package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = AGoTMod.MOD_ID)
public class PlayerLoginHandler {

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            // Send current balance to client when they log in
            long balance = serverPlayer.getPersistentData().getLong("agotmod.coin_balance");
            PacketDistributor.sendToPlayer(serverPlayer, new CoinBalancePacket(balance));
            AGoTMod.LOGGER.info("Sent initial balance {} to player {}", balance, serverPlayer.getName().getString());
        }
    }
}