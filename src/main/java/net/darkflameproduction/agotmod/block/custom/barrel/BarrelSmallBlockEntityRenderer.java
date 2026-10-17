package net.darkflameproduction.agotmod.block.custom.barrel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BarrelSmallBlockEntityRenderer extends GeoBlockRenderer<BarrelSmallBlockEntity> {

    public BarrelSmallBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new BarrelGeoModel<>("barrel_small"));
    }

    @Override
    public void preRender(PoseStack poseStack, BarrelSmallBlockEntity animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer,
                          boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, colour);
        // Lying on its side: turn the model the way its top points
        BlockState state = animatable.getBlockState();
        if (BarrelOrientation.isLying(state)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(BarrelOrientation.renderRotation(state.getValue(BarrelOrientation.FACING))));
        }
    }
}
