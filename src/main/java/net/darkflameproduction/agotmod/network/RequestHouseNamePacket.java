package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record RequestHouseNamePacket() implements CustomPacketPayload {
    public static final Type<RequestHouseNamePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "request_house_name"));

    public static final StreamCodec<FriendlyByteBuf, RequestHouseNamePacket> STREAM_CODEC = StreamCodec.unit(new RequestHouseNamePacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(RequestHouseNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                CompoundTag persistentData = serverPlayer.getPersistentData();
                String houseName = "";

                if (persistentData.contains(AGoTMod.MOD_ID + ".house")) {
                    CompoundTag houseTag = persistentData.getCompound(AGoTMod.MOD_ID + ".house");
                    if (houseTag.contains("house_name")) {
                        houseName = houseTag.getString("house_name");
                    }
                }

                // Send the house name back to the client
                PacketDistributor.sendToPlayer(serverPlayer, new SyncHouseNamePacket(houseName));
            }
        });
    }
}