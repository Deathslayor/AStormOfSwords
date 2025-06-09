// This code belongs to the package net.stormofsorts.agotmod.util
package net.darkflameproduction.agotmod.util;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

// A utility class for defining custom tags for blocks and items
public class ModTags {

    // Custom tags for blocks
    public static class Blocks {
        public static final TagKey<Block> INCORRECT_FOR_DRAGONGLASS_TOOL = tag("incorrect_for_dragonglass_tool");
        public static final TagKey<Block> INCORRECT_FOR_BRONZE_TOOL = tag("incorrect_for_bronze_tool");
        public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = tag("incorrect_for_steel_tool");
        // Tag for blocks that require BRONZE tools
        public static final TagKey<Block> NEEDS_BRONZE_TOOL = tag("needs_bronze_tool");
        // Tag for blocks that require STEEL tools
        public static final TagKey<Block> NEEDS_STEEL_TOOL = tag("needs_steel_tool");
        public static final TagKey<Block> NEEDS_DRAGONGLASS_TOOL = tag("needs_dragonglass_tool");

        // Helper method to create a block tag
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(AGoTMod.id(name));
        }
    }

    // Custom tags for items
    public static class Items {
        public static final TagKey<Item> DRAGONGLASS_TOOL_MATERIALS = tag("dragonglass_tool_materials");
        public static final TagKey<Item> BRONZE_TOOL_MATERIALS = tag("bronze_tool_materials");
        public static final TagKey<Item> STEEL_TOOL_MATERIALS = tag("steel_tool_materials");

        // Helper method to create an item tag
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(AGoTMod.id(name));
        }
    }
}
