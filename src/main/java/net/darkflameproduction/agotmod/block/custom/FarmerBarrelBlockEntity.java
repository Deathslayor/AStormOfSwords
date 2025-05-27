package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.inventory.FarmerBarrelMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FarmerBarrelBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
    private NonNullList<ItemStack> items = NonNullList.withSize(54, ItemStack.EMPTY);
    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            FarmerBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_OPEN);
            FarmerBarrelBlockEntity.this.updateBlockState(state, true);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            FarmerBarrelBlockEntity.this.playSound(state, SoundEvents.BARREL_CLOSE);
            FarmerBarrelBlockEntity.this.updateBlockState(state, false);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof FarmerBarrelMenu) {
                Container container = ((FarmerBarrelMenu)player.containerMenu).getContainer();
                return container == FarmerBarrelBlockEntity.this;
            }
            return false;
        }
    };

    public FarmerBarrelBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.FARMER_BARREL.get(), pos, blockState);
    }

    // Override getMaxStackSize to allow 999 items per stack
    @Override
    public int getMaxStackSize() {
        return 999;
    }

    // Override getMaxStackSize for specific ItemStack
    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 999; // Force 999 for all items in this container
    }

    // Ensure items can be placed in this container
    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        return true;
    }

    // Override setItem to handle large stacks properly
    @Override
    public void setItem(int slot, ItemStack stack) {
        super.setItem(slot, stack);
        this.setChanged();
    }

    // Custom item insertion logic for hoppers/entities
    @Override
    public ItemStack removeItem(int slot, int amount) {
        ItemStack result = super.removeItem(slot, amount);
        if (!result.isEmpty()) {
            this.setChanged();
        }
        return result;
    }

    // Override the item insertion logic to properly handle stacking beyond 64
    public ItemStack insertItem(ItemStack stackToInsert) {
        if (stackToInsert.isEmpty()) {
            return ItemStack.EMPTY;
        }

        // First, try to add to existing stacks
        for (int i = 0; i < this.getContainerSize(); i++) {
            ItemStack existingStack = this.getItem(i);

            if (!existingStack.isEmpty() && ItemStack.isSameItemSameComponents(existingStack, stackToInsert)) {
                int maxStack = Math.min(999, stackToInsert.getMaxStackSize());
                int canAdd = maxStack - existingStack.getCount();

                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, stackToInsert.getCount());
                    existingStack.grow(toAdd);
                    stackToInsert.shrink(toAdd);
                    this.setChanged();

                    if (stackToInsert.isEmpty()) {
                        return ItemStack.EMPTY;
                    }
                }
            }
        }

        // Then, try to put in empty slots
        for (int i = 0; i < this.getContainerSize(); i++) {
            if (this.getItem(i).isEmpty()) {
                int maxStack = Math.min(999, stackToInsert.getMaxStackSize());
                int toPlace = Math.min(maxStack, stackToInsert.getCount());

                ItemStack newStack = stackToInsert.copy();
                newStack.setCount(toPlace);
                this.setItem(i, newStack);
                stackToInsert.shrink(toPlace);

                if (stackToInsert.isEmpty()) {
                    return ItemStack.EMPTY;
                }
            }
        }

        return stackToInsert; // Return remaining items that couldn't be inserted
    }

    // Override for hopper interactions (this is the key method for automatic item insertion)
    @Override
    public int[] getSlotsForFace(net.minecraft.core.Direction side) {
        // Return all slots as available for insertion from any side
        int[] slots = new int[this.getContainerSize()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i;
        }
        return slots;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, net.minecraft.core.Direction direction) {
        return this.canPlaceItem(index, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, net.minecraft.core.Direction direction) {
        return true;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!this.trySaveLootTable(tag)) {
            ContainerHelper.saveAllItems(tag, this.items, registries);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ContainerHelper.loadAllItems(tag, this.items, registries);
        }
    }

    @Override
    public int getContainerSize() {
        return 54;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.agotmod.farmer_barrel");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        return new FarmerBarrelMenu(id, player, this);
    }

    @Override
    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    void updateBlockState(BlockState state, boolean open) {
        this.level.setBlock(this.getBlockPos(), state.setValue(FarmerBarrelBlock.OPEN, Boolean.valueOf(open)), 3);
    }

    void playSound(BlockState state, SoundEvent sound) {
        Vec3i vec3i = state.getValue(FarmerBarrelBlock.FACING).getUnitVec3i();
        double d0 = (double)this.worldPosition.getX() + 0.5 + (double)vec3i.getX() / 2.0;
        double d1 = (double)this.worldPosition.getY() + 0.5 + (double)vec3i.getY() / 2.0;
        double d2 = (double)this.worldPosition.getZ() + 0.5 + (double)vec3i.getZ() / 2.0;
        this.level.playSound(null, d0, d1, d2, sound, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}