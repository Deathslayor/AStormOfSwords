package net.darkflameproduction.agotmod.armor.client.ironborn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornLevyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class IronBornLevyArmorRenderer extends GeoArmorRenderer<IronBornLevyArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public IronBornLevyArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new IronBornLevyArmorModel());
    }
}
