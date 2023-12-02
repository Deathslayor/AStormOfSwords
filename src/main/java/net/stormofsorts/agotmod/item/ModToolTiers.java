// This code belongs to the package net.stormofsorts.agotmod.item
package net.stormofsorts.agotmod.item;

// Importing necessary classes from other packages
import net.stormofsorts.agotmod.AGoTMod;
import net.stormofsorts.agotmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

// A utility class for defining custom tool tiers
public class ModToolTiers {

    // Custom tool tier: BRONZE
    public static final Tier BRONZE = TierSortingRegistry.registerTier(
            // ForgeTier constructor parameters:
            new ForgeTier(2, 170, 7F, 2.5F, 14,
                    // Custom block tag for tools requiring BRONZE
                    ModTags.Blocks.NEEDS_BRONZE_TOOL,
                    // Ingredient for crafting tools with BRONZE
                    () -> Ingredient.of(ModItems.BRONZE_INGOT.get())),
            // Resource location for the custom tier
            new ResourceLocation(AGoTMod.MOD_ID, "bronze_ingot"),
            // List of upgrade tiers (e.g., Iron)
            List.of(Tiers.IRON),
            // List of downgrade tiers (e.g., no downgrade tiers for BRONZE)
            List.of());

    // Custom tool tier: STEEL
    public static final Tier STEEL = TierSortingRegistry.registerTier(
            // ForgeTier constructor parameters:
            new ForgeTier(4, 2031, 9.0F, 4.0F, 15,
                    // Custom block tag for tools requiring STEEL
                    ModTags.Blocks.NEEDS_STEEL_TOOL,
                    // Ingredient for crafting tools with STEEL
                    () -> Ingredient.of(ModItems.STEEL_INGOT.get())),
            // Resource location for the custom tier
            new ResourceLocation(AGoTMod.MOD_ID, "steel_ingot"),
            // List of upgrade tiers (e.g., Netherite)
            List.of(Tiers.NETHERITE),
            // List of downgrade tiers (e.g., no downgrade tiers for STEEL)
            List.of());

}
