package net.darkflameproduction.agotmod.entity.custom.npc.system;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem;
import net.darkflameproduction.agotmod.inventory.NPCInventoryMenu;

import java.util.Arrays;
import java.util.List;

public class InventorySystem {
    private final Peasant_Entity peasant;
    private final SimpleContainer inventory;

    // Equipment slot indices (matching Minecraft's conventions)
    private static final int MAINHAND_SLOT = 0;
    private static final int OFFHAND_SLOT = 1;
    private static final int FEET_SLOT = 0;
    private static final int LEGS_SLOT = 1;
    private static final int CHEST_SLOT = 2;
    private static final int HEAD_SLOT = 3;

    public InventorySystem(Peasant_Entity peasant, SimpleContainer inventory) {
        this.peasant = peasant;
        this.inventory = inventory;
    }

    public void tick() {
        // Sync main hand item with entity data for animations
        peasant.getEntityData().set(peasant.getMainHandItemAccessor(), peasant.getMainHandItem());

        // Automatically equip best armor/weapons every few ticks
        if (peasant.tickCount % 20 == 0) { // Check every second (20 ticks)
            autoEquipBestArmor();
        }

        // Mark dirty periodically to ensure saves
        if (peasant.tickCount % 100 == 0) { // Every 5 seconds
            markDirty();
        }
    }

    /**
     * Mark the inventory as dirty to trigger saving
     */
    public void markDirty() {
        inventory.setChanged();
        // Sync equipment data immediately
        peasant.getEntityData().set(peasant.getMainHandItemAccessor(), peasant.getMainHandItem());
    }

    /**
     * Automatically equips any armor available in the inventory using native equipment system
     */
    public void autoEquipBestArmor() {
        // Check each armor slot and equip any armor if slot is empty
        autoEquipArmorForSlot(EquipmentSlot.HEAD);
        autoEquipArmorForSlot(EquipmentSlot.CHEST);
        autoEquipArmorForSlot(EquipmentSlot.LEGS);
        autoEquipArmorForSlot(EquipmentSlot.FEET);

        // Also check for weapons and shields to auto-equip
        autoEquipWeaponsAndShields();
    }

    /**
     * Equips the first armor found for a specific slot from the inventory using native system
     */
    private void autoEquipArmorForSlot(EquipmentSlot slot) {
        // Use the entity's native equipment system
        ItemStack currentArmor = peasant.getItemBySlot(slot);

        // Only equip if the slot is currently empty
        if (currentArmor.isEmpty()) {
            int armorSlotIndex = findFirstArmorSlotInInventory(slot);

            if (armorSlotIndex != -1) {
                // Get the armor from the inventory
                ItemStack armorToEquip = inventory.getItem(armorSlotIndex).copy();

                // Remove the armor from that specific inventory slot
                inventory.setItem(armorSlotIndex, ItemStack.EMPTY);

                // Equip the armor using native system - this automatically handles NBT saving
                peasant.setItemSlot(slot, armorToEquip);

                // Sync entity data
                if (slot == EquipmentSlot.MAINHAND) {
                    peasant.getEntityData().set(peasant.getMainHandItemAccessor(), armorToEquip);
                }
            }
        }
    }

    /**
     * Finds the inventory slot index of the first armor piece for a specific equipment slot
     */
    private int findFirstArmorSlotInInventory(EquipmentSlot targetSlot) {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof ArmorItem armorItem) {
                EquipmentSlot armorSlot = null;

                try {
                    armorSlot = armorItem.getEquipmentSlot(stack);
                } catch (Exception e) {
                    // Fallback to string matching
                    armorSlot = getEquipmentSlotFromItemName(stack);
                }

                if (armorSlot == null) {
                    armorSlot = getEquipmentSlotFromItemName(stack);
                }

                if (armorSlot == targetSlot) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Helper method for equipment slot detection by item name
     */
    private EquipmentSlot getEquipmentSlotFromItemName(ItemStack stack) {
        String itemName = stack.getItem().toString().toLowerCase();
        if (itemName.contains("helmet") || itemName.contains("cap") || itemName.contains("crown")) {
            return EquipmentSlot.HEAD;
        } else if (itemName.contains("chestplate") || itemName.contains("tunic") || itemName.contains("shirt")) {
            return EquipmentSlot.CHEST;
        } else if (itemName.contains("leggings") || itemName.contains("pants") || itemName.contains("legs")) {
            return EquipmentSlot.LEGS;
        } else if (itemName.contains("boots") || itemName.contains("shoes") || itemName.contains("feet")) {
            return EquipmentSlot.FEET;
        }
        return null;
    }

    /**
     * Auto-equips weapons and shields from inventory using native system
     */
    private void autoEquipWeaponsAndShields() {
        // Check main hand - equip weapons if empty
        ItemStack currentMainHand = peasant.getItemBySlot(EquipmentSlot.MAINHAND);
        if (currentMainHand.isEmpty()) {
            int weaponSlotIndex = findFirstWeaponInInventory();
            if (weaponSlotIndex != -1) {
                ItemStack weaponToEquip = inventory.getItem(weaponSlotIndex).copy();

                // Remove from inventory and equip using native system
                inventory.setItem(weaponSlotIndex, ItemStack.EMPTY);
                peasant.setItemSlot(EquipmentSlot.MAINHAND, weaponToEquip);
                peasant.getEntityData().set(peasant.getMainHandItemAccessor(), weaponToEquip);
            }
        }

        // Check offhand - equip shields if empty
        ItemStack currentOffHand = peasant.getItemBySlot(EquipmentSlot.OFFHAND);
        if (currentOffHand.isEmpty()) {
            int shieldSlotIndex = findFirstShieldInInventory();
            if (shieldSlotIndex != -1) {
                ItemStack shieldToEquip = inventory.getItem(shieldSlotIndex).copy();

                // Remove from inventory and equip using native system
                inventory.setItem(shieldSlotIndex, ItemStack.EMPTY);
                peasant.setItemSlot(EquipmentSlot.OFFHAND, shieldToEquip);
            }
        }
    }

    private int findFirstWeaponInInventory() {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isWeapon(stack)) {
                return i;
            }
        }
        return -1;
    }

    private int findFirstShieldInInventory() {
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof net.minecraft.world.item.ShieldItem) {
                return i;
            }
        }
        return -1;
    }

    private boolean isWeapon(ItemStack stack) {
        // Check vanilla weapon types
        if (stack.getItem() instanceof net.minecraft.world.item.SwordItem ||
                stack.getItem() instanceof net.minecraft.world.item.AxeItem ||
                stack.getItem() instanceof net.minecraft.world.item.PickaxeItem ||
                stack.getItem() instanceof net.minecraft.world.item.ShovelItem ||
                stack.getItem() instanceof net.minecraft.world.item.HoeItem ||
                stack.getItem() instanceof net.minecraft.world.item.BowItem ||
                stack.getItem() instanceof net.minecraft.world.item.CrossbowItem ||
                stack.getItem() instanceof net.minecraft.world.item.TridentItem) {
            return true;
        }

        // Check for your custom LevelRequiredSwordItem weapons
        try {
            // Use reflection to check if it's a LevelRequiredSwordItem without directly importing it
            Class<?> levelRequiredSwordItemClass = Class.forName("net.darkflameproduction.agotmod.item.custom.LevelRequiredSwordItem");
            if (levelRequiredSwordItemClass.isInstance(stack.getItem())) {
                return true;
            }
        } catch (ClassNotFoundException e) {
            // If the class doesn't exist, fall back to individual item checks
        }

        // Fallback: Check specific items if class check fails
        return stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.DRAGONGLASS_SPEAR.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.DRAGONGLASS_DAGGER.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.BRONZE_SWORD.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.BRONZE_SPATHA.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.BRONZE_SPEAR.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.BRONZE_PIKE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.BRONZE_DAGGER.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.BRONZE_BATTLEAXE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.IRON_LONGSWORD.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.IRON_SPEAR.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.IRON_PIKE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.IRON_MACE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.IRON_DAGGER.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.IRON_BATTLEAXE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_SWORD.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_LONGSWORD.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_SPEAR.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_PIKE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_MACE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_DAGGER.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_BATTLEAXE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_HALBERD.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.NOBLE_LONGSWORD.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.NOBLE_SPEAR.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.NOBLE_PIKE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.NOBLE_MACE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.NOBLE_DAGGER.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.NOBLE_BATTLEAXE.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.NOBLE_HALBERD.get() ||
                stack.getItem() == net.darkflameproduction.agotmod.item.ModItems.STEEL_BOW.get();
    }

    /**
     * Forces an immediate check and equipping of any available armor, weapons, and shields
     */
    public void forceArmorUpdate() {
        autoEquipBestArmor();
    }

    /**
     * Public method to force equipment check - can be called externally
     */
    public void forceEquipmentCheck() {
        autoEquipBestArmor();
        markDirty();
    }

    public SimpleContainer getInventory() {
        return inventory;
    }

    public SlotAccess getSlot(int slot) {
        int i = slot - Peasant_Entity.PEASANT_SLOT_OFFSET;
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
                    if (stack.isEmpty()) {
                        forceArmorUpdate();
                        markDirty();
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            if (inventory.getItem(i).isEmpty()) {
                ItemStack stackToPlace = stack.copy();
                inventory.setItem(i, stackToPlace);
                stack.setCount(0);

                forceArmorUpdate();
                markDirty();
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

            // Drop equipped items using native system
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                ItemStack stack = peasant.getItemBySlot(slot);
                if (!stack.isEmpty()) {
                    peasant.spawnAtLocation(serverLevel, stack);
                    peasant.setItemSlot(slot, ItemStack.EMPTY);
                }
            }
        }
    }


    public ItemStack getItemInHand(InteractionHand hand) {
        return hand == InteractionHand.MAIN_HAND ?
                peasant.getItemBySlot(EquipmentSlot.MAINHAND) :
                peasant.getItemBySlot(EquipmentSlot.OFFHAND);
    }

    public void setItemInHand(InteractionHand hand, ItemStack stack) {
        if (hand == InteractionHand.MAIN_HAND) {
            peasant.setItemSlot(EquipmentSlot.MAINHAND, stack);
            peasant.getEntityData().set(peasant.getMainHandItemAccessor(), stack);
        } else {
            peasant.setItemSlot(EquipmentSlot.OFFHAND, stack);
        }
    }

    public ItemStack getMainHandItem() {
        return peasant.getItemBySlot(EquipmentSlot.MAINHAND);
    }

    public ItemStack getOffhandItem() {
        return peasant.getItemBySlot(EquipmentSlot.OFFHAND);
    }

    public Iterable<ItemStack> getArmorSlots() {
        return peasant.getArmorSlots();
    }

    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return peasant.getItemBySlot(slot);
    }

    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        peasant.setItemSlot(slot, stack);
        if (slot == EquipmentSlot.MAINHAND) {
            peasant.getEntityData().set(peasant.getMainHandItemAccessor(), stack);
        }
    }


    public boolean autoEquipItem(ItemStack stack) {
        if (stack.isEmpty()) return false;

        EquipmentSlot preferredSlot = getPreferredSlotForItem(stack);
        if (preferredSlot != null) {
            ItemStack currentItem = peasant.getItemBySlot(preferredSlot);
            if (currentItem.isEmpty()) {
                peasant.setItemSlot(preferredSlot, stack.copy());
                if (preferredSlot == EquipmentSlot.MAINHAND) {
                    peasant.getEntityData().set(peasant.getMainHandItemAccessor(), stack);
                }
                return true;
            }
        }
        return false;
    }

    private EquipmentSlot getPreferredSlotForItem(ItemStack stack) {
        if (stack.isEmpty()) return null;

        if (stack.getItem() instanceof net.minecraft.world.item.ArmorItem armorItem) {
            return armorItem.getEquipmentSlot(stack);
        } else if (stack.getItem() instanceof net.minecraft.world.item.ShieldItem) {
            return EquipmentSlot.OFFHAND;
        } else if (isWeapon(stack)) {
            return EquipmentSlot.MAINHAND;
        }

        return EquipmentSlot.MAINHAND;
    }

    public boolean hasFullArmor() {
        return !peasant.getItemBySlot(EquipmentSlot.HEAD).isEmpty() &&
                !peasant.getItemBySlot(EquipmentSlot.CHEST).isEmpty() &&
                !peasant.getItemBySlot(EquipmentSlot.LEGS).isEmpty() &&
                !peasant.getItemBySlot(EquipmentSlot.FEET).isEmpty();
    }

    public List<ItemStack> getAllEquippedItems() {
        return Arrays.asList(
                peasant.getItemBySlot(EquipmentSlot.MAINHAND),
                peasant.getItemBySlot(EquipmentSlot.OFFHAND),
                peasant.getItemBySlot(EquipmentSlot.HEAD),
                peasant.getItemBySlot(EquipmentSlot.CHEST),
                peasant.getItemBySlot(EquipmentSlot.LEGS),
                peasant.getItemBySlot(EquipmentSlot.FEET)
        );
    }

    public ItemStack unequipItem(EquipmentSlot slot) {
        ItemStack item = peasant.getItemBySlot(slot);
        if (!item.isEmpty()) {
            peasant.setItemSlot(slot, ItemStack.EMPTY);
            if (slot == EquipmentSlot.MAINHAND) {
                peasant.getEntityData().set(peasant.getMainHandItemAccessor(), ItemStack.EMPTY);
            }
            return item;
        }
        return ItemStack.EMPTY;
    }

    public Iterable<ItemStack> getHandSlots() {
        return Arrays.asList(
                peasant.getItemBySlot(EquipmentSlot.MAINHAND),
                peasant.getItemBySlot(EquipmentSlot.OFFHAND)
        );
    }


    public void openInventoryFor(ServerPlayer player) {
        player.openMenu(new SimpleMenuProvider(
                (id, playerInventory, playerEntity) -> new NPCInventoryMenu(
                        id,
                        playerInventory,
                        peasant
                ),
                Component.translatable("entity.agotmod.northern_peasant.inventory", peasant.getDisplayName())
        ), buf -> buf.writeInt(peasant.getId()));
    }



    public void saveData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        peasant.writeInventoryToTag(compound, registryAccess);

        compound.putBoolean("InventorySystemSaved", true);
    }


    public void loadData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        peasant.readInventoryFromTag(compound, registryAccess);

        peasant.getEntityData().set(peasant.getMainHandItemAccessor(), peasant.getMainHandItem());
    }
}