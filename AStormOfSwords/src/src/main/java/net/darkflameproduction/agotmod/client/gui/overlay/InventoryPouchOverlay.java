package net.darkflameproduction.agotmod.client.gui.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.network.ClientCoinHandler;
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

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = AGoTMod.MOD_ID, value = Dist.CLIENT)
public class InventoryPouchOverlay {
    private static boolean isRecipeBookOpen = false;
    private static final String CONFIG_FILE = "config/agotmod_recipe_book_state.txt";

    static {
        // Load state when class is first loaded
        loadRecipeBookState();
    }

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
            // Recipe book is open - bundle should move right
            bundleIconX = standardLeftPos + 8 + (8 * 18) + 77; // +77 offset when recipe book is open
            bundleIconY = standardTopPos + 61;
        } else {
            // Recipe book is closed - standard position
            bundleIconX = standardLeftPos + 8 + (8 * 18);
            bundleIconY = standardTopPos + 61;
        }

        // Render the bundle icon
        renderBundleIcon(guiGraphics, bundleIconX, bundleIconY);

        // Render bundle tooltip if hovering
        int mouseX = (int) event.getMouseX();
        int mouseY = (int) event.getMouseY();
        if (isHoveringBundle(bundleIconX, bundleIconY, mouseX, mouseY)) {
            renderBundleTooltip(guiGraphics, bundleIconX, bundleIconY, mouseX, mouseY);
        }

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

            // Save state to file
            saveRecipeBookState();

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

    private static boolean isHoveringBundle(int bundleIconX, int bundleIconY, int mouseX, int mouseY) {
        int bundleSize = 16; // Standard item icon size
        return mouseX >= bundleIconX && mouseX < bundleIconX + bundleSize &&
                mouseY >= bundleIconY && mouseY < bundleIconY + bundleSize;
    }

    private static void renderBundleTooltip(GuiGraphics guiGraphics, int bundleIconX, int bundleIconY, int mouseX, int mouseY) {
        // Get actual player balance from client handler
        long totalCoins = ClientCoinHandler.getClientCoinBalance();

        // Debug logging - always log to see if tooltip is being rendered
        AGoTMod.LOGGER.info("Rendering tooltip - Client balance: {}", totalCoins);

        // Create tooltip lines
        List<String> tooltipLines = new ArrayList<>();
        tooltipLines.add("Coin Pouch");
        tooltipLines.add("Balance: " + totalCoins + " Halfpennies");
        tooltipLines.add(""); // Empty line for spacing

        // Calculate coin breakdown
        long remaining = totalCoins;

        // Convert halfpennies to larger denominations (working from largest to smallest)
        // How many pennies can we make? (2 halfpennies = 1 penny)
        long pennies = remaining / 2L;
        remaining = remaining % 2L;

        // How many halfgroats can we make from pennies? (2 pennies = 1 halfgroat)
        long halfgroats = pennies / 2L;
        pennies = pennies % 2L;

        // How many groats? (2 halfgroats = 1 groat)
        long groats = halfgroats / 2L;
        halfgroats = halfgroats % 2L;

        // How many stars? (2 groats = 1 star)
        long stars = groats / 2L;
        groats = groats % 2L;

        // How many stags? (7 stars = 1 stag)
        long stags = stars / 7L;
        stars = stars % 7L;

        // How many moons? (7 stags = 1 moon)
        long moons = stags / 7L;
        stags = stags % 7L;

        // How many dragons? (30 moons = 1 dragon)
        long dragons = moons / 30L;
        moons = moons % 30L;

        // The remaining amount stays as halfpennies
        long remainingHalfpennies = remaining;

        // Add denominations to tooltip (only show if count > 0)
        if (dragons > 0) tooltipLines.add("Golden Dragons: " + dragons);
        if (moons > 0) tooltipLines.add("Silver Moons: " + moons);
        if (stags > 0) tooltipLines.add("Silver Stags: " + stags);
        if (stars > 0) tooltipLines.add("Copper Stars: " + stars);
        if (groats > 0) tooltipLines.add("Copper Groats: " + groats);
        if (halfgroats > 0) tooltipLines.add("Copper Halfgroats: " + halfgroats);
        if (pennies > 0) tooltipLines.add("Copper Pennies: " + pennies);
        if (remainingHalfpennies > 0) tooltipLines.add("Copper Halfpennies: " + remainingHalfpennies);

        // If no coins, show a message
        if (totalCoins == 0) {
            tooltipLines.add("No coins");
        }

        // Position tooltip to the right of the bundle
        int tooltipX = bundleIconX + 20; // 20 pixels to the right of bundle
        int tooltipY = bundleIconY;

        // Calculate tooltip dimensions
        Minecraft minecraft = Minecraft.getInstance();
        int maxTextWidth = 0;
        for (String line : tooltipLines) {
            maxTextWidth = Math.max(maxTextWidth, minecraft.font.width(line));
        }

        int textHeight = minecraft.font.lineHeight;
        int totalHeight = tooltipLines.size() * textHeight;

        // Add padding
        int padding = 4;
        int backgroundWidth = maxTextWidth + (padding * 2);
        int backgroundHeight = totalHeight + (padding * 2);

        // Ensure tooltip doesn't go off screen
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();

        if (tooltipX + backgroundWidth > screenWidth) {
            tooltipX = bundleIconX - backgroundWidth - 5; // Show to the left instead
        }
        if (tooltipY + backgroundHeight > screenHeight) {
            tooltipY = screenHeight - backgroundHeight - 5;
        }

        // Draw tooltip background with nice styling
        guiGraphics.fill(tooltipX - 1, tooltipY - 1,
                tooltipX + backgroundWidth + 1, tooltipY + backgroundHeight + 1,
                0xFF8B4513); // Brown border
        guiGraphics.fill(tooltipX, tooltipY,
                tooltipX + backgroundWidth, tooltipY + backgroundHeight,
                0xF0100010); // Dark transparent background

        // Draw the text lines
        for (int i = 0; i < tooltipLines.size(); i++) {
            String line = tooltipLines.get(i);
            if (!line.isEmpty()) { // Don't draw empty lines
                // Color code the text
                int textColor = 0xFFFFFF; // White by default
                if (line.contains("Golden")) textColor = 0xFFD700; // Gold
                else if (line.contains("Silver")) textColor = 0xC0C0C0; // Silver
                else if (line.contains("Copper")) textColor = 0xB87333; // Copper
                else if (line.equals("Coin Pouch")) textColor = 0xFFD700; // Gold for title

                guiGraphics.drawString(minecraft.font, line,
                        tooltipX + padding, tooltipY + padding + (i * textHeight),
                        textColor);
            }
        }
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
            buttonX = standardLeftPos + 147 + 34;
            buttonY = standardTopPos + 61;
        } else {
            // Recipe book is closed - button is at standard recipe book button position
            buttonX = standardLeftPos + 104;
            buttonY = standardTopPos + 61;
        }

        // Debug: Draw a semi-transparent overlay to see the button area
        // Uncomment this line to see where the button is:
        // guiGraphics.fill(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight, 0x40FF0000);
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

    private static void saveRecipeBookState() {
        try {
            Path configPath = Paths.get(CONFIG_FILE);
            // Create config directory if it doesn't exist
            Files.createDirectories(configPath.getParent());

            // Write state to file
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(configPath))) {
                writer.println(isRecipeBookOpen ? "true" : "false");
            }

            AGoTMod.LOGGER.info("Saved recipe book state: {}", isRecipeBookOpen);
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Failed to save recipe book state: {}", e.getMessage());
        }
    }

    private static void loadRecipeBookState() {
        try {
            Path configPath = Paths.get(CONFIG_FILE);
            if (Files.exists(configPath)) {
                try (BufferedReader reader = Files.newBufferedReader(configPath)) {
                    String line = reader.readLine();
                    if (line != null) {
                        isRecipeBookOpen = "true".equalsIgnoreCase(line.trim());
                        AGoTMod.LOGGER.info("Loaded recipe book state: {}", isRecipeBookOpen);
                    }
                }
            }
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Failed to load recipe book state: {}", e.getMessage());
            // Default to false if loading fails
            isRecipeBookOpen = false;
        }
    }
}