package net.darkflameproduction.agotmod.network;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.gui.GrocerInventoryScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record CoinBalancePacket(long balance) implements CustomPacketPayload {
    public static final Type<CoinBalancePacket> TYPE = new Type<>(AGoTMod.id("coin_balance"));

    public static final StreamCodec<FriendlyByteBuf, CoinBalancePacket> STREAM_CODEC = StreamCodec.composite(
            StreamCodec.of(
                    (buf, value) -> buf.writeLong(value),
                    FriendlyByteBuf::readLong
            ),
            CoinBalancePacket::balance,
            CoinBalancePacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    // Client-side packet handler
    public static void handle(CoinBalancePacket packet, IPayloadContext context) {
        context.enqueueWork(() -> {
            // Update the client-side coin balance
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft.player != null) {
                // Update the player's persistent data on client side
                minecraft.player.getPersistentData().putLong("agotmod.coin_balance", packet.balance());

                // Also update the grocer GUI if it's open
                GrocerInventoryScreen.updatePlayerBalance(packet.balance());

                System.out.println("DEBUG: Updated client coin balance to: " + packet.balance());
            }
        });
    }
}