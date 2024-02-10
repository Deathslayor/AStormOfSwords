package net.astormofsorts.agotmod.armor.client.stark;// Importing necessary classes from other packages
import net.astormofsorts.agotmod.armor.custom.stark.StarkPlateArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class StarkPlateArmorRenderer extends GeoArmorRenderer<StarkPlateArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public StarkPlateArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new StarkPlateArmorModel());
    }
}
