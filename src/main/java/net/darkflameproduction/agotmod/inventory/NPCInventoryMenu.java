package net.darkflameproduction.agotmod.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.InventorySystem;

public class NPCInventoryMenu extends AbstractContainerMenu {
    private final Northern_Peasant_Entity npc;
    private final InventorySystem inventorySystem;
    private final SimpleContainer npcInventory;

    // Equipment container for armor and hand items
    private final SimpleContainer equipmentContainer;

    // Slot indices for equipment
    private static final int MAINHAND_SLOT = 0;
    private static final int OFFHAND_SLOT = 1;
    private static final int HEAD_SLOT = 2;
    private static final int CHEST_SLOT = 3;
    private static final int LEGS_SLOT = 4;
    private static final int FEET_SLOT = 5;

    public NPCInventoryMenu(int containerId, Inventory playerInventory, Northern_Peasant_Entity npc) {
        // Use our custom menu type - you'll need to register this properly
        super(net.darkflameproduction.agotmod.init.ModMenuTypes.NPC_INVENTORY_MENU.get(), containerId);
        this.npc = npc;
        this.inventorySystem = npc.getInventorySystem();
        this.npcInventory = inventorySystem.getInventory();

        // Create equipment container and sync with current equipment
        this.equipmentContainer = new SimpleContainer(6);
        syncEquipmentToContainer();

        // Add equipment slots (left side) - indices 0-5
        this.addSlot(new CustomEquipmentSlot(equipmentContainer, MAINHAND_SLOT, 8, 18, net.minecraft.world.entity.EquipmentSlot.MAINHAND));
        this.addSlot(new CustomEquipmentSlot(equipmentContainer, OFFHAND_SLOT, 8, 36, net.minecraft.world.entity.EquipmentSlot.OFFHAND));
        this.addSlot(new CustomArmorSlot(equipmentContainer, HEAD_SLOT, 8, 54, net.minecraft.world.entity.EquipmentSlot.HEAD));
        this.addSlot(new CustomArmorSlot(equipmentContainer, CHEST_SLOT, 8, 72, net.minecraft.world.entity.EquipmentSlot.CHEST));
        this.addSlot(new CustomArmorSlot(equipmentContainer, LEGS_SLOT, 8, 90, net.minecraft.world.entity.EquipmentSlot.LEGS));
        this.addSlot(new CustomArmorSlot(equipmentContainer, FEET_SLOT, 8, 108, net.minecraft.world.entity.EquipmentSlot.FEET));

        // Add NPC inventory slots (6x9 = 54 slots) - indices 6-59
        for (int row = 0; row < 6; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(npcInventory, col + row * 9, 44 + col * 18, 18 + row * 18));
            }
        }

        // Add player inventory slots (3x9 = 27 slots) - indices 60-86
        // Moved down 1 pixel to perfectly align with the background texture
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 44 + col * 18, 132 + row * 18));
            }
        }

        // Add player hotbar (1x9 = 9 slots) - indices 87-95
        // Moved down 1 pixel to perfectly align with the background texture
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(playerInventory, col, 44 + col * 18, 190));
        }

        // Total slots: 6 (equipment) + 54 (NPC) + 27 (player inv) + 9 (hotbar) = 96 slots (indices 0-95)
    }

    /**
     * Sync current equipment from InventorySystem to the equipment container with safety checks
     */
    private void syncEquipmentToContainer() {
        // Only sync TO container
        ItemStack newMainHand = inventorySystem.getMainHandItem();
        ItemStack newOffHand = inventorySystem.getOffhandItem();
        ItemStack newHead = inventorySystem.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.HEAD);
        ItemStack newChest = inventorySystem.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.CHEST);
        ItemStack newLegs = inventorySystem.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.LEGS);
        ItemStack newFeet = inventorySystem.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);

        // Safety checks: ensure items are not null before comparison
        // Use direct container access to bypass slot restrictions
        if (newMainHand != null && !ItemStack.matches(equipmentContainer.getItem(MAINHAND_SLOT), newMainHand)) {
            equipmentContainer.setItem(MAINHAND_SLOT, newMainHand.copy());
        }
        if (newOffHand != null && !ItemStack.matches(equipmentContainer.getItem(OFFHAND_SLOT), newOffHand)) {
            equipmentContainer.setItem(OFFHAND_SLOT, newOffHand.copy());
        }
        if (newHead != null && !ItemStack.matches(equipmentContainer.getItem(HEAD_SLOT), newHead)) {
            equipmentContainer.setItem(HEAD_SLOT, newHead.copy());
        }
        if (newChest != null && !ItemStack.matches(equipmentContainer.getItem(CHEST_SLOT), newChest)) {
            equipmentContainer.setItem(CHEST_SLOT, newChest.copy());
        }
        if (newLegs != null && !ItemStack.matches(equipmentContainer.getItem(LEGS_SLOT), newLegs)) {
            equipmentContainer.setItem(LEGS_SLOT, newLegs.copy());
        }
        if (newFeet != null && !ItemStack.matches(equipmentContainer.getItem(FEET_SLOT), newFeet)) {
            equipmentContainer.setItem(FEET_SLOT, newFeet.copy());
        }
    }

    /**
     * Sync equipment container changes back to the InventorySystem with safety checks
     * Note: This method is now largely unused since equipment slots are read-only
     */
    private void syncContainerToEquipment() {
        // Equipment slots are now read-only, so this method does nothing
        // Equipment can only be changed by the NPC's auto-equip system

        // Force save the current state
        inventorySystem.markDirty();
    }

    @Override
    public void slotsChanged(net.minecraft.world.Container container) {
        super.slotsChanged(container);

        // Equipment container changes are now ignored since slots are read-only
        // Only sync when NPC inventory changes
        if (container == npcInventory) {
            inventorySystem.markDirty();
            // Trigger auto-equip check when items are added/removed from NPC inventory
            inventorySystem.forceEquipmentCheck();
        }
    }

    @Override
    public void broadcastChanges() {
        super.broadcastChanges();
        // Don't constantly sync - only sync equipment container TO match the actual equipment
        // but don't sync FROM container unless user actually changed something
        syncEquipmentToContainer();
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack slotStack = slot.getItem();
            result = slotStack.copy();

            // Equipment slots (0-5) - These are now read-only, so shift-clicking does nothing
            if (index < 6) {
                // Equipment slots are read-only - no movement allowed
                return ItemStack.EMPTY;
            }
            // NPC inventory slots (6-59)
            else if (index < 60) {
                // Move to player inventory (equipment auto-equipping will happen automatically)
                if (!this.moveItemStackTo(slotStack, 60, 96, true)) {
                    return ItemStack.EMPTY;
                }
            }
            // Player inventory slots (60-95)
            else {
                // Move from player to NPC inventory
                if (!this.moveItemStackTo(slotStack, 6, 60, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (slotStack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            // Trigger equipment check after any inventory changes
            inventorySystem.forceEquipmentCheck();
        }

        return result;
    }

    @Override
    public boolean stillValid(Player player) {
        return npc.isAlive() && npc.distanceToSqr(player) <= 64.0D;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        // Equipment slots are read-only, so no need to sync changes back
        // Just clear the interacting player when inventory is closed
        npc.clearInteractingPlayer();
    }

    // Custom armor slot that only accepts armor for specific slots
    public static class CustomArmorSlot extends Slot {
        private final net.minecraft.world.entity.EquipmentSlot equipmentSlot;

        public CustomArmorSlot(SimpleContainer container, int slot, int x, int y, net.minecraft.world.entity.EquipmentSlot equipmentSlot) {
            super(container, slot, x, y);
            this.equipmentSlot = equipmentSlot;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            // Armor slots are read-only - players cannot place items
            return false;
        }

        @Override
        public boolean mayPickup(net.minecraft.world.entity.player.Player player) {
            // Armor slots are read-only - players cannot take items
            return false;
        }

        @Override
        public ItemStack remove(int amount) {
            // Prevent removal of items from armor slots
            return ItemStack.EMPTY;
        }

        @Override
        public void set(ItemStack stack) {
            // Prevent setting items in armor slots from the GUI
            // Only allow programmatic setting via syncEquipmentToContainer
        }
    }

    // Custom equipment slot for main/offhand
    public static class CustomEquipmentSlot extends Slot {
        private final net.minecraft.world.entity.EquipmentSlot equipmentSlot;

        public CustomEquipmentSlot(SimpleContainer container, int slot, int x, int y, net.minecraft.world.entity.EquipmentSlot equipmentSlot) {
            super(container, slot, x, y);
            this.equipmentSlot = equipmentSlot;
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            // Equipment slots are read-only - players cannot place items
            return false;
        }

        @Override
        public boolean mayPickup(net.minecraft.world.entity.player.Player player) {
            // Equipment slots are read-only - players cannot take items
            return false;
        }

        @Override
        public ItemStack remove(int amount) {
            // Prevent removal of items from equipment slots
            return ItemStack.EMPTY;
        }

        @Override
        public void set(ItemStack stack) {
            // Prevent setting items in equipment slots from the GUI
            // Only allow programmatic setting via syncEquipmentToContainer
        }
    }
}