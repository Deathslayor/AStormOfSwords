package net.astormofsorts.agotmod.map;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;

public record MapBiome(ResourceKey<Biome> biome, int color, Block deepslateBlock, Block stoneBlock, Block dirtBlock, Block grassBlock,
                       int height, double perlinMultiplier, double biomeWeight) {

    public static final List<MapBiome> BIOME_LIST = new ArrayList<>();

    private static MapBiome DEEP_COLD_OCEAN;

    public static void initialize() {
        // The North
        register(new MapBiomeBuilder().setBiome(Biomes.MANGROVE_SWAMP).setColor(new Color(34, 51, 34)).setHeight(-3).setPerlinMultiplier(4).build());
        register(new MapBiomeBuilder().setBiome(Biomes.TAIGA).setColor(new Color(43, 70, 43)).setHeight(3).setPerlinMultiplier(6).build());
        register(new MapBiomeBuilder().setBiome(Biomes.SNOWY_PLAINS).setColor(new Color(57, 95, 57)).setHeight(3).setPerlinMultiplier(4).build());
        register(new MapBiomeBuilder().setBiome(Biomes.PLAINS).setColor(new Color(57, 95, 57)).setHeight(3).setPerlinMultiplier(4).build());
        register(new MapBiomeBuilder().setBiome(Biomes.WINDSWEPT_HILLS).setColor(new Color(151, 151, 151)).setHeight(24).setPerlinMultiplier(20).setBiomeWeight(1.5).build());
        register(new MapBiomeBuilder().setBiome(Biomes.JAGGED_PEAKS).setColor(new Color(130, 130, 130)).setHeight(60).setPerlinMultiplier(28).build());
        register(new MapBiomeBuilder().setBiome(Biomes.SWAMP).setColor(new Color(76, 95, 49)).setHeight(-3).setPerlinMultiplier(4).build());
        register(new MapBiomeBuilder().setBiome(Biomes.SAVANNA_PLATEAU).setColor(new Color(85, 109, 51)).setHeight(3).setPerlinMultiplier(4).build());
        register(new MapBiomeBuilder().setBiome(Biomes.SAVANNA).setColor(new Color(98, 123, 64)).setHeight(3).setPerlinMultiplier(0.1).build());
        register(new MapBiomeBuilder().setBiome(Biomes.FOREST).setColor(new Color(61, 81, 51)).setHeight(3).setPerlinMultiplier(7).build());
        register(new MapBiomeBuilder().setBiome(Biomes.MEADOW).setColor(new Color(72, 122, 72)).setHeight(3).setPerlinMultiplier(8).build());
        // wall
        register(new MapBiomeBuilder().setBiome(Biomes.FROZEN_PEAKS).setColor(new Color(255, 255, 255)).setStoneBlock(Blocks.PACKED_ICE).setDirtBlock(Blocks.PACKED_ICE).setGrassBlock(Blocks.SNOW_BLOCK).setHeight(279).setPerlinMultiplier(0).setBiomeWeight(16).build());
        //North Of The Wall
        register(new MapBiomeBuilder().setBiome(Biomes.SNOWY_TAIGA).setColor(new Color(130, 140, 130)).setHeight(5).setPerlinMultiplier(8).build());
        register(new MapBiomeBuilder().setBiome(Biomes.SNOWY_SLOPES).setColor(new Color(217, 217, 217)).setHeight(5).setPerlinMultiplier(8).build());
        register(new MapBiomeBuilder().setBiome(Biomes.SUNFLOWER_PLAINS).setColor(new Color(107, 143, 107)).setHeight(5).setPerlinMultiplier(8).build());

        // water biomes
        register(new MapBiomeBuilder().setBiome(Biomes.RIVER).setColor(new Color(1, 98, 255)).setDirtBlock(Blocks.SAND).setGrassBlock(Blocks.SAND).setHeight(-15).setPerlinMultiplier(8).setBiomeWeight(2).build());
        register(new MapBiomeBuilder().setBiome(Biomes.FROZEN_RIVER).setColor(new Color(87, 145, 240)).setDirtBlock(Blocks.SAND).setGrassBlock(Blocks.SAND).setHeight(-15).setPerlinMultiplier(8).setBiomeWeight(2).build());
        register(new MapBiomeBuilder().setBiome(Biomes.LUKEWARM_OCEAN).setColor(new Color(0, 83, 217)).setGrassBlock(Blocks.SAND).setHeight(-20).setPerlinMultiplier(8).setBiomeWeight(2).build()); // lake
        register(new MapBiomeBuilder().setBiome(Biomes.FROZEN_OCEAN).setColor(new Color(78, 126, 204)).setGrassBlock(Blocks.SAND).setHeight(-20).setPerlinMultiplier(8).setBiomeWeight(2).build()); // Frozen Lake
        register(new MapBiomeBuilder().setBiome(Biomes.COLD_OCEAN).setColor(new Color(0, 42, 103)).setGrassBlock(Blocks.SAND).setHeight(-35).setPerlinMultiplier(16).build());
        DEEP_COLD_OCEAN = register(new MapBiomeBuilder().setBiome(Biomes.DEEP_COLD_OCEAN).setColor(new Color(0, 35, 85)).setHeight(-60).setPerlinMultiplier(33).build());
    }

    public static MapBiome register(MapBiomeBuilder biomeBuilder) {
        return register(biomeBuilder.build());
    }

    public static MapBiome register(MapBiome biome) {
        BIOME_LIST.add(biome);
        return biome;
    }

    @NotNull
    public static MapBiome getByColor(int color) {
        return BIOME_LIST.stream().filter(b -> b.color == color).findAny().orElse(MapBiome.getDefault());
    }

    public static Map<Integer, Integer> toHeightMap(List<MapBiome> dataList) {
        Map<Integer, Integer> map = new HashMap<>();
        for (MapBiome data : dataList) {
            map.put(data.color(), data.height());
        }
        return map;
    }

    public static @NotNull MapBiome getDefault() {
        return DEEP_COLD_OCEAN;
    }

    @Override
    public String toString() {
        return "MapBiome[" +
                "biome=" + biome + ", " +
                "color=" + color + ", " +
                "stoneBlock=" + stoneBlock + ", " +
                "dirtBlock=" + dirtBlock + ", " +
                "grassBlock=" + grassBlock + ", " +
                "height=" + height + ", " +
                "perlinMultiplier=" + perlinMultiplier + ']';
    }

}
