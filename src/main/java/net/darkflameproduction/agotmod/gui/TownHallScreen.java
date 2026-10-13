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
    private net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings earnings =
            new net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings(0, 0, 0, Map.of(), Map.of());
    private net.darkflameproduction.agotmod.network.TownHallDataPacket.Economy economy =
            net.darkflameproduction.agotmod.network.TownHallDataPacket.Economy.EMPTY;

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

    // The Finances tab's sub-panels, switched with the buttons down its left side
    private static final int FIN_SUMMARY   = 0;
    private static final int FIN_HAPPINESS = 1;
    private static final int FIN_TAXES     = 2;
    private static final int FIN_FOOD      = 3;
    private static final int FIN_TRADE     = 4;
    private static final int FIN_MARKET    = 5;
    private static final String[] FIN_NAMES = { "Summary", "Happiness", "Taxes & Wages", "Food", "Trade", "Market" };
    private static final int SIDEBAR_W   = 84;
    private static final int SIDEBAR_GAP = 10;
    private int financePanel = FIN_SUMMARY;
    private final List<net.minecraft.client.gui.components.Button> financeButtons = new ArrayList<>();
    private int activeTab = TAB_OVERVIEW;

    // Scroll state for the list tabs (inventory, jobs, citizens)
    private int listScrollOffset = 0;

    // Rename UI state
    private boolean renameModeActive = false;
    private net.minecraft.client.gui.components.EditBox  townNameInput       = null;
    private net.minecraft.client.gui.components.Button  confirmRenameButton = null;
    private net.minecraft.client.gui.components.Button  renameButton        = null;
    private net.minecraft.client.gui.components.Button  claimButton         = null;
    // The owner's tax rate buttons (Finances tab) - placed next to the tax rate as it is drawn
    private net.minecraft.client.gui.components.Button  taxDownButton       = null;
    private net.minecraft.client.gui.components.Button  taxUpButton         = null;
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

        taxDownButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("-"), btn -> changeTax(-net.darkflameproduction.agotmod.economy.VillageEconomy.TAX_STEP_PERCENT)
        ).pos(0, 0).size(12, 10).build();
        taxUpButton = net.minecraft.client.gui.components.Button.builder(
                Component.literal("+"), btn -> changeTax(net.darkflameproduction.agotmod.economy.VillageEconomy.TAX_STEP_PERCENT)
        ).pos(0, 0).size(12, 10).build();
        addRenderableWidget(taxDownButton);
        addRenderableWidget(taxUpButton);

        // The Finances tab's sub-panel buttons, down its left side
        financeButtons.clear();
        for (int i = 0; i < FIN_NAMES.length; i++) {
            final int panel = i;
            net.minecraft.client.gui.components.Button button = net.minecraft.client.gui.components.Button.builder(
                    Component.literal(FIN_NAMES[i]), btn -> switchFinancePanel(panel)
            ).pos(getContentX(), getContentStartY() + i * 20).size(SIDEBAR_W, 16).build();
            financeButtons.add(button);
            addRenderableWidget(button);
        }

        refreshButtonStates();
    }

    /** Whether the player looking is the town's owner - who alone may set its taxes (the server checks too). */
    private boolean viewerIsOwner() {
        var player = net.minecraft.client.Minecraft.getInstance().player;
        return player != null && economy.owner().map(player.getUUID()::equals).orElse(false);
    }

    private void changeTax(int delta) {
        int percent = Math.max(0, Math.min(net.darkflameproduction.agotmod.economy.VillageEconomy.MAX_TAX_PERCENT,
                economy.taxPercent() + delta));
        if (percent == economy.taxPercent()) return;
        net.minecraft.client.Minecraft.getInstance().getConnection().send(
                new net.darkflameproduction.agotmod.network.SetTaxRatePacket(townHallPos, percent));
    }

    private void switchFinancePanel(int panel) {
        financePanel = panel;
        listScrollOffset = 0;
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
        for (int i = 0; i < financeButtons.size(); i++) {
            financeButtons.get(i).visible = activeTab == TAB_FINANCES;
            financeButtons.get(i).active = financePanel != i;
        }
        boolean taxButtons = activeTab == TAB_FINANCES && viewerIsOwner()
                && (financePanel == FIN_SUMMARY || financePanel == FIN_TAXES);
        if (taxDownButton != null) taxDownButton.visible = taxButtons;
        if (taxUpButton   != null) taxUpButton.visible   = taxButtons;
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

    public static void updateFinances(BlockPos packetPos, long townBalance,
                                      net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings earnings,
                                      net.darkflameproduction.agotmod.network.TownHallDataPacket.Economy economy) {
        if (currentInstance == null) return;
        if (!packetPos.equals(currentInstance.townHallPos)) return;
        currentInstance.townBalance = townBalance;
        currentInstance.earnings    = earnings;
        currentInstance.economy     = economy;
        currentInstance.refreshButtonStates();
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
            case TAB_FINANCES  -> financePanel == FIN_MARKET ? economy.marketStock().size() : -1;
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
        drawLabelValue(g, "Citizens", String.valueOf(citizenCount),  x, y, COLOR_GREEN); y += 16;
        long happiness = Math.round(economy.happiness());
        drawLabelValue(g, "Happiness", happiness + " / 100", x, y,
                happiness >= 60 ? COLOR_GREEN : happiness >= 40 ? COLOR_GOLD : COLOR_RED);
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
     * The town's finances and economy (VillageEconomy), in sub-panels picked with the buttons down the left:
     * the summary and cash flow, happiness, taxes and wages, food, trade and the market.
     */
    private void drawFinancesTab(GuiGraphics g) {
        int x = getContentX() + SIDEBAR_W + SIDEBAR_GAP;
        int y = getContentStartY();
        int w = getContentWidth() - SIDEBAR_W - SIDEBAR_GAP;
        int h = getInvPanelY() + getInvPanelH() - y;
        g.fill(x - SIDEBAR_GAP / 2, y, x - SIDEBAR_GAP / 2 + 1, y + h, 0x40000000);

        switch (financePanel) {
            case FIN_SUMMARY -> drawFinanceSummary(g, x, y, w);
            case FIN_HAPPINESS -> drawEconPanel(g, x, y, w, h, "Happiness   H = 50 - 40 x tax - F + 0.25 x liege reputation",
                    new String[]{ "", "Points" }, new float[]{ 1f }, happinessRows());
            case FIN_TAXES -> {
                int top = drawTaxRate(g, x, y);
                drawEconPanel(g, x, top, w, y + h - top, "Taxes and wages per day   (treasury: + taxes - wages - imports + exports)",
                        new String[]{ "Job", "People", "Taxes", "Wage each", "Wages" }, new float[]{ 0.5f, 0.64f, 0.84f, 1f }, payRows());
            }
            case FIN_FOOD -> drawEconPanel(g, x, y, w, h, "Food - servings the villagers ate",
                    new String[]{ "Category", "Today", "Yesterday", "Counts" }, new float[]{ 0.6f, 0.8f, 1f }, foodRows());
            case FIN_TRADE -> drawEconPanel(g, x, y, w, h, "Trade - the trader's imports and exports",
                    new String[]{ "", "" }, new float[]{ 1f }, tradeRows());
            case FIN_MARKET -> drawMarketPanel(g, x, y, w, h);
        }
    }

    /** The tax rate on its own line, with the owner's - and + after it; returns where the line ends. */
    private int drawTaxRate(GuiGraphics g, int x, int y) {
        String tax = economy.taxPercent() + "%  (" + net.darkflameproduction.agotmod.economy.VillageEconomy.taxPerVillager(economy.taxPercent())
                + " coins per villager a day)";
        drawLabelValue(g, "Tax Rate", tax, x, y + 1, COLOR_GOLD);
        int buttonX = x + font.width("Tax Rate:  ") + font.width(tax) + 8;
        if (taxDownButton != null) taxDownButton.setPosition(buttonX, y);
        if (taxUpButton   != null) taxUpButton.setPosition(buttonX + 14, y);
        return y + 16;
    }

    /**
     * The summary: treasury, tax rate (the owner's - and +), happiness, food, wages and trade at the top, and
     * below them a cash flow table for today and one for yesterday - every kind of money in and out
     * (CashFlow) side by side, with totals and the net.
     */
    private void drawFinanceSummary(GuiGraphics g, int x, int y, int w) {
        float s = TABLE_MAX_SCALE;

        // Header and summary drawn small (like the table), so the table gets the room
        int sw = Math.round(w / s);
        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(s, s, 1f);

        drawSectionHeader(g, "Finances", 0, 0, sw, COLOR_HEADER);
        int sy = 14;
        int right = sw / 2;

        // Treasury and debt
        drawLabelValue(g, "Treasury", formatCurrency(townBalance), 0, sy, townBalance < 0 ? COLOR_RED : COLOR_GOLD);
        // A town in debt charges double and pays half (TownHallBlockEntity.buyPriceFactor / sellPriceFactor)
        if (townBalance < 0) drawLabelValue(g, "In Debt", "prices x2, sell value x0.5", right, sy, COLOR_RED);
        else drawLabelValue(g, "Status", "Solvent", right, sy, COLOR_GREEN);
        sy += 12;

        // Tax rate, with the owner's buttons after it; happiness and what makes it
        String tax = economy.taxPercent() + "%  (" + net.darkflameproduction.agotmod.economy.VillageEconomy.taxPerVillager(economy.taxPercent())
                + " per villager a day)";
        drawLabelValue(g, "Tax Rate", tax, 0, sy, COLOR_GOLD);
        int buttonX = x + Math.round((font.width("Tax Rate:  ") + font.width(tax) + 6) * s);
        int buttonY = y + Math.round((sy - 1) * s);
        if (taxDownButton != null) { taxDownButton.setPosition(buttonX, buttonY); }
        if (taxUpButton   != null) { taxUpButton.setPosition(buttonX + 14, buttonY); }
        double happiness = economy.happiness();
        String why = "tax -" + Math.round(net.darkflameproduction.agotmod.economy.VillageEconomy.TAX_SENSITIVITY * economy.taxPercent() / 100.0)
                + ", food -" + Math.round(economy.foodDissatisfaction())
                + ", liege " + signed(Math.round(net.darkflameproduction.agotmod.economy.VillageEconomy.REPUTATION_WEIGHT * economy.liegeReputation()));
        drawLabelValue(g, "Happiness", Math.round(happiness) + " / 100  (" + why + ")", right, sy,
                happiness >= 60 ? COLOR_GREEN : happiness >= 40 ? COLOR_GOLD : COLOR_RED);
        sy += 14;

        // Yesterday's food, and the wages happiness sets
        drawLabelValue(g, "Food", foodSummary(), 0, sy, economy.foodDissatisfaction() <= 5 ? COLOR_GREEN
                : economy.foodDissatisfaction() <= 20 ? COLOR_GOLD : COLOR_RED);
        double wageFactor = net.darkflameproduction.agotmod.economy.VillageEconomy.wageFactor(happiness);
        drawLabelValue(g, "Wages", Math.round(wageFactor * 100) + "% of base (happier villagers ask less)", right, sy, COLOR_GOLD);
        sy += 12;

        // The trader: budget and today's trade
        if (economy.tradeCapacity() > 0) {
            drawLabelValue(g, "Trader Budget", formatCurrency(earnings.traderBudget()) + " / day", 0, sy,
                    earnings.traderBudget() > 0 ? COLOR_GOLD : COLOR_RED);
            drawLabelValue(g, "Trade Today", "imported " + economy.importedToday() + " / " + economy.tradeCapacity()
                    + ", exported " + economy.exportedToday() + " / " + economy.tradeCapacity(), right, sy, COLOR_BLUE);
        } else {
            drawLabelValue(g, "Trader", "None - no imports or exports", 0, sy, COLOR_GRAY);
        }
        sy += 13;

        g.pose().popPose();
        y += Math.round(sy * s);

        drawCashFlowTable(g, x, y, w, getInvPanelY() + getInvPanelH() - y);
    }

    /** Yesterday's food (VillageEconomy): how well fed the town was and how many kinds of food it ate. */
    private String foodSummary() {
        int population = economy.evaluatedPopulation();
        if (population <= 0) return "not evaluated yet (after a full day)";
        Map<net.darkflameproduction.agotmod.economy.FoodCategory, Long> meals =
                new java.util.EnumMap<>(net.darkflameproduction.agotmod.economy.FoodCategory.class);
        long eaten = 0;
        for (Map.Entry<String, Long> entry : economy.mealsYesterday().entrySet()) {
            try {
                meals.put(net.darkflameproduction.agotmod.economy.FoodCategory.valueOf(entry.getKey()), entry.getValue());
                eaten += entry.getValue();
            } catch (IllegalArgumentException ignored) {}
        }
        double quantity = net.darkflameproduction.agotmod.economy.VillageEconomy.quantitySatisfaction(eaten, population);
        int kinds = net.darkflameproduction.agotmod.economy.VillageEconomy.categoriesEaten(meals, population);
        return Math.round(quantity * 100) + "% fed, " + kinds + " kind" + (kinds == 1 ? "" : "s") + " of food yesterday";
    }

    private static final int TABLE_ROW = 12;
    private static final int TABLE_LINE = 0x40000000;
    /** The cash flow tables are drawn small - at most like the other lists (INV_SCALE), smaller if need be to fit. */
    private static final float TABLE_MAX_SCALE = INV_SCALE;
    /** Room between the today and yesterday tables, in table units. */
    private static final int TABLE_GAP = 10;

    /**
     * A row of a cash flow table: who the money came from or went to, and how much - {today, yesterday} - came
     * in and went out.
     */
    private record FinanceRow(String label, long[] income, long[] expense) {}

    /**
     * The cash flow, spreadsheet style: today's table on the left and yesterday's on the right. In each, a row
     * per kind of citizen ("Blacksmith ×2": the town's 2 blacksmiths) - and per kind of money with no citizen
     * behind it - with what came in and what went out, then the totals and the day's net. Both tables have the
     * same rows, so they line up. Scaled down until it all fits: no scrolling.
     */
    private void drawCashFlowTable(GuiGraphics g, int x, int y, int w, int maxH) {
        List<FinanceRow> rows = financeRows();
        long[] incomeTotal = new long[2];
        long[] expenseTotal = new long[2];
        for (FinanceRow r : rows) {
            for (int day = 0; day < 2; day++) {
                incomeTotal[day] += r.income()[day];
                expenseTotal[day] += r.expense()[day];
            }
        }

        // Title, column headers, the rows, the totals and the net
        int neededH = (rows.size() + 4) * TABLE_ROW + 1;
        float scale = Math.min(TABLE_MAX_SCALE, maxH / (float) neededH);
        listScrollOffset = 0;

        int tw = Math.round(w / scale);
        int tableW = (tw - TABLE_GAP) / 2;

        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(scale, scale, 1f);

        drawFinanceTable(g, 0, tableW, "Today", rows, 0, incomeTotal[0], expenseTotal[0]);
        drawFinanceTable(g, tableW + TABLE_GAP, tableW, "Yesterday", rows, 1, incomeTotal[1], expenseTotal[1]);

        g.pose().popPose();
    }

    /**
     * One day's table at {@code left} ({@code day} 0 today, 1 yesterday): its title, a row per entry - name,
     * income, expense, blank where there was none - the totals, the net and the grid.
     */
    private void drawFinanceTable(GuiGraphics g, int left, int w, String title, List<FinanceRow> rows, int day,
                                  long incomeTotal, long expenseTotal) {
        // Columns: name | income | expense
        int amountW = Math.max(font.width("Expense"), Math.max(font.width("-000,000"), Math.max(
                font.width(amount(incomeTotal, false)), font.width(amount(expenseTotal, true))))) + 8;
        int labelW = w - 2 * amountW;
        int incomeX = left + labelW + amountW - 4;
        int expenseX = left + w - 4;

        g.fill(left, 0, left + w, TABLE_ROW, COLOR_HEADER);
        drawCellText(g, 0, title, left + (w - font.width(title)) / 2, false, 0xFFFFFFFF);
        g.fill(left, TABLE_ROW, left + w, 2 * TABLE_ROW, 0x14000000);
        drawCellText(g, 1, "Income", incomeX, true, COLOR_GREEN);
        drawCellText(g, 1, "Expense", expenseX, true, COLOR_RED);

        for (int i = 0; i < rows.size(); i++) {
            FinanceRow r = rows.get(i);
            int row = i + 2;
            String label = r.label();
            if (font.width(label) > labelW - 7) label = font.plainSubstrByWidth(label, labelW - 7 - font.width("…")) + "…";
            drawCellText(g, row, label, left + 4, false, TEXT_COLOR);
            long in = r.income()[day], out = r.expense()[day];
            if (in != 0) drawCellText(g, row, amount(in, false), incomeX, true, COLOR_GREEN);
            if (out != 0) drawCellText(g, row, amount(out, true), expenseX, true, COLOR_RED);
        }

        // Totals
        int totalRow = rows.size() + 2;
        g.fill(left, totalRow * TABLE_ROW, left + w, (totalRow + 1) * TABLE_ROW, 0x28000000);
        drawCellText(g, totalRow, "Total", left + 4, false, TEXT_COLOR);
        drawCellText(g, totalRow, amount(incomeTotal, false), incomeX, true, incomeTotal == 0 ? COLOR_GRAY : COLOR_GREEN);
        drawCellText(g, totalRow, amount(expenseTotal, true), expenseX, true, expenseTotal == 0 ? COLOR_GRAY : COLOR_RED);

        // Net, across the whole table
        int netRow = totalRow + 1;
        long net = incomeTotal - expenseTotal;
        g.fill(left, netRow * TABLE_ROW, left + w, (netRow + 1) * TABLE_ROW, 0x50000000);
        drawCellText(g, netRow, "Net", left + 4, false, TEXT_COLOR);
        drawCellText(g, netRow, signed(net), expenseX, true, net < 0 ? COLOR_RED : net > 0 ? COLOR_GREEN : COLOR_GRAY);

        // Grid: a line under every row, column dividers (not through the title or the net) and the border
        int h = (netRow + 1) * TABLE_ROW;
        for (int row = 1; row <= netRow; row++) {
            int lineColor = row == 1 || row == 2 || row == totalRow || row == netRow ? 0x80000000 : TABLE_LINE;
            g.fill(left, row * TABLE_ROW, left + w, row * TABLE_ROW + 1, lineColor);
        }
        g.fill(left + labelW, TABLE_ROW, left + labelW + 1, netRow * TABLE_ROW, TABLE_LINE);
        g.fill(left + labelW + amountW, TABLE_ROW, left + labelW + amountW + 1, netRow * TABLE_ROW, TABLE_LINE);
        g.fill(left, 0, left + w, 1, 0x80000000);
        g.fill(left, h, left + w, h + 1, 0x80000000);
        g.fill(left, 0, left + 1, h, 0x80000000);
        g.fill(left + w - 1, 0, left + w, h + 1, 0x80000000);
    }

    /**
     * The rows of the cash flow tables: one per kind of citizen money came from or went to (by job name), then
     * one per kind of money no citizen was behind (by CashFlow order), each with its {today, yesterday} income
     * and expense.
     */
    private List<FinanceRow> financeRows() {
        // "FLOW|job" -> {today, yesterday}
        Map<String, long[]> amounts = new HashMap<>();
        earnings.flowsToday().forEach((key, value) -> amounts.computeIfAbsent(key, k -> new long[2])[0] += value);
        earnings.flowsYesterday().forEach((key, value) -> amounts.computeIfAbsent(key, k -> new long[2])[1] += value);
        Map<String, Integer> jobCounts = new HashMap<>();
        for (var citizen : citizens) jobCounts.merge(citizen.jobType(), 1, Integer::sum);

        Map<String, FinanceRow> byJob = new java.util.TreeMap<>();
        Map<net.darkflameproduction.agotmod.block.custom.CashFlow, FinanceRow> byFlow =
                new java.util.EnumMap<>(net.darkflameproduction.agotmod.block.custom.CashFlow.class);
        for (Map.Entry<String, long[]> entry : amounts.entrySet()) {
            String key = entry.getKey();
            int bar = key.indexOf('|');
            net.darkflameproduction.agotmod.block.custom.CashFlow flow;
            try {
                flow = net.darkflameproduction.agotmod.block.custom.CashFlow.valueOf(bar < 0 ? key : key.substring(0, bar));
            } catch (IllegalArgumentException e) {
                continue;
            }
            String job = bar < 0 ? "" : key.substring(bar + 1);
            FinanceRow row = job.isEmpty()
                    ? byFlow.computeIfAbsent(flow, f -> new FinanceRow(f.label, new long[2], new long[2]))
                    : byJob.computeIfAbsent(formatJobName(job), name ->
                            new FinanceRow(name + " ×" + jobCounts.getOrDefault(job, 0), new long[2], new long[2]));
            long[] target = flow.income ? row.income() : row.expense();
            target[0] += entry.getValue()[0];
            target[1] += entry.getValue()[1];
        }

        List<FinanceRow> rows = new ArrayList<>(byJob.values());
        rows.addAll(byFlow.values());
        return rows;
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

    // ===== FINANCES SUB-PANELS: HAPPINESS, TAXES & WAGES, FOOD, TRADE =====

    /** A row of an economy table: its cells and their colours; a total is shaded, a note spans the table. */
    private record EconRow(String[] cells, int[] colors, boolean total, boolean note) {
        static EconRow of(int color, String... cells) {
            int[] colors = new int[cells.length];
            java.util.Arrays.fill(colors, TEXT_COLOR);
            if (cells.length > 1) colors[cells.length - 1] = color;
            return new EconRow(cells, colors, false, false);
        }
        static EconRow total(int color, String... cells) {
            EconRow row = of(color, cells);
            return new EconRow(row.cells, row.colors, true, false);
        }
        static EconRow note(String text) {
            return new EconRow(new String[]{ text }, new int[]{ COLOR_GRAY }, false, true);
        }
        EconRow color(int column, int color) {
            colors[column] = color;
            return this;
        }
    }

    /** Happiness: H = 50 - k*t - F + 0.25*R, part by part, and the wage share it sets. */
    private List<EconRow> happinessRows() {
        int t = economy.taxPercent();
        long taxPenalty = Math.round(net.darkflameproduction.agotmod.economy.VillageEconomy.TAX_SENSITIVITY * t / 100.0);
        long foodPenalty = Math.round(economy.foodDissatisfaction());
        long liege = Math.round(net.darkflameproduction.agotmod.economy.VillageEconomy.REPUTATION_WEIGHT * economy.liegeReputation());
        double happiness = economy.happiness();
        double wageFactor = net.darkflameproduction.agotmod.economy.VillageEconomy.wageFactor(happiness);
        List<EconRow> rows = new ArrayList<>();
        rows.add(EconRow.of(TEXT_COLOR, "Neutral", "50"));
        rows.add(EconRow.of(taxPenalty > 0 ? COLOR_RED : COLOR_GRAY, "Taxes: " + t + "% x "
                + Math.round(net.darkflameproduction.agotmod.economy.VillageEconomy.TAX_SENSITIVITY), signed(-taxPenalty)));
        rows.add(EconRow.of(foodPenalty > 0 ? COLOR_RED : COLOR_GRAY, "Food dissatisfaction (F - see Food)", signed(-foodPenalty)));
        rows.add(EconRow.of(liege > 0 ? COLOR_GREEN : liege < 0 ? COLOR_RED : COLOR_GRAY,
                isClaimed ? "Liege reputation: " + economy.liegeReputation() + " x 0.25" : "Liege reputation: no liege", signed(liege)));
        rows.add(EconRow.total(happinessColor(happiness), "Happiness (0 - 100)", String.valueOf(Math.round(happiness))));
        rows.add(EconRow.of(COLOR_GOLD, "Wages paid: 1 - 0.2 x sqrt(H / 100)", Math.round(wageFactor * 100) + "% of base"));
        rows.add(EconRow.note("Lower taxes, better and more varied food and a respected liege make villagers happier."));
        rows.add(EconRow.note("Happier villagers ask lower wages: from 100% of base at 0 happiness to 80% at 100."));
        return rows;
    }

    /** Taxes and wages per job, per day: T = t * P * base, Wi = ceil(wi * wage share), and what's left. */
    private List<EconRow> payRows() {
        int t = economy.taxPercent();
        double happiness = economy.happiness();
        Map<String, Integer> jobs = new TreeMap<>();
        for (var citizen : citizens) jobs.merge(citizen.jobType() == null ? "" : citizen.jobType(), 1, Integer::sum);
        long perVillager = net.darkflameproduction.agotmod.economy.VillageEconomy.taxPerVillager(t);
        List<EconRow> rows = new ArrayList<>();
        long taxes = 0, wages = 0;
        int people = 0;
        for (Map.Entry<String, Integer> job : jobs.entrySet()) {
            int count = job.getValue();
            int base = net.darkflameproduction.agotmod.economy.VillageEconomy.baseWage(job.getKey());
            long wage = net.darkflameproduction.agotmod.economy.VillageEconomy.wage(job.getKey(), happiness);
            rows.add(new EconRow(new String[]{ formatJobName(job.getKey()), String.valueOf(count), "+" + perVillager * count,
                    base > 0 ? base + " -> " + wage : "-", wage > 0 ? "-" + wage * count : "-" },
                    new int[]{ TEXT_COLOR, TEXT_COLOR, COLOR_GREEN, COLOR_GRAY, COLOR_RED }, false, false));
            taxes += perVillager * count;
            wages += wage * count;
            people += count;
        }
        rows.add(new EconRow(new String[]{ "Total", String.valueOf(people), "+" + taxes, "", "-" + wages },
                new int[]{ TEXT_COLOR, TEXT_COLOR, COLOR_GREEN, TEXT_COLOR, COLOR_RED }, true, false));
        long daily = taxes - wages;
        rows.add(EconRow.total(daily < 0 ? COLOR_RED : COLOR_GREEN, "Taxes - wages, each day", "", "", "", signed(daily)));
        rows.add(EconRow.note("Tax: " + t + "% x " + net.darkflameproduction.agotmod.economy.VillageEconomy.TAX_BASE
                + " = " + perVillager + " coins per villager a day - everyone pays, children and the jobless too."));
        rows.add(EconRow.note("Wage each: base wage -> what is paid at happiness " + Math.round(happiness)
                + ". Paid from the treasury every morning, into debt if need be."));
        return rows;
    }

    /** Food per category, today and yesterday, and Q, D and F of the last day evaluated. */
    private List<EconRow> foodRows() {
        int evaluated = economy.evaluatedPopulation();
        long minimumYesterday = net.darkflameproduction.agotmod.economy.VillageEconomy.varietyMinimum(evaluated);
        long minimumToday = net.darkflameproduction.agotmod.economy.VillageEconomy.varietyMinimum(economy.population());
        List<EconRow> rows = new ArrayList<>();
        Map<net.darkflameproduction.agotmod.economy.FoodCategory, Long> yesterday =
                new java.util.EnumMap<>(net.darkflameproduction.agotmod.economy.FoodCategory.class);
        long eatenToday = 0, eatenYesterday = 0;
        for (var category : net.darkflameproduction.agotmod.economy.FoodCategory.values()) {
            long today = economy.mealsToday().getOrDefault(category.name(), 0L);
            long before = economy.mealsYesterday().getOrDefault(category.name(), 0L);
            yesterday.put(category, before);
            eatenToday += today;
            eatenYesterday += before;
            boolean counts = evaluated > 0 && before >= minimumYesterday;
            rows.add(new EconRow(new String[]{ category.label, String.valueOf(today), String.valueOf(before),
                    evaluated > 0 ? (counts ? "yes" : "no") : "-" },
                    new int[]{ TEXT_COLOR, today >= minimumToday ? COLOR_GREEN : COLOR_GRAY,
                            counts ? COLOR_GREEN : COLOR_GRAY, counts ? COLOR_GREEN : COLOR_GRAY }, false, false));
        }
        rows.add(new EconRow(new String[]{ "Total servings", String.valueOf(eatenToday), String.valueOf(eatenYesterday), "" },
                new int[]{ TEXT_COLOR, TEXT_COLOR, TEXT_COLOR, TEXT_COLOR }, true, false));
        if (evaluated > 0) {
            long needed = (long) evaluated * net.darkflameproduction.agotmod.economy.VillageEconomy.SERVINGS_PER_VILLAGER;
            double q = net.darkflameproduction.agotmod.economy.VillageEconomy.quantitySatisfaction(eatenYesterday, evaluated);
            int kinds = net.darkflameproduction.agotmod.economy.VillageEconomy.categoriesEaten(yesterday, evaluated);
            double d = net.darkflameproduction.agotmod.economy.VillageEconomy.varietySatisfaction(kinds);
            rows.add(EconRow.of(percentColor(q), "Quantity Q: " + eatenYesterday + " eaten / " + needed + " needed", "", "",
                    Math.round(q * 100) + "%"));
            rows.add(EconRow.of(percentColor(d), "Variety D: " + kinds + " kind" + (kinds == 1 ? "" : "s") + " (4 or more = full)", "", "",
                    Math.round(d * 100) + "%"));
            rows.add(EconRow.total(economy.foodDissatisfaction() <= 5 ? COLOR_GREEN : economy.foodDissatisfaction() <= 20 ? COLOR_GOLD : COLOR_RED,
                    "Food dissatisfaction F = 30 x (1 - Q) + 20 x (1 - D)", "", "", String.valueOf(Math.round(economy.foodDissatisfaction()))));
            rows.add(EconRow.note(evaluated + " villagers need " + net.darkflameproduction.agotmod.economy.VillageEconomy.SERVINGS_PER_VILLAGER
                    + " servings each a day. A kind counts once " + minimumYesterday + " servings of it are eaten (20% of villagers)."));
        } else {
            rows.add(EconRow.note("Evaluated after the first full day. F counts as 0 until then."));
        }
        rows.add(EconRow.note("Every kind of bread counts as one kind. Villagers who go hungry slowly lose health."));
        return rows;
    }

    /** The trader: today's imports and exports against the capacity, and the import budget. */
    private List<EconRow> tradeRows() {
        int capacity = economy.tradeCapacity();
        List<EconRow> rows = new ArrayList<>();
        if (capacity <= 0) {
            rows.add(EconRow.note("No trader: the town can't import or export. Give a citizen a trader's job."));
            return rows;
        }
        rows.add(EconRow.of(COLOR_BLUE, "Traders' capacity", capacity + " units a day, each way"));
        rows.add(EconRow.of(COLOR_BLUE, "Imported today", economy.importedToday() + " / " + capacity));
        rows.add(EconRow.of(COLOR_BLUE, "Exported today", economy.exportedToday() + " / " + capacity));
        rows.add(EconRow.of(earnings.traderBudget() > 0 ? COLOR_GOLD : COLOR_RED, "Import budget today",
                formatCurrency(earnings.traderBudget())));
        rows.add(EconRow.note("He imports what the town's jobs and food are short of (food first), once a day, at the market price."));
        rows.add(EconRow.note("Budget: half of yesterday's income, never below a reserve. Capacity: "
                + net.darkflameproduction.agotmod.economy.VillageMarket.TRADE_CAPACITY_PER_TRADER + " units per trader a day."));
        rows.add(EconRow.note("He exports what the town holds too much of - not below "
                + Math.round(net.darkflameproduction.agotmod.economy.VillageMarket.MIN_EXPORT_FACTOR * 100)
                + "% of the base price. Prices: see Market."));
        return rows;
    }

    /** One economy table filling the panel at ({@code x}, {@code y}), scaled down until it fits {@code h}. */
    private void drawEconPanel(GuiGraphics g, int x, int y, int w, int h, String title, String[] headers, float[] ends,
                               List<EconRow> rows) {
        float scale = Math.min(TABLE_MAX_SCALE, h / (float) tableHeight(rows));
        g.pose().pushPose();
        g.pose().translate(x, y, 0);
        g.pose().scale(scale, scale, 1f);
        drawEconTable(g, 0, 0, Math.round(w / scale), title, headers, ends, rows);
        g.pose().popPose();
    }

    /** The height of an economy table: title, header and rows. */
    private static int tableHeight(List<EconRow> rows) {
        return (rows.size() + 2) * TABLE_ROW + 1;
    }

    /**
     * An economy table at ({@code left}, {@code top}) in table units, {@code w} wide: a title bar, the column
     * headers and the rows. The first column is left-aligned; column i (from 1) right-aligns at
     * {@code ends[i-1]} of the width. Returns where it ends.
     */
    private int drawEconTable(GuiGraphics g, int left, int top, int w, String title, String[] headers, float[] ends,
                              List<EconRow> rows) {
        g.fill(left, top, left + w, top + TABLE_ROW, COLOR_HEADER);
        drawEconText(g, title, left + 4, top, false, 0xFFFFFFFF);
        int y = top + TABLE_ROW;
        g.fill(left, y, left + w, y + TABLE_ROW, 0x28000000);
        for (int i = 0; i < headers.length; i++) {
            if (i == 0) drawEconText(g, headers[0], left + 4, y, false, TEXT_COLOR);
            else drawEconText(g, headers[i], left + Math.round(w * ends[i - 1]) - 4, y, true, TEXT_COLOR);
        }
        for (EconRow row : rows) {
            y += TABLE_ROW;
            if (row.total()) g.fill(left, y, left + w, y + TABLE_ROW, 0x20000000);
            if (row.note()) {
                String text = row.cells()[0];
                float noteScale = Math.min(1f, (w - 8) / (float) Math.max(1, font.width(text)));
                g.pose().pushPose();
                g.pose().translate(left + 4, y + (TABLE_ROW - font.lineHeight * noteScale) / 2 + 1, 0);
                g.pose().scale(noteScale, noteScale, 1f);
                g.drawString(font, text, 0, 0, row.colors()[0], false);
                g.pose().popPose();
                continue;
            }
            for (int i = 0; i < row.cells().length; i++) {
                if (row.cells()[i].isEmpty()) continue;
                if (i == 0) drawEconText(g, row.cells()[0], left + 4, y, false, row.colors()[0]);
                else drawEconText(g, row.cells()[i], left + Math.round(w * ends[Math.min(i, ends.length) - 1]) - 4, y, true, row.colors()[i]);
            }
            g.fill(left, y, left + w, y + 1, TABLE_LINE);
        }
        int bottom = y + TABLE_ROW;
        g.fill(left, top, left + w, top + 1, 0x80000000);
        g.fill(left, bottom, left + w, bottom + 1, 0x80000000);
        g.fill(left, top, left + 1, bottom, 0x80000000);
        g.fill(left + w - 1, top, left + w, bottom + 1, 0x80000000);
        return bottom;
    }

    private void drawEconText(GuiGraphics g, String text, int x, int rowTop, boolean alignRight, int color) {
        int textY = rowTop + (TABLE_ROW - font.lineHeight) / 2 + 1;
        g.drawString(font, text, alignRight ? x - font.width(text) : x, textY, color, false);
    }

    private static int happinessColor(double happiness) {
        return happiness >= 60 ? COLOR_GREEN : happiness >= 40 ? COLOR_GOLD : COLOR_RED;
    }

    private static int percentColor(double share) {
        return share >= 0.99 ? COLOR_GREEN : share >= 0.5 ? COLOR_GOLD : COLOR_RED;
    }

    // ===== FINANCES SUB-PANEL: MARKET =====

    /**
     * The town's local market (VillageMarket): every good it holds or has traded, with what its stores hold,
     * the market's stock and today's price - green below the base price, red above. Prices differ from
     * village to village: buy where it's cheap, sell where it's dear.
     */
    private void drawMarketPanel(GuiGraphics g, int ix, int iy, int iw, int ih) {

        if (economy.marketStock().isEmpty()) {
            String empty = "The town trades in nothing yet.";
            g.drawString(font, empty, ix + (iw - font.width(empty)) / 2, iy + 20, COLOR_GRAY, false);
            return;
        }

        List<Map.Entry<String, Double>> entries = new ArrayList<>(economy.marketStock().entrySet());
        entries.sort(Comparator.comparing(e -> formatItemName(e.getKey())));

        // The price rule, a header row, then the goods
        int visibleRows = getVisibleRows() - 2;
        int totalRows = entries.size();
        int startIndex = Math.min(listScrollOffset, Math.max(0, totalRows - visibleRows));
        int endIndex = Math.min(startIndex + visibleRows, totalRows);
        int scaledW = Math.round(iw / INV_SCALE);
        int storedX = Math.round(scaledW * 0.44f);
        int stockX = Math.round(scaledW * 0.55f);
        int desiredX = Math.round(scaledW * 0.66f);
        int basePriceX = Math.round(scaledW * 0.76f);
        int priceX = Math.round(scaledW * 0.88f);
        int baseX = scaledW - 4;

        g.pose().pushPose();
        g.pose().translate(ix, iy, 0);
        g.pose().scale(INV_SCALE, INV_SCALE, 1f);

        int ruleY = (ROW_HEIGHT - font.lineHeight) / 2 + 1;
        String rule = "Price = base x (1 + " + net.darkflameproduction.agotmod.economy.VillageMarket.SENSITIVITY
                + " x (1 - market / desired)), between 50% and 200%. Buying from the town lowers its market, selling raises it.";
        float ruleScale = Math.min(1f, (scaledW - 8) / (float) font.width(rule));
        g.pose().pushPose();
        g.pose().translate(4, ruleY, 0);
        g.pose().scale(ruleScale, ruleScale, 1f);
        g.drawString(font, rule, 0, 0, COLOR_GRAY, false);
        g.pose().popPose();

        int headerY = ROW_HEIGHT + ruleY;
        g.fill(0, ROW_HEIGHT, scaledW, 2 * ROW_HEIGHT, COLOR_HEADER);
        g.drawString(font, "Good", 2 + ICON_SIZE + 4, headerY, 0xFFFFFFFF, false);
        g.drawString(font, "Stored", storedX - font.width("Stored"), headerY, 0xFFFFFFFF, false);
        g.drawString(font, "Market", stockX - font.width("Market"), headerY, 0xFFFFFFFF, false);
        g.drawString(font, "Desired", desiredX - font.width("Desired"), headerY, 0xFFFFFFFF, false);
        g.drawString(font, "Base", basePriceX - font.width("Base"), headerY, 0xFFFFFFFF, false);
        g.drawString(font, "Price here", priceX - font.width("Price here"), headerY, 0xFFFFFFFF, false);
        g.drawString(font, "vs base", baseX - font.width("vs base"), headerY, 0xFFFFFFFF, false);

        for (int i = startIndex; i < endIndex; i++) {
            Map.Entry<String, Double> entry = entries.get(i);
            String itemKey = entry.getKey();
            int rowY = (i - startIndex + 2) * ROW_HEIGHT;
            if ((i % 2) == 0) g.fill(0, rowY, scaledW, rowY + ROW_HEIGHT, 0x18000000);
            int textY = rowY + (ROW_HEIGHT - font.lineHeight) / 2 + 1;

            net.minecraft.resources.ResourceLocation loc = net.minecraft.resources.ResourceLocation.tryParse(itemKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
                if (item != net.minecraft.world.item.Items.AIR) {
                    g.renderItem(new net.minecraft.world.item.ItemStack(item), 2, rowY + (ROW_HEIGHT - ICON_SIZE) / 2);
                }
            }
            g.drawString(font, formatItemName(itemKey), 2 + ICON_SIZE + 4, textY, TEXT_COLOR, false);

            String stored = formatQuantity(townInventory.getOrDefault(itemKey, 0L));
            g.drawString(font, stored, storedX - font.width(stored), textY, COLOR_GOLD, false);
            String stock = formatQuantity(Math.round(entry.getValue()));
            g.drawString(font, stock, stockX - font.width(stock), textY, COLOR_GRAY, false);
            String desired = formatQuantity(Math.round(net.darkflameproduction.agotmod.economy.VillageMarket.desiredStock(itemKey)));
            g.drawString(font, desired, desiredX - font.width(desired), textY, COLOR_GRAY, false);
            String base = net.darkflameproduction.agotmod.util.ItemPricing.getItemPrice(itemKey) + "c";
            g.drawString(font, base, basePriceX - font.width(base), textY, TEXT_COLOR, false);

            double factor = net.darkflameproduction.agotmod.economy.VillageMarket.priceFactor(entry.getValue(),
                    net.darkflameproduction.agotmod.economy.VillageMarket.desiredStock(itemKey));
            double price = net.darkflameproduction.agotmod.economy.VillageMarket.unitPrice(itemKey, entry.getValue());
            int color = factor < 0.97 ? COLOR_GREEN : factor > 1.03 ? COLOR_RED : TEXT_COLOR;
            String priceText = price < 10 ? String.format("%.1fc", price) : Math.round(price) + "c";
            g.drawString(font, priceText, priceX - font.width(priceText), textY, color, false);
            String vsBase = signed(Math.round((factor - 1) * 100)) + "%";
            g.drawString(font, vsBase, baseX - font.width(vsBase), textY, color, false);
        }

        g.pose().popPose();

        if (totalRows > visibleRows) {
            int scrollTrackX = ix + iw + 4;
            g.fill(scrollTrackX, iy, scrollTrackX + 3, iy + ih, 0x40000000);
            int thumbH = Math.max(10, (int) (ih * (float) visibleRows / totalRows));
            float scrollRatio = (float) startIndex / Math.max(1, totalRows - visibleRows);
            int thumbY = iy + (int) ((ih - thumbH) * scrollRatio);
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

    /**
     * The settlement's size by its population, after medieval settlements: a homestead (one or two
     * households), a hamlet (under 100), a village (100 - 500) or large village (500 - 1,000), a market town
     * (1,000 - 2,500), a town (2,500 - 10,000) or large town (10,000 - 25,000), a city (25,000 - 100,000),
     * a great city (100,000 - 250,000, like Paris) and a metropolis (250,000 up to the largest of the age,
     * around a million, like Constantinople or Baghdad at their height).
     */
    private String getTownSizeCategory() {
        if      (citizenCount < 20)      return "Homestead";
        else if (citizenCount < 100)     return "Hamlet";
        else if (citizenCount < 500)     return "Village";
        else if (citizenCount < 1_000)   return "Large Village";
        else if (citizenCount < 2_500)   return "Market Town";
        else if (citizenCount < 10_000)  return "Town";
        else if (citizenCount < 25_000)  return "Large Town";
        else if (citizenCount < 100_000) return "City";
        else if (citizenCount < 250_000) return "Great City";
        else                             return "Metropolis";
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
