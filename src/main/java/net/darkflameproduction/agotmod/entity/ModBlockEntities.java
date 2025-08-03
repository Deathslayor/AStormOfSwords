package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.darkflameproduction.agotmod.block.custom.JobBarrelBlockEntity;
import net.darkflameproduction.agotmod.block.custom.TownHallBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AGoTMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<JobBarrelBlockEntity>> JOB_BARREL =
            BLOCK_ENTITIES.register("job_barrel", () -> {
                List<Block> barrelBlocks = new ArrayList<>();

                // Add all job barrel blocks
                barrelBlocks.add(ModBLocks.ALEHOUSE_BARREL.get());
                barrelBlocks.add(ModBLocks.ARMORSMITH_BARREL.get());
                barrelBlocks.add(ModBLocks.BAKER_BARREL.get());
                barrelBlocks.add(ModBLocks.BANKER_BARREL.get());
                barrelBlocks.add(ModBLocks.BARD_BARREL.get());
                barrelBlocks.add(ModBLocks.BUILDER_BARREL.get());
                barrelBlocks.add(ModBLocks.BUTCHER_BARREL.get());
                barrelBlocks.add(ModBLocks.CARAVAN_MASTER_BARREL.get());
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
            "yew"
    };

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () -> {
                List<Block> signBlocks = new ArrayList<>();

                // Add weirwood signs first
                signBlocks.add(ModBLocks.WEIRWOOD_SIGN.get());
                signBlocks.add(ModBLocks.WEIRWOOD_WALL_SIGN.get());

                // Add all other wood types' signs to the collection
                for (String woodType : ALL_WOOD_TYPES) {
                    signBlocks.add(ModBLocks.SIGNS.get(woodType).get());
                    signBlocks.add(ModBLocks.WALL_SIGNS.get(woodType).get());
                }

                // Convert to array for varargs parameter
                Block[] signBlocksArray = signBlocks.toArray(new Block[0]);

                // Pass all blocks directly as varargs using array - expanded for all 114 blocks (57 wood types * 2 sign types each)
                return new BlockEntityType<>(
                        ModSignBlockEntity::new,
                        signBlocksArray[0], signBlocksArray[1], signBlocksArray[2], signBlocksArray[3],
                        signBlocksArray[4], signBlocksArray[5], signBlocksArray[6], signBlocksArray[7],
                        signBlocksArray[8], signBlocksArray[9], signBlocksArray[10], signBlocksArray[11],
                        signBlocksArray[12], signBlocksArray[13], signBlocksArray[14], signBlocksArray[15],
                        signBlocksArray[16], signBlocksArray[17], signBlocksArray[18], signBlocksArray[19],
                        signBlocksArray[20], signBlocksArray[21], signBlocksArray[22], signBlocksArray[23],
                        signBlocksArray[24], signBlocksArray[25], signBlocksArray[26], signBlocksArray[27],
                        signBlocksArray[28], signBlocksArray[29], signBlocksArray[30], signBlocksArray[31],
                        signBlocksArray[32], signBlocksArray[33], signBlocksArray[34], signBlocksArray[35],
                        signBlocksArray[36], signBlocksArray[37], signBlocksArray[38], signBlocksArray[39],
                        signBlocksArray[40], signBlocksArray[41], signBlocksArray[42], signBlocksArray[43],
                        signBlocksArray[44], signBlocksArray[45], signBlocksArray[46], signBlocksArray[47],
                        signBlocksArray[48], signBlocksArray[49], signBlocksArray[50], signBlocksArray[51],
                        signBlocksArray[52], signBlocksArray[53], signBlocksArray[54], signBlocksArray[55],
                        signBlocksArray[56], signBlocksArray[57], signBlocksArray[58], signBlocksArray[59],
                        signBlocksArray[60], signBlocksArray[61], signBlocksArray[62], signBlocksArray[63],
                        signBlocksArray[64], signBlocksArray[65], signBlocksArray[66], signBlocksArray[67],
                        signBlocksArray[68], signBlocksArray[69], signBlocksArray[70], signBlocksArray[71],
                        signBlocksArray[72], signBlocksArray[73], signBlocksArray[74], signBlocksArray[75],
                        signBlocksArray[76], signBlocksArray[77], signBlocksArray[78], signBlocksArray[79],
                        signBlocksArray[80], signBlocksArray[81], signBlocksArray[82], signBlocksArray[83],
                        signBlocksArray[84], signBlocksArray[85], signBlocksArray[86], signBlocksArray[87],
                        signBlocksArray[88], signBlocksArray[89], signBlocksArray[90], signBlocksArray[91],
                        signBlocksArray[92], signBlocksArray[93], signBlocksArray[94], signBlocksArray[95],
                        signBlocksArray[96], signBlocksArray[97], signBlocksArray[98], signBlocksArray[99],
                        signBlocksArray[100], signBlocksArray[101], signBlocksArray[102], signBlocksArray[103],
                        signBlocksArray[104], signBlocksArray[105], signBlocksArray[106], signBlocksArray[107],
                        signBlocksArray[108], signBlocksArray[109], signBlocksArray[110], signBlocksArray[111],
                        signBlocksArray[112], signBlocksArray[113]);
            });

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () -> {
                List<Block> hangingSignBlocks = new ArrayList<>();

                // Add weirwood hanging signs first
                hangingSignBlocks.add(ModBLocks.WEIRWOOD_HANGING_SIGN.get());
                hangingSignBlocks.add(ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get());

                // Add all other wood types' hanging signs to the collection
                for (String woodType : ALL_WOOD_TYPES) {
                    hangingSignBlocks.add(ModBLocks.HANGING_SIGNS.get(woodType).get());
                    hangingSignBlocks.add(ModBLocks.WALL_HANGING_SIGNS.get(woodType).get());
                }

                // Convert to array for varargs parameter
                Block[] hangingSignBlocksArray = hangingSignBlocks.toArray(new Block[0]);

                // Pass all blocks directly as varargs using array - expanded for all 114 blocks (57 wood types * 2 hanging sign types each)
                return new BlockEntityType<>(
                        ModHangingSignBlockEntity::new,
                        hangingSignBlocksArray[0], hangingSignBlocksArray[1], hangingSignBlocksArray[2], hangingSignBlocksArray[3],
                        hangingSignBlocksArray[4], hangingSignBlocksArray[5], hangingSignBlocksArray[6], hangingSignBlocksArray[7],
                        hangingSignBlocksArray[8], hangingSignBlocksArray[9], hangingSignBlocksArray[10], hangingSignBlocksArray[11],
                        hangingSignBlocksArray[12], hangingSignBlocksArray[13], hangingSignBlocksArray[14], hangingSignBlocksArray[15],
                        hangingSignBlocksArray[16], hangingSignBlocksArray[17], hangingSignBlocksArray[18], hangingSignBlocksArray[19],
                        hangingSignBlocksArray[20], hangingSignBlocksArray[21], hangingSignBlocksArray[22], hangingSignBlocksArray[23],
                        hangingSignBlocksArray[24], hangingSignBlocksArray[25], hangingSignBlocksArray[26], hangingSignBlocksArray[27],
                        hangingSignBlocksArray[28], hangingSignBlocksArray[29], hangingSignBlocksArray[30], hangingSignBlocksArray[31],
                        hangingSignBlocksArray[32], hangingSignBlocksArray[33], hangingSignBlocksArray[34], hangingSignBlocksArray[35],
                        hangingSignBlocksArray[36], hangingSignBlocksArray[37], hangingSignBlocksArray[38], hangingSignBlocksArray[39],
                        hangingSignBlocksArray[40], hangingSignBlocksArray[41], hangingSignBlocksArray[42], hangingSignBlocksArray[43],
                        hangingSignBlocksArray[44], hangingSignBlocksArray[45], hangingSignBlocksArray[46], hangingSignBlocksArray[47],
                        hangingSignBlocksArray[48], hangingSignBlocksArray[49], hangingSignBlocksArray[50], hangingSignBlocksArray[51],
                        hangingSignBlocksArray[52], hangingSignBlocksArray[53], hangingSignBlocksArray[54], hangingSignBlocksArray[55],
                        hangingSignBlocksArray[56], hangingSignBlocksArray[57], hangingSignBlocksArray[58], hangingSignBlocksArray[59],
                        hangingSignBlocksArray[60], hangingSignBlocksArray[61], hangingSignBlocksArray[62], hangingSignBlocksArray[63],
                        hangingSignBlocksArray[64], hangingSignBlocksArray[65], hangingSignBlocksArray[66], hangingSignBlocksArray[67],
                        hangingSignBlocksArray[68], hangingSignBlocksArray[69], hangingSignBlocksArray[70], hangingSignBlocksArray[71],
                        hangingSignBlocksArray[72], hangingSignBlocksArray[73], hangingSignBlocksArray[74], hangingSignBlocksArray[75],
                        hangingSignBlocksArray[76], hangingSignBlocksArray[77], hangingSignBlocksArray[78], hangingSignBlocksArray[79],
                        hangingSignBlocksArray[80], hangingSignBlocksArray[81], hangingSignBlocksArray[82], hangingSignBlocksArray[83],
                        hangingSignBlocksArray[84], hangingSignBlocksArray[85], hangingSignBlocksArray[86], hangingSignBlocksArray[87],
                        hangingSignBlocksArray[88], hangingSignBlocksArray[89], hangingSignBlocksArray[90], hangingSignBlocksArray[91],
                        hangingSignBlocksArray[92], hangingSignBlocksArray[93], hangingSignBlocksArray[94], hangingSignBlocksArray[95],
                        hangingSignBlocksArray[96], hangingSignBlocksArray[97], hangingSignBlocksArray[98], hangingSignBlocksArray[99],
                        hangingSignBlocksArray[100], hangingSignBlocksArray[101], hangingSignBlocksArray[102], hangingSignBlocksArray[103],
                        hangingSignBlocksArray[104], hangingSignBlocksArray[105], hangingSignBlocksArray[106], hangingSignBlocksArray[107],
                        hangingSignBlocksArray[108], hangingSignBlocksArray[109], hangingSignBlocksArray[110], hangingSignBlocksArray[111],
                        hangingSignBlocksArray[112], hangingSignBlocksArray[113]);
            });

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}