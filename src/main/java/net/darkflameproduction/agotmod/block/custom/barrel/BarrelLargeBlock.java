package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.block.custom.GeoShapes;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Large barrel: 3×3×3 blocks.
 * The controller is the block the player places. Upright it is the bottom-centre of the 3×3 footprint:
 * offsets (-1..1, 0..2, -1..1). Lying against a wall it is the bottom-centre of the side against the wall,
 * so the footprint is moved one block out from the wall (the way the barrel points).
 */
public class BarrelLargeBlock extends BarrelControllerBlock {

    public static final EnumProperty<Direction> FACING = BarrelOrientation.FACING;

    // Cubes from geo/block/barrel_large.geo.json; the model is centred on the controller block
    private static final VoxelShape SHAPE = GeoShapes.fromCubes(new double[][]{
            {-14, 4, -14, 28, 41, 28},
            {-16, 10, -16, 32, 29, 32},
            {-2.6, 48, -2.7, 5, 3, 5},
            {-12, 0, -12, 24, 49, 24},
            {-18, 18, -18, 36, 13, 36}
    }, 8, 8);

    // Cubes from geo/block/barrel_large_side.geo.json (turned cubes as their bounds), pointing north, centred on
    // the block; getFullShape moves them onto the barrel's centre
    private static final Map<Direction, VoxelShape> LYING_SHAPES = BarrelOrientation.lyingShapes(GeoShapes.fromCubes(new double[][]{
            {-14, 6, -20, 28, 28, 41},
            {-16, 4, -14, 32, 32, 29},
            {-1.6, 18, -24.7, 4, 4, 3},
            {-12, 8, -23, 24, 24, 47},
            {-17, 3, -6, 34, 34, 13},
            {-15, 1, -16, 3, 4, 33},
            {12, 1, -16, 3, 4, 33},
            {11, 0, -24, 5, 3, 48},
            {-16, 0, -24, 5, 3, 48}
    }, 8, 8));

    private static final Map<Direction, List<BlockPos>> OFFSETS = buildOffsets();

    private static Map<Direction, List<BlockPos>> buildOffsets() {
        Map<Direction, List<BlockPos>> map = new EnumMap<>(Direction.class);
        for (Direction facing : FACING.getPossibleValues()) {
            BlockPos centre = centreOffset(facing);
            List<BlockPos> list = new ArrayList<>();
            for (int x = -1; x <= 1; x++) {
                for (int y = 0; y <= 2; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos offset = centre.offset(x, y, z);
                        if (!offset.equals(BlockPos.ZERO)) list.add(offset); // skip controller
                    }
                }
            }
            map.put(facing, List.copyOf(list));
        }
        return map;
    }

    /** Where the bottom-centre of the barrel is relative to the controller (used by the renderer too). */
    public static BlockPos centreOffset(Direction facing) {
        return facing == Direction.UP ? BlockPos.ZERO : BlockPos.ZERO.relative(facing);
    }

    public BarrelLargeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected BlockState getPlacementState(BlockPlaceContext context) {
        return defaultBlockState().setValue(FACING, BarrelOrientation.forPlacement(context));
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return BarrelOrientation.rotate(state, rotation);
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return BarrelOrientation.mirror(state, mirror);
    }

    @Override
    public List<BlockPos> getStructureOffsets(BlockState state) { return OFFSETS.get(state.getValue(FACING)); }

    @Override
    public Block getDummyBlock() { return ModBLocks.BARREL_DUMMY.get(); }

    @Override
    protected VoxelShape getFullShape(BlockState state) {
        Direction facing = state.getValue(FACING);
        if (facing == Direction.UP) return SHAPE;
        BlockPos centre = centreOffset(facing);
        return LYING_SHAPES.get(facing).move(centre.getX(), 0, centre.getZ());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BarrelLargeBlockEntity(pos, state);
    }
}
