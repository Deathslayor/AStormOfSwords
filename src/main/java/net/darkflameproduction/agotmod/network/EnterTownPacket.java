package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

// ── EnterTownPacket ───────────────────────────────────────────────────────
// Client -> Server. Sent when player enters a town area.

public record EnterTownPacket(boolean isClaimed, String ownerHouseDisplay)
        implements CustomPacketPayload {

    public static final Type<EnterTownPacket> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "enter_town"));

    public static final StreamCodec<FriendlyByteBuf, EnterTownPacket> STREAM_CODEC = StreamCodec.of(
            (buf, pkt) -> {
                buf.writeBoolean(pkt.isClaimed());
                buf.writeUtf(pkt.ownerHouseDisplay());
            },
            buf -> new EnterTownPacket(buf.readBoolean(), buf.readUtf())
    );

    @Override public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(EnterTownPacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            if (!(context.player() instanceof ServerPlayer player)) return;

            // Only switch survival -> adventure
            if (player.gameMode.getGameModeForPlayer() != GameType.SURVIVAL) return;

            // If unclaimed, always restrict
            if (!packet.isClaimed()) {
                player.setGameMode(GameType.ADVENTURE);
                return;
            }

            // Claimed — check if player is in the owning house
            // We resolve the owner string from the packet display name,
            // but actual authority check is done server-side via house UUID
            UUID playerHouseUUID = net.darkflameproduction.agotmod.network.ServerHouseHandler
                    .getPlayerHouseUUID(player);

            // Find the matching protection zone to get the owning house UUID
            boolean isOwner = false;
            if (playerHouseUUID != null) {
                for (net.darkflameproduction.agotmod.entity.custom.npc.system.protection
                        .TownProtectionZone.ZoneEntry zone :
                        net.darkflameproduction.agotmod.entity.custom.npc.system.protection
                                .TownProtectionZone.getAllZones()) {
                    if (playerHouseUUID.equals(zone.ownerHouseUUID)) {
                        isOwner = true;
                        break;
                    }
                }
            }

            if (!isOwner) {
                player.setGameMode(GameType.ADVENTURE);
            }
        });
    }
}
