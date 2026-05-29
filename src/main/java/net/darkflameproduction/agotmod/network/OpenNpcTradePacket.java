package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public record OpenNpcTradePacket(UUID npcUUID) implements CustomPacketPayload {

    public static final Type<OpenNpcTradePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "open_npc_trade"));

    public static final StreamCodec<FriendlyByteBuf, OpenNpcTradePacket> STREAM_CODEC = StreamCodec.of(
            OpenNpcTradePacket::write,
            OpenNpcTradePacket::read
    );

    public static void write(FriendlyByteBuf buf, OpenNpcTradePacket packet) {
        buf.writeUUID(packet.npcUUID);
    }

    public static OpenNpcTradePacket read(FriendlyByteBuf buf) {
        return new OpenNpcTradePacket(buf.readUUID());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
