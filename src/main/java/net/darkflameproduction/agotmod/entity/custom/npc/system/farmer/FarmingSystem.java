package net.darkflameproduction.agotmod.entity.custom.npc.system.farmer;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class FarmingSystem {
    private final Peasant_Entity peasant;

    // Simple farm management
    private boolean hasFarm = false;
    private long lastFarmlandConversion = -1;

    // Work tracking
    private boolean hasReturnedToJobBlockAfterFood = true;
    private FarmState currentFarmState = FarmState.NEEDS_FARM_SETUP;

    public enum FarmState {
        NEEDS_FARM_SETUP,       // Need to set up farm area
        RETURN_TO_JOB_BLOCK,    // Walk to job block each morning
        CONVERTING_TO_FARMLAND, // Converting dirt/grass to farmland
        PLANTING_CROPS,         // Planting seeds on farmland
        HARVESTING_CROPS,       // Harvesting and replanting
        PATROLLING             // All work done, just patrolling
    }

    public FarmingSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        // ONLY run farming system logic for farmers!
        if (!peasant.getJobType().equals(JobSystem.JOB_FARMER)) {
            return;
        }

        // DEBUG: Log farmer state every 100 ticks (5 seconds)
        if (!peasant.level().isClientSide && peasant.tickCount % 100 == 0) {
            // Check what goal is currently running (simplified - just check if navigation is active)
            String currentGoal = "NONE";
            if (peasant.getNavigation().isInProgress()) {
                currentGoal = "NAVIGATION_ACTIVE";
            }

            System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: " +
                    "State=" + currentFarmState +
                    ", HasFarm=" + hasFarm +
                    ", JobBlockPos=" + peasant.getJobBlockPos() +
                    ", AtJobBlock=" + isAtJobBlock() +
                    ", ReturnedAfterFood=" + hasReturnedToJobBlockAfterFood +
                    ", DayTime=" + (peasant.level().getDayTime() % 24000) +
                    ", Sleeping=" + peasant.isSleeping() +
                    ", NeedsFoodCollection=" + peasant.needsFoodCollection() +
                    ", Eating=" + peasant.getHungerSystem().isEating() +
                    ", NavigationActive=" + currentGoal);
        }

        // Check if job block still exists - if not, lose job
        if (peasant.hasJob() && peasant.getJobBlockPos() != null) {
            BlockPos jobBlockPos = peasant.getJobBlockPos();
            BlockState jobBlockState = peasant.level().getBlockState(jobBlockPos);

            // Check if job block was destroyed - CHANGED FROM COMPOSTER TO FARMER_BARREL
            if (!jobBlockState.is(ModBLocks.FARMER_BARREL.get())) {
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Job block destroyed! Block at " + jobBlockPos + " is " + jobBlockState.getBlock());
                }
                // Job block destroyed, lose job
                peasant.setJobType(JobSystem.JOB_NONE);
                peasant.setJobBlockPos(null);
                JobSystem.releaseJobBlockReservation(peasant.getUUID());

                // Reset farming state
                hasFarm = false;
                currentFarmState = FarmState.NEEDS_FARM_SETUP;
            }
        }
    }

    /**
     * Triggers interact animation - stops movement and plays animation
     */
    private void triggerInteractAnimation() {
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG: FarmingSystem calling triggerInteractAnimation");
            peasant.triggerInteractAnimation();
        }
    }

    public boolean hasExcessItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        int meatCount = 0;
        int seedStackCount = 0;
        int totalItems = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                totalItems += stack.getCount();

                if (stack.is(ItemTags.create(
                        net.minecraft.resources.ResourceLocation.fromNamespaceAndPath("minecraft", "meat")))) {
                    meatCount += stack.getCount();
                } else if (isSeed(stack)) {
                    seedStackCount++;
                }
            }
        }

        // Calculate what should be kept
        int allowedMeat = Math.min(meatCount, 20);
        int allowedSeedStacks = Math.min(seedStackCount, 2);
        int allowedSeedItems = 0;

        // Count items in the allowed seed stacks
        int seedStacksFound = 0;
        for (int i = 0; i < inventory.getContainerSize() && seedStacksFound < allowedSeedStacks; i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty() && isSeed(stack)) {
                allowedSeedItems += stack.getCount();
                seedStacksFound++;
            }
        }

        int allowedTotalItems = allowedMeat + allowedSeedItems;

        // Has excess if total items exceed what we're allowed to keep
        return totalItems > allowedTotalItems;
    }

    private boolean isSeed(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock;
    }

    // Simple farm setup - just mark that we have a job block
    public boolean setupFarm() {
        if (peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Cannot setup farm - no job block position!");
            }
            return false;
        }

        if (!hasFarm) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Setting up farm at " + peasant.getJobBlockPos());
            }
            hasFarm = true;
            currentFarmState = FarmState.CONVERTING_TO_FARMLAND; // Skip RETURN_TO_JOB_BLOCK since we're already here

            // Trigger interact animation when setting up farm
            triggerInteractAnimation();
            return true;
        }

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Farm already setup");
        }
        return true;
    }

    // INITIAL farm setup conversion - no daily limits, converts entire area at once
    public int initialFarmlandSetup() {
        if (!hasFarm || peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Cannot do initial farmland setup - hasFarm=" + hasFarm + ", jobBlockPos=" + peasant.getJobBlockPos());
            }
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        int converted = 0;
        boolean triggeredAnimation = false;

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Starting INITIAL farmland setup around " + jobBlock);
        }

        // Convert entire 19x19 area centered on job block at Y-1 level (no daily limits)
        for (int x = -9; x <= 9; x++) {
            for (int z = -9; z <= 9; z++) {
                BlockPos targetPos = new BlockPos(
                        jobBlock.getX() + x,
                        jobBlock.getY() - 1,  // One level below job block
                        jobBlock.getZ() + z
                );

                BlockState currentBlock = peasant.level().getBlockState(targetPos);

                // Convert grass and dirt to farmland
                if (currentBlock.getBlock() == net.minecraft.world.level.block.Blocks.GRASS_BLOCK ||
                        currentBlock.getBlock() == net.minecraft.world.level.block.Blocks.DIRT) {

                    // Trigger interact animation for first conversion
                    if (!triggeredAnimation) {
                        triggerInteractAnimation();
                        triggeredAnimation = true;
                    }

                    peasant.level().setBlock(targetPos,
                            net.minecraft.world.level.block.Blocks.FARMLAND.defaultBlockState(), 3);

                    // Play grass breaking sound when converting to farmland
                    peasant.level().playSound(null, targetPos, SoundEvents.GRASS_BREAK,
                            SoundSource.BLOCKS, 0.8F, 1.0F);

                    converted++;
                }
            }
        }

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Initial farmland setup converted " + converted + " blocks");
        }

        // Always move to planting after initial setup, regardless of how much was converted
        currentFarmState = FarmState.PLANTING_CROPS;

        return converted;
    }

    // DAILY farmland conversion - convert with daily limits for expansion
    public int convertToFarmland() {
        if (!hasFarm || peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Cannot convert farmland - hasFarm=" + hasFarm + ", jobBlockPos=" + peasant.getJobBlockPos());
            }
            return 0;
        }

        // Check daily limit
        long currentDay = peasant.level().getDayTime() / 24000;
        long lastConversionDay = lastFarmlandConversion / 24000;

        if (currentDay <= lastConversionDay && lastFarmlandConversion != -1) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Already converted farmland today (day " + currentDay + ", last conversion day " + lastConversionDay + ")");
            }
            // Already converted today
            currentFarmState = FarmState.PLANTING_CROPS;
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        int converted = 0;
        boolean triggeredAnimation = false;

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Starting DAILY farmland conversion around " + jobBlock);
        }

        // Convert 15x15 area centered on job block at Y-1 level (daily expansion)
        for (int x = -7; x <= 7; x++) {
            for (int z = -7; z <= 7; z++) {
                BlockPos targetPos = new BlockPos(
                        jobBlock.getX() + x,
                        jobBlock.getY() - 1,  // One level below job block
                        jobBlock.getZ() + z
                );

                BlockState currentBlock = peasant.level().getBlockState(targetPos);

                // Convert grass and dirt to farmland
                if (currentBlock.getBlock() == net.minecraft.world.level.block.Blocks.GRASS_BLOCK ||
                        currentBlock.getBlock() == net.minecraft.world.level.block.Blocks.DIRT) {

                    // Trigger interact animation for first conversion
                    if (!triggeredAnimation) {
                        triggerInteractAnimation();
                        triggeredAnimation = true;
                    }

                    peasant.level().setBlock(targetPos,
                            net.minecraft.world.level.block.Blocks.FARMLAND.defaultBlockState(), 3);

                    // Play grass breaking sound when converting to farmland
                    peasant.level().playSound(null, targetPos, SoundEvents.GRASS_BREAK,
                            SoundSource.BLOCKS, 0.8F, 1.0F);

                    converted++;
                }
            }
        }

        // Update conversion time
        lastFarmlandConversion = peasant.level().getDayTime();

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG FARMER [" + peasant.getDisplayName().getString() + "]: Daily farmland conversion converted " + converted + " blocks");
        }

        // Move to next state if no conversion needed
        if (converted == 0) {
            currentFarmState = FarmState.PLANTING_CROPS;
        }

        return converted;
    }

    public int plantCrops() {
        if (!hasFarm || peasant.getJobBlockPos() == null) {
            return 0;
        }

        // Determine what crop to plant
        net.minecraft.world.level.block.Block cropToPlant = determineCropType();
        if (cropToPlant == null) {
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        int planted = 0;
        boolean triggeredAnimation = false;

        // Plant crops on farmland in 15x15 area
        for (int x = -9; x <= 9; x++) {
            for (int z = -9; z <= 9; z++) {
                BlockPos farmlandPos = new BlockPos(
                        jobBlock.getX() + x,
                        jobBlock.getY() - 1,  // Farmland level
                        jobBlock.getZ() + z
                );
                BlockPos cropPos = farmlandPos.above(); // Crop position

                BlockState groundState = peasant.level().getBlockState(farmlandPos);
                BlockState aboveState = peasant.level().getBlockState(cropPos);

                // Plant on farmland with empty space above
                if (groundState.getBlock() == net.minecraft.world.level.block.Blocks.FARMLAND &&
                        aboveState.isAir()) {

                    // CHECK IF WE HAVE THE SEED BEFORE PLANTING
                    if (hasSeedInInventory(cropToPlant.asItem())) {
                        // Trigger interact animation for first planting
                        if (!triggeredAnimation) {
                            triggerInteractAnimation();
                            triggeredAnimation = true;
                        }

                        peasant.level().setBlock(cropPos, cropToPlant.defaultBlockState(), 3);

                        // Play grass breaking sound when planting crops
                        peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK,
                                SoundSource.BLOCKS, 0.6F, 1.2F);

                        consumeSeedFromInventory(cropToPlant.asItem());
                        planted++;
                    } else {
                        // No more seeds, stop planting
                        break;
                    }
                }
            }
            // If we ran out of seeds, break out of the outer loop too
            if (!hasSeedInInventory(cropToPlant.asItem())) {
                break;
            }
        }

        if (planted == 0) {
            currentFarmState = FarmState.HARVESTING_CROPS;
        }

        return planted;
    }

    private net.minecraft.world.level.block.Block determineCropType() {
        if (peasant.getJobBlockPos() == null) return null;

        BlockPos jobBlock = peasant.getJobBlockPos();

        // Check for existing crops in the area (starting from top-right corner)
        for (int x = 9; x >= -9; x--) {
            for (int z = 9; z >= -9; z--) {
                BlockPos cropPos = new BlockPos(
                        jobBlock.getX() + x,
                        jobBlock.getY(), // Crop level (job block level)
                        jobBlock.getZ() + z
                );

                BlockState state = peasant.level().getBlockState(cropPos);
                if (state.getBlock() instanceof net.minecraft.world.level.block.CropBlock) {
                    return state.getBlock();
                }
            }
        }

        // No existing crops, use most abundant seed
        ItemStack mostAbundantSeed = getMostAbundantSeeds();
        if (!mostAbundantSeed.isEmpty() &&
                mostAbundantSeed.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock) {
            return blockItem.getBlock();
        }

        return null;
    }

    public BlockPos findNextHarvestPosition() {
        if (!hasFarm || peasant.getJobBlockPos() == null) {
            return null;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();

        // Look for mature crops in 15x15 area
        for (int x = -9; x <= 9; x++) {
            for (int z = -9; z <= 9; z++) {
                BlockPos cropPos = new BlockPos(
                        jobBlock.getX() + x,
                        jobBlock.getY(), // Crop level (job block level)
                        jobBlock.getZ() + z
                );

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

        // Trigger interact animation for harvesting
        triggerInteractAnimation();

        if (peasant.level() instanceof net.minecraft.server.level.ServerLevel serverLevel) {
            // Play grass breaking sound when harvesting crops
            peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK,
                    SoundSource.BLOCKS, 0.7F, 0.8F);

            // Harvest drops
            List<ItemStack> drops = net.minecraft.world.level.block.Block.getDrops(
                    cropState, serverLevel, cropPos, null);

            for (ItemStack drop : drops) {
                peasant.getInventorySystem().addItem(drop);
            }

            // Replant with same crop type
            if (cropState.getBlock() instanceof net.minecraft.world.level.block.CropBlock cropBlock) {
                peasant.level().setBlock(cropPos, cropBlock.defaultBlockState(), 3);

                // Play grass breaking sound when replanting
                peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK,
                        SoundSource.BLOCKS, 0.5F, 1.3F);

                consumeSeedFromInventory(cropBlock.asItem());
            }
        }

        // After harvesting, do maintenance work in the area
        performMaintenanceWork();
    }

    /**
     * Performs maintenance work after harvesting - converts dirt to farmland and plants seeds
     */
    public void performMaintenanceWork() {
        if (!hasFarm || peasant.getJobBlockPos() == null) {
            return;
        }

        // Convert up to 3 dirt/grass blocks to farmland
        int conversionsPerformed = convertDirtGrassToFarmland();

        // Plant seeds on up to 3 empty farmland blocks
        int plantingsPerformed = plantSeedsOnEmptyFarmland();

        // Animation is triggered within the individual methods if work is done
    }

    // New methods for harvesting-time maintenance
    public int convertDirtGrassToFarmland() {
        if (!hasFarm || peasant.getJobBlockPos() == null) {
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        int converted = 0;
        int maxConversions = 3; // Limit conversions per call for gradual work
        boolean triggeredAnimation = false;

        // Convert dirt/grass to farmland in 19x19 area (no daily limit during harvesting)
        for (int x = -9; x <= 9 && converted < maxConversions; x++) {
            for (int z = -9; z <= 9 && converted < maxConversions; z++) {
                BlockPos targetPos = new BlockPos(
                        jobBlock.getX() + x,
                        jobBlock.getY() - 1,  // One level below job block
                        jobBlock.getZ() + z
                );

                BlockState currentBlock = peasant.level().getBlockState(targetPos);

                // Convert grass and dirt to farmland
                if (currentBlock.getBlock() == net.minecraft.world.level.block.Blocks.GRASS_BLOCK ||
                        currentBlock.getBlock() == net.minecraft.world.level.block.Blocks.DIRT) {

                    // Trigger interact animation for first conversion
                    if (!triggeredAnimation) {
                        triggerInteractAnimation();
                        triggeredAnimation = true;
                    }

                    peasant.level().setBlock(targetPos,
                            net.minecraft.world.level.block.Blocks.FARMLAND.defaultBlockState(), 3);

                    // Play grass breaking sound when converting to farmland
                    peasant.level().playSound(null, targetPos, SoundEvents.GRASS_BREAK,
                            SoundSource.BLOCKS, 0.8F, 1.0F);

                    converted++;
                }
            }
        }

        return converted;
    }

    public int plantSeedsOnEmptyFarmland() {
        if (!hasFarm || peasant.getJobBlockPos() == null) {
            return 0;
        }

        // Determine what crop to plant
        net.minecraft.world.level.block.Block cropToPlant = determineCropType();
        if (cropToPlant == null) {
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        int planted = 0;
        int maxPlantings = 3; // Limit plantings per call for gradual work
        boolean triggeredAnimation = false;

        // Plant crops on empty farmland in 19x19 area
        for (int x = -9; x <= 9 && planted < maxPlantings; x++) {
            for (int z = -9; z <= 9 && planted < maxPlantings; z++) {
                BlockPos farmlandPos = new BlockPos(
                        jobBlock.getX() + x,
                        jobBlock.getY() - 1,  // Farmland level
                        jobBlock.getZ() + z
                );
                BlockPos cropPos = farmlandPos.above(); // Crop position

                BlockState groundState = peasant.level().getBlockState(farmlandPos);
                BlockState aboveState = peasant.level().getBlockState(cropPos);

                // Plant on farmland with empty space above
                if (groundState.getBlock() == net.minecraft.world.level.block.Blocks.FARMLAND &&
                        aboveState.isAir()) {

                    // CHECK IF WE HAVE THE SEED BEFORE PLANTING
                    if (hasSeedInInventory(cropToPlant.asItem())) {
                        // Trigger interact animation for first planting
                        if (!triggeredAnimation) {
                            triggerInteractAnimation();
                            triggeredAnimation = true;
                        }

                        peasant.level().setBlock(cropPos, cropToPlant.defaultBlockState(), 3);

                        // Play grass breaking sound when planting crops
                        peasant.level().playSound(null, cropPos, SoundEvents.GRASS_BREAK,
                                SoundSource.BLOCKS, 0.6F, 1.2F);

                        consumeSeedFromInventory(cropToPlant.asItem());
                        planted++;
                    } else {
                        // No more seeds, stop planting
                        break;
                    }
                }
            }
            // If we ran out of seeds or hit the max planting limit, break out
            if (!hasSeedInInventory(cropToPlant.asItem()) || planted >= maxPlantings) {
                break;
            }
        }

        return planted;
    }

    private boolean hasSeedInInventory(net.minecraft.world.item.Item seedItem) {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == seedItem && !stack.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean isAtJobBlock() {
        if (peasant.getJobBlockPos() == null) {
            return false;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        double distance = peasant.distanceToSqr(jobBlock.getX(), jobBlock.getY(), jobBlock.getZ());
        return distance <= 4.0D; // Within 2 blocks of job block
    }

    // Get job block position for navigation
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
                seedCounts.put(stack.getItem(),
                        seedCounts.getOrDefault(stack.getItem(), 0) + stack.getCount());
            }
        }

        if (seedCounts.isEmpty()) return ItemStack.EMPTY;

        net.minecraft.world.item.Item mostAbundant = Collections.max(seedCounts.entrySet(),
                Map.Entry.comparingByValue()).getKey();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == mostAbundant) {
                return stack;
            }
        }

        return ItemStack.EMPTY;
    }

    private void consumeSeedFromInventory(net.minecraft.world.item.Item seedItem) {
        var inventory = peasant.getInventorySystem().getInventory();
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack.getItem() == seedItem) {
                stack.shrink(1);
                if (stack.isEmpty()) {
                    inventory.setItem(i, ItemStack.EMPTY);
                }
                break;
            }
        }
    }

    public FarmState getCurrentFarmState() { return currentFarmState; }
    public void setCurrentFarmState(FarmState state) { this.currentFarmState = state; }

    public boolean hasFarm() { return hasFarm; }

    public boolean hasReturnedToJobBlockAfterFood() { return hasReturnedToJobBlockAfterFood; }
    public void setHasReturnedToJobBlockAfterFood(boolean returned) {
        this.hasReturnedToJobBlockAfterFood = returned;
    }

    public void saveData(CompoundTag compound) {
        compound.putBoolean("HasReturnedToJobBlockAfterFood", hasReturnedToJobBlockAfterFood);
        compound.putBoolean("HasFarm", hasFarm);
        compound.putLong("LastFarmlandConversion", lastFarmlandConversion);
        compound.putString("CurrentFarmState", currentFarmState.name());
    }

    public void loadData(CompoundTag compound) {
        hasReturnedToJobBlockAfterFood = compound.getBoolean("HasReturnedToJobBlockAfterFood");
        hasFarm = compound.getBoolean("HasFarm");
        lastFarmlandConversion = compound.getLong("LastFarmlandConversion");

        try {
            currentFarmState = FarmState.valueOf(compound.getString("CurrentFarmState"));
        } catch (IllegalArgumentException e) {
            currentFarmState = FarmState.NEEDS_FARM_SETUP;
        }
    }
}