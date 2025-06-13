package net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer;

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

public class FarmerBarrelDropOffGoal extends Goal {
    private final Peasant_Entity peasant;
    private BlockPos targetBarrel;
    private long lastDropOffDay = -1; // Track which day we last dropped off

    // Set of allowed items that can be dropped off
    private static final Set<String> ALLOWED_ITEMS = new HashSet<>();

    static {
        // Vanilla items
        ALLOWED_ITEMS.add("minecraft:wheat");
        ALLOWED_ITEMS.add("minecraft:wheat_seeds");
        ALLOWED_ITEMS.add("minecraft:pumpkin");
        ALLOWED_ITEMS.add("minecraft:pumpkin_seeds");
        ALLOWED_ITEMS.add("minecraft:melon_slice");
        ALLOWED_ITEMS.add("minecraft:melon_seeds");
        ALLOWED_ITEMS.add("minecraft:beetroot");
        ALLOWED_ITEMS.add("minecraft:beetroot_seeds");
        ALLOWED_ITEMS.add("minecraft:carrot");
        ALLOWED_ITEMS.add("minecraft:potato");
        ALLOWED_ITEMS.add("minecraft:poisonous_potato");

        // Custom mod items
        ALLOWED_ITEMS.add("agotmod:horseradish");
        ALLOWED_ITEMS.add("agotmod:horseradish_seeds");
        ALLOWED_ITEMS.add("agotmod:onion");
        ALLOWED_ITEMS.add("agotmod:onion_seeds");
        ALLOWED_ITEMS.add("agotmod:red_onion");
        ALLOWED_ITEMS.add("agotmod:red_onion_seeds");
        ALLOWED_ITEMS.add("agotmod:wild_onion");
        ALLOWED_ITEMS.add("agotmod:wild_onion_seeds");
        ALLOWED_ITEMS.add("agotmod:leek");
        ALLOWED_ITEMS.add("agotmod:leek_seeds");
        ALLOWED_ITEMS.add("agotmod:neep");
        ALLOWED_ITEMS.add("agotmod:neep_seeds");
        ALLOWED_ITEMS.add("agotmod:turnip");
        ALLOWED_ITEMS.add("agotmod:turnip_seeds");
        ALLOWED_ITEMS.add("agotmod:parsley");
        ALLOWED_ITEMS.add("agotmod:parsley_seeds");
        ALLOWED_ITEMS.add("agotmod:bean");
        ALLOWED_ITEMS.add("agotmod:bean_seeds");
        ALLOWED_ITEMS.add("agotmod:green_bean");
        ALLOWED_ITEMS.add("agotmod:green_bean_seeds");
        ALLOWED_ITEMS.add("agotmod:chickpea");
        ALLOWED_ITEMS.add("agotmod:chickpea_seeds");
        ALLOWED_ITEMS.add("agotmod:cabbage");
        ALLOWED_ITEMS.add("agotmod:cabbage_seeds");
        ALLOWED_ITEMS.add("agotmod:spinach");
        ALLOWED_ITEMS.add("agotmod:spinach_seeds");
        ALLOWED_ITEMS.add("agotmod:cucumber");
        ALLOWED_ITEMS.add("agotmod:cucumber_seeds");
        ALLOWED_ITEMS.add("agotmod:dragon_pepper");
        ALLOWED_ITEMS.add("agotmod:dragon_pepper_seeds");
        ALLOWED_ITEMS.add("agotmod:pepper");
        ALLOWED_ITEMS.add("agotmod:pepper_seeds");
        ALLOWED_ITEMS.add("agotmod:peppercorn");
        ALLOWED_ITEMS.add("agotmod:peppercorn_seeds");
        ALLOWED_ITEMS.add("agotmod:barley");
        ALLOWED_ITEMS.add("agotmod:barley_seeds");
        ALLOWED_ITEMS.add("agotmod:oat");
        ALLOWED_ITEMS.add("agotmod:oat_seeds");
        ALLOWED_ITEMS.add("agotmod:opium_poppy_seeds");
        ALLOWED_ITEMS.add("agotmod:cotton");
        ALLOWED_ITEMS.add("agotmod:cotton_seeds");
        ALLOWED_ITEMS.add("agotmod:hemp");
        ALLOWED_ITEMS.add("agotmod:hemp_seeds");

        // Berries
        ALLOWED_ITEMS.add("agotmod:strawberry");
        ALLOWED_ITEMS.add("agotmod:strawberry_seeds");
        ALLOWED_ITEMS.add("agotmod:blackberry");
        ALLOWED_ITEMS.add("agotmod:blackberry_seeds");
        ALLOWED_ITEMS.add("agotmod:blueberry");
        ALLOWED_ITEMS.add("agotmod:blueberry_seeds");
        ALLOWED_ITEMS.add("agotmod:mulberry");
        ALLOWED_ITEMS.add("agotmod:mulberry_seeds");
        ALLOWED_ITEMS.add("agotmod:raspberry");
        ALLOWED_ITEMS.add("agotmod:raspberry_seeds");
        ALLOWED_ITEMS.add("agotmod:smokeberry");
        ALLOWED_ITEMS.add("agotmod:smokeberry_seeds");

        // Other
        ALLOWED_ITEMS.add("agotmod:garlic");
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

    public FarmerBarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // EXTENSIVE DEBUG LOGGING - log every check for farmers
        if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_FARMER) && peasant.tickCount % 50 == 0) {
            long dayTime = peasant.level().getDayTime() % 24000;
            long currentDay = peasant.level().getDayTime() / 24000;

            System.out.println("=== BARREL DROP OFF DEBUG [" + peasant.getDisplayName().getString() + "] ===");
            System.out.println("JobType: " + peasant.getJobType());
            System.out.println("JobBlockPos: " + peasant.getJobBlockPos());
            System.out.println("Sleeping: " + peasant.isSleeping());
            System.out.println("Eating: " + peasant.getHungerSystem().isEating());
            System.out.println("DayTime: " + dayTime + " (end of work day: " + (dayTime >= 12000 && dayTime <= 13000) + ")");
            System.out.println("CurrentDay: " + currentDay + ", LastDropOffDay: " + lastDropOffDay);
            System.out.println("HasAllowedItems: " + hasAllowedItems());
            if (peasant.getJobBlockPos() != null) {
                BlockState barrelState = peasant.level().getBlockState(peasant.getJobBlockPos());
                System.out.println("BarrelBlock: " + barrelState.getBlock() + " (is farmer barrel: " + barrelState.is(ModBLocks.FARMER_BARREL.get()) + ")");
            }
            System.out.println("===============================================");
        }

        // Only for farmers
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER)) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Not a farmer");
            }
            return false;
        }

        // Must have a job block
        if (peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: No job block position");
            }
            return false;
        }

        // Must not be sleeping or eating
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Sleeping or eating");
            }
            return false;
        }

        // Check if it's end of working day (working day ends at 12000 ticks)
        long dayTime = peasant.level().getDayTime() % 24000;
        boolean isEndOfWorkDay = (dayTime >= 12000 && dayTime <= 13000);

        if (!isEndOfWorkDay) {
            if (!peasant.level().isClientSide && peasant.tickCount % 400 == 0) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Not end of work day (time: " + dayTime + ")");
            }
            return false;
        }

        // Check if we already dropped off today
        long currentDay = peasant.level().getDayTime() / 24000;
        if (lastDropOffDay == currentDay) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Already dropped off today (day " + currentDay + ")");
            }
            return false;
        }

        // Verify job block is still a farmer barrel
        targetBarrel = peasant.getJobBlockPos();
        BlockState barrelState = peasant.level().getBlockState(targetBarrel);
        if (!barrelState.is(ModBLocks.FARMER_BARREL.get())) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Job block is not a farmer barrel: " + barrelState.getBlock());
            }
            return false;
        }

        // Check if we have any allowed items to drop off
        if (!hasAllowedItems()) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: No allowed items to drop off");
                logInventoryContents(); // Log what they actually have
            }
            // DON'T mark as completed - let them try again later if they get items
            return false;
        }

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: *** ACTIVATING DROP OFF *** at time " + dayTime + " on day " + currentDay);
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
        if (!barrelState.is(ModBLocks.FARMER_BARREL.get())) {
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
            System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Goal started - moving to barrel");
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
            System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Goal stopped");
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
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Barrel is not a container!");
            }
            return;
        }

        var peasantInventory = peasant.getInventorySystem().getInventory();
        int totalDropped = 0;

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Performing drop off...");
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
            System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Dropped off " + totalDropped + " items");
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
        System.out.println("=== INVENTORY CONTENTS [" + peasant.getDisplayName().getString() + "] ===");

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
        System.out.println("Sample allowed items: minecraft:wheat, minecraft:wheat_seeds, agotmod:turnip, agotmod:turnip_seeds");
        System.out.println("===============================================");
    }
}