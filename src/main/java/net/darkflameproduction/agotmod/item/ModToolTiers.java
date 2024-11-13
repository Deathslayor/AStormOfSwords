// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.util.ModTags;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

// A utility class for defining custom tool tiers
public class ModToolTiers {
    // Custom tool tier: DRAGONGLASS
    public static final ToolMaterial DRAGONGLASS = TierSortingRegistry.registerTier(
            // ForgeTier constructor parameters:
            new ToolMaterial(1, 170, 1.6F, 3F, 14,
                    // Ingredient for crafting tools with BRONZE
                    () -> Ingredient.of(ModItems.BRONZE_INGOT.get())),
            // Resource location for the custom tier
            AGoTMod.id("dragonglass"),
            // List of upgrade tiers (e.g., Iron)
            List.of(Tiers.IRON),
            // List of downgrade tiers (e.g., no downgrade tiers for BRONZE)
            List.of());

    // Custom tool tier: BRONZE
    public static final Tier BRONZE = TierSortingRegistry.registerTier(
            // ForgeTier constructor parameters:
            new ForgeTier(2, 170, 1.6F, 3.5F, 14,
                    // Custom block tag for tools requiring BRONZE
                    ModTags.Blocks.NEEDS_BRONZE_TOOL,
                    // Ingredient for crafting tools with BRONZE
                    () -> Ingredient.of(ModItems.BRONZE_INGOT.get())),
            // Resource location for the custom tier
            AGoTMod.id("bronze_ingot"),
            // List of upgrade tiers (e.g., Iron)
            List.of(Tiers.IRON),
            // List of downgrade tiers (e.g., no downgrade tiers for BRONZE)
            List.of());

    // Custom tool tier: STEEL
    public static final Tier STEEL = TierSortingRegistry.registerTier(
            // ForgeTier constructor parameters:
            new ForgeTier(4, 2031, 1.6F, 5F, 15,
                    // Custom block tag for tools requiring STEEL
                    ModTags.Blocks.NEEDS_STEEL_TOOL,
                    // Ingredient for crafting tools with STEEL
                    () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
            // Resource location for the custom tier
            AGoTMod.id("steel_ingot"),
            // List of upgrade tiers (e.g., Netherite)
            List.of(Tiers.NETHERITE),
            // List of downgrade tiers (e.g., no downgrade tiers for STEEL)
            List.of());
    // Custom tool tier: NOBLE
    public static final Tier NOBLE = TierSortingRegistry.registerTier(
            // ForgeTier constructor parameters:
            new ForgeTier(4, 4062, 1.6F, 6F, 18,
                    // Custom block tag for tools requiring STEEL
                    ModTags.Blocks.NEEDS_STEEL_TOOL,
                    // Ingredient for crafting tools with STEEL
                    () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
            // Resource location for the custom tier
            AGoTMod.id("noble_ingot"),
            // List of upgrade tiers (e.g., Netherite)
            List.of(Tiers.NETHERITE),
            // List of downgrade tiers (e.g., no downgrade tiers for STEEL)
            List.of());
}
