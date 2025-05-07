
package net.darkflameproduction.agotmod.entity.client.northofthewall;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Mammoth_Entity_Renderer extends GeoEntityRenderer<Mammoth_Entity> {
    public Mammoth_Entity_Renderer(EntityRendererProvider.Context context) {
        super(context, new Mammoth_Entity_Model());
        this.scaleWidth = 2.0f;
        this.scaleHeight = 2.0f;
    }




    @Override
    protected float getDeathMaxRotation(Mammoth_Entity entityLivingBaseIn, float partialTick) {
        return 0.0F;
    }

    @Override
    public int getPackedOverlay(Mammoth_Entity animatable, float u, float partialTick) {
        return OverlayTexture.NO_OVERLAY;
    }
}