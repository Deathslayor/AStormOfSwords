package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class BarrelDropOffGoal extends Goal {
    private final Peasant_Entity peasant;
    private BlockPos targetBarrel;
    private int searchAttempts = 0;
    private List<BlockPos> searchedBarrels = new ArrayList<>();
    private List<BlockPos> availableBarrels = new ArrayList<>();
    private static final int MEAT_TO_KEEP = 20;
    private boolean hasScannedToday = false;

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

    public BarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Must be a farmer
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER)) {
            return false;
        }

        // Must not be sleeping or eating
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Check if it's time to drop off (12000 daytime) OR we have unfinished barrel business
        long dayTime = peasant.level().getDayTime() % 24000;
        boolean isBarrelTime = (dayTime >= 12000 && dayTime <= 13000); // Give 1000 ticks window

        // Reset daily flag at midnight
        if (dayTime == 0) {
            hasScannedToday = false;
            searchedBarrels.clear();
            searchAttempts = 0;
            availableBarrels.clear();
        }

        // If not barrel time and we've already handled today, don't activate
        if (!isBarrelTime && hasScannedToday && !hasExcessItems()) {
            return false;
        }

        // Always check for excess items during barrel time window
        if (!hasExcessItems() && !isBarrelTime) {
            return false;
        }

        // If we have excess items during barrel time, always try to handle it
        if (isBarrelTime && hasExcessItems()) {
            // Scan more aggressively - every time we activate during barrel window
            scanForBarrels();
            return findNextBarrel();
        }

        // If we still have excess items after barrel time, keep trying
        if (hasExcessItems() && !hasScannedToday) {
            scanForBarrels();
            hasScannedToday = true;
            return findNextBarrel();
        }

        return false;
    }

    private boolean hasExcessItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        int meatCount = 0;
        int seedStackCount = 0;
        int totalItems = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                totalItems += stack.getCount();

                if (stack.is(ItemTags.create(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "meat")))) {
                    meatCount += stack.getCount();
                } else if (isSeed(stack)) {
                    seedStackCount++;
                }
            }
        }

        // Calculate what should be kept
        int allowedMeat = Math.min(meatCount, MEAT_TO_KEEP);
        int allowedSeedStacks = Math.min(seedStackCount, 2);
        int allowedSeedItems = 0;

        // Count items in the allowed seed stacks
        int seedStacksFound = 0;
        for (int i = 0; i < inventory.getContainerSize() && seedStacksFound < allowedSeedStacks; i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isSeed(stack)) {
                allowedSeedItems += stack.getCount();
                seedStacksFound++;
            }
        }

        int allowedTotalItems = allowedMeat + allowedSeedItems;

        // Has excess if total items exceed what we're allowed to keep
        return totalItems > allowedTotalItems;
    }

    private void scanForBarrels() {
        availableBarrels.clear();

        if (peasant.getJobBlockPos() == null) {
            // If no job block, scan around current position
            scanAroundPosition(peasant.blockPosition());
            return;
        }

        // First scan around job block
        scanAroundPosition(peasant.getJobBlockPos());

        // If no barrels found around job block, scan around current position too
        if (availableBarrels.isEmpty()) {
            scanAroundPosition(peasant.blockPosition());
        }

        // Sort barrels by distance (closest first)
        if (!availableBarrels.isEmpty()) {
            BlockPos referencePos = peasant.blockPosition();
            availableBarrels.sort((pos1, pos2) -> {
                double dist1 = referencePos.distSqr(pos1);
                double dist2 = referencePos.distSqr(pos2);
                return Double.compare(dist1, dist2);
            });
        }
    }

    private void scanAroundPosition(BlockPos centerPos) {
        // Search in 96x96x32 area around center position
        for (int x = -48; x <= 48; x++) {
            for (int y = -16; y <= 16; y++) {
                for (int z = -48; z <= 48; z++) {
                    BlockPos checkPos = centerPos.offset(x, y, z);
                    BlockState state = peasant.level().getBlockState(checkPos);

                    if (state.is(ModBLocks.FARMER_BARREL.get())) {
                        // Verify the barrel has an inventory and isn't already in our list
                        BlockEntity blockEntity = peasant.level().getBlockEntity(checkPos);
                        if (blockEntity instanceof Container && !availableBarrels.contains(checkPos)) {
                            availableBarrels.add(checkPos);
                        }
                    }
                }
            }
        }
    }

    private boolean findNextBarrel() {
        // If we have available barrels, find one we haven't tried
        for (BlockPos barrelPos : availableBarrels) {
            if (!searchedBarrels.contains(barrelPos)) {
                targetBarrel = barrelPos;
                return true;
            }
        }

        // If all barrels have been tried, try scanning again if attempts are low
        if (searchAttempts < 5) {
            searchedBarrels.clear(); // Reset searched barrels for another round
            scanForBarrels(); // Scan again

            for (BlockPos barrelPos : availableBarrels) {
                if (!searchedBarrels.contains(barrelPos)) {
                    targetBarrel = barrelPos;
                    return true;
                }
            }
        }

        // If still no barrels and we have excess items, delete them
        if (hasExcessItems()) {
            deleteExcessItems();
            hasScannedToday = true;
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetBarrel == null || peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Always continue if we have excess items - don't give up easily
        if (!hasExcessItems()) {
            hasScannedToday = true;
            return false;
        }

        // Check if barrel still exists
        BlockState barrelState = peasant.level().getBlockState(targetBarrel);
        if (!barrelState.is(ModBLocks.FARMER_BARREL.get())) {
            // Barrel was destroyed, but keep trying with other barrels
            targetBarrel = null;
            return findNextBarrel();
        }

        return true;
    }

    @Override
    public void start() {
        if (targetBarrel != null) {
            peasant.getNavigation().moveTo(targetBarrel.getX() + 0.5, targetBarrel.getY(), targetBarrel.getZ() + 0.5, 0.8D);
        }
    }

    @Override
    public void stop() {
        if (targetBarrel != null) {
            searchedBarrels.add(targetBarrel);
        }
        targetBarrel = null;
        peasant.getNavigation().stop();

        // Only mark as done if we truly don't have excess items
        if (!hasExcessItems()) {
            hasScannedToday = true;
            searchedBarrels.clear();
            searchAttempts = 0;
        } else {
            // Still have excess items, try to find another barrel
            if (!findNextBarrel()) {
                // No more barrels available, but keep the goal active
                searchAttempts++;
            }
        }
    }

    @Override
    public void tick() {
        if (targetBarrel != null) {
            peasant.getLookControl().setLookAt(targetBarrel.getX(), targetBarrel.getY(), targetBarrel.getZ());

            // If we're close to the barrel, try to drop off items
            if (peasant.distanceToSqr(targetBarrel.getX(), targetBarrel.getY(), targetBarrel.getZ()) <= 4.0D) {
                tryDropOffItems();
                return;
            }

            // Keep moving to barrel if not there yet
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(targetBarrel.getX() + 0.5, targetBarrel.getY(), targetBarrel.getZ() + 0.5, 0.8D);
            }
        }
    }

    // Helper method to check if an item is allowed to be dropped off
    private boolean isAllowedItem(ItemStack stack) {
        ResourceLocation itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        return ALLOWED_ITEMS.contains(itemId.toString());
    }

    private void tryDropOffItems() {
        var blockEntity = peasant.level().getBlockEntity(targetBarrel);
        if (blockEntity instanceof Container barrelInventory) {
            var peasantInventory = peasant.getInventorySystem().getInventory();
            boolean droppedSomething = false;

            // First pass: Keep exactly 20 meat items, drop excess meat (only if allowed)
            int meatToKeep = MEAT_TO_KEEP;
            for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
                ItemStack stack = peasantInventory.getItem(i);
                if (!stack.isEmpty() && stack.is(ItemTags.create(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "meat")))) {

                    if (meatToKeep > 0) {
                        int keepAmount = Math.min(meatToKeep, stack.getCount());
                        meatToKeep -= keepAmount;

                        if (stack.getCount() > keepAmount) {
                            // Split stack - keep some, drop excess (only if allowed)
                            ItemStack excess = stack.copy();
                            excess.setCount(stack.getCount() - keepAmount);
                            stack.setCount(keepAmount);

                            // Only drop if item is in allowed list
                            if (isAllowedItem(excess) && addToBarrel(barrelInventory, excess)) {
                                droppedSomething = true;
                            } else {
                                // Either not allowed or barrel full, restore original stack
                                stack.setCount(stack.getCount() + excess.getCount());
                                if (!isAllowedItem(excess)) {
                                    break; // Don't try other barrels for disallowed items
                                }
                            }
                        }
                    } else {
                        // Already have 20 meat, drop all of this stack (only if allowed)
                        if (isAllowedItem(stack) && addToBarrel(barrelInventory, stack.copy())) {
                            peasantInventory.setItem(i, ItemStack.EMPTY);
                            droppedSomething = true;
                        } else if (!isAllowedItem(stack)) {
                            // Item not allowed, keep it (don't delete)
                            continue;
                        } else {
                            // Barrel full
                            break;
                        }
                    }
                }
            }

            // Second pass: Keep exactly 2 stacks of seeds, drop excess seeds (only if allowed)
            int seedStacksToKeep = 2;
            for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
                ItemStack stack = peasantInventory.getItem(i);
                if (!stack.isEmpty() && isSeed(stack)) {

                    if (seedStacksToKeep > 0) {
                        seedStacksToKeep--;
                        // Keep this entire stack
                    } else {
                        // Already have 2 seed stacks, drop this one (only if allowed)
                        if (isAllowedItem(stack) && addToBarrel(barrelInventory, stack.copy())) {
                            peasantInventory.setItem(i, ItemStack.EMPTY);
                            droppedSomething = true;
                        } else if (!isAllowedItem(stack)) {
                            // Item not allowed, keep it (don't delete)
                            continue;
                        } else {
                            // Barrel full
                            break;
                        }
                    }
                }
            }

            // Third pass: Drop all other items (non-meat, non-seed) only if they're allowed
            for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
                ItemStack stack = peasantInventory.getItem(i);
                if (!stack.isEmpty() && !stack.is(ItemTags.create(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "meat")))
                        && !isSeed(stack)) {

                    // Only drop if item is in allowed list
                    if (isAllowedItem(stack) && addToBarrel(barrelInventory, stack.copy())) {
                        peasantInventory.setItem(i, ItemStack.EMPTY);
                        droppedSomething = true;
                    } else if (!isAllowedItem(stack)) {
                        // Item not allowed, keep it (don't delete)
                        continue;
                    } else {
                        // Barrel full
                        break;
                    }
                }
            }

            // Mark barrel as searched and set to find next barrel if we still have excess
            searchedBarrels.add(targetBarrel);
            targetBarrel = null;

            if (!droppedSomething) {
                searchAttempts++;
            }

            // Check if we still have excess items
            if (!hasExcessItems()) {
                searchedBarrels.clear();
                searchAttempts = 0;
            }
        }
    }

    private boolean addToBarrel(Container barrelInventory, ItemStack stackToAdd) {
        // Try to merge with existing stacks first
        for (int i = 0; i < barrelInventory.getContainerSize(); i++) {
            ItemStack existing = barrelInventory.getItem(i);
            if (!existing.isEmpty() && ItemStack.isSameItemSameComponents(existing, stackToAdd)) {
                int maxStackSize = Math.min(existing.getMaxStackSize(), barrelInventory.getMaxStackSize());
                int canAdd = maxStackSize - existing.getCount();
                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, stackToAdd.getCount());
                    existing.grow(toAdd);
                    stackToAdd.shrink(toAdd);
                    if (stackToAdd.isEmpty()) return true;
                }
            }
        }

        // Try to place in empty slots
        for (int i = 0; i < barrelInventory.getContainerSize(); i++) {
            if (barrelInventory.getItem(i).isEmpty()) {
                barrelInventory.setItem(i, stackToAdd.copy());
                return true;
            }
        }

        return false; // Barrel is full
    }

    private void deleteExcessItems() {
        var peasantInventory = peasant.getInventorySystem().getInventory();

        // First pass: Keep exactly 20 meat items, delete excess meat (only if allowed)
        int meatToKeep = MEAT_TO_KEEP;
        for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
            ItemStack stack = peasantInventory.getItem(i);
            if (!stack.isEmpty() && stack.is(ItemTags.create(
                    net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "meat")))) {

                if (meatToKeep > 0) {
                    int keepAmount = Math.min(meatToKeep, stack.getCount());
                    meatToKeep -= keepAmount;

                    if (stack.getCount() > keepAmount) {
                        // Keep some, delete excess (only if it would be allowed to drop)
                        if (isAllowedItem(stack)) {
                            stack.setCount(keepAmount);
                        }
                        // If not allowed, keep the full stack
                    }
                } else {
                    // Already have 20 meat, delete all of this stack (only if allowed)
                    if (isAllowedItem(stack)) {
                        peasantInventory.setItem(i, ItemStack.EMPTY);
                    }
                    // If not allowed, keep the stack
                }
            }
        }

        // Second pass: Keep exactly 2 stacks of seeds, delete excess seeds (only if allowed)
        int seedStacksToKeep = 2;
        for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
            ItemStack stack = peasantInventory.getItem(i);
            if (!stack.isEmpty() && isSeed(stack)) {

                if (seedStacksToKeep > 0) {
                    seedStacksToKeep--;
                    // Keep this entire stack
                } else {
                    // Already have 2 seed stacks, delete this one (only if allowed)
                    if (isAllowedItem(stack)) {
                        peasantInventory.setItem(i, ItemStack.EMPTY);
                    }
                    // If not allowed, keep the stack
                }
            }
        }

        // Third pass: Delete all other items (non-meat, non-seed) only if they're allowed
        for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
            ItemStack stack = peasantInventory.getItem(i);
            if (!stack.isEmpty() && !stack.is(ItemTags.create(
                    net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "meat")))
                    && !isSeed(stack)) {

                // Only delete if item is in allowed list
                if (isAllowedItem(stack)) {
                    peasantInventory.setItem(i, ItemStack.EMPTY);
                }
                // If not allowed, keep the item
            }
        }
    }

    private boolean isSeed(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock;
    }
}