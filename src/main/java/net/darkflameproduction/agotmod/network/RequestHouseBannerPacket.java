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

public record RequestHouseBannerPacket() implements CustomPacketPayload {
    public static final Type<RequestHouseBannerPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "request_house_banner"));

    public static final StreamCodec<FriendlyByteBuf, RequestHouseBannerPacket> STREAM_CODEC = StreamCodec.unit(new RequestHouseBannerPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(RequestHouseBannerPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                CompoundTag persistentData = serverPlayer.getPersistentData();
                CompoundTag bannerData = null;

                // Load banner data from UUID-based storage
                String playerUUID = serverPlayer.getUUID().toString();
                String bannerKey = AGoTMod.MOD_ID + ".player_banner_" + playerUUID;

                if (persistentData.contains(bannerKey)) {
                    CompoundTag bannerStorage = persistentData.getCompound(bannerKey);
                    if (bannerStorage.contains("house_banner")) {
                        bannerData = bannerStorage.getCompound("house_banner");
                    }
                }

                // Send the banner data back to the client (null if no banner)
                PacketDistributor.sendToPlayer(serverPlayer, new SyncHouseBannerPacket(bannerData));
            }
        });
    }
}