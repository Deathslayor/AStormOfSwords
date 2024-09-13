package net.astormofsorts.agotmod.worldgen.map;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.map.MapManager;
import net.astormofsorts.agotmod.worldgen.biome.MapBiomeData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.QuartPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MapBasedBiomeChunkGenerator extends ChunkGenerator {
    public static final Codec<MapBasedBiomeChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            MapBasedBiomeSource.CODEC.fieldOf("biome_source").forGetter(o -> o.biomeSource)
    ).apply(instance, instance.stable(MapBasedBiomeChunkGenerator::new)));

    protected final MapBasedBiomeSource biomeSource;
    protected final MapManager mapManager;
    private final long seed;

    private MapBasedBiomeChunkGenerator(MapBasedBiomeSource biomeSource) {
        super(biomeSource);
        this.biomeSource = biomeSource;
        this.seed = RandomSupport.generateUniqueSeed();
        Map<Color, Integer> heights = new HashMap<>();
        for (MapBiomeData biomedata : biomeSource.getSettings().biomeData()) {
            heights.put(biomedata.color(), biomedata.height());
        }
        this.mapManager = new MapManager(RandomSupport.generateUniqueSeed());
    }

    public static MapBasedBiomeChunkGenerator of(GeneratorSettings settings) {
        MapBasedBiomeSource biomeSource = new MapBasedBiomeSource(settings);
        return new MapBasedBiomeChunkGenerator(biomeSource);
    }

    @Override
    protected @NotNull Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(@NotNull WorldGenRegion pLevel, long pSeed, @NotNull RandomState pRandom, @NotNull BiomeManager pBiomeManager, @NotNull StructureManager pStructureManager, @NotNull ChunkAccess pChunk, GenerationStep.@NotNull Carving pStep) {
    }

    /**
     * places stone and dirt. Will be replaced by biomes automatically
     */
    @Override
    public void buildSurface(@NotNull WorldGenRegion pLevel, @NotNull StructureManager pStructureManager, @NotNull RandomState pRandom, @NotNull ChunkAccess chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                MapBiomeData biomeData = null;

                BlockPos pos = chunk.getPos().getBlockAt(x, chunk.getHeight(), z);

                Holder<Biome> biomeHolder = pLevel.getBiome(pos);
                for (MapBiomeData biomeDataI : this.biomeSource.getSettings().biomeData()) {
                    if (biomeDataI.biome() == biomeHolder) {
                        biomeData = biomeDataI;
                        break;
                    }
                }

                if (biomeData != null) {
                    float height = mapManager.getHeightFromPosition(pos.getX(), pos.getZ());

                    for (int y = chunk.getMinBuildHeight(); y < chunk.getMinBuildHeight() + 4; y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.BEDROCK.defaultBlockState(), false);
                    }

                    for (int y = chunk.getMinBuildHeight() + 4; y < biomeSource.getSettings().deepslateLevel(); y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), pLevel.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(biomeData.deepSlateBlock()).defaultBlockState(), false);
                    }

                    float dirtHeight = this.biomeSource.getSettings().dirtLevel() + height - 1;

                    for (int y = this.biomeSource.getSettings().deepslateLevel(); y < (dirtHeight / 2); y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), pLevel.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(biomeData.stoneBlock()).defaultBlockState(), false);
                    }

                    // upper stone
                    for (int y = (int) (dirtHeight / 2); y <= dirtHeight; y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), pLevel.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(biomeData.stoneBlock()).defaultBlockState(), false);
                    }

                    for (int y = (int) (this.biomeSource.getSettings().dirtLevel() - 4 + height); y < this.biomeSource.getSettings().dirtLevel() + height; y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), pLevel.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(biomeData.dirtBlock()).defaultBlockState(), false);
                    }

                    Block grassBlock = pLevel.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(biomeData.grassBlock());

                    // no grass underwater
                    if (this.biomeSource.getSettings().dirtLevel() + height < getSeaLevel() && grassBlock == Blocks.GRASS_BLOCK) {
                        grassBlock = Blocks.DIRT;
                    }

                    chunk.setBlockState(chunk.getPos().getBlockAt(x, (int) (this.biomeSource.getSettings().dirtLevel() + height), z), grassBlock.defaultBlockState(), false);

                    // place oceans
                    for (int y = (int) (this.biomeSource.getSettings().dirtLevel() + height + 1); y <= this.getSeaLevel(); y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.WATER.defaultBlockState(), false);
                    }
                }
            }
        }
    }

    @Override
    public void spawnOriginalMobs(@NotNull WorldGenRegion pLevel) {
        ChunkPos chunkpos = pLevel.getCenter();
        Holder<Biome> holder = pLevel.getBiome(chunkpos.getWorldPosition().atY(pLevel.getMaxBuildHeight() - 1));
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
        worldgenrandom.setDecorationSeed(pLevel.getSeed(), chunkpos.getMinBlockX(), chunkpos.getMinBlockZ());
        NaturalSpawner.spawnMobsForChunkGeneration(pLevel, holder, chunkpos, worldgenrandom);
    }

    @Override
    public int getGenDepth() {
        return this.biomeSource.getSettings().height();
    }

    @Override
    public @NotNull CompletableFuture<ChunkAccess> fillFromNoise(@NotNull Executor pExecutor, @NotNull Blender pBlender, @NotNull RandomState pRandom, @NotNull StructureManager pStructureManager, @NotNull ChunkAccess pChunk) {
        return CompletableFuture.completedFuture(pChunk);
    }

    @Override
    public int getSeaLevel() {
        return biomeSource.getSettings().seaLevel();
    }

    @Override
    public int getMinY() {
        return this.biomeSource.getSettings().minY();
    }

    @Override
    public int getBaseHeight(int pX, int pZ, @NotNull Heightmap.Types pType, @NotNull LevelHeightAccessor pLevel, @NotNull RandomState pRandom) {
        return (int) (1 + biomeSource.getSettings().dirtLevel() + Math.abs(mapManager.getHeightFromPosition(pX, pZ)));
    }

    @Override
    public @NotNull NoiseColumn getBaseColumn(int pX, int pZ, @NotNull LevelHeightAccessor pHeight, @NotNull RandomState pRandom) {
        return new NoiseColumn(0, new BlockState[0]);
    }

    @Override
    public void addDebugScreenInfo(@NotNull List<String> pInfo, @NotNull RandomState pRandom, @NotNull BlockPos pPos) {
        Color biomeColor = MapManager.getColorFromPosition(QuartPos.fromBlock(pPos.getX()), QuartPos.fromBlock(pPos.getZ()));
        pInfo.add("Biome Color: R:" + biomeColor.getRed() + " G: " + biomeColor.getGreen() + " B: " + biomeColor.getBlue() + " A: " + biomeColor.getAlpha());
    }

    @Override
    @NotNull
    public MapBasedBiomeSource getBiomeSource() {
        return biomeSource;
    }
}