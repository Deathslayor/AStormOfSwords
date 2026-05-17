package net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CarpenterCollectionTicketSystem {

    public static class CarpenterRequest {
        public final UUID carpenterUUID;
        public final long day;
        public final BlockPos carpenterPos;

        public CarpenterRequest(UUID carpenterUUID, long day, BlockPos carpenterPos) {
            this.carpenterUUID = carpenterUUID;
            this.day           = day;
            this.carpenterPos  = carpenterPos;
        }
    }

    public static class CarpenterResponse {
        public final List<ItemStack> items;
        public CarpenterResponse(List<ItemStack> items) {
            this.items = Collections.unmodifiableList(new ArrayList<>(items));
        }
    }

    public interface CarpenterCollectionListener {
        BlockPos getTownHallPos();
        void onCarpenterCollectionRequestPosted(CarpenterRequest request);
    }

    private static final Map<UUID, CarpenterRequest>       pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, CarpenterResponse>      pendingResponses = new ConcurrentHashMap<>();
    private static final List<CarpenterCollectionListener> listeners        = new ArrayList<>();
    private static final Map<UUID, Long>                   staleTicketDays  = new ConcurrentHashMap<>();

    public static void registerListener(CarpenterCollectionListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(CarpenterCollectionListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID carpenterUUID, long day, BlockPos carpenterPos) {
        CarpenterRequest request = new CarpenterRequest(carpenterUUID, day, carpenterPos);
        pendingRequests.put(carpenterUUID, request);
        staleTicketDays.put(carpenterUUID, day);

        CarpenterCollectionListener closest     = null;
        double                      closestDist = Double.MAX_VALUE;
        for (CarpenterCollectionListener listener : listeners) {
            double dist = carpenterPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) { closestDist = dist; closest = listener; }
        }
        if (closest != null) closest.onCarpenterCollectionRequestPosted(request);
    }

    public static boolean hasPendingRequest(UUID uuid)  { return pendingRequests.containsKey(uuid); }
    public static void consumeRequest(UUID uuid)         { pendingRequests.remove(uuid); }

    public static void postResponse(UUID uuid, List<ItemStack> items) {
        pendingResponses.put(uuid, new CarpenterResponse(items));
    }
    public static boolean hasPendingResponse(UUID uuid)        { return pendingResponses.containsKey(uuid); }
    public static CarpenterResponse consumeResponse(UUID uuid) { return pendingResponses.remove(uuid); }

    public static void cleanupStaleTickets(long currentDay) {
        staleTicketDays.entrySet().removeIf(entry -> {
            if (entry.getValue() < currentDay) {
                pendingRequests.remove(entry.getKey());
                pendingResponses.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }
}