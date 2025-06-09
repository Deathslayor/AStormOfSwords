// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.manderly;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.manderly.ManderlyNobleArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class ManderlyNobleArmorModel extends GeoModel<ManderlyNobleArmorItem> {
    @Override
    public ResourceLocation getModelResource(ManderlyNobleArmorItem animatable, @Nullable GeoRenderer<ManderlyNobleArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/manderly_noble.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ManderlyNobleArmorItem animatable, @Nullable GeoRenderer<ManderlyNobleArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/manderly_noble.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ManderlyNobleArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
