// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.thenn;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennPlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class ThennPlateArmorModel extends GeoModel<ThennPlateArmorItem> {
    @Override
    public ResourceLocation getModelResource(ThennPlateArmorItem animatable, @Nullable GeoRenderer<ThennPlateArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/thenn_plate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ThennPlateArmorItem animatable, @Nullable GeoRenderer<ThennPlateArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/thenn_plate.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ThennPlateArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
