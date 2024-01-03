package net.stormofsorts.agotmod.datagen;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.registries.ForgeRegistries;
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.block.ModBLocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    // Constructor for ModBlockStateProvider
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (BlockStateProvider)
        super(output, AGoTMod.MOD_ID, existingFileHelper);
    }

    // Method to register block states and models
    @Override
    protected void registerStatesAndModels() {
        // ---------------------------(TIN)--------------------------- //
        // Register block states and models for tin-related blocks
        blockWithItem(ModBLocks.TIN_BLOCK);
        blockWithItem(ModBLocks.RAW_TIN_BLOCK);
        blockWithItem(ModBLocks.DEEPSLATE_TIN_ORE);
        blockWithItem(ModBLocks.TIN_ORE);
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRONZE)--------------------------- //
        // Register block states and models for bronze-related blocks
        blockWithItem(ModBLocks.BRONZE_BLOCK);
        // ---------------------------(BRONZE)--------------------------- //

        // ---------------------------(BRICKS)--------------------------- //
        // Register block states and models for brick-related blocks
        blockWithItem(ModBLocks.KINGS_LANDING_BRICK_LARGE);
        blockWithItem(ModBLocks.DARK_STONE_BRICK);
        blockWithItem(ModBLocks.STONE_BRICK_BUT_COOLER);
        // ---------------------------(BRICKS)--------------------------- //

        // ---------------------------(TREES)--------------------------- //
        logBlock((RotatedPillarBlock) ModBLocks.SYCAMORE_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.SYCAMORE_WOOD.get()),
                blockTexture(ModBLocks.SYCAMORE_LOG.get()), // side of the block
                blockTexture(ModBLocks.SYCAMORE_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_SYCAMORE_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), // side of the block
                new ResourceLocation(AGoTMod.MOD_ID, "block/stripped_sycamore_log_top")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_SYCAMORE_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_SYCAMORE_LOG.get())); // top of the block

        blockItem(ModBLocks.SYCAMORE_LOG);
        blockItem(ModBLocks.STRIPPED_SYCAMORE_LOG);
        blockItem(ModBLocks.SYCAMORE_WOOD);
        blockItem(ModBLocks.STRIPPED_SYCAMORE_WOOD);

        blockWithItem(ModBLocks.SYCAMORE_PLANKS);

        leavesBlock(ModBLocks.SYCAMORE_LEAVES);

        // ---------------------------(TREES)--------------------------- //

        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        // Register block states and models for villager profession-related blocks
        blockWithItem(ModBLocks.MINT_BLOCK);
        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(AGoTMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    // Method to simplify the process of registering block states and models
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        // Generate block states and models for a block with its corresponding item
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
