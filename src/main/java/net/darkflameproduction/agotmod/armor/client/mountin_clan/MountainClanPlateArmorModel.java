// This code belongs to the package net.stormofsorts.agotmod.client
package net.darkflameproduction.agotmod.armor.client.mountin_clan;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.armor.custom.mountin_clan.MountainClanPlateArmorItem;
import net.darkflameproduction.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class MountainClanPlateArmorModel extends GeoModel<MountainClanPlateArmorItem> {
    @Override
    public ResourceLocation getModelResource(MountainClanPlateArmorItem animatable, @Nullable GeoRenderer<MountainClanPlateArmorItem> renderer) {
        // Return the resource location for the model
        return AGoTMod.id("geo/mountain_clan_plate.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MountainClanPlateArmorItem animatable, @Nullable GeoRenderer<MountainClanPlateArmorItem> renderer) {
        // Return the resource location for the texture
        return AGoTMod.id("textures/item/armor/mountain_clan_plate.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(MountainClanPlateArmorItem object) {
        // Return the resource location for the animation
        return AGoTMod.id("animations/idle.animation.json");
    }
}
