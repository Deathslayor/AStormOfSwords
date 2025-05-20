package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
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

    // Define the wood types array (including weirwood)
    private static final String[] ALL_WOOD_TYPES = {
            "weirwood",
            "sycamore",
            "sentinel",
            "pine",
            "ironwood",
            "hawthorn",
            "chestnut",
            "cedar",
            "beech",
            "ash",
            "blackbark",
            "aspen",
            "alder"
    };

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () -> {
                List<Block> signBlocks = new ArrayList<>();

                // Add all wood types' signs to the collection
                for (String woodType : ALL_WOOD_TYPES) {
                    signBlocks.add(woodType.equals("weirwood")
                            ? ModBLocks.WEIRWOOD_SIGN.get()
                            : ModBLocks.SIGNS.get(woodType).get());

                    signBlocks.add(woodType.equals("weirwood")
                            ? ModBLocks.WEIRWOOD_WALL_SIGN.get()
                            : ModBLocks.WALL_SIGNS.get(woodType).get());
                }

                // Convert to array for varargs parameter
                Block[] signBlocksArray = signBlocks.toArray(new Block[0]);

                // Pass all blocks directly as varargs using array
                return new BlockEntityType<>(
                        ModSignBlockEntity::new,
                        signBlocksArray[0], signBlocksArray[1], signBlocksArray[2], signBlocksArray[3],
                        signBlocksArray[4], signBlocksArray[5], signBlocksArray[6], signBlocksArray[7],
                        signBlocksArray[8], signBlocksArray[9], signBlocksArray[10], signBlocksArray[11],
                        signBlocksArray[12], signBlocksArray[13], signBlocksArray[14], signBlocksArray[15],
                        signBlocksArray[16], signBlocksArray[17], signBlocksArray[18], signBlocksArray[19],
                        signBlocksArray[20], signBlocksArray[21], signBlocksArray[22], signBlocksArray[23],
                        signBlocksArray[24], signBlocksArray[25]);
            });

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () -> {
                List<Block> hangingSignBlocks = new ArrayList<>();

                // Add all wood types' hanging signs to the collection
                for (String woodType : ALL_WOOD_TYPES) {
                    hangingSignBlocks.add(woodType.equals("weirwood")
                            ? ModBLocks.WEIRWOOD_HANGING_SIGN.get()
                            : ModBLocks.HANGING_SIGNS.get(woodType).get());

                    hangingSignBlocks.add(woodType.equals("weirwood")
                            ? ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get()
                            : ModBLocks.WALL_HANGING_SIGNS.get(woodType).get());
                }

                // Convert to array for varargs parameter
                Block[] hangingSignBlocksArray = hangingSignBlocks.toArray(new Block[0]);

                // Pass all blocks directly as varargs using array
                return new BlockEntityType<>(
                        ModHangingSignBlockEntity::new,
                        hangingSignBlocksArray[0], hangingSignBlocksArray[1], hangingSignBlocksArray[2], hangingSignBlocksArray[3],
                        hangingSignBlocksArray[4], hangingSignBlocksArray[5], hangingSignBlocksArray[6], hangingSignBlocksArray[7],
                        hangingSignBlocksArray[8], hangingSignBlocksArray[9], hangingSignBlocksArray[10], hangingSignBlocksArray[11],
                        hangingSignBlocksArray[12], hangingSignBlocksArray[13], hangingSignBlocksArray[14], hangingSignBlocksArray[15],
                        hangingSignBlocksArray[16], hangingSignBlocksArray[17], hangingSignBlocksArray[18], hangingSignBlocksArray[19],
                        hangingSignBlocksArray[20], hangingSignBlocksArray[21], hangingSignBlocksArray[22], hangingSignBlocksArray[23],
                        hangingSignBlocksArray[24], hangingSignBlocksArray[25]);
            });

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}