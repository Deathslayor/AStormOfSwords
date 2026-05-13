package net.darkflameproduction.agotmod.entity.custom.npc.goals.charcoalburner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner.CharcoalBurnerSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;

public class CharcoalBurnerGoal extends Goal {

    private static final int BURN_INTERVAL = 40; // 2 seconds per burn cycle

    // All log types — any block ending in _log, _wood, _stem, _hyphae
    private static final String[] LOG_SUFFIXES = { "_log", "_wood", "_stem", "_hyphae" };

    private final Peasant_Entity peasant;
    private int burnTick = 0;

    public CharcoalBurnerGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_CHARCOAL_BURNER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getCharcoalBurnerSystem().getLastCollectionDay() != currentDay) return false;
        if (peasant.getCharcoalBurnerSystem().getLogsCollected() == 0) return false;

        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_CHARCOAL_BURNER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public void start() {
        burnTick = 0;
        peasant.getCharcoalBurnerSystem().setCurrentState(
                CharcoalBurnerSystem.CharcoalBurnerState.GOING_TO_JOB_BLOCK);
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        burnTick = 0;
    }

    @Override
    public void tick() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        CharcoalBurnerSystem.CharcoalBurnerState state =
                peasant.getCharcoalBurnerSystem().getCurrentState();

        if (state == CharcoalBurnerSystem.CharcoalBurnerState.GOING_TO_JOB_BLOCK) {
            double dist = peasant.distanceToSqr(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);
            if (dist <= 4.0D) {
                peasant.getNavigation().stop();
                peasant.getCharcoalBurnerSystem().setCurrentState(
                        CharcoalBurnerSystem.CharcoalBurnerState.BURNING);
            } else {
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(
                            jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.7D);
                }
            }
            return;
        }

        // BURNING state
        peasant.getLookControl().setLookAt(
                jobBlock.getX() + 0.5, jobBlock.getY() + 0.5, jobBlock.getZ() + 0.5);

        burnTick++;
        if (burnTick >= BURN_INTERVAL) {
            burnTick = 0;
            tryBurn();
        }
    }

    /**
     * Consumes 1 log from inventory, produces 1 charcoal. 1:1 conversion.
     */
    private void tryBurn() {
        if (peasant.level().isClientSide) return;
        if (!peasant.getCharcoalBurnerSystem().hasLogCapacity()) return;

        String logKey = findLogInInventory();
        if (logKey == null) return;

        removeFromInventory(logKey, 1);

        // 1/10 chance to produce a charred log instead of charcoal
        if (peasant.level().getRandom().nextInt(10) == 0) {
            spawnItem("agotmod:charred_log", 1);
        } else {
            spawnItem("minecraft:charcoal", 1);
        }

        peasant.getCharcoalBurnerSystem().addLogsBurned(1);
        peasant.triggerInteractAnimation();
    }

    private String findLogInInventory() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.isEmpty()) continue;
            String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
            if (isLog(key) && stack.getCount() > 0) return key;
        }
        return null;
    }

    private boolean isLog(String itemKey) {
        String path = itemKey.contains(":") ? itemKey.split(":")[1] : itemKey;
        for (String suffix : LOG_SUFFIXES) {
            if (path.endsWith(suffix)) return true;
        }
        return false;
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
        net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.getValue(loc);
        if (item == null || item == Items.AIR) return;
        peasant.getInventorySystem().addItem(new ItemStack(item, count));
    }
}