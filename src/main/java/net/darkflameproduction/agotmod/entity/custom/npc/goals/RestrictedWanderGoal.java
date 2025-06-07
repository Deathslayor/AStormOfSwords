package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import org.jetbrains.annotations.Nullable;

public class RestrictedWanderGoal extends WaterAvoidingRandomStrollGoal {
    private final Peasant_Entity peasant;

    public RestrictedWanderGoal(Peasant_Entity peasant, double speedModifier) {
        super(peasant, speedModifier);
        this.peasant = peasant;
    }

    @Override
    public boolean canUse() {
        // Don't wander if sleeping, eating, or collecting food
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() || peasant.needsFoodCollection()) {
            return false;
        }

        // Don't wander if need to return to job block
        if (peasant.hasJob() && !peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood()) {
            return false;
        }

        // Don't wander if too far from work area during work hours
        if (peasant.getJobSystem().shouldBeAtWorkArea() && peasant.getJobSystem().isTooFarFromWork()) {
            return false;
        }

        // Don't wander if too far from home during non-work hours
        if (!peasant.getJobSystem().shouldBeAtWorkArea() && peasant.isTooFarFromHome()) {
            return false;
        }

        return super.canUse();
    }

    @Override
    @Nullable
    protected net.minecraft.world.phys.Vec3 getPosition() {
        net.minecraft.world.phys.Vec3 naturalPosition = super.getPosition();

        // Determine which area to respect based on time of day
        boolean useWorkArea = peasant.getJobSystem().shouldBeAtWorkArea();

        if (!useWorkArea && !peasant.getHomeSystem().hasHomeBed()) {
            return naturalPosition; // No restrictions if no home
        }

        if (useWorkArea && (!peasant.hasJob() || peasant.getJobBlockPos() == null)) {
            return naturalPosition; // No work restrictions if no job
        }

        // Check if natural position is within appropriate bounds
        if (naturalPosition != null) {
            BlockPos targetPos = BlockPos.containing(naturalPosition);
            boolean withinBounds = useWorkArea ?
                    peasant.getJobSystem().isWithinWorkArea(targetPos) :
                    peasant.isWithinHomeArea(targetPos);

            if (withinBounds) {
                return naturalPosition;
            }
        }

        // Find valid position within appropriate bounds
        for (int attempt = 0; attempt < 10; attempt++) {
            int searchRadius = Math.max(2, 10 - (attempt * 2));
            BlockPos currentPos = peasant.blockPosition();
            int randomX = currentPos.getX() + peasant.getRandom().nextInt(searchRadius * 2 + 1) - searchRadius;
            int randomZ = currentPos.getZ() + peasant.getRandom().nextInt(searchRadius * 2 + 1) - searchRadius;
            BlockPos candidatePos = new BlockPos(randomX, currentPos.getY(), randomZ);

            boolean withinBounds = useWorkArea ?
                    peasant.getJobSystem().isWithinWorkArea(candidatePos) :
                    peasant.isWithinHomeArea(candidatePos);

            if (withinBounds) {
                net.minecraft.world.phys.Vec3 candidateVec = net.minecraft.world.phys.Vec3.atBottomCenterOf(candidatePos);
                if (peasant.getNavigation().isStableDestination(candidatePos)) {
                    return candidateVec;
                }
            }
        }

        return null;
    }
}