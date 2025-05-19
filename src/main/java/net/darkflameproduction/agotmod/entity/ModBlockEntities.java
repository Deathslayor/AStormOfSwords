package net.darkflameproduction.agotmod.entity;

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CreakingHeartBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, AGoTMod.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    new BlockEntityType<>(ModSignBlockEntity::new,
                            ModBLocks.WEIRWOOD_SIGN.get(),
                            ModBLocks.WEIRWOOD_WALL_SIGN.get(),
                            ModBLocks.ASH_SIGN.get(),
                            ModBLocks.ASH_WALL_SIGN.get(),

                            ModBLocks.BEECH_SIGN.get(),
                            ModBLocks.BEECH_WALL_SIGN.get(),

                            ModBLocks.CEDAR_SIGN.get(),
                            ModBLocks.CEDAR_WALL_SIGN.get(),

                            ModBLocks.CHESTNUT_SIGN.get(),
                            ModBLocks.CHESTNUT_WALL_SIGN.get(),

                            ModBLocks.HAWTHORN_SIGN.get(),
                            ModBLocks.HAWTHORN_WALL_SIGN.get(),

                            ModBLocks.IRONWOOD_SIGN.get(),
                            ModBLocks.IRONWOOD_WALL_SIGN.get(),

                            ModBLocks.SENTINEL_SIGN.get(),
                            ModBLocks.SENTINEL_WALL_SIGN.get(),
                            ModBLocks.SYCAMORE_SIGN.get(),
                            ModBLocks.SYCAMORE_WALL_SIGN.get(),

                            ModBLocks.BLACKBARK_SIGN.get(),
                            ModBLocks.BLACKBARK_WALL_SIGN.get(),

                            ModBLocks.ASPEN_SIGN.get(),
                            ModBLocks.ASPEN_WALL_SIGN.get(),

                            ModBLocks.ALDER_SIGN.get(),
                            ModBLocks.ALDER_WALL_SIGN.get(),
                            ModBLocks.PINE_SIGN.get(),
                            ModBLocks.PINE_WALL_SIGN.get()));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    new BlockEntityType<>(ModHangingSignBlockEntity::new,
                            ModBLocks.WEIRWOOD_HANGING_SIGN.get(),
                            ModBLocks.WEIRWOOD_WALL_HANGING_SIGN.get(),
                            ModBLocks.ASH_HANGING_SIGN.get(),
                            ModBLocks.ASH_WALL_HANGING_SIGN.get(),

                            ModBLocks.BEECH_HANGING_SIGN.get(),
                            ModBLocks.BEECH_WALL_HANGING_SIGN.get(),

                            ModBLocks.CEDAR_HANGING_SIGN.get(),
                            ModBLocks.CEDAR_WALL_HANGING_SIGN.get(),

                            ModBLocks.CHESTNUT_HANGING_SIGN.get(),
                            ModBLocks.CHESTNUT_WALL_HANGING_SIGN.get(),

                            ModBLocks.HAWTHORN_HANGING_SIGN.get(),
                            ModBLocks.HAWTHORN_WALL_HANGING_SIGN.get(),

                            ModBLocks.IRONWOOD_HANGING_SIGN.get(),
                            ModBLocks.IRONWOOD_WALL_HANGING_SIGN.get(),
                            ModBLocks.SYCAMORE_HANGING_SIGN.get(),
                            ModBLocks.SYCAMORE_WALL_HANGING_SIGN.get(),

                            ModBLocks.BLACKBARK_HANGING_SIGN.get(),
                            ModBLocks.BLACKBARK_WALL_HANGING_SIGN.get(),

                            ModBLocks.ASPEN_HANGING_SIGN.get(),
                            ModBLocks.ASPEN_WALL_HANGING_SIGN.get(),

                            ModBLocks.ALDER_HANGING_SIGN.get(),
                            ModBLocks.ALDER_WALL_HANGING_SIGN.get(),

                            ModBLocks.SENTINEL_HANGING_SIGN.get(),
                            ModBLocks.SENTINEL_WALL_HANGING_SIGN.get(),
                            ModBLocks.PINE_HANGING_SIGN.get(),
                            ModBLocks.PINE_WALL_HANGING_SIGN.get()));






    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}