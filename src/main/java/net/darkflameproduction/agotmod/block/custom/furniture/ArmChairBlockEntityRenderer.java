package net.darkflameproduction.agotmod.block.custom.furniture;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class ArmChairBlockEntityRenderer extends GeoBlockRenderer<ArmChairBlockEntity> {

    public ArmChairBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new ArmChairModel());
    }

    @Override
    public void preRender(PoseStack poseStack, ArmChairBlockEntity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender,
                partialTick, packedLight, packedOverlay, colour);
        int rotation = animatable.getRotationY();
        if (rotation != 0) {
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        }
    }

    public static class ArmChairModel extends GeoModel<ArmChairBlockEntity> {

        @Override
        public ResourceLocation getModelResource(ArmChairBlockEntity animatable,
                                                 @Nullable GeoRenderer<ArmChairBlockEntity> renderer) {
            return AGoTMod.id("geo/block/arm_chair.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(ArmChairBlockEntity animatable,
                                                   @Nullable GeoRenderer<ArmChairBlockEntity> renderer) {
            return animatable.getTextureLocation();
        }

        @Override
        public ResourceLocation getAnimationResource(ArmChairBlockEntity animatable) {
            return AGoTMod.id("animations/block/arm_chair.animation.json");
        }
    }
}