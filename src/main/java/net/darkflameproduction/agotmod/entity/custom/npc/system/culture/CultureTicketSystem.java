package net.darkflameproduction.agotmod.entity.custom.npc.system.culture;

import net.minecraft.core.BlockPos;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Town halls post culture assignments here.
 * Peasants poll this on tick to receive their culture.
 */
public class CultureTicketSystem {

    public static class CultureTicket {
        public final UUID peasantUUID;
        public final Culture culture;
        public final BlockPos townHallPos;

        public CultureTicket(UUID peasantUUID, Culture culture, BlockPos townHallPos) {
            this.peasantUUID = peasantUUID;
            this.culture     = culture;
            this.townHallPos = townHallPos;
        }
    }

    private static final Map<UUID, CultureTicket> pendingTickets = new ConcurrentHashMap<>();

    public static void postTicket(UUID peasantUUID, Culture culture, BlockPos townHallPos) {
        pendingTickets.put(peasantUUID, new CultureTicket(peasantUUID, culture, townHallPos));
    }

    public static boolean hasPendingTicket(UUID peasantUUID) {
        return pendingTickets.containsKey(peasantUUID);
    }

    public static CultureTicket consumeTicket(UUID peasantUUID) {
        return pendingTickets.remove(peasantUUID);
    }

    public static void clearAll() {
        pendingTickets.clear();
    }
}