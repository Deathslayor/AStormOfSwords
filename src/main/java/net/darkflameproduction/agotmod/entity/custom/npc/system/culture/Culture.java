package net.darkflameproduction.agotmod.entity.custom.npc.system.culture;

public enum Culture {
    // ── No culture ────────────────────────────────────────────────────────────
    NONE(CultureGroup.NONE, "None"),

    // ── The North ─────────────────────────────────────────────────────────────
    GENERIC_NORTH     (CultureGroup.NORTH, "Generic North"),
    WHITE_HARBOR      (CultureGroup.NORTH, "White Harbor"),
    SKAGOS            (CultureGroup.NORTH, "Skagos"),
    THE_GIFT          (CultureGroup.NORTH, "The Gift"),
    BEAR_ISLAND       (CultureGroup.NORTH, "Bear Island"),
    MOUNTAIN_CLANS    (CultureGroup.NORTH, "Northern Mountain Clans"),
    BARROWLANDS       (CultureGroup.NORTH, "Barrowlands"),
    FLINTS            (CultureGroup.NORTH, "The Flints"),
    STONY_SHORE       (CultureGroup.NORTH, "Stony Shore"),
    SEA_DRAGON_POINT  (CultureGroup.NORTH, "Sea Dragon Point"),
    CRANNOGMEN        (CultureGroup.NORTH, "Crannogmen"),

    // ── Wildlings ─────────────────────────────────────────────────────────────
    GENERIC_WILDLING  (CultureGroup.WILDLING, "Generic Wildlings"),
    THENNS            (CultureGroup.WILDLING, "Thenns"),
    FROSTFANG         (CultureGroup.WILDLING, "Frostfang Mountains"),
    FROZEN_SHORE      (CultureGroup.WILDLING, "Frozen Shore"),
    ICE_RIVER_CLANS   (CultureGroup.WILDLING, "Ice-River Clans"),
    HARDHOME          (CultureGroup.WILDLING, "Hardhome");

    public final CultureGroup group;
    public final String displayName;

    Culture(CultureGroup group, String displayName) {
        this.group       = group;
        this.displayName = displayName;
    }

    /** Placeholder for future crafting restrictions. */
    public String getCraftingTag() {
        return group.name().toLowerCase();
    }
}
