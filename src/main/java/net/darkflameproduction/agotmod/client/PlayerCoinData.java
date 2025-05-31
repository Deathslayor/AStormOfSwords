package net.darkflameproduction.agotmod.client;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.common.util.INBTSerializable;

public class PlayerCoinData implements INBTSerializable<CompoundTag> {
    private long coinBalance = 0L;

    public long getCoinBalance() {
        return coinBalance;
    }

    public void setCoinBalance(long balance) {
        this.coinBalance = balance;
    }

    public void addCoins(long amount) {
        this.coinBalance += amount;
        AGoTMod.LOGGER.info("Added {} coins, new balance: {}", amount, this.coinBalance);
    }

    public boolean removeCoins(long amount) {
        if (this.coinBalance >= amount) {
            this.coinBalance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putLong("coin_balance", coinBalance);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        this.coinBalance = tag.getLong("coin_balance");
    }

    public static class Serializer implements IAttachmentSerializer<CompoundTag, PlayerCoinData> {
        @Override
        public PlayerCoinData read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
            PlayerCoinData data = new PlayerCoinData();
            data.deserializeNBT(provider, tag);
            return data;
        }

        @Override
        public CompoundTag write(PlayerCoinData attachment, HolderLookup.Provider provider) {
            return attachment.serializeNBT(provider);
        }
    }

    // Helper methods for easy access
    public static PlayerCoinData get(Player player) {
        return player.getData(ModAttachments.PLAYER_COIN_DATA);
    }

    public static long getBalance(Player player) {
        return get(player).getCoinBalance();
    }

    public static void addCoins(Player player, long amount) {
        get(player).addCoins(amount);
    }

    public static boolean removeCoins(Player player, long amount) {
        return get(player).removeCoins(amount);
    }
}