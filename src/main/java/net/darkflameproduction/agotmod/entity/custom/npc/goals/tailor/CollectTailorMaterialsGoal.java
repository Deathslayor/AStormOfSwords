package net.darkflameproduction.agotmod.entity.custom.npc.goals.tailor;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorSystem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class CollectTailorMaterialsGoal extends Goal {

    private final Peasant_Entity peasant;
    private boolean ticketPosted  = false;
    private boolean itemsReceived = false;
    private int waitTicks         = 0;
    private static final int MAX_WAIT_TICKS = 100;

    public CollectTailorMaterialsGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TAILOR)) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getTailorSystem().getLastCollectionDay() == currentDay) return false;

        // If there's a stuck pending request from a previous attempt, clear it
        if (TailorCollectionTicketSystem.hasPendingRequest(peasant.getUUID())
                && !TailorCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            TailorCollectionTicketSystem.consumeRequest(peasant.getUUID());
        }

        if (TailorCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) return true;

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

        if (TailorCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            ticketPosted = true;
            return;
        }

        // Make sure peasant is registered with a town hall before posting
        if (!peasant.isRegisteredToTownHall()) {
            peasant.findAndRegisterWithNearestTownHall();
        }

        long currentDay = peasant.level().getDayTime() / 24000;
        TailorCollectionTicketSystem.postRequest(
                peasant.getUUID(), currentDay, peasant.blockPosition());
        ticketPosted = true;
    }

    @Override
    public void tick() {
        if (!ticketPosted) return;
        waitTicks++;

        // If we've waited too long with no response, clear the stuck request
        if (waitTicks >= MAX_WAIT_TICKS) {
            TailorCollectionTicketSystem.consumeRequest(peasant.getUUID());
            return;
        }

        if (TailorCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            TailorCollectionTicketSystem.TailorResponse response =
                    TailorCollectionTicketSystem.consumeResponse(peasant.getUUID());

            if (response != null) {
                int flowers = 0;
                for (ItemStack stack : response.items) {
                    peasant.getInventorySystem().addItem(stack.copy());
                    flowers += stack.getCount();
                }
                peasant.getTailorSystem().setFlowersCollected(flowers);
            }

            itemsReceived = true;
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getTailorSystem().setLastCollectionDay(currentDay);
            peasant.getTailorSystem().setCurrentState(TailorSystem.TailorState.WORKING);
        }
    }

    @Override
    public void stop() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;
    }
}