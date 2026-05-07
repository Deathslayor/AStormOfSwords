package net.darkflameproduction.agotmod.mixin;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldPresets.class)
public class WorldPresetsMixin {

    private static final ResourceKey<WorldPreset> KNOWN_WORLD_PRESET =
            ResourceKey.create(
                    Registries.WORLD_PRESET,
                    AGoTMod.id("known_world")
            );

    @Redirect(
            method = {"createNormalWorldDimensions", "getNormalOverworld"},
            require = 2,
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.GETSTATIC,
                    target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;"
            )
    )
    private static ResourceKey<WorldPreset> replaceDefault() {
        return KNOWN_WORLD_PRESET;
    }
}