package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;
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
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
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
                .add(ModBLocks.WEIRWOOD_FACE_LOG.get().asItem())
                ;
        this.tag(ItemTags.PLANKS)
                .add(ModBLocks.WEIRWOOD_PLANKS.get().asItem());

        this.tag(ItemTags.WOODEN_STAIRS)
                .add(ModBLocks.WEIRWOOD_STAIRS.get().asItem());
        this.tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBLocks.WEIRWOOD_BUTTON.get().asItem());
        this.tag(ItemTags.WOODEN_DOORS)
                .add(ModBLocks.WEIRWOOD_DOOR.get().asItem());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get().asItem());
        this.tag(ItemTags.WOODEN_SLABS)
                .add(ModBLocks.WEIRWOOD_SLAB.get().asItem());
        this.tag(ItemTags.WOODEN_FENCES)
                .add(ModBLocks.WEIRWOOD_FENCE.get().asItem())
                .add(ModBLocks.WEIRWOOD_FENCE_GATE.get().asItem())
                .add(ModBLocks.WEIRWOOD_WALL.get().asItem());
        this.tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBLocks.WEIRWOOD_TRAPDOOR.get().asItem());
        this.tag(ItemTags.LEAVES)
                .add(ModBLocks.WEIRWOOD_LEAVES.get().asItem());

        String[] woodTypes = {
                "sycamore",
                "pine",
                "ash",
                "beech",
                "cedar",
                "chestnut",
                "hawthorn",
                "ironwood",
                "sentinel",
                "blackbark",
                "aspen",
                "black_cherry",
                "black_olive",
                "crabapple",
                "olive",
                "white_cherry",
                "red_cherry",
                "fir",
                "willow",
                "wormtree",
                "alder"
        };




// LOGS_THAT_BURN tag
        TagsProvider.TagAppender<Item> logsThatBurnTag = this.tag(ItemTags.LOGS_THAT_BURN);
        for (String woodType : woodTypes) {
            addItemToTag(logsThatBurnTag, ModBLocks.LOGS.get(woodType));
            addItemToTag(logsThatBurnTag, ModBLocks.STRIPPED_LOGS.get(woodType));
            addItemToTag(logsThatBurnTag, ModBLocks.WOODS.get(woodType));
            addItemToTag(logsThatBurnTag, ModBLocks.STRIPPED_WOODS.get(woodType));
        }

// PLANKS tag
        TagsProvider.TagAppender<Item> planksTag = this.tag(ItemTags.PLANKS);
        for (String woodType : woodTypes) {
            addItemToTag(planksTag, ModBLocks.PLANKS.get(woodType));
        }

// WOODEN_STAIRS tag
        TagsProvider.TagAppender<Item> stairsTag = this.tag(ItemTags.WOODEN_STAIRS);
        for (String woodType : woodTypes) {
            addItemToTag(stairsTag, ModBLocks.STAIRS.get(woodType));
        }

// WOODEN_BUTTONS tag
        TagsProvider.TagAppender<Item> buttonsTag = this.tag(ItemTags.WOODEN_BUTTONS);
        for (String woodType : woodTypes) {
            addItemToTag(buttonsTag, ModBLocks.BUTTONS.get(woodType));
        }

// WOODEN_DOORS tag
        TagsProvider.TagAppender<Item> doorsTag = this.tag(ItemTags.WOODEN_DOORS);
        for (String woodType : woodTypes) {
            addItemToTag(doorsTag, ModBLocks.DOORS.get(woodType));
        }

// WOODEN_SLABS tag
        TagsProvider.TagAppender<Item> slabsTag = this.tag(ItemTags.WOODEN_SLABS);
        for (String woodType : woodTypes) {
            addItemToTag(slabsTag, ModBLocks.SLABS.get(woodType));
        }

// WOODEN_PRESSURE_PLATES tag
        TagsProvider.TagAppender<Item> pressurePlatesTag = this.tag(ItemTags.WOODEN_PRESSURE_PLATES);
        for (String woodType : woodTypes) {
            addItemToTag(pressurePlatesTag, ModBLocks.PRESSURE_PLATES.get(woodType));
        }

// WOODEN_FENCES tag (including fences, fence gates, and walls)
        TagsProvider.TagAppender<Item> fencesTag = this.tag(ItemTags.WOODEN_FENCES);
        for (String woodType : woodTypes) {
            addItemToTag(fencesTag, ModBLocks.FENCES.get(woodType));
            addItemToTag(fencesTag, ModBLocks.FENCE_GATES.get(woodType));
            addItemToTag(fencesTag, ModBLocks.WALLS.get(woodType));
        }

// WOODEN_TRAPDOORS tag
        TagsProvider.TagAppender<Item> trapdoorsTag = this.tag(ItemTags.WOODEN_TRAPDOORS);
        for (String woodType : woodTypes) {
            addItemToTag(trapdoorsTag, ModBLocks.TRAPDOORS.get(woodType));
        }

// LEAVES tag
        TagsProvider.TagAppender<Item> leavesTag = this.tag(ItemTags.LEAVES);
        for (String woodType : woodTypes) {
            if (ModBLocks.LEAVES.containsKey(woodType)) {
                addItemToTag(leavesTag, ModBLocks.LEAVES.get(woodType));
            }
        }

// Helper method to add a block's item form to an item tag


        this.tag(ItemTags.FLOWERS)
                .add(ModBLocks.WINTER_ROSE.get().asItem())
                .add(ModBLocks.WILD_RADISH.get().asItem())
                .add(ModBLocks.WHITE_ROSE.get().asItem())
                .add(ModBLocks.DUSKY_ROSE.get().asItem())
                .add(ModBLocks.ROSE.get().asItem())
                .add(ModBLocks.THORN_BUSH.get().asItem())
                .add(ModBLocks.THISTLE.get().asItem())
                .add(ModBLocks.TANSY.get().asItem())
                .add(ModBLocks.SPICEFLOWER.get().asItem())
                .add(ModBLocks.SEDGE.get().asItem())
                .add(ModBLocks.SAFFRON_CROCUS.get().asItem())
                .add(ModBLocks.POISON_KISSES.get().asItem())
                .add(ModBLocks.PENNYROYAL.get().asItem())
                .add(ModBLocks.OPIUM_POPPY_WILD.get().asItem())
                .add(ModBLocks.NIGHTSHADE.get().asItem())
                .add(ModBLocks.MOONBLOOM.get().asItem())
                .add(ModBLocks.LUNGWORT.get().asItem())
                .add(ModBLocks.LIVERWORT.get().asItem())
                .add(ModBLocks.LAVENDER.get().asItem())
                .add(ModBLocks.LADYS_LACE.get().asItem())
                .add(ModBLocks.GORSE.get().asItem())
                .add(ModBLocks.GOLDENROD.get().asItem())
                .add(ModBLocks.GOLDENCUP.get().asItem())
                .add(ModBLocks.GOATHEAD.get().asItem())
                .add(ModBLocks.GINGER.get().asItem())
                .add(ModBLocks.GILLYFLOWER.get().asItem())
                .add(ModBLocks.FROSTFIRE.get().asItem())
                .add(ModBLocks.FORGET_ME_NOT.get().asItem())
                .add(ModBLocks.EVENING_STAR.get().asItem())
                .add(ModBLocks.DRAGONS_BREATH.get().asItem())
                .add(ModBLocks.COLDSNAP.get().asItem())
                .add(ModBLocks.BLUE_ROSE.get().asItem())
                .add(ModBLocks.BLOODBLOOM.get().asItem())
                .add(ModBLocks.BLACK_LOTUS.get().asItem())
                .add(ModBLocks.BLUE_ROSE_BUSH.get().asItem())
                .add(ModBLocks.WHITE_ROSE_BUSH.get().asItem())
                .add(ModBLocks.DUSKY_ROSE_BUSH.get().asItem())
                .add(ModBLocks.WINTER_ROSE_BUSH.get().asItem())
                .add(ModBLocks.RED_ROSE_BUSH.get().asItem());

        this.tag(ItemTags.MEAT)
                .add(ModItems.RAW_BEAR_MEAT.get().asItem())
                .add(ModItems.COOKED_BEAR_MEAT.get().asItem())
                .add(ModItems.RAW_BLOOD_SAUSAGE.get().asItem())
                .add(ModItems.COOKED_BLOOD_SAUSAGE.get().asItem())
                .add(ModItems.RAW_WHITE_SAUSAGE.get().asItem())
                .add(ModItems.COOKED_WHITE_SAUSAGE.get().asItem())
                .add(ModItems.RAW_SAUSAGE.get().asItem())
                .add(ModItems.COOKED_SAUSAGE.get().asItem())
                .add(ModItems.COOKED_BACON.get().asItem())
                .add(ModItems.RAW_BACON.get().asItem())
                .add(ModItems.RAW_BOAR_VENISON.get().asItem())
                .add(ModItems.COOKED_BOAR_VENISON.get().asItem())
                .add(ModItems.RAW_CHICKEN_NUGGETS.get().asItem())
                .add(ModItems.COOKED_CHICKEN_NUGGETS.get().asItem())
                .add(ModItems.RAW_DEER_VENISON.get().asItem())
                .add(ModItems.COOKED_DEER_VENISON.get().asItem())
                .add(ModItems.RAW_GOAT_MEAT.get().asItem())
                .add(ModItems.COOKED_GOAT_MEAT.get().asItem())
                .add(ModItems.RAW_HARE_MEAT.get().asItem())
                .add(ModItems.COOKED_HARE_MEAT.get().asItem())
                .add(ModItems.RAW_HORSE_MEAT.get().asItem())
                .add(ModItems.COOKED_HORSE_MEAT.get().asItem())
                .add(ModItems.RAW_MAMMOTH_MEAT.get().asItem())
                .add(ModItems.COOKED_MAMMOTH_MEAT.get().asItem());


        this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES)
                // Wildling Fur
                .add(ModItems.WILDLING_FUR_HELMET.get().asItem())
                .add(ModItems.WILDLING_FUR_CHESTPLATE.get().asItem())
                .add(ModItems.WILDLING_FUR_LEGGINGS.get().asItem())
                .add(ModItems.WILDLING_FUR_BOOTS.get().asItem())

                // Wildling Leather
                .add(ModItems.WILDLING_LEATHER_HELMET.get().asItem())
                .add(ModItems.WILDLING_LEATHER_CHESTPLATE.get().asItem())
                .add(ModItems.WILDLING_LEATHER_LEGGINGS.get().asItem())
                .add(ModItems.WILDLING_LEATHER_BOOTS.get().asItem())

                // Wildling Chief
                .add(ModItems.WILDLING_CHIEF_HELMET.get().asItem())
                .add(ModItems.WILDLING_CHIEF_CHESTPLATE.get().asItem())
                .add(ModItems.WILDLING_CHIEF_LEGGINGS.get().asItem())
                .add(ModItems.WILDLING_CHIEF_BOOTS.get().asItem())

                // Thenn Levy
                .add(ModItems.THENN_LEVY_HELMET.get().asItem())
                .add(ModItems.THENN_LEVY_CHESTPLATE.get().asItem())
                .add(ModItems.THENN_LEVY_LEGGINGS.get().asItem())
                .add(ModItems.THENN_LEVY_BOOTS.get().asItem())

                // Thenn Plate
                .add(ModItems.THENN_PLATE_HELMET.get().asItem())
                .add(ModItems.THENN_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.THENN_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.THENN_PLATE_BOOTS.get().asItem())

                // Thenn Noble
                .add(ModItems.THENN_NOBLE_HELMET.get().asItem())
                .add(ModItems.THENN_NOBLE_CHESTPLATE.get().asItem())
                .add(ModItems.THENN_NOBLE_LEGGINGS.get().asItem())
                .add(ModItems.THENN_NOBLE_BOOTS.get().asItem())
                //Nights Watch Ranger
                .add(ModItems.NIGHT_WATCH_RANGER_HELMET.get().asItem())
                .add(ModItems.NIGHT_WATCH_RANGER_CHESTPLATE.get().asItem())
                .add(ModItems.NIGHT_WATCH_RANGER_LEGGINGS.get().asItem())
                .add(ModItems.NIGHT_WATCH_RANGER_BOOTS.get().asItem())
                //Nights Watch Leather
                .add(ModItems.NIGHT_WATCH_LEATHER_HELMET.get().asItem())
                .add(ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE.get().asItem())
                .add(ModItems.NIGHT_WATCH_LEATHER_LEGGINGS.get().asItem())
                .add(ModItems.NIGHT_WATCH_LEATHER_BOOTS.get().asItem())
                //Nights Watch Elite
                .add(ModItems.NIGHT_WATCH_ELITE_HELMET.get().asItem())
                .add(ModItems.NIGHT_WATCH_ELITE_CHESTPLATE.get().asItem())
                .add(ModItems.NIGHT_WATCH_ELITE_LEGGINGS.get().asItem())
                .add(ModItems.NIGHT_WATCH_ELITE_BOOTS.get().asItem());

        this.tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.WILDLING_FUR_HELMET.get().asItem())
                .add(ModItems.WILDLING_LEATHER_HELMET.get().asItem())
                .add(ModItems.WILDLING_CHIEF_HELMET.get().asItem())
                .add(ModItems.THENN_LEVY_HELMET.get().asItem())
                .add(ModItems.THENN_PLATE_HELMET.get().asItem())
                .add(ModItems.THENN_NOBLE_HELMET.get().asItem())
                .add(ModItems.NIGHT_WATCH_RANGER_HELMET.get().asItem())
                .add(ModItems.NIGHT_WATCH_LEATHER_HELMET.get().asItem())
                .add(ModItems.NIGHT_WATCH_ELITE_HELMET.get().asItem())
                .add(ModItems.BOLTEN_LEVY_HELMET.get().asItem())
                .add(ModItems.BOLTEN_PLATE_HELMET.get().asItem())
                .add(ModItems.BOLTEN_NOBLE_HELMET.get().asItem())
                .add(ModItems.MANDERLY_LEVY_HELMET.get().asItem())
                .add(ModItems.MANDERLY_PLATE_HELMET.get().asItem())
                .add(ModItems.MANDERLY_NOBLE_HELMET.get().asItem())
                .add(ModItems.STARK_LEVY_HELMET.get().asItem())
                .add(ModItems.STARK_PLATE_HELMET.get().asItem())
                .add(ModItems.STARK_NOBLE_PLATE_HELMET.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_LEVY_HELMET.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_PLATE_HELMET.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_CHIEF_HELMET.get().asItem())
                .add(ModItems.IRONBORN_LEVY_HELMET.get().asItem())
                .add(ModItems.IRONBORN_PLATE_HELMET.get().asItem())
                .add(ModItems.IRONBORN_NOBLE_HELMET.get().asItem());

        this.tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.WILDLING_FUR_CHESTPLATE.get().asItem())
                .add(ModItems.WILDLING_LEATHER_CHESTPLATE.get().asItem())
                .add(ModItems.WILDLING_CHIEF_CHESTPLATE.get().asItem())
                .add(ModItems.THENN_LEVY_CHESTPLATE.get().asItem())
                .add(ModItems.THENN_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.THENN_NOBLE_CHESTPLATE.get().asItem())
                .add(ModItems.NIGHT_WATCH_RANGER_CHESTPLATE.get().asItem())
                .add(ModItems.NIGHT_WATCH_LEATHER_CHESTPLATE.get().asItem())
                .add(ModItems.NIGHT_WATCH_ELITE_CHESTPLATE.get().asItem())
                .add(ModItems.BOLTEN_LEVY_CHESTPLATE.get().asItem())
                .add(ModItems.BOLTEN_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.BOLTEN_NOBLE_CHESTPLATE.get().asItem())
                .add(ModItems.MANDERLY_LEVY_CHESTPLATE.get().asItem())
                .add(ModItems.MANDERLY_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.MANDERLY_NOBLE_CHESTPLATE.get().asItem())
                .add(ModItems.STARK_LEVY_CHESTPLATE.get().asItem())
                .add(ModItems.STARK_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.STARK_NOBLE_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_LEVY_CHESTPLATE.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_CHIEF_CHESTPLATE.get().asItem())
                .add(ModItems.IRONBORN_LEVY_CHESTPLATE.get().asItem())
                .add(ModItems.IRONBORN_PLATE_CHESTPLATE.get().asItem())
                .add(ModItems.IRONBORN_NOBLE_CHESTPLATE.get().asItem());

        this.tag(ItemTags.LEG_ARMOR)
                .add(ModItems.WILDLING_FUR_LEGGINGS.get().asItem())
                .add(ModItems.WILDLING_LEATHER_LEGGINGS.get().asItem())
                .add(ModItems.WILDLING_CHIEF_LEGGINGS.get().asItem())
                .add(ModItems.THENN_LEVY_LEGGINGS.get().asItem())
                .add(ModItems.THENN_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.THENN_NOBLE_LEGGINGS.get().asItem())
                .add(ModItems.NIGHT_WATCH_RANGER_LEGGINGS.get().asItem())
                .add(ModItems.NIGHT_WATCH_LEATHER_LEGGINGS.get().asItem())
                .add(ModItems.NIGHT_WATCH_ELITE_LEGGINGS.get().asItem())
                .add(ModItems.BOLTEN_LEVY_LEGGINGS.get().asItem())
                .add(ModItems.BOLTEN_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.BOLTEN_NOBLE_LEGGINGS.get().asItem())
                .add(ModItems.MANDERLY_LEVY_LEGGINGS.get().asItem())
                .add(ModItems.MANDERLY_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.MANDERLY_NOBLE_LEGGINGS.get().asItem())
                .add(ModItems.STARK_LEVY_LEGGINGS.get().asItem())
                .add(ModItems.STARK_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.STARK_NOBLE_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_LEVY_LEGGINGS.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_CHIEF_LEGGINGS.get().asItem())
                .add(ModItems.IRONBORN_LEVY_LEGGINGS.get().asItem())
                .add(ModItems.IRONBORN_PLATE_LEGGINGS.get().asItem())
                .add(ModItems.IRONBORN_NOBLE_LEGGINGS.get().asItem());

        this.tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.WILDLING_FUR_BOOTS.get().asItem())
                .add(ModItems.WILDLING_LEATHER_BOOTS.get().asItem())
                .add(ModItems.WILDLING_CHIEF_BOOTS.get().asItem())
                .add(ModItems.THENN_LEVY_BOOTS.get().asItem())
                .add(ModItems.THENN_PLATE_BOOTS.get().asItem())
                .add(ModItems.THENN_NOBLE_BOOTS.get().asItem())
                .add(ModItems.NIGHT_WATCH_RANGER_BOOTS.get().asItem())
                .add(ModItems.NIGHT_WATCH_LEATHER_BOOTS.get().asItem())
                .add(ModItems.NIGHT_WATCH_ELITE_BOOTS.get().asItem())
                .add(ModItems.BOLTEN_LEVY_BOOTS.get().asItem())
                .add(ModItems.BOLTEN_PLATE_BOOTS.get().asItem())
                .add(ModItems.BOLTEN_NOBLE_BOOTS.get().asItem())
                .add(ModItems.MANDERLY_LEVY_BOOTS.get().asItem())
                .add(ModItems.MANDERLY_PLATE_BOOTS.get().asItem())
                .add(ModItems.MANDERLY_NOBLE_BOOTS.get().asItem())
                .add(ModItems.STARK_LEVY_BOOTS.get().asItem())
                .add(ModItems.STARK_PLATE_BOOTS.get().asItem())
                .add(ModItems.STARK_NOBLE_PLATE_BOOTS.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_LEVY_BOOTS.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_PLATE_BOOTS.get().asItem())
                .add(ModItems.MOUNTAIN_CLAN_CHIEF_BOOTS.get().asItem())
                .add(ModItems.IRONBORN_LEVY_BOOTS.get().asItem())
                .add(ModItems.IRONBORN_PLATE_BOOTS.get().asItem())
                .add(ModItems.IRONBORN_NOBLE_BOOTS.get().asItem());

        this.tag(ItemTags.WALLS)
        ;

        this.tag(ItemTags.STONE_BRICKS)

                .add(ModBLocks.REDKEEP_STONE_BLOCK.get().asItem())
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get().asItem())
                .add(ModBLocks.REDKEEP_STONE_SLAB.get().asItem())
                .add(ModBLocks.REDKEEP_STONE_WALL.get().asItem())
        ;





        // Tool Tier Tags
        this.tag(ModTags.Items.DRAGONGLASS_TOOL_MATERIALS);
        this.tag(ModTags.Items.BRONZE_TOOL_MATERIALS).add(ModItems.BRONZE_INGOT.get());
        this.tag(ModTags.Items.STEEL_TOOL_MATERIALS).add(ModItems.STEEL_INGOT.get());
    }
    private <T extends Block> void addItemToTag(TagsProvider.TagAppender<Item> tag, DeferredBlock<T> block) {
        // Create a ResourceKey from the DeferredBlock for its item
        ResourceLocation id = block.getId();
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        tag.add(key);
    }
}
