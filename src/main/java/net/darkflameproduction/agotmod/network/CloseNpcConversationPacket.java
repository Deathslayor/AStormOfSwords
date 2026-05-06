package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public record CloseNpcConversationPacket(UUID npcUUID) implements CustomPacketPayload {

    public static final Type<CloseNpcConversationPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "close_npc_conversation"));

    public static final StreamCodec<FriendlyByteBuf, CloseNpcConversationPacket> STREAM_CODEC = StreamCodec.of(
            CloseNpcConversationPacket::write,
            CloseNpcConversationPacket::read
    );

    public static void write(FriendlyByteBuf buf, CloseNpcConversationPacket packet) {
        buf.writeUUID(packet.npcUUID);
    }

    public static CloseNpcConversationPacket read(FriendlyByteBuf buf) {
        return new CloseNpcConversationPacket(buf.readUUID());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}