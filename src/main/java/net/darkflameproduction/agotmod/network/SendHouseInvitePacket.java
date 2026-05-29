package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SendHouseInvitePacket(String targetUsername) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SendHouseInvitePacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "send_house_invite"));

    public static final StreamCodec<FriendlyByteBuf, SendHouseInvitePacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> buf.writeUtf(pkt.targetUsername),
            buf -> new SendHouseInvitePacket(buf.readUtf())
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
