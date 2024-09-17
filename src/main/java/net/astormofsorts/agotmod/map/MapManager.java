package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.datagen.MapProvider;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.synth.SimplexNoise;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.function.BiFunction;


public class MapManager {
    private static final BufferedImage MAP_BIOME_IMAGE = getMapBiomeImage();
    private static final BufferedImage MAP_HEIGHT_IMAGE = getMapHeightImage();
    private static final int PIXEL_WEIGHT = 64;
    private static final int PERLIN_STRETCH = 250;
    private static final int PERLIN_RANGE = 8;
    private static final int PERLIN_DETAIL = 8;
    @NotNull
    private final SimplexNoise noise;

    public MapManager(RandomSource randSource) {
        this.noise = new SimplexNoise(randSource);
    }

    private static BufferedImage getMapBiomeImage() {
        URL biomeMapUrl = AGoTMod.class.getResource("/" + MapProvider.BIOME_MAP_PATH);
        try {
            return ImageIO.read(Objects.requireNonNull(biomeMapUrl));
        } catch (IOException e) {
            throw new RuntimeException("Caught an error while reading the biome map!", e);
        }
    }

    private static BufferedImage getMapHeightImage() {
        URL heightMapUrl = AGoTMod.class.getResource("/" + MapProvider.HEIGHT_MAP_PATH);
        try {
            return ImageIO.read(Objects.requireNonNull(heightMapUrl));
        } catch (Exception e) {
            throw new RuntimeException("Caught an error while reading the height map!", e);
        }
    }

    /**
     * Gets the Color from a position on the map
     *
     * @param pX this should be a block position x
     * @param pY this should be a block position z
     * @return Returns the biome color
     */
    public static @Nullable Color getBiomeColor(int pX, int pY) {
        int x = xOffset(pX);
        int y = yOffset(pY);
        // check if coordinate is inbound
        if (isPosInImage(MAP_BIOME_IMAGE, x, y)) {
            return new Color(MAP_BIOME_IMAGE.getRGB(x, y));
        }

        // fallback
        return null;
    }

    private static @Nullable Color getHeightColor(int pX, int pY) {
        int x = xOffset(pX);
        int y = yOffset(pY);
        // check if coordinate is inbound
        if (isPosInImage(MAP_HEIGHT_IMAGE, x, y)) {
            return new Color(MAP_HEIGHT_IMAGE.getRGB(x, y));
        }

        // fallback
        return null;
    }

    private static int getColorHeight(int x, int y) {
        Color color = getHeightColor(x >> 2, y >> 2);
        if (color != null) {
            return color.getBlue() > 0 ? -color.getBlue() : color.getRed();
        } else {
            return MapBiome.getDefault().height();
        }
    }

    public double getHeight(int x, int y) {
        return getPerlinHeight(x,y);
    }

    private double getPerlinMultiplier(int x, int y) {
        return MapBiome.getByColor(getBiomeColor(x >> 2, y >> 2)).perlinModifier();
    }

    private double getPerlinHeight(int x, int y) {
        double perlin = getPerlin(x, y) * getTransformedMapValue(x, y, this::getPerlinMultiplier);
        double defHeight = getTransformedMapValue(x, y, (a, b) -> (double) getColorHeight(a, b));
        return perlin + defHeight;
    }

    private double getPerlin(int x, int y) {
        double perlin = noise.getValue((double) x / PERLIN_STRETCH, (double) y / PERLIN_STRETCH) * 4;
        double d = 1;
        for (int i = 2; i <= PERLIN_DETAIL; i *= 2) {
            perlin += (1d / i) * noise.getValue((double) x * i / PERLIN_STRETCH, (double) y * i / PERLIN_STRETCH) * 4;
            d += (1d / i);
        }

        perlin = perlin / d;
        perlin *= PERLIN_RANGE;

        return perlin;
    }

    private static double getTransformedMapValue(int x, int y, BiFunction<Integer, Integer, Double> function) {
        // Determine the base coordinates for the current biome grid
        int baseX = (x / PIXEL_WEIGHT) * PIXEL_WEIGHT;
        int baseY = (y / PIXEL_WEIGHT) * PIXEL_WEIGHT;

        // Adjust base coordinates for negative values
        if (x < 0) baseX -= PIXEL_WEIGHT;
        if (y < 0) baseY -= PIXEL_WEIGHT;

        // Sample the four surrounding biome heights at the corners of the grid
        double h00 = function.apply(baseX, baseY); // Top-left
        double h10 = function.apply(baseX + PIXEL_WEIGHT, baseY); // Top-right
        double h01 = function.apply(baseX, baseY + PIXEL_WEIGHT); // Bottom-left
        double h11 = function.apply(baseX + PIXEL_WEIGHT, baseY + PIXEL_WEIGHT); // Bottom-right

        // Calculate the fractional positions within the grid, relative to base coordinates
        double xPercent = (double)(x - baseX) / PIXEL_WEIGHT;
        double yPercent = (double)(y - baseY) / PIXEL_WEIGHT;

        // Ensure fractional values are within [0, 1] (handling negative values)
        xPercent = Math.abs(xPercent);
        yPercent = Math.abs(yPercent);

        // Perform bilinear interpolation to smoothly transition between the biome heights
        return bilinearInterpolation(h00, h10, h01, h11, xPercent, yPercent);
    }

    private static double bilinearInterpolation(double h00, double h10, double h01, double h11, double xPercent, double yPercent) {
        // Calculate the weights for each corner based on the fractional distances
        double w00 = (1 - xPercent) * (1 - yPercent); // Top-left corner weight
        double w10 = xPercent * (1 - yPercent);       // Top-right corner weight
        double w01 = (1 - xPercent) * yPercent;       // Bottom-left corner weight
        double w11 = xPercent * yPercent;             // Bottom-right corner weight

        // Perform weighted bilinear interpolation
        return h00 * w00 + h10 * w10 + h01 * w01 + h11 * w11;
    }

    private static boolean isPosInImage(BufferedImage image, int x, int y) {
        return x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight();
    }


    // used to move the map in order to spawn in the center
    public static int xOffset(int x) {
        return x + (Objects.requireNonNull(MAP_BIOME_IMAGE).getWidth() / 2);
    }
    public static int yOffset(int y) {
        return y + (Objects.requireNonNull(MAP_BIOME_IMAGE).getHeight() / 2);
    }
}
