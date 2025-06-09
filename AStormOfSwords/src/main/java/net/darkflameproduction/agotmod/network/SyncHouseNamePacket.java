package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SyncHouseNamePacket(String houseName) implements CustomPacketPayload {
    public static final Type<SyncHouseNamePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "sync_house_name"));

    public static final StreamCodec<FriendlyByteBuf, SyncHouseNamePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SyncHouseNamePacket::houseName,
            SyncHouseNamePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}