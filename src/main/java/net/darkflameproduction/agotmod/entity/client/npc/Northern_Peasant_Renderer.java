package net.darkflameproduction.agotmod.entity.client.npc;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Northern_Peasant_Renderer extends GeoEntityRenderer<Northern_Peasant_Entity> {

    private static final ResourceLocation TEXTURE_1 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant1.png");
    private static final ResourceLocation TEXTURE_2 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant2.png");
    private static final ResourceLocation TEXTURE_3 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant3.png");
    private static final ResourceLocation TEXTURE_4 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant4.png");
    private static final ResourceLocation TEXTURE_5 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant5.png");
    private static final ResourceLocation TEXTURE_6 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant6.png");
    private static final ResourceLocation TEXTURE_7 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant7.png");
    private static final ResourceLocation TEXTURE_8 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant8.png");

    public Northern_Peasant_Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Northern_Peasant_Model());
        this.scaleWidth = 0.95f;
        this.scaleHeight = 0.95f;
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)(Math.abs(bits) % 8);

        return switch (variant) {
            case 0 -> TEXTURE_1;
            case 1 -> TEXTURE_2;
            case 2 -> TEXTURE_3;
            case 3 -> TEXTURE_4;
            case 4 -> TEXTURE_5;
            case 5 -> TEXTURE_6;
            case 6 -> TEXTURE_7;
            case 7 -> TEXTURE_8;
            default -> TEXTURE_1;
        };
    }
}
