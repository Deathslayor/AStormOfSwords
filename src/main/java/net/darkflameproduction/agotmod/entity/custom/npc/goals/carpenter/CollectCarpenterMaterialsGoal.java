package net.darkflameproduction.agotmod.entity.custom.npc.goals.carpenter;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem;
import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.guard.GuardLoot;
import net.darkflameproduction.agotmod.entity.custom.npc.system.supply.SupplyChain;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;

import java.util.EnumSet;
import java.util.Map;

public class CollectCarpenterMaterialsGoal extends Goal {

    private final Peasant_Entity peasant;
    private boolean ticketPosted  = false;
    private boolean itemsReceived = false;
    private int waitTicks         = 0;
    private static final int MAX_WAIT_TICKS = 100;
    /** The least time between two pickups in a day. */
    private static final int RESTOCK_TICKS = 20 * 120;
    private long lastCollectTime = -RESTOCK_TICKS;

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
        // No limit on his work: after the morning's pickup he comes back whenever he runs out
        if (peasant.getCarpenterSystem().getLastWorkDay() == currentDay && !needsRestock()) return false;

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
            lastCollectTime = peasant.level().getGameTime();
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getCarpenterSystem().setLastWorkDay(currentDay);
            peasant.getCarpenterSystem().setCurrentState(CarpenterSystem.CarpenterState.WORKING);
        }
    }

    /**
     * A restock during the day: at most every {@link #RESTOCK_TICKS}, when he has no logs and too few planks
     * to make anything, and the town hall has logs.
     */
    private boolean needsRestock() {
        if (peasant.level().getGameTime() - lastCollectTime < RESTOCK_TICKS) return false;
        if (SupplyChain.count(peasant, SupplyChain.ANY_LOG) > 0) return false;
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getCount() >= 2 && BuiltInRegistries.ITEM.getKey(stack.getItem()).getPath().endsWith("_planks")) return false;
        }
        TownHallBlockEntity townHall = GuardLoot.townHallOf(peasant);
        if (townHall == null) return false;
        for (Map.Entry<String, Long> entry : townHall.getTownInventory().entrySet()) {
            if (entry.getValue() > 0 && SupplyChain.isLog(entry.getKey())) return true;
        }
        return false;
    }

    @Override
    public void stop() {
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;
    }
}
