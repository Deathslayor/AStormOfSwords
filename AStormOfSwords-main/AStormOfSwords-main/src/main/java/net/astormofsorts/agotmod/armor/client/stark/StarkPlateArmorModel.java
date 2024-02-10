// This code belongs to the package net.stormofsorts.agotmod.client
package net.astormofsorts.agotmod.armor.client.stark;

// Importing necessary classes from other packages
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.armor.custom.stark.StarkPlateArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class StarkPlateArmorModel extends GeoModel<StarkPlateArmorItem> {

    // Override method to get the model resource location
    @Override
    public ResourceLocation getModelResource(StarkPlateArmorItem object) {
        // Return the resource location for the model
        return new ResourceLocation(AGoTMod.MOD_ID, "geo/stark_plate.geo.json");
    }

    // Override method to get the texture resource location
    @Override
    public ResourceLocation getTextureResource(StarkPlateArmorItem object) {
        // Return the resource location for the texture
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/item/armor/stark_plate.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(StarkPlateArmorItem object) {
        // Return the resource location for the animation
        return new ResourceLocation(AGoTMod.MOD_ID, "animations/idle.animation.json");
    }
}
