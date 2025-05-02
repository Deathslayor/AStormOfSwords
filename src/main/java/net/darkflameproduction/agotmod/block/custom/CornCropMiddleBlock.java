package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

public class CornCropMiddleBlock extends SweetBerryBushBlock {
    public CornCropMiddleBlock(Properties properties) {
        super(properties);
    }


    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;

        if (level.getRawBrightness(pos, 0) >= 9) {
            int age = state.getValue(AGE);
            if (age < 3) {
                if (random.nextInt(5) == 0) {
                    BlockState newState = state.setValue(AGE, age + 1);
                    level.setBlock(pos, newState, 2);

                    // If we just reached age 3, try to place the middle block above
                    if (age + 1 == 3) {
                        BlockPos abovePos = pos.above();
                        if (level.getBlockState(abovePos).isAir()) {
                            level.setBlock(abovePos, ModBLocks.CORN_CROP_TOP.get().defaultBlockState(), 2);
                        }
                    }
                }
            }
        }
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(ModBLocks.CORN_CROP.get());
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.CORN.get()) ;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int i = state.getValue(AGE);
        boolean flag = i == 3;
        if (i > 1) {
            int j = 1 + level.random.nextInt(2);
            popResource(level, pos, new ItemStack(ModItems.CORN.get(), j + (flag ? 1 : 0)));
            level.playSound(
                    null, pos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F
            );
            BlockState blockstate = state.setValue(AGE, Integer.valueOf(1));
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }


}