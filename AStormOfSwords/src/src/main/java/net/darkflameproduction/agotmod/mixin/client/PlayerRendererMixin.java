package net.darkflameproduction.agotmod.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.darkflameproduction.agotmod.client.ModArmorSkinRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
    @Inject(method = "setupRotations(Lnet/minecraft/client/renderer/entity/state/PlayerRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;FF)V", at = @At("RETURN"))
    private void onSetupRotations(PlayerRenderState state, PoseStack poseStack,
                                  float ageInTicks, float rotationYaw, CallbackInfo ci) {
        if (Minecraft.getInstance().player != null) {
            boolean wearingHelmet = ModArmorSkinRenderer.HELMETS.values().stream()
                    .anyMatch(supplier -> supplier.get().isWearingHelmet(Minecraft.getInstance().player));

            if (wearingHelmet) {
                PlayerRenderer renderer = (PlayerRenderer) (Object) this;
                PlayerModel model = renderer.getModel();
                state.showHat = false;
            }
        }


            if (Minecraft.getInstance().player != null) {
                boolean wearingChestplate = ModArmorSkinRenderer.CHESTPLATES.values().stream()
                        .anyMatch(supplier -> supplier.get().isWearingChestplate(Minecraft.getInstance().player));
                if (wearingChestplate) {
                    PlayerRenderer renderer = (PlayerRenderer) (Object) this;
                    PlayerModel model = renderer.getModel();
                    state.showJacket = false;
                    state.showLeftSleeve = false;
                    state.showRightSleeve = false;
                }
            }

                if (Minecraft.getInstance().player != null) {
                    boolean wearingLeggings = ModArmorSkinRenderer.LEGGINGS.values().stream()
                            .anyMatch(supplier -> supplier.get().isWearingLeggings(Minecraft.getInstance().player));

                    if (wearingLeggings) {
                        PlayerRenderer renderer = (PlayerRenderer) (Object) this;
                        PlayerModel model = renderer.getModel();
                        state.showLeftPants = false;
                        state.showRightPants = false;
                    }
            }

        if (Minecraft.getInstance().player != null) {
            boolean wearingBoots = ModArmorSkinRenderer.BOOTS.values().stream()
                    .anyMatch(supplier -> supplier.get().isWearingBoots(Minecraft.getInstance().player));

            if (wearingBoots) {
                PlayerRenderer renderer = (PlayerRenderer) (Object) this;
                PlayerModel model = renderer.getModel();
                state.showLeftPants = false;
                state.showRightPants = false;
            }
        }
        }
    }
