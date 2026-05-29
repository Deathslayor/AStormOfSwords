// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.stark;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class StarkPlateArmorModel extends GeoModel<StarkPlateArmorItem> {
    @Override
    public ResourceLocation getModelResource(StarkPlateArmorItem animatable) {
        // Return the resource location for the model
        return AGoTMod.id("geo/stark_plate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StarkPlateArmorItem animatable) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/stark_plate.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(StarkPlateArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}


