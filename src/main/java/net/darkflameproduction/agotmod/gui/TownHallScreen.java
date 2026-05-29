package net.darkflameproduction.agotmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.Culture;
import net.darkflameproduction.agotmod.network.ClaimTownHallPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.core.BlockPos;

import java.util.*;

@OnlyIn(Dist.CLIENT)
public class TownHallScreen extends Screen {

    private static final ResourceLocation PAPER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paper.png");
    private static final ResourceLocation PAPER_SIDE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paperside.png");
    private static final ResourceLocation PAPER_SIDETOP_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papersidetop.png");
    private static final ResourceLocation PAPER_CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papercorner.png");

    private static final int TEXT_COLOR   = 0xFF000000;
    private static final int COLOR_GREEN  = 0xFF2E7D32;
    private static final int COLOR_BLUE   = 0xFF1565C0;
    private static final int COLOR_RED    = 0xFFD32F2F;
    private static final int COLOR_ORANGE = 0xFFFF8F00;
    private static final int COLOR_GRAY   = 0xFF757575;
    private static final int COLOR_GOLD   = 0xFFB8860B;
    private static final int COLOR_HEADER = 0xFF1A237E;
    private static final int COLOR_PURPLE = 0xFF4A148C;

    private static final int BORDER_PILLAR_WIDTH = 6;
    private static final int BORDER_HEIGHT       = 6;
    private static final int CORNER_SIZE         = 12;

    private static final int ROW_HEIGHT = 14;

    private final BlockPos townHallPos;

    private Culture townCulture = Culture.NONE;
    private net.minecraft.client.gui.components.Button chooseCultureButton = null;

    // Town data
    private int    bedCount       = 0;
    private int    citizenCount   = 0;
    private int    currentRadius  = 16;
    private String townName       = "Unnamed Town";
    private boolean isClaimed     = false;
    private String claimedByHouse = "";
    private long   townBalance    = 0;
    private long   townIncome     = 0;

    // Job data
    private int availableJobCount = 0;
    private int assignedJobCount  = 0;
    private int totalJobCount     = 0;
    private int joblessCount      = 0;

    // Inventory data
    private Map<String, Long> townInventory = new LinkedHashMap<>();

    // Tab state Ã¢â‚¬â€ 0 = Town Overview, 1 = Inventory
    private int activeTab = 0;

    // Scroll state for inventory tab
    private int inventoryScrollOffset = 0;

    // Rename UI state
    private boolean renameModeActive = false;
    private net.minecraft.client.gui.components.EditBox  townNameInput       = null;
    private net.minecraft.client.gui.components.Button  confirmRenameButton = null;
    private net.minecraft.client.gui.components.Button  renameButton        = null;
    private net.minecraft.client.gui.components.Button  claimButton         = null;
    private net.minecraft.client.gui.components.Button  tabOverviewButton   = null;
    private net.minecraft.client.gui.components.Button  tabInventoryButton  = null;

    private static TownHallScreen currentInstance = null;

    public TownHallScreen(BlockPos pos) {
        super(Component.literal("Town Hall"));
        this.townHallPos = pos;
        currentInstance  = this;
    }

    // ===== LAYOUT HELPERS =====

    private int getPanelWidth()   { return Math.min(680, width  - 40); }
    private int getPanelHeight()  { return Math.min(480, height - 40); }
    private int getPanelX()       { return (width  - getPanelWidth())  / 2; }
    private int getPanelY()       { return (height - getPanelHeight()) / 2; }

    private int getLeftColX()      { return getPanelX() + 20; }
    private int getRightColX()     { return getPanelX() + getPanelWidth() / 2 + 10; }
    private int getColWidth()      { return getPanelWidth() / 2 - 30; }
    private int getContentStartY() { return getPanelY() + 80; }

    // Inventory panel inner bounds
    private int getInvPanelX()     { return getPanelX() + 20; }
    private int getInvPanelY()     { return getContentStartY(); }
    private int getInvPanelW()     { return getPanelWidth() - 40; }
    private int getInvPanelH()     { return getPanelHeight() - 130; }
    private int getVisibleRows()   { return getInvPanelH() / ROW_HEIGHT; }

    // ===== INIT =====

    @Override
    protected void init() {
        super.init();
        buildButtons();
    }

    private void buildButtons() {
        int px = getPanelX();
        int py = getPanelY();
        int pw = getPanelWidth();
        int ph = getPanelHeight();

        // Ã¢â€â‚¬Ã¢â€â‚¬ Tab buttons Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        int tabY       = py + 60;
        int tabW       = 100;
        int tabGap     = 4;
        int tabsStartX = px + (pw - (tabW * 2 + tabGap)) / 2;

        tabOverviewButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("Overview"),
                btn -> switchTab(0)
        ).pos(tabsStartX, tabY).size(tabW, 16).build();
        addRenderableWidget(tabOverviewButton);

        tabInventoryButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("Inventory"),
                btn -> switchTab(1)
        ).pos(tabsStartX + tabW + tabGap, tabY).size(tabW, 16).build();
        addRenderableWidget(tabInventoryButton);

        // Ã¢â€â‚¬Ã¢â€â‚¬ Rename button Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        int renameBtnX = px + pw - 90;
        int renameBtnY = py + 15;

        renameButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("Rename"),
                btn -> enterRenameMode()
        ).pos(renameBtnX, renameBtnY).size(70, 16).build();
        renameButton.visible = !renameModeActive;
        addRenderableWidget(renameButton);

        // Ã¢â€â‚¬Ã¢â€â‚¬ Name input + confirm Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        int inputWidth = 180;
        int inputX     = px + pw / 2 - inputWidth / 2;
        int inputY     = py + 13;

        townNameInput = new net.minecraft.client.gui.components.EditBox(
                font, inputX, inputY, inputWidth, 18,
                Component.literal("Town Name")
        );
        townNameInput.setMaxLength(32);
        townNameInput.setValue(townName);
        townNameInput.setCanLoseFocus(true);
        townNameInput.visible = renameModeActive;
        addRenderableWidget(townNameInput);

        confirmRenameButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("Confirm"),
                btn -> confirmRename()
        ).pos(inputX + inputWidth + 5, inputY).size(60, 18).build();
        confirmRenameButton.visible = renameModeActive;
        addRenderableWidget(confirmRenameButton);

        // Ã¢â€â‚¬Ã¢â€â‚¬ Claim button Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        int claimBtnWidth = 120;
        int claimBtnX     = px + (pw - claimBtnWidth) / 2;
        int claimBtnY     = py + ph - 40;

        claimButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("Claim Town"),
                btn -> claimTown()
        ).pos(claimBtnX, claimBtnY).size(claimBtnWidth, 20).build();
        claimButton.visible = !isClaimed;
        addRenderableWidget(claimButton);

        // Ã¢â€â‚¬Ã¢â€â‚¬ Choose Culture button (only when claimed by this player, no culture yet) Ã¢â€â‚¬Ã¢â€â‚¬
        int cultureBtnWidth = 140;
        int cultureBtnX     = px + (pw - cultureBtnWidth) / 2;
        int cultureBtnY     = claimBtnY - 28;

        chooseCultureButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("Choose Culture"),
                btn -> net.minecraft.client.Minecraft.getInstance().setScreen(
                        new CultureSelectionScreen(townHallPos, this))
        ).pos(cultureBtnX, cultureBtnY).size(cultureBtnWidth, 20).build();
        chooseCultureButton.visible = isClaimed && townCulture == Culture.NONE && activeTab == 0;
        addRenderableWidget(chooseCultureButton);

        updateTabButtonStates();
    }

    private void switchTab(int tab) {
        activeTab = tab;
        inventoryScrollOffset = 0;
        if (claimButton       != null) claimButton.visible        = !isClaimed && activeTab == 0;
        if (chooseCultureButton != null) chooseCultureButton.visible = isClaimed && townCulture == Culture.NONE && activeTab == 0;
        updateTabButtonStates();
    }
    private void updateTabButtonStates() {
        // Visually distinguish active tab Ã¢â‚¬â€ active tab is not active (looks pressed)
        if (tabOverviewButton  != null) tabOverviewButton.active  = (activeTab != 0);
        if (tabInventoryButton != null) tabInventoryButton.active = (activeTab != 1);
    }

    // ===== RENAME =====

    private void enterRenameMode() {
        renameModeActive = true;
        if (townNameInput != null)       { townNameInput.setValue(townName); townNameInput.visible = true; }
        if (confirmRenameButton != null)   confirmRenameButton.visible = true;
        if (renameButton != null)          renameButton.visible = false;
    }

    private void confirmRename() {
        if (townNameInput == null) return;
        String newName = townNameInput.getValue().trim();
        if (!newName.isEmpty() && !newName.equals(townName)) {
            net.minecraft.client.Minecraft.getInstance().getConnection().send(
                    new net.darkflameproduction.agotmod.network.UpdateTownNamePacket(townHallPos, newName)
            );
            townName = newName;
        }
        exitRenameMode();
    }

    private void exitRenameMode() {
        renameModeActive = false;
        if (townNameInput       != null) townNameInput.visible       = false;
        if (confirmRenameButton != null) confirmRenameButton.visible = false;
        if (renameButton        != null) renameButton.visible        = true;
    }

    private void claimTown() {
        net.minecraft.client.Minecraft.getInstance().getConnection().send(
                new ClaimTownHallPacket(townHallPos)
        );
    }

    // ===== STATIC UPDATE METHODS =====

    public static void updateTownHallData(BlockPos packetPos, int bedCount, int citizenCount, int radius,
                                          String townName, boolean isClaimed, String claimedByHouse,
                                          int availableJobCount, int assignedJobCount,
                                          int totalJobCount, int joblessCount,
                                          String cultureName) {
        if (currentInstance == null) return;
        if (!packetPos.equals(currentInstance.townHallPos)) return;
        currentInstance.bedCount          = bedCount;
        currentInstance.citizenCount      = citizenCount;
        currentInstance.currentRadius     = radius;
        currentInstance.townName          = townName;
        currentInstance.isClaimed         = isClaimed;
        currentInstance.claimedByHouse    = claimedByHouse;
        currentInstance.availableJobCount = availableJobCount;
        currentInstance.assignedJobCount  = assignedJobCount;
        currentInstance.totalJobCount     = totalJobCount;
        currentInstance.joblessCount      = joblessCount;
        try {
            currentInstance.townCulture = Culture.valueOf(cultureName);
        } catch (IllegalArgumentException e) {
            currentInstance.townCulture = Culture.NONE;
        }
        if (currentInstance.claimButton != null)
            currentInstance.claimButton.visible = !isClaimed && currentInstance.activeTab == 0;
        if (currentInstance.chooseCultureButton != null)
            currentInstance.chooseCultureButton.visible =
                    isClaimed && currentInstance.townCulture == Culture.NONE && currentInstance.activeTab == 0;
        if (!currentInstance.renameModeActive && currentInstance.townNameInput != null)
            currentInstance.townNameInput.setValue(townName);
    }

    public static void updateFinances(BlockPos packetPos, long townBalance, long townIncome) {
        if (currentInstance == null) return;
        if (!packetPos.equals(currentInstance.townHallPos)) return;
        currentInstance.townBalance = townBalance;
        currentInstance.townIncome  = townIncome;
    }

    public static void updateInventory(BlockPos packetPos, Map<String, Long> inventory) {
        if (currentInstance == null) return;
        if (!packetPos.equals(currentInstance.townHallPos)) return;
        currentInstance.townInventory = inventory.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(java.util.stream.Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public static TownHallScreen getCurrentInstance() { return currentInstance; }

    // ===== SCROLL =====

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        if (activeTab == 1) {
            int maxScroll = Math.max(0, townInventory.size() - getVisibleRows());
            inventoryScrollOffset = (int) Math.max(0, Math.min(maxScroll,
                    inventoryScrollOffset - scrollY));
            return true;
        }
        return super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
    }

    // ===== INPUT =====

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (renameModeActive && keyCode == 257) { confirmRename(); return true; }
        if (renameModeActive && keyCode == 256) { exitRenameMode(); return true; }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    // ===== RENDER =====

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        g.fill(0, 0, width, height, 0x60000000);

        int px = getPanelX();
        int py = getPanelY();
        int pw = getPanelWidth();
        int ph = getPanelHeight();

        renderPaperPanel(g, px, py, pw, ph);
        drawHeader(g, px, py, pw);
        drawDivider(g, px, py, pw, ph);

        if (activeTab == 0) {
            drawLeftColumn(g);
            drawRightColumn(g);
            drawFooter(g, px, py, pw, ph);
        } else {
            drawInventoryTab(g, px, py, pw, ph);
        }

        super.render(g, mouseX, mouseY, partialTick);
    }

    private void drawHeader(GuiGraphics g, int px, int py, int pw) {
        if (!renameModeActive) {
            float titleScale = 1.3f;
            g.pose().pushPose();
            g.pose().scale(titleScale, titleScale, 1f);
            int titleWidth = (int)(font.width(townName) * titleScale);
            int titleX = (int)((px + (pw - titleWidth) / 2) / titleScale);
            int titleY = (int)((py + 16) / titleScale);
            g.drawString(font, townName, titleX, titleY, COLOR_HEADER, false);
            g.pose().popPose();

            String category = "Ã¢â‚¬â€ " + getTownSizeCategory() + " Ã¢â‚¬â€";
            int catWidth = font.width(category);
            g.drawString(font, category, px + (pw - catWidth) / 2, py + 38, COLOR_GOLD, false);
        }
    }

    private void drawDivider(GuiGraphics g, int px, int py, int pw, int ph) {
        // Below header
        g.fill(px + pw / 8, py + 58, px + pw - pw / 8, py + 59, 0x80000000);
        // Below tabs
        g.fill(px + pw / 8, py + 78, px + pw - pw / 8, py + 79, 0x40000000);
    }

    // ===== OVERVIEW TAB =====

    private void drawLeftColumn(GuiGraphics g) {
        int x = getLeftColX();
        int y = getContentStartY();
        int w = getColWidth();

        // Ã¢â€â‚¬Ã¢â€â‚¬ Town Info Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        drawSectionHeader(g, "Town Info", x, y, w, COLOR_HEADER);
        y += 20;

        if (isClaimed) {
            drawLabelValue(g, "Owned by", claimedByHouse, x, y, COLOR_BLUE);
        } else {
            drawLabelValue(g, "Status", "Unclaimed", x, y, COLOR_RED);
        }
        y += 16;
        drawLabelValue(g, "Radius",   currentRadius + " blocks",    x, y, COLOR_GREEN); y += 16;
        drawLabelValue(g, "Beds",     String.valueOf(bedCount),      x, y, COLOR_GREEN); y += 16;
        drawLabelValue(g, "Citizens", String.valueOf(citizenCount),  x, y, COLOR_GREEN); y += 20;

        // Ã¢â€â‚¬Ã¢â€â‚¬ Culture Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        drawSectionHeader(g, "Culture", x, y, w, COLOR_HEADER);
        y += 20;

        if (townCulture != Culture.NONE) {
            drawLabelValue(g, "Culture", townCulture.displayName,        x, y, COLOR_PURPLE); y += 16;
            drawLabelValue(g, "Group",   townCulture.group.displayName,  x, y, COLOR_BLUE);   y += 20;
        } else if (isClaimed) {
            drawLabelValue(g, "Culture", "Not yet chosen", x, y, COLOR_ORANGE); y += 20;
        } else {
            drawLabelValue(g, "Culture", "Claim town first", x, y, COLOR_GRAY); y += 20;
        }

        // Ã¢â€â‚¬Ã¢â€â‚¬ Finances Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        drawSectionHeader(g, "Finances", x, y, w, COLOR_HEADER);
        y += 20;

        drawLabelValue(g, "Town Balance", formatCurrency(townBalance), x, y, COLOR_GOLD); y += 16;
        drawLabelValue(g, "Treasury",     formatCurrency(townIncome),  x, y, COLOR_GOLD);
    }

    private void drawRightColumn(GuiGraphics g) {
        int x = getRightColX();
        int y = getContentStartY();
        int w = getColWidth();

        // Ã¢â€â‚¬Ã¢â€â‚¬ Employment Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        drawSectionHeader(g, "Employment", x, y, w, COLOR_PURPLE);
        y += 20;

        drawLabelValue(g, "Total Jobs",  String.valueOf(totalJobCount),    x, y, TEXT_COLOR); y += 16;
        drawLabelValue(g, "Occupied",    String.valueOf(assignedJobCount),  x, y, assignedJobCount  > 0 ? COLOR_BLUE  : COLOR_GRAY); y += 16;
        drawLabelValue(g, "Available",   String.valueOf(availableJobCount), x, y, availableJobCount > 0 ? COLOR_GREEN : COLOR_GRAY); y += 16;
        drawLabelValue(g, "Unemployed",  String.valueOf(joblessCount),      x, y, joblessCount      > 0 ? COLOR_RED   : COLOR_GREEN); y += 16;

        if (totalJobCount > 0) {
            int rate = (assignedJobCount * 100) / totalJobCount;
            drawLabelValue(g, "Utilization", rate + "%", x, y,
                    rate >= 80 ? COLOR_GREEN : rate >= 50 ? COLOR_ORANGE : COLOR_RED);
            y += 16;
        }

        y += 12;
        String status   = getJobMarketStatus();
        int statusColor = getJobMarketStatusColor();
        int badgeW      = font.width(status) + 12;
        int badgeX      = x + (w - badgeW) / 2;
        g.fill(badgeX, y, badgeX + badgeW, y + font.lineHeight + 6, statusColor & 0x40FFFFFF | 0x30000000);
        g.fill(badgeX, y, badgeX + badgeW, y + 1, statusColor);
        g.fill(badgeX, y + font.lineHeight + 5, badgeX + badgeW, y + font.lineHeight + 6, statusColor);
        g.drawString(font, status, badgeX + 6, y + 3, statusColor, false);
    }

    private void drawFooter(GuiGraphics g, int px, int py, int pw, int ph) {
        g.fill(px + pw / 8, py + ph - 50, px + pw - pw / 8, py + ph - 49, 0x80000000);
        String hint = isClaimed
                ? "Claimed by " + claimedByHouse
                : "Right-click to claim this town as your own";
        int hintColor = isClaimed ? COLOR_BLUE : COLOR_GRAY;
        int hintW = font.width(hint);
        g.drawString(font, hint, px + (pw - hintW) / 2, py + ph - 38, hintColor, false);
    }

    // ===== INVENTORY TAB =====

    private void drawInventoryTab(GuiGraphics g, int px, int py, int pw, int ph) {
        int ix = getInvPanelX();
        int iy = getInvPanelY();
        int iw = getInvPanelW();
        int ih = getInvPanelH();

        // Section header
        drawSectionHeader(g, "Town Stores", ix, iy, iw, COLOR_HEADER);
        int headerH = font.lineHeight + 8;

        if (townInventory.isEmpty()) {
            String empty = "The town stores are empty.";
            int ew = font.width(empty);
            g.drawString(font, empty, ix + (iw - ew) / 2, iy + headerH + 20, COLOR_GRAY, false);
            return;
        }

        // Column headers
        int listY = iy + headerH + 4;
        g.drawString(font, "Item",     ix + 4,        listY, COLOR_GRAY, false);
        g.drawString(font, "Quantity", ix + iw - 70,  listY, COLOR_GRAY, false);
        listY += font.lineHeight + 4;

        // Light header underline
        g.fill(ix, listY - 2, ix + iw, listY - 1, 0x40000000);

        // Clipping region Ã¢â‚¬â€ only draw rows within bounds
        int clipTop    = listY;
        int clipBottom = iy + ih;
        int visibleRows = getVisibleRows();

        List<Map.Entry<String, Long>> entries = new ArrayList<>(townInventory.entrySet());
        int totalRows  = entries.size();
        int startIndex = inventoryScrollOffset;
        int endIndex   = Math.min(startIndex + visibleRows, totalRows);

        for (int i = startIndex; i < endIndex; i++) {
            Map.Entry<String, Long> entry = entries.get(i);
            int rowY = listY + (i - startIndex) * ROW_HEIGHT;

            if (rowY + ROW_HEIGHT < clipTop || rowY > clipBottom) continue;

            // Alternating row background
            if ((i % 2) == 0) {
                g.fill(ix, rowY, ix + iw, rowY + ROW_HEIGHT, 0x18000000);
            }

            // Item name Ã¢â‚¬â€ strip namespace, replace underscores, capitalise
            String displayName = formatItemName(entry.getKey());
            g.drawString(font, displayName, ix + 4, rowY + 2, TEXT_COLOR, false);

            // Quantity right-aligned
            String qty    = formatQuantity(entry.getValue());
            int qtyW      = font.width(qty);
            g.drawString(font, qty, ix + iw - qtyW - 4, rowY + 2, COLOR_GOLD, false);
        }

        // Scroll indicator on the right edge
        if (totalRows > visibleRows) {
            int scrollTrackH = ih - headerH - font.lineHeight - 8;
            int scrollTrackY = iy + headerH + font.lineHeight + 8;
            int scrollTrackX = ix + iw + 4;

            // Track
            g.fill(scrollTrackX, scrollTrackY, scrollTrackX + 3, scrollTrackY + scrollTrackH, 0x40000000);

            // Thumb
            float thumbRatio   = (float) visibleRows / totalRows;
            int   thumbH       = Math.max(10, (int)(scrollTrackH * thumbRatio));
            float scrollRatio  = (float) inventoryScrollOffset / Math.max(1, totalRows - visibleRows);
            int   thumbY       = scrollTrackY + (int)((scrollTrackH - thumbH) * scrollRatio);
            g.fill(scrollTrackX, thumbY, scrollTrackX + 3, thumbY + thumbH, 0xA0000000);
        }

        // Item count summary at bottom
        String summary = totalRows + " item type" + (totalRows != 1 ? "s" : "") + " stored";
        int sumW = font.width(summary);
        g.drawString(font, summary, px + (pw - sumW) / 2, py + ph - 38, COLOR_GRAY, false);
    }

    // ===== FORMAT HELPERS =====

    private String formatItemName(String itemKey) {
        // Strip namespace (e.g. "minecraft:raw_iron" Ã¢â€ â€™ "raw_iron", "agotmod:amber" Ã¢â€ â€™ "amber")
        String name = itemKey.contains(":") ? itemKey.substring(itemKey.indexOf(':') + 1) : itemKey;
        // Replace underscores with spaces and title-case each word
        String[] words = name.split("_");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                if (sb.length() > 0) sb.append(' ');
                sb.append(Character.toUpperCase(word.charAt(0)));
                sb.append(word.substring(1));
            }
        }
        return sb.toString();
    }

    private String formatQuantity(long amount) {
        if (amount >= 1_000_000) return String.format("%.1fM", amount / 1_000_000.0);
        if (amount >= 1_000)     return String.format("%.1fK", amount / 1_000.0);
        return String.valueOf(amount);
    }

    private void drawSectionHeader(GuiGraphics g, String text, int x, int y, int colWidth, int color) {
        g.fill(x, y + font.lineHeight + 2, x + colWidth, y + font.lineHeight + 3, color & 0x60FFFFFF | 0x40000000);
        g.drawString(font, text, x, y, color, false);
    }

    private void drawLabelValue(GuiGraphics g, String label, String value, int x, int y, int valueColor) {
        g.drawString(font, label + ":", x, y, COLOR_GRAY, false);
        int labelWidth = font.width(label + ":  ");
        g.drawString(font, value, x + labelWidth, y, valueColor, false);
    }

    private String formatCurrency(long amount) {
        if (amount >= 1_000_000) return String.format("%.1fM coins", amount / 1_000_000.0);
        if (amount >= 1_000)     return String.format("%.1fK coins", amount / 1_000.0);
        return amount + " coins";
    }

    // ===== TOWN CLASSIFICATION =====

    private String getTownSizeCategory() {
        if      (citizenCount < 5)     return "Small Hovel";
        else if (citizenCount < 10)    return "Farmstead";
        else if (citizenCount < 30)    return "Small Settlement";
        else if (citizenCount < 50)    return "Small Village";
        else if (citizenCount < 100)   return "Village";
        else if (citizenCount < 150)   return "Large Village";
        else if (citizenCount < 200)   return "Small Town";
        else if (citizenCount < 400)   return "Town";
        else if (citizenCount < 1000)  return "Large Town";
        else if (citizenCount < 1500)  return "Small City";
        else if (citizenCount < 5000)  return "City";
        else if (citizenCount < 10000) return "Relevant City";
        else if (citizenCount < 15000) return "Major City";
        else                           return "Trade Capital";
    }

    private String getJobMarketStatus() {
        if (totalJobCount    == 0)                            return "No Jobs";
        if (joblessCount     == 0 && availableJobCount == 0) return "Full Employment";
        if (joblessCount     >  0 && availableJobCount >  0) return "Jobs Available";
        if (joblessCount     >  0 && availableJobCount == 0) return "Job Shortage";
        if (joblessCount     == 0 && availableJobCount >  0) return "Labor Shortage";
        return "Stable";
    }

    private int getJobMarketStatusColor() {
        return switch (getJobMarketStatus()) {
            case "Full Employment", "Stable" -> COLOR_GREEN;
            case "Jobs Available"            -> COLOR_BLUE;
            case "Job Shortage"              -> COLOR_ORANGE;
            case "Labor Shortage"            -> COLOR_RED;
            default                          -> COLOR_GRAY;
        };
    }

    // ===== PAPER PANEL RENDERING =====

    private void renderPaperPanel(GuiGraphics g, int x, int y, int width, int height) {
        g.flush();
        int bpw    = BORDER_PILLAR_WIDTH * 2;
        int bh     = BORDER_HEIGHT * 2;
        int cs     = CORNER_SIZE * 2;
        int innerX = x + bpw;
        int innerY = y + bh;
        int innerW = width  - bpw * 2;
        int innerH = height - bh  * 2;

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1f, 1f, 1f, 0.92f);

        int tileSize = 256;
        for (int tx = 0; tx < innerW; tx += tileSize) {
            for (int ty = 0; ty < innerH; ty += tileSize) {
                int tw = Math.min(tileSize, innerW - tx);
                int th = Math.min(tileSize, innerH - ty);
                g.blit(
                        PAPER_TEXTURE, innerX + tx, innerY + ty, 0, 0, tw, th, tileSize, tileSize);
            }
        }

        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        drawBorders(g, x, y, width, height, bpw, bh, cs);
    }

    private void drawBorders(GuiGraphics g, int x, int y, int w, int h,
                             int bpw, int bh, int cs) {
        drawRotatableBorder(g, PAPER_SIDE_TEXTURE,    x,           y + cs,     bpw, h - cs * 2, 0);
        drawRotatableBorder(g, PAPER_SIDE_TEXTURE,    x + w - bpw, y + cs,     bpw, h - cs * 2, 180);
        drawRotatableBorder(g, PAPER_SIDETOP_TEXTURE, x + cs,      y,          w - cs * 2, bh, 0);
        RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1f);
        drawRotatableBorder(g, PAPER_SIDETOP_TEXTURE, x + cs,      y + h - bh, w - cs * 2, bh, 180);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x,           y,           cs, cs, 0);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x + w - cs,  y,           cs, cs, 90);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x + w - cs,  y + h - cs,  cs, cs, 180);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x,           y + h - cs,  cs, cs, 270);
    }

    private void drawRotatableBorder(GuiGraphics g, ResourceLocation tex,
                                     int x, int y, int w, int h, float rot) {
        g.pose().pushPose();
        g.pose().translate(x + w / 2f, y + h / 2f, 0);
        g.pose().mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rot));
        g.pose().translate(-w / 2f, -h / 2f, 0);
        int ts = 32;
        for (int tx = 0; tx < w; tx += ts) {
            for (int ty = 0; ty < h; ty += ts) {
                int tw = Math.min(ts, w - tx);
                int th = Math.min(ts, h - ty);
                if (tw <= 0 || th <= 0) continue;
                g.blit( tex, tx, ty, 0, 0, tw, th, ts, ts);
            }
        }
        g.pose().popPose();
    }

    private void drawRotatableCorner(GuiGraphics g, ResourceLocation tex,
                                     int x, int y, int w, int h, float rot) {
        g.pose().pushPose();
        if (rot != 0) {
            g.pose().translate(x + w / 2f, y + h / 2f, 0);
            g.pose().mulPose(com.mojang.math.Axis.ZP.rotationDegrees(rot));
            g.pose().translate(-w / 2f, -h / 2f, 0);
            g.blit( tex, 0, 0, 0, 0, w, h, 32, 32);
        } else {
            g.blit( tex, x, y, 0, 0, w, h, 32, 32);
        }
        g.pose().popPose();
    }

    // ===== OVERRIDES =====

    @Override
    public void onClose() {
        super.onClose();
        if (currentInstance == this) currentInstance = null;
    }

    @Override
    public boolean isPauseScreen() { return false; }

    @Override
    public void renderBackground(GuiGraphics g, int mouseX, int mouseY, float partialTick) {}
}
