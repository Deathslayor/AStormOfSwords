package net.darkflameproduction.agotmod.gui;

import dev.tocraft.ctgen.impl.CTGClient;
import dev.tocraft.ctgen.impl.network.SyncMapPacket;
import dev.tocraft.ctgen.impl.screen.widget.MapWidget;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.network.*;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundClientCommandPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stats;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatsCounter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Map;


@OnlyIn(Dist.CLIENT)
public class CustomGuiScreen extends Screen {
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

    private static final ResourceLocation PILLAR_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/pillar.png");
    private static final ResourceLocation BORDER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/bottom.png");
    private static final ResourceLocation CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/corner.png");
    private static final ResourceLocation STONE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/background.png");
    private static final ResourceLocation PAPER_SIDE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paperside.png");
    private static final ResourceLocation PAPER_SIDETOP_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papersidetop.png");
    private static final ResourceLocation PAPER_CORNER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/papercorner.png");
    private static final ResourceLocation PAPER_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/gui/paper.png");

    private static final String[] SECTION_LABELS = {
            "Map", "Quests", "Skills", "Stats", "House", "Faction", "Guilds"
    };

    private static final String[] STAT_SUBMENU_LABELS = {
            "General Stats", "Combat Stats", "Crime", "Magic", "Weapon Usage", "Tool Usage"
    };

    private static final String[] GENERAL_SKILLS = {
            "Agility", "Strength", "Endurance"
    };

    private static final int[] RAINBOW_COLORS = {
            0xFF990000, 0xFF994C00, 0xFF999900, 0xFF009900,
            0xFF000099, 0xFF2D0050, 0xFF57007F
    };

    private static final int SUBMENU_TEXT_COLOR = 0xFF000000;
    private static final int PARCHMENT_COLOR = 0xFFF5E7C1;

    private static final Stat<ResourceLocation> DAMAGE_DEALT_STAT = Stats.CUSTOM.get(Stats.DAMAGE_DEALT);
    private static final Stat<ResourceLocation> DAMAGE_TAKEN_STAT = Stats.CUSTOM.get(Stats.DAMAGE_TAKEN);
    private static final Stat<ResourceLocation> DEATH_COUNT_STAT = Stats.CUSTOM.get(Stats.DEATHS);
    private static final Stat<ResourceLocation> KILLS_MOB_STAT = Stats.CUSTOM.get(Stats.MOB_KILLS);
    private static final Stat<ResourceLocation> PLAYER_KILLS_STAT = Stats.CUSTOM.get(Stats.PLAYER_KILLS);
    private static final Stat<ResourceLocation> JUMP_STAT = Stats.CUSTOM.get(Stats.JUMP);

    private static final Stat<ResourceLocation>[] DISTANCE_STATS = new Stat[]{
            Stats.CUSTOM.get(Stats.CLIMB_ONE_CM),
            Stats.CUSTOM.get(Stats.CROUCH_ONE_CM),
            Stats.CUSTOM.get(Stats.FALL_ONE_CM),
            Stats.CUSTOM.get(Stats.FLY_ONE_CM),
            Stats.CUSTOM.get(Stats.SPRINT_ONE_CM),
            Stats.CUSTOM.get(Stats.SWIM_ONE_CM),
            Stats.CUSTOM.get(Stats.WALK_ONE_CM),
            Stats.CUSTOM.get(Stats.WALK_ON_WATER_ONE_CM),
            Stats.CUSTOM.get(Stats.WALK_UNDER_WATER_ONE_CM),
            Stats.CUSTOM.get(Stats.BOAT_ONE_CM),
            Stats.CUSTOM.get(Stats.AVIATE_ONE_CM),
            Stats.CUSTOM.get(Stats.HORSE_ONE_CM),
            Stats.CUSTOM.get(Stats.MINECART_ONE_CM),
            Stats.CUSTOM.get(Stats.PIG_ONE_CM),
            Stats.CUSTOM.get(Stats.STRIDER_ONE_CM)
    };

    private static final Stat<ResourceLocation>[] WEAPON_STATS = new Stat[]{
            Stats.ITEM_USED.get(Items.WOODEN_SWORD),
            Stats.ITEM_USED.get(Items.STONE_SWORD),
            Stats.ITEM_USED.get(Items.IRON_SWORD),
            Stats.ITEM_USED.get(Items.GOLDEN_SWORD),
            Stats.ITEM_USED.get(Items.DIAMOND_SWORD),
            Stats.ITEM_USED.get(Items.NETHERITE_SWORD),
            Stats.ITEM_USED.get(Items.BOW),
            Stats.ITEM_USED.get(Items.CROSSBOW),
            Stats.ITEM_USED.get(Items.TRIDENT),
            Stats.ITEM_USED.get(ModItems.BRONZE_SWORD.get()),
            Stats.ITEM_USED.get(ModItems.DRAGONGLASS_DAGGER.get()),
            Stats.ITEM_USED.get(ModItems.DRAGONGLASS_SPEAR.get()),
            Stats.ITEM_USED.get(ModItems.BRONZE_SPATHA.get()),
            Stats.ITEM_USED.get(ModItems.BRONZE_SPEAR.get()),
            Stats.ITEM_USED.get(ModItems.BRONZE_PIKE.get()),
            Stats.ITEM_USED.get(ModItems.BRONZE_BATTLEAXE.get()),
            Stats.ITEM_USED.get(ModItems.BRONZE_DAGGER.get()),
            Stats.ITEM_USED.get(ModItems.IRON_BATTLEAXE.get()),
            Stats.ITEM_USED.get(ModItems.IRON_LONGSWORD.get()),
            Stats.ITEM_USED.get(ModItems.IRON_DAGGER.get()),
            Stats.ITEM_USED.get(ModItems.IRON_MACE.get()),
            Stats.ITEM_USED.get(ModItems.IRON_PIKE.get()),
            Stats.ITEM_USED.get(ModItems.IRON_SPEAR.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_LONGSWORD.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_SPEAR.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_PIKE.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_MACE.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_DAGGER.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_BATTLEAXE.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_HALBERD.get()),
            Stats.ITEM_USED.get(ModItems.NOBLE_LONGSWORD.get()),
            Stats.ITEM_USED.get(ModItems.NOBLE_SPEAR.get()),
            Stats.ITEM_USED.get(ModItems.NOBLE_PIKE.get()),
            Stats.ITEM_USED.get(ModItems.NOBLE_MACE.get()),
            Stats.ITEM_USED.get(ModItems.NOBLE_DAGGER.get()),
            Stats.ITEM_USED.get(ModItems.NOBLE_BATTLEAXE.get()),
            Stats.ITEM_USED.get(ModItems.NOBLE_HALBERD.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_BOW.get()),
            Stats.ITEM_USED.get(ModItems.STEEL_SWORD.get()),


    };

    private static final String[] WEAPON_NAMES = new String[]{
            "Wooden Sword",
            "Stone Sword",
            "Iron Sword",
            "Golden Sword",
            "Diamond Sword",
            "Netherite Sword",
            "Bow",
            "Crossbow",
            "Trident",
            "Bronze Sword",
            "Dragonglass Dagger",
            "Dragonglass Spear",
            "Bronze Spatha",
            "Bronze Spear",
            "Bronze Pike",
            "Bronze Battleaxe",
            "Bronze Dagger",
            "Iron Battleaxe",
            "Iron Longsword",
            "Iron Dagger",
            "Iron Mace",
            "Iron Pike",
            "Iron Spear",
            "Steel Longsword",
            "Steel Spear",
            "Steel Pike",
            "Steel Mace",
            "Steel Dagger",
            "Steel Battleaxe",
            "Steel Halberd",
            "Noble Longsword",
            "Noble Spear",
            "Noble Pike",
            "Noble Mace",
            "Noble Dagger",
            "Noble Battleaxe",
            "Noble Halberd",
            "Steel Bow",
            "Steel Sword"

    };

    private static final int BORDER_PILLAR_WIDTH = 6;
    private static final int BORDER_HEIGHT = 6;
    private static final int CORNER_SIZE = 12;

    private static final double AGILITY_LEVEL_MULTIPLIER = 1.085;
    private static final int MAX_AGILITY_LEVEL = 100;
    private static final double STRENGTH_LEVEL_MULTIPLIER = 1.06;
    private static final int MAX_STRENGTH_LEVEL = 100;
    private static final double ENDURANCE_LEVEL_MULTIPLIER = 1.08;
    private static final int MAX_ENDURANCE_LEVEL = 100;
    private static final double WEAPON_SKILL_MULTIPLIER = 1.04;
    private static final int MAX_WEAPON_SKILL_LEVEL = 100;
    private static final double WEAPON_SKILL_BASE_REQUIREMENT = 100.0;
    private double lastPlayerX = 0;
    private double lastPlayerY = 0;
    private double lastPlayerZ = 0;
    private boolean hasInitializedPosition = false;
    private static final double TELEPORT_THRESHOLD = 5.0;

    private boolean isUsingShader = false;
    private float lastPlayerHealth = 0;
    private static int lastSelectedSection = 2;
    private int selectedSection;
    private static int lastSelectedStatsSubmenu = 0;
    private int selectedStatsSubmenu;
    private static final int STATS_UPDATE_INTERVAL = 100;
    private int statsUpdateTimer = 0;
    private boolean hasRequestedInitialStats = false;
    private int[] cachedWeaponUsages = new int[WEAPON_STATS.length];
    private String favoriteWeapon = "None";
    private int favoriteWeaponUses = 0;

    private int cachedDeaths = 0;
    private int cachedDamageDealt= 0;
    private int cachedDamageTaken= 0;
    private int cachedMobKills = 0;
    private int cachedPlayerKills = 0;
    private int cachedJumps = 0;
    private double cachedTotalDistance = 0.0;
    private boolean areStatsLoaded = false;

    private int agilityLevel = 0;
    private double agilityProgress = 0.0;
    private double nextLevelDistance = 0.0;

    private int strengthLevel = 0;
    private double strengthProgress = 0.0;
    private double nextStrengthLevelRequirement = 0.0;

    private int enduranceLevel = 0;
    private double enduranceProgress = 0.0;
    private double nextEnduranceLevelRequirement = 0.0;

    private int oneHandedLevel = 0;
    private double oneHandedProgress = 0.0;
    private double nextOneHandedRequirement = 0.0;

    private int twoHandedLevel = 0;
    private double twoHandedProgress = 0.0;
    private double nextTwoHandedRequirement = 0.0;

    private int polearmLevel = 0;
    private double polearmProgress = 0.0;
    private double nextPolearmRequirement = 0.0;

    private int shortBladeLevel = 0;
    private double shortBladeProgress = 0.0;
    private double nextShortBladeRequirement = 0.0;

    private int rangedLevel = 0;
    private double rangedProgress = 0.0;
    private double nextRangedRequirement = 0.0;

    private final SyncMapPacket mapPacket;
    private MapWidget mapWidget;

    private static final int[] ONE_HANDED_STATS_INDICES = {0, 1, 2, 3, 4, 5, 9, 12, 15, 17, 18, 20, 23, 27, 28, 29, 30, 35, 36, 38};
    private static final int[] TWO_HANDED_STATS_INDICES = {18, 23, 30};
    private static final int[] POLEARM_STATS_INDICES = {8, 11, 13, 14, 21, 22, 24, 25, 29, 31, 36, 37};
    private static final int[] SHORT_BLADE_STATS_INDICES = {10, 16, 19, 27, 34};
    private static final int[] RANGED_STATS_INDICES = {6, 7, 37};

    private String houseName = "";
    private boolean isEditingHouseName = false;
    private net.minecraft.client.gui.components.EditBox houseNameEditBox;
    private net.minecraft.client.gui.components.Button saveHouseButton;
    private net.minecraft.client.gui.components.Button editHouseButton;
    private static java.util.List<SyncOwnedTownsPacket.TownInfo> ownedTowns = new java.util.ArrayList<>();
    private boolean hasRequestedTowns = false;
    private boolean isValidatingHouseName = false;
    private static CustomGuiScreen currentInstance = null;

    private static String syncedHouseName = "";
    private static CompoundTag syncedHouseBanner = null;


    private static final ResourceLocation BANNER_BASE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png");

    // Banner pattern texture locations - these should match your pattern names
    private static final Map<String, ResourceLocation> BANNER_PATTERN_TEXTURES = createBannerPatternMap();

    private static Map<String, ResourceLocation> createBannerPatternMap() {
        Map<String, ResourceLocation> map = new java.util.HashMap<>();
        map.put("base", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner_base.png"));

        // All Game of Thrones house banner patterns
        map.put("arryn", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/arryn.png"));
        map.put("baelish", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/baelish.png"));
        map.put("baratheon", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/baratheon.png"));
        map.put("blackwood", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/blackwood.png"));
        map.put("bolton", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/bolton.png"));
        map.put("bracken", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/bracken.png"));
        map.put("clegane", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/clegane.png"));
        map.put("dayne", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/dayne.png"));
        map.put("frey", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/frey.png"));
        map.put("greyjoy", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/greyjoy.png"));
        map.put("harlaw", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/harlaw.png"));
        map.put("hightower", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/hightower.png"));
        map.put("karstark", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/karstark.png"));
        map.put("lannister", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/lannister.png"));
        map.put("manderly", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/manderly.png"));
        map.put("mormont", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/mormont.png"));
        map.put("redwyne", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/redwyne.png"));
        map.put("reed", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/reed.png"));
        map.put("reyne", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/reyne.png"));
        map.put("royce", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/royce.png"));
        map.put("stark", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/stark.png"));
        map.put("targaryen", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/targaryen.png"));
        map.put("tarly", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/tarly.png"));
        map.put("tully", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/tully.png"));
        map.put("tyrell", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/tyrell.png"));
        map.put("umber", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/umber.png"));
        map.put("velaryon", ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/banner/velaryon.png"));

        // Vanilla Minecraft patterns
        map.put("stripe_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_bottom.png"));
        map.put("stripe_top", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_top.png"));
        map.put("stripe_left", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_left.png"));
        map.put("stripe_right", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_right.png"));
        map.put("stripe_center", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_center.png"));
        map.put("stripe_middle", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/stripe_middle.png"));
        map.put("cross", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/cross.png"));
        map.put("straight_cross", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/straight_cross.png"));
        map.put("diagonal_left", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/diagonal_left.png"));
        map.put("diagonal_right", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/diagonal_right.png"));
        map.put("half_vertical", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_vertical.png"));
        map.put("half_horizontal", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_horizontal.png"));
        map.put("half_vertical_right", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_vertical_right.png"));
        map.put("half_horizontal_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/half_horizontal_bottom.png"));
        map.put("circle", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/circle.png"));
        map.put("border", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/border.png"));
        map.put("triangle_top", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/triangle_top.png"));
        map.put("triangle_bottom", ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/triangle_bottom.png"));

        return java.util.Collections.unmodifiableMap(map);
    }



    private int getCategoryUsage(int[] indices) {
        int total = 0;
        for (int index : indices) {
            if (index < cachedWeaponUsages.length) {
                total += cachedWeaponUsages[index];
            }
        }
        return total;
    }

    private int getOneHandedUsage() {
        return getCategoryUsage(ONE_HANDED_STATS_INDICES);
    }

    private int getTwoHandedUsage() {
        return getCategoryUsage(TWO_HANDED_STATS_INDICES);
    }

    private int getPolearmUsage() {
        return getCategoryUsage(POLEARM_STATS_INDICES);
    }

    private int getShortBladeUsage() {
        return getCategoryUsage(SHORT_BLADE_STATS_INDICES);
    }

    private int getRangedUsage() {
        return getCategoryUsage(RANGED_STATS_INDICES);
    }

    public CustomGuiScreen(Minecraft minecraft) {
        this(minecraft, CTGClient.LAST_SYNC_MAP_PACKET.get());
    }

    public CustomGuiScreen(Minecraft minecraft, SyncMapPacket mapPacket) {
        super(Component.translatable("screen.agotmod.custom_gui"));
        this.selectedSection = lastSelectedSection;
        this.selectedStatsSubmenu = lastSelectedStatsSubmenu;
        this.mapPacket = mapPacket;
        this.mapWidget = MapWidget.ofPacket(minecraft, 0, 0, width, height, mapPacket);
        currentInstance = this; // Track current instance
    }

    @Override
    public void onClose() {
        super.onClose();
        if (currentInstance == this) {
            currentInstance = null;
        }
    }

    private void calculateWeaponSkillLevels() {
        calculateOneHandedLevel();
        calculateTwoHandedLevel();
        calculatePolearmLevel();
        calculateShortBladeLevel();
        calculateRangedLevel();
    }

    private void calculateOneHandedLevel() {
        int usage = getOneHandedUsage();
        if (usage <= 0) {
            oneHandedLevel = 0;
            oneHandedProgress = 0.0;
            nextOneHandedRequirement = WEAPON_SKILL_BASE_REQUIREMENT;
            return;
        }

        double totalRequired = 0;
        double currentRequirement = WEAPON_SKILL_BASE_REQUIREMENT;

        for (int level = 1; level <= MAX_WEAPON_SKILL_LEVEL; level++) {
            totalRequired += currentRequirement;

            if (usage < totalRequired) {
                oneHandedLevel = level - 1;
                double previousLevelRequirement = totalRequired - currentRequirement;
                oneHandedProgress = (usage - previousLevelRequirement) / currentRequirement;
                nextOneHandedRequirement = currentRequirement;
                return;
            }

            currentRequirement *= WEAPON_SKILL_MULTIPLIER;
        }

        oneHandedLevel = MAX_WEAPON_SKILL_LEVEL;
        oneHandedProgress = 1.0;
        nextOneHandedRequirement = 0;
    }

    private void calculateTwoHandedLevel() {
        int usage = getTwoHandedUsage();
        if (usage <= 0) {
            twoHandedLevel = 0;
            twoHandedProgress = 0.0;
            nextTwoHandedRequirement = WEAPON_SKILL_BASE_REQUIREMENT;
            return;
        }

        double totalRequired = 0;
        double currentRequirement = WEAPON_SKILL_BASE_REQUIREMENT;

        for (int level = 1; level <= MAX_WEAPON_SKILL_LEVEL; level++) {
            totalRequired += currentRequirement;

            if (usage < totalRequired) {
                twoHandedLevel = level - 1;
                double previousLevelRequirement = totalRequired - currentRequirement;
                twoHandedProgress = (usage - previousLevelRequirement) / currentRequirement;
                nextTwoHandedRequirement = currentRequirement;
                return;
            }

            currentRequirement *= WEAPON_SKILL_MULTIPLIER;
        }

        twoHandedLevel = MAX_WEAPON_SKILL_LEVEL;
        twoHandedProgress = 1.0;
        nextTwoHandedRequirement = 0;
    }

    private void calculatePolearmLevel() {
        int usage = getPolearmUsage();
        if (usage <= 0) {
            polearmLevel = 0;
            polearmProgress = 0.0;
            nextPolearmRequirement = WEAPON_SKILL_BASE_REQUIREMENT;
            return;
        }

        double totalRequired = 0;
        double currentRequirement = WEAPON_SKILL_BASE_REQUIREMENT;

        for (int level = 1; level <= MAX_WEAPON_SKILL_LEVEL; level++) {
            totalRequired += currentRequirement;

            if (usage < totalRequired) {
                polearmLevel = level - 1;
                double previousLevelRequirement = totalRequired - currentRequirement;
                polearmProgress = (usage - previousLevelRequirement) / currentRequirement;
                nextPolearmRequirement = currentRequirement;
                return;
            }

            currentRequirement *= WEAPON_SKILL_MULTIPLIER;
        }

        polearmLevel = MAX_WEAPON_SKILL_LEVEL;
        polearmProgress = 1.0;
        nextPolearmRequirement = 0;
    }

    private void calculateShortBladeLevel() {
        int usage = getShortBladeUsage();
        if (usage <= 0) {
            shortBladeLevel = 0;
            shortBladeProgress = 0.0;
            nextShortBladeRequirement = WEAPON_SKILL_BASE_REQUIREMENT;
            return;
        }

        double totalRequired = 0;
        double currentRequirement = WEAPON_SKILL_BASE_REQUIREMENT;

        for (int level = 1; level <= MAX_WEAPON_SKILL_LEVEL; level++) {
            totalRequired += currentRequirement;

            if (usage < totalRequired) {
                shortBladeLevel = level - 1;
                double previousLevelRequirement = totalRequired - currentRequirement;
                shortBladeProgress = (usage - previousLevelRequirement) / currentRequirement;
                nextShortBladeRequirement = currentRequirement;
                return;
            }

            currentRequirement *= WEAPON_SKILL_MULTIPLIER;
        }

        shortBladeLevel = MAX_WEAPON_SKILL_LEVEL;
        shortBladeProgress = 1.0;
        nextShortBladeRequirement = 0;
    }

    private void calculateRangedLevel() {
        int usage = getRangedUsage();
        if (usage <= 0) {
            rangedLevel = 0;
            rangedProgress = 0.0;
            nextRangedRequirement = WEAPON_SKILL_BASE_REQUIREMENT;
            return;
        }

        double totalRequired = 0;
        double currentRequirement = WEAPON_SKILL_BASE_REQUIREMENT;

        for (int level = 1; level <= MAX_WEAPON_SKILL_LEVEL; level++) {
            totalRequired += currentRequirement;

            if (usage < totalRequired) {
                rangedLevel = level - 1;
                double previousLevelRequirement = totalRequired - currentRequirement;
                rangedProgress = (usage - previousLevelRequirement) / currentRequirement;
                nextRangedRequirement = currentRequirement;
                return;
            }

            currentRequirement *= WEAPON_SKILL_MULTIPLIER;
        }

        rangedLevel = MAX_WEAPON_SKILL_LEVEL;
        rangedProgress = 1.0;
        nextRangedRequirement = 0;
    }

    private interface SkillValueSetter {
        void setValues(int[] values);
    }

    private void calculateSkillLevel(int usage, int maxLevel, double multiplier, double baseRequirement, SkillValueSetter setter) {
        if (usage <= 0) {
            setter.setValues(new int[]{0, 0, (int)baseRequirement});
            return;
        }

        double totalRequired = 0;
        double currentRequirement = baseRequirement;

        for (int level = 1; level <= maxLevel; level++) {
            totalRequired += currentRequirement;

            if (usage < totalRequired) {
                int levelValue = level - 1;
                double previousLevelRequirement = totalRequired - currentRequirement;
                double progressValue = (usage - previousLevelRequirement) / currentRequirement;
                setter.setValues(new int[]{levelValue, (int)(progressValue * 100) / 100, (int)currentRequirement});
                return;
            }

            currentRequirement *= multiplier;
        }

        setter.setValues(new int[]{maxLevel, 1, 0});
    }

    @Override
    protected void init() {
        super.init();

        currentInstance = this; // Ensure current instance is set

        // Reset the towns request flag when GUI is opened
        hasRequestedTowns = false;

        if (minecraft != null && minecraft.player != null) {
            lastPlayerHealth = minecraft.player.getHealth();
            requestStatisticsFromServer();

            // Initialize position tracking
            lastPlayerX = minecraft.player.getX();
            lastPlayerY = minecraft.player.getY();
            lastPlayerZ = minecraft.player.getZ();
            hasInitializedPosition = true;

            // Load house name from player data
            loadHouseNameFromPlayerData();
        }

        // Clear any existing house widgets when reopening
        if (houseNameEditBox != null) {
            removeWidget(houseNameEditBox);
            houseNameEditBox = null;
        }
        if (saveHouseButton != null) {
            removeWidget(saveHouseButton);
            saveHouseButton = null;
        }
        if (editHouseButton != null) {
            removeWidget(editHouseButton);
            editHouseButton = null;
        }

        applyBlurEffect();
    }

    @Override
    public void tick() {
        super.tick();

        if (minecraft == null || minecraft.player == null) {
            return;
        }

        if (hasInitializedPosition) {
            double currentX = minecraft.player.getX();
            double currentY = minecraft.player.getY();
            double currentZ = minecraft.player.getZ();

            double deltaX = currentX - lastPlayerX;
            double deltaY = currentY - lastPlayerY;
            double deltaZ = currentZ - lastPlayerZ;
            double distanceMoved = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

            if (distanceMoved > TELEPORT_THRESHOLD) {
                AGoTMod.LOGGER.info("Player teleported, closing GUI");
                minecraft.setScreen(null);
                return;
            }

            lastPlayerX = currentX;
            lastPlayerY = currentY;
            lastPlayerZ = currentZ;
        }

        if (!hasRequestedInitialStats) {
            requestStatisticsFromServer();
            hasRequestedInitialStats = true;
            return;
        }

        statsUpdateTimer++;
        if (statsUpdateTimer >= STATS_UPDATE_INTERVAL) {
            requestStatisticsFromServer();
            statsUpdateTimer = 0;
        }
        updatePlayerSkillData();
    }

    private void requestStatisticsFromServer() {
        if (minecraft == null || minecraft.player == null || minecraft.getConnection() == null) {
            return;
        }

        try {
            minecraft.getConnection().send(new ServerboundClientCommandPacket(
                    ServerboundClientCommandPacket.Action.REQUEST_STATS));

            AGoTMod.LOGGER.info("Requested player statistics from server");

            updateCachedStatistics(minecraft.player.getStats());
            areStatsLoaded = true;
        } catch (Exception e) {
            AGoTMod.LOGGER.error("Failed to request statistics from server: " + e.getMessage());
            updateCachedStatistics(minecraft.player.getStats());
        }
    }

    private void loadPlayerStatistics() {
        if (minecraft == null || minecraft.player == null) {
            return;
        }

        StatsCounter playerStats = minecraft.player.getStats();

        if (!areStatsLoaded) {
            requestStatisticsFromServer();
        } else {
            updateCachedStatistics(playerStats);
        }
    }

    private void updateCachedStatistics(StatsCounter stats) {
        if (stats == null) {
            return;
        }

        cachedDeaths = stats.getValue(DEATH_COUNT_STAT);
        cachedDamageDealt = stats.getValue(DAMAGE_DEALT_STAT);
        cachedDamageTaken = stats.getValue(DAMAGE_TAKEN_STAT);
        cachedMobKills = stats.getValue(KILLS_MOB_STAT);
        cachedPlayerKills = stats.getValue(PLAYER_KILLS_STAT);
        cachedJumps = stats.getValue(JUMP_STAT);

        long totalDistanceCentimeters = 0;
        for (Stat<ResourceLocation> distanceStat : DISTANCE_STATS) {
            totalDistanceCentimeters += stats.getValue(distanceStat);
        }

        cachedTotalDistance = totalDistanceCentimeters / 100000.0;
        calculateAgilityLevel();
        calculateStrengthLevel();
        calculateEnduranceLevel();

        favoriteWeapon = "None";
        favoriteWeaponUses = 0;

        for (int i = 0; i < WEAPON_STATS.length; i++) {
            int uses = stats.getValue(WEAPON_STATS[i]);
            cachedWeaponUsages[i] = uses;

            if (uses > favoriteWeaponUses) {
                favoriteWeaponUses = uses;
                favoriteWeapon = WEAPON_NAMES[i];
            }
        }
        calculateWeaponSkillLevels();
    }


    private void calculateAgilityLevel() {
        if (cachedTotalDistance <= 0) {
            agilityLevel = 0;
            agilityProgress = 0;
            nextLevelDistance = 1.0;
            return;
        }

        double totalRequiredDistance = 0;
        double currentLevelRequirement = 1.0;

        for (int level = 1; level <= MAX_AGILITY_LEVEL; level++) {
            totalRequiredDistance += currentLevelRequirement;

            if (cachedTotalDistance < totalRequiredDistance) {
                agilityLevel = level - 1;

                double previousLevelDistance = totalRequiredDistance - currentLevelRequirement;
                agilityProgress = (cachedTotalDistance - previousLevelDistance) / currentLevelRequirement;
                nextLevelDistance = currentLevelRequirement;
                return;
            }

            currentLevelRequirement *= AGILITY_LEVEL_MULTIPLIER;
        }

        agilityLevel = MAX_AGILITY_LEVEL;
        agilityProgress = 1.0;
        nextLevelDistance = 0;
    }

    private void calculateStrengthLevel() {
        if (cachedDamageDealt <= 0) {
            strengthLevel = 0;
            strengthProgress = 0;
            nextStrengthLevelRequirement = 1000.0;
            return;
        }

        double damageDealtDisplay = cachedDamageDealt / 10.0;

        double totalRequiredDamage = 0;
        double currentLevelRequirement = 1000.0;

        for (int level = 1; level <= MAX_STRENGTH_LEVEL; level++) {
            totalRequiredDamage += currentLevelRequirement;

            if (damageDealtDisplay < totalRequiredDamage) {
                strengthLevel = level - 1;

                double previousLevelDamage = totalRequiredDamage - currentLevelRequirement;
                strengthProgress = (damageDealtDisplay - previousLevelDamage) / currentLevelRequirement;
                nextStrengthLevelRequirement = currentLevelRequirement;
                return;
            }

            currentLevelRequirement *= STRENGTH_LEVEL_MULTIPLIER;
        }

        strengthLevel = MAX_STRENGTH_LEVEL;
        strengthProgress = 1.0;
        nextStrengthLevelRequirement = 0;
    }

    private void calculateEnduranceLevel() {
        if (cachedDamageTaken <= 0) {
            enduranceLevel = 0;
            enduranceProgress = 0;
            nextEnduranceLevelRequirement = 100.0;
            return;
        }

        double damageTakenDisplay = cachedDamageTaken / 10.0;

        double totalRequiredDamage = 0;
        double currentLevelRequirement = 100.0;

        for (int level = 1; level <= MAX_ENDURANCE_LEVEL; level++) {
            totalRequiredDamage += currentLevelRequirement;

            if (damageTakenDisplay < totalRequiredDamage) {
                enduranceLevel = level - 1;

                double previousLevelDamage = totalRequiredDamage - currentLevelRequirement;
                enduranceProgress = (damageTakenDisplay - previousLevelDamage) / currentLevelRequirement;
                nextEnduranceLevelRequirement = currentLevelRequirement;
                return;
            }

            currentLevelRequirement *= ENDURANCE_LEVEL_MULTIPLIER;
        }

        enduranceLevel = MAX_ENDURANCE_LEVEL;
        enduranceProgress = 1.0;
        nextEnduranceLevelRequirement = 0;
    }

    private void applyBlurEffect() {
        if (minecraft == null) {
            return;
        }

        try {
            if (minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class) != null) {
                minecraft.gameRenderer.getClass().getMethod("loadPostShader", ResourceLocation.class)
                        .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                isUsingShader = true;
            }
        } catch (Exception e1) {
            try {
                if (minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class) != null) {
                    minecraft.gameRenderer.getClass().getMethod("loadPostPass", ResourceLocation.class)
                            .invoke(minecraft.gameRenderer, BLUR_LOCATION);
                    isUsingShader = true;
                }
            } catch (Exception e2) {
                AGoTMod.LOGGER.error("Failed to apply blur shader: " + e2.getMessage());
            }
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (mapWidget != null && mapWidget.isActive() && mapWidget.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }

        ScreenLayout layout = new ScreenLayout(width, height);

        for (int i = 0; i < SECTION_LABELS.length; i++) {
            int startX = layout.sideMargin + (i * layout.sectionButtonWidth);
            int endX = (i == SECTION_LABELS.length - 1)
                    ? width - layout.sideMargin
                    : layout.sideMargin + ((i + 1) * layout.sectionButtonWidth);

            if (isMouseOverRect((int) mouseX, (int) mouseY, startX, layout.topMargin,
                    endX - startX, layout.sectionButtonHeight)) {

                selectedSection = i;
                lastSelectedSection = i;

                if (i == 3 && !areStatsLoaded && minecraft != null) {
                    requestStatisticsFromServer();
                }

                playButtonSound();
                return true;
            }
        }

        if (selectedSection == 3) {
            int submenuWidth = layout.contentWidth / 6;
            int submenuStartX = layout.contentX + layout.contentWidth / 128;
            int submenuStartY = layout.contentY + layout.contentHeight / 16;
            int submenuHeight = layout.contentHeight - (layout.contentHeight / 8);

            int buttonHeight = submenuHeight / 8;
            int buttonSpacing = submenuHeight / 24;
            int totalButtonsHeight = (buttonHeight * STAT_SUBMENU_LABELS.length) +
                    (buttonSpacing * (STAT_SUBMENU_LABELS.length - 1));
            int submenuStartYCentered = submenuStartY + (submenuHeight - totalButtonsHeight) / 2;

            for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
                int buttonY = submenuStartYCentered + (i * (buttonHeight + buttonSpacing));

                int buttonRightOffset = 10;
                if (isMouseOverRect((int) mouseX, (int) mouseY, submenuStartX + buttonRightOffset, buttonY,
                        submenuWidth - buttonRightOffset, buttonHeight)) {

                    selectedStatsSubmenu = i;
                    lastSelectedStatsSubmenu = i;

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
        } else {
            return super.mouseScrolled(mouseX, mouseY, deltaX, deltaY);
        }
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (mapWidget != null && selectedSection == 0 &&
                mouseX >= mapWidget.getX() && mouseX < mapWidget.getX() + mapWidget.getWidth() &&
                mouseY >= mapWidget.getY() && mouseY < mapWidget.getY() + mapWidget.getHeight()) {
            return mapWidget.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        } else {
            return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }
    }

    private void playButtonSound() {
        if (minecraft != null && minecraft.player != null) {
            minecraft.getSoundManager().play(
                    net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                            ModSounds.BUTTON, 1.0F
                    )
            );
        }
    }

    private void updatePlayerSkillData() {
        if (minecraft == null || minecraft.player == null) {
            return;
        }

        // Store skill levels in player's persistent data
        CompoundTag persistentData = minecraft.player.getPersistentData();
        CompoundTag skillsTag = new CompoundTag();

        skillsTag.putInt("one_handed_level", oneHandedLevel);
        skillsTag.putInt("two_handed_level", twoHandedLevel);
        skillsTag.putInt("polearm_level", polearmLevel);
        skillsTag.putInt("short_blade_level", shortBladeLevel);
        skillsTag.putInt("ranged_level", rangedLevel);

        persistentData.put(AGoTMod.MOD_ID + ".skills", skillsTag);

        // Also save house name here
        CompoundTag houseTag = new CompoundTag();
        houseTag.putString("house_name", houseName);
        persistentData.put(AGoTMod.MOD_ID + ".house", houseTag);
    }

    private boolean isMouseOverRect(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX < x + width && mouseY >= y && mouseY < y + height;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (minecraft != null && minecraft.player != null) {
            float currentHealth = minecraft.player.getHealth();

            if (currentHealth < lastPlayerHealth) {
                AGoTMod.LOGGER.info("Player health decreased, closing GUI");
                minecraft.setScreen(null);
                return;
            }

            lastPlayerHealth = currentHealth;
            updateCachedStatistics(minecraft.player.getStats());

        }

        guiGraphics.fill(0, 0, width, height, 0x40E6D8B7);

        ScreenLayout layout = new ScreenLayout(width, height);

        drawSectionButtons(guiGraphics, mouseX, mouseY, layout);

        if (selectedSection >= 0 && selectedSection < RAINBOW_COLORS.length) {
            renderStonePanel(guiGraphics, layout.contentX, layout.contentY,
                    layout.contentWidth, layout.contentHeight);

            drawSectionTitle(guiGraphics, SECTION_LABELS[selectedSection],
                    layout.contentX, layout.contentY, layout.contentWidth);

            switch (selectedSection) {
                case 0:
                    drawMapSection(guiGraphics, mouseX, mouseY, layout, partialTick);
                    break;
                case 2:
                    drawSkillsSection(guiGraphics, mouseX, mouseY, layout);
                    break;
                case 3:
                    drawStatsSection(guiGraphics, mouseX, mouseY, layout);
                    break;
                case 4:
                    drawHouseSection(guiGraphics, mouseX, mouseY, layout);
                    break;
            }
        }
    }

    private void drawSectionButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout) {
        for (int i = 0; i < SECTION_LABELS.length; i++) {
            int startX = layout.sideMargin + (i * layout.sectionButtonWidth);
            int endX = (i == SECTION_LABELS.length - 1)
                    ? width - layout.sideMargin
                    : layout.sideMargin + ((i + 1) * layout.sectionButtonWidth);

            boolean isHovered = isMouseOverRect(mouseX, mouseY, startX, layout.topMargin,
                    endX - startX, layout.sectionButtonHeight);
            boolean isSelected = (i == selectedSection);

            int buttonWidth = endX - startX;

            ResourceLocation texture = (isHovered || isSelected)
                    ? STAINED_GLASS_TRANSPARENT_TEXTURES[i]
                    : STAINED_GLASS_TEXTURES[i];

            renderTexturedPanel(guiGraphics, texture, startX, layout.topMargin,
                    buttonWidth, layout.sectionButtonHeight, true);

            String text = SECTION_LABELS[i];
            int textWidth = font.width(text);
            int textX = startX + (buttonWidth - textWidth) / 2;
            int textY = layout.topMargin + (layout.sectionButtonHeight - font.lineHeight) / 2;

            guiGraphics.drawString(font, text, textX, textY,
                    isSelected ? 0xFFFFFFFF : PARCHMENT_COLOR);
        }
    }

    private void drawMapSection(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout, float partialTick) {
        int borderPillarWidth = BORDER_PILLAR_WIDTH;
        int borderHeight = BORDER_HEIGHT;
        int cornerSize = CORNER_SIZE;

        int innerX = layout.contentX + borderPillarWidth;
        int innerY = layout.contentY + borderHeight;
        int innerWidth = layout.contentWidth - (borderPillarWidth * 2);
        int innerHeight = layout.contentHeight - (borderHeight * 2);

        mapWidget.setX(innerX);
        mapWidget.setY(innerY);
        mapWidget.setWidth(innerWidth);
        mapWidget.setHeight(innerHeight);
        mapWidget.setMinZoom(mapWidget.defaultZoom());

        if (mapWidget != null && mapWidget.isActive() && mapWidget.getMapId() != null &&
                minecraft.getResourceManager().getResource(mapWidget.getMapId()).isPresent()) {
            mapWidget.render(guiGraphics, mouseX, mouseY, partialTick);
        }

    }

    private void drawSkillsSection(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout) {
        int panelGap = 2;

        int panelWidth = (int) (layout.contentWidth * 0.45);

        int totalWidth = (panelWidth * 2) + panelGap;
        int startX = layout.contentX + ((layout.contentWidth - totalWidth) / 2);

        int leftPanelX = startX;
        int rightPanelX = leftPanelX + panelWidth + panelGap;

        int panelY = layout.contentY + layout.contentHeight / 16;
        int panelHeight = layout.contentHeight - (layout.contentHeight / 8);

        renderPaperPanel(guiGraphics, leftPanelX, panelY, panelWidth, panelHeight);

        String leftTitle = "General Skills";
        int leftTitleWidth = font.width(leftTitle);
        int leftTitleX = leftPanelX + (panelWidth - leftTitleWidth) / 2;
        drawSubmenuTitle(guiGraphics, leftTitle, leftTitleX, panelY);

        renderPaperPanel(guiGraphics, rightPanelX, panelY, panelWidth, panelHeight);

        String rightTitle = "Specification Skills";
        int rightTitleWidth = font.width(rightTitle);
        int rightTitleX = rightPanelX + (panelWidth - rightTitleWidth) / 2;
        drawSubmenuTitle(guiGraphics, rightTitle, rightTitleX, panelY);

        // Changed from 3 columns to 2 columns
        int columnWidth = panelWidth / 2;
        int headerY = panelY + layout.contentHeight / 10 + 10;

        float scale = 0.9f;

        String col1Title = "Skill Level";
        String col2Title = "Progress";

        // Left panel header
        int col1CenterX = leftPanelX + columnWidth / 2;
        int col2CenterX = leftPanelX + columnWidth + columnWidth / 2;

        int col1Width = (int)(font.width(col1Title) * scale);
        int col2Width = (int)(font.width(col2Title) * scale);

        int col1X = col1CenterX - col1Width / 2;
        int col2X = col2CenterX - col2Width / 2;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        guiGraphics.drawString(font, col1Title, (int)(col1X / scale), (int)(headerY / scale), SUBMENU_TEXT_COLOR, false);
        guiGraphics.drawString(font, col2Title, (int)(col2X / scale), (int)(headerY / scale), SUBMENU_TEXT_COLOR, false);

        guiGraphics.pose().popPose();

        guiGraphics.fill(
                leftPanelX + panelWidth/10,  // Start 1/10 of the way in
                headerY + (int)(font.lineHeight * scale) + 2,
                leftPanelX + panelWidth - panelWidth/10,  // End 1/10 of the way from the right
                headerY + (int)(font.lineHeight * scale) + 3,
                SUBMENU_TEXT_COLOR
        );

        int statsY = headerY + (int)(font.lineHeight * scale) + 10;
        int lineSpacing = layout.contentHeight / 18;

        drawAgilitySkill2Col(guiGraphics, leftPanelX, statsY, columnWidth, scale);
        drawStrengthSkill2Col(guiGraphics, leftPanelX, statsY + lineSpacing, columnWidth, scale);
        drawEnduranceSkill2Col(guiGraphics, leftPanelX, statsY + lineSpacing * 2, columnWidth, scale);

        // Right panel header
        int rightCol1CenterX = rightPanelX + columnWidth / 2;
        int rightCol2CenterX = rightPanelX + columnWidth + columnWidth / 2;

        int rightCol1X = rightCol1CenterX - col1Width / 2;
        int rightCol2X = rightCol2CenterX - col2Width / 2;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        guiGraphics.drawString(font, col1Title, (int)(rightCol1X / scale), (int)(headerY / scale), SUBMENU_TEXT_COLOR, false);
        guiGraphics.drawString(font, col2Title, (int)(rightCol2X / scale), (int)(headerY / scale), SUBMENU_TEXT_COLOR, false);

        guiGraphics.pose().popPose();

        guiGraphics.fill(
                rightPanelX + panelWidth/10,  // Start 1/10 of the way in
                headerY + (int)(font.lineHeight * scale) + 2,
                rightPanelX + panelWidth - panelWidth/10,  // End 1/10 of the way from the right
                headerY + (int)(font.lineHeight * scale) + 3,
                SUBMENU_TEXT_COLOR
        );

        int rightStatsY = statsY;

        drawWeaponSkill2Col(guiGraphics, "One Handed", oneHandedLevel, oneHandedProgress, nextOneHandedRequirement,
                rightPanelX, rightStatsY, columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(guiGraphics, "Two Handed", twoHandedLevel, twoHandedProgress, nextTwoHandedRequirement,
                rightPanelX, rightStatsY + lineSpacing, columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(guiGraphics, "Polearms", polearmLevel, polearmProgress, nextPolearmRequirement,
                rightPanelX, rightStatsY + lineSpacing * 2, columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(guiGraphics, "Short Blade", shortBladeLevel, shortBladeProgress, nextShortBladeRequirement,
                rightPanelX, rightStatsY + lineSpacing * 3, columnWidth, scale, RAINBOW_COLORS[0]);
        drawWeaponSkill2Col(guiGraphics, "Ranged", rangedLevel, rangedProgress, nextRangedRequirement,
                rightPanelX, rightStatsY + lineSpacing * 4, columnWidth, scale, RAINBOW_COLORS[0]);
    }

    private void drawWeaponSkill2Col(GuiGraphics guiGraphics, String skillName, int level, double progress,
                                     double nextLevelRequirement, int x, int y, int columnWidth, float scale,
                                     int color) {
        int barHeight = 6;
        int maxBarWidth = columnWidth - 25;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        // First column - Skill name and level
        String levelText = skillName + ": " + level;
        int levelTextWidth = (int)(font.width(levelText) * scale);
        int col1CenterX = x + columnWidth / 2;
        float scaledX = (col1CenterX - levelTextWidth / (2 * scale)) / scale;
        float scaledY = y / scale;
        guiGraphics.drawString(font, levelText, (int)scaledX, (int)scaledY, SUBMENU_TEXT_COLOR, false);

        guiGraphics.pose().popPose();

        // Second column - Progress bar with percentage
        int barX = x + columnWidth + 10;
        int barWidth = maxBarWidth - 20; // Leave room for percentage text

        int textCenterY = y + (int)((font.lineHeight * scale) / 2);
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + barWidth, textCenterY + barHeight/2,
                0xFF555555);

        int progressBarWidth = (int) (barWidth * progress);
        int barColor = level < MAX_WEAPON_SKILL_LEVEL ? color : RAINBOW_COLORS[4];
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + progressBarWidth, textCenterY + barHeight/2,
                barColor);

        // Draw percentage or "Max Level" after the bar
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        String progressText;
        if (level < MAX_WEAPON_SKILL_LEVEL) {
            // Show percentage instead of raw values
            progressText = String.format("%.0f%%", progress * 100);
        } else {
            progressText = "Max";
        }

        int progressTextWidth = (int)(font.width(progressText) * scale);
        float percentX = (barX + barWidth + 5) / scale;

        int textColor = level < MAX_WEAPON_SKILL_LEVEL ? SUBMENU_TEXT_COLOR : 0xFF55FF55;
        guiGraphics.drawString(font, progressText, (int)percentX, (int)scaledY, textColor, false);

        guiGraphics.pose().popPose();
    }

    private void drawAgilitySkill2Col(GuiGraphics guiGraphics, int x, int y, int columnWidth, float scale) {
        int barHeight = 6;
        int maxBarWidth = columnWidth - 25;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        // First column - Skill name and level
        String levelText = "Agility: " + agilityLevel;
        int levelTextWidth = (int)(font.width(levelText) * scale);
        int col1CenterX = x + columnWidth / 2;
        float scaledX = (col1CenterX - levelTextWidth / (2 * scale)) / scale;
        float scaledY = y / scale;
        guiGraphics.drawString(font, levelText, (int)scaledX, (int)scaledY, SUBMENU_TEXT_COLOR, false);

        guiGraphics.pose().popPose();

        // Second column - Progress bar with percentage
        int barX = x + columnWidth + 10;
        int barWidth = maxBarWidth - 20; // Leave room for percentage text

        int textCenterY = y + (int)((font.lineHeight * scale) / 2);
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + barWidth, textCenterY + barHeight/2,
                0xFF555555);

        int progressBarWidth = (int) (barWidth * agilityProgress);
        int barColor = agilityLevel < MAX_AGILITY_LEVEL ? RAINBOW_COLORS[0] : RAINBOW_COLORS[4];
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + progressBarWidth, textCenterY + barHeight/2,
                barColor);

        // Draw percentage or "Max Level" after the bar
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        String progressText;
        if (agilityLevel < MAX_AGILITY_LEVEL) {
            // Show percentage instead of raw values
            progressText = String.format("%.0f%%", agilityProgress * 100);
        } else {
            progressText = "Max";
        }

        int progressTextWidth = (int)(font.width(progressText) * scale);
        float percentX = (barX + barWidth + 5) / scale;

        int textColor = agilityLevel < MAX_AGILITY_LEVEL ? SUBMENU_TEXT_COLOR : 0xFF55FF55;
        guiGraphics.drawString(font, progressText, (int)percentX, (int)scaledY, textColor, false);

        guiGraphics.pose().popPose();
    }

    private void drawStrengthSkill2Col(GuiGraphics guiGraphics, int x, int y, int columnWidth, float scale) {
        int barHeight = 6;
        int maxBarWidth = columnWidth - 25;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        // First column - Skill name and level
        String levelText = "Strength: " + strengthLevel;
        int levelTextWidth = (int)(font.width(levelText) * scale);
        int col1CenterX = x + columnWidth / 2;
        float scaledX = (col1CenterX - levelTextWidth / (2 * scale)) / scale;
        float scaledY = y / scale;
        guiGraphics.drawString(font, levelText, (int)scaledX, (int)scaledY, SUBMENU_TEXT_COLOR, false);

        guiGraphics.pose().popPose();

        // Second column - Progress bar with percentage
        int barX = x + columnWidth + 10;
        int barWidth = maxBarWidth - 20; // Leave room for percentage text

        int textCenterY = y + (int)((font.lineHeight * scale) / 2);
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + barWidth, textCenterY + barHeight/2,
                0xFF555555);

        int progressBarWidth = (int) (barWidth * strengthProgress);
        int barColor = strengthLevel < MAX_STRENGTH_LEVEL ? RAINBOW_COLORS[0] : RAINBOW_COLORS[4];
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + progressBarWidth, textCenterY + barHeight/2,
                barColor);

        // Draw percentage or "Max Level" after the bar
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        String progressText;
        if (strengthLevel < MAX_STRENGTH_LEVEL) {
            // Show percentage instead of raw values
            progressText = String.format("%.0f%%", strengthProgress * 100);
        } else {
            progressText = "Max";
        }

        int progressTextWidth = (int)(font.width(progressText) * scale);
        float percentX = (barX + barWidth + 5) / scale;

        int textColor = strengthLevel < MAX_STRENGTH_LEVEL ? SUBMENU_TEXT_COLOR : 0xFF55FF55;
        guiGraphics.drawString(font, progressText, (int)percentX, (int)scaledY, textColor, false);

        guiGraphics.pose().popPose();
    }

    private void drawEnduranceSkill2Col(GuiGraphics guiGraphics, int x, int y, int columnWidth, float scale) {
        int barHeight = 6;
        int maxBarWidth = columnWidth - 25;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        // First column - Skill name and level
        String levelText = "Endurance: " + enduranceLevel;
        int levelTextWidth = (int)(font.width(levelText) * scale);
        int col1CenterX = x + columnWidth / 2;
        float scaledX = (col1CenterX - levelTextWidth / (2 * scale)) / scale;
        float scaledY = y / scale;
        guiGraphics.drawString(font, levelText, (int)scaledX, (int)scaledY, SUBMENU_TEXT_COLOR, false);

        guiGraphics.pose().popPose();

        // Second column - Progress bar with percentage
        int barX = x + columnWidth + 10;
        int barWidth = maxBarWidth - 20; // Leave room for percentage text

        int textCenterY = y + (int)((font.lineHeight * scale) / 2);
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + barWidth, textCenterY + barHeight/2,
                0xFF555555);

        int progressBarWidth = (int) (barWidth * enduranceProgress);
        int barColor = enduranceLevel < MAX_ENDURANCE_LEVEL ? RAINBOW_COLORS[0] : RAINBOW_COLORS[4];
        guiGraphics.fill(barX, textCenterY - barHeight/2,
                barX + progressBarWidth, textCenterY + barHeight/2,
                barColor);

        // Draw percentage or "Max Level" after the bar
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);

        String progressText;
        if (enduranceLevel < MAX_ENDURANCE_LEVEL) {
            // Show percentage instead of raw values
            progressText = String.format("%.0f%%", enduranceProgress * 100);
        } else {
            progressText = "Max";
        }

        int progressTextWidth = (int)(font.width(progressText) * scale);
        float percentX = (barX + barWidth + 5) / scale;

        int textColor = enduranceLevel < MAX_ENDURANCE_LEVEL ? SUBMENU_TEXT_COLOR : 0xFF55FF55;
        guiGraphics.drawString(font, progressText, (int)percentX, (int)scaledY, textColor, false);

        guiGraphics.pose().popPose();
    }


    private void drawStatsSection(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout) {
        int submenuWidth = layout.contentWidth / 6;
        int submenuStartX = layout.contentX + layout.contentWidth / 128;
        int submenuStartY = layout.contentY + layout.contentHeight / 16;
        int submenuHeight = layout.contentHeight - (layout.contentHeight / 8);

        int buttonHeight = submenuHeight / 8;
        int buttonSpacing = submenuHeight / 24;
        int totalButtonsHeight = (buttonHeight * STAT_SUBMENU_LABELS.length) +
                (buttonSpacing * (STAT_SUBMENU_LABELS.length - 1));
        int submenuStartYCentered = submenuStartY + (submenuHeight - totalButtonsHeight) / 2;

        // Calculate original content dimensions
        int originalContentStartX = submenuStartX + submenuWidth + layout.contentWidth / 32;
        int originalContentStartY = submenuStartY;
        int originalContentWidth = layout.contentWidth - submenuWidth - (layout.contentWidth / 16);
        int originalContentHeight = submenuHeight;

        // Calculate center points for scaling
        int contentCenterX = originalContentStartX + originalContentWidth / 2;
        int contentCenterY = originalContentStartY + originalContentHeight / 2;

        // Apply 75% scaling
        int contentWidth = (int)(originalContentWidth * 0.75);
        int contentHeight = (int)(originalContentHeight * 0.75);

        // Recalculate start positions to maintain centering
        int contentStartX = contentCenterX - contentWidth / 2;
        int contentStartY = contentCenterY - contentHeight / 2;

        for (int i = 0; i < STAT_SUBMENU_LABELS.length; i++) {
            int buttonY = submenuStartYCentered + (i * (buttonHeight + buttonSpacing));

            boolean isHovered = isMouseOverRect(mouseX, mouseY, submenuStartX, buttonY,
                    submenuWidth, buttonHeight);
            boolean isSelected = (i == selectedStatsSubmenu);

            ResourceLocation buttonTexture = (isHovered || isSelected)
                    ? STAINED_GLASS_TRANSPARENT_TEXTURES[3]
                    : STAINED_GLASS_TEXTURES[3];

            int buttonRightOffset = 10;
            renderTexturedPanel(guiGraphics, buttonTexture, submenuStartX + buttonRightOffset, buttonY,
                    submenuWidth - buttonRightOffset, buttonHeight, true);

            String buttonText = STAT_SUBMENU_LABELS[i];
            int buttonTextWidth = font.width(buttonText);

            int buttonTextX = submenuStartX + buttonRightOffset + ((submenuWidth - buttonRightOffset - buttonTextWidth) / 2);

            int textHeight = font.lineHeight;
            int buttonTextY = buttonY + (buttonHeight - textHeight) / 2;

            guiGraphics.drawString(font, buttonText, buttonTextX, buttonTextY,
                    isSelected ? 0xFFFFFFFF : SUBMENU_TEXT_COLOR, false); // Changed from PARCHMENT_COLOR to black
        }

        renderPaperPanel(guiGraphics, contentStartX, contentStartY,
                contentWidth, contentHeight);

        String submenuTitle = STAT_SUBMENU_LABELS[selectedStatsSubmenu];

        int titleWidth = (int)(font.width(submenuTitle) * 1.2f);
        int titleX = contentStartX + (contentWidth - titleWidth) / 2;
        drawSubmenuTitle(guiGraphics, submenuTitle, titleX, contentStartY);

        // Adjust stat positions proportionally to the new content size
        int statsX = contentStartX + contentWidth / 10;
        int statsY = contentStartY + (int)(layout.contentHeight / 12 * 0.75) + 15; // Adjusted for smaller size
        int lineSpacing = (int)(layout.contentHeight / 16 * 0.75); // Scale line spacing

        drawSubmenuContent(guiGraphics, statsX, statsY, lineSpacing, contentStartX, contentWidth);
    }

    private void drawSubmenuContent(GuiGraphics guiGraphics, int x, int y, int lineSpacing, int contentStartX, int contentWidth) {
        switch (selectedStatsSubmenu) {
            case 0:
                drawStat(guiGraphics, "Deaths", cachedDeaths, x, y, contentStartX, contentWidth);
                drawStat(guiGraphics, "Jumps", cachedJumps, x, y + lineSpacing, contentStartX, contentWidth);
                drawDistanceStat(guiGraphics, "Distance Traveled", cachedTotalDistance, x, y + lineSpacing * 2, contentStartX, contentWidth);
                break;

            case 1:
                drawStat(guiGraphics, "Mob Kills", cachedMobKills, x, y, contentStartX, contentWidth);
                drawStat(guiGraphics, "Player Kills", cachedPlayerKills, x, y + lineSpacing, contentStartX, contentWidth);
                drawStat(guiGraphics,"Damage Dealt", cachedDamageDealt, x, y + lineSpacing * 2, contentStartX, contentWidth);
                drawStat(guiGraphics,"Damage Taken", cachedDamageTaken, x, y + lineSpacing * 3, contentStartX, contentWidth);

                break;

            case 2:
                drawStat(guiGraphics, "Items Stolen", 0, x, y, contentStartX, contentWidth);
                drawStat(guiGraphics, "Bounty", 0, x, y + lineSpacing, contentStartX, contentWidth);
                break;

            case 3:
                drawStat(guiGraphics, "Spells Cast", 0, x, y, contentStartX, contentWidth);
                drawStat(guiGraphics, "Magic Level", 0, x, y + lineSpacing, contentStartX, contentWidth);
                break;

            case 4:
                drawStat(guiGraphics, "One Handed Usage", getOneHandedUsage(), x, y, contentStartX, contentWidth);
                drawStat(guiGraphics, "Two Handed Usage", getTwoHandedUsage(), x, y + lineSpacing, contentStartX, contentWidth);
                drawStat(guiGraphics, "Polearm Usage", getPolearmUsage(), x, y + lineSpacing * 2, contentStartX, contentWidth);
                drawStat(guiGraphics, "Short Blade Usage", getShortBladeUsage(), x, y + lineSpacing * 3, contentStartX, contentWidth);
                drawStat(guiGraphics, "Ranged Usage", getRangedUsage(), x, y + lineSpacing * 4, contentStartX, contentWidth);
                drawFavoriteWeapon(guiGraphics, x, y + lineSpacing * 5, contentStartX, contentWidth);
                break;

            case 5:
                drawStat(guiGraphics, "Blocks Mined", 0, x, y, contentStartX, contentWidth);
                drawStat(guiGraphics, "Items Crafted", 0, x, y + lineSpacing, contentStartX, contentWidth);
                break;
        }
    }

    private void drawSectionTitle(GuiGraphics guiGraphics, String title, int x, int width, int y) {
        int titleWidth = (int) (font.width(title) * 1.5f);
        int titleX = x + (width - titleWidth) / 2;
        int titleY = y + 12;

        float titleScale = 1.5f;
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(titleScale, titleScale, 1.0f);
        guiGraphics.drawString(font, title,
                (int) (titleX / titleScale),
                (int) (titleY / titleScale),
                SUBMENU_TEXT_COLOR, false);
        guiGraphics.pose().popPose();
    }

    private void drawSubmenuTitle(GuiGraphics guiGraphics, String title, int x, int y) {
        float scale = 1.2f;
        int titleY = y + 18;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, 1.0f);
        guiGraphics.drawString(font, title,
                (int) (x / scale),
                (int) (titleY / scale),
                SUBMENU_TEXT_COLOR, false);
        guiGraphics.pose().popPose();
    }

    private void drawStat(GuiGraphics guiGraphics, String label, int value, int x, int y, int contentStartX, int contentWidth) {
        String text;
        if (label.equals("Damage Dealt") || label.equals("Damage Taken")) {
            double displayValue = value / 10.0;
            text = label + ": " + String.format("%.0f", displayValue);
        } else {
            text = label + ": " + value;
        }

        int textWidth = font.width(text);
        int centeredX = contentStartX + (contentWidth - textWidth) / 2;
        guiGraphics.drawString(font, text, centeredX, y, SUBMENU_TEXT_COLOR, false); // Changed from PARCHMENT_COLOR
    }

    private void drawDistanceStat(GuiGraphics guiGraphics, String label, double valueKm, int x, int y, int contentStartX, int contentWidth) {
        String formattedDistance = String.format("%.2f", valueKm);
        String text = label + ": " + formattedDistance + " km";
        int textWidth = font.width(text);
        int centeredX = contentStartX + (contentWidth - textWidth) / 2;
        guiGraphics.drawString(font, text, centeredX, y, SUBMENU_TEXT_COLOR, false); // Changed from PARCHMENT_COLOR
    }

    private void drawFavoriteWeapon(GuiGraphics guiGraphics, int x, int y, int contentStartX, int contentWidth) {
        String text = "Favourite Weapon: " + favoriteWeapon;
        if (favoriteWeaponUses > 0) {
            text += " (" + favoriteWeaponUses + " uses)";
        }

        int textWidth = font.width(text);
        int centeredX = contentStartX + (contentWidth - textWidth) / 2;
        guiGraphics.drawString(font, text, centeredX, y, SUBMENU_TEXT_COLOR, false); // Changed from PARCHMENT_COLOR
    }

    private void renderTexturedPanel(GuiGraphics guiGraphics, ResourceLocation texture,
                                     int x, int y, int width, int height, boolean withBorders) {
        guiGraphics.flush();

        com.mojang.blaze3d.systems.RenderSystem.enableBlend();
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                texture, x, y, 0, 0, width, height, 32, 32);

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        if (withBorders) {
            drawPanelBorders(guiGraphics, x, y, width, height);
        }
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

        com.mojang.blaze3d.systems.RenderSystem.enableBlend();
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

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

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

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

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1.0f);

        drawRotatableBorder(guiGraphics, PAPER_SIDETOP_TEXTURE,
                x + cornerSize, y + height - borderHeight,
                width - (cornerSize * 2), borderHeight,
                180);

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

        drawPaperCorners(guiGraphics, x, y, width, height, cornerSize);
    }

    private void drawPaperCorners(GuiGraphics guiGraphics, int x, int y, int width, int height, int cornerSize) {
        com.mojang.blaze3d.vertex.PoseStack poseStack = guiGraphics.pose();

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
                                     net.minecraft.resources.ResourceLocation texture,
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
                                     net.minecraft.resources.ResourceLocation texture,
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

    private void renderStonePanel(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.flush();

        com.mojang.blaze3d.systems.RenderSystem.enableBlend();
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.9F);

        int tileSize = 16;
        for (int tileX = 0; tileX < width; tileX += tileSize) {
            for (int tileY = 0; tileY < height; tileY += tileSize) {
                int tileWidth = Math.min(tileSize, width - tileX);
                int tileHeight = Math.min(tileSize, height - tileY);

                guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                        STONE_TEXTURE, x + tileX, y + tileY, 0, 0,
                        tileWidth, tileHeight, tileSize, tileSize);
            }
        }


        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        drawPanelBorders(guiGraphics, x, y, width, height);
    }


    private void drawPanelBorders(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                PILLAR_TEXTURE, x, y, 0, 0, BORDER_PILLAR_WIDTH, height, 16, 64);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                PILLAR_TEXTURE, x + width - BORDER_PILLAR_WIDTH, y, 0, 0,
                BORDER_PILLAR_WIDTH, height, 16, 64);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                BORDER_TEXTURE, x, y, 0, 0, width, BORDER_HEIGHT, 64, 16);

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(0.8f, 0.8f, 0.8f, 1.0f);

        guiGraphics.blit(net.minecraft.client.renderer.RenderType::guiTextured,
                BORDER_TEXTURE, x, y + height - BORDER_HEIGHT, 0, 0,
                width, BORDER_HEIGHT, 64, 16);

        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

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

    private class ScreenLayout {
        final int topMargin;
        final int sideMargin;
        final int sectionButtonWidth;
        final int sectionButtonHeight;
        final int contentX;
        final int contentY;
        final int contentWidth;
        final int contentHeight;

        ScreenLayout(int screenWidth, int screenHeight) {
            topMargin = screenHeight / 32;
            sideMargin = screenWidth / 32;
            sectionButtonHeight = screenHeight / 8;

            int availableWidth = screenWidth - (2 * sideMargin);
            sectionButtonWidth = availableWidth / 7;

            contentX = sideMargin;
            contentY = topMargin + sectionButtonHeight + 5;
            contentWidth = screenWidth - (2 * sideMargin);
            contentHeight = screenHeight - topMargin - sectionButtonHeight - (screenHeight / 32);
        }
    }

    //House Mechanics

    private void drawHouseSection(GuiGraphics guiGraphics, int mouseX, int mouseY, ScreenLayout layout) {
        // Make paper panel larger - extend more towards left and right edges
        int contentStartX = layout.contentX + layout.contentWidth / 20;  // Much smaller left margin
        int contentStartY = layout.contentY + layout.contentHeight / 16;
        int contentWidth = layout.contentWidth - (layout.contentWidth / 10);  // Much wider panel
        int contentHeight = layout.contentHeight - (layout.contentHeight / 8);

        renderPaperPanel(guiGraphics, contentStartX, contentStartY, contentWidth, contentHeight);

        // Request owned towns data if we haven't already and we have a house name
        if (!hasRequestedTowns && !houseName.isEmpty()) {
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new RequestOwnedTownsPacket());
            hasRequestedTowns = true;
        }

        if (houseName.isEmpty() || isEditingHouseName) {
            // Draw instruction text
            String instructionText = houseName.isEmpty() ? "Enter your noble house name:" : "Edit your house name:";
            guiGraphics.drawString(font, instructionText, contentStartX + 20, contentStartY + 40, SUBMENU_TEXT_COLOR, false);

            // Show input field and save button
            if (houseNameEditBox == null) {
                houseNameEditBox = new net.minecraft.client.gui.components.EditBox(
                        font, contentStartX + 20, contentStartY + 60, contentWidth - 140, 20,
                        Component.literal("House Name")
                );
                houseNameEditBox.setMaxLength(30);
                houseNameEditBox.setValue(houseName);
                addWidget(houseNameEditBox);
            }

            if (saveHouseButton == null) {
                saveHouseButton = net.minecraft.client.gui.components.Button.builder(
                        Component.literal("Save"),
                        button -> {
                            if (!houseNameEditBox.getValue().trim().isEmpty()) {
                                houseName = houseNameEditBox.getValue().trim();
                                isEditingHouseName = false;
                                removeWidget(houseNameEditBox);
                                removeWidget(saveHouseButton);
                                houseNameEditBox = null;
                                saveHouseButton = null;
                                saveHouseNameToPlayerData();
                                hasRequestedTowns = false;
                                playButtonSound();
                            }
                        }
                ).bounds(contentStartX + contentWidth - 100, contentStartY + 60, 60, 20).build();
                addWidget(saveHouseButton);
            }

            // Render the widgets
            if (houseNameEditBox != null) {
                houseNameEditBox.render(guiGraphics, mouseX, mouseY, 0);
            }
            if (saveHouseButton != null) {
                saveHouseButton.render(guiGraphics, mouseX, mouseY, 0);
            }

        } else {
            // Show house name and edit button
            String houseTitle = "House " + houseName;
            int titleWidth = (int)(font.width(houseTitle) * 1.3f);
            int titleX = contentStartX + (contentWidth - titleWidth) / 2;

            guiGraphics.pose().pushPose();
            guiGraphics.pose().scale(1.3f, 1.3f, 1.0f);
            guiGraphics.drawString(font, houseTitle, (int)(titleX / 1.3f), (int)((contentStartY + 30) / 1.3f), SUBMENU_TEXT_COLOR, false);
            guiGraphics.pose().popPose();

            if (editHouseButton == null) {
                editHouseButton = net.minecraft.client.gui.components.Button.builder(
                        Component.literal("Edit"),
                        button -> {
                            isEditingHouseName = true;
                            removeWidget(editHouseButton);
                            editHouseButton = null;
                            playButtonSound();
                        }
                ).bounds(contentStartX + contentWidth - 50, contentStartY + 20, 30, 16).build();  // Smaller: 30x16 instead of 40x20
                addWidget(editHouseButton);
            }

            // Draw owned towns section - 80% of horizontal area, positioned directly under house name
            int townsSectionWidth = (int)(contentWidth * 0.8);
            int townsSectionX = contentStartX + (contentWidth - townsSectionWidth) / 2;  // Center horizontally
            int houseNameBottomY = contentStartY + 30 + (int)(font.lineHeight * 1.3f);  // Bottom of scaled house name
            drawOwnedTownsSection(guiGraphics, townsSectionX, houseNameBottomY + 10, townsSectionWidth, contentHeight - (houseNameBottomY + 10 - contentStartY));

            // Render the edit button
            if (editHouseButton != null) {
                editHouseButton.render(guiGraphics, mouseX, mouseY, 0);
            }
        }

        // Draw house banners ABOVE everything - positioned inside the panel on left and right sides
        if (!houseName.isEmpty() && !isEditingHouseName) {
            // Calculate vertical center of the paper panel
            int bannerY = contentStartY + (contentHeight - 120) / 2;  // 120 is banner height, center it vertically

            // Left banner - positioned inside the left side of the paper panel
            drawHouseBanner(guiGraphics, contentStartX + 20, bannerY);

            // Right banner - positioned inside the right side of the paper panel
            drawHouseBanner(guiGraphics, contentStartX + contentWidth - 80, bannerY);
        }
    }


    private void drawHouseBanner(GuiGraphics guiGraphics, int x, int y) {
        if (syncedHouseBanner == null) {
            AGoTMod.LOGGER.info("Banner rendering: syncedHouseBanner is null");
            drawNoBannerPlaceholder(guiGraphics, x, y);
            return;
        }

        try {
            AGoTMod.LOGGER.info("Banner rendering: Starting banner render process");

            // Create ItemStack from saved NBT data
            ItemStack bannerStack = ItemStack.parseOptional(minecraft.level.registryAccess(), syncedHouseBanner);

            if (bannerStack.isEmpty() || !(bannerStack.getItem() instanceof net.minecraft.world.item.BannerItem)) {
                AGoTMod.LOGGER.error("Banner rendering: Invalid banner stack - isEmpty: {}, isCorrectType: {}",
                        bannerStack.isEmpty(), bannerStack.getItem() instanceof net.minecraft.world.item.BannerItem);
                drawErrorBannerPlaceholder(guiGraphics, x, y, "Invalid Banner");
                return;
            }

            // Get banner data for rendering
            net.minecraft.world.item.BannerItem bannerItem = (net.minecraft.world.item.BannerItem) bannerStack.getItem();
            net.minecraft.world.item.DyeColor baseColor = bannerItem.getColor();

            AGoTMod.LOGGER.info("Banner rendering: Base banner color: {}", baseColor);

            // Get patterns from the banner
            net.minecraft.world.level.block.entity.BannerPatternLayers patterns =
                    bannerStack.get(net.minecraft.core.component.DataComponents.BANNER_PATTERNS);

            if (patterns == null) {
                AGoTMod.LOGGER.info("Banner rendering: No patterns found (patterns is null)");
            } else {
                AGoTMod.LOGGER.info("Banner rendering: Found {} pattern layers", patterns.layers().size());
                for (int i = 0; i < patterns.layers().size(); i++) {
                    var layer = patterns.layers().get(i);
                    AGoTMod.LOGGER.info("Banner rendering: Layer {}: pattern '{}' with color '{}'",
                            i, layer.pattern().value().assetId(), layer.color());
                }
            }

            // Set up rendering area - 3x larger
            int bannerWidth = 60;
            int bannerHeight = 120;

            // Enable blending for proper layer compositing
            com.mojang.blaze3d.systems.RenderSystem.enableBlend();
            com.mojang.blaze3d.systems.RenderSystem.defaultBlendFunc();

            // Save pose state
            guiGraphics.pose().pushPose();

            // Layer 1: Draw base banner texture with base color
            AGoTMod.LOGGER.info("Banner rendering: Drawing base layer with color {}", baseColor);
            drawTextureLayer(guiGraphics, x, y, bannerWidth, bannerHeight, BANNER_BASE_TEXTURE, baseColor);

            // Layer 2+: Apply ALL pattern layers if present (infinite support)
            if (patterns != null && !patterns.layers().isEmpty()) {
                AGoTMod.LOGGER.info("Banner rendering: Starting to render {} pattern layers", patterns.layers().size());
                int layerIndex = 0;
                for (net.minecraft.world.level.block.entity.BannerPatternLayers.Layer layer : patterns.layers()) {
                    AGoTMod.LOGGER.info("Banner rendering: Rendering pattern layer {}: {} with color {}",
                            layerIndex, layer.pattern().value().assetId(), layer.color());
                    drawPatternLayer(guiGraphics, x, y, bannerWidth, bannerHeight, layer);
                    layerIndex++;
                }
                AGoTMod.LOGGER.info("Banner rendering: Finished rendering all {} pattern layers", layerIndex);
            } else {
                AGoTMod.LOGGER.info("Banner rendering: No pattern layers to render");
            }

            // Restore pose state
            guiGraphics.pose().popPose();

            // Reset render system state
            com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            com.mojang.blaze3d.systems.RenderSystem.disableBlend();

            AGoTMod.LOGGER.info("Banner rendering: Banner rendering completed successfully");

        } catch (Exception e) {
            AGoTMod.LOGGER.error("Banner rendering: Error rendering house banner", e);
            drawErrorBannerPlaceholder(guiGraphics, x, y, "Render Error");
        }
    }

    // Draw any texture layer with proper Minecraft-style color tinting (like spawn eggs)
    private void drawTextureLayer(GuiGraphics guiGraphics, int x, int y, int width, int height,
                                  ResourceLocation texture, net.minecraft.world.item.DyeColor color) {

        AGoTMod.LOGGER.info("Banner rendering: Drawing texture layer - texture: {}, color: {}", texture, color);

        // Get the color value for tinting
        int colorValue = color.getTextureDiffuseColor();

        AGoTMod.LOGGER.info("Banner rendering: Using color value: 0x{}", Integer.toHexString(colorValue));

        // Reset shader color to white first
        com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // Draw the texture layer with proper color tinting using the color parameter
        // Sample 20x40 area (moved 1 pixel right) but display it at 60x120 size (3x scale)
        guiGraphics.blit(
                net.minecraft.client.renderer.RenderType::guiTextured,
                texture,
                x, y,                    // destination position
                1.0f, 0.0f,              // source UV start (moved 1 pixel right)
                width, height,           // destination size (60x120)
                20, 40,                  // source texture area (keep original 20x40 sampling)
                64, 64,                  // source texture size (64x64)
                colorValue               // This applies the color tint like spawn eggs!
        );

        AGoTMod.LOGGER.info("Banner rendering: Texture layer drawn with color tinting");
    }

    // Draw a single pattern layer - supports infinite layers
    private void drawPatternLayer(GuiGraphics guiGraphics, int x, int y, int width, int height,
                                  net.minecraft.world.level.block.entity.BannerPatternLayers.Layer layer) {

        // Get pattern resource location
        net.minecraft.resources.ResourceLocation patternId = layer.pattern().value().assetId();
        String patternName = patternId.getPath();

        AGoTMod.LOGGER.info("Banner rendering: Pattern layer - patternId: {}, patternName: {}", patternId, patternName);

        // Get the texture for this pattern
        ResourceLocation patternTexture = BANNER_PATTERN_TEXTURES.get(patternName);
        if (patternTexture == null) {
            // Fallback to vanilla patterns if custom pattern not found
            patternTexture = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/entity/banner/" + patternName + ".png");
            AGoTMod.LOGGER.info("Banner rendering: Pattern texture not found in map, using fallback: {}", patternTexture);
        } else {
            AGoTMod.LOGGER.info("Banner rendering: Found pattern texture in map: {}", patternTexture);
        }

        // Draw this pattern layer with its specific color using the same method as base
        drawTextureLayer(guiGraphics, x, y, width, height, patternTexture, layer.color());
    }

    // Draw placeholder when no banner is set
    private void drawNoBannerPlaceholder(GuiGraphics guiGraphics, int x, int y) {
        int width = 60;  // Scaled up to 3x
        int height = 120; // Scaled up to 3x

        // Draw placeholder background with banner-like appearance
        guiGraphics.fill(x, y, x + width, y + height, 0xFF8B4513); // Brown color
        guiGraphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, 0xFFD2B48C); // Tan color

        // Draw "No Banner" text
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(0.4f, 0.4f, 1.0f);

        String line1 = "No";
        String line2 = "Banner";

        int textX1 = (int)((x + width/2 - font.width(line1)/5) / 0.4f);
        int textX2 = (int)((x + width/2 - font.width(line2)/5) / 0.4f);
        int textY1 = (int)((y + height/2 - 6) / 0.4f);
        int textY2 = (int)((y + height/2 + 2) / 0.4f);

        guiGraphics.drawString(font, line1, textX1, textY1, 0xFF8B0000, false);
        guiGraphics.drawString(font, line2, textX2, textY2, 0xFF8B0000, false);

        guiGraphics.pose().popPose();
    }

    // Draw error placeholder when banner rendering fails
    private void drawErrorBannerPlaceholder(GuiGraphics guiGraphics, int x, int y, String errorText) {
        int width = 60;  // Scaled up to 3x
        int height = 120; // Scaled up to 3x

        // Draw error background
        guiGraphics.fill(x, y, x + width, y + height, 0xFF8B0000); // Dark red
        guiGraphics.fill(x + 1, y + 1, x + width - 1, y + height - 1, 0xFFFF4500); // Orange red

        // Draw error indicator (X pattern) - scaled up to 3x
        guiGraphics.fill(x + 9, y + 15, x + 15, y + height - 15, 0xFFFFFFFF);
        guiGraphics.fill(x + width - 15, y + 15, x + width - 9, y + height - 15, 0xFFFFFFFF);

        // Draw diagonal lines for X - scaled up to 3x
        for (int i = 0; i < 42; i++) {
            guiGraphics.fill(x + 9 + i, y + 15 + i, x + 12 + i, y + 18 + i, 0xFFFFFFFF);
            guiGraphics.fill(x + width - 12 - i, y + 15 + i, x + width - 9 - i, y + 18 + i, 0xFFFFFFFF);
        }
    }

    private void drawOwnedTownsSection(GuiGraphics guiGraphics, int x, int y, int width, int height) {
        // Draw section title
        String sectionTitle = "Owned Towns";
        int titleWidth = (int)(font.width(sectionTitle) * 1.1f);
        int titleX = x + (width - titleWidth) / 2;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(1.1f, 1.1f, 1.0f);
        guiGraphics.drawString(font, sectionTitle, (int)(titleX / 1.1f), (int)(y / 1.1f), SUBMENU_TEXT_COLOR, false);
        guiGraphics.pose().popPose();

        // Draw separator line
        guiGraphics.fill(x + width/8, y + (int)(font.lineHeight * 1.1f) + 5,
                x + width - width/8, y + (int)(font.lineHeight * 1.1f) + 6,
                SUBMENU_TEXT_COLOR);

        int listStartY = y + (int)(font.lineHeight * 1.1f) + 15;

        if (ownedTowns.isEmpty()) {
            // Show "No towns owned" message
            String noTownsText = "No towns claimed";
            int textWidth = font.width(noTownsText);
            int textX = x + (width - textWidth) / 2;
            guiGraphics.drawString(font, noTownsText, textX, listStartY + 20, 0xFF666666, false);
        } else {
            // Draw towns list
            int currentY = listStartY;
            int lineHeight = font.lineHeight + 4;

            for (SyncOwnedTownsPacket.TownInfo town : ownedTowns) {
                if (currentY + lineHeight > y + height) break; // Don't draw beyond bounds

                String townText = town.townName() + " - Population: " + town.population();
                int textWidth = font.width(townText);
                int textX = x + (width - textWidth) / 2;

                // Color coding based on population
                int textColor = SUBMENU_TEXT_COLOR;
                if (town.population() >= 100) {
                    textColor = 0xFF2E7D32; // Dark green for large towns
                } else if (town.population() >= 50) {
                    textColor = 0xFF1565C0; // Blue for medium towns
                } else if (town.population() >= 20) {
                    textColor = 0xFF6A4C93; // Purple for small towns
                }

                guiGraphics.drawString(font, townText, textX, currentY, textColor, false);
                currentY += lineHeight;
            }

            // Show total if multiple towns
            if (ownedTowns.size() > 1) {
                int totalPopulation = ownedTowns.stream().mapToInt(SyncOwnedTownsPacket.TownInfo::population).sum();
                String totalText = "Total Population: " + totalPopulation;
                int textWidth = font.width(totalText);
                int textX = x + (width - textWidth) / 2;

                guiGraphics.fill(x + width/6, currentY + 5, x + width - width/6, currentY + 6, SUBMENU_TEXT_COLOR);

                guiGraphics.pose().pushPose();
                guiGraphics.pose().scale(0.9f, 0.9f, 1.0f);
                guiGraphics.drawString(font, totalText, (int)(textX / 0.9f), (int)((currentY + 10) / 0.9f), 0xFF1A237E, false);
                guiGraphics.pose().popPose();
            }
        }
    }

    private void saveHouseNameToPlayerData() {
        if (minecraft == null || minecraft.player == null || isValidatingHouseName) {
            return;
        }

        String newHouseName = houseName.trim();
        if (newHouseName.isEmpty()) {
            return;
        }

        // Get current house name to check if it's actually changing
        String currentHouseName = "";
        CompoundTag persistentData = minecraft.player.getPersistentData();
        if (persistentData.contains(AGoTMod.MOD_ID + ".house")) {
            CompoundTag houseTag = persistentData.getCompound(AGoTMod.MOD_ID + ".house");
            if (houseTag.contains("house_name")) {
                currentHouseName = houseTag.getString("house_name");
            }
        }

        // If the name hasn't changed, just save it locally
        if (newHouseName.equals(currentHouseName)) {
            CompoundTag houseTag = new CompoundTag();
            houseTag.putString("house_name", newHouseName);
            persistentData.put(AGoTMod.MOD_ID + ".house", houseTag);
            net.neoforged.neoforge.network.PacketDistributor.sendToServer(new SaveHouseNamePacket(newHouseName));
            return;
        }

        // First validate the house name if it's actually changing
        isValidatingHouseName = true;
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new CheckHouseNamePacket(newHouseName));

        // Save locally for immediate UI update (will be corrected if validation fails)
        CompoundTag houseTag = new CompoundTag();
        houseTag.putString("house_name", newHouseName);
        persistentData.put(AGoTMod.MOD_ID + ".house", houseTag);

        // Send to server for persistence (this will be validated server-side)
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new SaveHouseNamePacket(newHouseName));
    }

    private void loadHouseNameFromPlayerData() {
        // Request house name from server
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new RequestHouseNamePacket());

        // Request house banner from server
        net.neoforged.neoforge.network.PacketDistributor.sendToServer(new RequestHouseBannerPacket());

        // Use the synced name if available
        houseName = syncedHouseName;
    }

    // Add this static variable at the top with other fields

    // Add this static method
    public static void setSyncedHouseName(String name) {
        syncedHouseName = name;
    }

    public static void setSyncedHouseBanner(CompoundTag bannerData) {
        syncedHouseBanner = bannerData;
    }

    public static void setOwnedTowns(java.util.List<SyncOwnedTownsPacket.TownInfo> towns) {
        ownedTowns = new java.util.ArrayList<>(towns);
    }

    // Update the static method in CustomGuiScreen
    public static void handleHouseNameValidation(boolean isAvailable, String message) {
        if (currentInstance != null) {
            currentInstance.isValidatingHouseName = false;
            if (!isAvailable) {
                // Show error message to player using displayClientMessage instead
                if (net.minecraft.client.Minecraft.getInstance().player != null) {
                    net.minecraft.client.Minecraft.getInstance().player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal(message), false);
                }
            }
        }
    }




}