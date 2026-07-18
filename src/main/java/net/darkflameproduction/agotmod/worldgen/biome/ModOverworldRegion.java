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

            // ── LANDS BEYOND THE WALL ────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.ICE_SPIKES, ModBiomes.THE_WALL);

            // ── HAUNTED FOREST ───────────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_TAIGA, ModBiomes.HAUNTED_FOREST_CLEARING);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_BEACH, ModBiomes.HAUNTED_OPEN_WOODLAND);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FROZEN_RIVER, ModBiomes.HAUNTED_FOREST_EDGE);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.CHERRY_GROVE, ModBiomes.HAUNTED_FOREST_MAIN);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MUSHROOM_FIELDS, ModBiomes.HAUNTED_BROADLEAF_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DRIPSTONE_CAVES, ModBiomes.HAUNTED_DEEP_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.LUSH_CAVES, ModBiomes.HAUNTED_PINE_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_DARK, ModBiomes.HAUNTED_IRONWOOD_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.JUNGLE, ModBiomes.HAUNTED_ANCIENT_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BAMBOO_JUNGLE, ModBiomes.HAUNTED_DENSE_IRONWOOD);

            // ── NORTHERN FOREST ──────────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FLOWER_FOREST, ModBiomes.NORTHERN_FOREST_CLEARING);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SPARSE_JUNGLE, ModBiomes.NORTHERN_OPEN_WOODLAND);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS, ModBiomes.NORTHERN_CONIFER_SCRUB);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WINDSWEPT_FOREST, ModBiomes.NORTHERN_FOREST_EDGE);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.TAIGA, ModBiomes.NORTHERN_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FOREST, ModBiomes.NORTHERN_BROADLEAF_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BIRCH_FOREST, ModBiomes.NORTHERN_BEECH_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.GROVE, ModBiomes.NORTHERN_PINE_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, ModBiomes.NORTHERN_IRONWOOD_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.OLD_GROWTH_PINE_TAIGA, ModBiomes.NORTHERN_DENSE_IRONWOOD);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DARK_FOREST, ModBiomes.NORTHERN_DEEP_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.OLD_GROWTH_BIRCH_FOREST, ModBiomes.NORTHERN_ANCIENT_FOREST);

            // ── WOLFSWOOD ────────────────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SAVANNA_PLATEAU, ModBiomes.WOLFSWOOD_CLEARING);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WINDSWEPT_SAVANNA, ModBiomes.WOLFSWOOD_OPEN_WOODLAND);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.ERODED_BADLANDS, ModBiomes.WOLFSWOOD_CONIFER_SCRUB);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WOODED_BADLANDS, ModBiomes.WOLFSWOOD_FOREST_EDGE);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BADLANDS, ModBiomes.WOLFSWOOD_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.JUNGLE, ModBiomes.WOLFSWOOD_BROADLEAF_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SPARSE_JUNGLE, ModBiomes.WOLFSWOOD_BEECH_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BAMBOO_JUNGLE, ModBiomes.WOLFSWOOD_PINE_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MANGROVE_SWAMP, ModBiomes.WOLFSWOOD_IRONWOOD_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SWAMP, ModBiomes.WOLFSWOOD_DENSE_IRONWOOD);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MUSHROOM_FIELDS, ModBiomes.WOLFSWOOD_DEEP_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.CHERRY_GROVE, ModBiomes.WOLFSWOOD_ANCIENT_FOREST);

            // ── BEAR ISLAND ──────────────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_PEAKS, ModBiomes.BEAR_ISLAND);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MEADOW, ModBiomes.BEAR_ISLAND_CLEARING);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SUNFLOWER_PLAINS, ModBiomes.BEAR_ISLAND_OPEN_WOODLAND);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BEACH, ModBiomes.BEAR_ISLAND_CONIFER_SCRUB);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SNOWY_BEACH, ModBiomes.BEAR_ISLAND_FOREST_EDGE);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FROZEN_RIVER, ModBiomes.BEAR_ISLAND_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.LUKEWARM_OCEAN, ModBiomes.BEAR_ISLAND_BROADLEAF_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_LUKEWARM_OCEAN, ModBiomes.BEAR_ISLAND_BEECH_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WARM_OCEAN, ModBiomes.BEAR_ISLAND_PINE_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_FROZEN_OCEAN, ModBiomes.BEAR_ISLAND_IRONWOOD_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_COLD_OCEAN, ModBiomes.BEAR_ISLAND_DENSE_IRONWOOD);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_OCEAN, ModBiomes.BEAR_ISLAND_ANCIENT_FOREST);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_LUKEWARM_OCEAN, ModBiomes.BEAR_ISLAND_DEEP_FOREST);

            // ── NORTHERN PLAINS & HILLS ──────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WINDSWEPT_HILLS, ModBiomes.NORTHERN_HILLS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.JAGGED_PEAKS, ModBiomes.NORTHERN_MOUNTAINS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SAVANNA, ModBiomes.BARROWLANDS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.PLAINS, ModBiomes.NORTHERN_PLAINS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SUNFLOWER_PLAINS, ModBiomes.RILLS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.RIVER, ModBiomes.NORTHERN_WATERS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.STONY_SHORE, ModBiomes.STONY_SHORES);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.MANGROVE_SWAMP, ModBiomes.THE_NECK);

            // ── THE GIFT ─────────────────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.COLD_OCEAN, ModBiomes.THE_GIFT_TUNDRA);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.FROZEN_OCEAN, ModBiomes.THE_GIFT_BARRENS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_COLD_OCEAN, ModBiomes.THE_GIFT_WOODS);

            // ── THE NEW GIFT ──────────────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.OCEAN, ModBiomes.THE_NEW_GIFT_TUNDRA);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_OCEAN, ModBiomes.THE_NEW_GIFT_BARRENS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.LUKEWARM_OCEAN, ModBiomes.THE_NEW_GIFT_WOODS);

            // ── UMBER DOMAIN ─────────────────────────────────────────────────
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.WARM_OCEAN, ModBiomes.UMBER_DOMAIN_TUNDRA);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.PLAINS, ModBiomes.UMBER_DOMAIN_BARRENS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DEEP_LUKEWARM_OCEAN, ModBiomes.UMBER_DOMAIN_WOODS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SUNFLOWER_PLAINS, ModBiomes.UMBER_DOMAIN_DEEP_TUNDRA);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DESERT, ModBiomes.UMBER_DOMAIN_DEEP_BARRENS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.SAVANNA, ModBiomes.UMBER_DOMAIN_DEEP_WOODS);

            // ── SKAGOS ───────────────────────────────────────────────────────
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
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.BIRCH_FOREST, ModBiomes.SKAGOS_VALLEY);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.DARK_FOREST, ModBiomes.SKAGOS_WOODS);
            modifiedVanillaOverworldBuilder.replaceBiome(Biomes.TAIGA, ModBiomes.SKAGOS_WOODS);
        });
    }
}