package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
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
            // Sycamore (still swapped!)
            if (state.is(ModBLocks.SYCAMORE_LOG.get())) return ModBLocks.STRIPPED_SYCAMORE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.SYCAMORE_WOOD.get())) return ModBLocks.STRIPPED_SYCAMORE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            // From here onwards: Normal behavior
            if (state.is(ModBLocks.WEIRWOOD_LOG.get())) return ModBLocks.STRIPPED_WEIRWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.WEIRWOOD_WOOD.get())) return ModBLocks.STRIPPED_WEIRWOOD_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.SENTINEL_LOG.get())) return ModBLocks.STRIPPED_SENTINEL_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.SENTINEL_WOOD.get())) return ModBLocks.STRIPPED_SENTINEL_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.PINE_LOG.get())) return ModBLocks.STRIPPED_PINE_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.PINE_WOOD.get())) return ModBLocks.STRIPPED_PINE_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.IRONWOOD_LOG.get())) return ModBLocks.STRIPPED_IRONWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.IRONWOOD_WOOD.get())) return ModBLocks.STRIPPED_IRONWOOD_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.HAWTHORN_LOG.get())) return ModBLocks.STRIPPED_HAWTHORN_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.HAWTHORN_WOOD.get())) return ModBLocks.STRIPPED_HAWTHORN_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.CHESTNUT_LOG.get())) return ModBLocks.STRIPPED_CHESTNUT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.CHESTNUT_WOOD.get())) return ModBLocks.STRIPPED_CHESTNUT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.CEDAR_LOG.get())) return ModBLocks.STRIPPED_CEDAR_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.CEDAR_WOOD.get())) return ModBLocks.STRIPPED_CEDAR_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.BEECH_LOG.get())) return ModBLocks.STRIPPED_BEECH_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.BEECH_WOOD.get())) return ModBLocks.STRIPPED_BEECH_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.ASH_LOG.get())) return ModBLocks.STRIPPED_ASH_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.ASH_WOOD.get())) return ModBLocks.STRIPPED_ASH_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.BLACKBARK_LOG.get())) return ModBLocks.STRIPPED_BLACKBARK_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.BLACKBARK_WOOD.get())) return ModBLocks.STRIPPED_BLACKBARK_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.ASPEN_LOG.get())) return ModBLocks.STRIPPED_ASPEN_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.ASPEN_WOOD.get())) return ModBLocks.STRIPPED_ASPEN_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));

            if (state.is(ModBLocks.ALDER_LOG.get())) return ModBLocks.STRIPPED_ALDER_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            if (state.is(ModBLocks.ALDER_WOOD.get())) return ModBLocks.STRIPPED_ALDER_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }







}
