package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public record TownHallDataPacket(
        BlockPos pos,
        int bedCount,
        int citizenCount,
        int currentRadius,
        String townName,
        boolean isClaimed,
        String claimedByHouse,
        int availableJobCount,
        int assignedJobCount,
        int totalJobCount,
        int joblessCount,
        long townBalance,
        long townIncome,
        Map<String, Long> townInventory,
        String culture
) implements CustomPacketPayload {

    public static final Type<TownHallDataPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "town_hall_data")
    );

    public static final StreamCodec<FriendlyByteBuf, TownHallDataPacket> STREAM_CODEC = StreamCodec.of(
            TownHallDataPacket::encode,
            TownHallDataPacket::decode
    );

    private static void encode(FriendlyByteBuf buf, TownHallDataPacket packet) {
        buf.writeBlockPos(packet.pos);
        buf.writeVarInt(packet.bedCount);
        buf.writeVarInt(packet.citizenCount);
        buf.writeVarInt(packet.currentRadius);
        buf.writeUtf(packet.townName);
        buf.writeUtf(packet.claimedByHouse);
        buf.writeBoolean(packet.isClaimed);
        buf.writeVarInt(packet.availableJobCount);
        buf.writeVarInt(packet.assignedJobCount);
        buf.writeVarInt(packet.totalJobCount);
        buf.writeVarInt(packet.joblessCount);
        buf.writeLong(packet.townBalance);
        buf.writeLong(packet.townIncome);
        buf.writeVarInt(packet.townInventory.size());
        for (Map.Entry<String, Long> entry : packet.townInventory.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeLong(entry.getValue());
        }
        buf.writeUtf(packet.culture);
    }

    private static TownHallDataPacket decode(FriendlyByteBuf buf) {
        BlockPos pos          = buf.readBlockPos();
        int bedCount          = buf.readVarInt();
        int citizenCount      = buf.readVarInt();
        int currentRadius     = buf.readVarInt();
        String townName       = buf.readUtf();
        String claimedByHouse = buf.readUtf();
        boolean isClaimed     = buf.readBoolean();
        int availableJobCount = buf.readVarInt();
        int assignedJobCount  = buf.readVarInt();
        int totalJobCount     = buf.readVarInt();
        int joblessCount      = buf.readVarInt();
        long townBalance      = buf.readLong();
        long townIncome       = buf.readLong();
        int inventorySize     = buf.readVarInt();
        Map<String, Long> townInventory = new HashMap<>();
        for (int i = 0; i < inventorySize; i++) {
            townInventory.put(buf.readUtf(), buf.readLong());
        }
        String culture = buf.readUtf();

        return new TownHallDataPacket(
                pos, bedCount, citizenCount, currentRadius,
                townName, isClaimed, claimedByHouse,
                availableJobCount, assignedJobCount, totalJobCount, joblessCount,
                townBalance, townIncome, townInventory, culture
        );
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}