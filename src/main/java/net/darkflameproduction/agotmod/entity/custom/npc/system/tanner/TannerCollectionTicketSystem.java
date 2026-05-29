package net.darkflameproduction.agotmod.entity.custom.npc.system.tanner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TannerCollectionTicketSystem {

    public static class TannerRequest {
        public final UUID tannerUUID;
        public final long day;
        public final BlockPos tannerPos;

        public TannerRequest(UUID tannerUUID, long day, BlockPos tannerPos) {
            this.tannerUUID = tannerUUID;
            this.day        = day;
            this.tannerPos  = tannerPos;
        }
    }

    public static class TannerResponse {
        public final List<ItemStack> items;
        public TannerResponse(List<ItemStack> items) {
            this.items = Collections.unmodifiableList(new ArrayList<>(items));
        }
    }

    public interface TannerCollectionListener {
        BlockPos getTownHallPos();
        void onTannerCollectionRequestPosted(TannerRequest request);
    }

    private static final Map<UUID, TannerRequest>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, TannerResponse> pendingResponses = new ConcurrentHashMap<>();
    private static final List<TannerCollectionListener> listeners   = new ArrayList<>();
    private static final Map<UUID, Long> staleTicketDays            = new ConcurrentHashMap<>();

    public static void registerListener(TannerCollectionListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(TannerCollectionListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID tannerUUID, long day, BlockPos tannerPos) {
        TannerRequest request = new TannerRequest(tannerUUID, day, tannerPos);
        pendingRequests.put(tannerUUID, request);
        staleTicketDays.put(tannerUUID, day);

        TannerCollectionListener closest     = null;
        double                   closestDist = Double.MAX_VALUE;

        for (TannerCollectionListener listener : listeners) {
            double dist = tannerPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) closest.onTannerCollectionRequestPosted(request);
    }

    public static boolean hasPendingRequest(UUID tannerUUID) {
        return pendingRequests.containsKey(tannerUUID);
    }

    public static void consumeRequest(UUID tannerUUID) {
        pendingRequests.remove(tannerUUID);
    }

    public static void postResponse(UUID tannerUUID, List<ItemStack> items) {
        pendingResponses.put(tannerUUID, new TannerResponse(items));
    }

    public static boolean hasPendingResponse(UUID tannerUUID) {
        return pendingResponses.containsKey(tannerUUID);
    }

    public static TannerResponse consumeResponse(UUID tannerUUID) {
        return pendingResponses.remove(tannerUUID);
    }

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
