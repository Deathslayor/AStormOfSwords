package net.stormofsorts.agotmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.block.ModBLocks;

import java.util.List;

public class ModConfiguredFeatures {
    // Define a ResourceKey for the configured feature of overworld tin ore
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE = registerKey("tin_ore");


    public static final ResourceKey<ConfiguredFeature<? , ?>> SYCAMORE_KEY = registerKey("sycamore");

    // Bootstrap method for initializing configured features
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
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

        /*
        register(context, SYCAMORE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks)
        ));

         */

    }

    // Register a ResourceKey for a configured feature
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        // Create and return a new ResourceKey with the specified mod ID and name
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(AGoTMod.MOD_ID, name));
    }

    // Register a configured feature
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        // Use the context to register a new ConfiguredFeature with the specified key, feature, and configuration
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
