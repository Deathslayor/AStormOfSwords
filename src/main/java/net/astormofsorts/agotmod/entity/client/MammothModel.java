package net.astormofsorts.agotmod.entity.client;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.astormofsorts.agotmod.entity.animations.ModAnimationDefinitions;
import net.astormofsorts.agotmod.entity.custom.MammothEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;


/**
 * The MammothModel class represents the 3D model for the MammothEntity in the client-side rendering.
 * It extends HierarchicalModel, providing a hierarchical structure for organizing model parts.
 *
 * @param <T> The type of entity associated with the model.
 */
public class MammothModel<T extends Entity> extends HierarchicalModel<T> {
    // The root model part representing the entire Mammoth model
    private final ModelPart mammoth;
    // The model part representing the head of the Mammoth
    private final ModelPart head;

    /**
     * Constructor for MammothModel.
     *
     * @param root The root model part of the Mammoth model.
     */
    public MammothModel(ModelPart root) {
        // Get references to the Mammoth and head model parts from the provided root
        this.mammoth = root.getChild("mammoth");
        this.head = mammoth.getChild("body").getChild("head");
    }

    /**
     * Creates a LayerDefinition for a Mammoth model.
     * The method defines the mesh structure using CubeListBuilder and PartDefinition,
     * setting the position, size, and textures for each part of the Mammoth.
     *
     * @return The LayerDefinition for the Mammoth model with a specified texture size.
     */

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition mammoth = partdefinition.addOrReplaceChild("mammoth", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0019F, 10.0F, -0.5882F, 0.0F, 3.1416F, 0.0F));

        PartDefinition body = mammoth.addOrReplaceChild("body", CubeListBuilder.create().texOffs(62, 93).addBox(-8.0F, -16.0F, -3.0F, 16.0F, 18.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(78, 65).addBox(-7.0F, -14.0F, -14.0F, 14.0F, 16.0F, 11.0F, new CubeDeformation(0.0F))
                .texOffs(0, 85).addBox(-8.0F, 2.0F, -3.0F, 16.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
                .texOffs(0, 105).addBox(-7.0F, 2.0F, -14.0F, 14.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 4.0F, -1.0F));

        PartDefinition shoulders = body.addOrReplaceChild("shoulders", CubeListBuilder.create(), PartPose.offset(0.0F, -13.0F, 1.25F));

        PartDefinition cube_r1 = shoulders.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(38, 54).addBox(-5.5F, 0.1603F, 6.1147F, 11.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -9.0F, 0.5236F, 0.0F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, -13.5F));

        PartDefinition cube_r2 = tail.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(49, 39).addBox(-1.0F, -4.0706F, 7.7274F, 2.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, -9.0F, -0.2618F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 11.0F));

        PartDefinition head_cube_r1 = head.addOrReplaceChild("head_cube_r1", CubeListBuilder.create().texOffs(88, 33).addBox(-5.0F, -26.9294F, -2.2726F, 10.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 20.0F, 9.0F, 0.2618F, 0.0F, 0.0F));

        PartDefinition ears = head.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 6.0F));

        PartDefinition ear_left = ears.addOrReplaceChild("ear_left", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = ear_left.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(53, 31).addBox(5.4737F, -3.6791F, -2.0359F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.0F, 3.0F, 0.2706F, 0.2527F, 0.0692F));

        PartDefinition ear_right = ears.addOrReplaceChild("ear_right", CubeListBuilder.create(), PartPose.offset(-5.0F, 0.0F, 0.0F));

        PartDefinition cube_r4 = ear_right.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(53, 31).addBox(-7.0F, -24.9294F, 3.4641F, 3.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 22.0F, 3.0F, 0.2706F, -0.2527F, -0.0692F));

        PartDefinition tusk = head.addOrReplaceChild("tusk", CubeListBuilder.create(), PartPose.offset(11.0F, 20.0F, 9.0F));

        PartDefinition cube_r5 = tusk.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 49).addBox(-0.4129F, -13.9294F, 1.9701F, 3.5F, 4.0F, 3.5F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(-0.6629F, -19.9294F, 1.7201F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-22.0F, 0.0F, 0.0F, 0.262F, -0.0421F, 0.3316F));

        PartDefinition cube_r6 = tusk.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 49).addBox(-3.0871F, -13.9294F, 1.9701F, 3.5F, 4.0F, 3.5F, new CubeDeformation(0.0F))
                .texOffs(0, 35).addBox(-3.3371F, -19.9294F, 1.7201F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.262F, 0.0421F, -0.3316F));

        PartDefinition cube_r7 = tusk.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(3, 58).addBox(-2.4455F, 10.0743F, 5.1595F, 2.3F, 9.0F, 2.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1495F, -0.0745F, -1.7162F, 1.7972F, 0.0745F, -0.3447F));

        PartDefinition cube_r8 = tusk.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(19, 48).addBox(-2.5455F, -4.1047F, 9.4424F, 2.5F, 8.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0085F, -0.0184F, -0.1914F, 1.1863F, 0.0745F, -0.3447F));

        PartDefinition cube_r9 = tusk.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(20, 59).addBox(0.1455F, 10.0743F, 5.1595F, 2.3F, 10.0F, 2.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.8505F, -0.0745F, -1.7162F, 1.7972F, -0.0745F, 0.3447F));

        PartDefinition cube_r10 = tusk.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(19, 48).addBox(0.0455F, -4.1047F, 9.4424F, 2.5F, 8.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.9915F, -0.0184F, -0.1914F, 1.1863F, -0.0745F, 0.3447F));

        PartDefinition slurf = head.addOrReplaceChild("slurf", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 11.0F));

        PartDefinition slurf_1 = slurf.addOrReplaceChild("slurf_1", CubeListBuilder.create().texOffs(112, 0).addBox(-2.0F, -0.5F, -2.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition lower_slurf = slurf_1.addOrReplaceChild("lower_slurf", CubeListBuilder.create(), PartPose.offset(0.0F, 6.0F, -0.5F));

        PartDefinition slurf_2 = lower_slurf.addOrReplaceChild("slurf_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -7.5F));

        PartDefinition cube_r11 = slurf_2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(112, 14).addBox(-1.75F, -2.0678F, 5.6001F, 3.5F, 8.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition slurf_3 = lower_slurf.addOrReplaceChild("slurf_3", CubeListBuilder.create(), PartPose.offset(0.0F, 7.0F, -1.0F));

        PartDefinition cube_r12 = slurf_3.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(118, 26).addBox(-1.25F, -6.2964F, 4.2506F, 2.5F, 4.0F, 2.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -8.5F, -0.8901F, 0.0F, 0.0F));

        PartDefinition legs = mammoth.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(3.0F, 8.0F, -1.0F));

        PartDefinition legs_front = legs.addOrReplaceChild("legs_front", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 9.0F));

        PartDefinition leg_front_right = legs_front.addOrReplaceChild("leg_front_right", CubeListBuilder.create().texOffs(45, 8).addBox(-3.0F, -4.0F, -3.0F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.0F, 1.0F));

        PartDefinition leg_front_left = legs_front.addOrReplaceChild("leg_front_left", CubeListBuilder.create().texOffs(24, 8).addBox(-2.0F, -5.0F, -2.0F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 2.0F, 0.0F));

        PartDefinition legs_back = legs.addOrReplaceChild("legs_back", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, -9.0F));

        PartDefinition leg_back_right = legs_back.addOrReplaceChild("leg_back_right", CubeListBuilder.create().texOffs(3, 8).addBox(-2.0F, -5.0F, -3.0F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 1.0F, 0.0F));

        PartDefinition leg_back_left = legs_back.addOrReplaceChild("leg_back_left", CubeListBuilder.create().texOffs(66, 8).addBox(-3.0F, -5.0F, -3.0F, 5.0F, 13.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 1.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    /**
     * The setupAnim method is responsible for setting up animations for the MammothModel during rendering.
     *
     * @param entity          The entity being rendered.
     * @param limbSwing       The limb swing progress for walking animations.
     * @param limbSwingAmount The limb swing amount for walking animations.
     * @param ageInTicks      The age of the entity in ticks.
     * @param netHeadYaw      The yaw rotation of the entity's head relative to the body.
     * @param headPitch       The pitch rotation of the entity's head relative to the body.
     */

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Reset the pose for all parts of the model to their default positions
        this.root().getAllParts().forEach(ModelPart::resetPose);
        // Apply head rotation based on yaw, pitch, and age

        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        // Animate the walk using the Mammoth_WALK animation definition
        this.animateWalk(ModAnimationDefinitions.MAMMOTH_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

        // Animate the idle state using the Mammoth_IDLE animation definition
        this.animate(((MammothEntity) entity).idleAnimationState, ModAnimationDefinitions.MAMMOTH_IDLE, ageInTicks, 1f);

        // Animate the attack state using the Mammoth_ATTACK animation definition
        this.animate(((MammothEntity) entity).attackAnimationState, ModAnimationDefinitions.MAMMOTH_ATTACK, ageInTicks, 1f);

    }

    /**
     * Applies the head rotation to the model based on the specified yaw, pitch, and age.
     *
     * @param pNetHeadYaw The yaw rotation of the entity's head relative to the body.
     * @param pHeadPitch  The pitch rotation of the entity's head relative to the body.
     * @param pAgeInTicks The age of the entity in ticks.
     */
    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        // Clamp the yaw and pitch values to a specified range
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        // Apply the yaw and pitch rotations to the head
        this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);
        this.head.xRot = pHeadPitch * ((float) Math.PI / 180F);
    }

    /**
     * Renders the MammothModel to the specified VertexConsumer.
     *
     * @param poseStack      The PoseStack for managing transformations during rendering.
     * @param vertexConsumer The VertexConsumer for recording geometry.
     * @param packedLight    The packed light value.
     * @param packedOverlay  The packed overlay value.
     * @param red            Red color factor.
     * @param green          Green color factor.
     * @param blue           Blue color factor.
     * @param alpha          Alpha (transparency) factor.
     */

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        // Render the Mammoth model using the specified parameters
        mammoth.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    /**
     * Returns the root ModelPart of the MammothModel.
     *
     * @return The root ModelPart.
     */

    @Override
    public ModelPart root() {
        return mammoth;
    }
}