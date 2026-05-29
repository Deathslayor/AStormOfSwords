package net.darkflameproduction.agotmod.client.overlay;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.minecraft.resources.ResourceLocation;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NpcSleepNotificationOverlay {

    private static final long DISPLAY_DURATION = 3000;
    private static final long FADE_DURATION    = 500;

    private static String message      = "";
    private static long   startTime    = 0;

    public static void show(String npcName) {
        message   = npcName + " " + Component.translatable("agotmod.notification.went_to_bed").getString();
        startTime = System.currentTimeMillis();
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR,
                ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "npc_sleep_notification"),
                NpcSleepNotificationOverlay::render);
    }

    private static void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        if (message.isEmpty()) return;

        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed >= DISPLAY_DURATION) {
            message = "";
            return;
        }

        float alpha;
        if (elapsed < FADE_DURATION) {
            alpha = (float) elapsed / FADE_DURATION;
        } else if (elapsed > DISPLAY_DURATION - FADE_DURATION) {
            alpha = 1.0f - ((float)(elapsed - (DISPLAY_DURATION - FADE_DURATION)) / FADE_DURATION);
        } else {
            alpha = 1.0f;
        }
        alpha = Math.max(0.02f, Math.min(1.0f, alpha));

        Minecraft mc = Minecraft.getInstance();
        int screenW  = mc.getWindow().getGuiScaledWidth();
        int screenH  = mc.getWindow().getGuiScaledHeight();

        int textW = mc.font.width(message);
        int x     = (screenW - textW) / 2;
        int y = (int)(screenH * 0.6);
        int color = ((int)(alpha * 255) & 0xFF) << 24 | 0x00FFFFFF;

        guiGraphics.drawString(mc.font, message, x, y, color, false);
    }
}
