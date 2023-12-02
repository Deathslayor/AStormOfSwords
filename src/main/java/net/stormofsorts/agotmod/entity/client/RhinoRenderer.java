package net.stormofsorts.agotmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.entity.custom.RhinoEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

// RhinoRenderer is responsible for rendering RhinoEntity in the game
public class RhinoRenderer extends MobRenderer<RhinoEntity, RhinoModel<RhinoEntity>> {

    // Constructor for RhinoRenderer
    public RhinoRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with RhinoModel, layer, and shadow size parameters
        super(pContext, new RhinoModel<>(pContext.bakeLayer(ModModelLayers.RHINO_LAYER)), 2f);
    }

    // Get the texture location for RhinoEntity
    @Override
    public ResourceLocation getTextureLocation(RhinoEntity pEntity) {
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/rhino.png");
    }

    // Override the render method to apply scaling for baby RhinoEntities
    @Override
    public void render(RhinoEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        // Check if the RhinoEntity is a baby
        if (pEntity.isBaby()) {
            // Scale down the rendering for baby RhinoEntities
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        // Call the render method of the superclass with the scaled matrix stack
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
