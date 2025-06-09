package net.darkflameproduction.agotmod.armor.client.ironborn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class IronBornPlateArmorRenderer extends GeoArmorRenderer<IronBornPlateArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public IronBornPlateArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new IronBornPlateArmorModel());
    }
}
