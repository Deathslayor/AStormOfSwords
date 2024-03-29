// This code belongs to the package net.stormofsorts.agotmod.client
package net.astormofsorts.agotmod.armor.client.manderly;

// Importing necessary classes from other packages

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class ManderlyLevyArmorModel extends GeoModel<ManderlyLevyArmorItem> {

    // Override method to get the model resource location
    @Override
    public ResourceLocation getModelResource(ManderlyLevyArmorItem object) {
        // Return the resource location for the model
        return new ResourceLocation(AGoTMod.MOD_ID, "geo/manderly_levy.geo.json");
    }

    // Override method to get the texture resource location
    @Override
    public ResourceLocation getTextureResource(ManderlyLevyArmorItem object) {
        // Return the resource location for the texture
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/item/armor/manderly_levy.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ManderlyLevyArmorItem object) {
        // Return the resource location for the animation
        return new ResourceLocation(AGoTMod.MOD_ID, "animations/idle.animation.json");
    }
}
