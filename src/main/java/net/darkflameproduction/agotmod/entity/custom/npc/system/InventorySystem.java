package net.darkflameproduction.agotmod.entity.custom.npc.system;

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
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.darkflameproduction.agotmod.entity.custom.npc.Northern_Peasant_Entity;

import java.util.Collections;

public class InventorySystem {
    private final Northern_Peasant_Entity peasant;
    private final SimpleContainer inventory;

    public InventorySystem(Northern_Peasant_Entity peasant, SimpleContainer inventory) {
        this.peasant = peasant;
        this.inventory = inventory;
    }

    public void tick() {
        // No regular ticking needed for inventory system
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
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stack = inventory.getItem(i);
                if (!stack.isEmpty()) {
                    peasant.spawnAtLocation(serverLevel, stack);
                    inventory.setItem(i, ItemStack.EMPTY);
                }
            }
        }
    }

    public ItemStack getItemInHand(InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {
            return peasant.getEntityData().get(peasant.getMainHandItemAccessor());
        }
        return ItemStack.EMPTY;
    }

    public void setItemInHand(InteractionHand hand, ItemStack stack) {
        if (hand == InteractionHand.MAIN_HAND) {
            peasant.getEntityData().set(peasant.getMainHandItemAccessor(), stack);
        }
    }

    public ItemStack getMainHandItem() {
        return getItemInHand(InteractionHand.MAIN_HAND);
    }

    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList(); // No armor for peasants
    }

    public ItemStack getItemBySlot(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return getItemInHand(InteractionHand.MAIN_HAND);
        }
        return ItemStack.EMPTY;
    }

    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            setItemInHand(InteractionHand.MAIN_HAND, stack);
        }
    }

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
        peasant.writeInventoryToTag(compound, registryAccess);

        ItemStack mainHandItem = getItemInHand(InteractionHand.MAIN_HAND);
        if (!mainHandItem.isEmpty()) {
            compound.put("MainHandItem", mainHandItem.saveOptional(registryAccess));
        }
    }

    public void loadData(CompoundTag compound, HolderLookup.Provider registryAccess) {
        peasant.readInventoryFromTag(compound, registryAccess);

        if (compound.contains("MainHandItem")) {
            ItemStack stack = ItemStack.parseOptional(registryAccess, compound.getCompound("MainHandItem"));
            setItemInHand(InteractionHand.MAIN_HAND, stack);
        }
    }
}