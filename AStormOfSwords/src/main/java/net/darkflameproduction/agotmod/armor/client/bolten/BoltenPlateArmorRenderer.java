package net.darkflameproduction.agotmod.armor.client.bolten;

import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenPlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class BoltenPlateArmorRenderer extends GeoArmorRenderer<BoltenPlateArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public BoltenPlateArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new BoltenPlateArmorModel());
    }
}
