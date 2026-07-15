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

public class TorFeature extends Feature<NoneFeatureConfiguration> {

    public TorFeature(Codec<NoneFeatureConfiguration> codec) {
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

        // max 16 blocks tall, max 32 blocks wide radius
        // always at least 1.5x wider than tall
        int radiusY = 5 + random.nextInt(8);
        int minRadius = (int)(radiusY * 1.5);
        int radiusX = Math.min(32, Math.max(minRadius,
                minRadius + random.nextInt(Math.max(1, 32 - minRadius))));
        int radiusZ = Math.min(32, Math.max(minRadius,
                minRadius + random.nextInt(Math.max(1, 32 - minRadius))));
        int halfY = radiusY / 2;

        // scan entire footprint for trees before placing anything
        for (int dx = -radiusX; dx <= radiusX; dx++) {
            for (int dz = -radiusZ; dz <= radiusZ; dz++) {
                int worldX = origin.getX() + dx;
                int worldZ = origin.getZ() + dz;
                for (int checkY = surfaceY; checkY <= surfaceY + radiusY + 5; checkY++) {
                    pos.set(worldX, checkY, worldZ);
                    BlockState cs = level.getBlockState(pos);
                    if (cs.is(BlockTags.LOGS) || cs.is(BlockTags.LEAVES)) {
                        return false;
                    }
                }
            }
        }

        BlockState andesite = Blocks.STONE.defaultBlockState();

        long torSeed = origin.getX() * 341873128712L ^ origin.getZ() * 132897987541L;

        double peakOffsetX = (((torSeed >> 8) & 0xFF) - 128) / 128.0 * 0.4;
        double peakOffsetZ = (((torSeed >> 16) & 0xFF) - 128) / 128.0 * 0.4;

        // place tor
        for (int dx = -radiusX; dx <= radiusX; dx++) {
            for (int dz = -radiusZ; dz <= radiusZ; dz++) {
                int worldX = origin.getX() + dx;
                int worldZ = origin.getZ() + dz;

                double nx = (double) dx / radiusX;
                double nz = (double) dz / radiusZ;
                double horizDist = Math.sqrt(nx * nx + nz * nz);
                if (horizDist > 1.0) continue;

                double shapeNoise =
                        Math.sin(dx * 0.7 + torSeed * 0.00001) * 0.08
                                + Math.sin(dz * 0.8 + torSeed * 0.00002) * 0.08
                                + Math.sin((dx + dz) * 0.5 + torSeed * 0.00004) * 0.06
                                + Math.sin((dx - dz) * 0.6 + torSeed * 0.00005) * 0.06
                                + Math.sin(dx * 1.3 + dz * 0.9 + torSeed * 0.00006) * 0.05;

                if (horizDist + shapeNoise > 1.0) continue;

                // check for trees in this column
                boolean hasTree = false;
                for (int checkY = surfaceY; checkY <= surfaceY + radiusY + 5; checkY++) {
                    pos.set(worldX, checkY, worldZ);
                    if (level.getBlockState(pos).is(BlockTags.LOGS)
                            || level.getBlockState(pos).is(BlockTags.LEAVES)) {
                        hasTree = true;
                        break;
                    }
                }
                if (hasTree) continue;

                double peakNx = nx - peakOffsetX;
                double peakNz = nz - peakOffsetZ;
                double peakDist = Math.min(1.0,
                        Math.sqrt(peakNx * peakNx + peakNz * peakNz));

                double heightWarp =
                        Math.sin(dx * 0.15 + torSeed * 0.00009) * 0.25
                                + Math.sin(dz * 0.12 + torSeed * 0.00010) * 0.25;

                double heightProfile = Math.cos(peakDist * Math.PI * 0.5) + heightWarp;
                int colMaxHeight = (int)(radiusY * Math.max(0.0,
                        Math.min(1.0, heightProfile)));

                if (colMaxHeight <= 0) continue;

                // find actual surface at this column
                int localSurface = surfaceY + radiusY + 1;
                pos.set(worldX, localSurface, worldZ);
                while (localSurface > level.getMinBuildHeight()
                        && level.getBlockState(pos).isAir()) {
                    localSurface--;
                    pos.set(worldX, localSurface, worldZ);
                }

                // underground depth proportional to above-ground height
                int colUnderground = Math.max(1,
                        (colMaxHeight * halfY) / Math.max(1, radiusY));

                for (int dy = -colUnderground; dy <= colMaxHeight; dy++) {
                    int worldY = localSurface + dy;
                    if (worldY <= level.getMinBuildHeight()
                            || worldY >= level.getMaxBuildHeight()) continue;
                    pos.set(worldX, worldY, worldZ);
                    if (isReplaceable(level.getBlockState(pos))) {
                        level.setBlock(pos, andesite, 2);
                    }
                }
            }
        }

        // satellite boulders
        int satelliteCount = 1 + random.nextInt(3);
        for (int s = 0; s < satelliteCount; s++) {
            double angle = random.nextDouble() * Math.PI * 2;
            int satDist = radiusX + 2 + random.nextInt(8);
            int satX = origin.getX() + (int)(Math.cos(angle) * satDist);
            int satZ = origin.getZ() + (int)(Math.sin(angle) * satDist);

            int satSurface = surfaceY + 5;
            pos.set(satX, satSurface, satZ);
            while (satSurface > level.getMinBuildHeight()
                    && level.getBlockState(pos).isAir()) {
                satSurface--;
                pos.set(satX, satSurface, satZ);
            }

            int satRadius = 2 + random.nextInt(5);
            int satHeight = 2 + random.nextInt(4);
            long satSeed = satX * 341873128712L ^ satZ * 132897987541L;

            boolean satHasTree = false;
            for (int dx = -satRadius; dx <= satRadius && !satHasTree; dx++) {
                for (int dz = -satRadius; dz <= satRadius && !satHasTree; dz++) {
                    for (int cy = satSurface; cy <= satSurface + satHeight + 3; cy++) {
                        pos.set(satX + dx, cy, satZ + dz);
                        if (level.getBlockState(pos).is(BlockTags.LOGS)
                                || level.getBlockState(pos).is(BlockTags.LEAVES)) {
                            satHasTree = true;
                            break;
                        }
                    }
                }
            }
            if (satHasTree) continue;

            for (int dx = -satRadius; dx <= satRadius; dx++) {
                for (int dz = -satRadius; dz <= satRadius; dz++) {
                    int worldX = satX + dx;
                    int worldZ = satZ + dz;

                    double snx = (double) dx / satRadius;
                    double snz = (double) dz / satRadius;
                    double sHorizDist = Math.sqrt(snx * snx + snz * snz);
                    if (sHorizDist > 1.0) continue;

                    double sNoise = Math.sin(dx * 1.1 + satSeed * 0.00001) * 0.08
                            + Math.sin(dz * 1.3 + satSeed * 0.00002) * 0.08;
                    if (sHorizDist + sNoise > 1.0) continue;

                    double sHeightProfile = Math.cos(sHorizDist * Math.PI * 0.5);
                    int sColMax = (int)(satHeight * Math.max(0.0, sHeightProfile));
                    if (sColMax <= 0) continue;

                    int sColUnder = Math.max(1,
                            (sColMax * (satHeight / 2)) / Math.max(1, satHeight));

                    int localSurface = satSurface;
                    pos.set(worldX, localSurface, worldZ);
                    while (localSurface > level.getMinBuildHeight()
                            && level.getBlockState(pos).isAir()) {
                        localSurface--;
                        pos.set(worldX, localSurface, worldZ);
                    }

                    for (int dy = -sColUnder; dy <= sColMax; dy++) {
                        int worldY = localSurface + dy;
                        if (worldY <= level.getMinBuildHeight()
                                || worldY >= level.getMaxBuildHeight()) continue;
                        pos.set(worldX, worldY, worldZ);
                        if (isReplaceable(level.getBlockState(pos))) {
                            level.setBlock(pos, andesite, 2);
                        }
                    }
                }
            }
        }

        return true;
    }
}