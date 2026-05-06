// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.manderly;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyPlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class ManderlyPlateArmorModel extends GeoModel<ManderlyPlateArmorItem> {
    @Override
    public ResourceLocation getModelResource(ManderlyPlateArmorItem animatable, @Nullable GeoRenderer<ManderlyPlateArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/manderly_plate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ManderlyPlateArmorItem animatable, @Nullable GeoRenderer<ManderlyPlateArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/manderly_plate.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ManderlyPlateArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
