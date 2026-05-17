package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/** Server->client. Full current member list for the player's house. */
public record SyncHouseMembersPacket(List<String> memberNames) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SyncHouseMembersPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "sync_house_members"));

    public static final StreamCodec<FriendlyByteBuf, SyncHouseMembersPacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> {
                buf.writeInt(pkt.memberNames.size());
                pkt.memberNames.forEach(buf::writeUtf);
            },
            buf -> {
                int size = buf.readInt();
                List<String> names = new ArrayList<>();
                for (int i = 0; i < size; i++) names.add(buf.readUtf());
                return new SyncHouseMembersPacket(names);
            }
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
}