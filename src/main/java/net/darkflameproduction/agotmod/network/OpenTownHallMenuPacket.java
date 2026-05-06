package net.darkflameproduction.agotmod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;

public record OpenTownHallMenuPacket(BlockPos pos) implements CustomPacketPayload {

    public static final Type<OpenTownHallMenuPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "open_town_hall_menu"));

    public static final StreamCodec<RegistryFriendlyByteBuf, OpenTownHallMenuPacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, OpenTownHallMenuPacket::pos,
                    OpenTownHallMenuPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}