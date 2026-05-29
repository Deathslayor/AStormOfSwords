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
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER) || peasant.getJobBlockPos() == null) {
            return false;
        }

        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() || peasant.needsFoodCollection()) {
            return false;
        }

        if (peasant.shouldSleep()) {
            return false;
        }

        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            return false;
        }

        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            var jobBlockState = peasant.level().getBlockState(jobBlockPos);
            if (!jobBlockState.is(ModBLocks.FARMER_BARREL.get())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (peasant.shouldSleep() || peasant.isSleeping() ||
                peasant.needsFoodCollection() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 11999;
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
            case NEEDS_FARM_SETUP -> handleFarmSetup();
            case PLANTING_CROPS -> handleCropPlanting();
            case HARVESTING_CROPS -> handleHarvesting();
            case PATROLLING -> handlePatrolling();
        }
    }

    private void handleFarmSetup() {
        if (!peasant.getFarmingSystem().isAtJobBlock()) {
            BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
            if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            if (workTimer % 100 == 0) {
                peasant.getFarmingSystem().setupFarm();
            }
        }
    }

    private void handleCropPlanting() {
        if (!peasant.getFarmingSystem().isAtJobBlock()) {
            BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
            if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            if (workTimer % 200 == 0) {
                peasant.getFarmingSystem().plantCrops();
            }
        }
    }

    private void handleHarvesting() {
        if (workTimer % 20 == 0) {
            BlockPos harvestPos = peasant.getFarmingSystem().findNextHarvestPosition();

            if (harvestPos != null) {
                currentWorkTarget = harvestPos;
            } else {
                currentWorkTarget = null;
                if (workTimer % 60 == 0) {
                    int planted = peasant.getFarmingSystem().plantSeedsOnEmptyFarmland();
                    if (planted == 0) {
                        peasant.getFarmingSystem().setCurrentFarmState(FarmState.PATROLLING);
                    }
                }
            }
        }

        if (currentWorkTarget != null) {
            double distance = peasant.distanceToSqr(
                    currentWorkTarget.getX(), currentWorkTarget.getY(), currentWorkTarget.getZ());

            if (distance <= 9.0D) {
                peasant.getFarmingSystem().harvestAndReplant(currentWorkTarget);
                currentWorkTarget = null;
            } else {
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(
                            currentWorkTarget.getX() + 0.5,
                            currentWorkTarget.getY(),
                            currentWorkTarget.getZ() + 0.5,
                            0.6D);
                }
            }
        }
    }

    private void handlePatrolling() {
        if (workTimer % 60 == 0) {
            BlockPos harvestPos = peasant.getFarmingSystem().findNextHarvestPosition();
            if (harvestPos != null) {
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.HARVESTING_CROPS);
                return;
            }
            peasant.getFarmingSystem().plantSeedsOnEmptyFarmland();
        }

        if (workTimer % 400 == 0 && peasant.getJobBlockPos() != null) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            BlockPos wanderTarget = jobBlock.offset(
                    peasant.getRandom().nextInt(19) - 9,
                    0,
                    peasant.getRandom().nextInt(19) - 9
            );
            peasant.getNavigation().moveTo(
                    wanderTarget.getX() + 0.5, wanderTarget.getY(), wanderTarget.getZ() + 0.5, 0.6D);
        }
    }
}
