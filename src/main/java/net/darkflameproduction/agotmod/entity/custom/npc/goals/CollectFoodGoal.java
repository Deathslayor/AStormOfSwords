package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class CollectFoodGoal extends Goal {
    private final Peasant_Entity peasant;
    private BlockPos targetChest;
    private int searchAttempts = 0;
    private List<BlockPos> searchedChests = new ArrayList<>();
    private static final int MIN_FOOD_COUNT = 15;

    public CollectFoodGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Only collect food if we need it and we're not sleeping/eating
        if (!peasant.needsFoodCollection() || peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Check if we already have enough food
        if (peasant.getHungerSystem().hasEnoughFood()) {
            peasant.setNeedsFoodCollection(false);
            return false;
        }

        return findNearestChest();
    }

    private boolean findNearestChest() {
        BlockPos peasantPos = peasant.blockPosition();
        BlockPos closestChest = null;
        double closestDistance = Double.MAX_VALUE;

        // Search in expanding radius
        for (int searchRadius = 8; searchRadius <= 64; searchRadius += 8) {
            for (int x = -searchRadius; x <= searchRadius; x++) {
                for (int y = -8; y <= 8; y++) {
                    for (int z = -searchRadius; z <= searchRadius; z++) {
                        BlockPos checkPos = peasantPos.offset(x, y, z);

                        // Skip if we've already searched this chest
                        if (searchedChests.contains(checkPos)) {
                            continue;
                        }

                        // Check if it's within home area
                        if (!peasant.isWithinHomeArea(checkPos)) {
                            continue;
                        }

                        BlockState state = peasant.level().getBlockState(checkPos);
                        if (state.getBlock() instanceof ChestBlock) {
                            double distance = peasantPos.distSqr(checkPos);
                            if (distance < closestDistance) {
                                closestDistance = distance;
                                closestChest = checkPos;
                            }
                        }
                    }
                }
            }

            if (closestChest != null) {
                targetChest = closestChest;
                return true;
            }
        }

        // No more chests found, stop searching
        if (searchAttempts > 2) {
            peasant.setNeedsFoodCollection(false);
            searchedChests.clear();
            searchAttempts = 0;
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetChest == null || peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Check if we have enough food now
        if (peasant.getHungerSystem().hasEnoughFood()) {
            peasant.setNeedsFoodCollection(false);
            return false;
        }

        // Check if chest still exists
        BlockState chestState = peasant.level().getBlockState(targetChest);
        if (!(chestState.getBlock() instanceof ChestBlock)) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        if (targetChest != null) {
            peasant.getNavigation().moveTo(targetChest.getX() + 0.5, targetChest.getY(), targetChest.getZ() + 0.5, 0.7D);
        }
    }

    @Override
    public void stop() {
        if (targetChest != null) {
            searchedChests.add(targetChest);
        }
        targetChest = null;
        peasant.getNavigation().stop();

        // If we have enough food now, clear the flag
        if (peasant.getHungerSystem().hasEnoughFood()) {
            peasant.setNeedsFoodCollection(false);
            searchedChests.clear();
            searchAttempts = 0;
        }
    }

    @Override
    public void tick() {
        if (targetChest != null) {
            peasant.getLookControl().setLookAt(targetChest.getX(), targetChest.getY(), targetChest.getZ());

            // If we're close to the chest, try to take items
            if (peasant.distanceToSqr(targetChest.getX(), targetChest.getY(), targetChest.getZ()) <= 4.0D) {
                tryTakeItemsFromChest();
                return;
            }

            // Keep moving to chest if not there yet
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(targetChest.getX() + 0.5, targetChest.getY(), targetChest.getZ() + 0.5, 0.7D);
            }
        }
    }

    private void tryTakeItemsFromChest() {
        var blockEntity = peasant.level().getBlockEntity(targetChest);
        if (blockEntity instanceof ChestBlockEntity chestEntity) {
            // Check how many meat items we need
            int currentMeat = peasant.getHungerSystem().countMeatInInventory();
            int neededMeat = MIN_FOOD_COUNT - currentMeat;

            if (neededMeat <= 0) {
                peasant.setNeedsFoodCollection(false);
                return;
            }

            // Try to take meat items from the chest
            boolean foundMeat = false;
            for (int i = 0; i < chestEntity.getContainerSize() && neededMeat > 0; i++) {
                ItemStack chestStack = chestEntity.getItem(i);
                if (!chestStack.isEmpty() && isFood(chestStack)) {
                    foundMeat = true;

                    // Calculate how many to take
                    int toTake = Math.min(neededMeat, chestStack.getCount());

                    // Check if peasant has space
                    if (!peasant.getInventorySystem().hasSpace()) {
                        break;
                    }

                    // Create a copy to add to peasant inventory
                    ItemStack toAdd = chestStack.copy();
                    toAdd.setCount(toTake);

                    // Try to add to peasant inventory
                    if (peasant.getInventorySystem().addItem(toAdd)) {
                        // Successfully added, remove from chest
                        chestStack.shrink(toTake);
                        if (chestStack.isEmpty()) {
                            chestEntity.setItem(i, ItemStack.EMPTY);
                        } else {
                            chestEntity.setItem(i, chestStack);
                        }
                        neededMeat -= toTake;
                    }
                }
            }

            // Mark chest as searched and set to find next chest if we still need more food
            searchedChests.add(targetChest);
            targetChest = null;

            if (!foundMeat) {
                searchAttempts++;
            }

            // Check if we have enough food now
            if (peasant.getHungerSystem().hasEnoughFood()) {
                peasant.setNeedsFoodCollection(false);
                searchedChests.clear();
                searchAttempts = 0;
            }
        }
    }

    private boolean isFood(ItemStack stack) {
        return stack.is(net.minecraft.tags.ItemTags.create(
                net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "meat")));
    }
}