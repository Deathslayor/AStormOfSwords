// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.bolten;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class BoltenLevyArmorModel extends GeoModel<BoltenLevyArmorItem> {
    @Override
    public ResourceLocation getModelResource(BoltenLevyArmorItem animatable, @Nullable GeoRenderer<BoltenLevyArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/bolten_levy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoltenLevyArmorItem animatable, @Nullable GeoRenderer<BoltenLevyArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/bolten_levy.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(BoltenLevyArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
