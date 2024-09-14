package net.astormofsorts.agotmod.worldgen.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

// TODO: Multi Block Layer per Biome
public record GeneratorSettings(List<Holder<Biome>> biomeData, int deepslateLevel, int dirtLevel, int minY, int height, int seaLevel) {
    public static final Codec<GeneratorSettings> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Codec.list(Biome.CODEC).fieldOf("biomes").forGetter(GeneratorSettings::biomeData),
            Codec.INT.fieldOf("deepslate_level").forGetter(GeneratorSettings::deepslateLevel),
            Codec.INT.fieldOf("dirt_level").forGetter(GeneratorSettings::dirtLevel),
            Codec.INT.fieldOf("min_y").forGetter(GeneratorSettings::minY),
            Codec.INT.fieldOf("height").forGetter(GeneratorSettings::height),
            Codec.INT.fieldOf("sea_level").forGetter(GeneratorSettings::seaLevel)
    ).apply(instance, instance.stable(GeneratorSettings::new)));
}
