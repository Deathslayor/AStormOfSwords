package net.darkflameproduction.agotmod.entity.custom.npc.goals.animalherder;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder.AnimalHerderCollectionTicketSystem;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.EnumSet;

public class CollectBreedingItemGoal extends Goal {

    private static final int MAX_BREEDING_ITEM_STOCK = 64;
    private static final int MAX_WAIT_TICKS          = 100;

    private final Peasant_Entity peasant;
    private boolean ticketPosted  = false;
    private boolean itemsReceived = false;
    private int waitTicks         = 0;

    public CollectBreedingItemGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    private boolean isAnimalHerder() {
        String job = peasant.getJobType();
        return job.equals(JobSystem.JOB_CATTLE_HERDER)
                || job.equals(JobSystem.JOB_CHICKEN_BREEDER)
                || job.equals(JobSystem.JOB_PIG_BREEDER)
                || job.equals(JobSystem.JOB_SHEEP_HERDER);
    }

    private Item getBreedingItem() {
        String job = peasant.getJobType();
        if (job.equals(JobSystem.JOB_CATTLE_HERDER))   return Items.WHEAT;
        if (job.equals(JobSystem.JOB_CHICKEN_BREEDER)) return Items.WHEAT_SEEDS;
        if (job.equals(JobSystem.JOB_PIG_BREEDER))     return Items.CARROT;
        if (job.equals(JobSystem.JOB_SHEEP_HERDER))    return Items.WHEAT;
        return Items.AIR;
    }

    private int countBreedingItemsInInventory() {
        Item target = getBreedingItem();
        if (target == Items.AIR) return 0;
        var inventory = peasant.getInventorySystem().getInventory();
        int count = 0;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == target) count += stack.getCount();
        }
        return count;
    }

    @Override
    public boolean canUse() {
        if (!isAnimalHerder()) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (AnimalHerderCollectionTicketSystem.hasPendingRequest(peasant.getUUID())) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getAnimalHerderSystem().getLastCollectionDay() == currentDay) return false;

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

        int currentStock = countBreedingItemsInInventory();
        int needed       = MAX_BREEDING_ITEM_STOCK - currentStock;

        if (needed <= 0) {
            // Already fully stocked, mark collection done
            itemsReceived = true;
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getAnimalHerderSystem().setLastCollectionDay(currentDay);
            peasant.getAnimalHerderSystem().setBreedingItemCount(currentStock);
            return;
        }

        long currentDay = peasant.level().getDayTime() / 24000;
        AnimalHerderCollectionTicketSystem.postRequest(
                peasant.getUUID(),
                peasant.getJobType(),
                needed,
                currentDay,
                peasant.blockPosition());
        ticketPosted = true;
    }

    @Override
    public void tick() {
        if (!ticketPosted) return;
        waitTicks++;

        if (AnimalHerderCollectionTicketSystem.hasPendingResponse(peasant.getUUID())) {
            AnimalHerderCollectionTicketSystem.AnimalHerderResponse response =
                    AnimalHerderCollectionTicketSystem.consumeResponse(peasant.getUUID());

            if (response != null) {
                for (ItemStack stack : response.items) {
                    peasant.getInventorySystem().addItem(stack.copy());
                }
            }

            int totalCount = countBreedingItemsInInventory();
            peasant.getAnimalHerderSystem().setBreedingItemCount(totalCount);

            itemsReceived = true;
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getAnimalHerderSystem().setLastCollectionDay(currentDay);
        }
    }

    @Override
    public void stop() {
        if (!itemsReceived) {
            // Timed out — mark done anyway so we don't loop forever
            long currentDay = peasant.level().getDayTime() / 24000;
            peasant.getAnimalHerderSystem().setLastCollectionDay(currentDay);
        }
        ticketPosted  = false;
        itemsReceived = false;
        waitTicks     = 0;
    }
}