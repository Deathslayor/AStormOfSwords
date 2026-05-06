// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.ironborn;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class IronBornLevyArmorModel extends GeoModel<IronBornLevyArmorItem> {
    @Override
    public ResourceLocation getModelResource(IronBornLevyArmorItem animatable, @Nullable GeoRenderer<IronBornLevyArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/ironborn_levy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IronBornLevyArmorItem animatable, @Nullable GeoRenderer<IronBornLevyArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/ironborn_levy.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(IronBornLevyArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
