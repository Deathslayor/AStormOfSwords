package net.darkflameproduction.agotmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.network.ClaimTownHallPacket;
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

    private final BlockPos townHallPos;
    private int bedCount = 0;
    private int citizenCount = 0;
    private int currentRadius = 16; // Store the actual radius from server
    private String townName = "Unnamed Town"; // Store the town name
    private static TownHallScreen currentInstance = null;

    private boolean isClaimed = false;
    private String claimedByHouse = "";
    private net.minecraft.client.gui.components.Button claimButton;

    // NEW: Job management data
    private int availableJobCount = 0;
    private int assignedJobCount = 0;
    private int totalJobCount = 0;
    private int joblessCount = 0;

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

        // Calculate panel dimensions for positioning (increased for job data)
        int panelWidth = Math.min(400, width - 40); // Increased width
        int panelHeight = Math.min(350, height - 40); // Increased height for job info
        int panelX = (width - panelWidth) / 2;
        int panelY = (height - panelHeight) / 2;

        // Create town name input field
        int inputWidth = 200;
        int inputHeight = 20;
        int inputX = panelX + (panelWidth - inputWidth) / 2;
        int inputY = panelY + panelHeight - 80; // Moved up to make room for claim button

        townNameInput = new net.minecraft.client.gui.components.EditBox(
                font, inputX, inputY, inputWidth, inputHeight,
                net.minecraft.network.chat.Component.literal("Town Name")
        );

        townNameInput.setMaxLength(32);
        townNameInput.setValue(townName);
        townNameInput.setCanLoseFocus(true);

        // Add save button next to the input field
        int buttonWidth = 50;
        int buttonX = inputX + inputWidth + 5;

        addRenderableWidget(townNameInput);
        addRenderableWidget(net.minecraft.client.gui.components.Button.builder(
                net.minecraft.network.chat.Component.literal("Save"),
                button -> saveTownName()
        ).pos(buttonX, inputY).size(buttonWidth, inputHeight).build());

        // Add claim button (only visible if not claimed)
        int claimButtonWidth = 100;
        int claimButtonX = panelX + (panelWidth - claimButtonWidth) / 2;
        int claimButtonY = panelY + panelHeight - 50;

        claimButton = net.minecraft.client.gui.components.Button.builder(
                net.minecraft.network.chat.Component.literal("Claim Town"),
                button -> claimTown()
        ).pos(claimButtonX, claimButtonY).size(claimButtonWidth, inputHeight).build();

        claimButton.visible = !isClaimed;
        addRenderableWidget(claimButton);
    }

    private void claimTown() {
        // Send packet to server to claim the town hall
        net.minecraft.client.Minecraft.getInstance().getConnection().send(
                new ClaimTownHallPacket(townHallPos)
        );
    }

    @Override
    public void onClose() {
        super.onClose();
        if (currentInstance == this) {
            currentInstance = null;
        }
    }

    // UPDATED: Static method for packet handler to update data with job management
    public static void updateTownHallData(int bedCount, int citizenCount, int radius, String townName,
                                          boolean isClaimed, String claimedByHouse,
                                          int availableJobCount, int assignedJobCount,
                                          int totalJobCount, int joblessCount) {
        if (currentInstance != null) {
            currentInstance.bedCount = bedCount;
            currentInstance.citizenCount = citizenCount;
            currentInstance.currentRadius = radius;
            currentInstance.townName = townName;
            currentInstance.isClaimed = isClaimed;
            currentInstance.claimedByHouse = claimedByHouse;

            // NEW: Update job management data
            currentInstance.availableJobCount = availableJobCount;
            currentInstance.assignedJobCount = assignedJobCount;
            currentInstance.totalJobCount = totalJobCount;
            currentInstance.joblessCount = joblessCount;

            // Update the input field if it exists
            if (currentInstance.townNameInput != null) {
                currentInstance.townNameInput.setValue(townName);
            }

            // Update claim button visibility
            if (currentInstance.claimButton != null) {
                currentInstance.claimButton.visible = !isClaimed;
            }
        }
    }

    // BACKWARDS COMPATIBILITY: Static method for packet handler without job data
    public static void updateTownHallData(int bedCount, int citizenCount, int radius, String townName,
                                          boolean isClaimed, String claimedByHouse) {
        updateTownHallData(bedCount, citizenCount, radius, townName, isClaimed, claimedByHouse, 0, 0, 0, 0);
    }

    public static TownHallScreen getCurrentInstance() {
        return currentInstance;
    }

    /**
     * Get the town size category for display
     */
    private String getTownSizeCategory() {
        if (citizenCount < 5) {
            return "Small Hovel";
        } else if (citizenCount < 10) {
            return "Farmstead";
        } else if (citizenCount < 30) {
            return "Small Settlement";
        } else if (citizenCount < 50) {
            return "Small Village";
        } else if (citizenCount < 100) {
            return "Village";
        } else if (citizenCount < 150) {
            return "Large Village";
        } else if (citizenCount < 200) {
            return "Small Town";
        } else if (citizenCount < 400) {
            return "Town";
        } else if (citizenCount < 1000) {
            return "Large Town";
        } else if (citizenCount < 1500) {
            return "Small City";
        } else if (citizenCount < 5000) {
            return "City";
        } else if (citizenCount < 10000) {
            return "Relevant City";
        } else if (citizenCount < 15000) {
            return "Major City";
        } else {
            return "Trade Capital";
        }
    }

    /**
     * NEW: Get job market status for display
     */
    private String getJobMarketStatus() {
        if (totalJobCount == 0) {
            return "No Jobs Available";
        }

        if (joblessCount == 0 && availableJobCount == 0) {
            return "Full Employment";
        }

        if (joblessCount > 0 && availableJobCount > 0) {
            return "Jobs Available";
        }

        if (joblessCount > 0 && availableJobCount == 0) {
            return "Job Shortage";
        }

        if (joblessCount == 0 && availableJobCount > 0) {
            return "Labor Shortage";
        }

        return "Stable";
    }

    /**
     * NEW: Get job market status color
     */
    private int getJobMarketStatusColor() {
        String status = getJobMarketStatus();
        return switch (status) {
            case "Full Employment", "Stable" -> 0xFF2E7D32; // Green
            case "Jobs Available" -> 0xFF1565C0; // Blue
            case "Job Shortage" -> 0xFFFF8F00; // Orange
            case "Labor Shortage" -> 0xFFD32F2F; // Red
            case "No Jobs Available" -> 0xFF424242; // Gray
            default -> TEXT_COLOR;
        };
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Render background
        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7);

        // Calculate panel dimensions (increased for job data)
        int panelWidth = Math.min(400, width - 40);
        int panelHeight = Math.min(350, height - 40);
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
        drawContent(guiGraphics, panelX, panelY + 50, panelWidth, panelHeight - 120); // Adjusted for larger content

        // Draw instruction text above input field
        String instructionText = "Town Name:";
        int instructionX = panelX + 20;
        int instructionY = panelY + panelHeight - 100; // Adjusted position
        guiGraphics.drawString(font, instructionText, instructionX, instructionY, TEXT_COLOR, false);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void saveTownName() {
        String newName = townNameInput.getValue().trim();
        if (!newName.equals(townName)) {
            // Send packet to server to update the town name
            net.minecraft.client.Minecraft.getInstance().getConnection().send(
                    new net.darkflameproduction.agotmod.network.UpdateTownNamePacket(townHallPos, newName)
            );
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
        // Town information with ownership
        java.util.List<String> townInfo = new java.util.ArrayList<>();
        townInfo.add("Town Statistics");
        townInfo.add("");

        if (isClaimed) {
            townInfo.add("Owned by: " + claimedByHouse);
            townInfo.add("");
        } else {
            townInfo.add("Unclaimed Territory");
            townInfo.add("");
        }

        townInfo.add("Total Beds: " + bedCount);
        townInfo.add("Citizens: " + citizenCount);
        townInfo.add("Current Radius: " + currentRadius + " blocks");

        // NEW: Add job management section
        townInfo.add("");
        townInfo.add("=== EMPLOYMENT ===");
        townInfo.add("Total Jobs: " + totalJobCount);
        townInfo.add("Available: " + availableJobCount);
        townInfo.add("Occupied: " + assignedJobCount);
        townInfo.add("Unemployed: " + joblessCount);
        townInfo.add("Status: " + getJobMarketStatus());

        // NEW: Add employment rate if there are jobs
        if (totalJobCount > 0) {
            int employmentRate = (assignedJobCount * 100) / totalJobCount;
            townInfo.add("Job Utilization: " + employmentRate + "%");
        }

        int textY = y + 20;
        for (String info : townInfo) {
            if (info.isEmpty()) {
                textY += font.lineHeight / 2;
                continue;
            }

            int textX = x + (width - font.width(info)) / 2;
            int color = TEXT_COLOR;

            if (info.startsWith("Total Beds") || info.startsWith("Citizens") || info.startsWith("Current Radius")) {
                color = 0xFF2E7D32; // Dark green for main stats
            } else if (info.startsWith("Owned by:")) {
                color = 0xFF1565C0; // Blue for ownership
            } else if (info.equals("Unclaimed Territory")) {
                color = 0xFFD32F2F; // Red for unclaimed
            } else if (info.equals("Town Statistics")) {
                float scale = 1.2f;
                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(scale, scale, 1.0f);
                int headerColor = 0xFF1A237E;
                int scaledX = (int)((x + (width - font.width(info) * scale) / 2) / scale);
                guiGraphics.drawString(font, info, scaledX, (int)(textY / scale), headerColor, false);
                guiGraphics.pose().popPose();
                textY += (int)(font.lineHeight * scale) + 5;
                continue;
            }
            // NEW: Job management colors
            else if (info.equals("=== EMPLOYMENT ===")) {
                float scale = 1.1f;
                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(scale, scale, 1.0f);
                int headerColor = 0xFF4A148C; // Purple for employment header
                int scaledX = (int)((x + (width - font.width(info) * scale) / 2) / scale);
                guiGraphics.drawString(font, info, scaledX, (int)(textY / scale), headerColor, false);
                guiGraphics.pose().popPose();
                textY += (int)(font.lineHeight * scale) + 3;
                continue;
            } else if (info.startsWith("Total Jobs") || info.startsWith("Job Utilization")) {
                color = 0xFF2E7D32; // Green for job stats
            } else if (info.startsWith("Available")) {
                color = availableJobCount > 0 ? 0xFF388E3C : 0xFF757575; // Green if available, gray if none
            } else if (info.startsWith("Occupied")) {
                color = assignedJobCount > 0 ? 0xFF1976D2 : 0xFF757575; // Blue if occupied, gray if none
            } else if (info.startsWith("Unemployed")) {
                color = joblessCount > 0 ? 0xFFD32F2F : 0xFF2E7D32; // Red if unemployed, green if none
            } else if (info.startsWith("Status:")) {
                color = getJobMarketStatusColor();
            }

            guiGraphics.drawString(font, info, textX, textY, color, false);
            textY += font.lineHeight + 4;
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