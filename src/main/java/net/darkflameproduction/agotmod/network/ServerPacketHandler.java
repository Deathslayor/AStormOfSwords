package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
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

public class ServerPacketHandler {

    private static final String COIN_BALANCE_KEY = "agotmod.coin_balance";

    public static void handleFinishTransaction(FinishTransactionPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            ServerLevel level = player.serverLevel();

            // Find the grocer by name in the current level
            Peasant_Entity targetGrocer = null;

            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof Peasant_Entity peasant) {
                    if (peasant.getDisplayName().getString().equals(packet.grocerName()) &&
                            peasant.getJobType().equals("grocer")) {
                        targetGrocer = peasant;
                        break;
                    }
                }
            }

            if (targetGrocer != null) {
                // Calculate total cost
                long totalCost = calculateTotalCost(packet.itemsToSubtract());

                // Check if player has enough money
                long playerBalance = getPlayerBalance(player);

                if (playerBalance < totalCost) {
                    player.sendSystemMessage(Component.literal("Insufficient funds! You need " + totalCost + " coins but only have " + playerBalance + "."));
                    return;
                }

                boolean success = processTransactionAndSpawnItems(targetGrocer, packet.itemsToSubtract(), player, level);

                if (success) {
                    // Deduct money from player
                    deductPlayerBalance(player, totalCost);

                    // Add money to grocer
                    targetGrocer.getGrocerSystem().addCoinsToBalance(totalCost);

                    // Send updated balance to player
                    long newPlayerBalance = getPlayerBalance(player);
                    PacketDistributor.sendToPlayer(player, new CoinBalancePacket(newPlayerBalance));

                    player.sendSystemMessage(Component.literal("Transaction completed! Spent " + totalCost + " coins. Remaining balance: " + newPlayerBalance));
                } else {
                    player.sendSystemMessage(Component.literal("Transaction failed - insufficient items!"));
                }
            } else {
                player.sendSystemMessage(Component.literal("Grocer not found!"));
            }
        });
    }

    public static void handleRequestOwnedTowns(RequestOwnedTownsPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                // Find all town halls claimed by this player's UUID
                java.util.List<SyncOwnedTownsPacket.TownInfo> ownedTowns = new java.util.ArrayList<>();
                ServerLevel level = serverPlayer.serverLevel();

                // Search within the player's view distance for claimed town halls
                int viewDistance = level.getServer().getPlayerList().getViewDistance();
                net.minecraft.world.level.ChunkPos playerChunkPos = new net.minecraft.world.level.ChunkPos(serverPlayer.blockPosition());

                for (int chunkX = playerChunkPos.x - viewDistance; chunkX <= playerChunkPos.x + viewDistance; chunkX++) {
                    for (int chunkZ = playerChunkPos.z - viewDistance; chunkZ <= playerChunkPos.z + viewDistance; chunkZ++) {
                        // Only check loaded chunks
                        if (level.getChunkSource().hasChunk(chunkX, chunkZ)) {
                            net.minecraft.world.level.chunk.LevelChunk chunk = level.getChunk(chunkX, chunkZ);

                            // Check all block entities in this chunk
                            for (net.minecraft.world.level.block.entity.BlockEntity blockEntity : chunk.getBlockEntities().values()) {
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

                // Sort by population (largest first)
                ownedTowns.sort((a, b) -> Integer.compare(b.population(), a.population()));

                // Send the data to the client
                PacketDistributor.sendToPlayer(serverPlayer, new SyncOwnedTownsPacket(ownedTowns));
            }
        });
    }

    // Add this method to ServerPacketHandler
    public static void handleCheckHouseName(CheckHouseNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                String proposedName = packet.proposedHouseName().trim();

                // Get the player's current house name
                String currentHouseName = getPlayerHouseName(serverPlayer);

                // If they're trying to set the same name they already have, allow it
                if (proposedName.equals(currentHouseName)) {
                    PacketDistributor.sendToPlayer(serverPlayer,
                            new HouseNameValidationPacket(true, "House name unchanged"));
                    return;
                }

                // Check if the proposed name is already taken by another player
                boolean isAvailable = isHouseNameAvailable(proposedName, serverPlayer);

                String message;
                if (isAvailable) {
                    message = "House name is available";
                } else {
                    message = "House name '" + proposedName + "' is already taken by another player";
                }

                PacketDistributor.sendToPlayer(serverPlayer,
                        new HouseNameValidationPacket(isAvailable, message));
            }
        });
    }

    // Updated isHouseNameAvailable method
    public static boolean isHouseNameAvailable(String houseName, ServerPlayer requestingPlayer) {
        if (houseName == null || houseName.trim().isEmpty()) {
            return false;
        }

        String trimmedName = houseName.trim();

        // Check all online players
        for (ServerPlayer player : requestingPlayer.getServer().getPlayerList().getPlayers()) {
            if (player.equals(requestingPlayer)) {
                continue; // Skip the requesting player
            }

            String playerHouseName = getPlayerHouseName(player);
            if (trimmedName.equalsIgnoreCase(playerHouseName)) {
                return false; // Name is taken
            }
        }

        // Check offline players by scanning saved player data
        try {
            java.nio.file.Path worldPath = requestingPlayer.getServer().getWorldPath(net.minecraft.world.level.storage.LevelResource.ROOT);
            java.nio.file.Path playersDir = worldPath.resolve("playerdata");

            if (java.nio.file.Files.exists(playersDir) && java.nio.file.Files.isDirectory(playersDir)) {
                try (java.util.stream.Stream<java.nio.file.Path> playerFiles = java.nio.file.Files.list(playersDir)) {
                    for (java.nio.file.Path playerFile : playerFiles.collect(java.util.stream.Collectors.toList())) {
                        if (playerFile.toString().endsWith(".dat")) {
                            try {
                                String fileName = playerFile.getFileName().toString().replace(".dat", "");
                                java.util.UUID playerUUID = java.util.UUID.fromString(fileName);

                                // Skip the requesting player
                                if (playerUUID.equals(requestingPlayer.getUUID())) {
                                    continue;
                                }

                                // Load the player's NBT data
                                net.minecraft.nbt.CompoundTag playerData = net.minecraft.nbt.NbtIo.readCompressed(
                                        playerFile, net.minecraft.nbt.NbtAccounter.unlimitedHeap());

                                if (playerData.contains(AGoTMod.MOD_ID + ".house")) {
                                    net.minecraft.nbt.CompoundTag houseTag = playerData.getCompound(AGoTMod.MOD_ID + ".house");
                                    if (houseTag.contains("house_name")) {
                                        String savedHouseName = houseTag.getString("house_name");
                                        if (trimmedName.equalsIgnoreCase(savedHouseName)) {
                                            return false; // Name is taken by offline player
                                        }
                                    }
                                }

                            } catch (IllegalArgumentException e) {
                                // Skip files that don't have valid UUID names
                                continue;
                            } catch (Exception e) {
                                // Skip files that can't be processed
                                continue;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            // Error checking offline players - continue with online check result
        }

        return true; // Name is available
    }

    public static void handleUpdateTownName(UpdateTownNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // Get the player who sent the packet
            if (context.player() instanceof ServerPlayer serverPlayer) {
                Level level = serverPlayer.level();
                BlockPos pos = packet.pos();
                String newName = packet.newName();

                // Validate the block position and get the block entity
                if (level.isLoaded(pos)) {
                    BlockEntity blockEntity = level.getBlockEntity(pos);

                    if (blockEntity instanceof TownHallBlockEntity townHallBE) {
                        // Check if player is close enough to modify the town name (within 64 blocks)
                        double distance = serverPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                        if (distance <= 64 * 64) {

                            // Validate and sanitize the town name
                            String sanitizedName = sanitizeTownName(newName);

                            // Update the town name
                            String oldName = townHallBE.getTownName();
                            townHallBE.setTownName(sanitizedName);

                            // Send updated data to all nearby players
                            level.players().forEach(player -> {
                                if (player instanceof ServerPlayer nearbyPlayer) {
                                    double playerDistance = nearbyPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                                    if (playerDistance < 64 * 64) { // Within 64 blocks for GUI updates
                                        PacketDistributor.sendToPlayer(nearbyPlayer,
                                                new TownHallDataPacket(pos, townHallBE.getBedCount(),
                                                        townHallBE.getCitizenCount(), townHallBE.getCurrentScanRadius(),
                                                        townHallBE.getTownName(), townHallBE.isClaimed(), townHallBE.getClaimedByHouse()));
                                    }
                                }
                            });

                        } else {
                            serverPlayer.sendSystemMessage(Component.literal("You must be closer to the town hall to rename it."));
                        }
                    } else {
                        serverPlayer.sendSystemMessage(Component.literal("Block is not a Town Hall."));
                    }
                } else {
                    serverPlayer.sendSystemMessage(Component.literal("Location is not loaded."));
                }
            }
        });
    }

    public static void handleClaimTownHall(ClaimTownHallPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                Level level = serverPlayer.level();
                BlockPos pos = packet.pos();

                // Validate the block position and get the block entity
                if (level.isLoaded(pos)) {
                    BlockEntity blockEntity = level.getBlockEntity(pos);

                    if (blockEntity instanceof TownHallBlockEntity townHallBE) {
                        // Check if player is close enough to claim (within 16 blocks)
                        double distance = serverPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                        if (distance <= 16 * 16) {

                            // Check if town hall is already claimed
                            if (townHallBE.isClaimed()) {
                                serverPlayer.sendSystemMessage(Component.literal("This town is already claimed by " + townHallBE.getClaimedByHouse()));
                                return;
                            }

                            // Get player's house name
                            String playerHouseName = getPlayerHouseName(serverPlayer);
                            if (playerHouseName.isEmpty()) {
                                serverPlayer.sendSystemMessage(Component.literal("You must set your house name before claiming a town!"));
                                return;
                            }

                            // Attempt to claim the town hall with player UUID
                            boolean success = townHallBE.claimTownHall(playerHouseName, serverPlayer.getUUID());
                            if (success) {
                                serverPlayer.sendSystemMessage(Component.literal("Successfully claimed " + townHallBE.getTownName() + " for House " + playerHouseName + "!"));

                                // Send updated data to all nearby players
                                level.players().forEach(player -> {
                                    if (player instanceof ServerPlayer nearbyPlayer) {
                                        double playerDistance = nearbyPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                                        if (playerDistance < 64 * 64) { // Within 64 blocks for GUI updates
                                            PacketDistributor.sendToPlayer(nearbyPlayer,
                                                    new TownHallDataPacket(pos, townHallBE.getBedCount(),
                                                            townHallBE.getCitizenCount(), townHallBE.getCurrentScanRadius(),
                                                            townHallBE.getTownName(), townHallBE.isClaimed(), townHallBE.getClaimedByHouse()));
                                        }
                                    }
                                });

                                System.out.println("DEBUG: Player " + serverPlayer.getName().getString() +
                                        " claimed town '" + townHallBE.getTownName() + "' at " + pos + " for House " + playerHouseName);
                            } else {
                                serverPlayer.sendSystemMessage(Component.literal("Failed to claim town hall."));
                            }

                        } else {
                            serverPlayer.sendSystemMessage(Component.literal("You must be closer to the town hall to claim it."));
                        }
                    } else {
                        serverPlayer.sendSystemMessage(Component.literal("Block is not a Town Hall."));
                    }
                } else {
                    serverPlayer.sendSystemMessage(Component.literal("Location is not loaded."));
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

    private static long calculateTotalCost(Map<String, Integer> itemsToSubtract) {
        long totalCost = 0;
        for (Map.Entry<String, Integer> entry : itemsToSubtract.entrySet()) {
            int amount = entry.getValue();
            if (amount > 0) {
                int itemPrice = net.darkflameproduction.agotmod.util.ItemPricing.getItemSellPrice(entry.getKey());
                totalCost += (long) itemPrice * amount;
            }
        }
        return totalCost;
    }

    private static long getPlayerBalance(ServerPlayer player) {
        return player.getPersistentData().getLong(COIN_BALANCE_KEY);
    }

    private static void deductPlayerBalance(ServerPlayer player, long amount) {
        long currentBalance = getPlayerBalance(player);
        long newBalance = Math.max(0, currentBalance - amount);
        player.getPersistentData().putLong(COIN_BALANCE_KEY, newBalance);
    }

    private static boolean processTransactionAndSpawnItems(Peasant_Entity grocer, Map<String, Integer> itemsToSubtract, ServerPlayer player, ServerLevel level) {
        // Verify all items are available first
        for (Map.Entry<String, Integer> entry : itemsToSubtract.entrySet()) {
            String itemKey = entry.getKey();
            int requestedAmount = entry.getValue();

            if (!grocer.getGrocerSystem().hasDigitalItem(itemKey, requestedAmount)) {
                return false;
            }
        }

        // All items are available, proceed with transaction
        for (Map.Entry<String, Integer> entry : itemsToSubtract.entrySet()) {
            String itemKey = entry.getKey();
            int amountToSubtract = entry.getValue();

            // Remove from grocer's digital inventory
            grocer.getGrocerSystem().removeFromDigitalInventory(itemKey, amountToSubtract);

            // Spawn items as entities at player's location
            spawnItemsAtPlayer(itemKey, amountToSubtract, player, level);
        }

        return true;
    }

    private static void spawnItemsAtPlayer(String itemKey, int amount, ServerPlayer player, ServerLevel level) {
        // Get the item from the registry
        ResourceLocation itemLocation = ResourceLocation.tryParse(itemKey);
        if (itemLocation == null) {
            return;
        }

        Item item = BuiltInRegistries.ITEM.getValue(itemLocation);
        if (item == null || item == Items.AIR) {
            return;
        }

        // Get player's position
        Vec3 playerPos = player.position();
        double x = playerPos.x;
        double y = playerPos.y + 0.5; // Spawn slightly above player to avoid being inside them
        double z = playerPos.z;

        // Calculate how many stacks we need (max stack size is 64 for most items)
        int maxStackSize = item.getDefaultMaxStackSize();
        int fullStacks = amount / maxStackSize;
        int remainder = amount % maxStackSize;

        // Spawn full stacks
        for (int i = 0; i < fullStacks; i++) {
            ItemStack stack = new ItemStack(item, maxStackSize);
            spawnSingleItemEntity(stack, x, y, z, level, i, fullStacks + (remainder > 0 ? 1 : 0));
        }

        // Spawn remainder stack if any
        if (remainder > 0) {
            ItemStack stack = new ItemStack(item, remainder);
            spawnSingleItemEntity(stack, x, y, z, level, fullStacks, fullStacks + 1);
        }
    }

    private static void spawnSingleItemEntity(ItemStack stack, double x, double y, double z, ServerLevel level, int index, int total) {
        // Create the item entity
        ItemEntity itemEntity = new ItemEntity(level, x, y, z, stack);

        // Add some random spread so items don't all spawn in the exact same spot
        double spreadRadius = 0.5;
        double randomX = (Math.random() - 0.5) * spreadRadius;
        double randomZ = (Math.random() - 0.5) * spreadRadius;

        // Set position with slight randomization
        itemEntity.setPos(x + randomX, y, z + randomZ);

        // Add slight upward velocity and random horizontal velocity
        double velocityX = (Math.random() - 0.5) * 0.2;
        double velocityY = 0.1 + Math.random() * 0.1; // Small upward velocity
        double velocityZ = (Math.random() - 0.5) * 0.2;

        itemEntity.setDeltaMovement(velocityX, velocityY, velocityZ);

        // Set pickup delay so items don't get picked up immediately
        itemEntity.setPickUpDelay(10); // 0.5 seconds delay

        // Spawn the entity in the world
        level.addFreshEntity(itemEntity);

    }
    private static String sanitizeTownName(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Unnamed Town";
        }

        // Trim whitespace and limit length
        String sanitized = input.trim();
        if (sanitized.length() > 32) {
            sanitized = sanitized.substring(0, 32);
        }

        // Remove any potentially problematic characters (optional - adjust as needed)
        // Keep letters, numbers, spaces, and common punctuation
        sanitized = sanitized.replaceAll("[^\\p{L}\\p{N}\\s\\-'.,!]", "");

        // Ensure it's not empty after sanitization
        if (sanitized.trim().isEmpty()) {
            return "Unnamed Town";
        }

        return sanitized.trim();
    }
}