package net.darkflameproduction.agotmod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;

public record TownHallDataPacket(BlockPos pos, int bedCount, int citizenCount, int currentRadius, String townName) implements CustomPacketPayload {

    public static final Type<TownHallDataPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_hall_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, TownHallDataPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, TownHallDataPacket::pos,
                    ByteBufCodecs.VAR_INT, TownHallDataPacket::bedCount,
                    ByteBufCodecs.VAR_INT, TownHallDataPacket::citizenCount,
                    ByteBufCodecs.VAR_INT, TownHallDataPacket::currentRadius,
                    ByteBufCodecs.STRING_UTF8, TownHallDataPacket::townName,
                    TownHallDataPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}