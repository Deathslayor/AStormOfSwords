package net.darkflameproduction.agotmod.block.custom.furniture;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class ChairBlockEntityRenderer extends GeoBlockRenderer<ChairBlockEntity> {

    public ChairBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(AGoTMod.id("chair")) {
            @Override
            public ResourceLocation getTextureResource(ChairBlockEntity animatable) {
                return animatable.getTextureLocation();
            }
        });
    }
}
