package net.stormofsorts.agotmod.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.stormofsorts.agotmod.AGoTMod;

public class ModBiomeModifiers {
    // Define a ResourceKey for the biome modifier to add tin ore features
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = registerKey("add_tin_ore");

    // Bootstrap method for initializing biome modifiers
    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        // Obtain references to placed features and biomes from the data generation context
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        // Register the biome modifier to add tin ore features
        context.register(ADD_TIN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                // Specify the target biomes (in this case, the overworld biomes)
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                // Specify the features to add (in this case, tin ore features)
                HolderSet.direct(placedFeatures.getOrThrow(ModplacedFeatures.TIN_ORE_PLACED_KEY)),
                // Specify the generation step for the features (underground ores)
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    // Helper method to register a ResourceKey for a biome modifier
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(AGoTMod.MOD_ID, name));
    }
}
