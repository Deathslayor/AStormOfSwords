package net.darkflameproduction.agotmod.entity.client.birds;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.birds.Crow_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Parrot;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Crow_Entity_Renderer extends GeoEntityRenderer<Crow_Entity> {
    private static final ResourceLocation TEXTURE_BLACK = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/crow.png");
    private static final ResourceLocation TEXTURE_WHITE = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/crow.png");

    public Crow_Entity_Renderer(EntityRendererProvider.Context context) {
        super(context, new Crow_Entity_Model());
        this.scaleWidth = 0.5f;
        this.scaleHeight = 0.5f;
        this.shadowRadius = 0.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(Crow_Entity instance) {
        long bits = instance.getUUID().getLeastSignificantBits();
        int variant = (int)(Math.abs(bits) % 100);

        if (variant < 99) {
            return TEXTURE_BLACK;
        } else {
            return TEXTURE_WHITE;
        }
    }

    @Override
    protected float getDeathMaxRotation(Crow_Entity entityLivingBaseIn, float partialTick) {
        return 0.0F;
    }

    @Override
    public int getPackedOverlay(Crow_Entity animatable, float u, float partialTick) {
        return OverlayTexture.NO_OVERLAY;
    }
}