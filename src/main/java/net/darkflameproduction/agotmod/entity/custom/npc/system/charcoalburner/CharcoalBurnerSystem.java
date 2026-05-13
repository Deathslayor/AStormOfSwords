package net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner;

import net.minecraft.nbt.CompoundTag;

public class CharcoalBurnerSystem {

    public enum CharcoalBurnerState {
        GOING_TO_JOB_BLOCK,
        BURNING
    }

    private CharcoalBurnerState currentState = CharcoalBurnerState.GOING_TO_JOB_BLOCK;
    private long lastCollectionDay = -1;

    // How many logs were collected this morning
    private int logsCollected = 0;
    // How many logs have been burned today
    private int logsBurned = 0;

    public CharcoalBurnerState getCurrentState()               { return currentState; }
    public void setCurrentState(CharcoalBurnerState state)     { this.currentState = state; }

    public long getLastCollectionDay()                         { return lastCollectionDay; }
    public void setLastCollectionDay(long day)                 { this.lastCollectionDay = day; }

    public int getLogsCollected()                              { return logsCollected; }
    public void setLogsCollected(int logs)                     { this.logsCollected = logs; }

    public int getLogsBurned()                                 { return logsBurned; }
    public void setLogsBurned(int logs)                        { this.logsBurned = logs; }
    public void addLogsBurned(int amount)                      { this.logsBurned += amount; }
    public boolean hasLogCapacity()                            { return logsBurned < logsCollected; }
    public int getRemainingLogs()                              { return Math.max(0, logsCollected - logsBurned); }

    public void saveData(CompoundTag tag) {
        tag.putString("CharcoalBurnerState", currentState.name());
        tag.putLong("CharcoalBurnerLastCollectionDay", lastCollectionDay);
        tag.putInt("CharcoalBurnerLogsCollected", logsCollected);
        tag.putInt("CharcoalBurnerLogsBurned", logsBurned);
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("CharcoalBurnerState")) {
            try {
                currentState = CharcoalBurnerState.valueOf(tag.getString("CharcoalBurnerState"));
            } catch (IllegalArgumentException e) {
                currentState = CharcoalBurnerState.GOING_TO_JOB_BLOCK;
            }
        }
        if (tag.contains("CharcoalBurnerLastCollectionDay")) lastCollectionDay = tag.getLong("CharcoalBurnerLastCollectionDay");
        if (tag.contains("CharcoalBurnerLogsCollected"))     logsCollected     = tag.getInt("CharcoalBurnerLogsCollected");
        if (tag.contains("CharcoalBurnerLogsBurned"))        logsBurned        = tag.getInt("CharcoalBurnerLogsBurned");
    }
}