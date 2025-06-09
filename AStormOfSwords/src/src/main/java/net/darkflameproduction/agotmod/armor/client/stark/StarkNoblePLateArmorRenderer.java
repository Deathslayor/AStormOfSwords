package net.darkflameproduction.agotmod.armor.client.stark;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class StarkNoblePLateArmorRenderer extends GeoArmorRenderer<StarkNoblePlateArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public StarkNoblePLateArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new StarkNoblePlateArmorModel());
    }
}
