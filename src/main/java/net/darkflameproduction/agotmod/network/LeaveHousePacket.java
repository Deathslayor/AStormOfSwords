package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.data.HouseSavedData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

/** Client->server. Sent when a non-founder member confirms they want to leave. */
public record LeaveHousePacket() implements CustomPacketPayload {

    public static final Type<LeaveHousePacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "leave_house"));

    public static final StreamCodec<FriendlyByteBuf, LeaveHousePacket> STREAM_CODEC =
            StreamCodec.unit(new LeaveHousePacket());

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(LeaveHousePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;

            UUID houseUUID = ServerHouseHandler.getPlayerHouseUUID(player);
            if (houseUUID == null) return;

            HouseSavedData saved = HouseSavedData.get(player.getServer());

            // Founder cannot leave — they can only disband (not implemented yet)
            UUID founderUUID = saved.getFounderUUID(houseUUID);
            if (player.getUUID().equals(founderUUID)) {
                player.sendSystemMessage(Component.literal(
                        "As the house founder you cannot leave. Rename the house if you wish to abandon it."));
                return;
            }

            String houseName = ServerHouseHandler.getOnlinePlayerHouseName(player);

            // Remove from saved data
            saved.removeMember(houseUUID, player.getUUID());

            // Clear house from player persistent data
            player.getPersistentData().remove(AGoTMod.MOD_ID + ".house");

            player.sendSystemMessage(Component.literal("You have left House " + houseName + "."));

            // Sync updated member list to remaining members
            ServerHouseHandler.syncMembersToAllHouseMembers(player.getServer(), houseUUID);
        });
    }
}
