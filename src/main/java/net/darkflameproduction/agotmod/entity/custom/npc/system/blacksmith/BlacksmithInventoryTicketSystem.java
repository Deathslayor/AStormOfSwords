package net.darkflameproduction.agotmod.entity.custom.npc.system.blacksmith;

import net.minecraft.core.BlockPos;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BlacksmithInventoryTicketSystem {

    public static class InventoryRequestTicket {
        public final UUID blacksmithUUID;
        public final UUID playerUUID;
        public final BlockPos blacksmithPos;

        public InventoryRequestTicket(UUID blacksmithUUID, UUID playerUUID, BlockPos blacksmithPos) {
            this.blacksmithUUID = blacksmithUUID;
            this.playerUUID     = playerUUID;
            this.blacksmithPos  = blacksmithPos;
        }
    }

    public static class InventoryResponseTicket {
        public final UUID blacksmithUUID;
        public final UUID playerUUID;
        public final Map<String, Long> items;
        public final long townBalance;
        public final BlockPos townHallPos;

        public InventoryResponseTicket(UUID blacksmithUUID, UUID playerUUID, Map<String, Long> items,
                                       long townBalance, BlockPos townHallPos) {
            this.blacksmithUUID = blacksmithUUID;
            this.playerUUID     = playerUUID;
            this.items          = Collections.unmodifiableMap(new HashMap<>(items));
            this.townBalance    = townBalance;
            this.townHallPos    = townHallPos;
        }
    }

    public interface TicketListener {
        BlockPos getTownHallPos();
        void onBlacksmithRequestPosted(InventoryRequestTicket ticket);
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

    public static void postRequest(UUID blacksmithUUID, UUID playerUUID, BlockPos blacksmithPos) {
        InventoryRequestTicket ticket = new InventoryRequestTicket(blacksmithUUID, playerUUID, blacksmithPos);
        pendingRequests.put(blacksmithUUID, ticket);

        TicketListener closest     = null;
        double         closestDist = Double.MAX_VALUE;
        for (TicketListener listener : listeners) {
            double dist = blacksmithPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) { closestDist = dist; closest = listener; }
        }
        if (closest != null) closest.onBlacksmithRequestPosted(ticket);
    }

    public static void consumeRequest(UUID blacksmithUUID) {
        pendingRequests.remove(blacksmithUUID);
    }

    public static void postResponse(UUID blacksmithUUID, UUID playerUUID, Map<String, Long> items,
                                    long townBalance, BlockPos townHallPos) {
        pendingResponses.put(blacksmithUUID, new InventoryResponseTicket(
                blacksmithUUID, playerUUID, items, townBalance, townHallPos));
    }

    public static boolean hasPendingResponse(UUID blacksmithUUID) {
        return pendingResponses.containsKey(blacksmithUUID);
    }

    public static InventoryResponseTicket consumeResponse(UUID blacksmithUUID) {
        return pendingResponses.remove(blacksmithUUID);
    }
}