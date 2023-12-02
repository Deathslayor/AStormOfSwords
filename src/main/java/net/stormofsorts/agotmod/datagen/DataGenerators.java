package net.stormofsorts.agotmod.datagen;

import net.stormofsorts.agotmod.AGoTMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    // Subscribe to the GatherDataEvent to add custom data generators
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        // Extracting necessary objects from the event for data generation
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        // ADDS MOD RECIPES
        // Add a provider for generating mod recipes
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));

        // ADDS LOOT TABLES
        // Add a provider for generating mod loot tables
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

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
    }
}