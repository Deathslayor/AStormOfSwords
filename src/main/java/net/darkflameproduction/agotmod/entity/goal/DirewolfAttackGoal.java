package net.darkflameproduction.agotmod.entity.goal;

import net.darkflameproduction.agotmod.entity.custom.wolves.Direwolf_Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

import java.util.EnumSet;

public class DirewolfAttackGoal extends Goal {
    private final Direwolf_Entity direwolf;
    private int attackProgress = 0;
    private final int attackDuration = 16;
    private boolean isAttacking = false;
    private static final double ATTACK_RANGE = 3.0D;
    private double targetX;
    private double targetY;
    private double targetZ;
    private boolean hasDealtDamage = false;

    public DirewolfAttackGoal(Direwolf_Entity direwolf) {
        this.direwolf = direwolf;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        return !this.direwolf.isAttacking() &&
                this.direwolf.getTarget() != null &&
                this.direwolf.getTarget().isAlive() &&
                this.direwolf.distanceToSqr(this.direwolf.getTarget()) <= ATTACK_RANGE * ATTACK_RANGE;
    }

    @Override
    public void start() {
        this.isAttacking = true;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.direwolf.setIsAttacking(true);
        this.direwolf.swing(InteractionHand.MAIN_HAND);

        playWolfGrowlSound();

        LivingEntity target = this.direwolf.getTarget();
        if (target != null) {
            this.targetX = target.getX();
            this.targetY = target.getEyeY();
            this.targetZ = target.getZ();
        }
    }

    @Override
    public void tick() {
        if (attackProgress < 15) {
            this.direwolf.getLookControl().setLookAt(targetX, targetY, targetZ, 30.0F, 30.0F);
        } else if (attackProgress == 15 && !hasDealtDamage) {
            performSingleTargetAttack();
            playWolfGrowlSound();
            hasDealtDamage = true;
        }

        attackProgress++;
        if (attackProgress >= attackDuration) {
            stop();
        }
    }

    private void performSingleTargetAttack() {
        LivingEntity target = this.direwolf.getTarget();
        if (target != null && target.isAlive()) {
            target.hurt(this.direwolf.level().damageSources().mobAttack(this.direwolf),
                    (float)this.direwolf.getAttributeValue(Attributes.ATTACK_DAMAGE));

            double d0 = target.getX() - this.direwolf.getX();
            double d1 = target.getZ() - this.direwolf.getZ();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
            target.push(d0 / d2 * 2.0D, 0.3D, d1 / d2 * 2.0D);
        }
    }

    private void playWolfGrowlSound() {
        this.direwolf.level().playSound(
                null,
                this.direwolf.getX(),
                this.direwolf.getY(),
                this.direwolf.getZ(),
                SoundEvents.WOLF_GROWL,
                SoundSource.HOSTILE,
                1.0F,
                1.0F
        );
    }

    @Override
    public boolean canContinueToUse() {
        return this.isAttacking && attackProgress < attackDuration;
    }

    @Override
    public void stop() {
        this.isAttacking = false;
        this.direwolf.setIsAttacking(false);
        this.attackProgress = 0;
        this.hasDealtDamage = false;
    }
}
