package net.darkflameproduction.agotmod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.MammothEntity;
import net.darkflameproduction.agotmod.entity.variant.MammothVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

// MammothRenderer is responsible for rendering MammothEntity in the game
public class MammothRenderer extends MobRenderer<MammothEntity, MammothRenderState, MammothModel<MammothRenderState>> {
    public static final Map<MammothVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MammothVariant.class), map -> {
                map.put(MammothVariant.DEFAULT,
                        AGoTMod.id("textures/entity/mammoth.png"));
                map.put(MammothVariant.DARK,
                        AGoTMod.id("textures/entity/dark_mammoth.png"));
                map.put(MammothVariant.BLACK,
                        AGoTMod.id("textures/entity/black_mammoth.png"));
                map.put(MammothVariant.WHITE,
                        AGoTMod.id("textures/entity/white_mammoth.png"));
            });

    // Constructor for MammothRenderer
    public MammothRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with MammothModel, layer, and shadow size parameters
        super(pContext, new MammothModel<>(pContext.bakeLayer(ModModelLayers.MAMMOTH_LAYER)), 1f);
    }

    @Override
    public void render(MammothRenderState state, PoseStack poseStack, MultiBufferSource buffer, int light) {
        // Check if the MammothEntity is a baby
        if (state.isBaby) {
            // Scale down the rendering for baby MammothEntities
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
        if (state.deathTime > 0) {
            // Scale up the rendering for dead or dying MammothEntities
            poseStack.scale(2.5f, 2.5f, 2.5f);
        } else {
            poseStack.scale(2.5f, 2.5f, 2.5f);
        }

        // Call the render method of the superclass with the scaled matrix stack
        super.render(state, poseStack, buffer, light);
    }

    @Override
    public MammothRenderState createRenderState() {
        return new MammothRenderState();
    }

    @Override
    public void extractRenderState(MammothEntity entity, MammothRenderState state, float p_361157_) {
        super.extractRenderState(entity, state, p_361157_);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.attackAnimationState.copyFrom(entity.attackAnimationState);
        state.variant = entity.getVariant();
    }

    @Override
    public ResourceLocation getTextureLocation(MammothRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }
}
