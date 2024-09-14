package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.util.ImageScaler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

public class MapUtils {
    public static BufferedImage removeInvalidColors(BufferedImage map, Collection<Color> colorList) {
        BufferedImage alphalised = new BufferedImage(map.getWidth(), map.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < alphalised.getWidth(); x++) {
            for (int y = 0; y < alphalised.getHeight(); y++) {
                Color in = new Color(map.getRGB(x, y));
                if (colorList.contains(in)) {
                    alphalised.setRGB(x, y, in.getRGB());
                }
            }
        }

        BufferedImage valid_only = new BufferedImage(alphalised.getWidth(), alphalised.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < alphalised.getWidth(); x++) {
            for (int y = 0; y < alphalised.getHeight(); y++) {
                int originalColor = alphalised.getRGB(x, y);
                Color originalPixel = new Color(originalColor, true);

                // If the pixel already has a non-transparent color, copy it to the output directly
                if (originalPixel.getAlpha() != 0) {
                    valid_only.setRGB(x, y, originalColor);
                    continue;
                }

                // Stream to find the nearest non-transparent neighbor pixel
                Stream<SnakePixelStream.Pixel> pixelStream = SnakePixelStream.getSnakePixelStream(alphalised.getWidth(), alphalised.getHeight(), x, y);
                final int finalX = x;
                final int finalY = y;

                // This flag will stop the search once a valid pixel is found
                boolean foundValidPixel = pixelStream
                        .filter(pixel -> {
                            // Avoid checking the center pixel itself
                            return !(pixel.x() == finalX && pixel.y() == finalY);
                        })
                        .limit(511) // Limit to checking the nearest 511 neighbors
                        .anyMatch(pixel -> {
                            int neighborColor = alphalised.getRGB(pixel.x(), pixel.y());
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

    public static BufferedImage upScale(BufferedImage valid_in, int size) {
        return ImageScaler.scaleImage(valid_in, new Dimension(valid_in.getWidth() * size, valid_in.getHeight() * size));
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

    public static BufferedImage blur(BufferedImage inImage, int times) {
        BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        outImage.setData(inImage.getData());
        for (int i = 1; i <= times; i++) {
            outImage = blur(outImage);
        }
        return outImage;
    }

    public static BufferedImage blur(BufferedImage inImage) {
        BufferedImage outImage = new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < inImage.getWidth(); x++) {
            for (int y = 0; y < inImage.getHeight(); y++) {
                Color in = new Color(inImage.getRGB(x, y));
                Color right = x + 1 < inImage.getWidth() ? new Color(inImage.getRGB(x + 1, y)) : in;
                Color bottom = y + 1 < inImage.getHeight() ? new Color(inImage.getRGB(x, y + 1)) : in;
                Color botright = x + 1 < inImage.getWidth() && y + 1 < inImage.getHeight() ? new Color(inImage.getRGB(x +1, y + 1)) : in;
                int a = (in.getAlpha() + right.getAlpha() + bottom.getAlpha() + botright.getAlpha()) / 4;
                int r = (in.getRed() + right.getRed() + bottom.getRed() + botright.getRed()) / 4;
                int g = (in.getGreen() + right.getGreen() + bottom.getGreen() + botright.getGreen()) / 4;
                int b = (in.getBlue() + right.getBlue() + bottom.getBlue() + botright.getBlue()) / 4;

                Color out = new Color(r, g, b, a);
                outImage.setRGB(x, y, out.getRGB());
            }
        }
        return outImage;
    }
}
