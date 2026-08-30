package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Opens the trading GUI for any shop-type NPC (Grocer, Butcher, Tanner,
 * Tailor, Blacksmith, Carpenter, Trader) - the catalog/entries are just
 * whatever the town inventory has, pre-filtered by job on the server.
 */
public record OpenTradingInventoryPacket(
        String tradingName,
        List<TradingInventoryEntry> tradingEntries,
        List<PlayerInventoryEntry> playerEntries,
        long tradingBalance,
        long playerBalance,
        float buyMultiplier,
        float sellMultiplier
) implements CustomPacketPayload {

    /** A single catalog entry offered by the trading NPC. */
    public static class TradingInventoryEntry {
        public final String displayName;
        public final long amount;
        public final String itemKey;

        public TradingInventoryEntry(String displayName, long amount, String itemKey) {
            this.displayName = displayName;
            this.amount      = amount;
            this.itemKey     = itemKey;
        }
    }

    public static class PlayerInventoryEntry {
        public final String displayName;
        public final int amount;
        public final String itemKey;
        public final int slot;

        public PlayerInventoryEntry(String displayName, int amount, String itemKey, int slot) {
            this.displayName = displayName;
            this.amount      = amount;
            this.itemKey     = itemKey;
            this.slot        = slot;
        }
    }

    public static final CustomPacketPayload.Type<OpenTradingInventoryPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "open_trading_inventory"));

    public static final StreamCodec<FriendlyByteBuf, OpenTradingInventoryPacket> STREAM_CODEC = StreamCodec.of(
            OpenTradingInventoryPacket::write,
            OpenTradingInventoryPacket::read
    );

    public static void write(FriendlyByteBuf buf, OpenTradingInventoryPacket packet) {
        buf.writeUtf(packet.tradingName);
        buf.writeLong(packet.tradingBalance);
        buf.writeLong(packet.playerBalance);
        buf.writeFloat(packet.buyMultiplier);
        buf.writeFloat(packet.sellMultiplier);

        buf.writeInt(packet.tradingEntries.size());
        for (TradingInventoryEntry entry : packet.tradingEntries) {
            buf.writeUtf(entry.displayName);
            buf.writeLong(entry.amount);
            buf.writeUtf(entry.itemKey);
        }

        buf.writeInt(packet.playerEntries.size());
        for (PlayerInventoryEntry entry : packet.playerEntries) {
            buf.writeUtf(entry.displayName);
            buf.writeInt(entry.amount);
            buf.writeUtf(entry.itemKey);
            buf.writeInt(entry.slot);
        }
    }

    public static OpenTradingInventoryPacket read(FriendlyByteBuf buf) {
        String tradingName   = buf.readUtf();
        long tradingBalance  = buf.readLong();
        long playerBalance   = buf.readLong();
        float buyMultiplier  = buf.readFloat();
        float sellMultiplier = buf.readFloat();

        int tradingSize = buf.readInt();
        List<TradingInventoryEntry> tradingEntries = new ArrayList<>();
        for (int i = 0; i < tradingSize; i++) {
            String displayName = buf.readUtf();
            long amount        = buf.readLong();
            String itemKey     = buf.readUtf();
            tradingEntries.add(new TradingInventoryEntry(displayName, amount, itemKey));
        }

        int playerSize = buf.readInt();
        List<PlayerInventoryEntry> playerEntries = new ArrayList<>();
        for (int i = 0; i < playerSize; i++) {
            String displayName = buf.readUtf();
            int amount         = buf.readInt();
            String itemKey     = buf.readUtf();
            int slot           = buf.readInt();
            playerEntries.add(new PlayerInventoryEntry(displayName, amount, itemKey, slot));
        }

        return new OpenTradingInventoryPacket(tradingName, tradingEntries, playerEntries,
                tradingBalance, playerBalance, buyMultiplier, sellMultiplier);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
