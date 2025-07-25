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
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SILVER_ORE = registerKey("silver_ore");
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_CHERRY_KEY = registerKey("black_cherry");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OLIVE_KEY = registerKey("black_olive");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRABAPPLE_KEY = registerKey("crabapple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_KEY = registerKey("olive");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_CHERRY_KEY = registerKey("white_cherry");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_CHERRY_KEY = registerKey("red_cherry");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_KEY = registerKey("fir");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_KEY = registerKey("willow");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WORMTREE_KEY = registerKey("wormtree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALMOND_KEY = registerKey("almond");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_KEY = registerKey("apple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APRICOT_KEY = registerKey("apricot");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BAOBAB_KEY = registerKey("baobab");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_COTTONWOOD_KEY = registerKey("black_cottonwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKTHORN_KEY = registerKey("blackthorn");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_ORANGE_KEY = registerKey("blood_orange");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_KEY = registerKey("bloodwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_MAHOE_KEY = registerKey("blue_mahoe");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COTTONWOOD_KEY = registerKey("cottonwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DATEPALM_KEY = registerKey("datepalm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_KEY = registerKey("ebony");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIG_KEY = registerKey("fig");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIREPLUM_KEY = registerKey("fireplum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENHEART_KEY = registerKey("goldenheart");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_KEY = registerKey("lemon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIME_KEY = registerKey("lime");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LINDEN_KEY = registerKey("linden");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_KEY = registerKey("mahogany");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_KEY = registerKey("maple");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYRRH_KEY = registerKey("myrrh");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NIGHTWOOD_KEY = registerKey("nightwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NUTMEG_KEY = registerKey("nutmeg");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_KEY = registerKey("orange");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH_KEY = registerKey("peach");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_KEY = registerKey("pear");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PECAN_KEY = registerKey("pecan");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERSIMMON_KEY = registerKey("persimmon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_IVORY_KEY = registerKey("pink_ivory");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLUM_KEY = registerKey("plum");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POMEGRANATE_KEY = registerKey("pomegranate");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLEHEART_KEY = registerKey("purpleheart");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_KEY = registerKey("redwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDALWOOD_KEY = registerKey("sandalwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDBEGGAR_KEY = registerKey("sandbeggar");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIGERWOOD_KEY = registerKey("tigerwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YEW_KEY = registerKey("yew");
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_CHERRY_RARE_KEY = registerKey("black_cherry_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_OLIVE_RARE_KEY = registerKey("black_olive_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRABAPPLE_RARE_KEY = registerKey("crabapple_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OLIVE_RARE_KEY = registerKey("olive_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WHITE_CHERRY_RARE_KEY = registerKey("white_cherry_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_CHERRY_RARE_KEY = registerKey("red_cherry_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_RARE_KEY = registerKey("fir_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_RARE_KEY = registerKey("willow_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WORMTREE_RARE_KEY = registerKey("wormtree_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALMOND_RARE_KEY = registerKey("almond_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APPLE_RARE_KEY = registerKey("apple_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> APRICOT_RARE_KEY = registerKey("apricot_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BAOBAB_RARE_KEY = registerKey("baobab_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_COTTONWOOD_RARE_KEY = registerKey("black_cottonwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKTHORN_RARE_KEY = registerKey("blackthorn_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_ORANGE_RARE_KEY = registerKey("blood_orange_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_RARE_KEY = registerKey("bloodwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_MAHOE_RARE_KEY = registerKey("blue_mahoe_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COTTONWOOD_RARE_KEY = registerKey("cottonwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DATEPALM_RARE_KEY = registerKey("datepalm_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EBONY_RARE_KEY = registerKey("ebony_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIG_RARE_KEY = registerKey("fig_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIREPLUM_RARE_KEY = registerKey("fireplum_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENHEART_RARE_KEY = registerKey("goldenheart_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LEMON_RARE_KEY = registerKey("lemon_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LIME_RARE_KEY = registerKey("lime_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LINDEN_RARE_KEY = registerKey("linden_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_RARE_KEY = registerKey("mahogany_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_RARE_KEY = registerKey("maple_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYRRH_RARE_KEY = registerKey("myrrh_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NIGHTWOOD_RARE_KEY = registerKey("nightwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NUTMEG_RARE_KEY = registerKey("nutmeg_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_RARE_KEY = registerKey("orange_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEACH_RARE_KEY = registerKey("peach_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PEAR_RARE_KEY = registerKey("pear_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PECAN_RARE_KEY = registerKey("pecan_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PERSIMMON_RARE_KEY = registerKey("persimmon_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_IVORY_RARE_KEY = registerKey("pink_ivory_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PLUM_RARE_KEY = registerKey("plum_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POMEGRANATE_RARE_KEY = registerKey("pomegranate_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PURPLEHEART_RARE_KEY = registerKey("purpleheart_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_RARE_KEY = registerKey("redwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDALWOOD_RARE_KEY = registerKey("sandalwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDBEGGAR_RARE_KEY = registerKey("sandbeggar_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIGERWOOD_RARE_KEY = registerKey("tigerwood_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YEW_RARE_KEY = registerKey("yew_rare");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THORN_BUSH_KEY = registerKey("thorn_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THISTLE_KEY = registerKey("thistle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TANSY_KEY = registerKey("tansy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPICEFLOWER_KEY = registerKey("spiceflower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SEDGE_KEY = registerKey("sedge");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAFFRON_CROCUS_KEY = registerKey("saffron_crocus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POISON_KISSES_KEY = registerKey("poison_kisses");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PENNYROYAL_KEY = registerKey("pennyroyal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OPIUM_POPPY_WILD_KEY = registerKey("opium_poppy_wild");
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

        List<OreConfiguration.TargetBlockState> overworldSilverOre = List.of(
                OreConfiguration.target(stoneReplaceables, ModBLocks.SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBLocks.DEEPSLATE_SILVER_ORE.get().defaultBlockState())
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
        register(context, OVERWORLD_SILVER_ORE, Feature.ORE, new OreConfiguration(overworldSilverOre, 6));
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

// Sycamore tree
        register(context, SYCAMORE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sycamore").get()),
                new FancyTrunkPlacer(7, 2, 3),
                // Makes leaves persistent
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sycamore").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Sentinel tree
        register(context, SENTINEL_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sentinel").get()),
                new StraightTrunkPlacer(12, 3, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sentinel").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Pine tree
        register(context, PINE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pine").get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pine").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Ironwood tree
        register(context, IRONWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("ironwood").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("ironwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Hawthorn tree
        register(context, HAWTHORN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("hawthorn").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("hawthorn").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Chestnut tree
        register(context, CHESTNUT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("chestnut").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("chestnut").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Cedar tree
        register(context, CEDAR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("cedar").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("cedar").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Beech tree
        register(context, BEECH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("beech").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("beech").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Ash tree
        register(context, ASH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("ash").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("ash").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Blackbark tree
        register(context, BLACKBARK_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blackbark").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blackbark").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Aspen tree
        register(context, ASPEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("aspen").get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("aspen").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)).build());

// Alder tree
        register(context, ALDER_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("alder").get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("alder").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)).build());

// Rare Trees For Plains

// Rare Sycamore tree
        register(context, SYCAMORE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sycamore").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sycamore").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Sentinel tree
        register(context, SENTINEL_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sentinel").get()),
                new StraightTrunkPlacer(12, 3, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sentinel").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Rare Pine tree
        register(context, PINE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pine").get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pine").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Rare Ironwood tree
        register(context, IRONWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("ironwood").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("ironwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Hawthorn tree
        register(context, HAWTHORN_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("hawthorn").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("hawthorn").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Chestnut tree
        register(context, CHESTNUT_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("chestnut").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("chestnut").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Cedar tree
        register(context, CEDAR_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("cedar").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("cedar").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Beech tree
        register(context, BEECH_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("beech").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("beech").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Ash tree
        register(context, ASH_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("ash").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("ash").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Blackbark tree
        register(context, BLACKBARK_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blackbark").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blackbark").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Aspen tree
        register(context, ASPEN_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("aspen").get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("aspen").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)).build());

// Rare Alder tree
        register(context, ALDER_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("alder").get()),
                new StraightTrunkPlacer(7, 2, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("alder").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(2), ConstantInt.of(3), ConstantInt.of(2)),
                new TwoLayersFeatureSize(1, 1, 1)).build());
// BLACK_BERRY tree
        register(context, BLACK_CHERRY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("black_cherry").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("black_cherry").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// BLACK_OLIVE tree
        register(context, BLACK_OLIVE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("black_olive").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("black_olive").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// CRABAPPLE tree
        register(context, CRABAPPLE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("crabapple").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("crabapple").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// OLIVE tree
        register(context, OLIVE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("olive").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("olive").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// WHITE_CHERRY tree
        register(context, WHITE_CHERRY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("white_cherry").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("white_cherry").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// RED_CHERRY tree
        register(context, RED_CHERRY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("red_cherry").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("red_cherry").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// FIR tree (spruce-like)
        register(context, FIR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("fir").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("fir").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// WILLOW tree
        register(context, WILLOW_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("willow").get()),
                new FancyTrunkPlacer(6, 3, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("willow").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// WORMTREE tree
        register(context, WORMTREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("wormtree").get()),
                new FancyTrunkPlacer(8, 4, 4),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("wormtree").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare Trees

// Rare BLACK_BERRY tree
        register(context, BLACK_CHERRY_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("black_cherry").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("black_cherry").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare BLACK_OLIVE tree
        register(context, BLACK_OLIVE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("black_olive").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("black_olive").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare CRABAPPLE tree
        register(context, CRABAPPLE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("crabapple").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("crabapple").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare OLIVE tree
        register(context, OLIVE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("olive").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("olive").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare WHITE_CHERRY tree
        register(context, WHITE_CHERRY_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("white_cherry").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("white_cherry").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare RED_CHERRY tree
        register(context, RED_CHERRY_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("red_cherry").get()),
                new FancyTrunkPlacer(7, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("red_cherry").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare FIR tree (spruce-like)
        register(context, FIR_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("fir").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("fir").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// Rare WILLOW tree
        register(context, WILLOW_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("willow").get()),
                new FancyTrunkPlacer(6, 3, 2),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("willow").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

// Rare WORMTREE tree
        register(context, WORMTREE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("wormtree").get()),
                new FancyTrunkPlacer(8, 4, 4),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("wormtree").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(3), ConstantInt.of(5), 0.25f, 0.1f, 0.5f, 0.8f),
                new TwoLayersFeatureSize(1, 0, 2)).build());

        // ALMOND
        register(context, ALMOND_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("almond").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("almond").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, ALMOND_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("almond").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("almond").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// APPLE
        register(context, APPLE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("apple").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("apple").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, APPLE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("apple").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("apple").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// APRICOT
        register(context, APRICOT_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("apricot").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("apricot").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, APRICOT_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("apricot").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("apricot").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// BAOBAB
        register(context, BAOBAB_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("baobab").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("baobab").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, BAOBAB_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("baobab").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("baobab").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// BLACK_COTTONWOOD
        register(context, BLACK_COTTONWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("black_cottonwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("black_cottonwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, BLACK_COTTONWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("black_cottonwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("black_cottonwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// BLACKTHORN
        register(context, BLACKTHORN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blackthorn").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blackthorn").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, BLACKTHORN_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blackthorn").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blackthorn").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// BLOOD_ORANGE
        register(context, BLOOD_ORANGE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blood_orange").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blood_orange").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, BLOOD_ORANGE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blood_orange").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blood_orange").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// BLOODWOOD
        register(context, BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("bloodwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("bloodwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, BLOODWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("bloodwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("bloodwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// BLUE_MAHOE
        register(context, BLUE_MAHOE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blue_mahoe").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blue_mahoe").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, BLUE_MAHOE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("blue_mahoe").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("blue_mahoe").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// COTTONWOOD
        register(context, COTTONWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("cottonwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("cottonwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, COTTONWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("cottonwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("cottonwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// DATEPALM
        register(context, DATEPALM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("datepalm").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("datepalm").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, DATEPALM_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("datepalm").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("datepalm").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// EBONY
        register(context, EBONY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("ebony").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("ebony").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, EBONY_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("ebony").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("ebony").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// FIG
        register(context, FIG_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("fig").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("fig").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, FIG_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("fig").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("fig").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// FIREPLUM
        register(context, FIREPLUM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("fireplum").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("fireplum").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, FIREPLUM_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("fireplum").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("fireplum").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// GOLDENHEART
        register(context, GOLDENHEART_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("goldenheart").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("goldenheart").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, GOLDENHEART_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("goldenheart").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("goldenheart").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// LEMON
        register(context, LEMON_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("lemon").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("lemon").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, LEMON_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("lemon").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("lemon").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// LIME
        register(context, LIME_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("lime").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("lime").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, LIME_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("lime").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("lime").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// LINDEN
        register(context, LINDEN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("linden").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("linden").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, LINDEN_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("linden").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("linden").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// MAHOGANY
        register(context, MAHOGANY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("mahogany").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("mahogany").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, MAHOGANY_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("mahogany").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("mahogany").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// MAPLE
        register(context, MAPLE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("maple").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("maple").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, MAPLE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("maple").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("maple").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// MYRRH
        register(context, MYRRH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("myrrh").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("myrrh").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, MYRRH_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("myrrh").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("myrrh").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// NIGHTWOOD
        register(context, NIGHTWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("nightwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("nightwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, NIGHTWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("nightwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("nightwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// NUTMEG
        register(context, NUTMEG_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("nutmeg").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("nutmeg").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, NUTMEG_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("nutmeg").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("nutmeg").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// ORANGE
        register(context, ORANGE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("orange").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("orange").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, ORANGE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("orange").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("orange").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// PEACH
        register(context, PEACH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("peach").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("peach").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PEACH_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("peach").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("peach").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// PEAR
        register(context, PEAR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pear").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pear").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PEAR_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pear").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pear").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// PECAN
        register(context, PECAN_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pecan").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pecan").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PECAN_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pecan").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pecan").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// PERSIMMON
        register(context, PERSIMMON_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("persimmon").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("persimmon").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PERSIMMON_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("persimmon").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("persimmon").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// PINK_IVORY
        register(context, PINK_IVORY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pink_ivory").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pink_ivory").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PINK_IVORY_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pink_ivory").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pink_ivory").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// PLUM
        register(context, PLUM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("plum").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("plum").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PLUM_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("plum").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("plum").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// POMEGRANATE
        register(context, POMEGRANATE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pomegranate").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pomegranate").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, POMEGRANATE_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("pomegranate").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("pomegranate").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// PURPLEHEART
        register(context, PURPLEHEART_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("purpleheart").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("purpleheart").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, PURPLEHEART_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("purpleheart").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("purpleheart").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// REDWOOD
        register(context, REDWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("redwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("redwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, REDWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("redwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("redwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// SANDALWOOD
        register(context, SANDALWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sandalwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sandalwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, SANDALWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sandalwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sandalwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// SANDBEGGAR
        register(context, SANDBEGGAR_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sandbeggar").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sandbeggar").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, SANDBEGGAR_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("sandbeggar").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("sandbeggar").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// TIGERWOOD
        register(context, TIGERWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("tigerwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("tigerwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, TIGERWOOD_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("tigerwood").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("tigerwood").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

// YEW
        register(context, YEW_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("yew").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("yew").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        register(context, YEW_RARE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBLocks.LOGS.get("yew").get()),
                new StraightTrunkPlacer(10, 2, 3),
                BlockStateProvider.simple(ModBLocks.LEAVES.get("yew").get().defaultBlockState()
                        .setValue(LeavesBlock.PERSISTENT, true)),
                new SpruceFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), ConstantInt.of(3)),
                new TwoLayersFeatureSize(1, 0, 1)).build());

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

        register(context, OPIUM_POPPY_WILD_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,  // tries
                        6,   // xz spread
                        2,   // y spread
                        PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBLocks.OPIUM_POPPY_WILD.get())
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
