package net.darkflameproduction.agotmod.entity.goal;

import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;
import java.util.List;

public class MammothAttackGoal extends Goal {
    private final Mammoth_Entity mammoth;
    private int attackProgress = 0;
    private final int attackDuration = 16;
    private boolean isAttacking = false;
    private static final double ATTACK_RANGE = 5.0D;
    private static final double DAMAGE_RADIUS = 3.0D;
    private double targetX;
    private double targetY;
    private double targetZ;
    private boolean hasDealtDamage = false;

    public MammothAttackGoal(Mammoth_Entity mammoth) {
        this.mammoth = mammoth;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return !this.mammoth.isAttacking() &&
                this.mammoth.getTarget() != null &&
                this.mammoth.getTarget().isAlive() &&
                this.mammoth.distanceToSqr(this.mammoth.getTarget()) <= ATTACK_RANGE * ATTACK_RANGE;
    }

    @Override
    public void start() {
        this.isAttacking = true;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.mammoth.setIsAttacking(true);
        this.mammoth.swing(InteractionHand.MAIN_HAND);
        this.mammoth.setDeltaMovement(0, this.mammoth.getDeltaMovement().y, 0);
        this.mammoth.getNavigation().stop();

        LivingEntity target = this.mammoth.getTarget();
        if (target != null) {
            this.targetX = target.getX();
            this.targetY = target.getEyeY();
            this.targetZ = target.getZ();
        }
    }

    @Override
    public void tick() {
        this.mammoth.setDeltaMovement(0, this.mammoth.getDeltaMovement().y, 0);

        if (attackProgress < 15) {
            this.mammoth.getLookControl().setLookAt(targetX, targetY, targetZ, 30.0F, 30.0F);
        } else if (attackProgress == 15 && !hasDealtDamage) {
            performAreaAttack();
            hasDealtDamage = true;
        }

        attackProgress++;
        if (attackProgress >= attackDuration) {
            stop();
        }
    }

    private void performAreaAttack() {
        List<LivingEntity> nearbyEntities = this.mammoth.level().getEntitiesOfClass(
                LivingEntity.class,
                this.mammoth.getBoundingBox().inflate(DAMAGE_RADIUS),
                entity -> entity != this.mammoth && entity.isAlive()
        );

        for (LivingEntity entity : nearbyEntities) {
            entity.hurt(this.mammoth.level().damageSources().mobAttack(this.mammoth),
                    (float)this.mammoth.getAttributeValue(Attributes.ATTACK_DAMAGE));

            double d0 = entity.getX() - this.mammoth.getX();
            double d1 = entity.getZ() - this.mammoth.getZ();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
            entity.push(d0 / d2 * 2.0D, 0.3D, d1 / d2 * 2.0D);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.isAttacking && attackProgress < attackDuration;
    }

    @Override
    public void stop() {
        this.isAttacking = false;
        this.mammoth.setIsAttacking(false);
        this.attackProgress = 0;
        this.hasDealtDamage = false;
    }
}