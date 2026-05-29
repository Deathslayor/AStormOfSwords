package net.darkflameproduction.agotmod.entity.custom.npc.system.tanner;

import net.minecraft.core.BlockPos;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TannerInventoryTicketSystem {

    public static class InventoryRequestTicket {
        public final UUID tannerUUID;
        public final UUID playerUUID;
        public final BlockPos tannerPos;

        public InventoryRequestTicket(UUID tannerUUID, UUID playerUUID, BlockPos tannerPos) {
            this.tannerUUID = tannerUUID;
            this.playerUUID = playerUUID;
            this.tannerPos  = tannerPos;
        }
    }

    public static class InventoryResponseTicket {
        public final UUID tannerUUID;
        public final UUID playerUUID;
        public final Map<String, Long> items;
        public final long townBalance;
        public final BlockPos townHallPos;

        public InventoryResponseTicket(UUID tannerUUID, UUID playerUUID, Map<String, Long> items,
                                       long townBalance, BlockPos townHallPos) {
            this.tannerUUID  = tannerUUID;
            this.playerUUID  = playerUUID;
            this.items       = Collections.unmodifiableMap(new HashMap<>(items));
            this.townBalance = townBalance;
            this.townHallPos = townHallPos;
        }
    }

    public interface TicketListener {
        BlockPos getTownHallPos();
        void onTannerRequestPosted(InventoryRequestTicket ticket);
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

    public static void postRequest(UUID tannerUUID, UUID playerUUID, BlockPos tannerPos) {
        InventoryRequestTicket ticket = new InventoryRequestTicket(tannerUUID, playerUUID, tannerPos);
        pendingRequests.put(tannerUUID, ticket);

        TicketListener closest     = null;
        double         closestDist = Double.MAX_VALUE;

        for (TicketListener listener : listeners) {
            double dist = tannerPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) closest.onTannerRequestPosted(ticket);
    }

    public static void consumeRequest(UUID tannerUUID) {
        pendingRequests.remove(tannerUUID);
    }

    public static void postResponse(UUID tannerUUID, UUID playerUUID, Map<String, Long> items,
                                    long townBalance, BlockPos townHallPos) {
        pendingResponses.put(tannerUUID, new InventoryResponseTicket(
                tannerUUID, playerUUID, items, townBalance, townHallPos));
    }

    public static boolean hasPendingResponse(UUID tannerUUID) {
        return pendingResponses.containsKey(tannerUUID);
    }

    public static InventoryResponseTicket consumeResponse(UUID tannerUUID) {
        return pendingResponses.remove(tannerUUID);
    }
}
