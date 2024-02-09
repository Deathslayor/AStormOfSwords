// This code belongs to the package net.stormofsorts.agotmod
package net.astormofsorts.agotmod;

// Importing necessary classes from other packages

import com.mojang.logging.LogUtils;
import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.entity.ModEntities;
import net.astormofsorts.agotmod.entity.client.BearRenderer;
import net.astormofsorts.agotmod.entity.client.RhinoRenderer;
import net.astormofsorts.agotmod.entity.client.norththewall.WightRenderer;
import net.astormofsorts.agotmod.item.ModItems;
import net.astormofsorts.agotmod.item.creativetabs.*;
import net.astormofsorts.agotmod.sound.ModSounds;
import net.astormofsorts.agotmod.villager.ModVillagers;
import net.astormofsorts.agotmod.worldgen.MapBasedBiomeChunkGenerator;
import net.astormofsorts.agotmod.worldgen.MapBasedBiomeSource;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AGoTMod.MOD_ID)
public class AGoTMod {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "agotmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    // Constructor for AGoTMod class
    public AGoTMod() {
        // Get the mod event bus
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

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

        // Adds Sounds to the game
        ModSounds.register(modEventBus);

        // Adds Custom Villagers to the game
        ModVillagers.register(modEventBus);

        // Adds Custom Blocks to the game
        ModBLocks.register(modEventBus);

        // Adds Custom Items to the game
        ModItems.register(modEventBus);

        // Adds custom Entities to the game
        ModEntities.register(modEventBus);

        // Listen for common setup event
        modEventBus.addListener(this::commonSetup);

        // Register this class as an event listener
        MinecraftForge.EVENT_BUS.register(this);

        // Listen for creative mode tab build event
        modEventBus.addListener(this::addCreative);

        // Listen for Register Event
        modEventBus.addListener(this::onRegister);
    }

    // Common setup method
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Perform common setup tasks here
    }

    // Creative mode tab build method
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // Add custom items/blocks to the creative mode tab here
    }

    private void onRegister(RegisterEvent event) {
        Registry.register(BuiltInRegistries.BIOME_SOURCE, new ResourceLocation(MOD_ID, "map_based_biome_source"), MapBasedBiomeSource.CODEC);
        Registry.register(BuiltInRegistries.CHUNK_GENERATOR, new ResourceLocation(MOD_ID, "map_based_chunk_generator"), MapBasedBiomeChunkGenerator.CODEC);
    }

    // Server starting event listener
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Perform tasks when the server is starting
    }

    // Static inner class for handling client-side events
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        // Subscribe to client setup event
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Register custom renderer for the Rhino entity
            EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
            EntityRenderers.register(ModEntities.BEAR.get(), BearRenderer::new);

            EntityRenderers.register(ModEntities.WIGHT.get(), WightRenderer::new);
        }
    }
}
