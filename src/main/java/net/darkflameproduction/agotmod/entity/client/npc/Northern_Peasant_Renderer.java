package net.darkflameproduction.agotmod.entity.client.npc;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.GeoBone;
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
    private static final ResourceLocation TEXTURE_9 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant9.png");
    private static final ResourceLocation TEXTURE_10 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant10.png");
    private static final ResourceLocation TEXTURE_11 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant11.png");
    private static final ResourceLocation TEXTURE_12 = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "textures/entity/northernpeasant/northern_peasant12.png");


    public Northern_Peasant_Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Northern_Peasant_Model());
        this.scaleWidth = 0.95f;
        this.scaleHeight = 0.95f;
        this.shadowRadius = 0.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)(Math.abs(bits) % 12);

        return switch (variant) {
            case 0 -> TEXTURE_1;
            case 1 -> TEXTURE_2;
            case 2 -> TEXTURE_3;
            case 3 -> TEXTURE_4;
            case 4 -> TEXTURE_5;
            case 5 -> TEXTURE_6;
            case 6 -> TEXTURE_7;
            case 7 -> TEXTURE_8;
            case 8 -> TEXTURE_9;
            case 9 -> TEXTURE_10;
            case 10 -> TEXTURE_11;
            case 11 -> TEXTURE_12;
            default -> TEXTURE_1;
        };
    }

    @Override
    public void renderRecursively(PoseStack poseStack, Northern_Peasant_Entity animatable, GeoBone bone,
                                  net.minecraft.client.renderer.RenderType renderType, MultiBufferSource bufferSource,
                                  com.mojang.blaze3d.vertex.VertexConsumer buffer, boolean isReRender, float partialTick,
                                  int packedLight, int packedOverlay, int color) {

        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, color);

        if ("right_arm".equals(bone.getName()) && animatable.isCurrentlyEating()) {
            ItemStack eatingItem = animatable.getEatingItem();
            if (!eatingItem.isEmpty()) {
                renderItemInBone(poseStack, eatingItem, packedLight, packedOverlay, bufferSource, animatable);
            }
        }
    }

    private void renderItemInBone(PoseStack poseStack, ItemStack item, int packedLight, int packedOverlay,
                                  MultiBufferSource bufferSource, Northern_Peasant_Entity animatable) {
        poseStack.pushPose();


        poseStack.translate(0.1, 0.3, 0.1);
        poseStack.scale(0.4f, 0.4f, 0.4f);

        Minecraft.getInstance().getItemRenderer().renderStatic(
                item,
                ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,
                packedLight,
                packedOverlay,
                poseStack,
                bufferSource,
                animatable.level(),
                (int) animatable.getX() + (int) animatable.getY() + (int) animatable.getZ()
        );

        poseStack.popPose();
    }
}