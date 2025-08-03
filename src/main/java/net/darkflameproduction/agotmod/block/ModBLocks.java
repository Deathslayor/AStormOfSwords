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
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
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

import java.lang.reflect.Field;
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
    public static final DeferredBlock<Block> SILVER_ORE = registerBlock("silver_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops(), true);
    public static final DeferredBlock<Block> RAW_SILVER_BLOCK = registerBlock("raw_silver_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK), true);
    public static final DeferredBlock<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops(), true);
    public static final DeferredBlock<Block> SILVER_BLOCK = registerBlock("silver_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK), true);

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


    public static final Map<String, DeferredBlock<Block>> LOGS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> WOODS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> STRIPPED_LOGS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> STRIPPED_WOODS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> PLANKS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> LEAVES = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> SAPLINGS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> STAIRS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> SLABS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> BUTTONS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> PRESSURE_PLATES = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> FENCES = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> FENCE_GATES = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> WALLS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> DOORS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> TRAPDOORS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> SIGNS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> WALL_SIGNS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> HANGING_SIGNS = new HashMap<>();
    public static final Map<String, DeferredBlock<Block>> WALL_HANGING_SIGNS = new HashMap<>();

    // Array of wood types (excluding weirwood)
    private static final String[] WOOD_TYPES = {
            "sycamore",
            "pine",
            "ash",
            "beech",
            "cedar",
            "chestnut",
            "hawthorn",
            "ironwood",
            "sentinel",
            "blackbark",
            "aspen",
            "black_cherry",
            "black_olive",
            "crabapple",
            "olive",
            "white_cherry",
            "red_cherry",
            "fir",
            "willow",
            "wormtree",
            "alder",
            "almond",
            "apple",
            "apricot",
            "baobab",
            "black_cottonwood",
            "blackthorn",
            "blood_orange",
            "bloodwood",
            "blue_mahoe",
            "cottonwood",
            "datepalm",
            "ebony",
            "fig",
            "fireplum",
            "goldenheart",
            "lemon",
            "lime",
            "linden",
            "mahogany",
            "maple",
            "myrrh",
            "nightwood",
            "nutmeg",
            "orange",
            "peach",
            "pear",
            "pecan",
            "persimmon",
            "pink_ivory",
            "plum",
            "pomegranate",
            "purpleheart",
            "redwood",
            "sandalwood",
            "sandbeggar",
            "tigerwood",
            "yew"
    };

    static {
        // Generate all blocks for each wood type
        for (String woodType : WOOD_TYPES) {
            registerWoodBlocks(woodType);
        }
    }

    private static void registerWoodBlocks(String woodType) {
        // Register log and wood blocks
        LOGS.put(woodType, registerBlock(woodType + "_log",
                ModFlammableRotatedPillarBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f),
                true));

        WOODS.put(woodType, registerBlock(woodType + "_wood",
                ModFlammableRotatedPillarBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f),
                true));

        STRIPPED_LOGS.put(woodType, registerBlock(woodType + "_log_stripped",
                ModFlammableRotatedPillarBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f),
                true));

        STRIPPED_WOODS.put(woodType, registerBlock(woodType + "_wood_stripped",
                ModFlammableRotatedPillarBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f),
                true));

        // Register planks and related blocks
        PLANKS.put(woodType, registerBlock(woodType + "_planks",
                ModFlammablePlanks::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS),
                true));

        LEAVES.put(woodType, registerBlock(woodType + "_leaves",
                ModFlammableLeaves::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES),
                true));

        // Get the appropriate tree grower for this wood type
        TreeGrower treeGrower = getTreeGrowerForWoodType(woodType);

        SAPLINGS.put(woodType, registerBlock(woodType + "_sapling",
                properties -> new SaplingBlock(treeGrower, properties),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING),
                true));

        // Register stairs, slabs, and other decorative blocks
        STAIRS.put(woodType, registerBlock(woodType + "_stairs",
                properties -> new StairBlock(PLANKS.get(woodType).get().defaultBlockState(), properties),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS),
                true));

        SLABS.put(woodType, registerBlock(woodType + "_slabs",
                SlabBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS),
                true));

        BUTTONS.put(woodType, registerBlock(woodType + "_button",
                properties -> new ButtonBlock(BlockSetType.OAK, 10, properties),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS),
                true));

        PRESSURE_PLATES.put(woodType, registerBlock(woodType + "_pressure_plate",
                properties -> new PressurePlateBlock(BlockSetType.OAK, properties),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS),
                true));

        FENCES.put(woodType, registerBlock(woodType + "_fence",
                FenceBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                true));

        FENCE_GATES.put(woodType, registerBlock(woodType + "_fence_gate",
                properties -> new FenceGateBlock(properties, SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                true));

        WALLS.put(woodType, registerBlock(woodType + "_wall",
                WallBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                true));

        DOORS.put(woodType, registerBlock(woodType + "_door",
                properties -> new DoorBlock(BlockSetType.OAK, properties),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR),
                true));

        TRAPDOORS.put(woodType, registerBlock(woodType + "_trapdoor",
                properties -> new TrapDoorBlock(BlockSetType.OAK, properties),
                BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR),
                true));

        // Get the appropriate WoodType for this wood type
        WoodType woodTypeObj = getWoodTypeForWoodType(woodType);

        // Register sign blocks
        SIGNS.put(woodType, registerBlock(woodType + "_sign",
                properties -> new ModStandingSignBlock(woodTypeObj, properties),
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.WOOD)
                        .noCollission()
                        .strength(1.0F)
                        .sound(SoundType.WOOD)
                        .ignitedByLava(),
                false));

        WALL_SIGNS.put(woodType, registerBlock(woodType + "_wall_sign",
                properties -> new ModWallSignBlock(woodTypeObj, properties),
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.WOOD)
                        .noCollission()
                        .strength(1.0F)
                        .sound(SoundType.WOOD)
                        .ignitedByLava(),
                false));

        HANGING_SIGNS.put(woodType, registerBlock(woodType + "_hanging_sign",
                properties -> new ModHangingSignBlock(woodTypeObj, properties),
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.WOOD)
                        .noCollission()
                        .strength(1.0F)
                        .sound(SoundType.WOOD)
                        .ignitedByLava(),
                false));

        WALL_HANGING_SIGNS.put(woodType, registerBlock(woodType + "_wall_hanging_sign",
                properties -> new ModWallHangingSignBlock(woodTypeObj, properties),
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.WOOD)
                        .noCollission()
                        .strength(1.0F)
                        .sound(SoundType.WOOD)
                        .ignitedByLava(),
                false));
    }

    // Helper method to get the appropriate TreeGrower for a wood type
    private static TreeGrower getTreeGrowerForWoodType(String woodType) {
        // This uses your existing ModTreeGrower static fields
        switch (woodType) {
            case "sycamore":
                return ModTreeGrower.SYSCAMORE;
            case "pine":
                return ModTreeGrower.PINE;
            case "ash":
                return ModTreeGrower.ASH;
            case "beech":
                return ModTreeGrower.BEECH;
            case "cedar":
                return ModTreeGrower.CEDAR;
            case "chestnut":
                return ModTreeGrower.CHESTNUT;
            case "hawthorn":
                return ModTreeGrower.HAWTHORN;
            case "ironwood":
                return ModTreeGrower.IRONWOOD;
            case "sentinel":
                return ModTreeGrower.SENTINEL;
            case "blackbark":
                return ModTreeGrower.BLACKBARK;
            case "aspen":
                return ModTreeGrower.ASPEN;
            case "black_cherry":
                return ModTreeGrower.BLACK_CHERRY;
            case "black_olive":
                return ModTreeGrower.BLACK_OLIVE;
            case "crabapple":
                return ModTreeGrower.CRABAPPLE;
            case "olive":
                return ModTreeGrower.OLIVE;
            case "white_cherry":
                return ModTreeGrower.WHITE_CHERRY;
            case "red_cherry":
                return ModTreeGrower.RED_CHERRY;
            case "fir":
                return ModTreeGrower.FIR;
            case "willow":
                return ModTreeGrower.WILLOW;
            case "wormtree":
                return ModTreeGrower.WORMTREE;
            case "alder":
                return ModTreeGrower.ALDER;
            case "almond":
                return ModTreeGrower.ALMOND;
            case "apple":
                return ModTreeGrower.APPLE;
            case "apricot":
                return ModTreeGrower.APRICOT;
            case "baobab":
                return ModTreeGrower.BAOBAB;
            case "black_cottonwood":
                return ModTreeGrower.BLACK_COTTONWOOD;
            case "blackthorn":
                return ModTreeGrower.BLACKTHORN;
            case "blood_orange":
                return ModTreeGrower.BLOOD_ORANGE;
            case "bloodwood":
                return ModTreeGrower.BLOODWOOD;
            case "blue_mahoe":
                return ModTreeGrower.BLUE_MAHOE;
            case "cottonwood":
                return ModTreeGrower.COTTONWOOD;
            case "datepalm":
                return ModTreeGrower.DATEPALM;
            case "ebony":
                return ModTreeGrower.EBONY;
            case "fig":
                return ModTreeGrower.FIG;
            case "fireplum":
                return ModTreeGrower.FIREPLUM;
            case "goldenheart":
                return ModTreeGrower.GOLDENHEART;
            case "lemon":
                return ModTreeGrower.LEMON;
            case "lime":
                return ModTreeGrower.LIME;
            case "linden":
                return ModTreeGrower.LINDEN;
            case "mahogany":
                return ModTreeGrower.MAHOGANY;
            case "maple":
                return ModTreeGrower.MAPLE;
            case "myrrh":
                return ModTreeGrower.MYRRH;
            case "nightwood":
                return ModTreeGrower.NIGHTWOOD;
            case "nutmeg":
                return ModTreeGrower.NUTMEG;
            case "orange":
                return ModTreeGrower.ORANGE;
            case "peach":
                return ModTreeGrower.PEACH;
            case "pear":
                return ModTreeGrower.PEAR;
            case "pecan":
                return ModTreeGrower.PECAN;
            case "persimmon":
                return ModTreeGrower.PERSIMMON;
            case "pink_ivory":
                return ModTreeGrower.PINK_IVORY;
            case "plum":
                return ModTreeGrower.PLUM;
            case "pomegranate":
                return ModTreeGrower.POMEGRANATE;
            case "purpleheart":
                return ModTreeGrower.PURPLEHEART;
            case "redwood":
                return ModTreeGrower.REDWOOD;
            case "sandalwood":
                return ModTreeGrower.SANDALWOOD;
            case "sandbeggar":
                return ModTreeGrower.SANDBEGGAR;
            case "tigerwood":
                return ModTreeGrower.TIGERWOOD;
            case "yew":
                return ModTreeGrower.YEW;
            default:
                return ModTreeGrower.SYSCAMORE;
        }
    }

    // Helper method to get the appropriate WoodType for a wood type
    private static WoodType getWoodTypeForWoodType(String woodType) {
        // Assuming ModWoodTypes contains WoodType objects
        switch (woodType) {
            case "sycamore":
                return ModWoodTypes.SYCAMORE;
            case "pine":
                return ModWoodTypes.PINE;
            case "ash":
                return ModWoodTypes.ASH;
            case "beech":
                return ModWoodTypes.BEECH;
            case "cedar":
                return ModWoodTypes.CEDAR;
            case "chestnut":
                return ModWoodTypes.CHESTNUT;
            case "hawthorn":
                return ModWoodTypes.HAWTHORN;
            case "ironwood":
                return ModWoodTypes.IRONWOOD;
            case "sentinel":
                return ModWoodTypes.SENTINEL;
            case "blackbark":
                return ModWoodTypes.BLACKBARK;
            case "aspen":
                return ModWoodTypes.ASPEN;
            case "black_cherry":
                return ModWoodTypes.BLACK_CHERRY;
            case "black_olive":
                return ModWoodTypes.BLACK_OLIVE;
            case "crabapple":
                return ModWoodTypes.CRABAPPLE;
            case "olive":
                return ModWoodTypes.OLIVE;
            case "white_cherry":
                return ModWoodTypes.WHITE_CHERRY;
            case "red_cherry":
                return ModWoodTypes.RED_CHERRY;
            case "fir":
                return ModWoodTypes.FIR;
            case "willow":
                return ModWoodTypes.WILLOW;
            case "wormtree":
                return ModWoodTypes.WORMTREE;
            case "alder":
                return ModWoodTypes.ALDER;
            case "almond":
                return ModWoodTypes.ALMOND;
            case "apple":
                return ModWoodTypes.APPLE;
            case "apricot":
                return ModWoodTypes.APRICOT;
            case "baobab":
                return ModWoodTypes.BAOBAB;
            case "black_cottonwood":
                return ModWoodTypes.BLACK_COTTONWOOD;
            case "blackthorn":
                return ModWoodTypes.BLACKTHORN;
            case "blood_orange":
                return ModWoodTypes.BLOOD_ORANGE;
            case "bloodwood":
                return ModWoodTypes.BLOODWOOD;
            case "blue_mahoe":
                return ModWoodTypes.BLUE_MAHOE;
            case "cottonwood":
                return ModWoodTypes.COTTONWOOD;
            case "datepalm":
                return ModWoodTypes.DATEPALM;
            case "ebony":
                return ModWoodTypes.EBONY;
            case "fig":
                return ModWoodTypes.FIG;
            case "fireplum":
                return ModWoodTypes.FIREPLUM;
            case "goldenheart":
                return ModWoodTypes.GOLDENHEART;
            case "lemon":
                return ModWoodTypes.LEMON;
            case "lime":
                return ModWoodTypes.LIME;
            case "linden":
                return ModWoodTypes.LINDEN;
            case "mahogany":
                return ModWoodTypes.MAHOGANY;
            case "maple":
                return ModWoodTypes.MAPLE;
            case "myrrh":
                return ModWoodTypes.MYRRH;
            case "nightwood":
                return ModWoodTypes.NIGHTWOOD;
            case "nutmeg":
                return ModWoodTypes.NUTMEG;
            case "orange":
                return ModWoodTypes.ORANGE;
            case "peach":
                return ModWoodTypes.PEACH;
            case "pear":
                return ModWoodTypes.PEAR;
            case "pecan":
                return ModWoodTypes.PECAN;
            case "persimmon":
                return ModWoodTypes.PERSIMMON;
            case "pink_ivory":
                return ModWoodTypes.PINK_IVORY;
            case "plum":
                return ModWoodTypes.PLUM;
            case "pomegranate":
                return ModWoodTypes.POMEGRANATE;
            case "purpleheart":
                return ModWoodTypes.PURPLEHEART;
            case "redwood":
                return ModWoodTypes.REDWOOD;
            case "sandalwood":
                return ModWoodTypes.SANDALWOOD;
            case "sandbeggar":
                return ModWoodTypes.SANDBEGGAR;
            case "tigerwood":
                return ModWoodTypes.TIGERWOOD;
            case "yew":
                return ModWoodTypes.YEW;
            default:
                return ModWoodTypes.SYCAMORE;
        }
    }

    // Add this method to your ModBLocks class
    public static DeferredBlock<?> getField(String fieldName) {
        try {
            Field field = ModBLocks.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (DeferredBlock<?>) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Failed to access field: " + fieldName, e);
        }
    }


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


    // ---------------------------(JOBBLOCKS)--------------------------- //


    // Town Hall (existing)
    public static final DeferredBlock<Block> TOWN_HALL = registerBlock("town_hall",
            TownHallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f),
            false);

    // All Job Barrel Blocks
    public static final DeferredBlock<Block> ALEHOUSE_BARREL = registerBlock("alehouse_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> ARMORSMITH_BARREL = registerBlock("armorsmith_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> BAKER_BARREL = registerBlock("baker_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> BANKER_BARREL = registerBlock("banker_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> BARD_BARREL = registerBlock("bard_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> BUILDER_BARREL = registerBlock("builder_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> BUTCHER_BARREL = registerBlock("butcher_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> CARAVAN_MASTER_BARREL = registerBlock("caravan_master_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> CATTLE_HERDER_BARREL = registerBlock("cattle_herder_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> CHARCOAL_BURNER_BARREL = registerBlock("charcoal_burner_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> CHICKEN_BREEDER_BARREL = registerBlock("chicken_breeder_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> FARMER_BARREL = registerBlock("farmer_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> GOAT_HERDER_BARREL = registerBlock("goat_herder_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> GROCER_BARREL = registerBlock("grocer_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> GUARD_BARREL = registerBlock("guard_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> HERBALIST_BARREL = registerBlock("herbalist_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> HORSE_BREEDER_BARREL = registerBlock("horse_breeder_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> HUNTER_BARREL = registerBlock("hunter_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> INNKEEPER_BARREL = registerBlock("innkeeper_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> JEWELER_BARREL = registerBlock("jeweler_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> LUMBERJACK_BARREL = registerBlock("lumberjack_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> MAESTER_BARREL = registerBlock("maester_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> MINER_BARREL = registerBlock("miner_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> PIG_BREEDER_BARREL = registerBlock("pig_breeder_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> PYROMANCER_BARREL = registerBlock("pyromancer_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> QUARRY_BARREL = registerBlock("quarry_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> SCRIBE_BARREL = registerBlock("scribe_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> SEPTON_BARREL = registerBlock("septon_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> SHEEP_HERDER_BARREL = registerBlock("sheep_herder_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> SHIPWRIGHT_BARREL = registerBlock("shipwright_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> SLAVER_BARREL = registerBlock("slaver_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> SMELTER_BARREL = registerBlock("smelter_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> STONEMASON_BARREL = registerBlock("stonemason_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> SWORDSMITH_BARREL = registerBlock("swordsmith_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> TAILOR_BARREL = registerBlock("tailor_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> TANNER_BARREL = registerBlock("tanner_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> TRADER_BARREL = registerBlock("trader_barrel",
            JobBarrelBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.5F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    // Tells the AGoTMod class to call the modded blocks into the game
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
