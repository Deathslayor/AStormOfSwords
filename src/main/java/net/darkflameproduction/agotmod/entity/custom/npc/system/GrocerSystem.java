package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.*;

public class GrocerSystem {
    private final Northern_Peasant_Entity peasant;

    // Digital inventory counter - maps item resource location to count
    private final Map<String, Integer> digitalInventory = new HashMap<>();

    // NEW: Grocer balance tracking
    private long grocerBalance = 0; // Balance in halfpennies

    // Collection tracking
    private long lastCollectionDay = -1;
    private boolean hasCollectedToday = false;
    private GrocerState currentState = GrocerState.WAITING_FOR_COLLECTION_TIME;

    // Constants
    private static final int COLLECTION_TIME = 4000; // Game time when collection happens
    private static final int COLLECTION_RADIUS = 48; // 96x96 area (48 blocks in each direction)
    private static final int MAX_BARRELS_PER_DAY = 10;
    public static final int MAX_ITEMS_PER_TYPE = 10000;// Maximum barrels to collect from per day

    public enum GrocerState {
        WAITING_FOR_COLLECTION_TIME,
        COLLECTING_FROM_BARRELS,
        COLLECTION_COMPLETE
    }

    public GrocerSystem(Northern_Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (peasant.level().isClientSide) {
            return;
        }

        // Only run grocer logic if this NPC is a grocer AND has a valid job block
        if (!peasant.getJobType().equals(JobSystem.JOB_GROCER) ||
                peasant.getJobBlockPos() == null) {
            return;
        }

        // Verify the job block still exists
        BlockPos jobBlockPos = peasant.getJobBlockPos();
        if (peasant.level().getBlockState(jobBlockPos).getBlock() != net.minecraft.world.level.block.Blocks.BARREL) {
            return; // Job block is gone, let JobSystem handle job loss
        }

        long currentTime = peasant.level().getDayTime() % 24000;
        long currentDay = peasant.level().getDayTime() / 24000;

        // Reset daily collection status
        if (currentDay > lastCollectionDay) {
            hasCollectedToday = false;
            currentState = GrocerState.WAITING_FOR_COLLECTION_TIME;
            lastCollectionDay = currentDay;
        }

        // Check if it's collection time and we haven't collected today
        if (currentTime >= COLLECTION_TIME && !hasCollectedToday) {
            if (currentState == GrocerState.WAITING_FOR_COLLECTION_TIME) {
                startCollection();
            }
        }
    }

    private void startCollection() {
        currentState = GrocerState.COLLECTING_FROM_BARRELS;

        // Find all farm barrels within radius
        List<BlockPos> farmBarrels = findFarmBarrels();

        if (farmBarrels.isEmpty()) {
            currentState = GrocerState.COLLECTION_COMPLETE;
            hasCollectedToday = true;
            return;
        }

        // Limit to maximum barrels per day
        int barrelsToCollect = Math.min(farmBarrels.size(), MAX_BARRELS_PER_DAY);

        // Collect from barrels
        for (int i = 0; i < barrelsToCollect; i++) {
            collectFromBarrel(farmBarrels.get(i));
        }

        // Clean up inventory after collection
        cleanupZeroQuantityItems();

        currentState = GrocerState.COLLECTION_COMPLETE;
        hasCollectedToday = true;
    }

    private List<BlockPos> findFarmBarrels() {
        List<BlockPos> farmBarrels = new ArrayList<>();
        BlockPos grocerPos = peasant.blockPosition();

        // Search in a area around the grocer using the COLLECTION_RADIUS constant
        for (int x = -COLLECTION_RADIUS; x <= COLLECTION_RADIUS; x++) {
            for (int y = -16; y <= 16; y++) { // Keep reasonable Y range
                for (int z = -COLLECTION_RADIUS; z <= COLLECTION_RADIUS; z++) {
                    BlockPos checkPos = grocerPos.offset(x, y, z);

                    // Check if it's within the grocer's home area
                    if (!peasant.isWithinHomeArea(checkPos)) {
                        continue;
                    }

                    // Check if it's a FARMER_BARREL (your modded barrel)
                    if (peasant.level().getBlockState(checkPos).getBlock() ==
                            ModBLocks.FARMER_BARREL.get()) {
                        farmBarrels.add(checkPos);
                    }
                }
            }
        }

        return farmBarrels;
    }

    private void collectFromBarrel(BlockPos barrelPos) {
        BlockEntity blockEntity = peasant.level().getBlockEntity(barrelPos);

        if (!(blockEntity instanceof net.minecraft.world.Container container)) {
            return;
        }


        for (int slot = 0; slot < container.getContainerSize(); slot++) {
            ItemStack stack = container.getItem(slot);

            if (!stack.isEmpty()) {
                addToDigitalInventory(stack);
                container.setItem(slot, ItemStack.EMPTY);
            }
        }

        if (blockEntity instanceof net.minecraft.world.level.block.entity.BaseContainerBlockEntity baseContainer) {
            baseContainer.setChanged();
        } else {
            blockEntity.setChanged();
        }
    }

    // Add items to digital inventory with automatic cleanup and balance tracking
    public void addToDigitalInventory(ItemStack stack) {
        if (stack.isEmpty()) {
            return;
        }

        String itemKey = getItemKey(stack.getItem());
        int currentAmount = digitalInventory.getOrDefault(itemKey, 0);

        // Calculate how many we can actually add (enforce 10k limit)
        int spaceRemaining = MAX_ITEMS_PER_TYPE - currentAmount;

        if (spaceRemaining <= 0) {
            return; // Already at limit, can't add any
        }

        // Only add what fits within the limit
        int actualAmountAdded = Math.min(stack.getCount(), spaceRemaining);
        int newAmount = currentAmount + actualAmountAdded;

        if (newAmount > 0) {
            digitalInventory.put(itemKey, newAmount);

            // NEW: Add 10% of item value to grocer's balance
            if (actualAmountAdded > 0) {
                addToGrocerBalance(itemKey, actualAmountAdded);
            }
        }
    }

    // NEW: Method to add money to grocer's balance based on collected items
    private void addToGrocerBalance(String itemKey, int amount) {
        // Import the pricing from GrocerInventoryScreen
        int itemBuyPrice = net.darkflameproduction.agotmod.gui.GrocerInventoryScreen.getItemPrice(itemKey);

        if (itemBuyPrice > 0) {
            // Add 10% of the buy value to grocer's balance
            long valueToAdd = (long) Math.ceil((itemBuyPrice * amount) * 0.1);
            grocerBalance += valueToAdd;
        }
    }

    // NEW: Getter for grocer balance
    public long getGrocerBalance() {
        return grocerBalance;
    }

    // NEW: Method to deduct from grocer balance (for future use)
    public boolean deductFromGrocerBalance(long amount) {
        if (grocerBalance >= amount) {
            grocerBalance -= amount;
            return true;
        }
        return false;
    }

    private String getItemKey(Item item) {
        ResourceLocation itemLocation = BuiltInRegistries.ITEM.getKey(item);
        return itemLocation != null ? itemLocation.toString() : "unknown";
    }

    // Get digital inventory for GUI display with automatic cleanup
    public Map<String, Integer> getDigitalInventory() {
        // Clean up before returning the inventory
        cleanupZeroQuantityItems();
        return new HashMap<>(digitalInventory);
    }

    // Get sorted list of items for GUI display with automatic cleanup
    public List<GrocerInventoryEntry> getSortedInventoryEntries() {
        // Clean up zero quantities before creating the display list
        cleanupZeroQuantityItems();

        List<GrocerInventoryEntry> entries = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : digitalInventory.entrySet()) {
            String itemKey = entry.getKey();
            int amount = entry.getValue();

            if (amount > 0) { // Double-check that we only include positive amounts
                // Get the actual item for display name
                ResourceLocation itemLocation = ResourceLocation.tryParse(itemKey);
                if (itemLocation != null) {
                    Item item = BuiltInRegistries.ITEM.getValue(itemLocation);
                    if (item != null && item != net.minecraft.world.item.Items.AIR) {
                        String displayName = new ItemStack(item).getHoverName().getString();
                        entries.add(new GrocerInventoryEntry(displayName, amount, itemKey));
                    }
                }
            }
        }

        // Sort alphabetically by display name
        entries.sort(Comparator.comparing(entry -> entry.displayName));

        return entries;
    }

    // Helper class for GUI display
    public static class GrocerInventoryEntry {
        public final String displayName;
        public final int amount;
        public final String itemKey;

        public GrocerInventoryEntry(String displayName, int amount, String itemKey) {
            this.displayName = displayName;
            this.amount = amount;
            this.itemKey = itemKey;
        }
    }

    public void testDigitalInventory() {

        // Add some test items
        ItemStack testItem1 = new ItemStack(net.minecraft.world.item.Items.WHEAT, 64);
        ItemStack testItem2 = new ItemStack(net.minecraft.world.item.Items.CARROT, 32);
        ItemStack testItem3 = new ItemStack(net.minecraft.world.item.Items.POTATO, 16);

        addToDigitalInventory(testItem1);
        addToDigitalInventory(testItem2);
        addToDigitalInventory(testItem3);

        // Check if items were added
        Map<String, Integer> inventory = getDigitalInventory();
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        // Test the sorted entries method
        List<GrocerInventoryEntry> entries = getSortedInventoryEntries();
        for (GrocerInventoryEntry entry : entries) {
            System.out.println("  " + entry.displayName + " x" + entry.amount);
        }
    }

    // Remove items from digital inventory with automatic cleanup
    public boolean removeFromDigitalInventory(String itemKey, int amount) {
        int currentAmount = digitalInventory.getOrDefault(itemKey, 0);
        if (currentAmount >= amount) {
            int newAmount = currentAmount - amount;
            if (newAmount <= 0) {
                // Remove the item completely if quantity becomes 0 or negative
                digitalInventory.remove(itemKey);
            } else {
                digitalInventory.put(itemKey, newAmount);
            }
            return true;
        }
        return false;
    }

    // Set item quantity with automatic cleanup
    public void setDigitalInventoryAmount(String itemKey, int amount) {
        if (amount <= 0) {
            // Remove the item if setting to 0 or negative
            digitalInventory.remove(itemKey);
        } else {
            digitalInventory.put(itemKey, amount);
        }
    }

    // Check if grocer has specific item in digital inventory
    public boolean hasDigitalItem(String itemKey, int amount) {
        return digitalInventory.getOrDefault(itemKey, 0) >= amount;
    }

    // Forces a refresh of the digital inventory display
    public void refreshInventoryDisplay() {
        // Clean up items with 0 or negative quantities
        cleanupZeroQuantityItems();

    }

    // Removes items with 0 or negative quantities from the digital inventory
    public void cleanupZeroQuantityItems() {
        int initialSize = digitalInventory.size();
        digitalInventory.entrySet().removeIf(entry -> entry.getValue() <= 0);
        int finalSize = digitalInventory.size();

        if (finalSize < initialSize) {
        }
    }

    // Get total number of different item types (after cleanup)
    public int getUniqueItemCount() {
        cleanupZeroQuantityItems();
        return digitalInventory.size();
    }

    // Get total quantity of all items (after cleanup)
    public int getTotalItemCount() {
        cleanupZeroQuantityItems();
        return digitalInventory.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Getters
    public GrocerState getCurrentState() { return currentState; }
    public boolean hasCollectedToday() { return hasCollectedToday; }
    public long getLastCollectionDay() { return lastCollectionDay; }

    // Called when NPC is removed from world
    public void onRemove() {
        // Clear any temporary state if needed
        // For grocer, we don't need to do anything special on removal
        // The digital inventory will be saved/loaded with NBT data
    }

    // Save method with cleanup before saving
    public void saveData(CompoundTag compound) {
        compound.putLong("LastCollectionDay", lastCollectionDay);
        compound.putBoolean("HasCollectedToday", hasCollectedToday);
        compound.putString("CurrentState", currentState.name());

        // NEW: Save grocer balance
        compound.putLong("GrocerBalance", grocerBalance);

        // Clean up before saving to avoid saving empty entries
        cleanupZeroQuantityItems();

        // Save digital inventory
        CompoundTag inventoryTag = new CompoundTag();
        for (Map.Entry<String, Integer> entry : digitalInventory.entrySet()) {
            // Only save entries with positive quantities (double safety check)
            if (entry.getValue() > 0) {
                inventoryTag.putInt(entry.getKey(), entry.getValue());
            }
        }
        compound.put("DigitalInventory", inventoryTag);
    }

    // Add this method to GrocerSystem class
    public void addCoinsToBalance(long amount) {
        grocerBalance += amount;
    }

    // Load method with cleanup after loading
    public void loadData(CompoundTag compound) {
        lastCollectionDay = compound.getLong("LastCollectionDay");
        hasCollectedToday = compound.getBoolean("HasCollectedToday");

        try {
            currentState = GrocerState.valueOf(compound.getString("CurrentState"));
        } catch (IllegalArgumentException e) {
            currentState = GrocerState.WAITING_FOR_COLLECTION_TIME;
        }

        // NEW: Load grocer balance
        grocerBalance = compound.getLong("GrocerBalance");

        // Load digital inventory
        digitalInventory.clear();
        if (compound.contains("DigitalInventory")) {
            CompoundTag inventoryTag = compound.getCompound("DigitalInventory");
            for (String key : inventoryTag.getAllKeys()) {
                int value = inventoryTag.getInt(key);
                // Only load entries with positive quantities
                if (value > 0) {
                    digitalInventory.put(key, value);
                } else {
                }
            }
        } else {
        }

        // Cleanup any potential issues after loading
        cleanupZeroQuantityItems();

    }
}