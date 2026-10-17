package net.darkflameproduction.agotmod.block.custom.barrel;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/**
 * Upright, the controller is the bottom-centre block, so the model renders centred on it without extra offset.
 * Lying against a wall, the barrel's centre is one block out from the controller (BarrelLargeBlock#centreOffset).
 */
public class BarrelLargeBlockEntityRenderer extends GeoBlockRenderer<BarrelLargeBlockEntity> {

    public BarrelLargeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new BarrelGeoModel<>("barrel_large"));
    }

    @Override
    public void preRender(PoseStack poseStack, BarrelLargeBlockEntity animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer,
                          boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, colour);
        BlockState state = animatable.getBlockState();
        if (BarrelOrientation.isLying(state)) {
            Direction facing = state.getValue(BarrelOrientation.FACING);
            BlockPos centre = BarrelLargeBlock.centreOffset(facing);
            poseStack.translate(centre.getX(), 0.0, centre.getZ());
            poseStack.mulPose(Axis.YP.rotationDegrees(BarrelOrientation.renderRotation(facing)));
        }
    }

    @Override
    public AABB getRenderBoundingBox(BarrelLargeBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos()
                .offset(BarrelLargeBlock.centreOffset(blockEntity.getBlockState().getValue(BarrelOrientation.FACING)));
        return new AABB(
                pos.getX() - 1, pos.getY(),     pos.getZ() - 1,
                pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2
        );
    }
}
