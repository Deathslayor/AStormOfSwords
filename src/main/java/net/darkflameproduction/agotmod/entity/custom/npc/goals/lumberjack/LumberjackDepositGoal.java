package net.darkflameproduction.agotmod.entity.custom.npc.goals.lumberjack;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmerDepositTicketSystem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class LumberjackDepositGoal extends Goal {

    private final Peasant_Entity peasant;
    private long lastDropOffDay = -1;

    public LumberjackDepositGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public long getLastDropOffDay()          { return lastDropOffDay; }
    public void setLastDropOffDay(long day)  { this.lastDropOffDay = day; }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_LUMBERJACK)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;

        long dayTime    = peasant.level().getDayTime() % 24000;
        long currentDay = peasant.level().getDayTime() / 24000;

        // If there's a pending confirmation, process it
        if (FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) return true;

        // Deposit window: evening (12000-13000), once per day, with items
        if (dayTime >= 12000 && dayTime <= 13000
                && lastDropOffDay != currentDay
                && !FarmerDepositTicketSystem.hasPendingDeposit(peasant.getUUID())
                && !FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {

            if (!hasLumberjackItems()) return false;
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
            if (!stack.isEmpty() && isLumberjackItem(stack)) {
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
    }

    private boolean hasLumberjackItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isLumberjackItem(stack)) return true;
        }
        return false;
    }

    /**
     * A lumberjack item is anything collected from trees —
     * logs, leaves, saplings, sticks, apples, etc.
     * Excludes tools (axes) so he keeps his iron axe.
     */
    private boolean isLumberjackItem(ItemStack stack) {
        if (stack.isEmpty()) return false;
        if (stack.getItem() instanceof AxeItem) return false;
        if (stack.has(net.minecraft.core.component.DataComponents.FOOD)) return false;

        String key = getItemKey(stack); // now returns full key like "minecraft:stick"

        if (key.endsWith("_log"))      return true;
        if (key.endsWith("_wood"))     return true;
        if (key.endsWith("_stem"))     return true;
        if (key.endsWith("_hyphae"))   return true;
        if (key.endsWith("_sapling"))  return true;
        if (key.endsWith("_leaves"))   return true;
        if (key.equals("minecraft:stick"))       return true;
        if (key.equals("minecraft:apple"))       return true;
        if (key.equals("minecraft:cocoa_beans")) return true;

        return false;
    }

    private String getItemKey(ItemStack stack) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return itemId != null ? itemId.toString() : "unknown"; // full key e.g. "minecraft:stick"
    }
}