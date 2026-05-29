package net.darkflameproduction.agotmod.entity.custom.npc.goals.behaviour;

import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;

import java.util.EnumSet;

public class ReturnToJobBlockGoal extends Goal {
    private final Peasant_Entity peasant;
    private static final double ARRIVAL_DIST_SQ = 4.0D; // 2 blocks squared

    public ReturnToJobBlockGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    private boolean isJobThatNeedsReturn() {
        String job = peasant.getJobType();
        return job.equals(JobSystem.JOB_FARMER)
                || job.equals(JobSystem.JOB_GROCER)
                || job.equals(JobSystem.JOB_BUTCHER)
                || job.equals(JobSystem.JOB_TANNER)
                || job.equals(JobSystem.JOB_TAILOR)
                || job.equals(JobSystem.JOB_BLACKSMITH);
    }

    @Override
    public boolean canUse() {
        if (!peasant.hasJob()) return false;
        if (!isJobThatNeedsReturn()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.isSleeping()) return false;
        if (peasant.getHungerSystem().isEating()) return false;
        if (peasant.getJobBlockPos() == null) return false;

        // Farmer-specific: only return after food collection
        if (peasant.getJobType().equals(JobSystem.JOB_FARMER)) {
            if (peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood()) return false;
        }

        BlockPos jobBlockPos = peasant.getJobBlockPos();
        double distance = peasant.distanceToSqr(
                jobBlockPos.getX() + 0.5, jobBlockPos.getY(), jobBlockPos.getZ() + 0.5);

        if (distance <= ARRIVAL_DIST_SQ) {
            if (peasant.getJobType().equals(JobSystem.JOB_FARMER)) {
                peasant.getFarmingSystem().setHasReturnedToJobBlockAfterFood(true);
            }
            return false;
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (!isJobThatNeedsReturn()) return false;
        if (peasant.isSleeping()) return false;
        if (peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;

        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos == null) return false;

        double distance = peasant.distanceToSqr(
                jobBlockPos.getX() + 0.5, jobBlockPos.getY(), jobBlockPos.getZ() + 0.5);
        return distance > ARRIVAL_DIST_SQ;
    }

    @Override
    public void start() {
        navigateToJobBlock();
    }

    @Override
    public void stop() {
        if (peasant.getJobType().equals(JobSystem.JOB_FARMER)) {
            peasant.getFarmingSystem().setHasReturnedToJobBlockAfterFood(true);
        }
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos == null) return;

        double distance = peasant.distanceToSqr(
                jobBlockPos.getX() + 0.5, jobBlockPos.getY(), jobBlockPos.getZ() + 0.5);

        if (distance <= ARRIVAL_DIST_SQ) {
            if (peasant.getJobType().equals(JobSystem.JOB_FARMER)) {
                peasant.getFarmingSystem().setHasReturnedToJobBlockAfterFood(true);
            }
            peasant.getNavigation().stop();
            return;
        }

        if (!peasant.getNavigation().isInProgress()) {
            navigateToJobBlock();
        }
    }

    private void navigateToJobBlock() {
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos == null) return;
        // Navigate to a position offset from the job block so they stand beside it
        peasant.getNavigation().moveTo(
                jobBlockPos.getX() + 0.5,
                jobBlockPos.getY(),
                jobBlockPos.getZ() + 1.5,
                0.6D);
    }
}
