package net.astormofsorts.agotmod.map;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;

@SuppressWarnings("unused")
public class MapBiomeBuilder {
    private ResourceKey<Biome> biome;
    private int color;
    private Block deepslateBlock = Blocks.DEEPSLATE;
    private Block stoneBlock = Blocks.STONE;
    private Block dirtBlock = Blocks.DIRT;
    private Block grassBlock = Blocks.GRASS_BLOCK;
    private int height = 0;
    private double perlinMultiplier = 8;
    private double biomeWeight = 1;

    public MapBiomeBuilder setBiome(ResourceKey<Biome> biome) {
        this.biome = biome;
        return this;
    }

    public MapBiomeBuilder setColor(int color) {
        this.color = color;
        return this;
    }

    public MapBiomeBuilder setColor(Color color) {
        return setColor(color.getRGB());
    }

    public MapBiomeBuilder setDeepslateBlock(Block deepslateBlock) {
        this.deepslateBlock = deepslateBlock;
        return this;
    }

    public MapBiomeBuilder setStoneBlock(Block stoneBlock) {
        this.stoneBlock = stoneBlock;
        return this;
    }

    public MapBiomeBuilder setDirtBlock(Block dirtBlock) {
        this.dirtBlock = dirtBlock;
        return this;
    }

    public MapBiomeBuilder setGrassBlock(Block grassBlock) {
        this.grassBlock = grassBlock;
        return this;
    }

    public MapBiomeBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public MapBiomeBuilder setPerlinMultiplier(double perlinMultiplier) {
        this.perlinMultiplier = perlinMultiplier;
        return this;
    }

    public MapBiomeBuilder setBiomeWeight(double biomeWeight) {
        this.biomeWeight = biomeWeight;
        return this;
    }


    public MapBiome build() {
        return new MapBiome(biome, color, deepslateBlock, stoneBlock, dirtBlock, grassBlock, height, perlinMultiplier, biomeWeight);
    }
}