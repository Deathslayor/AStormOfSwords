package net.darkflameproduction.agotmod.network;

import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.darkflameproduction.agotmod.gui.GrocerInventoryScreen;

public class ClientPacketHandler {

    public static void handleOpenGrocerInventory(OpenGrocerInventoryPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                GrocerInventoryScreen screen = new GrocerInventoryScreen(packet.entries(), packet.grocerName());
                minecraft.setScreen(screen);
            }
        });
    }
}