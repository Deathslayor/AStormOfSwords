package net.darkflameproduction.agotmod.entity.custom.npc.system.trader;

import net.minecraft.core.BlockPos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TraderInventoryTicketSystem {

    public static class InventoryRequestTicket {
        public final UUID traderUUID;
        public final UUID playerUUID;
        public final BlockPos traderPos;

        public InventoryRequestTicket(UUID traderUUID, UUID playerUUID, BlockPos traderPos) {
            this.traderUUID = traderUUID;
            this.playerUUID = playerUUID;
            this.traderPos  = traderPos;
        }
    }

    public static class InventoryResponseTicket {
        public final UUID traderUUID;
        public final UUID playerUUID;
        public final Map<String, Long> items;
        public final long townBalance;
        public final BlockPos townHallPos;

        public InventoryResponseTicket(UUID traderUUID, UUID playerUUID, Map<String, Long> items,
                                       long townBalance, BlockPos townHallPos) {
            this.traderUUID  = traderUUID;
            this.playerUUID  = playerUUID;
            this.items       = Collections.unmodifiableMap(new HashMap<>(items));
            this.townBalance = townBalance;
            this.townHallPos = townHallPos;
        }
    }

    public interface TicketListener {
        BlockPos getTownHallPos();
        void onTraderRequestPosted(InventoryRequestTicket ticket);
    }

    private static final Map<UUID, InventoryRequestTicket>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, InventoryResponseTicket> pendingResponses = new ConcurrentHashMap<>();
    private static final List<TicketListener>               listeners        = new ArrayList<>();

    public static void registerListener(TicketListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }
    public static void unregisterListener(TicketListener listener) { listeners.remove(listener); }

    public static void postRequest(UUID traderUUID, UUID playerUUID, BlockPos traderPos) {
        InventoryRequestTicket ticket = new InventoryRequestTicket(traderUUID, playerUUID, traderPos);
        pendingRequests.put(traderUUID, ticket);

        TicketListener closest     = null;
        double         closestDist = Double.MAX_VALUE;
        for (TicketListener listener : listeners) {
            double dist = traderPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) { closestDist = dist; closest = listener; }
        }
        if (closest != null) closest.onTraderRequestPosted(ticket);
    }

    public static void consumeRequest(UUID uuid) { pendingRequests.remove(uuid); }

    public static void postResponse(UUID traderUUID, UUID playerUUID, Map<String, Long> items,
                                    long townBalance, BlockPos townHallPos) {
        pendingResponses.put(traderUUID, new InventoryResponseTicket(
                traderUUID, playerUUID, items, townBalance, townHallPos));
    }
    public static boolean hasPendingResponse(UUID uuid)              { return pendingResponses.containsKey(uuid); }
    public static InventoryResponseTicket consumeResponse(UUID uuid) { return pendingResponses.remove(uuid); }
}