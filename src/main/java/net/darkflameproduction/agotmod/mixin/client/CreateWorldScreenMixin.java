package net.darkflameproduction.agotmod.mixin.client;

import net.darkflameproduction.agotmod.datagen.ModDimensionProvider;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@OnlyIn(Dist.CLIENT)
@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
    @Redirect(method = "openFresh",
            at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = "Lnet/minecraft/world/level/levelgen/presets/WorldPresets;NORMAL:Lnet/minecraft/resources/ResourceKey;"))
    private static ResourceKey<WorldPreset> replaceDefault() {
        return ModDimensionProvider.KNOWN_WORLD_PRESET;
    }
}