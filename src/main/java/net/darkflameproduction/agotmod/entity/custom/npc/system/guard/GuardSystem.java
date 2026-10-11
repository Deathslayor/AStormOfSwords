package net.darkflameproduction.agotmod.entity.custom.npc.system.guard;

import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.LivingEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Guard shift scheduling and threat detection.
 * Movement while on duty (patrols, visits, standing guard) is handled by GuardPatrolGoal.
 */
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
    // The kind of melee weapon this guard fights with ("spear", "sword"...). Only upgraded within this kind.
    private String weaponKind = "";
    // The shift (see currentShiftNumber) in which the guard last checked the town hall for better gear
    private long lastArmoryCheckShift = Long.MIN_VALUE;
    private static final int SHIFT_ASSIGNMENT_DELAY = 100; // Wait 100 ticks to gather shift info
    private static final int HOUSE_BANNER_CHECK_INTERVAL = 100; // Re-check the shield's house banner every 5 seconds

    // Combat tracking
    private LivingEntity currentTarget = null;
    private LivingEntity alertTarget = null; // a threat we ran to after a call for help
    private int targetScanCooldown = 0;
    private static final int TARGET_SCAN_INTERVAL = 20; // Scan for targets every second
    private static final int COMBAT_RANGE = 48; // Range to detect and chase monsters

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

        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (!peasant.level().getBlockState(jobBlockPos).is(ModBLocks.GUARD_BARREL.get())) {
            return; // Job block is gone, let JobSystem handle job loss
        }

        // Handle shift assignment
        if (assignedShift == null) {
            handleShiftAssignment();
            return; // Wait until shift is assigned
        }

        // Broadcast our shift periodically
        broadcastShiftInfo();

        // Keep the controlling house's banner painted on our shield
        if ((peasant.tickCount + peasant.getId()) % HOUSE_BANNER_CHECK_INTERVAL == 0) {
            GuardEquipment.applyHouseBanner(peasant);
        }

        long dayTime = peasant.level().getDayTime() % 24000;
        boolean onDuty = assignedShift.isActiveTime(dayTime);

        if (!onDuty) {
            // Off duty - guard sleep logic is handled by the sleep system
            return;
        }

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

        assignedShift = leastPopulatedShift;
        broadcastShiftInfo();

    }

    private void broadcastShiftInfo() {
        if (assignedShift != null && peasant.getJobBlockPos() != null) {
            activeShiftBroadcasts.put(peasant.getUUID(),
                    new GuardShiftBroadcast(peasant.getUUID(), assignedShift, peasant.getJobBlockPos()));
        }
    }

    private void handleGuardDuty() {
        // Badly hurt - don't hunt monsters until recovered (NpcRetreatGoal keeps the guard away from them)
        if (peasant.isRetreating()) {
            currentTarget = null;
            alertTarget = null;
            return;
        }

        // Scan for monsters periodically
        if (targetScanCooldown <= 0) {
            scanForTargets();
            targetScanCooldown = TARGET_SCAN_INTERVAL;
        } else {
            targetScanCooldown--;
        }

        if (currentTarget != null) {
            handleCombat();
        }
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
        } else if (currentTarget != null && currentTarget == alertTarget && currentTarget.isAlive()) {
            // Answering a call for help away from our post - keep fighting that threat
        } else {
            currentTarget = null;
            alertTarget = null;
            peasant.setTarget(null);
        }
    }

    /** Engage a threat another NPC called for help about. */
    public void engage(LivingEntity threat) {
        currentTarget = threat;
        alertTarget = threat;
        peasant.setTarget(threat);
    }

    private void handleCombat() {
        if (currentTarget == null || !currentTarget.isAlive() || currentTarget.isRemoved()) {
            currentTarget = null;
            peasant.setTarget(null);
            return;
        }

        // Abandon pursuit if the target left combat range
        if (peasant.distanceToSqr(currentTarget) > COMBAT_RANGE * COMBAT_RANGE) {
            currentTarget = null;
            peasant.setTarget(null);
        }

        // Actual fighting is handled by GuardCombatGoal
    }

    // Override normal sleep behavior for guards
    public boolean shouldSleep() {
        if (assignedShift == null) {
            // No shift assigned yet, use normal sleep schedule temporarily
            return peasant.getSleepSystem().shouldSleep();
        }

        long dayTime = peasant.level().getDayTime() % 24000;
        return assignedShift.isSleepTime(dayTime);
    }

    // Getters
    public String getWeaponKind() {
        return weaponKind;
    }

    public void setWeaponKind(String kind) {
        this.weaponKind = kind == null ? "" : kind;
    }

    public boolean hasAssignedShift() {
        return assignedShift != null;
    }

    /**
     * Counts this guard's shifts: goes up by one each time its shift starts (a night shift
     * keeps the same number past midnight). Long.MIN_VALUE when no shift is assigned.
     */
    public long currentShiftNumber() {
        if (assignedShift == null) return Long.MIN_VALUE;
        return Math.floorDiv(peasant.level().getDayTime() - assignedShift.startTime, 24000L);
    }

    /** True until the guard has done its town hall gear check this shift. */
    public boolean needsArmoryCheck() {
        return assignedShift != null && lastArmoryCheckShift != currentShiftNumber();
    }

    public void markArmoryChecked() {
        lastArmoryCheckShift = currentShiftNumber();
    }

    public GuardShift getAssignedShift() {
        return assignedShift;
    }

    public LivingEntity getCurrentTarget() {
        return currentTarget;
    }

    // Static methods for system management
    public static void removeGuardBroadcast(UUID guardUUID) {
        activeShiftBroadcasts.remove(guardUUID);
    }

    private static void cleanupExpiredBroadcasts() {
        activeShiftBroadcasts.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    // Save/Load methods
    public void saveData(CompoundTag compound) {
        if (assignedShift != null) {
            compound.putString("AssignedShift", assignedShift.name());
        }
        compound.putInt("ShiftAssignmentTimer", shiftAssignmentTimer);
        compound.putInt("TargetScanCooldown", targetScanCooldown);
        compound.putString("WeaponKind", weaponKind);
        compound.putLong("LastArmoryCheckShift", lastArmoryCheckShift);
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
        targetScanCooldown = compound.getInt("TargetScanCooldown");
        weaponKind = compound.getString("WeaponKind");
        if (compound.contains("LastArmoryCheckShift")) {
            lastArmoryCheckShift = compound.getLong("LastArmoryCheckShift");
        }
    }

    public void onRemove() {
        removeGuardBroadcast(peasant.getUUID());
    }
}
