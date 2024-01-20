package net.astormofsorts.agotmod.map;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiomeColorRegistry {
    public static final Exception COLOR_NOT_REGISTERED_EXCPETION = new Exception("Found a not registered color on the image");
    private static final Map<Color, ResourceKey<Biome>> BIOME_COLORS = new HashMap<>();

    static {
        register(new Color(0, 0, 255), Biomes.OCEAN);
        register(new Color(255, 255, 0), Biomes.DESERT);
        register(new Color(0, 255, 0), Biomes.PLAINS);
    }

    public static ResourceKey<Biome> register(Color color, ResourceKey<Biome> biome) {
        return BIOME_COLORS.put(color, biome);
    }

    public static ResourceKey<Biome> getBiomeByColor(Color color) {
        return BIOME_COLORS.get(color);
    }

    public static List<ResourceKey<Biome>> getAllRegisteredBiomes() {
        return BIOME_COLORS.values().stream().toList();
    }
}
