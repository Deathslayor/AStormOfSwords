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
        ensureSeeds();

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
        ensureSeeds();

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

                // The crop already growing here - if he has its seed (else whatever seed he has)
                if (state.getBlock() instanceof net.minecraft.world.level.block.CropBlock
                        && hasSeedInInventory(state.getBlock().asItem())) {
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

    // ===== SEEDS FROM THE TOWN =====

    /** How many seeds he takes at most at once. */
    private static final int MAX_SEED_WITHDRAWAL = 64;
    /** After asking the town for seeds (got them or not), the soonest he asks again. */
    private static final long SEED_FETCH_COOLDOWN = 20 * 30;
    private long nextSeedFetch = 0;

    /**
     * Tilled farmland lies empty and he has no seed to plant: he takes seeds from his town's stores - enough for
     * the empty farmland. The crop is the one already growing in his field if the town has its seed, else the seed
     * the town holds most of. Seeds that are also food (carrots, potatoes) are only taken when there is no other
     * seed - the town eats those.
     */
    private void ensureSeeds() {
        if (peasant.level().isClientSide || peasant.getJobBlockPos() == null) return;
        long now = peasant.level().getGameTime();
        if (now < nextSeedFetch) return;

        int empty = countEmptyFarmland();
        if (empty <= 0) return;
        int carried = countSeedsInInventory();
        if (carried >= empty) return;
        nextSeedFetch = now + SEED_FETCH_COOLDOWN;

        var townHall = net.darkflameproduction.agotmod.entity.custom.npc.system.reputation.TownReputation.townHallOf(peasant);
        if (townHall == null) return;

        String seed = chooseSeed(townHall.getTownInventory());
        if (seed == null) return;
        long stock = townHall.getTownInventory().getOrDefault(seed, 0L);
        int amount = (int) Math.min(Math.min(empty - carried, MAX_SEED_WITHDRAWAL), stock);
        if (amount <= 0 || !townHall.removeFromTownInventory(seed, amount)) return;

        net.minecraft.world.item.Item item = net.minecraft.core.registries.BuiltInRegistries.ITEM
                .get(net.minecraft.resources.ResourceLocation.parse(seed));
        ItemStack seeds = new ItemStack(item, amount);
        peasant.getInventorySystem().addItem(seeds); // takes what fits, leaves the rest in `seeds`
        if (!seeds.isEmpty()) townHall.addToTownInventory(seed, seeds.getCount()); // no room: stays in the stores
        if (seeds.getCount() < amount) triggerInteractAnimation();
    }

    /** The seed to take from the stores (an item key), or null if the town has none. */
    private String chooseSeed(Map<String, Long> stores) {
        // The crop already in his field, if the town has its seed
        net.minecraft.world.level.block.Block fieldCrop = cropInField();
        if (fieldCrop != null) {
            String key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(fieldCrop.asItem()).toString();
            if (stores.getOrDefault(key, 0L) > 0) return key;
        }
        // Else the seed the town has most of - real seeds before food crops
        String best = null;
        long bestStock = 0;
        boolean bestIsFood = true;
        for (Map.Entry<String, Long> entry : stores.entrySet()) {
            if (entry.getValue() <= 0 || !isSeedKey(entry.getKey())) continue;
            boolean food = net.darkflameproduction.agotmod.economy.FoodCategory.of(entry.getKey()) != null;
            if (best == null || (bestIsFood && !food) || (bestIsFood == food && entry.getValue() > bestStock)) {
                best = entry.getKey();
                bestStock = entry.getValue();
                bestIsFood = food;
            }
        }
        return best;
    }

    /** An item that plants a crop on farmland. */
    private static boolean isSeedKey(String itemKey) {
        net.minecraft.resources.ResourceLocation id = net.minecraft.resources.ResourceLocation.tryParse(itemKey);
        if (id == null) return false;
        net.minecraft.world.item.Item item = net.minecraft.core.registries.BuiltInRegistries.ITEM.get(id);
        return item instanceof net.minecraft.world.item.BlockItem blockItem
                && blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock;
    }

    /** A crop growing in his field, or null. */
    private net.minecraft.world.level.block.Block cropInField() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        for (int x = -18; x <= 18; x++) {
            for (int z = -18; z <= 18; z++) {
                BlockState state = peasant.level().getBlockState(jobBlock.offset(x, 0, z));
                if (state.getBlock() instanceof net.minecraft.world.level.block.CropBlock) return state.getBlock();
            }
        }
        return null;
    }

    /** Tilled farmland in his field with nothing growing on it. */
    private int countEmptyFarmland() {
        BlockPos jobBlock = peasant.getJobBlockPos();
        int empty = 0;
        for (int x = -18; x <= 18; x++) {
            for (int z = -18; z <= 18; z++) {
                BlockPos farmland = new BlockPos(jobBlock.getX() + x, jobBlock.getY() - 1, jobBlock.getZ() + z);
                if (peasant.level().getBlockState(farmland).getBlock() == net.minecraft.world.level.block.Blocks.FARMLAND
                        && peasant.level().getBlockState(farmland.above()).isAir()) {
                    empty++;
                }
            }
        }
        return empty;
    }

    /** All the seeds he carries, of any crop. */
    private int countSeedsInInventory() {
        int count = 0;
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem
                    && blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock) {
                count += stack.getCount();
            }
        }
        return count;
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
