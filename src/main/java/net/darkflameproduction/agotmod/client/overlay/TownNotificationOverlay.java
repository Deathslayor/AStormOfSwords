package net.darkflameproduction.agotmod.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.darkflameproduction.agotmod.AGoTMod;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TownNotificationOverlay {

    private static String currentMessage = "";
    private static String currentPopulation = "";
    private static long messageStartTime = 0;
    private static final long DISPLAY_DURATION = 5000; // 5 seconds in milliseconds
    private static final long FADE_DURATION = 500; // 0.5 seconds fade in/out

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR,
                ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_notification"),
                (guiGraphics, deltaTracker) -> renderTownNotification(guiGraphics, deltaTracker));
    }

    /**
     * Show a town entry message with population
     */
    public static void showEntryMessage(String townName, int population) {
        currentMessage = "You Have Entered " + townName;
        currentPopulation = "Population: " + population;
        messageStartTime = System.currentTimeMillis();
        System.out.println("DEBUG: Showing town entry message: " + currentMessage + " (" + currentPopulation + ")");
    }

    /**
     * Show a town entry message without population (backwards compatibility)
     */
    public static void showEntryMessage(String townName) {
        showEntryMessage(townName, 0);
    }

    /**
     * Show a town exit message
     */
    public static void showExitMessage(String townName) {
        currentMessage = "You Have Left " + townName;
        currentPopulation = ""; // No population for exit messages
        messageStartTime = System.currentTimeMillis();
        System.out.println("DEBUG: Showing town exit message: " + currentMessage);
    }

    /**
     * Clear any current message
     */
    public static void clearMessage() {
        currentMessage = "";
        currentPopulation = "";
        messageStartTime = 0;
    }

    /**
     * Check if a message is currently being displayed
     */
    public static boolean isShowingMessage() {
        if (currentMessage.isEmpty()) return false;

        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - messageStartTime;
        return elapsed < DISPLAY_DURATION;
    }

    /**
     * Render the town notification overlay
     */
    private static void renderTownNotification(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {
        if (currentMessage.isEmpty()) return;

        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - messageStartTime;

        // Calculate alpha for fade in/out effect
        float alpha = 1.0f;
        if (elapsed < FADE_DURATION) {
            // Fade in
            alpha = (float) elapsed / FADE_DURATION;
        } else if (elapsed > DISPLAY_DURATION - FADE_DURATION) {
            // Fade out
            long fadeOutElapsed = elapsed - (DISPLAY_DURATION - FADE_DURATION);
            alpha = 1.0f - ((float) fadeOutElapsed / FADE_DURATION);
        }

        // Ensure alpha is within bounds and clamp to prevent flicker
        alpha = Math.max(0.0f, Math.min(1.0f, alpha));

        // If alpha is essentially 0, clear the message to prevent flicker
        if (alpha <= 0.01f) {
            currentMessage = "";
            currentPopulation = "";
            return;
        }

        // Check if message should still be displayed (with a small buffer)
        if (elapsed >= DISPLAY_DURATION + 50) { // Add 50ms buffer
            currentMessage = "";
            currentPopulation = "";
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Get screen dimensions
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // Create the text components
        Component messageComponent = Component.literal(currentMessage);
        Component populationComponent = currentPopulation.isEmpty() ? null : Component.literal(currentPopulation);
        Font font = mc.font;

        // Calculate text dimensions
        int messageWidth = font.width(messageComponent);
        int populationWidth = populationComponent != null ? font.width(populationComponent) : 0;
        int maxWidth = Math.max(messageWidth, populationWidth);
        int textHeight = font.lineHeight;
        int totalHeight = populationComponent != null ? textHeight * 2 + 2 : textHeight; // 2px spacing between lines

        // Position at top center of screen
        int x = (screenWidth - maxWidth) / 2;
        int y = 20; // 20 pixels from top

        // Create background panel
        int padding = 10;
        int panelX = x - padding;
        int panelY = y - padding / 2;
        int panelWidth = maxWidth + (padding * 2);
        int panelHeight = totalHeight + padding;

        // Enable blending for transparency
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // Draw background panel with transparency
        int bgAlpha = Math.max(0, Math.min(255, (int) (128 * alpha))); // Scale background alpha and clamp
        int finalBgColor = (bgAlpha << 24) | 0x000000; // Pure black background
        guiGraphics.fill(panelX, panelY, panelX + panelWidth, panelY + panelHeight, finalBgColor);

        // Draw border
        int borderAlpha = Math.max(0, Math.min(255, (int) (128 * alpha))); // Scale border alpha and clamp
        int finalBorderColor = (borderAlpha << 24) | 0xFFFFFF; // Pure white border

        // Top border
        guiGraphics.fill(panelX, panelY, panelX + panelWidth, panelY + 1, finalBorderColor);
        // Bottom border
        guiGraphics.fill(panelX, panelY + panelHeight - 1, panelX + panelWidth, panelY + panelHeight, finalBorderColor);
        // Left border
        guiGraphics.fill(panelX, panelY, panelX + 1, panelY + panelHeight, finalBorderColor);
        // Right border
        guiGraphics.fill(panelX + panelWidth - 1, panelY, panelX + panelWidth, panelY + panelHeight, finalBorderColor);

        // Calculate text color with alpha
        int textAlpha = Math.max(0, Math.min(255, (int) (255 * alpha))); // Scale text alpha and clamp
        int textColor = (textAlpha << 24) | 0xFFFFFF; // White text with alpha

        // Draw the main message (centered)
        int messageX = (screenWidth - messageWidth) / 2;
        guiGraphics.drawString(font, messageComponent, messageX, y, textColor, false);

        // Draw the population text if present (centered, below main message)
        if (populationComponent != null) {
            int populationX = (screenWidth - populationWidth) / 2;
            int populationY = y + textHeight + 2; // 2px spacing

            // Population text in slightly smaller alpha for visual hierarchy
            int populationAlpha = Math.max(0, Math.min(255, (int) (200 * alpha))); // Slightly dimmer
            int populationColor = (populationAlpha << 24) | 0xFFFFFF;

            guiGraphics.drawString(font, populationComponent, populationX, populationY, populationColor, false);
        }

        // Disable blending
        RenderSystem.disableBlend();
    }
}