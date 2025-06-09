package net.darkflameproduction.agotmod.armor.client.manderly;

import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyNobleArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ManderlyNobleArmorRenderer extends GeoArmorRenderer<ManderlyNobleArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public ManderlyNobleArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new ManderlyNobleArmorModel());
    }
}
