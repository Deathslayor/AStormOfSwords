package net.darkflameproduction.agotmod.client;


import net.darkflameproduction.agotmod.custom.Stark1ArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class Stark1ArmorRenderer extends GeoArmorRenderer<Stark1ArmorItem> {
    public Stark1ArmorRenderer() {
        super(new Stark1ArmorModel());
    }
}
