package net.darkflameproduction.agotmod.block;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.*;
import net.darkflameproduction.agotmod.block.custom.BarleyCropBlock;
import net.darkflameproduction.agotmod.block.custom.specialleaves.WeirwoodLeavesBlock;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.util.ModWoodTypes;
import net.darkflameproduction.agotmod.worldgen.tree.ModTreeGrower;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;


// ModBLocks class for registering custom blocks
public class ModBLocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AGoTMod.MOD_ID);

    // ✅ Store valid blackstone indices for use in data generators
    public static final List<Integer> VALID_BLACKSTONE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15 && i != 22 && i != 31)
                    .boxed()
                    .collect(Collectors.toList())
    );
    public static final List<Integer> VALID_BASALT_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 1 && i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );
    public static final List<Integer> VALID_TUFF_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 10 && i != 15 && i != 20 && i != 21)
                    .boxed()
                    .collect(Collectors.toList())
    );
    public static final List<Integer> VALID_BRICKS_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 2 && i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );
    public static final List<Integer> VALID_CALCITE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_DIORITE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_QUARTZ_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 14 && i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_CDEEPSLATE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 4 && i != 15 && i != 19)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_GRANITE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_ANDESITE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_REDKEEP_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_RSANDSTONE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15 && i != 16 && i != 17 && i != 18)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_SANDSTONE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15 && i != 16 && i != 17 && i != 18)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_STONE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 5 && i != 15 && i != 19 && i != 23)
                    .boxed()
                    .collect(Collectors.toList())
    );
    public static final List<Integer> VALID_SSTONE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15 && i != 23)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_BONE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15 && i != 35)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_DRIPSTONE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_PACKED_ICE_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );

    public static final List<Integer> VALID_MUD_BRICK_INDICES = new ArrayList<>(
            IntStream.rangeClosed(1, 38)
                    .filter(i -> i != 7 && i != 9 && i != 15)
                    .boxed()
                    .collect(Collectors.toList())
    );


    public static final Map<Integer, BlockSet> BLACKSTONE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> BASALT_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> BRICKS_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> CALCITE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> CDEEPSLATE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> GRANITE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> REDKEEP_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> RSANDSTONE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> SANDSTONE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> STONE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> SSTONE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> BONE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> DRIPSTONE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> PACKED_ICE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> MUD_BRICK_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> ANDESITE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> QUARTZ_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> DIORITE_VARIANTS = new HashMap<>();
    public static final Map<Integer, BlockSet> TUFF_VARIANTS = new HashMap<>();




    private static <T extends Block> @NotNull DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties, boolean registerItem) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block, properties);
        // Register the block item only if the flag is true
        if (registerItem) {
            registerBlockItem(name, toReturn);
        }
        return toReturn;
    }

    private static <T extends Block> @NotNull DeferredHolder<Item, ?> registerBlockItem(String name, DeferredBlock<T> block) {
        return ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }


    private static void registerBlackstoneVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(3f);

        for (int i : VALID_BLACKSTONE_INDICES) {
            String baseName = "blackstone_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            BLACKSTONE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!BLACKSTONE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped blackstone variant: blackstone_" + i);
            }
        }
    }

    private static void registerTuffVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).strength(3f);

        for (int i : VALID_TUFF_INDICES) {
            String baseName = "tuff_brick_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            TUFF_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!TUFF_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped tuff variant: tuff_" + i);
            }
        }
    }


    private static void registerDioriteVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE).strength(3f);

        for (int i : VALID_DIORITE_INDICES) {
            String baseName = "diorite_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            DIORITE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!DIORITE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped diorite variant: diorite_" + i);
            }
        }
    }


    private static void registerQuartzVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).strength(3f);

        for (int i : VALID_QUARTZ_INDICES) {
            String baseName = "quartz_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            QUARTZ_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!QUARTZ_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped quartz variant: quartz_" + i);
            }
        }
    }



    private static void registerStoneVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f);

        for (int i : VALID_STONE_INDICES) {
            String baseName = "stone_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            STONE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!STONE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped stone variant: stone_" + i);
            }
        }
    }

    private static void registerAndesiteVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).strength(3f);

        for (int i : VALID_ANDESITE_INDICES) {
            String baseName = "andesite_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            ANDESITE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!ANDESITE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped andesite variant: andesite_" + i);
            }
        }
    }



    private static void registerBasaltVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f);

        for (int i : VALID_BASALT_INDICES) {
            String baseName = "basalt_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            BASALT_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!BASALT_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped basalt variant: basalt_" + i);
            }
        }
    }
    private static void registerBricksVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f);

        for (int i : VALID_BRICKS_INDICES) {
            String baseName = "bricks_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            BRICKS_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!BRICKS_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped bricks variant: bricks_" + i);
            }
        }
    }

    private static void registerCalciteVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f);

        for (int i : VALID_CALCITE_INDICES) { // Changed from VALID_BASALT_INDICES to VALID_CALCITE_INDICES
            String baseName = "calcite_" + i; // Changed to calcite_

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            CALCITE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall)); // Changed to CALCITE_VARIANTS

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!CALCITE_VARIANTS.containsKey(i)) { // Changed to CALCITE_VARIANTS
                System.err.println("⚠ Skipped calcite variant: calcite_" + i); // Changed to calcite_
            }
        }
    }

    private static void registerCDeepslateVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f); // Replaced BLACKSTONE with CDEEPSLATE

        for (int i : VALID_CDEEPSLATE_INDICES) { // Changed to CDEEPSLATE_INDICES
            String baseName = "cdeepslate_" + i; // Changed to cdeepslate_

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            CDEEPSLATE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall)); // Replaced BLACKSTONE_VARIANTS with CDEEPSLATE_VARIANTS

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!CDEEPSLATE_VARIANTS.containsKey(i)) { // Replaced BLACKSTONE_VARIANTS with CDEEPSLATE_VARIANTS
                System.err.println("⚠ Skipped cdeepslate variant: cdeepslate_" + i); // Replaced blackstone_ with cdeepslate_
            }
        }
    }
    private static void registerGraniteVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f);

        for (int i : VALID_GRANITE_INDICES) {
            String baseName = "granite_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            GRANITE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!GRANITE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped granite variant: granite_" + i);
            }
        }
    }

    private static void registerRedkeepVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f);

        for (int i : VALID_REDKEEP_INDICES) {
            String baseName = "redkeep_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            REDKEEP_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!REDKEEP_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped redkeep variant: redkeep_" + i);
            }
        }
    }

    private static void registerRsandstoneVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE).strength(3f);

        for (int i : VALID_RSANDSTONE_INDICES) {
            String baseName = "rsandstone_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            RSANDSTONE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!RSANDSTONE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped rsandstone variant: rsandstone_" + i);
            }
        }
    }

    private static void registerSandstoneVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).strength(3f);

        for (int i : VALID_SANDSTONE_INDICES) {
            String baseName = "sandstone_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            SANDSTONE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!SANDSTONE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped sandstone variant: sandstone_" + i);
            }
        }
    }

    private static void registerSstoneVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE).strength(3f);

        for (int i : VALID_SSTONE_INDICES) {
            String baseName = "sstone_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            SSTONE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!SSTONE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped sstone variant: sstone_" + i);
            }
        }

    }

    private static void registerBoneVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK).strength(3f);

        for (int i : VALID_BONE_INDICES) {
            String baseName = "bone_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            BONE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!BONE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped bone variant: bone_" + i);
            }
        }
    }

    private static void registerDripstoneVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).strength(3f);

        for (int i : VALID_DRIPSTONE_INDICES) {
            String baseName = "dripstone_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            DRIPSTONE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!DRIPSTONE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped dripstone variant: dripstone_" + i);
            }
        }
    }

    private static void registerPackedIceVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(3f);

        for (int i : VALID_PACKED_ICE_INDICES) {
            String baseName = "packed_ice_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            PACKED_ICE_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!PACKED_ICE_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped packed_ice variant: packed_ice_" + i);
            }
        }
    }

    private static void registerMudBrickVariants() {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS).strength(3f);

        for (int i : VALID_MUD_BRICK_INDICES) {
            String baseName = "mud_brick_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties, true);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties, true);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties, true);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties, true);

            MUD_BRICK_VARIANTS.put(i, new BlockSet(base, stairs, slab, wall));

            System.out.println("✅ Registered: " + baseName);
        }

        // Optional sanity check
        for (int i = 1; i <= 38; i++) {
            if (!MUD_BRICK_VARIANTS.containsKey(i)) {
                System.err.println("⚠ Skipped mud_brick variant: mud_brick_" + i);
            }
        }
    }






    public record BlockSet(
            DeferredBlock<Block> base,
            DeferredBlock<Block> stairs,
            DeferredBlock<Block> slab,
            DeferredBlock<Block> wall
    ) {}

    static {
        registerBlackstoneVariants();
        registerBasaltVariants();
        registerBricksVariants();
        registerCalciteVariants();
        registerCDeepslateVariants();
        registerGraniteVariants();
        registerRedkeepVariants();
        registerRsandstoneVariants();
        registerSandstoneVariants();
        registerStoneVariants();
        registerSstoneVariants();
        registerBoneVariants();
        registerDripstoneVariants();
        registerPackedIceVariants();
        registerMudBrickVariants();
        registerAndesiteVariants();
        registerQuartzVariants();
        registerDioriteVariants();
        registerTuffVariants();

    }


    // Adding a workstation for villagers
    public static final DeferredBlock<Block> MINT_BLOCK = registerBlock("mint_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK), true);

    // MOD ORES
    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops(), true);

    public static final DeferredBlock<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK), true);

    public static final DeferredBlock<Block> YELLOW_DIAMOND_BLOCK = registerBlock("yellow_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> TRANSPARENT_DIAMOND_BLOCK = registerBlock("transparent_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> AMBER_BLOCK = registerBlock("amber_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> AMBER_ORE = registerBlock("amber_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> AMBER_DEEPSLATE_ORE = registerBlock("amber_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> AMETHYST_ORE = registerBlock("amethyst_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE), true);

    public static final DeferredBlock<Block> AMETHYST_DEEPSLATE_ORE = registerBlock("amethyst_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE), true);

    public static final DeferredBlock<Block> BLACK_DIAMOND_BLOCK = registerBlock("black_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> BLOODSTONE_BLOCK = registerBlock("bloodstone_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> BLOODSTONE_ORE = registerBlock("bloodstone_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> BLOODSTONE_DEEPSLATE_ORE = registerBlock("bloodstone_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> CARNELIAN_BLOCK = registerBlock("carnelian_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> CARNELIAN_ORE = registerBlock("carnelian_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> CARNELIAN_DEEPSLATE_ORE = registerBlock("carnelian_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> CHALCEDONY_BLOCK = registerBlock("chalcedony_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> CHALCEDONY_ORE = registerBlock("chalcedony_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> CHALCEDONY_DEEPSLATE_ORE = registerBlock("chalcedony_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> DIAMONDS_ORE = registerBlock("diamonds_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> DIAMONDS_DEEPSLATE_ORE = registerBlock("diamonds_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> GARNET_BLOCK = registerBlock("garnet_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> GARNET_ORE = registerBlock("garnet_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> GARNET_DEEPSLATE_ORE = registerBlock("garnet_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> JADE_BLOCK = registerBlock("jade_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> JADE_ORE = registerBlock("jade_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> JADE_DEEPSLATE_ORE = registerBlock("jade_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> JASPER_BLOCK = registerBlock("jasper_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> JASPER_ORE = registerBlock("jasper_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> JASPER_DEEPSLATE_ORE = registerBlock("jasper_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> MALACHITE_BLOCK = registerBlock("malachite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> MALACHITE_ORE = registerBlock("malachite_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> MALACHITE_DEEPSLATE_ORE = registerBlock("malachite_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> MOONSTONE_BLOCK = registerBlock("moonstone_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> MOONSTONE_ORE = registerBlock("moonstone_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> MOONSTONE_DEEPSLATE_ORE = registerBlock("moonstone_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> ONYX_BLOCK = registerBlock("onyx_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> ONYX_ORE = registerBlock("onyx_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> ONYX_DEEPSLATE_ORE = registerBlock("onyx_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> OPAL_BLOCK = registerBlock("opal_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> OPAL_ORE = registerBlock("opal_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> OPAL_DEEPSLATE_ORE = registerBlock("opal_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> RUBY_DEEPSLATE_ORE = registerBlock("ruby_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> SAPPHIRE_DEEPSLATE_ORE = registerBlock("sapphire_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> TIGERS_EYE_BLOCK = registerBlock("tigers_eye_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> TIGERS_EYE_ORE = registerBlock("tigers_eye_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> TIGERS_EYE_DEEPSLATE_ORE = registerBlock("tigers_eye_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> TOPAZ_BLOCK = registerBlock("topaz_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK), true);

    public static final DeferredBlock<Block> TOPAZ_ORE = registerBlock("topaz_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> TOPAZ_DEEPSLATE_ORE = registerBlock("topaz_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> TOURMALINE_ORE = registerBlock("tourmaline_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE), true);

    public static final DeferredBlock<Block> TOURMALINE_DEEPSLATE_ORE = registerBlock("tourmaline_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE), true);

    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops(), true);

    // TIN BLOCK
    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK), true);

    // BRONZE BLOCK
    public static final DeferredBlock<Block> BRONZE_BLOCK = registerBlock("bronze_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK), true);

    // DARK STONE BRICK
    public static final DeferredBlock<Block> DARK_STONE_BRICK = registerBlock("dark_stone_brick", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS), true);

    // KINGS LANDING BRICK LARGE
    public static final DeferredBlock<Block> KINGS_LANDING_BRICK_LARGE = registerBlock("kings_landing_brick_large", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS), true);

    // ROUNDED STONE BRICK
    public static final DeferredBlock<Block> STONE_BRICK_BUT_COOLER = registerBlock("stone_brick_but_cooler", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS), true);


    public static final DeferredBlock<Block> QUAGMIRE = registerBlock("quagmire",
            properties -> new QuagmireBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)
                    .sound(SoundType.MUD)
                    .friction(0.8F)
                    .speedFactor(0.1F)
                    .strength(6f)
                    .noOcclusion()
                    .isValidSpawn((state, level, pos, type) -> false)
                    .pushReaction(PushReaction.BLOCK), true);



    // ---------------------------(BLOCKS)--------------------------- //

    // ---------------------------(TREE BLOCKS)--------------------------- //
    //Weirwood
    public static final DeferredBlock<Block> WEIRWOOD_LOG = registerBlock("weirwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> WEIRWOOD_FACE_LOG = registerBlock("weirwood_face_log",
            WeirwoodFaceLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN), true);
    public static final DeferredBlock<Block> WEIRWOOD_WOOD = registerBlock("weirwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_WEIRWOOD_LOG = registerBlock("stripped_weirwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_WEIRWOOD_WOOD = registerBlock("stripped_weirwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> WEIRWOOD_PLANKS = registerBlock("weirwood_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> WEIRWOOD_LEAVES = registerBlock("weirwood_leaves", WeirwoodLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES), true);
    public static final DeferredBlock<Block> WEIRWOOD_SAPLING = registerBlock("weirwood_sapling", properties -> new SaplingBlock(ModTreeGrower.WEIRWOOD, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> WEIRWOOD_STAIRS = registerBlock("weirwood_stairs", properties -> new StairBlock(ModBLocks.WEIRWOOD_PLANKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> WEIRWOOD_SLAB = registerBlock("weirwood_slabs", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> WEIRWOOD_BUTTON = registerBlock("weirwood_button", properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> WEIRWOOD_PRESSURE_PLATE = registerBlock("weirwood_pressure_plate", properties -> new PressurePlateBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> WEIRWOOD_FENCE = registerBlock("weirwood_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> WEIRWOOD_FENCE_GATE = registerBlock("weirwood_fence_gate", properties -> new FenceGateBlock(properties, SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> WEIRWOOD_WALL = registerBlock("weirwood_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> WEIRWOOD_DOOR = registerBlock("weirwood_door", properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> WEIRWOOD_TRAPDOOR = registerBlock("weirwood_trapdoor", properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);


    //Sycamore
    public static final DeferredBlock<Block> SYCAMORE_LOG = registerBlock("sycamore_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> SYCAMORE_WOOD = registerBlock("sycamore_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_SYCAMORE_LOG = registerBlock("sycamore_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_SYCAMORE_WOOD = registerBlock("sycamore_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> SYCAMORE_PLANKS = registerBlock("sycamore_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> SYCAMORE_LEAVES = registerBlock("sycamore_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> SYCAMORE_SAPLING = registerBlock("sycamore_sapling", properties -> new SaplingBlock(ModTreeGrower.SYSCAMORE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> SYCAMORE_STAIRS = registerBlock("sycamore_stairs",
            properties -> new StairBlock(ModBLocks.SYCAMORE_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SYCAMORE_SLAB = registerBlock("sycamore_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SYCAMORE_BUTTON = registerBlock("sycamore_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SYCAMORE_PRESSURE_PLATE = registerBlock("sycamore_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SYCAMORE_FENCE = registerBlock("sycamore_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> SYCAMORE_FENCE_GATE = registerBlock("sycamore_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> SYCAMORE_WALL = registerBlock("sycamore_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> SYCAMORE_DOOR = registerBlock("sycamore_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> SYCAMORE_TRAPDOOR = registerBlock("sycamore_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);


    //Sentinel
    public static final DeferredBlock<Block> SENTINEL_LOG = registerBlock("sentinel_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> SENTINEL_WOOD = registerBlock("sentinel_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_SENTINEL_LOG = registerBlock("sentinel_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_SENTINEL_WOOD = registerBlock("sentinel_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> SENTINEL_PLANKS = registerBlock("sentinel_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> SENTINEL_LEAVES = registerBlock("sentinel_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> SENTINEL_SAPLING = registerBlock("sentinel_sapling", properties -> new SaplingBlock(ModTreeGrower.SENTINEL, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> SENTINEL_STAIRS = registerBlock("sentinel_stairs",
            properties -> new StairBlock(ModBLocks.SENTINEL_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SENTINEL_SLAB = registerBlock("sentinel_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SENTINEL_BUTTON = registerBlock("sentinel_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SENTINEL_PRESSURE_PLATE = registerBlock("sentinel_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> SENTINEL_FENCE = registerBlock("sentinel_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> SENTINEL_FENCE_GATE = registerBlock("sentinel_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> SENTINEL_WALL = registerBlock("sentinel_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> SENTINEL_DOOR = registerBlock("sentinel_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> SENTINEL_TRAPDOOR = registerBlock("sentinel_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);


    //Pine
    public static final DeferredBlock<Block> PINE_LOG = registerBlock("pine_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG).strength(3f), true);
    public static final DeferredBlock<Block> PINE_WOOD = registerBlock("pine_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_PINE_LOG = registerBlock("pine_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_PINE_WOOD = registerBlock("pine_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> PINE_PLANKS = registerBlock("pine_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS), true);
    public static final DeferredBlock<Block> PINE_LEAVES = registerBlock("pine_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES), true);
    public static final DeferredBlock<Block> PINE_SAPLING = registerBlock("pine_sapling", properties -> new SaplingBlock(ModTreeGrower.PINE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING), true);
    public static final DeferredBlock<Block> PINE_STAIRS = registerBlock("pine_stairs",
            properties -> new StairBlock(ModBLocks.PINE_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS), true);
    public static final DeferredBlock<Block> PINE_SLAB = registerBlock("pine_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS), true);
    public static final DeferredBlock<Block> PINE_BUTTON = registerBlock("pine_button",
            properties -> new ButtonBlock(BlockSetType.SPRUCE, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS), true);
    public static final DeferredBlock<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.SPRUCE,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS), true);
    public static final DeferredBlock<Block> PINE_FENCE = registerBlock("pine_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE), true);
    public static final DeferredBlock<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE), true);
    public static final DeferredBlock<Block> PINE_WALL = registerBlock("pine_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE), true);
    public static final DeferredBlock<Block> PINE_DOOR = registerBlock("pine_door",
            properties -> new DoorBlock(BlockSetType.SPRUCE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR), true);
    public static final DeferredBlock<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.SPRUCE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR), true);



    //ironwood
    public static final DeferredBlock<Block> IRONWOOD_LOG = registerBlock("ironwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> IRONWOOD_WOOD = registerBlock("ironwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_IRONWOOD_LOG = registerBlock("ironwood_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_IRONWOOD_WOOD = registerBlock("ironwood_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> IRONWOOD_PLANKS = registerBlock("ironwood_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> IRONWOOD_LEAVES = registerBlock("ironwood_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> IRONWOOD_SAPLING = registerBlock("ironwood_sapling", properties -> new SaplingBlock(ModTreeGrower.IRONWOOD, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> IRONWOOD_STAIRS = registerBlock("ironwood_stairs",
            properties -> new StairBlock(ModBLocks.IRONWOOD_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> IRONWOOD_SLAB = registerBlock("ironwood_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> IRONWOOD_BUTTON = registerBlock("ironwood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> IRONWOOD_PRESSURE_PLATE = registerBlock("ironwood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> IRONWOOD_FENCE = registerBlock("ironwood_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> IRONWOOD_FENCE_GATE = registerBlock("ironwood_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> IRONWOOD_WALL = registerBlock("ironwood_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> IRONWOOD_DOOR = registerBlock("ironwood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> IRONWOOD_TRAPDOOR = registerBlock("ironwood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);


    //hawthorn
    public static final DeferredBlock<Block> HAWTHORN_LOG = registerBlock("hawthorn_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> HAWTHORN_WOOD = registerBlock("hawthorn_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_HAWTHORN_LOG = registerBlock("hawthorn_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_HAWTHORN_WOOD = registerBlock("hawthorn_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> HAWTHORN_PLANKS = registerBlock("hawthorn_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> HAWTHORN_LEAVES = registerBlock("hawthorn_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling", properties -> new SaplingBlock(ModTreeGrower.HAWTHORN, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> HAWTHORN_STAIRS = registerBlock("hawthorn_stairs",
            properties -> new StairBlock(ModBLocks.HAWTHORN_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> HAWTHORN_SLAB = registerBlock("hawthorn_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> HAWTHORN_BUTTON = registerBlock("hawthorn_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> HAWTHORN_PRESSURE_PLATE = registerBlock("hawthorn_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> HAWTHORN_FENCE = registerBlock("hawthorn_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> HAWTHORN_FENCE_GATE = registerBlock("hawthorn_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> HAWTHORN_WALL = registerBlock("hawthorn_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> HAWTHORN_DOOR = registerBlock("hawthorn_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> HAWTHORN_TRAPDOOR = registerBlock("hawthorn_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);


    //chestnut
    public static final DeferredBlock<Block> CHESTNUT_LOG = registerBlock("chestnut_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> CHESTNUT_WOOD = registerBlock("chestnut_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_LOG = registerBlock("chestnut_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_WOOD = registerBlock("chestnut_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> CHESTNUT_PLANKS = registerBlock("chestnut_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> CHESTNUT_LEAVES = registerBlock("chestnut_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> CHESTNUT_SAPLING = registerBlock("chestnut_sapling", properties -> new SaplingBlock(ModTreeGrower.CHESTNUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> CHESTNUT_STAIRS = registerBlock("chestnut_stairs",
            properties -> new StairBlock(ModBLocks.CHESTNUT_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHESTNUT_SLAB = registerBlock("chestnut_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHESTNUT_BUTTON = registerBlock("chestnut_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHESTNUT_PRESSURE_PLATE = registerBlock("chestnut_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHESTNUT_FENCE = registerBlock("chestnut_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CHESTNUT_FENCE_GATE = registerBlock("chestnut_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CHESTNUT_WALL = registerBlock("chestnut_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CHESTNUT_DOOR = registerBlock("chestnut_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> CHESTNUT_TRAPDOOR = registerBlock("chestnut_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);



    //Cedar
    public static final DeferredBlock<Block> CEDAR_LOG = registerBlock("cedar_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> CEDAR_WOOD = registerBlock("cedar_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_CEDAR_LOG = registerBlock("cedar_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_CEDAR_WOOD = registerBlock("cedar_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> CEDAR_PLANKS = registerBlock("cedar_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> CEDAR_LEAVES = registerBlock("cedar_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> CEDAR_SAPLING = registerBlock("cedar_sapling", properties -> new SaplingBlock(ModTreeGrower.CEDAR, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> CEDAR_STAIRS = registerBlock("cedar_stairs",
            properties -> new StairBlock(ModBLocks.CEDAR_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CEDAR_SLAB = registerBlock("cedar_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CEDAR_BUTTON = registerBlock("cedar_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CEDAR_PRESSURE_PLATE = registerBlock("cedar_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CEDAR_FENCE = registerBlock("cedar_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CEDAR_FENCE_GATE = registerBlock("cedar_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CEDAR_WALL = registerBlock("cedar_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CEDAR_DOOR = registerBlock("cedar_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> CEDAR_TRAPDOOR = registerBlock("cedar_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);



    //Beech
    public static final DeferredBlock<Block> BEECH_LOG = registerBlock("beech_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> BEECH_WOOD = registerBlock("beech_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_BEECH_LOG = registerBlock("beech_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_BEECH_WOOD = registerBlock("beech_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> BEECH_PLANKS = registerBlock("beech_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> BEECH_LEAVES = registerBlock("beech_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> BEECH_SAPLING = registerBlock("beech_sapling", properties -> new SaplingBlock(ModTreeGrower.BEECH, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> BEECH_STAIRS = registerBlock("beech_stairs",
            properties -> new StairBlock(ModBLocks.BEECH_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BEECH_SLAB = registerBlock("beech_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BEECH_BUTTON = registerBlock("beech_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BEECH_PRESSURE_PLATE = registerBlock("beech_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BEECH_FENCE = registerBlock("beech_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> BEECH_FENCE_GATE = registerBlock("beech_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> BEECH_WALL = registerBlock("beech_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> BEECH_DOOR = registerBlock("beech_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> BEECH_TRAPDOOR = registerBlock("beech_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);



    //Ash
    public static final DeferredBlock<Block> ASH_LOG = registerBlock("ash_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> ASH_WOOD = registerBlock("ash_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ASH_LOG = registerBlock("ash_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ASH_WOOD = registerBlock("ash_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> ASH_PLANKS = registerBlock("ash_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> ASH_LEAVES = registerBlock("ash_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> ASH_SAPLING = registerBlock("ash_sapling", properties -> new SaplingBlock(ModTreeGrower.ASH, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> ASH_STAIRS = registerBlock("ash_stairs",
            properties -> new StairBlock(ModBLocks.ASH_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASH_SLAB = registerBlock("ash_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASH_BUTTON = registerBlock("ash_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASH_PRESSURE_PLATE = registerBlock("ash_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASH_FENCE = registerBlock("ash_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ASH_FENCE_GATE = registerBlock("ash_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ASH_WALL = registerBlock("ash_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ASH_DOOR = registerBlock("ash_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> ASH_TRAPDOOR = registerBlock("ash_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);


    //Blackbark
    public static final DeferredBlock<Block> BLACKBARK_LOG = registerBlock("blackbark_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> BLACKBARK_WOOD = registerBlock("blackbark_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_BLACKBARK_LOG = registerBlock("blackbark_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_BLACKBARK_WOOD = registerBlock("blackbark_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> BLACKBARK_PLANKS = registerBlock("blackbark_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> BLACKBARK_LEAVES = registerBlock("blackbark_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> BLACKBARK_SAPLING = registerBlock("blackbark_sapling", properties -> new SaplingBlock(ModTreeGrower.BLACKBARK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> BLACKBARK_STAIRS = registerBlock("blackbark_stairs",
            properties -> new StairBlock(ModBLocks.BLACKBARK_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BLACKBARK_SLAB = registerBlock("blackbark_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BLACKBARK_BUTTON = registerBlock("blackbark_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BLACKBARK_PRESSURE_PLATE = registerBlock("blackbark_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> BLACKBARK_FENCE = registerBlock("blackbark_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> BLACKBARK_FENCE_GATE = registerBlock("blackbark_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> BLACKBARK_WALL = registerBlock("blackbark_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> BLACKBARK_DOOR = registerBlock("blackbark_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> BLACKBARK_TRAPDOOR = registerBlock("blackbark_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);



    //Aspen
    public static final DeferredBlock<Block> ASPEN_LOG = registerBlock("aspen_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> ASPEN_WOOD = registerBlock("aspen_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ASPEN_LOG = registerBlock("aspen_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ASPEN_WOOD = registerBlock("aspen_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> ASPEN_PLANKS = registerBlock("aspen_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> ASPEN_LEAVES = registerBlock("aspen_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> ASPEN_SAPLING = registerBlock("aspen_sapling", properties -> new SaplingBlock(ModTreeGrower.ASPEN, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> ASPEN_STAIRS = registerBlock("aspen_stairs",
            properties -> new StairBlock(ModBLocks.ASPEN_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASPEN_SLAB = registerBlock("aspen_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASPEN_BUTTON = registerBlock("aspen_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASPEN_PRESSURE_PLATE = registerBlock("aspen_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ASPEN_FENCE = registerBlock("aspen_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ASPEN_FENCE_GATE = registerBlock("aspen_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ASPEN_WALL = registerBlock("aspen_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ASPEN_DOOR = registerBlock("aspen_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> ASPEN_TRAPDOOR = registerBlock("aspen_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);



    //Alder
    public static final DeferredBlock<Block> ALDER_LOG = registerBlock("alder_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> ALDER_WOOD = registerBlock("alder_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ALDER_LOG = registerBlock("alder_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ALDER_WOOD = registerBlock("alder_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> ALDER_PLANKS = registerBlock("alder_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> ALDER_LEAVES = registerBlock("alder_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES), true);
    public static final DeferredBlock<Block> ALDER_SAPLING = registerBlock("alder_sapling", properties -> new SaplingBlock(ModTreeGrower.ALDER, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING), true);
    public static final DeferredBlock<Block> ALDER_STAIRS = registerBlock("alder_stairs",
            properties -> new StairBlock(ModBLocks.ALDER_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ALDER_SLAB = registerBlock("alder_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ALDER_BUTTON = registerBlock("alder_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ALDER_PRESSURE_PLATE = registerBlock("alder_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ALDER_FENCE = registerBlock("alder_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ALDER_FENCE_GATE = registerBlock("alder_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ALDER_WALL = registerBlock("alder_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ALDER_DOOR = registerBlock("alder_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> ALDER_TRAPDOOR = registerBlock("alder_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);



    // ---------------------------(SIGNS)--------------------------- //
    public static final DeferredBlock<Block> WEIRWOOD_SIGN = registerBlock("weirwood_sign",
            properties -> new ModStandingSignBlock(ModWoodTypes.WEIRWOOD, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);
    
    public static final DeferredBlock<Block> WEIRWOOD_WALL_SIGN = registerBlock("weirwood_wall_sign",
            properties -> new ModWallSignBlock(ModWoodTypes.WEIRWOOD, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                    .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);
    
    public static final DeferredBlock<Block> WEIRWOOD_HANGING_SIGN = registerBlock("weirwood_hanging_sign",
            properties -> new ModHangingSignBlock(ModWoodTypes.WEIRWOOD, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                    .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);
    
    public static final DeferredBlock<Block> WEIRWOOD_WALL_HANGING_SIGN = registerBlock("weirwood_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodTypes.WEIRWOOD, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                    .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);
    
    public static final DeferredBlock<Block> PINE_SIGN = registerBlock("pine_sign",
            properties -> new ModStandingSignBlock(ModWoodTypes.PINE, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                    .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);
    
    public static final DeferredBlock<Block> PINE_WALL_SIGN = registerBlock("pine_wall_sign",
            properties -> new ModWallSignBlock(ModWoodTypes.PINE, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                    .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);
    
    public static final DeferredBlock<Block> PINE_HANGING_SIGN = registerBlock("pine_hanging_sign",
            properties -> new ModHangingSignBlock(ModWoodTypes.PINE, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                    .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);
    
    public static final DeferredBlock<Block> PINE_WALL_HANGING_SIGN = registerBlock("pine_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodTypes.PINE, properties),
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                    .noCollission()
                .strength(1.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava(), 
            false);


    // ---------------------------(STONE)--------------------------- //

    public static final DeferredBlock<Block> REDKEEP_STONE_BLOCK = registerBlock("redkeep_stone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f), true);
    public static final DeferredBlock<Block> REDKEEP_STONE_STAIRS = registerBlock("redkeep_stone_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_STONE_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS), true);
    public static final DeferredBlock<Block> REDKEEP_STONE_SLAB = registerBlock("redkeep_stone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB), true);
    public static final DeferredBlock<Block> REDKEEP_STONE_WALL = registerBlock("redkeep_stone_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL), true);

    // ---------------------------(FLOWERS)--------------------------- //
    public static final DeferredBlock<Block> WINTER_ROSE = registerBlock("winter_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> WILD_RADISH = registerBlock("wild_radish",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> WHITE_ROSE = registerBlock("white_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> THORN_BUSH = registerBlock("thorn_bush",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), true);

    public static final DeferredBlock<Block> THISTLE = registerBlock("thistle",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> TANSY = registerBlock("tansy",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> SPICEFLOWER = registerBlock("spiceflower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> SEDGE = registerBlock("sedge",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> SAFFRON_CROCUS = registerBlock("saffron_crocus",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> ROSE = registerBlock("rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> POISON_KISSES = registerBlock("poison_kisses",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> PENNYROYAL = registerBlock("pennyroyal",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> OPIUM_POPPY_WILD = registerBlock("opium_poppy_wild",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> NIGHTSHADE = registerBlock("nightshade",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> MOONBLOOM = registerBlock("moonbloom",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> LUNGWORT = registerBlock("lungwort",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> LIVERWORT = registerBlock("liverwort",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> LAVENDER = registerBlock("lavender",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);


    public static final DeferredBlock<Block> LADYS_LACE = registerBlock("ladys_lace",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> GORSE = registerBlock("gorse",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> GOLDENROD = registerBlock("goldenrod",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> GOLDENCUP = registerBlock("goldencup",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> GOATHEAD = registerBlock("goathead",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> GINGER = registerBlock("ginger",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> GILLYFLOWER = registerBlock("gillyflower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> FROSTFIRE = registerBlock("frostfire",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> FORGET_ME_NOT = registerBlock("forget_me_not",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> EVENING_STAR = registerBlock("evening_star",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> DUSKY_ROSE = registerBlock("dusky_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> DRAGONS_BREATH = registerBlock("dragons_breath",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> COLDSNAP = registerBlock("coldsnap",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> BLUE_ROSE = registerBlock("blue_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> BLOODBLOOM = registerBlock("bloodbloom",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> BLACK_LOTUS = registerBlock("black_lotus",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY), true);

    public static final DeferredBlock<Block> WINTER_ROSE_BUSH = registerBlock("winter_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH), true);

    public static final DeferredBlock<Block> WHITE_ROSE_BUSH = registerBlock("white_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH), true);

    public static final DeferredBlock<Block> DUSKY_ROSE_BUSH = registerBlock("dusky_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH), true);

    public static final DeferredBlock<Block> BLUE_ROSE_BUSH = registerBlock("blue_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH), true);

    public static final DeferredBlock<Block> RED_ROSE_BUSH = registerBlock("red_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH), true);





    // ---------------------------(POTTED PLANTS)--------------------------- //

    public static final DeferredBlock<Block> POTTED_WINTER_ROSE = registerBlock("potted_winter_rose",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> WINTER_ROSE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_WILD_RADISH = registerBlock("potted_wild_radish",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> WILD_RADISH.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_WHITE_ROSE = registerBlock("potted_white_rose",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> WHITE_ROSE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_THORN_BUSH = registerBlock("potted_thorn_bush",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> THORN_BUSH.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_THISTLE = registerBlock("potted_thistle",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> THISTLE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_TANSY = registerBlock("potted_tansy",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> TANSY.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_SPICEFLOWER = registerBlock("potted_spiceflower",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> SPICEFLOWER.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_SEDGE = registerBlock("potted_sedge",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> SEDGE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_SAFFRON_CROCUS = registerBlock("potted_saffron_crocus",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> SAFFRON_CROCUS.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_ROSE = registerBlock("potted_rose",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> ROSE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_POISON_KISSES = registerBlock("potted_poison_kisses",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> POISON_KISSES.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_PENNYROYAL = registerBlock("potted_pennyroyal",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> PENNYROYAL.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_OPIUM_POPPY_WILD = registerBlock("potted_opium_poppy_wild",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> OPIUM_POPPY_WILD.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_NIGHTSHADE = registerBlock("potted_nightshade",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> NIGHTSHADE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_MOONBLOOM = registerBlock("potted_moonbloom",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> MOONBLOOM.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_LUNGWORT = registerBlock("potted_lungwort",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> LUNGWORT.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_LIVERWORT = registerBlock("potted_liverwort",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> LIVERWORT.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_LAVENDER = registerBlock("potted_lavender",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> LAVENDER.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_LADYS_LACE = registerBlock("potted_ladys_lace",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> LADYS_LACE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);


    public static final DeferredBlock<Block> POTTED_GORSE = registerBlock("potted_gorse",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> GORSE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_GOLDENROD = registerBlock("potted_goldenrod",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> GOLDENROD.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_GOLDENCUP = registerBlock("potted_goldencup",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> GOLDENCUP.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_GOATHEAD = registerBlock("potted_goathead",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> GOATHEAD.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_GINGER = registerBlock("potted_ginger",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> GINGER.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_GILLYFLOWER = registerBlock("potted_gillyflower",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> GILLYFLOWER.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_FROSTFIRE = registerBlock("potted_frostfire",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> FROSTFIRE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_FORGET_ME_NOT = registerBlock("potted_forget_me_not",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> FORGET_ME_NOT.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_EVENING_STAR = registerBlock("potted_evening_star",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> EVENING_STAR.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_DUSKY_ROSE = registerBlock("potted_dusky_rose",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> DUSKY_ROSE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_DRAGONS_BREATH = registerBlock("potted_dragons_breath",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> DRAGONS_BREATH.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_COLDSNAP = registerBlock("potted_coldsnap",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> COLDSNAP.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_BLUE_ROSE = registerBlock("potted_blue_rose",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> BLUE_ROSE.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);


    public static final DeferredBlock<Block> POTTED_BLOODBLOOM = registerBlock("potted_bloodbloom",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> BLOODBLOOM.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    public static final DeferredBlock<Block> POTTED_BLACK_LOTUS = registerBlock("potted_black_lotus",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> BLACK_LOTUS.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion(), true);

    // ---------------------------(CROPS)--------------------------- //

    public static final DeferredBlock<Block> HORSERADISH_CROP = registerBlock("horseradish_crop",
            HorseradishCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);
    public static final DeferredBlock<Block> BARLEY_CROP = registerBlock("barley_crop",
            BarleyCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT), false);
    public static final DeferredBlock<Block> GARLIC_CROP = registerBlock("garlic_crop",
            GarlicCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> LEEK_CROP = registerBlock("leek_crop",
            LeekCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> NEEP_CROP = registerBlock("neep_crop",
            NeepCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> OAT_CROP = registerBlock("oat_crop",
            OatCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT), false);

    public static final DeferredBlock<Block> PARSLEY_CROP = registerBlock("parsley_crop",
            ParsleyCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> RED_ONION_CROP = registerBlock("red_onion_crop",
            RedOnionCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> TURNIP_CROP = registerBlock("turnip_crop",
            TurnipCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> WILD_ONION_CROP = registerBlock("wild_onion_crop",
            WildOnionCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> ONION_CROP = registerBlock("onion_crop",
            OnionCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> OPIUM_POPPY_CROP = registerBlock("opium_poppy_crop",
            OpiumPoppyCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> CABBAGE_CROP = registerBlock("cabbage_crop",
            CabbageCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> BEAN_CROP = registerBlock("bean_crop",
            BeanCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> CHICKPEA_CROP = registerBlock("chickpea_crop",
            ChickpeaCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> CUCUMBER_CROP = registerBlock("cucumber_crop",
            CucumberCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> GREEN_BEAN_CROP = registerBlock("green_bean_crop",
            GreenBeanCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> SPINACH_CROP = registerBlock("spinach_crop",
            SpinachCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> DRAGON_PEPPER_CROP = registerBlock("dragon_pepper_crop",
            DragonPepperCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> PEPPER_CROP = registerBlock("pepper_crop",
            PepperCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> PEPPERCORN_CROP = registerBlock("peppercorn_crop",
            PeppercornCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> COTTON_CROP = registerBlock("cotton_crop",
            CottonCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> HEMP_CROP = registerBlock("hemp_crop",
            HempCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BEETROOTS), false);

    public static final DeferredBlock<Block> CORN_CROP = registerBlock("corn_crop",
            CornCropBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).noCollission().noOcclusion(), false);
    public static final DeferredBlock<Block> CORN_CROP_MIDDLE = registerBlock("corn_crop_middle",
            CornCropMiddleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).noCollission().noOcclusion(), false);
    public static final DeferredBlock<Block> CORN_CROP_TOP = registerBlock("corn_crop_top",
            CornCropTopBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).noCollission().noOcclusion(), false);
    public static final DeferredBlock<Block> GHOST_GRASS = registerBlock("ghost_grass",
            GhostGrassBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).noOcclusion(), true);
    
    public static final DeferredBlock<Block> GHOST_GRASS_MIDDLE = registerBlock("ghost_grass_middle",
            GhostGrassMiddleBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).noOcclusion(), true);
    
    public static final DeferredBlock<Block> GHOST_GRASS_TOP = registerBlock("ghost_grass_top",
            GhostGrassTopBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SUGAR_CANE).noOcclusion(), true);

    public static final DeferredBlock<Block> GHOST_GRASS_BLOCK = registerBlock("ghost_grass_block",
            WeirwoodFaceLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN).sound(SoundType.GRASS), true);



    // ---------------------------(BUSHES)--------------------------- //

    public static final DeferredBlock<Block> STRAWBERRY_BUSH = registerBlock("strawberry_bush",
            StrawberryBushBlock:: new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), false);

    public static final DeferredBlock<Block> BLACKBERRY_BUSH = registerBlock("blackberry_bush",
            BlackberryBushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), false);

    public static final DeferredBlock<Block> BLUEBERRY_BUSH = registerBlock("blueberry_bush",
            BlueberryBushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), false);

    public static final DeferredBlock<Block> MULBERRY_BUSH = registerBlock("mulberry_bush",
            MulberryBushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), false);

    public static final DeferredBlock<Block> RASPBERRY_BUSH = registerBlock("raspberry_bush",
            RaspberryBushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), false);

    public static final DeferredBlock<Block> SMOKEBERRY_BUSH = registerBlock("smokeberry_bush",
            SmokeberryBushBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH), false);





    // Tells the AGoTMod class to call the modded blocks into the game
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
