package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.tags.BlockTags;

public class GhostGrassBlock extends SweetBerryBushBlock {
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    
    public GhostGrassBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        int i = state.getValue(AGE);
        if (i > 1) {
            BlockState blockstate = state.setValue(AGE, Integer.valueOf(1));
            level.setBlock(pos, blockstate, 2);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockstate));
            return InteractionResult.SUCCESS;
        } else {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isAreaLoaded(pos, 1)) return;

        if (level.getRawBrightness(pos, 0) >= 9) {
            BlockState aboveState = level.getBlockState(pos.above());
            if (aboveState.isAir() || aboveState.is(BlockTags.FLOWERS) ||
                    aboveState.is(Blocks.TALL_GRASS) || aboveState.is(Blocks.SHORT_GRASS)) {
                level.setBlock(pos.above(), ModBLocks.GHOST_GRASS_MIDDLE.get().defaultBlockState(), 2);
            }

            // Try to convert block below to ghost grass
            BlockState belowState = level.getBlockState(pos.below());
            if (belowState.is(BlockTags.DIRT)) {
                    level.setBlock(pos.below(), ModBLocks.GHOST_GRASS_BLOCK.get().defaultBlockState(), 2);
                }
            
if (random.nextInt(5) == 0) {
    // Get only horizontal directions
    Direction[] horizontalDirections = {
        Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST
    };
    Direction randomDir = horizontalDirections[random.nextInt(horizontalDirections.length)];
    BlockPos targetPos = pos.relative(randomDir);
    
    // Try placing at target level, one up, or one down
    BlockPos[] possiblePositions = {
        targetPos,
        targetPos.above(),
        targetPos.below()
    };
    
    for (BlockPos tryPos : possiblePositions) {
        BlockState tryState = level.getBlockState(tryPos);
        BlockState belowTryState = level.getBlockState(tryPos.below());
        
        if (belowTryState.is(BlockTags.DIRT) &&
                (tryState.isAir() || tryState.is(BlockTags.FLOWERS) ||
                        tryState.is(Blocks.TALL_GRASS) || tryState.is(Blocks.SHORT_GRASS))) {
            level.setBlock(tryPos, ModBLocks.GHOST_GRASS.get().defaultBlockState(), 2);
            break; // Stop after successfully placing one block
        }
    }
}
            }
        }
        
        @Override
        public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            BlockState belowState = level.getBlockState(pos.below());
            return belowState.is(BlockTags.DIRT) || belowState.is(ModBLocks.GHOST_GRASS_BLOCK.get());
    }
    public ItemStack getCloneItemStack(BlockGetter world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.WHEAT_SEEDS);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75, 0.8F));
        }
    }
}