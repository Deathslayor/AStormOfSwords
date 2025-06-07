package net.darkflameproduction.agotmod.entity.client.npc;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class Peasant_Model extends GeoModel<Peasant_Entity> {

    // Model files
    private static final ResourceLocation MALE_ADULT_MODEL =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "geo/entity/peasant.geo.json");
    private static final ResourceLocation FEMALE_ADULT_MODEL =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "geo/entity/peasant_woman.geo.json");
    private static final ResourceLocation CHILD_MODEL =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "geo/entity/peasant_child.geo.json");

    // Animation resources
    private static final ResourceLocation ADULT_ANIMATIONS =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "animations/entity/peasant.animation.json");
    private static final ResourceLocation CHILD_ANIMATIONS =
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "animations/entity/peasant_child.animation.json");

    @Override
    public ResourceLocation getModelResource(Peasant_Entity animatable, GeoRenderer<Peasant_Entity> renderer) {
        // Debug logging
        String age = animatable.getAge();
        String gender = animatable.getGender();
        boolean isChild = animatable.isChild();

        ResourceLocation selectedModel;

        // Return appropriate model based on age and gender
        if (animatable.isChild()) {
            selectedModel = CHILD_MODEL;
            System.out.println("DEBUG - Using CHILD_MODEL: " + selectedModel.toString());
        } else {
            // Adults use gender-specific models
            if (animatable.isFemale()) {
                selectedModel = FEMALE_ADULT_MODEL;
                System.out.println("DEBUG - Using FEMALE_ADULT_MODEL: " + selectedModel.toString());
            } else {
                selectedModel = MALE_ADULT_MODEL;
                System.out.println("DEBUG - Using MALE_ADULT_MODEL: " + selectedModel.toString());
            }
        }

        return selectedModel;
    }

    @Override
    public ResourceLocation getTextureResource(Peasant_Entity animatable, GeoRenderer<Peasant_Entity> renderer) {
        // This will be handled by the renderer for variants
        // Return a default based on age and gender for fallback purposes
        if (animatable.isChild()) {
            if (animatable.isFemale()) {
                return ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                        "textures/entity/northernpeasant/body_woman_child1.png");
            } else {
                return ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                        "textures/entity/northernpeasant/body_child1.png");
            }
        } else {
            // Adult textures
            if (animatable.isFemale()) {
                return ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                        "textures/entity/northernpeasant/body_woman1.png");
            } else {
                return ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID,
                        "textures/entity/northernpeasant/body1.png");
            }
        }
    }

    @Override
    public ResourceLocation getAnimationResource(Peasant_Entity animatable) {
        // Use different animations based on age
        if (animatable.isChild()) {
            System.out.println("DEBUG - Using CHILD_ANIMATIONS");
            return CHILD_ANIMATIONS;
        } else {
            System.out.println("DEBUG - Using ADULT_ANIMATIONS");
            return ADULT_ANIMATIONS;
        }
    }
}