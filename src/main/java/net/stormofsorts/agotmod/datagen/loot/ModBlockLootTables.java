package net.stormofsorts.agotmod.datagen.loot;

import net.stormofsorts.agotmod.block.ModBLocks;
import net.stormofsorts.agotmod.item.ModItems;
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
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        /** // ---------------------------(ORES)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        this.add(ModBLocks.DEEPSLATE_TIN_ORE.get(),
                block -> oreDropBetween2And5(ModBLocks.DEEPSLATE_TIN_ORE.get(), ModItems.RAW_TIN.get()));
        this.add(ModBLocks.TIN_ORE.get(),
                block -> oreDropBetween2And5(ModBLocks.TIN_ORE.get(), ModItems.RAW_TIN.get()));
        // ---------------------------(TIN)--------------------------- //
        /** // ---------------------------(ORES)--------------------------- // */



        /** // ---------------------------(NORMAL BLOCKS)--------------------------- // */
        // ---------------------------(TIN)--------------------------- //
        this.dropSelf(ModBLocks.TIN_BLOCK.get());
        this.dropSelf(ModBLocks.RAW_TIN_BLOCK.get());
        // ---------------------------(TIN)--------------------------- //

        // ---------------------------(BRONZE)--------------------------- //
        this.dropSelf(ModBLocks.BRONZE_BLOCK.get());
        // ---------------------------(BRONZE)--------------------------- //

        // ---------------------------(BRICKS)--------------------------- //
        this.dropSelf(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());
        this.dropSelf(ModBLocks.DARK_STONE_BRICK.get());
        this.dropSelf(ModBLocks.STONE_BRICK_BUT_COOLER.get());
        // ---------------------------(BRICKS)--------------------------- //

        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        this.dropSelf(ModBLocks.MINT_BLOCK.get());
        // ---------------------(VILLAGER PROFESSIONS BLOCKS)--------------------- //
        /** // ---------------------------(NORMAL BLOCKS)--------------------------- // */
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBLocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder oreDropBetween2And5(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }
}
