package net.darkflameproduction.agotmod.entity.custom.npc.system.butcher;

import net.minecraft.core.BlockPos;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ButcherInventoryTicketSystem {

    public static class InventoryRequestTicket {
        public final UUID butcherUUID;
        public final UUID playerUUID;
        public final BlockPos butcherPos;

        public InventoryRequestTicket(UUID butcherUUID, UUID playerUUID, BlockPos butcherPos) {
            this.butcherUUID = butcherUUID;
            this.playerUUID  = playerUUID;
            this.butcherPos  = butcherPos;
        }
    }

    public static class InventoryResponseTicket {
        public final UUID butcherUUID;
        public final UUID playerUUID;
        public final Map<String, Long> items;
        public final long townBalance;
        public final BlockPos townHallPos;

        public InventoryResponseTicket(UUID butcherUUID, UUID playerUUID, Map<String, Long> items,
                                       long townBalance, BlockPos townHallPos) {
            this.butcherUUID = butcherUUID;
            this.playerUUID  = playerUUID;
            this.items       = Collections.unmodifiableMap(new HashMap<>(items));
            this.townBalance = townBalance;
            this.townHallPos = townHallPos;
        }
    }

    public interface TicketListener {
        BlockPos getTownHallPos();
        void onButcherRequestPosted(InventoryRequestTicket ticket);
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

    public static void postRequest(UUID butcherUUID, UUID playerUUID, BlockPos butcherPos) {
        InventoryRequestTicket ticket = new InventoryRequestTicket(butcherUUID, playerUUID, butcherPos);
        pendingRequests.put(butcherUUID, ticket);

        TicketListener closest     = null;
        double         closestDist = Double.MAX_VALUE;

        for (TicketListener listener : listeners) {
            double dist = butcherPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) {
            closest.onButcherRequestPosted(ticket);
        }
    }

    public static void consumeRequest(UUID butcherUUID) {
        pendingRequests.remove(butcherUUID);
    }

    public static void postResponse(UUID butcherUUID, UUID playerUUID, Map<String, Long> items,
                                    long townBalance, BlockPos townHallPos) {
        pendingResponses.put(butcherUUID, new InventoryResponseTicket(
                butcherUUID, playerUUID, items, townBalance, townHallPos));
    }

    public static boolean hasPendingResponse(UUID butcherUUID) {
        return pendingResponses.containsKey(butcherUUID);
    }

    public static InventoryResponseTicket consumeResponse(UUID butcherUUID) {
        return pendingResponses.remove(butcherUUID);
    }
}
