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
import net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class GrocerCollectionGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private BlockPos targetBarrel;
    private int searchAttempts = 0;
    private List<BlockPos> searchedBarrels = new ArrayList<>();
    private List<BlockPos> availableBarrels = new ArrayList<>();
    private int barrelsCollectedToday = 0;
    private boolean hasScannedToday = false;
    private long lastDayTime = -1; // Track actual game time to detect resets

    // Constants
    private static final int COLLECTION_TIME = 4000;
    private static final int MAX_BARRELS_PER_DAY = 10;
    private static final int SCAN_RADIUS = 48; // 96x96 area (48 blocks in each direction)

    public GrocerCollectionGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void resetDailyStateAfterSleep() {
        hasScannedToday = false;
        barrelsCollectedToday = 0;
        searchedBarrels.clear();
        searchAttempts = 0;
        availableBarrels.clear();
        targetBarrel = null;
        lastDayTime = peasant.level().getDayTime();
    }

    private void checkAndResetForNewDay() {
        long currentDayTime = peasant.level().getDayTime();
        long currentDay = currentDayTime / 24000;
        long lastDay = lastDayTime / 24000;

        // Reset if:
        // 1. It's a new day (day number increased)
        // 2. Time went backwards (server restart/rollback)
        // 3. We haven't initialized lastDayTime yet
        boolean shouldReset = (lastDayTime == -1) ||
                (currentDay > lastDay) ||
                (currentDayTime < lastDayTime - 1000); // Allow for small time fluctuations

        if (shouldReset) {
            resetDailyStateAfterSleep();
        }

        lastDayTime = currentDayTime;
    }

    @Override
    public boolean canUse() {
        // Must be a grocer with job block
        if (!peasant.getJobType().equals(JobSystem.JOB_GROCER) || peasant.getJobBlockPos() == null) {
            return false;
        }

        // Don't interrupt other important activities
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() || peasant.needsFoodCollection() || peasant.shouldSleep()) {
            return false;
        }

        // Check for day changes - this handles both midnight resets and time rollbacks
        checkAndResetForNewDay();

        // Check collection time window
        long dayTime = peasant.level().getDayTime() % 24000;
        boolean isCollectionTime = (dayTime >= COLLECTION_TIME && dayTime <= COLLECTION_TIME + 1000); // Give 1000 ticks window

        // If not collection time and we've already handled today, don't activate
        if (!isCollectionTime && hasScannedToday && barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            return false;
        }

        // Check if grocer system allows collection
        GrocerSystem.GrocerState grocerState = peasant.getGrocerSystem().getCurrentState();
        if (grocerState == GrocerSystem.GrocerState.COLLECTION_COMPLETE || peasant.getGrocerSystem().hasCollectedToday()) {
            return false;
        }

        // If we haven't collected enough barrels and it's collection time, always try
        if (isCollectionTime && barrelsCollectedToday < MAX_BARRELS_PER_DAY) {
            scanForBarrels();
            return findNextBarrel();
        }

        // If we still need to collect after collection time, keep trying
        if (barrelsCollectedToday < MAX_BARRELS_PER_DAY && !hasScannedToday) {
            scanForBarrels();
            hasScannedToday = true;
            return findNextBarrel();
        }

        return false;
    }

    private void scanForBarrels() {
        availableBarrels.clear();

        BlockPos centerPos = peasant.getJobBlockPos() != null ? peasant.getJobBlockPos() : peasant.blockPosition();

        // Search in 96x96x32 area around center position
        for (int x = -SCAN_RADIUS; x <= SCAN_RADIUS; x++) {
            for (int y = -16; y <= 16; y++) {
                for (int z = -SCAN_RADIUS; z <= SCAN_RADIUS; z++) {
                    BlockPos checkPos = centerPos.offset(x, y, z);
                    BlockState state = peasant.level().getBlockState(checkPos);

                    if (state.is(ModBLocks.FARMER_BARREL.get())) {
                        // Check if barrel has items and isn't already in our list
                        if (barrelHasItems(checkPos) && !availableBarrels.contains(checkPos)) {
                            availableBarrels.add(checkPos);
                        }
                    }
                }
            }
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

    private boolean findNextBarrel() {
        // If we've collected enough barrels, we're done
        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            hasScannedToday = true;
            return false;
        }

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

        // No more barrels available, mark collection as complete
        hasScannedToday = true;
        peasant.getGrocerSystem().markCollectionComplete();
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        // Check for day changes
        checkAndResetForNewDay();

        if (targetBarrel == null || peasant.isSleeping() || peasant.getHungerSystem().isEating() || peasant.shouldSleep()) {
            return false;
        }

        // Check if grocer system says we're done
        if (peasant.getGrocerSystem().hasCollectedToday() || peasant.getGrocerSystem().getCurrentState() == GrocerSystem.GrocerState.COLLECTION_COMPLETE) {
            return false;
        }

        // Continue if we haven't collected enough barrels
        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            return false;
        }

        // Check if barrel still exists and has items
        BlockState barrelState = peasant.level().getBlockState(targetBarrel);
        if (!barrelState.is(ModBLocks.FARMER_BARREL.get()) || !barrelHasItems(targetBarrel)) {
            // Barrel was destroyed or emptied, but keep trying with other barrels
            searchedBarrels.add(targetBarrel);
            targetBarrel = null;
            return findNextBarrel();
        }

        return true;
    }

    @Override
    public void start() {
        if (targetBarrel != null) {
            peasant.getNavigation().moveTo(targetBarrel.getX() + 0.5, targetBarrel.getY(), targetBarrel.getZ() + 0.5, 0.7D);
        }
    }

    @Override
    public void stop() {
        if (targetBarrel != null) {
            searchedBarrels.add(targetBarrel);
        }
        targetBarrel = null;
        peasant.getNavigation().stop();

        // Only mark as complete if we've collected enough or exhausted attempts
        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY || hasScannedToday) {
            peasant.getGrocerSystem().markCollectionComplete();
            searchedBarrels.clear();
            searchAttempts = 0;
        } else {
            // Still need to collect more, try to find another barrel
            if (!findNextBarrel()) {
                searchAttempts++;
            }
        }
    }

    @Override
    public void tick() {
        if (targetBarrel != null) {
            peasant.getLookControl().setLookAt(targetBarrel.getX(), targetBarrel.getY(), targetBarrel.getZ());

            // If we're close to the barrel, try to collect from it
            double distance = peasant.distanceToSqr(targetBarrel.getX(), targetBarrel.getY(), targetBarrel.getZ());
            if (distance <= 4.0D) {
                tryCollectFromBarrel();
                return;
            }

            // Keep moving to barrel if not there yet
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        targetBarrel.getX() + 0.5,
                        targetBarrel.getY(),
                        targetBarrel.getZ() + 0.5,
                        0.7D
                );
            }
        }
    }

    private void tryCollectFromBarrel() {
        BlockEntity blockEntity = peasant.level().getBlockEntity(targetBarrel);
        if (!(blockEntity instanceof net.minecraft.world.Container container)) {
            searchedBarrels.add(targetBarrel);
            targetBarrel = null;
            return;
        }

        boolean foundItems = false;
        for (int slot = 0; slot < container.getContainerSize(); slot++) {
            ItemStack stack = container.getItem(slot);
            if (!stack.isEmpty()) {
                foundItems = true;
                // Use grocer system to add items (maintains consistency)
                peasant.getGrocerSystem().addToDigitalInventory(stack);
                container.setItem(slot, ItemStack.EMPTY);
            }
        }

        // Mark the container as changed
        if (blockEntity instanceof net.minecraft.world.level.block.entity.BaseContainerBlockEntity baseContainer) {
            baseContainer.setChanged();
        } else {
            blockEntity.setChanged();
        }

        // Count this barrel as collected and mark it as searched
        barrelsCollectedToday++;
        searchedBarrels.add(targetBarrel);
        targetBarrel = null;

        // Check if we've reached the limit
        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            hasScannedToday = true;
            peasant.getGrocerSystem().markCollectionComplete();
        }
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

    // Getters for debug info
    public int getBarrelsCollectedToday() {
        return barrelsCollectedToday;
    }

    public int getSearchAttempts() {
        return searchAttempts;
    }

    // Save/Load methods
    public void saveCollectionData(CompoundTag compound) {
        compound.putBoolean("HasScannedToday", hasScannedToday);
        compound.putInt("BarrelsCollectedToday", barrelsCollectedToday);
        compound.putInt("SearchAttempts", searchAttempts);
        compound.putLong("LastDayTime", lastDayTime);

        ListTag availableBarrelsList = new ListTag();
        for (BlockPos pos : availableBarrels) {
            CompoundTag barrelTag = new CompoundTag();
            barrelTag.putInt("X", pos.getX());
            barrelTag.putInt("Y", pos.getY());
            barrelTag.putInt("Z", pos.getZ());
            availableBarrelsList.add(barrelTag);
        }
        compound.put("AvailableBarrels", availableBarrelsList);

        ListTag searchedBarrelsList = new ListTag();
        for (BlockPos pos : searchedBarrels) {
            CompoundTag barrelTag = new CompoundTag();
            barrelTag.putInt("X", pos.getX());
            barrelTag.putInt("Y", pos.getY());
            barrelTag.putInt("Z", pos.getZ());
            searchedBarrelsList.add(barrelTag);
        }
        compound.put("SearchedBarrels", searchedBarrelsList);

        if (targetBarrel != null) {
            CompoundTag targetTag = new CompoundTag();
            targetTag.putInt("X", targetBarrel.getX());
            targetTag.putInt("Y", targetBarrel.getY());
            targetTag.putInt("Z", targetBarrel.getZ());
            compound.put("CurrentTargetBarrel", targetTag);
        }
    }

    public void loadCollectionData(CompoundTag compound) {
        hasScannedToday = compound.getBoolean("HasScannedToday");
        barrelsCollectedToday = compound.getInt("BarrelsCollectedToday");
        searchAttempts = compound.getInt("SearchAttempts");
        lastDayTime = compound.getLong("LastDayTime");

        availableBarrels.clear();
        if (compound.contains("AvailableBarrels")) {
            ListTag availableBarrelsList = compound.getList("AvailableBarrels", Tag.TAG_COMPOUND);
            for (int i = 0; i < availableBarrelsList.size(); i++) {
                CompoundTag barrelTag = availableBarrelsList.getCompound(i);
                BlockPos pos = new BlockPos(
                        barrelTag.getInt("X"),
                        barrelTag.getInt("Y"),
                        barrelTag.getInt("Z")
                );
                availableBarrels.add(pos);
            }
        }

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

        if (compound.contains("CurrentTargetBarrel")) {
            CompoundTag targetTag = compound.getCompound("CurrentTargetBarrel");
            targetBarrel = new BlockPos(
                    targetTag.getInt("X"),
                    targetTag.getInt("Y"),
                    targetTag.getInt("Z")
            );
        }
    }
}