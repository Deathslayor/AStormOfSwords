package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/** Client -> Server. Sent when player leaves a town area. */
public record LeaveTownPacket() implements CustomPacketPayload {

    public static final Type<LeaveTownPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "leave_town"));

    public static final StreamCodec<FriendlyByteBuf, LeaveTownPacket> STREAM_CODEC =
            StreamCodec.unit(new LeaveTownPacket());

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(LeaveTownPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;

            // Only switch adventure -> survival
            if (player.gameMode.getGameModeForPlayer() != GameType.ADVENTURE) return;

            player.setGameMode(GameType.SURVIVAL);
        });
    }
}