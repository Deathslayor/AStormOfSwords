package net.darkflameproduction.agotmod.entity.custom.npc.system.guard;

import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.LivingEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class GuardSystem {
    private final Peasant_Entity peasant;

    // Guard shift types
    public enum GuardShift {
        DAY_SHIFT(0, 16000),        // 0 to 16000 ticks (dawn to dusk)
        EVENING_SHIFT(8000, 24000), // 8000 to 24000 ticks (noon to dawn)
        NIGHT_SHIFT(16000, 8000);   // 16000 to 8000 ticks (dusk to noon, wraps around)

        public final int startTime;
        public final int endTime;

        GuardShift(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean isActiveTime(long dayTime) {
            long time = dayTime % 24000;
            if (startTime < endTime) {
                // Normal range (e.g., 0 to 16000)
                return time >= startTime && time <= endTime;
            } else {
                // Wrapping range (e.g., 16000 to 8000)
                return time >= startTime || time <= endTime;
            }
        }

        public boolean isSleepTime(long dayTime) {
            return !isActiveTime(dayTime);
        }
    }

    // Static system for shift coordination
    private static final Map<UUID, GuardShiftBroadcast> activeShiftBroadcasts = new ConcurrentHashMap<>();
    private static final long BROADCAST_DURATION = 200; // 10 seconds

    public static class GuardShiftBroadcast {
        public final UUID guardUUID;
        public final GuardShift shift;
        public final BlockPos jobBlockPos;
        public final long timestamp;

        public GuardShiftBroadcast(UUID guardUUID, GuardShift shift, BlockPos jobBlockPos) {
            this.guardUUID = guardUUID;
            this.shift = shift;
            this.jobBlockPos = jobBlockPos;
            this.timestamp = System.currentTimeMillis();
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - timestamp > BROADCAST_DURATION * 50; // Convert ticks to ms
        }
    }

    // Instance variables
    private GuardShift assignedShift = null;
    private int shiftAssignmentTimer = 0;
    private static final int SHIFT_ASSIGNMENT_DELAY = 100; // Wait 100 ticks to gather shift info
    private BlockPos patrolTarget = null;
    private int patrolCooldown = 0;
    private static final int PATROL_RADIUS = 96;
    private static final int PATROL_CHANGE_INTERVAL = 400; // Change patrol target every 20 seconds

    // Combat tracking
    private LivingEntity currentTarget = null;
    private int targetScanCooldown = 0;
    private static final int TARGET_SCAN_INTERVAL = 20; // Scan for targets every second
    private static final int COMBAT_RANGE = 16; // Range to detect and chase monsters

    public GuardSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (peasant.level().isClientSide) {
            return;
        }

        // Only run guard logic if this NPC is a guard AND has a valid job block
        if (!peasant.getJobType().equals(JobSystem.JOB_GUARD) ||
                peasant.getJobBlockPos() == null) {
            return;
        }

        // Verify the job block still exists (armor stand)
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (!(peasant.level().getBlockState(jobBlockPos).getBlock() == net.minecraft.world.level.block.Blocks.ANVIL)) {
            return; // Job block is gone, let JobSystem handle job loss
        }

        // Handle shift assignment
        if (assignedShift == null) {
            handleShiftAssignment();
            return; // Wait until shift is assigned
        }

        // Broadcast our shift periodically
        broadcastShiftInfo();

        // Check if we should be sleeping or working
        long dayTime = peasant.level().getDayTime() % 24000;

        if (assignedShift.isSleepTime(dayTime)) {
            // We should be sleeping - guard sleep logic is handled by modified sleep system
            return;
        }

        // We're on duty - handle guard behavior
        handleGuardDuty();
    }

    private void handleShiftAssignment() {
        if (shiftAssignmentTimer < SHIFT_ASSIGNMENT_DELAY) {
            shiftAssignmentTimer++;
            return;
        }

        // Time to assign shift - gather information about existing shifts
        cleanupExpiredBroadcasts();

        Map<GuardShift, Integer> shiftCounts = new HashMap<>();
        shiftCounts.put(GuardShift.DAY_SHIFT, 0);
        shiftCounts.put(GuardShift.EVENING_SHIFT, 0);
        shiftCounts.put(GuardShift.NIGHT_SHIFT, 0);

        // Count existing guards in each shift
        for (GuardShiftBroadcast broadcast : activeShiftBroadcasts.values()) {
            if (!broadcast.isExpired() && !broadcast.guardUUID.equals(peasant.getUUID())) {
                shiftCounts.put(broadcast.shift, shiftCounts.get(broadcast.shift) + 1);
            }
        }

        // Find the shift with the least guards
        GuardShift leastPopulatedShift = GuardShift.DAY_SHIFT;
        int minCount = Integer.MAX_VALUE;

        for (Map.Entry<GuardShift, Integer> entry : shiftCounts.entrySet()) {
            if (entry.getValue() < minCount) {
                minCount = entry.getValue();
                leastPopulatedShift = entry.getKey();
            }
        }

        // Assign the least populated shift
        assignedShift = leastPopulatedShift;

        // Immediately broadcast our new shift
        broadcastShiftInfo();
    }

    private void broadcastShiftInfo() {
        if (assignedShift != null && peasant.getJobBlockPos() != null) {
            activeShiftBroadcasts.put(peasant.getUUID(),
                    new GuardShiftBroadcast(peasant.getUUID(), assignedShift, peasant.getJobBlockPos()));
        }
    }

    private void handleGuardDuty() {
        // Scan for monsters periodically
        if (targetScanCooldown <= 0) {
            scanForTargets();
            targetScanCooldown = TARGET_SCAN_INTERVAL;
        } else {
            targetScanCooldown--;
        }

        // Handle combat if we have a target
        if (currentTarget != null) {
            handleCombat();
            return; // Combat takes priority over patrol
        }

        // Handle patrol when not in combat
        handlePatrol();
    }

    private void scanForTargets() {
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos == null) return;

        // Find the closest monster within combat range
        List<Monster> nearbyMonsters = peasant.level().getEntitiesOfClass(
                Monster.class,
                new net.minecraft.world.phys.AABB(jobBlockPos).inflate(COMBAT_RANGE),
                monster -> monster.isAlive() && !monster.isRemoved()
        );

        if (!nearbyMonsters.isEmpty()) {
            // Find closest monster
            Monster closestMonster = null;
            double closestDistance = Double.MAX_VALUE;

            for (Monster monster : nearbyMonsters) {
                double distance = peasant.distanceToSqr(monster);
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestMonster = monster;
                }
            }

            if (closestMonster != null) {
                currentTarget = closestMonster;
                peasant.setTarget(closestMonster); // Set entity target for combat AI
            }
        } else {
            // No monsters found, clear target
            currentTarget = null;
            peasant.setTarget(null);
        }
    }

    private void handleCombat() {
        if (currentTarget == null || !currentTarget.isAlive() || currentTarget.isRemoved()) {
            currentTarget = null;
            peasant.setTarget(null);
            return;
        }

        // Check if target is still within combat range
        double distanceToTarget = peasant.distanceToSqr(currentTarget);
        if (distanceToTarget > COMBAT_RANGE * COMBAT_RANGE) {
            // Target too far away, abandon pursuit
            currentTarget = null;
            peasant.setTarget(null);
            return;
        }

        // Combat is handled by the existing PeasantDefenseGoal
        // We just need to maintain the target
    }

    private void handlePatrol() {
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos == null) return;

        // Update patrol cooldown
        if (patrolCooldown > 0) {
            patrolCooldown--;
        }

        // Check if we need a new patrol target or reached current one
        boolean needNewTarget = false;

        if (patrolTarget == null) {
            needNewTarget = true;
        } else if (patrolCooldown <= 0) {
            needNewTarget = true;
        } else if (peasant.distanceToSqr(patrolTarget.getX(), patrolTarget.getY(), patrolTarget.getZ()) <= 4.0D) {
            // Reached current patrol point
            needNewTarget = true;
        }

        if (needNewTarget) {
            generateNewPatrolTarget(jobBlockPos);
            patrolCooldown = PATROL_CHANGE_INTERVAL;
        }

        // Move to patrol target
        if (patrolTarget != null && !peasant.getNavigation().isInProgress()) {
            peasant.getNavigation().moveTo(
                    patrolTarget.getX() + 0.5,
                    patrolTarget.getY(),
                    patrolTarget.getZ() + 0.5,
                    0.6D
            );
        }
    }

    private void generateNewPatrolTarget(BlockPos center) {
        net.minecraft.util.RandomSource random = peasant.getRandom();

        // Generate random position within patrol radius
        int attempts = 0;
        while (attempts < 10) {
            int offsetX = random.nextInt(PATROL_RADIUS * 2 + 1) - PATROL_RADIUS;
            int offsetZ = random.nextInt(PATROL_RADIUS * 2 + 1) - PATROL_RADIUS;
            int offsetY = random.nextInt(17) - 8; // -8 to +8 Y offset

            BlockPos candidatePos = center.offset(offsetX, offsetY, offsetZ);

            // Check if position is valid (within home area and pathable)
            if (peasant.isWithinHomeArea(candidatePos)) {
                // Basic ground check - make sure there's solid ground
                BlockPos groundPos = candidatePos.below();
                if (peasant.level().getBlockState(groundPos).isSolid() &&
                        peasant.level().getBlockState(candidatePos).isAir() &&
                        peasant.level().getBlockState(candidatePos.above()).isAir()) {

                    patrolTarget = candidatePos;
                    return;
                }
            }
            attempts++;
        }

        // Fallback - just patrol around the job block
        patrolTarget = center.offset(
                random.nextInt(21) - 10,  // -10 to +10
                0,
                random.nextInt(21) - 10   // -10 to +10
        );
    }

    // Override normal sleep behavior for guards
    public boolean shouldSleep() {
        if (assignedShift == null) {
            // No shift assigned yet, use normal sleep schedule temporarily
            // DEBUG: Add logging
            if (!peasant.level().isClientSide) {
            }
            return peasant.getSleepSystem().shouldSleep();
        }

        long dayTime = peasant.level().getDayTime() % 24000;
        boolean sleepTime = assignedShift.isSleepTime(dayTime);

        // DEBUG: Add logging
        if (!peasant.level().isClientSide) {
        }

        return sleepTime;
    }

    // Getters
    public GuardShift getAssignedShift() {
        return assignedShift;
    }

    public boolean hasAssignedShift() {
        return assignedShift != null;
    }

    public LivingEntity getCurrentTarget() {
        return currentTarget;
    }

    public BlockPos getPatrolTarget() {
        return patrolTarget;
    }

    // Static methods for system management
    public static void removeGuardBroadcast(UUID guardUUID) {
        activeShiftBroadcasts.remove(guardUUID);
    }

    private static void cleanupExpiredBroadcasts() {
        activeShiftBroadcasts.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    public static Map<GuardShift, Integer> getShiftCounts() {
        cleanupExpiredBroadcasts();
        Map<GuardShift, Integer> counts = new HashMap<>();
        counts.put(GuardShift.DAY_SHIFT, 0);
        counts.put(GuardShift.EVENING_SHIFT, 0);
        counts.put(GuardShift.NIGHT_SHIFT, 0);

        for (GuardShiftBroadcast broadcast : activeShiftBroadcasts.values()) {
            if (!broadcast.isExpired()) {
                counts.put(broadcast.shift, counts.get(broadcast.shift) + 1);
            }
        }

        return counts;
    }

    // Save/Load methods
    public void saveData(CompoundTag compound) {
        if (assignedShift != null) {
            compound.putString("AssignedShift", assignedShift.name());
        }
        compound.putInt("ShiftAssignmentTimer", shiftAssignmentTimer);
        compound.putInt("PatrolCooldown", patrolCooldown);
        compound.putInt("TargetScanCooldown", targetScanCooldown);

        if (patrolTarget != null) {
            compound.putInt("PatrolTargetX", patrolTarget.getX());
            compound.putInt("PatrolTargetY", patrolTarget.getY());
            compound.putInt("PatrolTargetZ", patrolTarget.getZ());
        }
    }

    public void loadData(CompoundTag compound) {
        if (compound.contains("AssignedShift")) {
            try {
                assignedShift = GuardShift.valueOf(compound.getString("AssignedShift"));
            } catch (IllegalArgumentException e) {
                assignedShift = null; // Will be reassigned
            }
        }

        shiftAssignmentTimer = compound.getInt("ShiftAssignmentTimer");
        patrolCooldown = compound.getInt("PatrolCooldown");
        targetScanCooldown = compound.getInt("TargetScanCooldown");

        if (compound.contains("PatrolTargetX")) {
            patrolTarget = new BlockPos(
                    compound.getInt("PatrolTargetX"),
                    compound.getInt("PatrolTargetY"),
                    compound.getInt("PatrolTargetZ")
            );
        }
    }

    public void onRemove() {
        removeGuardBroadcast(peasant.getUUID());
    }
}