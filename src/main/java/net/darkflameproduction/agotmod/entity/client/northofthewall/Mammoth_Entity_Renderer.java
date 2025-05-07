package net.darkflameproduction.agotmod.entity.client.northofthewall;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Mammoth_Entity_Renderer extends GeoEntityRenderer<Mammoth_Entity> {
    private static final ResourceLocation TEXTURE_NORMAL = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/mammoth.png");
    private static final ResourceLocation TEXTURE_BROWN = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/black_mammoth.png");
    private static final ResourceLocation TEXTURE_WHITE = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/white_mammoth.png");
    private static final ResourceLocation TEXTURE_DARK = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/dark_mammoth.png");

    public Mammoth_Entity_Renderer(EntityRendererProvider.Context context) {
        super(context, new Mammoth_Entity_Model());
        this.scaleWidth = 2.0f;
        this.scaleHeight = 2.0f;
        this.shadowRadius = 1.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(Mammoth_Entity instance) {
        long bits = instance.getUUID().getLeastSignificantBits();
        int variant = (int)(Math.abs(bits) % 100);

        if (variant < 33) {
            return TEXTURE_BROWN;
        } else if (variant < 66) {
            return TEXTURE_DARK;
        } else if (variant < 99) {
            return TEXTURE_NORMAL;
        } else {
            return TEXTURE_WHITE;
        }
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