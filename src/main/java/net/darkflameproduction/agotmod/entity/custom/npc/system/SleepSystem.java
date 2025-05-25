package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.*;

public class SleepSystem {
    private final Northern_Peasant_Entity peasant;

    // Sleep timing constants
    private static final int SLEEP_START_TIME = 12542;
    private static final int SLEEP_END_TIME = 23460;

    // Bed management
    private BlockPos bedPos;
    int bedSearchCooldown = 0;

    // Removed bed reservation system - NPCs now try beds directly

    public SleepSystem(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (bedSearchCooldown > 0) {
            bedSearchCooldown--;
        }

        // CRITICAL FIX: More aggressive bed search reset during sleep time
        if (peasant.shouldSleep() && !peasant.isSleeping() && !peasant.needsFoodCollection()) {
            BlockPos currentBed = getBedPos();
            boolean hasValidBed = false;

            // Check if we have a valid, available bed
            if (currentBed != null) {
                BlockState bedState = peasant.level().getBlockState(currentBed);
                if (bedState.getBlock() instanceof BedBlock) {
                    // Also check if the bed is actually available (not occupied)
                    if (!SleepSystem.isBedOccupied(peasant.level(), currentBed)) {
                        hasValidBed = true;
                    }
                }
            }

            // CRITICAL FIX: Much more aggressive cooldown management during sleep time
            if (!hasValidBed) {
                if (bedSearchCooldown > 100) { // If cooldown is more than 5 seconds
                    bedSearchCooldown = Math.min(bedSearchCooldown, 100); // Reduce to max 5 seconds
                }

                // If we have no bed at all and cooldown is still high, reset it
                if (currentBed == null && bedSearchCooldown > 40) {
                    bedSearchCooldown = 20; // Reset to 1 second if no bed at all
                }

                // If we have an invalid/occupied bed, give a short cooldown
                if (currentBed != null && !hasValidBed && bedSearchCooldown > 60) {
                    bedSearchCooldown = 40; // 2 seconds to find alternative
                }
            }
        }

        Level level = peasant.level();

        // Check if bed still exists and is available for sleeping
        if (!level.isClientSide && bedPos != null) {
            BlockState bedState = level.getBlockState(bedPos);
            if (!(bedState.getBlock() instanceof BedBlock)) {
                // Bed block was destroyed
                if (bedPos.equals(peasant.getHomeSystem().getHomeBedPos())) {
                    peasant.getHomeSystem().setHomeBedPos(null);
                }
                bedPos = null;
                stopSleeping();

                // CRITICAL FIX: If bed is destroyed during sleep time, immediately allow bed search
                if (peasant.shouldSleep()) {
                    bedSearchCooldown = 0;
                }
            } else {
                // Bed exists, but check if it's occupied by someone else while we're trying to use it
                if (!peasant.isSleeping() && SleepSystem.isBedOccupied(level, bedPos)) {
                    // Someone else took our bed, clear it unless it's our home bed
                    if (!bedPos.equals(peasant.getHomeSystem().getHomeBedPos())) {
                        bedPos = null;
                        // Allow immediate search for new bed during sleep time
                        if (peasant.shouldSleep()) {
                            bedSearchCooldown = Math.min(bedSearchCooldown, 40); // Max 2 seconds
                        }
                    }
                }
            }
        }

        // Wake up if it's time
        if (!level.isClientSide && isSleeping() && !shouldSleep()) {
            stopSleeping();
            // Don't clear bed position if it's their home bed
            if (bedPos != null && !bedPos.equals(peasant.getHomeSystem().getHomeBedPos())) {
                bedPos = null;
            }

            // Check food levels when waking up
            onWakeUp();
        }

        // CRITICAL FIX: Handle case where NPC is at sleep time but has no bed
        if (!level.isClientSide && peasant.shouldSleep() && !peasant.isSleeping() && bedPos == null) {
            // No bed during sleep time - ensure cooldown is minimal
            if (bedSearchCooldown > 60) { // More than 3 seconds
                bedSearchCooldown = 20; // Reset to 1 second
            }
        }

        // Handle sleeping movement
        if (isSleeping()) {
            peasant.getNavigation().stop();
            peasant.setDeltaMovement(0, peasant.getDeltaMovement().y, 0);

            // CRITICAL FIX: Ensure NPC stays close to bed while sleeping
            if (bedPos != null) {
                double distanceToBed = peasant.distanceToSqr(bedPos.getX(), bedPos.getY(), bedPos.getZ());
                if (distanceToBed > 9.0D) { // More than 3 blocks away
                    // Teleport back to bed if somehow moved away while sleeping
                    peasant.teleportTo(bedPos.getX() + 0.5, bedPos.getY(), bedPos.getZ() + 0.5);
                }
            }
        }

        // CRITICAL FIX: Additional safety check for stuck NPCs during sleep time
        if (!level.isClientSide && peasant.shouldSleep() && !peasant.isSleeping()) {
            // Check if NPC has been without a bed for too long during sleep time
            long currentTime = level.getGameTime();
            long sleepStartTime = 12542; // Your sleep start time
            long dayTime = level.getDayTime() % 24000;

            // If it's been more than 5 minutes since sleep time started and we have no bed
            if (dayTime > sleepStartTime + 6000 && bedPos == null && bedSearchCooldown > 20) {
                bedSearchCooldown = 0; // Force immediate bed search
            }
        }
    }

    public boolean shouldSleep() {
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime >= SLEEP_START_TIME && dayTime <= SLEEP_END_TIME;
    }

    public boolean isSleeping() {
        return peasant.getEntityData().get(peasant.getIsSleepingAccessor());
    }

    public void setSleeping(boolean sleeping) {
        peasant.getEntityData().set(peasant.getIsSleepingAccessor(), sleeping);
        if (sleeping) {
            peasant.getEntityData().set(peasant.getLastSleepTimeAccessor(), peasant.level().getGameTime());
        }
    }

    public BlockPos getBedPos() {
        return bedPos;
    }

    public void setBedPos(BlockPos pos) {
        // Simple bed assignment - no reservation system
        bedPos = pos;
    }

    public void startSleeping(BlockPos pos) {
        setSleeping(true);
        setBedPos(pos);

        // Establish home base on first sleep
        peasant.getHomeSystem().establishHomeBed(pos);

        peasant.getNavigation().stop();
        peasant.setDeltaMovement(0, 0, 0);

        // Stop eating if we start sleeping
        peasant.getHungerSystem().stopEating();
    }

    public void stopSleeping() {
        setSleeping(false);
        // CRITICAL: Properly clear the sleeping pose when stopping sleep
        if (peasant.getSleepingPos().isPresent()) {
            peasant.stopSleeping(); // Call the entity's stopSleeping method to clear pose
        }
    }

    public int getBedSearchCooldown() {
        return bedSearchCooldown;
    }

    public boolean canUseBedSearch() {
        return bedSearchCooldown <= 0;
    }

    public void setBedSearchCooldown(int cooldown) {
        // CRITICAL FIX: Don't override cooldowns during sleep time if NPC has no bed
        if (peasant.shouldSleep() && !peasant.isSleeping() && peasant.getBedPos() == null) {
            // If it's sleep time and NPC has no bed, use much shorter cooldowns
            this.bedSearchCooldown = Math.min(cooldown, 200); // Max 10 seconds
        } else if (peasant.shouldSleep() && !peasant.isSleeping() && !peasant.needsFoodCollection()) {
            // During sleep time, limit cooldown to maximum 300 ticks (15 seconds)
            this.bedSearchCooldown = Math.min(cooldown, 300);
        } else {
            this.bedSearchCooldown = cooldown;
        }
    }

    public void onHurt() {
        if (isSleeping()) {
            stopSleeping();
            // Don't clear bed position if it's their home bed
            if (bedPos != null && !bedPos.equals(peasant.getHomeSystem().getHomeBedPos())) {
                bedPos = null;
            }
        }
    }

    public void onRemove() {
        // No cleanup needed without reservation system
    }

    private void onWakeUp() {
        if (!peasant.getHungerSystem().hasEnoughFood()) {
            peasant.setNeedsFoodCollection(true);
        }

        // Reset farming state for new day - using the new simplified farming system
        if (peasant.getJobType().equals("farmer")) {
            // Always start by returning to job block each morning
            peasant.getFarmingSystem().setCurrentFarmState(
                    peasant.getFarmingSystem().hasFarm() ?
                            FarmingSystem.FarmState.RETURN_TO_JOB_BLOCK :
                            FarmingSystem.FarmState.NEEDS_FARM_SETUP
            );
        }
    }

    // Static bed management methods - simplified without reservation system
    public static boolean isBedOccupied(Level level, BlockPos bedPos) {
        return level.getEntitiesOfClass(Northern_Peasant_Entity.class,
                        new net.minecraft.world.phys.AABB(bedPos).inflate(2.0))
                .stream()
                .anyMatch(peasant -> bedPos.equals(peasant.getBedPos()) && peasant.isSleeping());
    }

    public void saveData(CompoundTag compound) {
        compound.putBoolean("IsSleeping", isSleeping());
        compound.putLong("LastSleepTime", peasant.getEntityData().get(peasant.getLastSleepTimeAccessor()));
        compound.putInt("BedSearchCooldown", bedSearchCooldown); // Save cooldown state

        if (bedPos != null) {
            compound.putInt("BedX", bedPos.getX());
            compound.putInt("BedY", bedPos.getY());
            compound.putInt("BedZ", bedPos.getZ());
        }

        // Enhanced sleeping state persistence
        if (isSleeping()) {
            compound.putBoolean("WasSleeping", true);
            if (peasant.getSleepingPos().isPresent()) {
                BlockPos sleepPos = peasant.getSleepingPos().get();
                compound.putInt("SleepingX", sleepPos.getX());
                compound.putInt("SleepingY", sleepPos.getY());
                compound.putInt("SleepingZ", sleepPos.getZ());
            } else if (bedPos != null) {
                // Fallback to bed position if sleeping pos not available
                compound.putInt("SleepingX", bedPos.getX());
                compound.putInt("SleepingY", bedPos.getY());
                compound.putInt("SleepingZ", bedPos.getZ());
            }
        }
    }

    public void loadData(CompoundTag compound) {
        peasant.getEntityData().set(peasant.getIsSleepingAccessor(), compound.getBoolean("IsSleeping"));
        peasant.getEntityData().set(peasant.getLastSleepTimeAccessor(), compound.getLong("LastSleepTime"));
        bedSearchCooldown = compound.getInt("BedSearchCooldown"); // Load cooldown state

        if (compound.contains("BedX")) {
            bedPos = new BlockPos(compound.getInt("BedX"), compound.getInt("BedY"), compound.getInt("BedZ"));
        }

        // Enhanced sleeping state restoration
        if (compound.getBoolean("WasSleeping") && compound.contains("SleepingX")) {
            BlockPos sleepPos = new BlockPos(
                    compound.getInt("SleepingX"),
                    compound.getInt("SleepingY"),
                    compound.getInt("SleepingZ")
            );
            BlockState bedState = peasant.level().getBlockState(sleepPos);

            if (bedState.getBlock() instanceof BedBlock) {
                if (shouldSleep()) {
                    // Properly restore sleeping state
                    peasant.startSleeping(sleepPos);
                    setBedPos(sleepPos);
                } else {
                    // Should not be sleeping anymore, ensure proper wake up
                    peasant.getEntityData().set(peasant.getIsSleepingAccessor(), false);
                    if (peasant.getSleepingPos().isPresent()) {
                        peasant.stopSleeping(); // Ensure pose is cleared
                    }
                }
            } else {
                // Bed no longer exists, clear sleeping state
                peasant.getEntityData().set(peasant.getIsSleepingAccessor(), false);
                if (peasant.getSleepingPos().isPresent()) {
                    peasant.stopSleeping();
                }
                bedPos = null;
            }
        }
    }
}