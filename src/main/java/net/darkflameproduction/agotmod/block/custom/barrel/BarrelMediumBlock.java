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
 * Medium barrel: 2×2×2 blocks.
 * Controller at (0,0,0), dummies at the other 7 positions.
 */
public class BarrelMediumBlock extends BarrelControllerBlock {

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    private static final List<BlockPos> OFFSETS = buildOffsets();

    private static List<BlockPos> buildOffsets() {
        List<BlockPos> list = new ArrayList<>();
        for (int x = 0; x <= 1; x++) {
            for (int y = 0; y <= 1; y++) {
                for (int z = 0; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    list.add(new BlockPos(x, y, z));
                }
            }
        }
        return list;
    }

    public BarrelMediumBlock(Properties properties) {
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
        return new BarrelMediumBlockEntity(pos, state);
    }
}
