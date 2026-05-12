package net.darkflameproduction.agotmod.event;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.*;

@EventBusSubscriber(modid = AGoTMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class TreeChopHandler {

    private static final int MAX_LOGS   = 256;
    private static final int MAX_LEAVES = 1024;

    // ── Break speed modifier ──────────────────────────────────────────────────
    @SubscribeEvent
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().isCrouching()) return;

        BlockState state = event.getState();
        if (!isLog(state)) return;

        BlockPos pos = event.getPosition().orElse(null);
        if (pos == null) return;

        ItemStack tool = event.getEntity().getMainHandItem();
        if (!isValidTool(tool)) return;

        int logCount = quickCountLogs(event.getEntity().level(), pos, state.getBlock());
        if (logCount <= 1) return;

        float multiplier = getSpeedMultiplier(logCount);
        event.setNewSpeed(event.getOriginalSpeed() / multiplier);
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;
        if (player.isCrouching()) return;

        BlockState state = level.getBlockState(event.getPos());
        if (!isLog(state)) return;

        if (!hasMatchingLeavesNearby(level, event.getPos(), state.getBlock())) return;

        Set<BlockPos> logs = new LinkedHashSet<>();
        floodFillLogs(level, event.getPos(), state.getBlock(), logs);

        if (logs.size() <= 1) return;

        Set<BlockPos> leaves = new LinkedHashSet<>();
        floodFillLeaves(level, logs, state.getBlock(), leaves);

        // Remove source block — vanilla handles it
        logs.remove(event.getPos());

        // Break logs
        int logsBroken = 0;
        for (BlockPos logPos : logs) {
            if (!level.getBlockState(logPos).is(state.getBlock())) continue;
            level.destroyBlock(logPos, true, player);
            logsBroken++;
        }

        // Break leaves with vanilla drop rates
        for (BlockPos leafPos : leaves) {
            BlockState leafState = level.getBlockState(leafPos);
            if (isLeaf(leafState)) {
                Block.dropResources(leafState, level, leafPos, null, player, player.getMainHandItem());
                level.removeBlock(leafPos, false);
            }
        }

        damageTool(player, player.getMainHandItem(), logsBroken);
    }

    // ── Log flood fill ────────────────────────────────────────────────────────

    private static void floodFillLogs(ServerLevel level, BlockPos start,
                                      Block logType, Set<BlockPos> logs) {
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(start);
        logs.add(start);

        while (!queue.isEmpty() && logs.size() < MAX_LOGS) {
            BlockPos current = queue.poll();
            // Use all 26 neighbours so diagonal logs are included
            for (BlockPos neighbour : getAllNeighbours(current)) {
                if (logs.contains(neighbour)) continue;
                if (level.getBlockState(neighbour).getBlock() == logType) {
                    logs.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
    }

    // ── Leaf flood fill — spreads through adjacent leaves from log positions ──

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

    // ── Leaf presence check ───────────────────────────────────────────────────

    private static boolean hasMatchingLeavesNearby(ServerLevel level,
                                                   BlockPos logPos, Block logType) {
        // First collect all connected logs, then check for leaves near any of them
        // This handles thick trunk trees where the broken log may be far from leaves
        Set<BlockPos> nearbyLogs = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(logPos);
        nearbyLogs.add(logPos);

        // Quick scan of connected logs capped at 32
        while (!queue.isEmpty() && nearbyLogs.size() < 32) {
            BlockPos current = queue.poll();
            for (BlockPos neighbour : getAllNeighbours(current)) {
                if (nearbyLogs.contains(neighbour)) continue;
                if (level.getBlockState(neighbour).getBlock() == logType) {
                    nearbyLogs.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }

        // Check for leaves within 8 blocks of any connected log
        for (BlockPos nearLog : nearbyLogs) {
            for (int x = -8; x <= 8; x++) {
                for (int y = -2; y <= 8; y++) {
                    for (int z = -8; z <= 8; z++) {
                        BlockState state = level.getBlockState(nearLog.offset(x, y, z));
                        if (isLeafMatchingLog(state, logType)) return true;
                    }
                }
            }
        }
        return false;
    }

    // ── Neighbour helpers ─────────────────────────────────────────────────────

    // 6 face neighbours only — for log connectivity
    private static List<BlockPos> getFaceNeighbours(BlockPos pos) {
        return List.of(
                pos.east(), pos.west(),
                pos.above(), pos.below(),
                pos.north(), pos.south()
        );
    }

    // 26 neighbours (all directions including diagonals) — for leaf spreading
    private static List<BlockPos> getAllNeighbours(BlockPos pos) {
        List<BlockPos> result = new ArrayList<>(26);
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    result.add(pos.offset(x, y, z));
                }
            }
        }
        return result;
    }

    // ── Quick log count for speed penalty ────────────────────────────────────

    private static int quickCountLogs(net.minecraft.world.level.Level level,
                                      BlockPos start, Block logType) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty() && visited.size() < MAX_LOGS) {
            BlockPos current = queue.poll();
            for (BlockPos neighbour : getFaceNeighbours(current)) {
                if (visited.contains(neighbour)) continue;
                if (level.getBlockState(neighbour).getBlock() == logType) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        return visited.size();
    }

    // ── Speed multiplier ──────────────────────────────────────────────────────

    private static float getSpeedMultiplier(int logCount) {
        return Math.max(1.0f, (float) logCount);
    }


    // ── Tool helpers ──────────────────────────────────────────────────────────

    private static boolean isValidTool(ItemStack stack) {
        return true; // any item or fist triggers the chop
    }

    private static void damageTool(ServerPlayer player, ItemStack tool, int logCount) {
        if (tool.isEmpty()) return; // fist
        String key = BuiltInRegistries.ITEM.getKey(tool.getItem()).getPath();
        if (!key.endsWith("_axe") && !key.equals("axe")) return; // non-axe items take no damage
        int damage = Math.max(1, logCount);
        tool.hurtAndBreak(damage, player, EquipmentSlot.MAINHAND);
    }

    // ── Block type checks ─────────────────────────────────────────────────────

    private static boolean isLog(BlockState state) {
        Block block = state.getBlock();
        if (!(block instanceof RotatedPillarBlock)) return false;
        String key = BuiltInRegistries.BLOCK.getKey(block).getPath();
        return key.endsWith("_log") || key.endsWith("_wood")
                || key.endsWith("_stem") || key.endsWith("_hyphae");
    }

    private static boolean isLeaf(BlockState state) {
        Block block = state.getBlock();
        if (block instanceof LeavesBlock) return true;
        String key = BuiltInRegistries.BLOCK.getKey(block).getPath();
        return key.endsWith("_leaves");
    }

    private static boolean isLeafMatchingLog(BlockState state, Block logType) {
        if (!isLeaf(state)) return false;
        String logKey  = BuiltInRegistries.BLOCK.getKey(logType).getPath();
        String leafKey = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();
        // e.g. "oak_log" -> "oak", then check "oak_leaves" contains "oak"
        String woodType = logKey.replace("_log", "")
                .replace("_wood", "")
                .replace("_stem", "")
                .replace("_hyphae", "");
        return leafKey.contains(woodType);
    }
}