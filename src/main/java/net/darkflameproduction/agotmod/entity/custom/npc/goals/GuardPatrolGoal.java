package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;

import java.util.EnumSet;

/**
 * Goal that handles guard patrol behavior when not in combat
 */
public class GuardPatrolGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private BlockPos patrolTarget;
    private int stuckTimer = 0;
    private static final int MAX_STUCK_TIME = 100; // 5 seconds before giving up on a patrol point

    public GuardPatrolGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Only use if we're a guard with a valid job block
        if (!peasant.getJobType().equals(JobSystem.JOB_GUARD) ||
                peasant.getJobBlockPos() == null) {
            return false;
        }

        // Don't patrol if sleeping, eating, or interacting
        if (peasant.isSleeping() ||
                peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection() ||
                peasant.isInteractingWithPlayer() ||
                peasant.isPlayingInteractAnimation()) {
            return false;
        }

        // Don't patrol if we have a combat target
        if (peasant.getTarget() != null ||
                peasant.getGuardSystem().getCurrentTarget() != null) {
            return false;
        }

        // Check if we should be on duty (not sleeping)
        if (peasant.getGuardSystem().shouldSleep()) {
            return false;
        }

        // Must have an assigned shift to patrol
        if (!peasant.getGuardSystem().hasAssignedShift()) {
            return false;
        }

        // Get patrol target from guard system
        patrolTarget = peasant.getGuardSystem().getPatrolTarget();
        return patrolTarget != null;
    }

    @Override
    public boolean canContinueToUse() {
        // Stop if we're no longer a guard or lost job block
        if (!peasant.getJobType().equals(JobSystem.JOB_GUARD) ||
                peasant.getJobBlockPos() == null) {
            return false;
        }

        // Stop if we should be sleeping
        if (peasant.getGuardSystem().shouldSleep()) {
            return false;
        }

        // Stop if we have a combat target
        if (peasant.getTarget() != null ||
                peasant.getGuardSystem().getCurrentTarget() != null) {
            return false;
        }

        // Stop if eating or interacting
        if (peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection() ||
                peasant.isInteractingWithPlayer() ||
                peasant.isPlayingInteractAnimation()) {
            return false;
        }

        // Stop if we've been stuck for too long
        if (stuckTimer >= MAX_STUCK_TIME) {
            return false;
        }

        // Continue if we have a valid patrol target
        BlockPos currentTarget = peasant.getGuardSystem().getPatrolTarget();
        if (currentTarget != null && !currentTarget.equals(patrolTarget)) {
            // Patrol target changed, update and continue
            patrolTarget = currentTarget;
            stuckTimer = 0;
            return true;
        }

        return patrolTarget != null;
    }

    @Override
    public void start() {
        stuckTimer = 0;
        if (patrolTarget != null) {
            peasant.getNavigation().moveTo(
                    patrolTarget.getX() + 0.5,
                    patrolTarget.getY(),
                    patrolTarget.getZ() + 0.5,
                    0.6D
            );
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        patrolTarget = null;
        stuckTimer = 0;
    }

    @Override
    public void tick() {
        if (patrolTarget == null) {
            return;
        }

        // Check if guard system updated the patrol target
        BlockPos currentSystemTarget = peasant.getGuardSystem().getPatrolTarget();
        if (currentSystemTarget != null && !currentSystemTarget.equals(patrolTarget)) {
            // Target changed, update our target
            patrolTarget = currentSystemTarget;
            stuckTimer = 0;
            peasant.getNavigation().moveTo(
                    patrolTarget.getX() + 0.5,
                    patrolTarget.getY(),
                    patrolTarget.getZ() + 0.5,
                    0.6D
            );
            return;
        }

        // Check if we're close to the patrol target
        double distanceToTarget = peasant.distanceToSqr(
                patrolTarget.getX(),
                patrolTarget.getY(),
                patrolTarget.getZ()
        );

        if (distanceToTarget <= 4.0D) {
            // Reached patrol point, goal will end and guard system will generate new target
            return;
        }

        // Check if we're making progress
        if (!peasant.getNavigation().isInProgress()) {
            stuckTimer++;

            // Try to restart navigation if stuck
            if (stuckTimer % 20 == 0) { // Every second
                peasant.getNavigation().moveTo(
                        patrolTarget.getX() + 0.5,
                        patrolTarget.getY(),
                        patrolTarget.getZ() + 0.5,
                        0.6D
                );
            }
        } else {
            // We're moving, reset stuck timer
            if (stuckTimer > 0) {
                stuckTimer--;
            }
        }

        // Look towards patrol target while moving
        peasant.getLookControl().setLookAt(
                patrolTarget.getX() + 0.5,
                patrolTarget.getY() + 0.5,
                patrolTarget.getZ() + 0.5
        );
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}