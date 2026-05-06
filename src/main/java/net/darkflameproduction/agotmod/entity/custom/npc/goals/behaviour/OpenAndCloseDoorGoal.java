package net.darkflameproduction.agotmod.entity.custom.npc.goals.behaviour;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OpenAndCloseDoorGoal extends Goal {
    private final Peasant_Entity peasant;
    private final List<BlockPos> openableBlocks = new ArrayList<>();
    private final Set<BlockPos> openedBlocks = new HashSet<>();

    public OpenAndCloseDoorGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    @Override
    public boolean canUse() {
        if (peasant.isSleeping() && peasant.getSleepingPos().isPresent()) return false;

        openableBlocks.clear();
        BlockPos peasantPos = peasant.blockPosition();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    BlockPos checkPos = peasantPos.offset(x, y, z);
                    BlockState state = peasant.level().getBlockState(checkPos);
                    if (isOpenableBlock(state)) openableBlocks.add(checkPos);
                }
            }
        }

        return !openableBlocks.isEmpty() || !openedBlocks.isEmpty();
    }

    private boolean isOpenableBlock(BlockState state) {
        return state.getBlock() instanceof DoorBlock;
    }

    private boolean isBlockOpen(BlockState state) {
        if (state.getBlock() instanceof DoorBlock) {
            return state.getValue(DoorBlock.OPEN);
        }
        return false;
    }

    private void setBlockOpen(BlockPos pos, BlockState state, boolean open) {
        if (state.getBlock() instanceof DoorBlock doorBlock) {
            doorBlock.setOpen(peasant, peasant.level(), state, pos, open);
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
        Set<BlockPos> nearbyBlocks = new HashSet<>();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    BlockPos checkPos = peasantPos.offset(x, y, z);
                    BlockState state = peasant.level().getBlockState(checkPos);
                    if (isOpenableBlock(state)) nearbyBlocks.add(checkPos);
                }
            }
        }

        for (BlockPos pos : nearbyBlocks) {
            BlockState state = peasant.level().getBlockState(pos);
            if (!isOpenableBlock(state)) continue;

            double distance = peasant.distanceToSqr(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            if (distance <= 4.0 && !isBlockOpen(state)) {
                setBlockOpen(pos, state, true);
                openedBlocks.add(pos);
            }
        }

        for (BlockPos pos : new ArrayList<>(openedBlocks)) {
            BlockState state = peasant.level().getBlockState(pos);
            if (!isOpenableBlock(state)) {
                openedBlocks.remove(pos);
                continue;
            }

            double distance = peasant.distanceToSqr(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            if (distance > 4.0 && isBlockOpen(state)) {
                setBlockOpen(pos, state, false);
                openedBlocks.remove(pos);
            }
        }
    }

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
                BlockState state = peasant.level().getBlockState(pos);
                if (isOpenableBlock(state) && isBlockOpen(state)) openedBlocks.add(pos);
            }
        }
    }
}