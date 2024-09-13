package net.astormofsorts.agotmod.block;// Importing necessary classes from other packages

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.block.custom.ModFlammableLeaves;
import net.astormofsorts.agotmod.block.custom.ModFlammablePlanks;
import net.astormofsorts.agotmod.block.custom.ModFlammableRotatedPillarBlock;
import net.astormofsorts.agotmod.block.custom.specialleaves.WeirwoodLeavesBlock;
import net.astormofsorts.agotmod.item.ModItems;
import net.astormofsorts.agotmod.worldgen.tree.NBTBasedTreeGrower;
import net.astormofsorts.agotmod.worldgen.tree.SycamoreTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

// ModBLocks class for registering custom blocks
public class ModBLocks {
    // Deferred register for blocks
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AGoTMod.MOD_ID);

    // Method for registering a block and its corresponding item
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Method for registering a block item
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // ---------------------------(BLOCKS)--------------------------- //

    // Adding a workstation for villagers
    public static final RegistryObject<Block> MINT_BLOCK = registerBlock("mint_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    // TIN ORE
    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    // RAW TIN ORE BLOCK
    public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)));

    public static final RegistryObject<Block> YELLOW_DIAMOND_BLOCK = registerBlock("yellow_diamond_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> TRANSPARENT_DIAMOND_BLOCK = registerBlock("transparent_diamond_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> AMBER_BLOCK = registerBlock("amber_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> AMBER_ORE = registerBlock("amber_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> AMBER_DEEPSLATE_ORE = registerBlock("amber_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> AMETHYST_ORE = registerBlock("amethyst_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)));

    public static final RegistryObject<Block> AMETHYST_DEEPSLATE_ORE = registerBlock("amethyst_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)));

    public static final RegistryObject<Block> BLACK_DIAMOND_BLOCK = registerBlock("black_diamond_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> BLOODSTONE_BLOCK = registerBlock("bloodstone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> BLOODSTONE_ORE = registerBlock("bloodstone_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> BLOODSTONE_DEEPSLATE_ORE = registerBlock("bloodstone_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> CARNELIAN_BLOCK = registerBlock("carnelian_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> CARNELIAN_ORE = registerBlock("carnelian_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> CARNELIAN_DEEPSLATE_ORE = registerBlock("carnelian_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> CHALCEDONY_BLOCK = registerBlock("chalcedony_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> CHALCEDONY_ORE = registerBlock("chalcedony_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> CHALCEDONY_DEEPSLATE_ORE = registerBlock("chalcedony_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> DIAMONDS_ORE = registerBlock("diamonds_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> DIAMONDS_DEEPSLATE_ORE = registerBlock("diamonds_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> GARNET_BLOCK = registerBlock("garnet_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> GARNET_ORE = registerBlock("garnet_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> GARNET_DEEPSLATE_ORE = registerBlock("garnet_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> JADE_BLOCK = registerBlock("jade_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> JADE_ORE = registerBlock("jade_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> JADE_DEEPSLATE_ORE = registerBlock("jade_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> JASPER_BLOCK = registerBlock("jasper_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> JASPER_ORE = registerBlock("jasper_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> JASPER_DEEPSLATE_ORE = registerBlock("jasper_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> MALACHITE_BLOCK = registerBlock("malachite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> MALACHITE_ORE = registerBlock("malachite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> MALACHITE_DEEPSLATE_ORE = registerBlock("malachite_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> MOONSTONE_BLOCK = registerBlock("moonstone_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> MOONSTONE_ORE = registerBlock("moonstone_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> MOONSTONE_DEEPSLATE_ORE = registerBlock("moonstone_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> ONYX_BLOCK = registerBlock("onyx_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> ONYX_ORE = registerBlock("onyx_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> ONYX_DEEPSLATE_ORE = registerBlock("onyx_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> OPAL_BLOCK = registerBlock("opal_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> OPAL_ORE = registerBlock("opal_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> OPAL_DEEPSLATE_ORE = registerBlock("opal_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> RUBY_DEEPSLATE_ORE = registerBlock("ruby_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> SAPPHIRE_DEEPSLATE_ORE = registerBlock("sapphire_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> TIGERS_EYE_BLOCK = registerBlock("tigers_eye_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> TIGERS_EYE_ORE = registerBlock("tigers_eye_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> TIGERS_EYE_DEEPSLATE_ORE = registerBlock("tigers_eye_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> TOPAZ_BLOCK = registerBlock("topaz_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK)));

    public static final RegistryObject<Block> TOPAZ_ORE = registerBlock("topaz_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> TOPAZ_DEEPSLATE_ORE = registerBlock("topaz_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));

    public static final RegistryObject<Block> TOURMALINE_ORE = registerBlock("tourmaline_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_ORE)));

    public static final RegistryObject<Block> TOURMALINE_DEEPSLATE_ORE = registerBlock("tourmaline_deepslate_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_COPPER_ORE)));




    // DEEPSLATE TIN ORE
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    // TIN BLOCK
    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));

    // BRONZE BLOCK
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));

    // DARK STONE BRICK
    public static final RegistryObject<Block> DARK_STONE_BRICK = registerBlock("dark_stone_brick", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    // KINGS LANDING BRICK LARGE
    public static final RegistryObject<Block> KINGS_LANDING_BRICK_LARGE = registerBlock("kings_landing_brick_large", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    // ROUNDED STONE BRICK
    public static final RegistryObject<Block> STONE_BRICK_BUT_COOLER = registerBlock("stone_brick_but_cooler", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    // ---------------------------(BLOCKS)--------------------------- //

    // ---------------------------(TREE BLOCKS)--------------------------- //
    //Weirwood
    public static final RegistryObject<Block> WEIRWOOD_LOG = registerBlock("weirwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> WEIRWOOD_FACE_LOG = registerBlock("weirwood_face_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> WEIRWOOD_WOOD = registerBlock("weirwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_WEIRWOOD_LOG = registerBlock("stripped_weirwood_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_WEIRWOOD_WOOD = registerBlock("stripped_weirwood_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));

    public static final RegistryObject<Block> WEIRWOOD_PLANKS = registerBlock("weirwood_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> WEIRWOOD_LEAVES = registerBlock("weirwood_leaves", () -> new WeirwoodLeavesBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_LEAVES)));

    public static final RegistryObject<Block> WEIRWOOD_SAPLING = registerBlock("weirwood_sapling", () -> new SaplingBlock(new NBTBasedTreeGrower("weirwood_varient_", 2), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));
    //Sycamore
    public static final RegistryObject<Block> SYCAMORE_LOG = registerBlock("sycamore_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> SYCAMORE_WOOD = registerBlock("sycamore_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SYCAMORE_LOG = registerBlock("stripped_sycamore_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SYCAMORE_WOOD = registerBlock("stripped_sycamore_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));


    public static final RegistryObject<Block> SYCAMORE_PLANKS = registerBlock("sycamore_planks", () -> new ModFlammablePlanks(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SYCAMORE_LEAVES = registerBlock("sycamore_leaves", () -> new ModFlammableLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> SYCAMORE_SAPLING = registerBlock("sycamore_sapling", () -> new SaplingBlock(new SycamoreTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

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
