package net.darkflameproduction.agotmod.block;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.ModFlammableLeaves;
import net.darkflameproduction.agotmod.block.custom.ModFlammablePlanks;
import net.darkflameproduction.agotmod.block.custom.ModFlammableRotatedPillarBlock;
import net.darkflameproduction.agotmod.block.custom.specialleaves.WeirwoodLeavesBlock;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.worldgen.tree.ModTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

// ModBLocks class for registering custom blocks
public class ModBLocks {
    // Deferred register for blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, AGoTMod.MOD_ID);

    // Method for registering a block and its corresponding item
    private static <T extends Block> @NotNull DeferredHolder<Block, T> registerBlock(String name, Supplier<T> block) {
        DeferredHolder<Block, T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Method for registering a block item
    private static <T extends Block> @NotNull DeferredHolder<Item, ?> registerBlockItem(String name, DeferredHolder<Block, T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // ---------------------------(BLOCKS)--------------------------- //

    // Adding a workstation for villagers
    public static final DeferredHolder<Block, Block> MINT_BLOCK = registerBlock("mint_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    // TIN ORE
    public static final DeferredHolder<Block, Block> TIN_ORE = registerBlock("tin_ore", () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops()));

    // RAW TIN ORE BLOCK
    public static final DeferredHolder<Block, Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK)));

    public static final DeferredHolder<Block, Block> YELLOW_DIAMOND_BLOCK = registerBlock("yellow_diamond_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> TRANSPARENT_DIAMOND_BLOCK = registerBlock("transparent_diamond_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> AMBER_BLOCK = registerBlock("amber_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> AMBER_ORE = registerBlock("amber_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> AMBER_DEEPSLATE_ORE = registerBlock("amber_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> AMETHYST_BLOCK = registerBlock("amethyst_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)));

    public static final DeferredHolder<Block, Block> AMETHYST_DEEPSLATE_ORE = registerBlock("amethyst_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE)));

    public static final DeferredHolder<Block, Block> BLACK_DIAMOND_BLOCK = registerBlock("black_diamond_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> BLOODSTONE_BLOCK = registerBlock("bloodstone_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> BLOODSTONE_ORE = registerBlock("bloodstone_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> BLOODSTONE_DEEPSLATE_ORE = registerBlock("bloodstone_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> CARNELIAN_BLOCK = registerBlock("carnelian_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> CARNELIAN_ORE = registerBlock("carnelian_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> CARNELIAN_DEEPSLATE_ORE = registerBlock("carnelian_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> CHALCEDONY_BLOCK = registerBlock("chalcedony_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> CHALCEDONY_ORE = registerBlock("chalcedony_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> CHALCEDONY_DEEPSLATE_ORE = registerBlock("chalcedony_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> DIAMONDS_ORE = registerBlock("diamonds_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> DIAMONDS_DEEPSLATE_ORE = registerBlock("diamonds_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> GARNET_BLOCK = registerBlock("garnet_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> GARNET_ORE = registerBlock("garnet_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> GARNET_DEEPSLATE_ORE = registerBlock("garnet_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> JADE_BLOCK = registerBlock("jade_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> JADE_ORE = registerBlock("jade_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> JADE_DEEPSLATE_ORE = registerBlock("jade_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> JASPER_BLOCK = registerBlock("jasper_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> JASPER_ORE = registerBlock("jasper_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> JASPER_DEEPSLATE_ORE = registerBlock("jasper_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> MALACHITE_BLOCK = registerBlock("malachite_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> MALACHITE_ORE = registerBlock("malachite_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> MALACHITE_DEEPSLATE_ORE = registerBlock("malachite_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> MOONSTONE_BLOCK = registerBlock("moonstone_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> MOONSTONE_ORE = registerBlock("moonstone_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> MOONSTONE_DEEPSLATE_ORE = registerBlock("moonstone_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> ONYX_BLOCK = registerBlock("onyx_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> ONYX_ORE = registerBlock("onyx_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> ONYX_DEEPSLATE_ORE = registerBlock("onyx_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> OPAL_BLOCK = registerBlock("opal_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> OPAL_ORE = registerBlock("opal_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> OPAL_DEEPSLATE_ORE = registerBlock("opal_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> RUBY_ORE = registerBlock("ruby_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> RUBY_DEEPSLATE_ORE = registerBlock("ruby_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> SAPPHIRE_DEEPSLATE_ORE = registerBlock("sapphire_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> TIGERS_EYE_BLOCK = registerBlock("tigers_eye_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> TIGERS_EYE_ORE = registerBlock("tigers_eye_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> TIGERS_EYE_DEEPSLATE_ORE = registerBlock("tigers_eye_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> TOPAZ_BLOCK = registerBlock("topaz_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final DeferredHolder<Block, Block> TOPAZ_ORE = registerBlock("topaz_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> TOPAZ_DEEPSLATE_ORE = registerBlock("topaz_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final DeferredHolder<Block, Block> TOURMALINE_ORE = registerBlock("tourmaline_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE)));

    public static final DeferredHolder<Block, Block> TOURMALINE_DEEPSLATE_ORE = registerBlock("tourmaline_deepslate_ore", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE)));


    // DEEPSLATE TIN ORE
    public static final DeferredHolder<Block, Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops()));

    // TIN BLOCK
    public static final DeferredHolder<Block, Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));

    // BRONZE BLOCK
    public static final DeferredHolder<Block, Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK)));

    // DARK STONE BRICK
    public static final DeferredHolder<Block, Block> DARK_STONE_BRICK = registerBlock("dark_stone_brick", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));

    // KINGS LANDING BRICK LARGE
    public static final DeferredHolder<Block, Block> KINGS_LANDING_BRICK_LARGE = registerBlock("kings_landing_brick_large", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));

    // ROUNDED STONE BRICK
    public static final DeferredHolder<Block, Block> STONE_BRICK_BUT_COOLER = registerBlock("stone_brick_but_cooler", () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)));

    // ---------------------------(BLOCKS)--------------------------- //

    // ---------------------------(TREE BLOCKS)--------------------------- //
    //Weirwood
    public static final DeferredHolder<Block, Block> WEIRWOOD_LOG = registerBlock("weirwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_FACE_LOG = registerBlock("weirwood_face_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_WOOD = registerBlock("weirwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_WEIRWOOD_LOG = registerBlock("stripped_weirwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_WEIRWOOD_WOOD = registerBlock("stripped_weirwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_PLANKS = registerBlock("weirwood_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_LEAVES = registerBlock("weirwood_leaves", () -> new WeirwoodLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES)));
    // TODO: Change Tree Grower
    public static final DeferredHolder<Block, Block> WEIRWOOD_SAPLING = registerBlock("weirwood_sapling", () -> new SaplingBlock(ModTreeGrower.SYSCAMORE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_STAIRS = registerBlock("weirwood_stairs",
            () -> new StairBlock(() -> ModBLocks.WEIRWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_SLAB = registerBlock("weirwood_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_BUTTON = registerBlock("weirwood_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> WEIRWOOD_PRESSURE_PLATE = registerBlock("weirwood_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> WEIRWOOD_FENCE = registerBlock("weirwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_FENCE_GATE = registerBlock("weirwood_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> WEIRWOOD_WALL = registerBlock("weirwood_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> WEIRWOOD_DOOR = registerBlock("weirwood_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> WEIRWOOD_TRAPDOOR = registerBlock("weirwood_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));

    //Sycamore
    public static final DeferredHolder<Block, Block> SYCAMORE_LOG = registerBlock("sycamore_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> SYCAMORE_WOOD = registerBlock("sycamore_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_SYCAMORE_LOG = registerBlock("sycamore_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_SYCAMORE_WOOD = registerBlock("sycamore_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> SYCAMORE_PLANKS = registerBlock("sycamore_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> SYCAMORE_LEAVES = registerBlock("sycamore_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> SYCAMORE_SAPLING = registerBlock("sycamore_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> SYCAMORE_STAIRS = registerBlock("sycamore_stairs",
            () -> new StairBlock(() -> ModBLocks.SYCAMORE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> SYCAMORE_SLAB = registerBlock("sycamore_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> SYCAMORE_BUTTON = registerBlock("sycamore_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> SYCAMORE_PRESSURE_PLATE = registerBlock("sycamore_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> SYCAMORE_FENCE = registerBlock("sycamore_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> SYCAMORE_FENCE_GATE = registerBlock("sycamore_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> SYCAMORE_WALL = registerBlock("sycamore_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> SYCAMORE_DOOR = registerBlock("sycamore_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> SYCAMORE_TRAPDOOR = registerBlock("sycamore_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));

    //Sentinel
    public static final DeferredHolder<Block, Block> SENTINEL_LOG = registerBlock("sentinel_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> SENTINEL_WOOD = registerBlock("sentinel_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_SENTINEL_LOG = registerBlock("sentinel_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_SENTINEL_WOOD = registerBlock("sentinel_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> SENTINEL_PLANKS = registerBlock("sentinel_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> SENTINEL_LEAVES = registerBlock("sentinel_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> SENTINEL_SAPLING = registerBlock("sentinel_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> SENTINEL_STAIRS = registerBlock("sentinel_stairs",
            () -> new StairBlock(() -> ModBLocks.SENTINEL_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> SENTINEL_SLAB = registerBlock("sentinel_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> SENTINEL_BUTTON = registerBlock("sentinel_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> SENTINEL_PRESSURE_PLATE = registerBlock("sentinel_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> SENTINEL_FENCE = registerBlock("sentinel_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> SENTINEL_FENCE_GATE = registerBlock("sentinel_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> SENTINEL_WALL = registerBlock("sentinel_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> SENTINEL_DOOR = registerBlock("sentinel_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> SENTINEL_TRAPDOOR = registerBlock("sentinel_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));

    //Pine
    public static final DeferredHolder<Block, Block> PINE_LOG = registerBlock("pine_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> PINE_WOOD = registerBlock("pine_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_PINE_LOG = registerBlock("pine_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_PINE_WOOD = registerBlock("pine_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> PINE_PLANKS = registerBlock("pine_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> PINE_LEAVES = registerBlock("pine_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> PINE_SAPLING = registerBlock("pine_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> PINE_STAIRS = registerBlock("pine_stairs",
            () -> new StairBlock(() -> ModBLocks.PINE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> PINE_SLAB = registerBlock("pine_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> PINE_BUTTON = registerBlock("pine_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> PINE_FENCE = registerBlock("pine_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> PINE_WALL = registerBlock("pine_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> PINE_DOOR = registerBlock("pine_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    //ironwood
    public static final DeferredHolder<Block, Block> IRONWOOD_LOG = registerBlock("ironwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> IRONWOOD_WOOD = registerBlock("ironwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_IRONWOOD_LOG = registerBlock("ironwood_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_IRONWOOD_WOOD = registerBlock("ironwood_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> IRONWOOD_PLANKS = registerBlock("ironwood_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> IRONWOOD_LEAVES = registerBlock("ironwood_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> IRONWOOD_SAPLING = registerBlock("ironwood_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> IRONWOOD_STAIRS = registerBlock("ironwood_stairs",
            () -> new StairBlock(() -> ModBLocks.IRONWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> IRONWOOD_SLAB = registerBlock("ironwood_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> IRONWOOD_BUTTON = registerBlock("ironwood_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> IRONWOOD_PRESSURE_PLATE = registerBlock("ironwood_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> IRONWOOD_FENCE = registerBlock("ironwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> IRONWOOD_FENCE_GATE = registerBlock("ironwood_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> IRONWOOD_WALL = registerBlock("ironwood_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> IRONWOOD_DOOR = registerBlock("ironwood_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> IRONWOOD_TRAPDOOR = registerBlock("ironwood_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));

    //hawthorn
    public static final DeferredHolder<Block, Block> HAWTHORN_LOG = registerBlock("hawthorn_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> HAWTHORN_WOOD = registerBlock("hawthorn_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_HAWTHORN_LOG = registerBlock("hawthorn_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_HAWTHORN_WOOD = registerBlock("hawthorn_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> HAWTHORN_PLANKS = registerBlock("hawthorn_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> HAWTHORN_LEAVES = registerBlock("hawthorn_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> HAWTHORN_STAIRS = registerBlock("hawthorn_stairs",
            () -> new StairBlock(() -> ModBLocks.HAWTHORN_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> HAWTHORN_SLAB = registerBlock("hawthorn_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> HAWTHORN_BUTTON = registerBlock("hawthorn_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> HAWTHORN_PRESSURE_PLATE = registerBlock("hawthorn_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> HAWTHORN_FENCE = registerBlock("hawthorn_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> HAWTHORN_FENCE_GATE = registerBlock("hawthorn_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> HAWTHORN_WALL = registerBlock("hawthorn_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> HAWTHORN_DOOR = registerBlock("hawthorn_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> HAWTHORN_TRAPDOOR = registerBlock("hawthorn_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));

    //chestnut
    public static final DeferredHolder<Block, Block> CHESTNUT_LOG = registerBlock("chestnut_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> CHESTNUT_WOOD = registerBlock("chestnut_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_CHESTNUT_LOG = registerBlock("chestnut_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_CHESTNUT_WOOD = registerBlock("chestnut_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> CHESTNUT_PLANKS = registerBlock("chestnut_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> CHESTNUT_LEAVES = registerBlock("chestnut_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> CHESTNUT_SAPLING = registerBlock("chestnut_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> CHESTNUT_STAIRS = registerBlock("chestnut_stairs",
            () -> new StairBlock(() -> ModBLocks.CHESTNUT_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> CHESTNUT_SLAB = registerBlock("chestnut_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> CHESTNUT_BUTTON = registerBlock("chestnut_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> CHESTNUT_PRESSURE_PLATE = registerBlock("chestnut_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> CHESTNUT_FENCE = registerBlock("chestnut_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> CHESTNUT_FENCE_GATE = registerBlock("chestnut_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> CHESTNUT_WALL = registerBlock("chestnut_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> CHESTNUT_DOOR = registerBlock("chestnut_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> CHESTNUT_TRAPDOOR = registerBlock("chestnut_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    //Cedar
    public static final DeferredHolder<Block, Block> CEDAR_LOG = registerBlock("cedar_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> CEDAR_WOOD = registerBlock("cedar_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_CEDAR_LOG = registerBlock("cedar_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_CEDAR_WOOD = registerBlock("cedar_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> CEDAR_PLANKS = registerBlock("cedar_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> CEDAR_LEAVES = registerBlock("cedar_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> CEDAR_SAPLING = registerBlock("cedar_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> CEDAR_STAIRS = registerBlock("cedar_stairs",
            () -> new StairBlock(() -> ModBLocks.CEDAR_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> CEDAR_SLAB = registerBlock("cedar_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> CEDAR_BUTTON = registerBlock("cedar_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> CEDAR_PRESSURE_PLATE = registerBlock("cedar_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> CEDAR_FENCE = registerBlock("cedar_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> CEDAR_FENCE_GATE = registerBlock("cedar_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> CEDAR_WALL = registerBlock("cedar_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> CEDAR_DOOR = registerBlock("cedar_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> CEDAR_TRAPDOOR = registerBlock("cedar_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    //Beech
    public static final DeferredHolder<Block, Block> BEECH_LOG = registerBlock("beech_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> BEECH_WOOD = registerBlock("beech_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_BEECH_LOG = registerBlock("beech_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_BEECH_WOOD = registerBlock("beech_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> BEECH_PLANKS = registerBlock("beech_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> BEECH_LEAVES = registerBlock("beech_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> BEECH_SAPLING = registerBlock("beech_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> BEECH_STAIRS = registerBlock("beech_stairs",
            () -> new StairBlock(() -> ModBLocks.BEECH_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> BEECH_SLAB = registerBlock("beech_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> BEECH_BUTTON = registerBlock("beech_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> BEECH_PRESSURE_PLATE = registerBlock("beech_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> BEECH_FENCE = registerBlock("beech_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> BEECH_FENCE_GATE = registerBlock("beech_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> BEECH_WALL = registerBlock("beech_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> BEECH_DOOR = registerBlock("beech_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> BEECH_TRAPDOOR = registerBlock("beech_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    //Ash
    public static final DeferredHolder<Block, Block> ASH_LOG = registerBlock("ash_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> ASH_WOOD = registerBlock("ash_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_ASH_LOG = registerBlock("ash_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_ASH_WOOD = registerBlock("ash_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> ASH_PLANKS = registerBlock("ash_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> ASH_LEAVES = registerBlock("ash_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> ASH_SAPLING = registerBlock("ash_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> ASH_STAIRS = registerBlock("ash_stairs",
            () -> new StairBlock(ModBLocks.ASH_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> ASH_SLAB = registerBlock("ash_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> ASH_BUTTON = registerBlock("ash_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> ASH_PRESSURE_PLATE = registerBlock("ash_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> ASH_FENCE = registerBlock("ash_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> ASH_FENCE_GATE = registerBlock("ash_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> ASH_WALL = registerBlock("ash_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> ASH_DOOR = registerBlock("ash_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> ASH_TRAPDOOR = registerBlock("ash_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    //Blackbark
    public static final DeferredHolder<Block, Block> BLACKBARK_LOG = registerBlock("blackbark_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> BLACKBARK_WOOD = registerBlock("blackbark_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_BLACKBARK_LOG = registerBlock("blackbark_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_BLACKBARK_WOOD = registerBlock("blackbark_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> BLACKBARK_PLANKS = registerBlock("blackbark_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> BLACKBARK_LEAVES = registerBlock("blackbark_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> BLACKBARK_SAPLING = registerBlock("blackbark_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> BLACKBARK_STAIRS = registerBlock("blackbark_stairs",
            () -> new StairBlock(() -> ModBLocks.BLACKBARK_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> BLACKBARK_SLAB = registerBlock("blackbark_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> BLACKBARK_BUTTON = registerBlock("blackbark_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> BLACKBARK_PRESSURE_PLATE = registerBlock("blackbark_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> BLACKBARK_FENCE = registerBlock("blackbark_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> BLACKBARK_FENCE_GATE = registerBlock("blackbark_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> BLACKBARK_WALL = registerBlock("blackbark_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> BLACKBARK_DOOR = registerBlock("blackbark_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> BLACKBARK_TRAPDOOR = registerBlock("blackbark_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    //Aspen
    public static final DeferredHolder<Block, Block> ASPEN_LOG = registerBlock("aspen_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> ASPEN_WOOD = registerBlock("aspen_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_ASPEN_LOG = registerBlock("aspen_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_ASPEN_WOOD = registerBlock("aspen_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> ASPEN_PLANKS = registerBlock("aspen_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> ASPEN_LEAVES = registerBlock("aspen_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> ASPEN_SAPLING = registerBlock("aspen_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> ASPEN_STAIRS = registerBlock("aspen_stairs",
            () -> new StairBlock(() -> ModBLocks.ASPEN_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> ASPEN_SLAB = registerBlock("aspen_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> ASPEN_BUTTON = registerBlock("aspen_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> ASPEN_PRESSURE_PLATE = registerBlock("aspen_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> ASPEN_FENCE = registerBlock("aspen_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> ASPEN_FENCE_GATE = registerBlock("aspen_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> ASPEN_WALL = registerBlock("aspen_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> ASPEN_DOOR = registerBlock("aspen_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> ASPEN_TRAPDOOR = registerBlock("aspen_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    //Alder
    public static final DeferredHolder<Block, Block> ALDER_LOG = registerBlock("alder_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> ALDER_WOOD = registerBlock("alder_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_ALDER_LOG = registerBlock("alder_log_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final DeferredHolder<Block, Block> STRIPPED_ALDER_WOOD = registerBlock("alder_wood_stripped", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final DeferredHolder<Block, Block> ALDER_PLANKS = registerBlock("alder_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final DeferredHolder<Block, Block> ALDER_LEAVES = registerBlock("alder_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));
    public static final DeferredHolder<Block, Block> ALDER_SAPLING = registerBlock("alder_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredHolder<Block, Block> ALDER_STAIRS = registerBlock("alder_stairs",
            () -> new StairBlock(() -> ModBLocks.ALDER_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));
    public static final DeferredHolder<Block, Block> ALDER_SLAB = registerBlock("alder_slabs",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));
    public static final DeferredHolder<Block, Block> ALDER_BUTTON = registerBlock("alder_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));
    public static final DeferredHolder<Block, Block> ALDER_PRESSURE_PLATE = registerBlock("alder_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> ALDER_FENCE = registerBlock("alder_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> ALDER_FENCE_GATE = registerBlock("alder_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final DeferredHolder<Block, Block> ALDER_WALL = registerBlock("alder_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));
    public static final DeferredHolder<Block, Block> ALDER_DOOR = registerBlock("alder_door",
            () -> new DoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                    BlockSetType.OAK));
    public static final DeferredHolder<Block, Block> ALDER_TRAPDOOR = registerBlock("alder_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                    BlockSetType.OAK));


    // ---------------------------(TREE BLOCKS)--------------------------- //

    // Tells the AGoTMod class to call the modded blocks into the game
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static void spawnLeaveParticlesBelow(Level pLevel, BlockPos pPos, RandomSource pRandom, int color) {
        double d0 = (double) pPos.getX() + pRandom.nextDouble();
        double d1 = (double) pPos.getY() - 0.05D;
        double d2 = (double) pPos.getZ() + pRandom.nextDouble();

        double d3 = (double) (color >> 16 & 255) / 255.0D;
        double d4 = (double) (color >> 8 & 255) / 255.0D;
        double d5 = (double) (color & 255) / 255.0D;
        pLevel.addParticle(ParticleTypes.AMBIENT_ENTITY_EFFECT, d0, d1, d2, d3, d4, d5);
    }
}
