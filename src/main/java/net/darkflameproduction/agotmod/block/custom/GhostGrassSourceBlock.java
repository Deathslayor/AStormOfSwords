package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GhostGrassSourceBlock extends SweetBerryBushBlock {

    public GhostGrassSourceBlock(Properties properties) {
        super(properties);
    }




    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;

        if (level.getRawBrightness(pos, 0) >= 9) {
            if (random.nextInt(5) == 0) {
                BlockPos abovePos = pos.above();
                if (level.isEmptyBlock(abovePos)) {
                    if (!level.getBlockState(pos.above(2)).is(ModBLocks.GHOST_GRASS.get())) {
                        level.setBlock(abovePos, ModBLocks.GHOST_GRASS.get().defaultBlockState(), 2);
                    }
                }
            }

            if (random.nextInt(10) == 0) {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

                int verticalOffset = random.nextInt(3) - 1; // Gives -1, 0, or 1

                BlockPos adjacentPos = pos.relative(direction).above(verticalOffset);

                if (level.getBlockState(adjacentPos).is(Blocks.GRASS_BLOCK) || level.isEmptyBlock(adjacentPos)) {
                    BlockState belowAdjacentState = level.getBlockState(adjacentPos.below());
                    if (belowAdjacentState.is(BlockTags.DIRT)) {
                        level.setBlock(adjacentPos, ModBLocks.GHOST_GRASS_BLOCK.get().defaultBlockState(), 2);
                    }
                }
            }
        }
    }
}