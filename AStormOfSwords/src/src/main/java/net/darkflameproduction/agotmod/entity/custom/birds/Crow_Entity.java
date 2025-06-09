package net.darkflameproduction.agotmod.entity.custom.birds;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class Crow_Entity extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final EntityDataAccessor<Boolean> DATA_RESTING = SynchedEntityData.defineId(Crow_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final TargetingConditions RESTING_TARGETING = TargetingConditions.forNonCombat().range(4.0);

    @Nullable
    private BlockPos targetPosition;
    private int timeInDarkness = 0;

    public Crow_Entity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10f)
                .add(Attributes.FOLLOW_RANGE, 32D)
                .add(Attributes.MOVEMENT_SPEED, 0.3F)
                .add(Attributes.FLYING_SPEED, 2F);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_RESTING, false);
    }

    public boolean isResting() {
        return this.entityData.get(DATA_RESTING);
    }

    public void setResting(boolean rest) {
        this.entityData.set(DATA_RESTING, rest);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isResting()) {
            this.setDeltaMovement(Vec3.ZERO);
            this.setPosRaw(this.getX(), Mth.floor(this.getY()), this.getZ());
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.3, 1.0));
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
    }

    @Override
    public boolean hurtServer(ServerLevel p_376275_, DamageSource p_376205_, float p_376647_) {
        if (this.isResting()) {
            this.setResting(false);
        }
        return super.hurtServer(p_376275_, p_376205_, p_376647_);
    }


    private boolean hasBoostedUp = false;
    private boolean isFlying = false;
    private boolean upwardFlightInProgress = false;
    private int timeFlying = 0;
    private int landingDelay = 0;
    private int restingDuration = 0;
    private double targetY = 0.0;
    private int restingTicks = 0;

    @Override
    protected void customServerAiStep(ServerLevel level) {
        super.customServerAiStep(level);
        BlockPos blockpos = this.blockPosition();
        BlockPos blockBelow = blockpos.below();

        if (this.isResting()) {
            restingTicks++;

            // If resting for the first time, assign a random duration
            if (restingDuration == 0) {
                restingDuration = this.random.nextInt(900) + 100;
            }

            // If resting duration is over or we decide to stop early, transition out of resting
            if (restingTicks >= restingDuration || this.random.nextInt(100) == 0) {
                this.setResting(false);
                restingTicks = 0;
                hasBoostedUp = false;
                isFlying = false;
                timeFlying = 0;
                landingDelay = 0;
                restingDuration = 0;
                targetY = 0.0;
                if (!this.isSilent()) {
                    level.levelEvent(null, 1025, blockpos, 0);
                }
                return;
            }

            // If resting, check if the block below is valid, else stop resting
            if (isValidRestingBlock(blockBelow)) {
                if (this.random.nextInt(200) == 0) {
                    this.yHeadRot = this.random.nextInt(360);
                }

                // If resting too long and a player is nearby, transition out of resting
                if (restingTicks > 600 && level.getNearestPlayer(RESTING_TARGETING, this) != null) {
                    this.setResting(false);
                    restingTicks = 0;
                    hasBoostedUp = false;
                    isFlying = false;
                    timeFlying = 0;
                    landingDelay = 0;
                    restingDuration = 0;
                    targetY = 0.0;
                    if (!this.isSilent()) {
                        level.levelEvent(null, 1025, blockpos, 0);
                    }
                }
            } else {
                this.setResting(false);
                restingTicks = 0;
                hasBoostedUp = false;
                isFlying = false;
                timeFlying = 0;
                landingDelay = 0;
                restingDuration = 0;
                targetY = 0.0;
                if (!this.isSilent()) {
                    level.levelEvent(null, 1025, blockpos, 0);
                }
            }
        } else {
            // Flying behavior when not resting
            if (!hasBoostedUp) {
                targetY = this.getY() + (this.random.nextInt(11) + 10);
                hasBoostedUp = true;
                isFlying = true;
                landingDelay = this.random.nextInt(1401) + 200;
                upwardFlightInProgress = true;
            }

            if (upwardFlightInProgress) {
                if (this.getY() < targetY) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0, 1.5 / 20.0, 0));
                } else {
                    upwardFlightInProgress = false;
                    isFlying = false;
                }

                if (targetPosition == null || this.random.nextInt(10) == 0 || targetPosition.closerToCenterThan(this.position(), 3.0)) {
                    targetPosition = BlockPos.containing(
                            this.getX() + this.random.nextInt(50) - this.random.nextInt(50),
                            this.getY() + this.random.nextInt(40) - this.random.nextInt(20),
                            this.getZ() + this.random.nextInt(50) - this.random.nextInt(50)
                    );
                }

                Vec3 movement = this.getDeltaMovement();
                Vec3 direction = new Vec3(
                        targetPosition.getX() + 0.5 - this.getX(),
                        targetPosition.getY() + 0.1 - this.getY(),
                        targetPosition.getZ() + 0.5 - this.getZ()
                );
                Vec3 newMovement = movement.add(
                        (Math.signum(direction.x) * 0.5 - movement.x) * 0.1F,
                        (Math.signum(direction.y) * 0.3 - movement.y) * 0.1F,
                        (Math.signum(direction.z) * 0.5 - movement.z) * 0.1F
                );

                this.setDeltaMovement(newMovement);
                float f = (float)(Mth.atan2(newMovement.z, newMovement.x) * 180.0F / Math.PI) - 90.0F;
                float f1 = Mth.wrapDegrees(f - this.getYRot());
                this.setYRot(this.getYRot() + f1);
                this.zza = 0.5F;
            }

            if (!upwardFlightInProgress && !this.isResting()) {
                if (targetPosition == null || this.random.nextInt(10) == 0 || targetPosition.closerToCenterThan(this.position(), 20.0)) {
                    targetPosition = BlockPos.containing(
                            this.getX() + this.random.nextInt(50) - this.random.nextInt(50),
                            this.getY() + this.random.nextInt(40) - this.random.nextInt(20),
                            this.getZ() + this.random.nextInt(50) - this.random.nextInt(50)
                    );
                }

                Vec3 movement = this.getDeltaMovement();
                Vec3 direction = new Vec3(
                        targetPosition.getX() + 0.5 - this.getX(),
                        targetPosition.getY() + 0.1 - this.getY(),
                        targetPosition.getZ() + 0.5 - this.getZ()
                );
                Vec3 newMovement = movement.add(
                        (Math.signum(direction.x) * 0.5 - movement.x) * 0.1F,
                        (Math.signum(direction.y) * 0.3 - movement.y) * 0.1F,
                        (Math.signum(direction.z) * 0.5 - movement.z) * 0.1F
                );

                this.setDeltaMovement(newMovement);
                float f = (float)(Mth.atan2(newMovement.z, newMovement.x) * 180.0F / Math.PI) - 90.0F;
                float f1 = Mth.wrapDegrees(f - this.getYRot());
                this.setYRot(this.getYRot() + f1);
                this.zza = 0.5F;
            }
        }

        // Check if the crow has landed and can start resting
        if (!this.isResting() && this.onGround()) {
            BlockPos posBelow = this.blockPosition().below();
            if (isValidRestingBlock(posBelow)) {
                this.setResting(true);
                restingTicks = 0;
                isFlying = false;
            }
        }
    }


    private boolean isValidRestingBlock(BlockPos pos) {
        BlockState state = this.level().getBlockState(pos);
        return state.isFaceSturdy(this.level(), pos, Direction.UP) ||
                state.is(BlockTags.DIRT) ||
                state.is(BlockTags.LEAVES) ||
                state.is(Blocks.FARMLAND) ||
                state.is(BlockTags.STONE_BRICKS) ||
                state.is(BlockTags.STAIRS) ||
                state.is(BlockTags.SLABS) ||
                state.is(BlockTags.PLANKS) ||
                state.is(BlockTags.LOGS) ||
                state.isRedstoneConductor(this.level(), pos);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 0, state -> {
            if (!this.isResting()) {
                state.setAnimation(ModAnimationDefinitions.FLY);
                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));

        controllers.add(ModAnimationDefinitions.ModdedAttackController(this));
        controllers.add(ModAnimationDefinitions.ModdedSummonController(this));
        controllers.add(ModAnimationDefinitions.ModdedDeathController(this));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Nullable
    @Override
    public SoundEvent getAmbientSound() {
        return ModSounds.CROW_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return ModSounds.CROW_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.CROW_DEATH.get();
    }
}
