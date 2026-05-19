package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

/** Server->client. Tells the client whether the player is the house founder. */
public record SyncHouseRolePacket(boolean isFounder) implements CustomPacketPayload {

    public static final Type<SyncHouseRolePacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "sync_house_role"));

    public static final StreamCodec<FriendlyByteBuf, SyncHouseRolePacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> buf.writeBoolean(pkt.isFounder),
            buf -> new SyncHouseRolePacket(buf.readBoolean())
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }
}