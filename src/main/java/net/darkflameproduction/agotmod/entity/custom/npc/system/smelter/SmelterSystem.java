package net.darkflameproduction.agotmod.entity.custom.npc.system.smelter;

import net.minecraft.nbt.CompoundTag;

public class SmelterSystem {

    public enum SmelterState {
        GOING_TO_JOB_BLOCK,
        SMELTING
    }

    private SmelterState currentState = SmelterState.GOING_TO_JOB_BLOCK;
    private long lastCollectionDay = -1;

    // How much coal was collected this morning (determines max ore capacity)
    private int coalCollected = 0;
    // How many ore slots have been consumed today (1 coal = 8 ore)
    private int oreSlotsFilled = 0;

    public SmelterState getCurrentState() { return currentState; }
    public void setCurrentState(SmelterState state) { this.currentState = state; }

    public long getLastCollectionDay() { return lastCollectionDay; }
    public void setLastCollectionDay(long day) { this.lastCollectionDay = day; }

    public int getCoalCollected() { return coalCollected; }
    public void setCoalCollected(int coal) { this.coalCollected = coal; }

    public int getMaxOreSlots() { return coalCollected * 8; }

    public int getOreSlotsFilled() { return oreSlotsFilled; }
    public void setOreSlotsFilled(int slots) { this.oreSlotsFilled = slots; }
    public void addOreSlotsFilled(int amount) { this.oreSlotsFilled += amount; }
    public boolean hasOreCapacity() { return oreSlotsFilled < getMaxOreSlots(); }
    public int getRemainingOreSlots() { return Math.max(0, getMaxOreSlots() - oreSlotsFilled); }

    public void saveData(CompoundTag tag) {
        tag.putString("SmelterState", currentState.name());
        tag.putLong("SmelterLastCollectionDay", lastCollectionDay);
        tag.putInt("SmelterCoalCollected", coalCollected);
        tag.putInt("SmelterOreSlotsFilled", oreSlotsFilled);
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("SmelterState")) {
            try {
                currentState = SmelterState.valueOf(tag.getString("SmelterState"));
            } catch (IllegalArgumentException e) {
                currentState = SmelterState.GOING_TO_JOB_BLOCK;
            }
        }
        if (tag.contains("SmelterLastCollectionDay")) lastCollectionDay = tag.getLong("SmelterLastCollectionDay");
        if (tag.contains("SmelterCoalCollected"))     coalCollected     = tag.getInt("SmelterCoalCollected");
        if (tag.contains("SmelterOreSlotsFilled"))    oreSlotsFilled    = tag.getInt("SmelterOreSlotsFilled");
    }
}