// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.night_watch;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.night_watch.NightsWatchEliteArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class NightsWatchEliteArmorModel extends GeoModel<NightsWatchEliteArmorItem> {
    @Override
    public ResourceLocation getModelResource(NightsWatchEliteArmorItem animatable, @Nullable GeoRenderer<NightsWatchEliteArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/nights_watch_elite.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NightsWatchEliteArmorItem animatable, @Nullable GeoRenderer<NightsWatchEliteArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/nights_watch_elite.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(NightsWatchEliteArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
