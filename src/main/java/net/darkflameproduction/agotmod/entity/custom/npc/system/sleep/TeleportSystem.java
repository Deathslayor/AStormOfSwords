package net.darkflameproduction.agotmod.entity.custom.npc.system.sleep;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;

public class TeleportSystem {
    private final Peasant_Entity peasant;

    // Teleportation constants
    private static final int STUCK_THRESHOLD = 600; // 30 seconds of being stuck
    private static final int TELEPORT_COOLDOWN = 1200; // 60 seconds between teleport attempts

    // Teleportation tracking
    private int stuckTimer = 0;
    private BlockPos lastPosition = null;
    private int teleportCooldown = 0;

    public TeleportSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        // Reduce teleport cooldown
        if (teleportCooldown > 0) {
            teleportCooldown--;
        }

        if (!peasant.level().isClientSide && peasant.shouldSleep() && !peasant.isSleeping() && peasant.getBedPos() != null) {
            BlockPos currentPos = peasant.blockPosition();

            // Track if peasant is stuck (not moving toward bed)
            if (lastPosition == null) {
                lastPosition = currentPos;
                stuckTimer = 0;
            } else if (lastPosition.equals(currentPos)) {
                // Peasant hasn't moved
                stuckTimer++;

                // If stuck for too long and should be sleeping, try teleport
                if (stuckTimer >= STUCK_THRESHOLD) {
                    double distanceToBed = currentPos.distSqr(peasant.getBedPos());

                    // Only teleport if reasonably far from bed and navigation is failing
                    if (distanceToBed > 16.0D && !peasant.getNavigation().isInProgress()) {
                        teleportToBed();
                    }
                }
            } else {
                // Peasant moved, reset stuck tracking
                lastPosition = currentPos;
                stuckTimer = 0;
            }
        }
    }

    /**
     * Teleports the peasant to their bed when pathfinding fails
     */
    public void teleportToBed() {
        if (peasant.getBedPos() != null && teleportCooldown <= 0) {
            BlockPos bedPos = peasant.getBedPos();

            // Find a safe position near the bed
            BlockPos teleportPos = findSafeTeleportPosition(bedPos);
            if (teleportPos != null) {
                // Teleport the peasant
                peasant.teleportTo(teleportPos.getX() + 0.5, teleportPos.getY(), teleportPos.getZ() + 0.5);
                peasant.getNavigation().stop();

                // Reset stuck tracking
                stuckTimer = 0;
                lastPosition = null;
                teleportCooldown = TELEPORT_COOLDOWN;

                // Play teleport sound
                peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                        SoundEvents.ENDERMAN_TELEPORT, SoundSource.NEUTRAL,
                        0.5F, 1.0F);
            }
        }
    }

    /**
     * Finds a safe position to teleport near the bed
     */
    private BlockPos findSafeTeleportPosition(BlockPos bedPos) {
        // Try positions around the bed
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = -1; y <= 1; y++) {
                    BlockPos checkPos = bedPos.offset(x, y, z);

                    // Check if position is safe (not inside blocks, has ground)
                    if (isSafeTeleportPosition(checkPos)) {
                        return checkPos;
                    }
                }
            }
        }

        // If no safe position found nearby, teleport directly to bed level
        return bedPos;
    }

    /**
     * Checks if a position is safe for teleportation
     */
    private boolean isSafeTeleportPosition(BlockPos pos) {
        BlockState groundState = peasant.level().getBlockState(pos.below());
        BlockState feetState = peasant.level().getBlockState(pos);
        BlockState headState = peasant.level().getBlockState(pos.above());

        // Must have solid ground and air for feet and head
        return groundState.isSolid() &&
                !feetState.isSolid() &&
                !headState.isSolid();
    }

    public void saveData(CompoundTag compound) {
        compound.putInt("StuckTimer", stuckTimer);
        compound.putInt("TeleportCooldown", teleportCooldown);
        if (lastPosition != null) {
            compound.putInt("LastPosX", lastPosition.getX());
            compound.putInt("LastPosY", lastPosition.getY());
            compound.putInt("LastPosZ", lastPosition.getZ());
        }
    }

    public void loadData(CompoundTag compound) {
        stuckTimer = compound.getInt("StuckTimer");
        teleportCooldown = compound.getInt("TeleportCooldown");
        if (compound.contains("LastPosX")) {
            lastPosition = new BlockPos(
                    compound.getInt("LastPosX"),
                    compound.getInt("LastPosY"),
                    compound.getInt("LastPosZ")
            );
        }
    }
}