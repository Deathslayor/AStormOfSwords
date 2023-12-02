// This code belongs to the package net.stormofsorts.agotmod.util
package net.stormofsorts.agotmod.util;

// Importing necessary classes from other packages
import net.stormofsorts.agotmod.AGoTMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

// A utility class for defining custom tags for blocks and items
public class ModTags {

    // Custom tags for blocks
    public static class Blocks {
        // Tag for blocks that require BRONZE tools
        public static final TagKey<Block> NEEDS_BRONZE_TOOL = tag("needs_bronze_tool");
        // Tag for blocks that require STEEL tools
        public static final TagKey<Block> NEEDS_STEEL_TOOL = tag("needs_steel_tool");

        // Helper method to create a block tag
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(AGoTMod.MOD_ID, name));
        }
    }

    // Custom tags for items
    public static class Items {
        // Helper method to create an item tag
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(AGoTMod.MOD_ID, name));
        }
    }
}
