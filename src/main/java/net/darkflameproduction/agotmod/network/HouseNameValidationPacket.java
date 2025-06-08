package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record HouseNameValidationPacket(boolean isAvailable, String message) implements CustomPacketPayload {
    public static final Type<HouseNameValidationPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "house_name_validation")
    );

    public static final StreamCodec<FriendlyByteBuf, HouseNameValidationPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL,
            HouseNameValidationPacket::isAvailable,
            ByteBufCodecs.STRING_UTF8,
            HouseNameValidationPacket::message,
            HouseNameValidationPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}