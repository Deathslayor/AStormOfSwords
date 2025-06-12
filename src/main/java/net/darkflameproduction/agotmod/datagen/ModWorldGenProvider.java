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
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModWorldGenProvider extends DatapackBuiltinEntriesProvider {

    // All Banner Pattern Registry Keys
    public static final ResourceKey<BannerPattern> ARRYN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "arryn"));
    public static final ResourceKey<BannerPattern> BAELISH = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baelish"));
    public static final ResourceKey<BannerPattern> BARATHEON = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "baratheon"));
    public static final ResourceKey<BannerPattern> BLACKWOOD = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "blackwood"));
    public static final ResourceKey<BannerPattern> BOLTON = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bolton"));
    public static final ResourceKey<BannerPattern> BRACKEN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "bracken"));
    public static final ResourceKey<BannerPattern> CLEGANE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "clegane"));
    public static final ResourceKey<BannerPattern> DAYNE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "dayne"));
    public static final ResourceKey<BannerPattern> FREY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "frey"));
    public static final ResourceKey<BannerPattern> GREYJOY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "greyjoy"));
    public static final ResourceKey<BannerPattern> HARLAW = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "harlaw"));
    public static final ResourceKey<BannerPattern> HIGHTOWER = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "hightower"));
    public static final ResourceKey<BannerPattern> KARSTARK = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "karstark"));
    public static final ResourceKey<BannerPattern> LANNISTER = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "lannister"));
    public static final ResourceKey<BannerPattern> MANDERLY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "manderly"));
    public static final ResourceKey<BannerPattern> MORMONT = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "mormont"));
    public static final ResourceKey<BannerPattern> REDWYNE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "redwyne"));
    public static final ResourceKey<BannerPattern> REED = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reed"));
    public static final ResourceKey<BannerPattern> REYNE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "reyne"));
    public static final ResourceKey<BannerPattern> ROYCE = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "royce"));
    public static final ResourceKey<BannerPattern> STARK = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "stark"));
    public static final ResourceKey<BannerPattern> TARGARYEN = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "targaryen"));
    public static final ResourceKey<BannerPattern> TARLY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tarly"));
    public static final ResourceKey<BannerPattern> TULLY = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tully"));
    public static final ResourceKey<BannerPattern> TYRELL = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "tyrell"));
    public static final ResourceKey<BannerPattern> UMBER = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "umber"));
    public static final ResourceKey<BannerPattern> VELARYON = ResourceKey.create(Registries.BANNER_PATTERN,
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "velaryon"));

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(CTRegistries.ZONES_KEY, ModZones::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModplacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
            .add(Registries.WORLD_PRESET, ModDimensionProvider::bootstrap)
            .add(Registries.BIOME, ModBiomes::boostrap)
            .add(Registries.BANNER_PATTERN, ModWorldGenProvider::bootstrapBannerPatterns);

    public ModWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(AGoTMod.MOD_ID));
    }

    private static void bootstrapBannerPatterns(BootstrapContext<BannerPattern> context) {
        context.register(ARRYN, new BannerPattern(ARRYN.location(), "arryn"));
        context.register(BAELISH, new BannerPattern(BAELISH.location(), "baelish"));
        context.register(BARATHEON, new BannerPattern(BARATHEON.location(), "baratheon"));
        context.register(BLACKWOOD, new BannerPattern(BLACKWOOD.location(), "blackwood"));
        context.register(BOLTON, new BannerPattern(BOLTON.location(), "bolton"));
        context.register(BRACKEN, new BannerPattern(BRACKEN.location(), "bracken"));
        context.register(CLEGANE, new BannerPattern(CLEGANE.location(), "clegane"));
        context.register(DAYNE, new BannerPattern(DAYNE.location(), "dayne"));
        context.register(FREY, new BannerPattern(FREY.location(), "frey"));
        context.register(GREYJOY, new BannerPattern(GREYJOY.location(), "greyjoy"));
        context.register(HARLAW, new BannerPattern(HARLAW.location(), "harlaw"));
        context.register(HIGHTOWER, new BannerPattern(HIGHTOWER.location(), "hightower"));
        context.register(KARSTARK, new BannerPattern(KARSTARK.location(), "karstark"));
        context.register(LANNISTER, new BannerPattern(LANNISTER.location(), "lannister"));
        context.register(MANDERLY, new BannerPattern(MANDERLY.location(), "manderly"));
        context.register(MORMONT, new BannerPattern(MORMONT.location(), "mormont"));
        context.register(REDWYNE, new BannerPattern(REDWYNE.location(), "redwyne"));
        context.register(REED, new BannerPattern(REED.location(), "reed"));
        context.register(REYNE, new BannerPattern(REYNE.location(), "reyne"));
        context.register(ROYCE, new BannerPattern(ROYCE.location(), "royce"));
        context.register(STARK, new BannerPattern(STARK.location(), "stark"));
        context.register(TARGARYEN, new BannerPattern(TARGARYEN.location(), "targaryen"));
        context.register(TARLY, new BannerPattern(TARLY.location(), "tarly"));
        context.register(TULLY, new BannerPattern(TULLY.location(), "tully"));
        context.register(TYRELL, new BannerPattern(TYRELL.location(), "tyrell"));
        context.register(UMBER, new BannerPattern(UMBER.location(), "umber"));
        context.register(VELARYON, new BannerPattern(VELARYON.location(), "velaryon"));
    }
}