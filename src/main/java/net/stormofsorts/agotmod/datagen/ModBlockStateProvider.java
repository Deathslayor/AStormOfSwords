package net.stormofsorts.agotmod.datagen;

import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.block.ModBLocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AGoTMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // ---------------------------(TIN)--------------------------- //
        blockWithItem(ModBLocks.TIN_BLOCK);
        blockWithItem(ModBLocks.RAW_TIN_BLOCK);
        blockWithItem(ModBLocks.DEEPSLATE_TIN_ORE);
        blockWithItem(ModBLocks.TIN_ORE);
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRONZE)--------------------------- //
        blockWithItem(ModBLocks.BRONZE_BLOCK);
        // ---------------------------(BRONZE)--------------------------- //

        // ---------------------------(BRICKS)--------------------------- //
        blockWithItem(ModBLocks.KINGS_LANDING_BRICK_LARGE);
        blockWithItem(ModBLocks.DARK_STONE_BRICK);
        blockWithItem(ModBLocks.STONE_BRICK_BUT_COOLER);
        // ---------------------------(BRICKS)--------------------------- //

        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        blockWithItem(ModBLocks.MINT_BLOCK);
        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
