package net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.resources.ResourceLocation;

import java.util.EnumSet;
import java.util.Set;
import java.util.HashSet;

public class BarrelDropOffGoal extends Goal {
    private final Peasant_Entity peasant;
    private BlockPos targetBarrel;
    private boolean hasDroppedOffToday = false;
    private static final int MEAT_TO_KEEP = 20;
    private static final int SEED_STACKS_TO_KEEP = 2;

    // Set of allowed items that can be dropped off
    private static final Set<String> ALLOWED_ITEMS = new HashSet<>();

    static {
        // Vanilla items
        ALLOWED_ITEMS.add("minecraft:wheat");
        ALLOWED_ITEMS.add("minecraft:wheat_seeds");
        ALLOWED_ITEMS.add("minecraft:pumpkin");
        ALLOWED_ITEMS.add("minecraft:pumpkin_seeds");
        ALLOWED_ITEMS.add("minecraft:melon_slice");
        ALLOWED_ITEMS.add("minecraft:melon_seeds");
        ALLOWED_ITEMS.add("minecraft:beetroot");
        ALLOWED_ITEMS.add("minecraft:beetroot_seeds");

        // Custom mod items
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

        // Berries
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

        // Other
        ALLOWED_ITEMS.add("agotmod:garlic");
    }

    public BarrelDropOffGoal(Peasant_Entity peasant) {
        this.peasant = peasant;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        // Must be a farmer with a job block
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER) || peasant.getJobBlockPos() == null) {
            return false;
        }

        // Must not be sleeping or eating
        if (peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // EXPANDED TIME WINDOW - try 11000-13000 instead of 12000-13000
        long dayTime = peasant.level().getDayTime() % 24000;
        boolean isDropOffTime = (dayTime >= 11000 && dayTime <= 13000); // WIDER WINDOW

        if (!isDropOffTime) {
            // Add debug to see what time it is when this fails
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Not drop-off time. Current time: " + dayTime);
            }
            return false;
        }

        // Don't do it twice in one day
        if (hasDroppedOffToday) {
            if (!peasant.level().isClientSide && peasant.tickCount % 200 == 0) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Already dropped off today");
            }
            return false;
        }

        // Reset daily flag at midnight
        if (dayTime < 1000) {
            hasDroppedOffToday = false;
        }

        // Use the farmer's own job block as the target barrel
        targetBarrel = peasant.getJobBlockPos();

        // Verify the job block is still a farmer barrel
        BlockState barrelState = peasant.level().getBlockState(targetBarrel);
        if (!barrelState.is(ModBLocks.FARMER_BARREL.get())) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Job block is not a farmer barrel: " + barrelState.getBlock());
            }
            return false;
        }

        // ADD DEBUG: Check what items the farmer has
        if (!peasant.level().isClientSide) {
            var inventory = peasant.getInventorySystem().getInventory();
            int totalItems = 0;
            int allowedItems = 0;
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                if (!stack.isEmpty()) {
                    totalItems += stack.getCount();
                    if (isAllowedItem(stack)) {
                        allowedItems += stack.getCount();
                    }
                }
            }
            System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Activating drop-off. Total items: " + totalItems + ", Allowed items: " + allowedItems);
        }

        return true;
    }

    @Override
    public boolean canContinueToUse() {
        if (targetBarrel == null || peasant.isSleeping() || peasant.getHungerSystem().isEating()) {
            return false;
        }

        // Stop if we've completed the drop-off
        if (hasDroppedOffToday) {
            return false;
        }

        // Check if barrel still exists and is still our job block
        if (!targetBarrel.equals(peasant.getJobBlockPos())) {
            return false;
        }

        BlockState barrelState = peasant.level().getBlockState(targetBarrel);
        if (!barrelState.is(ModBLocks.FARMER_BARREL.get())) {
            return false;
        }

        return true;
    }

    @Override
    public void start() {
        if (targetBarrel != null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG BARREL DROP OFF GOAL [" + peasant.getDisplayName().getString() + "]: Starting end-of-day drop off");
            }
            peasant.getNavigation().moveTo(targetBarrel.getX() + 0.5, targetBarrel.getY(), targetBarrel.getZ() + 0.5, 0.8D);
        }
    }

    @Override
    public void stop() {
        targetBarrel = null;
        peasant.getNavigation().stop();
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG BARREL DROP OFF GOAL [" + peasant.getDisplayName().getString() + "]: End-of-day drop off completed");
        }
    }

    @Override
    public void tick() {
        if (targetBarrel != null) {
            peasant.getLookControl().setLookAt(targetBarrel.getX(), targetBarrel.getY(), targetBarrel.getZ());

            // If we're close to the barrel, try to drop off items
            if (peasant.distanceToSqr(targetBarrel.getX(), targetBarrel.getY(), targetBarrel.getZ()) <= 4.0D) {
                dropOffAllAllowedItems();
                return;
            }

            // Keep moving to barrel if not there yet
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(targetBarrel.getX() + 0.5, targetBarrel.getY(), targetBarrel.getZ() + 0.5, 0.8D);
            }
        }
    }

    // Helper method to check if an item is allowed to be dropped off
    private boolean isAllowedItem(ItemStack stack) {
        ResourceLocation itemId = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        return ALLOWED_ITEMS.contains(itemId.toString());
    }

    private void dropOffAllAllowedItems() {
        var blockEntity = peasant.level().getBlockEntity(targetBarrel);
        if (blockEntity instanceof Container barrelInventory) {
            var peasantInventory = peasant.getInventorySystem().getInventory();
            boolean droppedSomething = false;
            int totalDropped = 0;

            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Starting simplified drop-off");
            }

            // SIMPLIFIED: Just drop ALL allowed items except keep 10 meat and 1 stack of seeds
            int meatKept = 0;
            int seedStacksKept = 0;

            for (int i = 0; i < peasantInventory.getContainerSize(); i++) {
                ItemStack stack = peasantInventory.getItem(i);
                if (!stack.isEmpty() && isAllowedItem(stack)) {

                    boolean shouldKeep = false;

                    // Keep some meat
                    if (stack.is(ItemTags.create(ResourceLocation.fromNamespaceAndPath("minecraft", "meat")))) {
                        if (meatKept < 10) {
                            int keepAmount = Math.min(10 - meatKept, stack.getCount());
                            meatKept += keepAmount;
                            if (stack.getCount() > keepAmount) {
                                // Split stack
                                ItemStack excess = stack.copy();
                                excess.setCount(stack.getCount() - keepAmount);
                                stack.setCount(keepAmount);

                                if (addToBarrel(barrelInventory, excess)) {
                                    droppedSomething = true;
                                    totalDropped += excess.getCount();
                                }
                            }
                            shouldKeep = true;
                        }
                    }
                    // Keep one stack of seeds
                    else if (isSeed(stack)) {
                        if (seedStacksKept < 1) {
                            seedStacksKept++;
                            shouldKeep = true;
                        }
                    }

                    // Drop everything else that's allowed
                    if (!shouldKeep) {
                        if (addToBarrel(barrelInventory, stack.copy())) {
                            totalDropped += stack.getCount();
                            peasantInventory.setItem(i, ItemStack.EMPTY);
                            droppedSomething = true;
                        }
                    }
                }
            }

            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Dropped " + totalDropped + " items total. Success: " + droppedSomething);
            }

            // Mark as completed for today
            hasDroppedOffToday = true;
        } else {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG BARREL DROP OFF [" + peasant.getDisplayName().getString() + "]: Barrel block entity is not a container! Type: " + (blockEntity != null ? blockEntity.getClass().getSimpleName() : "null"));
            }
        }
    }

    private boolean addToBarrel(Container barrelInventory, ItemStack stackToAdd) {
        // Try to merge with existing stacks first
        for (int i = 0; i < barrelInventory.getContainerSize(); i++) {
            ItemStack existing = barrelInventory.getItem(i);
            if (!existing.isEmpty() && ItemStack.isSameItemSameComponents(existing, stackToAdd)) {
                int maxStackSize = Math.min(existing.getMaxStackSize(), barrelInventory.getMaxStackSize());
                int canAdd = maxStackSize - existing.getCount();
                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, stackToAdd.getCount());
                    existing.grow(toAdd);
                    stackToAdd.shrink(toAdd);
                    if (stackToAdd.isEmpty()) return true;
                }
            }
        }

        // Try to place in empty slots
        for (int i = 0; i < barrelInventory.getContainerSize(); i++) {
            if (barrelInventory.getItem(i).isEmpty()) {
                barrelInventory.setItem(i, stackToAdd.copy());
                return true;
            }
        }

        return false; // Barrel is full
    }

    private boolean isSeed(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock;
    }
}