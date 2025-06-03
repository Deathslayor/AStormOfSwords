package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.Arrays;
import java.util.List;

public class InventorySystem {
    private final Northern_Peasant_Entity peasant;
    private final SimpleContainer inventory;

    // Player-like equipment storage (like LivingEntity)
    private final NonNullList<ItemStack> handItems = NonNullList.withSize(2, ItemStack.EMPTY); // [mainhand, offhand]
    private final NonNullList<ItemStack> armorItems = NonNullList.withSize(4, ItemStack.EMPTY); // [feet, legs, chest, head]

    // Equipment slot indices (matching Minecraft's conventions)
    private static final int MAINHAND_SLOT = 0;
    private static final int OFFHAND_SLOT = 1;
    private static final int FEET_SLOT = 0;
    private static final int LEGS_SLOT = 1;
    private static final int CHEST_SLOT = 2;
    private static final int HEAD_SLOT = 3;

    public InventorySystem(Northern_Peasant_Entity peasant, SimpleContainer inventory) {
        this.peasant = peasant;
        this.inventory = inventory;
    }

    public void tick() {
        // Sync main hand item with entity data for animations
        peasant.getEntityData().set(peasant.getMainHandItemAccessor(), handItems.get(MAINHAND_SLOT));
    }

    public SimpleContainer getInventory() {
        return inventory;
    }

    public SlotAccess getSlot(int slot) {
        int i = slot - Northern_Peasant_Entity.PEASANT_SLOT_OFFSET;
        return i >= 0 && i < inventory.getContainerSize() ?
                SlotAccess.forContainer(inventory, i) :
                SlotAccess.NULL;
    }

    public boolean hasSpace() {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (inventory.getItem(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean addItem(ItemStack stack) {
        if (stack.isEmpty()) return false;

        // Try to merge with existing stacks first
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack existing = inventory.getItem(i);
            if (!existing.isEmpty() && ItemStack.isSameItemSameComponents(existing, stack)) {
                int maxStackSize = Math.min(existing.getMaxStackSize(), inventory.getMaxStackSize());
                int canAdd = maxStackSize - existing.getCount();
                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, stack.getCount());
                    existing.grow(toAdd);
                    stack.shrink(toAdd);
                    if (stack.isEmpty()) return true;
                }
            }
        }

        // Try to place in empty slots
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (inventory.getItem(i).isEmpty()) {
                inventory.setItem(i, stack.copy());
                stack.setCount(0);
                return true;
            }
        }

        return false;
    }

    public ItemStack removeItem(ItemStack stack) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack existing = inventory.getItem(i);
            if (ItemStack.isSameItemSameComponents(existing, stack)) {
                return inventory.removeItem(i, stack.getCount());
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean hasItem(ItemStack stack) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack existing = inventory.getItem(i);
            if (ItemStack.isSameItemSameComponents(existing, stack) && existing.getCount() >= stack.getCount()) {
                return true;
            }
        }
        return false;
    }

    public void dropAllItems() {
        if (peasant.level() instanceof ServerLevel serverLevel) {
            // Drop inventory items
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                if (!stack.isEmpty()) {
                    peasant.spawnAtLocation(serverLevel, stack);
                    inventory.setItem(i, ItemStack.EMPTY);
                }
            }

            // Drop hand items
            for (int i = 0; i < handItems.size(); i++) {
                ItemStack stack = handItems.get(i);
                if (!stack.isEmpty()) {
                    peasant.spawnAtLocation(serverLevel, stack);
                    handItems.set(i, ItemStack.EMPTY);
                }
            }

            // Drop armor items
            for (int i = 0; i < armorItems.size(); i++) {
                ItemStack stack = armorItems.get(i);
                if (!stack.isEmpty()) {
                    peasant.spawnAtLocation(serverLevel, stack);
                    armorItems.set(i, ItemStack.EMPTY);
                }
            }
        }
    }

    // ========== HAND ITEM HANDLING ==========

    public ItemStack getItemInHand(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND ? handItems.get(MAINHAND_SLOT) : handItems.get(OFFHAND_SLOT);
    }

    public void setItemInHand(InteractionHand hand, ItemStack stack) {
        if (hand == InteractionHand.MAIN_HAND) {
            handItems.set(MAINHAND_SLOT, stack);
            peasant.getEntityData().set(peasant.getMainHandItemAccessor(), stack);
        } else {
            handItems.set(OFFHAND_SLOT, stack);
        }
    }

    public ItemStack getMainHandItem() {
        return handItems.get(MAINHAND_SLOT);
    }

    public ItemStack getOffhandItem() {
        return handItems.get(OFFHAND_SLOT);
    }

    // ========== ARMOR HANDLING ==========

    public Iterable<ItemStack> getArmorSlots() {
        return armorItems;
    }

    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return switch (slot) {
            case MAINHAND -> handItems.get(MAINHAND_SLOT);
            case OFFHAND -> handItems.get(OFFHAND_SLOT);
            case HEAD -> armorItems.get(HEAD_SLOT);
            case CHEST -> armorItems.get(CHEST_SLOT);
            case LEGS -> armorItems.get(LEGS_SLOT);
            case FEET -> armorItems.get(FEET_SLOT);
            case BODY -> ItemStack.EMPTY; // Body slot is not used for standard armor
        };
    }

    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        switch (slot) {
            case MAINHAND -> {
                handItems.set(MAINHAND_SLOT, stack);
                peasant.getEntityData().set(peasant.getMainHandItemAccessor(), stack);
            }
            case OFFHAND -> handItems.set(OFFHAND_SLOT, stack);
            case HEAD -> armorItems.set(HEAD_SLOT, stack);
            case CHEST -> armorItems.set(CHEST_SLOT, stack);
            case LEGS -> armorItems.set(LEGS_SLOT, stack);
            case FEET -> armorItems.set(FEET_SLOT, stack);
            case BODY -> { } // Body slot is not used for standard armor
        }
    }

    // ========== EQUIPMENT UTILITY METHODS ==========

    /**
     * Automatically equip an item to the appropriate slot if possible
     */
    public boolean autoEquipItem(ItemStack stack) {
        if (stack.isEmpty()) return false;

        EquipmentSlot preferredSlot = getPreferredSlotForItem(stack);
        if (preferredSlot != null) {
            ItemStack currentItem = getItemBySlot(preferredSlot);
            if (currentItem.isEmpty()) {
                setItemSlot(preferredSlot, stack.copy());
                return true;
            }
        }
        return false;
    }

    /**
     * Get the preferred equipment slot for an item
     */
    private EquipmentSlot getPreferredSlotForItem(ItemStack stack) {
        if (stack.isEmpty()) return null;

        // Determine slot based on item type
        if (stack.getItem() instanceof net.minecraft.world.item.ArmorItem armorItem) {
            return armorItem.getEquipmentSlot(stack);
        } else if (stack.getItem() instanceof net.minecraft.world.item.ShieldItem) {
            return EquipmentSlot.OFFHAND;
        } else if (stack.getItem() instanceof net.minecraft.world.item.SwordItem ||
                stack.getItem() instanceof net.minecraft.world.item.AxeItem ||
                stack.getItem() instanceof net.minecraft.world.item.PickaxeItem ||
                stack.getItem() instanceof net.minecraft.world.item.ShovelItem ||
                stack.getItem() instanceof net.minecraft.world.item.HoeItem ||
                stack.getItem() instanceof net.minecraft.world.item.BowItem ||
                stack.getItem() instanceof net.minecraft.world.item.CrossbowItem ||
                stack.getItem() instanceof net.minecraft.world.item.TridentItem) {
            return EquipmentSlot.MAINHAND;
        }

        // Default to main hand for other items
        return EquipmentSlot.MAINHAND;
    }

    /**
     * Check if entity is wearing a full armor set
     */
    public boolean hasFullArmor() {
        return !armorItems.get(HEAD_SLOT).isEmpty() &&
                !armorItems.get(CHEST_SLOT).isEmpty() &&
                !armorItems.get(LEGS_SLOT).isEmpty() &&
                !armorItems.get(FEET_SLOT).isEmpty();
    }

    /**
     * Get all equipped items (armor + held items)
     */
    public List<ItemStack> getAllEquippedItems() {
        return Arrays.asList(
                handItems.get(MAINHAND_SLOT),
                handItems.get(OFFHAND_SLOT),
                armorItems.get(HEAD_SLOT),
                armorItems.get(CHEST_SLOT),
                armorItems.get(LEGS_SLOT),
                armorItems.get(FEET_SLOT)
        );
    }

    /**
     * Remove and return an equipped item from a specific slot
     */
    public ItemStack unequipItem(EquipmentSlot slot) {
        ItemStack item = getItemBySlot(slot);
        if (!item.isEmpty()) {
            setItemSlot(slot, ItemStack.EMPTY);
            return item;
        }
        return ItemStack.EMPTY;
    }

    /**
     * Get all hand items (like LivingEntity.getHandSlots())
     */
    public Iterable<ItemStack> getHandSlots() {
        return handItems;
    }

    /**
     * Get the armor items collection (like LivingEntity.getArmorSlots())
     */
    public NonNullList<ItemStack> getArmorItems() {
        return armorItems;
    }

    /**
     * Get the hand items collection (like LivingEntity.getHandSlots())
     */
    public NonNullList<ItemStack> getHandItems() {
        return handItems;
    }

    // ========== VANILLA ARMOR NBT INTEGRATION ==========

    /**
     * Handle vanilla ArmorItems NBT tag when loading entity data
     * This ensures compatibility with /summon commands that include ArmorItems
     */
    public void handleVanillaArmorItems(CompoundTag compound, HolderLookup.Provider registryAccess) {
        if (compound.contains("ArmorItems", 9)) {
            ListTag armorItemsList = compound.getList("ArmorItems", 10);

            // ArmorItems NBT order: [feet, legs, chest, head]
            for (int i = 0; i < armorItemsList.size() && i < 4; i++) {
                CompoundTag itemTag = armorItemsList.getCompound(i);
                if (!itemTag.isEmpty()) {
                    ItemStack armorStack = ItemStack.parseOptional(registryAccess, itemTag);
                    if (!armorStack.isEmpty()) {
                        armorItems.set(i, armorStack);
                    }
                }
            }
        }
    }

    /**
     * Handle vanilla HandItems NBT tag when loading entity data
     * This ensures compatibility with /summon commands that include HandItems
     */
    public void handleVanillaHandItems(CompoundTag compound, HolderLookup.Provider registryAccess) {
        if (compound.contains("HandItems", 9)) {
            ListTag handItemsList = compound.getList("HandItems", 10);

            // HandItems NBT order: [mainhand, offhand]
            for (int i = 0; i < handItemsList.size() && i < 2; i++) {
                CompoundTag itemTag = handItemsList.getCompound(i);
                if (!itemTag.isEmpty()) {
                    ItemStack handStack = ItemStack.parseOptional(registryAccess, itemTag);
                    if (!handStack.isEmpty()) {
                        handItems.set(i, handStack);
                        // Update entity data for main hand
                        if (i == MAINHAND_SLOT) {
                            peasant.getEntityData().set(peasant.getMainHandItemAccessor(), handStack);
                        }
                    }
                }
            }
        }
    }

    // ========== GUI AND PERSISTENCE ==========

    public void openInventoryFor(ServerPlayer player) {
        player.openMenu(new SimpleMenuProvider(
                (id, playerInventory, playerEntity) -> new ChestMenu(
                        MenuType.GENERIC_9x6,
                        id,
                        playerInventory,
                        inventory,
                        6
                ),
                Component.translatable("entity.agotmod.northern_peasant.inventory", peasant.getDisplayName())
        ));
    }

    public void saveData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        // Save regular inventory
        peasant.writeInventoryToTag(compound, registryAccess);

        // Save hand items in custom format
        ListTag customHandItemsList = new ListTag();
        for (int i = 0; i < handItems.size(); i++) {
            CompoundTag itemTag = new CompoundTag();
            itemTag.putByte("Slot", (byte) i);
            if (!handItems.get(i).isEmpty()) {
                handItems.get(i).save(registryAccess, itemTag);
            }
            customHandItemsList.add(itemTag);
        }
        compound.put("CustomHandItems", customHandItemsList);

        // Save armor items in custom format
        ListTag customArmorItemsList = new ListTag();
        for (int i = 0; i < armorItems.size(); i++) {
            CompoundTag itemTag = new CompoundTag();
            itemTag.putByte("Slot", (byte) i);
            if (!armorItems.get(i).isEmpty()) {
                armorItems.get(i).save(registryAccess, itemTag);
            }
            customArmorItemsList.add(itemTag);
        }
        compound.put("CustomArmorItems", customArmorItemsList);

        // ALSO save in vanilla format for compatibility with /summon commands
        ListTag vanillaArmorItems = new ListTag();
        for (int i = 0; i < armorItems.size(); i++) {
            CompoundTag itemTag = new CompoundTag();
            if (!armorItems.get(i).isEmpty()) {
                armorItems.get(i).save(registryAccess, itemTag);
            }
            vanillaArmorItems.add(itemTag);
        }
        compound.put("ArmorItems", vanillaArmorItems);

        ListTag vanillaHandItems = new ListTag();
        for (int i = 0; i < handItems.size(); i++) {
            CompoundTag itemTag = new CompoundTag();
            if (!handItems.get(i).isEmpty()) {
                handItems.get(i).save(registryAccess, itemTag);
            }
            vanillaHandItems.add(itemTag);
        }
        compound.put("HandItems", vanillaHandItems);
    }

    public void loadData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        // Load regular inventory
        peasant.readInventoryFromTag(compound, registryAccess);

        // Load from vanilla ArmorItems/HandItems (for compatibility with /summon commands)
        handleVanillaArmorItems(compound, registryAccess);
        handleVanillaHandItems(compound, registryAccess);

        // Load from custom format ONLY if vanilla format doesn't exist
        // This prevents double-loading the same armor items
        if (!compound.contains("ArmorItems", 9) && compound.contains("CustomArmorItems", 9)) {
            ListTag armorItemsList = compound.getList("CustomArmorItems", 10);
            for (int i = 0; i < armorItemsList.size(); i++) {
                CompoundTag itemTag = armorItemsList.getCompound(i);
                int slot = itemTag.getByte("Slot") & 255;
                if (slot >= 0 && slot < armorItems.size()) {
                    armorItems.set(slot, ItemStack.parseOptional(registryAccess, itemTag));
                }
            }
        }

        // Load from custom hand items ONLY if vanilla format doesn't exist
        if (!compound.contains("HandItems", 9) && compound.contains("CustomHandItems", 9)) {
            ListTag handItemsList = compound.getList("CustomHandItems", 10);
            for (int i = 0; i < handItemsList.size(); i++) {
                CompoundTag itemTag = handItemsList.getCompound(i);
                int slot = itemTag.getByte("Slot") & 255;
                if (slot >= 0 && slot < handItems.size()) {
                    ItemStack handStack = ItemStack.parseOptional(registryAccess, itemTag);
                    handItems.set(slot, handStack);
                    // Update entity data for main hand
                    if (slot == MAINHAND_SLOT) {
                        peasant.getEntityData().set(peasant.getMainHandItemAccessor(), handStack);
                    }
                }
            }
        }

        // Sync main hand item with entity data after loading
        peasant.getEntityData().set(peasant.getMainHandItemAccessor(), handItems.get(MAINHAND_SLOT));
    }
}