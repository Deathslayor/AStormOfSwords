package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.worldgen.biome.MapBiomeData;
import net.astormofsorts.agotmod.worldgen.map.GeneratorSettings;
import net.astormofsorts.agotmod.worldgen.map.MapBasedBiomeChunkGenerator;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import static net.astormofsorts.agotmod.AGoTMod.MOD_ID;

public class ModDimensionProvider {
    public final static ResourceLocation DIMENSION = new ResourceLocation(MOD_ID, "known_world");
    public final static ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(MOD_ID, "known_world_type"));
    public final static ResourceKey<LevelStem> DIMENSION_STEM = ResourceKey.create(Registries.LEVEL_STEM, DIMENSION);
    public final static Color DEFAULT_BIOME_COLOR = new Color(0, 0, 255);

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE, new DimensionType(OptionalLong.empty(), true, false, false, true, 1, true, false, -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, new ResourceLocation(MOD_ID, "renderer"), 0, new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);

        context.register(DIMENSION_STEM, new LevelStem(context.lookup(Registries.DIMENSION_TYPE).getOrThrow(DIMENSION_TYPE), MapBasedBiomeChunkGenerator.of(new GeneratorSettings(getBiomeData(biomeRegistry), 0, 66, -32, 256, 54))));
    }

    public static List<MapBiomeData> getBiomeData(HolderGetter<Biome> biomeRegistry) {
        return new ArrayList<>() {
            {
                add(formatBiomeData(biomeRegistry, Biomes.PLAINS, new Color(0, 255, 0), Blocks.DEEPSLATE, Blocks.STONE, Blocks.DIRT, Blocks.GRASS_BLOCK, false, 64));
                add(formatBiomeData(biomeRegistry, Biomes.DESERT, new Color(255, 255, 0), Blocks.DEEPSLATE, Blocks.STONE, Blocks.SANDSTONE, Blocks.SAND, false, 75));
                add(formatBiomeData(biomeRegistry, Biomes.OCEAN, new Color(0, 0, 255), Blocks.DEEPSLATE, Blocks.STONE, Blocks.WATER, Blocks.WATER, true, 50));
            }
        };
    }

    private static MapBiomeData formatBiomeData(HolderGetter<Biome> biomeRegistry, ResourceKey<Biome> biome, Color color, Block deepSlateBlock, Block stoneBlock,
                                                Block dirtBlock, Block grassBlock, boolean isWater, int height) {
        return new MapBiomeData(biomeRegistry.getOrThrow(biome), color, ForgeRegistries.BLOCKS.getResourceKey(deepSlateBlock).orElseThrow(), ForgeRegistries.BLOCKS.getResourceKey(stoneBlock).orElseThrow(), ForgeRegistries.BLOCKS.getResourceKey(dirtBlock).orElseThrow(), ForgeRegistries.BLOCKS.getResourceKey(grassBlock).orElseThrow(), isWater, height);
    }
}
