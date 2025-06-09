package net.darkflameproduction.agotmod.worldgen.biome;

import net.darkflameproduction.agotmod.AGoTMod;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(AGoTMod.id("overworld"), 5));
    }
}