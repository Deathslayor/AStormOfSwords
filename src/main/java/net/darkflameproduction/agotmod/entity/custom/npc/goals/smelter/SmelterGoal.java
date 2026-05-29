package net.darkflameproduction.agotmod.entity.custom.npc.goals.smelter;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.smelter.SmelterSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class SmelterGoal extends Goal {

    private static final int SMELT_INTERVAL = 40; // swing every 2 seconds

    // Ore â†’ Ingot in priority order: gold > silver > iron > copper > tin
    private static final LinkedHashMap<String, String> ORE_TO_INGOT = new LinkedHashMap<>();
    static {
        ORE_TO_INGOT.put("minecraft:raw_gold",   "minecraft:gold_ingot");
        ORE_TO_INGOT.put("agotmod:raw_silver",    "agotmod:silver_ingot");
        ORE_TO_INGOT.put("minecraft:raw_iron",    "minecraft:iron_ingot");
        ORE_TO_INGOT.put("minecraft:raw_copper",  "minecraft:copper_ingot");
        ORE_TO_INGOT.put("agotmod:raw_tin",       "agotmod:tin_ingot");
    }

    private static final String[] COAL_KEYS = { "minecraft:coal", "minecraft:charcoal" };

    private final Peasant_Entity peasant;
    private int smeltTick = 0;

    public SmelterGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_SMELTER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        // Don't work if morning collection hasn't happened yet today
        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getSmelterSystem().getLastCollectionDay() != currentDay) return false;

        // Don't work if no coal was collected
        if (peasant.getSmelterSystem().getCoalCollected() == 0) return false;

        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_SMELTER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public void start() {
        smeltTick = 0;
        peasant.getSmelterSystem().setCurrentState(SmelterSystem.SmelterState.GOING_TO_JOB_BLOCK);
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        smeltTick = 0;
    }

    @Override
    public void tick() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        SmelterSystem.SmelterState state = peasant.getSmelterSystem().getCurrentState();

        if (state == SmelterSystem.SmelterState.GOING_TO_JOB_BLOCK) {
            double dist = peasant.distanceToSqr(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);
            if (dist <= 4.0D) {
                peasant.getNavigation().stop();
                peasant.getSmelterSystem().setCurrentState(SmelterSystem.SmelterState.SMELTING);
            } else {
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(
                            jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.7D);
                }
            }
            return;
        }

        // SMELTING state
        peasant.getLookControl().setLookAt(
                jobBlock.getX() + 0.5, jobBlock.getY() + 0.5, jobBlock.getZ() + 0.5);

        smeltTick++;
        if (smeltTick >= SMELT_INTERVAL) {
            smeltTick = 0;
            trySmelt();
        }
    }

    /**
     * Consumes 1 coal + 8 ore (priority order) from inventory, adds 8 ingots.
     * If ore capacity is exhausted, does nothing.
     */
    private void trySmelt() {
        if (peasant.level().isClientSide) return;

        // Check ore capacity
        if (!peasant.getSmelterSystem().hasOreCapacity()) return;

        // Find coal in inventory
        String coalKey = findCoalInInventory();
        if (coalKey == null) return;

        // Collect up to 8 ores in priority order
        Map<String, Integer> oreToSmelt = collectOres(8);
        if (oreToSmelt.isEmpty()) return;

        int totalOre = oreToSmelt.values().stream().mapToInt(Integer::intValue).sum();
        if (totalOre == 0) return;

        // Consume 1 coal
        removeFromInventory(coalKey, 1);

        // Consume ores and produce ingots
        for (Map.Entry<String, Integer> entry : oreToSmelt.entrySet()) {
            String oreKey    = entry.getKey();
            int    oreCount  = entry.getValue();
            String ingotKey  = ORE_TO_INGOT.get(oreKey);

            removeFromInventory(oreKey, oreCount);
            spawnItem(ingotKey, oreCount);
        }

        peasant.getSmelterSystem().addOreSlotsFilled(totalOre);
        peasant.triggerInteractAnimation();
    }

    /**
     * Returns the first coal/charcoal key found in inventory, or null.
     */
    private String findCoalInInventory() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.isEmpty()) continue;
            String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
            for (String coalKey : COAL_KEYS) {
                if (coalKey.equals(key) && stack.getCount() > 0) return key;
            }
        }
        return null;
    }

    /**
     * Greedily collects up to `slots` ores from inventory in priority order.
     */
    private Map<String, Integer> collectOres(int slots) {
        Map<String, Integer> result = new LinkedHashMap<>();
        var inventory = peasant.getInventorySystem().getInventory();
        int remaining = slots;

        for (String oreKey : ORE_TO_INGOT.keySet()) {
            if (remaining <= 0) break;

            // Count available in inventory
            int available = 0;
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                if (!stack.isEmpty()) {
                    String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                    if (key.equals(oreKey)) available += stack.getCount();
                }
            }

            if (available <= 0) continue;

            int toTake = Math.min(available, remaining);
            result.put(oreKey, toTake);
            remaining -= toTake;
        }

        return result;
    }

    private void removeFromInventory(String itemKey, int amount) {
        var inventory = peasant.getInventorySystem().getInventory();
        int remaining = amount;
        for (int i = 0; i < inventory.getContainerSize() && remaining > 0; i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.isEmpty()) continue;
            String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
            if (key.equals(itemKey)) {
                int removed = Math.min(stack.getCount(), remaining);
                stack.shrink(removed);
                remaining -= removed;
                if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
            }
        }
    }

    private void spawnItem(String itemKey, int count) {
        ResourceLocation loc = ResourceLocation.tryParse(itemKey);
        if (loc == null) return;
        net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
        if (item == null || item == Items.AIR) return;
        peasant.getInventorySystem().addItem(new ItemStack(item, count));
    }
}
