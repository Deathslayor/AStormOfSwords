// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.wildling;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingFurArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class WildlingFurArmorModel extends GeoModel<WildlingFurArmorItem> {
    @Override
    public ResourceLocation getModelResource(WildlingFurArmorItem animatable, @Nullable GeoRenderer<WildlingFurArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/wildling_fur.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WildlingFurArmorItem animatable, @Nullable GeoRenderer<WildlingFurArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/wildling_fur.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(WildlingFurArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
