package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class JobSystem {
    private final Peasant_Entity peasant;

    // Job types
    public static final String JOB_GROCER = "grocer";
    public static final String JOB_FARMER = "farmer";
    public static final String JOB_GUARD = "guard";
    public static final String JOB_NONE = "";

    // Work area system constants
    private static final int FARMER_WORK_RADIUS_X = 40;
    private static final int FARMER_WORK_RADIUS_Z = 40;
    private static final int FARMER_WORK_RADIUS_Y = 16;

    // Grocer work area constants - now split into collection and idle areas
    private static final int GROCER_COLLECTION_RADIUS_X = 128;
    private static final int GROCER_COLLECTION_RADIUS_Z = 128;
    private static final int GROCER_COLLECTION_RADIUS_Y = 32;

    private static final int GROCER_IDLE_RADIUS_X = 5;
    private static final int GROCER_IDLE_RADIUS_Z = 5;
    private static final int GROCER_IDLE_RADIUS_Y = 16;

    // Guard work area constants
    private static final int GUARD_WORK_RADIUS_X = 96;
    private static final int GUARD_WORK_RADIUS_Z = 96;
    private static final int GUARD_WORK_RADIUS_Y = 32;

    // Job block reservations - static maps to track which blocks are taken
    private static final Map<BlockPos, UUID> jobBlockReservations = new HashMap<>();
    private static final Map<UUID, Long> jobReservationTimestamps = new HashMap<>();
    private static final long JOB_RESERVATION_TIMEOUT = 12000; // 10 minutes

    // Job warning broadcast timer
    private int warningBroadcastTimer = 0;

    public JobSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        // CRITICAL: Check if job block still exists before doing anything else
        if (hasJob() && getJobBlockPos() != null) {
            if (!isJobBlockValid()) {
                // Job block was destroyed, lose job immediately
                loseJob();
                return; // Don't continue with other job-related logic
            }
        }

        // Check if peasant should be at work area and force return if too far
        if (!peasant.level().isClientSide && shouldBeAtWorkArea() && isTooFarFromWork() && !peasant.isSleeping()) {
            // Cancel current navigation and head to work area
            peasant.getNavigation().stop();
            BlockPos workCenter = getWorkCenter();
            peasant.getNavigation().moveTo(workCenter.getX(), workCenter.getY(), workCenter.getZ(), 0.8D);
        }

        // Broadcast job block warning every 40 ticks if we have a job
        if (!peasant.level().isClientSide && hasJob() && getJobBlockPos() != null) {
            warningBroadcastTimer++;
            if (warningBroadcastTimer >= 40) {
                JobWarningSystem.broadcastJobBlockInUse(
                        peasant.getUUID(),
                        getJobType(),
                        getJobBlockPos()
                );
                warningBroadcastTimer = 0;
            }
        } else {
            warningBroadcastTimer = 0;
        }
    }

    private boolean isJobBlockValid() {
        BlockPos jobBlockPos = getJobBlockPos();
        if (jobBlockPos == null) {
            return false;
        }

        BlockState jobBlockState = peasant.level().getBlockState(jobBlockPos);

        // Check based on job type
        if (getJobType().equals(JOB_FARMER)) {
            return jobBlockState.getBlock() == net.minecraft.world.level.block.Blocks.COMPOSTER;
        } else if (getJobType().equals(JOB_GROCER)) {
            return jobBlockState.getBlock() == net.minecraft.world.level.block.Blocks.BARREL;
        } else if (getJobType().equals(JOB_GUARD)) {
            return jobBlockState.getBlock() == net.minecraft.world.level.block.Blocks.ANVIL;
        }

        // For unknown job types, assume invalid
        return false;
    }

    private void loseJob() {
        String oldJobType = getJobType();
        BlockPos oldJobBlockPos = getJobBlockPos();

        // Clear job data
        setJobType(JOB_NONE);
        setJobBlockPos(null);

        // Release job block reservation
        releaseJobBlockReservation(peasant.getUUID());

        // Remove job warning
        JobWarningSystem.removeJobBlockWarning(peasant.getUUID());

        // Reset job-specific systems
        if (oldJobType.equals(JOB_FARMER)) {
            // Reset farming system
            peasant.getFarmingSystem().setCurrentFarmState(
                    peasant.getFarmingSystem().hasFarm() ?
                            FarmingSystem.FarmState.NEEDS_FARM_SETUP :
                            FarmingSystem.FarmState.NEEDS_FARM_SETUP
            );
        } else if (oldJobType.equals(JOB_GROCER)) {
            // Reset grocer system if needed
            // The grocer system doesn't need specific reset logic currently
            // as it maintains its digital inventory even after job loss
        } else if (oldJobType.equals(JOB_GUARD)) {
            // Clean up guard system
            peasant.getGuardSystem().onRemove();
        }
    }

    /**
     * Called when a peasant successfully claims a job block
     * This should be called from wherever the job assignment logic happens
     */
    public void onJobBlockClaimed(BlockPos jobBlockPos, String newJobType) {
        // Set the job data
        setJobType(newJobType, false); // Don't give equipment yet
        setJobBlockPos(jobBlockPos);

        // Reserve the job block
        reserveJobBlock(jobBlockPos, peasant.getUUID());

        // Trigger the interact animation
        triggerInteractAnimation();

        // Give job equipment after animation is triggered
        giveJobEquipment("", newJobType);
    }

    /**
     * Triggers the interact animation for the peasant
     */
    private void triggerInteractAnimation() {
        // Only trigger on server side
        if (!peasant.level().isClientSide) {
            peasant.triggerInteractAnimation();
        }
    }

    public String getJobType() {
        return peasant.getEntityData().get(peasant.getJobTypeAccessor());
    }

    public void setJobType(String jobType) {
        setJobType(jobType, true); // Call with equipment giving enabled by default
    }

    public void setJobType(String jobType, boolean giveEquipment) {
        String oldJob = getJobType();
        peasant.getEntityData().set(peasant.getJobTypeAccessor(), jobType);

        // Update name when job changes
        if (!oldJob.equals(jobType)) {
            updateNameWithJob();

            // Give job-specific equipment when getting a new job (if enabled)
            if (giveEquipment) {
                giveJobEquipment(oldJob, jobType);
            }

            // If losing job, remove warning
            if (jobType.isEmpty() && !oldJob.isEmpty()) {
                JobWarningSystem.removeJobBlockWarning(peasant.getUUID());
            }
        }
    }

    /**
     * Gives appropriate equipment when an NPC gets a new job
     */
    private void giveJobEquipment(String oldJob, String newJob) {
        // Only give equipment on server side
        if (peasant.level().isClientSide) {
            return;
        }

        // Give equipment based on new job
        if (newJob.equals(JOB_FARMER)) {
            giveIronHoe();
        }
        // Guards no longer get automatic equipment
        // else if (newJob.equals(JOB_GUARD)) {
        //     giveGuardEquipment();
        // }
        // Could add equipment for other jobs here in the future
    }

    /**
     * Gives the farmer an iron hoe if they don't already have one
     */
    private void giveIronHoe() {
        // Check if they already have a hoe equipped or in inventory
        if (hasHoe()) {
            return; // Already has a hoe, don't give another
        }

        ItemStack ironHoe = new ItemStack(Items.IRON_HOE);

        // Try to add to inventory first
        if (peasant.getInventorySystem().addItem(ironHoe)) {
            // Successfully added to inventory, auto-equip will handle equipping it
            peasant.getInventorySystem().forceEquipmentCheck();
        } else {
            // Inventory full, try to equip directly to main hand if it's empty
            if (peasant.getInventorySystem().getMainHandItem().isEmpty()) {
                peasant.getInventorySystem().setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND, ironHoe);
            } else {
                // Main hand occupied and inventory full, drop the hoe near the peasant
                if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                    peasant.spawnAtLocation(serverLevel, ironHoe);
                }
            }
        }
    }

    /**
     * Gives guards equipment (sword and basic armor)
     */
    private void giveGuardEquipment() {
        // Give guards a sword if they don't have one
        if (!hasWeapon()) {
            ItemStack ironSword = new ItemStack(Items.IRON_SWORD);

            // Try to add to inventory first
            if (peasant.getInventorySystem().addItem(ironSword)) {
                // Successfully added to inventory, auto-equip will handle equipping it
                peasant.getInventorySystem().forceEquipmentCheck();
            } else {
                // Inventory full, try to equip directly to main hand if it's empty
                if (peasant.getInventorySystem().getMainHandItem().isEmpty()) {
                    peasant.getInventorySystem().setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND, ironSword);
                } else {
                    // Main hand occupied and inventory full, drop the sword near the peasant
                    if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                        peasant.spawnAtLocation(serverLevel, ironSword);
                    }
                }
            }
        }

        // Give basic armor if they don't have any
        giveBasicArmor();
    }

    /**
     * Checks if the peasant already has a hoe (equipped or in inventory)
     */
    private boolean hasHoe() {
        // Check equipped items
        if (peasant.getInventorySystem().getMainHandItem().getItem() instanceof net.minecraft.world.item.HoeItem ||
                peasant.getInventorySystem().getOffhandItem().getItem() instanceof net.minecraft.world.item.HoeItem) {
            return true;
        }

        // Check inventory
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() instanceof net.minecraft.world.item.HoeItem) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the peasant already has a weapon (equipped or in inventory)
     */
    private boolean hasWeapon() {
        // Check equipped items
        if (peasant.getInventorySystem().getMainHandItem().getItem() instanceof net.minecraft.world.item.SwordItem ||
                peasant.getInventorySystem().getMainHandItem().getItem() instanceof net.minecraft.world.item.AxeItem) {
            return true;
        }

        // Check inventory
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() instanceof net.minecraft.world.item.SwordItem ||
                    stack.getItem() instanceof net.minecraft.world.item.AxeItem) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gives basic leather armor to guards
     */
    private void giveBasicArmor() {
        // Give leather armor if no armor equipped
        if (peasant.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST).isEmpty()) {
            peasant.setItemSlot(net.minecraft.world.entity.EquipmentSlot.CHEST,
                    new ItemStack(Items.LEATHER_CHESTPLATE));
        }

        if (peasant.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS).isEmpty()) {
            peasant.setItemSlot(net.minecraft.world.entity.EquipmentSlot.LEGS,
                    new ItemStack(Items.LEATHER_LEGGINGS));
        }

        if (peasant.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).isEmpty()) {
            peasant.setItemSlot(net.minecraft.world.entity.EquipmentSlot.FEET,
                    new ItemStack(Items.LEATHER_BOOTS));
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

    // Method to check if grocer is currently collecting
    private boolean isGrocerCurrentlyCollecting() {
        if (!getJobType().equals(JOB_GROCER)) {
            return false;
        }

        GrocerSystem.GrocerState grocerState = peasant.getGrocerSystem().getCurrentState();
        boolean hasCollectedToday = peasant.getGrocerSystem().hasCollectedToday();
        long currentTime = peasant.level().getDayTime() % 24000;
        boolean isCollectionTime = currentTime >= 4000;

        boolean goalCanUse = false;
        if (peasant.getGrocerCollectionGoal() != null) {
            goalCanUse = peasant.getGrocerCollectionGoal().canUse();
        }

        // Only allow collection during proper conditions
        // 1. Must be actively collecting according to grocer system
        if (grocerState == GrocerSystem.GrocerState.COLLECTING_FROM_BARRELS) {
            return true;
        }

        // 2. Must be collection time AND haven't collected today AND goal wants to start
        if (isCollectionTime && !hasCollectedToday && goalCanUse) {
            return true;
        }

        // If they've finished collecting for the day, they should be idle
        if (hasCollectedToday) {
            return false;
        }

        // If it's not collection time yet, they should be idle
        if (!isCollectionTime) {
            return false;
        }

        return false;
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
        } else if (getJobType().equals(JOB_GROCER)) {
            // Dynamic work area for grocers based on collection status
            if (isGrocerCurrentlyCollecting()) {
                // Large area when collecting
                return deltaX <= GROCER_COLLECTION_RADIUS_X && deltaZ <= GROCER_COLLECTION_RADIUS_Z && deltaY <= GROCER_COLLECTION_RADIUS_Y;
            } else {
                // Small area when idle
                return deltaX <= GROCER_IDLE_RADIUS_X && deltaZ <= GROCER_IDLE_RADIUS_Z && deltaY <= GROCER_IDLE_RADIUS_Y;
            }
        } else if (getJobType().equals(JOB_GUARD)) {
            // Guards have large patrol areas
            return deltaX <= GUARD_WORK_RADIUS_X && deltaZ <= GUARD_WORK_RADIUS_Z && deltaY <= GUARD_WORK_RADIUS_Y;
        }

        return true; // Default: no restrictions for unknown job types
    }

    public boolean shouldBeAtWorkArea() {
        // Should be at work area during duty time
        if (!hasJob()) {
            return false;
        }

        if (getJobType().equals(JOB_GUARD)) {
            // Guards use their own sleep system
            return !peasant.getGuardSystem().shouldSleep() && !peasant.needsFoodCollection();
        }

        // For other jobs, use normal logic
        if (peasant.shouldSleep() || peasant.needsFoodCollection()) {
            return false;
        }

        // For farmers, they must have returned to job block after food collection
        if (getJobType().equals(JOB_FARMER)) {
            return peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood();
        }

        // For grocers, they can work anytime during non-sleep hours
        if (getJobType().equals(JOB_GROCER)) {
            return true;
        }

        return false;
    }

    public boolean isTooFarFromWork() {
        if (!hasJob() || getJobBlockPos() == null) {
            return false; // Not working hours or no job
        }

        if (getJobType().equals(JOB_GUARD)) {
            // Guards use their own sleep system
            if (peasant.getGuardSystem().shouldSleep()) {
                return false;
            }
        } else if (peasant.shouldSleep()) {
            return false;
        }

        BlockPos jobBlockPos = getJobBlockPos();
        BlockPos currentPos = peasant.blockPosition();
        double distanceSquared = jobBlockPos.distSqr(currentPos);

        // Return to work if too far (based on job type)
        if (getJobType().equals(JOB_FARMER)) {
            return distanceSquared > ((FARMER_WORK_RADIUS_X + 10) * (FARMER_WORK_RADIUS_X + 10));
        } else if (getJobType().equals(JOB_GROCER)) {
            // Dynamic distance check for grocers
            if (isGrocerCurrentlyCollecting()) {
                return distanceSquared > ((GROCER_COLLECTION_RADIUS_X + 10) * (GROCER_COLLECTION_RADIUS_X + 10));
            } else {
                return distanceSquared > ((GROCER_IDLE_RADIUS_X + 10) * (GROCER_IDLE_RADIUS_X + 10));
            }
        } else if (getJobType().equals(JOB_GUARD)) {
            return distanceSquared > ((GUARD_WORK_RADIUS_X + 10) * (GUARD_WORK_RADIUS_X + 10));
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
        // Remove job block warning when NPC is removed
        JobWarningSystem.removeJobBlockWarning(peasant.getUUID());
    }

    private void updateNameWithJob() {
        if (peasant.hasCustomName()) {
            String currentName = peasant.getCustomName().getString();
            String baseName = currentName;

            // Remove existing job title if present
            if (currentName.startsWith("Farmer ")) {
                baseName = currentName.substring(7); // Remove "Farmer "
            } else if (currentName.startsWith("Grocer ")) {
                baseName = currentName.substring(7); // Remove "Grocer "
            } else if (currentName.startsWith("Guard ")) {
                baseName = currentName.substring(6); // Remove "Guard "
            }

            // Add new job title
            String newName = baseName;
            if (getJobType().equals(JOB_FARMER)) {
                newName = "Farmer " + baseName;
            } else if (getJobType().equals(JOB_GROCER)) {
                newName = "Grocer " + baseName;
            } else if (getJobType().equals(JOB_GUARD)) {
                newName = "Guard " + baseName;
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