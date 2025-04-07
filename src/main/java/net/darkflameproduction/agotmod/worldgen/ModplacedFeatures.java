package net.darkflameproduction.agotmod.worldgen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.worldgen.ore.ModOrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.block.Blocks;


import java.util.List;

public class ModplacedFeatures {

    // Define a ResourceKey for the placed feature of tin ore
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> WEIRWOOD_KEY = registerKey("weirwood");
    public static final ResourceKey<PlacedFeature> OAK2_KEY = registerKey("oak");
    public static final ResourceKey<PlacedFeature> SENTINEL_KEY = registerKey("sentinel");
    public static final ResourceKey<PlacedFeature> PINE_KEY = registerKey("pine");
    public static final ResourceKey<PlacedFeature> IRONWOOD_KEY = registerKey("ironwood");
    public static final ResourceKey<PlacedFeature> HAWTHORN_KEY = registerKey("hawthorn");
    public static final ResourceKey<PlacedFeature> CHESTNUT_KEY = registerKey("chestnut");
    public static final ResourceKey<PlacedFeature> CEDAR_KEY = registerKey("cedar");
    public static final ResourceKey<PlacedFeature> BEECH_KEY = registerKey("beech");
    public static final ResourceKey<PlacedFeature> ASH_KEY = registerKey("ash");
    public static final ResourceKey<PlacedFeature> BLACKBARK_KEY = registerKey("blackbark");
    public static final ResourceKey<PlacedFeature> ASPEN_KEY = registerKey("aspen");
    public static final ResourceKey<PlacedFeature> ALDER_KEY = registerKey("alder");
    public static final ResourceKey<PlacedFeature> WEIRWOOD_RARE_KEY = registerKey("weirwood_rare");
    public static final ResourceKey<PlacedFeature> OAK2_RARE_KEY = registerKey("oak_rare");
    public static final ResourceKey<PlacedFeature> SENTINEL_RARE_KEY = registerKey("sentinel_rare");
    public static final ResourceKey<PlacedFeature> PINE_RARE_KEY = registerKey("pine_rare");
    public static final ResourceKey<PlacedFeature> IRONWOOD_RARE_KEY = registerKey("ironwood_rare");
    public static final ResourceKey<PlacedFeature> HAWTHORN_RARE_KEY = registerKey("hawthorn_rare");
    public static final ResourceKey<PlacedFeature> CHESTNUT_RARE_KEY = registerKey("chestnut_rare");
    public static final ResourceKey<PlacedFeature> CEDAR_RARE_KEY = registerKey("cedar_rare");
    public static final ResourceKey<PlacedFeature> BEECH_RARE_KEY = registerKey("beech_rare");
    public static final ResourceKey<PlacedFeature> ASH_RARE_KEY = registerKey("ash_rare");
    public static final ResourceKey<PlacedFeature> BLACKBARK_RARE_KEY = registerKey("blackbark_rare");
    public static final ResourceKey<PlacedFeature> ASPEN_RARE_KEY = registerKey("aspen_rare");
    public static final ResourceKey<PlacedFeature> ALDER_RARE_KEY = registerKey("alder_rare");
    public static final ResourceKey<PlacedFeature> THORN_BUSH_KEY = registerKey("thorn_bush");
    public static final ResourceKey<PlacedFeature> THISTLE_KEY = registerKey("thistle");
    public static final ResourceKey<PlacedFeature> TANSY_KEY = registerKey("tansy");
    public static final ResourceKey<PlacedFeature> SPICEFLOWER_KEY = registerKey("spiceflower");
    public static final ResourceKey<PlacedFeature> SEDGE_KEY = registerKey("sedge");
    public static final ResourceKey<PlacedFeature> SAFFRON_CROCUS_KEY = registerKey("saffron_crocus");
    public static final ResourceKey<PlacedFeature> POISON_KISSES_KEY = registerKey("poison_kisses");
    public static final ResourceKey<PlacedFeature> PENNYROYAL_KEY = registerKey("pennyroyal");
    public static final ResourceKey<PlacedFeature> OPIUM_POPPY_KEY = registerKey("opium_poppy");
    public static final ResourceKey<PlacedFeature> NIGHTSHADE_KEY = registerKey("nightshade");
    public static final ResourceKey<PlacedFeature> MOONBLOOM_KEY = registerKey("moonbloom");
    public static final ResourceKey<PlacedFeature> LUNGWORT_KEY = registerKey("lungwort");
    public static final ResourceKey<PlacedFeature> LIVERWORT_KEY = registerKey("liverwort");
    public static final ResourceKey<PlacedFeature> LAVENDER_KEY = registerKey("lavender");
    public static final ResourceKey<PlacedFeature> LADYS_LACE_KEY = registerKey("ladys_lace");
    public static final ResourceKey<PlacedFeature> GORSE_KEY = registerKey("gorse");
    public static final ResourceKey<PlacedFeature> GOLDENROD_KEY = registerKey("goldenrod");
    public static final ResourceKey<PlacedFeature> GOLDENCUP_KEY = registerKey("goldencup");
    public static final ResourceKey<PlacedFeature> GOATHEAD_KEY = registerKey("goathead");
    public static final ResourceKey<PlacedFeature> GINGER_KEY = registerKey("ginger");
    public static final ResourceKey<PlacedFeature> GILLYFLOWER_KEY = registerKey("gillyflower");
    public static final ResourceKey<PlacedFeature> FROSTFIRE_KEY = registerKey("frostfire");
    public static final ResourceKey<PlacedFeature> FORGET_ME_NOT_KEY = registerKey("forget_me_not");
    public static final ResourceKey<PlacedFeature> EVENING_STAR_KEY = registerKey("evening_star");
    public static final ResourceKey<PlacedFeature> DRAGONS_BREATH_KEY = registerKey("dragons_breath");
    public static final ResourceKey<PlacedFeature> COLDSNAP_KEY = registerKey("coldsnap");
    public static final ResourceKey<PlacedFeature> BLOODBLOOM_KEY = registerKey("bloodbloom");
    public static final ResourceKey<PlacedFeature> BLACK_LOTUS_KEY = registerKey("black_lotus");
    public static final ResourceKey<PlacedFeature> BLUE_ROSE_BUSH_KEY = registerKey("blue_rose_bush");
    public static final ResourceKey<PlacedFeature> WHITE_ROSE_BUSH_KEY = registerKey("white_rose_bush");
    public static final ResourceKey<PlacedFeature> DUSKY_ROSE_BUSH_KEY = registerKey("dusky_rose_bush");
    public static final ResourceKey<PlacedFeature> WINTER_ROSE_BUSH_KEY = registerKey("winter_rose_bush");
    public static final ResourceKey<PlacedFeature> RED_ROSE_BUSH_KEY = registerKey("red_rose_bush");




    // Bootstrap method for initializing placed features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        // Get a holder for configured features from the context
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Register the placed feature for tin ore with specified configuration and modifiers
        register(context, TIN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TIN_ORE),
                ModOrePlacement.commonOrePlacement(14,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        //Forest Trees

        register(context, WEIRWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WEIRWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModBLocks.WEIRWOOD_SAPLING.get()));

        register(context, OAK2_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OAK2_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        Blocks.OAK_SAPLING));


        register(context, SENTINEL_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SENTINEL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SENTINEL_SAPLING.get()));

        register(context, PINE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.PINE_SAPLING.get()));

        register(context, IRONWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.IRONWOOD_SAPLING.get()));

        register(context, HAWTHORN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAWTHORN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.HAWTHORN_SAPLING.get()));

        register(context, CHESTNUT_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHESTNUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.CHESTNUT_SAPLING.get()));

        register(context, CEDAR_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CEDAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.CEDAR_SAPLING.get()));

        register(context, BEECH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.BEECH_SAPLING.get()));

        register(context, ASH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.ASH_SAPLING.get()));

        register(context, BLACKBARK_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKBARK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.BLACKBARK_SAPLING.get()));

        register(context, ASPEN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASPEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.ASPEN_SAPLING.get()));

        register(context, ALDER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALDER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.ALDER_SAPLING.get()));

        //Rare Trees For plains
        register(context, WEIRWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WEIRWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.WEIRWOOD_SAPLING.get()));

        register(context, OAK2_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OAK2_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        Blocks.OAK_SAPLING));

        register(context, SENTINEL_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SENTINEL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SENTINEL_SAPLING.get()));

        register(context, PINE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.PINE_SAPLING.get()));

        register(context, IRONWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.IRONWOOD_SAPLING.get()));

        register(context, HAWTHORN_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAWTHORN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.HAWTHORN_SAPLING.get()));

        register(context, CHESTNUT_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHESTNUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.CHESTNUT_SAPLING.get()));

        register(context, CEDAR_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CEDAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.CEDAR_SAPLING.get()));

        register(context, BEECH_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.BEECH_SAPLING.get()));

        register(context, ASH_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.ASH_SAPLING.get()));

        register(context, BLACKBARK_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKBARK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.BLACKBARK_SAPLING.get()));

        register(context, ASPEN_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASPEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.ASPEN_SAPLING.get()));

        register(context, ALDER_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALDER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.ALDER_SAPLING.get()));






                        register(context, THORN_BUSH_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.THORN_BUSH_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, THISTLE_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.THISTLE_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, TANSY_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TANSY_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, SPICEFLOWER_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SPICEFLOWER_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, SEDGE_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SEDGE_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, SAFFRON_CROCUS_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SAFFRON_CROCUS_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, POISON_KISSES_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.POISON_KISSES_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, PENNYROYAL_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PENNYROYAL_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, OPIUM_POPPY_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OPIUM_POPPY_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, NIGHTSHADE_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.NIGHTSHADE_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, MOONBLOOM_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.MOONBLOOM_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, LUNGWORT_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LUNGWORT_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, LIVERWORT_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LIVERWORT_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, LAVENDER_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LAVENDER_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, LADYS_LACE_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LADYS_LACE_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, GORSE_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GORSE_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, GOLDENROD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENROD_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, GOLDENCUP_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENCUP_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, GOATHEAD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GOATHEAD_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, GINGER_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GINGER_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, GILLYFLOWER_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GILLYFLOWER_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, FROSTFIRE_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FROSTFIRE_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, FORGET_ME_NOT_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FORGET_ME_NOT_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, EVENING_STAR_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.EVENING_STAR_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, DRAGONS_BREATH_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DRAGONS_BREATH_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, COLDSNAP_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.COLDSNAP_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, BLOODBLOOM_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODBLOOM_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, BLACK_LOTUS_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_LOTUS_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, BLUE_ROSE_BUSH_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_ROSE_BUSH_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, WHITE_ROSE_BUSH_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.WHITE_ROSE_BUSH_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, DUSKY_ROSE_BUSH_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DUSKY_ROSE_BUSH_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, WINTER_ROSE_BUSH_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.WINTER_ROSE_BUSH_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, RED_ROSE_BUSH_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_ROSE_BUSH_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(32),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));
    }


    // Helper method to register a ResourceKey for a placed feature
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, AGoTMod.id(name));
    }

    // Helper method to register a placed feature
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        // Use the context to register a new PlacedFeature with the specified key, configuration, and modifiers
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
