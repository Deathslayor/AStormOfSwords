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
