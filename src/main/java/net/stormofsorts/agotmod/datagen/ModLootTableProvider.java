package net.stormofsorts.agotmod.datagen;

import net.stormofsorts.agotmod.datagen.loot.ModBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    // Static method to create an instance of LootTableProvider
    public static LootTableProvider create(PackOutput output) {
        // Return a new LootTableProvider instance
        // It takes a PackOutput, a Set of namespaces, and a List of SubProviderEntry instances
        return new LootTableProvider(output, Set.of(), List.of(
                // SubProviderEntry for ModBlockLootTables
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}
