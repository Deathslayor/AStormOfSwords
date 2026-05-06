package net.darkflameproduction.agotmod.armor.client.mountin_clan;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class MountainClanLevyArmorRenderer extends GeoArmorRenderer<MountainClanLevyArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public MountainClanLevyArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new MountainClanLevyArmorModel());
    }
}
