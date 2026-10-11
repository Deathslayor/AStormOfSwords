package net.darkflameproduction.agotmod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.client.ModArmorSkinRenderer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Inject(
            method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/player/PlayerRenderer;setModelProperties(Lnet/minecraft/client/player/AbstractClientPlayer;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void hidePlayerSkinLayersForArmor(AbstractClientPlayer player, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        PlayerRenderer renderer = (PlayerRenderer) (Object) this;
        PlayerModel<AbstractClientPlayer> model = renderer.getModel();

        if (ModArmorSkinRenderer.isWearing3dArmor(player, net.minecraft.world.entity.EquipmentSlot.HEAD)) {
            model.hat.visible = false;
        }

        if (ModArmorSkinRenderer.isWearing3dArmor(player, net.minecraft.world.entity.EquipmentSlot.CHEST)) {
            model.jacket.visible = false;
            model.leftSleeve.visible = false;
            model.rightSleeve.visible = false;
        }

        if (ModArmorSkinRenderer.isWearing3dArmor(player, net.minecraft.world.entity.EquipmentSlot.LEGS)) {
            model.leftPants.visible = false;
            model.rightPants.visible = false;
        }

        if (ModArmorSkinRenderer.isWearing3dArmor(player, net.minecraft.world.entity.EquipmentSlot.FEET)) {
            model.leftPants.visible = false;
            model.rightPants.visible = false;
        }
    }
}
