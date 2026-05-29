package net.darkflameproduction.agotmod.entity.custom.npc.goals.tanner;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmerDepositTicketSystem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class TannerBarrelDropOffGoal extends Goal {

    private final Peasant_Entity peasant;
    private long lastDropOffDay = -1;

    private static final Set<String> DEPOSIT_ITEMS = new HashSet<>();
    static {
        DEPOSIT_ITEMS.add("minecraft:leather");
        DEPOSIT_ITEMS.add("minecraft:rabbit_hide");
        DEPOSIT_ITEMS.add("minecraft:string");
        DEPOSIT_ITEMS.add("minecraft:bundle");
        DEPOSIT_ITEMS.add("agotmod:boiled_leather");
    }

    public TannerBarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TANNER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;

        long dayTime    = peasant.level().getDayTime() % 24000;
        long currentDay = peasant.level().getDayTime() / 24000;

        if (FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {
            return true;
        }

        if (dayTime >= 12000 && dayTime <= 13000
                && lastDropOffDay != currentDay
                && !FarmerDepositTicketSystem.hasPendingDeposit(peasant.getUUID())
                && !FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {

            var barrelState = peasant.level().getBlockState(peasant.getJobBlockPos());
            if (!barrelState.is(ModBLocks.TANNER_BARREL.get())) return false;
            if (!hasDepositItems()) return false;

            postDepositTicket(currentDay);
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() { return false; }

    @Override
    public void start() { consumeConfirmationAndClearInventory(); }

    @Override
    public void stop() {}

    private void postDepositTicket(long currentDay) {
        Map<String, Integer> items = new HashMap<>();
        var inventory = peasant.getInventorySystem().getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && DEPOSIT_ITEMS.contains(getItemKey(stack))) {
                items.merge(getItemKey(stack), stack.getCount(), Integer::sum);
            }
        }

        if (!items.isEmpty()) {
            FarmerDepositTicketSystem.postDeposit(
                    peasant.getUUID(), items, currentDay, peasant.blockPosition());
        }
    }

    private void consumeConfirmationAndClearInventory() {
        FarmerDepositTicketSystem.DepositTicket confirmation =
                FarmerDepositTicketSystem.getConfirmation(peasant.getUUID());
        if (confirmation == null) return;

        var inventory = peasant.getInventorySystem().getInventory();

        for (Map.Entry<String, Integer> entry : confirmation.items.entrySet()) {
            String itemKey  = entry.getKey();
            int    toRemove = entry.getValue();

            for (int i = 0; i < inventory.getContainerSize() && toRemove > 0; i++) {
                ItemStack stack = inventory.getItem(i);
                if (!stack.isEmpty() && getItemKey(stack).equals(itemKey)) {
                    int removed = Math.min(stack.getCount(), toRemove);
                    stack.shrink(removed);
                    toRemove -= removed;
                    if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
                }
            }
        }

        FarmerDepositTicketSystem.consumeConfirmation(peasant.getUUID());
        lastDropOffDay = peasant.level().getDayTime() / 24000;
        peasant.triggerInteractAnimation();
        peasant.getTannerSystem().setCurrentState(
                net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerSystem.TannerState.GOING_TO_JOB_BLOCK);
    }

    private boolean hasDepositItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && DEPOSIT_ITEMS.contains(getItemKey(stack))) return true;
        }
        return false;
    }

    private String getItemKey(ItemStack stack) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return itemId != null ? itemId.toString() : "unknown";
    }
}
