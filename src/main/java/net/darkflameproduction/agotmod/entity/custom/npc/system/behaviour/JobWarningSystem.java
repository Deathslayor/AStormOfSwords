package net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour;

import net.minecraft.core.BlockPos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JobWarningSystem {
    // Static maps to track job block warnings across all NPCs
    private static final Map<BlockPos, JobBlockWarning> activeWarnings = new ConcurrentHashMap<>();
    private static final long WARNING_DURATION = 100; // 5 seconds (100 ticks)

    public static class JobBlockWarning {
        public final UUID npcUUID;
        public final String jobType;
        public final long timestamp;
        public final BlockPos jobBlockPos;

        public JobBlockWarning(UUID npcUUID, String jobType, BlockPos jobBlockPos) {
            this.npcUUID = npcUUID;
            this.jobType = jobType;
            this.jobBlockPos = jobBlockPos;
            this.timestamp = System.currentTimeMillis();
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - timestamp > WARNING_DURATION * 50; // Convert ticks to ms
        }
    }

    // Broadcast a warning that this job block is in use
    public static void broadcastJobBlockInUse(UUID npcUUID, String jobType, BlockPos jobBlockPos) {
        if (jobBlockPos != null && !jobType.isEmpty()) {
            activeWarnings.put(jobBlockPos, new JobBlockWarning(npcUUID, jobType, jobBlockPos));
        }
    }

    // Check if a job block is warned as being in use by another NPC
    public static boolean isJobBlockWarned(BlockPos jobBlockPos, UUID excludeUUID) {
        cleanupExpiredWarnings();

        JobBlockWarning warning = activeWarnings.get(jobBlockPos);
        if (warning == null) {
            return false;
        }

        // Not warned if it's our own warning
        if (warning.npcUUID.equals(excludeUUID)) {
            return false;
        }

        return !warning.isExpired();
    }

    // Get all job blocks that are currently warned within a certain radius
    public static Set<BlockPos> getWarnedJobBlocksInRadius(BlockPos center, int radius, UUID excludeUUID) {
        cleanupExpiredWarnings();
        Set<BlockPos> warnedBlocks = new HashSet<>();

        for (Map.Entry<BlockPos, JobBlockWarning> entry : activeWarnings.entrySet()) {
            BlockPos warnedPos = entry.getKey();
            JobBlockWarning warning = entry.getValue();

            // Skip our own warnings
            if (warning.npcUUID.equals(excludeUUID)) {
                continue;
            }

            // Skip expired warnings
            if (warning.isExpired()) {
                continue;
            }

            // Check if within radius
            double distanceSquared = center.distSqr(warnedPos);
            if (distanceSquared <= radius * radius) {
                warnedBlocks.add(warnedPos);
            }
        }

        return warnedBlocks;
    }

    // Remove warning when NPC loses job or is removed
    public static void removeJobBlockWarning(UUID npcUUID) {
        activeWarnings.entrySet().removeIf(entry -> entry.getValue().npcUUID.equals(npcUUID));
    }

    // Clean up expired warnings
    private static void cleanupExpiredWarnings() {
        activeWarnings.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    // Get warning info for debugging
    public static JobBlockWarning getWarningInfo(BlockPos jobBlockPos) {
        cleanupExpiredWarnings();
        return activeWarnings.get(jobBlockPos);
    }
}