package net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmingSystem.FarmState;
import net.darkflameproduction.agotmod.block.ModBLocks;

import java.util.*;

public class FarmingGoal extends Goal {
    private final Peasant_Entity peasant;
    private int workTimer = 0;
    private BlockPos currentWorkTarget = null;

    public FarmingGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Debug: Log every attempt to use this goal for farmers only
        if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_FARMER) && peasant.tickCount % 100 == 0) {
            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: canUse() check - " +
                    "jobType=" + peasant.getJobType() +
                    ", jobBlockPos=" + peasant.getJobBlockPos() +
                    ", sleeping=" + peasant.isSleeping() +
                    ", eating=" + peasant.getHungerSystem().isEating() +
                    ", needsFoodCollection=" + peasant.needsFoodCollection() +
                    ", returnedAfterFood=" + peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood() +
                    ", shouldSleep=" + peasant.shouldSleep() +
                    ", dayTime=" + (peasant.level().getDayTime() % 24000));
        }

        // Must be a farmer with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER) ||
                peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_FARMER) && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - jobType=" + peasant.getJobType() + ", jobBlockPos=" + peasant.getJobBlockPos());
            }
            return false;
        }

        // Must not be sleeping, eating, or collecting food
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - sleeping=" + peasant.isSleeping() + ", eating=" + peasant.getHungerSystem().isEating() + ", needsFoodCollection=" + peasant.needsFoodCollection());
            }
            return false;
        }

        // Must have returned to job block after food collection (unless they haven't collected food yet)
        if (!peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood() && peasant.needsFoodCollection()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Waiting to return to job block after food collection");
            }
            return false;
        }

        // Don't work during sleep hours or just before barrel drop-off
        if (peasant.shouldSleep()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - should sleep");
            }
            return false;
        }

        // Stop farming at 11999 to prepare for barrel drop-off at 12000
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - too late in day (" + dayTime + ")");
            }
            return false;
        }

        // Check if job block still exists (this is also checked in JobSystem but double-check here)
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            var jobBlockState = peasant.level().getBlockState(jobBlockPos);
            if (!jobBlockState.is(ModBLocks.FARMER_BARREL.get())) { // CHANGED FROM COMPOSTER
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Job block is not a farmer barrel! Block at " + jobBlockPos + " is " + jobBlockState.getBlock());
                }
                // Job block destroyed, JobSystem will handle job loss
                return false;
            }
        }

        if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_FARMER) && peasant.tickCount % 200 == 0) {
            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: canUse() = TRUE, all checks passed!");
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        // Debug: Log why we might stop
        if (!peasant.level().isClientSide && peasant.tickCount % 20 == 0) {
            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: canContinueToUse() check - " +
                    "shouldSleep=" + peasant.shouldSleep() +
                    ", sleeping=" + peasant.isSleeping() +
                    ", needsFoodCollection=" + peasant.needsFoodCollection() +
                    ", eating=" + peasant.getHungerSystem().isEating() +
                    ", dayTime=" + (peasant.level().getDayTime() % 24000));
        }

        // Stop if should sleep, collecting food, or eating
        if (peasant.shouldSleep() || peasant.isSleeping() ||
                peasant.needsFoodCollection() || peasant.getHungerSystem().isEating()) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Stopping due to sleep/food conditions");
            }
            return false;
        }

        // Stop farming at 11999 to prepare for barrel drop-off at 12000
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Stopping due to late day time: " + dayTime);
            }
            return false;
        }

        if (!peasant.level().isClientSide && peasant.tickCount % 100 == 0) {
            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: canContinueToUse() = TRUE");
        }

        return true;
    }

    @Override
    public void start() {
        workTimer = 0;
        currentWorkTarget = null;
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Goal started");
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        currentWorkTarget = null;
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Goal stopped");
        }
    }

    @Override
    public void tick() {
        workTimer++;

        FarmState currentState = peasant.getFarmingSystem().getCurrentFarmState();

        // Debug logging every 5 seconds
        if (!peasant.level().isClientSide && workTimer % 100 == 0) {
            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Tick - State=" + currentState + ", Timer=" + workTimer + ", AtJobBlock=" + peasant.getFarmingSystem().isAtJobBlock());
        }

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
        // Move to job block first if not there
        if (!peasant.getFarmingSystem().isAtJobBlock()) {
            BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
            if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                if (!peasant.level().isClientSide && workTimer % 100 == 0) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Moving to job block for farm setup: " + jobBlock);
                }
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            // At job block, can set up farm
            if (workTimer % 100 == 0) { // Every 5 seconds
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: At job block, attempting farm setup. WorkTimer=" + workTimer);
                }
                boolean setupSuccess = peasant.getFarmingSystem().setupFarm();
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Farm setup result=" + setupSuccess);
                }
            } else if (!peasant.level().isClientSide && workTimer % 20 == 0) {
                // More frequent logging to see what's happening
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Waiting for farm setup. WorkTimer=" + workTimer + " (setup at " + (workTimer + (100 - workTimer % 100)) + ")");
            }
        }
    }

    private void handleReturnToJobBlock() {
        // Check if we're at the job block
        if (peasant.getFarmingSystem().isAtJobBlock()) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Returned to job block, switching to farmland conversion");
            }
            peasant.getFarmingSystem().setCurrentFarmState(FarmState.CONVERTING_TO_FARMLAND);
            return;
        }

        // Navigate to job block
        BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
        if (jobBlock != null) {
            if (!peasant.getNavigation().isInProgress()) {
                if (!peasant.level().isClientSide && workTimer % 100 == 0) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Navigating to job block: " + jobBlock);
                }
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        }
    }

    private void handleFarmlandConversion() {
        // Move to job block area first if not there
        if (!peasant.getFarmingSystem().isAtJobBlock()) {
            BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
            if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            // At job block area, can convert farmland
            if (workTimer % 200 == 0) { // Every 10 seconds
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Converting farmland (INITIAL SETUP)");
                }
                int converted = peasant.getFarmingSystem().initialFarmlandSetup(); // Use initial setup method

                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Initial farmland setup result=" + converted + " blocks converted");
                }

                // Initial setup always moves to planting state regardless of conversion count
                // (the method handles state transition internally)
            }
        }
    }

    private void handleCropPlanting() {
        // Move to job block area first if not there
        if (!peasant.getFarmingSystem().isAtJobBlock()) {
            BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
            if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            // At job block area, can plant crops
            if (workTimer % 200 == 0) { // Every 10 seconds
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Planting crops");
                }
                int planted = peasant.getFarmingSystem().plantCrops();

                if (planted == 0) {
                    if (!peasant.level().isClientSide) {
                        System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: No crops to plant, switching to harvesting");
                    }
                    peasant.getFarmingSystem().setCurrentFarmState(FarmState.HARVESTING_CROPS);
                }
            }
        }
    }

    private void handleHarvestingWithMaintenance() {
        // Look for crops to harvest every 1 second
        if (workTimer % 20 == 0) {
            BlockPos harvestPos = peasant.getFarmingSystem().findNextHarvestPosition();

            if (harvestPos != null) {
                currentWorkTarget = harvestPos;
                if (!peasant.level().isClientSide && workTimer % 100 == 0) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Found crop to harvest at " + harvestPos);
                }
            } else {
                // No crops to harvest, but stay in harvesting mode and do maintenance
                currentWorkTarget = null;

                // Perform maintenance work every 3 seconds when no crops to harvest
                if (workTimer % 60 == 0) {
                    // Only do maintenance if we're in the farm area
                    if (peasant.getFarmingSystem().isAtJobBlock()) {
                        if (!peasant.level().isClientSide) {
                            System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: No crops to harvest, doing maintenance");
                        }
                        int conversions = peasant.getFarmingSystem().convertDirtGrassToFarmland();
                        int plantings = peasant.getFarmingSystem().plantSeedsOnEmptyFarmland();

                        // If no maintenance work was done, switch to patrolling
                        if (conversions == 0 && plantings == 0) {
                            if (!peasant.level().isClientSide) {
                                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: No maintenance work needed, switching to patrolling");
                            }
                            peasant.getFarmingSystem().setCurrentFarmState(FarmState.PATROLLING);
                        }
                    } else {
                        // Move back to job block area for maintenance
                        BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
                        if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                            peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                                    jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
                        }
                    }
                }
            }
        }

        // If we have a harvest target, work on it
        if (currentWorkTarget != null) {
            double distance = peasant.distanceToSqr(currentWorkTarget.getX(),
                    currentWorkTarget.getY(), currentWorkTarget.getZ());

            if (distance <= 9.0D) { // Within 3 blocks
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Harvesting crop at " + currentWorkTarget);
                }
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
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Found crops while patrolling, switching to harvesting");
                }
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.HARVESTING_CROPS);
                return;
            }

            // Do maintenance work - convert dirt and plant seeds (only if in farm area)
            if (peasant.getFarmingSystem().isAtJobBlock()) {
                int conversions = peasant.getFarmingSystem().convertDirtGrassToFarmland();
                int plantings = peasant.getFarmingSystem().plantSeedsOnEmptyFarmland();

                if (!peasant.level().isClientSide && (conversions > 0 || plantings > 0)) {
                    System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Did maintenance while patrolling - conversions=" + conversions + ", plantings=" + plantings);
                }

                // If we did work, stay in patrolling mode for gradual farm expansion
                // If no work was done, just continue patrolling
            }
        }

        // Wander around the farm area occasionally
        if (workTimer % 400 == 0 && peasant.getJobBlockPos() != null) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            BlockPos wanderTarget = jobBlock.offset(
                    peasant.getRandom().nextInt(19) - 9,
                    0,
                    peasant.getRandom().nextInt(19) - 9
            );

            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMING GOAL [" + peasant.getDisplayName().getString() + "]: Wandering to " + wanderTarget);
            }

            peasant.getNavigation().moveTo(wanderTarget.getX() + 0.5,
                    wanderTarget.getY(), wanderTarget.getZ() + 0.5, 0.6D);
        }
    }
}