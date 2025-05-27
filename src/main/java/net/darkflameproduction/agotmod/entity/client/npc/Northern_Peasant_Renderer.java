package net.darkflameproduction.agotmod.entity.client.npc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class Northern_Peasant_Renderer extends GeoEntityRenderer<Northern_Peasant_Entity> {

    // ========== PRESET COLOR ARRAYS ==========

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

    // ========== BODY TEXTURES ==========
    private static final int BODY_VARIANTS = 6;
    private static final ResourceLocation[] BODY_TEXTURES = new ResourceLocation[BODY_VARIANTS];

    static {
        for (int i = 1; i <= BODY_VARIANTS; i++) {
            BODY_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/body" + i + ".png");
        }
    }

    // ========== EYES TEXTURES ==========
    private static final int EYES_VARIANTS = 1; // Change this number as needed
    private static final ResourceLocation[] EYES_TEXTURES = new ResourceLocation[EYES_VARIANTS];

    static {
        for (int i = 1; i <= EYES_VARIANTS; i++) {
            EYES_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/eyes" + i + ".png");
        }
    }

    // ========== LEGS TEXTURES ==========
    private static final int LEGS_VARIANTS = 1; // Change this number as needed
    private static final ResourceLocation[] LEGS_TEXTURES = new ResourceLocation[LEGS_VARIANTS];

    static {
        for (int i = 1; i <= LEGS_VARIANTS; i++) {
            LEGS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/legs" + i + ".png");
        }
    }

    // ========== SHIRT TEXTURES ==========
    private static final int SHIRT_VARIANTS = 1; // Change this number as needed
    private static final ResourceLocation[] SHIRT_TEXTURES = new ResourceLocation[SHIRT_VARIANTS];

    static {
        for (int i = 1; i <= SHIRT_VARIANTS; i++) {
            SHIRT_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/shirt" + i + ".png");
        }
    }

    // ========== HAIR TEXTURES ==========
    private static final int HAIR_VARIANTS = 24; // Change this number as needed
    private static final ResourceLocation[] HAIR_TEXTURES = new ResourceLocation[HAIR_VARIANTS];

    static {
        for (int i = 1; i <= HAIR_VARIANTS; i++) {
            HAIR_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hair" + i + ".png");
        }
    }

    // ========== BOOTS TEXTURES ==========
    private static final int BOOTS_VARIANTS = 2; // Change this number as needed
    private static final ResourceLocation[] BOOTS_TEXTURES = new ResourceLocation[BOOTS_VARIANTS];

    static {
        for (int i = 1; i <= BOOTS_VARIANTS; i++) {
            BOOTS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/boots" + i + ".png");
        }
    }

    // ========== TUNIC TEXTURES ==========
    private static final int TUNIC_VARIANTS = 4; // Change this number as needed
    private static final ResourceLocation[] TUNIC_TEXTURES = new ResourceLocation[TUNIC_VARIANTS];

    static {
        for (int i = 1; i <= TUNIC_VARIANTS; i++) {
            TUNIC_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/tunic" + i + ".png");
        }
    }

    // ========== HOOD TEXTURES ==========
    private static final int HOOD_VARIANTS = 3; // Change this number as needed
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
    }

    @Override
    public ResourceLocation getTextureLocation(Northern_Peasant_Entity entity) {
        // Return the base body texture (will be overridden by layered rendering)
        long bits = entity.getUUID().getLeastSignificantBits();
        int bodyVariant = (int)(Math.abs(bits) % BODY_VARIANTS);
        return BODY_TEXTURES[bodyVariant];
    }

    @Override
    public void actuallyRender(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model,
                               RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer,
                               boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {

        // Generate different variants for each layer using UUID
        long bits = animatable.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits);

        // Calculate variants for each layer independently
        int bodyVariant = (int)(seed % BODY_VARIANTS);
        int eyesVariant = (int)((seed >>> 8) % EYES_VARIANTS);
        int legsVariant = (int)((seed >>> 16) % LEGS_VARIANTS);
        int shirtVariant = (int)((seed >>> 24) % SHIRT_VARIANTS);
        int hairVariant = (int)((seed >>> 32) % HAIR_VARIANTS);
        int bootsVariant = (int)((seed >>> 40) % BOOTS_VARIANTS);
        int tunicVariant = (int)((seed >>> 48) % TUNIC_VARIANTS);
        int hoodVariant = (int)((seed >>> 56) % HOOD_VARIANTS);

        // Generate preset colors for each layer
        int eyesColor = getPresetColor(animatable, EYES_COLORS, 1);
        int legsColor = getPresetColor(animatable, PANTS_COLORS, 2);
        int shirtColor = getPresetColor(animatable, SHIRT_COLORS, 3);
        int hairColor = getPresetColor(animatable, HAIR_COLORS, 4);
        int tunicColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 5);
        int hoodColor = getPresetColor(animatable, TUNIC_HOOD_COLORS, 6);

        // Render layers in order: body, eyes, legs, shirt, hair, legs, boots, hair, tunic, hood

        // Layer 1: Body (bottom layer) - no color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, BODY_TEXTURES[bodyVariant]);

        // Layer 2: Eyes - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, eyesColor, EYES_TEXTURES[eyesVariant]);

        // Layer 3: Legs (first time) - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);

        // Layer 4: Shirt - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, shirtColor, SHIRT_TEXTURES[shirtVariant]);

        // Layer 5: Hair (first time) - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, hairColor, HAIR_TEXTURES[hairVariant]);

        // Layer 6: Legs (second time) - with same color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);

        // Layer 7: Boots - no color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, BOOTS_TEXTURES[bootsVariant]);

        // Layer 8: Hair (second time) - with same color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, hairColor, HAIR_TEXTURES[hairVariant]);

        // Layer 9: Tunic - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, tunicColor, TUNIC_TEXTURES[tunicVariant]);

        // Layer 10: Hood (top layer) - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, hoodColor, HOOD_TEXTURES[hoodVariant]);
    }

    private void renderLayer(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model,
                             MultiBufferSource bufferSource, boolean isReRender, float partialTick,
                             int packedLight, int packedOverlay, int color, ResourceLocation texture) {

        RenderType layerRenderType = RenderType.entityCutoutNoCull(texture);
        VertexConsumer layerBuffer = bufferSource.getBuffer(layerRenderType);

        super.actuallyRender(poseStack, animatable, model, layerRenderType, bufferSource, layerBuffer,
                isReRender, partialTick, packedLight, packedOverlay, color);
    }

    // ========== PRESET COLOR SELECTION ==========

    private int getPresetColor(Northern_Peasant_Entity entity, int[] colorArray, int colorIndex) {
        // Use entity UUID to generate consistent color selection
        long bits = entity.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits) + (colorIndex * 54321); // Different seed for each color type

        int colorVariant = (int)(seed % colorArray.length);
        return colorArray[colorVariant];
    }

    @Override
    public void renderRecursively(PoseStack poseStack, Northern_Peasant_Entity animatable, GeoBone bone,
                                  RenderType renderType, MultiBufferSource bufferSource,
                                  VertexConsumer buffer, boolean isReRender, float partialTick,
                                  int packedLight, int packedOverlay, int color) {

        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer,
                isReRender, partialTick, packedLight, packedOverlay, color);

        // Handle item rendering in right arm
        if ("right_arm".equals(bone.getName()) && animatable.getHungerSystem().isEating()) {
            ItemStack eatingItem = animatable.getHungerSystem().getEatingItem();
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

    // ========== UTILITY METHODS FOR DEBUGGING ==========

    public ResourceLocation getBodyTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)(Math.abs(bits) % BODY_VARIANTS);
        return BODY_TEXTURES[variant];
    }

    public ResourceLocation getEyesTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)((Math.abs(bits) >>> 8) % EYES_VARIANTS);
        return EYES_TEXTURES[variant];
    }

    public ResourceLocation getLegsTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)((Math.abs(bits) >>> 16) % LEGS_VARIANTS);
        return LEGS_TEXTURES[variant];
    }

    public ResourceLocation getShirtTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)((Math.abs(bits) >>> 24) % SHIRT_VARIANTS);
        return SHIRT_TEXTURES[variant];
    }

    public ResourceLocation getHairTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)((Math.abs(bits) >>> 32) % HAIR_VARIANTS);
        return HAIR_TEXTURES[variant];
    }

    public ResourceLocation getBootsTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)((Math.abs(bits) >>> 40) % BOOTS_VARIANTS);
        return BOOTS_TEXTURES[variant];
    }

    public ResourceLocation getTunicTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)((Math.abs(bits) >>> 48) % TUNIC_VARIANTS);
        return TUNIC_TEXTURES[variant];
    }

    public ResourceLocation getHoodTexture(Northern_Peasant_Entity entity) {
        long bits = entity.getUUID().getLeastSignificantBits();
        int variant = (int)((Math.abs(bits) >>> 56) % HOOD_VARIANTS);
        return HOOD_TEXTURES[variant];
    }

    // ========== COLOR UTILITY METHODS ==========

    public int getEyesColor(Northern_Peasant_Entity entity) {
        return getPresetColor(entity, EYES_COLORS, 1);
    }

    public int getLegsColor(Northern_Peasant_Entity entity) {
        return getPresetColor(entity, PANTS_COLORS, 2);
    }

    public int getShirtColor(Northern_Peasant_Entity entity) {
        return getPresetColor(entity, SHIRT_COLORS, 3);
    }

    public int getHairColor(Northern_Peasant_Entity entity) {
        return getPresetColor(entity, HAIR_COLORS, 4);
    }

    public int getTunicColor(Northern_Peasant_Entity entity) {
        return getPresetColor(entity, TUNIC_HOOD_COLORS, 5);
    }

    public int getHoodColor(Northern_Peasant_Entity entity) {
        return getPresetColor(entity, TUNIC_HOOD_COLORS, 6);
    }
}