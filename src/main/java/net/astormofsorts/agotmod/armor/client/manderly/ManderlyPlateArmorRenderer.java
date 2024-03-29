package net.astormofsorts.agotmod.armor.client.manderly;

import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyPlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ManderlyPlateArmorRenderer extends GeoArmorRenderer<ManderlyPlateArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public ManderlyPlateArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new ManderlyPlateArmorModel());
    }
}
