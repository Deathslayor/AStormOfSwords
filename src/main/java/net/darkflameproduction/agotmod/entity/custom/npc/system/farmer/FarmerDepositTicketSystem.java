package net.darkflameproduction.agotmod.entity.custom.npc.system.farmer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.core.BlockPos;

public class FarmerDepositTicketSystem {

    public static class DepositTicket {
        public final UUID farmerUUID;
        public final Map<String, Integer> items;
        public final long createdDay;
        public final BlockPos farmerPos;

        public DepositTicket(UUID farmerUUID, Map<String, Integer> items, long createdDay, BlockPos farmerPos) {
            this.farmerUUID = farmerUUID;
            this.items = Collections.unmodifiableMap(new HashMap<>(items));
            this.createdDay = createdDay;
            this.farmerPos = farmerPos;
        }
    }

    public interface DepositListener {
        BlockPos getTownHallPos();
        void onDepositPosted(DepositTicket ticket);
    }

    private static final Map<UUID, DepositTicket> pendingDeposits      = new ConcurrentHashMap<>();
    private static final Map<UUID, DepositTicket> pendingConfirmations = new ConcurrentHashMap<>();
    private static final List<DepositListener>    listeners            = new ArrayList<>();

    public static void registerListener(DepositListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(DepositListener listener) {
        listeners.remove(listener);
    }

    public static void postDeposit(UUID farmerUUID, Map<String, Integer> items, long createdDay, BlockPos farmerPos) {
        if (pendingDeposits.containsKey(farmerUUID)) return;

        DepositTicket ticket = new DepositTicket(farmerUUID, items, createdDay, farmerPos);
        pendingDeposits.put(farmerUUID, ticket);

        // Find the closest town hall listener
        DepositListener closest     = null;
        double          closestDist = Double.MAX_VALUE;

        for (DepositListener listener : listeners) {
            double dist = farmerPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) {
                closestDist = dist;
                closest     = listener;
            }
        }

        if (closest != null) {
            closest.onDepositPosted(ticket);
        }
    }

    public static boolean hasPendingDeposit(UUID farmerUUID) {
        return pendingDeposits.containsKey(farmerUUID);
    }

    public static boolean hasPendingConfirmation(UUID farmerUUID) {
        return pendingConfirmations.containsKey(farmerUUID);
    }

    public static Map<UUID, DepositTicket> getAllPendingDeposits() {
        return Collections.unmodifiableMap(pendingDeposits);
    }

    public static void confirmDeposit(UUID farmerUUID) {
        DepositTicket ticket = pendingDeposits.remove(farmerUUID);
        if (ticket != null) {
            pendingConfirmations.put(farmerUUID, ticket);
        }
    }

    public static DepositTicket getConfirmation(UUID farmerUUID) {
        return pendingConfirmations.get(farmerUUID);
    }

    public static void consumeConfirmation(UUID farmerUUID) {
        pendingConfirmations.remove(farmerUUID);
    }

    public static void cleanupStaleTickets(long currentDay) {
        pendingDeposits.entrySet().removeIf(e -> currentDay - e.getValue().createdDay > 2);
        pendingConfirmations.entrySet().removeIf(e -> currentDay - e.getValue().createdDay > 2);
    }
}
