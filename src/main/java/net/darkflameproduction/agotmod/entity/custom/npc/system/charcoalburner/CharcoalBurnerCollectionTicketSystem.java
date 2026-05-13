package net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CharcoalBurnerCollectionTicketSystem {

    public static class CharcoalBurnerRequestTicket {
        public final UUID charcoalBurnerUUID;
        public final int logsNeeded;
        public final long createdDay;
        public final BlockPos burnerPos;

        public CharcoalBurnerRequestTicket(UUID charcoalBurnerUUID, int logsNeeded,
                                           long createdDay, BlockPos burnerPos) {
            this.charcoalBurnerUUID = charcoalBurnerUUID;
            this.logsNeeded         = logsNeeded;
            this.createdDay         = createdDay;
            this.burnerPos          = burnerPos;
        }
    }

    public static class CharcoalBurnerResponse {
        public final List<ItemStack> items;

        public CharcoalBurnerResponse(List<ItemStack> items) {
            this.items = Collections.unmodifiableList(new ArrayList<>(items));
        }
    }

    public interface CharcoalBurnerCollectionListener {
        BlockPos getTownHallPos();
        void onCharcoalBurnerRequestPosted(CharcoalBurnerRequestTicket ticket);
    }

    private static final Map<UUID, CharcoalBurnerRequestTicket>  pendingRequests  = new ConcurrentHashMap<>();
    private static final Map<UUID, CharcoalBurnerResponse>       pendingResponses = new ConcurrentHashMap<>();
    private static final List<CharcoalBurnerCollectionListener>  listeners        = new ArrayList<>();

    public static void registerListener(CharcoalBurnerCollectionListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(CharcoalBurnerCollectionListener listener) {
        listeners.remove(listener);
    }

    public static void postRequest(UUID burnerUUID, int logsNeeded, long createdDay, BlockPos burnerPos) {
        if (pendingRequests.containsKey(burnerUUID)) return;

        CharcoalBurnerRequestTicket ticket = new CharcoalBurnerRequestTicket(
                burnerUUID, logsNeeded, createdDay, burnerPos);
        pendingRequests.put(burnerUUID, ticket);

        CharcoalBurnerCollectionListener closest = null;
        double closestDist = Double.MAX_VALUE;

        for (CharcoalBurnerCollectionListener listener : listeners) {
            double dist = burnerPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) {
            closest.onCharcoalBurnerRequestPosted(ticket);
        }
    }

    public static boolean hasPendingRequest(UUID burnerUUID) {
        return pendingRequests.containsKey(burnerUUID);
    }

    public static boolean hasPendingResponse(UUID burnerUUID) {
        return pendingResponses.containsKey(burnerUUID);
    }

    public static void postResponse(UUID burnerUUID, List<ItemStack> items) {
        pendingRequests.remove(burnerUUID);
        pendingResponses.put(burnerUUID, new CharcoalBurnerResponse(items));
    }

    public static CharcoalBurnerResponse consumeResponse(UUID burnerUUID) {
        return pendingResponses.remove(burnerUUID);
    }

    public static void cleanupStaleTickets(long currentDay) {
        pendingRequests.entrySet().removeIf(e -> currentDay - e.getValue().createdDay > 2);
    }
}