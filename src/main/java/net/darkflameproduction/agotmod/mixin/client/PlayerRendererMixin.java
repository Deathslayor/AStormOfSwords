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

        if (ModArmorSkinRenderer.HELMETS.values().stream().anyMatch(supplier -> supplier.get().isWearingHelmet(player))) {
            model.hat.visible = false;
        }

        if (ModArmorSkinRenderer.CHESTPLATES.values().stream().anyMatch(supplier -> supplier.get().isWearingChestplate(player))) {
            model.jacket.visible = false;
            model.leftSleeve.visible = false;
            model.rightSleeve.visible = false;
        }

        if (ModArmorSkinRenderer.LEGGINGS.values().stream().anyMatch(supplier -> supplier.get().isWearingLeggings(player))) {
            model.leftPants.visible = false;
            model.rightPants.visible = false;
        }

        if (ModArmorSkinRenderer.BOOTS.values().stream().anyMatch(supplier -> supplier.get().isWearingBoots(player))) {
            model.leftPants.visible = false;
            model.rightPants.visible = false;
        }
    }
}
