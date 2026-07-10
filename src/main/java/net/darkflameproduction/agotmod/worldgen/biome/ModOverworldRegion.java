package net.darkflameproduction.agotmod.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region {
    public ModOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_PLAINS, ModBiomes.LANDS_OF_ALWAYS_WINTER);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_TAIGA, ModBiomes.HAUNTED_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_SLOPES, ModBiomes.FROSTFANG_FOOTHILLS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FROZEN_PEAKS, ModBiomes.FROSTFANGS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MEADOW, ModBiomes.VALLEY_OF_THENN);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.TAIGA, ModBiomes.WOLFSWOOD);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, ModBiomes.IRONWOOD);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.TAIGA, ModBiomes.WOLFSWOOD_CLEARING);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WINDSWEPT_HILLS, ModBiomes.NORTHERN_HILLS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.JAGGED_PEAKS, ModBiomes.NORTHERN_MOUNTAINS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SAVANNA, ModBiomes.BARROWLANDS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.PLAINS, ModBiomes.THE_NORTH);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.PLAINS, ModBiomes.RILLS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.RIVER, ModBiomes.NORTHERN_WATERS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_SHORE, ModBiomes.STONY_SHORES);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MANGROVE_SWAMP, ModBiomes.THE_NECK);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_PLAINS, ModBiomes.SKAGOS_TUNDRA);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_TAIGA, ModBiomes.SKAGOS_WOODS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, ModBiomes.SKAGOS_WOODS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_SLOPES, ModBiomes.SKAGOS_HILLS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FROZEN_PEAKS, ModBiomes.SKAGOS_FROZEN_PEAKS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.JAGGED_PEAKS, ModBiomes.SKAGOS_MOUNTAINS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MEADOW, ModBiomes.SKAGOS_VALLEY);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.PLAINS, ModBiomes.SKAGOS_TUNDRA);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WINDSWEPT_HILLS, ModBiomes.SKAGOS_HILLS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WINDSWEPT_FOREST, ModBiomes.SKAGOS_WOODS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_PEAKS, ModBiomes.SKAGOS_BARRENS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_SHORE, ModBiomes.SKAGOS_BARRENS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SWAMP, ModBiomes.SKAGOS_BOG);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MANGROVE_SWAMP, ModBiomes.SKAGOS_BOG);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FOREST, ModBiomes.SKAGOS_VALLEY);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BIRCH_FOREST, ModBiomes.SKAGOS_VALLEY);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DARK_FOREST, ModBiomes.SKAGOS_WOODS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.TAIGA, ModBiomes.SKAGOS_WOODS);
        });
    }
}
