package net.darkflameproduction.agotmod.block.custom.barrel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BarrelMediumBlockEntityRenderer extends GeoBlockRenderer<BarrelMediumBlockEntity> {

    public BarrelMediumBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new BarrelGeoModel<>("barrel_medium"));
    }

    @Override
    public void preRender(PoseStack poseStack, BarrelMediumBlockEntity animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource,
                          com.mojang.blaze3d.vertex.VertexConsumer buffer,
                          boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, colour);
        // The renderer already centres on the controller block; shift to the corner the barrel is centred on
        BlockState state = animatable.getBlockState();
        poseStack.translate(BarrelMediumBlock.centreOffsetX(state) - 0.5, 0.0, BarrelMediumBlock.centreOffsetZ(state) - 0.5);
        // Lying on its side: turn the model around its centre the way its top points
        if (BarrelOrientation.isLying(state)) {
            poseStack.mulPose(Axis.YP.rotationDegrees(BarrelOrientation.renderRotation(state.getValue(BarrelOrientation.FACING))));
        }
    }

    @Override
    public AABB getRenderBoundingBox(BarrelMediumBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        BlockState state = blockEntity.getBlockState();
        double minX = pos.getX() + BarrelMediumBlock.centreOffsetX(state) - 1;
        double minZ = pos.getZ() + BarrelMediumBlock.centreOffsetZ(state) - 1;
        return new AABB(minX, pos.getY(), minZ, minX + 2, pos.getY() + 2, minZ + 2);
    }
}
