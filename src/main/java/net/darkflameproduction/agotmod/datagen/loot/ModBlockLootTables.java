package net.darkflameproduction.agotmod.datagen.loot;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.block.custom.*;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class ModBlockLootTables extends BlockLootSubProvider {

    private static final List<ItemLike> diamonds = List.of(
            ModItems.YELLOW_DIAMOND.get(),
            ModItems.TRANSPARENT_DIAMOND.get(),
            ModItems.BLACK_DIAMOND.get());

    public ModBlockLootTables(HolderLookup.Provider provider) {
        // Initialize the ModBlockLootTables, specifying sets of known blocks and flags
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);


        /** // ---------------------------(ORES)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        // Add loot table for tin ores
        this.add(ModBLocks.DEEPSLATE_TIN_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()));
        this.add(ModBLocks.TIN_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));
        this.add(ModBLocks.DEEPSLATE_SILVER_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.DEEPSLATE_SILVER_ORE.get(), ModItems.RAW_SILVER.get()));
        this.add(ModBLocks.SILVER_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.SILVER_ORE.get(), ModItems.RAW_SILVER.get()));

        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(GEMS)--------------------------- //
        this.add(ModBLocks.AMBER_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.AMBER_ORE.get(), ModItems.AMBER.get()));
        this.add(ModBLocks.AMBER_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.AMBER_DEEPSLATE_ORE.get(), ModItems.AMBER.get()));

        this.add(ModBLocks.AMETHYST_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.AMETHYST_ORE.get(), ModItems.AMETHYST.get()));
        this.add(ModBLocks.AMETHYST_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.AMETHYST_DEEPSLATE_ORE.get(), ModItems.AMETHYST.get()));

        this.add(ModBLocks.BLOODSTONE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.BLOODSTONE_ORE.get(), ModItems.BLOODSTONE.get()));
        this.add(ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get(), ModItems.BLOODSTONE.get()));

        this.add(ModBLocks.CARNELIAN_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.CARNELIAN_ORE.get(), ModItems.CARNELIAN.get()));
        this.add(ModBLocks.CARNELIAN_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.CARNELIAN_DEEPSLATE_ORE.get(), ModItems.CARNELIAN.get()));

        this.add(ModBLocks.CHALCEDONY_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.CHALCEDONY_ORE.get(), ModItems.CHALCEDONY.get()));
        this.add(ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get(), ModItems.CHALCEDONY.get()));

        this.add(ModBLocks.GARNET_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.GARNET_ORE.get(), ModItems.GARNET.get()));
        this.add(ModBLocks.GARNET_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.GARNET_DEEPSLATE_ORE.get(), ModItems.GARNET.get()));

        this.add(ModBLocks.JADE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.JADE_ORE.get(), ModItems.JADE.get()));
        this.add(ModBLocks.JADE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.JADE_DEEPSLATE_ORE.get(), ModItems.JADE.get()));

        this.add(ModBLocks.JASPER_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.JASPER_ORE.get(), ModItems.JASPER.get()));
        this.add(ModBLocks.JASPER_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.JASPER_DEEPSLATE_ORE.get(), ModItems.JASPER.get()));

        this.add(ModBLocks.MALACHITE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.MALACHITE_ORE.get(), ModItems.MALACHITE.get()));
        this.add(ModBLocks.MALACHITE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.MALACHITE_DEEPSLATE_ORE.get(), ModItems.MALACHITE.get()));

        this.add(ModBLocks.MOONSTONE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.MOONSTONE_ORE.get(), ModItems.MOONSTONE.get()));
        this.add(ModBLocks.MOONSTONE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.MOONSTONE_DEEPSLATE_ORE.get(), ModItems.MOONSTONE.get()));

        this.add(ModBLocks.ONYX_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.ONYX_ORE.get(), ModItems.ONYX.get()));
        this.add(ModBLocks.ONYX_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.ONYX_DEEPSLATE_ORE.get(), ModItems.ONYX.get()));

        this.add(ModBLocks.OPAL_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.OPAL_ORE.get(), ModItems.OPAL.get()));
        this.add(ModBLocks.OPAL_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.OPAL_DEEPSLATE_ORE.get(), ModItems.OPAL.get()));

        this.add(ModBLocks.RUBY_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.RUBY_ORE.get(), ModItems.RUBY.get()));
        this.add(ModBLocks.RUBY_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.RUBY_DEEPSLATE_ORE.get(), ModItems.RUBY.get()));

        this.add(ModBLocks.SAPPHIRE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.SAPPHIRE_ORE.get(), ModItems.SAPPHIRE.get()));
        this.add(ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get(), ModItems.SAPPHIRE.get()));

        this.add(ModBLocks.TIGERS_EYE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.TIGERS_EYE_ORE.get(), ModItems.TIGERS_EYE.get()));
        this.add(ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get(), ModItems.TIGERS_EYE.get()));

        this.add(ModBLocks.TOPAZ_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.TOPAZ_ORE.get(), ModItems.TOPAZ.get()));
        this.add(ModBLocks.TOPAZ_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.TOPAZ_DEEPSLATE_ORE.get(), ModItems.TOPAZ.get()));

        this.add(ModBLocks.TOURMALINE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.TOURMALINE_ORE.get(), ModItems.TOURMALINE.get()));
        this.add(ModBLocks.TOURMALINE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(registrylookup, ModBLocks.TOURMALINE_DEEPSLATE_ORE.get(), ModItems.TOURMALINE.get()));


        this.add(ModBLocks.DIAMONDS_ORE.get(), block -> oreDropBetween1And3(registrylookup, ModBLocks.DIAMONDS_ORE.get(), Items.DIAMOND));
        this.add(ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(), block -> oreDropBetween1And3(registrylookup, ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(), Items.DIAMOND));

        // ---------------------------(GEMS)--------------------------- //


        /** // ---------------------------(ORES)--------------------------- // */

        /** // ---------------------------(NORMAL BLOCKS)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        // Add loot tables for tin blocks

        this.dropSelf(ModBLocks.TIN_BLOCK.get());
        this.dropSelf(ModBLocks.RAW_TIN_BLOCK.get());
        this.dropSelf(ModBLocks.SILVER_BLOCK.get());
        this.dropSelf(ModBLocks.RAW_SILVER_BLOCK.get());

        // ---------------------------(TIN)--------------------------- //
        this.dropSelf(ModBLocks.QUAGMIRE.get());
        // ---------------------------(BRONZE)--------------------------- //
        // Add loot table for bronze block
        this.dropSelf(ModBLocks.BRONZE_BLOCK.get());
        // ---------------------------(BRONZE)--------------------------- //

        // ---------------------------(GEMS)--------------------------- //
        // Add loot table for bronze block
        this.dropSelf(ModBLocks.AMBER_BLOCK.get());
        this.dropSelf(ModBLocks.AMETHYST_BLOCK.get());
        this.dropSelf(ModBLocks.BLACK_DIAMOND_BLOCK.get());
        this.dropSelf(ModBLocks.YELLOW_DIAMOND_BLOCK.get());
        this.dropSelf(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get());
        this.dropSelf(ModBLocks.BLOODSTONE_BLOCK.get());
        this.dropSelf(ModBLocks.CARNELIAN_BLOCK.get());
        this.dropSelf(ModBLocks.CHALCEDONY_BLOCK.get());
        this.dropSelf(ModBLocks.GARNET_BLOCK.get());
        this.dropSelf(ModBLocks.JADE_BLOCK.get());
        this.dropSelf(ModBLocks.JASPER_BLOCK.get());
        this.dropSelf(ModBLocks.MALACHITE_BLOCK.get());
        this.dropSelf(ModBLocks.MOONSTONE_BLOCK.get());
        this.dropSelf(ModBLocks.ONYX_BLOCK.get());
        this.dropSelf(ModBLocks.OPAL_BLOCK.get());
        this.dropSelf(ModBLocks.RUBY_BLOCK.get());
        this.dropSelf(ModBLocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBLocks.TIGERS_EYE_BLOCK.get());
        this.dropSelf(ModBLocks.TOPAZ_BLOCK.get());


        // ---------------------------(GEMS)--------------------------- //

        // ---------------------------(BRICKS)--------------------------- //
        // Add loot tables for various brick blocks
        this.dropSelf(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        this.dropSelf(ModBLocks.DARK_STONE_BRICK.get());
        this.dropSelf(ModBLocks.STONE_BRICK_BUT_COOLER.get());
        // ---------------------------(BRICKS)--------------------------- //

        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        // Add loot table for mint block
        this.dropSelf(ModBLocks.MINT_BLOCK.get());
        // ---------------------(BLOCKS)--------------------- //

        this.dropSelf(ModBLocks.DIRT_STAIRS.get());
        this.add(ModBLocks.DIRT_SLAB.get(), createSlabItemTable(ModBLocks.DIRT_SLAB.get()));
        this.dropSelf(ModBLocks.MUD_STAIRS.get());
        this.add(ModBLocks.MUD_SLAB.get(), createSlabItemTable(ModBLocks.MUD_SLAB.get()));

        this.dropSelf(ModBLocks.COARSE_DIRT_STAIRS.get());
        this.add(ModBLocks.COARSE_DIRT_SLAB.get(), createSlabItemTable(ModBLocks.COARSE_DIRT_SLAB.get()));

        this.dropSelf(ModBLocks.PODZOL_STAIRS.get());
        this.add(ModBLocks.PODZOL_SLAB.get(), createSlabItemTable(ModBLocks.PODZOL_SLAB.get()));

        this.dropSelf(ModBLocks.GRASS_BLOCK_STAIRS.get());
        this.add(ModBLocks.GRASS_BLOCK_SLAB.get(), createSlabItemTable(ModBLocks.GRASS_BLOCK_SLAB.get()));

        this.dropSelf(ModBLocks.DIRT_PATH_STAIRS.get());
        this.add(ModBLocks.DIRT_PATH_SLAB.get(), createSlabItemTable(ModBLocks.DIRT_PATH_SLAB.get()));

        this.dropSelf(ModBLocks.THATCH_BLOCK.get());
        this.dropSelf(ModBLocks.THATCH_STAIRS.get());
        this.add(ModBLocks.THATCH_SLAB.get(), createSlabItemTable(ModBLocks.THATCH_SLAB.get()));

        this.dropSelf(ModBLocks.PEAT.get());
        this.dropSelf(ModBLocks.HEARTH_BLOCK.get());

        // ── Add to your block loot table provider ────────────────────────────────

// ── Add to your block loot table provider ────────────────────────────────

        this.add(ModBLocks.OILY_STONE.get(), this.noDrop());
        this.add(ModBLocks.OILY_STONE_EYE_CIRCLE.get(), this.noDrop());
        this.add(ModBLocks.OILY_STONE_EYE_CIRCLE_SLAB.get(), this.noDrop());
        this.add(ModBLocks.OILY_STONE_EYE_CIRCLE_STAIRS.get(), this.noDrop());
        this.add(ModBLocks.OILY_STONE_EYE_CIRCLE_WALL.get(), this.noDrop());
        this.add(ModBLocks.POLISHED_OILY_STONE.get(), this.noDrop());
        this.add(ModBLocks.OILY_BRICKS.get(), this.noDrop());
        this.add(ModBLocks.CHISELED_OILY_STONE.get(), this.noDrop());
        this.add(ModBLocks.CARVED_OILY_STONE.get(), this.noDrop());
        this.add(ModBLocks.PATTERNED_OILY_STONE.get(), this.noDrop());
        this.add(ModBLocks.OILY_STONE_SLAB.get(), this.noDrop());
        this.add(ModBLocks.OILY_STONE_STAIRS.get(), this.noDrop());
        this.add(ModBLocks.POLISHED_OILY_STONE_SLAB.get(), this.noDrop());
        this.add(ModBLocks.POLISHED_OILY_STONE_STAIRS.get(), this.noDrop());
        this.add(ModBLocks.OILY_BRICKS_SLAB.get(), this.noDrop());
        this.add(ModBLocks.OILY_BRICKS_STAIRS.get(), this.noDrop());
        this.add(ModBLocks.CHISELED_OILY_STONE_SLAB.get(), this.noDrop());
        this.add(ModBLocks.CHISELED_OILY_STONE_STAIRS.get(), this.noDrop());
        this.add(ModBLocks.CARVED_OILY_STONE_SLAB.get(), this.noDrop());
        this.add(ModBLocks.CARVED_OILY_STONE_STAIRS.get(), this.noDrop());
        this.add(ModBLocks.PATTERNED_OILY_STONE_SLAB.get(), this.noDrop());
        this.add(ModBLocks.PATTERNED_OILY_STONE_STAIRS.get(), this.noDrop());
        this.add(ModBLocks.OILY_STONE_WALL.get(), this.noDrop());
        this.add(ModBLocks.POLISHED_OILY_STONE_WALL.get(), this.noDrop());
        this.add(ModBLocks.OILY_BRICKS_WALL.get(), this.noDrop());
        this.add(ModBLocks.CHISELED_OILY_STONE_WALL.get(), this.noDrop());
        this.add(ModBLocks.CARVED_OILY_STONE_WALL.get(), this.noDrop());
        this.add(ModBLocks.PATTERNED_OILY_STONE_WALL.get(), this.noDrop());


        // ---------------------------(TREES)--------------------------- //
        //Weirwood
        this.dropSelf(ModBLocks.WEIRWOOD_LOG.get());
        this.dropSelf(ModBLocks.WEIRWOOD_FACE_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_WEIRWOOD_LOG.get());
        this.dropSelf(ModBLocks.WEIRWOOD_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get());
        this.dropSelf(ModBLocks.WEIRWOOD_PLANKS.get());
        this.dropSelf(ModBLocks.WEIRWOOD_SAPLING.get());

        this.add(ModBLocks.WEIRWOOD_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.WEIRWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.WEIRWOOD_FENCE.get());
        this.dropSelf(ModBLocks.WEIRWOOD_WALL.get());
        this.dropSelf(ModBLocks.OAK_WALL.get());
        this.dropSelf(ModBLocks.SPRUCE_WALL.get());
        this.dropSelf(ModBLocks.BIRCH_WALL.get());
        this.dropSelf(ModBLocks.JUNGLE_WALL.get());
        this.dropSelf(ModBLocks.ACACIA_WALL.get());
        this.dropSelf(ModBLocks.DARK_OAK_WALL.get());
        this.dropSelf(ModBLocks.MANGROVE_WALL.get());
        this.dropSelf(ModBLocks.CHERRY_WALL.get());
        this.dropSelf(ModBLocks.BAMBOO_WALL.get());
        this.dropSelf(ModBLocks.CRIMSON_WALL.get());
        this.dropSelf(ModBLocks.WARPED_WALL.get());
        this.dropSelf(ModBLocks.WEIRWOOD_STAIRS.get());
        this.dropSelf(ModBLocks.WEIRWOOD_BUTTON.get());
        this.dropSelf(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.WEIRWOOD_TRAPDOOR.get());
        this.dropSelf(ModBLocks.WEIRWOOD_FENCE_GATE.get());

        this.add(ModBLocks.WEIRWOOD_DOOR.get(),
                block -> createDoorTable(ModBLocks.WEIRWOOD_DOOR.get()));
        this.add(ModBLocks.WEIRWOOD_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.WEIRWOOD_SLAB.get()));

        //Rotten
        this.dropSelf(ModBLocks.ROTTEN_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_ROTTEN_LOG.get());
        this.dropSelf(ModBLocks.ROTTEN_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_ROTTEN_WOOD.get());
        this.dropSelf(ModBLocks.ROTTEN_PLANKS.get());

        this.dropSelf(ModBLocks.ROTTEN_FENCE.get());
        this.dropSelf(ModBLocks.ROTTEN_WALL.get());
        this.dropSelf(ModBLocks.ROTTEN_STAIRS.get());
        this.dropSelf(ModBLocks.ROTTEN_BUTTON.get());
        this.dropSelf(ModBLocks.ROTTEN_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.ROTTEN_TRAPDOOR.get());
        this.dropSelf(ModBLocks.ROTTEN_FENCE_GATE.get());

        this.add(ModBLocks.ROTTEN_DOOR.get(),
                block -> createDoorTable(ModBLocks.ROTTEN_DOOR.get()));
        this.add(ModBLocks.ROTTEN_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.ROTTEN_SLAB.get()));

//Charred
        this.dropSelf(ModBLocks.CHARRED_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_CHARRED_LOG.get());
        this.dropSelf(ModBLocks.CHARRED_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_CHARRED_WOOD.get());
        this.dropSelf(ModBLocks.CHARRED_PLANKS.get());

        this.dropSelf(ModBLocks.CHARRED_FENCE.get());
        this.dropSelf(ModBLocks.CHARRED_WALL.get());
        this.dropSelf(ModBLocks.CHARRED_STAIRS.get());
        this.dropSelf(ModBLocks.CHARRED_BUTTON.get());
        this.dropSelf(ModBLocks.CHARRED_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.CHARRED_TRAPDOOR.get());
        this.dropSelf(ModBLocks.CHARRED_FENCE_GATE.get());

        this.add(ModBLocks.CHARRED_DOOR.get(),
                block -> createDoorTable(ModBLocks.CHARRED_DOOR.get()));
        this.add(ModBLocks.CHARRED_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.CHARRED_SLAB.get()));

        this.add(ModBLocks.POTTED_WEIRWOOD_SAPLING.get(),
                createPotFlowerItemTable(ModBLocks.WEIRWOOD_SAPLING.get()));

        // Define the wood types
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
                "alder",
                "almond",
                "apple",
                "apricot",
                "baobab",
                "black_cottonwood",
                "blackthorn",
                "blood_orange",
                "bloodwood",
                "blue_mahoe",
                "cottonwood",
                "datepalm",
                "ebony",
                "fig",
                "fireplum",
                "goldenheart",
                "lemon",
                "lime",
                "linden",
                "mahogany",
                "maple",
                "myrrh",
                "nightwood",
                "nutmeg",
                "orange",
                "peach",
                "pear",
                "pecan",
                "persimmon",
                "pink_ivory",
                "plum",
                "pomegranate",
                "purpleheart",
                "redwood",
                "sandalwood",
                "sandbeggar",
                "tigerwood",
                "yew",
                "blue_soldier_pine",
                "soldier_pine"
        };

// Register loot tables for all wood blocks
        for (String woodType : woodTypes) {
            // Basic blocks that drop themselves
            this.dropSelf(ModBLocks.LOGS.get(woodType).get());
            this.dropSelf(ModBLocks.STRIPPED_LOGS.get(woodType).get());
            this.dropSelf(ModBLocks.WOODS.get(woodType).get());
            this.dropSelf(ModBLocks.STRIPPED_WOODS.get(woodType).get());
            this.dropSelf(ModBLocks.PLANKS.get(woodType).get());
            this.dropSelf(ModBLocks.SAPLINGS.get(woodType).get());

            // Leaves with sapling drops
            this.add(ModBLocks.LEAVES.get(woodType).get(),
                    block -> createLeavesDrops(block,
                            ModBLocks.SAPLINGS.get(woodType).get(),
                            NORMAL_LEAVES_SAPLING_CHANCES));

            // More blocks that drop themselves
            this.dropSelf(ModBLocks.FENCES.get(woodType).get());
            this.dropSelf(ModBLocks.WALLS.get(woodType).get());
            this.dropSelf(ModBLocks.STAIRS.get(woodType).get());
            this.dropSelf(ModBLocks.BUTTONS.get(woodType).get());
            this.dropSelf(ModBLocks.PRESSURE_PLATES.get(woodType).get());
            this.dropSelf(ModBLocks.TRAPDOORS.get(woodType).get());
            this.dropSelf(ModBLocks.FENCE_GATES.get(woodType).get());
            this.add(ModBLocks.POTTED_SAPLINGS.get(woodType).get(),
                    createPotFlowerItemTable(ModBLocks.SAPLINGS.get(woodType).get()));
            // Special drop handlers for doors and slabs
            this.add(ModBLocks.DOORS.get(woodType).get(),
                    block -> createDoorTable(ModBLocks.DOORS.get(woodType).get()));
            this.add(ModBLocks.SLABS.get(woodType).get(),
                    block -> createSlabItemTable(ModBLocks.SLABS.get(woodType).get()));

            // Sign blocks
            this.add(ModBLocks.SIGNS.get(woodType).get(),
                    createSingleItemTable(ModBLocks.SIGNS.get(woodType).get()));
            this.add(ModBLocks.WALL_SIGNS.get(woodType).get(),
                    createSingleItemTable(ModBLocks.SIGNS.get(woodType).get()));
            this.add(ModBLocks.HANGING_SIGNS.get(woodType).get(),
                    createSingleItemTable(ModBLocks.HANGING_SIGNS.get(woodType).get()));
            this.add(ModBLocks.WALL_HANGING_SIGNS.get(woodType).get(),
                    createSingleItemTable(ModBLocks.HANGING_SIGNS.get(woodType).get()));
        }
        // ---------------------------(STONE)--------------------------- //
        //redkeep
        this.dropSelf(ModBLocks.REDKEEP_STONE_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_STONE_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_STONE_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_STONE_WALL.get());


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
            for (ModBLocks.BlockSet set : variants.values()) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (String color : ModBLocks.WATTLE_DAUB_COLORS.keySet()) {
            this.dropSelf(ModBLocks.WATTLE_AND_DAUB_PLAIN.get(color).get());
            for (DeferredBlock<Block> block : ModBLocks.WATTLE_AND_DAUB_VARIANTS.get(color).values()) {
                this.dropSelf(block.get());
            }
        }

        // ---------------------------(FLOWERS)--------------------------- //
        this.dropSelf(ModBLocks.WINTER_ROSE.get());
        this.dropSelf(ModBLocks.WILD_RADISH.get());
        this.dropSelf(ModBLocks.WHITE_ROSE.get());
        this.dropSelf(ModBLocks.THORN_BUSH.get());
        this.dropSelf(ModBLocks.THISTLE.get());
        this.dropSelf(ModBLocks.TANSY.get());
        this.dropSelf(ModBLocks.SPICEFLOWER.get());
        this.dropSelf(ModBLocks.SEDGE.get());
        this.dropSelf(ModBLocks.SAFFRON_CROCUS.get());
        this.dropSelf(ModBLocks.ROSE.get());
        this.dropSelf(ModBLocks.POISON_KISSES.get());
        this.dropSelf(ModBLocks.PENNYROYAL.get());
        this.dropSelf(ModBLocks.OPIUM_POPPY_WILD.get());
        this.dropSelf(ModBLocks.NIGHTSHADE.get());
        this.dropSelf(ModBLocks.MOONBLOOM.get());
        this.dropSelf(ModBLocks.LUNGWORT.get());
        this.dropSelf(ModBLocks.LIVERWORT.get());
        this.dropSelf(ModBLocks.LAVENDER.get());
        this.dropSelf(ModBLocks.LADYS_LACE.get());
        this.dropSelf(ModBLocks.GORSE.get());
        this.dropSelf(ModBLocks.GOLDENROD.get());
        this.dropSelf(ModBLocks.GOLDENCUP.get());
        this.dropSelf(ModBLocks.GOATHEAD.get());
        this.dropSelf(ModBLocks.GINGER.get());
        this.dropSelf(ModBLocks.GILLYFLOWER.get());
        this.dropSelf(ModBLocks.FROSTFIRE.get());
        this.dropSelf(ModBLocks.FORGET_ME_NOT.get());
        this.dropSelf(ModBLocks.EVENING_STAR.get());
        this.dropSelf(ModBLocks.DUSKY_ROSE.get());
        this.dropSelf(ModBLocks.DRAGONS_BREATH.get());
        this.dropSelf(ModBLocks.COLDSNAP.get());
        this.dropSelf(ModBLocks.BLUE_ROSE.get());
        this.dropSelf(ModBLocks.BLOODBLOOM.get());
        this.dropSelf(ModBLocks.BLACK_LOTUS.get());
        this.add(ModBLocks.GHOST_GRASS_BLOCK.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Blocks.DIRT))));
        this.add(ModBLocks.GHOST_GRASS.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.WHEAT_SEEDS))));
        this.add(ModBLocks.GHOST_GRASS_MIDDLE.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.WHEAT_SEEDS))));
        this.add(ModBLocks.GHOST_GRASS_TOP.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.WHEAT_SEEDS))));
        this.add(ModBLocks.WINTER_ROSE_BUSH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModBLocks.WINTER_ROSE.get()))));
        this.add(ModBLocks.WHITE_ROSE_BUSH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModBLocks.WHITE_ROSE.get()))));

        this.add(ModBLocks.DUSKY_ROSE_BUSH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModBLocks.DUSKY_ROSE.get()))));

        this.add(ModBLocks.BLUE_ROSE_BUSH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModBLocks.BLUE_ROSE.get()))));

        this.add(ModBLocks.RED_ROSE_BUSH.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModBLocks.ROSE.get()))));

        this.add(ModBLocks.REED.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModBLocks.REED.get()))));

        // ---------------------------(POTTED PLANTS)--------------------------- //
        this.add(ModBLocks.POTTED_WINTER_ROSE.get(), createPotFlowerItemTable(ModBLocks.WINTER_ROSE.get()));
        this.add(ModBLocks.POTTED_WILD_RADISH.get(), createPotFlowerItemTable(ModBLocks.WILD_RADISH.get()));
        this.add(ModBLocks.POTTED_WHITE_ROSE.get(), createPotFlowerItemTable(ModBLocks.WHITE_ROSE.get()));
        this.add(ModBLocks.POTTED_THORN_BUSH.get(), createPotFlowerItemTable(ModBLocks.THORN_BUSH.get()));
        this.add(ModBLocks.POTTED_THISTLE.get(), createPotFlowerItemTable(ModBLocks.THISTLE.get()));
        this.add(ModBLocks.POTTED_TANSY.get(), createPotFlowerItemTable(ModBLocks.TANSY.get()));
        this.add(ModBLocks.POTTED_SPICEFLOWER.get(), createPotFlowerItemTable(ModBLocks.SPICEFLOWER.get()));
        this.add(ModBLocks.POTTED_SEDGE.get(), createPotFlowerItemTable(ModBLocks.SEDGE.get()));
        this.add(ModBLocks.POTTED_SAFFRON_CROCUS.get(), createPotFlowerItemTable(ModBLocks.SAFFRON_CROCUS.get()));
        this.add(ModBLocks.POTTED_ROSE.get(), createPotFlowerItemTable(ModBLocks.ROSE.get()));
        this.add(ModBLocks.POTTED_POISON_KISSES.get(), createPotFlowerItemTable(ModBLocks.POISON_KISSES.get()));
        this.add(ModBLocks.POTTED_PENNYROYAL.get(), createPotFlowerItemTable(ModBLocks.PENNYROYAL.get()));
        this.add(ModBLocks.POTTED_OPIUM_POPPY_WILD.get(), createPotFlowerItemTable(ModBLocks.OPIUM_POPPY_WILD.get()));
        this.add(ModBLocks.POTTED_NIGHTSHADE.get(), createPotFlowerItemTable(ModBLocks.NIGHTSHADE.get()));
        this.add(ModBLocks.POTTED_MOONBLOOM.get(), createPotFlowerItemTable(ModBLocks.MOONBLOOM.get()));
        this.add(ModBLocks.POTTED_LUNGWORT.get(), createPotFlowerItemTable(ModBLocks.LUNGWORT.get()));
        this.add(ModBLocks.POTTED_LIVERWORT.get(), createPotFlowerItemTable(ModBLocks.LIVERWORT.get()));
        this.add(ModBLocks.POTTED_LAVENDER.get(), createPotFlowerItemTable(ModBLocks.LAVENDER.get()));
        this.add(ModBLocks.POTTED_LADYS_LACE.get(), createPotFlowerItemTable(ModBLocks.LADYS_LACE.get()));
        this.add(ModBLocks.POTTED_GORSE.get(), createPotFlowerItemTable(ModBLocks.GORSE.get()));
        this.add(ModBLocks.POTTED_GOLDENROD.get(), createPotFlowerItemTable(ModBLocks.GOLDENROD.get()));
        this.add(ModBLocks.POTTED_GOLDENCUP.get(), createPotFlowerItemTable(ModBLocks.GOLDENCUP.get()));
        this.add(ModBLocks.POTTED_GOATHEAD.get(), createPotFlowerItemTable(ModBLocks.GOATHEAD.get()));
        this.add(ModBLocks.POTTED_GINGER.get(), createPotFlowerItemTable(ModBLocks.GINGER.get()));
        this.add(ModBLocks.POTTED_GILLYFLOWER.get(), createPotFlowerItemTable(ModBLocks.GILLYFLOWER.get()));
        this.add(ModBLocks.POTTED_FROSTFIRE.get(), createPotFlowerItemTable(ModBLocks.FROSTFIRE.get()));
        this.add(ModBLocks.POTTED_FORGET_ME_NOT.get(), createPotFlowerItemTable(ModBLocks.FORGET_ME_NOT.get()));
        this.add(ModBLocks.POTTED_EVENING_STAR.get(), createPotFlowerItemTable(ModBLocks.EVENING_STAR.get()));
        this.add(ModBLocks.POTTED_DUSKY_ROSE.get(), createPotFlowerItemTable(ModBLocks.DUSKY_ROSE.get()));
        this.add(ModBLocks.POTTED_DRAGONS_BREATH.get(), createPotFlowerItemTable(ModBLocks.DRAGONS_BREATH.get()));
        this.add(ModBLocks.POTTED_COLDSNAP.get(), createPotFlowerItemTable(ModBLocks.COLDSNAP.get()));
        this.add(ModBLocks.POTTED_BLUE_ROSE.get(), createPotFlowerItemTable(ModBLocks.BLUE_ROSE.get()));
        this.add(ModBLocks.POTTED_BLOODBLOOM.get(), createPotFlowerItemTable(ModBLocks.BLOODBLOOM.get()));
        this.add(ModBLocks.POTTED_BLACK_LOTUS.get(), createPotFlowerItemTable(ModBLocks.BLACK_LOTUS.get()));

        /** // ---------------------------(SIGNS)--------------------------- // */

        this.add(ModBLocks.WEIRWOOD_SIGN.get(),
                createSingleItemTable(ModBLocks.WEIRWOOD_SIGN.get()));
        this.add(ModBLocks.WEIRWOOD_WALL_SIGN.get(),
                createSingleItemTable(ModBLocks.WEIRWOOD_SIGN.get()));
        this.add(ModBLocks.WEIRWOOD_HANGING_SIGN.get(),
                createSingleItemTable(ModBLocks.WEIRWOOD_HANGING_SIGN.get()));
        this.add(ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModBLocks.WEIRWOOD_HANGING_SIGN.get()));

        //Rotten
        this.add(ModBLocks.ROTTEN_SIGN.get(),
                createSingleItemTable(ModBLocks.ROTTEN_SIGN.get()));
        this.add(ModBLocks.ROTTEN_WALL_SIGN.get(),
                createSingleItemTable(ModBLocks.ROTTEN_SIGN.get()));
        this.add(ModBLocks.ROTTEN_HANGING_SIGN.get(),
                createSingleItemTable(ModBLocks.ROTTEN_HANGING_SIGN.get()));
        this.add(ModBLocks.ROTTEN_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModBLocks.ROTTEN_HANGING_SIGN.get()));

//Charred
        this.add(ModBLocks.CHARRED_SIGN.get(),
                createSingleItemTable(ModBLocks.CHARRED_SIGN.get()));
        this.add(ModBLocks.CHARRED_WALL_SIGN.get(),
                createSingleItemTable(ModBLocks.CHARRED_SIGN.get()));
        this.add(ModBLocks.CHARRED_HANGING_SIGN.get(),
                createSingleItemTable(ModBLocks.CHARRED_HANGING_SIGN.get()));
        this.add(ModBLocks.CHARRED_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModBLocks.CHARRED_HANGING_SIGN.get()));


        /** // ---------------------------(CROPS)--------------------------- // */

        this.add(ModBLocks.HORSERADISH_CROP.get(), cropDrop(
                ModBLocks.HORSERADISH_CROP.get(),
                HorseradishCropBlock.AGE,
                HorseradishCropBlock.MAX_AGE,
                ModItems.HORSERADISH.get(), 0.0F, 3.0F,
                ModItems.HORSERADISH_SEEDS.get()
        ));

        this.add(ModBLocks.BARLEY_CROP.get(), cropDrop(
                ModBLocks.BARLEY_CROP.get(),
                BarleyCropBlock.AGE,
                BarleyCropBlock.MAX_AGE,
                ModItems.BARLEY.get(), 1.0F, 1.0F,
                ModItems.BARLEY_SEEDS.get()
        ));

        this.add(ModBLocks.GARLIC_CROP.get(), cropDrop(
                ModBLocks.GARLIC_CROP.get(),
                GarlicCropBlock.AGE,
                GarlicCropBlock.MAX_AGE,
                ModItems.GARLIC.get(), 0.0F, 3.0F,
                ModItems.GARLIC.get()
        ));

        this.add(ModBLocks.LEEK_CROP.get(), cropDrop(
                ModBLocks.LEEK_CROP.get(),
                LeekCropBlock.AGE,
                LeekCropBlock.MAX_AGE,
                ModItems.LEEK.get(), 0.0F, 3.0F,
                ModItems.LEEK_SEEDS.get()
        ));

        this.add(ModBLocks.NEEP_CROP.get(), cropDrop(
                ModBLocks.NEEP_CROP.get(),
                NeepCropBlock.AGE,
                NeepCropBlock.MAX_AGE,
                ModItems.NEEP.get(), 0.0F, 3.0F,
                ModItems.NEEP_SEEDS.get()
        ));

        this.add(ModBLocks.OAT_CROP.get(), cropDrop(
                ModBLocks.OAT_CROP.get(),
                OatCropBlock.AGE,
                OatCropBlock.MAX_AGE,
                ModItems.OAT.get(), 1.0F, 1.0F,
                ModItems.OAT_SEEDS.get()
        ));

        this.add(ModBLocks.PARSLEY_CROP.get(), cropDrop(
                ModBLocks.PARSLEY_CROP.get(),
                ParsleyCropBlock.AGE,
                ParsleyCropBlock.MAX_AGE,
                ModItems.PARSLEY.get(), 0.0F, 3.0F,
                ModItems.PARSLEY_SEEDS.get()
        ));

        this.add(ModBLocks.RED_ONION_CROP.get(), cropDrop(
                ModBLocks.RED_ONION_CROP.get(),
                RedOnionCropBlock.AGE,
                RedOnionCropBlock.MAX_AGE,
                ModItems.RED_ONION.get(), 0.0F, 3.0F,
                ModItems.RED_ONION_SEEDS.get()
        ));

        this.add(ModBLocks.TURNIP_CROP.get(), cropDrop(
                ModBLocks.TURNIP_CROP.get(),
                TurnipCropBlock.AGE,
                TurnipCropBlock.MAX_AGE,
                ModItems.TURNIP.get(), 0.0F, 3.0F,
                ModItems.TURNIP_SEEDS.get()
        ));

        this.add(ModBLocks.WILD_ONION_CROP.get(), cropDrop(
                ModBLocks.WILD_ONION_CROP.get(),
                WildOnionCropBlock.AGE,
                WildOnionCropBlock.MAX_AGE,
                ModItems.WILD_ONION.get(), 0.0F, 3.0F,
                ModItems.WILD_ONION_SEEDS.get()
        ));

        this.add(ModBLocks.ONION_CROP.get(), cropDrop(
                ModBLocks.ONION_CROP.get(),
                OnionCropBlock.AGE,
                OnionCropBlock.MAX_AGE,
                ModItems.ONION.get(), 0.0F, 3.0F,
                ModItems.ONION_SEEDS.get()
        ));

        this.add(ModBLocks.OPIUM_POPPY_CROP.get(), cropDrop(
                ModBLocks.OPIUM_POPPY_CROP.get(),
                OpiumPoppyCropBlock.AGE,
                OpiumPoppyCropBlock.MAX_AGE,
                ModItems.OPIUM_POPPY_SEEDS.get(), 0.0F, 0.0F,
                ModItems.OPIUM_POPPY_SEEDS.get()
        ));

        this.add(ModBLocks.CABBAGE_CROP.get(), cropDrop(
                ModBLocks.CABBAGE_CROP.get(),
                CabbageCropBlock.AGE,
                CabbageCropBlock.MAX_AGE,
                ModItems.CABBAGE.get(), 1.0F, 1.0F,
                ModItems.CABBAGE_SEEDS.get()
        ));

        this.add(ModBLocks.BEAN_CROP.get(), cropDrop(
                ModBLocks.BEAN_CROP.get(),
                BeanCropBlock.AGE,
                BeanCropBlock.MAX_AGE,
                ModItems.BEAN.get(), 0.0F, 3.0F,
                ModItems.BEAN_SEEDS.get()
        ));

        this.add(ModBLocks.CHICKPEA_CROP.get(), cropDrop(
                ModBLocks.CHICKPEA_CROP.get(),
                ChickpeaCropBlock.AGE,
                ChickpeaCropBlock.MAX_AGE,
                ModItems.CHICKPEA.get(), 0.0F, 3.0F,
                ModItems.CHICKPEA_SEEDS.get()
        ));

        this.add(ModBLocks.CUCUMBER_CROP.get(), cropDrop(
                ModBLocks.CUCUMBER_CROP.get(),
                CucumberCropBlock.AGE,
                CucumberCropBlock.MAX_AGE,
                ModItems.CUCUMBER.get(), 0.0F, 3.0F,
                ModItems.CUCUMBER_SEEDS.get()
        ));

        this.add(ModBLocks.GREEN_BEAN_CROP.get(), cropDrop(
                ModBLocks.GREEN_BEAN_CROP.get(),
                GreenBeanCropBlock.AGE,
                GreenBeanCropBlock.MAX_AGE,
                ModItems.GREEN_BEAN.get(), 0.0F, 3.0F,
                ModItems.GREEN_BEAN_SEEDS.get()
        ));

        this.add(ModBLocks.SPINACH_CROP.get(), cropDrop(
                ModBLocks.SPINACH_CROP.get(),
                SpinachCropBlock.AGE,
                SpinachCropBlock.MAX_AGE,
                ModItems.SPINACH.get(), 0.0F, 3.0F,
                ModItems.SPINACH_SEEDS.get()
        ));

        this.add(ModBLocks.DRAGON_PEPPER_CROP.get(), cropDrop(
                ModBLocks.DRAGON_PEPPER_CROP.get(),
                DragonPepperCropBlock.AGE,
                DragonPepperCropBlock.MAX_AGE,
                ModItems.DRAGON_PEPPER.get(), 0.0F, 3.0F,
                ModItems.DRAGON_PEPPER_SEEDS.get()
        ));

        this.add(ModBLocks.PEPPER_CROP.get(), cropDrop(
                ModBLocks.PEPPER_CROP.get(),
                PepperCropBlock.AGE,
                PepperCropBlock.MAX_AGE,
                ModItems.PEPPER.get(), 0.0F, 3.0F,
                ModItems.PEPPER_SEEDS.get()
        ));

        this.add(ModBLocks.PEPPERCORN_CROP.get(), cropDrop(
                ModBLocks.PEPPERCORN_CROP.get(),
                PeppercornCropBlock.AGE,
                PeppercornCropBlock.MAX_AGE,
                ModItems.PEPPERCORN.get(), 0.0F, 3.0F,
                ModItems.PEPPERCORN_SEEDS.get()
        ));

        this.add(ModBLocks.COTTON_CROP.get(), cropDrop(
                ModBLocks.COTTON_CROP.get(),
                CottonCropBlock.AGE,
                CottonCropBlock.MAX_AGE,
                ModItems.COTTON.get(), 0.0F, 3.0F,
                ModItems.COTTON_SEEDS.get()
        ));

        this.add(ModBLocks.HEMP_CROP.get(), cropDrop(
                ModBLocks.HEMP_CROP.get(),
                HempCropBlock.AGE,
                HempCropBlock.MAX_AGE,
                ModItems.HEMP.get(), 0.0F, 3.0F,
                ModItems.HEMP_SEEDS.get()
        ));

        this.add(ModBLocks.STRAWBERRY_BUSH.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.STRAWBERRY_BUSH.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.STRAWBERRY.get())));

        this.add(ModBLocks.BLACKBERRY_BUSH.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.BLACKBERRY_BUSH.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.BLACKBERRY.get())));

        this.add(ModBLocks.BLUEBERRY_BUSH.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.BLUEBERRY_BUSH.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.BLUEBERRY.get())));

        this.add(ModBLocks.MULBERRY_BUSH.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.MULBERRY_BUSH.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.MULBERRY.get())));

        this.add(ModBLocks.RASPBERRY_BUSH.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.RASPBERRY_BUSH.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.RASPBERRY.get())));

        this.add(ModBLocks.SMOKEBERRY_BUSH.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.SMOKEBERRY_BUSH.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.SMOKEBERRY.get())));

        this.add(ModBLocks.CORN_CROP.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.CORN_CROP.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.CORN_SEEDS.get())));


        this.add(ModBLocks.CORN_CROP_MIDDLE.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.CORN_CROP_MIDDLE.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.CORN_SEEDS.get())));

        this.add(ModBLocks.CORN_CROP_TOP.get(), block ->
                this.applyExplosionDecay(
                        block, berryBushDrop(
                                ModBLocks.CORN_CROP_TOP.get(),
                                SweetBerryBushBlock.AGE,
                                3,
                                ModItems.CORN_SEEDS.get())));


// Town Hall (existing)
        this.dropSelf(ModBLocks.TOWN_HALL.get());

// All Job Barrel Loot Drops
        this.dropSelf(ModBLocks.ALEHOUSE_BARREL.get());
        this.dropSelf(ModBLocks.ARMORSMITH_BARREL.get());
        this.dropSelf(ModBLocks.BAKER_BARREL.get());
        this.dropSelf(ModBLocks.BANKER_BARREL.get());
        this.dropSelf(ModBLocks.BARD_BARREL.get());
        this.dropSelf(ModBLocks.BLACKSMITH_BARREL.get());
        this.dropSelf(ModBLocks.BUILDER_BARREL.get());
        this.dropSelf(ModBLocks.BUTCHER_BARREL.get());
        this.dropSelf(ModBLocks.CARAVAN_MASTER_BARREL.get());
        this.dropSelf(ModBLocks.CARPENTER_BARREL.get());
        this.dropSelf(ModBLocks.CATTLE_HERDER_BARREL.get());
        this.dropSelf(ModBLocks.CHARCOAL_BURNER_BARREL.get());
        this.dropSelf(ModBLocks.CHICKEN_BREEDER_BARREL.get());
        this.dropSelf(ModBLocks.FARMER_BARREL.get());
        this.dropSelf(ModBLocks.GOAT_HERDER_BARREL.get());
        this.dropSelf(ModBLocks.GROCER_BARREL.get());
        this.dropSelf(ModBLocks.GUARD_BARREL.get());
        this.dropSelf(ModBLocks.HERBALIST_BARREL.get());
        this.dropSelf(ModBLocks.HORSE_BREEDER_BARREL.get());
        this.dropSelf(ModBLocks.HUNTER_BARREL.get());
        this.dropSelf(ModBLocks.INNKEEPER_BARREL.get());
        this.dropSelf(ModBLocks.JEWELER_BARREL.get());
        this.dropSelf(ModBLocks.LUMBERJACK_BARREL.get());
        this.dropSelf(ModBLocks.MAESTER_BARREL.get());
        this.dropSelf(ModBLocks.MINER_BARREL.get());
        this.dropSelf(ModBLocks.PIG_BREEDER_BARREL.get());
        this.dropSelf(ModBLocks.PYROMANCER_BARREL.get());
        this.dropSelf(ModBLocks.QUARRY_BARREL.get());
        this.dropSelf(ModBLocks.SCRIBE_BARREL.get());
        this.dropSelf(ModBLocks.SEPTON_BARREL.get());
        this.dropSelf(ModBLocks.SHEEP_HERDER_BARREL.get());
        this.dropSelf(ModBLocks.SHIPWRIGHT_BARREL.get());
        this.dropSelf(ModBLocks.SLAVER_BARREL.get());
        this.dropSelf(ModBLocks.SMELTER_BARREL.get());
        this.dropSelf(ModBLocks.STONEMASON_BARREL.get());
        this.dropSelf(ModBLocks.SWORDSMITH_BARREL.get());
        this.dropSelf(ModBLocks.TAILOR_BARREL.get());
        this.dropSelf(ModBLocks.TANNER_BARREL.get());
        this.dropSelf(ModBLocks.TRADER_BARREL.get());

        this.dropSelf(ModBLocks.BARREL_LARGE.get());
        this.dropSelf(ModBLocks.BARREL_MEDIUM.get());
        this.dropSelf(ModBLocks.BARREL_SMALL.get());
        this.add(ModBLocks.BARREL_DUMMY.get(), this.noDrop());


        this.dropSelf(ModBLocks.DARK_OAK_TABLE.get());
        this.dropSelf(ModBLocks.OAK_TABLE.get());
        this.dropSelf(ModBLocks.SPRUCE_TABLE.get());
        this.dropSelf(ModBLocks.BIRCH_TABLE.get());
        this.dropSelf(ModBLocks.JUNGLE_TABLE.get());
        this.dropSelf(ModBLocks.ACACIA_TABLE.get());
        this.dropSelf(ModBLocks.MANGROVE_TABLE.get());
        this.dropSelf(ModBLocks.CHERRY_TABLE.get());
        this.dropSelf(ModBLocks.BAMBOO_TABLE.get());
        this.dropSelf(ModBLocks.CRIMSON_TABLE.get());
        this.dropSelf(ModBLocks.WARPED_TABLE.get());
        this.dropSelf(ModBLocks.WEIRWOOD_TABLE.get());
        this.dropSelf(ModBLocks.CHARRED_TABLE.get());
        this.dropSelf(ModBLocks.ROTTEN_TABLE.get());
        this.dropSelf(ModBLocks.PINE_TABLE.get());
        this.dropSelf(ModBLocks.SOLDIER_PINE_TABLE.get());
        this.dropSelf(ModBLocks.BLUE_SOLDIER_PINE_TABLE.get());
        this.dropSelf(ModBLocks.ASH_TABLE.get());
        this.dropSelf(ModBLocks.BEECH_TABLE.get());
        this.dropSelf(ModBLocks.CEDAR_TABLE.get());
        this.dropSelf(ModBLocks.CHESTNUT_TABLE.get());
        this.dropSelf(ModBLocks.HAWTHORN_TABLE.get());
        this.dropSelf(ModBLocks.IRONWOOD_TABLE.get());
        this.dropSelf(ModBLocks.SENTINEL_TABLE.get());
        this.dropSelf(ModBLocks.SYCAMORE_TABLE.get());
        this.dropSelf(ModBLocks.BLACKBARK_TABLE.get());
        this.dropSelf(ModBLocks.ASPEN_TABLE.get());
        this.dropSelf(ModBLocks.ALDER_TABLE.get());
        this.dropSelf(ModBLocks.BLACK_CHERRY_TABLE.get());
        this.dropSelf(ModBLocks.BLACK_OLIVE_TABLE.get());
        this.dropSelf(ModBLocks.CRABAPPLE_TABLE.get());
        this.dropSelf(ModBLocks.OLIVE_TABLE.get());
        this.dropSelf(ModBLocks.WHITE_CHERRY_TABLE.get());
        this.dropSelf(ModBLocks.RED_CHERRY_TABLE.get());
        this.dropSelf(ModBLocks.FIR_TABLE.get());
        this.dropSelf(ModBLocks.WILLOW_TABLE.get());
        this.dropSelf(ModBLocks.WORMTREE_TABLE.get());
        this.dropSelf(ModBLocks.ALMOND_TABLE.get());
        this.dropSelf(ModBLocks.APPLE_TABLE.get());
        this.dropSelf(ModBLocks.APRICOT_TABLE.get());
        this.dropSelf(ModBLocks.BAOBAB_TABLE.get());
        this.dropSelf(ModBLocks.BLACK_COTTONWOOD_TABLE.get());
        this.dropSelf(ModBLocks.BLACKTHORN_TABLE.get());
        this.dropSelf(ModBLocks.BLOOD_ORANGE_TABLE.get());
        this.dropSelf(ModBLocks.BLOODWOOD_TABLE.get());
        this.dropSelf(ModBLocks.BLUE_MAHOE_TABLE.get());
        this.dropSelf(ModBLocks.COTTONWOOD_TABLE.get());
        this.dropSelf(ModBLocks.DATEPALM_TABLE.get());
        this.dropSelf(ModBLocks.EBONY_TABLE.get());
        this.dropSelf(ModBLocks.FIG_TABLE.get());
        this.dropSelf(ModBLocks.FIREPLUM_TABLE.get());
        this.dropSelf(ModBLocks.GOLDENHEART_TABLE.get());
        this.dropSelf(ModBLocks.LEMON_TABLE.get());
        this.dropSelf(ModBLocks.LIME_TABLE.get());
        this.dropSelf(ModBLocks.LINDEN_TABLE.get());
        this.dropSelf(ModBLocks.MAHOGANY_TABLE.get());
        this.dropSelf(ModBLocks.MAPLE_TABLE.get());
        this.dropSelf(ModBLocks.MYRRH_TABLE.get());
        this.dropSelf(ModBLocks.NIGHTWOOD_TABLE.get());
        this.dropSelf(ModBLocks.NUTMEG_TABLE.get());
        this.dropSelf(ModBLocks.ORANGE_TABLE.get());
        this.dropSelf(ModBLocks.PEACH_TABLE.get());
        this.dropSelf(ModBLocks.PEAR_TABLE.get());
        this.dropSelf(ModBLocks.PECAN_TABLE.get());
        this.dropSelf(ModBLocks.PERSIMMON_TABLE.get());
        this.dropSelf(ModBLocks.PINK_IVORY_TABLE.get());
        this.dropSelf(ModBLocks.PLUM_TABLE.get());
        this.dropSelf(ModBLocks.POMEGRANATE_TABLE.get());
        this.dropSelf(ModBLocks.PURPLEHEART_TABLE.get());
        this.dropSelf(ModBLocks.REDWOOD_TABLE.get());
        this.dropSelf(ModBLocks.SANDALWOOD_TABLE.get());
        this.dropSelf(ModBLocks.SANDBEGGAR_TABLE.get());
        this.dropSelf(ModBLocks.TIGERWOOD_TABLE.get());
        this.dropSelf(ModBLocks.YEW_TABLE.get());

// ── CHAIRS ────────────────────────────────────────────────────────────────
        this.dropSelf(ModBLocks.DARK_OAK_CHAIR.get());
        this.dropSelf(ModBLocks.OAK_CHAIR.get());
        this.dropSelf(ModBLocks.SPRUCE_CHAIR.get());
        this.dropSelf(ModBLocks.BIRCH_CHAIR.get());
        this.dropSelf(ModBLocks.JUNGLE_CHAIR.get());
        this.dropSelf(ModBLocks.ACACIA_CHAIR.get());
        this.dropSelf(ModBLocks.MANGROVE_CHAIR.get());
        this.dropSelf(ModBLocks.CHERRY_CHAIR.get());
        this.dropSelf(ModBLocks.BAMBOO_CHAIR.get());
        this.dropSelf(ModBLocks.CRIMSON_CHAIR.get());
        this.dropSelf(ModBLocks.WARPED_CHAIR.get());
        this.dropSelf(ModBLocks.WEIRWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.CHARRED_CHAIR.get());
        this.dropSelf(ModBLocks.ROTTEN_CHAIR.get());
        this.dropSelf(ModBLocks.PINE_CHAIR.get());
        this.dropSelf(ModBLocks.SOLDIER_PINE_CHAIR.get());
        this.dropSelf(ModBLocks.BLUE_SOLDIER_PINE_CHAIR.get());
        this.dropSelf(ModBLocks.ASH_CHAIR.get());
        this.dropSelf(ModBLocks.BEECH_CHAIR.get());
        this.dropSelf(ModBLocks.CEDAR_CHAIR.get());
        this.dropSelf(ModBLocks.CHESTNUT_CHAIR.get());
        this.dropSelf(ModBLocks.HAWTHORN_CHAIR.get());
        this.dropSelf(ModBLocks.IRONWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.SENTINEL_CHAIR.get());
        this.dropSelf(ModBLocks.SYCAMORE_CHAIR.get());
        this.dropSelf(ModBLocks.BLACKBARK_CHAIR.get());
        this.dropSelf(ModBLocks.ASPEN_CHAIR.get());
        this.dropSelf(ModBLocks.ALDER_CHAIR.get());
        this.dropSelf(ModBLocks.BLACK_CHERRY_CHAIR.get());
        this.dropSelf(ModBLocks.BLACK_OLIVE_CHAIR.get());
        this.dropSelf(ModBLocks.CRABAPPLE_CHAIR.get());
        this.dropSelf(ModBLocks.OLIVE_CHAIR.get());
        this.dropSelf(ModBLocks.WHITE_CHERRY_CHAIR.get());
        this.dropSelf(ModBLocks.RED_CHERRY_CHAIR.get());
        this.dropSelf(ModBLocks.FIR_CHAIR.get());
        this.dropSelf(ModBLocks.WILLOW_CHAIR.get());
        this.dropSelf(ModBLocks.WORMTREE_CHAIR.get());
        this.dropSelf(ModBLocks.ALMOND_CHAIR.get());
        this.dropSelf(ModBLocks.APPLE_CHAIR.get());
        this.dropSelf(ModBLocks.APRICOT_CHAIR.get());
        this.dropSelf(ModBLocks.BAOBAB_CHAIR.get());
        this.dropSelf(ModBLocks.BLACK_COTTONWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.BLACKTHORN_CHAIR.get());
        this.dropSelf(ModBLocks.BLOOD_ORANGE_CHAIR.get());
        this.dropSelf(ModBLocks.BLOODWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.BLUE_MAHOE_CHAIR.get());
        this.dropSelf(ModBLocks.COTTONWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.DATEPALM_CHAIR.get());
        this.dropSelf(ModBLocks.EBONY_CHAIR.get());
        this.dropSelf(ModBLocks.FIG_CHAIR.get());
        this.dropSelf(ModBLocks.FIREPLUM_CHAIR.get());
        this.dropSelf(ModBLocks.GOLDENHEART_CHAIR.get());
        this.dropSelf(ModBLocks.LEMON_CHAIR.get());
        this.dropSelf(ModBLocks.LIME_CHAIR.get());
        this.dropSelf(ModBLocks.LINDEN_CHAIR.get());
        this.dropSelf(ModBLocks.MAHOGANY_CHAIR.get());
        this.dropSelf(ModBLocks.MAPLE_CHAIR.get());
        this.dropSelf(ModBLocks.MYRRH_CHAIR.get());
        this.dropSelf(ModBLocks.NIGHTWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.NUTMEG_CHAIR.get());
        this.dropSelf(ModBLocks.ORANGE_CHAIR.get());
        this.dropSelf(ModBLocks.PEACH_CHAIR.get());
        this.dropSelf(ModBLocks.PEAR_CHAIR.get());
        this.dropSelf(ModBLocks.PECAN_CHAIR.get());
        this.dropSelf(ModBLocks.PERSIMMON_CHAIR.get());
        this.dropSelf(ModBLocks.PINK_IVORY_CHAIR.get());
        this.dropSelf(ModBLocks.PLUM_CHAIR.get());
        this.dropSelf(ModBLocks.POMEGRANATE_CHAIR.get());
        this.dropSelf(ModBLocks.PURPLEHEART_CHAIR.get());
        this.dropSelf(ModBLocks.REDWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.SANDALWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.SANDBEGGAR_CHAIR.get());
        this.dropSelf(ModBLocks.TIGERWOOD_CHAIR.get());
        this.dropSelf(ModBLocks.YEW_CHAIR.get());

// ── ARM CHAIRS ────────────────────────────────────────────────────────────
        this.dropSelf(ModBLocks.DARK_OAK_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.OAK_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.SPRUCE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BIRCH_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.JUNGLE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.ACACIA_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.MANGROVE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.CHERRY_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BAMBOO_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.CRIMSON_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.WARPED_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.WEIRWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.CHARRED_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.ROTTEN_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PINE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.SOLDIER_PINE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLUE_SOLDIER_PINE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.ASH_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BEECH_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.CEDAR_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.CHESTNUT_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.HAWTHORN_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.IRONWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.SENTINEL_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.SYCAMORE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLACKBARK_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.ASPEN_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.ALDER_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLACK_CHERRY_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLACK_OLIVE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.CRABAPPLE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.OLIVE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.WHITE_CHERRY_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.RED_CHERRY_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.FIR_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.WILLOW_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.WORMTREE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.ALMOND_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.APPLE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.APRICOT_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BAOBAB_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLACK_COTTONWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLACKTHORN_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLOOD_ORANGE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLOODWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.BLUE_MAHOE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.COTTONWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.DATEPALM_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.EBONY_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.FIG_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.FIREPLUM_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.GOLDENHEART_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.LEMON_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.LIME_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.LINDEN_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.MAHOGANY_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.MAPLE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.MYRRH_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.NIGHTWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.NUTMEG_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.ORANGE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PEACH_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PEAR_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PECAN_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PERSIMMON_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PINK_IVORY_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PLUM_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.POMEGRANATE_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.PURPLEHEART_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.REDWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.SANDALWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.SANDBEGGAR_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.TIGERWOOD_ARM_CHAIR.get());
        this.dropSelf(ModBLocks.YEW_ARM_CHAIR.get());

// ── STOOLS ────────────────────────────────────────────────────────────────
        this.dropSelf(ModBLocks.DARK_OAK_STOOL.get());
        this.dropSelf(ModBLocks.OAK_STOOL.get());
        this.dropSelf(ModBLocks.SPRUCE_STOOL.get());
        this.dropSelf(ModBLocks.BIRCH_STOOL.get());
        this.dropSelf(ModBLocks.JUNGLE_STOOL.get());
        this.dropSelf(ModBLocks.ACACIA_STOOL.get());
        this.dropSelf(ModBLocks.MANGROVE_STOOL.get());
        this.dropSelf(ModBLocks.CHERRY_STOOL.get());
        this.dropSelf(ModBLocks.BAMBOO_STOOL.get());
        this.dropSelf(ModBLocks.CRIMSON_STOOL.get());
        this.dropSelf(ModBLocks.WARPED_STOOL.get());
        this.dropSelf(ModBLocks.WEIRWOOD_STOOL.get());
        this.dropSelf(ModBLocks.CHARRED_STOOL.get());
        this.dropSelf(ModBLocks.ROTTEN_STOOL.get());
        this.dropSelf(ModBLocks.PINE_STOOL.get());
        this.dropSelf(ModBLocks.SOLDIER_PINE_STOOL.get());
        this.dropSelf(ModBLocks.BLUE_SOLDIER_PINE_STOOL.get());
        this.dropSelf(ModBLocks.ASH_STOOL.get());
        this.dropSelf(ModBLocks.BEECH_STOOL.get());
        this.dropSelf(ModBLocks.CEDAR_STOOL.get());
        this.dropSelf(ModBLocks.CHESTNUT_STOOL.get());
        this.dropSelf(ModBLocks.HAWTHORN_STOOL.get());
        this.dropSelf(ModBLocks.IRONWOOD_STOOL.get());
        this.dropSelf(ModBLocks.SENTINEL_STOOL.get());
        this.dropSelf(ModBLocks.SYCAMORE_STOOL.get());
        this.dropSelf(ModBLocks.BLACKBARK_STOOL.get());
        this.dropSelf(ModBLocks.ASPEN_STOOL.get());
        this.dropSelf(ModBLocks.ALDER_STOOL.get());
        this.dropSelf(ModBLocks.BLACK_CHERRY_STOOL.get());
        this.dropSelf(ModBLocks.BLACK_OLIVE_STOOL.get());
        this.dropSelf(ModBLocks.CRABAPPLE_STOOL.get());
        this.dropSelf(ModBLocks.OLIVE_STOOL.get());
        this.dropSelf(ModBLocks.WHITE_CHERRY_STOOL.get());
        this.dropSelf(ModBLocks.RED_CHERRY_STOOL.get());
        this.dropSelf(ModBLocks.FIR_STOOL.get());
        this.dropSelf(ModBLocks.WILLOW_STOOL.get());
        this.dropSelf(ModBLocks.WORMTREE_STOOL.get());
        this.dropSelf(ModBLocks.ALMOND_STOOL.get());
        this.dropSelf(ModBLocks.APPLE_STOOL.get());
        this.dropSelf(ModBLocks.APRICOT_STOOL.get());
        this.dropSelf(ModBLocks.BAOBAB_STOOL.get());
        this.dropSelf(ModBLocks.BLACK_COTTONWOOD_STOOL.get());
        this.dropSelf(ModBLocks.BLACKTHORN_STOOL.get());
        this.dropSelf(ModBLocks.BLOOD_ORANGE_STOOL.get());
        this.dropSelf(ModBLocks.BLOODWOOD_STOOL.get());
        this.dropSelf(ModBLocks.BLUE_MAHOE_STOOL.get());
        this.dropSelf(ModBLocks.COTTONWOOD_STOOL.get());
        this.dropSelf(ModBLocks.DATEPALM_STOOL.get());
        this.dropSelf(ModBLocks.EBONY_STOOL.get());
        this.dropSelf(ModBLocks.FIG_STOOL.get());
        this.dropSelf(ModBLocks.FIREPLUM_STOOL.get());
        this.dropSelf(ModBLocks.GOLDENHEART_STOOL.get());
        this.dropSelf(ModBLocks.LEMON_STOOL.get());
        this.dropSelf(ModBLocks.LIME_STOOL.get());
        this.dropSelf(ModBLocks.LINDEN_STOOL.get());
        this.dropSelf(ModBLocks.MAHOGANY_STOOL.get());
        this.dropSelf(ModBLocks.MAPLE_STOOL.get());
        this.dropSelf(ModBLocks.MYRRH_STOOL.get());
        this.dropSelf(ModBLocks.NIGHTWOOD_STOOL.get());
        this.dropSelf(ModBLocks.NUTMEG_STOOL.get());
        this.dropSelf(ModBLocks.ORANGE_STOOL.get());
        this.dropSelf(ModBLocks.PEACH_STOOL.get());
        this.dropSelf(ModBLocks.PEAR_STOOL.get());
        this.dropSelf(ModBLocks.PECAN_STOOL.get());
        this.dropSelf(ModBLocks.PERSIMMON_STOOL.get());
        this.dropSelf(ModBLocks.PINK_IVORY_STOOL.get());
        this.dropSelf(ModBLocks.PLUM_STOOL.get());
        this.dropSelf(ModBLocks.POMEGRANATE_STOOL.get());
        this.dropSelf(ModBLocks.PURPLEHEART_STOOL.get());
        this.dropSelf(ModBLocks.REDWOOD_STOOL.get());
        this.dropSelf(ModBLocks.SANDALWOOD_STOOL.get());
        this.dropSelf(ModBLocks.SANDBEGGAR_STOOL.get());
        this.dropSelf(ModBLocks.TIGERWOOD_STOOL.get());
        this.dropSelf(ModBLocks.YEW_STOOL.get());







    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        // Retrieve known blocks from the ModBLocks registry
        return ModBLocks.BLOCKS.getEntries().stream().map(h -> (Block) h.get()).toList();
    }

    protected LootTable.Builder oreDropBetween2And5(HolderLookup.RegistryLookup<Enchantment> registrylookup, Block pBlock, Item item) {
        // Create a loot table for ore drops with random count between 2 and 5
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))).apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected LootTable.Builder oreDropBetween1And3(HolderLookup.RegistryLookup<Enchantment> registrylookup, Block pBlock, Item item) {
        // Create a loot table for ore drops with random count between 1 and 3
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected LootTable.Builder cropDrop(Block cropBlock, IntegerProperty ageProperty, int maxAge, Item dropItem, float minDrop, float maxDrop, Item seedItem) {
        LootItemCondition.Builder fullyGrown = LootItemBlockStatePropertyCondition.hasBlockStateProperties(cropBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge));

        LootItemCondition.Builder notFullyGrown = InvertedLootItemCondition.invert(fullyGrown);

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(dropItem)
                                .when(fullyGrown)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrop, maxDrop)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(seedItem)
                                .when(fullyGrown)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(seedItem)
                                .when(notFullyGrown)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        )
                );
    }

    protected LootTable.Builder berryBushDrop(Block bushBlock, IntegerProperty ageProperty, int maxAge, Item berryItem) {
        LootItemCondition.Builder maxAgeCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(bushBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge));

        LootItemCondition.Builder mediumGrowthCondition = LootItemBlockStatePropertyCondition.hasBlockStateProperties(bushBlock)
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(ageProperty, maxAge - 1));

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(maxAgeCondition)
                        .add(LootItem.lootTableItem(berryItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        )
                )
                .withPool(LootPool.lootPool()
                        .when(mediumGrowthCondition)
                        .add(LootItem.lootTableItem(berryItem)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        )
                );
    }



}

