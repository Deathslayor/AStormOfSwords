package net.darkflameproduction.agotmod.mixin;

import net.darkflameproduction.agotmod.datagen.ModDimensionProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServerProperties;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DedicatedServerProperties.class)
public class ServerPropertiesMixin {
    @Redirect(method = "<init>",
            at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;"))
    private ResourceKey<WorldPreset> replaceDefault() {
        return ModDimensionProvider.KNOWN_WORLD_PRESET;
    }

    // Also override the fallback when invalid for consistency
    @SuppressWarnings("unused")
    @Mixin(targets = "net.minecraft.server.dedicated.DedicatedServerProperties$WorldDimensionData")
    private static class WorldGenPropertiesMixin {
        @Redirect(method = "create",
                at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;"))
        private ResourceKey<WorldPreset> replaceDefault() {
            return ModDimensionProvider.KNOWN_WORLD_PRESET;
        }
    }
}