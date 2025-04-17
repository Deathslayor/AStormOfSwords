package net.darkflameproduction.agotmod.block;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.ModFlammableLeaves;
import net.darkflameproduction.agotmod.block.custom.ModFlammablePlanks;
import net.darkflameproduction.agotmod.block.custom.ModFlammableRotatedPillarBlock;
import net.darkflameproduction.agotmod.block.custom.specialleaves.WeirwoodLeavesBlock;
import net.darkflameproduction.agotmod.item.ModItems;
import net.darkflameproduction.agotmod.worldgen.tree.ModTreeGrower;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
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
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.*;
import net.minecraft.world.level.pathfinder.PathComputationType;
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
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.world.level.block.Blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Collectors;


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




    private static <T extends Block> @NotNull DeferredBlock<T> registerBlock(String name, Function<BlockBehaviour.Properties, T> block, BlockBehaviour.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block, properties);
        registerBlockItem(name, toReturn);
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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).strength(3f);

        for (int i : VALID_MUD_BRICK_INDICES) {
            String baseName = "mud_brick_" + i;

            // Resource-safe lowercase names
            String blockName = baseName + "_block";
            String stairsName = baseName + "_stairs";
            String slabName = baseName + "_slab";
            String wallName = baseName + "_wall";

            DeferredBlock<Block> base = registerBlock(blockName, Block::new, properties);
            DeferredBlock<Block> stairs = registerBlock(stairsName, p -> new StairBlock(base.get().defaultBlockState(), p), properties);
            DeferredBlock<Block> slab = registerBlock(slabName, SlabBlock::new, properties);
            DeferredBlock<Block> wall = registerBlock(wallName, WallBlock::new, properties);

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
    public static final DeferredBlock<Block> MINT_BLOCK = registerBlock("mint_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));

    // MOD ORES
    public static final DeferredBlock<Block> TIN_ORE = registerBlock("tin_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops());

    public static final DeferredBlock<Block> RAW_TIN_BLOCK = registerBlock("raw_tin_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK));

    public static final DeferredBlock<Block> YELLOW_DIAMOND_BLOCK = registerBlock("yellow_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> TRANSPARENT_DIAMOND_BLOCK = registerBlock("transparent_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> AMBER_BLOCK = registerBlock("amber_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> AMBER_ORE = registerBlock("amber_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> AMBER_DEEPSLATE_ORE = registerBlock("amber_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> AMETHYST_BLOCK = registerBlock("amethyst_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> AMETHYST_ORE = registerBlock("amethyst_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE));

    public static final DeferredBlock<Block> AMETHYST_DEEPSLATE_ORE = registerBlock("amethyst_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_DIAMOND_ORE));

    public static final DeferredBlock<Block> BLACK_DIAMOND_BLOCK = registerBlock("black_diamond_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> BLOODSTONE_BLOCK = registerBlock("bloodstone_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> BLOODSTONE_ORE = registerBlock("bloodstone_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> BLOODSTONE_DEEPSLATE_ORE = registerBlock("bloodstone_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> CARNELIAN_BLOCK = registerBlock("carnelian_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> CARNELIAN_ORE = registerBlock("carnelian_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> CARNELIAN_DEEPSLATE_ORE = registerBlock("carnelian_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> CHALCEDONY_BLOCK = registerBlock("chalcedony_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> CHALCEDONY_ORE = registerBlock("chalcedony_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> CHALCEDONY_DEEPSLATE_ORE = registerBlock("chalcedony_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> DIAMONDS_ORE = registerBlock("diamonds_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> DIAMONDS_DEEPSLATE_ORE = registerBlock("diamonds_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> GARNET_BLOCK = registerBlock("garnet_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> GARNET_ORE = registerBlock("garnet_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> GARNET_DEEPSLATE_ORE = registerBlock("garnet_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> JADE_BLOCK = registerBlock("jade_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> JADE_ORE = registerBlock("jade_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> JADE_DEEPSLATE_ORE = registerBlock("jade_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> JASPER_BLOCK = registerBlock("jasper_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> JASPER_ORE = registerBlock("jasper_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> JASPER_DEEPSLATE_ORE = registerBlock("jasper_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> MALACHITE_BLOCK = registerBlock("malachite_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> MALACHITE_ORE = registerBlock("malachite_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> MALACHITE_DEEPSLATE_ORE = registerBlock("malachite_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> MOONSTONE_BLOCK = registerBlock("moonstone_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> MOONSTONE_ORE = registerBlock("moonstone_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> MOONSTONE_DEEPSLATE_ORE = registerBlock("moonstone_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> ONYX_BLOCK = registerBlock("onyx_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> ONYX_ORE = registerBlock("onyx_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> ONYX_DEEPSLATE_ORE = registerBlock("onyx_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> OPAL_BLOCK = registerBlock("opal_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> OPAL_ORE = registerBlock("opal_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> OPAL_DEEPSLATE_ORE = registerBlock("opal_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> RUBY_BLOCK = registerBlock("ruby_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> RUBY_ORE = registerBlock("ruby_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> RUBY_DEEPSLATE_ORE = registerBlock("ruby_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> SAPPHIRE_DEEPSLATE_ORE = registerBlock("sapphire_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> TIGERS_EYE_BLOCK = registerBlock("tigers_eye_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> TIGERS_EYE_ORE = registerBlock("tigers_eye_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> TIGERS_EYE_DEEPSLATE_ORE = registerBlock("tigers_eye_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> TOPAZ_BLOCK = registerBlock("topaz_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));

    public static final DeferredBlock<Block> TOPAZ_ORE = registerBlock("topaz_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> TOPAZ_DEEPSLATE_ORE = registerBlock("topaz_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> TOURMALINE_ORE = registerBlock("tourmaline_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_ORE));

    public static final DeferredBlock<Block> TOURMALINE_DEEPSLATE_ORE = registerBlock("tourmaline_deepslate_ore", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE_COPPER_ORE));

    public static final DeferredBlock<Block> DEEPSLATE_TIN_ORE = registerBlock("deepslate_tin_ore", properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops());

    // TIN BLOCK
    public static final DeferredBlock<Block> TIN_BLOCK = registerBlock("tin_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK));

    // BRONZE BLOCK
    public static final DeferredBlock<Block> BRONZE_BLOCK = registerBlock("bronze_block", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK));

    // DARK STONE BRICK
    public static final DeferredBlock<Block> DARK_STONE_BRICK = registerBlock("dark_stone_brick", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));

    // KINGS LANDING BRICK LARGE
    public static final DeferredBlock<Block> KINGS_LANDING_BRICK_LARGE = registerBlock("kings_landing_brick_large", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));

    // ROUNDED STONE BRICK
    public static final DeferredBlock<Block> STONE_BRICK_BUT_COOLER = registerBlock("stone_brick_but_cooler", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS));

    public static class QuagmireBlock extends Block implements SimpleWaterloggedBlock {
        private static final VoxelShape FALLING_COLLISION_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
        private static final double SUFFOCATION_CHANCE = 0.9D;
        private static final int DAMAGE_TICK_INTERVAL = 20;
        // Increased slowdown factor - from 0.1F to 0.02F (5 times slower)
        private static final float SPEED_FACTOR = 0.02F;

        // Add a waterlogged property
        public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

        public QuagmireBlock(Properties properties) {
            super(properties);
            // Register the waterlogged property with a default value of false
            this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE));
        }

        @Override
        protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
            builder.add(WATERLOGGED);
        }

        @Override
        public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            if (context instanceof EntityCollisionContext entityContext) {
                Entity entity = entityContext.getEntity();
                if (entity != null) {
                    boolean hasQuagmireBelow = level.getBlockState(pos.below()).getBlock() instanceof QuagmireBlock;
                    double entityY = entity.getY();
                    double blockMiddleY = pos.getY() + 0.5D;
                    boolean entityInLowerHalf = entityY < blockMiddleY;

                    if (hasQuagmireBelow && entityInLowerHalf) {
                        return Shapes.empty();
                    }

                    if (entity.fallDistance > 2.5F) {
                        return FALLING_COLLISION_SHAPE;
                    }

                    boolean canEntityWalkOnQuagmire = entity instanceof LivingEntity &&
                            ((LivingEntity) entity).getAttributeValue(Attributes.MOVEMENT_SPEED) > 0.1F;
                    if (canEntityWalkOnQuagmire && !entity.isSteppingCarefully()) {
                        return FALLING_COLLISION_SHAPE;
                    }
                }
            }
            return FALLING_COLLISION_SHAPE;
        }

        @Override
        public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
            return Shapes.empty();
        }

        @Override
        public VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            return Shapes.empty();
        }

        @Override
        public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
            if (!(entity instanceof LivingEntity livingEntity)) return;

            // Apply stronger slowdown to match the speedFactor
            entity.makeStuckInBlock(state, new Vec3(0.8D, 0.5D, 0.8D));

            double headY = entity.getY() + entity.getEyeHeight();
            boolean headFullyInside = headY > pos.getY() && headY < pos.getY() + 1.0D;

            if (headFullyInside) {
                if (!level.isClientSide) {
                    int tickCount = (int) (level.getGameTime() % DAMAGE_TICK_INTERVAL);

                    // Apply blindness effect when head is fully inside
                    if (livingEntity instanceof Player) {
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 100, false, false));
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 4, false, false));
                    }

                    if (tickCount == 0) {
                        if (!livingEntity.hasEffect(MobEffects.WATER_BREATHING) &&
                                !livingEntity.hasEffect(MobEffects.CONDUIT_POWER)) {

                            if (level.random.nextDouble() < SUFFOCATION_CHANCE) {
                                // Apply vanilla drowning damage
                                livingEntity.hurt(level.damageSources().drown(), 2.0F);
                            }
                        }
                    }
                }
            }
        }

        // Enhanced water resistance - prevent water from replacing this block
        @Override
        public boolean canBeReplaced(BlockState state, Fluid fluid) {
            return false;
        }

        // Prevent block from being waterlogged
        @Override
        public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
            return true;
        }

        // Always maintain empty fluid state
        @Override
        public FluidState getFluidState(BlockState state) {
            return Fluids.EMPTY.defaultFluidState();
        }

        // Block water propagation when placing the block
        @Override
        public BlockState getStateForPlacement(BlockPlaceContext context) {
            return this.defaultBlockState().setValue(WATERLOGGED, Boolean.FALSE);
        }

        // Override for multiplayer compatibility
    }

    // Register the block with updated properties
    public static final DeferredBlock<Block> QUAGMIRE = registerBlock("quagmire",
            properties -> new QuagmireBlock(properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SNOW_BLOCK)
                    .sound(SoundType.MUD)
                    .friction(0.8F)
                    .speedFactor(0.1F)  // Change to match the constant defined above
                    .strength(6f)
                    .noOcclusion()  // Important for proper rendering when sinking
                    .isValidSpawn((state, level, pos, type) -> false)  // Prevent mob spawning
                    .pushReaction(PushReaction.BLOCK));  // Prevent pistons from moving it



    // ---------------------------(BLOCKS)--------------------------- //

    // ---------------------------(TREE BLOCKS)--------------------------- //
    //Weirwood
    public static final DeferredBlock<Block> WEIRWOOD_LOG = registerBlock("weirwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> WEIRWOOD_FACE_LOG = registerBlock("weirwood_face_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> WEIRWOOD_WOOD = registerBlock("weirwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_WEIRWOOD_LOG = registerBlock("stripped_weirwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_WEIRWOOD_WOOD = registerBlock("stripped_weirwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> WEIRWOOD_PLANKS = registerBlock("weirwood_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> WEIRWOOD_LEAVES = registerBlock("weirwood_leaves", WeirwoodLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES));
    public static final DeferredBlock<Block> WEIRWOOD_SAPLING = registerBlock("weirwood_sapling", properties -> new SaplingBlock(ModTreeGrower.WEIRWOOD, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> WEIRWOOD_STAIRS = registerBlock("weirwood_stairs",
            properties -> new StairBlock(ModBLocks.WEIRWOOD_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_SLAB = registerBlock("weirwood_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_BUTTON = registerBlock("weirwood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_PRESSURE_PLATE = registerBlock("weirwood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> WEIRWOOD_FENCE = registerBlock("weirwood_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> WEIRWOOD_FENCE_GATE = registerBlock("weirwood_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> WEIRWOOD_WALL = registerBlock("weirwood_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> WEIRWOOD_DOOR = registerBlock("weirwood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> WEIRWOOD_TRAPDOOR = registerBlock("weirwood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //Sycamore
    public static final DeferredBlock<Block> SYCAMORE_LOG = registerBlock("sycamore_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> SYCAMORE_WOOD = registerBlock("sycamore_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SYCAMORE_LOG = registerBlock("sycamore_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SYCAMORE_WOOD = registerBlock("sycamore_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> SYCAMORE_PLANKS = registerBlock("sycamore_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> SYCAMORE_LEAVES = registerBlock("sycamore_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> SYCAMORE_SAPLING = registerBlock("sycamore_sapling", properties -> new SaplingBlock(ModTreeGrower.SYSCAMORE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> SYCAMORE_STAIRS = registerBlock("sycamore_stairs",
            properties -> new StairBlock(ModBLocks.SYCAMORE_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_SLAB = registerBlock("sycamore_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_BUTTON = registerBlock("sycamore_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_PRESSURE_PLATE = registerBlock("sycamore_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SYCAMORE_FENCE = registerBlock("sycamore_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SYCAMORE_FENCE_GATE = registerBlock("sycamore_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SYCAMORE_WALL = registerBlock("sycamore_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SYCAMORE_DOOR = registerBlock("sycamore_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> SYCAMORE_TRAPDOOR = registerBlock("sycamore_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //Sentinel
    public static final DeferredBlock<Block> SENTINEL_LOG = registerBlock("sentinel_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> SENTINEL_WOOD = registerBlock("sentinel_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SENTINEL_LOG = registerBlock("sentinel_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_SENTINEL_WOOD = registerBlock("sentinel_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> SENTINEL_PLANKS = registerBlock("sentinel_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> SENTINEL_LEAVES = registerBlock("sentinel_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> SENTINEL_SAPLING = registerBlock("sentinel_sapling", properties -> new SaplingBlock(ModTreeGrower.SENTINEL, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> SENTINEL_STAIRS = registerBlock("sentinel_stairs",
            properties -> new StairBlock(ModBLocks.SENTINEL_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_SLAB = registerBlock("sentinel_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_BUTTON = registerBlock("sentinel_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_PRESSURE_PLATE = registerBlock("sentinel_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> SENTINEL_FENCE = registerBlock("sentinel_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SENTINEL_FENCE_GATE = registerBlock("sentinel_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SENTINEL_WALL = registerBlock("sentinel_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> SENTINEL_DOOR = registerBlock("sentinel_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> SENTINEL_TRAPDOOR = registerBlock("sentinel_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //Pine
    public static final DeferredBlock<Block> PINE_LOG = registerBlock("pine_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG).strength(3f));
    public static final DeferredBlock<Block> PINE_WOOD = registerBlock("pine_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_PINE_LOG = registerBlock("pine_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_PINE_WOOD = registerBlock("pine_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD).strength(3f));
    public static final DeferredBlock<Block> PINE_PLANKS = registerBlock("pine_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    public static final DeferredBlock<Block> PINE_LEAVES = registerBlock("pine_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));
    public static final DeferredBlock<Block> PINE_SAPLING = registerBlock("pine_sapling", properties -> new SaplingBlock(ModTreeGrower.PINE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING));
    public static final DeferredBlock<Block> PINE_STAIRS = registerBlock("pine_stairs",
            properties -> new StairBlock(ModBLocks.PINE_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_SLAB = registerBlock("pine_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_BUTTON = registerBlock("pine_button",
            properties -> new ButtonBlock(BlockSetType.SPRUCE, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_PRESSURE_PLATE = registerBlock("pine_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.SPRUCE,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_STAIRS));
    public static final DeferredBlock<Block> PINE_FENCE = registerBlock("pine_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    public static final DeferredBlock<Block> PINE_FENCE_GATE = registerBlock("pine_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    public static final DeferredBlock<Block> PINE_WALL = registerBlock("pine_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE));
    public static final DeferredBlock<Block> PINE_DOOR = registerBlock("pine_door",
            properties -> new DoorBlock(BlockSetType.SPRUCE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_DOOR));
    public static final DeferredBlock<Block> PINE_TRAPDOOR = registerBlock("pine_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.SPRUCE, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_TRAPDOOR));


    //ironwood
    public static final DeferredBlock<Block> IRONWOOD_LOG = registerBlock("ironwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> IRONWOOD_WOOD = registerBlock("ironwood_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_IRONWOOD_LOG = registerBlock("ironwood_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_IRONWOOD_WOOD = registerBlock("ironwood_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> IRONWOOD_PLANKS = registerBlock("ironwood_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> IRONWOOD_LEAVES = registerBlock("ironwood_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> IRONWOOD_SAPLING = registerBlock("ironwood_sapling", properties -> new SaplingBlock(ModTreeGrower.IRONWOOD, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> IRONWOOD_STAIRS = registerBlock("ironwood_stairs",
            properties -> new StairBlock(ModBLocks.IRONWOOD_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_SLAB = registerBlock("ironwood_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_BUTTON = registerBlock("ironwood_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_PRESSURE_PLATE = registerBlock("ironwood_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> IRONWOOD_FENCE = registerBlock("ironwood_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> IRONWOOD_FENCE_GATE = registerBlock("ironwood_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> IRONWOOD_WALL = registerBlock("ironwood_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> IRONWOOD_DOOR = registerBlock("ironwood_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> IRONWOOD_TRAPDOOR = registerBlock("ironwood_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //hawthorn
    public static final DeferredBlock<Block> HAWTHORN_LOG = registerBlock("hawthorn_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> HAWTHORN_WOOD = registerBlock("hawthorn_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_HAWTHORN_LOG = registerBlock("hawthorn_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_HAWTHORN_WOOD = registerBlock("hawthorn_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> HAWTHORN_PLANKS = registerBlock("hawthorn_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> HAWTHORN_LEAVES = registerBlock("hawthorn_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> HAWTHORN_SAPLING = registerBlock("hawthorn_sapling", properties -> new SaplingBlock(ModTreeGrower.HAWTHORN, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> HAWTHORN_STAIRS = registerBlock("hawthorn_stairs",
            properties -> new StairBlock(ModBLocks.HAWTHORN_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_SLAB = registerBlock("hawthorn_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_BUTTON = registerBlock("hawthorn_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_PRESSURE_PLATE = registerBlock("hawthorn_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> HAWTHORN_FENCE = registerBlock("hawthorn_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> HAWTHORN_FENCE_GATE = registerBlock("hawthorn_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> HAWTHORN_WALL = registerBlock("hawthorn_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> HAWTHORN_DOOR = registerBlock("hawthorn_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> HAWTHORN_TRAPDOOR = registerBlock("hawthorn_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    //chestnut
    public static final DeferredBlock<Block> CHESTNUT_LOG = registerBlock("chestnut_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> CHESTNUT_WOOD = registerBlock("chestnut_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_LOG = registerBlock("chestnut_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CHESTNUT_WOOD = registerBlock("chestnut_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> CHESTNUT_PLANKS = registerBlock("chestnut_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> CHESTNUT_LEAVES = registerBlock("chestnut_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> CHESTNUT_SAPLING = registerBlock("chestnut_sapling", properties -> new SaplingBlock(ModTreeGrower.CHESTNUT, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> CHESTNUT_STAIRS = registerBlock("chestnut_stairs",
            properties -> new StairBlock(ModBLocks.CHESTNUT_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_SLAB = registerBlock("chestnut_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_BUTTON = registerBlock("chestnut_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_PRESSURE_PLATE = registerBlock("chestnut_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CHESTNUT_FENCE = registerBlock("chestnut_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CHESTNUT_FENCE_GATE = registerBlock("chestnut_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CHESTNUT_WALL = registerBlock("chestnut_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CHESTNUT_DOOR = registerBlock("chestnut_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> CHESTNUT_TRAPDOOR = registerBlock("chestnut_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Cedar
    public static final DeferredBlock<Block> CEDAR_LOG = registerBlock("cedar_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> CEDAR_WOOD = registerBlock("cedar_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CEDAR_LOG = registerBlock("cedar_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_CEDAR_WOOD = registerBlock("cedar_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> CEDAR_PLANKS = registerBlock("cedar_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> CEDAR_LEAVES = registerBlock("cedar_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> CEDAR_SAPLING = registerBlock("cedar_sapling", properties -> new SaplingBlock(ModTreeGrower.CEDAR, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> CEDAR_STAIRS = registerBlock("cedar_stairs",
            properties -> new StairBlock(ModBLocks.CEDAR_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_SLAB = registerBlock("cedar_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_BUTTON = registerBlock("cedar_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_PRESSURE_PLATE = registerBlock("cedar_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> CEDAR_FENCE = registerBlock("cedar_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CEDAR_FENCE_GATE = registerBlock("cedar_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CEDAR_WALL = registerBlock("cedar_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> CEDAR_DOOR = registerBlock("cedar_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> CEDAR_TRAPDOOR = registerBlock("cedar_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Beech
    public static final DeferredBlock<Block> BEECH_LOG = registerBlock("beech_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> BEECH_WOOD = registerBlock("beech_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BEECH_LOG = registerBlock("beech_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BEECH_WOOD = registerBlock("beech_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> BEECH_PLANKS = registerBlock("beech_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> BEECH_LEAVES = registerBlock("beech_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> BEECH_SAPLING = registerBlock("beech_sapling", properties -> new SaplingBlock(ModTreeGrower.BEECH, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> BEECH_STAIRS = registerBlock("beech_stairs",
            properties -> new StairBlock(ModBLocks.BEECH_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_SLAB = registerBlock("beech_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_BUTTON = registerBlock("beech_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_PRESSURE_PLATE = registerBlock("beech_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BEECH_FENCE = registerBlock("beech_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BEECH_FENCE_GATE = registerBlock("beech_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BEECH_WALL = registerBlock("beech_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BEECH_DOOR = registerBlock("beech_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> BEECH_TRAPDOOR = registerBlock("beech_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Ash
    public static final DeferredBlock<Block> ASH_LOG = registerBlock("ash_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> ASH_WOOD = registerBlock("ash_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASH_LOG = registerBlock("ash_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASH_WOOD = registerBlock("ash_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> ASH_PLANKS = registerBlock("ash_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> ASH_LEAVES = registerBlock("ash_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> ASH_SAPLING = registerBlock("ash_sapling", properties -> new SaplingBlock(ModTreeGrower.ASH, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> ASH_STAIRS = registerBlock("ash_stairs",
            properties -> new StairBlock(ModBLocks.ASH_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_SLAB = registerBlock("ash_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_BUTTON = registerBlock("ash_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_PRESSURE_PLATE = registerBlock("ash_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASH_FENCE = registerBlock("ash_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASH_FENCE_GATE = registerBlock("ash_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASH_WALL = registerBlock("ash_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASH_DOOR = registerBlock("ash_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> ASH_TRAPDOOR = registerBlock("ash_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Blackbark
    public static final DeferredBlock<Block> BLACKBARK_LOG = registerBlock("blackbark_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> BLACKBARK_WOOD = registerBlock("blackbark_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BLACKBARK_LOG = registerBlock("blackbark_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_BLACKBARK_WOOD = registerBlock("blackbark_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> BLACKBARK_PLANKS = registerBlock("blackbark_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> BLACKBARK_LEAVES = registerBlock("blackbark_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> BLACKBARK_SAPLING = registerBlock("blackbark_sapling", properties -> new SaplingBlock(ModTreeGrower.BLACKBARK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> BLACKBARK_STAIRS = registerBlock("blackbark_stairs",
            properties -> new StairBlock(ModBLocks.BLACKBARK_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_SLAB = registerBlock("blackbark_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_BUTTON = registerBlock("blackbark_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_PRESSURE_PLATE = registerBlock("blackbark_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> BLACKBARK_FENCE = registerBlock("blackbark_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BLACKBARK_FENCE_GATE = registerBlock("blackbark_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BLACKBARK_WALL = registerBlock("blackbark_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> BLACKBARK_DOOR = registerBlock("blackbark_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> BLACKBARK_TRAPDOOR = registerBlock("blackbark_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Aspen
    public static final DeferredBlock<Block> ASPEN_LOG = registerBlock("aspen_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> ASPEN_WOOD = registerBlock("aspen_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_LOG = registerBlock("aspen_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ASPEN_WOOD = registerBlock("aspen_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> ASPEN_PLANKS = registerBlock("aspen_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> ASPEN_LEAVES = registerBlock("aspen_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> ASPEN_SAPLING = registerBlock("aspen_sapling", properties -> new SaplingBlock(ModTreeGrower.ASPEN, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> ASPEN_STAIRS = registerBlock("aspen_stairs",
            properties -> new StairBlock(ModBLocks.ASPEN_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_SLAB = registerBlock("aspen_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_BUTTON = registerBlock("aspen_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_PRESSURE_PLATE = registerBlock("aspen_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ASPEN_FENCE = registerBlock("aspen_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASPEN_FENCE_GATE = registerBlock("aspen_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASPEN_WALL = registerBlock("aspen_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ASPEN_DOOR = registerBlock("aspen_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> ASPEN_TRAPDOOR = registerBlock("aspen_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));


    //Alder
    public static final DeferredBlock<Block> ALDER_LOG = registerBlock("alder_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> ALDER_WOOD = registerBlock("alder_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ALDER_LOG = registerBlock("alder_log_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f));
    public static final DeferredBlock<Block> STRIPPED_ALDER_WOOD = registerBlock("alder_wood_stripped", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f));
    public static final DeferredBlock<Block> ALDER_PLANKS = registerBlock("alder_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> ALDER_LEAVES = registerBlock("alder_leaves", ModFlammableLeaves::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));
    public static final DeferredBlock<Block> ALDER_SAPLING = registerBlock("alder_sapling", properties -> new SaplingBlock(ModTreeGrower.ALDER, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING));
    public static final DeferredBlock<Block> ALDER_STAIRS = registerBlock("alder_stairs",
            properties -> new StairBlock(ModBLocks.ALDER_PLANKS.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_SLAB = registerBlock("alder_slabs",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_BUTTON = registerBlock("alder_button",
            properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_PRESSURE_PLATE = registerBlock("alder_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.OAK,
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS));
    public static final DeferredBlock<Block> ALDER_FENCE = registerBlock("alder_fence",
            FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ALDER_FENCE_GATE = registerBlock("alder_fence_gate",
            properties -> new FenceGateBlock(properties,
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ALDER_WALL = registerBlock("alder_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE));
    public static final DeferredBlock<Block> ALDER_DOOR = registerBlock("alder_door",
            properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR));
    public static final DeferredBlock<Block> ALDER_TRAPDOOR = registerBlock("alder_trapdoor",
            properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR));

    // ---------------------------(STONE)--------------------------- //

    public static final DeferredBlock<Block> REDKEEP_STONE_BLOCK = registerBlock("redkeep_stone",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f));
    public static final DeferredBlock<Block> REDKEEP_STONE_STAIRS = registerBlock("redkeep_stone_stairs",
            properties -> new StairBlock(ModBLocks.REDKEEP_STONE_BLOCK.get().defaultBlockState(),
                    properties), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS));
    public static final DeferredBlock<Block> REDKEEP_STONE_SLAB = registerBlock("redkeep_stone_slab",
            SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB));
    public static final DeferredBlock<Block> REDKEEP_STONE_WALL = registerBlock("redkeep_stone_wall",
            WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL));

    // ---------------------------(FLOWERS)--------------------------- //
    public static final DeferredBlock<Block> WINTER_ROSE = registerBlock("winter_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> WILD_RADISH = registerBlock("wild_radish",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> WHITE_ROSE = registerBlock("white_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> THORN_BUSH = registerBlock("thorn_bush",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));

    public static final DeferredBlock<Block> THISTLE = registerBlock("thistle",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> TANSY = registerBlock("tansy",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> SPICEFLOWER = registerBlock("spiceflower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> SEDGE = registerBlock("sedge",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> SAFFRON_CROCUS = registerBlock("saffron_crocus",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> ROSE = registerBlock("rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> POISON_KISSES = registerBlock("poison_kisses",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> PENNYROYAL = registerBlock("pennyroyal",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> OPIUM_POPPY = registerBlock("opium_poppy",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> NIGHTSHADE = registerBlock("nightshade",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> MOONBLOOM = registerBlock("moonbloom",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LUNGWORT = registerBlock("lungwort",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LIVERWORT = registerBlock("liverwort",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LAVENDER = registerBlock("lavender",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> LADYS_LACE = registerBlock("ladys_lace",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GORSE = registerBlock("gorse",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GOLDENROD = registerBlock("goldenrod",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GOLDENCUP = registerBlock("goldencup",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GOATHEAD = registerBlock("goathead",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GINGER = registerBlock("ginger",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> GILLYFLOWER = registerBlock("gillyflower",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> FROSTFIRE = registerBlock("frostfire",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> FORGET_ME_NOT = registerBlock("forget_me_not",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> EVENING_STAR = registerBlock("evening_star",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> DUSKY_ROSE = registerBlock("dusky_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> DRAGONS_BREATH = registerBlock("dragons_breath",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> COLDSNAP = registerBlock("coldsnap",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> BLUE_ROSE = registerBlock("blue_rose",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> BLOODBLOOM = registerBlock("bloodbloom",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> BLACK_LOTUS = registerBlock("black_lotus",
            properties -> new FlowerBlock(MobEffects.SATURATION, 0, properties),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY));

    public static final DeferredBlock<Block> WINTER_ROSE_BUSH = registerBlock("winter_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> WHITE_ROSE_BUSH = registerBlock("white_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> DUSKY_ROSE_BUSH = registerBlock("dusky_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> BLUE_ROSE_BUSH = registerBlock("blue_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));

    public static final DeferredBlock<Block> RED_ROSE_BUSH = registerBlock("red_rose_bush",
            TallFlowerBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH));




    // ---------------------------(POTTED PLANTS)--------------------------- //
    //public static final DeferredBlock<Block> POTTED_WINTER_ROSE = BLOCKS.register("potted_winter_rose",
    //        properties -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBLocks.WINTER_ROSE,
    //               BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY).noOcclusion()));

    // Tells the AGoTMod class to call the modded blocks into the game
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
