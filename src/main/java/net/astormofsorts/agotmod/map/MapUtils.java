package net.astormofsorts.agotmod.map;

import net.astormofsorts.agotmod.util.ColorUtils;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class MapUtils {
    public static BufferedImage approachColors(final BufferedImage inImage, final Collection<Color> colors) {
        BufferedImage output = new BufferedImage(inImage.getWidth(), inImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < output.getWidth(); x++) {
            for (int y = 0; y < output.getHeight(); y++) {
                Color in = new Color(inImage.getRGB(x, y));
                Color out = ColorUtils.getNearestColor(in, colors);
                if (out != null) {
                    output.setRGB(x, y, out.getRGB());
                }
            }
        }

        return output;
    }

    private static final int[][] diagonal = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
    private static final int[][] orthogonal = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public static BufferedImage generateBiomeMap(final BufferedImage original) {
        final BufferedImage greatMap = new BufferedImage(original.getWidth() * 2, original.getHeight() * 2, original.getType());
        // seed is 0 so the map is always the same
        final Random random = new Random(0);

        // set each forth pixel
        for (int x = 0; x < original.getWidth(); x++) {
            for (int y = 0; y < original.getHeight(); y++) {
                greatMap.setRGB(x * 2, y * 2, original.getRGB(x, y));
            }
        }

        // define missing pixels diagonally
        for (int x = 1; x < greatMap.getWidth(); x+=2) {
            for (int y = 1; y < greatMap.getHeight(); y+=2) {
                ArrayList<Integer> nearColors = new ArrayList<>();
                    for (int[] direction : diagonal) {
                        int x1 = x + direction[0];
                        int y1 = y + direction[1];

                        if (isInBound(x1, y1, greatMap.getWidth(), greatMap.getHeight())) {
                            nearColors.add(greatMap.getRGB(x1, y1));
                        }
                    }
                int out = getMostWeightColor(nearColors, random);
                greatMap.setRGB(x, y, out);
            }
        }

        // define missing pixels orthogonally
        for (int x = 0; x < greatMap.getWidth(); x++) {
            // x % 2 is always 1 or 0, so we can define this as an offset
            for (int y = 1 - (x % 2); y < greatMap.getHeight(); y+=2) {
                ArrayList<Integer> nearColors = new ArrayList<>();
                    for (int[] direction : orthogonal) {
                        int x1 = x + direction[0];
                        int y1 = y + direction[1];

                        if (isInBound(x1, y1, greatMap.getWidth(), greatMap.getHeight())) {
                            nearColors.add(greatMap.getRGB(x1, y1));
                        }
                    }

                int out = getMostWeightColor(nearColors, random);
                greatMap.setRGB(x, y, out);
            }
        }
        return greatMap;
    }

    private static boolean isInBound(int x, int y, int width, int height) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    public static int getMostWeightColor(List<Integer> list, Random random) {
        double totalWeight = 0;
        for (int color : list) {
            totalWeight += MapBiome.getByColor(color).biomeWeight();
        }
        double randomWeight = random.nextDouble(totalWeight);
        double currentWeight = 0;
        for (int color : list) {
            currentWeight += MapBiome.getByColor(color).biomeWeight();
            if (currentWeight >= randomWeight) {
                return color;
            }
        }

        return MapBiome.getDefault().color();
    }

    public static BufferedImage acceptOverwriteMap(final BufferedImage originalMap, final @Nullable BufferedImage overwriteMap) {
        BufferedImage combinedMap = new BufferedImage(originalMap.getWidth(), originalMap.getHeight(), originalMap.getType());
        combinedMap.setData(originalMap.getData());

        if (overwriteMap != null) {
            for (int x = 0; x < combinedMap.getWidth(); x++) {
                for (int y = 0; y < combinedMap.getHeight(); y++) {
                    int overwrite = overwriteMap.getRGB(x, y);
                    if ((overwrite >> 24) != 0x00) { // check if the overwrite pixel is transparent
                        combinedMap.setRGB(x, y, overwrite); // accept overwrite pixel if defined
                    }
                }
            }
        }

        return combinedMap;
    }
}
