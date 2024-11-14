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
import org.jetbrains.annotations.NotNull;

public class WeirwoodLeavesBlock extends LeavesBlock {
    public WeirwoodLeavesBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return 30;
    }

    @Override
    public void animateTick(@NotNull BlockState p272714, @NotNull Level p272837, @NotNull BlockPos p273218, @NotNull RandomSource p273360) {
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
