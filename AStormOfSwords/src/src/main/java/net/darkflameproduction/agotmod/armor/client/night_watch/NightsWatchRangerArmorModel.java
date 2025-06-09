// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.night_watch;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchRangerArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class NightsWatchRangerArmorModel extends GeoModel<NightsWatchRangerArmorItem> {
    @Override
    public ResourceLocation getModelResource(NightsWatchRangerArmorItem animatable, @Nullable GeoRenderer<NightsWatchRangerArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/nights_watch_ranger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NightsWatchRangerArmorItem animatable, @Nullable GeoRenderer<NightsWatchRangerArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/nights_watch_ranger.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(NightsWatchRangerArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
