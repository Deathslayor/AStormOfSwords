package net.astormofsorts.agotmod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.entity.custom.MammothEntity;
import net.astormofsorts.agotmod.entity.variant.MammothVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

// MammothRenderer is responsible for rendering MammothEntity in the game
public class MammothRenderer extends MobRenderer<MammothEntity, MammothModel<MammothEntity>> {
    public static final Map<MammothVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MammothVariant.class), map -> {
                map.put(MammothVariant.DEFAULT,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/mammoth.png"));
                map.put(MammothVariant.DARK,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/dark_mammoth.png"));
                map.put(MammothVariant.BLACK,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/black_mammoth.png"));
                map.put(MammothVariant.WHITE,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/white_mammoth.png"));
            });

    // Constructor for MammothRenderer
    public MammothRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with MammothModel, layer, and shadow size parameters
        super(pContext, new MammothModel<>(pContext.bakeLayer(ModModelLayers.MAMMOTH_LAYER)), 1f);
    }

    // Get the texture location for MammothEntity
    @Override
    public ResourceLocation getTextureLocation(MammothEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());
    }

    // Override the render method to apply scaling for baby MammothEntities
    @Override
    public void render(MammothEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        // Check if the MammothEntity is a baby
        if (pEntity.isBaby()) {
            // Scale down the rendering for baby MammothEntities
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        if (pEntity.isAlive()) {
            // Scale up the rendering for alive MammothEntities
            pMatrixStack.scale(2.5f, 2.5f, 2.5f);
        }
        if (pEntity.isDeadOrDying()) {
            // Scale up the rendering for dead or dying MammothEntities
            pMatrixStack.scale(2.5f, 2.5f, 2.5f);
        }

        // Call the render method of the superclass with the scaled matrix stack
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
