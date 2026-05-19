package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public record SaveHouseBannerPacket() implements CustomPacketPayload {

    public static final Type<SaveHouseBannerPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "save_house_banner"));

    public static final StreamCodec<FriendlyByteBuf, SaveHouseBannerPacket> STREAM_CODEC =
            StreamCodec.unit(new SaveHouseBannerPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(SaveHouseBannerPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer serverPlayer)) return;

            UUID houseUUID = ServerHouseHandler.getPlayerHouseUUID(serverPlayer);
            if (houseUUID == null) {
                serverPlayer.sendSystemMessage(
                        net.minecraft.network.chat.Component.literal(
                                "You must be in a noble house to set a house banner."));
                return;
            }

            ItemStack held = serverPlayer.getMainHandItem();
            if (held.isEmpty() || !(held.getItem() instanceof BannerItem)) {
                serverPlayer.sendSystemMessage(
                        net.minecraft.network.chat.Component.literal(
                                "Hold a banner in your main hand to set it as your house banner."));
                return;
            }

            Tag savedTag = held.save(serverPlayer.serverLevel().registryAccess());
            if (!(savedTag instanceof CompoundTag bannerData)) {
                serverPlayer.sendSystemMessage(
                        net.minecraft.network.chat.Component.literal("Failed to save banner data."));
                return;
            }

            ServerHouseHandler.saveBannerForHouse(serverPlayer.getServer(), houseUUID, bannerData);
            serverPlayer.sendSystemMessage(
                    net.minecraft.network.chat.Component.literal(
                            "House banner updated for House "
                                    + ServerHouseHandler.getOnlinePlayerHouseName(serverPlayer) + "!"));
        });
    }
}