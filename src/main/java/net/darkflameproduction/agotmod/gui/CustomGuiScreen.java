package net.darkflameproduction.agotmod.gui;

import dev.tocraft.ctgen.impl.CTGClient;
import dev.tocraft.ctgen.impl.network.SyncMapPacket;
import dev.tocraft.ctgen.impl.screen.MapText;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.gui.widget.AGoTMapWidget;
import net.darkflameproduction.agotmod.network.*;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatsCounter;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class CustomGuiScreen extends Screen {

    // ── Textures ──────────────────────────────────────────────────────────────

    private static final ResourceLocation BLUR_LOCATION =
            ResourceLocation.fromNamespaceAndPath("minecraft", "shaders/post/blur.json");

    private static final ResourceLocation[] STAINED_GLASS_TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_red.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_orange.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_yellow.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_green.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_blue.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_purple.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_magenta.png")
    };

    private static final ResourceLocation[] STAINED_GLASS_TRANSPARENT_TEXTURES = {
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_red_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_orange_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_yellow_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_green_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_blue_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_purple_transparant.png"),
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/stained_glass_magenta_transparant.png")
    };

    private static final ResourceLocation PILLAR_TEXTURE        = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/pillar.png");
    private static final ResourceLocation BORDER_TEXTURE        = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/bottom.png");
    private static final ResourceLocation CORNER_TEXTURE        = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/corner.png");
    private static final ResourceLocation STONE_TEXTURE         = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/gui_asset_bg_main.png");
    private static final ResourceLocation PAPER_SIDE_TEXTURE    = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paperside.png");
    private static final ResourceLocation PAPER_SIDETOP_TEXTURE = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papersidetop.png");
    private static final ResourceLocation PAPER_CORNER_TEXTURE  = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papercorner.png");
    private static final ResourceLocation PAPER_TEXTURE         = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paper.png");
    private static final ResourceLocation BANNER_BASE_TEXTURE   = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png");

    // ── Prototype identity frame textures ─────────────────────────────────────
    private static final ResourceLocation FRAME_TEXTURE         = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/gui_asset_bg_prototype_identity.png");
    private static final ResourceLocation FRAME_GLASS_TEXTURE   = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/gui_asset_bg_prototype_identity_glass.png");
    private static final int GLASS_TEXTURE_WIDTH  = 382;
    private static final int GLASS_TEXTURE_HEIGHT = 212;
    private static final ResourceLocation BOOK_TEXTURE          = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/book.png");

    // ── Constants ─────────────────────────────────────────────────────────────

    private static final String[] SECTION_LABELS      = { "Map", "Quests", "Skills", "Stats", "House", "Faction", "Guilds" };
    private static final String[] STAT_SUBMENU_LABELS = { "General Stats", "Combat Stats", "Crime", "Magic", "Weapon Usage", "Tool Usage" };

    private static final int[] RAINBOW_COLORS = {
            0xFF990000, 0xFF994C00, 0xFF999900, 0xFF009900,
            0xFF000099, 0xFF2D0050, 0xFF57007F
    };

    private static final int SUBMENU_TEXT_COLOR  = 0xFF000000;
    private static final int PARCHMENT_COLOR     = 0xFFF5E7C1;
    private static final int BORDER_PILLAR_WIDTH = 6;
    private static final int BORDER_HEIGHT       = 6;
    private static final int CORNER_SIZE         = 12;

    private static final int    STATS_UPDATE_INTERVAL = 100;
    private static final double TELEPORT_THRESHOLD    = 5.0;

    /**
     * Actual pixel dimensions of gui_asset_bg_prototype_identity.png.
     * Change these if the texture is replaced with a differently sized image.
     * The GUI will always be scaled so TEXTURE_HEIGHT exactly fills the screen height.
     * TEXTURE_WIDTH determines how wide the canvas is at that scale.
     */
    private static final int TEXTURE_WIDTH  = 1082;
    private static final int TEXTURE_HEIGHT = 212;

    // These are derived — do not change them manually.
    private static final int BASE_GUI_WIDTH  = TEXTURE_WIDTH;
    private static final int BASE_GUI_HEIGHT = TEXTURE_HEIGHT;

    // Content void position within the texture (virtual pixels) — used by all non-map sections
    private static final int VOID_X = 431;
    private static final int VOID_Y = 64;
    private static final int VOID_W = 221;
    private static final int VOID_H = 131;

    // Map widget position — separate from the content void, keeps its original area
    private static final int MAP_X = 423;
    private static final int MAP_Y = 56;
    private static final int MAP_W = 239;
    private static final int MAP_H = 140;

    // Scale applied to all section content so it fits inside the void area.
    // Adjust if text is too large or too small.
    private static final float CONTENT_SCALE = 0.5f;
    private static final int TAB_X = 431;
    private static final int TAB_Y = 8;
    private static final int TAB_W = 220;
    private static final int TAB_H = 24;

    // ── House UI state ─────────────────────────────────────────────────────────

    private int selectedHouseTab = 0;
    private net.minecraft.client.gui.components.EditBox inviteEditBox;
    private net.minecraft.client.gui.components.Button  inviteButton;
    private net.minecraft.client.gui.components.Button  closeInviteButton;
    private net.minecraft.client.gui.components.Button  setBannerButton;
    private net.minecraft.client.gui.components.Button  leaveButton;
    private boolean showingLeaveConfirm = false;
    private UUID    kickTargetUUID      = null;
    private String  kickTargetUsername  = "";

    // ── Banner pattern map ─────────────────────────────────────────────────────

    private static final Map<String, ResourceLocation> BANNER_PATTERN_TEXTURES = createBannerPatternMap();

    private static Map<String, ResourceLocation> createBannerPatternMap() {
        Map<String, ResourceLocation> map = new java.util.HashMap<>();
        map.put("base",      ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png"));
        map.put("arryn",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/arryn.png"));
        map.put("baelish",   ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/baelish.png"));
        map.put("baratheon", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/baratheon.png"));
        map.put("blackwood", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/blackwood.png"));
        map.put("bolton",    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/bolton.png"));
        map.put("bracken",   ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/bracken.png"));
        map.put("clegane",   ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/clegane.png"));
        map.put("dayne",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/dayne.png"));
        map.put("frey",      ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/frey.png"));
        map.put("greyjoy",   ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/greyjoy.png"));
        map.put("harlaw",    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/harlaw.png"));
        map.put("hightower", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/hightower.png"));
        map.put("karstark",  ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/karstark.png"));
        map.put("lannister", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/lannister.png"));
        map.put("manderly",  ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/manderly.png"));
        map.put("mormont",   ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/mormont.png"));
        map.put("redwyne",   ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/redwyne.png"));
        map.put("reed",      ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/reed.png"));
        map.put("reyne",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/reyne.png"));
        map.put("royce",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/royce.png"));
        map.put("stark",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/stark.png"));
        map.put("targaryen", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/targaryen.png"));
        map.put("tarly",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/tarly.png"));
        map.put("tully",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/tully.png"));
        map.put("tyrell",    ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/tyrell.png"));
        map.put("umber",     ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/umber.png"));
        map.put("velaryon",  ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/velaryon.png"));
        map.put("stripe_bottom",          ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_bottom.png"));
        map.put("stripe_top",             ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_top.png"));
        map.put("stripe_left",            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_left.png"));
        map.put("stripe_right",           ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_right.png"));
        map.put("stripe_center",          ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_center.png"));
        map.put("stripe_middle",          ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_middle.png"));
        map.put("cross",                  ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/cross.png"));
        map.put("straight_cross",         ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/straight_cross.png"));
        map.put("diagonal_left",          ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/diagonal_left.png"));
        map.put("diagonal_right",         ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/diagonal_right.png"));
        map.put("half_vertical",          ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_vertical.png"));
        map.put("half_horizontal",        ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_horizontal.png"));
        map.put("half_vertical_right",    ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_vertical_right.png"));
        map.put("half_horizontal_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_horizontal_bottom.png"));
        map.put("circle",          ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/circle.png"));
        map.put("border",          ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/border.png"));
        map.put("triangle_top",    ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/triangle_top.png"));
        map.put("triangle_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/triangle_bottom.png"));
        return Collections.unmodifiableMap(map);
    }

    // ── Persistent / instance state ───────────────────────────────────────────

    private static int lastSelectedSection      = 2;
    private static int lastSelectedStatsSubmenu = 0;
    private static CustomGuiScreen currentInstance = null;

    private int selectedSection;
    private int selectedStatsSubmenu;

    private double  lastPlayerX, lastPlayerY, lastPlayerZ;
    private boolean hasInitializedPosition  = false;
    private float   lastPlayerHealth        = 0;
    private boolean isUsingShader           = false;
    private int     statsUpdateTimer        = 0;
    private boolean hasRequestedInitialStats = false;

    private final SyncMapPacket mapPacket;
    private AGoTMapWidget mapWidget;

    private final PlayerSkillData skills = new PlayerSkillData();
    private final HouseData       house  = new HouseData();

    // House edit-box widgets (must live on Screen so Minecraft tracks focus)
    private net.minecraft.client.gui.components.EditBox houseNameEditBox;
    private net.minecraft.client.gui.components.Button  saveHouseButton;
    private net.minecraft.client.gui.components.Button  editHouseButton;

    // ── Constructors ──────────────────────────────────────────────────────────

    public CustomGuiScreen(Minecraft minecraft) {
        this(minecraft, isMapPacketValid(CTGClient.LAST_SYNC_MAP_PACKET.get())
                ? CTGClient.LAST_SYNC_MAP_PACKET.get() : null);
    }

    public CustomGuiScreen(Minecraft minecraft, SyncMapPacket mapPacket) {
        super(Component.translatable("screen.agotmod.custom_gui"));
        this.selectedSection      = lastSelectedSection;
        this.selectedStatsSubmenu = lastSelectedStatsSubmenu;
        this.mapPacket            = mapPacket;
        currentInstance           = this;
        if (mapPacket == null && this.selectedSection == 0) {
            this.selectedSection = 2;
            lastSelectedSection  = 2;
        }
    }

    private static boolean isMapPacketValid(SyncMapPacket packet) {
        if (packet == null) return false;
        if (packet.getMapId() == null) return false;
        return packet.getMapWidth() > 0 && packet.getMapHeight() > 0;
    }

    // ── Static packet-handler entry points ────────────────────────────────────

    public static void setSyncedHouseName(String name)          { HouseData.setSyncedHouseName(name); }
    public static void setSyncedHouseBanner(CompoundTag banner) { HouseData.setSyncedHouseBanner(banner); }
    public static void setOwnedTowns(java.util.List<SyncOwnedTownsPacket.TownInfo> towns) { HouseData.setOwnedTowns(towns); }

    public static void handleHouseNameValidation(boolean isAvailable, String message) {
        if (currentInstance != null) currentInstance.house.onValidationResult(isAvailable, message);
    }

    // ── Screen lifecycle ──────────────────────────────────────────────────────

    @Override
    public void onClose() {
        super.onClose();
        if (currentInstance == this) currentInstance = null;
    }

    @Override
    protected void init() {
        super.init();
        currentInstance = this;

        if (minecraft != null && minecraft.player != null) {
            lastPlayerHealth = minecraft.player.getHealth();
            requestStats();
            lastPlayerX = minecraft.player.getX();
            lastPlayerY = minecraft.player.getY();
            lastPlayerZ = minecraft.player.getZ();
            hasInitializedPosition = true;
        }

        house.load();

        ScreenLayout layout = new ScreenLayout(width, height);

        // Map widget — uses its own position constants, independent of the content void
        if (mapPacket != null) {
            int wx = Math.round(MAP_X * layout.scale + layout.offsetX);
            int wy = Math.round(MAP_Y * layout.scale) - 1;
            int ww = Math.round(MAP_W * layout.scale) + 2;
            int wh = Math.round(MAP_H * layout.scale) + 1;
            mapWidget = AGoTMapWidget.ofPacket(minecraft, wx, wy, ww, wh, mapPacket);
            if (mapWidget != null) mapWidget.setMinZoom(mapWidget.defaultZoom());
        } else {
            mapWidget = null;
        }

        // Clear any leftover house widgets
        removeWidgetIfPresent(houseNameEditBox, () -> houseNameEditBox = null);
        removeWidgetIfPresent(saveHouseButton,  () -> saveHouseButton  = null);
        removeWidgetIfPresent(editHouseButton,  () -> editHouseButton  = null);
        removeWidgetIfPresent(setBannerButton,  () -> setBannerButton  = null);

        applyBlurEffect();
    }

    @Override
    public void tick() {
        super.tick();
        if (minecraft == null || minecraft.player == null) return;

        // Close if player teleports away
        if (hasInitializedPosition) {
            double dx = minecraft.player.getX() - lastPlayerX;
            double dy = minecraft.player.getY() - lastPlayerY;
            double dz = minecraft.player.getZ() - lastPlayerZ;
            if (Math.sqrt(dx*dx + dy*dy + dz*dz) > TELEPORT_THRESHOLD) {
                minecraft.setScreen(null);
                return;
            }
            lastPlayerX = minecraft.player.getX();
            lastPlayerY = minecraft.player.getY();
            lastPlayerZ = minecraft.player.getZ();
        }

        if (!hasRequestedInitialStats) {
            requestStats();
            hasRequestedInitialStats = true;
            return;
        }
        statsUpdateTimer++;
        if (statsUpdateTimer >= STATS_UPDATE_INTERVAL) {
            requestStats();
            statsUpdateTimer = 0;
        }

        house.tickPersist(minecraft, skills);
    }

    private void requestStats() {
        if (minecraft == null || minecraft.player == null || minecraft.getConnection() == null) return;
        try {
            minecraft.getConnection().send(new ServerboundClientCommandPacket(
                    ServerboundClientCommandPacket.Action.REQUEST_STATS));
            skills.updateFromStats(minecraft.player.getStats());
        } catch (Exception e) {
            skills.updateFromStats(minecraft.player.getStats());
        }
    }

    private void applyBlurEffect() {
        if (minecraft == null) return;
        try {
            minecraft.gameRenderer.getClass()
                    .getMethod("loadPostShader", ResourceLocation.class)
                    .invoke(minecraft.gameRenderer, BLUR_LOCATION);
            isUsingShader = true;
        } catch (Exception e1) {
            try {
                minecraft.gameRenderer.getClass()
                        .getMethod("loadPostPass", ResourceLocation.class)
                        .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                isUsingShader = true;
            } catch (Exception e2) {
                AGoTMod.LOGGER.error("Failed to apply blur shader: " + e2.getMessage());
            }
        }
    }

    // ── Input ─────────────────────────────────────────────────────────────────

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        ScreenLayout layout = new ScreenLayout(width, height);

        // Inverse-transform mouse into virtual coordinate space.
        int mx = (int)((mouseX - layout.offsetX) / layout.scale);
        int my = (int)((mouseY - layout.offsetY) / layout.scale);

        // Content-local coords: undo the VOID translate + CONTENT_SCALE
        // used when rendering section content.
        int cmx = (int)((mx - VOID_X) / CONTENT_SCALE);
        int cmy = (int)((my - VOID_Y) / CONTENT_SCALE);

        if (mapWidget != null && mapWidget.isActive() && mapWidget.mouseClicked(mouseX, mouseY, button))
            return true;

        // Section tab bar
        int perTabW = TAB_W / SECTION_LABELS.length;
        for (int i = 0; i < SECTION_LABELS.length; i++) {
            if (i == 0 && mapPacket == null) continue;
            int startX = TAB_X + (i * perTabW);
            int endX   = (i == SECTION_LABELS.length - 1) ? TAB_X + TAB_W : TAB_X + ((i + 1) * perTabW);
            if (isMouseOverRect(mx, my, startX, TAB_Y, endX - startX, TAB_H)) {
                selectedSection     = i;
                lastSelectedSection = i;
                if (i == 3 && !skills.areStatsLoaded && minecraft != null) requestStats();
                playButtonSound();
                return true;
            }
        }

        // Stats submenu
        if (selectedSection == 3) {
            int submenuWidth   = layout.contentWidth / 6;
            int submenuStartX  = layout.contentX + layout.contentWidth / 128;
            int submenuStartY  = layout.contentY + layout.contentHeight / 16;
            int submenuHeight  = layout.contentHeight - (layout.contentHeight / 8);
            int buttonHeight   = submenuHeight / 8;
            int buttonSpacing  = submenuHeight / 24;
            int totalBH        = (buttonHeight * STAT_SUBMENU_LABELS.length)
                    + (buttonSpacing * (STAT_SUBMENU_LABELS.length - 1));
            int submenuStartYC = submenuStartY + (submenuHeight - totalBH) / 2;

            for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
                int buttonY = submenuStartYC + (i * (buttonHeight + buttonSpacing));
                if (isMouseOverRect(cmx, cmy,
                        submenuStartX + 10, buttonY, submenuWidth - 10, buttonHeight)) {
                    selectedStatsSubmenu     = i;
                    lastSelectedStatsSubmenu = i;
                    playButtonSound();
                    return true;
                }
            }
        }

        // House section
        if (selectedSection == 4) {
            int cX = layout.contentX + layout.contentWidth / 20;
            int cY = layout.contentY + layout.contentHeight / 16;
            int cW = layout.contentWidth  - (layout.contentWidth  / 10);
            int cH = layout.contentHeight - (layout.contentHeight / 8);

            int tabY      = cY + 44;
            int tabH      = 18;
            int tabW      = 80;
            int tabGap    = 4;
            int tabsStart = cX + (cW - (tabW * 2 + tabGap)) / 2;
            int contentY  = tabY + tabH + 8;
            int contentH  = cY + cH - contentY - 10;

            if (!house.houseName.isEmpty() && !house.isEditingHouseName) {

                // Confirm dialogs eat clicks first
                if (selectedHouseTab == 1) {
                    if (showingLeaveConfirm || kickTargetUUID != null) {
                        int dialogW = 200, dialogH = 70;
                        int dialogX = cX + (cW - dialogW) / 2;
                        int dialogY = contentY + (contentH - dialogH) / 2;
                        int btnW    = 60, btnH = 18;
                        int yesX    = dialogX + dialogW / 2 - btnW - 6;
                        int noX     = dialogX + dialogW / 2 + 6;
                        int btnY    = dialogY + dialogH - btnH - 8;

                        if (isMouseOverRect(cmx, cmy, yesX, btnY, btnW, btnH)) {
                            if (showingLeaveConfirm) {
                                net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                                        new net.darkflameproduction.agotmod.network.LeaveHousePacket());
                                showingLeaveConfirm = false;
                                house.houseName = "";
                                removeWidgetIfPresent(leaveButton, () -> leaveButton = null);
                            } else {
                                net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                                        new net.darkflameproduction.agotmod.network.KickHouseMemberPacket(kickTargetUUID));
                                kickTargetUUID     = null;
                                kickTargetUsername = "";
                            }
                            playButtonSound();
                            return true;
                        }
                        if (isMouseOverRect(cmx, cmy, noX, btnY, btnW, btnH)) {
                            showingLeaveConfirm = false;
                            kickTargetUUID      = null;
                            kickTargetUsername  = "";
                            playButtonSound();
                            return true;
                        }
                        return true;
                    }

                    // Red X kick buttons (founder only)
                    if (HouseData.isFounder()) {
                        java.util.List<HouseData.MemberEntry> members = HouseData.getHouseMembers();
                        int safeLeft  = cX + 90;
                        int safeRight = cX + cW - 90;
                        int safeW     = safeRight - safeLeft;
                        int faceSize  = 16;
                        int rowH      = faceSize + 6;
                        int listY     = contentY + 10;

                        for (int i = 0; i < members.size(); i++) {
                            HouseData.MemberEntry entry = members.get(i);
                            boolean isSelf = minecraft.player != null
                                    && minecraft.player.getUUID().equals(entry.uuid);
                            if (isSelf) continue;
                            int rowY  = listY + (i * rowH);
                            if (rowY + rowH > contentY + contentH - 10) break;
                            int nameW    = font.width(entry.username);
                            int rowW     = faceSize + 6 + nameW;
                            int rowX     = safeLeft + (safeW - rowW) / 2;
                            int xBtnSize = 12;
                            int xBtnX    = rowX + faceSize + 6 + nameW + 6;
                            int xBtnY    = rowY + (faceSize - xBtnSize) / 2;
                            if (isMouseOverRect(cmx, cmy, xBtnX, xBtnY, xBtnSize, xBtnSize)) {
                                kickTargetUUID     = entry.uuid;
                                kickTargetUsername = entry.username;
                                playButtonSound();
                                return true;
                            }
                        }
                    }
                }

                // Tab bar
                if (isMouseOverRect(cmx, cmy, tabsStart, tabY, tabW, tabH)) {
                    if (selectedHouseTab != 0) {
                        removeWidgetIfPresent(inviteEditBox,     () -> inviteEditBox     = null);
                        removeWidgetIfPresent(closeInviteButton, () -> closeInviteButton = null);
                        removeWidgetIfPresent(inviteButton,      () -> inviteButton      = null);
                        removeWidgetIfPresent(leaveButton,       () -> leaveButton       = null);
                        showingLeaveConfirm = false;
                        kickTargetUUID      = null;
                        kickTargetUsername  = "";
                        selectedHouseTab = 0;
                        playButtonSound();
                    }
                    return true;
                }
                if (isMouseOverRect(cmx, cmy, tabsStart + tabW + tabGap, tabY, tabW, tabH)) {
                    if (selectedHouseTab != 1) {
                        removeWidgetIfPresent(inviteEditBox,     () -> inviteEditBox     = null);
                        removeWidgetIfPresent(closeInviteButton, () -> closeInviteButton = null);
                        removeWidgetIfPresent(editHouseButton,   () -> editHouseButton   = null);
                        removeWidgetIfPresent(setBannerButton,   () -> setBannerButton   = null);
                        selectedHouseTab = 1;
                        playButtonSound();
                    }
                    return true;
                }

                // Invite-tab send button
                if (selectedHouseTab == 2) {
                    int centerX = cX + cW / 2;
                    int midY    = contentY + contentH / 2 - 20;
                    int boxW    = (int)(cW * 0.45f);
                    int boxX    = centerX - boxW / 2 - 30;
                    int btnW    = 55;
                    int btnX    = boxX + boxW + 6;
                    int btnY    = midY + 2;
                    if (isMouseOverRect(cmx, cmy, btnX, btnY, btnW, 18)) {
                        if (inviteEditBox != null) {
                            String target = inviteEditBox.getValue().trim();
                            if (!target.isEmpty()) {
                                net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                                        new net.darkflameproduction.agotmod.network.SendHouseInvitePacket(target));
                                inviteEditBox.setValue("");
                                playButtonSound();
                            }
                        }
                        return true;
                    }
                }
            }

            // No house, pending invite: accept / decline
            if (house.houseName.isEmpty() && HouseData.hasPendingInvite()) {
                int centerX = cX + cW / 2;
                int centerY = cY + cH / 2;
                int btnW    = 80, btnH = 20;
                int acceptX = centerX - btnW - 10;
                int rejectX = centerX + 10;
                int btnY    = centerY + 20;
                if (isMouseOverRect(cmx, cmy, acceptX, btnY, btnW, btnH)) {
                    net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                            new net.darkflameproduction.agotmod.network.RespondHouseInvitePacket(true));
                    playButtonSound();
                    return true;
                }
                if (isMouseOverRect(cmx, cmy, rejectX, btnY, btnW, btnH)) {
                    net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                            new net.darkflameproduction.agotmod.network.RespondHouseInvitePacket(false));
                    playButtonSound();
                    return true;
                }
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double deltaX, double deltaY) {
        if (mapWidget != null && selectedSection == 0 &&
                mouseX >= mapWidget.getX() && mouseX < mapWidget.getX() + mapWidget.getWidth() &&
                mouseY >= mapWidget.getY() && mouseY < mapWidget.getY() + mapWidget.getHeight()) {
            return mapWidget.mouseScrolled(mouseX, mouseY, deltaX, deltaY);
        }
        return super.mouseScrolled(mouseX, mouseY, deltaX, deltaY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (mapWidget != null && selectedSection == 0 &&
                mouseX >= mapWidget.getX() && mouseX < mapWidget.getX() + mapWidget.getWidth() &&
                mouseY >= mapWidget.getY() && mouseY < mapWidget.getY() + mapWidget.getHeight()) {
            return mapWidget.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
        return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    // ── Render ────────────────────────────────────────────────────────────────

    @Override
    public void render(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        super.render(g, mouseX, mouseY, partialTick);

        if (minecraft != null && minecraft.player != null) {
            float currentHealth = minecraft.player.getHealth();
            if (currentHealth < lastPlayerHealth) { minecraft.setScreen(null); return; }
            lastPlayerHealth = currentHealth;
            skills.updateFromStats(minecraft.player.getStats());
        }

        ScreenLayout layout = new ScreenLayout(width, height);

        // ── Push virtual canvas pose ──────────────────────────────────────────
        g.pose().pushPose();
        g.pose().translate(layout.offsetX, layout.offsetY, 0);
        g.pose().scale(layout.scale, layout.scale, 1f);

        int scaledMouseX = (int)((mouseX - layout.offsetX) / layout.scale);
        int scaledMouseY = (int)((mouseY - layout.offsetY) / layout.scale);

        // ── Layer 1: tab buttons (behind frame) ───────────────────────────────
        drawSectionButtons(g, scaledMouseX, scaledMouseY, layout);

        // ── Layer 2: glass texture centred (382x212 inside 1082x212) ─────────
        if (selectedSection >= 0 && selectedSection < RAINBOW_COLORS.length) {
            int glassX = (BASE_GUI_WIDTH - GLASS_TEXTURE_WIDTH) / 2;
            int col = RAINBOW_COLORS[selectedSection];
            float r   = ((col >> 16) & 0xFF) / 255f;
            float gg2 = ((col >>  8) & 0xFF) / 255f;
            float b2  =  (col        & 0xFF) / 255f;
            com.mojang.blaze3d.systems.RenderSystem.enableBlend();
            com.mojang.blaze3d.systems.RenderSystem.setShaderColor(r, gg2, b2, 1f);
            g.blit(FRAME_GLASS_TEXTURE, glassX, 0, 0, 0, GLASS_TEXTURE_WIDTH, GLASS_TEXTURE_HEIGHT, GLASS_TEXTURE_WIDTH, GLASS_TEXTURE_HEIGHT);
            com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            com.mojang.blaze3d.systems.RenderSystem.disableBlend();
        }

        // ── Layer 2: frame texture on top ─────────────────────────────────────
        g.blit(FRAME_TEXTURE, 0, 0, 0, 0, BASE_GUI_WIDTH, BASE_GUI_HEIGHT, BASE_GUI_WIDTH, BASE_GUI_HEIGHT);

        // ── Layer 3: section content in the void ──────────────────────────────
        if (selectedSection >= 0 && selectedSection < RAINBOW_COLORS.length) {
            switch (selectedSection) {
                case 0 -> {
                    // Map widget uses real screen coords — render outside the virtual pose
                    g.pose().popPose();
                    drawMapSection(g, mouseX, mouseY, layout, partialTick);
                    g.pose().pushPose();
                    g.pose().translate(layout.offsetX, layout.offsetY, 0);
                    g.pose().scale(layout.scale, layout.scale, 1f);
                }
                default -> {
                    g.pose().pushPose();
                    g.pose().translate(VOID_X, VOID_Y, 0);
                    g.pose().scale(CONTENT_SCALE, CONTENT_SCALE, 1f);
                    // Mouse coords relative to void origin, scaled back
                    int cmx = (int)((scaledMouseX - VOID_X) / CONTENT_SCALE);
                    int cmy = (int)((scaledMouseY - VOID_Y) / CONTENT_SCALE);
                    switch (selectedSection) {
                        case 2 -> drawSkillsSection(g, cmx, cmy, layout);
                        case 3 -> drawStatsSection(g, cmx, cmy, layout);
                        case 4 -> drawHouseSection(g, cmx, cmy, (int)mouseX, (int)mouseY, layout);
                    }
                    g.pose().popPose();
                }
            }
        }

        g.pose().popPose();
    }

    @Override
    public void renderBackground(GuiGraphics g, int mouseX, int mouseY, float partialTick) {
        // Suppress vanilla blur / dim — we handle that ourselves
    }

    @Override
    public boolean isPauseScreen() { return false; }

    // ── Section renderers ─────────────────────────────────────────────────────

    private void drawSectionButtons(GuiGraphics g, int mouseX, int mouseY, ScreenLayout layout) {
        int perTabW = TAB_W / SECTION_LABELS.length;
        for (int i = 0; i < SECTION_LABELS.length; i++) {
            int startX = TAB_X + (i * perTabW);
            int endX   = (i == SECTION_LABELS.length - 1) ? TAB_X + TAB_W : TAB_X + ((i + 1) * perTabW);
            int bw     = endX - startX;

            boolean mapUnavail = (i == 0 && mapPacket == null);
            boolean selected   = (i == selectedSection);
            ResourceLocation tex = selected
                    ? STAINED_GLASS_TRANSPARENT_TEXTURES[i]
                    : STAINED_GLASS_TEXTURES[i];

            if (mapUnavail) com.mojang.blaze3d.systems.RenderSystem.setShaderColor(0.4f, 0.4f, 0.4f, 0.6f);
            int tileSize = 32;
            for (int tx = 0; tx < bw; tx += tileSize)
                for (int ty = 0; ty < TAB_H; ty += tileSize)
                    g.blit(tex, startX + tx, TAB_Y + ty, 0, 0,
                            Math.min(tileSize, bw - tx), Math.min(tileSize, TAB_H - ty),
                            tileSize, tileSize);
            com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1f, 1f, 1f, 1f);

            String text      = SECTION_LABELS[i];
            int    textColor = mapUnavail ? 0xFF444444 : (selected ? 0xFFFFFFFF : PARCHMENT_COLOR);
            float  textScale = 0.7f;
            int    tw        = (int)(font.width(text) * textScale);
            g.pose().pushPose();
            g.pose().scale(textScale, textScale, 1f);
            g.drawString(font, text,
                    (int)((startX + (bw - tw) / 2) / textScale),
                    (int)((TAB_Y + (TAB_H - font.lineHeight * textScale) / 2) / textScale),
                    textColor, false);
            g.pose().popPose();
        }
    }

    private void drawMapSection(GuiGraphics g, int mouseX, int mouseY, ScreenLayout layout, float partialTick) {
        if (mapPacket == null || mapWidget == null) return;
        if (mapWidget.isActive() && mapWidget.getMapTextureId() != null &&
                minecraft.getResourceManager().getResource(mapWidget.getMapTextureId()).isPresent()) {
            mapWidget.render(g, mouseX, mouseY, partialTick);
        }
    }

    private void drawSkillsSection(GuiGraphics g, int mouseX, int mouseY, ScreenLayout layout) {
        int panelWidth  = (int)(layout.contentWidth * 0.45);
        int totalWidth  = (panelWidth * 2) + 2;
        int startX      = layout.contentX + ((layout.contentWidth - totalWidth) / 2);
        int leftPanelX  = startX;
        int rightPanelX = startX + panelWidth + 2;
        int panelY      = layout.contentY + layout.contentHeight / 16;
        int panelHeight = layout.contentHeight - (layout.contentHeight / 8);



        drawSubmenuTitle(g, "General Skills",
                leftPanelX  + (panelWidth - font.width("General Skills")) / 2,       panelY);
        drawSubmenuTitle(g, "Specification Skills",
                rightPanelX + (panelWidth - font.width("Specification Skills")) / 2,  panelY);

        int columnWidth = panelWidth / 2;
        int headerY     = panelY + layout.contentHeight / 10 + 10;
        float scale     = 0.9f;

        drawColumnHeaders(g, leftPanelX,  panelWidth, columnWidth, headerY, scale, "Skill Level", "Progress");
        drawColumnHeaders(g, rightPanelX, panelWidth, columnWidth, headerY, scale, "Skill Level", "Progress");

        int statsY      = headerY + (int)(font.lineHeight * scale) + 10;
        int lineSpacing = layout.contentHeight / 18;

        drawGeneralSkill2Col(g, "Agility",   skills.agilityLevel,   skills.agilityProgress,   leftPanelX, statsY,                 columnWidth, scale);
        drawGeneralSkill2Col(g, "Strength",  skills.strengthLevel,  skills.strengthProgress,  leftPanelX, statsY + lineSpacing,   columnWidth, scale);
        drawGeneralSkill2Col(g, "Endurance", skills.enduranceLevel, skills.enduranceProgress, leftPanelX, statsY + lineSpacing*2, columnWidth, scale);

        drawWeaponSkill2Col(g, "One Handed",  skills.oneHandedLevel,  skills.oneHandedProgress,  skills.nextOneHandedRequirement,  rightPanelX, statsY,                 columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(g, "Two Handed",  skills.twoHandedLevel,  skills.twoHandedProgress,  skills.nextTwoHandedRequirement,  rightPanelX, statsY + lineSpacing,   columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(g, "Polearms",    skills.polearmLevel,    skills.polearmProgress,    skills.nextPolearmRequirement,    rightPanelX, statsY + lineSpacing*2, columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(g, "Short Blade", skills.shortBladeLevel, skills.shortBladeProgress, skills.nextShortBladeRequirement, rightPanelX, statsY + lineSpacing*3, columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(g, "Ranged",      skills.rangedLevel,     skills.rangedProgress,     skills.nextRangedRequirement,     rightPanelX, statsY + lineSpacing*4, columnWidth, scale, RAINBOW_COLORS[0]);
    }

    private void drawColumnHeaders(GuiGraphics g, int panelX, int panelWidth, int columnWidth,
                                   int headerY, float scale, String col1, String col2) {
        int c1w = (int)(font.width(col1) * scale);
        int c2w = (int)(font.width(col2) * scale);
        int c1x = panelX + columnWidth / 2 - c1w / 2;
        int c2x = panelX + columnWidth + columnWidth / 2 - c2w / 2;
        g.pose().pushPose();
        g.pose().scale(scale, scale, 1.0f);
        g.drawString(font, col1, (int)(c1x / scale), (int)(headerY / scale), SUBMENU_TEXT_COLOR, false);
        g.drawString(font, col2, (int)(c2x / scale), (int)(headerY / scale), SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();
        g.fill(panelX + panelWidth/10, headerY + (int)(font.lineHeight * scale) + 2,
                panelX + panelWidth - panelWidth/10, headerY + (int)(font.lineHeight * scale) + 3,
                SUBMENU_TEXT_COLOR);
    }

    private void drawGeneralSkill2Col(GuiGraphics g, String name, int level, double progress,
                                      int x, int y, int columnWidth, float scale) {
        int barHeight   = 6;
        int maxBarWidth = columnWidth - 25;
        g.pose().pushPose();
        g.pose().scale(scale, scale, 1.0f);
        String levelText = name + ": " + level;
        int lw = (int)(font.width(levelText) * scale);
        int cx = x + columnWidth / 2;
        g.drawString(font, levelText, (int)((cx - lw / (2 * scale)) / scale), (int)(y / scale), SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();
        int barX = x + columnWidth + 10;
        int barW = maxBarWidth - 20;
        int cy   = y + (int)((font.lineHeight * scale) / 2);
        g.fill(barX, cy - barHeight/2, barX + barW, cy + barHeight/2, 0xFF555555);
        g.fill(barX, cy - barHeight/2, barX + (int)(barW * progress), cy + barHeight/2, RAINBOW_COLORS[0]);
        g.pose().pushPose();
        g.pose().scale(scale, scale, 1.0f);
        String pct       = level < 100 ? String.format("%.0f%%", progress * 100) : "Max";
        int    textColor = level < 100 ? SUBMENU_TEXT_COLOR : 0xFF55FF55;
        g.drawString(font, pct, (int)((barX + barW + 5) / scale), (int)(y / scale), textColor, false);
        g.pose().popPose();
    }

    private void drawWeaponSkill2Col(GuiGraphics g, String skillName, int level, double progress,
                                     double nextReq, int x, int y, int columnWidth, float scale, int color) {
        int barHeight   = 6;
        int maxBarWidth = columnWidth - 25;
        g.pose().pushPose();
        g.pose().scale(scale, scale, 1.0f);
        String levelText = skillName + ": " + level;
        int lw = (int)(font.width(levelText) * scale);
        int cx = x + columnWidth / 2;
        g.drawString(font, levelText, (int)((cx - lw / (2 * scale)) / scale), (int)(y / scale), SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();
        int barX = x + columnWidth + 10;
        int barW = maxBarWidth - 20;
        int cy   = y + (int)((font.lineHeight * scale) / 2);
        g.fill(barX, cy - barHeight/2, barX + barW, cy + barHeight/2, 0xFF555555);
        g.fill(barX, cy - barHeight/2, barX + (int)(barW * progress), cy + barHeight/2,
                level < 100 ? color : RAINBOW_COLORS[4]);
        g.pose().pushPose();
        g.pose().scale(scale, scale, 1.0f);
        String pct       = level < 100 ? String.format("%.0f%%", progress * 100) : "Max";
        int    textColor = level < 100 ? SUBMENU_TEXT_COLOR : 0xFF55FF55;
        g.drawString(font, pct, (int)((barX + barW + 5) / scale), (int)(y / scale), textColor, false);
        g.pose().popPose();
    }

    private void drawStatsSection(GuiGraphics g, int mouseX, int mouseY, ScreenLayout layout) {
        int submenuWidth   = layout.contentWidth / 6;
        int submenuStartX  = layout.contentX + layout.contentWidth / 128;
        int submenuStartY  = layout.contentY + layout.contentHeight / 16;
        int submenuHeight  = layout.contentHeight - (layout.contentHeight / 8);
        int buttonHeight   = submenuHeight / 8;
        int buttonSpacing  = submenuHeight / 24;
        int totalBH        = (buttonHeight * STAT_SUBMENU_LABELS.length) + (buttonSpacing * (STAT_SUBMENU_LABELS.length - 1));
        int submenuStartYC = submenuStartY + (submenuHeight - totalBH) / 2;

        for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
            int buttonY  = submenuStartYC + (i * (buttonHeight + buttonSpacing));
            boolean hover = isMouseOverRect(mouseX, mouseY, submenuStartX, buttonY, submenuWidth, buttonHeight);
            boolean sel   = (i == selectedStatsSubmenu);
            ResourceLocation tex = (hover || sel) ? STAINED_GLASS_TRANSPARENT_TEXTURES[3] : STAINED_GLASS_TEXTURES[3];
            renderTexturedPanel(g, tex, submenuStartX + 10, buttonY, submenuWidth - 10, buttonHeight, true);
            String label = STAT_SUBMENU_LABELS[i];
            int lw = font.width(label);
            g.drawString(font, label,
                    submenuStartX + 10 + (submenuWidth - 10 - lw) / 2,
                    buttonY + (buttonHeight - font.lineHeight) / 2,
                    sel ? 0xFFFFFFFF : SUBMENU_TEXT_COLOR, false);
        }

        int origCX = submenuStartX + submenuWidth + layout.contentWidth / 32;
        int origCY = submenuStartY;
        int origCW = layout.contentWidth  - submenuWidth - (layout.contentWidth / 16);
        int origCH = submenuHeight;
        int cW     = (int)(origCW * 0.75);
        int cH     = (int)(origCH * 0.75);
        int cX     = origCX + origCW / 2 - cW / 2;
        int cY     = origCY + origCH / 2 - cH / 2;


        drawSubmenuTitle(g, STAT_SUBMENU_LABELS[selectedStatsSubmenu],
                cX + (cW - font.width(STAT_SUBMENU_LABELS[selectedStatsSubmenu])) / 2, cY);

        int sX = cX + cW / 10;
        int sY = cY + (int)(layout.contentHeight / 12 * 0.75) + 15;
        int ls = (int)(layout.contentHeight / 16 * 0.75);
        drawSubmenuContent(g, sX, sY, ls, cX, cW);
    }

    private void drawSubmenuContent(GuiGraphics g, int x, int y, int ls, int cX, int cW) {
        switch (selectedStatsSubmenu) {
            case 0 -> {
                drawStatCentered(g, "Deaths",           skills.cachedDeaths,       cX, cW, y);
                drawStatCentered(g, "Jumps",            skills.cachedJumps,        cX, cW, y + ls);
                drawDistanceStatCentered(g, "Distance Traveled", skills.cachedTotalDistance, cX, cW, y + ls*2);
            }
            case 1 -> {
                drawStatCentered(g, "Mob Kills",    skills.cachedMobKills,    cX, cW, y);
                drawStatCentered(g, "Player Kills", skills.cachedPlayerKills, cX, cW, y + ls);
                drawStatCentered(g, "Damage Dealt", skills.cachedDamageDealt, cX, cW, y + ls*2);
                drawStatCentered(g, "Damage Taken", skills.cachedDamageTaken, cX, cW, y + ls*3);
            }
            case 2 -> {
                drawStatCentered(g, "Items Stolen", 0, cX, cW, y);
                drawStatCentered(g, "Bounty",       0, cX, cW, y + ls);
            }
            case 3 -> {
                drawStatCentered(g, "Spells Cast", 0, cX, cW, y);
                drawStatCentered(g, "Magic Level", 0, cX, cW, y + ls);
            }
            case 4 -> {
                drawStatCentered(g, "One Handed Usage",  skills.getOneHandedUsage(),  cX, cW, y);
                drawStatCentered(g, "Two Handed Usage",  skills.getTwoHandedUsage(),  cX, cW, y + ls);
                drawStatCentered(g, "Polearm Usage",     skills.getPolearmUsage(),    cX, cW, y + ls*2);
                drawStatCentered(g, "Short Blade Usage", skills.getShortBladeUsage(), cX, cW, y + ls*3);
                drawStatCentered(g, "Ranged Usage",      skills.getRangedUsage(),     cX, cW, y + ls*4);
                drawFavoriteWeapon(g, cX, cW, y + ls*5);
            }
            case 5 -> {
                drawStatCentered(g, "Blocks Mined",  0, cX, cW, y);
                drawStatCentered(g, "Items Crafted", 0, cX, cW, y + ls);
            }
        }
    }

    private void drawStatCentered(GuiGraphics g, String label, int value, int cX, int cW, int y) {
        String text = (label.equals("Damage Dealt") || label.equals("Damage Taken"))
                ? label + ": " + String.format("%.0f", value / 10.0)
                : label + ": " + value;
        g.drawString(font, text, cX + (cW - font.width(text)) / 2, y, SUBMENU_TEXT_COLOR, false);
    }

    private void drawDistanceStatCentered(GuiGraphics g, String label, double km, int cX, int cW, int y) {
        String text = label + ": " + String.format("%.2f", km) + " km";
        g.drawString(font, text, cX + (cW - font.width(text)) / 2, y, SUBMENU_TEXT_COLOR, false);
    }

    private void drawFavoriteWeapon(GuiGraphics g, int cX, int cW, int y) {
        String text = "Favourite Weapon: " + skills.favoriteWeapon;
        if (skills.favoriteWeaponUses > 0) text += " (" + skills.favoriteWeaponUses + " uses)";
        g.drawString(font, text, cX + (cW - font.width(text)) / 2, y, SUBMENU_TEXT_COLOR, false);
    }

    // ── House section ─────────────────────────────────────────────────────────

    private void drawHouseSection(GuiGraphics g, int mouseX, int mouseY, int rawMouseX, int rawMouseY, ScreenLayout layout) {
        int cX = layout.contentX + layout.contentWidth / 20;
        int cY = layout.contentY + layout.contentHeight / 16;
        int cW = layout.contentWidth  - (layout.contentWidth  / 10);
        int cH = layout.contentHeight - (layout.contentHeight / 8);



        if (house.houseName.isEmpty() && HouseData.hasPendingInvite()) {
            drawInvitePanel(g, mouseX, mouseY, cX, cY, cW, cH);
            return;
        }

        if (house.houseName.isEmpty() || house.isEditingHouseName) {
            drawHouseCreationPanel(g, rawMouseX, rawMouseY, cX, cY, cW, cH);
            return;
        }

        if (!house.hasRequestedTowns) {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(
                    new net.darkflameproduction.agotmod.network.RequestOwnedTownsPacket());
            house.hasRequestedTowns = true;
        }

        boolean founder = HouseData.isFounder();

        String houseTitle = "House " + house.houseName;
        int titleW = (int)(font.width(houseTitle) * 1.3f);
        int titleX = cX + (cW - titleW) / 2;
        g.pose().pushPose();
        g.pose().scale(1.3f, 1.3f, 1.0f);
        g.drawString(font, houseTitle, (int)(titleX / 1.3f), (int)((cY + 18) / 1.3f), SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();

        int bannerY = cY + (cH - 120) / 2;
        drawHouseBanner(g, cX + 20,          bannerY);
        drawHouseBanner(g, cX + cW - 80,     bannerY);

        int cornerBtnX = toScreenX(cX + cW - 60);
        int cornerBtnY = toScreenY(cY + 14);
        int cornerBtnW = toScreenSize(44);
        int cornerBtnH = toScreenSize(16);

        if (founder) {
            if (selectedHouseTab == 0) {
                removeWidgetIfPresent(inviteButton,      () -> inviteButton      = null);
                removeWidgetIfPresent(closeInviteButton, () -> closeInviteButton = null);
                if (editHouseButton == null) {
                    editHouseButton = net.minecraft.client.gui.components.Button.builder(
                            net.minecraft.network.chat.Component.literal("Edit"), btn -> {
                                house.isEditingHouseName = true;
                                removeWidget(editHouseButton); editHouseButton = null;
                                playButtonSound();
                            }).bounds(cornerBtnX, cornerBtnY, cornerBtnW, cornerBtnH).build();
                    addWidget(editHouseButton);
                }
                editHouseButton.render(g, rawMouseX, rawMouseY, 0);
            } else if (selectedHouseTab == 1) {
                removeWidgetIfPresent(editHouseButton,   () -> editHouseButton   = null);
                removeWidgetIfPresent(closeInviteButton, () -> closeInviteButton = null);
                if (inviteButton == null) {
                    inviteButton = net.minecraft.client.gui.components.Button.builder(
                            net.minecraft.network.chat.Component.literal("Invite"), btn -> {
                                selectedHouseTab = 2;
                                removeWidget(inviteButton); inviteButton = null;
                                playButtonSound();
                            }).bounds(cornerBtnX, cornerBtnY, cornerBtnW, cornerBtnH).build();
                    addWidget(inviteButton);
                }
                inviteButton.render(g, rawMouseX, rawMouseY, 0);
            } else {
                removeWidgetIfPresent(editHouseButton, () -> editHouseButton = null);
                removeWidgetIfPresent(inviteButton,    () -> inviteButton    = null);
                if (closeInviteButton == null) {
                    closeInviteButton = net.minecraft.client.gui.components.Button.builder(
                            net.minecraft.network.chat.Component.literal("< Back"), btn -> {
                                selectedHouseTab = 1;
                                removeWidget(closeInviteButton); closeInviteButton = null;
                                removeWidget(inviteEditBox);     inviteEditBox     = null;
                                playButtonSound();
                            }).bounds(cornerBtnX, cornerBtnY, cornerBtnW, cornerBtnH).build();
                    addWidget(closeInviteButton);
                }
                closeInviteButton.render(g, rawMouseX, rawMouseY, 0);
            }
        } else {
            removeWidgetIfPresent(editHouseButton,   () -> editHouseButton   = null);
            removeWidgetIfPresent(inviteButton,      () -> inviteButton      = null);
            removeWidgetIfPresent(closeInviteButton, () -> closeInviteButton = null);
        }

        int tabY      = cY + 44;
        int tabH      = 18;
        int tabW      = 80;
        int tabGap    = 4;
        int tabsStart = cX + (cW - (tabW * 2 + tabGap)) / 2;

        drawTab(g, mouseX, mouseY, tabsStart,                 tabY, tabW, tabH, "Towns",   selectedHouseTab == 0);
        drawTab(g, mouseX, mouseY, tabsStart + tabW + tabGap, tabY, tabW, tabH, "Members", selectedHouseTab >= 1);
        g.fill(cX + cW/10, tabY + tabH + 2, cX + cW - cW/10, tabY + tabH + 3, SUBMENU_TEXT_COLOR);

        int contentY = tabY + tabH + 8;
        int contentH = cY + cH - contentY - 10;

        switch (selectedHouseTab) {
            case 0 -> {
                int townsW = (int)(cW * 0.6);
                int townsX = cX + (cW - townsW) / 2;
                drawOwnedTownsSection(g, townsX, contentY, townsW, contentH);
            }
            case 1 -> drawMembersTab(g, mouseX, mouseY, rawMouseX, rawMouseY, cX, contentY, cW, contentH);
            case 2 -> drawInviteTab(g, mouseX, mouseY, rawMouseX, rawMouseY, cX, contentY, cW, contentH);
        }
    }

    private void removeWidgetIfPresent(net.minecraft.client.gui.components.events.GuiEventListener widget,
                                       Runnable nullify) {
        if (widget != null) { removeWidget(widget); nullify.run(); }
    }

    private void drawInviteTab(GuiGraphics g, int mouseX, int mouseY, int rawMouseX, int rawMouseY,
                               int cX, int contentY, int cW, int contentH) {
        int centerX = cX + cW / 2;
        int midY    = contentY + contentH / 2 - 20;
        String label = "Invite a player to your house:";
        g.drawString(font, label, centerX - font.width(label) / 2, midY - 14, SUBMENU_TEXT_COLOR, false);

        int boxW = (int)(cW * 0.45f);
        int boxX = centerX - boxW / 2 - 30;
        int boxY = midY + 2;
        int btnW = 55;
        int btnX = boxX + boxW + 6;

        if (inviteEditBox == null) {
            inviteEditBox = new net.minecraft.client.gui.components.EditBox(
                    font, toScreenX(boxX), toScreenY(boxY), toScreenSize(boxW), toScreenSize(18),
                    net.minecraft.network.chat.Component.literal("Player name"));
            inviteEditBox.setMaxLength(40);
            inviteEditBox.setHint(net.minecraft.network.chat.Component.literal("Player name..."));
            addWidget(inviteEditBox);
        }
        if (inviteEditBox != null) inviteEditBox.render(g, rawMouseX, rawMouseY, 0);

        int sendBtnY = boxY;
        g.fill(btnX, sendBtnY, btnX + btnW, sendBtnY + 18, 0xFF888888);
        g.fill(btnX, sendBtnY, btnX + btnW, sendBtnY + 1, 0xFFCCCCCC);
        g.fill(btnX, sendBtnY, btnX + 1, sendBtnY + 18, 0xFFCCCCCC);
        g.fill(btnX + btnW - 1, sendBtnY, btnX + btnW, sendBtnY + 18, 0xFF444444);
        g.fill(btnX, sendBtnY + 17, btnX + btnW, sendBtnY + 18, 0xFF444444);
        String sendLabel = "Send";
        g.drawString(font, sendLabel, btnX + (btnW - font.width(sendLabel)) / 2,
                sendBtnY + (18 - font.lineHeight) / 2, 0xFFFFFFFF, false);
    }

    private void drawInvitePanel(GuiGraphics g, int mouseX, int mouseY,
                                 int cX, int cY, int cW, int cH) {
        String from  = HouseData.getPendingInviteFrom();
        String by    = HouseData.getPendingInviteBy();
        int centerX  = cX + cW / 2;
        int centerY  = cY + cH / 2;

        String line1 = by + " has invited you to join:";
        String line2 = "House " + from;
        g.drawString(font, line1, centerX - font.width(line1) / 2, centerY - 30, SUBMENU_TEXT_COLOR, false);

        g.pose().pushPose();
        g.pose().scale(1.3f, 1.3f, 1.0f);
        g.drawString(font, line2,
                (int)((centerX - (int)(font.width(line2) * 1.3f) / 2) / 1.3f),
                (int)((centerY - 12) / 1.3f), SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();

        int btnW    = 80, btnH = 20;
        int acceptX = centerX - btnW - 10;
        int rejectX = centerX + 10;
        int btnY    = centerY + 20;
        drawSimpleButton(g, acceptX, btnY, btnW, btnH, "Accept",  0xFF2E7D32);
        drawSimpleButton(g, rejectX, btnY, btnW, btnH, "Decline", 0xFF8B0000);
    }

    private void drawSimpleButton(GuiGraphics g, int x, int y, int w, int h, String label, int color) {
        g.fill(x, y, x + w, y + h, color);
        g.fill(x, y, x + w, y + 1, 0xFFCCCCCC);
        g.fill(x, y, x + 1, y + h, 0xFFCCCCCC);
        g.fill(x + w - 1, y, x + w, y + h, 0xFF333333);
        g.fill(x, y + h - 1, x + w, y + h, 0xFF333333);
        int lw = font.width(label);
        g.drawString(font, label, x + (w - lw) / 2, y + (h - font.lineHeight) / 2, 0xFFFFFFFF, false);
    }

    private void drawMembersTab(GuiGraphics g, int mouseX, int mouseY, int rawMouseX, int rawMouseY,
                                int cX, int contentY, int cW, int contentH) {
        java.util.List<HouseData.MemberEntry> members = HouseData.getHouseMembers();
        boolean founder = HouseData.isFounder();

        if (showingLeaveConfirm) {
            drawConfirmDialog(g, mouseX, mouseY, cX, contentY, cW, contentH,
                    "Are you sure you want to leave this house?");
            return;
        }
        if (kickTargetUUID != null) {
            drawConfirmDialog(g, mouseX, mouseY, cX, contentY, cW, contentH,
                    "Are you sure you want to kick " + kickTargetUsername + "?");
            return;
        }

        if (!founder) {
            int leaveBtnX = toScreenX(cX + cW - 60);
            int leaveBtnY = toScreenY((contentY - 18) - 30);
            if (leaveButton == null) {
                leaveButton = net.minecraft.client.gui.components.Button.builder(
                        net.minecraft.network.chat.Component.literal("Leave"), btn -> {
                            showingLeaveConfirm = true;
                            playButtonSound();
                        }).bounds(leaveBtnX, leaveBtnY, toScreenSize(44), toScreenSize(16)).build();
                addWidget(leaveButton);
            }
            leaveButton.render(g, rawMouseX, rawMouseY, 0);
        } else {
            removeWidgetIfPresent(leaveButton, () -> leaveButton = null);
        }

        int safeLeft  = cX + 90;
        int safeRight = cX + cW - 90;
        int safeW     = safeRight - safeLeft;
        int faceSize  = 16;
        int rowH      = faceSize + 6;
        int listY     = contentY + 10;

        if (members.isEmpty()) {
            String empty = "No members found";
            g.drawString(font, empty, safeLeft + (safeW - font.width(empty)) / 2,
                    listY + 10, 0xFF666666, false);
            return;
        }

        for (int i = 0; i < members.size(); i++) {
            int rowY = listY + (i * rowH);
            if (rowY + rowH > contentY + contentH - 10) break;
            HouseData.MemberEntry entry = members.get(i);
            boolean isSelf = minecraft.player != null && minecraft.player.getUUID().equals(entry.uuid);
            int nameW = font.width(entry.username);
            int rowW  = faceSize + 6 + nameW;
            int rowX  = safeLeft + (safeW - rowW) / 2;

            net.minecraft.resources.ResourceLocation skinTex = null;
            if (minecraft.level != null) {
                for (net.minecraft.client.player.AbstractClientPlayer p : minecraft.level.players()) {
                    if (p.getUUID().equals(entry.uuid)) { skinTex = p.getSkin().texture(); entry.skinTexture = skinTex; break; }
                }
            }
            if (skinTex == null && entry.skinTexture != null) skinTex = entry.skinTexture;
            if (skinTex == null && minecraft.getSkinManager() != null) {
                com.mojang.authlib.GameProfile profile = new com.mojang.authlib.GameProfile(entry.uuid, entry.username);
                minecraft.getSkinManager().getOrLoad(profile).thenAccept(skins -> {
                    net.minecraft.resources.ResourceLocation r = skins.texture();
                    if (r != null) entry.skinTexture = r;
                });
            }

            if (skinTex != null) {
                g.blit(skinTex, rowX, rowY, 8,  8, faceSize, faceSize, 8, 8, 64, 64);
                g.blit(skinTex, rowX, rowY, 40, 8, faceSize, faceSize, 8, 8, 64, 64);
            } else {
                g.fill(rowX, rowY, rowX + faceSize, rowY + faceSize, 0xFF888888);
                g.fill(rowX + 1, rowY + 1, rowX + faceSize - 1, rowY + faceSize - 1, 0xFFAAAAAA);
            }

            g.drawString(font, entry.username, rowX + faceSize + 6,
                    rowY + (faceSize - font.lineHeight) / 2,
                    isSelf ? 0xFF4CAF50 : SUBMENU_TEXT_COLOR, false);

            if (founder && !isSelf) {
                int xBtnSize = 12;
                int xBtnX    = rowX + faceSize + 6 + nameW + 6;
                int xBtnY    = rowY + (faceSize - xBtnSize) / 2;
                g.fill(xBtnX, xBtnY, xBtnX + xBtnSize, xBtnY + xBtnSize, 0xFFAA0000);
                g.fill(xBtnX, xBtnY, xBtnX + xBtnSize, xBtnY + 1,        0xFFCC4444);
                g.fill(xBtnX, xBtnY, xBtnX + 1,        xBtnY + xBtnSize, 0xFFCC4444);
                g.fill(xBtnX + xBtnSize - 1, xBtnY, xBtnX + xBtnSize, xBtnY + xBtnSize, 0xFF660000);
                g.fill(xBtnX, xBtnY + xBtnSize - 1, xBtnX + xBtnSize, xBtnY + xBtnSize, 0xFF660000);
                g.pose().pushPose();
                g.pose().scale(0.7f, 0.7f, 1.0f);
                g.drawString(font, "X",
                        (int)((xBtnX + (xBtnSize - (int)(font.width("X") * 0.7f)) / 2) / 0.7f),
                        (int)((xBtnY + (xBtnSize - (int)(font.lineHeight * 0.7f)) / 2) / 0.7f),
                        0xFFFFFFFF, false);
                g.pose().popPose();
            }
        }
    }

    private void drawConfirmDialog(GuiGraphics g, int mouseX, int mouseY,
                                   int cX, int contentY, int cW, int contentH, String question) {
        g.fill(cX, contentY, cX + cW, contentY + contentH, 0xA0000000);
        int dialogW = 200, dialogH = 70;
        int dialogX = cX + (cW - dialogW) / 2;
        int dialogY = contentY + (contentH - dialogH) / 2;
        g.fill(dialogX, dialogY, dialogX + dialogW, dialogY + dialogH, 0xFF2A2A2A);
        g.fill(dialogX, dialogY, dialogX + dialogW, dialogY + 1, 0xFF888888);
        g.fill(dialogX, dialogY, dialogX + 1, dialogY + dialogH, 0xFF888888);
        g.fill(dialogX + dialogW - 1, dialogY, dialogX + dialogW, dialogY + dialogH, 0xFF444444);
        g.fill(dialogX, dialogY + dialogH - 1, dialogX + dialogW, dialogY + dialogH, 0xFF444444);
        g.pose().pushPose();
        g.pose().scale(0.85f, 0.85f, 1.0f);
        g.drawString(font, question, (int)((dialogX + 10) / 0.85f), (int)((dialogY + 10) / 0.85f), 0xFFFFFFFF, false);
        g.pose().popPose();
        int btnW = 60, btnH = 18;
        int yesX = dialogX + dialogW / 2 - btnW - 6;
        int noX  = dialogX + dialogW / 2 + 6;
        int btnY = dialogY + dialogH - btnH - 8;
        drawSimpleButton(g, yesX, btnY, btnW, btnH, "Yes", 0xFF2E7D32);
        drawSimpleButton(g, noX,  btnY, btnW, btnH, "No",  0xFF8B0000);
    }

    private void drawTab(GuiGraphics g, int mouseX, int mouseY,
                         int x, int y, int w, int h, String label, boolean selected) {
        int bgColor = selected ? 0xFFD4C49A : 0xFF9E8A6A;
        g.fill(x, y, x + w, y + h, bgColor);
        g.fill(x, y,         x + w, y + 1,     0xFF666666);
        g.fill(x, y,         x + 1, y + h,     0xFF666666);
        g.fill(x + w - 1, y, x + w, y + h,     0xFF666666);
        if (!selected) g.fill(x, y + h - 1, x + w, y + h, 0xFF666666);
        int lw = font.width(label);
        g.drawString(font, label, x + (w - lw) / 2, y + (h - font.lineHeight) / 2,
                selected ? SUBMENU_TEXT_COLOR : 0xFF444444, false);
    }

    private void drawHouseCreationPanel(GuiGraphics g, int mouseX, int mouseY,
                                        int cX, int cY, int cW, int cH) {
        // Everything here is in real screen coordinates.
        // The void centre in real screen pixels:
        ScreenLayout layout = new ScreenLayout(width, height);
        int voidCentreX = Math.round((VOID_X + VOID_W / 2f) * layout.scale + layout.offsetX);
        int voidCentreY = Math.round((VOID_Y + VOID_H / 2f) * layout.scale);

        String instruction = house.houseName.isEmpty() ? "Enter your house name:" : "Edit your house name:";

        // Fixed widget sizes in real screen pixels
        int boxW = Math.round(VOID_W * 0.55f * layout.scale);
        int btnW = Math.round(36 * layout.scale);
        int rowH = Math.max(10, Math.round(12 * layout.scale));
        int gap  = Math.round(4 * layout.scale);
        int rowW = boxW + gap + btnW;
        int rowX = voidCentreX - rowW / 2;
        int rowY = voidCentreY;

        // Draw label directly at real screen coords (pop/push pose to draw unscaled)
        g.pose().popPose();
        int labelW = font.width(instruction);
        int labelX = voidCentreX - labelW / 2;
        int labelY = rowY - font.lineHeight - Math.round(4 * layout.scale);
        g.drawString(font, instruction, labelX, labelY, SUBMENU_TEXT_COLOR, false);
        g.pose().pushPose();
        g.pose().translate(VOID_X, VOID_Y, 0);
        g.pose().scale(CONTENT_SCALE, CONTENT_SCALE, 1f);

        if (houseNameEditBox == null) {
            houseNameEditBox = new net.minecraft.client.gui.components.EditBox(
                    font, rowX, rowY, boxW, rowH,
                    net.minecraft.network.chat.Component.literal("House Name"));
            houseNameEditBox.setMaxLength(30);
            houseNameEditBox.setValue(house.houseName);
            addWidget(houseNameEditBox);
        }
        if (saveHouseButton == null) {
            saveHouseButton = net.minecraft.client.gui.components.Button.builder(
                    net.minecraft.network.chat.Component.literal("Save"), button -> {
                        String entered = houseNameEditBox.getValue().trim();
                        if (!entered.isEmpty()) {
                            house.houseName          = entered;
                            house.isEditingHouseName = false;
                            removeWidget(houseNameEditBox); houseNameEditBox = null;
                            removeWidget(saveHouseButton);  saveHouseButton  = null;
                            house.save(minecraft);
                            house.hasRequestedTowns = false;
                            playButtonSound();
                        }
                    }).bounds(rowX + boxW + gap, rowY, btnW, rowH).build();
            addWidget(saveHouseButton);
        }
        if (houseNameEditBox != null) houseNameEditBox.render(g, mouseX, mouseY, 0);
        if (saveHouseButton  != null) saveHouseButton.render(g, mouseX, mouseY, 0);
    }

    private void drawHouseBanner(GuiGraphics g, int x, int y) {
        CompoundTag banner = HouseData.getSyncedHouseBanner();
        if (banner == null) { drawNoBannerPlaceholder(g, x, y); return; }
        try {
            net.minecraft.world.item.ItemStack bannerStack =
                    net.minecraft.world.item.ItemStack.parseOptional(minecraft.level.registryAccess(), banner);
            if (bannerStack.isEmpty() || !(bannerStack.getItem() instanceof net.minecraft.world.item.BannerItem)) {
                drawErrorBannerPlaceholder(g, x, y, "Invalid Banner"); return;
            }
            net.minecraft.world.item.BannerItem bannerItem = (net.minecraft.world.item.BannerItem) bannerStack.getItem();
            net.minecraft.world.item.DyeColor baseColor = bannerItem.getColor();
            net.minecraft.world.level.block.entity.BannerPatternLayers patterns =
                    bannerStack.get(net.minecraft.core.component.DataComponents.BANNER_PATTERNS);
            int bW = 60, bH = 120;
            com.mojang.blaze3d.systems.RenderSystem.enableBlend();
            com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc();
            g.pose().pushPose();
            drawTextureLayer(g, x, y, bW, bH, BANNER_BASE_TEXTURE, baseColor);
            if (patterns != null) {
                for (net.minecraft.world.level.block.entity.BannerPatternLayers.Layer layer : patterns.layers())
                    drawPatternLayer(g, x, y, bW, bH, layer);
            }
            g.pose().popPose();
            com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            com.mojang.blaze3d.systems.RenderSystem.disableBlend();
        } catch (Exception e) {
            drawErrorBannerPlaceholder(g, x, y, "Render Error");
        }
    }

    private void drawTextureLayer(GuiGraphics g, int x, int y, int w, int h,
                                  ResourceLocation texture, net.minecraft.world.item.DyeColor color) {
        int dyeColor = color.getTextureDiffuseColor();
        float red   = ((dyeColor >> 16) & 0xFF) / 255.0F;
        float green = ((dyeColor >>  8) & 0xFF) / 255.0F;
        float blue  =  (dyeColor        & 0xFF) / 255.0F;
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(red, green, blue, 1f);
        g.blit(texture, x, y, 1, 0, w, h, 64, 64);
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
    }

    private void drawPatternLayer(GuiGraphics g, int x, int y, int w, int h,
                                  net.minecraft.world.level.block.entity.BannerPatternLayers.Layer layer) {
        ResourceLocation id  = layer.pattern().value().assetId();
        ResourceLocation tex = BANNER_PATTERN_TEXTURES.getOrDefault(id.getPath(),
                ResourceLocation.fromNamespaceAndPath("minecraft",
                        "textures/entity/banner/" + id.getPath() + ".png"));
        drawTextureLayer(g, x, y, w, h, tex, layer.color());
    }

    private void drawNoBannerPlaceholder(GuiGraphics g, int x, int y) {
        g.fill(x, y, x + 60, y + 120, 0xFF8B4513);
        g.fill(x + 1, y + 1, x + 59, y + 119, 0xFFD2B48C);
    }

    private void drawErrorBannerPlaceholder(GuiGraphics g, int x, int y, String errorText) {
        g.fill(x, y, x + 60, y + 120, 0xFF8B0000);
        g.fill(x + 1, y + 1, x + 59, y + 119, 0xFFFF4500);
    }

    private void drawOwnedTownsSection(GuiGraphics g, int x, int y, int w, int h) {
        String title = "Owned Towns";
        int titleW = (int)(font.width(title) * 1.1f);
        g.pose().pushPose();
        g.pose().scale(1.1f, 1.1f, 1.0f);
        g.drawString(font, title,
                (int)((x + (w - titleW) / 2) / 1.1f), (int)(y / 1.1f),
                SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();
        g.fill(x + w/8, y + (int)(font.lineHeight * 1.1f) + 5,
                x + w - w/8, y + (int)(font.lineHeight * 1.1f) + 6, SUBMENU_TEXT_COLOR);

        int listY = y + (int)(font.lineHeight * 1.1f) + 15;
        java.util.List<SyncOwnedTownsPacket.TownInfo> towns = HouseData.getOwnedTowns();

        if (towns.isEmpty()) {
            String empty = "No towns claimed";
            g.drawString(font, empty, x + (w - font.width(empty)) / 2, listY + 20, 0xFF666666, false);
            return;
        }

        int lh = font.lineHeight + 6;
        for (int i = 0; i < towns.size(); i++) {
            int rowY = listY + (i * lh * 2);
            if (rowY + lh * 2 > y + h) break;
            SyncOwnedTownsPacket.TownInfo town = towns.get(i);
            String townText = town.townName() + " — Population: " + town.population();
            int color = town.population() >= 100 ? 0xFF2E7D32
                    : town.population() >= 50    ? 0xFF1565C0
                    : town.population() >= 20    ? 0xFF6A4C93
                    : SUBMENU_TEXT_COLOR;
            g.drawString(font, townText, x + (w - font.width(townText)) / 2, rowY, color, false);
            String ownerText = town.ownerString();
            if (ownerText != null && !ownerText.isEmpty()) {
                g.pose().pushPose();
                g.pose().scale(0.85f, 0.85f, 1.0f);
                g.drawString(font, ownerText,
                        (int)((x + (w - (int)(font.width(ownerText) * 0.85f)) / 2) / 0.85f),
                        (int)((rowY + font.lineHeight + 2) / 0.85f), 0xFF888888, false);
                g.pose().popPose();
            }
        }

        if (towns.size() > 1) {
            int total     = towns.stream().mapToInt(SyncOwnedTownsPacket.TownInfo::population).sum();
            String totalText = "Total Population: " + total;
            int footerY   = listY + towns.size() * lh * 2 + 5;
            g.fill(x + w/6, footerY, x + w - w/6, footerY + 1, SUBMENU_TEXT_COLOR);
            g.pose().pushPose();
            g.pose().scale(0.9f, 0.9f, 1.0f);
            g.drawString(font, totalText,
                    (int)((x + (w - (int)(font.width(totalText) * 0.9f)) / 2) / 0.9f),
                    (int)((footerY + 5) / 0.9f), 0xFF1A237E, false);
            g.pose().popPose();
        }
    }

    // ── Panel rendering helpers ───────────────────────────────────────────────

    private void renderStonePanel(GuiGraphics g, int x, int y, int w, int h) {
        g.flush();
        int tile = 16;
        for (int tx = 0; tx < w; tx += tile)
            for (int ty = 0; ty < h; ty += tile)
                g.blit(STONE_TEXTURE, x+tx, y+ty, 0, 0, Math.min(tile, w-tx), Math.min(tile, h-ty), tile, tile);
        drawPanelBorders(g, x, y, w, h);
    }

    private void renderPaperPanel(GuiGraphics g, int x, int y, int w, int h) {
        g.flush();
        int bpw = BORDER_PILLAR_WIDTH * 2, bh = BORDER_HEIGHT * 2, cs = CORNER_SIZE * 2;
        int ix = x + bpw, iy = y + bh, iw = w - bpw*2, ih = h - bh*2;
        int tile = 256;
        for (int tx = 0; tx < iw; tx += tile)
            for (int ty = 0; ty < ih; ty += tile)
                g.blit(PAPER_TEXTURE, ix+tx, iy+ty, 0, 0, Math.min(tile, iw-tx), Math.min(tile, ih-ty), tile, tile);
        drawRotatableBorder(g, PAPER_SIDE_TEXTURE,    x,             y + cs,          bpw,        h - cs*2, 0);
        drawRotatableBorder(g, PAPER_SIDE_TEXTURE,    x + w - bpw,  y + cs,          bpw,        h - cs*2, 180);
        drawRotatableBorder(g, PAPER_SIDETOP_TEXTURE, x + cs,       y,               w - cs*2,   bh,       0);
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1f);
        drawRotatableBorder(g, PAPER_SIDETOP_TEXTURE, x + cs,       y + h - bh,      w - cs*2,   bh,       180);
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x,         y,         cs, cs, 0);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x + w - cs, y,         cs, cs, 90);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x + w - cs, y + h - cs, cs, cs, 180);
        drawRotatableCorner(g, PAPER_CORNER_TEXTURE, x,         y + h - cs, cs, cs, 270);
    }

    private void renderTexturedPanel(GuiGraphics g, ResourceLocation tex, int x, int y, int w, int h, boolean borders) {
        g.flush();
        g.blit(tex, x, y, 0, 0, w, h, 32, 32);
        if (borders) drawPanelBorders(g, x, y, w, h);
    }

    private void drawPanelBorders(GuiGraphics g, int x, int y, int w, int h) {
        g.blit(PILLAR_TEXTURE, x,             y, 0, 0, BORDER_PILLAR_WIDTH, h, 16, 64);
        g.blit(PILLAR_TEXTURE, x + w - BORDER_PILLAR_WIDTH, y, 0, 0, BORDER_PILLAR_WIDTH, h, 16, 64);
        g.blit(BORDER_TEXTURE, x, y,         0, 0, w, BORDER_HEIGHT, 64, 16);
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1f);
        g.blit(BORDER_TEXTURE, x, y + h - BORDER_HEIGHT, 0, 0, w, BORDER_HEIGHT, 64, 16);
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        drawCorners(g, x, y, w, h);
    }

    private void drawCorners(GuiGraphics g, int x, int y, int w, int h) {
        com.mojang.blaze3d.vertex.PoseStack ps = g.pose();
        ps.pushPose();
        g.blit(CORNER_TEXTURE, x, y + h - BORDER_HEIGHT - CORNER_SIZE / 2, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        ps.popPose();
        ps.pushPose();
        ps.translate(x + w - CORNER_SIZE / 2, y + h - BORDER_HEIGHT, 0);
        ps.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(270));
        ps.translate(-CORNER_SIZE / 2.0, -CORNER_SIZE / 2.0, 0);
        g.blit(CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        ps.popPose();
        ps.pushPose();
        ps.translate(x + w - CORNER_SIZE / 2, y + CORNER_SIZE / 2, 0);
        ps.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(180));
        ps.translate(-CORNER_SIZE / 2.0, -CORNER_SIZE / 2.0, 0);
        g.blit(CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        ps.popPose();
        ps.pushPose();
        ps.translate(x + CORNER_SIZE / 2, y + CORNER_SIZE / 2, 0);
        ps.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(90));
        ps.translate(-CORNER_SIZE / 2.0, -CORNER_SIZE / 2.0, 0);
        g.blit(CORNER_TEXTURE, 0, 0, 0, 0, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE, CORNER_SIZE);
        ps.popPose();
    }

    private void drawRotatableCorner(GuiGraphics g, ResourceLocation tex, int x, int y, int w, int h, float deg) {
        g.pose().pushPose();
        if (deg != 0) {
            g.pose().translate(x + w / 2.0, y + h / 2.0, 0);
            g.pose().mulPose(com.mojang.math.Axis.ZP.rotationDegrees(deg));
            g.pose().translate(-w / 2.0, -h / 2.0, 0);
            g.blit(tex, 0, 0, 0, 0, w, h, 32, 32);
        } else {
            g.blit(tex, x, y, 0, 0, w, h, 32, 32);
        }
        g.pose().popPose();
    }

    private void drawRotatableBorder(GuiGraphics g, ResourceLocation tex, int x, int y, int w, int h, float deg) {
        g.pose().pushPose();
        g.pose().translate(x + w / 2.0, y + h / 2.0, 0);
        g.pose().mulPose(com.mojang.math.Axis.ZP.rotationDegrees(deg));
        g.pose().translate(-w / 2.0, -h / 2.0, 0);
        int tile = 32;
        for (int tx = 0; tx < w; tx += tile)
            for (int ty = 0; ty < h; ty += tile) {
                int tw = Math.min(tile, w - tx), th = Math.min(tile, h - ty);
                if (tw > 0 && th > 0) g.blit(tex, tx, ty, 0, 0, tw, th, tile, tile);
            }
        g.pose().popPose();
    }

    // ── Shared draw helpers ───────────────────────────────────────────────────

    private void drawSectionTitle(GuiGraphics g, String title, int x, int y, int w) {
        float scale = 1.5f;
        int tw = (int)(font.width(title) * scale);
        g.pose().pushPose();
        g.pose().scale(scale, scale, 1.0f);
        g.drawString(font, title,
                (int)((x + (w - tw) / 2) / scale),
                (int)((y + 12) / scale),
                SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();
    }

    private void drawSubmenuTitle(GuiGraphics g, String title, int x, int y) {
        float scale = 1.2f;
        g.pose().pushPose();
        g.pose().scale(scale, scale, 1.0f);
        g.drawString(font, title, (int)(x / scale), (int)((y + 18) / scale), SUBMENU_TEXT_COLOR, false);
        g.pose().popPose();
    }

    private boolean isMouseOverRect(int mx, int my, int x, int y, int w, int h) {
        return mx >= x && mx < x + w && my >= y && my < y + h;
    }

    /**
     * Convert a content-local X coordinate to a real screen X coordinate.
     * Used to position Minecraft Button/EditBox widgets that ignore the pose matrix.
     */
    private int toScreenX(int contentLocalX) {
        ScreenLayout layout = new ScreenLayout(width, height);
        return Math.round((VOID_X + contentLocalX * CONTENT_SCALE) * layout.scale + layout.offsetX);
    }

    /**
     * Convert a content-local Y coordinate to a real screen Y coordinate.
     */
    private int toScreenY(int contentLocalY) {
        ScreenLayout layout = new ScreenLayout(width, height);
        return Math.round((VOID_Y + contentLocalY * CONTENT_SCALE) * layout.scale);
    }

    /**
     * Convert a content-local size (width or height) to real screen pixels.
     */
    private int toScreenSize(int contentLocalSize) {
        ScreenLayout layout = new ScreenLayout(width, height);
        return Math.round(contentLocalSize * CONTENT_SCALE * layout.scale);
    }

    private void playButtonSound() {
        if (minecraft != null && minecraft.player != null) {
            minecraft.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(ModSounds.BUTTON, 1.0F));
        }
    }

    // ── Layout ────────────────────────────────────────────────────────────────

    /**
     * Computes a letterboxed / pillarboxed 16:9 canvas that fits inside the
     * real Minecraft window (width × height).
     *
     * All coordinates used by the draw methods are expressed in the virtual
     * BASE_GUI_WIDTH × BASE_GUI_HEIGHT space.  The render method pushes a
     * pose that maps from virtual → real pixels before any drawing happens,
     * so every sub-method can use simple integer arithmetic without caring
     * about the actual window size.
     *
     * Mouse coordinates are inverse-transformed in render() before being
     * passed to sub-methods, so hit-testing also works correctly.
     */
    private class ScreenLayout {

        final float scale;    // screenH / BASE_GUI_HEIGHT — nothing else
        final float offsetX;  // centres canvas horizontally
        final float offsetY = 0f;

        final int contentX, contentY, contentWidth, contentHeight;

        ScreenLayout(int screenW, int screenH) {
            scale   = (float) screenH / TEXTURE_HEIGHT;
            offsetX = (screenW - TEXTURE_WIDTH * scale) / 2f;

            // Content coords are relative to the void origin (0,0 after translate).
            // Width/height are in the unscaled virtual space the section renderers use.
            contentX      = 0;
            contentY      = 0;
            contentWidth  = (int)(VOID_W / CONTENT_SCALE);
            contentHeight = (int)(VOID_H / CONTENT_SCALE);
        }
    }
}