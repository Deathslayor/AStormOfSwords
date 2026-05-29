package net.darkflameproduction.agotmod.block.custom.furniture;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class TableBlockEntityRenderer extends GeoBlockRenderer<TableBlockEntity> {

    public TableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new TableModel());
    }

    @Override
    public void preRender(PoseStack poseStack, TableBlockEntity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender,
                partialTick, packedLight, packedOverlay, colour);

        int rotation = animatable.getModelInfo().rotationY();
        if (rotation != 0) {
            poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        }
    }

    public static class TableModel extends GeoModel<TableBlockEntity> {

        @Override
        public ResourceLocation getModelResource(TableBlockEntity animatable) {
            return AGoTMod.id(animatable.getModelInfo().modelPath());
        }

        @Override
        public ResourceLocation getTextureResource(TableBlockEntity animatable) {
            return animatable.getTextureLocation();
        }

        @Override
        public ResourceLocation getAnimationResource(TableBlockEntity animatable) {
            return AGoTMod.id("animations/block/table.animation.json");
        }
    }
}

