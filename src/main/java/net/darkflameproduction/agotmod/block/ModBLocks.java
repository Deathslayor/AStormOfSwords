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
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
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

            entity.makeStuckInBlock(state, new Vec3(0.8D, 0.5D, 0.8D));

            double headY = entity.getY() + entity.getEyeHeight();
            if (headY > pos.getY() && headY < pos.getY() + 1.0D) {
                if (!level.isClientSide) {
                    int tickCount = (int) (level.getGameTime() % DAMAGE_TICK_INTERVAL);

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

        // Prevent water from flowing into or through this block


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

        // Override for multiplayer compatibilit
    }

    // Register the block
    public static final DeferredBlock<Block> QUAGMIRE = registerBlock("quagmire",
            properties -> new QuagmireBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)
                    .sound(SoundType.MUD)
                    .friction(0.8F)
                    .speedFactor(0.2F)
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
    //public static final DeferredBlock<Block> SSTONE_21_BLOCK = registerBlock("sstone_21",
     //       Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
   // public static final DeferredBlock<Block> SSTONE_21_STAIRS = registerBlock("sstone_21_stairs",
     //       properties -> new StairBlock(ModBLocks.SSTONE_21_BLOCK.get().defaultBlockState(),
    //               properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    // public static final DeferredBlock<Block> SSTONE_21_SLAB = registerBlock("sstone_21_slab",
    //     SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    // public static final DeferredBlock<Block> SSTONE_21_WALL = registerBlock("sstone_21_wall",
    //      WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

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
