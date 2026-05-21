package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.gui.HouseData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = "agotmod", bus = EventBusSubscriber.Bus.MOD)
public class ModNetworking {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1.0");

        // ── Server -> Client ──────────────────────────────────────────────────

        registrar.playToClient(
                OpenGrocerInventoryPacket.TYPE,
                OpenGrocerInventoryPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleOpenGrocerInventory(packet, context))
        );
        registrar.playToClient(
                OpenTownHallMenuPacket.TYPE,
                OpenTownHallMenuPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleOpenTownHallMenu(packet, context))
        );
        registrar.playToClient(
                TownHallDataPacket.TYPE,
                TownHallDataPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleTownHallData(packet, context))
        );
        registrar.playToClient(
                TownHallRegisterPacket.TYPE,
                TownHallRegisterPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleTownHallRegister(packet, context))
        );
        registrar.playToClient(
                CoinBalancePacket.TYPE,
                CoinBalancePacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleCoinBalance(packet, context))
        );
        registrar.playToClient(
                SyncHouseNamePacket.TYPE,
                SyncHouseNamePacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleSyncHouseName(packet, context))
        );
        registrar.playToClient(
                SyncOwnedTownsPacket.TYPE,
                SyncOwnedTownsPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleSyncOwnedTowns(packet, context))
        );
        registrar.playToClient(
                HouseNameValidationPacket.TYPE,
                HouseNameValidationPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleHouseNameValidation(packet, context))
        );
        registrar.playToClient(
                OpenNpcConversationPacket.TYPE,
                OpenNpcConversationPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleOpenNpcConversation(packet, context))
        );
        registrar.playToClient(
                SyncHouseBannerPacket.TYPE,
                SyncHouseBannerPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleSyncHouseBanner(packet, context))
        );
        registrar.playToClient(
                SyncHouseInvitePacket.TYPE,
                SyncHouseInvitePacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleSyncHouseInvite(packet, context))
        );
        registrar.playToClient(
                SyncHouseMembersPacket.TYPE,
                SyncHouseMembersPacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        ClientPacketHandler.handleSyncHouseMembers(packet, context))
        );

        // ── Client -> Server ──────────────────────────────────────────────────

        registrar.playToServer(
                FinishTransactionPacket.TYPE,
                FinishTransactionPacket.STREAM_CODEC,
                ServerPacketHandler::handleFinishTransaction
        );
        registrar.playToServer(
                UpdateTownNamePacket.TYPE,
                UpdateTownNamePacket.STREAM_CODEC,
                ServerPacketHandler::handleUpdateTownName
        );
        registrar.playToServer(
                SaveHouseNamePacket.TYPE,
                SaveHouseNamePacket.STREAM_CODEC,
                SaveHouseNamePacket::handle
        );
        registrar.playToServer(
                RequestHouseNamePacket.TYPE,
                RequestHouseNamePacket.STREAM_CODEC,
                RequestHouseNamePacket::handle
        );
        registrar.playToServer(
                ClaimTownHallPacket.TYPE,
                ClaimTownHallPacket.STREAM_CODEC,
                ServerPacketHandler::handleClaimTownHall
        );
        registrar.playToServer(
                RequestOwnedTownsPacket.TYPE,
                RequestOwnedTownsPacket.STREAM_CODEC,
                ServerPacketHandler::handleRequestOwnedTowns
        );
        registrar.playToServer(
                CheckHouseNamePacket.TYPE,
                CheckHouseNamePacket.STREAM_CODEC,
                ServerPacketHandler::handleCheckHouseName
        );
        registrar.playToServer(
                CloseNpcConversationPacket.TYPE,
                CloseNpcConversationPacket.STREAM_CODEC,
                ServerPacketHandler::handleCloseNpcConversation
        );
        registrar.playToServer(
                OpenNpcInventoryPacket.TYPE,
                OpenNpcInventoryPacket.STREAM_CODEC,
                ServerPacketHandler::handleOpenNpcInventory
        );
        registrar.playToServer(
                OpenNpcTradePacket.TYPE,
                OpenNpcTradePacket.STREAM_CODEC,
                ServerPacketHandler::handleOpenNpcTrade
        );
        registrar.playToServer(
                RequestHouseBannerPacket.TYPE,
                RequestHouseBannerPacket.STREAM_CODEC,
                RequestHouseBannerPacket::handle
        );
        registrar.playToServer(
                SetTownHallCulturePacket.TYPE,
                SetTownHallCulturePacket.STREAM_CODEC,
                SetTownHallCulturePacket::handle
        );
        registrar.playToServer(
                SendHouseInvitePacket.TYPE,
                SendHouseInvitePacket.STREAM_CODEC,
                ServerHouseHandler::handleSendHouseInvite
        );
        registrar.playToServer(
                RespondHouseInvitePacket.TYPE,
                RespondHouseInvitePacket.STREAM_CODEC,
                ServerHouseHandler::handleRespondHouseInvite
        );
        registrar.playToServer(
                SaveHouseBannerPacket.TYPE,
                SaveHouseBannerPacket.STREAM_CODEC,
                SaveHouseBannerPacket::handle
        );

        // Client -> Server
        registrar.playToServer(
                LeaveHousePacket.TYPE,
                LeaveHousePacket.STREAM_CODEC,
                LeaveHousePacket::handle
        );
        registrar.playToServer(
                KickHouseMemberPacket.TYPE,
                KickHouseMemberPacket.STREAM_CODEC,
                KickHouseMemberPacket::handle
        );

// Server -> Client
        registrar.playToClient(
                SyncHouseRolePacket.TYPE,
                SyncHouseRolePacket.STREAM_CODEC,
                (packet, context) -> context.enqueueWork(() ->
                        HouseData.setIsFounder(packet.isFounder()))
        );

        registrar.playToServer(
                EnterTownPacket.TYPE,
                EnterTownPacket.STREAM_CODEC,
                EnterTownPacket::handle
        );
        registrar.playToServer(
                LeaveTownPacket.TYPE,
                LeaveTownPacket.STREAM_CODEC,
                LeaveTownPacket::handle
        );
    }
}