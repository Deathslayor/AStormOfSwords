package net.astormofsorts.agotmod.mixin;

import net.astormofsorts.agotmod.datagen.ModDimensionProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldPresets.class)
public class WorldPresetsMixin {
    @Redirect(method = {"createNormalWorldDimensions", "getNormalOverworld"},
            require = 2,
            at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;"))
    private static ResourceKey<WorldPreset> replaceDefault() {
        return ModDimensionProvider.KNOWN_WORLD_PRESET;
    }
}