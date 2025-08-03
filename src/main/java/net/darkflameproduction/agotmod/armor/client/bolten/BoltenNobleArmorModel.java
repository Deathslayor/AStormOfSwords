// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.bolten;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.bolten.BoltenNobleArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class BoltenNobleArmorModel extends GeoModel<BoltenNobleArmorItem> {
    @Override
    public ResourceLocation getModelResource(BoltenNobleArmorItem animatable, @Nullable GeoRenderer<BoltenNobleArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/bolten_noble.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BoltenNobleArmorItem animatable, @Nullable GeoRenderer<BoltenNobleArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/bolten_noble.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(BoltenNobleArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
