package net.darkflameproduction.agotmod.block;// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.custom.*;
import net.darkflameproduction.agotmod.block.custom.BarleyCropBlock;
import net.darkflameproduction.agotmod.block.custom.barrel.BarrelDummyBlock;
import net.darkflameproduction.agotmod.block.custom.barrel.BarrelLargeBlock;
import net.darkflameproduction.agotmod.block.custom.barrel.BarrelMediumBlock;
import net.darkflameproduction.agotmod.block.custom.barrel.BarrelSmallBlock;
import net.darkflameproduction.agotmod.block.custom.furniture.ArmChairBlock;
import net.darkflameproduction.agotmod.block.custom.furniture.ChairBlock;
import net.darkflameproduction.agotmod.block.custom.furniture.StoolBlock;
import net.darkflameproduction.agotmod.block.custom.furniture.TableBlock;
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
import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


// ModBLocks class for registering custom blocks
public class ModBLocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AGoTMod.MOD_ID);

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

    // ── Replace everything from the VALID_BLACKSTONE_INDICES declarations
// through the closing brace of the static { } block with this ──────────────

    public static final List<Integer> VALID_BLACKSTONE_INDICES  = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15 && i != 22 && i != 31).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_BASALT_INDICES      = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 1  && i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_TUFF_INDICES        = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 10 && i != 15 && i != 20 && i != 21).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_BRICKS_INDICES      = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 2  && i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_CALCITE_INDICES     = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_DIORITE_INDICES     = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_QUARTZ_INDICES      = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 14 && i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_CDEEPSLATE_INDICES  = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 4  && i != 15 && i != 19).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_GRANITE_INDICES     = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_ANDESITE_INDICES    = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_REDKEEP_INDICES     = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_RSANDSTONE_INDICES  = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15 && i != 16 && i != 17 && i != 18).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_SANDSTONE_INDICES   = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15 && i != 16 && i != 17 && i != 18).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_STONE_INDICES       = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 5  && i != 15 && i != 19 && i != 23).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_SSTONE_INDICES      = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15 && i != 23).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_BONE_INDICES        = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15 && i != 35).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_DRIPSTONE_INDICES   = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_PACKED_ICE_INDICES  = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 15).boxed().collect(Collectors.toList()));
    public static final List<Integer> VALID_MUD_BRICK_INDICES   = new ArrayList<>(IntStream.rangeClosed(1, 38).filter(i -> i != 7  && i != 9 && i != 15).boxed().collect(Collectors.toList()));

    public static final Map<Integer, BlockSet> BLACKSTONE_VARIANTS  = new HashMap<>();
    public static final Map<Integer, BlockSet> BASALT_VARIANTS      = new HashMap<>();
    public static final Map<Integer, BlockSet> BRICKS_VARIANTS      = new HashMap<>();
    public static final Map<Integer, BlockSet> CALCITE_VARIANTS     = new HashMap<>();
    public static final Map<Integer, BlockSet> CDEEPSLATE_VARIANTS  = new HashMap<>();
    public static final Map<Integer, BlockSet> GRANITE_VARIANTS     = new HashMap<>();
    public static final Map<Integer, BlockSet> REDKEEP_VARIANTS     = new HashMap<>();
    public static final Map<Integer, BlockSet> RSANDSTONE_VARIANTS  = new HashMap<>();
    public static final Map<Integer, BlockSet> SANDSTONE_VARIANTS   = new HashMap<>();
    public static final Map<Integer, BlockSet> STONE_VARIANTS       = new HashMap<>();
    public static final Map<Integer, BlockSet> SSTONE_VARIANTS      = new HashMap<>();
    public static final Map<Integer, BlockSet> BONE_VARIANTS        = new HashMap<>();
    public static final Map<Integer, BlockSet> DRIPSTONE_VARIANTS   = new HashMap<>();
    public static final Map<Integer, BlockSet> PACKED_ICE_VARIANTS  = new HashMap<>();
    public static final Map<Integer, BlockSet> MUD_BRICK_VARIANTS   = new HashMap<>();
    public static final Map<Integer, BlockSet> ANDESITE_VARIANTS    = new HashMap<>();
    public static final Map<Integer, BlockSet> QUARTZ_VARIANTS      = new HashMap<>();
    public static final Map<Integer, BlockSet> DIORITE_VARIANTS     = new HashMap<>();
    public static final Map<Integer, BlockSet> TUFF_VARIANTS        = new HashMap<>();

    private static void registerVariants(String prefix, BlockBehaviour.Properties props,
                                         List<Integer> indices, Map<Integer, BlockSet> map) {
        for (int i : indices) {
            String b = prefix + "_" + i;
            DeferredBlock<Block> bl = registerBlock(b + "_block",  Block::new, props, true);
            DeferredBlock<Block> st = registerBlock(b + "_stairs", p -> new StairBlock(bl.get().defaultBlockState(), p), props, true);
            DeferredBlock<Block> sl = registerBlock(b + "_slab",   SlabBlock::new, props, true);
            DeferredBlock<Block> wa = registerBlock(b + "_wall",   WallBlock::new, props, true);
            map.put(i, new BlockSet(bl, st, sl, wa));
        }
    }

    public record BlockSet(DeferredBlock<Block> base, DeferredBlock<Block> stairs,
                           DeferredBlock<Block> slab, DeferredBlock<Block> wall) {}

    static {
        registerVariants("blackstone",  BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).strength(3f),       VALID_BLACKSTONE_INDICES,  BLACKSTONE_VARIANTS);
        registerVariants("basalt",      BlockBehaviour.Properties.ofFullCopy(Blocks.BASALT).strength(3f),            VALID_BASALT_INDICES,      BASALT_VARIANTS);
        registerVariants("tuff_brick",  BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF).strength(3f),              VALID_TUFF_INDICES,        TUFF_VARIANTS);
        registerVariants("bricks",      BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).strength(3f),            VALID_BRICKS_INDICES,      BRICKS_VARIANTS);
        registerVariants("calcite",     BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE).strength(3f),           VALID_CALCITE_INDICES,     CALCITE_VARIANTS);
        registerVariants("diorite",     BlockBehaviour.Properties.ofFullCopy(Blocks.DIORITE).strength(3f),           VALID_DIORITE_INDICES,     DIORITE_VARIANTS);
        registerVariants("quartz",      BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).strength(3f),      VALID_QUARTZ_INDICES,      QUARTZ_VARIANTS);
        registerVariants("cdeepslate",  BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLED_DEEPSLATE).strength(3f), VALID_CDEEPSLATE_INDICES,  CDEEPSLATE_VARIANTS);
        registerVariants("granite",     BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f),           VALID_GRANITE_INDICES,     GRANITE_VARIANTS);
        registerVariants("andesite",    BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE).strength(3f),          VALID_ANDESITE_INDICES,    ANDESITE_VARIANTS);
        registerVariants("redkeep",     BlockBehaviour.Properties.ofFullCopy(Blocks.GRANITE).strength(3f),           VALID_REDKEEP_INDICES,     REDKEEP_VARIANTS);
        registerVariants("rsandstone",  BlockBehaviour.Properties.ofFullCopy(Blocks.RED_SANDSTONE).strength(3f),     VALID_RSANDSTONE_INDICES,  RSANDSTONE_VARIANTS);
        registerVariants("sandstone",   BlockBehaviour.Properties.ofFullCopy(Blocks.SANDSTONE).strength(3f),         VALID_SANDSTONE_INDICES,   SANDSTONE_VARIANTS);
        registerVariants("stone",       BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).strength(3f),             VALID_STONE_INDICES,       STONE_VARIANTS);
        registerVariants("sstone",      BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_STONE).strength(3f),      VALID_SSTONE_INDICES,      SSTONE_VARIANTS);
        registerVariants("bone",        BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK).strength(3f),        VALID_BONE_INDICES,        BONE_VARIANTS);
        registerVariants("dripstone",   BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK).strength(3f),   VALID_DRIPSTONE_INDICES,   DRIPSTONE_VARIANTS);
        registerVariants("packed_ice",  BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).strength(3f),        VALID_PACKED_ICE_INDICES,  PACKED_ICE_VARIANTS);
        registerVariants("mud_brick",   BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS).strength(3f),        VALID_MUD_BRICK_INDICES,   MUD_BRICK_VARIANTS);
    }

    public static final Map<String, Integer> WATTLE_DAUB_COLORS = new LinkedHashMap<>();
    static {
        WATTLE_DAUB_COLORS.put("white",      0xFFFFFFFF);
        WATTLE_DAUB_COLORS.put("orange",     0xFF8A6B52);
        WATTLE_DAUB_COLORS.put("magenta",    0xFF866184);
        WATTLE_DAUB_COLORS.put("light_blue", 0xFF62778A);
        WATTLE_DAUB_COLORS.put("yellow",     0xFF9C9460);
        WATTLE_DAUB_COLORS.put("lime",       0xFF72845A);
        WATTLE_DAUB_COLORS.put("pink",       0xFF957076);
        WATTLE_DAUB_COLORS.put("gray",       0xFF4C4C4C);
        WATTLE_DAUB_COLORS.put("light_gray", 0xFF999999);
        WATTLE_DAUB_COLORS.put("cyan",       0xFF58777A);
        WATTLE_DAUB_COLORS.put("purple",     0xFF67547A);
        WATTLE_DAUB_COLORS.put("blue",       0xFF536887);
        WATTLE_DAUB_COLORS.put("brown",      0xFFBBA98A);
        WATTLE_DAUB_COLORS.put("green",      0xFF627350);
        WATTLE_DAUB_COLORS.put("red",        0xFF8A615C);
        WATTLE_DAUB_COLORS.put("black",      0xFF191919);
    }

    // wattle_and_daub_<color>         — plain (texture 1 only, tinted)
// wattle_and_daub_<color>_<2-24>  — wattle variants (texture 1 tinted + wattle overlay on sides)
    public static final Map<String, DeferredBlock<Block>> WATTLE_AND_DAUB_PLAIN    = new HashMap<>();
    public static final Map<String, Map<Integer, DeferredBlock<Block>>> WATTLE_AND_DAUB_VARIANTS = new HashMap<>();

    static {
        BlockBehaviour.Properties props = BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(1.5f);

        for (String color : WATTLE_DAUB_COLORS.keySet()) {
            // plain block
            WATTLE_AND_DAUB_PLAIN.put(color,
                    registerBlock("wattle_and_daub" + color, WattleAndDaubBlock::new, props, true));

            // wattle variants 2–24
            Map<Integer, DeferredBlock<Block>> variants = new HashMap<>();
            for (int i = 2; i <= 24; i++) {
                variants.put(i, registerBlock(
                        "wattle_and_daub" + color + "_" + i,
                        WattleAndDaubBlock::new, props, true));
            }
            WATTLE_AND_DAUB_VARIANTS.put(color, variants);
        }
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
    public static final DeferredBlock<Block> DIRT_STAIRS = registerBlock("dirt_stairs", properties -> new StairBlock(Blocks.OAK_STAIRS.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), true);
    public static final DeferredBlock<Block> DIRT_SLAB = registerBlock("dirt_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), true);
    public static final DeferredBlock<Block> MUD_STAIRS = registerBlock("mud_stairs", properties -> new StairBlock(Blocks.MUD.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.MUD), true);
    public static final DeferredBlock<Block> MUD_SLAB = registerBlock("mud_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MUD), true);

    public static final DeferredBlock<Block> COARSE_DIRT_STAIRS = registerBlock("coarse_dirt_stairs", properties -> new StairBlock(Blocks.COARSE_DIRT.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT), true);
    public static final DeferredBlock<Block> COARSE_DIRT_SLAB = registerBlock("coarse_dirt_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT), true);

    public static final DeferredBlock<Block> PODZOL_STAIRS = registerBlock("podzol_stairs", properties -> new StairBlock(Blocks.PODZOL.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL), true);
    public static final DeferredBlock<Block> PODZOL_SLAB = registerBlock("podzol_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL), true);

    public static final DeferredBlock<Block> GRASS_BLOCK_STAIRS = registerBlock("grass_block_stairs", properties -> new StairBlock(Blocks.GRASS_BLOCK.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK), true);
    public static final DeferredBlock<Block> GRASS_BLOCK_SLAB = registerBlock("grass_block_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK), true);

    public static final DeferredBlock<Block> DIRT_PATH_STAIRS = registerBlock("dirt_path_stairs", properties -> new StairBlock(Blocks.DIRT_PATH.defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH), true);
    public static final DeferredBlock<Block> DIRT_PATH_SLAB = registerBlock("dirt_path_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH), true);

    public static final DeferredBlock<Block> PEAT = registerBlock("peat", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), true);
    public static final DeferredBlock<Block> HEARTH_BLOCK = registerBlock("hearth_block",
            HearthBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MAGMA_BLOCK), true);

    public static final DeferredBlock<Block> THATCH_BLOCK = registerBlock("thatch_block", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK).strength(3f), true);
    public static final DeferredBlock<Block> THATCH_STAIRS = registerBlock("thatch_stairs", properties -> new StairBlock(ModBLocks.THATCH_BLOCK.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK), true);
    public static final DeferredBlock<Block> THATCH_SLAB = registerBlock("thatch_slabs", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.HAY_BLOCK), true);

    // ---------------------------(TREE BLOCKS)--------------------------- //
    //Weirwood
    public static final DeferredBlock<Block> WEIRWOOD_LOG = registerBlock("weirwood_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> WEIRWOOD_FACE_LOG = registerBlock("weirwood_face_log", WeirwoodFaceLogBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CARVED_PUMPKIN), true);
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

    // Rotten Wood
    public static final DeferredBlock<Block> ROTTEN_LOG = registerBlock("rotten_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> ROTTEN_WOOD = registerBlock("rotten_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ROTTEN_LOG = registerBlock("stripped_rotten_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_ROTTEN_WOOD = registerBlock("stripped_rotten_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> ROTTEN_PLANKS = registerBlock("rotten_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> ROTTEN_STAIRS = registerBlock("rotten_stairs", properties -> new StairBlock(ModBLocks.ROTTEN_PLANKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ROTTEN_SLAB = registerBlock("rotten_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ROTTEN_BUTTON = registerBlock("rotten_button", properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ROTTEN_PRESSURE_PLATE = registerBlock("rotten_pressure_plate", properties -> new PressurePlateBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> ROTTEN_FENCE = registerBlock("rotten_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ROTTEN_FENCE_GATE = registerBlock("rotten_fence_gate", properties -> new FenceGateBlock(properties, SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ROTTEN_WALL = registerBlock("rotten_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> ROTTEN_DOOR = registerBlock("rotten_door", properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> ROTTEN_TRAPDOOR = registerBlock("rotten_trapdoor", properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);

    // Charred Wood
    public static final DeferredBlock<Block> CHARRED_LOG = registerBlock("charred_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> CHARRED_WOOD = registerBlock("charred_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_CHARRED_LOG = registerBlock("stripped_charred_log", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG).strength(3f), true);
    public static final DeferredBlock<Block> STRIPPED_CHARRED_WOOD = registerBlock("stripped_charred_wood", ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD).strength(3f), true);
    public static final DeferredBlock<Block> CHARRED_PLANKS = registerBlock("charred_planks", ModFlammablePlanks::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS), true);
    public static final DeferredBlock<Block> CHARRED_STAIRS = registerBlock("charred_stairs", properties -> new StairBlock(ModBLocks.CHARRED_PLANKS.get().defaultBlockState(), properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHARRED_SLAB = registerBlock("charred_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHARRED_BUTTON = registerBlock("charred_button", properties -> new ButtonBlock(BlockSetType.OAK, 10, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHARRED_PRESSURE_PLATE = registerBlock("charred_pressure_plate", properties -> new PressurePlateBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS), true);
    public static final DeferredBlock<Block> CHARRED_FENCE = registerBlock("charred_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CHARRED_FENCE_GATE = registerBlock("charred_fence_gate", properties -> new FenceGateBlock(properties, SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CHARRED_WALL = registerBlock("charred_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> CHARRED_DOOR = registerBlock("charred_door", properties -> new DoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR), true);
    public static final DeferredBlock<Block> CHARRED_TRAPDOOR = registerBlock("charred_trapdoor", properties -> new TrapDoorBlock(BlockSetType.OAK, properties), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR), true);

    public static final DeferredBlock<Block> OAK_WALL = registerBlock("oak_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> SPRUCE_WALL = registerBlock("spruce_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_FENCE_GATE), true);
    public static final DeferredBlock<Block> BIRCH_WALL = registerBlock("birch_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_FENCE_GATE), true);
    public static final DeferredBlock<Block> JUNGLE_WALL = registerBlock("jungle_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_FENCE_GATE), true);
    public static final DeferredBlock<Block> ACACIA_WALL = registerBlock("acacia_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_FENCE_GATE), true);
    public static final DeferredBlock<Block> DARK_OAK_WALL = registerBlock("dark_oak_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_FENCE_GATE), true);
    public static final DeferredBlock<Block> MANGROVE_WALL = registerBlock("mangrove_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE_GATE), true);
    public static final DeferredBlock<Block> CHERRY_WALL = registerBlock("cherry_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_FENCE_GATE), true);
    public static final DeferredBlock<Block> BAMBOO_WALL = registerBlock("bamboo_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_FENCE_GATE), true);
    public static final DeferredBlock<Block> CRIMSON_WALL = registerBlock("crimson_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_FENCE_GATE), true);
    public static final DeferredBlock<Block> WARPED_WALL = registerBlock("warped_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_FENCE_GATE), true);

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
    public static final Map<String, DeferredBlock<Block>> POTTED_SAPLINGS = new HashMap<>();

    // Array of wood types (excluding weirwood)
    public static final String[] WOOD_TYPES = {
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
            "yew",
            "blue_soldier_pine",
            "soldier_pine"

    };

    static {
        // Generate all blocks for each wood type
        for (String woodType : WOOD_TYPES) {
            registerWoodBlocks(woodType);
        }
    }

    private static void registerWoodBlocks(String woodType) {

        POTTED_SAPLINGS.put(woodType, registerBlock("potted_" + woodType + "_sapling",
                properties -> new FlowerPotBlock(
                        () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                        () -> SAPLINGS.get(woodType).get(),
                        properties
                ),
                BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion(),
                false));


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
            case "soldier_pine":
                return ModTreeGrower.SOLDIER_PINE;
            case "blue_soldier_pine":
                return ModTreeGrower.BLUE_SOLDIER_PINE;
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
            case "soldier_pine":
                return ModWoodTypes.SOLDIER_PINE;
            case "blue_soldier_pine":
                return ModWoodTypes.BLUE_SOLDIER_PINE;
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

    public static final DeferredBlock<Block> ROTTEN_SIGN = registerBlock("rotten_sign",
            properties -> new ModStandingSignBlock(ModWoodTypes.ROTTEN, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> ROTTEN_WALL_SIGN = registerBlock("rotten_wall_sign",
            properties -> new ModWallSignBlock(ModWoodTypes.ROTTEN, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> ROTTEN_HANGING_SIGN = registerBlock("rotten_hanging_sign",
            properties -> new ModHangingSignBlock(ModWoodTypes.ROTTEN, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> ROTTEN_WALL_HANGING_SIGN = registerBlock("rotten_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodTypes.ROTTEN, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);


    // Charred
    public static final DeferredBlock<Block> CHARRED_SIGN = registerBlock("charred_sign",
            properties -> new ModStandingSignBlock(ModWoodTypes.CHARRED, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> CHARRED_WALL_SIGN = registerBlock("charred_wall_sign",
            properties -> new ModWallSignBlock(ModWoodTypes.CHARRED, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> CHARRED_HANGING_SIGN = registerBlock("charred_hanging_sign",
            properties -> new ModHangingSignBlock(ModWoodTypes.CHARRED, properties),
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noCollission()
                    .strength(1.0F)
                    .sound(SoundType.WOOD)
                    .ignitedByLava(),
            false);

    public static final DeferredBlock<Block> CHARRED_WALL_HANGING_SIGN = registerBlock("charred_wall_hanging_sign",
            properties -> new ModWallHangingSignBlock(ModWoodTypes.CHARRED, properties),
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

    public static final DeferredBlock<Block> POTTED_WEIRWOOD_SAPLING = registerBlock("potted_weirwood_sapling",
            properties -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    () -> WEIRWOOD_SAPLING.get(),
                    properties
            ),
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING).noOcclusion(),
            false);

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

    // ---------------------------(FURNITURE)--------------------------- //
    // VANILLA
// ── TABLES ────────────────────────────────────────────────────────────────

    public static final DeferredBlock<Block> OAK_TABLE = registerBlock("oak_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> DARK_OAK_TABLE = registerBlock("dark_oak_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SPRUCE_TABLE = registerBlock("spruce_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BIRCH_TABLE = registerBlock("birch_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> JUNGLE_TABLE = registerBlock("jungle_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ACACIA_TABLE = registerBlock("acacia_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MANGROVE_TABLE = registerBlock("mangrove_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHERRY_TABLE = registerBlock("cherry_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAMBOO_TABLE = registerBlock("bamboo_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRIMSON_TABLE = registerBlock("crimson_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WARPED_TABLE = registerBlock("warped_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WEIRWOOD_TABLE = registerBlock("weirwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHARRED_TABLE = registerBlock("charred_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ROTTEN_TABLE = registerBlock("rotten_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINE_TABLE = registerBlock("pine_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASH_TABLE = registerBlock("ash_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BEECH_TABLE = registerBlock("beech_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CEDAR_TABLE = registerBlock("cedar_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHESTNUT_TABLE = registerBlock("chestnut_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> HAWTHORN_TABLE = registerBlock("hawthorn_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> IRONWOOD_TABLE = registerBlock("ironwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SENTINEL_TABLE = registerBlock("sentinel_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SYCAMORE_TABLE = registerBlock("sycamore_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKBARK_TABLE = registerBlock("blackbark_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASPEN_TABLE = registerBlock("aspen_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALDER_TABLE = registerBlock("alder_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_CHERRY_TABLE = registerBlock("black_cherry_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_OLIVE_TABLE = registerBlock("black_olive_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRABAPPLE_TABLE = registerBlock("crabapple_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> OLIVE_TABLE = registerBlock("olive_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WHITE_CHERRY_TABLE = registerBlock("white_cherry_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> RED_CHERRY_TABLE = registerBlock("red_cherry_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIR_TABLE = registerBlock("fir_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WILLOW_TABLE = registerBlock("willow_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WORMTREE_TABLE = registerBlock("wormtree_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALMOND_TABLE = registerBlock("almond_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APPLE_TABLE = registerBlock("apple_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APRICOT_TABLE = registerBlock("apricot_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAOBAB_TABLE = registerBlock("baobab_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_COTTONWOOD_TABLE = registerBlock("black_cottonwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKTHORN_TABLE = registerBlock("blackthorn_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOOD_ORANGE_TABLE = registerBlock("blood_orange_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOODWOOD_TABLE = registerBlock("bloodwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_MAHOE_TABLE = registerBlock("blue_mahoe_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> COTTONWOOD_TABLE = registerBlock("cottonwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> DATEPALM_TABLE = registerBlock("datepalm_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> EBONY_TABLE = registerBlock("ebony_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIG_TABLE = registerBlock("fig_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIREPLUM_TABLE = registerBlock("fireplum_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> GOLDENHEART_TABLE = registerBlock("goldenheart_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LEMON_TABLE = registerBlock("lemon_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LIME_TABLE = registerBlock("lime_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LINDEN_TABLE = registerBlock("linden_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAHOGANY_TABLE = registerBlock("mahogany_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAPLE_TABLE = registerBlock("maple_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MYRRH_TABLE = registerBlock("myrrh_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NIGHTWOOD_TABLE = registerBlock("nightwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NUTMEG_TABLE = registerBlock("nutmeg_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ORANGE_TABLE = registerBlock("orange_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEACH_TABLE = registerBlock("peach_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEAR_TABLE = registerBlock("pear_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PECAN_TABLE = registerBlock("pecan_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PERSIMMON_TABLE = registerBlock("persimmon_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINK_IVORY_TABLE = registerBlock("pink_ivory_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PLUM_TABLE = registerBlock("plum_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> POMEGRANATE_TABLE = registerBlock("pomegranate_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PURPLEHEART_TABLE = registerBlock("purpleheart_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> REDWOOD_TABLE = registerBlock("redwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDALWOOD_TABLE = registerBlock("sandalwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDBEGGAR_TABLE = registerBlock("sandbeggar_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> TIGERWOOD_TABLE = registerBlock("tigerwood_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> YEW_TABLE = registerBlock("yew_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SOLDIER_PINE_TABLE = registerBlock("soldier_pine_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_SOLDIER_PINE_TABLE = registerBlock("blue_soldier_pine_table",
            TableBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
// ── STOOLS ────────────────────────────────────────────────────────────────

    public static final DeferredBlock<Block> DARK_OAK_STOOL = registerBlock("dark_oak_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> OAK_STOOL = registerBlock("oak_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SPRUCE_STOOL = registerBlock("spruce_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BIRCH_STOOL = registerBlock("birch_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> JUNGLE_STOOL = registerBlock("jungle_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ACACIA_STOOL = registerBlock("acacia_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MANGROVE_STOOL = registerBlock("mangrove_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHERRY_STOOL = registerBlock("cherry_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAMBOO_STOOL = registerBlock("bamboo_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRIMSON_STOOL = registerBlock("crimson_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WARPED_STOOL = registerBlock("warped_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WEIRWOOD_STOOL = registerBlock("weirwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHARRED_STOOL = registerBlock("charred_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ROTTEN_STOOL = registerBlock("rotten_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINE_STOOL = registerBlock("pine_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASH_STOOL = registerBlock("ash_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BEECH_STOOL = registerBlock("beech_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CEDAR_STOOL = registerBlock("cedar_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHESTNUT_STOOL = registerBlock("chestnut_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> HAWTHORN_STOOL = registerBlock("hawthorn_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> IRONWOOD_STOOL = registerBlock("ironwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SENTINEL_STOOL = registerBlock("sentinel_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SYCAMORE_STOOL = registerBlock("sycamore_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKBARK_STOOL = registerBlock("blackbark_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASPEN_STOOL = registerBlock("aspen_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALDER_STOOL = registerBlock("alder_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_CHERRY_STOOL = registerBlock("black_cherry_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_OLIVE_STOOL = registerBlock("black_olive_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRABAPPLE_STOOL = registerBlock("crabapple_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> OLIVE_STOOL = registerBlock("olive_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WHITE_CHERRY_STOOL = registerBlock("white_cherry_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> RED_CHERRY_STOOL = registerBlock("red_cherry_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIR_STOOL = registerBlock("fir_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WILLOW_STOOL = registerBlock("willow_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WORMTREE_STOOL = registerBlock("wormtree_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALMOND_STOOL = registerBlock("almond_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APPLE_STOOL = registerBlock("apple_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APRICOT_STOOL = registerBlock("apricot_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAOBAB_STOOL = registerBlock("baobab_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_COTTONWOOD_STOOL = registerBlock("black_cottonwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKTHORN_STOOL = registerBlock("blackthorn_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOOD_ORANGE_STOOL = registerBlock("blood_orange_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOODWOOD_STOOL = registerBlock("bloodwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_MAHOE_STOOL = registerBlock("blue_mahoe_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> COTTONWOOD_STOOL = registerBlock("cottonwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> DATEPALM_STOOL = registerBlock("datepalm_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> EBONY_STOOL = registerBlock("ebony_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIG_STOOL = registerBlock("fig_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIREPLUM_STOOL = registerBlock("fireplum_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> GOLDENHEART_STOOL = registerBlock("goldenheart_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LEMON_STOOL = registerBlock("lemon_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LIME_STOOL = registerBlock("lime_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LINDEN_STOOL = registerBlock("linden_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAHOGANY_STOOL = registerBlock("mahogany_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAPLE_STOOL = registerBlock("maple_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MYRRH_STOOL = registerBlock("myrrh_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NIGHTWOOD_STOOL = registerBlock("nightwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NUTMEG_STOOL = registerBlock("nutmeg_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ORANGE_STOOL = registerBlock("orange_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEACH_STOOL = registerBlock("peach_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEAR_STOOL = registerBlock("pear_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PECAN_STOOL = registerBlock("pecan_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PERSIMMON_STOOL = registerBlock("persimmon_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINK_IVORY_STOOL = registerBlock("pink_ivory_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PLUM_STOOL = registerBlock("plum_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> POMEGRANATE_STOOL = registerBlock("pomegranate_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PURPLEHEART_STOOL = registerBlock("purpleheart_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> REDWOOD_STOOL = registerBlock("redwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDALWOOD_STOOL = registerBlock("sandalwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDBEGGAR_STOOL = registerBlock("sandbeggar_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> TIGERWOOD_STOOL = registerBlock("tigerwood_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> YEW_STOOL = registerBlock("yew_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SOLDIER_PINE_STOOL = registerBlock("soldier_pine_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_SOLDIER_PINE_STOOL = registerBlock("blue_soldier_pine_stool",
            StoolBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);

    public static final DeferredBlock<Block> BARREL_LARGE = registerBlock("barrel_large",
            BarrelLargeBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion()
                    .isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);

    public static final DeferredBlock<Block> BARREL_MEDIUM = registerBlock("barrel_medium",
            BarrelMediumBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion()
                    .isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);

    public static final DeferredBlock<Block> BARREL_SMALL = registerBlock("barrel_small",
            BarrelSmallBlock::new,
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion()
                    .isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);

    // Invisible dummy block — no item, no loot table needed
    public static final DeferredBlock<Block> BARREL_DUMMY = registerBlock("barrel_dummy",
            BarrelDummyBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(-1.0f, 3600000.0f)  // unbreakable in survival, like barrier
                    .noOcclusion()
                    .noCollission()                 // no collision so it doesn't interfere
                    .isSuffocating((s,l,p) -> false)
                    .isViewBlocking((s,l,p) -> false),
            false);



    public static final DeferredBlock<Block> DARK_OAK_CHAIR = registerBlock("dark_oak_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> OAK_CHAIR = registerBlock("oak_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SPRUCE_CHAIR = registerBlock("spruce_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BIRCH_CHAIR = registerBlock("birch_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> JUNGLE_CHAIR = registerBlock("jungle_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).strength(2f).noOcclusion().isSuffocating((s, l, p) -> false).isViewBlocking((s, l, p) -> false), false);
    public static final DeferredBlock<Block> ACACIA_CHAIR = registerBlock("acacia_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MANGROVE_CHAIR = registerBlock("mangrove_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHERRY_CHAIR = registerBlock("cherry_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAMBOO_CHAIR = registerBlock("bamboo_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRIMSON_CHAIR = registerBlock("crimson_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WARPED_CHAIR = registerBlock("warped_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);

    // MOD
    public static final DeferredBlock<Block> WEIRWOOD_CHAIR = registerBlock("weirwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHARRED_CHAIR = registerBlock("charred_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ROTTEN_CHAIR = registerBlock("rotten_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINE_CHAIR = registerBlock("pine_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASH_CHAIR = registerBlock("ash_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BEECH_CHAIR = registerBlock("beech_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CEDAR_CHAIR = registerBlock("cedar_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHESTNUT_CHAIR = registerBlock("chestnut_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> HAWTHORN_CHAIR = registerBlock("hawthorn_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> IRONWOOD_CHAIR = registerBlock("ironwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SENTINEL_CHAIR = registerBlock("sentinel_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SYCAMORE_CHAIR = registerBlock("sycamore_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKBARK_CHAIR = registerBlock("blackbark_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASPEN_CHAIR = registerBlock("aspen_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALDER_CHAIR = registerBlock("alder_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_CHERRY_CHAIR = registerBlock("black_cherry_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_OLIVE_CHAIR = registerBlock("black_olive_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRABAPPLE_CHAIR = registerBlock("crabapple_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> OLIVE_CHAIR = registerBlock("olive_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WHITE_CHERRY_CHAIR = registerBlock("white_cherry_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> RED_CHERRY_CHAIR = registerBlock("red_cherry_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIR_CHAIR = registerBlock("fir_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WILLOW_CHAIR = registerBlock("willow_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WORMTREE_CHAIR = registerBlock("wormtree_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALMOND_CHAIR = registerBlock("almond_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APPLE_CHAIR = registerBlock("apple_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APRICOT_CHAIR = registerBlock("apricot_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAOBAB_CHAIR = registerBlock("baobab_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_COTTONWOOD_CHAIR = registerBlock("black_cottonwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKTHORN_CHAIR = registerBlock("blackthorn_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOOD_ORANGE_CHAIR = registerBlock("blood_orange_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOODWOOD_CHAIR = registerBlock("bloodwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_MAHOE_CHAIR = registerBlock("blue_mahoe_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> COTTONWOOD_CHAIR = registerBlock("cottonwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> DATEPALM_CHAIR = registerBlock("datepalm_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> EBONY_CHAIR = registerBlock("ebony_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIG_CHAIR = registerBlock("fig_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIREPLUM_CHAIR = registerBlock("fireplum_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> GOLDENHEART_CHAIR = registerBlock("goldenheart_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LEMON_CHAIR = registerBlock("lemon_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LIME_CHAIR = registerBlock("lime_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LINDEN_CHAIR = registerBlock("linden_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAHOGANY_CHAIR = registerBlock("mahogany_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAPLE_CHAIR = registerBlock("maple_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MYRRH_CHAIR = registerBlock("myrrh_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NIGHTWOOD_CHAIR = registerBlock("nightwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NUTMEG_CHAIR = registerBlock("nutmeg_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ORANGE_CHAIR = registerBlock("orange_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEACH_CHAIR = registerBlock("peach_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEAR_CHAIR = registerBlock("pear_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PECAN_CHAIR = registerBlock("pecan_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PERSIMMON_CHAIR = registerBlock("persimmon_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINK_IVORY_CHAIR = registerBlock("pink_ivory_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PLUM_CHAIR = registerBlock("plum_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> POMEGRANATE_CHAIR = registerBlock("pomegranate_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PURPLEHEART_CHAIR = registerBlock("purpleheart_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> REDWOOD_CHAIR = registerBlock("redwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDALWOOD_CHAIR = registerBlock("sandalwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDBEGGAR_CHAIR = registerBlock("sandbeggar_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> TIGERWOOD_CHAIR = registerBlock("tigerwood_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> YEW_CHAIR = registerBlock("yew_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SOLDIER_PINE_CHAIR = registerBlock("soldier_pine_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_SOLDIER_PINE_CHAIR = registerBlock("blue_soldier_pine_chair", ChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);


    // ── 1b. ModBLocks.java — ARM CHAIRS ───────────────────────────────────────
    public static final DeferredBlock<Block> DARK_OAK_ARM_CHAIR = registerBlock("dark_oak_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> OAK_ARM_CHAIR = registerBlock("oak_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SPRUCE_ARM_CHAIR = registerBlock("spruce_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BIRCH_ARM_CHAIR = registerBlock("birch_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> JUNGLE_ARM_CHAIR = registerBlock("jungle_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ACACIA_ARM_CHAIR = registerBlock("acacia_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MANGROVE_ARM_CHAIR = registerBlock("mangrove_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).strength(2f).noOcclusion().isSuffocating((s, l, p) -> false).isViewBlocking((s, l, p) -> false), false);
    public static final DeferredBlock<Block> CHERRY_ARM_CHAIR = registerBlock("cherry_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAMBOO_ARM_CHAIR = registerBlock("bamboo_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRIMSON_ARM_CHAIR = registerBlock("crimson_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WARPED_ARM_CHAIR = registerBlock("warped_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WEIRWOOD_ARM_CHAIR = registerBlock("weirwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHARRED_ARM_CHAIR = registerBlock("charred_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ROTTEN_ARM_CHAIR = registerBlock("rotten_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINE_ARM_CHAIR = registerBlock("pine_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASH_ARM_CHAIR = registerBlock("ash_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BEECH_ARM_CHAIR = registerBlock("beech_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CEDAR_ARM_CHAIR = registerBlock("cedar_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CHESTNUT_ARM_CHAIR = registerBlock("chestnut_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> HAWTHORN_ARM_CHAIR = registerBlock("hawthorn_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> IRONWOOD_ARM_CHAIR = registerBlock("ironwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SENTINEL_ARM_CHAIR = registerBlock("sentinel_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SYCAMORE_ARM_CHAIR = registerBlock("sycamore_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKBARK_ARM_CHAIR = registerBlock("blackbark_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ASPEN_ARM_CHAIR = registerBlock("aspen_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALDER_ARM_CHAIR = registerBlock("alder_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_CHERRY_ARM_CHAIR = registerBlock("black_cherry_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_OLIVE_ARM_CHAIR = registerBlock("black_olive_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> CRABAPPLE_ARM_CHAIR = registerBlock("crabapple_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> OLIVE_ARM_CHAIR = registerBlock("olive_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WHITE_CHERRY_ARM_CHAIR = registerBlock("white_cherry_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> RED_CHERRY_ARM_CHAIR = registerBlock("red_cherry_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIR_ARM_CHAIR = registerBlock("fir_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WILLOW_ARM_CHAIR = registerBlock("willow_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> WORMTREE_ARM_CHAIR = registerBlock("wormtree_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ALMOND_ARM_CHAIR = registerBlock("almond_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APPLE_ARM_CHAIR = registerBlock("apple_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> APRICOT_ARM_CHAIR = registerBlock("apricot_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BAOBAB_ARM_CHAIR = registerBlock("baobab_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACK_COTTONWOOD_ARM_CHAIR = registerBlock("black_cottonwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLACKTHORN_ARM_CHAIR = registerBlock("blackthorn_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOOD_ORANGE_ARM_CHAIR = registerBlock("blood_orange_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLOODWOOD_ARM_CHAIR = registerBlock("bloodwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_MAHOE_ARM_CHAIR = registerBlock("blue_mahoe_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> COTTONWOOD_ARM_CHAIR = registerBlock("cottonwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> DATEPALM_ARM_CHAIR = registerBlock("datepalm_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> EBONY_ARM_CHAIR = registerBlock("ebony_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIG_ARM_CHAIR = registerBlock("fig_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> FIREPLUM_ARM_CHAIR = registerBlock("fireplum_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> GOLDENHEART_ARM_CHAIR = registerBlock("goldenheart_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LEMON_ARM_CHAIR = registerBlock("lemon_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LIME_ARM_CHAIR = registerBlock("lime_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> LINDEN_ARM_CHAIR = registerBlock("linden_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAHOGANY_ARM_CHAIR = registerBlock("mahogany_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MAPLE_ARM_CHAIR = registerBlock("maple_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> MYRRH_ARM_CHAIR = registerBlock("myrrh_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NIGHTWOOD_ARM_CHAIR = registerBlock("nightwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> NUTMEG_ARM_CHAIR = registerBlock("nutmeg_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> ORANGE_ARM_CHAIR = registerBlock("orange_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEACH_ARM_CHAIR = registerBlock("peach_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PEAR_ARM_CHAIR = registerBlock("pear_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PECAN_ARM_CHAIR = registerBlock("pecan_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PERSIMMON_ARM_CHAIR = registerBlock("persimmon_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PINK_IVORY_ARM_CHAIR = registerBlock("pink_ivory_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PLUM_ARM_CHAIR = registerBlock("plum_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> POMEGRANATE_ARM_CHAIR = registerBlock("pomegranate_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> PURPLEHEART_ARM_CHAIR = registerBlock("purpleheart_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> REDWOOD_ARM_CHAIR = registerBlock("redwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDALWOOD_ARM_CHAIR = registerBlock("sandalwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SANDBEGGAR_ARM_CHAIR = registerBlock("sandbeggar_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> TIGERWOOD_ARM_CHAIR = registerBlock("tigerwood_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> YEW_ARM_CHAIR = registerBlock("yew_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> SOLDIER_PINE_ARM_CHAIR = registerBlock("soldier_pine_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);
    public static final DeferredBlock<Block> BLUE_SOLDIER_PINE_ARM_CHAIR = registerBlock("blue_soldier_pine_arm_chair", ArmChairBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2f).noOcclusion().isSuffocating((s,l,p) -> false).isViewBlocking((s,l,p) -> false), false);


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

    public static final DeferredBlock<Block> CARPENTER_BARREL = registerBlock("carpenter_barrel",
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

    public static final DeferredBlock<Block> BLACKSMITH_BARREL = registerBlock("blacksmith_barrel",
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

