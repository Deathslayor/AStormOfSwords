package net.darkflameproduction.agotmod.entity.custom.npc;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Northern_Peasant_Entity extends PathfinderMob implements GeoEntity, InventoryCarrier {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Long> LAST_SLEEP_TIME = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.LONG);
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_IN_MAIN_HAND =
            SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.ITEM_STACK);

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
    private BlockPos bedPos;
    private int bedSearchCooldown = 0;

    // Eating and healing variables
    private int eatingTime = 0;
    private boolean isEating = false;
    private int healCooldown = 0;
    private static final int HEAL_COOLDOWN_TIME = 600; // 30 seconds cooldown between healing attempts
    private static final int FOOD_CHECK_INTERVAL = 60; // Check for food every 3 seconds when damaged
    private int foodCheckTimer = 0;

    public static final int PEASANT_SLOT_OFFSET = 400;
    private static final int PEASANT_INVENTORY_SIZE = 54;
    private final SimpleContainer inventory = new SimpleContainer(PEASANT_INVENTORY_SIZE);

    private static final Map<BlockPos, UUID> bedReservations = new HashMap<>();
    private static final Map<UUID, Long> reservationTimestamps = new HashMap<>();
    private static final long RESERVATION_TIMEOUT = 6000;

    public static boolean isBedOccupied(Level level, BlockPos bedPos) {
        return level.getEntitiesOfClass(Northern_Peasant_Entity.class,
                        new net.minecraft.world.phys.AABB(bedPos).inflate(2.0))
                .stream()
                .anyMatch(peasant -> bedPos.equals(peasant.getBedPos()) && peasant.isSleeping());
    }

    public static boolean isBedReserved(BlockPos bedPos, UUID excludeUUID) {
        cleanupExpiredReservations();
        UUID reservedBy = bedReservations.get(bedPos);
        return reservedBy != null && !reservedBy.equals(excludeUUID);
    }

    public static boolean reserveBed(BlockPos bedPos, UUID peasantUUID) {
        cleanupExpiredReservations();
        if (!isBedReserved(bedPos, peasantUUID)) {
            bedReservations.put(bedPos, peasantUUID);
            reservationTimestamps.put(peasantUUID, System.currentTimeMillis());
            return true;
        }
        return false;
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

    // Food and healing logic
    private boolean needsHealing() {
        return this.getHealth() < this.getMaxHealth() && this.healCooldown <= 0;
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
                this.heal(4.0F);
                this.healCooldown = HEAL_COOLDOWN_TIME;

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

        if (!this.level().isClientSide && this.isSleeping() && !this.shouldSleep()) {
            this.stopSleeping();
            this.setBedPos(null);
            releaseBedReservation(this.getUUID());
        }

        if (this.isSleeping()) {
            this.getNavigation().stop();
            this.setDeltaMovement(0, this.getDeltaMovement().y, 0);
            return;
        }

        if (!this.level().isClientSide && this.getBedPos() != null) {
            BlockState bedState = this.level().getBlockState(this.getBedPos());
            if (!(bedState.getBlock() instanceof BedBlock)) {
                this.setBedPos(null);
                this.stopSleeping();
                releaseBedReservation(this.getUUID());
            }
        }

        if (!this.level().isClientSide && shouldSleep() && !isSleeping() && this.getBedPos() != null) {
            if (isBedOccupied(this.level(), this.getBedPos()) || isBedReserved(this.getBedPos(), this.getUUID())) {
                this.setBedPos(null);
                releaseBedReservation(this.getUUID());
            }
        }

        // Handle healing and eating
        if (!this.level().isClientSide) {
            // Reduce heal cooldown
            if (this.healCooldown > 0) {
                this.healCooldown--;
            }

            // Check for food and eat if damaged
            if (!this.isSleeping() && !this.isEating && this.needsHealing()) {
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
        this.goalSelector.addGoal(2, new SleepGoal(this));
        this.goalSelector.addGoal(3, new FindBedGoal(this));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
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
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsSleeping", this.isSleeping());
        compound.putLong("LastSleepTime", this.entityData.get(LAST_SLEEP_TIME));

        this.writeInventoryToTag(compound, this.registryAccess());

        if (this.hasCustomName()) {
            compound.putString("CustomName", Component.Serializer.toJson(this.getCustomName(), this.registryAccess()));
        }

        if (bedPos != null) {
            compound.putInt("BedX", bedPos.getX());
            compound.putInt("BedY", bedPos.getY());
            compound.putInt("BedZ", bedPos.getZ());
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

        ItemStack mainHandItem = this.getItemInHand(InteractionHand.MAIN_HAND);
        if (!mainHandItem.isEmpty()) {
            // Fix 2: saveOptional() returns a Tag, so we store it directly
            compound.put("MainHandItem", mainHandItem.saveOptional(this.registryAccess()));
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(IS_SLEEPING, compound.getBoolean("IsSleeping"));
        this.entityData.set(LAST_SLEEP_TIME, compound.getLong("LastSleepTime"));

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

        // Load eating and healing data
        this.healCooldown = compound.getInt("HealCooldown");
        this.foodCheckTimer = compound.getInt("FoodCheckTimer");
        this.isEating = compound.getBoolean("IsEating");
        this.eatingTime = compound.getInt("EatingTime");

        if (compound.contains("MainHandItem")) {
            // Fix 3: Use parseOptional() with registry access instead of of()
            ItemStack stack = ItemStack.parseOptional(this.registryAccess(), compound.getCompound("MainHandItem"));
            this.setItemInHand(InteractionHand.MAIN_HAND, stack);
        }
    }

    @Override
    public boolean hurtServer(ServerLevel level, DamageSource source, float amount) {
        if (source == level.damageSources().sweetBerryBush()) {
            return false;
        }

        if (this.isSleeping()) {
            this.stopSleeping();
            this.setBedPos(null);
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
            return ModSounds.MAMMOTH_AMBIENT.get();
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.MAMMOTH_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MAMMOTH_DEATH.get();
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

    static class OpenAndCloseDoorGoal extends Goal {
        private final Northern_Peasant_Entity peasant;
        private BlockPos doorPos;
        private boolean hasDoorOpened;

        public OpenAndCloseDoorGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
        }

        @Override
        public boolean canUse() {
            if (peasant.isSleeping() && peasant.getSleepingPos().isPresent()) {
                return false;
            }

            BlockPos peasantPos = peasant.blockPosition();

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;

                        BlockPos checkPos = peasantPos.offset(x, y, z);
                        BlockState state = peasant.level().getBlockState(checkPos);

                        if (state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock) {
                            doorPos = checkPos;
                            return true;
                        }
                    }
                }
            }

            return hasDoorOpened && doorPos != null;
        }

        @Override
        public boolean canContinueToUse() {
            return doorPos != null;
        }

        @Override
        public void start() {
            hasDoorOpened = false;
        }

        @Override
        public void stop() {
            doorPos = null;
            hasDoorOpened = false;
        }

        @Override
        public void tick() {
            if (doorPos != null) {
                BlockState state = peasant.level().getBlockState(doorPos);
                if (state.getBlock() instanceof net.minecraft.world.level.block.DoorBlock doorBlock) {
                    double distance = peasant.distanceToSqr(doorPos.getX() + 0.5, doorPos.getY(), doorPos.getZ() + 0.5);

                    if (distance <= 2.0 && !state.getValue(net.minecraft.world.level.block.DoorBlock.OPEN)) {
                        doorBlock.setOpen(peasant, peasant.level(), state, doorPos, true);
                        hasDoorOpened = true;
                    }
                    else if (distance > 2.0 && state.getValue(net.minecraft.world.level.block.DoorBlock.OPEN) && hasDoorOpened) {
                        doorBlock.setOpen(peasant, peasant.level(), state, doorPos, false);
                        hasDoorOpened = false;
                        doorPos = null;
                    }
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
            return peasant.shouldSleep() && peasant.getBedPos() != null &&
                    !peasant.isSleeping() &&
                    !Northern_Peasant_Entity.isBedOccupied(peasant.level(), peasant.getBedPos()) &&
                    !Northern_Peasant_Entity.isBedReserved(peasant.getBedPos(), peasant.getUUID());
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
                peasant.startSleeping(bedPos);
            }
        }

        @Override
        public void stop() {
            peasant.stopSleeping();
            if (peasant.getBedPos() != null) {
                BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
                if (!(bedState.getBlock() instanceof BedBlock)) {
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
            if (!peasant.shouldSleep() || peasant.isSleeping() || !peasant.canUseBedSearch()) {
                return false;
            }

            if (peasant.getBedPos() != null) {
                BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
                if (bedState.getBlock() instanceof BedBlock) {
                    if (Northern_Peasant_Entity.isBedOccupied(peasant.level(), peasant.getBedPos()) ||
                            Northern_Peasant_Entity.isBedReserved(peasant.getBedPos(), peasant.getUUID())) {
                        peasant.setBedPos(null);
                    } else {
                        return peasant.distanceToSqr(peasant.getBedPos().getX(), peasant.getBedPos().getY(), peasant.getBedPos().getZ()) > 4.0D;
                    }
                } else {
                    peasant.setBedPos(null);
                }
            }

            return findNearbyBed();
        }

        private boolean findNearbyBed() {
            BlockPos peasantPos = peasant.blockPosition();
            int searchRadius = 32 + (searchAttempts * 8);
            searchRadius = Math.min(searchRadius, 96);

            for (int x = -searchRadius; x <= searchRadius; x++) {
                for (int y = -8; y <= 8; y++) {
                    for (int z = -searchRadius; z <= searchRadius; z++) {
                        BlockPos checkPos = peasantPos.offset(x, y, z);
                        BlockState state = peasant.level().getBlockState(checkPos);

                        if (state.getBlock() instanceof BedBlock) {
                            if (state.getValue(BedBlock.PART) == BedPart.HEAD &&
                                    !Northern_Peasant_Entity.isBedOccupied(peasant.level(), checkPos) &&
                                    !Northern_Peasant_Entity.isBedReserved(checkPos, peasant.getUUID())) {
                                targetBed = checkPos;
                                return true;
                            }
                        }
                    }
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
            return targetBed != null && peasant.shouldSleep() &&
                    peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) > 4.0D &&
                    !Northern_Peasant_Entity.isBedOccupied(peasant.level(), targetBed) &&
                    !Northern_Peasant_Entity.isBedReserved(targetBed, peasant.getUUID());
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

                if (Northern_Peasant_Entity.isBedOccupied(peasant.level(), targetBed) ||
                        Northern_Peasant_Entity.isBedReserved(targetBed, peasant.getUUID())) {
                    targetBed = null;
                    peasant.getNavigation().stop();
                    peasant.setBedSearchCooldown(20);
                    return;
                }

                if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                    BlockState bedState = peasant.level().getBlockState(targetBed);
                    if (bedState.getBlock() instanceof BedBlock &&
                            bedState.getValue(BedBlock.PART) == BedPart.HEAD) {
                        peasant.setBedPos(targetBed);
                        peasant.getNavigation().stop();
                    }
                }
            }
        }
    }
}