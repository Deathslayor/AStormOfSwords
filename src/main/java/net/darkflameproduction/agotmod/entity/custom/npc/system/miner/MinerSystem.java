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
    private boolean hasReturnedToJobBlockAfterFood = true;
    private MinerState currentMinerState = MinerState.NEEDS_MINE_SETUP;

    // Mining tracking
    private BlockPos currentMiningTarget = null;
    private BlockPos currentHallwayStart = null;
    private Direction currentHallwayDirection = null;
    private int currentHallwayLength = 0;
    private int currentHallwayIndex = 0;
    private long lastMiningTime = 0;
    private static final int MINING_INTERVAL_TICKS = 40; // 2 seconds (20 ticks per second)
    private static final int MAX_HALLWAY_LENGTH = 128;

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

        // Ingots and gems
        ALLOWED_ITEMS.add("minecraft:iron_ingot");
        ALLOWED_ITEMS.add("minecraft:gold_ingot");
        ALLOWED_ITEMS.add("minecraft:copper_ingot");
        ALLOWED_ITEMS.add("minecraft:diamond");
        ALLOWED_ITEMS.add("minecraft:emerald");
        ALLOWED_ITEMS.add("minecraft:lapis_lazuli");
        ALLOWED_ITEMS.add("minecraft:redstone");

        // Stone and building materials
        ALLOWED_ITEMS.add("minecraft:stone");
        ALLOWED_ITEMS.add("minecraft:cobblestone");
        ALLOWED_ITEMS.add("minecraft:deepslate");
        ALLOWED_ITEMS.add("minecraft:cobbled_deepslate");
        ALLOWED_ITEMS.add("minecraft:granite");
        ALLOWED_ITEMS.add("minecraft:diorite");
        ALLOWED_ITEMS.add("minecraft:andesite");

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
                currentMinerState = MinerState.NEEDS_MINE_SETUP;
            }
        }

        // Handle mining activities
        if (currentMinerState == MinerState.MINING) {
            handleMiningActivities();
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

        // Move to next state after setup
        currentMinerState = MinerState.MINING;

        return 1; // Return that work was done
    }

    private void buildSchematicMine(BlockPos center) {
        // First remove nearby lava and water
        removeLavaAndWater(center);

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

    private void clearExactAirPositions(BlockPos center) {
        // Clear blocks based on the complete schematic pattern
        // The schematic shows air blocks in specific patterns throughout the mine

        // Method 1: Clear the main mine shaft areas where most air should be
        // Based on analyzing the schematic, the air forms the main mining areas

        // Clear the central spiral shaft area
        for (int y = 35; y >= 0; y--) { // Full depth of schematic
            int worldY = center.getY() + (y - 35);

            // Clear different sized areas based on depth to match schematic pattern
            int clearRadius = getClearRadiusForLevel(y);

            for (int x = 4 - clearRadius; x <= 4 + clearRadius; x++) {
                for (int z = 4 - clearRadius; z <= 4 + clearRadius; z++) {
                    // Skip the job block position
                    if (y == 35 && x == 4 && z == 4) continue;

                    // Skip positions that should have cobblestone or stairs
                    if (shouldHaveCobblestone(x, y, z) || shouldHaveStairs(x, y, z)) continue;

                    BlockPos worldPos = new BlockPos(
                            center.getX() + (x - 4),
                            worldY,
                            center.getZ() + (z - 4)
                    );
                    peasant.level().setBlock(worldPos, Blocks.AIR.defaultBlockState(), 3);
                }
            }
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
        // Build the exact blocks from the schematic data
        // Convert schematic coordinates to world coordinates: [center.x + (x-4), center.y + (y-35), center.z + (z-4)]

        // Place cobblestone platforms (state 1) and clear above them
        buildCobblestoneBlocks(center);

        // Place stair blocks (states 8, 10, 11, 12) and clear above them
        buildStairBlocks(center);

        // Build the large 19x19 platform at the bottom
        buildBottomPlatform(center);
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

    /**
     * Handles all mining activities including hallway excavation and ore collection
     */
    /**
     * Handles all mining activities including hallway excavation and ore collection
     */
    // ==================== MinerSystem.java - REWRITTEN METHODS ONLY ====================

    /**
     * Handles all mining activities including hallway excavation and ore collection
     * COMPLETELY REWRITTEN with better distance handling and debugging
     */
    private void handleMiningActivities() {
        // Check if enough time has passed since last mining action (2 seconds)
        long currentTime = peasant.level().getGameTime();
        if (currentTime - lastMiningTime < MINING_INTERVAL_TICKS) {
            return; // Wait for mining cooldown
        }

        // Debug current state
        if (!peasant.level().isClientSide) {
            System.out.println("=== MINING ACTIVITY TICK [" + peasant.getName().getString() + "] ===");
            System.out.println("  Current target: " + currentMiningTarget);
            System.out.println("  Hallway index: " + currentHallwayIndex);
            System.out.println("  Hallway length: " + currentHallwayLength + "/" + MAX_HALLWAY_LENGTH);
            System.out.println("  Miner state: " + currentMinerState);
            System.out.println("  Peasant position: " + peasant.blockPosition());
        }

        // If no current target, find the next hallway to work on
        if (currentMiningTarget == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("  No current target, selecting next hallway");
            }
            selectNextHallway();
            return; // Wait for next tick to start mining
        }

        // Calculate distance to target
        double distanceToTarget = peasant.distanceToSqr(
                currentMiningTarget.getX() + 0.5,
                currentMiningTarget.getY(),
                currentMiningTarget.getZ() + 0.5
        );
        double actualDistance = Math.sqrt(distanceToTarget);

        if (!peasant.level().isClientSide) {
            System.out.println("  Target: " + currentMiningTarget);
            System.out.println("  Distance: " + String.format("%.2f", actualDistance) + " blocks");
            System.out.println("  Within range: " + (distanceToTarget <= 12.25D)); // 3.5 blocks
        }

        // Perform mining if close enough (3.5 blocks)
        if (distanceToTarget <= 12.25D) { // 3.5 blocks
            if (!peasant.level().isClientSide) {
                System.out.println("  PERFORMING MINING ACTION");
            }
            performMining();
            lastMiningTime = currentTime;
        } else {
            if (!peasant.level().isClientSide) {
                System.out.println("  Waiting for peasant to get closer to target");
            }
            // The MinerGoal will handle navigation to the target
        }
    }

    private void selectNextHallway() {
        if (peasant.getJobBlockPos() == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("  ERROR: No job block position, cannot select hallway");
            }
            return;
        }

        BlockPos center = peasant.getJobBlockPos();
        int platformY = center.getY() + (-1 - 35); // Platform level

        // Get all tunnel entrance positions
        List<BlockPos> tunnelEntrances = getAllTunnelEntrances(center, platformY);

        if (!peasant.level().isClientSide) {
            System.out.println("=== HALLWAY SELECTION [" + peasant.getName().getString() + "] ===");
            System.out.println("  Mine center: " + center);
            System.out.println("  Platform Y: " + platformY);
            System.out.println("  Current hallway index: " + currentHallwayIndex);
            System.out.println("  Total tunnel entrances: " + tunnelEntrances.size());
            System.out.println("  Available entrances: " + tunnelEntrances);
        }

        // Safety check for empty tunnels
        if (tunnelEntrances.isEmpty()) {
            if (!peasant.level().isClientSide) {
                System.out.println("  ERROR: No tunnel entrances found! Cannot mine.");
            }
            currentMinerState = MinerState.PATROLLING;
            return;
        }

        // Handle infinite mining - cycle through all entrances repeatedly
        if (currentHallwayIndex >= tunnelEntrances.size()) {
            if (!peasant.level().isClientSide) {
                System.out.println("  Completed all " + tunnelEntrances.size() + " tunnels, cycling back to start");
            }
            currentHallwayIndex = 0; // Reset to beginning for infinite mining
            // If you want finite mining instead, uncomment these lines:
            // currentMinerState = MinerState.PATROLLING;
            // return;
        }

        // Get the current tunnel entrance to work on
        BlockPos tunnelEntrance = tunnelEntrances.get(currentHallwayIndex);

        if (!peasant.level().isClientSide) {
            System.out.println("  Selected tunnel entrance #" + currentHallwayIndex + ": " + tunnelEntrance);
        }

        startNewHallway(tunnelEntrance);
    }

    public BlockPos getCurrentHallwayStart() {
        return currentHallwayStart;
    }

    private List<BlockPos> getAllTunnelEntrances(BlockPos center, int platformY) {
        List<BlockPos> entrances = new ArrayList<>();

        if (!peasant.level().isClientSide) {
            System.out.println("=== CALCULATING TUNNEL ENTRANCES ===");
            System.out.println("  Mine center (job block): " + center + " (Y=" + center.getY() + ")");
            System.out.println("  Platform Y level: " + platformY + " (should be " + (center.getY() - 36) + ")");
            System.out.println("  Entrance Y level: " + (platformY + 1) + " (1 block above platform)");
        }

        // All entrances are at platformY + 1 (1 block above the platform floor)
        int entranceY = platformY + 1;

        // North wall entrances (every other block from x=-9 to x=9)
        for (int x = -9; x <= 9; x += 2) {
            BlockPos entrance = new BlockPos(center.getX() + x, entranceY, center.getZ() - 10);
            entrances.add(entrance);
            if (!peasant.level().isClientSide) {
                System.out.println("    North wall entrance: " + entrance);
            }
        }

        // South wall entrances (every other block from x=-9 to x=9)
        for (int x = -9; x <= 9; x += 2) {
            BlockPos entrance = new BlockPos(center.getX() + x, entranceY, center.getZ() + 10);
            entrances.add(entrance);
            if (!peasant.level().isClientSide) {
                System.out.println("    South wall entrance: " + entrance);
            }
        }

        // East wall entrances (every other block from z=-9 to z=9)
        for (int z = -9; z <= 9; z += 2) {
            BlockPos entrance = new BlockPos(center.getX() + 10, entranceY, center.getZ() + z);
            entrances.add(entrance);
            if (!peasant.level().isClientSide) {
                System.out.println("    East wall entrance: " + entrance);
            }
        }

        // West wall entrances (every other block from z=-9 to z=9)
        for (int z = -9; z <= 9; z += 2) {
            BlockPos entrance = new BlockPos(center.getX() - 10, entranceY, center.getZ() + z);
            entrances.add(entrance);
            if (!peasant.level().isClientSide) {
                System.out.println("    West wall entrance: " + entrance);
            }
        }

        if (!peasant.level().isClientSide) {
            System.out.println("  Total entrances created: " + entrances.size());
            System.out.println("  All entrances at Y=" + entranceY + " (underground level)");
        }

        return entrances;
    }

    private void startNewHallway(BlockPos entrance) {
        if (!peasant.level().isClientSide) {
            System.out.println("=== STARTING NEW HALLWAY [" + peasant.getName().getString() + "] ===");
            System.out.println("  Target entrance position: " + entrance);
            System.out.println("  Current peasant position: " + peasant.blockPosition());

            // Calculate the vertical distance
            int verticalDistance = peasant.blockPosition().getY() - entrance.getY();
            System.out.println("  Vertical distance to entrance: " + verticalDistance + " blocks");

            if (verticalDistance > 30) {
                System.out.println("  WARNING: Peasant is " + verticalDistance + " blocks above entrance!");
                System.out.println("  Peasant needs to navigate DOWN into the mine!");
            }
        }

        // Reset hallway tracking
        currentHallwayStart = entrance;
        currentHallwayLength = 0;

        // Determine mining direction based on entrance position relative to center
        BlockPos center = peasant.getJobBlockPos();
        int deltaX = entrance.getX() - center.getX();
        int deltaZ = entrance.getZ() - center.getZ();

        if (!peasant.level().isClientSide) {
            System.out.println("  Center position: " + center);
            System.out.println("  Delta from center - X: " + deltaX + ", Z: " + deltaZ);
        }

        // Determine direction based on which wall the entrance is on
        if (Math.abs(deltaZ) > Math.abs(deltaX)) {
            // North or South wall
            if (deltaZ < 0) {
                currentHallwayDirection = Direction.NORTH; // Mine further north (away from center)
            } else {
                currentHallwayDirection = Direction.SOUTH; // Mine further south (away from center)
            }
        } else {
            // East or West wall
            if (deltaX < 0) {
                currentHallwayDirection = Direction.WEST; // Mine further west (away from center)
            } else {
                currentHallwayDirection = Direction.EAST; // Mine further east (away from center)
            }
        }

        // IMPORTANT: Set the mining target to the ENTRANCE ITSELF first
        // The peasant needs to reach the entrance before mining outward
        currentMiningTarget = entrance;

        if (!peasant.level().isClientSide) {
            System.out.println("  Mining direction (outward from entrance): " + currentHallwayDirection);
            System.out.println("  First mining target: " + currentMiningTarget + " (the entrance itself)");
            System.out.println("  After reaching entrance, will mine " + currentHallwayDirection);
        }

        // The MinerGoal will handle navigation to the entrance
        // Once the peasant reaches the entrance, mining will proceed outward
    }

    private void performMining() {
        if (currentMiningTarget == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("  ERROR: performMining called with null target");
            }
            return;
        }

        if (!peasant.level().isClientSide) {
            System.out.println("=== PERFORMING MINING [" + peasant.getName().getString() + "] ===");
            System.out.println("  Mining target: " + currentMiningTarget);
            System.out.println("  Hallway progress: " + currentHallwayLength + "/" + MAX_HALLWAY_LENGTH);
            System.out.println("  Direction: " + currentHallwayDirection);
            System.out.println("  Hallway index: " + currentHallwayIndex);
        }

        // Step 1: Clear the exact 1x2 hallway blocks and collect their drops
        clearAndMineHallwayBlocks(currentMiningTarget);

        // Step 2: Mine valuable ores in 5x5 radius (but not stone types)
        mineNearbyOresSelectively(currentMiningTarget);

        // Step 3: Ensure there's a floor to walk on
        ensureFloorBlock(currentMiningTarget);

        // Step 4: Trigger visual mining animation
        triggerInteractAnimation();

        // Step 5: Advance to next position in hallway
        currentHallwayLength++;

        if (!peasant.level().isClientSide) {
            System.out.println("  Mined position " + currentMiningTarget);
            System.out.println("  Hallway progress now: " + currentHallwayLength + "/" + MAX_HALLWAY_LENGTH);
        }

        // Step 6: Check if hallway is complete
        if (currentHallwayLength >= MAX_HALLWAY_LENGTH) {
            // Current hallway complete - move to next tunnel
            if (!peasant.level().isClientSide) {
                System.out.println("  HALLWAY #" + currentHallwayIndex + " COMPLETE! Moving to next tunnel.");
            }

            currentHallwayIndex++;
            currentMiningTarget = null;
            currentHallwayStart = null;
            currentHallwayDirection = null;
            currentHallwayLength = 0;

            if (!peasant.level().isClientSide) {
                System.out.println("  Advanced to hallway index: " + currentHallwayIndex);
                System.out.println("  Next target will be selected on next tick");
            }
        } else {
            // Continue current hallway - advance target position
            BlockPos nextTarget = currentMiningTarget.relative(currentHallwayDirection);
            currentMiningTarget = nextTarget;

            if (!peasant.level().isClientSide) {
                System.out.println("  Continuing hallway, next target: " + nextTarget);
            }
        }
    }

    private void mineNearbyOresSelectively(BlockPos center) {
        if (!peasant.level().isClientSide) {
            System.out.println("    Scanning 5x5 area for ores around " + center);
        }

        int oresFound = 0;
        int oresMined = 0;

        // Check in a 5x5 horizontal area around the hallway (2 blocks in each direction)
        // Only check the 2 vertical levels of the hallway (not above or below)
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                // Skip the center blocks (they're handled by clearAndMineHallwayBlocks)
                if (x == 0 && z == 0) continue;

                // Check both levels of the hallway
                BlockPos[] checkPositions = {
                        center.offset(x, 0, z),        // Bottom level of hallway
                        center.offset(x, 1, z)         // Top level of hallway
                };

                for (BlockPos checkPos : checkPositions) {
                    // Verify peasant can reach this position
                    double distanceToBlock = peasant.distanceToSqr(
                            checkPos.getX() + 0.5,
                            checkPos.getY(),
                            checkPos.getZ() + 0.5
                    );

                    // Must be within 4 blocks to mine
                    if (distanceToBlock > 16.0D) {
                        continue;
                    }

                    BlockState blockState = peasant.level().getBlockState(checkPos);

                    // Only mine if this block is a TRUE ore (not stone types)
                    if (isTrueOre(blockState)) {
                        oresFound++;
                        if (!peasant.level().isClientSide) {
                            System.out.println("      Found ore: " + getBlockRegistryName(blockState) + " at " + checkPos);
                        }
                        mineSpecificBlock(checkPos);
                        oresMined++;
                    }
                }
            }
        }

        if (!peasant.level().isClientSide && oresFound > 0) {
            System.out.println("    Ore scan complete: " + oresMined + "/" + oresFound + " ores mined");
        }
    }

    private boolean isTrueOre(BlockState blockState) {
        String blockId = getBlockRegistryName(blockState);

        // Explicitly exclude regular stone types that shouldn't be considered "ores"
        Set<String> STONE_TYPES = Set.of(
                "minecraft:stone", "minecraft:cobblestone", "minecraft:deepslate",
                "minecraft:cobbled_deepslate", "minecraft:granite", "minecraft:diorite",
                "minecraft:andesite", "minecraft:tuff", "minecraft:calcite",
                "minecraft:dirt", "minecraft:grass_block", "minecraft:gravel"
        );

        if (STONE_TYPES.contains(blockId)) {
            return false;
        }

        // Define actual valuable ores and materials
        Set<String> TRUE_ORES = Set.of(
                // Vanilla ores
                "minecraft:coal_ore", "minecraft:deepslate_coal_ore",
                "minecraft:iron_ore", "minecraft:deepslate_iron_ore",
                "minecraft:gold_ore", "minecraft:deepslate_gold_ore",
                "minecraft:diamond_ore", "minecraft:deepslate_diamond_ore",
                "minecraft:emerald_ore", "minecraft:deepslate_emerald_ore",
                "minecraft:lapis_ore", "minecraft:deepslate_lapis_ore",
                "minecraft:redstone_ore", "minecraft:deepslate_redstone_ore",
                "minecraft:copper_ore", "minecraft:deepslate_copper_ore",

                // Raw materials and processed items
                "minecraft:raw_iron", "minecraft:raw_gold", "minecraft:raw_copper",
                "minecraft:iron_ingot", "minecraft:gold_ingot", "minecraft:copper_ingot",
                "minecraft:diamond", "minecraft:emerald", "minecraft:lapis_lazuli",
                "minecraft:redstone", "minecraft:coal"
        );

        // Check vanilla ores first
        if (TRUE_ORES.contains(blockId)) {
            return true;
        }

        // Check custom mod ores (anything with agotmod namespace that contains "ore" or valuable keywords)
        if (blockId.startsWith("agotmod:")) {
            return blockId.contains("_ore") ||
                    blockId.contains("_block") && !blockId.contains("stone") ||
                    blockId.contains("silver") || blockId.contains("tin") || blockId.contains("bronze") ||
                    blockId.contains("amber") || blockId.contains("ruby") || blockId.contains("sapphire") ||
                    blockId.contains("garnet") || blockId.contains("jade") || blockId.contains("topaz") ||
                    blockId.contains("diamond") && !blockId.equals("agotmod:diamond_block");
        }

        return false;
    }

    public void resetMiningState() {
        if (!peasant.level().isClientSide) {
            System.out.println("=== RESETTING MINING STATE [" + peasant.getName().getString() + "] ===");
            System.out.println("  Previous state - Target: " + currentMiningTarget + ", Index: " + currentHallwayIndex + ", Length: " + currentHallwayLength);
        }

        // Clear current mining operation but preserve progress
        currentMiningTarget = null;
        currentHallwayStart = null;
        currentHallwayDirection = null;
        currentHallwayLength = 0;
        // Keep currentHallwayIndex - don't lose overall progress

        // Ensure we're in mining state
        if (currentMinerState == MinerState.PATROLLING) {
            currentMinerState = MinerState.MINING;
        }

        if (!peasant.level().isClientSide) {
            System.out.println("  Reset complete, will select new hallway on next tick");
        }
    }


    /**
     * Gets the drops that a block would give when mined
     */
    private List<ItemStack> getBlockDrops(BlockState blockState, BlockPos blockPos) {
        List<ItemStack> drops = new ArrayList<>();

        // Get the block's loot table drops
        try {
            // Use the block's built-in drop logic
            List<ItemStack> blockDrops = Block.getDrops(blockState, (ServerLevel) peasant.level(), blockPos, null);
            drops.addAll(blockDrops);
        } catch (Exception e) {
            // Fallback: create a basic drop based on the block type
            ItemStack basicDrop = new ItemStack(blockState.getBlock().asItem(), 1);
            if (!basicDrop.isEmpty()) {
                drops.add(basicDrop);
            }
        }

        // If no drops were generated, create a basic drop
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
            // Try to get the proper registry name
            var registryName = net.minecraft.core.registries.BuiltInRegistries.BLOCK.getKey(blockState.getBlock());
            if (registryName != null) {
                return registryName.toString();
            }
        } catch (Exception e) {
            // Fallback to description ID
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
     * Clears blocks to create a 2-high hallway
     */
    private void clearAndMineHallwayBlocks(BlockPos pos) {
        if (!peasant.level().isClientSide) {
            System.out.println("    Clearing 1x2 hallway at " + pos);
        }

        // Mine the main block (bottom of hallway)
        BlockState bottomBlock = peasant.level().getBlockState(pos);
        if (!bottomBlock.isAir()) {
            if (!peasant.level().isClientSide) {
                System.out.println("      Mining bottom block: " + getBlockRegistryName(bottomBlock));
            }
            mineSpecificBlock(pos);
        }

        // Mine the block above (top of hallway)
        BlockPos topPos = pos.above();
        BlockState topBlock = peasant.level().getBlockState(topPos);
        if (!topBlock.isAir()) {
            if (!peasant.level().isClientSide) {
                System.out.println("      Mining top block: " + getBlockRegistryName(topBlock));
            }
            mineSpecificBlock(topPos);
        }
    }


    private void mineSpecificBlock(BlockPos blockPos) {
        BlockState blockState = peasant.level().getBlockState(blockPos);

        // Skip if already air
        if (blockState.isAir()) {
            return;
        }

        // Verify distance one more time
        double distanceToBlock = peasant.distanceToSqr(
                blockPos.getX() + 0.5,
                blockPos.getY(),
                blockPos.getZ() + 0.5
        );

        if (distanceToBlock > 16.0D) { // More than 4 blocks away
            if (!peasant.level().isClientSide) {
                System.out.println("      Skipping block " + blockPos + " - too far (distance: " + Math.sqrt(distanceToBlock) + ")");
            }
            return;
        }

        // Get the drops from the block before breaking it
        List<ItemStack> drops = getBlockDrops(blockState, blockPos);
        String blockName = getBlockRegistryName(blockState);

        // Remove the block from the world
        peasant.level().setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);

        if (!peasant.level().isClientSide) {
            System.out.println("      Mined: " + blockName + " at " + blockPos + " -> " + drops.size() + " drops");
        }

        // Add drops to peasant's inventory
        int itemsAdded = 0;
        int itemsDropped = 0;

        for (ItemStack drop : drops) {
            if (!drop.isEmpty()) {
                boolean added = peasant.getInventorySystem().addItem(drop);

                if (added) {
                    itemsAdded++;
                } else {
                    // Inventory full - drop on ground
                    if (peasant.level() instanceof ServerLevel serverLevel) {
                        peasant.spawnAtLocation(serverLevel, drop);
                        itemsDropped++;
                    }
                }
            }
        }

        if (!peasant.level().isClientSide && (itemsAdded > 0 || itemsDropped > 0)) {
            System.out.println("        Items: " + itemsAdded + " to inventory, " + itemsDropped + " dropped on ground");
        }
    }


    public void advanceFromEntranceToMining() {
        if (currentHallwayStart == null || currentHallwayDirection == null) {
            if (!peasant.level().isClientSide) {
                System.out.println("  ERROR: Cannot advance from entrance - missing start or direction");
            }
            return;
        }

        // Move target from entrance to first mining position (one block in mining direction)
        BlockPos firstMiningPos = currentHallwayStart.relative(currentHallwayDirection);
        currentMiningTarget = firstMiningPos;

        if (!peasant.level().isClientSide) {
            System.out.println("=== ADVANCING FROM ENTRANCE TO MINING [" + peasant.getName().getString() + "] ===");
            System.out.println("  Entrance: " + currentHallwayStart);
            System.out.println("  First mining position: " + firstMiningPos);
            System.out.println("  Direction: " + currentHallwayDirection);
        }
    }



    public boolean hasReachedCurrentEntrance() {
        if (currentHallwayStart == null) {
            return false;
        }

        // Check if peasant is close to the tunnel entrance
        double distanceToEntrance = peasant.distanceToSqr(
                currentHallwayStart.getX() + 0.5,
                currentHallwayStart.getY(),
                currentHallwayStart.getZ() + 0.5
        );

        boolean atEntrance = distanceToEntrance <= 4.0D; // Within 2 blocks

        if (!peasant.level().isClientSide && atEntrance) {
            System.out.println("  Peasant has reached tunnel entrance: " + currentHallwayStart);
        }

        return atEntrance;
    }



    private void ensureFloorBlock(BlockPos pos) {
        BlockPos floorPos = pos.below();
        BlockState floorState = peasant.level().getBlockState(floorPos);

        // If there's air below, place cobblestone
        if (floorState.isAir()) {
            peasant.level().setBlock(floorPos, Blocks.COBBLESTONE.defaultBlockState(), 3);
            if (!peasant.level().isClientSide) {
                System.out.println("      Placed floor block at " + floorPos);
            }
        }
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

    public BlockPos getCurrentMiningTarget() {
        return currentMiningTarget;
    }

    public MinerState getCurrentMinerState() {
        return currentMinerState;
    }

    public void setCurrentMinerState(MinerState state) {
        this.currentMinerState = state;
    }

    public boolean hasReturnedToJobBlockAfterFood() {
        return hasReturnedToJobBlockAfterFood;
    }

    public void setHasReturnedToJobBlockAfterFood(boolean returned) {
        this.hasReturnedToJobBlockAfterFood = returned;
    }

    public void saveData(CompoundTag compound) {
        compound.putBoolean("HasReturnedToJobBlockAfterFood", hasReturnedToJobBlockAfterFood);
        compound.putString("CurrentMinerState", currentMinerState.name());
        compound.putInt("CurrentHallwayIndex", currentHallwayIndex);
        compound.putInt("CurrentHallwayLength", currentHallwayLength);
        compound.putLong("LastMiningTime", lastMiningTime);

        if (currentMiningTarget != null) {
            compound.putIntArray("CurrentMiningTarget", new int[]{
                    currentMiningTarget.getX(), currentMiningTarget.getY(), currentMiningTarget.getZ()
            });
        }

        if (currentHallwayStart != null) {
            compound.putIntArray("CurrentHallwayStart", new int[]{
                    currentHallwayStart.getX(), currentHallwayStart.getY(), currentHallwayStart.getZ()
            });
        }

        if (currentHallwayDirection != null) {
            compound.putString("CurrentHallwayDirection", currentHallwayDirection.name());
        }
    }

    public void loadData(CompoundTag compound) {
        hasReturnedToJobBlockAfterFood = compound.getBoolean("HasReturnedToJobBlockAfterFood");
        currentHallwayIndex = compound.getInt("CurrentHallwayIndex");
        currentHallwayLength = compound.getInt("CurrentHallwayLength");
        lastMiningTime = compound.getLong("LastMiningTime");

        try {
            currentMinerState = MinerState.valueOf(compound.getString("CurrentMinerState"));
        } catch (IllegalArgumentException e) {
            currentMinerState = MinerState.NEEDS_MINE_SETUP;
        }

        if (compound.contains("CurrentMiningTarget")) {
            int[] targetPos = compound.getIntArray("CurrentMiningTarget");
            if (targetPos.length == 3) {
                currentMiningTarget = new BlockPos(targetPos[0], targetPos[1], targetPos[2]);
            }
        }
        if (compound.contains("CurrentHallwayStart")) {
            int[] startPos = compound.getIntArray("CurrentHallwayStart");
            if (startPos.length == 3) {
                currentHallwayStart = new BlockPos(startPos[0], startPos[1], startPos[2]);
            }
        }

        if (compound.contains("CurrentHallwayDirection")) {
            try {
                currentHallwayDirection = Direction.valueOf(compound.getString("CurrentHallwayDirection"));
            } catch (IllegalArgumentException e) {
                currentHallwayDirection = null;
            }
        }
    }
}