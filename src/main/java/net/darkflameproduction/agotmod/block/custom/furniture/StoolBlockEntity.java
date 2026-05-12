package net.darkflameproduction.agotmod.block.custom.furniture;

import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class StoolBlockEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StoolBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STOOL.get(), pos, state);
    }

    public ResourceLocation getTextureLocation() {
        Block block = getBlockState().getBlock();
        String key = BuiltInRegistries.BLOCK.getKey(block).getPath(); // e.g. "acacia_stool"
        String woodType = key.replace("_stool", "");                  // e.g. "acacia"
        String textureName = "chair3" + woodType;                     // e.g. "chair3acacia"
        return ResourceLocation.fromNamespaceAndPath("agotmod",
                "textures/block/chair/" + textureName + ".png");
    }

    public int getRotationY() {
        BlockState state = getBlockState();
        if (!(state.getBlock() instanceof StoolBlock)) return 0;
        return switch (state.getValue(StoolBlock.FACING)) {
            case NORTH -> 0;
            case EAST  -> 90;
            case SOUTH -> 180;
            case WEST  -> 270;
            default    -> 0;
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}