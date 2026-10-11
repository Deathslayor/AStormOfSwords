package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.item.custom.BannerPatterns;
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
import net.minecraft.world.item.Items;
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

                //Rotten
                .add(ModBLocks.ROTTEN_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_ROTTEN_LOG.get().asItem())
                .add(ModBLocks.ROTTEN_WOOD.get().asItem())
                .add(ModBLocks.STRIPPED_ROTTEN_WOOD.get().asItem())

                //Charred
                .add(ModBLocks.CHARRED_LOG.get().asItem())
                .add(ModBLocks.STRIPPED_CHARRED_LOG.get().asItem())
                .add(ModBLocks.CHARRED_WOOD.get().asItem())
                .add(ModBLocks.STRIPPED_CHARRED_WOOD.get().asItem())
        ;

        this.tag(ItemTags.PLANKS)
                .add(ModBLocks.WEIRWOOD_PLANKS.get().asItem())
                .add(ModBLocks.ROTTEN_PLANKS.get().asItem())
                .add(ModBLocks.CHARRED_PLANKS.get().asItem());

        this.tag(ItemTags.WOODEN_STAIRS)
                .add(ModBLocks.WEIRWOOD_STAIRS.get().asItem())
                .add(ModBLocks.ROTTEN_STAIRS.get().asItem())
                .add(ModBLocks.CHARRED_STAIRS.get().asItem());

        this.tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBLocks.WEIRWOOD_BUTTON.get().asItem())
                .add(ModBLocks.ROTTEN_BUTTON.get().asItem())
                .add(ModBLocks.CHARRED_BUTTON.get().asItem());

        this.tag(ItemTags.WOODEN_DOORS)
                .add(ModBLocks.WEIRWOOD_DOOR.get().asItem())
                .add(ModBLocks.ROTTEN_DOOR.get().asItem())
                .add(ModBLocks.CHARRED_DOOR.get().asItem());

        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.ROTTEN_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.CHARRED_PRESSURE_PLATE.get().asItem());

        this.tag(ItemTags.WOODEN_SLABS)
                .add(ModBLocks.WEIRWOOD_SLAB.get().asItem())
                .add(ModBLocks.ROTTEN_SLAB.get().asItem())
                .add(ModBLocks.CHARRED_SLAB.get().asItem());

        this.tag(ItemTags.WOODEN_FENCES)
                .add(ModBLocks.WEIRWOOD_FENCE.get().asItem())
                .add(ModBLocks.ROTTEN_FENCE.get().asItem())
                .add(ModBLocks.CHARRED_FENCE.get().asItem())
                .add(ModBLocks.WEIRWOOD_FENCE_GATE.get().asItem())
                .add(ModBLocks.ROTTEN_FENCE_GATE.get().asItem())
                .add(ModBLocks.CHARRED_FENCE_GATE.get().asItem())
                .add(ModBLocks.WEIRWOOD_WALL.get().asItem())
                .add(ModBLocks.ROTTEN_WALL.get().asItem())
                .add(ModBLocks.CHARRED_WALL.get().asItem())
                .add(ModBLocks.OAK_WALL.get().asItem())
                .add(ModBLocks.SPRUCE_WALL.get().asItem())
                .add(ModBLocks.BIRCH_WALL.get().asItem())
                .add(ModBLocks.JUNGLE_WALL.get().asItem())
                .add(ModBLocks.ACACIA_WALL.get().asItem())
                .add(ModBLocks.DARK_OAK_WALL.get().asItem())
                .add(ModBLocks.MANGROVE_WALL.get().asItem())
                .add(ModBLocks.CHERRY_WALL.get().asItem())
                .add(ModBLocks.BAMBOO_WALL.get().asItem())
                .add(ModBLocks.CRIMSON_WALL.get().asItem())
                .add(ModBLocks.WARPED_WALL.get().asItem());

        this.tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBLocks.WEIRWOOD_TRAPDOOR.get().asItem())
                .add(ModBLocks.ROTTEN_TRAPDOOR.get().asItem())
                .add(ModBLocks.CHARRED_TRAPDOOR.get().asItem());
        this.tag(ItemTags.LEAVES)
                .add(ModBLocks.WEIRWOOD_LEAVES.get().asItem());

        String[] woodTypes = {
                "alder",
                "almond",
                "apple",
                "apricot",
                "ash",
                "aspen",
                "baobab",
                "beech",
                "black_cherry",
                "black_cottonwood",
                "black_olive",
                "blackbark",
                "blackthorn",
                "blood_orange",
                "bloodwood",
                "blue_mahoe",
                "cedar",
                "chestnut",
                "cottonwood",
                "crabapple",
                "datepalm",
                "ebony",
                "fig",
                "fir",
                "fireplum",
                "goldenheart",
                "hawthorn",
                "ironwood",
                "lemon",
                "lime",
                "linden",
                "mahogany",
                "maple",
                "myrrh",
                "nightwood",
                "nutmeg",
                "olive",
                "orange",
                "peach",
                "pear",
                "pecan",
                "persimmon",
                "pine",
                "pink_ivory",
                "plum",
                "pomegranate",
                "purpleheart",
                "redwood",
                "sandalwood",
                "sandbeggar",
                "sentinel",
                "sycamore",
                "tigerwood",
                "white_cherry",
                "willow",
                "wormtree",
                "yew",
                "blue_soldier_pine",
                "soldier_pine"
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
                .add(ModBLocks.RED_ROSE_BUSH.get().asItem())
                .add(ModBLocks.REED.get().asItem())

        ;


        // NPCs eat anything in minecraft:meat (HungerSystem) - every food, vanilla and the mod's
        net.minecraft.data.tags.IntrinsicHolderTagsProvider.IntrinsicTagAppender<Item> npcFood = this.tag(ItemTags.MEAT)
                .add(Items.APPLE.asItem())
                .add(Items.GOLDEN_APPLE.asItem())
                .add(Items.ENCHANTED_GOLDEN_APPLE.asItem())
                .add(Items.MELON_SLICE.asItem())
                .add(Items.SWEET_BERRIES.asItem())
                .add(Items.GLOW_BERRIES.asItem())
                .add(Items.CHORUS_FRUIT.asItem())
                .add(Items.CARROT.asItem())
                .add(Items.GOLDEN_CARROT.asItem())
                .add(Items.POTATO.asItem())
                .add(Items.BAKED_POTATO.asItem())
                .add(Items.BEETROOT.asItem())
                .add(Items.DRIED_KELP.asItem())
                .add(Items.BEEF.asItem())
                .add(Items.COOKED_BEEF.asItem())
                .add(Items.PORKCHOP.asItem())
                .add(Items.COOKED_PORKCHOP.asItem())
                .add(Items.MUTTON.asItem())
                .add(Items.COOKED_MUTTON.asItem())
                .add(Items.CHICKEN.asItem())
                .add(Items.COOKED_CHICKEN.asItem())
                .add(Items.RABBIT.asItem())
                .add(Items.COOKED_RABBIT.asItem())
                .add(Items.COD.asItem())
                .add(Items.COOKED_COD.asItem())
                .add(Items.SALMON.asItem())
                .add(Items.COOKED_SALMON.asItem())
                .add(Items.BREAD.asItem())
                .add(Items.COOKIE.asItem())
                .add(Items.PUMPKIN_PIE.asItem())
                .add(Items.MUSHROOM_STEW.asItem())
                .add(Items.BEETROOT_SOUP.asItem())
                .add(Items.SUSPICIOUS_STEW.asItem())
                ;
        for (net.darkflameproduction.agotmod.item.food.FoodCatalog.Food food
                : net.darkflameproduction.agotmod.item.food.FoodCatalog.FOODS) {
            Item item = net.darkflameproduction.agotmod.item.food.ModFoodItems.get(food.id()).get();
            npcFood.add(item);
            switch (food.category()) {
                case VEGETABLE -> this.tag(net.darkflameproduction.agotmod.util.ModTags.Items.VEGETABLES).add(item);
                case FRUIT -> this.tag(net.darkflameproduction.agotmod.util.ModTags.Items.FRUITS).add(item);
                case RAW_MEAT -> this.tag(net.darkflameproduction.agotmod.util.ModTags.Items.RAW_MEATS).add(item);
                case COOKED_MEAT -> this.tag(net.darkflameproduction.agotmod.util.ModTags.Items.COOKED_MEATS).add(item);
                default -> {}
            }
        }


        this.tag(ItemTags.BANNERS)
                .add(BannerPatterns.TARGARYEN_BANNER_PATTERN.get())

                ;




        // 3D armour sets: armour slot tags, and freeze immunity (powder snow) for the fur and leather families
        for (net.darkflameproduction.agotmod.armor.ModArmorSets.ArmorSet set : net.darkflameproduction.agotmod.armor.ModArmorSets.all()) {
            this.tag(ItemTags.HEAD_ARMOR).add(set.piece(net.minecraft.world.item.ArmorItem.Type.HELMET).get());
            this.tag(ItemTags.CHEST_ARMOR).add(set.piece(net.minecraft.world.item.ArmorItem.Type.CHESTPLATE).get());
            this.tag(ItemTags.LEG_ARMOR).add(set.piece(net.minecraft.world.item.ArmorItem.Type.LEGGINGS).get());
            this.tag(ItemTags.FOOT_ARMOR).add(set.piece(net.minecraft.world.item.ArmorItem.Type.BOOTS).get());
            if (set.chain.fromLeather) {
                for (net.minecraft.world.item.ArmorItem.Type type : net.darkflameproduction.agotmod.armor.ModArmorSets.PIECES) {
                    this.tag(ItemTags.FREEZE_IMMUNE_WEARABLES).add(set.piece(type).get());
                }
            }
        }

        this.tag(ItemTags.WALLS)
        ;

        this.tag(ItemTags.STONE_BRICKS)

                .add(ModBLocks.REDKEEP_STONE_BLOCK.get().asItem())
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get().asItem())
                .add(ModBLocks.REDKEEP_STONE_SLAB.get().asItem())
                .add(ModBLocks.REDKEEP_STONE_WALL.get().asItem())
        ;

        this.tag(ItemTags.SLABS)
                .add(ModBLocks.THATCH_SLAB.get().asItem().asItem());
        this.tag(ItemTags.STAIRS)
                .add(ModBLocks.THATCH_STAIRS.get().asItem().asItem());
        this.tag(ItemTags.LEAVES)
                .add(ModBLocks.THATCH_BLOCK.get().asItem().asItem());

        this.tag(ItemTags.DIRT)
                .add(ModBLocks.PEAT.get().asItem().asItem());




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

