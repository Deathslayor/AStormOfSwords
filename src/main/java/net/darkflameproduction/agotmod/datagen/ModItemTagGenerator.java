package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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
                //Weirwood
                .add(ModBLocks.WEIRWOOD_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_WEIRWOOD_LOG.get().asItem())
                .add(ModBLocks.WEIRWOOD_WOOD.get().asItem())
                .add(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get().asItem())
                //Sycamore
                .add(ModBLocks.SYCAMORE_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_SYCAMORE_LOG.get().asItem())
                .add(ModBLocks.SYCAMORE_WOOD.get().asItem())
                .add(ModBLocks.STRIPPED_SYCAMORE_WOOD.get().asItem())
                //Sentinel
                .add(ModBLocks.STRIPPED_SENTINEL_WOOD.get().asItem())
                .add(ModBLocks.SENTINEL_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_SENTINEL_LOG.get().asItem())
                .add(ModBLocks.SENTINEL_WOOD.get().asItem())
                //Pine
                .add(ModBLocks.STRIPPED_PINE_WOOD.get().asItem())
                .add(ModBLocks.PINE_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_PINE_LOG.get().asItem())
                .add(ModBLocks.PINE_WOOD.get().asItem())
                //Ironwood
                .add(ModBLocks.STRIPPED_IRONWOOD_WOOD.get().asItem())
                .add(ModBLocks.IRONWOOD_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_IRONWOOD_LOG.get().asItem())
                .add(ModBLocks.IRONWOOD_WOOD.get().asItem())
                //Hawthorn
                .add(ModBLocks.STRIPPED_HAWTHORN_WOOD.get().asItem())
                .add(ModBLocks.HAWTHORN_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_HAWTHORN_LOG.get().asItem())
                .add(ModBLocks.HAWTHORN_WOOD.get().asItem())
                //Chestnut
                .add(ModBLocks.STRIPPED_CHESTNUT_WOOD.get().asItem())
                .add(ModBLocks.CHESTNUT_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_CHESTNUT_LOG.get().asItem())
                .add(ModBLocks.CHESTNUT_WOOD.get().asItem())
                //Cedar
                .add(ModBLocks.STRIPPED_CEDAR_WOOD.get().asItem())
                .add(ModBLocks.CEDAR_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_CEDAR_LOG.get().asItem())
                .add(ModBLocks.CEDAR_WOOD.get().asItem())
                //beech
                .add(ModBLocks.STRIPPED_BEECH_WOOD.get().asItem())
                .add(ModBLocks.BEECH_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_BEECH_LOG.get().asItem())
                .add(ModBLocks.BEECH_WOOD.get().asItem())
                //ash
                .add(ModBLocks.STRIPPED_ASH_WOOD.get().asItem())
                .add(ModBLocks.ASH_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_ASH_LOG.get().asItem())
                .add(ModBLocks.ASH_WOOD.get().asItem())
                //Blackbark
                .add(ModBLocks.STRIPPED_BLACKBARK_WOOD.get().asItem())
                .add(ModBLocks.BLACKBARK_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_BLACKBARK_LOG.get().asItem())
                .add(ModBLocks.BLACKBARK_WOOD.get().asItem())
                //Aspen
                .add(ModBLocks.STRIPPED_ASPEN_WOOD.get().asItem())
                .add(ModBLocks.ASPEN_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_ASPEN_LOG.get().asItem())
                .add(ModBLocks.ASPEN_WOOD.get().asItem())
                //Aspen
                .add(ModBLocks.STRIPPED_ALDER_WOOD.get().asItem())
                .add(ModBLocks.ALDER_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_ALDER_LOG.get().asItem())
                .add(ModBLocks.ALDER_WOOD.get().asItem())


        ;

        this.tag(ItemTags.PLANKS)
                .add(ModBLocks.WEIRWOOD_PLANKS.get().asItem())
                .add(ModBLocks.SYCAMORE_PLANKS.get().asItem())
                .add(ModBLocks.SENTINEL_PLANKS.get().asItem())
                .add(ModBLocks.PINE_PLANKS.get().asItem())
                .add(ModBLocks.IRONWOOD_PLANKS.get().asItem())
                .add(ModBLocks.HAWTHORN_PLANKS.get().asItem())
                .add(ModBLocks.CHESTNUT_PLANKS.get().asItem())
                .add(ModBLocks.CEDAR_PLANKS.get().asItem())
                .add(ModBLocks.BEECH_PLANKS.get().asItem())
                .add(ModBLocks.ASH_PLANKS.get().asItem())
                .add(ModBLocks.BLACKBARK_PLANKS.get().asItem())
                .add(ModBLocks.ASPEN_PLANKS.get().asItem())


        ;
    }
}
