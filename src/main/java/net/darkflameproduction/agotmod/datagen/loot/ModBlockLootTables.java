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
                "alder"
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

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 22 || i == 31) continue; // Skip BLACKSTONE_15 and BLACKSTONE_22
            ModBLocks.BlockSet set = ModBLocks.BLACKSTONE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }
        for (int i = 1; i <= 38; i++) {
            if (i == 1 || i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.BASALT_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 2 || i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.BRICKS_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.CALCITE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 4 || i == 15 || i == 19) continue;
            ModBLocks.BlockSet set = ModBLocks.CDEEPSLATE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.GRANITE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.REDKEEP_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 16 || i == 17 || i == 18) continue; // Skip BLACKSTONE_15 and BLACKSTONE_22
            ModBLocks.BlockSet set = ModBLocks.RSANDSTONE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 16 || i == 17 || i == 18) continue; // Skip BLACKSTONE_15 and BLACKSTONE_22
            ModBLocks.BlockSet set = ModBLocks.SANDSTONE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 5 || i == 15 || i == 19 || i == 23) continue; // Skip BLACKSTONE_15 and BLACKSTONE_22
            ModBLocks.BlockSet set = ModBLocks.STONE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 23) continue;
            ModBLocks.BlockSet set = ModBLocks.SSTONE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15 || i == 35) continue;
            ModBLocks.BlockSet set = ModBLocks.BONE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.DRIPSTONE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.PACKED_ICE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 14 || i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.QUARTZ_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.ANDESITE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 10 || i == 15 || i == 20 || i == 21) continue;
            ModBLocks.BlockSet set = ModBLocks.TUFF_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }


        for (int i = 1; i <= 38; i++) {
            if (i == 15) continue;
            ModBLocks.BlockSet set = ModBLocks.DIORITE_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
            }
        }

        for (int i = 1; i <= 38; i++) {
            if (i == 7 || i == 9 || i == 15) continue; // Skip BLACKSTONE_15 and BLACKSTONE_22
            ModBLocks.BlockSet set = ModBLocks.MUD_BRICK_VARIANTS.get(i);
            if (set != null) {
                this.dropSelf(set.base().get());
                this.dropSelf(set.stairs().get());
                this.dropSelf(set.slab().get());
                this.dropSelf(set.wall().get());
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
