package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
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
    private static final int MAX_SEARCH_ATTEMPTS = 3; // New constant for auto-skip

    public GrocerCollectionGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    // Method to reset daily state - called by sleep system when peasant wakes up
    public void resetDailyStateAfterSleep() {
        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " - Resetting grocer collection state after sleep (was: hasCollected=" + hasCollectedToday +
                ", barrels=" + barrelsCollectedToday + ")");

        hasCollectedToday = false;
        searchedBarrels.clear();
        searchAttempts = 0;
        barrelsCollectedToday = 0;
        targetFarmerBarrel = null;

        // Update the last collection day to current day
        lastCollectionDay = peasant.level().getDayTime() / 24000;

        System.out.println("DEBUG: " + peasant.getDisplayName().getString() + " - Post-sleep reset completed");
    }

    private void checkAndResetForNewDay() {
        long currentDay = peasant.level().getDayTime() / 24000;
        if (currentDay > lastCollectionDay) {
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                    " - New day detected: " + lastCollectionDay + " -> " + currentDay +
                    " (resetting: hasCollected=" + hasCollectedToday + ", barrels=" + barrelsCollectedToday + ")");
            hasCollectedToday = false;
            lastCollectionDay = currentDay;
            searchedBarrels.clear();
            searchAttempts = 0;
            barrelsCollectedToday = 0;
            targetFarmerBarrel = null; // Also clear any current target
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() + " - Daily reset completed for day " + currentDay);
        }
    }

    @Override
    public boolean canUse() {
        // FIXED: Check for new day FIRST, before any other logic
        checkAndResetForNewDay();

        // Must be a grocer with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_GROCER) ||
                peasant.getJobBlockPos() == null) {
            return false;
        }

        // Must not be sleeping, eating, or collecting food
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        // Reset collection status when peasant sleeps
        if (peasant.getSleepSystem().isSleeping()) {
            hasCollectedToday = false;
            searchedBarrels.clear();
            searchAttempts = 0;
            barrelsCollectedToday = 0;
        }

        // Only collect during work hours (not sleep time)
        if (peasant.shouldSleep()) {
            return false;
        }

        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " canUse() check - hasCollected=" + hasCollectedToday +
                ", barrels=" + barrelsCollectedToday + "/" + MAX_BARRELS_PER_DAY);

        // CRITICAL: Stop if already collected today OR hit barrel limit
        if (hasCollectedToday || barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            return false;
        }

        // Only start collection if at job block and haven't collected today
        BlockPos jobBlock = peasant.getJobBlockPos();
        double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());

        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " canUse() - distance to job block: " + Math.sqrt(distanceToJobBlock) +
                " (threshold: 4.0)");

        if (distanceToJobBlock <= 16.0D) {
            boolean foundBarrel = findNearestFarmerBarrel();
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                    " canUse() - found barrel: " + foundBarrel);
            return foundBarrel;
        }

        return false;
    }

    private boolean findNearestFarmerBarrel() {
        BlockPos peasantPos = peasant.blockPosition();
        BlockPos closestBarrel = null;
        double closestDistance = Double.MAX_VALUE;

        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " searching for farmer barrels. Already collected from " + barrelsCollectedToday + "/" + MAX_BARRELS_PER_DAY +
                " (Search attempts: " + searchAttempts + "/" + MAX_SEARCH_ATTEMPTS + ")");

        // Search in expanding radius around grocer
        for (int searchRadius = 8; searchRadius <= 96; searchRadius += 8) {
            for (int x = -searchRadius; x <= searchRadius; x++) {
                for (int y = -16; y <= 16; y++) {
                    for (int z = -searchRadius; z <= searchRadius; z++) {
                        BlockPos checkPos = peasantPos.offset(x, y, z);

                        // Skip if we've already searched this barrel
                        if (searchedBarrels.contains(checkPos)) {
                            continue;
                        }

                        // Allow grocers to go beyond home area for collection
                        // Only check if it's within a reasonable distance from job block
                        BlockPos jobBlock = peasant.getJobBlockPos();
                        if (jobBlock != null) {
                            double distanceFromJobBlock = jobBlock.distSqr(checkPos);
                            // Allow up to 128 blocks from job block (much larger than home area)
                            if (distanceFromJobBlock > 128 * 128) {
                                continue;
                            }
                        }

                        BlockState state = peasant.level().getBlockState(checkPos);
                        if (state.getBlock() == ModBLocks.FARMER_BARREL.get()) {
                            // Check if barrel has items
                            if (barrelHasItems(checkPos)) {
                                double distance = peasantPos.distSqr(checkPos);
                                if (distance < closestDistance) {
                                    closestDistance = distance;
                                    closestBarrel = checkPos;
                                }
                            } else {
                                // Mark empty barrels as searched
                                searchedBarrels.add(checkPos);
                            }
                        }
                    }
                }
            }

            if (closestBarrel != null) {
                targetFarmerBarrel = closestBarrel;
                System.out.println("DEBUG: Found farmer barrel at " + closestBarrel +
                        " (distance: " + Math.sqrt(closestDistance) + " blocks from grocer, " +
                        Math.sqrt(peasant.getJobBlockPos().distSqr(closestBarrel)) + " blocks from job block)");
                return true;
            }
        }

        // No more barrels found with items
        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " no more farmer barrels found. Collected from " + barrelsCollectedToday + " barrels total");

        searchAttempts++;

        // AUTO-SKIP: If we've searched multiple times without finding barrels, complete collection
        if (searchAttempts >= MAX_SEARCH_ATTEMPTS || barrelsCollectedToday > 0) {
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                    " completing collection - searched " + searchAttempts + " times, found " + barrelsCollectedToday + " barrels");

            // Send chat message about completion
            if (!peasant.level().isClientSide) {
                if (barrelsCollectedToday == 0) {
                    peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                            Component.literal("<" + peasant.getDisplayName().getString() + "> " +
                                    "No farmer barrels with items found today. The farmers must be storing elsewhere."),
                            false
                    );
                } else {
                    peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                            Component.literal("<" + peasant.getDisplayName().getString() + "> " +
                                    "That's all the barrels I could find - " + barrelsCollectedToday + " total. No more available."),
                            false
                    );
                }
            }

            // FIXED: Mark as collected today to trigger return to job block logic
            hasCollectedToday = true;
            searchedBarrels.clear();
            searchAttempts = 0;

            // This will cause canContinueToUse() to return true until we reach job block
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() + " - Collection marked complete, will return to job block");
        }

        return false;
    }

    private void returnToJobBlock() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock != null) {
            double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());

            System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                    " returning to job block after collection (distance: " + Math.sqrt(distanceToJobBlock) + ")");

            // Only start return navigation if not already close to job block
            if (distanceToJobBlock > 4.0D) {
                // Force high priority navigation back to job block
                peasant.getNavigation().stop(); // Clear any existing navigation
                boolean success = peasant.getNavigation().moveTo(
                        jobBlock.getX() + 0.5,
                        jobBlock.getY(),
                        jobBlock.getZ() + 0.5,
                        0.8D // Higher speed for return trip
                );

                System.out.println("DEBUG: Navigation to job block started: " + success);

                // Send chat message about returning (only once)
                if (!peasant.level().isClientSide && success) {
                    peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                            Component.literal("<" + peasant.getDisplayName().getString() + "> " +
                                    "Time to return to my post. Collection duty complete!"),
                            false
                    );
                }
            } else {
                System.out.println("DEBUG: " + peasant.getDisplayName().getString() + " already at job block");
            }
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

    @Override
    public boolean canContinueToUse() {
        // ADDITIONAL DAILY RESET CHECK: Check here too in case goal is already running
        checkAndResetForNewDay();

        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        // Stop during sleep time
        if (peasant.shouldSleep()) {
            return false;
        }

        // If we've hit the daily limit or already collected today, check if we need to return to job block
        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY || hasCollectedToday) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            if (jobBlock != null) {
                double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());

                // If we're far from job block, continue the goal to navigate back
                if (distanceToJobBlock > 4.0D) {
                    System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                            " collection complete but still returning to job block (distance: " +
                            Math.sqrt(distanceToJobBlock) + ")");
                    return true; // Continue goal to finish returning
                } else {
                    System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                            " reached job block, collection goal complete");
                    return false; // Close enough to job block, end the goal
                }
            }
            return false;
        }

        // Continue collecting if under the limit and haven't finished today
        return barrelsCollectedToday < MAX_BARRELS_PER_DAY && !hasCollectedToday;
    }

    @Override
    public void start() {
        if (targetFarmerBarrel != null) {
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                    " starting collection from farmer barrel at " + targetFarmerBarrel);

            // Send chat message when starting collection (only once per day)
            if (!peasant.level().isClientSide && barrelsCollectedToday == 0) {
                peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                        Component.literal("<" + peasant.getDisplayName().getString() + "> " +
                                "Time to collect from the farmer barrels! Let me see what they've stored..."),
                        false
                );
            }

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
        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " stopping collection goal. Barrels collected: " + barrelsCollectedToday + "/" + MAX_BARRELS_PER_DAY);

        if (targetFarmerBarrel != null) {
            searchedBarrels.add(targetFarmerBarrel);
            targetFarmerBarrel = null;
        }

        peasant.getNavigation().stop();

        // Send chat message when collection ends
        if (!peasant.level().isClientSide && barrelsCollectedToday > 0) {
            String message;
            if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
                message = "Collection complete! Visited all " + barrelsCollectedToday + " barrels. Heading back to my post.";
            } else if (barrelsCollectedToday > 0) {
                message = "Found " + barrelsCollectedToday + " barrels with supplies. No more to collect today.";
            } else {
                message = "Couldn't find any farmer barrels with items today. Back to my post.";
            }

            peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                    Component.literal("<" + peasant.getDisplayName().getString() + "> " + message),
                    false
            );
        }

        // Check if we're at job block and send arrival message
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock != null) {
            double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());
            if (distanceToJobBlock <= 4.0D) {
                // Already at job block, send arrival message
                if (!peasant.level().isClientSide && barrelsCollectedToday > 0) {
                    peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                            Component.literal("<" + peasant.getDisplayName().getString() + "> " +
                                    "Back at my post. Ready for business!"),
                            false
                    );
                }
            } else {
                System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                        " stopped collection goal but still " + Math.sqrt(distanceToJobBlock) +
                        " blocks from job block");
            }
        }
    }

    @Override
    public void tick() {
        // ADDITIONAL DAILY RESET CHECK: Also check for new day in tick() to ensure it doesn't get missed
        checkAndResetForNewDay();

        // If collection is complete but we're not at job block, navigate back
        if (hasCollectedToday && targetFarmerBarrel == null) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            if (jobBlock != null) {
                double distanceToJobBlock = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());

                if (distanceToJobBlock > 4.0D) {
                    // Keep navigating back to job block
                    if (!peasant.getNavigation().isInProgress()) {
                        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                                " restarting navigation to job block (distance: " +
                                Math.sqrt(distanceToJobBlock) + ")");
                        peasant.getNavigation().moveTo(
                                jobBlock.getX() + 0.5,
                                jobBlock.getY(),
                                jobBlock.getZ() + 0.5,
                                0.8D
                        );
                    }
                    return; // Don't do collection logic while returning
                }
            }
        }

        if (targetFarmerBarrel != null) {
            peasant.getLookControl().setLookAt(
                    targetFarmerBarrel.getX(),
                    targetFarmerBarrel.getY(),
                    targetFarmerBarrel.getZ()
            );

            // If we're close to the barrel, try to collect items
            if (peasant.distanceToSqr(
                    targetFarmerBarrel.getX(),
                    targetFarmerBarrel.getY(),
                    targetFarmerBarrel.getZ()) <= 4.0D) {
                tryCollectFromBarrel();
                return;
            }

            // Keep moving to barrel if not there yet
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        targetFarmerBarrel.getX() + 0.5,
                        targetFarmerBarrel.getY(),
                        targetFarmerBarrel.getZ() + 0.5,
                        0.7D
                );
            }
        } else {
            // No current target, try to find another barrel
            if (barrelsCollectedToday < MAX_BARRELS_PER_DAY && !hasCollectedToday) {
                findNearestFarmerBarrel();
            }
        }
    }

    private void tryCollectFromBarrel() {
        BlockEntity blockEntity = peasant.level().getBlockEntity(targetFarmerBarrel);
        if (!(blockEntity instanceof net.minecraft.world.Container container)) {
            System.out.println("DEBUG: Barrel at " + targetFarmerBarrel + " is not a container");
            // Clear target and try to find next barrel
            searchedBarrels.add(targetFarmerBarrel);
            targetFarmerBarrel = null;
            return;
        }

        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " collecting from farmer barrel " + (barrelsCollectedToday + 1) + "/" + MAX_BARRELS_PER_DAY +
                " at " + targetFarmerBarrel);

        boolean foundItems = false;
        int itemsCollected = 0;

        // Collect all items from the barrel
        for (int slot = 0; slot < container.getContainerSize(); slot++) {
            ItemStack stack = container.getItem(slot);

            if (!stack.isEmpty()) {
                foundItems = true;

                // Add to grocer's digital inventory
                peasant.getGrocerSystem().addToDigitalInventory(stack);
                itemsCollected += stack.getCount();

                // Remove from barrel
                container.setItem(slot, ItemStack.EMPTY);
            }
        }

        // Mark barrel as changed
        if (blockEntity instanceof net.minecraft.world.level.block.entity.BaseContainerBlockEntity baseContainer) {
            baseContainer.setChanged();
        } else {
            blockEntity.setChanged();
        }

        System.out.println("DEBUG: Collected " + itemsCollected + " items from farmer barrel");

        // Mark this barrel as searched and increment counter
        searchedBarrels.add(targetFarmerBarrel);
        targetFarmerBarrel = null; // Clear current target
        barrelsCollectedToday++;

        // Send IN-GAME CHAT MESSAGE about collection progress
        if (!peasant.level().isClientSide) {
            String message;
            if (foundItems && itemsCollected > 0) {
                message = "Barrel " + barrelsCollectedToday + " of " + MAX_BARRELS_PER_DAY + " - Found " + itemsCollected + " items! Good harvest.";
            } else {
                message = "Barrel " + barrelsCollectedToday + " of " + MAX_BARRELS_PER_DAY + " - Empty. The farmers must be busy elsewhere.";
            }

            // Send as actual chat message from the NPC
            peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                    Component.literal("<" + peasant.getDisplayName().getString() + "> " + message),
                    false
            );
        }

        if (!foundItems) {
            searchAttempts++;
        }

        // Check if we've reached daily limit
        if (barrelsCollectedToday >= MAX_BARRELS_PER_DAY) {
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                    " reached daily barrel limit (" + MAX_BARRELS_PER_DAY + "), collection complete for today");
            hasCollectedToday = true; // CRITICAL: Mark as collected today
            searchedBarrels.clear();
            searchAttempts = 0;

            // Send completion chat message
            if (!peasant.level().isClientSide) {
                peasant.level().getServer().getPlayerList().broadcastSystemMessage(
                        Component.literal("<" + peasant.getDisplayName().getString() + "> " +
                                "That's all " + MAX_BARRELS_PER_DAY + " barrels for today! Heading back to my post."),
                        false
                );
            }
            // Goal will end naturally due to canContinueToUse() returning false
        } else {
            System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                    " collected from " + barrelsCollectedToday + "/" + MAX_BARRELS_PER_DAY +
                    " barrels, will search for next barrel");
            // Target is already cleared, tick() will find the next barrel
        }
    }

    // PERSISTENCE METHODS - Save/Load state to prevent loss on game reload
    public void saveCollectionData(CompoundTag compound) {
        compound.putBoolean("HasCollectedToday", hasCollectedToday);
        compound.putLong("LastCollectionDay", lastCollectionDay);
        compound.putInt("BarrelsCollectedToday", barrelsCollectedToday);
        compound.putInt("SearchAttempts", searchAttempts);

        // Save searched barrels list
        ListTag searchedBarrelsList = new ListTag();
        for (BlockPos pos : searchedBarrels) {
            CompoundTag barrelTag = new CompoundTag();
            barrelTag.putInt("X", pos.getX());
            barrelTag.putInt("Y", pos.getY());
            barrelTag.putInt("Z", pos.getZ());
            searchedBarrelsList.add(barrelTag);
        }
        compound.put("SearchedBarrels", searchedBarrelsList);

        // Save target barrel if exists
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

        // Load searched barrels list
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

        // Load target barrel if exists
        if (compound.contains("TargetFarmerBarrel")) {
            CompoundTag targetTag = compound.getCompound("TargetFarmerBarrel");
            targetFarmerBarrel = new BlockPos(
                    targetTag.getInt("X"),
                    targetTag.getInt("Y"),
                    targetTag.getInt("Z")
            );
        }

        System.out.println("DEBUG: " + peasant.getDisplayName().getString() +
                " - Loaded collection data: day=" + lastCollectionDay +
                ", collected=" + hasCollectedToday +
                ", barrels=" + barrelsCollectedToday);
    }
}