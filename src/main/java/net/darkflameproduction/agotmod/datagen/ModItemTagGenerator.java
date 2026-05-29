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
                .add(ModBLocks.RED_ROSE_BUSH.get().asItem());

        this.tag(ItemTags.MEAT)
                // Meats and sausages
                // Meats and sausages
.add(ModItems.RAW_AUROCHS.get().asItem())
.add(ModItems.COOKED_AUROCHS.get().asItem())
.add(ModItems.RAW_BEAR_MEAT.get().asItem())
.add(ModItems.COOKED_BEAR_MEAT.get().asItem())
.add(ModItems.RAW_BOAR_VENISON.get().asItem())
.add(ModItems.COOKED_BOAR_VENISON.get().asItem())
.add(ModItems.RAW_DEER_VENISON.get().asItem())
.add(ModItems.COOKED_DEER_VENISON.get().asItem())
.add(ModItems.RAW_DOG.get().asItem())
.add(ModItems.COOKED_DOG.get().asItem())
.add(ModItems.RAW_DORMICE.get().asItem())
.add(ModItems.COOKED_DORMICE.get().asItem())
.add(ModItems.RAW_FROG.get().asItem())
.add(ModItems.COOKED_FROG.get().asItem())
.add(ModItems.RAW_GOAT_MEAT.get().asItem())
.add(ModItems.COOKED_GOAT_MEAT.get().asItem())
.add(ModItems.RAW_HARE_MEAT.get().asItem())
.add(ModItems.COOKED_HARE_MEAT.get().asItem())
.add(ModItems.RAW_HORSE_MEAT.get().asItem())
.add(ModItems.COOKED_HORSE_MEAT.get().asItem())
.add(ModItems.RAW_LAMB.get().asItem())
.add(ModItems.COOKED_LAMB.get().asItem())
.add(ModItems.RAW_LOCUSTS.get().asItem())
.add(ModItems.COOKED_LOCUSTS.get().asItem())
.add(ModItems.RAW_MAMMOTH_MEAT.get().asItem())
.add(ModItems.COOKED_MAMMOTH_MEAT.get().asItem())
.add(ModItems.RAW_MAN_FLESH.get().asItem())
.add(ModItems.COOKED_MAN_FLESH.get().asItem())
.add(ModItems.RAW_RAT.get().asItem())
.add(ModItems.COOKED_RAT.get().asItem())
.add(ModItems.RAW_SNAKE.get().asItem())
.add(ModItems.COOKED_SNAKE.get().asItem())
.add(ModItems.RAW_SNAIL.get().asItem())
.add(ModItems.COOKED_SNAIL.get().asItem())
.add(ModItems.RAW_SQUIRREL.get().asItem())
.add(ModItems.COOKED_SQUIRREL.get().asItem())
.add(ModItems.RAW_SUCKLING_PIG.get().asItem())
.add(ModItems.COOKED_SUCKLING_PIG.get().asItem())

// Offal & organ meats
.add(ModItems.RAW_BACON.get().asItem())
.add(ModItems.COOKED_BACON.get().asItem())
.add(ModItems.RAW_PIG_KIDNEYS.get().asItem())
.add(ModItems.COOKED_PIG_KIDNEYS.get().asItem())
.add(ModItems.RAW_PIG_LIVER.get().asItem())
.add(ModItems.COOKED_PIG_LIVER.get().asItem())
.add(ModItems.RAW_PIG_RIBS.get().asItem())
.add(ModItems.COOKED_PIG_RIBS.get().asItem())
.add(ModItems.RAW_GOOSE_LIVER.get().asItem())
.add(ModItems.COOKED_GOOSE_LIVER.get().asItem())
.add(ModItems.RAW_CALF_PANCREAS.get().asItem())
.add(ModItems.COOKED_CALF_PANCREAS.get().asItem())
.add(ModItems.HORSE_HEART.get().asItem())
.add(ModItems.JELLIED_CALVES_BRAIN.get().asItem())
.add(ModItems.MINCED_MEAT.get().asItem())
.add(ModItems.PIG_INTESTINES.get().asItem())
.add(ModItems.CALF_BRAIN.get().asItem())

// Sausages
.add(ModItems.RAW_SAUSAGE.get().asItem())
.add(ModItems.COOKED_SAUSAGE.get().asItem())
.add(ModItems.RAW_BLOOD_SAUSAGE.get().asItem())
.add(ModItems.COOKED_BLOOD_SAUSAGE.get().asItem())
.add(ModItems.RAW_WHITE_SAUSAGE.get().asItem())
.add(ModItems.COOKED_WHITE_SAUSAGE.get().asItem())

// Fowl
.add(ModItems.RAW_PIGEON.get().asItem())
.add(ModItems.COOKED_PIGEON.get().asItem())
.add(ModItems.RAW_DUCK.get().asItem())
.add(ModItems.COOKED_DUCK.get().asItem())
.add(ModItems.RAW_GOOSE.get().asItem())
.add(ModItems.COOKED_GOOSE.get().asItem())
.add(ModItems.RAW_GULL.get().asItem())
.add(ModItems.COOKED_GULL.get().asItem())
.add(ModItems.RAW_HERON.get().asItem())
.add(ModItems.COOKED_HERON.get().asItem())
.add(ModItems.RAW_LARK.get().asItem())
.add(ModItems.COOKED_LARK.get().asItem())
.add(ModItems.RAW_PARTRIDGE.get().asItem())
.add(ModItems.COOKED_PARTRIDGE.get().asItem())
.add(ModItems.RAW_PEACOCK.get().asItem())
.add(ModItems.COOKED_PEACOCK.get().asItem())
.add(ModItems.RAW_QUAIL.get().asItem())
.add(ModItems.COOKED_QUAIL.get().asItem())
.add(ModItems.RAW_SWAN.get().asItem())
.add(ModItems.COOKED_SWAN.get().asItem())
.add(ModItems.RAW_CHICKEN_NUGGETS.get().asItem())
.add(ModItems.COOKED_CHICKEN_NUGGETS.get().asItem())

// Fish and seafood
.add(ModItems.RAW_HERRING.get().asItem())
.add(ModItems.COOKED_HERRING.get().asItem())
.add(ModItems.PICKLED_HERRING.get().asItem())
.add(ModItems.RAW_TROUT.get().asItem())
.add(ModItems.COOKED_TROUT.get().asItem())
.add(ModItems.RAW_EEL.get().asItem())
.add(ModItems.COOKED_EEL.get().asItem())
.add(ModItems.RAW_LAMPREY.get().asItem())
.add(ModItems.COOKED_LAMPREY.get().asItem())
.add(ModItems.RAW_PIKE.get().asItem())
.add(ModItems.COOKED_PIKE.get().asItem())
.add(ModItems.RAW_SARDINE.get().asItem())
.add(ModItems.COOKED_SARDINE.get().asItem())
.add(ModItems.RAW_MONKFISH.get().asItem())
.add(ModItems.COOKED_MONKFISH.get().asItem())
.add(ModItems.RAW_OCTOPUS.get().asItem())
.add(ModItems.COOKED_OCTOPUS.get().asItem())
.add(ModItems.RAW_WHITEFISH.get().asItem())
.add(ModItems.COOKED_WHITEFISH.get().asItem())
.add(ModItems.RAW_CRAB.get().asItem())
.add(ModItems.COOKED_CRAB.get().asItem())
.add(ModItems.RAW_LOBSTER.get().asItem())
.add(ModItems.COOKED_LOBSTER.get().asItem())
.add(ModItems.RAW_CLAM.get().asItem())
.add(ModItems.COOKED_CLAM.get().asItem())
.add(ModItems.RAW_MUSSELS.get().asItem())
.add(ModItems.COOKED_MUSSELS.get().asItem())
.add(ModItems.RAW_WINKLES.get().asItem())
.add(ModItems.COOKED_WINKLES.get().asItem())

// Prepared & preserved
.add(ModItems.HONEYED_LOCUSTS.get().asItem())
.add(ModItems.BEEF_AND_BARLEY_STEW.get().asItem())
.add(ModItems.BEEF_STEW.get().asItem())
.add(ModItems.MUTTON_STEW.get().asItem())
.add(ModItems.JERKY.get().asItem())
.add(ModItems.HAM.get().asItem())
.add(ModItems.JELLIED_CALVES_BRAIN.get().asItem())
.add(ModItems.BREWIS.get().asItem())

                // Baked goods and prepared dishes
                .add(ModItems.BISCUITS.get().asItem())
                .add(ModItems.BOWL_OF_BROWN.get().asItem())
                .add(ModItems.BARLEY_BREAD.get().asItem())
                .add(ModItems.BLACK_BREAD.get().asItem())
                .add(ModItems.HARD_BREAD.get().asItem())
                .add(ModItems.OATCAKE.get().asItem())
                .add(ModItems.BLACKBERRY_OATCAKE.get().asItem())
                .add(ModItems.PINENUT_OATCAKE.get().asItem())
                .add(ModItems.COD_CAKE.get().asItem())
                .add(ModItems.CREAM_CAKES.get().asItem())
                .add(ModItems.HONEY_CAKE.get().asItem())
                .add(ModItems.HONEYFINGERS.get().asItem())
                .add(ModItems.LEMON_CAKE.get().asItem())
                .add(ModItems.SPECIAL_CAKE.get().asItem())
                .add(ModItems.STALE_CAKE.get().asItem())
                .add(ModItems.SWEET_CAKE.get().asItem())
                .add(ModItems.WINTERCAKE.get().asItem())
                .add(ModItems.PIGEON_PIE.get().asItem())
                .add(ModItems.VENISON_PIE.get().asItem())
                .add(ModItems.LAMPREY_PIE.get().asItem())
                .add(ModItems.LOCUST_PIE.get().asItem())
                .add(ModItems.STRAWBERRY_PIE.get().asItem())
                .add(ModItems.APPLE_TART.get().asItem())
                .add(ModItems.BERRY_TARTS.get().asItem())
                .add(ModItems.SISTERS_STEW.get().asItem())
                .add(ModItems.PEA_SOUP.get().asItem())

                // General food
                .add(ModItems.CHEESE.get().asItem())
                .add(ModItems.GRAVY.get().asItem())
                .add(ModItems.HONEYCOMB_FOOD.get().asItem())
                .add(ModItems.JAMS.get().asItem())
                .add(ModItems.JELLIES.get().asItem())
                .add(ModItems.SHERBET.get().asItem())

                // Vegetables and herbs
                .add(ModItems.HORSERADISH.get().asItem())
                .add(ModItems.LEEK.get().asItem())
                .add(ModItems.NEEP.get().asItem())
                .add(ModItems.PARSLEY.get().asItem())
                .add(ModItems.RED_ONION.get().asItem())
                .add(ModItems.TURNIP.get().asItem())
                .add(ModItems.WILD_ONION.get().asItem())
                .add(ModItems.ONION.get().asItem())
                .add(ModItems.CABBAGE.get().asItem())
                .add(ModItems.BEAN.get().asItem())
                .add(ModItems.CHICKPEA.get().asItem())
                .add(ModItems.CUCUMBER.get().asItem())
                .add(ModItems.GREEN_BEAN.get().asItem())
                .add(ModItems.SPINACH.get().asItem())
                .add(ModItems.GARLIC.get().asItem())
                .add(ModItems.LENTILS.get().asItem())
                .add(ModItems.OLIVES.get().asItem())
                .add(ModItems.PEAS.get().asItem())
                .add(ModItems.RADISH.get().asItem())
                .add(ModItems.SQUASH.get().asItem())

                // Vegetable pies
                .add(ModItems.SPINACH_PIE.get().asItem())



                // Fruits and berries
                .add(ModItems.STRAWBERRY.get().asItem())
                .add(ModItems.BLACKBERRY.get().asItem())
                .add(ModItems.BLUEBERRY.get().asItem())
                .add(ModItems.MULBERRY.get().asItem())
                .add(ModItems.RASPBERRY.get().asItem())
                .add(ModItems.SMOKEBERRY.get().asItem())
                .add(ModItems.APRICOT.get().asItem())
                .add(ModItems.BLOOD_ORANGE.get().asItem())
                .add(ModItems.CHERRY.get().asItem())
                .add(ModItems.CRABAPPLE.get().asItem())
                .add(ModItems.DATE.get().asItem())
                .add(ModItems.DORNISH_PLUM.get().asItem())
                .add(ModItems.DRIED_APPLES.get().asItem())
                .add(ModItems.FIG.get().asItem())
                .add(ModItems.FIREPLUM.get().asItem())
                .add(ModItems.GRAPE.get().asItem())
                .add(ModItems.LEMON.get().asItem())
                .add(ModItems.LIME.get().asItem())
                .add(ModItems.MELON_SLICE_AGOT.get().asItem())
                .add(ModItems.ORANGE.get().asItem())
                .add(ModItems.PEACH.get().asItem())
                .add(ModItems.PEAR.get().asItem())
                .add(ModItems.PERSIMMON.get().asItem())
                .add(ModItems.PLUM.get().asItem())
                .add(ModItems.POMEGRANATE.get().asItem())
                .add(ModItems.RAISINS.get().asItem())
                .add(ModItems.WINTER_PEACH.get().asItem())

                // Nuts
                .add(ModItems.ALMOND.get().asItem())
                .add(ModItems.CHESTNUT.get().asItem())
                .add(ModItems.PECAN.get().asItem())
                .add(ModItems.PINE_NUT.get().asItem())
                .add(ModItems.WALNUT.get().asItem())

                // Edible herbs and sweeteners
                .add(ModItems.FENNEL.get().asItem())
                .add(ModItems.MINT.get().asItem())
                .add(ModItems.HONEY.get().asItem())
                .add(ModItems.MOLASSES.get().asItem())

                // Drinks
                .add(ModItems.BEER.get().asItem())
                .add(ModItems.ALE.get().asItem())
                .add(ModItems.STOUT.get().asItem())
                .add(ModItems.RUM.get().asItem())
                .add(ModItems.BLACKBELLY_RUM.get().asItem())
                .add(ModItems.BLACK_TAR_RUM.get().asItem())
                .add(ModItems.SPICED_RUM.get().asItem())
                .add(ModItems.MYRISH_FIRE.get().asItem())
                .add(ModItems.TYROSHI_PEAR_BRANDY.get().asItem())
                .add(ModItems.ALMOND_MILK.get().asItem())
                .add(ModItems.GOAT_MILK.get().asItem())
                .add(ModItems.MARES_MILK.get().asItem())
                .add(ModItems.FERMENTED_MILK.get().asItem())
                .add(ModItems.NAHSA.get().asItem())
                .add(ModItems.ICED_MILK.get().asItem())
                .add(ModItems.TEA.get().asItem())
                .add(ModItems.MINT_TEA.get().asItem())
                .add(ModItems.NETTLE_TEA.get().asItem())
                .add(ModItems.MOON_TEA.get().asItem())
                .add(ModItems.WINE.get().asItem())
                .add(ModItems.APPLE_WINE.get().asItem())
                .add(ModItems.GREEN_APPLE_WINE.get().asItem())
                .add(ModItems.ARBOR_GOLD.get().asItem())
                .add(ModItems.ARBOR_RED.get().asItem())
                .add(ModItems.DORNISH_RED.get().asItem())
                .add(ModItems.DREAMWINE.get().asItem())
                .add(ModItems.HONEYED_WINE.get().asItem())
                .add(ModItems.SPICED_WINE.get().asItem())
                .add(ModItems.STRONGWINE.get().asItem())
                .add(ModItems.SUMMERWINE.get().asItem())
                .add(ModItems.WINE_OF_COURAGE.get().asItem())
                .add(ModItems.CIDER.get().asItem())
                .add(ModItems.HIPPOCRAS.get().asItem())
                .add(ModItems.MEAD.get().asItem())
                .add(ModItems.SHADE_OF_THE_EVENING.get().asItem())
                .add(ModItems.SUGAR_WATER.get().asItem())

                // Vanilla food
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

                // Grains
                .add(ModItems.CORN.get().asItem())
                .add(ModItems.BLACK_RICE.get().asItem())
                .add(ModItems.OAT.get().asItem())
                .add(ModItems.BARLEY.get().asItem());


        this.tag(ItemTags.BANNERS)
                .add(BannerPatterns.TARGARYEN_BANNER_PATTERN.get())

                ;




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
