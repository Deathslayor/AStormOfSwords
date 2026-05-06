package net.darkflameproduction.agotmod.armor.client.night_watch;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchRangerArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class NightsWatchRangerArmorRenderer extends GeoArmorRenderer<NightsWatchRangerArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public NightsWatchRangerArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new NightsWatchRangerArmorModel());
    }
}
