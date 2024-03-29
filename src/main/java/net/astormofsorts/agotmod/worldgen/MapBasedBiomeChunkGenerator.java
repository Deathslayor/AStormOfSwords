package net.astormofsorts.agotmod.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.worldgen.biome.MapBiomeData;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MapBasedBiomeChunkGenerator extends ChunkGenerator {
    public static final Codec<MapBasedBiomeChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            MapBasedBiomeSource.CODEC.fieldOf("biome_source").forGetter(o -> o.biomeSource)
    ).apply(instance, instance.stable(MapBasedBiomeChunkGenerator::new)));

    protected final MapBasedBiomeSource biomeSource;


    private final GeneratorSettings settings;

    private MapBasedBiomeChunkGenerator(MapBasedBiomeSource biomeSource) {
        super(biomeSource);
        this.biomeSource = biomeSource;
        this.settings = biomeSource.getSettings();
    }

    public MapBasedBiomeChunkGenerator(GeneratorSettings settings) {
        super(new MapBasedBiomeSource(settings));
        this.biomeSource = new MapBasedBiomeSource(settings);
        this.settings = biomeSource.getSettings();
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
                // TODO: This should be position-based
                int height = 70;

                MapBiomeData biomeData = null;

                for (MapBiomeData biomeDataI : this.settings.biomeData()) {
                    if (biomeDataI.biome() == pLevel.getBiome(chunk.getPos().getBlockAt(x, chunk.getHeight(), z))) {
                        biomeData = biomeDataI;
                        break;
                    }
                }

                if (biomeData != null) {
                    // TODO: Add more loops for more block layer
                    for (int y = chunk.getMinBuildHeight(); y <= settings.dirtLevel(); y++) {
                        chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), pLevel.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(biomeData.stoneBlock()).defaultBlockState(), false);
                    }

                    for (int y = settings.dirtLevel(); y <= height; y++) {
                        // FIXME: Block Selection broken
                        Block surfaceBlock = pLevel.registryAccess().registryOrThrow(Registries.BLOCK).getOrThrow(biomeData.dirtBlock());

                        // place dirt instead of grass underground
                        if (surfaceBlock == Blocks.GRASS_BLOCK && y < height) {
                            chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), Blocks.DIRT.defaultBlockState(), false);
                        } else {
                            chunk.setBlockState(chunk.getPos().getBlockAt(x, y, z), surfaceBlock.defaultBlockState(), false);
                        }
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
        return settings.height();
    }

    @Override
    public @NotNull CompletableFuture<ChunkAccess> fillFromNoise(@NotNull Executor pExecutor, @NotNull Blender pBlender, @NotNull RandomState pRandom, @NotNull StructureManager pStructureManager, @NotNull ChunkAccess pChunk) {
        return CompletableFuture.completedFuture(pChunk);
    }

    @Override
    public int getSeaLevel() {
        return settings.seaLevel();
    }

    @Override
    public int getMinY() {
        return settings.minY();
    }

    // TODO: Should be something Block specific
    @Override
    public int getBaseHeight(int pX, int pZ, @NotNull Heightmap.Types pType, @NotNull LevelHeightAccessor pLevel, @NotNull RandomState pRandom) {
        return 70;
    }

    @Override
    public @NotNull NoiseColumn getBaseColumn(int pX, int pZ, @NotNull LevelHeightAccessor pHeight, @NotNull RandomState pRandom) {
        return new NoiseColumn(0, new BlockState[0]);
    }

    @Override
    public void addDebugScreenInfo(@NotNull List<String> pInfo, @NotNull RandomState pRandom, @NotNull BlockPos pPos) {
    }

    @Override
    @NotNull
    public MapBasedBiomeSource getBiomeSource() {
        return biomeSource;
    }
}