package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.worldgen.GeneratorSettings;
import net.astormofsorts.agotmod.worldgen.MapBasedBiomeChunkGenerator;
import net.astormofsorts.agotmod.worldgen.biome.MapBiomeData;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

import static net.astormofsorts.agotmod.AGoTMod.MOD_ID;

public class ModDimensionProvider {
    public final static ResourceLocation DIMENSION = new ResourceLocation(MOD_ID, "known_world");
    public final static ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(MOD_ID, "known_world_type"));
    public final static ResourceKey<LevelStem> DIMENSION_STEM = ResourceKey.create(Registries.LEVEL_STEM, DIMENSION);
    public final static Color DEFAULT_BIOME_COLOR = new Color(0, 0, 255);
    public final static URL MAP_URL = AGoTMod.class.getResource("/assets/agotmod/map.png");

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE, new DimensionType(OptionalLong.empty(), true, false, false, true, 1, true, false, -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, new ResourceLocation(MOD_ID, "renderer"), 0, new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);

        List<MapBiomeData> biomeData = new ArrayList<>() {
            {
                add(new MapBiomeData(biomeRegistry.getOrThrow(Biomes.PLAINS), new Color(0, 255, 0), ForgeRegistries.BLOCKS.getResourceKey(Blocks.STONE).orElseThrow(), ForgeRegistries.BLOCKS.getResourceKey(Blocks.GRASS_BLOCK).orElseThrow()));
                add(new MapBiomeData(biomeRegistry.getOrThrow(Biomes.DESERT), new Color(255, 255, 0), ForgeRegistries.BLOCKS.getResourceKey(Blocks.STONE).orElseThrow(), ForgeRegistries.BLOCKS.getResourceKey(Blocks.SAND).orElseThrow()));
                add(new MapBiomeData(biomeRegistry.getOrThrow(Biomes.OCEAN), new Color(0, 0, 255), ForgeRegistries.BLOCKS.getResourceKey(Blocks.STONE).orElseThrow(), ForgeRegistries.BLOCKS.getResourceKey(Blocks.WATER).orElseThrow()));
            }
        };

        context.register(DIMENSION_STEM, new LevelStem(context.lookup(Registries.DIMENSION_TYPE).getOrThrow(DIMENSION_TYPE), new MapBasedBiomeChunkGenerator(new GeneratorSettings(biomeData, 265, 0, 50, 64))));
    }
}
