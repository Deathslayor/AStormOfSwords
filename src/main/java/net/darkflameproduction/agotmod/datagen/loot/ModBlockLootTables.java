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
         this.dropSelf(ModBLocks.SSTONE_21_BLOCK.get());
          this.dropSelf(ModBLocks.SSTONE_21_STAIRS.get());
         this.dropSelf(ModBLocks.SSTONE_21_SLAB.get());
         this.dropSelf(ModBLocks.SSTONE_21_WALL.get());
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
 this.dropSelf(ModBLocks.RSANDSTONE_21_BLOCK.get());
  this.dropSelf(ModBLocks.RSANDSTONE_21_STAIRS.get());
 this.dropSelf(ModBLocks.RSANDSTONE_21_SLAB.get());
  this.dropSelf(ModBLocks.RSANDSTONE_21_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_22_WALL.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_23_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_24_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_24_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_24_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_24_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_25_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_25_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_25_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_25_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_26_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_26_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_26_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_26_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_27_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_27_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_27_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_27_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_28_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_28_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_28_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_28_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_29_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_29_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_29_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_29_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_30_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_30_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_30_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_30_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_31_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_31_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_31_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_31_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_32_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_32_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_32_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_32_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_33_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_33_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_33_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_33_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_34_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_34_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_34_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_34_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_35_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_35_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_35_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_35_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_36_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_36_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_36_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_36_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_37_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_37_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_37_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_37_WALL.get());

        this.dropSelf(ModBLocks.RSANDSTONE_38_BLOCK.get());
        this.dropSelf(ModBLocks.RSANDSTONE_38_STAIRS.get());
        this.dropSelf(ModBLocks.RSANDSTONE_38_SLAB.get());
        this.dropSelf(ModBLocks.RSANDSTONE_38_WALL.get());


        //redkeep
        this.dropSelf(ModBLocks.REDKEEP_STONE_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_STONE_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_STONE_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_STONE_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_1_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_1_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_1_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_1_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_2_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_2_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_2_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_2_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_3_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_3_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_3_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_3_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_4_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_4_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_4_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_4_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_5_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_5_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_5_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_5_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_6_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_6_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_6_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_6_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_7_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_7_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_7_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_7_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_8_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_8_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_8_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_8_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_9_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_9_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_9_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_9_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_10_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_10_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_10_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_10_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_11_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_11_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_11_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_11_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_12_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_12_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_12_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_12_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_13_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_13_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_13_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_13_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_14_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_14_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_14_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_14_WALL.get());
// this.dropSelf(ModBLocks.REDKEEP_15_BLOCK.get());
// this.dropSelf(ModBLocks.REDKEEP_15_STAIRS.get());
// this.dropSelf(ModBLocks.REDKEEP_15_SLAB.get());
// this.dropSelf(ModBLocks.REDKEEP_15_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_16_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_16_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_16_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_16_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_17_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_17_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_17_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_17_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_18_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_18_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_18_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_18_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_19_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_19_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_19_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_19_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_20_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_20_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_20_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_20_WALL.get());
this.dropSelf(ModBLocks.REDKEEP_21_BLOCK.get());
this.dropSelf(ModBLocks.REDKEEP_21_STAIRS.get());
this.dropSelf(ModBLocks.REDKEEP_21_SLAB.get());
this.dropSelf(ModBLocks.REDKEEP_21_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_22_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_22_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_22_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_22_WALL.get());
        this.dropSelf(ModBLocks.REDKEEP_23_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_23_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_23_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_23_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_24_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_24_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_24_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_24_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_25_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_25_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_25_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_25_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_26_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_26_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_26_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_26_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_27_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_27_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_27_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_27_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_28_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_28_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_28_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_28_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_29_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_29_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_29_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_29_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_30_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_30_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_30_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_30_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_31_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_31_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_31_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_31_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_32_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_32_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_32_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_32_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_33_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_33_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_33_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_33_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_34_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_34_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_34_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_34_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_35_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_35_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_35_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_35_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_36_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_36_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_36_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_36_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_37_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_37_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_37_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_37_WALL.get());

        this.dropSelf(ModBLocks.REDKEEP_38_BLOCK.get());
        this.dropSelf(ModBLocks.REDKEEP_38_STAIRS.get());
        this.dropSelf(ModBLocks.REDKEEP_38_SLAB.get());
        this.dropSelf(ModBLocks.REDKEEP_38_WALL.get());

        this.dropSelf(ModBLocks.STONE_1_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_1_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_1_SLAB.get());
        this.dropSelf(ModBLocks.STONE_1_WALL.get());
        this.dropSelf(ModBLocks.STONE_2_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_2_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_2_SLAB.get());
        this.dropSelf(ModBLocks.STONE_2_WALL.get());
        this.dropSelf(ModBLocks.STONE_3_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_3_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_3_SLAB.get());
        this.dropSelf(ModBLocks.STONE_3_WALL.get());
        this.dropSelf(ModBLocks.STONE_4_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_4_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_4_SLAB.get());
        this.dropSelf(ModBLocks.STONE_4_WALL.get());
        this.dropSelf(ModBLocks.STONE_6_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_6_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_6_SLAB.get());
        this.dropSelf(ModBLocks.STONE_6_WALL.get());
        this.dropSelf(ModBLocks.STONE_7_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_7_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_7_SLAB.get());
        this.dropSelf(ModBLocks.STONE_7_WALL.get());
        this.dropSelf(ModBLocks.STONE_8_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_8_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_8_SLAB.get());
        this.dropSelf(ModBLocks.STONE_8_WALL.get());
        this.dropSelf(ModBLocks.STONE_9_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_9_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_9_SLAB.get());
        this.dropSelf(ModBLocks.STONE_9_WALL.get());
        this.dropSelf(ModBLocks.STONE_10_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_10_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_10_SLAB.get());
        this.dropSelf(ModBLocks.STONE_10_WALL.get());
        this.dropSelf(ModBLocks.STONE_11_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_11_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_11_SLAB.get());
        this.dropSelf(ModBLocks.STONE_11_WALL.get());
        this.dropSelf(ModBLocks.STONE_12_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_12_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_12_SLAB.get());
        this.dropSelf(ModBLocks.STONE_12_WALL.get());
        this.dropSelf(ModBLocks.STONE_13_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_13_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_13_SLAB.get());
        this.dropSelf(ModBLocks.STONE_13_WALL.get());
        this.dropSelf(ModBLocks.STONE_14_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_14_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_14_SLAB.get());
        this.dropSelf(ModBLocks.STONE_14_WALL.get());
// this.dropSelf(ModBLocks.STONE_15_BLOCK.get());
// this.dropSelf(ModBLocks.STONE_15_STAIRS.get());
// this.dropSelf(ModBLocks.STONE_15_SLAB.get());
// this.dropSelf(ModBLocks.STONE_15_WALL.get());
        this.dropSelf(ModBLocks.STONE_16_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_16_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_16_SLAB.get());
        this.dropSelf(ModBLocks.STONE_16_WALL.get());
        this.dropSelf(ModBLocks.STONE_17_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_17_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_17_SLAB.get());
        this.dropSelf(ModBLocks.STONE_17_WALL.get());
        this.dropSelf(ModBLocks.STONE_18_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_18_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_18_SLAB.get());
        this.dropSelf(ModBLocks.STONE_18_WALL.get());
        this.dropSelf(ModBLocks.STONE_20_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_20_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_20_SLAB.get());
        this.dropSelf(ModBLocks.STONE_20_WALL.get());
        this.dropSelf(ModBLocks.STONE_21_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_21_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_21_SLAB.get());
        this.dropSelf(ModBLocks.STONE_21_WALL.get());
        this.dropSelf(ModBLocks.STONE_22_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_22_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_22_SLAB.get());
        this.dropSelf(ModBLocks.STONE_22_WALL.get());
        this.dropSelf(ModBLocks.STONE_24_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_24_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_24_SLAB.get());
        this.dropSelf(ModBLocks.STONE_24_WALL.get());
        this.dropSelf(ModBLocks.STONE_25_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_25_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_25_SLAB.get());
        this.dropSelf(ModBLocks.STONE_25_WALL.get());
        this.dropSelf(ModBLocks.STONE_26_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_26_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_26_SLAB.get());
        this.dropSelf(ModBLocks.STONE_26_WALL.get());
        this.dropSelf(ModBLocks.STONE_27_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_27_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_27_SLAB.get());
        this.dropSelf(ModBLocks.STONE_27_WALL.get());
        this.dropSelf(ModBLocks.STONE_28_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_28_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_28_SLAB.get());
        this.dropSelf(ModBLocks.STONE_28_WALL.get());
        this.dropSelf(ModBLocks.STONE_29_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_29_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_29_SLAB.get());
        this.dropSelf(ModBLocks.STONE_29_WALL.get());
        this.dropSelf(ModBLocks.STONE_30_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_30_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_30_SLAB.get());
        this.dropSelf(ModBLocks.STONE_30_WALL.get());
        this.dropSelf(ModBLocks.STONE_31_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_31_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_31_SLAB.get());
        this.dropSelf(ModBLocks.STONE_31_WALL.get());
        this.dropSelf(ModBLocks.STONE_32_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_32_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_32_SLAB.get());
        this.dropSelf(ModBLocks.STONE_32_WALL.get());
        this.dropSelf(ModBLocks.STONE_33_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_33_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_33_SLAB.get());
        this.dropSelf(ModBLocks.STONE_33_WALL.get());
        this.dropSelf(ModBLocks.STONE_34_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_34_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_34_SLAB.get());
        this.dropSelf(ModBLocks.STONE_34_WALL.get());
        this.dropSelf(ModBLocks.STONE_35_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_35_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_35_SLAB.get());
        this.dropSelf(ModBLocks.STONE_35_WALL.get());
        this.dropSelf(ModBLocks.STONE_36_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_36_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_36_SLAB.get());
        this.dropSelf(ModBLocks.STONE_36_WALL.get());
        this.dropSelf(ModBLocks.STONE_37_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_37_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_37_SLAB.get());
        this.dropSelf(ModBLocks.STONE_37_WALL.get());
        this.dropSelf(ModBLocks.STONE_38_BLOCK.get());
        this.dropSelf(ModBLocks.STONE_38_STAIRS.get());
        this.dropSelf(ModBLocks.STONE_38_SLAB.get());
        this.dropSelf(ModBLocks.STONE_38_WALL.get());
        this.dropSelf(ModBLocks.BASALT_2_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_2_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_2_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_2_WALL.get());
        this.dropSelf(ModBLocks.BASALT_3_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_3_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_3_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_3_WALL.get());
        this.dropSelf(ModBLocks.BASALT_4_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_4_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_4_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_4_WALL.get());
        this.dropSelf(ModBLocks.BASALT_5_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_5_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_5_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_5_WALL.get());
        this.dropSelf(ModBLocks.BASALT_6_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_6_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_6_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_6_WALL.get());
        this.dropSelf(ModBLocks.BASALT_7_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_7_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_7_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_7_WALL.get());
        this.dropSelf(ModBLocks.BASALT_8_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_8_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_8_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_8_WALL.get());
        this.dropSelf(ModBLocks.BASALT_9_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_9_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_9_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_9_WALL.get());
        this.dropSelf(ModBLocks.BASALT_10_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_10_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_10_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_10_WALL.get());
        this.dropSelf(ModBLocks.BASALT_11_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_11_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_11_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_11_WALL.get());
        this.dropSelf(ModBLocks.BASALT_12_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_12_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_12_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_12_WALL.get());
        this.dropSelf(ModBLocks.BASALT_13_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_13_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_13_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_13_WALL.get());
        this.dropSelf(ModBLocks.BASALT_14_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_14_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_14_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_14_WALL.get());
// this.dropSelf(ModBLocks.BASALT_15_BLOCK.get());
// this.dropSelf(ModBLocks.BASALT_15_STAIRS.get());
// this.dropSelf(ModBLocks.BASALT_15_SLAB.get());
// this.dropSelf(ModBLocks.BASALT_15_WALL.get());
        this.dropSelf(ModBLocks.BASALT_16_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_16_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_16_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_16_WALL.get());
        this.dropSelf(ModBLocks.BASALT_17_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_17_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_17_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_17_WALL.get());
        this.dropSelf(ModBLocks.BASALT_18_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_18_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_18_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_18_WALL.get());
        this.dropSelf(ModBLocks.BASALT_19_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_19_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_19_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_19_WALL.get());
        this.dropSelf(ModBLocks.BASALT_20_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_20_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_20_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_20_WALL.get());
        this.dropSelf(ModBLocks.BASALT_21_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_21_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_21_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_21_WALL.get());
        this.dropSelf(ModBLocks.BASALT_22_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_22_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_22_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_22_WALL.get());
        this.dropSelf(ModBLocks.BASALT_23_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_23_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_23_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_23_WALL.get());
        this.dropSelf(ModBLocks.BASALT_24_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_24_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_24_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_24_WALL.get());
        this.dropSelf(ModBLocks.BASALT_25_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_25_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_25_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_25_WALL.get());
        this.dropSelf(ModBLocks.BASALT_26_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_26_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_26_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_26_WALL.get());
        this.dropSelf(ModBLocks.BASALT_27_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_27_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_27_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_27_WALL.get());
        this.dropSelf(ModBLocks.BASALT_28_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_28_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_28_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_28_WALL.get());
        this.dropSelf(ModBLocks.BASALT_29_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_29_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_29_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_29_WALL.get());
        this.dropSelf(ModBLocks.BASALT_30_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_30_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_30_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_30_WALL.get());
        this.dropSelf(ModBLocks.BASALT_31_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_31_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_31_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_31_WALL.get());
        this.dropSelf(ModBLocks.BASALT_32_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_32_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_32_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_32_WALL.get());
        this.dropSelf(ModBLocks.BASALT_33_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_33_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_33_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_33_WALL.get());
        this.dropSelf(ModBLocks.BASALT_34_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_34_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_34_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_34_WALL.get());
        this.dropSelf(ModBLocks.BASALT_35_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_35_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_35_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_35_WALL.get());
        this.dropSelf(ModBLocks.BASALT_36_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_36_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_36_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_36_WALL.get());
        this.dropSelf(ModBLocks.BASALT_37_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_37_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_37_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_37_WALL.get());
        this.dropSelf(ModBLocks.BASALT_38_BLOCK.get());
        this.dropSelf(ModBLocks.BASALT_38_STAIRS.get());
        this.dropSelf(ModBLocks.BASALT_38_SLAB.get());
        this.dropSelf(ModBLocks.BASALT_38_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_1_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_1_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_1_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_1_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_3_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_3_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_3_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_3_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_4_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_4_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_4_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_4_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_5_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_5_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_5_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_5_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_6_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_6_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_6_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_6_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_7_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_7_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_7_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_7_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_8_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_8_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_8_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_8_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_9_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_9_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_9_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_9_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_10_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_10_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_10_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_10_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_11_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_11_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_11_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_11_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_12_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_12_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_12_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_12_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_13_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_13_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_13_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_13_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_14_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_14_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_14_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_14_WALL.get());
// this.dropSelf(ModBLocks.BRICKS_15_BLOCK.get());
// this.dropSelf(ModBLocks.BRICKS_15_STAIRS.get());
// this.dropSelf(ModBLocks.BRICKS_15_SLAB.get());
// this.dropSelf(ModBLocks.BRICKS_15_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_16_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_16_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_16_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_16_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_17_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_17_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_17_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_17_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_18_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_18_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_18_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_18_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_19_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_19_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_19_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_19_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_20_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_20_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_20_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_20_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_21_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_21_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_21_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_21_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_22_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_22_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_22_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_22_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_23_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_23_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_23_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_23_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_24_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_24_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_24_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_24_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_25_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_25_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_25_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_25_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_26_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_26_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_26_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_26_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_27_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_27_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_27_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_27_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_28_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_28_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_28_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_28_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_29_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_29_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_29_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_29_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_30_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_30_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_30_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_30_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_31_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_31_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_31_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_31_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_32_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_32_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_32_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_32_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_33_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_33_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_33_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_33_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_34_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_34_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_34_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_34_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_35_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_35_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_35_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_35_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_36_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_36_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_36_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_36_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_37_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_37_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_37_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_37_WALL.get());
        this.dropSelf(ModBLocks.BRICKS_38_BLOCK.get());
        this.dropSelf(ModBLocks.BRICKS_38_STAIRS.get());
        this.dropSelf(ModBLocks.BRICKS_38_SLAB.get());
        this.dropSelf(ModBLocks.BRICKS_38_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_1_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_1_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_1_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_1_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_2_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_2_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_2_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_2_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_3_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_3_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_3_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_3_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_4_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_4_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_4_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_4_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_5_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_5_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_5_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_5_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_6_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_6_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_6_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_6_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_7_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_7_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_7_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_7_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_8_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_8_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_8_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_8_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_9_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_9_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_9_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_9_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_10_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_10_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_10_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_10_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_11_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_11_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_11_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_11_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_12_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_12_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_12_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_12_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_13_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_13_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_13_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_13_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_14_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_14_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_14_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_14_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_16_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_16_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_16_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_16_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_17_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_17_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_17_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_17_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_18_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_18_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_18_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_18_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_19_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_19_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_19_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_19_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_20_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_20_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_20_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_20_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_21_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_21_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_21_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_21_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_22_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_22_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_22_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_22_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_23_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_23_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_23_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_23_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_24_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_24_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_24_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_24_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_25_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_25_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_25_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_25_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_26_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_26_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_26_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_26_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_27_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_27_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_27_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_27_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_28_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_28_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_28_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_28_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_29_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_29_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_29_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_29_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_30_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_30_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_30_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_30_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_31_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_31_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_31_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_31_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_32_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_32_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_32_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_32_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_33_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_33_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_33_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_33_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_34_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_34_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_34_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_34_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_35_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_35_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_35_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_35_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_36_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_36_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_36_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_36_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_37_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_37_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_37_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_37_WALL.get());
        this.dropSelf(ModBLocks.CALCITE_38_BLOCK.get());
        this.dropSelf(ModBLocks.CALCITE_38_STAIRS.get());
        this.dropSelf(ModBLocks.CALCITE_38_SLAB.get());
        this.dropSelf(ModBLocks.CALCITE_38_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_1_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_1_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_1_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_1_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_2_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_2_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_2_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_2_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_3_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_3_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_3_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_3_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_5_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_5_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_5_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_5_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_6_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_6_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_6_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_6_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_7_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_7_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_7_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_7_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_8_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_8_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_8_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_8_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_9_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_9_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_9_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_9_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_10_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_10_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_10_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_10_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_11_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_11_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_11_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_11_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_12_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_12_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_12_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_12_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_13_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_13_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_13_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_13_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_14_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_14_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_14_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_14_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_16_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_16_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_16_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_16_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_17_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_17_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_17_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_17_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_18_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_18_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_18_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_18_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_20_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_20_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_20_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_20_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_21_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_21_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_21_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_21_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_22_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_22_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_22_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_22_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_23_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_23_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_23_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_23_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_24_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_24_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_24_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_24_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_25_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_25_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_25_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_25_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_26_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_26_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_26_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_26_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_27_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_27_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_27_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_27_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_28_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_28_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_28_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_28_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_29_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_29_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_29_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_29_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_30_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_30_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_30_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_30_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_31_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_31_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_31_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_31_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_32_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_32_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_32_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_32_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_33_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_33_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_33_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_33_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_34_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_34_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_34_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_34_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_35_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_35_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_35_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_35_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_36_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_36_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_36_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_36_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_37_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_37_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_37_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_37_WALL.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_38_BLOCK.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_38_STAIRS.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_38_SLAB.get());
        this.dropSelf(ModBLocks.CDEEPSLATE_38_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_1_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_1_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_1_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_1_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_2_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_2_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_2_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_2_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_3_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_3_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_3_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_3_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_4_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_4_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_4_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_4_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_5_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_5_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_5_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_5_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_6_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_6_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_6_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_6_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_7_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_7_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_7_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_7_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_8_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_8_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_8_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_8_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_9_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_9_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_9_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_9_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_10_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_10_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_10_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_10_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_11_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_11_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_11_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_11_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_12_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_12_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_12_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_12_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_13_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_13_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_13_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_13_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_14_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_14_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_14_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_14_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_16_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_16_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_16_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_16_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_17_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_17_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_17_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_17_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_18_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_18_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_18_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_18_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_19_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_19_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_19_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_19_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_20_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_20_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_20_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_20_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_21_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_21_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_21_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_21_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_22_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_22_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_22_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_22_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_23_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_23_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_23_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_23_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_24_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_24_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_24_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_24_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_25_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_25_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_25_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_25_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_26_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_26_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_26_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_26_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_27_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_27_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_27_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_27_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_28_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_28_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_28_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_28_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_29_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_29_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_29_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_29_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_30_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_30_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_30_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_30_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_31_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_31_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_31_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_31_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_32_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_32_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_32_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_32_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_33_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_33_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_33_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_33_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_34_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_34_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_34_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_34_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_35_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_35_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_35_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_35_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_36_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_36_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_36_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_36_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_37_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_37_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_37_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_37_WALL.get());
        this.dropSelf(ModBLocks.GRANITE_38_BLOCK.get());
        this.dropSelf(ModBLocks.GRANITE_38_STAIRS.get());
        this.dropSelf(ModBLocks.GRANITE_38_SLAB.get());
        this.dropSelf(ModBLocks.GRANITE_38_WALL.get());










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
