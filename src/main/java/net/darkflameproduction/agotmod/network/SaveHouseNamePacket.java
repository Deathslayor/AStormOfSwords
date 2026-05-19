package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.data.HouseSavedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public record SaveHouseNamePacket(String houseName) implements CustomPacketPayload {

    public static final Type<SaveHouseNamePacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "save_house_name"));

    public static final StreamCodec<FriendlyByteBuf, SaveHouseNamePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SaveHouseNamePacket::houseName,
            SaveHouseNamePacket::new
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(SaveHouseNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer serverPlayer)) return;

            String proposedName = packet.houseName().trim();
            if (proposedName.isEmpty()) return;

            String loreError = ServerHouseHandler.validateHouseName(proposedName);
            if (loreError != null) {
                serverPlayer.sendSystemMessage(Component.literal(loreError));
                return;
            }

            String currentName  = ServerHouseHandler.getOnlinePlayerHouseName(serverPlayer);
            UUID   existingUUID = ServerHouseHandler.getPlayerHouseUUID(serverPlayer);

            HouseSavedData saved = HouseSavedData.get(serverPlayer.getServer());

            // If player already has a house, only the founder can rename it
            if (existingUUID != null) {
                UUID founderUUID = saved.getFounderUUID(existingUUID);
                if (!serverPlayer.getUUID().equals(founderUUID)) {
                    serverPlayer.sendSystemMessage(Component.literal(
                            "Only the house founder can rename the house."));
                    return;
                }
            }

            // Name unchanged — just refresh
            if (proposedName.equalsIgnoreCase(currentName) && existingUUID != null) {
                saved.addMember(existingUUID, serverPlayer.getUUID(),
                        serverPlayer.getGameProfile().getName());
                ServerHouseHandler.syncMembersToAllHouseMembers(serverPlayer.getServer(), existingUUID);
                syncRole(serverPlayer, existingUUID, saved);
                return;
            }

            if (!ServerPacketHandler.isHouseNameAvailable(proposedName, serverPlayer)) {
                serverPlayer.sendSystemMessage(Component.literal(
                        "House name '" + proposedName + "' is already taken!"));
                return;
            }

            UUID houseUUID;
            if (existingUUID != null) {
                // Rename — preserve all data
                saved.renameHouse(existingUUID, proposedName);
                houseUUID = existingUUID;
                // Update house_name in all online members' persistent data
                for (ServerPlayer member : serverPlayer.getServer().getPlayerList().getPlayers()) {
                    if (houseUUID.equals(ServerHouseHandler.getPlayerHouseUUID(member))) {
                        setPlayerHouseTag(member, houseUUID, proposedName);
                    }
                }
            } else {
                // New house — player becomes founder
                houseUUID = saved.createHouse(proposedName, serverPlayer.getUUID());
            }

            setPlayerHouseTag(serverPlayer, houseUUID, proposedName);
            saved.addMember(houseUUID, serverPlayer.getUUID(),
                    serverPlayer.getGameProfile().getName());

            serverPlayer.sendSystemMessage(Component.literal(
                    "House name set to '" + proposedName + "'"));
            ServerHouseHandler.syncMembersToAllHouseMembers(serverPlayer.getServer(), houseUUID);
            syncRole(serverPlayer, houseUUID, saved);
        });
    }

    private static void setPlayerHouseTag(ServerPlayer player, UUID houseUUID, String houseName) {
        CompoundTag tag = new CompoundTag();
        tag.putString("house_name", houseName);
        tag.putString("house_uuid", houseUUID.toString());
        player.getPersistentData().put(AGoTMod.MOD_ID + ".house", tag);
    }

    private static void syncRole(ServerPlayer player, UUID houseUUID, HouseSavedData saved) {
        boolean isFounder = player.getUUID().equals(saved.getFounderUUID(houseUUID));
        PacketDistributor.sendToPlayer(player, new SyncHouseRolePacket(isFounder));
    }
}