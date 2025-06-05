package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.FarmingSystem.FarmState;

import java.util.*;

public class FarmingGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private int workTimer = 0;
    private BlockPos currentWorkTarget = null;

    public FarmingGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Must be a farmer with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER) ||
                peasant.getJobBlockPos() == null) {
            return false;
        }

        // Must not be sleeping, eating, or collecting food
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        // Must have returned to job block after food collection
        if (!peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood()) {
            return false;
        }

        // Don't work during sleep hours or just before barrel drop-off
        if (peasant.shouldSleep()) {
            return false;
        }

        // Stop farming at 11999 to prepare for barrel drop-off at 12000
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            return false;
        }

        // Check if job block still exists (this is also checked in JobSystem but double-check here)
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            var jobBlockState = peasant.level().getBlockState(jobBlockPos);
            if (jobBlockState.getBlock() != net.minecraft.world.level.block.Blocks.COMPOSTER) {
                // Job block destroyed, JobSystem will handle job loss
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        // Stop if should sleep, collecting food, or eating
        if (peasant.shouldSleep() || peasant.isSleeping() ||
                peasant.needsFoodCollection() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Stop farming at 11999 to prepare for barrel drop-off at 12000
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        workTimer = 0;
        currentWorkTarget = null;
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        currentWorkTarget = null;
    }

    @Override
    public void tick() {
        workTimer++;

        FarmState currentState = peasant.getFarmingSystem().getCurrentFarmState();

        switch (currentState) {
            case NEEDS_FARM_SETUP:
                handleFarmSetup();
                break;
            case RETURN_TO_JOB_BLOCK:
                handleReturnToJobBlock();
                break;
            case CONVERTING_TO_FARMLAND:
                handleFarmlandConversion();
                break;
            case PLANTING_CROPS:
                handleCropPlanting();
                break;
            case HARVESTING_CROPS:
                handleHarvestingWithMaintenance();
                break;
            case PATROLLING:
                handlePatrollingWithMaintenance();
                break;
        }
    }

    private void handleFarmSetup() {
        if (workTimer % 100 == 0) { // Every 5 seconds
            peasant.getFarmingSystem().setupFarm();
        }
    }

    private void handleReturnToJobBlock() {
        // Check if we're at the job block
        if (peasant.getFarmingSystem().isAtJobBlock()) {
            peasant.getFarmingSystem().setCurrentFarmState(FarmState.CONVERTING_TO_FARMLAND);
            return;
        }

        // Navigate to job block
        BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
        if (jobBlock != null) {
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        }
    }

    private void handleFarmlandConversion() {
        if (workTimer % 200 == 0) { // Every 10 seconds
            int converted = peasant.getFarmingSystem().convertToFarmland();

            if (converted == 0) {
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.PLANTING_CROPS);
            }
        }
    }

    private void handleCropPlanting() {
        if (workTimer % 200 == 0) { // Every 10 seconds
            int planted = peasant.getFarmingSystem().plantCrops();

            if (planted == 0) {
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.HARVESTING_CROPS);
            }
        }
    }

    private void handleHarvestingWithMaintenance() {
        // Look for crops to harvest every 1 second
        if (workTimer % 20 == 0) {
            BlockPos harvestPos = peasant.getFarmingSystem().findNextHarvestPosition();

            if (harvestPos != null) {
                currentWorkTarget = harvestPos;
            } else {
                // No crops to harvest, but stay in harvesting mode and do maintenance
                currentWorkTarget = null;

                // Perform maintenance work every 3 seconds when no crops to harvest
                if (workTimer % 60 == 0) {
                    int conversions = peasant.getFarmingSystem().convertDirtGrassToFarmland();
                    int plantings = peasant.getFarmingSystem().plantSeedsOnEmptyFarmland();

                    // If no maintenance work was done, switch to patrolling
                    if (conversions == 0 && plantings == 0) {
                        peasant.getFarmingSystem().setCurrentFarmState(FarmState.PATROLLING);
                    }
                }
            }
        }

        // If we have a harvest target, work on it
        if (currentWorkTarget != null) {
            double distance = peasant.distanceToSqr(currentWorkTarget.getX(),
                    currentWorkTarget.getY(), currentWorkTarget.getZ());

            if (distance <= 9.0D) { // Within 3 blocks
                // Close enough to harvest (this will also trigger maintenance work)
                peasant.getFarmingSystem().harvestAndReplant(currentWorkTarget);
                currentWorkTarget = null;

                // Immediately look for next harvest target
                BlockPos nextHarvestPos = peasant.getFarmingSystem().findNextHarvestPosition();
                if (nextHarvestPos != null) {
                    currentWorkTarget = nextHarvestPos;
                }
            } else {
                // Move closer to harvest target
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(currentWorkTarget.getX() + 0.5,
                            currentWorkTarget.getY(), currentWorkTarget.getZ() + 0.5, 0.6D);
                }
            }
        }
    }

    private void handlePatrollingWithMaintenance() {
        // Check for new work every 3 seconds
        if (workTimer % 60 == 0) {
            // Check if there are crops to harvest (highest priority)
            BlockPos harvestPos = peasant.getFarmingSystem().findNextHarvestPosition();
            if (harvestPos != null) {
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.HARVESTING_CROPS);
                return;
            }

            // Do maintenance work - convert dirt and plant seeds
            int conversions = peasant.getFarmingSystem().convertDirtGrassToFarmland();
            int plantings = peasant.getFarmingSystem().plantSeedsOnEmptyFarmland();

            // If we did work, stay in patrolling mode for gradual farm expansion
            // If no work was done, just continue patrolling
        }

        // Wander around the farm area occasionally
        if (workTimer % 400 == 0 && peasant.getJobBlockPos() != null) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            BlockPos wanderTarget = jobBlock.offset(
                    peasant.getRandom().nextInt(19) - 9,
                    0,
                    peasant.getRandom().nextInt(19) - 9
            );

            peasant.getNavigation().moveTo(wanderTarget.getX() + 0.5,
                    wanderTarget.getY(), wanderTarget.getZ() + 0.5, 0.6D);
        }
    }
}