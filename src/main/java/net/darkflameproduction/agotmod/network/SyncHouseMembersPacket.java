package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Server->client. Ordered member list.
 * Carries only username + UUID — no base64 skin blob.
 * Client resolves the skin itself via SkinManager using the UUID.
 */
public record SyncHouseMembersPacket(List<MemberEntry> members) implements CustomPacketPayload {

    public record MemberEntry(String username, UUID uuid) {}

    public static final CustomPacketPayload.Type<SyncHouseMembersPacket> TYPE =
            new CustomPacketPayload.Type<>(
                    ResourceLocation.fromNamespaceAndPath("agotmod", "sync_house_members"));

    public static final StreamCodec<FriendlyByteBuf, SyncHouseMembersPacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> {
                buf.writeInt(pkt.members.size());
                for (MemberEntry e : pkt.members) {
                    buf.writeUtf(e.username());
                    buf.writeLong(e.uuid().getMostSignificantBits());
                    buf.writeLong(e.uuid().getLeastSignificantBits());
                }
            },
            buf -> {
                int size = buf.readInt();
                List<MemberEntry> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    String username = buf.readUtf();
                    UUID uuid = new UUID(buf.readLong(), buf.readLong());
                    list.add(new MemberEntry(username, uuid));
                }
                return new SyncHouseMembersPacket(list);
            }
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
}