package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SaveHouseNamePacket(String houseName) implements CustomPacketPayload {
    public static final Type<SaveHouseNamePacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "save_house_name"));

    public static final StreamCodec<FriendlyByteBuf, SaveHouseNamePacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            SaveHouseNamePacket::houseName,
            SaveHouseNamePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(SaveHouseNamePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (context.player() instanceof ServerPlayer serverPlayer) {
                String proposedName = packet.houseName().trim();
                String currentHouseName = "";

                // Get current house name
                if (serverPlayer.getPersistentData().contains(AGoTMod.MOD_ID + ".house")) {
                    CompoundTag houseTag = serverPlayer.getPersistentData().getCompound(AGoTMod.MOD_ID + ".house");
                    if (houseTag.contains("house_name")) {
                        currentHouseName = houseTag.getString("house_name");
                    }
                }

                // If name hasn't changed, just save it
                if (proposedName.equals(currentHouseName)) {
                    CompoundTag persistentData = serverPlayer.getPersistentData();
                    CompoundTag houseTag = new CompoundTag();
                    houseTag.putString("house_name", proposedName);
                    persistentData.put(AGoTMod.MOD_ID + ".house", houseTag);

                    AGoTMod.LOGGER.info("Confirmed house name '{}' for player {}", proposedName, serverPlayer.getName().getString());
                    return;
                }

                // Check if the new name is available
                boolean isAvailable = net.darkflameproduction.agotmod.network.ServerPacketHandler.isHouseNameAvailable(proposedName, serverPlayer);

                if (isAvailable) {
                    // Save the new house name
                    CompoundTag persistentData = serverPlayer.getPersistentData();
                    CompoundTag houseTag = new CompoundTag();
                    houseTag.putString("house_name", proposedName);
                    persistentData.put(AGoTMod.MOD_ID + ".house", houseTag);

                    AGoTMod.LOGGER.info("Saved house name '{}' for player {}", proposedName, serverPlayer.getName().getString());

                    // NO MORE TOWN OWNERSHIP UPDATE CODE NEEDED!
                    // Towns automatically show the new house name because they're linked to player UUID

                    serverPlayer.sendSystemMessage(Component.literal("House name set to '" + proposedName + "'"));
                } else {
                    serverPlayer.sendSystemMessage(Component.literal("House name '" + proposedName + "' is already taken by another player!"));
                    AGoTMod.LOGGER.info("Rejected duplicate house name '{}' for player {}", proposedName, serverPlayer.getName().getString());
                }
            }
        });
    }
}