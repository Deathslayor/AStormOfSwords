package net.darkflameproduction.agotmod.entity.client.npc;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Northern_Peasant_Renderer extends GeoEntityRenderer<Northern_Peasant_Entity> {

    public Northern_Peasant_Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Northern_Peasant_Model());
    }

    @Override
    public ResourceLocation getTextureLocation(Northern_Peasant_Entity entity) {
        return ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northern_peasant.png");
    }
}