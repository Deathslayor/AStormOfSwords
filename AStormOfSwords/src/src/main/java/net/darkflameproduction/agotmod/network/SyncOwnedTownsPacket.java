package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public record SyncOwnedTownsPacket(List<TownInfo> towns) implements CustomPacketPayload {
    public static final Type<SyncOwnedTownsPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "sync_owned_towns")
    );

    public static final StreamCodec<FriendlyByteBuf, SyncOwnedTownsPacket> STREAM_CODEC = StreamCodec.composite(
            TownInfo.STREAM_CODEC.apply(ByteBufCodecs.list()),
            SyncOwnedTownsPacket::towns,
            SyncOwnedTownsPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public record TownInfo(String townName, int population) {
        public static final StreamCodec<FriendlyByteBuf, TownInfo> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8,
                TownInfo::townName,
                ByteBufCodecs.VAR_INT,
                TownInfo::population,
                TownInfo::new
        );
    }
}