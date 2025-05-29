package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.gui.GrocerInventoryScreen;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientPacketHandler {

    public static void handleOpenGrocerInventory(OpenGrocerInventoryPacket packet, IPayloadContext context) {
        // Must run on main thread for GUI operations
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.player != null) {
                // Access packet data using record accessor methods
                String grocerName = packet.grocerName();
                java.util.List<net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem.GrocerInventoryEntry> inventoryEntries = packet.entries();


                // Check if there's already a grocer screen open for this grocer
                GrocerInventoryScreen currentScreen = GrocerInventoryScreen.getCurrentInstance();

                if (currentScreen != null && mc.screen == currentScreen) {
                    // Update existing screen with new data
                    GrocerInventoryScreen.updateInventoryData(grocerName, inventoryEntries);
                } else {
                    // Open new screen and populate with data
                    GrocerInventoryScreen newScreen = new GrocerInventoryScreen(grocerName);
                    mc.setScreen(newScreen);

                    // Update with data immediately after opening
                    GrocerInventoryScreen.updateInventoryData(grocerName, inventoryEntries);
                }
            }
        });
    }
}