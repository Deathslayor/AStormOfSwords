package net.darkflameproduction.agotmod.network;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.AGoTMod;

public record UpdateTownNamePacket(BlockPos pos, String newName) implements CustomPacketPayload {

    public static final Type<UpdateTownNamePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "update_town_name"));

    public static final StreamCodec<RegistryFriendlyByteBuf, UpdateTownNamePacket> STREAM_CODEC =
            StreamCodec.composite(
                    BlockPos.STREAM_CODEC, UpdateTownNamePacket::pos,
                    ByteBufCodecs.STRING_UTF8, UpdateTownNamePacket::newName,
                    UpdateTownNamePacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}