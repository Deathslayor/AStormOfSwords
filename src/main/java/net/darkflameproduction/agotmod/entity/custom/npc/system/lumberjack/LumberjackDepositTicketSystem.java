package net.darkflameproduction.agotmod.entity.custom.npc.system.lumberjack;

import net.minecraft.core.BlockPos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LumberjackDepositTicketSystem {

    public static class DepositTicket {
        public final UUID lumberjackUUID;
        public final BlockPos lumberjackPos;
        public final Map<String, Long> items;

        public DepositTicket(UUID lumberjackUUID, BlockPos lumberjackPos, Map<String, Long> items) {
            this.lumberjackUUID = lumberjackUUID;
            this.lumberjackPos  = lumberjackPos;
            this.items          = Collections.unmodifiableMap(new HashMap<>(items));
        }
    }

    public interface DepositListener {
        BlockPos getTownHallPos();
        void onLumberjackDepositPosted(DepositTicket ticket);
    }

    private static final Map<UUID, DepositTicket> pendingDeposits = new ConcurrentHashMap<>();
    private static final List<DepositListener>    listeners       = new ArrayList<>();

    public static void registerListener(DepositListener listener) {
        if (!listeners.contains(listener)) listeners.add(listener);
    }

    public static void unregisterListener(DepositListener listener) {
        listeners.remove(listener);
    }

    public static void postDeposit(UUID lumberjackUUID, BlockPos lumberjackPos,
                                   Map<String, Long> items) {
        DepositTicket ticket = new DepositTicket(lumberjackUUID, lumberjackPos, items);
        pendingDeposits.put(lumberjackUUID, ticket);

        DepositListener closest     = null;
        double          closestDist = Double.MAX_VALUE;
        for (DepositListener listener : listeners) {
            double dist = lumberjackPos.distSqr(listener.getTownHallPos());
            if (dist < closestDist) { closestDist = dist; closest = listener; }
        }
        if (closest != null) closest.onLumberjackDepositPosted(ticket);
    }

    public static void consumeDeposit(UUID lumberjackUUID) {
        pendingDeposits.remove(lumberjackUUID);
    }

    public static void cleanupStaleTickets(long currentDay) {
        pendingDeposits.clear();
    }
}
