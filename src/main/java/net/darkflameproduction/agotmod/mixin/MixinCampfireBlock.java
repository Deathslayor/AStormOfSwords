package net.darkflameproduction.agotmod.mixin;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public class MixinCampfireBlock {

    @Inject(method = "isSmokeSource", at = @At("RETURN"), cancellable = true)
    private void onIsSmokeSource(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) return;

        if (state.is(ModBLocks.THATCH_BLOCK.get())
                || state.is(ModBLocks.HEARTH_BLOCK.get())) {
            cir.setReturnValue(true);
        }
    }
}