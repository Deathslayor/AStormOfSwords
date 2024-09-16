package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.util.ColorUtils;
import net.astormofsorts.agotmod.util.ImageScaler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.*;

public class MapUtils {
    public static BufferedImage approachColors(BufferedImage inImage, Collection<Color> colors) {
        BufferedImage output = new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < output.getWidth(); x++) {
            for (int y = 0; y < output.getHeight(); y++) {
                Color in = new Color(inImage.getRGB(x, y));
                Color out = ColorUtils.getNearestColor(in, colors, null);
                if (out != null) {
                    output.setRGB(x, y, out.getRGB());
                }
            }
        }

        return output;
    }

    public static BufferedImage removeInvalidColors(BufferedImage map, Collection<Integer> colorList) {
        BufferedImage filtered = new BufferedImage(map.getWidth(), map.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < filtered.getWidth(); x++) {
            for (int y = 0; y < filtered.getHeight(); y++) {
                int in = map.getRGB(x, y);
                if (colorList.contains(in)) {
                    filtered.setRGB(x, y, in);
                }
            }
        }

        BufferedImage valid_only = new BufferedImage(filtered.getWidth(), filtered.getHeight(), BufferedImage.TYPE_INT_ARGB);
        valid_only.setData(filtered.getData());

        // use random with seed 0 so the image is always the same
        Random random = new Random(0);
        for (int x = 0; x < valid_only.getWidth(); x++) {
            for (int y = 0; y < valid_only.getHeight(); y++) {
                fillColor(valid_only, x, y, MapBiome.getDefault().color(), random);
            }
        }

        return valid_only;
    }

    private static final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private static int fillColor(BufferedImage image, int x, int y, int fallback, Random random) {
        int rgb = image.getRGB(x, y);

        if ((rgb >> 24) == 0x00) {
            int[] dirs = directions[random.nextInt(directions.length - 1)];
            int x2 = x + dirs[0];
            int y2 = y + dirs[1];

            // check if coordinat are inbound
            if (x2 < 0 || y2 < 0 || x2 >= image.getWidth() || y2 >= image.getHeight()) {
                image.setRGB(x, y, fallback);
                return fallback;
            } else {
                int out = fillColor(image, x2, y2, fallback, random);
                image.setRGB(x, y, out);
                return out;
            }
        } else {
            return rgb;
        }
    }

    public static BufferedImage blur(BufferedImage image, int times) {
        BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        out.setData(image.getData());

        for (int i = 0; i < times; i++) {
            out = blur(out);
        }

        return out;
    }

    public static BufferedImage blur(BufferedImage image) {

        // Define a Gaussian blur kernel
        float[] kernel = {
                1f/16f, 1f/8f, 1f/16f,
                1f/8f, 1f/4f, 1f/8f,
                1f/16f, 1f/8f, 1f/16f
        };

        // Create the ConvolveOp object with the kernel
        Kernel blurKernel = new Kernel(3, 3, kernel);
        ConvolveOp blurOp = new ConvolveOp(blurKernel, ConvolveOp.EDGE_NO_OP, null);

        // Apply the blur
       return blurOp.filter(image, null);
    }

    public static BufferedImage scaleImage(BufferedImage originalImage, int factor) {
        return ImageScaler.scaleImage(originalImage, factor);
    }

    public static BufferedImage generateHeightMap(BufferedImage valid_in, Map<Integer, Integer> heights) {
        // generate generic heightmap
        BufferedImage gen_heightmap = new BufferedImage(valid_in.getWidth(), valid_in.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < gen_heightmap.getWidth(); x++) {
            for (int y = 0; y < gen_heightmap.getHeight(); y++) {
                int in =valid_in.getRGB(x, y);
                int height = heights.get(in);
                Color out = height > 0 ? new Color(height, 0, 0) : new Color(0, 0, Math.abs(height));
                gen_heightmap.setRGB(x, y, out.getRGB());
            }
        }

        return gen_heightmap;
    }
}
