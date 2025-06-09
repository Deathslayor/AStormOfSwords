package net.darkflameproduction.agotmod.client.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.darkflameproduction.agotmod.AGoTMod;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TownNotificationOverlay {

    private static String currentMessage = "";
    private static String currentOwnership = "";
    private static String currentPopulation = "";
    private static CompoundTag currentBannerData = null; // NEW: Store banner data
    private static long messageStartTime = 0;
    private static final long DISPLAY_DURATION = 5000; // 5 seconds in milliseconds
    private static final long FADE_DURATION = 500; // 0.5 seconds fade in/out

    // Banner rendering constants
    private static final int BANNER_WIDTH = 40; // Smaller than CustomGuiScreen's 60
    private static final int BANNER_HEIGHT = 80; // Smaller than CustomGuiScreen's 120
    private static final int BANNER_SPACING = 20; // Space between banners and text

    // Banner texture resources (copied from CustomGuiScreen)
    private static final ResourceLocation BANNER_BASE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png");

    private static final Map<String, ResourceLocation> BANNER_PATTERN_TEXTURES = createBannerPatternMap();

    private static Map<String, ResourceLocation> createBannerPatternMap() {
        Map<String, ResourceLocation> map = new java.util.HashMap<>();
        map.put("base", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png"));
        // Add custom patterns
        map.put("targaryen", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/targaryen.png"));
        // Add vanilla patterns
        map.put("stripe_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_bottom.png"));
        map.put("stripe_top", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_top.png"));
        map.put("stripe_left", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_left.png"));
        map.put("stripe_right", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_right.png"));
        map.put("stripe_center", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_center.png"));
        map.put("stripe_middle", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_middle.png"));
        map.put("cross", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/cross.png"));
        map.put("straight_cross", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/straight_cross.png"));
        map.put("diagonal_left", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/diagonal_left.png"));
        map.put("diagonal_right", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/diagonal_right.png"));
        map.put("half_vertical", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_vertical.png"));
        map.put("half_horizontal", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_horizontal.png"));
        map.put("half_vertical_right", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_vertical_right.png"));
        map.put("half_horizontal_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_horizontal_bottom.png"));
        map.put("circle", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/circle.png"));
        map.put("border", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/border.png"));
        map.put("triangle_top", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/triangle_top.png"));
        map.put("triangle_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/triangle_bottom.png"));
        return java.util.Collections.unmodifiableMap(map);
    }

    @SubscribeEvent
    public static void registerOverlays(RegisterGuiLayersEvent event) {
        event.registerAbove(VanillaGuiLayers.HOTBAR,
                ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_notification"),
                (guiGraphics, deltaTracker) -> renderTownNotification(guiGraphics, deltaTracker));
    }

    /**
     * Show a town entry message with house ownership, population, and banner
     */
    public static void showEntryMessage(String townName, String houseName, int population, CompoundTag bannerData) {
        // Add stack trace to see what's calling this
        System.out.println("DEBUG: TownNotificationOverlay.showEntryMessage called:");
        System.out.println("  - townName: '" + townName + "'");
        System.out.println("  - houseName: '" + houseName + "'");
        System.out.println("  - population: " + population);
        System.out.println("  - bannerData: " + (bannerData != null ? "present" : "null"));

        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        for (int i = 1; i <= Math.min(5, stack.length - 1); i++) {
            System.out.println("    " + stack[i].getClassName() + "." + stack[i].getMethodName() + ":" + stack[i].getLineNumber());
        }

        currentMessage = "You Have Entered " + townName;
        if (houseName != null && !houseName.trim().isEmpty()) {
            currentOwnership = "Owned By House " + houseName;
            System.out.println("DEBUG: Set ownership to: '" + currentOwnership + "'");
        } else {
            currentOwnership = "Unclaimed Territory";
            System.out.println("DEBUG: Set ownership to unclaimed (houseName was: '" + houseName + "')");
        }
        currentPopulation = "Population: " + population;
        currentBannerData = bannerData; // Store banner data
        messageStartTime = System.currentTimeMillis();
        System.out.println("DEBUG: Showing town entry message: " + currentMessage + " | " + currentOwnership + " | " + currentPopulation);
    }

    /**
     * Show a town entry message with house ownership and population (backwards compatibility)
     */
    public static void showEntryMessage(String townName, String houseName, int population) {
        showEntryMessage(townName, houseName, population, null);
    }

    /**
     * Show a town entry message with population (backwards compatibility)
     */
    public static void showEntryMessage(String townName, int population) {
        showEntryMessage(townName, null, population, null);
    }

    /**
     * Show a town entry message without population (backwards compatibility)
     */
    public static void showEntryMessage(String townName) {
        showEntryMessage(townName, null, 0, null);
    }

    /**
     * Show a town exit message
     */
    public static void showExitMessage(String townName) {
        currentMessage = "You Have Left " + townName;
        currentOwnership = ""; // No ownership for exit messages
        currentPopulation = ""; // No population for exit messages
        currentBannerData = null; // No banner for exit messages
        messageStartTime = System.currentTimeMillis();
        System.out.println("DEBUG: Showing town exit message: " + currentMessage);
    }

    /**
     * Clear any current message
     */
    public static void clearMessage() {
        currentMessage = "";
        currentOwnership = "";
        currentPopulation = "";
        currentBannerData = null;
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
     * Render the town notification overlay with banners
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
            currentOwnership = "";
            currentPopulation = "";
            currentBannerData = null;
            return;
        }

        // Check if message should still be displayed (with a small buffer)
        if (elapsed >= DISPLAY_DURATION + 50) { // Add 50ms buffer
            currentMessage = "";
            currentOwnership = "";
            currentPopulation = "";
            currentBannerData = null;
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        // Get screen dimensions
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // Create the text components
        Component messageComponent = Component.literal(currentMessage);
        Component ownershipComponent = currentOwnership.isEmpty() ? null : Component.literal(currentOwnership);
        Component populationComponent = currentPopulation.isEmpty() ? null : Component.literal(currentPopulation);
        Font font = mc.font;

        // Calculate text dimensions
        int messageWidth = font.width(messageComponent);
        int ownershipWidth = ownershipComponent != null ? font.width(ownershipComponent) : 0;
        int populationWidth = populationComponent != null ? font.width(populationComponent) : 0;
        int maxTextWidth = Math.max(messageWidth, Math.max(ownershipWidth, populationWidth));
        int textHeight = font.lineHeight;

        // Calculate total height based on how many lines we have
        int lineCount = 1; // Always have the main message
        if (ownershipComponent != null) lineCount++;
        if (populationComponent != null) lineCount++;
        int totalTextHeight = (textHeight * lineCount) + ((lineCount - 1) * 2); // 2px spacing between lines

        // Calculate total width including banners
        boolean shouldShowBanners = currentBannerData != null && !currentOwnership.equals("Unclaimed Territory");
        int totalWidth = maxTextWidth;
        if (shouldShowBanners) {
            totalWidth = (BANNER_WIDTH * 2) + (BANNER_SPACING * 2) + maxTextWidth; // banners + spacing + text
        }

        // Position at top center of screen
        int centerX = screenWidth / 2;
        int y = 20; // 20 pixels from top

        // Calculate banner and text positions
        int leftBannerX = 0;
        int rightBannerX = 0;
        int textStartX = 0;

        if (shouldShowBanners) {
            leftBannerX = centerX - (totalWidth / 2);
            rightBannerX = centerX + (totalWidth / 2) - BANNER_WIDTH;
            textStartX = leftBannerX + BANNER_WIDTH + BANNER_SPACING;
        } else {
            textStartX = centerX - (maxTextWidth / 2);
        }

        // Create background panel
        int padding = 10;
        int panelX = centerX - (totalWidth / 2) - padding;
        int panelY = y - padding / 2;
        int panelWidth = totalWidth + (padding * 2);
        int panelHeight = Math.max(totalTextHeight, BANNER_HEIGHT) + padding;

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

        // Draw banners if we have banner data
        if (shouldShowBanners) {
            // Calculate banner Y position to center them vertically
            int bannerY = y + ((panelHeight - padding - BANNER_HEIGHT) / 2);

            // Draw left banner
            drawHouseBanner(guiGraphics, leftBannerX, bannerY, alpha);

            // Draw right banner
            drawHouseBanner(guiGraphics, rightBannerX, bannerY, alpha);
        }

        // Calculate text color with alpha
        int textAlpha = Math.max(0, Math.min(255, (int) (255 * alpha))); // Scale text alpha and clamp
        int textColor = (textAlpha << 24) | 0xFFFFFF; // White text with alpha

        // Calculate text Y position to center it vertically
        int textStartY = y + ((panelHeight - padding - totalTextHeight) / 2);

        // Draw the main message (centered in text area)
        int messageX = textStartX + ((maxTextWidth - messageWidth) / 2);
        int currentY = textStartY;
        guiGraphics.drawString(font, messageComponent, messageX, currentY, textColor, false);
        currentY += textHeight + 2; // Move to next line with 2px spacing

        // Draw the ownership text if present (centered in text area)
        if (ownershipComponent != null) {
            int ownershipX = textStartX + ((maxTextWidth - ownershipWidth) / 2);

            // Different color for ownership - blue for owned, red for unclaimed
            int ownershipAlpha = Math.max(0, Math.min(255, (int) (255 * alpha)));
            int ownershipColor;
            if (currentOwnership.equals("Unclaimed Territory")) {
                ownershipColor = (ownershipAlpha << 24) | 0xFF6666; // Light red for unclaimed
            } else {
                ownershipColor = (ownershipAlpha << 24) | 0x66AAFF; // Light blue for owned
            }

            guiGraphics.drawString(font, ownershipComponent, ownershipX, currentY, ownershipColor, false);
            currentY += textHeight + 2; // Move to next line with 2px spacing
        }

        // Draw the population text if present (centered in text area)
        if (populationComponent != null) {
            int populationX = textStartX + ((maxTextWidth - populationWidth) / 2);

            // Population text in green
            int populationAlpha = Math.max(0, Math.min(255, (int) (255 * alpha)));
            int populationColor = (populationAlpha << 24) | 0x66FF66; // Light green for population

            guiGraphics.drawString(font, populationComponent, populationX, currentY, populationColor, false);
        }

        // Disable blending
        RenderSystem.disableBlend();
    }

    /**
     * Draw house banner (adapted from CustomGuiScreen)
     */
    private static void drawHouseBanner(GuiGraphics guiGraphics, int x, int y, float alpha) {
        if (currentBannerData == null) {
            drawNoBannerPlaceholder(guiGraphics, x, y, alpha);
            return;
        }

        try {
            // Create ItemStack from saved NBT data
            ItemStack bannerStack = ItemStack.parseOptional(Minecraft.getInstance().level.registryAccess(), currentBannerData);

            if (bannerStack.isEmpty() || !(bannerStack.getItem() instanceof net.minecraft.world.item.BannerItem)) {
                drawErrorBannerPlaceholder(guiGraphics, x, y, alpha, "Invalid Banner");
                return;
            }

            // Get banner data for rendering
            net.minecraft.world.item.BannerItem bannerItem = (net.minecraft.world.item.BannerItem) bannerStack.getItem();
            net.minecraft.world.item.DyeColor baseColor = bannerItem.getColor();

            // Get patterns from the banner
            BannerPatternLayers patterns = bannerStack.get(DataComponents.BANNER_PATTERNS);

            // Enable blending for proper layer compositing
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            // Save pose state
            guiGraphics.pose().pushPose();

            // Set alpha for banner rendering
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);

            // Layer 1: Draw base banner texture with base color
            drawTextureLayer(guiGraphics, x, y, BANNER_WIDTH, BANNER_HEIGHT, BANNER_BASE_TEXTURE, baseColor);

            // Layer 2+: Apply ALL pattern layers if present
            if (patterns != null && !patterns.layers().isEmpty()) {
                for (BannerPatternLayers.Layer layer : patterns.layers()) {
                    drawPatternLayer(guiGraphics, x, y, BANNER_WIDTH, BANNER_HEIGHT, layer);
                }
            }

            // Restore pose state
            guiGraphics.pose().popPose();

            // Reset render system state
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.disableBlend();

        } catch (Exception e) {
            drawErrorBannerPlaceholder(guiGraphics, x, y, alpha, "Render Error");
        }
    }

    /**
     * Draw texture layer with proper Minecraft-style color tinting
     */
    private static void drawTextureLayer(GuiGraphics guiGraphics, int x, int y, int width, int height,
                                         ResourceLocation texture, net.minecraft.world.item.DyeColor color) {
        // Get the color value for tinting
        int colorValue = color.getTextureDiffuseColor();

        // Draw the texture layer with proper color tinting
        // Sample area from the texture but display it at specified size
        guiGraphics.blit(
                net.minecraft.client.renderer.RenderType::guiTextured,
                texture,
                x, y,                    // destination position
                1.0f, 0.0f,              // source UV start
                width, height,           // destination size
                20, 40,                  // source texture area sampling
                64, 64,                  // source texture size
                colorValue               // Color tint
        );
    }

    /**
     * Draw a single pattern layer
     */
    private static void drawPatternLayer(GuiGraphics guiGraphics, int x, int y, int width, int height,
                                         BannerPatternLayers.Layer layer) {
        // Get pattern resource location
        ResourceLocation patternId = layer.pattern().value().assetId();
        String patternName = patternId.getPath();

        // Get the texture for this pattern
        ResourceLocation patternTexture = BANNER_PATTERN_TEXTURES.get(patternName);
        if (patternTexture == null) {
            // Fallback to vanilla patterns if custom pattern not found
            patternTexture = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/" + patternName + ".png");
        }

        // Draw this pattern layer with its specific color
        drawTextureLayer(guiGraphics, x, y, width, height, patternTexture, layer.color());
    }

    /**
     * Draw placeholder when no banner is set
     */
    private static void drawNoBannerPlaceholder(GuiGraphics guiGraphics, int x, int y, float alpha) {
        int alphaInt = Math.max(0, Math.min(255, (int) (255 * alpha)));

        // Draw placeholder background with banner-like appearance
        guiGraphics.fill(x, y, x + BANNER_WIDTH, y + BANNER_HEIGHT, (alphaInt << 24) | 0x8B4513); // Brown color
        guiGraphics.fill(x + 1, y + 1, x + BANNER_WIDTH - 1, y + BANNER_HEIGHT - 1, (alphaInt << 24) | 0xD2B48C); // Tan color

        // Draw "No Banner" text
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.3f, 0.3f, 1.0f);

        String line1 = "No";
        String line2 = "Banner";
        Font font = Minecraft.getInstance().font;

        int textX1 = (int)((x + BANNER_WIDTH/2 - font.width(line1)/6) / 0.3f);
        int textX2 = (int)((x + BANNER_WIDTH/2 - font.width(line2)/6) / 0.3f);
        int textY1 = (int)((y + BANNER_HEIGHT/2 - 6) / 0.3f);
        int textY2 = (int)((y + BANNER_HEIGHT/2 + 2) / 0.3f);

        int textColor = (alphaInt << 24) | 0x8B0000;
        guiGraphics.drawString(font, line1, textX1, textY1, textColor, false);
        guiGraphics.drawString(font, line2, textX2, textY2, textColor, false);

        guiGraphics.pose().popPose();
    }

    /**
     * Draw error placeholder when banner rendering fails
     */
    private static void drawErrorBannerPlaceholder(GuiGraphics guiGraphics, int x, int y, float alpha, String errorText) {
        int alphaInt = Math.max(0, Math.min(255, (int) (255 * alpha)));

        // Draw error background
        guiGraphics.fill(x, y, x + BANNER_WIDTH, y + BANNER_HEIGHT, (alphaInt << 24) | 0x8B0000); // Dark red
        guiGraphics.fill(x + 1, y + 1, x + BANNER_WIDTH - 1, y + BANNER_HEIGHT - 1, (alphaInt << 24) | 0xFF4500); // Orange red

        // Draw error indicator (X pattern) - scaled appropriately
        int lineThickness = 2;
        guiGraphics.fill(x + 6, y + 10, x + 10, y + BANNER_HEIGHT - 10, (alphaInt << 24) | 0xFFFFFF);
        guiGraphics.fill(x + BANNER_WIDTH - 10, y + 10, x + BANNER_WIDTH - 6, y + BANNER_HEIGHT - 10, (alphaInt << 24) | 0xFFFFFF);

        // Draw diagonal lines for X
        for (int i = 0; i < 28; i++) {
            if (i < BANNER_WIDTH - 16) {
                guiGraphics.fill(x + 6 + i, y + 10 + i, x + 8 + i, y + 12 + i, (alphaInt << 24) | 0xFFFFFF);
                guiGraphics.fill(x + BANNER_WIDTH - 8 - i, y + 10 + i, x + BANNER_WIDTH - 6 - i, y + 12 + i, (alphaInt << 24) | 0xFFFFFF);
            }
        }
    }
}