package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AGoTMod.MOD_ID, existingFileHelper);
    }

    // Method to add custom tags to blocks
    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {

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

// Start building the mineable with axe tag
        TagsProvider.TagAppender<Block> axeTag = this.tag(BlockTags.MINEABLE_WITH_AXE);

// Add all wood blocks to the axe tag
        for (String woodType : woodTypes) {
            // Add logs, woods, planks and decorative blocks
            addBlockToTag(axeTag, ModBLocks.LOGS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.WOODS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.STRIPPED_LOGS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.STRIPPED_WOODS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.PLANKS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.STAIRS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.SLABS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.BUTTONS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.PRESSURE_PLATES.get(woodType));
            addBlockToTag(axeTag, ModBLocks.FENCES.get(woodType));
            addBlockToTag(axeTag, ModBLocks.FENCE_GATES.get(woodType));
            addBlockToTag(axeTag, ModBLocks.WALLS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.DOORS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.TRAPDOORS.get(woodType));

            // Add leaves if they exist
            if (ModBLocks.LEAVES.containsKey(woodType)) {
                addBlockToTag(axeTag, ModBLocks.LEAVES.get(woodType));
            }

            // Add saplings for specific wood types
            if (woodType.equals("blackbark") || woodType.equals("aspen") || woodType.equals("alder")) {
                addBlockToTag(axeTag, ModBLocks.SAPLINGS.get(woodType));
            }

            // Add sign blocks
            addBlockToTag(axeTag, ModBLocks.SIGNS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.WALL_SIGNS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.HANGING_SIGNS.get(woodType));
            addBlockToTag(axeTag, ModBLocks.WALL_HANGING_SIGNS.get(woodType));
        }

        TagsProvider.TagAppender<Block> logsThatBurnTag = this.tag(BlockTags.LOGS_THAT_BURN);
        TagsProvider.TagAppender<Block> fencesTag = this.tag(BlockTags.FENCES);
        TagsProvider.TagAppender<Block> fenceGatesTag = this.tag(BlockTags.FENCE_GATES);
        TagsProvider.TagAppender<Block> wallsTag = this.tag(BlockTags.WALLS);
        TagsProvider.TagAppender<Block> planksTag = this.tag(BlockTags.PLANKS);

        for (String woodType : woodTypes) {
            // Use map-based access consistently instead of field-based access
            addBlockToTag(logsThatBurnTag, ModBLocks.LOGS.get(woodType));
            addBlockToTag(logsThatBurnTag, ModBLocks.STRIPPED_LOGS.get(woodType));
            addBlockToTag(logsThatBurnTag, ModBLocks.WOODS.get(woodType));
            addBlockToTag(logsThatBurnTag, ModBLocks.STRIPPED_WOODS.get(woodType));

            addBlockToTag(planksTag, ModBLocks.PLANKS.get(woodType));
            addBlockToTag(fencesTag, ModBLocks.FENCES.get(woodType));
            addBlockToTag(fenceGatesTag, ModBLocks.FENCE_GATES.get(woodType));
            addBlockToTag(wallsTag, ModBLocks.WALLS.get(woodType));
        }


    // Helper method to add a DeferredBlock to a tag
        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBLocks.THATCH_BLOCK.get())
                .add(ModBLocks.THATCH_SLAB.get())
                .add(ModBLocks.THATCH_STAIRS.get());




        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBLocks.WEIRWOOD_LOG.get())
                .add(ModBLocks.WEIRWOOD_FACE_LOG.get())
                .add(ModBLocks.WEIRWOOD_WOOD.get())
                .add(ModBLocks.STRIPPED_WEIRWOOD_LOG.get())
                .add(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get())
                .add(ModBLocks.WEIRWOOD_PLANKS.get())
                .add(ModBLocks.WEIRWOOD_STAIRS.get())
                .add(ModBLocks.WEIRWOOD_SLAB.get())
                .add(ModBLocks.WEIRWOOD_BUTTON.get())
                .add(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get())
                .add(ModBLocks.WEIRWOOD_FENCE.get())
                .add(ModBLocks.WEIRWOOD_FENCE_GATE.get())
                .add(ModBLocks.WEIRWOOD_WALL.get())
                .add(ModBLocks.WEIRWOOD_DOOR.get())
                .add(ModBLocks.WEIRWOOD_TRAPDOOR.get())
                .add(ModBLocks.WEIRWOOD_SIGN.get())
                .add(ModBLocks.WEIRWOOD_WALL_SIGN.get())
                .add(ModBLocks.WEIRWOOD_HANGING_SIGN.get())
                .add(ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get())
                .add(ModBLocks.ROTTEN_SIGN.get())
                .add(ModBLocks.ROTTEN_WALL_SIGN.get())
                .add(ModBLocks.ROTTEN_HANGING_SIGN.get())
                .add(ModBLocks.ROTTEN_WALL_HANGING_SIGN.get())

                .add(ModBLocks.CHARRED_SIGN.get())
                .add(ModBLocks.CHARRED_WALL_SIGN.get())
                .add(ModBLocks.CHARRED_HANGING_SIGN.get())
                .add(ModBLocks.CHARRED_WALL_HANGING_SIGN.get())
                .add(ModBLocks.OAK_WALL.get())
                .add(ModBLocks.SPRUCE_WALL.get())
                .add(ModBLocks.BIRCH_WALL.get())
                .add(ModBLocks.JUNGLE_WALL.get())
                .add(ModBLocks.ACACIA_WALL.get())
                .add(ModBLocks.DARK_OAK_WALL.get())
                .add(ModBLocks.MANGROVE_WALL.get())
                .add(ModBLocks.CHERRY_WALL.get())
                .add(ModBLocks.BAMBOO_WALL.get())
                .add(ModBLocks.CRIMSON_WALL.get())
                .add(ModBLocks.WARPED_WALL.get())
                .add(ModBLocks.ROTTEN_LOG.get())
                .add(ModBLocks.ROTTEN_WOOD.get())
                .add(ModBLocks.STRIPPED_ROTTEN_LOG.get())
                .add(ModBLocks.STRIPPED_ROTTEN_WOOD.get())
                .add(ModBLocks.ROTTEN_PLANKS.get())
                .add(ModBLocks.ROTTEN_STAIRS.get())
                .add(ModBLocks.ROTTEN_SLAB.get())
                .add(ModBLocks.ROTTEN_BUTTON.get())
                .add(ModBLocks.ROTTEN_PRESSURE_PLATE.get())
                .add(ModBLocks.ROTTEN_FENCE.get())
                .add(ModBLocks.ROTTEN_FENCE_GATE.get())
                .add(ModBLocks.ROTTEN_WALL.get())
                .add(ModBLocks.ROTTEN_DOOR.get())
                .add(ModBLocks.ROTTEN_TRAPDOOR.get())

                .add(ModBLocks.CHARRED_LOG.get())
                .add(ModBLocks.CHARRED_WOOD.get())
                .add(ModBLocks.STRIPPED_CHARRED_LOG.get())
                .add(ModBLocks.STRIPPED_CHARRED_WOOD.get())
                .add(ModBLocks.CHARRED_PLANKS.get())
                .add(ModBLocks.CHARRED_STAIRS.get())
                .add(ModBLocks.CHARRED_SLAB.get())
                .add(ModBLocks.CHARRED_BUTTON.get())
                .add(ModBLocks.CHARRED_PRESSURE_PLATE.get())
                .add(ModBLocks.CHARRED_FENCE.get())
                .add(ModBLocks.CHARRED_FENCE_GATE.get())
                .add(ModBLocks.CHARRED_WALL.get())
                .add(ModBLocks.CHARRED_DOOR.get())
                .add(ModBLocks.CHARRED_TRAPDOOR.get())
        ;


        this.tag(BlockTags.CROPS)
                .add(ModBLocks.BARLEY_CROP.get())
                .add(ModBLocks.HORSERADISH_CROP.get())
                .add(ModBLocks.GARLIC_CROP.get())
                .add(ModBLocks.LEEK_CROP.get())
                .add(ModBLocks.NEEP_CROP.get())
                .add(ModBLocks.OAT_CROP.get())
                .add(ModBLocks.PARSLEY_CROP.get())
                .add(ModBLocks.RED_ONION_CROP.get())
                .add(ModBLocks.TURNIP_CROP.get())
                .add(ModBLocks.WILD_ONION_CROP.get())
                .add(ModBLocks.ONION_CROP.get())
                .add(ModBLocks.OPIUM_POPPY_CROP.get())
                .add(ModBLocks.CABBAGE_CROP.get())
                .add(ModBLocks.BEAN_CROP.get())
                .add(ModBLocks.CHICKPEA_CROP.get())
                .add(ModBLocks.CUCUMBER_CROP.get())
                .add(ModBLocks.GREEN_BEAN_CROP.get())
                .add(ModBLocks.SPINACH_CROP.get())
                .add(ModBLocks.DRAGON_PEPPER_CROP.get())
                .add(ModBLocks.PEPPER_CROP.get())
                .add(ModBLocks.PEPPERCORN_CROP.get())
                .add(ModBLocks.COTTON_CROP.get())
                .add(ModBLocks.HEMP_CROP.get())
                .add(ModBLocks.CORN_CROP.get())
                .add(ModBLocks.CORN_CROP_MIDDLE.get())
                .add(ModBLocks.CORN_CROP_TOP.get())




        ;






        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBLocks.QUAGMIRE.get(),
                        ModBLocks.GHOST_GRASS_BLOCK.get(),
                        ModBLocks.DIRT_STAIRS.get(),
                        ModBLocks.DIRT_SLAB.get(),
                        ModBLocks.MUD_STAIRS.get(),
                        ModBLocks.MUD_SLAB.get(),
                        ModBLocks.COARSE_DIRT_STAIRS.get(),
                        ModBLocks.COARSE_DIRT_SLAB.get(),
                        ModBLocks.PODZOL_STAIRS.get(),
                        ModBLocks.PODZOL_SLAB.get(),
                        ModBLocks.GRASS_BLOCK_STAIRS.get(),
                        ModBLocks.GRASS_BLOCK_SLAB.get(),
                        ModBLocks.DIRT_PATH_STAIRS.get(),
                        ModBLocks.DIRT_PATH_SLAB.get(),
                        ModBLocks.PEAT.get());


        this.tag(BlockTags.DIRT)
                .add(ModBLocks.QUAGMIRE.get(),
                        ModBLocks.DIRT_STAIRS.get(),
                        ModBLocks.DIRT_SLAB.get(),
                        ModBLocks.MUD_STAIRS.get(),
                        ModBLocks.MUD_SLAB.get(),
                        ModBLocks.COARSE_DIRT_STAIRS.get(),
                        ModBLocks.COARSE_DIRT_SLAB.get(),
                        ModBLocks.PODZOL_STAIRS.get(),
                        ModBLocks.PODZOL_SLAB.get(),
                        ModBLocks.GRASS_BLOCK_STAIRS.get(),
                        ModBLocks.GRASS_BLOCK_SLAB.get(),
                        ModBLocks.DIRT_PATH_STAIRS.get(),
                        ModBLocks.DIRT_PATH_SLAB.get());


        this.tag(BlockTags.STONE_ORE_REPLACEABLES)
                .add(ModBLocks.TIN_ORE.get(),
                        ModBLocks.STONE_BRICK_BUT_COOLER.get(),
                        ModBLocks.TIN_BLOCK.get(),
                        ModBLocks.BRONZE_BLOCK.get(),
                        ModBLocks.MINT_BLOCK.get(),
                        ModBLocks.RAW_TIN_BLOCK.get(),
                        ModBLocks.DARK_STONE_BRICK.get(),
                        ModBLocks.DEEPSLATE_TIN_ORE.get(),
                        ModBLocks.YELLOW_DIAMOND_BLOCK.get(),
                        ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get(),
                        ModBLocks.AMBER_BLOCK.get(),
                        ModBLocks.AMBER_ORE.get(),
                        ModBLocks.AMBER_DEEPSLATE_ORE.get(),
                        ModBLocks.AMETHYST_BLOCK.get(),
                        ModBLocks.AMETHYST_ORE.get(),
                        ModBLocks.AMETHYST_DEEPSLATE_ORE.get(),
                        ModBLocks.BLACK_DIAMOND_BLOCK.get(),
                        ModBLocks.BLOODSTONE_BLOCK.get(),
                        ModBLocks.BLOODSTONE_ORE.get(),
                        ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get(),
                        ModBLocks.CARNELIAN_ORE.get(),
                        ModBLocks.CARNELIAN_DEEPSLATE_ORE.get(),
                        ModBLocks.CARNELIAN_BLOCK.get(),
                        ModBLocks.CHALCEDONY_BLOCK.get(),
                        ModBLocks.CHALCEDONY_ORE.get(),
                        ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get(),
                        ModBLocks.DIAMONDS_ORE.get(),
                        ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(),
                        ModBLocks.GARNET_BLOCK.get(),
                        ModBLocks.GARNET_ORE.get(),
                        ModBLocks.GARNET_DEEPSLATE_ORE.get(),
                        ModBLocks.JADE_BLOCK.get(),
                        ModBLocks.JADE_ORE.get(),
                        ModBLocks.JADE_DEEPSLATE_ORE.get(),
                        ModBLocks.JASPER_BLOCK.get(),
                        ModBLocks.JASPER_ORE.get(),
                        ModBLocks.JASPER_DEEPSLATE_ORE.get(),
                        ModBLocks.MALACHITE_BLOCK.get(),
                        ModBLocks.MALACHITE_ORE.get(),
                        ModBLocks.MALACHITE_DEEPSLATE_ORE.get(),
                        ModBLocks.MOONSTONE_BLOCK.get(),
                        ModBLocks.MOONSTONE_ORE.get(),
                        ModBLocks.MOONSTONE_DEEPSLATE_ORE.get(),
                        ModBLocks.ONYX_BLOCK.get(),
                        ModBLocks.ONYX_ORE.get(),
                        ModBLocks.ONYX_DEEPSLATE_ORE.get(),
                        ModBLocks.OPAL_BLOCK.get(),
                        ModBLocks.OPAL_ORE.get(),
                        ModBLocks.OPAL_DEEPSLATE_ORE.get(),
                        ModBLocks.RUBY_BLOCK.get(),
                        ModBLocks.RUBY_ORE.get(),
                        ModBLocks.RUBY_DEEPSLATE_ORE.get(),
                        ModBLocks.SAPPHIRE_BLOCK.get(),
                        ModBLocks.SAPPHIRE_ORE.get(),
                        ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get(),
                        ModBLocks.TIGERS_EYE_BLOCK.get(),
                        ModBLocks.TIGERS_EYE_ORE.get(),
                        ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get(),
                        ModBLocks.TOPAZ_BLOCK.get(),
                        ModBLocks.TOPAZ_ORE.get(),
                        ModBLocks.TOPAZ_DEEPSLATE_ORE.get(),
                        ModBLocks.TOURMALINE_ORE.get(),
                        ModBLocks.TOURMALINE_DEEPSLATE_ORE.get(),
                        ModBLocks.RAW_SILVER_BLOCK.get(),
                        ModBLocks.SILVER_BLOCK.get(),
                        ModBLocks.SILVER_ORE.get(),
                        ModBLocks.DEEPSLATE_SILVER_ORE.get());


        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                        .add(ModBLocks.TIN_ORE.get(),
                        ModBLocks.STONE_BRICK_BUT_COOLER.get(),
                        ModBLocks.TIN_BLOCK.get(),
                        ModBLocks.BRONZE_BLOCK.get(),
                        ModBLocks.MINT_BLOCK.get(),
                        ModBLocks.RAW_TIN_BLOCK.get(),
                        ModBLocks.DARK_STONE_BRICK.get(),
                        ModBLocks.DEEPSLATE_TIN_ORE.get(),
                                ModBLocks.YELLOW_DIAMOND_BLOCK.get(),
                        ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get(),
                        ModBLocks.AMBER_BLOCK.get(),
                        ModBLocks.AMBER_ORE.get(),
                        ModBLocks.AMBER_DEEPSLATE_ORE.get(),
                        ModBLocks.AMETHYST_BLOCK.get(),
                        ModBLocks.AMETHYST_ORE.get(),
                        ModBLocks.AMETHYST_DEEPSLATE_ORE.get(),
                        ModBLocks.BLACK_DIAMOND_BLOCK.get(),
                        ModBLocks.BLOODSTONE_BLOCK.get(),
                        ModBLocks.BLOODSTONE_ORE.get(),
                        ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get(),
                        ModBLocks.CARNELIAN_ORE.get(),
                        ModBLocks.CARNELIAN_DEEPSLATE_ORE.get(),
                        ModBLocks.CARNELIAN_BLOCK.get(),
                        ModBLocks.CHALCEDONY_BLOCK.get(),
                        ModBLocks.CHALCEDONY_ORE.get(),
                        ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get(),
                        ModBLocks.DIAMONDS_ORE.get(),
                        ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(),
                        ModBLocks.GARNET_BLOCK.get(),
                        ModBLocks.GARNET_ORE.get(),
                        ModBLocks.GARNET_DEEPSLATE_ORE.get(),
                        ModBLocks.JADE_BLOCK.get(),
                        ModBLocks.JADE_ORE.get(),
                        ModBLocks.JADE_DEEPSLATE_ORE.get(),
                        ModBLocks.JASPER_BLOCK.get(),
                        ModBLocks.JASPER_ORE.get(),
                        ModBLocks.JASPER_DEEPSLATE_ORE.get(),
                        ModBLocks.MALACHITE_BLOCK.get(),
                        ModBLocks.MALACHITE_ORE.get(),
                        ModBLocks.MALACHITE_DEEPSLATE_ORE.get(),
                        ModBLocks.MOONSTONE_BLOCK.get(),
                        ModBLocks.MOONSTONE_ORE.get(),
                        ModBLocks.MOONSTONE_DEEPSLATE_ORE.get(),
                        ModBLocks.ONYX_BLOCK.get(),
                        ModBLocks.ONYX_ORE.get(),
                        ModBLocks.ONYX_DEEPSLATE_ORE.get(),
                        ModBLocks.OPAL_BLOCK.get(),
                        ModBLocks.OPAL_ORE.get(),
                        ModBLocks.OPAL_DEEPSLATE_ORE.get(),
                        ModBLocks.RUBY_BLOCK.get(),
                        ModBLocks.RUBY_ORE.get(),
                        ModBLocks.RUBY_DEEPSLATE_ORE.get(),
                        ModBLocks.SAPPHIRE_BLOCK.get(),
                        ModBLocks.SAPPHIRE_ORE.get(),
                        ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get(),
                        ModBLocks.TIGERS_EYE_BLOCK.get(),
                        ModBLocks.TIGERS_EYE_ORE.get(),
                        ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get(),
                        ModBLocks.TOPAZ_BLOCK.get(),
                        ModBLocks.TOPAZ_ORE.get(),
                        ModBLocks.TOPAZ_DEEPSLATE_ORE.get(),
                        ModBLocks.TOURMALINE_ORE.get(),
                        ModBLocks.TOURMALINE_DEEPSLATE_ORE.get(),
                                ModBLocks.RAW_SILVER_BLOCK.get(),
                                ModBLocks.SILVER_BLOCK.get(),
                                ModBLocks.SILVER_ORE.get(),
                                ModBLocks.DEEPSLATE_SILVER_ORE.get(),
                                ModBLocks.KINGS_LANDING_BRICK_LARGE.get())
                .add(ModBLocks.REDKEEP_STONE_BLOCK.get())
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get())
                .add(ModBLocks.REDKEEP_STONE_SLAB.get())
                .add(ModBLocks.REDKEEP_STONE_WALL.get())
                .add(ModBLocks.HEARTH_BLOCK.get())

        ;

        //FLOWERS


        this.tag(BlockTags.FLOWERS)
                .add(ModBLocks.WINTER_ROSE.get())
                .add(ModBLocks.WILD_RADISH.get())
                .add(ModBLocks.WHITE_ROSE.get())
                .add(ModBLocks.DUSKY_ROSE.get())
                .add(ModBLocks.ROSE.get())
                .add(ModBLocks.THORN_BUSH.get())
                .add(ModBLocks.THISTLE.get())
                .add(ModBLocks.TANSY.get())
                .add(ModBLocks.SPICEFLOWER.get())
                .add(ModBLocks.SEDGE.get())
                .add(ModBLocks.SAFFRON_CROCUS.get())
                .add(ModBLocks.POISON_KISSES.get())
                .add(ModBLocks.PENNYROYAL.get())
                .add(ModBLocks.OPIUM_POPPY_WILD.get())
                .add(ModBLocks.NIGHTSHADE.get())
                .add(ModBLocks.MOONBLOOM.get())
                .add(ModBLocks.LUNGWORT.get())
                .add(ModBLocks.LIVERWORT.get())
                .add(ModBLocks.LAVENDER.get())
                .add(ModBLocks.LADYS_LACE.get())
                .add(ModBLocks.GORSE.get())
                .add(ModBLocks.GOLDENROD.get())
                .add(ModBLocks.GOLDENCUP.get())
                .add(ModBLocks.GOATHEAD.get())
                .add(ModBLocks.GINGER.get())
                .add(ModBLocks.GILLYFLOWER.get())
                .add(ModBLocks.FROSTFIRE.get())
                .add(ModBLocks.FORGET_ME_NOT.get())
                .add(ModBLocks.EVENING_STAR.get())
                .add(ModBLocks.DRAGONS_BREATH.get())
                .add(ModBLocks.COLDSNAP.get())
                .add(ModBLocks.BLUE_ROSE.get())
                .add(ModBLocks.BLOODBLOOM.get())
                .add(ModBLocks.BLACK_LOTUS.get())
                .add(ModBLocks.BLUE_ROSE_BUSH.get())
                .add(ModBLocks.WHITE_ROSE_BUSH.get())
                .add(ModBLocks.DUSKY_ROSE_BUSH.get())
                .add(ModBLocks.WINTER_ROSE_BUSH.get())
                .add(ModBLocks.RED_ROSE_BUSH.get());

        this.tag(BlockTags.SMALL_FLOWERS)
                .add(ModBLocks.WINTER_ROSE.get())
                .add(ModBLocks.WILD_RADISH.get())
                .add(ModBLocks.WHITE_ROSE.get())
                .add(ModBLocks.DUSKY_ROSE.get())
                .add(ModBLocks.ROSE.get())
                .add(ModBLocks.THORN_BUSH.get())
                .add(ModBLocks.THISTLE.get())
                .add(ModBLocks.TANSY.get())
                .add(ModBLocks.SPICEFLOWER.get())
                .add(ModBLocks.SEDGE.get())
                .add(ModBLocks.SAFFRON_CROCUS.get())
                .add(ModBLocks.POISON_KISSES.get())
                .add(ModBLocks.PENNYROYAL.get())
                .add(ModBLocks.OPIUM_POPPY_WILD.get())
                .add(ModBLocks.NIGHTSHADE.get())
                .add(ModBLocks.MOONBLOOM.get())
                .add(ModBLocks.LUNGWORT.get())
                .add(ModBLocks.LIVERWORT.get())
                .add(ModBLocks.LAVENDER.get())
                .add(ModBLocks.LADYS_LACE.get())
                .add(ModBLocks.GORSE.get())
                .add(ModBLocks.GOLDENROD.get())
                .add(ModBLocks.GOLDENCUP.get())
                .add(ModBLocks.GOATHEAD.get())
                .add(ModBLocks.GINGER.get())
                .add(ModBLocks.GILLYFLOWER.get())
                .add(ModBLocks.FROSTFIRE.get())
                .add(ModBLocks.FORGET_ME_NOT.get())
                .add(ModBLocks.EVENING_STAR.get())
                .add(ModBLocks.DRAGONS_BREATH.get())
                .add(ModBLocks.COLDSNAP.get())
                .add(ModBLocks.BLUE_ROSE.get())
                .add(ModBLocks.BLOODBLOOM.get())
                .add(ModBLocks.BLACK_LOTUS.get());

        this.tag(BlockTags.TALL_FLOWERS)
                .add(ModBLocks.BLUE_ROSE_BUSH.get())
                .add(ModBLocks.WHITE_ROSE_BUSH.get())
                .add(ModBLocks.DUSKY_ROSE_BUSH.get())
                .add(ModBLocks.WINTER_ROSE_BUSH.get())
                .add(ModBLocks.RED_ROSE_BUSH.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBLocks.WEIRWOOD_LOG.get())
                .add(ModBLocks.STRIPPED_WEIRWOOD_LOG.get())
                .add(ModBLocks.WEIRWOOD_WOOD.get())
                .add(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get())
                .add(ModBLocks.ROTTEN_LOG.get())
                .add(ModBLocks.STRIPPED_ROTTEN_LOG.get())
                .add(ModBLocks.ROTTEN_WOOD.get())
                .add(ModBLocks.STRIPPED_ROTTEN_WOOD.get())
                .add(ModBLocks.CHARRED_LOG.get())
                .add(ModBLocks.STRIPPED_CHARRED_LOG.get())
                .add(ModBLocks.CHARRED_WOOD.get())
                .add(ModBLocks.STRIPPED_CHARRED_WOOD.get());

        this.tag(BlockTags.FENCES)
                .add(ModBLocks.WEIRWOOD_FENCE.get())
                .add(ModBLocks.ROTTEN_FENCE.get())
                .add(ModBLocks.CHARRED_FENCE.get());

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBLocks.WEIRWOOD_FENCE_GATE.get())
                .add(ModBLocks.ROTTEN_FENCE_GATE.get())
                .add(ModBLocks.CHARRED_FENCE_GATE.get());

        this.tag(BlockTags.WALLS)
                .add(ModBLocks.WEIRWOOD_WALL.get())
                .add(ModBLocks.ROTTEN_WALL.get())
                .add(ModBLocks.CHARRED_WALL.get())
                .add(ModBLocks.OAK_WALL.get())
                .add(ModBLocks.SPRUCE_WALL.get())
                .add(ModBLocks.BIRCH_WALL.get())
                .add(ModBLocks.JUNGLE_WALL.get())
                .add(ModBLocks.ACACIA_WALL.get())
                .add(ModBLocks.DARK_OAK_WALL.get())
                .add(ModBLocks.MANGROVE_WALL.get())
                .add(ModBLocks.CHERRY_WALL.get())
                .add(ModBLocks.BAMBOO_WALL.get())
                .add(ModBLocks.CRIMSON_WALL.get())
                .add(ModBLocks.WARPED_WALL.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBLocks.WEIRWOOD_PLANKS.get())
                .add(ModBLocks.ROTTEN_PLANKS.get())
                .add(ModBLocks.CHARRED_PLANKS.get());





        //Stone Blocks Organised

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.REDKEEP_STONE_BLOCK.get())
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get())
                .add(ModBLocks.REDKEEP_STONE_SLAB.get())
                .add(ModBLocks.REDKEEP_STONE_WALL.get())


        ;


        this.tag(BlockTags.STONE_BRICKS)

                .add(ModBLocks.REDKEEP_STONE_BLOCK.get())


        ;

        ;
        this.tag(BlockTags.STAIRS)
                .add(ModBLocks.REDKEEP_STONE_STAIRS.get(),
                        ModBLocks.DIRT_STAIRS.get(),
                        ModBLocks.MUD_STAIRS.get(),
                        ModBLocks.COARSE_DIRT_STAIRS.get(),
                        ModBLocks.PODZOL_STAIRS.get());

        this.tag(BlockTags.SLABS)
                .add(ModBLocks.REDKEEP_STONE_SLAB.get(),
                        ModBLocks.DIRT_SLAB.get(),
                        ModBLocks.MUD_SLAB.get(),
                        ModBLocks.COARSE_DIRT_SLAB.get(),
                        ModBLocks.PODZOL_SLAB.get());

        this.tag(BlockTags.WALLS)
                .add(ModBLocks.REDKEEP_STONE_WALL.get());

// Replace all variant tag registration blocks with this

        for (Map<Integer, ModBLocks.BlockSet> variants : List.of(
                ModBLocks.BLACKSTONE_VARIANTS,  ModBLocks.BASALT_VARIANTS,
                ModBLocks.BRICKS_VARIANTS,      ModBLocks.CALCITE_VARIANTS,
                ModBLocks.CDEEPSLATE_VARIANTS,  ModBLocks.GRANITE_VARIANTS,
                ModBLocks.REDKEEP_VARIANTS,     ModBLocks.RSANDSTONE_VARIANTS,
                ModBLocks.SANDSTONE_VARIANTS,   ModBLocks.STONE_VARIANTS,
                ModBLocks.SSTONE_VARIANTS,      ModBLocks.BONE_VARIANTS,
                ModBLocks.DRIPSTONE_VARIANTS,   ModBLocks.PACKED_ICE_VARIANTS,
                ModBLocks.QUARTZ_VARIANTS,      ModBLocks.ANDESITE_VARIANTS,
                ModBLocks.TUFF_VARIANTS,        ModBLocks.DIORITE_VARIANTS,
                ModBLocks.MUD_BRICK_VARIANTS
        )) {
            Block[] bases   = variants.values().stream().map(s -> s.base().get()).toArray(Block[]::new);
            Block[] stairs  = variants.values().stream().map(s -> s.stairs().get()).toArray(Block[]::new);
            Block[] slabs   = variants.values().stream().map(s -> s.slab().get()).toArray(Block[]::new);
            Block[] walls   = variants.values().stream().map(s -> s.wall().get()).toArray(Block[]::new);

            this.tag(BlockTags.STONE_BRICKS).add(bases);
            this.tag(BlockTags.WALLS).add(walls);
            this.tag(BlockTags.SLABS).add(slabs);
            this.tag(BlockTags.STAIRS).add(stairs);
            this.tag(BlockTags.NEEDS_STONE_TOOL).add(bases).add(stairs).add(slabs).add(walls);
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(bases).add(stairs).add(slabs).add(walls);
        }




        // Add blocks that need a stone tool to the NEEDS_STONE_TOOL tag
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBLocks.TIN_ORE.get())
                .add(ModBLocks.DARK_STONE_BRICK.get())
                .add(ModBLocks.STONE_BRICK_BUT_COOLER.get())
                .add(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        // ---------------------------(BRICKS)--------------------------- //
        this.tag(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        this.tag(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        // Add blocks that need an iron tool to the NEEDS_IRON_TOOL tag
        // ---------------------------(TIN)--------------------------- //
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.TIN_BLOCK.get())
                .add(ModBLocks.RAW_TIN_BLOCK.get())
                .add(ModBLocks.DEEPSLATE_TIN_ORE.get());
        this.tag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL);
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRONZE)--------------------------- //
        // Add blocks that need an iron tool to the NEEDS_IRON_TOOL tag
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBLocks.BRONZE_BLOCK.get());
        // ---------------------------(BRONZE)--------------------------- //
        // Add blocks that need a bronze tool to the custom NEEDS_BRONZE_TOOL tag
        this.tag(ModTags.Blocks.INCORRECT_FOR_BRONZE_TOOL)
                .addTag(ModTags.Blocks.NEEDS_STEEL_TOOL)
                .addTag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(ModTags.Blocks.NEEDS_BRONZE_TOOL)
                .add(ModBLocks.MINT_BLOCK.get());

        // ---------------------------(STEEL)--------------------------- //
        this.tag(ModTags.Blocks.NEEDS_STEEL_TOOL);

        // ---------------------------(DRAGON GLASS)--------------------------- //
        this.tag(ModTags.Blocks.NEEDS_DRAGONGLASS_TOOL);

        /* // ---------------------------(NETHERITE TOOL)--------------------------- // */

        this.tag(ModTags.Blocks.INCORRECT_FOR_STEEL_TOOL).addTag(BlockTags.NEEDS_DIAMOND_TOOL);

    }
    private <T extends Block> void addBlockToTag(TagsProvider.TagAppender<Block> tag, DeferredBlock<T> block) {
        // Create a ResourceKey from the DeferredBlock
        ResourceLocation id = block.getId();
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
        tag.add(key);
    }
}
