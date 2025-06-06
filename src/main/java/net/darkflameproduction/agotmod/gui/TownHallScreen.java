package net.darkflameproduction.agotmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.core.BlockPos;

@OnlyIn(Dist.CLIENT)
public class TownHallScreen extends Screen {
    // Using the same texture resources as your GrocerInventoryScreen
    private static final ResourceLocation PAPER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paper.png");
    private static final ResourceLocation PAPER_SIDE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paperside.png");
    private static final ResourceLocation PAPER_SIDETOP_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papersidetop.png");
    private static final ResourceLocation PAPER_CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papercorner.png");

    // Colors matching your custom GUI
    private static final int TEXT_COLOR = 0xFF000000;
    private static final int BORDER_PILLAR_WIDTH = 6;
    private static final int BORDER_HEIGHT = 6;
    private static final int CORNER_SIZE = 12;

    // Dynamic radius constants for display calculations
    private static final int BASE_RADIUS = 16;
    private static final int FIRST_TIER_CITIZENS = 10;
    private static final int FIRST_TIER_EXPANSION = 5;
    private static final int SECOND_TIER_CITIZENS = 20;
    private static final int SECOND_TIER_EXPANSION = 1;
    private static final int THIRD_TIER_EXPANSION = 5;

    private final BlockPos townHallPos;
    private int bedCount = 0;
    private int citizenCount = 0;
    private int currentRadius = BASE_RADIUS; // Store the actual radius from server
    private String townName = "Unnamed Town"; // Store the town name
    private static TownHallScreen currentInstance = null;

    // Text input field for town name
    private net.minecraft.client.gui.components.EditBox townNameInput;

    public TownHallScreen(BlockPos pos) {
        super(Component.literal("Town Hall"));
        this.townHallPos = pos;
        currentInstance = this;
    }

    @Override
    protected void init() {
        super.init();

        // Calculate panel dimensions for positioning
        int panelWidth = Math.min(450, width - 40);
        int panelHeight = Math.min(350, height - 40);
        int panelX = (width - panelWidth) / 2;
        int panelY = (height - panelHeight) / 2;

        // Create town name input field
        int inputWidth = 200;
        int inputHeight = 20;
        int inputX = panelX + (panelWidth - inputWidth) / 2;
        int inputY = panelY + panelHeight - 50; // Position near bottom of panel

        townNameInput = new net.minecraft.client.gui.components.EditBox(
                font, inputX, inputY, inputWidth, inputHeight,
                net.minecraft.network.chat.Component.literal("Town Name")
        );

        townNameInput.setMaxLength(32); // Limit to 32 characters
        townNameInput.setValue(townName); // Set current town name
        townNameInput.setCanLoseFocus(true);

        // Add save button next to the input field
        int buttonWidth = 50;
        int buttonX = inputX + inputWidth + 5;

        addRenderableWidget(townNameInput);
        addRenderableWidget(net.minecraft.client.gui.components.Button.builder(
                net.minecraft.network.chat.Component.literal("Save"),
                button -> saveTownName()
        ).pos(buttonX, inputY).size(buttonWidth, inputHeight).build());
    }

    @Override
    public void onClose() {
        super.onClose();
        if (currentInstance == this) {
            currentInstance = null;
        }
    }

    // Static method for packet handler to update data with radius and town name
    public static void updateTownHallData(int bedCount, int citizenCount, int radius, String townName) {
        if (currentInstance != null) {
            currentInstance.bedCount = bedCount;
            currentInstance.citizenCount = citizenCount;
            currentInstance.currentRadius = radius;
            currentInstance.townName = townName;
            // Update the input field if it exists
            if (currentInstance.townNameInput != null) {
                currentInstance.townNameInput.setValue(townName);
            }
        }
    }

    // Backwards compatibility method for old packet handlers
    public static void updateTownHallData(int bedCount, int citizenCount, int radius) {
        if (currentInstance != null) {
            currentInstance.bedCount = bedCount;
            currentInstance.citizenCount = citizenCount;
            currentInstance.currentRadius = radius;
            // Don't update town name for backwards compatibility
        }
    }

    // Backwards compatibility method for old packet handlers
    public static void updateTownHallData(int bedCount, int citizenCount) {
        if (currentInstance != null) {
            currentInstance.bedCount = bedCount;
            currentInstance.citizenCount = citizenCount;
            // Calculate radius if not provided
            currentInstance.currentRadius = currentInstance.calculateRadius(citizenCount);
        }
    }

    public static TownHallScreen getCurrentInstance() {
        return currentInstance;
    }

    /**
     * Calculate radius based on citizen count (matches TownHallBlockEntity logic)
     */
    private int calculateRadius(int citizens) {
        if (citizens <= FIRST_TIER_CITIZENS) {
            // First 10 citizens: base + (citizens * 5)
            return BASE_RADIUS + (citizens * FIRST_TIER_EXPANSION);
        } else if (citizens <= SECOND_TIER_CITIZENS) {
            // Citizens 11-20: base + (10 * 5) + ((citizens - 10) * 1)
            int firstTierBonus = FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION;
            int secondTierBonus = (citizens - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION;
            return BASE_RADIUS + firstTierBonus + secondTierBonus;
        } else {
            // Citizens 21+: base + (10 * 5) + (10 * 1) + ((citizens - 20) / 5)
            int firstTierBonus = FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION;
            int secondTierBonus = (SECOND_TIER_CITIZENS - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION;
            int thirdTierBonus = (citizens - SECOND_TIER_CITIZENS) / THIRD_TIER_EXPANSION;
            return BASE_RADIUS + firstTierBonus + secondTierBonus + thirdTierBonus;
        }
    }

    /**
     * Get the town size category for display
     */
    private String getTownSizeCategory() {
        if (citizenCount <= 5) {
            return "Small Town";
        } else if (citizenCount <= 15) {
            return "Medium Town";
        } else if (citizenCount <= 25) {
            return "Large Town";
        } else {
            return "Major City";
        }
    }

    /**
     * Calculate radius for next citizen milestone
     */
    private String getNextMilestoneInfo() {
        if (citizenCount < FIRST_TIER_CITIZENS) {
            int nextMilestone = citizenCount + 1;
            int nextRadius = calculateRadius(nextMilestone);
            return "Next citizen: +" + FIRST_TIER_EXPANSION + " blocks (radius " + nextRadius + ")";
        } else if (citizenCount < SECOND_TIER_CITIZENS) {
            int nextMilestone = citizenCount + 1;
            int nextRadius = calculateRadius(nextMilestone);
            return "Next citizen: +" + SECOND_TIER_EXPANSION + " block (radius " + nextRadius + ")";
        } else {
            // For citizens 21+, show when the next radius increase will happen
            int citizensInThirdTier = citizenCount - SECOND_TIER_CITIZENS;
            int citizensUntilNext = THIRD_TIER_EXPANSION - (citizensInThirdTier % THIRD_TIER_EXPANSION);
            if (citizensUntilNext == THIRD_TIER_EXPANSION) citizensUntilNext = 0; // Already at milestone

            if (citizensUntilNext == 0) {
                int nextMilestone = citizenCount + THIRD_TIER_EXPANSION;
                int nextRadius = calculateRadius(nextMilestone);
                return "Next radius increase: +" + THIRD_TIER_EXPANSION + " citizens (radius " + nextRadius + ")";
            } else {
                int nextRadius = calculateRadius(citizenCount + citizensUntilNext);
                return "Next radius increase: +" + citizensUntilNext + " citizens (radius " + nextRadius + ")";
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Render background
        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7);

        // Calculate panel dimensions
        int panelWidth = Math.min(450, width - 40); // Slightly wider for more info
        int panelHeight = Math.min(350, height - 40); // Slightly taller for more info
        int panelX = (width - panelWidth) / 2;
        int panelY = (height - panelHeight) / 2;

        // Render paper panel
        renderPaperPanel(guiGraphics, panelX, panelY, panelWidth, panelHeight);

        // Draw title with town name
        String title = townName + " - " + getTownSizeCategory();
        int titleWidth = font.width(title);
        int titleX = panelX + (panelWidth - titleWidth) / 2;
        int titleY = panelY + 20;
        guiGraphics.drawString(font, title, titleX, titleY, TEXT_COLOR, false);

        // Draw separator line
        guiGraphics.fill(panelX + panelWidth/8, panelY + 40,
                panelX + panelWidth - panelWidth/8, panelY + 41,
                TEXT_COLOR);

        // Content area starts here
        drawContent(guiGraphics, panelX, panelY + 50, panelWidth, panelHeight - 70);

        // Draw instruction text above input field
        String instructionText = "Town Name:";
        int instructionX = panelX + 20;
        int instructionY = panelY + panelHeight - 70;
        guiGraphics.drawString(font, instructionText, instructionX, instructionY, TEXT_COLOR, false);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    /**
     * Save the town name when the save button is clicked
     */
    private void saveTownName() {
        String newName = townNameInput.getValue().trim();
        if (!newName.equals(townName)) {
            // Send packet to server to update the town name
            net.minecraft.client.Minecraft.getInstance().getConnection().send(
                    new net.darkflameproduction.agotmod.network.UpdateTownNamePacket(townHallPos, newName)
            );
            System.out.println("DEBUG: Sent town name update: '" + newName + "' for position " + townHallPos);
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Handle Enter key in the text field
        if (townNameInput.isFocused() && keyCode == 257) { // 257 = Enter key
            saveTownName();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void drawContent(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        // Town Hall information with dynamic radius details
        String[] townInfo = {
                "Town Statistics",
                "",
                "Total Beds: " + bedCount,
                "Citizens: " + citizenCount,
                "Current Radius: " + currentRadius + " blocks",
                "",
                "Radius Breakdown:",
                "• Base radius: " + BASE_RADIUS + " blocks",
                citizenCount <= FIRST_TIER_CITIZENS ?
                        "• Citizens bonus: " + citizenCount + " × " + FIRST_TIER_EXPANSION + " = " + (citizenCount * FIRST_TIER_EXPANSION) + " blocks" :
                        "• First 10 citizens: 10 × " + FIRST_TIER_EXPANSION + " = " + (FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION) + " blocks",
                citizenCount > FIRST_TIER_CITIZENS && citizenCount <= SECOND_TIER_CITIZENS ?
                        "• Citizens 11-20: " + (citizenCount - FIRST_TIER_CITIZENS) + " × " + SECOND_TIER_EXPANSION + " = " + ((citizenCount - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION) + " blocks" :
                        citizenCount > SECOND_TIER_CITIZENS ? "• Citizens 11-20: 10 × " + SECOND_TIER_EXPANSION + " = " + ((SECOND_TIER_CITIZENS - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION) + " blocks" : "",
                citizenCount > SECOND_TIER_CITIZENS ?
                        "• Citizens 21+: " + (citizenCount - SECOND_TIER_CITIZENS) + " ÷ " + THIRD_TIER_EXPANSION + " = " + ((citizenCount - SECOND_TIER_CITIZENS) / THIRD_TIER_EXPANSION) + " blocks" : "",
                "",
                "Growth Information:",
                getNextMilestoneInfo(),
                "",
                "Scan Details:",
                "Scan height: ±64 blocks",
                "Daily scan at: 10000 game time"
        };

        int textY = y + 10;
        for (String info : townInfo) {
            if (info.isEmpty()) {
                textY += font.lineHeight / 2; // Smaller gap for empty lines
                continue;
            }

            int textX = x + 20;

            // Different colors for different types of info
            int color = TEXT_COLOR;
            if (info.startsWith("Total Beds") || info.startsWith("Citizens") || info.startsWith("Current Radius")) {
                color = 0xFF2E7D32; // Dark green for main stats
            } else if (info.startsWith("• Base radius") || info.startsWith("• Citizens bonus") || info.startsWith("• First 10") || info.startsWith("• Additional")) {
                color = 0xFF1976D2; // Blue for radius breakdown
            } else if (info.startsWith("Next citizen")) {
                color = 0xFF9C27B0; // Purple for growth info
            } else if (info.startsWith("Scan")) {
                color = 0xFF666666; // Gray for scan info
            } else if (info.equals("Town Statistics") || info.equals("Radius Breakdown:") || info.equals("Growth Information:") || info.equals("Scan Details:")) {
                // Scale the header text
                float scale = 1.1f;
                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(scale, scale, 1.0f);
                int headerColor = info.equals("Town Statistics") ? 0xFF1A237E : 0xFF424242; // Dark blue for main header, dark gray for sub-headers
                guiGraphics.drawString(font, info, (int)(textX / scale), (int)(textY / scale), headerColor, false);
                guiGraphics.pose().popPose();
                textY += (int)(font.lineHeight * scale) + 3;
                continue;
            }

            guiGraphics.drawString(font, info, textX, textY, color, false);
            textY += font.lineHeight + 2;
        }
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

        // Render paper texture
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
        // Side borders
        drawRotatableBorder(guiGraphics, PAPER_SIDE_TEXTURE,
                x, y + cornerSize,
                borderPillarWidth, height - (cornerSize * 2),
                0);

        drawRotatableBorder(guiGraphics, PAPER_SIDE_TEXTURE,
                x + width - borderPillarWidth, y + cornerSize,
                borderPillarWidth, height - (cornerSize * 2),
                180);

        // Top and bottom borders
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

    private void drawRotatableBorder(GuiGraphics guiGraphics, ResourceLocation texture,
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

    private void drawRotatableCorner(GuiGraphics guiGraphics, ResourceLocation texture,
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

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Don't call super to avoid blur effect
    }
}