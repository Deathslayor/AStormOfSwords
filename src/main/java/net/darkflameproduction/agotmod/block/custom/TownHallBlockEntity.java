package net.darkflameproduction.agotmod.block.custom;

import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder.AnimalHerderCollectionTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobWarningSystem;
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
import net.minecraft.world.entity.MobSpawnType;
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

    // ===== TOWN HALL SHARED INVENTORY =====
    private final Map<String, Long> townInventory = new HashMap<>();
    private long townBalance = 0;
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
    public int getJoblessCount() {
        return joblessQueue.size();
    }
    public List<CitizenInfo> getRegisteredCitizens() {
        return new ArrayList<>(registeredCitizens.values());
    }
    public List<JobBlockInfo> getAvailableJobs() {
        return new ArrayList<>(availableJobs.values());
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
     * Gets the current scan height (unchanged)
     */
    public int getCurrentScanHeight() {
        return SCAN_HEIGHT;
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
        int minY = Math.max(centerPos.getY() - currentHeight, level.dimensionType().minY());
        int maxY = Math.min(centerPos.getY() + currentHeight, level.dimensionType().minY() + level.dimensionType().height() - 1);
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
        if (state.is(ModBLocks.GUARD_BARREL.get()))           return JobSystem.JOB_GUARD;
        if (state.is(ModBLocks.MINER_BARREL.get()))           return JobSystem.JOB_MINER;
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
        ticket.items.forEach((key, amount) ->
                townInventory.merge(key, amount, Long::sum));
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
            removeFromTownInventory(logKey, toTake);
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
            removeFromTownInventory("agotmod:nail", toTake);
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
            removeFromTownInventory("minecraft:stick", toTake);
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
            removeFromTownInventory(flowerKey, toTake);
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
                "agotmod:cotton",
                "minecraft:string",
                "minecraft:white_wool",
                "minecraft:leather"
        }) {
            long available = townInventory.getOrDefault(matKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(64, available);
            removeFromTownInventory(matKey, toTake);
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
            removeFromTownInventory("minecraft:rabbit_hide", toTake);
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
            removeFromTownInventory("minecraft:leather", toTake);
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
            removeFromTownInventory("minecraft:string", toTake);
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
            removeFromTownInventory(logKey, toTake);
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
            removeFromTownInventory(coalKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(coalKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
            }
            coalRemaining -= toTake;
        }

        int coalActuallyTaken = coalNeeded - coalRemaining;
        int oreCapacity = coalActuallyTaken * 8;

        // Pull ores in priority order: gold > silver > iron > copper > tin
        int oreRemaining = oreCapacity;
        for (String oreKey : new String[]{
                "minecraft:raw_gold", "agotmod:raw_silver",
                "minecraft:raw_iron", "minecraft:raw_copper", "agotmod:raw_tin"}) {
            if (oreRemaining <= 0) break;
            long available = townInventory.getOrDefault(oreKey, 0L);
            if (available <= 0) continue;
            int toTake = (int) Math.min(oreRemaining, available);
            removeFromTownInventory(oreKey, toTake);
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
            removeFromTownInventory(breedingItemKey, toTake);
            ResourceLocation loc = ResourceLocation.tryParse(breedingItemKey);
            if (loc != null) {
                net.minecraft.world.item.Item item = BuiltInRegistries.ITEM.get(loc);
                if (item != null && item != Items.AIR) toGive.add(new ItemStack(item, toTake));
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
    }

    private void updateCitizenRegistryFromBeds() {
        int scanRadius = getCurrentScanRadius();

        Set<BlockPos> homeBedClaims = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                this.getBlockPos(), scanRadius, null);

        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInRadius(
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

            if (registeredCitizens.containsKey(npcUUID)) {
                CitizenInfo citizen = registeredCitizens.get(npcUUID);
                citizen.npcName  = npcName;
                citizen.lastSeen = System.currentTimeMillis();
                if (foundPeasant != null) citizen.currentJob = foundPeasant.getJobType();
            } else {
                CitizenInfo newCitizen = new CitizenInfo(npcUUID, bedPos, npcName);
                if (foundPeasant != null) newCitizen.currentJob = foundPeasant.getJobType();
                registeredCitizens.put(npcUUID, newCitizen);
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

    private void processSingleDeposit(FarmerDepositTicketSystem.DepositTicket ticket) {
        for (Map.Entry<String, Integer> entry : ticket.items.entrySet()) {
            String itemKey = entry.getKey();
            int count = entry.getValue();
            addToTownInventory(itemKey, count);
            int itemPrice = net.darkflameproduction.agotmod.util.ItemPricing.getItemPrice(itemKey);
            if (itemPrice > 0) {
                long income = (long) Math.ceil(count * itemPrice * 0.1);
                addToTownBalance(income);
            }
        }
        FarmerDepositTicketSystem.confirmDeposit(ticket.farmerUUID);
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

        Set<BlockPos> homeBedClaims = SimpleBedWarningSystem.getHomeBedClaimsInRadius(
                this.getBlockPos(), scanRadius, null);

        Map<UUID, String> citizenNamesMap = SimpleBedWarningSystem.getCitizenNamesInRadius(
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
                                    this.culture.name()
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
                                    this.culture.name()
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
                                    this.culture.name()
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
                    long unitPrice = net.darkflameproduction.agotmod.util.ItemPricing.getItemPrice(itemKey);
                    totalValue += unitPrice * stack.getCount();
                }
            }

            // Add total value to town balance
            if (totalValue > 0) {
                addToTownBalance(totalValue);

                // Apply 20% tax â€” move from townBalance to townIncome
                long taxAmount = (long) Math.floor(totalValue * 0.2);
                if (taxAmount > 0) {
                    deductFromTownBalance(taxAmount);
                    addToTownIncome(taxAmount);
                }
            }
        }

        FoodCollectionTicketSystem.postResponse(ticket.peasantUUID, toGive);
        setChanged();
    }

    private boolean isTownHallFood(net.minecraft.world.item.Item item) {
        net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack(item);

        // Must be a food item
        if (!stack.has(net.minecraft.core.component.DataComponents.FOOD)) return false;

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

    public boolean hasTownInventoryItem(String itemKey, long amount) {
        return townInventory.getOrDefault(itemKey, 0L) >= amount;
    }

    public void cleanupTownInventory() {
        townInventory.entrySet().removeIf(entry -> entry.getValue() <= 0);
    }

    public void addToTownBalance(long amount) {
        if (amount <= 0) return;
        townBalance += amount;
        setChanged();
    }

    public boolean deductFromTownBalance(long amount) {
        if (townBalance < amount) return false;
        townBalance -= amount;
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
            try { culture = Culture.valueOf(tag.getString("Culture")); }
            catch (IllegalArgumentException e) { culture = Culture.NONE; }
        }

        lastScanDay      = tag.getLong("LastScanDay");
        lastChildSpawnDay = tag.getLong("LastChildSpawnDay");
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
                System.err.println("Failed to load citizen data at index " + i + ": " + e.getMessage());
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
                System.err.println("Failed to load job data at index " + i + ": " + e.getMessage());
            }
        }

        joblessQueue.clear();
        int joblessCount = tag.getInt("JoblessQueueCount");
        for (int i = 0; i < joblessCount; i++) {
            if (!tag.hasUUID("JoblessUUID_" + i)) continue;
            try { joblessQueue.offer(tag.getUUID("JoblessUUID_" + i)); }
            catch (Exception e) { System.err.println("Failed to load jobless UUID at index " + i); }
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
