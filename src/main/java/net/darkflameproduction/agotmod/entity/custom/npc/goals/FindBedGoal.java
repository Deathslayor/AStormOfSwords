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
    private int searchAttempts = 0;
    private int ticksSinceLastSearch = 0;
    private int ticksSinceLastMessage = 0;
    private static final int SEARCH_INTERVAL = 40; // Only search every 2 seconds (40 ticks)
    private static final int MESSAGE_INTERVAL = 300; // Send messages every 15 seconds
    private static final int MAX_SEARCH_RADIUS = 64; // Reduced from 96
    private static final int RADIUS_INCREMENT = 16; // Increased from 10 for fewer iterations

    public FindBedGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // CRITICAL FIX: Simplify the conditions - prioritize sleep time over other restrictions
        if (!peasant.shouldSleep() || peasant.isSleeping()) {
            return false;
        }

        // CRITICAL FIX: Don't let food collection completely prevent bed finding during sleep time
        // Only skip if actively eating, not just needing food collection
        if (peasant.getHungerSystem().isEating()) {
            return false;
        }

        // CRITICAL FIX: Be more lenient with bed search cooldown during sleep time
        if (!peasant.getSleepSystem().canUseBedSearch()) {
            // If it's sleep time and we have no bed, override short cooldowns
            if (peasant.shouldSleep() && peasant.getBedPos() == null) {
                // Allow searching even with cooldown if we really need a bed during sleep time
                // This bypasses the normal cooldown restriction
            } else {
                return false;
            }
        }

        // Check home bed first (unchanged logic)
        if (peasant.getHomeSystem().hasHomeBed()) {
            BlockPos homeBed = peasant.getHomeSystem().getHomeBedPos();
            BlockState bedState = peasant.level().getBlockState(homeBed);

            if (bedState.getBlock() instanceof BedBlock) {
                if (SleepSystem.isBedOccupied(peasant.level(), homeBed)) {
                    sendChatMessage("My bed is occupied! I need to find somewhere else to sleep.");
                    peasant.getSleepSystem().setBedSearchCooldown(100);
                    return false;
                } else {
                    double distanceToHomeBed = peasant.distanceToSqr(homeBed.getX(), homeBed.getY(), homeBed.getZ());
                    if (distanceToHomeBed > 4.0D) {
                        targetBed = homeBed;
                        peasant.setBedPos(homeBed);
                        sendChatMessage("Time to head home to my bed for the night.");
                        return true;
                    } else {
                        peasant.setBedPos(homeBed);
                        return false;
                    }
                }
            } else {
                peasant.getHomeSystem().setHomeBedPos(null);
                peasant.setBedPos(null);
                sendChatMessage("My bed has been destroyed! I need to find a new place to sleep.");
            }
        }

        // Check current bed position (unchanged logic)
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
                        return false;
                    }
                }
            } else {
                sendChatMessage("My bed is gone! Need to find a new one urgently.");
                peasant.setBedPos(null);
            }
        }

        // CRITICAL FIX: Always try to search if we don't have a bed during sleep time
        if (peasant.shouldSleep() && peasant.getBedPos() == null) {
            // Override search interval during sleep time for NPCs without beds
            if (ticksSinceLastSearch < SEARCH_INTERVAL / 2) { // Faster searching during sleep time
                return false;
            }
            sendChatMessage("It's getting late... I really need to find a bed to sleep in!");
        } else {
            // Normal search interval for non-urgent situations
            if (ticksSinceLastSearch < SEARCH_INTERVAL) {
                return false;
            }
        }

        return findNearbyBed();
    }

    private boolean findNearbyBed() {
        BlockPos peasantPos = peasant.blockPosition();
        boolean foundAnyBeds = false;
        ticksSinceLastSearch = 0; // Reset the timer

        sendChatMessage("Searching for an available bed nearby...");

        // CRITICAL FIX: During sleep time, search more aggressively
        int maxRadius = peasant.shouldSleep() ? MAX_SEARCH_RADIUS + 32 : MAX_SEARCH_RADIUS;
        int radiusIncrement = peasant.shouldSleep() ? RADIUS_INCREMENT / 2 : RADIUS_INCREMENT;

        // Optimized search with early termination
        for (int searchRadius = 16; searchRadius <= maxRadius; searchRadius += radiusIncrement) {
            BlockPos closestBed = null;
            double closestDistance = Double.MAX_VALUE;
            int bedsChecked = 0;
            int maxBedsToCheck = peasant.shouldSleep() ? 150 : 100; // Check more beds during sleep time

            // Use a more efficient spiral search pattern instead of cubic
            for (int layer = 0; layer < searchRadius; layer += 4) { // Check every 4th layer
                for (int angle = 0; angle < 360; angle += 45) { // 8 directions
                    if (bedsChecked >= maxBedsToCheck) break;

                    double radians = Math.toRadians(angle);
                    int x = (int) (layer * Math.cos(radians));
                    int z = (int) (layer * Math.sin(radians));

                    // Check multiple Y levels at this X,Z position
                    for (int y = -8; y <= 8; y += 2) { // Check every other Y level
                        BlockPos checkPos = peasantPos.offset(x, y, z);
                        BlockState state = peasant.level().getBlockState(checkPos);

                        if (state.getBlock() instanceof BedBlock) {
                            bedsChecked++;
                            if (state.getValue(BedBlock.PART) == BedPart.HEAD) {
                                foundAnyBeds = true;

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
                if (bedsChecked >= maxBedsToCheck) break;
            }

            // If we found a bed at this radius, use it immediately
            if (closestBed != null) {
                targetBed = closestBed;
                searchAttempts = 0;
                sendChatMessage("Found an empty bed! Heading there now.");
                return true;
            }
        }

        // CRITICAL FIX: More reasonable cooldown system during sleep time
        searchAttempts++;

        if (foundAnyBeds) {
            // Found beds but occupied - shorter cooldown during sleep time
            if (peasant.shouldSleep()) {
                sendChatMessage("All nearby beds are occupied... I'll keep looking in a moment.");
                int cooldown = Math.min(100 + (searchAttempts * 50), 600); // 5-30 seconds max during sleep time
                peasant.getSleepSystem().setBedSearchCooldown(cooldown);
            } else {
                sendChatMessage("No available beds right now. I'll try again later.");
                int cooldown = Math.min(200 + (searchAttempts * 100), 1200); // 10-60 seconds max normally
                peasant.getSleepSystem().setBedSearchCooldown(cooldown);
            }
        } else {
            // No beds found - still shorter cooldown during sleep time
            if (peasant.shouldSleep()) {
                if (searchAttempts == 1) {
                    sendChatMessage("I can't find any beds nearby! This is troubling...");
                } else if (searchAttempts >= 3) {
                    sendChatMessage("Still no beds available... I'm getting very tired.");
                }
                int cooldown = Math.min(200 + (searchAttempts * 100), 1200); // 10-60 seconds max during sleep time
                peasant.getSleepSystem().setBedSearchCooldown(cooldown);
            } else {
                sendChatMessage("No beds found in the area.");
                int cooldown = Math.min(400 + (searchAttempts * 200), 2400); // 20-120 seconds max normally
                peasant.getSleepSystem().setBedSearchCooldown(cooldown);
            }

            if (searchAttempts > 10) {
                searchAttempts = 0; // Reset after many failed attempts
            }
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetBed == null || !peasant.shouldSleep()) {
            return false;
        }

        // CRITICAL FIX: Don't stop bed finding just because we need food during sleep time
        if (peasant.getHungerSystem().isEating()) {
            return false;
        }

        if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
            return false;
        }

        BlockState bedState = peasant.level().getBlockState(targetBed);
        if (!(bedState.getBlock() instanceof BedBlock)) {
            sendChatMessage("The bed I was heading to has disappeared!");
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
            }
        }
        targetBed = null;
        searchAttempts = 0;
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        // Increment the search timer
        ticksSinceLastSearch++;
        ticksSinceLastMessage++;

        if (targetBed != null) {
            peasant.getLookControl().setLookAt(targetBed.getX(), targetBed.getY(), targetBed.getZ());

            // Send periodic messages while walking to bed
            if (ticksSinceLastMessage >= MESSAGE_INTERVAL) {
                double distance = peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ());
                if (distance > 64.0D) { // More than 8 blocks away
                    sendChatMessage("This bed is quite far... but I really need to sleep.");
                } else if (distance > 16.0D) { // More than 4 blocks away
                    sendChatMessage("Almost at the bed now.");
                }
                ticksSinceLastMessage = 0;
            }

            if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                BlockState bedState = peasant.level().getBlockState(targetBed);
                if (bedState.getBlock() instanceof BedBlock &&
                        bedState.getValue(BedBlock.PART) == BedPart.HEAD) {
                    peasant.setBedPos(targetBed);
                    peasant.getNavigation().stop();
                    sendChatMessage("Finally reached the bed! Time to get some rest.");
                    return;
                }
            }

            if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 16.0D) {
                if (SleepSystem.isBedOccupied(peasant.level(), targetBed)) {
                    targetBed = null;
                    peasant.getNavigation().stop();
                    sendChatMessage("Oh no! Someone else took the bed I was walking to!");
                    // CRITICAL FIX: Shorter cooldown when bed becomes occupied during sleep time
                    int cooldown = peasant.shouldSleep() ? 20 : 40;
                    peasant.getSleepSystem().setBedSearchCooldown(cooldown);
                    return;
                }
            }

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