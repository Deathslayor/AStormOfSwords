package net.darkflameproduction.agotmod.gui;

import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.stats.Stats;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatsCounter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;

public class PlayerSkillData {

    // ── Constants ─────────────────────────────────────────────────────────────

    private static final double AGILITY_LEVEL_MULTIPLIER   = 1.085;
    private static final int    MAX_AGILITY_LEVEL           = 100;
    private static final double STRENGTH_LEVEL_MULTIPLIER  = 1.06;
    private static final int    MAX_STRENGTH_LEVEL          = 100;
    private static final double ENDURANCE_LEVEL_MULTIPLIER = 1.08;
    private static final int    MAX_ENDURANCE_LEVEL         = 100;
    private static final double WEAPON_SKILL_MULTIPLIER     = 1.04;
    private static final int    MAX_WEAPON_SKILL_LEVEL      = 100;
    private static final double WEAPON_SKILL_BASE_REQUIREMENT = 100.0;

    public static final Stat<ResourceLocation> DAMAGE_DEALT_STAT = Stats.CUSTOM.get(Stats.DAMAGE_DEALT);
    public static final Stat<ResourceLocation> DAMAGE_TAKEN_STAT = Stats.CUSTOM.get(Stats.DAMAGE_TAKEN);
    public static final Stat<ResourceLocation> DEATH_COUNT_STAT  = Stats.CUSTOM.get(Stats.DEATHS);
    public static final Stat<ResourceLocation> KILLS_MOB_STAT    = Stats.CUSTOM.get(Stats.MOB_KILLS);
    public static final Stat<ResourceLocation> PLAYER_KILLS_STAT = Stats.CUSTOM.get(Stats.PLAYER_KILLS);
    public static final Stat<ResourceLocation> JUMP_STAT         = Stats.CUSTOM.get(Stats.JUMP);

    @SuppressWarnings("unchecked")
    public static final Stat<ResourceLocation>[] DISTANCE_STATS = new Stat[]{
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

    @SuppressWarnings("unchecked")
    public static final Stat<ResourceLocation>[] WEAPON_STATS = new Stat[]{
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

    public static final String[] WEAPON_NAMES = {
            "Wooden Sword", "Stone Sword", "Iron Sword", "Golden Sword", "Diamond Sword",
            "Netherite Sword", "Bow", "Crossbow", "Trident", "Bronze Sword",
            "Dragonglass Dagger", "Dragonglass Spear", "Bronze Spatha", "Bronze Spear",
            "Bronze Pike", "Bronze Battleaxe", "Bronze Dagger", "Iron Battleaxe",
            "Iron Longsword", "Iron Dagger", "Iron Mace", "Iron Pike", "Iron Spear",
            "Steel Longsword", "Steel Spear", "Steel Pike", "Steel Mace", "Steel Dagger",
            "Steel Battleaxe", "Steel Halberd", "Noble Longsword", "Noble Spear",
            "Noble Pike", "Noble Mace", "Noble Dagger", "Noble Battleaxe", "Noble Halberd",
            "Steel Bow", "Steel Sword"
    };

    private static final int[] ONE_HANDED_STATS_INDICES = {0,1,2,3,4,5,9,12,15,17,18,20,23,27,28,29,30,35,36,38};
    private static final int[] TWO_HANDED_STATS_INDICES  = {18,23,30};
    private static final int[] POLEARM_STATS_INDICES     = {8,11,13,14,21,22,24,25,29,31,36,37};
    private static final int[] SHORT_BLADE_STATS_INDICES = {10,16,19,27,34};
    private static final int[] RANGED_STATS_INDICES      = {6,7,37};

    // ── Cached stat values ────────────────────────────────────────────────────

    public int     cachedDeaths        = 0;
    public int     cachedDamageDealt   = 0;
    public int     cachedDamageTaken   = 0;
    public int     cachedMobKills      = 0;
    public int     cachedPlayerKills   = 0;
    public int     cachedJumps         = 0;
    public double  cachedTotalDistance = 0.0;
    public int[]   cachedWeaponUsages  = new int[WEAPON_STATS.length];
    public String  favoriteWeapon      = "None";
    public int     favoriteWeaponUses  = 0;
    public boolean areStatsLoaded      = false;

    // ── Skill levels ──────────────────────────────────────────────────────────

    public int    agilityLevel     = 0;
    public double agilityProgress  = 0.0;
    public double nextLevelDistance = 0.0;

    public int    strengthLevel               = 0;
    public double strengthProgress            = 0.0;
    public double nextStrengthLevelRequirement = 0.0;

    public int    enduranceLevel               = 0;
    public double enduranceProgress            = 0.0;
    public double nextEnduranceLevelRequirement = 0.0;

    public int    oneHandedLevel          = 0;
    public double oneHandedProgress       = 0.0;
    public double nextOneHandedRequirement = 0.0;

    public int    twoHandedLevel          = 0;
    public double twoHandedProgress       = 0.0;
    public double nextTwoHandedRequirement = 0.0;

    public int    polearmLevel          = 0;
    public double polearmProgress       = 0.0;
    public double nextPolearmRequirement = 0.0;

    public int    shortBladeLevel          = 0;
    public double shortBladeProgress       = 0.0;
    public double nextShortBladeRequirement = 0.0;

    public int    rangedLevel          = 0;
    public double rangedProgress       = 0.0;
    public double nextRangedRequirement = 0.0;

    // ── Public update entry point ─────────────────────────────────────────────

    public void updateFromStats(StatsCounter stats) {
        if (stats == null) return;

        cachedDeaths       = stats.getValue(DEATH_COUNT_STAT);
        cachedDamageDealt  = stats.getValue(DAMAGE_DEALT_STAT);
        cachedDamageTaken  = stats.getValue(DAMAGE_TAKEN_STAT);
        cachedMobKills     = stats.getValue(KILLS_MOB_STAT);
        cachedPlayerKills  = stats.getValue(PLAYER_KILLS_STAT);
        cachedJumps        = stats.getValue(JUMP_STAT);

        long totalCm = 0;
        for (Stat<ResourceLocation> s : DISTANCE_STATS) totalCm += stats.getValue(s);
        cachedTotalDistance = totalCm / 100000.0;

        favoriteWeapon    = "None";
        favoriteWeaponUses = 0;
        for (int i = 0; i < WEAPON_STATS.length; i++) {
            int uses = stats.getValue(WEAPON_STATS[i]);
            cachedWeaponUsages[i] = uses;
            if (uses > favoriteWeaponUses) {
                favoriteWeaponUses = uses;
                favoriteWeapon = WEAPON_NAMES[i];
            }
        }

        areStatsLoaded = true;
        recalculateAll();
    }

    // ── Category helpers ──────────────────────────────────────────────────────

    public int getOneHandedUsage()  { return sumUsage(ONE_HANDED_STATS_INDICES); }
    public int getTwoHandedUsage()  { return sumUsage(TWO_HANDED_STATS_INDICES); }
    public int getPolearmUsage()    { return sumUsage(POLEARM_STATS_INDICES); }
    public int getShortBladeUsage() { return sumUsage(SHORT_BLADE_STATS_INDICES); }
    public int getRangedUsage()     { return sumUsage(RANGED_STATS_INDICES); }

    private int sumUsage(int[] indices) {
        int total = 0;
        for (int i : indices) if (i < cachedWeaponUsages.length) total += cachedWeaponUsages[i];
        return total;
    }

    // ── Recalculate everything ────────────────────────────────────────────────

    private void recalculateAll() {
        calculateAgilityLevel();
        calculateStrengthLevel();
        calculateEnduranceLevel();
        calculateOneHandedLevel();
        calculateTwoHandedLevel();
        calculatePolearmLevel();
        calculateShortBladeLevel();
        calculateRangedLevel();
    }

    // ── Individual calculations ───────────────────────────────────────────────

    private void calculateAgilityLevel() {
        if (cachedTotalDistance <= 0) {
            agilityLevel = 0; agilityProgress = 0; nextLevelDistance = 1.0; return;
        }
        double total = 0, req = 1.0;
        for (int level = 1; level <= MAX_AGILITY_LEVEL; level++) {
            total += req;
            if (cachedTotalDistance < total) {
                agilityLevel    = level - 1;
                agilityProgress = (cachedTotalDistance - (total - req)) / req;
                nextLevelDistance = req;
                return;
            }
            req *= AGILITY_LEVEL_MULTIPLIER;
        }
        agilityLevel = MAX_AGILITY_LEVEL; agilityProgress = 1.0; nextLevelDistance = 0;
    }

    private void calculateStrengthLevel() {
        if (cachedDamageDealt <= 0) {
            strengthLevel = 0; strengthProgress = 0; nextStrengthLevelRequirement = 1000.0; return;
        }
        double dealt = cachedDamageDealt / 10.0, total = 0, req = 1000.0;
        for (int level = 1; level <= MAX_STRENGTH_LEVEL; level++) {
            total += req;
            if (dealt < total) {
                strengthLevel    = level - 1;
                strengthProgress = (dealt - (total - req)) / req;
                nextStrengthLevelRequirement = req;
                return;
            }
            req *= STRENGTH_LEVEL_MULTIPLIER;
        }
        strengthLevel = MAX_STRENGTH_LEVEL; strengthProgress = 1.0; nextStrengthLevelRequirement = 0;
    }

    private void calculateEnduranceLevel() {
        if (cachedDamageTaken <= 0) {
            enduranceLevel = 0; enduranceProgress = 0; nextEnduranceLevelRequirement = 100.0; return;
        }
        double taken = cachedDamageTaken / 10.0, total = 0, req = 100.0;
        for (int level = 1; level <= MAX_ENDURANCE_LEVEL; level++) {
            total += req;
            if (taken < total) {
                enduranceLevel    = level - 1;
                enduranceProgress = (taken - (total - req)) / req;
                nextEnduranceLevelRequirement = req;
                return;
            }
            req *= ENDURANCE_LEVEL_MULTIPLIER;
        }
        enduranceLevel = MAX_ENDURANCE_LEVEL; enduranceProgress = 1.0; nextEnduranceLevelRequirement = 0;
    }

    private void calculateWeaponSkill(int usage, int maxLevel,
                                      java.util.function.IntConsumer levelSetter,
                                      java.util.function.DoubleConsumer progressSetter,
                                      java.util.function.DoubleConsumer nextSetter) {
        if (usage <= 0) {
            levelSetter.accept(0); progressSetter.accept(0.0); nextSetter.accept(WEAPON_SKILL_BASE_REQUIREMENT);
            return;
        }
        double total = 0, req = WEAPON_SKILL_BASE_REQUIREMENT;
        for (int level = 1; level <= maxLevel; level++) {
            total += req;
            if (usage < total) {
                levelSetter.accept(level - 1);
                progressSetter.accept((usage - (total - req)) / req);
                nextSetter.accept(req);
                return;
            }
            req *= WEAPON_SKILL_MULTIPLIER;
        }
        levelSetter.accept(maxLevel); progressSetter.accept(1.0); nextSetter.accept(0);
    }

    private void calculateOneHandedLevel() {
        calculateWeaponSkill(getOneHandedUsage(), MAX_WEAPON_SKILL_LEVEL,
                v -> oneHandedLevel = v, v -> oneHandedProgress = v, v -> nextOneHandedRequirement = v);
    }
    private void calculateTwoHandedLevel() {
        calculateWeaponSkill(getTwoHandedUsage(), MAX_WEAPON_SKILL_LEVEL,
                v -> twoHandedLevel = v, v -> twoHandedProgress = v, v -> nextTwoHandedRequirement = v);
    }
    private void calculatePolearmLevel() {
        calculateWeaponSkill(getPolearmUsage(), MAX_WEAPON_SKILL_LEVEL,
                v -> polearmLevel = v, v -> polearmProgress = v, v -> nextPolearmRequirement = v);
    }
    private void calculateShortBladeLevel() {
        calculateWeaponSkill(getShortBladeUsage(), MAX_WEAPON_SKILL_LEVEL,
                v -> shortBladeLevel = v, v -> shortBladeProgress = v, v -> nextShortBladeRequirement = v);
    }
    private void calculateRangedLevel() {
        calculateWeaponSkill(getRangedUsage(), MAX_WEAPON_SKILL_LEVEL,
                v -> rangedLevel = v, v -> rangedProgress = v, v -> nextRangedRequirement = v);
    }
}
