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
    /** How much food an NPC keeps on him: each morning he tops up to this from his town hall. */
    public static final int FOOD_CARRY_TARGET = 15;
    private static final int SERVING_HUNGER = 4;
    private static final int RATION_RETRY_TICKS = 20 * 60 * 2; // the town was short: ask again 2 minutes later
    /** A day's servings (VillageEconomy) - what everyone gets first when food is short. */
    private static final int DAYS_SERVINGS = VillageEconomy.SERVINGS_PER_VILLAGER;
    /** When NPCs get up (SleepSystem's end of night): a new morning, and a new top-up, starts here. */
    private static final long MORNING_TIME = 23460;

    // The morning top-up: due after waking (and for a new NPC), the morning it was last done, and the
    // earliest game time to ask again after a short one
    private boolean rationDue = true;
    private long lastRationMorning = Long.MIN_VALUE;
    private long nextRationAttempt = 0;

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
    /** Servings eaten since they were last reported to the town (for its food happiness) - reported each morning. */
    private final java.util.Map<FoodCategory, Integer> mealsSinceReport = new java.util.EnumMap<>(FoodCategory.class);
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

        // Passive healing while well fed (NPCs eat once a whole serving fits, so they are rarely at exactly full)
        if (getHungerLevel() > MAX_HUNGER - SERVING_HUNGER && peasant.getHealth() < peasant.getMaxHealth()) {
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

        // Morning routine: take the day's food from the town
        tickDailyRation();

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

    // ===== DAILY RATION =====

    /**
     * Every morning (and whenever he wakes, for guards on night shifts) the NPC tops his food up to
     * FOOD_CARRY_TARGET from his town hall, so after the first day only what he ate is replaced. The town picks
     * the items so the NPC carries a mix of food categories, and hands each category out evenly
     * (TownHallBlockEntity.handOutFoodRation).
     *
     * When food is short it is shared out: everyone first gets a day's servings, and only food beyond what the
     * rest of the town needs for the day tops anyone up further - the first NPCs up used to take 15 each and
     * leave nothing for the rest. An NPC that got less than a day's food asks again a little later.
     */
    private void tickDailyRation() {
        if ((peasant.tickCount + peasant.getId()) % 20 != 0) return;
        if (peasant.isSleeping() || peasant.shouldSleep() || isEating) return;
        if (!rationDue && morningOf(peasant.level().getDayTime()) == lastRationMorning) return;
        if (peasant.level().getGameTime() < nextRationAttempt) return;
        collectDailyRation();
    }

    /**
     * Which morning it is: counted from the moment NPCs get up (SleepSystem's end of night), not from midnight -
     * they wake shortly before the day number changes, so by day number the morning they woke on was yesterday's.
     */
    private static long morningOf(long dayTime) {
        return Math.floorDiv(dayTime + (24000 - MORNING_TIME), 24000);
    }

    /** Called when the NPC wakes up: time to top up his food. */
    public void onWakeUp() {
        rationDue = true;
        nextRationAttempt = 0;
    }

    private void collectDailyRation() {
        var townHall = net.darkflameproduction.agotmod.entity.custom.npc.system.reputation.TownReputation.townHallOf(peasant);
        reportMeals(townHall);

        int carried = countFoodInInventory();
        int wanted = FOOD_CARRY_TARGET - carried;
        if (wanted <= 0) {
            finishRation();
            return;
        }
        if (townHall == null) {
            retryRation();
            return;
        }

        // His day's servings come first; beyond them he only takes food the rest of the town won't need today
        int daysFood = Math.max(0, DAYS_SERVINGS - carried);
        long othersNeed = (long) DAYS_SERVINGS * Math.max(0, townHall.getCitizenCount() - 1);
        long spare = Math.max(0, townHall.countFoodInStores() - daysFood - othersNeed);
        int asked = (int) Math.min(wanted, daysFood + spare);
        if (asked <= 0) {
            finishRation();
            return;
        }

        java.util.List<ItemStack> food = townHall.handOutFoodRation(peasant.getUUID(), carriedFoodByCategory(), asked);
        int got = 0;
        boolean full = false;
        for (ItemStack stack : food) {
            ItemStack rest = stack.copy();
            peasant.getInventorySystem().addItem(rest); // takes what fits, leaves the rest in `rest`
            got += stack.getCount() - rest.getCount();
            if (!rest.isEmpty()) {
                // No room: back into the town's stores (not a sale, so not paid for)
                full = true;
                townHall.addToTownInventory(
                        net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(rest.getItem()).toString(), rest.getCount());
            }
        }

        // Done once he has what he was allowed, or at least a day's food. Short of a day's food (the stores ran
        // dry, or no room) he asks again later - after the farmers deliver, or he has emptied his pockets
        if ((!full && got >= asked) || carried + got >= DAYS_SERVINGS) finishRation();
        else retryRation();
    }

    /** Tells the town what was eaten since the last report, in one go; kept for later if there is no town hall. */
    private void reportMeals(@javax.annotation.Nullable net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity townHall) {
        if (townHall == null || mealsSinceReport.isEmpty()) return;
        townHall.recordMeals(mealsSinceReport);
        mealsSinceReport.clear();
    }

    private void finishRation() {
        rationDue = false;
        lastRationMorning = morningOf(peasant.level().getDayTime());
        setNeedsFoodCollection(false);
    }

    private void retryRation() {
        nextRationAttempt = peasant.level().getGameTime() + RATION_RETRY_TICKS;
        // Work goes on while waiting - nothing waits on this flag for long
        setNeedsFoodCollection(false);
    }

    /** How many servings of each food category the NPC carries. */
    public java.util.Map<FoodCategory, Integer> carriedFoodByCategory() {
        java.util.Map<FoodCategory, Integer> carried = new java.util.EnumMap<>(FoodCategory.class);
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            FoodCategory category = FoodCategory.of(stack);
            if (category != null) carried.merge(category, stack.getCount(), Integer::sum);
        }
        return carried;
    }

    /** Whether the food top-up since waking has been done. */
    public boolean hasTakenRationToday() {
        return !rationDue;
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
        return countFoodInInventory() >= FOOD_CARRY_TARGET;
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
        // Only once a whole serving fits - eating at 19 of 20 wasted most of it, and NPCs ate three times their ration
        return getHungerLevel() <= MAX_HUNGER - SERVING_HUNGER;
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
        return FOOD_CARRY_TARGET;
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
            addHunger(SERVING_HUNGER);

            // A serving eaten: remembered for variety, and counted by his town (its food satisfaction)
            if (eatingCategory != null) {
                recentMeals.remove(eatingCategory);
                recentMeals.addFirst(eatingCategory);
                while (recentMeals.size() > RECENT_MEALS) recentMeals.removeLast();
                mealsSinceReport.merge(eatingCategory, 1, Integer::sum);
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
        compound.putBoolean("RationDue", rationDue);
        compound.putLong("LastRationMorning", lastRationMorning);
        CompoundTag meals = new CompoundTag();
        mealsSinceReport.forEach((category, eaten) -> meals.putInt(category.name(), eaten));
        compound.put("MealsSinceReport", meals);

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
        rationDue = !compound.contains("RationDue") || compound.getBoolean("RationDue");
        if (compound.contains("LastRationMorning")) lastRationMorning = compound.getLong("LastRationMorning");
        mealsSinceReport.clear();
        CompoundTag meals = compound.getCompound("MealsSinceReport");
        for (String key : meals.getAllKeys()) {
            try {
                mealsSinceReport.put(FoodCategory.valueOf(key), meals.getInt(key));
            } catch (IllegalArgumentException ignored) {
                // a food category that no longer exists
            }
        }

        // Load previous main hand item
        if (compound.contains("PreviousMainHandItem")) {
            CompoundTag previousItemTag = compound.getCompound("PreviousMainHandItem");
            previousMainHandItem = ItemStack.parse(peasant.registryAccess(), previousItemTag).orElse(ItemStack.EMPTY);
        } else {
            previousMainHandItem = ItemStack.EMPTY;
        }
    }
}
