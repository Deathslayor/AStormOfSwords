package net.astormofsorts.agotmod.map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

public class MapUtils {
    public static BufferedImage removeInvalidColors(BufferedImage map, Collection<Color> colorList) {
        BufferedImage filtered = new BufferedImage(map.getWidth(), map.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < filtered.getWidth(); x++) {
            for (int y = 0; y < filtered.getHeight(); y++) {
                Color in = new Color(map.getRGB(x, y));
                if (colorList.contains(in)) {
                    filtered.setRGB(x, y, in.getRGB());
                }
            }
        }

        BufferedImage valid_only = new BufferedImage(filtered.getWidth(), filtered.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < filtered.getWidth(); x++) {
            for (int y = 0; y < filtered.getHeight(); y++) {
                int originalColor = filtered.getRGB(x, y);
                Color originalPixel = new Color(originalColor, true);

                // If the pixel already has a non-transparent color, copy it to the output directly
                if (originalPixel.getAlpha() != 0) {
                    valid_only.setRGB(x, y, originalColor);
                    continue;
                }

                // Stream to find the nearest non-transparent neighbor pixel
                Stream<SnakePixelStream.Pixel> pixelStream = new SnakePixelStream(filtered.getWidth(), filtered.getHeight(), x, y).stream();
                final int finalX = x;
                final int finalY = y;

                // This flag will stop the search once a valid pixel is found
                boolean foundValidPixel = pixelStream
                        .filter(pixel -> {
                            // Avoid checking the center pixel itself
                            return !(pixel.x() == finalX && pixel.y() == finalY);
                        })
                        .anyMatch(pixel -> {
                            int neighborColor = filtered.getRGB(pixel.x(), pixel.y());
                            Color neighborPixel = new Color(neighborColor, true);
                            if (neighborPixel.getAlpha() != 0) {
                                valid_only.setRGB(finalX, finalY, neighborColor); // Set the first valid color found
                                return true; // Stop searching
                            }
                            return false;
                        });

                // If no valid pixel was found within the neighborhood, throw an error
                if (!foundValidPixel) {
                    throw new RuntimeException("No valid color could be found for pixel at: x: " + x + " y: " + y);
                }
            }
        }

        return valid_only;
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

    public static BufferedImage scaleImage(BufferedImage originalImage, int scaleFactor) {
        int width = originalImage.getWidth() * scaleFactor;
        int height = originalImage.getHeight() * scaleFactor;

        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int origX = x / scaleFactor;
                int origY = y / scaleFactor;

                int rgb = originalImage.getRGB(origX, origY);
                scaledImage.setRGB(x, y, rgb);
            }
        }

        return scaledImage;
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
