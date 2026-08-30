package net.darkflameproduction.agotmod.entity.custom.npc.system.quarry;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.minecraft.nbt.CompoundTag;

public class QuarrySystem {
    private final Peasant_Entity peasant;

    public enum QuarryState {
        GOING_TO_JOB_BLOCK,
        MINING
    }

    private QuarryState currentQuarryState = QuarryState.GOING_TO_JOB_BLOCK;

    public QuarrySystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (!peasant.getJobType().equals(JobSystem.JOB_QUARRY)) return;
    }

    public QuarryState getCurrentQuarryState() {
        return currentQuarryState;
    }

    public void setCurrentQuarryState(QuarryState state) {
        this.currentQuarryState = state;
    }

    public void saveData(CompoundTag compound) {
        compound.putString("CurrentQuarryState", currentQuarryState.name());
    }

    public void loadData(CompoundTag compound) {
        try {
            currentQuarryState = QuarryState.valueOf(compound.getString("CurrentQuarryState"));
        } catch (IllegalArgumentException e) {
            currentQuarryState = QuarryState.GOING_TO_JOB_BLOCK;
        }
    }
}
