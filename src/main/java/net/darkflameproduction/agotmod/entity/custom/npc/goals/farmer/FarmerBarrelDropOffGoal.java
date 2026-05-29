package net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmerDepositTicketSystem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FarmerBarrelDropOffGoal extends Goal {
    private final Peasant_Entity peasant;
    private long lastDropOffDay = -1;

    private static final Set<String> ALLOWED_ITEMS = new HashSet<>();

    static {
        ALLOWED_ITEMS.add("minecraft:wheat");
        ALLOWED_ITEMS.add("minecraft:wheat_seeds");
        ALLOWED_ITEMS.add("minecraft:pumpkin");
        ALLOWED_ITEMS.add("minecraft:pumpkin_seeds");
        ALLOWED_ITEMS.add("minecraft:melon_slice");
        ALLOWED_ITEMS.add("minecraft:melon_seeds");
        ALLOWED_ITEMS.add("minecraft:beetroot");
        ALLOWED_ITEMS.add("minecraft:beetroot_seeds");
        ALLOWED_ITEMS.add("minecraft:carrot");
        ALLOWED_ITEMS.add("minecraft:potato");
        ALLOWED_ITEMS.add("minecraft:poisonous_potato");
        ALLOWED_ITEMS.add("agotmod:horseradish");
        ALLOWED_ITEMS.add("agotmod:horseradish_seeds");
        ALLOWED_ITEMS.add("agotmod:onion");
        ALLOWED_ITEMS.add("agotmod:onion_seeds");
        ALLOWED_ITEMS.add("agotmod:red_onion");
        ALLOWED_ITEMS.add("agotmod:red_onion_seeds");
        ALLOWED_ITEMS.add("agotmod:wild_onion");
        ALLOWED_ITEMS.add("agotmod:wild_onion_seeds");
        ALLOWED_ITEMS.add("agotmod:leek");
        ALLOWED_ITEMS.add("agotmod:leek_seeds");
        ALLOWED_ITEMS.add("agotmod:neep");
        ALLOWED_ITEMS.add("agotmod:neep_seeds");
        ALLOWED_ITEMS.add("agotmod:turnip");
        ALLOWED_ITEMS.add("agotmod:turnip_seeds");
        ALLOWED_ITEMS.add("agotmod:parsley");
        ALLOWED_ITEMS.add("agotmod:parsley_seeds");
        ALLOWED_ITEMS.add("agotmod:bean");
        ALLOWED_ITEMS.add("agotmod:bean_seeds");
        ALLOWED_ITEMS.add("agotmod:green_bean");
        ALLOWED_ITEMS.add("agotmod:green_bean_seeds");
        ALLOWED_ITEMS.add("agotmod:chickpea");
        ALLOWED_ITEMS.add("agotmod:chickpea_seeds");
        ALLOWED_ITEMS.add("agotmod:cabbage");
        ALLOWED_ITEMS.add("agotmod:cabbage_seeds");
        ALLOWED_ITEMS.add("agotmod:spinach");
        ALLOWED_ITEMS.add("agotmod:spinach_seeds");
        ALLOWED_ITEMS.add("agotmod:cucumber");
        ALLOWED_ITEMS.add("agotmod:cucumber_seeds");
        ALLOWED_ITEMS.add("agotmod:dragon_pepper");
        ALLOWED_ITEMS.add("agotmod:dragon_pepper_seeds");
        ALLOWED_ITEMS.add("agotmod:pepper");
        ALLOWED_ITEMS.add("agotmod:pepper_seeds");
        ALLOWED_ITEMS.add("agotmod:peppercorn");
        ALLOWED_ITEMS.add("agotmod:peppercorn_seeds");
        ALLOWED_ITEMS.add("agotmod:barley");
        ALLOWED_ITEMS.add("agotmod:barley_seeds");
        ALLOWED_ITEMS.add("agotmod:oat");
        ALLOWED_ITEMS.add("agotmod:oat_seeds");
        ALLOWED_ITEMS.add("agotmod:opium_poppy_seeds");
        ALLOWED_ITEMS.add("agotmod:cotton");
        ALLOWED_ITEMS.add("agotmod:cotton_seeds");
        ALLOWED_ITEMS.add("agotmod:hemp");
        ALLOWED_ITEMS.add("agotmod:hemp_seeds");
        ALLOWED_ITEMS.add("agotmod:strawberry");
        ALLOWED_ITEMS.add("agotmod:strawberry_seeds");
        ALLOWED_ITEMS.add("agotmod:blackberry");
        ALLOWED_ITEMS.add("agotmod:blackberry_seeds");
        ALLOWED_ITEMS.add("agotmod:blueberry");
        ALLOWED_ITEMS.add("agotmod:blueberry_seeds");
        ALLOWED_ITEMS.add("agotmod:mulberry");
        ALLOWED_ITEMS.add("agotmod:mulberry_seeds");
        ALLOWED_ITEMS.add("agotmod:raspberry");
        ALLOWED_ITEMS.add("agotmod:raspberry_seeds");
        ALLOWED_ITEMS.add("agotmod:smokeberry");
        ALLOWED_ITEMS.add("agotmod:smokeberry_seeds");
        ALLOWED_ITEMS.add("agotmod:garlic");
    }

    public FarmerBarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public long getLastDropOffDay() { return lastDropOffDay; }
    public void setLastDropOffDay(long day) { this.lastDropOffDay = day; }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER)) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;

        long dayTime = peasant.level().getDayTime() % 24000;
        long currentDay = peasant.level().getDayTime() / 24000;

        // If confirmation is waiting, consume it immediately
        if (FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {
            return true;
        }

        // Only post a new ticket if we haven't deposited today, have no pending deposit,
        // no pending confirmation, are in the time window, and have items
        if (dayTime >= 12000 && dayTime <= 13000
                && lastDropOffDay != currentDay
                && !FarmerDepositTicketSystem.hasPendingDeposit(peasant.getUUID())
                && !FarmerDepositTicketSystem.hasPendingConfirmation(peasant.getUUID())) {

            BlockState barrelState = peasant.level().getBlockState(peasant.getJobBlockPos());
            if (!barrelState.is(ModBLocks.FARMER_BARREL.get())) return false;
            if (!hasAllowedItems()) return false;

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
            if (!stack.isEmpty() && isAllowedItem(stack)) {
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

    private boolean hasAllowedItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isAllowedItem(stack)) return true;
        }
        return false;
    }

    private boolean isAllowedItem(ItemStack stack) {
        return ALLOWED_ITEMS.contains(getItemKey(stack));
    }

    private String getItemKey(ItemStack stack) {
        ResourceLocation itemId = BuiltInRegistries.ITEM.getKey(stack.getItem());
        return itemId != null ? itemId.toString() : "unknown";
    }
}
