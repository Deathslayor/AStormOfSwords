package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SyncHouseBannerPacket(CompoundTag bannerData) implements CustomPacketPayload {
    public static final Type<SyncHouseBannerPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "sync_house_banner"));

    public static final StreamCodec<FriendlyByteBuf, SyncHouseBannerPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.COMPOUND_TAG.apply(ByteBufCodecs::optional),
            packet -> java.util.Optional.ofNullable(packet.bannerData()),
            optional -> new SyncHouseBannerPacket(optional.orElse(null))
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}