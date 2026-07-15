package net.darkflameproduction.agotmod.worldgen.biome;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.darkflameproduction.agotmod.worldgen.ModplacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {
    public static final ResourceKey<Biome> LANDS_OF_ALWAYS_WINTER = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("lands_of_always_winter"));
    public static final ResourceKey<Biome> HAUNTED_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("haunted_forest"));
    public static final ResourceKey<Biome> FROSTFANG_FOOTHILLS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("frostfang_foothills"));
    public static final ResourceKey<Biome> FROSTFANGS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("frostfangs"));
    public static final ResourceKey<Biome> VALLEY_OF_THENN = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("valley_of_thenn"));
    public static final ResourceKey<Biome> NORTHERN_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_forest"));
    public static final ResourceKey<Biome> NORTHERN_DEEP_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_deep_forest"));
    public static final ResourceKey<Biome> NORTHERN_IRONWOOD_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_ironwood_forest"));
    public static final ResourceKey<Biome> NORTHERN_DENSE_IRONWOOD = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_dense_ironwood"));
    public static final ResourceKey<Biome> NORTHERN_BROADLEAF_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_broadleaf_forest"));
    public static final ResourceKey<Biome> NORTHERN_BEECH_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_beech_forest"));
    public static final ResourceKey<Biome> NORTHERN_PINE_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_pine_forest"));
    public static final ResourceKey<Biome> NORTHERN_ANCIENT_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_ancient_forest"));
    public static final ResourceKey<Biome> NORTHERN_FOREST_EDGE = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_forest_edge"));
    public static final ResourceKey<Biome> NORTHERN_FOREST_CLEARING = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_forest_clearing"));
    public static final ResourceKey<Biome> NORTHERN_OPEN_WOODLAND = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_open_woodland"));
    public static final ResourceKey<Biome> NORTHERN_CONIFER_SCRUB = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_conifer_scrub"));
    public static final ResourceKey<Biome> NORTHERN_HILLS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_hills"));
    public static final ResourceKey<Biome> NORTHERN_MOUNTAINS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_mountains"));
    public static final ResourceKey<Biome> BARROWLANDS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("barrowlands"));
    public static final ResourceKey<Biome> THE_NORTH = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("the_north"));
    public static final ResourceKey<Biome> RILLS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("rills"));
    public static final ResourceKey<Biome> NORTHERN_WATERS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_waters"));
    public static final ResourceKey<Biome> STONY_SHORES = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("stony_shores"));
    public static final ResourceKey<Biome> THE_NECK = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("the_neck"));
    public static final ResourceKey<Biome> SKAGOS_TUNDRA = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_tundra"));
    public static final ResourceKey<Biome> SKAGOS_WOODS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_woods"));
    public static final ResourceKey<Biome> SKAGOS_BARRENS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_barrens"));
    public static final ResourceKey<Biome> SKAGOS_HILLS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_hills"));
    public static final ResourceKey<Biome> SKAGOS_VALLEY = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_valley"));
    public static final ResourceKey<Biome> SKAGOS_MOUNTAINS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_mountains"));
    public static final ResourceKey<Biome> SKAGOS_FROZEN_PEAKS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_frozen_peaks"));
    public static final ResourceKey<Biome> SKAGOS_RIVER = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_river"));
    public static final ResourceKey<Biome> SKAGOS_BOG = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("skagos_bog"));

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(LANDS_OF_ALWAYS_WINTER, alwayswinter(context));
        context.register(HAUNTED_FOREST, hauntedforest(context));
        context.register(FROSTFANG_FOOTHILLS, frostfangfoothills(context));
        context.register(FROSTFANGS, frostfangs(context));
        context.register(VALLEY_OF_THENN, valleyofthenn(context));
        context.register(NORTHERN_FOREST, northernForest(context));
        context.register(NORTHERN_DEEP_FOREST, northernDeepForest(context));
        context.register(NORTHERN_IRONWOOD_FOREST, northernIronwoodForest(context));
        context.register(NORTHERN_DENSE_IRONWOOD, northernDenseIronwood(context));
        context.register(NORTHERN_BROADLEAF_FOREST, northernBroadleafForest(context));
        context.register(NORTHERN_BEECH_FOREST, northernBeechForest(context));
        context.register(NORTHERN_PINE_FOREST, northernPineForest(context));
        context.register(NORTHERN_ANCIENT_FOREST, northernAncientForest(context));
        context.register(NORTHERN_FOREST_EDGE, northernForestEdge(context));
        context.register(NORTHERN_FOREST_CLEARING, northernForestClearing(context));
        context.register(NORTHERN_OPEN_WOODLAND, northernOpenWoodland(context));
        context.register(NORTHERN_CONIFER_SCRUB, northernConiferScrub(context));
        context.register(NORTHERN_HILLS, northernhills(context));
        context.register(NORTHERN_MOUNTAINS, northernmountains(context));
        context.register(BARROWLANDS, barrowlands(context));
        context.register(THE_NORTH, the_north(context));
        context.register(RILLS, rills(context));
        context.register(NORTHERN_WATERS, northernwaters(context));
        context.register(STONY_SHORES, stonyshores(context));
        context.register(THE_NECK, theneck(context));
        context.register(SKAGOS_TUNDRA, skagosTundra(context));
        context.register(SKAGOS_WOODS, skagosWoods(context));
        context.register(SKAGOS_BARRENS, skagosBarrens(context));
        context.register(SKAGOS_HILLS, skagosHills(context));
        context.register(SKAGOS_VALLEY, skagosValley(context));
        context.register(SKAGOS_MOUNTAINS, skagosMountains(context));
        context.register(SKAGOS_FROZEN_PEAKS, skagosFrozenPeaks(context));
        context.register(SKAGOS_RIVER, skagosRiver(context));
        context.register(SKAGOS_BOG, skagosBog(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    // =========================================================================
    // MASTER FEATURE ORDER
    // All biomes must add features in this exact relative order:
    //
    // RIVER FEATURES (if any): CLAY_PATCH → QUAGMIRE_PATCH → SEAGRASS → KELP
    // TREES (dense):  ASH → CHESTNUT → IRONWOOD → SENTINEL → FIR → PINE → SOLDIER_PINE → WEIRWOOD → OAK2 → HAWTHORN → BEECH
    // TREES (rare):   ASH_RARE → CHESTNUT_RARE → IRONWOOD_RARE → SENTINEL_RARE → FIR_RARE → PINE_RARE → SOLDIER_PINE_RARE → WEIRWOOD_RARE → OAK2_RARE → HAWTHORN_RARE → BEECH_RARE
    // FLOWERS:        FORGET_ME_NOT → FROSTFIRE → LIVERWORT → LUNGWORT → PENNYROYAL → RED_ROSE_BUSH → THISTLE → THORN_BUSH → WINTER_ROSE_BUSH
    // SEDGE (after THORN_BUSH, before GRASS)
    // GRASS:          GRASS → TALL_GRASS
    // FERNS:          FERN → LARGE_FERN
    // =========================================================================

    private static void addRiverFeatures(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.MUD_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEAGRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.KELP_KEY);
    }

    private static void addModOres(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TIN_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.IRON_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.COAL_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.COPPER_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.GOLD_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.LAPIS_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.EMERALD_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.DIAMONDS_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.BLOODSTONE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CHALCEDONY_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.AMBER_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.AMETHYST_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CARNELIAN_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.GARNET_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.JADE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.JASPER_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.MALACHITE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.RUBY_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.ONYX_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.OPAL_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.SAPPHIRE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.MOONSTONE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TIGERS_EYE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TOPAZ_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TOURMALINE_ORE_PLACED_KEY);
    }

    // =========================================================================
    // NORTHERN FOREST VEGETATION METHODS
    // =========================================================================

    private static void addVegetationNorthernForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernDeepForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernIronwoodForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernDenseIronwood(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernBroadleafForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernBeechForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernPineForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernAncientForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernForestEdge(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernForestClearing(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernOpenWoodland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addVegetationNorthernConiferScrub(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    // =========================================================================
    // NON-FOREST VEGETATION METHODS
    // =========================================================================

    private static void addVegetationNorthernHills(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
    }

    private static void addVegetationBarrowlands(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
    }

    private static void addVegetationTheNorth(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
    }

    private static void addVegetationRills(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
    }

    private static void addVegetationStonyShores(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
    }

    // =========================================================================
    // SKAGOS VEGETATION
    // =========================================================================

    private static void addSkagosVegetation(
            BiomeGenerationSettings.Builder biomeBuilder,
            BootstrapContext<Biome> context,
            boolean includeTrees,
            boolean denseTrees,
            boolean includeFlowers,
            boolean includeGrass,
            boolean includeSedge,
            boolean includeFerns,
            boolean includeWeirwood
    ) {
        if (includeTrees) {
            if (denseTrees) {
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
            } else {
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
                biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            }
        }
        if (includeWeirwood) {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_RARE_KEY);
        }
        if (includeFlowers) {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        }
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        if (includeFlowers) {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        }
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        if (includeSedge) {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        }
        if (includeGrass) {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        }
        if (includeFerns) {
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
            biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
        }
    }

    private static void addSkagosTundra(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        addSkagosVegetation(biomeBuilder, context, true, false, false, true, true, false, false);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TOR_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addSkagosWoods(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        addSkagosVegetation(biomeBuilder, context, true, true, true, true, false, true, true);
    }

    private static void addSkagosBarrens(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        addSkagosVegetation(biomeBuilder, context, false, false, false, true, true, false, false);
    }

    private static void addSkagosHills(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        addSkagosVegetation(biomeBuilder, context, true, false, false, true, true, false, false);
    }

    private static void addSkagosValley(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        addSkagosVegetation(biomeBuilder, context, true, false, true, true, true, true, true);
    }

    private static void addSkagosMountains(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
    }

    private static void addSkagosFrozenPeaks(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
    }

    private static void addSkagosBog(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.MUD_PATCH_PLACED_KEY);
        addSkagosVegetation(biomeBuilder, context, true, false, false, true, true, true, false);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.REED_KEY);

    }

    // =========================================================================
    // BIOME DEFINITIONS
    // =========================================================================

    public static Biome alwayswinter(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-1.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0xd9d9d9)
                        .foliageColorOverride(0xd9d9d9)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome hauntedforest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addForestHauntedForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0x577a5f)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    private static void addForestHauntedForest(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    public static Biome frostfangfoothills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0x577a5f)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome frostfangs(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-1f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0x577a5f)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome valleyofthenn(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        addModOres(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x5a6ef2)
                        .grassColorOverride(0x5c9967)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MAMMOTH_SOUNDS)).build())
                .build();
    }

    // =========================================================================
    // NORTHERN FOREST BIOMES
    // =========================================================================

    public static Biome northernForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x042304)
                        .foliageColorOverride(0x031A03)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernDeepForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernDeepForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x0B2B0B)
                        .foliageColorOverride(0x092409)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernIronwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernIronwoodForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x123512)
                        .foliageColorOverride(0x102D10)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernDenseIronwood(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernDenseIronwood(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x194019)
                        .foliageColorOverride(0x153716)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernBroadleafForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernBroadleafForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x214921)
                        .foliageColorOverride(0x1C401B)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernBeechForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernBeechForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x2A5228)
                        .foliageColorOverride(0x244A23)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernPineForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernPineForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x335A30)
                        .foliageColorOverride(0x2D512B)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernAncientForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernAncientForest(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x3A6335)
                        .foliageColorOverride(0x345A30)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernForestEdge(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernForestEdge(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x426B3B)
                        .foliageColorOverride(0x3A6134)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernForestClearing(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernForestClearing(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x496F40)
                        .foliageColorOverride(0x41663A)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernOpenWoodland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernOpenWoodland(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x527747)
                        .foliageColorOverride(0x496D40)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernConiferScrub(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernConiferScrub(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x5A7D4E)
                        .foliageColorOverride(0x527348)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    // =========================================================================
    // OTHER NORTHERN BIOMES
    // =========================================================================

    public static Biome northernhills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernHills(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.2f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernmountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome barrowlands(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBarrowlands(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome the_north(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationTheNorth(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome rills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        addVegetationRills(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x35a641)
                        .foliageColorOverride(0x35a641)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome northernwaters(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome stonyshores(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationStonyShores(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome theneck(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMangroveSwampVegetation(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x294018)
                        .waterFogColor(0x1f1308)
                        .skyColor(0x30523b)
                        .grassColorOverride(0x27471f)
                        .foliageColorOverride(0x27471f)
                        .fogColor(0x638a6d)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    // =========================================================================
    // SKAGOS BIOMES
    // =========================================================================

    public static Biome skagosTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosTundra(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.85f)
                .temperature(-0.45f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x8f97c4)
                        .grassColorOverride(0x8a9582)
                        .foliageColorOverride(0x6e7867)
                        .fogColor(0xc9ced7)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome skagosWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosWoods(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.9f)
                .temperature(-0.25f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x8a94c4)
                        .grassColorOverride(0x61755b)
                        .foliageColorOverride(0x455843)
                        .fogColor(0x9fa7b4)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome skagosBarrens(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosBarrens(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.7f)
                .temperature(-0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x98a0c8)
                        .grassColorOverride(0x909090)
                        .foliageColorOverride(0x777777)
                        .fogColor(0xd7d7d7)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome skagosHills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosHills(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-0.55f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x9099c8)
                        .grassColorOverride(0x7b866f)
                        .foliageColorOverride(0x66715c)
                        .fogColor(0xbec4cd)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome skagosValley(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosValley(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.95f)
                .temperature(-0.1f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x8895c6)
                        .grassColorOverride(0x5c7d55)
                        .foliageColorOverride(0x476444)
                        .fogColor(0xa4adb9)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome skagosMountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosMountains(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.75f)
                .temperature(-0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x9ba4cc)
                        .grassColorOverride(0xc4c4c4)
                        .foliageColorOverride(0xaaaaaa)
                        .fogColor(0xe6e6e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome skagosFrozenPeaks(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosFrozenPeaks(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-1.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xb5c2e0)
                        .grassColorOverride(0xececec)
                        .foliageColorOverride(0xececec)
                        .fogColor(0xffffff)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    public static Biome skagosRiver(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.2f)
                .downfall(0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x315d7d)
                        .waterFogColor(0x1b3850)
                        .skyColor(0x647c91)
                        .grassColorOverride(0x566b4b)
                        .foliageColorOverride(0x4f6245)
                        .fogColor(0x8294a3)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND))
                        .build())
                .build();
    }

    public static Biome skagosBog(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosBog(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-0.45f)
                .downfall(0.85f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x8f97c4)
                        .grassColorOverride(0x8a9582)
                        .foliageColorOverride(0x6e7867)
                        .fogColor(0xc9ced7)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND))
                        .build())
                .build();
    }
}