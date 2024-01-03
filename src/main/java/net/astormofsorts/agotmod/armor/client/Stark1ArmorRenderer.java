package net.astormofsorts.agotmod.armor.client;// Importing necessary classes from other packages
import net.astormofsorts.agotmod.armor.custom.Stark1ArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class Stark1ArmorRenderer extends GeoArmorRenderer<Stark1ArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public Stark1ArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new Stark1ArmorModel());
    }
}
