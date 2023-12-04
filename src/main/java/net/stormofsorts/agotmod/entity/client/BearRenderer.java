package net.stormofsorts.agotmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.entity.custom.BearEntity;

// BearRenderer is responsible for rendering BearEntity in the game
public class BearRenderer extends MobRenderer<BearEntity, BearModel<BearEntity>> {

    // Constructor for BearRenderer
    public BearRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with BearModel, layer, and shadow size parameters
        super(pContext, new BearModel<>(pContext.bakeLayer(ModModelLayers.BEAR_LAYER)), 1f);
    }

    // Get the texture location for BearEntity
    @Override
    public ResourceLocation getTextureLocation(BearEntity pEntity) {
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/bear.png");
    }

    // Override the render method to apply scaling for baby BearEntities
    @Override
    public void render(BearEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        // Check if the BearEntity is a baby
        if (pEntity.isBaby()) {
            // Scale down the rendering for baby BearEntities
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        // Call the render method of the superclass with the scaled matrix stack
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
