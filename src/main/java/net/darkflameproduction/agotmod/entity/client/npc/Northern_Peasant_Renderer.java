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

    // ========== BODY TEXTURES ==========
    private static final int BODY_VARIANTS = 1;
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
    private static final int HAIR_VARIANTS = 5; // Change this number as needed
    private static final ResourceLocation[] HAIR_TEXTURES = new ResourceLocation[HAIR_VARIANTS];

    static {
        for (int i = 1; i <= HAIR_VARIANTS; i++) {
            HAIR_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/hair" + i + ".png");
        }
    }

    // ========== BOOTS TEXTURES ==========
    private static final int BOOTS_VARIANTS = 1; // Change this number as needed
    private static final ResourceLocation[] BOOTS_TEXTURES = new ResourceLocation[BOOTS_VARIANTS];

    static {
        for (int i = 1; i <= BOOTS_VARIANTS; i++) {
            BOOTS_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/boots" + i + ".png");
        }
    }

    // ========== TUNIC TEXTURES ==========
    private static final int TUNIC_VARIANTS = 1; // Change this number as needed
    private static final ResourceLocation[] TUNIC_TEXTURES = new ResourceLocation[TUNIC_VARIANTS];

    static {
        for (int i = 1; i <= TUNIC_VARIANTS; i++) {
            TUNIC_TEXTURES[i - 1] = ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                    "textures/entity/northernpeasant/tunic" + i + ".png");
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

        // Generate spawn egg style colors for specific layers
        int legsColor = generateSpawnEggColor(animatable, 1);
        int shirtColor = generateSpawnEggColor(animatable, 2);
        int tunicColor = generateSpawnEggColor(animatable, 3);

        // Render layers in order: body, eyes, legs, shirt, hair, legs, boots, hair, tunic

        // Layer 1: Body (bottom layer) - no color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, BODY_TEXTURES[bodyVariant]);

        // Layer 2: Eyes - no color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, EYES_TEXTURES[eyesVariant]);

        // Layer 3: Legs (first time) - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);

        // Layer 4: Shirt - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, shirtColor, SHIRT_TEXTURES[shirtVariant]);

        // Layer 5: Hair (first time) - no color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, HAIR_TEXTURES[hairVariant]);

        // Layer 6: Legs (second time) - with same color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, legsColor, LEGS_TEXTURES[legsVariant]);

        // Layer 7: Boots - no color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, BOOTS_TEXTURES[bootsVariant]);

        // Layer 8: Hair (second time) - no color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, 0xFFFFFFFF, HAIR_TEXTURES[hairVariant]);

        // Layer 9: Tunic (top layer) - with color overlay
        renderLayer(poseStack, animatable, model, bufferSource, isReRender, partialTick,
                packedLight, packedOverlay, tunicColor, TUNIC_TEXTURES[tunicVariant]);
    }

    private void renderLayer(PoseStack poseStack, Northern_Peasant_Entity animatable, BakedGeoModel model,
                             MultiBufferSource bufferSource, boolean isReRender, float partialTick,
                             int packedLight, int packedOverlay, int color, ResourceLocation texture) {

        RenderType layerRenderType = RenderType.entityCutoutNoCull(texture);
        VertexConsumer layerBuffer = bufferSource.getBuffer(layerRenderType);

        super.actuallyRender(poseStack, animatable, model, layerRenderType, bufferSource, layerBuffer,
                isReRender, partialTick, packedLight, packedOverlay, color);
    }

    // ========== SPAWN EGG STYLE COLOR GENERATION ==========

    private int generateSpawnEggColor(Northern_Peasant_Entity entity, int colorIndex) {
        // Use entity UUID to generate consistent colors like spawn eggs do
        long bits = entity.getUUID().getLeastSignificantBits();
        long seed = Math.abs(bits) + (colorIndex * 12345); // Different seed for each color

        // Generate random color values (like spawn egg colors)
        int color = (int)(seed % 0xFFFFFF); // Random 24-bit color
        return 0xFF000000 | color; // Add full alpha to make it opaque
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

    // ========== COLOR UTILITY METHODS ==========

    public int getLegsColor(Northern_Peasant_Entity entity) {
        return generateSpawnEggColor(entity, 1);
    }

    public int getShirtColor(Northern_Peasant_Entity entity) {
        return generateSpawnEggColor(entity, 2);
    }

    public int getTunicColor(Northern_Peasant_Entity entity) {
        return generateSpawnEggColor(entity, 3);
    }
}