package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

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
        // ---------------------------(BRICKS)--------------------------- //

        // ---------------------------(TREES)--------------------------- //
        //Weirwood
        logBlock((RotatedPillarBlock) ModBLocks.WEIRWOOD_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.WEIRWOOD_WOOD.get()),
                blockTexture(ModBLocks.WEIRWOOD_LOG.get()), // side of the block
                blockTexture(ModBLocks.WEIRWOOD_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_WEIRWOOD_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_WEIRWOOD_LOG.get()), // side of the block
                AGoTMod.id("block/stripped_weirwood_log_top")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.WEIRWOOD_FACE_LOG.get()),
                blockTexture(ModBLocks.WEIRWOOD_LOG.get()), // side of the block
                AGoTMod.id("block/weirwood_face")); // top of the block

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


        //Sycamore
        logBlock((RotatedPillarBlock) ModBLocks.SYCAMORE_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.SYCAMORE_WOOD.get()),
                blockTexture(ModBLocks.SYCAMORE_LOG.get()), // side of the block
                blockTexture(ModBLocks.SYCAMORE_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_SYCAMORE_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), // side of the block
                AGoTMod.id("block/sycamore_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_SYCAMORE_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_SYCAMORE_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_SYCAMORE_LOG.get())); // top of the block

        blockItem(ModBLocks.SYCAMORE_LOG);
        blockItem(ModBLocks.STRIPPED_SYCAMORE_LOG);
        blockItem(ModBLocks.SYCAMORE_WOOD);
        blockItem(ModBLocks.STRIPPED_SYCAMORE_WOOD);

        blockWithItem(ModBLocks.SYCAMORE_PLANKS);

        leavesBlock(ModBLocks.SYCAMORE_LEAVES);
        saplingBlock(ModBLocks.SYCAMORE_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.SYCAMORE_STAIRS.get()), blockTexture(ModBLocks.SYCAMORE_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.SYCAMORE_SLAB.get()),
                blockTexture(ModBLocks.SYCAMORE_PLANKS.get()),
                blockTexture(ModBLocks.SYCAMORE_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.SYCAMORE_BUTTON.get()), blockTexture(ModBLocks.SYCAMORE_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.SYCAMORE_PRESSURE_PLATE.get()), blockTexture(ModBLocks.SYCAMORE_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.SYCAMORE_FENCE.get()), blockTexture(ModBLocks.SYCAMORE_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.SYCAMORE_FENCE_GATE.get()), blockTexture(ModBLocks.SYCAMORE_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.SYCAMORE_WALL.get()), blockTexture(ModBLocks.SYCAMORE_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.SYCAMORE_DOOR.get()),
                modLoc("block/sycamore_door_bottom"),
                modLoc("block/sycamore_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.SYCAMORE_TRAPDOOR.get()),
                modLoc("block/sycamore_trapdoor"),
                true,
                "cutout");

        //Sentinel
        logBlock((RotatedPillarBlock) ModBLocks.SENTINEL_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.SENTINEL_WOOD.get()),
                blockTexture(ModBLocks.SENTINEL_LOG.get()), // side of the block
                blockTexture(ModBLocks.SENTINEL_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_SENTINEL_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_SENTINEL_LOG.get()), // side of the block
                AGoTMod.id("block/sentinel_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_SENTINEL_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_SENTINEL_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_SENTINEL_LOG.get())); // top of the block

        blockItem(ModBLocks.SENTINEL_LOG);
        blockItem(ModBLocks.STRIPPED_SENTINEL_LOG);
        blockItem(ModBLocks.SENTINEL_WOOD);
        blockItem(ModBLocks.STRIPPED_SENTINEL_WOOD);

        blockWithItem(ModBLocks.SENTINEL_PLANKS);

        leavesBlock(ModBLocks.SENTINEL_LEAVES);
        saplingBlock(ModBLocks.SENTINEL_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.SENTINEL_STAIRS.get()), blockTexture(ModBLocks.SENTINEL_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.SENTINEL_SLAB.get()),
                blockTexture(ModBLocks.SENTINEL_PLANKS.get()),
                blockTexture(ModBLocks.SENTINEL_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.SENTINEL_BUTTON.get()), blockTexture(ModBLocks.SENTINEL_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.SENTINEL_PRESSURE_PLATE.get()), blockTexture(ModBLocks.SENTINEL_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.SENTINEL_FENCE.get()), blockTexture(ModBLocks.SENTINEL_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.SENTINEL_FENCE_GATE.get()), blockTexture(ModBLocks.SENTINEL_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.SENTINEL_WALL.get()), blockTexture(ModBLocks.SENTINEL_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.SENTINEL_DOOR.get()),
                modLoc("block/sentinel_door_bottom"),
                modLoc("block/sentinel_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.SENTINEL_TRAPDOOR.get()),
                modLoc("block/sentinel_trapdoor"),
                true,
                "cutout");

        //Pine
        logBlock((RotatedPillarBlock) ModBLocks.PINE_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.PINE_WOOD.get()),
                blockTexture(ModBLocks.PINE_LOG.get()), // side of the block
                blockTexture(ModBLocks.PINE_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_PINE_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_PINE_LOG.get()), // side of the block
                AGoTMod.id("block/pine_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_PINE_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_PINE_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_PINE_LOG.get())); // top of the block

        blockItem(ModBLocks.PINE_LOG);
        blockItem(ModBLocks.STRIPPED_PINE_LOG);
        blockItem(ModBLocks.PINE_WOOD);
        blockItem(ModBLocks.STRIPPED_PINE_WOOD);

        blockWithItem(ModBLocks.PINE_PLANKS);

        leavesBlock(ModBLocks.PINE_LEAVES);
        saplingBlock(ModBLocks.PINE_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.PINE_STAIRS.get()), blockTexture(ModBLocks.PINE_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.PINE_SLAB.get()),
                blockTexture(ModBLocks.PINE_PLANKS.get()),
                blockTexture(ModBLocks.PINE_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.PINE_BUTTON.get()), blockTexture(ModBLocks.PINE_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.PINE_PRESSURE_PLATE.get()), blockTexture(ModBLocks.PINE_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.PINE_FENCE.get()), blockTexture(ModBLocks.PINE_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.PINE_FENCE_GATE.get()), blockTexture(ModBLocks.PINE_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.PINE_WALL.get()), blockTexture(ModBLocks.PINE_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.PINE_DOOR.get()),
                modLoc("block/pine_door_bottom"),
                modLoc("block/pine_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.PINE_TRAPDOOR.get()),
                modLoc("block/pine_trapdoor"),
                true,
                "cutout");

        //Ironwood
        logBlock((RotatedPillarBlock) ModBLocks.IRONWOOD_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.IRONWOOD_WOOD.get()),
                blockTexture(ModBLocks.IRONWOOD_LOG.get()), // side of the block
                blockTexture(ModBLocks.IRONWOOD_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_IRONWOOD_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_IRONWOOD_LOG.get()), // side of the block
                AGoTMod.id("block/ironwood_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_IRONWOOD_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_IRONWOOD_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_IRONWOOD_LOG.get())); // top of the block

        blockItem(ModBLocks.IRONWOOD_LOG);
        blockItem(ModBLocks.STRIPPED_IRONWOOD_LOG);
        blockItem(ModBLocks.IRONWOOD_WOOD);
        blockItem(ModBLocks.STRIPPED_IRONWOOD_WOOD);

        blockWithItem(ModBLocks.IRONWOOD_PLANKS);

        leavesBlock(ModBLocks.IRONWOOD_LEAVES);
        saplingBlock(ModBLocks.IRONWOOD_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.IRONWOOD_STAIRS.get()), blockTexture(ModBLocks.IRONWOOD_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.IRONWOOD_SLAB.get()),
                blockTexture(ModBLocks.IRONWOOD_PLANKS.get()),
                blockTexture(ModBLocks.IRONWOOD_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.IRONWOOD_BUTTON.get()), blockTexture(ModBLocks.IRONWOOD_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.IRONWOOD_PRESSURE_PLATE.get()), blockTexture(ModBLocks.IRONWOOD_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.IRONWOOD_FENCE.get()), blockTexture(ModBLocks.IRONWOOD_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.IRONWOOD_FENCE_GATE.get()), blockTexture(ModBLocks.IRONWOOD_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.IRONWOOD_WALL.get()), blockTexture(ModBLocks.IRONWOOD_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.IRONWOOD_DOOR.get()),
                modLoc("block/ironwood_door_bottom"),
                modLoc("block/ironwood_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.IRONWOOD_TRAPDOOR.get()),
                modLoc("block/ironwood_trapdoor"),
                true,
                "cutout");

        //Hawthorn
        logBlock((RotatedPillarBlock) ModBLocks.HAWTHORN_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.HAWTHORN_WOOD.get()),
                blockTexture(ModBLocks.HAWTHORN_LOG.get()), // side of the block
                blockTexture(ModBLocks.HAWTHORN_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_HAWTHORN_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_HAWTHORN_LOG.get()), // side of the block
                AGoTMod.id("block/hawthorn_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_HAWTHORN_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_HAWTHORN_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_HAWTHORN_LOG.get())); // top of the block

        blockItem(ModBLocks.HAWTHORN_LOG);
        blockItem(ModBLocks.STRIPPED_HAWTHORN_LOG);
        blockItem(ModBLocks.HAWTHORN_WOOD);
        blockItem(ModBLocks.STRIPPED_HAWTHORN_WOOD);

        blockWithItem(ModBLocks.HAWTHORN_PLANKS);

        leavesBlock(ModBLocks.HAWTHORN_LEAVES);
        saplingBlock(ModBLocks.HAWTHORN_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.HAWTHORN_STAIRS.get()), blockTexture(ModBLocks.HAWTHORN_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.HAWTHORN_SLAB.get()),
                blockTexture(ModBLocks.HAWTHORN_PLANKS.get()),
                blockTexture(ModBLocks.HAWTHORN_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.HAWTHORN_BUTTON.get()), blockTexture(ModBLocks.HAWTHORN_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.HAWTHORN_PRESSURE_PLATE.get()), blockTexture(ModBLocks.HAWTHORN_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.HAWTHORN_FENCE.get()), blockTexture(ModBLocks.HAWTHORN_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.HAWTHORN_FENCE_GATE.get()), blockTexture(ModBLocks.HAWTHORN_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.HAWTHORN_WALL.get()), blockTexture(ModBLocks.HAWTHORN_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.HAWTHORN_DOOR.get()),
                modLoc("block/hawthorn_door_bottom"),
                modLoc("block/hawthorn_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.HAWTHORN_TRAPDOOR.get()),
                modLoc("block/hawthorn_trapdoor"),
                true,
                "cutout");


        //Chestnut
        logBlock((RotatedPillarBlock) ModBLocks.CHESTNUT_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.CHESTNUT_WOOD.get()),
                blockTexture(ModBLocks.CHESTNUT_LOG.get()), // side of the block
                blockTexture(ModBLocks.CHESTNUT_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_CHESTNUT_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_CHESTNUT_LOG.get()), // side of the block
                AGoTMod.id("block/chestnut_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_CHESTNUT_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_CHESTNUT_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_CHESTNUT_LOG.get())); // top of the block

        blockItem(ModBLocks.CHESTNUT_LOG);
        blockItem(ModBLocks.STRIPPED_CHESTNUT_LOG);
        blockItem(ModBLocks.CHESTNUT_WOOD);
        blockItem(ModBLocks.STRIPPED_CHESTNUT_WOOD);

        blockWithItem(ModBLocks.CHESTNUT_PLANKS);

        leavesBlock(ModBLocks.CHESTNUT_LEAVES);
        saplingBlock(ModBLocks.CHESTNUT_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.CHESTNUT_STAIRS.get()), blockTexture(ModBLocks.CHESTNUT_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.CHESTNUT_SLAB.get()),
                blockTexture(ModBLocks.CHESTNUT_PLANKS.get()),
                blockTexture(ModBLocks.CHESTNUT_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.CHESTNUT_BUTTON.get()), blockTexture(ModBLocks.CHESTNUT_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.CHESTNUT_PRESSURE_PLATE.get()), blockTexture(ModBLocks.CHESTNUT_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.CHESTNUT_FENCE.get()), blockTexture(ModBLocks.CHESTNUT_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.CHESTNUT_FENCE_GATE.get()), blockTexture(ModBLocks.CHESTNUT_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.CHESTNUT_WALL.get()), blockTexture(ModBLocks.CHESTNUT_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.CHESTNUT_DOOR.get()),
                modLoc("block/chestnut_door_bottom"),
                modLoc("block/chestnut_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.CHESTNUT_TRAPDOOR.get()),
                modLoc("block/chestnut_trapdoor"),
                true,
                "cutout");


        //Cedar
        logBlock((RotatedPillarBlock) ModBLocks.CEDAR_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.CEDAR_WOOD.get()),
                blockTexture(ModBLocks.CEDAR_LOG.get()), // side of the block
                blockTexture(ModBLocks.CEDAR_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_CEDAR_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_CEDAR_LOG.get()), // side of the block
                AGoTMod.id("block/cedar_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_CEDAR_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_CEDAR_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_CEDAR_LOG.get())); // top of the block

        blockItem(ModBLocks.CEDAR_LOG);
        blockItem(ModBLocks.STRIPPED_CEDAR_LOG);
        blockItem(ModBLocks.CEDAR_WOOD);
        blockItem(ModBLocks.STRIPPED_CEDAR_WOOD);

        blockWithItem(ModBLocks.CEDAR_PLANKS);

        leavesBlock(ModBLocks.CEDAR_LEAVES);
        saplingBlock(ModBLocks.CEDAR_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.CEDAR_STAIRS.get()), blockTexture(ModBLocks.CEDAR_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.CEDAR_SLAB.get()),
                blockTexture(ModBLocks.CEDAR_PLANKS.get()),
                blockTexture(ModBLocks.CEDAR_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.CEDAR_BUTTON.get()), blockTexture(ModBLocks.CEDAR_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.CEDAR_PRESSURE_PLATE.get()), blockTexture(ModBLocks.CEDAR_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.CEDAR_FENCE.get()), blockTexture(ModBLocks.CEDAR_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.CEDAR_FENCE_GATE.get()), blockTexture(ModBLocks.CEDAR_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.CEDAR_WALL.get()), blockTexture(ModBLocks.CEDAR_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.CEDAR_DOOR.get()),
                modLoc("block/cedar_door_bottom"),
                modLoc("block/cedar_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.CEDAR_TRAPDOOR.get()),
                modLoc("block/cedar_trapdoor"),
                true,
                "cutout");


        //Beech
        logBlock((RotatedPillarBlock) ModBLocks.BEECH_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.BEECH_WOOD.get()),
                blockTexture(ModBLocks.BEECH_LOG.get()), // side of the block
                blockTexture(ModBLocks.BEECH_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_BEECH_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_BEECH_LOG.get()), // side of the block
                AGoTMod.id("block/beech_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_BEECH_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_BEECH_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_BEECH_LOG.get())); // top of the block

        blockItem(ModBLocks.BEECH_LOG);
        blockItem(ModBLocks.STRIPPED_BEECH_LOG);
        blockItem(ModBLocks.BEECH_WOOD);
        blockItem(ModBLocks.STRIPPED_BEECH_WOOD);

        blockWithItem(ModBLocks.BEECH_PLANKS);

        leavesBlock(ModBLocks.BEECH_LEAVES);
        saplingBlock(ModBLocks.BEECH_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.BEECH_STAIRS.get()), blockTexture(ModBLocks.BEECH_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.BEECH_SLAB.get()),
                blockTexture(ModBLocks.BEECH_PLANKS.get()),
                blockTexture(ModBLocks.BEECH_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.BEECH_BUTTON.get()), blockTexture(ModBLocks.BEECH_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.BEECH_PRESSURE_PLATE.get()), blockTexture(ModBLocks.BEECH_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.BEECH_FENCE.get()), blockTexture(ModBLocks.BEECH_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.BEECH_FENCE_GATE.get()), blockTexture(ModBLocks.BEECH_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.BEECH_WALL.get()), blockTexture(ModBLocks.BEECH_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.BEECH_DOOR.get()),
                modLoc("block/beech_door_bottom"),
                modLoc("block/beech_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.BEECH_TRAPDOOR.get()),
                modLoc("block/beech_trapdoor"),
                true,
                "cutout");


        //Ash
        logBlock((RotatedPillarBlock) ModBLocks.ASH_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.ASH_WOOD.get()),
                blockTexture(ModBLocks.ASH_LOG.get()), // side of the block
                blockTexture(ModBLocks.ASH_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ASH_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_ASH_LOG.get()), // side of the block
                AGoTMod.id("block/ash_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ASH_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_ASH_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_ASH_LOG.get())); // top of the block

        blockItem(ModBLocks.ASH_LOG);
        blockItem(ModBLocks.STRIPPED_ASH_LOG);
        blockItem(ModBLocks.ASH_WOOD);
        blockItem(ModBLocks.STRIPPED_ASH_WOOD);

        blockWithItem(ModBLocks.ASH_PLANKS);

        leavesBlock(ModBLocks.ASH_LEAVES);
        saplingBlock(ModBLocks.ASH_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.ASH_STAIRS.get()), blockTexture(ModBLocks.ASH_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.ASH_SLAB.get()),
                blockTexture(ModBLocks.ASH_PLANKS.get()),
                blockTexture(ModBLocks.ASH_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.ASH_BUTTON.get()), blockTexture(ModBLocks.ASH_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.ASH_PRESSURE_PLATE.get()), blockTexture(ModBLocks.ASH_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.ASH_FENCE.get()), blockTexture(ModBLocks.ASH_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.ASH_FENCE_GATE.get()), blockTexture(ModBLocks.ASH_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.ASH_WALL.get()), blockTexture(ModBLocks.ASH_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.ASH_DOOR.get()),
                modLoc("block/ash_door_bottom"),
                modLoc("block/ash_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.ASH_TRAPDOOR.get()),
                modLoc("block/ash_trapdoor"),
                true,
                "cutout");


        //Blackbark
        logBlock((RotatedPillarBlock) ModBLocks.BLACKBARK_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.BLACKBARK_WOOD.get()),
                blockTexture(ModBLocks.BLACKBARK_LOG.get()), // side of the block
                blockTexture(ModBLocks.BLACKBARK_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_BLACKBARK_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_BLACKBARK_LOG.get()), // side of the block
                AGoTMod.id("block/blackbark_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_BLACKBARK_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_BLACKBARK_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_BLACKBARK_LOG.get())); // top of the block

        blockItem(ModBLocks.BLACKBARK_LOG);
        blockItem(ModBLocks.STRIPPED_BLACKBARK_LOG);
        blockItem(ModBLocks.BLACKBARK_WOOD);
        blockItem(ModBLocks.STRIPPED_BLACKBARK_WOOD);

        blockWithItem(ModBLocks.BLACKBARK_PLANKS);

        leavesBlock(ModBLocks.BLACKBARK_LEAVES);
        saplingBlock(ModBLocks.BLACKBARK_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.BLACKBARK_STAIRS.get()), blockTexture(ModBLocks.BLACKBARK_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.BLACKBARK_SLAB.get()),
                blockTexture(ModBLocks.BLACKBARK_PLANKS.get()),
                blockTexture(ModBLocks.BLACKBARK_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.BLACKBARK_BUTTON.get()), blockTexture(ModBLocks.BLACKBARK_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.BLACKBARK_PRESSURE_PLATE.get()), blockTexture(ModBLocks.BLACKBARK_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.BLACKBARK_FENCE.get()), blockTexture(ModBLocks.BLACKBARK_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.BLACKBARK_FENCE_GATE.get()), blockTexture(ModBLocks.BLACKBARK_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.BLACKBARK_WALL.get()), blockTexture(ModBLocks.BLACKBARK_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.BLACKBARK_DOOR.get()),
                modLoc("block/blackbark_door_bottom"),
                modLoc("block/blackbark_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.BLACKBARK_TRAPDOOR.get()),
                modLoc("block/blackbark_trapdoor"),
                true,
                "cutout");


        //Aspen
        logBlock((RotatedPillarBlock) ModBLocks.ASPEN_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.ASPEN_WOOD.get()),
                blockTexture(ModBLocks.ASPEN_LOG.get()), // side of the block
                blockTexture(ModBLocks.ASPEN_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ASPEN_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_ASPEN_LOG.get()), // side of the block
                AGoTMod.id("block/aspen_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ASPEN_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_ASPEN_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_ASPEN_LOG.get())); // top of the block

        blockItem(ModBLocks.ASPEN_LOG);
        blockItem(ModBLocks.STRIPPED_ASPEN_LOG);
        blockItem(ModBLocks.ASPEN_WOOD);
        blockItem(ModBLocks.STRIPPED_ASPEN_WOOD);

        blockWithItem(ModBLocks.ASPEN_PLANKS);

        leavesBlock(ModBLocks.ASPEN_LEAVES);
        saplingBlock(ModBLocks.ASPEN_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.ASPEN_STAIRS.get()), blockTexture(ModBLocks.ASPEN_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.ASPEN_SLAB.get()),
                blockTexture(ModBLocks.ASPEN_PLANKS.get()),
                blockTexture(ModBLocks.ASPEN_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.ASPEN_BUTTON.get()), blockTexture(ModBLocks.ASPEN_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.ASPEN_PRESSURE_PLATE.get()), blockTexture(ModBLocks.ASPEN_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.ASPEN_FENCE.get()), blockTexture(ModBLocks.ASPEN_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.ASPEN_FENCE_GATE.get()), blockTexture(ModBLocks.ASPEN_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.ASPEN_WALL.get()), blockTexture(ModBLocks.ASPEN_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.ASPEN_DOOR.get()),
                modLoc("block/aspen_door_bottom"),
                modLoc("block/aspen_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.ASPEN_TRAPDOOR.get()),
                modLoc("block/aspen_trapdoor"),
                true,
                "cutout");


        //Alder
        logBlock((RotatedPillarBlock) ModBLocks.ALDER_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBLocks.ALDER_WOOD.get()),
                blockTexture(ModBLocks.ALDER_LOG.get()), // side of the block
                blockTexture(ModBLocks.ALDER_LOG.get())); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ALDER_LOG.get()),
                blockTexture(ModBLocks.STRIPPED_ALDER_LOG.get()), // side of the block
                AGoTMod.id("block/alder_log_top_stripped")); // top of the block

        axisBlock(((RotatedPillarBlock) ModBLocks.STRIPPED_ALDER_WOOD.get()),
                blockTexture(ModBLocks.STRIPPED_ALDER_LOG.get()), // side of the block
                blockTexture(ModBLocks.STRIPPED_ALDER_LOG.get())); // top of the block

        blockItem(ModBLocks.ALDER_LOG);
        blockItem(ModBLocks.STRIPPED_ALDER_LOG);
        blockItem(ModBLocks.ALDER_WOOD);
        blockItem(ModBLocks.STRIPPED_ALDER_WOOD);

        blockWithItem(ModBLocks.ALDER_PLANKS);

        leavesBlock(ModBLocks.ALDER_LEAVES);
        saplingBlock(ModBLocks.ALDER_SAPLING);
        stairsBlock(((StairBlock) ModBLocks.ALDER_STAIRS.get()), blockTexture(ModBLocks.ALDER_PLANKS.get()));

        slabBlock(((SlabBlock) ModBLocks.ALDER_SLAB.get()),
                blockTexture(ModBLocks.ALDER_PLANKS.get()),
                blockTexture(ModBLocks.ALDER_PLANKS.get()));

        buttonBlock(((ButtonBlock) ModBLocks.ALDER_BUTTON.get()), blockTexture(ModBLocks.ALDER_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBLocks.ALDER_PRESSURE_PLATE.get()), blockTexture(ModBLocks.ALDER_PLANKS.get()));

        fenceBlock(((FenceBlock) ModBLocks.ALDER_FENCE.get()), blockTexture(ModBLocks.ALDER_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBLocks.ALDER_FENCE_GATE.get()), blockTexture(ModBLocks.ALDER_PLANKS.get()));
        wallBlock(((WallBlock) ModBLocks.ALDER_WALL.get()), blockTexture(ModBLocks.ALDER_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBLocks.ALDER_DOOR.get()),
                modLoc("block/alder_door_bottom"),
                modLoc("block/alder_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBLocks.ALDER_TRAPDOOR.get()),
                modLoc("block/alder_trapdoor"),
                true,
                "cutout");


        // ---------------------------(STONE BLOCKS)--------------------------- //



        blockWithItem(ModBLocks.SSTONE_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_1_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_1_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_1_STAIRS.get()), blockTexture(ModBLocks.SSTONE_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_1_WALL.get()), blockTexture(ModBLocks.SSTONE_1_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_2_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_2_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_2_STAIRS.get()), blockTexture(ModBLocks.SSTONE_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_2_WALL.get()), blockTexture(ModBLocks.SSTONE_2_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_3_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_3_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_3_STAIRS.get()), blockTexture(ModBLocks.SSTONE_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_3_WALL.get()), blockTexture(ModBLocks.SSTONE_3_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_4_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_4_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_4_STAIRS.get()), blockTexture(ModBLocks.SSTONE_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_4_WALL.get()), blockTexture(ModBLocks.SSTONE_4_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_5_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_5_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_5_STAIRS.get()), blockTexture(ModBLocks.SSTONE_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_5_WALL.get()), blockTexture(ModBLocks.SSTONE_5_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_6_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_6_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_6_STAIRS.get()), blockTexture(ModBLocks.SSTONE_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_6_WALL.get()), blockTexture(ModBLocks.SSTONE_6_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_7_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_7_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_7_STAIRS.get()), blockTexture(ModBLocks.SSTONE_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_7_WALL.get()), blockTexture(ModBLocks.SSTONE_7_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_8_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_8_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_8_STAIRS.get()), blockTexture(ModBLocks.SSTONE_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_8_WALL.get()), blockTexture(ModBLocks.SSTONE_8_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_9_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_9_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_9_STAIRS.get()), blockTexture(ModBLocks.SSTONE_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_9_WALL.get()), blockTexture(ModBLocks.SSTONE_9_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_10_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_10_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_10_STAIRS.get()), blockTexture(ModBLocks.SSTONE_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_10_WALL.get()), blockTexture(ModBLocks.SSTONE_10_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_11_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_11_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_11_STAIRS.get()), blockTexture(ModBLocks.SSTONE_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_11_WALL.get()), blockTexture(ModBLocks.SSTONE_11_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_12_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_12_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_12_STAIRS.get()), blockTexture(ModBLocks.SSTONE_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_12_WALL.get()), blockTexture(ModBLocks.SSTONE_12_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_13_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_13_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_13_STAIRS.get()), blockTexture(ModBLocks.SSTONE_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_13_WALL.get()), blockTexture(ModBLocks.SSTONE_13_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_14_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_14_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_14_STAIRS.get()), blockTexture(ModBLocks.SSTONE_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_14_WALL.get()), blockTexture(ModBLocks.SSTONE_14_BLOCK.get()));
        // blockWithItem(ModBLocks.SSTONE_15_BLOCK);
// slabBlock(((SlabBlock) ModBLocks.SSTONE_15_SLAB.get()),
//         blockTexture(ModBLocks.SSTONE_15_BLOCK.get()),
//         blockTexture(ModBLocks.SSTONE_15_BLOCK.get()));
// stairsBlock(((StairBlock) ModBLocks.SSTONE_15_STAIRS.get()), blockTexture(ModBLocks.SSTONE_15_BLOCK.get()));
// wallBlock(((WallBlock) ModBLocks.SSTONE_15_WALL.get()), blockTexture(ModBLocks.SSTONE_15_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_16_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_16_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_16_STAIRS.get()), blockTexture(ModBLocks.SSTONE_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_16_WALL.get()), blockTexture(ModBLocks.SSTONE_16_BLOCK.get()));

        blockWithItem(ModBLocks.SSTONE_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_17_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_17_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_17_STAIRS.get()), blockTexture(ModBLocks.SSTONE_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_17_WALL.get()), blockTexture(ModBLocks.SSTONE_17_BLOCK.get()));

        blockWithItem(ModBLocks.SSTONE_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_18_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_18_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_18_STAIRS.get()), blockTexture(ModBLocks.SSTONE_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_18_WALL.get()), blockTexture(ModBLocks.SSTONE_18_BLOCK.get()));
        blockWithItem(ModBLocks.SSTONE_19_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_19_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_19_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_19_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_19_STAIRS.get()), blockTexture(ModBLocks.SSTONE_19_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_19_WALL.get()), blockTexture(ModBLocks.SSTONE_19_BLOCK.get()));

        blockWithItem(ModBLocks.SSTONE_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_20_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_20_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_20_STAIRS.get()), blockTexture(ModBLocks.SSTONE_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_20_WALL.get()), blockTexture(ModBLocks.SSTONE_20_BLOCK.get()));

         blockWithItem(ModBLocks.SSTONE_21_BLOCK);
         slabBlock(((SlabBlock) ModBLocks.SSTONE_21_SLAB.get()),
                 blockTexture(ModBLocks.SSTONE_21_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_21_STAIRS.get()), blockTexture(ModBLocks.SSTONE_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_21_WALL.get()), blockTexture(ModBLocks.SSTONE_21_BLOCK.get()));

        blockWithItem(ModBLocks.SSTONE_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.SSTONE_22_SLAB.get()),
                blockTexture(ModBLocks.SSTONE_22_BLOCK.get()),
                blockTexture(ModBLocks.SSTONE_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.SSTONE_22_STAIRS.get()), blockTexture(ModBLocks.SSTONE_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.SSTONE_22_WALL.get()), blockTexture(ModBLocks.SSTONE_22_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_1_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_1_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_1_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_1_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_1_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_2_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_2_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_2_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_2_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_2_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_3_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_3_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_3_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_3_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_3_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_4_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_4_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_4_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_4_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_4_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_5_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_5_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_5_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_5_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_5_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_6_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_6_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_6_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_6_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_6_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_7_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_7_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_7_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_7_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_7_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_8_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_8_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_8_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_8_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_8_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_9_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_9_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_9_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_9_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_9_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_10_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_10_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_10_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_10_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_10_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_11_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_11_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_11_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_11_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_11_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_12_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_12_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_12_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_12_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_12_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_13_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_13_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_13_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_13_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_13_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_14_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_14_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_14_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_14_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_14_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_19_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_19_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_19_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_19_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_19_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_19_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_19_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_19_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_20_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_20_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_20_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_20_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_20_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_21_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_21_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_21_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_21_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_21_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_21_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_22_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_22_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_22_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_22_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_22_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_23_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_23_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_23_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_23_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_23_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_23_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_23_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_23_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_24_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_24_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_24_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_24_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_24_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_25_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_25_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_25_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_25_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_25_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_26_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_26_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_26_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_26_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_26_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_27_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_27_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_27_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_27_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_27_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_28_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_28_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_28_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_28_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_28_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_29_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_29_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_29_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_29_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_29_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_30_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_30_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_30_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_30_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_30_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_31_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_31_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_31_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_31_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_31_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_32_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_32_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_32_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_32_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_32_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_33_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_33_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_33_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_33_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_33_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_34_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_34_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_34_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_34_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_34_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_35_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_35_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_35_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_35_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_35_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_36_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_36_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_36_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_36_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_36_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_37_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_37_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_37_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_37_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_37_BLOCK.get()));

        blockWithItem(ModBLocks.RSANDSTONE_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.RSANDSTONE_38_SLAB.get()),
                blockTexture(ModBLocks.RSANDSTONE_38_BLOCK.get()),
                blockTexture(ModBLocks.RSANDSTONE_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.RSANDSTONE_38_STAIRS.get()), blockTexture(ModBLocks.RSANDSTONE_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.RSANDSTONE_38_WALL.get()), blockTexture(ModBLocks.RSANDSTONE_38_BLOCK.get()));

        //Redkeep
        blockWithItem(ModBLocks.REDKEEP_STONE_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_STONE_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_STONE_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_STONE_WALL.get()), blockTexture(ModBLocks.REDKEEP_STONE_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_1_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_1_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_1_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_1_WALL.get()), blockTexture(ModBLocks.REDKEEP_1_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_2_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_2_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_2_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_2_WALL.get()), blockTexture(ModBLocks.REDKEEP_2_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_3_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_3_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_3_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_3_WALL.get()), blockTexture(ModBLocks.REDKEEP_3_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_4_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_4_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_4_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_4_WALL.get()), blockTexture(ModBLocks.REDKEEP_4_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_5_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_5_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_5_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_5_WALL.get()), blockTexture(ModBLocks.REDKEEP_5_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_6_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_6_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_6_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_6_WALL.get()), blockTexture(ModBLocks.REDKEEP_6_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_7_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_7_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_7_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_7_WALL.get()), blockTexture(ModBLocks.REDKEEP_7_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_8_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_8_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_8_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_8_WALL.get()), blockTexture(ModBLocks.REDKEEP_8_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_9_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_9_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_9_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_9_WALL.get()), blockTexture(ModBLocks.REDKEEP_9_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_10_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_10_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_10_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_10_WALL.get()), blockTexture(ModBLocks.REDKEEP_10_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_11_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_11_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_11_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_11_WALL.get()), blockTexture(ModBLocks.REDKEEP_11_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_12_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_12_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_12_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_12_WALL.get()), blockTexture(ModBLocks.REDKEEP_12_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_13_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_13_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_13_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_13_WALL.get()), blockTexture(ModBLocks.REDKEEP_13_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_14_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_14_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_14_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_14_WALL.get()), blockTexture(ModBLocks.REDKEEP_14_BLOCK.get()));
        // blockWithItem(ModBLocks.REDKEEP_15_BLOCK);
        // slabBlock(((SlabBlock) ModBLocks.REDKEEP_15_SLAB.get()),
        //         blockTexture(ModBLocks.REDKEEP_15_BLOCK.get()),
        //         blockTexture(ModBLocks.REDKEEP_15_BLOCK.get()));
        // stairsBlock(((StairBlock) ModBLocks.REDKEEP_15_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_15_BLOCK.get()));
        // wallBlock(((WallBlock) ModBLocks.REDKEEP_15_WALL.get()), blockTexture(ModBLocks.REDKEEP_15_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_16_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_16_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_16_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_16_WALL.get()), blockTexture(ModBLocks.REDKEEP_16_BLOCK.get()));
        blockWithItem(ModBLocks.REDKEEP_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_17_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_17_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_17_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_17_WALL.get()), blockTexture(ModBLocks.REDKEEP_17_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_18_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_18_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_18_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_18_WALL.get()), blockTexture(ModBLocks.REDKEEP_18_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_19_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_19_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_19_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_19_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_19_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_19_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_19_WALL.get()), blockTexture(ModBLocks.REDKEEP_19_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_20_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_20_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_20_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_20_WALL.get()), blockTexture(ModBLocks.REDKEEP_20_BLOCK.get()));

         blockWithItem(ModBLocks.REDKEEP_21_BLOCK);
         slabBlock(((SlabBlock) ModBLocks.REDKEEP_21_SLAB.get()),
                 blockTexture(ModBLocks.REDKEEP_21_BLOCK.get()),
                 blockTexture(ModBLocks.REDKEEP_21_BLOCK.get()));
         stairsBlock(((StairBlock) ModBLocks.REDKEEP_21_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_21_BLOCK.get()));
         wallBlock(((WallBlock) ModBLocks.REDKEEP_21_WALL.get()), blockTexture(ModBLocks.REDKEEP_21_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_22_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_22_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_22_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_22_WALL.get()), blockTexture(ModBLocks.REDKEEP_22_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_23_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_23_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_23_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_23_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_23_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_23_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_23_WALL.get()), blockTexture(ModBLocks.REDKEEP_23_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_24_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_24_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_24_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_24_WALL.get()), blockTexture(ModBLocks.REDKEEP_24_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_25_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_25_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_25_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_25_WALL.get()), blockTexture(ModBLocks.REDKEEP_25_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_26_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_26_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_26_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_26_WALL.get()), blockTexture(ModBLocks.REDKEEP_26_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_27_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_27_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_27_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_27_WALL.get()), blockTexture(ModBLocks.REDKEEP_27_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_28_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_28_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_28_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_28_WALL.get()), blockTexture(ModBLocks.REDKEEP_28_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_29_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_29_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_29_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_29_WALL.get()), blockTexture(ModBLocks.REDKEEP_29_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_30_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_30_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_30_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_30_WALL.get()), blockTexture(ModBLocks.REDKEEP_30_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_31_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_31_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_31_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_31_WALL.get()), blockTexture(ModBLocks.REDKEEP_31_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_32_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_32_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_32_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_32_WALL.get()), blockTexture(ModBLocks.REDKEEP_32_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_33_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_33_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_33_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_33_WALL.get()), blockTexture(ModBLocks.REDKEEP_33_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_34_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_34_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_34_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_34_WALL.get()), blockTexture(ModBLocks.REDKEEP_34_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_35_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_35_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_35_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_35_WALL.get()), blockTexture(ModBLocks.REDKEEP_35_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_36_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_36_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_36_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_36_WALL.get()), blockTexture(ModBLocks.REDKEEP_36_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_37_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_37_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_37_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_37_WALL.get()), blockTexture(ModBLocks.REDKEEP_37_BLOCK.get()));

        blockWithItem(ModBLocks.REDKEEP_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.REDKEEP_38_SLAB.get()),
                blockTexture(ModBLocks.REDKEEP_38_BLOCK.get()),
                blockTexture(ModBLocks.REDKEEP_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.REDKEEP_38_STAIRS.get()), blockTexture(ModBLocks.REDKEEP_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.REDKEEP_38_WALL.get()), blockTexture(ModBLocks.REDKEEP_38_BLOCK.get()));

// stone variant blocks
        blockWithItem(ModBLocks.STONE_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_1_SLAB.get()),
                blockTexture(ModBLocks.STONE_1_BLOCK.get()),
                blockTexture(ModBLocks.STONE_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_1_STAIRS.get()), blockTexture(ModBLocks.STONE_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_1_WALL.get()), blockTexture(ModBLocks.STONE_1_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_2_SLAB.get()),
                blockTexture(ModBLocks.STONE_2_BLOCK.get()),
                blockTexture(ModBLocks.STONE_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_2_STAIRS.get()), blockTexture(ModBLocks.STONE_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_2_WALL.get()), blockTexture(ModBLocks.STONE_2_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_3_SLAB.get()),
                blockTexture(ModBLocks.STONE_3_BLOCK.get()),
                blockTexture(ModBLocks.STONE_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_3_STAIRS.get()), blockTexture(ModBLocks.STONE_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_3_WALL.get()), blockTexture(ModBLocks.STONE_3_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_4_SLAB.get()),
                blockTexture(ModBLocks.STONE_4_BLOCK.get()),
                blockTexture(ModBLocks.STONE_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_4_STAIRS.get()), blockTexture(ModBLocks.STONE_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_4_WALL.get()), blockTexture(ModBLocks.STONE_4_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_6_SLAB.get()),
                blockTexture(ModBLocks.STONE_6_BLOCK.get()),
                blockTexture(ModBLocks.STONE_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_6_STAIRS.get()), blockTexture(ModBLocks.STONE_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_6_WALL.get()), blockTexture(ModBLocks.STONE_6_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_7_SLAB.get()),
                blockTexture(ModBLocks.STONE_7_BLOCK.get()),
                blockTexture(ModBLocks.STONE_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_7_STAIRS.get()), blockTexture(ModBLocks.STONE_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_7_WALL.get()), blockTexture(ModBLocks.STONE_7_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_8_SLAB.get()),
                blockTexture(ModBLocks.STONE_8_BLOCK.get()),
                blockTexture(ModBLocks.STONE_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_8_STAIRS.get()), blockTexture(ModBLocks.STONE_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_8_WALL.get()), blockTexture(ModBLocks.STONE_8_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_9_SLAB.get()),
                blockTexture(ModBLocks.STONE_9_BLOCK.get()),
                blockTexture(ModBLocks.STONE_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_9_STAIRS.get()), blockTexture(ModBLocks.STONE_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_9_WALL.get()), blockTexture(ModBLocks.STONE_9_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_10_SLAB.get()),
                blockTexture(ModBLocks.STONE_10_BLOCK.get()),
                blockTexture(ModBLocks.STONE_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_10_STAIRS.get()), blockTexture(ModBLocks.STONE_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_10_WALL.get()), blockTexture(ModBLocks.STONE_10_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_11_SLAB.get()),
                blockTexture(ModBLocks.STONE_11_BLOCK.get()),
                blockTexture(ModBLocks.STONE_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_11_STAIRS.get()), blockTexture(ModBLocks.STONE_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_11_WALL.get()), blockTexture(ModBLocks.STONE_11_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_12_SLAB.get()),
                blockTexture(ModBLocks.STONE_12_BLOCK.get()),
                blockTexture(ModBLocks.STONE_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_12_STAIRS.get()), blockTexture(ModBLocks.STONE_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_12_WALL.get()), blockTexture(ModBLocks.STONE_12_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_13_SLAB.get()),
                blockTexture(ModBLocks.STONE_13_BLOCK.get()),
                blockTexture(ModBLocks.STONE_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_13_STAIRS.get()), blockTexture(ModBLocks.STONE_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_13_WALL.get()), blockTexture(ModBLocks.STONE_13_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_14_SLAB.get()),
                blockTexture(ModBLocks.STONE_14_BLOCK.get()),
                blockTexture(ModBLocks.STONE_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_14_STAIRS.get()), blockTexture(ModBLocks.STONE_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_14_WALL.get()), blockTexture(ModBLocks.STONE_14_BLOCK.get()));

// Skipping STONE_15_BLOCK as requested

        blockWithItem(ModBLocks.STONE_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_16_SLAB.get()),
                blockTexture(ModBLocks.STONE_16_BLOCK.get()),
                blockTexture(ModBLocks.STONE_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_16_STAIRS.get()), blockTexture(ModBLocks.STONE_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_16_WALL.get()), blockTexture(ModBLocks.STONE_16_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_17_SLAB.get()),
                blockTexture(ModBLocks.STONE_17_BLOCK.get()),
                blockTexture(ModBLocks.STONE_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_17_STAIRS.get()), blockTexture(ModBLocks.STONE_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_17_WALL.get()), blockTexture(ModBLocks.STONE_17_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_18_SLAB.get()),
                blockTexture(ModBLocks.STONE_18_BLOCK.get()),
                blockTexture(ModBLocks.STONE_18_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_18_STAIRS.get()), blockTexture(ModBLocks.STONE_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_18_WALL.get()), blockTexture(ModBLocks.STONE_18_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_20_SLAB.get()),
                blockTexture(ModBLocks.STONE_20_BLOCK.get()),
                blockTexture(ModBLocks.STONE_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_20_STAIRS.get()), blockTexture(ModBLocks.STONE_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_20_WALL.get()), blockTexture(ModBLocks.STONE_20_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_21_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_21_SLAB.get()),
                blockTexture(ModBLocks.STONE_21_BLOCK.get()),
                blockTexture(ModBLocks.STONE_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_21_STAIRS.get()), blockTexture(ModBLocks.STONE_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_21_WALL.get()), blockTexture(ModBLocks.STONE_21_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_22_SLAB.get()),
                blockTexture(ModBLocks.STONE_22_BLOCK.get()),
                blockTexture(ModBLocks.STONE_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_22_STAIRS.get()), blockTexture(ModBLocks.STONE_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_22_WALL.get()), blockTexture(ModBLocks.STONE_22_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_24_SLAB.get()),
                blockTexture(ModBLocks.STONE_24_BLOCK.get()),
                blockTexture(ModBLocks.STONE_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_24_STAIRS.get()), blockTexture(ModBLocks.STONE_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_24_WALL.get()), blockTexture(ModBLocks.STONE_24_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_25_SLAB.get()),
                blockTexture(ModBLocks.STONE_25_BLOCK.get()),
                blockTexture(ModBLocks.STONE_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_25_STAIRS.get()), blockTexture(ModBLocks.STONE_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_25_WALL.get()), blockTexture(ModBLocks.STONE_25_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_26_SLAB.get()),
                blockTexture(ModBLocks.STONE_26_BLOCK.get()),
                blockTexture(ModBLocks.STONE_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_26_STAIRS.get()), blockTexture(ModBLocks.STONE_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_26_WALL.get()), blockTexture(ModBLocks.STONE_26_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_27_SLAB.get()),
                blockTexture(ModBLocks.STONE_27_BLOCK.get()),
                blockTexture(ModBLocks.STONE_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_27_STAIRS.get()), blockTexture(ModBLocks.STONE_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_27_WALL.get()), blockTexture(ModBLocks.STONE_27_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_28_SLAB.get()),
                blockTexture(ModBLocks.STONE_28_BLOCK.get()),
                blockTexture(ModBLocks.STONE_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_28_STAIRS.get()), blockTexture(ModBLocks.STONE_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_28_WALL.get()), blockTexture(ModBLocks.STONE_28_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_29_SLAB.get()),
                blockTexture(ModBLocks.STONE_29_BLOCK.get()),
                blockTexture(ModBLocks.STONE_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_29_STAIRS.get()), blockTexture(ModBLocks.STONE_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_29_WALL.get()), blockTexture(ModBLocks.STONE_29_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_30_SLAB.get()),
                blockTexture(ModBLocks.STONE_30_BLOCK.get()),
                blockTexture(ModBLocks.STONE_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_30_STAIRS.get()), blockTexture(ModBLocks.STONE_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_30_WALL.get()), blockTexture(ModBLocks.STONE_30_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_31_SLAB.get()),
                blockTexture(ModBLocks.STONE_31_BLOCK.get()),
                blockTexture(ModBLocks.STONE_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_31_STAIRS.get()), blockTexture(ModBLocks.STONE_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_31_WALL.get()), blockTexture(ModBLocks.STONE_31_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_32_SLAB.get()),
                blockTexture(ModBLocks.STONE_32_BLOCK.get()),
                blockTexture(ModBLocks.STONE_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_32_STAIRS.get()), blockTexture(ModBLocks.STONE_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_32_WALL.get()), blockTexture(ModBLocks.STONE_32_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_33_SLAB.get()),
                blockTexture(ModBLocks.STONE_33_BLOCK.get()),
                blockTexture(ModBLocks.STONE_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_33_STAIRS.get()), blockTexture(ModBLocks.STONE_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_33_WALL.get()), blockTexture(ModBLocks.STONE_33_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_34_SLAB.get()),
                blockTexture(ModBLocks.STONE_34_BLOCK.get()),
                blockTexture(ModBLocks.STONE_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_34_STAIRS.get()), blockTexture(ModBLocks.STONE_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_34_WALL.get()), blockTexture(ModBLocks.STONE_34_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_35_SLAB.get()),
                blockTexture(ModBLocks.STONE_35_BLOCK.get()),
                blockTexture(ModBLocks.STONE_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_35_STAIRS.get()), blockTexture(ModBLocks.STONE_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_35_WALL.get()), blockTexture(ModBLocks.STONE_35_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_36_SLAB.get()),
                blockTexture(ModBLocks.STONE_36_BLOCK.get()),
                blockTexture(ModBLocks.STONE_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_36_STAIRS.get()), blockTexture(ModBLocks.STONE_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_36_WALL.get()), blockTexture(ModBLocks.STONE_36_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_37_SLAB.get()),
                blockTexture(ModBLocks.STONE_37_BLOCK.get()),
                blockTexture(ModBLocks.STONE_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_37_STAIRS.get()), blockTexture(ModBLocks.STONE_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_37_WALL.get()), blockTexture(ModBLocks.STONE_37_BLOCK.get()));

        blockWithItem(ModBLocks.STONE_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.STONE_38_SLAB.get()),
                blockTexture(ModBLocks.STONE_38_BLOCK.get()),
                blockTexture(ModBLocks.STONE_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.STONE_38_STAIRS.get()), blockTexture(ModBLocks.STONE_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.STONE_38_WALL.get()), blockTexture(ModBLocks.STONE_38_BLOCK.get()));


        blockWithItem(ModBLocks.BASALT_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_2_SLAB.get()),
                blockTexture(ModBLocks.BASALT_2_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_2_STAIRS.get()), blockTexture(ModBLocks.BASALT_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_2_WALL.get()), blockTexture(ModBLocks.BASALT_2_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_3_SLAB.get()),
                blockTexture(ModBLocks.BASALT_3_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_3_STAIRS.get()), blockTexture(ModBLocks.BASALT_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_3_WALL.get()), blockTexture(ModBLocks.BASALT_3_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_4_SLAB.get()),
                blockTexture(ModBLocks.BASALT_4_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_4_STAIRS.get()), blockTexture(ModBLocks.BASALT_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_4_WALL.get()), blockTexture(ModBLocks.BASALT_4_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_5_SLAB.get()),
                blockTexture(ModBLocks.BASALT_5_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_5_STAIRS.get()), blockTexture(ModBLocks.BASALT_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_5_WALL.get()), blockTexture(ModBLocks.BASALT_5_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_6_SLAB.get()),
                blockTexture(ModBLocks.BASALT_6_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_6_STAIRS.get()), blockTexture(ModBLocks.BASALT_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_6_WALL.get()), blockTexture(ModBLocks.BASALT_6_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_7_SLAB.get()),
                blockTexture(ModBLocks.BASALT_7_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_7_STAIRS.get()), blockTexture(ModBLocks.BASALT_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_7_WALL.get()), blockTexture(ModBLocks.BASALT_7_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_8_SLAB.get()),
                blockTexture(ModBLocks.BASALT_8_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_8_STAIRS.get()), blockTexture(ModBLocks.BASALT_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_8_WALL.get()), blockTexture(ModBLocks.BASALT_8_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_9_SLAB.get()),
                blockTexture(ModBLocks.BASALT_9_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_9_STAIRS.get()), blockTexture(ModBLocks.BASALT_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_9_WALL.get()), blockTexture(ModBLocks.BASALT_9_BLOCK.get()));
        blockWithItem(ModBLocks.BASALT_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_10_SLAB.get()),
                blockTexture(ModBLocks.BASALT_10_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_10_STAIRS.get()), blockTexture(ModBLocks.BASALT_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_10_WALL.get()), blockTexture(ModBLocks.BASALT_10_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_11_SLAB.get()),
                blockTexture(ModBLocks.BASALT_11_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_11_STAIRS.get()), blockTexture(ModBLocks.BASALT_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_11_WALL.get()), blockTexture(ModBLocks.BASALT_11_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_12_SLAB.get()),
                blockTexture(ModBLocks.BASALT_12_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_12_STAIRS.get()), blockTexture(ModBLocks.BASALT_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_12_WALL.get()), blockTexture(ModBLocks.BASALT_12_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_13_SLAB.get()),
                blockTexture(ModBLocks.BASALT_13_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_13_STAIRS.get()), blockTexture(ModBLocks.BASALT_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_13_WALL.get()), blockTexture(ModBLocks.BASALT_13_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_14_SLAB.get()),
                blockTexture(ModBLocks.BASALT_14_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_14_STAIRS.get()), blockTexture(ModBLocks.BASALT_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_14_WALL.get()), blockTexture(ModBLocks.BASALT_14_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_16_SLAB.get()),
                blockTexture(ModBLocks.BASALT_16_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_16_STAIRS.get()), blockTexture(ModBLocks.BASALT_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_16_WALL.get()), blockTexture(ModBLocks.BASALT_16_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_17_SLAB.get()),
                blockTexture(ModBLocks.BASALT_17_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_17_STAIRS.get()), blockTexture(ModBLocks.BASALT_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_17_WALL.get()), blockTexture(ModBLocks.BASALT_17_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_18_SLAB.get()),
                blockTexture(ModBLocks.BASALT_18_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_18_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_18_STAIRS.get()), blockTexture(ModBLocks.BASALT_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_18_WALL.get()), blockTexture(ModBLocks.BASALT_18_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_19_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_19_SLAB.get()),
                blockTexture(ModBLocks.BASALT_19_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_19_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_19_STAIRS.get()), blockTexture(ModBLocks.BASALT_19_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_19_WALL.get()), blockTexture(ModBLocks.BASALT_19_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_20_SLAB.get()),
                blockTexture(ModBLocks.BASALT_20_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_20_STAIRS.get()), blockTexture(ModBLocks.BASALT_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_20_WALL.get()), blockTexture(ModBLocks.BASALT_20_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_21_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_21_SLAB.get()),
                blockTexture(ModBLocks.BASALT_21_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_21_STAIRS.get()), blockTexture(ModBLocks.BASALT_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_21_WALL.get()), blockTexture(ModBLocks.BASALT_21_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_22_SLAB.get()),
                blockTexture(ModBLocks.BASALT_22_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_22_STAIRS.get()), blockTexture(ModBLocks.BASALT_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_22_WALL.get()), blockTexture(ModBLocks.BASALT_22_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_23_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_23_SLAB.get()),
                blockTexture(ModBLocks.BASALT_23_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_23_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_23_STAIRS.get()), blockTexture(ModBLocks.BASALT_23_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_23_WALL.get()), blockTexture(ModBLocks.BASALT_23_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_24_SLAB.get()),
                blockTexture(ModBLocks.BASALT_24_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_24_STAIRS.get()), blockTexture(ModBLocks.BASALT_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_24_WALL.get()), blockTexture(ModBLocks.BASALT_24_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_25_SLAB.get()),
                blockTexture(ModBLocks.BASALT_25_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_25_STAIRS.get()), blockTexture(ModBLocks.BASALT_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_25_WALL.get()), blockTexture(ModBLocks.BASALT_25_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_26_SLAB.get()),
                blockTexture(ModBLocks.BASALT_26_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_26_STAIRS.get()), blockTexture(ModBLocks.BASALT_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_26_WALL.get()), blockTexture(ModBLocks.BASALT_26_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_27_SLAB.get()),
                blockTexture(ModBLocks.BASALT_27_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_27_STAIRS.get()), blockTexture(ModBLocks.BASALT_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_27_WALL.get()), blockTexture(ModBLocks.BASALT_27_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_28_SLAB.get()),
                blockTexture(ModBLocks.BASALT_28_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_28_STAIRS.get()), blockTexture(ModBLocks.BASALT_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_28_WALL.get()), blockTexture(ModBLocks.BASALT_28_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_29_SLAB.get()),
                blockTexture(ModBLocks.BASALT_29_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_29_STAIRS.get()), blockTexture(ModBLocks.BASALT_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_29_WALL.get()), blockTexture(ModBLocks.BASALT_29_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_30_SLAB.get()),
                blockTexture(ModBLocks.BASALT_30_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_30_STAIRS.get()), blockTexture(ModBLocks.BASALT_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_30_WALL.get()), blockTexture(ModBLocks.BASALT_30_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_31_SLAB.get()),
                blockTexture(ModBLocks.BASALT_31_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_31_STAIRS.get()), blockTexture(ModBLocks.BASALT_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_31_WALL.get()), blockTexture(ModBLocks.BASALT_31_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_32_SLAB.get()),
                blockTexture(ModBLocks.BASALT_32_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_32_STAIRS.get()), blockTexture(ModBLocks.BASALT_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_32_WALL.get()), blockTexture(ModBLocks.BASALT_32_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_33_SLAB.get()),
                blockTexture(ModBLocks.BASALT_33_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_33_STAIRS.get()), blockTexture(ModBLocks.BASALT_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_33_WALL.get()), blockTexture(ModBLocks.BASALT_33_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_34_SLAB.get()),
                blockTexture(ModBLocks.BASALT_34_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_34_STAIRS.get()), blockTexture(ModBLocks.BASALT_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_34_WALL.get()), blockTexture(ModBLocks.BASALT_34_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_35_SLAB.get()),
                blockTexture(ModBLocks.BASALT_35_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_35_STAIRS.get()), blockTexture(ModBLocks.BASALT_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_35_WALL.get()), blockTexture(ModBLocks.BASALT_35_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_36_SLAB.get()),
                blockTexture(ModBLocks.BASALT_36_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_36_STAIRS.get()), blockTexture(ModBLocks.BASALT_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_36_WALL.get()), blockTexture(ModBLocks.BASALT_36_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_37_SLAB.get()),
                blockTexture(ModBLocks.BASALT_37_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_37_STAIRS.get()), blockTexture(ModBLocks.BASALT_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_37_WALL.get()), blockTexture(ModBLocks.BASALT_37_BLOCK.get()));

        blockWithItem(ModBLocks.BASALT_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BASALT_38_SLAB.get()),
                blockTexture(ModBLocks.BASALT_38_BLOCK.get()),
                blockTexture(ModBLocks.BASALT_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BASALT_38_STAIRS.get()), blockTexture(ModBLocks.BASALT_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BASALT_38_WALL.get()), blockTexture(ModBLocks.BASALT_38_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_1_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_1_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_1_STAIRS.get()), blockTexture(ModBLocks.BRICKS_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_1_WALL.get()), blockTexture(ModBLocks.BRICKS_1_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_3_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_3_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_3_STAIRS.get()), blockTexture(ModBLocks.BRICKS_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_3_WALL.get()), blockTexture(ModBLocks.BRICKS_3_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_4_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_4_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_4_STAIRS.get()), blockTexture(ModBLocks.BRICKS_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_4_WALL.get()), blockTexture(ModBLocks.BRICKS_4_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_5_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_5_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_5_STAIRS.get()), blockTexture(ModBLocks.BRICKS_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_5_WALL.get()), blockTexture(ModBLocks.BRICKS_5_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_6_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_6_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_6_STAIRS.get()), blockTexture(ModBLocks.BRICKS_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_6_WALL.get()), blockTexture(ModBLocks.BRICKS_6_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_7_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_7_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_7_STAIRS.get()), blockTexture(ModBLocks.BRICKS_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_7_WALL.get()), blockTexture(ModBLocks.BRICKS_7_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_8_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_8_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_8_STAIRS.get()), blockTexture(ModBLocks.BRICKS_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_8_WALL.get()), blockTexture(ModBLocks.BRICKS_8_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_9_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_9_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_9_STAIRS.get()), blockTexture(ModBLocks.BRICKS_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_9_WALL.get()), blockTexture(ModBLocks.BRICKS_9_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_10_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_10_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_10_STAIRS.get()), blockTexture(ModBLocks.BRICKS_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_10_WALL.get()), blockTexture(ModBLocks.BRICKS_10_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_11_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_11_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_11_STAIRS.get()), blockTexture(ModBLocks.BRICKS_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_11_WALL.get()), blockTexture(ModBLocks.BRICKS_11_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_12_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_12_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_12_STAIRS.get()), blockTexture(ModBLocks.BRICKS_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_12_WALL.get()), blockTexture(ModBLocks.BRICKS_12_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_13_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_13_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_13_STAIRS.get()), blockTexture(ModBLocks.BRICKS_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_13_WALL.get()), blockTexture(ModBLocks.BRICKS_13_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_14_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_14_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_14_STAIRS.get()), blockTexture(ModBLocks.BRICKS_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_14_WALL.get()), blockTexture(ModBLocks.BRICKS_14_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_16_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_16_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_16_STAIRS.get()), blockTexture(ModBLocks.BRICKS_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_16_WALL.get()), blockTexture(ModBLocks.BRICKS_16_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_17_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_17_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_17_STAIRS.get()), blockTexture(ModBLocks.BRICKS_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_17_WALL.get()), blockTexture(ModBLocks.BRICKS_17_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_18_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_18_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_18_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_18_STAIRS.get()), blockTexture(ModBLocks.BRICKS_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_18_WALL.get()), blockTexture(ModBLocks.BRICKS_18_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_19_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_19_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_19_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_19_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_19_STAIRS.get()), blockTexture(ModBLocks.BRICKS_19_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_19_WALL.get()), blockTexture(ModBLocks.BRICKS_19_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_20_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_20_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_20_STAIRS.get()), blockTexture(ModBLocks.BRICKS_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_20_WALL.get()), blockTexture(ModBLocks.BRICKS_20_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_21_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_21_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_21_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_21_STAIRS.get()), blockTexture(ModBLocks.BRICKS_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_21_WALL.get()), blockTexture(ModBLocks.BRICKS_21_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_22_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_22_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_22_STAIRS.get()), blockTexture(ModBLocks.BRICKS_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_22_WALL.get()), blockTexture(ModBLocks.BRICKS_22_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_23_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_23_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_23_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_23_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_23_STAIRS.get()), blockTexture(ModBLocks.BRICKS_23_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_23_WALL.get()), blockTexture(ModBLocks.BRICKS_23_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_24_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_24_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_24_STAIRS.get()), blockTexture(ModBLocks.BRICKS_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_24_WALL.get()), blockTexture(ModBLocks.BRICKS_24_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_25_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_25_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_25_STAIRS.get()), blockTexture(ModBLocks.BRICKS_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_25_WALL.get()), blockTexture(ModBLocks.BRICKS_25_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_26_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_26_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_26_STAIRS.get()), blockTexture(ModBLocks.BRICKS_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_26_WALL.get()), blockTexture(ModBLocks.BRICKS_26_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_27_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_27_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_27_STAIRS.get()), blockTexture(ModBLocks.BRICKS_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_27_WALL.get()), blockTexture(ModBLocks.BRICKS_27_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_28_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_28_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_28_STAIRS.get()), blockTexture(ModBLocks.BRICKS_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_28_WALL.get()), blockTexture(ModBLocks.BRICKS_28_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_29_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_29_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_29_STAIRS.get()), blockTexture(ModBLocks.BRICKS_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_29_WALL.get()), blockTexture(ModBLocks.BRICKS_29_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_30_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_30_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_30_STAIRS.get()), blockTexture(ModBLocks.BRICKS_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_30_WALL.get()), blockTexture(ModBLocks.BRICKS_30_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_31_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_31_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_31_STAIRS.get()), blockTexture(ModBLocks.BRICKS_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_31_WALL.get()), blockTexture(ModBLocks.BRICKS_31_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_32_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_32_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_32_STAIRS.get()), blockTexture(ModBLocks.BRICKS_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_32_WALL.get()), blockTexture(ModBLocks.BRICKS_32_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_33_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_33_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_33_STAIRS.get()), blockTexture(ModBLocks.BRICKS_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_33_WALL.get()), blockTexture(ModBLocks.BRICKS_33_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_34_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_34_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_34_STAIRS.get()), blockTexture(ModBLocks.BRICKS_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_34_WALL.get()), blockTexture(ModBLocks.BRICKS_34_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_35_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_35_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_35_STAIRS.get()), blockTexture(ModBLocks.BRICKS_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_35_WALL.get()), blockTexture(ModBLocks.BRICKS_35_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_36_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_36_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_36_STAIRS.get()), blockTexture(ModBLocks.BRICKS_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_36_WALL.get()), blockTexture(ModBLocks.BRICKS_36_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_37_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_37_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_37_STAIRS.get()), blockTexture(ModBLocks.BRICKS_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_37_WALL.get()), blockTexture(ModBLocks.BRICKS_37_BLOCK.get()));

        blockWithItem(ModBLocks.BRICKS_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.BRICKS_38_SLAB.get()),
                blockTexture(ModBLocks.BRICKS_38_BLOCK.get()),
                blockTexture(ModBLocks.BRICKS_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.BRICKS_38_STAIRS.get()), blockTexture(ModBLocks.BRICKS_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.BRICKS_38_WALL.get()), blockTexture(ModBLocks.BRICKS_38_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_1_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_1_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_1_STAIRS.get()), blockTexture(ModBLocks.CALCITE_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_1_WALL.get()), blockTexture(ModBLocks.CALCITE_1_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_2_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_2_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_2_STAIRS.get()), blockTexture(ModBLocks.CALCITE_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_2_WALL.get()), blockTexture(ModBLocks.CALCITE_2_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_3_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_3_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_3_STAIRS.get()), blockTexture(ModBLocks.CALCITE_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_3_WALL.get()), blockTexture(ModBLocks.CALCITE_3_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_4_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_4_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_4_STAIRS.get()), blockTexture(ModBLocks.CALCITE_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_4_WALL.get()), blockTexture(ModBLocks.CALCITE_4_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_5_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_5_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_5_STAIRS.get()), blockTexture(ModBLocks.CALCITE_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_5_WALL.get()), blockTexture(ModBLocks.CALCITE_5_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_6_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_6_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_6_STAIRS.get()), blockTexture(ModBLocks.CALCITE_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_6_WALL.get()), blockTexture(ModBLocks.CALCITE_6_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_7_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_7_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_7_STAIRS.get()), blockTexture(ModBLocks.CALCITE_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_7_WALL.get()), blockTexture(ModBLocks.CALCITE_7_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_8_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_8_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_8_STAIRS.get()), blockTexture(ModBLocks.CALCITE_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_8_WALL.get()), blockTexture(ModBLocks.CALCITE_8_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_9_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_9_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_9_STAIRS.get()), blockTexture(ModBLocks.CALCITE_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_9_WALL.get()), blockTexture(ModBLocks.CALCITE_9_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_10_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_10_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_10_STAIRS.get()), blockTexture(ModBLocks.CALCITE_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_10_WALL.get()), blockTexture(ModBLocks.CALCITE_10_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_11_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_11_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_11_STAIRS.get()), blockTexture(ModBLocks.CALCITE_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_11_WALL.get()), blockTexture(ModBLocks.CALCITE_11_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_12_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_12_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_12_STAIRS.get()), blockTexture(ModBLocks.CALCITE_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_12_WALL.get()), blockTexture(ModBLocks.CALCITE_12_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_13_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_13_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_13_STAIRS.get()), blockTexture(ModBLocks.CALCITE_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_13_WALL.get()), blockTexture(ModBLocks.CALCITE_13_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_14_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_14_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_14_STAIRS.get()), blockTexture(ModBLocks.CALCITE_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_14_WALL.get()), blockTexture(ModBLocks.CALCITE_14_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_16_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_16_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_16_STAIRS.get()), blockTexture(ModBLocks.CALCITE_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_16_WALL.get()), blockTexture(ModBLocks.CALCITE_16_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_17_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_17_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_17_STAIRS.get()), blockTexture(ModBLocks.CALCITE_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_17_WALL.get()), blockTexture(ModBLocks.CALCITE_17_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_18_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_18_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_18_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_18_STAIRS.get()), blockTexture(ModBLocks.CALCITE_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_18_WALL.get()), blockTexture(ModBLocks.CALCITE_18_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_19_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_19_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_19_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_19_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_19_STAIRS.get()), blockTexture(ModBLocks.CALCITE_19_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_19_WALL.get()), blockTexture(ModBLocks.CALCITE_19_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_20_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_20_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_20_STAIRS.get()), blockTexture(ModBLocks.CALCITE_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_20_WALL.get()), blockTexture(ModBLocks.CALCITE_20_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_21_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_21_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_21_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_21_STAIRS.get()), blockTexture(ModBLocks.CALCITE_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_21_WALL.get()), blockTexture(ModBLocks.CALCITE_21_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_22_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_22_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_22_STAIRS.get()), blockTexture(ModBLocks.CALCITE_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_22_WALL.get()), blockTexture(ModBLocks.CALCITE_22_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_23_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_23_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_23_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_23_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_23_STAIRS.get()), blockTexture(ModBLocks.CALCITE_23_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_23_WALL.get()), blockTexture(ModBLocks.CALCITE_23_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_24_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_24_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_24_STAIRS.get()), blockTexture(ModBLocks.CALCITE_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_24_WALL.get()), blockTexture(ModBLocks.CALCITE_24_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_25_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_25_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_25_STAIRS.get()), blockTexture(ModBLocks.CALCITE_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_25_WALL.get()), blockTexture(ModBLocks.CALCITE_25_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_26_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_26_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_26_STAIRS.get()), blockTexture(ModBLocks.CALCITE_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_26_WALL.get()), blockTexture(ModBLocks.CALCITE_26_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_27_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_27_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_27_STAIRS.get()), blockTexture(ModBLocks.CALCITE_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_27_WALL.get()), blockTexture(ModBLocks.CALCITE_27_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_28_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_28_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_28_STAIRS.get()), blockTexture(ModBLocks.CALCITE_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_28_WALL.get()), blockTexture(ModBLocks.CALCITE_28_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_29_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_29_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_29_STAIRS.get()), blockTexture(ModBLocks.CALCITE_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_29_WALL.get()), blockTexture(ModBLocks.CALCITE_29_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_30_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_30_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_30_STAIRS.get()), blockTexture(ModBLocks.CALCITE_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_30_WALL.get()), blockTexture(ModBLocks.CALCITE_30_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_31_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_31_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_31_STAIRS.get()), blockTexture(ModBLocks.CALCITE_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_31_WALL.get()), blockTexture(ModBLocks.CALCITE_31_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_32_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_32_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_32_STAIRS.get()), blockTexture(ModBLocks.CALCITE_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_32_WALL.get()), blockTexture(ModBLocks.CALCITE_32_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_33_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_33_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_33_STAIRS.get()), blockTexture(ModBLocks.CALCITE_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_33_WALL.get()), blockTexture(ModBLocks.CALCITE_33_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_34_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_34_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_34_STAIRS.get()), blockTexture(ModBLocks.CALCITE_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_34_WALL.get()), blockTexture(ModBLocks.CALCITE_34_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_35_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_35_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_35_STAIRS.get()), blockTexture(ModBLocks.CALCITE_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_35_WALL.get()), blockTexture(ModBLocks.CALCITE_35_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_36_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_36_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_36_STAIRS.get()), blockTexture(ModBLocks.CALCITE_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_36_WALL.get()), blockTexture(ModBLocks.CALCITE_36_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_37_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_37_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_37_STAIRS.get()), blockTexture(ModBLocks.CALCITE_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_37_WALL.get()), blockTexture(ModBLocks.CALCITE_37_BLOCK.get()));

        blockWithItem(ModBLocks.CALCITE_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CALCITE_38_SLAB.get()),
                blockTexture(ModBLocks.CALCITE_38_BLOCK.get()),
                blockTexture(ModBLocks.CALCITE_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CALCITE_38_STAIRS.get()), blockTexture(ModBLocks.CALCITE_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CALCITE_38_WALL.get()), blockTexture(ModBLocks.CALCITE_38_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_1_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_1_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_1_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_1_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_1_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_2_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_2_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_2_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_2_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_2_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_3_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_3_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_3_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_3_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_3_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_5_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_5_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_5_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_5_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_5_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_6_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_6_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_6_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_6_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_6_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_7_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_7_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_7_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_7_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_7_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_8_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_8_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_8_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_8_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_8_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_9_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_9_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_9_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_9_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_9_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_10_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_10_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_10_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_10_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_10_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_11_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_11_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_11_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_11_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_11_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_12_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_12_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_12_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_12_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_12_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_13_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_13_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_13_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_13_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_13_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_14_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_14_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_14_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_14_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_14_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_16_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_16_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_16_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_16_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_16_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_17_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_17_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_17_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_17_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_17_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_18_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_18_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_18_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_18_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_18_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_18_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_20_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_20_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_20_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_20_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_20_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_21_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_21_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_21_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_21_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_21_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_21_BLOCK.get()));
        blockWithItem(ModBLocks.CDEEPSLATE_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_22_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_22_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_22_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_22_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_22_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_23_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_23_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_23_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_23_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_23_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_23_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_23_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_23_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_24_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_24_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_24_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_24_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_24_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_25_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_25_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_25_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_25_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_25_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_26_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_26_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_26_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_26_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_26_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_27_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_27_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_27_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_27_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_27_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_28_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_28_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_28_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_28_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_28_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_29_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_29_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_29_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_29_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_29_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_30_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_30_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_30_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_30_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_30_BLOCK.get()));
        blockWithItem(ModBLocks.CDEEPSLATE_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_31_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_31_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_31_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_31_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_31_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_32_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_32_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_32_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_32_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_32_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_33_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_33_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_33_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_33_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_33_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_34_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_34_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_34_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_34_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_34_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_35_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_35_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_35_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_35_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_35_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_36_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_36_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_36_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_36_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_36_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_37_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_37_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_37_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_37_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_37_BLOCK.get()));

        blockWithItem(ModBLocks.CDEEPSLATE_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.CDEEPSLATE_38_SLAB.get()),
                blockTexture(ModBLocks.CDEEPSLATE_38_BLOCK.get()),
                blockTexture(ModBLocks.CDEEPSLATE_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.CDEEPSLATE_38_STAIRS.get()), blockTexture(ModBLocks.CDEEPSLATE_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.CDEEPSLATE_38_WALL.get()), blockTexture(ModBLocks.CDEEPSLATE_38_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_1_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_1_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_1_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_1_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_1_STAIRS.get()), blockTexture(ModBLocks.GRANITE_1_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_1_WALL.get()), blockTexture(ModBLocks.GRANITE_1_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_2_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_2_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_2_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_2_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_2_STAIRS.get()), blockTexture(ModBLocks.GRANITE_2_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_2_WALL.get()), blockTexture(ModBLocks.GRANITE_2_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_3_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_3_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_3_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_3_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_3_STAIRS.get()), blockTexture(ModBLocks.GRANITE_3_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_3_WALL.get()), blockTexture(ModBLocks.GRANITE_3_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_4_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_4_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_4_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_4_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_4_STAIRS.get()), blockTexture(ModBLocks.GRANITE_4_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_4_WALL.get()), blockTexture(ModBLocks.GRANITE_4_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_5_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_5_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_5_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_5_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_5_STAIRS.get()), blockTexture(ModBLocks.GRANITE_5_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_5_WALL.get()), blockTexture(ModBLocks.GRANITE_5_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_6_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_6_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_6_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_6_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_6_STAIRS.get()), blockTexture(ModBLocks.GRANITE_6_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_6_WALL.get()), blockTexture(ModBLocks.GRANITE_6_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_7_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_7_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_7_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_7_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_7_STAIRS.get()), blockTexture(ModBLocks.GRANITE_7_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_7_WALL.get()), blockTexture(ModBLocks.GRANITE_7_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_8_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_8_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_8_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_8_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_8_STAIRS.get()), blockTexture(ModBLocks.GRANITE_8_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_8_WALL.get()), blockTexture(ModBLocks.GRANITE_8_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_9_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_9_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_9_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_9_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_9_STAIRS.get()), blockTexture(ModBLocks.GRANITE_9_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_9_WALL.get()), blockTexture(ModBLocks.GRANITE_9_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_10_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_10_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_10_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_10_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_10_STAIRS.get()), blockTexture(ModBLocks.GRANITE_10_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_10_WALL.get()), blockTexture(ModBLocks.GRANITE_10_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_11_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_11_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_11_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_11_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_11_STAIRS.get()), blockTexture(ModBLocks.GRANITE_11_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_11_WALL.get()), blockTexture(ModBLocks.GRANITE_11_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_12_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_12_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_12_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_12_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_12_STAIRS.get()), blockTexture(ModBLocks.GRANITE_12_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_12_WALL.get()), blockTexture(ModBLocks.GRANITE_12_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_13_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_13_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_13_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_13_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_13_STAIRS.get()), blockTexture(ModBLocks.GRANITE_13_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_13_WALL.get()), blockTexture(ModBLocks.GRANITE_13_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_14_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_14_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_14_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_14_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_14_STAIRS.get()), blockTexture(ModBLocks.GRANITE_14_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_14_WALL.get()), blockTexture(ModBLocks.GRANITE_14_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_16_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_16_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_16_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_16_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_16_STAIRS.get()), blockTexture(ModBLocks.GRANITE_16_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_16_WALL.get()), blockTexture(ModBLocks.GRANITE_16_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_17_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_17_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_17_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_17_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_17_STAIRS.get()), blockTexture(ModBLocks.GRANITE_17_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_17_WALL.get()), blockTexture(ModBLocks.GRANITE_17_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_18_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_18_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_18_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_18_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_18_STAIRS.get()), blockTexture(ModBLocks.GRANITE_18_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_18_WALL.get()), blockTexture(ModBLocks.GRANITE_18_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_19_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_19_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_19_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_19_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_19_STAIRS.get()), blockTexture(ModBLocks.GRANITE_19_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_19_WALL.get()), blockTexture(ModBLocks.GRANITE_19_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_20_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_20_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_20_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_20_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_20_STAIRS.get()), blockTexture(ModBLocks.GRANITE_20_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_20_WALL.get()), blockTexture(ModBLocks.GRANITE_20_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_21_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_21_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_21_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_21_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_21_STAIRS.get()), blockTexture(ModBLocks.GRANITE_21_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_21_WALL.get()), blockTexture(ModBLocks.GRANITE_21_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_22_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_22_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_22_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_22_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_22_STAIRS.get()), blockTexture(ModBLocks.GRANITE_22_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_22_WALL.get()), blockTexture(ModBLocks.GRANITE_22_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_23_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_23_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_23_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_23_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_23_STAIRS.get()), blockTexture(ModBLocks.GRANITE_23_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_23_WALL.get()), blockTexture(ModBLocks.GRANITE_23_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_24_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_24_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_24_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_24_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_24_STAIRS.get()), blockTexture(ModBLocks.GRANITE_24_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_24_WALL.get()), blockTexture(ModBLocks.GRANITE_24_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_25_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_25_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_25_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_25_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_25_STAIRS.get()), blockTexture(ModBLocks.GRANITE_25_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_25_WALL.get()), blockTexture(ModBLocks.GRANITE_25_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_26_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_26_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_26_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_26_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_26_STAIRS.get()), blockTexture(ModBLocks.GRANITE_26_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_26_WALL.get()), blockTexture(ModBLocks.GRANITE_26_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_27_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_27_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_27_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_27_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_27_STAIRS.get()), blockTexture(ModBLocks.GRANITE_27_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_27_WALL.get()), blockTexture(ModBLocks.GRANITE_27_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_28_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_28_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_28_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_28_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_28_STAIRS.get()), blockTexture(ModBLocks.GRANITE_28_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_28_WALL.get()), blockTexture(ModBLocks.GRANITE_28_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_29_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_29_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_29_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_29_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_29_STAIRS.get()), blockTexture(ModBLocks.GRANITE_29_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_29_WALL.get()), blockTexture(ModBLocks.GRANITE_29_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_30_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_30_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_30_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_30_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_30_STAIRS.get()), blockTexture(ModBLocks.GRANITE_30_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_30_WALL.get()), blockTexture(ModBLocks.GRANITE_30_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_31_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_31_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_31_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_31_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_31_STAIRS.get()), blockTexture(ModBLocks.GRANITE_31_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_31_WALL.get()), blockTexture(ModBLocks.GRANITE_31_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_32_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_32_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_32_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_32_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_32_STAIRS.get()), blockTexture(ModBLocks.GRANITE_32_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_32_WALL.get()), blockTexture(ModBLocks.GRANITE_32_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_33_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_33_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_33_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_33_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_33_STAIRS.get()), blockTexture(ModBLocks.GRANITE_33_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_33_WALL.get()), blockTexture(ModBLocks.GRANITE_33_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_34_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_34_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_34_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_34_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_34_STAIRS.get()), blockTexture(ModBLocks.GRANITE_34_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_34_WALL.get()), blockTexture(ModBLocks.GRANITE_34_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_35_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_35_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_35_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_35_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_35_STAIRS.get()), blockTexture(ModBLocks.GRANITE_35_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_35_WALL.get()), blockTexture(ModBLocks.GRANITE_35_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_36_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_36_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_36_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_36_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_36_STAIRS.get()), blockTexture(ModBLocks.GRANITE_36_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_36_WALL.get()), blockTexture(ModBLocks.GRANITE_36_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_37_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_37_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_37_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_37_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_37_STAIRS.get()), blockTexture(ModBLocks.GRANITE_37_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_37_WALL.get()), blockTexture(ModBLocks.GRANITE_37_BLOCK.get()));

        blockWithItem(ModBLocks.GRANITE_38_BLOCK);
        slabBlock(((SlabBlock) ModBLocks.GRANITE_38_SLAB.get()),
                blockTexture(ModBLocks.GRANITE_38_BLOCK.get()),
                blockTexture(ModBLocks.GRANITE_38_BLOCK.get()));
        stairsBlock(((StairBlock) ModBLocks.GRANITE_38_STAIRS.get()), blockTexture(ModBLocks.GRANITE_38_BLOCK.get()));
        wallBlock(((WallBlock) ModBLocks.GRANITE_38_WALL.get()), blockTexture(ModBLocks.GRANITE_38_BLOCK.get()));



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


        models().withExistingParent("block/" + ModBLocks.OPIUM_POPPY.getId().getPath(),
                        mcLoc("block/cross"))
                .texture("cross", modLoc("block/opium_poppy"))
                .renderType("cutout");

        models().withExistingParent("item/" + ModBLocks.OPIUM_POPPY.getId().getPath(),
                        mcLoc("item/generated"))
                .texture("layer0", modLoc("block/opium_poppy"))
                .renderType("cutout");

        getVariantBuilder(ModBLocks.OPIUM_POPPY.get())
                .partialState()
                .modelForState()
                .modelFile(models().getExistingFile(modLoc("block/" + ModBLocks.OPIUM_POPPY.getId().getPath())))
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

// Now register the blockstate that references both models
        getVariantBuilder(ModBLocks.BLUE_ROSE_BUSH.get())
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.BLUE_ROSE_BUSH.getId().getPath() + "_bottom"))).addModel()
                .partialState().with(TallFlowerBlock.HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().getExistingFile(
                        modLoc("block/" + ModBLocks.BLUE_ROSE_BUSH.getId().getPath() + "_top"))).addModel();







        //simpleBlockWithItem(ModBLocks.POTTED_WINTER_ROSE.get(), models().singleTexture("potted_winter_rose", new ResourceLocation.Serializer("flower_pot_cross"), "plant", blockTexture(ModBLocks.WINTER_ROSE.get())).renderType("cutout"));

        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        // Register block states and models for villager profession-related blocks
        blockWithItem(ModBLocks.MINT_BLOCK);
        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
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
}
