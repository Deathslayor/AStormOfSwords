package net.darkflameproduction.agotmod.entity.custom.npc.system.culture;

public enum CultureGroup {
    NONE("none", "Uncultured"),
    NORTH("northernpeasant", "The North"),
    WILDLING("wildlingpeasant", "Free Folk"),
    // No skins of their own yet - they use the Northern ones
    IRON_ISLANDS("northernpeasant", "Iron Islands"),
    THE_WALL("northernpeasant", "The Wall");

    /** The texture subfolder under textures/entity/ */
    public final String textureFolder;
    public final String displayName;

    CultureGroup(String textureFolder, String displayName) {
        this.textureFolder = textureFolder;
        this.displayName   = displayName;
    }
}
