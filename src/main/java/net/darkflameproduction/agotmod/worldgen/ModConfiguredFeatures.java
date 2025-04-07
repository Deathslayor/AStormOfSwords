package net.darkflameproduction.agotmod.worldgen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    // Define a ResourceKey for the configured feature of overworld tin ore
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SYCAMORE_KEY = registerKey("sycamore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WEIRWOOD_KEY = registerKey("weirwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK2_KEY = registerKey("oak2");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SENTINEL_KEY = registerKey("sentinel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_KEY = registerKey("pine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD_KEY = registerKey("ironwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN_KEY = registerKey("hawthorn");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHESTNUT_KEY = registerKey("chestnut");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CEDAR_KEY = registerKey("cedar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BEECH_KEY = registerKey("beech");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASH_KEY = registerKey("ash");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKBARK_KEY = registerKey("blackbark");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_KEY = registerKey("aspen");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALDER_KEY = registerKey("alder");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THORN_BUSH_KEY = registerKey("thorn_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THISTLE_KEY = registerKey("thistle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TANSY_KEY = registerKey("tansy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPICEFLOWER_KEY = registerKey("spiceflower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SEDGE_KEY = registerKey("sedge");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAFFRON_CROCUS_KEY = registerKey("saffron_crocus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POISON_KISSES_KEY = registerKey("poison_kisses");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PENNYROYAL_KEY = registerKey("pennyroyal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OPIUM_POPPY_KEY = registerKey("opium_poppy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NIGHTSHADE_KEY = registerKey("nightshade");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOONBLOOM_KEY = registerKey("moonbloom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LUNGWORT_KEY = registerKey("lungwort");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIVERWORT_KEY = registerKey("liverwort");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVENDER_KEY = registerKey("lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LADYS_LACE_KEY = registerKey("ladys_lace");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GORSE_KEY = registerKey("gorse");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD_KEY = registerKey("goldenrod");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENCUP_KEY = registerKey("goldencup");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOATHEAD_KEY = registerKey("goathead");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GINGER_KEY = registerKey("ginger");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GILLYFLOWER_KEY = registerKey("gillyflower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FROSTFIRE_KEY = registerKey("frostfire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FORGET_ME_NOT_KEY = registerKey("forget_me_not");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EVENING_STAR_KEY = registerKey("evening_star");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRAGONS_BREATH_KEY = registerKey("dragons_breath");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLDSNAP_KEY = registerKey("coldsnap");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ROSE_KEY = registerKey("blue_rose");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODBLOOM_KEY = registerKey("bloodbloom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_LOTUS_KEY = registerKey("black_lotus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ROSE_BUSH_KEY = registerKey("blue_rose_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_ROSE_BUSH_KEY = registerKey("white_rose_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DUSKY_ROSE_BUSH_KEY = registerKey("dusky_rose_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WINTER_ROSE_BUSH_KEY = registerKey("winter_rose_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_ROSE_BUSH_KEY = registerKey("red_rose_bush");



    // Bootstrap method for initializing configured features
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        // Define rule tests for replaceable blocks in different dimensions
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacebles = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        // Define target block states for overworld tin ore
        List<OreConfiguration.TargetBlockState> overworldTinOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.TIN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())
        );

        // Register the configured feature for overworld tin ore
        register(context, OVERWORLD_TIN_ORE, Feature.ORE, new OreConfiguration(overworldTinOre, 5));


        register(context, SYCAMORE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.SYCAMORE_LOG.get()),  // Ironwood log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.SYCAMORE_LEAVES.get()),  // Ironwood leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());


        register(context, WEIRWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.WEIRWOOD_LOG.get()),
                // Taller trunk with more branch variance to penetrate deeper into leaves
                new FancyTrunkPlacer(9, 6, 6),
                BlockStateProvider.simple(ModBLocks.WEIRWOOD_LEAVES.get()),
                // Increased foliage height and offset to surround the trunk more
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, OAK2_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),  // Use oak logs for the Oak tree
                new FancyTrunkPlacer(7, 2, 3),  // Same trunk as Weirwood tree

                BlockStateProvider.simple(Blocks.OAK_LEAVES),  // Use oak leaves for the Oak tree
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, SENTINEL_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.SENTINEL_LOG.get()),  // Sentinel log
                new StraightTrunkPlacer(12, 3, 3),  // Same trunk structure

                BlockStateProvider.simple(ModBLocks.SENTINEL_LEAVES.get()),  // Sentinel leaves
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),

                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        register(context, PINE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.PINE_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.PINE_LEAVES.get()),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        register(context, IRONWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.IRONWOOD_LOG.get()),  // Ironwood log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.IRONWOOD_LEAVES.get()),  // Ironwood leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, HAWTHORN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.HAWTHORN_LOG.get()),  // Hawthorn log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.HAWTHORN_LEAVES.get()),  // Hawthorn leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, CHESTNUT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.CHESTNUT_LOG.get()),  // Chestnut log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.CHESTNUT_LEAVES.get()),  // Chestnut leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, CEDAR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.CEDAR_LOG.get()),  // Cedar log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.CEDAR_LEAVES.get()),  // Cedar leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BEECH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.BEECH_LOG.get()),  // Beech log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.BEECH_LEAVES.get()),  // Beech leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ASH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ASH_LOG.get()),  // Ash log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.ASH_LEAVES.get()),  // Ash leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BLACKBARK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.BLACKBARK_LOG.get()),  // Blackbark log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.BLACKBARK_LEAVES.get()),  // Beech leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ASPEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ASPEN_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ASPEN_LEAVES.get()),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)
        ).build());

        register(context, ALDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ALDER_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ALDER_LEAVES.get()),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)
        ).build());

        register(context, THORN_BUSH_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.THORN_BUSH.get())
                                )
                        )
                ));

        register(context, THISTLE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.THISTLE.get())
                                )
                        )
                ));

        register(context, TANSY_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.TANSY.get())
                                )
                        )
                ));

        register(context, SPICEFLOWER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.SPICEFLOWER.get())
                                )
                        )
                ));

        register(context, SEDGE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.SEDGE.get())
                                )
                        )
                ));

        register(context, SAFFRON_CROCUS_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.SAFFRON_CROCUS.get())
                                )
                        )
                ));

        register(context, POISON_KISSES_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.POISON_KISSES.get())
                                )
                        )
                ));

        register(context, PENNYROYAL_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.PENNYROYAL.get())
                                )
                        )
                ));

        register(context, OPIUM_POPPY_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.OPIUM_POPPY.get())
                                )
                        )
                ));

        register(context, NIGHTSHADE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.NIGHTSHADE.get())
                                )
                        )
                ));

        register(context, MOONBLOOM_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.MOONBLOOM.get())
                                )
                        )
                ));

        register(context, LUNGWORT_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.LUNGWORT.get())
                                )
                        )
                ));

        register(context, LIVERWORT_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.LIVERWORT.get())
                                )
                        )
                ));

        register(context, LAVENDER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.LAVENDER.get())
                                )
                        )
                ));

        register(context, LADYS_LACE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.LADYS_LACE.get())
                                )
                        )
                ));

        register(context, GORSE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.GORSE.get())
                                )
                        )
                ));

        register(context, GOLDENROD_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.GOLDENROD.get())
                                )
                        )
                ));

        register(context, GOLDENCUP_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.GOLDENCUP.get())
                                )
                        )
                ));

        register(context, GOATHEAD_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.GOATHEAD.get())
                                )
                        )
                ));

        register(context, GINGER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.GINGER.get())
                                )
                        )
                ));

        register(context, GILLYFLOWER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.GILLYFLOWER.get())
                                )
                        )
                ));

        register(context, FROSTFIRE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.FROSTFIRE.get())
                                )
                        )
                ));

        register(context, FORGET_ME_NOT_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.FORGET_ME_NOT.get())
                                )
                        )
                ));

        register(context, EVENING_STAR_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.EVENING_STAR.get())
                                )
                        )
                ));

        register(context, DRAGONS_BREATH_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.DRAGONS_BREATH.get())
                                )
                        )
                ));

        register(context, COLDSNAP_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.COLDSNAP.get())
                                )
                        )
                ));

        register(context, BLUE_ROSE_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.BLUE_ROSE.get())
                                )
                        )
                ));

        register(context, BLOODBLOOM_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.BLOODBLOOM.get())
                                )
                        )
                ));

        register(context, BLACK_LOTUS_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.BLACK_LOTUS.get())
                                )
                        )
                ));

        register(context, BLUE_ROSE_BUSH_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.BLUE_ROSE_BUSH.get())
                                )
                        )
                ));

        register(context, WHITE_ROSE_BUSH_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.WHITE_ROSE_BUSH.get())
                                )
                        )
                ));

        register(context, DUSKY_ROSE_BUSH_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.DUSKY_ROSE_BUSH.get())
                                )
                        )
                ));

        register(context, WINTER_ROSE_BUSH_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.WINTER_ROSE_BUSH.get())
                                )
                        )
                ));

        register(context, RED_ROSE_BUSH_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.RED_ROSE_BUSH.get())
                                )
                        )
                ));



    }

    // Register a ResourceKey for a configured feature
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        // Create and return a new ResourceKey with the specified mod ID and name
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, AGoTMod.id(name));
    }

    // Register a configured feature
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        // Use the context to register a new ConfiguredFeature with the specified key, feature, and configuration
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
