// This code belongs to the package net.stormofsorts.agotmod.client
package net.stormofsorts.agotmod.armor.client;

// Importing necessary classes from other packages
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.armor.custom.Stark1ArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class Stark1ArmorModel extends GeoModel<Stark1ArmorItem> {

    // Override method to get the model resource location
    @Override
    public ResourceLocation getModelResource(Stark1ArmorItem object) {
        // Return the resource location for the model
        return new ResourceLocation(AGoTMod.MOD_ID, "geo/stark1.geo.json");
    }

    // Override method to get the texture resource location
    @Override
    public ResourceLocation getTextureResource(Stark1ArmorItem object) {
        // Return the resource location for the texture
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/item/armor/stark1.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(Stark1ArmorItem object) {
        // Return the resource location for the animation
        return new ResourceLocation(AGoTMod.MOD_ID, "animations/idle.animation.json");
    }
}
