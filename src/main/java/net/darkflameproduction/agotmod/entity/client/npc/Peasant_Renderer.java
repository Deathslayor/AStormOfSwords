package net.darkflameproduction.agotmod.entity.client.npc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.darkflameproduction.agotmod.AGoTMod;
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

public class Peasant_Renderer extends GeoEntityRenderer<Peasant_Entity> {

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

    // Male texture variant constants
    private static final int BODY_VARIANTS = 6;
    private static final int EYES_VARIANTS = 1;
    private static final int LEGS_VARIANTS = 1;
    private static final int SHIRT_VARIANTS = 1;
    private static final int HAIR_VARIANTS = 24;
    private static final int BOOTS_VARIANTS = 2;
    private static final int TUNIC_VARIANTS = 4;
    private static final int HOOD_VARIANTS = 3;

    // Female texture variant constants (based on actual files)
    private static final int FEMALE_BODY_VARIANTS = 6; // body_woman1.png through body_woman6.png
    private static final int FEMALE_EYES_VARIANTS = 1; // eyes1.png (shared with males)
    private static final int FEMALE_LEGS_VARIANTS = 1; // legs_woman1.png
    private static final int FEMALE_SHIRT_VARIANTS = 1; // shirt_woman1.png
    private static final int FEMALE_HAIR_VARIANTS = 1; // hair_woman1.png
    private static final int FEMALE_BOOTS_VARIANTS = 2; // boots_woman1.png, boots_woman2.png
    private static final int FEMALE_TUNIC_VARIANTS = 4; // tunic_woman1.png through tunic_woman4.png (but only tunic_woman4.png exists)
    private static final int FEMALE_HOOD_VARIANTS = 1; // hood_woman3.png (but numbered as 3)

    // Child texture variant constants
    private static final int CHILD_MALE_BODY_VARIANTS = 1; // body_child1.png through body_child6.png
    private static final int CHILD_MALE_EYES_VARIANTS = 1; // eyes_child1.png
    private static final int CHILD_MALE_LEGS_VARIANTS = 1; // legs_child1.png
    private static final int CHILD_MALE_SHIRT_VARIANTS = 1; // shirt_child1.png
    private static final int CHILD_MALE_HAIR_VARIANTS = 1; // hair_child1.png through hair_child24.png
    private static final int CHILD_MALE_BOOTS_VARIANTS = 1; // boots_child1.png, boots_child2.png
    private static final int CHILD_MALE_TUNIC_VARIANTS = 1; // tunic_child1.png through tunic_child4.png
    private static final int CHILD_MALE_HOOD_VARIANTS = 1; // hood_child1.png through hood_child3.png

    private static final int CHILD_FEMALE_BODY_VARIANTS = 1; // body_woman_child1.png through body_woman_child6.png
    private static final int CHILD_FEMALE_EYES_VARIANTS = 1; // eyes_woman_child1.png
    private static final int CHILD_FEMALE_LEGS_VARIANTS = 1; // legs_woman_child1.png
    private static final int CHILD_FEMALE_SHIRT_VARIANTS = 1; // shirt_woman_child1.png
    private static final int CHILD_FEMALE_HAIR_VARIANTS = 1; // hair_woman_child1.png through hair_woman_child24.png
    private static final int CHILD_FEMALE_BOOTS_VARIANTS = 1; // boots_woman_child1.png, boots_woman_child2.png
    private static final int CHILD_FEMALE_TUNIC_VARIANTS = 1; // tunic_woman_child1.png through tunic_woman_child4.png
    private static final int CHILD_FEMALE_HOOD_VARIANTS = 1; // hood_woman_child1.png through hood_woman_child3.png

    // Male texture arrays
    private static final ResourceLocation[] BODY_TEXTURES = new ResourceLocation[BODY_VARIANTS];
    private static final ResourceLocation[] EYES_TEXTURES = new ResourceLocation[EYES_VARIANTS];
    private static final ResourceLocation[] LEGS_TEXTURES = new ResourceLocation[LEGS_VARIANTS];
    private static final ResourceLocation[] SHIRT_TEXTURES = new ResourceLocation[SHIRT_VARIANTS];
    private static final ResourceLocation[] HAIR_TEXTURES = new ResourceLocation[HAIR_VARIANTS];
    private static final ResourceLocation[] BOOTS_TEXTURES = new ResourceLocation[BOOTS_VARIANTS];
    private static final ResourceLocation[] TUNIC_TEXTURES = new ResourceLocation[TUNIC_VARIANTS];
    private static final ResourceLocation[] HOOD_TEXTURES = new ResourceLocation[HOOD_VARIANTS];

    // Female texture arrays
    private static final ResourceLocation[] FEMALE_BODY_TEXTURES = new ResourceLocation[FEMALE_BODY_VARIANTS];
    private static final ResourceLocation[] FEMALE_LEGS_TEXTURES = new ResourceLocation[FEMALE_LEGS_VARIANTS];
    private static final ResourceLocation[] FEMALE_SHIRT_TEXTURES = new ResourceLocation[FEMALE_SHIRT_VARIANTS];
    private static final ResourceLocation[] FEMALE_HAIR_TEXTURES = new ResourceLocation[FEMALE_HAIR_VARIANTS];
    private static final ResourceLocation[] FEMALE_BOOTS_TEXTURES = new ResourceLocation[FEMALE_BOOTS_VARIANTS];
    private static final ResourceLocation[] FEMALE_TUNIC_TEXTURES = new ResourceLocation[FEMALE_TUNIC_VARIANTS];
    private static final ResourceLocation[] FEMALE_HOOD_TEXTURES = new ResourceLocation[FEMALE_HOOD_VARIANTS];

    // Child male texture arrays
    private static final ResourceLocation[] CHILD_MALE_BODY_TEXTURES = new ResourceLocation[CHILD_MALE_BODY_VARIANTS];
    private static final ResourceLocation[] CHILD_MALE_EYES_TEXTURES = new ResourceLocation[CHILD_MALE_EYES_VARIANTS];
    private static final ResourceLocation[] CHILD_MALE_LEGS_TEXTURES = new ResourceLocation[CHILD_MALE_LEGS_VARIANTS];
    private static final ResourceLocation[] CHILD_MALE_SHIRT_TEXTURES = new ResourceLocation[CHILD_MALE_SHIRT_VARIANTS];
    private static final ResourceLocation[] CHILD_MALE_HAIR_TEXTURES = new ResourceLocation[CHILD_MALE_HAIR_VARIANTS];
    private static final ResourceLocation[] CHILD_MALE_BOOTS_TEXTURES = new ResourceLocation[CHILD_MALE_BOOTS_VARIANTS];
    private static final ResourceLocation[] CHILD_MALE_TUNIC_TEXTURES = new ResourceLocation[CHILD_MALE_TUNIC_VARIANTS];
    private static final ResourceLocation[] CHILD_MALE_HOOD_TEXTURES = new ResourceLocation[CHILD_MALE_HOOD_VARIANTS];

    // Child female texture arrays
    private static final ResourceLocation[] CHILD_FEMALE_BODY_TEXTURES = new ResourceLocation[CHILD_FEMALE_BODY_VARIANTS];
    private static final ResourceLocation[] CHILD_FEMALE_EYES_TEXTURES = new ResourceLocation[CHILD_FEMALE_EYES_VARIANTS];
    private static final ResourceLocation[] CHILD_FEMALE_LEGS_TEXTURES = new ResourceLocation[CHILD_FEMALE_LEGS_VARIANTS];
    private static final ResourceLocation[] CHILD_FEMALE_SHIRT_TEXTURES = new ResourceLocation[CHILD_FEMALE_SHIRT_VARIANTS];
    private static final ResourceLocation[] CHILD_FEMALE_HAIR_TEXTURES = new ResourceLocation[CHILD_FEMALE_HAIR_VARIANTS];
    private static final ResourceLocation[] CHILD_FEMALE_BOOTS_TEXTURES = new ResourceLocation[CHILD_FEMALE_BOOTS_VARIANTS];
    private static final ResourceLocation[] CHILD_FEMALE_TUNIC_TEXTURES = new ResourceLocation[CHILD_FEMALE_TUNIC_VARIANTS];
    private static final ResourceLocation[] CHILD_FEMALE_HOOD_TEXTURES = new ResourceLocation[CHILD_FEMALE_HOOD_VARIANTS];

    static {
        // Initialize male texture arrays
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

        // Initialize female texture arrays
        for (int i = 1; i <= FEMALE_BODY_VARIANTS; i++) {
            FEMALE_BODY_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/body_woman" + i + ".png");
        }
        for (int i = 1; i <= FEMALE_LEGS_VARIANTS; i++) {
            FEMALE_LEGS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/legs_woman" + i + ".png");
        }
        for (int i = 1; i <= FEMALE_SHIRT_VARIANTS; i++) {
            FEMALE_SHIRT_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/shirt_woman" + i + ".png");
        }
        for (int i = 1; i <= FEMALE_HAIR_VARIANTS; i++) {
            FEMALE_HAIR_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hair_woman" + i + ".png");
        }
        for (int i = 1; i <= FEMALE_BOOTS_VARIANTS; i++) {
            FEMALE_BOOTS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/boots_woman" + i + ".png");
        }
        // Special handling for tunic_woman4.png (only this variant exists)
        FEMALE_TUNIC_TEXTURES[0] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                "textures/entity/northernpeasant/tunic_woman4.png");
        for (int i = 2; i <= FEMALE_TUNIC_VARIANTS; i++) {
            // Fill remaining slots with the same texture for now
            FEMALE_TUNIC_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/tunic_woman4.png");
        }
        // Special handling for hood_woman3.png (only this variant exists)
        FEMALE_HOOD_TEXTURES[0] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                "textures/entity/northernpeasant/hood_woman3.png");

        // Initialize child male texture arrays
        for (int i = 1; i <= CHILD_MALE_BODY_VARIANTS; i++) {
            CHILD_MALE_BODY_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/body_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_MALE_EYES_VARIANTS; i++) {
            CHILD_MALE_EYES_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/eyes_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_MALE_LEGS_VARIANTS; i++) {
            CHILD_MALE_LEGS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/legs_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_MALE_SHIRT_VARIANTS; i++) {
            CHILD_MALE_SHIRT_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/shirt_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_MALE_HAIR_VARIANTS; i++) {
            CHILD_MALE_HAIR_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hair_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_MALE_BOOTS_VARIANTS; i++) {
            CHILD_MALE_BOOTS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/boots_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_MALE_TUNIC_VARIANTS; i++) {
            CHILD_MALE_TUNIC_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/tunic_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_MALE_HOOD_VARIANTS; i++) {
            CHILD_MALE_HOOD_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hood_child" + i + ".png");
        }

        // Initialize child female texture arrays
        for (int i = 1; i <= CHILD_FEMALE_BODY_VARIANTS; i++) {
            CHILD_FEMALE_BODY_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/body_woman_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_FEMALE_EYES_VARIANTS; i++) {
            CHILD_FEMALE_EYES_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/eyes_woman_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_FEMALE_LEGS_VARIANTS; i++) {
            CHILD_FEMALE_LEGS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/legs_woman_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_FEMALE_SHIRT_VARIANTS; i++) {
            CHILD_FEMALE_SHIRT_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/shirt_woman_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_FEMALE_HAIR_VARIANTS; i++) {
            CHILD_FEMALE_HAIR_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hair_woman_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_FEMALE_BOOTS_VARIANTS; i++) {
            CHILD_FEMALE_BOOTS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/boots_woman_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_FEMALE_TUNIC_VARIANTS; i++) {
            CHILD_FEMALE_TUNIC_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/tunic_woman_child" + i + ".png");
        }
        for (int i = 1; i <= CHILD_FEMALE_HOOD_VARIANTS; i++) {
            CHILD_FEMALE_HOOD_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hood_woman_child" + i + ".png");
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

    public Peasant_Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Peasant_Model());
        this.scaleWidth = 0.95f;
        this.scaleHeight = 0.95f;
        this.shadowRadius = 0.5f;

        // Add armor rendering layer
        addRenderLayer(new ItemArmorGeoLayer<>(this) {
            @Nullable
            @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_BOOT, RIGHT_BOOT -> Peasant_Renderer.this.bootsItem;
                    case LEFT_ARMOR_LEG, RIGHT_ARMOR_LEG -> Peasant_Renderer.this.leggingsItem;
                    case CHESTPLATE, RIGHT_SLEEVE, LEFT_SLEEVE -> Peasant_Renderer.this.chestplateItem;
                    case HELMET -> Peasant_Renderer.this.helmetItem;
                    default -> null;
                };
            }

            @NotNull
            @Override
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, Peasant_Entity animatable) {
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
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, Peasant_Entity animatable, HumanoidModel<?> baseModel) {
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
            protected ItemStack getStackForBone(GeoBone bone, Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND -> Peasant_Renderer.this.offhandItem;
                    case RIGHT_HAND -> Peasant_Renderer.this.mainHandItem;
                    default -> null;
                };
            }

            @Override
            protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, Peasant_Entity animatable) {
                return switch (bone.getName()) {
                    case LEFT_HAND -> ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
                    case RIGHT_HAND -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
                    default -> ItemDisplayContext.NONE;
                };
            }

            @Override
            protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, Peasant_Entity animatable,
                                              MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
                if (stack == Peasant_Renderer.this.mainHandItem) {
                    // Main hand item transformations (right hand)
                    poseStack.mulPose(Axis.XP.rotationDegrees(-70f)); // X-axis rotation
                    poseStack.mulPose(Axis.YP.rotationDegrees(0f));   // Y-axis rotation
                    poseStack.mulPose(Axis.ZP.rotationDegrees(0f));   // Z-axis rotation

                    poseStack.translate(0, 0.25, -0.05);
                }
                else if (stack == Peasant_Renderer.this.offhandItem) {
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
    public ResourceLocation getTextureLocation(Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();

        if (entity.isChild()) {
            // Child texture selection
            if (entity.isFemale()) {
                // Female child textures: body_woman_child1.png, etc.
                int bodyVariant = (int)(Math.abs(bits) % CHILD_FEMALE_BODY_VARIANTS);
                return CHILD_FEMALE_BODY_TEXTURES[bodyVariant];
            } else {
                // Male child textures: body_child1.png, etc.
                int bodyVariant = (int)(Math.abs(bits) % CHILD_MALE_BODY_VARIANTS);
                return CHILD_MALE_BODY_TEXTURES[bodyVariant];
            }
        } else {
            // Adult texture selection (existing logic)
            if (entity.isFemale()) {
                // Use female body texture as the main texture
                int bodyVariant = (int)(Math.abs(bits) % FEMALE_BODY_VARIANTS);
                return FEMALE_BODY_TEXTURES[bodyVariant];
            } else {
                // Use male body texture as the main texture
                int bodyVariant = (int)(Math.abs(bits) % BODY_VARIANTS);
                return BODY_TEXTURES[bodyVariant];
            }
        }
    }

    @Override
    public void preRender(PoseStack poseStack, Peasant_Entity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
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
    public void actuallyRender(PoseStack poseStack, Peasant_Entity animatable, BakedGeoModel model,
                               RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer,
                               boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {

        // First, render the base entity using the main texture (body)
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, color);

        // Generate variants and colors
        long bits = animatable.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits);

        // Handle age and gender-specific rendering
        if (animatable.isChild()) {
            // Child rendering - separate texture sets based on gender
            if (animatable.isFemale()) {
                // Female child rendering
                int eyesVariant = (int)((seed >>> 8) % CHILD_FEMALE_EYES_VARIANTS);
                int legsVariant = (int)((seed >>> 16) % CHILD_FEMALE_LEGS_VARIANTS);
                int shirtVariant = (int)((seed >>> 24) % CHILD_FEMALE_SHIRT_VARIANTS);
                int hairVariant = (int)((seed >>> 32) % CHILD_FEMALE_HAIR_VARIANTS);
                int bootsVariant = (int)((seed >>> 40) % CHILD_FEMALE_BOOTS_VARIANTS);
                int tunicVariant = (int)((seed >>> 48) % CHILD_FEMALE_TUNIC_VARIANTS);
                int hoodVariant = (int)((seed >>> 56) % CHILD_FEMALE_HOOD_VARIANTS);

                int eyesColor = getPresetColor(animatable, EYES_COLORS, 1);
                int legsColor = getPresetColor(animatable, PANTS_COLORS, 2);
                int shirtColor = getPresetColor(animatable, SHIRT_COLORS, 3);
                int hairColor = getPresetColor(animatable, HAIR_COLORS, 4);
                int tunicColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 5);
                int hoodColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 6);

                // ALWAYS render eyes
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, eyesColor, CHILD_FEMALE_EYES_TEXTURES[eyesVariant]);

                // Render hair - only if not wearing head armor
                if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, hairColor, CHILD_FEMALE_HAIR_TEXTURES[hairVariant]);
                }

                // Render clothing layers - only if not wearing armor that covers them
                if (animatable.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, legsColor, CHILD_FEMALE_LEGS_TEXTURES[legsVariant]);
                }

                if (animatable.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, shirtColor, CHILD_FEMALE_SHIRT_TEXTURES[shirtVariant]);

                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, tunicColor, CHILD_FEMALE_TUNIC_TEXTURES[tunicVariant]);
                }

                if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, hoodColor, CHILD_FEMALE_HOOD_TEXTURES[hoodVariant]);
                }

                if (animatable.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, 0xFFFFFFFF, CHILD_FEMALE_BOOTS_TEXTURES[bootsVariant]);
                }
            } else {
                // Male child rendering
                int eyesVariant = (int)((seed >>> 8) % CHILD_MALE_EYES_VARIANTS);
                int legsVariant = (int)((seed >>> 16) % CHILD_MALE_LEGS_VARIANTS);
                int shirtVariant = (int)((seed >>> 24) % CHILD_MALE_SHIRT_VARIANTS);
                int hairVariant = (int)((seed >>> 32) % CHILD_MALE_HAIR_VARIANTS);
                int bootsVariant = (int)((seed >>> 40) % CHILD_MALE_BOOTS_VARIANTS);
                int tunicVariant = (int)((seed >>> 48) % CHILD_MALE_TUNIC_VARIANTS);
                int hoodVariant = (int)((seed >>> 56) % CHILD_MALE_HOOD_VARIANTS);

                int eyesColor = getPresetColor(animatable, EYES_COLORS, 1);
                int legsColor = getPresetColor(animatable, PANTS_COLORS, 2);
                int shirtColor = getPresetColor(animatable, SHIRT_COLORS, 3);
                int hairColor = getPresetColor(animatable, HAIR_COLORS, 4);
                int tunicColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 5);
                int hoodColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 6);

                // ALWAYS render eyes
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, eyesColor, CHILD_MALE_EYES_TEXTURES[eyesVariant]);

                // Render hair - only if not wearing head armor
                if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, hairColor, CHILD_MALE_HAIR_TEXTURES[hairVariant]);
                }

                // Render clothing layers - only if not wearing armor that covers them
                if (animatable.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, legsColor, CHILD_MALE_LEGS_TEXTURES[legsVariant]);
                }

                if (animatable.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, shirtColor, CHILD_MALE_SHIRT_TEXTURES[shirtVariant]);

                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, tunicColor, CHILD_MALE_TUNIC_TEXTURES[tunicVariant]);
                }

                if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, hoodColor, CHILD_MALE_HOOD_TEXTURES[hoodVariant]);
                }

                if (animatable.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
                    renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                            packedLight, packedOverlay, 0xFFFFFFFF, CHILD_MALE_BOOTS_TEXTURES[bootsVariant]);
                }
            }
        } else if (animatable.isFemale()) {
            // Female rendering - uses separate texture sets based on actual files
            int eyesVariant = (int)((seed >>> 8) % EYES_VARIANTS); // Use shared eyes textures
            int legsVariant = (int)((seed >>> 16) % FEMALE_LEGS_VARIANTS);
            int shirtVariant = (int)((seed >>> 24) % FEMALE_SHIRT_VARIANTS);
            int hairVariant = (int)((seed >>> 32) % FEMALE_HAIR_VARIANTS);
            int bootsVariant = (int)((seed >>> 40) % FEMALE_BOOTS_VARIANTS);
            int tunicVariant = (int)((seed >>> 48) % FEMALE_TUNIC_VARIANTS);
            int hoodVariant = (int)((seed >>> 56) % FEMALE_HOOD_VARIANTS);

            int eyesColor = getPresetColor(animatable, EYES_COLORS, 1);
            int legsColor = getPresetColor(animatable, PANTS_COLORS, 2);
            int shirtColor = getPresetColor(animatable, SHIRT_COLORS, 3);
            int hairColor = getPresetColor(animatable, HAIR_COLORS, 4);
            int tunicColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 5);
            int hoodColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 6);

            // ALWAYS render eyes - use shared male eyes texture
            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, eyesColor, EYES_TEXTURES[eyesVariant]);

            // Render hair - only if not wearing head armor
            if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, hairColor, FEMALE_HAIR_TEXTURES[hairVariant]);
            }

            // Render female clothing layers - only if not wearing armor that covers them
            if (animatable.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, legsColor, FEMALE_LEGS_TEXTURES[legsVariant]);
            }

            if (animatable.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, shirtColor, FEMALE_SHIRT_TEXTURES[shirtVariant]);

                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, tunicColor, FEMALE_TUNIC_TEXTURES[tunicVariant]);
            }

            if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, hoodColor, FEMALE_HOOD_TEXTURES[hoodVariant]);
            }

            if (animatable.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, 0xFFFFFFFF, FEMALE_BOOTS_TEXTURES[bootsVariant]);
            }

        } else {
            // Male rendering - existing logic with all clothing layers
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

            // ALWAYS render eyes
            renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, eyesColor, EYES_TEXTURES[eyesVariant]);

            // Render hair - only if not wearing head armor
            if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, hairColor, HAIR_TEXTURES[hairVariant]);
            }

            // Render clothing layers for males
            if (animatable.getItemBySlot(EquipmentSlot.LEGS).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);
            }

            if (animatable.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, shirtColor, SHIRT_TEXTURES[shirtVariant]);

                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, tunicColor, TUNIC_TEXTURES[tunicVariant]);
            }

            if (animatable.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, hoodColor, HOOD_TEXTURES[hoodVariant]);
            }

            if (animatable.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
                renderTextureLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                        packedLight, packedOverlay, 0xFFFFFFFF, BOOTS_TEXTURES[bootsVariant]);
            }
        }
    }

    private void renderTextureLayer(PoseStack poseStack, Peasant_Entity animatable, BakedGeoModel model,
                                    MultiBufferSource bufferSource, boolean isReRender, float partialTick,
                                    int packedLight, int packedOverlay, int color, ResourceLocation texture) {

        RenderType layerRenderType = RenderType.entityCutoutNoCull(texture);
        VertexConsumer layerBuffer = bufferSource.getBuffer(layerRenderType);

        super.actuallyRender(poseStack, animatable, model, layerRenderType, bufferSource, layerBuffer,
                isReRender, partialTick, packedLight, packedOverlay, color);
    }

    private int getPresetColor(Peasant_Entity entity, int[] colorArray, int colorIndex) {
        long bits = entity.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits) + (colorIndex * 54321);
        int colorVariant = (int)(seed % colorArray.length);
        return colorArray[colorVariant];
    }
}