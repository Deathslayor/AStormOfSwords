package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.*;

public class GrocerSystem {
    private final Northern_Peasant_Entity peasant;

    // Digital inventory counter - maps item resource location to count
    private final Map<String, Integer> digitalInventory = new HashMap<>();

    // Collection tracking
    private long lastCollectionDay = -1;
    private boolean hasCollectedToday = false;
    private GrocerState currentState = GrocerState.WAITING_FOR_COLLECTION_TIME;

    // Constants
    private static final int COLLECTION_TIME = 1100; // Game time when collection happens
    private static final int COLLECTION_RADIUS = 64; // Radius to search for farm barrels
    private static final int MAX_BARRELS_PER_DAY = 10; // Maximum barrels to collect from per day

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

        // Only run grocer logic if this NPC is a grocer
        if (!peasant.getJobType().equals(JobSystem.JOB_GROCER)) {
            return;
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

        currentState = GrocerState.COLLECTION_COMPLETE;
        hasCollectedToday = true;
    }

    private List<BlockPos> findFarmBarrels() {
        List<BlockPos> farmBarrels = new ArrayList<>();
        BlockPos grocerPos = peasant.blockPosition();

        // Search in a cubic area around the grocer
        for (int x = -COLLECTION_RADIUS; x <= COLLECTION_RADIUS; x++) {
            for (int y = -16; y <= 16; y++) { // Reasonable Y range
                for (int z = -COLLECTION_RADIUS; z <= COLLECTION_RADIUS; z++) {
                    BlockPos checkPos = grocerPos.offset(x, y, z);

                    // Check if it's within the grocer's home area
                    if (!peasant.isWithinHomeArea(checkPos)) {
                        continue;
                    }

                    // Check if it's a barrel
                    if (peasant.level().getBlockState(checkPos).getBlock() ==
                            net.minecraft.world.level.block.Blocks.BARREL) {
                        farmBarrels.add(checkPos);
                    }
                }
            }
        }

        return farmBarrels;
    }

    private void collectFromBarrel(BlockPos barrelPos) {
        BlockEntity blockEntity = peasant.level().getBlockEntity(barrelPos);

        if (!(blockEntity instanceof BarrelBlockEntity barrel)) {
            return;
        }

        // Collect all items from the barrel
        for (int slot = 0; slot < barrel.getContainerSize(); slot++) {
            ItemStack stack = barrel.getItem(slot);

            if (!stack.isEmpty()) {
                // Add to digital inventory
                addToDigitalInventory(stack);

                // Remove from barrel
                barrel.setItem(slot, ItemStack.EMPTY);
            }
        }

        // Mark the barrel as changed
        barrel.setChanged();
    }

    private void addToDigitalInventory(ItemStack stack) {
        String itemKey = getItemKey(stack.getItem());
        int currentAmount = digitalInventory.getOrDefault(itemKey, 0);
        digitalInventory.put(itemKey, currentAmount + stack.getCount());
    }

    private String getItemKey(Item item) {
        ResourceLocation itemLocation = BuiltInRegistries.ITEM.getKey(item);
        return itemLocation != null ? itemLocation.toString() : "unknown";
    }

    // Get digital inventory for GUI display
    public Map<String, Integer> getDigitalInventory() {
        return new HashMap<>(digitalInventory);
    }

    // Get sorted list of items for GUI display
    public List<GrocerInventoryEntry> getSortedInventoryEntries() {
        List<GrocerInventoryEntry> entries = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : digitalInventory.entrySet()) {
            String itemKey = entry.getKey();
            int amount = entry.getValue();

            if (amount > 0) {
                // Get the actual item for display name
                ResourceLocation itemLocation = ResourceLocation.tryParse(itemKey);
                if (itemLocation != null) {
                    // Use getValue() which returns the actual item, not an Optional
                    Item item = BuiltInRegistries.ITEM.getValue(itemLocation);
                    if (item != null) {
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

    // Remove items from digital inventory (for future trading functionality)
    public boolean removeFromDigitalInventory(String itemKey, int amount) {
        int currentAmount = digitalInventory.getOrDefault(itemKey, 0);
        if (currentAmount >= amount) {
            digitalInventory.put(itemKey, currentAmount - amount);
            return true;
        }
        return false;
    }

    // Check if grocer has specific item in digital inventory
    public boolean hasDigitalItem(String itemKey, int amount) {
        return digitalInventory.getOrDefault(itemKey, 0) >= amount;
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

    // Save/Load methods
    public void saveData(CompoundTag compound) {
        compound.putLong("LastCollectionDay", lastCollectionDay);
        compound.putBoolean("HasCollectedToday", hasCollectedToday);
        compound.putString("CurrentState", currentState.name());

        // Save digital inventory
        CompoundTag inventoryTag = new CompoundTag();
        for (Map.Entry<String, Integer> entry : digitalInventory.entrySet()) {
            inventoryTag.putInt(entry.getKey(), entry.getValue());
        }
        compound.put("DigitalInventory", inventoryTag);
    }

    public void loadData(CompoundTag compound) {
        lastCollectionDay = compound.getLong("LastCollectionDay");
        hasCollectedToday = compound.getBoolean("HasCollectedToday");

        try {
            currentState = GrocerState.valueOf(compound.getString("CurrentState"));
        } catch (IllegalArgumentException e) {
            currentState = GrocerState.WAITING_FOR_COLLECTION_TIME;
        }

        // Load digital inventory
        digitalInventory.clear();
        if (compound.contains("DigitalInventory")) {
            CompoundTag inventoryTag = compound.getCompound("DigitalInventory");
            for (String key : inventoryTag.getAllKeys()) {
                digitalInventory.put(key, inventoryTag.getInt(key));
            }
        }
    }
}