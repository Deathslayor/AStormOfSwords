package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ClientCoinHandler {
    private static long clientCoinBalance = 0L;

    public static void handleCoinBalanceUpdate(CoinBalancePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            clientCoinBalance = packet.balance();
            AGoTMod.LOGGER.info("Client received coin balance update: {}", clientCoinBalance);
        });
    }

    public static long getClientCoinBalance() {
        return clientCoinBalance;
    }
}