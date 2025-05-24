package net.darkflameproduction.agotmod.entity.custom.npc;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.*;

public class Northern_Peasant_Entity extends PathfinderMob implements GeoEntity, InventoryCarrier {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Long> LAST_SLEEP_TIME = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_IN_MAIN_HAND =
            SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.ITEM_STACK);
    // New hunger data accessor
    private static final EntityDataAccessor<Integer> HUNGER_LEVEL = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.INT);
    // Food collection state tracking
    private static final EntityDataAccessor<Boolean> NEEDS_FOOD_COLLECTION = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.BOOLEAN);

    private static final String[] FIRST_NAMES = {
            "Alastair", "Angus", "Arran", "Blair", "Brody", "Callum", "Campbell", "Cameron", "Cormag", "Craig",
            "Dougal", "Duncan", "Euan", "Ewan", "Fergus", "Finlay", "Fraser", "Gavin", "Gordon", "Graeme",
            "Gregor", "Hamish", "Harris", "Hugh", "Iain", "Innes", "Jamie", "Keith", "Kenneth", "Kerr",
            "Knox", "Lachlan", "Lennox", "Leslie", "Logan", "Malcolm", "Murray", "Neil", "Niall", "Ramsay",
            "Robbie", "Ross", "Ruaridh", "Seamus", "Sholto", "Struan", "Tavish", "Torin", "Wallace", "Watt",
            "Alec", "Archibald", "Baird", "Brodie", "Bruce", "Calum", "Conall", "Dalziel", "Donald", "Donnie",
            "Drummond", "Duff", "Eachann", "Erskine", "Farquhar", "Farquharson", "Findlay", "Gilchrist", "Gillespie", "Grant",
            "Hector", "Ivor", "Jock", "John", "Kennan", "Kyle", "Leith", "Lorne", "Magnus", "Mathew",
            "Maxwell", "Menzies", "Munro", "Murdo", "Nairn", "Paton", "Quinn", "Ranald", "Roderick", "Ronald",
            "Roy", "Shaw", "Sloan", "Stewart", "Tam", "Taran", "Torquil", "Uisdean", "Watson", "Wilson",
            "Eddard", "Robb", "Brandon", "Rickon", "Benjen", "Torrhen", "Cregan", "Rickard", "Harrion", "Arnolf",
            "Karlon", "Roose", "Ramsay", "Domeric", "Galbart", "Robett", "Mors", "Hother", "Jon", "Jeor", "Jorah",
            "Rodrik", "Osric", "Dorren", "Theon", "Wyman", "Cley", "Howland", "Halys", "Leobald", "Donnel",
            "Robin", "Ludd", "Gryff", "Gregor", "Asher", "Ethan", "Arthur", "Eddison", "Vayon", "Wendel",
            "Jonos", "Jory", "Bowen", "Richard","Dickon", "Roger"
    };

    private static final String[] LAST_NAMES = {
            "Ainsley", "Airey", "Archer", "Askew", "Atkinson", "Barker", "Barraclough", "Baxter", "Beadle", "Beckett",
            "Bell", "Benson", "Birkett", "Blackburne", "Blake", "Blenkinsop", "Booth", "Bramley", "Briggs", "Brock",
            "Brown", "Browne", "Bullock", "Butterworth", "Byers", "Calvert", "Carr", "Carter", "Charlton", "Clarke",
            "Clayton", "Close", "Cocker", "Collier", "Cook", "Cooper", "Corbett", "Coulson", "Cowell", "Cowley",
            "Craggs", "Craven", "Crompton", "Crook", "Cross", "Curry", "Dalton", "Dawson", "Dent", "Devine",
            "Dickinson", "Dobson", "Dodds", "Donaldson", "Dunn", "Dyson", "Eddison", "Edgar", "Edson", "Elliott",
            "English", "Etherington", "Fairhurst", "Fawcett", "Ferguson", "Fielding", "Firth", "Fleming", "Forster", "Fox",
            "Frain", "Fraser", "Gibson", "Gill", "Godfrey", "Goodwin", "Graves", "Greenwood", "Gregson", "Grey",
            "Grice", "Griffin", "Hall", "Hampson", "Handley", "Hardman", "Hargreaves", "Harker", "Harrison", "Hartley",
            "Hawkins", "Hayes", "Hewitt", "Hodgson", "Holden", "Hollis", "Holmes", "Hooper", "Hope", "Horner",
            "Houghton", "Howard", "Hudson", "Hughes", "Humphrey", "Hurst", "Ingham", "Jackson", "Jagger", "Jennings",
            "Johnston", "Jordan", "Kay", "Kendrick", "Kenyon", "Kerr", "Knowles", "Lamb", "Lang", "Layton",
            "Leach", "Lloyd", "Lofthouse", "Lord", "Lowes", "Lunn", "Maddison", "Maguire", "Marsden", "Martin",
            "Mason", "Milburn", "Milner", "Moore", "Moss", "Muir", "Murray", "Naylor", "Nelson", "Neville",
            "Nicholson", "Nixon", "Norton", "Ogden", "Oldham", "Orton", "Osborne", "Parkin", "Parr", "Pattinson",
            "Ainsworth", "Archer", "Baxter", "Beadle", "Brewster", "Chapman", "Clark", "Clerk", "Carter", "Collier",
            "Draper", "Fletcher", "Forester", "Gardener", "Glover", "Harper", "Hayward", "Heward", "Hodgson", "Joiner",
            "Leathersmith", "Mercer", "Miller", "Palmer", "Parker", "Parsons", "Porter", "Roper", "Sawyer", "Sexton",
            "Shepherd", "Spencer", "Steward", "Taylor", "Thatcher", "Turner", "Walker","Snow","Snow","Snow","Snow","Snow","Snow","Snow","Snow","Snow","Snow","Snow", "Waller", "Warner", "Watson",
            "Weaver", "Webster", "Wheeler", "Wilkinson", "Wright", "Yeoman", "Yeats", "Young", "Bowyer", "Snow","Snow","Snow","Snow","Snow","Snow","Snow","Snow","Snow","Snow","Lipps", "Farrier"
    };

    private static final int SLEEP_START_TIME = 12542;
    private static final int SLEEP_END_TIME = 23460;
    private static final int NOON_TIME = 6000; // Noon is at 6000 ticks in Minecraft
    private BlockPos bedPos;
    private BlockPos homeBedPos; // The original bed that establishes home base
    private int bedSearchCooldown = 0;

    // Home base system constants
    private static final int HOME_RADIUS_X = 60; // 120x120 area (60 blocks from center)
    private static final int HOME_RADIUS_Z = 60;
    private static final int HOME_RADIUS_Y = 32; // 64 block height (32 up/down from bed level)
    private static final int RETURN_HOME_DISTANCE = 140; // If they get beyond this distance, force return home

    // Eating and healing variables
    private int eatingTime = 0;
    private boolean isEating = false;
    private int healCooldown = 0;
    private static final int HEAL_COOLDOWN_TIME = 600; // 30 seconds cooldown between healing attempts
    private static final int FOOD_CHECK_INTERVAL = 60; // Check for food every 3 seconds when hungry or damaged
    private int foodCheckTimer = 0;

    // Hunger system variables
    private static final int MAX_HUNGER = 20;
    private static final int HUNGER_DECAY_INTERVAL = 1200; // 60 seconds (1200 ticks) between hunger decay
    private static final int PASSIVE_HEAL_INTERVAL = 200; // 10 seconds between passive healing when full hunger
    private int hungerDecayTimer = 0;
    private int passiveHealTimer = 0;

    // Food collection constants
    private static final int MIN_FOOD_COUNT = 15; // Minimum meat items to have in inventory

    public static final int PEASANT_SLOT_OFFSET = 400;
    private static final int PEASANT_INVENTORY_SIZE = 54;
    private final SimpleContainer inventory = new SimpleContainer(PEASANT_INVENTORY_SIZE);

    private static final Map<BlockPos, UUID> bedReservations = new HashMap<>();
    private static final Map<UUID, Long> reservationTimestamps = new HashMap<>();
    private static final long RESERVATION_TIMEOUT = 6000;

    private int stuckTimer = 0;
    private BlockPos lastPosition = null;
    private static final int STUCK_THRESHOLD = 600; // 30 seconds of being stuck
    private static final int TELEPORT_COOLDOWN = 1200; // 60 seconds between teleport attempts
    private int teleportCooldown = 0;

    // Job system constants
    private static final EntityDataAccessor<String> JOB_TYPE = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Optional<BlockPos>> JOB_BLOCK_POS = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);

    // Job block reservations - static maps to track which blocks are taken
    private static final Map<BlockPos, UUID> jobBlockReservations = new HashMap<>();
    private static final Map<UUID, Long> jobReservationTimestamps = new HashMap<>();
    private static final long JOB_RESERVATION_TIMEOUT = 12000; // 10 minutes

    // Job types
    public static final String JOB_FARMER = "farmer";
    public static final String JOB_NONE = "";

    // New farming system variables
    private List<BlockPos> farmPlotPositions = new ArrayList<>(); // Bottom layer (farmland/water)
    private List<BlockPos> cropPositions = new ArrayList<>(); // Top layer (crops)
    private BlockPos farmOrigin = null; // Corner of the farm
    private int farmWidth = 0;
    private int farmHeight = 0;
    private boolean farmRegistered = false;
    private long lastDailyCheck = -1; // Track when daily check was last performed
    private boolean dailyCheckComplete = false;
    private int currentHarvestIndex = 0; // Track which crop we're harvesting

    // Work area system constants
    private static final int FARMER_WORK_RADIUS_X = 40; // 80x80 area around job block
    private static final int FARMER_WORK_RADIUS_Z = 40;
    private static final int FARMER_WORK_RADIUS_Y = 16; // 32 block height around job block

    // Work area system methods
    public boolean isWithinWorkArea(BlockPos pos) {
        if (!this.hasJob() || this.getJobBlockPos() == null) {
            return true; // No job or job block, can go anywhere within home area
        }

        BlockPos jobBlockPos = this.getJobBlockPos();
        int deltaX = Math.abs(pos.getX() - jobBlockPos.getX());
        int deltaZ = Math.abs(pos.getZ() - jobBlockPos.getZ());
        int deltaY = Math.abs(pos.getY() - jobBlockPos.getY());

        // Different work radius based on job type
        if (this.getJobType().equals(JOB_FARMER)) {
            return deltaX <= FARMER_WORK_RADIUS_X && deltaZ <= FARMER_WORK_RADIUS_Z && deltaY <= FARMER_WORK_RADIUS_Y;
        }

        return true; // Default: no restrictions for unknown job types
    }

    public boolean shouldBeAtWorkArea() {
        // Should be at work area during day time (not sleeping) and have a job
        return this.hasJob() && !this.shouldSleep() && !this.needsFoodCollection();
    }

    public boolean isTooFarFromWork() {
        if (!this.hasJob() || this.getJobBlockPos() == null || this.shouldSleep()) {
            return false; // Not working hours or no job
        }

        BlockPos jobBlockPos = this.getJobBlockPos();
        BlockPos currentPos = this.blockPosition();
        double distanceSquared = jobBlockPos.distSqr(currentPos);

        // Return to work if too far (based on job type)
        if (this.getJobType().equals(JOB_FARMER)) {
            return distanceSquared > ((FARMER_WORK_RADIUS_X + 10) * (FARMER_WORK_RADIUS_X + 10));
        }

        return false;
    }

    public BlockPos getWorkCenter() {
        if (this.hasJob() && this.getJobBlockPos() != null) {
            return this.getJobBlockPos();
        }
        return this.getHomeCenter();
    }

    // Farm system methods
    public boolean hasFarmRegistered() {
        return farmRegistered && farmOrigin != null && !farmPlotPositions.isEmpty();
    }

    public void setFarmRegistered(boolean registered) {
        this.farmRegistered = registered;
    }

    public List<BlockPos> getFarmPlotPositions() {
        return farmPlotPositions;
    }

    public List<BlockPos> getCropPositions() {
        return cropPositions;
    }

    public BlockPos getFarmOrigin() {
        return farmOrigin;
    }

    public void setFarmOrigin(BlockPos origin) {
        this.farmOrigin = origin;
    }

    public int getFarmWidth() {
        return farmWidth;
    }

    public void setFarmWidth(int width) {
        this.farmWidth = width;
    }

    public int getFarmHeight() {
        return farmHeight;
    }

    public void setFarmHeight(int height) {
        this.farmHeight = height;
    }

    public boolean isDailyCheckNeeded() {
        long currentDay = this.level().getDayTime() / 24000;
        long lastCheckDay = lastDailyCheck / 24000;
        return currentDay > lastCheckDay;
    }

    public void setLastDailyCheck(long time) {
        this.lastDailyCheck = time;
    }

    public long getLastDailyCheck() {
        return lastDailyCheck;
    }

    public boolean isDailyCheckComplete() {
        return dailyCheckComplete;
    }

    public void setDailyCheckComplete(boolean complete) {
        this.dailyCheckComplete = complete;
    }

    public int getCurrentHarvestIndex() {
        return currentHarvestIndex;
    }

    public void setCurrentHarvestIndex(int index) {
        this.currentHarvestIndex = index;
    }

    public static boolean isBedOccupied(Level level, BlockPos bedPos) {
        return level.getEntitiesOfClass(Northern_Peasant_Entity.class,
                        new net.minecraft.world.phys.AABB(bedPos).inflate(2.0))
                .stream()
                .anyMatch(peasant -> bedPos.equals(peasant.getBedPos()) && peasant.isSleeping());
    }

    public static boolean isBedReserved(BlockPos bedPos, UUID excludeUUID) {
        cleanupExpiredReservations();
        UUID reservedBy = bedReservations.get(bedPos);
        if (reservedBy != null && !reservedBy.equals(excludeUUID)) {
            return true; // Already reserved by someone else
        }
        return false;
    }

    public static boolean reserveBed(BlockPos bedPos, UUID peasantUUID) {
        cleanupExpiredReservations();

        // Quick check if bed is already reserved by someone else
        UUID currentReserver = bedReservations.get(bedPos);
        if (currentReserver != null && !currentReserver.equals(peasantUUID)) {
            return false; // Someone else has it
        }

        // Reserve the bed
        bedReservations.put(bedPos, peasantUUID);
        reservationTimestamps.put(peasantUUID, System.currentTimeMillis());
        return true;
    }

    public static void releaseBedReservation(UUID peasantUUID) {
        bedReservations.entrySet().removeIf(entry -> entry.getValue().equals(peasantUUID));
        reservationTimestamps.remove(peasantUUID);
    }

    private static void cleanupExpiredReservations() {
        long currentTime = System.currentTimeMillis();
        reservationTimestamps.entrySet().removeIf(entry -> {
            if (currentTime - entry.getValue() > RESERVATION_TIMEOUT) {
                UUID expiredUUID = entry.getKey();
                bedReservations.entrySet().removeIf(bedEntry -> bedEntry.getValue().equals(expiredUUID));
                return true;
            }
            return false;
        });
    }

    public Northern_Peasant_Entity(EntityType<? extends Northern_Peasant_Entity> entityType, Level level) {
        super(entityType, level);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
        this.getNavigation().setRequiredPathLength(48.0F);
    }

    /**
     * Generates a random name for the peasant
     */
    private void generateRandomName(RandomSource random) {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String fullName = firstName + " " + lastName;

        this.setCustomName(Component.literal(fullName));
        this.setCustomNameVisible(false);
    }

    /**
     * Controls when the name tag is visible - only when a player is directly looking at the entity
     */
    @Override
    public boolean isCustomNameVisible() {
        return this.hasCustomName() && this.shouldShowName();
    }

    /**
     * Checks if a player is looking directly at this entity
     */
    @Override
    public boolean shouldShowName() {
        if (this.level().isClientSide) {
            return false;
        }

        return true;
    }

    // Home base system methods
    public BlockPos getHomeBedPos() {
        return homeBedPos;
    }

    public void setHomeBedPos(BlockPos pos) {
        this.homeBedPos = pos;
    }

    public boolean hasHomeBed() {
        return this.homeBedPos != null;
    }

    public boolean isWithinHomeArea(BlockPos pos) {
        if (this.homeBedPos == null) {
            return true; // No home established yet, can go anywhere
        }

        int deltaX = Math.abs(pos.getX() - this.homeBedPos.getX());
        int deltaZ = Math.abs(pos.getZ() - this.homeBedPos.getZ());
        int deltaY = Math.abs(pos.getY() - this.homeBedPos.getY());

        return deltaX <= HOME_RADIUS_X && deltaZ <= HOME_RADIUS_Z && deltaY <= HOME_RADIUS_Y;
    }

    public boolean isTooFarFromHome() {
        if (this.homeBedPos == null) {
            return false; // No home established yet
        }

        BlockPos currentPos = this.blockPosition();
        double distanceSquared = this.homeBedPos.distSqr(currentPos);
        return distanceSquared > (RETURN_HOME_DISTANCE * RETURN_HOME_DISTANCE);
    }

    public BlockPos getHomeCenter() {
        return this.homeBedPos != null ? this.homeBedPos : this.blockPosition();
    }

    // Job system methods
    public String getJobType() {
        return this.entityData.get(JOB_TYPE);
    }

    public void setJobType(String jobType) {
        String oldJob = this.getJobType();
        this.entityData.set(JOB_TYPE, jobType);

        // Update name when job changes
        if (!oldJob.equals(jobType)) {
            updateNameWithJob();
        }
    }

    public boolean hasJob() {
        return !this.getJobType().isEmpty();
    }

    public BlockPos getJobBlockPos() {
        return this.entityData.get(JOB_BLOCK_POS).orElse(null);
    }

    public void setJobBlockPos(BlockPos pos) {
        this.entityData.set(JOB_BLOCK_POS, Optional.ofNullable(pos));
    }

    private void updateNameWithJob() {
        if (this.hasCustomName()) {
            String currentName = this.getCustomName().getString();
            String baseName = currentName;

            // Remove existing job title if present
            if (currentName.startsWith("Farmer ")) {
                baseName = currentName.substring(7); // Remove "Farmer "
            }

            // Add new job title
            String newName = baseName;
            if (this.getJobType().equals(JOB_FARMER)) {
                newName = "Farmer " + baseName;
            }

            this.setCustomName(Component.literal(newName));
        }
    }

    // Job block reservation system
    public static boolean isJobBlockReserved(BlockPos blockPos, UUID excludeUUID) {
        cleanupExpiredJobReservations();
        UUID reservedBy = jobBlockReservations.get(blockPos);
        return reservedBy != null && !reservedBy.equals(excludeUUID);
    }

    public static boolean reserveJobBlock(BlockPos blockPos, UUID peasantUUID) {
        cleanupExpiredJobReservations();

        UUID currentReserver = jobBlockReservations.get(blockPos);
        if (currentReserver != null && !currentReserver.equals(peasantUUID)) {
            return false;
        }

        jobBlockReservations.put(blockPos, peasantUUID);
        jobReservationTimestamps.put(peasantUUID, System.currentTimeMillis());
        return true;
    }

    public static void releaseJobBlockReservation(UUID peasantUUID) {
        jobBlockReservations.entrySet().removeIf(entry -> entry.getValue().equals(peasantUUID));
        jobReservationTimestamps.remove(peasantUUID);
    }

    private static void cleanupExpiredJobReservations() {
        long currentTime = System.currentTimeMillis();
        jobReservationTimestamps.entrySet().removeIf(entry -> {
            if (currentTime - entry.getValue() > JOB_RESERVATION_TIMEOUT) {
                UUID expiredUUID = entry.getKey();
                jobBlockReservations.entrySet().removeIf(jobEntry -> jobEntry.getValue().equals(expiredUUID));
                return true;
            }
            return false;
        });
    }

    // Farming utility methods
    private boolean isCropBlock(BlockState state) {
        return state.getBlock() instanceof net.minecraft.world.level.block.CropBlock;
    }

    private boolean isCropFullyGrown(BlockState state) {
        if (state.getBlock() instanceof net.minecraft.world.level.block.CropBlock cropBlock) {
            return cropBlock.isMaxAge(state);
        }
        return false;
    }

    private boolean canBeFarmland(BlockState state) {
        return state.getBlock() == net.minecraft.world.level.block.Blocks.DIRT ||
                state.getBlock() == net.minecraft.world.level.block.Blocks.GRASS_BLOCK;
    }

    private boolean isWater(BlockState state) {
        return state.getBlock() == net.minecraft.world.level.block.Blocks.WATER;
    }

    private boolean isFarmland(BlockState state) {
        return state.getBlock() == net.minecraft.world.level.block.Blocks.FARMLAND;
    }

    private boolean isPathBlock(BlockState state) {
        return state.getBlock() == net.minecraft.world.level.block.Blocks.DIRT_PATH;
    }

    private boolean canBeInsideFarm(BlockState state) {
        return canBeFarmland(state) || isFarmland(state) || isWater(state);
    }

    private ItemStack getMostAbundantSeeds() {
        Map<net.minecraft.world.item.Item, Integer> seedCounts = new HashMap<>();

        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem) {
                net.minecraft.world.level.block.Block block = blockItem.getBlock();
                if (block instanceof net.minecraft.world.level.block.CropBlock) {
                    seedCounts.put(stack.getItem(), seedCounts.getOrDefault(stack.getItem(), 0) + stack.getCount());
                }
            }
        }

        if (seedCounts.isEmpty()) return ItemStack.EMPTY;

        net.minecraft.world.item.Item mostAbundantSeed = Collections.max(seedCounts.entrySet(), Map.Entry.comparingByValue()).getKey();

        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (stack.getItem() == mostAbundantSeed) {
                return stack;
            }
        }

        return ItemStack.EMPTY;
    }

    private void establishHomeBed(BlockPos bedPos) {
        if (this.homeBedPos == null && bedPos != null) {
            this.setHomeBedPos(bedPos);
            // Always prefer the home bed for sleeping
            this.setBedPos(bedPos);
        }
    }

    /**
     * Teleports the peasant to their bed when pathfinding fails
     */
    private void teleportToBed() {
        if (this.getBedPos() != null && teleportCooldown <= 0) {
            BlockPos bedPos = this.getBedPos();

            // Find a safe position near the bed
            BlockPos teleportPos = findSafeTeleportPosition(bedPos);
            if (teleportPos != null) {
                // Teleport the peasant
                this.teleportTo(teleportPos.getX() + 0.5, teleportPos.getY(), teleportPos.getZ() + 0.5);
                this.getNavigation().stop();

                // Reset stuck tracking
                this.stuckTimer = 0;
                this.lastPosition = null;
                this.teleportCooldown = TELEPORT_COOLDOWN;

                // Play teleport sound
                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                        net.minecraft.sounds.SoundEvents.ENDERMAN_TELEPORT, SoundSource.NEUTRAL,
                        0.5F, 1.0F);
            }
        }
    }

    /**
     * Finds a safe position to teleport near the bed
     */
    private BlockPos findSafeTeleportPosition(BlockPos bedPos) {
        // Try positions around the bed
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = -1; y <= 1; y++) {
                    BlockPos checkPos = bedPos.offset(x, y, z);

                    // Check if position is safe (not inside blocks, has ground)
                    if (isSafeTeleportPosition(checkPos)) {
                        return checkPos;
                    }
                }
            }
        }

        // If no safe position found nearby, teleport directly to bed level
        return bedPos;
    }

    /**
     * Checks if a position is safe for teleportation
     */
    private boolean isSafeTeleportPosition(BlockPos pos) {
        BlockState groundState = this.level().getBlockState(pos.below());
        BlockState feetState = this.level().getBlockState(pos);
        BlockState headState = this.level().getBlockState(pos.above());

        // Must have solid ground and air for feet and head
        return groundState.isSolid() &&
                !feetState.isSolid() &&
                !headState.isSolid();
    }

    // Food collection methods
    public boolean needsFoodCollection() {
        return this.entityData.get(NEEDS_FOOD_COLLECTION);
    }

    public void setNeedsFoodCollection(boolean needs) {
        this.entityData.set(NEEDS_FOOD_COLLECTION, needs);
    }

    /**
     * Counts how many meat items are in the peasant's inventory
     */
    public int countMeatInInventory() {
        int count = 0;
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (isFood(stack)) {
                count += stack.getCount();
            }
        }
        return count;
    }

    /**
     * Checks if the peasant has enough food in inventory
     */
    public boolean hasEnoughFood() {
        return countMeatInInventory() >= MIN_FOOD_COUNT;
    }

    /**
     * Triggered when peasant wakes up - checks food levels and sets collection flag
     */
    private void onWakeUp() {
        if (!this.hasEnoughFood()) {
            this.setNeedsFoodCollection(true);
        }
    }

    // Hunger system methods
    public int getHungerLevel() {
        return this.entityData.get(HUNGER_LEVEL);
    }

    public void setHungerLevel(int hunger) {
        this.entityData.set(HUNGER_LEVEL, Math.max(0, Math.min(MAX_HUNGER, hunger)));
    }

    public boolean isHungry() {
        return this.getHungerLevel() < MAX_HUNGER;
    }

    public boolean isFullHunger() {
        return this.getHungerLevel() >= MAX_HUNGER;
    }

    public void addHunger(int amount) {
        this.setHungerLevel(this.getHungerLevel() + amount);
    }

    public void removeHunger(int amount) {
        this.setHungerLevel(this.getHungerLevel() - amount);
    }

    @Override
    public SimpleContainer getInventory() {
        return this.inventory;
    }

    @Override
    public SlotAccess getSlot(int slot) {
        int i = slot - PEASANT_SLOT_OFFSET;
        return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(slot);
    }

    public boolean hasSpace() {
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            if (this.inventory.getItem(i).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean addItem(ItemStack stack) {
        if (stack.isEmpty()) return false;

        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack existing = this.inventory.getItem(i);
            if (!existing.isEmpty() && ItemStack.isSameItemSameComponents(existing, stack)) {
                int maxStackSize = Math.min(existing.getMaxStackSize(), this.inventory.getMaxStackSize());
                int canAdd = maxStackSize - existing.getCount();
                if (canAdd > 0) {
                    int toAdd = Math.min(canAdd, stack.getCount());
                    existing.grow(toAdd);
                    stack.shrink(toAdd);
                    if (stack.isEmpty()) return true;
                }
            }
        }

        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            if (this.inventory.getItem(i).isEmpty()) {
                this.inventory.setItem(i, stack.copy());
                stack.setCount(0);
                return true;
            }
        }

        return false;
    }

    public ItemStack removeItem(ItemStack stack) {
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack existing = this.inventory.getItem(i);
            if (ItemStack.isSameItemSameComponents(existing, stack)) {
                return this.inventory.removeItem(i, stack.getCount());
            }
        }
        return ItemStack.EMPTY;
    }

    public boolean hasItem(ItemStack stack) {
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack existing = this.inventory.getItem(i);
            if (ItemStack.isSameItemSameComponents(existing, stack) && existing.getCount() >= stack.getCount()) {
                return true;
            }
        }
        return false;
    }

    public void dropAllItems() {
        if (this.level() instanceof ServerLevel serverLevel) {
            for (int i = 0; i < this.inventory.getContainerSize(); i++) {
                ItemStack stack = this.inventory.getItem(i);
                if (!stack.isEmpty()) {
                    this.spawnAtLocation(serverLevel, stack);
                    this.inventory.setItem(i, ItemStack.EMPTY);
                }
            }
        }
    }

    // Item holding and eating methods
    public ItemStack getItemInHand(InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {
            return this.entityData.get(DATA_ITEM_IN_MAIN_HAND);
        }
        return ItemStack.EMPTY;
    }

    public void setItemInHand(InteractionHand hand, ItemStack stack) {
        if (hand == InteractionHand.MAIN_HAND) {
            this.entityData.set(DATA_ITEM_IN_MAIN_HAND, stack);
        }
    }

    // Override to provide item for rendering in right arm
    @Override
    public ItemStack getMainHandItem() {
        return this.getItemInHand(InteractionHand.MAIN_HAND);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return Collections.emptyList(); // We don't have armor for peasants
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.getItemInHand(InteractionHand.MAIN_HAND);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND) {
            this.setItemInHand(InteractionHand.MAIN_HAND, stack);
        }
    }

    // Add method to check if currently eating (useful for animations)
    public boolean isCurrentlyEating() {
        return this.isEating;
    }

    // Get the item being eaten (for rendering purposes)
    public ItemStack getEatingItem() {
        return this.isEating ? this.getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
    }

    // Modified food and healing logic to incorporate hunger
    private boolean needsHealing() {
        // Can only heal through passive regeneration when hunger is full
        return false; // No active healing from eating anymore
    }

    private boolean needsFood() {
        return this.isHungry();
    }

    private boolean canEat() {
        return !this.isEating && !this.isSleeping();
    }

    private boolean isFood(ItemStack stack) {
        return stack.is(ItemTags.create(
                ResourceLocation.fromNamespaceAndPath("minecraft", "meat")));
    }

    private ItemStack findFoodInInventory() {
        for (int i = 0; i < this.inventory.getContainerSize(); i++) {
            ItemStack stack = this.inventory.getItem(i);
            if (isFood(stack)) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    public void startEating(ItemStack foodStack) {
        if (!this.isEating && isFood(foodStack)) {
            this.isEating = true;
            this.eatingTime = 0;
            this.setItemInHand(InteractionHand.MAIN_HAND, foodStack.copy());
            this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                    SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL,
                    0.5F + 0.5F * this.getRandom().nextInt(2),
                    (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
        }
    }

    public void finishEating() {
        if (this.isEating) {
            ItemStack foodStack = this.getItemInHand(InteractionHand.MAIN_HAND);
            if (isFood(foodStack)) {
                // Only restore hunger - NO direct healing from eating
                this.addHunger(4); // Add 4 hunger points (1/5 of max hunger)

                // Healing is now ONLY through passive regeneration when hunger = 20/20

                this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                        SoundEvents.PLAYER_BURP, SoundSource.NEUTRAL,
                        0.5F, this.getRandom().nextFloat() * 0.1F + 0.9F);

                for (int i = 0; i < this.inventory.getContainerSize(); i++) {
                    ItemStack stack = this.inventory.getItem(i);
                    if (stack.getItem() == foodStack.getItem()) {
                        stack.shrink(1);
                        if (stack.isEmpty()) {
                            this.inventory.setItem(i, ItemStack.EMPTY);
                        }
                        break;
                    }
                }
            }

            this.isEating = false;
            this.eatingTime = 0;
            this.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND) {
            if (this.isSleeping()) {
                return InteractionResult.PASS;
            }

            if (player instanceof ServerPlayer serverPlayer) {
                openInventoryFor(serverPlayer);
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    public void openInventoryFor(ServerPlayer player) {
        player.openMenu(new SimpleMenuProvider(
                (id, playerInventory, playerEntity) -> new ChestMenu(
                        MenuType.GENERIC_9x6,
                        id,
                        playerInventory,
                        this.inventory,
                        6
                ),
                Component.translatable("entity.agotmod.northern_peasant.inventory", this.getDisplayName())
        ));
    }

    @Override
    public void aiStep() {
        if (!this.isSleeping()) {
            super.aiStep();
        } else {
            this.setDeltaMovement(0, 0, 0);
            this.setYRot(this.yRotO);
            this.setXRot(this.xRotO);
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (bedSearchCooldown > 0) {
            bedSearchCooldown--;
        }

        // Check if peasant is too far from home and force return
        if (!this.level().isClientSide && this.isTooFarFromHome() && !this.isSleeping()) {
            // Cancel current navigation and head back towards home
            this.getNavigation().stop();
            BlockPos homeCenter = this.getHomeCenter();
            this.getNavigation().moveTo(homeCenter.getX(), homeCenter.getY(), homeCenter.getZ(), 0.8D);
        }

        // Check if peasant should be at work area and force return if too far
        if (!this.level().isClientSide && this.shouldBeAtWorkArea() && this.isTooFarFromWork() && !this.isSleeping()) {
            // Cancel current navigation and head to work area
            this.getNavigation().stop();
            BlockPos workCenter = this.getWorkCenter();
            this.getNavigation().moveTo(workCenter.getX(), workCenter.getY(), workCenter.getZ(), 0.8D);
        }

        if (!this.level().isClientSide && this.shouldSleep() && !this.isSleeping() && this.getBedPos() != null) {
            BlockPos currentPos = this.blockPosition();

            // Track if peasant is stuck (not moving toward bed)
            if (this.lastPosition == null) {
                this.lastPosition = currentPos;
                this.stuckTimer = 0;
            } else if (this.lastPosition.equals(currentPos)) {
                // Peasant hasn't moved
                this.stuckTimer++;

                // If stuck for too long and should be sleeping, try teleport
                if (this.stuckTimer >= STUCK_THRESHOLD) {
                    double distanceToBed = currentPos.distSqr(this.getBedPos());

                    // Only teleport if reasonably far from bed and navigation is failing
                    if (distanceToBed > 16.0D && !this.getNavigation().isInProgress()) {
                        this.teleportToBed();
                    }
                }
            } else {
                // Peasant moved, reset stuck tracking
                this.lastPosition = currentPos;
                this.stuckTimer = 0;
            }
        }

        // Reduce teleport cooldown
        if (this.teleportCooldown > 0) {
            this.teleportCooldown--;
        }

        if (!this.level().isClientSide && this.isSleeping() && !this.shouldSleep()) {
            this.stopSleeping();
            // Don't clear bed position if it's their home bed
            if (this.getBedPos() != null && !this.getBedPos().equals(this.homeBedPos)) {
                this.setBedPos(null);
            }
            releaseBedReservation(this.getUUID());

            // Check food levels when waking up
            this.onWakeUp();
        }

        if (this.isSleeping()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            return;
        }

        // Check if current bed still exists
        if (!this.level().isClientSide && this.getBedPos() != null) {
            BlockState bedState = this.level().getBlockState(this.getBedPos());
            if (!(bedState.getBlock() instanceof BedBlock)) {
                // If this was their home bed, clear it and allow them to find a new one
                if (this.getBedPos().equals(this.homeBedPos)) {
                    this.setHomeBedPos(null);
                }
                this.setBedPos(null);
                this.stopSleeping();
                releaseBedReservation(this.getUUID());
            }
        }

        // Check if home bed still exists
        if (!this.level().isClientSide && this.homeBedPos != null) {
            BlockState homeBedState = this.level().getBlockState(this.homeBedPos);
            if (!(homeBedState.getBlock() instanceof BedBlock)) {
                // Home bed is gone, clear it and allow them to establish a new home
                this.setHomeBedPos(null);
                this.setBedPos(null);
                releaseBedReservation(this.getUUID());
            }
        }

        if (!this.level().isClientSide && shouldSleep() && !isSleeping() && this.getBedPos() != null) {
            if (isBedOccupied(this.level(), this.getBedPos()) || isBedReserved(this.getBedPos(), this.getUUID())) {
                // Only clear bed position if it's not their home bed
                if (!this.getBedPos().equals(this.homeBedPos)) {
                    this.setBedPos(null);
                }
                releaseBedReservation(this.getUUID());
            }
        }

        // Handle hunger, healing and eating
        if (!this.level().isClientSide) {
            // Hunger decay over time (not during sleep)
            if (!this.isSleeping()) {
                this.hungerDecayTimer++;
                if (this.hungerDecayTimer >= HUNGER_DECAY_INTERVAL) {
                    this.hungerDecayTimer = 0;
                    this.removeHunger(1); // Lose 1 hunger point every 60 seconds
                }
            }

            // Passive healing when hunger is full
            if (this.isFullHunger() && this.getHealth() < this.getMaxHealth()) {
                this.passiveHealTimer++;
                if (this.passiveHealTimer >= PASSIVE_HEAL_INTERVAL) {
                    this.passiveHealTimer = 0;
                    this.heal(1.0F); // Heal 1 HP every 10 seconds when at full hunger
                }
            } else {
                this.passiveHealTimer = 0; // Reset timer if not at full hunger
            }

            // Reduce heal cooldown
            if (this.healCooldown > 0) {
                this.healCooldown--;
            }

            // Check for food and eat if hungry
            if (!this.isSleeping() && !this.isEating && this.needsFood()) {
                this.foodCheckTimer++;
                if (this.foodCheckTimer >= FOOD_CHECK_INTERVAL) {
                    this.foodCheckTimer = 0;

                    ItemStack foodStack = this.findFoodInInventory();
                    if (!foodStack.isEmpty() && this.canEat()) {
                        this.startEating(foodStack);
                    }
                }
            }

            // Handle eating animation and timing
            if (this.isEating) {
                this.eatingTime++;

                // Play eating sound every 4 ticks while eating
                if (this.eatingTime % 4 == 0) {
                    this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                            SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL,
                            0.5F + 0.5F * this.getRandom().nextInt(2),
                            (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);
                }

                // Finish eating after 32 ticks (same as player)
                if (this.eatingTime >= 32) {
                    this.finishEating();
                }
            }
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new OpenAndCloseDoorGoal(this));
        this.goalSelector.addGoal(2, new CollectFoodGoal(this)); // Highest priority - food collection
        this.goalSelector.addGoal(3, new FindJobGoal(this)); // Job finding - only when not doing other important tasks
        this.goalSelector.addGoal(4, new FindBedGoal(this)); // Navigate to bed
        this.goalSelector.addGoal(5, new SleepGoal(this)); // Sleep when close
        this.goalSelector.addGoal(6, new NewFarmingGoal(this)); // New farming system - lower priority than sleep
        this.goalSelector.addGoal(7, new RestrictedWanderGoal(this, 0.6D)); // Use restricted wandering
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.FOLLOW_RANGE, 48.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_SLEEPING, false);
        builder.define(LAST_SLEEP_TIME, 0L);
        builder.define(DATA_ITEM_IN_MAIN_HAND, ItemStack.EMPTY);
        builder.define(HUNGER_LEVEL, MAX_HUNGER); // Start with full hunger
        builder.define(NEEDS_FOOD_COLLECTION, false); // Start without needing food collection
        builder.define(JOB_TYPE, JOB_NONE);
        builder.define(JOB_BLOCK_POS, Optional.empty());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsSleeping", this.isSleeping());
        compound.putLong("LastSleepTime", this.entityData.get(LAST_SLEEP_TIME));
        compound.putInt("HungerLevel", this.getHungerLevel());
        compound.putBoolean("NeedsFoodCollection", this.needsFoodCollection());

        this.writeInventoryToTag(compound, this.registryAccess());

        if (this.hasCustomName()) {
            compound.putString("CustomName", Component.Serializer.toJson(this.getCustomName(), this.registryAccess()));
        }

        if (bedPos != null) {
            compound.putInt("BedX", bedPos.getX());
            compound.putInt("BedY", bedPos.getY());
            compound.putInt("BedZ", bedPos.getZ());
        }

        // Save home bed position
        if (homeBedPos != null) {
            compound.putInt("HomeBedX", homeBedPos.getX());
            compound.putInt("HomeBedY", homeBedPos.getY());
            compound.putInt("HomeBedZ", homeBedPos.getZ());
        }

        if (this.isSleeping() && this.getSleepingPos().isPresent()) {
            BlockPos sleepPos = this.getSleepingPos().get();
            compound.putInt("SleepingX", sleepPos.getX());
            compound.putInt("SleepingY", sleepPos.getY());
            compound.putInt("SleepingZ", sleepPos.getZ());
            compound.putBoolean("WasSleeping", true);
        }

        // Save eating and healing data
        compound.putInt("HealCooldown", this.healCooldown);
        compound.putInt("FoodCheckTimer", this.foodCheckTimer);
        compound.putBoolean("IsEating", this.isEating);
        compound.putInt("EatingTime", this.eatingTime);

        // Save hunger system data
        compound.putInt("HungerDecayTimer", this.hungerDecayTimer);
        compound.putInt("PassiveHealTimer", this.passiveHealTimer);

        ItemStack mainHandItem = this.getItemInHand(InteractionHand.MAIN_HAND);
        if (!mainHandItem.isEmpty()) {
            compound.put("MainHandItem", mainHandItem.saveOptional(this.registryAccess()));
        }

        // Save teleportation data
        compound.putInt("StuckTimer", this.stuckTimer);
        compound.putInt("TeleportCooldown", this.teleportCooldown);
        if (this.lastPosition != null) {
            compound.putInt("LastPosX", this.lastPosition.getX());
            compound.putInt("LastPosY", this.lastPosition.getY());
            compound.putInt("LastPosZ", this.lastPosition.getZ());
        }

        compound.putString("JobType", this.getJobType());
        if (this.getJobBlockPos() != null) {
            compound.putInt("JobBlockX", this.getJobBlockPos().getX());
            compound.putInt("JobBlockY", this.getJobBlockPos().getY());
            compound.putInt("JobBlockZ", this.getJobBlockPos().getZ());
        }

        // Save new farming system data
        compound.putBoolean("FarmRegistered", this.farmRegistered);
        compound.putLong("LastDailyCheck", this.lastDailyCheck);
        compound.putBoolean("DailyCheckComplete", this.dailyCheckComplete);
        compound.putInt("CurrentHarvestIndex", this.currentHarvestIndex);

        if (this.farmOrigin != null) {
            compound.putInt("FarmOriginX", this.farmOrigin.getX());
            compound.putInt("FarmOriginY", this.farmOrigin.getY());
            compound.putInt("FarmOriginZ", this.farmOrigin.getZ());
        }

        compound.putInt("FarmWidth", this.farmWidth);
        compound.putInt("FarmHeight", this.farmHeight);

        // Save farm plot positions
        ListTag farmPlotList = new ListTag();
        for (BlockPos pos : this.farmPlotPositions) {
            CompoundTag posTag = new CompoundTag();
            posTag.putInt("X", pos.getX());
            posTag.putInt("Y", pos.getY());
            posTag.putInt("Z", pos.getZ());
            farmPlotList.add(posTag);
        }
        compound.put("FarmPlotPositions", farmPlotList);

        // Save crop positions
        ListTag cropPosList = new ListTag();
        for (BlockPos pos : this.cropPositions) {
            CompoundTag posTag = new CompoundTag();
            posTag.putInt("X", pos.getX());
            posTag.putInt("Y", pos.getY());
            posTag.putInt("Z", pos.getZ());
            cropPosList.add(posTag);
        }
        compound.put("CropPositions", cropPosList);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(IS_SLEEPING, compound.getBoolean("IsSleeping"));
        this.entityData.set(LAST_SLEEP_TIME, compound.getLong("LastSleepTime"));
        this.setHungerLevel(compound.getInt("HungerLevel"));
        this.setNeedsFoodCollection(compound.getBoolean("NeedsFoodCollection"));

        this.readInventoryFromTag(compound, this.registryAccess());

        if (compound.contains("CustomName")) {
            try {
                Component customName = Component.Serializer.fromJson(compound.getString("CustomName"), this.registryAccess());
                if (customName != null) {
                    this.setCustomName(customName);
                    this.setCustomNameVisible(false);
                }
            } catch (Exception e) {
                if (!this.level().isClientSide) {
                    this.generateRandomName(this.level().getRandom());
                }
            }
        } else if (!this.hasCustomName() && !this.level().isClientSide) {
            this.generateRandomName(this.level().getRandom());
        }

        if (compound.contains("BedX")) {
            bedPos = new BlockPos(compound.getInt("BedX"), compound.getInt("BedY"), compound.getInt("BedZ"));
        }

        // Load home bed position
        if (compound.contains("HomeBedX")) {
            homeBedPos = new BlockPos(compound.getInt("HomeBedX"), compound.getInt("HomeBedY"), compound.getInt("HomeBedZ"));
        }

        if (compound.getBoolean("WasSleeping") && compound.contains("SleepingX")) {
            BlockPos sleepPos = new BlockPos(
                    compound.getInt("SleepingX"),
                    compound.getInt("SleepingY"),
                    compound.getInt("SleepingZ")
            );
            BlockState bedState = this.level().getBlockState(sleepPos);
            if (bedState.getBlock() instanceof BedBlock) {
                if (this.shouldSleep()) {
                    this.startSleeping(sleepPos);
                    this.setBedPos(sleepPos);
                    reserveBed(sleepPos, this.getUUID());
                } else {
                    this.entityData.set(IS_SLEEPING, false);
                }
            }
        }

        // Load teleportation data
        this.stuckTimer = compound.getInt("StuckTimer");
        this.teleportCooldown = compound.getInt("TeleportCooldown");
        if (compound.contains("LastPosX")) {
            this.lastPosition = new BlockPos(
                    compound.getInt("LastPosX"),
                    compound.getInt("LastPosY"),
                    compound.getInt("LastPosZ")
            );
        }

        // Load eating and healing data
        this.healCooldown = compound.getInt("HealCooldown");
        this.foodCheckTimer = compound.getInt("FoodCheckTimer");
        this.isEating = compound.getBoolean("IsEating");
        this.eatingTime = compound.getInt("EatingTime");

        // Load hunger system data
        this.hungerDecayTimer = compound.getInt("HungerDecayTimer");
        this.passiveHealTimer = compound.getInt("PassiveHealTimer");

        if (compound.contains("MainHandItem")) {
            ItemStack stack = ItemStack.parseOptional(this.registryAccess(), compound.getCompound("MainHandItem"));
            this.setItemInHand(InteractionHand.MAIN_HAND, stack);
        }

        this.setJobType(compound.getString("JobType"));
        if (compound.contains("JobBlockX")) {
            BlockPos jobBlockPos = new BlockPos(compound.getInt("JobBlockX"), compound.getInt("JobBlockY"), compound.getInt("JobBlockZ"));
            this.setJobBlockPos(jobBlockPos);
            // Re-reserve the job block
            if (this.hasJob()) {
                Northern_Peasant_Entity.reserveJobBlock(jobBlockPos, this.getUUID());
            }
        }

        // Load new farming system data
        this.farmRegistered = compound.getBoolean("FarmRegistered");
        this.lastDailyCheck = compound.getLong("LastDailyCheck");
        this.dailyCheckComplete = compound.getBoolean("DailyCheckComplete");
        this.currentHarvestIndex = compound.getInt("CurrentHarvestIndex");

        if (compound.contains("FarmOriginX")) {
            this.farmOrigin = new BlockPos(compound.getInt("FarmOriginX"), compound.getInt("FarmOriginY"), compound.getInt("FarmOriginZ"));
        }

        this.farmWidth = compound.getInt("FarmWidth");
        this.farmHeight = compound.getInt("FarmHeight");

        // Load farm plot positions
        this.farmPlotPositions.clear();
        if (compound.contains("FarmPlotPositions")) {
            ListTag farmPlotList = compound.getList("FarmPlotPositions", Tag.TAG_COMPOUND);
            for (int i = 0; i < farmPlotList.size(); i++) {
                CompoundTag posTag = farmPlotList.getCompound(i);
                BlockPos pos = new BlockPos(posTag.getInt("X"), posTag.getInt("Y"), posTag.getInt("Z"));
                this.farmPlotPositions.add(pos);
            }
        }

        // Load crop positions
        this.cropPositions.clear();
        if (compound.contains("CropPositions")) {
            ListTag cropPosList = compound.getList("CropPositions", Tag.TAG_COMPOUND);
            for (int i = 0; i < cropPosList.size(); i++) {
                CompoundTag posTag = cropPosList.getCompound(i);
                BlockPos pos = new BlockPos(posTag.getInt("X"), posTag.getInt("Y"), posTag.getInt("Z"));
                this.cropPositions.add(pos);
            }
        }
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source == level.damageSources().sweetBerryBush()) {
            return false;
        }

        if (this.isSleeping()) {
            this.stopSleeping();
            // Don't clear bed position if it's their home bed
            if (this.getBedPos() != null && !this.getBedPos().equals(this.homeBedPos)) {
                this.setBedPos(null);
            }
            releaseBedReservation(this.getUUID());
        }

        boolean result = super.hurtServer(level, source, amount);

        // Reset food check timer when damaged to check for food sooner
        if (result && !this.isEating) {
            this.foodCheckTimer = 0;
        }

        return result;
    }

    @Override
    public void remove(RemovalReason reason) {
        releaseJobBlockReservation(this.getUUID());
        if (!this.level().isClientSide && reason == RemovalReason.KILLED) {
            this.dropAllItems();
        }
        releaseBedReservation(this.getUUID());
        super.remove(reason);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isSleeping() || this.isEating) {
            return null;
        } else {
            return SoundEvents.VILLAGER_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.VILLAGER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.VILLAGER_DEATH;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor p_35439_, DifficultyInstance p_35440_, EntitySpawnReason p_363222_, @Nullable SpawnGroupData p_35442_
    ) {
        if (!this.level().isClientSide) {
            this.generateRandomName(p_35439_.getRandom());
        }

        return super.finalizeSpawn(p_35439_, p_35440_, p_363222_, p_35442_);
    }

    public boolean shouldSleep() {
        long dayTime = this.level().getDayTime() % 24000;
        return dayTime >= SLEEP_START_TIME && dayTime <= SLEEP_END_TIME;
    }

    public boolean isSleeping() {
        return this.entityData.get(IS_SLEEPING);
    }

    public void setSleeping(boolean sleeping) {
        this.entityData.set(IS_SLEEPING, sleeping);
        if (sleeping) {
            this.entityData.set(LAST_SLEEP_TIME, this.level().getGameTime());
        }
    }

    public BlockPos getBedPos() {
        return bedPos;
    }

    public void setBedPos(BlockPos pos) {
        if (this.bedPos != null && !this.bedPos.equals(pos)) {
            releaseBedReservation(this.getUUID());
        }
        this.bedPos = pos;
        if (pos != null) {
            reserveBed(pos, this.getUUID());
        }
    }

    @Override
    public void startSleeping(BlockPos pos) {
        super.startSleeping(pos);
        setSleeping(true);
        setBedPos(pos);

        // Establish home base on first sleep
        establishHomeBed(pos);

        this.getNavigation().stop();
        this.setDeltaMovement(0, 0, 0);

        // Stop eating if we start sleeping
        if (this.isEating) {
            this.isEating = false;
            this.eatingTime = 0;
            this.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }
    }

    @Override
    public void stopSleeping() {
        super.stopSleeping();
        setSleeping(false);
    }

    public boolean canUseBedSearch() {
        return bedSearchCooldown <= 0;
    }

    public void setBedSearchCooldown(int cooldown) {
        this.bedSearchCooldown = cooldown;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 0, state -> {
            if (this.isSleeping()) {
                state.setAnimation(ModAnimationDefinitions.IDLE);
            } else if (state.isMoving()) {
                state.setAnimation(ModAnimationDefinitions.WALK);
            } else {
                state.setAnimation(ModAnimationDefinitions.IDLE);
            }
            return PlayState.CONTINUE;
        }));
        controllers.add(ModAnimationDefinitions.ModdedDeathController(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    // New CollectFoodGoal - highest priority goal for food collection after waking up
    static class CollectFoodGoal extends Goal {
        private final Northern_Peasant_Entity peasant;
        private BlockPos targetChest;
        private int searchAttempts = 0;
        private java.util.List<BlockPos> searchedChests = new java.util.ArrayList<>();

        public CollectFoodGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            // Only collect food if we need it and we're not sleeping/eating
            if (!peasant.needsFoodCollection() || peasant.isSleeping() || peasant.isEating) {
                return false;
            }

            // Check if we already have enough food
            if (peasant.hasEnoughFood()) {
                peasant.setNeedsFoodCollection(false);
                return false;
            }

            return findNearestChest();
        }

        private boolean findNearestChest() {
            BlockPos peasantPos = peasant.blockPosition();
            BlockPos closestChest = null;
            double closestDistance = Double.MAX_VALUE;

            // Search in expanding radius
            for (int searchRadius = 8; searchRadius <= 64; searchRadius += 8) {
                for (int x = -searchRadius; x <= searchRadius; x++) {
                    for (int y = -8; y <= 8; y++) {
                        for (int z = -searchRadius; z <= searchRadius; z++) {
                            BlockPos checkPos = peasantPos.offset(x, y, z);

                            // Skip if we've already searched this chest
                            if (searchedChests.contains(checkPos)) {
                                continue;
                            }

                            // Check if it's within home area
                            if (!peasant.isWithinHomeArea(checkPos)) {
                                continue;
                            }

                            BlockState state = peasant.level().getBlockState(checkPos);
                            if (state.getBlock() instanceof ChestBlock) {
                                double distance = peasantPos.distSqr(checkPos);
                                if (distance < closestDistance) {
                                    closestDistance = distance;
                                    closestChest = checkPos;
                                }
                            }
                        }
                    }
                }

                if (closestChest != null) {
                    targetChest = closestChest;
                    return true;
                }
            }

            // No more chests found, stop searching
            if (searchAttempts > 2) {
                peasant.setNeedsFoodCollection(false);
                searchedChests.clear();
                searchAttempts = 0;
            }

            return false;
        }

        @Override
        public boolean canContinueToUse() {
            if (targetChest == null || peasant.isSleeping() || peasant.isEating) {
                return false;
            }

            // Check if we have enough food now
            if (peasant.hasEnoughFood()) {
                peasant.setNeedsFoodCollection(false);
                return false;
            }

            // Check if chest still exists
            BlockState chestState = peasant.level().getBlockState(targetChest);
            if (!(chestState.getBlock() instanceof ChestBlock)) {
                return false;
            }

            return true;
        }

        @Override
        public void start() {
            if (targetChest != null) {
                peasant.getNavigation().moveTo(targetChest.getX() + 0.5, targetChest.getY(), targetChest.getZ() + 0.5, 0.7D);
            }
        }

        @Override
        public void stop() {
            if (targetChest != null) {
                searchedChests.add(targetChest);
            }
            targetChest = null;
            peasant.getNavigation().stop();

            // If we have enough food now, clear the flag
            if (peasant.hasEnoughFood()) {
                peasant.setNeedsFoodCollection(false);
                searchedChests.clear();
                searchAttempts = 0;
            }
        }

        @Override
        public void tick() {
            if (targetChest != null) {
                peasant.getLookControl().setLookAt(targetChest.getX(), targetChest.getY(), targetChest.getZ());

                // If we're close to the chest, try to take items
                if (peasant.distanceToSqr(targetChest.getX(), targetChest.getY(), targetChest.getZ()) <= 4.0D) {
                    tryTakeItemsFromChest();
                    return;
                }

                // Keep moving to chest if not there yet
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(targetChest.getX() + 0.5, targetChest.getY(), targetChest.getZ() + 0.5, 0.7D);
                }
            }
        }

        private void tryTakeItemsFromChest() {
            BlockEntity blockEntity = peasant.level().getBlockEntity(targetChest);
            if (blockEntity instanceof ChestBlockEntity chestEntity) {
                // Check how many meat items we need
                int currentMeat = peasant.countMeatInInventory();
                int neededMeat = MIN_FOOD_COUNT - currentMeat;

                if (neededMeat <= 0) {
                    peasant.setNeedsFoodCollection(false);
                    return;
                }

                // Try to take meat items from the chest
                boolean foundMeat = false;
                for (int i = 0; i < chestEntity.getContainerSize() && neededMeat > 0; i++) {
                    ItemStack chestStack = chestEntity.getItem(i);
                    if (!chestStack.isEmpty() && peasant.isFood(chestStack)) {
                        foundMeat = true;

                        // Calculate how many to take
                        int toTake = Math.min(neededMeat, chestStack.getCount());

                        // Check if peasant has space
                        if (!peasant.hasSpace()) {
                            break;
                        }

                        // Create a copy to add to peasant inventory
                        ItemStack toAdd = chestStack.copy();
                        toAdd.setCount(toTake);

                        // Try to add to peasant inventory
                        if (peasant.addItem(toAdd)) {
                            // Successfully added, remove from chest
                            chestStack.shrink(toTake);
                            if (chestStack.isEmpty()) {
                                chestEntity.setItem(i, ItemStack.EMPTY);
                            } else {
                                chestEntity.setItem(i, chestStack);
                            }
                            neededMeat -= toTake;
                        }
                    }
                }

                // Mark chest as searched and set to find next chest if we still need more food
                searchedChests.add(targetChest);
                targetChest = null;

                if (!foundMeat) {
                    searchAttempts++;
                }

                // Check if we have enough food now
                if (peasant.hasEnoughFood()) {
                    peasant.setNeedsFoodCollection(false);
                    searchedChests.clear();
                    searchAttempts = 0;
                }
            }
        }
    }

    // Custom wandering goal that respects home boundaries
    static class RestrictedWanderGoal extends WaterAvoidingRandomStrollGoal {
        private final Northern_Peasant_Entity peasant;

        public RestrictedWanderGoal(Northern_Peasant_Entity peasant, double speedModifier) {
            super(peasant, speedModifier);
            this.peasant = peasant;
        }

        @Override
        public boolean canUse() {
            // Don't wander if sleeping, eating, or collecting food
            if (peasant.isSleeping() || peasant.isEating || peasant.needsFoodCollection()) {
                return false;
            }

            // Don't wander if too far from work area during work hours
            if (peasant.shouldBeAtWorkArea() && peasant.isTooFarFromWork()) {
                return false;
            }

            // Don't wander if too far from home during non-work hours
            if (!peasant.shouldBeAtWorkArea() && peasant.isTooFarFromHome()) {
                return false;
            }

            return super.canUse();
        }

        @Override
        @Nullable
        protected net.minecraft.world.phys.Vec3 getPosition() {
            net.minecraft.world.phys.Vec3 naturalPosition = super.getPosition();

            // Determine which area to respect based on time of day
            boolean useWorkArea = peasant.shouldBeAtWorkArea();

            if (!useWorkArea && !peasant.hasHomeBed()) {
                return naturalPosition; // No restrictions if no home
            }

            if (useWorkArea && (!peasant.hasJob() || peasant.getJobBlockPos() == null)) {
                return naturalPosition; // No work restrictions if no job
            }

            // Check if natural position is within appropriate bounds
            if (naturalPosition != null) {
                BlockPos targetPos = BlockPos.containing(naturalPosition);
                boolean withinBounds = useWorkArea ? peasant.isWithinWorkArea(targetPos) : peasant.isWithinHomeArea(targetPos);

                if (withinBounds) {
                    return naturalPosition;
                }
            }

            // Find valid position within appropriate bounds
            for (int attempt = 0; attempt < 10; attempt++) {
                int searchRadius = Math.max(2, 10 - (attempt * 2));
                BlockPos currentPos = peasant.blockPosition();
                int randomX = currentPos.getX() + peasant.getRandom().nextInt(searchRadius * 2 + 1) - searchRadius;
                int randomZ = currentPos.getZ() + peasant.getRandom().nextInt(searchRadius * 2 + 1) - searchRadius;
                BlockPos candidatePos = new BlockPos(randomX, currentPos.getY(), randomZ);

                boolean withinBounds = useWorkArea ? peasant.isWithinWorkArea(candidatePos) : peasant.isWithinHomeArea(candidatePos);

                if (withinBounds) {
                    net.minecraft.world.phys.Vec3 candidateVec = net.minecraft.world.phys.Vec3.atBottomCenterOf(candidatePos);
                    if (peasant.getNavigation().isStableDestination(candidatePos)) {
                        return candidateVec;
                    }
                }
            }

            return null;
        }
    }

    static class OpenAndCloseDoorGoal extends Goal {
        private final Northern_Peasant_Entity peasant;
        private final java.util.List<BlockPos> openableBlocks = new java.util.ArrayList<>();
        private final java.util.Set<BlockPos> openedBlocks = new java.util.HashSet<>();

        public OpenAndCloseDoorGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
        }

        @Override
        public boolean canUse() {
            if (peasant.isSleeping() && peasant.getSleepingPos().isPresent()) {
                return false;
            }

            openableBlocks.clear();
            BlockPos peasantPos = peasant.blockPosition();

            // Check for doors and fence gates in a 3x3x3 area around the peasant
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;

                        BlockPos checkPos = peasantPos.offset(x, y, z);
                        BlockState state = peasant.level().getBlockState(checkPos);

                        if (isOpenableBlock(state)) {
                            openableBlocks.add(checkPos);
                        }
                    }
                }
            }

            return !openableBlocks.isEmpty() || !openedBlocks.isEmpty();
        }

        private boolean isOpenableBlock(BlockState state) {
            return state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock ||
                    state.getBlock() instanceof net.minecraft.world.level.block.FenceGateBlock;
        }

        private boolean isBlockOpen(BlockState state) {
            if (state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock) {
                return state.getValue(net.minecraft.world.level.block.DoorBlock.OPEN);
            } else if (state.getBlock() instanceof net.minecraft.world.level.block.FenceGateBlock) {
                return state.getValue(net.minecraft.world.level.block.FenceGateBlock.OPEN);
            }
            return false;
        }

        private void setBlockOpen(BlockPos pos, BlockState state, boolean open) {
            if (state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock doorBlock) {
                doorBlock.setOpen(peasant, peasant.level(), state, pos, open);
            } else if (state.getBlock() instanceof net.minecraft.world.level.block.FenceGateBlock) {
                // For fence gates, we need to manually set the block state
                BlockState newState = state.setValue(net.minecraft.world.level.block.FenceGateBlock.OPEN, open);
                peasant.level().setBlock(pos, newState, 10);

                // Play the gate sound (without player parameter)
                peasant.level().levelEvent(null, open ? 1008 : 1014, pos, 0);
            }
        }

        @Override
        public boolean canContinueToUse() {
            return !openableBlocks.isEmpty() || !openedBlocks.isEmpty();
        }

        @Override
        public void start() {
            // Clear any previously opened blocks from past iterations
            openedBlocks.clear();
        }

        @Override
        public void stop() {
            openableBlocks.clear();
            openedBlocks.clear();
        }

        @Override
        public void tick() {
            BlockPos peasantPos = peasant.blockPosition();

            // Continuously scan for all doors/gates in range and manage them
            java.util.Set<BlockPos> nearbyBlocks = new java.util.HashSet<>();

            // Find all doors/gates in 3x3x3 area
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;

                        BlockPos checkPos = peasantPos.offset(x, y, z);
                        BlockState state = peasant.level().getBlockState(checkPos);

                        if (isOpenableBlock(state)) {
                            nearbyBlocks.add(checkPos);
                        }
                    }
                }
            }

            // Handle each nearby door/gate
            for (BlockPos pos : nearbyBlocks) {
                BlockState state = peasant.level().getBlockState(pos);

                if (!isOpenableBlock(state)) {
                    continue;
                }

                double distance = peasant.distanceToSqr(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

                if (distance <= 4.0) {
                    // Close enough - open it if it's closed
                    if (!isBlockOpen(state)) {
                        setBlockOpen(pos, state, true);
                        openedBlocks.add(pos); // Track that WE opened this door
                    }
                }
            }

            // Check all doors we've opened and close them if we're far enough away
            for (BlockPos pos : new java.util.ArrayList<>(openedBlocks)) {
                BlockState state = peasant.level().getBlockState(pos);

                if (!isOpenableBlock(state)) {
                    openedBlocks.remove(pos);
                    continue;
                }

                double distance = peasant.distanceToSqr(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

                // Close doors we opened when we're far enough away
                if (distance > 4.0 && isBlockOpen(state)) {
                    setBlockOpen(pos, state, false);
                    openedBlocks.remove(pos); // Stop tracking this door
                }
            }
        }
    }

    static class SleepGoal extends Goal {
        private final Northern_Peasant_Entity peasant;

        public SleepGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
        }

        @Override
        public boolean canUse() {
            if (!peasant.shouldSleep() || peasant.isSleeping() || peasant.needsFoodCollection()) {
                return false;
            }

            // Always prefer home bed if it exists and is available
            if (peasant.hasHomeBed()) {
                BlockPos homeBed = peasant.getHomeBedPos();
                BlockState bedState = peasant.level().getBlockState(homeBed);

                if (bedState.getBlock() instanceof BedBlock &&
                        !Northern_Peasant_Entity.isBedOccupied(peasant.level(), homeBed) &&
                        !Northern_Peasant_Entity.isBedReserved(homeBed, peasant.getUUID())) {

                    // Set home bed as current bed if not already set
                    if (!homeBed.equals(peasant.getBedPos())) {
                        peasant.setBedPos(homeBed);
                    }

                    // Only start sleeping if we're close enough to the bed
                    double distanceToBed = peasant.distanceToSqr(homeBed.getX(), homeBed.getY(), homeBed.getZ());
                    return distanceToBed <= 4.0D; // Only sleep if within 2 blocks
                }
            }

            // Fall back to any assigned bed - but also check distance
            if (peasant.getBedPos() != null &&
                    !Northern_Peasant_Entity.isBedOccupied(peasant.level(), peasant.getBedPos()) &&
                    !Northern_Peasant_Entity.isBedReserved(peasant.getBedPos(), peasant.getUUID())) {

                double distanceToBed = peasant.distanceToSqr(peasant.getBedPos().getX(), peasant.getBedPos().getY(), peasant.getBedPos().getZ());
                return distanceToBed <= 4.0D; // Only sleep if within 2 blocks
            }

            return false;
        }

        @Override
        public boolean canContinueToUse() {
            if (!peasant.shouldSleep()) {
                return false;
            }
            if (peasant.getBedPos() != null) {
                BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
                if (!(bedState.getBlock() instanceof BedBlock)) {
                    return false;
                }
            }
            return peasant.isSleeping();
        }

        @Override
        public void start() {
            BlockPos bedPos = peasant.getBedPos();
            if (bedPos != null) {
                // Only start sleeping if we're actually close to the bed
                double distanceToBed = peasant.distanceToSqr(bedPos.getX(), bedPos.getY(), bedPos.getZ());
                if (distanceToBed <= 4.0D) {
                    peasant.startSleeping(bedPos);
                }
            }
        }

        @Override
        public void stop() {
            peasant.stopSleeping();
            if (peasant.getBedPos() != null) {
                BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
                if (!(bedState.getBlock() instanceof BedBlock)) {
                    // If this was their home bed and it's gone, clear home bed
                    if (peasant.getBedPos().equals(peasant.getHomeBedPos())) {
                        peasant.setHomeBedPos(null);
                    }
                    peasant.setBedPos(null);
                }
            }
        }
    }

    static class FindBedGoal extends Goal {
        private final Northern_Peasant_Entity peasant;
        private BlockPos targetBed;
        private int searchAttempts = 0;

        public FindBedGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (!peasant.shouldSleep() || peasant.isSleeping() || !peasant.canUseBedSearch() || peasant.needsFoodCollection()) {
                return false;
            }

            if (peasant.hasHomeBed()) {
                BlockPos homeBed = peasant.getHomeBedPos();
                BlockState bedState = peasant.level().getBlockState(homeBed);

                if (bedState.getBlock() instanceof BedBlock) {
                    if (Northern_Peasant_Entity.isBedOccupied(peasant.level(), homeBed) ||
                            Northern_Peasant_Entity.isBedReserved(homeBed, peasant.getUUID())) {
                        peasant.setBedSearchCooldown(100);
                        return false;
                    } else {
                        double distanceToHomeBed = peasant.distanceToSqr(homeBed.getX(), homeBed.getY(), homeBed.getZ());
                        if (distanceToHomeBed > 4.0D) {
                            targetBed = homeBed;
                            peasant.setBedPos(homeBed);
                            return true;
                        } else {
                            peasant.setBedPos(homeBed);
                            return false;
                        }
                    }
                } else {
                    peasant.setHomeBedPos(null);
                    peasant.setBedPos(null);
                }
            }

            if (peasant.getBedPos() != null) {
                BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
                if (bedState.getBlock() instanceof BedBlock) {
                    if (Northern_Peasant_Entity.isBedOccupied(peasant.level(), peasant.getBedPos()) ||
                            Northern_Peasant_Entity.isBedReserved(peasant.getBedPos(), peasant.getUUID())) {
                        peasant.setBedPos(null);
                    } else {
                        double distanceToBed = peasant.distanceToSqr(peasant.getBedPos().getX(), peasant.getBedPos().getY(), peasant.getBedPos().getZ());
                        if (distanceToBed > 4.0D) {
                            targetBed = peasant.getBedPos();
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    peasant.setBedPos(null);
                }
            }

            return findNearbyBed();
        }

        private boolean findNearbyBed() {
            BlockPos peasantPos = peasant.blockPosition();

            for (int searchRadius = 16; searchRadius <= 96; searchRadius += 10) {
                BlockPos closestBed = null;
                double closestDistance = Double.MAX_VALUE;

                for (int x = -searchRadius; x <= searchRadius; x++) {
                    for (int y = -16; y <= 16; y++) {
                        for (int z = -searchRadius; z <= searchRadius; z++) {
                            BlockPos checkPos = peasantPos.offset(x, y, z);
                            BlockState state = peasant.level().getBlockState(checkPos);

                            if (state.getBlock() instanceof BedBlock) {
                                if (state.getValue(BedBlock.PART) == BedPart.HEAD &&
                                        !Northern_Peasant_Entity.isBedOccupied(peasant.level(), checkPos) &&
                                        !Northern_Peasant_Entity.isBedReserved(checkPos, peasant.getUUID())) {

                                    double distance = peasantPos.distSqr(checkPos);

                                    if (distance < closestDistance) {
                                        closestDistance = distance;
                                        closestBed = checkPos;
                                    }
                                }
                            }
                        }
                    }
                }

                if (closestBed != null) {
                    targetBed = closestBed;
                    return true;
                }
            }

            searchAttempts++;
            if (searchAttempts > 3) {
                searchAttempts = 0;
            }

            peasant.setBedSearchCooldown(100);
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            if (targetBed == null || !peasant.shouldSleep() || peasant.needsFoodCollection()) {
                return false;
            }

            if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                return false;
            }

            BlockState bedState = peasant.level().getBlockState(targetBed);
            if (!(bedState.getBlock() instanceof BedBlock)) {
                return false;
            }

            return true;
        }

        @Override
        public void start() {
            if (targetBed != null) {
                peasant.getNavigation().moveTo(targetBed.getX() + 0.5, targetBed.getY(), targetBed.getZ() + 0.5, 0.7D);
            }
        }

        @Override
        public void stop() {
            if (targetBed != null && peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                BlockState bedState = peasant.level().getBlockState(targetBed);
                if (bedState.getBlock() instanceof BedBlock &&
                        bedState.getValue(BedBlock.PART) == BedPart.HEAD &&
                        !Northern_Peasant_Entity.isBedOccupied(peasant.level(), targetBed) &&
                        !Northern_Peasant_Entity.isBedReserved(targetBed, peasant.getUUID())) {
                    peasant.setBedPos(targetBed);
                }
            }
            targetBed = null;
            searchAttempts = 0;
            peasant.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (targetBed != null) {
                peasant.getLookControl().setLookAt(targetBed.getX(), targetBed.getY(), targetBed.getZ());

                if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                    BlockState bedState = peasant.level().getBlockState(targetBed);
                    if (bedState.getBlock() instanceof BedBlock &&
                            bedState.getValue(BedBlock.PART) == BedPart.HEAD) {
                        peasant.setBedPos(targetBed);
                        peasant.getNavigation().stop();
                        return;
                    }
                }

                if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 16.0D) {
                    if (Northern_Peasant_Entity.isBedOccupied(peasant.level(), targetBed) ||
                            Northern_Peasant_Entity.isBedReserved(targetBed, peasant.getUUID())) {
                        targetBed = null;
                        peasant.getNavigation().stop();
                        peasant.setBedSearchCooldown(20);
                        return;
                    }
                }

                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(targetBed.getX() + 0.5, targetBed.getY(), targetBed.getZ() + 0.5, 0.7D);
                }
            }
        }
    }

    static class FindJobGoal extends Goal {
        private final Northern_Peasant_Entity peasant;
        private BlockPos targetJobBlock;
        private int searchCooldown = 0;
        private boolean hasTriedThisBlock = false;

        public FindJobGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            // Don't look for jobs if already have one, sleeping, eating, or collecting food
            if (peasant.hasJob() || peasant.isSleeping() || peasant.isEating ||
                    peasant.needsFoodCollection() || searchCooldown > 0) {
                return false;
            }

            return findNearbyJobBlock();
        }

        private boolean findNearbyJobBlock() {
            BlockPos peasantPos = peasant.blockPosition();

            for (int radius = 8; radius <= 64; radius += 8) {
                for (int x = -radius; x <= radius; x++) {
                    for (int y = -8; y <= 8; y++) {
                        for (int z = -radius; z <= radius; z++) {
                            BlockPos checkPos = peasantPos.offset(x, y, z);

                            if (!peasant.isWithinHomeArea(checkPos)) {
                                continue;
                            }

                            BlockState state = peasant.level().getBlockState(checkPos);

                            // Check for composter (farmer job block)
                            if (state.getBlock() == net.minecraft.world.level.block.Blocks.COMPOSTER &&
                                    !Northern_Peasant_Entity.isJobBlockReserved(checkPos, peasant.getUUID())) {
                                targetJobBlock = checkPos;
                                hasTriedThisBlock = false;
                                return true;
                            }
                        }
                    }
                }
            }

            searchCooldown = 600; // 30 second cooldown if no job block found
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            if (targetJobBlock == null || peasant.isSleeping() || peasant.hasJob()) {
                return false;
            }

            BlockState state = peasant.level().getBlockState(targetJobBlock);
            if (state.getBlock() != net.minecraft.world.level.block.Blocks.COMPOSTER) {
                return false;
            }

            // Stop if someone else took this job block
            if (Northern_Peasant_Entity.isJobBlockReserved(targetJobBlock, peasant.getUUID())) {
                return false;
            }

            return !hasTriedThisBlock;
        }

        @Override
        public void start() {
            if (targetJobBlock != null) {
                peasant.getNavigation().moveTo(targetJobBlock.getX() + 0.5, targetJobBlock.getY(), targetJobBlock.getZ() + 0.5, 0.6D);
            }
        }

        @Override
        public void stop() {
            if (!peasant.hasJob()) {
                // Only set cooldown if we failed to get a job
                searchCooldown = 200; // 10 second cooldown before trying again
            }
            targetJobBlock = null;
            hasTriedThisBlock = false;
            peasant.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (searchCooldown > 0) {
                searchCooldown--;
            }

            if (targetJobBlock != null && !hasTriedThisBlock) {
                double distance = peasant.distanceToSqr(targetJobBlock.getX(), targetJobBlock.getY(), targetJobBlock.getZ());

                if (distance <= 4.0D) {
                    // Close enough to attempt claiming the job block
                    hasTriedThisBlock = true;

                    if (Northern_Peasant_Entity.reserveJobBlock(targetJobBlock, peasant.getUUID())) {
                        peasant.setJobType(JOB_FARMER);
                        peasant.setJobBlockPos(targetJobBlock);
                        // Successfully got job, goal will end naturally
                    } else {
                        // Failed to get job (someone else got it), stop trying this block
                        // Goal will end and set a cooldown
                    }
                    return;
                }

                // Keep moving toward the job block
                if (!peasant.getNavigation().isInProgress()) {
                    peasant.getNavigation().moveTo(targetJobBlock.getX() + 0.5, targetJobBlock.getY(), targetJobBlock.getZ() + 0.5, 0.6D);
                }
            }
        }
    }

    // NEW FARMING GOAL - Complete rewrite based on your specifications
    static class NewFarmingGoal extends Goal {
        private final Northern_Peasant_Entity peasant;
        private FarmingState currentState = FarmingState.IDLE;
        private FarmingState lastState = FarmingState.IDLE;
        private BlockPos targetHarvestPos = null;
        private int taskTimer = 0;
        private boolean hasReturnedToJobBlock = false;
        private int harvestCount = 0;
        private int lastMessageTick = 0;
        private static final int MESSAGE_COOLDOWN = 100; // 5 seconds between messages

        enum FarmingState {
            IDLE,
            GOING_TO_JOB_BLOCK,
            SCANNING_FOR_FARM,
            DAILY_CHECK,
            HARVESTING
        }

        public NewFarmingGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        @Override
        public boolean canUse() {
            // Don't farm if not a farmer, sleeping, eating, collecting food, or should be sleeping soon
            if (!peasant.getJobType().equals(JOB_FARMER) || peasant.isSleeping() ||
                    peasant.isEating || peasant.needsFoodCollection()) {
                return false;
            }

            // Don't start farming if it's close to sleep time (1 hour before sleep starts)
            long dayTime = peasant.level().getDayTime() % 24000;
            long timeUntilSleep = (SLEEP_START_TIME - dayTime + 24000) % 24000;
            if (timeUntilSleep < 1000) { // Less than 1000 ticks (50 seconds) until sleep
                return false;
            }

            // Don't farm during sleep hours
            if (peasant.shouldSleep()) {
                return false;
            }

            // Check if job block still exists
            BlockPos jobBlockPos = peasant.getJobBlockPos();
            if (jobBlockPos != null) {
                BlockState jobBlockState = peasant.level().getBlockState(jobBlockPos);
                if (jobBlockState.getBlock() != net.minecraft.world.level.block.Blocks.COMPOSTER) {
                    // Job block destroyed, lose job
                    peasant.setJobType(JOB_NONE);
                    peasant.setJobBlockPos(null);
                    Northern_Peasant_Entity.releaseJobBlockReservation(peasant.getUUID());
                    return false;
                }
            }

            // After collecting food, must return to job block first
            if (!hasReturnedToJobBlock && peasant.getJobBlockPos() != null) {
                currentState = FarmingState.GOING_TO_JOB_BLOCK;
                return true;
            }

            // If no farm registered, scan for one
            if (!peasant.hasFarmRegistered()) {
                currentState = FarmingState.SCANNING_FOR_FARM;
                return true;
            }

            // Check if daily check is needed (at noon)
            if (isNoonTime() && peasant.isDailyCheckNeeded()) {
                currentState = FarmingState.DAILY_CHECK;
                return true;
            }

            // If daily check is complete, do harvesting
            if (peasant.isDailyCheckComplete()) {
                currentState = FarmingState.HARVESTING;
                return true;
            }

            return false;
        }

        private boolean isNoonTime() {
            long dayTime = peasant.level().getDayTime() % 24000;
            return Math.abs(dayTime - NOON_TIME) < 100; // Within 100 ticks of noon
        }

        @Override
        public boolean canContinueToUse() {
            // Stop farming if should sleep, collecting food, or eating
            if (peasant.shouldSleep() || peasant.isSleeping() ||
                    peasant.needsFoodCollection() || peasant.isEating) {
                return false;
            }

            // Stop farming if getting close to sleep time
            long dayTime = peasant.level().getDayTime() % 24000;
            long timeUntilSleep = (SLEEP_START_TIME - dayTime + 24000) % 24000;
            if (timeUntilSleep < 500) { // Less than 500 ticks (25 seconds) until sleep
                return false;
            }

            return currentState != FarmingState.IDLE;
        }

        @Override
        public void start() {
            taskTimer = 0;
            targetHarvestPos = null;
            harvestCount = 0;
            lastMessageTick = 0;
        }

        @Override
        public void stop() {
            currentState = FarmingState.IDLE;
            lastState = FarmingState.IDLE;
            targetHarvestPos = null;
            peasant.getNavigation().stop();
        }

        @Override
        public void tick() {
            taskTimer++;

            // Send chat message when state changes
            if (currentState != lastState && taskTimer - lastMessageTick > MESSAGE_COOLDOWN) {
                sendStateMessage();
                lastState = currentState;
                lastMessageTick = taskTimer;
            }

            switch (currentState) {
                case GOING_TO_JOB_BLOCK:
                    handleGoingToJobBlock();
                    break;
                case SCANNING_FOR_FARM:
                    handleFarmScanning();
                    break;
                case DAILY_CHECK:
                    handleDailyCheck();
                    break;
                case HARVESTING:
                    handleHarvesting();
                    break;
            }
        }

        private void sendStateMessage() {
            if (peasant.level().isClientSide || !peasant.hasCustomName()) {
                return;
            }

            String farmerName = peasant.getCustomName().getString();
            String message = "";

            switch (currentState) {
                case GOING_TO_JOB_BLOCK:
                    message = farmerName + ": Heading back to my work station to start the day.";
                    break;
                case SCANNING_FOR_FARM:
                    message = farmerName + ": Looking for a suitable farm plot to tend...";
                    break;
                case DAILY_CHECK:
                    message = farmerName + ": Time for my daily farm inspection and maintenance.";
                    break;
                case HARVESTING:
                    if (harvestCount == 0) {
                        message = farmerName + ": Starting my harvest rounds for the day.";
                    }
                    break;
            }

            if (!message.isEmpty()) {
                final String finalMessage = message; // Make it final for lambda
                // Send message to all players in the area
                peasant.level().players().forEach(player -> {
                    if (player.distanceToSqr(peasant.getX(), peasant.getY(), peasant.getZ()) < 1024) { // 32 block radius
                        player.displayClientMessage(Component.literal(finalMessage), false);
                    }
                });
            }
        }

        private void sendHarvestMessage(int cropsHarvested) {
            if (peasant.level().isClientSide || !peasant.hasCustomName()) {
                return;
            }

            if (taskTimer - lastMessageTick < MESSAGE_COOLDOWN) {
                return;
            }

            String farmerName = peasant.getCustomName().getString();
            String message = "";

            if (cropsHarvested > 0) {
                if (cropsHarvested == 1) {
                    message = farmerName + ": Harvested and replanted a crop. The farm looks good!";
                } else if (cropsHarvested < 5) {
                    message = farmerName + ": Harvested " + cropsHarvested + " crops so far. Still checking for more.";
                } else if (cropsHarvested % 10 == 0) {
                    message = farmerName + ": Busy day! Harvested " + cropsHarvested + " crops already.";
                }
            } else {
                if (taskTimer % 600 == 0) { // Every 30 seconds when no crops
                    message = farmerName + ": Patrolling the farm. No ripe crops at the moment.";
                }
            }

            if (!message.isEmpty()) {
                lastMessageTick = taskTimer;
                final String finalMessage = message; // Make it final for lambda
                // Send message to all players in the area
                peasant.level().players().forEach(player -> {
                    if (player.distanceToSqr(peasant.getX(), peasant.getY(), peasant.getZ()) < 1024) { // 32 block radius
                        player.displayClientMessage(Component.literal(finalMessage), false);
                    }
                });
            }
        }

        private void sendFarmFoundMessage(int width, int height) {
            if (peasant.level().isClientSide || !peasant.hasCustomName()) {
                return;
            }

            String farmerName = peasant.getCustomName().getString();
            final String message = farmerName + ": Found a " + width + "x" + height + " farm plot! I'll take good care of it.";

            // Send message to all players in the area
            peasant.level().players().forEach(player -> {
                if (player.distanceToSqr(peasant.getX(), peasant.getY(), peasant.getZ()) < 1024) { // 32 block radius
                    player.displayClientMessage(Component.literal(message), false);
                }
            });
        }

        private void sendDailyCheckCompleteMessage(int plotsConverted, int cropsPlanted) {
            if (peasant.level().isClientSide || !peasant.hasCustomName()) {
                return;
            }

            String farmerName = peasant.getCustomName().getString();
            String message = "";

            if (plotsConverted > 0 && cropsPlanted > 0) {
                message = farmerName + ": Daily check complete! Converted " + plotsConverted + " plots to farmland and planted " + cropsPlanted + " crops.";
            } else if (plotsConverted > 0) {
                message = farmerName + ": Daily check complete! Converted " + plotsConverted + " plots to farmland.";
            } else if (cropsPlanted > 0) {
                message = farmerName + ": Daily check complete! Planted " + cropsPlanted + " new crops.";
            } else {
                message = farmerName + ": Daily check complete! Farm is in perfect condition.";
            }

            final String finalMessage = message; // Make it final for lambda
            // Send message to all players in the area
            peasant.level().players().forEach(player -> {
                if (player.distanceToSqr(peasant.getX(), peasant.getY(), peasant.getZ()) < 1024) { // 32 block radius
                    player.displayClientMessage(Component.literal(finalMessage), false);
                }
            });
        }

        private void handleGoingToJobBlock() {
            BlockPos jobBlockPos = peasant.getJobBlockPos();
            if (jobBlockPos == null) {
                currentState = FarmingState.IDLE;
                return;
            }

            double distance = peasant.distanceToSqr(jobBlockPos.getX(), jobBlockPos.getY(), jobBlockPos.getZ());

            if (distance <= 4.0D) {
                // Reached job block
                hasReturnedToJobBlock = true;
                currentState = FarmingState.IDLE; // Will trigger next state in canUse()
                return;
            }

            // Keep moving to job block
            if (!peasant.getNavigation().isInProgress()) {
                peasant.getNavigation().moveTo(jobBlockPos.getX() + 0.5, jobBlockPos.getY(), jobBlockPos.getZ() + 0.5, 0.6D);
            }
        }

        private void handleFarmScanning() {
            // Scan for farm plot without physically moving
            BlockPos foundFarm = scanForFarmPlot();
            if (foundFarm != null) {
                registerFarmPlot(foundFarm);
                peasant.setFarmRegistered(true);
                sendFarmFoundMessage(peasant.getFarmWidth(), peasant.getFarmHeight());
                currentState = FarmingState.IDLE; // Will trigger daily check if needed
            } else {
                // No farm found, stay idle until one is built
                currentState = FarmingState.IDLE;
            }
        }

        private BlockPos scanForFarmPlot() {
            BlockPos peasantPos = peasant.blockPosition();

            // Search in work area
            for (int radius = 8; radius <= 40; radius += 8) {
                for (int x = -radius; x <= radius; x += 4) {
                    for (int z = -radius; z <= radius; z += 4) {
                        for (int y = -8; y <= 8; y += 2) {
                            BlockPos checkPos = peasantPos.offset(x, y, z);

                            if (!peasant.isWithinWorkArea(checkPos)) {
                                continue;
                            }

                            // Look for path blocks that might be farm borders
                            if (peasant.isPathBlock(peasant.level().getBlockState(checkPos))) {
                                BlockPos farmOrigin = analyzePotentialFarm(checkPos);
                                if (farmOrigin != null) {
                                    return farmOrigin;
                                }
                            }
                        }
                    }
                }
            }

            return null;
        }

        private BlockPos analyzePotentialFarm(BlockPos pathPos) {
            // Check if this path block is part of a rectangular farm border
            // Try different farm sizes from 3x3 to 20x20
            for (int width = 3; width <= 20; width++) {
                for (int height = 3; height <= 20; height++) {
                    // Try this path block as different positions in the border
                    // Check as top-left corner of border
                    BlockPos potentialCorner = pathPos;
                    if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                        return potentialCorner.offset(1, 0, 1); // Inside the farm area
                    }

                    // Check as top-right corner of border
                    potentialCorner = pathPos.offset(-width, 0, 0);
                    if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                        return potentialCorner.offset(1, 0, 1);
                    }

                    // Check as bottom-left corner of border
                    potentialCorner = pathPos.offset(0, 0, -height);
                    if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                        return potentialCorner.offset(1, 0, 1);
                    }

                    // Check as bottom-right corner of border
                    potentialCorner = pathPos.offset(-width, 0, -height);
                    if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                        return potentialCorner.offset(1, 0, 1);
                    }

                    // Check as points along the edges
                    for (int edgePos = 1; edgePos < width - 1; edgePos++) {
                        // Top edge
                        potentialCorner = pathPos.offset(-edgePos, 0, 0);
                        if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                            return potentialCorner.offset(1, 0, 1);
                        }

                        // Bottom edge
                        potentialCorner = pathPos.offset(-edgePos, 0, -height);
                        if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                            return potentialCorner.offset(1, 0, 1);
                        }
                    }

                    for (int edgePos = 1; edgePos < height - 1; edgePos++) {
                        // Left edge
                        potentialCorner = pathPos.offset(0, 0, -edgePos);
                        if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                            return potentialCorner.offset(1, 0, 1);
                        }

                        // Right edge
                        potentialCorner = pathPos.offset(-width, 0, -edgePos);
                        if (validateCompleteRectangularFarm(potentialCorner, width, height)) {
                            return potentialCorner.offset(1, 0, 1);
                        }
                    }
                }
            }

            return null;
        }

        private boolean validateCompleteRectangularFarm(BlockPos corner, int width, int height) {
            // Validate that we have a complete rectangular border of path blocks
            // surrounding an interior of valid farm blocks

            // Check the complete perimeter - must ALL be path blocks
            // Top and bottom edges (including corners)
            for (int x = 0; x <= width + 1; x++) {
                // Top edge
                BlockPos topPos = corner.offset(x, 0, 0);
                if (!peasant.isPathBlock(peasant.level().getBlockState(topPos))) {
                    return false;
                }

                // Bottom edge
                BlockPos bottomPos = corner.offset(x, 0, height + 1);
                if (!peasant.isPathBlock(peasant.level().getBlockState(bottomPos))) {
                    return false;
                }
            }

            // Left and right edges (excluding corners to avoid double-checking)
            for (int z = 1; z <= height; z++) {
                // Left edge
                BlockPos leftPos = corner.offset(0, 0, z);
                if (!peasant.isPathBlock(peasant.level().getBlockState(leftPos))) {
                    return false;
                }

                // Right edge
                BlockPos rightPos = corner.offset(width + 1, 0, z);
                if (!peasant.isPathBlock(peasant.level().getBlockState(rightPos))) {
                    return false;
                }
            }

            // Check the interior - must ALL be valid farm blocks (dirt, grass, farmland, or water)
            for (int x = 1; x <= width; x++) {
                for (int z = 1; z <= height; z++) {
                    BlockPos interiorPos = corner.offset(x, 0, z);
                    BlockState interiorState = peasant.level().getBlockState(interiorPos);

                    if (!peasant.canBeInsideFarm(interiorState)) {
                        return false;
                    }
                }
            }

            // If we get here, we have a valid rectangular farm
            return true;
        }

        private void registerFarmPlot(BlockPos farmOrigin) {
            // Find the actual dimensions by scanning from origin
            int width = 0;
            int height = 0;

            // Scan east to find width
            for (int x = 0; x < 50; x++) {
                BlockPos checkPos = farmOrigin.offset(x, 0, 0);
                BlockState state = peasant.level().getBlockState(checkPos);
                if (peasant.canBeInsideFarm(state)) {
                    width = x + 1;
                } else {
                    break;
                }
            }

            // Scan south to find height
            for (int z = 0; z < 50; z++) {
                BlockPos checkPos = farmOrigin.offset(0, 0, z);
                BlockState state = peasant.level().getBlockState(checkPos);
                if (peasant.canBeInsideFarm(state)) {
                    height = z + 1;
                } else {
                    break;
                }
            }

            // Register all positions
            peasant.setFarmOrigin(farmOrigin);
            peasant.setFarmWidth(width);
            peasant.setFarmHeight(height);

            // Clear and populate farm positions
            peasant.getFarmPlotPositions().clear();
            peasant.getCropPositions().clear();

            for (int x = 0; x < width; x++) {
                for (int z = 0; z < height; z++) {
                    BlockPos plotPos = farmOrigin.offset(x, 0, z);
                    BlockPos cropPos = plotPos.above();

                    peasant.getFarmPlotPositions().add(plotPos);
                    peasant.getCropPositions().add(cropPos);
                }
            }
        }

        private void handleDailyCheck() {
            if (taskTimer == 1) {
                // Mark daily check as started
                peasant.setLastDailyCheck(peasant.level().getDayTime());
                int[] results = performDailyFarmCheck(); // Returns [plotsConverted, cropsPlanted]
                sendDailyCheckCompleteMessage(results[0], results[1]);
                peasant.setDailyCheckComplete(true);
                peasant.setCurrentHarvestIndex(0); // Reset harvest index
                currentState = FarmingState.IDLE; // Will trigger harvesting
            }
        }

        private int[] performDailyFarmCheck() {
            if (!peasant.hasFarmRegistered()) {
                return new int[]{0, 0};
            }

            int plotsConverted = 0;
            int cropsPlanted = 0;

            ItemStack seeds = peasant.getMostAbundantSeeds();
            net.minecraft.world.level.block.Block cropBlock = null;

            if (seeds.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                    blockItem.getBlock() instanceof net.minecraft.world.level.block.CropBlock) {
                cropBlock = blockItem.getBlock();
            }

            // Phase 1: Convert grass/dirt to farmland (skip water)
            for (BlockPos plotPos : peasant.getFarmPlotPositions()) {
                BlockState currentState = peasant.level().getBlockState(plotPos);

                if (peasant.canBeFarmland(currentState)) {
                    // Convert dirt/grass to farmland
                    peasant.level().setBlock(plotPos, net.minecraft.world.level.block.Blocks.FARMLAND.defaultBlockState(), 3);
                    plotsConverted++;
                }
                // Leave water and existing farmland as-is
            }

            // Phase 2: Plant crops on farmland (skip water positions)
            if (cropBlock != null) {
                for (BlockPos cropPos : peasant.getCropPositions()) {
                    BlockPos plotBelow = cropPos.below();
                    BlockState plotState = peasant.level().getBlockState(plotBelow);
                    BlockState cropState = peasant.level().getBlockState(cropPos);

                    // Only plant on farmland, not on water
                    if (peasant.isFarmland(plotState) && cropState.isAir()) {
                        peasant.level().setBlock(cropPos, cropBlock.defaultBlockState(), 3);
                        cropsPlanted++;

                        // Consume seed from inventory
                        for (int i = 0; i < peasant.inventory.getContainerSize(); i++) {
                            ItemStack stack = peasant.inventory.getItem(i);
                            if (stack.getItem() == seeds.getItem()) {
                                stack.shrink(1);
                                if (stack.isEmpty()) {
                                    peasant.inventory.setItem(i, ItemStack.EMPTY);
                                }
                                break;
                            }
                        }
                    }
                }
            }

            return new int[]{plotsConverted, cropsPlanted};
        }

        private void handleHarvesting() {
            // Find next crop to harvest
            if (targetHarvestPos == null) {
                targetHarvestPos = findNextHarvestCrop();
            }

            if (targetHarvestPos == null) {
                // No more crops to harvest - send patrol message occasionally
                sendHarvestMessage(0);
                currentState = FarmingState.IDLE;
                return;
            }

            // Check if crop is still harvestable
            BlockState cropState = peasant.level().getBlockState(targetHarvestPos);
            if (peasant.isCropFullyGrown(cropState)) {
                harvestCrop(targetHarvestPos);
                harvestCount++;
                sendHarvestMessage(harvestCount);
                targetHarvestPos = null; // Look for next crop
            } else {
                // Crop is no longer ready, find next one
                targetHarvestPos = null;
            }
        }

        private BlockPos findNextHarvestCrop() {
            if (!peasant.hasFarmRegistered()) {
                return null;
            }

            List<BlockPos> cropPositions = peasant.getCropPositions();
            int startIndex = peasant.getCurrentHarvestIndex();

            for (int i = 0; i < cropPositions.size(); i++) {
                int index = (startIndex + i) % cropPositions.size();
                BlockPos cropPos = cropPositions.get(index);
                BlockState cropState = peasant.level().getBlockState(cropPos);

                if (peasant.isCropFullyGrown(cropState)) {
                    peasant.setCurrentHarvestIndex(index + 1); // Set next starting point
                    return cropPos;
                }
            }

            return null; // No crops ready for harvest
        }

        private void harvestCrop(BlockPos cropPos) {
            BlockState cropState = peasant.level().getBlockState(cropPos);

            if (peasant.level() instanceof ServerLevel serverLevel) {
                // Drop loot
                java.util.List<ItemStack> drops = net.minecraft.world.level.block.Block.getDrops(cropState, serverLevel, cropPos, null);
                for (ItemStack drop : drops) {
                    peasant.addItem(drop);
                }

                // Replace with new crop
                if (cropState.getBlock() instanceof net.minecraft.world.level.block.CropBlock cropBlock) {
                    BlockState newCropState = cropBlock.defaultBlockState();
                    peasant.level().setBlock(cropPos, newCropState, 3);

                    // Consume one seed from inventory
                    for (int i = 0; i < peasant.inventory.getContainerSize(); i++) {
                        ItemStack stack = peasant.inventory.getItem(i);
                        if (stack.getItem() instanceof net.minecraft.world.item.BlockItem blockItem &&
                                blockItem.getBlock() == cropBlock) {
                            stack.shrink(1);
                            if (stack.isEmpty()) {
                                peasant.inventory.setItem(i, ItemStack.EMPTY);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}