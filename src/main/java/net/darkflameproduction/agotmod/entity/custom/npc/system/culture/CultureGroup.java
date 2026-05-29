package net.darkflameproduction.agotmod.entity.custom.npc.system.culture;

public enum CultureGroup {
    NONE("none", "Uncultured"),
    NORTH("northernpeasant", "The North"),
    WILDLING("wildlingpeasant", "Free Folk");

    /** The texture subfolder under textures/entity/ */
    public final String textureFolder;
    public final String displayName;

    CultureGroup(String textureFolder, String displayName) {
        this.textureFolder = textureFolder;
        this.displayName   = displayName;
    }
}
