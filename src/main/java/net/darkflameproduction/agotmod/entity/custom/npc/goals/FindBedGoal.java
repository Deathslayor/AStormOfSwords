package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.SleepSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.SimpleBedWarningSystem;

import java.util.EnumSet;
import java.util.Set;

public class FindBedGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private BlockPos targetBed;
    private int searchCooldown = 0;
    private int searchAttempts = 0;
    private static final int MAX_SEARCH_RADIUS = 64;

    public FindBedGoal(Northern_Peasant_Entity peasant) {
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

        // During sleep time, be more lenient with cooldowns
        if (searchCooldown > 0) {
            if (peasant.shouldSleep() && peasant.getBedPos() == null) {
                // Override cooldown if we desperately need a bed during sleep time
                if (searchCooldown > 100) { // More than 5 seconds
                    searchCooldown = 20; // Reduce to 1 second
                }
            } else {
                searchCooldown--;
                return false;
            }
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
                    if (distanceToHomeBed > 4.0D) {
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
                    if (distanceToBed > 4.0D) {
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

    private boolean findNearbyBed() {
        BlockPos peasantPos = peasant.blockPosition();
        BlockPos closestBed = null;
        double closestDistance = Double.MAX_VALUE;
        boolean foundAnyBeds = false;

        // Get claimed home beds to avoid
        Set<BlockPos> claimedHomeBeds = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                peasantPos, MAX_SEARCH_RADIUS, peasant.getUUID()
        );

        // MUCH FASTER: Simple expanding circle search instead of cubic
        for (int radius = 8; radius <= MAX_SEARCH_RADIUS; radius += 8) {
            // Check beds in a more efficient pattern - every 2nd block in each direction
            for (int x = -radius; x <= radius; x += 2) {
                for (int z = -radius; z <= radius; z += 2) {
                    // Skip blocks that are too far (rough circle approximation)
                    if (x * x + z * z > radius * radius) {
                        continue;
                    }

                    // Check a few Y levels around the peasant's level
                    for (int y = -4; y <= 4; y += 2) {
                        BlockPos checkPos = peasantPos.offset(x, y, z);

                        // Check if within home area
                        if (!peasant.isWithinHomeArea(checkPos)) {
                            continue;
                        }

                        BlockState state = peasant.level().getBlockState(checkPos);

                        if (state.getBlock() instanceof BedBlock) {
                            foundAnyBeds = true;

                            // Only consider head parts of beds
                            if (state.getValue(BedBlock.PART) == BedPart.HEAD) {
                                // Skip if this bed is claimed as someone's home bed
                                if (claimedHomeBeds.contains(checkPos)) {
                                    continue;
                                }

                                // Check if bed is available (not occupied)
                                if (!SleepSystem.isBedOccupied(peasant.level(), checkPos)) {
                                    double distance = peasantPos.distSqr(checkPos);
                                    if (distance < closestDistance) {
                                        closestDistance = distance;
                                        closestBed = checkPos;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // If we found a bed at this radius, use it immediately
            if (closestBed != null) {
                targetBed = closestBed;
                searchAttempts = 0;
                return true;
            }
        }

        // No available beds found - set appropriate cooldown
        searchAttempts++;

        if (foundAnyBeds) {
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
        if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
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
            peasant.getNavigation().moveTo(targetBed.getX() + 0.5, targetBed.getY(), targetBed.getZ() + 0.5, 0.7D);
        }
    }

    @Override
    public void stop() {
        if (targetBed != null && peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
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
            if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
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

            // Keep moving toward the bed
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(targetBed.getX() + 0.5, targetBed.getY(), targetBed.getZ() + 0.5, 0.7D);
            }
        }
    }
}