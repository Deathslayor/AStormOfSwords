package net.darkflameproduction.agotmod.entity.custom.npc.goals.behaviour;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Opens the doors an NPC walks through - wooden doors, fence gates, and the wooden trapdoor at the top of a ladder
 * it climbs - and closes them behind it.
 *
 * Only doors on the NPC's path are opened - the path has to go through the door's block, within the next few
 * steps. Doors it merely walks past (a wardrobe, a side room) stay shut: opening those swung their leaf into
 * the NPC's way, and it got stuck behind a door it never meant to use. A door it opened is closed once the NPC
 * is through it and away, unless someone is standing in the doorway.
 */
public class OpenAndCloseDoorGoal extends Goal {

    private static final int PATH_LOOKAHEAD = 3;           // nodes ahead of the NPC checked for doors
    private static final double OPEN_REACH_SQR = 2.25D;    // opens a door when within 1.5 blocks of it
    private static final double CLOSE_DISTANCE_SQR = 4.0D; // closes it once more than 2 blocks past it

    private final Peasant_Entity peasant;
    private final Set<BlockPos> openedBlocks = new HashSet<>();

    public OpenAndCloseDoorGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    @Override
    public boolean canUse() {
        if (peasant.isSleeping()) return false;
        return !openedBlocks.isEmpty() || hasDoorAhead();
    }

    @Override
    public boolean canContinueToUse() {
        return !peasant.isSleeping() && (!openedBlocks.isEmpty() || hasDoorAhead());
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void stop() {
        // Leave nothing open behind: close what is still ours (unless someone stands in it)
        for (BlockPos pos : new ArrayList<>(openedBlocks)) {
            tryClose(pos, true);
        }
    }

    @Override
    public void tick() {
        Set<BlockPos> ahead = doorsAhead();

        for (BlockPos pos : ahead) {
            BlockState state = peasant.level().getBlockState(pos);
            if (state.getValue(BlockStateProperties.OPEN)) continue;
            if (!withinReach(pos, state)) continue;
            setOpen(pos, state, true);
            openedBlocks.add(pos);
        }

        for (BlockPos pos : new ArrayList<>(openedBlocks)) {
            if (ahead.contains(pos)) continue; // still going through it
            tryClose(pos, false);
        }
    }

    /** Closes a door this NPC opened once it is clear of it; forgets it if it is gone or someone shut it. */
    private void tryClose(BlockPos pos, boolean leaving) {
        BlockState state = peasant.level().getBlockState(pos);
        if (!isOpenable(state) || !state.getValue(BlockStateProperties.OPEN)) {
            openedBlocks.remove(pos);
            return;
        }
        double distance = peasant.distanceToSqr(Vec3.atCenterOf(pos));
        if (!leaving && distance <= CLOSE_DISTANCE_SQR) return;
        if (isDoorwayOccupied(pos)) return;
        setOpen(pos, state, false);
        openedBlocks.remove(pos);
    }

    /** Someone (this NPC included) standing in the doorway - closing the door on them would trap them. */
    private boolean isDoorwayOccupied(BlockPos pos) {
        return !peasant.level().getEntitiesOfClass(LivingEntity.class, new AABB(pos).expandTowards(0, 1, 0),
                LivingEntity::isAlive).isEmpty();
    }

    private boolean hasDoorAhead() {
        return !doorsAhead().isEmpty();
    }

    /**
     * The doors, gates and trapdoors the NPC's path goes through in its next few steps (including the step it is
     * on), a door by its lower half.
     */
    private Set<BlockPos> doorsAhead() {
        Set<BlockPos> doors = new HashSet<>();
        Path path = peasant.getNavigation().getPath();
        if (path == null || path.isDone()) return doors;

        int from = Math.max(0, path.getNextNodeIndex() - 1);
        int to = Math.min(path.getNodeCount() - 1, path.getNextNodeIndex() + PATH_LOOKAHEAD);
        for (int i = from; i <= to; i++) {
            Node node = path.getNode(i);
            BlockPos door = openableAt(new BlockPos(node.x, node.y, node.z));
            if (door != null) doors.add(door);
        }
        return doors;
    }

    /** The door, gate or trapdoor an NPC can open at pos (a door by its lower half), null if there is none. */
    private BlockPos openableAt(BlockPos pos) {
        BlockState state = peasant.level().getBlockState(pos);
        if (!isOpenable(state)) return null;
        if (state.getBlock() instanceof DoorBlock) {
            return state.getValue(DoorBlock.HALF) == DoubleBlockHalf.UPPER ? pos.below() : pos;
        }
        return pos;
    }

    /** Opened by hand: wooden doors, fence gates and wooden trapdoors (not iron ones). */
    private static boolean isOpenable(BlockState state) {
        return DoorBlock.isWoodenDoor(state) || state.getBlock() instanceof FenceGateBlock
                || state.is(BlockTags.WOODEN_TRAPDOORS);
    }

    /**
     * Close enough to open it: a door or gate within reach beside it, a trapdoor right above or below it (on the
     * ladder) - opened before its head bumps the trapdoor on the way up.
     */
    private boolean withinReach(BlockPos pos, BlockState state) {
        double dx = peasant.getX() - (pos.getX() + 0.5), dz = peasant.getZ() - (pos.getZ() + 0.5);
        if (dx * dx + dz * dz > OPEN_REACH_SQR) return false;
        double dy = pos.getY() - peasant.getY();
        return state.is(BlockTags.WOODEN_TRAPDOORS) ? dy >= -1.5D && dy <= 2.5D : Math.abs(dy) <= 1.5D;
    }

    /** Opens or shuts it as a player would: the sound, and a gate swinging away from the NPC. */
    private void setOpen(BlockPos pos, BlockState state, boolean open) {
        var level = peasant.level();
        if (state.getBlock() instanceof DoorBlock door) {
            door.setOpen(peasant, level, state, pos, open);
            return;
        }
        BlockState changed = state.setValue(BlockStateProperties.OPEN, open);
        boolean gate = state.getBlock() instanceof FenceGateBlock;
        if (gate && open) {
            Direction facing = peasant.getDirection();
            if (state.getValue(FenceGateBlock.FACING) == facing.getOpposite()) changed = changed.setValue(FenceGateBlock.FACING, facing);
        }
        level.setBlock(pos, changed, 10);
        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            level.scheduleTick(pos, net.minecraft.world.level.material.Fluids.WATER, net.minecraft.world.level.material.Fluids.WATER.getTickDelay(level));
        }
        SoundEvent sound = gate ? (open ? SoundEvents.FENCE_GATE_OPEN : SoundEvents.FENCE_GATE_CLOSE)
                : (open ? SoundEvents.WOODEN_TRAPDOOR_OPEN : SoundEvents.WOODEN_TRAPDOOR_CLOSE);
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
        level.gameEvent(peasant, open ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
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
                // Can't read the world while the NPC is being loaded - tryClose forgets any that aren't open doors
                openedBlocks.add(pos);
            }
        }
    }
}
