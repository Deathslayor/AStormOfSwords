package net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class AnimalHerderCollectionTicketSystem {

    public interface AnimalHerderCollectionListener {
        BlockPos getTownHallPos();
        void onAnimalHerderRequestPosted(AnimalHerderRequestTicket ticket);
    }

    public static class AnimalHerderRequestTicket {
        public final UUID peasantUUID;
        public final String jobType;
        public final int amountNeeded;
        public final long createdDay;
        public final BlockPos peasantPos;

        public AnimalHerderRequestTicket(UUID peasantUUID, String jobType, int amountNeeded, long createdDay, BlockPos peasantPos) {
            this.peasantUUID  = peasantUUID;
            this.jobType      = jobType;
            this.amountNeeded = amountNeeded;
            this.createdDay   = createdDay;
            this.peasantPos   = peasantPos;
        }
    }

    public static class AnimalHerderResponse {
        public final List<ItemStack> items;
        public AnimalHerderResponse(List<ItemStack> items) { this.items = items; }
    }

    private static final Map<UUID, AnimalHerderRequestTicket> pendingRequests  = new HashMap<>();
    private static final Map<UUID, AnimalHerderResponse>      pendingResponses = new HashMap<>();
    private static final List<AnimalHerderCollectionListener>  listeners        = new ArrayList<>();

    public static void registerListener(AnimalHerderCollectionListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(AnimalHerderCollectionListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID peasantUUID, String jobType, int amountNeeded, long createdDay, BlockPos peasantPos) {
        if (pendingRequests.containsKey(peasantUUID)) return;

        AnimalHerderRequestTicket ticket = new AnimalHerderRequestTicket(peasantUUID, jobType, amountNeeded, createdDay, peasantPos);
        pendingRequests.put(peasantUUID, ticket);

        AnimalHerderCollectionListener closest     = null;
        double                          closestDist = Double.MAX_VALUE;

        for (AnimalHerderCollectionListener listener : listeners) {
            double dist = peasantPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) closest.onAnimalHerderRequestPosted(ticket);
    }

    public static void postResponse(UUID peasantUUID, List<ItemStack> items) {
        pendingRequests.remove(peasantUUID);
        pendingResponses.put(peasantUUID, new AnimalHerderResponse(items));
    }

    public static boolean hasPendingRequest(UUID peasantUUID) {
        return pendingRequests.containsKey(peasantUUID);
    }

    public static boolean hasPendingResponse(UUID peasantUUID) {
        return pendingResponses.containsKey(peasantUUID);
    }

    public static AnimalHerderResponse consumeResponse(UUID peasantUUID) {
        return pendingResponses.remove(peasantUUID);
    }

    public static void cleanupStaleTickets(long currentDay) {
        pendingRequests.entrySet().removeIf(entry -> currentDay - entry.getValue().createdDay > 1);
    }
}
