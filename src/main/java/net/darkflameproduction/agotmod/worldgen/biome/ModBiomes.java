package net.darkflameproduction.agotmod.worldgen.biome;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.darkflameproduction.agotmod.worldgen.ModplacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModBiomes {
    public static final ResourceKey<Biome> LANDS_OF_ALWAYS_WINTER = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("lands_of_always_winter"));
    public static final ResourceKey<Biome> HAUNTED_FOREST = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("haunted_forest"));
    public static final ResourceKey<Biome> FROSTFANG_FOOTHILLS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("frostfang_foothills"));
    public static final ResourceKey<Biome> FROSTFANGS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("frostfangs"));
    public static final ResourceKey<Biome> VALLEY_OF_THENN = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("valley_of_thenn"));
    public static final ResourceKey<Biome> WOLFSWOOD = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("wolfswood"));
    public static final ResourceKey<Biome> IRONWOOD = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("ironwood"));
    public static final ResourceKey<Biome> WOLFSWOOD_CLEARING = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("wolfswood_clearing"));
    public static final ResourceKey<Biome> NORTHERN_HILLS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_hills"));
    public static final ResourceKey<Biome> NORTHERN_MOUNTAINS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_mountains"));
    public static final ResourceKey<Biome> BARROWLANDS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("barrowlands"));
    public static final ResourceKey<Biome> THE_NORTH = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("the_north"));
    public static final ResourceKey<Biome> RILLS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("rills"));
    public static final ResourceKey<Biome> NORTHERN_WATERS = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("northern_waters"));
    public static final ResourceKey<Biome> STONY_SHORES = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("stony_shores"));
    public static final ResourceKey<Biome> THE_NECK = ResourceKey.create(Registries.BIOME,
            AGoTMod.id("the_neck"));

    public static void boostrap(BootstrapContext<Biome> context) {
        context.register(LANDS_OF_ALWAYS_WINTER, alwayswinter(context));
        context.register(HAUNTED_FOREST, hauntedforest(context));
        context.register(FROSTFANG_FOOTHILLS, frostfangfoothills(context));
        context.register(FROSTFANGS, frostfangs(context));
        context.register(VALLEY_OF_THENN, valleyofthenn(context));
        context.register(WOLFSWOOD, wolfswood(context));
        context.register(IRONWOOD, ironwood(context));
        context.register(WOLFSWOOD_CLEARING, wolfswoodclearing(context));
        context.register(NORTHERN_HILLS, northernhills(context));
        context.register(NORTHERN_MOUNTAINS, northernmountains(context));
        context.register(BARROWLANDS, barrowlands(context));
        context.register(THE_NORTH, the_north(context));
        context.register(RILLS, rills(context));
        context.register(NORTHERN_WATERS, northernwaters(context));
        context.register(STONY_SHORES, stonyshores(context));
        context.register(THE_NECK, theneck(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        //BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        //BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static void addForestFeaturesWolfswood (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);

    }

    private static void addForestIronwood (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
    }

    private static void addForestHauntedForest (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
    }

    private static void addTreesNorthPlains (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.HAWTHORN_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.BEECH_RARE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.GRASS_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FORGET_ME_NOT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LIVERWORT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.LUNGWORT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PENNYROYAL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.RED_ROSE_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
    }

    private static void addModOres (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TIN_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.IRON_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.COAL_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.COPPER_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.GOLD_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.LAPIS_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.EMERALD_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.DIAMONDS_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.BLOODSTONE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CHALCEDONY_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.AMBER_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.AMETHYST_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.CARNELIAN_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.GARNET_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.JADE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.JASPER_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.MALACHITE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.RUBY_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.ONYX_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.OPAL_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.SAPPHIRE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.MOONSTONE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TIGERS_EYE_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TOPAZ_ORE_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, ModplacedFeatures.TOURMALINE_ORE_PLACED_KEY);
    }

    private static void addRiverFeatures (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CLAY_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.QUAGMIRE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SEAGRASS_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.KELP_KEY);
    }

    private static void addRiverMobs(MobSpawnSettings.Builder spawnBuilder) {
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.SALMON, 1, 1, 5));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.COD, 1, 1, 5));
    }

    private static void addForestMobs(MobSpawnSettings.Builder spawnBuilder) {
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 4, 4));
    }



    //Beyond The Wall
    //Lands Of Always Winter
    public static Biome alwayswinter(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();



        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-1.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0xd9d9d9)
                        .foliageColorOverride(0xd9d9d9)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    //Haunted Forest
    public static Biome hauntedforest(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addForestHauntedForest(biomeBuilder, context);
        addForestMobs(spawnBuilder);


        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FIR_KEY);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0x577a5f)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    //Frostfang Foothills
    public static Biome frostfangfoothills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_TAIGA);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-0.8f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0x577a5f)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    //Frostfangs
    public static Biome frostfangs(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_TAIGA);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-1f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x7988b5)
                        .grassColorOverride(0x577a5f)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    //Valley Of Thenn
    public static Biome valleyofthenn(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0x5a6ef2)
                        .grassColorOverride(0x5c9967)
                        .foliageColorOverride(0x324d38)
                        .fogColor(0x000000)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MAMMOTH_SOUNDS)).build())
                .build();
    }

    //The North
//Wolfswood
    public static Biome wolfswood(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        addForestFeaturesWolfswood(biomeBuilder, context);
        addForestMobs(spawnBuilder);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x428a49)
                        .foliageColorOverride(0x428a49)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    // Ironwood
    public static Biome ironwood(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        addForestMobs(spawnBuilder);
        addForestIronwood(biomeBuilder, context);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x428a49)
                        .foliageColorOverride(0x428a49)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.FOREST_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    // Wolfswood Clearing
    public static Biome wolfswoodclearing(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);

        addModOres(biomeBuilder, context);
        addTreesNorthPlains(biomeBuilder, context);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    // Northern Hills
    public static Biome northernhills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        addTreesNorthPlains(biomeBuilder, context);
        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    // Northern Mountains
    public static Biome northernmountains(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addModOres(biomeBuilder, context);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(-0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .ambientLoopSound(ModSounds.WINTER_WIND)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    //Barrowlands
    public static Biome barrowlands(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addTreesNorthPlains(biomeBuilder, context);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_TAIGA);



        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    //The North
    public static Biome the_north(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addTreesNorthPlains(biomeBuilder, context);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_TAIGA);



        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    //The Rills
    public static Biome rills(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addRiverFeatures(biomeBuilder, context);
        addTreesNorthPlains(biomeBuilder, context);
        addRiverMobs(spawnBuilder);

        //biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_TAIGA);



        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x35a641)
                        .foliageColorOverride(0x35a641)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND)).build())
                .build();
    }

    // Northern Waters
    public static Biome northernwaters(BootstrapContext<Biome> context) {
        // Create mob spawns builder
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        // Add fish spawns
        // Biome generation settings
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        addRiverFeatures(biomeBuilder, context);
        addRiverMobs(spawnBuilder);
        addModOres(biomeBuilder, context);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND))
                        .build())
                .build();
    }

    // stonyshores
    public static Biome stonyshores(BootstrapContext<Biome> context) {
        // Create mob spawns builder
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        // Biome generation settings
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        addTreesNorthPlains(biomeBuilder, context);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x3d4ed1)
                        .waterFogColor(0x0c113b)
                        .skyColor(0xa7addb)
                        .grassColorOverride(0x47a651)
                        .foliageColorOverride(0x47a651)
                        .fogColor(0xa7addb)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND))
                        .build())
                .build();
    }

    // The Neck
    public static Biome theneck(BootstrapContext<Biome> context) {
        // Create mob spawns builder
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();


        // Biome generation settings
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(
                        context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));
        BiomeDefaultFeatures.addMangroveSwampVegetation(biomeBuilder);
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addSavannaExtraGrass(biomeBuilder);
        addModOres(biomeBuilder, context);
        addRiverFeatures(biomeBuilder, context);
        addRiverMobs(spawnBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.5f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x294018)
                        .waterFogColor(0x1f1308)
                        .skyColor(0x30523b)
                        .grassColorOverride(0x27471f)
                        .foliageColorOverride(0x27471f)
                        .fogColor(0x638a6d)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.WINTER_WIND))
                        .build())
                .build();
    }



}

