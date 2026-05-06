package net.darkflameproduction.agotmod.entity.custom.npc.system.sleep;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;

public class HomeSystem {
    private final Peasant_Entity peasant;

    // Home base system constants
    private static final int HOME_RADIUS_X = 60; // 120x120 area (60 blocks from center)
    private static final int HOME_RADIUS_Z = 60;
    private static final int HOME_RADIUS_Y = 32; // 64 block height (32 up/down from bed level)
    private static final int RETURN_HOME_DISTANCE = 140; // If they get beyond this distance, force return home

    private BlockPos homeBedPos; // The original bed that establishes home base

    // NEW: Home bed broadcast timer
    private int homeBedBroadcastTimer = 0;

    public HomeSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        // NEW: Broadcast home bed claim every 40 ticks if we have a home bed
        if (!peasant.level().isClientSide && homeBedPos != null) {
            homeBedBroadcastTimer++;
            if (homeBedBroadcastTimer >= 40) {
                SimpleBedWarningSystem.broadcastHomeBedClaim(peasant.getUUID(), homeBedPos);
                homeBedBroadcastTimer = 0;
            }
        } else {
            homeBedBroadcastTimer = 0;
        }

        // Check if peasant is too far from home and force return
        if (!peasant.level().isClientSide && isTooFarFromHome() && !peasant.isSleeping()) {
            // Cancel current navigation and head back towards home
            peasant.getNavigation().stop();
            BlockPos homeCenter = getHomeCenter();
            peasant.getNavigation().moveTo(homeCenter.getX(), homeCenter.getY(), homeCenter.getZ(), 0.8D);
        }

        // Check if home bed still exists
        if (!peasant.level().isClientSide && homeBedPos != null) {
            BlockState homeBedState = peasant.level().getBlockState(homeBedPos);
            if (!(homeBedState.getBlock() instanceof BedBlock)) {
                // Home bed is gone, clear it and allow them to establish a new home
                // NEW: Remove home bed claim when destroyed
                SimpleBedWarningSystem.removeHomeBedClaimForPosition(peasant.getUUID(), homeBedPos);
                homeBedPos = null;
                peasant.setBedPos(null);
            }
        }
    }

    public BlockPos getHomeBedPos() {
        return homeBedPos;
    }

    public void setHomeBedPos(BlockPos pos) {
        // NEW: Handle home bed claim updates
        if (homeBedPos != null) {
            SimpleBedWarningSystem.removeHomeBedClaimForPosition(peasant.getUUID(), homeBedPos);
        }

        this.homeBedPos = pos;

        if (pos != null) {
            SimpleBedWarningSystem.broadcastHomeBedClaim(peasant.getUUID(), pos);
        }
    }

    public boolean hasHomeBed() {
        return homeBedPos != null;
    }

    public boolean isWithinHomeArea(BlockPos pos) {
        if (homeBedPos == null) {
            return true; // No home established yet, can go anywhere
        }

        int deltaX = Math.abs(pos.getX() - homeBedPos.getX());
        int deltaZ = Math.abs(pos.getZ() - homeBedPos.getZ());
        int deltaY = Math.abs(pos.getY() - homeBedPos.getY());

        return deltaX <= HOME_RADIUS_X && deltaZ <= HOME_RADIUS_Z && deltaY <= HOME_RADIUS_Y;
    }

    public boolean isTooFarFromHome() {
        if (homeBedPos == null) {
            return false; // No home established yet
        }

        BlockPos currentPos = peasant.blockPosition();
        double distanceSquared = homeBedPos.distSqr(currentPos);
        return distanceSquared > (RETURN_HOME_DISTANCE * RETURN_HOME_DISTANCE);
    }

    public BlockPos getHomeCenter() {
        return homeBedPos != null ? homeBedPos : peasant.blockPosition();
    }

    public void establishHomeBed(BlockPos bedPos) {
        if (homeBedPos == null && bedPos != null) {
            setHomeBedPos(bedPos); // This will handle the claim broadcast
            // Always prefer the home bed for sleeping
            peasant.setBedPos(bedPos);
        }
    }

    // NEW: Cleanup method for when NPC is removed
    public void onRemove() {
        SimpleBedWarningSystem.removeHomeBedClaim(peasant.getUUID());
    }

    public void saveData(CompoundTag compound) {
        // Save home bed position
        if (homeBedPos != null) {
            compound.putInt("HomeBedX", homeBedPos.getX());
            compound.putInt("HomeBedY", homeBedPos.getY());
            compound.putInt("HomeBedZ", homeBedPos.getZ());
        }
    }

    public void loadData(CompoundTag compound) {
        // Load home bed position
        if (compound.contains("HomeBedX")) {
            BlockPos loadedHomeBed = new BlockPos(compound.getInt("HomeBedX"), compound.getInt("HomeBedY"), compound.getInt("HomeBedZ"));
            setHomeBedPos(loadedHomeBed); // This will handle the claim broadcast
        }
    }
}