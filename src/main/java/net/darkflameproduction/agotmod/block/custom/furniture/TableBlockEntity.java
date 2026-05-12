package net.darkflameproduction.agotmod.block.custom.furniture;

import net.darkflameproduction.agotmod.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class TableBlockEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TABLE.get(), pos, state);
    }

    public ResourceLocation getTextureLocation() {
        Block block = getBlockState().getBlock();
        String key = net.minecraft.core.registries.BuiltInRegistries.BLOCK
                .getKey(block).getPath(); // e.g. "dark_oak_table"
        String woodType = key.replace("_table", ""); // "dark_oak"
        String textureName = "table_" + woodType;    // "table_dark_oak"
        return ResourceLocation.fromNamespaceAndPath("agotmod",
                "textures/block/table/" + textureName + ".png");
    }

    public TableModelInfo getModelInfo() {
        if (level == null) return new TableModelInfo("geo/block/table.geo.json", 0);

        BlockState state = level.getBlockState(worldPosition);

        if (!(state.getBlock() instanceof TableBlock)) {
            return new TableModelInfo("geo/block/table.geo.json", 0);
        }

        boolean n = state.getValue(TableBlock.NORTH);
        boolean s = state.getValue(TableBlock.SOUTH);
        boolean e = state.getValue(TableBlock.EAST);
        boolean w = state.getValue(TableBlock.WEST);

        int count = (n?1:0) + (s?1:0) + (e?1:0) + (w?1:0);

        // ── 0 sides ───────────────────────────────────────────────────────────
        if (count == 0) {
            return new TableModelInfo("geo/block/table.geo.json", 0);
        }

        // ── 1 side ────────────────────────────────────────────────────────────
        if (count == 1) {
            if (n) return new TableModelInfo("geo/block/table_side.geo.json", 0);
            if (s) return new TableModelInfo("geo/block/table_side.geo.json", 180);
            if (e) return new TableModelInfo("geo/block/table_side.geo.json", 270);
            if (w) return new TableModelInfo("geo/block/table_side.geo.json", 90);
        }

        // ── 2 sides ───────────────────────────────────────────────────────────
        if (count == 2) {
            // Opposite sides → middle
            if (n && s) return new TableModelInfo("geo/block/table_middle.geo.json", 0);
            if (e && w) return new TableModelInfo("geo/block/table_middle.geo.json", 90);

            // Corner — no rotation needed
            if (n && e) return new TableModelInfo("geo/block/table_corner.geo.json", 0);
            if (e && s) return new TableModelInfo("geo/block/table_corner.geo.json", 0);
            if (s && w) return new TableModelInfo("geo/block/table_corner.geo.json", 0);
            if (w && n) return new TableModelInfo("geo/block/table_corner.geo.json", 0);
        }

        // ── 3 or 4 sides → middle ─────────────────────────────────────────────
        return new TableModelInfo("geo/block/table_middle.geo.json", 0);
    }

    public record TableModelInfo(String modelPath, int rotationY) {}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}