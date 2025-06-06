package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.client.renderer.TownHallDebugRenderer;
import net.darkflameproduction.agotmod.gui.GrocerInventoryScreen;
import net.darkflameproduction.agotmod.entity.custom.npc.system.GrocerSystem;
import net.darkflameproduction.agotmod.gui.TownHallScreen;
import net.darkflameproduction.agotmod.client.tracker.TownTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.Map;

@OnlyIn(Dist.CLIENT)
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

    public static void handleTownHallRegister(TownHallRegisterPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (packet.register()) {
                TownHallDebugRenderer.addTownHall(packet.pos());
                System.out.println("DEBUG: Registered Town Hall at " + packet.pos() + " with debug renderer");
            } else {
                TownHallDebugRenderer.removeTownHall(packet.pos());
                TownTracker.removeTown(packet.pos()); // Also remove from tracker
                System.out.println("DEBUG: Unregistered Town Hall at " + packet.pos() + " from debug renderer and tracker");
            }
        });
    }

    public static void handleOpenTownHallMenu(OpenTownHallMenuPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            TownHallScreen screen = new TownHallScreen(packet.pos());
            mc.setScreen(screen);
            System.out.println("DEBUG: Opened Town Hall GUI for position " + packet.pos());
        });
    }

    public static void handleTownHallData(TownHallDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // Extract packet data
            BlockPos pos = packet.pos();
            int bedCount = packet.bedCount();
            int citizenCount = packet.citizenCount();
            int currentRadius = packet.currentRadius();
            String townName = packet.townName();

            // Update the Town Hall screen if it's open
            TownHallScreen.updateTownHallData(bedCount, citizenCount, currentRadius, townName);

            // Update the debug renderer with radius and visual data
            TownHallDebugRenderer.updateTownHallData(pos, bedCount, citizenCount, currentRadius);

            // Update the town tracker with both name and population data
            // Use citizenCount as population since that's what represents actual residents
            TownTracker.updateTownData(pos, townName, citizenCount);

            System.out.println("DEBUG: Updated Town Hall data for " + pos + ":");
            System.out.println("  - Name: '" + townName + "'");
            System.out.println("  - Beds: " + bedCount);
            System.out.println("  - Citizens: " + citizenCount + " (population)");
            System.out.println("  - Radius: " + currentRadius + " blocks");
        });
    }

    public static void handleOpenGrocerInventory(OpenGrocerInventoryPacket packet, IPayloadContext context) {
        // Must run on main thread for GUI operations
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();

            if (mc.player != null) {
                // Access packet data using record accessor methods
                String grocerName = packet.grocerName();
                java.util.List<GrocerSystem.GrocerInventoryEntry> inventoryEntries = packet.entries();
                long grocerBalance = packet.grocerBalance();
                long playerBalance = packet.playerBalance();

                System.out.println("DEBUG: Opening grocer inventory for: " + grocerName + " with " + inventoryEntries.size() + " items");
                System.out.println("DEBUG: Grocer balance: " + grocerBalance + ", Player balance: " + playerBalance);

                // Update player's persistent data with the balance from server
                mc.player.getPersistentData().putLong("agotmod.coin_balance", playerBalance);

                // Check if there's already a grocer screen open for this grocer
                GrocerInventoryScreen currentScreen = GrocerInventoryScreen.getCurrentInstance();

                if (currentScreen != null && mc.screen == currentScreen) {
                    // Update existing screen with new data
                    GrocerInventoryScreen.updateInventoryData(grocerName, inventoryEntries, grocerBalance);
                    GrocerInventoryScreen.updatePlayerBalance(playerBalance);
                    System.out.println("DEBUG: Updated existing grocer screen with player balance: " + playerBalance);
                } else {
                    // Open new screen and populate with data
                    GrocerInventoryScreen newScreen = new GrocerInventoryScreen(grocerName);
                    mc.setScreen(newScreen);

                    // Update with data immediately after opening
                    GrocerInventoryScreen.updateInventoryData(grocerName, inventoryEntries, grocerBalance);
                    GrocerInventoryScreen.updatePlayerBalance(playerBalance);
                    System.out.println("DEBUG: Opened new grocer screen with player balance: " + playerBalance);
                }
            }
        });
    }
}