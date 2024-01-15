package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.worldgen.MapBasedBiomeSources;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterLists;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.OptionalLong;

import static net.astormofsorts.agotmod.AGoTMod.MOD_ID;

public class ModDimensionProvider {
    public final static ResourceLocation DIMENSION = new ResourceLocation(MOD_ID, "known_world");
    private final static ResourceKey<DimensionType> DIMENSION_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(MOD_ID, "known_world_type"));
    private final static ResourceKey<LevelStem> DIMENSION_STEM = ResourceKey.create(Registries.LEVEL_STEM, DIMENSION);

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(DIMENSION_TYPE, new DimensionType(
                OptionalLong.of(13000),
                true,
                false,
                false,
                true,
                1,
                true,
                false,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                new ResourceLocation(MOD_ID, "renderer"),
                0,
                new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)
        ));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context) {
        MapBasedBiomeSources biomeSources = new MapBasedBiomeSources(MultiNoiseBiomeSource.createFromPreset(context.lookup(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST).getOrThrow(MultiNoiseBiomeSourceParameterLists.OVERWORLD)));
        context.register(DIMENSION_STEM, new LevelStem(context.lookup(Registries.DIMENSION_TYPE).getOrThrow(DIMENSION_TYPE),
                new NoiseBasedChunkGenerator(
                        biomeSources,
                        context.lookup(Registries.NOISE_SETTINGS).getOrThrow(NoiseGeneratorSettings.OVERWORLD))
        ));
    }
}
