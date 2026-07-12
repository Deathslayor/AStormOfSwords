package net.darkflameproduction.agotmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class QuagmireBlock extends Block implements SimpleWaterloggedBlock {

    private static final VoxelShape SURFACE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final int DAMAGE_TICK_INTERVAL = 20;

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public QuagmireBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity != null) {
                if (entity.getY() < pos.getY() + 0.5D) {
                    return Shapes.empty();
                }
                if (entity.fallDistance > 1.0F) {
                    return Shapes.empty();
                }
                return SURFACE_SHAPE;
            }
        }
        return SURFACE_SHAPE;
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Shapes.block();
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) return;

        entity.makeStuckInBlock(state, new Vec3(0.1D, 0.1D, 0.1D));

        Vec3 velocity = entity.getDeltaMovement();
        if (velocity.y > 0) {
            entity.setDeltaMovement(velocity.x, 0, velocity.z);
        }
        if (velocity.y >= 0) {
            entity.setDeltaMovement(velocity.x, -0.05D, velocity.z);
        }

        if (!level.isClientSide) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4, false, false));

            double headY = entity.getY() + entity.getEyeHeight();
            boolean headSubmerged = headY > pos.getY() && headY < pos.getY() + 1.0D;

            if (headSubmerged) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, false));

                if ((level.getGameTime() % DAMAGE_TICK_INTERVAL) == 0) {
                    if (!livingEntity.hasEffect(MobEffects.WATER_BREATHING) &&
                            !livingEntity.hasEffect(MobEffects.CONDUIT_POWER)) {
                        livingEntity.hurt(level.damageSources().drown(), 2.0F);
                    }
                }
            }
        }
    }

    // always reject water placement — waterlogged stays false permanently
    @Override
    public boolean canPlaceLiquid(net.minecraft.world.entity.player.Player player,
                                  BlockGetter level, BlockPos pos,
                                  BlockState state, Fluid fluid) {
        return false;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // always place as non-waterlogged regardless of surrounding fluid
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        // always empty — no water inside quagmire
        return Fluids.EMPTY.defaultFluidState();
    }
}