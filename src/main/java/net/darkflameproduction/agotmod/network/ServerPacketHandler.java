package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
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
                    System.out.println("DEBUG: Transaction failed - insufficient funds. Needed: " + totalCost + ", Has: " + playerBalance);
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
                    System.out.println("DEBUG: Transaction completed for " + packet.grocerName() + " - Cost: " + totalCost + ", Player balance: " + newPlayerBalance);
                } else {
                    player.sendSystemMessage(Component.literal("Transaction failed - insufficient items!"));
                    System.out.println("DEBUG: Transaction failed for " + packet.grocerName());
                }
            } else {
                player.sendSystemMessage(Component.literal("Grocer not found!"));
                System.out.println("DEBUG: Grocer not found: " + packet.grocerName());
            }
        });
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

                            System.out.println("DEBUG: Player " + serverPlayer.getName().getString() +
                                    " renamed town at " + pos + " from '" + oldName + "' to '" + sanitizedName + "'");

                            // Send updated data to all nearby players
                            level.players().forEach(player -> {
                                if (player instanceof ServerPlayer nearbyPlayer) {
                                    double playerDistance = nearbyPlayer.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
                                    if (playerDistance < 64 * 64) { // Within 64 blocks for GUI updates
                                        PacketDistributor.sendToPlayer(nearbyPlayer,
                                                new TownHallDataPacket(pos, townHallBE.getBedCount(),
                                                        townHallBE.getCitizenCount(), townHallBE.getCurrentScanRadius(),
                                                        townHallBE.getTownName()));
                                    }
                                }
                            });

                        } else {
                            System.out.println("DEBUG: Player " + serverPlayer.getName().getString() +
                                    " tried to rename town at " + pos + " but was too far away (distance: " + Math.sqrt(distance) + ")");
                        }
                    } else {
                        System.out.println("DEBUG: Block at " + pos + " is not a Town Hall block entity");
                    }
                } else {
                    System.out.println("DEBUG: Block position " + pos + " is not loaded");
                }
            }
        });
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
                System.out.println("DEBUG: Insufficient " + itemKey + " (requested: " + requestedAmount + ")");
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
            System.out.println("DEBUG: Invalid item key: " + itemKey);
            return;
        }

        Item item = BuiltInRegistries.ITEM.getValue(itemLocation);
        if (item == null || item == Items.AIR) {
            System.out.println("DEBUG: Item not found or is air: " + itemKey);
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

        System.out.println("DEBUG: Spawning " + amount + " of " + itemKey + " at player location (" + x + ", " + y + ", " + z + ")");

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