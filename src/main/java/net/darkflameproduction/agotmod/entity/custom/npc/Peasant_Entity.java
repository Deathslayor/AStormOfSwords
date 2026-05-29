package net.darkflameproduction.agotmod.entity.custom.npc;

import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.behaviour.*;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.carpenter.CarpenterBarrelDropOffGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.carpenter.CarpenterGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.carpenter.CollectCarpenterMaterialsGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.charcoalburner.CharcoalBurnerBarrelDropOffGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.charcoalburner.CharcoalBurnerGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.charcoalburner.CollectCharcoalBurnerMaterialsGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer.FarmerBarrelDropOffGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer.FarmingGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.guard.GuardCombatGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.guard.GuardPatrolGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.inventory.CollectFoodGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.sleep.FindBedGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.sleep.SleepGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.miner.MinerGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.miner.MinerBarrelDropOffGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.smelter.CollectSmelterMaterialsGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.smelter.SmelterBarrelDropOffGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.smelter.SmelterGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.NameSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterInventoryTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.carpenter.CarpenterSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.charcoalburner.CharcoalBurnerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmingSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerInventoryTicketSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.guard.GuardSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.inventory.HungerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.inventory.InventorySystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.HomeSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.SleepSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.TeleportSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.smelter.SmelterSystem;
import net.darkflameproduction.agotmod.network.OpenGrocerInventoryPacket;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.darkflameproduction.agotmod.entity.custom.npc.system.lumberjack.LumberjackSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.lumberjack.LumberjackGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.trader.TraderGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderSystem;

import java.util.*;

public class Peasant_Entity extends PathfinderMob implements GeoEntity, InventoryCarrier {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Gender constants
    public static final String GENDER_MALE = "male";
    public static final String GENDER_FEMALE = "female";

    // Age constants
    public static final String AGE_ADULT = "adult";
    public static final String AGE_CHILD = "child";

    // Aging constants
    private static final int AVERAGE_AGING_TICKS = 72000; // Average time for child to become adult
    private static final int AGING_VARIANCE = 24000; // +/- variance (20% of average)

    // Entity data accessors
    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Long> LAST_SLEEP_TIME = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_IN_MAIN_HAND = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> HUNGER_LEVEL = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> NEEDS_FOOD_COLLECTION = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> JOB_TYPE = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Optional<BlockPos>> JOB_BLOCK_POS = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Long> LAST_DAY_TRACKED = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<Boolean> IS_INTERACTING = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> GENDER = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> AGE = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> AGING_TIMER = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Optional<BlockPos>> TOWN_HALL_POS = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);

    private static final EntityDataAccessor<String>  CULTURE        = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> CULTURE_BODY   = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_EYES   = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_LEGS   = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_SHIRT  = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_HAIR   = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_BOOTS  = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_TUNIC  = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_HOOD   = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_EYES_C = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_HAIR_C = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_SHIRT_C= SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_PANTS_C= SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_TUNIC_C= SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CULTURE_HOOD_C = SynchedEntityData.defineId(Peasant_Entity.class, EntityDataSerializers.INT);


    // Door goal reference for persistence
    private OpenAndCloseDoorGoal doorGoal;

    // Player interaction tracking
    private Player currentInteractingPlayer = null;
    private int interactionCooldown = 0;
    private static final Random RANDOM = new Random();
    // All NPC systems
    // All NPC systems
    private final SleepSystem sleepSystem;
    private final HungerSystem hungerSystem;
    private final InventorySystem inventorySystem;
    private final HomeSystem homeSystem;
    private final JobSystem jobSystem;
    private final FarmingSystem farmingSystem;
    private final GrocerSystem grocerSystem;
    private final TeleportSystem teleportSystem;
    private final NameSystem nameSystem;
    private final GuardSystem guardSystem;
    private final MinerSystem minerSystem;
    private final SmelterSystem smelterSystem;
    private final net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder.AnimalHerderSystem animalHerderSystem;
    private final net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherSystem butcherSystem;
    private final net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerSystem tannerSystem;
    private final net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorSystem tailorSystem;
    private final LumberjackSystem lumberjackSystem;
    private final CharcoalBurnerSystem charcoalBurnerSystem;
    private final CarpenterSystem carpenterSystem;
    private final TraderSystem traderSystem;


    private final CultureSystem cultureSystem = new CultureSystem();
    public CultureSystem getCultureSystem() { return cultureSystem; }

    // Daily reset tracking
    private long lastDayTracked = -1;

    // Animation tracking
    private int interactAnimationTimer = 0;
    private static final int INTERACT_ANIMATION_DURATION = 8;

    // Constants
    public static final int PEASANT_SLOT_OFFSET = 400;
    private static final int PEASANT_INVENTORY_SIZE = 54;
    private final SimpleContainer inventory = new SimpleContainer(PEASANT_INVENTORY_SIZE);

    // Coin balance constant for compatibility
    private static final String COIN_BALANCE_KEY = "agotmod.coin_balance";

    public Peasant_Entity(EntityType<? extends Peasant_Entity> entityType, Level level) {
        super(entityType, level);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
        this.sleepSystem          = new SleepSystem(this);
        this.hungerSystem         = new HungerSystem(this);
        this.inventorySystem      = new InventorySystem(this, inventory);
        this.homeSystem           = new HomeSystem(this);
        this.jobSystem            = new JobSystem(this);
        this.farmingSystem        = new FarmingSystem(this);
        this.grocerSystem         = new GrocerSystem(this);
        this.butcherSystem        = new net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherSystem(this);
        this.tannerSystem         = new net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerSystem();
        this.tailorSystem         = new net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorSystem();
        this.teleportSystem       = new TeleportSystem(this);
        this.nameSystem           = new NameSystem(this);
        this.guardSystem          = new GuardSystem(this);
        this.minerSystem          = new MinerSystem(this);
        this.smelterSystem        = new SmelterSystem();
        this.animalHerderSystem   = new net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder.AnimalHerderSystem();
        this.lumberjackSystem     = new LumberjackSystem(this);
        this.charcoalBurnerSystem = new CharcoalBurnerSystem();
        this.carpenterSystem      = new CarpenterSystem();
        this.traderSystem         = new TraderSystem();


    }

    // Getters for all systems
    public SleepSystem getSleepSystem() { return sleepSystem; }
    public HungerSystem getHungerSystem() { return hungerSystem; }
    public InventorySystem getInventorySystem() { return inventorySystem; }
    public HomeSystem getHomeSystem() { return homeSystem; }
    public JobSystem getJobSystem() { return jobSystem; }
    public FarmingSystem getFarmingSystem() { return farmingSystem; }
    public GrocerSystem getGrocerSystem() { return grocerSystem; }
    public TeleportSystem getTeleportSystem() { return teleportSystem; }
    public NameSystem getNameSystem() { return nameSystem; }
    public GuardSystem getGuardSystem() { return guardSystem; }
    public MinerSystem getMinerSystem() { return minerSystem; }
    public SmelterSystem getSmelterSystem()           { return smelterSystem; }
    public net.darkflameproduction.agotmod.entity.custom.npc.system.animalherder.AnimalHerderSystem getAnimalHerderSystem() { return animalHerderSystem; }
    public net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherSystem getButcherSystem() { return butcherSystem; }
    public net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerSystem getTannerSystem()   { return tannerSystem; }
    public net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorSystem getTailorSystem()   { return tailorSystem; }
    public LumberjackSystem getLumberjackSystem() { return lumberjackSystem; }
    public CharcoalBurnerSystem getCharcoalBurnerSystem() { return charcoalBurnerSystem; }
    public CarpenterSystem getCarpenterSystem() { return carpenterSystem; }
    public TraderSystem getTraderSystem() { return traderSystem; }



    // Gender methods
    public String getGender() {
        return this.entityData.get(GENDER);
    }

    public void setGender(String gender) {
        this.entityData.set(GENDER, gender);
    }

    public boolean isMale() {
        return GENDER_MALE.equals(getGender());
    }

    public boolean isFemale() {
        return GENDER_FEMALE.equals(getGender());
    }

    // Age methods
    public String getAge() {
        return this.entityData.get(AGE);
    }

    public void setAge(String age) {
        this.entityData.set(AGE, age);
    }

    public boolean isAdult() {
        return AGE_ADULT.equals(getAge());
    }

    public boolean isChild() {
        return AGE_CHILD.equals(getAge());
    }

    // Aging methods
    public int getAgingTimer() {
        return this.entityData.get(AGING_TIMER);
    }

    public void setAgingTimer(int timer) {
        this.entityData.set(AGING_TIMER, timer);
    }

    public String  getSyncedCulture()     { return this.entityData.get(CULTURE); }
    public int     getSyncedBody()        { return this.entityData.get(CULTURE_BODY); }
    public int     getSyncedEyes()        { return this.entityData.get(CULTURE_EYES); }
    public int     getSyncedLegs()        { return this.entityData.get(CULTURE_LEGS); }
    public int     getSyncedShirt()       { return this.entityData.get(CULTURE_SHIRT); }
    public int     getSyncedHair()        { return this.entityData.get(CULTURE_HAIR); }
    public int     getSyncedBoots()       { return this.entityData.get(CULTURE_BOOTS); }
    public int     getSyncedTunic()       { return this.entityData.get(CULTURE_TUNIC); }
    public int     getSyncedHood()        { return this.entityData.get(CULTURE_HOOD); }
    public int     getSyncedEyesColor()   { return this.entityData.get(CULTURE_EYES_C); }
    public int     getSyncedHairColor()   { return this.entityData.get(CULTURE_HAIR_C); }
    public int     getSyncedShirtColor()  { return this.entityData.get(CULTURE_SHIRT_C); }
    public int     getSyncedPantsColor()  { return this.entityData.get(CULTURE_PANTS_C); }
    public int     getSyncedTunicColor()  { return this.entityData.get(CULTURE_TUNIC_C); }
    public int     getSyncedHoodColor()   { return this.entityData.get(CULTURE_HOOD_C); }

    /**
     * Ages the child to an adult, preserving all data except model/textures
     */
    public void ageToAdult() {
        if (!isChild()) return;

        // Change age to adult
        setAge(AGE_ADULT);

        // Reset aging timer
        setAgingTimer(0);

        // Re-register goals to include adult-only goals
        this.goalSelector.removeAllGoals(goal -> true);
        this.targetSelector.removeAllGoals(goal -> true);
        this.registerGoals();

        // Note: Names, memories, inventory, gender, and all other data are preserved automatically
        // Only the model and textures will change through the renderer/model system
    }

    private float getBuyMultiplier() {
        return getJobType().equals(JobSystem.JOB_TRADER) ? 1.25f : 1.0f;
    }

    private float getSellMultiplier() {
        return getJobType().equals(JobSystem.JOB_TRADER) ? 0.375f : 0.5f;
    }



    // ===== TOWN HALL REGISTRATION METHODS =====
    public void registerWithTownHall(BlockPos townHallPos) {
        this.entityData.set(TOWN_HALL_POS, Optional.of(townHallPos));
    }

    public Optional<BlockPos> getTownHallPos() {
        return this.entityData.get(TOWN_HALL_POS);
    }

    public boolean isRegisteredToTownHall() {
        return getTownHallPos().isPresent();
    }

    public boolean isRegisteredToTownHall(BlockPos townHallPos) {
        Optional<BlockPos> myTownHall = getTownHallPos();
        return myTownHall.isPresent() && myTownHall.get().equals(townHallPos);
    }

    public void unregisterFromTownHall() {
        this.entityData.set(TOWN_HALL_POS, Optional.empty());
    }



    // Player interaction methods
    public void setInteractingPlayer(Player player) {
        this.currentInteractingPlayer = player;
        this.interactionCooldown = 20; // 1 second buffer
    }

    public void clearInteractingPlayer() {
        this.currentInteractingPlayer = null;
        this.interactionCooldown = 20; // 1 second buffer before resuming normal AI
    }

    public boolean isInteractingWithPlayer() {
        return this.currentInteractingPlayer != null && this.currentInteractingPlayer.isAlive()
                && this.distanceToSqr(this.currentInteractingPlayer) <= 64.0D; // 8 block range
    }

    public Player getCurrentInteractingPlayer() {
        return this.currentInteractingPlayer;
    }

    /**
     * Triggers the interact animation - stops movement and plays animation
     */
    public void triggerInteractAnimation() {
        if (!this.level().isClientSide) {
            // Stop current navigation
            this.getNavigation().stop();
            // Set animation timer
            interactAnimationTimer = INTERACT_ANIMATION_DURATION;
            // Set the synced data flag
            this.getEntityData().set(IS_INTERACTING, true);
        }
    }

    /**
     * Returns true if currently playing interact animation
     */
    public boolean isPlayingInteractAnimation() {
        return interactAnimationTimer > 0 || this.getEntityData().get(IS_INTERACTING);
    }

    // Combat methods
    public void setIsAttacking(boolean attacking) {
        this.entityData.set(IS_ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        if (isAdult()) {
            this.goalSelector.addGoal(1, new GuardCombatGoal(this));
            this.goalSelector.addGoal(2, new PeasantDefenseGoal(this));
        }

        this.doorGoal = new OpenAndCloseDoorGoal(this);
        this.goalSelector.addGoal(3, doorGoal);

        if (isAdult()) {
            this.goalSelector.addGoal(4, new FarmerBarrelDropOffGoal(this));
            this.goalSelector.addGoal(4, new MinerBarrelDropOffGoal(this));
            this.goalSelector.addGoal(4, new SmelterBarrelDropOffGoal(this));
            this.goalSelector.addGoal(4, new net.darkflameproduction.agotmod.entity.custom.npc.goals.animalherder.AnimalHerderDepositGoal(this));
            this.goalSelector.addGoal(4, new net.darkflameproduction.agotmod.entity.custom.npc.goals.tanner.TannerBarrelDropOffGoal(this));
            this.goalSelector.addGoal(4, new net.darkflameproduction.agotmod.entity.custom.npc.goals.tailor.TailorBarrelDropOffGoal(this));
            this.goalSelector.addGoal(4, new net.darkflameproduction.agotmod.entity.custom.npc.goals.lumberjack.LumberjackDepositGoal(this));
            this.goalSelector.addGoal(4, new CarpenterBarrelDropOffGoal(this));
        }

        this.goalSelector.addGoal(5, new FindBedGoal(this));
        this.goalSelector.addGoal(6, new SleepGoal(this));
        this.goalSelector.addGoal(6, new LumberjackGoal(this));
        this.goalSelector.addGoal(7, new CollectFoodGoal(this));

        if (isAdult()) {
            this.goalSelector.addGoal(7, new CollectSmelterMaterialsGoal(this));
            this.goalSelector.addGoal(7, new net.darkflameproduction.agotmod.entity.custom.npc.goals.animalherder.CollectBreedingItemGoal(this));
            this.goalSelector.addGoal(7, new net.darkflameproduction.agotmod.entity.custom.npc.goals.tanner.CollectTannerMaterialsGoal(this));
            this.goalSelector.addGoal(7, new net.darkflameproduction.agotmod.entity.custom.npc.goals.tailor.CollectTailorMaterialsGoal(this));
            this.goalSelector.addGoal(7, new CollectCharcoalBurnerMaterialsGoal(this));
            this.goalSelector.addGoal(7, new CollectCarpenterMaterialsGoal(this));
            this.goalSelector.addGoal(8, new CharcoalBurnerGoal(this));
            this.goalSelector.addGoal(8, new SmelterGoal(this));
            this.goalSelector.addGoal(8, new MinerGoal(this));
            this.goalSelector.addGoal(8, new net.darkflameproduction.agotmod.entity.custom.npc.goals.animalherder.AnimalHerderGoal(this));
            this.goalSelector.addGoal(8, new net.darkflameproduction.agotmod.entity.custom.npc.goals.tanner.TannerGoal(this));
            this.goalSelector.addGoal(8, new net.darkflameproduction.agotmod.entity.custom.npc.goals.tailor.TailorGoal(this));
            this.goalSelector.addGoal(8, new TraderGoal(this));
            this.goalSelector.addGoal(8, new CarpenterGoal(this));
            this.goalSelector.addGoal(9, new ReturnToJobBlockGoal(this));
            this.goalSelector.addGoal(11, new FarmingGoal(this));
            this.goalSelector.addGoal(13, new GuardPatrolGoal(this));
        }

        this.goalSelector.addGoal(14, new RestrictedWanderGoal(this, 0.6D));
        this.goalSelector.addGoal(15, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(16, new RandomLookAroundGoal(this));

        if (isAdult()) {
            this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        }
    }

    public void setIsInteracting(boolean interacting) {
        this.getEntityData().set(IS_INTERACTING, interacting);
    }


    public void findAndRegisterWithNearestTownHall() {
        if (!isRegisteredToTownHall() && !level().isClientSide()) {
            // Search for town halls within a reasonable distance (128 blocks)
            BlockPos myPos = this.blockPosition();
            int searchRadius = 128;

            BlockPos nearestTownHall = null;
            double nearestDistance = Double.MAX_VALUE;

            // Search in chunks to be more efficient
            for (int x = -searchRadius; x <= searchRadius; x += 16) {
                for (int y = -64; y <= 64; y += 16) {
                    for (int z = -searchRadius; z <= searchRadius; z += 16) {
                        BlockPos checkPos = myPos.offset(x, y, z);

                        if (level().isLoaded(checkPos)) {
                            BlockEntity blockEntity = level().getBlockEntity(checkPos);
                            if (blockEntity instanceof TownHallBlockEntity townHall) {
                                // Check if this NPC is within the town hall's scan radius
                                if (townHall.isWithinStableScanArea(myPos, townHall.getCurrentScanRadius())) {
                                    double distance = myPos.distSqr(checkPos);
                                    if (distance < nearestDistance) {
                                        nearestDistance = distance;
                                        nearestTownHall = checkPos;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (nearestTownHall != null) {
                registerWithTownHall(nearestTownHall);
            }
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.STEP_HEIGHT, 0.6); // Default, overridden per job
    }

    @Override
    public float getPathfindingMalus(PathType pathType) {
        String job = getJobType();
        boolean isHerder = job.equals(JobSystem.JOB_CATTLE_HERDER)
                || job.equals(JobSystem.JOB_CHICKEN_BREEDER)
                || job.equals(JobSystem.JOB_PIG_BREEDER)
                || job.equals(JobSystem.JOB_SHEEP_HERDER);

        if (isHerder && pathType == PathType.FENCE) {
            return 0.0F; // Treat fences as walkable
        }
        return super.getPathfindingMalus(pathType);
    }

    @Override
    public float maxUpStep() {
        String job = getJobType();
        if (job.equals(JobSystem.JOB_CATTLE_HERDER)
                || job.equals(JobSystem.JOB_CHICKEN_BREEDER)
                || job.equals(JobSystem.JOB_PIG_BREEDER)
                || job.equals(JobSystem.JOB_SHEEP_HERDER)) {
            return 1.5F;
        }
        return super.maxUpStep();
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        // Children cannot attack anyone
        if (isChild()) {
            return false;
        }

        // Guards can attack monsters freely
        if (getJobType().equals(JobSystem.JOB_GUARD) && target instanceof net.minecraft.world.entity.monster.Monster) {
            return true;
        }

        // Don't attack players unless they attacked us first
        if (target instanceof Player) {
            return this.getLastHurtByMob() == target;
        }
        return super.canAttack(target);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_SLEEPING, false);
        builder.define(LAST_SLEEP_TIME, 0L);
        builder.define(DATA_ITEM_IN_MAIN_HAND, ItemStack.EMPTY);
        builder.define(HUNGER_LEVEL, HungerSystem.MAX_HUNGER);
        builder.define(NEEDS_FOOD_COLLECTION, false);
        builder.define(JOB_TYPE, JobSystem.JOB_NONE);
        builder.define(JOB_BLOCK_POS, Optional.empty());
        builder.define(LAST_DAY_TRACKED, -1L);
        builder.define(IS_INTERACTING, false);
        builder.define(IS_ATTACKING, false);
        builder.define(GENDER, GENDER_MALE); // Default to male
        builder.define(AGE, AGE_ADULT); // Default to adult
        builder.define(AGING_TIMER, 0); // Default aging timer
        builder.define(TOWN_HALL_POS, Optional.empty()); // NEW: Town hall registration
        builder.define(CULTURE,         "NONE");
        builder.define(CULTURE_BODY,    0);
        builder.define(CULTURE_EYES,    0);
        builder.define(CULTURE_LEGS,    0);
        builder.define(CULTURE_SHIRT,   0);
        builder.define(CULTURE_HAIR,    0);
        builder.define(CULTURE_BOOTS,   0);
        builder.define(CULTURE_TUNIC,   0);
        builder.define(CULTURE_HOOD,    0);
        builder.define(CULTURE_EYES_C,  0);
        builder.define(CULTURE_HAIR_C,  0);
        builder.define(CULTURE_SHIRT_C, 0);
        builder.define(CULTURE_PANTS_C, 0);
        builder.define(CULTURE_TUNIC_C, 0);
        builder.define(CULTURE_HOOD_C,  0);
    }

    public void syncCultureToClients() {
        net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureSystem cs = cultureSystem;
        this.entityData.set(CULTURE,         cs.getCulture().name());
        this.entityData.set(CULTURE_BODY,    cs.getBodyVariant());
        this.entityData.set(CULTURE_EYES,    cs.getEyesVariant());
        this.entityData.set(CULTURE_LEGS,    cs.getLegsVariant());
        this.entityData.set(CULTURE_SHIRT,   cs.getShirtVariant());
        this.entityData.set(CULTURE_HAIR,    cs.getHairVariant());
        this.entityData.set(CULTURE_BOOTS,   cs.getBootsVariant());
        this.entityData.set(CULTURE_TUNIC,   cs.getTunicVariant());
        this.entityData.set(CULTURE_HOOD,    cs.getHoodVariant());
        this.entityData.set(CULTURE_EYES_C,  cs.getEyesColorIdx());
        this.entityData.set(CULTURE_HAIR_C,  cs.getHairColorIdx());
        this.entityData.set(CULTURE_SHIRT_C, cs.getShirtColorIdx());
        this.entityData.set(CULTURE_PANTS_C, cs.getPantsColorIdx());
        this.entityData.set(CULTURE_TUNIC_C, cs.getTunicColorIdx());
        this.entityData.set(CULTURE_HOOD_C,  cs.getHoodColorIdx());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putLong("LastDayTracked", lastDayTracked);
        compound.putInt("InteractAnimationTimer", interactAnimationTimer);
        compound.putBoolean("IsAttacking", this.isAttacking());
        compound.putString("Gender", this.getGender());
        compound.putString("Age", this.getAge());
        compound.putInt("AgingTimer", this.getAgingTimer());

        Optional<BlockPos> townHallPos = getTownHallPos();
        if (townHallPos.isPresent()) {
            compound.putLong("TownHallPos", townHallPos.get().asLong());
        }

        sleepSystem.saveData(compound);
        hungerSystem.saveData(compound);
        homeSystem.saveData(compound);
        jobSystem.saveData(compound);
        farmingSystem.saveData(compound);
        grocerSystem.saveData(compound);
        butcherSystem.saveData(compound);
        tannerSystem.saveData(compound);
        tailorSystem.saveData(compound);
        teleportSystem.saveData(compound);
        nameSystem.saveData(compound, this.registryAccess());
        guardSystem.saveData(compound);
        minerSystem.saveData(compound);
        smelterSystem.saveData(compound);
        animalHerderSystem.saveData(compound);
        inventorySystem.saveData(compound, this.registryAccess());

        CompoundTag lumberjackTag = new CompoundTag();
        lumberjackSystem.save(lumberjackTag);
        compound.put("LumberjackSystem", lumberjackTag);

        charcoalBurnerSystem.saveData(compound);
        carpenterSystem.saveData(compound);
        traderSystem.saveData(compound);
        cultureSystem.saveData(compound);





        if (doorGoal != null) {
            doorGoal.saveOpenedBlocks(compound);
        }
    }



    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        lastDayTracked         = compound.getLong("LastDayTracked");
        interactAnimationTimer = compound.getInt("InteractAnimationTimer");

        if (compound.contains("IsAttacking"))  this.setIsAttacking(compound.getBoolean("IsAttacking"));
        if (compound.contains("Gender"))       this.setGender(compound.getString("Gender"));
        if (compound.contains("Age"))          this.setAge(compound.getString("Age"));
        if (compound.contains("AgingTimer"))   this.setAgingTimer(compound.getInt("AgingTimer"));

        if (compound.contains("TownHallPos")) {
            registerWithTownHall(BlockPos.of(compound.getLong("TownHallPos")));
        }

        sleepSystem.loadData(compound);
        hungerSystem.loadData(compound);
        homeSystem.loadData(compound);
        jobSystem.loadData(compound);
        farmingSystem.loadData(compound);
        grocerSystem.loadData(compound);
        butcherSystem.loadData(compound);
        tannerSystem.loadData(compound);
        tailorSystem.loadData(compound);
        teleportSystem.loadData(compound);
        nameSystem.loadData(compound, this.registryAccess());
        guardSystem.loadData(compound);
        minerSystem.loadData(compound);
        smelterSystem.loadData(compound);
        animalHerderSystem.loadData(compound);
        inventorySystem.loadData(compound, this.registryAccess());
        charcoalBurnerSystem.loadData(compound);
        carpenterSystem.loadData(compound);
        traderSystem.loadData(compound);
        cultureSystem.loadData(compound);



        if (compound.contains("LumberjackSystem")) {
            lumberjackSystem.load(compound.getCompound("LumberjackSystem"));
        }

        if (doorGoal != null) {
            doorGoal.loadOpenedBlocks(compound);
        }

        this.getEntityData().set(DATA_ITEM_IN_MAIN_HAND, this.getMainHandItem());
    }


    @Override
    public void tick() {
        super.tick();

        if (interactionCooldown > 0) interactionCooldown--;

        if (interactAnimationTimer > 0) {
            interactAnimationTimer--;
            if (interactAnimationTimer <= 0 && !this.level().isClientSide) {
                this.getEntityData().set(IS_INTERACTING, false);
            }
        }

        if (currentInteractingPlayer != null) {
            if (!currentInteractingPlayer.isAlive() || this.distanceToSqr(currentInteractingPlayer) > 64.0D) {
                clearInteractingPlayer();
            }
        }

        if (!this.level().isClientSide) {
            checkForNewDay();

            if (!isRegisteredToTownHall() && this.tickCount % 200 == 0) {
                findAndRegisterWithNearestTownHall();
            }

            if (isRegisteredToTownHall() && this.tickCount % 400 == 0) {
                validateTownHallRegistration();
            }

            if (!cultureSystem.hasCulture()
                    && net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureTicketSystem
                    .hasPendingTicket(this.getUUID())) {
                net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureTicketSystem.CultureTicket ticket =
                        net.darkflameproduction.agotmod.entity.custom.npc.system.culture.CultureTicketSystem
                                .consumeTicket(this.getUUID());
                if (ticket != null) {
                    cultureSystem.assignCulture(
                            ticket.culture,
                            this.level().getRandom(),
                            isFemale(),
                            isChild()
                    );
                    nameSystem.generateRandomName(this.level().getRandom());
                }
            }
        }

        sleepSystem.tick();
        hungerSystem.tick();
        inventorySystem.tick();
        homeSystem.tick();
        jobSystem.tick();
        farmingSystem.tick();
        grocerSystem.tick();
        butcherSystem.tick();
        teleportSystem.tick();
        guardSystem.tick();
        minerSystem.tick();

        if (!this.level().isClientSide && getJobType().equals(JobSystem.JOB_GROCER)) {
            if (GrocerInventoryTicketSystem.hasPendingResponse(this.getUUID())) {
                GrocerInventoryTicketSystem.InventoryResponseTicket response =
                        GrocerInventoryTicketSystem.consumeResponse(this.getUUID());
                if (response != null && this.level() instanceof ServerLevel serverLevel) {
                    this.registerWithTownHall(response.townHallPos);
                    ServerPlayer serverPlayer = serverLevel.getServer().getPlayerList().getPlayer(response.playerUUID);
                    if (serverPlayer != null) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = buildFilteredEntries(
                                response.items, net.darkflameproduction.agotmod.util.ItemPricing::isGrocerItem);
                        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries =
                                buildPlayerEntries(serverPlayer, net.darkflameproduction.agotmod.util.ItemPricing::isGrocerItem);
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new OpenGrocerInventoryPacket(this.getDisplayName().getString(),
                                        entries, playerEntries, response.townBalance, playerBalance,
                                        getBuyMultiplier(), getSellMultiplier()));
                    }
                }
            }
        }

        if (!this.level().isClientSide && getJobType().equals(JobSystem.JOB_BUTCHER)) {
            if (net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherInventoryTicketSystem.hasPendingResponse(this.getUUID())) {
                net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherInventoryTicketSystem.InventoryResponseTicket response =
                        net.darkflameproduction.agotmod.entity.custom.npc.system.butcher.ButcherInventoryTicketSystem.consumeResponse(this.getUUID());
                if (response != null && this.level() instanceof ServerLevel serverLevel) {
                    this.registerWithTownHall(response.townHallPos);
                    ServerPlayer serverPlayer = serverLevel.getServer().getPlayerList().getPlayer(response.playerUUID);
                    if (serverPlayer != null) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = buildFilteredEntries(
                                response.items, net.darkflameproduction.agotmod.util.ItemPricing::isButcherItem);
                        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries =
                                buildPlayerEntries(serverPlayer, net.darkflameproduction.agotmod.util.ItemPricing::isButcherItem);
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new OpenGrocerInventoryPacket(this.getDisplayName().getString(),
                                        entries, playerEntries, response.townBalance, playerBalance,
                                        getBuyMultiplier(), getSellMultiplier()));
                    }
                }
            }
        }

        if (!this.level().isClientSide && getJobType().equals(JobSystem.JOB_TANNER)) {
            if (net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerInventoryTicketSystem.hasPendingResponse(this.getUUID())) {
                net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerInventoryTicketSystem.InventoryResponseTicket response =
                        net.darkflameproduction.agotmod.entity.custom.npc.system.tanner.TannerInventoryTicketSystem.consumeResponse(this.getUUID());
                if (response != null && this.level() instanceof ServerLevel serverLevel) {
                    this.registerWithTownHall(response.townHallPos);
                    ServerPlayer serverPlayer = serverLevel.getServer().getPlayerList().getPlayer(response.playerUUID);
                    if (serverPlayer != null) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = buildFilteredEntries(
                                response.items, net.darkflameproduction.agotmod.util.ItemPricing::isTannerItem);
                        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries =
                                buildPlayerEntries(serverPlayer, net.darkflameproduction.agotmod.util.ItemPricing::isTannerItem);
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new OpenGrocerInventoryPacket(this.getDisplayName().getString(),
                                        entries, playerEntries, response.townBalance, playerBalance,
                                        getBuyMultiplier(), getSellMultiplier()));
                    }
                }
            }
        }

        if (!this.level().isClientSide && getJobType().equals(JobSystem.JOB_TAILOR)) {
            if (net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorInventoryTicketSystem.hasPendingResponse(this.getUUID())) {
                net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorInventoryTicketSystem.InventoryResponseTicket response =
                        net.darkflameproduction.agotmod.entity.custom.npc.system.tailor.TailorInventoryTicketSystem.consumeResponse(this.getUUID());
                if (response != null && this.level() instanceof ServerLevel serverLevel) {
                    this.registerWithTownHall(response.townHallPos);
                    ServerPlayer serverPlayer = serverLevel.getServer().getPlayerList().getPlayer(response.playerUUID);
                    if (serverPlayer != null) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = buildFilteredEntries(
                                response.items, net.darkflameproduction.agotmod.util.ItemPricing::isTailorItem);
                        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries =
                                buildPlayerEntries(serverPlayer, net.darkflameproduction.agotmod.util.ItemPricing::isTailorItem);
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new OpenGrocerInventoryPacket(this.getDisplayName().getString(),
                                        entries, playerEntries, response.townBalance, playerBalance,
                                        getBuyMultiplier(), getSellMultiplier()));
                    }
                }
            }
        }

        if (!this.level().isClientSide && getJobType().equals(JobSystem.JOB_BLACKSMITH)) {
            if (net.darkflameproduction.agotmod.entity.custom.npc.system.blacksmith.BlacksmithInventoryTicketSystem.hasPendingResponse(this.getUUID())) {
                net.darkflameproduction.agotmod.entity.custom.npc.system.blacksmith.BlacksmithInventoryTicketSystem.InventoryResponseTicket response =
                        net.darkflameproduction.agotmod.entity.custom.npc.system.blacksmith.BlacksmithInventoryTicketSystem.consumeResponse(this.getUUID());
                if (response != null && this.level() instanceof ServerLevel serverLevel) {
                    this.registerWithTownHall(response.townHallPos);
                    ServerPlayer serverPlayer = serverLevel.getServer().getPlayerList().getPlayer(response.playerUUID);
                    if (serverPlayer != null) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = buildFilteredEntries(
                                response.items, net.darkflameproduction.agotmod.util.ItemPricing::isBlacksmithItem);
                        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries =
                                buildPlayerEntries(serverPlayer, net.darkflameproduction.agotmod.util.ItemPricing::isBlacksmithItem);
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new OpenGrocerInventoryPacket(this.getDisplayName().getString(),
                                        entries, playerEntries, response.townBalance, playerBalance,
                                        getBuyMultiplier(), getSellMultiplier()));
                    }
                }
            }
        }

        if (!this.level().isClientSide && getJobType().equals(JobSystem.JOB_CARPENTER)) {
            if (CarpenterInventoryTicketSystem.hasPendingResponse(this.getUUID())) {
                CarpenterInventoryTicketSystem.InventoryResponseTicket response =
                        CarpenterInventoryTicketSystem.consumeResponse(this.getUUID());
                if (response != null && this.level() instanceof ServerLevel serverLevel) {
                    this.registerWithTownHall(response.townHallPos);
                    ServerPlayer serverPlayer = serverLevel.getServer().getPlayerList().getPlayer(response.playerUUID);
                    if (serverPlayer != null) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = buildFilteredEntries(
                                response.items, net.darkflameproduction.agotmod.util.ItemPricing::isCarpenterItem);
                        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries =
                                buildPlayerEntries(serverPlayer, net.darkflameproduction.agotmod.util.ItemPricing::isCarpenterItem);
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new OpenGrocerInventoryPacket(this.getDisplayName().getString(),
                                        entries, playerEntries, response.townBalance, playerBalance,
                                        getBuyMultiplier(), getSellMultiplier()));
                    }
                }
            }
        }

        if (!this.level().isClientSide && getJobType().equals(JobSystem.JOB_TRADER)) {
            if (net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.hasPendingResponse(this.getUUID())) {
                net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.InventoryResponseTicket response =
                        net.darkflameproduction.agotmod.entity.custom.npc.system.trader.TraderInventoryTicketSystem.consumeResponse(this.getUUID());
                if (response != null && this.level() instanceof ServerLevel serverLevel) {
                    this.registerWithTownHall(response.townHallPos);
                    ServerPlayer serverPlayer = serverLevel.getServer().getPlayerList().getPlayer(response.playerUUID);
                    if (serverPlayer != null) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = buildFilteredEntries(
                                response.items, net.darkflameproduction.agotmod.util.ItemPricing::isTraderItem);
                        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries =
                                buildPlayerEntries(serverPlayer, net.darkflameproduction.agotmod.util.ItemPricing::isTraderItem);
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);
                        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                                new OpenGrocerInventoryPacket(this.getDisplayName().getString(),
                                        entries, playerEntries, response.townBalance, playerBalance,
                                        getBuyMultiplier(), getSellMultiplier()));
                    }
                }
            }
        }

        if (!this.level().isClientSide && isChild()) {
            int currentTimer = getAgingTimer();
            currentTimer++;
            setAgingTimer(currentTimer);
            if (currentTimer >= getAgingTarget()) {
                ageToAdult();
            }
        }
    }

    private List<GrocerSystem.GrocerInventoryEntry> buildFilteredEntries(
            Map<String, Long> items, java.util.function.Predicate<String> filter) {
        List<GrocerSystem.GrocerInventoryEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Long> entry : items.entrySet()) {
            String itemKey = entry.getKey();
            long amount    = entry.getValue();
            if (amount <= 0 || !filter.test(itemKey)) continue;
            net.minecraft.resources.ResourceLocation loc =
                    net.minecraft.resources.ResourceLocation.tryParse(itemKey);
            if (loc == null) continue;
            net.minecraft.world.item.Item item =
                    net.minecraft.core.registries.BuiltInRegistries.ITEM.get(loc);
            if (item == null || item == net.minecraft.world.item.Items.AIR) continue;
            String displayName = new net.minecraft.world.item.ItemStack(item).getHoverName().getString();
            entries.add(new GrocerSystem.GrocerInventoryEntry(displayName, amount, itemKey));
        }
        entries.sort(Comparator.comparing(e -> e.displayName));
        return entries;
    }

    private List<OpenGrocerInventoryPacket.PlayerInventoryEntry> buildPlayerEntries(
            net.minecraft.server.level.ServerPlayer serverPlayer,
            java.util.function.Predicate<String> filter) {
        List<OpenGrocerInventoryPacket.PlayerInventoryEntry> playerEntries = new ArrayList<>();
        net.minecraft.world.Container playerInventory = serverPlayer.getInventory();
        for (int slot = 0; slot < playerInventory.getContainerSize(); slot++) {
            net.minecraft.world.item.ItemStack stack = playerInventory.getItem(slot);
            if (stack.isEmpty()) continue;
            net.minecraft.resources.ResourceLocation loc =
                    net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
            if (loc == null) continue;
            String itemKey = loc.toString();
            if (!filter.test(itemKey)) continue;
            String displayName = stack.getHoverName().getString();
            int amount         = stack.getCount();
            boolean found      = false;
            for (int i = 0; i < playerEntries.size(); i++) {
                OpenGrocerInventoryPacket.PlayerInventoryEntry existing = playerEntries.get(i);
                if (existing.itemKey.equals(itemKey)) {
                    playerEntries.set(i, new OpenGrocerInventoryPacket.PlayerInventoryEntry(
                            existing.displayName, existing.amount + amount, existing.itemKey, existing.slot));
                    found = true;
                    break;
                }
            }
            if (!found) {
                playerEntries.add(new OpenGrocerInventoryPacket.PlayerInventoryEntry(
                        displayName, amount, itemKey, slot));
            }
        }
        return playerEntries;
    }

    private void validateTownHallRegistration() {
        Optional<BlockPos> townHallPos = getTownHallPos();
        if (townHallPos.isPresent()) {
            BlockEntity blockEntity = level().getBlockEntity(townHallPos.get());
            if (!(blockEntity instanceof TownHallBlockEntity townHall)) {
                // Town hall no longer exists, unregister
                unregisterFromTownHall();
            } else {
                // Check if we're still within the town hall's radius
                if (!townHall.isWithinStableScanArea(this.blockPosition(), townHall.getCurrentScanRadius())) {
                    // Outside town hall's radius, unregister and look for a new one
                    unregisterFromTownHall();
                    findAndRegisterWithNearestTownHall();
                }
            }
        }
    }

    private int getAgingTarget() {
        return this.getPersistentData().getInt("AgingTarget");
    }

    private void checkForNewDay() {
        long currentDay = this.level().getDayTime() / 24000;

        if (currentDay > lastDayTracked) {

            // Update synched data
            this.getEntityData().set(LAST_DAY_TRACKED, currentDay);
            lastDayTracked = currentDay;
        }
    }

    @Override
    public void aiStep() {
        if (isInteractingWithPlayer() || interactionCooldown > 0 || isPlayingInteractAnimation()) {
            // Stop all movement but still allow look control
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            this.getNavigation().stop();

            // Force look at interacting player
            if (currentInteractingPlayer != null) {
                double dx = currentInteractingPlayer.getX() - this.getX();
                double dz = currentInteractingPlayer.getZ() - this.getZ();
                this.setYRot((float)(Math.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F);
                this.yRotO = this.getYRot();
                this.getLookControl().setLookAt(currentInteractingPlayer, 30.0F, 30.0F);
                this.getLookControl().tick();
            }
            return;
        }

        if (!sleepSystem.isSleeping()) {
            super.aiStep();
        } else {
            this.setDeltaMovement(0, 0, 0);
            this.setYRot(this.yRotO);
            this.setXRot(this.xRotO);
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (hand == InteractionHand.MAIN_HAND) {
            if (sleepSystem.isSleeping()) return InteractionResult.PASS;
            if (isChild()) return InteractionResult.PASS;

            setInteractingPlayer(player);

            double dx = player.getX() - this.getX();
            double dz = player.getZ() - this.getZ();
            this.setYRot((float)(Math.atan2(dz, dx) * (180.0 / Math.PI)) - 90.0F);
            this.yRotO = this.getYRot();
            this.getLookControl().setLookAt(player, 30.0F, 30.0F);

            if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
                net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(serverPlayer,
                        new net.darkflameproduction.agotmod.network.OpenNpcConversationPacket(
                                this.getUUID(), getJobType(), this.getDisplayName().getString()));
            }
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source == this.damageSources().sweetBerryBush()) {
            return false;
        }

        boolean result = super.hurt(source, amount);

        if (result) {
            sleepSystem.onHurt();
            hungerSystem.onHurt();
            // Clear interaction when hurt
            clearInteractingPlayer();
        }

        return result;
    }

    @Override
    public void remove(RemovalReason reason) {
        if (isRegisteredToTownHall()) {
            unregisterFromTownHall();
        }

        jobSystem.onRemove();
        sleepSystem.onRemove();
        homeSystem.onRemove();
        grocerSystem.onRemove();
        butcherSystem.onRemove();
        guardSystem.onRemove();

        super.remove(reason);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    //@Nullable
    //@Override
    //protected SoundEvent getAmbientSound() {
        // List of your throat clearing and cough sounds
    // SoundEvent[] ambientSounds = new SoundEvent[] {
    //           ModSounds.NPC_COUGH.get(),
    //           ModSounds.NPC_CLEAR_THROAT_1.get(),
    //           ModSounds.NPC_CLEAR_THROAT_2.get(),
    //           ModSounds.NPC_CLEAR_THROAT_3.get(),
    //            ModSounds.NPC_CLEAR_THROAT_4.get()
    //   };
        // Randomly pick one to play
    //   return ambientSounds[RANDOM.nextInt(ambientSounds.length)];
    //}

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.NPC_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.NPC_DEATH.get();
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(
            ServerLevelAccessor p_35439_, DifficultyInstance p_35440_, MobSpawnType p_363222_, @Nullable SpawnGroupData p_35442_
    ) {
        if (!this.level().isClientSide) {
            String gender = p_35439_.getRandom().nextBoolean() ? GENDER_FEMALE : GENDER_MALE;
            this.setGender(gender);

            String age = p_35439_.getRandom().nextFloat() < 0.8f ? AGE_ADULT : AGE_CHILD;
            this.setAge(age);

            if (age.equals(AGE_CHILD)) {
                int agingTime = AVERAGE_AGING_TICKS +
                        (p_35439_.getRandom().nextInt(AGING_VARIANCE * 2) - AGING_VARIANCE);
                this.setAgingTimer(0);
                this.getPersistentData().putInt("AgingTarget", agingTime);
            } else {
                this.setAgingTimer(0);
            }

            // Culture and name are assigned by TownCultureZone.onEntityJoinLevel
            // which fires when this entity is added to the level. generateRandomName
            // is called there too, so we don't call it here to avoid double-rolling.

            lastDayTracked = this.level().getDayTime() / 24000;
            this.getEntityData().set(LAST_DAY_TRACKED, lastDayTracked);
        }
        return super.finalizeSpawn(p_35439_, p_35440_, p_363222_, p_35442_);
    }

    // Delegate methods to systems - modified shouldSleep for guards
    public boolean shouldSleep() {
        // Guards use their own sleep system
        if (getJobType().equals(JobSystem.JOB_GUARD)) {
            return guardSystem.shouldSleep();
        }
        return sleepSystem.shouldSleep();
    }

    public boolean isSleeping() { return sleepSystem.isSleeping(); }
    public void setSleeping(boolean sleeping) { sleepSystem.setSleeping(sleeping); }
    public BlockPos getBedPos() { return sleepSystem.getBedPos(); }
    public void setBedPos(BlockPos pos) { sleepSystem.setBedPos(pos); }

    public int getHungerLevel() { return hungerSystem.getHungerLevel(); }
    public void setHungerLevel(int hunger) { hungerSystem.setHungerLevel(hunger); }
    public boolean isHungry() { return hungerSystem.isHungry(); }
    public boolean needsFoodCollection() { return hungerSystem.needsFoodCollection(); }
    public void setNeedsFoodCollection(boolean needs) { hungerSystem.setNeedsFoodCollection(needs); }

    public String getJobType() {
        // Children cannot have jobs
        if (isChild()) {
            return JobSystem.JOB_NONE;
        }
        return jobSystem.getJobType();
    }

    public void setJobType(String jobType) {
        if (isChild()) return;
        jobSystem.setJobType(jobType);

        // Update step height based on job
        boolean isHerder = jobType.equals(JobSystem.JOB_CATTLE_HERDER)
                || jobType.equals(JobSystem.JOB_CHICKEN_BREEDER)
                || jobType.equals(JobSystem.JOB_PIG_BREEDER)
                || jobType.equals(JobSystem.JOB_SHEEP_HERDER);

        if (this.getAttribute(Attributes.STEP_HEIGHT) != null) {
            this.getAttribute(Attributes.STEP_HEIGHT).setBaseValue(isHerder ? 1.5 : 0.6);
        }
    }

    public boolean hasJob() {
        // Children cannot have jobs
        if (isChild()) {
            return false;
        }
        return jobSystem.hasJob();
    }
    public BlockPos getJobBlockPos() { return jobSystem.getJobBlockPos(); }
    public void setJobBlockPos(BlockPos pos) { jobSystem.setJobBlockPos(pos); }

    public boolean isWithinHomeArea(BlockPos pos) { return homeSystem.isWithinHomeArea(pos); }
    public boolean isTooFarFromHome() { return homeSystem.isTooFarFromHome(); }
    public BlockPos getHomeCenter() { return homeSystem.getHomeCenter(); }

    // InventoryCarrier implementation
    @Override
    public SimpleContainer getInventory() { return inventorySystem.getInventory(); }

    @Override
    public SlotAccess getSlot(int slot) { return inventorySystem.getSlot(slot); }

    // Item handling delegates - use native equipment system
    @Override
    public ItemStack getMainHandItem() {
        return super.getMainHandItem(); // Use native system
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return super.getArmorSlots(); // Use native system
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) {
        return super.getItemBySlot(slot); // Use native system
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        super.setItemSlot(slot, stack); // Use native system
        // Sync main hand with entity data for animations
        if (slot == EquipmentSlot.MAINHAND) {
            this.getEntityData().set(DATA_ITEM_IN_MAIN_HAND, stack);
        }
    }

    // Sleep delegates
    @Override
    public void startSleeping(BlockPos pos) {
        // For guards, bypass vanilla time restrictions during their designated sleep time
        if (getJobType().equals(JobSystem.JOB_GUARD) && guardSystem.shouldSleep()) {
            // Manually set sleeping state without calling super (which has time restrictions)
            this.setSleepingPos(pos);
            this.setPos(pos.getX() + 0.5, pos.getY() + 0.0625, pos.getZ() + 0.5);
            this.setDeltaMovement(Vec3.ZERO);
            this.hasImpulse = true;

            // Set our custom sleep system state
            sleepSystem.setSleeping(true);
            sleepSystem.setBedPos(pos);

            // Establish home bed if this is their first sleep
            homeSystem.establishHomeBed(pos);

            // Stop eating and navigation
            hungerSystem.stopEating();
            this.getNavigation().stop();
        } else {
            // For regular NPCs, use vanilla behavior (which includes time restrictions)
            super.startSleeping(pos);
            sleepSystem.startSleeping(pos);
        }
    }

    @Override
    public void stopSleeping() {
        super.stopSleeping();
        sleepSystem.stopSleeping();
    }

    // Name system delegates
    @Override
    public boolean isCustomNameVisible() { return nameSystem.isCustomNameVisible(); }

    @Override
    public boolean shouldShowName() { return nameSystem.shouldShowName(); }

    // Entity data accessor getters (for systems)
    public EntityDataAccessor<Boolean> getIsSleepingAccessor() { return IS_SLEEPING; }
    public EntityDataAccessor<Long> getLastSleepTimeAccessor() { return LAST_SLEEP_TIME; }
    public EntityDataAccessor<ItemStack> getMainHandItemAccessor() { return DATA_ITEM_IN_MAIN_HAND; }
    public EntityDataAccessor<Integer> getHungerLevelAccessor() { return HUNGER_LEVEL; }
    public EntityDataAccessor<Boolean> getNeedsFoodCollectionAccessor() { return NEEDS_FOOD_COLLECTION; }
    public EntityDataAccessor<String> getJobTypeAccessor() { return JOB_TYPE; }
    public EntityDataAccessor<Optional<BlockPos>> getJobBlockPosAccessor() { return JOB_BLOCK_POS; }
    public EntityDataAccessor<Long> getLastDayTrackedAccessor() { return LAST_DAY_TRACKED; }
    public EntityDataAccessor<String> getGenderAccessor() { return GENDER; }
    public EntityDataAccessor<String> getAgeAccessor() { return AGE; }
    public EntityDataAccessor<Integer> getAgingTimerAccessor() { return AGING_TIMER; }

    /**
     * Gets the aging target for this child (stored in persistent data)
     */

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Main animation controller - handles movement, interact, and attack animations
        controllers.add(new AnimationController<>(this, "main", 0, state -> {
            boolean isInteracting = state.getAnimatable().getEntityData().get(IS_INTERACTING);
            boolean isAttacking = state.getAnimatable().getEntityData().get(IS_ATTACKING);

            // Priority: Attack (while moving) > Interact > Movement
            if (isAttacking) {
                // Show attack animation regardless of movement state
                return state.setAndContinue(ModAnimationDefinitions.ATTACK);
            } else if (isInteracting) {
                return state.setAndContinue(ModAnimationDefinitions.INTERRACT);
            }
            // Otherwise show movement animations
            else if (sleepSystem.isSleeping()) {
                state.setAnimation(ModAnimationDefinitions.IDLE);
            } else if (state.isMoving()) {
                state.setAnimation(ModAnimationDefinitions.WALK);
            } else {
                state.setAnimation(ModAnimationDefinitions.IDLE);
            }
            return PlayState.CONTINUE;
        }));

        // Death controller
        controllers.add(ModAnimationDefinitions.ModdedDeathController(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
