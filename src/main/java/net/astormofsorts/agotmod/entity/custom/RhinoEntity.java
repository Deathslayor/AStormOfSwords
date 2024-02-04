package net.astormofsorts.agotmod.entity.custom;

import net.astormofsorts.agotmod.entity.ModEntities;
import net.astormofsorts.agotmod.entity.ai.RhinoAttackGoal;
import net.astormofsorts.agotmod.entity.variant.RhinoVariant;
import net.astormofsorts.agotmod.sound.ModSounds;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// RhinoEntity represents the custom entity class for the Rhino
public class RhinoEntity extends TamableAnimal implements PlayerRideable, Saddleable, ContainerListener {
    protected SimpleContainer inventory;
    public static final int INV_SLOT_SADDLE = 0;
    public static final int INV_SLOT_ARMOR = 1;
    public static final int INV_BASE_COUNT = 2;
    public static final int EQUIPMENT_SLOT_OFFSET = 400;
    public static final int CHEST_SLOT_OFFSET = 499;
    public static final int INVENTORY_SLOT_OFFSET = 500;

    // Data accessor for tracking whether the Rhino is attacking
    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.BOOLEAN);

    public static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_IS_SADDLED = SynchedEntityData.defineId(RhinoEntity.class, EntityDataSerializers.BOOLEAN);


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
        this.createInventory();
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
        this.entityData.define(DATA_IS_SADDLED, false);
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
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RhinoVariant variant = Util.getRandom(RhinoVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Varient"));
        Camel.areAllEffectsAmbient()
        this.updateContainerEquipment();
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
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
    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Animal.createLivingAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.FOLLOW_RANGE, 24D).add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ARMOR_TOUGHNESS, 0.1f).add(Attributes.ATTACK_KNOCKBACK, 0.5f).add(Attributes.ATTACK_DAMAGE, 24f);
    }

    // Method for getting the offspring of two Rhino entities
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel pLevel, @NotNull AgeableMob pOtherParent) {
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
    protected SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ModSounds.MAMMOTH_DAMAGED.get();
    }

    // Method for getting the death sound of the Rhino
    @Nullable
    @Override
    protected SoundEvent getDeathSound() {

        return ModSounds.MAMMOTH_ATTACK.get();
    }

    public @NotNull InteractionResult mobInteract(@NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        if (!this.isVehicle() && !this.isBaby()) {
            if (this.isTame() && pPlayer.isSecondaryUseActive()) {
                //this.openCustomInventoryScreen(pPlayer);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            } else {
                ItemStack itemstack = pPlayer.getItemInHand(pHand);
                if (!itemstack.isEmpty()) {
                    InteractionResult interactionresult = itemstack.interactLivingEntity(pPlayer, this, pHand);
                    if (interactionresult.consumesAction()) {
                        return interactionresult;
                    }

                }
            }


        } else {
            return super.mobInteract(pPlayer, pHand);
        }
        return InteractionResult.SUCCESS;


    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return ((LivingEntity) this.getFirstPassenger());
    }

    @Override
    public void travel(@NotNull Vec3 pTravelVector) {
        if (this.isVehicle() && getControllingPassenger() instanceof Player) {
            LivingEntity livingentity = this.getControllingPassenger();
            this.setYRot(livingentity.getYRot());
            this.yRotO = this.getYRot();
            this.setXRot(livingentity.getXRot() * 0.5F);
            this.setRot(this.getYRot(), this.getXRot());
            this.yBodyRot = this.getYRot();
            this.yHeadRot = this.yBodyRot;
            float f = livingentity.xxa * 0.5F;
            float f1 = livingentity.zza;

            // Inside this if statement, we are on the client!
            if (this.isControlledByLocalInstance()) {
                float newSpeed = (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
                // increasing speed by 100% if the spring key is held down (number for testing purposes)
                if (Minecraft.getInstance().options.keySprint.isDown()) {
                    newSpeed *= 2f;
                }

                this.setSpeed(newSpeed);
                super.travel(new Vec3(f, pTravelVector.y, f1));
            }
        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity pLivingEntity) {
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

    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@javax.annotation.Nullable SoundSource pSource) {
        this.inventory.setItem(0, new ItemStack(Items.SADDLE));
    }


    @Override
    public @NotNull SoundEvent getSaddleSoundEvent() {
        return Saddleable.super.getSaddleSoundEvent();
    }

    @Override
    public boolean isSaddled() {
        return this.entityData.get(DATA_IS_SADDLED);
    }


    @Override
    public void containerChanged(@NotNull Container pContainer) {
        this.updateContainerEquipment();
    }

    @Override
    public boolean alwaysAccepts() {
        return super.alwaysAccepts();
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        return super.getOwner();
    }


    protected int getInventorySize() {
        return 2;
    }

    protected void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.updateContainerEquipment();
    }

    protected void updateContainerEquipment() {
        if (!this.level().isClientSide) {
            this.entityData.set(DATA_IS_SADDLED, !this.inventory.getItem(0).isEmpty());
        }
    }
}