package net.darkflameproduction.agotmod.entity.custom.npc.goals.quarry;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmerDepositTicketSystem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QuarryBarrelDropOffGoal extends Goal {

    private final Peasant_Entity peasant;
    private long lastDropOffDay = -1;

    // Every stone type the quarry can produce
    private static final Set<String> QUARRY_ITEMS = new HashSet<>();

    static {
        QUARRY_ITEMS.add("minecraft:stone");
        QUARRY_ITEMS.add("minecraft:cobblestone");
        QUARRY_ITEMS.add("minecraft:deepslate");
        QUARRY_ITEMS.add("minecraft:cobbled_deepslate");
        QUARRY_ITEMS.add("minecraft:diorite");
        QUARRY_ITEMS.add("minecraft:andesite");
        QUARRY_ITEMS.add("minecraft:granite");
        QUARRY_ITEMS.add("minecraft:calcite");
        QUARRY_ITEMS.add("minecraft:blackstone");
        QUARRY_ITEMS.add("minecraft:basalt");
        QUARRY_ITEMS.add("minecraft:sandstone");
        QUARRY_ITEMS.add("minecraft:red_sandstone");
    }

    public QuarryBarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_QUARRY)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;

        long dayTime = peasant.level().getDayTime() % 24000;
        long currentDay = peasant.level().getDayTime() / 24000;

        // Consume confirmation if waiting
        if (FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {
            return true;
        }

        // Post new ticket at end of work day
        if (dayTime >= 12000 && dayTime <= 13000
                && lastDropOffDay != currentDay
                && !FarmerDepositTicketSystem.hasPendingDeposit(peasant.getUUID())
                && !FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {

            if (!hasQuarryItems()) return false;
            postDepositTicket(currentDay);
        }

        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return false;
    }

    @Override
    public void start() {
        consumeConfirmationAndClearInventory();
    }

    @Override
    public void stop() {}

    private void postDepositTicket(long currentDay) {
        Map<String, Integer> items = new HashMap<>();
        var inventory = peasant.getInventorySystem().getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isQuarryItem(stack)) {
                String itemKey = getItemKey(stack);
                items.merge(itemKey, stack.getCount(), Integer::sum);
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
            String itemKey = entry.getKey();
            int toRemove = entry.getValue();

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
    }

    private boolean hasQuarryItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isQuarryItem(stack)) return true;
        }
        return false;
    }

    private boolean isQuarryItem(ItemStack stack) {
        return QUARRY_ITEMS.contains(getItemKey(stack));
    }

    private String getItemKey(ItemStack stack) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return itemId != null ? itemId.toString() : "unknown";
    }
}
