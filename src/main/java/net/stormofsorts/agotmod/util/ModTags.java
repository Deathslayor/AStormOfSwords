package net.stormofsorts.agotmod.util;

import net.stormofsorts.agotmod.AGoTMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_BRONZE_TOOL = tag("needs_bronze_tool");


        // Creates BLOCK TAGS
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(AGoTMod.MOD_ID, name));
        }

    }

    public static class Items {


        // Creates ITEM TAGS
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(AGoTMod.MOD_ID, name));
        }
    }
}
