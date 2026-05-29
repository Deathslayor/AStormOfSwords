package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.block.custom.GhostGrassBlock;
import net.darkflameproduction.agotmod.block.custom.HorseradishCropBlock;
import net.darkflameproduction.agotmod.block.custom.WeirwoodFaceLogBlock;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class ModBlockStateProvider extends BlockStateProvider {
    // Constructor for ModBlockStateProvider
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (BlockStateProvider)
        super(output, AGoTMod.MOD_ID, existingFileHelper);
    }

    // Method to register block states and models
    @Override
    protected void registerStatesAndModels() {


        // Variant models 2â€“24
        Map<Integer, BlockModelBuilder> variantModels = new HashMap<>();
        for (int i = 2; i <= 24; i++) {
            variantModels.put(i, wattleModel("wattle_and_daub" + i + "_base", i));
        }

        for (String color : ModBLocks.WATTLE_DAUB_COLORS.keySet()) {
            wattleAndDaubBlock(ModBLocks.WATTLE_AND_DAUB_PLAIN.get(color), 0);
            for (int i = 2; i <= 24; i++) {
                wattleAndDaubBlock(ModBLocks.WATTLE_AND_DAUB_VARIANTS.get(color).get(i), i);
            }
        }

        // ---------------------------(TIN)--------------------------- //
        // Register block states and models for tin-related blocks
        GhostGrassBlock(ModBLocks.GHOST_GRASS_BLOCK.get());
        blockItem(ModBLocks.GHOST_GRASS_BLOCK);
        blockWithItem(ModBLocks.SILVER_BLOCK);
        blockWithItem(ModBLocks.RAW_SILVER_BLOCK);
        blockWithItem(ModBLocks.DEEPSLATE_SILVER_ORE);
        blockWithItem(ModBLocks.SILVER_ORE);
        blockWithItem(ModBLocks.TIN_BLOCK);
        blockWithItem(ModBLocks.RAW_TIN_BLOCK);
        blockWithItem(ModBLocks.DEEPSLATE_TIN_ORE);
        blockWithItem(ModBLocks.TIN_ORE);
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(GEMS)--------------------------- //
        blockWithItem(ModBLocks.YELLOW_DIAMOND_BLOCK);
        blockWithItem(ModBLocks.TRANSPARENT_DIAMOND_BLOCK);
        blockWithItem(ModBLocks.BLACK_DIAMOND_BLOCK);

        blockWithItem(ModBLocks.AMETHYST_BLOCK);
        blockWithItem(ModBLocks.AMETHYST_ORE);
        blockWithItem(ModBLocks.AMETHYST_DEEPSLATE_ORE);


        blockWithItem(ModBLocks.AMBER_BLOCK);
        blockWithItem(ModBLocks.AMBER_ORE);
        blockWithItem(ModBLocks.AMBER_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.BLOODSTONE_BLOCK);
        blockWithItem(ModBLocks.BLOODSTONE_ORE);
        blockWithItem(ModBLocks.BLOODSTONE_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.CARNELIAN_BLOCK);
        blockWithItem(ModBLocks.CARNELIAN_ORE);
        blockWithItem(ModBLocks.CARNELIAN_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.CHALCEDONY_BLOCK);
        blockWithItem(ModBLocks.CHALCEDONY_ORE);
        blockWithItem(ModBLocks.CHALCEDONY_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.DIAMONDS_ORE);
        blockWithItem(ModBLocks.DIAMONDS_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.GARNET_BLOCK);
        blockWithItem(ModBLocks.GARNET_ORE);
        blockWithItem(ModBLocks.GARNET_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.JADE_BLOCK);
        blockWithItem(ModBLocks.JADE_ORE);
        blockWithItem(ModBLocks.JADE_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.JASPER_BLOCK);
        blockWithItem(ModBLocks.JASPER_ORE);
        blockWithItem(ModBLocks.JASPER_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.MALACHITE_BLOCK);
        blockWithItem(ModBLocks.MALACHITE_ORE);
        blockWithItem(ModBLocks.MALACHITE_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.MOONSTONE_BLOCK);
        blockWithItem(ModBLocks.MOONSTONE_ORE);
        blockWithItem(ModBLocks.MOONSTONE_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.ONYX_BLOCK);
        blockWithItem(ModBLocks.ONYX_ORE);
        blockWithItem(ModBLocks.ONYX_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.OPAL_BLOCK);
        blockWithItem(ModBLocks.OPAL_ORE);
        blockWithItem(ModBLocks.OPAL_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.RUBY_BLOCK);
        blockWithItem(ModBLocks.RUBY_ORE);
        blockWithItem(ModBLocks.RUBY_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBLocks.SAPPHIRE_ORE);
        blockWithItem(ModBLocks.SAPPHIRE_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.TOURMALINE_ORE);
        blockWithItem(ModBLocks.TOURMALINE_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.TOPAZ_BLOCK);
        blockWithItem(ModBLocks.TOPAZ_ORE);
        blockWithItem(ModBLocks.TOPAZ_DEEPSLATE_ORE);

        blockWithItem(ModBLocks.TIGERS_EYE_BLOCK);
        blockWithItem(ModBLocks.TIGERS_EYE_ORE);
        blockWithItem(ModBLocks.TIGERS_EYE_DEEPSLATE_ORE);
        // ---------------------------(GEMS)--------------------------- //


        // ---------------------------(BRONZE)--------------------------- //
        // Register block states and models for bronze-related blocks
        blockWithItem(ModBLocks.BRONZE_BLOCK);
        // ---------------------------(BRONZE)--------------------------- //

        // ---------------------------(BRICKS)--------------------------- //
        // Register block states and models for brick-related blocks
        blockWithItem(ModBLocks.QUAGMIRE);
        blockWithItem(ModBLocks.KINGS_LANDING_BRICK_LARGE);
        blockWithItem(ModBLocks.DARK_STONE_BRICK);
        blockWithItem(ModBLocks.STONE_BRICK_BUT_COOLER);
        // ---------------------------(BLOCKS)--------------------------- //
        stairsBlock(((StairBlock) ModBLocks.DIRT_STAIRS.get()), blockTexture(Blocks.DIRT));

        slabBlock(((SlabBlock) ModBLocks.DIRT_SLAB.get()),
                blockTexture(Blocks.DIRT),
                blockTexture(Blocks.DIRT));
        stairsBlock(((StairBlock) ModBLocks.MUD_STAIRS.get()), blockTexture(Blocks.MUD));

        slabBlock(((SlabBlock) ModBLocks.MUD_SLAB.get()),
                blockTexture(Blocks.MUD),
                blockTexture(Blocks.MUD));

        stairsBlock(((StairBlock) ModBLocks.COARSE_DIRT_STAIRS.get()), blockTexture(Blocks.COARSE_DIRT));

        slabBlock(((SlabBlock) ModBLocks.COARSE_DIRT_SLAB.get()),
                blockTexture(Blocks.COARSE_DIRT),
                blockTexture(Blocks.COARSE_DIRT));

        ModelFile podzolSlab = models().slab("podzol_slab", mcLoc("block/podzol_side"), mcLoc("block/dirt"), mcLoc("block/podzol_top"));
        ModelFile podzolSlabTop = models().slabTop("podzol_slab_top", mcLoc("block/podzol_side"), mcLoc("block/dirt"), mcLoc("block/podzol_top"));
        ModelFile podzolFull = models().cubeBottomTop("podzol_slab_double", mcLoc("block/podzol_side"), mcLoc("block/dirt"), mcLoc("block/podzol_top"));

        slabBlock(((SlabBlock) ModBLocks.PODZOL_SLAB.get()), podzolSlab, podzolSlabTop, podzolFull);

        ModelFile podzolStairs = models().stairs("podzol_stairs", mcLoc("block/podzol_side"), mcLoc("block/dirt"), mcLoc("block/podzol_top"));
        ModelFile podzolStairsInner = models().stairsInner("podzol_stairs_inner", mcLoc("block/podzol_side"), mcLoc("block/dirt"), mcLoc("block/podzol_top"));
        ModelFile podzolStairsOuter = models().stairsOuter("podzol_stairs_outer", mcLoc("block/podzol_side"), mcLoc("block/dirt"), mcLoc("block/podzol_top"));

        stairsBlock(((StairBlock) ModBLocks.PODZOL_STAIRS.get()), podzolStairs, podzolStairsInner, podzolStairsOuter);

        ModelFile dirtPathSlab = models().slab("dirt_path_slab", modLoc("block/dirt_path_side"), mcLoc("block/dirt"), mcLoc("block/dirt_path_top"));
        ModelFile dirtPathSlabTop = models().slabTop("dirt_path_slab_top", modLoc("block/dirt_path_side"), mcLoc("block/dirt"), mcLoc("block/dirt_path_top"));
        ModelFile dirtPathFull = models().cubeBottomTop("dirt_path_slab_double", modLoc("block/dirt_path_side"), mcLoc("block/dirt"), mcLoc("block/dirt_path_top"));

        slabBlock(((SlabBlock) ModBLocks.DIRT_PATH_SLAB.get()), dirtPathSlab, dirtPathSlabTop, dirtPathFull);

        ModelFile dirtPathStairs = models().stairs("dirt_path_stairs", modLoc("block/dirt_path_side"), mcLoc("block/dirt"), mcLoc("block/dirt_path_top"));
        ModelFile dirtPathStairsInner = models().stairsInner("dirt_path_stairs_inner", modLoc("block/dirt_path_side"), mcLoc("block/dirt"), mcLoc("block/dirt_path_top"));
        ModelFile dirtPathStairsOuter = models().stairsOuter("dirt_path_stairs_outer", modLoc("block/dirt_path_side"), mcLoc("block/dirt"), mcLoc("block/dirt_path_top"));

        stairsBlock(((StairBlock) ModBLocks.DIRT_PATH_STAIRS.get()), dirtPathStairs, dirtPathStairsInner, dirtPathStairsOuter);

        ModelFile grassSlab = models().slab("grass_block_slab", mcLoc("block/grass_block_side"), mcLoc("block/dirt"), mcLoc("block/grass_block_top"));
        ModelFile grassSlabTop = models().slabTop("grass_block_slab_top", mcLoc("block/grass_block_side"), mcLoc("block/dirt"), mcLoc("block/grass_block_top"));
        ModelFile grassSlabDouble = models().cubeBottomTop("grass_block_slab_double", mcLoc("block/grass_block_side"), mcLoc("block/dirt"), mcLoc("block/grass_block_top"));
        slabBlock(((SlabBlock) ModBLocks.GRASS_BLOCK_SLAB.get()), grassSlab, grassSlabTop, grassSlabDouble);

        ModelFile grassStairs = models().stairs("grass_block_stairs", mcLoc("block/grass_block_side"), mcLoc("block/dirt"), mcLoc("block/grass_block_top"));
        ModelFile grassStairsInner = models().stairsInner("grass_block_stairs_inner", mcLoc("block/grass_block_side"), mcLoc("block/dirt"), mcLoc("block/grass_block_top"));
        ModelFile grassStairsOuter = models().stairsOuter("grass_block_stairs_outer", mcLoc("block/grass_block_side"), mcLoc("block/dirt"), mcLoc("block/grass_block_top"));
        stairsBlock(((StairBlock) ModBLocks.GRASS_BLOCK_STAIRS.get()), grassStairs, grassStairsInner, grassStairsOuter);

        blockWithItem(ModBLocks.PEAT);


        // THATCH BLOCK (RotatedPillarBlock)
        axisBlock(
                ((RotatedPillarBlock) ModBLocks.THATCH_BLOCK.get()),
                modLoc("block/thatch_block_side"),
                modLoc("block/thatch_block_top")
        );
        simpleBlockItem(ModBLocks.THATCH_BLOCK.get(), models().getExistingFile(AGoTMod.id("block/thatch_block")));

// THATCH SLAB
        ModelFile thatchSlab = models().slab(
                "thatch_slab",
                modLoc("block/thatch_block_top"),
                modLoc("block/thatch_block_side"),
                modLoc("block/thatch_block_side")
        );

        ModelFile thatchSlabTop = models().slabTop(
                "thatch_slab_top",
                modLoc("block/thatch_block_top"),
                modLoc("block/thatch_block_side"),
                modLoc("block/thatch_block_side")
        );

        ModelFile thatchSlabDouble = models().cubeBottomTop(
                "thatch_slab_double",
                modLoc("block/thatch_block_top"),
                modLoc("block/thatch_block_side"),
                modLoc("block/thatch_block_side")
        );

        slabBlock(
                ((SlabBlock) ModBLocks.THATCH_SLAB.get()),
                thatchSlab,
                thatchSlabTop,
                thatchSlabDouble
        );

// THATCH STAIRS
        ModelFile thatchStairs = models().stairs(
                "thatch_stairs",
                modLoc("block/thatch_block_top"),
                modLoc("block/thatch_block_side"),
                modLoc("block/thatch_block_side")
        );

        ModelFile thatchStairsInner = models().stairsInner(
                "thatch_stairs_inner",
                modLoc("block/thatch_block_top"),
                modLoc("block/thatch_block_side"),
                modLoc("block/thatch_block_side")
        );

        ModelFile thatchStairsOuter = models().stairsOuter(
                "thatch_stairs_outer",
                modLoc("block/thatch_block_top"),
                modLoc("block/thatch_block_side"),
                modLoc("block/thatch_block_side")
        );

        stairsBlock(
                ((StairBlock) ModBLocks.THATCH_STAIRS.get()),
                thatchStairs,
                thatchStairsInner,
                thatchStairsOuter
        );

        blockWithItem(ModBLocks.HEARTH_BLOCK);



        // ---------------------------(TREES)--------------------------- //
        //Weirwood
        logBlock((RotatedPillarBlock) ModBLocks.WEIRWOOD_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.WEIRWOOD_WOOD.get()),
                blockTexture(ModBLocks.WEIRWOOD_LOG.get()), // side of the block
                blockTexture(ModBLocks.WEIRWOOD_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_WEIRWOOD_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), // side of the block
                AGoTMod.id("block/stripped_weirwood_log_top")); // top of the block

        weirwoodFaceLogBlock(ModBLocks.WEIRWOOD_FACE_LOG.get());

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_WEIRWOOD_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_WEIRWOOD_LOG.get())); // top of the block

        blockItem(ModBLocks.WEIRWOOD_LOG);
        blockItem(ModBLocks.WEIRWOOD_FACE_LOG);
        blockItem(ModBLocks.STRIPPED_WEIRWOOD_LOG);
        blockItem(ModBLocks.WEIRWOOD_WOOD);
        blockItem(ModBLocks.STRIPPED_WEIRWOOD_WOOD);

        blockWithItem(ModBLocks.WEIRWOOD_PLANKS);

        leavesBlock(ModBLocks.WEIRWOOD_LEAVES);
        saplingBlock(ModBLocks.WEIRWOOD_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.WEIRWOOD_STAIRS.get()), blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.WEIRWOOD_SLAB.get()),
                blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()),
                blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.WEIRWOOD_BUTTON.get()), blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.WEIRWOOD_PRESSURE_PLATE.get()), blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.WEIRWOOD_FENCE.get()), blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.WEIRWOOD_FENCE_GATE.get()), blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.WEIRWOOD_WALL.get()), blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.WEIRWOOD_DOOR.get()),
                modLoc("block/weirwood_door_bottom"),
                modLoc("block/weirwood_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.WEIRWOOD_TRAPDOOR.get()),
                modLoc("block/weirwood_trapdoor"),
                true,
                "cutout");


        signBlock(((StandingSignBlock) ModBLocks.WEIRWOOD_SIGN.get()), ((WallSignBlock) ModBLocks.WEIRWOOD_WALL_SIGN.get()),
                blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));

        hangingSignBlock(ModBLocks.WEIRWOOD_HANGING_SIGN.get(), ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get(), blockTexture(ModBLocks.WEIRWOOD_PLANKS.get()));

        //Rotten
        signBlock(((StandingSignBlock) ModBLocks.ROTTEN_SIGN.get()),
                ((WallSignBlock) ModBLocks.ROTTEN_WALL_SIGN.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        hangingSignBlock(ModBLocks.ROTTEN_HANGING_SIGN.get(),
                ModBLocks.ROTTEN_WALL_HANGING_SIGN.get(),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

//Charred
        signBlock(((StandingSignBlock) ModBLocks.CHARRED_SIGN.get()),
                ((WallSignBlock) ModBLocks.CHARRED_WALL_SIGN.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        hangingSignBlock(ModBLocks.CHARRED_HANGING_SIGN.get(),
                ModBLocks.CHARRED_WALL_HANGING_SIGN.get(),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        //Rotten
        logBlock((RotatedPillarBlock) ModBLocks.ROTTEN_LOG.get());

        axisBlock(((RotatedPillarBlock) ModBLocks.ROTTEN_WOOD.get()),
                blockTexture(ModBLocks.ROTTEN_LOG.get()),
                blockTexture(ModBLocks.ROTTEN_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ROTTEN_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_ROTTEN_LOG.get()),
                AGoTMod.id("block/stripped_rotten_log_top"));

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ROTTEN_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_ROTTEN_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_ROTTEN_LOG.get()));

        blockItem(ModBLocks.ROTTEN_LOG);
        blockItem(ModBLocks.STRIPPED_ROTTEN_LOG);
        blockItem(ModBLocks.ROTTEN_WOOD);
        blockItem(ModBLocks.STRIPPED_ROTTEN_WOOD);

        blockWithItem(ModBLocks.ROTTEN_PLANKS);

        stairsBlock(((StairBlock) ModBLocks.ROTTEN_STAIRS.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.ROTTEN_SLAB.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.ROTTEN_BUTTON.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.ROTTEN_PRESSURE_PLATE.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.ROTTEN_FENCE.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBLocks.ROTTEN_FENCE_GATE.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        wallBlock(((WallBlock) ModBLocks.ROTTEN_WALL.get()),
                blockTexture(ModBLocks.ROTTEN_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.ROTTEN_DOOR.get()),
                modLoc("block/rotten_door_bottom"),
                modLoc("block/rotten_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.ROTTEN_TRAPDOOR.get()),
                modLoc("block/rotten_trapdoor"),
                true,
                "cutout");


//Charred
        logBlock((RotatedPillarBlock) ModBLocks.CHARRED_LOG.get());

        axisBlock(((RotatedPillarBlock) ModBLocks.CHARRED_WOOD.get()),
                blockTexture(ModBLocks.CHARRED_LOG.get()),
                blockTexture(ModBLocks.CHARRED_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_CHARRED_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_CHARRED_LOG.get()),
                AGoTMod.id("block/stripped_charred_log_top"));

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_CHARRED_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_CHARRED_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_CHARRED_LOG.get()));

        blockItem(ModBLocks.CHARRED_LOG);
        blockItem(ModBLocks.STRIPPED_CHARRED_LOG);
        blockItem(ModBLocks.CHARRED_WOOD);
        blockItem(ModBLocks.STRIPPED_CHARRED_WOOD);

        blockWithItem(ModBLocks.CHARRED_PLANKS);

        stairsBlock(((StairBlock) ModBLocks.CHARRED_STAIRS.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.CHARRED_SLAB.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.CHARRED_BUTTON.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.CHARRED_PRESSURE_PLATE.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.CHARRED_FENCE.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBLocks.CHARRED_FENCE_GATE.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        wallBlock(((WallBlock) ModBLocks.CHARRED_WALL.get()),
                blockTexture(ModBLocks.CHARRED_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.CHARRED_DOOR.get()),
                modLoc("block/charred_door_bottom"),
                modLoc("block/charred_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.CHARRED_TRAPDOOR.get()),
                modLoc("block/charred_trapdoor"),
                true,
                "cutout");

        wallBlock(((WallBlock) ModBLocks.OAK_WALL.get()), blockTexture(Blocks.OAK_PLANKS));
        wallBlock(((WallBlock) ModBLocks.SPRUCE_WALL.get()), blockTexture(Blocks.SPRUCE_PLANKS));
        wallBlock(((WallBlock) ModBLocks.BIRCH_WALL.get()), blockTexture(Blocks.BIRCH_PLANKS));
        wallBlock(((WallBlock) ModBLocks.JUNGLE_WALL.get()), blockTexture(Blocks.JUNGLE_PLANKS));
        wallBlock(((WallBlock) ModBLocks.ACACIA_WALL.get()), blockTexture(Blocks.ACACIA_PLANKS));
        wallBlock(((WallBlock) ModBLocks.DARK_OAK_WALL.get()), blockTexture(Blocks.DARK_OAK_PLANKS));
        wallBlock(((WallBlock) ModBLocks.MANGROVE_WALL.get()), blockTexture(Blocks.MANGROVE_PLANKS));
        wallBlock(((WallBlock) ModBLocks.CHERRY_WALL.get()), blockTexture(Blocks.CHERRY_PLANKS));
        wallBlock(((WallBlock) ModBLocks.BAMBOO_WALL.get()), blockTexture(Blocks.BAMBOO_PLANKS));
        wallBlock(((WallBlock) ModBLocks.CRIMSON_WALL.get()), blockTexture(Blocks.CRIMSON_PLANKS));
        wallBlock(((WallBlock) ModBLocks.WARPED_WALL.get()), blockTexture(Blocks.WARPED_PLANKS));

        // Weirwood separately:
        simpleBlockWithItem(ModBLocks.POTTED_WEIRWOOD_SAPLING.get(),
                models().withExistingParent(ModBLocks.POTTED_WEIRWOOD_SAPLING.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.WEIRWOOD_SAPLING.get()))
                        .renderType("cutout"));

        // Define the wood types array
        String[] woodTypes = {
                "alder",
                "almond",
                "apple",
                "apricot",
                "ash",
                "aspen",
                "baobab",
                "beech",
                "black_cherry",
                "black_cottonwood",
                "black_olive",
                "blackbark",
                "blackthorn",
                "blood_orange",
                "bloodwood",
                "blue_mahoe",
                "cedar",
                "chestnut",
                "cottonwood",
                "crabapple",
                "datepalm",
                "ebony",
                "fig",
                "fir",
                "fireplum",
                "goldenheart",
                "hawthorn",
                "ironwood",
                "lemon",
                "lime",
                "linden",
                "mahogany",
                "maple",
                "myrrh",
                "nightwood",
                "nutmeg",
                "olive",
                "orange",
                "peach",
                "pear",
                "pecan",
                "persimmon",
                "pine",
                "pink_ivory",
                "plum",
                "pomegranate",
                "purpleheart",
                "redwood",
                "sandalwood",
                "sandbeggar",
                "sentinel",
                "sycamore",
                "tigerwood",
                "white_cherry",
                "willow",
                "wormtree",
                "yew",
                "blue_soldier_pine",
                "soldier_pine"
        };

// Loop through all wood types to register their models and textures
        for (String woodType : woodTypes) {
            // Get blocks from the maps in ModBLocks
            DeferredBlock<Block> logBlock = ModBLocks.LOGS.get(woodType);
            DeferredBlock<Block> woodBlock = ModBLocks.WOODS.get(woodType);
            DeferredBlock<Block> strippedLogBlock = ModBLocks.STRIPPED_LOGS.get(woodType);
            DeferredBlock<Block> strippedWoodBlock = ModBLocks.STRIPPED_WOODS.get(woodType);
            DeferredBlock<Block> planksBlock = ModBLocks.PLANKS.get(woodType);
            DeferredBlock<Block> leavesBlock = ModBLocks.LEAVES.get(woodType);
            DeferredBlock<Block> saplingBlock = ModBLocks.SAPLINGS.get(woodType);
            DeferredBlock<Block> stairsBlock = ModBLocks.STAIRS.get(woodType);
            DeferredBlock<Block> slabBlock = ModBLocks.SLABS.get(woodType);
            DeferredBlock<Block> buttonBlock = ModBLocks.BUTTONS.get(woodType);
            DeferredBlock<Block> pressurePlateBlock = ModBLocks.PRESSURE_PLATES.get(woodType);
            DeferredBlock<Block> fenceBlock = ModBLocks.FENCES.get(woodType);
            DeferredBlock<Block> fenceGateBlock = ModBLocks.FENCE_GATES.get(woodType);
            DeferredBlock<Block> wallBlock = ModBLocks.WALLS.get(woodType);
            DeferredBlock<Block> doorBlock = ModBLocks.DOORS.get(woodType);
            DeferredBlock<Block> trapdoorBlock = ModBLocks.TRAPDOORS.get(woodType);
            DeferredBlock<Block> signBlock = ModBLocks.SIGNS.get(woodType);
            DeferredBlock<Block> wallSignBlock = ModBLocks.WALL_SIGNS.get(woodType);
            DeferredBlock<Block> hangingSignBlock = ModBLocks.HANGING_SIGNS.get(woodType);
            DeferredBlock<Block> wallHangingSignBlock = ModBLocks.WALL_HANGING_SIGNS.get(woodType);

            // Inside your wood type loop, after the existing block model registrations:
            DeferredBlock<Block> pottedSaplingBlock = ModBLocks.POTTED_SAPLINGS.get(woodType);
            simpleBlockWithItem(pottedSaplingBlock.get(),
                    models().withExistingParent(pottedSaplingBlock.getId().getPath(), mcLoc("block/flower_pot_cross"))
                            .texture("plant", blockTexture(saplingBlock.get()))
                            .renderType("cutout"));



            // Register sign blocks
            signBlock(
                    ((StandingSignBlock) signBlock.get()),
                    ((WallSignBlock) wallSignBlock.get()),
                    blockTexture(planksBlock.get())
            );

            hangingSignBlock(
                    hangingSignBlock.get(),
                    wallHangingSignBlock.get(),
                    blockTexture(planksBlock.get())
            );

            // Register log and wood blocks
            // Log block - side and top textures
            axisBlock(
                    ((RotatedPillarBlock) logBlock.get()),
                    blockTexture(logBlock.get()),
                    AGoTMod.id("block/" + woodType + "_log_top")
            );

            // Wood block - uses log side texture for all faces
            axisBlock(
                    ((RotatedPillarBlock) woodBlock.get()),
                    blockTexture(logBlock.get()),
                    blockTexture(logBlock.get())
            );

            // Stripped log - side and top textures
            axisBlock(
                    ((RotatedPillarBlock) strippedLogBlock.get()),
                    blockTexture(strippedLogBlock.get()),
                    AGoTMod.id("block/" + woodType + "_log_top_stripped")
            );

            // Stripped wood - uses stripped log side texture for all faces
            axisBlock(
                    ((RotatedPillarBlock) strippedWoodBlock.get()),
                    blockTexture(strippedLogBlock.get()),
                    blockTexture(strippedLogBlock.get())
            );

            // Register items for logs and wood
            // Instead of using cubeAll which would apply the same texture to all faces
            // we need to use models that respect the axis orientation for logs and wood
            simpleBlockItem(logBlock.get(), models().getExistingFile(AGoTMod.id("block/" + woodType + "_log")));
            simpleBlockItem(woodBlock.get(), models().getExistingFile(AGoTMod.id("block/" + woodType + "_wood")));
            simpleBlockItem(strippedLogBlock.get(), models().getExistingFile(AGoTMod.id("block/" + woodType + "_log_stripped")));
            simpleBlockItem(strippedWoodBlock.get(), models().getExistingFile(AGoTMod.id("block/" + woodType + "_wood_stripped")));

            // Register planks and related blocks
            simpleBlockWithItem(planksBlock.get(), cubeAll(planksBlock.get()));

            simpleBlockWithItem(leavesBlock.get(),
                    models().cubeAll(name(leavesBlock.get()), blockTexture(leavesBlock.get()))
                            .renderType("cutout"));


            // Sapling with cross rendering
            simpleBlock(saplingBlock.get(),
                    models().cross(name(saplingBlock.get()), blockTexture(saplingBlock.get()))
                            .renderType("cutout"));

            stairsBlock(
                    ((StairBlock) stairsBlock.get()),
                    blockTexture(planksBlock.get())
            );

            slabBlock(
                    ((SlabBlock) slabBlock.get()),
                    blockTexture(planksBlock.get()),
                    blockTexture(planksBlock.get())
            );

            buttonBlock(
                    ((ButtonBlock) buttonBlock.get()),
                    blockTexture(planksBlock.get())
            );

            pressurePlateBlock(
                    ((PressurePlateBlock) pressurePlateBlock.get()),
                    blockTexture(planksBlock.get())
            );

            fenceBlock(
                    ((FenceBlock) fenceBlock.get()),
                    blockTexture(planksBlock.get())
            );

            fenceGateBlock(
                    ((FenceGateBlock) fenceGateBlock.get()),
                    blockTexture(planksBlock.get())
            );

            wallBlock(
                    ((WallBlock) wallBlock.get()),
                    blockTexture(planksBlock.get())
            );

            doorBlockWithRenderType(
                    ((DoorBlock) doorBlock.get()),
                    modLoc("block/" + woodType + "_door_bottom"),
                    modLoc("block/" + woodType + "_door_top"),
                    "cutout"
            );

            trapdoorBlockWithRenderType(
                    ((TrapDoorBlock) trapdoorBlock.get()),
                    modLoc("block/" + woodType + "_trapdoor"),
                    true,
                    "cutout"
            );
        }


        // ---------------------------(STONE BLOCKS)--------------------------- //

        //Redkeep
        blockWithItem(ModBLocks.REDKEEP_STONE_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_STONE_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_STONE_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_STONE_WALL.get()), blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()));

        registerVariantSet(ModBLocks.BLACKSTONE_VARIANTS, "blackstone",  false);
        registerVariantSet(ModBLocks.BASALT_VARIANTS,     "basalt",      false);
        registerVariantSet(ModBLocks.BRICKS_VARIANTS,     "bricks",      false);
        registerVariantSet(ModBLocks.CALCITE_VARIANTS,    "calcite",     false);
        registerVariantSet(ModBLocks.CDEEPSLATE_VARIANTS, "cdeepslate",  false);
        registerVariantSet(ModBLocks.GRANITE_VARIANTS,    "granite",     false);
        registerVariantSet(ModBLocks.REDKEEP_VARIANTS,    "redkeep",     false);
        registerVariantSet(ModBLocks.RSANDSTONE_VARIANTS, "rsandstone",  false);
        registerVariantSet(ModBLocks.SANDSTONE_VARIANTS,  "sandstone",   false);
        registerVariantSet(ModBLocks.STONE_VARIANTS,      "stone",       true);
        registerVariantSet(ModBLocks.SSTONE_VARIANTS,     "sstone",      false);
        registerVariantSet(ModBLocks.BONE_VARIANTS,       "bone",        false);
        registerVariantSet(ModBLocks.DRIPSTONE_VARIANTS,  "dripstone",   false);
        registerVariantSet(ModBLocks.PACKED_ICE_VARIANTS, "packed_ice",  false);
        registerVariantSet(ModBLocks.MUD_BRICK_VARIANTS,  "mud_brick",   false);
        registerVariantSet(ModBLocks.ANDESITE_VARIANTS,   "andesite",    false);
        registerVariantSet(ModBLocks.QUARTZ_VARIANTS,     "quartz",      false);
        registerVariantSet(ModBLocks.DIORITE_VARIANTS,    "diorite",     false);
        registerVariantSet(ModBLocks.TUFF_VARIANTS,       "tuff_brick",  false);


        // ---------------------------(FLOWERS)--------------------------- //

        // Register the block model
        models().withExistingParent("block/" + ModBLocks.WINTER_ROSE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/winter_rose"))
                .renderType("cutout");

// Register the item model (using the same texture)
        models().withExistingParent("item/" + ModBLocks.WINTER_ROSE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/winter_rose"))
                .renderType("cutout");

// Register the blockstate pointing to the single model
        getVariantBuilder(ModBLocks.WINTER_ROSE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.WINTER_ROSE.getId().getPath())))
                .addModel();

// Block model
        models().withExistingParent("block/" + ModBLocks.WILD_RADISH.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/wild_radish"))
                .renderType("cutout");

// Item model
        models().withExistingParent("item/" + ModBLocks.WILD_RADISH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/wild_radish"))
                .renderType("cutout");

// Blockstate
        getVariantBuilder(ModBLocks.WILD_RADISH.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.WILD_RADISH.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.WHITE_ROSE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/white_rose"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.WHITE_ROSE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/white_rose"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.WHITE_ROSE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.WHITE_ROSE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.THORN_BUSH.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/thorn_bush"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.THORN_BUSH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/thorn_bush"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.THORN_BUSH.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.THORN_BUSH.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.THISTLE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/thistle"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.THISTLE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/thistle"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.THISTLE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.THISTLE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.TANSY.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/tansy"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.TANSY.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/tansy"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.TANSY.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.TANSY.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.SPICEFLOWER.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/spiceflower"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.SPICEFLOWER.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/spiceflower"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.SPICEFLOWER.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.SPICEFLOWER.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.SEDGE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/sedge"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.SEDGE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/sedge"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.SEDGE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.SEDGE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.SAFFRON_CROCUS.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/saffron_crocus"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.SAFFRON_CROCUS.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/saffron_crocus"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.SAFFRON_CROCUS.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.SAFFRON_CROCUS.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.ROSE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/rose"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.ROSE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/rose"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.ROSE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.ROSE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.POISON_KISSES.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/poison_kisses"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.POISON_KISSES.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/poison_kisses"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.POISON_KISSES.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.POISON_KISSES.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.PENNYROYAL.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/pennyroyal"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.PENNYROYAL.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/pennyroyal"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.PENNYROYAL.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.PENNYROYAL.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.OPIUM_POPPY_WILD.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/opium_poppy_wild"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.OPIUM_POPPY_WILD.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/opium_poppy_wild"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.OPIUM_POPPY_WILD.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.OPIUM_POPPY_WILD.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.NIGHTSHADE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/nightshade"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.NIGHTSHADE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/nightshade"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.NIGHTSHADE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.NIGHTSHADE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.MOONBLOOM.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/moonbloom"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.MOONBLOOM.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/moonbloom"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.MOONBLOOM.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.MOONBLOOM.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.LUNGWORT.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/lungwort"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.LUNGWORT.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/lungwort"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.LUNGWORT.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.LUNGWORT.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.LIVERWORT.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/liverwort"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.LIVERWORT.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/liverwort"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.LIVERWORT.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.LIVERWORT.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.LAVENDER.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/lavender"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.LAVENDER.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/lavender"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.LAVENDER.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.LAVENDER.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.LADYS_LACE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/ladys_lace"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.LADYS_LACE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/ladys_lace"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.LADYS_LACE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.LADYS_LACE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.GORSE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/gorse"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.GORSE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/gorse"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.GORSE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GORSE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.GOLDENROD.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/goldenrod"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.GOLDENROD.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/goldenrod"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.GOLDENROD.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GOLDENROD.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.GOLDENCUP.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/goldencup"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.GOLDENCUP.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/goldencup"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.GOLDENCUP.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GOLDENCUP.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.GOATHEAD.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/goathead"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.GOATHEAD.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/goathead"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.GOATHEAD.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GOATHEAD.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.GINGER.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/ginger"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.GINGER.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/ginger"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.GINGER.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GINGER.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.GILLYFLOWER.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/gillyflower"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.GILLYFLOWER.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/gillyflower"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.GILLYFLOWER.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GILLYFLOWER.getId().getPath())))
                .addModel();

        models().withExistingParent("block/" + ModBLocks.FROSTFIRE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/frostfire"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.FROSTFIRE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/frostfire"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.FROSTFIRE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.FROSTFIRE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.FORGET_ME_NOT.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/forget_me_not"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.FORGET_ME_NOT.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/forget_me_not"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.FORGET_ME_NOT.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.FORGET_ME_NOT.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.EVENING_STAR.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/evening_star"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.EVENING_STAR.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/evening_star"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.EVENING_STAR.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.EVENING_STAR.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.DUSKY_ROSE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/dusky_rose"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.DUSKY_ROSE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/dusky_rose"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.DUSKY_ROSE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.DUSKY_ROSE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.DRAGONS_BREATH.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/dragons_breath"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.DRAGONS_BREATH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/dragons_breath"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.DRAGONS_BREATH.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.DRAGONS_BREATH.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.COLDSNAP.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/coldsnap"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.COLDSNAP.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/coldsnap"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.COLDSNAP.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.COLDSNAP.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.BLUE_ROSE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/blue_rose"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.BLUE_ROSE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/blue_rose"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.BLUE_ROSE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.BLUE_ROSE.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.BLOODBLOOM.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/bloodbloom"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.BLOODBLOOM.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/bloodbloom"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.BLOODBLOOM.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.BLOODBLOOM.getId().getPath())))
                .addModel();


        models().withExistingParent("block/" + ModBLocks.BLACK_LOTUS.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/black_lotus"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.BLACK_LOTUS.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/black_lotus"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.BLACK_LOTUS.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.BLACK_LOTUS.getId().getPath())))
                .addModel();


// First register the bottom half
        models().withExistingParent("block/" + ModBLocks.WINTER_ROSE_BUSH.getId().getPath() + "_bottom",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/winter_rose_bush_bottom"))
                .renderType("cutout");

// Then register the top half
        models().withExistingParent("block/" + ModBLocks.WINTER_ROSE_BUSH.getId().getPath() + "_top",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/winter_rose_bush_top"))
                .renderType("cutout");

// Register the item model (using bottom texture typically)
        models().withExistingParent("item/" + ModBLocks.WINTER_ROSE_BUSH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/winter_rose_bush_top"))
                .renderType("cutout");

// Now register the blockstate that references both models
        getVariantBuilder(ModBLocks.WINTER_ROSE_BUSH.get())
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.WINTER_ROSE_BUSH.getId().getPath() + "_bottom"))).addModel()
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.WINTER_ROSE_BUSH.getId().getPath() + "_top"))).addModel();

        // First register the bottom half
        models().withExistingParent("block/" + ModBLocks.WHITE_ROSE_BUSH.getId().getPath() + "_bottom",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/white_rose_bush_bottom"))
                .renderType("cutout");

// Then register the top half
        models().withExistingParent("block/" + ModBLocks.WHITE_ROSE_BUSH.getId().getPath() + "_top",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/white_rose_bush_top"))
                .renderType("cutout");

// Register the item model (using bottom texture typically)
        models().withExistingParent("item/" + ModBLocks.WHITE_ROSE_BUSH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/white_rose_bush_top"))
                .renderType("cutout");

// Now register the blockstate that references both models
        getVariantBuilder(ModBLocks.WHITE_ROSE_BUSH.get())
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.WHITE_ROSE_BUSH.getId().getPath() + "_bottom"))).addModel()
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.WHITE_ROSE_BUSH.getId().getPath() + "_top"))).addModel();

        // First register the bottom half
        models().withExistingParent("block/" + ModBLocks.DUSKY_ROSE_BUSH.getId().getPath() + "_bottom",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/dusky_rose_bush_bottom"))
                .renderType("cutout");

// Then register the top half
        models().withExistingParent("block/" + ModBLocks.DUSKY_ROSE_BUSH.getId().getPath() + "_top",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/dusky_rose_bush_top"))
                .renderType("cutout");

// Register the item model (using bottom texture typically)
        models().withExistingParent("item/" + ModBLocks.DUSKY_ROSE_BUSH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/dusky_rose_bush_top"))
                .renderType("cutout");

// Now register the blockstate that references both models
        getVariantBuilder(ModBLocks.DUSKY_ROSE_BUSH.get())
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.DUSKY_ROSE_BUSH.getId().getPath() + "_bottom"))).addModel()
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.DUSKY_ROSE_BUSH.getId().getPath() + "_top"))).addModel();

        // First register the bottom half
        models().withExistingParent("block/" + ModBLocks.RED_ROSE_BUSH.getId().getPath() + "_bottom",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/red_rose_bush_bottom"))
                .renderType("cutout");

// Then register the top half
        models().withExistingParent("block/" + ModBLocks.RED_ROSE_BUSH.getId().getPath() + "_top",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/red_rose_bush_top"))
                .renderType("cutout");

// Register the item model (using bottom texture typically)
        models().withExistingParent("item/" + ModBLocks.RED_ROSE_BUSH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/red_rose_bush_top"))
                .renderType("cutout");

// Now register the blockstate that references both models
        getVariantBuilder(ModBLocks.RED_ROSE_BUSH.get())
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.RED_ROSE_BUSH.getId().getPath() + "_bottom"))).addModel()
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.RED_ROSE_BUSH.getId().getPath() + "_top"))).addModel();

        // First register the bottom half
        models().withExistingParent("block/" + ModBLocks.BLUE_ROSE_BUSH.getId().getPath() + "_bottom",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/blue_rose_bush_bottom"))
                .renderType("cutout");

// Then register the top half
        models().withExistingParent("block/" + ModBLocks.BLUE_ROSE_BUSH.getId().getPath() + "_top",
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/blue_rose_bush_top"))
                .renderType("cutout");

// Register the item model (using bottom texture typically)
        models().withExistingParent("item/" + ModBLocks.BLUE_ROSE_BUSH.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/blue_rose_bush_top"))
                .renderType("cutout");

        // Ghost Grass Bottom
        models().withExistingParent("block/" + ModBLocks.GHOST_GRASS.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/ghost_grass_bottom"))
                .renderType("cutout");
        
        models().withExistingParent("item/" + ModBLocks.GHOST_GRASS.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/ghost_grass_bottom"));
        
        getVariantBuilder(ModBLocks.GHOST_GRASS.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GHOST_GRASS.getId().getPath())))
                .addModel();
        
        // Ghost Grass Middle
        models().withExistingParent("block/" + ModBLocks.GHOST_GRASS_MIDDLE.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/ghost_grass_middle"))
                .renderType("cutout");
        
        models().withExistingParent("item/" + ModBLocks.GHOST_GRASS_MIDDLE.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/ghost_grass_middle"));
        
        getVariantBuilder(ModBLocks.GHOST_GRASS_MIDDLE.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GHOST_GRASS_MIDDLE.getId().getPath())))
                .addModel();
        
        // Ghost Grass Top
        models().withExistingParent("block/" + ModBLocks.GHOST_GRASS_TOP.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/ghost_grass_top"))
                .renderType("cutout");
        
        models().withExistingParent("item/" + ModBLocks.GHOST_GRASS_TOP.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/ghost_grass_top"));
        
        getVariantBuilder(ModBLocks.GHOST_GRASS_TOP.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.GHOST_GRASS_TOP.getId().getPath())))
                .addModel();




        simpleBlockWithItem(ModBLocks.POTTED_WINTER_ROSE.get(),
                models().withExistingParent(ModBLocks.POTTED_WINTER_ROSE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.WINTER_ROSE.get())) // Make sure this texture is valid
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_WILD_RADISH.get(),
                models().withExistingParent(ModBLocks.POTTED_WILD_RADISH.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.WILD_RADISH.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_WHITE_ROSE.get(),
                models().withExistingParent(ModBLocks.POTTED_WHITE_ROSE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.WHITE_ROSE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_THORN_BUSH.get(),
                models().withExistingParent(ModBLocks.POTTED_THORN_BUSH.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.THORN_BUSH.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_THISTLE.get(),
                models().withExistingParent(ModBLocks.POTTED_THISTLE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.THISTLE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_TANSY.get(),
                models().withExistingParent(ModBLocks.POTTED_TANSY.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.TANSY.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_SPICEFLOWER.get(),
                models().withExistingParent(ModBLocks.POTTED_SPICEFLOWER.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.SPICEFLOWER.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_SEDGE.get(),
                models().withExistingParent(ModBLocks.POTTED_SEDGE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.SEDGE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_SAFFRON_CROCUS.get(),
                models().withExistingParent(ModBLocks.POTTED_SAFFRON_CROCUS.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.SAFFRON_CROCUS.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_ROSE.get(),
                models().withExistingParent(ModBLocks.POTTED_ROSE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.ROSE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_POISON_KISSES.get(),
                models().withExistingParent(ModBLocks.POTTED_POISON_KISSES.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.POISON_KISSES.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_PENNYROYAL.get(),
                models().withExistingParent(ModBLocks.POTTED_PENNYROYAL.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.PENNYROYAL.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_OPIUM_POPPY_WILD.get(),
                models().withExistingParent(ModBLocks.POTTED_OPIUM_POPPY_WILD.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.OPIUM_POPPY_WILD.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_NIGHTSHADE.get(),
                models().withExistingParent(ModBLocks.POTTED_NIGHTSHADE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.NIGHTSHADE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_MOONBLOOM.get(),
                models().withExistingParent(ModBLocks.POTTED_MOONBLOOM.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.MOONBLOOM.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_LUNGWORT.get(),
                models().withExistingParent(ModBLocks.POTTED_LUNGWORT.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.LUNGWORT.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_LIVERWORT.get(),
                models().withExistingParent(ModBLocks.POTTED_LIVERWORT.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.LIVERWORT.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_LAVENDER.get(),
                models().withExistingParent(ModBLocks.POTTED_LAVENDER.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.LAVENDER.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_LADYS_LACE.get(),
                models().withExistingParent(ModBLocks.POTTED_LADYS_LACE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.LADYS_LACE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_GORSE.get(),
                models().withExistingParent(ModBLocks.POTTED_GORSE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.GORSE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_GOLDENROD.get(),
                models().withExistingParent(ModBLocks.POTTED_GOLDENROD.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.GOLDENROD.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_GOLDENCUP.get(),
                models().withExistingParent(ModBLocks.POTTED_GOLDENCUP.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.GOLDENCUP.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_GOATHEAD.get(),
                models().withExistingParent(ModBLocks.POTTED_GOATHEAD.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.GOATHEAD.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_GINGER.get(),
                models().withExistingParent(ModBLocks.POTTED_GINGER.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.GINGER.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_GILLYFLOWER.get(),
                models().withExistingParent(ModBLocks.POTTED_GILLYFLOWER.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.GILLYFLOWER.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_FROSTFIRE.get(),
                models().withExistingParent(ModBLocks.POTTED_FROSTFIRE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.FROSTFIRE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_FORGET_ME_NOT.get(),
                models().withExistingParent(ModBLocks.POTTED_FORGET_ME_NOT.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.FORGET_ME_NOT.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_EVENING_STAR.get(),
                models().withExistingParent(ModBLocks.POTTED_EVENING_STAR.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.EVENING_STAR.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_DUSKY_ROSE.get(),
                models().withExistingParent(ModBLocks.POTTED_DUSKY_ROSE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.DUSKY_ROSE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_DRAGONS_BREATH.get(),
                models().withExistingParent(ModBLocks.POTTED_DRAGONS_BREATH.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.DRAGONS_BREATH.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_COLDSNAP.get(),
                models().withExistingParent(ModBLocks.POTTED_COLDSNAP.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.COLDSNAP.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_BLUE_ROSE.get(),
                models().withExistingParent(ModBLocks.POTTED_BLUE_ROSE.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.BLUE_ROSE.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_BLOODBLOOM.get(),
                models().withExistingParent(ModBLocks.POTTED_BLOODBLOOM.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.BLOODBLOOM.get()))
                        .renderType("cutout"));

        simpleBlockWithItem(ModBLocks.POTTED_BLACK_LOTUS.get(),
                models().withExistingParent(ModBLocks.POTTED_BLACK_LOTUS.getId().getPath(), mcLoc("block/flower_pot_cross"))
                        .texture("plant", blockTexture(ModBLocks.BLACK_LOTUS.get()))
                        .renderType("cutout"));

        // ---------------------(SIGNS)--------------------- //



        // ---------------------(Job Blocks)--------------------- //
        blockWithItem(ModBLocks.TOWN_HALL);
        jobBarrelBlock(ModBLocks.ALEHOUSE_BARREL.get());
        jobBarrelBlock(ModBLocks.ARMORSMITH_BARREL.get());
        jobBarrelBlock(ModBLocks.BAKER_BARREL.get());
        jobBarrelBlock(ModBLocks.BANKER_BARREL.get());
        jobBarrelBlock(ModBLocks.BARD_BARREL.get());
        jobBarrelBlock(ModBLocks.BLACKSMITH_BARREL.get());
        jobBarrelBlock(ModBLocks.BUILDER_BARREL.get());
        jobBarrelBlock(ModBLocks.BUTCHER_BARREL.get());
        jobBarrelBlock(ModBLocks.CARAVAN_MASTER_BARREL.get());
        jobBarrelBlock(ModBLocks.CARPENTER_BARREL.get());
        jobBarrelBlock(ModBLocks.CATTLE_HERDER_BARREL.get());
        jobBarrelBlock(ModBLocks.CHARCOAL_BURNER_BARREL.get());
        jobBarrelBlock(ModBLocks.CHICKEN_BREEDER_BARREL.get());
        jobBarrelBlock(ModBLocks.FARMER_BARREL.get());
        jobBarrelBlock(ModBLocks.GOAT_HERDER_BARREL.get());
        jobBarrelBlock(ModBLocks.GROCER_BARREL.get());
        jobBarrelBlock(ModBLocks.GUARD_BARREL.get());
        jobBarrelBlock(ModBLocks.HERBALIST_BARREL.get());
        jobBarrelBlock(ModBLocks.HORSE_BREEDER_BARREL.get());
        jobBarrelBlock(ModBLocks.HUNTER_BARREL.get());
        jobBarrelBlock(ModBLocks.INNKEEPER_BARREL.get());
        jobBarrelBlock(ModBLocks.JEWELER_BARREL.get());
        jobBarrelBlock(ModBLocks.LUMBERJACK_BARREL.get());
        jobBarrelBlock(ModBLocks.MAESTER_BARREL.get());
        jobBarrelBlock(ModBLocks.MINER_BARREL.get());
        jobBarrelBlock(ModBLocks.PIG_BREEDER_BARREL.get());
        jobBarrelBlock(ModBLocks.PYROMANCER_BARREL.get());
        jobBarrelBlock(ModBLocks.QUARRY_BARREL.get());
        jobBarrelBlock(ModBLocks.SCRIBE_BARREL.get());
        jobBarrelBlock(ModBLocks.SEPTON_BARREL.get());
        jobBarrelBlock(ModBLocks.SHEEP_HERDER_BARREL.get());
        jobBarrelBlock(ModBLocks.SHIPWRIGHT_BARREL.get());
        jobBarrelBlock(ModBLocks.SLAVER_BARREL.get());
        jobBarrelBlock(ModBLocks.SMELTER_BARREL.get());
        jobBarrelBlock(ModBLocks.STONEMASON_BARREL.get());
        jobBarrelBlock(ModBLocks.SWORDSMITH_BARREL.get());
        jobBarrelBlock(ModBLocks.TAILOR_BARREL.get());
        jobBarrelBlock(ModBLocks.TANNER_BARREL.get());
        jobBarrelBlock(ModBLocks.TRADER_BARREL.get());


        // ---------------------(CROPS)--------------------- //
        makeCrop((CropBlock) ModBLocks.HORSERADISH_CROP.get(), "horseradish_crop_stage", "horseradish_crop_stage");
        makeCrop((CropBlock) ModBLocks.BARLEY_CROP.get(), "barley_crop_stage", "barley_crop_stage");
        makeCrop((CropBlock) ModBLocks.GARLIC_CROP.get(), "garlic_crop_stage", "garlic_crop_stage");
        makeCrop((CropBlock) ModBLocks.LEEK_CROP.get(), "leek_crop_stage", "leek_crop_stage");
        makeCrop((CropBlock) ModBLocks.NEEP_CROP.get(), "neep_crop_stage", "neep_crop_stage");
        makeCrop((CropBlock) ModBLocks.OAT_CROP.get(), "oat_crop_stage", "oat_crop_stage");
        makeCrop((CropBlock) ModBLocks.PARSLEY_CROP.get(), "parsley_crop_stage", "parsley_crop_stage");
        makeCrop((CropBlock) ModBLocks.RED_ONION_CROP.get(), "red_onion_crop_stage", "red_onion_crop_stage");
        makeCrop((CropBlock) ModBLocks.TURNIP_CROP.get(), "turnip_crop_stage", "turnip_crop_stage");
        makeCrop((CropBlock) ModBLocks.WILD_ONION_CROP.get(), "wild_onion_crop_stage", "wild_onion_crop_stage");
        makeCrop((CropBlock) ModBLocks.ONION_CROP.get(), "onion_crop_stage", "onion_crop_stage");
        makeCrop((CropBlock) ModBLocks.OPIUM_POPPY_CROP.get(), "opium_poppy_crop_stage", "opium_poppy_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.CABBAGE_CROP.get(), "cabbage_crop_stage", "cabbage_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.BEAN_CROP.get(), "bean_crop_stage", "bean_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.CHICKPEA_CROP.get(), "chickpea_crop_stage", "chickpea_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.CUCUMBER_CROP.get(), "cucumber_crop_stage", "cucumber_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.GREEN_BEAN_CROP.get(), "green_bean_crop_stage", "green_bean_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.SPINACH_CROP.get(), "spinach_crop_stage", "spinach_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.DRAGON_PEPPER_CROP.get(), "dragon_pepper_crop_stage", "dragon_pepper_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.PEPPER_CROP.get(), "pepper_crop_stage", "pepper_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.PEPPERCORN_CROP.get(), "peppercorn_crop_stage", "peppercorn_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.COTTON_CROP.get(), "cotton_crop_stage", "cotton_crop_stage");
        makeTorchflowerStyleCrop((CropBlock) ModBLocks.HEMP_CROP.get(), "hemp_crop_stage", "hemp_crop_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.STRAWBERRY_BUSH.get()), "strawberry_bush_stage", "strawberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.BLACKBERRY_BUSH.get()), "blackberry_bush_stage", "blackberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.BLUEBERRY_BUSH.get()), "blueberry_bush_stage", "blueberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.MULBERRY_BUSH.get()), "mulberry_bush_stage", "mulberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.RASPBERRY_BUSH.get()), "raspberry_bush_stage", "raspberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.SMOKEBERRY_BUSH.get()), "smokeberry_bush_stage", "smokeberry_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.CORN_CROP.get()), "corn_bush_stage", "corn_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.CORN_CROP_MIDDLE.get()), "corn_middle_bush_stage", "corn_middle_bush_stage");
        makeBush(((SweetBerryBushBlock) ModBLocks.CORN_CROP_TOP.get()), "corn_top_bush_stage", "corn_top_bush_stage");






    }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        int age = state.getValue(SweetBerryBushBlock.AGE);
        models[0] = new ConfiguredModel(models().cross(modelName + age,
                ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "block/" + textureName + age)).renderType("cutout"));

        return models;
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];

        IntegerProperty ageProperty = getAgeProperty(block);

        models[0] = new ConfiguredModel(
                models().crop(
                        modelName + state.getValue(ageProperty),
                        ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "block/" + textureName + state.getValue(ageProperty))
                ).renderType("cutout")
        );

        return models;
    }
    public void makeTorchflowerStyleCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> torchflowerStyleStates(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] torchflowerStyleStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        IntegerProperty ageProperty = getAgeProperty(block);
        int age = state.getValue(ageProperty);


        models[0] = new ConfiguredModel(
                models().withExistingParent(modelName + age, mcLoc("block/cross"))
                        .texture("cross", modLoc("block/" + textureName + age))
                        .renderType("cutout")
        );

        return models;
    }

    private IntegerProperty getAgeProperty(CropBlock block) {
        try {
            Method getAgePropertyMethod = block.getClass().getMethod("getAgeProperty");
            return (IntegerProperty) getAgePropertyMethod.invoke(block);
        } catch (Exception e) {
            return CropBlock.AGE;
        }
    }



    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private void saplingBlock(@NotNull DeferredHolder<Block, Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(@NotNull DeferredHolder<Block, Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(@NotNull DeferredHolder<Block, Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(AGoTMod.MOD_ID +
                ":block/" + BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath()));
    }

    // Method to simplify the process of registering block states and models
    private void blockWithItem(@NotNull DeferredHolder<Block, Block> blockRegistryObject) {
        // Generate block states and models for a block with its corresponding item
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void weirwoodFaceLogBlock(Block block) {
        ModelFile model = models().cube(name(block),
                modLoc("block/weirwood_log_top"),     // bottom
                modLoc("block/weirwood_log_top"),     // top
                modLoc("block/weirwood_face"),        // north
                modLoc("block/weirwood_log"),         // south
                modLoc("block/weirwood_log"),         // west
                modLoc("block/weirwood_log")          // east
        ).texture("particle", modLoc("block/weirwood_log"));
    
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(CarvedPumpkinBlock.FACING);
                    
                    int rotationY = switch (facing) {
                        case SOUTH -> 180;
                        case WEST -> 270;
                        case EAST -> 90;
                        default -> 0;
                    };
    
                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationY(rotationY)
                            .build();
                });
    }



    private void jobBarrelBlock(Block block) {
        String blockName = name(block);
        String jobType = blockName.replace("_barrel", ""); // Extracts job type from block name

        ModelFile model = models().cube(blockName,
                modLoc("block/barrel_bottom"),                           // bottom
                modLoc("block/barrel_top"),                              // top
                modLoc("block/barrel_" + jobType),                       // north
                modLoc("block/barrel_" + jobType),                       // south
                modLoc("block/barrel_" + jobType),                       // west
                modLoc("block/barrel_" + jobType)                        // east
        ).texture("particle", modLoc("block/barrel_" + jobType));

        // Create open variant
        ModelFile openModel = models().cube(blockName + "_open",
                modLoc("block/barrel_bottom"),                           // bottom
                modLoc("block/barrel_top_open"),                         // top
                modLoc("block/barrel_" + jobType),                       // north
                modLoc("block/barrel_" + jobType),                       // south
                modLoc("block/barrel_" + jobType),                       // west
                modLoc("block/barrel_" + jobType)                        // east
        ).texture("particle", modLoc("block/barrel_" + jobType));

        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(BlockStateProperties.FACING); // or HORIZONTAL_FACING
                    boolean open = state.getValue(BlockStateProperties.OPEN);

                    return ConfiguredModel.builder()
                            .modelFile(open ? openModel : model)
                            .rotationX(facing == Direction.DOWN ? 180 : facing.getAxis().isHorizontal() ? 0 : 0)
                            .rotationY(facing.getAxis().isVertical() ? 0 : ((int) facing.toYRot()) % 360)
                            .build();
                });
    }

    private void GhostGrassBlock(Block block) {
        ModelFile model = models().cube(name(block),
                modLoc("block/ghostgrass_block_bottom"),     // bottom
                modLoc("block/ghost_grass_block"),     // top
                modLoc("block/ghostgrass_block_side"),        // north
                modLoc("block/ghostgrass_block_side"),         // south
                modLoc("block/ghostgrass_block_side"),         // west
                modLoc("block/ghostgrass_block_side")          // east
        ).texture("particle", modLoc("block/ghostgrass_block_side"));

    getVariantBuilder(block)
                .forAllStates(state -> {
        Direction facing = state.getValue(CarvedPumpkinBlock.FACING);

        int rotationY = switch (facing) {
            case SOUTH -> 180;
            case WEST -> 270;
            case EAST -> 90;
            default -> 0;
        };

        return ConfiguredModel.builder()
                .modelFile(model)
                .rotationY(rotationY)
                .build();
    });
}

    private void registerVariantSet(Map<Integer, ModBLocks.BlockSet> variants, String prefix, boolean useVanillaCobblestoneForBottom) {
        for (Map.Entry<Integer, ModBLocks.BlockSet> entry : variants.entrySet()) {
            int i = entry.getKey();
            ModBLocks.BlockSet set = entry.getValue();

            net.minecraft.resources.ResourceLocation side;
            net.minecraft.resources.ResourceLocation top;
            net.minecraft.resources.ResourceLocation bottom;

            if (i == 16) {
                side   = modLoc("block/" + prefix + "_16_block");
                top    = modLoc("block/" + prefix + "_17_block");
                bottom = useVanillaCobblestoneForBottom
                        ? mcLoc("block/cobblestone")
                        : modLoc("block/" + prefix + "_5_block");
            } else if (i == 18) {
                side   = modLoc("block/" + prefix + "_18_block");
                top    = modLoc("block/" + prefix + "_17_block");
                bottom = modLoc("block/" + prefix + "_17_block");
            } else {
                side   = blockTexture(set.base().get());
                top    = blockTexture(set.base().get());
                bottom = blockTexture(set.base().get());
            }

            // Base block
            if (i == 16 || i == 18) {
                ModelFile baseModel = models().cubeBottomTop(
                        prefix + "_" + i + "_block_model", side, bottom, top);
                simpleBlockWithItem(set.base().get(), baseModel);
            } else {
                blockWithItem(set.base());
            }

            ModelFile slab       = models().slab(         prefix + "_" + i + "_slab",        side, bottom, top);
            ModelFile slabTop    = models().slabTop(      prefix + "_" + i + "_slab_top",     side, bottom, top);
            ModelFile slabDouble = models().cubeBottomTop(prefix + "_" + i + "_slab_double",  side, bottom, top);

            slabBlock((SlabBlock) set.slab().get(), slab, slabTop, slabDouble);

            ModelFile stairs      = models().stairs(      prefix + "_" + i + "_stairs",       side, bottom, top);
            ModelFile stairsInner = models().stairsInner( prefix + "_" + i + "_stairs_inner", side, bottom, top);
            ModelFile stairsOuter = models().stairsOuter( prefix + "_" + i + "_stairs_outer", side, bottom, top);

            stairsBlock((StairBlock) set.stairs().get(), stairs, stairsInner, stairsOuter);
            wallBlock((WallBlock) set.wall().get(), side);
        }
    }

    private BlockModelBuilder wattleModel(String name, int wattleIndex) {
        BlockModelBuilder builder = models()
                .getBuilder(name)
                .parent(models().getExistingFile(mcLoc("block/block")))
                .renderType("cutout")
                .texture("particle", modLoc("block/wattle_and_daub1"))
                .texture("daub",     modLoc("block/wattle_and_daub1"));

        // Element 1 â€” full cube, daub texture (tinted) on all faces
        builder.element()
                .from(0, 0, 0).to(16, 16, 16)
                .face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#daub").tintindex(0).end()
                .face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#daub").tintindex(0).end()
                .face(Direction.EAST ).uvs(0, 0, 16, 16).texture("#daub").tintindex(0).end()
                .face(Direction.WEST ).uvs(0, 0, 16, 16).texture("#daub").tintindex(0).end()
                .face(Direction.UP   ).uvs(0, 0, 16, 16).texture("#daub").tintindex(0).end()
                .face(Direction.DOWN ).uvs(0, 0, 16, 16).texture("#daub").tintindex(0).end()
                .end();

        if (wattleIndex > 0) {
            // Element 2 â€” side faces only, wattle overlay (no tint)
            builder.texture("wattle", modLoc("block/wattle_and_daub" + wattleIndex));
            builder.element()
                    .from(0, 0, 0).to(16, 16, 16)
                    .face(Direction.NORTH).uvs(0, 0, 16, 16).texture("#wattle").end()
                    .face(Direction.SOUTH).uvs(0, 0, 16, 16).texture("#wattle").end()
                    .face(Direction.EAST ).uvs(0, 0, 16, 16).texture("#wattle").end()
                    .face(Direction.WEST ).uvs(0, 0, 16, 16).texture("#wattle").end()
                    .end();
        }

        return builder;
    }

    private void wattleAndDaubBlock(DeferredBlock<Block> block, int wattleIndex) {
        String blockName = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
        BlockModelBuilder model = wattleModel(blockName, wattleIndex);

        getVariantBuilder(block.get())
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).addModel();

        // Same pattern as blockItem() â€” UncheckedModelFile references the model we just generated
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(
                AGoTMod.MOD_ID + ":block/" + blockName));
    }


}

