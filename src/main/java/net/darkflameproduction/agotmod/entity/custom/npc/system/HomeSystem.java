package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

public class HomeSystem {
    private final Northern_Peasant_Entity peasant;

    // Home base system constants
    private static final int HOME_RADIUS_X = 60; // 120x120 area (60 blocks from center)
    private static final int HOME_RADIUS_Z = 60;
    private static final int HOME_RADIUS_Y = 32; // 64 block height (32 up/down from bed level)
    private static final int RETURN_HOME_DISTANCE = 140; // If they get beyond this distance, force return home

    private BlockPos homeBedPos; // The original bed that establishes home base

    public HomeSystem(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
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
                homeBedPos = null;
                peasant.setBedPos(null);
            }
        }
    }

    public BlockPos getHomeBedPos() {
        return homeBedPos;
    }

    public void setHomeBedPos(BlockPos pos) {
        this.homeBedPos = pos;
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
            setHomeBedPos(bedPos);
            // Always prefer the home bed for sleeping
            peasant.setBedPos(bedPos);
        }
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
            homeBedPos = new BlockPos(compound.getInt("HomeBedX"), compound.getInt("HomeBedY"), compound.getInt("HomeBedZ"));
        }
    }
}