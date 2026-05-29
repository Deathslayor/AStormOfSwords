package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BarrelControllerBlock extends Block implements EntityBlock {

    public BarrelControllerBlock(Properties properties) {
        super(properties);
    }

    public abstract List<BlockPos> getStructureOffsets();
    public abstract Block getDummyBlock();

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level,
                                  BlockPos pos, CollisionContext context) {
        return getCollisionShape(state, level, pos, context);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos origin = context.getClickedPos();
        for (BlockPos offset : getStructureOffsets()) {
            BlockPos target = origin.offset(offset);
            if (!level.getBlockState(target).canBeReplaced(context)) {
                return null;
            }
        }
        return super.getStateForPlacement(context);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos,
                        BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!level.isClientSide) {
            placeStructure(level, pos);
        }
    }

    public void placeStructure(Level level, BlockPos controllerPos) {
        Block dummy = getDummyBlock();
        BlockState dummyState = dummy.defaultBlockState();
        for (BlockPos offset : getStructureOffsets()) {
            BlockPos target = controllerPos.offset(offset);
            level.setBlock(target, dummyState, Block.UPDATE_ALL);
            BlockEntity be = level.getBlockEntity(target);
            if (be instanceof BarrelDummyBlockEntity dummyBE) {
                dummyBE.setControllerPos(controllerPos);
            }
        }
    }

    public void removeStructure(Level level, BlockPos controllerPos, BlockState controllerState) {
        for (BlockPos offset : getStructureOffsets()) {
            BlockPos target = controllerPos.offset(offset);
            if (level.getBlockState(target).getBlock() instanceof BarrelDummyBlock) {
                level.removeBlock(target, false);
            }
        }
        level.removeBlock(controllerPos, false);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            removeStructure(level, pos, state);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos,
                         BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            if (!level.isClientSide) {
                for (BlockPos offset : getStructureOffsets()) {
                    BlockPos target = pos.offset(offset);
                    if (level.getBlockState(target).getBlock() instanceof BarrelDummyBlock) {
                        level.removeBlock(target, false);
                    }
                }
            }
            level.removeBlockEntity(pos);
        }
    }
}
