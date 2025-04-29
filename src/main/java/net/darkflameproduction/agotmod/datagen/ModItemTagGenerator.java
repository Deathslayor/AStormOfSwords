package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
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
                .add(ModBLocks.ASPEN_PLANKS.get().asItem());

        // STAIRS
        this.tag(ItemTags.WOODEN_STAIRS)
                .add(ModBLocks.WEIRWOOD_STAIRS.get().asItem())
                .add(ModBLocks.SYCAMORE_STAIRS.get().asItem())
                .add(ModBLocks.SENTINEL_STAIRS.get().asItem())
                .add(ModBLocks.PINE_STAIRS.get().asItem())
                .add(ModBLocks.IRONWOOD_STAIRS.get().asItem())
                .add(ModBLocks.HAWTHORN_STAIRS.get().asItem())
                .add(ModBLocks.CHESTNUT_STAIRS.get().asItem())
                .add(ModBLocks.CEDAR_STAIRS.get().asItem())
                .add(ModBLocks.BEECH_STAIRS.get().asItem())
                .add(ModBLocks.ASH_STAIRS.get().asItem())
                .add(ModBLocks.BLACKBARK_STAIRS.get().asItem())
                .add(ModBLocks.ASPEN_STAIRS.get().asItem());

// BUTTONS
        this.tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBLocks.WEIRWOOD_BUTTON.get().asItem())
                .add(ModBLocks.SYCAMORE_BUTTON.get().asItem())
                .add(ModBLocks.SENTINEL_BUTTON.get().asItem())
                .add(ModBLocks.PINE_BUTTON.get().asItem())
                .add(ModBLocks.IRONWOOD_BUTTON.get().asItem())
                .add(ModBLocks.HAWTHORN_BUTTON.get().asItem())
                .add(ModBLocks.CHESTNUT_BUTTON.get().asItem())
                .add(ModBLocks.CEDAR_BUTTON.get().asItem())
                .add(ModBLocks.BEECH_BUTTON.get().asItem())
                .add(ModBLocks.ASH_BUTTON.get().asItem())
                .add(ModBLocks.BLACKBARK_BUTTON.get().asItem())
                .add(ModBLocks.ASPEN_BUTTON.get().asItem());

// DOORS
        this.tag(ItemTags.WOODEN_DOORS)
                .add(ModBLocks.WEIRWOOD_DOOR.get().asItem())
                .add(ModBLocks.SYCAMORE_DOOR.get().asItem())
                .add(ModBLocks.SENTINEL_DOOR.get().asItem())
                .add(ModBLocks.PINE_DOOR.get().asItem())
                .add(ModBLocks.IRONWOOD_DOOR.get().asItem())
                .add(ModBLocks.HAWTHORN_DOOR.get().asItem())
                .add(ModBLocks.CHESTNUT_DOOR.get().asItem())
                .add(ModBLocks.CEDAR_DOOR.get().asItem())
                .add(ModBLocks.BEECH_DOOR.get().asItem())
                .add(ModBLocks.ASH_DOOR.get().asItem())
                .add(ModBLocks.BLACKBARK_DOOR.get().asItem())
                .add(ModBLocks.ASPEN_DOOR.get().asItem());

// SLABS
        this.tag(ItemTags.WOODEN_SLABS)
                .add(ModBLocks.WEIRWOOD_SLAB.get().asItem())
                .add(ModBLocks.SYCAMORE_SLAB.get().asItem())
                .add(ModBLocks.SENTINEL_SLAB.get().asItem())
                .add(ModBLocks.PINE_SLAB.get().asItem())
                .add(ModBLocks.IRONWOOD_SLAB.get().asItem())
                .add(ModBLocks.HAWTHORN_SLAB.get().asItem())
                .add(ModBLocks.CHESTNUT_SLAB.get().asItem())
                .add(ModBLocks.CEDAR_SLAB.get().asItem())
                .add(ModBLocks.BEECH_SLAB.get().asItem())
                .add(ModBLocks.ASH_SLAB.get().asItem())
                .add(ModBLocks.BLACKBARK_SLAB.get().asItem())
                .add(ModBLocks.ASPEN_SLAB.get().asItem());

// PRESSURE PLATES
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.SYCAMORE_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.SENTINEL_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.PINE_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.IRONWOOD_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.HAWTHORN_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.CHESTNUT_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.CEDAR_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.BEECH_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.ASH_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.BLACKBARK_PRESSURE_PLATE.get().asItem())
                .add(ModBLocks.ASPEN_PRESSURE_PLATE.get().asItem());

// FENCES
        // FENCES, FENCE GATES, and WALLS
        this.tag(ItemTags.WOODEN_FENCES)
                .add(ModBLocks.WEIRWOOD_FENCE.get().asItem())
                .add(ModBLocks.WEIRWOOD_FENCE_GATE.get().asItem())
                .add(ModBLocks.WEIRWOOD_WALL.get().asItem())

                .add(ModBLocks.SYCAMORE_FENCE.get().asItem())
                .add(ModBLocks.SYCAMORE_FENCE_GATE.get().asItem())
                .add(ModBLocks.SYCAMORE_WALL.get().asItem())

                .add(ModBLocks.SENTINEL_FENCE.get().asItem())
                .add(ModBLocks.SENTINEL_FENCE_GATE.get().asItem())
                .add(ModBLocks.SENTINEL_WALL.get().asItem())

                .add(ModBLocks.PINE_FENCE.get().asItem())
                .add(ModBLocks.PINE_FENCE_GATE.get().asItem())
                .add(ModBLocks.PINE_WALL.get().asItem())

                .add(ModBLocks.IRONWOOD_FENCE.get().asItem())
                .add(ModBLocks.IRONWOOD_FENCE_GATE.get().asItem())
                .add(ModBLocks.IRONWOOD_WALL.get().asItem())

                .add(ModBLocks.HAWTHORN_FENCE.get().asItem())
                .add(ModBLocks.HAWTHORN_FENCE_GATE.get().asItem())
                .add(ModBLocks.HAWTHORN_WALL.get().asItem())

                .add(ModBLocks.CHESTNUT_FENCE.get().asItem())
                .add(ModBLocks.CHESTNUT_FENCE_GATE.get().asItem())
                .add(ModBLocks.CHESTNUT_WALL.get().asItem())

                .add(ModBLocks.CEDAR_FENCE.get().asItem())
                .add(ModBLocks.CEDAR_FENCE_GATE.get().asItem())
                .add(ModBLocks.CEDAR_WALL.get().asItem())

                .add(ModBLocks.BEECH_FENCE.get().asItem())
                .add(ModBLocks.BEECH_FENCE_GATE.get().asItem())
                .add(ModBLocks.BEECH_WALL.get().asItem())

                .add(ModBLocks.ASH_FENCE.get().asItem())
                .add(ModBLocks.ASH_FENCE_GATE.get().asItem())
                .add(ModBLocks.ASH_WALL.get().asItem())

                .add(ModBLocks.BLACKBARK_FENCE.get().asItem())
                .add(ModBLocks.BLACKBARK_FENCE_GATE.get().asItem())
                .add(ModBLocks.BLACKBARK_WALL.get().asItem())

                .add(ModBLocks.ASPEN_FENCE.get().asItem())
                .add(ModBLocks.ASPEN_FENCE_GATE.get().asItem())
                .add(ModBLocks.ASPEN_WALL.get().asItem());


// TRAPDOORS
        this.tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBLocks.WEIRWOOD_TRAPDOOR.get().asItem())
                .add(ModBLocks.SYCAMORE_TRAPDOOR.get().asItem())
                .add(ModBLocks.SENTINEL_TRAPDOOR.get().asItem())
                .add(ModBLocks.PINE_TRAPDOOR.get().asItem())
                .add(ModBLocks.IRONWOOD_TRAPDOOR.get().asItem())
                .add(ModBLocks.HAWTHORN_TRAPDOOR.get().asItem())
                .add(ModBLocks.CHESTNUT_TRAPDOOR.get().asItem())
                .add(ModBLocks.CEDAR_TRAPDOOR.get().asItem())
                .add(ModBLocks.BEECH_TRAPDOOR.get().asItem())
                .add(ModBLocks.ASH_TRAPDOOR.get().asItem())
                .add(ModBLocks.BLACKBARK_TRAPDOOR.get().asItem())
                .add(ModBLocks.ASPEN_TRAPDOOR.get().asItem());

        this.tag(ItemTags.LEAVES)
                .add(ModBLocks.WEIRWOOD_LEAVES.get().asItem())
                .add(ModBLocks.SYCAMORE_LEAVES.get().asItem())
                .add(ModBLocks.SENTINEL_LEAVES.get().asItem())
                .add(ModBLocks.PINE_LEAVES.get().asItem())
                .add(ModBLocks.IRONWOOD_LEAVES.get().asItem())
                .add(ModBLocks.HAWTHORN_LEAVES.get().asItem())
                .add(ModBLocks.CHESTNUT_LEAVES.get().asItem())
                .add(ModBLocks.CEDAR_LEAVES.get().asItem())
                .add(ModBLocks.BEECH_LEAVES.get().asItem())
                .add(ModBLocks.ASH_LEAVES.get().asItem())
                .add(ModBLocks.BLACKBARK_LEAVES.get().asItem())
                .add(ModBLocks.ASPEN_LEAVES.get().asItem());


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
}
