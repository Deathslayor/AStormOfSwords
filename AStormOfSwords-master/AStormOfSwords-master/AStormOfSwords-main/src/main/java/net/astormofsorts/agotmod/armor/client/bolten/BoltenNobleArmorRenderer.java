package net.astormofsorts.agotmod.armor.client.bolten;
import net.astormofsorts.agotmod.armor.client.manderly.ManderlyNobleArmorModel;
import net.astormofsorts.agotmod.armor.custom.bolten.BoltenNobleArmorItem;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyNobleArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

// Stark1ArmorRenderer class extending GeoArmorRenderer for Stark1ArmorItem
public class BoltenNobleArmorRenderer extends GeoArmorRenderer<BoltenNobleArmorItem> {

    // Constructor for Stark1ArmorRenderer
    public BoltenNobleArmorRenderer() {
        // Call the constructor of the superclass (GeoArmorRenderer) with a new instance of Stark1ArmorModel
        super(new BoltenNobleArmorModel());
    }
}
