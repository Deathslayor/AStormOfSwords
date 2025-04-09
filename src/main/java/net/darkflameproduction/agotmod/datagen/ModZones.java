package net.darkflameproduction.agotmod.datagen;

import dev.tocraft.ctgen.xtend.CTRegistries;
import dev.tocraft.ctgen.zone.Zone;
import dev.tocraft.ctgen.zone.ZoneBuilder;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.worldgen.biome.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;

public class ModZones {
    public static final ResourceKey<Zone> RILLS = getKey("rills");
    public static final ResourceKey<Zone> THE_WALL = getKey("the_wall");
    public static final ResourceKey<Zone> FROZEN_RIVER = getKey("frozen_river");
    public static final ResourceKey<Zone> FROZEN_LAKE = getKey("frozen_lake");
    public static final ResourceKey<Zone> COLD_OCEAN = getKey("cold_ocean");
    public static final ResourceKey<Zone> DEEP_COLD_OCEAN = getKey("deep_cold_ocean");
    public static final ResourceKey<Zone> BASALT_DELTAS = getKey("basalt_deltas");
    public static final ResourceKey<Zone> HAUNTED_FOREST = getKey("haunted_forest");
    public static final ResourceKey<Zone> VALLEY_OF_THENN = getKey("valley_of_thenn");
    public static final ResourceKey<Zone> FROSTFANG_FOOTHILLS = getKey("frostfang_foothills");
    public static final ResourceKey<Zone> FROSTFANGS = getKey("frostfangs");
    public static final ResourceKey<Zone> LANDS_OF_ALWAYS_WINTER = getKey("lands_of_always_winter");
    public static final ResourceKey<Zone> WOLFSWOOD = getKey("wolfswood");
    public static final ResourceKey<Zone> IRONWOOD = getKey("ironwood");
    public static final ResourceKey<Zone> WOLFSWOOD_CLEARING = getKey("wolfswood_clearing");
    public static final ResourceKey<Zone> NORTHERN_HILLS = getKey("northern_hills");
    public static final ResourceKey<Zone> NORTHERN_MOUNTAINS = getKey("northern_mountains");
    public static final ResourceKey<Zone> BARROWLANDS = getKey("barrowlands");
    public static final ResourceKey<Zone> NORTHERN_RIVER = getKey("river");
    public static final ResourceKey<Zone> NORTHERN_LAKE = getKey("lake");
    public static final ResourceKey<Zone> THE_NORTH = getKey("the_north");
    public static final ResourceKey<Zone> STONY_SHORES = getKey("stony_shores");
    public static final ResourceKey<Zone> THE_NECK = getKey("the_neck");
    static final ResourceKey<Zone> THE_NECK_RIVER = getKey("the_neck_river");

    public static void bootstrap(BootstrapContext<Zone> context) {
        // The North
        context.register(THE_NECK, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.THE_NECK)).setColor(new Color(34, 51, 34)).setHeight(-2).setTerrainModifier(8).build());
        context.register(THE_NECK_RIVER, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.THE_NECK)).setColor(new Color(87, 76, 47)).setDirtBlock(Blocks.DIRT).setSurfaceBlock(Blocks.DIRT).setHeight(-15).setPixelWeight(40).setCarverModifier(26).build());
        context.register(STONY_SHORES, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.STONY_SHORES)).setColor(new Color(80, 98, 80)).setStoneBlock(Blocks.STONE).setDirtBlock(Blocks.STONE).setSurfaceBlock(Blocks.STONE).setHeight(3).setTerrainModifier(4).setTerrainModifier(6).build());
        context.register(WOLFSWOOD_CLEARING, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.WOLFSWOOD_CLEARING)).setColor(new Color(72, 122, 72)).setHeight(3).setTerrainModifier(6).build());
        context.register(IRONWOOD, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.IRONWOOD)).setColor(new Color(38, 59, 38)).setHeight(3).setTerrainModifier(6).build());
        context.register(WOLFSWOOD, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.WOLFSWOOD)).setColor(new Color(43, 70, 43)).setHeight(3).setTerrainModifier(6).build());
        context.register(NORTHERN_HILLS, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.NORTHERN_HILLS)).setColor(new Color(151, 151, 151)).setHeight(30).setTerrainModifier(40).setPixelWeight(1.5).build());
        context.register(NORTHERN_MOUNTAINS, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.NORTHERN_MOUNTAINS)).setColor(new Color(130, 130, 130)).setStoneBlock(Blocks.STONE).setDirtBlock(Blocks.CALCITE).setSurfaceBlock(Blocks.SNOW).setHeight(100).setTerrainModifier(80).build());
        context.register(BARROWLANDS, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.BARROWLANDS)).setColor(new Color(98, 123, 64)).setHeight(15).setTerrainModifier(20).build());
        context.register(THE_NORTH, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.THE_NORTH)).setColor(new Color(57, 95, 57)).setHeight(8).setTerrainModifier(10).build());
        context.register(RILLS, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.RILLS)).setColor(new Color(61, 81, 51)).setHeight(1).setTerrainModifier(12).build());
        context.register(NORTHERN_LAKE, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.NORTHERN_WATERS)).setColor(new Color(0, 83, 217)).setSurfaceBlock(Blocks.SAND).setHeight(-20).setPixelWeight(3).setCarverModifier(26).build());
        context.register(NORTHERN_RIVER, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.NORTHERN_WATERS)).setColor(new Color(1, 98, 255)).setDirtBlock(Blocks.SAND).setSurfaceBlock(Blocks.SAND).setHeight(-15).setPixelWeight(40).setCarverModifier(26).build());
        // wall
        context.register(THE_WALL, new ZoneBuilder().setBiome(getBiome(context, Biomes.FROZEN_PEAKS)).setColor(new Color(255, 255, 255)).setStoneBlock(Blocks.PACKED_ICE).setDirtBlock(Blocks.PACKED_ICE).setSurfaceBlock(Blocks.SNOW_BLOCK).setHeight(213).setTerrainModifier(0).setPixelWeight(100).build());
        //North Of The Wall
        context.register(HAUNTED_FOREST, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.HAUNTED_FOREST)).setColor(new Color(130, 140, 130)).setHeight(5).build());
        context.register(LANDS_OF_ALWAYS_WINTER, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.LANDS_OF_ALWAYS_WINTER)).setColor(new Color(217, 217, 217)).setStoneBlock(Blocks.STONE).setDirtBlock(Blocks.SNOW_BLOCK).setSurfaceBlock(Blocks.SNOW).setHeight(3).setTerrainModifier(6).setPixelWeight(1.5).build());
        context.register(FROSTFANG_FOOTHILLS, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.FROSTFANG_FOOTHILLS)).setColor(new Color(192, 192, 192)).setStoneBlock(Blocks.STONE).setDirtBlock(Blocks.STONE).setSurfaceBlock(Blocks.SNOW).setHeight(50).setTerrainModifier(20).setPixelWeight(1.5).build());
        context.register(FROSTFANGS, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.FROSTFANGS)).setColor(new Color(174, 174, 174)).setStoneBlock(Blocks.STONE).setDirtBlock(Blocks.POWDER_SNOW).setSurfaceBlock(Blocks.SNOW).setHeight(150).setTerrainModifier(80).build());
        context.register(VALLEY_OF_THENN, new ZoneBuilder().setBiome(getBiome(context, ModBiomes.VALLEY_OF_THENN)).setColor(new Color(107, 143, 107)).setHeight(5).build());
        //Essos
        context.register(BASALT_DELTAS, new ZoneBuilder().setBiome(getBiome(context, Biomes.BASALT_DELTAS)).setColor(new Color(0, 0, 0)).setStoneBlock(Blocks.BASALT).setDirtBlock(Blocks.BASALT).setSurfaceBlock(Blocks.BASALT).setHeight(33).setTerrainModifier(20).setPixelWeight(1.5).build());
        // water biomes
        context.register(FROZEN_RIVER, new ZoneBuilder().setBiome(getBiome(context, Biomes.FROZEN_RIVER)).setColor(new Color(87, 145, 240)).setDirtBlock(Blocks.SAND).setSurfaceBlock(Blocks.SAND).setHeight(-15).setPixelWeight(40).setCarverModifier(26).build());
        context.register(FROZEN_LAKE, new ZoneBuilder().setBiome(getBiome(context, Biomes.FROZEN_OCEAN)).setColor(new Color(78, 126, 204)).setSurfaceBlock(Blocks.SAND).setHeight(-20).setPixelWeight(3).setCarverModifier(26).build()); // Frozen Lake
        context.register(COLD_OCEAN, new ZoneBuilder().setBiome(getBiome(context, Biomes.COLD_OCEAN)).setColor(new Color(0, 42, 103)).setSurfaceBlock(Blocks.SAND).setHeight(-35).setTerrainModifier(16).setCarverModifier(26).build());
        context.register(DEEP_COLD_OCEAN, new ZoneBuilder().setBiome(getBiome(context, Biomes.DEEP_COLD_OCEAN)).setColor(new Color(0, 35, 85)).setHeight(-60).setTerrainModifier(33).setCarverModifier(26).build());
    }

    private static Holder<Biome> getBiome(BootstrapContext<?> context, ResourceKey<Biome> biome) {
        return context.lookup(Registries.BIOME).getOrThrow(biome);
    }

    private static ResourceKey<Zone> getKey(String name) {
        return ResourceKey.create(CTRegistries.ZONES_KEY, AGoTMod.id(name));
    }
}
