// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.stark;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class StarkLevyArmorModel extends GeoModel<StarkLevyArmorItem> {
    @Override
    public ResourceLocation getModelResource(StarkLevyArmorItem animatable, @Nullable GeoRenderer<StarkLevyArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/stark_levy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StarkLevyArmorItem animatable, @Nullable GeoRenderer<StarkLevyArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/stark_levy.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(StarkLevyArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
