package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

public class HungerSystem {
    private final Northern_Peasant_Entity peasant;

    // Hunger system constants
    public static final int MAX_HUNGER = 20;
    private static final int HUNGER_DECAY_INTERVAL = 1200; // 60 seconds
    private static final int PASSIVE_HEAL_INTERVAL = 200; // 10 seconds
    private static final int FOOD_CHECK_INTERVAL = 60; // 3 seconds when hungry
    private static final int HEAL_COOLDOWN_TIME = 600; // 30 seconds
    private static final int MIN_FOOD_COUNT = 15; // Minimum meat items to have in inventory

    // Eating variables
    private int eatingTime = 0;
    private boolean isEating = false;
    private int healCooldown = 0;
    private int foodCheckTimer = 0;
    private int hungerDecayTimer = 0;
    private int passiveHealTimer = 0;

    // Store the item that was in main hand before eating
    private ItemStack previousMainHandItem = ItemStack.EMPTY;
    private int foodInventorySlot = -1; // Track which slot the food came from

    public HungerSystem(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (peasant.level().isClientSide) return;

        // Hunger decay over time (not during sleep)
        if (!peasant.isSleeping()) {
            hungerDecayTimer++;
            if (hungerDecayTimer >= HUNGER_DECAY_INTERVAL) {
                hungerDecayTimer = 0;
                removeHunger(1); // Lose 1 hunger point every 60 seconds
            }
        }

        // Passive healing when hunger is full
        if (isFullHunger() && peasant.getHealth() < peasant.getMaxHealth()) {
            passiveHealTimer++;
            if (passiveHealTimer >= PASSIVE_HEAL_INTERVAL) {
                passiveHealTimer = 0;
                peasant.heal(1.0F); // Heal 1 HP every 10 seconds when at full hunger
            }
        } else {
            passiveHealTimer = 0; // Reset timer if not at full hunger
        }

        // Reduce heal cooldown
        if (healCooldown > 0) {
            healCooldown--;
        }

        // Check for food and eat if hungry
        if (!peasant.isSleeping() && !isEating && needsFood()) {
            foodCheckTimer++;
            if (foodCheckTimer >= FOOD_CHECK_INTERVAL) {
                foodCheckTimer = 0;

                int foodSlot = findFoodSlotInInventory();
                if (foodSlot != -1 && canEat()) {
                    ItemStack foodStack = peasant.getInventorySystem().getInventory().getItem(foodSlot);
                    startEating(foodStack, foodSlot);
                }
            }
        }

        // Handle eating animation and timing
        if (isEating) {
            eatingTime++;

            // Play eating sound every 4 ticks while eating
            if (eatingTime % 4 == 0) {
                peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                        SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL,
                        0.5F + 0.5F * peasant.getRandom().nextInt(2),
                        (peasant.getRandom().nextFloat() - peasant.getRandom().nextFloat()) * 0.2F + 1.0F);
            }

            // Finish eating after 32 ticks (same as player)
            if (eatingTime >= 32) {
                finishEating();
            }
        }
    }

    public int getHungerLevel() {
        return peasant.getEntityData().get(peasant.getHungerLevelAccessor());
    }

    public void setHungerLevel(int hunger) {
        peasant.getEntityData().set(peasant.getHungerLevelAccessor(), Math.max(0, Math.min(MAX_HUNGER, hunger)));
    }

    public boolean isHungry() {
        return getHungerLevel() < MAX_HUNGER;
    }

    public boolean isFullHunger() {
        return getHungerLevel() >= MAX_HUNGER;
    }

    public void addHunger(int amount) {
        setHungerLevel(getHungerLevel() + amount);
    }

    public void removeHunger(int amount) {
        setHungerLevel(getHungerLevel() - amount);
    }

    public boolean needsFoodCollection() {
        return peasant.getEntityData().get(peasant.getNeedsFoodCollectionAccessor());
    }

    public void setNeedsFoodCollection(boolean needs) {
        peasant.getEntityData().set(peasant.getNeedsFoodCollectionAccessor(), needs);
        // Reset job block return flag when starting food collection
        if (needs) {
            peasant.getFarmingSystem().setHasReturnedToJobBlockAfterFood(false);
        }
    }

    public int countMeatInInventory() {
        int count = 0;
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (isFood(stack)) {
                count += stack.getCount();
            }
        }
        return count;
    }

    public boolean hasEnoughFood() {
        return countMeatInInventory() >= MIN_FOOD_COUNT;
    }

    public boolean isEating() {
        return isEating;
    }

    public ItemStack getEatingItem() {
        return isEating ? peasant.getInventorySystem().getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
    }

    public void stopEating() {
        if (isEating) {
            // Restore the previous main hand item if we have one
            restorePreviousMainHandItem();

            isEating = false;
            eatingTime = 0;
            previousMainHandItem = ItemStack.EMPTY;
            foodInventorySlot = -1;
        }
    }

    public void onHurt() {
        // Reset food check timer when damaged to check for food sooner
        if (!isEating) {
            foodCheckTimer = 0;
        }
    }

    private boolean needsFood() {
        return isHungry();
    }

    private boolean canEat() {
        return !isEating && !peasant.isSleeping();
    }

    private boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.create(
                ResourceLocation.fromNamespaceAndPath("minecraft", "meat")));
    }

    private ItemStack findFoodInInventory() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (isFood(stack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    private int findFoodSlotInInventory() {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (isFood(stack)) {
                return i;
            }
        }
        return -1;
    }

    private void startEating(ItemStack foodStack, int foodSlot) {
        if (!isEating && isFood(foodStack)) {
            isEating = true;
            eatingTime = 0;
            foodInventorySlot = foodSlot;

            // Store the current main hand item before replacing it
            ItemStack currentMainHand = peasant.getInventorySystem().getMainHandItem();
            if (!currentMainHand.isEmpty()) {
                previousMainHandItem = currentMainHand.copy();

                // Try to put the previous item back into the inventory slot where the food was
                var inventory = peasant.getInventorySystem().getInventory();
                if (foodSlot >= 0 && foodSlot < inventory.getContainerSize()) {
                    // Remove one food item from the slot
                    ItemStack foodInSlot = inventory.getItem(foodSlot);
                    if (!foodInSlot.isEmpty() && foodInSlot.getCount() > 1) {
                        // Multiple food items in this slot - just reduce count
                        foodInSlot.shrink(1);

                        // Try to find another empty slot for the previous item
                        if (!peasant.getInventorySystem().addItem(previousMainHandItem)) {
                            // No space in inventory - drop the item
                            if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
                                peasant.spawnAtLocation(serverLevel, previousMainHandItem);
                            }
                        }
                        previousMainHandItem = ItemStack.EMPTY; // Clear since we handled it
                    } else {
                        // Only one food item - we'll replace it with the previous item after eating
                        inventory.setItem(foodSlot, ItemStack.EMPTY);
                    }
                }
            } else {
                // No previous item, just remove the food from inventory
                var inventory = peasant.getInventorySystem().getInventory();
                if (foodSlot >= 0 && foodSlot < inventory.getContainerSize()) {
                    ItemStack foodInSlot = inventory.getItem(foodSlot);
                    if (!foodInSlot.isEmpty()) {
                        foodInSlot.shrink(1);
                        if (foodInSlot.isEmpty()) {
                            inventory.setItem(foodSlot, ItemStack.EMPTY);
                        }
                    }
                }
            }

            // Set the food item in main hand for eating animation
            peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, foodStack.copyWithCount(1));

            peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                    SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL,
                    0.5F + 0.5F * peasant.getRandom().nextInt(2),
                    (peasant.getRandom().nextFloat() - peasant.getRandom().nextFloat()) * 0.2F + 1.0F);
        }
    }

    private void finishEating() {
        if (isEating) {
            // Only restore hunger - NO direct healing from eating
            addHunger(4); // Add 4 hunger points (1/5 of max hunger)

            peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                    SoundEvents.PLAYER_BURP, SoundSource.NEUTRAL,
                    0.5F, peasant.getRandom().nextFloat() * 0.1F + 0.9F);

            // Restore the previous main hand item
            restorePreviousMainHandItem();

            isEating = false;
            eatingTime = 0;
            previousMainHandItem = ItemStack.EMPTY;
            foodInventorySlot = -1;
        }
    }

    private void restorePreviousMainHandItem() {
        if (!previousMainHandItem.isEmpty()) {
            var inventory = peasant.getInventorySystem().getInventory();

            // Try to put the previous item back in the slot where food was taken from
            if (foodInventorySlot >= 0 && foodInventorySlot < inventory.getContainerSize()) {
                ItemStack slotItem = inventory.getItem(foodInventorySlot);
                if (slotItem.isEmpty()) {
                    // Slot is empty, put the previous item there
                    inventory.setItem(foodInventorySlot, previousMainHandItem.copy());
                    peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    return;
                }
            }

            // If we can't put it back in the original slot, try to add it to inventory
            if (peasant.getInventorySystem().addItem(previousMainHandItem)) {
                peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
            } else {
                // No space in inventory, restore it to main hand
                peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, previousMainHandItem);
            }
        } else {
            // No previous item, just clear main hand
            peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }
    }

    public void saveData(CompoundTag compound) {
        compound.putInt("HungerLevel", getHungerLevel());
        compound.putBoolean("NeedsFoodCollection", needsFoodCollection());
        compound.putInt("HealCooldown", healCooldown);
        compound.putInt("FoodCheckTimer", foodCheckTimer);
        compound.putBoolean("IsEating", isEating);
        compound.putInt("EatingTime", eatingTime);
        compound.putInt("HungerDecayTimer", hungerDecayTimer);
        compound.putInt("PassiveHealTimer", passiveHealTimer);
        compound.putInt("FoodInventorySlot", foodInventorySlot);

        // Save previous main hand item
        if (!previousMainHandItem.isEmpty()) {
            CompoundTag previousItemTag = new CompoundTag();
            previousMainHandItem.save(peasant.registryAccess(), previousItemTag);
            compound.put("PreviousMainHandItem", previousItemTag);
        }
    }

    public void loadData(CompoundTag compound) {
        setHungerLevel(compound.getInt("HungerLevel"));
        setNeedsFoodCollection(compound.getBoolean("NeedsFoodCollection"));
        healCooldown = compound.getInt("HealCooldown");
        foodCheckTimer = compound.getInt("FoodCheckTimer");
        isEating = compound.getBoolean("IsEating");
        eatingTime = compound.getInt("EatingTime");
        hungerDecayTimer = compound.getInt("HungerDecayTimer");
        passiveHealTimer = compound.getInt("PassiveHealTimer");
        foodInventorySlot = compound.getInt("FoodInventorySlot");

        // Load previous main hand item
        if (compound.contains("PreviousMainHandItem")) {
            CompoundTag previousItemTag = compound.getCompound("PreviousMainHandItem");
            previousMainHandItem = ItemStack.parse(peasant.registryAccess(), previousItemTag).orElse(ItemStack.EMPTY);
        } else {
            previousMainHandItem = ItemStack.EMPTY;
        }
    }
}