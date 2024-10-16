package net.astormofsorts.agotmod.datagen;

import dev.tocraft.crafted.ctgen.CTerrainGeneration;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.worldgen.ModBiomeModifiers;
import net.astormofsorts.agotmod.worldgen.ModConfiguredFeatures;
import net.astormofsorts.agotmod.worldgen.ModplacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(CTerrainGeneration.MAP_ZONES_REGISTRY, ModZones::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModplacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.WORLD_PRESET, ModDimensionProvider::boostrapPreset);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AGoTMod.MOD_ID));
    }
}
