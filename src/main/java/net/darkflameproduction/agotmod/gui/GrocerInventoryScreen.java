package net.darkflameproduction.agotmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.network.FinishTransactionPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

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

    private int totalItems = 0;
    private int totalPrice = 0;
    private long playerBalance = 0;

    // NEW: Grocer balance
    private long grocerBalance = 0;

    // Item pricing map - only items in this map can be stored by the grocer
    private static final Map<String, Integer> ALLOWED_ITEMS_PRICING = new HashMap<>();
    static {
        // Crops and Seeds
        ALLOWED_ITEMS_PRICING.put("minecraft:wheat", 2);
        ALLOWED_ITEMS_PRICING.put("minecraft:wheat_seeds", 8);
        ALLOWED_ITEMS_PRICING.put("minecraft:pumpkin", 23);
        ALLOWED_ITEMS_PRICING.put("minecraft:pumpkin_seeds", 6);
        ALLOWED_ITEMS_PRICING.put("minecraft:melon_slice", 23); // Assuming this is the melon item
        ALLOWED_ITEMS_PRICING.put("minecraft:melon_seeds", 6);
        ALLOWED_ITEMS_PRICING.put("minecraft:beetroot", 8);
        ALLOWED_ITEMS_PRICING.put("minecraft:beetroot_seeds", 31);

        // Custom mod items - adjust these to match your actual item registry names
        ALLOWED_ITEMS_PRICING.put("agotmod:horseradish", 7);
        ALLOWED_ITEMS_PRICING.put("agotmod:horseradish_seeds", 28);
        ALLOWED_ITEMS_PRICING.put("agotmod:onion", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:onion_seeds", 16);
        ALLOWED_ITEMS_PRICING.put("agotmod:red_onion", 5);
        ALLOWED_ITEMS_PRICING.put("agotmod:red_onion_seeds", 19);
        ALLOWED_ITEMS_PRICING.put("agotmod:wild_onion", 7);
        ALLOWED_ITEMS_PRICING.put("agotmod:wild_onion_seeds", 25);
        ALLOWED_ITEMS_PRICING.put("agotmod:leek", 6);
        ALLOWED_ITEMS_PRICING.put("agotmod:leek_seeds", 23);
        ALLOWED_ITEMS_PRICING.put("agotmod:neep", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:neep_seeds", 31);
        ALLOWED_ITEMS_PRICING.put("agotmod:turnip", 6);
        ALLOWED_ITEMS_PRICING.put("agotmod:turnip_seeds", 23);
        ALLOWED_ITEMS_PRICING.put("agotmod:parsley", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:parsley_seeds", 16);
        ALLOWED_ITEMS_PRICING.put("agotmod:bean", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:bean_seeds", 13);
        ALLOWED_ITEMS_PRICING.put("agotmod:green_bean", 4);
        ALLOWED_ITEMS_PRICING.put("agotmod:green_bean_seeds", 16);
        ALLOWED_ITEMS_PRICING.put("agotmod:chickpea", 6);
        ALLOWED_ITEMS_PRICING.put("agotmod:chickpea_seeds", 22);
        ALLOWED_ITEMS_PRICING.put("agotmod:cabbage", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:cabbage_seeds", 31);
        ALLOWED_ITEMS_PRICING.put("agotmod:spinach", 5);
        ALLOWED_ITEMS_PRICING.put("agotmod:spinach_seeds", 19);
        ALLOWED_ITEMS_PRICING.put("agotmod:cucumber", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:cucumber_seeds", 31);
        ALLOWED_ITEMS_PRICING.put("agotmod:dragon_pepper", 10);
        ALLOWED_ITEMS_PRICING.put("agotmod:dragon_pepper_seeds", 37);
        ALLOWED_ITEMS_PRICING.put("agotmod:pepper", 9);
        ALLOWED_ITEMS_PRICING.put("agotmod:pepper_seeds", 34);
        ALLOWED_ITEMS_PRICING.put("agotmod:peppercorn", 12);
        ALLOWED_ITEMS_PRICING.put("agotmod:peppercorn_seeds", 46);
        ALLOWED_ITEMS_PRICING.put("agotmod:barley", 2);
        ALLOWED_ITEMS_PRICING.put("agotmod:barley_seeds", 8);
        ALLOWED_ITEMS_PRICING.put("agotmod:oat", 3);
        ALLOWED_ITEMS_PRICING.put("agotmod:oat_seeds", 10);
        ALLOWED_ITEMS_PRICING.put("agotmod:opium_poppy_seeds", 46);
        ALLOWED_ITEMS_PRICING.put("agotmod:cotton", 10);
        ALLOWED_ITEMS_PRICING.put("agotmod:cotton_seeds", 37);
        ALLOWED_ITEMS_PRICING.put("agotmod:hemp", 12);
        ALLOWED_ITEMS_PRICING.put("agotmod:hemp_seeds", 46);

        // Berries
        ALLOWED_ITEMS_PRICING.put("agotmod:strawberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:strawberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:blackberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:blackberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:blueberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:blueberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:mulberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:mulberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:raspberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:raspberry_seeds", 68);
        ALLOWED_ITEMS_PRICING.put("agotmod:smokeberry", 17);
        ALLOWED_ITEMS_PRICING.put("agotmod:smokeberry_seeds", 68);

        // Other
        ALLOWED_ITEMS_PRICING.put("agotmod:garlic", 5);
    }

    private void drawTransactionMenu(GuiGraphics guiGraphics, int panelX, int panelY, int panelWidth, int panelHeight) {
        // Draw separator line below title
        guiGraphics.fill(panelX + panelWidth/8, panelY + 35,
                panelX + panelWidth - panelWidth/8, panelY + 36,
                SUBMENU_TEXT_COLOR);

        // Draw grocer balance below title with smaller text
        String balanceText = "Grocer Balance: " + formatCurrency(grocerBalance);

        // CHANGED: Scale down the balance text
        float balanceScale = 0.8f; // Scale down to 80%
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(balanceScale, balanceScale, 1.0f);

        int balanceWidth = (int)(font.width(balanceText) * balanceScale);
        int balanceX = (int)((panelX + (panelWidth - balanceWidth) / 2) / balanceScale);
        int balanceY = (int)((panelY + 45) / balanceScale);

        // Draw balance text in green color
        guiGraphics.drawString(font, balanceText, balanceX, balanceY, 0xFF4CAF50, false);

        guiGraphics.pose().popPose();

        // Draw another separator line below balance
        guiGraphics.fill(panelX + panelWidth/8, panelY + 60,
                panelX + panelWidth - panelWidth/8, panelY + 61,
                SUBMENU_TEXT_COLOR);

        // Calculate totals first
        calculateTotals();

        // Transaction menu content starts below the balance and separator
        int contentStartY = panelY + 70; // Moved down to accommodate balance display

        // Reserve space for bottom section (totals + button)
        int bottomSectionHeight = 80;
        int availableHeight = panelHeight - 70 - bottomSectionHeight; // Adjusted for new balance area

        // Scale down everything except the title and balance
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.5f, 0.5f, 1.0f);

        if (selectedEntryIndices.isEmpty()) {
            // Show instruction text when nothing is selected (scaled)
            String instructionText = "Select items";
            String instructionText2 = "from the left";
            String instructionText3 = "to trade";

            int text1Width = (int)(font.width(instructionText) * 0.5f);
            int text2Width = (int)(font.width(instructionText2) * 0.5f);
            int text3Width = (int)(font.width(instructionText3) * 0.5f);

            int text1X = (panelX + (panelWidth - text1Width) / 2) * 2;
            int text2X = (panelX + (panelWidth - text2Width) / 2) * 2;
            int text3X = (panelX + (panelWidth - text3Width) / 2) * 2;

            int textY = (contentStartY + availableHeight / 2 - font.lineHeight) * 2;

            guiGraphics.drawString(font, instructionText, text1X, textY, SUBMENU_TEXT_COLOR, false);
            textY += (font.lineHeight + 4) * 2;
            guiGraphics.drawString(font, instructionText2, text2X, textY, SUBMENU_TEXT_COLOR, false);
            textY += (font.lineHeight + 4) * 2;
            guiGraphics.drawString(font, instructionText3, text3X, textY, SUBMENU_TEXT_COLOR, false);
        } else {
            // Show selected items with transaction controls (scaled)
            drawTransactionItems(guiGraphics, panelX * 2, contentStartY * 2, panelWidth * 2, availableHeight * 2);

            // Draw scroll indicator if needed
            if (needsTransactionScroll(availableHeight * 2)) {
                drawTransactionScrollIndicator(guiGraphics, panelX * 2, contentStartY * 2, panelWidth * 2, availableHeight * 2);
            }
        }

        guiGraphics.pose().popPose();

        // Draw bottom section (not scaled)
        drawBottomSection(guiGraphics, panelX, panelY + panelHeight - bottomSectionHeight, panelWidth, bottomSectionHeight);
    }

    private void finishTransaction() {
        if (totalItems <= 0) return;

        // Create a map of items to subtract from inventory
        Map<String, Integer> itemsToSubtract = new HashMap<>();
        for (Map.Entry<String, Integer> entry : transactionAmounts.entrySet()) {
            int amount = entry.getValue();
            if (amount > 0) {
                itemsToSubtract.put(entry.getKey(), amount);
            }
        }

        // Send packet to server to update grocer inventory
        FinishTransactionPacket packet = new FinishTransactionPacket(grocerName, itemsToSubtract);
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(packet);


        // Close the screen
        this.onClose();
    }
    // NEW: Helper method to format currency display
    private String formatCurrency(long halfpennies) {
        if (halfpennies == 0) {
            return "0 coins";
        }

        // Simple format - you can make this more complex with gold/silver/copper later
        return halfpennies + " coins";
    }

    private void drawBottomSection(GuiGraphics guiGraphics, int panelX, int panelY, int panelWidth, int panelHeight) {
        // Draw separator line above bottom section
        guiGraphics.fill(panelX + panelWidth/8, panelY,
                panelX + panelWidth - panelWidth/8, panelY + 1,
                SUBMENU_TEXT_COLOR);

        // Get current player balance
        long currentPlayerBalance = getPlayerBalance();

        // Draw totals with smaller text
        String totalItemsText = "Total items: " + totalItems;
        String totalPriceText = "Total Price: " + totalPrice + " coins";
        String playerBalanceText = "Your Balance: " + formatCurrency(currentPlayerBalance);

        // CHANGED: Scale down all bottom text
        float bottomTextScale = 0.85f; // Scale down to 85%
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(bottomTextScale, bottomTextScale, 1.0f);

        int textY = (int)((panelY + 10) / bottomTextScale);
        int textX = (int)((panelX + 20) / bottomTextScale); // CHANGED: Moved from +10 to +20 (10 pixels right)

        // Draw totals text (scaled)
        guiGraphics.drawString(font, totalItemsText, textX, textY, SUBMENU_TEXT_COLOR, false);
        textY += (int)((font.lineHeight + 2) / bottomTextScale);
        guiGraphics.drawString(font, totalPriceText, textX, textY, SUBMENU_TEXT_COLOR, false);
        textY += (int)((font.lineHeight + 2) / bottomTextScale);

        // Draw player balance in different color (scaled)
        int balanceColor = currentPlayerBalance >= totalPrice ? 0xFF4CAF50 : 0xFFFF5722; // Green if enough, red if not
        guiGraphics.drawString(font, playerBalanceText, textX, textY, balanceColor, false);

        guiGraphics.pose().popPose();

        // Draw "Finish Transaction" button (keep button text normal size)
        int buttonWidth = 120;
        int buttonHeight = 20;
        int buttonX = panelX + (panelWidth - buttonWidth) / 2;
        int buttonY = panelY + panelHeight - buttonHeight - 20; // CHANGED: Moved up from -10 to -20 (10px up)

        // Check if transaction is possible
        boolean hasItems = totalItems > 0;
        boolean hasEnoughMoney = currentPlayerBalance >= totalPrice;
        boolean canComplete = hasItems && hasEnoughMoney;

        // Button background color based on state
        int buttonColor;
        if (!hasItems) {
            buttonColor = 0xFF666666; // Gray for no items
        } else if (!hasEnoughMoney) {
            buttonColor = 0xFF8B0000; // Dark red for insufficient funds
        } else {
            buttonColor = 0xFF888888; // Normal gray
        }

        guiGraphics.fill(buttonX, buttonY, buttonX + buttonWidth, buttonY + buttonHeight, buttonColor);

        // Button border
        guiGraphics.fill(buttonX, buttonY, buttonX + buttonWidth, buttonY + 1, 0xFFCCCCCC); // Top
        guiGraphics.fill(buttonX, buttonY, buttonX + 1, buttonY + buttonHeight, 0xFFCCCCCC); // Left
        guiGraphics.fill(buttonX + buttonWidth - 1, buttonY, buttonX + buttonWidth, buttonY + buttonHeight, 0xFF444444); // Right
        guiGraphics.fill(buttonX, buttonY + buttonHeight - 1, buttonX + buttonWidth, buttonY + buttonHeight, 0xFF444444); // Bottom

        // Button text based on state (keep button text normal size)
        String buttonText;
        int textColor;

        if (!hasItems) {
            buttonText = "No Items Selected";
            textColor = 0xFF666666;
        } else if (!hasEnoughMoney) {
            buttonText = "Insufficient Funds";
            textColor = 0xFFFF0000; // Bright red
        } else {
            buttonText = "Finish Transaction";
            textColor = 0xFFFFFFFF;
        }

        int textWidth = font.width(buttonText);
        int buttonTextX = buttonX + (buttonWidth - textWidth) / 2;
        int buttonTextY = buttonY + (buttonHeight - font.lineHeight) / 2;

        guiGraphics.drawString(font, buttonText, buttonTextX, buttonTextY, textColor, false);
    }


    private void drawTransactionItems(GuiGraphics guiGraphics, int panelX, int startY, int panelWidth, int availableHeight) {
        int itemHeight = 16 * 2;
        int margin = 6 * 2;
        int contentWidth = panelWidth - (margin * 8);
        int centeredX = panelX + (margin * 4);

        // Calculate which items to show based on transaction scroll
        int maxVisibleItems = Math.max(1, (availableHeight - 32) / itemHeight);
        List<Integer> sortedIndices = new ArrayList<>(selectedEntryIndices);
        sortedIndices.sort(Integer::compareTo);

        int startIndex = transactionScrollOffset;
        int endIndex = Math.min(startIndex + maxVisibleItems, sortedIndices.size());

        int itemY = startY;

        for (int i = startIndex; i < endIndex; i++) {
            Integer entryIndex = sortedIndices.get(i);
            if (entryIndex >= inventoryEntries.size()) continue;

            GrocerSystem.GrocerInventoryEntry entry = inventoryEntries.get(entryIndex);
            int transactionAmount = transactionAmounts.getOrDefault(entry.itemKey, 0);

            // Draw item icon 50% larger (1.5x scale)
            net.minecraft.resources.ResourceLocation itemLocation = net.minecraft.resources.ResourceLocation.tryParse(entry.itemKey);
            if (itemLocation != null) {
                net.minecraft.world.item.Item item = net.minecraft.core.registries.BuiltInRegistries.ITEM.getValue(itemLocation);
                if (item != null && item != net.minecraft.world.item.Items.AIR) {
                    net.minecraft.world.item.ItemStack itemStack = new net.minecraft.world.item.ItemStack(item);

                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(1.5f, 1.5f, 1.0f);
                    guiGraphics.renderItem(itemStack, (int)(centeredX / 1.5f), (int)((itemY + 2) / 1.5f));
                    guiGraphics.pose().popPose();
                }
            }

            // Draw transaction amount
            String amountText = String.valueOf(transactionAmount);
            int amountX = centeredX + 40;
            guiGraphics.drawString(font, amountText, amountX, itemY + 8, SUBMENU_TEXT_COLOR, false);

            // Draw buttons
            int buttonSize = 20;
            int totalButtonWidth = buttonSize * 6;
            int buttonsStartX = centeredX + contentWidth - totalButtonWidth + 20;
            int buttonY = itemY + 2;

            drawTransactionButton(guiGraphics, buttonsStartX, buttonY, buttonSize, "-64", entry.itemKey, false);
            drawTransactionButton(guiGraphics, buttonsStartX + buttonSize, buttonY, buttonSize, "-10", entry.itemKey, false);
            drawTransactionButton(guiGraphics, buttonsStartX + (buttonSize * 2), buttonY, buttonSize, "-", entry.itemKey, false);
            drawTransactionButton(guiGraphics, buttonsStartX + (buttonSize * 3), buttonY, buttonSize, "+", entry.itemKey, true);
            drawTransactionButton(guiGraphics, buttonsStartX + (buttonSize * 4), buttonY, buttonSize, "+10", entry.itemKey, true);
            drawTransactionButton(guiGraphics, buttonsStartX + (buttonSize * 5), buttonY, buttonSize, "+64", entry.itemKey, true);

            itemY += itemHeight;
        }
    }

    private void drawTransactionButton(GuiGraphics guiGraphics, int x, int y, int size, String text, String itemKey, boolean isPlus) {
        // Button background
        guiGraphics.fill(x, y, x + size, y + size, 0xFF888888);

        // Button border (scaled)
        guiGraphics.fill(x, y, x + size, y + 2, 0xFFCCCCCC); // Top
        guiGraphics.fill(x, y, x + 2, y + size, 0xFFCCCCCC); // Left
        guiGraphics.fill(x + size - 2, y, x + size, y + size, 0xFF444444); // Right
        guiGraphics.fill(x, y + size - 2, x + size, y + size, 0xFF444444); // Bottom

        // Button text (smaller scale for multi-character buttons)
        float textScale = text.length() > 1 ? 0.6f : 1.0f;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(textScale, textScale, 1.0f);

        int textWidth = (int)(font.width(text) * textScale);
        int textX = (int)((x + (size - textWidth) / 2) / textScale);
        int textY = (int)((y + (size - (font.lineHeight * textScale)) / 2) / textScale);
        guiGraphics.drawString(font, text, textX, textY, 0xFFFFFFFF, false);

        guiGraphics.pose().popPose();
    }

    // Data that will be updated from server
    private List<GrocerSystem.GrocerInventoryEntry> inventoryEntries;
    private final String grocerName;
    private boolean dataLoaded = false;
    private Set<Integer> selectedEntryIndices = new HashSet<>();
    private Map<String, Integer> transactionAmounts = new HashMap<>();

    // Tooltip state tracking
    private net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem.GrocerInventoryEntry currentTooltipEntry = null;
    private int tooltipX = 0;
    private int tooltipY = 0;
    private boolean tooltipPositioned = false;

    // Dynamic grid layout - calculated based on submenu width
    private int scrollOffset = 0;
    private static final int VISIBLE_ROWS = 4; // Number of visible rows
    private static final int ITEM_SLOT_SIZE = 32; // Smaller size since we only show icon + amount
    private static final int ITEM_SPACING = 4; // Closer spacing between items
    private static final int GRID_MARGIN = 16;
    private int transactionScrollOffset = 0; // Add this with your other scroll variables// Margin from submenu edges

    // Performance optimization - cached layout
    private ScreenLayout cachedLayout;
    private boolean layoutDirty = true;

    // Static instance for client-side access
    private static GrocerInventoryScreen currentInstance = null;

    public GrocerInventoryScreen(String grocerName) {
        super(Component.literal("Grocer Inventory - " + grocerName));
        this.grocerName = grocerName;
        this.inventoryEntries = new ArrayList<>(); // Start empty, will be populated by packet

        // Set as current instance for packet handling
        currentInstance = this;
    }

    // Method to check if an item is allowed to be stored by the grocer
    public static boolean isItemAllowed(String itemKey) {
        return ALLOWED_ITEMS_PRICING.containsKey(itemKey);
    }

    // Method to get item price
    public static int getItemPrice(String itemKey) {
        return ALLOWED_ITEMS_PRICING.getOrDefault(itemKey, 0);
    }

    // Method to get item sell price with special rules
    public static int getItemSellPrice(String itemKey) {
        int buyPrice = getItemPrice(itemKey);

        // Special rule: Seeds have same sell value as their non-seed counterparts
        // Exception: Pumpkin and melon seeds/items are half of normal value
        if (itemKey.contains("_seeds")) {
            String baseItem = itemKey.replace("_seeds", "");

            // For pumpkin and melon seeds, use half of the seed buy price
            if (baseItem.contains("pumpkin") || baseItem.contains("melon")) {
                return buyPrice / 2;
            }

            // For other seeds, use half of the non-seed item's price
            int baseItemPrice = getItemPrice(baseItem);
            if (baseItemPrice > 0) {
                return baseItemPrice / 2;
            }
        }

        // For pumpkin and melon (non-seeds), also half price
        if (itemKey.contains("pumpkin") || itemKey.contains("melon")) {
            return buyPrice / 2;
        }

        // Default: half of buy price rounded down
        return buyPrice / 2;
    }

    // Static method for packet handler to update inventory data
    // Static method for packet handler to update inventory data with balance
    public static void updateInventoryData(String grocerName, List<GrocerSystem.GrocerInventoryEntry> entries, long grocerBalance) {
        if (currentInstance != null && currentInstance.grocerName.equals(grocerName)) {
            // Filter entries to only include allowed items
            List<GrocerSystem.GrocerInventoryEntry> filteredEntries = new ArrayList<>();
            for (GrocerSystem.GrocerInventoryEntry entry : entries) {
                if (isItemAllowed(entry.itemKey)) {
                    filteredEntries.add(entry);
                } else {
                }
            }

            currentInstance.inventoryEntries = filteredEntries;
            currentInstance.grocerBalance = grocerBalance; // NEW: Store grocer balance
            currentInstance.dataLoaded = true;
            currentInstance.scrollOffset = 0;
            currentInstance.transactionScrollOffset = 0; // Reset transaction scroll too
            currentInstance.selectedEntryIndices.clear();
        } else {
        }
    }

    // Static method to get current instance (for packet handler)
    public static GrocerInventoryScreen getCurrentInstance() {
        return currentInstance;
    }

    @Override
    protected void init() {
        super.init();
        // Mark layout as dirty when screen is resized
        this.layoutDirty = true;
        this.cachedLayout = null;
    }

    @Override
    public void tick() {
        super.tick();
        // Removed render frequency limitation to prevent flickering
    }

    @Override
    public void onClose() {
        super.onClose();
        // Clear current instance when closing
        if (currentInstance == this) {
            currentInstance = null;
            transactionAmounts.clear();
        }
    }

    private ScreenLayout getOrCreateLayout() {
        if (cachedLayout == null || layoutDirty) {
            cachedLayout = new ScreenLayout(width, height);
            layoutDirty = false;
        }
        return cachedLayout;
    }

    // NEW: Get player's current balance
    private long getPlayerBalance() {
        if (minecraft != null && minecraft.player != null) {
            return minecraft.player.getPersistentData().getLong("agotmod.coin_balance");
        }
        return 0;
    }

    // Add this method to GrocerInventoryScreen class
    public static void updatePlayerBalance(long newBalance) {
        if (currentInstance != null) {
            currentInstance.playerBalance = newBalance;
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        ScreenLayout layout = getOrCreateLayout();
        int panelSpacing = 10;
        int leftPanelWidth = (layout.contentWidth * 2) / 3 - (panelSpacing / 2);
        int rightPanelWidth = layout.contentWidth / 3 - (panelSpacing / 2);

        int leftPanelX = layout.contentX;
        int rightPanelX = layout.contentX + leftPanelWidth + panelSpacing;

        // Check if mouse is in left panel (inventory)
        if (mouseX >= leftPanelX && mouseX < leftPanelX + leftPanelWidth &&
                mouseY >= layout.contentY && mouseY < layout.contentY + layout.contentHeight) {

            // Existing inventory scroll logic
            int paperWidth = layout.contentWidth - 40;
            int itemsPerRow = calculateItemsPerRow(paperWidth);
            int availableHeight = layout.contentHeight - 80;
            int maxRowsThatFit = availableHeight / (ITEM_SLOT_SIZE + ITEM_SPACING);
            int actualVisibleRows = Math.min(VISIBLE_ROWS, Math.max(1, maxRowsThatFit));
            int totalRows = (int) Math.ceil((double) inventoryEntries.size() / itemsPerRow);

            if (totalRows > actualVisibleRows) {
                if (scrollY > 0) {
                    scrollUp();
                    return true;
                } else if (scrollY < 0) {
                    scrollDown();
                    return true;
                }
            }
        }
        // Check if mouse is in right panel (transaction) - but only in the scrollable area
        else if (mouseX >= rightPanelX && mouseX < rightPanelX + rightPanelWidth &&
                mouseY >= layout.contentY && mouseY < layout.contentY + layout.contentHeight) {

            // Check if mouse is in the scrollable items area (not the bottom section or balance area)
            int bottomSectionHeight = 80;
            int bottomSectionY = layout.contentY + layout.contentHeight - bottomSectionHeight;
            int balanceAreaHeight = 35; // Height of balance display area
            int scrollableAreaStartY = layout.contentY + balanceAreaHeight;

            if (mouseY >= scrollableAreaStartY && mouseY < bottomSectionY) {
                int availableHeight = layout.contentHeight - 70 - bottomSectionHeight; // Adjusted for balance area
                if (needsTransactionScroll(availableHeight * 2)) {
                    if (scrollY > 0) {
                        transactionScrollUp();
                        return true;
                    } else if (scrollY < 0) {
                        transactionScrollDown();
                        return true;
                    }
                }
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

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && dataLoaded) { // Left click only
            // Check if click is within the left panel (inventory area)
            ScreenLayout layout = getOrCreateLayout();
            int panelSpacing = 10;
            int leftPanelWidth = (layout.contentWidth * 2) / 3 - (panelSpacing / 2);
            int leftPanelX = layout.contentX;
            int leftPanelY = layout.contentY;

            if (mouseX >= leftPanelX && mouseX < leftPanelX + leftPanelWidth &&
                    mouseY >= leftPanelY && mouseY < leftPanelY + layout.contentHeight) {

                int clickedEntryIndex = getClickedEntryIndex((int)mouseX, (int)mouseY, leftPanelX, leftPanelY, leftPanelWidth, layout.contentHeight);
                if (clickedEntryIndex >= 0) {
                    toggleEntrySelection(clickedEntryIndex);
                    playButtonSound();
                    return true;
                }
            }

            // Check if click is within the right panel (transaction area)
            int rightPanelX = layout.contentX + leftPanelWidth + panelSpacing;
            int rightPanelY = layout.contentY;
            int rightPanelWidth = layout.contentWidth / 3 - (panelSpacing / 2);

            if (mouseX >= rightPanelX && mouseX < rightPanelX + rightPanelWidth &&
                    mouseY >= rightPanelY && mouseY < rightPanelY + layout.contentHeight) {

                // Check if clicking in the bottom section (finish transaction button)
                int bottomSectionHeight = 80;
                int bottomSectionY = rightPanelY + layout.contentHeight - bottomSectionHeight;

                if (mouseY >= bottomSectionY) {
                    // Click is in bottom section, check if it's the button
                    int buttonWidth = 120;
                    int buttonHeight = 20;
                    int buttonX = rightPanelX + (rightPanelWidth - buttonWidth) / 2;
                    int buttonY = bottomSectionY + bottomSectionHeight - buttonHeight - 10;

                    if (mouseX >= buttonX && mouseX < buttonX + buttonWidth &&
                            mouseY >= buttonY && mouseY < buttonY + buttonHeight) {

                        // Check if transaction can be completed
                        boolean hasItems = totalItems > 0;
                        boolean hasEnoughMoney = getPlayerBalance() >= totalPrice;

                        if (hasItems && hasEnoughMoney) {
                            finishTransaction();
                            playButtonSound();
                            return true;
                        } else {
                            // Play a different sound for failed attempt or no sound
                            // Could add a "error" sound here if you have one
                            return true; // Still consume the click
                        }
                    }
                } else {
                    // Click is in items section - UPDATED: Account for balance area
                    int availableHeight = layout.contentHeight - 70 - bottomSectionHeight; // Adjusted for balance area
                    if (handleTransactionClick((int)mouseX, (int)mouseY, rightPanelX, rightPanelY + 70, rightPanelWidth, availableHeight)) { // rightPanelY + 70 to account for balance area
                        return true;
                    }
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private boolean needsTransactionScroll(int availableHeight) {
        if (selectedEntryIndices.isEmpty()) return false;

        int itemHeight = 16 * 2;
        int maxVisibleItems = Math.max(1, (availableHeight - 32) / itemHeight);
        return selectedEntryIndices.size() > maxVisibleItems;
    }

    private void transactionScrollUp() {
        if (transactionScrollOffset > 0) {
            transactionScrollOffset--;
        }
    }

    private void transactionScrollDown() {
        if (selectedEntryIndices.isEmpty()) return;

        ScreenLayout layout = getOrCreateLayout();
        int bottomSectionHeight = 80;
        int availableHeight = layout.contentHeight - 70 - bottomSectionHeight; // FIXED: Changed from 45 to 70
        int scaledAvailableHeight = availableHeight * 2;

        int itemHeight = 16 * 2;
        int maxVisibleItems = Math.max(1, (scaledAvailableHeight - 32) / itemHeight);
        int maxScroll = Math.max(0, selectedEntryIndices.size() - maxVisibleItems);

        if (transactionScrollOffset < maxScroll) {
            transactionScrollOffset++;
        }
    }

    private void drawTransactionScrollIndicator(GuiGraphics guiGraphics, int panelX, int startY, int panelWidth, int availableHeight) {
        int itemHeight = 16 * 2;
        int maxVisibleItems = Math.max(1, (availableHeight - 32) / itemHeight);
        int totalItems = selectedEntryIndices.size();

        if (totalItems <= maxVisibleItems) return;

        int scrollBarX = panelX + panelWidth - 20; // Exactly between -16 and -24
        int scrollBarY = startY + 20;
        int scrollBarHeight = availableHeight - 60;
        int scrollBarWidth = 16;

        // Draw scroll bar background
        guiGraphics.fill(scrollBarX, scrollBarY,
                scrollBarX + scrollBarWidth, scrollBarY + scrollBarHeight,
                0xFF555555);

        // Calculate scroll thumb
        int thumbHeight = Math.max(40, (maxVisibleItems * scrollBarHeight) / totalItems);
        int maxScroll = Math.max(1, totalItems - maxVisibleItems);
        int thumbY = scrollBarY + (transactionScrollOffset * (scrollBarHeight - thumbHeight)) / maxScroll;

        // Draw scroll thumb
        guiGraphics.fill(scrollBarX, thumbY,
                scrollBarX + scrollBarWidth, thumbY + thumbHeight,
                0xFFC6C6C6);

        // Draw scroll arrows
        String upArrow = "↑";
        String downArrow = "↓";
        int arrowX = scrollBarX + (scrollBarWidth - font.width(upArrow)) / 2;

        int upColor = transactionScrollOffset > 0 ? SUBMENU_TEXT_COLOR : 0xFF666666;
        guiGraphics.drawString(font, upArrow, arrowX, scrollBarY - 30, upColor, false);

        int downColor = transactionScrollOffset < maxScroll ? SUBMENU_TEXT_COLOR : 0xFF666666;
        guiGraphics.drawString(font, downArrow, arrowX, scrollBarY + scrollBarHeight + 10, downColor, false);
    }

    private boolean handleTransactionClick(int mouseX, int mouseY, int panelX, int startY, int panelWidth, int availableHeight) {
        // Get which transaction item was clicked (similar to getClickedEntryIndex for inventory)
        int clickedTransactionIndex = getClickedTransactionIndex(mouseX, mouseY, panelX, startY, panelWidth, availableHeight);

        if (clickedTransactionIndex >= 0) {
            // Get the actual entry
            List<Integer> sortedIndices = new ArrayList<>(selectedEntryIndices);
            sortedIndices.sort(Integer::compareTo);

            if (clickedTransactionIndex < sortedIndices.size()) {
                Integer entryIndex = sortedIndices.get(clickedTransactionIndex);
                if (entryIndex < inventoryEntries.size()) {
                    GrocerSystem.GrocerInventoryEntry entry = inventoryEntries.get(entryIndex);

                    // Now check which button within that item was clicked
                    return checkTransactionButtons(mouseX, mouseY, panelX, startY, panelWidth, availableHeight, clickedTransactionIndex, entry);
                }
            }
        }

        return false;
    }

    private int getClickedTransactionIndex(int mouseX, int mouseY, int panelX, int startY, int panelWidth, int availableHeight) {
        int scaledMouseY = mouseY * 2;
        int scaledStartY = startY * 2;
        int scaledAvailableHeight = availableHeight * 2;

        int itemHeight = 16 * 2;
        int maxVisibleItems = Math.max(1, (scaledAvailableHeight - 32) / itemHeight);

        // Calculate which visible item was clicked
        int relativeIndex = (scaledMouseY - scaledStartY) / itemHeight;

        if (relativeIndex >= 0 && relativeIndex < maxVisibleItems) {
            int actualIndex = transactionScrollOffset + relativeIndex;
            // Check against the sorted list size, not selectedEntryIndices.size()
            List<Integer> sortedIndices = new ArrayList<>(selectedEntryIndices);
            sortedIndices.sort(Integer::compareTo);

            if (actualIndex < sortedIndices.size()) {
                return actualIndex;
            }
        }

        return -1;
    }

    private boolean checkTransactionButtons(int mouseX, int mouseY, int panelX, int startY, int panelWidth, int availableHeight, int transactionIndex, GrocerSystem.GrocerInventoryEntry entry) {
        // Convert to scaled coordinates
        int scaledMouseX = mouseX * 2;
        int scaledMouseY = mouseY * 2;
        int scaledPanelX = panelX * 2;
        int scaledStartY = startY * 2;
        int scaledPanelWidth = panelWidth * 2;

        int itemHeight = 16 * 2;
        int margin = 6 * 2;

        // Calculate this specific item's Y position based on its visible position
        int visibleIndex = transactionIndex - transactionScrollOffset;
        int itemY = scaledStartY + (visibleIndex * itemHeight);

        int contentWidth = scaledPanelWidth - (margin * 8);
        int centeredX = scaledPanelX + (margin * 4);

        int buttonSize = 20;
        int totalButtonWidth = buttonSize * 6;
        int buttonsStartX = centeredX + contentWidth - totalButtonWidth + 20;
        int buttonY = itemY + 2;

        // Check each button
        for (int j = 0; j < 6; j++) {
            int buttonX = buttonsStartX + (j * buttonSize);

            if (scaledMouseX >= buttonX && scaledMouseX < buttonX + buttonSize &&
                    scaledMouseY >= buttonY && scaledMouseY < buttonY + buttonSize) {

                switch (j) {
                    case 0: adjustTransactionAmount(entry.itemKey, entry.amount, false, 64); break;
                    case 1: adjustTransactionAmount(entry.itemKey, entry.amount, false, 10); break;
                    case 2: adjustTransactionAmount(entry.itemKey, entry.amount, false, 1); break;
                    case 3: adjustTransactionAmount(entry.itemKey, entry.amount, true, 1); break;
                    case 4: adjustTransactionAmount(entry.itemKey, entry.amount, true, 10); break;
                    case 5: adjustTransactionAmount(entry.itemKey, entry.amount, true, 64); break;
                }

                playButtonSound();
                return true;
            }
        }

        return false;
    }

    private void adjustTransactionAmount(String itemKey, int maxAmount, boolean increase, int changeAmount) {
        int currentAmount = transactionAmounts.getOrDefault(itemKey, 0);
        int newAmount;

        if (increase) {
            newAmount = Math.min(currentAmount + changeAmount, maxAmount);
        } else {
            newAmount = Math.max(currentAmount - changeAmount, 0);
        }

        transactionAmounts.put(itemKey, newAmount);
    }

    private int getClickedEntryIndex(int mouseX, int mouseY, int panelX, int panelY, int panelWidth, int panelHeight) {
        int startY = panelY + 60;
        int availableWidth = panelWidth - (GRID_MARGIN * 2);
        int itemsPerRow = calculateItemsPerRow(availableWidth);
        int availableHeight = panelHeight - 80;
        int maxRowsThatFit = availableHeight / (ITEM_SLOT_SIZE + ITEM_SPACING);
        int actualVisibleRows = Math.min(VISIBLE_ROWS, Math.max(1, maxRowsThatFit));
        int totalGridWidth = (itemsPerRow * ITEM_SLOT_SIZE) + ((itemsPerRow - 1) * ITEM_SPACING);
        int gridStartX = panelX + (panelWidth - totalGridWidth) / 2;
        int startIndex = scrollOffset * itemsPerRow;
        int endIndex = Math.min(startIndex + (actualVisibleRows * itemsPerRow), inventoryEntries.size());

        for (int i = startIndex; i < endIndex; i++) {
            int relativeIndex = i - startIndex;
            int gridX = relativeIndex % itemsPerRow;
            int gridY = relativeIndex / itemsPerRow;
            if (gridY >= actualVisibleRows) break;
            int slotX = gridStartX + (gridX * (ITEM_SLOT_SIZE + ITEM_SPACING));
            int slotY = startY + (gridY * (ITEM_SLOT_SIZE + ITEM_SPACING));
            if (mouseX >= slotX && mouseX < slotX + ITEM_SLOT_SIZE &&
                    mouseY >= slotY && mouseY < slotY + ITEM_SLOT_SIZE) {
                return i;
            }
        }
        return -1;
    }

    private void toggleEntrySelection(int entryIndex) {
        if (selectedEntryIndices.contains(entryIndex)) {
            selectedEntryIndices.remove(entryIndex);
            if (entryIndex < inventoryEntries.size()) {
                String itemKey = inventoryEntries.get(entryIndex).itemKey;
                transactionAmounts.remove(itemKey);
            }
        } else {
            selectedEntryIndices.add(entryIndex);
            if (entryIndex < inventoryEntries.size()) {
                String itemKey = inventoryEntries.get(entryIndex).itemKey;
                transactionAmounts.put(itemKey, 0);
            }
        }
        transactionScrollOffset = 0;
    }

    private void playButtonSound() {
        if (minecraft != null && minecraft.player != null) {
            minecraft.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                            ModSounds.PAGE, 1.0F
                    )
            );
        }
    }

    private void scrollUp() {
        if (scrollOffset > 0) {
            scrollOffset--;
            // Removed scroll sound to avoid audio spam
        }
    }

    private void scrollDown() {
        // Calculate items per row based on current layout
        ScreenLayout layout = getOrCreateLayout();
        int paperWidth = layout.contentWidth - 40; // Account for paper margins
        int itemsPerRow = calculateItemsPerRow(paperWidth);

        // Calculate actual visible rows based on available space
        int availableHeight = layout.contentHeight - 80; // Reduced reserved space
        int maxRowsThatFit = availableHeight / (ITEM_SLOT_SIZE + ITEM_SPACING);
        int actualVisibleRows = Math.min(VISIBLE_ROWS, Math.max(1, maxRowsThatFit));

        int totalRows = (int) Math.ceil((double) inventoryEntries.size() / itemsPerRow);
        int maxScroll = Math.max(0, totalRows - actualVisibleRows);

        if (scrollOffset < maxScroll) {
            scrollOffset++;
            // Removed scroll sound to avoid audio spam
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Render background matching your custom GUI
        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7);

        // Use cached layout - now for the main container
        ScreenLayout layout = getOrCreateLayout();

        // Calculate panel dimensions for horizontal split
        int panelSpacing = 10; // Space between panels
        int leftPanelWidth = (layout.contentWidth * 2) / 3 - (panelSpacing / 2); // 2/3 width
        int rightPanelWidth = layout.contentWidth / 3 - (panelSpacing / 2); // 1/3 width

        // Left panel (Item Menu) - 2/3 of screen
        int leftPanelX = layout.contentX;
        int leftPanelY = layout.contentY;
        renderPaperPanel(guiGraphics, leftPanelX, leftPanelY, leftPanelWidth, layout.contentHeight);

        // Right panel (Transaction Menu) - 1/3 of screen
        int rightPanelX = layout.contentX + leftPanelWidth + panelSpacing;
        int rightPanelY = layout.contentY;
        renderPaperPanel(guiGraphics, rightPanelX, rightPanelY, rightPanelWidth, layout.contentHeight);

        // Draw titles for both panels
        drawSectionTitle(guiGraphics, grocerName,
                leftPanelX, leftPanelY, leftPanelWidth);
        drawSectionTitle(guiGraphics, "Transaction",
                rightPanelX, rightPanelY, rightPanelWidth);

        // Draw content in left panel (Item Menu)
        if (!dataLoaded) {
            drawLoadingMessage(guiGraphics, leftPanelX, leftPanelY, leftPanelWidth, layout.contentHeight);
        } else {
            drawInventoryEntries(guiGraphics, leftPanelX, leftPanelY, leftPanelWidth, layout.contentHeight, mouseX, mouseY);

            // Draw scroll indicator if needed
            int availableWidth = leftPanelWidth - (GRID_MARGIN * 2);
            int itemsPerRow = calculateItemsPerRow(availableWidth);
            int availableHeight = layout.contentHeight - 80;
            int maxRowsThatFit = availableHeight / (ITEM_SLOT_SIZE + ITEM_SPACING);
            int actualVisibleRows = Math.min(VISIBLE_ROWS, Math.max(1, maxRowsThatFit));
            int totalRows = (int) Math.ceil((double) inventoryEntries.size() / itemsPerRow);
            if (totalRows > actualVisibleRows) {
                drawScrollIndicator(guiGraphics, leftPanelX, leftPanelY, leftPanelWidth, layout.contentHeight);
            }
        }

        // Draw content in right panel (Transaction Menu)
        drawTransactionMenu(guiGraphics, rightPanelX, rightPanelY, rightPanelWidth, layout.contentHeight);

        // Draw help text at bottom of left panel only
        drawHelpText(guiGraphics, leftPanelX, leftPanelY, leftPanelWidth, layout.contentHeight);

        // Draw tooltip LAST so it appears on top of everything else
        if (currentTooltipEntry != null && tooltipPositioned) {
            drawItemTooltip(guiGraphics, currentTooltipEntry);
        }

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    private void drawLoadingMessage(GuiGraphics guiGraphics, int panelX, int panelY, int panelWidth, int panelHeight) {
        String loadingMessage = "Loading inventory data...";
        int messageWidth = font.width(loadingMessage);
        int messageX = panelX + (panelWidth - messageWidth) / 2;
        int messageY = panelY + panelHeight / 2;

        guiGraphics.drawString(font, loadingMessage, messageX, messageY, SUBMENU_TEXT_COLOR, false);
    }

    private void drawInventoryEntries(GuiGraphics guiGraphics, int panelX, int panelY, int panelWidth, int panelHeight, int mouseX, int mouseY) {
        if (inventoryEntries.isEmpty()) {
            // Draw "No items collected" message
            String emptyMessage = "No items have been collected yet.";
            int messageWidth = font.width(emptyMessage);
            int messageX = panelX + (panelWidth - messageWidth) / 2;
            int messageY = panelY + panelHeight / 2;

            guiGraphics.drawString(font, emptyMessage, messageX, messageY, SUBMENU_TEXT_COLOR, false);
            return;
        }

        int startY = panelY + 60; // Leave space for title and separator

        // Draw separator line
        guiGraphics.fill(panelX + panelWidth/8, startY - 25,
                panelX + panelWidth - panelWidth/8, startY - 24,
                SUBMENU_TEXT_COLOR);

        // Calculate dynamic grid layout based on available width
        int availableWidth = panelWidth - (GRID_MARGIN * 2); // Remove margins from both sides
        int itemsPerRow = calculateItemsPerRow(availableWidth);

        // Calculate available height for items (reserve space for separator and help text)
        int reservedTopSpace = 50; // Space for title and separator
        int reservedBottomSpace = 30; // Space for help text
        int availableHeight = panelHeight - reservedTopSpace - reservedBottomSpace;

        // Calculate maximum rows that can fit within bounds
        int maxRowsThatFit = availableHeight / (ITEM_SLOT_SIZE + ITEM_SPACING);
        int actualVisibleRows = Math.min(VISIBLE_ROWS, maxRowsThatFit);

        // Ensure we have at least 1 row visible
        if (actualVisibleRows < 1) {
            actualVisibleRows = 1;
        }

        // Calculate grid positioning
        int totalGridWidth = (itemsPerRow * ITEM_SLOT_SIZE) + ((itemsPerRow - 1) * ITEM_SPACING);
        int gridStartX = panelX + (panelWidth - totalGridWidth) / 2; // Center the grid

        // Calculate which items to show based on scroll
        int startIndex = scrollOffset * itemsPerRow;
        int endIndex = Math.min(startIndex + (actualVisibleRows * itemsPerRow), inventoryEntries.size());

        // Variable to track hovered item for tooltip
        net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem.GrocerInventoryEntry hoveredEntry = null;

        // Draw items in dynamic grid format (only within bounds)
        for (int i = startIndex; i < endIndex; i++) {
            net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem.GrocerInventoryEntry entry = inventoryEntries.get(i);

            // Calculate grid position
            int relativeIndex = i - startIndex;
            int gridX = relativeIndex % itemsPerRow;
            int gridY = relativeIndex / itemsPerRow;

            // Only draw if the row fits within our bounds
            if (gridY >= actualVisibleRows) {
                break; // Stop drawing if we've exceeded visible rows
            }

            // Calculate actual screen positions
            int slotX = gridStartX + (gridX * (ITEM_SLOT_SIZE + ITEM_SPACING));
            int slotY = startY + (gridY * (ITEM_SLOT_SIZE + ITEM_SPACING));

            // Additional bounds check - ensure item doesn't go below panel bounds
            if (slotY + ITEM_SLOT_SIZE > panelY + panelHeight - 30) { // Reduced bottom margin
                break; // Stop drawing if item would exceed panel bounds
            }

            // Draw item slot background only around the icon (18x18 to give some padding around 16x16 icon)
            int iconSlotSize = 18;
            int iconSlotX = slotX + (ITEM_SLOT_SIZE - iconSlotSize) / 2;
            int iconSlotY = slotY + 2;
            boolean isSelected = selectedEntryIndices.contains(i);
            drawItemSlotBackground(guiGraphics, iconSlotX, iconSlotY, iconSlotSize, iconSlotSize, isSelected);
            // Get the actual item for rendering
            net.minecraft.resources.ResourceLocation itemLocation = net.minecraft.resources.ResourceLocation.tryParse(entry.itemKey);
            if (itemLocation != null) {
                net.minecraft.world.item.Item item = net.minecraft.core.registries.BuiltInRegistries.ITEM.getValue(itemLocation);
                if (item != null && item != net.minecraft.world.item.Items.AIR) {
                    net.minecraft.world.item.ItemStack itemStack = new net.minecraft.world.item.ItemStack(item);

                    // Render the item icon (16x16, centered in the small icon slot)
                    int iconX = slotX + (ITEM_SLOT_SIZE - 16) / 2;
                    int iconY = slotY + 3;
                    guiGraphics.renderItem(itemStack, iconX, iconY);

                    // Check if mouse is hovering over the icon for tooltip
                    if (mouseX >= iconX && mouseX < iconX + 16 && mouseY >= iconY && mouseY < iconY + 16) {
                        hoveredEntry = entry;

                        // If this is a new tooltip or first time positioning
                        if (currentTooltipEntry != entry || !tooltipPositioned) {
                            currentTooltipEntry = entry;
                            calculateTooltipPosition(entry, mouseX, mouseY);
                            tooltipPositioned = true;
                        }
                    }

                    // Render amount (centered, below icon)
                    String amountText = formatNumber(entry.amount);
                    float amountScale = 0.7f;
                    guiGraphics.pose().pushPose();
                    guiGraphics.pose().scale(amountScale, amountScale, 1.0f);

                    int amountWidth = (int)(font.width(amountText) * amountScale);
                    int amountX = (int)((slotX + (ITEM_SLOT_SIZE - amountWidth) / 2) / amountScale);
                    int amountY = (int)((slotY + 22) / amountScale); // Position just below icon

                    guiGraphics.drawString(font, amountText, amountX, amountY, 0xFF4CAF50, false); // Green color for amounts
                    guiGraphics.pose().popPose();
                }
            }
        }

        // Reset tooltip state if no longer hovering
        if (hoveredEntry == null) {
            currentTooltipEntry = null;
            tooltipPositioned = false;
        }

        // Note: Tooltip will be drawn later in the main render method to ensure it's on top
    }

    // Calculate and save tooltip position (called once per tooltip)
    private void calculateTooltipPosition(net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem.GrocerInventoryEntry entry, int mouseX, int mouseY) {
        String itemName = entry.displayName;
        String amountText = "Amount: " + entry.amount; // Use full number, not formatted

        // Get item prices
        int buyPrice = getItemPrice(entry.itemKey);
        int sellPrice = getItemSellPrice(entry.itemKey);
        long totalValue = (long) buyPrice * entry.amount;

        String buyText = "Buy Price: " + buyPrice + " coins";
        String sellText = "Sell Price: " + sellPrice + " coins";
        String valueText = "Total Value: " + formatNumber((int) Math.min(totalValue, Integer.MAX_VALUE)) + " coins";

        // Calculate tooltip dimensions (now 5 lines)
        int nameWidth = font.width(itemName);
        int amountWidth = font.width(amountText);
        int buyWidth = font.width(buyText);
        int sellWidth = font.width(sellText);
        int valueWidth = font.width(valueText);
        int tooltipWidth = Math.max(Math.max(Math.max(nameWidth, amountWidth), Math.max(buyWidth, sellWidth)), valueWidth) + 16; // 8 padding on each side
        int tooltipHeight = font.lineHeight * 5 + 18; // 5 lines + padding

        // Position tooltip so its top-left corner is at the bottom-right of the mouse
        tooltipX = mouseX;
        tooltipY = mouseY;

        // Adjust if tooltip would go off screen horizontally
        if (tooltipX + tooltipWidth > width - 5) {
            tooltipX = width - tooltipWidth - 5;
        }

        // Adjust if tooltip would go off screen vertically
        if (tooltipY + tooltipHeight > height - 5) {
            tooltipY = height - tooltipHeight - 5;
        }

        // Ensure tooltip doesn't go off-screen on the left or top
        if (tooltipX < 5) tooltipX = 5;
        if (tooltipY < 5) tooltipY = 5;
    }

    // Draw tooltip using saved position (doesn't take mouse coordinates)
    private void drawItemTooltip(GuiGraphics guiGraphics, net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem.GrocerInventoryEntry entry) {
        String itemName = entry.displayName;
        String amountText = "Amount: " + entry.amount; // Use full number, not formatted

        // Get item prices
        int buyPrice = getItemPrice(entry.itemKey);
        int sellPrice = getItemSellPrice(entry.itemKey);
        long totalValue = (long) buyPrice * entry.amount;

        String buyText = "Buy Price: " + buyPrice + " coins";
        String sellText = "Sell Price: " + sellPrice + " coins";
        String valueText = "Total Value: " + formatNumber((int) Math.min(totalValue, Integer.MAX_VALUE)) + " coins";

        // Calculate tooltip dimensions (now 5 lines)
        int nameWidth = font.width(itemName);
        int amountWidth = font.width(amountText);
        int buyWidth = font.width(buyText);
        int sellWidth = font.width(sellText);
        int valueWidth = font.width(valueText);
        int tooltipWidth = Math.max(Math.max(Math.max(nameWidth, amountWidth), Math.max(buyWidth, sellWidth)), valueWidth) + 16; // 8 padding on each side
        int tooltipHeight = font.lineHeight * 5 + 18; // 5 lines + padding

        // Push pose to render tooltip at higher Z-level
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 300); // Move tooltip forward in Z-space

        // Draw tooltip background
        guiGraphics.fill(tooltipX, tooltipY, tooltipX + tooltipWidth, tooltipY + tooltipHeight, 0xE0000000);

        // Draw tooltip border
        guiGraphics.fill(tooltipX, tooltipY, tooltipX + tooltipWidth, tooltipY + 1, 0xFF555555); // Top
        guiGraphics.fill(tooltipX, tooltipY, tooltipX + 1, tooltipY + tooltipHeight, 0xFF555555); // Left
        guiGraphics.fill(tooltipX + tooltipWidth - 1, tooltipY, tooltipX + tooltipWidth, tooltipY + tooltipHeight, 0xFF555555); // Right
        guiGraphics.fill(tooltipX, tooltipY + tooltipHeight - 1, tooltipX + tooltipWidth, tooltipY + tooltipHeight, 0xFF555555); // Bottom

        // Draw text (5 lines now)
        int textY = tooltipY + 8;
        guiGraphics.drawString(font, itemName, tooltipX + 8, textY, 0xFFFFFF, false);
        textY += font.lineHeight;
        guiGraphics.drawString(font, amountText, tooltipX + 8, textY, 0xFF4CAF50, false);
        textY += font.lineHeight;
        guiGraphics.drawString(font, buyText, tooltipX + 8, textY, 0xFFFFD700, false); // Gold color for buy price
        textY += font.lineHeight;
        guiGraphics.drawString(font, sellText, tooltipX + 8, textY, 0xFF00CED1, false); // Light blue/cyan color for sell price
        textY += font.lineHeight;
        guiGraphics.drawString(font, valueText, tooltipX + 8, textY, 0xFFFFD700, false); // Gold color for total value

        // Pop pose to restore normal Z-level
        guiGraphics.pose().popPose();
    }

    // Calculate how many items can fit per row based on available width
    private int calculateItemsPerRow(int availableWidth) {
        // Calculate maximum items that can fit: (width - margins) / (item_size + spacing)
        // We need to account for the fact that the last item doesn't need spacing after it
        int maxItems = (availableWidth + ITEM_SPACING) / (ITEM_SLOT_SIZE + ITEM_SPACING);

        // Ensure at least 1 item per row, and reasonable maximum
        return Math.max(1, Math.min(maxItems, 10)); // Max 10 items per row for sanity
    }

    // Smart text wrapping method
    private java.util.List<String> wrapText(String text, int maxWidth, float scale) {
        java.util.List<String> lines = new java.util.ArrayList<>();

        // Split by spaces first to handle word boundaries
        String[] words = text.split(" ");

        if (words.length == 1) {
            // Single word - handle hyphenation if too long
            String word = words[0];
            if (font.width(word) * scale <= maxWidth) {
                lines.add(word);
            } else {
                // Break long word with hyphen
                String currentLine = "";
                for (int i = 0; i < word.length(); i++) {
                    String testLine = currentLine + word.charAt(i);
                    String testLineWithHyphen = testLine + "-";

                    if (font.width(testLineWithHyphen) * scale > maxWidth && !currentLine.isEmpty()) {
                        lines.add(currentLine + "-");
                        currentLine = "" + word.charAt(i);
                    } else {
                        currentLine = testLine;
                    }
                }
                if (!currentLine.isEmpty()) {
                    lines.add(currentLine);
                }
            }
        } else {
            // Multiple words - wrap by words
            String currentLine = "";

            for (String word : words) {
                String testLine = currentLine.isEmpty() ? word : currentLine + " " + word;

                if (font.width(testLine) * scale <= maxWidth) {
                    currentLine = testLine;
                } else {
                    // Current line is full, start new line
                    if (!currentLine.isEmpty()) {
                        lines.add(currentLine);
                    }

                    // Check if single word is too long
                    if (font.width(word) * scale > maxWidth) {
                        // Break the word with hyphen
                        String partialWord = "";
                        for (int i = 0; i < word.length(); i++) {
                            String testChar = partialWord + word.charAt(i);
                            String testWithHyphen = testChar + "-";

                            if (font.width(testWithHyphen) * scale > maxWidth && !partialWord.isEmpty()) {
                                lines.add(partialWord + "-");
                                partialWord = "" + word.charAt(i);
                            } else {
                                partialWord = testChar;
                            }
                        }
                        currentLine = partialWord;
                    } else {
                        currentLine = word;
                    }
                }
            }

            if (!currentLine.isEmpty()) {
                lines.add(currentLine);
            }
        }

        // Limit to maximum 3 lines to prevent overflow
        if (lines.size() > 3) {
            lines = lines.subList(0, 2);
            String lastLine = lines.get(1);
            if (lastLine.length() > 3) {
                lines.set(1, lastLine.substring(0, lastLine.length() - 3) + "...");
            } else {
                lines.set(1, "...");
            }
        }

        return lines;
    }

    private void drawItemSlotBackground(GuiGraphics guiGraphics, int x, int y, int width, int height, boolean isSelected) {        // Draw slot background (slightly darker)
        if (isSelected) {
            guiGraphics.fill(x, y, x + width, y + height, 0x80FFFFFF); // Semi-transparent white background
            guiGraphics.fill(x, y, x + width, y + 1, 0xFFFFFFFF); // Bright white border - top
            guiGraphics.fill(x, y, x + 1, y + height, 0xFFFFFFFF); // Bright white border - left
            guiGraphics.fill(x + width - 1, y, x + width, y + height, 0xFFFFFFFF); // Bright white border - right
            guiGraphics.fill(x, y + height - 1, x + width, y + height, 0xFFFFFFFF); // Bright white border - bottom
        } else {
            guiGraphics.fill(x, y, x + width, y + height, 0x40000000);
            guiGraphics.fill(x, y, x + width, y + 1, 0x80FFFFFF);
            guiGraphics.fill(x, y, x + 1, y + height, 0x80FFFFFF);
            guiGraphics.fill(x + width - 1, y, x + width, y + height, 0x60000000);
            guiGraphics.fill(x, y + height - 1, x + width, y + height, 0x60000000);
        }
    }

    private void drawScrollIndicator(GuiGraphics guiGraphics, int panelX, int panelY, int panelWidth, int panelHeight) {
        // Calculate items per row based on current layout
        int availableWidth = panelWidth - (GRID_MARGIN * 2);
        int itemsPerRow = calculateItemsPerRow(availableWidth);
        int totalRows = (int) Math.ceil((double) inventoryEntries.size() / itemsPerRow);

        if (totalRows <= VISIBLE_ROWS) {
            return; // No need for scroll indicator
        }

        int scrollBarX = panelX + panelWidth - 10; // Exactly between -8 and -12
        int scrollBarY = panelY + 80;
        int scrollBarHeight = panelHeight - 140;
        int scrollBarWidth = 8;

        // Draw scroll bar background
        guiGraphics.fill(scrollBarX, scrollBarY,
                scrollBarX + scrollBarWidth, scrollBarY + scrollBarHeight,
                0xFF555555);

        // Calculate scroll thumb for dynamic grid layout
        int thumbHeight = Math.max(20, (VISIBLE_ROWS * scrollBarHeight) / totalRows);
        int maxScroll = Math.max(1, totalRows - VISIBLE_ROWS);
        int thumbY = scrollBarY + (scrollOffset * (scrollBarHeight - thumbHeight)) / maxScroll;

        // Draw scroll thumb
        guiGraphics.fill(scrollBarX, thumbY,
                scrollBarX + scrollBarWidth, thumbY + thumbHeight,
                0xFFC6C6C6);

        // Draw scroll arrows as text
        String upArrow = "↑";
        String downArrow = "↓";

        int arrowX = scrollBarX + (scrollBarWidth - font.width(upArrow)) / 2;

        // Up arrow
        int upColor = scrollOffset > 0 ? SUBMENU_TEXT_COLOR : 0xFF666666;
        guiGraphics.drawString(font, upArrow, arrowX, scrollBarY - 15, upColor, false);

        // Down arrow
        int downColor = scrollOffset < maxScroll ? SUBMENU_TEXT_COLOR : 0xFF666666;
        guiGraphics.drawString(font, downArrow, arrowX, scrollBarY + scrollBarHeight + 5, downColor, false);
    }

    private void drawHelpText(GuiGraphics guiGraphics, int panelX, int panelY, int panelWidth, int panelHeight) {
        String helpText = "Use mouse wheel or arrow keys to scroll • Press ESC to close";

        // CHANGED: Scale down the help text further
        float scale = 0.7f; // CHANGED: Reduced from 0.8f to 0.7f
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        int helpWidth = (int)(font.width(helpText) * scale);
        int helpX = (int)((panelX + (panelWidth - helpWidth) / 2) / scale);
        int helpY = (int)((panelY + panelHeight - 25) / scale);

        guiGraphics.drawString(font, helpText, helpX, helpY, 0xFF666666, false);

        guiGraphics.pose().popPose();
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
        float titleScale = 0.9f; // CHANGED: Reduced from 1.2f to 0.9f
        int titleWidth = (int) (font.width(title) * titleScale);
        int titleX = x + (width - titleWidth) / 2;
        int titleY = y + 18;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(titleScale, titleScale, 1.0f);
        guiGraphics.drawString(font, title,
                (int) (titleX / titleScale),
                (int) (titleY / titleScale),
                SUBMENU_TEXT_COLOR, false);
        guiGraphics.pose().popPose();
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

    private void calculateTotals() {
        int totalItems = 0;
        int totalPrice = 0;

        for (Map.Entry<String, Integer> entry : transactionAmounts.entrySet()) {
            int amount = entry.getValue();
            if (amount > 0) {
                totalItems += amount;
                int itemPrice = getItemPrice(entry.getKey());
                totalPrice += itemPrice * amount;
            }
        }

        this.totalItems = totalItems;
        this.totalPrice = totalPrice;
    }

    // Override to disable background blur
    @Override
    public boolean isPauseScreen() {
        return false; // This prevents the game from pausing and adding blur
    }

    // Override to disable background rendering
    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // Don't call super.renderBackground() to avoid blur effect
        // Just render a simple transparent overlay instead
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