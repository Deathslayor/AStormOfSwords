package net.astormofsorts.agotmod.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorUtils {
    public static int compareColors(Color c1, Color c2) {
        int a = Math.abs(c1.getAlpha() - c2.getAlpha());
        int r = Math.abs(c1.getRed() - c2.getRed());
        int g = Math.abs(c1.getGreen() - c2.getGreen());
        int b = Math.abs(c1.getBlue() - c2.getBlue());
        return a + r + g + b;
    }

    public static Color getNearestColor(Color color, Iterable<Color> colorList, Color fallback) {
        Map<Integer, Color> differences = new HashMap<>();
        for (Color color1 : colorList) {
            int i = compareColors(color, color1);
            differences.put(i, color1);
        }

        int lowestDifference = 765;
        for (Integer i : differences.keySet()) {
            if (i < lowestDifference) {
                lowestDifference = i;
            }
        }
        return differences.getOrDefault(lowestDifference, fallback);
    }

    public static double getPerlinColor(SimplexNoise noise, int x, int z, int scale, int xStrech, int yStretch, int range) {
        double perlin = noise.noise2((double) x / xStrech,(double) z / yStretch);
        double d = 1;
        for (int i = 2; i <= scale; i *= 2) {
            perlin += (1d / i) * noise.noise2((double) x * i / xStrech,(double) z * i / yStretch);
            d += (1d / i);
        }

        perlin = perlin / d;
        perlin *= range;

        return perlin;
    }
}