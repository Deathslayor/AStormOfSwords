package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.core.BlockPos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBedWarningSystem {
    // Static map to track home beds only
    private static final Map<BlockPos, HomeBedClaim> homeBedClaims = new ConcurrentHashMap<>();
    private static final long HOME_BED_WARNING_DURATION = 100; // 5 seconds (100 ticks)

    public static class HomeBedClaim {
        public final UUID npcUUID;
        public final long timestamp;

        public HomeBedClaim(UUID npcUUID) {
            this.npcUUID = npcUUID;
            this.timestamp = System.currentTimeMillis();
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - timestamp > HOME_BED_WARNING_DURATION * 50; // Convert ticks to ms
        }
    }

    // Broadcast that this bed is claimed as a home bed (permanent claim)
    public static void broadcastHomeBedClaim(UUID npcUUID, BlockPos homeBedPos) {
        if (homeBedPos != null) {
            homeBedClaims.put(homeBedPos, new HomeBedClaim(npcUUID));
        }
    }

    // Check if a bed is claimed as someone's home bed
    public static boolean isHomeBedClaimed(BlockPos bedPos, UUID excludeUUID) {
        cleanupExpiredClaims();

        HomeBedClaim claim = homeBedClaims.get(bedPos);
        if (claim == null) {
            return false;
        }

        // Not claimed if it's our own claim
        if (claim.npcUUID.equals(excludeUUID)) {
            return false;
        }

        return !claim.isExpired();
    }

    // Get all home bed claims within a radius (for bed searching NPCs to avoid)
    public static Set<BlockPos> getHomeBedClaimsInRadius(BlockPos center, int radius, UUID excludeUUID) {
        cleanupExpiredClaims();
        Set<BlockPos> claimedBeds = new HashSet<>();

        for (Map.Entry<BlockPos, HomeBedClaim> entry : homeBedClaims.entrySet()) {
            BlockPos claimedPos = entry.getKey();
            HomeBedClaim claim = entry.getValue();

            // Skip our own claims
            if (claim.npcUUID.equals(excludeUUID)) {
                continue;
            }

            // Skip expired claims
            if (claim.isExpired()) {
                continue;
            }

            // Check if within radius
            double distanceSquared = center.distSqr(claimedPos);
            if (distanceSquared <= radius * radius) {
                claimedBeds.add(claimedPos);
            }
        }

        return claimedBeds;
    }

    // Remove home bed claim when NPC loses home or is removed
    public static void removeHomeBedClaim(UUID npcUUID) {
        homeBedClaims.entrySet().removeIf(entry -> entry.getValue().npcUUID.equals(npcUUID));
    }

    // Remove specific home bed claim
    public static void removeHomeBedClaimForPosition(UUID npcUUID, BlockPos bedPos) {
        HomeBedClaim claim = homeBedClaims.get(bedPos);
        if (claim != null && claim.npcUUID.equals(npcUUID)) {
            homeBedClaims.remove(bedPos);
        }
    }

    // Clean up expired claims
    private static void cleanupExpiredClaims() {
        homeBedClaims.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }
}