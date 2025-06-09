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

        // Register the save house name packet (client -> server)
        registrar.playToServer(
                SaveHouseNamePacket.TYPE,
                SaveHouseNamePacket.STREAM_CODEC,
                SaveHouseNamePacket::handle
        );

        // Register request house name packet (client -> server)
        registrar.playToServer(
                RequestHouseNamePacket.TYPE,
                RequestHouseNamePacket.STREAM_CODEC,
                RequestHouseNamePacket::handle
        );

        // Register sync house name packet (server -> client)
        registrar.playToClient(
                SyncHouseNamePacket.TYPE,
                SyncHouseNamePacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleSyncHouseName(packet, context);
                    });
                }
        );

        // Register claim town hall packet (client -> server)
        registrar.playToServer(
                ClaimTownHallPacket.TYPE,
                ClaimTownHallPacket.STREAM_CODEC,
                ServerPacketHandler::handleClaimTownHall
        );

        // Register request owned towns packet (client -> server)
        registrar.playToServer(
                RequestOwnedTownsPacket.TYPE,
                RequestOwnedTownsPacket.STREAM_CODEC,
                ServerPacketHandler::handleRequestOwnedTowns
        );

        // Register sync owned towns packet (server -> client)
        registrar.playToClient(
                SyncOwnedTownsPacket.TYPE,
                SyncOwnedTownsPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleSyncOwnedTowns(packet, context);
                    });
                }
        );

        registrar.playToServer(
                CheckHouseNamePacket.TYPE,
                CheckHouseNamePacket.STREAM_CODEC,
                ServerPacketHandler::handleCheckHouseName
        );

        // Register house name validation packet (server -> client)
        registrar.playToClient(
                HouseNameValidationPacket.TYPE,
                HouseNameValidationPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleHouseNameValidation(packet, context);
                    });
                }
        );

        // Register request house banner packet (client -> server)
        registrar.playToServer(
                RequestHouseBannerPacket.TYPE,
                RequestHouseBannerPacket.STREAM_CODEC,
                RequestHouseBannerPacket::handle
        );

        // Register sync house banner packet (server -> client)
        registrar.playToClient(
                SyncHouseBannerPacket.TYPE,
                SyncHouseBannerPacket.STREAM_CODEC,
                (packet, context) -> {
                    context.enqueueWork(() -> {
                        ClientPacketHandler.handleSyncHouseBanner(packet, context);
                    });
                }
        );
    }


}