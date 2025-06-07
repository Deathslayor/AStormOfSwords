package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;

import java.util.EnumSet;

public class SleepGoal extends Goal {
    private final Peasant_Entity peasant;

    public SleepGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        boolean shouldSleep = peasant.shouldSleep();
        boolean isSleeping = peasant.isSleeping();
        boolean needsFood = peasant.needsFoodCollection();
        boolean isGuard = peasant.getJobType().equals(JobSystem.JOB_GUARD);

        // Must be sleep time and not already sleeping
        if (!shouldSleep || isSleeping) {
            return false;
        }

        // For guards: sleep takes priority over food collection during sleep time
        // For regular NPCs: don't sleep if they need food
        if (needsFood && !isGuard) {
            return false;
        }

        // Always prefer home bed if it exists and is available
        if (peasant.getHomeSystem().hasHomeBed()) {
            BlockPos homeBed = peasant.getHomeSystem().getHomeBedPos();
            BlockState bedState = peasant.level().getBlockState(homeBed);

            if (bedState.getBlock() instanceof BedBlock &&
                    !peasant.getSleepSystem().isBedOccupied(peasant.level(), homeBed)) {

                // Set home bed as current bed if not already set
                if (!homeBed.equals(peasant.getBedPos())) {
                    peasant.setBedPos(homeBed);
                }

                // Only start sleeping if we're close enough to the bed
                double distanceToBed = peasant.distanceToSqr(homeBed.getX(), homeBed.getY(), homeBed.getZ());
                return distanceToBed <= 4.0D; // Must be within 2 blocks
            }
        }

        // Fall back to any assigned bed - but also check distance
        if (peasant.getBedPos() != null &&
                !peasant.getSleepSystem().isBedOccupied(peasant.level(), peasant.getBedPos())) {

            double distanceToBed = peasant.distanceToSqr(peasant.getBedPos().getX(), peasant.getBedPos().getY(), peasant.getBedPos().getZ());
            return distanceToBed <= 4.0D; // Must be within 2 blocks
        }

        // No valid bed found or not close enough
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        boolean shouldSleep = peasant.shouldSleep();
        boolean isGuard = peasant.getJobType().equals(JobSystem.JOB_GUARD);

        // Must still be sleep time
        if (!shouldSleep) {
            return false;
        }

        // For guards: continue sleeping even if they need food - sleep is priority during sleep time
        // For regular NPCs: stop sleeping if they need food and are actively eating
        if (!isGuard && peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Check if bed still exists and is valid
        if (peasant.getBedPos() != null) {
            BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
            if (!(bedState.getBlock() instanceof BedBlock)) {
                return false;
            }
        }

        return peasant.isSleeping();
    }

    @Override
    public void start() {
        BlockPos bedPos = peasant.getBedPos();
        if (bedPos != null) {
            // Only start sleeping if we're actually close to the bed
            double distanceToBed = peasant.distanceToSqr(bedPos.getX(), bedPos.getY(), bedPos.getZ());
            if (distanceToBed <= 4.0D) {
                // Use our custom startSleeping method which handles guard time restrictions
                peasant.startSleeping(bedPos);
            }
        }
    }

    @Override
    public void stop() {
        peasant.stopSleeping();
        if (peasant.getBedPos() != null) {
            BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
            if (!(bedState.getBlock() instanceof BedBlock)) {
                // If this was their home bed and it's gone, clear home bed
                if (peasant.getBedPos().equals(peasant.getHomeSystem().getHomeBedPos())) {
                    peasant.getHomeSystem().setHomeBedPos(null);
                }
                peasant.setBedPos(null);
            }
        }
    }
}