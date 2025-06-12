package net.darkflameproduction.agotmod.entity.custom.npc.goals.behaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;

import java.util.EnumSet;

public class ReturnToJobBlockGoal extends Goal {
    private final Peasant_Entity peasant;

    public ReturnToJobBlockGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Only active if peasant has a job, finished collecting food, but hasn't returned to job block yet
        boolean shouldReturn = peasant.hasJob() &&
                !peasant.needsFoodCollection() &&
                !peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood() &&
                !peasant.isSleeping() &&
                !peasant.getHungerSystem().isEating() &&
                peasant.getJobBlockPos() != null;

        // DEBUG: Add logging for this goal
        if (!peasant.level().isClientSide && peasant.tickCount % 100 == 0 && shouldReturn) {
            System.out.println("DEBUG RETURN TO JOB BLOCK GOAL [" + peasant.getDisplayName().getString() + "]: " +
                    "canUse=true, checking if already at job block");
        }

        if (shouldReturn) {
            // Check if already at job block - if so, immediately mark as returned and don't activate
            BlockPos jobBlockPos = peasant.getJobBlockPos();
            if (jobBlockPos != null) {
                double distance = peasant.distanceToSqr(jobBlockPos.getX(), jobBlockPos.getY(), jobBlockPos.getZ());
                if (distance <= 4.0D) {
                    // Already at job block, immediately mark as returned
                    if (!peasant.level().isClientSide) {
                        System.out.println("DEBUG RETURN TO JOB BLOCK GOAL [" + peasant.getDisplayName().getString() + "]: " +
                                "Already at job block (distance=" + Math.sqrt(distance) + "), marking as returned immediately");
                    }
                    peasant.getFarmingSystem().setHasReturnedToJobBlockAfterFood(true);
                    return false; // Don't activate the goal since we're already there
                }
            }
        }

        return shouldReturn;
    }

    @Override
    public boolean canContinueToUse() {
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() || peasant.needsFoodCollection()) {
            return false;
        }

        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos == null) {
            return false;
        }

        // Continue until we're close to the job block
        double distance = peasant.distanceToSqr(jobBlockPos.getX(), jobBlockPos.getY(), jobBlockPos.getZ());
        return distance > 4.0D;
    }

    @Override
    public void start() {
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG RETURN TO JOB BLOCK GOAL [" + peasant.getDisplayName().getString() + "]: " +
                        "Starting navigation to job block: " + jobBlockPos);
            }
            peasant.getNavigation().moveTo(jobBlockPos.getX() + 0.5, jobBlockPos.getY(), jobBlockPos.getZ() + 0.5, 0.6D);
        }
    }

    @Override
    public void stop() {
        // Mark that we've returned to job block
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG RETURN TO JOB BLOCK GOAL [" + peasant.getDisplayName().getString() + "]: " +
                    "Goal stopped, marking as returned to job block");
        }
        peasant.getFarmingSystem().setHasReturnedToJobBlockAfterFood(true);
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            double distance = peasant.distanceToSqr(jobBlockPos.getX(), jobBlockPos.getY(), jobBlockPos.getZ());

            if (distance <= 4.0D) {
                // Close enough, mark as returned
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG RETURN TO JOB BLOCK GOAL [" + peasant.getDisplayName().getString() + "]: " +
                            "Reached job block (distance=" + Math.sqrt(distance) + "), marking as returned");
                }
                peasant.getFarmingSystem().setHasReturnedToJobBlockAfterFood(true);
                return;
            }

            // Keep moving toward job block
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(jobBlockPos.getX() + 0.5, jobBlockPos.getY(), jobBlockPos.getZ() + 0.5, 0.6D);
            }
        }
    }
}