package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;

import java.util.EnumSet;

/**
 * Goal that handles guard combat behavior against monsters
 * Similar to PeasantDefenseGoal but specifically for guards hunting monsters
 */
public class GuardCombatGoal extends Goal {
    private final Peasant_Entity guard;
    private LivingEntity target;
    private int attackCooldown = 0;
    private int attackProgress = 0;
    private final int attackDuration = 8; // Match the actual animation length
    private boolean isAttacking = false;
    private static final double ATTACK_RANGE = 3.0D; // Attack range
    private static final double CHASE_RANGE = 20.0D; // Range to chase monsters (longer than peasant defense)
    private boolean hasDealtDamage = false;

    public GuardCombatGoal(Peasant_Entity guard) {
        this.guard = guard;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Only use if we're a guard
        if (!guard.getJobType().equals(JobSystem.JOB_GUARD)) {
            return false;
        }

        // Don't fight if sleeping, eating, or interacting
        if (guard.isSleeping() ||
                guard.getHungerSystem().isEating() ||
                guard.isInteractingWithPlayer() ||
                guard.isPlayingInteractAnimation()) {
            return false;
        }

        // Don't fight if we should be sleeping
        if (guard.getGuardSystem().shouldSleep()) {
            return false;
        }

        // Get target from guard system (it scans for monsters)
        this.target = guard.getGuardSystem().getCurrentTarget();

        // Must have a monster target
        return this.target != null &&
                this.target.isAlive() &&
                this.target instanceof Monster;
    }

    @Override
    public boolean canContinueToUse() {
        // Stop if no longer a guard
        if (!guard.getJobType().equals(JobSystem.JOB_GUARD)) {
            return false;
        }

        // Stop if we should be sleeping
        if (guard.getGuardSystem().shouldSleep()) {
            return false;
        }

        // Stop if eating or interacting
        if (guard.getHungerSystem().isEating() ||
                guard.isInteractingWithPlayer() ||
                guard.isPlayingInteractAnimation()) {
            return false;
        }

        // Check if target is still valid
        if (target == null || !target.isAlive() || !(target instanceof Monster)) {
            return false;
        }

        // Stop if target is too far away (guards don't chase indefinitely)
        double distanceToTarget = guard.distanceToSqr(target);
        if (distanceToTarget > CHASE_RANGE * CHASE_RANGE) {
            return false;
        }

        // Update target from guard system in case it changed
        LivingEntity currentSystemTarget = guard.getGuardSystem().getCurrentTarget();
        if (currentSystemTarget != null && currentSystemTarget != target) {
            target = currentSystemTarget;
        }

        return true;
    }

    @Override
    public void start() {
        guard.setAggressive(true);
        guard.setTarget(target); // Set for vanilla AI systems
        this.attackCooldown = 0;
        this.isAttacking = false;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
    }

    @Override
    public void stop() {
        guard.setAggressive(false);
        guard.setIsAttacking(false);
        guard.setTarget(null); // Clear vanilla target
        this.isAttacking = false;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.target = null;
        this.attackCooldown = 40; // Short cooldown after combat ends
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

        double distanceToTarget = this.guard.distanceToSqr(this.target);

        // Always look at target during combat
        this.guard.getLookControl().setLookAt(this.target, 30.0F, 30.0F);

        if (this.isAttacking) {
            // Handle ongoing attack
            handleAttackSequence();
        } else {
            // Not currently attacking - decide what to do
            if (distanceToTarget <= ATTACK_RANGE * ATTACK_RANGE && this.attackCooldown <= 0) {
                // Close enough to attack and cooldown is ready
                startNewAttack();
            } else {
                // Move towards target - guards are more aggressive (faster movement)
                this.guard.getNavigation().moveTo(this.target, 0.8D);
            }
        }
    }

    private void startNewAttack() {
        this.isAttacking = true;
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.guard.setIsAttacking(true);
        this.guard.swing(InteractionHand.MAIN_HAND);
        // Stop movement during attack
        this.guard.getNavigation().stop();
    }

    private void handleAttackSequence() {
        this.attackProgress++;

        // Deal damage at tick 6 (about 75% through the 8-tick animation)
        if (this.attackProgress == 6 && !this.hasDealtDamage) {
            performAttack();
            this.hasDealtDamage = true;
        }

        // Complete the full attack sequence
        if (this.attackProgress >= this.attackDuration) {
            endCurrentAttack();
        }
    }

    private void endCurrentAttack() {
        this.isAttacking = false;
        this.guard.setIsAttacking(false);
        this.attackProgress = 0;
        this.hasDealtDamage = false;
        this.attackCooldown = 15; // Shorter cooldown for guards (more aggressive)

        // Resume movement after attack
        double distanceToTarget = this.guard.distanceToSqr(this.target);
        if (distanceToTarget > ATTACK_RANGE * ATTACK_RANGE) {
            this.guard.getNavigation().moveTo(this.target, 0.8D);
        }
    }

    private void performAttack() {
        // Check if target is still in range
        if (this.guard.distanceToSqr(this.target) <= ATTACK_RANGE * ATTACK_RANGE) {
            // Deal damage to target - guards hit harder than regular peasants
            float damage = (float) this.guard.getAttributeValue(Attributes.ATTACK_DAMAGE);
            // Guards deal 25% more damage to monsters
            damage *= 1.25f;

            this.target.hurt(this.guard.level().damageSources().mobAttack(this.guard), damage);

            // Apply knockback to target
            double d0 = this.target.getX() - this.guard.getX();
            double d1 = this.target.getZ() - this.guard.getZ();
            double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
            this.target.push(d0 / d2 * 1.2D, 0.3D, d1 / d2 * 1.2D); // Stronger knockback than peasants
        }
        // If target is out of range, the swing misses but animation still completes
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}