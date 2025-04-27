// This code belongs to the package net.stormofsorts.agotmod
package net.darkflameproduction.agotmod;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.item.ModItemProperties;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.item.creativetabs.*;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.darkflameproduction.agotmod.util.ModWoodTypes;
import net.darkflameproduction.agotmod.villager.ModVillagers;
import net.darkflameproduction.agotmod.worldgen.biome.ModTerrablender;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AGoTMod.MOD_ID)
public class AGoTMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "agotmod";

    // Add a logger for the mod
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Constructor for AGoTMod class
    public AGoTMod(IEventBus modEventBus) {
        // Register various mod features with the mod event bus

        // Adds Creative Mod Tabs
        ModCreativeBuildingBlocks.register(modEventBus);
        ModCreativeNaturalBlocks.register(modEventBus);
        ModCreativeToolsUtilities.register(modEventBus);
        ModCreativeWeapons.register(modEventBus);
        ModCreativeArmour.register(modEventBus);
        ModCreativeFoods.register(modEventBus);
        ModCreativeIngredients.register(modEventBus);
        ModCreativeAnimals.register(modEventBus);
        ModCreativeMagic.register(modEventBus);
        ModCreativeArchery.register(modEventBus);
        ModBlockEntities.register(modEventBus);


        // Register The Wall line generator

        // Adds Sounds to the game
        ModSounds.register(modEventBus);

        // Adds Custom Villagers to the game
        ModVillagers.register(modEventBus);

        // Adds Custom Blocks to the game
        ModBLocks.register(modEventBus);

        // Adds Custom Items to the game
        ModItems.register(modEventBus);

        // Adds custom Entities to the game

        // Listen for common setup event
        modEventBus.addListener(this::commonSetup);

        // Register this class as an event listener
        NeoForge.EVENT_BUS.register(this);

        // Listen for creative mode tab build event
        modEventBus.addListener(this::addCreative);

        // Register Biomes
        ModTerrablender.registerBiomes();

        ModEntities.register(modEventBus);

        LOGGER.info("AGOT Mod initialized");
    }

    // Common setup method
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Perform common setup tasks here
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.WINTER_ROSE.getId(), ModBLocks.POTTED_WINTER_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.WILD_RADISH.getId(), ModBLocks.POTTED_WILD_RADISH);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.WHITE_ROSE.getId(), ModBLocks.POTTED_WHITE_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.THORN_BUSH.getId(), ModBLocks.POTTED_THORN_BUSH);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.THISTLE.getId(), ModBLocks.POTTED_THISTLE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.TANSY.getId(), ModBLocks.POTTED_TANSY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.SPICEFLOWER.getId(), ModBLocks.POTTED_SPICEFLOWER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.SEDGE.getId(), ModBLocks.POTTED_SEDGE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.SAFFRON_CROCUS.getId(), ModBLocks.POTTED_SAFFRON_CROCUS);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.ROSE.getId(), ModBLocks.POTTED_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.POISON_KISSES.getId(), ModBLocks.POTTED_POISON_KISSES);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.PENNYROYAL.getId(), ModBLocks.POTTED_PENNYROYAL);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.OPIUM_POPPY.getId(), ModBLocks.POTTED_OPIUM_POPPY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.NIGHTSHADE.getId(), ModBLocks.POTTED_NIGHTSHADE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.MOONBLOOM.getId(), ModBLocks.POTTED_MOONBLOOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LUNGWORT.getId(), ModBLocks.POTTED_LUNGWORT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LIVERWORT.getId(), ModBLocks.POTTED_LIVERWORT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LAVENDER.getId(), ModBLocks.POTTED_LAVENDER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.LADYS_LACE.getId(), ModBLocks.POTTED_LADYS_LACE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GORSE.getId(), ModBLocks.POTTED_GORSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GOLDENROD.getId(), ModBLocks.POTTED_GOLDENROD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GOLDENCUP.getId(), ModBLocks.POTTED_GOLDENCUP);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GOATHEAD.getId(), ModBLocks.POTTED_GOATHEAD);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GINGER.getId(), ModBLocks.POTTED_GINGER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.GILLYFLOWER.getId(), ModBLocks.POTTED_GILLYFLOWER);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.FROSTFIRE.getId(), ModBLocks.POTTED_FROSTFIRE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.FORGET_ME_NOT.getId(), ModBLocks.POTTED_FORGET_ME_NOT);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.EVENING_STAR.getId(), ModBLocks.POTTED_EVENING_STAR);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.DUSKY_ROSE.getId(), ModBLocks.POTTED_DUSKY_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.DRAGONS_BREATH.getId(), ModBLocks.POTTED_DRAGONS_BREATH);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.COLDSNAP.getId(), ModBLocks.POTTED_COLDSNAP);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.BLUE_ROSE.getId(), ModBLocks.POTTED_BLUE_ROSE);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.BLOODBLOOM.getId(), ModBLocks.POTTED_BLOODBLOOM);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.BLACK_LOTUS.getId(), ModBLocks.POTTED_BLACK_LOTUS);

        });
    }

    // Creative mode tab build method
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // Add custom items/blocks to the creative mode tab here
    }

    // Server starting event listener
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Perform tasks when the server is starting
    }

    // Static inner class for handling client-side events
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        // Subscribe to client setup event
        @SubscribeEvent
        public static void onClientSetup(@NotNull FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.WEIRWOOD);
            Sheets.addWoodType(ModWoodTypes.PINE);
            // Register custom renderer for the Rhino entity

            event.enqueueWork(ModItemProperties::addCustomItemProperties);
        }
    }


    @Contract("_ -> new")
    @ApiStatus.Internal
    public static @NotNull ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
