package net.darkflameproduction.agotmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MammothSaddleLayer extends RenderLayer<MammothRenderState, MammothModel> {
    private static final ResourceLocation SADDLE_TEXTURE = AGoTMod.id("textures/entity/horse/armor/horse_armor_iron_horse_armor.png");
    private final MammothModel model;

    public MammothSaddleLayer(RenderLayerParent<MammothRenderState, MammothModel> pRenderer, @NotNull EntityModelSet pModelSet) {
        super(pRenderer);
        this.model = new MammothModel(pModelSet.bakeLayer(ModModelLayers.MAMMOTH_LAYER));
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int p_116668_, @NotNull MammothRenderState state, float p_116670_, float p_116671_) {
        if (state.hasSaddle) {
            // TODO: check color
            coloredCutoutModelCopyLayerRender(model, SADDLE_TEXTURE, poseStack, bufferSource, p_116668_, state, -1);
        }
    }
}
