// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.util.ModTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.neoforged.neoforge.common.SimpleTier;

// A utility class for defining custom tool tiers
public class ModToolTiers {
    // Custom tool tier: DRAGONGLASS
    public static final Tier DRAGONGLASS = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_DRAGONGLASS_TOOL, 2031, 9.0F, 4.0F, 15, () -> Ingredient.of(ModTags.Items.DRAGONGLASS_TOOL_MATERIALS));
    public static final Tier BRONZE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_BRONZE_TOOL, 170, 6.0F, 2.0F, 14, () -> Ingredient.of(ModTags.Items.BRONZE_TOOL_MATERIALS));
    public static final Tier STEEL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL, 780, 6.0F, 2.0F, 14, () -> Ingredient.of(ModTags.Items.STEEL_TOOL_MATERIALS));
    public static final Tier NOBLE = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL, 4062, 7.0F, 4.0F, 18, () -> Ingredient.of(ModTags.Items.STEEL_TOOL_MATERIALS));
}



