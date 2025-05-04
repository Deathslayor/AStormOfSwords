package net.darkflameproduction.agotmod.datagen;

import dev.tocraft.ctgen.util.Noise;
import dev.tocraft.ctgen.worldgen.MapBasedChunkGenerator;
import dev.tocraft.ctgen.worldgen.MapSettingsBuilder;
import dev.tocraft.ctgen.xtend.CTRegistries;
import dev.tocraft.ctgen.xtend.carver.NoiseCarver;
import dev.tocraft.ctgen.zone.Zone;
import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModDimensionProvider {
    public final static ResourceLocation KNOWN_WORLD = AGoTMod.id("known_world");
    public final static ResourceKey<WorldPreset> KNOWN_WORLD_PRESET = ResourceKey.create(Registries.WORLD_PRESET, KNOWN_WORLD);

    public static void bootstrap(BootstrapContext<WorldPreset> context) {
        HolderGetter<DimensionType> dimTypeRegistry = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<Zone> zoneRegistry = context.lookup(CTRegistries.ZONES_KEY);
        context.register(KNOWN_WORLD_PRESET, new WorldPreset(Map.of(LevelStem.OVERWORLD, new LevelStem(dimTypeRegistry.getOrThrow(BuiltinDimensionTypes.OVERWORLD), MapBasedChunkGenerator.of(new MapSettingsBuilder()
                .setBiomeMapId(KNOWN_WORLD)
                .setZones(getZones(zoneRegistry))
                .setDefaultBiome(zoneRegistry.getOrThrow(ModZones.DEEP_COLD_OCEAN))
                .setSpawnX(3500)
                .setSpawnY(6000)
                .setPixelsAreChunks(false)
                .setCarver(new NoiseCarver(new Noise(List.of(new Noise.Octave(1, 1), new Noise.Octave(0.5f, 1)), 63, 47), 0.95))
                .build())))));
    }

    public static @Unmodifiable List<Holder<Zone>> getZones(@NotNull HolderGetter<Zone> ZoneRegistry) {
        return List.of(
                ZoneRegistry.getOrThrow(ModZones.THE_WALL),
                ZoneRegistry.getOrThrow(ModZones.HAUNTED_FOREST),
                ZoneRegistry.getOrThrow(ModZones.FROZEN_RIVER),
                ZoneRegistry.getOrThrow(ModZones.FROZEN_LAKE),
                ZoneRegistry.getOrThrow(ModZones.COLD_OCEAN),
                ZoneRegistry.getOrThrow(ModZones.DEEP_COLD_OCEAN),
                ZoneRegistry.getOrThrow(ModZones.BASALT_DELTAS),
                ZoneRegistry.getOrThrow(ModZones.FROSTFANG_FOOTHILLS),
                ZoneRegistry.getOrThrow(ModZones.FROSTFANGS),
                ZoneRegistry.getOrThrow(ModZones.VALLEY_OF_THENN),
                ZoneRegistry.getOrThrow(ModZones.LANDS_OF_ALWAYS_WINTER),
                ZoneRegistry.getOrThrow(ModZones.WOLFSWOOD),
                ZoneRegistry.getOrThrow(ModZones.WOLFSWOOD_CLEARING),
                ZoneRegistry.getOrThrow(ModZones.IRONWOOD),
                ZoneRegistry.getOrThrow(ModZones.NORTHERN_HILLS),
                ZoneRegistry.getOrThrow(ModZones.NORTHERN_MOUNTAINS),
                ZoneRegistry.getOrThrow(ModZones.THE_NORTH),
                ZoneRegistry.getOrThrow(ModZones.THE_NORTH),
                ZoneRegistry.getOrThrow(ModZones.RILLS),
                ZoneRegistry.getOrThrow(ModZones.NORTHERN_RIVER),
                ZoneRegistry.getOrThrow(ModZones.NORTHERN_LAKE),
                ZoneRegistry.getOrThrow(ModZones.STONY_SHORES),
                ZoneRegistry.getOrThrow(ModZones.THE_NECK),
                ZoneRegistry.getOrThrow(ModZones.THE_NECK_RIVER),
                ZoneRegistry.getOrThrow(ModZones.BARROWLANDS)
        );
    }

    public static BufferedImage getOriginalMapImage() {
        URL orignalMapUrl = AGoTMod.class.getResource("/assets/agotmod/textures/gui/known_world.png");
        try {
            return ImageIO.read(Objects.requireNonNull(orignalMapUrl));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load biome map located at: " + (orignalMapUrl != null ? orignalMapUrl.getPath() : "invalid location"), e);
        }
    }
}
