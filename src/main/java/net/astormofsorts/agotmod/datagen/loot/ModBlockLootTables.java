package net.astormofsorts.agotmod.datagen.loot;

import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
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

        this.add(ModBLocks.DIAMONDS_ORE.get(), block -> oreDropBetween1And3(ModBLocks.DIAMONDS_ORE.get(), (Item) diamonds));
        this.add(ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(), block -> oreDropBetween1And3(ModBLocks.DIAMONDS_DEEPSLATE_ORE.get(), (Item) diamonds));

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

        //Sycamore
        this.dropSelf(ModBLocks.SYCAMORE_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_SYCAMORE_LOG.get());
        this.dropSelf(ModBLocks.SYCAMORE_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_SYCAMORE_WOOD.get());
        this.dropSelf(ModBLocks.SYCAMORE_PLANKS.get());
        this.dropSelf(ModBLocks.SYCAMORE_SAPLING.get());

        this.add(ModBLocks.SYCAMORE_LEAVES.get(), block -> createLeavesDrops(block, ModBLocks.SYCAMORE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
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
