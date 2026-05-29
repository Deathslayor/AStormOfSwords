package net.darkflameproduction.agotmod.entity.custom.npc.goals.tanner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerSystem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;

public class CollectTannerMaterialsGoal extends Goal {

    private final Peasant_Entity peasant;
    private boolean ticketPosted  = false;
    private boolean itemsReceived = false;
    private int waitTicks         = 0;
    private static final int MAX_WAIT_TICKS = 100;

    public CollectTannerMaterialsGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        if (!peasant.getJobType().equals(JobSystem.JOB_TANNER)) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getTannerSystem().getLastCollectionDay() == currentDay) return false;

        // Clear stuck pending requests
        if (TannerCollectionTicketSystem.hasPendingRequest(peasant.getUUID())
                && !TannerCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            TannerCollectionTicketSystem.consumeRequest(peasant.getUUID());
        }

        if (TannerCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) return true;

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

        if (TannerCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            ticketPosted = true;
            return;
        }

        if (!peasant.isRegisteredToTownHall()) {
            peasant.findAndRegisterWithNearestTownHall();
        }

        long currentDay = peasant.level().getDayTime() / 24000;
        TannerCollectionTicketSystem.postRequest(
                peasant.getUUID(), currentDay, peasant.blockPosition());
        ticketPosted = true;
    }

    @Override
    public void tick() {
        if (!ticketPosted) return;
        waitTicks++;

        if (waitTicks >= MAX_WAIT_TICKS) {
            TannerCollectionTicketSystem.consumeRequest(peasant.getUUID());
            return;
        }

        if (TannerCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            TannerCollectionTicketSystem.TannerResponse response =
                    TannerCollectionTicketSystem.consumeResponse(peasant.getUUID());

            if (response != null) {
                int hides   = 0;
                int leather = 0;
                int string  = 0;
                for (ItemStack stack : response.items) {
                    peasant.getInventorySystem().addItem(stack.copy());
                    String key = net.minecraft.core.registries.BuiltInRegistries.ITEM
                            .getKey(stack.getItem()).toString();
                    switch (key) {
                        case "minecraft:rabbit_hide" -> hides   += stack.getCount();
                        case "minecraft:leather"     -> leather += stack.getCount();
                        case "minecraft:string"      -> string  += stack.getCount();
                    }
                }
                peasant.getTannerSystem().setRabbitHidesCollected(hides);
                peasant.getTannerSystem().setLeatherCollected(leather);
                peasant.getTannerSystem().setStringCollected(string);
                peasant.getTannerSystem().setHidesProcessed(0);
                peasant.getTannerSystem().resetHideTicks();
            }

            itemsReceived = true;
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getTannerSystem().setLastCollectionDay(currentDay);
            peasant.getTannerSystem().setCurrentState(TannerSystem.TannerState.WORKING);
        }
    }

    @Override
    public void stop() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;
    }
}
