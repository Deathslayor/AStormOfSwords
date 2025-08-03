package net.darkflameproduction.agotmod.gui;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.loading.FMLEnvironment;

public class NeoForgeHelper {
    public static boolean isClientSide() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }
}