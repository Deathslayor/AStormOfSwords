package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobWarningSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelResource;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.SimpleBedWarningSystem;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class TownHallBlockEntity extends BlockEntity {

    private static final int SCAN_TIME = 10000; // Time of day to perform scan
    private static final int CHILD_SPAWN_TIME = 18000; // Time of day to spawn children (midnight)
    private static final int SCAN_HEIGHT = 384; // Vertical radius (up and down)
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

    private static final int JOB_ASSIGNMENT_INTERVAL = 80; // 4 seconds (assign one job every 4 seconds)
    private static final int JOBLESS_UPDATE_INTERVAL = 120; // 6 seconds (update jobless queue)
    private static final int JOB_CLEANUP_INTERVAL = 400;
    private int ticksSinceLastJobAssignment = 0;
    private int ticksSinceLastJoblessUpdate = 0;
    private int ticksSinceLastJobCleanup = 0;//
    private boolean shouldSendDataUpdate = false;
    private int availableJobCount = 0;
    private int assignedJobCount = 0;

    private int bedCount = 0;
    private int citizenCount = 0;
    private String townName = "Unnamed Town"; // Default town name
    private long lastScanDay = -1;
    private long lastChildSpawnDay = -1; // Track last day children were spawned
    private boolean hasScannedToday = false;
    private int ticksSinceLastRegister = 0;
    private int ticksSinceLastCitizenCheck = 0;

    // Updated fields for dynamic house name system
    private boolean isClaimed = false;
    private UUID claimedByPlayerUUID = null; // NEW: Track which player claimed it

    // Cache for citizen last names (refreshed during citizen checks)
    private List<String> citizenLastNames = new ArrayList<>();

    public int getAvailableJobCount() {
        return availableJobCount;
    }
    public int getAssignedJobCount() {
        return assignedJobCount;
    }
    public int getTotalJobCount() {
        return availableJobs.size();
    }
    public int getJoblessCount() {
        return joblessQueue.size();
    }
    public List<CitizenInfo> getRegisteredCitizens() {
        return new ArrayList<>(registeredCitizens.values());
    }
    public List<JobBlockInfo> getAvailableJobs() {
        return new ArrayList<>(availableJobs.values());
    }


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

    public boolean isClaimed() {
        return isClaimed;
    }

    // Updated getClaimedByHouse to be dynamic
    public String getClaimedByHouse() {
        if (!isClaimed || claimedByPlayerUUID == null) {
            return "";
        }

        // Get the current house name of the claiming player
        if (level != null && level.getServer() != null) {
            ServerPlayer player = level.getServer().getPlayerList().getPlayer(claimedByPlayerUUID);
            if (player != null) {
                // Player is online - get their current house name
                return getPlayerHouseName(player);
            } else {
                // Player is offline - load their data from file
                return getOfflinePlayerHouseName(claimedByPlayerUUID);
            }
        }

        return ""; // Fallback
    }

    // Add getter for player UUID
    public UUID getClaimedByPlayerUUID() {
        return claimedByPlayerUUID;
    }

    // Updated claimTownHall method
    public boolean claimTownHall(String houseName, UUID playerUUID) {
        if (!isClaimed && houseName != null && !houseName.trim().isEmpty() && playerUUID != null) {
            this.claimedByPlayerUUID = playerUUID;
            this.isClaimed = true;
            setChanged();

            // Send updated data to nearby players
            if (level != null) {
                sendDataToClients(level);
            }

            return true;
        }
        return false;
    }

    // Helper method to get house name from online player
    private String getPlayerHouseName(ServerPlayer player) {
        if (player.getPersistentData().contains("agotmod.house")) {
            CompoundTag houseTag = player.getPersistentData().getCompound("agotmod.house");
            if (houseTag.contains("house_name")) {
                return houseTag.getString("house_name");
            }
        }
        return "";
    }

    // Helper method to get house name from offline player
    private String getOfflinePlayerHouseName(UUID playerUUID) {
        try {
            Path worldPath = level.getServer().getWorldPath(LevelResource.ROOT);
            Path playerFile = worldPath.resolve("playerdata").resolve(playerUUID.toString() + ".dat");

            if (Files.exists(playerFile)) {
                CompoundTag playerData = NbtIo.readCompressed(
                        playerFile, NbtAccounter.unlimitedHeap());

                if (playerData.contains("agotmod.house")) {
                    CompoundTag houseTag = playerData.getCompound("agotmod.house");
                    if (houseTag.contains("house_name")) {
                        return houseTag.getString("house_name");
                    }
                }
            }
        } catch (Exception e) {
            // Failed to load offline player house name - return empty string
        }

        return "";
    }

    private final Map<UUID, CitizenInfo> registeredCitizens = new HashMap<>();
    private final Map<BlockPos, JobBlockInfo> availableJobs = new HashMap<>();
    private final Queue<UUID> joblessQueue = new LinkedList<>();

    public static class CitizenInfo {
        public final UUID npcUUID;
        public final BlockPos homeBedPos;
        public String currentJob;
        public String npcName;
        public long lastSeen;

        public CitizenInfo(UUID npcUUID, BlockPos homeBedPos, String npcName) {
            this.npcUUID = npcUUID;
            this.homeBedPos = homeBedPos;
            this.npcName = npcName;
            this.currentJob = JobSystem.JOB_NONE;
            this.lastSeen = System.currentTimeMillis();
        }
    }

    public static class JobBlockInfo {
        public final BlockPos jobBlockPos;
        public final String jobType;
        public boolean isOccupied;
        public UUID assignedNPC;

        public JobBlockInfo(BlockPos pos, String type) {
            this.jobBlockPos = pos;
            this.jobType = type;
            this.isOccupied = false;
            this.assignedNPC = null;
        }
    }

    // Add to TownHallBlockEntity
    private void performJobBlockScan(Level level) {
        availableJobs.clear();
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

        // Scan for job blocks
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos scanPos = new BlockPos(x, y, z);

                    if (level.isLoaded(scanPos)) {
                        BlockState state = level.getBlockState(scanPos);
                        String jobType = getJobTypeFromBlock(state);

                        if (!jobType.equals(JobSystem.JOB_NONE)) {
                            JobBlockInfo jobInfo = new JobBlockInfo(scanPos, jobType);

                            // Check if occupied via warning system
                            if (JobWarningSystem.isJobBlockWarned(scanPos, null)) {
                                JobWarningSystem.JobBlockWarning warning =
                                        JobWarningSystem.getWarningInfo(scanPos);
                                if (warning != null) {
                                    jobInfo.isOccupied = true;
                                    jobInfo.assignedNPC = warning.npcUUID;
                                }
                            }

                            availableJobs.put(scanPos, jobInfo);
                        }
                    }
                }
            }
        }

        // Update job counts
        availableJobCount = (int) availableJobs.values().stream().filter(job -> !job.isOccupied).count();
        assignedJobCount = (int) availableJobs.values().stream().filter(job -> job.isOccupied).count();
        shouldSendDataUpdate = true;
    }

    private String getJobTypeFromBlock(BlockState state) {
        if (state.is(ModBLocks.FARMER_BARREL.get())) return JobSystem.JOB_FARMER;
        if (state.is(ModBLocks.GROCER_BARREL.get())) return JobSystem.JOB_GROCER;
        if (state.is(ModBLocks.GUARD_BARREL.get())) return JobSystem.JOB_GUARD;
        if (state.is(ModBLocks.MINER_BARREL.get())) return JobSystem.JOB_MINER;
        return JobSystem.JOB_NONE;
    }


    // Add to TownHallBlockEntity
    private void processJobAssignments(ServerLevel level) {
        // Update citizen registry from bed warning system
        updateCitizenRegistryFromBeds();

        // Find unemployed citizens
        updateJoblessQueue();

        // Assign jobs to unemployed citizens
        assignJobsToUnemployed(level);
    }

    private void assignJobsToUnemployed(ServerLevel level) {
        if (joblessQueue.isEmpty()) return;

        // Find available jobs
        List<JobBlockInfo> availableJobsList = availableJobs.values().stream()
                .filter(job -> !job.isOccupied)
                .toList();

        if (availableJobsList.isEmpty()) return;

        // Assign one job per tick to prevent lag
        UUID unemployedNPC = joblessQueue.poll();
        if (unemployedNPC != null) {
            CitizenInfo citizen = registeredCitizens.get(unemployedNPC);
            if (citizen != null && citizen.currentJob.equals(JobSystem.JOB_NONE)) {

                // Find closest available job
                JobBlockInfo closestJob = findClosestJob(citizen.homeBedPos, availableJobsList);
                if (closestJob != null) {
                    assignJobToNPC(level, unemployedNPC, closestJob);
                }
            }
        }
    }

    private void assignJobToNPC(ServerLevel level, UUID npcUUID, JobBlockInfo jobInfo) {
        // Find the NPC entity
        Entity entity = level.getEntity(npcUUID);
        if (entity instanceof Peasant_Entity peasant && peasant.isAdult()) {
            // Send job assignment packet to NPC
            peasant.setJobType(jobInfo.jobType);
            peasant.setJobBlockPos(jobInfo.jobBlockPos);

            // Update our records
            CitizenInfo citizen = registeredCitizens.get(npcUUID);
            if (citizen != null) {
                citizen.currentJob = jobInfo.jobType;
            }

            jobInfo.isOccupied = true;
            jobInfo.assignedNPC = npcUUID;

            // The NPC will handle broadcasting the warning when it starts working
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TownHallBlockEntity blockEntity) {
        if (level.isClientSide()) return;

        long currentTime = level.getDayTime() % 24000;
        long currentDay = level.getDayTime() / 24000;

        // ===== DAILY SCANNING OPERATIONS =====
        // Check if it's time to scan (time = 10000) and we haven't scanned today
        if (currentTime >= SCAN_TIME &&
                (blockEntity.lastScanDay != currentDay || !blockEntity.hasScannedToday)) {

            // Perform bed scan (existing functionality)
            blockEntity.performBedScan(level);

            // NEW: Perform job block scan
            blockEntity.performJobBlockScan(level);

            blockEntity.lastScanDay = currentDay;
            blockEntity.hasScannedToday = true;
            blockEntity.setChanged();
        }

        // ===== CHILD SPAWNING =====
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

        // ===== CITIZEN MANAGEMENT =====
        // Update citizen registry from bed warning system (every 10 seconds)
        blockEntity.ticksSinceLastCitizenCheck++;
        if (blockEntity.ticksSinceLastCitizenCheck >= CITIZEN_CHECK_INTERVAL) {
            blockEntity.updateCitizenRegistry(level);
            blockEntity.updateCitizenRegistryFromBeds(); // NEW: Sync with bed claims
            blockEntity.ticksSinceLastCitizenCheck = 0;
        }

        // ===== JOB MANAGEMENT SYSTEM =====
        // Process job assignments (every 4 seconds to prevent lag)
        blockEntity.ticksSinceLastJobAssignment++;
        if (blockEntity.ticksSinceLastJobAssignment >= JOB_ASSIGNMENT_INTERVAL) {
            blockEntity.processJobAssignments((ServerLevel) level);
            blockEntity.ticksSinceLastJobAssignment = 0;
        }

        // Update jobless queue (every 6 seconds)
        blockEntity.ticksSinceLastJoblessUpdate++;
        if (blockEntity.ticksSinceLastJoblessUpdate >= JOBLESS_UPDATE_INTERVAL) {
            blockEntity.updateJoblessQueue();
            blockEntity.ticksSinceLastJoblessUpdate = 0;
        }

        // Clean up stale job assignments (every 20 seconds)
        blockEntity.ticksSinceLastJobCleanup++;
        if (blockEntity.ticksSinceLastJobCleanup >= JOB_CLEANUP_INTERVAL) {
            blockEntity.cleanupStaleJobAssignments((ServerLevel) level);
            blockEntity.ticksSinceLastJobCleanup = 0;
        }

        // ===== NETWORKING =====
        // Register with nearby players (every 5 seconds)
        blockEntity.ticksSinceLastRegister++;
        if (blockEntity.ticksSinceLastRegister >= REGISTER_INTERVAL) {
            blockEntity.registerWithNearbyPlayers();
            blockEntity.ticksSinceLastRegister = 0;
        }

        // Send data updates when citizen or job counts change
        if (blockEntity.shouldSendDataUpdate) {
            blockEntity.sendDataToClients(level);
            blockEntity.shouldSendDataUpdate = false;
        }
    }

    private void updateCitizenRegistryFromBeds() {
        int scanRadius = getCurrentScanRadius();

        // Get all home bed claims in the area
        Set<BlockPos> homeBedClaims = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                this.getBlockPos(), scanRadius, null);

        // Get citizen names from the bed warning system
        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInRadius(
                this.getBlockPos(), scanRadius);

        // Track which citizens we found this update
        Set<UUID> foundCitizens = new HashSet<>();

        // Process each home bed claim
        for (BlockPos bedPos : homeBedClaims) {
            SimpleBedWarningSystem.HomeBedClaim claim =
                    SimpleBedWarningSystem.getClaimForPosition(bedPos);

            if (claim != null && !claim.isExpired()) {
                UUID npcUUID = claim.npcUUID;
                foundCitizens.add(npcUUID);

                // Get current name from the map
                String npcName = citizenNamesMap.getOrDefault(npcUUID, claim.getCurrentName());

                // Determine if NPC is adult by checking if they have a job or trying to find them
                boolean isAdult = true; // Default assumption
                if (claim.level != null && !claim.level.isClientSide()) {
                    Entity entity = ((ServerLevel) claim.level).getEntity(npcUUID);
                    if (entity instanceof Peasant_Entity peasant) {
                        isAdult = peasant.isAdult();
                    }
                }

                if (registeredCitizens.containsKey(npcUUID)) {
                    // Update existing citizen info
                    CitizenInfo citizen = registeredCitizens.get(npcUUID);
                    citizen.npcName = npcName;
                    citizen.lastSeen = System.currentTimeMillis();

                    // Update their job status if we can find the entity
                    if (claim.level != null && !claim.level.isClientSide()) {
                        Entity entity = ((ServerLevel) claim.level).getEntity(npcUUID);
                        if (entity instanceof Peasant_Entity peasant) {
                            citizen.currentJob = peasant.getJobType();
                        }
                    }
                } else {
                    // Register new citizen
                    CitizenInfo newCitizen = new CitizenInfo(npcUUID, bedPos, npcName);

                    // Get their current job if we can find the entity
                    if (claim.level != null && !claim.level.isClientSide()) {
                        Entity entity = ((ServerLevel) claim.level).getEntity(npcUUID);
                        if (entity instanceof Peasant_Entity peasant) {
                            newCitizen.currentJob = peasant.getJobType();
                        }
                    }

                    registeredCitizens.put(npcUUID, newCitizen);
                    shouldSendDataUpdate = true;
                }
            }
        }

        // Remove citizens who no longer have bed claims (they moved or were removed)
        registeredCitizens.entrySet().removeIf(entry -> {
            if (!foundCitizens.contains(entry.getKey()) &&
                    System.currentTimeMillis() - entry.getValue().lastSeen > 300000) { // 5 minutes
                shouldSendDataUpdate = true;
                return true;
            }
            return false;
        });
    }

    /**
     * Updates the queue of jobless citizens
     */
    private void updateJoblessQueue() {
        joblessQueue.clear();

        for (CitizenInfo citizen : registeredCitizens.values()) {
            // Only adults can have jobs
            if (citizen.currentJob.equals(JobSystem.JOB_NONE)) {
                // Double-check by looking up the entity if possible
                if (level != null && !level.isClientSide()) {
                    Entity entity = ((ServerLevel) level).getEntity(citizen.npcUUID);
                    if (entity instanceof Peasant_Entity peasant && peasant.isAdult()) {
                        joblessQueue.offer(citizen.npcUUID);
                    }
                } else {
                    // Fallback if we can't find the entity
                    joblessQueue.offer(citizen.npcUUID);
                }
            }
        }
    }

    /**
     * Finds the closest job to a given position
     */
    private JobBlockInfo findClosestJob(BlockPos homeBedPos, List<JobBlockInfo> availableJobs) {
        if (availableJobs.isEmpty()) return null;

        JobBlockInfo closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (JobBlockInfo job : availableJobs) {
            double distance = homeBedPos.distSqr(job.jobBlockPos);
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = job;
            }
        }

        return closest;
    }

    /**
     * Cleans up stale job assignments
     */
    private void cleanupStaleJobAssignments(ServerLevel level) {
        boolean hasChanges = false;

        for (JobBlockInfo job : availableJobs.values()) {
            if (job.isOccupied && job.assignedNPC != null) {
                // Check if the NPC still exists and has this job
                Entity entity = level.getEntity(job.assignedNPC);
                if (!(entity instanceof Peasant_Entity peasant) ||
                        !peasant.getJobBlockPos().equals(job.jobBlockPos) ||
                        !peasant.getJobType().equals(job.jobType)) {

                    // NPC is gone or has different job, clear the assignment
                    job.isOccupied = false;
                    job.assignedNPC = null;
                    hasChanges = true;

                    // Update citizen record if they exist
                    CitizenInfo citizen = registeredCitizens.get(job.assignedNPC);
                    if (citizen != null) {
                        citizen.currentJob = JobSystem.JOB_NONE;
                    }
                }
            }
        }

        if (hasChanges) {
            // Recalculate job counts
            availableJobCount = (int) availableJobs.values().stream().filter(job -> !job.isOccupied).count();
            assignedJobCount = (int) availableJobs.values().stream().filter(job -> job.isOccupied).count();
            shouldSendDataUpdate = true;
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
    public boolean isWithinStableScanArea(BlockPos pos, int radius) {
        BlockPos townHallPos = this.getBlockPos();
        int currentHeight = getCurrentScanHeight();

        int dx = Math.abs(pos.getX() - townHallPos.getX());
        int dy = Math.abs(pos.getY() - townHallPos.getY());
        int dz = Math.abs(pos.getZ() - townHallPos.getZ());

        return dx <= radius && dy <= currentHeight && dz <= radius;
    }

    public void triggerJobAssignment() {
        if (level != null && !level.isClientSide()) {
            processJobAssignments((ServerLevel) level);
        }
    }

    public String getDebugInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== TOWN HALL DEBUG INFO ===\n");
        sb.append("Town: ").append(townName).append("\n");
        sb.append("Citizens: ").append(citizenCount).append("\n");
        sb.append("Beds: ").append(bedCount).append("\n");
        sb.append("Scan Radius: ").append(getCurrentScanRadius()).append("\n");
        sb.append("Jobs - Total: ").append(getTotalJobCount())
                .append(", Available: ").append(availableJobCount)
                .append(", Assigned: ").append(assignedJobCount).append("\n");
        sb.append("Jobless Citizens: ").append(getJoblessCount()).append("\n");
        sb.append("Registered Citizens: ").append(registeredCitizens.size()).append("\n");

        if (isClaimed) {
            sb.append("Claimed by: ").append(getClaimedByHouse()).append("\n");
        } else {
            sb.append("Unclaimed\n");
        }

        return sb.toString();
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
            if (player instanceof ServerPlayer serverPlayer) {
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
            if (player instanceof ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < 64 * 64) { // Within 64 blocks for GUI
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallDataPacket(
                                    this.worldPosition,
                                    this.bedCount,
                                    this.citizenCount,
                                    getCurrentScanRadius(),
                                    this.townName,
                                    this.isClaimed,
                                    this.getClaimedByHouse(),
                                    this.getAvailableJobCount(),      // NEW
                                    this.getAssignedJobCount(),       // NEW
                                    this.getTotalJobCount(),          // NEW
                                    this.getJoblessCount()            // NEW
                            ));
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
            if (player instanceof ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < renderDistance * renderDistance) { // Within render distance
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallDataPacket(
                                    this.worldPosition,
                                    this.bedCount,
                                    this.citizenCount,
                                    getCurrentScanRadius(),
                                    this.townName,
                                    this.isClaimed,
                                    this.getClaimedByHouse(),
                                    this.getAvailableJobCount(),      // NEW
                                    this.getAssignedJobCount(),       // NEW
                                    this.getTotalJobCount(),          // NEW
                                    this.getJoblessCount()            // NEW
                            ));
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
                if (player instanceof ServerPlayer serverPlayer) {
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

        // ===== BASIC TOWN DATA =====
        tag.putInt("BedCount", bedCount);
        tag.putInt("CitizenCount", citizenCount);
        tag.putString("TownName", townName);

        // ===== TOWN CLAIMING DATA =====
        tag.putBoolean("IsClaimed", isClaimed);
        if (claimedByPlayerUUID != null) {
            tag.putUUID("ClaimedByPlayerUUID", claimedByPlayerUUID);
        }

        // ===== TIMING AND STATE DATA =====
        tag.putLong("LastScanDay", lastScanDay);
        tag.putLong("LastChildSpawnDay", lastChildSpawnDay);
        tag.putBoolean("HasScannedToday", hasScannedToday);

        // ===== TICK COUNTERS =====
        tag.putInt("TicksSinceLastRegister", ticksSinceLastRegister);
        tag.putInt("TicksSinceLastCitizenCheck", ticksSinceLastCitizenCheck);
        tag.putInt("TicksSinceLastJobAssignment", ticksSinceLastJobAssignment);
        tag.putInt("TicksSinceLastJoblessUpdate", ticksSinceLastJoblessUpdate);
        tag.putInt("TicksSinceLastJobCleanup", ticksSinceLastJobCleanup);

        // ===== JOB MANAGEMENT DATA =====
        tag.putInt("AvailableJobCount", availableJobCount);
        tag.putInt("AssignedJobCount", assignedJobCount);
        tag.putBoolean("ShouldSendDataUpdate", shouldSendDataUpdate);

        // ===== CITIZEN LAST NAMES CACHE =====
        if (!citizenLastNames.isEmpty()) {
            tag.putInt("CitizenLastNamesCount", citizenLastNames.size());
            for (int i = 0; i < citizenLastNames.size(); i++) {
                tag.putString("CitizenLastName_" + i, citizenLastNames.get(i));
            }
        }

        // ===== REGISTERED CITIZENS =====
        tag.putInt("RegisteredCitizensCount", registeredCitizens.size());
        int citizenIndex = 0;
        for (CitizenInfo citizen : registeredCitizens.values()) {
            CompoundTag citizenTag = new CompoundTag();
            citizenTag.putUUID("UUID", citizen.npcUUID);
            citizenTag.putLong("HomeBedPos", citizen.homeBedPos.asLong());
            citizenTag.putString("Name", citizen.npcName != null ? citizen.npcName : "");
            citizenTag.putString("Job", citizen.currentJob != null ? citizen.currentJob : JobSystem.JOB_NONE);
            citizenTag.putLong("LastSeen", citizen.lastSeen);
            tag.put("Citizen_" + citizenIndex, citizenTag);
            citizenIndex++;
        }

        // ===== AVAILABLE JOBS =====
        tag.putInt("AvailableJobsCount", availableJobs.size());
        int jobIndex = 0;
        for (JobBlockInfo job : availableJobs.values()) {
            CompoundTag jobTag = new CompoundTag();
            jobTag.putLong("JobBlockPos", job.jobBlockPos.asLong());
            jobTag.putString("JobType", job.jobType);
            jobTag.putBoolean("IsOccupied", job.isOccupied);
            if (job.assignedNPC != null) {
                jobTag.putUUID("AssignedNPC", job.assignedNPC);
            }
            tag.put("Job_" + jobIndex, jobTag);
            jobIndex++;
        }

        // ===== JOBLESS QUEUE =====
        List<UUID> joblessUUIDs = new ArrayList<>(joblessQueue);
        tag.putInt("JoblessQueueCount", joblessUUIDs.size());
        for (int i = 0; i < joblessUUIDs.size(); i++) {
            tag.putUUID("JoblessUUID_" + i, joblessUUIDs.get(i));
        }
    }

    // Updated loadAdditional method
    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        // ===== BASIC TOWN DATA =====
        bedCount = tag.getInt("BedCount");
        citizenCount = tag.getInt("CitizenCount");
        townName = tag.getString("TownName");
        if (townName.isEmpty()) {
            townName = "Unnamed Town";
        }

        // ===== TOWN CLAIMING DATA =====
        isClaimed = tag.getBoolean("IsClaimed");
        if (tag.hasUUID("ClaimedByPlayerUUID")) {
            claimedByPlayerUUID = tag.getUUID("ClaimedByPlayerUUID");
        } else {
            claimedByPlayerUUID = null;
        }

        // ===== TIMING AND STATE DATA =====
        lastScanDay = tag.getLong("LastScanDay");
        lastChildSpawnDay = tag.getLong("LastChildSpawnDay");
        hasScannedToday = tag.getBoolean("HasScannedToday");

        // ===== TICK COUNTERS =====
        ticksSinceLastRegister = tag.getInt("TicksSinceLastRegister");
        ticksSinceLastCitizenCheck = tag.getInt("TicksSinceLastCitizenCheck");
        ticksSinceLastJobAssignment = tag.getInt("TicksSinceLastJobAssignment");
        ticksSinceLastJoblessUpdate = tag.getInt("TicksSinceLastJoblessUpdate");
        ticksSinceLastJobCleanup = tag.getInt("TicksSinceLastJobCleanup");

        // ===== JOB MANAGEMENT DATA =====
        availableJobCount = tag.getInt("AvailableJobCount");
        assignedJobCount = tag.getInt("AssignedJobCount");
        shouldSendDataUpdate = tag.getBoolean("ShouldSendDataUpdate");

        // ===== CITIZEN LAST NAMES CACHE =====
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

        // ===== REGISTERED CITIZENS =====
        registeredCitizens.clear();
        if (tag.contains("RegisteredCitizensCount")) {
            int count = tag.getInt("RegisteredCitizensCount");
            for (int i = 0; i < count; i++) {
                if (tag.contains("Citizen_" + i)) {
                    CompoundTag citizenTag = tag.getCompound("Citizen_" + i);
                    try {
                        UUID uuid = citizenTag.getUUID("UUID");
                        BlockPos bedPos = BlockPos.of(citizenTag.getLong("HomeBedPos"));
                        String name = citizenTag.getString("Name");
                        String job = citizenTag.contains("Job") ? citizenTag.getString("Job") : JobSystem.JOB_NONE;
                        long lastSeen = citizenTag.contains("LastSeen") ? citizenTag.getLong("LastSeen") : System.currentTimeMillis();

                        CitizenInfo citizen = new CitizenInfo(uuid, bedPos, name.isEmpty() ? "Unknown" : name);
                        citizen.currentJob = job;
                        citizen.lastSeen = lastSeen;
                        registeredCitizens.put(uuid, citizen);
                    } catch (Exception e) {
                        // Skip corrupted citizen data
                        System.err.println("Failed to load citizen data at index " + i + ": " + e.getMessage());
                    }
                }
            }
        }

        // ===== AVAILABLE JOBS =====
        availableJobs.clear();
        if (tag.contains("AvailableJobsCount")) {
            int count = tag.getInt("AvailableJobsCount");
            for (int i = 0; i < count; i++) {
                if (tag.contains("Job_" + i)) {
                    CompoundTag jobTag = tag.getCompound("Job_" + i);
                    try {
                        BlockPos jobPos = BlockPos.of(jobTag.getLong("JobBlockPos"));
                        String jobType = jobTag.getString("JobType");
                        boolean isOccupied = jobTag.getBoolean("IsOccupied");

                        JobBlockInfo job = new JobBlockInfo(jobPos, jobType);
                        job.isOccupied = isOccupied;

                        if (jobTag.hasUUID("AssignedNPC")) {
                            job.assignedNPC = jobTag.getUUID("AssignedNPC");
                        }

                        availableJobs.put(jobPos, job);
                    } catch (Exception e) {
                        // Skip corrupted job data
                        System.err.println("Failed to load job data at index " + i + ": " + e.getMessage());
                    }
                }
            }
        }

        // ===== JOBLESS QUEUE =====
        joblessQueue.clear();
        if (tag.contains("JoblessQueueCount")) {
            int count = tag.getInt("JoblessQueueCount");
            for (int i = 0; i < count; i++) {
                if (tag.hasUUID("JoblessUUID_" + i)) {
                    try {
                        UUID joblessUUID = tag.getUUID("JoblessUUID_" + i);
                        joblessQueue.offer(joblessUUID);
                    } catch (Exception e) {
                        // Skip corrupted UUID data
                        System.err.println("Failed to load jobless UUID at index " + i + ": " + e.getMessage());
                    }
                }
            }
        }

        // ===== DATA VALIDATION AND CLEANUP =====
        // Validate job counts after loading
        if (availableJobs.size() > 0) {
            long actualAvailableCount = availableJobs.values().stream().filter(job -> !job.isOccupied).count();
            long actualAssignedCount = availableJobs.values().stream().filter(job -> job.isOccupied).count();

            // Update counts if they don't match (corruption recovery)
            if (actualAvailableCount != availableJobCount || actualAssignedCount != assignedJobCount) {
                availableJobCount = (int) actualAvailableCount;
                assignedJobCount = (int) actualAssignedCount;
                shouldSendDataUpdate = true;
            }
        }

        // Remove jobless queue entries that don't have corresponding citizen records
        joblessQueue.removeIf(uuid -> !registeredCitizens.containsKey(uuid));

        // Ensure data consistency - remove citizens with jobs that aren't tracked as jobless
        for (UUID uuid : joblessQueue) {
            CitizenInfo citizen = registeredCitizens.get(uuid);
            if (citizen != null && !citizen.currentJob.equals(JobSystem.JOB_NONE)) {
                // This citizen has a job but is in jobless queue - remove from queue
                joblessQueue.remove(uuid);
            }
        }
    }
}