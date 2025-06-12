package net.darkflameproduction.agotmod.entity.custom.npc.goals.behaviour;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import java.util.EnumSet;

/**
 * Goal that handles peasant defensive combat behavior
 * Peasants will defend themselves when attacked and fight aggressively while moving
 */
public class PeasantDefenseGoal extends Goal {
    private final Peasant_Entity peasant;
    private LivingEntity target;
    private int attackCooldown = 0;
    private int attackProgress = 0;
    private final int attackDuration = 8; // Match the actual animation length (0.4167 seconds = ~8 ticks)
    private boolean isAttacking = false;
    private static final double ATTACK_RANGE = 3.0D; // Attack range
    private static final double CHASE_RANGE = 12.0D; // Range to chase targets
    private boolean hasDealtDamage = false;

    public PeasantDefenseGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        this.target = this.peasant.getTarget();

        // Only defend if we have a target and we're not sleeping/interacting
        return this.target != null &&
                this.target.isAlive() &&
                !this.peasant.isSleeping() &&
                !this.peasant.isInteractingWithPlayer() &&
                !this.peasant.isPlayingInteractAnimation();
    }

    @Override
    public boolean canContinueToUse() {
        return this.target != null &&
                this.target.isAlive() &&
                !this.peasant.isSleeping() &&
                !this.peasant.isInteractingWithPlayer() &&
                !this.peasant.isPlayingInteractAnimation() &&
                this.peasant.distanceToSqr(this.target) <= CHASE_RANGE * CHASE_RANGE;
    }

    @Override
    public void start() {
        this.peasant.setAggressive(true);
        this.attackCooldown = 0;
        this.isAttacking = false;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
    }

    @Override
    public void stop() {
        this.peasant.setAggressive(false);
        this.peasant.setIsAttacking(false);
        this.isAttacking = false;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.target = null;
        this.attackCooldown = 60; // 3 second cooldown after combat ends
    }

    @Override
    public void tick() {
        if (this.target == null) {
            return;
        }

        // Update attack cooldown
        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }

        double distanceToTarget = this.peasant.distanceToSqr(this.target);

        // Always look at target during combat
        this.peasant.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        if (this.isAttacking) {
            // Handle ongoing attack - complete the full animation regardless of hit/miss
            handleAttackSequence();
        } else {
            // Not currently attacking - decide what to do
            if (distanceToTarget <= ATTACK_RANGE * ATTACK_RANGE && this.attackCooldown <= 0) {
                // Close enough to attack and cooldown is ready
                startNewAttack();
            } else {
                // Move towards target at normal walking speed
                this.peasant.getNavigation().moveTo(this.target, 0.7D);
            }
        }
    }

    private void startNewAttack() {
        this.isAttacking = true;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.peasant.setIsAttacking(true);
        this.peasant.swing(InteractionHand.MAIN_HAND);
        // Stop movement during attack
        this.peasant.getNavigation().stop();
    }

    private void handleAttackSequence() {
        this.attackProgress++;

        // Deal damage at tick 6 (about 75% through the 8-tick animation) - but only if target is in range
        if (this.attackProgress == 6 && !this.hasDealtDamage) {
            performAttack(); // This will check range and only deal damage if target is close enough
            this.hasDealtDamage = true; // Mark as attempted regardless of hit/miss
        }

        // ALWAYS complete the full attack sequence regardless of whether we hit or missed
        if (this.attackProgress >= this.attackDuration) {
            endCurrentAttack();
        }
    }

    private void endCurrentAttack() {
        this.isAttacking = false;
        this.peasant.setIsAttacking(false);
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.attackCooldown = 20; // 1 second mandatory cooldown between attacks (prevents spam)

        // Always resume movement after attack, regardless of hit/miss
        double distanceToTarget = this.peasant.distanceToSqr(this.target);
        if (distanceToTarget > ATTACK_RANGE * ATTACK_RANGE) {
            this.peasant.getNavigation().moveTo(this.target, 0.7D);
        }
    }

    private void performAttack() {
        // Check if target is still in range - only deal damage if they are
        if (this.peasant.distanceToSqr(this.target) <= ATTACK_RANGE * ATTACK_RANGE) {
            // Deal damage to target
            float damage = (float) this.peasant.getAttributeValue(Attributes.ATTACK_DAMAGE);
            this.target.hurt(this.peasant.level().damageSources().mobAttack(this.peasant), damage);

            // Apply knockback to target
            double d0 = this.target.getX() - this.peasant.getX();
            double d1 = this.target.getZ() - this.peasant.getZ();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
            this.target.push(d0 / d2 * 1.0D, 0.2D, d1 / d2 * 1.0D);
        }
        // If target is out of range, the swing misses but animation still completes
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}