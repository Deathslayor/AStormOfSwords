package net.darkflameproduction.agotmod.entity.custom.npc.system.miner;

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

public class MinerSystem {
    private final Peasant_Entity peasant;

    // Simple mine management
    private boolean hasMine = false;
    private long lastMineWork = -1;

    // Work tracking
    private boolean hasReturnedToJobBlockAfterFood = true;
    private MinerState currentMinerState = MinerState.NEEDS_MINE_SETUP;

    public enum MinerState {
        NEEDS_MINE_SETUP,       // Need to set up mine area
        RETURN_TO_JOB_BLOCK,    // Walk to job block each morning
        SETTING_UP_MINE,        // Setting up mine shafts and infrastructure
        MINING,                 // Actively mining ores and materials
        PATROLLING              // All work done, just patrolling
    }

    public MinerSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        // ONLY run mining system logic for miners!
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) {
            return;
        }

        // DEBUG: Log miner state every 100 ticks (5 seconds)
        if (!peasant.level().isClientSide && peasant.tickCount % 100 == 0) {
            // Check what goal is currently running (simplified - just check if navigation is active)
            String currentGoal = "NONE";
            if (peasant.getNavigation().isInProgress()) {
                currentGoal = "NAVIGATION_ACTIVE";
            }

            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: " +
                    "State=" + currentMinerState +
                    ", HasMine=" + hasMine +
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

            // Check if job block was destroyed
            if (!jobBlockState.is(ModBLocks.MINER_BARREL.get())) {
                if (!peasant.level().isClientSide) {
                    System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Job block destroyed! Block at " + jobBlockPos + " is " + jobBlockState.getBlock());
                }
                // Job block destroyed, lose job
                peasant.setJobType(JobSystem.JOB_NONE);
                peasant.setJobBlockPos(null);
                JobSystem.releaseJobBlockReservation(peasant.getUUID());

                // Reset mining state
                hasMine = false;
                currentMinerState = MinerState.NEEDS_MINE_SETUP;
            }
        }
    }

    /**
     * Triggers interact animation - stops movement and plays animation
     */
    private void triggerInteractAnimation() {
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG: MinerSystem calling triggerInteractAnimation");
            peasant.triggerInteractAnimation();
        }
    }

    public boolean hasExcessItems() {
        var inventory = peasant.getInventorySystem().getInventory();
        int pickaxeCount = 0;
        int torchCount = 0;
        int totalItems = 0;

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                totalItems += stack.getCount();

                if (isPickaxe(stack)) {
                    pickaxeCount++;
                } else if (isTorch(stack)) {
                    torchCount += stack.getCount();
                }
            }
        }

        // Calculate what should be kept
        int allowedPickaxes = Math.min(pickaxeCount, 2); // Keep up to 2 pickaxes
        int allowedTorches = Math.min(torchCount, 64); // Keep up to 64 torches

        // Count items in the allowed categories
        int allowedTotalItems = allowedPickaxes + allowedTorches;

        // Has excess if total items exceed what we're allowed to keep by a significant margin
        return totalItems > (allowedTotalItems + 100); // Allow some extra space for mined materials
    }

    private boolean isPickaxe(ItemStack stack) {
        return stack.getItem() instanceof net.minecraft.world.item.PickaxeItem;
    }

    private boolean isTorch(ItemStack stack) {
        return stack.getItem() == net.minecraft.world.item.Items.TORCH;
    }

    // Simple mine setup - just mark that we have a job block
    public boolean setupMine() {
        if (peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Cannot setup mine - no job block position!");
            }
            return false;
        }

        if (!hasMine) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Setting up mine at " + peasant.getJobBlockPos());
            }
            hasMine = true;
            currentMinerState = MinerState.SETTING_UP_MINE; // Skip RETURN_TO_JOB_BLOCK since we're already here

            // Trigger interact animation when setting up mine
            triggerInteractAnimation();
            return true;
        }

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Mine already setup");
        }
        return true;
    }

    // INITIAL mine setup - create basic mining infrastructure
    public int initialMineSetup() {
        if (!hasMine || peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Cannot do initial mine setup - hasMine=" + hasMine + ", jobBlockPos=" + peasant.getJobBlockPos());
            }
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        int setupWork = 0;
        boolean triggeredAnimation = false;

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Starting INITIAL mine setup around " + jobBlock);
        }

        // TODO: Implement mine shaft creation, torch placement, etc.
        // For now, just trigger animation and mark as setup
        if (!triggeredAnimation) {
            triggerInteractAnimation();
            triggeredAnimation = true;
        }

        setupWork = 1; // Placeholder

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Initial mine setup completed " + setupWork + " work units");
        }

        // Always move to mining after initial setup
        currentMinerState = MinerState.MINING;

        return setupWork;
    }

    // DAILY mining work - mine ores and materials
    public int performMining() {
        if (!hasMine || peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Cannot mine - hasMine=" + hasMine + ", jobBlockPos=" + peasant.getJobBlockPos());
            }
            return 0;
        }

        // Check daily limit
        long currentDay = peasant.level().getDayTime() / 24000;
        long lastMiningDay = lastMineWork / 24000;

        if (currentDay <= lastMiningDay && lastMineWork != -1) {
            if (!peasant.level().isClientSide) {
                System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Already mined today (day " + currentDay + ", last mining day " + lastMiningDay + ")");
            }
            // Already mined today
            currentMinerState = MinerState.PATROLLING;
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();
        int mined = 0;
        boolean triggeredAnimation = false;

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Starting DAILY mining around " + jobBlock);
        }

        // TODO: Implement actual mining logic - find ores, mine them, collect materials
        // For now, just trigger animation and add some basic materials to inventory
        if (!triggeredAnimation) {
            triggerInteractAnimation();
            triggeredAnimation = true;
        }

        // Placeholder: add some basic mining results
        addMiningResults();
        mined = 1; // Placeholder

        // Update mining time
        lastMineWork = peasant.level().getDayTime();

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Daily mining completed " + mined + " work units");
        }

        // Move to patrolling if no mining needed
        if (mined == 0) {
            currentMinerState = MinerState.PATROLLING;
        }

        return mined;
    }

    /**
     * Adds some basic mining results to the peasant's inventory (placeholder)
     */
    private void addMiningResults() {
        // TODO: Replace with actual mining logic
        // For now, add some basic materials
        Random random = peasant.getRandom();

        // Add some cobblestone (common)
        if (random.nextFloat() < 0.8f) {
            ItemStack cobblestone = new ItemStack(net.minecraft.world.item.Items.COBBLESTONE,
                    random.nextInt(8) + 1);
            peasant.getInventorySystem().addItem(cobblestone);
        }

        // Add some coal (common)
        if (random.nextFloat() < 0.6f) {
            ItemStack coal = new ItemStack(net.minecraft.world.item.Items.COAL,
                    random.nextInt(4) + 1);
            peasant.getInventorySystem().addItem(coal);
        }

        // Add some iron ore (uncommon)
        if (random.nextFloat() < 0.3f) {
            ItemStack ironOre = new ItemStack(net.minecraft.world.item.Items.IRON_ORE,
                    random.nextInt(3) + 1);
            peasant.getInventorySystem().addItem(ironOre);
        }
    }

    public BlockPos findNextMiningPosition() {
        if (!hasMine || peasant.getJobBlockPos() == null) {
            return null;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();

        // TODO: Implement smart mining position finding
        // For now, return a random position around the job block
        Random random = peasant.getRandom();
        BlockPos miningPos = new BlockPos(
                jobBlock.getX() + random.nextInt(21) - 10, // -10 to +10
                jobBlock.getY() - random.nextInt(10) - 1,  // Go underground
                jobBlock.getZ() + random.nextInt(21) - 10  // -10 to +10
        );

        return miningPos;
    }

    public void mineAtPosition(BlockPos miningPos) {
        // Trigger interact animation for mining
        triggerInteractAnimation();

        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Mining at position " + miningPos);
        }

        // TODO: Implement actual block mining logic
        // For now, just play mining sound and add some materials
        peasant.level().playSound(null, miningPos, SoundEvents.STONE_BREAK,
                SoundSource.BLOCKS, 0.8F, 1.0F);

        addMiningResults();
    }

    /**
     * Performs maintenance work in the mine area
     */
    public void performMaintenanceWork() {
        if (!hasMine || peasant.getJobBlockPos() == null) {
            return;
        }

        // TODO: Implement mine maintenance (torch placement, shaft expansion, etc.)
        if (!peasant.level().isClientSide) {
            System.out.println("DEBUG MINER [" + peasant.getDisplayName().getString() + "]: Performing mine maintenance");
        }
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

    public MinerState getCurrentMinerState() { return currentMinerState; }
    public void setCurrentMinerState(MinerState state) { this.currentMinerState = state; }

    public boolean hasMine() { return hasMine; }

    public boolean hasReturnedToJobBlockAfterFood() { return hasReturnedToJobBlockAfterFood; }
    public void setHasReturnedToJobBlockAfterFood(boolean returned) {
        this.hasReturnedToJobBlockAfterFood = returned;
    }

    public void saveData(CompoundTag compound) {
        compound.putBoolean("HasReturnedToJobBlockAfterFood", hasReturnedToJobBlockAfterFood);
        compound.putBoolean("HasMine", hasMine);
        compound.putLong("LastMineWork", lastMineWork);
        compound.putString("CurrentMinerState", currentMinerState.name());
    }

    public void loadData(CompoundTag compound) {
        hasReturnedToJobBlockAfterFood = compound.getBoolean("HasReturnedToJobBlockAfterFood");
        hasMine = compound.getBoolean("HasMine");
        lastMineWork = compound.getLong("LastMineWork");

        try {
            currentMinerState = MinerState.valueOf(compound.getString("CurrentMinerState"));
        } catch (IllegalArgumentException e) {
            currentMinerState = MinerState.NEEDS_MINE_SETUP;
        }
    }
}