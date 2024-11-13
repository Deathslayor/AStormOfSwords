package net.darkflameproduction.agotmod.worldgen.tree;

import net.darkflameproduction.agotmod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower SYSCAMORE = new TreeGrower("syscamore", Optional.empty(), Optional.of(ModConfiguredFeatures.SYCAMORE_KEY), Optional.empty());
}
