package net.darkflameproduction.agotmod.armor.client.mountin_clan;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class MountainClanPlateArmorRenderer extends GeoArmorRenderer<MountainClanPlateArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public MountainClanPlateArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new MountainClanPlateArmorModel());
    }
}
