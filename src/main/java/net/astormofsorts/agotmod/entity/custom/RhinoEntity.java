package net.astormofsorts.agotmod.entity.custom;

import net.astormofsorts.agotmod.entity.ModEntities;
import net.astormofsorts.agotmod.entity.ai.RhinoAttackGoal;
import net.astormofsorts.agotmod.entity.variant.RhinoVariant;
import net.astormofsorts.agotmod.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.Nullable;

// RhinoEntity represents the custom entity class for the Rhino
public class RhinoEntity extends TamableAnimal implements PlayerRideable {

    // Data accessor for tracking whether the Rhino is attacking
    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
            SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.INT);

    // Animation state for idle animations
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    // Animation state for attack animations
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    // Constructor for RhinoEntity
    public RhinoEntity(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setMaxUpStep(1f);
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

    // Method for checking if the Rhino is attacking
    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    // Method for defining synchronized data
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(DATA_ID_TYPE_VARIANT, 0);
    }

    public RhinoVariant getVariant() {
        return RhinoVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(RhinoVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RhinoVariant variant = Util.getRandom(RhinoVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Varient"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Varient", this.getTypeVariant());
    }

    // Method for registering AI goals
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new RhinoAttackGoal(this, 1.5, true));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(7, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(8, new BreedGoal(this, 1.15D));
    }

    // Method for creating attribute builder for Rhino
    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 24f);
    }

    // Method for getting the offspring of two Rhino entities
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.RHINO.get().create(pLevel);
    }

    // Method for checking if an item is food for the Rhino
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.APPLE);
    }

    // Method for getting the ambient sound of the Rhino
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.MAMMOTH_IDLE.get();
    }

    // Method for getting the hurt sound of the Rhino
    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return ModSounds.MAMMOTH_DAMAGED.get();
    }

    // Method for getting the death sound of the Rhino
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.MAMMOTH_ATTACK.get();
    }

    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        Item itemForTaming = Items.APPLE;

        if (item == itemForTaming && !isTame()) {
            if (this.level().isClientSide()) {
                return InteractionResult.CONSUME;
            } else {
                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (!ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    super.tame(pPlayer);
                    this.navigation.recomputePath();
                    this.setTarget(null);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                    setOrderedToSit(true);
                    this.setInSittingPose(true);
                }

                return InteractionResult.SUCCESS;
            }
        }

        // TOGGLES SITTING FOR OUR ENTITY
        if (isTame() && pHand == InteractionHand.MAIN_HAND) {
            if (!pPlayer.isCrouching()) {
                setRiding(pPlayer);
            } else {
                setOrderedToSit(!isOrderedToSit());
                setInSittingPose(!isOrderedToSit());
            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(pPlayer, pHand);
    }

    private void setRiding(Player pPlayer) {
        this.setInSittingPose(false);

        pPlayer.setYRot(this.getYRot());
        pPlayer.setXRot(this.getXRot());
        pPlayer.startRiding(this);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pLivingEntity) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() != Direction.Axis.Y) {
            int[][] offsets = DismountHelper.offsetsForDirection(direction);
            BlockPos blockpos = this.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (Pose pose : pLivingEntity.getDismountPoses()) {
                AABB aabb = pLivingEntity.getLocalBoundsForPose(pose);

                for (int[] offset : offsets) {
                    blockpos$mutableblockpos.set(blockpos.getX() + offset[0], blockpos.getY(), blockpos.getZ() + offset[1]);
                    double d0 = this.level().getBlockFloorHeight(blockpos$mutableblockpos);
                    if (DismountHelper.isBlockFloorValid(d0)) {
                        Vec3 vec3 = Vec3.upFromBottomCenterOf(blockpos$mutableblockpos, d0);
                        if (DismountHelper.canDismountTo(this.level(), pLivingEntity, aabb.move(vec3))) {
                            pLivingEntity.setPose(pose);
                            return vec3;
                        }
                    }
                }
            }
        }

        return super.getDismountLocationForPassenger(pLivingEntity);
    }
}
