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

        // Only process if no other screen is open and the player exists
        if (minecraft.screen == null && minecraft.player != null) {
            // Log the current key state for debugging
            AGoTMod.LOGGER.debug("OpenCustomGUI isDown: {}", KeyBindings.INSTANCE.OpenCustomGUI.isDown());

            // Method 1: Using isDown with press detection
            if (KeyBindings.INSTANCE.OpenCustomGUI.isDown()) {
                if (!keyWasDown) {
                    AGoTMod.LOGGER.info("M key pressed - opening GUI");
                    minecraft.setScreen(new CustomGuiScreen());
                }
                keyWasDown = true;
            } else {
                keyWasDown = false;
            }

            // Method 2: Using consumeClick (alternative approach)
            /*
            if (KeyBindings.INSTANCE.OpenCustomGUI.consumeClick()) {
                AGoTMod.LOGGER.info("M key clicked - opening GUI");
                minecraft.setScreen(new CustomGuiScreen());
            }
            */
        }
    }
}