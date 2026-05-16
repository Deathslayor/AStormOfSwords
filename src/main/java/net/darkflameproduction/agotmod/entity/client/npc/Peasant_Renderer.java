package net.darkflameproduction.agotmod.entity.client.npc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.Culture;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureGroup;
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

import java.util.HashMap;
import java.util.Map;

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

    static final int[] EYES_COLORS = {
            0xFF245014, 0xFF2d6618, 0xFF377a1e, 0xFF7a521e, 0xFF543b1b, 0xFF3e2a12,
            0xFF201609, 0xFF0e263b, 0xFF21215c, 0xFF262793, 0xFF191bc0
    };
    static final int[] HAIR_COLORS = {
            0xFF715017, 0xFF644819, 0xFF55401b, 0xFF433215, 0xFF31240e, 0xFF231909,
            0xFF2c1c02, 0xFF52270c, 0xFF69300c, 0xFF7a3d16, 0xFF6b2813, 0xFF551a08,
            0xFF7e2a0f, 0xFF8f1e11, 0xFF671107, 0xFF675916, 0xFF7f6c14, 0xFFa78d10,
            0xFFbb9f1d, 0xFF939393, 0xFF777777, 0xFFb63d08, 0xFFd5380d
    };
    static final int[] SHIRT_COLORS = {
            0xFFe7e7e7, 0xFFa9a39a, 0xFF988569, 0xFFae8f5f, 0xFFd79e46, 0xFFaf751d,
            0xFFcbcda2, 0xFFa0a27b, 0xFF92b772, 0xFF6a8d4b, 0xFF507530, 0xFF5dc05c,
            0xFF3d943d, 0xFF248124, 0xFF115210, 0xFF355d4f, 0xFF699d8b, 0xFF97cab8,
            0xFF69687e, 0xFF7c78c3, 0xFF8b89b7, 0xFF232d8c, 0xFFc19c9c, 0xFF895656,
            0xFF8a3d3d, 0xFF642222, 0xFF8b1717
    };
    static final int[] PANTS_COLORS = {
            0xFF331d0b, 0xFF43250c, 0xFF221206, 0xFF37210e, 0xFF2d1e12, 0xFF2e251d,
            0xFF24201d, 0xFF291911, 0xFF391c0e, 0xFF1f0c03, 0xFF2a0e00, 0xFF370605,
            0xFF270201, 0xFF451312
    };
    static final int[] TUNIC_HOOD_COLORS = {
            0xFF320808, 0xFF5f3010, 0xFF3e1f09, 0xFF271508, 0xFF1b110a, 0xFF231f1c,
            0xFF423c1d, 0xFF393107, 0xFF133347, 0xFF071d2b, 0xFF050f15, 0xFF28390a,
            0xFF0a392a, 0xFF15523f
    };

    private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            AGoTMod.MOD_ID, "textures/entity/northernpeasant/body1.png");

    private static final Map<String, TextureSet> TEXTURE_SETS = new HashMap<>();

    static {
        buildTextureSet("northernpeasant",
                6, 1, 1, 1, 24, 2, 4, 3,
                6, 1, 1, 1,  1, 2, 4, 1,
                3, 1, 1, 1,  7, 2, 4, 1,
                1, 1, 1, 1,  1, 1, 1, 1);
        buildTextureSet("wildlingpeasant",
                6, 1, 1, 1, 24, 2, 4, 3,
                6, 1, 1, 1,  1, 2, 4, 1,
                3, 1, 1, 1,  7, 2, 4, 1,
                1, 1, 1, 1,  1, 1, 1, 1);
    }

    private static void buildTextureSet(String folder,
                                        int mBody, int mEyes, int mLegs, int mShirt, int mHair, int mBoots, int mTunic, int mHood,
                                        int fBody, int fEyes, int fLegs, int fShirt, int fHair, int fBoots, int fTunic, int fHood,
                                        int cmBody, int cmEyes, int cmLegs, int cmShirt, int cmHair, int cmBoots, int cmTunic, int cmHood,
                                        int cfBody, int cfEyes, int cfLegs, int cfShirt, int cfHair, int cfBoots, int cfTunic, int cfHood) {

        String base = "textures/entity/" + folder + "/";
        TextureSet ts = new TextureSet();
        ts.mBody  = build(base, "body",             mBody);
        ts.mEyes  = build(base, "eyes",             mEyes);
        ts.mLegs  = build(base, "legs",             mLegs);
        ts.mShirt = build(base, "shirt",            mShirt);
        ts.mHair  = build(base, "hair",             mHair);
        ts.mBoots = build(base, "boots",            mBoots);
        ts.mTunic = build(base, "tunic",            mTunic);
        ts.mHood  = build(base, "hood",             mHood);
        ts.fBody  = build(base, "body_woman",       fBody);
        ts.fEyes  = ts.mEyes;
        ts.fLegs  = build(base, "legs_woman",       fLegs);
        ts.fShirt = build(base, "shirt_woman",      fShirt);
        ts.fHair  = build(base, "hair_woman",       fHair);
        ts.fBoots = build(base, "boots_woman",      fBoots);
        ts.fTunic = build(base, "tunic_woman",      fTunic);
        ts.fHood  = build(base, "hood_woman",       fHood);
        ts.cmBody  = build(base, "body_child",       cmBody);
        ts.cmEyes  = build(base, "eyes_child",       cmEyes);
        ts.cmLegs  = build(base, "legs_child",       cmLegs);
        ts.cmShirt = build(base, "shirt_child",      cmShirt);
        ts.cmHair  = build(base, "hair_child",       cmHair);
        ts.cmBoots = build(base, "boots_child",      cmBoots);
        ts.cmTunic = build(base, "tunic_child",      cmTunic);
        ts.cmHood  = build(base, "hood_child",       cmHood);
        ts.cfBody  = build(base, "body_woman_child",  cfBody);
        ts.cfEyes  = build(base, "eyes_woman_child",  cfEyes);
        ts.cfLegs  = build(base, "legs_woman_child",  cfLegs);
        ts.cfShirt = build(base, "shirt_woman_child", cfShirt);
        ts.cfHair  = build(base, "hair_woman_child",  cfHair);
        ts.cfBoots = build(base, "boots_woman_child", cfBoots);
        ts.cfTunic = build(base, "tunic_woman_child", cfTunic);
        ts.cfHood  = build(base, "hood_woman_child",  cfHood);
        TEXTURE_SETS.put(folder, ts);
    }

    private static ResourceLocation[] build(String base, String name, int count) {
        ResourceLocation[] arr = new ResourceLocation[count];
        for (int i = 0; i < count; i++)
            arr[i] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, base + name + (i + 1) + ".png");
        return arr;
    }

    private static class TextureSet {
        ResourceLocation[] mBody, mEyes, mLegs, mShirt, mHair, mBoots, mTunic, mHood;
        ResourceLocation[] fBody, fEyes, fLegs, fShirt, fHair, fBoots, fTunic, fHood;
        ResourceLocation[] cmBody, cmEyes, cmLegs, cmShirt, cmHair, cmBoots, cmTunic, cmHood;
        ResourceLocation[] cfBody, cfEyes, cfLegs, cfShirt, cfHair, cfBoots, cfTunic, cfHood;
    }

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

    // ── Texture location — reads from synced entity data ─────────────────────

    @Override
    public ResourceLocation getTextureLocation(Peasant_Entity entity) {
        TextureSet ts = getTextureSet(entity);
        if (ts == null) return DEFAULT_TEXTURE;

        int bodyIdx = entity.getSyncedBody();
        if (entity.isChild()) {
            return entity.isFemale()
                    ? ts.cfBody[bodyIdx % ts.cfBody.length]
                    : ts.cmBody[bodyIdx % ts.cmBody.length];
        } else {
            return entity.isFemale()
                    ? ts.fBody[bodyIdx % ts.fBody.length]
                    : ts.mBody[bodyIdx % ts.mBody.length];
        }
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

        TextureSet ts = getTextureSet(animatable);
        if (ts == null) return;

        if (animatable.isChild()) {
            if (animatable.isFemale()) renderLayers(poseStack, animatable, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay, animatable, ts.cfEyes, ts.cfHair, ts.cfLegs, ts.cfShirt, ts.cfTunic, ts.cfHood, ts.cfBoots);
            else                       renderLayers(poseStack, animatable, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay, animatable, ts.cmEyes, ts.cmHair, ts.cmLegs, ts.cmShirt, ts.cmTunic, ts.cmHood, ts.cmBoots);
        } else if (animatable.isFemale()) {
            renderLayers(poseStack, animatable, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay, animatable, ts.fEyes, ts.fHair, ts.fLegs, ts.fShirt, ts.fTunic, ts.fHood, ts.fBoots);
        } else {
            renderLayers(poseStack, animatable, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay, animatable, ts.mEyes, ts.mHair, ts.mLegs, ts.mShirt, ts.mTunic, ts.mHood, ts.mBoots);
        }
    }

    // ── Reads all indices from synced entity data (client-safe) ──────────────

    private void renderLayers(PoseStack poseStack, Peasant_Entity entity, BakedGeoModel model,
                              MultiBufferSource bufferSource, boolean isReRender, float partialTick,
                              int packedLight, int packedOverlay, Peasant_Entity src,
                              ResourceLocation[] eyes, ResourceLocation[] hair,
                              ResourceLocation[] legs, ResourceLocation[] shirt,
                              ResourceLocation[] tunic, ResourceLocation[] hood,
                              ResourceLocation[] boots) {

        int eyesColor  = EYES_COLORS     [src.getSyncedEyesColor()  % EYES_COLORS.length];
        int hairColor  = HAIR_COLORS     [src.getSyncedHairColor()  % HAIR_COLORS.length];
        int legsColor  = PANTS_COLORS    [src.getSyncedPantsColor() % PANTS_COLORS.length];
        int shirtColor = SHIRT_COLORS    [src.getSyncedShirtColor() % SHIRT_COLORS.length];
        int tunicColor = TUNIC_HOOD_COLORS[src.getSyncedTunicColor() % TUNIC_HOOD_COLORS.length];
        int hoodColor  = TUNIC_HOOD_COLORS[src.getSyncedHoodColor()  % TUNIC_HOOD_COLORS.length];

        renderLayer(poseStack, entity, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay,
                eyesColor, eyes[src.getSyncedEyes() % eyes.length]);

        if (entity.getItemBySlot(EquipmentSlot.HEAD).isEmpty())
            renderLayer(poseStack, entity, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay,
                    hairColor, hair[src.getSyncedHair() % hair.length]);

        if (entity.getItemBySlot(EquipmentSlot.LEGS).isEmpty())
            renderLayer(poseStack, entity, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay,
                    legsColor, legs[src.getSyncedLegs() % legs.length]);

        if (entity.getItemBySlot(EquipmentSlot.CHEST).isEmpty()) {
            renderLayer(poseStack, entity, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay,
                    shirtColor, shirt[src.getSyncedShirt() % shirt.length]);
            renderLayer(poseStack, entity, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay,
                    tunicColor, tunic[src.getSyncedTunic() % tunic.length]);
        }

        if (entity.getItemBySlot(EquipmentSlot.HEAD).isEmpty())
            renderLayer(poseStack, entity, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay,
                    hoodColor, hood[src.getSyncedHood() % hood.length]);

        if (entity.getItemBySlot(EquipmentSlot.FEET).isEmpty())
            renderLayer(poseStack, entity, model, bufferSource, isReRender, partialTick, packedLight, packedOverlay,
                    0xFFFFFFFF, boots[src.getSyncedBoots() % boots.length]);
    }

    private void renderLayer(PoseStack poseStack, Peasant_Entity entity, BakedGeoModel model,
                             MultiBufferSource bufferSource, boolean isReRender, float partialTick,
                             int packedLight, int packedOverlay, int color, ResourceLocation texture) {
        RenderType rt = RenderType.entityCutoutNoCull(texture);
        super.actuallyRender(poseStack, entity, model, rt, bufferSource, bufferSource.getBuffer(rt),
                isReRender, partialTick, packedLight, packedOverlay, color);
    }

    @Nullable
    private TextureSet getTextureSet(Peasant_Entity entity) {
        String cultureName = entity.getSyncedCulture();
        if (cultureName.equals("NONE")) return null;
        try {
            Culture culture = Culture.valueOf(cultureName);
            if (culture.group == CultureGroup.NONE) return null;
            return TEXTURE_SETS.get(culture.group.textureFolder);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}