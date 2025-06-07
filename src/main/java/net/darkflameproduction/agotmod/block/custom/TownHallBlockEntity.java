package net.darkflameproduction.agotmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.entity.custom.npc.system.SimpleBedWarningSystem;

import java.util.*;

public class TownHallBlockEntity extends BlockEntity {

    private static final int SCAN_TIME = 10000; // Time of day to perform scan
    private static final int CHILD_SPAWN_TIME = 18000; // Time of day to spawn children (midnight)
    private static final int SCAN_HEIGHT = 64; // Vertical radius (up and down)
    private static final int REGISTER_INTERVAL = 100; // Ticks between registration updates (5 seconds)
    private static final int CITIZEN_CHECK_INTERVAL = 200; // Check for citizens every 10 seconds
    private static final double CHILD_SPAWN_CHANCE = 0.05; // 5% chance per unclaimed bed
    private static final double FAMILY_NAME_CHANCE = 0.8; // 80% chance to inherit family name

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
    private long lastChildSpawnDay = -1; // Track last day children were spawned
    private boolean hasScannedToday = false;
    private int ticksSinceLastRegister = 0;
    private int ticksSinceLastCitizenCheck = 0;

    // Cache for citizen last names (refreshed during citizen checks)
    private List<String> citizenLastNames = new ArrayList<>();

    public TownHallBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.TOWN_HALL.get(), pos, blockState);
    }

    /**
     * Calculates the current scan radius based on citizen count
     */
    public int getCurrentScanRadius() {
        if (citizenCount <= FIRST_TIER_CITIZENS) {
            return BASE_RADIUS + (citizenCount * FIRST_TIER_EXPANSION);
        } else if (citizenCount <= SECOND_TIER_CITIZENS) {
            int firstTierBonus = FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION;
            int secondTierBonus = (citizenCount - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION;
            return BASE_RADIUS + firstTierBonus + secondTierBonus;
        } else {
            int firstTierBonus = FIRST_TIER_CITIZENS * FIRST_TIER_EXPANSION; // 10 * 5 = 50
            int secondTierBonus = (SECOND_TIER_CITIZENS - FIRST_TIER_CITIZENS) * SECOND_TIER_EXPANSION; // 10 * 1 = 10
            int thirdTierBonus = (citizenCount - SECOND_TIER_CITIZENS) / THIRD_TIER_EXPANSION; // Every 5 citizens = 1 block
            return BASE_RADIUS + firstTierBonus + secondTierBonus + thirdTierBonus;
        }
    }

    /**
     * Gets the current scan height (unchanged)
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

        // Check if it's time to spawn children (midnight) and we haven't spawned today
        if (currentTime >= CHILD_SPAWN_TIME && blockEntity.lastChildSpawnDay != currentDay) {
            blockEntity.attemptChildSpawning((ServerLevel) level);
            blockEntity.lastChildSpawnDay = currentDay;
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

    /**
     * Attempts to spawn children on unclaimed beds at midnight
     */
    private void attemptChildSpawning(ServerLevel level) {
        if (citizenCount == 0) return; // No point spawning if no existing citizens

        // Get all beds in town and claimed beds
        List<BlockPos> allBeds = getAllBedsInTown(level);
        Set<BlockPos> claimedBeds = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                this.getBlockPos(), getCurrentScanRadius(), null);

        // Find unclaimed beds
        List<BlockPos> unclaimedBeds = new ArrayList<>();
        for (BlockPos bedPos : allBeds) {
            if (!claimedBeds.contains(bedPos)) {
                unclaimedBeds.add(bedPos);
            }
        }

        if (unclaimedBeds.isEmpty()) return;

        // For each unclaimed bed, roll for child spawning
        RandomSource random = level.getRandom();
        for (BlockPos bedPos : unclaimedBeds) {
            if (random.nextDouble() < CHILD_SPAWN_CHANCE) {
                spawnChildAtBed(level, bedPos, random);
            }
        }
    }

    /**
     * Gets all bed positions within the town
     */
    private List<BlockPos> getAllBedsInTown(Level level) {
        List<BlockPos> beds = new ArrayList<>();
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

        // Scan for bed heads
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos scanPos = new BlockPos(x, y, z);

                    if (level.isLoaded(scanPos)) {
                        BlockState blockState = level.getBlockState(scanPos);

                        if (blockState.getBlock() instanceof BedBlock) {
                            if (blockState.getValue(BedBlock.PART) == net.minecraft.world.level.block.state.properties.BedPart.HEAD) {
                                beds.add(scanPos);
                            }
                        }
                    }
                }
            }
        }

        return beds;
    }

    /**
     * Spawns a child NPC at the specified bed
     */
    private void spawnChildAtBed(ServerLevel level, BlockPos bedPos, RandomSource random) {
        // Create the child peasant
        Peasant_Entity child = ModEntities.PEASANT_ENTITY.get().create(level, EntitySpawnReason.SPAWNER);
        if (child == null) return;

        // Set position at the bed
        child.setPos(bedPos.getX() + 0.5, bedPos.getY() + 1.0, bedPos.getZ() + 0.5);

        // Set as child
        child.setAge(Peasant_Entity.AGE_CHILD);

        // Random gender
        String gender = random.nextBoolean() ? Peasant_Entity.GENDER_MALE : Peasant_Entity.GENDER_FEMALE;
        child.setGender(gender);

        // Set aging timer for child
        int agingTime = 240000 + (random.nextInt(96000) - 48000); // Average ± variance
        child.setAgingTimer(0);
        child.getPersistentData().putInt("AgingTarget", agingTime);

        // Generate name with potential family connection
        generateChildName(child, random);

        // Set the bed as their home and register it with the warning system
        child.getHomeSystem().establishHomeBed(bedPos);

        // Register the bed claim with the warning system (including the child's name)
        String childName = child.hasCustomName() ? child.getCustomName().getString() : "Unnamed Child";
        SimpleBedWarningSystem.broadcastHomeBedClaim(child.getUUID(), bedPos, childName, level);

        // Finalize spawn
        level.addFreshEntity(child);
    }

    /**
     * Generates a name for the child, with 80% chance of using existing family name
     */
    private void generateChildName(Peasant_Entity child, RandomSource random) {
        // Get fresh list of citizen names from the bed warning system
        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInRadius(
                this.getBlockPos(), getCurrentScanRadius());

        // Extract last names from the citizen names
        List<String> availableLastNames = new ArrayList<>();
        for (String fullName : citizenNamesMap.values()) {
            if (fullName != null && !fullName.isEmpty() && !fullName.equals("Unknown")) {
                String lastName = extractLastName(fullName);
                if (!lastName.isEmpty() && !lastName.equals("Snow")) {
                    availableLastNames.add(lastName);
                }
            }
        }

        // First generate a random name using the NameSystem to get a proper first name
        child.getNameSystem().generateRandomName(random);
        String originalName = child.getDisplayName().getString();
        String firstName = extractFirstName(originalName);

        String lastName;

        // 80% chance to use existing family name, 20% chance for new name
        if (!availableLastNames.isEmpty() && random.nextDouble() < FAMILY_NAME_CHANCE) {
            lastName = availableLastNames.get(random.nextInt(availableLastNames.size()));
        } else {
            // Use the last name from the generated name
            lastName = extractLastName(originalName);
        }

        // Combine the NameSystem-generated first name with the chosen last name
        String fullName = firstName + " " + lastName;

        child.setCustomName(Component.literal(fullName));
        child.setCustomNameVisible(false);
    }

    /**
     * Extracts first name from full name string
     */
    private String extractFirstName(String fullName) {
        if (fullName == null || fullName.isEmpty()) return "Jon";

        String[] parts = fullName.split(" ");
        return parts[0]; // First part is always the first name
    }

    /**
     * Extracts last name from full name string
     */
    private String extractLastName(String fullName) {
        if (fullName == null || fullName.isEmpty()) return "Snow";

        String[] parts = fullName.split(" ");
        if (parts.length > 1) {
            return parts[parts.length - 1];
        }
        return "Snow";
    }

    /**
     * Updated citizen registry using the enhanced SimpleBedWarningSystem
     */
    private void updateCitizenRegistry(Level level) {
        int scanRadius = getCurrentScanRadius();

        // Get claimed beds and citizen names efficiently
        Set<BlockPos> homeBedClaims = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                this.getBlockPos(), scanRadius, null);

        // Get all citizen names from the warning system for caching
        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInRadius(
                this.getBlockPos(), scanRadius);

        // Extract and cache last names for display purposes
        citizenLastNames.clear();
        for (String fullName : citizenNamesMap.values()) {
            if (fullName != null && !fullName.isEmpty() && !fullName.equals("Unknown")) {
                String lastName = extractLastName(fullName);
                if (!lastName.isEmpty() && !lastName.equals("Snow")) {
                    citizenLastNames.add(lastName);
                }
            }
        }

        // Count citizens within our stable scan area
        int newCitizenCount = 0;
        for (BlockPos claimedBedPos : homeBedClaims) {
            if (isWithinStableScanArea(claimedBedPos, scanRadius)) {
                newCitizenCount++;
            }
        }

        // Only update if the count actually changed
        if (newCitizenCount != citizenCount) {
            citizenCount = newCitizenCount;
            sendDataToClients(level);
            setChanged();
        }
    }

    /**
     * Helper method to check if a bed is within a specific radius
     */
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
        tag.putLong("LastChildSpawnDay", lastChildSpawnDay); // Save child spawn day
        tag.putBoolean("HasScannedToday", hasScannedToday);
        tag.putInt("TicksSinceLastRegister", ticksSinceLastRegister);
        tag.putInt("TicksSinceLastCitizenCheck", ticksSinceLastCitizenCheck);

        // Save citizen last names
        if (!citizenLastNames.isEmpty()) {
            tag.putInt("CitizenLastNamesCount", citizenLastNames.size());
            for (int i = 0; i < citizenLastNames.size(); i++) {
                tag.putString("CitizenLastName_" + i, citizenLastNames.get(i));
            }
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        bedCount = tag.getInt("BedCount");
        citizenCount = tag.getInt("CitizenCount");
        townName = tag.getString("TownName");
        if (townName.isEmpty()) {
            townName = "Unnamed Town";
        }
        lastScanDay = tag.getLong("LastScanDay");
        lastChildSpawnDay = tag.getLong("LastChildSpawnDay"); // Load child spawn day
        hasScannedToday = tag.getBoolean("HasScannedToday");
        ticksSinceLastRegister = tag.getInt("TicksSinceLastRegister");
        ticksSinceLastCitizenCheck = tag.getInt("TicksSinceLastCitizenCheck");

        // Load citizen last names
        citizenLastNames.clear();
        if (tag.contains("CitizenLastNamesCount")) {
            int count = tag.getInt("CitizenLastNamesCount");
            for (int i = 0; i < count; i++) {
                String lastName = tag.getString("CitizenLastName_" + i);
                if (!lastName.isEmpty()) {
                    citizenLastNames.add(lastName);
                }
            }
        }
    }
}