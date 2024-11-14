// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.util.ModTags;
import net.minecraft.world.item.ToolMaterial;

// A utility class for defining custom tool tiers
public class ModToolTiers {
    // Custom tool tier: DRAGONGLASS
    public static final ToolMaterial DRAGONGLASS = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_DRAGONGLASS_TOOL, 2031, 9.0F, 4.0F, 15, ModTags.Items.DRAGONGLASS_TOOL_MATERIALS);
    public static final ToolMaterial BRONZE = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_BRONZE_TOOL, 170, 6.0F, 2.0F, 14, ModTags.Items.BRONZE_TOOL_MATERIALS);
    public static final ToolMaterial STEEL = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL, 780, 6.0F, 2.0F, 14, ModTags.Items.STEEL_TOOL_MATERIALS);
    public static final ToolMaterial NOBLE = new ToolMaterial(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL, 4062, 7.0F, 4.0F, 18, ModTags.Items.STEEL_TOOL_MATERIALS);
}
