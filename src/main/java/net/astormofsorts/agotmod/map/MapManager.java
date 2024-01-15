package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;


public class MapManager {
    private static final BufferedImage mapImage = getMapImage();

    private static BufferedImage getMapImage() {
        URL mapURL = AGoTMod.class.getResource("/assets/agotmod/map.png");
        try {
            assert mapURL != null;
            return ImageIO.read(mapURL);
        } catch (IOException e) {
            AGoTMod.LOGGER.error("Failed to load map located at: " + mapURL.getPath());
            return null;
        }
    }

    public static Holder<Biome> getBiomeFromColor(int x, int y) {
        // 0 | 0 should be in the mddle of the image
        y -= mapImage.getHeight() / 2;
        x += mapImage.getWidth() / 2;

        // get biome via color
        try {
            //assert mapImage != null;

            ResourceKey<Biome> biome = BiomeColorRegistry.getBiomeByColor(new Color(mapImage.getRGB(x, y)));
            if (biome != null)
                return AGoTMod.VANILLA_BIOMES.getHolderOrThrow(biome);
        }
        // ignore exception since in case of failure, an ocean biome is returned
        catch (Exception ignored) {
        }

        // return ocean biome if something went wrong obtaining the biome from the map
        return AGoTMod.VANILLA_BIOMES.getHolderOrThrow(Biomes.OCEAN);
    }
}
