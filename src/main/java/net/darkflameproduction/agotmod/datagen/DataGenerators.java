package net.darkflameproduction.agotmod.datagen;

import dev.tocraft.ctgen.data.MapProvider;
import dev.tocraft.ctgen.xtend.CTRegistries;
import dev.tocraft.ctgen.zone.Zone;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    // Subscribe to the GatherDataEvent to add custom data generators
    @SubscribeEvent
    public static void gatherData(@NotNull GatherDataEvent event) {
        // Extracting necessary objects from the event for data generation
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // ADDS MOD RECIPES
        // Add a provider for generating mod recipes
        generator.addProvider(event.includeServer(), new ModRecipeProvider.Runner(packOutput, lookupProvider));

        // ADDS LOOT TABLES
        // Add a provider for generating mod loot tables
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput, lookupProvider));

        // ADDS BLOCK STATES
        // Add a provider for generating mod block states (for rendering)
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));

        // ADDS ITEM MODEL
        // Add a provider for generating mod item models (for rendering)
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

        // ADDS BLOCK AND ITEM TAGS
        // Add a provider for generating mod block and item tags
        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));


        // Adds Villager Professions
        // Add a provider for generating mod villager profession tags
        generator.addProvider(event.includeServer(), new ModPoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        // Add a provider for generating custom world generation features
        ModWorldGenProvider worldGenProvider = generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));

        // World gen maps
        generator.addProvider(true, new MapProvider(ModDimensionProvider.getOriginalMapImage(), ModDimensionProvider.KNOWN_WORLD,
                // get provider with custom registry entries
                worldGenProvider.getRegistryProvider().thenComposeAsync(provider -> {
                    List<Zone> Zones = ModDimensionProvider.getZones(provider.lookupOrThrow(CTRegistries.ZONES_KEY)).stream().map(Holder::value).toList();
                    return CompletableFuture.completedFuture(Zones);
                }), packOutput));
    }
}