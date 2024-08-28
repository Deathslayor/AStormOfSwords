package net.astormofsorts.agotmod.worldgen;

import net.astormofsorts.agotmod.mixin.TrunkPlacerTypeInvoker;
import net.astormofsorts.agotmod.worldgen.tree.NBTBasedTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<NBTBasedTrunkPlacer> NBT_BASED_TRUNK_PLACER_TYPE = TrunkPlacerTypeInvoker.callRegister("nbt_based_trunk_placer", NBTBasedTrunkPlacer.CODEC);
}
