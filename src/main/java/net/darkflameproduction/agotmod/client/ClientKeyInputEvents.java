package net.darkflameproduction.agotmod.client;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.event.KeyMappings.KeyBindings;
import net.darkflameproduction.agotmod.gui.CustomGuiScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(modid = AGoTMod.MOD_ID, value = Dist.CLIENT)
public class ClientKeyInputEvents {
    private static boolean keyWasDown = false;

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.screen == null && minecraft.player != null) {
            if (KeyBindings.INSTANCE.OpenCustomGUI.isDown()) {
                if (!keyWasDown) {
                    minecraft.setScreen(new CustomGuiScreen(minecraft));
                }
                keyWasDown = true;
            } else {
                keyWasDown = false;
            }
        }
    }
}