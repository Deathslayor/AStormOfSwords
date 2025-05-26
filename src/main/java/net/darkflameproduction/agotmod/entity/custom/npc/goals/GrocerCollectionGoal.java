package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem;

import java.util.EnumSet;

public class GrocerCollectionGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private int workTimer = 0;

    public GrocerCollectionGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Must be a grocer with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_GROCER) ||
                peasant.getJobBlockPos() == null) {
            return false;
        }

        // Must not be sleeping, eating, or collecting food
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        // Check if it's collection time (around 1100 game time)
        long dayTime = peasant.level().getDayTime() % 24000;

        // Active during collection time (1100-1200) or if collection is in progress
        boolean isCollectionTime = dayTime >= 1100 && dayTime <= 1200;
        boolean isCollecting = peasant.getGrocerSystem().getCurrentState() == GrocerSystem.GrocerState.COLLECTING_FROM_BARRELS;

        return isCollectionTime || isCollecting;
    }

    @Override
    public boolean canContinueToUse() {
        // Stop if should sleep, collecting food, or eating
        if (peasant.shouldSleep() || peasant.isSleeping() ||
                peasant.needsFoodCollection() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Continue if collection is still in progress
        return peasant.getGrocerSystem().getCurrentState() == GrocerSystem.GrocerState.COLLECTING_FROM_BARRELS;
    }

    @Override
    public void start() {
        workTimer = 0;
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        workTimer++;

        // The GrocerSystem handles the actual collection logic in its tick() method
        // This goal just ensures the NPC is available during collection time

        // Stay near job block during collection
        if (peasant.getJobBlockPos() != null && workTimer % 100 == 0) {
            double distance = peasant.distanceToSqr(
                    peasant.getJobBlockPos().getX(),
                    peasant.getJobBlockPos().getY(),
                    peasant.getJobBlockPos().getZ()
            );

            // If too far from job block, return to it
            if (distance > 16.0D) { // 4 blocks away
                peasant.getNavigation().moveTo(
                        peasant.getJobBlockPos().getX() + 0.5,
                        peasant.getJobBlockPos().getY(),
                        peasant.getJobBlockPos().getZ() + 0.5,
                        0.6D
                );
            }
        }
    }
}