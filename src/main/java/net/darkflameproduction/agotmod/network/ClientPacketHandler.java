package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.gui.GrocerInventoryScreen;
import net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Map;

@OnlyIn(Dist.CLIENT) // ADD THIS ANNOTATION - This is the key fix!
public class ClientPacketHandler {

    public static void handleCoinBalance(CoinBalancePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                // Update player's persistent data
                minecraft.player.getPersistentData().putLong("agotmod.coin_balance", packet.balance());

                // Always update the grocer GUI if it's open, and log the update
                GrocerInventoryScreen.updatePlayerBalance(packet.balance());

                System.out.println("DEBUG: ClientPacketHandler updated coin balance to: " + packet.balance());
            }
        });
    }

    public static void handleOpenGrocerInventory(OpenGrocerInventoryPacket packet, IPayloadContext context) {
        // Must run on main thread for GUI operations
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.player != null) {
                // Access packet data using record accessor methods
                String grocerName = packet.grocerName();
                java.util.List<net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem.GrocerInventoryEntry> inventoryEntries = packet.entries();
                long grocerBalance = packet.grocerBalance();
                long playerBalance = packet.playerBalance(); // NEW: Get player balance from packet

                System.out.println("DEBUG: Opening grocer inventory for: " + grocerName + " with " + inventoryEntries.size() + " items");
                System.out.println("DEBUG: Grocer balance: " + grocerBalance + ", Player balance: " + playerBalance);

                // NEW: Update player's persistent data with the balance from server
                mc.player.getPersistentData().putLong("agotmod.coin_balance", playerBalance);

                // Check if there's already a grocer screen open for this grocer
                GrocerInventoryScreen currentScreen = GrocerInventoryScreen.getCurrentInstance();

                if (currentScreen != null && mc.screen == currentScreen) {
                    // Update existing screen with new data
                    GrocerInventoryScreen.updateInventoryData(grocerName, inventoryEntries, grocerBalance);
                    // NEW: Update player balance from packet
                    GrocerInventoryScreen.updatePlayerBalance(playerBalance);
                    System.out.println("DEBUG: Updated existing grocer screen with player balance: " + playerBalance);
                } else {
                    // Open new screen and populate with data
                    GrocerInventoryScreen newScreen = new GrocerInventoryScreen(grocerName);
                    mc.setScreen(newScreen);

                    // Update with data immediately after opening
                    GrocerInventoryScreen.updateInventoryData(grocerName, inventoryEntries, grocerBalance);
                    // NEW: Update player balance from packet
                    GrocerInventoryScreen.updatePlayerBalance(playerBalance);
                    System.out.println("DEBUG: Opened new grocer screen with player balance: " + playerBalance);
                }
            }
        });
    }
}