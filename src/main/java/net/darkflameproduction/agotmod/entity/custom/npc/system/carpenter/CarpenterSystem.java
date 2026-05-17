package net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

/**
 * Handles daily carpenter work:
 *  - Requests logs, nails and sticks from town storage each day
 *  - Converts logs → planks (1 log = 4 planks, vanilla rate)
 *  - Produces planks, stairs, slabs, fences, fence gates, walls,
 *    doors, trapdoors, stools, chairs, arm chairs, tables
 *  - Deposits all produced goods into town storage for sale
 *  - Can also sell saplings that the town has in storage
 *
 * The carpenter works with the wood type assigned to his job block.
 */
public class CarpenterSystem {

    public enum CarpenterState {
        GOING_TO_JOB_BLOCK,
        WORKING,
        DEPOSITING
    }

    // ── Daily material consumption ────────────────────────────────────────────
    public static final int LOGS_PER_CYCLE   = 64;
    public static final int NAILS_PER_CYCLE  = 64;
    public static final int STICKS_PER_CYCLE = 64;
    public static final int PLANKS_PER_LOG   = 4;

    // ── State ─────────────────────────────────────────────────────────────────
    private CarpenterState currentState = CarpenterState.GOING_TO_JOB_BLOCK;
    private long           lastWorkDay  = -1;
    private int            workProgress = 0;

    public CarpenterState getCurrentState()            { return currentState; }
    public void setCurrentState(CarpenterState state)  { this.currentState = state; }
    public long getLastWorkDay()                        { return lastWorkDay; }
    public void setLastWorkDay(long day)                { this.lastWorkDay = day; }
    public int getWorkProgress()                        { return workProgress; }
    public void setWorkProgress(int p)                  { this.workProgress = p; }

    // ── Main work cycle ───────────────────────────────────────────────────────

    /**
     * Called by TownHallBlockEntity after supplying the carpenter with materials.
     * Returns all ItemStacks produced this cycle to be deposited into town storage.
     *
     * @param availableLogs   logs the town can supply (capped at LOGS_PER_CYCLE)
     * @param availableNails  nails the town can supply
     * @param availableSticks sticks the town can supply
     * @param woodType        e.g. "oak", "pine", "weirwood" — determines which items to produce
     */
    public List<ItemStack> doWorkCycle(long availableLogs, long availableNails,
                                       long availableSticks, String woodType) {
        List<ItemStack> produced = new ArrayList<>();

        long logs   = Math.min(availableLogs,   LOGS_PER_CYCLE);
        long nails  = Math.min(availableNails,  NAILS_PER_CYCLE);
        long sticks = Math.min(availableSticks, STICKS_PER_CYCLE);

        if (logs <= 0) return produced;

        long planks  = logs * PLANKS_PER_LOG;
        long planksL = planks;
        long nailsL  = nails;
        long sticksL = sticks;

        // Planks — sell half raw
        long sellPlanks = planksL / 2;
        planksL        -= sellPlanks;
        produce(produced, woodType + "_planks", sellPlanks, woodType);

        // Stairs: 6 planks → 4 stairs
        if (planksL >= 6) {
            long sets = planksL / 6;
            produce(produced, woodType + "_stairs", sets * 4, woodType);
            planksL -= sets * 6;
        }

        // Slabs: 3 planks → 6 slabs
        if (planksL >= 3) {
            long sets = planksL / 3;
            produce(produced, woodType + "_slabs", sets * 6, woodType);
            planksL -= sets * 3;
        }

        // Fences: 4 planks + 2 sticks → 3 fences
        if (planksL >= 4 && sticksL >= 2) {
            long sets = Math.min(planksL / 4, sticksL / 2);
            produce(produced, woodType + "_fence", sets * 3, woodType);
            planksL -= sets * 4;
            sticksL -= sets * 2;
        }

        // Fence gates: 2 planks + 4 sticks → 1 gate
        if (planksL >= 2 && sticksL >= 4) {
            long sets = Math.min(planksL / 2, sticksL / 4);
            produce(produced, woodType + "_fence_gate", sets, woodType);
            planksL -= sets * 2;
            sticksL -= sets * 4;
        }

        // Walls: 6 planks → 6 walls
        if (planksL >= 6) {
            long sets = planksL / 6;
            produce(produced, woodType + "_wall", sets * 6, woodType);
            planksL -= sets * 6;
        }

        // Doors: 6 planks → 3 doors
        if (planksL >= 6) {
            long sets = planksL / 6;
            produce(produced, woodType + "_door", sets * 3, woodType);
            planksL -= sets * 6;
        }

        // Trapdoors: 6 planks → 2 trapdoors
        if (planksL >= 6) {
            long sets = planksL / 6;
            produce(produced, woodType + "_trapdoor", sets * 2, woodType);
            planksL -= sets * 6;
        }

        // Stools: 2 planks + 2 sticks + 1 nail → 1 stool
        if (planksL >= 2 && sticksL >= 2 && nailsL >= 1) {
            long sets = Math.min(Math.min(planksL / 2, sticksL / 2), nailsL);
            produce(produced, woodType + "_stool", sets, woodType);
            planksL -= sets * 2;
            sticksL -= sets * 2;
            nailsL  -= sets;
        }

        // Chairs: 1 stool + 2 sticks + 1 nail → 1 chair
        long stools = countProduced(produced, "_stool");
        if (stools >= 1 && sticksL >= 2 && nailsL >= 1) {
            long sets = Math.min(Math.min(stools, sticksL / 2), nailsL);
            produce(produced, woodType + "_chair", sets, woodType);
            sticksL -= sets * 2;
            nailsL  -= sets;
        }

        // Tables: 3 planks + 2 sticks + 1 nail → 1 table
        if (planksL >= 3 && sticksL >= 2 && nailsL >= 1) {
            long sets = Math.min(Math.min(planksL / 3, sticksL / 2), nailsL);
            produce(produced, woodType + "_table", sets, woodType);
            planksL -= sets * 3;
            sticksL -= sets * 2;
            nailsL  -= sets;
        }

        // Arm chairs: 1 chair + 2 sticks + 1 nail → 1 arm chair
        long chairs = countProduced(produced, "_chair");
        if (chairs >= 1 && sticksL >= 2 && nailsL >= 1) {
            long sets = Math.min(Math.min(chairs, sticksL / 2), nailsL);
            produce(produced, woodType + "_arm_chair", sets, woodType);
            sticksL -= sets * 2;
            nailsL  -= sets;
        }

        return produced;
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void produce(List<ItemStack> out, String itemName, long count, String woodType) {
        if (count <= 0) return;
        Item item = resolveItem(itemName);
        if (item == null || item == Items.AIR) return;
        long rem = count;
        while (rem > 0) {
            int n = (int) Math.min(rem, item.getMaxStackSize(new ItemStack(item)));
            out.add(new ItemStack(item, n));
            rem -= n;
        }
    }

    private long countProduced(List<ItemStack> produced, String suffix) {
        return produced.stream()
                .filter(s -> BuiltInRegistries.ITEM.getKey(s.getItem()).getPath().endsWith(suffix))
                .mapToLong(ItemStack::getCount).sum();
    }

    private Item resolveItem(String itemName) {
        Item item = BuiltInRegistries.ITEM.getValue(ResourceLocation.fromNamespaceAndPath("agotmod", itemName));
        if (item != null && item != Items.AIR) return item;
        item = BuiltInRegistries.ITEM.getValue(ResourceLocation.fromNamespaceAndPath("minecraft", itemName));
        return (item != null) ? item : Items.AIR;
    }

    // ── ItemPricing helper ────────────────────────────────────────────────────

    public static boolean isCarpenterItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        String path = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        for (String suffix : CARPENTER_SUFFIXES) {
            if (path.endsWith(suffix)) return true;
        }
        return path.endsWith("_sapling") || path.equals("nail");
    }

    private static final String[] CARPENTER_SUFFIXES = {
            "_planks", "_stairs", "_slabs", "_slab",
            "_fence_gate", "_fence",
            "_wall", "_door", "_trapdoor",
            "_sign", "_hanging_sign",
            "_stool", "_arm_chair", "_chair", "_table",
            "_log", "_wood"
    };

    // ── NBT ───────────────────────────────────────────────────────────────────

    public void saveData(CompoundTag tag) {
        tag.putString("CarpenterState",      currentState.name());
        tag.putLong("CarpenterLastWorkDay",  lastWorkDay);
        tag.putInt("CarpenterWorkProgress",  workProgress);
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("CarpenterState")) {
            try { currentState = CarpenterState.valueOf(tag.getString("CarpenterState")); }
            catch (IllegalArgumentException e) { currentState = CarpenterState.GOING_TO_JOB_BLOCK; }
        }
        if (tag.contains("CarpenterLastWorkDay")) lastWorkDay   = tag.getLong("CarpenterLastWorkDay");
        if (tag.contains("CarpenterWorkProgress")) workProgress = tag.getInt("CarpenterWorkProgress");
    }
}