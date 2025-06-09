// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.manderly;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class ManderlyLevyArmorModel extends GeoModel<ManderlyLevyArmorItem> {
    @Override
    public ResourceLocation getModelResource(ManderlyLevyArmorItem animatable, @Nullable GeoRenderer<ManderlyLevyArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/manderly_levy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ManderlyLevyArmorItem animatable, @Nullable GeoRenderer<ManderlyLevyArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/manderly_levy.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ManderlyLevyArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
