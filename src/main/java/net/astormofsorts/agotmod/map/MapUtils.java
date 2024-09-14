package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.util.ColorUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class MapUtils {
    public static BufferedImage removeInvalidColors(BufferedImage map, Iterable<Color> colorList) {
        BufferedImage valid_in = new BufferedImage(map.getWidth(), map.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < valid_in.getWidth(); x++) {
            for (int y = 0; y < valid_in.getHeight(); y++) {
                Color in = new Color(map.getRGB(x, y));
                Color out = ColorUtils.getNearestColor(in, colorList, new Color(-1));
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
}
