package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.datagen.MapProvider;
import net.astormofsorts.agotmod.datagen.ModDimensionProvider;
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
    private static final int PIXEL_WEIGHT = 16;
    private static final int PIXEL_STRETCH = 250;
    private static final int PIXEL_RANGE = 50;
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
            // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner of the image
            x += MAP_BIOME_IMAGE.getWidth() / 2;
            y += MAP_BIOME_IMAGE.getHeight() / 2;

            // check if coordinate is inbound
            if (isCoordinateInImage(MAP_BIOME_IMAGE, x, y)) {
                return new Color(MAP_BIOME_IMAGE.getRGB(x, y));
            }
        }

        // fallback
        return ModDimensionProvider.DEFAULT_BIOME_COLOR;
    }

    private static @NotNull Color getHeightColor(int x, int y) {
        if (MAP_HEIGHT_IMAGE != null) {
            // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner of the image
            x += MAP_HEIGHT_IMAGE.getWidth() / 2;
            y += MAP_HEIGHT_IMAGE.getHeight() / 2;

            // check if coordinate is inbound
            if (isCoordinateInImage(MAP_HEIGHT_IMAGE, x, y)) {
                return new Color(MAP_HEIGHT_IMAGE.getRGB(x, y));
            }
        }

        // fallback
        return new Color(0);
    }

    private static double getColorHeight(int x, int y) {
        Color color = getHeightColor(x / PIXEL_WEIGHT, y / PIXEL_WEIGHT);
        return color.getBlue() < 0 ? color.getRed() : -color.getBlue();
    }

    private static double getBiomeHeight(int x, int y) {
        double topLeft = getColorHeight(x, y);
        double topRight = getColorHeight(x + PIXEL_WEIGHT, y);
        double bottomLeft = getColorHeight(x, y + PIXEL_WEIGHT);
        double bottomRight = getColorHeight(x + PIXEL_WEIGHT, y + PIXEL_WEIGHT);
        return getHeightBetween(new double[]{topLeft, topRight, bottomLeft, bottomRight},
                (double) (x % PIXEL_WEIGHT) / PIXEL_WEIGHT, (double) (y % PIXEL_WEIGHT) / PIXEL_WEIGHT);
    }

    public double getPerlinHeight(int x, int y) {
        double perlin = getPerlin(x, y) * 0.5;

        // TODO: Replace with something more smooth for water generation
        if (getBiomeColor(x >> 2, y >> 2).getBlue() == 255) {
            perlin = 0;
        }

        double defHeight = getBiomeHeight(x >> 2, y >> 2);
        return perlin + defHeight;
    }

    private double getPerlin(int x, int y) {
        int scale = 32;

        double perlin = noise.noise2((double) x / PIXEL_STRETCH,(double) y / PIXEL_STRETCH);
        double d = 1;
        for (int i = 2; i <= scale; i *= 2) {
            perlin += (1d / i) * noise.noise2((double) x * i / PIXEL_STRETCH,(double) y * i / PIXEL_STRETCH);
            d += (1d / i);
        }

        perlin = perlin / d;
        perlin *= PIXEL_RANGE;

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

    private static boolean isCoordinateInImage(BufferedImage image, int x, int y) {
        return x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight();
    }
}
