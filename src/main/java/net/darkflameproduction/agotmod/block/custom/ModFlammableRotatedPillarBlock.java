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
import net.neoforged.neoforge.registries.DeferredBlock;
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

            // Define the wood types array (including sycamore)
            String[] woodTypes = {
                    "sycamore",
                    "sentinel",
                    "pine",
                    "ironwood",
                    "hawthorn",
                    "chestnut",
                    "cedar",
                    "beech",
                    "ash",
                    "blackbark",
                    "aspen",
                    "alder"
            };

            // Process all wood types with a loop
            for (String woodType : woodTypes) {
                String upperWoodType = woodType.toUpperCase();

                // Handle logs
                Block logBlock = getBlockField(upperWoodType + "_LOG").get();
                Block strippedLogBlock = getBlockField("STRIPPED_" + upperWoodType + "_LOG").get();
                if (state.is(logBlock)) {
                    return strippedLogBlock.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                }

                // Handle wood
                Block woodBlock = getBlockField(upperWoodType + "_WOOD").get();
                Block strippedWoodBlock = getBlockField("STRIPPED_" + upperWoodType + "_WOOD").get();
                if (state.is(woodBlock)) {
                    return strippedWoodBlock.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
                }
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    // Helper method to get blocks from ModBLocks by field name
    private static DeferredBlock<Block> getBlockField(String fieldName) {
        try {
            java.lang.reflect.Field field = ModBLocks.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (DeferredBlock<Block>) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access field: " + fieldName, e);
        }
    }







}
