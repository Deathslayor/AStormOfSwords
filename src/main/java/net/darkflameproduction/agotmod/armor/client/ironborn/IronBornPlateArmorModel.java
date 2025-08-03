// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.ironborn;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.ironborn.IronBornPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class IronBornPlateArmorModel extends GeoModel<IronBornPlateArmorItem> {
    @Override
    public ResourceLocation getModelResource(IronBornPlateArmorItem animatable, @Nullable GeoRenderer<IronBornPlateArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/ironborn_plate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IronBornPlateArmorItem animatable, @Nullable GeoRenderer<IronBornPlateArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/ironborn_plate.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(IronBornPlateArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
