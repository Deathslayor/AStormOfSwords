package net.darkflameproduction.agotmod.entity.custom.npc.system.trader;

import net.minecraft.nbt.CompoundTag;

public class TraderSystem {

    public enum TraderState {
        GOING_TO_JOB_BLOCK,
        WORKING
    }

    private TraderState currentState = TraderState.GOING_TO_JOB_BLOCK;

    public TraderState getCurrentState()           { return currentState; }
    public void setCurrentState(TraderState state) { this.currentState = state; }

    public void saveData(CompoundTag tag) {
        tag.putString("TraderState", currentState.name());
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("TraderState")) {
            try {
                currentState = TraderState.valueOf(tag.getString("TraderState"));
            } catch (IllegalArgumentException e) {
                currentState = TraderState.GOING_TO_JOB_BLOCK;
            }
        }
    }
}