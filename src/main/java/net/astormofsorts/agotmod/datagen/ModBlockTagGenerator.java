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
                        ModBLocks.YELLOW_DIAMOND_BLOCK.get();
                        ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get();
                        ModBLocks.AMBER_BLOCK.get();
                        ModBLocks.AMBER_ORE.get();
                        ModBLocks.AMBER_DEEPSLATE_ORE.get();
                        ModBLocks.AMETHYST_BLOCK.get();
                        ModBLocks.AMETHYST_ORE.get();
                        ModBLocks.AMETHYST_DEEPSLATE_ORE.get();
                        ModBLocks.BLACK_DIAMOND_BLOCK.get();
                        ModBLocks.BLOODSTONE_BLOCK.get();
                        ModBLocks.BLOODSTONE_ORE.get();
                        ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get();
                        ModBLocks.CARNELIAN_ORE.get();
                        ModBLocks.CARNELIAN_DEEPSLATE_ORE.get();
                        ModBLocks.CARNELIAN_BLOCK.get();
                        ModBLocks.CHALCEDONY_BLOCK.get();
                        ModBLocks.CHALCEDONY_ORE.get();
                        ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get();
                        ModBLocks.DIAMONDS_ORE.get();
                        ModBLocks.DIAMONDS_DEEPSLATE_ORE.get();
                        ModBLocks.GARNET_BLOCK.get();
                        ModBLocks.GARNET_ORE.get();
                        ModBLocks.GARNET_DEEPSLATE_ORE.get();
                        ModBLocks.JADE_BLOCK.get();
                        ModBLocks.JADE_ORE.get();
                        ModBLocks.JADE_DEEPSLATE_ORE.get();
                        ModBLocks.JASPER_BLOCK.get();
                        ModBLocks.JASPER_ORE.get();
                        ModBLocks.JASPER_DEEPSLATE_ORE.get();
                        ModBLocks.MALACHITE_BLOCK.get();
                        ModBLocks.MALACHITE_ORE.get();
                        ModBLocks.MALACHITE_DEEPSLATE_ORE.get();
                        ModBLocks.MOONSTONE_BLOCK.get();
                        ModBLocks.MOONSTONE_ORE.get();
                        ModBLocks.MOONSTONE_DEEPSLATE_ORE.get();
                        ModBLocks.ONYX_BLOCK.get();
                        ModBLocks.ONYX_ORE.get();
                        ModBLocks.ONYX_DEEPSLATE_ORE.get();
                        ModBLocks.OPAL_BLOCK.get();
                        ModBLocks.OPAL_ORE.get();
                        ModBLocks.OPAL_DEEPSLATE_ORE.get();
                        ModBLocks.RUBY_BLOCK.get();
                        ModBLocks.RUBY_ORE.get();
                        ModBLocks.RUBY_DEEPSLATE_ORE.get();
                        ModBLocks.SAPPHIRE_BLOCK.get();
                        ModBLocks.SAPPHIRE_ORE.get();
                        ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get();
                        ModBLocks.TIGERS_EYE_BLOCK.get();
                        ModBLocks.TIGERS_EYE_ORE.get();
                        ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get();
                        ModBLocks.TOPAZ_BLOCK.get();
                        ModBLocks.TOPAZ_ORE.get();
                        ModBLocks.TOPAZ_DEEPSLATE_ORE.get();
                        ModBLocks.TOURMALINE_ORE.get();
                        ModBLocks.TOURMALINE_DEEPSLATE_ORE.get();
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
