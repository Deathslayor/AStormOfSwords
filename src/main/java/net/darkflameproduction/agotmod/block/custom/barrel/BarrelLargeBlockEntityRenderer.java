package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

/** The controller is the bottom-centre block, so the model renders centred on it without extra offset. */
public class BarrelLargeBlockEntityRenderer extends GeoBlockRenderer<BarrelLargeBlockEntity> {

    public BarrelLargeBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(AGoTMod.id("barrel_large")));
    }

    @Override
    public AABB getRenderBoundingBox(BarrelLargeBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(
                pos.getX() - 1, pos.getY(),     pos.getZ() - 1,
                pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2
        );
    }
}
