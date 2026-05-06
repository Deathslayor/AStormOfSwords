package net.darkflameproduction.agotmod.entity.custom.npc.goals.animalherder;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmerDepositTicketSystem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnimalHerderDepositGoal extends Goal {

    private final Peasant_Entity peasant;
    private long lastDropOffDay = -1;

    private static final Set<String> CATTLE_ITEMS  = new HashSet<>();
    private static final Set<String> CHICKEN_ITEMS = new HashSet<>();
    private static final Set<String> PIG_ITEMS     = new HashSet<>();
    private static final Set<String> SHEEP_ITEMS   = new HashSet<>();

    static {
        CATTLE_ITEMS.add("minecraft:leather");
        CATTLE_ITEMS.add("minecraft:beef");
        // wheat excluded — breeding item

        CHICKEN_ITEMS.add("minecraft:feather");
        CHICKEN_ITEMS.add("minecraft:chicken");
        CHICKEN_ITEMS.add("minecraft:egg");
        // wheat_seeds excluded — breeding item

        PIG_ITEMS.add("minecraft:porkchop");
        // carrot excluded — breeding item

        SHEEP_ITEMS.add("minecraft:mutton");
        SHEEP_ITEMS.add("minecraft:white_wool");
        SHEEP_ITEMS.add("minecraft:orange_wool");
        SHEEP_ITEMS.add("minecraft:magenta_wool");
        SHEEP_ITEMS.add("minecraft:light_blue_wool");
        SHEEP_ITEMS.add("minecraft:yellow_wool");
        SHEEP_ITEMS.add("minecraft:lime_wool");
        SHEEP_ITEMS.add("minecraft:pink_wool");
        SHEEP_ITEMS.add("minecraft:gray_wool");
        SHEEP_ITEMS.add("minecraft:light_gray_wool");
        SHEEP_ITEMS.add("minecraft:cyan_wool");
        SHEEP_ITEMS.add("minecraft:purple_wool");
        SHEEP_ITEMS.add("minecraft:blue_wool");
        SHEEP_ITEMS.add("minecraft:brown_wool");
        SHEEP_ITEMS.add("minecraft:green_wool");
        SHEEP_ITEMS.add("minecraft:red_wool");
        SHEEP_ITEMS.add("minecraft:black_wool");
        // wheat excluded — breeding item
    }

    public AnimalHerderDepositGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public long getLastDropOffDay() { return lastDropOffDay; }
    public void setLastDropOffDay(long day) { this.lastDropOffDay = day; }

    private boolean isAnimalHerder() {
        String job = peasant.getJobType();
        return job.equals(JobSystem.JOB_CATTLE_HERDER)
                || job.equals(JobSystem.JOB_CHICKEN_BREEDER)
                || job.equals(JobSystem.JOB_PIG_BREEDER)
                || job.equals(JobSystem.JOB_SHEEP_HERDER);
    }

    private Set<String> getItemsForJob() {
        String job = peasant.getJobType();
        if (job.equals(JobSystem.JOB_CATTLE_HERDER))   return CATTLE_ITEMS;
        if (job.equals(JobSystem.JOB_CHICKEN_BREEDER)) return CHICKEN_ITEMS;
        if (job.equals(JobSystem.JOB_PIG_BREEDER))     return PIG_ITEMS;
        if (job.equals(JobSystem.JOB_SHEEP_HERDER))    return SHEEP_ITEMS;
        return new HashSet<>();
    }

    @Override
    public boolean canUse() {
        if (!isAnimalHerder()) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;

        long dayTime    = peasant.level().getDayTime() % 24000;
        long currentDay = peasant.level().getDayTime() / 24000;

        if (FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) return true;

        if (dayTime >= 12000 && dayTime <= 13000
                && lastDropOffDay != currentDay
                && !FarmerDepositTicketSystem.hasPendingDeposit(peasant.getUUID())
                && !FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {

            if (!hasHerderItems()) return false;
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
            if (!stack.isEmpty() && isHerderItem(stack)) {
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

    private boolean hasHerderItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isHerderItem(stack)) return true;
        }
        return false;
    }

    private boolean isHerderItem(ItemStack stack) {
        return getItemsForJob().contains(getItemKey(stack));
    }

    private String getItemKey(ItemStack stack) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return itemId != null ? itemId.toString() : "unknown";
    }
}