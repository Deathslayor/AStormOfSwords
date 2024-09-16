package net.astormofsorts.agotmod.datagen.loot;

import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    private static final List<ItemLike> diamonds = List.of(
            ModItems.YELLOW_DIAMOND.get(),
            ModItems.TRANSPARENT_DIAMOND.get(),
            ModItems.BLACK_DIAMOND.get());
    public ModBlockLootTables() {
        // Initialize the ModBlockLootTables, specifying sets of known blocks and flags
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        /** // ---------------------------(ORES)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        // Add loot table for tin ores
        this.add(ModBLocks.DEEPSLATE_TIN_ORE.get(), block -> oreDropBetween2And5(ModBLocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()));
        this.add(ModBLocks.TIN_ORE.get(), block -> oreDropBetween2And5(ModBLocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(GEMS)--------------------------- //
        this.add(ModBLocks.AMBER_ORE.get(), block -> oreDropBetween2And5(ModBLocks.AMBER_ORE.get(), ModItems.AMBER.get()));
        this.add(ModBLocks.AMBER_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.AMBER_DEEPSLATE_ORE.get(), ModItems.AMBER.get()));

        this.add(ModBLocks.AMETHYST_ORE.get(), block -> oreDropBetween2And5(ModBLocks.AMETHYST_ORE.get(), ModItems.AMETHYST.get()));
        this.add(ModBLocks.AMETHYST_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.AMETHYST_DEEPSLATE_ORE.get(), ModItems.AMETHYST.get()));

        this.add(ModBLocks.BLOODSTONE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.BLOODSTONE_ORE.get(), ModItems.BLOODSTONE.get()));
        this.add(ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.BLOODSTONE_DEEPSLATE_ORE.get(), ModItems.BLOODSTONE.get()));

        this.add(ModBLocks.CARNELIAN_ORE.get(), block -> oreDropBetween2And5(ModBLocks.CARNELIAN_ORE.get(), ModItems.CARNELIAN.get()));
        this.add(ModBLocks.CARNELIAN_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.CARNELIAN_DEEPSLATE_ORE.get(), ModItems.CARNELIAN.get()));

        this.add(ModBLocks.CHALCEDONY_ORE.get(), block -> oreDropBetween2And5(ModBLocks.CHALCEDONY_ORE.get(), ModItems.CHALCEDONY.get()));
        this.add(ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.CHALCEDONY_DEEPSLATE_ORE.get(), ModItems.CHALCEDONY.get()));

        this.add(ModBLocks.GARNET_ORE.get(), block -> oreDropBetween2And5(ModBLocks.GARNET_ORE.get(), ModItems.GARNET.get()));
        this.add(ModBLocks.GARNET_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.GARNET_DEEPSLATE_ORE.get(), ModItems.GARNET.get()));

        this.add(ModBLocks.JADE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.JADE_ORE.get(), ModItems.JADE.get()));
        this.add(ModBLocks.JADE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.JADE_DEEPSLATE_ORE.get(), ModItems.JADE.get()));

        this.add(ModBLocks.JASPER_ORE.get(), block -> oreDropBetween2And5(ModBLocks.JASPER_ORE.get(), ModItems.JASPER.get()));
        this.add(ModBLocks.JASPER_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.JASPER_DEEPSLATE_ORE.get(), ModItems.JASPER.get()));

        this.add(ModBLocks.MALACHITE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.MALACHITE_ORE.get(), ModItems.MALACHITE.get()));
        this.add(ModBLocks.MALACHITE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.MALACHITE_DEEPSLATE_ORE.get(), ModItems.MALACHITE.get()));

        this.add(ModBLocks.MOONSTONE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.MOONSTONE_ORE.get(), ModItems.MOONSTONE.get()));
        this.add(ModBLocks.MOONSTONE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.MOONSTONE_DEEPSLATE_ORE.get(), ModItems.MOONSTONE.get()));

        this.add(ModBLocks.ONYX_ORE.get(), block -> oreDropBetween2And5(ModBLocks.ONYX_ORE.get(), ModItems.ONYX.get()));
        this.add(ModBLocks.ONYX_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.ONYX_DEEPSLATE_ORE.get(), ModItems.ONYX.get()));

        this.add(ModBLocks.OPAL_ORE.get(), block -> oreDropBetween2And5(ModBLocks.OPAL_ORE.get(), ModItems.OPAL.get()));
        this.add(ModBLocks.OPAL_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.OPAL_DEEPSLATE_ORE.get(), ModItems.OPAL.get()));

        this.add(ModBLocks.RUBY_ORE.get(), block -> oreDropBetween2And5(ModBLocks.RUBY_ORE.get(), ModItems.RUBY.get()));
        this.add(ModBLocks.RUBY_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.RUBY_DEEPSLATE_ORE.get(), ModItems.RUBY.get()));

        this.add(ModBLocks.SAPPHIRE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.SAPPHIRE_ORE.get(), ModItems.SAPPHIRE.get()));
        this.add(ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.SAPPHIRE_DEEPSLATE_ORE.get(), ModItems.SAPPHIRE.get()));

        this.add(ModBLocks.TIGERS_EYE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.TIGERS_EYE_ORE.get(), ModItems.TIGERS_EYE.get()));
        this.add(ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.TIGERS_EYE_DEEPSLATE_ORE.get(), ModItems.TIGERS_EYE.get()));

        this.add(ModBLocks.TOPAZ_ORE.get(), block -> oreDropBetween2And5(ModBLocks.TOPAZ_ORE.get(), ModItems.TOPAZ.get()));
        this.add(ModBLocks.TOPAZ_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.TOPAZ_DEEPSLATE_ORE.get(), ModItems.TOPAZ.get()));

        this.add(ModBLocks.TOURMALINE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.TOURMALINE_ORE.get(), ModItems.TOURMALINE.get()));
        this.add(ModBLocks.TOURMALINE_DEEPSLATE_ORE.get(), block -> oreDropBetween2And5(ModBLocks.TOURMALINE_DEEPSLATE_ORE.get(), ModItems.TOURMALINE.get()));


        this.add(ModBLocks.DIAMONDS_ORE.get(), block -> oreDropBetween1And3(ModBLocks.DIAMONDS_ORE.get(), Items.DIAMOND));
        this.add(ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(), block -> oreDropBetween1And3(ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(), Items.DIAMOND));

        // ---------------------------(GEMS)--------------------------- //


        /** // ---------------------------(ORES)--------------------------- // */

        /** // ---------------------------(NORMAL BLOCKS)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        // Add loot tables for tin blocks
        this.dropSelf(ModBLocks.TIN_BLOCK.get());
        this.dropSelf(ModBLocks.RAW_TIN_BLOCK.get());
        // ---------------------------(TIN)--------------------------- //

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

        // ---------------------------(TREES)--------------------------- //

        /** // ---------------------------(NORMAL BLOCKS)--------------------------- // */
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // Retrieve known blocks from the ModBLocks registry
        return ModBLocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder oreDropBetween2And5(Block pBlock, Item item) {
        // Create a loot table for ore drops with random count between 2 and 5
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder oreDropBetween1And3(Block pBlock, Item item) {
        // Create a loot table for ore drops with random count between 1 and 3
        return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock, LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))).apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
