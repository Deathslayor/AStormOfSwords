package net.darkflameproduction.agotmod.worldgen.feature;

import com.mojang.serialization.Codec;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.block.custom.AbstractReedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

/**
 * Places a full 3-tall reed stack (bottom/middle/top) at once, waterlogging any
 * segment that lands in a water source so reeds can generate right at the water's edge.
 */
public class ReedFeature extends Feature<NoneFeatureConfiguration> {

    // Reeds only take root in a narrow band around sea level, same idea as a real reed bed
    // hugging the shoreline instead of growing anywhere the placer's heightmap happens to land.
    private static final int MIN_Y = 61;
    private static final int MAX_Y = 65;

    public ReedFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos bottom = context.origin();

        if (bottom.getY() < MIN_Y || bottom.getY() > MAX_Y) {
            return false;
        }

        BlockPos middle = bottom.above();
        BlockPos top = bottom.above(2);

        BlockState belowState = level.getBlockState(bottom.below());
        if (!belowState.is(BlockTags.DIRT) && !belowState.is(Blocks.CLAY)) {
            return false;
        }

        BlockState bottomState = level.getBlockState(bottom);
        BlockState middleState = level.getBlockState(middle);
        BlockState topState = level.getBlockState(top);
        // The top segment must break the surface — it may never generate underwater,
        // so only air (not a water source) is acceptable there.
        if (!AbstractReedBlock.isReplaceable(bottomState)
                || !AbstractReedBlock.isReplaceable(middleState)
                || !topState.isAir()) {
            return false;
        }

        level.setBlock(bottom, ModBLocks.REED.get().defaultBlockState()
                .setValue(AbstractReedBlock.WATERLOGGED, bottomState.is(Blocks.WATER)), 2);
        level.setBlock(middle, ModBLocks.REED_MIDDLE.get().defaultBlockState()
                .setValue(AbstractReedBlock.WATERLOGGED, middleState.is(Blocks.WATER)), 2);
        level.setBlock(top, ModBLocks.REED_TOP.get().defaultBlockState(), 2);
        return true;
    }
}