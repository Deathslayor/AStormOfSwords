package net.stormofsorts.agotmod.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.stormofsorts.agotmod.entity.custom.BearEntity;

/**
 * Custom attack goal for Bear entities.
 */
public class BearAttackGoal extends MeleeAttackGoal {
    // Reference to the Bear entity associated with this goal
    private final BearEntity entity;

    // Cooldown parameters for the attack
    private int attackDelay = 40;
    private int ticksUntilNextAttack = 40;

    // Flag to determine if ticks should be counted until the next attack
    private boolean shouldCountTillNextAttack = false;

    /**
     * Constructor for BearAttackGoal.
     *
     * @param pMob                        The Bear entity using this goal.
     * @param pSpeedModifier              Speed modifier for the attack.
     * @param pFollowingTargetEvenIfNotSeen Flag indicating whether to follow the target even if not seen.
     */
    public BearAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        entity = ((BearEntity) pMob);
    }

    /**
     * Method called when the attack goal starts.
     */
    @Override
    public void start() {
        super.start();
        // Reset attack cooldown parameters
        attackDelay = 40;
        ticksUntilNextAttack = 40;
    }

    /**
     * Main method to check and perform the attack.
     */
    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (isEnemyWithinAttackDistance(pEnemy, pDistToEnemySqr)) {
            shouldCountTillNextAttack = true;

            // Start attack animation
            if (isTimeToStartAttackAnimation()) {
                entity.setAttacking(true);
            }

            // Perform attack if it's time
            if (isTimeToAttack()) {
                this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
                performAttack(pEnemy);
            }
        } else {
            // Reset attack cooldown if the enemy is not in attack range
            resetAttackCooldown();
            shouldCountTillNextAttack = false;
            entity.setAttacking(false);
            entity.attackAnimationTimeout = 0;
        }
    }

    // Helper method to check if the enemy is within attack distance
    private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
        return pDistToEnemySqr <= this.getAttackReachSqr(pEnemy);
    }

    // Helper method to reset the attack cooldown
    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
    }

    // Helper method to check if it's time to perform an attack
    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    // Helper method to check if it's time to start the attack animation
    protected boolean isTimeToStartAttackAnimation() {
        return this.ticksUntilNextAttack <= attackDelay;
    }

    // Helper method to get the remaining ticks until the next attack
    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    // Helper method to perform the attack
    protected void performAttack(LivingEntity pEnemy) {
        this.resetAttackCooldown();
        this.mob.swing(InteractionHand.MAIN_HAND);
        this.mob.doHurtTarget(pEnemy);
    }

    /**
     * Tick method to update the remaining ticks until the next attack.
     */
    @Override
    public void tick() {
        super.tick();
        if (shouldCountTillNextAttack) {
            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
        }
    }

    /**
     * Method called when the attack goal is stopped.
     */
    @Override
    public void stop() {
        entity.setAttacking(false);
        super.stop();
    }
}
