package net.darkflameproduction.agotmod.inventory;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.darkflameproduction.agotmod.inventory.NPCInventoryMenu;

public class NPCInventoryScreen extends AbstractContainerScreen<NPCInventoryMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/generic_54.png");
    private static final ResourceLocation PLAYER_INVENTORY = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/inventory.png");

    public NPCInventoryScreen(NPCInventoryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 212; // Wider to accommodate equipment slots
        this.imageHeight = 222; // Taller for NPC inventory + player inventory
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = 44; // Move title right to account for equipment slots
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        // Render main inventory background (NPC inventory) - using the working blit signature
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                TEXTURE, x + 36, y, 0, 0, 176, 132, 256, 256);

        // Render player inventory background
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                PLAYER_INVENTORY, x + 36, y + 132, 0, 84, 176, 90, 256, 256);

        // Render equipment slots backgrounds
        renderEquipmentSlots(guiGraphics, x, y);
    }

    private void renderEquipmentSlots(GuiGraphics guiGraphics, int x, int y) {
        // Equipment panel background (left side)
        guiGraphics.fill(x, y, x + 36, y + 222, 0xFF8B8B8B); // Gray background
        guiGraphics.fill(x + 1, y + 1, x + 35, y + 221, 0xFF373737); // Dark inner

        // Render individual equipment slot backgrounds
        // Main hand slot
        renderSlotBackground(guiGraphics, x + 7, y + 17);

        // Off hand slot
        renderSlotBackground(guiGraphics, x + 7, y + 35);

        // Armor slots
        renderArmorSlotBackground(guiGraphics, x + 7, y + 53);  // Head
        renderArmorSlotBackground(guiGraphics, x + 7, y + 71);  // Chest
        renderArmorSlotBackground(guiGraphics, x + 7, y + 89);  // Legs
        renderArmorSlotBackground(guiGraphics, x + 7, y + 107); // Feet

        // Render equipment slot icons/overlays
        renderEquipmentIcons(guiGraphics, x, y);
    }

    private void renderSlotBackground(GuiGraphics guiGraphics, int x, int y) {
        // Standard slot background (18x18)
        guiGraphics.fill(x, y, x + 18, y + 18, 0xFF8B8B8B); // Gray border
        guiGraphics.fill(x + 1, y + 1, x + 17, y + 17, 0xFF373737); // Dark inner
    }

    private void renderArmorSlotBackground(GuiGraphics guiGraphics, int x, int y) {
        // Armor slot background with different color
        guiGraphics.fill(x, y, x + 18, y + 18, 0xFF8B8B8B); // Gray border
        guiGraphics.fill(x + 1, y + 1, x + 17, y + 17, 0xFF4A4A4A); // Slightly lighter for armor
    }

    private void renderEquipmentIcons(GuiGraphics guiGraphics, int x, int y) {
        // You can add icon overlays here to show what each slot is for
        // For now, we'll just use the slot positions

        // Main hand icon (sword silhouette)
        // guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
        //         PLAYER_INVENTORY, x + 8, y + 18, 16, 16, 16, 16, 256, 256);

        // Off hand icon (shield silhouette)
        // guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
        //         PLAYER_INVENTORY, x + 8, y + 36, 32, 16, 16, 16, 256, 256);

        // Armor slot icons could be rendered here as well
        // Head: helmet icon
        // Chest: chestplate icon
        // Legs: leggings icon
        // Feet: boots icon
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Render title (moved right to account for equipment slots)
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);

        // Player inventory label removed to fix alignment issues

        // Render equipment labels
        renderEquipmentLabels(guiGraphics);
    }

    private void renderEquipmentLabels(GuiGraphics guiGraphics) {
        // Optional: Add small text labels for equipment slots
        // These would be very small and positioned next to the slots

        // You could add single letter indicators:
        // guiGraphics.drawString(this.font, "M", 2, 21, 0xFFFFFF, false); // Main hand
        // guiGraphics.drawString(this.font, "O", 2, 39, 0xFFFFFF, false); // Off hand
        // guiGraphics.drawString(this.font, "H", 2, 57, 0xFFFFFF, false); // Head
        // guiGraphics.drawString(this.font, "C", 2, 75, 0xFFFFFF, false); // Chest
        // guiGraphics.drawString(this.font, "L", 2, 93, 0xFFFFFF, false); // Legs
        // guiGraphics.drawString(this.font, "F", 2, 111, 0xFFFFFF, false); // Feet
    }
}