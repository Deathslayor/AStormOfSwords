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

public record MapBiome(ResourceKey<Biome> biome, int color, Block stoneBlock,
                       Block dirtBlock, Block grassBlock, int height, double perlinModifier) {

    public static final List<MapBiome> BIOME_LIST = new ArrayList<>();

    private static MapBiome DEEP_OCEAN;

    public static void initialize() {
        DEEP_OCEAN = register(new MapBiome(Biomes.DEEP_OCEAN, new Color(0, 35, 85).getRGB(), Blocks.STONE, Blocks.STONE, Blocks.GRAVEL, -18, 0.2));
        register(new MapBiome(Biomes.OCEAN, new Color(0, 42, 103).getRGB(), Blocks.STONE, Blocks.STONE, Blocks.GRAVEL, -10, 0.1));
        register(new MapBiome(Biomes.RIVER, new Color(34, 96, 136).getRGB(), Blocks.STONE, Blocks.STONE, Blocks.GRAVEL, -5, 0.05));
        register(new MapBiome(Biomes.STONY_PEAKS, new Color(151, 151, 151).getRGB(), Blocks.STONE, Blocks.STONE, Blocks.STONE, 75, 2));
        register(new MapBiome(Biomes.DARK_FOREST, new Color(34, 51, 34).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.4));
        register(new MapBiome(Biomes.FOREST, new Color(43, 70, 43).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 6, 0.5));
        register(new MapBiome(Biomes.PLAINS, new Color(56, 93, 56).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.4));
        register(new MapBiome(Biomes.SWAMP, new Color(98, 123, 64).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 0, 0.2));
    }

    public static MapBiome register(MapBiome biome) {
        BIOME_LIST.add(biome);
        return biome;
    }

    @NotNull
    public static MapBiome getByColor(@Nullable Color color) {
        return BIOME_LIST.stream().filter(b -> new Color(b.color).equals(color)).findAny().orElse(MapBiome.getDefault());
    }

    public static Map<Integer, Integer> toHeightMap(List<MapBiome> dataList) {
        Map<Integer, Integer> map = new HashMap<>();
        for (MapBiome data : dataList) {
            map.put(data.color(), data.height());
        }
        return map;
    }

    public static @NotNull MapBiome getDefault() {
        return DEEP_OCEAN;
    }
}
