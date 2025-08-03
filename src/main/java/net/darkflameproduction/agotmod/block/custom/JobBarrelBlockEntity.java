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
import java.util.HashMap;
import java.util.Map;

public class JobBarrelBlockEntity extends BlockEntity implements WorldlyContainer, net.minecraft.world.MenuProvider {
    private static final int CONTAINER_SIZE = 54; // Double chest size (6 rows x 9 columns)
    private NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);
    private String jobType;

    // Map for job type lookup - handles all job types including compound names
    private static final Map<String, String> JOB_TYPE_MAP = new HashMap<>();
    static {
        JOB_TYPE_MAP.put("alehouse_barrel", "alehouse");
        JOB_TYPE_MAP.put("armorsmith_barrel", "armorsmith");
        JOB_TYPE_MAP.put("baker_barrel", "baker");
        JOB_TYPE_MAP.put("banker_barrel", "banker");
        JOB_TYPE_MAP.put("bard_barrel", "bard");
        JOB_TYPE_MAP.put("builder_barrel", "builder");
        JOB_TYPE_MAP.put("butcher_barrel", "butcher");
        JOB_TYPE_MAP.put("caravan_master_barrel", "caravan_master");
        JOB_TYPE_MAP.put("cattle_herder_barrel", "cattle_herder");
        JOB_TYPE_MAP.put("charcoal_burner_barrel", "charcoal_burner");
        JOB_TYPE_MAP.put("chicken_breeder_barrel", "chicken_breeder");
        JOB_TYPE_MAP.put("farmer_barrel", "farmer");
        JOB_TYPE_MAP.put("goat_herder_barrel", "goat_herder");
        JOB_TYPE_MAP.put("grocer_barrel", "grocer");
        JOB_TYPE_MAP.put("guard_barrel", "guard");
        JOB_TYPE_MAP.put("herbalist_barrel", "herbalist");
        JOB_TYPE_MAP.put("horse_breeder_barrel", "horse_breeder");
        JOB_TYPE_MAP.put("hunter_barrel", "hunter");
        JOB_TYPE_MAP.put("innkeeper_barrel", "innkeeper");
        JOB_TYPE_MAP.put("jeweler_barrel", "jeweler");
        JOB_TYPE_MAP.put("lumberjack_barrel", "lumberjack");
        JOB_TYPE_MAP.put("maester_barrel", "maester");
        JOB_TYPE_MAP.put("miner_barrel", "miner");
        JOB_TYPE_MAP.put("pig_breeder_barrel", "pig_breeder");
        JOB_TYPE_MAP.put("pyromancer_barrel", "pyromancer");
        JOB_TYPE_MAP.put("quarry_barrel", "quarry");
        JOB_TYPE_MAP.put("scribe_barrel", "scribe");
        JOB_TYPE_MAP.put("septon_barrel", "septon");
        JOB_TYPE_MAP.put("sheep_herder_barrel", "sheep_herder");
        JOB_TYPE_MAP.put("shipwright_barrel", "shipwright");
        JOB_TYPE_MAP.put("slaver_barrel", "slaver");
        JOB_TYPE_MAP.put("smelter_barrel", "smelter");
        JOB_TYPE_MAP.put("stonemason_barrel", "stonemason");
        JOB_TYPE_MAP.put("swordsmith_barrel", "swordsmith");
        JOB_TYPE_MAP.put("tailor_barrel", "tailor");
        JOB_TYPE_MAP.put("tanner_barrel", "tanner");
        JOB_TYPE_MAP.put("trader_barrel", "trader");
    }

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
        this.jobType = extractJobType(blockState);
    }

    /**
     * Extracts the job type from the block state using the job type map
     */
    private String extractJobType(BlockState blockState) {
        String blockName = blockState.getBlock().getDescriptionId();

        // Extract the last part of the block name (after the last dot)
        String[] parts = blockName.split("\\.");
        if (parts.length > 0) {
            String blockIdentifier = parts[parts.length - 1];

            // Look up the job type in our map
            String jobType = JOB_TYPE_MAP.get(blockIdentifier);
            if (jobType != null) {
                return jobType;
            }

            // Fallback: try to extract from the identifier by removing "_barrel"
            if (blockIdentifier.endsWith("_barrel")) {
                return blockIdentifier.replace("_barrel", "");
            }
        }

        // Ultimate fallback
        return "unknown";
    }

    public String getJobType() {
        return jobType;
    }

    /**
     * Gets a display-friendly version of the job type
     */
    public String getDisplayJobType() {
        return jobType.replace("_", " ");
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

        // Load job type from NBT if available, otherwise extract from block state
        if (tag.contains("JobType")) {
            this.jobType = tag.getString("JobType");
        } else {
            this.jobType = extractJobType(this.getBlockState());
        }
    }

    // Menu provider
    @Override
    public Component getDisplayName() {
        String displayName = getDisplayJobType();
        // Capitalize first letter of each word
        String[] words = displayName.split(" ");
        StringBuilder capitalizedName = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i > 0) capitalizedName.append(" ");
            if (words[i].length() > 0) {
                capitalizedName.append(Character.toUpperCase(words[i].charAt(0)));
                if (words[i].length() > 1) {
                    capitalizedName.append(words[i].substring(1));
                }
            }
        }
        return Component.translatable("container.agotmod." + jobType + "_barrel", capitalizedName.toString());
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