package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RespondHouseInvitePacket(boolean accepted) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RespondHouseInvitePacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "respond_house_invite"));

    public static final StreamCodec<FriendlyByteBuf, RespondHouseInvitePacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> buf.writeBoolean(pkt.accepted),
            buf -> new RespondHouseInvitePacket(buf.readBoolean())
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
}
