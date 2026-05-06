package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.datagen.loot.ModBlockLootTables;
import net.darkflameproduction.agotmod.datagen.loot.ModEntityLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        return new LootTableProvider(
                output, 
                Set.of(), 
                List.of(
                    new LootTableProvider.SubProviderEntry(
                            ModBlockLootTables::new, 
                            LootContextParamSets.BLOCK
                    ),
                    new LootTableProvider.SubProviderEntry(
                            ModEntityLootTables::new, 
                            LootContextParamSets.ENTITY
                    )
                ), 
                provider
        );
    }
}