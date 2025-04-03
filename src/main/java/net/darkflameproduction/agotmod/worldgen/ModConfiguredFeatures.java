package net.darkflameproduction.agotmod.worldgen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
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
                new FancyTrunkPlacer(9, 3, 4),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.WEIRWOOD_LEAVES.get()),
                new CherryFoliagePlacer(ConstantInt.of(5), ConstantInt.of(4), ConstantInt.of(6), 0.25f, 0.1f, 0.5f, 0.8f),

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
                new FancyTrunkPlacer(8, 3, 4),  // Mimicking Cherry tree trunk

                BlockStateProvider.simple(ModBLocks.BLACKBARK_LEAVES.get()),  // Blackbark leaves
                new CherryFoliagePlacer(ConstantInt.of(5), ConstantInt.of(4), ConstantInt.of(6), 0.25f, 0.1f, 0.5f, 0.8f),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ASPEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ASPEN_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ASPEN_LEAVES.get()),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        register(context, ALDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ALDER_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ALDER_LEAVES.get()),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(3), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());


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
