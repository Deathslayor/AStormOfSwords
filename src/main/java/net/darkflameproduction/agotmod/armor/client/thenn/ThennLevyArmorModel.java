// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.thenn;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.thenn.ThennLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class ThennLevyArmorModel extends GeoModel<ThennLevyArmorItem> {
    @Override
    public ResourceLocation getModelResource(ThennLevyArmorItem animatable, @Nullable GeoRenderer<ThennLevyArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/thenn_levy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ThennLevyArmorItem animatable, @Nullable GeoRenderer<ThennLevyArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/thenn_levy.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ThennLevyArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
