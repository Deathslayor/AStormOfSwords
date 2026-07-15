package net.darkflameproduction.agotmod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class StonePileFeature extends Feature<NoneFeatureConfiguration> {

    public StonePileFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    private static boolean isVegetation(BlockState state) {
        return state.is(BlockTags.LEAVES)
                || state.is(BlockTags.LOGS)
                || state.is(BlockTags.FLOWERS)
                || state.is(BlockTags.SMALL_FLOWERS)
                || state.is(BlockTags.TALL_FLOWERS)
                || state.is(BlockTags.REPLACEABLE_BY_TREES)
                || state.is(Blocks.SHORT_GRASS)
                || state.is(Blocks.TALL_GRASS)
                || state.is(Blocks.FERN)
                || state.is(Blocks.LARGE_FERN)
                || state.is(Blocks.DEAD_BUSH)
                || state.is(Blocks.SEAGRASS)
                || state.is(Blocks.VINE);
    }

    private static boolean isReplaceable(BlockState state) {
        return state.isAir()
                || isVegetation(state)
                || state.is(Blocks.DIRT)
                || state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.COARSE_DIRT)
                || state.is(Blocks.GRAVEL)
                || state.is(Blocks.SAND)
                || state.is(Blocks.SNOW)
                || state.is(Blocks.SNOW_BLOCK);
    }
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        var random = context.random();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        // find surface at origin
        int surfaceY = origin.getY();
        pos.set(origin.getX(), surfaceY, origin.getZ());
        while (surfaceY > level.getMinBuildHeight() && level.getBlockState(pos).isAir()) {
            surfaceY--;
            pos.set(origin.getX(), surfaceY, origin.getZ());
        }

        if (surfaceY <= level.getMinBuildHeight()) return false;

        // 50% stone, 25% andesite, 12.5% diorite, 12.5% granite
        BlockState boulderBlock;
        BlockState boulderSlab;
        int stoneType = random.nextInt(4);
        switch (stoneType) {
            case 0, 1 -> {
                boulderBlock = Blocks.STONE.defaultBlockState();
                boulderSlab = Blocks.STONE_SLAB.defaultBlockState()
                        .setValue(SlabBlock.TYPE, SlabType.BOTTOM);
            }
            case 2 -> {
                boulderBlock = Blocks.ANDESITE.defaultBlockState();
                boulderSlab = Blocks.ANDESITE_SLAB.defaultBlockState()
                        .setValue(SlabBlock.TYPE, SlabType.BOTTOM);
            }
            default -> {
                if (random.nextBoolean()) {
                    boulderBlock = Blocks.DIORITE.defaultBlockState();
                    boulderSlab = Blocks.DIORITE_SLAB.defaultBlockState()
                            .setValue(SlabBlock.TYPE, SlabType.BOTTOM);
                } else {
                    boulderBlock = Blocks.GRANITE.defaultBlockState();
                    boulderSlab = Blocks.GRANITE_SLAB.defaultBlockState()
                            .setValue(SlabBlock.TYPE, SlabType.BOTTOM);
                }
            }
        }

        int radiusX = 2 + random.nextInt(3);
        int radiusY = 2 + random.nextInt(2);
        int radiusZ = 2 + random.nextInt(3);

        long boulderSeed = origin.getX() * 341873128712L ^ origin.getZ() * 132897987541L;

        for (int dx = -radiusX; dx <= radiusX; dx++) {
            for (int dz = -radiusZ; dz <= radiusZ; dz++) {
                int worldX = origin.getX() + dx;
                int worldZ = origin.getZ() + dz;

                // find actual surface at this X/Z column
                int localSurface = surfaceY + radiusY + 1;
                pos.set(worldX, localSurface, worldZ);
                while (localSurface > level.getMinBuildHeight()
                        && level.getBlockState(pos).isAir()) {
                    localSurface--;
                    pos.set(worldX, localSurface, worldZ);
                }

                // skip column if tree is above
                boolean hasTreeAbove = false;
                for (int checkY = localSurface + 1; checkY <= localSurface + radiusY + 4; checkY++) {
                    pos.set(worldX, checkY, worldZ);
                    BlockState checkState = level.getBlockState(pos);
                    if (checkState.is(BlockTags.LOGS) || checkState.is(BlockTags.LEAVES)) {
                        hasTreeAbove = true;
                        break;
                    }
                }
                if (hasTreeAbove) continue;

                boolean placedAnyInColumn = false;

                for (int dy = -radiusY; dy <= radiusY; dy++) {
                    int worldY = localSurface + dy;

                    if (worldY <= level.getMinBuildHeight()
                            || worldY >= level.getMaxBuildHeight()) continue;

                    double nx = (double) dx / radiusX;
                    double ny = (double) dy / radiusY;
                    double nz = (double) dz / radiusZ;

                    double dist = nx * nx + ny * ny + nz * nz;

                    double shapeNoise =
                            Math.sin(dx * 1.2 + boulderSeed * 0.00001) * 0.06
                                    + Math.sin(dz * 1.4 + boulderSeed * 0.00002) * 0.06
                                    + Math.sin(dy * 1.6 + boulderSeed * 0.00003) * 0.05
                                    + Math.sin((dx + dz) * 0.9 + boulderSeed * 0.00004) * 0.04
                                    + Math.sin((dx - dz) * 1.0 + boulderSeed * 0.00005) * 0.04;

                    boolean insideEllipsoid = dist + shapeNoise <= 1.0;

                    if (!insideEllipsoid) {
                        if (placedAnyInColumn && dy > 0) break;
                        continue;
                    }

                    pos.set(worldX, worldY, worldZ);
                    BlockState existing = level.getBlockState(pos);
                    if (!isReplaceable(existing)) {
                        placedAnyInColumn = true;
                        continue;
                    }

                    level.setBlock(pos, boulderBlock, 2);
                    placedAnyInColumn = true;

                    // only consider slab if above ground
                    if (dy > 0) {
                        double nextNy = (double)(dy + 1) / radiusY;
                        double nextDist = nx * nx + nextNy * nextNy + nz * nz;
                        double nextNoise =
                                Math.sin(dx * 1.2 + boulderSeed * 0.00001) * 0.06
                                        + Math.sin(dz * 1.4 + boulderSeed * 0.00002) * 0.06
                                        + Math.sin((dy + 1) * 1.6 + boulderSeed * 0.00003) * 0.05
                                        + Math.sin((dx + dz) * 0.9 + boulderSeed * 0.00004) * 0.04
                                        + Math.sin((dx - dz) * 1.0 + boulderSeed * 0.00005) * 0.04;

                        boolean nextIsOutside = nextDist + nextNoise > 1.0;

                        if (nextIsOutside && random.nextInt(5) == 0) {
                            pos.set(worldX, worldY + 1, worldZ);
                            BlockState above = level.getBlockState(pos);
                            // never place slab on top of another slab
                            boolean belowIsSlab = level.getBlockState(
                                    new BlockPos(worldX, worldY, worldZ)).getBlock() instanceof SlabBlock;
                            if ((above.isAir() || isVegetation(above)) && !belowIsSlab) {
                                level.setBlock(pos, boulderSlab, 2);
                            }
                            break;
                        }
                    }
                }
            }
        }

        // scatter 0-2 lone slabs around base — only on solid non-slab ground
        int scatterCount = random.nextInt(3);
        for (int i = 0; i < scatterCount; i++) {
            double angle = random.nextDouble() * Math.PI * 2;
            int scatterDist = radiusX + 1 + random.nextInt(3);
            int scatterX = origin.getX() + (int)(Math.cos(angle) * scatterDist);
            int scatterZ = origin.getZ() + (int)(Math.sin(angle) * scatterDist);

            int scatterY = surfaceY + 3;
            pos.set(scatterX, scatterY, scatterZ);
            while (scatterY > level.getMinBuildHeight() && level.getBlockState(pos).isAir()) {
                scatterY--;
                pos.set(scatterX, scatterY, scatterZ);
            }

            BlockState surfaceBlock = level.getBlockState(pos);
            if (surfaceBlock.isAir() || isVegetation(surfaceBlock)) continue;

            // never place slab on top of another slab
            boolean surfaceIsSlab = surfaceBlock.getBlock() instanceof SlabBlock;
            if (surfaceIsSlab) continue;

            pos.set(scatterX, scatterY + 1, scatterZ);
            BlockState above = level.getBlockState(pos);
            if (above.isAir() || isVegetation(above)) {
                level.setBlock(pos, boulderSlab, 2);
            }
        }

        return true;
    }
}