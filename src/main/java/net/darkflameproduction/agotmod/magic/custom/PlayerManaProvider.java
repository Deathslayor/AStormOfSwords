package net.darkflameproduction.agotmod.magic.custom;


import cpw.mods.util.Lazy;
import net.darkflameproduction.agotmod.magic.client.PlayerMana;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class PlayerManaProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<PlayerMana> PLAYER_MANA = CapabilityManager.get(new CapabilityToken<PlayerMana>() {
    });

    private PlayerMana mana = null;
    private final LazyOptional<PlayerMana> optional = LazyOptional.of(this::createPlayerMana);

    private PlayerMana createPlayerMana() {
        if (this.mana == null) {
            this.mana = new PlayerMana();
        }

        return this.mana;
    }


    @Override
    public @NotNull <T> Lazy<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MANA) {
            return optional.cast();
        }


        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerMana().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerMana().loadNBTData(nbt);

    }
}
