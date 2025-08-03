package net.darkflameproduction.agotmod.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class TemperatureHelper {
    public static final float COLD_TEMP = 0.0f;
    public static final float HOT_TEMP = 0.85f;

    public static float getTemperature(Level level, BlockPos pos) {
        return level.getBiome(pos).value().getBaseTemperature();
    }

    public static boolean isCold(float temperature) {
        return temperature <= COLD_TEMP;
    }

    public static boolean isHot(float temperature) {
        return temperature >= HOT_TEMP;
    }
}
