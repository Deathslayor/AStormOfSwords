// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.mountin_clan;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanChiefArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class MountainClanChiefArmorModel extends GeoModel<MountainClanChiefArmorItem> {
    @Override
    public ResourceLocation getModelResource(MountainClanChiefArmorItem animatable, @Nullable GeoRenderer<MountainClanChiefArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/mountain_clan_chief.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MountainClanChiefArmorItem animatable, @Nullable GeoRenderer<MountainClanChiefArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/mountain_clan_chief.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(MountainClanChiefArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
