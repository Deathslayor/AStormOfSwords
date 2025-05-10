package net.darkflameproduction.agotmod.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class CustomGuiScreen extends Screen {
    // Use the correct ResourceLocation constructor format with namespace and path
    private static final ResourceLocation BLUR_LOCATION =
            ResourceLocation.fromNamespaceAndPath("minecraft", "shaders/post/blur.json");

    // Darker, more washed out rainbow colors in ARGB format (with full opacity)
    private static final int[] RAINBOW_COLORS = {
            0xFF990000, // Darker Red
            0xFF994C00, // Darker Orange
            0xFF999900, // Darker Yellow
            0xFF009900, // Darker Green
            0xFF000099, // Darker Blue
            0xFF2D0050, // Darker Indigo
            0xFF57007F  // Darker Violet
    };

    // The words to display in each rectangle
    private static final String[] SECTION_LABELS = {
            "Map",
            "Quests",
            "Skills",
            "Stats",
            "House",
            "Faction",
            "Guilds"
    };

    // Parchment color (a light tan/beige color)
    private static final int PARCHMENT_COLOR = 0xFFF5E7C1;

    // Black color for borders
    private static final int BLACK_COLOR = 0xFF000000;

    // Hover brightness factor (50% brighter)
    private static final float HOVER_BRIGHTNESS = 1.5f;

    private boolean usingShader = false;
    private float lastHealth = 0;

    public CustomGuiScreen() {
        super(Component.translatable("screen.agotmod.custom_gui"));
    }

    @Override
    protected void init() {
        super.init();

        // Store the player's current health when opening the screen
        if (minecraft != null && minecraft.player != null) {
            lastHealth = minecraft.player.getHealth();
        }

        // Apply blur shader immediately at init time
        applyBlurShader();
    }

    private void applyBlurShader() {
        // Try to enable blur shader using various methods
        if (minecraft != null) {
            try {
                // For 1.21.3, let's try different possible method names
                // Method 1: Try to use PostPass directly
                if (minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class) != null) {
                    minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class)
                            .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                    usingShader = true;
                }
            } catch (Exception e1) {
                try {
                    // Method 2: Try alternative method name
                    if (minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class) != null) {
                        minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class)
                                .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                        usingShader = true;
                    }
                } catch (Exception e2) {
                    // If we can't apply blur, just continue without it
                    AGoTMod.LOGGER.error("Could not apply blur shader: " + e2.getMessage());
                }
            }
        }
    }

    // Method to brighten a color for hover effect
    private int brightenColor(int color, float factor) {
        // Extract ARGB components
        int alpha = (color >> 24) & 0xFF;
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;

        // Brighten each component (capped at 255)
        red = Math.min(255, (int)(red * factor));
        green = Math.min(255, (int)(green * factor));
        blue = Math.min(255, (int)(blue * factor));

        // Recombine into ARGB
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    // Method to check if mouse is over a rectangle
    private boolean isMouseOverRect(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // First, render the blurred background by calling the parent class's render method
        // This applies the blur shader effect to the existing game world
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // Check if the player has taken damage
        if (minecraft != null && minecraft.player != null) {
            float currentHealth = minecraft.player.getHealth();

            // If health decreased, close the screen
            if (currentHealth < lastHealth) {
                AGoTMod.LOGGER.info("Player health decreased, closing GUI");
                minecraft.setScreen(null);
                return;
            }

            // Update the last health for next check
            lastHealth = currentHealth;
        }

        // Draw semi-transparent beige background at 25% opacity to darken the blurred game view
        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7); // ARGB: 25% opacity, beige color

        // Calculate the height of the rainbow bar (1/8th of screen height)
        int barHeight = height / 8;

        // Calculate vertical margin (1/32nd of screen height)
        int topMargin = height / 32;

        // Calculate side margins (1/32nd of screen width on each side)
        int sideMargin = width / 32;

        // Calculate the available width for the rectangles
        int availableWidth = width - (2 * sideMargin);

        // Calculate the width of each vertical rectangle
        int rectWidth = availableWidth / 7;

        // Calculate border thickness (1/8th of the rectangle's height)
        int borderThickness = barHeight / 8;

        // Draw 7 equal-sized vertical rectangles with text in rainbow colors
        // At the top of the screen with a margin of 1/32nd of screen height
        for (int i = 0; i < 7; i++) {
            int startX = sideMargin + (i * rectWidth);
            int endX = sideMargin + ((i + 1) * rectWidth);

            // If this is the last rectangle, make sure it extends to the right edge of the rectangles area
            if (i == 6) endX = width - sideMargin;

            // Check if mouse is over this rectangle
            boolean isHovered = isMouseOverRect(mouseX, mouseY, startX, topMargin, endX - startX, barHeight);

            // Get appropriate colors based on hover state
            int rectangleColor = isHovered ? brightenColor(RAINBOW_COLORS[i], HOVER_BRIGHTNESS) : RAINBOW_COLORS[i];
            int borderColor = isHovered ? brightenColor(BLACK_COLOR, HOVER_BRIGHTNESS) : BLACK_COLOR;

            // Draw rectangle with rainbow color (with top margin)
            guiGraphics.fill(startX, topMargin, endX, topMargin + barHeight, rectangleColor);

            // Draw black border inside the rectangle
            // Top border
            guiGraphics.fill(startX, topMargin, endX, topMargin + borderThickness, borderColor);
            // Bottom border
            guiGraphics.fill(startX, topMargin + barHeight - borderThickness, endX, topMargin + barHeight, borderColor);
            // Left border
            guiGraphics.fill(startX, topMargin, startX + borderThickness, topMargin + barHeight, borderColor);
            // Right border
            guiGraphics.fill(endX - borderThickness, topMargin, endX, topMargin + barHeight, borderColor);

            // Calculate text position - centered in each rectangle
            String text = SECTION_LABELS[i];
            int textWidth = font.width(text);
            int textX = startX + ((endX - startX) - textWidth) / 2;
            int textY = topMargin + (barHeight - font.lineHeight) / 2; // Centered vertically in the bar

            // Draw text with parchment color (text stays the same regardless of hover)
            guiGraphics.drawString(font, text, textX, textY, PARCHMENT_COLOR);
        }
    }

    @Override
    public void onClose() {
        super.onClose();

        // Try to remove blur shader if it was applied
        if (minecraft != null && usingShader) {
            try {
                // Try different potential method names for resetting shaders
                try {
                    if (minecraft.gameRenderer.getClass().getMethod("clearPostPass") != null) {
                        minecraft.gameRenderer.getClass().getMethod("clearPostPass")
                                .invoke(minecraft.gameRenderer);
                    }
                } catch (Exception e1) {
                    try {
                        if (minecraft.gameRenderer.getClass().getMethod("clearPostShader") != null) {
                            minecraft.gameRenderer.getClass().getMethod("clearPostShader")
                                    .invoke(minecraft.gameRenderer);
                        }
                    } catch (Exception e2) {
                        // If we can't clear the shader, log the error
                        AGoTMod.LOGGER.error("Could not clear post shader: " + e2.getMessage());
                    }
                }
            } catch (Exception e) {
                AGoTMod.LOGGER.error("Error when trying to clear shader: " + e.getMessage());
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        // Check if player has taken damage during the tick
        if (minecraft != null && minecraft.player != null) {
            float currentHealth = minecraft.player.getHealth();

            // If health decreased, close the screen
            if (currentHealth < lastHealth) {
                AGoTMod.LOGGER.info("Player health decreased, closing GUI");
                minecraft.setScreen(null);
                return;
            }

            // Update the last health for next check
            lastHealth = currentHealth;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Don't pause the game when this screen is open
    }
}