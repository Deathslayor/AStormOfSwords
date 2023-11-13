package net.deathslayor.examplemod.ModItems.Client;


import net.deathslayor.examplemod.ModItems.Custom.Stark1ArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class Stark1ArmorRenderer extends GeoArmorRenderer<Stark1ArmorItem> {
    public Stark1ArmorRenderer() {
        super(new Stark1ArmorModel());
    }
}
