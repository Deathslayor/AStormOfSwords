package net.astormofsorts.agotmod.armor.client.manderly;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class ManderlyLevyArmorRenderer extends GeoArmorRenderer<ManderlyLevyArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public ManderlyLevyArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new ManderlyLevyArmorModel());
    }
}
