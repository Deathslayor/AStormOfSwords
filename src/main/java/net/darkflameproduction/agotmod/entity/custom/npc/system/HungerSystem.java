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

                ItemStack foodStack = findFoodInInventory();
                if (!foodStack.isEmpty() && canEat()) {
                    startEating(foodStack);
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
            isEating = false;
            eatingTime = 0;
            peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
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

    private void startEating(ItemStack foodStack) {
        if (!isEating && isFood(foodStack)) {
            isEating = true;
            eatingTime = 0;
            peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, foodStack.copy());
            peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                    SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL,
                    0.5F + 0.5F * peasant.getRandom().nextInt(2),
                    (peasant.getRandom().nextFloat() - peasant.getRandom().nextFloat()) * 0.2F + 1.0F);
        }
    }

    private void finishEating() {
        if (isEating) {
            ItemStack foodStack = peasant.getInventorySystem().getItemInHand(InteractionHand.MAIN_HAND);
            if (isFood(foodStack)) {
                // Only restore hunger - NO direct healing from eating
                addHunger(4); // Add 4 hunger points (1/5 of max hunger)

                peasant.level().playSound(null, peasant.getX(), peasant.getY(), peasant.getZ(),
                        SoundEvents.PLAYER_BURP, SoundSource.NEUTRAL,
                        0.5F, peasant.getRandom().nextFloat() * 0.1F + 0.9F);

                // Remove food from inventory
                var inventory = peasant.getInventorySystem().getInventory();
                for (int i = 0; i < inventory.getContainerSize(); i++) {
                    ItemStack stack = inventory.getItem(i);
                    if (stack.getItem() == foodStack.getItem()) {
                        stack.shrink(1);
                        if (stack.isEmpty()) {
                            inventory.setItem(i, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }

            isEating = false;
            eatingTime = 0;
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
    }
}