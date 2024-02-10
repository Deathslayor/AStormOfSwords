package net.astormofsorts.agotmod.worldgen.ore;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {

    // Method for configuring ore placement with specified modifiers
    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        // Return a list of placement modifiers, including the provided ones and some default modifiers
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    // Method for configuring common ore placement with count and height range modifiers
    public static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        // Use the orePlacement method with CountPlacement and provided height range modifier
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    // Method for configuring rare ore placement with chance and height range modifiers
    public static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        // Use the orePlacement method with RarityFilter and provided height range modifier
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
