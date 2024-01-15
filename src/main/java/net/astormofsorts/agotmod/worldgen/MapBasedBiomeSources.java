package net.astormofsorts.agotmod.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.map.MapManager;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class MapBasedBiomeSources extends BiomeSource {
    public static final Codec<MapBasedBiomeSources> MAP_CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            MapBasedBiomeSources.CODEC.fieldOf("wrapped").forGetter(o -> o.delegate)

    ).apply(instance, MapBasedBiomeSources::new));

    public BiomeSource delegate;

    public MapBasedBiomeSources(BiomeSource delegate) {
        this.delegate = delegate;
    }

    @Override
    protected @NotNull Codec<? extends BiomeSource> codec() {
        return MAP_CODEC;
    }

    @Override
    protected Stream<Holder<Biome>> collectPossibleBiomes() {
        return delegate.possibleBiomes().stream();
    }

    public @NotNull Holder<Biome> getNoiseBiome(int pX, int pY, int pZ, Climate.@NotNull Sampler pSampler) {
        ResourceKey<Biome> biome = MapManager.getBiomeFromColor(pX, pZ);
        for (Holder<Biome> possibleBiome : possibleBiomes()) {
            if (possibleBiome.is(biome))
                return possibleBiome;
        }
        // again, return ocean if biome not found
        return possibleBiomes().stream().findFirst().orElseThrow();
    }
}