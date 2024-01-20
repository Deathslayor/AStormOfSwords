// This code belongs to the package net.stormofsorts.agotmod.client
package net.astormofsorts.agotmod.armor.client.manderly;

// Importing necessary classes from other packages
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyLevyArmorItem;
import net.astormofsorts.agotmod.armor.custom.manderly.ManderlyNobleArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

// Stark1ArmorModel class extending GeoModel for Stark1ArmorItem
public class ManderlyNobleArmorModel extends GeoModel<ManderlyNobleArmorItem> {

    // Override method to get the model resource location
    @Override
    public ResourceLocation getModelResource(ManderlyNobleArmorItem object) {
        // Return the resource location for the model
        return new ResourceLocation(AGoTMod.MOD_ID, "geo/manderly_noble.geo.json");
    }

    // Override method to get the texture resource location
    @Override
    public ResourceLocation getTextureResource(ManderlyNobleArmorItem object) {
        // Return the resource location for the texture
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/item/armor/manderly_noble.png");
    }

    // Override method to get the animation resource location
    @Override
    public ResourceLocation getAnimationResource(ManderlyNobleArmorItem object) {
        // Return the resource location for the animation
        return new ResourceLocation(AGoTMod.MOD_ID, "animations/idle.animation.json");
    }
}
