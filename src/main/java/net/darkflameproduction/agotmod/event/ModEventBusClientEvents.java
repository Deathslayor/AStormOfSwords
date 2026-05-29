package net.darkflameproduction.agotmod.event;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
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
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

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


    private static void registerPie(RegisterColorHandlersEvent.Item event,
                                    DeferredHolder<Item, Item> item, int overlayColor) {
        event.register((stack, tintIndex) -> tintIndex == 1 ? overlayColor : 0xFFFFFFFF,
                item.get());
    }

    // ── Client event class — register both block and item color handlers ───────

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        for (Map.Entry<String, Integer> entry : ModBLocks.WATTLE_DAUB_COLORS.entrySet()) {
            int color = entry.getValue();
            String colorName = entry.getKey();

            // plain
            event.register((state, level, pos, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF,
                    ModBLocks.WATTLE_AND_DAUB_PLAIN.get(colorName).get());

            // variants
            for (DeferredBlock<Block> block : ModBLocks.WATTLE_AND_DAUB_VARIANTS.get(colorName).values()) {
                event.register((state, level, pos, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF,
                        block.get());
            }
        }
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        registerPie(event, ModItems.SPINACH_PIE, 0xFF1a5c00);
        for (Map.Entry<String, Integer> entry : ModBLocks.WATTLE_DAUB_COLORS.entrySet()) {
            int color = entry.getValue();
            String colorName = entry.getKey();

            // plain
            event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF,
                    ModBLocks.WATTLE_AND_DAUB_PLAIN.get(colorName).get().asItem());

            // variants
            for (DeferredBlock<Block> block : ModBLocks.WATTLE_AND_DAUB_VARIANTS.get(colorName).values()) {
                event.register((stack, tintIndex) -> tintIndex == 0 ? color : 0xFFFFFFFF,
                        block.get().asItem());
            }
        }
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