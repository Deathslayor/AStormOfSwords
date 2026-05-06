package net.darkflameproduction.agotmod.entity.custom.npc.system.tanner;

import net.minecraft.nbt.CompoundTag;

public class TannerSystem {

    public enum TannerState {
        GOING_TO_JOB_BLOCK,
        WORKING
    }

    private TannerState currentState    = TannerState.GOING_TO_JOB_BLOCK;
    private long lastCollectionDay      = -1;
    private int rabbitHidesCollected    = 0;
    private int leatherCollected        = 0;
    private int stringCollected         = 0;
    private int hidesProcessed          = 0; // every 4 hides = 1 leather
    private int hideTicks               = 0; // ticks since last hide processed

    public TannerState getCurrentState()              { return currentState; }
    public void setCurrentState(TannerState state)    { this.currentState = state; }

    public long getLastCollectionDay()                { return lastCollectionDay; }
    public void setLastCollectionDay(long day)        { this.lastCollectionDay = day; }

    public int getRabbitHidesCollected()              { return rabbitHidesCollected; }
    public void setRabbitHidesCollected(int amount)   { this.rabbitHidesCollected = amount; }

    public int getLeatherCollected()                  { return leatherCollected; }
    public void setLeatherCollected(int amount)       { this.leatherCollected = amount; }

    public int getStringCollected()                   { return stringCollected; }
    public void setStringCollected(int amount)        { this.stringCollected = amount; }

    public int getHidesProcessed()                    { return hidesProcessed; }
    public void setHidesProcessed(int amount)         { this.hidesProcessed = amount; }

    public int getHideTicks()                         { return hideTicks; }
    public void setHideTicks(int ticks)               { this.hideTicks = ticks; }
    public void incrementHideTicks()                  { this.hideTicks++; }
    public void resetHideTicks()                      { this.hideTicks = 0; }

    public void saveData(CompoundTag tag) {
        tag.putString("TannerState",             currentState.name());
        tag.putLong("TannerLastCollectionDay",    lastCollectionDay);
        tag.putInt("TannerRabbitHidesCollected",  rabbitHidesCollected);
        tag.putInt("TannerLeatherCollected",      leatherCollected);
        tag.putInt("TannerStringCollected",       stringCollected);
        tag.putInt("TannerHidesProcessed",        hidesProcessed);
        tag.putInt("TannerHideTicks",             hideTicks);
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("TannerState")) {
            try {
                currentState = TannerState.valueOf(tag.getString("TannerState"));
            } catch (IllegalArgumentException e) {
                currentState = TannerState.GOING_TO_JOB_BLOCK;
            }
        }
        if (tag.contains("TannerLastCollectionDay"))   lastCollectionDay   = tag.getLong("TannerLastCollectionDay");
        if (tag.contains("TannerRabbitHidesCollected")) rabbitHidesCollected = tag.getInt("TannerRabbitHidesCollected");
        if (tag.contains("TannerLeatherCollected"))    leatherCollected    = tag.getInt("TannerLeatherCollected");
        if (tag.contains("TannerStringCollected"))     stringCollected     = tag.getInt("TannerStringCollected");
        if (tag.contains("TannerHidesProcessed"))      hidesProcessed      = tag.getInt("TannerHidesProcessed");
        if (tag.contains("TannerHideTicks"))           hideTicks           = tag.getInt("TannerHideTicks");
    }
}