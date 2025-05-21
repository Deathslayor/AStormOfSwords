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
        // ---------------------------(TIN)--------------------------- //
        // Register block states and models for tin-related blocks
        GhostGrassBlock(ModBLocks.GHOST_GRASS_BLOCK.get());
        blockItem(ModBLocks.GHOST_GRASS_BLOCK);
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

        // Define the wood types array
        String[] woodTypes = {
                "sycamore",
                "pine",
                "ash",
                "beech",
                "cedar",
                "chestnut",
                "hawthorn",
                "ironwood",
                "sentinel",
                "blackbark",
                "aspen",
                "black_cherry",
                "black_olive",
                "crabapple",
                "olive",
                "white_cherry",
                "red_cherry",
                "fir",
                "willow",
                "wormtree",
                "alder"
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

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 22 || i == 31) continue;

            ModBLocks.BlockSet set = ModBLocks.BLACKSTONE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }
        for (int i = 1; i <= 38; i++) {
            if (i == 5 || i == 15 || i == 19 || i == 23) continue;

            ModBLocks.BlockSet set = ModBLocks.STONE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }
        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 1) continue;

            ModBLocks.BlockSet set = ModBLocks.BASALT_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }
        for (int i = 1; i <= 38; i++) {
            if (i == 2 || i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.BRICKS_VARIANTS.get(i); // Changed to BRICK_VARIANTS
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.CALCITE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 4 || i == 15 || i == 19) continue;

            ModBLocks.BlockSet set = ModBLocks.CDEEPSLATE_VARIANTS.get(i); // Changed to BRICK_VARIANTS
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.GRANITE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.REDKEEP_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 16 || i == 17 || i == 18) continue;

            ModBLocks.BlockSet set = ModBLocks.RSANDSTONE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 16 || i == 17 || i == 18) continue;

            ModBLocks.BlockSet set = ModBLocks.SANDSTONE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 23) continue;

            ModBLocks.BlockSet set = ModBLocks.SSTONE_VARIANTS.get(i); // Changed to SSTONE_VARIANTS
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 7 || i == 9 || i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.MUD_BRICK_VARIANTS.get(i); // Changed to SSTONE_VARIANTS
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 35) continue;

            ModBLocks.BlockSet set = ModBLocks.BONE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.DRIPSTONE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.ANDESITE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 10 || i == 15 || i == 20 || i == 21) continue;

            ModBLocks.BlockSet set = ModBLocks.TUFF_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }


        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.PACKED_ICE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.DIORITE_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 14 || i == 15) continue;

            ModBLocks.BlockSet set = ModBLocks.QUARTZ_VARIANTS.get(i);
            if (set == null) continue; // Skip if not registered (just to be safe)

            blockWithItem(set.base());

            slabBlock(
                    (SlabBlock) set.slab().get(),
                    blockTexture(set.base().get()),
                    blockTexture(set.base().get())
            );

            stairsBlock(
                    (StairBlock) set.stairs().get(),
                    blockTexture(set.base().get())
            );

            wallBlock(
                    (WallBlock) set.wall().get(),
                    blockTexture(set.base().get())
            );
        }


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



        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        // Register block states and models for villager profession-related blocks
        blockWithItem(ModBLocks.MINT_BLOCK);
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





}
