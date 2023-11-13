package net.deathslayor.examplemod.ModItems.Client;


import net.deathslayor.examplemod.ExampleMod;
import net.deathslayor.examplemod.ModItems.Custom.Stark1ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Stark1ArmorModel extends GeoModel<Stark1ArmorItem>{
    @Override
    public ResourceLocation getModelResource(Stark1ArmorItem object) {
        return new ResourceLocation(ExampleMod.MODID, "geo/godslayer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Stark1ArmorItem object) {
        return new ResourceLocation(ExampleMod.MODID, "textures/armor/godslayer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Stark1ArmorItem object) {
        return new ResourceLocation(ExampleMod.MODID, "animations/idle.animation.json");
    }
}
