package net.darkflameproduction.agotmod.entity.custom.npc.goals.miner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.status.ChunkStatus;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The miner is stationary (rolls for an item every SWING_INTERVAL ticks while
 * stood at its barrel), but what it can roll isn't a fixed table anymore -
 * it's an exact census of the actual ore/stone blocks sitting in the loaded
 * chunks within RADIUS_CHUNKS of the job block, weighted by how much of each
 * is really there. The census is taken once (lazily, on first roll) and
 * cached for the lifetime of this goal instance.
 */
public class MinerGoal extends Goal {

    private final Peasant_Entity peasant;
    private int swingTimer = 0;
    private static final int SWING_INTERVAL = 40; // 2 seconds
    private static final int RADIUS_CHUNKS  = 5;

    // Regardless of how the census looks, only 1 in this many rolls is even
    // allowed to be an ore - the rest are plain rock. This keeps ore feeling
    // special even in an ore-rich area, instead of overwhelming rubble output.
    private static final int ORE_ROLL_DENOMINATOR = 20;

    // The three "plain rock" output items - everything else the census can
    // find (coal, every ore, the diamond group) counts as "ore" for the
    // 1-in-ORE_ROLL_DENOMINATOR gate below.
    private static final Set<String> STONE_FAMILY_ITEMS = Set.of(
            "minecraft:cobblestone", "minecraft:cobbled_deepslate", "minecraft:gravel"
    );

    // Not a real item id - a marker bucket for "one of the modded diamond
    // colours, chosen at random", matching the block's own loot table.
    private static final String DIAMOND_GROUP_KEY = "agotmod:diamonds_group";
    private static final String[] DIAMOND_VARIANTS = {
            "agotmod:yellow_diamond",
            "agotmod:transparent_diamond",
            "agotmod:black_diamond"
    };

    // Every block the miner recognizes, mapped to the item it actually yields
    // when mined (this mirrors each block's real loot table in this mod -
    // normal and deepslate variants of the same ore merge into one output
    // item, since they're the same material either way).
    private static final Map<String, String> ORE_BLOCK_TO_ITEM = new HashMap<>();
    static {
        // Plain rock - what mining stone/deepslate/gravel actually drops
        ORE_BLOCK_TO_ITEM.put("minecraft:stone",     "minecraft:cobblestone");
        ORE_BLOCK_TO_ITEM.put("minecraft:deepslate", "minecraft:cobbled_deepslate");
        ORE_BLOCK_TO_ITEM.put("minecraft:gravel",    "minecraft:gravel");

        // Vanilla ores
        ORE_BLOCK_TO_ITEM.put("minecraft:coal_ore",             "minecraft:coal");
        ORE_BLOCK_TO_ITEM.put("minecraft:deepslate_coal_ore",   "minecraft:coal");
        ORE_BLOCK_TO_ITEM.put("minecraft:iron_ore",             "minecraft:raw_iron");
        ORE_BLOCK_TO_ITEM.put("minecraft:deepslate_iron_ore",   "minecraft:raw_iron");
        ORE_BLOCK_TO_ITEM.put("minecraft:copper_ore",           "minecraft:raw_copper");
        ORE_BLOCK_TO_ITEM.put("minecraft:deepslate_copper_ore", "minecraft:raw_copper");
        ORE_BLOCK_TO_ITEM.put("minecraft:gold_ore",             "minecraft:raw_gold");
        ORE_BLOCK_TO_ITEM.put("minecraft:deepslate_gold_ore",   "minecraft:raw_gold");
        ORE_BLOCK_TO_ITEM.put("minecraft:lapis_ore",            "minecraft:lapis_lazuli");
        ORE_BLOCK_TO_ITEM.put("minecraft:deepslate_lapis_ore",  "minecraft:lapis_lazuli");

        // Mod ores - tin/silver
        ORE_BLOCK_TO_ITEM.put("agotmod:tin_ore",             "agotmod:raw_tin");
        ORE_BLOCK_TO_ITEM.put("agotmod:deepslate_tin_ore",   "agotmod:raw_tin");
        ORE_BLOCK_TO_ITEM.put("agotmod:silver_ore",          "agotmod:raw_silver");
        ORE_BLOCK_TO_ITEM.put("agotmod:deepslate_silver_ore","agotmod:raw_silver");

        // Mod ores - gemstones (each has its own block, unlike the diamond colors below)
        ORE_BLOCK_TO_ITEM.put("agotmod:amber_ore",               "agotmod:amber");
        ORE_BLOCK_TO_ITEM.put("agotmod:amber_deepslate_ore",     "agotmod:amber");
        ORE_BLOCK_TO_ITEM.put("agotmod:amethyst_ore",            "agotmod:amethyst");
        ORE_BLOCK_TO_ITEM.put("agotmod:amethyst_deepslate_ore",  "agotmod:amethyst");
        ORE_BLOCK_TO_ITEM.put("agotmod:bloodstone_ore",          "agotmod:bloodstone");
        ORE_BLOCK_TO_ITEM.put("agotmod:bloodstone_deepslate_ore","agotmod:bloodstone");
        ORE_BLOCK_TO_ITEM.put("agotmod:carnelian_ore",           "agotmod:carnelian");
        ORE_BLOCK_TO_ITEM.put("agotmod:carnelian_deepslate_ore", "agotmod:carnelian");
        ORE_BLOCK_TO_ITEM.put("agotmod:chalcedony_ore",          "agotmod:chalcedony");
        ORE_BLOCK_TO_ITEM.put("agotmod:chalcedony_deepslate_ore","agotmod:chalcedony");
        ORE_BLOCK_TO_ITEM.put("agotmod:garnet_ore",              "agotmod:garnet");
        ORE_BLOCK_TO_ITEM.put("agotmod:garnet_deepslate_ore",    "agotmod:garnet");
        ORE_BLOCK_TO_ITEM.put("agotmod:jade_ore",                "agotmod:jade");
        ORE_BLOCK_TO_ITEM.put("agotmod:jade_deepslate_ore",      "agotmod:jade");
        ORE_BLOCK_TO_ITEM.put("agotmod:jasper_ore",              "agotmod:jasper");
        ORE_BLOCK_TO_ITEM.put("agotmod:jasper_deepslate_ore",    "agotmod:jasper");
        ORE_BLOCK_TO_ITEM.put("agotmod:malachite_ore",           "agotmod:malachite");
        ORE_BLOCK_TO_ITEM.put("agotmod:malachite_deepslate_ore", "agotmod:malachite");
        ORE_BLOCK_TO_ITEM.put("agotmod:moonstone_ore",           "agotmod:moonstone");
        ORE_BLOCK_TO_ITEM.put("agotmod:moonstone_deepslate_ore", "agotmod:moonstone");
        ORE_BLOCK_TO_ITEM.put("agotmod:onyx_ore",                "agotmod:onyx");
        ORE_BLOCK_TO_ITEM.put("agotmod:onyx_deepslate_ore",      "agotmod:onyx");
        ORE_BLOCK_TO_ITEM.put("agotmod:opal_ore",                "agotmod:opal");
        ORE_BLOCK_TO_ITEM.put("agotmod:opal_deepslate_ore",      "agotmod:opal");
        ORE_BLOCK_TO_ITEM.put("agotmod:ruby_ore",                "agotmod:ruby");
        ORE_BLOCK_TO_ITEM.put("agotmod:ruby_deepslate_ore",      "agotmod:ruby");
        ORE_BLOCK_TO_ITEM.put("agotmod:sapphire_ore",            "agotmod:sapphire");
        ORE_BLOCK_TO_ITEM.put("agotmod:sapphire_deepslate_ore",  "agotmod:sapphire");
        ORE_BLOCK_TO_ITEM.put("agotmod:tigers_eye_ore",          "agotmod:tigers_eye");
        ORE_BLOCK_TO_ITEM.put("agotmod:tigers_eye_deepslate_ore","agotmod:tigers_eye");
        ORE_BLOCK_TO_ITEM.put("agotmod:topaz_ore",               "agotmod:topaz");
        ORE_BLOCK_TO_ITEM.put("agotmod:topaz_deepslate_ore",     "agotmod:topaz");
        ORE_BLOCK_TO_ITEM.put("agotmod:tourmaline_ore",          "agotmod:tourmaline");
        ORE_BLOCK_TO_ITEM.put("agotmod:tourmaline_deepslate_ore","agotmod:tourmaline");

        // Diamonds - the mod only has one diamond ore block (no separate block
        // per diamond colour); its loot table randomly drops one of the three
        // modded diamonds, so both bucket under this synthetic key and the
        // actual colour is rolled at spawn time in spawnItem().
        ORE_BLOCK_TO_ITEM.put("agotmod:diamonds_ore",           DIAMOND_GROUP_KEY);
        ORE_BLOCK_TO_ITEM.put("agotmod:diamonds_deepslate_ore", DIAMOND_GROUP_KEY);
    }

    // Lazily-computed, cached per job-block chunk so we don't re-scan every roll.
    private Map<String, Integer> availableItemCounts = null;
    private BlockPos             scannedJobBlock      = null;

    public MinerGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public void start() {
        swingTimer = 0;
        peasant.getMinerSystem().setCurrentMinerState(MinerSystem.MinerState.GOING_TO_JOB_BLOCK);
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        swingTimer = 0;
    }

    @Override
    public void tick() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        MinerSystem.MinerState state = peasant.getMinerSystem().getCurrentMinerState();

        if (state == MinerSystem.MinerState.GOING_TO_JOB_BLOCK) {
            double dist = peasant.distanceToSqr(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);

            if (dist <= 4.0D) {
                peasant.getNavigation().stop();
                peasant.getMinerSystem().setCurrentMinerState(MinerSystem.MinerState.MINING);
            } else {
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(
                            jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.7D);
                }
            }
            return;
        }

        // MINING state
        peasant.getLookControl().setLookAt(
                jobBlock.getX() + 0.5, jobBlock.getY() + 0.5, jobBlock.getZ() + 0.5);

        swingTimer++;
        if (swingTimer >= SWING_INTERVAL) {
            swingTimer = 0;
            peasant.triggerInteractAnimation();
            rollForItems();
        }
    }

    private void rollForItems() {
        if (peasant.level().isClientSide) return;

        Map<String, Integer> counts = getAvailableItemCounts();
        if (counts.isEmpty()) return; // nothing minable found nearby

        // Split the census into "plain rock" vs "ore" (everything else: coal,
        // every metal/gem ore, the diamond group).
        Map<String, Integer> stone = new HashMap<>();
        Map<String, Integer> ore   = new HashMap<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            (STONE_FAMILY_ITEMS.contains(entry.getKey()) ? stone : ore)
                    .put(entry.getKey(), entry.getValue());
        }

        boolean hasStone = !stone.isEmpty();
        boolean hasOre   = !ore.isEmpty();
        if (!hasStone && !hasOre) return;

        // 1-in-ORE_ROLL_DENOMINATOR chance of the roll even being allowed to be
        // an ore - unless only one family is actually present nearby, in which
        // case that's the only thing it can produce regardless of the gate.
        boolean mineOre = hasOre && (!hasStone || peasant.getRandom().nextInt(ORE_ROLL_DENOMINATOR) == 0);

        spawnWeighted(mineOre ? ore : stone);
    }

    /** Picks one entry from the given census, weighted by its counts, and spawns it. */
    private void spawnWeighted(Map<String, Integer> pool) {
        int total = 0;
        for (int c : pool.values()) total += c;
        if (total <= 0) return;

        int roll       = peasant.getRandom().nextInt(total);
        int cumulative = 0;
        for (Map.Entry<String, Integer> entry : pool.entrySet()) {
            cumulative += entry.getValue();
            if (roll < cumulative) {
                spawnItem(entry.getKey());
                return;
            }
        }
    }

    /** Returns the cached census, (re)scanning if the job block has changed since last time. */
    private Map<String, Integer> getAvailableItemCounts() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return Map.of();

        if (availableItemCounts != null && jobBlock.equals(scannedJobBlock)) {
            return availableItemCounts;
        }

        availableItemCounts = scanRadiusForItemCounts(jobBlock);
        scannedJobBlock     = jobBlock;
        return availableItemCounts;
    }

    /**
     * Exactly counts every recognized ore/stone block within RADIUS_CHUNKS
     * chunks of the job block, across every loaded chunk in that radius
     * (unloaded/ungenerated chunks are skipped rather than force-loaded).
     * Uses each chunk section's palette to get exact counts cheaply instead
     * of reading every individual block position.
     */
    private Map<String, Integer> scanRadiusForItemCounts(BlockPos jobBlock) {
        Map<String, Integer> counts = new HashMap<>();
        ChunkPos center = new ChunkPos(jobBlock);

        for (int dx = -RADIUS_CHUNKS; dx <= RADIUS_CHUNKS; dx++) {
            for (int dz = -RADIUS_CHUNKS; dz <= RADIUS_CHUNKS; dz++) {
                int cx = center.x + dx;
                int cz = center.z + dz;
                if (!peasant.level().hasChunk(cx, cz)) continue;

                ChunkAccess chunk = peasant.level().getChunk(cx, cz, ChunkStatus.FULL, false);
                if (chunk == null) continue;

                for (LevelChunkSection section : chunk.getSections()) {
                    if (section.hasOnlyAir()) continue;
                    section.getStates().count((state, count) -> {
                        ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
                        if (id == null) return;
                        String itemKey = ORE_BLOCK_TO_ITEM.get(id.toString());
                        if (itemKey != null) {
                            counts.merge(itemKey, count, Integer::sum);
                        }
                    });
                }
            }
        }

        return counts;
    }

    private void spawnItem(String itemKey) {
        if (itemKey.equals(DIAMOND_GROUP_KEY)) {
            itemKey = DIAMOND_VARIANTS[peasant.getRandom().nextInt(DIAMOND_VARIANTS.length)];
        }
        ResourceLocation loc = ResourceLocation.tryParse(itemKey);
        if (loc == null) return;
        net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
        if (item == null || item == Items.AIR) return;
        peasant.getInventorySystem().addItem(new ItemStack(item, 1));
    }
}
