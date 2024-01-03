package net.astormofsorts.agotmod.datagen.loot;

import net.astormofsorts.agotmod.block.ModBLocks;
import net.astormofsorts.agotmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        // Initialize the ModBlockLootTables, specifying sets of known blocks and flags
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        /** // ---------------------------(ORES)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        // Add loot table for tin ores
        this.add(ModBLocks.DEEPSLATE_TIN_ORE.get(),
                block -> oreDropBetween2And5(ModBLocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()));
        this.add(ModBLocks.TIN_ORE.get(),
                block -> oreDropBetween2And5(ModBLocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));
        // ---------------------------(TIN)--------------------------- //
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
        this.dropSelf(ModBLocks.SYCAMORE_LOG.get());
        this.dropSelf(ModBLocks.STRIPPED_SYCAMORE_LOG.get());
        this.dropSelf(ModBLocks.SYCAMORE_WOOD.get());
        this.dropSelf(ModBLocks.STRIPPED_SYCAMORE_WOOD.get());
        this.dropSelf(ModBLocks.SYCAMORE_PLANKS.get());

        this.add(ModBLocks.SYCAMORE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBLocks.SYCAMORE_LOG.get(), NORMAL_LEAVES_SAPLING_CHANCES)); // TODO: Change to Sapling!
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
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
