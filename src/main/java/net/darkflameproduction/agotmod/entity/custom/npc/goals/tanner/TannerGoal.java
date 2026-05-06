package net.darkflameproduction.agotmod.entity.custom.npc.goals.tanner;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerSystem;
import net.darkflameproduction.agotmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class TannerGoal extends Goal {

    private static final int TICKS_PER_CRAFT = 16;

    private final Peasant_Entity peasant;
    private int craftTick = 0;

    public TannerGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TANNER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (!isJobBlockValid()) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getTannerSystem().getLastCollectionDay() != currentDay) return false;

        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 12000) return false;

        return peasant.getTannerSystem().getCurrentState() == TannerSystem.TannerState.WORKING
                && hasAnythingToCraft();
    }

    @Override
    public boolean canContinueToUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TANNER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        if (dayTime >= 12000) return false;
        return hasAnythingToCraft();
    }

    private boolean hasAnythingToCraft() {
        return hasItemInInventory("minecraft:rabbit_hide", 4)
                || (!hasItemInInventory("minecraft:rabbit_hide", 1)
                && (hasItemInInventory("minecraft:leather", 1)));
    }

    @Override
    public void start() {
        craftTick = 0;
        peasant.getTannerSystem().setCurrentState(TannerSystem.TannerState.WORKING);
        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock != null) {
            peasant.getNavigation().moveTo(
                    jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 1.5, 0.6D);
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        craftTick = 0;
    }

    @Override
    public void tick() {
        if (peasant.level().isClientSide) return;

        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        TannerSystem system = peasant.getTannerSystem();

        // Navigate to job block if needed
        double dist = peasant.distanceToSqr(
                jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);

        if (dist > 4.0D) {
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(
                        jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 1.5, 0.6D);
            }
            return;
        }

        peasant.getNavigation().stop();
        peasant.getLookControl().setLookAt(
                jobBlock.getX() + 0.5, jobBlock.getY() + 0.5, jobBlock.getZ() + 0.5);

        craftTick++;
        if (craftTick < TICKS_PER_CRAFT) return;
        craftTick = 0;

        tryCraft(system);
    }

    private void tryCraft(TannerSystem system) {
        // Step 1: Convert rabbit hides to leather (4 hides = 1 leather)
        if (hasItemInInventory("minecraft:rabbit_hide", 4)) {
            removeItemFromInventory("minecraft:rabbit_hide", 4);
            addItemToInventory(new ItemStack(Items.LEATHER, 1));
            system.setHidesProcessed(system.getHidesProcessed() + 4);
            peasant.triggerInteractAnimation();
            return;
        }

        // Only proceed to step 2/3 when no hides remain at all
        if (hasItemInInventory("minecraft:rabbit_hide", 1)) return;

        // Step 2: Craft bundles (1 leather + 1 string = 1 bundle)
        if (hasItemInInventory("minecraft:leather", 1) &&
                hasItemInInventory("minecraft:string", 1)) {
            removeItemFromInventory("minecraft:leather", 1);
            removeItemFromInventory("minecraft:string", 1);
            addItemToInventory(new ItemStack(Items.BUNDLE, 1));
            peasant.triggerInteractAnimation();
            return;
        }

        // Step 3: Convert remaining leather to boiled leather
        if (hasItemInInventory("minecraft:leather", 1)) {
            removeItemFromInventory("minecraft:leather", 1);
            addItemToInventory(new ItemStack(ModItems.BOILED_LEATHER.get(), 1));
            peasant.triggerInteractAnimation();
        }
    }

    private boolean isJobBlockValid() {
        BlockPos pos = peasant.getJobBlockPos();
        if (pos == null) return false;
        BlockState state = peasant.level().getBlockState(pos);
        return state.is(ModBLocks.TANNER_BARREL.get());
    }

    private boolean hasItemInInventory(String itemKey, int amount) {
        var inventory = peasant.getInventorySystem().getInventory();
        int count = 0;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                if (key.equals(itemKey)) count += stack.getCount();
            }
        }
        return count >= amount;
    }

    private void removeItemFromInventory(String itemKey, int amount) {
        var inventory = peasant.getInventorySystem().getInventory();
        int toRemove = amount;
        for (int i = 0; i < inventory.getContainerSize() && toRemove > 0; i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                String key = BuiltInRegistries.ITEM.getKey(stack.getItem()).toString();
                if (key.equals(itemKey)) {
                    int removed = Math.min(stack.getCount(), toRemove);
                    stack.shrink(removed);
                    toRemove -= removed;
                    if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
                }
            }
        }
    }

    private void addItemToInventory(ItemStack stack) {
        peasant.getInventorySystem().addItem(stack);
    }
}