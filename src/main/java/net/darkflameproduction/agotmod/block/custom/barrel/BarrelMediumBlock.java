package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.block.custom.GeoShapes;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Medium barrel: 2×2×2 blocks.
 * A 2×2 footprint has no centre block, so the barrel is centred on the corner of the
 * placed block nearest to where the player clicked. EAST/SOUTH say which way the
 * structure extends from the controller (the placed block).
 */
public class BarrelMediumBlock extends BarrelControllerBlock {

    public static final BooleanProperty EAST  = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;

    // Cubes from geo/block/barrel_medium.geo.json
    private static final double[][] CUBES = {
            {2, 3, -9, 7, 22, 18},
            {-11, 8, -11, 22, 12, 22},
            {-9, 3, -9, 7, 22, 18},
            {8, 2, -2, 3, 24, 4},
            {-11, 2, -2, 5, 24, 4},
            {-12, 7, -3, 6, 14, 6},
            {6, 7, -3, 6, 14, 6},
            {-2, 2, -11, 4, 24, 22},
            {-8, 0, -8, 16, 28, 16},
            {-1, -0.1, -10, 2, 28.2, 20},
            {-10, -0.2, -1, 20, 28.4, 2},
            {-3, 7, -12, 6, 14, 24},
            {-1.6, 28, -1.7, 3, 2, 3}
    };

    public BarrelMediumBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(EAST, true)
                .setValue(SOUTH, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(EAST, SOUTH);
    }

    @Override
    protected BlockState getPlacementState(BlockPlaceContext context) {
        Vec3 hit = context.getClickLocation();
        BlockPos pos = context.getClickedPos();
        boolean east  = hit.x - pos.getX() >= 0.5;
        boolean south = hit.z - pos.getZ() >= 0.5;
        return defaultBlockState().setValue(EAST, east).setValue(SOUTH, south);
    }

    /**
     * Turns the way the barrel extends with the block, so a barrel in a rotated structure template still
     * covers its dummies. Clockwise 90 turns the extent (x, z) into (-z, x).
     */
    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        boolean east = state.getValue(EAST), south = state.getValue(SOUTH);
        return switch (rotation) {
            case CLOCKWISE_90 -> state.setValue(EAST, !south).setValue(SOUTH, east);
            case CLOCKWISE_180 -> state.setValue(EAST, !east).setValue(SOUTH, !south);
            case COUNTERCLOCKWISE_90 -> state.setValue(EAST, south).setValue(SOUTH, !east);
            default -> state;
        };
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return switch (mirror) {
            case LEFT_RIGHT -> state.setValue(SOUTH, !state.getValue(SOUTH));
            case FRONT_BACK -> state.setValue(EAST, !state.getValue(EAST));
            default -> state;
        };
    }

    /** Model centre relative to the controller, in blocks (used by the renderer too). */
    public static double centreOffsetX(BlockState state) { return state.getValue(EAST)  ? 1.0 : 0.0; }
    public static double centreOffsetZ(BlockState state) { return state.getValue(SOUTH) ? 1.0 : 0.0; }

    @Override
    public List<BlockPos> getStructureOffsets(BlockState state) {
        int dx = state.getValue(EAST)  ? 1 : -1;
        int dz = state.getValue(SOUTH) ? 1 : -1;
        List<BlockPos> list = new ArrayList<>();
        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                for (int z = 0; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    list.add(new BlockPos(x * dx, y, z * dz));
                }
            }
        }
        return list;
    }

    @Override
    public Block getDummyBlock() { return ModBLocks.BARREL_DUMMY.get(); }

    @Override
    protected VoxelShape getFullShape(BlockState state) {
        return GeoShapes.fromCubes(CUBES, centreOffsetX(state) * 16, centreOffsetZ(state) * 16);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BarrelMediumBlockEntity(pos, state);
    }
}
