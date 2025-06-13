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
        // Must be a miner with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER) || peasant.getJobBlockPos() == null) {
            return false;
        }

        // Must not be sleeping or eating
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Don't mine when needing food
        if (peasant.needsFoodCollection()) {
            return false;
        }

        // Don't work during sleep hours
        if (peasant.shouldSleep()) {
            return false;
        }

        // Stop mining before barrel drop-off time
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 11999) {
            return false;
        }

        // Verify job block still exists
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            var jobBlockState = peasant.level().getBlockState(jobBlockPos);
            if (!jobBlockState.is(ModBLocks.MINER_BARREL.get())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        // Check all stopping conditions
        boolean shouldSleep = peasant.shouldSleep();
        boolean isSleeping = peasant.isSleeping();
        boolean needsFood = peasant.needsFoodCollection();
        boolean isEating = peasant.getHungerSystem().isEating();
        long dayTime = peasant.level().getDayTime() % 24000;
        boolean nearBarrelTime = dayTime >= 11999;

        // Log detailed stopping reason (only on server)
        if (!peasant.level().isClientSide && (shouldSleep || isSleeping || needsFood || isEating || nearBarrelTime)) {
            System.out.println("=== MINER GOAL STOPPING [" + peasant.getName().getString() + "] ===");
            System.out.println("  Reason - shouldSleep: " + shouldSleep + ", isSleeping: " + isSleeping);
            System.out.println("  needsFood: " + needsFood + ", isEating: " + isEating);
            System.out.println("  nearBarrelTime: " + nearBarrelTime + " (dayTime: " + dayTime + ")");
            System.out.println("  Goal will stop and allow other goals to take over");
        }

        // Stop if any condition is true
        return !shouldSleep && !isSleeping && !needsFood && !isEating && !nearBarrelTime;
    }

    @Override
    public void start() {
        workTimer = 0;
        currentWorkTarget = null;

        if (!peasant.level().isClientSide) {
            System.out.println("=== MINER GOAL STARTED [" + peasant.getName().getString() + "] ===");
            System.out.println("  Job block: " + peasant.getJobBlockPos());
            System.out.println("  Miner state: " + peasant.getMinerSystem().getCurrentMinerState());
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        currentWorkTarget = null;

        if (!peasant.level().isClientSide) {
            System.out.println("=== MINER GOAL STOPPED [" + peasant.getName().getString() + "] ===");
            System.out.println("  Final position: " + peasant.blockPosition());
            System.out.println("  Final miner state: " + peasant.getMinerSystem().getCurrentMinerState());
        }
    }


    @Override
    public void tick() {
        workTimer++;

        MinerState currentState = peasant.getMinerSystem().getCurrentMinerState();

        // Debug state every 5 seconds
        if (!peasant.level().isClientSide && workTimer % 100 == 0) {
            System.out.println("=== MINER GOAL TICK [" + peasant.getName().getString() + "] ===");
            System.out.println("  State: " + currentState + ", Timer: " + workTimer);
            System.out.println("  Position: " + peasant.blockPosition());
            System.out.println("  Navigation active: " + peasant.getNavigation().isInProgress());
            if (currentWorkTarget != null) {
                System.out.println("  Current target: " + currentWorkTarget);
                double dist = Math.sqrt(peasant.distanceToSqr(currentWorkTarget.getX() + 0.5, currentWorkTarget.getY(), currentWorkTarget.getZ() + 0.5));
                System.out.println("  Distance to target: " + String.format("%.2f", dist));
            }
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
                if (!peasant.level().isClientSide) {
                    System.out.println("  Mine setup: navigating to job block " + jobBlock);
                }
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            // At job block, can set up mine
            if (workTimer % 100 == 0) { // Every 5 seconds
                if (!peasant.level().isClientSide) {
                    System.out.println("  Attempting mine setup...");
                }
                boolean setupSuccess = peasant.getMinerSystem().setupMine();
                if (!peasant.level().isClientSide) {
                    System.out.println("  Mine setup result: " + setupSuccess);
                }
            }
        }
    }

    private void handleReturnToJobBlock() {
        // Check if we're at the job block
        if (peasant.getMinerSystem().isAtJobBlock()) {
            if (!peasant.level().isClientSide) {
                System.out.println("  Reached job block, switching to mine setup");
            }
            peasant.getMinerSystem().setCurrentMinerState(MinerState.SETTING_UP_MINE);
            return;
        }

        // Navigate to job block
        BlockPos jobBlock = peasant.getMinerSystem().getJobBlockPosition();
        if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
            if (!peasant.level().isClientSide) {
                System.out.println("  Returning to job block: " + jobBlock);
            }
            peasant.getNavigation().moveTo(jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
        }
    }

    private void handleMineSetupWork() {
        // Move to job block area first if not there
        if (!peasant.getMinerSystem().isAtJobBlock()) {
            BlockPos jobBlock = peasant.getMinerSystem().getJobBlockPosition();
            if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                if (!peasant.level().isClientSide) {
                    System.out.println("  Mine setup work: navigating to job block " + jobBlock);
                }
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        } else {
            // At job block area, perform mine setup
            if (workTimer % 200 == 0) { // Every 10 seconds
                if (!peasant.level().isClientSide) {
                    System.out.println("  Performing mine construction...");
                }
                int setupWork = peasant.getMinerSystem().performMineSetup();
                if (!peasant.level().isClientSide) {
                    System.out.println("  Mine construction work completed: " + setupWork);
                    System.out.println("  Setup complete: " + peasant.getMinerSystem().isMineSetupComplete());
                }
                // Setup automatically moves to MINING state when complete
            }
        }
    }

    private void handleMining() {
        // Get the current mining target from the MinerSystem
        BlockPos miningTarget = peasant.getMinerSystem().getCurrentMiningTarget();

        if (miningTarget == null) {
            // Safety check - if stuck without target for too long, reset
            if (workTimer > 200) { // If no target for 10 seconds (200 ticks)
                if (!peasant.level().isClientSide) {
                    System.out.println("=== MINING TARGET TIMEOUT [" + peasant.getName().getString() + "] ===");
                    System.out.println("  No mining target for " + workTimer + " ticks, resetting mining state");
                }
                peasant.getMinerSystem().resetMiningState();
                workTimer = 0;
                return;
            }

            // Stay near job block area and let MinerSystem select a target
            if (!peasant.getMinerSystem().isAtJobBlock()) {
                BlockPos jobBlock = peasant.getMinerSystem().getJobBlockPosition();
                if (jobBlock != null && !peasant.getNavigation().isInProgress()) {
                    if (!peasant.level().isClientSide) {
                        System.out.println("  No mining target, returning to job block area: " + jobBlock);
                    }
                    peasant.getNavigation().moveTo(jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
                }
            }
            return;
        }

        // Reset timer when we have a target
        workTimer = 0;

        // CHECK: Are we still navigating to the tunnel entrance?
        if (!peasant.getMinerSystem().hasReachedCurrentEntrance()) {
            // Still need to reach the tunnel entrance - navigate there first
            if (!peasant.getNavigation().isInProgress() || !isSameTarget(miningTarget)) {
                currentWorkTarget = miningTarget;

                if (!peasant.level().isClientSide) {
                    double dist = Math.sqrt(peasant.distanceToSqr(miningTarget.getX() + 0.5, miningTarget.getY(), miningTarget.getZ() + 0.5));
                    System.out.println("  Navigating to tunnel entrance: " + miningTarget + " (distance: " + String.format("%.2f", dist) + ")");
                    System.out.println("  This is ENTRANCE navigation, not mining yet");
                }

                peasant.getNavigation().moveTo(miningTarget.getX() + 0.5, miningTarget.getY(), miningTarget.getZ() + 0.5, 0.6D);
            }
        } else {
            // We've reached the tunnel entrance - now start actual mining
            // Check if we need to advance from entrance to first mining position
            if (miningTarget.equals(peasant.getMinerSystem().getCurrentHallwayStart())) {
                if (!peasant.level().isClientSide) {
                    System.out.println("  At tunnel entrance, advancing to first mining position");
                }
                peasant.getMinerSystem().advanceFromEntranceToMining();
                return; // Wait for next tick with new target
            }

            // Normal mining navigation
            if (!peasant.getNavigation().isInProgress() || !isSameTarget(miningTarget)) {
                currentWorkTarget = miningTarget;

                if (!peasant.level().isClientSide) {
                    double dist = Math.sqrt(peasant.distanceToSqr(miningTarget.getX() + 0.5, miningTarget.getY(), miningTarget.getZ() + 0.5));
                    System.out.println("  Mining navigation to: " + miningTarget + " (distance: " + String.format("%.2f", dist) + ")");
                }

                peasant.getNavigation().moveTo(miningTarget.getX() + 0.5, miningTarget.getY(), miningTarget.getZ() + 0.5, 0.6D);
            }
        }

        // Always look at the mining target while navigating
        peasant.getLookControl().setLookAt(miningTarget.getX() + 0.5, miningTarget.getY() + 1, miningTarget.getZ() + 0.5);
    }

    private void handlePatrolling() {
        // Wander around the mine area occasionally
        if (workTimer % 400 == 0 && peasant.getJobBlockPos() != null) {
            BlockPos jobBlock = peasant.getJobBlockPos();

            // Patrol within the 19x19 platform area at the bottom of the mine
            int platformY = jobBlock.getY() + (-1 - 35); // Platform level
            BlockPos wanderTarget = new BlockPos(
                    jobBlock.getX() + peasant.getRandom().nextInt(19) - 9,
                    platformY,
                    jobBlock.getZ() + peasant.getRandom().nextInt(19) - 9
            );

            // Validate patrol target
            if (isValidPatrolTarget(wanderTarget)) {
                if (!peasant.level().isClientSide) {
                    System.out.println("  Patrolling to: " + wanderTarget);
                }
                peasant.getNavigation().moveTo(wanderTarget.getX() + 0.5, wanderTarget.getY(), wanderTarget.getZ() + 0.5, 0.4D);
            } else {
                // If invalid target, just stay at job block
                if (!peasant.level().isClientSide) {
                    System.out.println("  Invalid patrol target, staying at job block");
                }
            }
        }
    }


    private boolean isSameTarget(BlockPos target) {
        return currentWorkTarget != null && currentWorkTarget.equals(target);
    }

    /**
     * Checks if a position is valid for patrolling (not a hole or wall)
     */
    private boolean isValidPatrolTarget(BlockPos pos) {
        // Check if there's a solid block to stand on
        if (peasant.level().getBlockState(pos.below()).isAir()) {
            return false;
        }

        // Check if there's space to walk (2 blocks high)
        if (!peasant.level().getBlockState(pos).isAir() ||
                !peasant.level().getBlockState(pos.above()).isAir()) {
            return false;
        }

        return true;
    }

    /**
     * Gets the current mining target from the MinerSystem
     */
    public BlockPos getCurrentMiningTarget() {
        return peasant.getMinerSystem().getCurrentMiningTarget();
    }
}