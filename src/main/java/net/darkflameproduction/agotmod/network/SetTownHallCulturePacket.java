package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.Culture;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SetTownHallCulturePacket(BlockPos pos, Culture culture)
        implements CustomPacketPayload {

    public static final Type<SetTownHallCulturePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(AGoTMod.MOD_ID, "set_town_hall_culture"));

    public static final StreamCodec<FriendlyByteBuf, SetTownHallCulturePacket> STREAM_CODEC =
            StreamCodec.of(
                    (buf, pkt) -> {
                        buf.writeBlockPos(pkt.pos);
                        buf.writeEnum(pkt.culture);
                    },
                    buf -> new SetTownHallCulturePacket(buf.readBlockPos(), buf.readEnum(Culture.class))
            );

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(SetTownHallCulturePacket pkt, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) ctx.player();
            BlockEntity be = player.level().getBlockEntity(pkt.pos);
            if (!(be instanceof TownHallBlockEntity townHall)) return;
            // Only allow if town hall is claimed by this player and has no culture yet
            if (!townHall.isClaimed()) return;
            if (!player.getUUID().equals(townHall.getClaimedByPlayerUUID())) return;
            if (townHall.getCulture() != Culture.NONE) return;
            townHall.setCulture(pkt.culture);
        });
    }
}