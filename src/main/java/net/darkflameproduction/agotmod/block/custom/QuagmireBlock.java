package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
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
    private static final VoxelShape FALLING_COLLISION_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    private static final double SUFFOCATION_CHANCE = 0.9D;
    private static final int DAMAGE_TICK_INTERVAL = 20;
    private static final float SPEED_FACTOR = 0.02F;

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
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            if (entity != null) {
                boolean hasQuagmireBelow = level.getBlockState(pos.below()).getBlock() instanceof QuagmireBlock;
                double entityY = entity.getY();
                double blockMiddleY = pos.getY() + 0.5D;
                boolean entityInLowerHalf = entityY < blockMiddleY;

                if (hasQuagmireBelow && entityInLowerHalf) {
                    return Shapes.empty();
                }

                if (entity.fallDistance > 2.5F) {
                    return FALLING_COLLISION_SHAPE;
                }

                boolean canEntityWalkOnQuagmire = entity instanceof LivingEntity &&
                        ((LivingEntity) entity).getAttributeValue(Attributes.MOVEMENT_SPEED) > 0.1F;
                if (canEntityWalkOnQuagmire && !entity.isSteppingCarefully()) {
                    return FALLING_COLLISION_SHAPE;
                }
            }
        }
        return FALLING_COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) return;

        entity.makeStuckInBlock(state, new Vec3(0.8D, 0.5D, 0.8D));

        double headY = entity.getY() + entity.getEyeHeight();
        boolean headFullyInside = headY > pos.getY() && headY < pos.getY() + 1.0D;

        if (headFullyInside) {
            if (!level.isClientSide) {
                int tickCount = (int) (level.getGameTime() % DAMAGE_TICK_INTERVAL);

                if (livingEntity instanceof Player) {
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 100, false, false));
                    livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4, false, false));
                }

                if (tickCount == 0) {
                    if (!livingEntity.hasEffect(MobEffects.WATER_BREATHING) &&
                            !livingEntity.hasEffect(MobEffects.CONDUIT_POWER)) {

                        if (level.random.nextDouble() < SUFFOCATION_CHANCE) {
                            livingEntity.hurt(level.damageSources().drown(), 2.0F);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluid) {
        return false;
    }


    @Override
    public FluidState getFluidState(BlockState state) {
        return Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE);
    }

}