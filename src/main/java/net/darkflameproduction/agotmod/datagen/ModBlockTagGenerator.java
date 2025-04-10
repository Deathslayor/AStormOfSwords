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
                        ModBLocks.KINGS_LANDING_BRICK_LARGE.get());



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
