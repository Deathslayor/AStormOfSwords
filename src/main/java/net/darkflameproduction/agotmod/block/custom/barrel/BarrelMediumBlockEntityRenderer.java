package net.darkflameproduction.agotmod.block.custom.barrel;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
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
        // The renderer already centres on the controller block; shift to the corner the barrel is centred on
        BlockState state = animatable.getBlockState();
        poseStack.translate(BarrelMediumBlock.centreOffsetX(state) - 0.5, 0.0, BarrelMediumBlock.centreOffsetZ(state) - 0.5);
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
