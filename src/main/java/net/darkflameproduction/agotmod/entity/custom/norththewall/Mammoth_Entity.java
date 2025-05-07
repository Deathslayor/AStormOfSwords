package net.darkflameproduction.agotmod.entity.custom.norththewall;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.entity.goal.MammothAttackGoal;
import net.darkflameproduction.agotmod.entity.goal.PersistentMoveTowardsTargetGoal;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;


public class Mammoth_Entity extends PathfinderMob implements GeoEntity {
    private static final EntityDataAccessor<Boolean> DATA_SPRINTING = SynchedEntityData.defineId(Mammoth_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Mammoth_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final float SPRINT_SPEED_MULTIPLIER = 3F;
    private WrappedGoal activeMoveGoalWrapper;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int attackCooldown = 0;



    public Mammoth_Entity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
            this.goalSelector.addGoal(0, new FloatGoal(this));
            this.goalSelector.addGoal(1, new MammothAttackGoal(this));
            this.goalSelector.addGoal(2, new PersistentMoveTowardsTargetGoal(this, 0.6D, 64.0F));
            this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
            this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
            this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
            this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
        }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 450f)
                .add(Attributes.FOLLOW_RANGE, 64D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5f)
                .add(Attributes.MOVEMENT_SPEED, 0.2F)
                .add(Attributes.ATTACK_KNOCKBACK, 10f)
                .add(Attributes.ATTACK_DAMAGE, 30f);
    }


    @Nullable
    protected SoundEvent getAmbientSound() {
        return ModSounds.MAMMOTH_AMBIENT.get();
    }



    @Nullable
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.MAMMOTH_HURT.get();
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return ModSounds.MAMMOTH_DEATH.get();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 0, state -> {
            if (this.isAttacking()) {
                return PlayState.STOP;
            }
            if (state.isMoving()) {
                if (this.isSprinting()) {
                    state.setAnimation(ModAnimationDefinitions.RUN);
                } else {
                    state.setAnimation(ModAnimationDefinitions.WALK);
                }
                return PlayState.CONTINUE;
            }
            state.setAnimation(ModAnimationDefinitions.IDLE);
            return PlayState.CONTINUE;
        }));
        controllers.add(ModAnimationDefinitions.ModdedAttackController(this));
        controllers.add(ModAnimationDefinitions.ModdedSummonController(this));
        controllers.add(ModAnimationDefinitions.ModdedDeathController(this));
    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_SPRINTING, false);
        builder.define(IS_ATTACKING, false);
    }

    public boolean canAttack() {
        return attackCooldown <= 0;
    }

    public void setIsAttacking(boolean attacking) {
        if (attacking) {
            if (canAttack()) {
                this.entityData.set(IS_ATTACKING, true);
                attackCooldown = 1;
            }
        } else {
            this.entityData.set(IS_ATTACKING, false);
        }
    }


    public boolean isAttacking() {
        return this.entityData.get(IS_ATTACKING);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isAttacking()) {
            super.travel(new Vec3(0, pTravelVector.y, 0));
        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.isAttacking()) {
            updateSwingTime();
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void tick() {
        super.tick();

        // Update attack cooldown
        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (!this.level().isClientSide()) {
            // If attacking, ensure sprinting is disabled
            if (this.isAttacking()) {
                if (this.isSprinting()) {
                    this.setSprinting(false);
                    this.entityData.set(DATA_SPRINTING, false);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F);
                }
                return;
            }

            LivingEntity target = this.getTarget();
            if (target != null) {
                boolean isCloseToTarget = this.distanceToSqr(target) < 16.0D;
                if (isCloseToTarget && this.isSprinting()) {
                    this.setSprinting(false);
                    this.entityData.set(DATA_SPRINTING, false);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F);
                } else if (!isCloseToTarget && !this.isSprinting() && !this.isAttacking()) {
                    this.setSprinting(true);
                    this.entityData.set(DATA_SPRINTING, true);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F * SPRINT_SPEED_MULTIPLIER);
                }
            } else {
                if (this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() != 0.2F) {
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F);
                }
            }
        }
    }




    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        if (!this.level().isClientSide()) {
            boolean shouldSprint = target != null;
            if (shouldSprint != this.isSprinting()) {
                this.setSprinting(shouldSprint);
                this.entityData.set(DATA_SPRINTING, shouldSprint);
                float speed = shouldSprint ? 0.2F * SPRINT_SPEED_MULTIPLIER : 0.2F;
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);
            }
        }
    }




    @Override
    public boolean isSprinting() {
        return this.entityData.get(DATA_SPRINTING);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (DATA_SPRINTING.equals(key)) {
            this.setSprinting(this.isSprinting());
        }
        super.onSyncedDataUpdated(key);
    }

    @Override
    public int getCurrentSwingDuration() {
        return 11;
    }

    protected void updateSwingTime() {
        int i = this.getCurrentSwingDuration();
        if (this.swinging) {
            ++this.swingTime;
            if (this.swingTime >= i) {
                this.swingTime = 0;
                this.swinging = false;
            }
        } else {
            this.swingTime = 0;
        }
        this.attackAnim = (float)this.swingTime / (float)i;
    }
}