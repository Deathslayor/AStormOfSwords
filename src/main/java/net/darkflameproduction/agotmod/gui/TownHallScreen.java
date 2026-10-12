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

    private static final int ROW_HEIGHT = 18;
    private static final int ICON_SIZE  = 16;

    // Inventory list is drawn scaled down to fit more items
    private static final float INV_SCALE   = 2f / 3f;

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
    private net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings earnings =
            new net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings(0, 0, 0, Map.of(), Map.of());

    // Job data
    private int availableJobCount = 0;
    private int assignedJobCount  = 0;
    private int totalJobCount     = 0;
    private int joblessCount      = 0;

    // Inventory data
    private Map<String, Long> townInventory = new LinkedHashMap<>();

    // Citizens (name + job) reported by the town hall
    private List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CitizenEntry> citizens = new ArrayList<>();

    // What the town's blacksmith still has to make
    private List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CraftingNeed> craftingNeeds = new ArrayList<>();

    // Tab state
    private static final int TAB_OVERVIEW   = 0;
    private static final int TAB_CULTURE    = 1;
    private static final int TAB_FINANCES   = 2;
    private static final int TAB_EMPLOYMENT = 3;
    private static final int TAB_INVENTORY  = 4;
    private static final int TAB_JOBS       = 5;
    private static final int TAB_CITIZENS   = 6;
    private static final int TAB_CRAFTING   = 7;
    private static final String[] TAB_NAMES = { "Overview", "Culture", "Finances", "Employment", "Inventory", "Jobs", "Citizens", "Crafting" };
    private int activeTab = TAB_OVERVIEW;

    // Scroll state for the list tabs (inventory, jobs, citizens)
    private int listScrollOffset = 0;

    // Rename UI state
    private boolean renameModeActive = false;
    private net.minecraft.client.gui.components.EditBox  townNameInput       = null;
    private net.minecraft.client.gui.components.Button  confirmRenameButton = null;
    private net.minecraft.client.gui.components.Button  renameButton        = null;
    private net.minecraft.client.gui.components.Button  claimButton         = null;
    private final List<net.minecraft.client.gui.components.Button> tabButtons = new ArrayList<>();

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

    private int getContentX()      { return getPanelX() + 20; }
    private int getContentWidth()  { return getPanelWidth() - 40; }
    private int getContentStartY() { return getPanelY() + 80; }

    // Inventory panel inner bounds
    private int getInvPanelX()     { return getPanelX() + 20; }
    private int getInvPanelY()     { return getContentStartY(); }
    private int getInvPanelW()     { return getPanelWidth() - 40; }
    private int getInvPanelH()     { return getPanelHeight() - 100; }
    private int getVisibleRows()   { return Math.max(1, (int) (getInvPanelH() / (ROW_HEIGHT * INV_SCALE))); }

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
        int tabCount   = TAB_NAMES.length;
        int tabY       = py + 60;
        int tabGap     = 4;
        int tabW       = Math.min(100, (getContentWidth() - tabGap * (tabCount - 1)) / tabCount);
        int tabsStartX = px + (pw - (tabW * tabCount + tabGap * (tabCount - 1))) / 2;

        tabButtons.clear();
        for (int i = 0; i < tabCount; i++) {
            final int tab = i;
            net.minecraft.client.gui.components.Button tabButton = net.minecraft.client.gui.components.Button.builder(
                    Component.literal(TAB_NAMES[i]),
                    btn -> switchTab(tab)
            ).pos(tabsStartX + i * (tabW + tabGap), tabY).size(tabW, 16).build();
            tabButtons.add(tabButton);
            addRenderableWidget(tabButton);
        }

        // Ã¢â€â‚¬Ã¢â€â‚¬ Rename button Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬
        int renameBtnX = px + pw - 90;
        int renameBtnY = py + 15;

        renameButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("Rename"),
                btn -> enterRenameMode()
        ).pos(renameBtnX, renameBtnY).size(70, 16).build();
        renameButton.visible = !renameModeActive;
        addRenderableWidget(renameButton);

        // Donate: give items or coins to the town (the trade menu on your own inventory, for nothing)
        addRenderableWidget(net.minecraft.client.gui.components.Button.builder(
                Component.literal("Donate"),
                btn -> TradingInventoryScreen.openDonate(townHallPos, townName)
        ).pos(px + 20, renameBtnY).size(70, 16).build());

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
        addRenderableWidget(chooseCultureButton);

        refreshButtonStates();
    }

    private void switchTab(int tab) {
        activeTab = tab;
        listScrollOffset = 0;
        refreshButtonStates();
    }

    private void refreshButtonStates() {
        if (claimButton         != null) claimButton.visible         = !isClaimed && activeTab == TAB_OVERVIEW;
        if (chooseCultureButton != null) chooseCultureButton.visible = isClaimed && townCulture == Culture.NONE && activeTab == TAB_CULTURE;
        // Visually distinguish active tab — active tab is not active (looks pressed)
        for (int i = 0; i < tabButtons.size(); i++) {
            tabButtons.get(i).active = (activeTab != i);
        }
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
        currentInstance.townCulture = Culture.fromName(cultureName);
        currentInstance.refreshButtonStates();
        if (!currentInstance.renameModeActive && currentInstance.townNameInput != null)
            currentInstance.townNameInput.setValue(townName);
    }

    public static void updateFinances(BlockPos packetPos, long townBalance, long townIncome,
                                      net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings earnings) {
        if (currentInstance == null) return;
        if (!packetPos.equals(currentInstance.townHallPos)) return;
        currentInstance.townBalance = townBalance;
        currentInstance.townIncome  = townIncome;
        currentInstance.earnings    = earnings;
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

    public static void updateCraftingNeeds(BlockPos packetPos,
                                           List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CraftingNeed> needs) {
        if (currentInstance == null) return;
        if (!packetPos.equals(currentInstance.townHallPos)) return;
        currentInstance.craftingNeeds = new ArrayList<>(needs);
    }

    public static void updateCitizens(BlockPos packetPos,
                                      List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CitizenEntry> citizens) {
        if (currentInstance == null) return;
        if (!packetPos.equals(currentInstance.townHallPos)) return;
        currentInstance.citizens = new ArrayList<>(citizens);
    }

    public static TownHallScreen getCurrentInstance() { return currentInstance; }

    // ===== SCROLL =====

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        int listSize = switch (activeTab) {
            case TAB_INVENTORY -> townInventory.size();
            case TAB_JOBS      -> getJobCounts().size();
            case TAB_CITIZENS  -> citizens.size();
            case TAB_CRAFTING  -> craftingNeeds.size();
            default            -> -1;
        };
        if (listSize >= 0) {
            int maxScroll = Math.max(0, listSize - getVisibleRows());
            listScrollOffset = (int) Math.max(0, Math.min(maxScroll,
                    listScrollOffset - scrollY));
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

        switch (activeTab) {
            case TAB_OVERVIEW -> {
                drawOverviewTab(g);
                drawFooter(g, px, py, pw, ph);
            }
            case TAB_CULTURE    -> drawCultureTab(g);
            case TAB_FINANCES   -> drawFinancesTab(g);
            case TAB_EMPLOYMENT -> drawEmploymentTab(g);
            case TAB_INVENTORY  -> drawInventoryTab(g);
            case TAB_JOBS       -> drawJobsTab(g);
            case TAB_CITIZENS   -> drawCitizensTab(g);
            case TAB_CRAFTING   -> drawCraftingTab(g);
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

            String category = "— " + getTownSizeCategory() + " —";
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

    private void drawOverviewTab(GuiGraphics g) {
        int x = getContentX();
        int y = getContentStartY();
        int w = getContentWidth();

        drawSectionHeader(g, "Town Info", x, y, w, COLOR_HEADER);
        y += 20;

        if (isClaimed) {
            drawLabelValue(g, "Owned by", claimedByHouse, x, y, COLOR_BLUE);
        } else {
            drawLabelValue(g, "Status", "Unclaimed", x, y, COLOR_RED);
        }
        y += 16;
        drawLabelValue(g, "Size",     getTownSizeCategory(),        x, y, COLOR_GOLD);  y += 16;
        drawLabelValue(g, "Radius",   currentRadius + " blocks",    x, y, COLOR_GREEN); y += 16;
        drawLabelValue(g, "Beds",     String.valueOf(bedCount),      x, y, COLOR_GREEN); y += 16;
        drawLabelValue(g, "Citizens", String.valueOf(citizenCount),  x, y, COLOR_GREEN);
    }

    // ===== CULTURE TAB =====

    private void drawCultureTab(GuiGraphics g) {
        int x = getContentX();
        int y = getContentStartY();
        int w = getContentWidth();

        drawSectionHeader(g, "Culture", x, y, w, COLOR_HEADER);
        y += 20;

        if (townCulture != Culture.NONE) {
            drawLabelValue(g, "Culture", townCulture.displayName,        x, y, COLOR_PURPLE); y += 16;
            drawLabelValue(g, "Group",   townCulture.group.displayName,  x, y, COLOR_BLUE);
        } else if (isClaimed) {
            drawLabelValue(g, "Culture", "Not yet chosen", x, y, COLOR_ORANGE);
        } else {
            drawLabelValue(g, "Culture", "Claim town first", x, y, COLOR_GRAY);
        }
    }

    // ===== FINANCES TAB =====

    /**
     * The town's finances: its balance, treasury, trader budget and debt at the top, and below them a cash
     * flow table - every kind of money in and out (CashFlow), today and yesterday, with totals and the net.
     */
    private void drawFinancesTab(GuiGraphics g) {
        int x = getContentX();
        int y = getContentStartY();
        int w = getContentWidth();

        drawSectionHeader(g, "Finances", x, y, w, COLOR_HEADER);
        y += 20;

        // Summary, two columns
        int right = x + w / 2;
        drawLabelValue(g, "Town Balance", formatCurrency(townBalance), x, y, townBalance < 0 ? COLOR_RED : COLOR_GOLD);
        boolean hasTrader = citizens.stream().anyMatch(c ->
                net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem.JOB_TRADER.equals(c.jobType()));
        if (hasTrader) {
            drawLabelValue(g, "Trader Budget", formatCurrency(earnings.traderBudget()) + " / day", right, y,
                    earnings.traderBudget() > 0 ? COLOR_GOLD : COLOR_RED);
        } else {
            drawLabelValue(g, "Trader Budget", "No trader", right, y, COLOR_GRAY);
        }
        y += 14;
        drawLabelValue(g, "Treasury", formatCurrency(townIncome), x, y, COLOR_GOLD);
        // A town in debt charges double and pays half (TownHallBlockEntity.buyPriceFactor / sellPriceFactor)
        if (townBalance < 0) drawLabelValue(g, "In Debt", "prices x2, sell value x0.5", right, y, COLOR_RED);
        else drawLabelValue(g, "Status", "Solvent", right, y, COLOR_GREEN);
        y += 20;

        drawCashFlowTable(g, x, y, w, getInvPanelY() + getInvPanelH() - y);
    }

    private static final int TABLE_ROW = 12;
    private static final int TABLE_LINE = 0x40000000;
    /** The cash flow tables are drawn small - at most like the other lists (INV_SCALE), smaller if need be to fit. */
    private static final float TABLE_MAX_SCALE = INV_SCALE;
    /** Room between the income and expenses tables, in table units. */
    private static final int TABLE_GAP = 10;

    // Kinds of row in the cash flow tables
    private static final int ROW_FLOW = 1, ROW_JOB = 2, ROW_TOTAL = 3;

    /** A row of a cash flow table. */
    private record FinanceRow(int kind, String label, long today, long yesterday) {}

    /**
     * The cash flow, spreadsheet style: income on the left and expenses on the right - each kind of money in
     * or out, with what each kind of citizen paid or was paid ("Farmer ×5": the town's 5 farmers) and the
     * total - and the net below both. Scaled down until it all fits: no scrolling.
     */
    private void drawCashFlowTable(GuiGraphics g, int x, int y, int w, int maxH) {
        long[] incomeTotal = new long[2];
        long[] expenseTotal = new long[2];
        List<FinanceRow> income = financeRows(true, incomeTotal);
        List<FinanceRow> expenses = financeRows(false, expenseTotal);

        // Header, the longer table's rows, a gap and the net row
        int shown = Math.max(income.size(), expenses.size());
        int neededH = (shown + 1) * TABLE_ROW + 6 + TABLE_ROW + 3;
        float scale = Math.min(TABLE_MAX_SCALE, maxH / (float) neededH);
        listScrollOffset = 0;

        int tw = Math.round(w / scale);
        int tableW = (tw - TABLE_GAP) / 2;

        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(scale, scale, 1f);

        drawFinanceTable(g, 0, tableW, "Income", income, shown, COLOR_GREEN, false);
        drawFinanceTable(g, tableW + TABLE_GAP, tableW, "Expenses", expenses, shown, COLOR_RED, true);

        // Net, under both tables
        int netTop = (shown + 1) * TABLE_ROW + 6;
        long netToday = incomeTotal[0] - expenseTotal[0];
        long netYesterday = incomeTotal[1] - expenseTotal[1];
        g.fill(0, netTop, tw, netTop + TABLE_ROW + 2, 0x50000000);
        int textY = netTop + (TABLE_ROW + 2 - font.lineHeight) / 2 + 1;
        g.drawString(font, "Net", 4, textY, TEXT_COLOR, false);
        String today = "Today: " + signed(netToday);
        String yesterday = "Yesterday: " + signed(netYesterday);
        g.drawString(font, today, tw / 2 - font.width(today) - 10, textY, netToday < 0 ? COLOR_RED : COLOR_GREEN, false);
        g.drawString(font, yesterday, tw - font.width(yesterday) - 4, textY, netYesterday < 0 ? COLOR_RED : COLOR_GREEN, false);
        g.fill(0, netTop, tw, netTop + 1, 0x80000000);
        g.fill(0, netTop + TABLE_ROW + 2, tw, netTop + TABLE_ROW + 3, 0x80000000);
        g.fill(0, netTop, 1, netTop + TABLE_ROW + 3, 0x80000000);
        g.fill(tw - 1, netTop, tw, netTop + TABLE_ROW + 3, 0x80000000);

        g.pose().popPose();
    }

    /** One table (income or expenses) at {@code left}: its header, its rows ({@code shown} high) and its grid. */
    private void drawFinanceTable(GuiGraphics g, int left, int w, String title, List<FinanceRow> rows, int shown,
                                  int color, boolean expense) {
        int catW = w / 2;
        int colW = (w - catW) / 2;
        int todayX = left + catW + colW - 4;
        int yesterdayX = left + catW + 2 * colW - 4;

        g.fill(left, 0, left + w, TABLE_ROW, COLOR_HEADER);
        drawCellText(g, 0, title, left + 4, false, 0xFFFFFFFF);
        drawCellText(g, 0, "Today", todayX, true, 0xFFFFFFFF);
        drawCellText(g, 0, "Yesterday", yesterdayX, true, 0xFFFFFFFF);

        for (int i = 0; i < shown; i++) {
            int row = i + 1;
            int index = i;
            if (index >= rows.size()) continue;
            FinanceRow r = rows.get(index);
            int top = row * TABLE_ROW;
            boolean job = r.kind() == ROW_JOB;
            if (r.kind() == ROW_TOTAL) g.fill(left, top, left + w, top + TABLE_ROW, expense ? 0x40D32F2F : 0x402E7D32);
            else if (r.kind() == ROW_FLOW) g.fill(left, top, left + w, top + TABLE_ROW, 0x14000000);
            int valueColor = job ? lighter(color) : color;
            drawCellText(g, row, r.label(), left + (job ? 14 : 4), false, job ? COLOR_GRAY : TEXT_COLOR);
            drawCellText(g, row, amount(r.today(), expense), todayX, true, r.today() == 0 ? COLOR_GRAY : valueColor);
            drawCellText(g, row, amount(r.yesterday(), expense), yesterdayX, true, r.yesterday() == 0 ? COLOR_GRAY : valueColor);
        }

        // Grid: a line under every row, column dividers and the border
        int h = (shown + 1) * TABLE_ROW;
        for (int row = 1; row <= shown; row++) g.fill(left, row * TABLE_ROW, left + w, row * TABLE_ROW + 1, TABLE_LINE);
        g.fill(left + catW, 0, left + catW + 1, h, TABLE_LINE);
        g.fill(left + catW + colW, 0, left + catW + colW + 1, h, TABLE_LINE);
        g.fill(left, 0, left + w, 1, 0x80000000);
        g.fill(left, h, left + w, h + 1, 0x80000000);
        g.fill(left, 0, left + 1, h, 0x80000000);
        g.fill(left + w - 1, 0, left + w, h + 1, 0x80000000);
    }

    /**
     * The rows of the income ({@code income}) or expenses table: each kind of flow, broken down by job, and the
     * total. Its {today, yesterday} total goes into {@code total}.
     */
    private List<FinanceRow> financeRows(boolean income, long[] total) {
        // "FLOW|job" -> {today, yesterday}
        Map<String, long[]> amounts = new HashMap<>();
        earnings.flowsToday().forEach((key, value) -> amounts.computeIfAbsent(key, k -> new long[2])[0] += value);
        earnings.flowsYesterday().forEach((key, value) -> amounts.computeIfAbsent(key, k -> new long[2])[1] += value);
        Map<String, Integer> jobCounts = new HashMap<>();
        for (var citizen : citizens) jobCounts.merge(citizen.jobType(), 1, Integer::sum);

        List<FinanceRow> rows = new ArrayList<>();
        for (var flow : net.darkflameproduction.agotmod.block.custom.CashFlow.values()) {
            if (flow.income != income) continue;
            long[] sum = new long[2];
            Map<String, long[]> byJob = new HashMap<>();
            for (Map.Entry<String, long[]> entry : amounts.entrySet()) {
                String key = entry.getKey();
                int bar = key.indexOf('|');
                if (!(bar < 0 ? key : key.substring(0, bar)).equals(flow.name())) continue;
                sum[0] += entry.getValue()[0];
                sum[1] += entry.getValue()[1];
                String job = bar < 0 ? "" : key.substring(bar + 1);
                long[] jobSum = byJob.computeIfAbsent(job, k -> new long[2]);
                jobSum[0] += entry.getValue()[0];
                jobSum[1] += entry.getValue()[1];
            }
            rows.add(new FinanceRow(ROW_FLOW, flow.label, sum[0], sum[1]));
            // Who it came from or went to - only when a citizen's job is known for some of it
            if (byJob.keySet().stream().anyMatch(job -> !job.isEmpty())) {
                byJob.entrySet().stream()
                        .sorted((a, b) -> Long.compare(b.getValue()[0] + b.getValue()[1], a.getValue()[0] + a.getValue()[1]))
                        .forEach(entry -> {
                            String job = entry.getKey();
                            String label = job.isEmpty() ? "Other"
                                    : formatJobName(job) + " ×" + jobCounts.getOrDefault(job, 0);
                            rows.add(new FinanceRow(ROW_JOB, label, entry.getValue()[0], entry.getValue()[1]));
                        });
            }
            total[0] += sum[0];
            total[1] += sum[1];
        }
        rows.add(new FinanceRow(ROW_TOTAL, "Total " + (income ? "income" : "expenses"), total[0], total[1]));
        return rows;
    }

    /** A paler shade of a colour, for the job rows under a flow. */
    private static int lighter(int color) {
        int r = (color >> 16) & 0xFF, g = (color >> 8) & 0xFF, b = color & 0xFF;
        return 0xFF000000 | ((r + 255) / 2 << 16) | ((g + 255) / 2 << 8) | (b + 255) / 2;
    }

    /** Text in a table row: left-aligned from {@code x}, or right-aligned to it. */
    private void drawCellText(GuiGraphics g, int row, String text, int x, boolean alignRight, int color) {
        int textY = row * TABLE_ROW + (TABLE_ROW - font.lineHeight) / 2 + 1;
        g.drawString(font, text, alignRight ? x - font.width(text) : x, textY, color, false);
    }

    /** An amount in coins, thousands separated; expenses with a minus. */
    private static String amount(long value, boolean expense) {
        if (value == 0) return "0";
        return (expense ? "-" : "") + String.format("%,d", value);
    }

    /** The net: with a + or - sign. */
    private static String signed(long value) {
        return (value > 0 ? "+" : value < 0 ? "-" : "") + String.format("%,d", Math.abs(value));
    }

    // ===== EMPLOYMENT TAB =====

    private void drawEmploymentTab(GuiGraphics g) {
        int x = getContentX();
        int y = getContentStartY();
        int w = getContentWidth();

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

    private void drawInventoryTab(GuiGraphics g) {
        int ix = getInvPanelX();
        int iy = getInvPanelY();
        int iw = getInvPanelW();
        int ih = getInvPanelH();

        if (townInventory.isEmpty()) {
            String empty = "The town stores are empty.";
            int ew = font.width(empty);
            g.drawString(font, empty, ix + (iw - ew) / 2, iy + 20, COLOR_GRAY, false);
            return;
        }

        int visibleRows = getVisibleRows();
        int totalRows   = townInventory.size();

        List<Map.Entry<String, Long>> entries = new ArrayList<>(townInventory.entrySet());
        int startIndex = listScrollOffset;
        int endIndex   = Math.min(startIndex + visibleRows, totalRows);

        // Rows are laid out in unscaled units inside a scaled pose
        int scaledW = Math.round(iw / INV_SCALE);

        g.pose().pushPose();
        g.pose().translate(ix, iy, 0);
        g.pose().scale(INV_SCALE, INV_SCALE, 1f);

        for (int i = startIndex; i < endIndex; i++) {
            Map.Entry<String, Long> entry = entries.get(i);
            int rowY = (i - startIndex) * ROW_HEIGHT;

            // Alternating row background
            if ((i % 2) == 0) {
                g.fill(0, rowY, scaledW, rowY + ROW_HEIGHT, 0x18000000);
            }

            int textY = rowY + (ROW_HEIGHT - font.lineHeight) / 2 + 1;

            // Item icon on the far left
            net.minecraft.resources.ResourceLocation loc =
                    net.minecraft.resources.ResourceLocation.tryParse(entry.getKey());
            if (loc != null) {
                net.minecraft.world.item.Item item =
                        net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != net.minecraft.world.item.Items.AIR) {
                    g.renderItem(new net.minecraft.world.item.ItemStack(item),
                            2, rowY + (ROW_HEIGHT - ICON_SIZE) / 2);
                }
            }

            // Item name — strip namespace, replace underscores, capitalise
            String displayName = formatItemName(entry.getKey());
            g.drawString(font, displayName, 2 + ICON_SIZE + 4, textY, TEXT_COLOR, false);

            // Quantity right-aligned
            String qty = formatQuantity(entry.getValue());
            g.drawString(font, qty, scaledW - font.width(qty) - 4, textY, COLOR_GOLD, false);
        }

        g.pose().popPose();

        // Scroll indicator on the right edge
        if (totalRows > visibleRows) {
            int scrollTrackX = ix + iw + 4;

            // Track
            g.fill(scrollTrackX, iy, scrollTrackX + 3, iy + ih, 0x40000000);

            // Thumb
            float thumbRatio  = (float) visibleRows / totalRows;
            int   thumbH      = Math.max(10, (int)(ih * thumbRatio));
            float scrollRatio = (float) listScrollOffset / Math.max(1, totalRows - visibleRows);
            int   thumbY      = iy + (int)((ih - thumbH) * scrollRatio);
            g.fill(scrollTrackX, thumbY, scrollTrackX + 3, thumbY + thumbH, 0xA0000000);
        }
    }

    // ===== JOBS TAB =====

    /** How many citizens have each job, most common first ("Unemployed" for citizens without one). */
    private List<Map.Entry<String, Integer>> getJobCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (var citizen : citizens) {
            counts.merge(formatJobName(citizen.jobType()), 1, Integer::sum);
        }
        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(counts.entrySet());
        sorted.sort(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()));
        return sorted;
    }

    private void drawJobsTab(GuiGraphics g) {
        List<String[]> rows = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : getJobCounts()) {
            rows.add(new String[]{ entry.getKey(), String.valueOf(entry.getValue()) });
        }
        drawTwoColumnList(g, rows, "No citizens yet.");
    }

    // ===== CRAFTING TAB =====

    /**
     * The town's supply chain: first the required trades (red) - basic goods its jobs need and it is short
     * of, which the trader buys - then everything its crafting NPCs can make that the town hall holds too
     * little of: tools workers have requested (orange), then the blacksmith's work, then every other job's.
     * Last, the sell list (green): what the town holds more of than it may, which the trader sells off.
     * Each row shows the item, the job(s) that make it and have / want; the name is red when no citizen
     * has one of those jobs.
     */
    private void drawCraftingTab(GuiGraphics g) {
        int ix = getInvPanelX();
        int iy = getInvPanelY();
        int iw = getInvPanelW();
        int ih = getInvPanelH();

        if (craftingNeeds.isEmpty()) {
            String empty = "Nothing needs to be crafted, bought or sold.";
            g.drawString(font, empty, ix + (iw - font.width(empty)) / 2, iy + 20, COLOR_GRAY, false);
            return;
        }

        int visibleRows = getVisibleRows();
        int totalRows   = craftingNeeds.size();
        listScrollOffset = Math.max(0, Math.min(listScrollOffset, Math.max(0, totalRows - visibleRows)));
        int endIndex    = Math.min(listScrollOffset + visibleRows, totalRows);
        int scaledW     = Math.round(iw / INV_SCALE);

        g.pose().pushPose();
        g.pose().translate(ix, iy, 0);
        g.pose().scale(INV_SCALE, INV_SCALE, 1f);

        for (int i = listScrollOffset; i < endIndex; i++) {
            var need  = craftingNeeds.get(i);
            int rowY  = (i - listScrollOffset) * ROW_HEIGHT;
            int textY = rowY + (ROW_HEIGHT - font.lineHeight) / 2 + 1;

            if (need.sell()) {
                g.fill(0, rowY, scaledW, rowY + ROW_HEIGHT, 0x302E7D32); // the sell list stands out in green
            } else if (need.trade()) {
                g.fill(0, rowY, scaledW, rowY + ROW_HEIGHT, 0x30D32F2F); // required trades stand out in red
            } else if (need.toolRequest()) {
                g.fill(0, rowY, scaledW, rowY + ROW_HEIGHT, 0x30FF8F00); // requested tools stand out
            } else if ((i % 2) == 0) {
                g.fill(0, rowY, scaledW, rowY + ROW_HEIGHT, 0x18000000);
            }

            // Icon (the "any sword style" entries show a plain or first-style sword)
            String iconKey = switch (need.itemKey()) {
                case "agotmod:iron_sword_variant"  -> "minecraft:iron_sword";
                case "agotmod:steel_sword_variant" -> "agotmod:steel_sword";
                case "agotmod:noble_sword_variant" -> "agotmod:noble_sword_h1_b1_c1";
                default -> need.itemKey();
            };
            net.minecraft.resources.ResourceLocation loc = net.minecraft.resources.ResourceLocation.tryParse(iconKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
                if (item != net.minecraft.world.item.Items.AIR) {
                    g.renderItem(new net.minecraft.world.item.ItemStack(item), 2, rowY + (ROW_HEIGHT - ICON_SIZE) / 2);
                }
            }

            // Icon | Name (red: no citizen in town has a job that makes it) | Job | have / want
            String count = need.have() + " / " + need.want();
            int countX = scaledW - font.width(count) - 4;
            g.drawString(font, count, countX, textY, need.sell() ? COLOR_GREEN : need.have() == 0 ? COLOR_RED : COLOR_GOLD, false);

            StringBuilder jobs = new StringBuilder();
            if (need.sell()) jobs.append(need.hasWorker() ? "Sell" : "Sell (no trader)");
            else if (need.trade()) jobs.append(need.hasWorker() ? "Trade" : "Trade (no trader)");
            else for (String job : need.jobs().split("/")) {
                if (job.isEmpty()) continue;
                if (!jobs.isEmpty()) jobs.append(" / ");
                jobs.append(formatJobName(job));
            }
            int jobRight = countX - 8;
            int jobX = Math.max(jobRight - font.width(jobs.toString()), scaledW / 2);
            g.drawString(font, font.plainSubstrByWidth(jobs.toString(), jobRight - jobX), jobX, textY,
                    need.hasWorker() ? COLOR_BLUE : COLOR_RED, false);

            String name = !need.label().isEmpty() ? need.label()
                    : need.itemKey().endsWith("_sword_variant")
                            ? formatItemName(need.itemKey().replace("_variant", "")) + " (any style)"
                    : formatItemName(need.itemKey());
            int nameX = 2 + ICON_SIZE + 4;
            int nameColor = need.sell() ? COLOR_GREEN
                    : need.trade() || !need.hasWorker() ? COLOR_RED : need.toolRequest() ? COLOR_ORANGE : TEXT_COLOR;
            g.drawString(font, font.plainSubstrByWidth(name, jobX - nameX - 6), nameX, textY, nameColor, false);
        }

        g.pose().popPose();

        if (totalRows > visibleRows) {
            int scrollTrackX = ix + iw + 4;
            g.fill(scrollTrackX, iy, scrollTrackX + 3, iy + ih, 0x40000000);
            int   thumbH      = Math.max(10, (int)(ih * (float) visibleRows / totalRows));
            float scrollRatio = (float) listScrollOffset / Math.max(1, totalRows - visibleRows);
            int   thumbY      = iy + (int)((ih - thumbH) * scrollRatio);
            g.fill(scrollTrackX, thumbY, scrollTrackX + 3, thumbY + thumbH, 0xA0000000);
        }
    }

    // ===== CITIZENS TAB =====

    private void drawCitizensTab(GuiGraphics g) {
        List<String[]> rows = new ArrayList<>();
        for (var citizen : citizens) {
            rows.add(new String[]{ citizen.name(), formatJobName(citizen.jobType()) });
        }
        drawTwoColumnList(g, rows, "No citizens yet.");
    }

    /** A scrollable list of rows with text on the left and right, drawn like the inventory list. */
    private void drawTwoColumnList(GuiGraphics g, List<String[]> rows, String emptyText) {
        int ix = getInvPanelX();
        int iy = getInvPanelY();
        int iw = getInvPanelW();
        int ih = getInvPanelH();

        if (rows.isEmpty()) {
            int ew = font.width(emptyText);
            g.drawString(font, emptyText, ix + (iw - ew) / 2, iy + 20, COLOR_GRAY, false);
            return;
        }

        int visibleRows = getVisibleRows();
        int totalRows   = rows.size();
        listScrollOffset = Math.max(0, Math.min(listScrollOffset, Math.max(0, totalRows - visibleRows)));
        int endIndex    = Math.min(listScrollOffset + visibleRows, totalRows);
        int scaledW     = Math.round(iw / INV_SCALE);

        g.pose().pushPose();
        g.pose().translate(ix, iy, 0);
        g.pose().scale(INV_SCALE, INV_SCALE, 1f);

        for (int i = listScrollOffset; i < endIndex; i++) {
            String[] row = rows.get(i);
            int rowY  = (i - listScrollOffset) * ROW_HEIGHT;
            int textY = rowY + (ROW_HEIGHT - font.lineHeight) / 2 + 1;

            if ((i % 2) == 0) {
                g.fill(0, rowY, scaledW, rowY + ROW_HEIGHT, 0x18000000);
            }
            g.drawString(font, row[0], 4, textY, TEXT_COLOR, false);
            g.drawString(font, row[1], scaledW - font.width(row[1]) - 4, textY, COLOR_GOLD, false);
        }

        g.pose().popPose();

        // Scroll indicator on the right edge
        if (totalRows > visibleRows) {
            int scrollTrackX = ix + iw + 4;
            g.fill(scrollTrackX, iy, scrollTrackX + 3, iy + ih, 0x40000000);
            int   thumbH      = Math.max(10, (int)(ih * (float) visibleRows / totalRows));
            float scrollRatio = (float) listScrollOffset / Math.max(1, totalRows - visibleRows);
            int   thumbY      = iy + (int)((ih - thumbH) * scrollRatio);
            g.fill(scrollTrackX, thumbY, scrollTrackX + 3, thumbY + thumbH, 0xA0000000);
        }
    }

    /** "cattle_herder" -> "Cattle Herder", "" -> "Unemployed". */
    private String formatJobName(String jobType) {
        if (jobType == null || jobType.isEmpty()) return "Unemployed";
        return formatItemName(jobType);
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
        if (Math.abs(amount) >= 1_000_000) return String.format("%.1fM coins", amount / 1_000_000.0);
        if (Math.abs(amount) >= 1_000)     return String.format("%.1fK coins", amount / 1_000.0);
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
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

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
