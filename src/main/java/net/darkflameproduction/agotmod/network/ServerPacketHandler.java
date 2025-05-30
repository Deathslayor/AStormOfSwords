package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
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
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Map;

public class ServerPacketHandler {

    public static void handleFinishTransaction(FinishTransactionPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            ServerLevel level = player.serverLevel();

            // Find the grocer by name in the current level
            Northern_Peasant_Entity targetGrocer = null;

            for (Entity entity : level.getAllEntities()) {
                if (entity instanceof Northern_Peasant_Entity peasant) {
                    if (peasant.getDisplayName().getString().equals(packet.grocerName()) &&
                            peasant.getJobType().equals("grocer")) {
                        targetGrocer = peasant;
                        break;
                    }
                }
            }

            if (targetGrocer != null) {
                boolean success = processTransactionAndSpawnItems(targetGrocer, packet.itemsToSubtract(), player, level);

                if (success) {
                    player.sendSystemMessage(Component.literal("Transaction completed successfully! Items dropped at your location."));
                    System.out.println("DEBUG: Transaction completed for " + packet.grocerName() + " - items spawned at player location");
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

    private static boolean processTransactionAndSpawnItems(Northern_Peasant_Entity grocer, Map<String, Integer> itemsToSubtract, ServerPlayer player, ServerLevel level) {
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

        System.out.println("DEBUG: Spawned ItemEntity with " + stack.getCount() + " " + stack.getItem().getDescriptionId() + " (" + (index + 1) + "/" + total + ")");
    }
}