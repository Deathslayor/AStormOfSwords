package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.client.overlay.TownNotificationOverlay;
import net.darkflameproduction.agotmod.client.renderer.TownHallDebugRenderer;
import net.darkflameproduction.agotmod.client.town.ClientTownAreaManager;
import net.darkflameproduction.agotmod.gui.CustomGuiScreen;
import net.darkflameproduction.agotmod.gui.GrocerInventoryScreen;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerSystem;
import net.darkflameproduction.agotmod.gui.HouseData;
import net.darkflameproduction.agotmod.gui.TownHallScreen;
import net.darkflameproduction.agotmod.client.tracker.TownTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;

@OnlyIn(Dist.CLIENT)
public class ClientPacketHandler {

    public static void handleCoinBalance(CoinBalancePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                minecraft.player.getPersistentData().putLong("agotmod.coin_balance", packet.balance());
                GrocerInventoryScreen.updatePlayerBalance(packet.balance());
            }
        });
    }

    public static void handleTownHallRegister(TownHallRegisterPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (packet.register()) {
                TownHallDebugRenderer.addTownHall(packet.pos());
            } else {
                TownHallDebugRenderer.removeTownHall(packet.pos());
                TownTracker.removeTown(packet.pos());
                ClientTownAreaManager.removeTownArea(packet.pos());
            }
        });
    }

    public static void handleOpenTownHallMenu(OpenTownHallMenuPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            mc.setScreen(new TownHallScreen(packet.pos()));
        });
    }

    public static void handleHouseNameValidation(HouseNameValidationPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            CustomGuiScreen.handleHouseNameValidation(packet.isAvailable(), packet.message());
        });
    }

    public static void handleTownHallData(TownHallDataPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            BlockPos pos = packet.pos();

            TownHallScreen.updateTownHallData(
                    pos,
                    packet.bedCount(),
                    packet.citizenCount(),
                    packet.currentRadius(),
                    packet.townName(),
                    packet.isClaimed(),
                    packet.claimedByHouse(),
                    packet.availableJobCount(),
                    packet.assignedJobCount(),
                    packet.totalJobCount(),
                    packet.joblessCount(),
                    packet.culture()
            );

            TownHallScreen.updateFinances(pos, packet.townBalance(), packet.townIncome());
            TownHallScreen.updateInventory(pos, packet.townInventory());

            TownHallDebugRenderer.updateTownHallData(pos, packet.bedCount(),
                    packet.citizenCount(), packet.currentRadius());
            TownTracker.updateTownData(pos, packet.townName(), packet.citizenCount());
            ClientTownAreaManager.updateTownArea(pos, packet.townName(), packet.isClaimed(),
                    packet.claimedByHouse(), packet.citizenCount(), packet.currentRadius());
        });
    }

    public static void handleSyncOwnedTowns(SyncOwnedTownsPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            CustomGuiScreen.setOwnedTowns(packet.towns());
        });
    }

    public static void handleOpenGrocerInventory(OpenGrocerInventoryPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                String grocerName    = packet.grocerName();
                java.util.List<GrocerSystem.GrocerInventoryEntry> grocerEntries = packet.grocerEntries();
                java.util.List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries = packet.playerEntries();
                long grocerBalance   = packet.grocerBalance();
                long playerBalance   = packet.playerBalance();
                float buyMultiplier  = packet.buyMultiplier();
                float sellMultiplier = packet.sellMultiplier();

                mc.player.getPersistentData().putLong("agotmod.coin_balance", playerBalance);

                GrocerInventoryScreen currentScreen = GrocerInventoryScreen.getCurrentInstance();

                if (currentScreen != null && mc.screen == currentScreen) {
                    GrocerInventoryScreen.updateInventoryData(grocerName, grocerEntries, playerEntries,
                            grocerBalance, buyMultiplier, sellMultiplier);
                    GrocerInventoryScreen.updatePlayerBalance(playerBalance);
                } else {
                    GrocerInventoryScreen newScreen = new GrocerInventoryScreen(grocerName);
                    mc.setScreen(newScreen);
                    GrocerInventoryScreen.updateInventoryData(grocerName, grocerEntries, playerEntries,
                            grocerBalance, buyMultiplier, sellMultiplier);
                    GrocerInventoryScreen.updatePlayerBalance(playerBalance);
                }
            }
        });
    }

    public static void handleSyncHouseName(SyncHouseNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            CustomGuiScreen.setSyncedHouseName(packet.houseName());
        });
    }

    public static void handleSyncHouseBanner(SyncHouseBannerPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            CustomGuiScreen.setSyncedHouseBanner(packet.bannerData());
            TownNotificationOverlay.setSyncedBanner(packet.bannerData());
        });
    }

    public static void handleOpenNpcConversation(OpenNpcConversationPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            mc.setScreen(new net.darkflameproduction.agotmod.client.gui.NpcConversationScreen(
                    packet.npcUUID(), packet.jobType(), packet.npcName()));
        });
    }

    public static void handleSyncHouseInvite(SyncHouseInvitePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            HouseData.setPendingInvite(packet.houseName(), packet.inviterName());
        });
    }

    public static void handleSyncHouseMembers(SyncHouseMembersPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> HouseData.setHouseMembers(packet.members()));
    }

    public static void handleSyncHouseRole(SyncHouseRolePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> HouseData.setIsFounder(packet.isFounder()));
    }
}
