package net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder;

import net.minecraft.nbt.CompoundTag;

public class AnimalHerderSystem {

    private long    lastCollectionDay = -1;
    private int     breedingItemCount = 0;
    private boolean hasBreedToday     = false;
    private long    lastBreedDay      = -1;

    public long    getLastCollectionDay()          { return lastCollectionDay; }
    public void    setLastCollectionDay(long day)  { this.lastCollectionDay = day; }

    public int     getBreedingItemCount()          { return breedingItemCount; }
    public void    setBreedingItemCount(int count) { this.breedingItemCount = count; }
    public void    decrementBreedingItemCount()    { if (breedingItemCount > 0) breedingItemCount--; }

    public boolean hasBreedToday()                 { return hasBreedToday; }
    public void    setHasBreedToday(boolean bred)  { this.hasBreedToday = bred; }

    public long    getLastBreedDay()               { return lastBreedDay; }
    public void    setLastBreedDay(long day)        { this.lastBreedDay = day; }



    public void saveData(CompoundTag compound) {
        compound.putLong("AnimalHerderLastCollectionDay", lastCollectionDay);
        compound.putInt("AnimalHerderBreedingItemCount", breedingItemCount);
        compound.putBoolean("AnimalHerderHasBreedToday", hasBreedToday);
        compound.putLong("AnimalHerderLastBreedDay", lastBreedDay);
    }

    public void loadData(CompoundTag compound) {
        lastCollectionDay = compound.contains("AnimalHerderLastCollectionDay")
                ? compound.getLong("AnimalHerderLastCollectionDay") : -1;
        breedingItemCount = compound.getInt("AnimalHerderBreedingItemCount");
        hasBreedToday     = compound.getBoolean("AnimalHerderHasBreedToday");
        lastBreedDay      = compound.contains("AnimalHerderLastBreedDay")
                ? compound.getLong("AnimalHerderLastBreedDay") : -1;
    }
}