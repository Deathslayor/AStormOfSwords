// This code belongs to the package net.stormofsorts.agotmod.item
package net.darkflameproduction.agotmod.item.creativetabs;

// Importing necessary classes from other packages

import net.darkflameproduction.agotmod.AGoTMod;
import net.darkflameproduction.agotmod.block.ModBLocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// A utility class for creating custom creative mode tabs
public class ModCreativeBuildingBlocks {

    // Deferred register for creative mode tabs
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AGoTMod.MOD_ID);

    // Adding items into the MAIN mod tab in creative
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> AGOT_TAB_BUILDING_BLOCKS = CREATIVE_MODE_TAB.register("agot_tab_building_blocks",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBLocks.DARK_STONE_BRICK.get())) // Icon for the creative mode tab
                    .title(Component.translatable("creativetab.agot_tab_building_blocks")) // Title for the creative mode tab
                    .displayItems((pParameters, pOutput) -> {

                        //Wood
                        pOutput.accept(ModBLocks.WEIRWOOD_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_LOG.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_WEIRWOOD_WOOD.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_PLANKS.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_FACE_LOG.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_STAIRS.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_SLAB.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_BUTTON.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_FENCE.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_WALL.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_DOOR.get());
                        pOutput.accept(ModBLocks.WEIRWOOD_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.SYCAMORE_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_SYCAMORE_LOG.get());
                        pOutput.accept(ModBLocks.SYCAMORE_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_SYCAMORE_WOOD.get());
                        pOutput.accept(ModBLocks.SYCAMORE_PLANKS.get());
                        pOutput.accept(ModBLocks.SYCAMORE_STAIRS.get());
                        pOutput.accept(ModBLocks.SYCAMORE_SLAB.get());
                        pOutput.accept(ModBLocks.SYCAMORE_BUTTON.get());
                        pOutput.accept(ModBLocks.SYCAMORE_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.SYCAMORE_FENCE.get());
                        pOutput.accept(ModBLocks.SYCAMORE_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.SYCAMORE_WALL.get());
                        pOutput.accept(ModBLocks.SYCAMORE_DOOR.get());
                        pOutput.accept(ModBLocks.SYCAMORE_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.SENTINEL_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_SENTINEL_LOG.get());
                        pOutput.accept(ModBLocks.SENTINEL_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_SENTINEL_WOOD.get());
                        pOutput.accept(ModBLocks.SENTINEL_PLANKS.get());
                        pOutput.accept(ModBLocks.SENTINEL_STAIRS.get());
                        pOutput.accept(ModBLocks.SENTINEL_SLAB.get());
                        pOutput.accept(ModBLocks.SENTINEL_BUTTON.get());
                        pOutput.accept(ModBLocks.SENTINEL_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.SENTINEL_FENCE.get());
                        pOutput.accept(ModBLocks.SENTINEL_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.SENTINEL_WALL.get());
                        pOutput.accept(ModBLocks.SENTINEL_DOOR.get());
                        pOutput.accept(ModBLocks.SENTINEL_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.PINE_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_PINE_LOG.get());
                        pOutput.accept(ModBLocks.PINE_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_PINE_WOOD.get());
                        pOutput.accept(ModBLocks.PINE_PLANKS.get());
                        pOutput.accept(ModBLocks.PINE_STAIRS.get());
                        pOutput.accept(ModBLocks.PINE_SLAB.get());
                        pOutput.accept(ModBLocks.PINE_BUTTON.get());
                        pOutput.accept(ModBLocks.PINE_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.PINE_FENCE.get());
                        pOutput.accept(ModBLocks.PINE_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.PINE_WALL.get());
                        pOutput.accept(ModBLocks.PINE_DOOR.get());
                        pOutput.accept(ModBLocks.PINE_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.IRONWOOD_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_IRONWOOD_LOG.get());
                        pOutput.accept(ModBLocks.IRONWOOD_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_IRONWOOD_WOOD.get());
                        pOutput.accept(ModBLocks.IRONWOOD_PLANKS.get());
                        pOutput.accept(ModBLocks.IRONWOOD_STAIRS.get());
                        pOutput.accept(ModBLocks.IRONWOOD_SLAB.get());
                        pOutput.accept(ModBLocks.IRONWOOD_BUTTON.get());
                        pOutput.accept(ModBLocks.IRONWOOD_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.IRONWOOD_FENCE.get());
                        pOutput.accept(ModBLocks.IRONWOOD_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.IRONWOOD_WALL.get());
                        pOutput.accept(ModBLocks.IRONWOOD_DOOR.get());
                        pOutput.accept(ModBLocks.IRONWOOD_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.HAWTHORN_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_HAWTHORN_LOG.get());
                        pOutput.accept(ModBLocks.HAWTHORN_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_HAWTHORN_WOOD.get());
                        pOutput.accept(ModBLocks.HAWTHORN_PLANKS.get());
                        pOutput.accept(ModBLocks.HAWTHORN_STAIRS.get());
                        pOutput.accept(ModBLocks.HAWTHORN_SLAB.get());
                        pOutput.accept(ModBLocks.HAWTHORN_BUTTON.get());
                        pOutput.accept(ModBLocks.HAWTHORN_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.HAWTHORN_FENCE.get());
                        pOutput.accept(ModBLocks.HAWTHORN_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.HAWTHORN_WALL.get());
                        pOutput.accept(ModBLocks.HAWTHORN_DOOR.get());
                        pOutput.accept(ModBLocks.HAWTHORN_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.CHESTNUT_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_CHESTNUT_LOG.get());
                        pOutput.accept(ModBLocks.CHESTNUT_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_CHESTNUT_WOOD.get());
                        pOutput.accept(ModBLocks.CHESTNUT_PLANKS.get());
                        pOutput.accept(ModBLocks.CHESTNUT_STAIRS.get());
                        pOutput.accept(ModBLocks.CHESTNUT_SLAB.get());
                        pOutput.accept(ModBLocks.CHESTNUT_BUTTON.get());
                        pOutput.accept(ModBLocks.CHESTNUT_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.CHESTNUT_FENCE.get());
                        pOutput.accept(ModBLocks.CHESTNUT_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.CHESTNUT_WALL.get());
                        pOutput.accept(ModBLocks.CHESTNUT_DOOR.get());
                        pOutput.accept(ModBLocks.CHESTNUT_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.CEDAR_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_CEDAR_LOG.get());
                        pOutput.accept(ModBLocks.CEDAR_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_CEDAR_WOOD.get());
                        pOutput.accept(ModBLocks.CEDAR_PLANKS.get());
                        pOutput.accept(ModBLocks.CEDAR_STAIRS.get());
                        pOutput.accept(ModBLocks.CEDAR_SLAB.get());
                        pOutput.accept(ModBLocks.CEDAR_BUTTON.get());
                        pOutput.accept(ModBLocks.CEDAR_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.CEDAR_FENCE.get());
                        pOutput.accept(ModBLocks.CEDAR_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.CEDAR_WALL.get());
                        pOutput.accept(ModBLocks.CEDAR_DOOR.get());
                        pOutput.accept(ModBLocks.CEDAR_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.BEECH_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_BEECH_LOG.get());
                        pOutput.accept(ModBLocks.BEECH_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_BEECH_WOOD.get());
                        pOutput.accept(ModBLocks.BEECH_PLANKS.get());
                        pOutput.accept(ModBLocks.BEECH_STAIRS.get());
                        pOutput.accept(ModBLocks.BEECH_SLAB.get());
                        pOutput.accept(ModBLocks.BEECH_BUTTON.get());
                        pOutput.accept(ModBLocks.BEECH_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.BEECH_FENCE.get());
                        pOutput.accept(ModBLocks.BEECH_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.BEECH_WALL.get());
                        pOutput.accept(ModBLocks.BEECH_DOOR.get());
                        pOutput.accept(ModBLocks.BEECH_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.ASH_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_ASH_LOG.get());
                        pOutput.accept(ModBLocks.ASH_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_ASH_WOOD.get());
                        pOutput.accept(ModBLocks.ASH_PLANKS.get());
                        pOutput.accept(ModBLocks.ASH_STAIRS.get());
                        pOutput.accept(ModBLocks.ASH_SLAB.get());
                        pOutput.accept(ModBLocks.ASH_BUTTON.get());
                        pOutput.accept(ModBLocks.ASH_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.ASH_FENCE.get());
                        pOutput.accept(ModBLocks.ASH_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.ASH_WALL.get());
                        pOutput.accept(ModBLocks.ASH_DOOR.get());
                        pOutput.accept(ModBLocks.ASH_TRAPDOOR.get());

                        pOutput.accept(ModBLocks.BLACKBARK_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_BLACKBARK_LOG.get());
                        pOutput.accept(ModBLocks.BLACKBARK_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_BLACKBARK_WOOD.get());
                        pOutput.accept(ModBLocks.BLACKBARK_PLANKS.get());
                        pOutput.accept(ModBLocks.BLACKBARK_STAIRS.get());
                        pOutput.accept(ModBLocks.BLACKBARK_SLAB.get());
                        pOutput.accept(ModBLocks.BLACKBARK_BUTTON.get());
                        pOutput.accept(ModBLocks.BLACKBARK_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.BLACKBARK_FENCE.get());
                        pOutput.accept(ModBLocks.BLACKBARK_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.BLACKBARK_WALL.get());
                        pOutput.accept(ModBLocks.BLACKBARK_DOOR.get());
                        pOutput.accept(ModBLocks.BLACKBARK_TRAPDOOR.get());


                        pOutput.accept(ModBLocks.ASPEN_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_ASPEN_LOG.get());
                        pOutput.accept(ModBLocks.ASPEN_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_ASPEN_WOOD.get());
                        pOutput.accept(ModBLocks.ASPEN_PLANKS.get());
                        pOutput.accept(ModBLocks.ASPEN_STAIRS.get());
                        pOutput.accept(ModBLocks.ASPEN_SLAB.get());
                        pOutput.accept(ModBLocks.ASPEN_BUTTON.get());
                        pOutput.accept(ModBLocks.ASPEN_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.ASPEN_FENCE.get());
                        pOutput.accept(ModBLocks.ASPEN_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.ASPEN_WALL.get());
                        pOutput.accept(ModBLocks.ASPEN_DOOR.get());
                        pOutput.accept(ModBLocks.ASPEN_TRAPDOOR.get());


                        pOutput.accept(ModBLocks.ALDER_LOG.get());
                        pOutput.accept(ModBLocks.STRIPPED_ALDER_LOG.get());
                        pOutput.accept(ModBLocks.ALDER_WOOD.get());
                        pOutput.accept(ModBLocks.STRIPPED_ALDER_WOOD.get());
                        pOutput.accept(ModBLocks.ALDER_PLANKS.get());
                        pOutput.accept(ModBLocks.ALDER_STAIRS.get());
                        pOutput.accept(ModBLocks.ALDER_SLAB.get());
                        pOutput.accept(ModBLocks.ALDER_BUTTON.get());
                        pOutput.accept(ModBLocks.ALDER_PRESSURE_PLATE.get());
                        pOutput.accept(ModBLocks.ALDER_FENCE.get());
                        pOutput.accept(ModBLocks.ALDER_FENCE_GATE.get());
                        pOutput.accept(ModBLocks.ALDER_WALL.get());
                        pOutput.accept(ModBLocks.ALDER_DOOR.get());
                        pOutput.accept(ModBLocks.ALDER_TRAPDOOR.get());

                        // Bricks
                        pOutput.accept(ModBLocks.STONE_BRICK_BUT_COOLER.get());
                        pOutput.accept(ModBLocks.DARK_STONE_BRICK.get());
                        // Kings LANDING BRICKS
                        pOutput.accept(ModBLocks.KINGS_LANDING_BRICK_LARGE.get());

                        //Stones

                        pOutput.accept(ModBLocks.STONE_1_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_1_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_1_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_1_WALL.get());

                        pOutput.accept(ModBLocks.STONE_2_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_2_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_2_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_2_WALL.get());

                        pOutput.accept(ModBLocks.STONE_3_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_3_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_3_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_3_WALL.get());

                        pOutput.accept(ModBLocks.STONE_4_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_4_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_4_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_4_WALL.get());

                        pOutput.accept(ModBLocks.STONE_6_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_6_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_6_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_6_WALL.get());

                        pOutput.accept(ModBLocks.STONE_7_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_7_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_7_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_7_WALL.get());

                        pOutput.accept(ModBLocks.STONE_8_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_8_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_8_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_8_WALL.get());

                        pOutput.accept(ModBLocks.STONE_9_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_9_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_9_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_9_WALL.get());

                        pOutput.accept(ModBLocks.STONE_10_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_10_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_10_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_10_WALL.get());

                        pOutput.accept(ModBLocks.STONE_11_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_11_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_11_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_11_WALL.get());

                        pOutput.accept(ModBLocks.STONE_12_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_12_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_12_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_12_WALL.get());

                        pOutput.accept(ModBLocks.STONE_13_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_13_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_13_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_13_WALL.get());

                        pOutput.accept(ModBLocks.STONE_14_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_14_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_14_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_14_WALL.get());

                        pOutput.accept(ModBLocks.STONE_16_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_16_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_16_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_16_WALL.get());

                        pOutput.accept(ModBLocks.STONE_17_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_17_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_17_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_17_WALL.get());

                        pOutput.accept(ModBLocks.STONE_18_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_18_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_18_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_18_WALL.get());

                        pOutput.accept(ModBLocks.STONE_20_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_20_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_20_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_20_WALL.get());

                        pOutput.accept(ModBLocks.STONE_21_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_21_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_21_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_21_WALL.get());

                        pOutput.accept(ModBLocks.STONE_22_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_22_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_22_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_22_WALL.get());

                        pOutput.accept(ModBLocks.STONE_24_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_24_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_24_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_24_WALL.get());

                        pOutput.accept(ModBLocks.STONE_25_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_25_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_25_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_25_WALL.get());

                        pOutput.accept(ModBLocks.STONE_26_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_26_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_26_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_26_WALL.get());

                        pOutput.accept(ModBLocks.STONE_27_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_27_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_27_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_27_WALL.get());

                        pOutput.accept(ModBLocks.STONE_28_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_28_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_28_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_28_WALL.get());

                        pOutput.accept(ModBLocks.STONE_29_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_29_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_29_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_29_WALL.get());

                        pOutput.accept(ModBLocks.STONE_30_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_30_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_30_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_30_WALL.get());

                        pOutput.accept(ModBLocks.STONE_31_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_31_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_31_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_31_WALL.get());

                        pOutput.accept(ModBLocks.STONE_32_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_32_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_32_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_32_WALL.get());

                        pOutput.accept(ModBLocks.STONE_33_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_33_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_33_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_33_WALL.get());

                        pOutput.accept(ModBLocks.STONE_34_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_34_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_34_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_34_WALL.get());

                        pOutput.accept(ModBLocks.STONE_35_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_35_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_35_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_35_WALL.get());

                        pOutput.accept(ModBLocks.STONE_36_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_36_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_36_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_36_WALL.get());

                        pOutput.accept(ModBLocks.STONE_37_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_37_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_37_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_37_WALL.get());

                        pOutput.accept(ModBLocks.STONE_38_BLOCK.get());
                        pOutput.accept(ModBLocks.STONE_38_STAIRS.get());
                        pOutput.accept(ModBLocks.STONE_38_SLAB.get());
                        pOutput.accept(ModBLocks.STONE_38_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_1_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_1_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_1_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_1_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_2_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_2_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_2_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_2_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_3_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_3_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_3_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_3_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_4_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_4_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_4_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_4_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_5_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_5_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_5_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_5_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_6_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_6_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_6_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_6_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_7_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_7_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_7_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_7_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_8_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_8_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_8_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_8_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_9_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_9_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_9_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_9_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_10_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_10_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_10_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_10_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_11_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_11_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_11_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_11_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_12_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_12_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_12_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_12_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_13_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_13_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_13_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_13_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_14_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_14_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_14_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_14_WALL.get());
                        // pOutput.accept(ModBLocks.SSTONE_15_BLOCK.get());
                        // pOutput.accept(ModBLocks.SSTONE_15_STAIRS.get());
                        // pOutput.accept(ModBLocks.SSTONE_15_SLAB.get());
                        // pOutput.accept(ModBLocks.SSTONE_15_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_16_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_16_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_16_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_16_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_17_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_17_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_17_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_17_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_18_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_18_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_18_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_18_WALL.get());
                        pOutput.accept(ModBLocks.SSTONE_19_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_19_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_19_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_19_WALL.get());

                        pOutput.accept(ModBLocks.SSTONE_20_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_20_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_20_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_20_WALL.get());

                          pOutput.accept(ModBLocks.SSTONE_21_BLOCK.get());
                          pOutput.accept(ModBLocks.SSTONE_21_STAIRS.get());
                           pOutput.accept(ModBLocks.SSTONE_21_SLAB.get());
                          pOutput.accept(ModBLocks.SSTONE_21_WALL.get());

                        pOutput.accept(ModBLocks.SSTONE_22_BLOCK.get());
                        pOutput.accept(ModBLocks.SSTONE_22_STAIRS.get());
                        pOutput.accept(ModBLocks.SSTONE_22_SLAB.get());
                        pOutput.accept(ModBLocks.SSTONE_22_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_2_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_2_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_2_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_2_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_3_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_3_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_3_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_3_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_4_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_4_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_4_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_4_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_5_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_5_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_5_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_5_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_6_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_6_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_6_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_6_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_7_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_7_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_7_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_7_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_8_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_8_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_8_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_8_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_9_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_9_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_9_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_9_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_10_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_10_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_10_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_10_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_11_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_11_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_11_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_11_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_12_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_12_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_12_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_12_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_13_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_13_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_13_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_13_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_14_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_14_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_14_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_14_WALL.get());
// pOutput.accept(ModBLocks.BASALT_15_BLOCK.get());
// pOutput.accept(ModBLocks.BASALT_15_STAIRS.get());
// pOutput.accept(ModBLocks.BASALT_15_SLAB.get());
// pOutput.accept(ModBLocks.BASALT_15_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_16_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_16_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_16_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_16_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_17_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_17_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_17_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_17_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_18_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_18_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_18_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_18_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_19_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_19_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_19_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_19_WALL.get());
                        pOutput.accept(ModBLocks.BASALT_20_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_20_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_20_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_20_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_21_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_21_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_21_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_21_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_22_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_22_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_22_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_22_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_23_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_23_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_23_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_23_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_24_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_24_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_24_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_24_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_25_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_25_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_25_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_25_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_26_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_26_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_26_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_26_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_27_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_27_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_27_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_27_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_28_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_28_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_28_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_28_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_29_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_29_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_29_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_29_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_30_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_30_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_30_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_30_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_31_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_31_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_31_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_31_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_32_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_32_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_32_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_32_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_33_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_33_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_33_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_33_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_34_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_34_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_34_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_34_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_35_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_35_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_35_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_35_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_36_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_36_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_36_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_36_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_37_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_37_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_37_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_37_WALL.get());

                        pOutput.accept(ModBLocks.BASALT_38_BLOCK.get());
                        pOutput.accept(ModBLocks.BASALT_38_STAIRS.get());
                        pOutput.accept(ModBLocks.BASALT_38_SLAB.get());
                        pOutput.accept(ModBLocks.BASALT_38_WALL.get());


                        pOutput.accept(ModBLocks.RSANDSTONE_1_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_1_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_1_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_1_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_2_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_2_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_2_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_2_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_3_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_3_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_3_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_3_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_4_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_4_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_4_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_4_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_5_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_5_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_5_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_5_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_6_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_6_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_6_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_6_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_7_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_7_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_7_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_7_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_8_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_8_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_8_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_8_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_9_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_9_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_9_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_9_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_10_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_10_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_10_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_10_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_11_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_11_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_11_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_11_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_12_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_12_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_12_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_12_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_13_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_13_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_13_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_13_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_14_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_14_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_14_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_14_WALL.get());
// pOutput.accept(ModBLocks.RSANDSTONE_15_BLOCK.get());
// pOutput.accept(ModBLocks.RSANDSTONE_15_STAIRS.get());
// pOutput.accept(ModBLocks.RSANDSTONE_15_SLAB.get());
// pOutput.accept(ModBLocks.RSANDSTONE_15_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_19_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_19_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_19_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_19_WALL.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_20_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_20_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_20_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_20_WALL.get());

  pOutput.accept(ModBLocks.RSANDSTONE_21_BLOCK.get());
  pOutput.accept(ModBLocks.RSANDSTONE_21_STAIRS.get());
   pOutput.accept(ModBLocks.RSANDSTONE_21_SLAB.get());
  pOutput.accept(ModBLocks.RSANDSTONE_21_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_22_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_22_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_22_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_22_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_23_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_23_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_23_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_23_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_24_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_24_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_24_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_24_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_25_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_25_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_25_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_25_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_26_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_26_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_26_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_26_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_27_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_27_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_27_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_27_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_28_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_28_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_28_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_28_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_29_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_29_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_29_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_29_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_30_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_30_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_30_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_30_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_31_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_31_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_31_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_31_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_32_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_32_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_32_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_32_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_33_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_33_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_33_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_33_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_34_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_34_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_34_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_34_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_35_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_35_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_35_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_35_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_36_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_36_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_36_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_36_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_37_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_37_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_37_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_37_WALL.get());

                        pOutput.accept(ModBLocks.RSANDSTONE_38_BLOCK.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_38_STAIRS.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_38_SLAB.get());
                        pOutput.accept(ModBLocks.RSANDSTONE_38_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_STONE_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_STONE_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_STONE_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_STONE_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_1_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_1_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_1_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_1_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_2_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_2_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_2_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_2_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_3_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_3_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_3_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_3_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_4_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_4_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_4_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_4_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_5_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_5_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_5_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_5_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_6_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_6_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_6_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_6_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_7_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_7_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_7_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_7_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_8_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_8_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_8_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_8_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_9_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_9_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_9_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_9_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_10_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_10_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_10_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_10_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_11_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_11_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_11_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_11_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_12_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_12_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_12_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_12_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_13_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_13_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_13_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_13_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_14_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_14_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_14_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_14_WALL.get());
// pOutput.accept(ModBLocks.REDKEEP_15_BLOCK.get());
// pOutput.accept(ModBLocks.REDKEEP_15_STAIRS.get());
// pOutput.accept(ModBLocks.REDKEEP_15_SLAB.get());
// pOutput.accept(ModBLocks.REDKEEP_15_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_16_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_16_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_16_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_16_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_17_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_17_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_17_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_17_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_18_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_18_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_18_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_18_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_19_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_19_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_19_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_19_WALL.get());
                        pOutput.accept(ModBLocks.REDKEEP_20_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_20_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_20_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_20_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_21_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_21_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_21_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_21_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_22_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_22_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_22_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_22_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_23_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_23_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_23_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_23_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_24_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_24_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_24_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_24_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_25_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_25_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_25_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_25_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_26_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_26_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_26_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_26_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_27_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_27_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_27_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_27_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_28_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_28_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_28_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_28_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_29_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_29_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_29_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_29_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_30_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_30_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_30_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_30_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_31_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_31_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_31_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_31_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_32_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_32_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_32_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_32_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_33_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_33_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_33_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_33_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_34_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_34_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_34_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_34_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_35_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_35_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_35_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_35_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_36_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_36_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_36_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_36_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_37_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_37_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_37_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_37_WALL.get());

                        pOutput.accept(ModBLocks.REDKEEP_38_BLOCK.get());
                        pOutput.accept(ModBLocks.REDKEEP_38_STAIRS.get());
                        pOutput.accept(ModBLocks.REDKEEP_38_SLAB.get());
                        pOutput.accept(ModBLocks.REDKEEP_38_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_1_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_1_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_1_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_1_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_3_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_3_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_3_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_3_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_4_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_4_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_4_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_4_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_5_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_5_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_5_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_5_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_6_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_6_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_6_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_6_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_7_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_7_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_7_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_7_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_8_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_8_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_8_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_8_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_9_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_9_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_9_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_9_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_10_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_10_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_10_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_10_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_11_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_11_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_11_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_11_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_12_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_12_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_12_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_12_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_13_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_13_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_13_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_13_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_14_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_14_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_14_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_14_WALL.get());
// pOutput.accept(ModBLocks.BRICKS_15_BLOCK.get());
// pOutput.accept(ModBLocks.BRICKS_15_STAIRS.get());
// pOutput.accept(ModBLocks.BRICKS_15_SLAB.get());
// pOutput.accept(ModBLocks.BRICKS_15_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_16_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_16_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_16_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_16_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_17_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_17_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_17_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_17_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_18_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_18_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_18_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_18_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_19_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_19_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_19_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_19_WALL.get());
                        pOutput.accept(ModBLocks.BRICKS_20_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_20_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_20_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_20_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_21_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_21_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_21_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_21_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_22_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_22_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_22_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_22_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_23_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_23_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_23_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_23_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_24_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_24_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_24_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_24_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_25_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_25_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_25_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_25_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_26_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_26_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_26_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_26_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_27_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_27_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_27_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_27_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_28_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_28_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_28_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_28_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_29_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_29_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_29_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_29_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_30_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_30_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_30_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_30_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_31_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_31_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_31_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_31_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_32_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_32_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_32_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_32_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_33_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_33_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_33_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_33_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_34_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_34_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_34_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_34_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_35_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_35_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_35_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_35_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_36_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_36_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_36_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_36_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_37_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_37_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_37_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_37_WALL.get());

                        pOutput.accept(ModBLocks.BRICKS_38_BLOCK.get());
                        pOutput.accept(ModBLocks.BRICKS_38_STAIRS.get());
                        pOutput.accept(ModBLocks.BRICKS_38_SLAB.get());
                        pOutput.accept(ModBLocks.BRICKS_38_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_1_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_1_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_1_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_1_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_2_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_2_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_2_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_2_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_3_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_3_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_3_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_3_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_4_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_4_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_4_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_4_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_5_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_5_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_5_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_5_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_6_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_6_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_6_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_6_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_7_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_7_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_7_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_7_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_8_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_8_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_8_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_8_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_9_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_9_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_9_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_9_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_10_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_10_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_10_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_10_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_11_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_11_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_11_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_11_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_12_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_12_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_12_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_12_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_13_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_13_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_13_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_13_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_14_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_14_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_14_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_14_WALL.get());
// pOutput.accept(ModBLocks.CALCITE_15_BLOCK.get());
// pOutput.accept(ModBLocks.CALCITE_15_STAIRS.get());
// pOutput.accept(ModBLocks.CALCITE_15_SLAB.get());
// pOutput.accept(ModBLocks.CALCITE_15_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_16_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_16_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_16_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_16_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_17_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_17_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_17_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_17_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_18_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_18_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_18_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_18_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_19_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_19_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_19_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_19_WALL.get());
                        pOutput.accept(ModBLocks.CALCITE_20_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_20_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_20_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_20_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_21_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_21_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_21_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_21_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_22_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_22_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_22_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_22_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_23_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_23_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_23_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_23_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_24_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_24_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_24_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_24_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_25_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_25_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_25_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_25_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_26_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_26_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_26_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_26_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_27_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_27_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_27_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_27_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_28_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_28_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_28_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_28_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_29_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_29_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_29_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_29_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_30_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_30_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_30_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_30_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_31_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_31_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_31_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_31_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_32_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_32_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_32_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_32_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_33_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_33_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_33_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_33_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_34_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_34_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_34_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_34_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_35_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_35_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_35_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_35_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_36_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_36_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_36_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_36_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_37_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_37_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_37_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_37_WALL.get());

                        pOutput.accept(ModBLocks.CALCITE_38_BLOCK.get());
                        pOutput.accept(ModBLocks.CALCITE_38_STAIRS.get());
                        pOutput.accept(ModBLocks.CALCITE_38_SLAB.get());
                        pOutput.accept(ModBLocks.CALCITE_38_WALL.get());

                        pOutput.accept(ModBLocks.CDEEPSLATE_1_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_1_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_1_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_1_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_2_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_2_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_2_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_2_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_3_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_3_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_3_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_3_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_5_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_5_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_5_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_5_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_6_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_6_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_6_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_6_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_7_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_7_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_7_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_7_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_8_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_8_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_8_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_8_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_9_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_9_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_9_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_9_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_10_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_10_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_10_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_10_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_11_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_11_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_11_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_11_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_12_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_12_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_12_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_12_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_13_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_13_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_13_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_13_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_14_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_14_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_14_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_14_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_16_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_16_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_16_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_16_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_17_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_17_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_17_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_17_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_18_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_18_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_18_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_18_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_20_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_20_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_20_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_20_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_21_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_21_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_21_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_21_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_22_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_22_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_22_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_22_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_23_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_23_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_23_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_23_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_24_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_24_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_24_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_24_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_25_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_25_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_25_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_25_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_26_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_26_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_26_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_26_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_27_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_27_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_27_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_27_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_28_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_28_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_28_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_28_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_29_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_29_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_29_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_29_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_30_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_30_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_30_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_30_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_31_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_31_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_31_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_31_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_32_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_32_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_32_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_32_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_33_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_33_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_33_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_33_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_34_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_34_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_34_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_34_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_35_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_35_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_35_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_35_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_36_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_36_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_36_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_36_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_37_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_37_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_37_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_37_WALL.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_38_BLOCK.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_38_STAIRS.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_38_SLAB.get());
                        pOutput.accept(ModBLocks.CDEEPSLATE_38_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_1_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_1_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_1_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_1_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_2_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_2_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_2_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_2_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_3_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_3_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_3_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_3_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_4_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_4_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_4_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_4_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_5_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_5_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_5_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_5_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_6_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_6_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_6_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_6_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_7_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_7_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_7_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_7_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_8_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_8_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_8_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_8_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_9_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_9_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_9_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_9_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_10_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_10_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_10_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_10_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_11_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_11_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_11_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_11_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_12_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_12_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_12_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_12_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_13_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_13_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_13_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_13_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_14_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_14_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_14_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_14_WALL.get());
// pOutput.accept(ModBLocks.GRANITE_15_BLOCK.get());
// pOutput.accept(ModBLocks.GRANITE_15_STAIRS.get());
// pOutput.accept(ModBLocks.GRANITE_15_SLAB.get());
// pOutput.accept(ModBLocks.GRANITE_15_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_16_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_16_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_16_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_16_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_17_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_17_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_17_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_17_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_18_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_18_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_18_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_18_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_19_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_19_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_19_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_19_WALL.get());
                        pOutput.accept(ModBLocks.GRANITE_20_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_20_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_20_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_20_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_21_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_21_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_21_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_21_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_22_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_22_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_22_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_22_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_23_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_23_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_23_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_23_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_24_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_24_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_24_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_24_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_25_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_25_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_25_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_25_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_26_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_26_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_26_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_26_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_27_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_27_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_27_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_27_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_28_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_28_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_28_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_28_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_29_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_29_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_29_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_29_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_30_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_30_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_30_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_30_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_31_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_31_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_31_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_31_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_32_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_32_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_32_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_32_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_33_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_33_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_33_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_33_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_34_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_34_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_34_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_34_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_35_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_35_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_35_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_35_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_36_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_36_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_36_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_36_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_37_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_37_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_37_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_37_WALL.get());

                        pOutput.accept(ModBLocks.GRANITE_38_BLOCK.get());
                        pOutput.accept(ModBLocks.GRANITE_38_STAIRS.get());
                        pOutput.accept(ModBLocks.GRANITE_38_SLAB.get());
                        pOutput.accept(ModBLocks.GRANITE_38_WALL.get());




                        pOutput.accept(ModBLocks.QUAGMIRE.get());
                        /** INGOTS = Blocks */
                        // TIN
                        pOutput.accept(ModBLocks.TIN_ORE.get());
                        pOutput.accept(ModBLocks.RAW_TIN_BLOCK.get());
                        pOutput.accept(ModBLocks.DEEPSLATE_TIN_ORE.get());
                        pOutput.accept(ModBLocks.TIN_BLOCK.get());
                        // BRONZE
                        pOutput.accept(ModBLocks.BRONZE_BLOCK.get());
                        // GEMSTONES
                        pOutput.accept(ModBLocks.YELLOW_DIAMOND_BLOCK.get());
                        pOutput.accept(ModBLocks.TRANSPARENT_DIAMOND_BLOCK.get());
                        pOutput.accept(ModBLocks.BLOODSTONE_BLOCK.get());
                        pOutput.accept(ModBLocks.BLACK_DIAMOND_BLOCK.get());
                        pOutput.accept(ModBLocks.CHALCEDONY_BLOCK.get());
                        pOutput.accept(ModBLocks.AMBER_BLOCK.get());
                        pOutput.accept(ModBLocks.AMETHYST_BLOCK.get());
                        pOutput.accept(ModBLocks.CARNELIAN_BLOCK.get());
                        pOutput.accept(ModBLocks.GARNET_BLOCK.get());
                        pOutput.accept(ModBLocks.JADE_BLOCK.get());
                        pOutput.accept(ModBLocks.JASPER_BLOCK.get());
                        pOutput.accept(ModBLocks.MALACHITE_BLOCK.get());
                        pOutput.accept(ModBLocks.RUBY_BLOCK.get());
                        pOutput.accept(ModBLocks.ONYX_BLOCK.get());
                        pOutput.accept(ModBLocks.OPAL_BLOCK.get());
                        pOutput.accept(ModBLocks.SAPPHIRE_BLOCK.get());
                        pOutput.accept(ModBLocks.MOONSTONE_BLOCK.get());
                        pOutput.accept(ModBLocks.TIGERS_EYE_BLOCK.get());
                        pOutput.accept(ModBLocks.TOPAZ_BLOCK.get());
                        //pOutput.accept(ModBLocks.TOURMALINE_BLOCK.get());


                    })
                    .build());

    // Tells the AGoTMod class to call the modded tabs into the game
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
