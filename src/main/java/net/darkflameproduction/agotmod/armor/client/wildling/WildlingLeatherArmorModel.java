// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.wildling;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.wildling.WildlingLeatherArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class WildlingLeatherArmorModel extends GeoModel<WildlingLeatherArmorItem> {
    @Override
    public ResourceLocation getModelResource(WildlingLeatherArmorItem animatable, @Nullable GeoRenderer<WildlingLeatherArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/wildling_leather.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WildlingLeatherArmorItem animatable, @Nullable GeoRenderer<WildlingLeatherArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/wildling_leather.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(WildlingLeatherArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
