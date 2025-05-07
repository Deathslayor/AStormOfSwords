package net.darkflameproduction.agotmod.entity.goal;

import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

public class MammothPersistentMoveTowardsTargetGoal extends Goal {
    private final Mammoth_Entity mammoth;
    private final double speedModifier;
    private final float maxDistance;
    private LivingEntity target;
    private int navigationCooldown;
    private float lastYRot;

    public MammothPersistentMoveTowardsTargetGoal(Mammoth_Entity mammoth, double speedModifier, float maxDistance) {
        this.mammoth = mammoth;
        this.speedModifier = speedModifier;
        this.maxDistance = maxDistance;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        this.target = this.mammoth.getTarget();
        if (this.target == null) {
            return false;
        }
        return true;
    }

    @Override
    public void start() {
        this.navigationCooldown = 0;
        this.lastYRot = this.mammoth.getYRot();
        mammoth.setSprinting(true);
    }

    @Override
    public void stop() {
        this.target = null;
        this.mammoth.getNavigation().stop();
        mammoth.setSprinting(false);
    }

    @Override
    public void tick() {
        this.mammoth.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        if (--this.navigationCooldown <= 0) {
            this.navigationCooldown = 4 + this.mammoth.getRandom().nextInt(7);
            if (!this.mammoth.isLeashed()) {
                // Calculate rotation difference
                float currentYRot = this.mammoth.getYRot();
                float rotDiff = Math.abs(currentYRot - this.lastYRot);
                // Normalize the difference to be between 0 and 180
                if (rotDiff > 180) {
                    rotDiff = 360 - rotDiff;
                }
                
                // Calculate turn speed modifier (much slower when turning sharply)
                double turnSpeedMultiplier = rotDiff > 30 ? 0.1 : 1.0;
                this.lastYRot = currentYRot;

                if (this.mammoth.distanceToSqr(this.target) > 16.0D) {
                    this.mammoth.getNavigation().moveTo(this.target, this.speedModifier * turnSpeedMultiplier);
                    mammoth.setSprinting(true);
                } else {
                    this.mammoth.getNavigation().moveTo(this.target, this.speedModifier * 0.5D * turnSpeedMultiplier);
                    mammoth.setSprinting(false);
                }
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.mammoth.getTarget();
        if (target == null) {
            return false;
        }
        
        if (!target.isAlive()) {
            return false;
        }

        return !this.mammoth.getNavigation().isDone();
    }
}