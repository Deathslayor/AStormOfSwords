package net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner.CharcoalBurnerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmingSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.lumberjack.LumberjackSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
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
    public static final String JOB_GROCER          = "grocer";
    public static final String JOB_FARMER          = "farmer";
    public static final String JOB_GUARD           = "guard";
    public static final String JOB_MINER           = "miner";
    public static final String JOB_SMELTER         = "smelter";
    public static final String JOB_CATTLE_HERDER   = "cattle_herder";
    public static final String JOB_CHICKEN_BREEDER = "chicken_breeder";
    public static final String JOB_PIG_BREEDER     = "pig_breeder";
    public static final String JOB_SHEEP_HERDER    = "sheep_herder";
    public static final String JOB_BUTCHER         = "butcher";
    public static final String JOB_TANNER          = "tanner";
    public static final String JOB_TAILOR          = "tailor";
    public static final String JOB_BLACKSMITH      = "blacksmith";
    public static final String JOB_LUMBERJACK      = "lumberjack";
    public static final String JOB_CHARCOAL_BURNER = "charcoal_burner";
    public static final String JOB_NONE            = "";

    // Work area constants
    private static final int FARMER_WORK_RADIUS_X          = 40;
    private static final int FARMER_WORK_RADIUS_Z          = 40;
    private static final int FARMER_WORK_RADIUS_Y          = 16;
    private static final int GROCER_COLLECTION_RADIUS_X    = 128;
    private static final int GROCER_COLLECTION_RADIUS_Z    = 128;
    private static final int GROCER_COLLECTION_RADIUS_Y    = 32;
    private static final int GROCER_IDLE_RADIUS_X          = 5;
    private static final int GROCER_IDLE_RADIUS_Z          = 5;
    private static final int GROCER_IDLE_RADIUS_Y          = 16;
    private static final int GUARD_WORK_RADIUS_X           = 96;
    private static final int GUARD_WORK_RADIUS_Z           = 96;
    private static final int GUARD_WORK_RADIUS_Y           = 32;
    private static final int MINER_WORK_RADIUS_X           = 64;
    private static final int MINER_WORK_RADIUS_Z           = 64;
    private static final int MINER_WORK_RADIUS_Y           = 48;
    private static final int SMELTER_WORK_RADIUS_X         = 8;
    private static final int SMELTER_WORK_RADIUS_Z         = 8;
    private static final int SMELTER_WORK_RADIUS_Y         = 16;
    private static final int ANIMAL_HERDER_WORK_RADIUS_X   = 16;
    private static final int ANIMAL_HERDER_WORK_RADIUS_Z   = 16;
    private static final int ANIMAL_HERDER_WORK_RADIUS_Y   = 16;
    private static final int BUTCHER_IDLE_RADIUS_X         = 5;
    private static final int BUTCHER_IDLE_RADIUS_Z         = 5;
    private static final int BUTCHER_IDLE_RADIUS_Y         = 16;
    private static final int TANNER_IDLE_RADIUS_X          = 5;
    private static final int TANNER_IDLE_RADIUS_Z          = 5;
    private static final int TANNER_IDLE_RADIUS_Y          = 16;
    private static final int TAILOR_IDLE_RADIUS_X          = 5;
    private static final int TAILOR_IDLE_RADIUS_Z          = 5;
    private static final int TAILOR_IDLE_RADIUS_Y          = 16;
    private static final int BLACKSMITH_IDLE_RADIUS_X      = 5;
    private static final int BLACKSMITH_IDLE_RADIUS_Z      = 5;
    private static final int BLACKSMITH_IDLE_RADIUS_Y      = 16;
    private static final int LUMBERJACK_WORK_RADIUS_X      = 96;
    private static final int LUMBERJACK_WORK_RADIUS_Z      = 96;
    private static final int LUMBERJACK_WORK_RADIUS_Y      = 32;
    private static final int CHARCOAL_BURNER_WORK_RADIUS_X = 8;
    private static final int CHARCOAL_BURNER_WORK_RADIUS_Z = 8;
    private static final int CHARCOAL_BURNER_WORK_RADIUS_Y = 16;

    // Job block reservations
    private static final Map<BlockPos, UUID> jobBlockReservations    = new HashMap<>();
    private static final Map<UUID, Long>     jobReservationTimestamps = new HashMap<>();
    private static final long JOB_RESERVATION_TIMEOUT = 12000;

    private int warningBroadcastTimer = 0;

    public JobSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    // ── Tick ─────────────────────────────────────────────────────────────────

    public void tick() {
        if (hasJob() && getJobBlockPos() != null) {
            if (!isJobBlockValid()) {
                loseJob();
                return;
            }
        }

        if (!peasant.level().isClientSide && shouldBeAtWorkArea() && isTooFarFromWork() && !peasant.isSleeping()) {
            peasant.getNavigation().stop();
            BlockPos workCenter = getWorkCenter();
            peasant.getNavigation().moveTo(workCenter.getX(), workCenter.getY(), workCenter.getZ(), 0.8D);
        }

        if (!peasant.level().isClientSide && hasJob() && getJobBlockPos() != null) {
            warningBroadcastTimer++;
            if (warningBroadcastTimer >= 40) {
                JobWarningSystem.broadcastJobBlockInUse(peasant.getUUID(), getJobType(), getJobBlockPos());
                warningBroadcastTimer = 0;
            }
        } else {
            warningBroadcastTimer = 0;
        }
    }

    // ── Job block validation ──────────────────────────────────────────────────

    private boolean isJobBlockValid() {
        BlockPos jobBlockPos = getJobBlockPos();
        if (jobBlockPos == null) return false;
        BlockState jobBlockState = peasant.level().getBlockState(jobBlockPos);
        if (getJobType().equals(JOB_FARMER))          return jobBlockState.is(ModBLocks.FARMER_BARREL.get());
        if (getJobType().equals(JOB_GROCER))          return jobBlockState.is(ModBLocks.GROCER_BARREL.get());
        if (getJobType().equals(JOB_BUTCHER))         return jobBlockState.is(ModBLocks.BUTCHER_BARREL.get());
        if (getJobType().equals(JOB_TANNER))          return jobBlockState.is(ModBLocks.TANNER_BARREL.get());
        if (getJobType().equals(JOB_TAILOR))          return jobBlockState.is(ModBLocks.TAILOR_BARREL.get());
        if (getJobType().equals(JOB_BLACKSMITH))      return jobBlockState.is(ModBLocks.BLACKSMITH_BARREL.get());
        if (getJobType().equals(JOB_GUARD))           return jobBlockState.is(ModBLocks.GUARD_BARREL.get());
        if (getJobType().equals(JOB_MINER))           return jobBlockState.is(ModBLocks.MINER_BARREL.get());
        if (getJobType().equals(JOB_SMELTER))         return jobBlockState.is(ModBLocks.SMELTER_BARREL.get());
        if (getJobType().equals(JOB_CATTLE_HERDER))   return jobBlockState.is(ModBLocks.CATTLE_HERDER_BARREL.get());
        if (getJobType().equals(JOB_CHICKEN_BREEDER)) return jobBlockState.is(ModBLocks.CHICKEN_BREEDER_BARREL.get());
        if (getJobType().equals(JOB_PIG_BREEDER))     return jobBlockState.is(ModBLocks.PIG_BREEDER_BARREL.get());
        if (getJobType().equals(JOB_SHEEP_HERDER))    return jobBlockState.is(ModBLocks.SHEEP_HERDER_BARREL.get());
        if (getJobType().equals(JOB_LUMBERJACK))      return jobBlockState.is(ModBLocks.LUMBERJACK_BARREL.get());
        if (getJobType().equals(JOB_CHARCOAL_BURNER)) return jobBlockState.is(ModBLocks.CHARCOAL_BURNER_BARREL.get());
        return false;
    }

    // ── Lose job ──────────────────────────────────────────────────────────────

    private void loseJob() {
        String oldJobType = getJobType();

        setJobType(JOB_NONE);
        setJobBlockPos(null);
        releaseJobBlockReservation(peasant.getUUID());
        JobWarningSystem.removeJobBlockWarning(peasant.getUUID());

        if (oldJobType.equals(JOB_FARMER)) {
            peasant.getFarmingSystem().setCurrentFarmState(FarmingSystem.FarmState.NEEDS_FARM_SETUP);
        } else if (oldJobType.equals(JOB_TANNER)) {
            peasant.getTannerSystem().setCurrentState(
                    net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerSystem.TannerState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_TAILOR)) {
            peasant.getTailorSystem().setCurrentState(
                    net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorSystem.TailorState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_GUARD)) {
            peasant.getGuardSystem().onRemove();
        } else if (oldJobType.equals(JOB_MINER)) {
            peasant.getMinerSystem().setCurrentMinerState(MinerSystem.MinerState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_SMELTER)) {
            peasant.getSmelterSystem().setCurrentState(
                    net.darkflameproduction.agotmod.entity.custom.npc.system.smelter.SmelterSystem.SmelterState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_CHARCOAL_BURNER)) {
            peasant.getCharcoalBurnerSystem().setCurrentState(
                    CharcoalBurnerSystem.CharcoalBurnerState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_CATTLE_HERDER)
                || oldJobType.equals(JOB_CHICKEN_BREEDER)
                || oldJobType.equals(JOB_PIG_BREEDER)
                || oldJobType.equals(JOB_SHEEP_HERDER)) {
            peasant.getAnimalHerderSystem().setBreedingItemCount(0);
        } else if (oldJobType.equals(JOB_LUMBERJACK)) {
            peasant.getLumberjackSystem().setCurrentState(LumberjackSystem.LumberjackState.GOING_TO_JOB_BLOCK);
        }
    }

    // ── Set job type ──────────────────────────────────────────────────────────

    public String getJobType() {
        return peasant.getEntityData().get(peasant.getJobTypeAccessor());
    }

    public void setJobType(String jobType) {
        setJobType(jobType, true);
    }

    public void setJobType(String jobType, boolean giveEquipment) {
        String oldJob = getJobType();
        peasant.getEntityData().set(peasant.getJobTypeAccessor(), jobType);

        if (!oldJob.equals(jobType)) {
            // Update name via NameSystem
            if (jobType.isEmpty()) {
                peasant.getNameSystem().onJobLost();
            } else {
                peasant.getNameSystem().onJobAssigned(jobType, peasant.level().getRandom());
            }

            if (giveEquipment) {
                giveJobEquipment(oldJob, jobType);
            }

            if (jobType.isEmpty() && !oldJob.isEmpty()) {
                JobWarningSystem.removeJobBlockWarning(peasant.getUUID());
            }
        }
    }

    // ── Equipment ─────────────────────────────────────────────────────────────

    private void giveJobEquipment(String oldJob, String newJob) {
        if (peasant.level().isClientSide) return;
        if (newJob.equals(JOB_FARMER))      giveIronHoe();
        else if (newJob.equals(JOB_MINER))  giveMinerEquipment();
        else if (newJob.equals(JOB_LUMBERJACK)) giveLumberjackEquipment();
    }

    private void giveIronHoe() {
        if (hasHoe()) return;
        ItemStack ironHoe = new ItemStack(Items.IRON_HOE);
        if (peasant.getInventorySystem().addItem(ironHoe)) {
            peasant.getInventorySystem().forceEquipmentCheck();
        } else if (peasant.getInventorySystem().getMainHandItem().isEmpty()) {
            peasant.getInventorySystem().setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND, ironHoe);
        } else if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            peasant.spawnAtLocation(serverLevel, ironHoe);
        }
    }

    private void giveLumberjackEquipment() {
        if (hasAxe()) return;
        ItemStack ironAxe = new ItemStack(Items.IRON_AXE);
        if (peasant.getInventorySystem().addItem(ironAxe)) {
            peasant.getInventorySystem().forceEquipmentCheck();
        } else if (peasant.getInventorySystem().getMainHandItem().isEmpty()) {
            peasant.getInventorySystem().setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND, ironAxe);
        } else if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            peasant.spawnAtLocation(serverLevel, ironAxe);
        }
    }

    private void giveMinerEquipment() {
        if (!hasPickaxe()) {
            ItemStack ironPickaxe = new ItemStack(Items.IRON_PICKAXE);
            if (peasant.getInventorySystem().addItem(ironPickaxe)) {
                peasant.getInventorySystem().forceEquipmentCheck();
            } else if (peasant.getInventorySystem().getMainHandItem().isEmpty()) {
                peasant.getInventorySystem().setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND, ironPickaxe);
            } else if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                peasant.spawnAtLocation(serverLevel, ironPickaxe);
            }
        }
        giveTorches();
    }

    private boolean hasHoe() {
        if (peasant.getInventorySystem().getMainHandItem().getItem() instanceof net.minecraft.world.item.HoeItem ||
                peasant.getInventorySystem().getOffhandItem().getItem() instanceof net.minecraft.world.item.HoeItem) return true;
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++)
            if (inventory.getItem(i).getItem() instanceof net.minecraft.world.item.HoeItem) return true;
        return false;
    }

    private boolean hasAxe() {
        if (peasant.getInventorySystem().getMainHandItem().getItem() instanceof net.minecraft.world.item.AxeItem ||
                peasant.getInventorySystem().getOffhandItem().getItem() instanceof net.minecraft.world.item.AxeItem) return true;
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++)
            if (inventory.getItem(i).getItem() instanceof net.minecraft.world.item.AxeItem) return true;
        return false;
    }

    private boolean hasPickaxe() {
        if (peasant.getInventorySystem().getMainHandItem().getItem() instanceof net.minecraft.world.item.PickaxeItem ||
                peasant.getInventorySystem().getOffhandItem().getItem() instanceof net.minecraft.world.item.PickaxeItem) return true;
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++)
            if (inventory.getItem(i).getItem() instanceof net.minecraft.world.item.PickaxeItem) return true;
        return false;
    }

    private void giveTorches() {
        ItemStack torches = new ItemStack(Items.TORCH, 32);
        if (!peasant.getInventorySystem().addItem(torches)) {
            if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                peasant.spawnAtLocation(serverLevel, torches);
            }
        }
    }

    // ── Work area ─────────────────────────────────────────────────────────────

    public boolean hasJob() {
        return !getJobType().isEmpty();
    }

    public BlockPos getJobBlockPos() {
        return peasant.getEntityData().get(peasant.getJobBlockPosAccessor()).orElse(null);
    }

    public void setJobBlockPos(BlockPos pos) {
        peasant.getEntityData().set(peasant.getJobBlockPosAccessor(), Optional.ofNullable(pos));
    }

    private boolean isGrocerCurrentlyCollecting() {
        return false;
    }

    public boolean isWithinWorkArea(BlockPos pos) {
        if (!hasJob() || getJobBlockPos() == null) return true;
        BlockPos jobBlockPos = getJobBlockPos();
        int deltaX = Math.abs(pos.getX() - jobBlockPos.getX());
        int deltaZ = Math.abs(pos.getZ() - jobBlockPos.getZ());
        int deltaY = Math.abs(pos.getY() - jobBlockPos.getY());
        if (getJobType().equals(JOB_FARMER))
            return deltaX <= FARMER_WORK_RADIUS_X && deltaZ <= FARMER_WORK_RADIUS_Z && deltaY <= FARMER_WORK_RADIUS_Y;
        if (getJobType().equals(JOB_GROCER)) {
            if (isGrocerCurrentlyCollecting())
                return deltaX <= GROCER_COLLECTION_RADIUS_X && deltaZ <= GROCER_COLLECTION_RADIUS_Z && deltaY <= GROCER_COLLECTION_RADIUS_Y;
            else
                return deltaX <= GROCER_IDLE_RADIUS_X && deltaZ <= GROCER_IDLE_RADIUS_Z && deltaY <= GROCER_IDLE_RADIUS_Y;
        }
        if (getJobType().equals(JOB_BUTCHER))    return deltaX <= BUTCHER_IDLE_RADIUS_X    && deltaZ <= BUTCHER_IDLE_RADIUS_Z    && deltaY <= BUTCHER_IDLE_RADIUS_Y;
        if (getJobType().equals(JOB_TANNER))     return deltaX <= TANNER_IDLE_RADIUS_X     && deltaZ <= TANNER_IDLE_RADIUS_Z     && deltaY <= TANNER_IDLE_RADIUS_Y;
        if (getJobType().equals(JOB_TAILOR))     return deltaX <= TAILOR_IDLE_RADIUS_X     && deltaZ <= TAILOR_IDLE_RADIUS_Z     && deltaY <= TAILOR_IDLE_RADIUS_Y;
        if (getJobType().equals(JOB_BLACKSMITH)) return deltaX <= BLACKSMITH_IDLE_RADIUS_X && deltaZ <= BLACKSMITH_IDLE_RADIUS_Z && deltaY <= BLACKSMITH_IDLE_RADIUS_Y;
        if (getJobType().equals(JOB_GUARD))
            return deltaX <= GUARD_WORK_RADIUS_X && deltaZ <= GUARD_WORK_RADIUS_Z && deltaY <= GUARD_WORK_RADIUS_Y;
        if (getJobType().equals(JOB_MINER)) return true;
        if (getJobType().equals(JOB_SMELTER))
            return deltaX <= SMELTER_WORK_RADIUS_X && deltaZ <= SMELTER_WORK_RADIUS_Z && deltaY <= SMELTER_WORK_RADIUS_Y;
        if (getJobType().equals(JOB_CATTLE_HERDER)
                || getJobType().equals(JOB_CHICKEN_BREEDER)
                || getJobType().equals(JOB_PIG_BREEDER)
                || getJobType().equals(JOB_SHEEP_HERDER))
            return deltaX <= ANIMAL_HERDER_WORK_RADIUS_X && deltaZ <= ANIMAL_HERDER_WORK_RADIUS_Z && deltaY <= ANIMAL_HERDER_WORK_RADIUS_Y;
        if (getJobType().equals(JOB_LUMBERJACK))
            return deltaX <= LUMBERJACK_WORK_RADIUS_X && deltaZ <= LUMBERJACK_WORK_RADIUS_Z && deltaY <= LUMBERJACK_WORK_RADIUS_Y;
        if (getJobType().equals(JOB_CHARCOAL_BURNER))
            return deltaX <= CHARCOAL_BURNER_WORK_RADIUS_X && deltaZ <= CHARCOAL_BURNER_WORK_RADIUS_Z && deltaY <= CHARCOAL_BURNER_WORK_RADIUS_Y;
        return true;
    }

    public boolean shouldBeAtWorkArea() {
        if (!hasJob()) return false;
        if (getJobType().equals(JOB_GUARD))
            return !peasant.getGuardSystem().shouldSleep() && !peasant.needsFoodCollection();
        if (peasant.shouldSleep() || peasant.needsFoodCollection()) return false;
        if (getJobType().equals(JOB_FARMER))     return peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood();
        if (getJobType().equals(JOB_GROCER))     return true;
        if (getJobType().equals(JOB_BUTCHER))    return true;
        if (getJobType().equals(JOB_TANNER))     return true;
        if (getJobType().equals(JOB_TAILOR))     return true;
        if (getJobType().equals(JOB_BLACKSMITH)) return true;
        if (getJobType().equals(JOB_MINER))      return true;
        if (getJobType().equals(JOB_SMELTER))    return true;
        if (getJobType().equals(JOB_CATTLE_HERDER)
                || getJobType().equals(JOB_CHICKEN_BREEDER)
                || getJobType().equals(JOB_PIG_BREEDER)
                || getJobType().equals(JOB_SHEEP_HERDER)) return true;
        if (getJobType().equals(JOB_LUMBERJACK))      return true;
        if (getJobType().equals(JOB_CHARCOAL_BURNER)) return true;
        return false;
    }

    public boolean isTooFarFromWork() {
        if (!hasJob() || getJobBlockPos() == null) return false;
        if (getJobType().equals(JOB_GUARD)) {
            if (peasant.getGuardSystem().shouldSleep()) return false;
        } else if (peasant.shouldSleep()) {
            return false;
        }
        BlockPos jobBlockPos   = getJobBlockPos();
        BlockPos currentPos    = peasant.blockPosition();
        double distanceSquared = jobBlockPos.distSqr(currentPos);
        if (getJobType().equals(JOB_FARMER))
            return distanceSquared > ((FARMER_WORK_RADIUS_X + 10) * (FARMER_WORK_RADIUS_X + 10));
        if (getJobType().equals(JOB_GROCER)) {
            if (isGrocerCurrentlyCollecting())
                return distanceSquared > ((GROCER_COLLECTION_RADIUS_X + 10) * (GROCER_COLLECTION_RADIUS_X + 10));
            else
                return distanceSquared > ((GROCER_IDLE_RADIUS_X + 10) * (GROCER_IDLE_RADIUS_X + 10));
        }
        if (getJobType().equals(JOB_BUTCHER))
            return distanceSquared > ((BUTCHER_IDLE_RADIUS_X + 10) * (BUTCHER_IDLE_RADIUS_X + 10));
        if (getJobType().equals(JOB_TANNER))
            return distanceSquared > ((TANNER_IDLE_RADIUS_X + 10) * (TANNER_IDLE_RADIUS_X + 10));
        if (getJobType().equals(JOB_TAILOR))
            return distanceSquared > ((TAILOR_IDLE_RADIUS_X + 10) * (TAILOR_IDLE_RADIUS_X + 10));
        if (getJobType().equals(JOB_BLACKSMITH))
            return distanceSquared > ((BLACKSMITH_IDLE_RADIUS_X + 10) * (BLACKSMITH_IDLE_RADIUS_X + 10));
        if (getJobType().equals(JOB_GUARD))
            return distanceSquared > ((GUARD_WORK_RADIUS_X + 10) * (GUARD_WORK_RADIUS_X + 10));
        if (getJobType().equals(JOB_MINER)) return false;
        if (getJobType().equals(JOB_SMELTER))
            return distanceSquared > ((SMELTER_WORK_RADIUS_X + 10) * (SMELTER_WORK_RADIUS_X + 10));
        if (getJobType().equals(JOB_CATTLE_HERDER)
                || getJobType().equals(JOB_CHICKEN_BREEDER)
                || getJobType().equals(JOB_PIG_BREEDER)
                || getJobType().equals(JOB_SHEEP_HERDER))
            return distanceSquared > ((ANIMAL_HERDER_WORK_RADIUS_X + 10) * (ANIMAL_HERDER_WORK_RADIUS_X + 10));
        if (getJobType().equals(JOB_LUMBERJACK))
            return distanceSquared > ((LUMBERJACK_WORK_RADIUS_X + 10) * (LUMBERJACK_WORK_RADIUS_X + 10));
        if (getJobType().equals(JOB_CHARCOAL_BURNER))
            return distanceSquared > ((CHARCOAL_BURNER_WORK_RADIUS_X + 10) * (CHARCOAL_BURNER_WORK_RADIUS_X + 10));
        return false;
    }

    public BlockPos getWorkCenter() {
        if (hasJob() && getJobBlockPos() != null) return getJobBlockPos();
        return peasant.getHomeCenter();
    }

    public void onRemove() {
        releaseJobBlockReservation(peasant.getUUID());
        JobWarningSystem.removeJobBlockWarning(peasant.getUUID());
    }

    // ── Job block reservations ────────────────────────────────────────────────

    public static boolean isJobBlockReserved(BlockPos blockPos, UUID excludeUUID) {
        cleanupExpiredJobReservations();
        UUID reservedBy = jobBlockReservations.get(blockPos);
        return reservedBy != null && !reservedBy.equals(excludeUUID);
    }

    public static boolean reserveJobBlock(BlockPos blockPos, UUID peasantUUID) {
        cleanupExpiredJobReservations();
        UUID currentReserver = jobBlockReservations.get(blockPos);
        if (currentReserver != null && !currentReserver.equals(peasantUUID)) return false;
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

    // ── Save / Load ───────────────────────────────────────────────────────────

    public void saveData(CompoundTag compound) {
        compound.putString("JobType", getJobType());
        if (getJobBlockPos() != null) {
            compound.putInt("JobBlockX", getJobBlockPos().getX());
            compound.putInt("JobBlockY", getJobBlockPos().getY());
            compound.putInt("JobBlockZ", getJobBlockPos().getZ());
        }
    }

    public void loadData(CompoundTag compound) {
        setJobType(compound.getString("JobType"), false);
        if (compound.contains("JobBlockX")) {
            BlockPos jobBlockPos = new BlockPos(
                    compound.getInt("JobBlockX"),
                    compound.getInt("JobBlockY"),
                    compound.getInt("JobBlockZ"));
            setJobBlockPos(jobBlockPos);
            if (hasJob()) reserveJobBlock(jobBlockPos, peasant.getUUID());
        }
    }
}