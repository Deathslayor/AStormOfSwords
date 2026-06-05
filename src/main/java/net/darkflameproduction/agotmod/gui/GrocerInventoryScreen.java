package net.darkflameproduction.agotmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.network.FinishTransactionPacket;
import net.darkflameproduction.agotmod.network.OpenGrocerInventoryPacket;
import net.darkflameproduction.agotmod.util.ItemPricing;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerSystem;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

@OnlyIn(Dist.CLIENT)
public class GrocerInventoryScreen extends Screen {

    // ── Trader GUI texture ────────────────────────────────────────────────────

    private static final ResourceLocation TRADER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/gui_asset_trader_book.png");
    private static final ResourceLocation SEAL_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/gui_asset_trader_seal.png");
    private static final ResourceLocation CHANGE_AMOUNT_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/gui_asset_trader_change_amount_icon.png");

    /** Actual pixel dimensions of gui_asset_trader_book.png. */
    private static final int TEXTURE_WIDTH  = 1082;
    private static final int TEXTURE_HEIGHT = 212;

    /** Left panel — inventory/products area (in texture virtual pixels). */
    private static final int LEFT_X = 366;
    private static final int LEFT_Y = 40;
    private static final int LEFT_W = 210;
    private static final int LEFT_H = 117;

    /** Right panel — transaction area (in texture virtual pixels). */
    private static final int RIGHT_X = 611;
    private static final int RIGHT_Y = 62;
    private static final int RIGHT_W = 102;
    private static final int RIGHT_H = 125;

    // ── Colors ────────────────────────────────────────────────────────────────

    private static final int TEXT_COLOR      = 0xFF000000;
    private static final int TEXT_COLOR_GREEN = 0xFF4CAF50;
    private static final int TEXT_COLOR_RED   = 0xFFFF5722;
    private static final int TEXT_COLOR_GOLD  = 0xFFFFD700;

    // ── Item grid constants ───────────────────────────────────────────────────

    private static final int BUY_BTN_X  = 442;
    private static final int BUY_BTN_Y  = 158;
    private static final int BUY_BTN_W  = 24;
    private static final int BUY_BTN_H  = 24;

    private static final int SELL_BTN_X = 475;
    private static final int SELL_BTN_Y = 158;
    private static final int SELL_BTN_W = 24;
    private static final int SELL_BTN_H = 24;

    private static final int SEAL_BTN_X = 611;
    private static final int SEAL_BTN_Y = 158;
    private static final int SEAL_BTN_W = 25;
    private static final int SEAL_BTN_H = 26;

    private static final int BOTTOM_X = 637;
    private static final int BOTTOM_Y = 158;
    private static final int BOTTOM_W = 76;
    private static final int BOTTOM_H = 27;

    private static final int INFO_X = 631;
    private static final int INFO_Y = 47;
    private static final int INFO_W = 78;
    private static final int INFO_H = 15;
    private static final int ITEM_SLOT_SIZE = 18;
    private static final int ITEM_SPACING   = 2;
    private static final int GRID_MARGIN    = 4;

    // ── State ─────────────────────────────────────────────────────────────────

    private int  totalItems = 0;
    private int  totalPrice = 0;
    private long playerBalance = 0;
    private float buyMultiplier  = 1.0f;
    private float sellMultiplier = 0.5f;
    private long grocerBalance   = 0;

    public enum TradeMode { BUY, SELL }
    private TradeMode currentMode = TradeMode.BUY;

    private List<GrocerSystem.GrocerInventoryEntry>          grocerEntries = new ArrayList<>();
    private List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries = new ArrayList<>();
    private final String grocerName;
    private boolean dataLoaded = false;

    private Set<Integer>         selectedEntryIndices  = new HashSet<>();
    private Map<String, Integer> transactionAmounts    = new HashMap<>();

    private int scrollOffset            = 0;
    private int transactionScrollOffset = 0;

    private Object  currentTooltipEntry = null;
    private int     tooltipX = 0, tooltipY = 0;
    private boolean tooltipPositioned = false;

    private static GrocerInventoryScreen currentInstance = null;

    // ── Constructor ───────────────────────────────────────────────────────────

    public GrocerInventoryScreen(String grocerName) {
        super(Component.literal("Grocer - " + grocerName));
        this.grocerName = grocerName;
        currentInstance = this;
    }

    // ── Static helpers ────────────────────────────────────────────────────────

    public static boolean isItemAllowed(String itemKey)  { return ItemPricing.isItemAllowed(itemKey); }
    public static int     getItemPrice(String itemKey)   { return ItemPricing.getItemPrice(itemKey); }
    public static int     getItemSellPrice(String itemKey){ return ItemPricing.getItemSellPrice(itemKey); }
    public static GrocerInventoryScreen getCurrentInstance() { return currentInstance; }

    public static void updateInventoryData(String grocerName,
                                           List<GrocerSystem.GrocerInventoryEntry> grocerEntries,
                                           List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries,
                                           long grocerBalance, float buyMultiplier, float sellMultiplier) {
        if (currentInstance != null && currentInstance.grocerName.equals(grocerName)) {
            List<GrocerSystem.GrocerInventoryEntry> filtered = new ArrayList<>();
            for (GrocerSystem.GrocerInventoryEntry e : grocerEntries)
                if (ItemPricing.isItemAllowed(e.itemKey)) filtered.add(e);

            currentInstance.grocerEntries   = filtered;
            currentInstance.playerEntries   = playerEntries;
            currentInstance.grocerBalance   = grocerBalance;
            currentInstance.buyMultiplier   = buyMultiplier;
            currentInstance.sellMultiplier  = sellMultiplier;
            currentInstance.dataLoaded      = true;

            currentInstance.selectedEntryIndices.removeIf(
                    idx -> idx >= currentInstance.getCurrentInventoryEntries().size());
            currentInstance.transactionAmounts.entrySet().removeIf(e -> {
                for (Object entry : currentInstance.getCurrentInventoryEntries())
                    if (e.getKey().equals(currentInstance.getItemKeyFromEntry(entry))) return false;
                return true;
            });
        }
    }

    public static void updatePlayerBalance(long newBalance) {
        if (currentInstance != null) currentInstance.playerBalance = newBalance;
    }

    // ── Screen lifecycle ──────────────────────────────────────────────────────

    @Override
    protected void init() {
        super.init();
        if (minecraft != null) minecraft.options.hideGui = true;
    }

    @Override public boolean isPauseScreen() { return false; }
    @Override public void renderBackground(GuiGraphics g, int mx, int my, float pt) { }

    @Override
    public void onClose() {
        if (minecraft != null) minecraft.options.hideGui = false;
        super.onClose();
        if (currentInstance == this) { currentInstance = null; transactionAmounts.clear(); }
    }

    // ── Layout ────────────────────────────────────────────────────────────────

    /**
     * Returns the uniform scale: screenH / TEXTURE_HEIGHT.
     * The canvas is centred horizontally; it may extend beyond screen edges.
     */
    private float getScale()   { return (float) height / TEXTURE_HEIGHT; }
    private float getOffsetX() { return (width - TEXTURE_WIDTH * getScale()) / 2f; }

    /** Convert a virtual X coord to real screen X. */
    private int toRealX(int vx) { return Math.round(vx * getScale() + getOffsetX()); }
    /** Convert a virtual Y coord to real screen Y. */
    private int toRealY(int vy) { return Math.round(vy * getScale()); }
    /** Convert a virtual size to real screen pixels. */
    private int toRealS(int vs) { return Math.round(vs * getScale()); }

    /** Convert a real screen X to virtual texture X. */
    private int toVirtX(double rx) { return (int)((rx - getOffsetX()) / getScale()); }
    /** Convert a real screen Y to virtual texture Y. */
    private int toVirtY(double ry) { return (int)(ry / getScale()); }

    // ── Entry helpers ─────────────────────────────────────────────────────────

    private List<?> getCurrentInventoryEntries() {
        return currentMode == TradeMode.BUY ? grocerEntries : playerEntries;
    }

    private String getItemKeyFromEntry(Object e) {
        if (e instanceof GrocerSystem.GrocerInventoryEntry g) return g.itemKey;
        if (e instanceof OpenGrocerInventoryPacket.PlayerInventoryEntry p) return p.itemKey;
        return null;
    }

    private String getDisplayNameFromEntry(Object e) {
        if (e instanceof GrocerSystem.GrocerInventoryEntry g) return g.displayName;
        if (e instanceof OpenGrocerInventoryPacket.PlayerInventoryEntry p) return p.displayName;
        return "";
    }

    private long getAmountFromEntry(Object e) {
        if (e instanceof GrocerSystem.GrocerInventoryEntry g) return g.amount;
        if (e instanceof OpenGrocerInventoryPacket.PlayerInventoryEntry p) return p.amount;
        return 0;
    }

    private long getPlayerBalance() {
        if (minecraft != null && minecraft.player != null)
            return minecraft.player.getPersistentData().getLong("agotmod.coin_balance");
        return 0;
    }

    // ── Render ────────────────────────────────────────────────────────────────

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);

        float scale   = getScale();
        float offsetX = getOffsetX();

        // Push virtual canvas pose
        g.pose().pushPose();
        g.pose().translate(offsetX, 0, 0);
        g.pose().scale(scale, scale, 1f);

        // Virtual mouse coords
        int vmx = toVirtX(mouseX);
        int vmy = toVirtY(mouseY);

        // ── Layer 1: trader texture ───────────────────────────────────────────
        g.blit(TRADER_TEXTURE, 0, 0, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        // ── Layer 2: seal texture (only when cart has items) ──────────────────
        calculateTotals();
        if (totalItems > 0) {
            com.mojang.blaze3d.systems.RenderSystem.enableBlend();
            com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc();
            g.blit(SEAL_TEXTURE, 0, 0, 0, 0, TEXTURE_WIDTH, TEXTURE_HEIGHT, TEXTURE_WIDTH, TEXTURE_HEIGHT);
            com.mojang.blaze3d.systems.RenderSystem.disableBlend();
        }

        // ── Layer 3: left panel content ───────────────────────────────────────
        drawLeftPanel(g, vmx, vmy);

        // ── Layer 4: right panel content ──────────────────────────────────────
        drawRightPanel(g, vmx, vmy);

        // ── Layer 5: trader info (name, balance, mode) ────────────────────────
        drawTraderInfo(g);

        g.pose().popPose();

        // Tooltip is drawn outside pose in real screen coords
        if (currentTooltipEntry != null && tooltipPositioned)
            drawItemTooltip(g, currentTooltipEntry);
    }

    // ── Left panel ────────────────────────────────────────────────────────────

    private void drawLeftPanel(GuiGraphics g, int vmx, int vmy) {
        if (!dataLoaded) {
            drawTextCentered(g, "Loading...", LEFT_X + LEFT_W / 2, LEFT_Y + LEFT_H / 2, TEXT_COLOR);
            return;
        }

        List<?> entries = getCurrentInventoryEntries();
        if (entries.isEmpty()) {
            String msg = currentMode == TradeMode.BUY ? "Nothing for sale" : "Nothing to sell";
            drawTextCentered(g, msg, LEFT_X + LEFT_W / 2, LEFT_Y + LEFT_H / 2, TEXT_COLOR);
            return;
        }

        // Grid of items below the button row
        int gridStartY = LEFT_Y + GRID_MARGIN;
        int availW     = LEFT_W - GRID_MARGIN * 2;
        int availH     = LEFT_H - GRID_MARGIN * 2;
        int itemsPerRow = Math.max(1, (availW + ITEM_SPACING) / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int rowsVisible = Math.max(1, availH / (ITEM_SLOT_SIZE + ITEM_SPACING));

        int totalGridW = itemsPerRow * ITEM_SLOT_SIZE + (itemsPerRow - 1) * ITEM_SPACING;
        int gridX      = LEFT_X + GRID_MARGIN + (availW - totalGridW) / 2;

        int startIdx = scrollOffset * itemsPerRow;
        int endIdx   = Math.min(startIdx + rowsVisible * itemsPerRow, entries.size());

        Object hovered = null;

        // Pass 1: slot backgrounds and item icons
        for (int i = startIdx; i < endIdx; i++) {
            Object entry  = entries.get(i);
            int rel       = i - startIdx;
            int col       = rel % itemsPerRow;
            int row       = rel / itemsPerRow;
            int slotX     = gridX + col * (ITEM_SLOT_SIZE + ITEM_SPACING);
            int slotY     = gridStartY + row * (ITEM_SLOT_SIZE + ITEM_SPACING);

            boolean selected = selectedEntryIndices.contains(i);
            g.fill(slotX, slotY, slotX + ITEM_SLOT_SIZE, slotY + ITEM_SLOT_SIZE,
                    selected ? 0x80FFFFFF : 0x40000000);

            String itemKey = getItemKeyFromEntry(entry);
            net.minecraft.resources.ResourceLocation loc =
                    net.minecraft.resources.ResourceLocation.tryParse(itemKey);
            if (loc != null) {
                net.minecraft.world.item.Item item =
                        net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != net.minecraft.world.item.Items.AIR) {
                    g.renderItem(new net.minecraft.world.item.ItemStack(item),
                            slotX + 1, slotY + 1);

                    if (vmx >= slotX && vmx < slotX + ITEM_SLOT_SIZE &&
                            vmy >= slotY && vmy < slotY + ITEM_SLOT_SIZE) {
                        hovered = entry;
                        if (currentTooltipEntry != entry || !tooltipPositioned) {
                            currentTooltipEntry = entry;
                            tooltipPositioned   = true;
                            tooltipX = Math.round((slotX + ITEM_SLOT_SIZE + 2) * getScale() + getOffsetX());
                            tooltipY = Math.round(slotY * getScale());
                        }
                    }
                }
            }
        }

        // Pass 2: amount text at bottom-right of slot, 0.6 scale, on top of items
        for (int i = startIdx; i < endIdx; i++) {
            Object entry = entries.get(i);
            int rel      = i - startIdx;
            int col      = rel % itemsPerRow;
            int row      = rel / itemsPerRow;
            int slotX    = gridX + col * (ITEM_SLOT_SIZE + ITEM_SPACING);
            int slotY    = gridStartY + row * (ITEM_SLOT_SIZE + ITEM_SPACING);

            String amtText = formatNumber(getAmountFromEntry(entry));
            float s  = 0.6f;
            int   tw = (int)(font.width(amtText) * s);
            int   th = (int)(font.lineHeight * s);
            // Centre within the bottom-right quarter of the slot
            int   tx = (int)((slotX + ITEM_SLOT_SIZE - tw / 2 - 2) / s);
            int   ty = (int)((slotY + ITEM_SLOT_SIZE - th - 1)      / s);

            g.pose().pushPose();
            g.pose().translate(0, 0, 300);
            g.pose().scale(s, s, 1f);
            // Black outline
            g.drawString(font, amtText, tx - 1, ty,     0xFF000000, false);
            g.drawString(font, amtText, tx + 1, ty,     0xFF000000, false);
            g.drawString(font, amtText, tx,     ty - 1, 0xFF000000, false);
            g.drawString(font, amtText, tx,     ty + 1, 0xFF000000, false);
            // White text
            g.drawString(font, amtText, tx, ty, 0xFFFFFFFF, false);
            g.pose().popPose();
        }

        if (hovered == null) { currentTooltipEntry = null; tooltipPositioned = false; }

        // Scroll indicator if needed
        int totalRows = (int) Math.ceil((double) entries.size() / itemsPerRow);
        if (totalRows > rowsVisible) {
            int barX = LEFT_X + LEFT_W - 4;
            int barY = gridStartY;
            int barH = availH;
            g.fill(barX, barY, barX + 3, barY + barH, 0xFF555555);
            int thumbH   = Math.max(8, rowsVisible * barH / totalRows);
            int maxScroll = Math.max(1, totalRows - rowsVisible);
            int thumbY   = barY + scrollOffset * (barH - thumbH) / maxScroll;
            g.fill(barX, thumbY, barX + 3, thumbY + thumbH, 0xFFC6C6C6);
        }
    }

    // ── Right panel ───────────────────────────────────────────────────────────

    private void drawRightPanel(GuiGraphics g, int vmx, int vmy) {
        calculateTotals();

        int y     = RIGHT_Y + 3;
        int lineH = 13;
        int availH = BOTTOM_Y - 1 - y;
        int maxVisible = Math.max(1, availH / lineH);

        if (selectedEntryIndices.isEmpty()) {
            drawTextCenteredScaled(g, "No items", RIGHT_X + RIGHT_W / 2, RIGHT_Y + RIGHT_H / 2 - 4, TEXT_COLOR, 0.55f);
        } else {
            List<Integer> sorted  = new ArrayList<>(selectedEntryIndices);
            sorted.sort(Integer::compareTo);
            List<?> entries = getCurrentInventoryEntries();

            int shown = 0;
            for (int i = transactionScrollOffset; i < sorted.size() && shown < maxVisible; i++, shown++) {
                Integer idx = sorted.get(i);
                if (idx >= entries.size()) continue;

                Object entry    = entries.get(idx);
                String itemKey  = getItemKeyFromEntry(entry);
                String dispName = getDisplayNameFromEntry(entry);
                int    amount   = transactionAmounts.getOrDefault(itemKey, 0);
                float  mul      = currentMode == TradeMode.BUY ? buyMultiplier : sellMultiplier;
                int    unitPrice = (int) Math.ceil(ItemPricing.getItemPrice(itemKey) * mul);
                int    lineTotal = unitPrice * amount;

                int rowY = y + shown * lineH;

                // Item icon at 0.4 scale (6px tall)
                net.minecraft.resources.ResourceLocation loc =
                        net.minecraft.resources.ResourceLocation.tryParse(itemKey);
                if (loc != null) {
                    net.minecraft.world.item.Item item =
                            net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
                    if (item != null && item != net.minecraft.world.item.Items.AIR) {
                        g.pose().pushPose();
                        g.pose().scale(0.4f, 0.4f, 1f);
                        g.renderItem(new net.minecraft.world.item.ItemStack(item),
                                (int)((RIGHT_X + 1) / 0.4f), (int)(rowY / 0.4f));
                        g.pose().popPose();
                    }
                }

                // "name x amount = price" — full name, small text
                String line = dispName + " x " + amount + " = " + lineTotal + "c";
                drawTextScaled(g, line, RIGHT_X + 8, rowY + 2, TEXT_COLOR, 0.4f);

                // +/- icon texture at same 0.4f scale as item icon (16px tall → 6px on screen)
                int iconX = RIGHT_X + RIGHT_W - 16;
                g.pose().pushPose();
                g.pose().scale(0.4f, 0.4f, 1f);
                g.blit(CHANGE_AMOUNT_TEXTURE,
                        (int)(iconX / 0.4f), (int)(rowY / 0.4f),
                        0, 0, 37, 16, 37, 16);
                g.pose().popPose();
            }
        }

        // Separator just above the bottom area
        g.fill(RIGHT_X + 1, BOTTOM_Y - 1, RIGHT_X + RIGHT_W - 1, BOTTOM_Y, TEXT_COLOR);

        // Total price inside the bottom area, centred
        String totalStr = "Total: " + totalPrice + "c";
        drawTextCenteredScaled(g, totalStr, BOTTOM_X + BOTTOM_W / 2, BOTTOM_Y + 4, TEXT_COLOR, 0.55f);

        // Player balance below total
        String balStr = "Your Bal: " + formatCurrency(getPlayerBalance());
        drawTextCenteredScaled(g, balStr, BOTTOM_X + BOTTOM_W / 2, BOTTOM_Y + 13, TEXT_COLOR_GREEN, 0.55f);
    }

    private void drawTraderInfo(GuiGraphics g) {
        int cx    = INFO_X + INFO_W / 2;
        int lineH = 5;
        int y     = INFO_Y;
        float s   = 0.4f;

        drawTextCenteredScaled(g, grocerName, cx, y, TEXT_COLOR, s);
        y += lineH;

        long bal = grocerBalance;
        drawTextCenteredScaled(g, "Balance: " + formatCurrency(bal), cx, y, TEXT_COLOR_GREEN, s);
        y += lineH;

        drawTextCenteredScaled(g, currentMode == TradeMode.BUY ? "Buying" : "Selling", cx, y, TEXT_COLOR, s);
    }

    // ── Draw helpers ──────────────────────────────────────────────────────────

    private void drawSimpleButton(GuiGraphics g, int x, int y, int w, int h, String label, int color) {
        g.fill(x, y, x + w, y + h, color);
        g.fill(x, y, x + w, y + 1, 0xFFCCCCCC);
        g.fill(x, y, x + 1, y + h, 0xFFCCCCCC);
        g.fill(x + w - 1, y, x + w, y + h, 0xFF444444);
        g.fill(x, y + h - 1, x + w, y + h, 0xFF444444);
        float s = 0.5f;
        int tw = (int)(font.width(label) * s);
        g.pose().pushPose();
        g.pose().scale(s, s, 1f);
        g.drawString(font, label, (int)((x + (w - tw) / 2) / s), (int)((y + (h - font.lineHeight * s) / 2) / s), 0xFFFFFFFF, false);
        g.pose().popPose();
    }

    private void drawTextCentered(GuiGraphics g, String text, int cx, int y, int color) {
        g.drawString(font, text, cx - font.width(text) / 2, y, color, false);
    }

    private void drawTextCenteredScaled(GuiGraphics g, String text, int cx, int y, int color, float s) {
        int tw = (int)(font.width(text) * s);
        g.pose().pushPose();
        g.pose().scale(s, s, 1f);
        g.drawString(font, text, (int)((cx - tw / 2) / s), (int)(y / s), color, false);
        g.pose().popPose();
    }

    private void drawTextScaled(GuiGraphics g, String text, int x, int y, int color, float s) {
        g.pose().pushPose();
        g.pose().scale(s, s, 1f);
        g.drawString(font, text, (int)(x / s), (int)(y / s), color, false);
        g.pose().popPose();
    }

    private void drawItemTooltip(GuiGraphics g, Object entry) {
        String name     = getDisplayNameFromEntry(entry);
        String itemKey  = getItemKeyFromEntry(entry);
        long   amount   = getAmountFromEntry(entry);
        int    buyP     = (int) Math.ceil(ItemPricing.getItemPrice(itemKey) * buyMultiplier);
        int    sellP    = (int) Math.ceil(ItemPricing.getItemPrice(itemKey) * sellMultiplier);

        String[] lines = { name, "Amount: " + amount, "Buy: " + buyP, "Sell: " + sellP };
        int tw = 0;
        for (String l : lines) tw = Math.max(tw, font.width(l));
        int bw = tw + 12, bh = lines.length * (font.lineHeight + 2) + 8;

        int tx = tooltipX, ty = tooltipY;
        if (tx + bw > width  - 4) tx = width  - bw - 4;
        if (ty + bh > height - 4) ty = height - bh - 4;

        g.pose().pushPose();
        g.pose().translate(0, 0, 300);
        g.fill(tx, ty, tx + bw, ty + bh, 0xE0000000);
        g.fill(tx, ty, tx + bw, ty + 1, 0xFF555555);
        g.fill(tx, ty, tx + 1, ty + bh, 0xFF555555);
        g.fill(tx + bw - 1, ty, tx + bw, ty + bh, 0xFF555555);
        g.fill(tx, ty + bh - 1, tx + bw, ty + bh, 0xFF555555);
        int ly = ty + 5;
        for (String l : lines) {
            g.drawString(font, l, tx + 6, ly, 0xFFFFFF, false);
            ly += font.lineHeight + 2;
        }
        g.pose().popPose();
    }

    // ── Input ─────────────────────────────────────────────────────────────────

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0 || !dataLoaded) return super.mouseClicked(mouseX, mouseY, button);

        int vmx = toVirtX(mouseX);
        int vmy = toVirtY(mouseY);

        // Buy button
        if (vmx >= BUY_BTN_X && vmx < BUY_BTN_X + BUY_BTN_W &&
                vmy >= BUY_BTN_Y && vmy < BUY_BTN_Y + BUY_BTN_H) {
            if (currentMode != TradeMode.BUY) toggleMode();
            return true;
        }

        // Sell button
        if (vmx >= SELL_BTN_X && vmx < SELL_BTN_X + SELL_BTN_W &&
                vmy >= SELL_BTN_Y && vmy < SELL_BTN_Y + SELL_BTN_H) {
            if (currentMode != TradeMode.SELL) toggleMode();
            return true;
        }

        // Left panel — item selection
        if (vmx >= LEFT_X && vmx < LEFT_X + LEFT_W && vmy >= LEFT_Y && vmy < LEFT_Y + LEFT_H) {
            int idx = getClickedItemIndex(vmx, vmy);
            if (idx >= 0) { toggleEntrySelection(idx); playButtonSound(); return true; }
        }

        // Seal button — finish transaction (only active when cart has items)
        if (vmx >= SEAL_BTN_X && vmx < SEAL_BTN_X + SEAL_BTN_W &&
                vmy >= SEAL_BTN_Y && vmy < SEAL_BTN_Y + SEAL_BTN_H) {
            calculateTotals();
            long limBal = currentMode == TradeMode.BUY ? getPlayerBalance() : grocerBalance;
            if (totalItems > 0 && limBal >= totalPrice) {
                finishTransaction();
                playButtonSound();
            }
            return true;
        }

        // Right panel — +/- buttons on transaction items
        if (vmx >= RIGHT_X && vmx < RIGHT_X + RIGHT_W && vmy >= RIGHT_Y && vmy < RIGHT_Y + RIGHT_H) {
            handleTransactionButtonClick(vmx, vmy); return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private int getClickedItemIndex(int vmx, int vmy) {
        int gridStartY  = LEFT_Y + GRID_MARGIN;
        int availW      = LEFT_W - GRID_MARGIN * 2;
        int itemsPerRow = Math.max(1, (availW + ITEM_SPACING) / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int totalGridW  = itemsPerRow * ITEM_SLOT_SIZE + (itemsPerRow - 1) * ITEM_SPACING;
        int gridX       = LEFT_X + GRID_MARGIN + (availW - totalGridW) / 2;

        int availH      = LEFT_H - GRID_MARGIN * 2;
        int rowsVisible = Math.max(1, availH / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int startIdx    = scrollOffset * itemsPerRow;
        List<?> entries = getCurrentInventoryEntries();
        int     total   = Math.min(startIdx + rowsVisible * itemsPerRow, entries.size());

        for (int i = startIdx; i < total; i++) {
            int rel   = i - startIdx;
            int col   = rel % itemsPerRow;
            int row   = rel / itemsPerRow;
            int slotX = gridX + col * (ITEM_SLOT_SIZE + ITEM_SPACING);
            int slotY = gridStartY + row * (ITEM_SLOT_SIZE + ITEM_SPACING);
            // Extend the click bottom of the last row to the panel bottom
            boolean isLastRow = (row == rowsVisible - 1);
            int     slotBot   = isLastRow ? LEFT_Y + LEFT_H : slotY + ITEM_SLOT_SIZE;
            if (vmx >= slotX && vmx < slotX + ITEM_SLOT_SIZE &&
                    vmy >= slotY && vmy < slotBot) return i;
        }
        return -1;
    }

    private void handleTransactionButtonClick(int vmx, int vmy) {
        List<Integer> sorted  = new ArrayList<>(selectedEntryIndices);
        sorted.sort(Integer::compareTo);
        List<?> entries = getCurrentInventoryEntries();

        boolean shift = hasShiftDown();
        boolean ctrl  = hasControlDown();
        int delta = (shift && ctrl) ? 100 : shift ? 10 : ctrl ? 5 : 1;

        int y      = RIGHT_Y + 3;
        int lineH  = 13;
        int availH = BOTTOM_Y - 1 - y;
        int maxVis = Math.max(1, availH / lineH);

        for (int i = transactionScrollOffset; i < sorted.size() && (i - transactionScrollOffset) < maxVis; i++) {
            Integer idx   = sorted.get(i);
            if (idx >= entries.size()) continue;
            Object entry  = entries.get(idx);
            String itemKey = getItemKeyFromEntry(entry);
            long   maxAmt  = getAmountFromEntry(entry);
            int    shown   = i - transactionScrollOffset;
            int    rowY    = y + shown * lineH;

            int iconX     = RIGHT_X + RIGHT_W - 16;
            int minusBtnX = iconX;
            int plusBtnX  = iconX + 8; // 6px minus icon + 2px gap

            if (vmx >= minusBtnX && vmx < minusBtnX + 6 && vmy >= rowY && vmy < rowY + 6) {
                adjustAmount(itemKey, maxAmt, false, delta); playButtonSound(); return;
            }
            if (vmx >= plusBtnX && vmx < plusBtnX + 6 && vmy >= rowY && vmy < rowY + 6) {
                adjustAmount(itemKey, maxAmt, true, delta); playButtonSound(); return;
            }
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double sx, double sy) {
        int vmx = toVirtX(mouseX);
        int vmy = toVirtY(mouseY);

        if (vmx >= LEFT_X && vmx < LEFT_X + LEFT_W && vmy >= LEFT_Y && vmy < LEFT_Y + LEFT_H) {
            if (sy > 0) scrollUp(); else scrollDown(); return true;
        }
        if (vmx >= RIGHT_X && vmx < RIGHT_X + RIGHT_W && vmy >= RIGHT_Y && vmy < RIGHT_Y + RIGHT_H) {
            if (sy > 0) transactionScrollUp(); else transactionScrollDown(); return true;
        }
        return super.mouseScrolled(mouseX, mouseY, sx, sy);
    }

    // ── Trade logic ───────────────────────────────────────────────────────────

    private void toggleMode() {
        currentMode = currentMode == TradeMode.BUY ? TradeMode.SELL : TradeMode.BUY;
        selectedEntryIndices.clear(); transactionAmounts.clear();
        scrollOffset = 0; transactionScrollOffset = 0;
        currentTooltipEntry = null; tooltipPositioned = false;
        playButtonSound();
    }

    private void toggleEntrySelection(int idx) {
        List<?> entries = getCurrentInventoryEntries();
        if (idx >= entries.size()) return;
        String itemKey = getItemKeyFromEntry(entries.get(idx));
        if (selectedEntryIndices.contains(idx)) {
            selectedEntryIndices.remove(idx);
            if (itemKey != null) transactionAmounts.remove(itemKey);
        } else {
            selectedEntryIndices.add(idx);
            if (itemKey != null) transactionAmounts.put(itemKey, 0);
        }
        transactionScrollOffset = 0;
    }

    private void adjustAmount(String itemKey, long max, boolean increase, int delta) {
        int cur = transactionAmounts.getOrDefault(itemKey, 0);
        transactionAmounts.put(itemKey, (int)(increase
                ? Math.min(cur + delta, max)
                : Math.max(cur - delta, 0)));
    }

    private void calculateTotals() {
        totalItems = 0; totalPrice = 0;
        for (Map.Entry<String, Integer> e : transactionAmounts.entrySet()) {
            int amt = e.getValue();
            if (amt <= 0) continue;
            totalItems += amt;
            float mul = currentMode == TradeMode.BUY ? buyMultiplier : sellMultiplier;
            totalPrice += (int) Math.ceil(ItemPricing.getItemPrice(e.getKey()) * mul) * amt;
        }
    }

    private void finishTransaction() {
        if (totalItems <= 0) return;
        Map<String, Integer> items = new HashMap<>();
        Map<String, Integer> slots = new HashMap<>();
        for (Map.Entry<String, Integer> e : transactionAmounts.entrySet()) {
            if (e.getValue() > 0) { items.put(e.getKey(), e.getValue()); if (currentMode == TradeMode.SELL) slots.put(e.getKey(), 0); }
        }
        FinishTransactionPacket pkt = currentMode == TradeMode.BUY
                ? new FinishTransactionPacket(grocerName, items)
                : new FinishTransactionPacket(grocerName, items, slots);
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(pkt);
        this.onClose();
    }

    private void scrollUp()   { if (scrollOffset > 0) scrollOffset--; }
    private void scrollDown() {
        List<?> entries = getCurrentInventoryEntries();
        int availW = LEFT_W - GRID_MARGIN * 2;
        int ipr    = Math.max(1, (availW + ITEM_SPACING) / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int availH = LEFT_H - GRID_MARGIN * 2;
        int vis    = Math.max(1, availH / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int total  = (int) Math.ceil((double) entries.size() / ipr);
        if (scrollOffset < total - vis) scrollOffset++;
    }
    private void transactionScrollUp()   { if (transactionScrollOffset > 0) transactionScrollOffset--; }
    private void transactionScrollDown() { if (transactionScrollOffset < selectedEntryIndices.size() - 1) transactionScrollOffset++; }

    // ── Utilities ─────────────────────────────────────────────────────────────

    private String formatCurrency(long coins) { return coins == 0 ? "0c" : coins + "c"; }
    private String formatNumber(long n) {
        if (n >= 1_000_000) return String.format("%.1fm", n / 1_000_000.0);
        if (n >= 1_000)     return String.format("%.1fk", n / 1_000.0);
        return String.valueOf(n);
    }

    private void playButtonSound() {
        if (minecraft != null && minecraft.player != null)
            minecraft.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(ModSounds.PAGE, 1.0F));
    }
}