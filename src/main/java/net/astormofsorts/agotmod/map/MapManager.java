package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.datagen.ModDimensionProvider;
import net.minecraft.core.QuartPos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MapManager {
    private static final BufferedImage mapBiomeImage = getMapBiomeImage();
    private static final BufferedImage mapHeightImage = getHeightImage();

    @Nullable
    private static BufferedImage getMapBiomeImage() {
        try {
            if (ModDimensionProvider.BIOME_MAP_URL != null) {
                return ImageIO.read(ModDimensionProvider.BIOME_MAP_URL);
            }
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Failed to load biome map located at: " + ModDimensionProvider.BIOME_MAP_URL.getPath());
        }
        return null;
    }

    @Nullable
    private static BufferedImage getHeightImage() {
        try {
            if (ModDimensionProvider.HEIGHT_MAP_URL != null) {
                return ImageIO.read(ModDimensionProvider.HEIGHT_MAP_URL);
            }
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Failed to load height map located at: " + ModDimensionProvider.HEIGHT_MAP_URL.getPath());
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
        if (mapBiomeImage != null) {
            // one pixel shouldn't be one block but rather a chunk
            int x = QuartPos.fromBlock(pX);
            int y = QuartPos.fromBlock(pZ);

            // TODO: Define custom spawn position
            // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner of the image
            x += mapBiomeImage.getWidth() / 2;
            y += mapBiomeImage.getHeight() / 2;

            // check if coordinate is inbound
            if (isCoordinateInImage(mapBiomeImage, x, y)) {
                return new Color(mapBiomeImage.getRGB(x, y));
            }
        }

        // fallback
        return ModDimensionProvider.DEFAULT_BIOME_COLOR;
    }

    public static float getHeightFromChunkPosition(int pX, int pZ) {
        if (mapHeightImage != null) {
            int x = QuartPos.fromBlock(pX);
            int y = QuartPos.fromBlock(pZ);

            // TODO: Define custom spawn position
            // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner of the image
            x += mapHeightImage.getWidth() / 2;
            y += mapHeightImage.getHeight() / 2;

            // check if coordinate is inbound
            if (isCoordinateInImage(mapHeightImage, x, y)) {
                return new Color(mapHeightImage.getRGB(x, y)).getRed() / 5f;
            }
        }

        // fallback
        return 0;
    }

    public static float getHeightFromPosition(int pX, int pZ) {
        int x = QuartPos.fromBlock(pX);
        int z = QuartPos.fromBlock(pZ);
        float topLeft = getHeightFromChunkPosition(x, z);
        float topRight = getHeightFromChunkPosition(x + 5, z);
        float bottomLeft = getHeightFromChunkPosition(x, z + 5);
        float bottomRight = getHeightFromChunkPosition(x + 5, z + 5);
        return getHeightBetween(new float[]{topLeft, topRight, bottomLeft, bottomRight},
                (float) (x % 5) / 5, (float) (z % 5) / 5);
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
