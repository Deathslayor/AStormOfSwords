package net.darkflameproduction.agotmod.event;

import net.darkflameproduction.agotmod.AGoTMod;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlayerPlacedBlockTracker {

    private static final String SAVE_KEY = "agotmod_placed_blocks";

    private static final Map<ResourceKey<Level>, Set<BlockPos>> placedBlocks   = new HashMap<>();
    private static final Map<ResourceKey<Level>, Set<BlockPos>> bonemealExempt = new HashMap<>();

    // ── SavedData inner class ─────────────────────────────────────────────────

    public static class PlacedBlockData extends SavedData {

        private final Set<BlockPos> blocks = new HashSet<>();

        public PlacedBlockData() {}

        public static PlacedBlockData load(CompoundTag tag, HolderLookup.Provider provider) {
            PlacedBlockData data = new PlacedBlockData();
            ListTag list = tag.getList("blocks", Tag.TAG_LONG);
            for (Tag entry : list) {
                data.blocks.add(BlockPos.of(((LongTag) entry).getAsLong()));
            }
            return data;
        }

        @Override
        public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
            ListTag list = new ListTag();
            for (BlockPos pos : blocks) {
                list.add(LongTag.valueOf(pos.asLong()));
            }
            tag.put("blocks", list);
            return tag;
        }

        public Set<BlockPos> getBlocks() { return blocks; }
    }

    // ── SavedData access ──────────────────────────────────────────────────────

    private static PlacedBlockData getOrCreateData(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<PlacedBlockData>(
                        PlacedBlockData::new,
                        PlacedBlockData::load,
                        null
                ),
                SAVE_KEY
        );
    }

    // ── Level load/unload ─────────────────────────────────────────────────────

    @SubscribeEvent
    public static void onLevelLoad(LevelEvent.Load event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        ResourceKey<Level> dim = level.dimension();
        PlacedBlockData data = getOrCreateData(level);
        placedBlocks.put(dim, new HashSet<>(data.getBlocks()));
    }

    @SubscribeEvent
    public static void onLevelUnload(LevelEvent.Unload event) {
        if (!(event.getLevel() instanceof ServerLevel level)) return;
        ResourceKey<Level> dim = level.dimension();
        Set<BlockPos> current = placedBlocks.get(dim);
        if (current != null) {
            PlacedBlockData data = getOrCreateData(level);
            data.getBlocks().clear();
            data.getBlocks().addAll(current);
            data.setDirty();
        }
        placedBlocks.remove(dim);
        bonemealExempt.remove(dim);
    }

    // ── Bonemeal ──────────────────────────────────────────────────────────────

    @SubscribeEvent
    public static void onBonemeal(BonemealEvent event) {
        if (!(event.getLevel() instanceof Level level)) return;
        BlockState state = level.getBlockState(event.getPos());
        String key = BuiltInRegistries.BLOCK.getKey(state.getBlock()).getPath();

        if (key.endsWith("_sapling") || key.equals("sapling")
                || key.endsWith("_fungus") || key.endsWith("_propagule")) {
            ResourceKey<Level> dim = level.dimension();
            BlockPos origin = event.getPos();
            Set<BlockPos> exempt = bonemealExempt.computeIfAbsent(dim, k -> new HashSet<>());
            for (int x = -3; x <= 3; x++) {
                for (int y = 0; y <= 32; y++) {
                    for (int z = -3; z <= 3; z++) {
                        exempt.add(origin.offset(x, y, z).immutable());
                    }
                }
            }
        }
    }

    // ── Block place ───────────────────────────────────────────────────────────

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof net.minecraft.world.entity.player.Player)) return;
        ResourceKey<Level> dim = getDimKey(event.getLevel());
        if (dim == null) return;

        BlockPos pos = event.getPos().immutable();
        BlockState placed = event.getPlacedBlock();
        String key = BuiltInRegistries.BLOCK.getKey(placed.getBlock()).getPath();

        // Stripping a log — remove from tracking so stripped logs stay choppable
        if (key.startsWith("stripped_")) {
            Set<BlockPos> set = placedBlocks.get(dim);
            if (set != null && set.remove(pos)) {
                flushToSavedData(event.getLevel(), dim, set);
            }
            return;
        }

        // Saplings, fungus, propagules — grown trees are natural
        if (key.endsWith("_sapling") || key.equals("sapling")
                || key.endsWith("_fungus") || key.endsWith("_propagule")) return;

        // Only track logs and leaves placed directly by players
        if (!isLogKey(key) && !isLeafKey(key)) return;

        // If bonemeal-exempt, skip and clean up
        Set<BlockPos> exempt = bonemealExempt.get(dim);
        if (exempt != null && exempt.contains(pos)) {
            exempt.remove(pos);
            return;
        }

        Set<BlockPos> set = placedBlocks.computeIfAbsent(dim, k -> new HashSet<>());
        set.add(pos);
        flushToSavedData(event.getLevel(), dim, set);
    }

    // ── Block break ───────────────────────────────────────────────────────────

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        ResourceKey<Level> dim = getDimKey(event.getLevel());
        if (dim == null) return;
        BlockPos pos = event.getPos();

        Set<BlockPos> placed = placedBlocks.get(dim);
        if (placed != null && placed.remove(pos)) {
            flushToSavedData(event.getLevel(), dim, placed);
        }

        Set<BlockPos> exempt = bonemealExempt.get(dim);
        if (exempt != null) exempt.remove(pos);
    }

    // ── Public API ────────────────────────────────────────────────────────────

    public static boolean isPlayerPlaced(Level level, BlockPos pos) {
        ResourceKey<Level> dim = level.dimension();
        Set<BlockPos> set = placedBlocks.get(dim);
        return set != null && set.contains(pos);
    }

    public static void clearDimension(ResourceKey<Level> dim) {
        placedBlocks.remove(dim);
        bonemealExempt.remove(dim);
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static void flushToSavedData(LevelAccessor level, ResourceKey<Level> dim,
                                         Set<BlockPos> current) {
        if (!(level instanceof ServerLevel serverLevel)) return;
        PlacedBlockData data = getOrCreateData(serverLevel);
        data.getBlocks().clear();
        data.getBlocks().addAll(current);
        data.setDirty();
    }

    private static boolean isLogKey(String key) {
        return key.endsWith("_log") || key.endsWith("_wood")
                || key.endsWith("_stem") || key.endsWith("_hyphae")
                || key.startsWith("stripped_");
    }

    private static boolean isLeafKey(String key) {
        return key.endsWith("_leaves");
    }

    private static ResourceKey<Level> getDimKey(LevelAccessor level) {
        if (level instanceof Level l) return l.dimension();
        return null;
    }
}
