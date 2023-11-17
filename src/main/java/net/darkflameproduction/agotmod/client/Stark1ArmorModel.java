package net.darkflameproduction.agotmod.client;


import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.custom.Stark1ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Stark1ArmorModel extends GeoModel<Stark1ArmorItem>{
    @Override
    public ResourceLocation getModelResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MOD_ID, "geo/stark1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/armor/stark1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MOD_ID, "animations/idle.animation.json");
    }
}
