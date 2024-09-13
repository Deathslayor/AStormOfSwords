package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.datagen.MapProvider;
import net.astormofsorts.agotmod.datagen.ModDimensionProvider;
import net.minecraft.core.QuartPos;
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
    private static final int PIXEL_WEIGHT = 5;
    @Nullable
    private final BufferedImage noisedHeightmap;

    public MapManager(long seed) {
        this.noisedHeightmap = MAP_HEIGHT_IMAGE != null ? MapUtils.addNoise(MAP_HEIGHT_IMAGE, seed) : null;
    }

    @Nullable
    private static BufferedImage getMapBiomeImage() {
        URL biomeMapUrl = AGoTMod.class.getResource("/" + MapProvider.BIOME_MAP_PATH);
        try {
            if (biomeMapUrl != null) {
                return ImageIO.read(biomeMapUrl);
            }
        } catch (IOException ignored) {
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
        } catch (IOException ignored) {
        }
        return null;
    }

    /**
     * Gets the Color from a position on the mob
     *
     * @param pX this should be a block position x
     * @param pZ this should be a block position z
     * @return Returns the biome color
     */
    public static @NotNull Color getColorFromPosition(int pX, int pZ) {
        if (MAP_BIOME_IMAGE != null) {
            // one pixel shouldn't be one block but rather a chunk
            int x = QuartPos.fromBlock(pX);
            int y = QuartPos.fromBlock(pZ);

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

    public float getHeightFromChunkPosition(int pX, int pZ) {
        if (noisedHeightmap != null) {
            int x = QuartPos.fromBlock(pX);
            int y = QuartPos.fromBlock(pZ);

            // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner of the image
            x += noisedHeightmap.getWidth() / 2;
            y += noisedHeightmap.getHeight() / 2;

            // check if coordinate is inbound
            if (isCoordinateInImage(noisedHeightmap, x, y)) {
                return (float) new Color(noisedHeightmap.getRGB(x, y)).getRed() / PIXEL_WEIGHT;
            }
        }

        // fallback
        return 0;
    }

    public float getHeightFromPosition(int pX, int pZ) {
        int x = QuartPos.fromBlock(pX);
        int z = QuartPos.fromBlock(pZ);
        float topLeft = getHeightFromChunkPosition(x, z);
        float topRight = getHeightFromChunkPosition(x + PIXEL_WEIGHT, z);
        float bottomLeft = getHeightFromChunkPosition(x, z + PIXEL_WEIGHT);
        float bottomRight = getHeightFromChunkPosition(x + PIXEL_WEIGHT, z + PIXEL_WEIGHT);
        return getHeightBetween(new float[]{topLeft, topRight, bottomLeft, bottomRight},
                (float) (x % PIXEL_WEIGHT) / PIXEL_WEIGHT, (float) (z % PIXEL_WEIGHT) / PIXEL_WEIGHT);
    }

    private static float getHeightBetween(float[] heights, float xPercent, float zPercent) {
        float h1 = getMiddleHeight(heights[0], heights[1], xPercent);
        float h2 = getMiddleHeight(heights[2], heights[3], xPercent);
        return getMiddleHeight(h1, h2, zPercent);
    }

    private static float getMiddleHeight(float a, float b, float percentage) {
        return (a * (1 - percentage)) + (b * percentage);
    }

    private static boolean isCoordinateInImage(BufferedImage image, int x, int y) {
        return x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight();
    }
}
