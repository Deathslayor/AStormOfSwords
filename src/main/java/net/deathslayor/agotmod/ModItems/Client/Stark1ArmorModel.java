package net.deathslayor.agotmod.ModItems.Client;


import net.deathslayor.agotmod.AGoTMod;
import net.deathslayor.agotmod.ModItems.Custom.Stark1ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Stark1ArmorModel extends GeoModel<Stark1ArmorItem>{
    @Override
    public ResourceLocation getModelResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MODID, "geo/godslayer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MODID, "textures/armor/godslayer.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MODID, "animations/idle.animation.json");
    }
}
