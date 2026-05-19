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

/** Client->server. Sent when the founder confirms kicking a member by UUID. */
public record KickHouseMemberPacket(UUID targetUUID) implements CustomPacketPayload {

    public static final Type<KickHouseMemberPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "kick_house_member"));

    public static final StreamCodec<FriendlyByteBuf, KickHouseMemberPacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> {
                buf.writeLong(pkt.targetUUID.getMostSignificantBits());
                buf.writeLong(pkt.targetUUID.getLeastSignificantBits());
            },
            buf -> new KickHouseMemberPacket(new UUID(buf.readLong(), buf.readLong()))
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(KickHouseMemberPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer kicker)) return;

            UUID houseUUID = ServerHouseHandler.getPlayerHouseUUID(kicker);
            if (houseUUID == null) return;

            HouseSavedData saved = HouseSavedData.get(kicker.getServer());

            // Only founder can kick
            UUID founderUUID = saved.getFounderUUID(houseUUID);
            if (!kicker.getUUID().equals(founderUUID)) {
                kicker.sendSystemMessage(Component.literal("Only the house founder can kick members."));
                return;
            }

            // Cannot kick yourself
            if (packet.targetUUID().equals(kicker.getUUID())) {
                kicker.sendSystemMessage(Component.literal("You cannot kick yourself."));
                return;
            }

            // Find username for message
            String targetUsername = "Unknown";
            for (HouseSavedData.MemberRecord m : saved.getMembers(houseUUID)) {
                if (m.uuid.equals(packet.targetUUID())) { targetUsername = m.username; break; }
            }

            saved.removeMember(houseUUID, packet.targetUUID());

            // If the kicked player is online, clear their house data
            ServerPlayer target = kicker.getServer().getPlayerList().getPlayer(packet.targetUUID());
            if (target != null) {
                target.getPersistentData().remove(AGoTMod.MOD_ID + ".house");
                target.sendSystemMessage(Component.literal(
                        "You have been kicked from House "
                                + ServerHouseHandler.getOnlinePlayerHouseName(kicker) + "."));
            }

            kicker.sendSystemMessage(Component.literal(targetUsername + " has been kicked from the house."));
            ServerHouseHandler.syncMembersToAllHouseMembers(kicker.getServer(), houseUUID);
        });
    }
}