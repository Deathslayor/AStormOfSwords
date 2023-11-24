package net.stormofsorts.agotmod.client;


import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.custom.Stark1ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class Stark1ArmorModel extends GeoModel<Stark1ArmorItem>{
    @Override
    public ResourceLocation getModelResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MOD_ID, "geo/stark1.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/item/armor/stark1.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Stark1ArmorItem object) {
        return new ResourceLocation(AGoTMod.MOD_ID, "animations/idle.animation.json");
    }
}
