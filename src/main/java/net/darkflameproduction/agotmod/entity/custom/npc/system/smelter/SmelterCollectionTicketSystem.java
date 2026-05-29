package net.darkflameproduction.agotmod.entity.custom.npc.system.smelter;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SmelterCollectionTicketSystem {

    public static class SmelterRequestTicket {
        public final UUID smelterUUID;
        public final int coalNeeded;
        public final long createdDay;
        public final BlockPos smelterPos;

        public SmelterRequestTicket(UUID smelterUUID, int coalNeeded, long createdDay, BlockPos smelterPos) {
            this.smelterUUID = smelterUUID;
            this.coalNeeded = coalNeeded;
            this.createdDay = createdDay;
            this.smelterPos = smelterPos;
        }
    }

    public static class SmelterResponse {
        public final List<ItemStack> items;

        public SmelterResponse(List<ItemStack> items) {
            this.items = Collections.unmodifiableList(new ArrayList<>(items));
        }

        public int getTotalCount() {
            return items.stream().mapToInt(ItemStack::getCount).sum();
        }
    }

    public interface SmelterCollectionListener {
        BlockPos getTownHallPos();
        void onSmelterRequestPosted(SmelterRequestTicket ticket);
    }

    private static final Map<UUID, SmelterRequestTicket>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, SmelterResponse>       pendingResponses = new ConcurrentHashMap<>();
    private static final List<SmelterCollectionListener>  listeners        = new ArrayList<>();

    public static void registerListener(SmelterCollectionListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(SmelterCollectionListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID smelterUUID, int coalNeeded, long createdDay, BlockPos smelterPos) {
        if (pendingRequests.containsKey(smelterUUID)) return;

        SmelterRequestTicket ticket = new SmelterRequestTicket(smelterUUID, coalNeeded, createdDay, smelterPos);
        pendingRequests.put(smelterUUID, ticket);

        SmelterCollectionListener closest = null;
        double closestDist = Double.MAX_VALUE;

        for (SmelterCollectionListener listener : listeners) {
            double dist = smelterPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest = listener;
            }
        }

        if (closest != null) {
            closest.onSmelterRequestPosted(ticket);
        }
    }

    public static boolean hasPendingRequest(UUID smelterUUID) {
        return pendingRequests.containsKey(smelterUUID);
    }

    public static boolean hasPendingResponse(UUID smelterUUID) {
        return pendingResponses.containsKey(smelterUUID);
    }

    public static void postResponse(UUID smelterUUID, List<ItemStack> items) {
        pendingRequests.remove(smelterUUID);
        pendingResponses.put(smelterUUID, new SmelterResponse(items));
    }

    public static SmelterResponse consumeResponse(UUID smelterUUID) {
        return pendingResponses.remove(smelterUUID);
    }

    public static void cleanupStaleTickets(long currentDay) {
        pendingRequests.entrySet().removeIf(e -> currentDay - e.getValue().createdDay > 2);
    }
}
