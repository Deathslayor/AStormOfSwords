package net.stormofsorts.agotmod.datagen;

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

        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        // Register block states and models for villager profession-related blocks
        blockWithItem(ModBLocks.MINT_BLOCK);
        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
    }

    // Method to simplify the process of registering block states and models
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        // Generate block states and models for a block with its corresponding item
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
