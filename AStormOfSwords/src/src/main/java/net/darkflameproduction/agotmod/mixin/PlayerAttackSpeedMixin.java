package net.darkflameproduction.agotmod.mixin;

import net.darkflameproduction.agotmod.item.WeaponRequirements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerAttackSpeedMixin {
    @Inject(method = "getAttackStrengthScale", at = @At("RETURN"), cancellable = true)
    private void modifyAttackSpeedScale(float baseTime, CallbackInfoReturnable<Float> cir) {
        Player player = (Player)(Object)this;
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() instanceof SwordItem) {
            if (!WeaponRequirements.meetsRequirement(player, heldItem)) {
                // Apply the penalty by multiplying the returned value
                cir.setReturnValue(cir.getReturnValue() * 0.2f); // 5x slower
            }
        }
    }
}