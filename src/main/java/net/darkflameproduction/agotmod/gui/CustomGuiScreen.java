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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatsCounter;
import net.minecraft.world.entity.player.Player;
import org.joml.Matrix4f;

import java.util.concurrent.CompletableFuture;

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

    // Death count stat
    private static final net.minecraft.stats.Stat<ResourceLocation> DEATH_COUNT_STAT =
            Stats.CUSTOM.get(Stats.DEATHS);

    // Additional stat resources for common statistics
    private static final Stat<ResourceLocation> KILLS_MOB_STAT =
            Stats.CUSTOM.get(Stats.MOB_KILLS);
    private static final Stat<ResourceLocation> PLAYER_KILLS_STAT =
            Stats.CUSTOM.get(Stats.PLAYER_KILLS);
    private static final Stat<ResourceLocation> JUMP_STAT =
            Stats.CUSTOM.get(Stats.JUMP);

    // All distance-related stats for combined "Distance Traveled"
    private static final Stat<ResourceLocation> DISTANCE_CLIMB =
            Stats.CUSTOM.get(Stats.CLIMB_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_CROUCH =
            Stats.CUSTOM.get(Stats.CROUCH_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_FALL =
            Stats.CUSTOM.get(Stats.FALL_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_FLY =
            Stats.CUSTOM.get(Stats.FLY_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_SPRINT =
            Stats.CUSTOM.get(Stats.SPRINT_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_SWIM =
            Stats.CUSTOM.get(Stats.SWIM_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_WALK =
            Stats.CUSTOM.get(Stats.WALK_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_WALK_ON_WATER =
            Stats.CUSTOM.get(Stats.WALK_ON_WATER_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_WALK_UNDER_WATER =
            Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_BOAT =
            Stats.CUSTOM.get(Stats.BOAT_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_AVIATE =
            Stats.CUSTOM.get(Stats.AVIATE_ONE_CM); // Elytra
    private static final Stat<ResourceLocation> DISTANCE_HORSE =
            Stats.CUSTOM.get(Stats.HORSE_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_MINECART =
            Stats.CUSTOM.get(Stats.MINECART_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_PIG =
            Stats.CUSTOM.get(Stats.PIG_ONE_CM);
    private static final Stat<ResourceLocation> DISTANCE_STRIDER =
            Stats.CUSTOM.get(Stats.STRIDER_ONE_CM);

    private boolean usingShader = false;
    private float lastHealth = 0;

    // Track the currently selected section (default to Skills - index 2)
    private static int lastSelectedSection = 2;
    private int selectedSection;

    // Cache for statistics values
    private int cachedDeathCount = 0;
    private int cachedMobKills = 0;
    private int cachedPlayerKills = 0;
    private int cachedJumps = 0;
    private double cachedTotalDistance = 0.0; // Total distance in kilometers with decimals

    // Flag to track if stats have been loaded
    private boolean statsLoaded = false;

    public CustomGuiScreen() {
        super(Component.translatable("screen.agotmod.custom_gui"));
        // Use the last selected section when opening the menu
        this.selectedSection = lastSelectedSection;
    }

    @Override
    protected void init() {
        super.init();

        // Store the player's current health when opening the screen
        if (minecraft != null && minecraft.player != null) {
            lastHealth = minecraft.player.getHealth();

            // Force load the player's statistics when the screen is opened
            loadPlayerStats();
        }

        // Apply blur shader immediately at init time
        applyBlurShader();
    }

    /**
     * Force loads the player's statistics when the GUI is opened
     * This ensures stats are available without opening the vanilla stats menu
     */
    private void loadPlayerStats() {
        if (minecraft != null && minecraft.player != null) {
            // Get reference to the stats counter
            StatsCounter stats = minecraft.player.getStats();

            // Force the stats to be loaded from the server if they haven't been yet
            // This is the key part - we're ensuring stats are loaded when our GUI opens
            if (!statsLoaded) {
                try {
                    // Try to force a stats request from the server
                    // This mimics what happens when the vanilla stats screen opens
                    if (minecraft.getConnection() != null) {
                        // Request stats from server - in modern versions this happens automatically
                        // when the stats screen opens, we're just triggering it manually
                        minecraft.getConnection().send(new net.minecraft.network.protocol.game.ServerboundClientCommandPacket(
                                net.minecraft.network.protocol.game.ServerboundClientCommandPacket.Action.REQUEST_STATS));

                        AGoTMod.LOGGER.info("Requested player statistics from server");

                        // Update our cached values
                        updateCachedStats(stats);

                        // Mark stats as loaded
                        statsLoaded = true;
                    }
                } catch (Exception e) {
                    AGoTMod.LOGGER.error("Failed to request stats from server: " + e.getMessage());
                    // Even if an error occurs, try to update cached values with what we can get
                    updateCachedStats(stats);
                }
            } else {
                // If stats are already loaded, just update the cached values
                updateCachedStats(stats);
            }
        }
    }

    /**
     * Updates the cached statistic values from the player's stats counter
     */
    private void updateCachedStats(StatsCounter stats) {
        if (stats != null) {
            cachedDeathCount = stats.getValue(DEATH_COUNT_STAT);
            cachedMobKills = stats.getValue(KILLS_MOB_STAT);
            cachedPlayerKills = stats.getValue(PLAYER_KILLS_STAT);
            cachedJumps = stats.getValue(JUMP_STAT);

            // Calculate total distance traveled across all movement types (in centimeters)
            long totalDistanceCm = 0;
            totalDistanceCm += stats.getValue(DISTANCE_CLIMB);
            totalDistanceCm += stats.getValue(DISTANCE_CROUCH);
            totalDistanceCm += stats.getValue(DISTANCE_FALL);
            totalDistanceCm += stats.getValue(DISTANCE_FLY);
            totalDistanceCm += stats.getValue(DISTANCE_SPRINT);
            totalDistanceCm += stats.getValue(DISTANCE_SWIM);
            totalDistanceCm += stats.getValue(DISTANCE_WALK);
            totalDistanceCm += stats.getValue(DISTANCE_WALK_ON_WATER);
            totalDistanceCm += stats.getValue(DISTANCE_WALK_UNDER_WATER);
            totalDistanceCm += stats.getValue(DISTANCE_BOAT);
            totalDistanceCm += stats.getValue(DISTANCE_AVIATE);
            totalDistanceCm += stats.getValue(DISTANCE_HORSE);
            totalDistanceCm += stats.getValue(DISTANCE_MINECART);
            totalDistanceCm += stats.getValue(DISTANCE_PIG);
            totalDistanceCm += stats.getValue(DISTANCE_STRIDER);

            // Convert centimeters to kilometers
            cachedTotalDistance = totalDistanceCm / 100000.0; // 100,000 cm = 1 km

            AGoTMod.LOGGER.debug("Updated cached stats - deaths: " + cachedDeathCount +
                    ", total distance: " + cachedTotalDistance + " km");
        }
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
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        // Calculate UI dimensions similar to render method
        int barHeight = height / 8;
        int topMargin = height / 32;
        int sideMargin = width / 32;
        int availableWidth = width - (2 * sideMargin);
        int rectWidth = availableWidth / 7;

        // Check if any section button was clicked
        for (int i = 0; i < 7; i++) {
            int startX = sideMargin + (i * rectWidth);
            int endX = sideMargin + ((i + 1) * rectWidth);

            // If this is the last rectangle, make sure it extends to the right edge
            if (i == 6) endX = width - sideMargin;

            // Check if click was within this button
            if (isMouseOverRect((int)mouseX, (int)mouseY, startX, topMargin, endX - startX, barHeight)) {
                // Always select the clicked section, no toggle/deselect functionality
                selectedSection = i;
                // Update the static variable to remember this selection
                lastSelectedSection = i;

                // If stats tab was selected and stats aren't loaded yet, try to load them
                if (i == 3 && !statsLoaded && minecraft != null) {
                    loadPlayerStats();
                }

                // Play the minecraft button click sound
                if (minecraft != null && minecraft.player != null) {
                    // Play button click sound - same as main menu buttons
                    minecraft.getSoundManager().play(
                            net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                                    SoundEvents.UI_BUTTON_CLICK, 1.0F
                            )
                    );
                }

                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
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

            // Check if mouse is over this rectangle or if it's the selected section
            boolean isHovered = isMouseOverRect(mouseX, mouseY, startX, topMargin, endX - startX, barHeight);
            boolean isSelected = (i == selectedSection);

            // Get appropriate colors based on hover/selected state
            int rectangleColor;
            int borderColor;

            if (isSelected) {
                // Selected state takes precedence over hover
                rectangleColor = brightenColor(RAINBOW_COLORS[i], HOVER_BRIGHTNESS);
                borderColor = PARCHMENT_COLOR; // Use parchment color for border when selected
            } else if (isHovered) {
                rectangleColor = brightenColor(RAINBOW_COLORS[i], HOVER_BRIGHTNESS);
                borderColor = brightenColor(BLACK_COLOR, HOVER_BRIGHTNESS);
            } else {
                rectangleColor = RAINBOW_COLORS[i];
                borderColor = BLACK_COLOR;
            }

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

        // If a section is selected, draw the center rectangle with the corresponding color
        if (selectedSection >= 0 && selectedSection < RAINBOW_COLORS.length) {
            // Make center rectangle the full width of the top bar
            int centerRectX = sideMargin; // Same as the left edge of the top bar
            int centerRectWidth = width - (2 * sideMargin); // Same width as the top bar

            // Height: From below the tabs to 1/32 from the bottom of the screen
            int centerRectHeight = height - topMargin - barHeight - (height / 32);

            // Position it right below the tab bar
            int centerRectY = topMargin + barHeight + 5; // Just below the tab bar with a small gap

            // Draw center rectangle with selected color (70% opacity)
            int centerColor = (RAINBOW_COLORS[selectedSection] & 0x00FFFFFF) | 0xB0000000;
            guiGraphics.fill(centerRectX, centerRectY, centerRectX + centerRectWidth, centerRectY + centerRectHeight, centerColor);

            // Draw border around center rectangle
            int centerBorderThickness = centerRectHeight / 16; // Thinner border
            int centerBorderColor = BLACK_COLOR;

            // Draw borders
            // Top border
            guiGraphics.fill(centerRectX, centerRectY,
                    centerRectX + centerRectWidth, centerRectY + centerBorderThickness,
                    centerBorderColor);
            // Bottom border
            guiGraphics.fill(centerRectX, centerRectY + centerRectHeight - centerBorderThickness,
                    centerRectX + centerRectWidth, centerRectY + centerRectHeight,
                    centerBorderColor);
            // Left border
            guiGraphics.fill(centerRectX, centerRectY,
                    centerRectX + centerBorderThickness, centerRectY + centerRectHeight,
                    centerBorderColor);
            // Right border
            guiGraphics.fill(centerRectX + centerRectWidth - centerBorderThickness, centerRectY,
                    centerRectX + centerRectWidth, centerRectY + centerRectHeight,
                    centerBorderColor);

            // Center the section title properly based on the full width
            String sectionTitle = SECTION_LABELS[selectedSection];
            int titleWidth = (int)(font.width(sectionTitle) * 1.5f); // Account for the 1.5x scale
            int titleX = centerRectX + (centerRectWidth - titleWidth) / 2;
            int titleY = centerRectY + 10; // Position near the top of the content area

            // Draw section title with parchment color (slightly larger)
            float titleScale = 1.5f;
            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(titleScale, titleScale, 1.0f);
            guiGraphics.drawString(font, sectionTitle,
                    (int)(titleX / titleScale),
                    (int)(titleY / titleScale),
                    PARCHMENT_COLOR);
            guiGraphics.pose().popPose();

            // If Stats section is selected, display the player's statistics
            if (selectedSection == 3) { // Stats section (index 3)
                // If stats haven't been loaded yet, try to load them now
                if (!statsLoaded && minecraft != null) {
                    loadPlayerStats();
                }

                // Spacing between stat lines
                int lineSpacing = 20;

                // Base Y position for the first stat
                int statsY = centerRectY + 50;

                // Display death count
                drawCenteredStat(guiGraphics, "Deaths", cachedDeathCount, centerRectX, centerRectWidth, statsY);

                // Display mob kills
                drawCenteredStat(guiGraphics, "Mob Kills", cachedMobKills, centerRectX, centerRectWidth, statsY + lineSpacing);

                // Display player kills
                drawCenteredStat(guiGraphics, "Player Kills", cachedPlayerKills, centerRectX, centerRectWidth, statsY + lineSpacing * 2);

                // Display jump count
                drawCenteredStat(guiGraphics, "Jumps", cachedJumps, centerRectX, centerRectWidth, statsY + lineSpacing * 3);

                // Display total distance traveled (in kilometers with 2 decimal places)
                drawCenteredDistanceStat(guiGraphics, "Distance Traveled", cachedTotalDistance, centerRectX, centerRectWidth, statsY + lineSpacing * 4);

                // Additional stats can be added here
            }
        }
    }

    /**
     * Helper method to draw a stat with label and value centered in the content area
     */
    private void drawCenteredStat(GuiGraphics guiGraphics, String label, int value, int rectX, int rectWidth, int y) {
        String text = label + ": " + value;
        int textWidth = font.width(text);
        int textX = rectX + (rectWidth - textWidth) / 2;

        // Draw the stat text with parchment color
        guiGraphics.drawString(font, text, textX, y, PARCHMENT_COLOR);
    }

    /**
     * Helper method to draw the distance stat with label and formatted value centered in the content area
     * Formats the distance in kilometers with 2 decimal places
     */
    private void drawCenteredDistanceStat(GuiGraphics guiGraphics, String label, double valueKm, int rectX, int rectWidth, int y) {
        // Format the distance to show 2 decimal places
        String formattedDistance = String.format("%.2f", valueKm);
        String text = label + ": " + formattedDistance + " km";
        int textWidth = font.width(text);
        int textX = rectX + (rectWidth - textWidth) / 2;

        // Draw the stat text with parchment color
        guiGraphics.drawString(font, text, textX, y, PARCHMENT_COLOR);
    }

    @Override
    public void onClose() {
        super.onClose();

        // Save the currently selected section to the static variable
        lastSelectedSection = selectedSection;

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

            // Periodically update stats while the GUI is open
            // This ensures stats stay fresh if the player earns achievements while the GUI is open
            if (selectedSection == 3 && minecraft.player.getStats() != null) {
                updateCachedStats(minecraft.player.getStats());
            }
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false; // Don't pause the game when this screen is open
    }
}