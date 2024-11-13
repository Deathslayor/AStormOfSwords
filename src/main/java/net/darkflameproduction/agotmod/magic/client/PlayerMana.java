package net.darkflameproduction.agotmod.magic.client;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private final int MIN_MANA = 0;
    private final int MAX_MANA = 100;

    public int getMana() {
        return mana;
    }

    public void addMana(int add) {
        this.mana = Math.min(mana + add, MAX_MANA);
    }

    public void subMana(int sub) {
        this.mana = Math.max(mana - sub, MIN_MANA);
    }

    public void copyFrom(PlayerMana source) {
        this.mana = source.mana;

    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("mana", mana);
    }


    public void loadNBTData(CompoundTag nbt) {
        mana = nbt.getInt("mana");

    }
}
