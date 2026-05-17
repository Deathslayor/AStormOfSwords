package net.darkflameproduction.agotmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerSystem;

import java.util.ArrayList;
import java.util.List;

public record OpenGrocerInventoryPacket(
        String grocerName,
        List<GrocerSystem.GrocerInventoryEntry> grocerEntries,
        List<PlayerInventoryEntry> playerEntries,
        long grocerBalance,
        long playerBalance,
        float buyMultiplier,
        float sellMultiplier
) implements CustomPacketPayload {

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

    public static final CustomPacketPayload.Type<OpenGrocerInventoryPacket> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("agotmod", "open_grocer_inventory"));

    public static final StreamCodec<FriendlyByteBuf, OpenGrocerInventoryPacket> STREAM_CODEC = StreamCodec.of(
            OpenGrocerInventoryPacket::write,
            OpenGrocerInventoryPacket::read
    );

    public static void write(FriendlyByteBuf buf, OpenGrocerInventoryPacket packet) {
        buf.writeUtf(packet.grocerName);
        buf.writeLong(packet.grocerBalance);
        buf.writeLong(packet.playerBalance);
        buf.writeFloat(packet.buyMultiplier);
        buf.writeFloat(packet.sellMultiplier);

        buf.writeInt(packet.grocerEntries.size());
        for (GrocerSystem.GrocerInventoryEntry entry : packet.grocerEntries) {
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

    public static OpenGrocerInventoryPacket read(FriendlyByteBuf buf) {
        String grocerName    = buf.readUtf();
        long grocerBalance   = buf.readLong();
        long playerBalance   = buf.readLong();
        float buyMultiplier  = buf.readFloat();
        float sellMultiplier = buf.readFloat();

        int grocerSize = buf.readInt();
        List<GrocerSystem.GrocerInventoryEntry> grocerEntries = new ArrayList<>();
        for (int i = 0; i < grocerSize; i++) {
            String displayName = buf.readUtf();
            long amount        = buf.readLong();
            String itemKey     = buf.readUtf();
            grocerEntries.add(new GrocerSystem.GrocerInventoryEntry(displayName, amount, itemKey));
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

        return new OpenGrocerInventoryPacket(grocerName, grocerEntries, playerEntries,
                grocerBalance, playerBalance, buyMultiplier, sellMultiplier);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}