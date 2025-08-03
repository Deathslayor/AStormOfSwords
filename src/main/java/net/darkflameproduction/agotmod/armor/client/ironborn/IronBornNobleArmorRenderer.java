package net.darkflameproduction.agotmod.armor.client.ironborn;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornNobleArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class IronBornNobleArmorRenderer extends GeoArmorRenderer<IronBornNobleArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public IronBornNobleArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new IronBornNobleArmorModel());
    }
}
