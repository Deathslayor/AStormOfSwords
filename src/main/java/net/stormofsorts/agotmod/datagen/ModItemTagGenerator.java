package net.stormofsorts.agotmod.datagen;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.stormofsorts.agotmod.AGoTMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.stormofsorts.agotmod.block.ModBLocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    // Constructor for ModItemTagGenerator
    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        // Call the constructor of the superclass (ItemTagsProvider)
        super(pOutput, pLookupProvider, pBlockTags, AGoTMod.MOD_ID, existingFileHelper);
    }

    // Method to add tags to items
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        // This method is where you would add tags to items
        // Tags are used for grouping items together for various purposes
        // You can add tags based on your mod's requirements
        // For example:
        // this.tag(ModTags.Items.MY_CUSTOM_TAG).add(ModItems.MY_ITEM.get());
        // This would create a custom tag for your item
        // and include your item in that tag

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBLocks.SYCAMORE_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_SYCAMORE_LOG.get().asItem())
                .add(ModBLocks.SYCAMORE_WOOD.get().asItem())
                .add(ModBLocks.STRIPPED_SYCAMORE_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBLocks.SYCAMORE_PLANKS.get().asItem());
    }
}
