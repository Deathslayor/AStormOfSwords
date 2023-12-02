package net.stormofsorts.agotmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.stormofsorts.agotmod.entity.animations.ModAnimationDefinitions;
import net.stormofsorts.agotmod.entity.custom.RhinoEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

/**
 * The RhinoModel class represents the 3D model for the RhinoEntity in the client-side rendering.
 * It extends HierarchicalModel, providing a hierarchical structure for organizing model parts.
 *
 * @param <T> The type of entity associated with the model.
 */
public class RhinoModel<T extends Entity> extends HierarchicalModel<T> {
	// The root model part representing the entire rhino model
	private final ModelPart rhino;

	// The model part representing the head of the rhino
	private final ModelPart head;

	/**
	 * Constructor for RhinoModel.
	 *
	 * @param root The root model part of the rhino model.
	 */
	public RhinoModel(ModelPart root) {
		// Get references to the rhino and head model parts from the provided root
		this.rhino = root.getChild("rhino");
		this.head = rhino.getChild("body").getChild("torso").getChild("head");
	}

	/**
	 * Creates a LayerDefinition for a rhino model.
	 * The method defines the mesh structure using CubeListBuilder and PartDefinition,
	 * setting the position, size, and textures for each part of the rhino.
	 *
	 * @return The LayerDefinition for the rhino model with a specified texture size.
	 */
	public static LayerDefinition createBodyLayer() {
		// Create a new MeshDefinition for defining the model's mesh structure
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		// Create the main body part named "rhino" with an initial pose
		PartDefinition rhino = partdefinition.addOrReplaceChild("rhino", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 2.5F));

		// Add or replace child parts for torso, head, skull, horns, ears, eyes, eyelids, tail, and legs
		PartDefinition body = rhino.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -38.0F, -26.0F, 20.0F, 24.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 41).addBox(-9.0F, -37.0F, -10.0F, 18.0F, 25.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(53, 67).addBox(-7.0F, -37.0F, 6.0F, 14.0F, 21.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -21.0F, -26.0F));
		PartDefinition skull = head.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(69, 29).addBox(-6.0F, -9.0F, -12.0F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-1.0F, -1.0F, -15.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition horn = skull.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(0, 102).addBox(-5.0F, -5.0F, -9.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 8.5F, -11.0F, -0.1745F, 0.0F, 0.0F));
		PartDefinition horn2 = horn.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(103, 15).addBox(-3.0F, -3.0F, -9.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -9.0F, -0.4363F, 0.0F, 0.0F));
		PartDefinition left_ear = skull.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -8.0F, -9.5F));
		PartDefinition right_ear = skull.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.0F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -8.0F, -9.5F));
		PartDefinition left_eye = skull.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(55, 46).addBox(-0.45F, -0.4F, -0.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(57, 46).addBox(-0.55F, -1.6F, -1.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8F, 5.1F, -9.4F, 0.2618F, 0.0F, 0.0F));
		PartDefinition left_eyelid = skull.addOrReplaceChild("left_eyelid", CubeListBuilder.create().texOffs(42, 85).addBox(-0.55F, -2.1F, -1.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.05F, 5.1F, -9.4F));
		PartDefinition right_eyelid = skull.addOrReplaceChild("right_eyelid", CubeListBuilder.create().texOffs(42, 85).mirror().addBox(-0.45F, -2.1F, -1.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.05F, 5.1F, -9.4F));
		PartDefinition right_eye = skull.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(55, 46).mirror().addBox(-0.55F, -0.4F, -0.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(57, 46).mirror().addBox(-0.45F, -1.6F, -1.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.8F, 5.1F, -9.4F, 0.2618F, 0.0F, 0.0F));
		PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 88).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 13.0F, new CubeDeformation(0.0F))
				.texOffs(57, 0).addBox(-2.5F, 0.0F, 13.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 22.0F, -1.309F, 0.0F, 0.0F));
		PartDefinition left_back_leg = body.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(73, 0).addBox(-4.5F, -4.5F, -5.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -28.5F, 15.5F));
		PartDefinition left_back_knee = left_back_leg.addOrReplaceChild("left_back_knee", CubeListBuilder.create().texOffs(98, 62).addBox(-3.5F, 0.0F, -1.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, -3.0F));
		PartDefinition left_back_heel = left_back_knee.addOrReplaceChild("left_back_heel", CubeListBuilder.create().texOffs(54, 105).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -0.5F));
		PartDefinition right_back_leg = body.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(73, 0).mirror().addBox(-4.5F, -4.5F, -5.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -28.5F, 15.5F));
		PartDefinition right_back_knee = right_back_leg.addOrReplaceChild("right_back_knee", CubeListBuilder.create().texOffs(98, 62).mirror().addBox(-3.5F, 0.0F, -1.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 8.5F, -3.0F));
		PartDefinition right_back_heel = right_back_knee.addOrReplaceChild("right_back_heel", CubeListBuilder.create().texOffs(54, 105).mirror().addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, -0.5F));
		PartDefinition right_front_leg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(100, 111).mirror().addBox(-3.5F, -3.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -17.0F, -20.5F));
		PartDefinition right_front_knee = right_front_leg.addOrReplaceChild("right_front_knee", CubeListBuilder.create().texOffs(54, 105).mirror().addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 7.0F, -2.5F));
		PartDefinition left_front_leg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(100, 111).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -17.0F, -20.5F));
		PartDefinition left_front_knee = left_front_leg.addOrReplaceChild("left_front_knee", CubeListBuilder.create().texOffs(54, 105).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -2.5F));

		// Return a LayerDefinition created from the meshdefinition with a texture size of 128x128
		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	/**
	 * The setupAnim method is responsible for setting up animations for the RhinoModel during rendering.
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

		// Animate the walk using the RHINO_WALK animation definition
		this.animateWalk(ModAnimationDefinitions.RHINO_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

		// Animate the idle state using the RHINO_IDLE animation definition
		this.animate(((RhinoEntity) entity).idleAnimationState, ModAnimationDefinitions.RHINO_IDLE, ageInTicks, 1f);

		// Animate the attack state using the RHINO_ATTACK animation definition
		this.animate(((RhinoEntity) entity).attackAnimationState, ModAnimationDefinitions.RHINO_ATTACK, ageInTicks, 1f);
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
	 * Renders the RhinoModel to the specified VertexConsumer.
	 *
	 * @param poseStack       The PoseStack for managing transformations during rendering.
	 * @param vertexConsumer  The VertexConsumer for recording geometry.
	 * @param packedLight     The packed light value.
	 * @param packedOverlay   The packed overlay value.
	 * @param red             Red color factor.
	 * @param green           Green color factor.
	 * @param blue            Blue color factor.
	 * @param alpha           Alpha (transparency) factor.
	 */
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		// Render the rhino model using the specified parameters
		rhino.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	/**
	 * Returns the root ModelPart of the RhinoModel.
	 *
	 * @return The root ModelPart.
	 */
	@Override
	public ModelPart root() {
		return rhino;
	}
}