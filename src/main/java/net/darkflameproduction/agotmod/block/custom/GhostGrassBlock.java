package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.tags.BlockTags;

public class GhostGrassBlock extends SweetBerryBushBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    
    public GhostGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;

        if (level.getRawBrightness(pos, 0) >= 9) {
            if (random.nextInt(5) == 0) {
                BlockPos abovePos = pos.above();
                if (level.isEmptyBlock(abovePos)) {
                    if (!level.getBlockState(pos.above(2)).is(ModBLocks.GHOST_GRASS_MIDDLE.get())) {
                        level.setBlock(abovePos, ModBLocks.GHOST_GRASS_MIDDLE.get().defaultBlockState(), 2);
                    }
                }
            }

            if (random.nextInt(10) == 0) {
                Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);

                int verticalOffset = random.nextInt(3) - 1; // Gives -1, 0, or 1

                BlockPos adjacentPos = pos.relative(direction).above(verticalOffset);

                if (level.getBlockState(adjacentPos).is(Blocks.SHORT_GRASS) || (level.getBlockState(adjacentPos).is(Blocks.TALL_GRASS) || level.isEmptyBlock(adjacentPos))) {
                    BlockState belowAdjacentState = level.getBlockState(adjacentPos.below());
                    if (belowAdjacentState.is(BlockTags.DIRT)) {
                        level.setBlock(adjacentPos, ModBLocks.GHOST_GRASS.get().defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        return belowState.is(BlockTags.DIRT);
    }
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        return new ItemStack(ModBLocks.GHOST_GRASS);
    }
}