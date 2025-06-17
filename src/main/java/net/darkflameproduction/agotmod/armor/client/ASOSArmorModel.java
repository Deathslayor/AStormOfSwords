package net.darkflameproduction.agotmod.armor.client;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.ASOSArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.base.GeoRenderState;

public final class ASOSArmorModel extends GeoModel<ASOSArmorItem> {
    private final String name;

    public ASOSArmorModel(String name) {
        this.name = name;
    }

    @Override
    public ResourceLocation getModelResource(GeoRenderState renderState) {
        // Return the resource location for the model
        return AGoTMod.id("geo/" + name + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoRenderState renderState) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/" + name + ".png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ASOSArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
