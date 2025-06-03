package net.darkflameproduction.agotmod.entity.client.npc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.ItemArmorGeoLayer;

public class Northern_Peasant_Renderer extends GeoEntityRenderer<Northern_Peasant_Entity> {


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

    private static final int BODY_VARIANTS = 6;
    private static final ResourceLocation[] BODY_TEXTURES = new ResourceLocation[BODY_VARIANTS];

    static {
        for (int i = 1; i <= BODY_VARIANTS; i++) {
            BODY_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/body" + i + ".png");
        }
    }

    private static final int EYES_VARIANTS = 1;
    private static final ResourceLocation[] EYES_TEXTURES = new ResourceLocation[EYES_VARIANTS];

    static {
        for (int i = 1; i <= EYES_VARIANTS; i++) {
            EYES_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/eyes" + i + ".png");
        }
    }

    private static final int LEGS_VARIANTS = 1;
    private static final ResourceLocation[] LEGS_TEXTURES = new ResourceLocation[LEGS_VARIANTS];

    static {
        for (int i = 1; i <= LEGS_VARIANTS; i++) {
            LEGS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/legs" + i + ".png");
        }
    }

    private static final int SHIRT_VARIANTS = 1;
    private static final ResourceLocation[] SHIRT_TEXTURES = new ResourceLocation[SHIRT_VARIANTS];

    static {
        for (int i = 1; i <= SHIRT_VARIANTS; i++) {
            SHIRT_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/shirt" + i + ".png");
        }
    }

    private static final int HAIR_VARIANTS = 24;
    private static final ResourceLocation[] HAIR_TEXTURES = new ResourceLocation[HAIR_VARIANTS];

    static {
        for (int i = 1; i <= HAIR_VARIANTS; i++) {
            HAIR_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hair" + i + ".png");
        }
    }

    private static final int BOOTS_VARIANTS = 2;
    private static final ResourceLocation[] BOOTS_TEXTURES = new ResourceLocation[BOOTS_VARIANTS];

    static {
        for (int i = 1; i <= BOOTS_VARIANTS; i++) {
            BOOTS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/boots" + i + ".png");
        }
    }

    private static final int TUNIC_VARIANTS = 4;
    private static final ResourceLocation[] TUNIC_TEXTURES = new ResourceLocation[TUNIC_VARIANTS];

    static {
        for (int i = 1; i <= TUNIC_VARIANTS; i++) {
            TUNIC_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/tunic" + i + ".png");
        }
    }

    private static final int HOOD_VARIANTS = 3;
    private static final ResourceLocation[] HOOD_TEXTURES = new ResourceLocation[HOOD_VARIANTS];

    static {
        for (int i = 1; i <= HOOD_VARIANTS; i++) {
            HOOD_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hood" + i + ".png");
        }
    }

    public Northern_Peasant_Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Northern_Peasant_Model());
        this.scaleWidth = 0.95f;
        this.scaleHeight = 0.95f;
        this.shadowRadius = 0.5f;

        addRenderLayer(new ItemArmorGeoLayer<>(this) {
            @Nullable
            @Override
            protected ItemStack getArmorItemForBone(GeoBone bone, Northern_Peasant_Entity animatable) {
                String boneName = bone.getName();

                if ("armor_head".equals(boneName) || "armorHead".equals(boneName)) {
                    ItemStack helmet = animatable.getItemBySlot(EquipmentSlot.HEAD);
                    return helmet.isEmpty() ? null : helmet;
                }

                if ("armor_body".equals(boneName) || "armorBody".equals(boneName) ||
                        "armor_right_arm".equals(boneName) || "armorRightArm".equals(boneName) ||
                        "armor_left_arm".equals(boneName) || "armorLeftArm".equals(boneName)) {
                    ItemStack chestplate = animatable.getItemBySlot(EquipmentSlot.CHEST);
                    return chestplate.isEmpty() ? null : chestplate;
                }

                if ("armor_right_leg".equals(boneName) || "armorRightLeg".equals(boneName) ||
                        "armor_left_leg".equals(boneName) || "armorLeftLeg".equals(boneName)) {
                    ItemStack leggings = animatable.getItemBySlot(EquipmentSlot.LEGS);
                    return leggings.isEmpty() ? null : leggings;
                }

                if ("armor_right_boot".equals(boneName) || "armorRightBoot".equals(boneName) ||
                        "armor_left_boot".equals(boneName) || "armorLeftBoot".equals(boneName)) {
                    ItemStack boots = animatable.getItemBySlot(EquipmentSlot.FEET);
                    return boots.isEmpty() ? null : boots;
                }

                return null;
            }

            @NotNull
            @Override
            protected EquipmentSlot getEquipmentSlotForBone(GeoBone bone, ItemStack stack, Northern_Peasant_Entity animatable) {
                String boneName = bone.getName();

                if ("armor_right_boot".equals(boneName) || "armorRightBoot".equals(boneName) ||
                        "armor_left_boot".equals(boneName) || "armorLeftBoot".equals(boneName)) {
                    return EquipmentSlot.FEET;
                }

                if ("armor_right_leg".equals(boneName) || "armorRightLeg".equals(boneName) ||
                        "armor_left_leg".equals(boneName) || "armorLeftLeg".equals(boneName)) {
                    return EquipmentSlot.LEGS;
                }

                if ("armor_right_arm".equals(boneName) || "armorRightArm".equals(boneName)) {
                    return !animatable.isLeftHanded() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
                }

                if ("armor_left_arm".equals(boneName) || "armorLeftArm".equals(boneName)) {
                    return animatable.isLeftHanded() ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
                }

                if ("armor_body".equals(boneName) || "armorBody".equals(boneName)) {
                    return EquipmentSlot.CHEST;
                }

                if ("armor_head".equals(boneName) || "armorHead".equals(boneName)) {
                    return EquipmentSlot.HEAD;
                }

                return super.getEquipmentSlotForBone(bone, stack, animatable);
            }

            @NotNull
            @Override
            protected ModelPart getModelPartForBone(GeoBone bone, EquipmentSlot slot, ItemStack stack, Northern_Peasant_Entity animatable, HumanoidModel<?> baseModel) {
                String boneName = bone.getName();

                if ("armor_left_boot".equals(boneName) || "armorLeftBoot".equals(boneName) ||
                        "armor_left_leg".equals(boneName) || "armorLeftLeg".equals(boneName)) {
                    return baseModel.leftLeg;
                }

                if ("armor_right_boot".equals(boneName) || "armorRightBoot".equals(boneName) ||
                        "armor_right_leg".equals(boneName) || "armorRightLeg".equals(boneName)) {
                    return baseModel.rightLeg;
                }

                if ("armor_right_arm".equals(boneName) || "armorRightArm".equals(boneName)) {
                    return baseModel.rightArm;
                }

                if ("armor_left_arm".equals(boneName) || "armorLeftArm".equals(boneName)) {
                    return baseModel.leftArm;
                }

                if ("armor_body".equals(boneName) || "armorBody".equals(boneName)) {
                    return baseModel.body;
                }

                if ("armor_head".equals(boneName) || "armorHead".equals(boneName)) {
                    return baseModel.head;
                }

                return super.getModelPartForBone(bone, slot, stack, animatable, baseModel);
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int bodyVariant = (int)(Math.abs(bits) % BODY_VARIANTS);
        return BODY_TEXTURES[bodyVariant];
    }

    @Override
    public void preRender(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }

    @Override
    public void actuallyRender(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model,
                               RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer,
                               boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {

        boolean hasCustomHelmet = isCustomGeckoLibArmor(animatable.getItemBySlot(EquipmentSlot.HEAD));
        boolean hasCustomChestplate = isCustomGeckoLibArmor(animatable.getItemBySlot(EquipmentSlot.CHEST));
        boolean hasCustomLeggings = isCustomGeckoLibArmor(animatable.getItemBySlot(EquipmentSlot.LEGS));
        boolean hasCustomBoots = isCustomGeckoLibArmor(animatable.getItemBySlot(EquipmentSlot.FEET));

        long bits = animatable.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits);

        int bodyVariant = (int)(seed % BODY_VARIANTS);
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


        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, BODY_TEXTURES[bodyVariant]);

        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, eyesColor, EYES_TEXTURES[eyesVariant]);

        if (!hasCustomLeggings) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);
        }

        if (!hasCustomChestplate) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, shirtColor, SHIRT_TEXTURES[shirtVariant]);
        }

        if (!hasCustomHelmet) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, hairColor, HAIR_TEXTURES[hairVariant]);
        }

        if (!hasCustomLeggings) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);
        }

        if (!hasCustomBoots) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, 0xFFFFFFFF, BOOTS_TEXTURES[bootsVariant]);
        }

        if (!hasCustomHelmet) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, hairColor, HAIR_TEXTURES[hairVariant]);
        }

        if (!hasCustomChestplate) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, tunicColor, TUNIC_TEXTURES[tunicVariant]);
        }

        if (!hasCustomHelmet) {
            renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                    packedLight, packedOverlay, hoodColor, HOOD_TEXTURES[hoodVariant]);
        }
    }

    private void renderLayer(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model,
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


    private boolean isCustomGeckoLibArmor(ItemStack stack) {
        if (stack.isEmpty()) return false;
        return stack.getItem() instanceof software.bernie.geckolib.animatable.GeoItem;
    }
}