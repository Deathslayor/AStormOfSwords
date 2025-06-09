package net.darkflameproduction.agotmod.entity.goal;

import net.darkflameproduction.agotmod.entity.custom.wolves.Direwolf_Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class DirewolfPersistentMoveTowardsTargetGoal extends Goal {
    private final Direwolf_Entity direwolf;
    private final double speedModifier;
    private LivingEntity target;

    public DirewolfPersistentMoveTowardsTargetGoal(Direwolf_Entity direwolf, double speedModifier) {
        this.direwolf = direwolf;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity potentialTarget = this.direwolf.getTarget();
        if (potentialTarget == null || !potentialTarget.isAlive()) {
            return false;
        }
        this.target = potentialTarget;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.target.isAlive() && !this.direwolf.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.direwolf.setSprinting(true); // Always sprint when chasing
        this.moveToTarget();
    }

    @Override
    public void stop() {
        this.target = null;
        this.direwolf.getNavigation().stop();
        this.direwolf.setSprinting(false);
    }

    @Override
    public void tick() {
        if (this.target == null) return;

        this.direwolf.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        // Re-path every few ticks in case the target is moving
        if (this.direwolf.tickCount % 10 == 0) {
            this.moveToTarget();
        }

        this.direwolf.setSprinting(this.direwolf.distanceToSqr(this.target) > 16.0D);
    }

    private void moveToTarget() {
        if (!this.direwolf.isLeashed() && this.target != null) {
            this.direwolf.getNavigation().moveTo(this.target, this.speedModifier);
        }
    }
}
