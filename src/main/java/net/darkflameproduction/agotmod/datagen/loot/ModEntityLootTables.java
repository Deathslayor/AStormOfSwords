package net.darkflameproduction.agotmod.datagen.loot;

import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class ModEntityLootTables extends EntityLootSubProvider {
    private final HolderLookup.Provider provider;

    public ModEntityLootTables(HolderLookup.Provider provider) {
        super(FeatureFlags.REGISTRY.allFlags(), FeatureFlags.REGISTRY.allFlags(), provider);
        this.provider = provider;
    }

    @Override
    public void generate() {
        this.add(ModEntities.MAMMOTH_ENTITY.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.IVORY.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.RAW_MAMMOTH_MEAT.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 32.0F)))))
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(ModItems.FUR.get())
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 32.0F)))
        )));

        this.add(ModEntities.CROW_ENTITY.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1))
                                .add(LootItem.lootTableItem(Items.FEATHER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))));






    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(entry -> (EntityType<?>) entry.get());
    }

    // Helper method for basic mob drops
    protected LootTable.Builder createBasicMobDrop(EntityType<?> entityType, Item mainDrop, float minDrop, float maxDrop) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(mainDrop)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrop, maxDrop)))
                ));
    }

    // Helper method for rare drops with looting bonus
    protected LootTable.Builder createRareDropTable(EntityType<?> entityType, Item rareItem, float dropChance) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(rareItem))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())
                );
    }

    // Helper method for multiple guaranteed drops
    protected LootTable.Builder createMultipleDrops(EntityType<?> entityType, Item... items) {
        LootTable.Builder builder = LootTable.lootTable();
        for (Item item : items) {
            builder.withPool(LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .add(LootItem.lootTableItem(item)));
        }
        return builder;
    }
}
