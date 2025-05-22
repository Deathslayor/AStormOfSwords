// This code belongs to the package net.stormofsorts.agotmod.event
package net.darkflameproduction.agotmod.event;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.entity.client.birds.Crow_Entity_Renderer;
import net.darkflameproduction.agotmod.entity.client.northofthewall.Mammoth_Entity_Renderer;
import net.darkflameproduction.agotmod.entity.client.wolves.Direwolf_Entity_Renderer;
import net.darkflameproduction.agotmod.entity.client.npc.Northern_Peasant_Renderer; // Add this import
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.event.KeyMappings.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.jetbrains.annotations.NotNull;

// Annotating the class as a subscriber to the Forge Mod Event Bus
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    // Annotation indicating that this method should subscribe to the specified event
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.@NotNull RegisterLayerDefinitions event) {

    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.MAMMOTH_ENTITY.get(), Mammoth_Entity_Renderer::new);
        EntityRenderers.register(ModEntities.CROW_ENTITY.get(), Crow_Entity_Renderer::new);
        EntityRenderers.register(ModEntities.DIREWOLF_ENTITY.get(), Direwolf_Entity_Renderer::new);
        // Fixed: Use the proper renderer instead of the entity constructor
        EntityRenderers.register(ModEntities.NORTHERN_PEASANT_ENTITY.get(), Northern_Peasant_Renderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeys(@NotNull RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.INSTANCE.OpenCustomGUI);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}