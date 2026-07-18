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
    public static final ResourceKey<Biome> NORTHERN_PLAINS = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_plains"));
    public static final ResourceKey<Biome> RILLS = ResourceKey.create(Registries.BIOME, AGoTMod.id("rills"));
    public static final ResourceKey<Biome> NORTHERN_WATERS = ResourceKey.create(Registries.BIOME, AGoTMod.id("northern_waters"));
    public static final ResourceKey<Biome> STONY_SHORES = ResourceKey.create(Registries.BIOME, AGoTMod.id("stony_shores"));
    public static final ResourceKey<Biome> THE_NECK = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_neck"));

    // Umber

    public static final ResourceKey<Biome> UMBER_DOMAIN_TUNDRA = ResourceKey.create(Registries.BIOME, AGoTMod.id("umber_domain_tundra"));
    public static final ResourceKey<Biome> UMBER_DOMAIN_BARRENS = ResourceKey.create(Registries.BIOME, AGoTMod.id("umber_domain_barrens"));
    public static final ResourceKey<Biome> UMBER_DOMAIN_WOODS = ResourceKey.create(Registries.BIOME, AGoTMod.id("umber_domain_woods"));
    public static final ResourceKey<Biome> UMBER_DOMAIN_DEEP_TUNDRA = ResourceKey.create(Registries.BIOME, AGoTMod.id("umber_domain_deep_tundra"));
    public static final ResourceKey<Biome> UMBER_DOMAIN_DEEP_BARRENS = ResourceKey.create(Registries.BIOME, AGoTMod.id("umber_domain_deep_barrens"));
    public static final ResourceKey<Biome> UMBER_DOMAIN_DEEP_WOODS = ResourceKey.create(Registries.BIOME, AGoTMod.id("umber_domain_deep_woods"));

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

    // Night's Watch
    public static final ResourceKey<Biome> THE_WALL = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_wall"));
    public static final ResourceKey<Biome> THE_GIFT_TUNDRA = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_gift_tundra"));
    public static final ResourceKey<Biome> THE_GIFT_BARRENS = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_gift_barrens"));
    public static final ResourceKey<Biome> THE_GIFT_WOODS = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_gift_woods"));
    public static final ResourceKey<Biome> THE_NEW_GIFT_TUNDRA = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_new_gift_tundra"));
    public static final ResourceKey<Biome> THE_NEW_GIFT_BARRENS = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_new_gift_barrens"));
    public static final ResourceKey<Biome> THE_NEW_GIFT_WOODS = ResourceKey.create(Registries.BIOME, AGoTMod.id("the_new_gift_woods"));

    // Beyond The Wall

    public static final ResourceKey<Biome> BEYOND_THE_WALL = ResourceKey.create(Registries.BIOME, AGoTMod.id("beyond_the_wall"));
    public static final ResourceKey<Biome> BEYOND_THE_WALL_ICE_RIVER_VALLEY = ResourceKey.create(Registries.BIOME, AGoTMod.id("beyond_the_wall_ice_river_valley"));
    public static final ResourceKey<Biome> BEYOND_THE_WALL_FROZEN_SHORE = ResourceKey.create(Registries.BIOME, AGoTMod.id("beyond_the_wall_frozen_shore"));
    public static final ResourceKey<Biome> BEYOND_THE_WALL_RIVER_VALLEY = ResourceKey.create(Registries.BIOME, AGoTMod.id("beyond_the_wall_river_valley"));
    public static final ResourceKey<Biome> GIANTS_STAIR = ResourceKey.create(Registries.BIOME, AGoTMod.id("giants_stair"));
    public static final ResourceKey<Biome> GIANTS_STAIR_RIVER_VALLEY = ResourceKey.create(Registries.BIOME, AGoTMod.id("giants_stair_river_valley"));
    public static final ResourceKey<Biome> FROSTFANG_HILLS = ResourceKey.create(Registries.BIOME, AGoTMod.id("frostfang_hills"));
    public static final ResourceKey<Biome> FROSTFANG_MOUNTAINS = ResourceKey.create(Registries.BIOME, AGoTMod.id("frostfang_mountains"));

    // Haunted Forest
    public static final ResourceKey<Biome> HAUNTED_FOREST_CLEARING = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_forest_clearing"));
    public static final ResourceKey<Biome> HAUNTED_OPEN_WOODLAND = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_open_woodland"));
    public static final ResourceKey<Biome> HAUNTED_FOREST_EDGE = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_forest_edge"));
    public static final ResourceKey<Biome> HAUNTED_FOREST_MAIN = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_forest_main"));
    public static final ResourceKey<Biome> HAUNTED_BROADLEAF_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_broadleaf_forest"));
    public static final ResourceKey<Biome> HAUNTED_DEEP_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_deep_forest"));
    public static final ResourceKey<Biome> HAUNTED_PINE_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_pine_forest"));
    public static final ResourceKey<Biome> HAUNTED_IRONWOOD_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_ironwood_forest"));
    public static final ResourceKey<Biome> HAUNTED_ANCIENT_FOREST = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_ancient_forest"));
    public static final ResourceKey<Biome> HAUNTED_DENSE_IRONWOOD = ResourceKey.create(Registries.BIOME, AGoTMod.id("haunted_dense_ironwood"));

    public static final ResourceKey<Biome> THENN_VALLEY_PLAINS = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_valley_plains"));
    public static final ResourceKey<Biome> THENN_VALLEY_TUNDRA = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_valley_tundra"));
    public static final ResourceKey<Biome> THENN_VALLEY_WOODS = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_valley_woods"));
    public static final ResourceKey<Biome> THENN_UPPER_VALLEY_PLAINS = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_upper_valley_plains"));
    public static final ResourceKey<Biome> THENN_UPPER_VALLEY_TUNDRA = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_upper_valley_tundra"));
    public static final ResourceKey<Biome> THENN_UPPER_VALLEY_WOODS = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_upper_valley_woods"));
    public static final ResourceKey<Biome> THENN_HILLS = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_hills"));
    public static final ResourceKey<Biome> THENN_MOUNTAINS = ResourceKey.create(Registries.BIOME, AGoTMod.id("thenn_mountains"));

    // =========================================================================
    // BOOTSTRAP
    // =========================================================================

    public static void boostrap(BootstrapContext<Biome> context) {

        // Haunted Forest

        context.register(HAUNTED_FOREST_CLEARING, hauntedForestClearing(context));
        context.register(HAUNTED_OPEN_WOODLAND, hauntedOpenWoodland(context));
        context.register(HAUNTED_FOREST_EDGE, hauntedForestEdge(context));
        context.register(HAUNTED_FOREST_MAIN, hauntedForestMain(context));
        context.register(HAUNTED_BROADLEAF_FOREST, hauntedBroadleafForest(context));
        context.register(HAUNTED_DEEP_FOREST, hauntedDeepForest(context));
        context.register(HAUNTED_PINE_FOREST, hauntedPineForest(context));
        context.register(HAUNTED_IRONWOOD_FOREST, hauntedIronwoodForest(context));
        context.register(HAUNTED_ANCIENT_FOREST, hauntedAncientForest(context));
        context.register(HAUNTED_DENSE_IRONWOOD, hauntedDenseIronwood(context));

        context.register(BEYOND_THE_WALL, beyondTheWall(context));
        context.register(BEYOND_THE_WALL_ICE_RIVER_VALLEY, beyondTheWallIceRiverValley(context));
        context.register(BEYOND_THE_WALL_FROZEN_SHORE, beyondTheWallFrozenShore(context));
        context.register(BEYOND_THE_WALL_RIVER_VALLEY, beyondTheWallRiverValley(context));
        context.register(GIANTS_STAIR, giantsStair(context));
        context.register(GIANTS_STAIR_RIVER_VALLEY, giantsStairRiverValley(context));
        context.register(FROSTFANG_HILLS, frostfangHills(context));
        context.register(FROSTFANG_MOUNTAINS, frostfangMountains(context));
        context.register(THENN_VALLEY_PLAINS, thennValleyPlains(context));
        context.register(THENN_VALLEY_TUNDRA, thennValleyTundra(context));
        context.register(THENN_VALLEY_WOODS, thennValleyWoods(context));
        context.register(THENN_UPPER_VALLEY_PLAINS, thennUpperValleyPlains(context));
        context.register(THENN_UPPER_VALLEY_TUNDRA, thennUpperValleyTundra(context));
        context.register(THENN_UPPER_VALLEY_WOODS, thennUpperValleyWoods(context));
        context.register(THENN_HILLS, thennHills(context));
        context.register(THENN_MOUNTAINS, thennMountains(context));

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
        context.register(NORTHERN_PLAINS, northernPlains(context));
        context.register(RILLS, rills(context));
        context.register(NORTHERN_WATERS, northernwaters(context));
        context.register(STONY_SHORES, stonyshores(context));
        context.register(THE_NECK, theneck(context));

        // Umber

        context.register(UMBER_DOMAIN_TUNDRA, umberDomainTundra(context));
        context.register(UMBER_DOMAIN_BARRENS, umberDomainBarrens(context));
        context.register(UMBER_DOMAIN_WOODS, umberDomainWoods(context));
        context.register(UMBER_DOMAIN_DEEP_TUNDRA, umberDomainDeepTundra(context));
        context.register(UMBER_DOMAIN_DEEP_BARRENS, umberDomainDeepBarrens(context));
        context.register(UMBER_DOMAIN_DEEP_WOODS, umberDomainDeepWoods(context));

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

        // Night's Watch
        context.register(THE_WALL, theWall(context));
        context.register(THE_GIFT_TUNDRA, theGiftTundra(context));
        context.register(THE_GIFT_BARRENS, theGiftBarrens(context));
        context.register(THE_GIFT_WOODS, theGiftWoods(context));
        context.register(THE_NEW_GIFT_TUNDRA, theNewGiftTundra(context));
        context.register(THE_NEW_GIFT_BARRENS, theNewGiftBarrens(context));
        context.register(THE_NEW_GIFT_WOODS, theNewGiftWoods(context));


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

    // Haunted Forest

    private static void addHauntedForestVegetation(
            BiomeGenerationSettings.Builder b,
            BootstrapContext<Biome> context,
            boolean includeAllTrees,
            boolean includeIronwood,
            boolean includeBroadleaf,
            boolean includePine,
            boolean includeAncient,
            boolean sparseTrees,
            boolean includeWeirwood,
            boolean denseWeirwood
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
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
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
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
            } else {
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
                b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
            }
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
                    denseWeirwood ? ModplacedFeatures.WEIRWOOD_KEY : ModplacedFeatures.WEIRWOOD_RARE_KEY);
        }

        // haunted ground cover — always the same, no extra flowers
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
    }

    // Haunted Forest Clearing — sparse, open, ghostly
    private static void addVegetationHauntedForestClearing(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, true, false, false, false, false, true, true, false);
    }

    // Haunted Open Woodland — sparse mix, more variety
    private static void addVegetationHauntedOpenWoodland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, true, false, false, false, false, true, true, false);
    }

    // Haunted Forest Edge — sparse all types, transitional
    private static void addVegetationHauntedForestEdge(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, true, false, false, false, false, true, true, false);
    }

    // Haunted Forest — full dense mix
    private static void addVegetationHauntedForestMain(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, true, false, false, false, false, false, true, false);
    }

    // Haunted Broadleaf — ash/chestnut/oak heavy
    private static void addVegetationHauntedBroadleafForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, false, false, true, false, false, false, true, false);
    }

    // Haunted Deep Forest — full dense, darker via colors
    private static void addVegetationHauntedDeepForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, true, false, false, false, false, false, true, true);
    }

    // Haunted Pine Forest — conifer dominant
    private static void addVegetationHauntedPineForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, false, false, false, true, false, false, true, false);
    }

    // Haunted Ironwood Forest — ironwood/conifer dominant
    private static void addVegetationHauntedIronwoodForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, false, true, false, false, false, false, true, false);
    }

    // Haunted Ancient Forest — weirwood heavy, old growth
    private static void addVegetationHauntedAncientForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, false, false, false, false, true, false, true, true);
    }

    // Haunted Dense Ironwood — darkest, pure ironwood/conifer
    private static void addVegetationHauntedDenseIronwood(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addHauntedForestVegetation(b, context, false, true, false, false, false, false, true, true);
    }

    private static void addBeyondTheWallVegetation(
            BiomeGenerationSettings.Builder b,
            BootstrapContext<Biome> context,
            boolean includeGrass,
            boolean includeTrees,
            boolean includeBushes,
            boolean includeRocks
    ) {
        // trees always first — matches master order
        if (includeTrees) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_RARE_KEY);
        }

        // ground cover always after trees
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);

        if (includeGrass) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        }

        if (includeRocks) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PATCH_PLACED_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
        }

        if (includeBushes) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
        }
    }

    // beyond the wall standard — sparse grass, no trees, no rocks
    private static void addVegetationBeyondTheWall(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addBeyondTheWallVegetation(b, context, true, false, false, false);
    }

    // ice river valley — completely frozen, rocks and gravel only
    private static void addVegetationBeyondTheWallIceRiverValley(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addBeyondTheWallVegetation(b, context, false, false, false, true);
    }

    // frozen shore — same as ice river valley
    private static void addVegetationBeyondTheWallFrozenShore(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addBeyondTheWallVegetation(b, context, false, false, false, true);
    }

    // river valley — grass, occasional trees, bushes
    private static void addVegetationBeyondTheWallRiverValley(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addBeyondTheWallVegetation(b, context, true, true, true, false);
    }

    private static void addFrostfangVegetation(
            BiomeGenerationSettings.Builder b,
            boolean includeGrass,
            boolean includeBushes,
            boolean includeTor
    ) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);

        if (includeGrass) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        }

        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);

        if (includeTor) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TOR_KEY);
        }

        if (includeBushes) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
        }
    }

    private static void addVegetationGiantsStair(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addFrostfangVegetation(b, false, false, true);
    }

    private static void addVegetationGiantsStairRiverValley(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addFrostfangVegetation(b, true, true, false);
    }

    private static void addVegetationFrostfangHills(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addFrostfangVegetation(b, true, false, true);
    }

    private static void addVegetationFrostfangMountains(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addFrostfangVegetation(b, false, false, true);
    }

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

    // Umber

    private static void addVegetationUmberDomainTundra(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
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
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addVegetationUmberDomainBarrens(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addVegetationUmberDomainWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
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
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
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

    private static void addVegetationNorthernPlains(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
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

    private static void addVegetationTheWall(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        // wolfswood ground cover — no trees
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
        // skagos bushes
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addVegetationGiftTundra(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        // rare trees
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
        // ground cover
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
        // bushes
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addVegetationGiftBarrens(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        // rare trees
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        // ground cover
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
        // bushes
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // woods — full northern forest but trees are rare (0.1 weight in zone)
    private static void addVegetationGiftWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addForestVegetation(b, context, true, false, false, false, false, false, false, true);
    }
    private static void addThennVegetation(
            BiomeGenerationSettings.Builder b,
            BootstrapContext<Biome> context,
            boolean denseTrees,
            boolean sparseTrees,
            boolean includeGrass,
            boolean includeRocks,
            boolean includeTor,
            boolean includeBushes
    ) {
        if (sparseTrees) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        }

        if (denseTrees) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SOLDIER_PINE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_RARE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        }

        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);

        if (includeGrass) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEDGE_KEY); // add this
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TALL_GRASS_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FERN_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LARGE_FERN_KEY);
        }

        if (includeRocks) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PATCH_PLACED_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.STONE_PILE_KEY);
        }

        if (includeTor) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.TOR_KEY);
        }

        if (includeBushes) {
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch"));
            b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
        }
    }
    // Valley 1 — lower, warmer
    private static void addVegetationThennValleyPlains(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, false, true, true, false, false, true);
    }

    private static void addVegetationThennValleyTundra(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, false, true, true, false, false, false);
    }

    private static void addVegetationThennValleyWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, true, false, true, false, false, true);
    }

    // Valley 2 — higher, slightly more exposed
    private static void addVegetationThennUpperValleyPlains(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, false, true, true, false, false, true);
    }

    private static void addVegetationThennUpperValleyTundra(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, false, true, true, false, false, false);
    }

    private static void addVegetationThennUpperValleyWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, true, false, true, false, false, true);
    }

    // Hills — rocky, sparse trees, tors
    private static void addVegetationThennHills(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, false, true, true, true, true, false);
    }

    // Mountains — cold, barren, heavy rocks
    private static void addVegetationThennMountains(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> context) {
        addThennVegetation(b, context, false, false, false, true, true, false);
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

    public static Biome hauntedForestClearing(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedForestClearing(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x828c82, 0x6a746a, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedOpenWoodland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedOpenWoodland(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x828c82, 0x6a746a, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedForestEdge(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedForestEdge(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x778277, 0x616b61, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedForestMain(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedForestMain(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x778277, 0x616b61, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedBroadleafForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedBroadleafForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x778277, 0x616b61, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedDeepForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedDeepForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x6d786d, 0x586258, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedPineForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedPineForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x6d786d, 0x586258, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedIronwoodForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedIronwoodForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x6d786d, 0x586258, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedAncientForest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedAncientForest(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x5a635a, 0x474f47, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome hauntedDenseIronwood(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationHauntedDenseIronwood(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x7988b5, 0x5a635a, 0x474f47, 0x000000,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome beyondTheWall(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBeyondTheWall(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xd9d9d9, 0xc8c8c8, 0xe8e8e8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome beyondTheWallIceRiverValley(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBeyondTheWallIceRiverValley(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xd6d3bb, 0xc4c1aa, 0xe8e8e8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome beyondTheWallFrozenShore(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBeyondTheWallFrozenShore(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xd9caca, 0xc8b9b9, 0xe8e8e8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome beyondTheWallRiverValley(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationBeyondTheWallRiverValley(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xc5c5c5, 0xb4b4b4, 0xe8e8e8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome giantsStair(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiantsStair(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xb8b9cc, 0xbfc0cd, 0xaeafbc, 0xd8d8e8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome giantsStairRiverValley(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiantsStairRiverValley(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xb8b9cc, 0xadaec5, 0x9c9db4, 0xd8d8e8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome frostfangHills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationFrostfangHills(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.6f,
                0x3d4ed1, 0x0c113b, 0xa8aab8, 0x969494, 0x848282, 0xc8c8c8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome frostfangMountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationFrostfangMountains(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -1.0f, 0.5f,
                0x3d4ed1, 0x0c113b, 0xb8bac8, 0xc6c6c6, 0xb4b4b4, 0xe0e0e0,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome northernhills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernHills(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.15f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x8f9e98, 0x7a8a83, 0xa7addb,
                null, false);
    }

    public static Biome northernmountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0xcbcfcd, 0xb0b5b2, 0xe0e3e2,
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

    public static Biome northernPlains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationNorthernPlains(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f55, 0x3d4f45, 0xa7addb,
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

    public static Biome theWall(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationTheWall(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, -0.8f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xd0e0f0, 0xe8f0e8, 0xe0ece0, 0xffffff,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome theGiftTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiftTundra(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.1f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x9ab0a6, 0x6a7b71, 0x576866, 0xb0c4bb,
                null, false);
    }

    public static Biome theGiftBarrens(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiftBarrens(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.1f, 0.7f,
                0x3d4ed1, 0x0c113b, 0x9ab0a6, 0x6a7b71, 0x576866, 0xb0c4bb,
                null, false);
    }

    public static Biome theGiftWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiftWoods(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.1f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x9ab0a6, 0x6a7b71, 0x576866, 0xb0c4bb,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome theNewGiftTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiftTundra(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.20f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa0b8ac, 0x5b6d63, 0x4a5c52, 0xbacec6,
                null, false);
    }

    public static Biome theNewGiftBarrens(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiftBarrens(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.20f, 0.7f,
                0x3d4ed1, 0x0c113b, 0xa0b8ac, 0x5b6d63, 0x4a5c52, 0xbacec6,
                null, false);
    }

    public static Biome theNewGiftWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationGiftWoods(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.20f, 0.8f,
                0x3d4ed1, 0x0c113b, 0xa0b8ac, 0x5b6d63, 0x4a5c52, 0xbacec6,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome umberDomainTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationUmberDomainTundra(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x8aaa96, 0x2a5d3e, 0x1f4a30, 0xa8c4b4,
                null, false);
    }

    public static Biome umberDomainBarrens(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationUmberDomainBarrens(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.7f,
                0x3d4ed1, 0x0c113b, 0x8aaa96, 0x2a5d3e, 0x1f4a30, 0xa8c4b4,
                null, false);
    }

    public static Biome umberDomainWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationUmberDomainWoods(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x8aaa96, 0x2a5d3e, 0x1f4a30, 0xa8c4b4,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome umberDomainDeepTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationUmberDomainTundra(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x82a28e, 0x326647, 0x265237, 0xa0bcaa,
                null, false);
    }

    public static Biome umberDomainDeepBarrens(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationUmberDomainBarrens(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.7f,
                0x3d4ed1, 0x0c113b, 0x82a28e, 0x326647, 0x265237, 0xa0bcaa,
                null, false);
    }

    public static Biome umberDomainDeepWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationUmberDomainWoods(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.2f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x82a28e, 0x326647, 0x265237, 0xa0bcaa,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome thennValleyPlains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennValleyPlains(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.3f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x96b096, 0x6b8f6b, 0x587a58, 0xb0c8b0,
                null, false);
    }

    public static Biome thennValleyTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennValleyTundra(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.3f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x96b096, 0x6b8f6b, 0x587a58, 0xb0c8b0,
                null, false);
    }

    public static Biome thennValleyWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennValleyWoods(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.3f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x96b096, 0x6b8f6b, 0x587a58, 0xb0c8b0,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome thennUpperValleyPlains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennUpperValleyPlains(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.3f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x8ea88e, 0x5f835f, 0x4e6e4e, 0xa8c0a8,
                null, false);
    }

    public static Biome thennUpperValleyTundra(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennUpperValleyTundra(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.3f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x8ea88e, 0x5f835f, 0x4e6e4e, 0xa8c0a8,
                null, false);
    }

    public static Biome thennUpperValleyWoods(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennUpperValleyWoods(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.3f, 0.8f,
                0x3d4ed1, 0x0c113b, 0x8ea88e, 0x5f835f, 0x4e6e4e, 0xa8c0a8,
                ModSounds.FOREST_WIND, true);
    }

    public static Biome thennHills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennHills(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.15f, 0.7f,
                0x3d4ed1, 0x0c113b, 0xb0a8a8, 0xad9f9f, 0x9c8e8e, 0xd0c8c8,
                ModSounds.WINTER_WIND, false);
    }

    public static Biome thennMountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);
        addVegetationThennMountains(biomeBuilder, context);
        return buildBiome(biomeBuilder, spawnBuilder, 0.15f, 0.6f,
                0x3d4ed1, 0x0c113b, 0xc0b8b8, 0xc5b4b4, 0xb4a3a3, 0xe0d8d8,
                ModSounds.WINTER_WIND, false);
    }
}