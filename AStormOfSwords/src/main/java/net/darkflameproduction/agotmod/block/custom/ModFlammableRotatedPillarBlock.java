package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(@NotNull BlockState state, @NotNull UseOnContext context, @NotNull ItemAbility toolAction, boolean simulate) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            // Handle weirwood separately
            if (state.is(ModBLocks.WEIRWOOD_LOG.get()))
                return ModBLocks.STRIPPED_WEIRWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.WEIRWOOD_WOOD.get()))
                return ModBLocks.STRIPPED_WEIRWOOD_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            // Get the current block
            Block currentBlock = state.getBlock();

            // Check if it's a log that needs to be stripped
            for (String woodType : ModBLocks.LOGS.keySet()) {
                if (ModBLocks.LOGS.get(woodType).get() == currentBlock) {
                    // This is a log, return the corresponding stripped log
                    Block strippedLog = ModBLocks.STRIPPED_LOGS.get(woodType).get();
                    return strippedLog.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                }
            }

            // Check if it's a wood block that needs to be stripped
            for (String woodType : ModBLocks.WOODS.keySet()) {
                if (ModBLocks.WOODS.get(woodType).get() == currentBlock) {
                    // This is a wood block, return the corresponding stripped wood
                    Block strippedWood = ModBLocks.STRIPPED_WOODS.get(woodType).get();
                    return strippedWood.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                }
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}