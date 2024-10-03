package net.astormofsorts.agotmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.astormofsorts.agotmod.entity.custom.MammothEntity;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static net.astormofsorts.agotmod.AGoTMod.MOD_ID;

public class MammothSaddleLayer extends RenderLayer<MammothEntity, MammothModel<MammothEntity>> {
    private final MammothModel<MammothEntity> model;
    private static final ResourceLocation SADDLE_TEXTURE = new ResourceLocation(MOD_ID, "textures/entity/horse/armor/horse_armor_iron_horse_armor.png");

    public MammothSaddleLayer(MammothRenderer mammothRenderer, EntityModelSet pModelSet) {
        super(mammothRenderer);
        this.model = new MammothModel<>(pModelSet.bakeLayer(ModModelLayers.MAMMOTH_LAYER));
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight, @NotNull MammothEntity pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        if (pLivingEntity.getVehicle() != null) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
            this.model.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

            VertexConsumer vertexconsumer = pBuffer.getBuffer(RenderType.entityCutoutNoCull(SADDLE_TEXTURE));
            this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
