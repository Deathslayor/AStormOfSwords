package net.stormofsorts.agotmod.block;// Importing necessary classes from other packages
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

// ModBLocks class for registering custom blocks
public class ModBLocks {
    // Deferred register for blocks
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AGoTMod.MOD_ID);

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
    public static final RegistryObject<Block> MINT_BLOCK = registerBlock("mint_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    // TIN ORE
    public static final RegistryObject<Block> TIN_ORE = registerBlock("tin_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    // RAW TIN ORE BLOCK
    public static final RegistryObject<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.RAW_COPPER_BLOCK)));

    // DEEPSLATE TIN ORE
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3, 6)));

    // TIN BLOCK
    public static final RegistryObject<Block> TIN_BLOCK = registerBlock("tin_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));

    // BRONZE BLOCK
    public static final RegistryObject<Block> BRONZE_BLOCK = registerBlock("bronze_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COPPER_BLOCK)));

    // DARK STONE BRICK
    public static final RegistryObject<Block> DARK_STONE_BRICK = registerBlock("dark_stone_brick",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    // KINGS LANDING BRICK LARGE
    public static final RegistryObject<Block> KINGS_LANDING_BRICK_LARGE = registerBlock("kings_landing_brick_large",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    // ROUNDED STONE BRICK
    public static final RegistryObject<Block> STONE_BRICK_BUT_COOLER = registerBlock("stone_brick_but_cooler",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE_BRICKS)));

    // ---------------------------(BLOCKS)--------------------------- //

    // Tells the AGoTMod class to call the modded blocks into the game
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
