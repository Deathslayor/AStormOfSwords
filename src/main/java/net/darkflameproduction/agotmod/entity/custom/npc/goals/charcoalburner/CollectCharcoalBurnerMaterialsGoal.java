package net.darkflameproduction.agotmod.entity.custom.npc.goals.charcoalburner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner.CharcoalBurnerCollectionTicketSystem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class CollectCharcoalBurnerMaterialsGoal extends Goal {

    // Up to 128 logs per day
    private static final int MAX_LOG_REQUEST = 128;
    private static final int MAX_WAIT_TICKS  = 100;

    private final Peasant_Entity peasant;
    private boolean ticketPosted  = false;
    private boolean itemsReceived = false;
    private int waitTicks = 0;

    public CollectCharcoalBurnerMaterialsGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_CHARCOAL_BURNER)) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getCharcoalBurnerSystem().getLastCollectionDay() == currentDay) return false;
        if (CharcoalBurnerCollectionTicketSystem.hasPendingRequest(peasant.getUUID())) return false;
        if (CharcoalBurnerCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) return true;

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

        if (CharcoalBurnerCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            ticketPosted = true;
            return;
        }

        long currentDay = peasant.level().getDayTime() / 24000;
        CharcoalBurnerCollectionTicketSystem.postRequest(
                peasant.getUUID(), MAX_LOG_REQUEST, currentDay, peasant.blockPosition());
        ticketPosted = true;
    }

    @Override
    public void tick() {
        if (!ticketPosted) return;
        waitTicks++;

        if (CharcoalBurnerCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            CharcoalBurnerCollectionTicketSystem.CharcoalBurnerResponse response =
                    CharcoalBurnerCollectionTicketSystem.consumeResponse(peasant.getUUID());

            if (response != null) {
                int logCount = 0;
                for (ItemStack stack : response.items) {
                    peasant.getInventorySystem().addItem(stack.copy());
                    logCount += stack.getCount();
                }
                peasant.getCharcoalBurnerSystem().setLogsCollected(logCount);
                peasant.getCharcoalBurnerSystem().setLogsBurned(0);
            }

            itemsReceived = true;
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getCharcoalBurnerSystem().setLastCollectionDay(currentDay);
        }
    }

    @Override
    public void stop() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;
    }
}