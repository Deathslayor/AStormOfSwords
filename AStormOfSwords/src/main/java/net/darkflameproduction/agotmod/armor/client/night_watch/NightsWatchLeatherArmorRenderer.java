package net.darkflameproduction.agotmod.armor.client.night_watch;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchLeatherArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class NightsWatchLeatherArmorRenderer extends GeoArmorRenderer<NightsWatchLeatherArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public NightsWatchLeatherArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new NightsWatchLeatherArmorModel());
    }
}
