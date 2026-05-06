package net.darkflameproduction.agotmod.entity.custom.npc.system.miner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.minecraft.nbt.CompoundTag;

public class MinerSystem {
    private final Peasant_Entity peasant;

    public enum MinerState {
        GOING_TO_JOB_BLOCK,
        MINING
    }

    private MinerState currentMinerState = MinerState.GOING_TO_JOB_BLOCK;

    public MinerSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) return;
    }

    public MinerState getCurrentMinerState() {
        return currentMinerState;
    }

    public void setCurrentMinerState(MinerState state) {
        this.currentMinerState = state;
    }

    public void saveData(CompoundTag compound) {
        compound.putString("CurrentMinerState", currentMinerState.name());
    }

    public void loadData(CompoundTag compound) {
        try {
            currentMinerState = MinerState.valueOf(compound.getString("CurrentMinerState"));
        } catch (IllegalArgumentException e) {
            currentMinerState = MinerState.GOING_TO_JOB_BLOCK;
        }
    }
}