package net.darkflameproduction.agotmod.gui;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatsCounter;


public class CustomGuiScreen extends Screen {
    private static final ResourceLocation BLUR_LOCATION =
            ResourceLocation.fromNamespaceAndPath("minecraft", "shaders/post/blur.json");

    private static final int[] RAINBOW_COLORS = {
            0xFF990000,
            0xFF994C00,
            0xFF999900,
            0xFF009900,
            0xFF000099,
            0xFF2D0050,
            0xFF57007F
    };

    private static final String[] SECTION_LABELS = {
            "Map",
            "Quests",
            "Skills",
            "Stats",
            "House",
            "Faction",
            "Guilds"
    };

    private static final String[] STAT_SUBMENU_LABELS = {
            "General Stats",
            "Combat Stats",
            "Crime",
            "Magic",
            "Weapon Usage",
            "Tool Usage"
    };

    private static final int PARCHMENT_COLOR = 0xFFF5E7C1;
    private static final int BLACK_COLOR = 0xFF000000;
    private static final float HOVER_BRIGHTNESS = 1.5f;
    private static final net.minecraft.stats.Stat<ResourceLocation> DEATH_COUNT_STAT =
            Stats.CUSTOM.get(Stats.DEATHS);
    private static final Stat<ResourceLocation> KILLS_MOB_STAT =
            Stats.CUSTOM.get(Stats.MOB_KILLS);
    private static final Stat<ResourceLocation> PLAYER_KILLS_STAT =
            Stats.CUSTOM.get(Stats.PLAYER_KILLS);
    private static final Stat<ResourceLocation> JUMP_STAT =
            Stats.CUSTOM.get(Stats.JUMP);
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
            Stats.CUSTOM.get(Stats.AVIATE_ONE_CM);
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
    private static int lastSelectedSection = 2;
    private int selectedSection;
    private static int lastSelectedStatsSubmenu = 0;
    private int selectedStatsSubmenu;
    private int cachedDeathCount = 0;
    private int cachedMobKills = 0;
    private int cachedPlayerKills = 0;
    private int cachedJumps = 0;
    private double cachedTotalDistance = 0.0;
    private boolean statsLoaded = false;

    public CustomGuiScreen() {
        super(Component.translatable("screen.agotmod.custom_gui"));
        this.selectedSection = lastSelectedSection;
        this.selectedStatsSubmenu = lastSelectedStatsSubmenu;
    }

    @Override
    protected void init() {
        super.init();
        if (minecraft != null && minecraft.player != null) {
            lastHealth = minecraft.player.getHealth();
            loadPlayerStats();
        }
        applyBlurShader();
    }

    private void loadPlayerStats() {
        if (minecraft != null && minecraft.player != null) {
            StatsCounter stats = minecraft.player.getStats();
            if (!statsLoaded) {
                try {
                    if (minecraft.getConnection() != null) {
                        minecraft.getConnection().send(new net.minecraft.network.protocol.game.ServerboundClientCommandPacket(
                                net.minecraft.network.protocol.game.ServerboundClientCommandPacket.Action.REQUEST_STATS));
                        AGoTMod.LOGGER.info("Requested player statistics from server");
                        updateCachedStats(stats);
                        statsLoaded = true;
                    }
                } catch (Exception e) {
                    AGoTMod.LOGGER.error("Failed to request stats from server: " + e.getMessage());
                    updateCachedStats(stats);
                }
            } else {
                updateCachedStats(stats);
            }
        }
    }

    private void updateCachedStats(StatsCounter stats) {
        if (stats != null) {
            cachedDeathCount = stats.getValue(DEATH_COUNT_STAT);
            cachedMobKills = stats.getValue(KILLS_MOB_STAT);
            cachedPlayerKills = stats.getValue(PLAYER_KILLS_STAT);
            cachedJumps = stats.getValue(JUMP_STAT);

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

            cachedTotalDistance = totalDistanceCm / 100000.0;
        }
    }

    private void applyBlurShader() {
        if (minecraft != null) {
            try {
                if (minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class) != null) {
                    minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class)
                            .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                    usingShader = true;
                }
            } catch (Exception e1) {
                try {
                    if (minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class) != null) {
                        minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class)
                                .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                        usingShader = true;
                    }
                } catch (Exception e2) {
                    AGoTMod.LOGGER.error("Could not apply blur shader: " + e2.getMessage());
                }
            }
        }
    }

    private int brightenColor(int color, float factor) {
        int alpha = (color >> 24) & 0xFF;
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;

        red = Math.min(255, (int) (red * factor));
        green = Math.min(255, (int) (green * factor));
        blue = Math.min(255, (int) (blue * factor));

        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    private boolean isMouseOverRect(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int barHeight = height / 8;
        int topMargin = height / 32;
        int sideMargin = width / 32;
        int availableWidth = width - (2 * sideMargin);
        int rectWidth = availableWidth / 7;

        for (int i = 0; i < 7; i++) {
            int startX = sideMargin + (i * rectWidth);
            int endX = sideMargin + ((i + 1) * rectWidth);

            if (i == 6) endX = width - sideMargin;

            if (isMouseOverRect((int) mouseX, (int) mouseY, startX, topMargin, endX - startX, barHeight)) {
                selectedSection = i;
                lastSelectedSection = i;

                if (i == 3 && !statsLoaded && minecraft != null) {
                    loadPlayerStats();
                }

                if (minecraft != null && minecraft.player != null) {
                    minecraft.getSoundManager().play(
                            net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                                    SoundEvents.UI_BUTTON_CLICK, 1.0F
                            )
                    );
                }

                return true;
            }
        }

        if (selectedSection == 3) {
            // Calculate left menu area dimensions based on proportions
            int centerRectX = sideMargin;
            int centerRectY = topMargin + barHeight + 5;
            int centerRectWidth = width - (2 * sideMargin);
            int centerRectHeight = height - topMargin - barHeight - (height / 32);

            // Calculate the same proportions as in render method for consistency
            int leftMenuX = centerRectX + centerRectWidth / 32;
            int leftMenuY = centerRectY + centerRectHeight / 8;
            int leftMenuWidth = centerRectWidth / 7;
            int leftMenuHeight = (int)(centerRectHeight * 0.75);

            // Calculate button dimensions to match render method
            int buttonSpacing = centerRectHeight / 100;
            int submenuButtonHeight = (leftMenuHeight - ((STAT_SUBMENU_LABELS.length - 1) * buttonSpacing)) / STAT_SUBMENU_LABELS.length;

            // Check each submenu button
            for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
                int buttonY = leftMenuY + (i * (submenuButtonHeight + buttonSpacing));

                if (isMouseOverRect((int) mouseX, (int) mouseY, leftMenuX, buttonY,
                        leftMenuWidth, submenuButtonHeight)) {
                    selectedStatsSubmenu = i;
                    lastSelectedStatsSubmenu = i;

                    if (minecraft != null && minecraft.player != null) {
                        minecraft.getSoundManager().play(
                                net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                                        SoundEvents.UI_BUTTON_CLICK, 1.0F
                                )
                        );
                    }

                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (minecraft != null && minecraft.player != null) {
            float currentHealth = minecraft.player.getHealth();

            if (currentHealth < lastHealth) {
                AGoTMod.LOGGER.info("Player health decreased, closing GUI");
                minecraft.setScreen(null);
                return;
            }

            lastHealth = currentHealth;
        }

        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7);

        int barHeight = height / 8;
        int topMargin = height / 32;
        int sideMargin = width / 32;
        int availableWidth = width - (2 * sideMargin);
        int rectWidth = availableWidth / 7;
        int borderThickness = barHeight / 8;

        for (int i = 0; i < 7; i++) {
            int startX = sideMargin + (i * rectWidth);
            int endX = sideMargin + ((i + 1) * rectWidth);

            if (i == 6) endX = width - sideMargin;

            boolean isHovered = isMouseOverRect(mouseX, mouseY, startX, topMargin, endX - startX, barHeight);
            boolean isSelected = (i == selectedSection);

            int rectangleColor;
            int borderColor;

            if (isSelected) {
                rectangleColor = brightenColor(RAINBOW_COLORS[i], HOVER_BRIGHTNESS);
                borderColor = PARCHMENT_COLOR;
            } else if (isHovered) {
                rectangleColor = brightenColor(RAINBOW_COLORS[i], HOVER_BRIGHTNESS);
                borderColor = brightenColor(BLACK_COLOR, HOVER_BRIGHTNESS);
            } else {
                rectangleColor = RAINBOW_COLORS[i];
                borderColor = BLACK_COLOR;
            }

            guiGraphics.fill(startX, topMargin, endX, topMargin + barHeight, rectangleColor);
            guiGraphics.fill(startX, topMargin, endX, topMargin + borderThickness, borderColor);
            guiGraphics.fill(startX, topMargin + barHeight - borderThickness, endX, topMargin + barHeight, borderColor);
            guiGraphics.fill(startX, topMargin, startX + borderThickness, topMargin + barHeight, borderColor);
            guiGraphics.fill(endX - borderThickness, topMargin, endX, topMargin + barHeight, borderColor);

            String text = SECTION_LABELS[i];
            int textWidth = font.width(text);
            int textX = startX + ((endX - startX) - textWidth) / 2;
            int textY = topMargin + (barHeight - font.lineHeight) / 2;

            guiGraphics.drawString(font, text, textX, textY, PARCHMENT_COLOR);
        }

        if (selectedSection >= 0 && selectedSection < RAINBOW_COLORS.length) {
            int centerRectX = sideMargin;
            int centerRectWidth = width - (2 * sideMargin);
            int centerRectHeight = height - topMargin - barHeight - (height / 32);
            int centerRectY = topMargin + barHeight + 5;

            int centerColor = (RAINBOW_COLORS[selectedSection] & 0x00FFFFFF) | 0xB0000000;
            guiGraphics.fill(centerRectX, centerRectY, centerRectX + centerRectWidth, centerRectY + centerRectHeight, centerColor);

            int centerBorderThickness = centerRectHeight / 16;
            int centerBorderColor = BLACK_COLOR;

            guiGraphics.fill(centerRectX, centerRectY,
                    centerRectX + centerRectWidth, centerRectY + centerBorderThickness,
                    centerBorderColor);
            guiGraphics.fill(centerRectX, centerRectY + centerRectHeight - centerBorderThickness,
                    centerRectX + centerRectWidth, centerRectY + centerRectHeight,
                    centerBorderColor);
            guiGraphics.fill(centerRectX, centerRectY,
                    centerRectX + centerBorderThickness, centerRectY + centerRectHeight,
                    centerBorderColor);
            guiGraphics.fill(centerRectX + centerRectWidth - centerBorderThickness, centerRectY,
                    centerRectX + centerRectWidth, centerRectY + centerRectHeight,
                    centerBorderColor);

            String sectionTitle = SECTION_LABELS[selectedSection];
            int titleWidth = (int) (font.width(sectionTitle) * 1.5f);
            int titleX = centerRectX + (centerRectWidth - titleWidth) / 2;
            int titleY = centerRectY + 10;

            float titleScale = 1.5f;
            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(titleScale, titleScale, 1.0f);
            guiGraphics.drawString(font, sectionTitle,
                    (int) (titleX / titleScale),
                    (int) (titleY / titleScale),
                    PARCHMENT_COLOR);
            guiGraphics.pose().popPose();

            if (selectedSection == 3) {
                if (!statsLoaded && minecraft != null) {
                    loadPlayerStats();
                }

                int leftMenuX = centerRectX + centerRectWidth / 32; // Changed from /16 to /32
                int leftMenuY = centerRectY + centerRectHeight / 8;
                int leftMenuWidth = centerRectWidth / 7;
                int leftMenuHeight = (int)(centerRectHeight * 0.75);

                int contentAreaX = leftMenuX + leftMenuWidth + centerRectWidth / 64;
                int contentAreaY = leftMenuY;
                int contentAreaWidth = (int)(centerRectWidth * 0.75);
                int contentAreaHeight = leftMenuHeight;

                int buttonSpacing = centerRectHeight / 100;
                int submenuButtonHeight = (leftMenuHeight - ((STAT_SUBMENU_LABELS.length - 1) * buttonSpacing)) / STAT_SUBMENU_LABELS.length;

                for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
                    int buttonY = leftMenuY + (i * (submenuButtonHeight + buttonSpacing));

                    boolean isHovered = isMouseOverRect(mouseX, mouseY, leftMenuX, buttonY,
                            leftMenuWidth, submenuButtonHeight);
                    boolean isSelected = (i == selectedStatsSubmenu);

                    int buttonColor;
                    int buttonBorderColor;

                    if (isSelected) {
                        buttonColor = brightenColor(RAINBOW_COLORS[3], HOVER_BRIGHTNESS);
                        buttonBorderColor = PARCHMENT_COLOR;
                    } else if (isHovered) {
                        buttonColor = brightenColor(RAINBOW_COLORS[3], 1.2f);
                        buttonBorderColor = brightenColor(BLACK_COLOR, HOVER_BRIGHTNESS);
                    } else {
                        buttonColor = RAINBOW_COLORS[3];
                        buttonBorderColor = BLACK_COLOR;
                    }

                    guiGraphics.fill(leftMenuX, buttonY,
                            leftMenuX + leftMenuWidth, buttonY + submenuButtonHeight,
                            buttonColor);

                    int subBorderThickness = submenuButtonHeight / 8;
                    guiGraphics.fill(leftMenuX, buttonY,
                            leftMenuX + leftMenuWidth, buttonY + subBorderThickness,
                            buttonBorderColor);
                    guiGraphics.fill(leftMenuX, buttonY + submenuButtonHeight - subBorderThickness,
                            leftMenuX + leftMenuWidth, buttonY + submenuButtonHeight,
                            buttonBorderColor);
                    guiGraphics.fill(leftMenuX, buttonY,
                            leftMenuX + subBorderThickness, buttonY + submenuButtonHeight,
                            buttonBorderColor);
                    guiGraphics.fill(leftMenuX + leftMenuWidth - subBorderThickness, buttonY,
                            leftMenuX + leftMenuWidth, buttonY + submenuButtonHeight,
                            buttonBorderColor);

                    String buttonText = STAT_SUBMENU_LABELS[i];
                    int buttonTextWidth = font.width(buttonText);
                    int buttonTextX = leftMenuX + (leftMenuWidth - buttonTextWidth) / 2;
                    int buttonTextY = buttonY + (submenuButtonHeight - font.lineHeight) / 2;

                    guiGraphics.drawString(font, buttonText, buttonTextX, buttonTextY, PARCHMENT_COLOR);
                }

                // Draw the content area
                int contentBgColor = (centerColor & 0x00FFFFFF) | 0xC0000000;
                guiGraphics.fill(contentAreaX, contentAreaY, contentAreaX + contentAreaWidth, contentAreaY + contentAreaHeight, contentBgColor);

                int contentBorderThickness = centerBorderThickness / 4;
                guiGraphics.fill(contentAreaX, contentAreaY,
                        contentAreaX + contentAreaWidth, contentAreaY + contentBorderThickness,
                        centerBorderColor);
                guiGraphics.fill(contentAreaX, contentAreaY + contentAreaHeight - contentBorderThickness,
                        contentAreaX + contentAreaWidth, contentAreaY + contentAreaHeight,
                        centerBorderColor);
                guiGraphics.fill(contentAreaX, contentAreaY,
                        contentAreaX + contentBorderThickness, contentAreaY + contentAreaHeight,
                        centerBorderColor);
                guiGraphics.fill(contentAreaX + contentAreaWidth - contentBorderThickness, contentAreaY,
                        contentAreaX + contentAreaWidth, contentAreaY + contentAreaHeight,
                        centerBorderColor);

                int statsX = contentAreaX + centerRectWidth / 32;
                int statsY = contentAreaY + centerRectHeight / 32;
                int lineSpacing = centerRectHeight / 24;

                String submenuTitle = STAT_SUBMENU_LABELS[selectedStatsSubmenu];
                float subtitleScale = 1.2f;
                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(subtitleScale, subtitleScale, 1.0f);
                guiGraphics.drawString(font, submenuTitle,
                        (int) (statsX / subtitleScale),
                        (int) (statsY / subtitleScale),
                        PARCHMENT_COLOR);
                guiGraphics.pose().popPose();

                statsY += 30;

                switch (selectedStatsSubmenu) {
                    case 0:
                        drawStat(guiGraphics, "Deaths", cachedDeathCount, statsX, statsY);
                        drawStat(guiGraphics, "Jumps", cachedJumps, statsX, statsY + lineSpacing);
                        drawDistanceStat(guiGraphics, "Distance Traveled", cachedTotalDistance, statsX, statsY + lineSpacing * 2);
                        break;

                    case 1:
                        drawStat(guiGraphics, "Mob Kills", cachedMobKills, statsX, statsY);
                        drawStat(guiGraphics, "Player Kills", cachedPlayerKills, statsX, statsY + lineSpacing);
                        break;

                    case 2:
                        drawStat(guiGraphics, "Items Stolen", 0, statsX, statsY);
                        drawStat(guiGraphics, "Bounty", 0, statsX, statsY + lineSpacing);
                        break;

                    case 3:
                        drawStat(guiGraphics, "Spells Cast", 0, statsX, statsY);
                        drawStat(guiGraphics, "Magic Level", 0, statsX, statsY + lineSpacing);
                        break;

                    case 4:
                        drawStat(guiGraphics, "Sword Kills", 0, statsX, statsY);
                        drawStat(guiGraphics, "Bow Kills", 0, statsX, statsY + lineSpacing);
                        break;

                    case 5:
                        drawStat(guiGraphics, "Blocks Mined", 0, statsX, statsY);
                        drawStat(guiGraphics, "Items Crafted", 0, statsX, statsY + lineSpacing);
                        break;
                }
            }
        }
    }

    private void drawStat(GuiGraphics guiGraphics, String label, int value, int x, int y) {
        String text = label + ": " + value;
        guiGraphics.drawString(font, text, x, y, PARCHMENT_COLOR);
    }

    private void drawDistanceStat(GuiGraphics guiGraphics, String label, double valueKm, int x, int y) {
        String formattedDistance = String.format("%.2f", valueKm);
        String text = label + ": " + formattedDistance + " km";
        guiGraphics.drawString(font, text, x, y, PARCHMENT_COLOR);
    }
}