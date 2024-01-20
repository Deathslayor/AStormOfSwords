package net.astormofsorts.agotmod.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
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

// TODO: Fix terrain generation, everything is grass and flat right now :/
public class MapBasedBiomeChunkGenerator extends ChunkGenerator {
    public static final Codec<MapBasedBiomeChunkGenerator> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            BiomeSource.CODEC.fieldOf("biome_source").forGetter(o -> o.biomeSource),
            NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(o -> o.settings)
    ).apply(instance, instance.stable(MapBasedBiomeChunkGenerator::new)));

    private final NoiseBasedChunkGenerator delegate;
    private final Holder<NoiseGeneratorSettings> settings;

    public MapBasedBiomeChunkGenerator(BiomeSource pBiomeSource, Holder<NoiseGeneratorSettings> pSettings) {
        super(pBiomeSource);
        this.settings = pSettings;
        this.delegate = new NoiseBasedChunkGenerator(pBiomeSource, pSettings);
    }

    @Override
    protected @NotNull Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(@NotNull WorldGenRegion pLevel, long pSeed, @NotNull RandomState pRandom, @NotNull BiomeManager pBiomeManager, @NotNull StructureManager pStructureManager, @NotNull ChunkAccess pChunk, GenerationStep.@NotNull Carving pStep) {
    }

    @Override
    public void buildSurface(@NotNull WorldGenRegion pLevel, @NotNull StructureManager pStructureManager, @NotNull RandomState pRandom, @NotNull ChunkAccess pChunk) {
    }

    @Override
    public void spawnOriginalMobs(@NotNull WorldGenRegion pLevel) {
    }

    @Override
    public int getGenDepth() {
        return delegate.getGenDepth();
    }

    @Override
    public @NotNull CompletableFuture<ChunkAccess> fillFromNoise(@NotNull Executor pExecutor, @NotNull Blender pBlender, @NotNull RandomState pRandom, @NotNull StructureManager pStructureManager, ChunkAccess pChunk) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        Heightmap heightmap = pChunk.getOrCreateHeightmapUnprimed(Heightmap.Types.OCEAN_FLOOR_WG);
        Heightmap heightmap1 = pChunk.getOrCreateHeightmapUnprimed(Heightmap.Types.WORLD_SURFACE_WG);

        for (int i = 0; i < 5; ++i) {
            BlockState blockstate = Blocks.GRASS_BLOCK.defaultBlockState();
            int j = pChunk.getMinBuildHeight() + i;

            for (int k = 0; k < 16; ++k) {
                for (int l = 0; l < 16; ++l) {
                    pChunk.setBlockState(blockpos$mutableblockpos.set(k, j, l), blockstate, false);
                    heightmap.update(k, j, l, blockstate);
                    heightmap1.update(k, j, l, blockstate);
                }
            }
        }

        return CompletableFuture.completedFuture(pChunk);
    }

    @Override
    public int getSeaLevel() {
        return delegate.getSeaLevel();
    }

    @Override
    public int getMinY() {
        return delegate.getMinY();
    }

    @Override
    public int getBaseHeight(int pX, int pZ, Heightmap.@NotNull Types pType, @NotNull LevelHeightAccessor pLevel, @NotNull RandomState pRandom) {
        return delegate.getBaseHeight(pX, pZ, pType, pLevel, pRandom);
    }

    @Override
    public @NotNull NoiseColumn getBaseColumn(int pX, int pZ, @NotNull LevelHeightAccessor pHeight, @NotNull RandomState pRandom) {
        return delegate.getBaseColumn(pX, pZ, pHeight, pRandom);
    }

    @Override
    public void addDebugScreenInfo(@NotNull List<String> pInfo, @NotNull RandomState pRandom, @NotNull BlockPos pPos) {
        delegate.addDebugScreenInfo(pInfo, pRandom, pPos);
    }
}