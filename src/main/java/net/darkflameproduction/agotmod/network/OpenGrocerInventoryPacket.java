package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerSystem;

import java.util.ArrayList;
import java.util.List;

public record OpenGrocerInventoryPacket(String grocerName, List<GrocerSystem.GrocerInventoryEntry> entries, long grocerBalance, long playerBalance) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<OpenGrocerInventoryPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "open_grocer_inventory"));

    public static final StreamCodec<FriendlyByteBuf, OpenGrocerInventoryPacket> STREAM_CODEC = StreamCodec.of(
            OpenGrocerInventoryPacket::write,
            OpenGrocerInventoryPacket::read
    );

    public static void write(FriendlyByteBuf buf, OpenGrocerInventoryPacket packet) {
        buf.writeUtf(packet.grocerName);
        buf.writeLong(packet.grocerBalance); // Write grocer balance
        buf.writeLong(packet.playerBalance); // NEW: Write player balance
        buf.writeInt(packet.entries.size());

        for (GrocerSystem.GrocerInventoryEntry entry : packet.entries) {
            buf.writeUtf(entry.displayName);
            buf.writeInt(entry.amount);
            buf.writeUtf(entry.itemKey);
        }
    }

    public static OpenGrocerInventoryPacket read(FriendlyByteBuf buf) {
        String grocerName = buf.readUtf();
        long grocerBalance = buf.readLong(); // Read grocer balance
        long playerBalance = buf.readLong(); // NEW: Read player balance
        int size = buf.readInt();
        List<GrocerSystem.GrocerInventoryEntry> entries = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String displayName = buf.readUtf();
            int amount = buf.readInt();
            String itemKey = buf.readUtf();
            entries.add(new GrocerSystem.GrocerInventoryEntry(displayName, amount, itemKey));
        }

        return new OpenGrocerInventoryPacket(grocerName, entries, grocerBalance, playerBalance);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}