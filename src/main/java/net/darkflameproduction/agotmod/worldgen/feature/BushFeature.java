package net.darkflameproduction.agotmod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.darkflameproduction.agotmod.worldgen.feature.configuration.BushConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class BushFeature extends Feature<BushConfiguration> {

    public BushFeature(Codec<BushConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BushConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        var random = context.random();
        BushConfiguration config = context.config();

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        // find surface at origin
        int surfaceY = origin.getY();
        pos.set(origin.getX(), surfaceY, origin.getZ());
        while (surfaceY > level.getMinBuildHeight() && level.getBlockState(pos).isAir()) {
            surfaceY--;
            pos.set(origin.getX(), surfaceY, origin.getZ());
        }

        if (surfaceY <= level.getMinBuildHeight()) return false;

        // don't place in water or on non-solid ground
        BlockState groundState = level.getBlockState(pos);
        if (!groundState.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)) {
            return false;
        }

        // bush height — 1 to 4 blocks tall above ground
        // if height is 4, the 4th block is a leaf block not wood
        int bushHeight = 1 + random.nextInt(3);

        // base radius for leaves — 1 to 2 blocks
        int baseRadius = 1 + random.nextInt(3);

        // wood state — oriented upward
        BlockState woodState = config.woodBlock().defaultBlockState();
        if (woodState.hasProperty(RotatedPillarBlock.AXIS)) {
            woodState = woodState.setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
        }

        // leaves state — persistent so they never decay
        BlockState leavesState = config.leavesBlock().defaultBlockState();
        if (leavesState.hasProperty(LeavesBlock.PERSISTENT)) {
            leavesState = leavesState.setValue(LeavesBlock.PERSISTENT, true);
        }

        // check for existing trees above — don't place inside another tree
        for (int checkY = surfaceY + 1; checkY <= surfaceY + bushHeight + 2; checkY++) {
            pos.set(origin.getX(), checkY, origin.getZ());
            if (level.getBlockState(pos).is(BlockTags.LOGS)) return false;
        }

        // wood stem height — if bush is 4 tall the top block is leaves not wood
        int woodHeight = bushHeight == 3 ? 2 : bushHeight;

        // place central wood stem — always starts at surfaceY + 1 (on the floor)
        for (int dy = 1; dy <= woodHeight; dy++) {
            int worldY = surfaceY + dy;
            if (worldY >= level.getMaxBuildHeight()) break;
            pos.set(origin.getX(), worldY, origin.getZ());
            if (level.getBlockState(pos).isAir() || isReplaceable(level.getBlockState(pos))) {
                level.setBlock(pos, woodState, 2);
            }
        }

        // place leaves — always start at ground level (surfaceY + 1)
        // spread outward at each layer, narrowing toward top
        for (int dy = 0; dy <= bushHeight; dy++) {
            int worldY = surfaceY + dy;
            if (worldY >= level.getMaxBuildHeight()) break;

            // layer 0 (ground level) starts with base radius
            // layers shrink as we go up
            double layerProgress = (double) dy / bushHeight;
            int layerRadius;
            if (dy == 0) {
                // ground level — full base radius for leaves
                layerRadius = baseRadius;
            } else {
                // shrink toward top
                layerRadius = (int) Math.ceil(baseRadius * (1.0 - layerProgress * 0.8));
            }

            for (int dx = -layerRadius; dx <= layerRadius; dx++) {
                for (int dz = -layerRadius; dz <= layerRadius; dz++) {
                    int worldX = origin.getX() + dx;
                    int worldZ = origin.getZ() + dz;

                    // circular shape
                    double dist = Math.sqrt(dx * dx + dz * dz);
                    if (dist > layerRadius + 0.5) continue;

                    // skip corners randomly for irregular blob shape
                    if (dist > layerRadius - 0.5 && random.nextFloat() < 0.4f) continue;

                    // skip center column where wood stem is placed
                    // except for the top block if bushHeight == 4
                    if (dx == 0 && dz == 0 && dy <= woodHeight) continue;

                    pos.set(worldX, worldY, worldZ);
                    BlockState existing = level.getBlockState(pos);

                    if (existing.isAir() || isReplaceable(existing)) {
                        level.setBlock(pos, leavesState, 2);
                    }
                }
            }
        }

        return true;
    }

    private static boolean isReplaceable(BlockState state) {
        return state.is(BlockTags.LEAVES)
                || state.is(BlockTags.SMALL_FLOWERS)
                || state.is(BlockTags.TALL_FLOWERS)
                || state.is(net.minecraft.world.level.block.Blocks.SHORT_GRASS)
                || state.is(net.minecraft.world.level.block.Blocks.TALL_GRASS)
                || state.is(net.minecraft.world.level.block.Blocks.FERN)
                || state.is(net.minecraft.world.level.block.Blocks.LARGE_FERN)
                || state.is(net.minecraft.world.level.block.Blocks.DEAD_BUSH);
    }
}