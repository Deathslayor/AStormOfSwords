package net.darkflameproduction.agotmod.entity.custom.wolves;

import net.darkflameproduction.agotmod.entity.animations.ModAnimationDefinitions;
import net.darkflameproduction.agotmod.entity.goal.DirewolfAttackGoal;
import net.darkflameproduction.agotmod.entity.goal.DirewolfPersistentMoveTowardsTargetGoal;
import net.darkflameproduction.agotmod.sound.ModSounds;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import static net.minecraft.world.entity.animal.Wolf.PREY_SELECTOR;


public class Direwolf_Entity extends TamableAnimal implements GeoEntity {
    private static final EntityDataAccessor<Boolean> DATA_SPRINTING = SynchedEntityData.defineId(Direwolf_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_ATTACKING = SynchedEntityData.defineId(Direwolf_Entity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(Direwolf_Entity.class, EntityDataSerializers.BOOLEAN);

    private static final float SPRINT_SPEED_MULTIPLIER = 3F;
    private WrappedGoal activeMoveGoalWrapper;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int attackCooldown = 0;

    // Sitting animation definition
    public static final RawAnimation SIT = RawAnimation.begin().thenPlay("misc.sit");

    public Direwolf_Entity(EntityType<? extends TamableAnimal> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new DirewolfAttackGoal(this));
        // FIX: Remove the boolean parameter from FollowOwnerGoal constructor
        this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(4, new DirewolfPersistentMoveTowardsTargetGoal(this, 0.6D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        // Add tamed-specific targeting goals
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, PREY_SELECTOR));
        this.targetSelector.addGoal(6, new NonTameRandomTargetGoal<>(this, Turtle.class, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 120f)
                .add(Attributes.FOLLOW_RANGE, 64D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5f)
                .add(Attributes.MOVEMENT_SPEED, 0.4F)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 18f);
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.WOLF_PANT;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.WOLF_WHINE;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return SoundEvents.WOLF_DEATH;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movement", 0, state -> {
            if (this.isAttacking()) {
                return PlayState.STOP;
            }
            if (this.isOrderedToSit()) {
                state.setAnimation(SIT);
                return PlayState.CONTINUE;
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
        builder.define(SITTING, false);
    }

    public boolean canAttack() {
        return attackCooldown <= 0;
    }

    public void setAttackCooldown(int cooldownTicks) {
        this.attackCooldown = cooldownTicks;
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

    // Required for TamableAnimal implementation
    @Override
    public boolean isOrderedToSit() {
        return this.entityData.get(SITTING);
    }

    @Override
    public void setOrderedToSit(boolean sitting) {
        this.entityData.set(SITTING, sitting);
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isOrderedToSit()) {
            if (this.getNavigation().isInProgress()) {
                this.getNavigation().stop();
            }
            super.travel(Vec3.ZERO);
            return;
        }
        super.travel(pTravelVector);
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

        if (attackCooldown > 0) {
            attackCooldown--;
        }

        if (!this.level().isClientSide()) {
            if (this.isOrderedToSit() && this.isSprinting()) {
                this.setSprinting(false);
                this.entityData.set(DATA_SPRINTING, false);
                this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F);
            }

            if (this.isAttacking()) {
                if (this.isSprinting()) {
                    this.setSprinting(false);
                    this.entityData.set(DATA_SPRINTING, false);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F);
                }
            }

            LivingEntity target = this.getTarget();
            if (target != null && !this.isOrderedToSit()) {
                boolean isCloseToTarget = this.distanceToSqr(target) < 16.0D;
                if (isCloseToTarget && this.isSprinting()) {
                    this.setSprinting(false);
                    this.entityData.set(DATA_SPRINTING, false);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F);
                } else if (!isCloseToTarget && !this.isSprinting() && !this.isAttacking()) {
                    this.setSprinting(true);
                    this.entityData.set(DATA_SPRINTING, true);
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F * SPRINT_SPEED_MULTIPLIER);
                }
            } else {
                if (this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue() != 0.4F && !this.isSprinting()) {
                    this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F);
                }
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Sitting", this.isOrderedToSit());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setOrderedToSit(tag.getBoolean("Sitting"));
    }

    @Override
    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        if (!this.level().isClientSide()) {
            boolean shouldSprint = target != null && !this.isOrderedToSit();
            if (shouldSprint != this.isSprinting()) {
                this.setSprinting(shouldSprint);
                this.entityData.set(DATA_SPRINTING, shouldSprint);
                float speed = shouldSprint ? 0.4F * SPRINT_SPEED_MULTIPLIER : 0.4F;
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

    // Taming behavior
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();

        // If already tamed
        if (this.isTame()) {
            // Check if the player is the owner before allowing any interaction
            if (this.isOwnedBy(player)) {
                // Toggle sitting state on right-click when empty-handed or not holding a food item
                if (itemstack.isEmpty()) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                }

                // If holding a valid food that the direwolf can eat and the direwolf is hurt
                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.heal(5.0F); // Heal more than regular wolves since direwolves have more health
                    this.gameEvent(GameEvent.EAT);
                    return InteractionResult.SUCCESS;
                }
            } else {
                // Player is not the owner, cannot interact with the direwolf
                // You could add a message here if you want to inform the player
                return InteractionResult.PASS;
            }
        }
        // Attempt to tame the direwolf
        else if (item == Items.BONE) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            // 25% chance of taming (harder than regular wolves)
            if (this.random.nextInt(4) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget(null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte)7); // Heart particles
            } else {
                this.level().broadcastEntityEvent(this, (byte)6); // Smoke particles
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        // Direwolves prefer raw meat
        return pStack.getItem() == Items.BEEF || pStack.getItem() == Items.MUTTON
                || pStack.getItem() == Items.CHICKEN || pStack.getItem() == Items.PORKCHOP;
    }

    @Override
    public boolean wantsToAttack(LivingEntity target, LivingEntity owner) {
        // Don't attack owner or other tamed direwolves of the same owner
        if (target instanceof Direwolf_Entity direwolf && direwolf.isTame() && direwolf.getOwner() == owner) {
            return false;
        }

        // Don't attack the owner
        if (target == owner) {
            return false;
        }

        return true;
    }

    @Nullable
    @Override
    public Direwolf_Entity getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        // Direwolves don't breed (for now)
        return null;
    }
}