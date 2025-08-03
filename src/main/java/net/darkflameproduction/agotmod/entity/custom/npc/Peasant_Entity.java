package net.darkflameproduction.agotmod.entity.custom.npc;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.behaviour.*;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer.FarmerBarrelDropOffGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.farmer.FarmingGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.grocer.GrocerCollectionGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.guard.GuardCombatGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.guard.GuardPatrolGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.inventory.CollectFoodGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.sleep.FindBedGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.sleep.SleepGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.miner.MinerGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.miner.MinerBarrelDropOffGoal;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.JobSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.behaviour.NameSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.farmer.FarmingSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.grocer.GrocerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.guard.GuardSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.miner.MinerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.inventory.HungerSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.inventory.InventorySystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.HomeSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.SleepSystem;
import net.darkflameproduction.agotmod.entity.custom.npc.system.sleep.TeleportSystem;
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
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

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

    // Door goal reference for persistence
    private OpenAndCloseDoorGoal doorGoal;
    // Grocer collection goal reference for persistence
    private GrocerCollectionGoal grocerCollectionGoal;

    // Player interaction tracking
    private Player currentInteractingPlayer = null;
    private int interactionCooldown = 0;

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
    private static final Random RANDOM = new Random();

    // Daily reset tracking
    private long lastDayTracked = -1;

    // Animation tracking
    private int interactAnimationTimer = 0;
    private static final int INTERACT_ANIMATION_DURATION = 8; // Animation lasts 8 ticks (0.375 seconds * 20 ticks)

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
        this.getNavigation().setRequiredPathLength(48.0F);

        // Initialize all systems
        this.sleepSystem = new SleepSystem(this);
        this.hungerSystem = new HungerSystem(this);
        this.inventorySystem = new InventorySystem(this, inventory);
        this.homeSystem = new HomeSystem(this);
        this.jobSystem = new JobSystem(this);
        this.farmingSystem = new FarmingSystem(this);
        this.grocerSystem = new GrocerSystem(this);
        this.teleportSystem = new TeleportSystem(this);
        this.nameSystem = new NameSystem(this);
        this.guardSystem = new GuardSystem(this);
        this.minerSystem = new MinerSystem(this);
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

    // Getter for grocer collection goal
    public GrocerCollectionGoal getGrocerCollectionGoal() { return grocerCollectionGoal; }

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

        // Combat goals - only for adults
        if (isAdult()) {
            this.goalSelector.addGoal(1, new GuardCombatGoal(this));
            this.goalSelector.addGoal(2, new PeasantDefenseGoal(this));
        }

        this.doorGoal = new OpenAndCloseDoorGoal(this);
        this.goalSelector.addGoal(3, doorGoal);

        // Barrel drop off goals - only for adults
        if (isAdult()) {
            this.goalSelector.addGoal(4, new FarmerBarrelDropOffGoal(this));
            this.goalSelector.addGoal(4, new MinerBarrelDropOffGoal(this));
        }

        this.goalSelector.addGoal(5, new FindBedGoal(this));
        this.goalSelector.addGoal(6, new SleepGoal(this));
        this.goalSelector.addGoal(7, new CollectFoodGoal(this));

        // Job-related goals - only for adults
        if (isAdult()) {
            // FIXED: MinerGoal now has priority 8 (higher than other work goals)
            this.goalSelector.addGoal(8, new MinerGoal(this));
            this.goalSelector.addGoal(9, new ReturnToJobBlockGoal(this));
            this.goalSelector.addGoal(10, new FindJobGoal(this));
            this.goalSelector.addGoal(11, new FarmingGoal(this));

            this.grocerCollectionGoal = new GrocerCollectionGoal(this);
            this.goalSelector.addGoal(12, grocerCollectionGoal);

            this.goalSelector.addGoal(13, new GuardPatrolGoal(this));
        }

        this.goalSelector.addGoal(14, new RestrictedWanderGoal(this, 0.6D));
        this.goalSelector.addGoal(15, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(16, new RandomLookAroundGoal(this));

        // Target selection goals - only for adults
        if (isAdult()) {
            this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.MAX_HEALTH, 40.0)
                .add(Attributes.FOLLOW_RANGE, 48.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0);
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

        // Save all systems data (excluding equipment - handled by native system)
        sleepSystem.saveData(compound);
        hungerSystem.saveData(compound);
        homeSystem.saveData(compound);
        jobSystem.saveData(compound);
        farmingSystem.saveData(compound);
        grocerSystem.saveData(compound);
        teleportSystem.saveData(compound);
        nameSystem.saveData(compound, this.registryAccess());
        guardSystem.saveData(compound); // Save guard system data
        minerSystem.saveData(compound); // Save miner system data

        // Save inventory system data (only regular inventory, not equipment)
        inventorySystem.saveData(compound, this.registryAccess());

        // Save grocer collection goal data
        if (grocerCollectionGoal != null) {
            CompoundTag grocerGoalTag = new CompoundTag();
            grocerCollectionGoal.saveCollectionData(grocerGoalTag);
            compound.put("GrocerCollectionGoal", grocerGoalTag);
        }

        // Save door states
        if (doorGoal != null) {
            doorGoal.saveOpenedBlocks(compound);
        }

        // Equipment is automatically saved by Minecraft's native system via super.addAdditionalSaveData()
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        lastDayTracked = compound.getLong("LastDayTracked");
        interactAnimationTimer = compound.getInt("InteractAnimationTimer");

        // Load attack state
        if (compound.contains("IsAttacking")) {
            this.setIsAttacking(compound.getBoolean("IsAttacking"));
        }

        // Load gender
        if (compound.contains("Gender")) {
            this.setGender(compound.getString("Gender"));
        }

        // Load age
        if (compound.contains("Age")) {
            this.setAge(compound.getString("Age"));
        }

        // Load aging timer
        if (compound.contains("AgingTimer")) {
            this.setAgingTimer(compound.getInt("AgingTimer"));
        }

        // Load all systems data (excluding equipment - handled by native system)
        sleepSystem.loadData(compound);
        hungerSystem.loadData(compound);
        homeSystem.loadData(compound);
        jobSystem.loadData(compound);
        farmingSystem.loadData(compound);
        grocerSystem.loadData(compound);
        teleportSystem.loadData(compound);
        nameSystem.loadData(compound, this.registryAccess());
        guardSystem.loadData(compound); // Load guard system data
        minerSystem.loadData(compound); // Load miner system data

        // Load inventory system data (only regular inventory, not equipment)
        inventorySystem.loadData(compound, this.registryAccess());

        // Load grocer collection goal data
        if (compound.contains("GrocerCollectionGoal") && grocerCollectionGoal != null) {
            CompoundTag grocerGoalTag = compound.getCompound("GrocerCollectionGoal");
            grocerCollectionGoal.loadCollectionData(grocerGoalTag);
        }

        // Load door states - must be done after goals are registered
        if (doorGoal != null) {
            doorGoal.loadOpenedBlocks(compound);
        }

        // Equipment is automatically loaded by Minecraft's native system via super.readAdditionalSaveData()
        // Sync main hand with entity data after everything is loaded
        this.getEntityData().set(DATA_ITEM_IN_MAIN_HAND, this.getMainHandItem());
    }

    @Override
    public void tick() {
        super.tick();

        // Handle interaction cooldown
        if (interactionCooldown > 0) {
            interactionCooldown--;
        }

        // Handle interact animation timer
        if (interactAnimationTimer > 0) {
            interactAnimationTimer--;
            // Clear the synced flag when timer expires
            if (interactAnimationTimer <= 0 && !this.level().isClientSide) {
                this.getEntityData().set(IS_INTERACTING, false);
            }
        }

        // Check if current interacting player is still valid
        if (currentInteractingPlayer != null) {
            if (!currentInteractingPlayer.isAlive() || this.distanceToSqr(currentInteractingPlayer) > 64.0D) {
                clearInteractingPlayer();
            }
        }

        // Check for new day and reset daily states
        if (!this.level().isClientSide) {
            checkForNewDay();
        }

        // Update all systems including guard and miner systems
        sleepSystem.tick();
        hungerSystem.tick();
        inventorySystem.tick();
        homeSystem.tick();
        jobSystem.tick();
        farmingSystem.tick();
        grocerSystem.tick();
        teleportSystem.tick();
        guardSystem.tick(); // Update guard system
        minerSystem.tick(); // Update miner system

        // Handle aging for children
        if (!this.level().isClientSide && isChild()) {
            int currentTimer = getAgingTimer();
            currentTimer++;
            setAgingTimer(currentTimer);

            // Check if child should age to adult
            if (currentTimer >= getAgingTarget()) {
                ageToAdult();
            }
        }
    }

    private void checkForNewDay() {
        long currentDay = this.level().getDayTime() / 24000;

        if (currentDay > lastDayTracked) {
            // Reset daily states for grocer collection goal
            if (grocerCollectionGoal != null && getJobType().equals(JobSystem.JOB_GROCER)) {
                grocerCollectionGoal.resetDailyStateAfterSleep();
            }

            // Update synched data
            this.getEntityData().set(LAST_DAY_TRACKED, currentDay);
            lastDayTracked = currentDay;
        }
    }

    @Override
    public void aiStep() {
        // Freeze movement if interacting with player, during cooldown, OR playing interact animation (but NOT during attacks)
        if (isInteractingWithPlayer() || interactionCooldown > 0 || isPlayingInteractAnimation()) {
            // Stop all movement
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            this.getNavigation().stop();

            // Force the NPC to look at the interacting player (if any)
            if (currentInteractingPlayer != null) {
                this.getLookControl().setLookAt(currentInteractingPlayer, 10.0F, 10.0F);
            }

            // Don't call super.aiStep() to prevent normal AI from running
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
            if (sleepSystem.isSleeping()) {
                return InteractionResult.PASS;
            }

            // Set the interacting player
            setInteractingPlayer(player);

            // Check if this is a grocer
            if (getJobType().equals(JobSystem.JOB_GROCER)) {
                if (!this.level().isClientSide) {
                    // Server side - send grocer inventory data to client
                    if (player instanceof ServerPlayer serverPlayer) {
                        // Refresh inventory to ensure clean data (removes 0-quantity items)
                        grocerSystem.refreshInventoryDisplay();

                        // Get current inventory data
                        List<GrocerSystem.GrocerInventoryEntry> entries = grocerSystem.getSortedInventoryEntries();
                        String grocerName = this.getDisplayName().getString();

                        // Get grocer balance
                        long grocerBalance = grocerSystem.getGrocerBalance();

                        // Get player's current balance from their persistent data
                        long playerBalance = serverPlayer.getPersistentData().getLong(COIN_BALANCE_KEY);

                        // Send packet to client to open GUI with both balances
                        OpenGrocerInventoryPacket packet = new OpenGrocerInventoryPacket(grocerName, entries, grocerBalance, playerBalance);
                        serverPlayer.connection.send(packet);
                    }
                    return InteractionResult.SUCCESS;
                } else {
                    // Client side - just acknowledge the interaction
                    // The GUI will be opened when the packet is received
                    return InteractionResult.SUCCESS;
                }
            } else {
                // Regular inventory for non-grocers (including guards and miners)
                if (!this.level().isClientSide && player instanceof ServerPlayer serverPlayer) {
                    inventorySystem.openInventoryFor(serverPlayer);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source == level.damageSources().sweetBerryBush()) {
            return false;
        }

        boolean result = super.hurtServer(level, source, amount);

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
        jobSystem.onRemove();
        sleepSystem.onRemove();
        homeSystem.onRemove();
        grocerSystem.onRemove();
        guardSystem.onRemove(); // Clean up guard system
        // No special cleanup needed for miner system currently

        // Don't drop items when killed - comment out the inventory drop
        // if (!this.level().isClientSide && reason == RemovalReason.KILLED) {
        //     inventorySystem.dropAllItems();
        // }

        super.remove(reason);
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        // List of your throat clearing and cough sounds
        SoundEvent[] ambientSounds = new SoundEvent[] {
                ModSounds.NPC_COUGH.get(),
                ModSounds.NPC_CLEAR_THROAT_1.get(),
                ModSounds.NPC_CLEAR_THROAT_2.get(),
                ModSounds.NPC_CLEAR_THROAT_3.get(),
                ModSounds.NPC_CLEAR_THROAT_4.get()
        };
        // Randomly pick one to play
        return ambientSounds[RANDOM.nextInt(ambientSounds.length)];
    }

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
            ServerLevelAccessor p_35439_, DifficultyInstance p_35440_, EntitySpawnReason p_363222_, @Nullable SpawnGroupData p_35442_
    ) {
        if (!this.level().isClientSide) {
            // Randomly assign gender (50/50 split)
            String gender = p_35439_.getRandom().nextBoolean() ? GENDER_FEMALE : GENDER_MALE;
            this.setGender(gender);

            // Randomly assign age (80% adult, 20% child)
            String age = p_35439_.getRandom().nextFloat() < 0.8f ? AGE_ADULT : AGE_CHILD;
            this.setAge(age);

            // Set aging timer for children (randomized around average)
            if (age.equals(AGE_CHILD)) {
                int agingTime = AVERAGE_AGING_TICKS +
                        (p_35439_.getRandom().nextInt(AGING_VARIANCE * 2) - AGING_VARIANCE);
                // Store the target aging time (we'll count up to this)
                this.setAgingTimer(0); // Start at 0, will count up
                // Store the target in NBT for persistence
                this.getPersistentData().putInt("AgingTarget", agingTime);
            } else {
                this.setAgingTimer(0);
            }

            nameSystem.generateRandomName(p_35439_.getRandom());
            // Initialize day tracking
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
        // Children cannot have jobs
        if (isChild()) {
            return;
        }
        jobSystem.setJobType(jobType);
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
    private int getAgingTarget() {
        return this.getPersistentData().getInt("AgingTarget");
    }

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