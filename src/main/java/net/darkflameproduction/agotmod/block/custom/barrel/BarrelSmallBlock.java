package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.block.custom.GeoShapes;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/** Small barrel: single block, no multi-block needed. */
public class BarrelSmallBlock extends Block implements EntityBlock {

    // Cubes from geo/block/barrel_small.geo.json, centred on the block
    private static final VoxelShape SHAPE = GeoShapes.fromCubes(new double[][]{
            {2, 2, -5, 3, 12, 10},
            {-6, 6, -6, 12, 4, 12},
            {-5, 2, -5, 3, 12, 10},
            {5, 2, -2, 1, 12, 4},
            {-6, 2, -2, 1, 12, 4},
            {-7, 6, -3, 1, 4, 6},
            {6, 6, -3, 1, 4, 6},
            {-2, 2, -6, 4, 12, 12},
            {-4, 0, -4, 8, 16, 8},
            {-1, -0.1, -5, 2, 16.2, 10},
            {-5, -0.2, -1, 10, 16.4, 2},
            {-3, 5, -7, 6, 6, 14},
            {-1.6, 15, -1.7, 3, 2, 3}
    }, 8, 8);

    public BarrelSmallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level,
                                  BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BarrelSmallBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
