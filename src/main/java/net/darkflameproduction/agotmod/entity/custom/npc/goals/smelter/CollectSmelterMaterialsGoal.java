package net.darkflameproduction.agotmod.entity.custom.npc.goals.smelter;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.smelter.SmelterCollectionTicketSystem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.List;

public class CollectSmelterMaterialsGoal extends Goal {

    private static final int MAX_COAL_REQUEST = 15;

    private final Peasant_Entity peasant;
    private boolean ticketPosted  = false;
    private boolean itemsReceived = false;
    private int waitTicks = 0;
    private static final int MAX_WAIT_TICKS = 100;

    public CollectSmelterMaterialsGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_SMELTER)) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getSmelterSystem().getLastCollectionDay() == currentDay) return false;
        if (SmelterCollectionTicketSystem.hasPendingRequest(peasant.getUUID())) return false;
        if (SmelterCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) return true;

        // Only trigger at start of workday
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (itemsReceived) return false;
        if (waitTicks >= MAX_WAIT_TICKS) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        return true;
    }

    @Override
    public void start() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;

        // Response may already be waiting if canUse() detected it
        if (SmelterCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            ticketPosted = true;
            return;
        }

        long currentDay = peasant.level().getDayTime() / 24000;
        SmelterCollectionTicketSystem.postRequest(
                peasant.getUUID(), MAX_COAL_REQUEST, currentDay, peasant.blockPosition());
        ticketPosted = true;
    }

    @Override
    public void tick() {
        if (!ticketPosted) return;
        waitTicks++;

        if (SmelterCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            SmelterCollectionTicketSystem.SmelterResponse response =
                    SmelterCollectionTicketSystem.consumeResponse(peasant.getUUID());

            if (response != null) {
                int coalCount = 0;
                for (ItemStack stack : response.items) {
                    peasant.getInventorySystem().addItem(stack.copy());
                    // Count coal received to set capacity
                    String key = net.minecraft.core.registries.BuiltInRegistries.ITEM
                            .getKey(stack.getItem()).toString();
                    if (key.equals("minecraft:coal") || key.equals("minecraft:charcoal")) {
                        coalCount += stack.getCount();
                    }
                }
                peasant.getSmelterSystem().setCoalCollected(coalCount);
                peasant.getSmelterSystem().setOreSlotsFilled(0);
            }

            itemsReceived = true;
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getSmelterSystem().setLastCollectionDay(currentDay);
        }
    }

    @Override
    public void stop() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;
    }
}