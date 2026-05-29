package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BarrelSmallBlockEntityRenderer extends GeoBlockRenderer<BarrelSmallBlockEntity> {

    public BarrelSmallBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(AGoTMod.id("barrel_small")));
    }
}
