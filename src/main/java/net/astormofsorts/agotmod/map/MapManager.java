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
    private static final BufferedImage mapImage = getMapImage();

    @Nullable
    private static BufferedImage getMapImage() {
        try {
            if (ModDimensionProvider.MAP_URL != null) {
                return ImageIO.read(ModDimensionProvider.MAP_URL);
            }
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Failed to load map located at: " + ModDimensionProvider.MAP_URL.getPath());
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
        if (mapImage != null) {
            // one pixel shouldn't be one block but rather a chunk
            int x = QuartPos.fromBlock(pX);
            int y = QuartPos.fromBlock(pZ);

            // TODO: Define custom spawn position
            // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner of the image
            x += mapImage.getWidth() / 2;
            y += mapImage.getHeight() / 2;

            // check if coordinate is inbound
            try {
                return new Color(mapImage.getRGB(x, y));
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }

        // fallback
        return ModDimensionProvider.DEFAULT_BIOME_COLOR;
    }
}
