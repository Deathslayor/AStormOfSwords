package net.darkflameproduction.agotmod.datagen;

import dev.tocraft.ctgen.xtend.CTRegistries;
import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.worldgen.ModBiomeModifiers;
import net.darkflameproduction.agotmod.worldgen.ModConfiguredFeatures;
import net.darkflameproduction.agotmod.worldgen.ModplacedFeatures;
import net.darkflameproduction.agotmod.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(CTRegistries.ZONES_KEY, ModZones::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModplacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.WORLD_PRESET, ModDimensionProvider::bootstrap)
            .add(Registries.BIOME, ModBiomes::boostrap);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AGoTMod.MOD_ID));
    }
}
