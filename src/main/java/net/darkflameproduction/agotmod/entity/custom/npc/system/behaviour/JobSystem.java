package net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner.CharcoalBurnerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmingSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.lumberjack.LumberjackSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.quarry.QuarrySystem;
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
    public static final String JOB_QUARRY          = "quarry";
    public static final String JOB_SMELTER         = "smelter";
    public static final String JOB_CATTLE_HERDER   = "cattle_herder";
    public static final String JOB_CHICKEN_BREEDER = "chicken_breeder";
    public static final String JOB_PIG_BREEDER     = "pig_breeder";
    public static final String JOB_SHEEP_HERDER    = "sheep_herder";
    public static final String JOB_BUTCHER         = "butcher";
    public static final String JOB_TANNER          = "tanner";
    public static final String JOB_TAILOR          = "tailor";
    public static final String JOB_BLACKSMITH      = "blacksmith";
    public static final String JOB_ARMORSMITH      = "armorsmith";
    public static final String JOB_SWORDSMITH      = "swordsmith";
    public static final String JOB_BAKER           = "baker";
    public static final String JOB_STONEMASON      = "stonemason";
    public static final String JOB_INNKEEPER       = "innkeeper";
    public static final String JOB_ALEHOUSE_KEEPER = "alehouse_keeper";

    /** The tavern keepers: awake while their tavern is open, asleep while it's closed (NpcSchedule). */
    public static boolean isTavernKeeper(String job) {
        return JOB_INNKEEPER.equals(job) || JOB_ALEHOUSE_KEEPER.equals(job);
    }

    /**
     * The jobs that work standing at their barrel, crafting for the town's supply chain or the smiths' work: they
     * keep to a few blocks around it.
     */
    public static boolean isBarrelCrafter(String job) {
        return isSmith(job) || JOB_BAKER.equals(job) || JOB_STONEMASON.equals(job) || isTavernKeeper(job);
    }

    /** The smiths: the blacksmith, and the armorsmith and swordsmith who make the high tier armour and weapons. */
    public static boolean isSmith(String job) {
        return JOB_BLACKSMITH.equals(job) || JOB_ARMORSMITH.equals(job) || JOB_SWORDSMITH.equals(job);
    }
    public static final String JOB_LUMBERJACK      = "lumberjack";
    public static final String JOB_CHARCOAL_BURNER = "charcoal_burner";
    public static final String JOB_CARPENTER       = "carpenter";
    public static final String JOB_TRADER = "trader";
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
    private static final int CARPENTER_IDLE_RADIUS_X       = 5;
    private static final int CARPENTER_IDLE_RADIUS_Z       = 5;
    private static final int CARPENTER_IDLE_RADIUS_Y       = 16;
    private static final int TRADER_IDLE_RADIUS_X = 5;
    private static final int TRADER_IDLE_RADIUS_Z = 5;
    private static final int TRADER_IDLE_RADIUS_Y = 16;

    // Job block reservations
    private static final Map<BlockPos, UUID> jobBlockReservations    = new HashMap<>();
    private static final Map<UUID, Long>     jobReservationTimestamps = new HashMap<>();
    private static final long JOB_RESERVATION_TIMEOUT = 12000;

    private int warningBroadcastTimer = 0;

    public JobSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    // Ã¢â€â‚¬Ã¢â€â‚¬ Tick Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

    public void tick() {
        takeUpWaitingJob();

        if (hasJob() && getJobBlockPos() != null) {
            if (!isJobBlockValid()) {
                loseJob();
                return;
            }
        }

        if (workTeleportCooldown > 0) workTeleportCooldown--;
        if (!peasant.level().isClientSide && shouldBeAtWorkArea() && isTooFarFromWork() && !peasant.isSleeping()) {
            headToWork();
        } else {
            resetWorkProgress();
        }

        if (!peasant.level().isClientSide && hasJob() && getJobBlockPos() != null) {
            warningBroadcastTimer++;
            if (warningBroadcastTimer >= 40) {
                warningBroadcastTimer = 0;
                // Only one NPC may work a job block. If someone else holds the claim, this job isn't ours.
                if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel
                        && !JobClaims.get(serverLevel).confirm(serverLevel, getJobBlockPos(), peasant.getUUID())) {
                    net.darkflameproduction.agotmod.AGoTMod.LOGGER.info(
                            "[Jobs] {} gave up {} job at {} - that job block already belongs to another NPC",
                            peasant.getName().getString(), getJobType(), getJobBlockPos().toShortString());
                    loseJob();
                    return;
                }
                JobWarningSystem.broadcastJobBlockInUse(peasant.getUUID(), getJobType(), getJobBlockPos());
            }
        } else {
            warningBroadcastTimer = 0;
        }
    }

    // Ã¢â€â‚¬Ã¢â€â‚¬ Job block validation Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

    // ===== GETTING TO WORK =====

    private static final int WORK_CHECK_INTERVAL = 20;
    private static final int WORK_STUCK_TICKS = 20 * 30;        // no closer to the job block in 30 seconds: teleport
    private static final int WORK_FAILED_PATHS = 3;             // no path to the job block this many times running: teleport
    private static final int WORK_TELEPORT_COOLDOWN = 20 * 60;
    private static final int WORK_SPOT_RADIUS = 3;              // how far from the job block it may land

    private double closestToWorkSqr = Double.MAX_VALUE;
    private int ticksWithoutWorkProgress = 0;
    private int failedWorkPaths = 0;
    private int workTeleportCooldown = 0;

    /**
     * Out of its work area during work hours: walk back to the job block. A path is planned only when it has none
     * (it used to be planned again every tick). If it can't get there - no path, a path that can't reach it, or no
     * closer for WORK_STUCK_TICKS - it is teleported beside its job block.
     */
    private void headToWork() {
        if ((peasant.tickCount + peasant.getId()) % WORK_CHECK_INTERVAL != 0) return;
        BlockPos jobBlock = getJobBlockPos();
        if (jobBlock == null) return;
        // Busy with something that matters more - the walk to work waits
        if (peasant.getHungerSystem().isEating() || peasant.isInteractingWithPlayer() || peasant.isPassenger()
                || peasant.isRetreating() || (peasant.getTarget() != null && peasant.getTarget().isAlive())) {
            resetWorkProgress();
            return;
        }

        double distanceSqr = peasant.distanceToSqr(jobBlock.getX() + 0.5D, jobBlock.getY(), jobBlock.getZ() + 0.5D);
        if (closestToWorkSqr == Double.MAX_VALUE || Math.sqrt(distanceSqr) < Math.sqrt(closestToWorkSqr) - 1.0D) {
            closestToWorkSqr = distanceSqr;
            ticksWithoutWorkProgress = 0;
        } else {
            ticksWithoutWorkProgress += WORK_CHECK_INTERVAL;
        }

        if (!peasant.getNavigation().isInProgress()) {
            boolean planned = peasant.getNavigation().moveTo(jobBlock.getX() + 0.5D, jobBlock.getY(), jobBlock.getZ() + 0.5D, 1.0D);
            net.minecraft.world.level.pathfinder.Path path = peasant.getNavigation().getPath();
            if (!planned || path == null || !path.canReach()) failedWorkPaths++;
            else failedWorkPaths = 0;
        }

        if ((ticksWithoutWorkProgress >= WORK_STUCK_TICKS || failedWorkPaths >= WORK_FAILED_PATHS)
                && workTeleportCooldown <= 0) {
            teleportToWork(jobBlock);
        }
    }

    private void resetWorkProgress() {
        closestToWorkSqr = Double.MAX_VALUE;
        ticksWithoutWorkProgress = 0;
        failedWorkPaths = 0;
    }

    /** Puts the NPC on a free spot beside its job block - the nearest one. False if there is none (or it isn't loaded). */
    public boolean teleportToWork(BlockPos jobBlock) {
        if (!peasant.level().isLoaded(jobBlock)) return false;
        BlockPos spot = null;
        double best = Double.MAX_VALUE;
        for (int dx = -WORK_SPOT_RADIUS; dx <= WORK_SPOT_RADIUS; dx++) {
            for (int dz = -WORK_SPOT_RADIUS; dz <= WORK_SPOT_RADIUS; dz++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dz == 0) continue;
                    BlockPos candidate = jobBlock.offset(dx, dy, dz);
                    double distance = candidate.distSqr(jobBlock);
                    if (distance >= best) continue;
                    if (!net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.BedAccess.canStandAt(peasant.level(), candidate)) continue;
                    if (net.darkflameproduction.agotmod.entity.custom.npc.NpcNodeEvaluator.isForbiddenFloor(
                            peasant.level().getBlockState(candidate.below()))) continue;
                    spot = candidate;
                    best = distance;
                }
            }
        }
        workTeleportCooldown = WORK_TELEPORT_COOLDOWN; // found or not: don't search every second
        resetWorkProgress();
        if (spot == null) return false;

        peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                net.minecraft.sounds.SoundEvents.ENDERMAN_TELEPORT, net.minecraft.sounds.SoundSource.NEUTRAL, 0.5F, 1.0F);
        peasant.getNavigation().stop();
        peasant.teleportTo(spot.getX() + 0.5D, spot.getY(), spot.getZ() + 0.5D);
        return true;
    }

    private static final int WAITING_JOB_CHECK_INTERVAL = 100;

    /**
     * A jobless NPC's town may have given it a job while it wasn't loaded: the job block is claimed in its name
     * (JobClaims). It takes that job up - once it is loaded, and the job block's chunk is too.
     */
    private void takeUpWaitingJob() {
        if (hasJob() || !peasant.isAdult()) return;
        if (!(peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel)) return;
        if ((peasant.tickCount + peasant.getId()) % WAITING_JOB_CHECK_INTERVAL != 0) return;

        BlockPos jobBlock = JobClaims.get(serverLevel).claimOf(serverLevel, peasant.getUUID());
        if (jobBlock == null || !serverLevel.isLoaded(jobBlock)) return;
        String jobType = net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity
                .getJobTypeFromBlock(serverLevel.getBlockState(jobBlock));
        if (jobType.equals(JOB_NONE)) {
            JobClaims.get(serverLevel).release(jobBlock, peasant.getUUID()); // the job block is gone
            return;
        }
        setJobType(jobType);
        setJobBlockPos(jobBlock);
    }

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
        if (getJobType().equals(JOB_ARMORSMITH))      return jobBlockState.is(ModBLocks.ARMORSMITH_BARREL.get());
        if (getJobType().equals(JOB_SWORDSMITH))      return jobBlockState.is(ModBLocks.SWORDSMITH_BARREL.get());
        if (getJobType().equals(JOB_BAKER))           return jobBlockState.is(ModBLocks.BAKER_BARREL.get());
        if (getJobType().equals(JOB_STONEMASON))      return jobBlockState.is(ModBLocks.STONEMASON_BARREL.get());
        if (getJobType().equals(JOB_INNKEEPER))       return jobBlockState.is(ModBLocks.INNKEEPER_BARREL.get());
        if (getJobType().equals(JOB_ALEHOUSE_KEEPER)) return jobBlockState.is(ModBLocks.ALEHOUSE_BARREL.get());
        if (getJobType().equals(JOB_GUARD))         return jobBlockState.is(ModBLocks.GUARD_BARREL.get());
        if (getJobType().equals(JOB_MINER))           return jobBlockState.is(ModBLocks.MINER_BARREL.get());
        if (getJobType().equals(JOB_QUARRY))          return jobBlockState.is(ModBLocks.QUARRY_BARREL.get());
        if (getJobType().equals(JOB_SMELTER))         return jobBlockState.is(ModBLocks.SMELTER_BARREL.get());
        if (getJobType().equals(JOB_CATTLE_HERDER))   return jobBlockState.is(ModBLocks.CATTLE_HERDER_BARREL.get());
        if (getJobType().equals(JOB_CHICKEN_BREEDER)) return jobBlockState.is(ModBLocks.CHICKEN_BREEDER_BARREL.get());
        if (getJobType().equals(JOB_PIG_BREEDER))     return jobBlockState.is(ModBLocks.PIG_BREEDER_BARREL.get());
        if (getJobType().equals(JOB_SHEEP_HERDER))    return jobBlockState.is(ModBLocks.SHEEP_HERDER_BARREL.get());
        if (getJobType().equals(JOB_LUMBERJACK))      return jobBlockState.is(ModBLocks.LUMBERJACK_BARREL.get());
        if (getJobType().equals(JOB_CHARCOAL_BURNER)) return jobBlockState.is(ModBLocks.CHARCOAL_BURNER_BARREL.get());
        if (getJobType().equals(JOB_CARPENTER))       return jobBlockState.is(ModBLocks.CARPENTER_BARREL.get());
        if (getJobType().equals(JOB_TRADER)) return jobBlockState.is(ModBLocks.TRADER_BARREL.get());
        return false;
    }

    // Ã¢â€â‚¬Ã¢â€â‚¬ Lose job Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

    private void loseJob() {
        String oldJobType = getJobType();

        if (getJobBlockPos() != null && peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            JobClaims.get(serverLevel).release(getJobBlockPos(), peasant.getUUID());
        }
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
        } else if (oldJobType.equals(JOB_QUARRY)) {
            peasant.getQuarrySystem().setCurrentQuarryState(QuarrySystem.QuarryState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_SMELTER)) {
            peasant.getSmelterSystem().setCurrentState(
                    net.darkflameproduction.agotmod.entity.custom.npc.system.smelter.SmelterSystem.SmelterState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_CHARCOAL_BURNER)) {
            peasant.getCharcoalBurnerSystem().setCurrentState(
                    CharcoalBurnerSystem.CharcoalBurnerState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_CARPENTER)) {
            peasant.getCarpenterSystem().setCurrentState(
                    CarpenterSystem.CarpenterState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_CATTLE_HERDER)
                || oldJobType.equals(JOB_CHICKEN_BREEDER)
                || oldJobType.equals(JOB_PIG_BREEDER)
                || oldJobType.equals(JOB_SHEEP_HERDER)) {
            peasant.getAnimalHerderSystem().setBreedingItemCount(0);
        } else if (oldJobType.equals(JOB_TRADER)) {
            peasant.getTraderSystem().setCurrentState(
                    net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderSystem.TraderState.GOING_TO_JOB_BLOCK);
        } else if (oldJobType.equals(JOB_LUMBERJACK)) {
            peasant.getLumberjackSystem().setCurrentState(LumberjackSystem.LumberjackState.GOING_TO_JOB_BLOCK);
        }
    }

    // Ã¢â€â‚¬Ã¢â€â‚¬ Set job type Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

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

    // Ã¢â€â‚¬Ã¢â€â‚¬ Equipment Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

    // Workers no longer get their tools for free: they request one and the town's blacksmith makes it
    // (see JobTools and CollectToolGoal). Miners still bring their own torches.
    private void giveJobEquipment(String oldJob, String newJob) {
        if (peasant.level().isClientSide) return;
        if (newJob.equals(JOB_MINER) || newJob.equals(JOB_QUARRY)) giveTorches();
        giveStarterTool(newJob);
        JobTools.updateRequest(peasant);
    }

    /** Set on the residents a village is built with: their first job that needs a tool comes with one. */
    public static final String STARTER_TOOL_TAG = "StarterTool";

    /**
     * A village founder starts out with its job's tool (iron, which works at normal speed) - a village built with
     * the world has no blacksmith making tools yet, and its workers would wait for them. Once only: later tools
     * come from the blacksmith like everyone else's.
     */
    private void giveStarterTool(String job) {
        if (!peasant.getPersistentData().getBoolean(STARTER_TOOL_TAG)) return;
        JobTools.ToolKind kind = JobTools.requiredTool(job);
        if (kind == null) return; // keep the gift for a job that needs a tool
        if (!JobTools.findTool(peasant, kind).isEmpty()) {
            peasant.getPersistentData().remove(STARTER_TOOL_TAG);
            return;
        }
        ItemStack tool = switch (kind) {
            case HOE -> new ItemStack(Items.IRON_HOE);
            case PICKAXE -> new ItemStack(Items.IRON_PICKAXE);
            case AXE -> new ItemStack(Items.IRON_AXE);
            case HAMMER -> new ItemStack(net.darkflameproduction.agotmod.item.ModItems.HAMMER.get());
        };
        if (!peasant.getInventorySystem().addItem(tool)) peasant.spawnAtLocation(tool);
        peasant.getPersistentData().remove(STARTER_TOOL_TAG);
    }








    private void giveTorches() {
        ItemStack torches = new ItemStack(Items.TORCH, 32);
        if (!peasant.getInventorySystem().addItem(torches)) {
            if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                peasant.spawnAtLocation(torches);
            }
        }
    }

    // Ã¢â€â‚¬Ã¢â€â‚¬ Work area Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

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
        if (isBarrelCrafter(getJobType())) return deltaX <= BLACKSMITH_IDLE_RADIUS_X && deltaZ <= BLACKSMITH_IDLE_RADIUS_Z && deltaY <= BLACKSMITH_IDLE_RADIUS_Y;
        if (getJobType().equals(JOB_CARPENTER))  return deltaX <= CARPENTER_IDLE_RADIUS_X  && deltaZ <= CARPENTER_IDLE_RADIUS_Z  && deltaY <= CARPENTER_IDLE_RADIUS_Y;
        if (getJobType().equals(JOB_GUARD))
            return deltaX <= GUARD_WORK_RADIUS_X && deltaZ <= GUARD_WORK_RADIUS_Z && deltaY <= GUARD_WORK_RADIUS_Y;
        if (getJobType().equals(JOB_MINER)) return true;
        if (getJobType().equals(JOB_QUARRY)) return true;
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
        if (getJobType().equals(JOB_TRADER))
            return deltaX <= TRADER_IDLE_RADIUS_X && deltaZ <= TRADER_IDLE_RADIUS_Z && deltaY <= TRADER_IDLE_RADIUS_Y;
        return true;
    }

    public boolean shouldBeAtWorkArea() {
        if (!hasJob()) return false;
        if (getJobType().equals(JOB_GUARD))
            return !peasant.getGuardSystem().shouldSleep() && !peasant.needsFoodCollection();
        if (peasant.shouldSleep() || peasant.needsFoodCollection()) return false;
        if (peasant.isVisitingTavern()) return false; // done for the day, and off to the tavern
        if (getJobType().equals(JOB_FARMER))      return peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood();
        if (getJobType().equals(JOB_GROCER))      return true;
        if (getJobType().equals(JOB_BUTCHER))     return true;
        if (getJobType().equals(JOB_TANNER))      return true;
        if (getJobType().equals(JOB_TAILOR))      return true;
        if (isBarrelCrafter(getJobType()))  return true;
        if (getJobType().equals(JOB_CARPENTER))   return true;
        if (getJobType().equals(JOB_MINER))       return true;
        if (getJobType().equals(JOB_QUARRY))      return true;
        if (getJobType().equals(JOB_SMELTER))     return true;
        if (getJobType().equals(JOB_CATTLE_HERDER)
                || getJobType().equals(JOB_CHICKEN_BREEDER)
                || getJobType().equals(JOB_PIG_BREEDER)
                || getJobType().equals(JOB_SHEEP_HERDER)) return true;
        if (getJobType().equals(JOB_LUMBERJACK))      return true;
        if (getJobType().equals(JOB_CHARCOAL_BURNER)) return true;
        if (getJobType().equals(JOB_TRADER)) return true;

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
        if (isBarrelCrafter(getJobType()))
            return distanceSquared > ((BLACKSMITH_IDLE_RADIUS_X + 10) * (BLACKSMITH_IDLE_RADIUS_X + 10));
        if (getJobType().equals(JOB_CARPENTER))
            return distanceSquared > ((CARPENTER_IDLE_RADIUS_X + 10) * (CARPENTER_IDLE_RADIUS_X + 10));
        if (getJobType().equals(JOB_TRADER))
            return distanceSquared > ((TRADER_IDLE_RADIUS_X + 10) * (TRADER_IDLE_RADIUS_X + 10));
        if (getJobType().equals(JOB_GUARD)) {
            // Guards patrol the whole town - only too far once they leave the town area
            Optional<BlockPos> townHallPos = peasant.getTownHallPos();
            if (townHallPos.isPresent() && peasant.level().isLoaded(townHallPos.get())
                    && peasant.level().getBlockEntity(townHallPos.get())
                            instanceof net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity townHall) {
                return !townHall.isWithinStableScanArea(currentPos, townHall.getCurrentScanRadius() + 16);
            }
            return distanceSquared > ((GUARD_WORK_RADIUS_X + 10) * (GUARD_WORK_RADIUS_X + 10));
        }
        if (getJobType().equals(JOB_MINER)) return false;
        if (getJobType().equals(JOB_QUARRY)) return false;
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

    // Ã¢â€â‚¬Ã¢â€â‚¬ Job block reservations Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

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

    // Ã¢â€â‚¬Ã¢â€â‚¬ Save / Load Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬Ã¢â€â‚¬

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
