package net.astormofsorts.agotmod.datagen;

import dev.tocraft.crafted.ctgen.CTerrainGeneration;
import dev.tocraft.crafted.ctgen.biome.CarverSetting;
import dev.tocraft.crafted.ctgen.biome.MapBiome;
import dev.tocraft.crafted.ctgen.worldgen.MapBasedChunkGenerator;
import dev.tocraft.crafted.ctgen.worldgen.MapSettingsBuilder;
import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
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
    public final static ResourceKey<WorldPreset> KNOWN_WORLD_PRESET = ResourceKey.create(Registries.WORLD_PRESET, KNOWN_WORLD);

    public static void boostrapPreset(BootstapContext<WorldPreset> context) {
        HolderGetter<DimensionType> dimTypeRegistry = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<MapBiome> mapBiomeRegistry = context.lookup(CTerrainGeneration.MAP_BIOME_REGISTRY);
        context.register(KNOWN_WORLD_PRESET, new WorldPreset(Map.of(LevelStem.OVERWORLD, new LevelStem(dimTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD), MapBasedChunkGenerator.of(new MapSettingsBuilder()
                .setBiomeMapId(KNOWN_WORLD)
                .setBiomeData(getMapBiomeList(mapBiomeRegistry))
                .setDefaultBiome(mapBiomeRegistry.getOrThrow(ModMapBiomes.DEEP_COLD_OCEAN))
                .build())))));
    }

    public static List<Holder<MapBiome>> getMapBiomeList(HolderGetter<MapBiome> mapBiomeRegistry) {
        return List.of(
                mapBiomeRegistry.getOrThrow(ModMapBiomes.MANGOVES),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.TAIGA),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_PLAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.PLAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.WINDSWEPT_HILLS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_MOUNTAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SWAMP),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SAVANNA_PLATEAU),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SAVANNA),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.FOREST),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.MEADOW),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.THE_WALL),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_TAIGA),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_FLATS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SUNFLOWER_PLAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.RIVER),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.FROZEN_RIVER),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.LAKE),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.FROZEN_LAKE),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.COLD_OCEAN),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.DEEP_COLD_OCEAN),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.BASALT_DELTAS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.SNOWY_SLOPES),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.FROZEN_MOUNTAINS),
                mapBiomeRegistry.getOrThrow(ModMapBiomes.OLD_GROWTH_SPRUCE_TAIGA)
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
