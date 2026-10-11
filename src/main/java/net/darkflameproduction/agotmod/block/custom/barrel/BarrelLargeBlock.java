package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.block.custom.GeoShapes;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Large barrel: 3×3×3 blocks.
 * The controller is the block the player places: the bottom-centre of the 3×3 footprint.
 * Occupies offsets (-1..1, 0..2, -1..1) minus the controller itself.
 */
public class BarrelLargeBlock extends BarrelControllerBlock {

    // Cubes from geo/block/barrel_large.geo.json; the model is centred on the controller block
    private static final VoxelShape SHAPE = GeoShapes.fromCubes(new double[][]{
            {2, 4, -15, 13, 32, 30},
            {-17, 14, -17, 34, 12, 34},
            {-15, 4, -15, 13, 32, 30},
            {12, 2, -2, 6, 35, 4},
            {-17, 2, -2, 5, 35, 4},
            {-19, 12, -3, 5, 17, 6},
            {16, 12, -3, 4, 17, 6},
            {-2, 2, -17, 4, 35, 34},
            {-13, 0, -13, 26, 44, 26},
            {-1, -0.1, -15, 2, 45.1, 30},
            {-15, -0.2, -1, 31, 45.1, 2},
            {-3, 12, -19, 6, 17, 38},
            {-2.6, 44, -2.7, 5, 3, 5}
    }, 8, 8);

    private static final List<BlockPos> OFFSETS = buildOffsets();

    private static List<BlockPos> buildOffsets() {
        List<BlockPos> list = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue; // skip controller
                    list.add(new BlockPos(x, y, z));
                }
            }
        }
        return list;
    }

    public BarrelLargeBlock(Properties properties) {
        super(properties);
    }

    @Override
    public List<BlockPos> getStructureOffsets(BlockState state) { return OFFSETS; }

    @Override
    public Block getDummyBlock() { return ModBLocks.BARREL_DUMMY.get(); }

    @Override
    protected VoxelShape getFullShape(BlockState state) { return SHAPE; }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BarrelLargeBlockEntity(pos, state);
    }
}
