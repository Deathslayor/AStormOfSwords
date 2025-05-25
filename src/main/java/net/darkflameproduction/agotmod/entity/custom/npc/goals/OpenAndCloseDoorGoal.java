package net.darkflameproduction.agotmod.entity.custom.npc.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenAndCloseDoorGoal extends Goal {
    private final Northern_Peasant_Entity peasant;
    private final List<BlockPos> openableBlocks = new ArrayList<>();
    private final Set<BlockPos> openedBlocks = new HashSet<>();

    public OpenAndCloseDoorGoal(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    @Override
    public boolean canUse() {
        if (peasant.isSleeping() && peasant.getSleepingPos().isPresent()) {
            return false;
        }

        openableBlocks.clear();
        BlockPos peasantPos = peasant.blockPosition();

        // Check for doors and fence gates in a 3x3x3 area around the peasant
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;

                    BlockPos checkPos = peasantPos.offset(x, y, z);
                    BlockState state = peasant.level().getBlockState(checkPos);

                    if (isOpenableBlock(state)) {
                        openableBlocks.add(checkPos);
                    }
                }
            }
        }

        return !openableBlocks.isEmpty() || !openedBlocks.isEmpty();
    }

    private boolean isOpenableBlock(BlockState state) {
        return state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock ||
                state.getBlock() instanceof net.minecraft.world.level.block.FenceGateBlock;
    }

    private boolean isBlockOpen(BlockState state) {
        if (state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock) {
            return state.getValue(net.minecraft.world.level.block.DoorBlock.OPEN);
        } else if (state.getBlock() instanceof net.minecraft.world.level.block.FenceGateBlock) {
            return state.getValue(net.minecraft.world.level.block.FenceGateBlock.OPEN);
        }
        return false;
    }

    private void setBlockOpen(BlockPos pos, BlockState state, boolean open) {
        if (state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock doorBlock) {
            doorBlock.setOpen(peasant, peasant.level(), state, pos, open);
        } else if (state.getBlock() instanceof net.minecraft.world.level.block.FenceGateBlock) {
            // For fence gates, we need to manually set the block state
            BlockState newState = state.setValue(net.minecraft.world.level.block.FenceGateBlock.OPEN, open);
            peasant.level().setBlock(pos, newState, 10);

            // Play the gate sound
            peasant.level().levelEvent(null, open ? 1008 : 1014, pos, 0);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return !openableBlocks.isEmpty() || !openedBlocks.isEmpty();
    }

    @Override
    public void start() {
        openedBlocks.clear();
    }

    @Override
    public void stop() {
        openableBlocks.clear();
        openedBlocks.clear();
    }

    @Override
    public void tick() {
        BlockPos peasantPos = peasant.blockPosition();

        // Continuously scan for all doors/gates in range and manage them
        Set<BlockPos> nearbyBlocks = new HashSet<>();

        // Find all doors/gates in 3x3x3 area
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;

                    BlockPos checkPos = peasantPos.offset(x, y, z);
                    BlockState state = peasant.level().getBlockState(checkPos);

                    if (isOpenableBlock(state)) {
                        nearbyBlocks.add(checkPos);
                    }
                }
            }
        }

        // Handle each nearby door/gate
        for (BlockPos pos : nearbyBlocks) {
            BlockState state = peasant.level().getBlockState(pos);

            if (!isOpenableBlock(state)) {
                continue;
            }

            double distance = peasant.distanceToSqr(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

            if (distance <= 4.0) {
                // Close enough - open it if it's closed
                if (!isBlockOpen(state)) {
                    setBlockOpen(pos, state, true);
                    openedBlocks.add(pos); // Track that WE opened this door
                }
            }
        }

        // Check all doors we've opened and close them if we're far enough away
        for (BlockPos pos : new ArrayList<>(openedBlocks)) {
            BlockState state = peasant.level().getBlockState(pos);

            if (!isOpenableBlock(state)) {
                openedBlocks.remove(pos);
                continue;
            }

            double distance = peasant.distanceToSqr(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

            // Close doors we opened when we're far enough away
            if (distance > 4.0 && isBlockOpen(state)) {
                setBlockOpen(pos, state, false);
                openedBlocks.remove(pos); // Stop tracking this door
            }
        }
    }

    // Persistence methods for door states
    public void saveOpenedBlocks(CompoundTag compound) {
        ListTag openedList = new ListTag();
        for (BlockPos pos : openedBlocks) {
            CompoundTag posTag = new CompoundTag();
            posTag.putInt("X", pos.getX());
            posTag.putInt("Y", pos.getY());
            posTag.putInt("Z", pos.getZ());
            openedList.add(posTag);
        }
        compound.put("OpenedBlocks", openedList);
    }

    public void loadOpenedBlocks(CompoundTag compound) {
        openedBlocks.clear();
        if (compound.contains("OpenedBlocks")) {
            ListTag openedList = compound.getList("OpenedBlocks", Tag.TAG_COMPOUND);
            for (int i = 0; i < openedList.size(); i++) {
                CompoundTag posTag = openedList.getCompound(i);
                BlockPos pos = new BlockPos(posTag.getInt("X"), posTag.getInt("Y"), posTag.getInt("Z"));

                // Validate that we should still track this door
                BlockState state = peasant.level().getBlockState(pos);
                if (isOpenableBlock(state) && isBlockOpen(state)) {
                    openedBlocks.add(pos);
                }
            }
        }
    }
}