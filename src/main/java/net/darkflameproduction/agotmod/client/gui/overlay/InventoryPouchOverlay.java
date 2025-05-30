package net.darkflameproduction.agotmod.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, value = Dist.CLIENT)
public class InventoryPouchOverlay {
    private static boolean isRecipeBookOpen = false;

    @SubscribeEvent
    public static void onScreenRender(ScreenEvent.Render.Post event) {
        if (!(event.getScreen() instanceof InventoryScreen inventoryScreen)) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();

        // Calculate positions
        int standardLeftPos = (inventoryScreen.width - 176) / 2;
        int standardTopPos = (inventoryScreen.height - 166) / 2;

        // Calculate bundle position based on our tracked state
        int bundleIconX, bundleIconY;

        if (isRecipeBookOpen) {
            // Recipe book is open - bundle should move the same amount as the button
            // Button moves 77 pixels right, so bundle should too
            bundleIconX = standardLeftPos + 8 + (8 * 18) + 77; // Same +77 offset as button
            bundleIconY = standardTopPos + 61;
        } else {
            // Recipe book is closed - standard position
            bundleIconX = standardLeftPos + 8 + (8 * 18);
            bundleIconY = standardTopPos + 61;
        }

        // Render the bundle icon
        renderBundleIcon(guiGraphics, bundleIconX, bundleIconY);

        // Render invisible button over recipe book
        renderInvisibleRecipeBookButton(guiGraphics, inventoryScreen);
    }

    @SubscribeEvent
    public static void onMouseClicked(ScreenEvent.MouseButtonPressed.Pre event) {
        if (!(event.getScreen() instanceof InventoryScreen inventoryScreen)) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) {
            return;
        }

        double mouseX = event.getMouseX();
        double mouseY = event.getMouseY();
        int button = event.getButton();

        // Only handle left clicks
        if (button != 0) return;

        // Check if clicking on our invisible recipe book button
        if (isClickingRecipeBookButton(inventoryScreen, (int)mouseX, (int)mouseY)) {
            // Toggle our state
            isRecipeBookOpen = !isRecipeBookOpen;
            AGoTMod.LOGGER.info("Recipe book state changed to: {}", isRecipeBookOpen);

            // Don't cancel the event - let it pass through to vanilla recipe book
            return;
        }
    }

    @SubscribeEvent
    public static void onScreenClosed(ScreenEvent.Closing event) {
        if (event.getScreen() instanceof InventoryScreen) {
            // Keep the state when inventory is closed - don't reset
            AGoTMod.LOGGER.info("Inventory closed, keeping recipe book state: {}", isRecipeBookOpen);
        }
    }

    private static void renderBundleIcon(GuiGraphics guiGraphics, int bundleIconX, int bundleIconY) {
        // Save current render state
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // Create a bundle itemstack for rendering
        ItemStack bundleStack = new ItemStack(Items.BUNDLE);

        // Render just the bundle item icon
        guiGraphics.renderItem(bundleStack, bundleIconX, bundleIconY);
    }

    private static void renderInvisibleRecipeBookButton(GuiGraphics guiGraphics, InventoryScreen inventoryScreen) {
        // Calculate button position based on current state
        int standardLeftPos = (inventoryScreen.width - 176) / 2;
        int standardTopPos = (inventoryScreen.height - 166) / 2;

        int buttonX, buttonY;
        int buttonWidth = 20;
        int buttonHeight = 18;

        if (isRecipeBookOpen) {
            // Recipe book is open - button should be over the green recipe book button
            // Moving 8 pixels more left to perfectly cover the green book
            buttonX = standardLeftPos + 147 + 34; // Moved 8 pixels left from 42
            buttonY = standardTopPos + 61;
        } else {
            // Recipe book is closed - button is at standard recipe book button position
            buttonX = standardLeftPos + 104; // Standard recipe book button position
            buttonY = standardTopPos + 61;
        }

        // Debug: Draw a semi-transparent overlay to see the button area
        // Uncomment this line to see where the button is:
        guiGraphics.fill(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight, 0x40FF0000);
    }

    private static boolean isClickingRecipeBookButton(InventoryScreen inventoryScreen, int mouseX, int mouseY) {
        int standardLeftPos = (inventoryScreen.width - 176) / 2;
        int standardTopPos = (inventoryScreen.height - 166) / 2;

        int buttonX, buttonY;
        int buttonWidth = 20;
        int buttonHeight = 18;

        if (isRecipeBookOpen) {
            // Recipe book is open - check green recipe book button position
            buttonX = standardLeftPos + 147 + 34;
            buttonY = standardTopPos + 61;
        } else {
            // Recipe book is closed - check standard button position
            buttonX = standardLeftPos + 104;
            buttonY = standardTopPos + 61;
        }

        boolean isClicking = mouseX >= buttonX && mouseX < buttonX + buttonWidth &&
                mouseY >= buttonY && mouseY < buttonY + buttonHeight;

        if (isClicking) {
            AGoTMod.LOGGER.info("Clicking recipe book button at: {}, {} (state: {})", mouseX, mouseY, isRecipeBookOpen);
        }

        return isClicking;
    }
}