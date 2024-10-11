package net.astormofsorts.agotmod.datagen;

import dev.tocraft.crafted.ctgen.CTerrainGeneration;
import dev.tocraft.crafted.ctgen.zone.Zone;
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
        HolderGetter<Zone> ZoneRegistry = context.lookup(CTerrainGeneration.MAP_ZONES_REGISTRY);
        context.register(KNOWN_WORLD_PRESET, new WorldPreset(Map.of(LevelStem.OVERWORLD, new LevelStem(dimTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD), MapBasedChunkGenerator.of(new MapSettingsBuilder()
                .setBiomeMapId(KNOWN_WORLD)
                .setZones(getZones(ZoneRegistry))
                .setDefaultBiome(ZoneRegistry.getOrThrow(ModZones.DEEP_COLD_OCEAN))
                .setSpawnX(3500)
                .setSpawnY(6000)
                .setPixelsAreChunks(false)
                .build())))));
    }

    public static List<Holder<Zone>> getZones(HolderGetter<Zone> ZoneRegistry) {
        return List.of(
                ZoneRegistry.getOrThrow(ModZones.MANGOVES),
                ZoneRegistry.getOrThrow(ModZones.TAIGA),
                ZoneRegistry.getOrThrow(ModZones.SNOWY_PLAINS),
                ZoneRegistry.getOrThrow(ModZones.PLAINS),
                ZoneRegistry.getOrThrow(ModZones.WINDSWEPT_HILLS),
                ZoneRegistry.getOrThrow(ModZones.SNOWY_MOUNTAINS),
                ZoneRegistry.getOrThrow(ModZones.SWAMP),
                ZoneRegistry.getOrThrow(ModZones.SAVANNA_PLATEAU),
                ZoneRegistry.getOrThrow(ModZones.SAVANNA),
                ZoneRegistry.getOrThrow(ModZones.FOREST),
                ZoneRegistry.getOrThrow(ModZones.MEADOW),
                ZoneRegistry.getOrThrow(ModZones.THE_WALL),
                ZoneRegistry.getOrThrow(ModZones.SNOWY_TAIGA),
                ZoneRegistry.getOrThrow(ModZones.SNOWY_FLATS),
                ZoneRegistry.getOrThrow(ModZones.SUNFLOWER_PLAINS),
                ZoneRegistry.getOrThrow(ModZones.RIVER),
                ZoneRegistry.getOrThrow(ModZones.FROZEN_RIVER),
                ZoneRegistry.getOrThrow(ModZones.LAKE),
                ZoneRegistry.getOrThrow(ModZones.FROZEN_LAKE),
                ZoneRegistry.getOrThrow(ModZones.COLD_OCEAN),
                ZoneRegistry.getOrThrow(ModZones.DEEP_COLD_OCEAN),
                ZoneRegistry.getOrThrow(ModZones.BASALT_DELTAS),
                ZoneRegistry.getOrThrow(ModZones.SNOWY_SLOPES),
                ZoneRegistry.getOrThrow(ModZones.FROZEN_MOUNTAINS),
                ZoneRegistry.getOrThrow(ModZones.OLD_GROWTH_SPRUCE_TAIGA)
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
