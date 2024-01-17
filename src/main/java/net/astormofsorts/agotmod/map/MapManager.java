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

import static net.astormofsorts.agotmod.map.BiomeColorRegistry.COLOR_NOT_REGISTERED_EXCPETION;


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
     * @param pZ this should be a block position z
     * @return Returns the ResourceKey of the Biome from a pixel from the map, specified above.
     */
    public static ResourceKey<Biome> getBiomeFromColor(int pX, int pZ) {
        assert mapImage != null;

        // one pixel shouldn't be one block but rather a chunk
        int x = QuartPos.fromBlock(pX);
        int y = QuartPos.fromBlock(pZ);

        // 0 | 0 should be in the middle of the image, comment this lines to move it to the top-left corner of the image
        x += mapImage.getWidth() / 2;
        y += mapImage.getHeight() / 2;

        // get biome via color
        try {
            ResourceKey<Biome> biome = BiomeColorRegistry.getBiomeByColor(new Color(mapImage.getRGB(x, y)));
            if (biome != null)
                return biome;
            else
                throw COLOR_NOT_REGISTERED_EXCPETION;
        } catch (Exception e) {
            if (e == COLOR_NOT_REGISTERED_EXCPETION)
                AGoTMod.LOGGER.error(e.getMessage(), "X: " + x + " Y: " + y, ModDimensionProvider.DEFAULT_BIOME.location());
        }

        // return a default biome in case the specified position wasn't found on the map or something went wrong in general
        return ModDimensionProvider.DEFAULT_BIOME;
    }
}
