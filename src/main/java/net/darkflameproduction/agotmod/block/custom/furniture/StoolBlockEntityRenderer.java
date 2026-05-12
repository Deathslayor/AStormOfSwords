package net.darkflameproduction.agotmod.block.custom.furniture;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class StoolBlockEntityRenderer extends GeoBlockRenderer<StoolBlockEntity> {

    public StoolBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(AGoTMod.id("stool")) {
            @Override
            public ResourceLocation getTextureResource(StoolBlockEntity animatable,
                                                       software.bernie.geckolib.renderer.GeoRenderer<StoolBlockEntity> renderer) {
                return animatable.getTextureLocation();
            }
        });
    }
}