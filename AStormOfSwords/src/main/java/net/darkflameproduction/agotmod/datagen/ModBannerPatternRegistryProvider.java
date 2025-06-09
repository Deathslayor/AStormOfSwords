package net.darkflameproduction.agotmod.datagen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModBannerPatternRegistryProvider extends DatapackBuiltinEntriesProvider {

    public static final ResourceKey<BannerPattern> TARGARYEN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen"));

    public ModBannerPatternRegistryProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, createBuilder(), Set.of(AGoTMod.MOD_ID));
    }

    private static RegistrySetBuilder createBuilder() {
        return new RegistrySetBuilder()
                .add(Registries.BANNER_PATTERN, ModBannerPatternRegistryProvider::bootstrap);
    }

    private static void bootstrap(BootstrapContext<BannerPattern> context) {
        context.register(TARGARYEN, new BannerPattern(TARGARYEN.location(), "targaryen"));
    }
}