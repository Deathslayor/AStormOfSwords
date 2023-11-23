package net.stormofsorts.agotmod.datagen;

import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.block.ModBLocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.stormofsorts.agotmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AGoTMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        // MAKES BLOCKS MINEABLE FILE LOCATION IN RESOURCES/DATA/MINECRAFT/TAGS/BLOCKS

        /** // ---------------------------(PICKAXE)--------------------------- // */
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBLocks.TIN_ORE.get(),
                    ModBLocks.STONE_BRICK_BUT_COOLER.get(),
                    ModBLocks.TIN_BLOCK.get(),
                    ModBLocks.BRONZE_BLOCK.get(),
                    ModBLocks.MINT_BLOCK.get(),
                    ModBLocks.RAW_TIN_BLOCK.get(),
                    ModBLocks.DARK_STONE_BRICK.get(),
                    ModBLocks.DEEPSLATE_TIN_ORE.get(),
                    ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        /** // ---------------------------(PICKAXE)--------------------------- // */



        /** // ---------------------------(STONE TOOL)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.TIN_ORE.get());
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRICKS)--------------------------- //
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.DARK_STONE_BRICK.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.STONE_BRICK_BUT_COOLER.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        // ---------------------------(BRICKS)--------------------------- //
        /** // ---------------------------(STONE TOOL)--------------------------- // */



        /** ---------------------------(IRON TOOL)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.TIN_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.RAW_TIN_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.DEEPSLATE_TIN_ORE.get());
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRONZE)--------------------------- //
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.BRONZE_BLOCK.get());
        // ---------------------------(BRONZE)--------------------------- //
        /** // ---------------------------(IRON TOOL)--------------------------- // */



        /** // ---------------------------(BRONZE TOOL)--------------------------- // */
        this.tag(ModTags.Blocks.NEEDS_BRONZE_TOOL)
                .add(ModBLocks.MINT_BLOCK.get());
        /** // ---------------------------(BRONZE TOOL)--------------------------- // */



        /** // ---------------------------(DIAMOND TOOL)--------------------------- // */

        /** // ---------------------------(DIAMOND TOOL)--------------------------- // */

    }

}
