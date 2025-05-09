package net.darkflameproduction.agotmod.entity.client.wolves;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.norththewall.Mammoth_Entity;
import net.darkflameproduction.agotmod.entity.custom.wolves.Direwolf_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Direwolf_Entity_Renderer extends GeoEntityRenderer<Direwolf_Entity> {
    private static final ResourceLocation TEXTURE_GHOST = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/direwolf_ghost.png");
    private static final ResourceLocation TEXTURE_GREYWIND = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/direwolf_greywind.png");
    private static final ResourceLocation TEXTURE_LADY = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/direwolf_lady.png");
    private static final ResourceLocation TEXTURE_NYMERIA = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/direwolf_nymeria.png");
    private static final ResourceLocation TEXTURE_SHAGGYDOG = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/direwolf_shaggydog.png");
    private static final ResourceLocation TEXTURE_SUMMER = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/direwolf_summer.png");


    public Direwolf_Entity_Renderer(EntityRendererProvider.Context context) {
        super(context, new Direwolf_Entity_Model());
        this.scaleWidth = 0.8f;
        this.scaleHeight = 0.8f;
        this.shadowRadius = 1f;
    }

    @Override
    public ResourceLocation getTextureLocation(Direwolf_Entity instance) {
        long bits = instance.getUUID().getLeastSignificantBits();
        int variant = (int)(Math.abs(bits) % 100);

        if (variant < 20) {
            return TEXTURE_GREYWIND;
        } else if (variant < 40) {
            return TEXTURE_LADY;
        } else if (variant < 60) {
            return TEXTURE_NYMERIA;
        } else if (variant < 80) {
            return TEXTURE_SHAGGYDOG;
        } else if (variant < 99) {
            return TEXTURE_SUMMER;
        } else {
            return TEXTURE_GHOST;
        }
    }

    @Override
    protected float getDeathMaxRotation(Direwolf_Entity entityLivingBaseIn, float partialTick) {
        return 0.0F;
    }

    @Override
    public int getPackedOverlay(Direwolf_Entity animatable, float u, float partialTick) {
        return OverlayTexture.NO_OVERLAY;
    }
}