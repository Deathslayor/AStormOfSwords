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
    @SubscribeEvent
    public static void gatherData(@NotNull GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // Server-side data generation
        generator.addProvider(event.includeServer(), new ModRecipeProvider.Runner(packOutput, lookupProvider));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput, lookupProvider));

        // Block and Item Tags (order matters - blocks first, then items)
        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        // POI and Banner Pattern Tags
        generator.addProvider(event.includeServer(), new ModPoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModBannerPatternProvider(packOutput, lookupProvider, existingFileHelper));

        // World Generation (includes banner patterns now)
        ModWorldGenProvider worldGenProvider = generator.addProvider(event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));

        // Client-side data generation
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

        // Map Provider (for CTGen integration)
        generator.addProvider(true, new MapProvider(ModDimensionProvider.getOriginalMapImage(), ModDimensionProvider.KNOWN_WORLD,
                worldGenProvider.getRegistryProvider().thenComposeAsync(provider -> {
                    List<Zone> Zones = ModDimensionProvider.getZones(provider.lookupOrThrow(CTRegistries.ZONES_KEY)).stream().map(Holder::value).toList();
                    return CompletableFuture.completedFuture(Zones);
                }), packOutput));
    }
}