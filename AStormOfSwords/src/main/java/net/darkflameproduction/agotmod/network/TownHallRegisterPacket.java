package net.darkflameproduction.agotmod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;

public record TownHallRegisterPacket(BlockPos pos, boolean register) implements CustomPacketPayload {

    public static final Type<TownHallRegisterPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_hall_register"));

    public static final StreamCodec<RegistryFriendlyByteBuf, TownHallRegisterPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, TownHallRegisterPacket::pos,
                    ByteBufCodecs.BOOL, TownHallRegisterPacket::register,
                    TownHallRegisterPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}