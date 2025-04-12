package net.darkflameproduction.agotmod.block;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.ModFlammableLeaves;
import net.darkflameproduction.agotmod.block.custom.ModFlammablePlanks;
import net.darkflameproduction.agotmod.block.custom.ModFlammableRotatedPillarBlock;
import net.darkflameproduction.agotmod.block.custom.specialleaves.WeirwoodLeavesBlock;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.worldgen.tree.ModTreeGrower;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.*;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;
import java.util.function.Supplier;

import java.util.function.Function;

// ModBLocks class for registering custom blocks
public class ModBLocks {
    // Deferred register for blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AGoTMod.MOD_ID);

    // Method for registering a block and its corresponding item
    private static <T extends Block> @NotNull DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block, properties);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Method for registering a block item
    private static <T extends Block> @NotNull DeferredHolder<Item, ?> registerBlockItem(String name, DeferredBlock<T> block) {
        return ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    // ---------------------------(BLOCKS)--------------------------- //

    // Adding a workstation for villagers
    public static final DeferredBlock<Block> MINT_BLOCK = registerBlock("mint_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));

    // MOD ORES
    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK));

    public static final DeferredBlock<Block> YELLOW_DIAMOND_BLOCK = registerBlock("yellow_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> TRANSPARENT_DIAMOND_BLOCK = registerBlock("transparent_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> AMBER_BLOCK = registerBlock("amber_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> AMBER_ORE = registerBlock("amber_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> AMBER_DEEPSLATE_ORE = registerBlock("amber_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> AMETHYST_ORE = registerBlock("amethyst_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE));

    public static final DeferredBlock<Block> AMETHYST_DEEPSLATE_ORE = registerBlock("amethyst_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE));

    public static final DeferredBlock<Block> BLACK_DIAMOND_BLOCK = registerBlock("black_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> BLOODSTONE_BLOCK = registerBlock("bloodstone_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> BLOODSTONE_ORE = registerBlock("bloodstone_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> BLOODSTONE_DEEPSLATE_ORE = registerBlock("bloodstone_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> CARNELIAN_BLOCK = registerBlock("carnelian_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> CARNELIAN_ORE = registerBlock("carnelian_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> CARNELIAN_DEEPSLATE_ORE = registerBlock("carnelian_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> CHALCEDONY_BLOCK = registerBlock("chalcedony_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> CHALCEDONY_ORE = registerBlock("chalcedony_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> CHALCEDONY_DEEPSLATE_ORE = registerBlock("chalcedony_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> DIAMONDS_ORE = registerBlock("diamonds_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> DIAMONDS_DEEPSLATE_ORE = registerBlock("diamonds_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> GARNET_BLOCK = registerBlock("garnet_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> GARNET_ORE = registerBlock("garnet_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> GARNET_DEEPSLATE_ORE = registerBlock("garnet_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> JADE_BLOCK = registerBlock("jade_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> JADE_ORE = registerBlock("jade_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> JADE_DEEPSLATE_ORE = registerBlock("jade_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> JASPER_BLOCK = registerBlock("jasper_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> JASPER_ORE = registerBlock("jasper_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> JASPER_DEEPSLATE_ORE = registerBlock("jasper_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> MALACHITE_BLOCK = registerBlock("malachite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> MALACHITE_ORE = registerBlock("malachite_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> MALACHITE_DEEPSLATE_ORE = registerBlock("malachite_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> MOONSTONE_BLOCK = registerBlock("moonstone_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> MOONSTONE_ORE = registerBlock("moonstone_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> MOONSTONE_DEEPSLATE_ORE = registerBlock("moonstone_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> ONYX_BLOCK = registerBlock("onyx_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> ONYX_ORE = registerBlock("onyx_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> ONYX_DEEPSLATE_ORE = registerBlock("onyx_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> OPAL_BLOCK = registerBlock("opal_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> OPAL_ORE = registerBlock("opal_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> OPAL_DEEPSLATE_ORE = registerBlock("opal_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> RUBY_DEEPSLATE_ORE = registerBlock("ruby_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> SAPPHIRE_DEEPSLATE_ORE = registerBlock("sapphire_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> TIGERS_EYE_BLOCK = registerBlock("tigers_eye_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> TIGERS_EYE_ORE = registerBlock("tigers_eye_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> TIGERS_EYE_DEEPSLATE_ORE = registerBlock("tigers_eye_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> TOPAZ_BLOCK = registerBlock("topaz_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> TOPAZ_ORE = registerBlock("topaz_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> TOPAZ_DEEPSLATE_ORE = registerBlock("topaz_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> TOURMALINE_ORE = registerBlock("tourmaline_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> TOURMALINE_DEEPSLATE_ORE = registerBlock("tourmaline_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops());

    // TIN BLOCK
    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK));

    // BRONZE BLOCK
    public static final DeferredBlock<Block> BRONZE_BLOCK = registerBlock("bronze_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK));

    // DARK STONE BRICK
    public static final DeferredBlock<Block> DARK_STONE_BRICK = registerBlock("dark_stone_brick", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));

    // KINGS LANDING BRICK LARGE
    public static final DeferredBlock<Block> KINGS_LANDING_BRICK_LARGE = registerBlock("kings_landing_brick_large", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));

    // ROUNDED STONE BRICK
    public static final DeferredBlock<Block> STONE_BRICK_BUT_COOLER = registerBlock("stone_brick_but_cooler", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));

    public static class QuagmireBlock extends Block implements SimpleWaterloggedBlock {
        private static final VoxelShape FALLING_COLLISION_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        private static final double SUFFOCATION_CHANCE = 0.9D;
        private static final int DAMAGE_TICK_INTERVAL = 20;
        // Increased slowdown factor - from 0.1F to 0.02F (5 times slower)
        private static final float SPEED_FACTOR = 0.02F;

        // Add a waterlogged property
        public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

        public QuagmireBlock(Properties properties) {
            super(properties);
            // Register the waterlogged property with a default value of false
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
            return Shapes.empty();
        }

        @Override
        public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            return Shapes.empty();
        }

        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (!(entity instanceof LivingEntity livingEntity)) return;

            // Apply stronger slowdown to match the speedFactor
            entity.makeStuckInBlock(state, new Vec3(0.8D, 0.5D, 0.8D));

            double headY = entity.getY() + entity.getEyeHeight();
            boolean headFullyInside = headY > pos.getY() && headY < pos.getY() + 1.0D;

            if (headFullyInside) {
                if (!level.isClientSide) {
                    int tickCount = (int) (level.getGameTime() % DAMAGE_TICK_INTERVAL);

                    // Apply blindness effect when head is fully inside
                    if (livingEntity instanceof Player) {
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 100, false, false));
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4, false, false));
                    }

                    if (tickCount == 0) {
                        if (!livingEntity.hasEffect(MobEffects.WATER_BREATHING) &&
                                !livingEntity.hasEffect(MobEffects.CONDUIT_POWER)) {

                            if (level.random.nextDouble() < SUFFOCATION_CHANCE) {
                                // Apply vanilla drowning damage
                                livingEntity.hurt(level.damageSources().drown(), 2.0F);
                            }
                        }
                    }
                }
            }
        }

        // Enhanced water resistance - prevent water from replacing this block
        @Override
        public boolean canBeReplaced(BlockState state, Fluid fluid) {
            return false;
        }

        // Prevent block from being waterlogged
        @Override
        public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            return true;
        }

        // Always maintain empty fluid state
        @Override
        public FluidState getFluidState(BlockState state) {
            return Fluids.EMPTY.defaultFluidState();
        }

        // Block water propagation when placing the block
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            return this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE);
        }

        // Override for multiplayer compatibility
    }

    // Register the block with updated properties
    public static final DeferredBlock<Block> QUAGMIRE = registerBlock("quagmire",
            properties -> new QuagmireBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)
                    .sound(SoundType.MUD)
                    .friction(0.8F)
                    .speedFactor(0.1F)  // Change to match the constant defined above
                    .strength(6f)
                    .noOcclusion()  // Important for proper rendering when sinking
                    .isValidSpawn((state, level, pos, type) -> false)  // Prevent mob spawning
                    .pushReaction(PushReaction.BLOCK));  // Prevent pistons from moving it



    // ---------------------------(BLOCKS)--------------------------- //

    // ---------------------------(TREE BLOCKS)--------------------------- //
    //Weirwood
    public static final DeferredBlock<Block> WEIRWOOD_LOG = registerBlock("weirwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> WEIRWOOD_FACE_LOG = registerBlock("weirwood_face_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> WEIRWOOD_WOOD = registerBlock("weirwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_WEIRWOOD_LOG = registerBlock("stripped_weirwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_WEIRWOOD_WOOD = registerBlock("stripped_weirwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> WEIRWOOD_PLANKS = registerBlock("weirwood_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> WEIRWOOD_LEAVES = registerBlock("weirwood_leaves", WeirwoodLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES));
    public static final DeferredBlock<Block> WEIRWOOD_SAPLING = registerBlock("weirwood_sapling", properties -> new SaplingBlock(ModTreeGrower.WEIRWOOD, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> WEIRWOOD_STAIRS = registerBlock("weirwood_stairs",
            properties -> new StairBlock(ModBLocks.WEIRWOOD_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_SLAB = registerBlock("weirwood_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_BUTTON = registerBlock("weirwood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_PRESSURE_PLATE = registerBlock("weirwood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_FENCE = registerBlock("weirwood_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> WEIRWOOD_FENCE_GATE = registerBlock("weirwood_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> WEIRWOOD_WALL = registerBlock("weirwood_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> WEIRWOOD_DOOR = registerBlock("weirwood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> WEIRWOOD_TRAPDOOR = registerBlock("weirwood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //Sycamore
    public static final DeferredBlock<Block> SYCAMORE_LOG = registerBlock("sycamore_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> SYCAMORE_WOOD = registerBlock("sycamore_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SYCAMORE_LOG = registerBlock("sycamore_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SYCAMORE_WOOD = registerBlock("sycamore_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> SYCAMORE_PLANKS = registerBlock("sycamore_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> SYCAMORE_LEAVES = registerBlock("sycamore_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> SYCAMORE_SAPLING = registerBlock("sycamore_sapling", properties -> new SaplingBlock(ModTreeGrower.SYSCAMORE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> SYCAMORE_STAIRS = registerBlock("sycamore_stairs",
            properties -> new StairBlock(ModBLocks.SYCAMORE_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_SLAB = registerBlock("sycamore_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_BUTTON = registerBlock("sycamore_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_PRESSURE_PLATE = registerBlock("sycamore_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_FENCE = registerBlock("sycamore_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SYCAMORE_FENCE_GATE = registerBlock("sycamore_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SYCAMORE_WALL = registerBlock("sycamore_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SYCAMORE_DOOR = registerBlock("sycamore_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> SYCAMORE_TRAPDOOR = registerBlock("sycamore_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //Sentinel
    public static final DeferredBlock<Block> SENTINEL_LOG = registerBlock("sentinel_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> SENTINEL_WOOD = registerBlock("sentinel_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SENTINEL_LOG = registerBlock("sentinel_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SENTINEL_WOOD = registerBlock("sentinel_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> SENTINEL_PLANKS = registerBlock("sentinel_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> SENTINEL_LEAVES = registerBlock("sentinel_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> SENTINEL_SAPLING = registerBlock("sentinel_sapling", properties -> new SaplingBlock(ModTreeGrower.SENTINEL, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> SENTINEL_STAIRS = registerBlock("sentinel_stairs",
            properties -> new StairBlock(ModBLocks.SENTINEL_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_SLAB = registerBlock("sentinel_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_BUTTON = registerBlock("sentinel_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_PRESSURE_PLATE = registerBlock("sentinel_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_FENCE = registerBlock("sentinel_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SENTINEL_FENCE_GATE = registerBlock("sentinel_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SENTINEL_WALL = registerBlock("sentinel_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SENTINEL_DOOR = registerBlock("sentinel_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> SENTINEL_TRAPDOOR = registerBlock("sentinel_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //Pine
    public static final DeferredBlock<Block> PINE_LOG = registerBlock("pine_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG).strength(3f));
    public static final DeferredBlock<Block> PINE_WOOD = registerBlock("pine_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_PINE_LOG = registerBlock("pine_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_PINE_WOOD = registerBlock("pine_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD).strength(3f));
    public static final DeferredBlock<Block> PINE_PLANKS = registerBlock("pine_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    public static final DeferredBlock<Block> PINE_LEAVES = registerBlock("pine_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));
    public static final DeferredBlock<Block> PINE_SAPLING = registerBlock("pine_sapling", properties -> new SaplingBlock(ModTreeGrower.PINE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING));
    public static final DeferredBlock<Block> PINE_STAIRS = registerBlock("pine_stairs",
            properties -> new StairBlock(ModBLocks.PINE_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_SLAB = registerBlock("pine_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_BUTTON = registerBlock("pine_button",
            properties -> new ButtonBlock(BlockSetType.SPRUCE, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.SPRUCE,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_FENCE = registerBlock("pine_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    public static final DeferredBlock<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    public static final DeferredBlock<Block> PINE_WALL = registerBlock("pine_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    public static final DeferredBlock<Block> PINE_DOOR = registerBlock("pine_door",
            properties -> new DoorBlock(BlockSetType.SPRUCE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR));
    public static final DeferredBlock<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.SPRUCE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR));


    //ironwood
    public static final DeferredBlock<Block> IRONWOOD_LOG = registerBlock("ironwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> IRONWOOD_WOOD = registerBlock("ironwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_IRONWOOD_LOG = registerBlock("ironwood_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_IRONWOOD_WOOD = registerBlock("ironwood_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> IRONWOOD_PLANKS = registerBlock("ironwood_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> IRONWOOD_LEAVES = registerBlock("ironwood_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> IRONWOOD_SAPLING = registerBlock("ironwood_sapling", properties -> new SaplingBlock(ModTreeGrower.IRONWOOD, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> IRONWOOD_STAIRS = registerBlock("ironwood_stairs",
            properties -> new StairBlock(ModBLocks.IRONWOOD_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_SLAB = registerBlock("ironwood_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_BUTTON = registerBlock("ironwood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_PRESSURE_PLATE = registerBlock("ironwood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_FENCE = registerBlock("ironwood_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> IRONWOOD_FENCE_GATE = registerBlock("ironwood_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> IRONWOOD_WALL = registerBlock("ironwood_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> IRONWOOD_DOOR = registerBlock("ironwood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> IRONWOOD_TRAPDOOR = registerBlock("ironwood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //hawthorn
    public static final DeferredBlock<Block> HAWTHORN_LOG = registerBlock("hawthorn_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> HAWTHORN_WOOD = registerBlock("hawthorn_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_HAWTHORN_LOG = registerBlock("hawthorn_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_HAWTHORN_WOOD = registerBlock("hawthorn_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> HAWTHORN_PLANKS = registerBlock("hawthorn_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> HAWTHORN_LEAVES = registerBlock("hawthorn_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling", properties -> new SaplingBlock(ModTreeGrower.HAWTHORN, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> HAWTHORN_STAIRS = registerBlock("hawthorn_stairs",
            properties -> new StairBlock(ModBLocks.HAWTHORN_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_SLAB = registerBlock("hawthorn_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_BUTTON = registerBlock("hawthorn_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_PRESSURE_PLATE = registerBlock("hawthorn_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_FENCE = registerBlock("hawthorn_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> HAWTHORN_FENCE_GATE = registerBlock("hawthorn_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> HAWTHORN_WALL = registerBlock("hawthorn_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> HAWTHORN_DOOR = registerBlock("hawthorn_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> HAWTHORN_TRAPDOOR = registerBlock("hawthorn_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //chestnut
    public static final DeferredBlock<Block> CHESTNUT_LOG = registerBlock("chestnut_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> CHESTNUT_WOOD = registerBlock("chestnut_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_LOG = registerBlock("chestnut_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_WOOD = registerBlock("chestnut_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> CHESTNUT_PLANKS = registerBlock("chestnut_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> CHESTNUT_LEAVES = registerBlock("chestnut_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> CHESTNUT_SAPLING = registerBlock("chestnut_sapling", properties -> new SaplingBlock(ModTreeGrower.CHESTNUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> CHESTNUT_STAIRS = registerBlock("chestnut_stairs",
            properties -> new StairBlock(ModBLocks.CHESTNUT_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_SLAB = registerBlock("chestnut_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_BUTTON = registerBlock("chestnut_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_PRESSURE_PLATE = registerBlock("chestnut_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_FENCE = registerBlock("chestnut_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CHESTNUT_FENCE_GATE = registerBlock("chestnut_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CHESTNUT_WALL = registerBlock("chestnut_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CHESTNUT_DOOR = registerBlock("chestnut_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> CHESTNUT_TRAPDOOR = registerBlock("chestnut_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Cedar
    public static final DeferredBlock<Block> CEDAR_LOG = registerBlock("cedar_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> CEDAR_WOOD = registerBlock("cedar_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CEDAR_LOG = registerBlock("cedar_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CEDAR_WOOD = registerBlock("cedar_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> CEDAR_PLANKS = registerBlock("cedar_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> CEDAR_LEAVES = registerBlock("cedar_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> CEDAR_SAPLING = registerBlock("cedar_sapling", properties -> new SaplingBlock(ModTreeGrower.CEDAR, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> CEDAR_STAIRS = registerBlock("cedar_stairs",
            properties -> new StairBlock(ModBLocks.CEDAR_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_SLAB = registerBlock("cedar_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_BUTTON = registerBlock("cedar_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_PRESSURE_PLATE = registerBlock("cedar_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_FENCE = registerBlock("cedar_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CEDAR_FENCE_GATE = registerBlock("cedar_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CEDAR_WALL = registerBlock("cedar_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CEDAR_DOOR = registerBlock("cedar_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> CEDAR_TRAPDOOR = registerBlock("cedar_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Beech
    public static final DeferredBlock<Block> BEECH_LOG = registerBlock("beech_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> BEECH_WOOD = registerBlock("beech_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BEECH_LOG = registerBlock("beech_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BEECH_WOOD = registerBlock("beech_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> BEECH_PLANKS = registerBlock("beech_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> BEECH_LEAVES = registerBlock("beech_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> BEECH_SAPLING = registerBlock("beech_sapling", properties -> new SaplingBlock(ModTreeGrower.BEECH, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> BEECH_STAIRS = registerBlock("beech_stairs",
            properties -> new StairBlock(ModBLocks.BEECH_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_SLAB = registerBlock("beech_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_BUTTON = registerBlock("beech_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_PRESSURE_PLATE = registerBlock("beech_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_FENCE = registerBlock("beech_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BEECH_FENCE_GATE = registerBlock("beech_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BEECH_WALL = registerBlock("beech_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BEECH_DOOR = registerBlock("beech_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> BEECH_TRAPDOOR = registerBlock("beech_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Ash
    public static final DeferredBlock<Block> ASH_LOG = registerBlock("ash_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> ASH_WOOD = registerBlock("ash_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASH_LOG = registerBlock("ash_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASH_WOOD = registerBlock("ash_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> ASH_PLANKS = registerBlock("ash_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> ASH_LEAVES = registerBlock("ash_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> ASH_SAPLING = registerBlock("ash_sapling", properties -> new SaplingBlock(ModTreeGrower.ASH, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> ASH_STAIRS = registerBlock("ash_stairs",
            properties -> new StairBlock(ModBLocks.ASH_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_SLAB = registerBlock("ash_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_BUTTON = registerBlock("ash_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_PRESSURE_PLATE = registerBlock("ash_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_FENCE = registerBlock("ash_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASH_FENCE_GATE = registerBlock("ash_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASH_WALL = registerBlock("ash_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASH_DOOR = registerBlock("ash_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> ASH_TRAPDOOR = registerBlock("ash_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Blackbark
    public static final DeferredBlock<Block> BLACKBARK_LOG = registerBlock("blackbark_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> BLACKBARK_WOOD = registerBlock("blackbark_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BLACKBARK_LOG = registerBlock("blackbark_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BLACKBARK_WOOD = registerBlock("blackbark_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> BLACKBARK_PLANKS = registerBlock("blackbark_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> BLACKBARK_LEAVES = registerBlock("blackbark_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> BLACKBARK_SAPLING = registerBlock("blackbark_sapling", properties -> new SaplingBlock(ModTreeGrower.BLACKBARK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> BLACKBARK_STAIRS = registerBlock("blackbark_stairs",
            properties -> new StairBlock(ModBLocks.BLACKBARK_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_SLAB = registerBlock("blackbark_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_BUTTON = registerBlock("blackbark_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_PRESSURE_PLATE = registerBlock("blackbark_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_FENCE = registerBlock("blackbark_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BLACKBARK_FENCE_GATE = registerBlock("blackbark_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BLACKBARK_WALL = registerBlock("blackbark_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BLACKBARK_DOOR = registerBlock("blackbark_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> BLACKBARK_TRAPDOOR = registerBlock("blackbark_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Aspen
    public static final DeferredBlock<Block> ASPEN_LOG = registerBlock("aspen_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> ASPEN_WOOD = registerBlock("aspen_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_LOG = registerBlock("aspen_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_WOOD = registerBlock("aspen_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> ASPEN_PLANKS = registerBlock("aspen_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> ASPEN_LEAVES = registerBlock("aspen_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> ASPEN_SAPLING = registerBlock("aspen_sapling", properties -> new SaplingBlock(ModTreeGrower.ASPEN, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> ASPEN_STAIRS = registerBlock("aspen_stairs",
            properties -> new StairBlock(ModBLocks.ASPEN_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_SLAB = registerBlock("aspen_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_BUTTON = registerBlock("aspen_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_PRESSURE_PLATE = registerBlock("aspen_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_FENCE = registerBlock("aspen_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASPEN_FENCE_GATE = registerBlock("aspen_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASPEN_WALL = registerBlock("aspen_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASPEN_DOOR = registerBlock("aspen_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> ASPEN_TRAPDOOR = registerBlock("aspen_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Alder
    public static final DeferredBlock<Block> ALDER_LOG = registerBlock("alder_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> ALDER_WOOD = registerBlock("alder_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ALDER_LOG = registerBlock("alder_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ALDER_WOOD = registerBlock("alder_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> ALDER_PLANKS = registerBlock("alder_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> ALDER_LEAVES = registerBlock("alder_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> ALDER_SAPLING = registerBlock("alder_sapling", properties -> new SaplingBlock(ModTreeGrower.ALDER, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> ALDER_STAIRS = registerBlock("alder_stairs",
            properties -> new StairBlock(ModBLocks.ALDER_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_SLAB = registerBlock("alder_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_BUTTON = registerBlock("alder_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_PRESSURE_PLATE = registerBlock("alder_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_FENCE = registerBlock("alder_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ALDER_FENCE_GATE = registerBlock("alder_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ALDER_WALL = registerBlock("alder_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ALDER_DOOR = registerBlock("alder_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> ALDER_TRAPDOOR = registerBlock("alder_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    // ---------------------------(STONE)--------------------------- //

    public static final DeferredBlock<Block> STONE_1_BLOCK = registerBlock("stone_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_1_STAIRS = registerBlock("stone_1_stairs",
            properties -> new StairBlock(ModBLocks.STONE_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_1_SLAB = registerBlock("stone_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_1_WALL = registerBlock("stone_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_2_BLOCK = registerBlock("stone_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_2_STAIRS = registerBlock("stone_2_stairs",
            properties -> new StairBlock(ModBLocks.STONE_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_2_SLAB = registerBlock("stone_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_2_WALL = registerBlock("stone_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_3_BLOCK = registerBlock("stone_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_3_STAIRS = registerBlock("stone_3_stairs",
            properties -> new StairBlock(ModBLocks.STONE_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_3_SLAB = registerBlock("stone_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_3_WALL = registerBlock("stone_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_4_BLOCK = registerBlock("stone_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_4_STAIRS = registerBlock("stone_4_stairs",
            properties -> new StairBlock(ModBLocks.STONE_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_4_SLAB = registerBlock("stone_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_4_WALL = registerBlock("stone_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_6_BLOCK = registerBlock("stone_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_6_STAIRS = registerBlock("stone_6_stairs",
            properties -> new StairBlock(ModBLocks.STONE_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_6_SLAB = registerBlock("stone_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_6_WALL = registerBlock("stone_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_7_BLOCK = registerBlock("stone_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_7_STAIRS = registerBlock("stone_7_stairs",
            properties -> new StairBlock(ModBLocks.STONE_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_7_SLAB = registerBlock("stone_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_7_WALL = registerBlock("stone_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_8_BLOCK = registerBlock("stone_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_8_STAIRS = registerBlock("stone_8_stairs",
            properties -> new StairBlock(ModBLocks.STONE_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_8_SLAB = registerBlock("stone_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_8_WALL = registerBlock("stone_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_9_BLOCK = registerBlock("stone_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_9_STAIRS = registerBlock("stone_9_stairs",
            properties -> new StairBlock(ModBLocks.STONE_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_9_SLAB = registerBlock("stone_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_9_WALL = registerBlock("stone_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_10_BLOCK = registerBlock("stone_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_10_STAIRS = registerBlock("stone_10_stairs",
            properties -> new StairBlock(ModBLocks.STONE_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_10_SLAB = registerBlock("stone_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_10_WALL = registerBlock("stone_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_11_BLOCK = registerBlock("stone_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_11_STAIRS = registerBlock("stone_11_stairs",
            properties -> new StairBlock(ModBLocks.STONE_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_11_SLAB = registerBlock("stone_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_11_WALL = registerBlock("stone_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_12_BLOCK = registerBlock("stone_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_12_STAIRS = registerBlock("stone_12_stairs",
            properties -> new StairBlock(ModBLocks.STONE_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_12_SLAB = registerBlock("stone_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_12_WALL = registerBlock("stone_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_13_BLOCK = registerBlock("stone_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_13_STAIRS = registerBlock("stone_13_stairs",
            properties -> new StairBlock(ModBLocks.STONE_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_13_SLAB = registerBlock("stone_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_13_WALL = registerBlock("stone_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_14_BLOCK = registerBlock("stone_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_14_STAIRS = registerBlock("stone_14_stairs",
            properties -> new StairBlock(ModBLocks.STONE_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_14_SLAB = registerBlock("stone_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_14_WALL = registerBlock("stone_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    // public static final DeferredBlock<Block> STONE_15_BLOCK = registerBlock("stone_15",
    //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    //public static final DeferredBlock<Block> STONE_15_STAIRS = registerBlock("stone_15_stairs",
    //      properties -> new StairBlock(ModBLocks.STONE_15_BLOCK.get().defaultBlockState(),
    //            properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    //public static final DeferredBlock<Block> STONE_15_SLAB = registerBlock("stone_15_slab",
    //      SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    //public static final DeferredBlock<Block> STONE_15_WALL = registerBlock("stone_15_wall",
    //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_16_BLOCK = registerBlock("stone_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_16_STAIRS = registerBlock("stone_16_stairs",
            properties -> new StairBlock(ModBLocks.STONE_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_16_SLAB = registerBlock("stone_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_16_WALL = registerBlock("stone_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_17_BLOCK = registerBlock("stone_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_17_STAIRS = registerBlock("stone_17_stairs",
            properties -> new StairBlock(ModBLocks.STONE_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_17_SLAB = registerBlock("stone_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_17_WALL = registerBlock("stone_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> STONE_18_BLOCK = registerBlock("stone_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_18_STAIRS = registerBlock("stone_18_stairs",
            properties -> new StairBlock(ModBLocks.STONE_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_18_SLAB = registerBlock("stone_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_18_WALL = registerBlock("stone_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 20
    public static final DeferredBlock<Block> STONE_20_BLOCK = registerBlock("stone_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_20_STAIRS = registerBlock("stone_20_stairs",
            properties -> new StairBlock(ModBLocks.STONE_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_20_SLAB = registerBlock("stone_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_20_WALL = registerBlock("stone_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 21
    public static final DeferredBlock<Block> STONE_21_BLOCK = registerBlock("stone_21",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_21_STAIRS = registerBlock("stone_21_stairs",
            properties -> new StairBlock(ModBLocks.STONE_21_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_21_SLAB = registerBlock("stone_21_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_21_WALL = registerBlock("stone_21_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 22
    public static final DeferredBlock<Block> STONE_22_BLOCK = registerBlock("stone_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_22_STAIRS = registerBlock("stone_22_stairs",
            properties -> new StairBlock(ModBLocks.STONE_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_22_SLAB = registerBlock("stone_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_22_WALL = registerBlock("stone_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // STONE 24
    public static final DeferredBlock<Block> STONE_24_BLOCK = registerBlock("stone_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_24_STAIRS = registerBlock("stone_24_stairs",
            properties -> new StairBlock(ModBLocks.STONE_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_24_SLAB = registerBlock("stone_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_24_WALL = registerBlock("stone_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // STONE 25
    public static final DeferredBlock<Block> STONE_25_BLOCK = registerBlock("stone_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_25_STAIRS = registerBlock("stone_25_stairs",
            properties -> new StairBlock(ModBLocks.STONE_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_25_SLAB = registerBlock("stone_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_25_WALL = registerBlock("stone_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // STONE 26
    public static final DeferredBlock<Block> STONE_26_BLOCK = registerBlock("stone_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_26_STAIRS = registerBlock("stone_26_stairs",
            properties -> new StairBlock(ModBLocks.STONE_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_26_SLAB = registerBlock("stone_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_26_WALL = registerBlock("stone_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // STONE 27
    public static final DeferredBlock<Block> STONE_27_BLOCK = registerBlock("stone_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_27_STAIRS = registerBlock("stone_27_stairs",
            properties -> new StairBlock(ModBLocks.STONE_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_27_SLAB = registerBlock("stone_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_27_WALL = registerBlock("stone_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // STONE 28
    public static final DeferredBlock<Block> STONE_28_BLOCK = registerBlock("stone_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_28_STAIRS = registerBlock("stone_28_stairs",
            properties -> new StairBlock(ModBLocks.STONE_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_28_SLAB = registerBlock("stone_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_28_WALL = registerBlock("stone_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // STONE 29
    public static final DeferredBlock<Block> STONE_29_BLOCK = registerBlock("stone_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_29_STAIRS = registerBlock("stone_29_stairs",
            properties -> new StairBlock(ModBLocks.STONE_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_29_SLAB = registerBlock("stone_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_29_WALL = registerBlock("stone_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // STONE 30
    public static final DeferredBlock<Block> STONE_30_BLOCK = registerBlock("stone_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_30_STAIRS = registerBlock("stone_30_stairs",
            properties -> new StairBlock(ModBLocks.STONE_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_30_SLAB = registerBlock("stone_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_30_WALL = registerBlock("stone_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_31_BLOCK = registerBlock("stone_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_31_STAIRS = registerBlock("stone_31_stairs",
            properties -> new StairBlock(ModBLocks.STONE_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_31_SLAB = registerBlock("stone_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_31_WALL = registerBlock("stone_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_32_BLOCK = registerBlock("stone_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_32_STAIRS = registerBlock("stone_32_stairs",
            properties -> new StairBlock(ModBLocks.STONE_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_32_SLAB = registerBlock("stone_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_32_WALL = registerBlock("stone_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_33_BLOCK = registerBlock("stone_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_33_STAIRS = registerBlock("stone_33_stairs",
            properties -> new StairBlock(ModBLocks.STONE_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_33_SLAB = registerBlock("stone_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_33_WALL = registerBlock("stone_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_34_BLOCK = registerBlock("stone_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_34_STAIRS = registerBlock("stone_34_stairs",
            properties -> new StairBlock(ModBLocks.STONE_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_34_SLAB = registerBlock("stone_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_34_WALL = registerBlock("stone_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_35_BLOCK = registerBlock("stone_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_35_STAIRS = registerBlock("stone_35_stairs",
            properties -> new StairBlock(ModBLocks.STONE_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_35_SLAB = registerBlock("stone_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_35_WALL = registerBlock("stone_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_36_BLOCK = registerBlock("stone_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_36_STAIRS = registerBlock("stone_36_stairs",
            properties -> new StairBlock(ModBLocks.STONE_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_36_SLAB = registerBlock("stone_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_36_WALL = registerBlock("stone_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_37_BLOCK = registerBlock("stone_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_37_STAIRS = registerBlock("stone_37_stairs",
            properties -> new StairBlock(ModBLocks.STONE_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_37_SLAB = registerBlock("stone_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_37_WALL = registerBlock("stone_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> STONE_38_BLOCK = registerBlock("stone_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> STONE_38_STAIRS = registerBlock("stone_38_stairs",
            properties -> new StairBlock(ModBLocks.STONE_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> STONE_38_SLAB = registerBlock("stone_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> STONE_38_WALL = registerBlock("stone_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> SSTONE_1_BLOCK = registerBlock("sstone_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_1_STAIRS = registerBlock("sstone_1_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_1_SLAB = registerBlock("sstone_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_1_WALL = registerBlock("sstone_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_2_BLOCK = registerBlock("sstone_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_2_STAIRS = registerBlock("sstone_2_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_2_SLAB = registerBlock("sstone_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_2_WALL = registerBlock("sstone_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_3_BLOCK = registerBlock("sstone_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_3_STAIRS = registerBlock("sstone_3_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_3_SLAB = registerBlock("sstone_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_3_WALL = registerBlock("sstone_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_4_BLOCK = registerBlock("sstone_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_4_STAIRS = registerBlock("sstone_4_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_4_SLAB = registerBlock("sstone_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_4_WALL = registerBlock("sstone_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_5_BLOCK = registerBlock("sstone_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_5_STAIRS = registerBlock("sstone_5_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_5_SLAB = registerBlock("sstone_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_5_WALL = registerBlock("sstone_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_6_BLOCK = registerBlock("sstone_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_6_STAIRS = registerBlock("sstone_6_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_6_SLAB = registerBlock("sstone_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_6_WALL = registerBlock("sstone_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_7_BLOCK = registerBlock("sstone_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_7_STAIRS = registerBlock("sstone_7_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_7_SLAB = registerBlock("sstone_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_7_WALL = registerBlock("sstone_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_8_BLOCK = registerBlock("sstone_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_8_STAIRS = registerBlock("sstone_8_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_8_SLAB = registerBlock("sstone_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_8_WALL = registerBlock("sstone_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_9_BLOCK = registerBlock("sstone_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_9_STAIRS = registerBlock("sstone_9_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_9_SLAB = registerBlock("sstone_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_9_WALL = registerBlock("sstone_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_10_BLOCK = registerBlock("sstone_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_10_STAIRS = registerBlock("sstone_10_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_10_SLAB = registerBlock("sstone_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_10_WALL = registerBlock("sstone_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_11_BLOCK = registerBlock("sstone_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_11_STAIRS = registerBlock("sstone_11_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_11_SLAB = registerBlock("sstone_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_11_WALL = registerBlock("sstone_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_12_BLOCK = registerBlock("sstone_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_12_STAIRS = registerBlock("sstone_12_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_12_SLAB = registerBlock("sstone_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_12_WALL = registerBlock("sstone_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_13_BLOCK = registerBlock("sstone_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_13_STAIRS = registerBlock("sstone_13_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_13_SLAB = registerBlock("sstone_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_13_WALL = registerBlock("sstone_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_14_BLOCK = registerBlock("sstone_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_14_STAIRS = registerBlock("sstone_14_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_14_SLAB = registerBlock("sstone_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_14_WALL = registerBlock("sstone_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
   // public static final DeferredBlock<Block> SSTONE_15_BLOCK = registerBlock("sstone_15",
     //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    //public static final DeferredBlock<Block> SSTONE_15_STAIRS = registerBlock("sstone_15_stairs",
      //      properties -> new StairBlock(ModBLocks.SSTONE_15_BLOCK.get().defaultBlockState(),
        //            properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    //public static final DeferredBlock<Block> SSTONE_15_SLAB = registerBlock("sstone_15_slab",
      //      SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    //public static final DeferredBlock<Block> SSTONE_15_WALL = registerBlock("sstone_15_wall",
      //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_16_BLOCK = registerBlock("sstone_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_16_STAIRS = registerBlock("sstone_16_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_16_SLAB = registerBlock("sstone_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_16_WALL = registerBlock("sstone_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_17_BLOCK = registerBlock("sstone_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_17_STAIRS = registerBlock("sstone_17_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_17_SLAB = registerBlock("sstone_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_17_WALL = registerBlock("sstone_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> SSTONE_18_BLOCK = registerBlock("sstone_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_18_STAIRS = registerBlock("sstone_18_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_18_SLAB = registerBlock("sstone_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_18_WALL = registerBlock("sstone_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    // For 19
    public static final DeferredBlock<Block> SSTONE_19_BLOCK = registerBlock("sstone_19",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_19_STAIRS = registerBlock("sstone_19_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_19_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_19_SLAB = registerBlock("sstone_19_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_19_WALL = registerBlock("sstone_19_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 20
    public static final DeferredBlock<Block> SSTONE_20_BLOCK = registerBlock("sstone_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_20_STAIRS = registerBlock("sstone_20_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_20_SLAB = registerBlock("sstone_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_20_WALL = registerBlock("sstone_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 21
    public static final DeferredBlock<Block> SSTONE_21_BLOCK = registerBlock("sstone_21",
           Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_21_STAIRS = registerBlock("sstone_21_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_21_BLOCK.get().defaultBlockState(),
                  properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
     public static final DeferredBlock<Block> SSTONE_21_SLAB = registerBlock("sstone_21_slab",
         SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
     public static final DeferredBlock<Block> SSTONE_21_WALL = registerBlock("sstone_21_wall",
         WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 22
    public static final DeferredBlock<Block> SSTONE_22_BLOCK = registerBlock("sstone_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> SSTONE_22_STAIRS = registerBlock("sstone_22_stairs",
            properties -> new StairBlock(ModBLocks.SSTONE_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> SSTONE_22_SLAB = registerBlock("sstone_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> SSTONE_22_WALL = registerBlock("sstone_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> RSANDSTONE_1_BLOCK = registerBlock("rsandstone_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_1_STAIRS = registerBlock("rsandstone_1_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_1_SLAB = registerBlock("rsandstone_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_1_WALL = registerBlock("rsandstone_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_2_BLOCK = registerBlock("rsandstone_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_2_STAIRS = registerBlock("rsandstone_2_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_2_SLAB = registerBlock("rsandstone_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_2_WALL = registerBlock("rsandstone_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_3_BLOCK = registerBlock("rsandstone_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_3_STAIRS = registerBlock("rsandstone_3_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_3_SLAB = registerBlock("rsandstone_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_3_WALL = registerBlock("rsandstone_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_4_BLOCK = registerBlock("rsandstone_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_4_STAIRS = registerBlock("rsandstone_4_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_4_SLAB = registerBlock("rsandstone_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_4_WALL = registerBlock("rsandstone_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_5_BLOCK = registerBlock("rsandstone_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_5_STAIRS = registerBlock("rsandstone_5_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_5_SLAB = registerBlock("rsandstone_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_5_WALL = registerBlock("rsandstone_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_6_BLOCK = registerBlock("rsandstone_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_6_STAIRS = registerBlock("rsandstone_6_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_6_SLAB = registerBlock("rsandstone_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_6_WALL = registerBlock("rsandstone_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_7_BLOCK = registerBlock("rsandstone_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_7_STAIRS = registerBlock("rsandstone_7_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_7_SLAB = registerBlock("rsandstone_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_7_WALL = registerBlock("rsandstone_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_8_BLOCK = registerBlock("rsandstone_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_8_STAIRS = registerBlock("rsandstone_8_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_8_SLAB = registerBlock("rsandstone_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_8_WALL = registerBlock("rsandstone_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_9_BLOCK = registerBlock("rsandstone_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_9_STAIRS = registerBlock("rsandstone_9_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_9_SLAB = registerBlock("rsandstone_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_9_WALL = registerBlock("rsandstone_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_10_BLOCK = registerBlock("rsandstone_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_10_STAIRS = registerBlock("rsandstone_10_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_10_SLAB = registerBlock("rsandstone_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_10_WALL = registerBlock("rsandstone_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    public static final DeferredBlock<Block> RSANDSTONE_11_BLOCK = registerBlock("rsandstone_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_11_STAIRS = registerBlock("rsandstone_11_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_11_SLAB = registerBlock("rsandstone_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_11_WALL = registerBlock("rsandstone_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_12_BLOCK = registerBlock("rsandstone_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_12_STAIRS = registerBlock("rsandstone_12_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_12_SLAB = registerBlock("rsandstone_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_12_WALL = registerBlock("rsandstone_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_13_BLOCK = registerBlock("rsandstone_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_13_STAIRS = registerBlock("rsandstone_13_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_13_SLAB = registerBlock("rsandstone_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_13_WALL = registerBlock("rsandstone_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_14_BLOCK = registerBlock("rsandstone_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_14_STAIRS = registerBlock("rsandstone_14_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_14_SLAB = registerBlock("rsandstone_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_14_WALL = registerBlock("rsandstone_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_19_BLOCK = registerBlock("rsandstone_19",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_19_STAIRS = registerBlock("rsandstone_19_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_19_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_19_SLAB = registerBlock("rsandstone_19_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_19_WALL = registerBlock("rsandstone_19_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_20_BLOCK = registerBlock("rsandstone_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_20_STAIRS = registerBlock("rsandstone_20_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_20_SLAB = registerBlock("rsandstone_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_20_WALL = registerBlock("rsandstone_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_21_BLOCK = registerBlock("rsandstone_21",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_21_STAIRS = registerBlock("rsandstone_21_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_21_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_21_SLAB = registerBlock("rsandstone_21_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_21_WALL = registerBlock("rsandstone_21_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_22_BLOCK = registerBlock("rsandstone_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_22_STAIRS = registerBlock("rsandstone_22_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_22_SLAB = registerBlock("rsandstone_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_22_WALL = registerBlock("rsandstone_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> RSANDSTONE_23_BLOCK = registerBlock("rsandstone_23",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_23_STAIRS = registerBlock("rsandstone_23_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_23_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_23_SLAB = registerBlock("rsandstone_23_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_23_WALL = registerBlock("rsandstone_23_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    // RSANDSTONE 24
    public static final DeferredBlock<Block> RSANDSTONE_24_BLOCK = registerBlock("rsandstone_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_24_STAIRS = registerBlock("rsandstone_24_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_24_SLAB = registerBlock("rsandstone_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_24_WALL = registerBlock("rsandstone_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 25
    public static final DeferredBlock<Block> RSANDSTONE_25_BLOCK = registerBlock("rsandstone_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_25_STAIRS = registerBlock("rsandstone_25_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_25_SLAB = registerBlock("rsandstone_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_25_WALL = registerBlock("rsandstone_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 26
    public static final DeferredBlock<Block> RSANDSTONE_26_BLOCK = registerBlock("rsandstone_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_26_STAIRS = registerBlock("rsandstone_26_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_26_SLAB = registerBlock("rsandstone_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_26_WALL = registerBlock("rsandstone_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 27
    public static final DeferredBlock<Block> RSANDSTONE_27_BLOCK = registerBlock("rsandstone_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_27_STAIRS = registerBlock("rsandstone_27_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_27_SLAB = registerBlock("rsandstone_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_27_WALL = registerBlock("rsandstone_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    // RSANDSTONE 28
    public static final DeferredBlock<Block> RSANDSTONE_28_BLOCK = registerBlock("rsandstone_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_28_STAIRS = registerBlock("rsandstone_28_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_28_SLAB = registerBlock("rsandstone_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_28_WALL = registerBlock("rsandstone_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 29
    public static final DeferredBlock<Block> RSANDSTONE_29_BLOCK = registerBlock("rsandstone_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_29_STAIRS = registerBlock("rsandstone_29_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_29_SLAB = registerBlock("rsandstone_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_29_WALL = registerBlock("rsandstone_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 30
    public static final DeferredBlock<Block> RSANDSTONE_30_BLOCK = registerBlock("rsandstone_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_30_STAIRS = registerBlock("rsandstone_30_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_30_SLAB = registerBlock("rsandstone_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_30_WALL = registerBlock("rsandstone_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 31
    public static final DeferredBlock<Block> RSANDSTONE_31_BLOCK = registerBlock("rsandstone_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_31_STAIRS = registerBlock("rsandstone_31_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_31_SLAB = registerBlock("rsandstone_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_31_WALL = registerBlock("rsandstone_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 32
    public static final DeferredBlock<Block> RSANDSTONE_32_BLOCK = registerBlock("rsandstone_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_32_STAIRS = registerBlock("rsandstone_32_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_32_SLAB = registerBlock("rsandstone_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_32_WALL = registerBlock("rsandstone_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 33
    public static final DeferredBlock<Block> RSANDSTONE_33_BLOCK = registerBlock("rsandstone_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_33_STAIRS = registerBlock("rsandstone_33_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_33_SLAB = registerBlock("rsandstone_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_33_WALL = registerBlock("rsandstone_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 34
    public static final DeferredBlock<Block> RSANDSTONE_34_BLOCK = registerBlock("rsandstone_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_34_STAIRS = registerBlock("rsandstone_34_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_34_SLAB = registerBlock("rsandstone_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_34_WALL = registerBlock("rsandstone_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 35
    public static final DeferredBlock<Block> RSANDSTONE_35_BLOCK = registerBlock("rsandstone_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_35_STAIRS = registerBlock("rsandstone_35_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_35_SLAB = registerBlock("rsandstone_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_35_WALL = registerBlock("rsandstone_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 36
    public static final DeferredBlock<Block> RSANDSTONE_36_BLOCK = registerBlock("rsandstone_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_36_STAIRS = registerBlock("rsandstone_36_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_36_SLAB = registerBlock("rsandstone_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_36_WALL = registerBlock("rsandstone_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 37
    public static final DeferredBlock<Block> RSANDSTONE_37_BLOCK = registerBlock("rsandstone_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_37_STAIRS = registerBlock("rsandstone_37_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_37_SLAB = registerBlock("rsandstone_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_37_WALL = registerBlock("rsandstone_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // RSANDSTONE 38
    public static final DeferredBlock<Block> RSANDSTONE_38_BLOCK = registerBlock("rsandstone_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> RSANDSTONE_38_STAIRS = registerBlock("rsandstone_38_stairs",
            properties -> new StairBlock(ModBLocks.RSANDSTONE_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> RSANDSTONE_38_SLAB = registerBlock("rsandstone_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> RSANDSTONE_38_WALL = registerBlock("rsandstone_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    //REDKEEP
    public static final DeferredBlock<Block> REDKEEP_STONE_BLOCK = registerBlock("redkeep_stone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_STONE_STAIRS = registerBlock("redkeep_stone_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_STONE_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_STONE_SLAB = registerBlock("redkeep_stone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_STONE_WALL = registerBlock("redkeep_stone_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_1_BLOCK = registerBlock("redkeep_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_1_STAIRS = registerBlock("redkeep_1_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_1_SLAB = registerBlock("redkeep_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_1_WALL = registerBlock("redkeep_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_2_BLOCK = registerBlock("redkeep_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_2_STAIRS = registerBlock("redkeep_2_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_2_SLAB = registerBlock("redkeep_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_2_WALL = registerBlock("redkeep_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_3_BLOCK = registerBlock("redkeep_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_3_STAIRS = registerBlock("redkeep_3_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_3_SLAB = registerBlock("redkeep_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_3_WALL = registerBlock("redkeep_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_4_BLOCK = registerBlock("redkeep_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_4_STAIRS = registerBlock("redkeep_4_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_4_SLAB = registerBlock("redkeep_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_4_WALL = registerBlock("redkeep_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_5_BLOCK = registerBlock("redkeep_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_5_STAIRS = registerBlock("redkeep_5_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_5_SLAB = registerBlock("redkeep_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_5_WALL = registerBlock("redkeep_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_6_BLOCK = registerBlock("redkeep_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_6_STAIRS = registerBlock("redkeep_6_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_6_SLAB = registerBlock("redkeep_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_6_WALL = registerBlock("redkeep_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_7_BLOCK = registerBlock("redkeep_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_7_STAIRS = registerBlock("redkeep_7_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_7_SLAB = registerBlock("redkeep_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_7_WALL = registerBlock("redkeep_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_8_BLOCK = registerBlock("redkeep_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_8_STAIRS = registerBlock("redkeep_8_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_8_SLAB = registerBlock("redkeep_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_8_WALL = registerBlock("redkeep_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_9_BLOCK = registerBlock("redkeep_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_9_STAIRS = registerBlock("redkeep_9_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_9_SLAB = registerBlock("redkeep_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_9_WALL = registerBlock("redkeep_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_10_BLOCK = registerBlock("redkeep_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_10_STAIRS = registerBlock("redkeep_10_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_10_SLAB = registerBlock("redkeep_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_10_WALL = registerBlock("redkeep_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_11_BLOCK = registerBlock("redkeep_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_11_STAIRS = registerBlock("redkeep_11_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_11_SLAB = registerBlock("redkeep_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_11_WALL = registerBlock("redkeep_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_12_BLOCK = registerBlock("redkeep_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_12_STAIRS = registerBlock("redkeep_12_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_12_SLAB = registerBlock("redkeep_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_12_WALL = registerBlock("redkeep_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_13_BLOCK = registerBlock("redkeep_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_13_STAIRS = registerBlock("redkeep_13_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_13_SLAB = registerBlock("redkeep_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_13_WALL = registerBlock("redkeep_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_14_BLOCK = registerBlock("redkeep_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_14_STAIRS = registerBlock("redkeep_14_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_14_SLAB = registerBlock("redkeep_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_14_WALL = registerBlock("redkeep_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    // public static final DeferredBlock<Block> REDKEEP_15_BLOCK = registerBlock("redkeep_15",
    //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    //public static final DeferredBlock<Block> REDKEEP_15_STAIRS = registerBlock("redkeep_15_stairs",
    //      properties -> new StairBlock(ModBLocks.REDKEEP_15_BLOCK.get().defaultBlockState(),
    //            properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    //public static final DeferredBlock<Block> REDKEEP_15_SLAB = registerBlock("redkeep_15_slab",
    //      SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    //public static final DeferredBlock<Block> REDKEEP_15_WALL = registerBlock("redkeep_15_wall",
    //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_16_BLOCK = registerBlock("redkeep_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_16_STAIRS = registerBlock("redkeep_16_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_16_SLAB = registerBlock("redkeep_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_16_WALL = registerBlock("redkeep_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_17_BLOCK = registerBlock("redkeep_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_17_STAIRS = registerBlock("redkeep_17_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_17_SLAB = registerBlock("redkeep_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_17_WALL = registerBlock("redkeep_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_18_BLOCK = registerBlock("redkeep_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_18_STAIRS = registerBlock("redkeep_18_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_18_SLAB = registerBlock("redkeep_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_18_WALL = registerBlock("redkeep_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    // For 19
    public static final DeferredBlock<Block> REDKEEP_19_BLOCK = registerBlock("redkeep_19",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_19_STAIRS = registerBlock("redkeep_19_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_19_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_19_SLAB = registerBlock("redkeep_19_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_19_WALL = registerBlock("redkeep_19_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 20
    public static final DeferredBlock<Block> REDKEEP_20_BLOCK = registerBlock("redkeep_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_20_STAIRS = registerBlock("redkeep_20_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_20_SLAB = registerBlock("redkeep_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_20_WALL = registerBlock("redkeep_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 21
    public static final DeferredBlock<Block> REDKEEP_21_BLOCK = registerBlock("redkeep_21",
         Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
     public static final DeferredBlock<Block> REDKEEP_21_STAIRS = registerBlock("redkeep_21_stairs",
           properties -> new StairBlock(ModBLocks.REDKEEP_21_BLOCK.get().defaultBlockState(),
                  properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
     public static final DeferredBlock<Block> REDKEEP_21_SLAB = registerBlock("redkeep_21_slab",
         SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
     public static final DeferredBlock<Block> REDKEEP_21_WALL = registerBlock("redkeep_21_wall",
          WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // For 22
    public static final DeferredBlock<Block> REDKEEP_22_BLOCK = registerBlock("redkeep_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_22_STAIRS = registerBlock("redkeep_22_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_22_SLAB = registerBlock("redkeep_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_22_WALL = registerBlock("redkeep_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));
    public static final DeferredBlock<Block> REDKEEP_23_BLOCK = registerBlock("redkeep_23",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_23_STAIRS = registerBlock("redkeep_23_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_23_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_23_SLAB = registerBlock("redkeep_23_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_23_WALL = registerBlock("redkeep_23_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 24
    public static final DeferredBlock<Block> REDKEEP_24_BLOCK = registerBlock("redkeep_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_24_STAIRS = registerBlock("redkeep_24_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_24_SLAB = registerBlock("redkeep_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_24_WALL = registerBlock("redkeep_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 25
    public static final DeferredBlock<Block> REDKEEP_25_BLOCK = registerBlock("redkeep_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_25_STAIRS = registerBlock("redkeep_25_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_25_SLAB = registerBlock("redkeep_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_25_WALL = registerBlock("redkeep_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 26
    public static final DeferredBlock<Block> REDKEEP_26_BLOCK = registerBlock("redkeep_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_26_STAIRS = registerBlock("redkeep_26_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_26_SLAB = registerBlock("redkeep_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_26_WALL = registerBlock("redkeep_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 27
    public static final DeferredBlock<Block> REDKEEP_27_BLOCK = registerBlock("redkeep_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_27_STAIRS = registerBlock("redkeep_27_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_27_SLAB = registerBlock("redkeep_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_27_WALL = registerBlock("redkeep_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 28
    public static final DeferredBlock<Block> REDKEEP_28_BLOCK = registerBlock("redkeep_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_28_STAIRS = registerBlock("redkeep_28_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_28_SLAB = registerBlock("redkeep_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_28_WALL = registerBlock("redkeep_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 29
    public static final DeferredBlock<Block> REDKEEP_29_BLOCK = registerBlock("redkeep_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_29_STAIRS = registerBlock("redkeep_29_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_29_SLAB = registerBlock("redkeep_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_29_WALL = registerBlock("redkeep_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 30
    public static final DeferredBlock<Block> REDKEEP_30_BLOCK = registerBlock("redkeep_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_30_STAIRS = registerBlock("redkeep_30_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_30_SLAB = registerBlock("redkeep_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_30_WALL = registerBlock("redkeep_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 31
    public static final DeferredBlock<Block> REDKEEP_31_BLOCK = registerBlock("redkeep_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_31_STAIRS = registerBlock("redkeep_31_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_31_SLAB = registerBlock("redkeep_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_31_WALL = registerBlock("redkeep_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 32
    public static final DeferredBlock<Block> REDKEEP_32_BLOCK = registerBlock("redkeep_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_32_STAIRS = registerBlock("redkeep_32_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_32_SLAB = registerBlock("redkeep_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_32_WALL = registerBlock("redkeep_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 33
    public static final DeferredBlock<Block> REDKEEP_33_BLOCK = registerBlock("redkeep_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_33_STAIRS = registerBlock("redkeep_33_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_33_SLAB = registerBlock("redkeep_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_33_WALL = registerBlock("redkeep_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 34
    public static final DeferredBlock<Block> REDKEEP_34_BLOCK = registerBlock("redkeep_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_34_STAIRS = registerBlock("redkeep_34_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_34_SLAB = registerBlock("redkeep_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_34_WALL = registerBlock("redkeep_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 35
    public static final DeferredBlock<Block> REDKEEP_35_BLOCK = registerBlock("redkeep_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_35_STAIRS = registerBlock("redkeep_35_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_35_SLAB = registerBlock("redkeep_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_35_WALL = registerBlock("redkeep_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 36
    public static final DeferredBlock<Block> REDKEEP_36_BLOCK = registerBlock("redkeep_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_36_STAIRS = registerBlock("redkeep_36_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_36_SLAB = registerBlock("redkeep_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_36_WALL = registerBlock("redkeep_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 37
    public static final DeferredBlock<Block> REDKEEP_37_BLOCK = registerBlock("redkeep_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_37_STAIRS = registerBlock("redkeep_37_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_37_SLAB = registerBlock("redkeep_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_37_WALL = registerBlock("redkeep_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // REDKEEP 38
    public static final DeferredBlock<Block> REDKEEP_38_BLOCK = registerBlock("redkeep_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_38_STAIRS = registerBlock("redkeep_38_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_38_SLAB = registerBlock("redkeep_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_38_WALL = registerBlock("redkeep_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));


    public static final DeferredBlock<Block> BASALT_2_BLOCK = registerBlock("basalt_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_2_STAIRS = registerBlock("basalt_2_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_2_SLAB = registerBlock("basalt_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_2_WALL = registerBlock("basalt_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_3_BLOCK = registerBlock("basalt_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_3_STAIRS = registerBlock("basalt_3_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_3_SLAB = registerBlock("basalt_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_3_WALL = registerBlock("basalt_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_4_BLOCK = registerBlock("basalt_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_4_STAIRS = registerBlock("basalt_4_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_4_SLAB = registerBlock("basalt_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_4_WALL = registerBlock("basalt_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_5_BLOCK = registerBlock("basalt_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_5_STAIRS = registerBlock("basalt_5_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_5_SLAB = registerBlock("basalt_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_5_WALL = registerBlock("basalt_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_6_BLOCK = registerBlock("basalt_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_6_STAIRS = registerBlock("basalt_6_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_6_SLAB = registerBlock("basalt_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_6_WALL = registerBlock("basalt_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_7_BLOCK = registerBlock("basalt_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_7_STAIRS = registerBlock("basalt_7_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_7_SLAB = registerBlock("basalt_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_7_WALL = registerBlock("basalt_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_8_BLOCK = registerBlock("basalt_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_8_STAIRS = registerBlock("basalt_8_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_8_SLAB = registerBlock("basalt_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_8_WALL = registerBlock("basalt_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_9_BLOCK = registerBlock("basalt_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_9_STAIRS = registerBlock("basalt_9_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_9_SLAB = registerBlock("basalt_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_9_WALL = registerBlock("basalt_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_10_BLOCK = registerBlock("basalt_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_10_STAIRS = registerBlock("basalt_10_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_10_SLAB = registerBlock("basalt_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_10_WALL = registerBlock("basalt_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_11_BLOCK = registerBlock("basalt_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_11_STAIRS = registerBlock("basalt_11_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_11_SLAB = registerBlock("basalt_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_11_WALL = registerBlock("basalt_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_12_BLOCK = registerBlock("basalt_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_12_STAIRS = registerBlock("basalt_12_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_12_SLAB = registerBlock("basalt_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_12_WALL = registerBlock("basalt_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_13_BLOCK = registerBlock("basalt_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_13_STAIRS = registerBlock("basalt_13_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_13_SLAB = registerBlock("basalt_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_13_WALL = registerBlock("basalt_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_14_BLOCK = registerBlock("basalt_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_14_STAIRS = registerBlock("basalt_14_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_14_SLAB = registerBlock("basalt_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_14_WALL = registerBlock("basalt_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    // public static final DeferredBlock<Block> BASALT_15_BLOCK = registerBlock("basalt_15",
    //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    //public static final DeferredBlock<Block> BASALT_15_STAIRS = registerBlock("basalt_15_stairs",
    //      properties -> new StairBlock(ModBLocks.BASALT_15_BLOCK.get().defaultBlockState(),
    //            properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    //public static final DeferredBlock<Block> BASALT_15_SLAB = registerBlock("basalt_15_slab",
    //      SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    //public static final DeferredBlock<Block> BASALT_15_WALL = registerBlock("basalt_15_wall",
    //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_16_BLOCK = registerBlock("basalt_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_16_STAIRS = registerBlock("basalt_16_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_16_SLAB = registerBlock("basalt_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_16_WALL = registerBlock("basalt_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_17_BLOCK = registerBlock("basalt_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_17_STAIRS = registerBlock("basalt_17_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_17_SLAB = registerBlock("basalt_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_17_WALL = registerBlock("basalt_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_18_BLOCK = registerBlock("basalt_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_18_STAIRS = registerBlock("basalt_18_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_18_SLAB = registerBlock("basalt_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_18_WALL = registerBlock("basalt_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    // For 19
    public static final DeferredBlock<Block> BASALT_19_BLOCK = registerBlock("basalt_19",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_19_STAIRS = registerBlock("basalt_19_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_19_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_19_SLAB = registerBlock("basalt_19_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_19_WALL = registerBlock("basalt_19_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // For 20
    public static final DeferredBlock<Block> BASALT_20_BLOCK = registerBlock("basalt_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_20_STAIRS = registerBlock("basalt_20_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_20_SLAB = registerBlock("basalt_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_20_WALL = registerBlock("basalt_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // For 21
    public static final DeferredBlock<Block> BASALT_21_BLOCK = registerBlock("basalt_21",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_21_STAIRS = registerBlock("basalt_21_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_21_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_21_SLAB = registerBlock("basalt_21_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_21_WALL = registerBlock("basalt_21_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // For 22
    public static final DeferredBlock<Block> BASALT_22_BLOCK = registerBlock("basalt_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_22_STAIRS = registerBlock("basalt_22_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_22_SLAB = registerBlock("basalt_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_22_WALL = registerBlock("basalt_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_23_BLOCK = registerBlock("basalt_23",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_23_STAIRS = registerBlock("basalt_23_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_23_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_23_SLAB = registerBlock("basalt_23_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_23_WALL = registerBlock("basalt_23_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // BASALT 24
    public static final DeferredBlock<Block> BASALT_24_BLOCK = registerBlock("basalt_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_24_STAIRS = registerBlock("basalt_24_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_24_SLAB = registerBlock("basalt_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_24_WALL = registerBlock("basalt_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // BASALT 25
    public static final DeferredBlock<Block> BASALT_25_BLOCK = registerBlock("basalt_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_25_STAIRS = registerBlock("basalt_25_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_25_SLAB = registerBlock("basalt_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_25_WALL = registerBlock("basalt_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // BASALT 26
    public static final DeferredBlock<Block> BASALT_26_BLOCK = registerBlock("basalt_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_26_STAIRS = registerBlock("basalt_26_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_26_SLAB = registerBlock("basalt_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_26_WALL = registerBlock("basalt_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // BASALT 27
    public static final DeferredBlock<Block> BASALT_27_BLOCK = registerBlock("basalt_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_27_STAIRS = registerBlock("basalt_27_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_27_SLAB = registerBlock("basalt_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_27_WALL = registerBlock("basalt_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // BASALT 28
    public static final DeferredBlock<Block> BASALT_28_BLOCK = registerBlock("basalt_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_28_STAIRS = registerBlock("basalt_28_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_28_SLAB = registerBlock("basalt_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_28_WALL = registerBlock("basalt_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // BASALT 29
    public static final DeferredBlock<Block> BASALT_29_BLOCK = registerBlock("basalt_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_29_STAIRS = registerBlock("basalt_29_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_29_SLAB = registerBlock("basalt_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_29_WALL = registerBlock("basalt_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    // BASALT 30
    public static final DeferredBlock<Block> BASALT_30_BLOCK = registerBlock("basalt_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_30_STAIRS = registerBlock("basalt_30_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_30_SLAB = registerBlock("basalt_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_30_WALL = registerBlock("basalt_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_31_BLOCK = registerBlock("basalt_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_31_STAIRS = registerBlock("basalt_31_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_31_SLAB = registerBlock("basalt_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_31_WALL = registerBlock("basalt_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_32_BLOCK = registerBlock("basalt_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_32_STAIRS = registerBlock("basalt_32_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_32_SLAB = registerBlock("basalt_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_32_WALL = registerBlock("basalt_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_33_BLOCK = registerBlock("basalt_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_33_STAIRS = registerBlock("basalt_33_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_33_SLAB = registerBlock("basalt_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_33_WALL = registerBlock("basalt_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_34_BLOCK = registerBlock("basalt_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_34_STAIRS = registerBlock("basalt_34_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_34_SLAB = registerBlock("basalt_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_34_WALL = registerBlock("basalt_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_35_BLOCK = registerBlock("basalt_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_35_STAIRS = registerBlock("basalt_35_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_35_SLAB = registerBlock("basalt_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_35_WALL = registerBlock("basalt_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_36_BLOCK = registerBlock("basalt_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_36_STAIRS = registerBlock("basalt_36_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_36_SLAB = registerBlock("basalt_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_36_WALL = registerBlock("basalt_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_37_BLOCK = registerBlock("basalt_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_37_STAIRS = registerBlock("basalt_37_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_37_SLAB = registerBlock("basalt_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_37_WALL = registerBlock("basalt_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BASALT_38_BLOCK = registerBlock("basalt_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f));
    public static final DeferredBlock<Block> BASALT_38_STAIRS = registerBlock("basalt_38_stairs",
            properties -> new StairBlock(ModBLocks.BASALT_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_38_SLAB = registerBlock("basalt_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));
    public static final DeferredBlock<Block> BASALT_38_WALL = registerBlock("basalt_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT));

    public static final DeferredBlock<Block> BRICKS_1_BLOCK = registerBlock("bricks_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_1_STAIRS = registerBlock("bricks_1_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_1_SLAB = registerBlock("bricks_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_1_WALL = registerBlock("bricks_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_3_BLOCK = registerBlock("bricks_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_3_STAIRS = registerBlock("bricks_3_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_3_SLAB = registerBlock("bricks_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_3_WALL = registerBlock("bricks_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_4_BLOCK = registerBlock("bricks_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_4_STAIRS = registerBlock("bricks_4_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_4_SLAB = registerBlock("bricks_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_4_WALL = registerBlock("bricks_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_5_BLOCK = registerBlock("bricks_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_5_STAIRS = registerBlock("bricks_5_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_5_SLAB = registerBlock("bricks_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_5_WALL = registerBlock("bricks_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_6_BLOCK = registerBlock("bricks_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_6_STAIRS = registerBlock("bricks_6_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_6_SLAB = registerBlock("bricks_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_6_WALL = registerBlock("bricks_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_7_BLOCK = registerBlock("bricks_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_7_STAIRS = registerBlock("bricks_7_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_7_SLAB = registerBlock("bricks_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_7_WALL = registerBlock("bricks_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_8_BLOCK = registerBlock("bricks_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_8_STAIRS = registerBlock("bricks_8_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_8_SLAB = registerBlock("bricks_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_8_WALL = registerBlock("bricks_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_9_BLOCK = registerBlock("bricks_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_9_STAIRS = registerBlock("bricks_9_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_9_SLAB = registerBlock("bricks_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_9_WALL = registerBlock("bricks_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_10_BLOCK = registerBlock("bricks_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_10_STAIRS = registerBlock("bricks_10_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_10_SLAB = registerBlock("bricks_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_10_WALL = registerBlock("bricks_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_11_BLOCK = registerBlock("bricks_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_11_STAIRS = registerBlock("bricks_11_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_11_SLAB = registerBlock("bricks_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_11_WALL = registerBlock("bricks_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_12_BLOCK = registerBlock("bricks_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_12_STAIRS = registerBlock("bricks_12_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_12_SLAB = registerBlock("bricks_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_12_WALL = registerBlock("bricks_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_13_BLOCK = registerBlock("bricks_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_13_STAIRS = registerBlock("bricks_13_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_13_SLAB = registerBlock("bricks_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_13_WALL = registerBlock("bricks_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_14_BLOCK = registerBlock("bricks_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_14_STAIRS = registerBlock("bricks_14_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_14_SLAB = registerBlock("bricks_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_14_WALL = registerBlock("bricks_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    // public static final DeferredBlock<Block> BRICKS_15_BLOCK = registerBlock("bricks_15",
    //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    //public static final DeferredBlock<Block> BRICKS_15_STAIRS = registerBlock("bricks_15_stairs",
    //      properties -> new StairBlock(ModBLocks.BRICKS_15_BLOCK.get().defaultBlockState(),
    //            properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    //public static final DeferredBlock<Block> BRICKS_15_SLAB = registerBlock("bricks_15_slab",
    //      SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    //public static final DeferredBlock<Block> BRICKS_15_WALL = registerBlock("bricks_15_wall",
    //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_16_BLOCK = registerBlock("bricks_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_16_STAIRS = registerBlock("bricks_16_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_16_SLAB = registerBlock("bricks_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_16_WALL = registerBlock("bricks_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_17_BLOCK = registerBlock("bricks_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_17_STAIRS = registerBlock("bricks_17_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_17_SLAB = registerBlock("bricks_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_17_WALL = registerBlock("bricks_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_18_BLOCK = registerBlock("bricks_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_18_STAIRS = registerBlock("bricks_18_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_18_SLAB = registerBlock("bricks_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_18_WALL = registerBlock("bricks_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    // For 19
    public static final DeferredBlock<Block> BRICKS_19_BLOCK = registerBlock("bricks_19",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_19_STAIRS = registerBlock("bricks_19_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_19_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_19_SLAB = registerBlock("bricks_19_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_19_WALL = registerBlock("bricks_19_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // For 20
    public static final DeferredBlock<Block> BRICKS_20_BLOCK = registerBlock("bricks_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_20_STAIRS = registerBlock("bricks_20_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_20_SLAB = registerBlock("bricks_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_20_WALL = registerBlock("bricks_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // For 21
    public static final DeferredBlock<Block> BRICKS_21_BLOCK = registerBlock("bricks_21",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_21_STAIRS = registerBlock("bricks_21_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_21_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_21_SLAB = registerBlock("bricks_21_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_21_WALL = registerBlock("bricks_21_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // For 22
    public static final DeferredBlock<Block> BRICKS_22_BLOCK = registerBlock("bricks_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_22_STAIRS = registerBlock("bricks_22_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_22_SLAB = registerBlock("bricks_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_22_WALL = registerBlock("bricks_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));
    public static final DeferredBlock<Block> BRICKS_23_BLOCK = registerBlock("bricks_23",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_23_STAIRS = registerBlock("bricks_23_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_23_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_23_SLAB = registerBlock("bricks_23_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_23_WALL = registerBlock("bricks_23_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // BRICKS 24
    public static final DeferredBlock<Block> BRICKS_24_BLOCK = registerBlock("bricks_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_24_STAIRS = registerBlock("bricks_24_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_24_SLAB = registerBlock("bricks_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_24_WALL = registerBlock("bricks_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // BRICKS 25
    public static final DeferredBlock<Block> BRICKS_25_BLOCK = registerBlock("bricks_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_25_STAIRS = registerBlock("bricks_25_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_25_SLAB = registerBlock("bricks_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_25_WALL = registerBlock("bricks_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // BRICKS 26
    public static final DeferredBlock<Block> BRICKS_26_BLOCK = registerBlock("bricks_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_26_STAIRS = registerBlock("bricks_26_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_26_SLAB = registerBlock("bricks_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_26_WALL = registerBlock("bricks_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // BRICKS 27
    public static final DeferredBlock<Block> BRICKS_27_BLOCK = registerBlock("bricks_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_27_STAIRS = registerBlock("bricks_27_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_27_SLAB = registerBlock("bricks_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_27_WALL = registerBlock("bricks_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // BRICKS 28
    public static final DeferredBlock<Block> BRICKS_28_BLOCK = registerBlock("bricks_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_28_STAIRS = registerBlock("bricks_28_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_28_SLAB = registerBlock("bricks_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_28_WALL = registerBlock("bricks_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // BRICKS 29
    public static final DeferredBlock<Block> BRICKS_29_BLOCK = registerBlock("bricks_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_29_STAIRS = registerBlock("bricks_29_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_29_SLAB = registerBlock("bricks_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_29_WALL = registerBlock("bricks_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    // BRICKS 30
    public static final DeferredBlock<Block> BRICKS_30_BLOCK = registerBlock("bricks_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_30_STAIRS = registerBlock("bricks_30_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_30_SLAB = registerBlock("bricks_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_30_WALL = registerBlock("bricks_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_31_BLOCK = registerBlock("bricks_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_31_STAIRS = registerBlock("bricks_31_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_31_SLAB = registerBlock("bricks_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_31_WALL = registerBlock("bricks_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_32_BLOCK = registerBlock("bricks_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_32_STAIRS = registerBlock("bricks_32_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_32_SLAB = registerBlock("bricks_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_32_WALL = registerBlock("bricks_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_33_BLOCK = registerBlock("bricks_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_33_STAIRS = registerBlock("bricks_33_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_33_SLAB = registerBlock("bricks_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_33_WALL = registerBlock("bricks_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_34_BLOCK = registerBlock("bricks_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_34_STAIRS = registerBlock("bricks_34_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_34_SLAB = registerBlock("bricks_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_34_WALL = registerBlock("bricks_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_35_BLOCK = registerBlock("bricks_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_35_STAIRS = registerBlock("bricks_35_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_35_SLAB = registerBlock("bricks_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_35_WALL = registerBlock("bricks_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_36_BLOCK = registerBlock("bricks_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_36_STAIRS = registerBlock("bricks_36_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_36_SLAB = registerBlock("bricks_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_36_WALL = registerBlock("bricks_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_37_BLOCK = registerBlock("bricks_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_37_STAIRS = registerBlock("bricks_37_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_37_SLAB = registerBlock("bricks_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_37_WALL = registerBlock("bricks_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));

    public static final DeferredBlock<Block> BRICKS_38_BLOCK = registerBlock("bricks_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f));
    public static final DeferredBlock<Block> BRICKS_38_STAIRS = registerBlock("bricks_38_stairs",
            properties -> new StairBlock(ModBLocks.BRICKS_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_STAIRS));
    public static final DeferredBlock<Block> BRICKS_38_SLAB = registerBlock("bricks_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_SLAB));
    public static final DeferredBlock<Block> BRICKS_38_WALL = registerBlock("bricks_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BRICK_WALL));


    public static final DeferredBlock<Block> CALCITE_1_BLOCK = registerBlock("calcite_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_1_STAIRS = registerBlock("calcite_1_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_1_SLAB = registerBlock("calcite_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_1_WALL = registerBlock("calcite_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_2_BLOCK = registerBlock("calcite_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_2_STAIRS = registerBlock("calcite_2_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_2_SLAB = registerBlock("calcite_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_2_WALL = registerBlock("calcite_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_3_BLOCK = registerBlock("calcite_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_3_STAIRS = registerBlock("calcite_3_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_3_SLAB = registerBlock("calcite_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_3_WALL = registerBlock("calcite_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_4_BLOCK = registerBlock("calcite_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_4_STAIRS = registerBlock("calcite_4_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_4_SLAB = registerBlock("calcite_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_4_WALL = registerBlock("calcite_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_5_BLOCK = registerBlock("calcite_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_5_STAIRS = registerBlock("calcite_5_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_5_SLAB = registerBlock("calcite_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_5_WALL = registerBlock("calcite_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_6_BLOCK = registerBlock("calcite_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_6_STAIRS = registerBlock("calcite_6_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_6_SLAB = registerBlock("calcite_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_6_WALL = registerBlock("calcite_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_7_BLOCK = registerBlock("calcite_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_7_STAIRS = registerBlock("calcite_7_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_7_SLAB = registerBlock("calcite_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_7_WALL = registerBlock("calcite_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_8_BLOCK = registerBlock("calcite_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_8_STAIRS = registerBlock("calcite_8_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_8_SLAB = registerBlock("calcite_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_8_WALL = registerBlock("calcite_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_9_BLOCK = registerBlock("calcite_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_9_STAIRS = registerBlock("calcite_9_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_9_SLAB = registerBlock("calcite_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_9_WALL = registerBlock("calcite_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_10_BLOCK = registerBlock("calcite_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_10_STAIRS = registerBlock("calcite_10_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_10_SLAB = registerBlock("calcite_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_10_WALL = registerBlock("calcite_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_11_BLOCK = registerBlock("calcite_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_11_STAIRS = registerBlock("calcite_11_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_11_SLAB = registerBlock("calcite_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_11_WALL = registerBlock("calcite_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_12_BLOCK = registerBlock("calcite_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_12_STAIRS = registerBlock("calcite_12_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_12_SLAB = registerBlock("calcite_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_12_WALL = registerBlock("calcite_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_13_BLOCK = registerBlock("calcite_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_13_STAIRS = registerBlock("calcite_13_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_13_SLAB = registerBlock("calcite_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_13_WALL = registerBlock("calcite_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_14_BLOCK = registerBlock("calcite_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_14_STAIRS = registerBlock("calcite_14_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_14_SLAB = registerBlock("calcite_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_14_WALL = registerBlock("calcite_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    // public static final DeferredBlock<Block> CALCITE_15_BLOCK = registerBlock("calcite_15",
    //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    //public static final DeferredBlock<Block> CALCITE_15_STAIRS = registerBlock("calcite_15_stairs",
    //      properties -> new StairBlock(ModBLocks.CALCITE_15_BLOCK.get().defaultBlockState(),
    //            properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    //public static final DeferredBlock<Block> CALCITE_15_SLAB = registerBlock("calcite_15_slab",
    //      SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    //public static final DeferredBlock<Block> CALCITE_15_WALL = registerBlock("calcite_15_wall",
    //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_16_BLOCK = registerBlock("calcite_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_16_STAIRS = registerBlock("calcite_16_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_16_SLAB = registerBlock("calcite_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_16_WALL = registerBlock("calcite_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_17_BLOCK = registerBlock("calcite_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_17_STAIRS = registerBlock("calcite_17_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_17_SLAB = registerBlock("calcite_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_17_WALL = registerBlock("calcite_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_18_BLOCK = registerBlock("calcite_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_18_STAIRS = registerBlock("calcite_18_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_18_SLAB = registerBlock("calcite_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_18_WALL = registerBlock("calcite_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    // For 19
    public static final DeferredBlock<Block> CALCITE_19_BLOCK = registerBlock("calcite_19",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_19_STAIRS = registerBlock("calcite_19_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_19_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_19_SLAB = registerBlock("calcite_19_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_19_WALL = registerBlock("calcite_19_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // For 20
    public static final DeferredBlock<Block> CALCITE_20_BLOCK = registerBlock("calcite_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_20_STAIRS = registerBlock("calcite_20_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_20_SLAB = registerBlock("calcite_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_20_WALL = registerBlock("calcite_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // For 21
    public static final DeferredBlock<Block> CALCITE_21_BLOCK = registerBlock("calcite_21",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_21_STAIRS = registerBlock("calcite_21_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_21_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_21_SLAB = registerBlock("calcite_21_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_21_WALL = registerBlock("calcite_21_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // For 22
    public static final DeferredBlock<Block> CALCITE_22_BLOCK = registerBlock("calcite_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_22_STAIRS = registerBlock("calcite_22_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_22_SLAB = registerBlock("calcite_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_22_WALL = registerBlock("calcite_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_23_BLOCK = registerBlock("calcite_23",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_23_STAIRS = registerBlock("calcite_23_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_23_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_23_SLAB = registerBlock("calcite_23_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_23_WALL = registerBlock("calcite_23_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 24
    public static final DeferredBlock<Block> CALCITE_24_BLOCK = registerBlock("calcite_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_24_STAIRS = registerBlock("calcite_24_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_24_SLAB = registerBlock("calcite_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_24_WALL = registerBlock("calcite_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 25
    public static final DeferredBlock<Block> CALCITE_25_BLOCK = registerBlock("calcite_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_25_STAIRS = registerBlock("calcite_25_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_25_SLAB = registerBlock("calcite_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_25_WALL = registerBlock("calcite_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 26
    public static final DeferredBlock<Block> CALCITE_26_BLOCK = registerBlock("calcite_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_26_STAIRS = registerBlock("calcite_26_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_26_SLAB = registerBlock("calcite_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_26_WALL = registerBlock("calcite_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 27
    public static final DeferredBlock<Block> CALCITE_27_BLOCK = registerBlock("calcite_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_27_STAIRS = registerBlock("calcite_27_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_27_SLAB = registerBlock("calcite_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_27_WALL = registerBlock("calcite_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 28
    public static final DeferredBlock<Block> CALCITE_28_BLOCK = registerBlock("calcite_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_28_STAIRS = registerBlock("calcite_28_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_28_SLAB = registerBlock("calcite_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_28_WALL = registerBlock("calcite_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 29
    public static final DeferredBlock<Block> CALCITE_29_BLOCK = registerBlock("calcite_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_29_STAIRS = registerBlock("calcite_29_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_29_SLAB = registerBlock("calcite_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_29_WALL = registerBlock("calcite_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 30
    public static final DeferredBlock<Block> CALCITE_30_BLOCK = registerBlock("calcite_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_30_STAIRS = registerBlock("calcite_30_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_30_SLAB = registerBlock("calcite_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_30_WALL = registerBlock("calcite_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // CALCITE 31
    public static final DeferredBlock<Block> CALCITE_31_BLOCK = registerBlock("calcite_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_31_STAIRS = registerBlock("calcite_31_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_31_SLAB = registerBlock("calcite_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_31_WALL = registerBlock("calcite_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    public static final DeferredBlock<Block> CALCITE_32_BLOCK = registerBlock("calcite_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_32_STAIRS = registerBlock("calcite_32_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_32_SLAB = registerBlock("calcite_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_32_WALL = registerBlock("calcite_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    public static final DeferredBlock<Block> CALCITE_33_BLOCK = registerBlock("calcite_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_33_STAIRS = registerBlock("calcite_33_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_33_SLAB = registerBlock("calcite_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_33_WALL = registerBlock("calcite_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    public static final DeferredBlock<Block> CALCITE_34_BLOCK = registerBlock("calcite_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_34_STAIRS = registerBlock("calcite_34_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_34_SLAB = registerBlock("calcite_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_34_WALL = registerBlock("calcite_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    public static final DeferredBlock<Block> CALCITE_35_BLOCK = registerBlock("calcite_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_35_STAIRS = registerBlock("calcite_35_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_35_SLAB = registerBlock("calcite_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_35_WALL = registerBlock("calcite_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    public static final DeferredBlock<Block> CALCITE_36_BLOCK = registerBlock("calcite_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_36_STAIRS = registerBlock("calcite_36_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_36_SLAB = registerBlock("calcite_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_36_WALL = registerBlock("calcite_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    public static final DeferredBlock<Block> CALCITE_37_BLOCK = registerBlock("calcite_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_37_STAIRS = registerBlock("calcite_37_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_37_SLAB = registerBlock("calcite_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_37_WALL = registerBlock("calcite_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    public static final DeferredBlock<Block> CALCITE_38_BLOCK = registerBlock("calcite_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f));
    public static final DeferredBlock<Block> CALCITE_38_STAIRS = registerBlock("calcite_38_stairs",
            properties -> new StairBlock(ModBLocks.CALCITE_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_38_SLAB = registerBlock("calcite_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));
    public static final DeferredBlock<Block> CALCITE_38_WALL = registerBlock("calcite_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));


    public static final DeferredBlock<Block> CDEEPSLATE_1_BLOCK = registerBlock("cdeepslate_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_1_STAIRS = registerBlock("cdeepslate_1_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_1_SLAB = registerBlock("cdeepslate_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_1_WALL = registerBlock("cdeepslate_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_2_BLOCK = registerBlock("cdeepslate_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_2_STAIRS = registerBlock("cdeepslate_2_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_2_SLAB = registerBlock("cdeepslate_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_2_WALL = registerBlock("cdeepslate_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_3_BLOCK = registerBlock("cdeepslate_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_3_STAIRS = registerBlock("cdeepslate_3_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_3_SLAB = registerBlock("cdeepslate_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_3_WALL = registerBlock("cdeepslate_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_5_BLOCK = registerBlock("cdeepslate_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_5_STAIRS = registerBlock("cdeepslate_5_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_5_SLAB = registerBlock("cdeepslate_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_5_WALL = registerBlock("cdeepslate_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_6_BLOCK = registerBlock("cdeepslate_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_6_STAIRS = registerBlock("cdeepslate_6_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_6_SLAB = registerBlock("cdeepslate_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_6_WALL = registerBlock("cdeepslate_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_7_BLOCK = registerBlock("cdeepslate_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_7_STAIRS = registerBlock("cdeepslate_7_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_7_SLAB = registerBlock("cdeepslate_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_7_WALL = registerBlock("cdeepslate_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_8_BLOCK = registerBlock("cdeepslate_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_8_STAIRS = registerBlock("cdeepslate_8_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_8_SLAB = registerBlock("cdeepslate_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_8_WALL = registerBlock("cdeepslate_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_9_BLOCK = registerBlock("cdeepslate_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_9_STAIRS = registerBlock("cdeepslate_9_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_9_SLAB = registerBlock("cdeepslate_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_9_WALL = registerBlock("cdeepslate_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_10_BLOCK = registerBlock("cdeepslate_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_10_STAIRS = registerBlock("cdeepslate_10_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_10_SLAB = registerBlock("cdeepslate_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_10_WALL = registerBlock("cdeepslate_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_11_BLOCK = registerBlock("cdeepslate_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_11_STAIRS = registerBlock("cdeepslate_11_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_11_SLAB = registerBlock("cdeepslate_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_11_WALL = registerBlock("cdeepslate_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_12_BLOCK = registerBlock("cdeepslate_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_12_STAIRS = registerBlock("cdeepslate_12_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_12_SLAB = registerBlock("cdeepslate_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_12_WALL = registerBlock("cdeepslate_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_13_BLOCK = registerBlock("cdeepslate_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_13_STAIRS = registerBlock("cdeepslate_13_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_13_SLAB = registerBlock("cdeepslate_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_13_WALL = registerBlock("cdeepslate_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_14_BLOCK = registerBlock("cdeepslate_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_14_STAIRS = registerBlock("cdeepslate_14_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_14_SLAB = registerBlock("cdeepslate_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_14_WALL = registerBlock("cdeepslate_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    // Number 15 is skipped as requested
    public static final DeferredBlock<Block> CDEEPSLATE_16_BLOCK = registerBlock("cdeepslate_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_16_STAIRS = registerBlock("cdeepslate_16_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_16_SLAB = registerBlock("cdeepslate_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_16_WALL = registerBlock("cdeepslate_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_17_BLOCK = registerBlock("cdeepslate_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_17_STAIRS = registerBlock("cdeepslate_17_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_17_SLAB = registerBlock("cdeepslate_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_17_WALL = registerBlock("cdeepslate_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_18_BLOCK = registerBlock("cdeepslate_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_18_STAIRS = registerBlock("cdeepslate_18_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_18_SLAB = registerBlock("cdeepslate_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_18_WALL = registerBlock("cdeepslate_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    // Number 19 is skipped as requested

    // For 20
    public static final DeferredBlock<Block> CDEEPSLATE_20_BLOCK = registerBlock("cdeepslate_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_20_STAIRS = registerBlock("cdeepslate_20_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_20_SLAB = registerBlock("cdeepslate_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_20_WALL = registerBlock("cdeepslate_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    // For 21
    public static final DeferredBlock<Block> CDEEPSLATE_21_BLOCK = registerBlock("cdeepslate_21",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_21_STAIRS = registerBlock("cdeepslate_21_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_21_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_21_SLAB = registerBlock("cdeepslate_21_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_21_WALL = registerBlock("cdeepslate_21_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    // For 22
    public static final DeferredBlock<Block> CDEEPSLATE_22_BLOCK = registerBlock("cdeepslate_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_22_STAIRS = registerBlock("cdeepslate_22_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_22_SLAB = registerBlock("cdeepslate_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_22_WALL = registerBlock("cdeepslate_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_23_BLOCK = registerBlock("cdeepslate_23",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_23_STAIRS = registerBlock("cdeepslate_23_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_23_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_23_SLAB = registerBlock("cdeepslate_23_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_23_WALL = registerBlock("cdeepslate_23_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    // CDEEPSLATE 24
    public static final DeferredBlock<Block> CDEEPSLATE_24_BLOCK = registerBlock("cdeepslate_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_24_STAIRS = registerBlock("cdeepslate_24_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_24_SLAB = registerBlock("cdeepslate_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_24_WALL = registerBlock("cdeepslate_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    // CDEEPSLATE 25
    public static final DeferredBlock<Block> CDEEPSLATE_25_BLOCK = registerBlock("cdeepslate_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_25_STAIRS = registerBlock("cdeepslate_25_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_25_SLAB = registerBlock("cdeepslate_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_25_WALL = registerBlock("cdeepslate_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    // CDEEPSLATE 26
    public static final DeferredBlock<Block> CDEEPSLATE_26_BLOCK = registerBlock("cdeepslate_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_26_STAIRS = registerBlock("cdeepslate_26_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_26_SLAB = registerBlock("cdeepslate_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_26_WALL = registerBlock("cdeepslate_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    // CDEEPSLATE 27
    public static final DeferredBlock<Block> CDEEPSLATE_27_BLOCK = registerBlock("cdeepslate_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_27_STAIRS = registerBlock("cdeepslate_27_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_27_SLAB = registerBlock("cdeepslate_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_27_WALL = registerBlock("cdeepslate_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    // CDEEPSLATE 28
    public static final DeferredBlock<Block> CDEEPSLATE_28_BLOCK = registerBlock("cdeepslate_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_28_STAIRS = registerBlock("cdeepslate_28_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_28_SLAB = registerBlock("cdeepslate_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_28_WALL = registerBlock("cdeepslate_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_29_BLOCK = registerBlock("cdeepslate_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_29_STAIRS = registerBlock("cdeepslate_29_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_29_SLAB = registerBlock("cdeepslate_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_29_WALL = registerBlock("cdeepslate_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_30_BLOCK = registerBlock("cdeepslate_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_30_STAIRS = registerBlock("cdeepslate_30_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_30_SLAB = registerBlock("cdeepslate_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_30_WALL = registerBlock("cdeepslate_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_31_BLOCK = registerBlock("cdeepslate_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_31_STAIRS = registerBlock("cdeepslate_31_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_31_SLAB = registerBlock("cdeepslate_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_31_WALL = registerBlock("cdeepslate_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_32_BLOCK = registerBlock("cdeepslate_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_32_STAIRS = registerBlock("cdeepslate_32_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_32_SLAB = registerBlock("cdeepslate_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_32_WALL = registerBlock("cdeepslate_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_33_BLOCK = registerBlock("cdeepslate_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_33_STAIRS = registerBlock("cdeepslate_33_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_33_SLAB = registerBlock("cdeepslate_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_33_WALL = registerBlock("cdeepslate_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_34_BLOCK = registerBlock("cdeepslate_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_34_STAIRS = registerBlock("cdeepslate_34_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_34_SLAB = registerBlock("cdeepslate_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_34_WALL = registerBlock("cdeepslate_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_35_BLOCK = registerBlock("cdeepslate_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_35_STAIRS = registerBlock("cdeepslate_35_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_35_SLAB = registerBlock("cdeepslate_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_35_WALL = registerBlock("cdeepslate_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_36_BLOCK = registerBlock("cdeepslate_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_36_STAIRS = registerBlock("cdeepslate_36_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_36_SLAB = registerBlock("cdeepslate_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_36_WALL = registerBlock("cdeepslate_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_37_BLOCK = registerBlock("cdeepslate_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_37_STAIRS = registerBlock("cdeepslate_37_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_37_SLAB = registerBlock("cdeepslate_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_37_WALL = registerBlock("cdeepslate_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> CDEEPSLATE_38_BLOCK = registerBlock("cdeepslate_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f));
    public static final DeferredBlock<Block> CDEEPSLATE_38_STAIRS = registerBlock("cdeepslate_38_stairs",
            properties -> new StairBlock(ModBLocks.CDEEPSLATE_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_38_SLAB = registerBlock("cdeepslate_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));
    public static final DeferredBlock<Block> CDEEPSLATE_38_WALL = registerBlock("cdeepslate_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE));

    public static final DeferredBlock<Block> GRANITE_1_BLOCK = registerBlock("granite_1",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_1_STAIRS = registerBlock("granite_1_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_1_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_1_SLAB = registerBlock("granite_1_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_1_WALL = registerBlock("granite_1_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_2_BLOCK = registerBlock("granite_2",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_2_STAIRS = registerBlock("granite_2_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_2_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_2_SLAB = registerBlock("granite_2_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_2_WALL = registerBlock("granite_2_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_3_BLOCK = registerBlock("granite_3",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_3_STAIRS = registerBlock("granite_3_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_3_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_3_SLAB = registerBlock("granite_3_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_3_WALL = registerBlock("granite_3_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_4_BLOCK = registerBlock("granite_4",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_4_STAIRS = registerBlock("granite_4_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_4_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_4_SLAB = registerBlock("granite_4_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_4_WALL = registerBlock("granite_4_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_5_BLOCK = registerBlock("granite_5",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_5_STAIRS = registerBlock("granite_5_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_5_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_5_SLAB = registerBlock("granite_5_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_5_WALL = registerBlock("granite_5_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_6_BLOCK = registerBlock("granite_6",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_6_STAIRS = registerBlock("granite_6_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_6_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_6_SLAB = registerBlock("granite_6_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_6_WALL = registerBlock("granite_6_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_7_BLOCK = registerBlock("granite_7",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_7_STAIRS = registerBlock("granite_7_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_7_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_7_SLAB = registerBlock("granite_7_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_7_WALL = registerBlock("granite_7_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_8_BLOCK = registerBlock("granite_8",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_8_STAIRS = registerBlock("granite_8_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_8_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_8_SLAB = registerBlock("granite_8_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_8_WALL = registerBlock("granite_8_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_9_BLOCK = registerBlock("granite_9",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_9_STAIRS = registerBlock("granite_9_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_9_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_9_SLAB = registerBlock("granite_9_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_9_WALL = registerBlock("granite_9_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_10_BLOCK = registerBlock("granite_10",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_10_STAIRS = registerBlock("granite_10_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_10_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_10_SLAB = registerBlock("granite_10_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_10_WALL = registerBlock("granite_10_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_11_BLOCK = registerBlock("granite_11",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_11_STAIRS = registerBlock("granite_11_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_11_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_11_SLAB = registerBlock("granite_11_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_11_WALL = registerBlock("granite_11_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_12_BLOCK = registerBlock("granite_12",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_12_STAIRS = registerBlock("granite_12_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_12_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_12_SLAB = registerBlock("granite_12_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_12_WALL = registerBlock("granite_12_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_13_BLOCK = registerBlock("granite_13",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_13_STAIRS = registerBlock("granite_13_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_13_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_13_SLAB = registerBlock("granite_13_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_13_WALL = registerBlock("granite_13_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_14_BLOCK = registerBlock("granite_14",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_14_STAIRS = registerBlock("granite_14_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_14_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_14_SLAB = registerBlock("granite_14_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_14_WALL = registerBlock("granite_14_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    // public static final DeferredBlock<Block> GRANITE_15_BLOCK = registerBlock("granite_15",
    //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    //public static final DeferredBlock<Block> GRANITE_15_STAIRS = registerBlock("granite_15_stairs",
    //      properties -> new StairBlock(ModBLocks.GRANITE_15_BLOCK.get().defaultBlockState(),
    //            properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    //public static final DeferredBlock<Block> GRANITE_15_SLAB = registerBlock("granite_15_slab",
    //      SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    //public static final DeferredBlock<Block> GRANITE_15_WALL = registerBlock("granite_15_wall",
    //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_16_BLOCK = registerBlock("granite_16",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_16_STAIRS = registerBlock("granite_16_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_16_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_16_SLAB = registerBlock("granite_16_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_16_WALL = registerBlock("granite_16_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_17_BLOCK = registerBlock("granite_17",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_17_STAIRS = registerBlock("granite_17_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_17_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_17_SLAB = registerBlock("granite_17_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_17_WALL = registerBlock("granite_17_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_18_BLOCK = registerBlock("granite_18",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_18_STAIRS = registerBlock("granite_18_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_18_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_18_SLAB = registerBlock("granite_18_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_18_WALL = registerBlock("granite_18_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    // For 19
    public static final DeferredBlock<Block> GRANITE_19_BLOCK = registerBlock("granite_19",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_19_STAIRS = registerBlock("granite_19_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_19_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_19_SLAB = registerBlock("granite_19_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_19_WALL = registerBlock("granite_19_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // For 20
    public static final DeferredBlock<Block> GRANITE_20_BLOCK = registerBlock("granite_20",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_20_STAIRS = registerBlock("granite_20_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_20_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_20_SLAB = registerBlock("granite_20_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_20_WALL = registerBlock("granite_20_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // For 21
    public static final DeferredBlock<Block> GRANITE_21_BLOCK = registerBlock("granite_21",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_21_STAIRS = registerBlock("granite_21_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_21_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_21_SLAB = registerBlock("granite_21_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_21_WALL = registerBlock("granite_21_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // For 22
    public static final DeferredBlock<Block> GRANITE_22_BLOCK = registerBlock("granite_22",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_22_STAIRS = registerBlock("granite_22_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_22_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_22_SLAB = registerBlock("granite_22_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_22_WALL = registerBlock("granite_22_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_23_BLOCK = registerBlock("granite_23",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_23_STAIRS = registerBlock("granite_23_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_23_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_23_SLAB = registerBlock("granite_23_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_23_WALL = registerBlock("granite_23_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 24
    public static final DeferredBlock<Block> GRANITE_24_BLOCK = registerBlock("granite_24",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_24_STAIRS = registerBlock("granite_24_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_24_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_24_SLAB = registerBlock("granite_24_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_24_WALL = registerBlock("granite_24_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 25
    public static final DeferredBlock<Block> GRANITE_25_BLOCK = registerBlock("granite_25",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_25_STAIRS = registerBlock("granite_25_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_25_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_25_SLAB = registerBlock("granite_25_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_25_WALL = registerBlock("granite_25_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 26
    public static final DeferredBlock<Block> GRANITE_26_BLOCK = registerBlock("granite_26",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_26_STAIRS = registerBlock("granite_26_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_26_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_26_SLAB = registerBlock("granite_26_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_26_WALL = registerBlock("granite_26_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 27
    public static final DeferredBlock<Block> GRANITE_27_BLOCK = registerBlock("granite_27",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_27_STAIRS = registerBlock("granite_27_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_27_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_27_SLAB = registerBlock("granite_27_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_27_WALL = registerBlock("granite_27_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 28
    public static final DeferredBlock<Block> GRANITE_28_BLOCK = registerBlock("granite_28",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_28_STAIRS = registerBlock("granite_28_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_28_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_28_SLAB = registerBlock("granite_28_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_28_WALL = registerBlock("granite_28_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 29
    public static final DeferredBlock<Block> GRANITE_29_BLOCK = registerBlock("granite_29",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_29_STAIRS = registerBlock("granite_29_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_29_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_29_SLAB = registerBlock("granite_29_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_29_WALL = registerBlock("granite_29_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 30
    public static final DeferredBlock<Block> GRANITE_30_BLOCK = registerBlock("granite_30",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_30_STAIRS = registerBlock("granite_30_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_30_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_30_SLAB = registerBlock("granite_30_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_30_WALL = registerBlock("granite_30_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    // GRANITE 31
    public static final DeferredBlock<Block> GRANITE_31_BLOCK = registerBlock("granite_31",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_31_STAIRS = registerBlock("granite_31_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_31_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_31_SLAB = registerBlock("granite_31_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_31_WALL = registerBlock("granite_31_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    public static final DeferredBlock<Block> GRANITE_32_BLOCK = registerBlock("granite_32",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_32_STAIRS = registerBlock("granite_32_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_32_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_32_SLAB = registerBlock("granite_32_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_32_WALL = registerBlock("granite_32_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    public static final DeferredBlock<Block> GRANITE_33_BLOCK = registerBlock("granite_33",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_33_STAIRS = registerBlock("granite_33_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_33_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_33_SLAB = registerBlock("granite_33_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_33_WALL = registerBlock("granite_33_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    public static final DeferredBlock<Block> GRANITE_34_BLOCK = registerBlock("granite_34",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_34_STAIRS = registerBlock("granite_34_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_34_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_34_SLAB = registerBlock("granite_34_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_34_WALL = registerBlock("granite_34_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    public static final DeferredBlock<Block> GRANITE_35_BLOCK = registerBlock("granite_35",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_35_STAIRS = registerBlock("granite_35_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_35_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_35_SLAB = registerBlock("granite_35_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_35_WALL = registerBlock("granite_35_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    public static final DeferredBlock<Block> GRANITE_36_BLOCK = registerBlock("granite_36",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_36_STAIRS = registerBlock("granite_36_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_36_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_36_SLAB = registerBlock("granite_36_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_36_WALL = registerBlock("granite_36_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    public static final DeferredBlock<Block> GRANITE_37_BLOCK = registerBlock("granite_37",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_37_STAIRS = registerBlock("granite_37_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_37_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_37_SLAB = registerBlock("granite_37_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_37_WALL = registerBlock("granite_37_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));

    public static final DeferredBlock<Block> GRANITE_38_BLOCK = registerBlock("granite_38",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f));
    public static final DeferredBlock<Block> GRANITE_38_STAIRS = registerBlock("granite_38_stairs",
            properties -> new StairBlock(ModBLocks.GRANITE_38_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_38_SLAB = registerBlock("granite_38_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));
    public static final DeferredBlock<Block> GRANITE_38_WALL = registerBlock("granite_38_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE));


    // ---------------------------(FLOWERS)--------------------------- //
    public static final DeferredBlock<Block> WINTER_ROSE = registerBlock("winter_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> WILD_RADISH = registerBlock("wild_radish",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> WHITE_ROSE = registerBlock("white_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> THORN_BUSH = registerBlock("thorn_bush",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> THISTLE = registerBlock("thistle",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> TANSY = registerBlock("tansy",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> SPICEFLOWER = registerBlock("spiceflower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> SEDGE = registerBlock("sedge",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> SAFFRON_CROCUS = registerBlock("saffron_crocus",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> ROSE = registerBlock("rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> POISON_KISSES = registerBlock("poison_kisses",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> PENNYROYAL = registerBlock("pennyroyal",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> OPIUM_POPPY = registerBlock("opium_poppy",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> NIGHTSHADE = registerBlock("nightshade",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> MOONBLOOM = registerBlock("moonbloom",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LUNGWORT = registerBlock("lungwort",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LIVERWORT = registerBlock("liverwort",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LAVENDER = registerBlock("lavender",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LADYS_LACE = registerBlock("ladys_lace",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GORSE = registerBlock("gorse",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GOLDENROD = registerBlock("goldenrod",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GOLDENCUP = registerBlock("goldencup",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GOATHEAD = registerBlock("goathead",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GINGER = registerBlock("ginger",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GILLYFLOWER = registerBlock("gillyflower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> FROSTFIRE = registerBlock("frostfire",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> FORGET_ME_NOT = registerBlock("forget_me_not",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> EVENING_STAR = registerBlock("evening_star",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> DUSKY_ROSE = registerBlock("dusky_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> DRAGONS_BREATH = registerBlock("dragons_breath",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> COLDSNAP = registerBlock("coldsnap",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> BLUE_ROSE = registerBlock("blue_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> BLOODBLOOM = registerBlock("bloodbloom",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> BLACK_LOTUS = registerBlock("black_lotus",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> WINTER_ROSE_BUSH = registerBlock("winter_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> WHITE_ROSE_BUSH = registerBlock("white_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> DUSKY_ROSE_BUSH = registerBlock("dusky_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> BLUE_ROSE_BUSH = registerBlock("blue_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> RED_ROSE_BUSH = registerBlock("red_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));




    // ---------------------------(POTTED PLANTS)--------------------------- //
    //public static final DeferredBlock<Block> POTTED_WINTER_ROSE = BLOCKS.register("potted_winter_rose",
    //        properties -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBLocks.WINTER_ROSE,
    //               BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));

    // Tells the AGoTMod class to call the modded blocks into the game
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
