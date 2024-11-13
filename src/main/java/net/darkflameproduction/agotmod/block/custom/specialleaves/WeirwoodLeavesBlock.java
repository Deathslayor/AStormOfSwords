package net.darkflameproduction.agotmod.block.custom.specialleaves;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

public class WeirwoodLeavesBlock extends LeavesBlock {
    public WeirwoodLeavesBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 30;
    }

    @Override
    public void animateTick(BlockState p272714, Level p272837, BlockPos p273218, RandomSource p273360) {
        super.animateTick(p272714, p272837, p273218, p273360);
        if (p273360.nextInt(10) == 0) {
            BlockPos blockpos = p273218.below();
            BlockState blockstate = p272837.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(p272837, blockpos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(p272837, p273218, p273360, ParticleTypes.CHERRY_LEAVES);
            }


        }
    }
}
