package net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

/**
 * Persists the carpenter's daily state (job state machine, last work day) and
 * classifies which items count as carpenter goods for the trading GUI. The
 * actual crafting logic (logs → planks → furniture) lives in CarpenterGoal.
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

    public CarpenterState getCurrentState()            { return currentState; }
    public void setCurrentState(CarpenterState state)  { this.currentState = state; }
    public long getLastWorkDay()                        { return lastWorkDay; }
    public void setLastWorkDay(long day)                { this.lastWorkDay = day; }

    // ── ItemPricing helper ────────────────────────────────────────────────────

    public static boolean isCarpenterItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        String path = BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath();
        for (String suffix : CARPENTER_SUFFIXES) {
            if (path.endsWith(suffix)) return true;
        }
        return path.endsWith("_sapling") || path.equals("nail") || path.equals("stick");
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
    }

    public void loadData(CompoundTag tag) {
        if (tag.contains("CarpenterState")) {
            try { currentState = CarpenterState.valueOf(tag.getString("CarpenterState")); }
            catch (IllegalArgumentException e) { currentState = CarpenterState.GOING_TO_JOB_BLOCK; }
        }
        if (tag.contains("CarpenterLastWorkDay")) lastWorkDay   = tag.getLong("CarpenterLastWorkDay");
    }
}
