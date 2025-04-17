// This code belongs to the package net.stormofsorts.agotmod
package net.darkflameproduction.agotmod;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItemProperties;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.item.creativetabs.*;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.darkflameproduction.agotmod.villager.ModVillagers;
import net.darkflameproduction.agotmod.worldgen.biome.ModTerrablender;
import net.minecraft.resources.ResourceLocation;
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

        LOGGER.info("AGOT Mod initialized");
    }

    // Common setup method
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Perform common setup tasks here
        event.enqueueWork(() -> {
            //((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBLocks.WINTER_ROSE.getId(), ModBLocks.POTTED_WINTER_ROSE);
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
