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

    // =========================================================================
    // RESOURCE KEYS
    // =========================================================================

    public static final ResourceKey<Biome> LANDS_OF_ALWAYS_WINTER = ResourceKey.create(Registries.BIOME, AGoTMod.id("lands_of_always_winter"));
    public static final ResourceKey<Biome> HAUNTED_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_forest"));
    public static final ResourceKey<Biome> FROSTFANG_FOOTHILLS = ResourceKey.create(Registries.BIOME, AGoTMod.id("frostfang_foothills"));
    public static final ResourceKey<Biome> FROSTFANGS = ResourceKey.create(Registries.BIOME, AGoTMod.id("frostfangs"));
    public static final ResourceKey<Biome> VALLEY_OF_THENN = ResourceKey.create(Registries.BIOME, AGoTMod.id("valley_of_thenn"));

    // Northern Forest
    public static final ResourceKey<Biome> NORTHERN_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_forest"));
    public static final ResourceKey<Biome> NORTHERN_DEEP_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_deep_forest"));
    public static final ResourceKey<Biome> NORTHERN_IRONWOOD_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_ironwood_forest"));
    public static final ResourceKey<Biome> NORTHERN_DENSE_IRONWOOD = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_dense_ironwood"));
    public static final ResourceKey<Biome> NORTHERN_BROADLEAF_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_broadleaf_forest"));
    public static final ResourceKey<Biome> NORTHERN_BEECH_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_beech_forest"));
    public static final ResourceKey<Biome> NORTHERN_PINE_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_pine_forest"));
    public static final ResourceKey<Biome> NORTHERN_ANCIENT_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_ancient_forest"));
    public static final ResourceKey<Biome> NORTHERN_FOREST_EDGE = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_forest_edge"));
    public static final ResourceKey<Biome> NORTHERN_FOREST_CLEARING = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_forest_clearing"));
    public static final ResourceKey<Biome> NORTHERN_OPEN_WOODLAND = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_open_woodland"));
    public static final ResourceKey<Biome> NORTHERN_CONIFER_SCRUB = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_conifer_scrub"));

    // Wolfswood Forest
    public static final ResourceKey<Biome> WOLFSWOOD_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_forest"));
    public static final ResourceKey<Biome> WOLFSWOOD_DEEP_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_deep_forest"));
    public static final ResourceKey<Biome> WOLFSWOOD_IRONWOOD_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_ironwood_forest"));
    public static final ResourceKey<Biome> WOLFSWOOD_DENSE_IRONWOOD = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_dense_ironwood"));
    public static final ResourceKey<Biome> WOLFSWOOD_BROADLEAF_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_broadleaf_forest"));
    public static final ResourceKey<Biome> WOLFSWOOD_BEECH_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_beech_forest"));
    public static final ResourceKey<Biome> WOLFSWOOD_PINE_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_pine_forest"));
    public static final ResourceKey<Biome> WOLFSWOOD_ANCIENT_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_ancient_forest"));
    public static final ResourceKey<Biome> WOLFSWOOD_FOREST_EDGE = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_forest_edge"));
    public static final ResourceKey<Biome> WOLFSWOOD_CLEARING = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_clearing"));
    public static final ResourceKey<Biome> WOLFSWOOD_OPEN_WOODLAND = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_open_woodland"));
    public static final ResourceKey<Biome> WOLFSWOOD_CONIFER_SCRUB = ResourceKey.create(Registries.BIOME, AGoTMod.id("wolfswood_conifer_scrub"));

    // Bear Island
    public static final ResourceKey<Biome> BEAR_ISLAND = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island"));
    public static final ResourceKey<Biome> BEAR_ISLAND_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_forest"));
    public static final ResourceKey<Biome> BEAR_ISLAND_DEEP_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_deep_forest"));
    public static final ResourceKey<Biome> BEAR_ISLAND_IRONWOOD_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_ironwood_forest"));
    public static final ResourceKey<Biome> BEAR_ISLAND_DENSE_IRONWOOD = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_dense_ironwood"));
    public static final ResourceKey<Biome> BEAR_ISLAND_BROADLEAF_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_broadleaf_forest"));
    public static final ResourceKey<Biome> BEAR_ISLAND_BEECH_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_beech_forest"));
    public static final ResourceKey<Biome> BEAR_ISLAND_PINE_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_pine_forest"));
    public static final ResourceKey<Biome> BEAR_ISLAND_ANCIENT_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_ancient_forest"));
    public static final ResourceKey<Biome> BEAR_ISLAND_FOREST_EDGE = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_forest_edge"));
    public static final ResourceKey<Biome> BEAR_ISLAND_CLEARING = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_clearing"));
    public static final ResourceKey<Biome> BEAR_ISLAND_OPEN_WOODLAND = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_open_woodland"));
    public static final ResourceKey<Biome> BEAR_ISLAND_CONIFER_SCRUB = ResourceKey.create(Registries.BIOME, AGoTMod.id("bear_island_conifer_scrub"));

    // Other Northern
    public static final ResourceKey<Biome> NORTHERN_HILLS = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_hills"));
    public static final ResourceKey<Biome> NORTHERN_MOUNTAINS = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_mountains"));
    public static final ResourceKey<Biome> BARROWLANDS = ResourceKey.create(Registries.BIOME, AGoTMod.id("barrowlands"));
    public static final ResourceKey<Biome> THE_NORTH = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_north"));
    public static final ResourceKey<Biome> RILLS = ResourceKey.create(Registries.BIOME, AGoTMod.id("rills"));
    public static final ResourceKey<Biome> NORTHERN_WATERS = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_waters"));
    public static final ResourceKey<Biome> STONY_SHORES = ResourceKey.create(Registries.BIOME, AGoTMod.id("stony_shores"));
    public static final ResourceKey<Biome> THE_NECK = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_neck"));

    // Skagos
    public static final ResourceKey<Biome> SKAGOS_TUNDRA = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_tundra"));
    public static final ResourceKey<Biome> SKAGOS_WOODS = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_woods"));
    public static final ResourceKey<Biome> SKAGOS_BARRENS = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_barrens"));
    public static final ResourceKey<Biome> SKAGOS_HILLS = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_hills"));
    public static final ResourceKey<Biome> SKAGOS_VALLEY = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_valley"));
    public static final ResourceKey<Biome> SKAGOS_MOUNTAINS = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_mountains"));
    public static final ResourceKey<Biome> SKAGOS_FROZEN_PEAKS = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_frozen_peaks"));
    public static final ResourceKey<Biome> SKAGOS_RIVER = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_river"));
    public static final ResourceKey<Biome> SKAGOS_BOG = ResourceKey.create(Registries.BIOME, AGoTMod.id("skagos_bog"));

    // =========================================================================
    // BOOTSTRAP
    // =========================================================================

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(LANDS_OF_ALWAYS_WINTER, alwayswinter(context));
        context.register(HAUNTED_FOREST, hauntedforest(context));
        context.register(FROSTFANG_FOOTHILLS, frostfangfoothills(context));
        context.register(FROSTFANGS, frostfangs(context));
        context.register(VALLEY_OF_THENN, valleyofthenn(context));

        // Northern Forest
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

        // Wolfswood Forest
        context.register(WOLFSWOOD_FOREST, wolfswoodForest(context));
        context.register(WOLFSWOOD_DEEP_FOREST, wolfswoodDeepForest(context));
        context.register(WOLFSWOOD_IRONWOOD_FOREST, wolfswoodIronwoodForest(context));
        context.register(WOLFSWOOD_DENSE_IRONWOOD, wolfswoodDenseIronwood(context));
        context.register(WOLFSWOOD_BROADLEAF_FOREST, wolfswoodBroadleafForest(context));
        context.register(WOLFSWOOD_BEECH_FOREST, wolfswoodBeechForest(context));
        context.register(WOLFSWOOD_PINE_FOREST, wolfswoodPineForest(context));
        context.register(WOLFSWOOD_ANCIENT_FOREST, wolfswoodAncientForest(context));
        context.register(WOLFSWOOD_FOREST_EDGE, wolfswoodForestEdge(context));
        context.register(WOLFSWOOD_CLEARING, wolfswoodClearing(context));
        context.register(WOLFSWOOD_OPEN_WOODLAND, wolfswoodOpenWoodland(context));
        context.register(WOLFSWOOD_CONIFER_SCRUB, wolfswoodConiferScrub(context));

        // Bear Island
        context.register(BEAR_ISLAND, bearIsland(context));
        context.register(BEAR_ISLAND_FOREST, bearIslandForest(context));
        context.register(BEAR_ISLAND_DEEP_FOREST, bearIslandDeepForest(context));
        context.register(BEAR_ISLAND_IRONWOOD_FOREST, bearIslandIronwoodForest(context));
        context.register(BEAR_ISLAND_DENSE_IRONWOOD, bearIslandDenseIronwood(context));
        context.register(BEAR_ISLAND_BROADLEAF_FOREST, bearIslandBroadleafForest(context));
        context.register(BEAR_ISLAND_BEECH_FOREST, bearIslandBeechForest(context));
        context.register(BEAR_ISLAND_PINE_FOREST, bearIslandPineForest(context));
        context.register(BEAR_ISLAND_ANCIENT_FOREST, bearIslandAncientForest(context));
        context.register(BEAR_ISLAND_FOREST_EDGE, bearIslandForestEdge(context));
        context.register(BEAR_ISLAND_CLEARING, bearIslandClearing(context));
        context.register(BEAR_ISLAND_OPEN_WOODLAND, bearIslandOpenWoodland(context));
        context.register(BEAR_ISLAND_CONIFER_SCRUB, bearIslandConiferScrub(context));

        // Other Northern
        context.register(NORTHERN_HILLS, northernhills(context));
        context.register(NORTHERN_MOUNTAINS, northernmountains(context));
        context.register(BARROWLANDS, barrowlands(context));
        context.register(THE_NORTH, the_north(context));
        context.register(RILLS, rills(context));
        context.register(NORTHERN_WATERS, northernwaters(context));
        context.register(STONY_SHORES, stonyshores(context));
        context.register(THE_NECK, theneck(context));

        // Skagos
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

    // =========================================================================
    // GLOBAL GENERATION
    // =========================================================================

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    // =========================================================================
    // ORES
    // =========================================================================

    private static void addModOres(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TIN_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.IRON_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.COAL_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.COPPER_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.GOLD_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.LAPIS_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.EMERALD_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.DIAMONDS_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.BLOODSTONE_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CHALCEDONY_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.AMBER_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.AMETHYST_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CARNELIAN_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.GARNET_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.JADE_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.JASPER_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.MALACHITE_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.RUBY_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.ONYX_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.OPAL_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.SAPPHIRE_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.MOONSTONE_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TIGERS_EYE_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TOPAZ_ORE_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TOURMALINE_ORE_PLACED_KEY);
    }

    // =========================================================================
    // RIVER FEATURES
    // =========================================================================

    private static void addRiverFeatures(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.MUD_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEAGRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.KELP_KEY);
    }

    // =========================================================================
    // SHARED FOREST VEGETATION
    // master order: trees → flowers → sedge → grass → ferns
    //
    // parameters:
    // includeAllTrees     — full dense mix of all tree types
    // includeIronwood     — ironwood/sentinel/fir/pine/soldier pine dominant
    // includeBroadleaf    — ash/chestnut/oak/hawthorn/beech heavy
    // includeBeech        — beech/hawthorn/oak dominant
    // includePine         — fir/pine/soldier pine dominant
    // includeAncient      — ironwood/weirwood heavy
    // sparseTrees         — use rare variants instead of dense
    // includeWeirwood     — add weirwood (rare)
    // =========================================================================

    private static void addForestVegetation(
            BiomeGenerationSettings.Builder b,
            BootstrapContext<Biome> context,
            boolean includeAllTrees,
            boolean includeIronwood,
            boolean includeBroadleaf,
            boolean includeBeech,
            boolean includePine,
            boolean includeAncient,
            boolean sparseTrees,
            boolean includeWeirwood
    ) {
        // trees — master order always respected
        if (includeAllTrees) {
            if (sparseTrees) {
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
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
            }
        }

        if (includeIronwood) {
            if (sparseTrees) {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }

        if (includeBroadleaf) {
            if (sparseTrees) {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
            }
        }

        if (includeBeech) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
        }

        if (includePine) {
            if (sparseTrees) {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }

        if (includeAncient) {
            if (sparseTrees) {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }

        if (includeWeirwood) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                    sparseTrees ? ModplacedFeatures.WEIRWOOD_RARE_KEY : ModplacedFeatures.WEIRWOOD_KEY);
        }

        // ground cover — same for all forest biomes
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
    // NORTHERN FOREST VEGETATION CALLS
    // =========================================================================

    private static void addVegetationNorthernForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, false, true);
    }

    private static void addVegetationNorthernDeepForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, false, true);
    }

    private static void addVegetationNorthernIronwoodForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, true, false, false, false, false, false, false);
    }

    private static void addVegetationNorthernDenseIronwood(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, true, false, false, false, false, false, false);
    }

    private static void addVegetationNorthernBroadleafForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, true, false, false, false, false, false);
    }

    private static void addVegetationNorthernBeechForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, true, false, false, false, false);
    }

    private static void addVegetationNorthernPineForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, true, false, false, false);
    }

    private static void addVegetationNorthernAncientForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, false, true, false, true);
    }

    private static void addVegetationNorthernForestEdge(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationNorthernForestClearing(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationNorthernOpenWoodland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationNorthernConiferScrub(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, true, false, true, false);
    }

    // =========================================================================
    // WOLFSWOOD VEGETATION CALLS — same tree logic, slightly warmer feel
    // =========================================================================

    private static void addVegetationWolfswoodForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, false, true);
    }

    private static void addVegetationWolfswoodDeepForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, false, true);
    }

    private static void addVegetationWolfswoodIronwoodForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, true, false, false, false, false, false, false);
    }

    private static void addVegetationWolfswoodDenseIronwood(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, true, false, false, false, false, false, false);
    }

    private static void addVegetationWolfswoodBroadleafForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, true, false, false, false, false, false);
    }

    private static void addVegetationWolfswoodBeechForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, true, false, false, false, false);
    }

    private static void addVegetationWolfswoodPineForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, true, false, false, false);
    }

    private static void addVegetationWolfswoodAncientForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, false, true, false, true);
    }

    private static void addVegetationWolfswoodForestEdge(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationWolfswoodClearing(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationWolfswoodOpenWoodland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationWolfswoodConiferScrub(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, true, false, true, false);
    }

    // =========================================================================
    // BEAR ISLAND VEGETATION CALLS
    // =========================================================================

    private static void addVegetationBearIsland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationBearIslandForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, false, true);
    }

    private static void addVegetationBearIslandDeepForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, false, true);
    }

    private static void addVegetationBearIslandIronwoodForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, true, false, false, false, false, false, false);
    }

    private static void addVegetationBearIslandDenseIronwood(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, true, false, false, false, false, false, false);
    }

    private static void addVegetationBearIslandBroadleafForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, true, false, false, false, false, false);
    }

    private static void addVegetationBearIslandBeechForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, true, false, false, false, false);
    }

    private static void addVegetationBearIslandPineForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, true, false, false, false);
    }

    private static void addVegetationBearIslandAncientForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, false, true, false, true);
    }

    private static void addVegetationBearIslandForestEdge(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationBearIslandClearing(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationBearIslandOpenWoodland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationBearIslandConiferScrub(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, false, false, false, false, true, false, true, false);
    }

    // =========================================================================
    // NON-FOREST VEGETATION
    // =========================================================================

    private static void addVegetationNorthernHills(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationBarrowlands(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationTheNorth(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationRills(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    private static void addVegetationStonyShores(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    // =========================================================================
    // SKAGOS VEGETATION
    // =========================================================================

    private static void addSkagosVegetation(
            BiomeGenerationSettings.Builder b,
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
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            }
        }
        if (includeWeirwood) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_RARE_KEY);
        }
        if (includeFlowers) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        }
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        if (includeFlowers) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        }
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        if (includeSedge) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        }
        if (includeGrass) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        }
        if (includeFerns) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
        }
    }

    // skagos rocky features — shared by tundra, barrens, hills, mountains, frozen peaks
    private static void addSkagosRockyFeatures(BiomeGenerationSettings.Builder b) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TOR_KEY);
    }

    // skagos bushes — conifer types only
    private static void addSkagosBushes(BiomeGenerationSettings.Builder b) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addSkagosTundra(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addSkagosVegetation(b, context, true, false, false, true, true, false, false);
        addSkagosRockyFeatures(b);
        addSkagosBushes(b);
    }

    private static void addSkagosWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addSkagosVegetation(b, context, true, true, true, true, false, true, true);
        addSkagosBushes(b);
    }

    private static void addSkagosBarrens(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addSkagosVegetation(b, context, false, false, false, true, true, false, false);
        addSkagosRockyFeatures(b);
        addSkagosBushes(b);
    }

    private static void addSkagosHills(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addSkagosVegetation(b, context, true, false, false, true, true, false, false);
        addSkagosRockyFeatures(b);
        addSkagosBushes(b);
    }

    private static void addSkagosValley(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addSkagosVegetation(b, context, true, false, true, true, true, true, true);
        addSkagosBushes(b);
    }

    private static void addSkagosMountains(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        addSkagosRockyFeatures(b);
        addSkagosBushes(b);
    }

    private static void addSkagosFrozenPeaks(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        addSkagosRockyFeatures(b);
        addSkagosBushes(b);
    }

    private static void addSkagosBog(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.MUD_PATCH_PLACED_KEY);
        addSkagosVegetation(b, context, true, false, false, true, true, true, false);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
        addSkagosBushes(b);
    }

    // =========================================================================
    // BIOME BUILDER HELPER
    // =========================================================================

    private static Biome buildBiome(
            BiomeGenerationSettings.Builder biomeBuilder,
            MobSpawnSettings.Builder spawnBuilder,
            float temperature,
            float downfall,
            int waterColor,
            int waterFogColor,
            int skyColor,
            int grassColor,
            int foliageColor,
            int fogColor,
            net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.sounds.SoundEvent, net.minecraft.sounds.SoundEvent> ambientLoop,
            boolean forestWind
    ) {
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .waterColor(waterColor)
                .waterFogColor(waterFogColor)
                .skyColor(skyColor)
                .grassColorOverride(grassColor)
                .foliageColorOverride(foliageColor)
                .fogColor(fogColor)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND));

        if (ambientLoop != null) {
            effects.ambientLoopSound(ambientLoop);
        }

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(downfall)
                .temperature(temperature)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(effects.build())
                .build();
    }

    // =========================================================================
    // BIOME DEFINITIONS — NON-FOREST
    // =========================================================================

    public static Biome alwayswinter(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0xd9d9d9, 0xd9d9d9, 0x000000,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome hauntedforest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addForestHauntedForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x577a5f, 0x324d38, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    private static void addForestHauntedForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    public static Biome frostfangfoothills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x577a5f, 0x324d38, 0x000000,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome frostfangs(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x577a5f, 0x324d38, 0x000000,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome valleyofthenn(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
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
                        .waterColor(0x3d4ed1).waterFogColor(0x0c113b).skyColor(0x5a6ef2)
                        .grassColorOverride(0x5c9967).foliageColorOverride(0x324d38).fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MAMMOTH_SOUNDS)).build())
                .build();
    }

    public static Biome northernhills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernHills(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb,
                null, false);
    }

    public static Biome northernmountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.5f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome barrowlands(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBarrowlands(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.5f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb,
                null, false);
    }

    public static Biome the_north(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationTheNorth(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.5f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb,
                null, false);
    }

    public static Biome rills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        addVegetationRills(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.5f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x35a641, 0x35a641, 0xa7addb,
                null, false);
    }

    public static Biome northernwaters(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.5f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb,
                null, false);
    }

    public static Biome stonyshores(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationStonyShores(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.5f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb,
                null, false);
    }

    public static Biome theneck(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMangroveSwampVegetation(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.5f, 0.8f,
                0x294018, 0x1f1308, 0x30523b, 0x27471f, 0x27471f, 0x638a6d,
                null, false);
    }

    // =========================================================================
    // NORTHERN FOREST BIOME DEFINITIONS
    // =========================================================================

    public static Biome northernForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x224d22, 0x1a3d1a, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernDeepForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernDeepForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x194219, 0x123412, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernIronwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernIronwoodForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x0d330d, 0x092909, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernDenseIronwood(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernDenseIronwood(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x042304, 0x031a03, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernBroadleafForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernBroadleafForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x2d5c2d, 0x234823, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernBeechForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernBeechForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x285228, 0x1e3e1e, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernPineForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernPineForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x1e471e, 0x163816, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernAncientForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernAncientForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x113811, 0x0d2b0d, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernForestEdge(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernForestEdge(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x356535, 0x284e28, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome northernForestClearing(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernForestClearing(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x487a48, 0x386038, 0xa7addb,
                null, false);
    }

    public static Biome northernOpenWoodland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernOpenWoodland(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d6e3d, 0x2e552e, 0xa7addb,
                null, false);
    }

    public static Biome northernConiferScrub(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernConiferScrub(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x153d15, 0x0f2f0f, 0xa7addb,
                null, false);
    }

    // =========================================================================
    // WOLFSWOOD BIOME DEFINITIONS
    // =========================================================================

    public static Biome wolfswoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x224d22, 0x1a3d1a, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodDeepForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodDeepForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x194219, 0x123412, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodIronwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodIronwoodForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x0d330d, 0x092909, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodDenseIronwood(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodDenseIronwood(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x042304, 0x031a03, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodBroadleafForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodBroadleafForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x2d5c2d, 0x234823, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodBeechForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodBeechForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x285228, 0x1e3e1e, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodPineForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodPineForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x1e471e, 0x163816, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodAncientForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodAncientForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x113811, 0x0d2b0d, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodForestEdge(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodForestEdge(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x356535, 0x284e28, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome wolfswoodClearing(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodClearing(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x487948, 0x386038, 0xa7addb,
                null, false);
    }

    public static Biome wolfswoodOpenWoodland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodOpenWoodland(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d6e3d, 0x2e552e, 0xa7addb,
                null, false);
    }

    public static Biome wolfswoodConiferScrub(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationWolfswoodConiferScrub(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x153d15, 0x0f2f0f, 0xa7addb,
                null, false);
    }

    // =========================================================================
    // BEAR ISLAND BIOME DEFINITIONS
    // =========================================================================

    public static Biome bearIsland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIsland(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x456245, 0x364d36, 0xa7addb,
                null, false);
    }

    public static Biome bearIslandForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x224d22, 0x1a3d1a, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandDeepForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandDeepForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x194219, 0x123412, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandIronwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandIronwoodForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x0d330d, 0x092909, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandDenseIronwood(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandDenseIronwood(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x042304, 0x031a03, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandBroadleafForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandBroadleafForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x2d5c2d, 0x234823, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandBeechForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandBeechForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x285228, 0x1e3e1e, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandPineForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandPineForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x1e471e, 0x163816, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandAncientForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandAncientForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x113811, 0x0d2b0d, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandForestEdge(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandForestEdge(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x356535, 0x284e28, 0xa7addb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome bearIslandClearing(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandClearing(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x487948, 0x386038, 0xa7addb,
                null, false);
    }

    public static Biome bearIslandOpenWoodland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandOpenWoodland(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d6e3d, 0x2e552e, 0xa7addb,
                null, false);
    }

    public static Biome bearIslandConiferScrub(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBearIslandConiferScrub(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x153d15, 0x0f2f0f, 0xa7addb,
                null, false);
    }

    // =========================================================================
    // SKAGOS BIOME DEFINITIONS
    // =========================================================================

    public static Biome skagosTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosTundra(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.45f, 0.85f,
                0x3d4ed1, 0x0c113b, 0x8f97c4, 0x8a9582, 0x6e7867, 0xc9ced7,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome skagosWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosWoods(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.25f, 0.9f,
                0x3d4ed1, 0x0c113b, 0x8a94c4, 0x61755b, 0x455843, 0x9fa7b4,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome skagosBarrens(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosBarrens(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.7f, 0.7f,
                0x3d4ed1, 0x0c113b, 0x98a0c8, 0x909090, 0x777777, 0xd7d7d7,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome skagosHills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosHills(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.55f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x9099c8, 0x7b866f, 0x66715c, 0xbec4cd,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome skagosValley(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosValley(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.1f, 0.95f,
                0x3d4ed1, 0x0c113b, 0x8895c6, 0x5c7d55, 0x476444, 0xa4adb9,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome skagosMountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosMountains(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.75f,
                0x3d4ed1, 0x0c113b, 0x9ba4cc, 0xc4c4c4, 0xaaaaaa, 0xe6e6e6,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome skagosFrozenPeaks(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosFrozenPeaks(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xb5c2e0, 0xececec, 0xececec, 0xffffff,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome skagosRiver(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x315d7d, 0x1b3850, 0x647c91, 0x566b4b, 0x4f6245, 0x8294a3,
                null, false);
    }

    public static Biome skagosBog(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addSkagosBog(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.45f, 0.85f,
                0x3d4ed1, 0x0c113b, 0x8f97c4, 0x8a9582, 0x6e7867, 0xc9ced7,
                ModSounds.WINTER_WIND, false);
    }
}