package net.darkflameproduction.agotmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class GrocerInventoryScreen extends Screen {
    // Texture resources matching your custom GUI style
    private static final ResourceLocation STONE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/background.png");
    private static final ResourceLocation PAPER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paper.png");
    private static final ResourceLocation PAPER_SIDE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paperside.png");
    private static final ResourceLocation PAPER_SIDETOP_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papersidetop.png");
    private static final ResourceLocation PAPER_CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papercorner.png");
    private static final ResourceLocation PILLAR_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/pillar.png");
    private static final ResourceLocation BORDER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/bottom.png");
    private static final ResourceLocation CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/corner.png");

    // Colors matching your custom GUI
    private static final int SUBMENU_TEXT_COLOR = 0xFF000000;
    private static final int PARCHMENT_COLOR = 0xFFF5E7C1;

    // Border constants matching your custom GUI
    private static final int BORDER_PILLAR_WIDTH = 6;
    private static final int BORDER_HEIGHT = 6;
    private static final int CORNER_SIZE = 12;

    private final List<GrocerSystem.GrocerInventoryEntry> inventoryEntries;
    private final String grocerName;

    // Scrolling
    private int scrollOffset = 0;
    private static final int VISIBLE_ROWS = 12;
    private static final int ROW_HEIGHT = 18;

    public GrocerInventoryScreen(List<GrocerSystem.GrocerInventoryEntry> inventoryEntries, String grocerName) {
        super(Component.literal("Grocer Inventory - " + grocerName));
        this.inventoryEntries = inventoryEntries;
        this.grocerName = grocerName;
    }

    @Override
    protected void init() {
        super.init();
        // No buttons needed - using scroll wheel and ESC to close
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (inventoryEntries.size() > VISIBLE_ROWS) {
            if (scrollY > 0) {
                scrollUp();
                return true;
            } else if (scrollY < 0) {
                scrollDown();
                return true;
            }
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Handle arrow keys for scrolling
        if (keyCode == 265) { // UP arrow
            scrollUp();
            return true;
        } else if (keyCode == 264) { // DOWN arrow
            scrollDown();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void scrollUp() {
        if (scrollOffset > 0) {
            scrollOffset--;
            playScrollSound();
        }
    }

    private void scrollDown() {
        int maxScroll = Math.max(0, inventoryEntries.size() - VISIBLE_ROWS);
        if (scrollOffset < maxScroll) {
            scrollOffset++;
            playScrollSound();
        }
    }

    private void playScrollSound() {
        if (minecraft != null && minecraft.player != null) {
            minecraft.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                            ModSounds.BUTTON, 0.5F
                    )
            );
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Render background matching your custom GUI
        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7);

        // Calculate layout similar to your custom GUI
        ScreenLayout layout = new ScreenLayout(width, height);

        // Render stone panel background
        renderStonePanel(guiGraphics, layout.contentX, layout.contentY,
                layout.contentWidth, layout.contentHeight);

        // Render paper panel for content
        int paperMargin = 20;
        int paperX = layout.contentX + paperMargin;
        int paperY = layout.contentY + paperMargin;
        int paperWidth = layout.contentWidth - (paperMargin * 2);
        int paperHeight = layout.contentHeight - (paperMargin * 2);

        renderPaperPanel(guiGraphics, paperX, paperY, paperWidth, paperHeight);

        // Draw title
        drawSectionTitle(guiGraphics, "Grocer Inventory - " + grocerName,
                layout.contentX, layout.contentY, layout.contentWidth);

        // Draw inventory entries
        drawInventoryEntries(guiGraphics, paperX, paperY, paperWidth, paperHeight);

        // Draw scroll indicator if needed
        if (inventoryEntries.size() > VISIBLE_ROWS) {
            drawScrollIndicator(guiGraphics, paperX, paperY, paperWidth, paperHeight);
        }

        // Draw help text at bottom
        drawHelpText(guiGraphics, paperX, paperY, paperWidth, paperHeight);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void drawInventoryEntries(GuiGraphics guiGraphics, int paperX, int paperY, int paperWidth, int paperHeight) {
        if (inventoryEntries.isEmpty()) {
            // Draw "No items collected" message
            String emptyMessage = "No items have been collected yet.";
            int messageWidth = font.width(emptyMessage);
            int messageX = paperX + (paperWidth - messageWidth) / 2;
            int messageY = paperY + paperHeight / 2;

            guiGraphics.drawString(font, emptyMessage, messageX, messageY, SUBMENU_TEXT_COLOR, false);
            return;
        }

        int startY = paperY + 60; // Leave space for title
        int endIndex = Math.min(scrollOffset + VISIBLE_ROWS, inventoryEntries.size());

        // Draw header
        String headerText = "Items Collected:";
        int headerWidth = font.width(headerText);
        int headerX = paperX + (paperWidth - headerWidth) / 2;
        guiGraphics.drawString(font, headerText, headerX, startY - 25, SUBMENU_TEXT_COLOR, false);

        // Draw separator line
        guiGraphics.fill(paperX + paperWidth/8, startY - 15,
                paperX + paperWidth - paperWidth/8, startY - 14,
                SUBMENU_TEXT_COLOR);

        // Draw inventory entries
        for (int i = scrollOffset; i < endIndex; i++) {
            GrocerSystem.GrocerInventoryEntry entry = inventoryEntries.get(i);
            int yPos = startY + (i - scrollOffset) * ROW_HEIGHT;

            // Format the text: "Item name: amount"
            String displayText = entry.displayName + ": " + formatNumber(entry.amount);

            // Center the text
            int textWidth = font.width(displayText);
            int textX = paperX + (paperWidth - textWidth) / 2;

            // Alternate row background for better readability
            if ((i - scrollOffset) % 2 == 0) {
                guiGraphics.fill(paperX + 20, yPos - 2,
                        paperX + paperWidth - 20, yPos + font.lineHeight + 2,
                        0x20000000);
            }

            guiGraphics.drawString(font, displayText, textX, yPos, SUBMENU_TEXT_COLOR, false);
        }
    }

    private void drawScrollIndicator(GuiGraphics guiGraphics, int paperX, int paperY, int paperWidth, int paperHeight) {
        int scrollBarX = paperX + paperWidth - 25;
        int scrollBarY = paperY + 80;
        int scrollBarHeight = paperHeight - 140;
        int scrollBarWidth = 8;

        // Draw scroll bar background
        guiGraphics.fill(scrollBarX, scrollBarY,
                scrollBarX + scrollBarWidth, scrollBarY + scrollBarHeight,
                0xFF555555);

        // Calculate scroll thumb
        if (inventoryEntries.size() > VISIBLE_ROWS) {
            int thumbHeight = Math.max(20, (VISIBLE_ROWS * scrollBarHeight) / inventoryEntries.size());
            int thumbY = scrollBarY + (scrollOffset * (scrollBarHeight - thumbHeight)) /
                    Math.max(1, inventoryEntries.size() - VISIBLE_ROWS);

            // Draw scroll thumb
            guiGraphics.fill(scrollBarX, thumbY,
                    scrollBarX + scrollBarWidth, thumbY + thumbHeight,
                    0xFFC6C6C6);
        }

        // Draw scroll arrows as text
        String upArrow = "↑";
        String downArrow = "↓";

        int arrowX = scrollBarX + (scrollBarWidth - font.width(upArrow)) / 2;

        // Up arrow
        int upColor = scrollOffset > 0 ? SUBMENU_TEXT_COLOR : 0xFF666666;
        guiGraphics.drawString(font, upArrow, arrowX, scrollBarY - 15, upColor, false);

        // Down arrow
        int maxScroll = Math.max(0, inventoryEntries.size() - VISIBLE_ROWS);
        int downColor = scrollOffset < maxScroll ? SUBMENU_TEXT_COLOR : 0xFF666666;
        guiGraphics.drawString(font, downArrow, arrowX, scrollBarY + scrollBarHeight + 5, downColor, false);
    }

    private void drawHelpText(GuiGraphics guiGraphics, int paperX, int paperY, int paperWidth, int paperHeight) {
        String helpText = "Use mouse wheel or arrow keys to scroll • Press ESC to close";
        int helpWidth = font.width(helpText);
        int helpX = paperX + (paperWidth - helpWidth) / 2;
        int helpY = paperY + paperHeight - 25;

        // Scale down the help text
        float scale = 0.8f;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        guiGraphics.drawString(font, helpText,
                (int)(helpX / scale), (int)(helpY / scale),
                0xFF666666, false);

        guiGraphics.pose().popPose();

        // Draw total count
        String totalText = "Total different items: " + inventoryEntries.size();
        int totalWidth = font.width(totalText);
        int totalX = paperX + (paperWidth - totalWidth) / 2;
        int totalY = helpY - 20;

        guiGraphics.drawString(font, totalText, totalX, totalY, SUBMENU_TEXT_COLOR, false);
    }

    private String formatNumber(int number) {
        if (number >= 1000000) {
            return String.format("%.1fM", number / 1000000.0);
        } else if (number >= 1000) {
            return String.format("%.1fK", number / 1000.0);
        } else {
            return String.valueOf(number);
        }
    }

    private void drawSectionTitle(GuiGraphics guiGraphics, String title, int x, int y, int width) {
        int titleWidth = (int) (font.width(title) * 1.2f);
        int titleX = x + (width - titleWidth) / 2;
        int titleY = y + 18;

        float titleScale = 1.2f;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(titleScale, titleScale, 1.0f);
        guiGraphics.drawString(font, title,
                (int) (titleX / titleScale),
                (int) (titleY / titleScale),
                SUBMENU_TEXT_COLOR, false);
        guiGraphics.pose().popPose();
    }

    // Rendering methods copied from your custom GUI
    private void renderStonePanel(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.flush();

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

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

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        drawPanelBorders(guiGraphics, x, y, width, height);
    }

    private void renderPaperPanel(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.flush();

        int borderPillarWidth = BORDER_PILLAR_WIDTH * 2;
        int borderHeight = BORDER_HEIGHT * 2;
        int cornerSize = CORNER_SIZE * 2;

        int innerX = x + borderPillarWidth;
        int innerY = y + borderHeight;
        int innerWidth = width - (borderPillarWidth * 2);
        int innerHeight = height - (borderHeight * 2);

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

        int tileSize = 256;
        for (int tileX = 0; tileX < innerWidth; tileX += tileSize) {
            for (int tileY = 0; tileY < innerHeight; tileY += tileSize) {
                int tileWidth = Math.min(tileSize, innerWidth - tileX);
                int tileHeight = Math.min(tileSize, innerHeight - tileY);

                guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                        PAPER_TEXTURE, innerX + tileX, innerY + tileY, 0, 0,
                        tileWidth, tileHeight, tileSize, tileSize);
            }
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        drawPaperPanelBorders(guiGraphics, x, y, width, height, borderPillarWidth, borderHeight, cornerSize);
    }

    private void drawPaperPanelBorders(GuiGraphics guiGraphics, int x, int y, int width, int height,
                                       int borderPillarWidth, int borderHeight, int cornerSize) {
        drawRotatableBorder(guiGraphics, PAPER_SIDE_TEXTURE,
                x, y + cornerSize,
                borderPillarWidth, height - (cornerSize * 2),
                0);

        drawRotatableBorder(guiGraphics, PAPER_SIDE_TEXTURE,
                x + width - borderPillarWidth, y + cornerSize,
                borderPillarWidth, height - (cornerSize * 2),
                180);

        drawRotatableBorder(guiGraphics, PAPER_SIDETOP_TEXTURE,
                x + cornerSize, y,
                width - (cornerSize * 2), borderHeight,
                0);

        RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1.0f);

        drawRotatableBorder(guiGraphics, PAPER_SIDETOP_TEXTURE,
                x + cornerSize, y + height - borderHeight,
                width - (cornerSize * 2), borderHeight,
                180);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        drawPaperCorners(guiGraphics, x, y, width, height, cornerSize);
    }

    private void drawPaperCorners(GuiGraphics guiGraphics, int x, int y, int width, int height, int cornerSize) {
        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x, y, cornerSize, cornerSize, 0);

        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x + width - cornerSize, y, cornerSize, cornerSize, 90);

        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x + width - cornerSize, y + height - cornerSize, cornerSize, cornerSize, 180);

        drawRotatableCorner(guiGraphics, PAPER_CORNER_TEXTURE,
                x, y + height - cornerSize, cornerSize, cornerSize, 270);
    }

    private void drawRotatableCorner(GuiGraphics guiGraphics,
                                     ResourceLocation texture,
                                     int x, int y, int width, int height, float rotationDegrees) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();

        if (rotationDegrees != 0) {
            poseStack.translate(x + width / 2.0f, y + height / 2.0f, 0);
            poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rotationDegrees));
            poseStack.translate(-width / 2.0f, -height / 2.0f, 0);

            guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                    texture, 0, 0, 0, 0, width, height, 32, 32);
        } else {
            guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                    texture, x, y, 0, 0, width, height, 32, 32);
        }

        poseStack.popPose();
    }

    private void drawRotatableBorder(GuiGraphics guiGraphics,
                                     ResourceLocation texture,
                                     int x, int y, int width, int height, float rotationDegrees) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();

        float centerX = x + width / 2.0f;
        float centerY = y + height / 2.0f;

        poseStack.translate(centerX, centerY, 0);
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rotationDegrees));
        poseStack.translate(-width / 2.0f, -height / 2.0f, 0);

        int textureSize = 32;

        for (int tileX = 0; tileX < width; tileX += textureSize) {
            for (int tileY = 0; tileY < height; tileY += textureSize) {
                int tileWidth = Math.min(textureSize, width - tileX);
                int tileHeight = Math.min(textureSize, height - tileY);

                if (tileWidth <= 0 || tileHeight <= 0) continue;

                guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                        texture, tileX, tileY, 0, 0,
                        tileWidth, tileHeight, textureSize, textureSize);
            }
        }

        poseStack.popPose();
    }

    private void drawPanelBorders(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                PILLAR_TEXTURE, x, y, 0, 0, BORDER_PILLAR_WIDTH, height, 16, 64);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                PILLAR_TEXTURE, x + width - BORDER_PILLAR_WIDTH, y, 0, 0,
                BORDER_PILLAR_WIDTH, height, 16, 64);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                BORDER_TEXTURE, x, y, 0, 0, width, BORDER_HEIGHT, 64, 16);

        RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1.0f);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                BORDER_TEXTURE, x, y + height - BORDER_HEIGHT, 0, 0,
                width, BORDER_HEIGHT, 64, 16);

        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        drawCorners(guiGraphics, x, y, width, height);
    }

    private void drawCorners(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

        poseStack.pushPose();
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, x, y + height - BORDER_HEIGHT - CORNER_SIZE / 2,
                0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(x + width - CORNER_SIZE / 2, y + height - BORDER_HEIGHT, 0);
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(270));
        poseStack.translate(-CORNER_SIZE / 2, -CORNER_SIZE / 2, 0);
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(x + width - CORNER_SIZE / 2, y + CORNER_SIZE / 2, 0);
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(180));
        poseStack.translate(-CORNER_SIZE / 2, -CORNER_SIZE / 2, 0);
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(x + CORNER_SIZE / 2, y + CORNER_SIZE / 2, 0);
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(90));
        poseStack.translate(-CORNER_SIZE / 2, -CORNER_SIZE / 2, 0);
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        poseStack.popPose();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    // Layout class matching your custom GUI structure
    private class ScreenLayout {
        final int contentX;
        final int contentY;
        final int contentWidth;
        final int contentHeight;

        ScreenLayout(int screenWidth, int screenHeight) {
            int sideMargin = screenWidth / 16;
            int topMargin = screenHeight / 16;

            contentX = sideMargin;
            contentY = topMargin;
            contentWidth = screenWidth - (2 * sideMargin);
            contentHeight = screenHeight - (2 * topMargin);
        }
    }
}