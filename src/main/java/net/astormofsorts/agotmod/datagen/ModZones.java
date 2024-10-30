package net.astormofsorts.agotmod.datagen;

import dev.tocraft.ctgen.xtend.CTRegistries;
import dev.tocraft.ctgen.zone.Zone;
import dev.tocraft.ctgen.zone.ZoneBuilder;
import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;

public class ModZones {
    public static final ResourceKey<Zone> MANGOVES = getKey("mangroves");
    public static final ResourceKey<Zone> TAIGA = getKey("taiga");
    public static final ResourceKey<Zone> SNOWY_PLAINS = getKey("snowy_plains");
    public static final ResourceKey<Zone> PLAINS = getKey("plains");
    public static final ResourceKey<Zone> WINDSWEPT_HILLS = getKey("windswept_hills");
    public static final ResourceKey<Zone> SNOWY_MOUNTAINS = getKey("snowy_mountains");
    public static final ResourceKey<Zone> SWAMP = getKey("swamp");
    public static final ResourceKey<Zone> SAVANNA_PLATEAU = getKey("savanna_plateu");
    public static final ResourceKey<Zone> SAVANNA = getKey("savanna");
    public static final ResourceKey<Zone> FOREST = getKey("forest");
    public static final ResourceKey<Zone> MEADOW = getKey("meadow");
    public static final ResourceKey<Zone> THE_WALL = getKey("the_wall");
    public static final ResourceKey<Zone> SNOWY_TAIGA = getKey("snowy_taiga");
    public static final ResourceKey<Zone> SNOWY_FLATS = getKey("snowy_flats");
    public static final ResourceKey<Zone> SUNFLOWER_PLAINS = getKey("sunflower_plains");
    public static final ResourceKey<Zone> RIVER = getKey("river");
    public static final ResourceKey<Zone> FROZEN_RIVER = getKey("frozen_river");
    public static final ResourceKey<Zone> LAKE = getKey("lake");
    public static final ResourceKey<Zone> FROZEN_LAKE = getKey("frozen_lake");
    public static final ResourceKey<Zone> COLD_OCEAN = getKey("cold_ocean");
    public static final ResourceKey<Zone> DEEP_COLD_OCEAN = getKey("deep_cold_ocean");
    public static final ResourceKey<Zone> BASALT_DELTAS = getKey("basalt_deltas");
    public static final ResourceKey<Zone> SNOWY_SLOPES = getKey("snowy_slopes");
    public static final ResourceKey<Zone> FROZEN_MOUNTAINS = getKey("frozen_mountains");
    public static final ResourceKey<Zone> OLD_GROWTH_SPRUCE_TAIGA = getKey("old_growth_spruce_taiga");

    public static void bootstrap(BootstapContext<Zone> context) {
        // The North
        context.register(MANGOVES, new ZoneBuilder().setBiome(getBiome(context, Biomes.MANGROVE_SWAMP)).setColor(new Color(34, 51, 34)).setHeight(-3).setTerrainModifier(4).build());
        context.register(TAIGA, new ZoneBuilder().setBiome(getBiome(context, Biomes.TAIGA)).setColor(new Color(43, 70, 43)).setHeight(3).setTerrainModifier(6).build());
        context.register(SNOWY_PLAINS, new ZoneBuilder().setBiome(getBiome(context, Biomes.SNOWY_PLAINS)).setColor(new Color(57, 95, 57)).setHeight(3).setTerrainModifier(4).build());
        context.register(PLAINS, new ZoneBuilder().setBiome(getBiome(context, Biomes.PLAINS)).setColor(new Color(57, 95, 57)).setHeight(3).setTerrainModifier(4).build());
        context.register(WINDSWEPT_HILLS, new ZoneBuilder().setBiome(getBiome(context, Biomes.WINDSWEPT_HILLS)).setColor(new Color(151, 151, 151)).setHeight(33).setTerrainModifier(20).setPixelWeight(1.5).build());
        context.register(SNOWY_MOUNTAINS, new ZoneBuilder().setBiome(getBiome(context, Biomes.JAGGED_PEAKS)).setColor(new Color(130, 130, 130)).setHeight(60).setTerrainModifier(28).build());
        context.register(SWAMP, new ZoneBuilder().setBiome(getBiome(context, Biomes.SWAMP)).setColor(new Color(76, 95, 49)).setHeight(-3).setTerrainModifier(4).build());
        context.register(SAVANNA_PLATEAU, new ZoneBuilder().setBiome(getBiome(context, Biomes.SAVANNA_PLATEAU)).setColor(new Color(85, 109, 51)).setHeight(3).setTerrainModifier(4).build());
        context.register(SAVANNA, new ZoneBuilder().setBiome(getBiome(context, Biomes.SAVANNA)).setColor(new Color(98, 123, 64)).setHeight(3).setTerrainModifier(0.1).build());
        context.register(FOREST, new ZoneBuilder().setBiome(getBiome(context, Biomes.FOREST)).setColor(new Color(61, 81, 51)).setHeight(3).setTerrainModifier(7).build());
        context.register(MEADOW, new ZoneBuilder().setBiome(getBiome(context, Biomes.MEADOW)).setColor(new Color(72, 122, 72)).setHeight(3).build());
        context.register(OLD_GROWTH_SPRUCE_TAIGA, new ZoneBuilder().setBiome(getBiome(context, Biomes.OLD_GROWTH_SPRUCE_TAIGA)).setColor(new Color(38, 59, 38)).setHeight(3).setTerrainModifier(6).build());
        // wall
        context.register(THE_WALL, new ZoneBuilder().setBiome(getBiome(context, Biomes.FROZEN_PEAKS)).setColor(new Color(255, 255, 255)).setStoneBlock(Blocks.PACKED_ICE).setDirtBlock(Blocks.PACKED_ICE).setSurfaceBlock(Blocks.SNOW_BLOCK).setHeight(213).setTerrainModifier(0).setPixelWeight(16).build());
        //North Of The Wall
        context.register(SNOWY_TAIGA, new ZoneBuilder().setBiome(getBiome(context, Biomes.SNOWY_TAIGA)).setColor(new Color(130, 140, 130)).setHeight(5).build());
        context.register(SNOWY_FLATS, new ZoneBuilder().setBiome(getBiome(context, Biomes.SNOWY_SLOPES)).setColor(new Color(217, 217, 217)).setHeight(5).build());
        context.register(SUNFLOWER_PLAINS, new ZoneBuilder().setBiome(getBiome(context, Biomes.SUNFLOWER_PLAINS)).setColor(new Color(107, 143, 107)).setHeight(5).build());
        context.register(SNOWY_SLOPES, new ZoneBuilder().setBiome(getBiome(context, Biomes.SNOWY_SLOPES)).setColor(new Color(192, 192, 192)).setStoneBlock(Blocks.STONE).setDirtBlock(Blocks.SNOW_BLOCK).setSurfaceBlock(Blocks.SNOW).setHeight(33).setTerrainModifier(20).setPixelWeight(1.5).build());
        context.register(FROZEN_MOUNTAINS, new ZoneBuilder().setBiome(getBiome(context, Biomes.JAGGED_PEAKS)).setColor(new Color(174, 174, 174)).setStoneBlock(Blocks.STONE).setDirtBlock(Blocks.POWDER_SNOW).setSurfaceBlock(Blocks.POWDER_SNOW).setHeight(60).setTerrainModifier(28).build());
        //Essos
        context.register(BASALT_DELTAS, new ZoneBuilder().setBiome(getBiome(context, Biomes.BASALT_DELTAS)).setColor(new Color(0, 0, 0)).setSurfaceBlock(Blocks.BASALT).setHeight(5).build());
        // water biomes
        context.register(RIVER, new ZoneBuilder().setBiome(getBiome(context, Biomes.RIVER)).setColor(new Color(1, 98, 255)).setDirtBlock(Blocks.SAND).setSurfaceBlock(Blocks.SAND).setHeight(-15).setPixelWeight(2).setThresholdModifier(26).build());
        context.register(FROZEN_RIVER, new ZoneBuilder().setBiome(getBiome(context, Biomes.FROZEN_RIVER)).setColor(new Color(87, 145, 240)).setDirtBlock(Blocks.SAND).setSurfaceBlock(Blocks.SAND).setHeight(-15).setPixelWeight(2).setThresholdModifier(26).build());
        context.register(LAKE, new ZoneBuilder().setBiome(getBiome(context, Biomes.LUKEWARM_OCEAN)).setColor(new Color(0, 83, 217)).setSurfaceBlock(Blocks.SAND).setHeight(-20).setPixelWeight(3).setThresholdModifier(26).build()); // lake
        context.register(FROZEN_LAKE, new ZoneBuilder().setBiome(getBiome(context, Biomes.FROZEN_OCEAN)).setColor(new Color(78, 126, 204)).setSurfaceBlock(Blocks.SAND).setHeight(-20).setPixelWeight(3).setThresholdModifier(26).build()); // Frozen Lake
        context.register(COLD_OCEAN, new ZoneBuilder().setBiome(getBiome(context, Biomes.COLD_OCEAN)).setColor(new Color(0, 42, 103)).setSurfaceBlock(Blocks.SAND).setHeight(-35).setTerrainModifier(16).setThresholdModifier(26).build());
        context.register(DEEP_COLD_OCEAN, new ZoneBuilder().setBiome(getBiome(context, Biomes.DEEP_COLD_OCEAN)).setColor(new Color(0, 35, 85)).setHeight(-60).setTerrainModifier(33).setThresholdModifier(26).build());
    }

    private static Holder<Biome> getBiome(BootstapContext<?> context, ResourceKey<Biome> biome) {
        return context.lookup(Registries.BIOME).getOrThrow(biome);
    }

    private static ResourceKey<Zone> getKey(String name) {
        return ResourceKey.create(CTRegistries.ZONES_KEY, new ResourceLocation(AGoTMod.MOD_ID, name));
    }
}
