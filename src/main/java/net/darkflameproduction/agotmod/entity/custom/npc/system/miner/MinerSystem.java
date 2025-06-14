package net.darkflameproduction.agotmod.entity.custom.npc.system.miner;

import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;

import java.util.*;
import java.util.List;

public class MinerSystem {
    private final Peasant_Entity peasant;

    // Work tracking
    private MinerState currentMinerState = MinerState.NEEDS_MINE_SETUP;


    private int currentTunnelIndex = 0;           // Which tunnel (0-39, cycling)
    private int currentTunnelProgress = 0;        // How far we've mined in current tunnel
    private BlockPos selectedTunnelEntrance = null;  // Today's tunnel entrance
    private Direction miningDirection = null;     // Direction to mine from entrance
    private BlockPos currentMiningPosition = null;   // Exact block we're mining
    private int blocksMineToday = 0;             // Daily mining counter
    private int torchCounter = 0;                // For torch placement every 8 blocks

    // Constants
    private static final int MAX_TUNNEL_LENGTH = 128;
    private static final int TORCH_INTERVAL = 8;
    private static final int MAX_BLOCKS_PER_DAY = 512;

    // Allowed mining items
    private static final Set<String> ALLOWED_ITEMS = new HashSet<>();

    static {
        // Vanilla ores and materials
        ALLOWED_ITEMS.add("minecraft:coal");
        ALLOWED_ITEMS.add("minecraft:iron_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_iron_ore");
        ALLOWED_ITEMS.add("minecraft:gold_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_gold_ore");
        ALLOWED_ITEMS.add("minecraft:diamond_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_diamond_ore");
        ALLOWED_ITEMS.add("minecraft:emerald_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_emerald_ore");
        ALLOWED_ITEMS.add("minecraft:lapis_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_lapis_ore");
        ALLOWED_ITEMS.add("minecraft:redstone_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_redstone_ore");
        ALLOWED_ITEMS.add("minecraft:copper_ore");
        ALLOWED_ITEMS.add("minecraft:deepslate_copper_ore");

        // Raw materials
        ALLOWED_ITEMS.add("minecraft:raw_iron");
        ALLOWED_ITEMS.add("minecraft:raw_gold");
        ALLOWED_ITEMS.add("minecraft:raw_copper");
        ALLOWED_ITEMS.add("minecraft:gravel");


        // Ingots and gems
        ALLOWED_ITEMS.add("minecraft:iron_ingot");
        ALLOWED_ITEMS.add("minecraft:gold_ingot");
        ALLOWED_ITEMS.add("minecraft:copper_ingot");
        ALLOWED_ITEMS.add("minecraft:diamond");
        ALLOWED_ITEMS.add("minecraft:emerald");
        ALLOWED_ITEMS.add("minecraft:lapis_lazuli");
        ALLOWED_ITEMS.add("minecraft:redstone");


        // Custom mod ores and gems
        ALLOWED_ITEMS.add("agotmod:silver_ore");
        ALLOWED_ITEMS.add("agotmod:raw_silver_block");
        ALLOWED_ITEMS.add("agotmod:deepslate_silver_ore");
        ALLOWED_ITEMS.add("agotmod:silver_block");

        ALLOWED_ITEMS.add("agotmod:tin_ore");
        ALLOWED_ITEMS.add("agotmod:raw_tin_block");
        ALLOWED_ITEMS.add("agotmod:deepslate_tin_ore");
        ALLOWED_ITEMS.add("agotmod:tin_block");
        ALLOWED_ITEMS.add("agotmod:bronze_block");

        // Gemstones and precious blocks
        ALLOWED_ITEMS.add("agotmod:yellow_diamond_block");
        ALLOWED_ITEMS.add("agotmod:transparent_diamond_block");
        ALLOWED_ITEMS.add("agotmod:black_diamond_block");

        ALLOWED_ITEMS.add("agotmod:amber_block");
        ALLOWED_ITEMS.add("agotmod:amber_ore");
        ALLOWED_ITEMS.add("agotmod:amber_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:amethyst_block");
        ALLOWED_ITEMS.add("agotmod:amethyst_ore");
        ALLOWED_ITEMS.add("agotmod:amethyst_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:bloodstone_block");
        ALLOWED_ITEMS.add("agotmod:bloodstone_ore");
        ALLOWED_ITEMS.add("agotmod:bloodstone_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:carnelian_block");
        ALLOWED_ITEMS.add("agotmod:carnelian_ore");
        ALLOWED_ITEMS.add("agotmod:carnelian_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:chalcedony_block");
        ALLOWED_ITEMS.add("agotmod:chalcedony_ore");
        ALLOWED_ITEMS.add("agotmod:chalcedony_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:diamonds_ore");
        ALLOWED_ITEMS.add("agotmod:diamonds_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:garnet_block");
        ALLOWED_ITEMS.add("agotmod:garnet_ore");
        ALLOWED_ITEMS.add("agotmod:garnet_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:jade_block");
        ALLOWED_ITEMS.add("agotmod:jade_ore");
        ALLOWED_ITEMS.add("agotmod:jade_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:jasper_block");
        ALLOWED_ITEMS.add("agotmod:jasper_ore");
        ALLOWED_ITEMS.add("agotmod:jasper_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:malachite_block");
        ALLOWED_ITEMS.add("agotmod:malachite_ore");
        ALLOWED_ITEMS.add("agotmod:malachite_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:moonstone_block");
        ALLOWED_ITEMS.add("agotmod:moonstone_ore");
        ALLOWED_ITEMS.add("agotmod:moonstone_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:onyx_block");
        ALLOWED_ITEMS.add("agotmod:onyx_ore");
        ALLOWED_ITEMS.add("agotmod:onyx_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:opal_block");
        ALLOWED_ITEMS.add("agotmod:opal_ore");
        ALLOWED_ITEMS.add("agotmod:opal_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:ruby_block");
        ALLOWED_ITEMS.add("agotmod:ruby_ore");
        ALLOWED_ITEMS.add("agotmod:ruby_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:sapphire_block");
        ALLOWED_ITEMS.add("agotmod:sapphire_ore");
        ALLOWED_ITEMS.add("agotmod:sapphire_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:tigers_eye_block");
        ALLOWED_ITEMS.add("agotmod:tigers_eye_ore");
        ALLOWED_ITEMS.add("agotmod:tigers_eye_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:topaz_block");
        ALLOWED_ITEMS.add("agotmod:topaz_ore");
        ALLOWED_ITEMS.add("agotmod:topaz_deepslate_ore");

        ALLOWED_ITEMS.add("agotmod:tourmaline_ore");
        ALLOWED_ITEMS.add("agotmod:tourmaline_deepslate_ore");
    }

    public enum MinerState {
        NEEDS_MINE_SETUP,    // Add this back
        RETURN_TO_JOB_BLOCK, // Add this back
        SETTING_UP_MINE,     // Add this back
        SELECTING_TUNNEL,
        WALKING_TO_TUNNEL,
        MINING,
        PATROLLING          // Add this back
    }

    public MinerSystem(Peasant_Entity peasant) {
        this.peasant = peasant;
    }

    public void tick() {
        // ONLY run mining system logic for miners!
        if (!peasant.getJobType().equals(JobSystem.JOB_MINER)) {
            return;
        }

        // Check if job block still exists - if not, lose job
        if (peasant.hasJob() && peasant.getJobBlockPos() != null) {
            BlockPos jobBlockPos = peasant.getJobBlockPos();
            BlockState jobBlockState = peasant.level().getBlockState(jobBlockPos);

            // Check if job block was destroyed
            if (!jobBlockState.is(ModBLocks.MINER_BARREL.get())) {
                // Job block destroyed, lose job
                peasant.setJobType(JobSystem.JOB_NONE);
                peasant.setJobBlockPos(null);
                JobSystem.releaseJobBlockReservation(peasant.getUUID());

                // Reset mining state
                currentMinerState = MinerState.SELECTING_TUNNEL;
                resetDailyMining();
            }
        }

        // Reset daily progress if new day
        checkAndResetDailyProgress();
    }

    private void resetDailyMining() {
        blocksMineToday = 0;
        torchCounter = 0;
        selectedTunnelEntrance = null;
        currentMiningPosition = null;
        miningDirection = null;

        // Reset tunnel progress for the current tunnel (start over each day)
        currentTunnelProgress = 0;  // Add this line

        // Move to tunnel selection state
        if (currentMinerState == MinerState.MINING || currentMinerState == MinerState.WALKING_TO_TUNNEL) {
            currentMinerState = MinerState.SELECTING_TUNNEL;
        }
    }

    // Add this method to MinerSystem
    public BlockPos getCurrentMiningTarget() {
        return currentMiningPosition;  // Same as getCurrentMiningPosition()
    }

    public void selectTodaysTunnel() {
        List<BlockPos> entrances = getAllTunnelEntrances();
        if (entrances.isEmpty()) {
            currentMinerState = MinerState.PATROLLING;
            return;
        }

        // FIXED: Clear any old mining data from the previous system
        clearOldMiningData();

        // Cycle through tunnels
        if (currentTunnelIndex >= entrances.size()) {
            currentTunnelIndex = 0;
        }

        selectedTunnelEntrance = entrances.get(currentTunnelIndex);
        calculateMiningDirection();

        // Start mining 3 blocks inside the tunnel entrance
        BlockPos tunnelStart = selectedTunnelEntrance.relative(miningDirection, 3);
        currentMiningPosition = tunnelStart;

        if (!peasant.level().isClientSide) {
            System.out.println("=== TUNNEL SELECTED [" + peasant.getName().getString() + "] ===");
            System.out.println("  Tunnel #" + currentTunnelIndex + " entrance: " + selectedTunnelEntrance);
            System.out.println("  Mining direction: " + miningDirection);
            System.out.println("  Starting position (3 blocks inside): " + currentMiningPosition);
        }

        currentMinerState = MinerState.WALKING_TO_TUNNEL;
    }

    private void clearOldMiningData() {
        // Clear any persistent data that might contain old mining targets
        peasant.getPersistentData().remove("CurrentMiningTarget");
        peasant.getPersistentData().remove("CurrentHallwayStart");
        peasant.getPersistentData().remove("CurrentHallwayDirection");

        // Stop any current navigation
        peasant.getNavigation().stop();

        if (!peasant.level().isClientSide) {
            System.out.println("  Cleared old mining data for: " + peasant.getName().getString());
        }
    }


    private void calculateMiningDirection() {
        if (selectedTunnelEntrance == null || peasant.getJobBlockPos() == null) {
            return;
        }

        BlockPos center = peasant.getJobBlockPos();
        int deltaX = selectedTunnelEntrance.getX() - center.getX();
        int deltaZ = selectedTunnelEntrance.getZ() - center.getZ();

        // Determine direction based on which wall the entrance is on
        if (Math.abs(deltaZ) > Math.abs(deltaX)) {
            // North or South wall
            if (deltaZ < 0) {
                miningDirection = Direction.NORTH; // Mine further north
            } else {
                miningDirection = Direction.SOUTH; // Mine further south
            }
        } else {
            // East or West wall
            if (deltaX < 0) {
                miningDirection = Direction.WEST; // Mine further west
            } else {
                miningDirection = Direction.EAST; // Mine further east
            }
        }
    }

    private void checkAndResetDailyProgress() {
        // Check if new day started
        long currentDay = peasant.level().getDayTime() / 24000;
        long lastDay = peasant.getPersistentData().getLong("LastMiningDay");

        if (currentDay > lastDay) {
            // New day - advance to next tunnel
            currentTunnelIndex++;  // Add this line

            // Reset daily progress
            resetDailyMining();
            peasant.getPersistentData().putLong("LastMiningDay", currentDay);

            if (!peasant.level().isClientSide) {
                System.out.println("=== NEW MINING DAY [" + peasant.getName().getString() + "] ===");
                System.out.println("  Advanced to tunnel #" + currentTunnelIndex);  // Update message
                System.out.println("  Reset daily progress, will select new tunnel");
            }
        }
    }

    public boolean setupMine() {
        if (peasant.getJobBlockPos() == null) {
            return false;
        }

        if (currentMinerState == MinerState.NEEDS_MINE_SETUP) {
            currentMinerState = MinerState.SETTING_UP_MINE;
            return true;
        }

        return true;
    }

    public boolean isMineSetupComplete() {
        // Mine is setup if we've moved past the setup states
        return currentMinerState != MinerState.NEEDS_MINE_SETUP &&
                currentMinerState != MinerState.SETTING_UP_MINE;
    }

    public int performMineSetup() {
        if (peasant.getJobBlockPos() == null) {
            return 0;
        }

        BlockPos jobBlock = peasant.getJobBlockPos();

        // Build the exact mine structure from the schematic
        buildSchematicMine(jobBlock);

        // Trigger interact animation when setting up mine
        triggerInteractAnimation();

        // FIXED: Move to tunnel selection after setup (not directly to mining)
        currentMinerState = MinerState.SELECTING_TUNNEL;

        if (!peasant.level().isClientSide) {
            System.out.println("=== MINE SETUP COMPLETE [" + peasant.getName().getString() + "] ===");
            System.out.println("  Mine structure built successfully");
            System.out.println("  Transitioning to tunnel selection state");
        }

        return 1; // Return that work was done
    }

    public void performSimpleMining() {
        if (currentMiningPosition == null || miningDirection == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("  ERROR: Missing mining position or direction");
                System.out.println("  Position: " + currentMiningPosition + ", Direction: " + miningDirection);
            }
            return;
        }

        // Check daily mining limit first
        if (blocksMineToday >= MAX_BLOCKS_PER_DAY) {
            if (!peasant.level().isClientSide) {
                System.out.println("  Daily mining limit reached (" + MAX_BLOCKS_PER_DAY + " blocks)");
                System.out.println("  Stopping mining for today");
            }
            return;
        }

        // FIXED: Check shorter tunnel length limit
        if (currentTunnelProgress >= MAX_TUNNEL_LENGTH) {
            if (!peasant.level().isClientSide) {
                System.out.println("  Tunnel #" + currentTunnelIndex + " completed at " + MAX_TUNNEL_LENGTH + " blocks");
                System.out.println("  Will advance to next tunnel tomorrow");
            }
            return;
        }

        if (!peasant.level().isClientSide) {
            System.out.println("=== PERFORMING REMOTE MINING [" + peasant.getName().getString() + "] ===");
            System.out.println("  Position: " + currentMiningPosition);
            System.out.println("  Tunnel #" + currentTunnelIndex + " - Progress: " + currentTunnelProgress + "/" + MAX_TUNNEL_LENGTH);
            System.out.println("  Daily progress: " + blocksMineToday + "/" + MAX_BLOCKS_PER_DAY + " blocks");
            System.out.println("  Mining direction: " + miningDirection);
            System.out.println("  Miner location: " + peasant.blockPosition());

            // Log warnings when approaching limits
            if (currentTunnelProgress >= MAX_TUNNEL_LENGTH - 5) {
                System.out.println("  WARNING: Approaching tunnel length limit (" + (MAX_TUNNEL_LENGTH - currentTunnelProgress) + " blocks remaining)");
            }
            if (blocksMineToday >= MAX_BLOCKS_PER_DAY - 5) {
                System.out.println("  WARNING: Approaching daily mining limit (" + (MAX_BLOCKS_PER_DAY - blocksMineToday) + " blocks remaining)");
            }
        }

        // Mine the 2-high hallway at current position
        mineHallwayBlocks(currentMiningPosition);

        // FIXED: Mine valuable ores in large 15x15x5 area around current position
        mineOresInLargeArea(currentMiningPosition);

        // Place torch if needed (every 8 blocks)
        if (torchCounter % TORCH_INTERVAL == 0) {
            placeTorch(currentMiningPosition);
            if (!peasant.level().isClientSide) {
                System.out.println("  Placed torch at position " + currentMiningPosition);
            }
        }

        // Trigger mining animation (visual only)
        peasant.triggerInteractAnimation();

        // Update all progress counters
        currentTunnelProgress++;
        blocksMineToday++;
        torchCounter++;

        // Calculate next mining position (advance one block in mining direction)
        BlockPos nextPosition = currentMiningPosition.relative(miningDirection);
        currentMiningPosition = nextPosition;

        if (!peasant.level().isClientSide) {
            System.out.println("  Remote mining complete at this position");
            System.out.println("  Updated progress - Tunnel: " + currentTunnelProgress + ", Daily: " + blocksMineToday);
            System.out.println("  Next mining position: " + currentMiningPosition);
        }
    }

    private void mineOresInLargeArea(BlockPos center) {
        if (!peasant.level().isClientSide) {
            System.out.println("    Scanning large 15x15x10 area for ores around " + center);
        }

        int oresFound = 0;
        int oresMined = 0;

        // FIXED: Exact coverage - 4 blocks in each direction from hallway
        for (int x = -7; x <= 7; x++) {     // 15 blocks wide
            for (int z = -7; z <= 7; z++) { // 15 blocks deep
                for (int y = -4; y <= 5; y++) { // 10 blocks high: 4 below hallway, hallway (2 blocks), 4 above hallway
                    BlockPos checkPos = center.offset(x, y, z);
                    BlockState blockState = peasant.level().getBlockState(checkPos);

                    // Skip the center hallway blocks (they're handled by mineHallwayBlocks)
                    if (x == 0 && z == 0 && (y == 0 || y == 1)) {
                        continue;
                    }

                    if (isTrueOre(blockState)) {
                        oresFound++;
                        mineBlock(checkPos);
                        oresMined++;

                        if (!peasant.level().isClientSide) {
                            System.out.println("      Remote mined ore: " + getBlockRegistryName(blockState) +
                                    " at " + checkPos + " (offset: x" + x + ",y" + y + ",z" + z + ")");
                        }
                    }
                }
            }
        }

        if (!peasant.level().isClientSide && oresFound > 0) {
            System.out.println("    Large area ore scan complete: " + oresMined + "/" + oresFound + " ores mined");
            System.out.println("    Coverage: 15x15x10 (" + (15*15*10) + " blocks checked)");
            System.out.println("    Y levels: " + (center.getY() - 4) + " to " + (center.getY() + 5) + " (10 blocks high)");
        }
    }


    private void mineHallwayBlocks(BlockPos pos) {
        if (!peasant.level().isClientSide) {
            System.out.println("    Mining 1x2 hallway at " + pos);
        }

        // Mine bottom block
        mineBlock(pos);
        // Mine top block
        mineBlock(pos.above());
        // Ensure floor below
        ensureFloor(pos.below());
    }


    private void mineBlock(BlockPos pos) {
        BlockState blockState = peasant.level().getBlockState(pos);

        if (blockState.isAir()) {
            return;
        }

        // FIXED: No distance check - mine from anywhere (remote mining)
        List<ItemStack> drops = getBlockDrops(blockState, pos);
        peasant.level().setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

        if (!peasant.level().isClientSide) {
            System.out.println("      Remote mined: " + getBlockRegistryName(blockState) + " at " + pos);
        }

        // Add drops to inventory
        for (ItemStack drop : drops) {
            if (!drop.isEmpty()) {
                boolean added = peasant.getInventorySystem().addItem(drop);
                if (!added && peasant.level() instanceof ServerLevel serverLevel) {
                    // Drop on ground if inventory full
                    peasant.spawnAtLocation(serverLevel, drop);
                }
            }
        }
    }


    private void ensureFloor(BlockPos pos) {
        if (peasant.level().getBlockState(pos).isAir()) {
            peasant.level().setBlock(pos, Blocks.COBBLESTONE.defaultBlockState(), 3);
            if (!peasant.level().isClientSide) {
                System.out.println("      Placed floor block at " + pos);
            }
        }
    }

    private void placeTorch(BlockPos pos) {
        BlockPos torchPos = pos.above(2); // Place torch on ceiling
        if (peasant.level().getBlockState(torchPos).isAir()) {
            peasant.level().setBlock(torchPos, Blocks.TORCH.defaultBlockState(), 3);
        }
    }



    private void buildSchematicMine(BlockPos center) {
        // First remove nearby lava and water
        removeLavaAndWater(center);
        cleanMineArea(center);  // Renamed from removeLavaAndWater()

        // Build the exact structure from the schematic
        buildExactSchematicStructure(center);
    }

    private void removeLavaAndWater(BlockPos center) {
        // Remove lava and water within 8 blocks outside the 9x9 mine area
        // Mine area is 9x9 (from -4 to +4), so we check from -12 to +12 (8 blocks outside)
        // And check the full depth of the mine plus some extra

        for (int x = -12; x <= 12; x++) {
            for (int z = -12; z <= 12; z++) {
                for (int y = 5; y >= -40; y--) { // Check above and below mine area
                    BlockPos checkPos = new BlockPos(
                            center.getX() + x,
                            center.getY() + y,
                            center.getZ() + z
                    );

                    BlockState blockState = peasant.level().getBlockState(checkPos);

                    // Remove lava by replacing with air
                    if (blockState.is(Blocks.LAVA)) {
                        peasant.level().setBlock(checkPos, Blocks.AIR.defaultBlockState(), 3);
                    }
                    // Remove water by replacing with air
                    else if (blockState.is(Blocks.WATER)) {
                        peasant.level().setBlock(checkPos, Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
    }

    private void cleanMineArea(BlockPos center) {
        // Remove lava and water within 8 blocks outside the 9x9 mine area
        // AND convert gravel and sand to stone for structural stability
        // Mine area is 9x9 (from -4 to +4), so we check from -12 to +12 (8 blocks outside)
        // And check the full depth of the mine plus some extra

        int gravelConverted = 0;
        int sandConverted = 0;
        int lavaRemoved = 0;
        int waterRemoved = 0;

        for (int x = -12; x <= 12; x++) {
            for (int z = -12; z <= 12; z++) {
                for (int y = 5; y >= -40; y--) { // Check above and below mine area
                    BlockPos checkPos = new BlockPos(
                            center.getX() + x,
                            center.getY() + y,
                            center.getZ() + z
                    );

                    BlockState blockState = peasant.level().getBlockState(checkPos);

                    // Remove lava by replacing with air
                    if (blockState.is(Blocks.LAVA)) {
                        peasant.level().setBlock(checkPos, Blocks.AIR.defaultBlockState(), 3);
                        lavaRemoved++;
                    }
                    // Remove water by replacing with air
                    else if (blockState.is(Blocks.WATER)) {
                        peasant.level().setBlock(checkPos, Blocks.AIR.defaultBlockState(), 3);
                        waterRemoved++;
                    }
                    // Convert gravel to stone for structural stability
                    else if (blockState.is(Blocks.GRAVEL)) {
                        peasant.level().setBlock(checkPos, Blocks.STONE.defaultBlockState(), 3);
                        gravelConverted++;
                    }
                    // NEW: Convert sand to stone for structural stability
                    else if (blockState.is(Blocks.SAND) || blockState.is(Blocks.RED_SAND)) {
                        peasant.level().setBlock(checkPos, Blocks.STONE.defaultBlockState(), 3);
                        sandConverted++;
                    }
                }
            }
        }

        // Log the cleanup results
        if (!peasant.level().isClientSide && (lavaRemoved > 0 || waterRemoved > 0 || gravelConverted > 0 || sandConverted > 0)) {
            System.out.println("=== MINE AREA CLEANUP COMPLETE [" + peasant.getName().getString() + "] ===");
            System.out.println("  Lava blocks removed: " + lavaRemoved);
            System.out.println("  Water blocks removed: " + waterRemoved);
            System.out.println("  Gravel converted to stone: " + gravelConverted);
            System.out.println("  Sand converted to stone: " + sandConverted);
            System.out.println("  Total blocks processed: " + (lavaRemoved + waterRemoved + gravelConverted + sandConverted));
        }
    }

    private int getClearRadiusForLevel(int schematicY) {
        // Different clear radius based on the schematic pattern
        // Upper levels have larger clear areas, lower levels more focused
        if (schematicY >= 30) return 3; // Near surface, wider area
        if (schematicY >= 20) return 2; // Middle depths
        if (schematicY >= 10) return 2; // Lower middle
        return 1; // Deep levels, narrow shafts
    }

    private boolean shouldHaveCobblestone(int x, int y, int z) {
        // Check if this position should have cobblestone based on platform locations
        // Platform positions from the schematic
        if (y == 4) return (x == 0 || x == 1) && (z == 0 || z == 1);
        if (y == 9) return (x == 7 || x == 8) && (z == 0 || z == 1);
        if (y == 14) return (x == 7 || x == 8) && (z == 7 || z == 8);
        if (y == 19) return (x == 0 || x == 1) && (z == 7 || z == 8);
        if (y == 24) return (x == 0 || x == 1) && (z == 0 || z == 1);
        if (y == 29) return (x == 7 || x == 8) && (z == 0 || z == 1);
        return false;
    }

    private boolean shouldHaveStairs(int x, int y, int z) {
        // Check if this position should have stairs based on the schematic
        // This is a simplified check - you could make it more precise

        // North stairs area (roughly)
        if (y <= 4 && (x == 0 || x == 1) && z >= 6) return true;
        if ((y >= 20 && y <= 24) && (x == 0 || x == 1) && z >= 5) return true;

        // East stairs area
        if ((y >= 5 && y <= 9) && x >= 2 && x <= 6 && (z == 0 || z == 1)) return true;
        if ((y >= 25 && y <= 29) && x >= 2 && x <= 6 && (z == 0 || z == 1)) return true;

        // South stairs area
        if ((y >= 10 && y <= 14) && (x == 7 || x == 8) && z >= 2 && z <= 6) return true;
        if ((y >= 30 && y <= 34) && (x == 7 || x == 8) && z >= 2 && z <= 6) return true;

        // West stairs area
        if ((y >= 15 && y <= 19) && x >= 2 && x <= 6 && (z == 7 || z == 8)) return true;

        return false;
    }

    private void buildExactSchematicStructure(BlockPos center) {
        buildCobblestoneBlocks(center);
        buildStairBlocks(center);
        buildBottomPlatform(center);

        // NEW: Pre-clear tunnel starts to avoid positioning conflicts
        int platformY = center.getY() - 36;
        preClearTunnelStarts(center, platformY);
    }

    private void preClearTunnelStarts(BlockPos center, int platformY) {
        List<BlockPos> entrances = getAllTunnelEntrances();

        for (BlockPos entrance : entrances) {
            // Use the same logic as calculateMiningDirection() but inline
            int deltaX = entrance.getX() - center.getX();
            int deltaZ = entrance.getZ() - center.getZ();

            Direction direction;
            if (Math.abs(deltaZ) > Math.abs(deltaX)) {
                // North or South wall
                if (deltaZ < 0) {
                    direction = Direction.NORTH;
                } else {
                    direction = Direction.SOUTH;
                }
            } else {
                // East or West wall
                if (deltaX < 0) {
                    direction = Direction.WEST;
                } else {
                    direction = Direction.EAST;
                }
            }

            // Clear first 5 blocks of each tunnel (2 blocks high)
            for (int i = 1; i <= 5; i++) {
                BlockPos clearPos = entrance.relative(direction, i);
                peasant.level().setBlock(clearPos, Blocks.AIR.defaultBlockState(), 3);
                peasant.level().setBlock(clearPos.above(), Blocks.AIR.defaultBlockState(), 3);
            }

            if (!peasant.level().isClientSide) {
                System.out.println("  Pre-cleared tunnel start: " + entrance + " direction: " + direction);
            }
        }
    }

    private void clearAboveBlock(BlockPos blockPos) {
        // Clear 4 blocks above the given position
        for (int i = 1; i <= 4; i++) {
            BlockPos clearPos = blockPos.above(i);
            peasant.level().setBlock(clearPos, Blocks.AIR.defaultBlockState(), 3);
        }
    }

    private void clearAboveBlockSelective(BlockPos blockPos) {
        // Clear 4 blocks above the given position, but skip cobblestone and cobblestone stairs
        for (int i = 1; i <= 4; i++) {
            BlockPos clearPos = blockPos.above(i);
            BlockState existingState = peasant.level().getBlockState(clearPos);

            // Don't clear cobblestone or cobblestone stairs
            if (!existingState.is(Blocks.COBBLESTONE) && !existingState.is(Blocks.COBBLESTONE_STAIRS)) {
                peasant.level().setBlock(clearPos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    private void placeCobblestoneBelow(BlockPos blockPos, int depth) {
        // Place cobblestone blocks below the given position
        for (int i = 1; i <= depth; i++) {
            BlockPos belowPos = blockPos.below(i);
            peasant.level().setBlock(belowPos, Blocks.COBBLESTONE.defaultBlockState(), 3);
        }
    }

    private void buildCobblestoneBlocks(BlockPos center) {
        // All cobblestone blocks from the schematic (state 1)
        int[][] cobblestonePositions = {
                // Platform at depth -31 (Y=4 in schematic)
                {0, 4, 0}, {0, 4, 1}, {1, 4, 0}, {1, 4, 1},

                // Platform at depth -26 (Y=9 in schematic)
                {7, 9, 0}, {7, 9, 1}, {8, 9, 0}, {8, 9, 1},

                // Platform at depth -21 (Y=14 in schematic)
                {7, 14, 7}, {7, 14, 8}, {8, 14, 7}, {8, 14, 8},

                // Platform at depth -16 (Y=19 in schematic)
                {0, 19, 7}, {0, 19, 8}, {1, 19, 7}, {1, 19, 8},

                // Platform at depth -11 (Y=24 in schematic)
                {0, 24, 0}, {0, 24, 1}, {1, 24, 0}, {1, 24, 1},

                // Platform at depth -6 (Y=29 in schematic)
                {7, 29, 0}, {7, 29, 1}, {8, 29, 0}, {8, 29, 1}
        };

        for (int[] pos : cobblestonePositions) {
            BlockPos worldPos = new BlockPos(
                    center.getX() + (pos[0] - 4),  // Convert schematic X to world X
                    center.getY() + (pos[1] - 35), // Convert schematic Y to world Y
                    center.getZ() + (pos[2] - 4)   // Convert schematic Z to world Z
            );

            // Place the cobblestone block
            peasant.level().setBlock(worldPos, Blocks.COBBLESTONE.defaultBlockState(), 3);

            // Clear 3 blocks above it
            clearAboveBlock(worldPos);
        }
    }

    private void buildStairBlocks(BlockPos center) {
        // Stair blocks from the schematic with their exact positions and facings
        Object[][] stairPositions = {
// North-facing stairs (state 8) - sorted by Y level
// North-facing stairs (state 8) - sorted by Z level, Y levels grouped
                {new int[]{0, 4, 2}, Direction.NORTH},
                {new int[]{1, 4, 2}, Direction.NORTH},
                {new int[]{0, 24, 2}, Direction.NORTH},
                {new int[]{1, 24, 2}, Direction.NORTH},
                {new int[]{0, 3, 3}, Direction.NORTH},
                {new int[]{1, 3, 3}, Direction.NORTH},
                {new int[]{0, 23, 3}, Direction.NORTH},
                {new int[]{1, 23, 3}, Direction.NORTH},
                {new int[]{0, 2, 4}, Direction.NORTH},
                {new int[]{1, 2, 4}, Direction.NORTH},
                {new int[]{0, 22, 4}, Direction.NORTH},
                {new int[]{1, 22, 4}, Direction.NORTH},
                {new int[]{0, 1, 5}, Direction.NORTH},
                {new int[]{1, 1, 5}, Direction.NORTH},
                {new int[]{0, 21, 5}, Direction.NORTH},
                {new int[]{1, 21, 5}, Direction.NORTH},
                {new int[]{0, 0, 6}, Direction.NORTH},
                {new int[]{1, 0, 6}, Direction.NORTH},
                {new int[]{0, 20, 6}, Direction.NORTH},
                {new int[]{1, 20, 6}, Direction.NORTH},

                // East-facing stairs (state 10)
                {new int[]{2, 5, 0}, Direction.EAST},
                {new int[]{2, 5, 1}, Direction.EAST},
                {new int[]{3, 6, 0}, Direction.EAST},
                {new int[]{3, 6, 1}, Direction.EAST},
                {new int[]{4, 7, 0}, Direction.EAST},
                {new int[]{4, 7, 1}, Direction.EAST},
                {new int[]{5, 8, 0}, Direction.EAST},
                {new int[]{5, 8, 1}, Direction.EAST},
                {new int[]{6, 9, 0}, Direction.EAST},
                {new int[]{6, 9, 1}, Direction.EAST},
                {new int[]{2, 25, 0}, Direction.EAST},
                {new int[]{2, 25, 1}, Direction.EAST},
                {new int[]{3, 26, 0}, Direction.EAST},
                {new int[]{3, 26, 1}, Direction.EAST},
                {new int[]{4, 27, 0}, Direction.EAST},
                {new int[]{4, 27, 1}, Direction.EAST},
                {new int[]{5, 28, 0}, Direction.EAST},
                {new int[]{5, 28, 1}, Direction.EAST},
                {new int[]{6, 29, 0}, Direction.EAST},
                {new int[]{6, 29, 1}, Direction.EAST},

                // South-facing stairs (state 11)
                {new int[]{7, 10, 2}, Direction.SOUTH},
                {new int[]{8, 10, 2}, Direction.SOUTH},
                {new int[]{7, 11, 3}, Direction.SOUTH},
                {new int[]{8, 11, 3}, Direction.SOUTH},
                {new int[]{7, 12, 4}, Direction.SOUTH},
                {new int[]{8, 12, 4}, Direction.SOUTH},
                {new int[]{7, 13, 5}, Direction.SOUTH},
                {new int[]{8, 13, 5}, Direction.SOUTH},
                {new int[]{7, 14, 6}, Direction.SOUTH},
                {new int[]{8, 14, 6}, Direction.SOUTH},
                {new int[]{7, 30, 2}, Direction.SOUTH},
                {new int[]{8, 30, 2}, Direction.SOUTH},
                {new int[]{7, 31, 3}, Direction.SOUTH},
                {new int[]{8, 31, 3}, Direction.SOUTH},
                {new int[]{7, 32, 4}, Direction.SOUTH},
                {new int[]{8, 32, 4}, Direction.SOUTH},
                {new int[]{7, 33, 5}, Direction.SOUTH},
                {new int[]{8, 33, 5}, Direction.SOUTH},
                {new int[]{7, 34, 6}, Direction.SOUTH},
                {new int[]{8, 34, 6}, Direction.SOUTH},

                // West-facing stairs (state 12)
                {new int[]{6, 15, 7}, Direction.WEST},
                {new int[]{6, 15, 8}, Direction.WEST},
                {new int[]{5, 16, 7}, Direction.WEST},
                {new int[]{5, 16, 8}, Direction.WEST},
                {new int[]{4, 17, 7}, Direction.WEST},
                {new int[]{4, 17, 8}, Direction.WEST},
                {new int[]{3, 18, 7}, Direction.WEST},
                {new int[]{3, 18, 8}, Direction.WEST},
                {new int[]{2, 19, 7}, Direction.WEST},
                {new int[]{2, 19, 8}, Direction.WEST}
        };

        for (Object[] stairData : stairPositions) {
            int[] pos = (int[]) stairData[0];
            Direction facing = (Direction) stairData[1];

            BlockPos worldPos = new BlockPos(
                    center.getX() + (pos[0] - 4),  // Convert schematic X to world X
                    center.getY() + (pos[1] - 35), // Convert schematic Y to world Y
                    center.getZ() + (pos[2] - 4)   // Convert schematic Z to world Z
            );

            BlockState stairState = Blocks.COBBLESTONE_STAIRS.defaultBlockState()
                    .setValue(StairBlock.FACING, facing)
                    .setValue(StairBlock.HALF, Half.BOTTOM)
                    .setValue(StairBlock.SHAPE, StairsShape.STRAIGHT);

            // Place the stair block
            peasant.level().setBlock(worldPos, stairState, 3);

            // Clear 3 blocks above it
            clearAboveBlock(worldPos);

            // Place 1 cobblestone block below the stair
            placeCobblestoneBelow(worldPos, 1);
        }
    }

    /**
     * Builds a 19x19 cobblestone platform at schematic Y = -1
     * Using the same coordinate conversion as buildCobblestoneBlocks
     */
    private void buildBottomPlatform(BlockPos center) {
        // Platform Y level: schematic Y = -1, world Y = center.getY() + (-1 - 35) = center.getY() - 36
        int platformY = center.getY() + (-1 - 35);

        // Build 19x19 platform centered on job block's X and Z coordinates
        for (int x = -9; x <= 9; x++) { // 19 blocks wide (-9 to +9 inclusive)
            for (int z = -9; z <= 9; z++) { // 19 blocks deep (-9 to +9 inclusive)
                BlockPos platformPos = new BlockPos(
                        center.getX() + x,
                        platformY,
                        center.getZ() + z
                );

                // Place cobblestone block
                peasant.level().setBlock(platformPos, Blocks.COBBLESTONE.defaultBlockState(), 3);

                // Clear 4 blocks above it, but preserve existing cobblestone and cobblestone stairs
                clearAboveBlockSelective(platformPos);
            }
        }

        // Add mining tunnel entrances in the walls
        createMiningTunnelEntrances(center, platformY);
    }

    /**
     * Creates mining tunnel entrances in the walls around the platform
     * These are 2x1 holes (2 tall, 1 wide) every other block with torches above
     */
    private void createMiningTunnelEntrances(BlockPos center, int platformY) {
        // Create tunnel entrances on all four walls
        // Each tunnel is 1 block wide, 2 blocks high, every other block, with torch above

        // North wall tunnels (z = -10)
        for (int x = -9; x <= 9; x += 2) { // Every other block
            createSingleTunnel(center, platformY, x, -10);
        }

        // South wall tunnels (z = 10)
        for (int x = -9; x <= 9; x += 2) { // Every other block
            createSingleTunnel(center, platformY, x, 10);
        }

        // East wall tunnels (x = 10)
        for (int z = -9; z <= 9; z += 2) { // Every other block
            createSingleTunnel(center, platformY, 10, z);
        }

        // West wall tunnels (x = -10)
        for (int z = -9; z <= 9; z += 2) { // Every other block
            createSingleTunnel(center, platformY, -10, z);
        }
    }

    /**
     * Creates a single 2x1 tunnel entrance (2 tall, 1 wide) with torch one block before the hole
     */
    private void createSingleTunnel(BlockPos center, int platformY, int x, int z) {
        // Create 2-tall, 1-wide tunnel entrance
        for (int dy = 1; dy <= 2; dy++) { // 2 blocks high, starting 1 block above platform
            BlockPos tunnelPos = new BlockPos(
                    center.getX() + x,
                    platformY + dy,
                    center.getZ() + z
            );
            // Create the hole
            peasant.level().setBlock(tunnelPos, Blocks.AIR.defaultBlockState(), 3);
        }

        // Place torch one block before the tunnel entrance (toward the center) and attach to wall
        int torchX = x;
        int torchZ = z;
        Direction torchFacing = Direction.NORTH; // Default facing

        // Determine torch position and facing direction based on the wall
        if (x == -10) { // West wall - torch goes east (toward center), faces east (toward tunnel)
            torchX = x + 1;
            torchFacing = Direction.EAST;
        } else if (x == 10) { // East wall - torch goes west (toward center), faces west (toward tunnel)
            torchX = x - 1;
            torchFacing = Direction.WEST;
        } else if (z == -10) { // North wall - torch goes south (toward center), faces south (toward tunnel)
            torchZ = z + 1;
            torchFacing = Direction.SOUTH;
        } else if (z == 10) { // South wall - torch goes north (toward center), faces north (toward tunnel)
            torchZ = z - 1;
            torchFacing = Direction.NORTH;
        }

        BlockPos torchPos = new BlockPos(
                center.getX() + torchX,
                platformY + 3,     // Back to original height (one block above tunnel)
                center.getZ() + torchZ
        );

        // Place wall torch with proper facing
        BlockState wallTorch = Blocks.WALL_TORCH.defaultBlockState()
                .setValue(net.minecraft.world.level.block.WallTorchBlock.FACING, torchFacing);
        peasant.level().setBlock(torchPos, wallTorch, 3);
    }







    private List<BlockPos> getAllTunnelEntrances() {
        if (peasant.getJobBlockPos() == null) {
            return new ArrayList<>();
        }

        BlockPos center = peasant.getJobBlockPos();
        int platformY = center.getY() - 36; // Platform level
        int entranceY = platformY + 1; // 1 block above platform

        List<BlockPos> entrances = new ArrayList<>();

        // North wall entrances
        for (int x = -9; x <= 9; x += 2) {
            entrances.add(new BlockPos(center.getX() + x, entranceY, center.getZ() - 10));
        }
        // South wall entrances
        for (int x = -9; x <= 9; x += 2) {
            entrances.add(new BlockPos(center.getX() + x, entranceY, center.getZ() + 10));
        }
        // East wall entrances
        for (int z = -9; z <= 9; z += 2) {
            entrances.add(new BlockPos(center.getX() + 10, entranceY, center.getZ() + z));
        }
        // West wall entrances
        for (int z = -9; z <= 9; z += 2) {
            entrances.add(new BlockPos(center.getX() - 10, entranceY, center.getZ() + z));
        }

        return entrances;
    }



    private boolean isTrueOre(BlockState blockState) {
        String blockId = getBlockRegistryName(blockState);

        // Explicitly exclude stone types that should NOT be mined as ores
        Set<String> STONE_TYPES = Set.of(
                "minecraft:stone", "minecraft:cobblestone", "minecraft:deepslate",
                "minecraft:cobbled_deepslate", "minecraft:granite", "minecraft:diorite",
                "minecraft:andesite", "minecraft:tuff", "minecraft:calcite",
                "minecraft:dirt", "minecraft:grass_block",
                "minecraft:sand", "minecraft:sandstone", "minecraft:red_sandstone"
        );

        // If it's a stone type, it's NOT an ore
        if (STONE_TYPES.contains(blockId)) {
            return false;
        }

        // Define ONLY actual valuable ores and materials
        Set<String> TRUE_ORES = Set.of(
                // Vanilla ores
                "minecraft:coal_ore", "minecraft:deepslate_coal_ore", "minecraft:gravel",
                "minecraft:iron_ore", "minecraft:deepslate_iron_ore",
                "minecraft:gold_ore", "minecraft:deepslate_gold_ore",
                "minecraft:diamond_ore", "minecraft:deepslate_diamond_ore",
                "minecraft:emerald_ore", "minecraft:deepslate_emerald_ore",
                "minecraft:lapis_ore", "minecraft:deepslate_lapis_ore",
                "minecraft:redstone_ore", "minecraft:deepslate_redstone_ore",
                "minecraft:copper_ore", "minecraft:deepslate_copper_ore "
        );

        // Check vanilla ores first
        if (TRUE_ORES.contains(blockId)) {
            return true;
        }

        // Check custom mod ores (anything with agotmod namespace that contains "ore")
        if (blockId.startsWith("agotmod:") && blockId.contains("_ore")) {
            return true;
        }

        // Check for valuable mod blocks (gems, precious metals)
        if (blockId.startsWith("agotmod:")) {
            return blockId.contains("silver") || blockId.contains("tin") ||
                    blockId.contains("amber") || blockId.contains("ruby") ||
                    blockId.contains("sapphire") || blockId.contains("garnet") ||
                    blockId.contains("jade") || blockId.contains("topaz") ||
                    blockId.contains("emerald") || blockId.contains("diamond") ||
                    (blockId.contains("_block") && !blockId.contains("stone"));
        }

        return false;
    }





    /**
     * Gets the drops that a block would give when mined
     */
    private List<ItemStack> getBlockDrops(BlockState blockState, BlockPos blockPos) {
        List<ItemStack> drops = new ArrayList<>();
        try {
            List<ItemStack> blockDrops = Block.getDrops(blockState, (ServerLevel) peasant.level(), blockPos, null);
            drops.addAll(blockDrops);
        } catch (Exception e) {
            ItemStack basicDrop = new ItemStack(blockState.getBlock().asItem(), 1);
            if (!basicDrop.isEmpty()) {
                drops.add(basicDrop);
            }
        }
        if (drops.isEmpty()) {
            ItemStack basicDrop = new ItemStack(blockState.getBlock().asItem(), 1);
            if (!basicDrop.isEmpty()) {
                drops.add(basicDrop);
            }
        }
        return drops;
    }

    /**
     * Checks if a block is valuable and should be mined
     */
    private boolean isValuableBlock(BlockState blockState) {
        String blockId = getBlockRegistryName(blockState);

        // Check against the allowed items list
        if (ALLOWED_ITEMS.contains(blockId)) {
            return true;
        }

        // Fallback ore detection
        return isOreBlock(blockState);
    }

    /**
     * Gets the registry name of a block
     */
    private String getBlockRegistryName(BlockState blockState) {
        try {
            var registryName = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(blockState.getBlock());
            if (registryName != null) {
                return registryName.toString();
            }
        } catch (Exception e) {
            // Fallback
        }
        return blockState.getBlock().getDescriptionId();
    }

    /**
     * Checks if a block is an ore that should be mined
     */
    private boolean isOreBlock(BlockState blockState) {
        String blockName = blockState.getBlock().getDescriptionId();
        return blockName.contains("_ore") ||
                blockName.contains("_block") &&
                        (blockName.contains("diamond") || blockName.contains("emerald") ||
                                blockName.contains("gold") || blockName.contains("iron") ||
                                blockName.contains("silver") || blockName.contains("tin") ||
                                blockName.contains("amber") || blockName.contains("ruby") ||
                                blockName.contains("sapphire") || blockName.contains("garnet"));
    }

    /**
     * Triggers interact animation - stops movement and plays animation
     */
    private void triggerInteractAnimation() {
        if (!peasant.level().isClientSide) {
            peasant.triggerInteractAnimation();
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

    public BlockPos getJobBlockPosition() {
        return peasant.getJobBlockPos();
    }



    public MinerState getCurrentMinerState() {
        return currentMinerState;
    }

    public void setCurrentMinerState(MinerState state) {
        this.currentMinerState = state;
    }




    public void saveData(CompoundTag compound) {
        compound.putString("CurrentMinerState", currentMinerState.name());
        compound.putInt("CurrentTunnelIndex", currentTunnelIndex);
        compound.putInt("CurrentTunnelProgress", currentTunnelProgress);
        compound.putInt("BlocksMinedToday", blocksMineToday);
        compound.putInt("TorchCounter", torchCounter);

        if (selectedTunnelEntrance != null) {
            compound.putIntArray("SelectedTunnelEntrance", new int[]{
                    selectedTunnelEntrance.getX(), selectedTunnelEntrance.getY(), selectedTunnelEntrance.getZ()
            });
        }

        if (currentMiningPosition != null) {
            compound.putIntArray("CurrentMiningPosition", new int[]{
                    currentMiningPosition.getX(), currentMiningPosition.getY(), currentMiningPosition.getZ()
            });
        }

        if (miningDirection != null) {
            compound.putString("MiningDirection", miningDirection.name());
        }
    }

    public BlockPos getSelectedTunnelEntrance() {
        return selectedTunnelEntrance;
    }

    public BlockPos getCurrentMiningPosition() {
        return currentMiningPosition;
    }

    public boolean hasReachedTunnelEntrance() {
        if (selectedTunnelEntrance == null) {
            return false;
        }

        double distance = peasant.distanceToSqr(
                selectedTunnelEntrance.getX() + 0.5,
                selectedTunnelEntrance.getY(),
                selectedTunnelEntrance.getZ() + 0.5
        );

        return distance <= 4.0D; // Within 2 blocks
    }

    public boolean canMineAtCurrentPosition() {
        if (currentMiningPosition == null) {
            return false;
        }

        double distance = peasant.distanceToSqr(
                currentMiningPosition.getX() + 0.5,
                currentMiningPosition.getY(),
                currentMiningPosition.getZ() + 0.5
        );

        double yDifference = Math.abs(peasant.getY() - currentMiningPosition.getY());

        if (!peasant.level().isClientSide) {
            double actualDistance = Math.sqrt(distance);
            System.out.println("  DISTANCE CHECK: " + String.format("%.2f", actualDistance) +
                    " blocks (max: 1.5), Y diff: " + String.format("%.2f", yDifference));
        }

        // FIXED: Much stricter distance - must be within 1.5 blocks AND correct Y level
        return distance <= 2.25D && yDifference <= 0.5; // 1.5 blocks max, Y within 0.5
    }

    public void loadData(CompoundTag compound) {
        currentTunnelIndex = compound.getInt("CurrentTunnelIndex");
        currentTunnelProgress = compound.getInt("CurrentTunnelProgress");
        blocksMineToday = compound.getInt("BlocksMinedToday");
        torchCounter = compound.getInt("TorchCounter");

        try {
            currentMinerState = MinerState.valueOf(compound.getString("CurrentMinerState"));
        } catch (IllegalArgumentException e) {
            currentMinerState = MinerState.SELECTING_TUNNEL;
        }

        if (compound.contains("SelectedTunnelEntrance")) {
            int[] pos = compound.getIntArray("SelectedTunnelEntrance");
            if (pos.length == 3) {
                selectedTunnelEntrance = new BlockPos(pos[0], pos[1], pos[2]);
            }
        }

        if (compound.contains("CurrentMiningPosition")) {
            int[] pos = compound.getIntArray("CurrentMiningPosition");
            if (pos.length == 3) {
                currentMiningPosition = new BlockPos(pos[0], pos[1], pos[2]);
            }
        }

        if (compound.contains("MiningDirection")) {
            try {
                miningDirection = Direction.valueOf(compound.getString("MiningDirection"));
            } catch (IllegalArgumentException e) {
                miningDirection = null;
            }
        }
    }
}