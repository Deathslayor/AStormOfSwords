package net.darkflameproduction.agotmod.util;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType WEIRWOOD = WoodType.register(new WoodType(AGoTMod.MOD_ID + ":weirwood", BlockSetType.OAK));
    public static final WoodType PINE = WoodType.register(new WoodType(AGoTMod.MOD_ID + ":pine", BlockSetType.OAK));
}
