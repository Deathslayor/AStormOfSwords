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
    public static final ResourceKey<Biome> BARROWLANDS         = key("barrowlands");
    public static final ResourceKey<Biome> BARROWLANDS_2       = key("barrowlands_2");
    public static final ResourceKey<Biome> BARROWLANDS_3       = key("barrowlands_3");
    public static final ResourceKey<Biome> BARROWLANDS_BARRENS = key("barrowlands_barrens");
    public static final ResourceKey<Biome> NORTHERN_PLAINS   = key("northern_plains");
    public static final ResourceKey<Biome> RILLS             = key("rills");
    public static final ResourceKey<Biome> RILLS_RIVER_VALLEY= key("rills_river_valley");
    public static final ResourceKey<Biome> RILLS_COAST           = key("rills_coast");
    public static final ResourceKey<Biome> NORTHERN_WATERS       = key("northern_waters");
    public static final ResourceKey<Biome> SHALLOW_OCEAN         = key("shallow_ocean");
    public static final ResourceKey<Biome> COLD_SHALLOW_OCEAN    = key("cold_shallow_ocean");
    public static final ResourceKey<Biome> DEEP_OCEAN_NORTH      = key("deep_ocean_north");
    public static final ResourceKey<Biome> NECK_WETLANDS         = key("neck_wetlands");
    public static final ResourceKey<Biome> NORTHERN_WETLANDS     = key("northern_wetlands");
    public static final ResourceKey<Biome> NORTHERN_LAKE         = key("northern_lake");
    public static final ResourceKey<Biome> ARCTIC_LAKE           = key("arctic_lake");
    public static final ResourceKey<Biome> NECK_RIVER            = key("neck_river");
    public static final ResourceKey<Biome> NORTHERN_RIVER        = key("northern_river");
    public static final ResourceKey<Biome> ARCTIC_RIVER          = key("arctic_river");
    public static final ResourceKey<Biome> STONY_SHORES       = key("stony_shores");
    public static final ResourceKey<Biome> STONY_SHORES_2     = key("stony_shores_2");
    public static final ResourceKey<Biome> STONY_SHORES_COAST       = key("stony_shores_coast");
    public static final ResourceKey<Biome> NORTHERN_CLANS_HIGHLANDS = key("northern_clans_highlands");
    public static final ResourceKey<Biome> NORTHERN_CLANS_UPPER     = key("northern_clans_upper");
    public static final ResourceKey<Biome> NORTHERN_CLANS_COAST     = key("northern_clans_coast");
    public static final ResourceKey<Biome> CAPE_KRAKEN_INLAND       = key("cape_kraken_inland");
    public static final ResourceKey<Biome> CAPE_KRAKEN_NORTH_COAST  = key("cape_kraken_north_coast");
    public static final ResourceKey<Biome> CAPE_KRAKEN_COAST_MID    = key("cape_kraken_coast_mid");
    public static final ResourceKey<Biome> CAPE_KRAKEN_COAST_SOUTH  = key("cape_kraken_coast_south");
    public static final ResourceKey<Biome> THE_NECK                  = key("the_neck");
    public static final ResourceKey<Biome> THE_NECK_2                = key("the_neck_2");

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
        reg(context, LANDS_OF_ALWAYS_WINTER, -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8990be, 0x849588, 0x6b8070, 0x695aa5, ModSounds.WINTER_WIND,
                (b,ctx) -> f(b, ModplacedFeatures.WEIRWOOD_KEY));

        // Beyond the Wall
        reg(context, BEYOND_THE_WALL,                  -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xbcc2db, 0x849588, 0x6b8070, 0xd6d2e6, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,true,false,false,false));
        reg(context, BEYOND_THE_WALL_ICE_RIVER_VALLEY, -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xbcc2db, 0x849589, 0x6b8071, 0xd6d2e6, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,false,false,false,true));
        reg(context, BEYOND_THE_WALL_FROZEN_SHORE,     -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xbcc2db, 0x849588, 0x6b8070, 0xd6d2e6, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,false,false,false,true));
        reg(context, BEYOND_THE_WALL_RIVER_VALLEY,     -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xbcc2db, 0x849588, 0x6b8070, 0xd6d2e6, ModSounds.WINTER_WIND, (b,ctx)->addBTW(b,ctx,true,true,true,false));

        // Giant's Stair
        reg(context, GIANTS_STAIR,              -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xb0b4d4, 0x84958d, 0x6b8077, 0xcbcde3, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,false,false,true));
        reg(context, GIANTS_STAIR_RIVER_VALLEY, -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0xb0b4d4, 0x84958d, 0x6b8077, 0xcbcde3, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,true,true,false));

        // Frostfangs
        reg(context, FROSTFANG_FOOTHILLS, -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x5c6c63, 0x3b4740, 0x6857a8, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, FROSTFANGS,          -1.0f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8990be, 0x5d6b63, 0x3b4740, 0x695aa5, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, FROSTFANG_HILLS,     -1.0f, 0.6f, 0x3d4ed1, 0x0c113b, 0x9fa4cb, 0x7c8e81, 0x697e6f, 0xbcb5d7, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,true,false,true));
        reg(context, FROSTFANG_MOUNTAINS, -1.0f, 0.5f, 0x3d4ed1, 0x0c113b, 0xaeb2d3, 0x849588, 0x6b8070, 0xd0cbe3, ModSounds.WINTER_WIND, (b,ctx)->addFrostfang(b,false,false,true));

        // Valley of Thenn
        reg(context, VALLEY_OF_THENN,           0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8c94d0, 0x667d70, 0x394940, 0x604ab5, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, THENN_VALLEY_PLAINS,        0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x89a5cf, 0x677f71, 0x546d5e, 0xa2b8d9, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,true));
        reg(context, THENN_VALLEY_TUNDRA,        0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x89a5cf, 0x677f71, 0x546d5e, 0xa2b8d9, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,false));
        reg(context, THENN_VALLEY_WOODS,         0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x89a5cf, 0x677f71, 0x546d5e, 0xa2b8d9, ModSounds.FOREST_WIND, (b,ctx)->addThenn(b,ctx,true,false,true,false,false,true));
        reg(context, THENN_UPPER_VALLEY_PLAINS,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x819fcc, 0x5f7568, 0x4d6356, 0x9ab2d6, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,true));
        reg(context, THENN_UPPER_VALLEY_TUNDRA,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x819fcc, 0x5f7568, 0x4d6356, 0x9ab2d6, null,                  (b,ctx)->addThenn(b,ctx,false,true,true,false,false,false));
        reg(context, THENN_UPPER_VALLEY_WOODS,   0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x819fcc, 0x5f7568, 0x4d6356, 0x9ab2d6, ModSounds.FOREST_WIND, (b,ctx)->addThenn(b,ctx,true,false,true,false,false,true));
        reg(context, THENN_HILLS,                0.15f,0.7f, 0x3d4ed1, 0x0c113b, 0x9f93d2, 0x809886, 0x66846e, 0xbbb2df, ModSounds.WINTER_WIND, (b,ctx)->addThenn(b,ctx,false,true,true,true,true,false));
        reg(context, THENN_MOUNTAINS,            0.15f,0.6f, 0x3d4ed1, 0x0c113b, 0xada2d9, 0x809886, 0x66846e, 0xc9c2e6, ModSounds.WINTER_WIND, (b,ctx)->addThenn(b,ctx,false,false,false,true,true,false));

        // Haunted Forest
        reg(context, HAUNTED_FOREST_CLEARING,  -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x718479, 0x5b6e63, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,true,true,false));
        reg(context, HAUNTED_OPEN_WOODLAND,    -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x718479, 0x5b6e63, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,true,true,false));
        reg(context, HAUNTED_FOREST_EDGE,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x6a7c71, 0x55675c, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,true,true,false));
        reg(context, HAUNTED_FOREST_MAIN,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x6a7c71, 0x55675c, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, HAUNTED_BROADLEAF_FOREST, -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x6a7c71, 0x55675c, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,false,true,false,false,false,true,false));
        reg(context, HAUNTED_DEEP_FOREST,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x63746a, 0x4f6056, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,true,false,false,false,false,false,true,true));
        reg(context, HAUNTED_PINE_FOREST,      -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x63746a, 0x4f6056, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,false,false,true,false,false,true,false));
        reg(context, HAUNTED_IRONWOOD_FOREST,  -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x63746a, 0x4f6056, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,true,false,false,false,false,true,false));
        reg(context, HAUNTED_ANCIENT_FOREST,   -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x55645b, 0x435148, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,false,false,false,true,false,true,true));
        reg(context, HAUNTED_DENSE_IRONWOOD,   -0.8f, 0.8f, 0x3d4ed1, 0x0c113b, 0x878fc0, 0x55645b, 0x435148, 0x6857a8, ModSounds.FOREST_WIND, (b,ctx)->addHaunted(b,ctx,false,true,false,false,false,false,true,true));

        // Northern Forest
        reg(context, NORTHERN_FOREST,           0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x39463e, 0x2c3931, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, NORTHERN_DEEP_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x323d37, 0x26312b, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, NORTHERN_IRONWOOD_FOREST,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, NORTHERN_DENSE_IRONWOOD,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, NORTHERN_BROADLEAF_FOREST, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x415147, 0x324138, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,true,false,false,false,false,false));
        reg(context, NORTHERN_BEECH_FOREST,     0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3c4a42, 0x2d3b33, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,true,false,false,false,false));
        reg(context, NORTHERN_PINE_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x35423a, 0x29352e, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, NORTHERN_ANCIENT_FOREST,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,false,true,false,true));
        reg(context, NORTHERN_FOREST_EDGE,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x47584e, 0x36463c, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_FOREST_CLEARING,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x54685c, 0x415449, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_OPEN_WOODLAND,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f54, 0x3a4b41, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_CONIFER_SCRUB,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2f3a33, 0x242e28, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,true,false));

        // Wolfswood
        reg(context, WOLFSWOOD_FOREST,           0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x39463e, 0x2c3931, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, WOLFSWOOD_DEEP_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x323d37, 0x26312b, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, WOLFSWOOD_IRONWOOD_FOREST,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, WOLFSWOOD_DENSE_IRONWOOD,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, WOLFSWOOD_BROADLEAF_FOREST, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x415147, 0x324138, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,true,false,false,false,false,false));
        reg(context, WOLFSWOOD_BEECH_FOREST,     0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3c4a42, 0x2d3b33, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,true,false,false,false,false));
        reg(context, WOLFSWOOD_PINE_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x35423a, 0x29352e, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, WOLFSWOOD_ANCIENT_FOREST,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,false,true,false,true));
        reg(context, WOLFSWOOD_FOREST_EDGE,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x47584e, 0x36463c, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, WOLFSWOOD_CLEARING,         0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x54685c, 0x415449, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, WOLFSWOOD_OPEN_WOODLAND,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f54, 0x3a4b41, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, WOLFSWOOD_CONIFER_SCRUB,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2f3a33, 0x242e28, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,true,false));

        // Bear Island
        reg(context, BEAR_ISLAND,                0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4b5d53, 0x3a4b41, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_FOREST,         0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x39463e, 0x2c3931, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, BEAR_ISLAND_DEEP_FOREST,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x323d37, 0x26312b, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, BEAR_ISLAND_IRONWOOD_FOREST,0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, BEAR_ISLAND_DENSE_IRONWOOD, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, BEAR_ISLAND_BROADLEAF_FOREST,0.2f,0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x415147, 0x324138, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,true,false,false,false,false,false));
        reg(context, BEAR_ISLAND_BEECH_FOREST,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3c4a42, 0x2d3b33, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,true,false,false,false,false));
        reg(context, BEAR_ISLAND_PINE_FOREST,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x35423a, 0x29352e, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, BEAR_ISLAND_ANCIENT_FOREST, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2e3832, 0x242e28, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,false,true,false,true));
        reg(context, BEAR_ISLAND_FOREST_EDGE,    0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x47584e, 0x36463c, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_CLEARING,       0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x54685c, 0x415449, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_OPEN_WOODLAND,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f54, 0x3a4b41, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, BEAR_ISLAND_CONIFER_SCRUB,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x2f3a33, 0x242e28, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,true,false));

        // Hornwood
        reg(context, HORNWOOD_FOREST,           0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96a9d4, 0x55695e, 0x42554a, 0xa2b4d9, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,false));
        reg(context, HORNWOOD_DEEP_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96a9d4, 0x415148, 0x324138, 0xa2b4d9, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,false));
        reg(context, HORNWOOD_PINE_FOREST,      0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96a9d4, 0x36433c, 0x29352e, 0xa2b4d9, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,false,false,false,true,false,false,false));
        reg(context, HORNWOOD_IRONWOOD_FOREST,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x96a9d4, 0x2e3933, 0x242e28, 0xa2b4d9, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,false,true,false,false,false,false,false,true));
        reg(context, HORNWOOD_MOUNTAINS,        0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x80988b, 0x668474, 0xc7d1e8, ModSounds.WINTER_WIND, (b,ctx)->{});

        // Bolton Domain
        reg(context, BOLTON_DOMAIN_PLAINS,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x89a1cf, 0x415048, 0x33423a, 0xa2b5d9, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, BOLTON_DOMAIN_TUNDRA,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x89a1cf, 0x415048, 0x33423a, 0xa2b5d9, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, BOLTON_DOMAIN_WOODS,        0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x89a1cf, 0x415048, 0x33423a, 0xa2b5d9, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,false,false));
        reg(context, BOLTON_DOMAIN_HILLS_PLAINS, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x819acc, 0x3e4d45, 0x313f37, 0x9aafd6, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, BOLTON_DOMAIN_HILLS_TUNDRA, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x819acc, 0x3e4d45, 0x313f37, 0x9aafd6, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, BOLTON_DOMAIN_HILLS_WOODS,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x819acc, 0x3e4d45, 0x313f37, 0x9aafd6, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,true,false));
        reg(context, BOLTON_DOMAIN_RIVER_VALLEY, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x89a1cf, 0x44544b, 0x36473e, 0xa2b5d9, null,
                (b,ctx)->{ addRiverFeatures(b,ctx); addDomain(b,ctx,false,false); });

        // Manderly Domain — warmest, flat, calcite underground
        reg(context, MANDERLY_DOMAIN_PLAINS,      0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8da3d0, 0x47574e, 0x37483f, 0xa2b5d9, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_TUNDRA,      0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8da3d0, 0x47574e, 0x37483f, 0xa2b5d9, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_WOODS,       0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8da3d0, 0x47574e, 0x37483f, 0xa2b5d9, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_DEEP_PLAINS, 0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x859ccd, 0x43534b, 0x34443b, 0x9aafd6, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_DEEP_TUNDRA, 0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x859ccd, 0x43534b, 0x34443b, 0x9aafd6, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_DEEP_WOODS,  0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x859ccd, 0x43534b, 0x34443b, 0x9aafd6, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, MANDERLY_DOMAIN_COAST,       0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8da3d0, 0x4c5d54, 0x3c4d44, 0xa2b5d9, null,
                (b,ctx)->{ addRiverFeatures(b,ctx); addDomainCalcite(b,ctx,false); });

        // Flint Domain — calcite underground
        reg(context, FLINT_DOMAIN_PLAINS,      0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x899ecf, 0x415048, 0x324139, 0xa2b2d9, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_TUNDRA,      0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x899ecf, 0x415048, 0x324139, 0xa2b2d9, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_WOODS,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x899ecf, 0x415048, 0x324139, 0xa2b2d9, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, FLINT_DOMAIN_DEEP_PLAINS, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8197cc, 0x3e4c45, 0x303e37, 0x9aacd6, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_DEEP_TUNDRA, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8197cc, 0x3e4c45, 0x303e37, 0x9aacd6, null,                  (b,ctx)->addDomainCalcite(b,ctx,false));
        reg(context, FLINT_DOMAIN_DEEP_WOODS,  0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x8197cc, 0x3e4c45, 0x303e37, 0x9aacd6, ModSounds.FOREST_WIND, (b,ctx)->addDomainCalciteWoods(b,ctx,false));
        reg(context, FLINT_DOMAIN_COAST,       0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0x899ecf, 0x3f4d45, 0x313f37, 0xa2b2d9, null,
                (b,ctx)->{ addRiverFeatures(b,ctx); addDomainCalcite(b,ctx,false); });

        // Karstark Domain — colder, rougher
        reg(context, KARSTARK_DOMAIN_PLAINS,       0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x869ecd, 0x3c4a42, 0x2e3c34, 0x9eb1d7, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, KARSTARK_DOMAIN_TUNDRA,       0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x869ecd, 0x3c4a42, 0x2e3c34, 0x9eb1d7, null,                  (b,ctx)->addDomain(b,ctx,false,false));
        reg(context, KARSTARK_DOMAIN_WOODS,        0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x869ecd, 0x3c4a42, 0x2e3c34, 0x9eb1d7, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,false,false));
        reg(context, KARSTARK_DOMAIN_DEEP_PLAINS,  0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x7e98c9, 0x37443d, 0x2a3730, 0x96aad4, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, KARSTARK_DOMAIN_DEEP_TUNDRA,  0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x7e98c9, 0x37443d, 0x2a3730, 0x96aad4, null,                  (b,ctx)->addDomain(b,ctx,true,false));
        reg(context, KARSTARK_DOMAIN_DEEP_WOODS,   0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x7e98c9, 0x37443d, 0x2a3730, 0x96aad4, ModSounds.FOREST_WIND, (b,ctx)->addDomainWoods(b,ctx,true,false));
        reg(context, KARSTARK_DOMAIN_INLAND,       0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x7e98c9, 0x43534a, 0x34433a, 0x96aad4, null,                  (b,ctx)->addKarstarkInland(b,ctx));
        reg(context, KARSTARK_DOMAIN_COAST,        0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0x7e98c9, 0x4b5c4f, 0x3a4c3f, 0x96aad4, null,                  (b,ctx)->addKarstarkCoast(b,ctx));

        // Other Northern
        reg(context, NORTHERN_HILLS,    0.15f,0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x7a9385, 0x648272, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, NORTHERN_MOUNTAINS,0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x80988b, 0x668474, 0xc7d1e8, ModSounds.WINTER_WIND, (b,ctx)->{});
        reg(context, BARROWLANDS,         0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f54, 0x3e5146, 0xa7addb, null, (b,ctx)->addBarrowlands(b,ctx));
        reg(context, BARROWLANDS_2,       0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x506358, 0x415448, 0xa7addb, null, (b,ctx)->addBarrowlands(b,ctx));
        reg(context, BARROWLANDS_3,       0.4f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x48584e, 0x38493f, 0xa7addb, null, (b,ctx)->addBarrowlands(b,ctx));
        reg(context, BARROWLANDS_BARRENS, 0.5f, 0.7f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x48584e, 0x38493f, 0xa7addb, null, (b,ctx)->addBarrowlandsBarrens(b,ctx));
        reg(context, NORTHERN_PLAINS,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f55, 0x3d4f45, 0xa7addb, null,                  (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,true,false));
        reg(context, RILLS,              0.5f, 0.9f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d4c44, 0x303e36, 0xa7addb, null, (b,ctx)->addRillsVegetation(b,ctx,true));
        reg(context, RILLS_RIVER_VALLEY, 0.5f, 0.9f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x38453e, 0x2b3831, 0xa7addb, null, (b,ctx)->addRillsVegetation(b,ctx,true));
        reg(context, RILLS_COAST,        0.5f, 0.9f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x43534a, 0x34433b, 0xa7addb, null, (b,ctx)->addRillsVegetation(b,ctx,false));

        // wetlands — rills vegetation, flat boggy terrain
        reg(context, NECK_WETLANDS,     0.5f, 1.0f, 0x617b64, 0x232317, 0x4a7bb5, 0x4c5e52, 0x465b4d, 0x4a7cb5, null, (b,ctx)->addRillsVegetation(b,ctx,false));
        reg(context, NORTHERN_WETLANDS, 0.2f, 0.9f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x3d4c44, 0x303e36, 0xa7addb, null, (b,ctx)->addRillsVegetation(b,ctx,true));

        // lakes — same vegetation as wetlands, height variation creates water
        reg(context, NORTHERN_LAKE,     0.2f, 0.9f, 0x0e5cda, 0x0a4ab8, 0xa7addb, 0x3d4c44, 0x303e36, 0xa7addb, null, (b,ctx)->addRillsVegetation(b,ctx,true));
        reg(context, ARCTIC_LAKE,      -0.5f, 0.9f, 0x7da5e5, 0x6090d0, 0xb5badb, 0x73897b, 0x5a7062, 0xbabfdd, null, (b,ctx)->addRillsVegetation(b,ctx,false));

        // CTgen river biomes — water features only, no land vegetation
        reg(context, NECK_RIVER,     0.5f, 1.0f, 0x617b64, 0x232317, 0x4a7bb5, 0x4c5e52, 0x465b4d, 0x4a7cb5, null,
                (b,ctx)->{ f(b,ModplacedFeatures.SEAGRASS_KEY); f(b,ModplacedFeatures.KELP_KEY); });
        reg(context, NORTHERN_RIVER, 0.2f, 0.9f, 0x1971ff, 0x1560e0, 0xa7addb, 0x3d4c44, 0x303e36, 0xa7addb, null,
                (b,ctx)->{ f(b,ModplacedFeatures.SEAGRASS_KEY); f(b,ModplacedFeatures.KELP_KEY); });
        reg(context, ARCTIC_RIVER,  -0.5f, 0.8f, 0x7da5e5, 0x6090d0, 0xb5badb, 0x829687, 0x69826f, 0xd5d0e8, null,
                (b,ctx)->{ f(b,ModplacedFeatures.SEAGRASS_KEY); f(b,ModplacedFeatures.KELP_KEY); });

        reg(context, NORTHERN_WATERS,    0.5f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x637a6c, 0x5d7868, 0xa7addb, null, (b,ctx)->addRiverFeatures(b,ctx));
        reg(context, SHALLOW_OCEAN,      0.5f, 0.8f, 0x060060, 0x040040, 0x828fcc, 0x516459, 0x405247, 0x4a54b5, null,
                (b,ctx)->{ f(b,ModplacedFeatures.SEAGRASS_KEY); f(b,ModplacedFeatures.KELP_KEY); });
        reg(context, COLD_SHALLOW_OCEAN, 0.0f, 0.8f, 0x120e4f, 0x0c0a3a, 0x8490ca, 0x50625a, 0x40524a, 0x4d56b2, null,
                (b,ctx)->{ f(b,ModplacedFeatures.SEAGRASS_KEY); f(b,ModplacedFeatures.KELP_KEY); });
        reg(context, DEEP_OCEAN_NORTH,  -0.5f, 0.8f, 0x040040, 0x020020, 0x8893c5, 0x414d47, 0x313d37, 0x535bac, null,
                (b,ctx)->{ f(b,ModplacedFeatures.SEAGRASS_KEY); f(b,ModplacedFeatures.KELP_KEY); });
        reg(context, STONY_SHORES,       0.3f, 0.7f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x495a50, 0x394940, 0xa7addb, null, (b,ctx)->addStonyShoresVegetation(b));
        reg(context, STONY_SHORES_2,     0.3f, 0.7f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4d5f55, 0x3c4e43, 0xa7addb, null, (b,ctx)->addStonyShoresVegetation(b));
        reg(context, STONY_SHORES_COAST, 0.3f, 0.6f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x55695a, 0x435748, 0xa7addb, null, (b,ctx)->addStonyShoresVegetation(b));

        // Northern Clans — cold conifer highlands
        reg(context, NORTHERN_CLANS_HIGHLANDS, 0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x51645a, 0x405248, 0xa7addb, null,                  (b,ctx)->addConiferLand(b,ctx,false,true,false));
        reg(context, NORTHERN_CLANS_UPPER,     0.1f, 0.7f, 0x3d4ed1, 0x0c113b, 0xa8aeda, 0x4a5a51, 0x384840, 0xa8aeda, ModSounds.WINTER_WIND, (b,ctx)->addConiferLand(b,ctx,false,false,true));
        reg(context, NORTHERN_CLANS_COAST,     0.2f, 0.7f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4e6056, 0x3c4e44, 0xa7addb, null,                  (b,ctx)->addConiferLand(b,ctx,true,false,false));

        // Cape Kraken — conifer coast, sparse forests
        reg(context, CAPE_KRAKEN_INLAND,      0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4b5d53, 0x3a4b42, 0xa7addb, ModSounds.FOREST_WIND, (b,ctx)->addConiferLand(b,ctx,false,true,false));
        reg(context, CAPE_KRAKEN_NORTH_COAST, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x4e6056, 0x3d4f45, 0xa7addb, null,                  (b,ctx)->addConiferLand(b,ctx,true,false,false));
        reg(context, CAPE_KRAKEN_COAST_MID,   0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x506258, 0x3e5046, 0xa7addb, null,                  (b,ctx)->addConiferLand(b,ctx,true,false,false));
        reg(context, CAPE_KRAKEN_COAST_SOUTH, 0.3f, 0.8f, 0x3d4ed1, 0x0c113b, 0xa7addb, 0x506359, 0x3e5147, 0xa7addb, null, (b,ctx)->addConiferLand(b,ctx,true,false,false));
        reg(context, THE_NECK,   0.5f, 1.0f, 0x294018, 0x1f1308, 0x4a7bb5, 0x2e3832, 0x242e28, 0x4a7cb5, null,
                (b,ctx)->{ BiomeDefaultFeatures.addMangroveSwampVegetation(b);
                    f(b,ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
                    f(b,ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
                    f(b,ModplacedFeatures.MUD_PATCH_PLACED_KEY);
                    f(b,ModplacedFeatures.SEAGRASS_KEY);
                    f(b,ModplacedFeatures.KELP_KEY);
                    f(b,ModplacedFeatures.REED_KEY); });
        reg(context, THE_NECK_2, 0.5f, 1.0f, 0x294018, 0x1f1308, 0x4a7cb5, 0x2e3832, 0x242e27, 0x4a7eb5, null,
                (b,ctx)->{ BiomeDefaultFeatures.addMangroveSwampVegetation(b);
                    f(b,ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
                    f(b,ModplacedFeatures.MUD_PATCH_PLACED_KEY);
                    f(b,ModplacedFeatures.SEAGRASS_KEY);
                    f(b,ModplacedFeatures.KELP_KEY);
                    f(b,ModplacedFeatures.REED_KEY); });

        // Umber Domain
        reg(context, UMBER_DOMAIN_TUNDRA,       0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x809acb, 0x415047, 0x324038, 0x9cb0d7, null,                  (b,ctx)->addUmber(b,ctx));
        reg(context, UMBER_DOMAIN_BARRENS,      0.2f, 0.7f, 0x3d4ed1, 0x0c113b, 0x809acb, 0x415047, 0x324038, 0x9cb0d7, null,                  (b,ctx)->addUmberBarrens(b,ctx));
        reg(context, UMBER_DOMAIN_WOODS,        0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x809acb, 0x415047, 0x324038, 0x9cb0d7, ModSounds.FOREST_WIND, (b,ctx)->addUmberWoods(b,ctx));
        reg(context, UMBER_DOMAIN_DEEP_TUNDRA,  0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7894c8, 0x46574e, 0x36473e, 0x94aad3, null,                  (b,ctx)->addUmber(b,ctx));
        reg(context, UMBER_DOMAIN_DEEP_BARRENS, 0.2f, 0.7f, 0x3d4ed1, 0x0c113b, 0x7894c8, 0x46574e, 0x36473e, 0x94aad3, null,                  (b,ctx)->addUmberBarrens(b,ctx));
        reg(context, UMBER_DOMAIN_DEEP_WOODS,   0.2f, 0.8f, 0x3d4ed1, 0x0c113b, 0x7894c8, 0x46574e, 0x36473e, 0x94aad3, ModSounds.FOREST_WIND, (b,ctx)->addUmberWoods(b,ctx));

        // Skagos
        reg(context, SKAGOS_TUNDRA,       -0.45f,0.85f,0x3d4ed1,0x0c113b,0x959ccc,0x73897b,0x5a7062,0xb9bfde, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,true,false,false,true,true,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_WOODS,        -0.25f,0.9f, 0x3d4ed1,0x0c113b,0x9198cd,0x5a6d61,0x43544a,0x939cce, ModSounds.FOREST_WIND, (b,ctx)->{ addSkagos(b,ctx,true,true,true,true,false,true,true); addSkagosBushes(b); });
        reg(context, SKAGOS_BARRENS,      -0.7f, 0.7f, 0x3d4ed1,0x0c113b,0x9da3cd,0x778c7c,0x607565,0xc7c1e0, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,false,false,false,true,true,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_HILLS,        -0.55f,0.8f, 0x3d4ed1,0x0c113b,0x989fcc,0x687b6f,0x55685c,0xb0b6d8, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,true,false,false,true,true,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_VALLEY,       -0.1f, 0.95f,0x3d4ed1,0x0c113b,0x9098ce,0x5b6e62,0x475a4f,0x97a0d1, ModSounds.FOREST_WIND, (b,ctx)->{ addSkagos(b,ctx,true,false,true,true,true,true,true); addSkagosBushes(b); });
        reg(context, SKAGOS_MOUNTAINS,    -0.8f, 0.75f,0x3d4ed1,0x0c113b,0xa1a7ce,0x839588,0x6a8170,0xd4d0e6, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,false,false,false,false,false,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_FROZEN_PEAKS, -1.0f, 0.8f, 0x3d4ed1,0x0c113b,0xb7bcd8,0x849588,0x6b8070,0xe9e7f2, ModSounds.WINTER_WIND, (b,ctx)->{ addSkagos(b,ctx,false,false,false,false,false,false,false); addSkagosRocks(b); addSkagosBushes(b); });
        reg(context, SKAGOS_RIVER,         0.2f, 0.8f, 0x315d7d,0x1b3850,0x6172be,0x506358,0x465a4e,0x7987c8, null,                  (b,ctx)->addRiverFeatures(b,ctx));
        reg(context, SKAGOS_BOG,          -0.45f,0.85f,0x3d4ed1,0x0c113b,0x959ccc,0x73897b,0x5a7062,0xb9bfde, ModSounds.WINTER_WIND,
                (b,ctx)->{ f(b,ModplacedFeatures.CLAY_PATCH_PLACED_KEY); f(b,ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.MUD_PATCH_PLACED_KEY);
                    f(b,ModplacedFeatures.REED_KEY);
                    addSkagos(b,ctx,true,false,false,true,true,true,false);
                    f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY);
                    addSkagosBushes(b); });

        // Night's Watch
        reg(context, THE_WALL,            -0.8f, 0.8f, 0x3d4ed1,0x0c113b,0xcacee3,0x83958b,0x6a8173,0xe9e7f2, ModSounds.WINTER_WIND, (b,ctx)->addWall(b,ctx));
        reg(context, THE_GIFT_TUNDRA,      0.1f, 0.8f, 0x3d4ed1,0x0c113b,0x8ca1cf,0x60766a,0x4e6459,0xa1b2d7, null,                  (b,ctx)->addGiftTundra(b,ctx));
        reg(context, THE_GIFT_BARRENS,     0.1f, 0.7f, 0x3d4ed1,0x0c113b,0x8ca1cf,0x60766a,0x4e6459,0xa1b2d7, null,                  (b,ctx)->addGiftBarrens(b,ctx));
        reg(context, THE_GIFT_WOODS,       0.1f, 0.8f, 0x3d4ed1,0x0c113b,0x8ca1cf,0x60766a,0x4e6459,0xa1b2d7, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
        reg(context, THE_NEW_GIFT_TUNDRA,  0.2f, 0.8f, 0x3d4ed1,0x0c113b,0x92a7d2,0x566b5f,0x455a4f,0xaab9dc, null,                  (b,ctx)->addGiftTundra(b,ctx));
        reg(context, THE_NEW_GIFT_BARRENS, 0.2f, 0.7f, 0x3d4ed1,0x0c113b,0x92a7d2,0x566b5f,0x455a4f,0xaab9dc, null,                  (b,ctx)->addGiftBarrens(b,ctx));
        reg(context, THE_NEW_GIFT_WOODS,   0.2f, 0.8f, 0x3d4ed1,0x0c113b,0x92a7d2,0x566b5f,0x455a4f,0xaab9dc, ModSounds.FOREST_WIND, (b,ctx)->addForest(b,ctx,true,false,false,false,false,false,false,true));
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
        f(b, ModplacedFeatures.REED_KEY);
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
        // ferns always before rocks and bushes
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
        if (bushes) { f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow")); }
    }

    // ── FROSTFANG ─────────────────────────────────────────────────────────────

    private static void addFrostfang(BiomeGenerationSettings.Builder b, boolean grass, boolean bushes, boolean tor) {
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
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
        }
        // ferns always before rocks regardless of grass flag
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
        if (tor)   f(b,ModplacedFeatures.TOR_KEY);
        if (bushes) { f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow")); }
    }

    // ── CONIFER LAND (Northern Clans + Cape Kraken) ───────────────────────────
    // rocks = stone piles on coast, sparse = scattered trees, dense = sparse forest

    private static void addConiferLand(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                       boolean rocks, boolean sparseForest, boolean barren) {
        if (sparseForest) {
            f(b,ModplacedFeatures.SENTINEL_RARE_KEY); f(b,ModplacedFeatures.FIR_RARE_KEY);
            f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        }
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        if (rocks) {
            f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
            f(b,ModplacedFeatures.STONE_PILE_KEY);
        }
        if (!barren) {
            f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
        }
    }

    // ── STONY SHORES ──────────────────────────────────────────────────────────

    private static void addStonyShoresVegetation(BiomeGenerationSettings.Builder b) {
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
        f(b,ModplacedFeatures.STONE_PILE_KEY); f(b,ModplacedFeatures.TOR_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // ── RILLS ─────────────────────────────────────────────────────────────────

    private static void addRillsVegetation(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx, boolean sparseForest) {
        // river bed features first
        f(b,ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
        f(b,ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
        f(b,ModplacedFeatures.MUD_PATCH_PLACED_KEY);
        f(b,ModplacedFeatures.SEAGRASS_KEY);
        f(b,ModplacedFeatures.KELP_KEY);
        f(b,ModplacedFeatures.REED_KEY);
        // sparse trees
        if (sparseForest) {
            f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
            f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
            f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
            f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
            f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        }
        // wetland ground cover — sedge and fern heavy
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // ── BARROWLANDS ───────────────────────────────────────────────────────────

    private static void addBarrowlands(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        // rare full northern mix — no forests, just scattered trees
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        // full flower and grass cover
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
    }

    private static void addBarrowlandsBarrens(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        // no flowers, no trees — just grass and stone piles
        f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY);
    }

    // ── NOBLE DOMAIN VEGETATION ───────────────────────────────────────────────
    // shared ground cover for Bolton, Karstark (no calcite)

    // ground cover without bushes — bushes must always come after rocks
    private static void addDomainGroundCover(BiomeGenerationSettings.Builder b) {
        f(b,ModplacedFeatures.FORGET_ME_NOT_KEY); f(b,ModplacedFeatures.FROSTFIRE_KEY);
        f(b,ModplacedFeatures.LIVERWORT_KEY); f(b,ModplacedFeatures.LUNGWORT_KEY);
        f(b,ModplacedFeatures.PENNYROYAL_KEY); f(b,ModplacedFeatures.RED_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.THISTLE_KEY); f(b,ModplacedFeatures.THORN_BUSH_KEY); f(b,ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
        f(b,ModplacedFeatures.SEDGE_KEY);
        f(b,ModplacedFeatures.GRASS_KEY); f(b,ModplacedFeatures.TALL_GRASS_KEY);
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
        // NO bushes here — always added after rocks by each caller
    }

    private static void addDomainBushes(BiomeGenerationSettings.Builder b) {
        f(b,ModplacedFeatures.VANILLA_BUSH_PLACED_KEYS.get("birch")); f(b,ModplacedFeatures.BUSH_PLACED_KEYS.get("willow"));
    }

    // plains/tundra — rare trees, rocks, then bushes last
    private static void addDomain(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                  boolean rocks, boolean calcite) {
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        addDomainGroundCover(b);
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
        addDomainBushes(b);
    }

    // woods — full trees, rocks, then bushes last
    private static void addDomainWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx,
                                       boolean rocks, boolean calcite) {
        f(b,ModplacedFeatures.ASH_KEY); f(b,ModplacedFeatures.CHESTNUT_KEY);
        f(b,ModplacedFeatures.IRONWOOD_KEY); f(b,ModplacedFeatures.SENTINEL_KEY);
        f(b,ModplacedFeatures.FIR_KEY); f(b,ModplacedFeatures.PINE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_KEY); f(b,ModplacedFeatures.HAWTHORN_KEY); f(b,ModplacedFeatures.BEECH_KEY);
        addDomainGroundCover(b);
        if (rocks) { f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY); f(b,ModplacedFeatures.STONE_PILE_KEY); }
        addDomainBushes(b);
    }

    // calcite variants — underground calcite, bushes still last
    private static void addDomainCalcite(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx, boolean rocks) {
        addDomain(b, ctx, rocks, false);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CALCITE_DEPOSIT_PLACED_KEY);
    }

    private static void addDomainCalciteWoods(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx, boolean rocks) {
        addDomainWoods(b, ctx, rocks, false);
        b.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CALCITE_DEPOSIT_PLACED_KEY);
    }

    // Karstark inland — tors and stone piles, no patches, bushes last
    private static void addKarstarkInland(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        addDomainGroundCover(b);
        f(b,ModplacedFeatures.STONE_PILE_KEY); f(b,ModplacedFeatures.TOR_KEY);
        addDomainBushes(b);
    }

    // Karstark coast — full patches, tors, bushes last
    private static void addKarstarkCoast(BiomeGenerationSettings.Builder b, BootstrapContext<Biome> ctx) {
        f(b,ModplacedFeatures.ASH_RARE_KEY); f(b,ModplacedFeatures.CHESTNUT_RARE_KEY);
        f(b,ModplacedFeatures.IRONWOOD_RARE_KEY); f(b,ModplacedFeatures.SENTINEL_RARE_KEY);
        f(b,ModplacedFeatures.FIR_RARE_KEY); f(b,ModplacedFeatures.PINE_RARE_KEY); f(b,ModplacedFeatures.SOLDIER_PINE_RARE_KEY);
        f(b,ModplacedFeatures.WEIRWOOD_RARE_KEY);
        f(b,ModplacedFeatures.OAK2_RARE_KEY); f(b,ModplacedFeatures.HAWTHORN_RARE_KEY); f(b,ModplacedFeatures.BEECH_RARE_KEY);
        addDomainGroundCover(b);
        f(b,ModplacedFeatures.STONE_PATCH_PLACED_KEY); f(b,ModplacedFeatures.GRAVEL_PATCH_PLACED_KEY);
        f(b,ModplacedFeatures.STONE_PILE_KEY); f(b,ModplacedFeatures.TOR_KEY);
        addDomainBushes(b);
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
        // sedge, grass, ferns always emitted — ordering must be consistent across all biomes
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
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
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
        f(b,ModplacedFeatures.FERN_KEY); f(b,ModplacedFeatures.LARGE_FERN_KEY);
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