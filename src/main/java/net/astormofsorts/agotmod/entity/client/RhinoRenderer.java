package net.astormofsorts.agotmod.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.entity.custom.RhinoEntity;
import net.astormofsorts.agotmod.entity.variant.RhinoVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

// RhinoRenderer is responsible for rendering RhinoEntity in the game
public class RhinoRenderer extends MobRenderer<RhinoEntity, RhinoModel<RhinoEntity>> {
    public static final Map<RhinoVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(RhinoVariant.class), map -> {
                map.put(RhinoVariant.DEFAULT,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/rhino.png"));
                map.put(RhinoVariant.DARK,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/dark_rhino.png"));
                map.put(RhinoVariant.BLACK,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/black_rhino.png"));
                map.put(RhinoVariant.WHITE,
                        new ResourceLocation(AGoTMod.MOD_ID, "textures/entity/white_rhino.png"));

            });

    // Constructor for RhinoRenderer
    public RhinoRenderer(EntityRendererProvider.Context pContext) {
        // Call the constructor of the superclass (MobRenderer) with RhinoModel, layer, and shadow size parameters
        super(pContext, new RhinoModel<>(pContext.bakeLayer(ModModelLayers.RHINO_LAYER)), 1f);
        this.addLayer(new RhinoSaddleLayer(this, pContext.getModelSet()));
    }

    // Get the texture location for RhinoEntity
    @Override
    public @NotNull ResourceLocation getTextureLocation(RhinoEntity pEntity) {
        return LOCATION_BY_VARIANT.get(pEntity.getVariant());

    }

    // Override the render method to apply scaling for baby RhinoEntities
    @Override
    public void render(RhinoEntity pEntity, float pEntityYaw, float pPartialTicks, @NotNull PoseStack pMatrixStack,
                       @NotNull MultiBufferSource pBuffer, int pPackedLight) {
        // Check if the RhinoEntity is a baby
        if (pEntity.isBaby()) {
            // Scale down the rendering for baby RhinoEntities
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        if (pEntity.isAlive()) {
            // Scale down the rendering for baby RhinoEntities
            pMatrixStack.scale(2.5f, 2.5f, 2.5f);

        }
        if (pEntity.isDeadOrDying()) {
            // Scale down the rendering for baby RhinoEntities
            pMatrixStack.scale(2.5f, 2.5f, 2.5f);
        }


        // Call the render method of the superclass with the scaled matrix stack
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
