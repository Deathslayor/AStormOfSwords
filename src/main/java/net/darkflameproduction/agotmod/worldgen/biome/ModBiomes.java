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
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBiomes {

    // =========================================================================
    // RESOURCE KEYS
    // =========================================================================

    // Lands of Always Winter
    public static final ResourceKey<Biome> LANDS_OF_ALWAYS_WINTER    = key("lands_of_always_winter");

    // Beyond the Wall
    public static final ResourceKey<Biome> BEYOND_THE_WALL               = key("beyond_the_wall");
    public static final ResourceKey<Biome> BEYOND_THE_WALL_ICE_RIVER_VALLEY = key("beyond_the_wall_ice_river_valley");
    public static final ResourceKey<Biome> BEYOND_THE_WALL_FROZEN_SHORE  = key("beyond_the_wall_frozen_shore");
    public static final ResourceKey<Biome> BEYOND_THE_WALL_RIVER_VALLEY  = key("beyond_the_wall_river_valley");

    // Giant's Stair
    public static final ResourceKey<Biome> GIANTS_STAIR              = key("giants_stair");
    public static final ResourceKey<Biome> GIANTS_STAIR_RIVER_VALLEY = key("giants_stair_river_valley");

    // Frostfangs
    public static final ResourceKey<Biome> FROSTFANG_FOOTHILLS  = key("frostfang_foothills");
    public static final ResourceKey<Biome> FROSTFANGS           = key("frostfangs");
    public static final ResourceKey<Biome> FROSTFANG_HILLS      = key("frostfang_hills");
    public static final ResourceKey<Biome> FROSTFANG_MOUNTAINS  = key("frostfang_mountains");

    // Valley of Thenn
    public static final ResourceKey<Biome> VALLEY_OF_THENN           = key("valley_of_thenn");
    public static final ResourceKey<Biome> THENN_VALLEY_PLAINS        = key("thenn_valley_plains");
    public static final ResourceKey<Biome> THENN_VALLEY_TUNDRA        = key("thenn_valley_tundra");
    public static final ResourceKey<Biome> THENN_VALLEY_WOODS         = key("thenn_valley_woods");
    public static final ResourceKey<Biome> THENN_UPPER_VALLEY_PLAINS  = key("thenn_upper_valley_plains");
    public static final ResourceKey<Biome> THENN_UPPER_VALLEY_TUNDRA  = key("thenn_upper_valley_tundra");
    public static final ResourceKey<Biome> THENN_UPPER_VALLEY_WOODS   = key("thenn_upper_valley_woods");
    public static final ResourceKey<Biome> THENN_HILLS                = key("thenn_hills");
    public static final ResourceKey<Biome> THENN_MOUNTAINS            = key("thenn_mountains");

    // Haunted Forest
    public static final ResourceKey<Biome> HAUNTED_FOREST_CLEARING  = key("haunted_forest_clearing");
    public static final ResourceKey<Biome> HAUNTED_OPEN_WOODLAND    = key("haunted_open_woodland");
    public static final ResourceKey<Biome> HAUNTED_FOREST_EDGE      = key("haunted_forest_edge");
    public static final ResourceKey<Biome> HAUNTED_FOREST_MAIN      = key("haunted_forest_main");
    public static final ResourceKey<Biome> HAUNTED_BROADLEAF_FOREST = key("haunted_broadleaf_forest");
    public static final ResourceKey<Biome> HAUNTED_DEEP_FOREST      = key("haunted_deep_forest");
    public static final ResourceKey<Biome> HAUNTED_PINE_FOREST      = key("haunted_pine_forest");
    public static final ResourceKey<Biome> HAUNTED_IRONWOOD_FOREST  = key("haunted_ironwood_forest");
    public static final ResourceKey<Biome> HAUNTED_ANCIENT_FOREST   = key("haunted_ancient_forest");
    public static final ResourceKey<Biome> HAUNTED_DENSE_IRONWOOD   = key("haunted_dense_ironwood");

    // Northern Forest
    public static final ResourceKey<Biome> NORTHERN_FOREST          = key("northern_forest");
    public static final ResourceKey<Biome> NORTHERN_DEEP_FOREST     = key("northern_deep_forest");
    public static final ResourceKey<Biome> NORTHERN_IRONWOOD_FOREST = key("northern_ironwood_forest");
    public static final ResourceKey<Biome> NORTHERN_DENSE_IRONWOOD  = key("northern_dense_ironwood");
    public static final ResourceKey<Biome> NORTHERN_BROADLEAF_FOREST= key("northern_broadleaf_forest");
    public static final ResourceKey<Biome> NORTHERN_BEECH_FOREST    = key("northern_beech_forest");
    public static final ResourceKey<Biome> NORTHERN_PINE_FOREST     = key("northern_pine_forest");
    public static final ResourceKey<Biome> NORTHERN_ANCIENT_FOREST  = key("northern_ancient_forest");
    public static final ResourceKey<Biome> NORTHERN_FOREST_EDGE     = key("northern_forest_edge");
    public static final ResourceKey<Biome> NORTHERN_FOREST_CLEARING = key("northern_forest_clearing");
    public static final ResourceKey<Biome> NORTHERN_OPEN_WOODLAND   = key("northern_open_woodland");
    public static final ResourceKey<Biome> NORTHERN_CONIFER_SCRUB   = key("northern_conifer_scrub");

    // Wolfswood
    public static final ResourceKey<Biome> WOLFSWOOD_FOREST          = key("wolfswood_forest");
    public static final ResourceKey<Biome> WOLFSWOOD_DEEP_FOREST     = key("wolfswood_deep_forest");
    public static final ResourceKey<Biome> WOLFSWOOD_IRONWOOD_FOREST = key("wolfswood_ironwood_forest");
    public static final ResourceKey<Biome> WOLFSWOOD_DENSE_IRONWOOD  = key("wolfswood_dense_ironwood");
    public static final ResourceKey<Biome> WOLFSWOOD_BROADLEAF_FOREST= key("wolfswood_broadleaf_forest");
    public static final ResourceKey<Biome> WOLFSWOOD_BEECH_FOREST    = key("wolfswood_beech_forest");
    public static final ResourceKey<Biome> WOLFSWOOD_PINE_FOREST     = key("wolfswood_pine_forest");
    public static final ResourceKey<Biome> WOLFSWOOD_ANCIENT_FOREST  = key("wolfswood_ancient_forest");
    public static final ResourceKey<Biome> WOLFSWOOD_FOREST_EDGE     = key("wolfswood_forest_edge");
    public static final ResourceKey<Biome> WOLFSWOOD_CLEARING        = key("wolfswood_clearing");
    public static final ResourceKey<Biome> WOLFSWOOD_OPEN_WOODLAND   = key("wolfswood_open_woodland");
    public static final ResourceKey<Biome> WOLFSWOOD_CONIFER_SCRUB   = key("wolfswood_conifer_scrub");

    // Bear Island
    public static final ResourceKey<Biome> BEAR_ISLAND               = key("bear_island");
    public static final ResourceKey<Biome> BEAR_ISLAND_FOREST        = key("bear_island_forest");
    public static final ResourceKey<Biome> BEAR_ISLAND_DEEP_FOREST   = key("bear_island_deep_forest");
    public static final ResourceKey<Biome> BEAR_ISLAND_IRONWOOD_FOREST=key("bear_island_ironwood_forest");
    public static final ResourceKey<Biome> BEAR_ISLAND_DENSE_IRONWOOD = key("bear_island_dense_ironwood");
    public static final ResourceKey<Biome> BEAR_ISLAND_BROADLEAF_FOREST=key("bear_island_broadleaf_forest");
    public static final ResourceKey<Biome> BEAR_ISLAND_BEECH_FOREST  = key("bear_island_beech_forest");
    public static final ResourceKey<Biome> BEAR_ISLAND_PINE_FOREST   = key("bear_island_pine_forest");
    public static final ResourceKey<Biome> BEAR_ISLAND_ANCIENT_FOREST = key("bear_island_ancient_forest");
    public static final ResourceKey<Biome> BEAR_ISLAND_FOREST_EDGE   = key("bear_island_forest_edge");
    public static final ResourceKey<Biome> BEAR_ISLAND_CLEARING      = key("bear_island_clearing");
    public static final ResourceKey<Biome> BEAR_ISLAND_OPEN_WOODLAND = key("bear_island_open_woodland");
    public static final ResourceKey<Biome> BEAR_ISLAND_CONIFER_SCRUB = key("bear_island_conifer_scrub");

    // Hornwood
    public static final ResourceKey<Biome> HORNWOOD_FOREST          = key("hornwood_forest");
    public static final ResourceKey<Biome> HORNWOOD_DEEP_FOREST     = key("hornwood_deep_forest");
    public static final ResourceKey<Biome> HORNWOOD_PINE_FOREST     = key("hornwood_pine_forest");
    public static final ResourceKey<Biome> HORNWOOD_IRONWOOD_FOREST = key("hornwood_ironwood_forest");
    public static final ResourceKey<Biome> HORNWOOD_MOUNTAINS       = key("hornwood_mountains");

    // Bolton Domain
    public static final ResourceKey<Biome> BOLTON_DOMAIN_PLAINS      = key("bolton_domain_plains");
    public static final ResourceKey<Biome> BOLTON_DOMAIN_TUNDRA      = key("bolton_domain_tundra");
    public static final ResourceKey<Biome> BOLTON_DOMAIN_WOODS       = key("bolton_domain_woods");
    public static final ResourceKey<Biome> BOLTON_DOMAIN_HILLS_PLAINS = key("bolton_domain_hills_plains");
    public static final ResourceKey<Biome> BOLTON_DOMAIN_HILLS_TUNDRA = key("bolton_domain_hills_tundra");
    public static final ResourceKey<Biome> BOLTON_DOMAIN_HILLS_WOODS  = key("bolton_domain_hills_woods");
    public static final ResourceKey<Biome> BOLTON_DOMAIN_RIVER_VALLEY = key("bolton_domain_river_valley");

    // Manderly Domain
    public static final ResourceKey<Biome> MANDERLY_DOMAIN_PLAINS     = key("manderly_domain_plains");
    public static final ResourceKey<Biome> MANDERLY_DOMAIN_TUNDRA     = key("manderly_domain_tundra");
    public static final ResourceKey<Biome> MANDERLY_DOMAIN_WOODS      = key("manderly_domain_woods");
    public static final ResourceKey<Biome> MANDERLY_DOMAIN_DEEP_PLAINS = key("manderly_domain_deep_plains");
    public static final ResourceKey<Biome> MANDERLY_DOMAIN_DEEP_TUNDRA = key("manderly_domain_deep_tundra");
    public static final ResourceKey<Biome> MANDERLY_DOMAIN_DEEP_WOODS  = key("manderly_domain_deep_woods");
    public static final ResourceKey<Biome> MANDERLY_DOMAIN_COAST       = key("manderly_domain_coast");

    // Flint Domain
    public static final ResourceKey<Biome> FLINT_DOMAIN_PLAINS   = key("flint_domain_plains");
    public static final ResourceKey<Biome> FLINT_DOMAIN_TUNDRA   = key("flint_domain_tundra");
    public static final ResourceKey<Biome> FLINT_DOMAIN_WOODS    = key("flint_domain_woods");
    public static final ResourceKey<Biome> FLINT_DOMAIN_DEEP_PLAINS = key("flint_domain_deep_plains");
    public static final ResourceKey<Biome> FLINT_DOMAIN_DEEP_TUNDRA = key("flint_domain_deep_tundra");
    public static final ResourceKey<Biome> FLINT_DOMAIN_DEEP_WOODS  = key("flint_domain_deep_woods");
    public static final ResourceKey<Biome> FLINT_DOMAIN_COAST    = key("flint_domain_coast");

    // Karstark Domain
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_PLAINS  = key("karstark_domain_plains");
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_TUNDRA  = key("karstark_domain_tundra");
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_WOODS   = key("karstark_domain_woods");
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_DEEP_PLAINS = key("karstark_domain_deep_plains");
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_DEEP_TUNDRA = key("karstark_domain_deep_tundra");
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_DEEP_WOODS  = key("karstark_domain_deep_woods");
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_INLAND   = key("karstark_domain_inland");
    public static final ResourceKey<Biome> KARSTARK_DOMAIN_COAST    = key("karstark_domain_coast");

    // Other Northern
    public static final ResourceKey<Biome> NORTHERN_HILLS    = key("northern_hills");
    public static final ResourceKey<Biome> NORTHERN_MOUNTAINS= key("northern_mountains");
    public static final ResourceKey<Biome> BARROWLANDS       = key("barrowlands");
    public static final ResourceKey<Biome> NORTHERN_PLAINS   = key("northern_plains");
    public static final ResourceKey<Biome> RILLS             = key("rills");
    public static final ResourceKey<Biome> NORTHERN_WATERS   = key("northern_waters");
    public static final ResourceKey<Biome> STONY_SHORES      = key("stony_shores");
    public static final ResourceKey<Biome> THE_NECK          = key("the_neck");

    // Umber Domain
    public static final ResourceKey<Biome> UMBER_DOMAIN_TUNDRA       = key("umber_domain_tundra");
    public static final ResourceKey<Biome> UMBER_DOMAIN_BARRENS      = key("umber_domain_barrens");
    public static final ResourceKey<Biome> UMBER_DOMAIN_WOODS        = key("umber_domain_woods");
    public static final ResourceKey<Biome> UMBER_DOMAIN_DEEP_TUNDRA  = key("umber_domain_deep_tundra");
    public static final ResourceKey<Biome> UMBER_DOMAIN_DEEP_BARRENS = key("umber_domain_deep_barrens");
    public static final ResourceKey<Biome> UMBER_DOMAIN_DEEP_WOODS   = key("umber_domain_deep_woods");

    // Skagos
    public static final ResourceKey<Biome> SKAGOS_TUNDRA       = key("skagos_tundra");
    public static final ResourceKey<Biome> SKAGOS_WOODS        = key("skagos_woods");
    public static final ResourceKey<Biome> SKAGOS_BARRENS      = key("skagos_barrens");
    public static final ResourceKey<Biome> SKAGOS_HILLS        = key("skagos_hills");
    public static final ResourceKey<Biome> SKAGOS_VALLEY       = key("skagos_valley");
    public static final ResourceKey<Biome> SKAGOS_MOUNTAINS    = key("skagos_mountains");
    public static final ResourceKey<Biome> SKAGOS_FROZEN_PEAKS = key("skagos_frozen_peaks");
    public static final ResourceKey<Biome> SKAGOS_RIVER        = key("skagos_river");
    public static final ResourceKey<Biome> SKAGOS_BOG          = key("skagos_bog");

    // Night's Watch
    public static final ResourceKey<Biome> THE_WALL           = key("the_wall");
    public static final ResourceKey<Biome> THE_GIFT_TUNDRA    = key("the_gift_tundra");
    public static final ResourceKey<Biome> THE_GIFT_BARRENS   = key("the_gift_barrens");
    public static final ResourceKey<Biome> THE_GIFT_WOODS     = key("the_gift_woods");
    public static final ResourceKey<Biome> THE_NEW_GIFT_TUNDRA  = key("the_new_gift_tundra");
    public static final ResourceKey<Biome> THE_NEW_GIFT_BARRENS = key("the_new_gift_barrens");
    public static final ResourceKey<Biome> THE_NEW_GIFT_WOODS   = key("the_new_gift_woods");

    private static ResourceKey<Biome> key(String name) {
        return ResourceKey.create(Registries.BIOME, AGoTMod.id(name));
    }

    // =========================================================================
    // BOOTSTRAP
    // =========================================================================

    public static void boostrap(BootstrapContext<Biome> context) {

        // Lands of Always Winter
        reg(context, LANDS_OF_ALWAYS_WINTER, -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0xd9d9d9, 0xd9d9d9, 0x000000, ModSounds.WINTER_WIND,
                (b,ctx) -> f(b, ModplacedFeatures.WEIRWOOD_KEY));

        // Beyond the Wall
        reg(context, BEYOND_THE_WALL,                  -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xd9d9d9, 0xc8c8c8, 0xe8e8e8, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,true,false,false,false));
        reg(context, BEYOND_THE_WALL_ICE_RIVER_VALLEY, -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xd6d3bb, 0xc4c1aa, 0xe8e8e8, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,false,false,false,true));
        reg(context, BEYOND_THE_WALL_FROZEN_SHORE,     -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xd9caca, 0xc8b9b9, 0xe8e8e8, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,false,false,false,true));
        reg(context, BEYOND_THE_WALL_RIVER_VALLEY,     -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xc8d0d8, 0xc5c5c5, 0xb4b4b4, 0xe8e8e8, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,true,true,true,false));

        // Giant's Stair
        reg(context, GIANTS_STAIR,              -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xb8b9cc, 0xbfc0cd, 0xaeafbc, 0xd8d8e8, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,false,false,true));
        reg(context, GIANTS_STAIR_RIVER_VALLEY, -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xb8b9cc, 0xadaec5, 0x9c9db4, 0xd8d8e8, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,true,true,false));

        // Frostfangs
        reg(context, FROSTFANG_FOOTHILLS, -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x577a5f, 0x324d38, 0x000000, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, FROSTFANGS,          -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x577a5f, 0x324d38, 0x000000, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, FROSTFANG_HILLS,     -1.0f, 0.6f, 0x3d4ed1, 0x0c113b, 0xa8aab8, 0x969494, 0x848282, 0xc8c8c8, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,true,false,true));
        reg(context, FROSTFANG_MOUNTAINS, -1.0f, 0.5f, 0x3d4ed1, 0x0c113b, 0xb8bac8, 0xc6c6c6, 0xb4b4b4, 0xe0e0e0, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,false,false,true));

        // Valley of Thenn
        reg(context, VALLEY_OF_THENN,           0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x5a6ef2, 0x5c9967, 0x324d38, 0x000000, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, THENN_VALLEY_PLAINS,        0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b096, 0x6b8f6b, 0x587a58, 0xb0c8b0, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,true));
        reg(context, THENN_VALLEY_TUNDRA,        0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b096, 0x6b8f6b, 0x587a58, 0xb0c8b0, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,false));
        reg(context, THENN_VALLEY_WOODS,         0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b096, 0x6b8f6b, 0x587a58, 0xb0c8b0, ModSounds.FOREST_WIND, (b,ctx)->addThenn(b,ctx,true,false,true,false,false,true));
        reg(context, THENN_UPPER_VALLEY_PLAINS,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea88e, 0x5f835f, 0x4e6e4e, 0xa8c0a8, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,true));
        reg(context, THENN_UPPER_VALLEY_TUNDRA,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea88e, 0x5f835f, 0x4e6e4e, 0xa8c0a8, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,false));
        reg(context, THENN_UPPER_VALLEY_WOODS,   0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea88e, 0x5f835f, 0x4e6e4e, 0xa8c0a8, ModSounds.FOREST_WIND, (b,ctx)->addThenn(b,ctx,true,false,true,false,false,true));
        reg(context, THENN_HILLS,                0.15f,0.7f, 0x3d4ed1, 0x0c113b, 0xb0a8a8, 0xad9f9f, 0x9c8e8e, 0xd0c8c8, ModSounds.WINTER_WIND, (b,ctx)->addThenn(b,ctx,false,true,true,true,true,false));
        reg(context, THENN_MOUNTAINS,            0.15f,0.6f, 0x3d4ed1, 0x0c113b, 0xc0b8b8, 0xc5b4b4, 0xb4a3a3, 0xe0d8d8, ModSounds.WINTER_WIND, (b,ctx)->addThenn(b,ctx,false,false,false,true,true,false));

        // Haunted Forest
        reg(context, HAUNTED_FOREST_CLEARING,  -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x828c82, 0x6a746a, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,true,true,false));
        reg(context, HAUNTED_OPEN_WOODLAND,    -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x828c82, 0x6a746a, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,true,true,false));
        reg(context, HAUNTED_FOREST_EDGE,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x778277, 0x616b61, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,true,true,false));
        reg(context, HAUNTED_FOREST_MAIN,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x778277, 0x616b61, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, HAUNTED_BROADLEAF_FOREST, -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x778277, 0x616b61, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,false,true,false,false,false,true,false));
        reg(context, HAUNTED_DEEP_FOREST,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x6d786d, 0x586258, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,false,true,true));
        reg(context, HAUNTED_PINE_FOREST,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x6d786d, 0x586258, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,false,false,true,false,false,true,false));
        reg(context, HAUNTED_IRONWOOD_FOREST,  -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x6d786d, 0x586258, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,true,false,false,false,false,true,false));
        reg(context, HAUNTED_ANCIENT_FOREST,   -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x5a635a, 0x474f47, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,false,false,false,true,false,true,true));
        reg(context, HAUNTED_DENSE_IRONWOOD,   -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7988b5, 0x5a635a, 0x474f47, 0x000000, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,true,false,false,false,false,true,true));

        // Northern Forest
        reg(context, NORTHERN_FOREST,           0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x224d22, 0x1a3d1a, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, NORTHERN_DEEP_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x194219, 0x123412, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, NORTHERN_IRONWOOD_FOREST,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x0d330d, 0x092909, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, NORTHERN_DENSE_IRONWOOD,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x042304, 0x031a03, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, NORTHERN_BROADLEAF_FOREST, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2d5c2d, 0x234823, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,true,false,false,false,false,false));
        reg(context, NORTHERN_BEECH_FOREST,     0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x285228, 0x1e3e1e, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,true,false,false,false,false));
        reg(context, NORTHERN_PINE_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x1e471e, 0x163816, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, NORTHERN_ANCIENT_FOREST,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x113811, 0x0d2b0d, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,false,true,false,true));
        reg(context, NORTHERN_FOREST_EDGE,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x356535, 0x284e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_FOREST_CLEARING,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x487a48, 0x386038, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_OPEN_WOODLAND,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d6e3d, 0x2e552e, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_CONIFER_SCRUB,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x153d15, 0x0f2f0f, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,true,false));

        // Wolfswood
        reg(context, WOLFSWOOD_FOREST,           0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x224d22, 0x1a3d1a, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, WOLFSWOOD_DEEP_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x194219, 0x123412, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, WOLFSWOOD_IRONWOOD_FOREST,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x0d330d, 0x092909, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, WOLFSWOOD_DENSE_IRONWOOD,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x042304, 0x031a03, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, WOLFSWOOD_BROADLEAF_FOREST, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2d5c2d, 0x234823, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,true,false,false,false,false,false));
        reg(context, WOLFSWOOD_BEECH_FOREST,     0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x285228, 0x1e3e1e, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,true,false,false,false,false));
        reg(context, WOLFSWOOD_PINE_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x1e471e, 0x163816, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, WOLFSWOOD_ANCIENT_FOREST,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x113811, 0x0d2b0d, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,false,true,false,true));
        reg(context, WOLFSWOOD_FOREST_EDGE,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x356535, 0x284e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, WOLFSWOOD_CLEARING,         0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x487948, 0x386038, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, WOLFSWOOD_OPEN_WOODLAND,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d6e3d, 0x2e552e, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, WOLFSWOOD_CONIFER_SCRUB,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x153d15, 0x0f2f0f, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,true,false));

        // Bear Island
        reg(context, BEAR_ISLAND,                0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x456245, 0x364d36, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_FOREST,         0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x224d22, 0x1a3d1a, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, BEAR_ISLAND_DEEP_FOREST,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x194219, 0x123412, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, BEAR_ISLAND_IRONWOOD_FOREST,0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x0d330d, 0x092909, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, BEAR_ISLAND_DENSE_IRONWOOD, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x042304, 0x031a03, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, BEAR_ISLAND_BROADLEAF_FOREST,0.2f,0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2d5c2d, 0x234823, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,true,false,false,false,false,false));
        reg(context, BEAR_ISLAND_BEECH_FOREST,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x285228, 0x1e3e1e, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,true,false,false,false,false));
        reg(context, BEAR_ISLAND_PINE_FOREST,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x1e471e, 0x163816, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, BEAR_ISLAND_ANCIENT_FOREST, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x113811, 0x0d2b0d, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,false,true,false,true));
        reg(context, BEAR_ISLAND_FOREST_EDGE,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x356535, 0x284e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_CLEARING,       0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x487948, 0x386038, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_OPEN_WOODLAND,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d6e3d, 0x2e552e, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_CONIFER_SCRUB,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x153d15, 0x0f2f0f, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,true,false));

        // Hornwood
        reg(context, HORNWOOD_FOREST,           0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7b8b0, 0x4a7a5a, 0x3a6048, 0xb0c8bc, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,false));
        reg(context, HORNWOOD_DEEP_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7b8b0, 0x2d5c3a, 0x224830, 0xb0c8bc, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, HORNWOOD_PINE_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7b8b0, 0x1e4a2a, 0x163820, 0xb0c8bc, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, HORNWOOD_IRONWOOD_FOREST,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7b8b0, 0x163a22, 0x102c1a, 0xb0c8bc, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,true));
        reg(context, HORNWOOD_MOUNTAINS,        0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0xcbcfcd, 0xb0b5b2, 0xe0e3e2, ModSounds.WINTER_WIND, (b,ctx)->{});

        // Bolton Domain
        reg(context, BOLTON_DOMAIN_PLAINS,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a0, 0x375143, 0x2c4136, 0xb0c8b8, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, BOLTON_DOMAIN_TUNDRA,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a0, 0x375143, 0x2c4136, 0xb0c8b8, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, BOLTON_DOMAIN_WOODS,        0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a0, 0x375143, 0x2c4136, 0xb0c8b8, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,false,false));
        reg(context, BOLTON_DOMAIN_HILLS_PLAINS, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea89a, 0x344c3f, 0x293d32, 0xa8c0b0, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, BOLTON_DOMAIN_HILLS_TUNDRA, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea89a, 0x344c3f, 0x293d32, 0xa8c0b0, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, BOLTON_DOMAIN_HILLS_WOODS,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea89a, 0x344c3f, 0x293d32, 0xa8c0b0, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,true,false));
        reg(context, BOLTON_DOMAIN_RIVER_VALLEY, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a0, 0x3b5748, 0x304839, 0xb0c8b8, null,
                (b,ctx)->{ addRiverFeatures(b,ctx); addDomain(b,ctx,false,false); });

        // Manderly Domain — warmest, flat, calcite underground
        reg(context, MANDERLY_DOMAIN_PLAINS,      0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b8a8, 0x316856, 0x275446, 0xb0c8b8, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_TUNDRA,      0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b8a8, 0x316856, 0x275446, 0xb0c8b8, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_WOODS,       0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b8a8, 0x316856, 0x275446, 0xb0c8b8, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_DEEP_PLAINS, 0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8eb0a0, 0x2f6050, 0x254c40, 0xa8c0b0, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_DEEP_TUNDRA, 0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8eb0a0, 0x2f6050, 0x254c40, 0xa8c0b0, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_DEEP_WOODS,  0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8eb0a0, 0x2f6050, 0x254c40, 0xa8c0b0, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_COAST,       0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b8a8, 0x37715e, 0x2c5c4b, 0xb0c8b8, null,
                (b,ctx)->{ addRiverFeatures(b,ctx); addDomainCalcite(b,ctx,false); });

        // Flint Domain — calcite underground
        reg(context, FLINT_DOMAIN_PLAINS,      0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a8, 0x2a5d4c, 0x214a3d, 0xb0c8c0, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_TUNDRA,      0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a8, 0x2a5d4c, 0x214a3d, 0xb0c8c0, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_WOODS,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a8, 0x2a5d4c, 0x214a3d, 0xb0c8c0, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, FLINT_DOMAIN_DEEP_PLAINS, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea8a0, 0x285747, 0x1f4639, 0xa8c0b8, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_DEEP_TUNDRA, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea8a0, 0x285747, 0x1f4639, 0xa8c0b8, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_DEEP_WOODS,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8ea8a0, 0x285747, 0x1f4639, 0xa8c0b8, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, FLINT_DOMAIN_COAST,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96b0a8, 0x2f5246, 0x254139, 0xb0c8c0, null,
                (b,ctx)->{ addRiverFeatures(b,ctx); addDomainCalcite(b,ctx,false); });

        // Karstark Domain — colder, rougher
        reg(context, KARSTARK_DOMAIN_PLAINS,       0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x96a89c, 0x26543b, 0x1d4230, 0xb0c0b8, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, KARSTARK_DOMAIN_TUNDRA,       0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x96a89c, 0x26543b, 0x1d4230, 0xb0c0b8, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, KARSTARK_DOMAIN_WOODS,        0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x96a89c, 0x26543b, 0x1d4230, 0xb0c0b8, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,false,false));
        reg(context, KARSTARK_DOMAIN_DEEP_PLAINS,  0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x8ea094, 0x214a34, 0x193a28, 0xa8b8b0, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, KARSTARK_DOMAIN_DEEP_TUNDRA,  0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x8ea094, 0x214a34, 0x193a28, 0xa8b8b0, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, KARSTARK_DOMAIN_DEEP_WOODS,   0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x8ea094, 0x214a34, 0x193a28, 0xa8b8b0, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,true,false));
        reg(context, KARSTARK_DOMAIN_INLAND,       0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x8ea094, 0x3e523d, 0x304030, 0xa8b8b0, null,                  (b,ctx)->addKarstarkInland(b,ctx));
        reg(context, KARSTARK_DOMAIN_COAST,        0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x8ea094, 0x535353, 0x424242, 0xa8b8b0, null,                  (b,ctx)->addKarstarkCoast(b,ctx));

        // Other Northern
        reg(context, NORTHERN_HILLS,    0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x8f9e98, 0x7a8a83, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_MOUNTAINS,0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0xcbcfcd, 0xb0b5b2, 0xe0e3e2, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, BARROWLANDS,       0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_PLAINS,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f55, 0x3d4f45, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, RILLS,             0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x35a641, 0x35a641, 0xa7addb, null,
                (b,ctx)->{ addRiverFeatures(b,ctx); addForest(b,ctx,true,false,false,false,false,false,true,false); });
        reg(context, NORTHERN_WATERS,   0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb, null,   (b,ctx)->addRiverFeatures(b,ctx));
        reg(context, STONY_SHORES,      0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x47a651, 0x47a651, 0xa7addb, null,   (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, THE_NECK,          0.5f, 0.8f, 0x294018, 0x1f1308, 0x638a6d, 0x27471f, 0x27471f, 0x30523b, null,
                (b,ctx)->{ BiomeDefaultFeatures.addMangroveSwampVegetation(b); addRiverFeatures(b,ctx); });

        // Umber Domain
        reg(context, UMBER_DOMAIN_TUNDRA,       0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8aaa96, 0x2a5d3e, 0x1f4a30, 0xa8c4b4, null,                  (b,ctx)->addUmber(b,ctx));
        reg(context, UMBER_DOMAIN_BARRENS,      0.2f, 0.7f, 0x3d4ed1, 0x0c113b, 0x8aaa96, 0x2a5d3e, 0x1f4a30, 0xa8c4b4, null,                  (b,ctx)->addUmberBarrens(b,ctx));
        reg(context, UMBER_DOMAIN_WOODS,        0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8aaa96, 0x2a5d3e, 0x1f4a30, 0xa8c4b4, ModSounds.FOREST_WIND, (b,ctx)->addUmberWoods(b,ctx));
        reg(context, UMBER_DOMAIN_DEEP_TUNDRA,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x82a28e, 0x326647, 0x265237, 0xa0bcaa, null,                  (b,ctx)->addUmber(b,ctx));
        reg(context, UMBER_DOMAIN_DEEP_BARRENS, 0.2f, 0.7f, 0x3d4ed1, 0x0c113b, 0x82a28e, 0x326647, 0x265237, 0xa0bcaa, null,                  (b,ctx)->addUmberBarrens(b,ctx));
        reg(context, UMBER_DOMAIN_DEEP_WOODS,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x82a28e, 0x326647, 0x265237, 0xa0bcaa, ModSounds.FOREST_WIND, (b,ctx)->addUmberWoods(b,ctx));

        // Skagos
        reg(context, SKAGOS_TUNDRA,       -0.45f,0.85f,0x3d4ed1,0x0c113b,0x8f97c4,0x8a9582,0x6e7867,0xc9ced7, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,true,false,false,true,true,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_WOODS,        -0.25f,0.9f, 0x3d4ed1,0x0c113b,0x8a94c4,0x61755b,0x455843,0x9fa7b4, ModSounds.FOREST_WIND, (b,ctx)->{ addSkagos(b,ctx,true,true,true,true,false,true,true); addSkagosBushes(b); });
        reg(context, SKAGOS_BARRENS,      -0.7f, 0.7f, 0x3d4ed1,0x0c113b,0x98a0c8,0x909090,0x777777,0xd7d7d7, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,false,false,false,true,true,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_HILLS,        -0.55f,0.8f, 0x3d4ed1,0x0c113b,0x9099c8,0x7b866f,0x66715c,0xbec4cd, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,true,false,false,true,true,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_VALLEY,       -0.1f, 0.95f,0x3d4ed1,0x0c113b,0x8895c6,0x5c7d55,0x476444,0xa4adb9, ModSounds.FOREST_WIND, (b,ctx)->{ addSkagos(b,ctx,true,false,true,true,true,true,true); addSkagosBushes(b); });
        reg(context, SKAGOS_MOUNTAINS,    -0.8f, 0.75f,0x3d4ed1,0x0c113b,0x9ba4cc,0xc4c4c4,0xaaaaaa,0xe6e6e6, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,false,false,false,false,false,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_FROZEN_PEAKS, -1.0f, 0.8f, 0x3d4ed1,0x0c113b,0xb5c2e0,0xececec,0xececec,0xffffff, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,false,false,false,false,false,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_RIVER,         0.2f, 0.8f, 0x315d7d,0x1b3850,0x647c91,0x566b4b,0x4f6245,0x8294a3, null,                  (b,ctx)->addRiverFeatures(b,ctx));
        reg(context, SKAGOS_BOG,          -0.45f,0.85f,0x3d4ed1,0x0c113b,0x8f97c4,0x8a9582,0x6e7867,0xc9ced7, ModSounds.WINTER_WIND,
                (b,ctx)->{ f(b,ModplacedFeatures.CLAY_PATCH_PLACED_KEY); f(b,ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.MUD_PATCH_PLACED_KEY);
                    addSkagos(b,ctx,true,false,false,true,true,true,false);
                    addSkagosBushes(b); });

        // Night's Watch
        reg(context, THE_WALL,            -0.8f, 0.8f, 0x3d4ed1,0x0c113b,0xd0e0f0,0xe8f0e8,0xe0ece0,0xffffff, ModSounds.WINTER_WIND, (b,ctx)->addWall(b,ctx));
        reg(context, THE_GIFT_TUNDRA,      0.1f, 0.8f, 0x3d4ed1,0x0c113b,0x9ab0a6,0x6a7b71,0x576866,0xb0c4bb, null,                  (b,ctx)->addGiftTundra(b,ctx));
        reg(context, THE_GIFT_BARRENS,     0.1f, 0.7f, 0x3d4ed1,0x0c113b,0x9ab0a6,0x6a7b71,0x576866,0xb0c4bb, null,                  (b,ctx)->addGiftBarrens(b,ctx));
        reg(context, THE_GIFT_WOODS,       0.1f, 0.8f, 0x3d4ed1,0x0c113b,0x9ab0a6,0x6a7b71,0x576866,0xb0c4bb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, THE_NEW_GIFT_TUNDRA,  0.2f, 0.8f, 0x3d4ed1,0x0c113b,0xa0b8ac,0x5b6d63,0x4a5c52,0xbacec6, null,                  (b,ctx)->addGiftTundra(b,ctx));
        reg(context, THE_NEW_GIFT_BARRENS, 0.2f, 0.7f, 0x3d4ed1,0x0c113b,0xa0b8ac,0x5b6d63,0x4a5c52,0xbacec6, null,                  (b,ctx)->addGiftBarrens(b,ctx));
        reg(context, THE_NEW_GIFT_WOODS,   0.2f, 0.8f, 0x3d4ed1,0x0c113b,0xa0b8ac,0x5b6d63,0x4a5c52,0xbacec6, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
    }

    // =========================================================================
    // FACTORY
    // =========================================================================

    @FunctionalInterface
    private interface VegetationSetup {
        void apply(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx);
    }

    private static void reg(
            BootstrapContext<Biome> context, ResourceKey<Biome> key,
            float temperature, float downfall,
            int water, int waterFog, int sky, int grass, int foliage, int fog,
            DeferredHolder<net.minecraft.sounds.SoundEvent, net.minecraft.sounds.SoundEvent> ambientLoop,
            VegetationSetup vegetation) {
        MobSpawnSettings.Builder spawn = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder gen = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(gen);
        addModOres(gen, context);
        vegetation.apply(gen, context);
        BiomeSpecialEffects.Builder fx = new BiomeSpecialEffects.Builder()
                .waterColor(water).waterFogColor(waterFog).skyColor(sky)
                .grassColorOverride(grass).foliageColorOverride(foliage).fogColor(fog)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND));
        if (ambientLoop != null) fx.ambientLoopSound(ambientLoop);
        context.register(key, new Biome.BiomeBuilder()
                .hasPrecipitation(true).downfall(downfall).temperature(temperature)
                .generationSettings(gen.build()).mobSpawnSettings(spawn.build())
                .specialEffects(fx.build()).build());
    }

    private static void f(BiomeGenerationSettings.Builder b, ResourceKey<net.minecraft.world.level.levelgen.placement.PlacedFeature> key) {
        b.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, key);
    }

    // =========================================================================
    // GLOBAL GENERATION & ORES
    // =========================================================================

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder b) {
        BiomeDefaultFeatures.addDefaultCrystalFormations(b);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(b);
        BiomeDefaultFeatures.addSurfaceFreezing(b);
    }

    private static void addModOres(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
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

    private static void addRiverFeatures(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b, ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
        f(b, ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
        f(b, ModplacedFeatures.MUD_PATCH_PLACED_KEY);
        f(b, ModplacedFeatures.SEAGRASS_KEY);
        f(b, ModplacedFeatures.KELP_KEY);
    }

    // =========================================================================
    // MASTER FEATURE ORDER (never deviate):
    // TREES: ASH→CHESTNUT→IRONWOOD→SENTINEL→FIR→PINE→SOLDIER_PINE
    //        then weirwood block → OAK2→HAWTHORN→BEECH (always after weirwood)
    // FLOWERS: FORGET_ME_NOT→FROSTFIRE→LIVERWORT→LUNGWORT→PENNYROYAL→RED_ROSE_BUSH
    // THORNS: THISTLE→THORN_BUSH→WINTER_ROSE_BUSH
    // SEDGE
    // GRASS: GRASS→TALL_GRASS
    // FERNS: FERN→LARGE_FERN
    // ROCKS: STONE_PATCH→GRAVEL_PATCH→STONE_PILE→TOR
    // BUSHES: BIRCH_BUSH→WILLOW_BUSH
    // =========================================================================

    // ── FOREST VEGETATION ────────────────────────────────────────────────────

    private static void addForest(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                  boolean all, boolean ironwood, boolean broadleaf, boolean beech,
                                  boolean pine, boolean ancient, boolean sparse, boolean weirwood) {

        if (all) {
            if (sparse) {
                f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.ASH_KEY); f(b,ModplacedFeatures.CHESTNUT_KEY);
                f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
                f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        if (ironwood) {
            if (sparse) {
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
                f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        if (broadleaf) {
            if (sparse) {
                f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.ASH_KEY); f(b,ModplacedFeatures.CHESTNUT_KEY);
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY);
            }
        }
        if (beech) {
            f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        }
        if (pine) {
            if (sparse) {
                f(b,ModplacedFeatures.SENTINEL_RARE_KEY); f(b,ModplacedFeatures.FIR_RARE_KEY);
                f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.SENTINEL_KEY); f(b,ModplacedFeatures.FIR_KEY);
                f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        if (ancient) {
            if (sparse) {
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
                f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        // weirwood always before oak2/hawthorn/beech
        if (weirwood) f(b, sparse ? ModplacedFeatures.WEIRWOOD_RARE_KEY : ModplacedFeatures.WEIRWOOD_KEY);
        if (all || broadleaf || beech) {
            f(b, sparse ? ModplacedFeatures.OAK2_RARE_KEY : ModplacedFeatures.OAK2_KEY);
            f(b, sparse ? ModplacedFeatures.HAWTHORN_RARE_KEY : ModplacedFeatures.HAWTHORN_KEY);
            f(b, sparse ? ModplacedFeatures.BEECH_RARE_KEY : ModplacedFeatures.BEECH_KEY);
        }

        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
    }

    // ── HAUNTED FOREST VEGETATION ─────────────────────────────────────────────

    private static void addHaunted(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                   boolean all, boolean ironwood, boolean broadleaf, boolean pine, boolean ancient,
                                   boolean sparse, boolean weirwood, boolean denseWeirwood) {

        if (all) {
            if (sparse) {
                f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.ASH_KEY); f(b,ModplacedFeatures.CHESTNUT_KEY);
                f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
                f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        if (ironwood) {
            if (sparse) {
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
                f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        if (broadleaf) {
            if (sparse) {
                f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.ASH_KEY); f(b,ModplacedFeatures.CHESTNUT_KEY);
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
            }
        }
        if (pine) {
            if (sparse) {
                f(b,ModplacedFeatures.SENTINEL_RARE_KEY); f(b,ModplacedFeatures.FIR_RARE_KEY);
                f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.SENTINEL_KEY); f(b,ModplacedFeatures.FIR_KEY);
                f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        if (ancient) {
            if (sparse) {
                f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
                f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            } else {
                f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
                f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            }
        }
        // weirwood always before oak2
        if (weirwood) f(b, denseWeirwood ? ModplacedFeatures.WEIRWOOD_KEY : ModplacedFeatures.WEIRWOOD_RARE_KEY);
        if (all || broadleaf) f(b, sparse ? ModplacedFeatures.OAK2_RARE_KEY : ModplacedFeatures.OAK2_KEY);

        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
    }

    // ── BEYOND THE WALL ───────────────────────────────────────────────────────

    private static void addBTW(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                               boolean grass, boolean trees, boolean bushes, boolean rocks) {
        if (trees) {
            f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
            f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        }
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        if (grass) {
            f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.SEDGE_KEY);
            f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        }
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
        if (bushes) { f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow")); }
    }

    // ── FROSTFANG ─────────────────────────────────────────────────────────────

    private static void addFrostfang(BiomeGenerationSettings.Builder b, boolean grass, boolean bushes, boolean tor) {
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        if (grass) {
            f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.SEDGE_KEY);
            f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        }
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY);
        if (tor) f(b,ModplacedFeatures.TOR_KEY);
        if (bushes) { f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow")); }
    }

    // ── THENN ─────────────────────────────────────────────────────────────────

    private static void addThenn(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                 boolean dense, boolean sparse, boolean grass, boolean rocks, boolean tor, boolean bushes) {
        if (sparse) {
            f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
            f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
            f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY); f(b,ModplacedFeatures.OAK2_RARE_KEY);
        }
        if (dense) {
            f(b,ModplacedFeatures.ASH_KEY); f(b,ModplacedFeatures.CHESTNUT_KEY);
            f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
            f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY); f(b,ModplacedFeatures.OAK2_KEY);
        }
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        if (grass) {
            f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
            f(b,ModplacedFeatures.SEDGE_KEY);
            f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
            f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        }
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
        if (tor)   f(b,ModplacedFeatures.TOR_KEY);
        if (bushes) { f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow")); }
    }

    // ── NOBLE DOMAIN VEGETATION ───────────────────────────────────────────────
    // shared ground cover for Bolton, Karstark (no calcite)

    private static void addDomainGroundCover(BiomeGenerationSettings.Builder b) {
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // plains/tundra — rare trees, optional rocks
    private static void addDomain(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                  boolean rocks, boolean calcite) {
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        addDomainGroundCover(b);
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
    }

    // woods — full trees
    private static void addDomainWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                       boolean rocks, boolean calcite) {
        f(b,ModplacedFeatures.ASH_KEY); f(b,ModplacedFeatures.CHESTNUT_KEY);
        f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
        f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_KEY); f(b,ModplacedFeatures.HAWTHORN_KEY); f(b,ModplacedFeatures.BEECH_KEY);
        addDomainGroundCover(b);
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
    }

    // calcite variants — same as above but with underground calcite deposit
    private static void addDomainCalcite(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx, boolean rocks) {
        addDomain(b, ctx, rocks, false);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CALCITE_DEPOSIT_PLACED_KEY);
    }

    private static void addDomainCalciteWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx, boolean rocks) {
        addDomainWoods(b, ctx, rocks, false);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CALCITE_DEPOSIT_PLACED_KEY);
    }

    private static void addKarstarkInland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.STONE_PILE_KEY); f(b,ModplacedFeatures.TOR_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addKarstarkCoast(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
        f(b,ModplacedFeatures.STONE_PILE_KEY); f(b,ModplacedFeatures.TOR_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // ── SKAGOS ────────────────────────────────────────────────────────────────

    private static void addSkagos(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                  boolean trees, boolean dense, boolean flowers, boolean grass, boolean sedge, boolean ferns, boolean weirwood) {
        if (trees) {
            if (dense) {
                f(b,ModplacedFeatures.SENTINEL_KEY); f(b,ModplacedFeatures.FIR_KEY);
                f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
            } else {
                f(b,ModplacedFeatures.SENTINEL_RARE_KEY); f(b,ModplacedFeatures.FIR_RARE_KEY);
                f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            }
        }
        if (weirwood) f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        if (flowers)  f(b,ModplacedFeatures.FORGET_ME_NOT_KEY);
        f(b,ModplacedFeatures.FROSTFIRE_KEY); f(b,ModplacedFeatures.LIVERWORT_KEY);
        if (flowers)  { f(b,ModplacedFeatures.LUNGWORT_KEY); f(b,ModplacedFeatures.PENNYROYAL_KEY); }
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addSkagosRocks(BiomeGenerationSettings.Builder b) {
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
        f(b,ModplacedFeatures.STONE_PILE_KEY); f(b,ModplacedFeatures.TOR_KEY);
    }

    private static void addSkagosBushes(BiomeGenerationSettings.Builder b) {
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // ── WALL ──────────────────────────────────────────────────────────────────

    private static void addWall(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // ── GIFT ──────────────────────────────────────────────────────────────────

    private static void addGiftTundra(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addGiftBarrens(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.SENTINEL_RARE_KEY); f(b,ModplacedFeatures.FIR_RARE_KEY);
        f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // ── UMBER ─────────────────────────────────────────────────────────────────

    private static void addUmber(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.OAK2_RARE_KEY);
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addUmberBarrens(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.OAK2_RARE_KEY);
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    private static void addUmberWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.OAK2_KEY);
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY);
    }
}