package net.astormofsorts.agotmod.datagen;

import dev.tocraft.crafted.ctgen.CTerrainGeneration;
import dev.tocraft.crafted.ctgen.biome.MapBiome;
import dev.tocraft.crafted.ctgen.biome.MapBiomeBuilder;
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
import java.util.Optional;

public class ModMapBiomes {
    public static final ResourceKey<MapBiome> MANGOVES = getKey("mangroves");
    public static final ResourceKey<MapBiome> TAIGA = getKey("taiga");
    public static final ResourceKey<MapBiome> SNOWY_PLAINS = getKey("snowy_plains");
    public static final ResourceKey<MapBiome> PLAINS = getKey("plains");
    public static final ResourceKey<MapBiome> WINDSWEPT_HILLS = getKey("windswept_hills");
    public static final ResourceKey<MapBiome> SNOWY_MOUNTAINS = getKey("snowy_mountains");
    public static final ResourceKey<MapBiome> SWAMP = getKey("swamp");
    public static final ResourceKey<MapBiome> SAVANNA_PLATEAU = getKey("savanna_plateu");
    public static final ResourceKey<MapBiome> SAVANNA = getKey("savanna");
    public static final ResourceKey<MapBiome> FOREST = getKey("forest");
    public static final ResourceKey<MapBiome> MEADOW = getKey("meadow");
    public static final ResourceKey<MapBiome> THE_WALL = getKey("the_wall");
    public static final ResourceKey<MapBiome> SNOWY_TAIGA = getKey("snowy_taiga");
    public static final ResourceKey<MapBiome> SNOWY_SLOPES = getKey("snowy_slopes");
    public static final ResourceKey<MapBiome> SUNFLOWER_PLAINS = getKey("sunflower_plains");
    public static final ResourceKey<MapBiome> RIVER = getKey("river");
    public static final ResourceKey<MapBiome> FROZEN_RIVER = getKey("frozen_river");
    public static final ResourceKey<MapBiome> LAKE = getKey("lake");
    public static final ResourceKey<MapBiome> FROZEN_LAKE = getKey("frozen_lake");
    public static final ResourceKey<MapBiome> COLD_OCEAN = getKey("cold_ocean");
    public static final ResourceKey<MapBiome> DEEP_COLD_OCEAN = getKey("deep_cold_ocean");

    public static void bootstrap(BootstapContext<MapBiome> context) {
        // The North
        context.register(MANGOVES, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.MANGROVE_SWAMP)).setColor(new Color(34, 51, 34)).setHeight(-3).setPerlinMultiplier(4).build());
        context.register(TAIGA, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.TAIGA)).setColor(new Color(43, 70, 43)).setHeight(3).setPerlinMultiplier(6).build());
        context.register(SNOWY_PLAINS, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.SNOWY_PLAINS)).setColor(new Color(57, 95, 57)).setHeight(3).setPerlinMultiplier(4).build());
        context.register(PLAINS, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.PLAINS)).setColor(new Color(57, 95, 57)).setHeight(3).setPerlinMultiplier(4).build());
        context.register(WINDSWEPT_HILLS, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.WINDSWEPT_HILLS)).setColor(new Color(151, 151, 151)).setHeight(33).setPerlinMultiplier(20).setPixelWeight(1.5).build());
        context.register(SNOWY_MOUNTAINS, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.JAGGED_PEAKS)).setColor(new Color(130, 130, 130)).setHeight(60).setPerlinMultiplier(28).setCaveThreshold(0.7).build());
        context.register(SWAMP, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.SWAMP)).setColor(new Color(76, 95, 49)).setHeight(-3).setPerlinMultiplier(4).build());
        context.register(SAVANNA_PLATEAU, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.SAVANNA_PLATEAU)).setColor(new Color(85, 109, 51)).setHeight(3).setPerlinMultiplier(4).build());
        context.register(SAVANNA, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.SAVANNA)).setColor(new Color(98, 123, 64)).setHeight(3).setPerlinMultiplier(0.1).build());
        context.register(FOREST, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.FOREST)).setColor(new Color(61, 81, 51)).setHeight(3).setPerlinMultiplier(7).build());
        context.register(MEADOW, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.MEADOW)).setColor(new Color(72, 122, 72)).setHeight(3).build());
        // wall
        context.register(THE_WALL, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.FROZEN_PEAKS)).setColor(new Color(255, 255, 255)).setStoneBlock(Blocks.PACKED_ICE).setDirtBlock(Blocks.PACKED_ICE).setSurfaceBlock(Blocks.SNOW_BLOCK).setHeight(213).setPerlinMultiplier(0).setPixelWeight(16).build());
        //North Of The Wall
        context.register(SNOWY_TAIGA, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.SNOWY_TAIGA)).setColor(new Color(130, 140, 130)).setHeight(5).build());
        context.register(SNOWY_SLOPES, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.SNOWY_SLOPES)).setColor(new Color(217, 217, 217)).setHeight(5).build());
        context.register(SUNFLOWER_PLAINS, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.SUNFLOWER_PLAINS)).setColor(new Color(107, 143, 107)).setHeight(5).build());

        // water biomes
        context.register(RIVER, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.RIVER)).setColor(new Color(1, 98, 255)).setDirtBlock(Blocks.SAND).setSurfaceBlock(Blocks.SAND).setHeight(-15).setPixelWeight(2).build());
        context.register(FROZEN_RIVER, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.FROZEN_RIVER)).setColor(new Color(87, 145, 240)).setDirtBlock(Blocks.SAND).setSurfaceBlock(Blocks.SAND).setHeight(-15).setPixelWeight(2).build());
        context.register(LAKE, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.LUKEWARM_OCEAN)).setColor(new Color(0, 83, 217)).setSurfaceBlock(Blocks.SAND).setHeight(-20).setPixelWeight(3).build()); // lake
        context.register(FROZEN_LAKE, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.FROZEN_OCEAN)).setColor(new Color(78, 126, 204)).setSurfaceBlock(Blocks.SAND).setHeight(-20).setPixelWeight(3).build()); // Frozen Lake
        context.register(COLD_OCEAN, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.COLD_OCEAN)).setColor(new Color(0, 42, 103)).setSurfaceBlock(Blocks.SAND).setHeight(-35).setPerlinMultiplier(16).build());
        context.register(DEEP_COLD_OCEAN, new MapBiomeBuilder().setBiome(getBiome(context, Biomes.DEEP_COLD_OCEAN)).setColor(new Color(0, 35, 85)).setHeight(-60).setPerlinMultiplier(33).build());
    }

    private static Holder<Biome> getBiome(BootstapContext<?> context, ResourceKey<Biome> biome) {
        return context.lookup(Registries.BIOME).getOrThrow(biome);
    }

    private static ResourceKey<MapBiome> getKey(String name) {
        return ResourceKey.create(CTerrainGeneration.MAP_BIOME_REGISTRY, new ResourceLocation(AGoTMod.MOD_ID, name));
    }
}
