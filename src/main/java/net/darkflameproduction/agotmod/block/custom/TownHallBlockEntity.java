package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder.AnimalHerderCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobWarningSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobClaims;
import net.darkflameproduction.agotmod.entity.custom.npc.system.blacksmith.BlacksmithInventoryTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherInventoryTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner.CharcoalBurnerCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.Culture;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.TownCultureZone;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmerDepositTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerInventoryTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.inventory.FoodCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.lumberjack.LumberjackDepositTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.smelter.SmelterCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorInventoryTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerInventoryTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterInventoryTicketSystem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelResource;
import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.darkflameproduction.agotmod.entity.custom.npc.Peasant_Entity;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.SimpleBedWarningSystem;
import java.util.Collections;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import org.jetbrains.annotations.Nullable;

public class TownHallBlockEntity extends BlockEntity implements
        GrocerInventoryTicketSystem.TicketListener,
        ButcherInventoryTicketSystem.TicketListener,
        TannerInventoryTicketSystem.TicketListener,
        TailorInventoryTicketSystem.TicketListener,
        BlacksmithInventoryTicketSystem.TicketListener,
        FarmerDepositTicketSystem.DepositListener,
        FoodCollectionTicketSystem.FoodCollectionListener,
        SmelterCollectionTicketSystem.SmelterCollectionListener,
        AnimalHerderCollectionTicketSystem.AnimalHerderCollectionListener,
        TannerCollectionTicketSystem.TannerCollectionListener,
        TailorCollectionTicketSystem.TailorCollectionListener,
        LumberjackDepositTicketSystem.DepositListener,
        CharcoalBurnerCollectionTicketSystem.CharcoalBurnerCollectionListener,
        CarpenterCollectionTicketSystem.CarpenterCollectionListener,
        CarpenterInventoryTicketSystem.TicketListener,
        net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.TicketListener {

    private static final int SCAN_TIME = 10000; // Time of day to perform scan
    private static final int CHILD_SPAWN_TIME = 18000; // Time of day to spawn children (midnight)
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
    private long lastTraderRestockDay = -1; // The day the town's trader last bought supplies (TraderPurchasing)
    private boolean hasScannedToday = false;
    private int ticksSinceLastRegister = 0;
    private int ticksSinceLastCitizenCheck = 0;

    // Updated fields for dynamic house name system
    private boolean isClaimed = false;
    private UUID claimedByPlayerUUID = null; // NEW: Track which player claimed it

    // ===== TOWN HALL SHARED INVENTORY =====
    private final Map<String, Long> townInventory = new HashMap<>();
    private long townBalance = 0;
    /** Earnings memory for the trader's budget (TraderPurchasing): the day being counted, its earnings so far, and the day before's. */
    private long earningsDay = -1;
    private long earnedToday = 0;
    private long earnedYesterday = 0;

    /** Each player's reputation with this town (TownReputation): -100 to 100, 5 from their first visit. */
    private final Map<UUID, Integer> playerReputation = new HashMap<>();
    private long townIncome = 0;

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

    /**
     * Positions of every known job block in town (used by guards to plan patrols).
     * Merges the daily job block scan with job blocks that working NPCs are currently
     * broadcasting, so blocks placed since the last scan are included too.
     * Blocks that are no longer job blocks are left out.
     */
    public List<BlockPos> getJobBlockPositions() {
        Set<BlockPos> positions = new LinkedHashSet<>(availableJobs.keySet());
        int radius = getCurrentScanRadius();
        for (JobWarningSystem.JobBlockWarning warning : JobWarningSystem.getActiveWarnings()) {
            if (isWithinStableScanArea(warning.jobBlockPos, radius)) {
                positions.add(warning.jobBlockPos);
            }
        }

        List<BlockPos> result = new ArrayList<>();
        for (BlockPos pos : positions) {
            if (level != null && level.isLoaded(pos)
                    && getJobTypeFromBlock(level.getBlockState(pos)).equals(JobSystem.JOB_NONE)) {
                continue; // Job block was removed
            }
            result.add(pos);
        }
        return result;
    }
    public int getJoblessCount() {
        return joblessQueue.size();
    }
    private Culture culture = Culture.NONE;

    public Culture getCulture() { return culture; }

    public void setCulture(Culture culture) {
        if (this.culture != Culture.NONE) return; // cannot change once set
        this.culture = culture;
        setChanged();
        if (level instanceof ServerLevel sl) {
            net.darkflameproduction.agotmod.entity.custom.npc.system.culture.TownCultureZone.registerZone(
                    this.worldPosition,
                    getCurrentScanRadius(),
                    culture,
                    sl.dimension()
            );
        }
        if (level != null) sendDataToClients(level);
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
     * The town area is a column: it spans the full build height of the world,
     * so it has no height or depth limit.
     */
    private static int getColumnMinY(Level level) {
        return level.getMinBuildHeight();
    }

    private static int getColumnMaxY(Level level) {
        return level.getMaxBuildHeight() - 1;
    }

    public boolean isClaimed() {
        return isClaimed;
    }

    public String getClaimedByHouse() {
        if (!isClaimed || claimedByPlayerUUID == null) return "";

        if (level != null && level.getServer() != null) {
            ServerPlayer player = level.getServer().getPlayerList().getPlayer(claimedByPlayerUUID);
            if (player != null) {
                return buildOwnerString(player.getGameProfile().getName(), getPlayerHouseName(player));
            } else {
                String[] data = getOfflinePlayerNameAndHouse(claimedByPlayerUUID);
                return buildOwnerString(data[0], data[1]);
            }
        }
        return "";
    }

    private String[] getOfflinePlayerNameAndHouse(UUID playerUUID) {
        try {
            Path worldPath  = level.getServer().getWorldPath(LevelResource.ROOT);
            Path playerFile = worldPath.resolve("playerdata").resolve(playerUUID + ".dat");

            if (Files.exists(playerFile)) {
                CompoundTag playerData = NbtIo.readCompressed(playerFile, NbtAccounter.unlimitedHeap());

                CompoundTag houseTag = null;
                if (playerData.contains("ForgeCaps")) {
                    CompoundTag caps = playerData.getCompound("ForgeCaps");
                    if (caps.contains("agotmod.house")) houseTag = caps.getCompound("agotmod.house");
                }
                if (houseTag == null && playerData.contains("agotmod.house")) {
                    houseTag = playerData.getCompound("agotmod.house");
                }

                String houseName = "";
                if (houseTag != null && houseTag.contains("house_name")) {
                    houseName = houseTag.getString("house_name");
                }

                String username = playerUUID.toString();
                if (level.getServer().getProfileCache() != null) {
                    com.mojang.authlib.GameProfile profile =
                            level.getServer().getProfileCache().get(playerUUID).orElse(null);
                    if (profile != null && profile.getName() != null) username = profile.getName();
                }

                return new String[]{ username, houseName };
            }
        } catch (Exception ignored) {}
        return new String[]{ playerUUID.toString(), "" };
    }

    private String buildOwnerString(String playerName, String houseName) {
        if (houseName == null || houseName.isEmpty()) return playerName;
        return playerName + " of House " + houseName;
    }

    // Add getter for player UUID
    public UUID getClaimedByPlayerUUID() {
        return claimedByPlayerUUID;
    }

    public boolean claimTownHall(String houseName, UUID playerUUID) {
        if (!isClaimed && houseName != null && !houseName.trim().isEmpty() && playerUUID != null) {
            this.claimedByPlayerUUID = playerUUID;
            this.isClaimed = true;
            setChanged();

            if (level instanceof ServerLevel sl) {
                // Resolve house UUID â€” may be null if player has no house yet
                ServerPlayer claimingPlayer = sl.getServer().getPlayerList().getPlayer(playerUUID);
                UUID houseUUID = claimingPlayer != null
                        ? net.darkflameproduction.agotmod.network.ServerHouseHandler
                        .getPlayerHouseUUID(claimingPlayer)
                        : resolveHouseUUIDFromPlayer(sl, playerUUID);

                // Register with houseUUID (null = unclaimed protection still applies)
                net.darkflameproduction.agotmod.entity.custom.npc.system.protection.TownProtectionZone
                        .registerZone(this.worldPosition, getCurrentScanRadius(), houseUUID, sl.dimension());
            }

            if (level != null) sendDataToClients(level);
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

        BlockPos centerPos = this.getBlockPos();
        int minX = centerPos.getX() - currentRadius;
        int maxX = centerPos.getX() + currentRadius;
        int minY = getColumnMinY(level);
        int maxY = getColumnMaxY(level);
        int minZ = centerPos.getZ() - currentRadius;
        int maxZ = centerPos.getZ() + currentRadius;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    BlockPos scanPos = new BlockPos(x, y, z);
                    if (!level.isLoaded(scanPos)) continue;

                    BlockState state = level.getBlockState(scanPos);
                    String jobType = getJobTypeFromBlock(state);
                    if (jobType.equals(JobSystem.JOB_NONE)) continue;

                    JobBlockInfo jobInfo = new JobBlockInfo(scanPos, jobType);

                    if (JobWarningSystem.isJobBlockWarned(scanPos, null)) {
                        JobWarningSystem.JobBlockWarning warning = JobWarningSystem.getWarningInfo(scanPos);
                        if (warning != null) {
                            jobInfo.isOccupied = true;
                            jobInfo.assignedNPC = warning.npcUUID;
                        }
                    }
                    // A saved claim counts even if its worker is asleep, far away or in an unloaded chunk
                    if (!jobInfo.isOccupied && level instanceof ServerLevel serverLevel) {
                        UUID owner = JobClaims.get(serverLevel).getOwner(serverLevel, scanPos);
                        if (owner != null) {
                            jobInfo.isOccupied = true;
                            jobInfo.assignedNPC = owner;
                        }
                    }

                    availableJobs.put(scanPos, jobInfo);
                }
            }
        }

        availableJobCount = (int) availableJobs.values().stream().filter(job -> !job.isOccupied).count();
        assignedJobCount  = (int) availableJobs.values().stream().filter(job -> job.isOccupied).count();
        shouldSendDataUpdate = true;
    }

    private String getJobTypeFromBlock(BlockState state) {
        if (state.is(ModBLocks.FARMER_BARREL.get()))          return JobSystem.JOB_FARMER;
        if (state.is(ModBLocks.GROCER_BARREL.get()))          return JobSystem.JOB_GROCER;
        if (state.is(ModBLocks.BUTCHER_BARREL.get()))         return JobSystem.JOB_BUTCHER;
        if (state.is(ModBLocks.TANNER_BARREL.get()))          return JobSystem.JOB_TANNER;
        if (state.is(ModBLocks.TAILOR_BARREL.get()))          return JobSystem.JOB_TAILOR;
        if (state.is(ModBLocks.BLACKSMITH_BARREL.get()))      return JobSystem.JOB_BLACKSMITH;
        if (state.is(ModBLocks.ARMORSMITH_BARREL.get()))      return JobSystem.JOB_ARMORSMITH;
        if (state.is(ModBLocks.SWORDSMITH_BARREL.get()))      return JobSystem.JOB_SWORDSMITH;
        if (state.is(ModBLocks.BAKER_BARREL.get()))           return JobSystem.JOB_BAKER;
        if (state.is(ModBLocks.STONEMASON_BARREL.get()))      return JobSystem.JOB_STONEMASON;
        if (state.is(ModBLocks.GUARD_BARREL.get()))           return JobSystem.JOB_GUARD;
        if (state.is(ModBLocks.MINER_BARREL.get()))           return JobSystem.JOB_MINER;
        if (state.is(ModBLocks.QUARRY_BARREL.get()))          return JobSystem.JOB_QUARRY;
        if (state.is(ModBLocks.SMELTER_BARREL.get()))         return JobSystem.JOB_SMELTER;
        if (state.is(ModBLocks.CATTLE_HERDER_BARREL.get()))   return JobSystem.JOB_CATTLE_HERDER;
        if (state.is(ModBLocks.CHICKEN_BREEDER_BARREL.get())) return JobSystem.JOB_CHICKEN_BREEDER;
        if (state.is(ModBLocks.PIG_BREEDER_BARREL.get()))     return JobSystem.JOB_PIG_BREEDER;
        if (state.is(ModBLocks.SHEEP_HERDER_BARREL.get()))    return JobSystem.JOB_SHEEP_HERDER;
        if (state.is(ModBLocks.LUMBERJACK_BARREL.get()))      return JobSystem.JOB_LUMBERJACK;
        if (state.is(ModBLocks.CHARCOAL_BURNER_BARREL.get())) return JobSystem.JOB_CHARCOAL_BURNER;
        if (state.is(ModBLocks.CARPENTER_BARREL.get()))       return JobSystem.JOB_CARPENTER;
        if (state.is(ModBLocks.TRADER_BARREL.get()))          return JobSystem.JOB_TRADER;
        return JobSystem.JOB_NONE;
    }

    @Override
    public void onLumberjackDepositPosted(LumberjackDepositTicketSystem.DepositTicket ticket) {
        // Paid for like every NPC's deposit (depositItems)
        Map<String, Integer> items = new HashMap<>();
        ticket.items.forEach((key, amount) -> items.put(key, (int) Math.min(Integer.MAX_VALUE, amount)));
        depositItems(ticket.lumberjackUUID, items);
        LumberjackDepositTicketSystem.consumeDeposit(ticket.lumberjackUUID);
        setChanged();
    }

    @Override
    public void onTraderRequestPosted(
            net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.InventoryRequestTicket ticket) {
        net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.postResponse(
                ticket.traderUUID,
                ticket.playerUUID,
                townInventory,
                townBalance,
                this.getBlockPos()
        );
        net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.consumeRequest(ticket.traderUUID);
    }

    @Override
    public void onBlacksmithRequestPosted(BlacksmithInventoryTicketSystem.InventoryRequestTicket ticket) {
        BlacksmithInventoryTicketSystem.postResponse(
                ticket.blacksmithUUID,
                ticket.playerUUID,
                townInventory,
                townBalance,
                this.getBlockPos()
        );
        BlacksmithInventoryTicketSystem.consumeRequest(ticket.blacksmithUUID);
    }

    @Override
    public void onTailorRequestPosted(TailorInventoryTicketSystem.InventoryRequestTicket ticket) {
        TailorInventoryTicketSystem.postResponse(
                ticket.tailorUUID,
                ticket.playerUUID,
                townInventory,
                townBalance,
                this.getBlockPos()
        );
        TailorInventoryTicketSystem.consumeRequest(ticket.tailorUUID);
    }

    @Override
    public void onCarpenterCollectionRequestPosted(CarpenterCollectionTicketSystem.CarpenterRequest request) {
        List<ItemStack> toGive = new ArrayList<>();

        // Pull logs â€” any item ending in _log, _wood, _stem, _hyphae (skip charred)
        int logsNeeded = net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem.LOGS_PER_CYCLE;
        int logsRemaining = logsNeeded;

        List<String> logKeys = townInventory.keySet().stream()
                .filter(key -> {
                    String path = key.contains(":") ? key.split(":")[1] : key;
                    if (path.startsWith("charred")) return false;
                    return path.endsWith("_log") || path.endsWith("_wood")
                            || path.endsWith("_stem") || path.endsWith("_hyphae");
                })
                .toList();

        for (String logKey : logKeys) {
            if (logsRemaining <= 0) break;
            long available = townInventory.getOrDefault(logKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(logsRemaining, available);
            takeForNpc(request.carpenterUUID, logKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(logKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
            logsRemaining -= toTake;
        }

        // Pull nails
        int nailsNeeded = net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem.NAILS_PER_CYCLE;
        long nailsAvailable = townInventory.getOrDefault("agotmod:nail", 0L);
        if (nailsAvailable > 0) {
            int toTake = (int) Math.min(nailsNeeded, nailsAvailable);
            takeForNpc(request.carpenterUUID, "agotmod:nail", toTake);
            ResourceLocation loc = ResourceLocation.tryParse("agotmod:nail");
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
        }

        // Pull sticks
        int sticksNeeded = net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem.STICKS_PER_CYCLE;
        long sticksAvailable = townInventory.getOrDefault("minecraft:stick", 0L);
        if (sticksAvailable > 0) {
            int toTake = (int) Math.min(sticksNeeded, sticksAvailable);
            takeForNpc(request.carpenterUUID, "minecraft:stick", toTake);
            ResourceLocation loc = ResourceLocation.tryParse("minecraft:stick");
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
        }

        CarpenterCollectionTicketSystem.postResponse(request.carpenterUUID, toGive);
        setChanged();
    }

    @Override
    public void onCarpenterRequestPosted(CarpenterInventoryTicketSystem.InventoryRequestTicket ticket) {
        CarpenterInventoryTicketSystem.postResponse(
                ticket.carpenterUUID,
                ticket.playerUUID,
                townInventory,
                townBalance,
                this.getBlockPos()
        );
        CarpenterInventoryTicketSystem.consumeRequest(ticket.carpenterUUID);
    }

    @Override
    public void onTailorCollectionRequestPosted(TailorCollectionTicketSystem.TailorRequest request) {
        List<ItemStack> toGive = new ArrayList<>();
        int flowersToTake = 64;
        int taken = 0;

        List<String> allFlowerKeys = new ArrayList<>();
        // Mod flowers
        allFlowerKeys.add("agotmod:winter_rose"); allFlowerKeys.add("agotmod:wild_radish");
        allFlowerKeys.add("agotmod:white_rose");  allFlowerKeys.add("agotmod:thorn_bush");
        allFlowerKeys.add("agotmod:thistle");     allFlowerKeys.add("agotmod:tansy");
        allFlowerKeys.add("agotmod:spiceflower"); allFlowerKeys.add("agotmod:sedge");
        allFlowerKeys.add("agotmod:saffron_crocus"); allFlowerKeys.add("agotmod:rose");
        allFlowerKeys.add("agotmod:poison_kisses"); allFlowerKeys.add("agotmod:pennyroyal");
        allFlowerKeys.add("agotmod:opium_poppy_wild"); allFlowerKeys.add("agotmod:nightshade");
        allFlowerKeys.add("agotmod:moonbloom");   allFlowerKeys.add("agotmod:lungwort");
        allFlowerKeys.add("agotmod:liverwort");   allFlowerKeys.add("agotmod:lavender");
        allFlowerKeys.add("agotmod:ladys_lace");  allFlowerKeys.add("agotmod:gorse");
        allFlowerKeys.add("agotmod:goldenrod");   allFlowerKeys.add("agotmod:goldencup");
        allFlowerKeys.add("agotmod:goathead");    allFlowerKeys.add("agotmod:ginger");
        allFlowerKeys.add("agotmod:gillyflower"); allFlowerKeys.add("agotmod:frostfire");
        allFlowerKeys.add("agotmod:forget_me_not"); allFlowerKeys.add("agotmod:evening_star");
        allFlowerKeys.add("agotmod:dusky_rose");  allFlowerKeys.add("agotmod:dragons_breath");
        allFlowerKeys.add("agotmod:coldsnap");    allFlowerKeys.add("agotmod:blue_rose");
        allFlowerKeys.add("agotmod:bloodbloom");  allFlowerKeys.add("agotmod:black_lotus");
        allFlowerKeys.add("agotmod:winter_rose_bush"); allFlowerKeys.add("agotmod:white_rose_bush");
        allFlowerKeys.add("agotmod:dusky_rose_bush"); allFlowerKeys.add("agotmod:blue_rose_bush");
        allFlowerKeys.add("agotmod:red_rose_bush");
        // Vanilla flowers
        allFlowerKeys.add("minecraft:poppy");     allFlowerKeys.add("minecraft:dandelion");
        allFlowerKeys.add("minecraft:blue_orchid"); allFlowerKeys.add("minecraft:allium");
        allFlowerKeys.add("minecraft:azure_bluet"); allFlowerKeys.add("minecraft:red_tulip");
        allFlowerKeys.add("minecraft:orange_tulip"); allFlowerKeys.add("minecraft:white_tulip");
        allFlowerKeys.add("minecraft:pink_tulip"); allFlowerKeys.add("minecraft:oxeye_daisy");
        allFlowerKeys.add("minecraft:cornflower"); allFlowerKeys.add("minecraft:lily_of_the_valley");
        allFlowerKeys.add("minecraft:wither_rose"); allFlowerKeys.add("minecraft:sunflower");
        allFlowerKeys.add("minecraft:lilac");     allFlowerKeys.add("minecraft:rose_bush");
        allFlowerKeys.add("minecraft:peony");

        // Pull up to 64 flowers total
        for (String flowerKey : allFlowerKeys) {
            if (taken >= flowersToTake) break;
            long available = townInventory.getOrDefault(flowerKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(flowersToTake - taken, available);
            takeForNpc(request.tailorUUID, flowerKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(flowerKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) {
                    toGive.add(new ItemStack(item, toTake));
                }
            }
            taken += toTake;
        }

        // Pull up to 64 of each textile material
        for (String matKey : new String[]{
                "minecraft:string",
                "minecraft:white_wool",
                "minecraft:leather"
        }) {
            long available = townInventory.getOrDefault(matKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(64, available);
            takeForNpc(request.tailorUUID, matKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(matKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
        }

        TailorCollectionTicketSystem.postResponse(request.tailorUUID, toGive);
        setChanged();
    }

    @Override
    public void onTannerRequestPosted(TannerInventoryTicketSystem.InventoryRequestTicket ticket) {
        TannerInventoryTicketSystem.postResponse(
                ticket.tannerUUID,
                ticket.playerUUID,
                townInventory,
                townBalance,
                this.getBlockPos()
        );
        TannerInventoryTicketSystem.consumeRequest(ticket.tannerUUID);
    }

    @Override
    public void onTannerCollectionRequestPosted(TannerCollectionTicketSystem.TannerRequest request) {
        List<ItemStack> toGive = new ArrayList<>();

        // Pull up to 64 rabbit hides
        long hidesAvailable = townInventory.getOrDefault("minecraft:rabbit_hide", 0L);
        if (hidesAvailable > 0) {
            int toTake = (int) Math.min(64, hidesAvailable);
            takeForNpc(request.tannerUUID, "minecraft:rabbit_hide", toTake);
            ResourceLocation hideLoc = ResourceLocation.tryParse("minecraft:rabbit_hide");
            if (hideLoc != null) {
                net.minecraft.world.item.Item hideItem = BuiltInRegistries.ITEM.get(hideLoc);
                if (hideItem != null && hideItem != Items.AIR) {
                    toGive.add(new ItemStack(hideItem, toTake));
                }
            }
        }

        // Pull up to 42 leather
        long leatherAvailable = townInventory.getOrDefault("minecraft:leather", 0L);
        if (leatherAvailable > 0) {
            int toTake = (int) Math.min(42, leatherAvailable);
            takeForNpc(request.tannerUUID, "minecraft:leather", toTake);
            ResourceLocation leatherLoc = ResourceLocation.tryParse("minecraft:leather");
            if (leatherLoc != null) {
                net.minecraft.world.item.Item leatherItem = BuiltInRegistries.ITEM.get(leatherLoc);
                if (leatherItem != null && leatherItem != Items.AIR) {
                    toGive.add(new ItemStack(leatherItem, toTake));
                }
            }
        }

        // Pull up to 10 string
        long stringAvailable = townInventory.getOrDefault("minecraft:string", 0L);
        if (stringAvailable > 0) {
            int toTake = (int) Math.min(10, stringAvailable);
            takeForNpc(request.tannerUUID, "minecraft:string", toTake);
            ResourceLocation stringLoc = ResourceLocation.tryParse("minecraft:string");
            if (stringLoc != null) {
                net.minecraft.world.item.Item stringItem = BuiltInRegistries.ITEM.get(stringLoc);
                if (stringItem != null && stringItem != Items.AIR) {
                    toGive.add(new ItemStack(stringItem, toTake));
                }
            }
        }

        TannerCollectionTicketSystem.postResponse(request.tannerUUID, toGive);
        setChanged();
    }

    @Override
    public void onCharcoalBurnerRequestPosted(CharcoalBurnerCollectionTicketSystem.CharcoalBurnerRequestTicket ticket) {
        int logsNeeded = ticket.logsNeeded;
        List<ItemStack> toGive = new ArrayList<>();
        int remaining = logsNeeded;

        // Pull logs from town inventory â€” any item ending in _log, _wood, _stem, _hyphae
        // except charred variants
        List<String> logKeys = townInventory.keySet().stream()
                .filter(key -> {
                    String path = key.contains(":") ? key.split(":")[1] : key;
                    if (path.startsWith("charred")) return false; // skip charred logs
                    return path.endsWith("_log") || path.endsWith("_wood")
                            || path.endsWith("_stem") || path.endsWith("_hyphae");
                })
                .toList();

        for (String logKey : logKeys) {
            if (remaining <= 0) break;
            long available = townInventory.getOrDefault(logKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(remaining, available);
            takeForNpc(ticket.charcoalBurnerUUID, logKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(logKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
            remaining -= toTake;
        }

        CharcoalBurnerCollectionTicketSystem.postResponse(ticket.charcoalBurnerUUID, toGive);
        setChanged();
    }



    @Override
    public void onSmelterRequestPosted(SmelterCollectionTicketSystem.SmelterRequestTicket ticket) {
        int coalNeeded = ticket.coalNeeded;
        List<ItemStack> toGive = new ArrayList<>();

        // Pull coal (regular then charcoal) up to coalNeeded
        int coalRemaining = coalNeeded;
        for (String coalKey : new String[]{"minecraft:coal", "minecraft:charcoal"}) {
            if (coalRemaining <= 0) break;
            long available = townInventory.getOrDefault(coalKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(coalRemaining, available);
            takeForNpc(ticket.smelterUUID, coalKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(coalKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
            coalRemaining -= toTake;
        }

        int coalActuallyTaken = coalNeeded - coalRemaining;
        int oreCapacity = coalActuallyTaken * 8;

        // Pull ores of the ingots the town is short of first, then gold > silver > iron > copper > tin
        int oreRemaining = oreCapacity;
        for (String oreKey : net.darkflameproduction.agotmod.entity.custom.npc.system.supply.SupplyChain.oresByNeed(
                townInventory, key -> 0)) {
            if (oreRemaining <= 0) break;
            long available = townInventory.getOrDefault(oreKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(oreRemaining, available);
            takeForNpc(ticket.smelterUUID, oreKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(oreKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
            oreRemaining -= toTake;
        }

        SmelterCollectionTicketSystem.postResponse(ticket.smelterUUID, toGive);
        setChanged();
    }

    @Override
    public void onAnimalHerderRequestPosted(AnimalHerderCollectionTicketSystem.AnimalHerderRequestTicket ticket) {
        String breedingItemKey = switch (ticket.jobType) {
            case JobSystem.JOB_CATTLE_HERDER   -> "minecraft:wheat";
            case JobSystem.JOB_CHICKEN_BREEDER -> "minecraft:wheat_seeds";
            case JobSystem.JOB_PIG_BREEDER     -> "minecraft:carrot";
            case JobSystem.JOB_SHEEP_HERDER    -> "minecraft:wheat";
            default -> null;
        };

        if (breedingItemKey == null) {
            AnimalHerderCollectionTicketSystem.postResponse(ticket.peasantUUID, new ArrayList<>());
            return;
        }

        List<ItemStack> toGive = new ArrayList<>();
        long available = townInventory.getOrDefault(breedingItemKey, 0L);

        if (available > 0) {
            int toTake = (int) Math.min(ticket.amountNeeded, available);
            takeForNpc(ticket.peasantUUID, breedingItemKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(breedingItemKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
        }

        // The cattle herder also gets empty buckets to milk with - only as many as the town is short of milk
        if (JobSystem.JOB_CATTLE_HERDER.equals(ticket.jobType) && level instanceof ServerLevel serverLevel
                && serverLevel.getEntity(ticket.peasantUUID) instanceof Peasant_Entity herder) {
            int buckets = net.darkflameproduction.agotmod.entity.custom.npc.system.supply.Milking.bucketsToHandOut(this, herder);
            if (buckets > 0 && takeForNpc(ticket.peasantUUID, "minecraft:bucket", buckets)) {
                for (int i = 0; i < buckets; i++) toGive.add(new ItemStack(Items.BUCKET));
            }
        }

        AnimalHerderCollectionTicketSystem.postResponse(ticket.peasantUUID, toGive);
        setChanged();
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
        Entity entity = level.getEntity(npcUUID);

        if (entity instanceof Peasant_Entity peasant && peasant.isAdult()) {
            // Never give a second job to someone who already has one
            if (peasant.hasJob()) {
                CitizenInfo citizen = registeredCitizens.get(npcUUID);
                if (citizen != null) citizen.currentJob = peasant.getJobType();
                return;
            }
            // The job block must be free - claim it first, or leave it with whoever holds it
            JobClaims claims = JobClaims.get(level);
            if (!claims.tryClaim(level, jobInfo.jobBlockPos, npcUUID)) {
                jobInfo.isOccupied = true;
                jobInfo.assignedNPC = claims.getOwner(level, jobInfo.jobBlockPos);
                return;
            }

            peasant.setJobType(jobInfo.jobType);
            peasant.setJobBlockPos(jobInfo.jobBlockPos);

            CitizenInfo citizen = registeredCitizens.get(npcUUID);
            if (citizen != null) citizen.currentJob = jobInfo.jobType;

            jobInfo.isOccupied = true;
            jobInfo.assignedNPC = npcUUID;
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TownHallBlockEntity blockEntity) {
        if (level.isClientSide()) return;

        long currentTime = level.getDayTime() % 24000;
        long currentDay  = level.getDayTime() / 24000;

        if (currentTime >= SCAN_TIME &&
                (blockEntity.lastScanDay != currentDay || !blockEntity.hasScannedToday)) {

            blockEntity.performBedScan(level);
            blockEntity.performJobBlockScan(level);
            FarmerDepositTicketSystem.cleanupStaleTickets(currentDay);
            SmelterCollectionTicketSystem.cleanupStaleTickets(currentDay);
            AnimalHerderCollectionTicketSystem.cleanupStaleTickets(currentDay);
            TannerCollectionTicketSystem.cleanupStaleTickets(currentDay);
            TailorCollectionTicketSystem.cleanupStaleTickets(currentDay);
            LumberjackDepositTicketSystem.cleanupStaleTickets(currentDay);
            CharcoalBurnerCollectionTicketSystem.cleanupStaleTickets(currentDay);
            CarpenterCollectionTicketSystem.cleanupStaleTickets(currentDay);



            blockEntity.lastScanDay     = currentDay;
            blockEntity.hasScannedToday = true;
            blockEntity.setChanged();
        }

        if (currentTime >= CHILD_SPAWN_TIME && blockEntity.lastChildSpawnDay != currentDay) {
            blockEntity.attemptChildSpawning((ServerLevel) level);
            blockEntity.lastChildSpawnDay = currentDay;
            blockEntity.setChanged();
        }

        if (currentTime < SCAN_TIME && blockEntity.hasScannedToday) {
            blockEntity.hasScannedToday = false;
        }

        blockEntity.ticksSinceLastCitizenCheck++;
        if (blockEntity.ticksSinceLastCitizenCheck >= CITIZEN_CHECK_INTERVAL) {
            blockEntity.updateCitizenRegistry(level);
            blockEntity.updateCitizenRegistryFromBeds();
            blockEntity.processFarmerDepositTickets();
            blockEntity.ticksSinceLastCitizenCheck = 0;
        }

        blockEntity.ticksSinceLastJobAssignment++;
        if (blockEntity.ticksSinceLastJobAssignment >= JOB_ASSIGNMENT_INTERVAL) {
            blockEntity.processJobAssignments((ServerLevel) level);
            blockEntity.ticksSinceLastJobAssignment = 0;
        }

        blockEntity.ticksSinceLastJoblessUpdate++;
        if (blockEntity.ticksSinceLastJoblessUpdate >= JOBLESS_UPDATE_INTERVAL) {
            blockEntity.updateJoblessQueue();
            blockEntity.ticksSinceLastJoblessUpdate = 0;
        }

        blockEntity.ticksSinceLastJobCleanup++;
        if (blockEntity.ticksSinceLastJobCleanup >= JOB_CLEANUP_INTERVAL) {
            blockEntity.cleanupStaleJobAssignments((ServerLevel) level);
            blockEntity.ticksSinceLastJobCleanup = 0;
        }

        blockEntity.ticksSinceLastRegister++;
        if (blockEntity.ticksSinceLastRegister >= REGISTER_INTERVAL) {
            blockEntity.registerWithNearbyPlayers();
            blockEntity.ticksSinceLastRegister = 0;
        }

        if (blockEntity.shouldSendDataUpdate) {
            blockEntity.sendDataToClients(level);
            blockEntity.shouldSendDataUpdate = false;
        }

        // Settle the day that ended (its tax) as a new one starts, and keep the town hall screen current for players at the
        // town hall (stores, crafting needs...)
        if (level.getGameTime() % SCREEN_REFRESH_INTERVAL == 0) {
            blockEntity.settleDay();
            blockEntity.refreshNearbyScreens(level);
        }
    }

    private static final int SCREEN_REFRESH_INTERVAL = 100; // 5 seconds
    private static final double SCREEN_REFRESH_RANGE = 8.0D;

    /** Sends fresh town data to players close enough to have the town hall screen open. */
    private void refreshNearbyScreens(Level level) {
        net.darkflameproduction.agotmod.network.TownHallDataPacket packet = null;
        for (net.minecraft.world.entity.player.Player player : level.players()) {
            if (!(player instanceof ServerPlayer serverPlayer)) continue;
            if (serverPlayer.distanceToSqr(worldPosition.getX() + 0.5, worldPosition.getY() + 0.5, worldPosition.getZ() + 0.5)
                    > SCREEN_REFRESH_RANGE * SCREEN_REFRESH_RANGE) continue;
            if (packet == null) {
                packet = new net.darkflameproduction.agotmod.network.TownHallDataPacket(
                        worldPosition, bedCount, citizenCount, getCurrentScanRadius(), townName, isClaimed,
                        getClaimedByHouse(), getAvailableJobCount(), getAssignedJobCount(), getTotalJobCount(),
                        getJoblessCount(), townBalance, townIncome, new java.util.HashMap<>(townInventory),
                        culture.name(), getCitizenEntries(), getCraftingNeeds(), getEarnings());
            }
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer, packet);
        }
    }

    private void updateCitizenRegistryFromBeds() {
        int scanRadius = getCurrentScanRadius();

        Set<BlockPos> homeBedClaims = SimpleBedWarningSystem.getHomeBedClaimsInColumn(
                this.getBlockPos(), scanRadius, null);

        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInColumn(
                this.getBlockPos(), scanRadius);

        Set<UUID> foundCitizens = new HashSet<>();

        for (BlockPos bedPos : homeBedClaims) {
            SimpleBedWarningSystem.HomeBedClaim claim =
                    SimpleBedWarningSystem.getClaimForPosition(bedPos);

            if (claim == null || claim.isExpired()) continue;

            UUID npcUUID = claim.npcUUID;
            foundCitizens.add(npcUUID);

            String npcName = citizenNamesMap.getOrDefault(npcUUID, claim.getCurrentName());

            Peasant_Entity foundPeasant = null;
            if (claim.level != null && !claim.level.isClientSide()) {
                Entity entity = ((ServerLevel) claim.level).getEntity(npcUUID);
                if (entity instanceof Peasant_Entity peasant) {
                    foundPeasant = peasant;
                    peasant.registerWithTownHall(this.getBlockPos());

                    // Post culture ticket if town has a culture and peasant doesn't yet
                    if (!peasant.getCultureSystem().hasCulture() && culture != Culture.NONE) {
                        net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureTicketSystem
                                .postTicket(peasant.getUUID(), culture, this.getBlockPos());
                    }
                }
            }

            // The NPC's own report (CitizenReportSystem) is the source for its name and job - it is kept
            // while the NPC is unloaded. Fall back to the bed claim / loaded entity before the first report.
            net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.CitizenReportSystem.CitizenReport report =
                    net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.CitizenReportSystem.get(npcUUID);
            if (report != null && !report.name().isEmpty()) npcName = report.name();
            String job = report != null ? report.jobType()
                    : foundPeasant != null ? foundPeasant.getJobType() : null;

            CitizenInfo citizen = registeredCitizens.get(npcUUID);
            if (citizen == null) {
                citizen = new CitizenInfo(npcUUID, bedPos, npcName);
                registeredCitizens.put(npcUUID, citizen);
                shouldSendDataUpdate = true;
            } else if (!npcName.equals(citizen.npcName)) {
                shouldSendDataUpdate = true;
            }
            citizen.npcName  = npcName;
            citizen.lastSeen = System.currentTimeMillis();
            if (job != null && !job.equals(citizen.currentJob)) {
                citizen.currentJob = job;
                shouldSendDataUpdate = true;
            }
        }

        registeredCitizens.entrySet().removeIf(entry -> {
            if (!foundCitizens.contains(entry.getKey()) &&
                    System.currentTimeMillis() - entry.getValue().lastSeen > 300000) {
                shouldSendDataUpdate = true;
                return true;
            }
            return false;
        });
    }

    @Override
    public BlockPos getTownHallPos() {
        return this.worldPosition;
    }

    @Override
    public void onDepositPosted(FarmerDepositTicketSystem.DepositTicket ticket) {
        processSingleDeposit(ticket);
    }

    @Override
    public void onRequestPosted(GrocerInventoryTicketSystem.InventoryRequestTicket ticket) {
        GrocerInventoryTicketSystem.postResponse(
                ticket.grocerUUID,
                ticket.playerUUID,
                townInventory,
                townBalance,
                this.getBlockPos()
        );
        GrocerInventoryTicketSystem.consumeRequest(ticket.grocerUUID);
    }

    @Override
    public void onButcherRequestPosted(ButcherInventoryTicketSystem.InventoryRequestTicket ticket) {
        ButcherInventoryTicketSystem.postResponse(
                ticket.butcherUUID,
                ticket.playerUUID,
                townInventory,
                townBalance,
                this.getBlockPos()
        );
        ButcherInventoryTicketSystem.consumeRequest(ticket.butcherUUID);
    }



    /**
     * Updates the queue of jobless citizens
     */
    private void updateJoblessQueue() {
        joblessQueue.clear();

        for (CitizenInfo citizen : registeredCitizens.values()) {
            if (level == null || level.isClientSide()) continue;

            Entity entity = ((ServerLevel) level).getEntity(citizen.npcUUID);
            if (!(entity instanceof Peasant_Entity peasant) || !peasant.isAdult()) continue;

            // Always sync the cached job from the actual entity
            citizen.currentJob = peasant.getJobType();

            if (citizen.currentJob.equals(JobSystem.JOB_NONE)) {
                joblessQueue.offer(citizen.npcUUID);
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
                        peasant.getJobBlockPos() == null ||
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
        Set<BlockPos> claimedBeds = SimpleBedWarningSystem.getHomeBedClaimsInColumn(
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

        BlockPos centerPos = this.getBlockPos();
        int minX = centerPos.getX() - currentRadius;
        int maxX = centerPos.getX() + currentRadius;
        int minY = getColumnMinY(level);
        int maxY = getColumnMaxY(level);
        int minZ = centerPos.getZ() - currentRadius;
        int maxZ = centerPos.getZ() + currentRadius;

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

    private void processSingleDeposit(FarmerDepositTicketSystem.DepositTicket ticket) {
        depositItems(ticket.farmerUUID, ticket.items);
        FarmerDepositTicketSystem.confirmDeposit(ticket.farmerUUID);
    }

    /** Adds deposited items to the town's stores; the town earns 10% of their value. */
    public void depositItems(Map<String, Integer> items) {
        depositItems(null, items);
    }

    /**
     * An NPC ({@code npc}, null if unknown) hands items in. What he took from the stores and paid for since he
     * last handed anything in and now brings back unused is refunded what he paid (takeForNpc). The rest he
     * sells to the town like a player selling to a shop (ItemPricing.SHOP_BUY_MULTIPLIER - halved while the
     * town is in debt): the town pays for it out of its balance, which may go below zero. The NPC himself
     * gets nothing. Whatever he took and doesn't bring back he used up - paid for.
     */
    public void depositItems(@Nullable UUID npc, Map<String, Integer> items) {
        Map<String, long[]> taken = npc == null ? null : npcWithdrawals.remove(npc);
        String job = jobOf(npc);
        float sellMultiplier = net.darkflameproduction.agotmod.util.ItemPricing.SHOP_BUY_MULTIPLIER * sellPriceFactor();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String itemKey = entry.getKey();
            int count = entry.getValue();
            if (count <= 0) continue;
            addToTownInventory(itemKey, count);
            long[] took = taken == null ? null : taken.get(itemKey);
            long returned = took == null ? 0 : Math.min(count, took[0]);
            if (returned > 0) refundWithdrawal(took[1] * returned / took[0], job);
            long deposited = count - returned;
            if (deposited > 0) {
                chargeTownBalance(deposited * net.darkflameproduction.agotmod.util.ItemPricing.applyMultiplier(itemKey, sellMultiplier),
                        CashFlow.NPC_DEPOSITS, job);
            }
        }
        setChanged();
    }

    /** What each NPC has taken from the stores since he last handed anything in: item -> {count, coins paid}. */
    private final Map<UUID, Map<String, long[]>> npcWithdrawals = new HashMap<>();

    /**
     * An NPC ({@code npc}, null if unknown) takes {@code amount} of {@code itemKey} from the stores: the town is
     * paid its value (ItemPricing - doubled while the town is in debt), counted as income. False (nothing
     * taken) if the stores have too little.
     */
    public boolean takeForNpc(@Nullable UUID npc, String itemKey, long amount) {
        if (!removeFromTownInventory(itemKey, amount)) return false;
        payForWithdrawal(npc, itemKey, amount);
        return true;
    }

    /** The payment of takeForNpc, for an NPC that has already taken the items out of the stores. */
    public void payForWithdrawal(@Nullable UUID npc, String itemKey, long amount) {
        if (amount <= 0) return;
        long paid = amount * net.darkflameproduction.agotmod.util.ItemPricing.applyMultiplier(itemKey, buyPriceFactor());
        addToTownBalance(paid, CashFlow.NPC_SUPPLIES, jobOf(npc));
        if (npc != null) {
            long[] took = npcWithdrawals.computeIfAbsent(npc, k -> new HashMap<>()).computeIfAbsent(itemKey, k -> new long[2]);
            took[0] += amount;
            took[1] += paid;
        }
        setChanged();
    }

    /** Pays back {@code value} for items returned unused: off the balance (into debt if need be) and today's earnings. */
    private void refundWithdrawal(long value, @Nullable String job) {
        if (value <= 0) return;
        townBalance -= value;
        rollEarningsDay();
        earnedToday = Math.max(0, earnedToday - value);
        book(CashFlow.REFUNDS, job, value);
        setChanged();
    }

    // ===== TAX AND DEBT =====

    /** The share of the day's net earnings that goes to the treasury - only on a day the town made money (rollEarningsDay). */
    public static final double TAX_RATE = 0.05;

    /** A town with a negative balance is in debt. */
    public boolean isInDebt() {
        return townBalance < 0;
    }

    /** What anyone buying from the town pays, as a share of the usual price: double while it is in debt. */
    public float buyPriceFactor() {
        return isInDebt() ? 2.0f : 1.0f;
    }

    /** What the town pays anyone selling to it, as a share of the usual price: half while it is in debt. */
    public float sellPriceFactor() {
        return isInDebt() ? 0.5f : 1.0f;
    }

    /** Takes {@code amount} off the balance even if that puts the town in debt (NPC deposits). */
    public void chargeTownBalance(long amount, CashFlow flow, @Nullable String job) {
        if (amount <= 0) return;
        townBalance -= amount;
        book(flow, job, amount);
        setChanged();
    }

    /** The job of one of the town's citizens (for the cash flow table); null if unknown or jobless. */
    @Nullable
    private String jobOf(@Nullable UUID npc) {
        CitizenInfo citizen = npc == null ? null : registeredCitizens.get(npc);
        return citizen == null || citizen.currentJob == null || citizen.currentJob.isEmpty() ? null : citizen.currentJob;
    }

    private void processFarmerDepositTickets() {
        Map<UUID, FarmerDepositTicketSystem.DepositTicket> pending =
                new HashMap<>(FarmerDepositTicketSystem.getAllPendingDeposits());
        for (FarmerDepositTicketSystem.DepositTicket ticket : pending.values()) {
            processSingleDeposit(ticket);
        }
    }

    private void spawnChildAtBed(ServerLevel level, BlockPos bedPos, RandomSource random) {
        Peasant_Entity child = ModEntities.PEASANT_ENTITY.get().create(level);
        if (child == null) return;

        child.setPos(bedPos.getX() + 0.5, bedPos.getY() + 1.0, bedPos.getZ() + 0.5);
        child.setAge(Peasant_Entity.AGE_CHILD);

        // Gender must be set before addFreshEntity so TownCultureZone rolls the right variants
        String gender = random.nextBoolean() ? Peasant_Entity.GENDER_MALE : Peasant_Entity.GENDER_FEMALE;
        child.setGender(gender);

        int agingTime = 240000 + (random.nextInt(96000) - 48000);
        child.setAgingTimer(0);
        child.getPersistentData().putInt("AgingTarget", agingTime);

        generateChildName(child, random);

        child.getHomeSystem().establishHomeBed(bedPos);

        String childName = child.hasCustomName() ? child.getCustomName().getString() : "Unnamed Child";
        SimpleBedWarningSystem.broadcastHomeBedClaim(child.getUUID(), bedPos, childName, level);

        // TownCultureZone.onEntityJoinLevel fires here and assigns culture + re-rolls name
        level.addFreshEntity(child);
    }
    /**
     * Generates a name for the child, with 80% chance of using existing family name
     */
    private void generateChildName(Peasant_Entity child, RandomSource random) {
        // Get fresh list of citizen names from the bed warning system
        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInColumn(
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
        // Guards are titled ("Guard Stinky Joe") and have no family name to pass on
        if (fullName.startsWith(net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.NameSystem.GUARD_TITLE + " ")) {
            return "Snow";
        }

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

        Set<BlockPos> homeBedClaims = SimpleBedWarningSystem.getHomeBedClaimsInColumn(
                this.getBlockPos(), scanRadius, null);

        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInColumn(
                this.getBlockPos(), scanRadius);

        citizenLastNames.clear();
        for (String fullName : citizenNamesMap.values()) {
            if (fullName != null && !fullName.isEmpty() && !fullName.equals("Unknown")) {
                String lastName = extractLastName(fullName);
                if (!lastName.isEmpty() && !lastName.equals("Snow")) {
                    citizenLastNames.add(lastName);
                }
            }
        }

        int newCitizenCount = 0;
        for (BlockPos claimedBedPos : homeBedClaims) {
            if (isWithinStableScanArea(claimedBedPos, scanRadius)) {
                newCitizenCount++;
            }
        }

        if (newCitizenCount != citizenCount) {
            citizenCount = newCitizenCount;
            sendDataToClients(level);
            setChanged();

            // Re-register zone with updated radius as town grows
            if (culture != Culture.NONE && level instanceof ServerLevel sl) {
                net.darkflameproduction.agotmod.entity.custom.npc.system.culture.TownCultureZone.registerZone(
                        this.worldPosition,
                        getCurrentScanRadius(),
                        culture,
                        sl.dimension()
                );
            }
        }
    }

    /**
     * Checks if a position is within the town column (square radius, unlimited height/depth)
     */
    public boolean isWithinStableScanArea(BlockPos pos, int radius) {
        BlockPos townHallPos = this.getBlockPos();

        int dx = Math.abs(pos.getX() - townHallPos.getX());
        int dz = Math.abs(pos.getZ() - townHallPos.getZ());

        return dx <= radius && dz <= radius;
    }

    private void performBedScan(Level level) {
        int bedCount = 0;
        int currentRadius = getCurrentScanRadius();

        BlockPos centerPos = this.getBlockPos();
        int minX = centerPos.getX() - currentRadius;
        int maxX = centerPos.getX() + currentRadius;
        int minY = getColumnMinY(level);
        int maxY = getColumnMaxY(level);
        int minZ = centerPos.getZ() - currentRadius;
        int maxZ = centerPos.getZ() + currentRadius;

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
        int renderDistance = level.getServer().getPlayerList().getViewDistance() * 16;

        level.players().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(
                        this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < renderDistance * renderDistance) {
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallRegisterPacket(
                                    this.worldPosition, true));
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallDataPacket(
                                    this.worldPosition,
                                    this.bedCount,
                                    this.citizenCount,
                                    getCurrentScanRadius(),
                                    this.townName,
                                    this.isClaimed,
                                    this.getClaimedByHouse(),
                                    this.getAvailableJobCount(),
                                    this.getAssignedJobCount(),
                                    this.getTotalJobCount(),
                                    this.getJoblessCount(),
                                    this.townBalance,
                                    this.townIncome,
                                    new java.util.HashMap<>(this.townInventory),
                                    this.culture.name(),
                                    this.getCitizenEntries(),
                                    this.getCraftingNeeds(),
                                    this.getEarnings()
                            ));
                }
            }
        });
    }

    private void sendDataToClients(Level level) {
        level.players().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(
                        this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < 64 * 64) {
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallDataPacket(
                                    this.worldPosition,
                                    this.bedCount,
                                    this.citizenCount,
                                    getCurrentScanRadius(),
                                    this.townName,
                                    this.isClaimed,
                                    this.getClaimedByHouse(),
                                    this.getAvailableJobCount(),
                                    this.getAssignedJobCount(),
                                    this.getTotalJobCount(),
                                    this.getJoblessCount(),
                                    this.townBalance,
                                    this.townIncome,
                                    new java.util.HashMap<>(this.townInventory),
                                    this.culture.name(),
                                    this.getCitizenEntries(),
                                    this.getCraftingNeeds(),
                                    this.getEarnings()
                            ));
                }
            }
        });

        sendDebugDataToClients(level);
    }

    private void sendDebugDataToClients(Level level) {
        int renderDistance = level.getServer().getPlayerList().getViewDistance() * 16;

        level.players().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer) {
                double distance = serverPlayer.distanceToSqr(
                        this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                if (distance < renderDistance * renderDistance) {
                    net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                            new net.darkflameproduction.agotmod.network.TownHallDataPacket(
                                    this.worldPosition,
                                    this.bedCount,
                                    this.citizenCount,
                                    getCurrentScanRadius(),
                                    this.townName,
                                    this.isClaimed,
                                    this.getClaimedByHouse(),
                                    this.getAvailableJobCount(),
                                    this.getAssignedJobCount(),
                                    this.getTotalJobCount(),
                                    this.getJoblessCount(),
                                    this.townBalance,
                                    this.townIncome,
                                    new java.util.HashMap<>(this.townInventory),
                                    this.culture.name(),
                                    this.getCitizenEntries(),
                                    this.getCraftingNeeds(),
                                    this.getEarnings()
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
            registerWithNearbyPlayers();
            GrocerInventoryTicketSystem.registerListener(this);
            ButcherInventoryTicketSystem.registerListener(this);
            TannerInventoryTicketSystem.registerListener(this);
            TailorInventoryTicketSystem.registerListener(this);
            BlacksmithInventoryTicketSystem.registerListener(this);
            FarmerDepositTicketSystem.registerListener(this);
            FoodCollectionTicketSystem.registerListener(this);
            SmelterCollectionTicketSystem.registerListener(this);
            AnimalHerderCollectionTicketSystem.registerListener(this);
            TannerCollectionTicketSystem.registerListener(this);
            TailorCollectionTicketSystem.registerListener(this);
            LumberjackDepositTicketSystem.registerListener(this);
            CharcoalBurnerCollectionTicketSystem.registerListener(this);
            CarpenterCollectionTicketSystem.registerListener(this);
            CarpenterInventoryTicketSystem.registerListener(this);
            net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem
                    .registerListener(this);

            if (level instanceof ServerLevel sl) {
                if (culture != Culture.NONE) {
                    net.darkflameproduction.agotmod.entity.custom.npc.system.culture.TownCultureZone
                            .registerZone(this.worldPosition, getCurrentScanRadius(), culture, sl.dimension());
                }

                // Always register a protection zone.
                // Unclaimed towns pass null so nobody except OPs can grief.
                // Claimed towns pass the owning house UUID.
                UUID houseUUID = null;
                if (isClaimed && claimedByPlayerUUID != null) {
                    houseUUID = resolveHouseUUIDFromPlayer(sl, claimedByPlayerUUID);
                }
                net.darkflameproduction.agotmod.entity.custom.npc.system.protection.TownProtectionZone
                        .registerZone(this.worldPosition, getCurrentScanRadius(), houseUUID, sl.dimension());
            }
        }
    }

    // The controlling house, cached: resolving it for an offline owner reads their player file
    private UUID cachedHouseUUID = null;
    private long houseUUIDResolvedAt = Long.MIN_VALUE / 2;
    private static final long HOUSE_UUID_REFRESH_TICKS = 20 * 60;

    /** The house controlling this town (the claiming player's house), or null if unclaimed or houseless. */
    @org.jetbrains.annotations.Nullable
    public UUID getControllingHouseUUID() {
        if (!isClaimed || claimedByPlayerUUID == null || !(level instanceof ServerLevel sl)) return null;
        long now = sl.getGameTime();
        if (now - houseUUIDResolvedAt >= HOUSE_UUID_REFRESH_TICKS) {
            cachedHouseUUID = resolveHouseUUIDFromPlayer(sl, claimedByPlayerUUID);
            houseUUIDResolvedAt = now;
        }
        return cachedHouseUUID;
    }

    private UUID resolveHouseUUIDFromPlayer(ServerLevel serverLevel, UUID playerUUID) {
        ServerPlayer player = serverLevel.getServer().getPlayerList().getPlayer(playerUUID);
        if (player != null) {
            return net.darkflameproduction.agotmod.network.ServerHouseHandler.getPlayerHouseUUID(player);
        }

        try {
            Path playerFile = serverLevel.getServer()
                    .getWorldPath(LevelResource.ROOT)
                    .resolve("playerdata").resolve(playerUUID + ".dat");

            if (Files.exists(playerFile)) {
                CompoundTag root = NbtIo.readCompressed(playerFile, NbtAccounter.unlimitedHeap());

                CompoundTag houseTag = null;
                if (root.contains("ForgeCaps")) {
                    CompoundTag caps = root.getCompound("ForgeCaps");
                    if (caps.contains("agotmod.house")) houseTag = caps.getCompound("agotmod.house");
                }
                if (houseTag == null && root.contains("agotmod.house")) {
                    houseTag = root.getCompound("agotmod.house");
                }
                if (houseTag != null && houseTag.contains("house_uuid")) {
                    return UUID.fromString(houseTag.getString("house_uuid"));
                }
            }
        } catch (Exception ignored) {}
        return null;
    }

    @Override
    public void onFoodRequestPosted(FoodCollectionTicketSystem.FoodRequestTicket ticket) {
        int remaining = ticket.amountNeeded;
        List<ItemStack> toGive = new ArrayList<>();

        List<String> keys = new ArrayList<>(townInventory.keySet());

        for (String itemKey : keys) {
            if (remaining <= 0) break;

            net.minecraft.resources.ResourceLocation loc =
                    net.minecraft.resources.ResourceLocation.tryParse(itemKey);
            if (loc == null) continue;

            net.minecraft.world.item.Item item =
                    net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
            if (item == null || item == net.minecraft.world.item.Items.AIR) continue;

            if (!isTownHallFood(item)) continue;

            long available = townInventory.getOrDefault(itemKey, 0L);
            if (available <= 0) continue;

            int toTake = (int) Math.min(remaining, available);
            removeFromTownInventory(itemKey, toTake);
            toGive.add(new net.minecraft.world.item.ItemStack(item, toTake));
            remaining -= toTake;
        }

        // Calculate total value of food given using ItemPricing
        if (!toGive.isEmpty()) {
            long totalValue = 0;
            for (ItemStack stack : toGive) {
                net.minecraft.resources.ResourceLocation itemLoc =
                        net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
                if (itemLoc != null) {
                    String itemKey = itemLoc.toString();
                    // Doubled while the town is in debt, like everything bought from it
                    long unitPrice = net.darkflameproduction.agotmod.util.ItemPricing.applyMultiplier(itemKey, buyPriceFactor());
                    totalValue += unitPrice * stack.getCount();
                }
            }

            // Add total value to town balance (taxed once a day, on the day's net earnings - rollEarningsDay)
            if (totalValue > 0) {
                addToTownBalance(totalValue, CashFlow.NPC_FOOD, jobOf(ticket.peasantUUID));
            }
        }

        FoodCollectionTicketSystem.postResponse(ticket.peasantUUID, toGive);
        setChanged();
    }

    private boolean isTownHallFood(net.minecraft.world.item.Item item) {
        net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack(item);

        // Must be a food item
        if (!stack.has(net.minecraft.core.component.DataComponents.FOOD)) return false;

        // Only hand out food NPCs can actually eat - anything else isn't counted as food by them,
        // so they'd keep coming back for more
        if (!net.darkflameproduction.agotmod.entity.custom.npc.system.inventory.HungerSystem.isNpcFood(stack)) return false;

        // Exclude golden apples explicitly
        if (item == net.minecraft.world.item.Items.GOLDEN_APPLE) return false;
        if (item == net.minecraft.world.item.Items.ENCHANTED_GOLDEN_APPLE) return false;

        // Exclude known poisonous foods explicitly
        if (item == net.minecraft.world.item.Items.POISONOUS_POTATO) return false;
        if (item == net.minecraft.world.item.Items.PUFFERFISH) return false;
        if (item == net.minecraft.world.item.Items.ROTTEN_FLESH) return false;
        if (item == net.minecraft.world.item.Items.SPIDER_EYE) return false;

        return true;
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        GrocerInventoryTicketSystem.unregisterListener(this);
        ButcherInventoryTicketSystem.unregisterListener(this);
        TannerInventoryTicketSystem.unregisterListener(this);
        TailorInventoryTicketSystem.unregisterListener(this);
        BlacksmithInventoryTicketSystem.unregisterListener(this);
        FarmerDepositTicketSystem.unregisterListener(this);
        SmelterCollectionTicketSystem.unregisterListener(this);
        FoodCollectionTicketSystem.unregisterListener(this);
        AnimalHerderCollectionTicketSystem.unregisterListener(this);
        TannerCollectionTicketSystem.unregisterListener(this);
        TailorCollectionTicketSystem.unregisterListener(this);
        LumberjackDepositTicketSystem.unregisterListener(this);
        CharcoalBurnerCollectionTicketSystem.unregisterListener(this);
        CarpenterCollectionTicketSystem.unregisterListener(this);
        CarpenterInventoryTicketSystem.unregisterListener(this);
        net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem
                .unregisterListener(this);

        if (level instanceof ServerLevel sl) {
            net.darkflameproduction.agotmod.entity.custom.npc.system.culture.TownCultureZone
                    .removeZone(this.worldPosition, sl.dimension());
            net.darkflameproduction.agotmod.entity.custom.npc.system.protection.TownProtectionZone
                    .removeZone(this.worldPosition, sl.dimension());
        }

        if (!level.isClientSide()) {
            level.players().forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    int renderDistance = level.getServer().getPlayerList().getViewDistance() * 16;
                    double distance = serverPlayer.distanceToSqr(
                            this.worldPosition.getX(), this.worldPosition.getY(), this.worldPosition.getZ());
                    if (distance < renderDistance * renderDistance) {
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new net.darkflameproduction.agotmod.network.TownHallRegisterPacket(
                                        this.worldPosition, false));
                    }
                }
            });
        }
    }

    // ===== TOWN HALL INVENTORY API =====

    public void addToTownInventory(String itemKey, long amount) {
        if (itemKey == null || itemKey.isEmpty() || amount <= 0) return;
        townInventory.merge(itemKey, amount, Long::sum);
        setChanged();
    }

    public boolean removeFromTownInventory(String itemKey, long amount) {
        if (itemKey == null || itemKey.isEmpty() || amount <= 0) return false;
        long current = townInventory.getOrDefault(itemKey, 0L);
        if (current < amount) return false;
        long remaining = current - amount;
        if (remaining <= 0) {
            townInventory.remove(itemKey);
        } else {
            townInventory.put(itemKey, remaining);
        }
        setChanged();
        return true;
    }

    public long getTownInventoryAmount(String itemKey) {
        return townInventory.getOrDefault(itemKey, 0L);
    }

    public Map<String, Long> getTownInventory() {
        return Collections.unmodifiableMap(townInventory);
    }

    /**
     * The town's supply chain needs (SupplyChain.townNeeds): everything its crafting NPCs can make that the
     * town hall holds too little of, with the jobs that make it and whether the town has anyone with one.
     * A row only goes once the town hall itself holds enough - what NPCs carry doesn't count.
     *
     * Above them, the required trades: the basic goods the town's jobs need that it is short of (TownDemand),
     * which its trader buys. An item listed as a trade isn't listed again as a craft. Below them, the sell
     * list: what the town holds more of than it may (TownSurplus), which its trader sells off.
     */
    public List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CraftingNeed> getCraftingNeeds() {
        Set<String> jobsInTown = new HashSet<>();
        for (CitizenInfo citizen : registeredCitizens.values()) {
            if (citizen.currentJob != null) jobsInTown.add(citizen.currentJob);
        }
        List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CraftingNeed> needs = new ArrayList<>();
        Set<String> traded = new HashSet<>();
        boolean hasTrader = jobsInTown.contains(JobSystem.JOB_TRADER);
        for (net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TownDemand.Demand demand : getDemands()) {
            if (demand.shortfall() <= 0) continue;
            String item = net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TownDemand.displayItem(demand.key());
            needs.add(new net.darkflameproduction.agotmod.network.TownHallDataPacket.CraftingNeed(item,
                    net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TownDemand.label(demand.key()),
                    JobSystem.JOB_TRADER, (int) Math.min(Integer.MAX_VALUE, demand.have()),
                    (int) Math.min(Integer.MAX_VALUE, demand.want()), false, hasTrader, true, false));
            traded.add(demand.key());
        }
        for (var need : net.darkflameproduction.agotmod.entity.custom.npc.system.supply.SupplyChain.townNeeds(this, jobsInTown)) {
            if (!traded.contains(need.itemKey())) needs.add(need);
        }
        // Last, the sell list: what the town holds too much of, which its trader sells off
        for (var surplus : getSurplus()) {
            needs.add(new net.darkflameproduction.agotmod.network.TownHallDataPacket.CraftingNeed(surplus.itemKey(), "",
                    JobSystem.JOB_TRADER, (int) Math.min(Integer.MAX_VALUE, surplus.have()),
                    (int) Math.min(Integer.MAX_VALUE, surplus.cap()), false, hasTrader, false, true));
        }
        return needs;
    }

    /** How many citizens have each job (jobless citizens aside). */
    public Map<String, Integer> getJobCounts() {
        Map<String, Integer> counts = new HashMap<>();
        for (CitizenInfo citizen : registeredCitizens.values()) {
            if (citizen.currentJob != null && !citizen.currentJob.isEmpty()) counts.merge(citizen.currentJob, 1, Integer::sum);
        }
        return counts;
    }

    /** The registered citizens - everyone the town feeds. */
    public int getRegisteredCitizenCount() {
        return registeredCitizens.size();
    }

    /** The basic goods the town needs for its citizens to work and eat (TownDemand), most important first. */
    public List<net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TownDemand.Demand> getDemands() {
        return net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TownDemand.of(this);
    }

    /** True once a day: the first trader to ask gets to buy the town's supplies (TraderPurchasing). */
    public boolean claimTraderRestock(long day) {
        if (lastTraderRestockDay == day) return false;
        lastTraderRestockDay = day;
        setChanged();
        return true;
    }

    /** When the town's trader last sold off its surplus (TownSurplus) - game time, not saved. */
    private long lastTraderSaleTime = Long.MIN_VALUE / 2;

    /** True at most once every {@code interval} ticks: the trader that asks gets to sell the town's surplus. */
    public boolean claimTraderSale(long gameTime, int interval) {
        if (gameTime - lastTraderSaleTime < interval && gameTime >= lastTraderSaleTime) return false;
        lastTraderSaleTime = gameTime;
        return true;
    }

    /** The town's sell list: what it holds more of than it may (TownSurplus). */
    public List<net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TownSurplus.Surplus> getSurplus() {
        return net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TownSurplus.of(this);
    }

    /** The town's citizens (name and job, from their CitizenReportSystem reports), sorted by name, for the client. */
    public List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CitizenEntry> getCitizenEntries() {
        List<net.darkflameproduction.agotmod.network.TownHallDataPacket.CitizenEntry> entries = new ArrayList<>();
        for (CitizenInfo citizen : registeredCitizens.values()) {
            entries.add(new net.darkflameproduction.agotmod.network.TownHallDataPacket.CitizenEntry(
                    citizen.npcName == null ? "Unknown" : citizen.npcName,
                    citizen.currentJob == null ? JobSystem.JOB_NONE : citizen.currentJob));
        }
        entries.sort(Comparator.comparing(net.darkflameproduction.agotmod.network.TownHallDataPacket.CitizenEntry::name,
                String.CASE_INSENSITIVE_ORDER));
        return entries;
    }

    public boolean hasTownInventoryItem(String itemKey, long amount) {
        return townInventory.getOrDefault(itemKey, 0L) >= amount;
    }

    public void cleanupTownInventory() {
        townInventory.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

    /** Adds income to the balance, booked under {@code flow} (an income CashFlow) and counted in today's earnings. */
    public void addToTownBalance(long amount, CashFlow flow) {
        addToTownBalance(amount, flow, null);
    }

    /** Adds income to the balance from a citizen with {@code job} (null if none), for the cash flow table. */
    public void addToTownBalance(long amount, CashFlow flow, @Nullable String job) {
        if (amount <= 0) return;
        townBalance += amount;
        rollEarningsDay();
        earnedToday += amount;
        book(flow, job, amount);
        setChanged();
    }

    /**
     * Today's and yesterday's money in and out, by CashFlow and the job of the citizen it came from or went to:
     * "FLOW|job" (job empty when it's not a citizen's) -> coins.
     */
    private Map<String, Long> flowToday = new HashMap<>();
    private Map<String, Long> flowYesterday = new HashMap<>();

    /** Books {@code amount} under {@code flow} for a citizen with {@code job} (null if none). */
    private void book(CashFlow flow, @Nullable String job, long amount) {
        if (amount <= 0) return;
        rollEarningsDay();
        flowToday.merge(flow.name() + "|" + (job == null ? "" : job), amount, Long::sum);
    }

    private static CompoundTag flowsTag(Map<String, Long> flows) {
        CompoundTag tag = new CompoundTag();
        flows.forEach(tag::putLong);
        return tag;
    }

    private static Map<String, Long> flowsFrom(CompoundTag tag) {
        Map<String, Long> flows = new HashMap<>();
        for (String key : tag.getAllKeys()) flows.put(key.contains("|") ? key : key + "|", tag.getLong(key)); // older saves: no job
        return flows;
    }

    /** What the town earned yesterday - everything added to its balance that day (0 if it earned nothing). */
    public long getEarnedYesterday() {
        rollEarningsDay();
        return earnedYesterday;
    }

    /** Today's and yesterday's earnings and cash flow and the trader's budget, for the town hall screen. */
    public net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings getEarnings() {
        rollEarningsDay();
        return new net.darkflameproduction.agotmod.network.TownHallDataPacket.Earnings(earnedToday, earnedYesterday,
                net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderPurchasing.budget(this),
                new HashMap<>(flowToday), new HashMap<>(flowYesterday));
    }

    /**
     * On a new day: the day that ended is taxed - {@link #TAX_RATE} of what it made, if it made money (its income
     * less its expenses), moved from the balance to the treasury; a day that lost money pays no tax. Then today's
     * earnings and cash flow become yesterday's (nothing, if a day or more went by without any).
     */
    private void rollEarningsDay() {
        if (level == null) return;
        long day = level.getDayTime() / 24000;
        if (day == earningsDay) return;
        long net = 0;
        for (Map.Entry<String, Long> entry : flowToday.entrySet()) {
            CashFlow flow = flowOf(entry.getKey());
            if (flow != null) net += flow.income ? entry.getValue() : -entry.getValue();
        }
        long tax = net > 0 ? (long) Math.floor(net * TAX_RATE) : 0;
        if (tax > 0) {
            townBalance -= tax;
            townIncome += tax;
            flowToday.merge(CashFlow.TAXES.name() + "|", tax, Long::sum);
        }
        boolean consecutive = day == earningsDay + 1;
        earnedYesterday = consecutive ? earnedToday : 0;
        earnedToday = 0;
        flowYesterday = consecutive ? flowToday : new HashMap<>();
        flowToday = new HashMap<>();
        earningsDay = day;
        setChanged();
    }

    /** The CashFlow of a "FLOW|job" key; null if there is none by that name. */
    @Nullable
    public static CashFlow flowOf(String key) {
        int bar = key.indexOf('|');
        try {
            return CashFlow.valueOf(bar < 0 ? key : key.substring(0, bar));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /** Called regularly so the day's tax is settled as soon as a new day starts, not only on the next payment. */
    public void settleDay() {
        rollEarningsDay();
    }

    /** Spends {@code amount} if the town has it (never into debt), booked under {@code flow} (an expense CashFlow). */
    public boolean deductFromTownBalance(long amount, CashFlow flow) {
        if (townBalance < amount) return false;
        townBalance -= amount;
        book(flow, null, amount);
        setChanged();
        return true;
    }

    public long getTownBalance() {
        return townBalance;
    }

    public void addToTownIncome(long amount) {
        if (amount <= 0) return;
        townIncome += amount;
        setChanged();
    }

    public long getTownIncome() {
        return townIncome;
    }

    /** A player's reputation with this town; the starting value on their first visit (see TownReputation). */
    public int getReputation(UUID player) {
        Integer reputation = playerReputation.get(player);
        if (reputation != null) return reputation;
        int start = net.darkflameproduction.agotmod.entity.custom.npc.system.reputation.TownReputation.START;
        playerReputation.put(player, start);
        setChanged();
        return start;
    }

    /** Sets it (clamped to -100..100). Use TownReputation.change, which also tells the player. */
    public void setReputation(UUID player, int reputation) {
        playerReputation.put(player, net.darkflameproduction.agotmod.entity.custom.npc.system.reputation.TownReputation.clamp(reputation));
        setChanged();
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        tag.putInt("BedCount", bedCount);
        tag.putInt("CitizenCount", citizenCount);
        tag.putString("TownName", townName);

        tag.putBoolean("IsClaimed", isClaimed);
        if (claimedByPlayerUUID != null) tag.putUUID("ClaimedByPlayerUUID", claimedByPlayerUUID);

        tag.putString("Culture", culture.name());

        tag.putLong("LastScanDay", lastScanDay);
        tag.putLong("LastChildSpawnDay", lastChildSpawnDay);
        tag.putLong("LastTraderRestockDay", lastTraderRestockDay);
        tag.putLong("EarningsDay", earningsDay);
        tag.putLong("EarnedToday", earnedToday);
        tag.putLong("EarnedYesterday", earnedYesterday);
        tag.put("CashFlowToday", flowsTag(flowToday));
        tag.put("CashFlowYesterday", flowsTag(flowYesterday));
        tag.putBoolean("HasScannedToday", hasScannedToday);

        tag.putInt("TicksSinceLastRegister", ticksSinceLastRegister);
        tag.putInt("TicksSinceLastCitizenCheck", ticksSinceLastCitizenCheck);
        tag.putInt("TicksSinceLastJobAssignment", ticksSinceLastJobAssignment);
        tag.putInt("TicksSinceLastJoblessUpdate", ticksSinceLastJoblessUpdate);
        tag.putInt("TicksSinceLastJobCleanup", ticksSinceLastJobCleanup);

        tag.putInt("AvailableJobCount", availableJobCount);
        tag.putInt("AssignedJobCount", assignedJobCount);
        tag.putBoolean("ShouldSendDataUpdate", shouldSendDataUpdate);

        tag.putInt("CitizenLastNamesCount", citizenLastNames.size());
        for (int i = 0; i < citizenLastNames.size(); i++) {
            tag.putString("CitizenLastName_" + i, citizenLastNames.get(i));
        }

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

        tag.putInt("AvailableJobsCount", availableJobs.size());
        int jobIndex = 0;
        for (JobBlockInfo job : availableJobs.values()) {
            CompoundTag jobTag = new CompoundTag();
            jobTag.putLong("JobBlockPos", job.jobBlockPos.asLong());
            jobTag.putString("JobType", job.jobType);
            jobTag.putBoolean("IsOccupied", job.isOccupied);
            if (job.assignedNPC != null) jobTag.putUUID("AssignedNPC", job.assignedNPC);
            tag.put("Job_" + jobIndex, jobTag);
            jobIndex++;
        }

        List<UUID> joblessUUIDs = new ArrayList<>(joblessQueue);
        tag.putInt("JoblessQueueCount", joblessUUIDs.size());
        for (int i = 0; i < joblessUUIDs.size(); i++) {
            tag.putUUID("JoblessUUID_" + i, joblessUUIDs.get(i));
        }

        cleanupTownInventory();
        CompoundTag townInventoryTag = new CompoundTag();
        for (Map.Entry<String, Long> entry : townInventory.entrySet()) {
            if (entry.getValue() > 0) townInventoryTag.putLong(entry.getKey(), entry.getValue());
        }
        tag.put("TownInventory", townInventoryTag);

        tag.putLong("TownBalance", townBalance);
        tag.putLong("TownIncome", townIncome);

        CompoundTag reputationTag = new CompoundTag();
        for (Map.Entry<UUID, Integer> entry : playerReputation.entrySet()) {
            reputationTag.putInt(entry.getKey().toString(), entry.getValue());
        }
        tag.put("PlayerReputation", reputationTag);

        CompoundTag withdrawalsTag = new CompoundTag();
        for (Map.Entry<UUID, Map<String, long[]>> npc : npcWithdrawals.entrySet()) {
            CompoundTag itemsTag = new CompoundTag();
            npc.getValue().forEach((item, took) -> itemsTag.putLongArray(item, took.clone())); // {count, coins paid}
            withdrawalsTag.put(npc.getKey().toString(), itemsTag);
        }
        tag.put("NpcWithdrawals", withdrawalsTag);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        bedCount     = tag.getInt("BedCount");
        citizenCount = tag.getInt("CitizenCount");
        townName     = tag.getString("TownName");
        if (townName.isEmpty()) townName = "Unnamed Town";

        isClaimed           = tag.getBoolean("IsClaimed");
        claimedByPlayerUUID = tag.hasUUID("ClaimedByPlayerUUID") ? tag.getUUID("ClaimedByPlayerUUID") : null;

        if (tag.contains("Culture")) {
            culture = Culture.fromName(tag.getString("Culture")); // also converts cultures from older saves
        }

        lastScanDay      = tag.getLong("LastScanDay");
        lastChildSpawnDay = tag.getLong("LastChildSpawnDay");
        lastTraderRestockDay = tag.contains("LastTraderRestockDay") ? tag.getLong("LastTraderRestockDay") : -1;
        earningsDay     = tag.contains("EarningsDay") ? tag.getLong("EarningsDay") : -1;
        earnedToday     = tag.getLong("EarnedToday");
        earnedYesterday = tag.getLong("EarnedYesterday");
        flowToday       = flowsFrom(tag.getCompound("CashFlowToday"));
        flowYesterday   = flowsFrom(tag.getCompound("CashFlowYesterday"));
        hasScannedToday  = tag.getBoolean("HasScannedToday");

        ticksSinceLastRegister      = tag.getInt("TicksSinceLastRegister");
        ticksSinceLastCitizenCheck  = tag.getInt("TicksSinceLastCitizenCheck");
        ticksSinceLastJobAssignment = tag.getInt("TicksSinceLastJobAssignment");
        ticksSinceLastJoblessUpdate = tag.getInt("TicksSinceLastJoblessUpdate");
        ticksSinceLastJobCleanup    = tag.getInt("TicksSinceLastJobCleanup");

        availableJobCount  = tag.getInt("AvailableJobCount");
        assignedJobCount   = tag.getInt("AssignedJobCount");
        shouldSendDataUpdate = tag.getBoolean("ShouldSendDataUpdate");

        citizenLastNames.clear();
        int lastNameCount = tag.getInt("CitizenLastNamesCount");
        for (int i = 0; i < lastNameCount; i++) {
            String lastName = tag.getString("CitizenLastName_" + i);
            if (!lastName.isEmpty()) citizenLastNames.add(lastName);
        }

        registeredCitizens.clear();
        int citizenCount = tag.getInt("RegisteredCitizensCount");
        for (int i = 0; i < citizenCount; i++) {
            if (!tag.contains("Citizen_" + i)) continue;
            try {
                CompoundTag citizenTag = tag.getCompound("Citizen_" + i);
                UUID uuid       = citizenTag.getUUID("UUID");
                BlockPos bedPos = BlockPos.of(citizenTag.getLong("HomeBedPos"));
                String name     = citizenTag.getString("Name");
                String job      = citizenTag.contains("Job") ? citizenTag.getString("Job") : JobSystem.JOB_NONE;
                long lastSeen   = citizenTag.contains("LastSeen") ? citizenTag.getLong("LastSeen") : System.currentTimeMillis();
                CitizenInfo citizen = new CitizenInfo(uuid, bedPos, name.isEmpty() ? "Unknown" : name);
                citizen.currentJob = job;
                citizen.lastSeen   = lastSeen;
                registeredCitizens.put(uuid, citizen);
            } catch (Exception e) {
                net.darkflameproduction.agotmod.AGoTMod.LOGGER.warn("Failed to load citizen data at index {}: {}", i, e.getMessage());
            }
        }

        availableJobs.clear();
        int jobCount = tag.getInt("AvailableJobsCount");
        for (int i = 0; i < jobCount; i++) {
            if (!tag.contains("Job_" + i)) continue;
            try {
                CompoundTag jobTag  = tag.getCompound("Job_" + i);
                BlockPos jobPos     = BlockPos.of(jobTag.getLong("JobBlockPos"));
                String jobType      = jobTag.getString("JobType");
                boolean isOccupied  = jobTag.getBoolean("IsOccupied");
                JobBlockInfo job    = new JobBlockInfo(jobPos, jobType);
                job.isOccupied      = isOccupied;
                if (jobTag.hasUUID("AssignedNPC")) job.assignedNPC = jobTag.getUUID("AssignedNPC");
                availableJobs.put(jobPos, job);
            } catch (Exception e) {
                net.darkflameproduction.agotmod.AGoTMod.LOGGER.warn("Failed to load job data at index {}: {}", i, e.getMessage());
            }
        }

        joblessQueue.clear();
        int joblessCount = tag.getInt("JoblessQueueCount");
        for (int i = 0; i < joblessCount; i++) {
            if (!tag.hasUUID("JoblessUUID_" + i)) continue;
            try { joblessQueue.offer(tag.getUUID("JoblessUUID_" + i)); }
            catch (Exception e) { net.darkflameproduction.agotmod.AGoTMod.LOGGER.warn("Failed to load jobless UUID at index {}", i); }
        }

        townInventory.clear();
        if (tag.contains("TownInventory")) {
            CompoundTag inv = tag.getCompound("TownInventory");
            for (String key : inv.getAllKeys()) {
                long value = inv.getLong(key);
                if (value > 0) townInventory.put(key, value);
            }
        }
        cleanupTownInventory();

        townBalance = tag.getLong("TownBalance");
        townIncome  = tag.getLong("TownIncome");

        playerReputation.clear();
        CompoundTag reputationTag = tag.getCompound("PlayerReputation");
        for (String key : reputationTag.getAllKeys()) {
            try {
                playerReputation.put(UUID.fromString(key), reputationTag.getInt(key));
            } catch (IllegalArgumentException ignored) {}
        }

        npcWithdrawals.clear();
        CompoundTag withdrawalsTag = tag.getCompound("NpcWithdrawals");
        for (String key : withdrawalsTag.getAllKeys()) {
            try {
                CompoundTag itemsTag = withdrawalsTag.getCompound(key);
                Map<String, long[]> items = new HashMap<>();
                for (String item : itemsTag.getAllKeys()) {
                    long[] took = itemsTag.getLongArray(item);
                    if (took.length != 2) { // older saves kept only the count: paid at the base price
                        long count = itemsTag.getLong(item);
                        took = new long[]{count, count * net.darkflameproduction.agotmod.util.ItemPricing.getItemPrice(item)};
                    }
                    if (took[0] > 0) items.put(item, took);
                }
                npcWithdrawals.put(UUID.fromString(key), items);
            } catch (IllegalArgumentException ignored) {}
        }

        if (!availableJobs.isEmpty()) {
            long actualAvailable = availableJobs.values().stream().filter(j -> !j.isOccupied).count();
            long actualAssigned  = availableJobs.values().stream().filter(j ->  j.isOccupied).count();
            if (actualAvailable != availableJobCount || actualAssigned != assignedJobCount) {
                availableJobCount = (int) actualAvailable;
                assignedJobCount  = (int) actualAssigned;
                shouldSendDataUpdate = true;
            }
        }
        joblessQueue.removeIf(uuid -> !registeredCitizens.containsKey(uuid));
    }
}
