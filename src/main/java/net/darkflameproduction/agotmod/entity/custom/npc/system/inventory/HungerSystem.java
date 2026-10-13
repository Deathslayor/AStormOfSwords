package net.darkflameproduction.agotmod.entity.custom.npc.system.inventory;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.darkflameproduction.agotmod.economy.FoodCategory;
import net.darkflameproduction.agotmod.economy.VillageEconomy;

public class HungerSystem {
    private final Peasant_Entity peasant;

    // Hunger system constants
    public static final int MAX_HUNGER = 20;
    private static final int HUNGER_DECAY_INTERVAL = 1200; // 60 seconds
    private static final int PASSIVE_HEAL_INTERVAL = 200; // 10 seconds
    private static final int FOOD_CHECK_INTERVAL = 60; // 3 seconds when hungry
    private static final int MIN_FOOD_COUNT = 15; // Minimum food items to have in inventory

    // Eating variables
    private int eatingTime = 0;
    private boolean isEating = false;
    private int healCooldown = 0;
    private int foodCheckTimer = 0;
    private int hungerDecayTimer = 0;
    private int passiveHealTimer = 0;

    // Store the item that was in main hand before eating
    private ItemStack previousMainHandItem = ItemStack.EMPTY;

    // Variety: the category of the meal being eaten, and the last few eaten (most recent first) - the next
    // meal is of a category not among them if he carries one
    private FoodCategory eatingCategory = null;
    private final java.util.Deque<FoodCategory> recentMeals = new java.util.ArrayDeque<>();
    private static final int RECENT_MEALS = 3;
    private int starvationTimer = 0;
    private int foodInventorySlot = -1; // Track which slot the food came from

    public HungerSystem(Peasant_Entity peasant) {
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

        // Starvation: with no hunger left he slowly loses health (never below STARVATION_MIN_HEALTH) - apart
        // from the town's unhappiness about food (VillageEconomy)
        if (getHungerLevel() <= 0) {
            starvationTimer++;
            if (starvationTimer >= VillageEconomy.STARVATION_INTERVAL) {
                starvationTimer = 0;
                float damage = Math.min(VillageEconomy.STARVATION_DAMAGE,
                        peasant.getHealth() - VillageEconomy.STARVATION_MIN_HEALTH);
                if (damage > 0) peasant.hurt(peasant.damageSources().starve(), damage);
            }
        } else {
            starvationTimer = 0;
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

    public int countFoodInInventory() {
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
        return countFoodInInventory() >= MIN_FOOD_COUNT;
    }

    public boolean isEating() {
        return isEating;
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
        return isNpcFood(stack);
    }

    /** The food NPCs eat (and count towards their food supply): any with a FoodCategory. */
    public static boolean isNpcFood(ItemStack stack) {
        return FoodCategory.of(stack) != null;
    }

    /** How much NPC food an NPC wants to carry. */
    public static int getMinFoodCount() {
        return MIN_FOOD_COUNT;
    }

    /** The food to eat next: one of a category he hasn't eaten lately if he carries one, else any. */
    private int findFoodSlotInInventory() {
        var inventory = peasant.getInventorySystem().getInventory();
        int any = -1;
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            FoodCategory category = FoodCategory.of(inventory.getItem(i));
            if (category == null) continue;
            if (!recentMeals.contains(category)) return i;
            if (any < 0) any = i;
        }
        return any;
    }

    private void startEating(ItemStack foodStack, int foodSlot) {
        if (!isEating && isFood(foodStack)) {
            isEating = true;
            eatingTime = 0;
            foodInventorySlot = foodSlot;
            eatingCategory = FoodCategory.of(foodStack);

            // Hold on to what was in the hand (weapon, tool) - it goes straight back into the hand after
            // eating. It isn't moved into the inventory, where it could be swapped for another item,
            // deposited or dropped.
            previousMainHandItem = peasant.getInventorySystem().getMainHandItem().copy();

            // Take one food item from its slot
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

            // A serving eaten: remembered for variety, and counted by his town (its food satisfaction)
            if (eatingCategory != null) {
                recentMeals.remove(eatingCategory);
                recentMeals.addFirst(eatingCategory);
                while (recentMeals.size() > RECENT_MEALS) recentMeals.removeLast();
                var townHall = net.darkflameproduction.agotmod.entity.custom.npc.system.reputation.TownReputation.townHallOf(peasant);
                if (townHall != null) townHall.recordMeal(eatingCategory);
                eatingCategory = null;
            }

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

    /** Puts whatever was held before eating back into the hand. */
    private void restorePreviousMainHandItem() {
        // Normally the hand holds the food being eaten (used up). If something else ended up in the hand
        // meanwhile, keep it rather than overwrite it.
        ItemStack inHand = peasant.getInventorySystem().getMainHandItem();
        if (!inHand.isEmpty() && !isFood(inHand)) {
            if (!peasant.getInventorySystem().addItem(inHand.copy())) {
                peasant.spawnAtLocation(inHand.copy());
            }
        }
        peasant.getInventorySystem().setItemInHand(InteractionHand.MAIN_HAND, previousMainHandItem.copy());
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
