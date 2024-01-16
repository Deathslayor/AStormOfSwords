package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.datagen.ModDimensionProvider;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class MapManager {
    private static final BufferedImage mapImage = getMapImage();

    private static BufferedImage getMapImage() {
        try {
            assert ModDimensionProvider.MAP_URL != null;
            return ImageIO.read(ModDimensionProvider.MAP_URL);
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Failed to load map located at: " + ModDimensionProvider.MAP_URL.getPath());
            return null;
        }
    }

    /**
     * Gets the ResourceKey of the Biome from a pixel from the map, specified above.
     * This also uses the @see {@link BiomeColorRegistry} .
     *
     * @param pX this should be a block position x
     * @param pY this should be a block position z
     * @return Returns the ResourceKey of the Biome from a pixel from the map, specified above.
     */
    public static ResourceKey<Biome> getBiomeFromColor(int pX, int pY) {
        // one pixel shouldn't be one block but rather a chunk
        int x = QuartPos.fromBlock(pX);
        int y = QuartPos.fromBlock(pY);

        // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner
        x += mapImage.getWidth() / 2;
        y -= mapImage.getHeight() / 2;

        // return ocean in case the specified position wasn't found on the map
        if (x < 0 || x > mapImage.getWidth() || y < 0 || y > mapImage.getHeight())
            return ModDimensionProvider.DEFAULT_BIOME;

        // get biome via color
        try {
            return BiomeColorRegistry.getBiomeByColor(new Color(mapImage.getRGB(x, y)));
        }
        catch (Exception ignored) {
            // return null if something went wrong obtaining the biome from the map
            return null;
        }
    }
}
