package net.darkflameproduction.agotmod.entity.custom.npc.goals.sleep;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;

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

        // FIXED: More lenient food check - allow sleeping even if hungry during sleep time
        // Only prevent sleep if actively eating
        if (needsFood && peasant.getHungerSystem().isEating()) {
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

                // FIXED: More lenient distance check and measure to bed center
                double distanceToBed = peasant.distanceToSqr(
                        homeBed.getX() + 0.5,
                        homeBed.getY() + 0.5,
                        homeBed.getZ() + 0.5
                );
                return distanceToBed <= 9.0D; // Within 3 blocks (was 2 blocks)
            }
        }

        // Fall back to any assigned bed - but also check distance
        if (peasant.getBedPos() != null &&
                !peasant.getSleepSystem().isBedOccupied(peasant.level(), peasant.getBedPos())) {

            // FIXED: More lenient distance check and measure to bed center
            BlockPos bedPos = peasant.getBedPos();
            double distanceToBed = peasant.distanceToSqr(
                    bedPos.getX() + 0.5,
                    bedPos.getY() + 0.5,
                    bedPos.getZ() + 0.5
            );
            return distanceToBed <= 9.0D; // Within 3 blocks (was 2 blocks)
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

        // FIXED: Only stop sleeping for non-guards if they're actively eating, not just hungry
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
            // FIXED: More lenient distance check for starting sleep
            double distanceToBed = peasant.distanceToSqr(
                    bedPos.getX() + 0.5,
                    bedPos.getY() + 0.5,
                    bedPos.getZ() + 0.5
            );

            // If close enough, start sleeping immediately
            if (distanceToBed <= 9.0D) { // Within 3 blocks
                peasant.startSleeping(bedPos);
            } else {
                // If not close enough, move closer first
                peasant.getNavigation().moveTo(
                        bedPos.getX() + 0.5,
                        bedPos.getY(),
                        bedPos.getZ() + 0.5,
                        1.0
                );
            }
        }
    }

    @Override
    public void tick() {
        // ADDED: Continuously try to start sleeping if we're close enough but not sleeping yet
        if (!peasant.isSleeping() && peasant.getBedPos() != null) {
            BlockPos bedPos = peasant.getBedPos();
            double distanceToBed = peasant.distanceToSqr(
                    bedPos.getX() + 0.5,
                    bedPos.getY() + 0.5,
                    bedPos.getZ() + 0.5
            );

            // If we're close enough and not moving, try to sleep
            if (distanceToBed <= 9.0D && !peasant.getNavigation().isInProgress()) {
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