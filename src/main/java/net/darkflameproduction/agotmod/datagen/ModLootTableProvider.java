package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.datagen.loot.ModBlockLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider {
    // Static method to create an instance of LootTableProvider
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        // Return a new LootTableProvider instance
        // It takes a PackOutput, a Set of namespaces, and a List of SubProviderEntry instances
        return new LootTableProvider(output, Set.of(), List.of(
                // SubProviderEntry for ModBlockLootTables
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ), provider);
    }
}
