package net.darkflameproduction.agotmod.entity.custom.npc.goals.trader;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class TraderGoal extends Goal {

    private final Peasant_Entity peasant;

    public TraderGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TRADER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (!isJobBlockValid()) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 12000) return false;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TRADER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public void start() {
        peasant.getTraderSystem().setCurrentState(TraderSystem.TraderState.WORKING);
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock != null) {
            peasant.getNavigation().moveTo(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 1.5, 0.6D);
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (peasant.level().isClientSide) return;

        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        double dist = peasant.distanceToSqr(
                jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);

        if (dist > 4.0D) {
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 1.5, 0.6D);
            }
            return;
        }

        // Arrived — stand at barrel and look at it
        peasant.getNavigation().stop();
        peasant.getLookControl().setLookAt(
                jobBlock.getX() + 0.5, jobBlock.getY() + 0.5, jobBlock.getZ() + 0.5);
    }

    private boolean isJobBlockValid() {
        BlockPos pos = peasant.getJobBlockPos();
        if (pos == null) return false;
        BlockState state = peasant.level().getBlockState(pos);
        return state.is(ModBLocks.TRADER_BARREL.get());
    }
}
