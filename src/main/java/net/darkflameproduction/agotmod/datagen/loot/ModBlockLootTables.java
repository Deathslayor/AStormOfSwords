package net.darkflameproduction.agotmod.datagen.loot;

import net.darkflameproduction.agotmod.block.ModBLocks;
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
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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
        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //

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
        this.dropSelf(ModBLocks.WEIRWOOD_STAIRS.get());
        this.dropSelf(ModBLocks.WEIRWOOD_BUTTON.get());
        this.dropSelf(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.WEIRWOOD_TRAPDOOR.get());
        this.dropSelf(ModBLocks.WEIRWOOD_FENCE_GATE.get());

        this.add(ModBLocks.WEIRWOOD_DOOR.get(),
                block -> createDoorTable(ModBLocks.WEIRWOOD_DOOR.get()));
        this.add(ModBLocks.WEIRWOOD_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.WEIRWOOD_SLAB.get()));

        //Sycamore
        this.dropSelf(ModBLocks.SYCAMORE_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_SYCAMORE_LOG.get());
        this.dropSelf(ModBLocks.SYCAMORE_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_SYCAMORE_WOOD.get());
        this.dropSelf(ModBLocks.SYCAMORE_PLANKS.get());
        this.dropSelf(ModBLocks.SYCAMORE_SAPLING.get());

        this.add(ModBLocks.SYCAMORE_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.SYCAMORE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.SYCAMORE_FENCE.get());
        this.dropSelf(ModBLocks.SYCAMORE_WALL.get());
        this.dropSelf(ModBLocks.SYCAMORE_STAIRS.get());
        this.dropSelf(ModBLocks.SYCAMORE_BUTTON.get());
        this.dropSelf(ModBLocks.SYCAMORE_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.SYCAMORE_TRAPDOOR.get());
        this.dropSelf(ModBLocks.SYCAMORE_FENCE_GATE.get());

        this.add(ModBLocks.SYCAMORE_DOOR.get(),
                block -> createDoorTable(ModBLocks.SYCAMORE_DOOR.get()));
        this.add(ModBLocks.SYCAMORE_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.SYCAMORE_SLAB.get()));

        //Sentinel
        this.dropSelf(ModBLocks.SENTINEL_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_SENTINEL_LOG.get());
        this.dropSelf(ModBLocks.SENTINEL_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_SENTINEL_WOOD.get());
        this.dropSelf(ModBLocks.SENTINEL_PLANKS.get());
        this.dropSelf(ModBLocks.SENTINEL_SAPLING.get());

        this.add(ModBLocks.SENTINEL_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.SENTINEL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.SENTINEL_FENCE.get());
        this.dropSelf(ModBLocks.SENTINEL_WALL.get());
        this.dropSelf(ModBLocks.SENTINEL_STAIRS.get());
        this.dropSelf(ModBLocks.SENTINEL_BUTTON.get());
        this.dropSelf(ModBLocks.SENTINEL_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.SENTINEL_TRAPDOOR.get());
        this.dropSelf(ModBLocks.SENTINEL_FENCE_GATE.get());

        this.add(ModBLocks.SENTINEL_DOOR.get(),
                block -> createDoorTable(ModBLocks.SENTINEL_DOOR.get()));
        this.add(ModBLocks.SENTINEL_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.SENTINEL_SLAB.get()));


        //Pine
        this.dropSelf(ModBLocks.PINE_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_PINE_LOG.get());
        this.dropSelf(ModBLocks.PINE_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_PINE_WOOD.get());
        this.dropSelf(ModBLocks.PINE_PLANKS.get());
        this.dropSelf(ModBLocks.PINE_SAPLING.get());

        this.add(ModBLocks.PINE_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.PINE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.PINE_FENCE.get());
        this.dropSelf(ModBLocks.PINE_WALL.get());
        this.dropSelf(ModBLocks.PINE_STAIRS.get());
        this.dropSelf(ModBLocks.PINE_BUTTON.get());
        this.dropSelf(ModBLocks.PINE_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.PINE_TRAPDOOR.get());
        this.dropSelf(ModBLocks.PINE_FENCE_GATE.get());

        this.add(ModBLocks.PINE_DOOR.get(),
                block -> createDoorTable(ModBLocks.PINE_DOOR.get()));
        this.add(ModBLocks.PINE_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.PINE_SLAB.get()));

        //Ironwood
        this.dropSelf(ModBLocks.IRONWOOD_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_IRONWOOD_LOG.get());
        this.dropSelf(ModBLocks.IRONWOOD_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_IRONWOOD_WOOD.get());
        this.dropSelf(ModBLocks.IRONWOOD_PLANKS.get());
        this.dropSelf(ModBLocks.IRONWOOD_SAPLING.get());

        this.add(ModBLocks.IRONWOOD_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.IRONWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.IRONWOOD_FENCE.get());
        this.dropSelf(ModBLocks.IRONWOOD_WALL.get());
        this.dropSelf(ModBLocks.IRONWOOD_STAIRS.get());
        this.dropSelf(ModBLocks.IRONWOOD_BUTTON.get());
        this.dropSelf(ModBLocks.IRONWOOD_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.IRONWOOD_TRAPDOOR.get());
        this.dropSelf(ModBLocks.IRONWOOD_FENCE_GATE.get());

        this.add(ModBLocks.IRONWOOD_DOOR.get(),
                block -> createDoorTable(ModBLocks.IRONWOOD_DOOR.get()));
        this.add(ModBLocks.IRONWOOD_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.IRONWOOD_SLAB.get()));

        //Hawthorn
        this.dropSelf(ModBLocks.HAWTHORN_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_HAWTHORN_LOG.get());
        this.dropSelf(ModBLocks.HAWTHORN_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_HAWTHORN_WOOD.get());
        this.dropSelf(ModBLocks.HAWTHORN_PLANKS.get());
        this.dropSelf(ModBLocks.HAWTHORN_SAPLING.get());

        this.add(ModBLocks.HAWTHORN_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.HAWTHORN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.HAWTHORN_FENCE.get());
        this.dropSelf(ModBLocks.HAWTHORN_WALL.get());
        this.dropSelf(ModBLocks.HAWTHORN_STAIRS.get());
        this.dropSelf(ModBLocks.HAWTHORN_BUTTON.get());
        this.dropSelf(ModBLocks.HAWTHORN_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.HAWTHORN_TRAPDOOR.get());
        this.dropSelf(ModBLocks.HAWTHORN_FENCE_GATE.get());

        this.add(ModBLocks.HAWTHORN_DOOR.get(),
                block -> createDoorTable(ModBLocks.HAWTHORN_DOOR.get()));
        this.add(ModBLocks.HAWTHORN_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.HAWTHORN_SLAB.get()));


        //Chestnut
        this.dropSelf(ModBLocks.CHESTNUT_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_CHESTNUT_LOG.get());
        this.dropSelf(ModBLocks.CHESTNUT_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_CHESTNUT_WOOD.get());
        this.dropSelf(ModBLocks.CHESTNUT_PLANKS.get());
        this.dropSelf(ModBLocks.CHESTNUT_SAPLING.get());

        this.add(ModBLocks.CHESTNUT_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.CHESTNUT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.CHESTNUT_FENCE.get());
        this.dropSelf(ModBLocks.CHESTNUT_WALL.get());
        this.dropSelf(ModBLocks.CHESTNUT_STAIRS.get());
        this.dropSelf(ModBLocks.CHESTNUT_BUTTON.get());
        this.dropSelf(ModBLocks.CHESTNUT_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.CHESTNUT_TRAPDOOR.get());
        this.dropSelf(ModBLocks.CHESTNUT_FENCE_GATE.get());

        this.add(ModBLocks.CHESTNUT_DOOR.get(),
                block -> createDoorTable(ModBLocks.CHESTNUT_DOOR.get()));
        this.add(ModBLocks.CHESTNUT_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.CHESTNUT_SLAB.get()));


        //Cedar
        this.dropSelf(ModBLocks.CEDAR_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_CEDAR_LOG.get());
        this.dropSelf(ModBLocks.CEDAR_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_CEDAR_WOOD.get());
        this.dropSelf(ModBLocks.CEDAR_PLANKS.get());
        this.dropSelf(ModBLocks.CEDAR_SAPLING.get());

        this.add(ModBLocks.CEDAR_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.CEDAR_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.CEDAR_FENCE.get());
        this.dropSelf(ModBLocks.CEDAR_WALL.get());
        this.dropSelf(ModBLocks.CEDAR_STAIRS.get());
        this.dropSelf(ModBLocks.CEDAR_BUTTON.get());
        this.dropSelf(ModBLocks.CEDAR_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.CEDAR_TRAPDOOR.get());
        this.dropSelf(ModBLocks.CEDAR_FENCE_GATE.get());

        this.add(ModBLocks.CEDAR_DOOR.get(),
                block -> createDoorTable(ModBLocks.CEDAR_DOOR.get()));
        this.add(ModBLocks.CEDAR_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.CEDAR_SLAB.get()));


        //Beech
        this.dropSelf(ModBLocks.BEECH_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_BEECH_LOG.get());
        this.dropSelf(ModBLocks.BEECH_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_BEECH_WOOD.get());
        this.dropSelf(ModBLocks.BEECH_PLANKS.get());
        this.dropSelf(ModBLocks.BEECH_SAPLING.get());

        this.add(ModBLocks.BEECH_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.BEECH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.BEECH_FENCE.get());
        this.dropSelf(ModBLocks.BEECH_WALL.get());
        this.dropSelf(ModBLocks.BEECH_STAIRS.get());
        this.dropSelf(ModBLocks.BEECH_BUTTON.get());
        this.dropSelf(ModBLocks.BEECH_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.BEECH_TRAPDOOR.get());
        this.dropSelf(ModBLocks.BEECH_FENCE_GATE.get());

        this.add(ModBLocks.BEECH_DOOR.get(),
                block -> createDoorTable(ModBLocks.BEECH_DOOR.get()));
        this.add(ModBLocks.BEECH_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.BEECH_SLAB.get()));


        //Ash
        this.dropSelf(ModBLocks.ASH_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_ASH_LOG.get());
        this.dropSelf(ModBLocks.ASH_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_ASH_WOOD.get());
        this.dropSelf(ModBLocks.ASH_PLANKS.get());
        this.dropSelf(ModBLocks.ASH_SAPLING.get());

        this.add(ModBLocks.ASH_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.ASH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.ASH_FENCE.get());
        this.dropSelf(ModBLocks.ASH_WALL.get());
        this.dropSelf(ModBLocks.ASH_STAIRS.get());
        this.dropSelf(ModBLocks.ASH_BUTTON.get());
        this.dropSelf(ModBLocks.ASH_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.ASH_TRAPDOOR.get());
        this.dropSelf(ModBLocks.ASH_FENCE_GATE.get());

        this.add(ModBLocks.ASH_DOOR.get(),
                block -> createDoorTable(ModBLocks.ASH_DOOR.get()));
        this.add(ModBLocks.ASH_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.ASH_SLAB.get()));


        //Blackbark
        this.dropSelf(ModBLocks.BLACKBARK_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_BLACKBARK_LOG.get());
        this.dropSelf(ModBLocks.BLACKBARK_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_BLACKBARK_WOOD.get());
        this.dropSelf(ModBLocks.BLACKBARK_PLANKS.get());
        this.dropSelf(ModBLocks.BLACKBARK_SAPLING.get());

        this.add(ModBLocks.BLACKBARK_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.BLACKBARK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.BLACKBARK_FENCE.get());
        this.dropSelf(ModBLocks.BLACKBARK_WALL.get());
        this.dropSelf(ModBLocks.BLACKBARK_STAIRS.get());
        this.dropSelf(ModBLocks.BLACKBARK_BUTTON.get());
        this.dropSelf(ModBLocks.BLACKBARK_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.BLACKBARK_TRAPDOOR.get());
        this.dropSelf(ModBLocks.BLACKBARK_FENCE_GATE.get());

        this.add(ModBLocks.BLACKBARK_DOOR.get(),
                block -> createDoorTable(ModBLocks.BLACKBARK_DOOR.get()));
        this.add(ModBLocks.BLACKBARK_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.BLACKBARK_SLAB.get()));


        //Aspen
        this.dropSelf(ModBLocks.ASPEN_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_ASPEN_LOG.get());
        this.dropSelf(ModBLocks.ASPEN_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_ASPEN_WOOD.get());
        this.dropSelf(ModBLocks.ASPEN_PLANKS.get());
        this.dropSelf(ModBLocks.ASPEN_SAPLING.get());

        this.add(ModBLocks.ASPEN_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.ASPEN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.ASPEN_FENCE.get());
        this.dropSelf(ModBLocks.ASPEN_WALL.get());
        this.dropSelf(ModBLocks.ASPEN_STAIRS.get());
        this.dropSelf(ModBLocks.ASPEN_BUTTON.get());
        this.dropSelf(ModBLocks.ASPEN_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.ASPEN_TRAPDOOR.get());
        this.dropSelf(ModBLocks.ASPEN_FENCE_GATE.get());

        this.add(ModBLocks.ASPEN_DOOR.get(),
                block -> createDoorTable(ModBLocks.ASPEN_DOOR.get()));
        this.add(ModBLocks.ASPEN_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.ASPEN_SLAB.get()));


        //Alder
        this.dropSelf(ModBLocks.ALDER_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_ALDER_LOG.get());
        this.dropSelf(ModBLocks.ALDER_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_ALDER_WOOD.get());
        this.dropSelf(ModBLocks.ALDER_PLANKS.get());
        this.dropSelf(ModBLocks.ALDER_SAPLING.get());

        this.add(ModBLocks.ALDER_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.ALDER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBLocks.ALDER_FENCE.get());
        this.dropSelf(ModBLocks.ALDER_WALL.get());
        this.dropSelf(ModBLocks.ALDER_STAIRS.get());
        this.dropSelf(ModBLocks.ALDER_BUTTON.get());
        this.dropSelf(ModBLocks.ALDER_PRESSURE_PLATE.get());
        this.dropSelf(ModBLocks.ALDER_TRAPDOOR.get());
        this.dropSelf(ModBLocks.ALDER_FENCE_GATE.get());

        this.add(ModBLocks.ALDER_DOOR.get(),
                block -> createDoorTable(ModBLocks.ALDER_DOOR.get()));
        this.add(ModBLocks.ALDER_SLAB.get(),
                block -> createSlabItemTable(ModBLocks.ALDER_SLAB.get()));
        // ---------------------------(STONE)--------------------------- //
        this.dropSelf(ModBLocks.SSTONE_1_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_1_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_1_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_1_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_2_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_2_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_2_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_2_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_3_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_3_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_3_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_3_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_4_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_4_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_4_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_4_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_5_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_5_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_5_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_5_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_6_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_6_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_6_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_6_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_7_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_7_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_7_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_7_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_8_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_8_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_8_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_8_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_9_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_9_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_9_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_9_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_10_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_10_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_10_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_10_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_11_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_11_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_11_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_11_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_12_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_12_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_12_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_12_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_13_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_13_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_13_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_13_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_14_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_14_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_14_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_14_WALL.get());
        // this.dropSelf(ModBLocks.SSTONE_15_BLOCK.get());
        // this.dropSelf(ModBLocks.SSTONE_15_STAIRS.get());
        // this.dropSelf(ModBLocks.SSTONE_15_SLAB.get());
        // this.dropSelf(ModBLocks.SSTONE_15_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_16_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_16_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_16_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_16_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_17_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_17_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_17_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_17_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_18_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_18_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_18_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_18_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_19_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_19_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_19_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_19_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_20_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_20_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_20_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_20_WALL.get());
        // this.dropSelf(ModBLocks.SSTONE_21_BLOCK.get());
        //  this.dropSelf(ModBLocks.SSTONE_21_STAIRS.get());
        // this.dropSelf(ModBLocks.SSTONE_21_SLAB.get());
        //  this.dropSelf(ModBLocks.SSTONE_21_WALL.get());
        this.dropSelf(ModBLocks.SSTONE_22_BLOCK.get());
        this.dropSelf(ModBLocks.SSTONE_22_STAIRS.get());
        this.dropSelf(ModBLocks.SSTONE_22_SLAB.get());
        this.dropSelf(ModBLocks.SSTONE_22_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_1_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_1_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_1_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_1_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_2_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_2_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_2_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_2_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_3_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_3_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_3_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_3_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_4_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_4_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_4_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_4_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_5_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_5_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_5_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_5_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_6_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_6_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_6_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_6_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_7_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_7_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_7_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_7_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_8_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_8_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_8_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_8_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_9_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_9_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_9_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_9_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_10_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_10_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_10_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_10_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_11_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_11_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_11_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_11_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_12_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_12_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_12_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_12_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_13_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_13_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_13_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_13_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_14_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_14_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_14_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_14_WALL.get());
// this.dropSelf(ModBLocks.RSANDSTONE_15_BLOCK.get());
// this.dropSelf(ModBLocks.RSANDSTONE_15_STAIRS.get());
// this.dropSelf(ModBLocks.RSANDSTONE_15_SLAB.get());
// this.dropSelf(ModBLocks.RSANDSTONE_15_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_19_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_19_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_19_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_19_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_20_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_20_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_20_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_20_WALL.get());
// this.dropSelf(ModBLocks.RSANDSTONE_21_BLOCK.get());
//  this.dropSelf(ModBLocks.RSANDSTONE_21_STAIRS.get());
// this.dropSelf(ModBLocks.RSANDSTONE_21_SLAB.get());
//  this.dropSelf(ModBLocks.RSANDSTONE_21_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_WALL.get());







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
        this.dropSelf(ModBLocks.OPIUM_POPPY.get());
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

        // ---------------------------(POTTED PLANTS)--------------------------- //
        //this.add(ModBLocks.POTTED_WINTER_ROSE.get(), createPotFlowerItemTable(ModBLocks.WINTER_ROSE.get()));

        /** // ---------------------------(NORMAL BLOCKS)--------------------------- // */
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
}
