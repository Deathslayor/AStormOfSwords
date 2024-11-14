package net.darkflameproduction.agotmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.BearEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

// BearRenderer is responsible for rendering BearEntity in the game
public class BearRenderer extends MobRenderer<BearEntity, LivingEntityRenderState, BearModel<LivingEntityRenderState>> {

    // Constructor for BearRenderer
    public BearRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with BearModel, layer, and shadow size parameters
        super(pContext, new BearModel<>(pContext.bakeLayer(ModModelLayers.BEAR_LAYER)), 1f);
    }

    @Override
    public @NotNull LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    // Get the texture location for BearEntity
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull LivingEntityRenderState state) {
        return AGoTMod.id("textures/entity/bear.png");
    }

    @Override
    public void render(LivingEntityRenderState state, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int light) {
        // Check if the BearEntity is a baby
        if (state.isBaby) {
            // Scale down the rendering for baby BearEntities
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        // Call the render method of the superclass with the scaled matrix stack
        super.render(state, poseStack, buffer, light);
    }
}
