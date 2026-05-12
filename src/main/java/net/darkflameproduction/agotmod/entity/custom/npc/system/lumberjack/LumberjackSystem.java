package net.darkflameproduction.agotmod.entity.custom.npc.system.lumberjack;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class LumberjackSystem {

    public enum LumberjackState {
        GOING_TO_JOB_BLOCK,
        IDLING,
        GOING_TO_TREE,
        CHOPPING,
        COLLECTING,
        REPLANTING,
        DEPOSITING
    }

    private static final int  SEARCH_RADIUS = 96;
    private static final int  MAX_LOGS      = 256;
    private static final int  MAX_LEAVES    = 2048;
    private static final int  CHOP_TICKS    = 30;
    private int stuckTimer = 0;
    private static final int MAX_STATE_TICKS = 600; // 30 seconds max per state

    private final Peasant_Entity peasant;
    private LumberjackState currentState = LumberjackState.GOING_TO_JOB_BLOCK;

    // Current tree
    private BlockPos      targetTreePos = null;
    private Set<BlockPos> treeLogs      = new LinkedHashSet<>();
    private Set<BlockPos> treeLeaves    = new LinkedHashSet<>();
    private BlockPos      stumpPos      = null;
    private Block         stumpLogType  = null;
    private BlockPos      lastChoppedPos = null;

    // Chopping progress
    private int     chopTicksElapsed = 0;
    private boolean choppingDone     = false;

    // Idle search timer
    private int idleSearchTimer = 0;

    public LumberjackSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    // ── Main tick ─────────────────────────────────────────────────────────────

    public void tick() {
        if (!(peasant.level() instanceof ServerLevel level)) return;
        if (peasant.shouldSleep()) return;

        stuckTimer++;
        if (stuckTimer > MAX_STATE_TICKS) {
            stuckTimer = 0;
            resetTree();
            setCurrentState(LumberjackState.IDLING);
            return;
        }

        switch (currentState) {
            case GOING_TO_JOB_BLOCK -> tickGoingToJobBlock();
            case IDLING              -> tickIdling(level);
            case GOING_TO_TREE       -> tickGoingToTree(level);
            case CHOPPING            -> tickChopping(level);
            case COLLECTING          -> {}
            case REPLANTING          -> tickReplanting(level);
            case DEPOSITING          -> {}
        }
    }

    // ── States ────────────────────────────────────────────────────────────────

    private void tickGoingToJobBlock() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;
        if (peasant.blockPosition().distSqr(jobBlock) < 16) {
            setCurrentState(LumberjackState.IDLING);
        }
    }

    private void tickIdling(ServerLevel level) {
        idleSearchTimer++;
        if (idleSearchTimer < 100) return;
        idleSearchTimer = 0;

        BlockPos tree = findNearestTree(level);
        if (tree != null) {
            if (lastChoppedPos != null && tree.distSqr(lastChoppedPos) < 9) return;
            targetTreePos = tree;
            setCurrentState(LumberjackState.GOING_TO_TREE);
        }
    }

    private void tickReplanting(ServerLevel level) {
        if (stumpPos == null || stumpLogType == null) {
            lastChoppedPos = stumpPos;
            resetTree();
            idleSearchTimer = 90;
            setCurrentState(LumberjackState.IDLING);
            return;
        }

        String logKey    = BuiltInRegistries.BLOCK.getKey(stumpLogType).toString();
        String saplingKey = getSaplingKey(logKey);

        if (saplingKey != null) {
            net.minecraft.resources.ResourceLocation saplingLoc =
                    net.minecraft.resources.ResourceLocation.tryParse(saplingKey);

            if (saplingLoc != null && BuiltInRegistries.BLOCK.containsKey(saplingLoc)) {
                Block saplingBlock = BuiltInRegistries.BLOCK.getValue(saplingLoc);
                if (saplingBlock != null) {
                    BlockState below = level.getBlockState(stumpPos.below());
                    if (level.getBlockState(stumpPos).isAir() &&
                            (below.is(net.minecraft.tags.BlockTags.DIRT) ||
                                    below.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK))) {
                        level.setBlock(stumpPos, saplingBlock.defaultBlockState(), 3);
                    }
                }
            }
        }

        lastChoppedPos = stumpPos;
        resetTree();
        idleSearchTimer = 90;
        setCurrentState(LumberjackState.IDLING);
    }


    private void tickGoingToTree(ServerLevel level) {
        if (targetTreePos == null) {
            setCurrentState(LumberjackState.IDLING);
            return;
        }

        double dx = peasant.getX() - (targetTreePos.getX() + 0.5);
        double dz = peasant.getZ() - (targetTreePos.getZ() + 0.5);
        double horizontalDistSq = dx * dx + dz * dz;

        if (horizontalDistSq < 25) {
            BlockState state = level.getBlockState(targetTreePos);
            if (!isLog(state)) {
                targetTreePos = null;
                setCurrentState(LumberjackState.IDLING);
                return;
            }

            treeLogs.clear();
            treeLeaves.clear();
            floodFillLogs(level, targetTreePos, state.getBlock(), treeLogs);
            floodFillLeaves(level, treeLogs, state.getBlock(), treeLeaves);

            stumpPos     = findLowestLog(treeLogs);
            stumpLogType = state.getBlock();

            chopTicksElapsed = 0;
            choppingDone     = false;
            setCurrentState(LumberjackState.CHOPPING);
            return;
        }

        if (peasant.getNavigation().isDone() && horizontalDistSq > 25) {
            targetTreePos = null;
            setCurrentState(LumberjackState.IDLING);
        }
    }

    private void tickChopping(ServerLevel level) {
        if (treeLogs.isEmpty()) {
            setCurrentState(LumberjackState.COLLECTING);
            return;
        }

        chopTicksElapsed++;

        if (targetTreePos != null) {
            double dx = targetTreePos.getX() - peasant.getX();
            double dz = targetTreePos.getZ() - peasant.getZ();
            peasant.setYRot((float)(Math.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F);
        }

        if (chopTicksElapsed >= treeLogs.size() * CHOP_TICKS) {
            for (BlockPos logPos : treeLogs) {
                BlockState logState = level.getBlockState(logPos);
                if (!isLog(logState)) continue;
                Block.dropResources(logState, level, logPos, null, peasant, ItemStack.EMPTY);
                level.removeBlock(logPos, false);
            }

            for (BlockPos leafPos : treeLeaves) {
                BlockState leafState = level.getBlockState(leafPos);
                if (!isLeaf(leafState)) continue;
                Block.dropResources(leafState, level, leafPos, null, peasant, ItemStack.EMPTY);
                level.removeBlock(leafPos, false);
            }

            treeLogs.clear();
            treeLeaves.clear();
            choppingDone = true;
            setCurrentState(LumberjackState.COLLECTING);
        }
    }

    // ── Tree finding ──────────────────────────────────────────────────────────

    private BlockPos findNearestTree(ServerLevel level) {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return null;

        BlockPos nearest    = null;
        double  nearestDist = Double.MAX_VALUE;

        for (int x = -SEARCH_RADIUS; x <= SEARCH_RADIUS; x += 2) {
            for (int z = -SEARCH_RADIUS; z <= SEARCH_RADIUS; z += 2) {
                for (int y = -16; y <= 32; y++) {
                    BlockPos candidate = jobBlock.offset(x, y, z);
                    BlockState state   = level.getBlockState(candidate);

                    if (!isLeaf(state)) continue;

                    BlockPos logPos = findConnectedLog(level, candidate);
                    if (logPos == null) continue;

                    BlockState logState = level.getBlockState(logPos);
                    Block logType = logState.getBlock();

                    BlockPos base = findTreeBase(level, logPos, logType);
                    if (base == null) continue;

                    BlockPos target = base.above();
                    BlockState targetState = level.getBlockState(target);
                    if (!isLog(targetState) || targetState.getBlock() != logType) continue;

                    if (lastChoppedPos != null && target.distSqr(lastChoppedPos) < 9) continue;

                    double dist = jobBlock.distSqr(target);
                    if (dist < nearestDist) {
                        nearestDist = dist;
                        nearest     = target;
                    }
                }
            }
        }

        return nearest;
    }

    private BlockPos findConnectedLog(ServerLevel level, BlockPos leafPos) {
        BlockState leafState = level.getBlockState(leafPos);

        // Extract wood type from leaf name to match log type
        String leafKey = BuiltInRegistries.BLOCK.getKey(leafState.getBlock()).getPath();
        // e.g. "oak_leaves" -> "oak"
        String woodType = leafKey.replace("_leaves", "");

        for (BlockPos nb : getAllNeighbours(leafPos)) {
            BlockState state = level.getBlockState(nb);
            if (!isLog(state)) continue;
            // Make sure the log type matches the leaf type
            String logKey = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
            if (logKey.contains(woodType)) return nb;
        }
        return null;
    }

    private BlockPos findTreeBase(ServerLevel level, BlockPos startLog, Block logType) {
        // Flood fill all connected logs
        Set<BlockPos>   logs  = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(startLog);
        logs.add(startLog);

        while (!queue.isEmpty() && logs.size() < MAX_LOGS) {
            BlockPos current = queue.poll();
            for (BlockPos nb : getAllNeighbours(current)) {
                if (logs.contains(nb)) continue;
                if (level.getBlockState(nb).getBlock() == logType) {
                    logs.add(nb);
                    queue.add(nb);
                }
            }
        }

        // Find the lowest log that has a ground block directly below it
        BlockPos base = null;
        for (BlockPos logPos : logs) {
            BlockState below = level.getBlockState(logPos.below());
            boolean isGround = below.is(net.minecraft.tags.BlockTags.DIRT)
                    || below.is(net.minecraft.world.level.block.Blocks.GRASS_BLOCK)
                    || below.is(net.minecraft.world.level.block.Blocks.PODZOL)
                    || below.is(net.minecraft.world.level.block.Blocks.MYCELIUM)
                    || below.is(net.minecraft.world.level.block.Blocks.ROOTED_DIRT)
                    || below.is(net.minecraft.world.level.block.Blocks.SAND)
                    || below.is(net.minecraft.world.level.block.Blocks.RED_SAND)
                    || below.is(net.minecraft.world.level.block.Blocks.GRAVEL);
            if (!isGround) continue;
            if (base == null || logPos.getY() < base.getY()) base = logPos;
        }

        return base;
    }




    // ── Flood fills ───────────────────────────────────────────────────────────

    private static void floodFillLogs(ServerLevel level, BlockPos start,
                                      Block logType, Set<BlockPos> logs) {
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(start);
        logs.add(start);

        while (!queue.isEmpty() && logs.size() < MAX_LOGS) {
            BlockPos current = queue.poll();
            for (BlockPos nb : getAllNeighbours(current)) {
                if (logs.contains(nb)) continue;
                if (level.getBlockState(nb).getBlock() == logType) {
                    logs.add(nb);
                    queue.add(nb);
                }
            }
        }
    }

    private static void floodFillLeaves(ServerLevel level, Set<BlockPos> logs,
                                        Block logType, Set<BlockPos> leaves) {
        // Collect all nearby foreign logs of the same type (not part of our tree)
        Set<BlockPos> foreignLogs = new HashSet<>();
        for (BlockPos logPos : logs) {
            for (int x = -12; x <= 12; x++) {
                for (int y = -4; y <= 16; y++) {
                    for (int z = -12; z <= 12; z++) {
                        BlockPos candidate = logPos.offset(x, y, z);
                        if (logs.contains(candidate)) continue;
                        if (foreignLogs.contains(candidate)) continue;
                        BlockState s = level.getBlockState(candidate);
                        if (s.getBlock() == logType) {
                            foreignLogs.add(candidate);
                        }
                    }
                }
            }
        }

        Queue<BlockPos> queue = new ArrayDeque<>();

        // Seed from logs
        for (BlockPos logPos : logs) {
            for (BlockPos nb : getAllNeighbours(logPos)) {
                if (logs.contains(nb) || leaves.contains(nb)) continue;
                BlockState ns = level.getBlockState(nb);
                if (!isLeafMatchingLog(ns, logType)) continue;
                if (isCloserToForeignLog(nb, logs, foreignLogs)) continue;
                leaves.add(nb);
                queue.add(nb);
            }
        }

        // Spread through connected leaves
        while (!queue.isEmpty() && leaves.size() < MAX_LEAVES) {
            BlockPos current        = queue.poll();
            double   closestLogDist = Double.MAX_VALUE;
            for (BlockPos logPos : logs) {
                double d = current.distSqr(logPos);
                if (d < closestLogDist) closestLogDist = d;
            }
            if (closestLogDist > 100) continue; // 10 blocks from nearest own log

            for (BlockPos nb : getAllNeighbours(current)) {
                if (logs.contains(nb) || leaves.contains(nb)) continue;
                BlockState ns = level.getBlockState(nb);
                if (!isLeafMatchingLog(ns, logType)) continue;
                if (isCloserToForeignLog(nb, logs, foreignLogs)) continue;
                leaves.add(nb);
                queue.add(nb);
            }
        }
    }

    private static boolean isCloserToForeignLog(BlockPos pos,
                                                Set<BlockPos> ownLogs,
                                                Set<BlockPos> foreignLogs) {
        if (foreignLogs.isEmpty()) return false;

        double closestOwn     = Double.MAX_VALUE;
        double closestForeign = Double.MAX_VALUE;

        for (BlockPos own : ownLogs) {
            double d = pos.distSqr(own);
            if (d < closestOwn) closestOwn = d;
        }

        for (BlockPos foreign : foreignLogs) {
            double d = pos.distSqr(foreign);
            if (d < closestForeign) closestForeign = d;
        }

        // Leaf belongs to the other tree if it's closer to a foreign log
        // Small bias toward own tree (0.9) so boundary leaves lean toward staying
        return closestForeign < closestOwn * 0.9;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static List<BlockPos> getAllNeighbours(BlockPos pos) {
        List<BlockPos> result = new ArrayList<>(26);
        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++)
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    result.add(pos.offset(x, y, z));
                }
        return result;
    }

    private static boolean isLog(BlockState state) {
        if (!(state.getBlock() instanceof RotatedPillarBlock)) return false;
        String key = BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();
        if (key.contains("weirwood")) return false;
        String path = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
        return path.endsWith("_log") || path.endsWith("_wood")
                || path.endsWith("_stem") || path.endsWith("_hyphae");
    }

    private static boolean isLeaf(BlockState state) {
        if (state.getBlock() instanceof LeavesBlock) return true;
        return BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath().endsWith("_leaves");
    }

    private static boolean isLeafMatchingLog(BlockState state, Block logType) {
        if (!isLeaf(state)) return false;
        String logKey  = BuiltInRegistries.BLOCK.getKey(logType).getPath();
        String leafKey = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
        String woodType = logKey.replace("_log", "").replace("_wood", "")
                .replace("_stem", "").replace("_hyphae", "");
        if (woodType.contains(":")) woodType = woodType.split(":")[1];
        return leafKey.contains(woodType);
    }

    private BlockPos findLowestLog(Set<BlockPos> logs) {
        BlockPos lowest = null;
        for (BlockPos pos : logs)
            if (lowest == null || pos.getY() < lowest.getY()) lowest = pos;
        return lowest;
    }

    private String getSaplingKey(String logKey) {
        String woodType = logKey.replace("_log", "").replace("_wood", "")
                .replace("_stem", "").replace("_hyphae", "");
        return woodType + "_sapling";
    }

    private void resetTree() {
        targetTreePos    = null;
        treeLogs.clear();
        treeLeaves.clear();
        stumpPos         = null;
        stumpLogType     = null;
        chopTicksElapsed = 0;
        choppingDone     = false;
        idleSearchTimer  = 0;
    }

    // ── Getters / Setters ─────────────────────────────────────────────────────

    public LumberjackState getCurrentState()               { return currentState; }
    public void setCurrentState(LumberjackState state) {
        if (this.currentState != state) {
            this.stuckTimer = 0;
        }
        this.currentState = state;
    }
    public boolean isChopping()                            { return currentState == LumberjackState.CHOPPING; }
    public BlockPos getTargetTreePos()                     { return targetTreePos; }

    private boolean isWithinRange() {
        if (targetTreePos == null) return false;
        double dx = peasant.getX() - (targetTreePos.getX() + 0.5);
        double dz = peasant.getZ() - (targetTreePos.getZ() + 0.5);
        return dx * dx + dz * dz < 9;
    }

    // ── Save / Load ───────────────────────────────────────────────────────────

    public void save(CompoundTag tag) {
        tag.putString("LumberjackState", currentState.name());
        if (targetTreePos != null) {
            tag.putLong("TargetTreePos", targetTreePos.asLong());
        }
    }

    public void load(CompoundTag tag) {
        if (tag.contains("LumberjackState")) {
            try {
                LumberjackState loaded = LumberjackState.valueOf(tag.getString("LumberjackState"));
                if (loaded == LumberjackState.CHOPPING
                        || loaded == LumberjackState.GOING_TO_TREE
                        || loaded == LumberjackState.COLLECTING) {
                    currentState = LumberjackState.IDLING;
                } else {
                    currentState = loaded;
                }
            } catch (IllegalArgumentException e) {
                currentState = LumberjackState.GOING_TO_JOB_BLOCK;
            }
        }
        if (tag.contains("TargetTreePos")) {
            targetTreePos = BlockPos.of(tag.getLong("TargetTreePos"));
        }
    }
}