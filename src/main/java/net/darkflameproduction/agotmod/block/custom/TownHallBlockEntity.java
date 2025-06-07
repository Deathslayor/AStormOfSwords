package net.darkflameproduction.agotmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.custom.npc.system.SimpleBedWarningSystem;

import java.util.Set;

public class TownHallBlockEntity extends BlockEntity {

    private static final int SCAN_TIME = 10000; // Time of day to perform scan
    private static final int SCAN_HEIGHT = 64; // Vertical radius (up and down)
    private static final int REGISTER_INTERVAL = 100; // Ticks between registration updates (5 seconds)
    private static final int CITIZEN_CHECK_INTERVAL = 200; // Check for citizens every 10 seconds

    // Dynamic radius constants
    private static final int BASE_RADIUS = 16; // Starting radius
    private static final int FIRST_TIER_CITIZENS = 10; // First 10 citizens
    private static final int FIRST_TIER_EXPANSION = 5; // 5 blocks per citizen for first 10
    private static final int SECOND_TIER_CITIZENS = 20; // Up to 20 citizens total
    private static final int SECOND_TIER_EXPANSION = 1; // 1 block per citizen for citizens 11-20
    private static final int THIRD_TIER_EXPANSION = 5; // 5 citizens required per 1 block after 20

    private int bedCount = 0;
    private int citizenCount = 0;
    private String townName = "Unnamed Town"; // Default town name
    private long lastScanDay = -1;
    private boolean hasScannedToday = false;
    private int ticksSinceLastRegister = 0;
    private int ticksSinceLastCitizenCheck = 0;

    public TownHallBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TOWN_HALL.get(), pos, blockState);
    }

    /**
     * Calculates the current scan radius based on citizen count
     * Tier 1 (0-10 citizens): base + (citizens * 5)
     * Tier 2 (11-20 citizens): base + (10 * 5) + ((citizens - 10) * 1)
     * Tier 3 (21+ citizens): base + (10 * 5) + (10 * 1) + ((citizens - 20) / 5)
     * @return the radius in blocks
     */
    public int getCurrentScanRadius() {
        if (citizenCount <= FIRST_TIER_CITIZENS) {
            // First 10 citizens: base + (citizens * 5)
            return BASE_RADIUS + (citizenCount * FIRST_TIER_EXPANSION);
        } else if (citizenCount <= SECOND_TIER_CITIZENS) {
            // Citizens 11-20: base + (10 * 5) + ((citizens - 10) * 1)
            int firstTierBonus = FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION;
            int secondTierBonus = (citizenCount - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION;
            return BASE_RADIUS + firstTierBonus + secondTierBonus;
        } else {
            // Citizens 21+: base + (10 * 5) + (10 * 1) + ((citizens - 20) / 5)
            int firstTierBonus = FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION; // 10 * 5 = 50
            int secondTierBonus = (SECOND_TIER_CITIZENS - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION; // 10 * 1 = 10
            int thirdTierBonus = (citizenCount - SECOND_TIER_CITIZENS) / THIRD_TIER_EXPANSION; // Every 5 citizens = 1 block
            return BASE_RADIUS + firstTierBonus + secondTierBonus + thirdTierBonus;
        }
    }

    /**
     * Gets the current scan height (unchanged)
     * @return the scan height in blocks
     */
    public int getCurrentScanHeight() {
        return SCAN_HEIGHT;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TownHallBlockEntity blockEntity) {
        if (level.isClientSide()) return;

        long currentTime = level.getDayTime() % 24000;
        long currentDay = level.getDayTime() / 24000;

        // Check if it's time to scan (time = 10000) and we haven't scanned today
        if (currentTime >= SCAN_TIME &&
                (blockEntity.lastScanDay != currentDay || !blockEntity.hasScannedToday)) {

            blockEntity.performBedScan(level);
            blockEntity.lastScanDay = currentDay;
            blockEntity.hasScannedToday = true;
            blockEntity.setChanged();
        }

        // Reset scan flag at start of new day
        if (currentTime < SCAN_TIME && blockEntity.hasScannedToday) {
            blockEntity.hasScannedToday = false;
        }

        // Periodically register with nearby players (every 5 seconds)
        blockEntity.ticksSinceLastRegister++;
        if (blockEntity.ticksSinceLastRegister >= REGISTER_INTERVAL) {
            blockEntity.registerWithNearbyPlayers();
            blockEntity.ticksSinceLastRegister = 0;
        }

        // Periodically check for new/removed citizens (every 10 seconds)
        blockEntity.ticksSinceLastCitizenCheck++;
        if (blockEntity.ticksSinceLastCitizenCheck >= CITIZEN_CHECK_INTERVAL) {
            blockEntity.updateCitizenRegistry(level);
            blockEntity.ticksSinceLastCitizenCheck = 0;
        }
    }

    private void updateCitizenRegistry(Level level) {
        // Store the current radius at the start of the scan to keep it stable
        int scanRadius = getCurrentScanRadius();
        int newCitizenCount = 0;



        // Get all home bed claims within our current scan radius
        Set<BlockPos> homeBedClaims = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                this.getBlockPos(), scanRadius, null); // null excludeUUID to get all claims


        // Count beds that are actually within our scan area using the stable radius
        for (BlockPos claimedBedPos : homeBedClaims) {

            if (isWithinStableScanArea(claimedBedPos, scanRadius)) {
                newCitizenCount++;
            } else {
            }
        }


        // Only update if the count actually changed
        if (newCitizenCount != citizenCount) {
            int oldCount = citizenCount;
            int oldRadius = getCurrentScanRadius();

            // Update citizen count
            citizenCount = newCitizenCount;

            int newRadius = getCurrentScanRadius();


            sendDataToClients(level);
            setChanged();
        }
    }

    /**
     * Helper method to check if a bed is within a specific radius (used during scanning)
     */
    private boolean isWithinMaxScanArea(BlockPos bedPos, int maxRadius) {
        BlockPos townHallPos = this.getBlockPos();
        int currentHeight = getCurrentScanHeight();

        int dx = Math.abs(bedPos.getX() - townHallPos.getX());
        int dy = Math.abs(bedPos.getY() - townHallPos.getY());
        int dz = Math.abs(bedPos.getZ() - townHallPos.getZ());

        return dx <= maxRadius && dy <= currentHeight && dz <= maxRadius;
    }

    /**
     * Helper method to check if a bed is within a specific radius
     */
    private boolean isWithinSpecificScanArea(BlockPos bedPos, int specificRadius) {
        BlockPos townHallPos = this.getBlockPos();
        int currentHeight = getCurrentScanHeight();

        int dx = Math.abs(bedPos.getX() - townHallPos.getX());
        int dy = Math.abs(bedPos.getY() - townHallPos.getY());
        int dz = Math.abs(bedPos.getZ() - townHallPos.getZ());

        return dx <= specificRadius && dy <= currentHeight && dz <= specificRadius;
    }

    /**
     * Calculate radius for a given citizen count (separate from current state)
     */
    private int calculateRadiusForCitizenCount(int citizens) {
        if (citizens <= FIRST_TIER_CITIZENS) {
            // First 10 citizens: base + (citizens * 5)
            return BASE_RADIUS + (citizens * FIRST_TIER_EXPANSION);
        } else {
            // After first 10: base + (10 * 5) + ((citizens - 10) * 1)
            int firstTierBonus = FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION;
            int secondTierBonus = (citizens - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION;
            return BASE_RADIUS + firstTierBonus + secondTierBonus;
        }
    }

    private boolean isWithinStableScanArea(BlockPos bedPos, int stableRadius) {
        BlockPos townHallPos = this.getBlockPos();
        int currentHeight = getCurrentScanHeight();

        int dx = Math.abs(bedPos.getX() - townHallPos.getX());
        int dy = Math.abs(bedPos.getY() - townHallPos.getY());
        int dz = Math.abs(bedPos.getZ() - townHallPos.getZ());

        return dx <= stableRadius && dy <= currentHeight && dz <= stableRadius;
    }

    private void performBedScan(Level level) {
        int bedCount = 0;
        int currentRadius = getCurrentScanRadius();
        int currentHeight = getCurrentScanHeight();

        BlockPos centerPos = this.getBlockPos();
        int minX = centerPos.getX() - currentRadius;
        int maxX = centerPos.getX() + currentRadius;
        int minY = centerPos.getY() - currentHeight;
        int maxY = centerPos.getY() + currentHeight;
        int minZ = centerPos.getZ() - currentRadius;
        int maxZ = centerPos.getZ() + currentRadius;

        // Ensure Y bounds are within world limits
        minY = Math.max(minY, level.dimensionType().minY());
        maxY = Math.min(maxY, level.dimensionType().minY() + level.dimensionType().height() - 1);


        // Scan the area for beds - only count HEAD blocks to avoid double counting
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos scanPos = new BlockPos(x, y, z);

                    // Check if the chunk is loaded before scanning
                    if (level.isLoaded(scanPos)) {
                        BlockState blockState = level.getBlockState(scanPos);

                        // Check if the block is a bed HEAD (only count head blocks to avoid double counting)
                        if (blockState.getBlock() instanceof BedBlock) {
                            // Only count if this is the HEAD part of the bed
                            if (blockState.getValue(BedBlock.PART) == net.minecraft.world.level.block.state.properties.BedPart.HEAD) {
                                bedCount++;
                            }
                        }
                    }
                }
            }
        }

        this.bedCount = bedCount;

        // Send update packet to any players who have the GUI open AND debug renderer updates
        sendDataToClients(level);
    }

    private void registerWithNearbyPlayers() {
        // Send registration packet to all players within render distance
        // Using render distance (typically 12-32 chunks = 192-512 blocks)
        int renderDistance = level.getServer().getPlayerList().getViewDistance() * 16; // Convert chunks to blocks

        level.players().forEach(player -> {
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < renderDistance * renderDistance) {
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallRegisterPacket(this.worldPosition, true));
                }
            }
        });
    }

    private void sendDataToClients(Level level) {
        // Send data packet to all nearby players for GUI updates (close range)
        level.players().forEach(player -> {
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < 64 * 64) { // Within 64 blocks for GUI
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallDataPacket(this.worldPosition, this.bedCount, this.citizenCount, getCurrentScanRadius(), this.townName));
                }
            }
        });

        // ALSO send to all players within render distance for debug renderer updates (wider range)
        sendDebugDataToClients(level);
    }

    private void sendDebugDataToClients(Level level) {
        // Send debug data to all players within render distance for the debug renderer
        int renderDistance = level.getServer().getPlayerList().getViewDistance() * 16; // Convert chunks to blocks

        level.players().forEach(player -> {
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < renderDistance * renderDistance) { // Within render distance
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallDataPacket(this.worldPosition, this.bedCount, this.citizenCount, getCurrentScanRadius(), this.townName));
                }
            }
        });
    }

    public int getBedCount() {
        return bedCount;
    }

    public int getCitizenCount() {
        return citizenCount;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        // Validate and clean the town name
        if (townName == null || townName.trim().isEmpty()) {
            this.townName = "Unnamed Town";
        } else {
            // Limit length and clean up the name
            String cleanName = townName.trim();
            if (cleanName.length() > 32) {
                cleanName = cleanName.substring(0, 32);
            }
            this.townName = cleanName;
        }
        setChanged(); // Mark as dirty for saving
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (!level.isClientSide()) {
            // Register this Town Hall with nearby players
            registerWithNearbyPlayers();
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        if (!level.isClientSide()) {
            // Unregister this Town Hall from clients
            level.players().forEach(player -> {
                if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                    int renderDistance = level.getServer().getPlayerList().getViewDistance() * 16;
                    double distance = serverPlayer.distanceToSqr(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                    if (distance < renderDistance * renderDistance) {
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new net.darkflameproduction.agotmod.network.TownHallRegisterPacket(this.worldPosition, false));
                    }
                }
            });
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("BedCount", bedCount);
        tag.putInt("CitizenCount", citizenCount);
        tag.putString("TownName", townName);
        tag.putLong("LastScanDay", lastScanDay);
        tag.putBoolean("HasScannedToday", hasScannedToday);
        tag.putInt("TicksSinceLastRegister", ticksSinceLastRegister);
        tag.putInt("TicksSinceLastCitizenCheck", ticksSinceLastCitizenCheck);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        bedCount = tag.getInt("BedCount");
        citizenCount = tag.getInt("CitizenCount");
        townName = tag.getString("TownName");
        if (townName.isEmpty()) {
            townName = "Unnamed Town"; // Fallback for old saves
        }
        lastScanDay = tag.getLong("LastScanDay");
        hasScannedToday = tag.getBoolean("HasScannedToday");
        ticksSinceLastRegister = tag.getInt("TicksSinceLastRegister");
        ticksSinceLastCitizenCheck = tag.getInt("TicksSinceLastCitizenCheck");
    }
}