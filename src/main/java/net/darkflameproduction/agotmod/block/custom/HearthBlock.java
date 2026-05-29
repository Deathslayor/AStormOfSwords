package net.darkflameproduction.agotmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class HearthBlock extends Block {

    public HearthBlock(Properties properties) {
        super(properties);
    }

    /** Fire on this block never goes out. */
    @Override
    public boolean isFireSource(@NotNull BlockState state, @NotNull LevelReader level,
                                @NotNull BlockPos pos, @NotNull Direction side) {
        return side == Direction.UP;
    }

    @Override
    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                               @NotNull BlockPos pos, @NotNull Direction direction) {
        return false;
    }
}
