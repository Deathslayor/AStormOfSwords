package net.darkflameproduction.agotmod.entity.custom.npc.system.tailor;

import net.minecraft.nbt.CompoundTag;

public class TailorSystem {

    public enum TailorState { GOING_TO_JOB_BLOCK, WORKING }

    private TailorState currentState   = TailorState.GOING_TO_JOB_BLOCK;
    private long lastCollectionDay     = -1;
    private int flowersCollected       = 0;

    public TailorState getCurrentState()             { return currentState; }
    public void setCurrentState(TailorState state)   { this.currentState = state; }

    public long getLastCollectionDay()               { return lastCollectionDay; }
    public void setLastCollectionDay(long day)       { this.lastCollectionDay = day; }

    public int getFlowersCollected()                 { return flowersCollected; }
    public void setFlowersCollected(int amount)      { this.flowersCollected = amount; }

    public void saveData(CompoundTag tag) {
        tag.putString("TailorState",           currentState.name());
        tag.putLong("TailorLastCollectionDay", lastCollectionDay);
        tag.putInt("TailorFlowersCollected",   flowersCollected);
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("TailorState")) {
            try { currentState = TailorState.valueOf(tag.getString("TailorState")); }
            catch (IllegalArgumentException e) { currentState = TailorState.GOING_TO_JOB_BLOCK; }
        }
        if (tag.contains("TailorLastCollectionDay")) lastCollectionDay = tag.getLong("TailorLastCollectionDay");
        if (tag.contains("TailorFlowersCollected"))  flowersCollected  = tag.getInt("TailorFlowersCollected");
    }
}
