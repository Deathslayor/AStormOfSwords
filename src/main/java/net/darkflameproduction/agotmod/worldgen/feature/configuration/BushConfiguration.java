package net.darkflameproduction.agotmod.worldgen.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record BushConfiguration(Block woodBlock, Block leavesBlock) implements FeatureConfiguration {

    public static final Codec<BushConfiguration> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BuiltInRegistries.BLOCK.byNameCodec()
                            .fieldOf("wood_block")
                            .forGetter(BushConfiguration::woodBlock),
                    BuiltInRegistries.BLOCK.byNameCodec()
                            .fieldOf("leaves_block")
                            .forGetter(BushConfiguration::leavesBlock)
            ).apply(instance, BushConfiguration::new)
    );
}