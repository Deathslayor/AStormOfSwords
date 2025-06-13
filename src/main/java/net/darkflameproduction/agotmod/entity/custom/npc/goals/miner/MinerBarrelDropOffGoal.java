package net.darkflameproduction.agotmod.entity.custom.npc.goals.miner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
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

    // Set of allowed items that can be dropped off (mining-related items)
    private static final Set<String> ALLOWED_ITEMS = new HashSet<>();

    static {
        // Vanilla ores and materials
        ALLOWED_ITEMS.add("minecraft:coal");
        ALLOWED_ITEMS.add("minecraft:iron_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_iron_ore");
        ALLOWED_ITEMS.add("minecraft:gold_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_gold_ore");
        ALLOWED_ITEMS.add("minecraft:diamond_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_diamond_ore");
        ALLOWED_ITEMS.add("minecraft:emerald_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_emerald_ore");
        ALLOWED_ITEMS.add("minecraft:lapis_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_lapis_ore");
        ALLOWED_ITEMS.add("minecraft:redstone_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_redstone_ore");
        ALLOWED_ITEMS.add("minecraft:copper_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_copper_ore");

        // Raw materials
        ALLOWED_ITEMS.add("minecraft:raw_iron");
        ALLOWED_ITEMS.add("minecraft:raw_gold");
        ALLOWED_ITEMS.add("minecraft:raw_copper");

        // Ingots and gems
        ALLOWED_ITEMS.add("minecraft:iron_ingot");
        ALLOWED_ITEMS.add("minecraft:gold_ingot");
        ALLOWED_ITEMS.add("minecraft:copper_ingot");
        ALLOWED_ITEMS.add("minecraft:diamond");
        ALLOWED_ITEMS.add("minecraft:emerald");
        ALLOWED_ITEMS.add("minecraft:lapis_lazuli");
        ALLOWED_ITEMS.add("minecraft:redstone");

        // Stone and building materials
        ALLOWED_ITEMS.add("minecraft:stone");
        ALLOWED_ITEMS.add("minecraft:cobblestone");
        ALLOWED_ITEMS.add("minecraft:deepslate");
        ALLOWED_ITEMS.add("minecraft:cobbled_deepslate");
        ALLOWED_ITEMS.add("minecraft:granite");
        ALLOWED_ITEMS.add("minecraft:diorite");
        ALLOWED_ITEMS.add("minecraft:andesite");

        // TODO: Add custom mod ores when they exist
        // ALLOWED_ITEMS.add("agotmod:tin_ore");
        // ALLOWED_ITEMS.add("agotmod:silver_ore");
        // etc.
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
            System.out.println("HasAllowedItems: " + hasAllowedItems());
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

        // Check if we have any allowed items to drop off
        if (!hasAllowedItems()) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: No allowed items to drop off");
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
     * Performs the actual drop-off of all allowed items
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

        // Go through peasant inventory and drop off all allowed items
        for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
            ItemStack stack = peasantInventory.getItem(i);

            if (!stack.isEmpty() && isAllowedItem(stack)) {
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
     * Checks if the peasant has any allowed items in inventory
     */
    private boolean hasAllowedItems() {
        var inventory = peasant.getInventorySystem().getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isAllowedItem(stack)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if an item is on the allowed list
     */
    private boolean isAllowedItem(ItemStack stack) {
        ResourceLocation itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        return ALLOWED_ITEMS.contains(itemId.toString());
    }

    /**
     * Debug method to log what's actually in the peasant's inventory
     */
    private void logInventoryContents() {
        var inventory = peasant.getInventorySystem().getInventory();
        System.out.println("=== MINER INVENTORY CONTENTS [" + peasant.getDisplayName().getString() + "] ===");

        int totalItems = 0;
        int allowedItems = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                totalItems += stack.getCount();
                ResourceLocation itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
                boolean allowed = ALLOWED_ITEMS.contains(itemId.toString());
                if (allowed) allowedItems += stack.getCount();

                System.out.println("Slot " + i + ": " + stack.getCount() + "x " + itemId + " (allowed: " + allowed + ")");
            }
        }

        System.out.println("Total items: " + totalItems + ", Allowed items: " + allowedItems);

        // Also log a few sample allowed items for comparison
        System.out.println("Sample allowed items: minecraft:coal, minecraft:iron_ore, minecraft:diamond, minecraft:cobblestone");
        System.out.println("===============================================");
    }
}