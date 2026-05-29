package net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter;

import net.minecraft.core.BlockPos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CarpenterInventoryTicketSystem {

    public static class InventoryRequestTicket {
        public final UUID carpenterUUID;
        public final UUID playerUUID;
        public final BlockPos carpenterPos;

        public InventoryRequestTicket(UUID carpenterUUID, UUID playerUUID, BlockPos carpenterPos) {
            this.carpenterUUID = carpenterUUID;
            this.playerUUID    = playerUUID;
            this.carpenterPos  = carpenterPos;
        }
    }

    public static class InventoryResponseTicket {
        public final UUID carpenterUUID;
        public final UUID playerUUID;
        public final Map<String, Long> items;
        public final long townBalance;
        public final BlockPos townHallPos;

        public InventoryResponseTicket(UUID carpenterUUID, UUID playerUUID, Map<String, Long> items,
                                       long townBalance, BlockPos townHallPos) {
            this.carpenterUUID = carpenterUUID;
            this.playerUUID    = playerUUID;
            this.items         = Collections.unmodifiableMap(new HashMap<>(items));
            this.townBalance   = townBalance;
            this.townHallPos   = townHallPos;
        }
    }

    public interface TicketListener {
        BlockPos getTownHallPos();
        void onCarpenterRequestPosted(InventoryRequestTicket ticket);
    }

    private static final Map<UUID, InventoryRequestTicket>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, InventoryResponseTicket> pendingResponses = new ConcurrentHashMap<>();
    private static final List<TicketListener>               listeners        = new ArrayList<>();

    public static void registerListener(TicketListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }
    public static void unregisterListener(TicketListener listener) { listeners.remove(listener); }

    public static void postRequest(UUID carpenterUUID, UUID playerUUID, BlockPos carpenterPos) {
        InventoryRequestTicket ticket = new InventoryRequestTicket(carpenterUUID, playerUUID, carpenterPos);
        pendingRequests.put(carpenterUUID, ticket);

        TicketListener closest     = null;
        double         closestDist = Double.MAX_VALUE;
        for (TicketListener listener : listeners) {
            double dist = carpenterPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) { closestDist = dist; closest = listener; }
        }
        if (closest != null) closest.onCarpenterRequestPosted(ticket);
    }

    public static void consumeRequest(UUID uuid) { pendingRequests.remove(uuid); }

    public static void postResponse(UUID carpenterUUID, UUID playerUUID, Map<String, Long> items,
                                    long townBalance, BlockPos townHallPos) {
        pendingResponses.put(carpenterUUID, new InventoryResponseTicket(
                carpenterUUID, playerUUID, items, townBalance, townHallPos));
    }
    public static boolean hasPendingResponse(UUID uuid)               { return pendingResponses.containsKey(uuid); }
    public static InventoryResponseTicket consumeResponse(UUID uuid)  { return pendingResponses.remove(uuid); }
}
