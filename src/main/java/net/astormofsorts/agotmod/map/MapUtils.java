package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.util.ColorUtils;
import net.astormofsorts.agotmod.util.SimplexNoise;
import net.minecraft.util.Mth;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class MapUtils {
    public static BufferedImage removeInvalidColors(BufferedImage map, Map<Color, Integer> heights) {
        // remove not registered colors
        BufferedImage valid_in = new BufferedImage(map.getWidth(), map.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < valid_in.getWidth(); x++) {
            for (int y = 0; y < valid_in.getHeight(); y++) {
                Color in = new Color(map.getRGB(x, y));
                Color out = ColorUtils.getNearestColor(in, heights.keySet(), new Color(-1));
                valid_in.setRGB(x, y, out.getRGB());
            }
        }

        return valid_in;
    }

    public static BufferedImage generateHeightMap(BufferedImage valid_in, Map<Color, Integer> heights) {
        // generate generic heightmap
        BufferedImage gen_heightmap = new BufferedImage(valid_in.getWidth(), valid_in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < gen_heightmap.getWidth(); x++) {
            for (int y = 0; y < gen_heightmap.getHeight(); y++) {
                Color in = new Color(valid_in.getRGB(x, y));
                int height = heights.get(in);
                Color out = height > 0 ? new Color(height, 0, 0) : new Color(0, 0, Math.abs(height));
                gen_heightmap.setRGB(x, y, out.getRGB());
            }
        }

        return gen_heightmap;
    }

    public static BufferedImage addNoise(BufferedImage gen_heightmap, long seed) {
        // add noise
        SimplexNoise noise = new SimplexNoise(seed);
        BufferedImage noise_heightmap = new BufferedImage(gen_heightmap.getWidth(), gen_heightmap.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < noise_heightmap.getWidth(); x++) {
            for (int y = 0; y < noise_heightmap.getHeight(); y++) {
                Color in = new Color(gen_heightmap.getRGB(x, y));
                Color out;
                if (in.getBlue() <= 0) {
                    int r = (int) Mth.clamp(in.getRed() + ColorUtils.getPerlinColor(noise, x, y, 32, 50, 50, 20), 0, 255);
                    out = new Color(r, in.getGreen(), in.getBlue(), in.getAlpha());
                } else {
                    int b = (int) Mth.clamp(in.getBlue() - ColorUtils.getPerlinColor(noise, x, y, 32, 50, 50, 25), 0, 255);
                    out = new Color(in.getRed(), in.getGreen(), b, in.getAlpha());
                }
                noise_heightmap.setRGB(x, y, out.getRGB());
            }
        }

        return noise_heightmap;
    }
}
