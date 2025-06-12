package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;

@EventBusSubscriber(modid = AGoTMod.MOD_ID)
public class PlayerLoginHandler {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            AGoTMod.LOGGER.info("Player {} logged in, sending initialization packets", serverPlayer.getName().getString());

            // Send current coin balance
            long balance = serverPlayer.getPersistentData().getLong("agotmod.coin_balance");
            PacketDistributor.sendToPlayer(serverPlayer, new CoinBalancePacket(balance));
            AGoTMod.LOGGER.info("Sent initial balance {} to player {}", balance, serverPlayer.getName().getString());

            // Send house name
            sendHouseName(serverPlayer);

            // Send house banner
            sendHouseBanner(serverPlayer);

            // Send empty owned towns list (will be populated by other systems as needed)
            PacketDistributor.sendToPlayer(serverPlayer, new SyncOwnedTownsPacket(new ArrayList<>()));
        }
    }

    /**
     * Send house name to player
     */
    private static void sendHouseName(ServerPlayer serverPlayer) {
        CompoundTag playerData = serverPlayer.getPersistentData();
        String houseName = "";

        if (playerData.contains("agotmod.house")) {
            CompoundTag houseTag = playerData.getCompound("agotmod.house");
            if (houseTag.contains("house_name")) {
                houseName = houseTag.getString("house_name");
            }
        }

        PacketDistributor.sendToPlayer(serverPlayer, new SyncHouseNamePacket(houseName));
    }

    /**
     * Send house banner to player
     */
    private static void sendHouseBanner(ServerPlayer serverPlayer) {
        CompoundTag playerData = serverPlayer.getPersistentData();
        CompoundTag bannerData = null;

        // Load banner data from UUID-based storage
        String playerUUID = serverPlayer.getUUID().toString();
        String bannerKey = AGoTMod.MOD_ID + ".player_banner_" + playerUUID;

        if (playerData.contains(bannerKey)) {
            CompoundTag bannerStorage = playerData.getCompound(bannerKey);
            if (bannerStorage.contains("house_banner")) {
                bannerData = bannerStorage.getCompound("house_banner");
            }
        }

        PacketDistributor.sendToPlayer(serverPlayer, new SyncHouseBannerPacket(bannerData));
    }
}