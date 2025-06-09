package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record RequestOwnedTownsPacket() implements CustomPacketPayload {
    public static final Type<RequestOwnedTownsPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "request_owned_towns")
    );

    public static final StreamCodec<FriendlyByteBuf, RequestOwnedTownsPacket> STREAM_CODEC =
            StreamCodec.unit(new RequestOwnedTownsPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}