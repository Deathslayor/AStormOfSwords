package net.astormofsorts.agotmod.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.astormofsorts.agotmod.AGoTMod;
import net.astormofsorts.agotmod.worldgen.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class NBTBasedTrunkPlacer extends TrunkPlacer {
    public static final Codec<NBTBasedTrunkPlacer> CODEC = RecordCodecBuilder.create(instance -> instance);
    private final int variants = 2;

    public NBTBasedTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.NBT_BASED_TRUNK_PLACER_TYPE;
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader pLevel, @NotNull BiConsumer<BlockPos, BlockState> pBlockSetter, @NotNull RandomSource pRandom, int pFreeTreeHeight, @NotNull BlockPos pPos, @NotNull TreeConfiguration pConfig) {
        // TODO: Here the tree should be build
        //growTree(pLevel, pPos, pRandom);
        return List.of();
    }

    public void growTree(ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
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
        }
    }
}
