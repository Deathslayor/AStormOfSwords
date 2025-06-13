package net.darkflameproduction.agotmod.entity.custom.npc.goals.miner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem.MinerState;
import net.darkflameproduction.agotmod.block.ModBLocks;

import java.util.*;

public class MinerGoal extends Goal {
    private final Peasant_Entity peasant;
    private int workTimer = 0;
    private BlockPos currentWorkTarget = null;

    public MinerGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Debug: Log every attempt to use this goal for miners only
        if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_MINER) && peasant.tickCount % 100 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: canUse() check - " +
                    "jobType=" + peasant.getJobType() +
                    ", jobBlockPos=" + peasant.getJobBlockPos() +
                    ", sleeping=" + peasant.isSleeping() +
                    ", eating=" + peasant.getHungerSystem().isEating() +
                    ", needsFoodCollection=" + peasant.needsFoodCollection() +
                    ", returnedAfterFood=" + peasant.getMinerSystem().hasReturnedToJobBlockAfterFood() +
                    ", shouldSleep=" + peasant.shouldSleep() +
                    ", dayTime=" + (peasant.level().getDayTime() % 24000));
        }

        // Must be a miner with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER) ||
                peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_MINER) && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - jobType=" + peasant.getJobType() + ", jobBlockPos=" + peasant.getJobBlockPos());
            }
            return false;
        }

        // Must not be sleeping, eating, or collecting food
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - sleeping=" + peasant.isSleeping() + ", eating=" + peasant.getHungerSystem().isEating() + ", needsFoodCollection=" + peasant.needsFoodCollection());
            }
            return false;
        }

        // Must have returned to job block after food collection (unless they haven't collected food yet)
        if (!peasant.getMinerSystem().hasReturnedToJobBlockAfterFood() && peasant.needsFoodCollection()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Waiting to return to job block after food collection");
            }
            return false;
        }

        // Don't work during sleep hours or just before barrel drop-off
        if (peasant.shouldSleep()) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - should sleep");
            }
            return false;
        }

        // Stop mining at 11999 to prepare for barrel drop-off at 12000
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Cannot use - too late in day (" + dayTime + ")");
            }
            return false;
        }

        // Check if job block still exists (this is also checked in JobSystem but double-check here)
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            var jobBlockState = peasant.level().getBlockState(jobBlockPos);
            if (!jobBlockState.is(ModBLocks.MINER_BARREL.get())) {
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Job block is not a miner barrel! Block at " + jobBlockPos + " is " + jobBlockState.getBlock());
                }
                // Job block destroyed, JobSystem will handle job loss
                return false;
            }
        }

        if (!peasant.level().isClientSide && peasant.getJobType().equals(JobSystem.JOB_MINER) && peasant.tickCount % 200 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: canUse() = TRUE, all checks passed!");
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        // Debug: Log why we might stop
        if (!peasant.level().isClientSide && peasant.tickCount % 20 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: canContinueToUse() check - " +
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
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Stopping due to sleep/food conditions");
            }
            return false;
        }

        // Stop mining at 11999 to prepare for barrel drop-off at 12000
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Stopping due to late day time: " + dayTime);
            }
            return false;
        }

        if (!peasant.level().isClientSide && peasant.tickCount % 100 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: canContinueToUse() = TRUE");
        }

        return true;
    }

    @Override
    public void start() {
        workTimer = 0;
        currentWorkTarget = null;
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Goal started");
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        currentWorkTarget = null;
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Goal stopped");
        }
    }

    @Override
    public void tick() {
        workTimer++;

        MinerState currentState = peasant.getMinerSystem().getCurrentMinerState();

        // Debug logging every 5 seconds
        if (!peasant.level().isClientSide && workTimer % 100 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Tick - State=" + currentState + ", Timer=" + workTimer + ", AtJobBlock=" + peasant.getMinerSystem().isAtJobBlock());
        }

        switch (currentState) {
            case NEEDS_MINE_SETUP:
                handleMineSetup();
                break;
            case RETURN_TO_JOB_BLOCK:
                handleReturnToJobBlock();
                break;
            case SETTING_UP_MINE:
                handleMineSetupWork();
                break;
            case MINING:
                handleMining();
                break;
            case PATROLLING:
                handlePatrolling();
                break;
        }
    }

    private void handleMineSetup() {
        // Move to job block first if not there
        if (!peasant.getMinerSystem().isAtJobBlock()) {
            BlockPos jobBlock = peasant.getMinerSystem().getJobBlockPosition();
            if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                if (!peasant.level().isClientSide && workTimer % 100 == 0) {
                    System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Moving to job block for mine setup: " + jobBlock);
                }
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            // At job block, can set up mine
            if (workTimer % 100 == 0) { // Every 5 seconds
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: At job block, attempting mine setup. WorkTimer=" + workTimer);
                }
                boolean setupSuccess = peasant.getMinerSystem().setupMine();
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Mine setup result=" + setupSuccess);
                }
            } else if (!peasant.level().isClientSide && workTimer % 20 == 0) {
                // More frequent logging to see what's happening
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Waiting for mine setup. WorkTimer=" + workTimer + " (setup at " + (workTimer + (100 - workTimer % 100)) + ")");
            }
        }
    }

    private void handleReturnToJobBlock() {
        // Check if we're at the job block
        if (peasant.getMinerSystem().isAtJobBlock()) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Returned to job block, switching to mine setup work");
            }
            peasant.getMinerSystem().setCurrentMinerState(MinerState.SETTING_UP_MINE);
            return;
        }

        // Navigate to job block
        BlockPos jobBlock = peasant.getMinerSystem().getJobBlockPosition();
        if (jobBlock != null) {
            if (!peasant.getNavigation().isInProgress()) {
                if (!peasant.level().isClientSide && workTimer % 100 == 0) {
                    System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Navigating to job block: " + jobBlock);
                }
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        }
    }

    private void handleMineSetupWork() {
        // TODO: Implement mine setup work (creating mine shafts, etc.)
        if (!peasant.level().isClientSide && workTimer % 100 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Doing mine setup work");
        }

        // For now, immediately transition to mining
        peasant.getMinerSystem().setCurrentMinerState(MinerState.MINING);
    }

    private void handleMining() {
        // TODO: Implement actual mining logic
        if (!peasant.level().isClientSide && workTimer % 100 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Mining");
        }
    }

    private void handlePatrolling() {
        // TODO: Implement patrolling logic
        if (!peasant.level().isClientSide && workTimer % 100 == 0) {
            System.out.println("DEBUG MINER GOAL [" + peasant.getDisplayName().getString() + "]: Patrolling");
        }
    }
}