package net.darkflameproduction.agotmod.entity.custom.npc.goals.miner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmerDepositTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class MinerBarrelDropOffGoal extends Goal {

    private final Peasant_Entity peasant;
    private long lastDropOffDay = -1;

    // All items the miner can produce
    private static final java.util.Set<String> MINER_ITEMS = new java.util.HashSet<>();

    static {
        // Rubble
        MINER_ITEMS.add("minecraft:cobblestone");
        MINER_ITEMS.add("minecraft:cobbled_deepslate");
        MINER_ITEMS.add("minecraft:gravel");
        // Common ores
        MINER_ITEMS.add("minecraft:coal");
        MINER_ITEMS.add("minecraft:raw_iron");
        MINER_ITEMS.add("minecraft:raw_copper");
        MINER_ITEMS.add("agotmod:raw_tin");
        // Rare ores
        MINER_ITEMS.add("agotmod:raw_silver");
        MINER_ITEMS.add("minecraft:raw_gold");
        MINER_ITEMS.add("minecraft:lapis_lazuli");
        // Gemstones
        MINER_ITEMS.add("agotmod:amber");
        MINER_ITEMS.add("agotmod:amethyst");
        MINER_ITEMS.add("agotmod:bloodstone");
        MINER_ITEMS.add("agotmod:carnelian");
        MINER_ITEMS.add("agotmod:chalcedony");
        MINER_ITEMS.add("agotmod:garnet");
        MINER_ITEMS.add("agotmod:jade");
        MINER_ITEMS.add("agotmod:jasper");
        MINER_ITEMS.add("agotmod:malachite");
        MINER_ITEMS.add("agotmod:moonstone");
        MINER_ITEMS.add("agotmod:onyx");
        MINER_ITEMS.add("agotmod:opal");
        MINER_ITEMS.add("agotmod:ruby");
        MINER_ITEMS.add("agotmod:sapphire");
        MINER_ITEMS.add("agotmod:tigers_eye");
        MINER_ITEMS.add("agotmod:topaz");
        MINER_ITEMS.add("agotmod:tourmaline");
        MINER_ITEMS.add("agotmod:yellow_diamond");
        MINER_ITEMS.add("agotmod:transparent_diamond");
        MINER_ITEMS.add("agotmod:black_diamond");
    }

    public MinerBarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public long getLastDropOffDay() { return lastDropOffDay; }
    public void setLastDropOffDay(long day) { this.lastDropOffDay = day; }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) return false;
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

            if (!hasMinerItems()) return false;
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
            if (!stack.isEmpty() && isMinerItem(stack)) {
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

    private boolean hasMinerItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isMinerItem(stack)) return true;
        }
        return false;
    }

    private boolean isMinerItem(ItemStack stack) {
        return MINER_ITEMS.contains(getItemKey(stack));
    }

    private String getItemKey(ItemStack stack) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return itemId != null ? itemId.toString() : "unknown";
    }
}