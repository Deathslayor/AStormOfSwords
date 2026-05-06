package net.darkflameproduction.agotmod.entity.custom.npc.system.inventory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

public class FoodCollectionTicketSystem {

    public static class FoodRequestTicket {
        public final UUID peasantUUID;
        public final int amountNeeded;
        public final long createdDay;
        public final BlockPos peasantPos;

        public FoodRequestTicket(UUID peasantUUID, int amountNeeded, long createdDay, BlockPos peasantPos) {
            this.peasantUUID = peasantUUID;
            this.amountNeeded = amountNeeded;
            this.createdDay = createdDay;
            this.peasantPos = peasantPos;
        }
    }

    public static class FoodResponse {
        public final List<ItemStack> items;

        public FoodResponse(List<ItemStack> items) {
            this.items = Collections.unmodifiableList(new ArrayList<>(items));
        }

        public int getTotalCount() {
            return items.stream().mapToInt(ItemStack::getCount).sum();
        }
    }

    public interface FoodCollectionListener {
        BlockPos getTownHallPos();
        void onFoodRequestPosted(FoodRequestTicket ticket);
    }

    private static final Map<UUID, FoodRequestTicket> pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, FoodResponse>      pendingResponses = new ConcurrentHashMap<>();
    private static final List<FoodCollectionListener> listeners        = new ArrayList<>();

    public static void registerListener(FoodCollectionListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(FoodCollectionListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID peasantUUID, int amountNeeded, long createdDay, BlockPos peasantPos) {
        if (pendingRequests.containsKey(peasantUUID)) return;

        FoodRequestTicket ticket = new FoodRequestTicket(peasantUUID, amountNeeded, createdDay, peasantPos);
        pendingRequests.put(peasantUUID, ticket);

        FoodCollectionListener closest     = null;
        double                 closestDist = Double.MAX_VALUE;

        for (FoodCollectionListener listener : listeners) {
            double dist = peasantPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) {
            closest.onFoodRequestPosted(ticket);
        }
    }

    public static boolean hasPendingRequest(UUID peasantUUID) {
        return pendingRequests.containsKey(peasantUUID);
    }

    public static boolean hasPendingResponse(UUID peasantUUID) {
        return pendingResponses.containsKey(peasantUUID);
    }

    public static void postResponse(UUID peasantUUID, List<ItemStack> items) {
        pendingRequests.remove(peasantUUID);
        pendingResponses.put(peasantUUID, new FoodResponse(items));
    }

    public static FoodResponse consumeResponse(UUID peasantUUID) {
        return pendingResponses.remove(peasantUUID);
    }

    public static void cleanupStaleTickets(long currentDay) {
        pendingRequests.entrySet().removeIf(e -> currentDay - e.getValue().createdDay > 2);
    }
}