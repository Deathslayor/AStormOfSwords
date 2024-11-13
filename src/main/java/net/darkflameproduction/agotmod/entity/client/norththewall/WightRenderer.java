package net.darkflameproduction.agotmod.entity.client.norththewall;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.client.ModModelLayers;
import net.darkflameproduction.agotmod.entity.custom.norththewall.WightEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

// BearRenderer is responsible for rendering BearEntity in the game
public class WightRenderer extends MobRenderer<WightEntity, WightEntityRenderState, WightModel<WightEntityRenderState>> {

    // Constructor for BearRenderer
    public WightRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with BearModel, layer, and shadow size parameters
        super(pContext, new WightModel<>(pContext.bakeLayer(ModModelLayers.WIGHT_LAYER)), 1f);
    }

    @Override
    public WightEntityRenderState createRenderState() {
        return new WightEntityRenderState();
    }

    @Override
    public void extractRenderState(WightEntity wight, WightEntityRenderState state, float p_361424_) {
        super.extractRenderState(wight, state, p_361424_);
        state.emerginState.copyFrom(wight.emerginState);
    }

    // Get the texture location for BearEntity
    @Override
    public ResourceLocation getTextureLocation(WightEntityRenderState state) {
        return AGoTMod.id("textures/entity/wight.png");
    }

    @Override
    public void render(WightEntityRenderState state, PoseStack poseStack, MultiBufferSource buffer, int light) {
        // Check if the BearEntity is a baby
        if (state.isBaby) {
            // Scale down the rendering for baby BearEntities
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        // Call the render method of the superclass with the scaled matrix stack
        super.render(state, poseStack, buffer, light);
    }
}
