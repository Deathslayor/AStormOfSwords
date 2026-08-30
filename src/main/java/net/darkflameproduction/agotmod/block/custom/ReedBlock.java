package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Bottom segment of the 3-tall reed. Placing one plants the whole stack at once
 * (mirrors how vanilla double plants place both halves together), and waterlogs
 * whichever segments land in water.
 */
public class ReedBlock extends AbstractReedBlock {

    public ReedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        return belowState.is(BlockTags.DIRT) || belowState.is(Blocks.CLAY);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (level.isClientSide) return;

        BlockPos middlePos = pos.above();
        BlockPos topPos = pos.above(2);
        BlockState middleState = level.getBlockState(middlePos);
        BlockState topState = level.getBlockState(topPos);

        // The top segment must break the surface — it may never end up underwater,
        // so only air (not a water source) is acceptable there.
        if (AbstractReedBlock.isReplaceable(middleState) && topState.isAir()) {
            level.setBlock(middlePos, ModBLocks.REED_MIDDLE.get().defaultBlockState()
                    .setValue(AbstractReedBlock.WATERLOGGED, middleState.is(Blocks.WATER)), 3);
            level.setBlock(topPos, ModBLocks.REED_TOP.get().defaultBlockState(), 3);
        }
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(ModBLocks.REED.get());
    }
}