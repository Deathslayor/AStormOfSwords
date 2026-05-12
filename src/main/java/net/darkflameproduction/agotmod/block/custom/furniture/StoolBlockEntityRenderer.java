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

public class StoolBlockEntityRenderer extends GeoBlockRenderer<StoolBlockEntity> {

    public StoolBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new StoolModel());
    }

    @Override
    public void preRender(PoseStack poseStack, StoolBlockEntity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender,
                partialTick, packedLight, packedOverlay, colour);
        int rotation = animatable.getRotationY();
        if (rotation != 0) {
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        }
    }

    public static class StoolModel extends GeoModel<StoolBlockEntity> {

        @Override
        public ResourceLocation getModelResource(StoolBlockEntity animatable,
                                                 @Nullable GeoRenderer<StoolBlockEntity> renderer) {
            return AGoTMod.id("geo/block/stool.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(StoolBlockEntity animatable,
                                                   @Nullable GeoRenderer<StoolBlockEntity> renderer) {
            return animatable.getTextureLocation();
        }

        @Override
        public ResourceLocation getAnimationResource(StoolBlockEntity animatable) {
            return AGoTMod.id("animations/block/stool.animation.json");
        }
    }
}