package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public record OpenNpcInventoryPacket(UUID npcUUID) implements CustomPacketPayload {

    public static final Type<OpenNpcInventoryPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "open_npc_inventory"));

    public static final StreamCodec<FriendlyByteBuf, OpenNpcInventoryPacket> STREAM_CODEC = StreamCodec.of(
            OpenNpcInventoryPacket::write,
            OpenNpcInventoryPacket::read
    );

    public static void write(FriendlyByteBuf buf, OpenNpcInventoryPacket packet) {
        buf.writeUUID(packet.npcUUID);
    }

    public static OpenNpcInventoryPacket read(FriendlyByteBuf buf) {
        return new OpenNpcInventoryPacket(buf.readUUID());
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
