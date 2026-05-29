package net.darkflameproduction.agotmod.entity.custom.npc.system.tailor;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TailorCollectionTicketSystem {

    public static class TailorRequest {
        public final UUID tailorUUID;
        public final long day;
        public final BlockPos tailorPos;

        public TailorRequest(UUID tailorUUID, long day, BlockPos tailorPos) {
            this.tailorUUID = tailorUUID;
            this.day        = day;
            this.tailorPos  = tailorPos;
        }
    }

    public static class TailorResponse {
        public final List<ItemStack> items;
        public TailorResponse(List<ItemStack> items) {
            this.items = Collections.unmodifiableList(new ArrayList<>(items));
        }
    }

    public interface TailorCollectionListener {
        BlockPos getTownHallPos();
        void onTailorCollectionRequestPosted(TailorRequest request);
    }

    private static final Map<UUID, TailorRequest>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, TailorResponse> pendingResponses = new ConcurrentHashMap<>();
    private static final List<TailorCollectionListener> listeners   = new ArrayList<>();
    private static final Map<UUID, Long> staleTicketDays            = new ConcurrentHashMap<>();

    public static void registerListener(TailorCollectionListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(TailorCollectionListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID tailorUUID, long day, BlockPos tailorPos) {
        TailorRequest request = new TailorRequest(tailorUUID, day, tailorPos);
        pendingRequests.put(tailorUUID, request);
        staleTicketDays.put(tailorUUID, day);

        TailorCollectionListener closest     = null;
        double                   closestDist = Double.MAX_VALUE;
        for (TailorCollectionListener listener : listeners) {
            double dist = tailorPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) { closestDist = dist; closest = listener; }
        }
        if (closest != null) closest.onTailorCollectionRequestPosted(request);
    }

    public static boolean hasPendingRequest(UUID uuid)  { return pendingRequests.containsKey(uuid); }
    public static void consumeRequest(UUID uuid)         { pendingRequests.remove(uuid); }
    public static void postResponse(UUID uuid, List<ItemStack> items) {
        pendingResponses.put(uuid, new TailorResponse(items));
    }
    public static boolean hasPendingResponse(UUID uuid) { return pendingResponses.containsKey(uuid); }
    public static TailorResponse consumeResponse(UUID uuid) { return pendingResponses.remove(uuid); }

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
