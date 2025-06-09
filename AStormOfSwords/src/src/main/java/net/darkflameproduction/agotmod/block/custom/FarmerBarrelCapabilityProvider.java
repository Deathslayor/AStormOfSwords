package net.darkflameproduction.agotmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;

import javax.annotation.Nullable;

public class FarmerBarrelCapabilityProvider {

    // Helper method to get item handler from a block entity
    @Nullable
    public static IItemHandler getItemHandler(Level level, BlockPos pos, @Nullable Direction side) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof FarmerBarrelBlockEntity barrel) {
            return barrel.getItemHandler();
        }
        return null;
    }

    // Helper method to check if a block entity provides item handler capability
    public static boolean hasItemHandler(Level level, BlockPos pos) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        return blockEntity instanceof FarmerBarrelBlockEntity;
    }
}