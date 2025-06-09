package net.darkflameproduction.agotmod.armor.client.thenn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennPlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ThennPlateArmorRenderer extends GeoArmorRenderer<ThennPlateArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public ThennPlateArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new ThennPlateArmorModel());
    }
}
