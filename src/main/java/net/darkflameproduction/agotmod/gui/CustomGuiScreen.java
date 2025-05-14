package net.darkflameproduction.agotmod.gui;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatsCounter;

public class CustomGuiScreen extends Screen {
    private static final ResourceLocation BLUR_LOCATION =
            ResourceLocation.fromNamespaceAndPath("minecraft", "shaders/post/blur.json");

    private static final ResourceLocation[] STAINED_GLASS_TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_red.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_orange.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_yellow.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_green.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_blue.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_purple.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_magenta.png")
    };

    private static final ResourceLocation[] STAINED_GLASS_TRANSPARENT_TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_red_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_orange_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_yellow_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_green_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_blue_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_purple_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_magenta_transparant.png")
    };

    private static final ResourceLocation PILLAR_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/pillar.png");
    private static final ResourceLocation BORDER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/bottom.png");
    private static final ResourceLocation CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/corner.png");
    private static final ResourceLocation STONE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/background.png");
    private static final ResourceLocation PAPER_SIDE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paperside.png");
    private static final ResourceLocation PAPER_SIDETOP_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papersidetop.png");
    private static final ResourceLocation PAPER_CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papercorner.png");
    private static final ResourceLocation PAPER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paper.png");

    private static final String[] SECTION_LABELS = {
            "Map", "Quests", "Skills", "Stats", "House", "Faction", "Guilds"
    };

    private static final String[] STAT_SUBMENU_LABELS = {
            "General Stats", "Combat Stats", "Crime", "Magic", "Weapon Usage", "Tool Usage"
    };

    private static final String[] COMBAT_SKILLS = {
            "Swordsmanship", "Archery", "Defense", "Dual-Wielding", "Battle Tactics"
    };

    private static final String[] CRAFTING_SKILLS = {
            "Smithing", "Alchemy", "Enchanting", "Cooking", "Tailoring"
    };

    private static final int[] RAINBOW_COLORS = {
            0xFF990000, 0xFF994C00, 0xFF999900, 0xFF009900,
            0xFF000099, 0xFF2D0050, 0xFF57007F
    };

    private static final int PARCHMENT_COLOR = 0xFFF5E7C1;
    private static final int BLACK_COLOR = 0xFF000000;

    private static final Stat<ResourceLocation> DEATH_COUNT_STAT = Stats.CUSTOM.get(Stats.DEATHS);
    private static final Stat<ResourceLocation> KILLS_MOB_STAT = Stats.CUSTOM.get(Stats.MOB_KILLS);
    private static final Stat<ResourceLocation> PLAYER_KILLS_STAT = Stats.CUSTOM.get(Stats.PLAYER_KILLS);
    private static final Stat<ResourceLocation> JUMP_STAT = Stats.CUSTOM.get(Stats.JUMP);

    private static final Stat<ResourceLocation>[] DISTANCE_STATS = new Stat[] {
            Stats.CUSTOM.get(Stats.CLIMB_ONE_CM),
            Stats.CUSTOM.get(Stats.CROUCH_ONE_CM),
            Stats.CUSTOM.get(Stats.FALL_ONE_CM),
            Stats.CUSTOM.get(Stats.FLY_ONE_CM),
            Stats.CUSTOM.get(Stats.SPRINT_ONE_CM),
            Stats.CUSTOM.get(Stats.SWIM_ONE_CM),
            Stats.CUSTOM.get(Stats.WALK_ONE_CM),
            Stats.CUSTOM.get(Stats.WALK_ON_WATER_ONE_CM),
            Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM),
            Stats.CUSTOM.get(Stats.BOAT_ONE_CM),
            Stats.CUSTOM.get(Stats.AVIATE_ONE_CM),
            Stats.CUSTOM.get(Stats.HORSE_ONE_CM),
            Stats.CUSTOM.get(Stats.MINECART_ONE_CM),
            Stats.CUSTOM.get(Stats.PIG_ONE_CM),
            Stats.CUSTOM.get(Stats.STRIDER_ONE_CM)
    };

    private static final int BORDER_PILLAR_WIDTH = 6;
    private static final int BORDER_HEIGHT = 6;
    private static final int CORNER_SIZE = 12;

    private boolean isUsingShader = false;
    private float lastPlayerHealth = 0;
    private static int lastSelectedSection = 2;
    private int selectedSection;
    private static int lastSelectedStatsSubmenu = 0;
    private int selectedStatsSubmenu;

    private int[] combatSkillLevels = {45, 32, 27, 18, 15};
    private int[] craftingSkillLevels = {39, 22, 31, 45, 17};

    private int cachedDeaths = 0;
    private int cachedMobKills = 0;
    private int cachedPlayerKills = 0;
    private int cachedJumps = 0;
    private double cachedTotalDistance = 0.0;
    private boolean areStatsLoaded = false;

    public CustomGuiScreen() {
        super(Component.translatable("screen.agotmod.custom_gui"));
        this.selectedSection = lastSelectedSection;
        this.selectedStatsSubmenu = lastSelectedStatsSubmenu;
    }

    @Override
    protected void init() {
        super.init();

        if (minecraft != null && minecraft.player != null) {
            lastPlayerHealth = minecraft.player.getHealth();
            loadPlayerStatistics();
        }

        applyBlurEffect();
    }

    private void loadPlayerStatistics() {
        if (minecraft == null || minecraft.player == null) {
            return;
        }

        StatsCounter playerStats = minecraft.player.getStats();

        if (!areStatsLoaded) {
            try {
                if (minecraft.getConnection() != null) {
                    minecraft.getConnection().send(new net.minecraft.network.protocol.game.ServerboundClientCommandPacket(
                            net.minecraft.network.protocol.game.ServerboundClientCommandPacket.Action.REQUEST_STATS));
                    AGoTMod.LOGGER.info("Requested player statistics from server");
                    updateCachedStatistics(playerStats);
                    areStatsLoaded = true;
                }
            } catch (Exception e) {
                AGoTMod.LOGGER.error("Failed to request statistics from server: " + e.getMessage());
                updateCachedStatistics(playerStats);
            }
        } else {
            updateCachedStatistics(playerStats);
        }
    }

    private void updateCachedStatistics(StatsCounter stats) {
        if (stats == null) {
            return;
        }

        cachedDeaths = stats.getValue(DEATH_COUNT_STAT);
        cachedMobKills = stats.getValue(KILLS_MOB_STAT);
        cachedPlayerKills = stats.getValue(PLAYER_KILLS_STAT);
        cachedJumps = stats.getValue(JUMP_STAT);

        long totalDistanceCentimeters = 0;
        for (Stat<ResourceLocation> distanceStat : DISTANCE_STATS) {
            totalDistanceCentimeters += stats.getValue(distanceStat);
        }

        cachedTotalDistance = totalDistanceCentimeters / 100000.0;
    }

    private void applyBlurEffect() {
        if (minecraft == null) {
            return;
        }

        try {
            if (minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class) != null) {
                minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class)
                        .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                isUsingShader = true;
            }
        } catch (Exception e1) {
            try {
                if (minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class) != null) {
                    minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class)
                            .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                    isUsingShader = true;
                }
            } catch (Exception e2) {
                AGoTMod.LOGGER.error("Failed to apply blur shader: " + e2.getMessage());
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        ScreenLayout layout = new ScreenLayout(width, height);

        for (int i = 0; i < SECTION_LABELS.length; i++) {
            int startX = layout.sideMargin + (i * layout.sectionButtonWidth);
            int endX = (i == SECTION_LABELS.length - 1)
                    ? width - layout.sideMargin
                    : layout.sideMargin + ((i + 1) * layout.sectionButtonWidth);

            if (isMouseOverRect((int) mouseX, (int) mouseY, startX, layout.topMargin,
                    endX - startX, layout.sectionButtonHeight)) {

                selectedSection = i;
                lastSelectedSection = i;

                if (i == 3 && !areStatsLoaded && minecraft != null) {
                    loadPlayerStatistics();
                }

                playButtonSound();
                return true;
            }
        }

        if (selectedSection == 3) {
            int submenuWidth = layout.contentWidth / 6;
            int submenuStartX = layout.contentX + layout.contentWidth / 128;
            int submenuStartY = layout.contentY + layout.contentHeight / 16;
            int submenuHeight = layout.contentHeight - (layout.contentHeight / 8);

            int buttonHeight = submenuHeight / 8;
            int buttonSpacing = submenuHeight / 24;
            int totalButtonsHeight = (buttonHeight * STAT_SUBMENU_LABELS.length) +
                    (buttonSpacing * (STAT_SUBMENU_LABELS.length - 1));
            int submenuStartYCentered = submenuStartY + (submenuHeight - totalButtonsHeight) / 2;

            for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
                int buttonY = submenuStartYCentered + (i * (buttonHeight + buttonSpacing));

                int buttonRightOffset = 10;
                if (isMouseOverRect((int) mouseX, (int) mouseY, submenuStartX + buttonRightOffset, buttonY,
                        submenuWidth - buttonRightOffset, buttonHeight)) {

                    selectedStatsSubmenu = i;
                    lastSelectedStatsSubmenu = i;

                    playButtonSound();
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void playButtonSound() {
        if (minecraft != null && minecraft.player != null) {
            minecraft.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                            ModSounds.BUTTON, 1.0F
                    )
            );
        }
    }

    private boolean isMouseOverRect(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (minecraft != null && minecraft.player != null) {
            float currentHealth = minecraft.player.getHealth();

            if (currentHealth < lastPlayerHealth) {
                AGoTMod.LOGGER.info("Player health decreased, closing GUI");
                minecraft.setScreen(null);
                return;
            }

            lastPlayerHealth = currentHealth;
        }

        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7);

        ScreenLayout layout = new ScreenLayout(width, height);

        drawSectionButtons(guiGraphics, mouseX, mouseY, layout);

        if (selectedSection >= 0 && selectedSection < RAINBOW_COLORS.length) {
            renderStonePanel(guiGraphics, layout.contentX, layout.contentY,
                    layout.contentWidth, layout.contentHeight);

            drawSectionTitle(guiGraphics, SECTION_LABELS[selectedSection],
                    layout.contentX, layout.contentY, layout.contentWidth);

            if (selectedSection == 2) {
                drawSkillsSection(guiGraphics, mouseX, mouseY, layout);
            }
            else if (selectedSection == 3) {
                drawStatsSection(guiGraphics, mouseX, mouseY, layout);
            }
        }
    }

    private void drawSectionButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout) {
        for (int i = 0; i < SECTION_LABELS.length; i++) {
            int startX = layout.sideMargin + (i * layout.sectionButtonWidth);
            int endX = (i == SECTION_LABELS.length - 1)
                    ? width - layout.sideMargin
                    : layout.sideMargin + ((i + 1) * layout.sectionButtonWidth);

            boolean isHovered = isMouseOverRect(mouseX, mouseY, startX, layout.topMargin,
                    endX - startX, layout.sectionButtonHeight);
            boolean isSelected = (i == selectedSection);

            int buttonWidth = endX - startX;

            ResourceLocation texture = (isHovered || isSelected)
                    ? STAINED_GLASS_TRANSPARENT_TEXTURES[i]
                    : STAINED_GLASS_TEXTURES[i];

            renderTexturedPanel(guiGraphics, texture, startX, layout.topMargin,
                    buttonWidth, layout.sectionButtonHeight, true);

            String text = SECTION_LABELS[i];
            int textWidth = font.width(text);
            int textX = startX + (buttonWidth - textWidth) / 2;
            int textY = layout.topMargin + (layout.sectionButtonHeight - font.lineHeight) / 2;

            guiGraphics.drawString(font, text, textX, textY,
                    isSelected ? 0xFFFFFFFF : PARCHMENT_COLOR, true);
        }
    }

    private void drawSkillsSection(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout) {
        int panelGap = 4;

        int panelWidth = (int)(layout.contentWidth * 0.48);

        int totalWidth = (panelWidth * 2) + panelGap;
        int startX = layout.contentX + ((layout.contentWidth - totalWidth) / 2);

        int leftPanelX = startX;
        int rightPanelX = leftPanelX + panelWidth + panelGap;

        int panelY = layout.contentY + layout.contentHeight / 16;
        int panelHeight = layout.contentHeight - (layout.contentHeight / 8);

        renderPaperPanel(guiGraphics, leftPanelX, panelY, panelWidth, panelHeight);
        drawSubmenuTitle(guiGraphics, "Combat Skills", leftPanelX, panelY);

        renderPaperPanel(guiGraphics, rightPanelX, panelY, panelWidth, panelHeight);
        drawSubmenuTitle(guiGraphics, "Crafting Skills", rightPanelX, panelY);

        int leftStatsX = leftPanelX + layout.contentWidth / 32;
        int statsY = panelY + layout.contentHeight / 12 + 20;
        int lineSpacing = layout.contentHeight / 16;

        for (int i = 0; i < COMBAT_SKILLS.length; i++) {
            drawSkillStat(guiGraphics, COMBAT_SKILLS[i], combatSkillLevels[i],
                    leftStatsX, statsY + (lineSpacing * i));
        }

        int rightStatsX = rightPanelX + layout.contentWidth / 32;

        for (int i = 0; i < CRAFTING_SKILLS.length; i++) {
            drawSkillStat(guiGraphics, CRAFTING_SKILLS[i], craftingSkillLevels[i],
                    rightStatsX, statsY + (lineSpacing * i));
        }
    }

    private void drawSkillStat(GuiGraphics guiGraphics, String skillName, int level, int x, int y) {
        float percentage = level / 50.0f;
        int maxBarWidth = 120;
        int barWidth = (int)(maxBarWidth * percentage);
        int barHeight = 8;

        String text = skillName + ": " + level;
        guiGraphics.drawString(font, text, x, y, PARCHMENT_COLOR);

        guiGraphics.fill(x, y + font.lineHeight + 2, x + maxBarWidth, y + font.lineHeight + 2 + barHeight, 0xFF555555);

        guiGraphics.fill(x, y + font.lineHeight + 2, x + barWidth, y + font.lineHeight + 2 + barHeight, RAINBOW_COLORS[2]);
    }

    private void drawStatsSection(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout) {
        if (!areStatsLoaded && minecraft != null) {
            loadPlayerStatistics();
        }

        int submenuWidth = layout.contentWidth / 6;
        int submenuStartX = layout.contentX + layout.contentWidth / 128;
        int submenuStartY = layout.contentY + layout.contentHeight / 16;
        int submenuHeight = layout.contentHeight - (layout.contentHeight / 8);

        int buttonHeight = submenuHeight / 8;
        int buttonSpacing = submenuHeight / 24;
        int totalButtonsHeight = (buttonHeight * STAT_SUBMENU_LABELS.length) +
                (buttonSpacing * (STAT_SUBMENU_LABELS.length - 1));
        int submenuStartYCentered = submenuStartY + (submenuHeight - totalButtonsHeight) / 2;

        int contentStartX = submenuStartX + submenuWidth + layout.contentWidth / 32;
        int contentStartY = submenuStartY;
        int contentWidth = layout.contentWidth - submenuWidth - (layout.contentWidth / 16);
        int contentHeight = submenuHeight;

        for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
            int buttonY = submenuStartYCentered + (i * (buttonHeight + buttonSpacing));

            boolean isHovered = isMouseOverRect(mouseX, mouseY, submenuStartX, buttonY,
                    submenuWidth, buttonHeight);
            boolean isSelected = (i == selectedStatsSubmenu);

            ResourceLocation buttonTexture = (isHovered || isSelected)
                    ? STAINED_GLASS_TRANSPARENT_TEXTURES[3]
                    : STAINED_GLASS_TEXTURES[3];

            int buttonRightOffset = 10;
            renderTexturedPanel(guiGraphics, buttonTexture, submenuStartX + buttonRightOffset, buttonY,
                    submenuWidth - buttonRightOffset, buttonHeight, true);

            String buttonText = STAT_SUBMENU_LABELS[i];
            int buttonTextWidth = font.width(buttonText);

            int buttonTextX = submenuStartX + buttonRightOffset + ((submenuWidth - buttonRightOffset - buttonTextWidth) / 2);

            int textHeight = font.lineHeight;
            int buttonTextY = buttonY + (buttonHeight - textHeight) / 2;

            guiGraphics.drawString(font, buttonText, buttonTextX, buttonTextY,
                    isSelected ? 0xFFFFFFFF : PARCHMENT_COLOR);
        }

        renderPaperPanel(guiGraphics, contentStartX, contentStartY,
                contentWidth, contentHeight);

        String submenuTitle = STAT_SUBMENU_LABELS[selectedStatsSubmenu];
        drawSubmenuTitle(guiGraphics, submenuTitle, contentStartX, contentStartY);

        int statsX = contentStartX + layout.contentWidth / 32;
        int statsY = contentStartY + layout.contentHeight / 12 + 20;
        int lineSpacing = layout.contentHeight / 16;

        drawSubmenuContent(guiGraphics, statsX, statsY, lineSpacing);
    }

    private void drawSubmenuContent(GuiGraphics guiGraphics, int x, int y, int lineSpacing) {
        switch (selectedStatsSubmenu) {
            case 0:
                drawStat(guiGraphics, "Deaths", cachedDeaths, x, y);
                drawStat(guiGraphics, "Jumps", cachedJumps, x, y + lineSpacing);
                drawDistanceStat(guiGraphics, "Distance Traveled", cachedTotalDistance, x, y + lineSpacing * 2);
                break;

            case 1:
                drawStat(guiGraphics, "Mob Kills", cachedMobKills, x, y);
                drawStat(guiGraphics, "Player Kills", cachedPlayerKills, x, y + lineSpacing);
                break;

            case 2:
                drawStat(guiGraphics, "Items Stolen", 0, x, y);
                drawStat(guiGraphics, "Bounty", 0, x, y + lineSpacing);
                break;

            case 3:
                drawStat(guiGraphics, "Spells Cast", 0, x, y);
                drawStat(guiGraphics, "Magic Level", 0, x, y + lineSpacing);
                break;

            case 4:
                drawStat(guiGraphics, "Sword Kills", 0, x, y);
                drawStat(guiGraphics, "Bow Kills", 0, x, y + lineSpacing);
                break;

            case 5:
                drawStat(guiGraphics, "Blocks Mined", 0, x, y);
                drawStat(guiGraphics, "Items Crafted", 0, x, y + lineSpacing);
                break;
        }
    }

    private void drawSectionTitle(GuiGraphics guiGraphics, String title, int x, int width, int y) {
        int titleWidth = (int) (font.width(title) * 1.5f);
        int titleX = x + (width - titleWidth) / 2;
        int titleY = y + 12;

        float titleScale = 1.5f;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(titleScale, titleScale, 1.0f);
        guiGraphics.drawString(font, title,
                (int) (titleX / titleScale),
                (int) (titleY / titleScale),
                PARCHMENT_COLOR);
        guiGraphics.pose().popPose();
    }

    private void drawSubmenuTitle(GuiGraphics guiGraphics, String title, int x, int y) {
        float scale = 1.2f;
        int titleY = y + 18;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);
        guiGraphics.drawString(font, title,
                (int) ((x + 20) / scale),
                (int) (titleY / scale),
                PARCHMENT_COLOR);
        guiGraphics.pose().popPose();
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

    private void renderTexturedPanel(GuiGraphics guiGraphics, ResourceLocation texture,
                                     int x, int y, int width, int height, boolean withBorders) {
        guiGraphics.flush();

        com.mojang.blaze3d.systems.RenderSystem.enableBlend();
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                texture, x, y, 0, 0, width, height, 32, 32);

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        if (withBorders) {
            drawPanelBorders(guiGraphics, x, y, width, height);
        }
    }



    private void renderPaperPanel(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.flush();

        // Define thicker border dimensions
        int borderPillarWidth = BORDER_PILLAR_WIDTH * 2;
        int borderHeight = BORDER_HEIGHT * 2;
        int cornerSize = CORNER_SIZE * 2;

        // Calculate the inner panel dimensions - shrink the panel to fit inside borders
        int innerX = x + borderPillarWidth;
        int innerY = y + borderHeight;
        int innerWidth = width - (borderPillarWidth * 2);
        int innerHeight = height - (borderHeight * 2);

        // Render the inner panel background
        com.mojang.blaze3d.systems.RenderSystem.enableBlend();
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

        int tileSize = 32;
        for (int tileX = 0; tileX < innerWidth; tileX += tileSize) {
            for (int tileY = 0; tileY < innerHeight; tileY += tileSize) {
                int tileWidth = Math.min(tileSize, innerWidth - tileX);
                int tileHeight = Math.min(tileSize, innerHeight - tileY);

                guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                        PAPER_TEXTURE, innerX + tileX, innerY + tileY, 0, 0,
                        tileWidth, tileHeight, tileSize, tileSize);
            }
        }

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // Draw borders with rotation capability
        drawPaperPanelBorders(guiGraphics, x, y, width, height, borderPillarWidth, borderHeight, cornerSize);
    }

    private void drawPaperPanelBorders(GuiGraphics guiGraphics, int x, int y, int width, int height,
                                       int borderPillarWidth, int borderHeight, int cornerSize) {
        // Draw left side border with rotation capability
        drawRotatableBorder(guiGraphics, PAPER_SIDE_TEXTURE,
                x, y + cornerSize,
                borderPillarWidth, height - (cornerSize * 2),
                0); // Degrees of rotation - can be changed

        // Draw right side border with rotation capability
        drawRotatableBorder(guiGraphics, PAPER_SIDE_TEXTURE,
                x + width - borderPillarWidth, y + cornerSize,
                borderPillarWidth, height - (cornerSize * 2),
                180); // Rotation by 180 degrees - can be changed

        // Draw top border with rotation capability
        drawRotatableBorder(guiGraphics, PAPER_SIDETOP_TEXTURE,
                x + cornerSize, y,
                width - (cornerSize * 2), borderHeight,
                0); // Degrees of rotation - can be changed

        // Draw bottom border with rotation capability and darkened color
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1.0f);

        drawRotatableBorder(guiGraphics, PAPER_SIDETOP_TEXTURE,
                x + cornerSize, y + height - borderHeight,
                width - (cornerSize * 2), borderHeight,
                180); // Rotation by 180 degrees - can be changed

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        // Draw corner pieces that fit perfectly in each corner
        drawPaperCorners(guiGraphics, x, y, width, height, cornerSize);
    }

    /**
     * Draws the corner pieces of the paper panel
     * @param guiGraphics The graphics context
     * @param x The x position of the panel
     * @param y The y position of the panel
     * @param width The width of the panel
     * @param height The height of the panel
     * @param cornerSize The size of the corner pieces
     */
    private void drawPaperCorners(GuiGraphics guiGraphics, int x, int y, int width, int height, int cornerSize) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

        // Top-left corner - standard orientation (0 degrees)
        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x, y, cornerSize, cornerSize, 0);

        // Top-right corner - rotate 90 degrees clockwise
        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x + width - cornerSize, y, cornerSize, cornerSize, 90);

        // Bottom-right corner - rotate 180 degrees
        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x + width - cornerSize, y + height - cornerSize, cornerSize, cornerSize, 180);

        // Bottom-left corner - rotate 270 degrees clockwise
        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x, y + height - cornerSize, cornerSize, cornerSize, 270);
    }

    /**
     * Draws a rotatable corner piece
     * @param guiGraphics The graphics context
     * @param texture The texture to use
     * @param x The x position
     * @param y The y position
     * @param width The width
     * @param height The height
     * @param rotationDegrees The rotation angle in degrees
     */
    private void drawRotatableCorner(GuiGraphics guiGraphics,
                                     net.minecraft.resources.ResourceLocation texture,
                                     int x, int y, int width, int height, float rotationDegrees) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

        // Save current transformation state
        poseStack.pushPose();

        if (rotationDegrees != 0) {
            // Move to the corner's center for rotation
            poseStack.translate(x + width / 2.0f, y + height / 2.0f, 0);

            // Apply rotation
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rotationDegrees));

            // Move back to draw from the top-left corner
            poseStack.translate(-width / 2.0f, -height / 2.0f, 0);

            // Draw the rotated corner
            guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                    texture, 0, 0, 0, 0, width, height, 32, 32);
        } else {
            // No rotation needed, draw directly
            guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                    texture, x, y, 0, 0, width, height, 32, 32);
        }

        // Restore transformation state
        poseStack.popPose();
    }

    /**
     * Draws a border with rotation capability
     * @param guiGraphics The graphics context
     * @param texture The texture to use
     * @param x The x position
     * @param y The y position
     * @param width The width of the border
     * @param height The height of the border
     * @param rotationDegrees The rotation angle in degrees
     */
    private void drawRotatableBorder(GuiGraphics guiGraphics,
                                     net.minecraft.resources.ResourceLocation texture,
                                     int x, int y, int width, int height, float rotationDegrees) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

        // Save the current transformation state
        poseStack.pushPose();

        // Calculate the center of the border for rotation
        float centerX = x + width / 2.0f;
        float centerY = y + height / 2.0f;

        // Move to the center point for rotation
        poseStack.translate(centerX, centerY, 0);

        // Apply rotation around the Z axis
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rotationDegrees));

        // Move back to draw from the corner, accounting for the dimensions
        poseStack.translate(-width / 2.0f, -height / 2.0f, 0);

        // Determine texture size (32x32)
        int textureSize = 32;

        // Draw with tiling to avoid stretching
        for (int tileX = 0; tileX < width; tileX += textureSize) {
            for (int tileY = 0; tileY < height; tileY += textureSize) {
                int tileWidth = Math.min(textureSize, width - tileX);
                int tileHeight = Math.min(textureSize, height - tileY);

                // Skip if dimensions are invalid
                if (tileWidth <= 0 || tileHeight <= 0) continue;

                // Draw the tile
                guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                        texture, tileX, tileY, 0, 0,
                        tileWidth, tileHeight, textureSize, textureSize);
            }
        }

        // Restore the transformation state
        poseStack.popPose();
    }

    private void renderStonePanel(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.flush();

        com.mojang.blaze3d.systems.RenderSystem.enableBlend();
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

        int tileSize = 16;
        for (int tileX = 0; tileX < width; tileX += tileSize) {
            for (int tileY = 0; tileY < height; tileY += tileSize) {
                int tileWidth = Math.min(tileSize, width - tileX);
                int tileHeight = Math.min(tileSize, height - tileY);

                guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                        STONE_TEXTURE, x + tileX, y + tileY, 0, 0,
                        tileWidth, tileHeight, tileSize, tileSize);
            }
        }



        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        drawPanelBorders(guiGraphics, x, y, width, height);
    }



    private void drawPanelBorders(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                PILLAR_TEXTURE, x, y, 0, 0, BORDER_PILLAR_WIDTH, height, 16, 64);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                PILLAR_TEXTURE, x + width - BORDER_PILLAR_WIDTH, y, 0, 0,
                BORDER_PILLAR_WIDTH, height, 16, 64);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                BORDER_TEXTURE, x, y, 0, 0, width, BORDER_HEIGHT, 64, 16);

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1.0f);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                BORDER_TEXTURE, x, y + height - BORDER_HEIGHT, 0, 0,
                width, BORDER_HEIGHT, 64, 16);

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        drawCorners(guiGraphics, x, y, width, height);
    }

    private void drawCorners(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, x, y + height - BORDER_HEIGHT - CORNER_SIZE/2,
                0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(x + width - CORNER_SIZE/2, y + height - BORDER_HEIGHT, 0);
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(270));
        poseStack.translate(-CORNER_SIZE/2, -CORNER_SIZE/2, 0);
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(x + width - CORNER_SIZE/2, y + CORNER_SIZE/2, 0);
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(180));
        poseStack.translate(-CORNER_SIZE/2, -CORNER_SIZE/2, 0);
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(x + CORNER_SIZE/2, y + CORNER_SIZE/2, 0);
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(90));
        poseStack.translate(-CORNER_SIZE/2, -CORNER_SIZE/2, 0);
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();
    }

    private class ScreenLayout {
        final int topMargin;
        final int sideMargin;
        final int sectionButtonWidth;
        final int sectionButtonHeight;
        final int contentX;
        final int contentY;
        final int contentWidth;
        final int contentHeight;

        ScreenLayout(int screenWidth, int screenHeight) {
            topMargin = screenHeight / 32;
            sideMargin = screenWidth / 32;
            sectionButtonHeight = screenHeight / 8;

            int availableWidth = screenWidth - (2 * sideMargin);
            sectionButtonWidth = availableWidth / 7;

            contentX = sideMargin;
            contentY = topMargin + sectionButtonHeight + 5;
            contentWidth = screenWidth - (2 * sideMargin);
            contentHeight = screenHeight - topMargin - sectionButtonHeight - (screenHeight / 32);
        }
    }
}