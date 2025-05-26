package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobWarningSystem;

import java.util.EnumSet;
import java.util.Set;

public class FindJobGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private BlockPos targetJobBlock;
    private int searchCooldown = 0;
    private boolean hasTriedThisBlock = false;
    private int searchAttempts = 0;
    private int ticksSinceLastMessage = 0;
    private static final int MESSAGE_INTERVAL = 600; // Send messages every 30 seconds
    private static final int MAX_SEARCH_ATTEMPTS = 10; // Reset attempts after this many failures

    public FindJobGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Don't look for jobs if already have one, sleeping, eating, collecting food
        if (peasant.hasJob() || peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        // For employed peasants, they must have returned to job block after food collection
        // For unemployed peasants, this requirement doesn't apply
        if (peasant.hasJob() && !peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood()) {
            return false;
        }

        // Handle search cooldown with periodic attempts
        if (searchCooldown > 0) {
            searchCooldown--;
            return false;
        }

        return findNearbyJobBlock();
    }

    private boolean findNearbyJobBlock() {
        BlockPos peasantPos = peasant.blockPosition();

        // NEW: Get all warned job blocks in the area to avoid them
        Set<BlockPos> warnedJobBlocks = JobWarningSystem.getWarnedJobBlocksInRadius(
                peasantPos, 64, peasant.getUUID()
        );

        boolean foundAnyJobBlocks = false;
        BlockPos closestJobBlock = null;
        double closestDistance = Double.MAX_VALUE;

        for (int radius = 8; radius <= 64; radius += 8) {
            for (int x = -radius; x <= radius; x++) {
                for (int y = -8; y <= 8; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos checkPos = peasantPos.offset(x, y, z);

                        if (!peasant.isWithinHomeArea(checkPos)) {
                            continue;
                        }

                        BlockState state = peasant.level().getBlockState(checkPos);

                        // Check for composter (farmer job block)
                        if (state.getBlock() == net.minecraft.world.level.block.Blocks.COMPOSTER) {
                            foundAnyJobBlocks = true;

                            // NEW: Skip if this job block is warned as being in use
                            if (warnedJobBlocks.contains(checkPos)) {
                                continue;
                            }

                            // Check traditional reservation system
                            if (!JobSystem.isJobBlockReserved(checkPos, peasant.getUUID())) {
                                double distance = peasantPos.distSqr(checkPos);
                                if (distance < closestDistance) {
                                    closestDistance = distance;
                                    closestJobBlock = checkPos;
                                }
                            } else {
                                // Job block reserved by someone else
                            }
                        }
                    }
                }
            }
        }

        // If we found an available job block, target it
        if (closestJobBlock != null) {
            targetJobBlock = closestJobBlock;
            hasTriedThisBlock = false;
            searchAttempts = 0; // Reset attempts on success
            return true;
        }

        // No available job blocks found - set appropriate cooldown
        searchAttempts++;

        if (foundAnyJobBlocks) {
            // Found job blocks but all were taken
            // Progressive cooldown - starts short, gets longer, then resets
            if (searchAttempts <= 3) {
                searchCooldown = 200; // 10 seconds for first few attempts
            } else if (searchAttempts <= 6) {
                searchCooldown = 400; // 20 seconds for middle attempts
            } else if (searchAttempts <= 9) {
                searchCooldown = 600; // 30 seconds for later attempts
            } else {
                searchCooldown = 100; // Reset to 5 seconds and try again
                searchAttempts = 0; // Reset attempts counter
            }
        } else {
            // No job blocks found at all
            // Longer cooldown when no job blocks exist
            if (searchAttempts <= 2) {
                searchCooldown = 400; // 20 seconds
            } else if (searchAttempts <= 5) {
                searchCooldown = 800; // 40 seconds
            } else {
                searchCooldown = 200; // Reset to shorter cooldown
                searchAttempts = 0;
            }
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetJobBlock == null || peasant.isSleeping() || peasant.hasJob()) {
            return false;
        }

        BlockState state = peasant.level().getBlockState(targetJobBlock);
        if (state.getBlock() != net.minecraft.world.level.block.Blocks.COMPOSTER) {
            return false;
        }

        // NEW: Stop if job block becomes warned while we're approaching
        if (JobWarningSystem.isJobBlockWarned(targetJobBlock, peasant.getUUID())) {
            return false;
        }

        // Stop if someone else took this job block using traditional reservation
        if (JobSystem.isJobBlockReserved(targetJobBlock, peasant.getUUID())) {
            return false;
        }

        return !hasTriedThisBlock;
    }

    @Override
    public void start() {
        if (targetJobBlock != null) {
            peasant.getNavigation().moveTo(targetJobBlock.getX() + 0.5, targetJobBlock.getY(), targetJobBlock.getZ() + 0.5, 0.6D);
        }
    }

    @Override
    public void stop() {
        // Only set short cooldown for specific failures, not general ones
        if (targetJobBlock != null && !peasant.hasJob()) {
            // We were trying for a specific job block but failed
            searchCooldown = 100; // 5 second cooldown before trying again
        }

        targetJobBlock = null;
        hasTriedThisBlock = false;
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        ticksSinceLastMessage++;

        // Reduce search cooldown faster if we're unemployed and it's work hours
        if (searchCooldown > 0 && !peasant.hasJob() && !peasant.shouldSleep()) {
            // Don't just decrement in canUse(), also decrement here for unemployed NPCs
            if (searchCooldown > 1) {
                searchCooldown--; // Double decrement for unemployed NPCs during work hours
            }
        }

        if (targetJobBlock != null && !hasTriedThisBlock) {
            double distance = peasant.distanceToSqr(targetJobBlock.getX(), targetJobBlock.getY(), targetJobBlock.getZ());

            // Send periodic updates while walking
            if (ticksSinceLastMessage >= MESSAGE_INTERVAL) {
                ticksSinceLastMessage = 0;
            }

            if (distance <= 4.0D) {
                // Close enough to attempt claiming the job block
                hasTriedThisBlock = true;

                // NEW: Final check for warnings before claiming
                if (JobWarningSystem.isJobBlockWarned(targetJobBlock, peasant.getUUID())) {
                    // Short cooldown since we need to find alternative quickly
                    searchCooldown = 60; // 3 seconds
                    return;
                }

                // Attempt to reserve using traditional system
                if (JobSystem.reserveJobBlock(targetJobBlock, peasant.getUUID())) {
                    peasant.setJobType(JobSystem.JOB_FARMER);
                    peasant.setJobBlockPos(targetJobBlock);
                    searchAttempts = 0; // Reset attempts on successful job acquisition
                    // Successfully got job, goal will end naturally
                } else {
                    // Failed to get job (someone else got it), stop trying this block
                    searchCooldown = 40; // 2 second cooldown to find alternative quickly
                    // Goal will end and set a cooldown
                }
                return;
            }

            // Keep moving toward the job block
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(targetJobBlock.getX() + 0.5, targetJobBlock.getY(), targetJobBlock.getZ() + 0.5, 0.6D);
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