package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record TownHallDataPacket(
        BlockPos pos,
        int bedCount,
        int citizenCount,
        int currentRadius,
        String townName,
        boolean isClaimed,
        String claimedByHouse,
        CompoundTag claimedByHouseBanner  // NEW: Banner data
) implements CustomPacketPayload {

    public static final Type<TownHallDataPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_hall_data")
    );

    public static final StreamCodec<FriendlyByteBuf, TownHallDataPacket> STREAM_CODEC = StreamCodec.composite(
            BlockPos.STREAM_CODEC,
            TownHallDataPacket::pos,
            ByteBufCodecs.VAR_INT,
            TownHallDataPacket::bedCount,
            ByteBufCodecs.VAR_INT,
            TownHallDataPacket::citizenCount,
            ByteBufCodecs.VAR_INT,
            TownHallDataPacket::currentRadius,
            ByteBufCodecs.STRING_UTF8,
            TownHallDataPacket::townName,
            ByteBufCodecs.BOOL,
            TownHallDataPacket::isClaimed,
            ByteBufCodecs.STRING_UTF8,
            TownHallDataPacket::claimedByHouse,
            // NEW: Banner data codec (optional CompoundTag)
            ByteBufCodecs.COMPOUND_TAG.apply(ByteBufCodecs::optional),
            packet -> java.util.Optional.ofNullable(packet.claimedByHouseBanner()),
            optional -> optional.orElse(null),
            (pos, bedCount, citizenCount, currentRadius, townName, isClaimed, claimedByHouse, claimedByHouseBanner) ->
                    new TownHallDataPacket(pos, bedCount, citizenCount, currentRadius, townName, isClaimed, claimedByHouse, claimedByHouseBanner)
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}