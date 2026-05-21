package net.darkflameproduction.agotmod.block.custom.barrel;

import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BarrelDummyBlockEntity extends BlockEntity {

    @Nullable
    private BlockPos controllerPos = null;

    public BarrelDummyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BARREL_DUMMY.get(), pos, state);
    }

    public void setControllerPos(BlockPos pos) {
        this.controllerPos = pos.immutable();
        setChanged();
    }

    @Nullable
    public BlockPos getControllerPos() {
        return controllerPos;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (controllerPos != null) {
            tag.putLong("ControllerPos", controllerPos.asLong());
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("ControllerPos")) {
            controllerPos = BlockPos.of(tag.getLong("ControllerPos"));
        }
    }
}