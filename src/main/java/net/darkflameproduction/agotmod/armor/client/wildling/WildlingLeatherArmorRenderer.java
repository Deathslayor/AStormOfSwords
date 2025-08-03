package net.darkflameproduction.agotmod.armor.client.wildling;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingLeatherArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class WildlingLeatherArmorRenderer extends GeoArmorRenderer<WildlingLeatherArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public WildlingLeatherArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new WildlingLeatherArmorModel());
    }
}
