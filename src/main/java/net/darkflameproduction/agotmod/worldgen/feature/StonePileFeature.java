package net.darkflameproduction.agotmod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

        // small boulder — much smaller than tor
        // always wider than tall, max 3-4 blocks above ground
        int radiusY = 1 + random.nextInt(3);           // 1-3
        int minRadius = (int)(radiusY * 1.5);
        int radiusX = Math.min(6, Math.max(minRadius,
                minRadius + random.nextInt(Math.max(1, 6 - minRadius))));
        int radiusZ = Math.min(6, Math.max(minRadius,
                minRadius + random.nextInt(Math.max(1, 6 - minRadius))));
        int halfY = Math.max(1, radiusY / 2);

        // scan footprint for trees
        for (int dx = -radiusX; dx <= radiusX; dx++) {
            for (int dz = -radiusZ; dz <= radiusZ; dz++) {
                int worldX = origin.getX() + dx;
                int worldZ = origin.getZ() + dz;
                for (int checkY = surfaceY; checkY <= surfaceY + radiusY + 3; checkY++) {
                    pos.set(worldX, checkY, worldZ);
                    BlockState cs = level.getBlockState(pos);
                    if (cs.is(BlockTags.LOGS) || cs.is(BlockTags.LEAVES)) {
                        return false;
                    }
                }
            }
        }

        // 50% stone, 25% andesite, 12.5% diorite, 12.5% granite
        BlockState boulderBlock;
        int stoneType = random.nextInt(4);
        switch (stoneType) {
            case 0, 1 -> boulderBlock = Blocks.STONE.defaultBlockState();
            case 2 -> boulderBlock = Blocks.ANDESITE.defaultBlockState();
            default -> {
                if (random.nextBoolean()) {
                    boulderBlock = Blocks.DIORITE.defaultBlockState();
                } else {
                    boulderBlock = Blocks.ANDESITE.defaultBlockState();
                }
            }
        }

        long pileSeed = origin.getX() * 341873128712L ^ origin.getZ() * 132897987541L;

        // offset peak slightly from center for natural look
        double peakOffsetX = (((pileSeed >> 8) & 0xFF) - 128) / 128.0 * 0.3;
        double peakOffsetZ = (((pileSeed >> 16) & 0xFF) - 128) / 128.0 * 0.3;

        // place boulder using same dome logic as tor
        for (int dx = -radiusX; dx <= radiusX; dx++) {
            for (int dz = -radiusZ; dz <= radiusZ; dz++) {
                int worldX = origin.getX() + dx;
                int worldZ = origin.getZ() + dz;

                double nx = (double) dx / radiusX;
                double nz = (double) dz / radiusZ;
                double horizDist = Math.sqrt(nx * nx + nz * nz);
                if (horizDist > 1.0) continue;

                // shape noise for irregular outline
                double shapeNoise =
                        Math.sin(dx * 0.7 + pileSeed * 0.00001) * 0.08
                                + Math.sin(dz * 0.8 + pileSeed * 0.00002) * 0.08
                                + Math.sin((dx + dz) * 0.5 + pileSeed * 0.00004) * 0.06
                                + Math.sin((dx - dz) * 0.6 + pileSeed * 0.00005) * 0.06;

                if (horizDist + shapeNoise > 1.0) continue;

                // distance from offset peak
                double peakNx = nx - peakOffsetX;
                double peakNz = nz - peakOffsetZ;
                double peakDist = Math.min(1.0,
                        Math.sqrt(peakNx * peakNx + peakNz * peakNz));

                // height warp for natural lopsided shape
                double heightWarp =
                        Math.sin(dx * 0.3 + pileSeed * 0.00009) * 0.2
                                + Math.sin(dz * 0.25 + pileSeed * 0.00010) * 0.2;

                double heightProfile = Math.cos(peakDist * Math.PI * 0.5) + heightWarp;
                int colMaxHeight = (int)(radiusY * Math.max(0.0,
                        Math.min(1.0, heightProfile)));

                // skip columns with no above-ground height
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
                        level.setBlock(pos, boulderBlock, 2);
                    }
                }
            }
        }

        return true;
    }
}