package net.darkflameproduction.agotmod.entity.custom.npc.goals.inventory;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.inventory.FoodCollectionTicketSystem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class CollectFoodGoal extends Goal {

    private static final int MIN_FOOD_COUNT = 15;

    private final Peasant_Entity peasant;
    private boolean ticketPosted   = false;
    private boolean foodReceived   = false;
    private int     waitTicks      = 0;
    private static final int MAX_WAIT_TICKS = 100; // 5 seconds timeout

    public CollectFoodGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (!peasant.needsFoodCollection()) return false;
        if (peasant.getHungerSystem().hasEnoughFood()) {
            peasant.setNeedsFoodCollection(false);
            return false;
        }
        if (FoodCollectionTicketSystem.hasPendingRequest(peasant.getUUID())) return false;
        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (foodReceived) return false;
        if (waitTicks >= MAX_WAIT_TICKS) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        return true;
    }

    @Override
    public void start() {
        ticketPosted = false;
        foodReceived = false;
        waitTicks    = 0;

        int currentFood = peasant.getHungerSystem().countMeatInInventory();
        int needed      = MIN_FOOD_COUNT - currentFood;

        if (needed <= 0) {
            peasant.setNeedsFoodCollection(false);
            foodReceived = true;
            return;
        }

        long currentDay = peasant.level().getDayTime() / 24000;
        FoodCollectionTicketSystem.postRequest(
                peasant.getUUID(), needed, currentDay, peasant.blockPosition());
        ticketPosted = true;
    }

    @Override
    public void tick() {
        if (!ticketPosted) return;

        waitTicks++;

        if (FoodCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            FoodCollectionTicketSystem.FoodResponse response =
                    FoodCollectionTicketSystem.consumeResponse(peasant.getUUID());

            if (response != null && !response.items.isEmpty()) {
                for (ItemStack stack : response.items) {
                    peasant.getInventorySystem().addItem(stack.copy());
                }
            }

            // Whether food was available or not, move on with the day
            foodReceived = true;
            peasant.setNeedsFoodCollection(false);
        }
    }

    @Override
    public void stop() {
        ticketPosted = false;
        foodReceived = false;
        waitTicks    = 0;
    }
}
