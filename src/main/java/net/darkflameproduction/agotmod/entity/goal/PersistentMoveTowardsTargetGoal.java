package net.darkflameproduction.agotmod.entity.goal;

import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;

public class PersistentMoveTowardsTargetGoal extends MoveTowardsTargetGoal {
    private final Mammoth_Entity mammoth;
    
    public PersistentMoveTowardsTargetGoal(Mammoth_Entity mammoth, double speedModifier, float maxDistance) {
        super(mammoth, speedModifier, maxDistance);
        this.mammoth = mammoth;
    }
    
    @Override
    public boolean canUse() {
        boolean canUse = super.canUse();
        if (canUse) {
            mammoth.setSprinting(true);
        }
        return canUse;
    }
    
    @Override
    public void stop() {
        super.stop();
        mammoth.setSprinting(false);
    }
    
    @Override
    public boolean canContinueToUse() {
        boolean shouldContinue = super.canContinueToUse();
        mammoth.setSprinting(shouldContinue);
        return shouldContinue;
    }
}