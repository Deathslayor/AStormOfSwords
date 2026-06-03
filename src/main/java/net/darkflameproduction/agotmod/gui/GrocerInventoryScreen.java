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

    /** Actual pixel dimensions of gui_asset_trader_book.png. */
    private static final int TEXTURE_WIDTH  = 1082;
    private static final int TEXTURE_HEIGHT = 212;

    /** Left panel — inventory/products area (in texture virtual pixels). */
    private static final int LEFT_X = 368;
    private static final int LEFT_Y = 64;
    private static final int LEFT_W = 221;
    private static final int LEFT_H = 126;

    /** Right panel — transaction area (in texture virtual pixels). */
    private static final int RIGHT_X = 638;
    private static final int RIGHT_Y = 87;
    private static final int RIGHT_W = 61;
    private static final int RIGHT_H = 102;

    // ── Colors ────────────────────────────────────────────────────────────────

    private static final int TEXT_COLOR      = 0xFF000000;
    private static final int TEXT_COLOR_GREEN = 0xFF4CAF50;
    private static final int TEXT_COLOR_RED   = 0xFFFF5722;
    private static final int TEXT_COLOR_GOLD  = 0xFFFFD700;

    // ── Item grid constants ───────────────────────────────────────────────────

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

    @Override protected void init()    { super.init(); }
    @Override public boolean isPauseScreen() { return false; }
    @Override public void renderBackground(GuiGraphics g, int mx, int my, float pt) { }

    @Override
    public void onClose() {
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

        // ── Layer 2: left panel content ───────────────────────────────────────
        drawLeftPanel(g, vmx, vmy);

        // ── Layer 3: right panel content ──────────────────────────────────────
        drawRightPanel(g, vmx, vmy);

        g.pose().popPose();

        // Tooltip is drawn outside pose in real screen coords
        if (currentTooltipEntry != null && tooltipPositioned)
            drawItemTooltip(g, currentTooltipEntry);
    }

    // ── Left panel ────────────────────────────────────────────────────────────

    private void drawLeftPanel(GuiGraphics g, int vmx, int vmy) {
        // Mode toggle button at top of left panel
        int btnX = LEFT_X + 2;
        int btnY = LEFT_Y + 2;
        int btnW = 28;
        int btnH = 10;
        drawSimpleButton(g, btnX, btnY, btnW, btnH,
                currentMode == TradeMode.BUY ? "BUY" : "SELL", 0xFF888888);

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
        int gridStartY = LEFT_Y + btnH + 4;
        int availW     = LEFT_W - GRID_MARGIN * 2;
        int availH     = LEFT_H - (btnH + 4) - GRID_MARGIN;
        int itemsPerRow = Math.max(1, (availW + ITEM_SPACING) / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int rowsVisible = Math.max(1, availH / (ITEM_SLOT_SIZE + ITEM_SPACING));

        int totalGridW = itemsPerRow * ITEM_SLOT_SIZE + (itemsPerRow - 1) * ITEM_SPACING;
        int gridX      = LEFT_X + GRID_MARGIN + (availW - totalGridW) / 2;

        int startIdx = scrollOffset * itemsPerRow;
        int endIdx   = Math.min(startIdx + rowsVisible * itemsPerRow, entries.size());

        Object hovered = null;

        for (int i = startIdx; i < endIdx; i++) {
            Object entry  = entries.get(i);
            int rel       = i - startIdx;
            int col       = rel % itemsPerRow;
            int row       = rel / itemsPerRow;
            int slotX     = gridX + col * (ITEM_SLOT_SIZE + ITEM_SPACING);
            int slotY     = gridStartY + row * (ITEM_SLOT_SIZE + ITEM_SPACING);

            boolean selected = selectedEntryIndices.contains(i);
            // Slot background
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
                            currentTooltipEntry  = entry;
                            tooltipPositioned    = true;
                            // tooltip position calculated in real screen coords
                            tooltipX = Math.round((slotX + ITEM_SLOT_SIZE + 2) * getScale() + getOffsetX());
                            tooltipY = Math.round(slotY * getScale());
                        }
                    }
                }
            }

            // Amount text
            String amtText = formatNumber(getAmountFromEntry(entry));
            g.pose().pushPose();
            g.pose().scale(0.5f, 0.5f, 1f);
            g.drawString(font, amtText,
                    (int)((slotX + 1) / 0.5f),
                    (int)((slotY + ITEM_SLOT_SIZE - 5) / 0.5f),
                    TEXT_COLOR_GREEN, false);
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

        int cx = RIGHT_X + RIGHT_W / 2;
        int y  = RIGHT_Y + 2;

        // Balance
        String balLabel = currentMode == TradeMode.BUY ? "Bal:" : "Groc:";
        long   bal      = currentMode == TradeMode.BUY ? getPlayerBalance() : grocerBalance;
        drawTextCenteredScaled(g, balLabel, cx, y, TEXT_COLOR, 0.6f);
        y += 7;
        drawTextCenteredScaled(g, formatCurrency(bal), cx, y, TEXT_COLOR_GREEN, 0.6f);
        y += 9;

        // Separator
        g.fill(RIGHT_X + 2, y, RIGHT_X + RIGHT_W - 2, y + 1, TEXT_COLOR);
        y += 3;

        if (selectedEntryIndices.isEmpty()) {
            drawTextCenteredScaled(g, "Select", cx, y + 20, TEXT_COLOR, 0.6f);
            drawTextCenteredScaled(g, "items", cx, y + 28, TEXT_COLOR, 0.6f);
        } else {
            // Transaction items
            int itemH = 14;
            int availH = RIGHT_H - (y - RIGHT_Y) - 30;
            int maxVisible = Math.max(1, availH / itemH);

            List<Integer> sorted = new ArrayList<>(selectedEntryIndices);
            sorted.sort(Integer::compareTo);
            List<?> entries = getCurrentInventoryEntries();

            int shown = 0;
            for (int i = transactionScrollOffset; i < sorted.size() && shown < maxVisible; i++, shown++) {
                Integer idx = sorted.get(i);
                if (idx >= entries.size()) continue;
                Object entry   = entries.get(idx);
                String itemKey = getItemKeyFromEntry(entry);
                int    amount  = transactionAmounts.getOrDefault(itemKey, 0);

                // Item icon tiny
                net.minecraft.resources.ResourceLocation loc =
                        net.minecraft.resources.ResourceLocation.tryParse(itemKey);
                if (loc != null) {
                    net.minecraft.world.item.Item item =
                            net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
                    if (item != null && item != net.minecraft.world.item.Items.AIR) {
                        g.pose().pushPose();
                        g.pose().scale(0.5f, 0.5f, 1f);
                        g.renderItem(new net.minecraft.world.item.ItemStack(item),
                                (int)((RIGHT_X + 2) / 0.5f), (int)(y / 0.5f));
                        g.pose().popPose();
                    }
                }

                // Amount
                drawTextScaled(g, "x" + amount, RIGHT_X + 12, y + 1, TEXT_COLOR, 0.5f);

                // +/- buttons
                int bx = RIGHT_X + RIGHT_W - 14;
                drawSimpleButton(g, bx,     y, 6, 6, "-", 0xFF888888);
                drawSimpleButton(g, bx + 7, y, 6, 6, "+", 0xFF888888);

                y += itemH;
            }
        }

        // Separator before totals
        int sepY = RIGHT_Y + RIGHT_H - 28;
        g.fill(RIGHT_X + 2, sepY, RIGHT_X + RIGHT_W - 2, sepY + 1, TEXT_COLOR);

        // Totals
        drawTextCenteredScaled(g, "Items: " + totalItems, cx, sepY + 3, TEXT_COLOR, 0.55f);
        drawTextCenteredScaled(g, totalPrice + " coins",  cx, sepY + 10, TEXT_COLOR, 0.55f);

        // Finish button
        int btnY = RIGHT_Y + RIGHT_H - 14;
        long limBal = currentMode == TradeMode.BUY ? getPlayerBalance() : grocerBalance;
        boolean canFinish = totalItems > 0 && limBal >= totalPrice;
        int btnColor = canFinish ? 0xFF888888 : 0xFF666666;
        String btnText = canFinish ?
                (currentMode == TradeMode.BUY ? "Buy" : "Sell") : "N/A";
        drawSimpleButton(g, RIGHT_X + 4, btnY, RIGHT_W - 8, 12, btnText, btnColor);
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

        // Mode toggle button
        int btnX = LEFT_X + 2, btnY = LEFT_Y + 2, btnW = 28, btnH = 10;
        if (vmx >= btnX && vmx < btnX + btnW && vmy >= btnY && vmy < btnY + btnH) {
            toggleMode(); return true;
        }

        // Left panel — item selection
        if (vmx >= LEFT_X && vmx < LEFT_X + LEFT_W && vmy >= LEFT_Y && vmy < LEFT_Y + LEFT_H) {
            int idx = getClickedItemIndex(vmx, vmy);
            if (idx >= 0) { toggleEntrySelection(idx); playButtonSound(); return true; }
        }

        // Right panel — transaction buttons + finish
        if (vmx >= RIGHT_X && vmx < RIGHT_X + RIGHT_W && vmy >= RIGHT_Y && vmy < RIGHT_Y + RIGHT_H) {
            // Finish button
            int finBtnY = RIGHT_Y + RIGHT_H - 14;
            if (vmy >= finBtnY && vmy < finBtnY + 12 &&
                    vmx >= RIGHT_X + 4 && vmx < RIGHT_X + RIGHT_W - 4) {
                long limBal = currentMode == TradeMode.BUY ? getPlayerBalance() : grocerBalance;
                if (totalItems > 0 && limBal >= totalPrice) { finishTransaction(); return true; }
                return true;
            }

            // +/- buttons on transaction items
            handleTransactionButtonClick(vmx, vmy); return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private int getClickedItemIndex(int vmx, int vmy) {
        int gridStartY = LEFT_Y + 10 + 4;
        int availW     = LEFT_W - GRID_MARGIN * 2;
        int itemsPerRow = Math.max(1, (availW + ITEM_SPACING) / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int totalGridW  = itemsPerRow * ITEM_SLOT_SIZE + (itemsPerRow - 1) * ITEM_SPACING;
        int gridX       = LEFT_X + GRID_MARGIN + (availW - totalGridW) / 2;

        int availH     = LEFT_H - (10 + 4) - GRID_MARGIN;
        int rowsVisible = Math.max(1, availH / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int startIdx    = scrollOffset * itemsPerRow;
        List<?> entries = getCurrentInventoryEntries();

        for (int i = startIdx; i < Math.min(startIdx + rowsVisible * itemsPerRow, entries.size()); i++) {
            int rel  = i - startIdx;
            int col  = rel % itemsPerRow;
            int row  = rel / itemsPerRow;
            int slotX = gridX + col * (ITEM_SLOT_SIZE + ITEM_SPACING);
            int slotY = gridStartY + row * (ITEM_SLOT_SIZE + ITEM_SPACING);
            if (vmx >= slotX && vmx < slotX + ITEM_SLOT_SIZE &&
                    vmy >= slotY && vmy < slotY + ITEM_SLOT_SIZE) return i;
        }
        return -1;
    }

    private void handleTransactionButtonClick(int vmx, int vmy) {
        List<Integer> sorted  = new ArrayList<>(selectedEntryIndices);
        sorted.sort(Integer::compareTo);
        List<?> entries = getCurrentInventoryEntries();

        int y      = RIGHT_Y + 2 + 7 + 9 + 3; // matches drawRightPanel layout
        int itemH  = 14;
        int availH = RIGHT_H - (y - RIGHT_Y) - 30;
        int maxVis = Math.max(1, availH / itemH);

        for (int i = transactionScrollOffset; i < sorted.size() && (i - transactionScrollOffset) < maxVis; i++) {
            Integer idx = sorted.get(i);
            if (idx >= entries.size()) continue;
            Object entry   = entries.get(idx);
            String itemKey = getItemKeyFromEntry(entry);
            long   maxAmt  = getAmountFromEntry(entry);

            int bx = RIGHT_X + RIGHT_W - 14;
            // Minus
            if (vmx >= bx && vmx < bx + 6 && vmy >= y && vmy < y + 6) {
                adjustAmount(itemKey, maxAmt, false, 1); playButtonSound(); return;
            }
            // Plus
            if (vmx >= bx + 7 && vmx < bx + 13 && vmy >= y && vmy < y + 6) {
                adjustAmount(itemKey, maxAmt, true, 1); playButtonSound(); return;
            }
            y += itemH;
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
        int availH = LEFT_H - 14 - GRID_MARGIN;
        int vis    = Math.max(1, availH / (ITEM_SLOT_SIZE + ITEM_SPACING));
        int total  = (int) Math.ceil((double) entries.size() / ipr);
        if (scrollOffset < total - vis) scrollOffset++;
    }
    private void transactionScrollUp()   { if (transactionScrollOffset > 0) transactionScrollOffset--; }
    private void transactionScrollDown() { if (transactionScrollOffset < selectedEntryIndices.size() - 1) transactionScrollOffset++; }

    // ── Utilities ─────────────────────────────────────────────────────────────

    private String formatCurrency(long coins) { return coins == 0 ? "0c" : coins + "c"; }
    private String formatNumber(long n) {
        if (n >= 1_000_000) return String.format("%.1fM", n / 1_000_000.0);
        if (n >= 1_000)     return String.format("%.1fK", n / 1_000.0);
        return String.valueOf(n);
    }

    private void playButtonSound() {
        if (minecraft != null && minecraft.player != null)
            minecraft.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(ModSounds.PAGE, 1.0F));
    }
}