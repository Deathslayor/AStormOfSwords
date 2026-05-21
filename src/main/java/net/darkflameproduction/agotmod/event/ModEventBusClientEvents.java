package net.darkflameproduction.agotmod.event;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.barrel.BarrelLargeBlockEntityRenderer;
import net.darkflameproduction.agotmod.block.custom.barrel.BarrelMediumBlockEntityRenderer;
import net.darkflameproduction.agotmod.block.custom.barrel.BarrelSmallBlockEntityRenderer;
import net.darkflameproduction.agotmod.block.custom.furniture.ArmChairBlockEntityRenderer;
import net.darkflameproduction.agotmod.block.custom.furniture.ChairBlockEntityRenderer;
import net.darkflameproduction.agotmod.block.custom.furniture.StoolBlockEntityRenderer;
import net.darkflameproduction.agotmod.block.custom.furniture.TableBlockEntityRenderer;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.entity.client.birds.Crow_Entity_Renderer;
import net.darkflameproduction.agotmod.entity.client.northofthewall.Mammoth_Entity_Renderer;
import net.darkflameproduction.agotmod.entity.client.wolves.Direwolf_Entity_Renderer;
import net.darkflameproduction.agotmod.entity.client.npc.Peasant_Renderer;
import net.darkflameproduction.agotmod.event.KeyMappings.KeyBindings;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.@NotNull RegisterLayerDefinitions event) {}

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.MAMMOTH_ENTITY.get(),  Mammoth_Entity_Renderer::new);
        EntityRenderers.register(ModEntities.CROW_ENTITY.get(),     Crow_Entity_Renderer::new);
        EntityRenderers.register(ModEntities.DIREWOLF_ENTITY.get(), Direwolf_Entity_Renderer::new);
        EntityRenderers.register(ModEntities.PEASANT_ENTITY.get(),  Peasant_Renderer::new);
        EntityRenderers.register(ModEntities.SEAT_ENTITY.get(),
                ctx -> new net.minecraft.client.renderer.entity.NoopRenderer<>(ctx));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeys(@NotNull RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.INSTANCE.OpenCustomGUI);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(),        SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.TABLE.get(),            TableBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.STOOL.get(),            StoolBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CHAIR.get(),            ChairBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.ARM_CHAIR.get(),        ArmChairBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BARREL_LARGE.get(),     BarrelLargeBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BARREL_MEDIUM.get(),    BarrelMediumBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.BARREL_SMALL.get(),     BarrelSmallBlockEntityRenderer::new);
    }
}