package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.datagen.MapProvider;
import net.astormofsorts.agotmod.util.SimplexNoise;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class MapManager {
    @Nullable
    public static final BufferedImage MAP_BIOME_IMAGE = getMapBiomeImage();
    @Nullable
    public static final BufferedImage MAP_HEIGHT_IMAGE = getMapHeightImage();
    private static final int PIXEL_WEIGHT = 32;
    private static final int PERLIN_STRETCH = 250;
    private static final int PERLIN_RANGE = 50;
    @NotNull
    private final SimplexNoise noise;

    public MapManager(long seed) {
        this.noise = new SimplexNoise(seed);
    }

    @Nullable
    private static BufferedImage getMapBiomeImage() {
        URL biomeMapUrl = AGoTMod.class.getResource("/" + MapProvider.BIOME_MAP_PATH);
        try {
            if (biomeMapUrl != null) {
                return ImageIO.read(biomeMapUrl);
            }
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Caught an error while reading the biome map!", e);
        }
        return null;
    }

    @Nullable
    private static BufferedImage getMapHeightImage() {
        URL heightMapUrl = AGoTMod.class.getResource("/" + MapProvider.HEIGHT_MAP_PATH);
        try {
            if (heightMapUrl != null) {
                return ImageIO.read(Objects.requireNonNull(heightMapUrl));
            }
        } catch (Exception e) {
            AGoTMod.LOGGER.error("Caught an error while reading the height map!", e);
        }
        return null;
    }

    /**
     * Gets the Color from a position on the mob
     *
     * @param x this should be a block position x
     * @param y this should be a block position z
     * @return Returns the biome color
     */
    public static @NotNull Color getBiomeColor(int x, int y) {
        if (MAP_BIOME_IMAGE != null) {
            // check if coordinate is inbound
            if (isPosInImage(MAP_BIOME_IMAGE, x, y)) {
                return new Color(MAP_BIOME_IMAGE.getRGB(x, y));
            }
        }

        // fallback
        return MapBiome.getDefault().color();
    }

    private static @Nullable Color getHeightColor(int x, int y) {
        if (MAP_HEIGHT_IMAGE != null) {
            // check if coordinate is inbound
            if (isPosInImage(MAP_HEIGHT_IMAGE, x, y)) {
                return new Color(MAP_HEIGHT_IMAGE.getRGB(x, y));
            }
        }

        // fallback
        return null;
    }

    private static int getColorHeight(int x, int y) {
        Color color = getHeightColor(x  >> 4, y  >> 4);
        if (color != null) {
            return color.getBlue() > 0 ? -color.getBlue() : color.getRed();
        } else {
            return MapBiome.getDefault().height();
        }
    }

    public static double getBiomeHeight(int x, int y) {
        double topLeft = getColorHeight(x, y);
        double topRight = getColorHeight(x + PIXEL_WEIGHT, y);
        double bottomLeft = getColorHeight(x, y + PIXEL_WEIGHT);
        double bottomRight = getColorHeight(x + PIXEL_WEIGHT, y + PIXEL_WEIGHT);
        return getHeightBetween(new double[]{topLeft, topRight, bottomLeft, bottomRight},
                (double) (x % PIXEL_WEIGHT) / PIXEL_WEIGHT, (double) (y % PIXEL_WEIGHT) / PIXEL_WEIGHT);
    }

    public double getPerlinHeight(int x, int y) {
        double perlin = getPerlin(x, y);
        double defHeight;

        // get chunk pos
        int xChunk = x >> 4;
        int yChunk = y >> 4;

        if (isCoordinateInImages(x, y)) {
            MapBiome biome = MapBiome.getByColor(getBiomeColor(xChunk, yChunk));
            perlin *= biome != null ? biome.perlinModifier() : 1;

            defHeight = getBiomeHeight(x, y);
        } else {
            defHeight = MapBiome.getDefault().height();
        }

        return perlin + defHeight;
    }

    private double getPerlin(int x, int y) {
        int scale = 32;

        double perlin = noise.noise2((double) x / PERLIN_STRETCH,(double) y / PERLIN_STRETCH) * 4;
        double d = 1;
        for (int i = 2; i <= scale; i *= 2) {
            perlin += (1d / i) * noise.noise2((double) x * i / PERLIN_STRETCH,(double) y * i / PERLIN_STRETCH) * 4;
            d += (1d / i);
        }

        perlin = perlin / d;
        perlin *= PERLIN_RANGE;

        return perlin;
    }

    private static double getHeightBetween(double[] heights, double xPercent, double zPercent) {
        double h1 = getMiddleHeight(heights[0], heights[1], xPercent);
        double h2 = getMiddleHeight(heights[2], heights[3], xPercent);
        return getMiddleHeight(h1, h2, zPercent);
    }

    private static double getMiddleHeight(double a, double b, double percentage) {
        return (a * (1 - percentage)) + (b * percentage);
    }

    private static boolean isCoordinateInImages(int x, int y) {
        int xPos = x >> 4;
        int yPos = y >> 4;
        return isPosInImage(MAP_BIOME_IMAGE, xPos, yPos) && isPosInImage(MAP_HEIGHT_IMAGE, xPos, yPos);
    }

    private static boolean isPosInImage(BufferedImage image, int x, int y) {
        return x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight();
    }
}
