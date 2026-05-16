package net.darkflameproduction.agotmod.entity.custom.npc.system.farmer;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class FarmingSystem {
    private final Peasant_Entity peasant;

    private boolean hasFarm = false;
    private boolean hasReturnedToJobBlockAfterFood = true;
    private FarmState currentFarmState = FarmState.NEEDS_FARM_SETUP;

    public enum FarmState {
        NEEDS_FARM_SETUP,
        PLANTING_CROPS,
        HARVESTING_CROPS,
        PATROLLING
    }

    public FarmingSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER)) return;

        if (peasant.hasJob() && peasant.getJobBlockPos() != null) {
            BlockPos jobBlockPos = peasant.getJobBlockPos();
            BlockState jobBlockState = peasant.level().getBlockState(jobBlockPos);

            if (!jobBlockState.is(ModBLocks.FARMER_BARREL.get())) {
                peasant.setJobType(JobSystem.JOB_NONE);
                peasant.setJobBlockPos(null);
                JobSystem.releaseJobBlockReservation(peasant.getUUID());
                hasFarm = false;
                currentFarmState = FarmState.NEEDS_FARM_SETUP;
            }
        }
    }

    private void triggerInteractAnimation() {
        if (!peasant.level().isClientSide) {
            peasant.triggerInteractAnimation();
        }
    }

    public boolean setupFarm() {
        if (peasant.getJobBlockPos() == null) return false;

        if (!hasFarm) {
            hasFarm = true;
            currentFarmState = FarmState.PLANTING_CROPS;
            triggerInteractAnimation();
            return true;
        }

        return true;
    }

    public int plantCrops() {
        if (!hasFarm || peasant.getJobBlockPos() == null) return 0;

        net.minecraft.world.level.block.Block cropToPlant = determineCropType();
        if (cropToPlant == null) return 0;

        BlockPos jobBlock = peasant.getJobBlockPos();
        int planted = 0;
        boolean triggeredAnimation = false;

        for (int x = -18; x <= 18; x++) {
            for (int z = -18; z <= 18; z++) {
                BlockPos farmlandPos = new BlockPos(jobBlock.getX() + x, jobBlock.getY() - 1, jobBlock.getZ() + z);
                BlockPos cropPos = farmlandPos.above();

                BlockState groundState = peasant.level().getBlockState(farmlandPos);
                BlockState aboveState = peasant.level().getBlockState(cropPos);

                if (groundState.getBlock() == net.minecraft.world.level.block.Blocks.FARMLAND && aboveState.isAir()) {
                    if (hasSeedInInventory(cropToPlant.asItem())) {
                        if (!triggeredAnimation) {
                            triggerInteractAnimation();
                            triggeredAnimation = true;
                        }

                        peasant.level().setBlock(cropPos, cropToPlant.defaultBlockState(), 3);
                        peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.6F, 1.2F);
                        consumeSeedFromInventory(cropToPlant.asItem());
                        planted++;
                    } else {
                        break;
                    }
                }
            }

            if (!hasSeedInInventory(cropToPlant.asItem())) break;
        }

        if (planted == 0) {
            currentFarmState = FarmState.HARVESTING_CROPS;
        }

        return planted;
    }

    public int plantSeedsOnEmptyFarmland() {
        if (!hasFarm || peasant.getJobBlockPos() == null) return 0;

        net.minecraft.world.level.block.Block cropToPlant = determineCropType();
        if (cropToPlant == null) return 0;

        BlockPos jobBlock = peasant.getJobBlockPos();
        int planted = 0;
        int maxPlantings = 3;
        boolean triggeredAnimation = false;

        for (int x = -18; x <= 18 && planted < maxPlantings; x++) {
            for (int z = -18; z <= 18 && planted < maxPlantings; z++) {
                BlockPos farmlandPos = new BlockPos(jobBlock.getX() + x, jobBlock.getY() - 1, jobBlock.getZ() + z);
                BlockPos cropPos = farmlandPos.above();

                BlockState groundState = peasant.level().getBlockState(farmlandPos);
                BlockState aboveState = peasant.level().getBlockState(cropPos);

                if (groundState.getBlock() == net.minecraft.world.level.block.Blocks.FARMLAND && aboveState.isAir()) {
                    if (hasSeedInInventory(cropToPlant.asItem())) {
                        if (!triggeredAnimation) {
                            triggerInteractAnimation();
                            triggeredAnimation = true;
                        }

                        peasant.level().setBlock(cropPos, cropToPlant.defaultBlockState(), 3);
                        peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.6F, 1.2F);
                        consumeSeedFromInventory(cropToPlant.asItem());
                        planted++;
                    } else {
                        break;
                    }
                }
            }

            if (!hasSeedInInventory(cropToPlant.asItem()) || planted >= maxPlantings) break;
        }

        return planted;
    }

    public BlockPos findNextHarvestPosition() {
        if (!hasFarm || peasant.getJobBlockPos() == null) return null;

        BlockPos jobBlock = peasant.getJobBlockPos();

        for (int x = -18; x <= 18; x++) {
            for (int z = -18; z <= 18; z++) {
                BlockPos cropPos = new BlockPos(jobBlock.getX() + x, jobBlock.getY(), jobBlock.getZ() + z);
                BlockState cropState = peasant.level().getBlockState(cropPos);

                if (isCropFullyGrown(cropState)) {
                    return cropPos;
                }
            }
        }

        return null;
    }

    public void harvestAndReplant(BlockPos cropPos) {
        BlockState cropState = peasant.level().getBlockState(cropPos);
        triggerInteractAnimation();

        if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.7F, 0.8F);

            List<ItemStack> drops = net.minecraft.world.level.block.Block.getDrops(cropState, serverLevel, cropPos, null);
            for (ItemStack drop : drops) {
                peasant.getInventorySystem().addItem(drop);
            }

            if (cropState.getBlock() instanceof net.minecraft.world.level.block.CropBlock cropBlock) {
                peasant.level().setBlock(cropPos, cropBlock.defaultBlockState(), 3);
                peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 0.5F, 1.3F);
                consumeSeedFromInventory(cropBlock.asItem());
            }
        }
    }

    private net.minecraft.world.level.block.Block determineCropType() {
        if (peasant.getJobBlockPos() == null) return null;

        BlockPos jobBlock = peasant.getJobBlockPos();

        for (int x = 18; x >= -18; x--) {
            for (int z = 18; z >= -18; z--) {
                BlockPos cropPos = new BlockPos(jobBlock.getX() + x, jobBlock.getY(), jobBlock.getZ() + z);
                BlockState state = peasant.level().getBlockState(cropPos);

                if (state.getBlock() instanceof net.minecraft.world.level.block.CropBlock) {
                    return state.getBlock();
                }
            }
        }

        ItemStack mostAbundantSeed = getMostAbundantSeeds();

        if (!mostAbundantSeed.isEmpty() &&
                mostAbundantSeed.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock) {
            return blockItem.getBlock();
        }

        return null;
    }

    public boolean isAtJobBlock() {
        if (peasant.getJobBlockPos() == null) return false;
        BlockPos jobBlock = peasant.getJobBlockPos();
        double distance = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());
        return distance <= 4.0D;
    }

    public BlockPos getJobBlockPosition() {
        return peasant.getJobBlockPos();
    }

    private boolean isCropFullyGrown(BlockState state) {
        if (state.getBlock() instanceof net.minecraft.world.level.block.CropBlock cropBlock) {
            return cropBlock.isMaxAge(state);
        }
        return false;
    }

    private ItemStack getMostAbundantSeeds() {
        Map<net.minecraft.world.item.Item, Integer> seedCounts = new HashMap<>();
        var inventory = peasant.getInventorySystem().getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                    blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock) {
                seedCounts.put(stack.getItem(), seedCounts.getOrDefault(stack.getItem(), 0) + stack.getCount());
            }
        }

        if (seedCounts.isEmpty()) return ItemStack.EMPTY;

        net.minecraft.world.item.Item mostAbundant = Collections.max(
                seedCounts.entrySet(), Map.Entry.comparingByValue()).getKey();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == mostAbundant) return stack;
        }

        return ItemStack.EMPTY;
    }

    private boolean hasSeedInInventory(net.minecraft.world.item.Item seedItem) {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == seedItem && !stack.isEmpty()) return true;
        }
        return false;
    }

    private void consumeSeedFromInventory(net.minecraft.world.item.Item seedItem) {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == seedItem) {
                stack.shrink(1);
                if (stack.isEmpty()) inventory.setItem(i, ItemStack.EMPTY);
                break;
            }
        }
    }

    public FarmState getCurrentFarmState() { return currentFarmState; }
    public void setCurrentFarmState(FarmState state) { this.currentFarmState = state; }
    public boolean hasFarm() { return hasFarm; }
    public boolean hasReturnedToJobBlockAfterFood() { return hasReturnedToJobBlockAfterFood; }
    public void setHasReturnedToJobBlockAfterFood(boolean returned) { this.hasReturnedToJobBlockAfterFood = returned; }

    public void saveData(CompoundTag compound) {
        compound.putBoolean("HasReturnedToJobBlockAfterFood", hasReturnedToJobBlockAfterFood);
        compound.putBoolean("HasFarm", hasFarm);
        compound.putString("CurrentFarmState", currentFarmState.name());
    }

    public void loadData(CompoundTag compound) {
        hasReturnedToJobBlockAfterFood = compound.getBoolean("HasReturnedToJobBlockAfterFood");
        hasFarm = compound.getBoolean("HasFarm");
        try {
            currentFarmState = FarmState.valueOf(compound.getString("CurrentFarmState"));
        } catch (IllegalArgumentException e) {
            currentFarmState = FarmState.NEEDS_FARM_SETUP;
        }
    }
}