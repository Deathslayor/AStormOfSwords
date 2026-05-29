package net.darkflameproduction.agotmod.entity.custom.npc.system.grocer;

import net.minecraft.core.BlockPos;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GrocerInventoryTicketSystem {

    public static class InventoryRequestTicket {
        public final UUID grocerUUID;
        public final UUID playerUUID;
        public final BlockPos grocerPos;

        public InventoryRequestTicket(UUID grocerUUID, UUID playerUUID, BlockPos grocerPos) {
            this.grocerUUID = grocerUUID;
            this.playerUUID = playerUUID;
            this.grocerPos = grocerPos;
        }
    }

    public static class InventoryResponseTicket {
        public final UUID grocerUUID;
        public final UUID playerUUID;
        public final Map<String, Long> items;
        public final long townBalance;
        public final BlockPos townHallPos;

        public InventoryResponseTicket(UUID grocerUUID, UUID playerUUID, Map<String, Long> items,
                                       long townBalance, BlockPos townHallPos) {
            this.grocerUUID = grocerUUID;
            this.playerUUID = playerUUID;
            this.items = Collections.unmodifiableMap(new HashMap<>(items));
            this.townBalance = townBalance;
            this.townHallPos = townHallPos;
        }
    }

    public interface TicketListener {
        BlockPos getTownHallPos();
        void onRequestPosted(InventoryRequestTicket ticket);
    }

    private static final Map<UUID, InventoryRequestTicket>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, InventoryResponseTicket> pendingResponses = new ConcurrentHashMap<>();
    private static final List<TicketListener>               listeners        = new ArrayList<>();

    public static void registerListener(TicketListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(TicketListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID grocerUUID, UUID playerUUID, BlockPos grocerPos) {
        InventoryRequestTicket ticket = new InventoryRequestTicket(grocerUUID, playerUUID, grocerPos);
        pendingRequests.put(grocerUUID, ticket);

        // Find the closest town hall listener
        TicketListener closest     = null;
        double         closestDist = Double.MAX_VALUE;

        for (TicketListener listener : listeners) {
            double dist = grocerPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) {
            closest.onRequestPosted(ticket);
        }
    }

    public static void consumeRequest(UUID grocerUUID) {
        pendingRequests.remove(grocerUUID);
    }

    public static void postResponse(UUID grocerUUID, UUID playerUUID, Map<String, Long> items,
                                    long townBalance, BlockPos townHallPos) {
        pendingResponses.put(grocerUUID, new InventoryResponseTicket(
                grocerUUID, playerUUID, items, townBalance, townHallPos));
    }

    public static boolean hasPendingResponse(UUID grocerUUID) {
        return pendingResponses.containsKey(grocerUUID);
    }

    public static InventoryResponseTicket consumeResponse(UUID grocerUUID) {
        return pendingResponses.remove(grocerUUID);
    }
}
