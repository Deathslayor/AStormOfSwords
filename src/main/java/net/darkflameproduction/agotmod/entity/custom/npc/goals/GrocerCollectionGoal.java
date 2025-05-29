package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class GrocerCollectionGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private BlockPos targetFarmerBarrel;
    private int searchAttempts = 0;
    private List<BlockPos> searchedBarrels = new ArrayList<>();
    private boolean hasCollectedToday = false;
    private long lastCollectionDay = -1;
    private static final int MAX_BARRELS_PER_DAY = 10;
    private int barrelsCollectedToday = 0;
    private static final int MAX_SEARCH_ATTEMPTS = 3;

    public GrocerCollectionGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void resetDailyStateAfterSleep() {
        hasCollectedToday = false;
        searchedBarrels.clear();
        searchAttempts = 0;
        barrelsCollectedToday = 0;
        targetFarmerBarrel = null;
        lastCollectionDay = peasant.level().getDayTime() / 24000;
    }

    private void checkAndResetForNewDay() {
        long currentDay = peasant.level().getDayTime() / 24000;
        if (currentDay > lastCollectionDay) {
            hasCollectedToday = false;
            lastCollectionDay = currentDay;
            searchedBarrels.clear();
            searchAttempts = 0;
            barrelsCollectedToday = 0;
            targetFarmerBarrel = null;
        }
    }

    @Override
    public boolean canUse() {
        checkAndResetForNewDay();

        if (!peasant.getJobType().equals(JobSystem.JOB_GROCER) ||
                peasant.getJobBlockPos() == null) {
            return false;
        }

        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        if (peasant.getSleepSystem().isSleeping()) {
            hasCollectedToday = false;
            searchedBarrels.clear();
            searchAttempts = 0;
            barrelsCollectedToday = 0;
        }

        if (peasant.shouldSleep()) {
            return false;
        }

        if (hasCollectedToday || barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            return false;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());

        if (distanceToJobBlock <= 16.0D) {
            return findNearestFarmerBarrel();
        }

        return false;
    }

    private boolean findNearestFarmerBarrel() {
        BlockPos peasantPos = peasant.blockPosition();
        BlockPos closestBarrel = null;
        double closestDistance = Double.MAX_VALUE;

        for (int searchRadius = 8; searchRadius <= 96; searchRadius += 8) {
            for (int x = -searchRadius; x <= searchRadius; x++) {
                for (int y = -16; y <= 16; y++) {
                    for (int z = -searchRadius; z <= searchRadius; z++) {
                        BlockPos checkPos = peasantPos.offset(x, y, z);

                        if (searchedBarrels.contains(checkPos)) {
                            continue;
                        }

                        BlockPos jobBlock = peasant.getJobBlockPos();
                        if (jobBlock != null) {
                            double distanceFromJobBlock = jobBlock.distSqr(checkPos);
                            if (distanceFromJobBlock > 128 * 128) {
                                continue;
                            }
                        }

                        BlockState state = peasant.level().getBlockState(checkPos);
                        if (state.getBlock() == ModBLocks.FARMER_BARREL.get()) {
                            if (barrelHasItems(checkPos)) {
                                double distance = peasantPos.distSqr(checkPos);
                                if (distance < closestDistance) {
                                    closestDistance = distance;
                                    closestBarrel = checkPos;
                                }
                            } else {
                                searchedBarrels.add(checkPos);
                            }
                        }
                    }
                }
            }

            if (closestBarrel != null) {
                targetFarmerBarrel = closestBarrel;
                return true;
            }
        }

        searchAttempts++;

        if (searchAttempts >= MAX_SEARCH_ATTEMPTS || barrelsCollectedToday > 0) {
            hasCollectedToday = true;
            searchedBarrels.clear();
            searchAttempts = 0;
        }

        return false;
    }

    private boolean barrelHasItems(BlockPos barrelPos) {
        BlockEntity blockEntity = peasant.level().getBlockEntity(barrelPos);
        if (blockEntity instanceof net.minecraft.world.Container container) {
            for (int i = 0; i < container.getContainerSize(); i++) {
                if (!container.getItem(i).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        checkAndResetForNewDay();

        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        if (peasant.shouldSleep()) {
            return false;
        }

        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY || hasCollectedToday) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            if (jobBlock != null) {
                double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());

                if (distanceToJobBlock > 4.0D) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        return barrelsCollectedToday < MAX_BARRELS_PER_DAY && !hasCollectedToday;
    }

    @Override
    public void start() {
        if (targetFarmerBarrel != null) {
            peasant.getNavigation().moveTo(
                    targetFarmerBarrel.getX() + 0.5,
                    targetFarmerBarrel.getY(),
                    targetFarmerBarrel.getZ() + 0.5,
                    0.7D
            );
        }
    }

    @Override
    public void stop() {
        if (targetFarmerBarrel != null) {
            searchedBarrels.add(targetFarmerBarrel);
            targetFarmerBarrel = null;
        }

        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        checkAndResetForNewDay();

        if (hasCollectedToday && targetFarmerBarrel == null) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            if (jobBlock != null) {
                double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());

                if (distanceToJobBlock > 4.0D) {
                    if (!peasant.getNavigation().isInProgress()) {
                        peasant.getNavigation().moveTo(
                                jobBlock.getX() + 0.5,
                                jobBlock.getY(),
                                jobBlock.getZ() + 0.5,
                                0.8D
                        );
                    }
                    return;
                }
            }
        }

        if (targetFarmerBarrel != null) {
            peasant.getLookControl().setLookAt(
                    targetFarmerBarrel.getX(),
                    targetFarmerBarrel.getY(),
                    targetFarmerBarrel.getZ()
            );

            if (peasant.distanceToSqr(
                    targetFarmerBarrel.getX(),
                    targetFarmerBarrel.getY(),
                    targetFarmerBarrel.getZ()) <= 4.0D) {
                tryCollectFromBarrel();
                return;
            }

            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        targetFarmerBarrel.getX() + 0.5,
                        targetFarmerBarrel.getY(),
                        targetFarmerBarrel.getZ() + 0.5,
                        0.7D
                );
            }
        } else {
            if (barrelsCollectedToday < MAX_BARRELS_PER_DAY && !hasCollectedToday) {
                findNearestFarmerBarrel();
            }
        }
    }

    private void tryCollectFromBarrel() {
        BlockEntity blockEntity = peasant.level().getBlockEntity(targetFarmerBarrel);
        if (!(blockEntity instanceof net.minecraft.world.Container container)) {
            searchedBarrels.add(targetFarmerBarrel);
            targetFarmerBarrel = null;
            return;
        }

        boolean foundItems = false;

        for (int slot = 0; slot < container.getContainerSize(); slot++) {
            ItemStack stack = container.getItem(slot);

            if (!stack.isEmpty()) {
                foundItems = true;
                peasant.getGrocerSystem().addToDigitalInventory(stack);
                container.setItem(slot, ItemStack.EMPTY);
            }
        }

        if (blockEntity instanceof net.minecraft.world.level.block.entity.BaseContainerBlockEntity baseContainer) {
            baseContainer.setChanged();
        } else {
            blockEntity.setChanged();
        }

        searchedBarrels.add(targetFarmerBarrel);
        targetFarmerBarrel = null;
        barrelsCollectedToday++;

        if (!foundItems) {
            searchAttempts++;
        }

        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            hasCollectedToday = true;
            searchedBarrels.clear();
            searchAttempts = 0;
        }
    }

    public void saveCollectionData(CompoundTag compound) {
        compound.putBoolean("HasCollectedToday", hasCollectedToday);
        compound.putLong("LastCollectionDay", lastCollectionDay);
        compound.putInt("BarrelsCollectedToday", barrelsCollectedToday);
        compound.putInt("SearchAttempts", searchAttempts);

        ListTag searchedBarrelsList = new ListTag();
        for (BlockPos pos : searchedBarrels) {
            CompoundTag barrelTag = new CompoundTag();
            barrelTag.putInt("X", pos.getX());
            barrelTag.putInt("Y", pos.getY());
            barrelTag.putInt("Z", pos.getZ());
            searchedBarrelsList.add(barrelTag);
        }
        compound.put("SearchedBarrels", searchedBarrelsList);

        if (targetFarmerBarrel != null) {
            CompoundTag targetTag = new CompoundTag();
            targetTag.putInt("X", targetFarmerBarrel.getX());
            targetTag.putInt("Y", targetFarmerBarrel.getY());
            targetTag.putInt("Z", targetFarmerBarrel.getZ());
            compound.put("TargetFarmerBarrel", targetTag);
        }
    }

    public void loadCollectionData(CompoundTag compound) {
        hasCollectedToday = compound.getBoolean("HasCollectedToday");
        lastCollectionDay = compound.getLong("LastCollectionDay");
        barrelsCollectedToday = compound.getInt("BarrelsCollectedToday");
        searchAttempts = compound.getInt("SearchAttempts");

        searchedBarrels.clear();
        if (compound.contains("SearchedBarrels")) {
            ListTag searchedBarrelsList = compound.getList("SearchedBarrels", Tag.TAG_COMPOUND);
            for (int i = 0; i < searchedBarrelsList.size(); i++) {
                CompoundTag barrelTag = searchedBarrelsList.getCompound(i);
                BlockPos pos = new BlockPos(
                        barrelTag.getInt("X"),
                        barrelTag.getInt("Y"),
                        barrelTag.getInt("Z")
                );
                searchedBarrels.add(pos);
            }
        }

        if (compound.contains("TargetFarmerBarrel")) {
            CompoundTag targetTag = compound.getCompound("TargetFarmerBarrel");
            targetFarmerBarrel = new BlockPos(
                    targetTag.getInt("X"),
                    targetTag.getInt("Y"),
                    targetTag.getInt("Z")
            );
        }
    }
}