package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.goal.Goal;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.FarmingSystem.FarmState;

import java.util.*;

public class FarmingGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private int workTimer = 0;
    private BlockPos currentWorkTarget = null;

    public FarmingGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Must be a farmer with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER) ||
                peasant.getJobBlockPos() == null) {
            return false;
        }

        // Must not be sleeping, eating, or collecting food
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection()) {
            return false;
        }

        // Must have returned to job block after food collection
        if (!peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood()) {
            return false;
        }

        // Don't work during sleep hours
        if (peasant.shouldSleep()) {
            return false;
        }

        // Check if job block still exists
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (jobBlockPos != null) {
            var jobBlockState = peasant.level().getBlockState(jobBlockPos);
            if (jobBlockState.getBlock() != net.minecraft.world.level.block.Blocks.COMPOSTER) {
                // Job block destroyed, lose job
                peasant.setJobType(JobSystem.JOB_NONE);
                peasant.setJobBlockPos(null);
                JobSystem.releaseJobBlockReservation(peasant.getUUID());
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        // Stop if should sleep, collecting food, or eating
        if (peasant.shouldSleep() || peasant.isSleeping() ||
                peasant.needsFoodCollection() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        workTimer = 0;
        currentWorkTarget = null;
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        currentWorkTarget = null;
    }

    @Override
    public void tick() {
        workTimer++;

        FarmState currentState = peasant.getFarmingSystem().getCurrentFarmState();

        switch (currentState) {
            case NEEDS_FARM_SETUP:
                handleFarmSetup();
                break;
            case RETURN_TO_JOB_BLOCK:
                handleReturnToJobBlock();
                break;
            case CONVERTING_TO_FARMLAND:
                handleFarmlandConversion();
                break;
            case PLANTING_CROPS:
                handleCropPlanting();
                break;
            case HARVESTING_CROPS:
                handleHarvesting();
                break;
            case PATROLLING:
                handlePatrolling();
                break;
        }
    }

    private void handleFarmSetup() {
        if (workTimer % 100 == 0) { // Every 5 seconds
            if (peasant.getFarmingSystem().setupFarm()) {
                sendChatMessage(peasant.getCustomName().getString() + ": Setting up 19x19 farm around my composter!");
            } else {
                sendChatMessage(peasant.getCustomName().getString() + ": Waiting for job block to set up farm...");
            }
        }
    }

    private void handleReturnToJobBlock() {
        // Check if we're at the job block
        if (peasant.getFarmingSystem().isAtJobBlock()) {
            sendChatMessage(peasant.getCustomName().getString() + ": Back at my composter! Time to work the land!");
            peasant.getFarmingSystem().setCurrentFarmState(FarmState.CONVERTING_TO_FARMLAND);
            return;
        }

        // Navigate to job block
        BlockPos jobBlock = peasant.getFarmingSystem().getJobBlockPosition();
        if (jobBlock != null) {
            if (workTimer % 100 == 0) { // Every 5 seconds
                sendChatMessage(peasant.getCustomName().getString() + ": Walking to my composter to start the day's work!");
            }

            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(jobBlock.getX() + 0.5,
                        jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
            }
        }
    }

    private void handleFarmlandConversion() {
        if (workTimer % 200 == 0) { // Every 10 seconds
            int converted = peasant.getFarmingSystem().convertToFarmland();

            if (converted > 0) {
                sendChatMessage(peasant.getCustomName().getString() + ": Converted " +
                        converted + " grass and dirt blocks to farmland!");
            } else {
                sendChatMessage(peasant.getCustomName().getString() + ": Ready to plant crops!");
            }
        }
    }

    private void handleCropPlanting() {
        if (workTimer % 200 == 0) { // Every 10 seconds
            int planted = peasant.getFarmingSystem().plantCrops();

            if (planted > 0) {
                sendChatMessage(peasant.getCustomName().getString() + ": Planted " +
                        planted + " crops!");
            } else {
                sendChatMessage(peasant.getCustomName().getString() + ": All farmland planted! Waiting for harvest.");
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.HARVESTING_CROPS);
            }
        }
    }

    private void handleHarvesting() {
        // Look for crops to harvest every 1 second (was 5 seconds)
        if (workTimer % 20 == 0) {
            BlockPos harvestPos = peasant.getFarmingSystem().findNextHarvestPosition();

            if (harvestPos != null) {
                currentWorkTarget = harvestPos;
                sendChatMessage(peasant.getCustomName().getString() + ": Found mature crops to harvest!");
            } else {
                // No crops to harvest, switch to patrolling faster
                if (workTimer % 200 == 0) { // Every 10 seconds (was 30)
                    peasant.getFarmingSystem().setCurrentFarmState(FarmState.PATROLLING);
                }
            }
        }

        // If we have a harvest target, work on it
        if (currentWorkTarget != null) {
            double distance = peasant.distanceToSqr(currentWorkTarget.getX(),
                    currentWorkTarget.getY(), currentWorkTarget.getZ());

            if (distance <= 9.0D) { // Increased range from 4.0D to 9.0D (3 blocks)
                // Close enough to harvest
                peasant.getFarmingSystem().harvestAndReplant(currentWorkTarget);
                sendChatMessage(peasant.getCustomName().getString() + ": Harvested and replanted!");
                currentWorkTarget = null;

                // Immediately look for next harvest target
                BlockPos nextHarvestPos = peasant.getFarmingSystem().findNextHarvestPosition();
                if (nextHarvestPos != null) {
                    currentWorkTarget = nextHarvestPos;
                }
            } else {
                // Move closer to harvest target with faster speed
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(currentWorkTarget.getX() + 0.5,
                            currentWorkTarget.getY(), currentWorkTarget.getZ() + 0.5, 0.6D); // Faster speed: 0.8D instead of 0.6D
                }
            }
        }
    }

    private void handlePatrolling() {
        // Check for new work every 5 seconds (was 10 seconds)
        if (workTimer % 100 == 0) {
            // Check if there are crops to harvest (higher priority)
            BlockPos harvestPos = peasant.getFarmingSystem().findNextHarvestPosition();
            if (harvestPos != null) {
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.HARVESTING_CROPS);
                return;
            }

            // Check if farmland conversion is needed
            int converted = peasant.getFarmingSystem().convertToFarmland();
            if (converted > 0) {
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.CONVERTING_TO_FARMLAND);
                sendChatMessage(peasant.getCustomName().getString() + ": Found new grass and dirt to convert!");
                return;
            }

            // Check if planting is needed
            int planted = peasant.getFarmingSystem().plantCrops();
            if (planted > 0) {
                peasant.getFarmingSystem().setCurrentFarmState(FarmState.PLANTING_CROPS);
                sendChatMessage(peasant.getCustomName().getString() + ": Found empty farmland to plant!");
                return;
            }

            // Nothing to do, continue patrolling
            if (workTimer % 1200 == 0) { // Every minute
                sendChatMessage(peasant.getCustomName().getString() + ": Patrolling my farm. Everything looks good!");
            }
        }

        // Wander around the farm area occasionally
        if (workTimer % 400 == 0 && peasant.getJobBlockPos() != null) {
            BlockPos jobBlock = peasant.getJobBlockPos();
            BlockPos wanderTarget = jobBlock.offset(
                    peasant.getRandom().nextInt(19) - 9,
                    0,
                    peasant.getRandom().nextInt(19) - 9
            );

            peasant.getNavigation().moveTo(wanderTarget.getX() + 0.5,
                    wanderTarget.getY(), wanderTarget.getZ() + 0.5, 0.6D);
        }
    }

    private void sendChatMessage(String message) {
        if (peasant.level().isClientSide || !peasant.hasCustomName()) {
            return;
        }

        // Send message to all players within 32 blocks
        peasant.level().players().forEach(player -> {
            if (player.distanceToSqr(peasant.getX(), peasant.getY(), peasant.getZ()) < 1024) {
                player.displayClientMessage(Component.literal(message), false);
            }
        });
    }
}