package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
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
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        // MAKES BLOCKS MINEABLE FILE LOCATION IN RESOURCES/DATA/MINECRAFT/TAGS/BLOCKS

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBLocks.QUAGMIRE.get());

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
                                ModBLocks.YELLOW_DIAMOND_BLOCK.get(),
                        ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get(),
                        ModBLocks.AMBER_BLOCK.get(),
                        ModBLocks.AMBER_ORE.get(),
                        ModBLocks.AMBER_DEEPSLATE_ORE.get(),
                        ModBLocks.AMETHYST_BLOCK.get(),
                        ModBLocks.AMETHYST_ORE.get(),
                        ModBLocks.AMETHYST_DEEPSLATE_ORE.get(),
                        ModBLocks.BLACK_DIAMOND_BLOCK.get(),
                        ModBLocks.BLOODSTONE_BLOCK.get(),
                        ModBLocks.BLOODSTONE_ORE.get(),
                        ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get(),
                        ModBLocks.CARNELIAN_ORE.get(),
                        ModBLocks.CARNELIAN_DEEPSLATE_ORE.get(),
                        ModBLocks.CARNELIAN_BLOCK.get(),
                        ModBLocks.CHALCEDONY_BLOCK.get(),
                        ModBLocks.CHALCEDONY_ORE.get(),
                        ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get(),
                        ModBLocks.DIAMONDS_ORE.get(),
                        ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(),
                        ModBLocks.GARNET_BLOCK.get(),
                        ModBLocks.GARNET_ORE.get(),
                        ModBLocks.GARNET_DEEPSLATE_ORE.get(),
                        ModBLocks.JADE_BLOCK.get(),
                        ModBLocks.JADE_ORE.get(),
                        ModBLocks.JADE_DEEPSLATE_ORE.get(),
                        ModBLocks.JASPER_BLOCK.get(),
                        ModBLocks.JASPER_ORE.get(),
                        ModBLocks.JASPER_DEEPSLATE_ORE.get(),
                        ModBLocks.MALACHITE_BLOCK.get(),
                        ModBLocks.MALACHITE_ORE.get(),
                        ModBLocks.MALACHITE_DEEPSLATE_ORE.get(),
                        ModBLocks.MOONSTONE_BLOCK.get(),
                        ModBLocks.MOONSTONE_ORE.get(),
                        ModBLocks.MOONSTONE_DEEPSLATE_ORE.get(),
                        ModBLocks.ONYX_BLOCK.get(),
                        ModBLocks.ONYX_ORE.get(),
                        ModBLocks.ONYX_DEEPSLATE_ORE.get(),
                        ModBLocks.OPAL_BLOCK.get(),
                        ModBLocks.OPAL_ORE.get(),
                        ModBLocks.OPAL_DEEPSLATE_ORE.get(),
                        ModBLocks.RUBY_BLOCK.get(),
                        ModBLocks.RUBY_ORE.get(),
                        ModBLocks.RUBY_DEEPSLATE_ORE.get(),
                        ModBLocks.SAPPHIRE_BLOCK.get(),
                        ModBLocks.SAPPHIRE_ORE.get(),
                        ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get(),
                        ModBLocks.TIGERS_EYE_BLOCK.get(),
                        ModBLocks.TIGERS_EYE_ORE.get(),
                        ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get(),
                        ModBLocks.TOPAZ_BLOCK.get(),
                        ModBLocks.TOPAZ_ORE.get(),
                        ModBLocks.TOPAZ_DEEPSLATE_ORE.get(),
                        ModBLocks.TOURMALINE_ORE.get(),
                        ModBLocks.TOURMALINE_DEEPSLATE_ORE.get(),
                        ModBLocks.KINGS_LANDING_BRICK_LARGE.get())
        .add(ModBLocks.SSTONE_1_BLOCK.get())
                .add(ModBLocks.SSTONE_1_STAIRS.get())
                .add(ModBLocks.SSTONE_1_SLAB.get())
                .add(ModBLocks.SSTONE_1_WALL.get())
                .add(ModBLocks.SSTONE_2_BLOCK.get())
                .add(ModBLocks.SSTONE_2_STAIRS.get())
                .add(ModBLocks.SSTONE_2_SLAB.get())
                .add(ModBLocks.SSTONE_2_WALL.get())
                .add(ModBLocks.SSTONE_3_BLOCK.get())
                .add(ModBLocks.SSTONE_3_STAIRS.get())
                .add(ModBLocks.SSTONE_3_SLAB.get())
                .add(ModBLocks.SSTONE_3_WALL.get())
                .add(ModBLocks.SSTONE_4_BLOCK.get())
                .add(ModBLocks.SSTONE_4_STAIRS.get())
                .add(ModBLocks.SSTONE_4_SLAB.get())
                .add(ModBLocks.SSTONE_4_WALL.get())
                .add(ModBLocks.SSTONE_5_BLOCK.get())
                .add(ModBLocks.SSTONE_5_STAIRS.get())
                .add(ModBLocks.SSTONE_5_SLAB.get())
                .add(ModBLocks.SSTONE_5_WALL.get())
                .add(ModBLocks.SSTONE_6_BLOCK.get())
                .add(ModBLocks.SSTONE_6_STAIRS.get())
                .add(ModBLocks.SSTONE_6_SLAB.get())
                .add(ModBLocks.SSTONE_6_WALL.get())
                .add(ModBLocks.SSTONE_7_BLOCK.get())
                .add(ModBLocks.SSTONE_7_STAIRS.get())
                .add(ModBLocks.SSTONE_7_SLAB.get())
                .add(ModBLocks.SSTONE_7_WALL.get())
                .add(ModBLocks.SSTONE_8_BLOCK.get())
                .add(ModBLocks.SSTONE_8_STAIRS.get())
                .add(ModBLocks.SSTONE_8_SLAB.get())
                .add(ModBLocks.SSTONE_8_WALL.get())
                .add(ModBLocks.SSTONE_9_BLOCK.get())
                .add(ModBLocks.SSTONE_9_STAIRS.get())
                .add(ModBLocks.SSTONE_9_SLAB.get())
                .add(ModBLocks.SSTONE_9_WALL.get())
                .add(ModBLocks.SSTONE_10_BLOCK.get())
                .add(ModBLocks.SSTONE_10_STAIRS.get())
                .add(ModBLocks.SSTONE_10_SLAB.get())
                .add(ModBLocks.SSTONE_10_WALL.get())
                .add(ModBLocks.SSTONE_11_BLOCK.get())
                .add(ModBLocks.SSTONE_11_STAIRS.get())
                .add(ModBLocks.SSTONE_11_SLAB.get())
                .add(ModBLocks.SSTONE_11_WALL.get())
                .add(ModBLocks.SSTONE_12_BLOCK.get())
                .add(ModBLocks.SSTONE_12_STAIRS.get())
                .add(ModBLocks.SSTONE_12_SLAB.get())
                .add(ModBLocks.SSTONE_12_WALL.get())
                .add(ModBLocks.SSTONE_13_BLOCK.get())
                .add(ModBLocks.SSTONE_13_STAIRS.get())
                .add(ModBLocks.SSTONE_13_SLAB.get())
                .add(ModBLocks.SSTONE_13_WALL.get())
                .add(ModBLocks.SSTONE_14_BLOCK.get())
                .add(ModBLocks.SSTONE_14_STAIRS.get())
                .add(ModBLocks.SSTONE_14_SLAB.get())
                .add(ModBLocks.SSTONE_14_WALL.get())
                // .add(ModBLocks.SSTONE_15_BLOCK.get())
                // .add(ModBLocks.SSTONE_15_STAIRS.get())
                // .add(ModBLocks.SSTONE_15_SLAB.get())
                // .add(ModBLocks.SSTONE_15_WALL.get())
                .add(ModBLocks.SSTONE_16_BLOCK.get())
                .add(ModBLocks.SSTONE_16_STAIRS.get())
                .add(ModBLocks.SSTONE_16_SLAB.get())
                .add(ModBLocks.SSTONE_16_WALL.get())
                .add(ModBLocks.SSTONE_17_BLOCK.get())
                .add(ModBLocks.SSTONE_17_STAIRS.get())
                .add(ModBLocks.SSTONE_17_SLAB.get())
                .add(ModBLocks.SSTONE_17_WALL.get())
                .add(ModBLocks.SSTONE_18_BLOCK.get())
                .add(ModBLocks.SSTONE_18_STAIRS.get())
                .add(ModBLocks.SSTONE_18_SLAB.get())
                .add(ModBLocks.SSTONE_18_WALL.get())
                .add(ModBLocks.SSTONE_19_BLOCK.get())
                .add(ModBLocks.SSTONE_19_STAIRS.get())
                .add(ModBLocks.SSTONE_19_SLAB.get())
                .add(ModBLocks.SSTONE_19_WALL.get())
                .add(ModBLocks.SSTONE_20_BLOCK.get())
                .add(ModBLocks.SSTONE_20_STAIRS.get())
                .add(ModBLocks.SSTONE_20_SLAB.get())
                .add(ModBLocks.SSTONE_20_WALL.get())
                 .add(ModBLocks.SSTONE_21_BLOCK.get())
                .add(ModBLocks.SSTONE_21_STAIRS.get())
                 .add(ModBLocks.SSTONE_21_SLAB.get())
                .add(ModBLocks.SSTONE_21_WALL.get())
                .add(ModBLocks.SSTONE_22_BLOCK.get())
                .add(ModBLocks.SSTONE_22_STAIRS.get())
                .add(ModBLocks.SSTONE_22_SLAB.get())
                .add(ModBLocks.SSTONE_22_WALL.get())
                .add(ModBLocks.RSANDSTONE_1_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_1_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_1_SLAB.get())
                .add(ModBLocks.RSANDSTONE_1_WALL.get())
                .add(ModBLocks.RSANDSTONE_2_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_2_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_2_SLAB.get())
                .add(ModBLocks.RSANDSTONE_2_WALL.get())
                .add(ModBLocks.RSANDSTONE_3_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_3_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_3_SLAB.get())
                .add(ModBLocks.RSANDSTONE_3_WALL.get())
                .add(ModBLocks.RSANDSTONE_4_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_4_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_4_SLAB.get())
                .add(ModBLocks.RSANDSTONE_4_WALL.get())
                .add(ModBLocks.RSANDSTONE_5_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_5_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_5_SLAB.get())
                .add(ModBLocks.RSANDSTONE_5_WALL.get())
                .add(ModBLocks.RSANDSTONE_6_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_6_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_6_SLAB.get())
                .add(ModBLocks.RSANDSTONE_6_WALL.get())
                .add(ModBLocks.RSANDSTONE_7_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_7_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_7_SLAB.get())
                .add(ModBLocks.RSANDSTONE_7_WALL.get())
                .add(ModBLocks.RSANDSTONE_8_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_8_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_8_SLAB.get())
                .add(ModBLocks.RSANDSTONE_8_WALL.get())
                .add(ModBLocks.RSANDSTONE_9_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_9_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_9_SLAB.get())
                .add(ModBLocks.RSANDSTONE_9_WALL.get())
                .add(ModBLocks.RSANDSTONE_10_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_10_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_10_SLAB.get())
                .add(ModBLocks.RSANDSTONE_10_WALL.get())
                .add(ModBLocks.RSANDSTONE_11_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_11_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_11_SLAB.get())
                .add(ModBLocks.RSANDSTONE_11_WALL.get())
                .add(ModBLocks.RSANDSTONE_12_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_12_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_12_SLAB.get())
                .add(ModBLocks.RSANDSTONE_12_WALL.get())
                .add(ModBLocks.RSANDSTONE_13_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_13_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_13_SLAB.get())
                .add(ModBLocks.RSANDSTONE_13_WALL.get())
                .add(ModBLocks.RSANDSTONE_14_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_14_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_14_SLAB.get())
                .add(ModBLocks.RSANDSTONE_14_WALL.get())
// .add(ModBLocks.RSANDSTONE_15_BLOCK.get())
// .add(ModBLocks.RSANDSTONE_15_STAIRS.get())
// .add(ModBLocks.RSANDSTONE_15_SLAB.get())
// .add(ModBLocks.RSANDSTONE_15_WALL.get())
                .add(ModBLocks.RSANDSTONE_19_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_19_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_19_SLAB.get())
                .add(ModBLocks.RSANDSTONE_19_WALL.get())
                .add(ModBLocks.RSANDSTONE_20_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_20_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_20_SLAB.get())
                .add(ModBLocks.RSANDSTONE_20_WALL.get())
 .add(ModBLocks.RSANDSTONE_21_BLOCK.get())
 .add(ModBLocks.RSANDSTONE_21_STAIRS.get())
 .add(ModBLocks.RSANDSTONE_21_SLAB.get())
.add(ModBLocks.RSANDSTONE_21_WALL.get())
                .add(ModBLocks.RSANDSTONE_22_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_22_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_22_SLAB.get())
                .add(ModBLocks.RSANDSTONE_22_WALL.get())
                .add(ModBLocks.RSANDSTONE_23_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_23_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_23_SLAB.get())
                .add(ModBLocks.RSANDSTONE_23_WALL.get())

                .add(ModBLocks.RSANDSTONE_24_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_24_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_24_SLAB.get())
                .add(ModBLocks.RSANDSTONE_24_WALL.get())

                .add(ModBLocks.RSANDSTONE_25_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_25_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_25_SLAB.get())
                .add(ModBLocks.RSANDSTONE_25_WALL.get())

                .add(ModBLocks.RSANDSTONE_26_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_26_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_26_SLAB.get())
                .add(ModBLocks.RSANDSTONE_26_WALL.get())

                .add(ModBLocks.RSANDSTONE_27_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_27_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_27_SLAB.get())
                .add(ModBLocks.RSANDSTONE_27_WALL.get())

                .add(ModBLocks.RSANDSTONE_28_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_28_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_28_SLAB.get())
                .add(ModBLocks.RSANDSTONE_28_WALL.get())

                .add(ModBLocks.RSANDSTONE_29_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_29_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_29_SLAB.get())
                .add(ModBLocks.RSANDSTONE_29_WALL.get())

                .add(ModBLocks.RSANDSTONE_30_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_30_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_30_SLAB.get())
                .add(ModBLocks.RSANDSTONE_30_WALL.get())

                .add(ModBLocks.RSANDSTONE_31_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_31_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_31_SLAB.get())
                .add(ModBLocks.RSANDSTONE_31_WALL.get())

                .add(ModBLocks.RSANDSTONE_32_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_32_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_32_SLAB.get())
                .add(ModBLocks.RSANDSTONE_32_WALL.get())

                .add(ModBLocks.RSANDSTONE_33_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_33_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_33_SLAB.get())
                .add(ModBLocks.RSANDSTONE_33_WALL.get())

                .add(ModBLocks.RSANDSTONE_34_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_34_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_34_SLAB.get())
                .add(ModBLocks.RSANDSTONE_34_WALL.get())

                .add(ModBLocks.RSANDSTONE_35_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_35_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_35_SLAB.get())
                .add(ModBLocks.RSANDSTONE_35_WALL.get())

                .add(ModBLocks.RSANDSTONE_36_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_36_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_36_SLAB.get())
                .add(ModBLocks.RSANDSTONE_36_WALL.get())

                .add(ModBLocks.RSANDSTONE_37_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_37_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_37_SLAB.get())
                .add(ModBLocks.RSANDSTONE_37_WALL.get())

                .add(ModBLocks.RSANDSTONE_38_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_38_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_38_SLAB.get())
                .add(ModBLocks.RSANDSTONE_38_WALL.get())

                .add(ModBLocks.REDKEEP_STONE_BLOCK.get())
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get())
                .add(ModBLocks.REDKEEP_STONE_SLAB.get())
                .add(ModBLocks.REDKEEP_STONE_WALL.get())

                .add(ModBLocks.REDKEEP_1_BLOCK.get())
                .add(ModBLocks.REDKEEP_1_STAIRS.get())
                .add(ModBLocks.REDKEEP_1_SLAB.get())
                .add(ModBLocks.REDKEEP_1_WALL.get())

                .add(ModBLocks.REDKEEP_2_BLOCK.get())
                .add(ModBLocks.REDKEEP_2_STAIRS.get())
                .add(ModBLocks.REDKEEP_2_SLAB.get())
                .add(ModBLocks.REDKEEP_2_WALL.get())

                .add(ModBLocks.REDKEEP_3_BLOCK.get())
                .add(ModBLocks.REDKEEP_3_STAIRS.get())
                .add(ModBLocks.REDKEEP_3_SLAB.get())
                .add(ModBLocks.REDKEEP_3_WALL.get())

                .add(ModBLocks.REDKEEP_4_BLOCK.get())
                .add(ModBLocks.REDKEEP_4_STAIRS.get())
                .add(ModBLocks.REDKEEP_4_SLAB.get())
                .add(ModBLocks.REDKEEP_4_WALL.get())

                .add(ModBLocks.REDKEEP_5_BLOCK.get())
                .add(ModBLocks.REDKEEP_5_STAIRS.get())
                .add(ModBLocks.REDKEEP_5_SLAB.get())
                .add(ModBLocks.REDKEEP_5_WALL.get())

                .add(ModBLocks.REDKEEP_6_BLOCK.get())
                .add(ModBLocks.REDKEEP_6_STAIRS.get())
                .add(ModBLocks.REDKEEP_6_SLAB.get())
                .add(ModBLocks.REDKEEP_6_WALL.get())

                .add(ModBLocks.REDKEEP_7_BLOCK.get())
                .add(ModBLocks.REDKEEP_7_STAIRS.get())
                .add(ModBLocks.REDKEEP_7_SLAB.get())
                .add(ModBLocks.REDKEEP_7_WALL.get())

                .add(ModBLocks.REDKEEP_8_BLOCK.get())
                .add(ModBLocks.REDKEEP_8_STAIRS.get())
                .add(ModBLocks.REDKEEP_8_SLAB.get())
                .add(ModBLocks.REDKEEP_8_WALL.get())

                .add(ModBLocks.REDKEEP_9_BLOCK.get())
                .add(ModBLocks.REDKEEP_9_STAIRS.get())
                .add(ModBLocks.REDKEEP_9_SLAB.get())
                .add(ModBLocks.REDKEEP_9_WALL.get())

                .add(ModBLocks.REDKEEP_10_BLOCK.get())
                .add(ModBLocks.REDKEEP_10_STAIRS.get())
                .add(ModBLocks.REDKEEP_10_SLAB.get())
                .add(ModBLocks.REDKEEP_10_WALL.get())

                .add(ModBLocks.REDKEEP_11_BLOCK.get())
                .add(ModBLocks.REDKEEP_11_STAIRS.get())
                .add(ModBLocks.REDKEEP_11_SLAB.get())
                .add(ModBLocks.REDKEEP_11_WALL.get())

                .add(ModBLocks.REDKEEP_12_BLOCK.get())
                .add(ModBLocks.REDKEEP_12_STAIRS.get())
                .add(ModBLocks.REDKEEP_12_SLAB.get())
                .add(ModBLocks.REDKEEP_12_WALL.get())

                .add(ModBLocks.REDKEEP_13_BLOCK.get())
                .add(ModBLocks.REDKEEP_13_STAIRS.get())
                .add(ModBLocks.REDKEEP_13_SLAB.get())
                .add(ModBLocks.REDKEEP_13_WALL.get())

                .add(ModBLocks.REDKEEP_14_BLOCK.get())
                .add(ModBLocks.REDKEEP_14_STAIRS.get())
                .add(ModBLocks.REDKEEP_14_SLAB.get())
                .add(ModBLocks.REDKEEP_14_WALL.get())

                // .add(ModBLocks.REDKEEP_15_BLOCK.get())
                // .add(ModBLocks.REDKEEP_15_STAIRS.get())
                // .add(ModBLocks.REDKEEP_15_SLAB.get())
                // .add(ModBLocks.REDKEEP_15_WALL.get())

                .add(ModBLocks.REDKEEP_19_BLOCK.get())
                .add(ModBLocks.REDKEEP_19_STAIRS.get())
                .add(ModBLocks.REDKEEP_19_SLAB.get())
                .add(ModBLocks.REDKEEP_19_WALL.get())

                .add(ModBLocks.REDKEEP_20_BLOCK.get())
                .add(ModBLocks.REDKEEP_20_STAIRS.get())
                .add(ModBLocks.REDKEEP_20_SLAB.get())
                .add(ModBLocks.REDKEEP_20_WALL.get())

                .add(ModBLocks.REDKEEP_21_BLOCK.get())
                .add(ModBLocks.REDKEEP_21_STAIRS.get())
                .add(ModBLocks.REDKEEP_21_SLAB.get())
                .add(ModBLocks.REDKEEP_21_WALL.get())

                .add(ModBLocks.REDKEEP_22_BLOCK.get())
                .add(ModBLocks.REDKEEP_22_STAIRS.get())
                .add(ModBLocks.REDKEEP_22_SLAB.get())
                .add(ModBLocks.REDKEEP_22_WALL.get())

                .add(ModBLocks.REDKEEP_23_BLOCK.get())
                .add(ModBLocks.REDKEEP_23_STAIRS.get())
                .add(ModBLocks.REDKEEP_23_SLAB.get())
                .add(ModBLocks.REDKEEP_23_WALL.get())

                .add(ModBLocks.REDKEEP_24_BLOCK.get())
                .add(ModBLocks.REDKEEP_24_STAIRS.get())
                .add(ModBLocks.REDKEEP_24_SLAB.get())
                .add(ModBLocks.REDKEEP_24_WALL.get())

                .add(ModBLocks.REDKEEP_25_BLOCK.get())
                .add(ModBLocks.REDKEEP_25_STAIRS.get())
                .add(ModBLocks.REDKEEP_25_SLAB.get())
                .add(ModBLocks.REDKEEP_25_WALL.get())

                .add(ModBLocks.REDKEEP_26_BLOCK.get())
                .add(ModBLocks.REDKEEP_26_STAIRS.get())
                .add(ModBLocks.REDKEEP_26_SLAB.get())
                .add(ModBLocks.REDKEEP_26_WALL.get())

                .add(ModBLocks.REDKEEP_27_BLOCK.get())
                .add(ModBLocks.REDKEEP_27_STAIRS.get())
                .add(ModBLocks.REDKEEP_27_SLAB.get())
                .add(ModBLocks.REDKEEP_27_WALL.get())

                .add(ModBLocks.REDKEEP_28_BLOCK.get())
                .add(ModBLocks.REDKEEP_28_STAIRS.get())
                .add(ModBLocks.REDKEEP_28_SLAB.get())
                .add(ModBLocks.REDKEEP_28_WALL.get())

                .add(ModBLocks.REDKEEP_29_BLOCK.get())
                .add(ModBLocks.REDKEEP_29_STAIRS.get())
                .add(ModBLocks.REDKEEP_29_SLAB.get())
                .add(ModBLocks.REDKEEP_29_WALL.get())

                .add(ModBLocks.REDKEEP_30_BLOCK.get())
                .add(ModBLocks.REDKEEP_30_STAIRS.get())
                .add(ModBLocks.REDKEEP_30_SLAB.get())
                .add(ModBLocks.REDKEEP_30_WALL.get())

                .add(ModBLocks.REDKEEP_31_BLOCK.get())
                .add(ModBLocks.REDKEEP_31_STAIRS.get())
                .add(ModBLocks.REDKEEP_31_SLAB.get())
                .add(ModBLocks.REDKEEP_31_WALL.get())

                .add(ModBLocks.REDKEEP_32_BLOCK.get())
                .add(ModBLocks.REDKEEP_32_STAIRS.get())
                .add(ModBLocks.REDKEEP_32_SLAB.get())
                .add(ModBLocks.REDKEEP_32_WALL.get())

                .add(ModBLocks.REDKEEP_33_BLOCK.get())
                .add(ModBLocks.REDKEEP_33_STAIRS.get())
                .add(ModBLocks.REDKEEP_33_SLAB.get())
                .add(ModBLocks.REDKEEP_33_WALL.get())

                .add(ModBLocks.REDKEEP_34_BLOCK.get())
                .add(ModBLocks.REDKEEP_34_STAIRS.get())
                .add(ModBLocks.REDKEEP_34_SLAB.get())
                .add(ModBLocks.REDKEEP_34_WALL.get())

                .add(ModBLocks.REDKEEP_35_BLOCK.get())
                .add(ModBLocks.REDKEEP_35_STAIRS.get())
                .add(ModBLocks.REDKEEP_35_SLAB.get())
                .add(ModBLocks.REDKEEP_35_WALL.get())

                .add(ModBLocks.REDKEEP_36_BLOCK.get())
                .add(ModBLocks.REDKEEP_36_STAIRS.get())
                .add(ModBLocks.REDKEEP_36_SLAB.get())
                .add(ModBLocks.REDKEEP_36_WALL.get())

                .add(ModBLocks.REDKEEP_37_BLOCK.get())
                .add(ModBLocks.REDKEEP_37_STAIRS.get())
                .add(ModBLocks.REDKEEP_37_SLAB.get())
                .add(ModBLocks.REDKEEP_37_WALL.get())

                .add(ModBLocks.REDKEEP_38_BLOCK.get())
                .add(ModBLocks.REDKEEP_38_STAIRS.get())
                .add(ModBLocks.REDKEEP_38_SLAB.get())
                .add(ModBLocks.REDKEEP_38_WALL.get())

                                .add(ModBLocks.STONE_1_BLOCK.get())
                .add(ModBLocks.STONE_1_STAIRS.get())
                .add(ModBLocks.STONE_1_SLAB.get())
                .add(ModBLocks.STONE_1_WALL.get())

                .add(ModBLocks.STONE_2_BLOCK.get())
                .add(ModBLocks.STONE_2_STAIRS.get())
                .add(ModBLocks.STONE_2_SLAB.get())
                .add(ModBLocks.STONE_2_WALL.get())

                .add(ModBLocks.STONE_3_BLOCK.get())
                .add(ModBLocks.STONE_3_STAIRS.get())
                .add(ModBLocks.STONE_3_SLAB.get())
                .add(ModBLocks.STONE_3_WALL.get())

                .add(ModBLocks.STONE_4_BLOCK.get())
                .add(ModBLocks.STONE_4_STAIRS.get())
                .add(ModBLocks.STONE_4_SLAB.get())
                .add(ModBLocks.STONE_4_WALL.get())

                .add(ModBLocks.STONE_6_BLOCK.get())
                .add(ModBLocks.STONE_6_STAIRS.get())
                .add(ModBLocks.STONE_6_SLAB.get())
                .add(ModBLocks.STONE_6_WALL.get())

                .add(ModBLocks.STONE_7_BLOCK.get())
                .add(ModBLocks.STONE_7_STAIRS.get())
                .add(ModBLocks.STONE_7_SLAB.get())
                .add(ModBLocks.STONE_7_WALL.get())

                .add(ModBLocks.STONE_8_BLOCK.get())
                .add(ModBLocks.STONE_8_STAIRS.get())
                .add(ModBLocks.STONE_8_SLAB.get())
                .add(ModBLocks.STONE_8_WALL.get())

                .add(ModBLocks.STONE_9_BLOCK.get())
                .add(ModBLocks.STONE_9_STAIRS.get())
                .add(ModBLocks.STONE_9_SLAB.get())
                .add(ModBLocks.STONE_9_WALL.get())

                .add(ModBLocks.STONE_10_BLOCK.get())
                .add(ModBLocks.STONE_10_STAIRS.get())
                .add(ModBLocks.STONE_10_SLAB.get())
                .add(ModBLocks.STONE_10_WALL.get())

                .add(ModBLocks.STONE_11_BLOCK.get())
                .add(ModBLocks.STONE_11_STAIRS.get())
                .add(ModBLocks.STONE_11_SLAB.get())
                .add(ModBLocks.STONE_11_WALL.get())

                .add(ModBLocks.STONE_12_BLOCK.get())
                .add(ModBLocks.STONE_12_STAIRS.get())
                .add(ModBLocks.STONE_12_SLAB.get())
                .add(ModBLocks.STONE_12_WALL.get())

                .add(ModBLocks.STONE_13_BLOCK.get())
                .add(ModBLocks.STONE_13_STAIRS.get())
                .add(ModBLocks.STONE_13_SLAB.get())
                .add(ModBLocks.STONE_13_WALL.get())

                .add(ModBLocks.STONE_14_BLOCK.get())
                .add(ModBLocks.STONE_14_STAIRS.get())
                .add(ModBLocks.STONE_14_SLAB.get())
                .add(ModBLocks.STONE_14_WALL.get())

                // .add(ModBLocks.STONE_15_BLOCK.get())
                // .add(ModBLocks.STONE_15_STAIRS.get())
                // .add(ModBLocks.STONE_15_SLAB.get())
                // .add(ModBLocks.STONE_15_WALL.get())

                .add(ModBLocks.STONE_20_BLOCK.get())
                .add(ModBLocks.STONE_20_STAIRS.get())
                .add(ModBLocks.STONE_20_SLAB.get())
                .add(ModBLocks.STONE_20_WALL.get())

                .add(ModBLocks.STONE_21_BLOCK.get())
                .add(ModBLocks.STONE_21_STAIRS.get())
                .add(ModBLocks.STONE_21_SLAB.get())
                .add(ModBLocks.STONE_21_WALL.get())

                .add(ModBLocks.STONE_22_BLOCK.get())
                .add(ModBLocks.STONE_22_STAIRS.get())
                .add(ModBLocks.STONE_22_SLAB.get())
                .add(ModBLocks.STONE_22_WALL.get())

                .add(ModBLocks.STONE_24_BLOCK.get())
                .add(ModBLocks.STONE_24_STAIRS.get())
                .add(ModBLocks.STONE_24_SLAB.get())
                .add(ModBLocks.STONE_24_WALL.get())

                .add(ModBLocks.STONE_25_BLOCK.get())
                .add(ModBLocks.STONE_25_STAIRS.get())
                .add(ModBLocks.STONE_25_SLAB.get())
                .add(ModBLocks.STONE_25_WALL.get())

                .add(ModBLocks.STONE_26_BLOCK.get())
                .add(ModBLocks.STONE_26_STAIRS.get())
                .add(ModBLocks.STONE_26_SLAB.get())
                .add(ModBLocks.STONE_26_WALL.get())

                .add(ModBLocks.STONE_27_BLOCK.get())
                .add(ModBLocks.STONE_27_STAIRS.get())
                .add(ModBLocks.STONE_27_SLAB.get())
                .add(ModBLocks.STONE_27_WALL.get())

                .add(ModBLocks.STONE_28_BLOCK.get())
                .add(ModBLocks.STONE_28_STAIRS.get())
                .add(ModBLocks.STONE_28_SLAB.get())
                .add(ModBLocks.STONE_28_WALL.get())

                .add(ModBLocks.STONE_29_BLOCK.get())
                .add(ModBLocks.STONE_29_STAIRS.get())
                .add(ModBLocks.STONE_29_SLAB.get())
                .add(ModBLocks.STONE_29_WALL.get())

                .add(ModBLocks.STONE_30_BLOCK.get())
                .add(ModBLocks.STONE_30_STAIRS.get())
                .add(ModBLocks.STONE_30_SLAB.get())
                .add(ModBLocks.STONE_30_WALL.get())

                .add(ModBLocks.STONE_31_BLOCK.get())
                .add(ModBLocks.STONE_31_STAIRS.get())
                .add(ModBLocks.STONE_31_SLAB.get())
                .add(ModBLocks.STONE_31_WALL.get())

                .add(ModBLocks.STONE_32_BLOCK.get())
                .add(ModBLocks.STONE_32_STAIRS.get())
                .add(ModBLocks.STONE_32_SLAB.get())
                .add(ModBLocks.STONE_32_WALL.get())

                .add(ModBLocks.STONE_33_BLOCK.get())
                .add(ModBLocks.STONE_33_STAIRS.get())
                .add(ModBLocks.STONE_33_SLAB.get())
                .add(ModBLocks.STONE_33_WALL.get())

                .add(ModBLocks.STONE_34_BLOCK.get())
                .add(ModBLocks.STONE_34_STAIRS.get())
                .add(ModBLocks.STONE_34_SLAB.get())
                .add(ModBLocks.STONE_34_WALL.get())

                .add(ModBLocks.STONE_35_BLOCK.get())
                .add(ModBLocks.STONE_35_STAIRS.get())
                .add(ModBLocks.STONE_35_SLAB.get())
                .add(ModBLocks.STONE_35_WALL.get())

                .add(ModBLocks.STONE_36_BLOCK.get())
                .add(ModBLocks.STONE_36_STAIRS.get())
                .add(ModBLocks.STONE_36_SLAB.get())
                .add(ModBLocks.STONE_36_WALL.get())

                .add(ModBLocks.STONE_37_BLOCK.get())
                .add(ModBLocks.STONE_37_STAIRS.get())
                .add(ModBLocks.STONE_37_SLAB.get())
                .add(ModBLocks.STONE_37_WALL.get())

                .add(ModBLocks.STONE_38_BLOCK.get())
                .add(ModBLocks.STONE_38_STAIRS.get())
                .add(ModBLocks.STONE_38_SLAB.get())
                .add(ModBLocks.STONE_38_WALL.get())
        ;



        //Weirwood
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.WEIRWOOD_LOG.get())
                .add(ModBLocks.STRIPPED_WEIRWOOD_LOG.get())
                .add(ModBLocks.WEIRWOOD_WOOD.get())
                .add(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get());
        this.tag(BlockTags.FENCES)
                .add(ModBLocks.WEIRWOOD_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.WEIRWOOD_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.WEIRWOOD_WALL.get());


        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.WEIRWOOD_PLANKS.get());
        //Sycamore
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.SYCAMORE_LOG.get())
                .add(ModBLocks.STRIPPED_SYCAMORE_LOG.get())
                .add(ModBLocks.SYCAMORE_WOOD.get())
                .add(ModBLocks.STRIPPED_SYCAMORE_WOOD.get());
        this.tag(BlockTags.FENCES)
                .add(ModBLocks.SYCAMORE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.SYCAMORE_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.SYCAMORE_WALL.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.SYCAMORE_PLANKS.get());
        //Sentinel
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.SENTINEL_LOG.get())
                .add(ModBLocks.STRIPPED_SENTINEL_LOG.get())
                .add(ModBLocks.SENTINEL_WOOD.get())
                .add(ModBLocks.STRIPPED_SENTINEL_WOOD.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.SENTINEL_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.SENTINEL_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.SENTINEL_WALL.get());


        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.SENTINEL_PLANKS.get());


        //Pine
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.PINE_LOG.get())
                .add(ModBLocks.STRIPPED_PINE_LOG.get())
                .add(ModBLocks.PINE_WOOD.get())
                .add(ModBLocks.STRIPPED_PINE_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.PINE_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.PINE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.PINE_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.PINE_WALL.get());


        //Ironwood
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.IRONWOOD_LOG.get())
                .add(ModBLocks.STRIPPED_IRONWOOD_LOG.get())
                .add(ModBLocks.IRONWOOD_WOOD.get())
                .add(ModBLocks.STRIPPED_IRONWOOD_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.IRONWOOD_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.IRONWOOD_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.IRONWOOD_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.IRONWOOD_WALL.get());


        //Hawthorn
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.HAWTHORN_LOG.get())
                .add(ModBLocks.STRIPPED_HAWTHORN_LOG.get())
                .add(ModBLocks.HAWTHORN_WOOD.get())
                .add(ModBLocks.STRIPPED_HAWTHORN_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.HAWTHORN_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.HAWTHORN_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.HAWTHORN_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.HAWTHORN_WALL.get());


        //Chestnut
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.CHESTNUT_LOG.get())
                .add(ModBLocks.STRIPPED_CHESTNUT_LOG.get())
                .add(ModBLocks.CHESTNUT_WOOD.get())
                .add(ModBLocks.STRIPPED_CHESTNUT_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.CHESTNUT_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.CHESTNUT_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.CHESTNUT_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.CHESTNUT_WALL.get());

        //Cedar
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.CEDAR_LOG.get())
                .add(ModBLocks.STRIPPED_CEDAR_LOG.get())
                .add(ModBLocks.CEDAR_WOOD.get())
                .add(ModBLocks.STRIPPED_CEDAR_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.CEDAR_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.CEDAR_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.CEDAR_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.CEDAR_WALL.get());

        //Beech
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.BEECH_LOG.get())
                .add(ModBLocks.STRIPPED_BEECH_LOG.get())
                .add(ModBLocks.BEECH_WOOD.get())
                .add(ModBLocks.STRIPPED_BEECH_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.BEECH_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.BEECH_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.BEECH_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.BEECH_WALL.get());


        //Ash
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.ASH_LOG.get())
                .add(ModBLocks.STRIPPED_ASH_LOG.get())
                .add(ModBLocks.ASH_WOOD.get())
                .add(ModBLocks.STRIPPED_ASH_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.ASH_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.ASH_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.ASH_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.ASH_WALL.get());


        //Blackbark
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.BLACKBARK_LOG.get())
                .add(ModBLocks.STRIPPED_BLACKBARK_LOG.get())
                .add(ModBLocks.BLACKBARK_WOOD.get())
                .add(ModBLocks.STRIPPED_BLACKBARK_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.BLACKBARK_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.BLACKBARK_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.BLACKBARK_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.BLACKBARK_WALL.get());


        //Aspen
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.ASPEN_LOG.get())
                .add(ModBLocks.STRIPPED_ASPEN_LOG.get())
                .add(ModBLocks.ASPEN_WOOD.get())
                .add(ModBLocks.STRIPPED_ASPEN_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.ASPEN_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.ASPEN_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.ASPEN_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.ASPEN_WALL.get());


        //Alder
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.ALDER_LOG.get())
                .add(ModBLocks.STRIPPED_ALDER_LOG.get())
                .add(ModBLocks.ALDER_WOOD.get())
                .add(ModBLocks.STRIPPED_ALDER_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.ALDER_PLANKS.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.ALDER_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.ALDER_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.ALDER_WALL.get());


        //Stone Blocks Organised

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.SSTONE_1_BLOCK.get())
                .add(ModBLocks.SSTONE_1_STAIRS.get())
                .add(ModBLocks.SSTONE_1_SLAB.get())
                .add(ModBLocks.SSTONE_1_WALL.get())
                .add(ModBLocks.SSTONE_2_BLOCK.get())
                .add(ModBLocks.SSTONE_2_STAIRS.get())
                .add(ModBLocks.SSTONE_2_SLAB.get())
                .add(ModBLocks.SSTONE_2_WALL.get())
                .add(ModBLocks.SSTONE_3_BLOCK.get())
                .add(ModBLocks.SSTONE_3_STAIRS.get())
                .add(ModBLocks.SSTONE_3_SLAB.get())
                .add(ModBLocks.SSTONE_3_WALL.get())
                .add(ModBLocks.SSTONE_4_BLOCK.get())
                .add(ModBLocks.SSTONE_4_STAIRS.get())
                .add(ModBLocks.SSTONE_4_SLAB.get())
                .add(ModBLocks.SSTONE_4_WALL.get())
                .add(ModBLocks.SSTONE_5_BLOCK.get())
                .add(ModBLocks.SSTONE_5_STAIRS.get())
                .add(ModBLocks.SSTONE_5_SLAB.get())
                .add(ModBLocks.SSTONE_5_WALL.get())
                .add(ModBLocks.SSTONE_6_BLOCK.get())
                .add(ModBLocks.SSTONE_6_STAIRS.get())
                .add(ModBLocks.SSTONE_6_SLAB.get())
                .add(ModBLocks.SSTONE_6_WALL.get())
                .add(ModBLocks.SSTONE_7_BLOCK.get())
                .add(ModBLocks.SSTONE_7_STAIRS.get())
                .add(ModBLocks.SSTONE_7_SLAB.get())
                .add(ModBLocks.SSTONE_7_WALL.get())
                .add(ModBLocks.SSTONE_8_BLOCK.get())
                .add(ModBLocks.SSTONE_8_STAIRS.get())
                .add(ModBLocks.SSTONE_8_SLAB.get())
                .add(ModBLocks.SSTONE_8_WALL.get())
                .add(ModBLocks.SSTONE_9_BLOCK.get())
                .add(ModBLocks.SSTONE_9_STAIRS.get())
                .add(ModBLocks.SSTONE_9_SLAB.get())
                .add(ModBLocks.SSTONE_9_WALL.get())
                .add(ModBLocks.SSTONE_10_BLOCK.get())
                .add(ModBLocks.SSTONE_10_STAIRS.get())
                .add(ModBLocks.SSTONE_10_SLAB.get())
                .add(ModBLocks.SSTONE_10_WALL.get())
                .add(ModBLocks.SSTONE_11_BLOCK.get())
                .add(ModBLocks.SSTONE_11_STAIRS.get())
                .add(ModBLocks.SSTONE_11_SLAB.get())
                .add(ModBLocks.SSTONE_11_WALL.get())
                .add(ModBLocks.SSTONE_12_BLOCK.get())
                .add(ModBLocks.SSTONE_12_STAIRS.get())
                .add(ModBLocks.SSTONE_12_SLAB.get())
                .add(ModBLocks.SSTONE_12_WALL.get())
                .add(ModBLocks.SSTONE_13_BLOCK.get())
                .add(ModBLocks.SSTONE_13_STAIRS.get())
                .add(ModBLocks.SSTONE_13_SLAB.get())
                .add(ModBLocks.SSTONE_13_WALL.get())
                .add(ModBLocks.SSTONE_14_BLOCK.get())
                .add(ModBLocks.SSTONE_14_STAIRS.get())
                .add(ModBLocks.SSTONE_14_SLAB.get())
                .add(ModBLocks.SSTONE_14_WALL.get())
                // .add(ModBLocks.SSTONE_15_BLOCK.get())
                // .add(ModBLocks.SSTONE_15_STAIRS.get())
                // .add(ModBLocks.SSTONE_15_SLAB.get())
                // .add(ModBLocks.SSTONE_15_WALL.get())
                .add(ModBLocks.SSTONE_16_BLOCK.get())
                .add(ModBLocks.SSTONE_16_STAIRS.get())
                .add(ModBLocks.SSTONE_16_SLAB.get())
                .add(ModBLocks.SSTONE_16_WALL.get())
                .add(ModBLocks.SSTONE_17_BLOCK.get())
                .add(ModBLocks.SSTONE_17_STAIRS.get())
                .add(ModBLocks.SSTONE_17_SLAB.get())
                .add(ModBLocks.SSTONE_17_WALL.get())
                .add(ModBLocks.SSTONE_18_BLOCK.get())
                .add(ModBLocks.SSTONE_18_STAIRS.get())
                .add(ModBLocks.SSTONE_18_SLAB.get())
                .add(ModBLocks.SSTONE_18_WALL.get())
                .add(ModBLocks.SSTONE_19_BLOCK.get())
                .add(ModBLocks.SSTONE_19_STAIRS.get())
                .add(ModBLocks.SSTONE_19_SLAB.get())
                .add(ModBLocks.SSTONE_19_WALL.get())
                .add(ModBLocks.SSTONE_20_BLOCK.get())
                .add(ModBLocks.SSTONE_20_STAIRS.get())
                .add(ModBLocks.SSTONE_20_SLAB.get())
                .add(ModBLocks.SSTONE_20_WALL.get())
                .add(ModBLocks.SSTONE_21_BLOCK.get())
                .add(ModBLocks.SSTONE_21_STAIRS.get())
                .add(ModBLocks.SSTONE_21_SLAB.get())
                .add(ModBLocks.SSTONE_21_WALL.get())
                .add(ModBLocks.SSTONE_22_BLOCK.get())
                .add(ModBLocks.SSTONE_22_STAIRS.get())
                .add(ModBLocks.SSTONE_22_SLAB.get())
                .add(ModBLocks.SSTONE_22_WALL.get())
                .add(ModBLocks.RSANDSTONE_1_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_1_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_1_SLAB.get())
                .add(ModBLocks.RSANDSTONE_1_WALL.get())
                .add(ModBLocks.RSANDSTONE_2_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_2_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_2_SLAB.get())
                .add(ModBLocks.RSANDSTONE_2_WALL.get())
                .add(ModBLocks.RSANDSTONE_3_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_3_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_3_SLAB.get())
                .add(ModBLocks.RSANDSTONE_3_WALL.get())
                .add(ModBLocks.RSANDSTONE_4_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_4_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_4_SLAB.get())
                .add(ModBLocks.RSANDSTONE_4_WALL.get())
                .add(ModBLocks.RSANDSTONE_5_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_5_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_5_SLAB.get())
                .add(ModBLocks.RSANDSTONE_5_WALL.get())
                .add(ModBLocks.RSANDSTONE_6_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_6_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_6_SLAB.get())
                .add(ModBLocks.RSANDSTONE_6_WALL.get())
                .add(ModBLocks.RSANDSTONE_7_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_7_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_7_SLAB.get())
                .add(ModBLocks.RSANDSTONE_7_WALL.get())
                .add(ModBLocks.RSANDSTONE_8_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_8_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_8_SLAB.get())
                .add(ModBLocks.RSANDSTONE_8_WALL.get())
                .add(ModBLocks.RSANDSTONE_9_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_9_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_9_SLAB.get())
                .add(ModBLocks.RSANDSTONE_9_WALL.get())
                .add(ModBLocks.RSANDSTONE_10_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_10_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_10_SLAB.get())
                .add(ModBLocks.RSANDSTONE_10_WALL.get())
                .add(ModBLocks.RSANDSTONE_11_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_11_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_11_SLAB.get())
                .add(ModBLocks.RSANDSTONE_11_WALL.get())
                .add(ModBLocks.RSANDSTONE_12_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_12_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_12_SLAB.get())
                .add(ModBLocks.RSANDSTONE_12_WALL.get())
                .add(ModBLocks.RSANDSTONE_13_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_13_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_13_SLAB.get())
                .add(ModBLocks.RSANDSTONE_13_WALL.get())
                .add(ModBLocks.RSANDSTONE_14_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_14_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_14_SLAB.get())
                .add(ModBLocks.RSANDSTONE_14_WALL.get())
// .add(ModBLocks.RSANDSTONE_15_BLOCK.get())
// .add(ModBLocks.RSANDSTONE_15_STAIRS.get())
// .add(ModBLocks.RSANDSTONE_15_SLAB.get())
// .add(ModBLocks.RSANDSTONE_15_WALL.get())
                .add(ModBLocks.RSANDSTONE_19_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_19_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_19_SLAB.get())
                .add(ModBLocks.RSANDSTONE_19_WALL.get())
                .add(ModBLocks.RSANDSTONE_20_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_20_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_20_SLAB.get())
                .add(ModBLocks.RSANDSTONE_20_WALL.get())
.add(ModBLocks.RSANDSTONE_21_BLOCK.get())
.add(ModBLocks.RSANDSTONE_21_STAIRS.get())
.add(ModBLocks.RSANDSTONE_21_SLAB.get())
.add(ModBLocks.RSANDSTONE_21_WALL.get())
                .add(ModBLocks.RSANDSTONE_22_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_22_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_22_SLAB.get())
                .add(ModBLocks.RSANDSTONE_22_WALL.get())
                .add(ModBLocks.RSANDSTONE_23_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_23_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_23_SLAB.get())
                .add(ModBLocks.RSANDSTONE_23_WALL.get())

                .add(ModBLocks.RSANDSTONE_24_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_24_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_24_SLAB.get())
                .add(ModBLocks.RSANDSTONE_24_WALL.get())

                .add(ModBLocks.RSANDSTONE_25_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_25_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_25_SLAB.get())
                .add(ModBLocks.RSANDSTONE_25_WALL.get())

                .add(ModBLocks.RSANDSTONE_26_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_26_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_26_SLAB.get())
                .add(ModBLocks.RSANDSTONE_26_WALL.get())

                .add(ModBLocks.RSANDSTONE_27_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_27_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_27_SLAB.get())
                .add(ModBLocks.RSANDSTONE_27_WALL.get())

                .add(ModBLocks.RSANDSTONE_28_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_28_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_28_SLAB.get())
                .add(ModBLocks.RSANDSTONE_28_WALL.get())

                .add(ModBLocks.RSANDSTONE_29_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_29_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_29_SLAB.get())
                .add(ModBLocks.RSANDSTONE_29_WALL.get())

                .add(ModBLocks.RSANDSTONE_30_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_30_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_30_SLAB.get())
                .add(ModBLocks.RSANDSTONE_30_WALL.get())

                .add(ModBLocks.RSANDSTONE_31_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_31_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_31_SLAB.get())
                .add(ModBLocks.RSANDSTONE_31_WALL.get())

                .add(ModBLocks.RSANDSTONE_32_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_32_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_32_SLAB.get())
                .add(ModBLocks.RSANDSTONE_32_WALL.get())

                .add(ModBLocks.RSANDSTONE_33_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_33_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_33_SLAB.get())
                .add(ModBLocks.RSANDSTONE_33_WALL.get())

                .add(ModBLocks.RSANDSTONE_34_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_34_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_34_SLAB.get())
                .add(ModBLocks.RSANDSTONE_34_WALL.get())

                .add(ModBLocks.RSANDSTONE_35_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_35_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_35_SLAB.get())
                .add(ModBLocks.RSANDSTONE_35_WALL.get())

                .add(ModBLocks.RSANDSTONE_36_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_36_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_36_SLAB.get())
                .add(ModBLocks.RSANDSTONE_36_WALL.get())

                .add(ModBLocks.RSANDSTONE_37_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_37_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_37_SLAB.get())
                .add(ModBLocks.RSANDSTONE_37_WALL.get())

                .add(ModBLocks.RSANDSTONE_38_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_38_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_38_SLAB.get())
                .add(ModBLocks.RSANDSTONE_38_WALL.get())

                .add(ModBLocks.REDKEEP_STONE_BLOCK.get())
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get())
                .add(ModBLocks.REDKEEP_STONE_SLAB.get())
                .add(ModBLocks.REDKEEP_STONE_WALL.get())


                .add(ModBLocks.REDKEEP_1_BLOCK.get())
                .add(ModBLocks.REDKEEP_1_STAIRS.get())
                .add(ModBLocks.REDKEEP_1_SLAB.get())
                .add(ModBLocks.REDKEEP_1_WALL.get())

                .add(ModBLocks.REDKEEP_2_BLOCK.get())
                .add(ModBLocks.REDKEEP_2_STAIRS.get())
                .add(ModBLocks.REDKEEP_2_SLAB.get())
                .add(ModBLocks.REDKEEP_2_WALL.get())

                .add(ModBLocks.REDKEEP_3_BLOCK.get())
                .add(ModBLocks.REDKEEP_3_STAIRS.get())
                .add(ModBLocks.REDKEEP_3_SLAB.get())
                .add(ModBLocks.REDKEEP_3_WALL.get())

                .add(ModBLocks.REDKEEP_4_BLOCK.get())
                .add(ModBLocks.REDKEEP_4_STAIRS.get())
                .add(ModBLocks.REDKEEP_4_SLAB.get())
                .add(ModBLocks.REDKEEP_4_WALL.get())

                .add(ModBLocks.REDKEEP_5_BLOCK.get())
                .add(ModBLocks.REDKEEP_5_STAIRS.get())
                .add(ModBLocks.REDKEEP_5_SLAB.get())
                .add(ModBLocks.REDKEEP_5_WALL.get())

                .add(ModBLocks.REDKEEP_6_BLOCK.get())
                .add(ModBLocks.REDKEEP_6_STAIRS.get())
                .add(ModBLocks.REDKEEP_6_SLAB.get())
                .add(ModBLocks.REDKEEP_6_WALL.get())

                .add(ModBLocks.REDKEEP_7_BLOCK.get())
                .add(ModBLocks.REDKEEP_7_STAIRS.get())
                .add(ModBLocks.REDKEEP_7_SLAB.get())
                .add(ModBLocks.REDKEEP_7_WALL.get())

                .add(ModBLocks.REDKEEP_8_BLOCK.get())
                .add(ModBLocks.REDKEEP_8_STAIRS.get())
                .add(ModBLocks.REDKEEP_8_SLAB.get())
                .add(ModBLocks.REDKEEP_8_WALL.get())

                .add(ModBLocks.REDKEEP_9_BLOCK.get())
                .add(ModBLocks.REDKEEP_9_STAIRS.get())
                .add(ModBLocks.REDKEEP_9_SLAB.get())
                .add(ModBLocks.REDKEEP_9_WALL.get())

                .add(ModBLocks.REDKEEP_10_BLOCK.get())
                .add(ModBLocks.REDKEEP_10_STAIRS.get())
                .add(ModBLocks.REDKEEP_10_SLAB.get())
                .add(ModBLocks.REDKEEP_10_WALL.get())

                .add(ModBLocks.REDKEEP_11_BLOCK.get())
                .add(ModBLocks.REDKEEP_11_STAIRS.get())
                .add(ModBLocks.REDKEEP_11_SLAB.get())
                .add(ModBLocks.REDKEEP_11_WALL.get())

                .add(ModBLocks.REDKEEP_12_BLOCK.get())
                .add(ModBLocks.REDKEEP_12_STAIRS.get())
                .add(ModBLocks.REDKEEP_12_SLAB.get())
                .add(ModBLocks.REDKEEP_12_WALL.get())

                .add(ModBLocks.REDKEEP_13_BLOCK.get())
                .add(ModBLocks.REDKEEP_13_STAIRS.get())
                .add(ModBLocks.REDKEEP_13_SLAB.get())
                .add(ModBLocks.REDKEEP_13_WALL.get())

                .add(ModBLocks.REDKEEP_14_BLOCK.get())
                .add(ModBLocks.REDKEEP_14_STAIRS.get())
                .add(ModBLocks.REDKEEP_14_SLAB.get())
                .add(ModBLocks.REDKEEP_14_WALL.get())

                // .add(ModBLocks.REDKEEP_15_BLOCK.get())
                // .add(ModBLocks.REDKEEP_15_STAIRS.get())
                // .add(ModBLocks.REDKEEP_15_SLAB.get())
                // .add(ModBLocks.REDKEEP_15_WALL.get())

                .add(ModBLocks.REDKEEP_19_BLOCK.get())
                .add(ModBLocks.REDKEEP_19_STAIRS.get())
                .add(ModBLocks.REDKEEP_19_SLAB.get())
                .add(ModBLocks.REDKEEP_19_WALL.get())

                .add(ModBLocks.REDKEEP_20_BLOCK.get())
                .add(ModBLocks.REDKEEP_20_STAIRS.get())
                .add(ModBLocks.REDKEEP_20_SLAB.get())
                .add(ModBLocks.REDKEEP_20_WALL.get())

                .add(ModBLocks.REDKEEP_21_BLOCK.get())
                .add(ModBLocks.REDKEEP_21_STAIRS.get())
                .add(ModBLocks.REDKEEP_21_SLAB.get())
                .add(ModBLocks.REDKEEP_21_WALL.get())

                .add(ModBLocks.REDKEEP_22_BLOCK.get())
                .add(ModBLocks.REDKEEP_22_STAIRS.get())
                .add(ModBLocks.REDKEEP_22_SLAB.get())
                .add(ModBLocks.REDKEEP_22_WALL.get())

                .add(ModBLocks.REDKEEP_23_BLOCK.get())
                .add(ModBLocks.REDKEEP_23_STAIRS.get())
                .add(ModBLocks.REDKEEP_23_SLAB.get())
                .add(ModBLocks.REDKEEP_23_WALL.get())

                .add(ModBLocks.REDKEEP_24_BLOCK.get())
                .add(ModBLocks.REDKEEP_24_STAIRS.get())
                .add(ModBLocks.REDKEEP_24_SLAB.get())
                .add(ModBLocks.REDKEEP_24_WALL.get())

                .add(ModBLocks.REDKEEP_25_BLOCK.get())
                .add(ModBLocks.REDKEEP_25_STAIRS.get())
                .add(ModBLocks.REDKEEP_25_SLAB.get())
                .add(ModBLocks.REDKEEP_25_WALL.get())

                .add(ModBLocks.REDKEEP_26_BLOCK.get())
                .add(ModBLocks.REDKEEP_26_STAIRS.get())
                .add(ModBLocks.REDKEEP_26_SLAB.get())
                .add(ModBLocks.REDKEEP_26_WALL.get())

                .add(ModBLocks.REDKEEP_27_BLOCK.get())
                .add(ModBLocks.REDKEEP_27_STAIRS.get())
                .add(ModBLocks.REDKEEP_27_SLAB.get())
                .add(ModBLocks.REDKEEP_27_WALL.get())

                .add(ModBLocks.REDKEEP_28_BLOCK.get())
                .add(ModBLocks.REDKEEP_28_STAIRS.get())
                .add(ModBLocks.REDKEEP_28_SLAB.get())
                .add(ModBLocks.REDKEEP_28_WALL.get())

                .add(ModBLocks.REDKEEP_29_BLOCK.get())
                .add(ModBLocks.REDKEEP_29_STAIRS.get())
                .add(ModBLocks.REDKEEP_29_SLAB.get())
                .add(ModBLocks.REDKEEP_29_WALL.get())

                .add(ModBLocks.REDKEEP_30_BLOCK.get())
                .add(ModBLocks.REDKEEP_30_STAIRS.get())
                .add(ModBLocks.REDKEEP_30_SLAB.get())
                .add(ModBLocks.REDKEEP_30_WALL.get())

                .add(ModBLocks.REDKEEP_31_BLOCK.get())
                .add(ModBLocks.REDKEEP_31_STAIRS.get())
                .add(ModBLocks.REDKEEP_31_SLAB.get())
                .add(ModBLocks.REDKEEP_31_WALL.get())

                .add(ModBLocks.REDKEEP_32_BLOCK.get())
                .add(ModBLocks.REDKEEP_32_STAIRS.get())
                .add(ModBLocks.REDKEEP_32_SLAB.get())
                .add(ModBLocks.REDKEEP_32_WALL.get())

                .add(ModBLocks.REDKEEP_33_BLOCK.get())
                .add(ModBLocks.REDKEEP_33_STAIRS.get())
                .add(ModBLocks.REDKEEP_33_SLAB.get())
                .add(ModBLocks.REDKEEP_33_WALL.get())

                .add(ModBLocks.REDKEEP_34_BLOCK.get())
                .add(ModBLocks.REDKEEP_34_STAIRS.get())
                .add(ModBLocks.REDKEEP_34_SLAB.get())
                .add(ModBLocks.REDKEEP_34_WALL.get())

                .add(ModBLocks.REDKEEP_35_BLOCK.get())
                .add(ModBLocks.REDKEEP_35_STAIRS.get())
                .add(ModBLocks.REDKEEP_35_SLAB.get())
                .add(ModBLocks.REDKEEP_35_WALL.get())

                .add(ModBLocks.REDKEEP_36_BLOCK.get())
                .add(ModBLocks.REDKEEP_36_STAIRS.get())
                .add(ModBLocks.REDKEEP_36_SLAB.get())
                .add(ModBLocks.REDKEEP_36_WALL.get())

                .add(ModBLocks.REDKEEP_37_BLOCK.get())
                .add(ModBLocks.REDKEEP_37_STAIRS.get())
                .add(ModBLocks.REDKEEP_37_SLAB.get())
                .add(ModBLocks.REDKEEP_37_WALL.get())

                .add(ModBLocks.REDKEEP_38_BLOCK.get())
                .add(ModBLocks.REDKEEP_38_STAIRS.get())
                .add(ModBLocks.REDKEEP_38_SLAB.get())
                .add(ModBLocks.REDKEEP_38_WALL.get())

                        .add(ModBLocks.STONE_1_BLOCK.get())
                .add(ModBLocks.STONE_1_STAIRS.get())
                .add(ModBLocks.STONE_1_SLAB.get())
                .add(ModBLocks.STONE_1_WALL.get())

                .add(ModBLocks.STONE_2_BLOCK.get())
                .add(ModBLocks.STONE_2_STAIRS.get())
                .add(ModBLocks.STONE_2_SLAB.get())
                .add(ModBLocks.STONE_2_WALL.get())

                .add(ModBLocks.STONE_3_BLOCK.get())
                .add(ModBLocks.STONE_3_STAIRS.get())
                .add(ModBLocks.STONE_3_SLAB.get())
                .add(ModBLocks.STONE_3_WALL.get())

                .add(ModBLocks.STONE_4_BLOCK.get())
                .add(ModBLocks.STONE_4_STAIRS.get())
                .add(ModBLocks.STONE_4_SLAB.get())
                .add(ModBLocks.STONE_4_WALL.get())

                .add(ModBLocks.STONE_6_BLOCK.get())
                .add(ModBLocks.STONE_6_STAIRS.get())
                .add(ModBLocks.STONE_6_SLAB.get())
                .add(ModBLocks.STONE_6_WALL.get())

                .add(ModBLocks.STONE_7_BLOCK.get())
                .add(ModBLocks.STONE_7_STAIRS.get())
                .add(ModBLocks.STONE_7_SLAB.get())
                .add(ModBLocks.STONE_7_WALL.get())

                .add(ModBLocks.STONE_8_BLOCK.get())
                .add(ModBLocks.STONE_8_STAIRS.get())
                .add(ModBLocks.STONE_8_SLAB.get())
                .add(ModBLocks.STONE_8_WALL.get())

                .add(ModBLocks.STONE_9_BLOCK.get())
                .add(ModBLocks.STONE_9_STAIRS.get())
                .add(ModBLocks.STONE_9_SLAB.get())
                .add(ModBLocks.STONE_9_WALL.get())

                .add(ModBLocks.STONE_10_BLOCK.get())
                .add(ModBLocks.STONE_10_STAIRS.get())
                .add(ModBLocks.STONE_10_SLAB.get())
                .add(ModBLocks.STONE_10_WALL.get())

                .add(ModBLocks.STONE_11_BLOCK.get())
                .add(ModBLocks.STONE_11_STAIRS.get())
                .add(ModBLocks.STONE_11_SLAB.get())
                .add(ModBLocks.STONE_11_WALL.get())

                .add(ModBLocks.STONE_12_BLOCK.get())
                .add(ModBLocks.STONE_12_STAIRS.get())
                .add(ModBLocks.STONE_12_SLAB.get())
                .add(ModBLocks.STONE_12_WALL.get())

                .add(ModBLocks.STONE_13_BLOCK.get())
                .add(ModBLocks.STONE_13_STAIRS.get())
                .add(ModBLocks.STONE_13_SLAB.get())
                .add(ModBLocks.STONE_13_WALL.get())

                .add(ModBLocks.STONE_14_BLOCK.get())
                .add(ModBLocks.STONE_14_STAIRS.get())
                .add(ModBLocks.STONE_14_SLAB.get())
                .add(ModBLocks.STONE_14_WALL.get())

                // .add(ModBLocks.STONE_15_BLOCK.get())
                // .add(ModBLocks.STONE_15_STAIRS.get())
                // .add(ModBLocks.STONE_15_SLAB.get())
                // .add(ModBLocks.STONE_15_WALL.get())

                .add(ModBLocks.STONE_20_BLOCK.get())
                .add(ModBLocks.STONE_20_STAIRS.get())
                .add(ModBLocks.STONE_20_SLAB.get())
                .add(ModBLocks.STONE_20_WALL.get())

                .add(ModBLocks.STONE_21_BLOCK.get())
                .add(ModBLocks.STONE_21_STAIRS.get())
                .add(ModBLocks.STONE_21_SLAB.get())
                .add(ModBLocks.STONE_21_WALL.get())

                .add(ModBLocks.STONE_22_BLOCK.get())
                .add(ModBLocks.STONE_22_STAIRS.get())
                .add(ModBLocks.STONE_22_SLAB.get())
                .add(ModBLocks.STONE_22_WALL.get())

                .add(ModBLocks.STONE_24_BLOCK.get())
                .add(ModBLocks.STONE_24_STAIRS.get())
                .add(ModBLocks.STONE_24_SLAB.get())
                .add(ModBLocks.STONE_24_WALL.get())

                .add(ModBLocks.STONE_25_BLOCK.get())
                .add(ModBLocks.STONE_25_STAIRS.get())
                .add(ModBLocks.STONE_25_SLAB.get())
                .add(ModBLocks.STONE_25_WALL.get())

                .add(ModBLocks.STONE_26_BLOCK.get())
                .add(ModBLocks.STONE_26_STAIRS.get())
                .add(ModBLocks.STONE_26_SLAB.get())
                .add(ModBLocks.STONE_26_WALL.get())

                .add(ModBLocks.STONE_27_BLOCK.get())
                .add(ModBLocks.STONE_27_STAIRS.get())
                .add(ModBLocks.STONE_27_SLAB.get())
                .add(ModBLocks.STONE_27_WALL.get())

                .add(ModBLocks.STONE_28_BLOCK.get())
                .add(ModBLocks.STONE_28_STAIRS.get())
                .add(ModBLocks.STONE_28_SLAB.get())
                .add(ModBLocks.STONE_28_WALL.get())

                .add(ModBLocks.STONE_29_BLOCK.get())
                .add(ModBLocks.STONE_29_STAIRS.get())
                .add(ModBLocks.STONE_29_SLAB.get())
                .add(ModBLocks.STONE_29_WALL.get())

                .add(ModBLocks.STONE_30_BLOCK.get())
                .add(ModBLocks.STONE_30_STAIRS.get())
                .add(ModBLocks.STONE_30_SLAB.get())
                .add(ModBLocks.STONE_30_WALL.get())

                .add(ModBLocks.STONE_31_BLOCK.get())
                .add(ModBLocks.STONE_31_STAIRS.get())
                .add(ModBLocks.STONE_31_SLAB.get())
                .add(ModBLocks.STONE_31_WALL.get())

                .add(ModBLocks.STONE_32_BLOCK.get())
                .add(ModBLocks.STONE_32_STAIRS.get())
                .add(ModBLocks.STONE_32_SLAB.get())
                .add(ModBLocks.STONE_32_WALL.get())

                .add(ModBLocks.STONE_33_BLOCK.get())
                .add(ModBLocks.STONE_33_STAIRS.get())
                .add(ModBLocks.STONE_33_SLAB.get())
                .add(ModBLocks.STONE_33_WALL.get())

                .add(ModBLocks.STONE_34_BLOCK.get())
                .add(ModBLocks.STONE_34_STAIRS.get())
                .add(ModBLocks.STONE_34_SLAB.get())
                .add(ModBLocks.STONE_34_WALL.get())

                .add(ModBLocks.STONE_35_BLOCK.get())
                .add(ModBLocks.STONE_35_STAIRS.get())
                .add(ModBLocks.STONE_35_SLAB.get())
                .add(ModBLocks.STONE_35_WALL.get())

                .add(ModBLocks.STONE_36_BLOCK.get())
                .add(ModBLocks.STONE_36_STAIRS.get())
                .add(ModBLocks.STONE_36_SLAB.get())
                .add(ModBLocks.STONE_36_WALL.get())

                .add(ModBLocks.STONE_37_BLOCK.get())
                .add(ModBLocks.STONE_37_STAIRS.get())
                .add(ModBLocks.STONE_37_SLAB.get())
                .add(ModBLocks.STONE_37_WALL.get())

                .add(ModBLocks.STONE_38_BLOCK.get())
                .add(ModBLocks.STONE_38_STAIRS.get())
                .add(ModBLocks.STONE_38_SLAB.get())
                .add(ModBLocks.STONE_38_WALL.get());

        ;
        this.tag(BlockTags.STONE_BRICKS)
                .add(ModBLocks.SSTONE_1_BLOCK.get())
                .add(ModBLocks.SSTONE_2_BLOCK.get())
                .add(ModBLocks.SSTONE_3_BLOCK.get())
                .add(ModBLocks.SSTONE_4_BLOCK.get())
                .add(ModBLocks.SSTONE_5_BLOCK.get())
                .add(ModBLocks.SSTONE_6_BLOCK.get())
                .add(ModBLocks.SSTONE_7_BLOCK.get())
                .add(ModBLocks.SSTONE_8_BLOCK.get())
                .add(ModBLocks.SSTONE_9_BLOCK.get())
                .add(ModBLocks.SSTONE_10_BLOCK.get())
                .add(ModBLocks.SSTONE_11_BLOCK.get())
                .add(ModBLocks.SSTONE_12_BLOCK.get())
                .add(ModBLocks.SSTONE_13_BLOCK.get())
                .add(ModBLocks.SSTONE_14_BLOCK.get())
                // .add(ModBLocks.SSTONE_15_BLOCK.get())
                .add(ModBLocks.SSTONE_16_BLOCK.get())
                .add(ModBLocks.SSTONE_17_BLOCK.get())
                .add(ModBLocks.SSTONE_18_BLOCK.get())
                .add(ModBLocks.SSTONE_19_BLOCK.get())
                .add(ModBLocks.SSTONE_20_BLOCK.get())
                .add(ModBLocks.SSTONE_21_BLOCK.get())
                .add(ModBLocks.SSTONE_22_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_1_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_2_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_3_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_4_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_5_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_6_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_7_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_8_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_9_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_10_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_11_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_12_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_13_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_14_BLOCK.get())
// .add(ModBLocks.RSANDSTONE_15_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_19_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_20_BLOCK.get())
.add(ModBLocks.RSANDSTONE_21_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_22_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_23_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_24_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_25_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_26_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_27_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_28_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_29_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_30_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_31_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_32_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_33_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_34_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_35_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_36_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_37_BLOCK.get())
                .add(ModBLocks.RSANDSTONE_38_BLOCK.get())
                .add(ModBLocks.REDKEEP_STONE_BLOCK.get())
                .add(ModBLocks.REDKEEP_1_BLOCK.get())
                .add(ModBLocks.REDKEEP_2_BLOCK.get())
                .add(ModBLocks.REDKEEP_3_BLOCK.get())
                .add(ModBLocks.REDKEEP_4_BLOCK.get())
                .add(ModBLocks.REDKEEP_5_BLOCK.get())
                .add(ModBLocks.REDKEEP_6_BLOCK.get())
                .add(ModBLocks.REDKEEP_7_BLOCK.get())
                .add(ModBLocks.REDKEEP_8_BLOCK.get())
                .add(ModBLocks.REDKEEP_9_BLOCK.get())
                .add(ModBLocks.REDKEEP_10_BLOCK.get())
                .add(ModBLocks.REDKEEP_11_BLOCK.get())
                .add(ModBLocks.REDKEEP_12_BLOCK.get())
                .add(ModBLocks.REDKEEP_13_BLOCK.get())
                .add(ModBLocks.REDKEEP_14_BLOCK.get())
// .add(ModBLocks.REDKEEP_15_BLOCK.get())
                .add(ModBLocks.REDKEEP_16_BLOCK.get())
                .add(ModBLocks.REDKEEP_17_BLOCK.get())
                .add(ModBLocks.REDKEEP_18_BLOCK.get())
                .add(ModBLocks.REDKEEP_19_BLOCK.get())
                .add(ModBLocks.REDKEEP_20_BLOCK.get())
.add(ModBLocks.REDKEEP_21_BLOCK.get())
                .add(ModBLocks.REDKEEP_22_BLOCK.get())
                .add(ModBLocks.REDKEEP_23_BLOCK.get())
                .add(ModBLocks.REDKEEP_24_BLOCK.get())
                .add(ModBLocks.REDKEEP_25_BLOCK.get())
                .add(ModBLocks.REDKEEP_26_BLOCK.get())
                .add(ModBLocks.REDKEEP_27_BLOCK.get())
                .add(ModBLocks.REDKEEP_28_BLOCK.get())
                .add(ModBLocks.REDKEEP_29_BLOCK.get())
                .add(ModBLocks.REDKEEP_30_BLOCK.get())
                .add(ModBLocks.REDKEEP_31_BLOCK.get())
                .add(ModBLocks.REDKEEP_32_BLOCK.get())
                .add(ModBLocks.REDKEEP_33_BLOCK.get())
                .add(ModBLocks.REDKEEP_34_BLOCK.get())
                .add(ModBLocks.REDKEEP_35_BLOCK.get())
                .add(ModBLocks.REDKEEP_36_BLOCK.get())
                .add(ModBLocks.REDKEEP_37_BLOCK.get())
                .add(ModBLocks.REDKEEP_38_BLOCK.get())
                .add(ModBLocks.STONE_1_BLOCK.get())
                .add(ModBLocks.STONE_2_BLOCK.get())
                .add(ModBLocks.STONE_3_BLOCK.get())
                .add(ModBLocks.STONE_4_BLOCK.get())
                .add(ModBLocks.STONE_6_BLOCK.get())
                .add(ModBLocks.STONE_7_BLOCK.get())
                .add(ModBLocks.STONE_8_BLOCK.get())
                .add(ModBLocks.STONE_9_BLOCK.get())
                .add(ModBLocks.STONE_10_BLOCK.get())
                .add(ModBLocks.STONE_11_BLOCK.get())
                .add(ModBLocks.STONE_12_BLOCK.get())
                .add(ModBLocks.STONE_13_BLOCK.get())
                .add(ModBLocks.STONE_14_BLOCK.get())
                // .add(ModBLocks.STONE_15_BLOCK.get())
                .add(ModBLocks.STONE_16_BLOCK.get())
                .add(ModBLocks.STONE_17_BLOCK.get())
                .add(ModBLocks.STONE_18_BLOCK.get())
                .add(ModBLocks.STONE_20_BLOCK.get())
                .add(ModBLocks.STONE_21_BLOCK.get())
                .add(ModBLocks.STONE_22_BLOCK.get())
                .add(ModBLocks.STONE_24_BLOCK.get())
                .add(ModBLocks.STONE_25_BLOCK.get())
                .add(ModBLocks.STONE_26_BLOCK.get())
                .add(ModBLocks.STONE_27_BLOCK.get())
                .add(ModBLocks.STONE_28_BLOCK.get())
                .add(ModBLocks.STONE_29_BLOCK.get())
                .add(ModBLocks.STONE_30_BLOCK.get())
                .add(ModBLocks.STONE_31_BLOCK.get())
                .add(ModBLocks.STONE_32_BLOCK.get())
                .add(ModBLocks.STONE_33_BLOCK.get())
                .add(ModBLocks.STONE_34_BLOCK.get())
                .add(ModBLocks.STONE_35_BLOCK.get())
                .add(ModBLocks.STONE_36_BLOCK.get())
                .add(ModBLocks.STONE_37_BLOCK.get())
                .add(ModBLocks.STONE_38_BLOCK.get());

        ;
        this.tag(BlockTags.STAIRS)
                .add(ModBLocks.SSTONE_1_STAIRS.get())
                .add(ModBLocks.SSTONE_2_STAIRS.get())
                .add(ModBLocks.SSTONE_3_STAIRS.get())
                .add(ModBLocks.SSTONE_4_STAIRS.get())
                .add(ModBLocks.SSTONE_5_STAIRS.get())
                .add(ModBLocks.SSTONE_6_STAIRS.get())
                .add(ModBLocks.SSTONE_7_STAIRS.get())
                .add(ModBLocks.SSTONE_8_STAIRS.get())
                .add(ModBLocks.SSTONE_9_STAIRS.get())
                .add(ModBLocks.SSTONE_10_STAIRS.get())
                .add(ModBLocks.SSTONE_11_STAIRS.get())
                .add(ModBLocks.SSTONE_12_STAIRS.get())
                .add(ModBLocks.SSTONE_13_STAIRS.get())
                .add(ModBLocks.SSTONE_14_STAIRS.get())
                // .add(ModBLocks.SSTONE_15_STAIRS.get())
                .add(ModBLocks.SSTONE_16_STAIRS.get())
                .add(ModBLocks.SSTONE_17_STAIRS.get())
                .add(ModBLocks.SSTONE_18_STAIRS.get())
                .add(ModBLocks.SSTONE_19_STAIRS.get())
                .add(ModBLocks.SSTONE_20_STAIRS.get())
                .add(ModBLocks.SSTONE_21_STAIRS.get())
                .add(ModBLocks.SSTONE_22_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_1_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_2_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_3_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_4_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_5_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_6_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_7_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_8_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_9_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_10_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_11_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_12_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_13_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_14_STAIRS.get())
// .add(ModBLocks.RSANDSTONE_15_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_19_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_20_STAIRS.get())
.add(ModBLocks.RSANDSTONE_21_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_22_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_23_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_24_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_25_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_26_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_27_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_28_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_29_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_30_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_31_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_32_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_33_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_34_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_35_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_36_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_37_STAIRS.get())
                .add(ModBLocks.RSANDSTONE_38_STAIRS.get())
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get())
                .add(ModBLocks.REDKEEP_1_STAIRS.get())
                .add(ModBLocks.REDKEEP_2_STAIRS.get())
                .add(ModBLocks.REDKEEP_3_STAIRS.get())
                .add(ModBLocks.REDKEEP_4_STAIRS.get())
                .add(ModBLocks.REDKEEP_5_STAIRS.get())
                .add(ModBLocks.REDKEEP_6_STAIRS.get())
                .add(ModBLocks.REDKEEP_7_STAIRS.get())
                .add(ModBLocks.REDKEEP_8_STAIRS.get())
                .add(ModBLocks.REDKEEP_9_STAIRS.get())
                .add(ModBLocks.REDKEEP_10_STAIRS.get())
                .add(ModBLocks.REDKEEP_11_STAIRS.get())
                .add(ModBLocks.REDKEEP_12_STAIRS.get())
                .add(ModBLocks.REDKEEP_13_STAIRS.get())
                .add(ModBLocks.REDKEEP_14_STAIRS.get())
// .add(ModBLocks.REDKEEP_15_STAIRS.get())
                .add(ModBLocks.REDKEEP_16_STAIRS.get())
                .add(ModBLocks.REDKEEP_17_STAIRS.get())
                .add(ModBLocks.REDKEEP_18_STAIRS.get())
                .add(ModBLocks.REDKEEP_19_STAIRS.get())
                .add(ModBLocks.REDKEEP_20_STAIRS.get())
                .add(ModBLocks.REDKEEP_21_STAIRS.get())
                .add(ModBLocks.REDKEEP_22_STAIRS.get())
                .add(ModBLocks.REDKEEP_23_STAIRS.get())
                .add(ModBLocks.REDKEEP_24_STAIRS.get())
                .add(ModBLocks.REDKEEP_25_STAIRS.get())
                .add(ModBLocks.REDKEEP_26_STAIRS.get())
                .add(ModBLocks.REDKEEP_27_STAIRS.get())
                .add(ModBLocks.REDKEEP_28_STAIRS.get())
                .add(ModBLocks.REDKEEP_29_STAIRS.get())
                .add(ModBLocks.REDKEEP_30_STAIRS.get())
                .add(ModBLocks.REDKEEP_31_STAIRS.get())
                .add(ModBLocks.REDKEEP_32_STAIRS.get())
                .add(ModBLocks.REDKEEP_33_STAIRS.get())
                .add(ModBLocks.REDKEEP_34_STAIRS.get())
                .add(ModBLocks.REDKEEP_35_STAIRS.get())
                .add(ModBLocks.REDKEEP_36_STAIRS.get())
                .add(ModBLocks.REDKEEP_37_STAIRS.get())
                .add(ModBLocks.REDKEEP_38_STAIRS.get())
                .add(ModBLocks.STONE_1_STAIRS.get())
                .add(ModBLocks.STONE_2_STAIRS.get())
                .add(ModBLocks.STONE_3_STAIRS.get())
                .add(ModBLocks.STONE_4_STAIRS.get())
                .add(ModBLocks.STONE_6_STAIRS.get())
                .add(ModBLocks.STONE_7_STAIRS.get())
                .add(ModBLocks.STONE_8_STAIRS.get())
                .add(ModBLocks.STONE_9_STAIRS.get())
                .add(ModBLocks.STONE_10_STAIRS.get())
                .add(ModBLocks.STONE_11_STAIRS.get())
                .add(ModBLocks.STONE_12_STAIRS.get())
                .add(ModBLocks.STONE_13_STAIRS.get())
                .add(ModBLocks.STONE_14_STAIRS.get())
                // .add(ModBLocks.STONE_15_STAIRS.get())
                .add(ModBLocks.STONE_16_STAIRS.get())
                .add(ModBLocks.STONE_17_STAIRS.get())
                .add(ModBLocks.STONE_18_STAIRS.get())
                .add(ModBLocks.STONE_20_STAIRS.get())
                .add(ModBLocks.STONE_21_STAIRS.get())
                .add(ModBLocks.STONE_22_STAIRS.get())
                .add(ModBLocks.STONE_24_STAIRS.get())
                .add(ModBLocks.STONE_25_STAIRS.get())
                .add(ModBLocks.STONE_26_STAIRS.get())
                .add(ModBLocks.STONE_27_STAIRS.get())
                .add(ModBLocks.STONE_28_STAIRS.get())
                .add(ModBLocks.STONE_29_STAIRS.get())
                .add(ModBLocks.STONE_30_STAIRS.get())
                .add(ModBLocks.STONE_31_STAIRS.get())
                .add(ModBLocks.STONE_32_STAIRS.get())
                .add(ModBLocks.STONE_33_STAIRS.get())
                .add(ModBLocks.STONE_34_STAIRS.get())
                .add(ModBLocks.STONE_35_STAIRS.get())
                .add(ModBLocks.STONE_36_STAIRS.get())
                .add(ModBLocks.STONE_37_STAIRS.get())
                .add(ModBLocks.STONE_38_STAIRS.get());


        ;
        this.tag(BlockTags.SLABS)
                .add(ModBLocks.SSTONE_1_SLAB.get())
                .add(ModBLocks.SSTONE_2_SLAB.get())
                .add(ModBLocks.SSTONE_3_SLAB.get())
                .add(ModBLocks.SSTONE_4_SLAB.get())
                .add(ModBLocks.SSTONE_5_SLAB.get())
                .add(ModBLocks.SSTONE_6_SLAB.get())
                .add(ModBLocks.SSTONE_7_SLAB.get())
                .add(ModBLocks.SSTONE_8_SLAB.get())
                .add(ModBLocks.SSTONE_9_SLAB.get())
                .add(ModBLocks.SSTONE_10_SLAB.get())
                .add(ModBLocks.SSTONE_11_SLAB.get())
                .add(ModBLocks.SSTONE_12_SLAB.get())
                .add(ModBLocks.SSTONE_13_SLAB.get())
                .add(ModBLocks.SSTONE_14_SLAB.get())
                // .add(ModBLocks.SSTONE_15_SLAB.get())
                .add(ModBLocks.SSTONE_16_SLAB.get())
                .add(ModBLocks.SSTONE_17_SLAB.get())
                .add(ModBLocks.SSTONE_18_SLAB.get())
                .add(ModBLocks.SSTONE_19_SLAB.get())
                .add(ModBLocks.SSTONE_20_SLAB.get())
                .add(ModBLocks.SSTONE_21_SLAB.get())
                .add(ModBLocks.SSTONE_22_SLAB.get())
                .add(ModBLocks.RSANDSTONE_1_SLAB.get())
                .add(ModBLocks.RSANDSTONE_2_SLAB.get())
                .add(ModBLocks.RSANDSTONE_3_SLAB.get())
                .add(ModBLocks.RSANDSTONE_4_SLAB.get())
                .add(ModBLocks.RSANDSTONE_5_SLAB.get())
                .add(ModBLocks.RSANDSTONE_6_SLAB.get())
                .add(ModBLocks.RSANDSTONE_7_SLAB.get())
                .add(ModBLocks.RSANDSTONE_8_SLAB.get())
                .add(ModBLocks.RSANDSTONE_9_SLAB.get())
                .add(ModBLocks.RSANDSTONE_10_SLAB.get())
                .add(ModBLocks.RSANDSTONE_11_SLAB.get())
                .add(ModBLocks.RSANDSTONE_12_SLAB.get())
                .add(ModBLocks.RSANDSTONE_13_SLAB.get())
                .add(ModBLocks.RSANDSTONE_14_SLAB.get())
// .add(ModBLocks.RSANDSTONE_15_SLAB.get())
                .add(ModBLocks.RSANDSTONE_19_SLAB.get())
                .add(ModBLocks.RSANDSTONE_20_SLAB.get())
.add(ModBLocks.RSANDSTONE_21_SLAB.get())
                .add(ModBLocks.RSANDSTONE_22_SLAB.get())
                .add(ModBLocks.RSANDSTONE_23_SLAB.get())
                .add(ModBLocks.RSANDSTONE_24_SLAB.get())
                .add(ModBLocks.RSANDSTONE_25_SLAB.get())
                .add(ModBLocks.RSANDSTONE_26_SLAB.get())
                .add(ModBLocks.RSANDSTONE_27_SLAB.get())
                .add(ModBLocks.RSANDSTONE_28_SLAB.get())
                .add(ModBLocks.RSANDSTONE_29_SLAB.get())
                .add(ModBLocks.RSANDSTONE_30_SLAB.get())
                .add(ModBLocks.RSANDSTONE_31_SLAB.get())
                .add(ModBLocks.RSANDSTONE_32_SLAB.get())
                .add(ModBLocks.RSANDSTONE_33_SLAB.get())
                .add(ModBLocks.RSANDSTONE_34_SLAB.get())
                .add(ModBLocks.RSANDSTONE_35_SLAB.get())
                .add(ModBLocks.RSANDSTONE_36_SLAB.get())
                .add(ModBLocks.RSANDSTONE_37_SLAB.get())
                .add(ModBLocks.RSANDSTONE_38_SLAB.get())
                .add(ModBLocks.REDKEEP_STONE_SLAB.get())
                .add(ModBLocks.REDKEEP_1_SLAB.get())
                .add(ModBLocks.REDKEEP_2_SLAB.get())
                .add(ModBLocks.REDKEEP_3_SLAB.get())
                .add(ModBLocks.REDKEEP_4_SLAB.get())
                .add(ModBLocks.REDKEEP_5_SLAB.get())
                .add(ModBLocks.REDKEEP_6_SLAB.get())
                .add(ModBLocks.REDKEEP_7_SLAB.get())
                .add(ModBLocks.REDKEEP_8_SLAB.get())
                .add(ModBLocks.REDKEEP_9_SLAB.get())
                .add(ModBLocks.REDKEEP_10_SLAB.get())
                .add(ModBLocks.REDKEEP_11_SLAB.get())
                .add(ModBLocks.REDKEEP_12_SLAB.get())
                .add(ModBLocks.REDKEEP_13_SLAB.get())
                .add(ModBLocks.REDKEEP_14_SLAB.get())
// .add(ModBLocks.REDKEEP_15_SLAB.get())
                .add(ModBLocks.REDKEEP_16_SLAB.get())
                .add(ModBLocks.REDKEEP_17_SLAB.get())
                .add(ModBLocks.REDKEEP_18_SLAB.get())
                .add(ModBLocks.REDKEEP_19_SLAB.get())
                .add(ModBLocks.REDKEEP_20_SLAB.get())
   .add(ModBLocks.REDKEEP_21_SLAB.get())
                .add(ModBLocks.REDKEEP_22_SLAB.get())
                .add(ModBLocks.REDKEEP_23_SLAB.get())
                .add(ModBLocks.REDKEEP_24_SLAB.get())
                .add(ModBLocks.REDKEEP_25_SLAB.get())
                .add(ModBLocks.REDKEEP_26_SLAB.get())
                .add(ModBLocks.REDKEEP_27_SLAB.get())
                .add(ModBLocks.REDKEEP_28_SLAB.get())
                .add(ModBLocks.REDKEEP_29_SLAB.get())
                .add(ModBLocks.REDKEEP_30_SLAB.get())
                .add(ModBLocks.REDKEEP_31_SLAB.get())
                .add(ModBLocks.REDKEEP_32_SLAB.get())
                .add(ModBLocks.REDKEEP_33_SLAB.get())
                .add(ModBLocks.REDKEEP_34_SLAB.get())
                .add(ModBLocks.REDKEEP_35_SLAB.get())
                .add(ModBLocks.REDKEEP_36_SLAB.get())
                .add(ModBLocks.REDKEEP_37_SLAB.get())
                .add(ModBLocks.REDKEEP_38_SLAB.get())
                .add(ModBLocks.STONE_1_SLAB.get())
                .add(ModBLocks.STONE_2_SLAB.get())
                .add(ModBLocks.STONE_3_SLAB.get())
                .add(ModBLocks.STONE_4_SLAB.get())
                .add(ModBLocks.STONE_6_SLAB.get())
                .add(ModBLocks.STONE_7_SLAB.get())
                .add(ModBLocks.STONE_8_SLAB.get())
                .add(ModBLocks.STONE_9_SLAB.get())
                .add(ModBLocks.STONE_10_SLAB.get())
                .add(ModBLocks.STONE_11_SLAB.get())
                .add(ModBLocks.STONE_12_SLAB.get())
                .add(ModBLocks.STONE_13_SLAB.get())
                .add(ModBLocks.STONE_14_SLAB.get())
                // .add(ModBLocks.STONE_15_SLAB.get())
                .add(ModBLocks.STONE_16_SLAB.get())
                .add(ModBLocks.STONE_17_SLAB.get())
                .add(ModBLocks.STONE_18_SLAB.get())
                .add(ModBLocks.STONE_20_SLAB.get())
                .add(ModBLocks.STONE_21_SLAB.get())
                .add(ModBLocks.STONE_22_SLAB.get())
                .add(ModBLocks.STONE_24_SLAB.get())
                .add(ModBLocks.STONE_25_SLAB.get())
                .add(ModBLocks.STONE_26_SLAB.get())
                .add(ModBLocks.STONE_27_SLAB.get())
                .add(ModBLocks.STONE_28_SLAB.get())
                .add(ModBLocks.STONE_29_SLAB.get())
                .add(ModBLocks.STONE_30_SLAB.get())
                .add(ModBLocks.STONE_31_SLAB.get())
                .add(ModBLocks.STONE_32_SLAB.get())
                .add(ModBLocks.STONE_33_SLAB.get())
                .add(ModBLocks.STONE_34_SLAB.get())
                .add(ModBLocks.STONE_35_SLAB.get())
                .add(ModBLocks.STONE_36_SLAB.get())
                .add(ModBLocks.STONE_37_SLAB.get())
                .add(ModBLocks.STONE_38_SLAB.get())

        ;
        this.tag(BlockTags.WALLS)
                .add(ModBLocks.SSTONE_1_WALL.get())
                .add(ModBLocks.SSTONE_2_WALL.get())
                .add(ModBLocks.SSTONE_3_WALL.get())
                .add(ModBLocks.SSTONE_4_WALL.get())
                .add(ModBLocks.SSTONE_5_WALL.get())
                .add(ModBLocks.SSTONE_6_WALL.get())
                .add(ModBLocks.SSTONE_7_WALL.get())
                .add(ModBLocks.SSTONE_8_WALL.get())
                .add(ModBLocks.SSTONE_9_WALL.get())
                .add(ModBLocks.SSTONE_10_WALL.get())
                .add(ModBLocks.SSTONE_11_WALL.get())
                .add(ModBLocks.SSTONE_12_WALL.get())
                .add(ModBLocks.SSTONE_13_WALL.get())
                .add(ModBLocks.SSTONE_14_WALL.get())
                // .add(ModBLocks.SSTONE_15_WALL.get())
                .add(ModBLocks.SSTONE_16_WALL.get())
                .add(ModBLocks.SSTONE_17_WALL.get())
                .add(ModBLocks.SSTONE_18_WALL.get())
                .add(ModBLocks.SSTONE_19_WALL.get())
                .add(ModBLocks.SSTONE_20_WALL.get())
                .add(ModBLocks.SSTONE_21_WALL.get())
                .add(ModBLocks.SSTONE_22_WALL.get())
                .add(ModBLocks.RSANDSTONE_1_WALL.get())
                .add(ModBLocks.RSANDSTONE_2_WALL.get())
                .add(ModBLocks.RSANDSTONE_3_WALL.get())
                .add(ModBLocks.RSANDSTONE_4_WALL.get())
                .add(ModBLocks.RSANDSTONE_5_WALL.get())
                .add(ModBLocks.RSANDSTONE_6_WALL.get())
                .add(ModBLocks.RSANDSTONE_7_WALL.get())
                .add(ModBLocks.RSANDSTONE_8_WALL.get())
                .add(ModBLocks.RSANDSTONE_9_WALL.get())
                .add(ModBLocks.RSANDSTONE_10_WALL.get())
                .add(ModBLocks.RSANDSTONE_11_WALL.get())
                .add(ModBLocks.RSANDSTONE_12_WALL.get())
                .add(ModBLocks.RSANDSTONE_13_WALL.get())
                .add(ModBLocks.RSANDSTONE_14_WALL.get())
// .add(ModBLocks.RSANDSTONE_15_WALL.get())
                .add(ModBLocks.RSANDSTONE_19_WALL.get())
                .add(ModBLocks.RSANDSTONE_20_WALL.get())
.add(ModBLocks.RSANDSTONE_21_WALL.get())
                .add(ModBLocks.RSANDSTONE_22_WALL.get())
                .add(ModBLocks.RSANDSTONE_23_WALL.get())
                .add(ModBLocks.RSANDSTONE_24_WALL.get())
                .add(ModBLocks.RSANDSTONE_25_WALL.get())
                .add(ModBLocks.RSANDSTONE_26_WALL.get())
                .add(ModBLocks.RSANDSTONE_27_WALL.get())
                .add(ModBLocks.RSANDSTONE_28_WALL.get())
                .add(ModBLocks.RSANDSTONE_29_WALL.get())
                .add(ModBLocks.RSANDSTONE_30_WALL.get())
                .add(ModBLocks.RSANDSTONE_31_WALL.get())
                .add(ModBLocks.RSANDSTONE_32_WALL.get())
                .add(ModBLocks.RSANDSTONE_33_WALL.get())
                .add(ModBLocks.RSANDSTONE_34_WALL.get())
                .add(ModBLocks.RSANDSTONE_35_WALL.get())
                .add(ModBLocks.RSANDSTONE_36_WALL.get())
                .add(ModBLocks.RSANDSTONE_37_WALL.get())
                .add(ModBLocks.RSANDSTONE_38_WALL.get())
                .add(ModBLocks.REDKEEP_STONE_WALL.get())
                .add(ModBLocks.REDKEEP_1_WALL.get())
                .add(ModBLocks.REDKEEP_2_WALL.get())
                .add(ModBLocks.REDKEEP_3_WALL.get())
                .add(ModBLocks.REDKEEP_4_WALL.get())
                .add(ModBLocks.REDKEEP_5_WALL.get())
                .add(ModBLocks.REDKEEP_6_WALL.get())
                .add(ModBLocks.REDKEEP_7_WALL.get())
                .add(ModBLocks.REDKEEP_8_WALL.get())
                .add(ModBLocks.REDKEEP_9_WALL.get())
                .add(ModBLocks.REDKEEP_10_WALL.get())
                .add(ModBLocks.REDKEEP_11_WALL.get())
                .add(ModBLocks.REDKEEP_12_WALL.get())
                .add(ModBLocks.REDKEEP_13_WALL.get())
                .add(ModBLocks.REDKEEP_14_WALL.get())
// .add(ModBLocks.REDKEEP_15_WALL.get())
                .add(ModBLocks.REDKEEP_16_WALL.get())
                .add(ModBLocks.REDKEEP_17_WALL.get())
                .add(ModBLocks.REDKEEP_18_WALL.get())
                .add(ModBLocks.REDKEEP_19_WALL.get())
                .add(ModBLocks.REDKEEP_20_WALL.get())
  .add(ModBLocks.REDKEEP_21_WALL.get())
                .add(ModBLocks.REDKEEP_22_WALL.get())
                .add(ModBLocks.REDKEEP_23_WALL.get())
                .add(ModBLocks.REDKEEP_24_WALL.get())
                .add(ModBLocks.REDKEEP_25_WALL.get())
                .add(ModBLocks.REDKEEP_26_WALL.get())
                .add(ModBLocks.REDKEEP_27_WALL.get())
                .add(ModBLocks.REDKEEP_28_WALL.get())
                .add(ModBLocks.REDKEEP_29_WALL.get())
                .add(ModBLocks.REDKEEP_30_WALL.get())
                .add(ModBLocks.REDKEEP_31_WALL.get())
                .add(ModBLocks.REDKEEP_32_WALL.get())
                .add(ModBLocks.REDKEEP_33_WALL.get())
                .add(ModBLocks.REDKEEP_34_WALL.get())
                .add(ModBLocks.REDKEEP_35_WALL.get())
                .add(ModBLocks.REDKEEP_36_WALL.get())
                .add(ModBLocks.REDKEEP_37_WALL.get())
                .add(ModBLocks.REDKEEP_38_WALL.get())
                .add(ModBLocks.STONE_1_WALL.get())
                .add(ModBLocks.STONE_2_WALL.get())
                .add(ModBLocks.STONE_3_WALL.get())
                .add(ModBLocks.STONE_4_WALL.get())
                .add(ModBLocks.STONE_6_WALL.get())
                .add(ModBLocks.STONE_7_WALL.get())
                .add(ModBLocks.STONE_8_WALL.get())
                .add(ModBLocks.STONE_9_WALL.get())
                .add(ModBLocks.STONE_10_WALL.get())
                .add(ModBLocks.STONE_11_WALL.get())
                .add(ModBLocks.STONE_12_WALL.get())
                .add(ModBLocks.STONE_13_WALL.get())
                .add(ModBLocks.STONE_14_WALL.get())
                // .add(ModBLocks.STONE_15_WALL.get())
                .add(ModBLocks.STONE_16_WALL.get())
                .add(ModBLocks.STONE_17_WALL.get())
                .add(ModBLocks.STONE_18_WALL.get())
                .add(ModBLocks.STONE_20_WALL.get())
                .add(ModBLocks.STONE_21_WALL.get())
                .add(ModBLocks.STONE_22_WALL.get())
                .add(ModBLocks.STONE_24_WALL.get())
                .add(ModBLocks.STONE_25_WALL.get())
                .add(ModBLocks.STONE_26_WALL.get())
                .add(ModBLocks.STONE_27_WALL.get())
                .add(ModBLocks.STONE_28_WALL.get())
                .add(ModBLocks.STONE_29_WALL.get())
                .add(ModBLocks.STONE_30_WALL.get())
                .add(ModBLocks.STONE_31_WALL.get())
                .add(ModBLocks.STONE_32_WALL.get())
                .add(ModBLocks.STONE_33_WALL.get())
                .add(ModBLocks.STONE_34_WALL.get())
                .add(ModBLocks.STONE_35_WALL.get())
                .add(ModBLocks.STONE_36_WALL.get())
                .add(ModBLocks.STONE_37_WALL.get())
                .add(ModBLocks.STONE_38_WALL.get())


        ;




        // Add blocks that need a stone tool to the NEEDS_STONE_TOOL tag
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.TIN_ORE.get())
                .add(ModBLocks.DARK_STONE_BRICK.get())
                .add(ModBLocks.STONE_BRICK_BUT_COOLER.get())
                .add(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        // ---------------------------(BRICKS)--------------------------- //
        this.tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        // Add blocks that need an iron tool to the NEEDS_IRON_TOOL tag
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.TIN_BLOCK.get())
                .add(ModBLocks.RAW_TIN_BLOCK.get())
                .add(ModBLocks.DEEPSLATE_TIN_ORE.get());
        this.tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRONZE)--------------------------- //
        // Add blocks that need an iron tool to the NEEDS_IRON_TOOL tag
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.BRONZE_BLOCK.get());
        // ---------------------------(BRONZE)--------------------------- //
        // Add blocks that need a bronze tool to the custom NEEDS_BRONZE_TOOL tag
        this.tag(ModTags.Blocks.INCORRECT_FOR_BRONZE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(ModTags.Blocks.NEEDS_BRONZE_TOOL)
                .add(ModBLocks.MINT_BLOCK.get());

        // ---------------------------(STEEL)--------------------------- //
        this.tag(ModTags.Blocks.NEEDS_STEEL_TOOL);

        // ---------------------------(DRAGON GLASS)--------------------------- //
        this.tag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL);

        /* // ---------------------------(NETHERITE TOOL)--------------------------- // */

        this.tag(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL).addTag(BlockTags.NEEDS_DIAMOND_TOOL);

    }
}
