package net.astormofsorts.agotmod.entity.client;

import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    // Model layer location for the Bear entity
    public static final ModelLayerLocation BEAR_LAYER = new ModelLayerLocation(
            new ResourceLocation(AGoTMod.MOD_ID, "bear_layer"), "main");

    public static final ModelLayerLocation WIGHT_LAYER = new ModelLayerLocation(
            new ResourceLocation(AGoTMod.MOD_ID, "wight_layer"), "main");

    // Model layer location for the Mammoth entity
    public static final ModelLayerLocation MAMMOTH_LAYER = new ModelLayerLocation(
            new ResourceLocation(AGoTMod.MOD_ID, "mammoth_layer"), "main");
}
