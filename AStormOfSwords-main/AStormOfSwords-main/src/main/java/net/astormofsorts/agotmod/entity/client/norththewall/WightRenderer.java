package net.astormofsorts.agotmod.entity.client.norththewall;

import com.mojang.blaze3d.vertex.PoseStack;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.entity.client.norththewall.WightModel;
import net.astormofsorts.agotmod.entity.client.ModModelLayers;
import net.astormofsorts.agotmod.entity.custom.BearEntity;
import net.astormofsorts.agotmod.entity.custom.norththewall.WightEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

// BearRenderer is responsible for rendering BearEntity in the game
public class WightRenderer extends MobRenderer<WightEntity, WightModel<WightEntity>> {

    // Constructor for BearRenderer
    public WightRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with BearModel, layer, and shadow size parameters
        super(pContext, new WightModel<>(pContext.bakeLayer(ModModelLayers.WIGHT_LAYER)), 1f);
    }

    // Get the texture location for BearEntity
    @Override
    public ResourceLocation getTextureLocation(WightEntity pEntity) {
        return new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/wight.png");
    }

    // Override the render method to apply scaling for baby BearEntities
    @Override
    public void render(WightEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
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
