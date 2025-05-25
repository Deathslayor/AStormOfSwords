package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class JobSystem {
    private final Northern_Peasant_Entity peasant;

    // Job types
    public static final String JOB_FARMER = "farmer";
    public static final String JOB_NONE = "";

    // Work area system constants
    private static final int FARMER_WORK_RADIUS_X = 40; // 80x80 area around job block
    private static final int FARMER_WORK_RADIUS_Z = 40;
    private static final int FARMER_WORK_RADIUS_Y = 16; // 32 block height around job block

    // Job block reservations - static maps to track which blocks are taken
    private static final Map<BlockPos, UUID> jobBlockReservations = new HashMap<>();
    private static final Map<UUID, Long> jobReservationTimestamps = new HashMap<>();
    private static final long JOB_RESERVATION_TIMEOUT = 12000; // 10 minutes

    public JobSystem(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        // Check if peasant should be at work area and force return if too far
        if (!peasant.level().isClientSide && shouldBeAtWorkArea() && isTooFarFromWork() && !peasant.isSleeping()) {
            // Cancel current navigation and head to work area
            peasant.getNavigation().stop();
            BlockPos workCenter = getWorkCenter();
            peasant.getNavigation().moveTo(workCenter.getX(), workCenter.getY(), workCenter.getZ(), 0.8D);
        }
    }

    public String getJobType() {
        return peasant.getEntityData().get(peasant.getJobTypeAccessor());
    }

    public void setJobType(String jobType) {
        String oldJob = getJobType();
        peasant.getEntityData().set(peasant.getJobTypeAccessor(), jobType);

        // Update name when job changes
        if (!oldJob.equals(jobType)) {
            updateNameWithJob();
        }
    }

    public boolean hasJob() {
        return !getJobType().isEmpty();
    }

    public BlockPos getJobBlockPos() {
        return peasant.getEntityData().get(peasant.getJobBlockPosAccessor()).orElse(null);
    }

    public void setJobBlockPos(BlockPos pos) {
        peasant.getEntityData().set(peasant.getJobBlockPosAccessor(), Optional.ofNullable(pos));
    }

    public boolean isWithinWorkArea(BlockPos pos) {
        if (!hasJob() || getJobBlockPos() == null) {
            return true; // No job or job block, can go anywhere within home area
        }

        BlockPos jobBlockPos = getJobBlockPos();
        int deltaX = Math.abs(pos.getX() - jobBlockPos.getX());
        int deltaZ = Math.abs(pos.getZ() - jobBlockPos.getZ());
        int deltaY = Math.abs(pos.getY() - jobBlockPos.getY());

        // Different work radius based on job type
        if (getJobType().equals(JOB_FARMER)) {
            return deltaX <= FARMER_WORK_RADIUS_X && deltaZ <= FARMER_WORK_RADIUS_Z && deltaY <= FARMER_WORK_RADIUS_Y;
        }

        return true; // Default: no restrictions for unknown job types
    }

    public boolean shouldBeAtWorkArea() {
        // Should be at work area during day time (not sleeping) and have a job
        return hasJob() && !peasant.shouldSleep() && !peasant.needsFoodCollection() &&
                peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood();
    }

    public boolean isTooFarFromWork() {
        if (!hasJob() || getJobBlockPos() == null || peasant.shouldSleep()) {
            return false; // Not working hours or no job
        }

        BlockPos jobBlockPos = getJobBlockPos();
        BlockPos currentPos = peasant.blockPosition();
        double distanceSquared = jobBlockPos.distSqr(currentPos);

        // Return to work if too far (based on job type)
        if (getJobType().equals(JOB_FARMER)) {
            return distanceSquared > ((FARMER_WORK_RADIUS_X + 10) * (FARMER_WORK_RADIUS_X + 10));
        }

        return false;
    }

    public BlockPos getWorkCenter() {
        if (hasJob() && getJobBlockPos() != null) {
            return getJobBlockPos();
        }
        return peasant.getHomeCenter();
    }

    public void onRemove() {
        releaseJobBlockReservation(peasant.getUUID());
    }

    private void updateNameWithJob() {
        if (peasant.hasCustomName()) {
            String currentName = peasant.getCustomName().getString();
            String baseName = currentName;

            // Remove existing job title if present
            if (currentName.startsWith("Farmer ")) {
                baseName = currentName.substring(7); // Remove "Farmer "
            }

            // Add new job title
            String newName = baseName;
            if (getJobType().equals(JOB_FARMER)) {
                newName = "Farmer " + baseName;
            }

            peasant.setCustomName(Component.literal(newName));
        }
    }

    // Static job block reservation methods
    public static boolean isJobBlockReserved(BlockPos blockPos, UUID excludeUUID) {
        cleanupExpiredJobReservations();
        UUID reservedBy = jobBlockReservations.get(blockPos);
        return reservedBy != null && !reservedBy.equals(excludeUUID);
    }

    public static boolean reserveJobBlock(BlockPos blockPos, UUID peasantUUID) {
        cleanupExpiredJobReservations();

        UUID currentReserver = jobBlockReservations.get(blockPos);
        if (currentReserver != null && !currentReserver.equals(peasantUUID)) {
            return false;
        }

        jobBlockReservations.put(blockPos, peasantUUID);
        jobReservationTimestamps.put(peasantUUID, System.currentTimeMillis());
        return true;
    }

    public static void releaseJobBlockReservation(UUID peasantUUID) {
        jobBlockReservations.entrySet().removeIf(entry -> entry.getValue().equals(peasantUUID));
        jobReservationTimestamps.remove(peasantUUID);
    }

    private static void cleanupExpiredJobReservations() {
        long currentTime = System.currentTimeMillis();
        jobReservationTimestamps.entrySet().removeIf(entry -> {
            if (currentTime - entry.getValue() > JOB_RESERVATION_TIMEOUT) {
                UUID expiredUUID = entry.getKey();
                jobBlockReservations.entrySet().removeIf(jobEntry -> jobEntry.getValue().equals(expiredUUID));
                return true;
            }
            return false;
        });
    }

    public void saveData(CompoundTag compound) {
        compound.putString("JobType", getJobType());
        if (getJobBlockPos() != null) {
            compound.putInt("JobBlockX", getJobBlockPos().getX());
            compound.putInt("JobBlockY", getJobBlockPos().getY());
            compound.putInt("JobBlockZ", getJobBlockPos().getZ());
        }
    }

    public void loadData(CompoundTag compound) {
        setJobType(compound.getString("JobType"));
        if (compound.contains("JobBlockX")) {
            BlockPos jobBlockPos = new BlockPos(compound.getInt("JobBlockX"), compound.getInt("JobBlockY"), compound.getInt("JobBlockZ"));
            setJobBlockPos(jobBlockPos);
            // Re-reserve the job block
            if (hasJob()) {
                reserveJobBlock(jobBlockPos, peasant.getUUID());
            }
        }
    }
}