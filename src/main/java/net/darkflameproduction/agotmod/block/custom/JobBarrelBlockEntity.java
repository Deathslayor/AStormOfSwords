package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

public class JobBarrelBlockEntity extends BlockEntity implements WorldlyContainer, net.minecraft.world.MenuProvider {
    private static final int CONTAINER_SIZE = 54; // Double chest size (6 rows x 9 columns)
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    private String jobType;

    // Simple wrapper for capability system
    private final IItemHandler itemHandler = new InvWrapper(this);

    private final ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(Level level, BlockPos pos, BlockState state) {
            level.playSound(null, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
            JobBarrelBlockEntity.this.updateBlockState(state, true);
        }

        @Override
        protected void onClose(Level level, BlockPos pos, BlockState state) {
            level.playSound(null, pos, SoundEvents.BARREL_CLOSE, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
            JobBarrelBlockEntity.this.updateBlockState(state, false);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos pos, BlockState state, int oldCount, int newCount) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu) {
                Container container = ((ChestMenu) player.containerMenu).getContainer();
                return container == JobBarrelBlockEntity.this;
            }
            return false;
        }
    };

    public JobBarrelBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.JOB_BARREL.get(), pos, blockState);
        // Extract job type from block name
        String blockName = blockState.getBlock().getDescriptionId();
        if (blockName.contains("farmer")) {
            this.jobType = "farmer";
        } else if (blockName.contains("miner")) {
            this.jobType = "miner";
        } else {
            // Extract from block name pattern like "block.modid.jobtype_barrel"
            String[] parts = blockName.split("\\.");
            if (parts.length > 0) {
                String lastPart = parts[parts.length - 1];
                this.jobType = lastPart.replace("_barrel", "");
            } else {
                this.jobType = "unknown";
            }
        }
    }

    public String getJobType() {
        return jobType;
    }

    // Basic inventory implementation
    @Override
    public int getContainerSize() {
        return CONTAINER_SIZE;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getItem(int slot) {
        return this.items.get(slot);
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return ContainerHelper.removeItem(this.items, slot, amount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(this.items, slot);
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        this.items.set(slot, stack);
        if (stack.getCount() > this.getMaxStackSize()) {
            stack.setCount(this.getMaxStackSize());
        }
        this.setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        }
        return player.distanceToSqr((double)this.worldPosition.getX() + 0.5,
                (double)this.worldPosition.getY() + 0.5,
                (double)this.worldPosition.getZ() + 0.5) <= 64.0;
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    // NBT handling - simple vanilla style
    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
        tag.putString("JobType", this.jobType);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
        // Note: jobType is set in constructor, but we could load it from NBT if needed
    }

    // Menu provider
    @Override
    public Component getDisplayName() {
        return Component.translatable("container.agotmod." + jobType + "_barrel");
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player, Player playerEntity) {
        return ChestMenu.sixRows(id, player, this);
    }

    // Container opening/closing
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

    // WorldlyContainer implementation for automation compatibility
    @Override
    public int[] getSlotsForFace(Direction side) {
        int[] slots = new int[this.getContainerSize()];
        for (int i = 0; i < slots.length; i++) {
            slots[i] = i;
        }
        return slots;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return true;
    }

    void updateBlockState(BlockState state, boolean open) {
        this.getLevel().setBlock(this.getBlockPos(), state.setValue(JobBarrelBlock.OPEN, Boolean.valueOf(open)), 3);
    }

    // Simple capability support
    public IItemHandler getItemHandler() {
        return itemHandler;
    }
}