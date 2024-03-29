package net.astormofsorts.agotmod.worldgen.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.map.MapManager;
import net.astormofsorts.agotmod.worldgen.biome.MapBiomeData;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.stream.Stream;

public class MapBasedBiomeSource extends BiomeSource {
    public static final Codec<MapBasedBiomeSource> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            GeneratorSettings.CODEC.fieldOf("settings").forGetter(o -> o.settings)
    ).apply(instance, instance.stable(MapBasedBiomeSource::new)));

    private final GeneratorSettings settings;

    public MapBasedBiomeSource(GeneratorSettings settings) {
        this.settings = settings;
    }

    @Override
    protected @NotNull Codec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull Stream<Holder<Biome>> collectPossibleBiomes() {
        return this.settings.biomeData().stream().map(MapBiomeData::biome);
    }

    @Override
    public @NotNull Holder<Biome> getNoiseBiome(int pX, int pY, int pZ, Climate.@NotNull Sampler pSampler) {
        return getBiomeData(pX, pZ).biome();
    }

    public @NotNull MapBiomeData getBiomeData(int pX, int pZ) {
        Color biomeColor = MapManager.getColorFromPosition(pX, pZ);

        for (MapBiomeData biomeData : this.settings.biomeData()) {
            if (biomeData.color().getRGB() == biomeColor.getRGB()) {
                return biomeData;
            }
        }

        // this shouldn't be triggered
        return this.settings.biomeData().get(0);
    }

    public GeneratorSettings getSettings() {
        return settings;
    }
}