package net.darkflameproduction.agotmod.magic.custom;


import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.magic.client.PlayerMana;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;


public class PlayerManaProvider implements ICapabilityProvider<Player, Void, PlayerMana>, INBTSerializable<CompoundTag> {
    public static final EntityCapability<PlayerMana, Void> PLAYER_MANA = EntityCapability.createVoid(AGoTMod.id("player_mana"), PlayerMana.class);

    private PlayerMana mana = null;

    private PlayerMana createPlayerMana() {
        if (this.mana == null) {
            this.mana = new PlayerMana();
        }

        return this.mana;
    }

    @Override
    public @Nullable PlayerMana getCapability(@NotNull Player player, Void context) {
        return createPlayerMana();
    }

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.@NotNull Provider provider) {
        CompoundTag nbt = new CompoundTag();
        createPlayerMana().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.@NotNull Provider provider, @NotNull CompoundTag nbt) {
        createPlayerMana().loadNBTData(nbt);
    }
}
