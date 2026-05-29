package net.darkflameproduction.agotmod.block.custom.furniture;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class ArmChairBlockEntityRenderer extends GeoBlockRenderer<ArmChairBlockEntity> {

    public ArmChairBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(AGoTMod.id("arm_chair")) {
            @Override
            public ResourceLocation getTextureResource(ArmChairBlockEntity animatable) {
                return animatable.getTextureLocation();
            }
        });
    }
}
