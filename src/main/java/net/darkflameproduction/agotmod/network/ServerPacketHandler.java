package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Map;
import java.util.Optional;

public class ServerPacketHandler {

    private static final String COIN_BALANCE_KEY = "agotmod.coin_balance";

    public static void handleFinishTransaction(FinishTransactionPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            ServerLevel level = player.serverLevel();

            // Find the NPC: must match the name AND be a trader-type job
            Peasant_Entity targetNpc = null;
            for (Entity entity : level.getAllEntities()) {
                if (!(entity instanceof Peasant_Entity peasant)) continue;
                String job = peasant.getJobType();
                boolean isTradeJob =
                        job.equals(JobSystem.JOB_GROCER)    ||
                                job.equals(JobSystem.JOB_BUTCHER)   ||
                                job.equals(JobSystem.JOB_TANNER)    ||
                                job.equals(JobSystem.JOB_TAILOR)    ||
                                job.equals(JobSystem.JOB_BLACKSMITH)||
                                job.equals(JobSystem.JOB_CARPENTER) ||
                                job.equals(JobSystem.JOB_TRADER);
                if (isTradeJob && peasant.getDisplayName().getString().equals(packet.grocerName())) {
                    targetNpc = peasant;
                    break;
                }
            }

            if (targetNpc == null) {
                player.sendSystemMessage(Component.literal("Trader not found!"));
                return;
            }

            TownHallBlockEntity townHall = getNpcTownHall(targetNpc, level);
            if (townHall == null) {
                player.sendSystemMessage(Component.literal("This trader is not registered to a town hall!"));
                return;
            }

            String jobType = targetNpc.getJobType();
            if (packet.isBuyTransaction()) {
                handleBuyTransaction(packet, player, targetNpc, townHall, level, jobType);
            } else if (packet.isSellTransaction()) {
                handleSellTransaction(packet, player, targetNpc, townHall, level, jobType);
            }
        });
    }


    private static TownHallBlockEntity getNpcTownHall(Peasant_Entity npc, ServerLevel level) {
        if (!npc.isRegisteredToTownHall()) return null;

        Optional<BlockPos> townHallPos = npc.getTownHallPos();
        if (townHallPos.isEmpty()) return null;

        BlockEntity blockEntity = level.getBlockEntity(townHallPos.get());
        if (blockEntity instanceof TownHallBlockEntity townHall) {
            return townHall;
        }
        return null;
    }

    private static void handleBuyTransaction(FinishTransactionPacket packet, ServerPlayer player,
                                             Peasant_Entity grocer, TownHallBlockEntity townHall,
                                             ServerLevel level, String jobType) {
        long totalCost = calculateBuyTotalCost(packet.getItemAmounts(), jobType);
        long playerBalance = getPlayerBalance(player);

        if (playerBalance < totalCost) {
            player.sendSystemMessage(Component.literal(
                    "Insufficient funds! You need " + totalCost + " coins but only have " + playerBalance + "."));
            return;
        }

        for (Map.Entry<String, Integer> entry : packet.getItemAmounts().entrySet()) {
            if (!townHall.hasTownInventoryItem(entry.getKey(), entry.getValue())) {
                player.sendSystemMessage(Component.literal("Transaction failed - insufficient items in town supply!"));
                return;
            }
        }

        for (Map.Entry<String, Integer> entry : packet.getItemAmounts().entrySet()) {
            townHall.removeFromTownInventory(entry.getKey(), entry.getValue());
            spawnItemsAtPlayer(entry.getKey(), entry.getValue(), player, level);
        }

        deductPlayerBalance(player, totalCost);
        townHall.addToTownBalance(totalCost);
        long taxAmount = (long) Math.floor(totalCost * 0.2);
        if (taxAmount > 0) {
            townHall.deductFromTownBalance(taxAmount);
            townHall.addToTownIncome(taxAmount);
        }

        long newPlayerBalance = getPlayerBalance(player);
        PacketDistributor.sendToPlayer(player, new CoinBalancePacket(newPlayerBalance));
        player.sendSystemMessage(Component.literal(
                "Purchase completed! Spent " + totalCost + " coins. Remaining balance: " + newPlayerBalance));
    }

    private static void handleSellTransaction(FinishTransactionPacket packet, ServerPlayer player,
                                              Peasant_Entity grocer, TownHallBlockEntity townHall,
                                              ServerLevel level, String jobType) {
        long totalEarnings = calculateSellTotalEarnings(packet.getItemAmounts(), jobType);
        long townBalance = townHall.getTownBalance();

        if (townBalance < totalEarnings) {
            player.sendSystemMessage(Component.literal(
                    "The town has insufficient funds! They have " + townBalance + " coins but need " + totalEarnings + "."));
            return;
        }

        if (!verifyPlayerHasItems(player, packet.getItemAmounts(), packet.getPlayerSlots())) {
            player.sendSystemMessage(Component.literal("You don't have enough of the selected items to sell!"));
            return;
        }

        boolean success = processSellTransaction(townHall, packet.getItemAmounts(), player);

        if (success) {
            addPlayerBalance(player, totalEarnings);
            townHall.deductFromTownBalance(totalEarnings);

            long newPlayerBalance = getPlayerBalance(player);
            PacketDistributor.sendToPlayer(player, new CoinBalancePacket(newPlayerBalance));
            player.sendSystemMessage(Component.literal(
                    "Sale completed! Earned " + totalEarnings + " coins. New balance: " + newPlayerBalance));
        } else {
            player.sendSystemMessage(Component.literal("Transaction failed!"));
        }
    }

    private static boolean processSellTransaction(TownHallBlockEntity townHall,
                                                  Map<String, Integer> itemAmounts,
                                                  ServerPlayer player) {
        for (Map.Entry<String, Integer> entry : itemAmounts.entrySet()) {
            String itemKey = entry.getKey();
            int amountToSell = entry.getValue();
            if (amountToSell <= 0) continue;

            int remainingToRemove = amountToSell;
            for (int slot = 0; slot < player.getInventory().getContainerSize() && remainingToRemove > 0; slot++) {
                ItemStack stack = player.getInventory().getItem(slot);
                if (stack.isEmpty()) continue;

                ResourceLocation itemLocation = BuiltInRegistries.ITEM.getKey(stack.getItem());
                if (itemLocation != null && itemLocation.toString().equals(itemKey)) {
                    int toRemove = Math.min(remainingToRemove, stack.getCount());
                    townHall.addToTownInventory(itemKey, toRemove);
                    stack.shrink(toRemove);
                    player.getInventory().setItem(slot, stack.isEmpty() ? ItemStack.EMPTY : stack);
                    remainingToRemove -= toRemove;
                }
            }

            if (remainingToRemove > 0) return false;
        }
        return true;
    }

    // ===== UNCHANGED HANDLERS =====

    public static void handleRequestOwnedTowns(RequestOwnedTownsPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                java.util.List<SyncOwnedTownsPacket.TownInfo> ownedTowns = new java.util.ArrayList<>();
                ServerLevel level = serverPlayer.serverLevel();

                int viewDistance = level.getServer().getPlayerList().getViewDistance();
                net.minecraft.world.level.ChunkPos playerChunkPos = new net.minecraft.world.level.ChunkPos(serverPlayer.blockPosition());

                for (int chunkX = playerChunkPos.x - viewDistance; chunkX <= playerChunkPos.x + viewDistance; chunkX++) {
                    for (int chunkZ = playerChunkPos.z - viewDistance; chunkZ <= playerChunkPos.z + viewDistance; chunkZ++) {
                        if (level.getChunkSource().hasChunk(chunkX, chunkZ)) {
                            net.minecraft.world.level.chunk.LevelChunk chunk = level.getChunk(chunkX, chunkZ);
                            for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
                                if (blockEntity instanceof TownHallBlockEntity townHall) {
                                    if (townHall.isClaimed() && serverPlayer.getUUID().equals(townHall.getClaimedByPlayerUUID())) {
                                        ownedTowns.add(new SyncOwnedTownsPacket.TownInfo(
                                                townHall.getTownName(),
                                                townHall.getCitizenCount()
                                        ));
                                    }
                                }
                            }
                        }
                    }
                }

                ownedTowns.sort((a, b) -> Integer.compare(b.population(), a.population()));
                PacketDistributor.sendToPlayer(serverPlayer, new SyncOwnedTownsPacket(ownedTowns));
            }
        });
    }

    public static void handleCheckHouseName(CheckHouseNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                String proposedName = packet.proposedHouseName().trim();
                String currentHouseName = getPlayerHouseName(serverPlayer);

                if (proposedName.equals(currentHouseName)) {
                    PacketDistributor.sendToPlayer(serverPlayer,
                            new HouseNameValidationPacket(true, "House name unchanged"));
                    return;
                }

                boolean isAvailable = isHouseNameAvailable(proposedName, serverPlayer);
                String message = isAvailable ? "House name is available" :
                        "House name '" + proposedName + "' is already taken by another player";
                PacketDistributor.sendToPlayer(serverPlayer, new HouseNameValidationPacket(isAvailable, message));
            }
        });
    }

    public static boolean isHouseNameAvailable(String houseName, ServerPlayer requestingPlayer) {
        if (houseName == null || houseName.trim().isEmpty()) return false;

        String trimmedName = houseName.trim();

        for (ServerPlayer player : requestingPlayer.getServer().getPlayerList().getPlayers()) {
            if (player.equals(requestingPlayer)) continue;
            if (trimmedName.equalsIgnoreCase(getPlayerHouseName(player))) return false;
        }

        try {
            java.nio.file.Path worldPath = requestingPlayer.getServer().getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT);
            java.nio.file.Path playersDir = worldPath.resolve("playerdata");

            if (java.nio.file.Files.exists(playersDir) && java.nio.file.Files.isDirectory(playersDir)) {
                try (java.util.stream.Stream<java.nio.file.Path> playerFiles = java.nio.file.Files.list(playersDir)) {
                    for (java.nio.file.Path playerFile : playerFiles.collect(java.util.stream.Collectors.toList())) {
                        if (!playerFile.toString().endsWith(".dat")) continue;
                        try {
                            java.util.UUID playerUUID = java.util.UUID.fromString(
                                    playerFile.getFileName().toString().replace(".dat", ""));
                            if (playerUUID.equals(requestingPlayer.getUUID())) continue;

                            net.minecraft.nbt.CompoundTag playerData = net.minecraft.nbt.NbtIo.readCompressed(
                                    playerFile, net.minecraft.nbt.NbtAccounter.unlimitedHeap());

                            if (playerData.contains(AGoTMod.MOD_ID + ".house")) {
                                String savedName = playerData.getCompound(AGoTMod.MOD_ID + ".house").getString("house_name");
                                if (trimmedName.equalsIgnoreCase(savedName)) return false;
                            }
                        } catch (Exception ignored) {}
                    }
                }
            }
        } catch (Exception ignored) {}

        return true;
    }

    public static void handleUpdateTownName(UpdateTownNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                Level level = serverPlayer.level();
                BlockPos pos = packet.pos();

                if (level.isLoaded(pos)) {
                    BlockEntity blockEntity = level.getBlockEntity(pos);
                    if (blockEntity instanceof TownHallBlockEntity townHallBE) {
                        double distance = serverPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                        if (distance <= 64 * 64) {
                            townHallBE.setTownName(sanitizeTownName(packet.newName()));
                            broadcastTownHallData(level, pos, townHallBE, 64);
                        } else {
                            serverPlayer.sendSystemMessage(Component.literal("You must be closer to the town hall to rename it."));
                        }
                    } else {
                        serverPlayer.sendSystemMessage(Component.literal("Block is not a Town Hall."));
                    }
                }
            }
        });
    }

    public static void handleClaimTownHall(ClaimTownHallPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                Level level = serverPlayer.level();
                BlockPos pos = packet.pos();

                if (level.isLoaded(pos)) {
                    BlockEntity blockEntity = level.getBlockEntity(pos);
                    if (blockEntity instanceof TownHallBlockEntity townHallBE) {
                        double distance = serverPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                        if (distance <= 16 * 16) {
                            if (townHallBE.isClaimed()) {
                                serverPlayer.sendSystemMessage(Component.literal(
                                        "This town is already claimed by " + townHallBE.getClaimedByHouse()));
                                return;
                            }

                            String playerHouseName = getPlayerHouseName(serverPlayer);
                            if (playerHouseName.isEmpty()) {
                                serverPlayer.sendSystemMessage(Component.literal(
                                        "You must set your house name before claiming a town!"));
                                return;
                            }

                            boolean success = townHallBE.claimTownHall(playerHouseName, serverPlayer.getUUID());
                            if (success) {
                                serverPlayer.sendSystemMessage(Component.literal(
                                        "Successfully claimed " + townHallBE.getTownName() + " for House " + playerHouseName + "!"));
                                broadcastTownHallData(level, pos, townHallBE, 64);
                            } else {
                                serverPlayer.sendSystemMessage(Component.literal("Failed to claim town hall."));
                            }
                        } else {
                            serverPlayer.sendSystemMessage(Component.literal(
                                    "You must be closer to the town hall to claim it."));
                        }
                    } else {
                        serverPlayer.sendSystemMessage(Component.literal("Block is not a Town Hall."));
                    }
                }
            }
        });
    }

    // ===== HELPERS =====

    private static void broadcastTownHallData(Level level, BlockPos pos, TownHallBlockEntity townHall, int range) {
        level.players().forEach(player -> {
            if (player instanceof ServerPlayer nearbyPlayer) {
                double dist = nearbyPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                if (dist < range * range) {
                    PacketDistributor.sendToPlayer(nearbyPlayer, new TownHallDataPacket(
                            pos,
                            townHall.getBedCount(),
                            townHall.getCitizenCount(),
                            townHall.getCurrentScanRadius(),
                            townHall.getTownName(),
                            townHall.isClaimed(),
                            townHall.getClaimedByHouse(),
                            townHall.getAvailableJobCount(),
                            townHall.getAssignedJobCount(),
                            townHall.getTotalJobCount(),
                            townHall.getJoblessCount(),
                            townHall.getTownBalance(),
                            townHall.getTownIncome(),
                            new java.util.HashMap<>(townHall.getTownInventory()),
                            townHall.getCulture().name()
                    ));
                }
            }
        });
    }

    private static String getPlayerHouseName(ServerPlayer player) {
        if (player.getPersistentData().contains("agotmod.house")) {
            return player.getPersistentData().getCompound("agotmod.house").getString("house_name");
        }
        return "";
    }

    private static long calculateBuyTotalCost(Map<String, Integer> itemAmounts, String jobType) {
        long totalCost = 0;
        for (Map.Entry<String, Integer> entry : itemAmounts.entrySet()) {
            if (entry.getValue() > 0) {
                int price = jobType.equals(JobSystem.JOB_TRADER)
                        ? net.darkflameproduction.agotmod.util.ItemPricing.getTraderSellPrice(entry.getKey())
                        : net.darkflameproduction.agotmod.util.ItemPricing.getItemPrice(entry.getKey());
                totalCost += (long) price * entry.getValue();
            }
        }
        return totalCost;
    }

    private static long calculateSellTotalEarnings(Map<String, Integer> itemAmounts, String jobType) {
        long totalEarnings = 0;
        for (Map.Entry<String, Integer> entry : itemAmounts.entrySet()) {
            if (entry.getValue() > 0) {
                int price = jobType.equals(JobSystem.JOB_TRADER)
                        ? net.darkflameproduction.agotmod.util.ItemPricing.getTraderBuyPrice(entry.getKey())
                        : net.darkflameproduction.agotmod.util.ItemPricing.getItemSellPrice(entry.getKey());
                totalEarnings += (long) price * entry.getValue();
            }
        }
        return totalEarnings;
    }

    private static boolean verifyPlayerHasItems(ServerPlayer player, Map<String, Integer> itemAmounts,
                                                Map<String, Integer> playerSlots) {
        for (Map.Entry<String, Integer> entry : itemAmounts.entrySet()) {
            if (entry.getValue() <= 0) continue;
            int playerHasAmount = 0;
            for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
                ItemStack stack = player.getInventory().getItem(slot);
                if (!stack.isEmpty()) {
                    ResourceLocation itemLocation = BuiltInRegistries.ITEM.getKey(stack.getItem());
                    if (itemLocation != null && itemLocation.toString().equals(entry.getKey())) {
                        playerHasAmount += stack.getCount();
                    }
                }
            }
            if (playerHasAmount < entry.getValue()) return false;
        }
        return true;
    }

    private static long getPlayerBalance(ServerPlayer player) {
        return player.getPersistentData().getLong(COIN_BALANCE_KEY);
    }

    private static void addPlayerBalance(ServerPlayer player, long amount) {
        player.getPersistentData().putLong(COIN_BALANCE_KEY, getPlayerBalance(player) + amount);
    }

    private static void deductPlayerBalance(ServerPlayer player, long amount) {
        player.getPersistentData().putLong(COIN_BALANCE_KEY, Math.max(0, getPlayerBalance(player) - amount));
    }

    private static void spawnItemsAtPlayer(String itemKey, int amount, ServerPlayer player, ServerLevel level) {
        ResourceLocation itemLocation = ResourceLocation.tryParse(itemKey);
        if (itemLocation == null) return;

        Item item = BuiltInRegistries.ITEM.getValue(itemLocation);
        if (item == null || item == Items.AIR) return;

        Vec3 pos = player.position();
        int maxStackSize = item.getDefaultMaxStackSize();
        int fullStacks = amount / maxStackSize;
        int remainder = amount % maxStackSize;

        for (int i = 0; i < fullStacks; i++) {
            spawnSingleItemEntity(new ItemStack(item, maxStackSize), pos.x, pos.y + 0.5, pos.z, level);
        }
        if (remainder > 0) {
            spawnSingleItemEntity(new ItemStack(item, remainder), pos.x, pos.y + 0.5, pos.z, level);
        }
    }

    private static void spawnSingleItemEntity(ItemStack stack, double x, double y, double z, ServerLevel level) {
        ItemEntity itemEntity = new ItemEntity(level, x, y, z, stack);
        itemEntity.setPos(x + (Math.random() - 0.5) * 0.5, y, z + (Math.random() - 0.5) * 0.5);
        itemEntity.setDeltaMovement(
                (Math.random() - 0.5) * 0.2,
                0.1 + Math.random() * 0.1,
                (Math.random() - 0.5) * 0.2
        );
        itemEntity.setPickUpDelay(10);
        level.addFreshEntity(itemEntity);
    }

    private static String sanitizeTownName(String input) {
        if (input == null || input.trim().isEmpty()) return "Unnamed Town";
        String sanitized = input.trim();
        if (sanitized.length() > 32) sanitized = sanitized.substring(0, 32);
        sanitized = sanitized.replaceAll("[^\\p{L}\\p{N}\\s\\-'.,!]", "");
        return sanitized.trim().isEmpty() ? "Unnamed Town" : sanitized.trim();
    }

    public static void handleCloseNpcConversation(CloseNpcConversationPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                ServerLevel level = serverPlayer.serverLevel();
                Entity entity = level.getEntity(packet.npcUUID());
                if (entity instanceof Peasant_Entity peasant) {
                    peasant.clearInteractingPlayer();
                }
            }
        });
    }

    public static void handleOpenNpcInventory(OpenNpcInventoryPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                ServerLevel level = serverPlayer.serverLevel();
                Entity entity = level.getEntity(packet.npcUUID());
                if (entity instanceof Peasant_Entity peasant) {
                    peasant.setInteractingPlayer(serverPlayer);
                    peasant.getInventorySystem().openInventoryFor(serverPlayer);
                }
            }
        });
    }

    public static void handleOpenNpcTrade(OpenNpcTradePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                ServerLevel level = serverPlayer.serverLevel();
                Entity entity = level.getEntity(packet.npcUUID());
                if (entity instanceof Peasant_Entity peasant) {
                    peasant.setInteractingPlayer(serverPlayer);
                    switch (peasant.getJobType()) {
                        case JobSystem.JOB_GROCER ->
                                net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerInventoryTicketSystem.postRequest(
                                        peasant.getUUID(), serverPlayer.getUUID(), peasant.blockPosition());
                        case JobSystem.JOB_BUTCHER ->
                                net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherInventoryTicketSystem.postRequest(
                                        peasant.getUUID(), serverPlayer.getUUID(), peasant.blockPosition());
                        case JobSystem.JOB_TANNER ->
                                net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerInventoryTicketSystem.postRequest(
                                        peasant.getUUID(), serverPlayer.getUUID(), peasant.blockPosition());
                        case JobSystem.JOB_TAILOR ->
                                net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorInventoryTicketSystem.postRequest(
                                        peasant.getUUID(), serverPlayer.getUUID(), peasant.blockPosition());
                        case JobSystem.JOB_BLACKSMITH ->
                                net.darkflameproduction.agotmod.entity.custom.npc.system.blacksmith.BlacksmithInventoryTicketSystem.postRequest(
                                        peasant.getUUID(), serverPlayer.getUUID(), peasant.blockPosition());
                        case JobSystem.JOB_CARPENTER ->
                                net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterInventoryTicketSystem.postRequest(
                                        peasant.getUUID(), serverPlayer.getUUID(), peasant.blockPosition());
                        case JobSystem.JOB_TRADER ->
                                net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.postRequest(
                                        peasant.getUUID(), serverPlayer.getUUID(), peasant.blockPosition());
                    }
                }
            }
        });
    }
}