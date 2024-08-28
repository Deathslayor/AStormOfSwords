package net.astormofsorts.agotmod.worldgen.tree;

import net.astormofsorts.agotmod.AGoTMod;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class NBTBasedTreeGrower extends AbstractTreeGrower {
    private final String base;
    private final int variants;

    public NBTBasedTreeGrower(String base, int variants) {
        this.base = base;
        this.variants = variants;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(@NotNull RandomSource pRandom, boolean pHasFlowers) {
        // Return null since we are not using a ConfiguredFeature to place the structure
        return null;
    }

    @Override
    public boolean growTree(ServerLevel level, @NotNull ChunkGenerator pGenerator, @NotNull BlockPos pos, @NotNull BlockState pState, @NotNull RandomSource random) {
        // randomly get variant number
        int randomNumber = random.nextInt(variants + 1);
        Optional<StructureTemplate> template = level.getStructureManager().get(new ResourceLocation(AGoTMod.MOD_ID, base + randomNumber));

        if (template.isPresent()) {
            // Calculate the position for the structure
            BlockPos structurePos = pos.offset(0, 0, 0); // Adjust as needed for your structure placement

            // Place the structure in the world
            StructurePlaceSettings settings = new StructurePlaceSettings()
                    .setRotation(Rotation.NONE)
                    .setMirror(Mirror.NONE)
                    .setBoundingBox(new BoundingBox(structurePos).inflatedBy(10)); // Adjust the bounding box size as needed

            template.get().placeInWorld(level, structurePos, structurePos, settings, random, 2);
            return true;
        }

        return false;
    }
}