package net.darkflameproduction.agotmod.entity.goal;

import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class MammothPersistentMoveTowardsTargetGoal extends Goal {
    private final Mammoth_Entity mammoth;
    private final double speedModifier;
    private LivingEntity target;

    public MammothPersistentMoveTowardsTargetGoal(Mammoth_Entity mammoth, double speedModifier) {
        this.mammoth = mammoth;
        this.speedModifier = speedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity potentialTarget = this.mammoth.getTarget();
        if (potentialTarget == null || !potentialTarget.isAlive()) {
            return false;
        }
        this.target = potentialTarget;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null && this.target.isAlive() && !this.mammoth.getNavigation().isDone();
    }

    @Override
    public void start() {
        this.mammoth.setSprinting(true); // Sprint at the start of pursuit
        moveToTarget();
    }

    @Override
    public void stop() {
        this.target = null;
        this.mammoth.getNavigation().stop();
        this.mammoth.setSprinting(false);
    }

    @Override
    public void tick() {
        if (this.target == null) return;

        this.mammoth.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        // Update path periodically like vanilla AI
        if (this.mammoth.tickCount % 10 == 0) {
            moveToTarget();
        }

        this.mammoth.setSprinting(this.mammoth.distanceToSqr(this.target) > 16.0D);
    }

    private void moveToTarget() {
        if (!this.mammoth.isLeashed() && this.target != null) {
            this.mammoth.getNavigation().moveTo(this.target, this.speedModifier);
        }
    }
}
