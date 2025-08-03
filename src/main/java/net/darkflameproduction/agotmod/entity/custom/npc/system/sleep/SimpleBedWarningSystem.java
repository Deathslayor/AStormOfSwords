package net.darkflameproduction.agotmod.entity.custom.npc.system.sleep;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleBedWarningSystem {
    // Static map to track home beds only
    private static final Map<BlockPos, HomeBedClaim> homeBedClaims = new ConcurrentHashMap<>();
    private static final long HOME_BED_WARNING_DURATION = 100; // 5 seconds (100 ticks)

    public static class HomeBedClaim {
        public final UUID npcUUID;
        public final long timestamp;
        public final String npcName; // Store the NPC's name
        public final Level level; // Store reference to level for entity lookup

        public HomeBedClaim(UUID npcUUID, String npcName, Level level) {
            this.npcUUID = npcUUID;
            this.npcName = npcName;
            this.level = level;
            this.timestamp = System.currentTimeMillis();
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - timestamp > HOME_BED_WARNING_DURATION * 50; // Convert ticks to ms
        }

        /**
         * Gets the current name of the NPC, either from stored name or by looking up the entity
         */
        public String getCurrentName() {
            if (npcName != null && !npcName.isEmpty()) {
                return npcName;
            }

            // Fallback: try to find the entity and get its current name
            if (level != null && !level.isClientSide()) {
                Entity entity = ((net.minecraft.server.level.ServerLevel) level).getEntity(npcUUID);
                if (entity instanceof Peasant_Entity peasant && peasant.hasCustomName()) {
                    return peasant.getCustomName().getString();
                }
            }

            return "Unknown"; // Fallback if name can't be determined
        }
    }

    // Broadcast that this bed is claimed as a home bed (permanent claim) - with name
    public static void broadcastHomeBedClaim(UUID npcUUID, BlockPos homeBedPos, String npcName, Level level) {
        if (homeBedPos != null) {
            homeBedClaims.put(homeBedPos, new HomeBedClaim(npcUUID, npcName, level));
        }
    }

    // Broadcast that this bed is claimed as a home bed (permanent claim) - backwards compatibility
    public static void broadcastHomeBedClaim(UUID npcUUID, BlockPos homeBedPos) {
        if (homeBedPos != null) {
            homeBedClaims.put(homeBedPos, new HomeBedClaim(npcUUID, null, null));
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
            if (excludeUUID != null && claim.npcUUID.equals(excludeUUID)) {
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

    /**
     * Get citizen names mapped by their UUIDs within a radius
     * This is used by the TownHall to collect family names for child spawning
     */
    public static Map<UUID, String> getCitizenNamesInRadius(BlockPos center, int radius) {
        cleanupExpiredClaims();
        Map<UUID, String> citizenNames = new HashMap<>();

        for (Map.Entry<BlockPos, HomeBedClaim> entry : homeBedClaims.entrySet()) {
            BlockPos claimedPos = entry.getKey();
            HomeBedClaim claim = entry.getValue();

            // Skip expired claims
            if (claim.isExpired()) {
                continue;
            }

            // Check if within radius
            double distanceSquared = center.distSqr(claimedPos);
            if (distanceSquared <= radius * radius) {
                String currentName = claim.getCurrentName();
                if (currentName != null && !currentName.isEmpty() && !currentName.equals("Unknown")) {
                    citizenNames.put(claim.npcUUID, currentName);
                }
            }
        }

        return citizenNames;
    }

    /**
     * Get all citizen names within a radius as a simple list (for easier processing)
     */
    public static List<String> getCitizenNamesListInRadius(BlockPos center, int radius) {
        Map<UUID, String> nameMap = getCitizenNamesInRadius(center, radius);
        return new ArrayList<>(nameMap.values());
    }

    /**
     * Get all unique last names within a radius
     */
    public static List<String> getCitizenLastNamesInRadius(BlockPos center, int radius) {
        Map<UUID, String> citizenNames = getCitizenNamesInRadius(center, radius);
        Set<String> lastNames = new HashSet<>();

        for (String fullName : citizenNames.values()) {
            if (fullName != null && !fullName.isEmpty()) {
                String lastName = extractLastName(fullName);
                if (!lastName.isEmpty() && !lastName.equals("Snow")) { // Filter out default/bastard names
                    lastNames.add(lastName);
                }
            }
        }

        return new ArrayList<>(lastNames);
    }

    /**
     * Helper method to extract last name from full name
     */
    private static String extractLastName(String fullName) {
        if (fullName == null || fullName.isEmpty()) return "";

        String[] parts = fullName.split(" ");
        if (parts.length > 1) {
            return parts[parts.length - 1];
        }
        return "";
    }

    /**
     * Update the name for an existing claim (when NPC name changes)
     */
    public static void updateClaimName(UUID npcUUID, String newName) {
        for (Map.Entry<BlockPos, HomeBedClaim> entry : homeBedClaims.entrySet()) {
            HomeBedClaim claim = entry.getValue();
            if (claim.npcUUID.equals(npcUUID)) {
                // Replace the claim with updated name
                homeBedClaims.put(entry.getKey(), new HomeBedClaim(npcUUID, newName, claim.level));
                break; // Assuming one home per NPC
            }
        }
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

    /**
     * Debug method to get all current claims (useful for testing)
     */
    public static Map<BlockPos, HomeBedClaim> getAllClaims() {
        cleanupExpiredClaims();
        return new HashMap<>(homeBedClaims);
    }

    /**
     * Get claim information for a specific bed position
     */
    public static HomeBedClaim getClaimForPosition(BlockPos bedPos) {
        cleanupExpiredClaims();
        return homeBedClaims.get(bedPos);
    }
}