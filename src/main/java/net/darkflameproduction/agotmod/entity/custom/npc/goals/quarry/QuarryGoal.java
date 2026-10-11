package net.darkflameproduction.agotmod.entity.custom.npc.goals.quarry;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.quarry.QuarrySystem;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The quarry works exactly like the miner (stationary, rolls for an item every
 * SWING_INTERVAL ticks while stood at its barrel) except its "loot table" isn't
 * fixed - it's an exact census of which of the 12 vanilla stone types actually
 * occur in the chunk the job block stands in, weighted by how much of each is
 * really there (same principle as the miner's radius scan, just over a single
 * chunk and limited to stone types instead of ores). The census is taken once
 * (lazily, on first roll) and cached for the lifetime of this goal instance.
 */
public class QuarryGoal extends Goal {

    private final Peasant_Entity peasant;
    private int swingTimer = 0;
    private static final int SWING_INTERVAL = 40; // 2 seconds

    private static final Set<String> STONE_TYPES = new HashSet<>();
    static {
        STONE_TYPES.add("minecraft:stone");
        STONE_TYPES.add("minecraft:cobblestone");
        STONE_TYPES.add("minecraft:deepslate");
        STONE_TYPES.add("minecraft:cobbled_deepslate");
        STONE_TYPES.add("minecraft:diorite");
        STONE_TYPES.add("minecraft:andesite");
        STONE_TYPES.add("minecraft:granite");
        STONE_TYPES.add("minecraft:calcite");
        STONE_TYPES.add("minecraft:blackstone");
        STONE_TYPES.add("minecraft:basalt");
        STONE_TYPES.add("minecraft:sandstone");
        STONE_TYPES.add("minecraft:red_sandstone");
    }

    // Vanilla stone/deepslate mined by hand normally comes out as its cobbled
    // form most of the time. Only these two vanilla types have a real cobbled
    // counterpart - everything else in STONE_TYPES (including modded stones,
    // should any be added later) always yields itself untouched.
    private static final Map<String, String> COBBLE_CONVERSION = Map.of(
            "minecraft:stone",     "minecraft:cobblestone",
            "minecraft:deepslate", "minecraft:cobbled_deepslate"
    );
    private static final double COBBLE_CHANCE = 2.0 / 3.0;

    // Lazily-computed, cached per job-block chunk so we don't re-scan every roll.
    private Map<String, Integer> stoneCounts   = null;
    private BlockPos             scannedChunkOf = null;

    public QuarryGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobTools.hasRequiredTool(peasant)) return false; // no work without the job's tool (see JobTools)
        if (!peasant.getJobType().equals(JobSystem.JOB_QUARRY)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (!net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobTools.hasRequiredTool(peasant)) return false;
        if (!peasant.getJobType().equals(JobSystem.JOB_QUARRY)) return false;
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
        peasant.getQuarrySystem().setCurrentQuarryState(QuarrySystem.QuarryState.GOING_TO_JOB_BLOCK);
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        swingTimer = 0;
    }

    // The work step runs once per tick with an iron tool, more often with faster tools and less with slower ones
    @Override
    public void tick() {
        for (int i = net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobTools.workTicks(peasant); i > 0; i--) {
            workTick();
        }
    }

    private void workTick() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        QuarrySystem.QuarryState state = peasant.getQuarrySystem().getCurrentQuarryState();

        if (state == QuarrySystem.QuarryState.GOING_TO_JOB_BLOCK) {
            double dist = peasant.distanceToSqr(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);

            if (dist <= 4.0D) {
                peasant.getNavigation().stop();
                peasant.getQuarrySystem().setCurrentQuarryState(QuarrySystem.QuarryState.MINING);
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

        Map<String, Integer> counts = getStoneCounts();
        if (counts.isEmpty()) return; // nothing minable in this chunk

        int total = 0;
        for (int c : counts.values()) total += c;
        if (total <= 0) return;

        int roll       = peasant.getRandom().nextInt(total);
        int cumulative = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            cumulative += entry.getValue();
            if (roll < cumulative) {
                mineBlock(entry.getKey());
                return;
            }
        }
    }

    /**
     * Turns a mined block type into the actual item produced. Stone and
     * deepslate come out as their cobbled form 2/3 of the time (matching how
     * they'd normally break when hand-mined) and stay uncobbled the other
     * 1/3; every other stone type always yields itself.
     */
    private void mineBlock(String blockKey) {
        String cobbled = COBBLE_CONVERSION.get(blockKey);
        boolean shouldCobble = cobbled != null && peasant.getRandom().nextDouble() < COBBLE_CHANCE;
        spawnItem(shouldCobble ? cobbled : blockKey);
    }

    /** Returns the cached census, (re)scanning if the job block has changed since last time. */
    private Map<String, Integer> getStoneCounts() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return Map.of();

        if (stoneCounts != null && jobBlock.equals(scannedChunkOf)) {
            return stoneCounts;
        }

        stoneCounts    = scanChunkForStoneCounts(jobBlock);
        scannedChunkOf = jobBlock;
        return stoneCounts;
    }

    /**
     * Exactly counts every recognized stone-type block in the job block's own
     * chunk, using each chunk section's palette for an exact count rather than
     * reading every individual block position (same technique as the miner's
     * radius scan). If the chunk isn't loaded, returns an empty census rather
     * than forcing it to load.
     */
    private Map<String, Integer> scanChunkForStoneCounts(BlockPos jobBlock) {
        Map<String, Integer> counts = new HashMap<>();

        ChunkPos chunkPos = new ChunkPos(jobBlock);
        if (!peasant.level().hasChunk(chunkPos.x, chunkPos.z)) return counts;

        ChunkAccess chunk = peasant.level().getChunk(chunkPos.x, chunkPos.z, ChunkStatus.FULL, false);
        if (chunk == null) return counts;

        for (LevelChunkSection section : chunk.getSections()) {
            if (section.hasOnlyAir()) continue;
            section.getStates().count((state, count) -> {
                ResourceLocation id = BuiltInRegistries.BLOCK.getKey(state.getBlock());
                if (id == null) return;
                String key = id.toString();
                if (STONE_TYPES.contains(key)) {
                    counts.merge(key, count, Integer::sum);
                }
            });
        }

        return counts;
    }

    private void spawnItem(String itemKey) {
        ResourceLocation loc = ResourceLocation.tryParse(itemKey);
        if (loc == null) return;
        net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
        if (item == null || item == Items.AIR) return;
        peasant.getInventorySystem().addItem(new ItemStack(item, 1));
    }
}
