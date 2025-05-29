package net.darkflameproduction.agotmod.inventory;

import net.darkflameproduction.agotmod.block.custom.FarmerBarrelBlockEntity;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class FarmerBarrelMenu extends AbstractContainerMenu {
    private static final int CONTAINER_ROWS = 6;
    private static final int CONTAINER_SLOTS = CONTAINER_ROWS * 9;
    private static final int PLAYER_INVENTORY_ROWS = 3;
    private static final int PLAYER_HOTBAR_SLOTS = 9;

    private final Container container;

    public FarmerBarrelMenu(int containerId, Inventory playerInventory, Container container) {
        super(MenuType.GENERIC_9x6, containerId);
        checkContainerSize(container, CONTAINER_SLOTS);
        this.container = container;
        container.startOpen(playerInventory.player);

        // Add barrel container slots (54 slots, 6 rows x 9 columns)
        this.addBarrelSlots(container);

        // Add player inventory slots
        this.addPlayerInventorySlots(playerInventory);

        // Add player hotbar slots
        this.addPlayerHotbarSlots(playerInventory);
    }

    private void addBarrelSlots(Container container) {
        int startX = 8;
        int startY = 18;

        for (int row = 0; row < CONTAINER_ROWS; row++) {
            for (int col = 0; col < 9; col++) {
                int slotIndex = col + row * 9;
                int slotX = startX + col * 18;
                int slotY = startY + row * 18;

                // Use regular vanilla slots
                this.addSlot(new Slot(container, slotIndex, slotX, slotY));
            }
        }
    }

    private void addPlayerInventorySlots(Inventory playerInventory) {
        int startX = 8;
        int startY = 18 + CONTAINER_ROWS * 18 + 13; // Below barrel slots with gap

        // Main inventory (3 rows)
        for (int row = 0; row < PLAYER_INVENTORY_ROWS; row++) {
            for (int col = 0; col < 9; col++) {
                int slotIndex = col + row * 9 + 9; // +9 to skip hotbar slots
                int slotX = startX + col * 18;
                int slotY = startY + row * 18;
                this.addSlot(new Slot(playerInventory, slotIndex, slotX, slotY));
            }
        }
    }

    private void addPlayerHotbarSlots(Inventory playerInventory) {
        int startX = 8;
        int startY = 18 + CONTAINER_ROWS * 18 + 13 + PLAYER_INVENTORY_ROWS * 18 + 4; // Below main inventory with gap

        // Hotbar (1 row)
        for (int col = 0; col < PLAYER_HOTBAR_SLOTS; col++) {
            int slotX = startX + col * 18;
            this.addSlot(new Slot(playerInventory, col, slotX, startY));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();

            if (index < CONTAINER_SLOTS) {
                // Moving from barrel to player inventory
                if (!this.moveItemStackTo(itemstack1, CONTAINER_SLOTS, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < CONTAINER_SLOTS + PLAYER_INVENTORY_ROWS * 9) {
                // Moving from player main inventory to barrel, then hotbar
                if (!this.moveItemStackTo(itemstack1, 0, CONTAINER_SLOTS, false)) {
                    if (!this.moveItemStackTo(itemstack1, CONTAINER_SLOTS + PLAYER_INVENTORY_ROWS * 9,
                            CONTAINER_SLOTS + PLAYER_INVENTORY_ROWS * 9 + PLAYER_HOTBAR_SLOTS, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else {
                // Moving from player hotbar to barrel, then main inventory
                if (!this.moveItemStackTo(itemstack1, 0, CONTAINER_SLOTS, false)) {
                    if (!this.moveItemStackTo(itemstack1, CONTAINER_SLOTS,
                            CONTAINER_SLOTS + PLAYER_INVENTORY_ROWS * 9, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }

    public Container getContainer() {
        return this.container;
    }
}