package net.darkflameproduction.agotmod.entity.client.npc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

/**
 * GeckoLib renderer for peasants. Adults are drawn by {@link PeasantHumanoidRenderer}
 * (player model / Epic Fight); this renderer is used for children, whose textures
 * don't fit the player skin layout.
 */
public class Peasant_Renderer extends GeoEntityRenderer<Peasant_Entity> {

    private static final String LEFT_HAND       = "left_hand";
    private static final String RIGHT_HAND      = "right_hand";
    private static final String LEFT_BOOT       = "armor_left_boot";
    private static final String RIGHT_BOOT      = "armor_right_boot";
    private static final String LEFT_ARMOR_LEG  = "armor_left_leg";
    private static final String RIGHT_ARMOR_LEG = "armor_right_leg";
    private static final String CHESTPLATE      = "armor_body";
    private static final String RIGHT_SLEEVE    = "armor_right_arm";
    private static final String LEFT_SLEEVE     = "armor_left_arm";
    private static final String HELMET          = "armor_head";

    protected ItemStack helmetItem, chestplateItem, leggingsItem, bootsItem;
    protected ItemStack mainHandItem, offhandItem;

    public Peasant_Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Peasant_Model());
        this.scaleWidth   = 0.95f;
        this.scaleHeight  = 0.95f;
        this.shadowRadius = 0.5f;

        addRenderLayer(new ItemArmorGeoLayer<>(this) {
            @Nullable @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, RIGHT_BOOT                 -> Peasant_Renderer.this.bootsItem;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG       -> Peasant_Renderer.this.leggingsItem;
                    case CHESTPLATE, RIGHT_SLEEVE, LEFT_SLEEVE -> Peasant_Renderer.this.chestplateItem;
                    case HELMET                                 -> Peasant_Renderer.this.helmetItem;
                    default -> null;
                };
            }
            @NotNull @Override
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, RIGHT_BOOT           -> EquipmentSlot.FEET;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG -> EquipmentSlot.LEGS;
                    case RIGHT_SLEEVE, LEFT_SLEEVE        -> EquipmentSlot.CHEST;
                    case CHESTPLATE                       -> EquipmentSlot.CHEST;
                    case HELMET                           -> EquipmentSlot.HEAD;
                    default -> super.getEquipmentSlotForBone(bone, stack, animatable);
                };
            }
            @NotNull @Override
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, Peasant_Entity animatable, HumanoidModel<?> baseModel) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, LEFT_ARMOR_LEG   -> baseModel.leftLeg;
                    case RIGHT_BOOT, RIGHT_ARMOR_LEG -> baseModel.rightLeg;
                    case RIGHT_SLEEVE                -> baseModel.rightArm;
                    case LEFT_SLEEVE                 -> baseModel.leftArm;
                    case CHESTPLATE                  -> baseModel.body;
                    case HELMET                      -> baseModel.head;
                    default -> super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
                };
            }
        });

        addRenderLayer(new BlockAndItemGeoLayer<>(this) {
            @Nullable @Override
            protected ItemStack getStackForBone(GeoBone bone, Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND  -> Peasant_Renderer.this.offhandItem;
                    case RIGHT_HAND -> Peasant_Renderer.this.mainHandItem;
                    default -> null;
                };
            }
            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND  -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
                    case RIGHT_HAND -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }
            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, Peasant_Entity animatable,
                                              MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
                if (stack == Peasant_Renderer.this.mainHandItem) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(-70f));
                    poseStack.translate(0, 0.25, -0.05);
                } else if (stack == Peasant_Renderer.this.offhandItem) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90f));
                    if (stack.getItem() instanceof ShieldItem) {
                        poseStack.translate(0, 0.2, 1.3);
                        poseStack.mulPose(Axis.YP.rotationDegrees(180));
                    }
                }
                super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(Peasant_Entity entity) {
        return PeasantTextures.bodyTexture(entity);
    }

    @Override
    public void preRender(PoseStack poseStack, Peasant_Entity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
        this.helmetItem     = animatable.getItemBySlot(EquipmentSlot.HEAD);
        this.chestplateItem = animatable.getItemBySlot(EquipmentSlot.CHEST);
        this.leggingsItem   = animatable.getItemBySlot(EquipmentSlot.LEGS);
        this.bootsItem      = animatable.getItemBySlot(EquipmentSlot.FEET);
        this.mainHandItem   = animatable.getItemBySlot(EquipmentSlot.MAINHAND);
        this.offhandItem    = animatable.getItemBySlot(EquipmentSlot.OFFHAND);
    }

    @Override
    public void actuallyRender(PoseStack poseStack, Peasant_Entity animatable, BakedGeoModel model,
                               RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer,
                               boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {

        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, color);

        // Clothing layers drawn over the body on the same model
        PeasantTextures.forEachClothingLayer(animatable, (texture, layerColor) -> {
            RenderType rt = RenderType.entityCutoutNoCull(texture);
            super.actuallyRender(poseStack, animatable, model, rt, bufferSource, bufferSource.getBuffer(rt),
                    isReRender, partialTick, packedLight, packedOverlay, layerColor);
        });
    }
}
