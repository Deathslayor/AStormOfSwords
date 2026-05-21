package net.darkflameproduction.agotmod.block.custom.barrel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BarrelMediumBlockEntityRenderer extends GeoBlockRenderer<BarrelMediumBlockEntity> {

    public BarrelMediumBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(AGoTMod.id("barrel_medium")));
    }

    @Override
    public void preRender(PoseStack poseStack, BarrelMediumBlockEntity animatable,
                          BakedGeoModel model, MultiBufferSource bufferSource,
                          com.mojang.blaze3d.vertex.VertexConsumer buffer,
                          boolean isReRender, float partialTick,
                          int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, colour);
        poseStack.translate(0.5f, 0.0f, 0.5f);
    }

    @Override
    public AABB getRenderBoundingBox(BarrelMediumBlockEntity blockEntity) {
        return new AABB(
                blockEntity.getBlockPos().getX(),
                blockEntity.getBlockPos().getY(),
                blockEntity.getBlockPos().getZ(),
                blockEntity.getBlockPos().getX() + 2,
                blockEntity.getBlockPos().getY() + 2,
                blockEntity.getBlockPos().getZ() + 2
        );
    }
}