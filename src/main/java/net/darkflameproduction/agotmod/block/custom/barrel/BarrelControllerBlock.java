package net.darkflameproduction.agotmod.block.custom.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Multi-block barrel. The controller is the block the player places; the rest of the
 * structure is filled with invisible {@link BarrelDummyBlock}s at {@link #getStructureOffsets}.
 */
public abstract class BarrelControllerBlock extends Block implements EntityBlock {

    public BarrelControllerBlock(Properties properties) {
        super(properties);
    }

    /** Positions of the dummy blocks relative to the controller, for this controller state. */
    public abstract List<BlockPos> getStructureOffsets(BlockState state);
    public abstract Block getDummyBlock();

    /** The whole barrel model shape, relative to the controller block. */
    protected abstract VoxelShape getFullShape(BlockState state);

    /** State to place before checking the structure fits; override to pick a layout from the click. */
    protected BlockState getPlacementState(BlockPlaceContext context) {
        return defaultBlockState();
    }

    private final Map<BlockState, Map<BlockPos, VoxelShape>> outlineCache   = new ConcurrentHashMap<>();
    private final Map<BlockState, Map<BlockPos, VoxelShape>> collisionCache = new ConcurrentHashMap<>();

    /** Outline/hit shape for the structure cell at {@code offset}: the whole barrel, seen from that cell. */
    public VoxelShape getOutlineShapeForOffset(BlockState state, BlockPos offset) {
        return outlineCache.computeIfAbsent(state, s -> new ConcurrentHashMap<>())
                .computeIfAbsent(offset.immutable(), o ->
                        getFullShape(state).move(-o.getX(), -o.getY(), -o.getZ()));
    }

    /** Collision shape for the structure cell at {@code offset}: only the part of the barrel inside that cell. */
    public VoxelShape getCollisionShapeForOffset(BlockState state, BlockPos offset) {
        return collisionCache.computeIfAbsent(state, s -> new ConcurrentHashMap<>())
                .computeIfAbsent(offset.immutable(), o ->
                        Shapes.join(getOutlineShapeForOffset(state, o), Shapes.block(), BooleanOp.AND).optimize());
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level,
                                  BlockPos pos, CollisionContext context) {
        return getOutlineShapeForOffset(state, BlockPos.ZERO);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level,
                                           BlockPos pos, CollisionContext context) {
        return getCollisionShapeForOffset(state, BlockPos.ZERO);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos origin = context.getClickedPos();
        BlockState state = getPlacementState(context);
        for (BlockPos offset : getStructureOffsets(state)) {
            BlockPos target = origin.offset(offset);
            if (!level.getBlockState(target).canBeReplaced(context)) {
                return null;
            }
        }
        return state;
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos,
                        BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (!level.isClientSide) {
            placeStructure(level, pos, state);
        }
    }

    public void placeStructure(Level level, BlockPos controllerPos, BlockState controllerState) {
        Block dummy = getDummyBlock();
        BlockState dummyState = dummy.defaultBlockState();
        for (BlockPos offset : getStructureOffsets(controllerState)) {
            BlockPos target = controllerPos.offset(offset);
            level.setBlock(target, dummyState, Block.UPDATE_ALL);
            BlockEntity be = level.getBlockEntity(target);
            if (be instanceof BarrelDummyBlockEntity dummyBE) {
                dummyBE.setControllerPos(controllerPos);
            }
        }
    }

    public void removeStructure(Level level, BlockPos controllerPos, BlockState controllerState) {
        // Break particles and sound for the whole barrel, using the barrel's own texture
        level.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, controllerPos, Block.getId(controllerState));
        for (BlockPos offset : getStructureOffsets(controllerState)) {
            BlockPos target = controllerPos.offset(offset);
            if (level.getBlockState(target).getBlock() instanceof BarrelDummyBlock) {
                level.removeBlock(target, false);
            }
        }
        level.removeBlock(controllerPos, false);
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        // Handled for the whole structure in removeStructure
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
                for (BlockPos offset : getStructureOffsets(state)) {
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
