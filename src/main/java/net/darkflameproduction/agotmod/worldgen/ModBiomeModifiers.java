package net.darkflameproduction.agotmod.worldgen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModBiomeModifiers {
    // Define a ResourceKey for the biome modifier to add tin ore features
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = registerKey("add_tin_ore");
    public static final ResourceKey<BiomeModifier> SPAWN_MAMMOTH = registerKey("spawn_mammoth");
    public static final ResourceKey<BiomeModifier> CREATE_WEIRWOOD_TREE = registerKey("create_weirwood_tree");

    // Bootstrap method for initializing biome modifiers
    public static void bootstrap(@NotNull BootstrapContext<BiomeModifier> context) {
        // Obtain references to placed features and biomes from the data generation context
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Register the biome modifier to add tin ore features
        context.register(ADD_TIN_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                // Specify the target biomes (in this case, the overworld biomes)
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                // Specify the features to add (in this case, tin ore features)
                HolderSet.direct(placedFeatures.getOrThrow(ModplacedFeatures.TIN_ORE_PLACED_KEY)),
                // Specify the generation step for the features (underground ores)
                GenerationStep.Decoration.UNDERGROUND_ORES));
        //Spawns Mammoths
        context.register(SPAWN_MAMMOTH, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.MAMMOTH.get(), 100, 3, 5))));
        context.register(CREATE_WEIRWOOD_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(ModplacedFeatures.WEIRWOOD_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    // Helper method to register a ResourceKey for a biome modifier
    private static @NotNull ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, AGoTMod.id(name));
    }
}
