package net.darkflameproduction.agotmod.util;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {


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
