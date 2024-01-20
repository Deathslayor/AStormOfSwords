package net.astormofsorts.agotmod.entity.client;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class BearModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "bear"), "main");
	private final ModelPart bear;

	public BearModel(ModelPart root) {
		this.bear = root.getChild("bear");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bear = partdefinition.addOrReplaceChild("bear", CubeListBuilder.create(), PartPose.offset(-0.5F, 18.0F, -6.0F));

		PartDefinition head = bear.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -2.0F));

		PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, 1).addBox(-1.0F, -2.0F, -1.0F, 3.25F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -6.0F, 4.0F, 0.0886F, 0.1739F, 0.0154F));

		PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 1).addBox(-1.0F, -2.0F, -1.0F, 3.25F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -6.0F, 4.0F, 0.0886F, -0.1739F, -0.0154F));

		PartDefinition Neck_r1 = head.addOrReplaceChild("Neck_r1", CubeListBuilder.create().texOffs(0, 7).addBox(-3.6F, -7.1F, 0.0F, 8.45F, 7.95F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition cube_r3 = head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 5).addBox(-3.35F, -6.1F, 0.0F, 7.95F, 6.7F, 5.25F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r4 = head.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 1).addBox(0.0F, -4.1F, -4.15F, 1.25F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 5).addBox(-1.25F, -3.75F, -5.0F, 3.75F, 2.75F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.35F, 1.0F, 0.1745F, 0.0F, 0.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(0, 4).addBox(-1.0F, -1.25F, -3.75F, 3.25F, 1.25F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition chest = bear.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(0, 7).addBox(-4.1F, -15.4667F, 10.5941F, 9.45F, 9.2F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.75F, 8.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r6 = chest.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-4.85F, -7.6229F, 10.4772F, 0.5F, 9.7F, 5.5F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 7).addBox(5.6F, -7.6229F, 10.4772F, 0.5F, 9.7F, 5.5F, new CubeDeformation(0.0F))
		.texOffs(0, 7).addBox(-4.35F, -7.6229F, 6.9772F, 9.95F, 9.7F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -4.0F, 0.1309F, 0.0F, 0.0F));

		PartDefinition cube_r7 = chest.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 7).addBox(-4.6F, -7.6F, 0.0F, 10.45F, 9.2F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -4.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition legs = bear.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -2.25F, 4.0F));

		PartDefinition frontleft = legs.addOrReplaceChild("frontleft", CubeListBuilder.create().texOffs(0, 7).addBox(-5.1F, 2.4F, 1.4F, 3.2F, 1.45F, 5.5F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 4.0F, 0.0F));

		PartDefinition cube_r8 = frontleft.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(0, 7).addBox(-5.35F, -4.6F, 0.5F, 3.7F, 4.95F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 2.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r9 = frontleft.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 7).addBox(-5.85F, -7.6F, 0.5F, 4.7F, 7.2F, 5.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition frontright = legs.addOrReplaceChild("frontright", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(3.15F, 2.4F, 1.4F, 3.2F, 1.45F, 5.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.5F, 4.0F, 0.0F));

		PartDefinition cube_r10 = frontright.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(2.9F, -4.6F, 0.5F, 3.7F, 4.95F, 4.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 2.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r11 = frontright.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(2.4F, -7.6F, 0.5F, 4.7F, 7.2F, 5.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition backleft = legs.addOrReplaceChild("backleft", CubeListBuilder.create().texOffs(0, 7).addBox(2.4F, 6.4F, 16.15F, 3.2F, 1.45F, 5.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r12 = backleft.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 7).addBox(-5.35F, -5.6F, 0.5F, 3.7F, 5.95F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 7.0F, 17.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r13 = backleft.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 7).addBox(-5.85F, -7.6F, 0.5F, 4.7F, 8.2F, 5.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.5F, 1.75F, 17.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition backright = legs.addOrReplaceChild("backright", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-5.6F, 6.4F, 16.15F, 3.2F, 1.45F, 5.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition cube_r14 = backright.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(1.65F, -5.6F, 0.5F, 3.7F, 5.95F, 4.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 7.0F, 17.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition cube_r15 = backright.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(1.15F, -7.6F, 0.5F, 4.7F, 8.2F, 5.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-7.5F, 1.75F, 17.0F, -0.0436F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}