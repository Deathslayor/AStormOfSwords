package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.SleepSystem;

import java.util.EnumSet;

public class FindBedGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private BlockPos targetBed;
    private int searchCooldown = 0;
    private int searchAttempts = 0;
    private int ticksSinceLastMessage = 0;
    private static final int MESSAGE_INTERVAL = 300; // Send messages every 15 seconds
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

        // Check home bed first
        if (peasant.getHomeSystem().hasHomeBed()) {
            BlockPos homeBed = peasant.getHomeSystem().getHomeBedPos();
            BlockState bedState = peasant.level().getBlockState(homeBed);

            if (bedState.getBlock() instanceof BedBlock) {
                if (SleepSystem.isBedOccupied(peasant.level(), homeBed)) {
                    sendChatMessage("My bed is occupied! I need to find somewhere else to sleep.");
                    // Don't set long cooldown, immediately look for alternative
                    searchCooldown = 40; // 2 seconds
                } else {
                    double distanceToHomeBed = peasant.distanceToSqr(homeBed.getX(), homeBed.getY(), homeBed.getZ());
                    if (distanceToHomeBed > 4.0D) {
                        targetBed = homeBed;
                        peasant.setBedPos(homeBed);
                        sendChatMessage("Time to head home to my bed for the night.");
                        return true;
                    } else {
                        peasant.setBedPos(homeBed);
                        return false; // Close enough, SleepGoal will handle it
                    }
                }
            } else {
                peasant.getHomeSystem().setHomeBedPos(null);
                peasant.setBedPos(null);
                sendChatMessage("My home bed has been destroyed! I need to find a new place to sleep.");
            }
        }

        // Check current bed position
        if (peasant.getBedPos() != null) {
            BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
            if (bedState.getBlock() instanceof BedBlock) {
                if (SleepSystem.isBedOccupied(peasant.level(), peasant.getBedPos())) {
                    sendChatMessage("Someone else is using my bed! Looking for another one...");
                    peasant.setBedPos(null);
                } else {
                    double distanceToBed = peasant.distanceToSqr(peasant.getBedPos().getX(), peasant.getBedPos().getY(), peasant.getBedPos().getZ());
                    if (distanceToBed > 4.0D) {
                        targetBed = peasant.getBedPos();
                        sendChatMessage("Walking to my bed for the night.");
                        return true;
                    } else {
                        return false; // Close enough, SleepGoal will handle it
                    }
                }
            } else {
                sendChatMessage("My bed is gone! Need to find a new one urgently.");
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

        sendChatMessage("Searching for an available bed nearby...");

        // Use a proper cubic search pattern - much more thorough
        for (int radius = 8; radius <= MAX_SEARCH_RADIUS; radius += 8) {
            // Search in expanding cubic areas
            for (int x = -radius; x <= radius; x++) {
                for (int y = -8; y <= 8; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        // Only check the outer shell of each radius to avoid re-checking inner areas
                        if (radius > 8 && Math.abs(x) < radius - 8 && Math.abs(z) < radius - 8) {
                            continue;
                        }

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
                                // Check if bed is available
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
                sendChatMessage("Found an empty bed! Heading there now.");
                return true;
            }
        }

        // No available beds found - set appropriate cooldown
        searchAttempts++;

        if (foundAnyBeds) {
            // Found beds but all occupied
            if (peasant.shouldSleep()) {
                sendChatMessage("All nearby beds are occupied... I'll keep looking soon.");
                searchCooldown = 60 + (searchAttempts * 20); // 3-9 seconds during sleep time
            } else {
                sendChatMessage("No available beds right now. I'll try again later.");
                searchCooldown = 200 + (searchAttempts * 100); // 10-30 seconds normally
            }
        } else {
            // No beds found at all
            if (peasant.shouldSleep()) {
                if (searchAttempts == 1) {
                    sendChatMessage("I can't find any beds nearby! This is troubling...");
                } else if (searchAttempts >= 3) {
                    sendChatMessage("Still no beds available... I'm getting very tired.");
                }
                searchCooldown = 100 + (searchAttempts * 40); // 5-13 seconds during sleep time
            } else {
                sendChatMessage("No beds found in the area.");
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
            sendChatMessage("The bed I was heading to has disappeared!");
            return false;
        }

        // Check if bed became occupied while we were walking to it
        if (SleepSystem.isBedOccupied(peasant.level(), targetBed)) {
            sendChatMessage("Oh no! Someone else took the bed I was walking to!");
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        if (targetBed != null) {
            sendChatMessage("Walking to the bed I found.");
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
                sendChatMessage("Perfect! This bed will do nicely for the night.");
            } else {
                sendChatMessage("Darn, someone else got to the bed first!");
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

        ticksSinceLastMessage++;

        if (targetBed != null) {
            peasant.getLookControl().setLookAt(targetBed.getX(), targetBed.getY(), targetBed.getZ());

            // Send periodic messages while walking to bed
            if (ticksSinceLastMessage >= MESSAGE_INTERVAL) {
                double distance = peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ());
                if (distance > 64.0D) {
                    sendChatMessage("This bed is quite far... but I really need to sleep.");
                } else if (distance > 16.0D) {
                    sendChatMessage("Almost at the bed now.");
                }
                ticksSinceLastMessage = 0;
            }

            // Check if we reached the bed
            if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                BlockState bedState = peasant.level().getBlockState(targetBed);
                if (bedState.getBlock() instanceof BedBlock &&
                        bedState.getValue(BedBlock.PART) == BedPart.HEAD) {

                    // Final check if bed is still available
                    if (!SleepSystem.isBedOccupied(peasant.level(), targetBed)) {
                        peasant.setBedPos(targetBed);
                        peasant.getNavigation().stop();
                        sendChatMessage("Finally reached the bed! Time to get some rest.");
                        return;
                    } else {
                        sendChatMessage("Someone took the bed just as I got here!");
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

    private void sendChatMessage(String message) {
        if (peasant.level().isClientSide || !peasant.hasCustomName()) {
            return;
        }

        String npcName = peasant.getCustomName().getString();
        String fullMessage = npcName + ": " + message;

        // Send message to all players within 32 blocks
        peasant.level().players().forEach(player -> {
            if (player.distanceToSqr(peasant.getX(), peasant.getY(), peasant.getZ()) < 1024) {
                player.displayClientMessage(Component.literal(fullMessage), false);
            }
        });
    }
}