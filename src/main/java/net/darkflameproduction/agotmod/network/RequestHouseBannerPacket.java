package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.data.HouseSavedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public record RequestHouseBannerPacket() implements CustomPacketPayload {

    public static final Type<RequestHouseBannerPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "request_house_banner"));

    public static final StreamCodec<FriendlyByteBuf, RequestHouseBannerPacket> STREAM_CODEC =
            StreamCodec.unit(new RequestHouseBannerPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(RequestHouseBannerPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer serverPlayer)) return;

            UUID houseUUID = ServerHouseHandler.getPlayerHouseUUID(serverPlayer);
            CompoundTag banner = null;

            if (houseUUID != null) {
                banner = HouseSavedData.get(serverPlayer.getServer()).getBanner(houseUUID);
            }

            PacketDistributor.sendToPlayer(serverPlayer, new SyncHouseBannerPacket(banner));
        });
    }
}
