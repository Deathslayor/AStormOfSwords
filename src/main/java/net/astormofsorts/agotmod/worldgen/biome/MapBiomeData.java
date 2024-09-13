package net.astormofsorts.agotmod.worldgen.biome;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record MapBiomeData(Holder<Biome> biome, Color color, ResourceKey<Block> deepSlateBlock, ResourceKey<Block> stoneBlock,
                           ResourceKey<Block> dirtBlock, ResourceKey<Block> grassBlock, boolean isWater, int height) {

    private MapBiomeData(Holder<Biome> biome, int color, ResourceKey<Block> deepSlateBlock, ResourceKey<Block> stoneBlock,
                 ResourceKey<Block> dirtBlock, ResourceKey<Block> grassBlock, boolean isWater, int height) {
        this(biome, new Color(color), deepSlateBlock, stoneBlock, dirtBlock, grassBlock, isWater, height);
    }
    public static final Codec<MapBiomeData> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            Biome.CODEC.fieldOf("biome").forGetter(MapBiomeData::biome),
            Codec.INT.fieldOf("color").forGetter(o -> o.color().getRGB()),
            ResourceKey.codec(Registries.BLOCK).fieldOf("deepslate_block").forGetter(MapBiomeData::deepSlateBlock),
            ResourceKey.codec(Registries.BLOCK).fieldOf("stone_block").forGetter(MapBiomeData::stoneBlock),
            ResourceKey.codec(Registries.BLOCK).fieldOf("dirt_block").forGetter(MapBiomeData::dirtBlock),
            ResourceKey.codec(Registries.BLOCK).fieldOf("grass_block").forGetter(MapBiomeData::grassBlock),
            Codec.BOOL.fieldOf("is_water").forGetter(MapBiomeData::isWater),
            Codec.INT.fieldOf("height").forGetter(MapBiomeData::height)
    ).apply(instance, instance.stable(MapBiomeData::new)));

    public static Map<Color, Integer> toHeightsMap(List<MapBiomeData> dataList) {
        Map<Color, Integer> map = new HashMap<>();
        for (MapBiomeData data : dataList) {
            map.put(data.color(), data.height());
        }
        return map;
    }
}
