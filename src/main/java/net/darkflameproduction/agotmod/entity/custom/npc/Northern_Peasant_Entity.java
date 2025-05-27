package net.darkflameproduction.agotmod.entity.custom.npc;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.entity.custom.npc.goals.*;
import net.darkflameproduction.agotmod.entity.custom.npc.system.*;
import net.darkflameproduction.agotmod.gui.GrocerInventoryScreen;
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
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
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

    // Entity data accessors
    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Long> LAST_SLEEP_TIME = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_IN_MAIN_HAND =
            SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> HUNGER_LEVEL = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> NEEDS_FOOD_COLLECTION = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<String> JOB_TYPE = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Optional<BlockPos>> JOB_BLOCK_POS = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Long> LAST_DAY_TRACKED = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.LONG);

    // Door goal reference for persistence
    private OpenAndCloseDoorGoal doorGoal;
    // Grocer collection goal reference for persistence
    private GrocerCollectionGoal grocerCollectionGoal;

    private final SleepSystem sleepSystem;
    private final HungerSystem hungerSystem;
    private final InventorySystem inventorySystem;
    private final HomeSystem homeSystem;
    private final JobSystem jobSystem;
    private final FarmingSystem farmingSystem;
    private final GrocerSystem grocerSystem;
    private final TeleportSystem teleportSystem;
    private final NameSystem nameSystem;
    private static final Random RANDOM = new Random();

    // Daily reset tracking
    private long lastDayTracked = -1;

    // Constants
    public static final int PEASANT_SLOT_OFFSET = 400;
    private static final int PEASANT_INVENTORY_SIZE = 54;
    private final SimpleContainer inventory = new SimpleContainer(PEASANT_INVENTORY_SIZE);

    public Northern_Peasant_Entity(EntityType<? extends Northern_Peasant_Entity> entityType, Level level) {
        super(entityType, level);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
        this.getNavigation().setRequiredPathLength(48.0F);

        // Initialize systems
        this.sleepSystem = new SleepSystem(this);
        this.hungerSystem = new HungerSystem(this);
        this.inventorySystem = new InventorySystem(this, inventory);
        this.homeSystem = new HomeSystem(this);
        this.jobSystem = new JobSystem(this);
        this.farmingSystem = new FarmingSystem(this);
        this.grocerSystem = new GrocerSystem(this);
        this.teleportSystem = new TeleportSystem(this);
        this.nameSystem = new NameSystem(this);
    }

    // Getters for systems
    public SleepSystem getSleepSystem() { return sleepSystem; }
    public HungerSystem getHungerSystem() { return hungerSystem; }
    public InventorySystem getInventorySystem() { return inventorySystem; }
    public HomeSystem getHomeSystem() { return homeSystem; }
    public JobSystem getJobSystem() { return jobSystem; }
    public FarmingSystem getFarmingSystem() { return farmingSystem; }
    public GrocerSystem getGrocerSystem() { return grocerSystem; }
    public TeleportSystem getTeleportSystem() { return teleportSystem; }
    public NameSystem getNameSystem() { return nameSystem; }

    // Getter for grocer collection goal
    public GrocerCollectionGoal getGrocerCollectionGoal() { return grocerCollectionGoal; }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.doorGoal = new OpenAndCloseDoorGoal(this);
        this.goalSelector.addGoal(1, doorGoal);
        this.goalSelector.addGoal(1, new BarrelDropOffGoal(this));
        this.goalSelector.addGoal(2, new FindBedGoal(this));
        this.goalSelector.addGoal(3, new SleepGoal(this));
        this.goalSelector.addGoal(6, new CollectFoodGoal(this));
        this.goalSelector.addGoal(7, new ReturnToJobBlockGoal(this));
        this.goalSelector.addGoal(8, new FindJobGoal(this));
        this.goalSelector.addGoal(9, new FarmingGoal(this));

        // Store reference to grocer collection goal for persistence
        this.grocerCollectionGoal = new GrocerCollectionGoal(this);
        this.goalSelector.addGoal(9, grocerCollectionGoal);

        this.goalSelector.addGoal(10, new RestrictedWanderGoal(this, 0.6D));
        this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(12, new RandomLookAroundGoal(this));
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
        builder.define(HUNGER_LEVEL, HungerSystem.MAX_HUNGER);
        builder.define(NEEDS_FOOD_COLLECTION, false);
        builder.define(JOB_TYPE, JobSystem.JOB_NONE);
        builder.define(JOB_BLOCK_POS, Optional.empty());
        builder.define(LAST_DAY_TRACKED, -1L);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putLong("LastDayTracked", lastDayTracked);

        sleepSystem.saveData(compound);
        hungerSystem.saveData(compound);
        inventorySystem.saveData(compound, this.registryAccess());
        homeSystem.saveData(compound);
        jobSystem.saveData(compound);
        farmingSystem.saveData(compound);
        grocerSystem.saveData(compound);
        teleportSystem.saveData(compound);
        nameSystem.saveData(compound, this.registryAccess());

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
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        lastDayTracked = compound.getLong("LastDayTracked");

        sleepSystem.loadData(compound);
        hungerSystem.loadData(compound);
        inventorySystem.loadData(compound, this.registryAccess());
        homeSystem.loadData(compound);
        jobSystem.loadData(compound);
        farmingSystem.loadData(compound);
        grocerSystem.loadData(compound);
        teleportSystem.loadData(compound);
        nameSystem.loadData(compound, this.registryAccess());

        // Load grocer collection goal data
        if (compound.contains("GrocerCollectionGoal") && grocerCollectionGoal != null) {
            CompoundTag grocerGoalTag = compound.getCompound("GrocerCollectionGoal");
            grocerCollectionGoal.loadCollectionData(grocerGoalTag);
        }

        // Load door states - must be done after goals are registered
        if (doorGoal != null) {
            doorGoal.loadOpenedBlocks(compound);
        }
    }

    @Override
    public void tick() {
        super.tick();

        // Check for new day and reset daily states
        if (!this.level().isClientSide) {
            checkForNewDay();
        }

        // Update all systems
        sleepSystem.tick();
        hungerSystem.tick();
        inventorySystem.tick();
        homeSystem.tick();
        jobSystem.tick();
        farmingSystem.tick();
        grocerSystem.tick();
        teleportSystem.tick();
    }

    private void checkForNewDay() {
        long currentDay = this.level().getDayTime() / 24000;

        if (currentDay > lastDayTracked) {
            System.out.println("DEBUG: " + this.getDisplayName().getString() + " - New day detected: " + lastDayTracked + " -> " + currentDay);

            // Reset daily states for grocer collection goal
            if (grocerCollectionGoal != null && getJobType().equals(JobSystem.JOB_GROCER)) {
                grocerCollectionGoal.resetDailyStateAfterSleep();
                System.out.println("DEBUG: " + this.getDisplayName().getString() + " - Reset grocer collection goal daily state");
            }

            // Update synched data
            this.getEntityData().set(LAST_DAY_TRACKED, currentDay);
            lastDayTracked = currentDay;

            System.out.println("DEBUG: " + this.getDisplayName().getString() + " - Daily reset completed for day " + currentDay);
        }
    }

    @Override
    public void aiStep() {
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

            // Check if this is a grocer
            if (getJobType().equals(JobSystem.JOB_GROCER)) {
                if (!this.level().isClientSide) {
                    // Server side - send grocer inventory data to client
                    if (player instanceof ServerPlayer serverPlayer) {
                        List<GrocerSystem.GrocerInventoryEntry> entries = grocerSystem.getSortedInventoryEntries();
                        String grocerName = this.getDisplayName().getString();

                        OpenGrocerInventoryPacket packet = new OpenGrocerInventoryPacket(grocerName, entries);
                        serverPlayer.connection.send(packet);
                    }
                } else {
                    // Client side - open the GUI directly (this will be triggered by the packet)
                    List<GrocerSystem.GrocerInventoryEntry> entries = grocerSystem.getSortedInventoryEntries();
                    String grocerName = this.getDisplayName().getString();

                    net.minecraft.client.Minecraft.getInstance().setScreen(
                            new GrocerInventoryScreen(entries, grocerName)
                    );
                }
                return InteractionResult.SUCCESS;
            } else {
                // Regular inventory for non-grocers
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
        }

        return result;
    }

    @Override
    public void remove(RemovalReason reason) {
        jobSystem.onRemove();
        sleepSystem.onRemove();
        homeSystem.onRemove();
        grocerSystem.onRemove();
        if (!this.level().isClientSide && reason == RemovalReason.KILLED) {
            inventorySystem.dropAllItems();
        }
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
            nameSystem.generateRandomName(p_35439_.getRandom());
            // Initialize day tracking
            lastDayTracked = this.level().getDayTime() / 24000;
            this.getEntityData().set(LAST_DAY_TRACKED, lastDayTracked);
        }
        return super.finalizeSpawn(p_35439_, p_35440_, p_363222_, p_35442_);
    }

    // Delegate methods to systems
    public boolean shouldSleep() { return sleepSystem.shouldSleep(); }
    public boolean isSleeping() { return sleepSystem.isSleeping(); }
    public void setSleeping(boolean sleeping) { sleepSystem.setSleeping(sleeping); }
    public BlockPos getBedPos() { return sleepSystem.getBedPos(); }
    public void setBedPos(BlockPos pos) { sleepSystem.setBedPos(pos); }

    public int getHungerLevel() { return hungerSystem.getHungerLevel(); }
    public void setHungerLevel(int hunger) { hungerSystem.setHungerLevel(hunger); }
    public boolean isHungry() { return hungerSystem.isHungry(); }
    public boolean needsFoodCollection() { return hungerSystem.needsFoodCollection(); }
    public void setNeedsFoodCollection(boolean needs) { hungerSystem.setNeedsFoodCollection(needs); }

    public String getJobType() { return jobSystem.getJobType(); }
    public void setJobType(String jobType) { jobSystem.setJobType(jobType); }
    public boolean hasJob() { return jobSystem.hasJob(); }
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

    // Item handling delegates
    @Override
    public ItemStack getMainHandItem() { return inventorySystem.getMainHandItem(); }

    @Override
    public Iterable<ItemStack> getArmorSlots() { return inventorySystem.getArmorSlots(); }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot slot) { return inventorySystem.getItemBySlot(slot); }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) { inventorySystem.setItemSlot(slot, stack); }

    // Sleep delegates
    @Override
    public void startSleeping(BlockPos pos) {
        super.startSleeping(pos);
        sleepSystem.startSleeping(pos);
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 0, state -> {
            if (sleepSystem.isSleeping()) {
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
}