package net.darkflameproduction.agotmod.entity.custom.npc.goals.miner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.ItemTags;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.resources.ResourceLocation;

import java.util.EnumSet;
import java.util.Set;
import java.util.HashSet;

public class MinerBarrelDropOffGoal extends Goal {
    private final Peasant_Entity peasant;
    private BlockPos targetBarrel;
    private long lastDropOffDay = -1; // Track which day we last dropped off

    // Set of disallowed items that cannot be dropped off (blacklist approach)
    private static final Set<String> DISALLOWED_ITEMS = new HashSet<>();

    static {
        // Torches - miners need these for mining
        DISALLOWED_ITEMS.add("minecraft:torch");
        DISALLOWED_ITEMS.add("minecraft:soul_torch");
        DISALLOWED_ITEMS.add("minecraft:redstone_torch");
        DISALLOWED_ITEMS.add("minecraft:wall_torch");
        DISALLOWED_ITEMS.add("minecraft:soul_wall_torch");
        DISALLOWED_ITEMS.add("minecraft:redstone_wall_torch");

        // Pickaxes - miners need these for their job
        DISALLOWED_ITEMS.add("minecraft:wooden_pickaxe");
        DISALLOWED_ITEMS.add("minecraft:stone_pickaxe");
        DISALLOWED_ITEMS.add("minecraft:iron_pickaxe");
        DISALLOWED_ITEMS.add("minecraft:golden_pickaxe");
        DISALLOWED_ITEMS.add("minecraft:diamond_pickaxe");
        DISALLOWED_ITEMS.add("minecraft:netherite_pickaxe");

        // Add any custom mod pickaxes
        DISALLOWED_ITEMS.add("agotmod:bronze_pickaxe");
        DISALLOWED_ITEMS.add("agotmod:steel_pickaxe");

        // Food items that miners should keep for themselves
        // Note: We'll also check the meat tag, but add some explicit ones for safety
        DISALLOWED_ITEMS.add("minecraft:bread");
        DISALLOWED_ITEMS.add("minecraft:apple");
        DISALLOWED_ITEMS.add("minecraft:golden_apple");
        DISALLOWED_ITEMS.add("minecraft:enchanted_golden_apple");
        DISALLOWED_ITEMS.add("minecraft:potato");
        DISALLOWED_ITEMS.add("minecraft:baked_potato");
        DISALLOWED_ITEMS.add("minecraft:carrot");
        DISALLOWED_ITEMS.add("minecraft:golden_carrot");
        DISALLOWED_ITEMS.add("minecraft:wheat");
        DISALLOWED_ITEMS.add("minecraft:beetroot");
        DISALLOWED_ITEMS.add("minecraft:beetroot_soup");
        DISALLOWED_ITEMS.add("minecraft:mushroom_stew");
        DISALLOWED_ITEMS.add("minecraft:rabbit_stew");
        DISALLOWED_ITEMS.add("minecraft:suspicious_stew");
    }

    /**
     * Gets the day of the last drop-off (for persistence)
     */
    public long getLastDropOffDay() {
        return lastDropOffDay;
    }

    /**
     * Sets the day of the last drop-off (for loading from NBT)
     */
    public void setLastDropOffDay(long day) {
        this.lastDropOffDay = day;
    }

    public MinerBarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // EXTENSIVE DEBUG LOGGING - log every check for miners
        if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_MINER) && peasant.tickCount % 50 == 0) {
            long dayTime = peasant.level().getDayTime() % 24000;
            long currentDay = peasant.level().getDayTime() / 24000;

            System.out.println("=== MINER BARREL DROP OFF DEBUG [" + peasant.getDisplayName().getString() + "] ===");
            System.out.println("JobType: " + peasant.getJobType());
            System.out.println("JobBlockPos: " + peasant.getJobBlockPos());
            System.out.println("Sleeping: " + peasant.isSleeping());
            System.out.println("Eating: " + peasant.getHungerSystem().isEating());
            System.out.println("DayTime: " + dayTime + " (end of work day: " + (dayTime >= 12000 && dayTime <= 13000) + ")");
            System.out.println("CurrentDay: " + currentDay + ", LastDropOffDay: " + lastDropOffDay);
            System.out.println("HasDroppableItems: " + hasDroppableItems());
            if (peasant.getJobBlockPos() != null) {
                BlockState barrelState = peasant.level().getBlockState(peasant.getJobBlockPos());
                System.out.println("BarrelBlock: " + barrelState.getBlock() + " (is miner barrel: " + barrelState.is(ModBLocks.MINER_BARREL.get()) + ")");
            }
            System.out.println("===============================================");
        }

        // Only for miners
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Not a miner");
            }
            return false;
        }

        // Must have a job block
        if (peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: No job block position");
            }
            return false;
        }

        // Must not be sleeping or eating
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Sleeping or eating");
            }
            return false;
        }

        // Check if it's end of working day (working day ends at 12000 ticks)
        long dayTime = peasant.level().getDayTime() % 24000;
        boolean isEndOfWorkDay = (dayTime >= 12000 && dayTime <= 13000);

        if (!isEndOfWorkDay) {
            if (!peasant.level().isClientSide && peasant.tickCount % 400 == 0) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Not end of work day (time: " + dayTime + ")");
            }
            return false;
        }

        // Check if we already dropped off today
        long currentDay = peasant.level().getDayTime() / 24000;
        if (lastDropOffDay == currentDay) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Already dropped off today (day " + currentDay + ")");
            }
            return false;
        }

        // Verify job block is still a miner barrel
        targetBarrel = peasant.getJobBlockPos();
        BlockState barrelState = peasant.level().getBlockState(targetBarrel);
        if (!barrelState.is(ModBLocks.MINER_BARREL.get())) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Job block is not a miner barrel: " + barrelState.getBlock());
            }
            return false;
        }

        // Check if we have any droppable items
        if (!hasDroppableItems()) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: No droppable items");
                logInventoryContents(); // Log what they actually have
            }
            // DON'T mark as completed - let them try again later if they get items
            return false;
        }

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: *** ACTIVATING DROP OFF *** at time " + dayTime + " on day " + currentDay);
            logInventoryContents(); // Log what items we're about to drop
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        // Stop if no target barrel
        if (targetBarrel == null) {
            return false;
        }

        // Stop if sleeping or eating
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Stop if job block changed or doesn't exist
        if (!targetBarrel.equals(peasant.getJobBlockPos())) {
            return false;
        }

        // Stop if barrel block was destroyed
        BlockState barrelState = peasant.level().getBlockState(targetBarrel);
        if (!barrelState.is(ModBLocks.MINER_BARREL.get())) {
            return false;
        }

        // Stop if we've already completed drop-off
        long currentDay = peasant.level().getDayTime() / 24000;
        if (lastDropOffDay == currentDay) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Goal started - moving to barrel");
        }

        if (targetBarrel != null) {
            peasant.getNavigation().moveTo(
                    targetBarrel.getX() + 0.5,
                    targetBarrel.getY(),
                    targetBarrel.getZ() + 0.5,
                    0.8D
            );
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        targetBarrel = null;

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Goal stopped");
        }
    }

    @Override
    public void tick() {
        if (targetBarrel == null) {
            return;
        }

        // Look at the barrel
        peasant.getLookControl().setLookAt(
                targetBarrel.getX() + 0.5,
                targetBarrel.getY() + 0.5,
                targetBarrel.getZ() + 0.5
        );

        // Check if we're close enough to the barrel
        double distanceToBarrel = peasant.distanceToSqr(
                targetBarrel.getX() + 0.5,
                targetBarrel.getY(),
                targetBarrel.getZ() + 0.5
        );

        if (distanceToBarrel <= 4.0D) { // Within 2 blocks
            // Close enough - perform the drop off
            performDropOff();
        } else {
            // Too far - keep moving to barrel
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        targetBarrel.getX() + 0.5,
                        targetBarrel.getY(),
                        targetBarrel.getZ() + 0.5,
                        0.8D
                );
            }
        }
    }

    /**
     * Performs the actual drop-off of all droppable items
     */
    private void performDropOff() {
        BlockEntity blockEntity = peasant.level().getBlockEntity(targetBarrel);

        if (!(blockEntity instanceof Container barrelInventory)) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Barrel is not a container!");
            }
            return;
        }

        var peasantInventory = peasant.getInventorySystem().getInventory();
        int totalDropped = 0;

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Performing drop off...");
        }

        // Go through peasant inventory and drop off all droppable items
        for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
            ItemStack stack = peasantInventory.getItem(i);

            if (!stack.isEmpty() && isDroppableItem(stack)) {
                // Try to add this stack to the barrel
                ItemStack remaining = addToBarrel(barrelInventory, stack.copy());

                // Calculate how much we actually moved
                int movedAmount = stack.getCount() - (remaining.isEmpty() ? 0 : remaining.getCount());

                if (movedAmount > 0) {
                    totalDropped += movedAmount;

                    // Update the peasant's inventory
                    if (remaining.isEmpty()) {
                        peasantInventory.setItem(i, ItemStack.EMPTY);
                    } else {
                        peasantInventory.setItem(i, remaining);
                    }
                }
            }
        }

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Dropped off " + totalDropped + " items");
        }

        // Mark drop-off as completed for today
        long currentDay = peasant.level().getDayTime() / 24000;
        lastDropOffDay = currentDay;
    }

    /**
     * Attempts to add an item stack to the barrel inventory
     * @param barrelInventory The barrel's inventory
     * @param stackToAdd The stack to add
     * @return The remaining items that couldn't be added (empty if all was added)
     */
    private ItemStack addToBarrel(Container barrelInventory, ItemStack stackToAdd) {
        if (stackToAdd.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // First pass: try to merge with existing stacks
        for (int i = 0; i < barrelInventory.getContainerSize(); i++) {
            ItemStack existing = barrelInventory.getItem(i);

            if (!existing.isEmpty() && ItemStack.isSameItemSameComponents(existing, stackToAdd)) {
                int maxStackSize = Math.min(existing.getMaxStackSize(), barrelInventory.getMaxStackSize());
                int canAdd = maxStackSize - existing.getCount();

                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, stackToAdd.getCount());
                    existing.grow(toAdd);
                    stackToAdd.shrink(toAdd);

                    if (stackToAdd.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        // Second pass: try to place in empty slots
        for (int i = 0; i < barrelInventory.getContainerSize(); i++) {
            if (barrelInventory.getItem(i).isEmpty()) {
                barrelInventory.setItem(i, stackToAdd.copy());
                return ItemStack.EMPTY;
            }
        }

        // Barrel is full, return the remaining items
        return stackToAdd;
    }

    /**
     * Checks if the peasant has any droppable items in inventory
     */
    private boolean hasDroppableItems() {
        var inventory = peasant.getInventorySystem().getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isDroppableItem(stack)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if an item can be dropped off (not on the blacklist)
     * Uses a blacklist approach - items are droppable unless specifically excluded
     */
    private boolean isDroppableItem(ItemStack stack) {
        // Check if it's a pickaxe (using instanceof for safety)
        if (stack.getItem() instanceof PickaxeItem) {
            return false;
        }

        // Check if it's tagged as meat
        if (stack.is(ItemTags.MEAT)) {
            return false;
        }

        // Check if it's in our explicit blacklist
        ResourceLocation itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (DISALLOWED_ITEMS.contains(itemId.toString())) {
            return false;
        }

        // If it passes all checks, it's droppable
        return true;
    }

    /**
     * Debug method to log what's actually in the peasant's inventory
     */
    private void logInventoryContents() {
        var inventory = peasant.getInventorySystem().getInventory();
        System.out.println("=== MINER INVENTORY CONTENTS [" + peasant.getDisplayName().getString() + "] ===");

        int totalItems = 0;
        int droppableItems = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                totalItems += stack.getCount();
                ResourceLocation itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
                boolean droppable = isDroppableItem(stack);
                if (droppable) droppableItems += stack.getCount();

                String reason = "";
                if (!droppable) {
                    if (stack.getItem() instanceof PickaxeItem) reason = " (pickaxe)";
                    else if (stack.is(ItemTags.MEAT)) reason = " (meat)";
                    else if (DISALLOWED_ITEMS.contains(itemId.toString())) reason = " (blacklisted)";
                }

                System.out.println("Slot " + i + ": " + stack.getCount() + "x " + itemId + " (droppable: " + droppable + ")" + reason);
            }
        }

        System.out.println("Total items: " + totalItems + ", Droppable items: " + droppableItems);
        System.out.println("Items that WON'T be dropped: pickaxes, torches, meat items, and specific blacklisted items");
        System.out.println("===============================================");
    }
}