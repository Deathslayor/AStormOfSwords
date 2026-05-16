package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.block.custom.JobBarrelBlockEntity;
import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.darkflameproduction.agotmod.block.custom.furniture.ArmChairBlockEntity;
import net.darkflameproduction.agotmod.block.custom.furniture.ChairBlockEntity;
import net.darkflameproduction.agotmod.block.custom.furniture.StoolBlockEntity;
import net.darkflameproduction.agotmod.block.custom.furniture.TableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AGoTMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TableBlockEntity>> TABLE =
            BLOCK_ENTITIES.register("table", () -> new BlockEntityType<>(
                    TableBlockEntity::new,
                    ModBLocks.DARK_OAK_TABLE.get(),
                    ModBLocks.OAK_TABLE.get(),
                    ModBLocks.SPRUCE_TABLE.get(),
                    ModBLocks.BIRCH_TABLE.get(),
                    ModBLocks.JUNGLE_TABLE.get(),
                    ModBLocks.ACACIA_TABLE.get(),
                    ModBLocks.MANGROVE_TABLE.get(),
                    ModBLocks.CHERRY_TABLE.get(),
                    ModBLocks.BAMBOO_TABLE.get(),
                    ModBLocks.CRIMSON_TABLE.get(),
                    ModBLocks.WARPED_TABLE.get(),
                    ModBLocks.WEIRWOOD_TABLE.get(),
                    ModBLocks.CHARRED_TABLE.get(),
                    ModBLocks.ROTTEN_TABLE.get(),
                    ModBLocks.PINE_TABLE.get(),
                    ModBLocks.ASH_TABLE.get(),
                    ModBLocks.BEECH_TABLE.get(),
                    ModBLocks.CEDAR_TABLE.get(),
                    ModBLocks.CHESTNUT_TABLE.get(),
                    ModBLocks.HAWTHORN_TABLE.get(),
                    ModBLocks.IRONWOOD_TABLE.get(),
                    ModBLocks.SENTINEL_TABLE.get(),
                    ModBLocks.SYCAMORE_TABLE.get(),
                    ModBLocks.BLACKBARK_TABLE.get(),
                    ModBLocks.ASPEN_TABLE.get(),
                    ModBLocks.ALDER_TABLE.get(),
                    ModBLocks.BLACK_CHERRY_TABLE.get(),
                    ModBLocks.BLACK_OLIVE_TABLE.get(),
                    ModBLocks.CRABAPPLE_TABLE.get(),
                    ModBLocks.OLIVE_TABLE.get(),
                    ModBLocks.WHITE_CHERRY_TABLE.get(),
                    ModBLocks.RED_CHERRY_TABLE.get(),
                    ModBLocks.FIR_TABLE.get(),
                    ModBLocks.WILLOW_TABLE.get(),
                    ModBLocks.WORMTREE_TABLE.get(),
                    ModBLocks.ALMOND_TABLE.get(),
                    ModBLocks.APPLE_TABLE.get(),
                    ModBLocks.APRICOT_TABLE.get(),
                    ModBLocks.BAOBAB_TABLE.get(),
                    ModBLocks.BLACK_COTTONWOOD_TABLE.get(),
                    ModBLocks.BLACKTHORN_TABLE.get(),
                    ModBLocks.BLOOD_ORANGE_TABLE.get(),
                    ModBLocks.BLOODWOOD_TABLE.get(),
                    ModBLocks.BLUE_MAHOE_TABLE.get(),
                    ModBLocks.COTTONWOOD_TABLE.get(),
                    ModBLocks.DATEPALM_TABLE.get(),
                    ModBLocks.EBONY_TABLE.get(),
                    ModBLocks.FIG_TABLE.get(),
                    ModBLocks.FIREPLUM_TABLE.get(),
                    ModBLocks.GOLDENHEART_TABLE.get(),
                    ModBLocks.LEMON_TABLE.get(),
                    ModBLocks.LIME_TABLE.get(),
                    ModBLocks.LINDEN_TABLE.get(),
                    ModBLocks.MAHOGANY_TABLE.get(),
                    ModBLocks.MAPLE_TABLE.get(),
                    ModBLocks.MYRRH_TABLE.get(),
                    ModBLocks.NIGHTWOOD_TABLE.get(),
                    ModBLocks.NUTMEG_TABLE.get(),
                    ModBLocks.ORANGE_TABLE.get(),
                    ModBLocks.PEACH_TABLE.get(),
                    ModBLocks.PEAR_TABLE.get(),
                    ModBLocks.PECAN_TABLE.get(),
                    ModBLocks.PERSIMMON_TABLE.get(),
                    ModBLocks.PINK_IVORY_TABLE.get(),
                    ModBLocks.PLUM_TABLE.get(),
                    ModBLocks.POMEGRANATE_TABLE.get(),
                    ModBLocks.PURPLEHEART_TABLE.get(),
                    ModBLocks.REDWOOD_TABLE.get(),
                    ModBLocks.SANDALWOOD_TABLE.get(),
                    ModBLocks.SANDBEGGAR_TABLE.get(),
                    ModBLocks.TIGERWOOD_TABLE.get(),
                    ModBLocks.YEW_TABLE.get(),
                    ModBLocks.SOLDIER_PINE_TABLE.get(),
                    ModBLocks.BLUE_SOLDIER_PINE_TABLE.get()
                    ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<StoolBlockEntity>> STOOL =
            BLOCK_ENTITIES.register("stool", () -> new BlockEntityType<>(
                    StoolBlockEntity::new,
                    ModBLocks.DARK_OAK_STOOL.get(), ModBLocks.OAK_STOOL.get(),
                    ModBLocks.SPRUCE_STOOL.get(), ModBLocks.BIRCH_STOOL.get(),
                    ModBLocks.JUNGLE_STOOL.get(), ModBLocks.ACACIA_STOOL.get(),
                    ModBLocks.MANGROVE_STOOL.get(), ModBLocks.CHERRY_STOOL.get(),
                    ModBLocks.BAMBOO_STOOL.get(), ModBLocks.CRIMSON_STOOL.get(),
                    ModBLocks.WARPED_STOOL.get(), ModBLocks.WEIRWOOD_STOOL.get(),
                    ModBLocks.CHARRED_STOOL.get(), ModBLocks.ROTTEN_STOOL.get(),
                    ModBLocks.PINE_STOOL.get(), ModBLocks.ASH_STOOL.get(),
                    ModBLocks.BEECH_STOOL.get(), ModBLocks.CEDAR_STOOL.get(),
                    ModBLocks.CHESTNUT_STOOL.get(), ModBLocks.HAWTHORN_STOOL.get(),
                    ModBLocks.IRONWOOD_STOOL.get(), ModBLocks.SENTINEL_STOOL.get(),
                    ModBLocks.SYCAMORE_STOOL.get(), ModBLocks.BLACKBARK_STOOL.get(),
                    ModBLocks.ASPEN_STOOL.get(), ModBLocks.ALDER_STOOL.get(),
                    ModBLocks.BLACK_CHERRY_STOOL.get(), ModBLocks.BLACK_OLIVE_STOOL.get(),
                    ModBLocks.CRABAPPLE_STOOL.get(), ModBLocks.OLIVE_STOOL.get(),
                    ModBLocks.WHITE_CHERRY_STOOL.get(), ModBLocks.RED_CHERRY_STOOL.get(),
                    ModBLocks.FIR_STOOL.get(), ModBLocks.WILLOW_STOOL.get(),
                    ModBLocks.WORMTREE_STOOL.get(), ModBLocks.ALMOND_STOOL.get(),
                    ModBLocks.APPLE_STOOL.get(), ModBLocks.APRICOT_STOOL.get(),
                    ModBLocks.BAOBAB_STOOL.get(), ModBLocks.BLACK_COTTONWOOD_STOOL.get(),
                    ModBLocks.BLACKTHORN_STOOL.get(), ModBLocks.BLOOD_ORANGE_STOOL.get(),
                    ModBLocks.BLOODWOOD_STOOL.get(), ModBLocks.BLUE_MAHOE_STOOL.get(),
                    ModBLocks.COTTONWOOD_STOOL.get(), ModBLocks.DATEPALM_STOOL.get(),
                    ModBLocks.EBONY_STOOL.get(), ModBLocks.FIG_STOOL.get(),
                    ModBLocks.FIREPLUM_STOOL.get(), ModBLocks.GOLDENHEART_STOOL.get(),
                    ModBLocks.LEMON_STOOL.get(), ModBLocks.LIME_STOOL.get(),
                    ModBLocks.LINDEN_STOOL.get(), ModBLocks.MAHOGANY_STOOL.get(),
                    ModBLocks.MAPLE_STOOL.get(), ModBLocks.MYRRH_STOOL.get(),
                    ModBLocks.NIGHTWOOD_STOOL.get(), ModBLocks.NUTMEG_STOOL.get(),
                    ModBLocks.ORANGE_STOOL.get(), ModBLocks.PEACH_STOOL.get(),
                    ModBLocks.PEAR_STOOL.get(), ModBLocks.PECAN_STOOL.get(),
                    ModBLocks.PERSIMMON_STOOL.get(), ModBLocks.PINK_IVORY_STOOL.get(),
                    ModBLocks.PLUM_STOOL.get(), ModBLocks.POMEGRANATE_STOOL.get(),
                    ModBLocks.PURPLEHEART_STOOL.get(), ModBLocks.REDWOOD_STOOL.get(),
                    ModBLocks.SANDALWOOD_STOOL.get(), ModBLocks.SANDBEGGAR_STOOL.get(),
                    ModBLocks.TIGERWOOD_STOOL.get(), ModBLocks.YEW_STOOL.get(),
                    ModBLocks.SOLDIER_PINE_STOOL.get(),
                    ModBLocks.BLUE_SOLDIER_PINE_STOOL.get()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChairBlockEntity>> CHAIR =
            BLOCK_ENTITIES.register("chair", () -> new BlockEntityType<>(ChairBlockEntity::new,
                    ModBLocks.DARK_OAK_CHAIR.get(), ModBLocks.OAK_CHAIR.get(), ModBLocks.SPRUCE_CHAIR.get(),
                    ModBLocks.BIRCH_CHAIR.get(), ModBLocks.JUNGLE_CHAIR.get(), ModBLocks.ACACIA_CHAIR.get(),
                    ModBLocks.MANGROVE_CHAIR.get(), ModBLocks.CHERRY_CHAIR.get(), ModBLocks.BAMBOO_CHAIR.get(),
                    ModBLocks.CRIMSON_CHAIR.get(), ModBLocks.WARPED_CHAIR.get(), ModBLocks.WEIRWOOD_CHAIR.get(),
                    ModBLocks.CHARRED_CHAIR.get(), ModBLocks.ROTTEN_CHAIR.get(), ModBLocks.PINE_CHAIR.get(),
                    ModBLocks.ASH_CHAIR.get(), ModBLocks.BEECH_CHAIR.get(), ModBLocks.CEDAR_CHAIR.get(),
                    ModBLocks.CHESTNUT_CHAIR.get(), ModBLocks.HAWTHORN_CHAIR.get(), ModBLocks.IRONWOOD_CHAIR.get(),
                    ModBLocks.SENTINEL_CHAIR.get(), ModBLocks.SYCAMORE_CHAIR.get(), ModBLocks.BLACKBARK_CHAIR.get(),
                    ModBLocks.ASPEN_CHAIR.get(), ModBLocks.ALDER_CHAIR.get(), ModBLocks.BLACK_CHERRY_CHAIR.get(),
                    ModBLocks.BLACK_OLIVE_CHAIR.get(), ModBLocks.CRABAPPLE_CHAIR.get(), ModBLocks.OLIVE_CHAIR.get(),
                    ModBLocks.WHITE_CHERRY_CHAIR.get(), ModBLocks.RED_CHERRY_CHAIR.get(), ModBLocks.FIR_CHAIR.get(),
                    ModBLocks.WILLOW_CHAIR.get(), ModBLocks.WORMTREE_CHAIR.get(), ModBLocks.ALMOND_CHAIR.get(),
                    ModBLocks.APPLE_CHAIR.get(), ModBLocks.APRICOT_CHAIR.get(), ModBLocks.BAOBAB_CHAIR.get(),
                    ModBLocks.BLACK_COTTONWOOD_CHAIR.get(), ModBLocks.BLACKTHORN_CHAIR.get(),
                    ModBLocks.BLOOD_ORANGE_CHAIR.get(), ModBLocks.BLOODWOOD_CHAIR.get(),
                    ModBLocks.BLUE_MAHOE_CHAIR.get(), ModBLocks.COTTONWOOD_CHAIR.get(),
                    ModBLocks.DATEPALM_CHAIR.get(), ModBLocks.EBONY_CHAIR.get(), ModBLocks.FIG_CHAIR.get(),
                    ModBLocks.FIREPLUM_CHAIR.get(), ModBLocks.GOLDENHEART_CHAIR.get(), ModBLocks.LEMON_CHAIR.get(),
                    ModBLocks.LIME_CHAIR.get(), ModBLocks.LINDEN_CHAIR.get(), ModBLocks.MAHOGANY_CHAIR.get(),
                    ModBLocks.MAPLE_CHAIR.get(), ModBLocks.MYRRH_CHAIR.get(), ModBLocks.NIGHTWOOD_CHAIR.get(),
                    ModBLocks.NUTMEG_CHAIR.get(), ModBLocks.ORANGE_CHAIR.get(), ModBLocks.PEACH_CHAIR.get(),
                    ModBLocks.PEAR_CHAIR.get(), ModBLocks.PECAN_CHAIR.get(), ModBLocks.PERSIMMON_CHAIR.get(),
                    ModBLocks.PINK_IVORY_CHAIR.get(), ModBLocks.PLUM_CHAIR.get(),
                    ModBLocks.POMEGRANATE_CHAIR.get(), ModBLocks.PURPLEHEART_CHAIR.get(),
                    ModBLocks.REDWOOD_CHAIR.get(), ModBLocks.SANDALWOOD_CHAIR.get(),
                    ModBLocks.SANDBEGGAR_CHAIR.get(), ModBLocks.TIGERWOOD_CHAIR.get(), ModBLocks.YEW_CHAIR.get(),
                    ModBLocks.SOLDIER_PINE_CHAIR.get(), ModBLocks.BLUE_SOLDIER_PINE_CHAIR.get()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ArmChairBlockEntity>> ARM_CHAIR =
            BLOCK_ENTITIES.register("arm_chair", () -> new BlockEntityType<>(ArmChairBlockEntity::new,
                    ModBLocks.DARK_OAK_ARM_CHAIR.get(), ModBLocks.OAK_ARM_CHAIR.get(), ModBLocks.SPRUCE_ARM_CHAIR.get(),
                    ModBLocks.BIRCH_ARM_CHAIR.get(), ModBLocks.JUNGLE_ARM_CHAIR.get(), ModBLocks.ACACIA_ARM_CHAIR.get(),
                    ModBLocks.MANGROVE_ARM_CHAIR.get(), ModBLocks.CHERRY_ARM_CHAIR.get(), ModBLocks.BAMBOO_ARM_CHAIR.get(),
                    ModBLocks.CRIMSON_ARM_CHAIR.get(), ModBLocks.WARPED_ARM_CHAIR.get(), ModBLocks.WEIRWOOD_ARM_CHAIR.get(),
                    ModBLocks.CHARRED_ARM_CHAIR.get(), ModBLocks.ROTTEN_ARM_CHAIR.get(), ModBLocks.PINE_ARM_CHAIR.get(),
                    ModBLocks.ASH_ARM_CHAIR.get(), ModBLocks.BEECH_ARM_CHAIR.get(), ModBLocks.CEDAR_ARM_CHAIR.get(),
                    ModBLocks.CHESTNUT_ARM_CHAIR.get(), ModBLocks.HAWTHORN_ARM_CHAIR.get(), ModBLocks.IRONWOOD_ARM_CHAIR.get(),
                    ModBLocks.SENTINEL_ARM_CHAIR.get(), ModBLocks.SYCAMORE_ARM_CHAIR.get(), ModBLocks.BLACKBARK_ARM_CHAIR.get(),
                    ModBLocks.ASPEN_ARM_CHAIR.get(), ModBLocks.ALDER_ARM_CHAIR.get(), ModBLocks.BLACK_CHERRY_ARM_CHAIR.get(),
                    ModBLocks.BLACK_OLIVE_ARM_CHAIR.get(), ModBLocks.CRABAPPLE_ARM_CHAIR.get(), ModBLocks.OLIVE_ARM_CHAIR.get(),
                    ModBLocks.WHITE_CHERRY_ARM_CHAIR.get(), ModBLocks.RED_CHERRY_ARM_CHAIR.get(), ModBLocks.FIR_ARM_CHAIR.get(),
                    ModBLocks.WILLOW_ARM_CHAIR.get(), ModBLocks.WORMTREE_ARM_CHAIR.get(), ModBLocks.ALMOND_ARM_CHAIR.get(),
                    ModBLocks.APPLE_ARM_CHAIR.get(), ModBLocks.APRICOT_ARM_CHAIR.get(), ModBLocks.BAOBAB_ARM_CHAIR.get(),
                    ModBLocks.BLACK_COTTONWOOD_ARM_CHAIR.get(), ModBLocks.BLACKTHORN_ARM_CHAIR.get(),
                    ModBLocks.BLOOD_ORANGE_ARM_CHAIR.get(), ModBLocks.BLOODWOOD_ARM_CHAIR.get(),
                    ModBLocks.BLUE_MAHOE_ARM_CHAIR.get(), ModBLocks.COTTONWOOD_ARM_CHAIR.get(),
                    ModBLocks.DATEPALM_ARM_CHAIR.get(), ModBLocks.EBONY_ARM_CHAIR.get(), ModBLocks.FIG_ARM_CHAIR.get(),
                    ModBLocks.FIREPLUM_ARM_CHAIR.get(), ModBLocks.GOLDENHEART_ARM_CHAIR.get(), ModBLocks.LEMON_ARM_CHAIR.get(),
                    ModBLocks.LIME_ARM_CHAIR.get(), ModBLocks.LINDEN_ARM_CHAIR.get(), ModBLocks.MAHOGANY_ARM_CHAIR.get(),
                    ModBLocks.MAPLE_ARM_CHAIR.get(), ModBLocks.MYRRH_ARM_CHAIR.get(), ModBLocks.NIGHTWOOD_ARM_CHAIR.get(),
                    ModBLocks.NUTMEG_ARM_CHAIR.get(), ModBLocks.ORANGE_ARM_CHAIR.get(), ModBLocks.PEACH_ARM_CHAIR.get(),
                    ModBLocks.PEAR_ARM_CHAIR.get(), ModBLocks.PECAN_ARM_CHAIR.get(), ModBLocks.PERSIMMON_ARM_CHAIR.get(),
                    ModBLocks.PINK_IVORY_ARM_CHAIR.get(), ModBLocks.PLUM_ARM_CHAIR.get(),
                    ModBLocks.POMEGRANATE_ARM_CHAIR.get(), ModBLocks.PURPLEHEART_ARM_CHAIR.get(),
                    ModBLocks.REDWOOD_ARM_CHAIR.get(), ModBLocks.SANDALWOOD_ARM_CHAIR.get(),
                    ModBLocks.SANDBEGGAR_ARM_CHAIR.get(), ModBLocks.TIGERWOOD_ARM_CHAIR.get(), ModBLocks.YEW_ARM_CHAIR.get(),
                    ModBLocks.SOLDIER_PINE_ARM_CHAIR.get(), ModBLocks.BLUE_SOLDIER_PINE_ARM_CHAIR.get()
            ));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<JobBarrelBlockEntity>> JOB_BARREL =
            BLOCK_ENTITIES.register("job_barrel", () -> {
                List<Block> barrelBlocks = new ArrayList<>();

                // Add all job barrel blocks
                barrelBlocks.add(ModBLocks.ALEHOUSE_BARREL.get());
                barrelBlocks.add(ModBLocks.ARMORSMITH_BARREL.get());
                barrelBlocks.add(ModBLocks.BAKER_BARREL.get());
                barrelBlocks.add(ModBLocks.BANKER_BARREL.get());
                barrelBlocks.add(ModBLocks.BARD_BARREL.get());
                barrelBlocks.add(ModBLocks.BLACKSMITH_BARREL.get());
                barrelBlocks.add(ModBLocks.BUILDER_BARREL.get());
                barrelBlocks.add(ModBLocks.BUTCHER_BARREL.get());
                barrelBlocks.add(ModBLocks.CARAVAN_MASTER_BARREL.get());
                barrelBlocks.add(ModBLocks.CARPENTER_BARREL.get());
                barrelBlocks.add(ModBLocks.CATTLE_HERDER_BARREL.get());
                barrelBlocks.add(ModBLocks.CHARCOAL_BURNER_BARREL.get());
                barrelBlocks.add(ModBLocks.CHICKEN_BREEDER_BARREL.get());
                barrelBlocks.add(ModBLocks.FARMER_BARREL.get());
                barrelBlocks.add(ModBLocks.GOAT_HERDER_BARREL.get());
                barrelBlocks.add(ModBLocks.GROCER_BARREL.get());
                barrelBlocks.add(ModBLocks.GUARD_BARREL.get());
                barrelBlocks.add(ModBLocks.HERBALIST_BARREL.get());
                barrelBlocks.add(ModBLocks.HORSE_BREEDER_BARREL.get());
                barrelBlocks.add(ModBLocks.HUNTER_BARREL.get());
                barrelBlocks.add(ModBLocks.INNKEEPER_BARREL.get());
                barrelBlocks.add(ModBLocks.JEWELER_BARREL.get());
                barrelBlocks.add(ModBLocks.LUMBERJACK_BARREL.get());
                barrelBlocks.add(ModBLocks.MAESTER_BARREL.get());
                barrelBlocks.add(ModBLocks.MINER_BARREL.get());
                barrelBlocks.add(ModBLocks.PIG_BREEDER_BARREL.get());
                barrelBlocks.add(ModBLocks.PYROMANCER_BARREL.get());
                barrelBlocks.add(ModBLocks.QUARRY_BARREL.get());
                barrelBlocks.add(ModBLocks.SCRIBE_BARREL.get());
                barrelBlocks.add(ModBLocks.SEPTON_BARREL.get());
                barrelBlocks.add(ModBLocks.SHEEP_HERDER_BARREL.get());
                barrelBlocks.add(ModBLocks.SHIPWRIGHT_BARREL.get());
                barrelBlocks.add(ModBLocks.SLAVER_BARREL.get());
                barrelBlocks.add(ModBLocks.SMELTER_BARREL.get());
                barrelBlocks.add(ModBLocks.STONEMASON_BARREL.get());
                barrelBlocks.add(ModBLocks.SWORDSMITH_BARREL.get());
                barrelBlocks.add(ModBLocks.TAILOR_BARREL.get());
                barrelBlocks.add(ModBLocks.TANNER_BARREL.get());
                barrelBlocks.add(ModBLocks.TRADER_BARREL.get());

                Block[] barrelBlocksArray = barrelBlocks.toArray(new Block[0]);

                return new BlockEntityType<>(
                        JobBarrelBlockEntity::new,
                        barrelBlocksArray);
            });

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<TownHallBlockEntity>> TOWN_HALL =
            BLOCK_ENTITIES.register("town_hall", () ->
                    new BlockEntityType<>(
                            TownHallBlockEntity::new,
                            ModBLocks.TOWN_HALL.get()));

    // Define the wood types array (excluding weirwood as it's handled separately)
    private static final String[] ALL_WOOD_TYPES = {
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
            "soldier_pine",
            "blue_soldier_pine"

    };

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () -> {
                List<Block> signBlocks = new ArrayList<>();

                signBlocks.add(ModBLocks.WEIRWOOD_SIGN.get());
                signBlocks.add(ModBLocks.WEIRWOOD_WALL_SIGN.get());
                signBlocks.add(ModBLocks.ROTTEN_SIGN.get());
                signBlocks.add(ModBLocks.ROTTEN_WALL_SIGN.get());
                signBlocks.add(ModBLocks.CHARRED_SIGN.get());
                signBlocks.add(ModBLocks.CHARRED_WALL_SIGN.get());

                for (String woodType : ALL_WOOD_TYPES) {
                    signBlocks.add(ModBLocks.SIGNS.get(woodType).get());
                    signBlocks.add(ModBLocks.WALL_SIGNS.get(woodType).get());
                }

                Block first = signBlocks.get(0);
                Block[] rest = signBlocks.subList(1, signBlocks.size()).toArray(new Block[0]);
                Block[] signBlocksArray = signBlocks.toArray(new Block[0]);
                return new BlockEntityType<ModSignBlockEntity>(ModSignBlockEntity::new, signBlocksArray);
            });

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () -> {
                List<Block> hangingSignBlocks = new ArrayList<>();

                hangingSignBlocks.add(ModBLocks.WEIRWOOD_HANGING_SIGN.get());
                hangingSignBlocks.add(ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get());
                hangingSignBlocks.add(ModBLocks.ROTTEN_HANGING_SIGN.get());
                hangingSignBlocks.add(ModBLocks.ROTTEN_WALL_HANGING_SIGN.get());
                hangingSignBlocks.add(ModBLocks.CHARRED_HANGING_SIGN.get());
                hangingSignBlocks.add(ModBLocks.CHARRED_WALL_HANGING_SIGN.get());

                for (String woodType : ALL_WOOD_TYPES) {
                    hangingSignBlocks.add(ModBLocks.HANGING_SIGNS.get(woodType).get());
                    hangingSignBlocks.add(ModBLocks.WALL_HANGING_SIGNS.get(woodType).get());
                }

                Block first = hangingSignBlocks.get(0);
                Block[] rest = hangingSignBlocks.subList(1, hangingSignBlocks.size()).toArray(new Block[0]);
                Block[] hangingSignBlocksArray = hangingSignBlocks.toArray(new Block[0]);
                return new BlockEntityType<ModHangingSignBlockEntity>(ModHangingSignBlockEntity::new, hangingSignBlocksArray);
            });

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}