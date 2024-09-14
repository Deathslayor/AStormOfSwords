package net.astormofsorts.agotmod.map;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record MapBiome(ResourceKey<Biome> biome, Color color, Block stoneBlock,
                       Block dirtBlock, Block grassBlock, int height, double perlinModifier) {

    public static final List<MapBiome> BIOME_LIST = new ArrayList<>();

    public static MapBiome OCEAN;
    public static MapBiome WARN_OCEAN;
    public static MapBiome PLAINS;
    public static MapBiome DESERT;
    public static MapBiome MOUNTAINS;

    public static void initialize() {
        OCEAN = register(new MapBiome(Biomes.OCEAN, new Color(0, 0, 100), Blocks.STONE, Blocks.STONE, Blocks.GRAVEL, -45, 0.1));
        WARN_OCEAN = register(new MapBiome(Biomes.WARM_OCEAN, new Color(0, 0, 255), Blocks.STONE, Blocks.STONE, Blocks.SAND, -14, 0.1));
        PLAINS = register(new MapBiome(Biomes.PLAINS, new Color(0, 255, 0), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 10, 1.4));
        DESERT = register(new MapBiome(Biomes.DESERT, new Color(255, 255, 0), Blocks.STONE, Blocks.SANDSTONE, Blocks.SAND, 10, 1.2));
        MOUNTAINS = register(new MapBiome(Biomes.STONY_PEAKS, new Color(255, 255, 255), Blocks.STONE, Blocks.STONE, Blocks.STONE, 100, 5));
    }

    public static MapBiome register(MapBiome biome) {
        BIOME_LIST.add(biome);
        return biome;
    }

    @Nullable
    public static MapBiome getByColor(@Nullable Color color) {
        return BIOME_LIST.stream().filter(b -> b.color().equals(color)).findAny().orElse(null);
    }

    public static Map<Color, Integer> toHeightMap(List<MapBiome> dataList) {
        Map<Color, Integer> map = new HashMap<>();
        for (MapBiome data : dataList) {
            map.put(data.color(), data.height());
        }
        return map;
    }

    public static @NotNull MapBiome getDefault() {
        return OCEAN;
    }
}
