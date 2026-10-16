package net.darkflameproduction.agotmod.entity.custom.npc.goals.sleep;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.BedAccess;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.SleepSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.SimpleBedWarningSystem;

import net.minecraft.core.SectionPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class FindBedGoal extends Goal {
    private final Peasant_Entity peasant;
    private BlockPos targetBed;
    private int searchCooldown = 0;
    private int searchAttempts = 0;
    private static final int MAX_SEARCH_RADIUS = 64;
    private static final int BED_SEARCH_HEIGHT = 8;   // floors above and below the NPC searched for a bed
    private static final int REPATH_INTERVAL = 20;
    private int repathCooldown = 0;

    public FindBedGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Must be sleep time and not already sleeping
        if (!peasant.shouldSleep() || peasant.isSleeping()) {
            return false;
        }

        // Don't search if actively eating (but allow if just needing food collection)
        if (peasant.getHungerSystem().isEating()) {
            return false;
        }

        // The cooldown always holds - it used to be skipped at bedtime for an NPC without a bed, which then
        // searched every time the goals were checked (10 times a second). Only shortened when it needs a bed.
        if (searchCooldown > 0) {
            if (peasant.getBedPos() == null && searchCooldown > 100) {
                searchCooldown = 20;
            }
            searchCooldown--;
            return false;
        }

        // Check home bed first (UNCHANGED)
        if (peasant.getHomeSystem().hasHomeBed()) {
            BlockPos homeBed = peasant.getHomeSystem().getHomeBedPos();
            BlockState bedState = peasant.level().getBlockState(homeBed);

            if (bedState.getBlock() instanceof BedBlock) {
                if (SleepSystem.isBedOccupied(peasant.level(), homeBed)) {
                    searchCooldown = 40; // 2 seconds
                } else {
                    double distanceToHomeBed = peasant.distanceToSqr(homeBed.getX(), homeBed.getY(), homeBed.getZ());
                    if (!BedAccess.isWithinReach(peasant, homeBed)) {
                        targetBed = homeBed;
                        peasant.setBedPos(homeBed);
                        return true;
                    } else {
                        peasant.setBedPos(homeBed);
                        return false; // Close enough, SleepGoal will handle it
                    }
                }
            } else {
                peasant.getHomeSystem().setHomeBedPos(null);
                peasant.setBedPos(null);
            }
        }

        // Check current bed position (UNCHANGED)
        if (peasant.getBedPos() != null) {
            BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
            if (bedState.getBlock() instanceof BedBlock) {
                if (SleepSystem.isBedOccupied(peasant.level(), peasant.getBedPos())) {
                    peasant.setBedPos(null);
                } else {
                    double distanceToBed = peasant.distanceToSqr(peasant.getBedPos().getX(), peasant.getBedPos().getY(), peasant.getBedPos().getZ());
                    if (!BedAccess.isWithinReach(peasant, peasant.getBedPos())) {
                        targetBed = peasant.getBedPos();
                        return true;
                    } else {
                        return false; // Close enough, SleepGoal will handle it
                    }
                }
            } else {
                peasant.setBedPos(null);
            }
        }

        // Need to find a new bed
        return findNearbyBed();
    }

    /**
     * Picks the nearest free bed within MAX_SEARCH_RADIUS (and BED_SEARCH_HEIGHT up or down) of the NPC.
     *
     * Every block is looked at - the old search only checked every second block across and up, so it missed most
     * beds (a bed whose head lay on a skipped block was only seen by its foot, and passed over). It is still quick:
     * a chunk section without a bed in its palette is skipped without looking at its blocks.
     */
    private boolean findNearbyBed() {
        BlockPos peasantPos = peasant.blockPosition();
        Level level = peasant.level();

        // Beds that are someone's home stay theirs
        Set<BlockPos> claimedHomeBeds = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                peasantPos, MAX_SEARCH_RADIUS + BED_SEARCH_HEIGHT, peasant.getUUID());

        List<BlockPos> beds = bedsAround(level, peasantPos);
        boolean anyBeds = !beds.isEmpty();
        List<BlockPos> candidates = new ArrayList<>();
        for (BlockPos bed : beds) {
            BlockState state = level.getBlockState(bed);
            if (!(state.getBlock() instanceof BedBlock) || state.getValue(BedBlock.OCCUPIED)) continue;
            if (claimedHomeBeds.contains(bed) || !peasant.isWithinHomeArea(bed)) continue;
            candidates.add(bed);
        }

        // Nearest first; checking for a sleeping NPC is the costly part, so only until a free bed turns up
        candidates.sort(Comparator.comparingDouble(peasantPos::distSqr));
        for (BlockPos bed : candidates) {
            if (SleepSystem.isBedOccupied(level, bed)) continue;
            targetBed = bed;
            searchAttempts = 0;
            return true;
        }
        return noBedFound(anyBeds);
    }

    // The beds around the NPC from its last scan: scanning is the costly part, and beds rarely move, so a scan
    // is reused for a while unless the NPC has gone elsewhere. Whether each is free is checked every time.
    private static final int BED_CACHE_TICKS = 20 * 60;
    private static final double BED_CACHE_MOVE_SQR = 16.0D * 16.0D;
    private List<BlockPos> cachedBeds = List.of();
    private BlockPos cachedFrom = null;
    private long cachedAt = Long.MIN_VALUE;

    private List<BlockPos> bedsAround(Level level, BlockPos peasantPos) {
        long now = level.getGameTime();
        if (cachedFrom != null && now - cachedAt < BED_CACHE_TICKS && cachedFrom.distSqr(peasantPos) <= BED_CACHE_MOVE_SQR) {
            return cachedBeds;
        }
        cachedBeds = scanForBeds(level, peasantPos);
        cachedFrom = peasantPos;
        cachedAt = now;
        return cachedBeds;
    }

    /** Every bed head within MAX_SEARCH_RADIUS (and BED_SEARCH_HEIGHT up or down). */
    private static List<BlockPos> scanForBeds(Level level, BlockPos peasantPos) {
        List<BlockPos> beds = new ArrayList<>();
        int minX = peasantPos.getX() - MAX_SEARCH_RADIUS, maxX = peasantPos.getX() + MAX_SEARCH_RADIUS;
        int minZ = peasantPos.getZ() - MAX_SEARCH_RADIUS, maxZ = peasantPos.getZ() + MAX_SEARCH_RADIUS;
        int minY = Math.max(level.getMinBuildHeight(), peasantPos.getY() - BED_SEARCH_HEIGHT);
        int maxY = Math.min(level.getMaxBuildHeight() - 1, peasantPos.getY() + BED_SEARCH_HEIGHT);
        long radiusSqr = (long) MAX_SEARCH_RADIUS * MAX_SEARCH_RADIUS;

        for (int cx = minX >> 4; cx <= maxX >> 4; cx++) {
            for (int cz = minZ >> 4; cz <= maxZ >> 4; cz++) {
                if (!level.hasChunk(cx, cz)) continue;
                LevelChunk chunk = level.getChunk(cx, cz);
                for (int sy = SectionPos.blockToSectionCoord(minY); sy <= SectionPos.blockToSectionCoord(maxY); sy++) {
                    LevelChunkSection section = chunk.getSection(chunk.getSectionIndexFromSectionY(sy));
                    if (section.hasOnlyAir() || !section.maybeHas(state -> state.getBlock() instanceof BedBlock)) continue;
                    int baseY = SectionPos.sectionToBlockCoord(sy);
                    for (int lx = 0; lx < 16; lx++) {
                        int x = (cx << 4) + lx;
                        for (int lz = 0; lz < 16; lz++) {
                            int z = (cz << 4) + lz;
                            long dx = x - peasantPos.getX(), dz = z - peasantPos.getZ();
                            if (dx * dx + dz * dz > radiusSqr) continue;
                            for (int ly = 0; ly < 16; ly++) {
                                int y = baseY + ly;
                                if (y < minY || y > maxY) continue;
                                BlockState state = section.getBlockState(lx, ly, lz);
                                if (!(state.getBlock() instanceof BedBlock) || state.getValue(BedBlock.PART) != BedPart.HEAD) continue;
                                beds.add(new BlockPos(x, y, z));
                            }
                        }
                    }
                }
            }
        }
        return beds;
    }

    private boolean noBedFound(boolean anyBeds) {
        // No available beds found - set appropriate cooldown
        searchAttempts++;
        if (anyBeds) {
            // Found beds but all occupied or claimed
            if (peasant.shouldSleep()) {
                searchCooldown = 60 + (searchAttempts * 20); // 3-9 seconds during sleep time
            } else {
                searchCooldown = 200 + (searchAttempts * 100); // 10-30 seconds normally
            }
        } else {
            // No beds found at all
            if (peasant.shouldSleep()) {
                searchCooldown = 100 + (searchAttempts * 40); // 5-13 seconds during sleep time
            } else {
                searchCooldown = 400 + (searchAttempts * 200); // 20-80 seconds normally
            }

            // Reset search attempts after many failures
            if (searchAttempts > 8) {
                searchAttempts = 0;
            }
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetBed == null || !peasant.shouldSleep()) {
            return false;
        }

        // Don't stop for food collection during sleep time - sleep is priority
        if (peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Stop if we're close enough
        if (BedAccess.isWithinReach(peasant, targetBed)) {
            return false;
        }

        // Check if bed still exists and is available
        BlockState bedState = peasant.level().getBlockState(targetBed);
        if (!(bedState.getBlock() instanceof BedBlock)) {
            return false;
        }

        // Check if bed became occupied while we were walking to it
        if (SleepSystem.isBedOccupied(peasant.level(), targetBed)) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        if (targetBed != null) {
            moveToBed();
        }
    }

    @Override
    public void stop() {
        if (targetBed != null && BedAccess.isWithinReach(peasant, targetBed)) {
            BlockState bedState = peasant.level().getBlockState(targetBed);
            if (bedState.getBlock() instanceof BedBlock &&
                    bedState.getValue(BedBlock.PART) == BedPart.HEAD &&
                    !SleepSystem.isBedOccupied(peasant.level(), targetBed)) {
                peasant.setBedPos(targetBed);
            } else {
                // Short cooldown to try again quickly
                searchCooldown = peasant.shouldSleep() ? 20 : 40;
            }
        }
        targetBed = null;
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (searchCooldown > 0) {
            searchCooldown--;
        }

        if (targetBed != null) {
            peasant.getLookControl().setLookAt(targetBed.getX(), targetBed.getY(), targetBed.getZ());

            // Check if we reached the bed
            if (BedAccess.isWithinReach(peasant, targetBed)) {
                BlockState bedState = peasant.level().getBlockState(targetBed);
                if (bedState.getBlock() instanceof BedBlock &&
                        bedState.getValue(BedBlock.PART) == BedPart.HEAD) {

                    // Final check if bed is still available
                    if (!SleepSystem.isBedOccupied(peasant.level(), targetBed)) {
                        peasant.setBedPos(targetBed);
                        peasant.getNavigation().stop();
                        return;
                    } else {
                        targetBed = null;
                        searchCooldown = 20; // Quick retry
                        return;
                    }
                }
            }

            // Keep moving toward the bed - planning a path is costly, so not every tick if the last one ran out
            if (repathCooldown > 0) repathCooldown--;
            if (!peasant.getNavigation().isInProgress() && repathCooldown <= 0) {
                moveToBed();
            }
        }
    }

    /**
     * Walks to a free spot beside the bed - not to the bed itself, which can't be walked into: a path there ended
     * on the nearest walkable spot, often the top of the bed, and the NPC stood on its bed stuck.
     */
    private void moveToBed() {
        repathCooldown = REPATH_INTERVAL;
        BlockPos bedside = BedAccess.findBedside(peasant.level(), targetBed, peasant.blockPosition());
        if (bedside != null) {
            peasant.getNavigation().moveTo(bedside.getX() + 0.5, bedside.getY(), bedside.getZ() + 0.5, 0.7D);
        } else {
            peasant.getNavigation().moveTo(targetBed.getX() + 0.5, targetBed.getY(), targetBed.getZ() + 0.5, 0.7D);
        }
    }
}
