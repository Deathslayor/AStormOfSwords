package net.darkflameproduction.agotmod.armor.client.thenn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennNobleArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ThennNobleArmorRenderer extends GeoArmorRenderer<ThennNobleArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public ThennNobleArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new ThennNobleArmorModel());
    }
}
