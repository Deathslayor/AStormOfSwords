package net.astormofsorts.agotmod.armor.client.bolten;
import net.astormofsorts.agotmod.armor.client.manderly.ManderlyLevyArmorModel;
import net.astormofsorts.agotmod.armor.custom.bolten.BoltenLevyArmorItem;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class BoltenLevyArmorRenderer extends GeoArmorRenderer<BoltenLevyArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public BoltenLevyArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new BoltenLevyArmorModel());
    }
}
