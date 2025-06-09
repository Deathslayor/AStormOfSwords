// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.mountin_clan;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanLevyArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkNoblePlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class MountainClanLevyArmorModel extends GeoModel<MountainClanLevyArmorItem> {
    @Override
    public ResourceLocation getModelResource(MountainClanLevyArmorItem animatable, @Nullable GeoRenderer<MountainClanLevyArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/mountain_clan_levy.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MountainClanLevyArmorItem animatable, @Nullable GeoRenderer<MountainClanLevyArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/mountain_clan_levy.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(MountainClanLevyArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
