package net.astormofsorts.agotmod.entity.custom;

import net.astormofsorts.agotmod.entity.ModEntities;
import net.astormofsorts.agotmod.entity.ai.BearAttackGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

// BearEntity represents the custom entity class for the Bear
public class BearEntity extends Animal {

    // Data accessor for tracking whether the Bear is attacking
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(BearEntity.class, EntityDataSerializers.BOOLEAN);

    // Animation state for idle animations
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    // Animation state for attack animations
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    // Constructor for BearEntity
    public BearEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    // Tick method for entity behavior
    @Override
    public void tick() {
        super.tick();

        // Client-side animation setup
        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    // Method for setting up animation states
    private void setupAnimationStates() {
        // Idle animation logic
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        // Attack animation logic
        if (this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 80; // Length in ticks
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        // Stop attack animation when not attacking
        if (!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    // Update walk animation based on the entity's pose
    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    // Method for setting the attacking state
    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    // Method for checking if the Bear is attacking
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    // Method for defining synchronized data
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
    }

    // Method for registering AI goals
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new BearAttackGoal(this, 1, true));

        this.goalSelector.addGoal(1, new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.COOKED_BEEF), false));

        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));

        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    // Method for creating attribute builder for Bear
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 200D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 10f);
    }

    // Method for getting the offspring of two Bear entities
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.BEAR.get().create(pLevel);
    }

    // Method for checking if an item is food for the Bear
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.COOKED_BEEF);
    }

    // Method for getting the ambient sound of the Bear
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.HOGLIN_AMBIENT;
    }

    // Method for getting the hurt sound of the Bear
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    // Method for getting the death sound of the Bear
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }
}
