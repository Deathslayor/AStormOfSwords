package net.astormofsorts.agotmod.datagen;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    // Constructor for ModBlockTagGenerator
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (BlockTagsProvider)
        super(output, lookupProvider, AGoTMod.MOD_ID, existingFileHelper);
    }

    // Method to add custom tags to blocks
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // MAKES BLOCKS MINEABLE FILE LOCATION IN RESOURCES/DATA/MINECRAFT/TAGS/BLOCKS

        /** // ---------------------------(PICKAXE)--------------------------- // */
        // Add blocks that can be mined with a pickaxe to the MINEABLE_WITH_PICKAXE tag
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


        /** // ---------------------------(BLOCKS THAT BURN)--------------------------- // */

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.SYCAMORE_LOG.get())
                .add(ModBLocks.STRIPPED_SYCAMORE_LOG.get())
                .add(ModBLocks.SYCAMORE_WOOD.get())
                .add(ModBLocks.STRIPPED_SYCAMORE_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.SYCAMORE_PLANKS.get());

        /** // ---------------------------(BLOCKS THAT BURN)--------------------------- // */


        /** // ---------------------------(STONE TOOL)--------------------------- // */
        // Add blocks that need a stone tool to the NEEDS_STONE_TOOL tag
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.TIN_ORE.get());
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRICKS)--------------------------- //
        // Add brick blocks that need a stone tool to the NEEDS_STONE_TOOL tag
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.DARK_STONE_BRICK.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.STONE_BRICK_BUT_COOLER.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        // ---------------------------(BRICKS)--------------------------- //
        /** // ---------------------------(STONE TOOL)--------------------------- // */

        /** ---------------------------(IRON TOOL)--------------------------- // */
        // Add blocks that need an iron tool to the NEEDS_IRON_TOOL tag
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.TIN_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.RAW_TIN_BLOCK.get());
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.DEEPSLATE_TIN_ORE.get());
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRONZE)--------------------------- //
        // Add blocks that need an iron tool to the NEEDS_IRON_TOOL tag
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.BRONZE_BLOCK.get());
        // ---------------------------(BRONZE)--------------------------- //
        /** // ---------------------------(IRON TOOL)--------------------------- // */

        /** // ---------------------------(BRONZE TOOL)--------------------------- // */
        // Add blocks that need a bronze tool to the custom NEEDS_BRONZE_TOOL tag
        this.tag(ModTags.Blocks.NEEDS_BRONZE_TOOL)
                .add(ModBLocks.MINT_BLOCK.get());
        /** // ---------------------------(BRONZE TOOL)--------------------------- // */

        /** // ---------------------------(DIAMOND TOOL)--------------------------- // */

        /** // ---------------------------(DIAMOND TOOL)--------------------------- // */

        /** // ---------------------------(NETHERITE TOOL)--------------------------- // */

        /** // ---------------------------(NETHERITE TOOL)--------------------------- // */

        /** // ---------------------------(STEEL TOOL)--------------------------- // */

        /** // ---------------------------(STEEL TOOL)--------------------------- // */
    }
}
