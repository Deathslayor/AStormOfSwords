package net.deathslayor.agotmod.ModItems.Client;


import net.deathslayor.agotmod.ModItems.Custom.Stark1ArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class Stark1ArmorRenderer extends GeoArmorRenderer<Stark1ArmorItem> {
    public Stark1ArmorRenderer() {
        super(new Stark1ArmorModel());
    }
}
