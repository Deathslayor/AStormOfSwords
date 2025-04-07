package net.darkflameproduction.agotmod.worldgen.biome;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.ModEntities;
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
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

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
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        //BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        //BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static void addFlowerFeaturesNorth(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        // Always add features in the same alphabetical/consistent order
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

    private static void addFlowerFeaturesBeyondTheWall(BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context) {
        // Always add features in the same alphabetical/consistent order
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.FROSTFIRE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THISTLE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.THORN_BUSH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WINTER_ROSE_BUSH_KEY);
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
    }

    private static void addForestIronwood (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
    }

    private static void addForestHauntedForest (BiomeGenerationSettings.Builder biomeBuilder, BootstrapContext<Biome> context){
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.ASH_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.CHESTNUT_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.IRONWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.SENTINEL_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.PINE_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.WEIRWOOD_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModplacedFeatures.OAK2_KEY);
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
    }





    //Beyond The Wall
    //Lands Of Always Winter
    public static Biome alwayswinter(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MAMMOTH.get(), 2, 3, 5));

        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        //BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        //BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
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
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MAMMOTH.get(), 2, 3, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4, 4));

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addFlowerFeaturesBeyondTheWall(biomeBuilder, context);
        addForestHauntedForest(biomeBuilder, context);


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
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MAMMOTH.get(), 2, 3, 5));
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4, 4));

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
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
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MAMMOTH.get(), 2, 3, 5));
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4, 4));

        //Add Mob Spawns Here

        //Features
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        //we need to follow the same order as vanilla biomes for the BiomeDefaultFeatures
        globalOverworldGeneration(biomeBuilder);
        //BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
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
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.MAMMOTH.get(), 2, 3, 5));
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4, 4));

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
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addFlowerFeaturesBeyondTheWall(biomeBuilder, context);


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
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4, 4));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFlowerFeaturesNorth(biomeBuilder, context);
        addForestFeaturesWolfswood(biomeBuilder, context);


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
        spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 2, 4, 4));

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addForestGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFlowerFeaturesNorth(biomeBuilder, context);
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
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFlowerFeaturesNorth(biomeBuilder, context);
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
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        addFlowerFeaturesNorth(biomeBuilder, context);
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
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);


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
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addFlowerFeaturesNorth(biomeBuilder, context);
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
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        //BiomeDefaultFeatures.addExtraGold(biomeBuilder);
        addFlowerFeaturesNorth(biomeBuilder, context);
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
}

