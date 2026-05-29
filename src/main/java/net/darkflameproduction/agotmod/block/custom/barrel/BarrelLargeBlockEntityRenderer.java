package net.darkflameproduction.agotmod.block.custom.barrel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BarrelLargeBlockEntityRenderer extends GeoBlockRenderer<BarrelLargeBlockEntity> {

    public BarrelLargeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(AGoTMod.id("barrel_large")));
    }

    @Override
    public void preRender(PoseStack poseStack, BarrelLargeBlockEntity animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource,
                          com.mojang.blaze3d.vertex.VertexConsumer buffer,
                          boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, colour);
        poseStack.translate(1.0f, 0.0f, 1.0f);
    }

    @Override
    public AABB getRenderBoundingBox(BarrelLargeBlockEntity blockEntity) {
        return new AABB(
                blockEntity.getBlockPos().getX(),
                blockEntity.getBlockPos().getY(),
                blockEntity.getBlockPos().getZ(),
                blockEntity.getBlockPos().getX() + 3,
                blockEntity.getBlockPos().getY() + 3,
                blockEntity.getBlockPos().getZ() + 3
        );
    }
}
