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

public class ChairBlockEntityRenderer extends GeoBlockRenderer<ChairBlockEntity> {

    public ChairBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new ChairModel());
    }

    @Override
    public void preRender(PoseStack poseStack, ChairBlockEntity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender,
                partialTick, packedLight, packedOverlay, colour);
        int rotation = animatable.getRotationY();
        if (rotation != 0) {
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        }
    }

    public static class ChairModel extends GeoModel<ChairBlockEntity> {

        @Override
        public ResourceLocation getModelResource(ChairBlockEntity animatable,
                                                 @Nullable GeoRenderer<ChairBlockEntity> renderer) {
            return AGoTMod.id("geo/block/chair.geo.json");
        }

        @Override
        public ResourceLocation getTextureResource(ChairBlockEntity animatable,
                                                   @Nullable GeoRenderer<ChairBlockEntity> renderer) {
            return animatable.getTextureLocation();
        }

        @Override
        public ResourceLocation getAnimationResource(ChairBlockEntity animatable) {
            return AGoTMod.id("animations/block/chair.animation.json");
        }
    }
}