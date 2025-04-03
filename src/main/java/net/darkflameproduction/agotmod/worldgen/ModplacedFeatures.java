package net.darkflameproduction.agotmod.worldgen;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.worldgen.ore.ModOrePlacement;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.block.Blocks;


import java.util.List;

public class ModplacedFeatures {

    // Define a ResourceKey for the placed feature of tin ore
    public static final ResourceKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
    public static final ResourceKey<PlacedFeature> WEIRWOOD_KEY = registerKey("weirwood");
    public static final ResourceKey<PlacedFeature> OAK2_KEY = registerKey("oak");
    public static final ResourceKey<PlacedFeature> SENTINEL_KEY = registerKey("sentinel");
    public static final ResourceKey<PlacedFeature> PINE_KEY = registerKey("pine");
    public static final ResourceKey<PlacedFeature> IRONWOOD_KEY = registerKey("ironwood");
    public static final ResourceKey<PlacedFeature> HAWTHORN_KEY = registerKey("hawthorn");
    public static final ResourceKey<PlacedFeature> CHESTNUT_KEY = registerKey("chestnut");
    public static final ResourceKey<PlacedFeature> CEDAR_KEY = registerKey("cedar");
    public static final ResourceKey<PlacedFeature> BEECH_KEY = registerKey("beech");
    public static final ResourceKey<PlacedFeature> ASH_KEY = registerKey("ash");
    public static final ResourceKey<PlacedFeature> BLACKBARK_KEY = registerKey("blackbark");
    public static final ResourceKey<PlacedFeature> ASPEN_KEY = registerKey("aspen");
    public static final ResourceKey<PlacedFeature> ALDER_KEY = registerKey("alder");


    // Bootstrap method for initializing placed features
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        // Get a holder for configured features from the context
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // Register the placed feature for tin ore with specified configuration and modifiers
        register(context, TIN_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_TIN_ORE),
                ModOrePlacement.commonOrePlacement(14,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80))));

        register(context, WEIRWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.WEIRWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.1f, 1),
                        ModBLocks.WEIRWOOD_SAPLING.get()));

        register(context, OAK2_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OAK2_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        Blocks.OAK_SAPLING));


        register(context, SENTINEL_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SENTINEL_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.SENTINEL_SAPLING.get()));

        register(context, PINE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.PINE_SAPLING.get()));

        register(context, IRONWOOD_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.IRONWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.IRONWOOD_SAPLING.get()));

        register(context, HAWTHORN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAWTHORN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.HAWTHORN_SAPLING.get()));

        register(context, CHESTNUT_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHESTNUT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.CHESTNUT_SAPLING.get()));

        register(context, CEDAR_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CEDAR_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.CEDAR_SAPLING.get()));

        register(context, BEECH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BEECH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.BEECH_SAPLING.get()));

        register(context, ASH_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASH_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.ASH_SAPLING.get()));

        register(context, BLACKBARK_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLACKBARK_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.BLACKBARK_SAPLING.get()));

        register(context, ASPEN_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ASPEN_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.ASPEN_SAPLING.get()));

        register(context, ALDER_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALDER_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBLocks.ALDER_SAPLING.get()));
    }

    // Helper method to register a ResourceKey for a placed feature
    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, AGoTMod.id(name));
    }

    // Helper method to register a placed feature
    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        // Use the context to register a new PlacedFeature with the specified key, configuration, and modifiers
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
