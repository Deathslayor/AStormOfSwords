package net.darkflameproduction.agotmod.armor.client.wildling;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingChiefArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class WildlingChiefArmorRenderer extends GeoArmorRenderer<WildlingChiefArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public WildlingChiefArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new WildlingChiefArmorModel());
    }
}
