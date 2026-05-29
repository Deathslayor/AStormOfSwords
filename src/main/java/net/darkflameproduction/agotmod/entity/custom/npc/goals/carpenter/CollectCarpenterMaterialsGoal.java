package net.darkflameproduction.agotmod.entity.custom.npc.goals.carpenter;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class CollectCarpenterMaterialsGoal extends Goal {

    private final Peasant_Entity peasant;
    private boolean ticketPosted  = false;
    private boolean itemsReceived = false;
    private int waitTicks         = 0;
    private static final int MAX_WAIT_TICKS = 100;

    public CollectCarpenterMaterialsGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_CARPENTER)) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getCarpenterSystem().getLastWorkDay() == currentDay) return false;

        // Clear stuck pending request
        if (CarpenterCollectionTicketSystem.hasPendingRequest(peasant.getUUID())
                && !CarpenterCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            CarpenterCollectionTicketSystem.consumeRequest(peasant.getUUID());
        }

        if (CarpenterCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) return true;

        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (itemsReceived) return false;
        if (waitTicks >= MAX_WAIT_TICKS) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.shouldSleep()) return false;
        return true;
    }

    @Override
    public void start() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;

        if (CarpenterCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            ticketPosted = true;
            return;
        }

        if (!peasant.isRegisteredToTownHall()) {
            peasant.findAndRegisterWithNearestTownHall();
        }

        long currentDay = peasant.level().getDayTime() / 24000;
        CarpenterCollectionTicketSystem.postRequest(
                peasant.getUUID(), currentDay, peasant.blockPosition());
        ticketPosted = true;
    }

    @Override
    public void tick() {
        if (!ticketPosted) return;
        waitTicks++;

        if (waitTicks >= MAX_WAIT_TICKS) {
            CarpenterCollectionTicketSystem.consumeRequest(peasant.getUUID());
            return;
        }

        if (CarpenterCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            CarpenterCollectionTicketSystem.CarpenterResponse response =
                    CarpenterCollectionTicketSystem.consumeResponse(peasant.getUUID());

            if (response != null) {
                for (ItemStack stack : response.items) {
                    peasant.getInventorySystem().addItem(stack.copy());
                }
            }

            itemsReceived = true;
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getCarpenterSystem().setLastWorkDay(currentDay);
            peasant.getCarpenterSystem().setCurrentState(CarpenterSystem.CarpenterState.WORKING);
        }
    }

    @Override
    public void stop() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;
    }
}
