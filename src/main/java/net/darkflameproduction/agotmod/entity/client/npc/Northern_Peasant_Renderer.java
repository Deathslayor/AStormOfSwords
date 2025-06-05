package net.darkflameproduction.agotmod.entity.client.npc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
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

public class Northern_Peasant_Renderer extends GeoEntityRenderer<Northern_Peasant_Entity> {

    // Pre-define our bone names for easy and consistent reference later - MATCH YOUR GEOMETRY EXACTLY
    private static final String LEFT_HAND = "left_hand";
    private static final String RIGHT_HAND = "right_hand";
    private static final String LEFT_BOOT = "armor_left_boot";
    private static final String RIGHT_BOOT = "armor_right_boot";
    private static final String LEFT_ARMOR_LEG = "armor_left_leg";
    private static final String RIGHT_ARMOR_LEG = "armor_right_leg";
    private static final String CHESTPLATE = "armor_body";
    private static final String RIGHT_SLEEVE = "armor_right_arm";
    private static final String LEFT_SLEEVE = "armor_left_arm";
    private static final String HELMET = "armor_head";

    private static final int[] EYES_COLORS = {
            0xFF245014, 0xFF2d6618, 0xFF377a1e, 0xFF7a521e, 0xFF543b1b, 0xFF3e2a12,
            0xFF201609, 0xFF0e263b, 0xFF21215c, 0xFF262793, 0xFF191bc0
    };

    private static final int[] HAIR_COLORS = {
            0xFF715017, 0xFF644819, 0xFF55401b, 0xFF433215, 0xFF31240e, 0xFF231909,
            0xFF2c1c02, 0xFF52270c, 0xFF69300c, 0xFF7a3d16, 0xFF6b2813, 0xFF551a08,
            0xFF7e2a0f, 0xFF8f1e11, 0xFF671107, 0xFF675916, 0xFF7f6c14, 0xFFa78d10,
            0xFFbb9f1d, 0xFF939393, 0xFF777777, 0xFFb63d08, 0xFFd5380d
    };

    private static final int[] SHIRT_COLORS = {
            0xFFe7e7e7, 0xFFa9a39a, 0xFF988569, 0xFFae8f5f, 0xFFd79e46, 0xFFaf751d,
            0xFFcbcda2, 0xFFa0a27b, 0xFF92b772, 0xFF6a8d4b, 0xFF507530, 0xFF5dc05c,
            0xFF3d943d, 0xFF248124, 0xFF115210, 0xFF355d4f, 0xFF699d8b, 0xFF97cab8,
            0xFF69687e, 0xFF7c78c3, 0xFF8b89b7, 0xFF232d8c, 0xFFc19c9c, 0xFF895656,
            0xFF8a3d3d, 0xFF642222, 0xFF8b1717
    };

    private static final int[] PANTS_COLORS = {
            0xFF331d0b, 0xFF43250c, 0xFF221206, 0xFF37210e, 0xFF2d1e12, 0xFF2e251d,
            0xFF24201d, 0xFF291911, 0xFF391c0e, 0xFF1f0c03, 0xFF2a0e00, 0xFF370605,
            0xFF270201, 0xFF451312
    };

    private static final int[] TUNIC_HOOD_COLORS = {
            0xFF320808, 0xFF5f3010, 0xFF3e1f09, 0xFF271508, 0xFF1b110a, 0xFF231f1c,
            0xFF423c1d, 0xFF393107, 0xFF133347, 0xFF071d2b, 0xFF050f15, 0xFF28390a,
            0xFF0a392a, 0xFF15523f
    };

    // Texture variant constants
    private static final int BODY_VARIANTS = 6;
    private static final int EYES_VARIANTS = 1;
    private static final int LEGS_VARIANTS = 1;
    private static final int SHIRT_VARIANTS = 1;
    private static final int HAIR_VARIANTS = 24;
    private static final int BOOTS_VARIANTS = 2;
    private static final int TUNIC_VARIANTS = 4;
    private static final int HOOD_VARIANTS = 3;

    // Texture arrays
    private static final ResourceLocation[] BODY_TEXTURES = new ResourceLocation[BODY_VARIANTS];
    private static final ResourceLocation[] EYES_TEXTURES = new ResourceLocation[EYES_VARIANTS];
    private static final ResourceLocation[] LEGS_TEXTURES = new ResourceLocation[LEGS_VARIANTS];
    private static final ResourceLocation[] SHIRT_TEXTURES = new ResourceLocation[SHIRT_VARIANTS];
    private static final ResourceLocation[] HAIR_TEXTURES = new ResourceLocation[HAIR_VARIANTS];
    private static final ResourceLocation[] BOOTS_TEXTURES = new ResourceLocation[BOOTS_VARIANTS];
    private static final ResourceLocation[] TUNIC_TEXTURES = new ResourceLocation[TUNIC_VARIANTS];
    private static final ResourceLocation[] HOOD_TEXTURES = new ResourceLocation[HOOD_VARIANTS];

    static {
        // Initialize texture arrays
        for (int i = 1; i <= BODY_VARIANTS; i++) {
            BODY_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/body" + i + ".png");
        }
        for (int i = 1; i <= EYES_VARIANTS; i++) {
            EYES_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/eyes" + i + ".png");
        }
        for (int i = 1; i <= LEGS_VARIANTS; i++) {
            LEGS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/legs" + i + ".png");
        }
        for (int i = 1; i <= SHIRT_VARIANTS; i++) {
            SHIRT_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/shirt" + i + ".png");
        }
        for (int i = 1; i <= HAIR_VARIANTS; i++) {
            HAIR_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hair" + i + ".png");
        }
        for (int i = 1; i <= BOOTS_VARIANTS; i++) {
            BOOTS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/boots" + i + ".png");
        }
        for (int i = 1; i <= TUNIC_VARIANTS; i++) {
            TUNIC_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/tunic" + i + ".png");
        }
        for (int i = 1; i <= HOOD_VARIANTS; i++) {
            HOOD_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hood" + i + ".png");
        }
    }

    // Add instance variables for armor items
    protected ItemStack helmetItem;
    protected ItemStack chestplateItem;
    protected ItemStack leggingsItem;
    protected ItemStack bootsItem;

    // Add instance variables for held items
    protected ItemStack mainHandItem;
    protected ItemStack offhandItem;

    public Northern_Peasant_Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Northern_Peasant_Model());
        this.scaleWidth = 0.95f;
        this.scaleHeight = 0.95f;
        this.shadowRadius = 0.5f;

        // Add armor rendering layer
        addRenderLayer(new ItemArmorGeoLayer<>(this) {
            @Nullable
            @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, Northern_Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, RIGHT_BOOT -> Northern_Peasant_Renderer.this.bootsItem;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG -> Northern_Peasant_Renderer.this.leggingsItem;
                    case CHESTPLATE, RIGHT_SLEEVE, LEFT_SLEEVE -> Northern_Peasant_Renderer.this.chestplateItem;
                    case HELMET -> Northern_Peasant_Renderer.this.helmetItem;
                    default -> null;
                };
            }

            @NotNull
            @Override
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, Northern_Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, RIGHT_BOOT -> EquipmentSlot.FEET;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG -> EquipmentSlot.LEGS;
                    case RIGHT_SLEEVE, LEFT_SLEEVE -> EquipmentSlot.CHEST;
                    case CHESTPLATE -> EquipmentSlot.CHEST;
                    case HELMET -> EquipmentSlot.HEAD;
                    default -> super.getEquipmentSlotForBone(bone, stack, animatable);
                };
            }

            @NotNull
            @Override
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, Northern_Peasant_Entity animatable, HumanoidModel<?> baseModel) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, LEFT_ARMOR_LEG -> baseModel.leftLeg;
                    case RIGHT_BOOT, RIGHT_ARMOR_LEG -> baseModel.rightLeg;
                    case RIGHT_SLEEVE -> baseModel.rightArm;
                    case LEFT_SLEEVE -> baseModel.leftArm;
                    case CHESTPLATE -> baseModel.body;
                    case HELMET -> baseModel.head;
                    default -> super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
                };
            }
        });

        // Add held item rendering layer
        addRenderLayer(new BlockAndItemGeoLayer<>(this) {
            @Nullable
            @Override
            protected ItemStack getStackForBone(GeoBone bone, Northern_Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND -> Northern_Peasant_Renderer.this.offhandItem;
                    case RIGHT_HAND -> Northern_Peasant_Renderer.this.mainHandItem;
                    default -> null;
                };
            }

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, Northern_Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
                    case RIGHT_HAND -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, Northern_Peasant_Entity animatable,
                                              MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
                if (stack == Northern_Peasant_Renderer.this.mainHandItem) {
                    // Main hand item transformations (right hand)
                    poseStack.mulPose(Axis.XP.rotationDegrees(-70f)); // X-axis rotation
                    poseStack.mulPose(Axis.YP.rotationDegrees(0f));   // Y-axis rotation
                    poseStack.mulPose(Axis.ZP.rotationDegrees(0f));   // Z-axis rotation

                    poseStack.translate(0, 0.25, -0.05);
                }
                else if (stack == Northern_Peasant_Renderer.this.offhandItem) {
                    // Offhand item transformations (left hand) - Full rotation control
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90f));   // X-axis rotation
                    poseStack.mulPose(Axis.YP.rotationDegrees(0f));   // Y-axis rotation
                    poseStack.mulPose(Axis.ZP.rotationDegrees(0f));   // Z-axis rotation

                    // Apply special positioning for shields in offhand
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
    public ResourceLocation getTextureLocation(Northern_Peasant_Entity entity) {
        // Use body texture as the main texture
        long bits = entity.getUUID().getLeastSignificantBits();
        int bodyVariant = (int)(Math.abs(bits) % BODY_VARIANTS);
        return BODY_TEXTURES[bodyVariant];
    }

    @Override
    public void preRender(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);

        // Set items for armor and held item layers
        this.helmetItem = animatable.getItemBySlot(EquipmentSlot.HEAD);
        this.chestplateItem = animatable.getItemBySlot(EquipmentSlot.CHEST);
        this.leggingsItem = animatable.getItemBySlot(EquipmentSlot.LEGS);
        this.bootsItem = animatable.getItemBySlot(EquipmentSlot.FEET);
        this.mainHandItem = animatable.getItemBySlot(EquipmentSlot.MAINHAND);
        this.offhandItem = animatable.getItemBySlot(EquipmentSlot.OFFHAND);
    }

    @Override
    public void actuallyRender(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model,
                               RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer,
                               boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {

        // First, render the base entity using the main texture (body)
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, color);

        // Generate variants and colors
        long bits = animatable.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits);

        int eyesVariant = (int)((seed >>> 8) % EYES_VARIANTS);
        int legsVariant = (int)((seed >>> 16) % LEGS_VARIANTS);
        int shirtVariant = (int)((seed >>> 24) % SHIRT_VARIANTS);
        int hairVariant = (int)((seed >>> 32) % HAIR_VARIANTS);
        int bootsVariant = (int)((seed >>> 40) % BOOTS_VARIANTS);
        int tunicVariant = (int)((seed >>> 48) % TUNIC_VARIANTS);
        int hoodVariant = (int)((seed >>> 56) % HOOD_VARIANTS);

        int eyesColor = getPresetColor(animatable, EYES_COLORS, 1);
        int legsColor = getPresetColor(animatable, PANTS_COLORS, 2);
        int shirtColor = getPresetColor(animatable, SHIRT_COLORS, 3);
        int hairColor = getPresetColor(animatable, HAIR_COLORS, 4);
        int tunicColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 5);
        int hoodColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 6);

        // ALWAYS render eyes - they should show through armor
        renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, eyesColor, EYES_TEXTURES[eyesVariant]);

        // Render hair - only if not wearing head armor
        if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, hairColor, HAIR_TEXTURES[hairVariant]);
        }

        // Render clothing layers - these represent the NPC's natural clothing, not armor replacements
        // Legs (pants) - render unless wearing leg armor that completely covers them
        if (animatable.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);
        }

        // Shirt and tunic - render unless wearing chest armor that completely covers them
        if (animatable.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, shirtColor, SHIRT_TEXTURES[shirtVariant]);

            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, tunicColor, TUNIC_TEXTURES[tunicVariant]);
        }

        // Hood - render unless wearing head armor that completely covers it
        if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, hoodColor, HOOD_TEXTURES[hoodVariant]);
        }

        // Default boots - only render if not wearing foot armor
        if (animatable.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, 0xFFFFFFFF, BOOTS_TEXTURES[bootsVariant]);
        }
    }

    private void renderTextureLayer(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model,
                                    MultiBufferSource bufferSource, boolean isReRender, float partialTick,
                                    int packedLight, int packedOverlay, int color, ResourceLocation texture) {

        RenderType layerRenderType = RenderType.entityCutoutNoCull(texture);
        VertexConsumer layerBuffer = bufferSource.getBuffer(layerRenderType);

        super.actuallyRender(poseStack, animatable, model, layerRenderType, bufferSource, layerBuffer,
                isReRender, partialTick, packedLight, packedOverlay, color);
    }

    private int getPresetColor(Northern_Peasant_Entity entity, int[] colorArray, int colorIndex) {
        long bits = entity.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits) + (colorIndex * 54321);
        int colorVariant = (int)(seed % colorArray.length);
        return colorArray[colorVariant];
    }
}