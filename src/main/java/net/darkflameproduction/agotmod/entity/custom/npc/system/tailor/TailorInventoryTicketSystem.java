package net.darkflameproduction.agotmod.entity.custom.npc.system.tailor;

import net.minecraft.core.BlockPos;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TailorInventoryTicketSystem {

    public static class InventoryRequestTicket {
        public final UUID tailorUUID;
        public final UUID playerUUID;
        public final BlockPos tailorPos;

        public InventoryRequestTicket(UUID tailorUUID, UUID playerUUID, BlockPos tailorPos) {
            this.tailorUUID = tailorUUID;
            this.playerUUID = playerUUID;
            this.tailorPos  = tailorPos;
        }
    }

    public static class InventoryResponseTicket {
        public final UUID tailorUUID;
        public final UUID playerUUID;
        public final Map<String, Long> items;
        public final long townBalance;
        public final BlockPos townHallPos;

        public InventoryResponseTicket(UUID tailorUUID, UUID playerUUID, Map<String, Long> items,
                                       long townBalance, BlockPos townHallPos) {
            this.tailorUUID  = tailorUUID;
            this.playerUUID  = playerUUID;
            this.items       = Collections.unmodifiableMap(new HashMap<>(items));
            this.townBalance = townBalance;
            this.townHallPos = townHallPos;
        }
    }

    public interface TicketListener {
        BlockPos getTownHallPos();
        void onTailorRequestPosted(InventoryRequestTicket ticket);
    }

    private static final Map<UUID, InventoryRequestTicket>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, InventoryResponseTicket> pendingResponses = new ConcurrentHashMap<>();
    private static final List<TicketListener>               listeners        = new ArrayList<>();

    public static void registerListener(TicketListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }
    public static void unregisterListener(TicketListener listener) { listeners.remove(listener); }

    public static void postRequest(UUID tailorUUID, UUID playerUUID, BlockPos tailorPos) {
        InventoryRequestTicket ticket = new InventoryRequestTicket(tailorUUID, playerUUID, tailorPos);
        pendingRequests.put(tailorUUID, ticket);

        TicketListener closest = null;
        double closestDist = Double.MAX_VALUE;
        for (TicketListener listener : listeners) {
            double dist = tailorPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) { closestDist = dist; closest = listener; }
        }
        if (closest != null) closest.onTailorRequestPosted(ticket);
    }

    public static void consumeRequest(UUID uuid)  { pendingRequests.remove(uuid); }
    public static void postResponse(UUID tailorUUID, UUID playerUUID, Map<String, Long> items,
                                    long townBalance, BlockPos townHallPos) {
        pendingResponses.put(tailorUUID, new InventoryResponseTicket(
                tailorUUID, playerUUID, items, townBalance, townHallPos));
    }
    public static boolean hasPendingResponse(UUID uuid) { return pendingResponses.containsKey(uuid); }
    public static InventoryResponseTicket consumeResponse(UUID uuid) { return pendingResponses.remove(uuid); }
}