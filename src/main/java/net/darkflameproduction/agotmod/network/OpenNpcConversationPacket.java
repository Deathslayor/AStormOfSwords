package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public record OpenNpcConversationPacket(UUID npcUUID, String jobType, String npcName) implements CustomPacketPayload {

    public static final Type<OpenNpcConversationPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "open_npc_conversation"));

    public static final StreamCodec<FriendlyByteBuf, OpenNpcConversationPacket> STREAM_CODEC = StreamCodec.of(
            OpenNpcConversationPacket::write,
            OpenNpcConversationPacket::read
    );

    public static void write(FriendlyByteBuf buf, OpenNpcConversationPacket packet) {
        buf.writeUUID(packet.npcUUID);
        buf.writeUtf(packet.jobType);
        buf.writeUtf(packet.npcName);
    }

    public static OpenNpcConversationPacket read(FriendlyByteBuf buf) {
        return new OpenNpcConversationPacket(buf.readUUID(), buf.readUtf(), buf.readUtf());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}