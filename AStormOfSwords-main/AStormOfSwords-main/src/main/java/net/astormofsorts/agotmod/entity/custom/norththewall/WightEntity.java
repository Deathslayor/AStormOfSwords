package net.astormofsorts.agotmod.entity.custom.norththewall;

import net.astormofsorts.agotmod.entity.ModEntities;
import net.astormofsorts.agotmod.entity.ai.BearAttackGoal;
import net.astormofsorts.agotmod.entity.animations.ModAnimationDefinitions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Unit;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.warden.Emerging;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.warden.WardenAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimationController;

// BearEntity represents the custom entity class for the Bear
public class WightEntity extends Monster {

    private boolean isSpawning = false;

    // Data accessor for tracking whether the Bear is attacking
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(WightEntity.class, EntityDataSerializers.BOOLEAN);

    // Animation state for idle animations
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    // Animation state for attack animations
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public final AnimationState emerginState = new AnimationState();
    public int emerginTimeout = 0;

    // Constructor for BearEntity
    public WightEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    // Tick method for entity behavior
    @Override
    public void tick() {
        super.tick();

        // Client-side animation setup
        if (this.level().isClientSide()) {
            setupAnimationStates();

            // Continue emerging animation while still in the spawn animation
            if (this.isSpawning) {
                if (this.emerginTimeout > 0) {
                    this.emerginTimeout--;
                } else {
                    // Spawn animation is complete, set the flag to false and transition to other animations
                    this.isSpawning = false;
                    // Optionally set a different pose or transition to other animations
                    // Example: this.setPose(Pose.STANDING);
                }
            }
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

        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.COOKED_BEEF), false));

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

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag compoundTag) {
        // Call the super method
        spawnGroupData = super.finalizeSpawn(levelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);

        // Check if this is the first time the entity is spawned (you can use a flag or a property)
        if (!this.level().isClientSide() && mobSpawnType == MobSpawnType.SPAWNER) {
            // Play your spawn animation here
            this.setPose(Pose.EMERGING);
        }

        return spawnGroupData;
    }
}
