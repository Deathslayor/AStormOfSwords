package net.darkflameproduction.agotmod.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = "agotmod", bus = EventBusSubscriber.Bus.MOD)
public class ModNetworking {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0");

        // Register client-bound packets (server -> client)
        registrar.playToClient(
                OpenGrocerInventoryPacket.TYPE,
                OpenGrocerInventoryPacket.STREAM_CODEC,
                ClientPacketHandler::handleOpenGrocerInventory
        );

        // Register coin balance packet (server -> client)
        registrar.playToClient(
                CoinBalancePacket.TYPE,
                CoinBalancePacket.STREAM_CODEC,
                ClientCoinHandler::handleCoinBalanceUpdate
        );

        // Register server-bound packets (client -> server)
        registrar.playToServer(
                FinishTransactionPacket.TYPE,
                FinishTransactionPacket.STREAM_CODEC,
                ServerPacketHandler::handleFinishTransaction
        );
    }
}