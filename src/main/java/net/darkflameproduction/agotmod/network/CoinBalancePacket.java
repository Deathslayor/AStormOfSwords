package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record CoinBalancePacket(long balance) implements CustomPacketPayload {
    public static final Type<CoinBalancePacket> TYPE = new Type<>(AGoTMod.id("coin_balance"));

    public static final StreamCodec<FriendlyByteBuf, CoinBalancePacket> STREAM_CODEC = StreamCodec.composite(
            StreamCodec.of(
                    (buf, value) -> buf.writeLong(value),
                    FriendlyByteBuf::readLong
            ),
            CoinBalancePacket::balance,
            CoinBalancePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}