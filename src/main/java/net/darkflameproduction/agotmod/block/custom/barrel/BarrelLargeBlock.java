package net.darkflameproduction.agotmod.block.custom.barrel;

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
 * Controller placed at the bottom-north-west corner (0,0,0).
 * Occupies offsets (0..2, 0..2, 0..2) minus the controller itself.
 */
public class BarrelLargeBlock extends BarrelControllerBlock {

    // Full block hitbox for the controller position
    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    private static final List<BlockPos> OFFSETS = buildOffsets();

    private static List<BlockPos> buildOffsets() {
        List<BlockPos> list = new ArrayList<>();
        for (int x = 0; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                for (int z = 0; z <= 2; z++) {
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
    public List<BlockPos> getStructureOffsets() { return OFFSETS; }

    @Override
    public Block getDummyBlock() { return ModBLocks.BARREL_DUMMY.get(); }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level,
                                           BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BarrelLargeBlockEntity(pos, state);
    }
}
