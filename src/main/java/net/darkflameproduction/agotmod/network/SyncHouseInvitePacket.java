package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Server->client. Empty strings clear the invite. */
public record SyncHouseInvitePacket(String houseName, String inviterName) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SyncHouseInvitePacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "sync_house_invite"));

    public static final StreamCodec<FriendlyByteBuf, SyncHouseInvitePacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> { buf.writeUtf(pkt.houseName); buf.writeUtf(pkt.inviterName); },
            buf -> new SyncHouseInvitePacket(buf.readUtf(), buf.readUtf())
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
