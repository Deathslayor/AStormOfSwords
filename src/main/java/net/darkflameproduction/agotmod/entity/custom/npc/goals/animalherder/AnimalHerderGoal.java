package net.darkflameproduction.agotmod.entity.custom.npc.goals.animalherder;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.EnumSet;
import java.util.List;

public class AnimalHerderGoal extends Goal {

    private static final int PEN_RADIUS       = 5;
    private static final int PEN_HEIGHT       = 5;
    private static final int MAX_ADULTS       = 10;
    private static final int WORK_INTERVAL    = 40;
    private static final double INTERACT_RANGE_SQ = 4.0D; // 2 block distance squared — gives a little leeway

    private final Peasant_Entity peasant;
    private int workTimer = 0;

    private AgeableMob targetToKill  = null;
    private AgeableMob targetToShear = null;

    public AnimalHerderGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    private boolean isAnimalHerder() {
        String job = peasant.getJobType();
        return job.equals(JobSystem.JOB_CATTLE_HERDER)
                || job.equals(JobSystem.JOB_CHICKEN_BREEDER)
                || job.equals(JobSystem.JOB_PIG_BREEDER)
                || job.equals(JobSystem.JOB_SHEEP_HERDER);
    }

    private boolean isJobBlockValid() {
        BlockPos pos = peasant.getJobBlockPos();
        if (pos == null) return false;
        BlockState state = peasant.level().getBlockState(pos);
        String job = peasant.getJobType();
        if (job.equals(JobSystem.JOB_CATTLE_HERDER))   return state.is(ModBLocks.CATTLE_HERDER_BARREL.get());
        if (job.equals(JobSystem.JOB_CHICKEN_BREEDER)) return state.is(ModBLocks.CHICKEN_BREEDER_BARREL.get());
        if (job.equals(JobSystem.JOB_PIG_BREEDER))     return state.is(ModBLocks.PIG_BREEDER_BARREL.get());
        if (job.equals(JobSystem.JOB_SHEEP_HERDER))    return state.is(ModBLocks.SHEEP_HERDER_BARREL.get());
        return false;
    }

    @Override
    public boolean canUse() {
        if (!isAnimalHerder()) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (!isJobBlockValid()) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;

        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getAnimalHerderSystem().getLastCollectionDay() != currentDay) return false;

        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public boolean canContinueToUse() {
        if (!isAnimalHerder()) return false;
        if (peasant.getJobBlockPos() == null) return false;
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) return false;
        if (peasant.needsFoodCollection()) return false;
        if (peasant.shouldSleep()) return false;
        long dayTime = peasant.level().getDayTime() % 24000;
        return dayTime < 12000;
    }

    @Override
    public void start() {
        workTimer    = 0;
        targetToKill  = null;
        targetToShear = null;
        long currentDay = peasant.level().getDayTime() / 24000;
        if (peasant.getAnimalHerderSystem().getLastBreedDay() != currentDay) {
            peasant.getAnimalHerderSystem().setHasBreedToday(false);
        }
    }

    @Override
    public void stop() {
        peasant.getNavigation().stop();
        workTimer     = 0;
        targetToKill  = null;
        targetToShear = null;
    }

    @Override
    public void tick() {
        if (peasant.level().isClientSide) return;
        workTimer++;

        BlockPos jobBlock = peasant.getJobBlockPos();
        if (jobBlock == null) return;

        if (workTimer % 20 == 0) {
            pickUpNearbyItems(jobBlock);
        }

        // Navigate to and kill target
        if (targetToKill != null) {
            if (!targetToKill.isAlive()) {
                targetToKill = null;
            } else {
                peasant.getNavigation().moveTo(targetToKill, 0.7D);
                if (peasant.distanceToSqr(targetToKill) <= INTERACT_RANGE_SQ) {
                    if (peasant.level() instanceof ServerLevel serverLevel) {
                        targetToKill.kill(serverLevel);
                    }
                    peasant.triggerInteractAnimation();
                    targetToKill = null;
                }
                return;
            }
        }

        // Navigate to and shear target
        if (targetToShear != null) {
            if (!targetToShear.isAlive()) {
                targetToShear = null;
            } else {
                peasant.getNavigation().moveTo(targetToShear, 0.7D);
                if (peasant.distanceToSqr(targetToShear) <= INTERACT_RANGE_SQ) {
                    if (targetToShear instanceof Sheep sheep && sheep.readyForShearing()
                            && peasant.level() instanceof ServerLevel serverLevel) {
                        sheep.shear(serverLevel, net.minecraft.sounds.SoundSource.NEUTRAL, ItemStack.EMPTY);
                        peasant.triggerInteractAnimation();
                    }
                    targetToShear = null;
                }
                return;
            }
        }

        // Return near job block when idle
        double dist = peasant.distanceToSqr(jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5);
        if (dist > 16.0D && !peasant.getNavigation().isInProgress()) {
            peasant.getNavigation().moveTo(jobBlock.getX() + 0.5, jobBlock.getY(), jobBlock.getZ() + 0.5, 0.6D);
        }

        if (workTimer % WORK_INTERVAL != 0) return;

        String job      = peasant.getJobType();
        long currentDay = peasant.level().getDayTime() / 24000;

        List<? extends AgeableMob> adults = getAnimalsInPen(jobBlock, job).stream()
                .filter(a -> !a.isBaby())
                .toList();

        // Pick a target to cull if overpopulated
        if (adults.size() > MAX_ADULTS) {
            targetToKill = adults.get(0);
            return;
        }

        // Breed once per day
        if (peasant.getAnimalHerderSystem().getLastBreedDay() != currentDay) {
            peasant.getAnimalHerderSystem().setHasBreedToday(false);
        }

        if (!peasant.getAnimalHerderSystem().hasBreedToday()
                && peasant.getAnimalHerderSystem().getBreedingItemCount() > 0) {
            for (AgeableMob animal : adults) {
                if (tryBreed(animal)) {
                    peasant.getAnimalHerderSystem().decrementBreedingItemCount();
                    consumeBreedingItemFromInventory(job);
                    peasant.triggerInteractAnimation();
                }
            }
            peasant.getAnimalHerderSystem().setHasBreedToday(true);
            peasant.getAnimalHerderSystem().setLastBreedDay(currentDay);
        }

        // Pick a sheep to shear
        if (job.equals(JobSystem.JOB_SHEEP_HERDER)) {
            for (AgeableMob animal : adults) {
                if (animal instanceof Sheep sheep && sheep.readyForShearing()) {
                    targetToShear = sheep;
                    return;
                }
            }
        }
    }

    private boolean tryBreed(AgeableMob animal) {
        if (!(animal instanceof Animal breedable)) return false;
        if (breedable.isInLove()) return false;
        if (breedable.isBaby()) return false;
        if (peasant.getAnimalHerderSystem().getBreedingItemCount() <= 0) return false;
        breedable.setInLove(null);
        return true;
    }

    private void consumeBreedingItemFromInventory(String job) {
        net.minecraft.world.item.Item target = getBreedingItem(job).getItem();
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == target) {
                stack.shrink(1);
                if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
                return;
            }
        }
    }

    private ItemStack getBreedingItem(String job) {
        if (job.equals(JobSystem.JOB_CATTLE_HERDER))   return new ItemStack(Items.WHEAT);
        if (job.equals(JobSystem.JOB_CHICKEN_BREEDER)) return new ItemStack(Items.WHEAT_SEEDS);
        if (job.equals(JobSystem.JOB_PIG_BREEDER))     return new ItemStack(Items.CARROT);
        if (job.equals(JobSystem.JOB_SHEEP_HERDER))    return new ItemStack(Items.WHEAT);
        return ItemStack.EMPTY;
    }

    private void pickUpNearbyItems(BlockPos jobBlock) {
        AABB pen = getPenAABB(jobBlock);
        peasant.level().getEntitiesOfClass(ItemEntity.class, pen).forEach(itemEntity -> {
            ItemStack stack = itemEntity.getItem();
            if (isHerderLoot(stack, peasant.getJobType())) {
                peasant.getInventorySystem().addItem(stack.copy());
                itemEntity.discard();
            }
        });
    }

    private boolean isHerderLoot(ItemStack stack, String job) {
        net.minecraft.world.item.Item item = stack.getItem();
        if (job.equals(JobSystem.JOB_CATTLE_HERDER))   return item == Items.LEATHER || item == Items.BEEF;
        if (job.equals(JobSystem.JOB_CHICKEN_BREEDER)) return item == Items.FEATHER || item == Items.CHICKEN || item == Items.EGG;
        if (job.equals(JobSystem.JOB_PIG_BREEDER))     return item == Items.PORKCHOP;
        if (job.equals(JobSystem.JOB_SHEEP_HERDER)) {
            return item == Items.MUTTON || item == Items.WHITE_WOOL
                    || item == Items.ORANGE_WOOL || item == Items.MAGENTA_WOOL
                    || item == Items.LIGHT_BLUE_WOOL || item == Items.YELLOW_WOOL
                    || item == Items.LIME_WOOL || item == Items.PINK_WOOL
                    || item == Items.GRAY_WOOL || item == Items.LIGHT_GRAY_WOOL
                    || item == Items.CYAN_WOOL || item == Items.PURPLE_WOOL
                    || item == Items.BLUE_WOOL || item == Items.BROWN_WOOL
                    || item == Items.GREEN_WOOL || item == Items.RED_WOOL
                    || item == Items.BLACK_WOOL;
        }
        return false;
    }

    private List<? extends AgeableMob> getAnimalsInPen(BlockPos jobBlock, String job) {
        AABB pen = getPenAABB(jobBlock);
        if (job.equals(JobSystem.JOB_CATTLE_HERDER))   return peasant.level().getEntitiesOfClass(Cow.class, pen);
        if (job.equals(JobSystem.JOB_CHICKEN_BREEDER)) return peasant.level().getEntitiesOfClass(Chicken.class, pen);
        if (job.equals(JobSystem.JOB_PIG_BREEDER))     return peasant.level().getEntitiesOfClass(Pig.class, pen);
        if (job.equals(JobSystem.JOB_SHEEP_HERDER))    return peasant.level().getEntitiesOfClass(Sheep.class, pen);
        return List.of();
    }

    private AABB getPenAABB(BlockPos jobBlock) {
        return new AABB(
                jobBlock.getX() - PEN_RADIUS, jobBlock.getY() - PEN_HEIGHT, jobBlock.getZ() - PEN_RADIUS,
                jobBlock.getX() + PEN_RADIUS + 1, jobBlock.getY() + PEN_HEIGHT + 1, jobBlock.getZ() + PEN_RADIUS + 1
        );
    }
}