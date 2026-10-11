package net.darkflameproduction.agotmod.entity.custom.npc.system.culture;

import java.util.Map;

/**
 * A town's culture: one per faction that has its own armour (ModArmorSets) - the culture's
 * {@link #armorFaction} is that armour's faction id, e.g. "stark" for stark_tier2 / stark_tier3.
 */
public enum Culture {
    // ── No culture ────────────────────────────────────────────────────────────
    NONE(CultureGroup.NONE, "None", ""),

    // ── The North ─────────────────────────────────────────────────────────────
    STARK               (CultureGroup.NORTH, "House Stark",             "stark"),
    BOLTON              (CultureGroup.NORTH, "House Bolton",            "bolton"),
    MANDERLY            (CultureGroup.NORTH, "House Manderly",          "manderly"),
    MORMONT             (CultureGroup.NORTH, "House Mormont",           "mormont"),
    UMBER               (CultureGroup.NORTH, "House Umber",             "umber"),
    NORTH_MOUNTAIN_CLAN (CultureGroup.NORTH, "Northern Mountain Clans", "north_mountain_clan"),
    SKAGOS              (CultureGroup.NORTH, "Skagos",                  "skagos"),

    // ── Free Folk ─────────────────────────────────────────────────────────────
    WILDLING            (CultureGroup.WILDLING, "Wildlings",       "wildling"),
    CAVE_DWELLER        (CultureGroup.WILDLING, "Cave Dwellers",   "cave_dweller"),
    FROZEN_SHORE        (CultureGroup.WILDLING, "Frozen Shore",    "frozen_shore"),
    HARDHOME            (CultureGroup.WILDLING, "Hardhome",        "hardhome"),
    HORNFOOT            (CultureGroup.WILDLING, "Hornfoots",       "hornfoot"),
    ICE_RIVER           (CultureGroup.WILDLING, "Ice River Clans", "ice_river"),
    THENN               (CultureGroup.WILDLING, "Thenns",          "thenn"),

    // ── Iron Islands ──────────────────────────────────────────────────────────
    GREYJOY             (CultureGroup.IRON_ISLANDS, "House Greyjoy", "greyjoy"),
    HARLAW              (CultureGroup.IRON_ISLANDS, "House Harlaw",  "harlaw"),

    // ── The Wall ──────────────────────────────────────────────────────────────
    NIGHTS_WATCH        (CultureGroup.THE_WALL, "Night's Watch", "nights_watch");

    public final CultureGroup group;
    public final String displayName;
    /** The armour faction this culture wears (ModArmorSets faction id), "" for none. */
    public final String armorFaction;

    Culture(CultureGroup group, String displayName, String armorFaction) {
        this.group        = group;
        this.displayName  = displayName;
        this.armorFaction = armorFaction;
    }

    /** Cultures from before they were tied to the armour factions, mapped to the closest one. */
    private static final Map<String, Culture> LEGACY = Map.ofEntries(
            Map.entry("GENERIC_NORTH", STARK),
            Map.entry("WHITE_HARBOR", MANDERLY),
            Map.entry("THE_GIFT", NIGHTS_WATCH),
            Map.entry("BEAR_ISLAND", MORMONT),
            Map.entry("MOUNTAIN_CLANS", NORTH_MOUNTAIN_CLAN),
            Map.entry("BARROWLANDS", STARK),
            Map.entry("FLINTS", NORTH_MOUNTAIN_CLAN),
            Map.entry("STONY_SHORE", STARK),
            Map.entry("SEA_DRAGON_POINT", STARK),
            Map.entry("CRANNOGMEN", STARK),
            Map.entry("GENERIC_WILDLING", WILDLING),
            Map.entry("THENNS", THENN),
            Map.entry("FROSTFANG", HORNFOOT),
            Map.entry("ICE_RIVER_CLANS", ICE_RIVER)
    );

    /** A saved culture name - current or from before - or NONE if unknown. */
    public static Culture fromName(String name) {
        if (name == null || name.isEmpty()) return NONE;
        try {
            return valueOf(name);
        } catch (IllegalArgumentException e) {
            return LEGACY.getOrDefault(name, NONE);
        }
    }
}
