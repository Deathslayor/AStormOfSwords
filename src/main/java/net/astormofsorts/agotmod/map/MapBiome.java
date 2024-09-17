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

    private static MapBiome DEEP_COLD_OCEAN;

    public static void initialize() {
        // The North
        register(new MapBiome(Biomes.MANGROVE_SWAMP, new Color(34, 51, 34).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 0, 0.01));
        register(new MapBiome(Biomes.TAIGA, new Color(43, 70, 43).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));
        register(new MapBiome(Biomes.SNOWY_PLAINS, new Color(57, 95, 57).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));
        register(new MapBiome(Biomes.PLAINS, new Color(57, 95, 57).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));
        register(new MapBiome(Biomes.WINDSWEPT_HILLS, new Color(151, 151, 151).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 45, 1.7));
        register(new MapBiome(Biomes.JAGGED_PEAKS, new Color(130, 130, 130).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 100, 3.5));
        register(new MapBiome(Biomes.SWAMP, new Color(76, 95, 49).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 0, 0.01));
        register(new MapBiome(Biomes.SAVANNA_PLATEAU, new Color(85, 109, 51).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.07));
        register(new MapBiome(Biomes.SAVANNA, new Color(98, 123, 64).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.04));
        register(new MapBiome(Biomes.FOREST, new Color(61, 81, 51).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));
        register(new MapBiome(Biomes.MEADOW, new Color(72, 122, 72).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));
        // wall
        register(new MapBiome(Biomes.FROZEN_PEAKS, new Color(255, 255, 255).getRGB(), Blocks.STONE, Blocks.PACKED_ICE, Blocks.SNOW_BLOCK, 150, 0));
        //North Of The Wall
        register(new MapBiome(Biomes.SNOWY_TAIGA, new Color(130, 140, 130).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));
        register(new MapBiome(Biomes.SNOWY_SLOPES, new Color(217, 217, 217).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));
        register(new MapBiome(Biomes.SUNFLOWER_PLAINS, new Color(107, 143, 107).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, 5, 0.1));



        // water biomes
        register(new MapBiome(Biomes.RIVER, new Color(1, 98, 255).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, -5, 0));
        register(new MapBiome(Biomes.FROZEN_RIVER, new Color(87, 145, 240).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, -5, 0));
        register(new MapBiome(Biomes.LUKEWARM_OCEAN, new Color(0, 83, 217).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, -5, 1)); // lake
        register(new MapBiome(Biomes.FROZEN_OCEAN, new Color(78, 126, 204).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, -5, 1)); // Frozen Lake
        register(new MapBiome(Biomes.COLD_OCEAN, new Color(0, 42, 103).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, -35, 0.2));
        DEEP_COLD_OCEAN = register(new MapBiome(Biomes.DEEP_COLD_OCEAN, new Color(0, 35, 85).getRGB(), Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, -125, 0.5));
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
        return DEEP_COLD_OCEAN;
    }
}
