package net.darkflameproduction.agotmod.block.custom.barrel;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * Invisible filler block of a multi-block barrel. Its hitbox and particles come from
 * the controller barrel it belongs to.
 */
public class BarrelDummyBlock extends Block implements EntityBlock {
    /** How far a dummy can be from its barrel's controller (the large barrel is 3x3x3, controller bottom centre). */
    private static final int REACH = 2;

    public BarrelDummyBlock(Properties properties) {
        super(properties);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.INVISIBLE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level,
                                  BlockPos pos, CollisionContext context) {
        BlockPos controllerPos = getControllerPos(level, pos);
        if (controllerPos != null) {
            BlockState controllerState = level.getBlockState(controllerPos);
            if (controllerState.getBlock() instanceof BarrelControllerBlock controller) {
                return controller.getOutlineShapeForOffset(controllerState, pos.subtract(controllerPos));
            }
        }
        return Shapes.block();
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level,
                                           BlockPos pos, CollisionContext context) {
        BlockPos controllerPos = getControllerPos(level, pos);
        if (controllerPos != null) {
            BlockState controllerState = level.getBlockState(controllerPos);
            if (controllerState.getBlock() instanceof BarrelControllerBlock controller) {
                return controller.getCollisionShapeForOffset(controllerState, pos.subtract(controllerPos));
            }
        }
        return Shapes.block();
    }

    /**
     * The barrel this dummy belongs to, or null if there is none. The saved position is only trusted when a
     * barrel there really covers this block: a dummy placed from a structure template still holds the
     * position its barrel had in the world the template was saved in, which can be anywhere - reading it
     * would point at the wrong block, or crash the client's chunk renderer, which only sees nearby blocks.
     * Otherwise the barrel is looked for among the nearby blocks.
     */
    @Nullable
    public static BlockPos getControllerPos(BlockGetter level, BlockPos pos) {
        BlockPos saved = level.getBlockEntity(pos) instanceof BarrelDummyBlockEntity dummy ? dummy.getControllerPos() : null;
        if (saved != null && isControllerOf(level, saved, pos)) return saved;
        return findController(level, pos);
    }

    /** Whether the barrel at controllerPos has a dummy at pos. */
    private static boolean isControllerOf(BlockGetter level, BlockPos controllerPos, BlockPos pos) {
        BlockPos offset = pos.subtract(controllerPos);
        if (Math.abs(offset.getX()) > REACH || Math.abs(offset.getY()) > REACH || Math.abs(offset.getZ()) > REACH) {
            return false;
        }
        BlockState state = level.getBlockState(controllerPos);
        return state.getBlock() instanceof BarrelControllerBlock controller
                && controller.getStructureOffsets(state).contains(offset);
    }

    @Nullable
    private static BlockPos findController(BlockGetter level, BlockPos pos) {
        for (BlockPos candidate : BlockPos.betweenClosed(pos.offset(-REACH, -REACH, -REACH), pos.offset(REACH, REACH, REACH))) {
            if (!candidate.equals(pos) && isControllerOf(level, candidate, pos)) return candidate.immutable();
        }
        return null;
    }

    /** Points a dummy whose saved barrel position is wrong (see getControllerPos) at its real barrel. */
    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, net.minecraft.util.RandomSource random) {
        if (level.getBlockEntity(pos) instanceof BarrelDummyBlockEntity dummy
                && level.hasChunksAt(pos.offset(-REACH, -REACH, -REACH), pos.offset(REACH, REACH, REACH))) {
            BlockPos controllerPos = getControllerPos(level, pos);
            if (controllerPos != null && !controllerPos.equals(dummy.getControllerPos())) {
                dummy.setControllerPos(controllerPos);
            }
        }
    }

    /** The state of the barrel this dummy belongs to, or null if it can't be found. */
    @Nullable
    public static BlockState getControllerState(BlockGetter level, BlockPos pos) {
        BlockPos controllerPos = getControllerPos(level, pos);
        if (controllerPos == null) return null;
        BlockState controllerState = level.getBlockState(controllerPos);
        return controllerState.getBlock() instanceof BarrelControllerBlock ? controllerState : null;
    }

    // ── Particles: use the barrel's texture instead of this invisible block ──

    @Override
    public boolean addRunningEffects(BlockState state, Level level, BlockPos pos, Entity entity) {
        BlockState controllerState = getControllerState(level, pos);
        if (controllerState != null) {
            Vec3 motion = entity.getDeltaMovement();
            double x = entity.getX() + (level.random.nextDouble() - 0.5) * entity.getBbWidth();
            double z = entity.getZ() + (level.random.nextDouble() - 0.5) * entity.getBbWidth();
            level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, controllerState),
                    x, entity.getY() + 0.1, z, motion.x * -4.0, 1.5, motion.z * -4.0);
        }
        return true;
    }

    @Override
    public boolean addLandingEffects(BlockState state1, ServerLevel level, BlockPos pos,
                                     BlockState state2, LivingEntity entity, int numberOfParticles) {
        BlockState controllerState = getControllerState(level, pos);
        if (controllerState != null) {
            level.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, controllerState),
                    entity.getX(), entity.getY(), entity.getZ(), numberOfParticles, 0.0, 0.0, 0.0, 0.15);
        }
        return true;
    }

    @Override
    protected void spawnDestroyParticles(Level level, Player player, BlockPos pos, BlockState state) {
        // Handled for the whole structure by BarrelControllerBlock.removeStructure
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BarrelDummyBlockEntity(pos, state);
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            BlockPos controllerPos = getControllerPos(level, pos);
            if (controllerPos != null) {
                BlockState controllerState = level.getBlockState(controllerPos);
                if (controllerState.getBlock() instanceof BarrelControllerBlock controller) {
                    controller.removeStructure(level, controllerPos, controllerState);
                }
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos,
                         BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            level.removeBlockEntity(pos);
        }
    }
}
