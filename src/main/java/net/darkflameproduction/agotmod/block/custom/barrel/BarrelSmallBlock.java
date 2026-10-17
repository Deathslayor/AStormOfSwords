package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.block.custom.GeoShapes;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/** Small barrel: single block, no multi-block needed. Upright, or lying on its side against a wall. */
public class BarrelSmallBlock extends Block implements EntityBlock {

    public static final EnumProperty<Direction> FACING = BarrelOrientation.FACING;

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

    // Cubes from geo/block/barrel_small_side.geo.json (turned cubes as their bounds), pointing north
    private static final Map<Direction, VoxelShape> LYING_SHAPES = BarrelOrientation.lyingShapes(GeoShapes.fromCubes(new double[][]{
            {2, 3, -6, 3, 10, 12},
            {-5, 3, -6, 3, 10, 12},
            {5, 6, -6, 1, 4, 12},
            {-6, 6, -6, 1, 4, 12},
            {-2, 2, -6, 4, 12, 12},
            {-3, 1, -3, 6, 14, 6},
            {-6, 2, -2, 12, 12, 4},
            {-7, 5, -2, 1, 6, 4},
            {6, 5, -2, 1, 6, 4},
            {-4, 4, -8, 8, 8, 16},
            {-1.6, 6, -8.7, 3, 3, 2},
            {3, 0, -7, 1, 3, 14},
            {-4, 0, -7, 1, 3, 14}
    }, 8, 8));

    public BarrelSmallBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, BarrelOrientation.forPlacement(context));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level,
                                  BlockPos pos, CollisionContext context) {
        return BarrelOrientation.isLying(state) ? LYING_SHAPES.get(state.getValue(FACING)) : SHAPE;
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return BarrelOrientation.rotate(state, rotation);
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return BarrelOrientation.mirror(state, mirror);
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
