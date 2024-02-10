package net.astormofsorts.agotmod.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.map.MapManager;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public class MapBasedBiomeSource extends BiomeSource {
    private static final MapCodec<Holder<Biome>> BIOME_CODEC = Biome.CODEC.fieldOf("biome");
    private static final MapCodec<List<Holder<Biome>>> BIOME_LIST_CODEC = Codec.list(BIOME_CODEC.codec()).fieldOf("biome_list");
    public static final Codec<MapBasedBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BIOME_LIST_CODEC.forGetter(o -> o.biomeList)
    ).apply(instance, instance.stable(MapBasedBiomeSource::new)));

    private final List<Holder<Biome>> biomeList;

    public MapBasedBiomeSource(List<Holder<Biome>> pBiomeList) {
        this.biomeList = pBiomeList;
    }

    @Override
    protected @NotNull Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull Stream<Holder<Biome>> collectPossibleBiomes() {
        return this.biomeList.stream();
    }

    @Override
    public @NotNull Holder<Biome> getNoiseBiome(int pX, int pY, int pZ, Climate.@NotNull Sampler pSampler) {
        ResourceKey<Biome> biome = MapManager.getBiomeFromColor(pX, pZ);
        for (Holder<Biome> possibleBiome : possibleBiomes()) {
            if (possibleBiome.is(biome))
                return possibleBiome;
        }

        // this shouldn't be triggered
        return possibleBiomes().stream().findFirst().orElseThrow();
    }
}