package net.darkflameproduction.agotmod.entity.custom.norththewall;



import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.entity.goal.PersistentMoveTowardsTargetGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.util.GeckoLibUtil;


    public class Mammoth_Entity extends PathfinderMob implements GeoEntity{
        private static final EntityDataAccessor<Boolean> DATA_SPRINTING = SynchedEntityData.defineId(Mammoth_Entity.class, EntityDataSerializers.BOOLEAN);
        private WrappedGoal activeMoveGoalWrapper;
        private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


        public Mammoth_Entity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
            super(p_21683_, p_21684_);

        }



        @Override
        protected void registerGoals() {
            this.goalSelector.addGoal(0, new FloatGoal(this));
            this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
            this.goalSelector.addGoal(2, new PersistentMoveTowardsTargetGoal(this, 0.6D, 64.0F));



            this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
            this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
            this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
            this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        }



        public static AttributeSupplier.Builder createAttributes() {
            return PathfinderMob.createLivingAttributes()
                    .add(Attributes.MAX_HEALTH, 150D)
                    .add(Attributes.FOLLOW_RANGE, 64D)
                    .add(Attributes.KNOCKBACK_RESISTANCE, 5f)
                    .add(Attributes.MOVEMENT_SPEED, 0.25F)
                    .add(Attributes.ATTACK_KNOCKBACK, 2f)
                    .add(Attributes.ATTACK_DAMAGE, 50f);

        }


        @Nullable
        protected SoundEvent getAmbientSound() {
            return SoundEvents.LIGHTNING_BOLT_THUNDER;
        }


        @Nullable
        protected SoundEvent getHurtSound(DamageSource pDamageSource) {
            return SoundEvents.LIGHTNING_BOLT_IMPACT;
        }


        @Nullable
        protected SoundEvent getDeathSound() {
            return SoundEvents.LIGHTNING_BOLT_IMPACT;
        }


        @Override
        public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
            controllers.add(DefaultAnimations.genericWalkRunIdleController(this));
            controllers.add(DefaultAnimations.genericAttackAnimation(this,DefaultAnimations.ATTACK_STRIKE));
            controllers.add(ModAnimationDefinitions.ModdedSummonController(this));
            controllers.add(ModAnimationDefinitions.ModdedDeathController(this));
        }




        @Override
        public AnimatableInstanceCache getAnimatableInstanceCache() {
            return this.cache;
        }


        @Override
        protected void defineSynchedData(SynchedEntityData.Builder builder) {
            super.defineSynchedData(builder);
            builder.define(DATA_SPRINTING, false);
        }

        // Rest of your class remains the same, but here are other potential updates:

        @Override
        public void tick() {
            super.tick();

            // Change from this.level to this.level()
            if (!this.level().isClientSide() && this.getTarget() != null) {
                boolean isCloseToTarget = this.distanceToSqr(this.getTarget()) < 16.0D;
                boolean shouldSprint = !isCloseToTarget;

                if (shouldSprint != this.isSprinting()) {
                    this.setSprinting(shouldSprint);
                    this.entityData.set(DATA_SPRINTING, shouldSprint);

                    float speed = shouldSprint ? 0.4F : 0.25F;
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);
                }
            }
        }

        private void updateSprintingState() {
            // Find the active MoveTowardsTargetGoal wrapper if we don't have one
            if (activeMoveGoalWrapper == null || !activeMoveGoalWrapper.isRunning()) {
                activeMoveGoalWrapper = this.goalSelector.getAvailableGoals()
                        .stream().filter(wrappedGoal -> wrappedGoal.getGoal() instanceof MoveTowardsTargetGoal)
                        .findFirst()
                        .orElse(null);
            }

            boolean shouldSprint = activeMoveGoalWrapper != null && activeMoveGoalWrapper.isRunning();

            if (shouldSprint != this.entityData.get(DATA_SPRINTING)) {
                this.entityData.set(DATA_SPRINTING, shouldSprint);
                this.setSprinting(shouldSprint);
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
        public void aiStep(){
            super.aiStep();

            updateSwingTime();

        }

        @Override
        public int getCurrentSwingDuration () {
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
























