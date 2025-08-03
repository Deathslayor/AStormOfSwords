// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.bolten;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenPlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class BoltenPlateArmorModel extends GeoModel<BoltenPlateArmorItem> {
    @Override
    public ResourceLocation getModelResource(BoltenPlateArmorItem animatable, @Nullable GeoRenderer<BoltenPlateArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/bolten_plate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoltenPlateArmorItem animatable, @Nullable GeoRenderer<BoltenPlateArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/bolten_plate.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(BoltenPlateArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
