package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.EnumSet;

public class ReturnToJobBlockGoal extends Goal {
    private final Northern_Peasant_Entity peasant;

    public ReturnToJobBlockGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Only active if peasant has a job, finished collecting food, but hasn't returned to job block yet
        return peasant.hasJob() &&
                !peasant.needsFoodCollection() &&
                !peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood() &&
                !peasant.isSleeping() &&
                !peasant.getHungerSystem().isEating() &&
                peasant.getJobBlockPos() != null;
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
            peasant.getNavigation().moveTo(jobBlockPos.getX() + 0.5, jobBlockPos.getY(), jobBlockPos.getZ() + 0.5, 0.6D);
        }
    }

    @Override
    public void stop() {
        // Mark that we've returned to job block
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