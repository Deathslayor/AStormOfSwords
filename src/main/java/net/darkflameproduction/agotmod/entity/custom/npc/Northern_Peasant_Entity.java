package net.darkflameproduction.agotmod.entity.custom.npc;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
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
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class Northern_Peasant_Entity extends PathfinderMob implements GeoEntity {

    // GeckoLib
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Data for sleep tracking
    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Long> LAST_SLEEP_TIME = SynchedEntityData.defineId(Northern_Peasant_Entity.class, EntityDataSerializers.LONG);

    // Sleep constants - matching player sleep times
    private static final int SLEEP_START_TIME = 12542; // When players can sleep (sunset)
    private static final int SLEEP_END_TIME = 23460;   // When players wake up (sunrise)
    private BlockPos bedPos;

    // Static method to check if a bed is occupied by another peasant
    public static boolean isBedOccupied(Level level, BlockPos bedPos) {
        return level.getEntitiesOfClass(Northern_Peasant_Entity.class,
                        new net.minecraft.world.phys.AABB(bedPos).inflate(2.0))
                .stream()
                .anyMatch(peasant -> bedPos.equals(peasant.getBedPos()) && peasant.isSleeping());
    }

    public Northern_Peasant_Entity(EntityType<? extends Northern_Peasant_Entity> entityType, Level level) {
        super(entityType, level);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
        this.getNavigation().setRequiredPathLength(48.0F);
    }

    @Override
    public void tick() {
        super.tick();

        // Check if bed was destroyed and reset to normal behavior
        if (!this.level().isClientSide && this.getBedPos() != null) {
            BlockState bedState = this.level().getBlockState(this.getBedPos());
            if (!(bedState.getBlock() instanceof BedBlock)) {
                // Bed was destroyed, reset to normal wandering
                this.setBedPos(null);
                this.stopSleeping();
            }
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SleepGoal(this));
        this.goalSelector.addGoal(2, new FindBedGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.6D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
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
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(IS_SLEEPING, compound.getBoolean("IsSleeping"));
        this.entityData.set(LAST_SLEEP_TIME, compound.getLong("LastSleepTime"));
        if (compound.contains("BedX")) {
            bedPos = new BlockPos(compound.getInt("BedX"), compound.getInt("BedY"), compound.getInt("BedZ"));
        }
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

    // Sleep-related methods
    public boolean shouldSleep() {
        long dayTime = this.level().getDayTime() % 24000;
        // Only sleep during times when players can sleep (same as bed mechanics)
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
        this.bedPos = pos;
    }

    @Override
    public void startSleeping(BlockPos pos) {
        super.startSleeping(pos);
        setSleeping(true);
        setBedPos(pos);
    }

    @Override
    public void stopSleeping() {
        super.stopSleeping();
        setSleeping(false);
    }

    // GeckoLib implementation
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 0, state -> {
            if (this.isSleeping()) {
                state.setAnimation(ModAnimationDefinitions.IDLE); // Or create a sleep animation
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

    // Custom Goals
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
                    !Northern_Peasant_Entity.isBedOccupied(peasant.level(), peasant.getBedPos());
        }

        @Override
        public boolean canContinueToUse() {
            // Stop sleeping if it's no longer night time or bed was destroyed
            if (!peasant.shouldSleep()) {
                return false;
            }
            if (peasant.getBedPos() != null) {
                BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
                if (!(bedState.getBlock() instanceof BedBlock)) {
                    return false; // Bed was destroyed
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
            // If bed was destroyed, clear the bed position
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

        public FindBedGoal(Northern_Peasant_Entity peasant) {
            this.peasant = peasant;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            if (!peasant.shouldSleep() || peasant.isSleeping()) {
                return false;
            }

            // If we already have a bed, check if it's still valid and not occupied
            if (peasant.getBedPos() != null) {
                BlockState bedState = peasant.level().getBlockState(peasant.getBedPos());
                if (bedState.getBlock() instanceof BedBlock) {
                    // Check if bed is occupied by another peasant
                    if (Northern_Peasant_Entity.isBedOccupied(peasant.level(), peasant.getBedPos())) {
                        peasant.setBedPos(null); // Bed is occupied, find another
                    } else {
                        return peasant.distanceToSqr(peasant.getBedPos().getX(), peasant.getBedPos().getY(), peasant.getBedPos().getZ()) > 4.0D;
                    }
                } else {
                    peasant.setBedPos(null); // Bed was destroyed
                }
            }

            // Find a nearby bed that's not occupied
            BlockPos peasantPos = peasant.blockPosition();
            for (int x = -16; x <= 16; x++) {
                for (int y = -4; y <= 4; y++) {
                    for (int z = -16; z <= 16; z++) {
                        BlockPos checkPos = peasantPos.offset(x, y, z);
                        BlockState state = peasant.level().getBlockState(checkPos);
                        if (state.getBlock() instanceof BedBlock &&
                                !Northern_Peasant_Entity.isBedOccupied(peasant.level(), checkPos)) {
                            targetBed = checkPos;
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return targetBed != null && peasant.shouldSleep() &&
                    peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) > 4.0D;
        }

        @Override
        public void start() {
            if (targetBed != null) {
                peasant.getNavigation().moveTo(targetBed.getX(), targetBed.getY(), targetBed.getZ(), 1.0D);
            }
        }

        @Override
        public void stop() {
            if (targetBed != null && peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                peasant.setBedPos(targetBed);
            }
            targetBed = null;
            peasant.getNavigation().stop();
        }

        @Override
        public void tick() {
            if (targetBed != null) {
                peasant.getLookControl().setLookAt(targetBed.getX(), targetBed.getY(), targetBed.getZ());
                if (peasant.distanceToSqr(targetBed.getX(), targetBed.getY(), targetBed.getZ()) <= 4.0D) {
                    peasant.setBedPos(targetBed);
                    peasant.getNavigation().stop();
                }
            }
        }
    }
}