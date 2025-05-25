package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.JobSystem;

import java.util.EnumSet;

public class FindJobGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private BlockPos targetJobBlock;
    private int searchCooldown = 0;
    private boolean hasTriedThisBlock = false;

    public FindJobGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        // Don't look for jobs if already have one, sleeping, eating, collecting food, or in search cooldown
        if (peasant.hasJob() || peasant.isSleeping() || peasant.getHungerSystem().isEating() ||
                peasant.needsFoodCollection() || searchCooldown > 0) {
            return false;
        }

        // For employed peasants, they must have returned to job block after food collection
        // For unemployed peasants, this requirement doesn't apply
        if (peasant.hasJob() && !peasant.getFarmingSystem().hasReturnedToJobBlockAfterFood()) {
            return false;
        }

        return findNearbyJobBlock();
    }

    private boolean findNearbyJobBlock() {
        BlockPos peasantPos = peasant.blockPosition();

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
                        if (state.getBlock() == net.minecraft.world.level.block.Blocks.COMPOSTER &&
                                !JobSystem.isJobBlockReserved(checkPos, peasant.getUUID())) {
                            targetJobBlock = checkPos;
                            hasTriedThisBlock = false;
                            return true;
                        }
                    }
                }
            }
        }

        searchCooldown = 600; // 30 second cooldown if no job block found
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

        // Stop if someone else took this job block (FIXED: now using static method call)
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
        if (!peasant.hasJob()) {
            // Only set cooldown if we failed to get a job
            searchCooldown = 200; // 10 second cooldown before trying again
        }
        targetJobBlock = null;
        hasTriedThisBlock = false;
        peasant.getNavigation().stop();
    }

    @Override
    public void tick() {
        if (searchCooldown > 0) {
            searchCooldown--;
        }

        if (targetJobBlock != null && !hasTriedThisBlock) {
            double distance = peasant.distanceToSqr(targetJobBlock.getX(), targetJobBlock.getY(), targetJobBlock.getZ());

            if (distance <= 4.0D) {
                // Close enough to attempt claiming the job block
                hasTriedThisBlock = true;

                // FIXED: Using static method call for reserveJobBlock
                if (JobSystem.reserveJobBlock(targetJobBlock, peasant.getUUID())) {
                    peasant.setJobType(JobSystem.JOB_FARMER);
                    peasant.setJobBlockPos(targetJobBlock);
                    // Successfully got job, goal will end naturally
                } else {
                    // Failed to get job (someone else got it), stop trying this block
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
}