package net.darkflameproduction.agotmod.network;

import net.neoforged.api.distmarker.Dist;
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
        // Use lambda expressions to avoid loading client classes during registration
        registrar.playToClient(
                OpenGrocerInventoryPacket.TYPE,
                OpenGrocerInventoryPacket.STREAM_CODEC,
                (packet, context) -> {
                    // Only handle on client side - this lambda won't load client classes until executed
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleOpenGrocerInventory(packet, context);
                    });
                }
        );

        // Register Town Hall menu packet (server -> client)
        registrar.playToClient(
                OpenTownHallMenuPacket.TYPE,
                OpenTownHallMenuPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleOpenTownHallMenu(packet, context);
                    });
                }
        );

        // Register Town Hall data packet (server -> client)
        registrar.playToClient(
                TownHallDataPacket.TYPE,
                TownHallDataPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleTownHallData(packet, context);
                    });
                }
        );

        // Register Town Hall register packet (server -> client)
        registrar.playToClient(
                TownHallRegisterPacket.TYPE,
                TownHallRegisterPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleTownHallRegister(packet, context);
                    });
                }
        );

        // Register coin balance packet (server -> client)
        registrar.playToClient(
                CoinBalancePacket.TYPE,
                CoinBalancePacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleCoinBalance(packet, context);
                    });
                }
        );

        // Register server-bound packets (client -> server)
        // Server packets are fine as they don't reference client classes
        registrar.playToServer(
                FinishTransactionPacket.TYPE,
                FinishTransactionPacket.STREAM_CODEC,
                ServerPacketHandler::handleFinishTransaction
        );

        // Register the new town name update packet (client -> server)
        registrar.playToServer(
                UpdateTownNamePacket.TYPE,
                UpdateTownNamePacket.STREAM_CODEC,
                ServerPacketHandler::handleUpdateTownName
        );
    }
}