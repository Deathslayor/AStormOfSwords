package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.map.MapBiome;
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
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;

import java.util.ArrayList;
import java.util.OptionalLong;

import static net.astormofsorts.agotmod.AGoTMod.MOD_ID;

public class ModDimensionProvider {
    public final static ResourceLocation DIMENSION = new ResourceLocation(MOD_ID, "known_world");
    public final static ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(MOD_ID, "known_world_type"));
    public final static ResourceKey<LevelStem> DIMENSION_STEM = ResourceKey.create(Registries.LEVEL_STEM, DIMENSION);

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE, new DimensionType(OptionalLong.empty(), true, false, false, true, 1, true, false, -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, new ResourceLocation(MOD_ID, "renderer"), 0, new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);

        context.register(DIMENSION_STEM, new LevelStem(context.lookup(Registries.DIMENSION_TYPE).getOrThrow(DIMENSION_TYPE), MapBasedBiomeChunkGenerator.of(new GeneratorSettings(new ArrayList<>() {
            {
                for (MapBiome biome : MapBiome.BIOME_LIST) {
                    add(biomeRegistry.getOrThrow(biome.biome()));
                }
            }
        }, 0, 66, -32, 256, 64))));
    }
}
