package net.darkflameproduction.agotmod.armor.client.wildling;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingFurArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class WildlingFurArmorRenderer extends GeoArmorRenderer<WildlingFurArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public WildlingFurArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new WildlingFurArmorModel());
    }
}
