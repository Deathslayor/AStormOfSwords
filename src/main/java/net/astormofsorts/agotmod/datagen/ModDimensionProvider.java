package net.astormofsorts.agotmod.datagen;

import dev.tocraft.crafted.ctgen.CTerrainGeneration;
import dev.tocraft.crafted.ctgen.map.MapBiome;
import dev.tocraft.crafted.ctgen.worldgen.MapBasedBiomeChunkGenerator;
import dev.tocraft.crafted.ctgen.worldgen.MapSettings;
import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static net.astormofsorts.agotmod.AGoTMod.MOD_ID;

public class ModDimensionProvider {
    public final static ResourceLocation KNOWN_WORLD = new ResourceLocation(MOD_ID, "known_world");
    public final static ResourceKey<DimensionType> KNOWN_WORLD_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(MOD_ID, "known_world_type"));
    public final static ResourceKey<WorldPreset> KNOWN_WORLD_PRESET = ResourceKey.create(Registries.WORLD_PRESET, KNOWN_WORLD);

    public static void bootstrapType(BootstapContext<DimensionType> context) {
        context.register(KNOWN_WORLD_TYPE, new DimensionType(OptionalLong.empty(), true, false, false, true, 1, true, false, -64, 384, 384, BlockTags.INFINIBURN_OVERWORLD, new ResourceLocation(MOD_ID, "renderer"), 0, new DimensionType.MonsterSettings(false, false, UniformInt.of(0, 0), 0)));
    }

    public static void boostrapPreset(BootstapContext<WorldPreset> context) {
        HolderGetter<DimensionType> dimTypeRegistry = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<MapBiome> mapBiomeRegistry = context.lookup(CTerrainGeneration.MAP_BIOME_REGISTRY);
        context.register(KNOWN_WORLD_PRESET, new WorldPreset(Map.of(LevelStem.OVERWORLD, new LevelStem(dimTypeRegistry.getOrThrow(KNOWN_WORLD_TYPE), MapBasedBiomeChunkGenerator.of(new MapSettings(
                KNOWN_WORLD,
                getMapBiomeList(mapBiomeRegistry),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.DEEP_COLD_OCEAN),
                0, 66, -32, 279, 64,
                23, 250, 3,
                Optional.empty(), Optional.empty()
        ))))));
    }

    public static List<Holder<MapBiome>> getMapBiomeList(HolderGetter<MapBiome> mapBiomeRegistry) {
        return List.of(
                mapBiomeRegistry.getOrThrow(ModMapBiomes.MANGOVES),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.TAIGA),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_PLAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.PLAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.WINDSWEPT_HILLS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.JAGGED_PEAKS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SWAMP),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SAVANNA_PLATEAU),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SAVANNA),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.FOREST),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.MEADOW),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.THE_WALL),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_TAIGA),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_SLOPES),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SUNFLOWER_PLAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.RIVER),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.FROZEN_RIVER),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.LAKE),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.FROZEN_LAKE),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.COLD_OCEAN),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.DEEP_COLD_OCEAN)
        );
    }

    public static BufferedImage getOriginalMapImage() {
        URL orignalMapUrl = AGoTMod.class.getResource("/map.png");
        try {
            return ImageIO.read(Objects.requireNonNull(orignalMapUrl));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome map located at: " + (orignalMapUrl != null ? orignalMapUrl.getPath() : "invalid location"), e);
        }
    }
}
