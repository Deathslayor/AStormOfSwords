package net.darkflameproduction.agotmod.block.custom;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

// Extends RotatedPillarBlock — already handles AXIS (X/Y/Z) and placement direction
public class WattleAndDaubBlock extends RotatedPillarBlock {
    public WattleAndDaubBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
}
