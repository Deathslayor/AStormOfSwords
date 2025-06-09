package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public record FinishTransactionPacket(String grocerName, Map<String, Integer> itemsToSubtract) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FinishTransactionPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "finish_transaction"));

    public static final StreamCodec<FriendlyByteBuf, FinishTransactionPacket> STREAM_CODEC = StreamCodec.of(
            FinishTransactionPacket::write,
            FinishTransactionPacket::read
    );

    public static void write(FriendlyByteBuf buf, FinishTransactionPacket packet) {
        buf.writeUtf(packet.grocerName);
        buf.writeInt(packet.itemsToSubtract.size());

        for (Map.Entry<String, Integer> entry : packet.itemsToSubtract.entrySet()) {
            buf.writeUtf(entry.getKey());   // itemKey
            buf.writeInt(entry.getValue()); // amount
        }
    }

    public static FinishTransactionPacket read(FriendlyByteBuf buf) {
        String grocerName = buf.readUtf();
        int size = buf.readInt();
        Map<String, Integer> itemsToSubtract = new HashMap<>();

        for (int i = 0; i < size; i++) {
            String itemKey = buf.readUtf();
            int amount = buf.readInt();
            itemsToSubtract.put(itemKey, amount);
        }

        return new FinishTransactionPacket(grocerName, itemsToSubtract);
    }

    // Add these methods to your FinishTransactionPacket class:
    public String getGrocerName() {
        return grocerName;
    }

    public Map<String, Integer> getItemsToSubtract() {
        return itemsToSubtract;
    }


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}