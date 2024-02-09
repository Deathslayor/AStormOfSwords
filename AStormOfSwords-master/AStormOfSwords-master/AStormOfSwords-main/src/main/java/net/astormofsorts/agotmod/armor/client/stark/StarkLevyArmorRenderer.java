package net.astormofsorts.agotmod.armor.client.stark;// Importing necessary classes from other packages
import net.astormofsorts.agotmod.armor.custom.stark.StarkLevyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class StarkLevyArmorRenderer extends GeoArmorRenderer<StarkLevyArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public StarkLevyArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new StarkLevyArmorModel());
    }
}
