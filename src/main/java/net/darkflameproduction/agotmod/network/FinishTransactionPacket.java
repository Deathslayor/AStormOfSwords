package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public record FinishTransactionPacket(
        String grocerName,
        Map<String, Integer> itemAmounts,
        TransactionType transactionType,  // NEW: BUY or SELL
        Map<String, Integer> playerSlots  // NEW: For selling - tracks which slots items come from
) implements CustomPacketPayload {

    // NEW: Transaction type enum
    public enum TransactionType {
        BUY,
        SELL
    }

    // Convenience constructor for BUY transactions (backwards compatibility)
    public FinishTransactionPacket(String grocerName, Map<String, Integer> itemAmounts) {
        this(grocerName, itemAmounts, TransactionType.BUY, new HashMap<>());
    }

    // Constructor for SELL transactions
    public FinishTransactionPacket(String grocerName, Map<String, Integer> itemAmounts, Map<String, Integer> playerSlots) {
        this(grocerName, itemAmounts, TransactionType.SELL, playerSlots);
    }

    public static final CustomPacketPayload.Type<FinishTransactionPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "finish_transaction"));

    public static final StreamCodec<FriendlyByteBuf, FinishTransactionPacket> STREAM_CODEC = StreamCodec.of(
            FinishTransactionPacket::write,
            FinishTransactionPacket::read
    );

    public static void write(FriendlyByteBuf buf, FinishTransactionPacket packet) {
        buf.writeUtf(packet.grocerName);

        // Write transaction type
        buf.writeEnum(packet.transactionType);

        // Write item amounts
        buf.writeInt(packet.itemAmounts.size());
        for (Map.Entry<String, Integer> entry : packet.itemAmounts.entrySet()) {
            buf.writeUtf(entry.getKey());   // itemKey
            buf.writeInt(entry.getValue()); // amount
        }

        // Write player slots (for selling)
        buf.writeInt(packet.playerSlots.size());
        for (Map.Entry<String, Integer> entry : packet.playerSlots.entrySet()) {
            buf.writeUtf(entry.getKey());   // itemKey
            buf.writeInt(entry.getValue()); // slot number
        }
    }

    public static FinishTransactionPacket read(FriendlyByteBuf buf) {
        String grocerName = buf.readUtf();

        // Read transaction type
        TransactionType transactionType = buf.readEnum(TransactionType.class);

        // Read item amounts
        int itemSize = buf.readInt();
        Map<String, Integer> itemAmounts = new HashMap<>();
        for (int i = 0; i < itemSize; i++) {
            String itemKey = buf.readUtf();
            int amount = buf.readInt();
            itemAmounts.put(itemKey, amount);
        }

        // Read player slots
        int slotSize = buf.readInt();
        Map<String, Integer> playerSlots = new HashMap<>();
        for (int i = 0; i < slotSize; i++) {
            String itemKey = buf.readUtf();
            int slot = buf.readInt();
            playerSlots.put(itemKey, slot);
        }

        return new FinishTransactionPacket(grocerName, itemAmounts, transactionType, playerSlots);
    }

    // Convenience methods
    public String getGrocerName() {
        return grocerName;
    }

    public Map<String, Integer> getItemAmounts() {
        return itemAmounts;
    }

    // NEW: Backwards compatibility
    public Map<String, Integer> getItemsToSubtract() {
        return itemAmounts;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Map<String, Integer> getPlayerSlots() {
        return playerSlots;
    }

    public boolean isBuyTransaction() {
        return transactionType == TransactionType.BUY;
    }

    public boolean isSellTransaction() {
        return transactionType == TransactionType.SELL;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}