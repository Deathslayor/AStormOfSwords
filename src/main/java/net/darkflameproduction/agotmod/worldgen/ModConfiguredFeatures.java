package net.darkflameproduction.agotmod.worldgen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    // Define a ResourceKey for the configured feature of overworld tin ore
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIN_ORE = registerKey("tin_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_IRON_ORE = registerKey("iron_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_COAL_ORE = registerKey("coal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_COPPER_ORE = registerKey("copper_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_GOLD_ORE = registerKey("gold_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_LAPIS_ORE = registerKey("lapis_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_EMERALD_ORE = registerKey("emerald_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_DIAMONDS_ORE = registerKey("diamonds_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_BLOODSTONE_ORE = registerKey("bloodstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CHALCEDONY_ORE = registerKey("chalcedony_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_AMBER_ORE = registerKey("amber_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_AMETHYST_ORE = registerKey("amethyst_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CARNELIAN_ORE = registerKey("carnelian_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_GARNET_ORE = registerKey("garnet_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_JADE_ORE = registerKey("jade_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_JASPER_ORE = registerKey("jasper_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MALACHITE_ORE = registerKey("malachite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_RUBY_ORE = registerKey("ruby_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_ONYX_ORE = registerKey("onyx_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_OPAL_ORE = registerKey("opal_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SAPPHIRE_ORE = registerKey("sapphire_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_MOONSTONE_ORE = registerKey("moonstone_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TIGERS_EYE_ORE = registerKey("tigers_eye_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TOPAZ_ORE = registerKey("topaz_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_TOURMALINE_ORE = registerKey("tourmaline_ore");
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> SYCAMORE_RARE_KEY = registerKey("sycamore_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WEIRWOOD_RARE_KEY = registerKey("weirwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK2_RARE_KEY = registerKey("oak2_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SENTINEL_RARE_KEY = registerKey("sentinel_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINE_RARE_KEY = registerKey("pine_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> IRONWOOD_RARE_KEY = registerKey("ironwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HAWTHORN_RARE_KEY = registerKey("hawthorn_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHESTNUT_RARE_KEY = registerKey("chestnut_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CEDAR_RARE_KEY = registerKey("cedar_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BEECH_RARE_KEY = registerKey("beech_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASH_RARE_KEY = registerKey("ash_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKBARK_RARE_KEY = registerKey("blackbark_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ASPEN_RARE_KEY = registerKey("aspen_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALDER_RARE_KEY = registerKey("alder_rare");
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_BLOCK_PATCH_KEY = registerKey("grass_block_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CLAY_PATCH_KEY = registerKey("clay_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SEAGRASS_KEY = registerKey("seagrass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> KELP_KEY = registerKey("kelp");
    public static final ResourceKey<ConfiguredFeature<?, ?>> QUAGMIRE_PATCH_KEY = registerKey("quagmire");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FERN_KEY = registerKey("fern");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_FERN_KEY = registerKey("large_fern");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRASS_KEY = registerKey("grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_GRASS_KEY = registerKey("tall_grass");






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

        List<OreConfiguration.TargetBlockState> overworldIronOre = List.of(
                OreConfiguration.target(stoneReplaceables, Blocks.IRON_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, Blocks.DEEPSLATE_IRON_ORE.defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldCoalOre = List.of(
                OreConfiguration.target(stoneReplaceables, Blocks.COAL_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, Blocks.DEEPSLATE_COAL_ORE.defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldCopperOre = List.of(
                OreConfiguration.target(stoneReplaceables, Blocks.COPPER_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, Blocks.DEEPSLATE_COPPER_ORE.defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldGoldOre = List.of(
                OreConfiguration.target(stoneReplaceables, Blocks.GOLD_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, Blocks.DEEPSLATE_GOLD_ORE.defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldLapisOre = List.of(
                OreConfiguration.target(stoneReplaceables, Blocks.LAPIS_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, Blocks.DEEPSLATE_LAPIS_ORE.defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldEmeraldOre = List.of(
                OreConfiguration.target(stoneReplaceables, Blocks.EMERALD_ORE.defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, Blocks.DEEPSLATE_EMERALD_ORE.defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldDiamondsOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.DIAMONDS_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.DIAMONDS_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldBloodstoneOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.BLOODSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldChalcedonyOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.CHALCEDONY_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldAmberOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.AMBER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.AMBER_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldAmethystOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.AMETHYST_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.AMETHYST_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldCarnelianOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.CARNELIAN_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.CARNELIAN_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldGarnetOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.GARNET_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.GARNET_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldJadeOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.JADE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.JADE_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldJasperOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.JASPER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.JASPER_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldMalachiteOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.MALACHITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.MALACHITE_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldRubyOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.RUBY_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.RUBY_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldOnyxOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.ONYX_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.ONYX_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldOpalOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.OPAL_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.OPAL_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldSapphireOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.SAPPHIRE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldMoonstoneOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.MOONSTONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.MOONSTONE_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldTigersEyeOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.TIGERS_EYE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldTopazOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.TOPAZ_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.TOPAZ_DEEPSLATE_ORE.get().defaultBlockState())
        );

        List<OreConfiguration.TargetBlockState> overworldTourmalineOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.TOURMALINE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.TOURMALINE_DEEPSLATE_ORE.get().defaultBlockState())
        );


        // Register the configured feature for overworld tin ore
        register(context, OVERWORLD_TIN_ORE, Feature.ORE, new OreConfiguration(overworldTinOre, 9));
        register(context, OVERWORLD_IRON_ORE, Feature.ORE, new OreConfiguration(overworldIronOre, 9));
        register(context, OVERWORLD_COAL_ORE, Feature.ORE, new OreConfiguration(overworldCoalOre, 17));
        register(context, OVERWORLD_COPPER_ORE, Feature.ORE, new OreConfiguration(overworldCopperOre, 9));
        register(context, OVERWORLD_GOLD_ORE, Feature.ORE, new OreConfiguration(overworldGoldOre, 9));
        register(context, OVERWORLD_LAPIS_ORE, Feature.ORE, new OreConfiguration(overworldLapisOre, 7));
        register(context, OVERWORLD_EMERALD_ORE, Feature.ORE, new OreConfiguration(overworldEmeraldOre, 4));
        register(context, OVERWORLD_DIAMONDS_ORE, Feature.ORE, new OreConfiguration(overworldDiamondsOre, 8));
        register(context, OVERWORLD_BLOODSTONE_ORE, Feature.ORE, new OreConfiguration(overworldBloodstoneOre, 8));
        register(context, OVERWORLD_CHALCEDONY_ORE, Feature.ORE, new OreConfiguration(overworldChalcedonyOre, 8));
        register(context, OVERWORLD_AMBER_ORE, Feature.ORE, new OreConfiguration(overworldAmberOre, 8));
        register(context, OVERWORLD_AMETHYST_ORE, Feature.ORE, new OreConfiguration(overworldAmethystOre, 8));
        register(context, OVERWORLD_CARNELIAN_ORE, Feature.ORE, new OreConfiguration(overworldCarnelianOre, 8));
        register(context, OVERWORLD_GARNET_ORE, Feature.ORE, new OreConfiguration(overworldGarnetOre, 8));
        register(context, OVERWORLD_JADE_ORE, Feature.ORE, new OreConfiguration(overworldJadeOre, 8));
        register(context, OVERWORLD_JASPER_ORE, Feature.ORE, new OreConfiguration(overworldJasperOre, 8));
        register(context, OVERWORLD_MALACHITE_ORE, Feature.ORE, new OreConfiguration(overworldMalachiteOre, 8));
        register(context, OVERWORLD_RUBY_ORE, Feature.ORE, new OreConfiguration(overworldRubyOre, 8));
        register(context, OVERWORLD_ONYX_ORE, Feature.ORE, new OreConfiguration(overworldOnyxOre, 8));
        register(context, OVERWORLD_OPAL_ORE, Feature.ORE, new OreConfiguration(overworldOpalOre, 8));
        register(context, OVERWORLD_SAPPHIRE_ORE, Feature.ORE, new OreConfiguration(overworldSapphireOre, 8));
        register(context, OVERWORLD_MOONSTONE_ORE, Feature.ORE, new OreConfiguration(overworldMoonstoneOre, 8));
        register(context, OVERWORLD_TIGERS_EYE_ORE, Feature.ORE, new OreConfiguration(overworldTigersEyeOre, 8));
        register(context, OVERWORLD_TOPAZ_ORE, Feature.ORE, new OreConfiguration(overworldTopazOre, 8));
        register(context, OVERWORLD_TOURMALINE_ORE, Feature.ORE, new OreConfiguration(overworldTourmalineOre, 8));




        //Forest Trees

        register(context, SYCAMORE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.SYCAMORE_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),

                // Modified this line to make leaves persistent
                BlockStateProvider.simple(ModBLocks.SYCAMORE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),

                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());


        register(context, WEIRWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.WEIRWOOD_LOG.get()),
                // Taller trunk with more branch variance to penetrate deeper into leaves
                new FancyTrunkPlacer(9, 6, 6),
                BlockStateProvider.simple(ModBLocks.WEIRWOOD_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                // Increased foliage height and offset to surround the trunk more
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, OAK2_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),  // Use oak logs for the Oak tree
                new FancyTrunkPlacer(7, 2, 3),  // Same trunk as Weirwood tree
                BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Use oak leaves for the Oak tree
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, SENTINEL_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.SENTINEL_LOG.get()),  // Sentinel log
                new StraightTrunkPlacer(12, 3, 3),  // Same trunk structure
                BlockStateProvider.simple(ModBLocks.SENTINEL_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Sentinel leaves
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        register(context, PINE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.PINE_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.PINE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build());

        register(context, IRONWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.IRONWOOD_LOG.get()),  // Ironwood log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk
                BlockStateProvider.simple(ModBLocks.IRONWOOD_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Ironwood leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, HAWTHORN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.HAWTHORN_LOG.get()),  // Hawthorn log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk
                BlockStateProvider.simple(ModBLocks.HAWTHORN_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Hawthorn leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, CHESTNUT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.CHESTNUT_LOG.get()),  // Chestnut log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk
                BlockStateProvider.simple(ModBLocks.CHESTNUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Chestnut leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, CEDAR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.CEDAR_LOG.get()),  // Cedar log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk
                BlockStateProvider.simple(ModBLocks.CEDAR_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Cedar leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BEECH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.BEECH_LOG.get()),  // Beech log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk
                BlockStateProvider.simple(ModBLocks.BEECH_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Beech leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ASH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ASH_LOG.get()),  // Ash log instead of Weirwood log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk
                BlockStateProvider.simple(ModBLocks.ASH_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Ash leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BLACKBARK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.BLACKBARK_LOG.get()),  // Blackbark log
                new FancyTrunkPlacer(7, 2, 3),  // Mimicking Cherry tree trunk
                BlockStateProvider.simple(ModBLocks.BLACKBARK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),  // Beech leaves instead of Weirwood leaves
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ASPEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ASPEN_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ASPEN_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)
        ).build());

        register(context, ALDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ALDER_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ALDER_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)
        ).build());

//Rare Trees For Plains

        register(context, SYCAMORE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.SYCAMORE_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.SYCAMORE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, WEIRWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.WEIRWOOD_LOG.get()),
                new FancyTrunkPlacer(9, 6, 6),
                BlockStateProvider.simple(ModBLocks.WEIRWOOD_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, OAK2_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, SENTINEL_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.SENTINEL_LOG.get()),
                new StraightTrunkPlacer(12, 3, 3),
                BlockStateProvider.simple(ModBLocks.SENTINEL_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PINE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.PINE_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.PINE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, IRONWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.IRONWOOD_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.IRONWOOD_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, HAWTHORN_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.HAWTHORN_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.HAWTHORN_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, CHESTNUT_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.CHESTNUT_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.CHESTNUT_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, CEDAR_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.CEDAR_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.CEDAR_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BEECH_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.BEECH_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.BEECH_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ASH_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ASH_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.ASH_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, BLACKBARK_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.BLACKBARK_LOG.get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.BLACKBARK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, ASPEN_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ASPEN_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ASPEN_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)).build());

        register(context, ALDER_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.ALDER_LOG.get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.ALDER_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)).build());


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

        register(context, LARGE_FERN_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.LARGE_FERN)
                                )
                        )
                ));

        register(context, FERN_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.FERN)
                                )
                        )
                ));

        register(context, GRASS_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        16,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.SHORT_GRASS)
                                )
                        )
                ));

        register(context, TALL_GRASS_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        8,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.TALL_GRASS)
                                )
                        )
                ));

        register(context, GRASS_BLOCK_PATCH_KEY, Feature.DISK,
                new DiskConfiguration(
                        RuleBasedBlockStateProvider.simple(Blocks.GRASS_BLOCK),  // The block to place (grass instead of clay)
                        BlockPredicate.matchesBlocks(
                                Blocks.DIRT,
                                Blocks.CLAY,
                                Blocks.GRAVEL,
                                Blocks.SAND,
                                Blocks.PODZOL,
                                Blocks.MUD,
                                Blocks.STONE
                        ),  // Blocks that can be replaced (same as vanilla clay)
                        UniformInt.of(3, 6),  // Random radius between 2-3 blocks (same as vanilla)
                        2  // Height (same as vanilla)
                )
        );

        register(context, CLAY_PATCH_KEY, Feature.DISK,
                new DiskConfiguration(
                        RuleBasedBlockStateProvider.simple(Blocks.CLAY),  // The block to place (grass instead of clay)
                        BlockPredicate.matchesBlocks(
                                Blocks.DIRT,
                                Blocks.GRAVEL,
                                Blocks.MUD,
                                Blocks.SAND
                        ),  // Blocks that can be replaced (same as vanilla clay)
                        UniformInt.of(3, 6),  // Random radius between 2-3 blocks (same as vanilla)
                        2  // Height (same as vanilla)
                )
        );

        register(context, QUAGMIRE_PATCH_KEY, Feature.DISK,
                new DiskConfiguration(
                        RuleBasedBlockStateProvider.simple(ModBLocks.QUAGMIRE.get()),  // The block to place (grass instead of clay)
                        BlockPredicate.matchesBlocks(
                                Blocks.DIRT,
                                Blocks.GRAVEL,
                                Blocks.MUD,
                                Blocks.GRASS_BLOCK,
                                Blocks.SAND
                        ),  // Blocks that can be replaced (same as vanilla clay)
                        UniformInt.of(3, 6),  // Random radius between 2-3 blocks (same as vanilla)
                        4  // Height (same as vanilla)
                )
        );

        register(context, SEAGRASS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SEAGRASS)));

        register(context, KELP_KEY, Feature.KELP, NoneFeatureConfiguration.INSTANCE);

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
