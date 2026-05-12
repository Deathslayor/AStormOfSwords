package net.darkflameproduction.agotmod.entity.custom;

import net.darkflameproduction.agotmod.block.custom.furniture.ArmChairBlock;
import net.darkflameproduction.agotmod.block.custom.furniture.ChairBlock;
import net.darkflameproduction.agotmod.block.custom.furniture.StoolBlock;
import net.darkflameproduction.agotmod.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SeatEntity extends Entity {

    private BlockPos stoolPos;

    public SeatEntity(EntityType<? extends SeatEntity> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
        this.setInvisible(true);
    }

    public SeatEntity(Level level, BlockPos stoolPos, double x, double y, double z) {
        this(ModEntities.SEAT_ENTITY.get(), level);
        this.stoolPos = stoolPos;
        this.setPos(x, y, z);
    }

    @Override
    public boolean hurtServer(net.minecraft.server.level.ServerLevel level,
                              net.minecraft.world.damagesource.DamageSource source, float amount) {
        return false; // Seat entity cannot be damaged
    }

    @Override
    public void tick() {
        super.tick();

        if (level().isClientSide) return;

        // Dismount if no passengers
        if (getPassengers().isEmpty()) {
            this.discard();
            return;
        }

        // Dismount if player is sneaking
        for (Entity passenger : getPassengers()) {
            if (passenger instanceof net.minecraft.world.entity.player.Player player
                    && player.isShiftKeyDown()) {
                passenger.stopRiding();
                this.discard();
                return;
            }
        }

        // Dismount if seat block is gone
        if (stoolPos != null) {
            net.minecraft.world.level.block.Block block = level().getBlockState(stoolPos).getBlock();
            boolean isValidSeat = block instanceof StoolBlock
                    || block instanceof ChairBlock
                    || block instanceof ArmChairBlock;
            if (!isValidSeat) {
                ejectPassengers();
                this.discard();
                return;
            }
        }

        this.setDeltaMovement(net.minecraft.world.phys.Vec3.ZERO);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // No synced data needed
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        if (tag.contains("StoolX")) {
            stoolPos = new BlockPos(
                    tag.getInt("StoolX"),
                    tag.getInt("StoolY"),
                    tag.getInt("StoolZ")
            );
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (stoolPos != null) {
            tag.putInt("StoolX", stoolPos.getX());
            tag.putInt("StoolY", stoolPos.getY());
            tag.putInt("StoolZ", stoolPos.getZ());
        }
    }

    @Override
    public boolean canCollideWith(Entity other) {
        return false;
    }

    @Override
    protected void addPassenger(Entity passenger) {
        super.addPassenger(passenger);
        passenger.noPhysics = true;
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        passenger.noPhysics = false;
    }





    @Override
    public boolean shouldBeSaved() { return false; }

    @Override
    public boolean isPickable() { return false; }

    @Override
    public boolean isPushable() { return false; }
}