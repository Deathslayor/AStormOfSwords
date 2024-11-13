package net.darkflameproduction.agotmod.entity.client.norththewall;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import org.jetbrains.annotations.NotNull;

public class WightModel<T extends WightEntityRenderState> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AGoTMod.id("wight"), "main");

    protected WightModel(@NotNull ModelPart part) {
        super(part.getChild("skin"));
    }

    public static @NotNull LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition skin = partdefinition.addOrReplaceChild("skin", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Head = skin.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(2, 2).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition Head_r1 = Head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(6, 6).addBox(-0.9F, -0.5F, 0.0F, 7.6F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -0.25F, -3.0F, 0.3491F, 0.0F, 0.0F));

        PartDefinition Body = skin.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(19, 19).addBox(-4.0F, 2.0F, 1.0F, 8.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(17, 17).addBox(0.0F, 5.0F, -2.0F, 4.0F, 3.5F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 17).addBox(-4.0F, 5.5F, -2.0F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 17).addBox(-4.0F, 2.0F, -2.0F, 0.25F, 3.5F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 17).addBox(3.75F, 2.0F, -2.0F, 0.25F, 3.5F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(20, 20).addBox(-4.0F, 8.0F, -1.75F, 8.0F, 3.5F, 1.75F, new CubeDeformation(0.0F))
                .texOffs(16, 16).addBox(-4.0F, 11.0F, -2.0F, 8.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(17, 17).addBox(-4.0F, 8.5F, -2.0F, 0.25F, 2.5F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(17, 17).addBox(3.75F, 8.5F, -2.0F, 0.25F, 2.5F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, 0.0F));

        PartDefinition cube_r1 = Body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(9, 49).addBox(-1.0F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 5.75F, -1.25F, -0.6666F, 0.7563F, -0.2328F));

        PartDefinition cube_r2 = Body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(14, 7).mirror().addBox(-0.5F, -2.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.5F, -1.5F, 0.0F, -0.5236F, 0.0F));

        PartDefinition cube_r3 = Body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(51, 17).addBox(-2.75F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 4.5F, -1.25F, -0.2054F, -0.5383F, 0.2575F));

        PartDefinition cube_r4 = Body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(51, 49).addBox(-1.0F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 4.5F, -1.25F, 0.0798F, 0.3788F, 0.371F));

        PartDefinition cube_r5 = Body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(51, 48).addBox(-2.75F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 3.5F, -1.25F, -0.0097F, -0.218F, 0.0447F));

        PartDefinition cube_r6 = Body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(50, 12).addBox(-1.0F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 3.5F, -1.25F, 0.3562F, 0.1589F, 0.2568F));

        PartDefinition cube_r7 = Body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(50, 8).addBox(-2.75F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 1.5F, -1.25F, 0.0204F, -0.3918F, -0.093F));

        PartDefinition cube_r8 = Body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(49, 39).mirror().addBox(-1.0F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 1.5F, -1.25F, -0.3102F, 0.5954F, -0.1199F));

        PartDefinition cube_r9 = Body.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(46, 36).addBox(-1.0F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 2.5F, -1.25F, 0.2375F, 0.2173F, 0.0894F));

        PartDefinition cube_r10 = Body.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(7, 33).mirror().addBox(-2.75F, -0.25F, 0.0F, 3.75F, 0.75F, 0.5F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.0F, 2.5F, -1.25F, 0.0204F, -0.3918F, -0.093F));

        PartDefinition RightArm = skin.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(41, 17).addBox(-2.75F, 3.0F, -1.75F, 3.5F, 7.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -22.0F, 0.0F));

        PartDefinition LeftArm = skin.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(41, 17).addBox(-0.75F, 7.0F, -1.75F, 3.5F, 3.0F, 3.5F, new CubeDeformation(0.0F))
                .texOffs(42, 18).addBox(-0.25F, 3.0F, -1.25F, 2.5F, 4.0F, 2.5F, new CubeDeformation(0.0F))
                .texOffs(41, 17).addBox(-0.75F, 3.0F, -1.75F, 3.5F, 2.0F, 3.5F, new CubeDeformation(0.0F))
                .texOffs(42, 18).addBox(-0.75F, 5.0F, -1.75F, 1.75F, 2.0F, 2.75F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -22.0F, 0.0F));

        PartDefinition RightLeg = skin.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(1, 17).addBox(-1.75F, 4.0F, -1.75F, 3.5F, 3.0F, 3.5F, new CubeDeformation(0.0F))
                .texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(2, 18).addBox(-1.25F, 7.0F, -1.25F, 2.25F, 1.25F, 2.25F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-2.0F, 10.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(1, 17).addBox(-1.75F, 8.25F, -1.75F, 3.5F, 1.75F, 3.5F, new CubeDeformation(0.0F))
                .texOffs(1, 17).addBox(0.5F, 7.0F, -1.75F, 1.25F, 1.25F, 3.5F, new CubeDeformation(0.0F))
                .texOffs(3, 19).addBox(-1.75F, 7.0F, -0.5F, 0.75F, 1.25F, 2.25F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, -12.0F, 0.0F));

        PartDefinition LeftLeg = skin.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 2.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F))
                .texOffs(17, 49).addBox(-1.75F, 0.0F, -1.75F, 3.5F, 2.0F, 3.5F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, -12.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(@NotNull T state) {
        super.animate(state.emerginState, ModAnimationDefinitions.WIGHT_EMERGING, state.ageInTicks, 1f);
    }
}