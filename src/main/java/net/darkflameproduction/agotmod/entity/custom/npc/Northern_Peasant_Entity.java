package net.darkflameproduction.agotmod.entity.custom.npc;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.player.Player;
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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Northern_Peasant_Entity extends PathfinderMob implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Long> LAST_SLEEP_TIME = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.LONG);

    private static final int SLEEP_START_TIME = 12542;
    private static final int SLEEP_END_TIME = 23460;
    private BlockPos bedPos;
    private int bedSearchCooldown = 0;

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
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("IsSleeping", this.isSleeping());
        compound.putLong("LastSleepTime", this.entityData.get(LAST_SLEEP_TIME));
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
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(IS_SLEEPING, compound.getBoolean("IsSleeping"));
        this.entityData.set(LAST_SLEEP_TIME, compound.getLong("LastSleepTime"));
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

        return super.hurtServer(level, source, amount);
    }

    @Override
    public void remove(RemovalReason reason) {
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
        if (this.isSleeping()) {
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
            ServerLevelAccessor p_35439_, net.minecraft.world.DifficultyInstance p_35440_, EntitySpawnReason p_363222_, @Nullable SpawnGroupData p_35442_
    ) {
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