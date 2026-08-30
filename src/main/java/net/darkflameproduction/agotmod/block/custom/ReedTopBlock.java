package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;

/**
 * Top segment of the 3-tall reed. Unlike the bottom/middle, the top must always
 * poke out above the waterline — it can never be waterlogged.
 */
public class ReedTopBlock extends AbstractReedBlock {

    public ReedTopBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModBLocks.REED_MIDDLE.get());
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // Never waterlog the top segment, regardless of what fluid is at the clicked position.
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE);
    }

    // always reject water placement — the top segment stays dry permanently
    @Override
    public boolean canPlaceLiquid(Player player, BlockGetter level, BlockPos pos, BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(ModBLocks.REED.get());
    }
}