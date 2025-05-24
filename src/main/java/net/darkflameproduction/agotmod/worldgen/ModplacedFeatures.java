package net.darkflameproduction.agotmod.worldgen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.worldgen.ore.ModOrePlacement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
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
    public static final ResourceKey<PlacedFeature> IRON_ORE_PLACED_KEY = registerKey("iron_ore_placed");
    public static final ResourceKey<PlacedFeature> COAL_ORE_PLACED_KEY = registerKey("coal_ore_placed");
    public static final ResourceKey<PlacedFeature> COPPER_ORE_PLACED_KEY = registerKey("copper_ore_placed");
    public static final ResourceKey<PlacedFeature> GOLD_ORE_PLACED_KEY = registerKey("gold_ore_placed");
    public static final ResourceKey<PlacedFeature> LAPIS_ORE_PLACED_KEY = registerKey("lapis_ore_placed");
    public static final ResourceKey<PlacedFeature> EMERALD_ORE_PLACED_KEY = registerKey("emerald_ore_placed");
    public static final ResourceKey<PlacedFeature> DIAMONDS_ORE_PLACED_KEY = registerKey("diamonds_ore_placed");
    public static final ResourceKey<PlacedFeature> BLOODSTONE_ORE_PLACED_KEY = registerKey("bloodstone_ore_placed");
    public static final ResourceKey<PlacedFeature> CHALCEDONY_ORE_PLACED_KEY = registerKey("chalcedony_ore_placed");
    public static final ResourceKey<PlacedFeature> AMBER_ORE_PLACED_KEY = registerKey("amber_ore_placed");
    public static final ResourceKey<PlacedFeature> AMETHYST_ORE_PLACED_KEY = registerKey("amethyst_ore_placed");
    public static final ResourceKey<PlacedFeature> CARNELIAN_ORE_PLACED_KEY = registerKey("carnelian_ore_placed");
    public static final ResourceKey<PlacedFeature> GARNET_ORE_PLACED_KEY = registerKey("garnet_ore_placed");
    public static final ResourceKey<PlacedFeature> JADE_ORE_PLACED_KEY = registerKey("jade_ore_placed");
    public static final ResourceKey<PlacedFeature> JASPER_ORE_PLACED_KEY = registerKey("jasper_ore_placed");
    public static final ResourceKey<PlacedFeature> MALACHITE_ORE_PLACED_KEY = registerKey("malachite_ore_placed");
    public static final ResourceKey<PlacedFeature> RUBY_ORE_PLACED_KEY = registerKey("ruby_ore_placed");
    public static final ResourceKey<PlacedFeature> ONYX_ORE_PLACED_KEY = registerKey("onyx_ore_placed");
    public static final ResourceKey<PlacedFeature> OPAL_ORE_PLACED_KEY = registerKey("opal_ore_placed");
    public static final ResourceKey<PlacedFeature> SAPPHIRE_ORE_PLACED_KEY = registerKey("sapphire_ore_placed");
    public static final ResourceKey<PlacedFeature> MOONSTONE_ORE_PLACED_KEY = registerKey("moonstone_ore_placed");
    public static final ResourceKey<PlacedFeature> TIGERS_EYE_ORE_PLACED_KEY = registerKey("tigers_eye_ore_placed");
    public static final ResourceKey<PlacedFeature> TOPAZ_ORE_PLACED_KEY = registerKey("topaz_ore_placed");
    public static final ResourceKey<PlacedFeature> TOURMALINE_ORE_PLACED_KEY = registerKey("tourmaline_ore_placed");
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
    public static final ResourceKey<PlacedFeature> BLACK_CHERRY_KEY = registerKey("black_cherry");
    public static final ResourceKey<PlacedFeature> BLACK_OLIVE_KEY = registerKey("black_olive");
    public static final ResourceKey<PlacedFeature> CRABAPPLE_KEY = registerKey("crabapple");
    public static final ResourceKey<PlacedFeature> OLIVE_KEY = registerKey("olive");
    public static final ResourceKey<PlacedFeature> WHITE_CHERRY_KEY = registerKey("white_cherry");
    public static final ResourceKey<PlacedFeature> RED_CHERRY_KEY = registerKey("red_cherry");
    public static final ResourceKey<PlacedFeature> FIR_KEY = registerKey("fir");
    public static final ResourceKey<PlacedFeature> WILLOW_KEY = registerKey("willow");
    public static final ResourceKey<PlacedFeature> WORMTREE_KEY = registerKey("wormtree");
    public static final ResourceKey<PlacedFeature> ALMOND_KEY = registerKey("almond");
    public static final ResourceKey<PlacedFeature> APPLE_KEY = registerKey("apple");
    public static final ResourceKey<PlacedFeature> APRICOT_KEY = registerKey("apricot");
    public static final ResourceKey<PlacedFeature> BAOBAB_KEY = registerKey("baobab");
    public static final ResourceKey<PlacedFeature> BLACK_COTTONWOOD_KEY = registerKey("black_cottonwood");
    public static final ResourceKey<PlacedFeature> BLACKTHORN_KEY = registerKey("blackthorn");
    public static final ResourceKey<PlacedFeature> BLOOD_ORANGE_KEY = registerKey("blood_orange");
    public static final ResourceKey<PlacedFeature> BLOODWOOD_KEY = registerKey("bloodwood");
    public static final ResourceKey<PlacedFeature> BLUE_MAHOE_KEY = registerKey("blue_mahoe");
    public static final ResourceKey<PlacedFeature> COTTONWOOD_KEY = registerKey("cottonwood");
    public static final ResourceKey<PlacedFeature> DATEPALM_KEY = registerKey("datepalm");
    public static final ResourceKey<PlacedFeature> EBONY_KEY = registerKey("ebony");
    public static final ResourceKey<PlacedFeature> FIG_KEY = registerKey("fig");
    public static final ResourceKey<PlacedFeature> FIREPLUM_KEY = registerKey("fireplum");
    public static final ResourceKey<PlacedFeature> GOLDENHEART_KEY = registerKey("goldenheart");
    public static final ResourceKey<PlacedFeature> LEMON_KEY = registerKey("lemon");
    public static final ResourceKey<PlacedFeature> LIME_KEY = registerKey("lime");
    public static final ResourceKey<PlacedFeature> LINDEN_KEY = registerKey("linden");
    public static final ResourceKey<PlacedFeature> MAHOGANY_KEY = registerKey("mahogany");
    public static final ResourceKey<PlacedFeature> MAPLE_KEY = registerKey("maple");
    public static final ResourceKey<PlacedFeature> MYRRH_KEY = registerKey("myrrh");
    public static final ResourceKey<PlacedFeature> NIGHTWOOD_KEY = registerKey("nightwood");
    public static final ResourceKey<PlacedFeature> NUTMEG_KEY = registerKey("nutmeg");
    public static final ResourceKey<PlacedFeature> ORANGE_KEY = registerKey("orange");
    public static final ResourceKey<PlacedFeature> PEACH_KEY = registerKey("peach");
    public static final ResourceKey<PlacedFeature> PEAR_KEY = registerKey("pear");
    public static final ResourceKey<PlacedFeature> PECAN_KEY = registerKey("pecan");
    public static final ResourceKey<PlacedFeature> PERSIMMON_KEY = registerKey("persimmon");
    public static final ResourceKey<PlacedFeature> PINK_IVORY_KEY = registerKey("pink_ivory");
    public static final ResourceKey<PlacedFeature> PLUM_KEY = registerKey("plum");
    public static final ResourceKey<PlacedFeature> POMEGRANATE_KEY = registerKey("pomegranate");
    public static final ResourceKey<PlacedFeature> PURPLEHEART_KEY = registerKey("purpleheart");
    public static final ResourceKey<PlacedFeature> REDWOOD_KEY = registerKey("redwood");
    public static final ResourceKey<PlacedFeature> SANDALWOOD_KEY = registerKey("sandalwood");
    public static final ResourceKey<PlacedFeature> SANDBEGGAR_KEY = registerKey("sandbeggar");
    public static final ResourceKey<PlacedFeature> TIGERWOOD_KEY = registerKey("tigerwood");
    public static final ResourceKey<PlacedFeature> YEW_KEY = registerKey("yew");
    public static final ResourceKey<PlacedFeature> WEIRWOOD_RARE_KEY = registerKey("weirwood_rare");
    public static final ResourceKey<PlacedFeature> OAK2_RARE_KEY = registerKey("oak_rare");
    public static final ResourceKey<PlacedFeature> SYCAMORE_KEY = registerKey("sycamore");
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
    public static final ResourceKey<PlacedFeature> BLACK_CHERRY_RARE_KEY = registerKey("black_cherry_rare");
    public static final ResourceKey<PlacedFeature> BLACK_OLIVE_RARE_KEY = registerKey("black_olive_rare");
    public static final ResourceKey<PlacedFeature> CRABAPPLE_RARE_KEY = registerKey("crabapple_rare");
    public static final ResourceKey<PlacedFeature> OLIVE_RARE_KEY = registerKey("olive_rare");
    public static final ResourceKey<PlacedFeature> WHITE_CHERRY_RARE_KEY = registerKey("white_cherry_rare");
    public static final ResourceKey<PlacedFeature> RED_CHERRY_RARE_KEY = registerKey("red_cherry_rare");
    public static final ResourceKey<PlacedFeature> FIR_RARE_KEY = registerKey("fir_rare");
    public static final ResourceKey<PlacedFeature> WILLOW_RARE_KEY = registerKey("willow_rare");
    public static final ResourceKey<PlacedFeature> WORMTREE_RARE_KEY = registerKey("wormtree_rare");
    public static final ResourceKey<PlacedFeature> ALMOND_RARE_KEY = registerKey("almond_rare");
    public static final ResourceKey<PlacedFeature> APPLE_RARE_KEY = registerKey("apple_rare");
    public static final ResourceKey<PlacedFeature> APRICOT_RARE_KEY = registerKey("apricot_rare");
    public static final ResourceKey<PlacedFeature> BAOBAB_RARE_KEY = registerKey("baobab_rare");
    public static final ResourceKey<PlacedFeature> BLACK_COTTONWOOD_RARE_KEY = registerKey("black_cottonwood_rare");
    public static final ResourceKey<PlacedFeature> BLACKTHORN_RARE_KEY = registerKey("blackthorn_rare");
    public static final ResourceKey<PlacedFeature> BLOOD_ORANGE_RARE_KEY = registerKey("blood_orange_rare");
    public static final ResourceKey<PlacedFeature> BLOODWOOD_RARE_KEY = registerKey("bloodwood_rare");
    public static final ResourceKey<PlacedFeature> BLUE_MAHOE_RARE_KEY = registerKey("blue_mahoe_rare");
    public static final ResourceKey<PlacedFeature> COTTONWOOD_RARE_KEY = registerKey("cottonwood_rare");
    public static final ResourceKey<PlacedFeature> DATEPALM_RARE_KEY = registerKey("datepalm_rare");
    public static final ResourceKey<PlacedFeature> EBONY_RARE_KEY = registerKey("ebony_rare");
    public static final ResourceKey<PlacedFeature> FIG_RARE_KEY = registerKey("fig_rare");
    public static final ResourceKey<PlacedFeature> FIREPLUM_RARE_KEY = registerKey("fireplum_rare");
    public static final ResourceKey<PlacedFeature> GOLDENHEART_RARE_KEY = registerKey("goldenheart_rare");
    public static final ResourceKey<PlacedFeature> LEMON_RARE_KEY = registerKey("lemon_rare");
    public static final ResourceKey<PlacedFeature> LIME_RARE_KEY = registerKey("lime_rare");
    public static final ResourceKey<PlacedFeature> LINDEN_RARE_KEY = registerKey("linden_rare");
    public static final ResourceKey<PlacedFeature> MAHOGANY_RARE_KEY = registerKey("mahogany_rare");
    public static final ResourceKey<PlacedFeature> MAPLE_RARE_KEY = registerKey("maple_rare");
    public static final ResourceKey<PlacedFeature> MYRRH_RARE_KEY = registerKey("myrrh_rare");
    public static final ResourceKey<PlacedFeature> NIGHTWOOD_RARE_KEY = registerKey("nightwood_rare");
    public static final ResourceKey<PlacedFeature> NUTMEG_RARE_KEY = registerKey("nutmeg_rare");
    public static final ResourceKey<PlacedFeature> ORANGE_RARE_KEY = registerKey("orange_rare");
    public static final ResourceKey<PlacedFeature> PEACH_RARE_KEY = registerKey("peach_rare");
    public static final ResourceKey<PlacedFeature> PEAR_RARE_KEY = registerKey("pear_rare");
    public static final ResourceKey<PlacedFeature> PECAN_RARE_KEY = registerKey("pecan_rare");
    public static final ResourceKey<PlacedFeature> PERSIMMON_RARE_KEY = registerKey("persimmon_rare");
    public static final ResourceKey<PlacedFeature> PINK_IVORY_RARE_KEY = registerKey("pink_ivory_rare");
    public static final ResourceKey<PlacedFeature> PLUM_RARE_KEY = registerKey("plum_rare");
    public static final ResourceKey<PlacedFeature> POMEGRANATE_RARE_KEY = registerKey("pomegranate_rare");
    public static final ResourceKey<PlacedFeature> PURPLEHEART_RARE_KEY = registerKey("purpleheart_rare");
    public static final ResourceKey<PlacedFeature> REDWOOD_RARE_KEY = registerKey("redwood_rare");
    public static final ResourceKey<PlacedFeature> SANDALWOOD_RARE_KEY = registerKey("sandalwood_rare");
    public static final ResourceKey<PlacedFeature> SANDBEGGAR_RARE_KEY = registerKey("sandbeggar_rare");
    public static final ResourceKey<PlacedFeature> TIGERWOOD_RARE_KEY = registerKey("tigerwood_rare");
    public static final ResourceKey<PlacedFeature> YEW_RARE_KEY = registerKey("yew_rare");
    public static final ResourceKey<PlacedFeature> THORN_BUSH_KEY = registerKey("thorn_bush");
    public static final ResourceKey<PlacedFeature> THISTLE_KEY = registerKey("thistle");
    public static final ResourceKey<PlacedFeature> TANSY_KEY = registerKey("tansy");
    public static final ResourceKey<PlacedFeature> SPICEFLOWER_KEY = registerKey("spiceflower");
    public static final ResourceKey<PlacedFeature> SEDGE_KEY = registerKey("sedge");
    public static final ResourceKey<PlacedFeature> SAFFRON_CROCUS_KEY = registerKey("saffron_crocus");
    public static final ResourceKey<PlacedFeature> POISON_KISSES_KEY = registerKey("poison_kisses");
    public static final ResourceKey<PlacedFeature> PENNYROYAL_KEY = registerKey("pennyroyal");
    public static final ResourceKey<PlacedFeature> OPIUM_POPPY_WILD_KEY = registerKey("opium_poppy_wild");
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
    public static final ResourceKey<PlacedFeature> GRASS_BLOCK_PATCH_PLACED_KEY = registerKey("grass_block_patch");
    public static final ResourceKey<PlacedFeature> CLAY_PATCH_PLACED_KEY = registerKey("clay_patch");
    public static final ResourceKey<PlacedFeature> SEAGRASS_KEY = registerKey("seagrass");
    public static final ResourceKey<PlacedFeature> KELP_KEY = registerKey("kelp");
    public static final ResourceKey<PlacedFeature> QUAGMIRE_PATCH_PLACED_KEY = registerKey("quagmire");
    public static final ResourceKey<PlacedFeature> FERN_KEY = registerKey("fern");
    public static final ResourceKey<PlacedFeature> LARGE_FERN_KEY = registerKey("large_fern");
    public static final ResourceKey<PlacedFeature> GRASS_KEY = registerKey("grass");
    public static final ResourceKey<PlacedFeature> TALL_GRASS_KEY = registerKey("tall_grass");








    // Bootstrap method for initializing placed features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        // Get a holder for configured features from the context
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Register the placed feature for tin ore with specified configuration and modifiers
        register(context, TIN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TIN_ORE),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, IRON_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_IRON_ORE),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, COAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_COAL_ORE),
                ModOrePlacement.commonOrePlacement(17,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, COPPER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_COPPER_ORE),
                ModOrePlacement.commonOrePlacement(10,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(112))));

        register(context, GOLD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_GOLD_ORE),
                ModOrePlacement.commonOrePlacement(2,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));

        register(context, LAPIS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_LAPIS_ORE),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64))));

        register(context, EMERALD_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_EMERALD_ORE),
                ModOrePlacement.commonOrePlacement(1,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32))));

        register(context, DIAMONDS_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_DIAMONDS_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, BLOODSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_BLOODSTONE_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, CHALCEDONY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_CHALCEDONY_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, AMBER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_AMBER_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, AMETHYST_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_AMETHYST_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, CARNELIAN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_CARNELIAN_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, GARNET_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_GARNET_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, JADE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_JADE_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, JASPER_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_JASPER_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, MALACHITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_MALACHITE_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, RUBY_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_RUBY_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, ONYX_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_ONYX_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, OPAL_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_OPAL_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, SAPPHIRE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SAPPHIRE_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, MOONSTONE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_MOONSTONE_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, TIGERS_EYE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TIGERS_EYE_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, TOPAZ_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TOPAZ_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));

        register(context, TOURMALINE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TOURMALINE_ORE),
                ModOrePlacement.commonOrePlacement(1, // 1 vein per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(150))));



        //Forest Trees

        register(context, WEIRWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WEIRWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModBLocks.WEIRWOOD_SAPLING.get()));

        register(context, OAK2_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OAK2_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        Blocks.OAK_SAPLING));
        register(context, WEIRWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WEIRWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.WEIRWOOD_SAPLING.get()));

        register(context, OAK2_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OAK2_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        Blocks.OAK_SAPLING));

        register(context, SYCAMORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SYCAMORE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("sycamore").get()));


        register(context, SENTINEL_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SENTINEL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("sentinel").get()));

        register(context, PINE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("pine").get()));

        register(context, IRONWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("ironwood").get()));

        register(context, HAWTHORN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAWTHORN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("hawthorn").get()));

        register(context, CHESTNUT_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHESTNUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("chestnut").get()));

        register(context, CEDAR_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CEDAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("cedar").get()));

        register(context, BEECH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("beech").get()));

        register(context, ASH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("ash").get()));

        register(context, BLACKBARK_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKBARK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("blackbark").get()));

        register(context, ASPEN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASPEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("aspen").get()));

        register(context, ALDER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALDER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("alder").get()));

// Rare tree placements
        register(context, SENTINEL_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SENTINEL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("sentinel").get()));

        register(context, PINE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("pine").get()));

        register(context, IRONWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("ironwood").get()));

        register(context, HAWTHORN_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAWTHORN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("hawthorn").get()));

        register(context, CHESTNUT_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHESTNUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("chestnut").get()));

        register(context, CEDAR_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CEDAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("cedar").get()));

        register(context, BEECH_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("beech").get()));

        register(context, ASH_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("ash").get()));

        register(context, BLACKBARK_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKBARK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("blackbark").get()));

        register(context, ASPEN_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASPEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("aspen").get()));

        register(context, ALDER_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALDER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("alder").get()));

// Adding the regular tree placements for the new trees
        register(context, BLACK_CHERRY_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_CHERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("black_cherry").get()));

        register(context, BLACK_OLIVE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_OLIVE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("black_olive").get()));

        register(context, CRABAPPLE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRABAPPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("crabapple").get()));

        register(context, OLIVE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLIVE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("olive").get()));

        register(context, WHITE_CHERRY_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WHITE_CHERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("white_cherry").get()));

        register(context, RED_CHERRY_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_CHERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("red_cherry").get()));

        register(context, FIR_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("fir").get()));

        register(context, WILLOW_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILLOW_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("willow").get()));

        register(context, WORMTREE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WORMTREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("wormtree").get()));

// Adding the rare tree placements for the new trees
        register(context, BLACK_CHERRY_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_CHERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("black_cherry").get()));

        register(context, BLACK_OLIVE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_OLIVE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("black_olive").get()));

        register(context, CRABAPPLE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CRABAPPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("crabapple").get()));

        register(context, OLIVE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLIVE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("olive").get()));

        register(context, WHITE_CHERRY_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WHITE_CHERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("white_cherry").get()));

        register(context, RED_CHERRY_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_CHERRY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("red_cherry").get()));

        register(context, FIR_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("fir").get()));

        register(context, WILLOW_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WILLOW_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("willow").get()));

        register(context, WORMTREE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WORMTREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("wormtree").get()));

// ALMOND
        register(context, ALMOND_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALMOND_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("almond").get()));

        register(context, ALMOND_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALMOND_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("almond").get()));

// APPLE
        register(context, APPLE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("apple").get()));

        register(context, APPLE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.APPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("apple").get()));

// APRICOT
        register(context, APRICOT_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.APRICOT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("apricot").get()));

        register(context, APRICOT_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.APRICOT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("apricot").get()));

// BAOBAB
        register(context, BAOBAB_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BAOBAB_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("baobab").get()));

        register(context, BAOBAB_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BAOBAB_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("baobab").get()));

// BLACK_COTTONWOOD
        register(context, BLACK_COTTONWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_COTTONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("black_cottonwood").get()));

        register(context, BLACK_COTTONWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACK_COTTONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("black_cottonwood").get()));

// BLACKTHORN
        register(context, BLACKTHORN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKTHORN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("blackthorn").get()));

        register(context, BLACKTHORN_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKTHORN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("blackthorn").get()));

// BLOOD_ORANGE
        register(context, BLOOD_ORANGE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_ORANGE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("blood_orange").get()));

        register(context, BLOOD_ORANGE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOOD_ORANGE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("blood_orange").get()));

// BLOODWOOD
        register(context, BLOODWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("bloodwood").get()));

        register(context, BLOODWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("bloodwood").get()));

// BLUE_MAHOE
        register(context, BLUE_MAHOE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_MAHOE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("blue_mahoe").get()));

        register(context, BLUE_MAHOE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLUE_MAHOE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("blue_mahoe").get()));

// COTTONWOOD
        register(context, COTTONWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COTTONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("cottonwood").get()));

        register(context, COTTONWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.COTTONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("cottonwood").get()));

// DATEPALM
        register(context, DATEPALM_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DATEPALM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("datepalm").get()));

        register(context, DATEPALM_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.DATEPALM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("datepalm").get()));

// EBONY
        register(context, EBONY_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EBONY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("ebony").get()));

        register(context, EBONY_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.EBONY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("ebony").get()));

// FIG
        register(context, FIG_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIG_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("fig").get()));

        register(context, FIG_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIG_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("fig").get()));

// FIREPLUM
        register(context, FIREPLUM_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIREPLUM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("fireplum").get()));

        register(context, FIREPLUM_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIREPLUM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("fireplum").get()));

// GOLDENHEART
        register(context, GOLDENHEART_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENHEART_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("goldenheart").get()));

        register(context, GOLDENHEART_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENHEART_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("goldenheart").get()));

// LEMON
        register(context, LEMON_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEMON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("lemon").get()));

        register(context, LEMON_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEMON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("lemon").get()));

// LIME
        register(context, LIME_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIME_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("lime").get()));

        register(context, LIME_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LIME_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("lime").get()));

// LINDEN
        register(context, LINDEN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LINDEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("linden").get()));

        register(context, LINDEN_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LINDEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("linden").get()));

// MAHOGANY
        register(context, MAHOGANY_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAHOGANY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("mahogany").get()));

        register(context, MAHOGANY_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAHOGANY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("mahogany").get()));

// MAPLE
        register(context, MAPLE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("maple").get()));

        register(context, MAPLE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAPLE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("maple").get()));

// MYRRH
        register(context, MYRRH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MYRRH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("myrrh").get()));

        register(context, MYRRH_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MYRRH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("myrrh").get()));

// NIGHTWOOD
        register(context, NIGHTWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NIGHTWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("nightwood").get()));

        register(context, NIGHTWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NIGHTWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("nightwood").get()));

// NUTMEG
        register(context, NUTMEG_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NUTMEG_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("nutmeg").get()));

        register(context, NUTMEG_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NUTMEG_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("nutmeg").get()));

// ORANGE
        register(context, ORANGE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORANGE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("orange").get()));

        register(context, ORANGE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORANGE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("orange").get()));

// PEACH
        register(context, PEACH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PEACH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("peach").get()));

        register(context, PEACH_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PEACH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("peach").get()));

// PEAR
        register(context, PEAR_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PEAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("pear").get()));

        register(context, PEAR_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PEAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("pear").get()));

// PECAN
        register(context, PECAN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PECAN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("pecan").get()));

        register(context, PECAN_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PECAN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("pecan").get()));

// PERSIMMON
        register(context, PERSIMMON_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PERSIMMON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("persimmon").get()));

        register(context, PERSIMMON_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PERSIMMON_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("persimmon").get()));

// PINK_IVORY
        register(context, PINK_IVORY_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINK_IVORY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("pink_ivory").get()));

        register(context, PINK_IVORY_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINK_IVORY_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("pink_ivory").get()));

// PLUM
        register(context, PLUM_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PLUM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("plum").get()));

        register(context, PLUM_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PLUM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("plum").get()));

// POMEGRANATE
        register(context, POMEGRANATE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.POMEGRANATE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("pomegranate").get()));

        register(context, POMEGRANATE_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.POMEGRANATE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("pomegranate").get()));

// PURPLEHEART
        register(context, PURPLEHEART_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PURPLEHEART_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("purpleheart").get()));

        register(context, PURPLEHEART_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PURPLEHEART_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("purpleheart").get()));

// REDWOOD
        register(context, REDWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.REDWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("redwood").get()));

        register(context, REDWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.REDWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("redwood").get()));

// SANDALWOOD
        register(context, SANDALWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SANDALWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("sandalwood").get()));

        register(context, SANDALWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SANDALWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("sandalwood").get()));

// SANDBEGGAR
        register(context, SANDBEGGAR_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SANDBEGGAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("sandbeggar").get()));

        register(context, SANDBEGGAR_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SANDBEGGAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("sandbeggar").get()));

// TIGERWOOD
        register(context, TIGERWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIGERWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("tigerwood").get()));

        register(context, TIGERWOOD_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIGERWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("tigerwood").get()));

// YEW
        register(context, YEW_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.YEW_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SAPLINGS.get("yew").get()));

        register(context, YEW_RARE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.YEW_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        ModBLocks.SAPLINGS.get("yew").get()));



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

        register(context, OPIUM_POPPY_WILD_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.OPIUM_POPPY_WILD_KEY),
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

        register(context, FERN_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.FERN_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(8),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, LARGE_FERN_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LARGE_FERN_KEY),
                List.of(CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(8),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(context, GRASS_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GRASS_KEY),
                List.of(
                        // Reduce base count by 50%
                        CountPlacement.of(10),
                        // Keep noise parameters similar but slightly reduced
                        NoiseBasedCountPlacement.of(4, 0.5, 0.0),
                        InSquarePlacement.spread(),
                        // Keep height range the same
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(65), VerticalAnchor.top()),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                ));

        register(context, TALL_GRASS_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.TALL_GRASS_KEY),
                List.of(
                        // Reduce base count by 50%
                        CountPlacement.of(2),
                        // Reduce noise count by 50%
                        NoiseBasedCountPlacement.of(3, 0.3, 0.0),
                        InSquarePlacement.spread(),
                        // Keep height range the same
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(65), VerticalAnchor.top()),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                ));


        register(context, GRASS_BLOCK_PATCH_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GRASS_BLOCK_PATCH_KEY),
                List.of(
                        CountPlacement.of(4),
                        NoiseBasedCountPlacement.of(5, 10.0, 0.0),
                        InSquarePlacement.spread(),
                        // Ensures placement only at or above Y=64
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(65), VerticalAnchor.top()),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()
                ));

        register(context, CLAY_PATCH_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.CLAY_PATCH_KEY),
                List.of(
                        // Try placing clay 10 times per chunk — tweak this number as needed
                        CountPlacement.of(10),

                        // Spread attempts randomly in the chunk
                        InSquarePlacement.spread(),

                        // Allow full vertical generation, including underwater
                        PlacementUtils.FULL_RANGE,

                        // Only place in valid biomes (make sure rivers are included in biome modifiers)
                        BiomeFilter.biome()
                ));

        register(context, QUAGMIRE_PATCH_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.QUAGMIRE_PATCH_KEY),
                List.of(
                        // Try placing clay 10 times per chunk — tweak this number as needed
                        CountPlacement.of(10),

                        // Spread attempts randomly in the chunk
                        InSquarePlacement.spread(),

                        // Allow full vertical generation, including underwater
                        PlacementUtils.FULL_RANGE,

                        // Only place in valid biomes (make sure rivers are included in biome modifiers)
                        BiomeFilter.biome()
                ));

        register(context, SEAGRASS_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.SEAGRASS_KEY),
                List.of(
                        CountPlacement.of(120),  // Increased from 20 to 120
                        RarityFilter.onAverageOnceEvery(1),  // Added to ensure maximum frequency (every block)
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_TOP_SOLID,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.WATER),
                                        BlockPredicate.matchesBlocks(BlockPos.ZERO.below(),
                                                Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.MUD)
                                )
                        )
                ));

        register(context, KELP_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.KELP_KEY),
                List.of(
                        CountPlacement.of(80),  // High count for abundance
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_TOP_SOLID,
                        BiomeFilter.biome(),
                        BlockPredicateFilter.forPredicate(
                                BlockPredicate.allOf(
                                        BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.WATER),
                                        BlockPredicate.matchesBlocks(BlockPos.ZERO.below(),
                                                Blocks.DIRT, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.MUD, Blocks.STONE)
                                )
                        )
                ));






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
