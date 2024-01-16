package net.astormofsorts.agotmod.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.datagen.ModDimensionProvider;
import net.astormofsorts.agotmod.map.MapManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class MapBasedBiomeSource extends BiomeSource {
    public static final Codec<MapBasedBiomeSource> MAP_CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            MapBasedBiomeSource.CODEC.fieldOf("wrapped").forGetter(o -> o.delegate)

    ).apply(instance, MapBasedBiomeSource::new));

    private final BiomeSource delegate;

    public MapBasedBiomeSource(BiomeSource delegate) {
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

    @Override
    public @NotNull Holder<Biome> getNoiseBiome(int pX, int pY, int pZ, Climate.@NotNull Sampler pSampler) {
        return getNoiseBiome(pX, pZ);
    }

    private @NotNull Holder<Biome> getNoiseBiome(int pX, int pZ) {
        ResourceKey<Biome> biome = MapManager.getBiomeFromColor(pX, pZ);
        for (Holder<Biome> possibleBiome : possibleBiomes()) {
            if (possibleBiome.is(biome))
                return possibleBiome;
        }
        // generate a new biome if the biome from the map wasn't found
        Holder<Biome> nearestBiome = getNearestAlreadyGeneratedBiome(pX, pZ, 16, 32);
        return nearestBiome != null ? nearestBiome : possibleBiomes().stream().findAny().orElseThrow();
    }

    /**
     * This crazy shit gets the nearest already generated biome, needed in case there are errors in the map
     * <p>
     * Nevertheless, this should be replaced with something that intentionally gets the new biome (e.g. plains, x, oceans, x should probably be a beach)
     */
    @Nullable
    private Holder<Biome> getNearestAlreadyGeneratedBiome(int pX, int pZ, int radius, int steps) {
        // 64 means within 64 blocks
        int i = Math.floorDiv(radius, steps);

        for (BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.spiralAround(BlockPos.ZERO, i, Direction.EAST, Direction.SOUTH)) {
            int j = pX + blockpos$mutableblockpos.getX() * steps;
            int k = pZ + blockpos$mutableblockpos.getZ() * steps;
            int l = QuartPos.fromBlock(j);
            int i1 = QuartPos.fromBlock(k);

            Holder<Biome> holder = this.getNoiseBiome(l, i1);
            // ignore the default biome
            if (possibleBiomes().contains(holder) && !holder.is(ModDimensionProvider.DEFAULT_BIOME)) {
                return holder;
            }
        }

        return null;
    }
}