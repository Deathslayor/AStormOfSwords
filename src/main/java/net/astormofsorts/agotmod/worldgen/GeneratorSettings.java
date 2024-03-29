package net.astormofsorts.agotmod.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.worldgen.biome.MapBiomeData;

import java.util.List;

// TODO: Multi Block Layer per Biome
public record GeneratorSettings(List<MapBiomeData> biomeData, int height, int minY, int dirtLevel,
                                int seaLevel) {
    public static final Codec<GeneratorSettings> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.list(MapBiomeData.CODEC).fieldOf("biome_data").forGetter(GeneratorSettings::biomeData),
            Codec.INT.fieldOf("height").forGetter(GeneratorSettings::height), Codec.INT.fieldOf("sea_level").forGetter(GeneratorSettings::seaLevel),
            Codec.INT.fieldOf("dirt_level").forGetter(GeneratorSettings::dirtLevel),
            Codec.INT.fieldOf("minY").forGetter(GeneratorSettings::minY)
    ).apply(instance, instance.stable(GeneratorSettings::new)));
}
